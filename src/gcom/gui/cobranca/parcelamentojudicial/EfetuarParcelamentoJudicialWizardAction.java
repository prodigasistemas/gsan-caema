/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
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
	
	
	//1� aba - Im�vel
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
		
			//[SB0008] Pesquisar Cliente Usu�rio
			case EfetuarParcelamentoJudicialActionForm.PESQUISAR_CLIENTE_USUARIO:	
				return new ExibirEfetuarParcelamentoJudicialImovelAction().pesquisarClienteUsuario(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			//[SB0001] Limpar Cliente Usu�rio	
			case EfetuarParcelamentoJudicialActionForm.LIMPAR_CLIENTE_USUARIO:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().limparClienteUsuario(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0011] Pesquisar Im�vel
			case EfetuarParcelamentoJudicialActionForm.PESQUISAR_IMOVEL:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().pesquisarImovel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0002] Limpar Im�vel
			case EfetuarParcelamentoJudicialActionForm.LIMPAR_IMOVEL:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().limparImovel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0003] Adicionar Im�vel
			case EfetuarParcelamentoJudicialActionForm.ADICIONAR_IMOVEL:
				return new ExibirEfetuarParcelamentoJudicialImovelAction().adicionarImovel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
			
			//[SB0004] Remover Im�vel
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
	
	
	//2� aba - D�bitos
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
	
	//3� aba - Negocia��o
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
			
			//[SB0009] Pesquisar Cliente Respons�vel
			case EfetuarParcelamentoJudicialActionForm.PESQUISAR_CLIENTE_RESPONSAVEL:	
				return new ExibirEfetuarParcelamentoJudicialNegociacaoAction().pesquisarClienteResponsavel(
						actionMapping, actionForm, httpServletRequest, httpServletResponse);
				
			//[SB0021] Limpar Cliente Respons�vel
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
				
			//[SB0018] Calcular Honor�rios	
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
	
	//4� aba - Conclus�o
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
			
			//[SB0037] Calcular Data de Vencimento 1� Parcela
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
