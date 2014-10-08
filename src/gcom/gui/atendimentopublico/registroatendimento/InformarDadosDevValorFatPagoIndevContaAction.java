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
public class InformarDadosDevValorFatPagoIndevContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("informarDadosConcluir");
		HttpSession sessao = httpServletRequest.getSession(false);
		InformarDadosDevValorFatPagoIndevActionForm form = (InformarDadosDevValorFatPagoIndevActionForm) actionForm;
		
		//Recuperando o documento selecionado
		PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper)sessao.getAttribute("helperSelecionado");
		
		//Motivo da Cobrança Indevida
		//------------------------------------------------------------------------------------------------------------------------
		if(form.getIdMotivoCobrancaIndevida() == null || 
				form.getIdMotivoCobrancaIndevida().equals(Util.converterObjetoParaString(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			
			 throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Motivo da Cobrança Indevida");
		}
		//------------------------------------------------------------------------------------------------------------------------
		
		
		// Valor Total da conta
		//------------------------------------------------------------------------------------------------------------------------
		if(form.getValorTotalConta() == null || form.getValorTotalConta().equals("")){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Valor Total da Conta");
		}
		
		//BigDecimal valorTotalConta = Util.formatarMoedaRealparaBigDecimal(form.getValorTotalConta());	
		BigDecimal valorPagamento = helper.getValorPagamento();
		BigDecimal valorTotalConta = Util.formatarMoedaRealparaBigDecimal(form.getValorTotalConta());
		
		if (valorTotalConta.equals(new BigDecimal("0.00"))){
			throw new ActionServletException("atencao.valor_conta_igual_zero");
		}
		else if (valorTotalConta.compareTo(new BigDecimal("0.00")) == -1){	
			throw new ActionServletException("atencao.valor_conta_negativo");
		}
		
		//Caso o valor corrigido seja igual do que o valor faturado
		if(valorTotalConta.compareTo(valorPagamento) == 0){
			throw new ActionServletException("atencao.valor_nao_pode_ser_igual_o","Valor Corrigido","Valor Faturado");
		}
		//------------------------------------------------------------------------------------------------------------------------
		
			
		//Motivo da Retificação
        //=========================================================================================
		String idMotivo = form.getIdMotivoCobrancaIndevida();
		CobrancaIndevidaMotivo motivoRet = this.getFachada().pesquisarCobrancaIndevidaMotivo(new Integer(idMotivo));
		helper.setMotivoCobIndevida(motivoRet);
		helper.setValorDevolucao(Util.subtrairBigDecimal(valorPagamento,valorTotalConta));
        //=========================================================================================
		return retorno;
	}
	
}