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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.CobrancaIndevidaMotivo;
import gcom.atendimentopublico.registroatendimento.bean.PesquisarDocumentosHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1396] Informar Dados para Devolução de Valor Faturado e Pago Indevidamente
 * 
 * @author Hugo Azevedo
 * @date 20/11/2012
 * 
 */
public class InformarDadosDevValorFatPagoIndevOutrosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("informarDadosConcluir");
		HttpSession sessao = httpServletRequest.getSession(false);
		InformarDadosDevValorFatPagoIndevActionForm form = (InformarDadosDevValorFatPagoIndevActionForm) actionForm;
		
		String valorFaturado = form.getValorFaturado();
		String valorCorrigido = form.getValorCorrigido();	
		
		//Validação dos campos obrigatórios
		if(valorCorrigido == null || valorCorrigido.equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Valor Corrigido");
		}
		
		BigDecimal valorFaturadoBD = Util.formatarMoedaRealparaBigDecimal(valorFaturado);
		BigDecimal valorCorrigidoBD = Util.formatarMoedaRealparaBigDecimal(valorCorrigido);
		
		
		//Caso o valor corrigido seja igual do que o valor faturado
		if(valorCorrigidoBD.compareTo(valorFaturadoBD) == 0){
			throw new ActionServletException("atencao.valor_nao_pode_ser_igual_o","Valor Corrigido","Valor Faturado");
		}
		
		//Recuperando o documento selecionado
		PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper)sessao.getAttribute("helperSelecionado");
		
		helper.setValorDevolucao(Util.subtrairBigDecimal(valorFaturadoBD, valorCorrigidoBD));
		return retorno;
	}
	
	
	private PesquisarDocumentosHelper obterDocumentoPagoIndevColecao(
			Collection colecaoPagamentosValoresCobIndevidamente,
			String idPagamento) {
		
		PesquisarDocumentosHelper retorno = null;
		
		Iterator it = colecaoPagamentosValoresCobIndevidamente.iterator();
		while(it.hasNext()){
			PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper)it.next();
			if(helper.getIdPagamento().toString().equals(idPagamento)){
				retorno = helper;
				break;
			}
		}
		return retorno;		
	}

	
}