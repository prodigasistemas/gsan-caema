/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package gcom.gui.cobranca.parcelamentojudicial;

import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamentojudicial.ParcelamentoJudicial;
import gcom.cobranca.parcelamentojudicial.ParcelamentoJudicialImovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * 
 * [UC1463] Cancelar Parcelamento Judicial
 * 
 * @author Hugo Azevedo
 * @date 16/03/2013
 */
public class ExibirCancelarParcelamentoJudicialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		ActionForward retorno = actionMapping.findForward("cancelarParcJudicial");
		CancelarParcelamentoJudicialActionForm form = (CancelarParcelamentoJudicialActionForm)actionForm;
		
		String idParcelamento = (String)httpServletRequest.getParameter("idParcelamentoJudicial");
		
		//Validando Id do parcelamento
		if(!Util.validarStringNumerica(idParcelamento)){
			throw new ActionServletException("atencao.elemento_invalido",null,"Parcelamento Judicial");
		}
		
		Integer idParcelamentoJudicial = new Integer(idParcelamento);
		
		//[IT0001] Obter Dados do Parcelamento Judicial.
		ParcelamentoJudicialImovel parcelamentoJudImovel = this.getFachada().obterParcelamentoJudicialImovel(idParcelamentoJudicial);
		
		if(parcelamentoJudImovel == null){
			throw new ActionServletException("atencao.pesquisa_inexistente",null,"Parcelamento Judicial");
		}
		//[FE0001] Verificar situação do parcelamento judicial
		this.getFachada().verificarSituacaoParcelamentoJudicial(parcelamentoJudImovel.getParcelamentoJudicial());
		
		
		//Preenchendo os campos no formulário
		//3.1. Nª do Processo Judicial
		ParcelamentoJudicial parc = parcelamentoJudImovel.getParcelamentoJudicial();
		String numeroProcesso = parc.getNumeroProcesso();
		form.setNumeroProcessoJudicial(numeroProcesso);
		
		//4.1. Cliente Responsável
		form.setClienteResponsavel(parcelamentoJudImovel.getParcelamentoJudicial().getClienteResponsavel().getNome());
		
		//5.1. Imóvel Principal
		form.setImovelPrincipal(parcelamentoJudImovel.getImovel().getId().toString());
		
		//6.1. Data do Parcelamento
		form.setDataParcelamento(Util.formatarData(parcelamentoJudImovel.getParcelamentoJudicial().getDataParcelamento()));
		
		//7.1. Data do Cancelamento
		form.setDataCancelamento(Util.formatarData(new Date()));
		
		//8.1. Motivo do Cancelamento
		//[IT0002] Obter Lista de Motivos de Cancelamento
		Collection<ParcelamentoMotivoDesfazer> listaMotivosCancelamento = this.getFachada().obterListaMotivosCancelamento();
		form.setListaMotivoCancelamento(listaMotivosCancelamento);
		
		return retorno;
	}
	
}
