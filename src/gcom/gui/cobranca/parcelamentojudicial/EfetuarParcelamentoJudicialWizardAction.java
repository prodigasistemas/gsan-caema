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

import gcom.gui.WizardAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * 
 * [UC1441] Efetuar Parcelamento Judicial
 * 
 * @author Hugo Azevedo
 * @date 15/03/2013
 */
public class EfetuarParcelamentoJudicialWizardAction extends WizardAction {
	
	
	//1ª aba - Imóvel
	//------------------------------------------------------------------------------
	public ActionForward exibirEfetuarParcelamentoJudicialImovelAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		int metodo = 0;
		String metodoRequest = (String)httpServletRequest.getParameter("metodo");
		if(metodoRequest != null & Util.isInteger(metodoRequest)){
			metodo = new Integer(metodoRequest).intValue();
		}
		
		switch(metodo){
		
			//[SB0008] Pesquisar Cliente Usuário
			case EfetuarParcelamentoJudicialActionForm.PESQUISAR_CLIENTE_USUARIO:	
				return new ExibirEfetuarParcelamentoJudicialImovelAction().pesquisarClienteUsuario(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			//[SB0001] Limpar Cliente Usuário	
			case EfetuarParcelamentoJudicialActionForm.LIMPAR_CLIENTE_USUARIO:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().limparClienteUsuario(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0011] Pesquisar Imóvel
			case EfetuarParcelamentoJudicialActionForm.PESQUISAR_IMOVEL:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().pesquisarImovel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0002] Limpar Imóvel
			case EfetuarParcelamentoJudicialActionForm.LIMPAR_IMOVEL:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().limparImovel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0003] Adicionar Imóvel
			case EfetuarParcelamentoJudicialActionForm.ADICIONAR_IMOVEL:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().adicionarImovel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0004] Remover Imóvel
			case EfetuarParcelamentoJudicialActionForm.REMOVER_IMOVEL:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().removerImovel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
		
		default:
			return new ExibirEfetuarParcelamentoJudicialImovelAction().unspecified(
					actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		

	}
	
	public ActionForward efetuarParcelamentoJudicialImovelAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new EfetuarParcelamentoJudicialImovelAction().execute(
				actionMapping, actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	
	}
	//------------------------------------------------------------------------------
	
	
	//2ª aba - Débitos
	//------------------------------------------------------------------------------
	public ActionForward exibirEfetuarParcelamentoJudicialDebitosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirEfetuarParcelamentoJudicialDebitosAction().unspecified(
				actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	
	public ActionForward efetuarParcelamentoJudicialDebitosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new EfetuarParcelamentoJudicialDebitosAction().execute(
				actionMapping, actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	
	}
	//------------------------------------------------------------------------------
	
	//3ª aba - Negociação
	//------------------------------------------------------------------------------
	public ActionForward exibirEfetuarParcelamentoJudicialNegociacaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		int metodo = 0;
		String metodoRequest = (String)httpServletRequest.getParameter("metodo");
		if(metodoRequest != null & Util.isInteger(metodoRequest)){
			metodo = new Integer(metodoRequest).intValue();
		}
		
		switch(metodo){
			
			//[SB0009] Pesquisar Cliente Responsável
			case EfetuarParcelamentoJudicialActionForm.PESQUISAR_CLIENTE_RESPONSAVEL:	
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().pesquisarClienteResponsavel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			//[SB0021] Limpar Cliente Responsável
			case EfetuarParcelamentoJudicialActionForm.LIMPAR_CLIENTE_RESPONSAVEL:	
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().limparClienteResponsavel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			//[SB0016] Calcular Desconto
			case EfetuarParcelamentoJudicialActionForm.CALCULAR_DESCONTO:
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().calcularDesconto(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			//[SB0017] Calcular Custas
			case EfetuarParcelamentoJudicialActionForm.CALCULAR_CUSTAS:
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().calcularCustas(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);	
				
			//[SB0018] Calcular Honorários	
			case EfetuarParcelamentoJudicialActionForm.CALCULAR_HONORARIOS:
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().calcularHonorarios(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);	
				
			case EfetuarParcelamentoJudicialActionForm.REMOVER_DOCUMENTO_JUDICIAL:
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().removerDocumentoJudicial(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);	
				
			default:	
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().unspecified(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		
	}
	
	public ActionForward efetuarParcelamentoJudicialNegociacaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new EfetuarParcelamentoJudicialNegociacaoAction().execute(
				actionMapping, actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	
	}
	//------------------------------------------------------------------------------
	
	//4ª aba - Conclusão
	//------------------------------------------------------------------------------
	public ActionForward exibirEfetuarParcelamentoJudicialConclusaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		int metodo = 0;
		String metodoRequest = (String)httpServletRequest.getParameter("metodo");
		if(metodoRequest != null & Util.isInteger(metodoRequest)){
			metodo = new Integer(metodoRequest).intValue();
		}
		
		switch(metodo){
		
			//[SB0024] Calcular Valor Parcelas
			case EfetuarParcelamentoJudicialActionForm.CALCULAR_VALOR_PARCELAS:
				return new ExibirEfetuarParcelamentoJudicialConclusaoAction().calcularValorParcelas(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0037] Calcular Data de Vencimento 1ª Parcela
			case EfetuarParcelamentoJudicialActionForm.CALCULAR_DATA_VENC_PRIMEIRA_PARCELA:
				return new ExibirEfetuarParcelamentoJudicialConclusaoAction().calcularDataVencPrimeiraParcela(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			case EfetuarParcelamentoJudicialActionForm.LIMPAR_LISTA_PARCELAS:
				return new ExibirEfetuarParcelamentoJudicialConclusaoAction().limparListaParcelas(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			default:
				return new ExibirEfetuarParcelamentoJudicialConclusaoAction().unspecified(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		
		
	}
	
	public ActionForward efetuarParcelamentoJudicialConclusaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new EfetuarParcelamentoJudicialConclusaoAction().execute(
				actionMapping, actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	//------------------------------------------------------------------------------	
	
	
	//[SB0030] Efetuar Parcelamento Judicial
	public ActionForward concluirEfetuarParcelamentoJudicialAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ConcluirEfetuarParcelamentoJudicialAction().execute(
				actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	
	
	//[SB0023] Informar Valor Parcelas
	//------------------------------------------------------------------------------	
	public ActionForward exibirInformarValorParcelasPopupAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		int metodo = 0;
		String metodoRequest = (String)httpServletRequest.getParameter("metodo");
		if(metodoRequest != null & Util.isInteger(metodoRequest)){
			metodo = new Integer(metodoRequest).intValue();
		}
		
		switch(metodo){
		
			case EfetuarParcelamentoJudicialActionForm.ADICIONAR_PARCELA:
				return new ExibirInformarValorParcelasPopupAction().adicionarParcela(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			case EfetuarParcelamentoJudicialActionForm.REMOVER_PARCELA:
				return new ExibirInformarValorParcelasPopupAction().removerParcela(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			case EfetuarParcelamentoJudicialActionForm.DESFAZER_PARCELAS_INFORMADAS:
				return new ExibirInformarValorParcelasPopupAction().desfazerParcelasInformadas(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
						
			default:
				return new ExibirInformarValorParcelasPopupAction().unspecified(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
	}
	
	public ActionForward informarValorParcelasPopupAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new InformarValorParcelasPopupAction().execute(
				actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	//------------------------------------------------------------------------------	
	
}
