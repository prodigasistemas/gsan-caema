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

import gcom.cadastro.cliente.Cliente;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.parcelamentojudicial.bean.ContaParcelamentoJudicialHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.RegistroImovelHelper;
import gcom.util.Util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
 * @date 21/03/2013
 */

public class ExibirEfetuarParcelamentoJudicialNegociacaoAction extends GcomAction {
	
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//VALIDA��O DA 1� ABA
		//-----------------------------------------------------------------------------------------------------------------
		//Campos da 1� aba para validar
		Collection<RegistroImovelHelper> listaImoveis = form.getListaRegistroImovelHelper();
		String idRegistroPrincipal = form.getIdRegistroPrincipal();
		String periodoInicial = form.getAmReferenciaInicial();
		String periodoFinal = form.getAmReferenciaFinal();		
		boolean debitosImoveisInformados = form.verificarDebitosImoveisInformados();
		
		//Validando a 1� aba
		this.getFachada().validarEfetuarParcelamentoJudicialImovel(listaImoveis,idRegistroPrincipal,periodoInicial,periodoFinal,debitosImoveisInformados);
		
		//[SB0039] Verificar Campos Alterados 1� Aba
		form.verificarCamposAlteradosPrimeiraAba();
		form.setListaRegistroImovelHelperAnterior(new ArrayList<RegistroImovelHelper>(form.getListaRegistroImovelHelper()));
		
		//Selecionando o registro principal
		for(Iterator<RegistroImovelHelper> it = listaImoveis.iterator();it.hasNext();){
			RegistroImovelHelper helper = it.next();
			if(helper.getIdColecao().equals(idRegistroPrincipal)){
				form.setRegistroImovelPrincipal(helper);
				break;
			}
		}
		
		//-----------------------------------------------------------------------------------------------------------------
		
		//VALIDA��O DA 2� ABA
		//-----------------------------------------------------------------------------------------------------------------
		//Campos da 2� aba para validar
		String[] idsContasSelecionadas = form.getIdsContasSelecionadas();
		
		//Validando a 2� aba
		this.getFachada().validarEfetuarParcelamentoJudicialDebitos(idsContasSelecionadas, form.getRegistroImovelPrincipal());
		
		//[SB0040] Verificar Campos Alterados 2� Aba
		form.verificarCamposAlteradosSegundaAba();
		form.setIdsContasSelecionadasAnterior(form.getIdsContasSelecionadas());
		form.setListaContaParcelamentoJudicialHelperAnterior(form.getListaContaParcelamentoJudicialHelper());
		//-----------------------------------------------------------------------------------------------------------------
		
		//Setando os campos da 3� aba
		//1.4.2. Valor do D�bito
		String[] idsSelecionados = form.getIdsContasSelecionadas();
		Collection<ContaParcelamentoJudicialHelper> listaContaParcelamentoJudicialHelper =
				form.getListaContaParcelamentoJudicialHelper();
		BigDecimal valorDebito = this.obterValorTotalDebitos(idsSelecionados, listaContaParcelamentoJudicialHelper);
		form.setValorDebito(Util.formatarMoedaReal(valorDebito));
		
		return actionMapping.findForward("efetuarParcelamentoJudicialNegociacaoAction");
	}
	
	/**
	 * 
	 * [SB0009] Pesquisar Cliente Respons�vel
	 * 
	 * @author Hugo Azevedo
	 * @date 21/03/2013
	 */
	public ActionForward pesquisarClienteResponsavel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		String idClienteResponsavel = form.getIdClienteResponsavel();
		
		//1. Caso exista cliente com o c�digo informado
		//  [IT0001] Pesquisar Cliente
		Cliente cliente = this.getFachada().obterCliente(idClienteResponsavel);
		if(cliente != null){
			
			//2.1. O sistema dever� exibir o nome do cliente no campo TextBox 
			//     referente a Descri��o do Cliente Respons�vel
			//     [IT0002] Pesquisar Nome do Cliente
			form.setDescClienteResponsavel(cliente.getNome());
			this.getSessao(httpServletRequest).removeAttribute("clienteResponsavelInexistenteException");
		}
		//2. Caso contr�rio
		else{
			//2. o sistema dever� exibir "CLIENTE INEXISTENTE" em vermelho
			//   no campo TextBox referente a Descri��o do Cliente Respons�vel
			this.getSessao(httpServletRequest).setAttribute("clienteResponsavelInexistenteException", "sim");
			form.setDescClienteResponsavel("CLIENTE INEXISTENTE");
			form.setIdClienteResponsavel("");
		}
		
		
		return actionMapping.findForward("efetuarParcelamentoJudicialNegociacaoAction");
	}
	
	/**
	 * 
	 * [SB0021] Limpar Cliente Respons�vel
	 * 
	 * @author Hugo Azevedo
	 * @date 21/03/2013
	 */
	public ActionForward limparClienteResponsavel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//1. O sistema dever� limpar todos os campos referentes a Cliente Respons�vel 
		//   e voltar ao passo 1 do fluxo principal.
		
		form.setDescClienteResponsavel("");
		form.setIdClienteResponsavel("");
		this.getSessao(httpServletRequest).removeAttribute("clienteResponsavelInexistenteException");
		return actionMapping.findForward("efetuarParcelamentoJudicialNegociacaoAction");
	}
	
	/**
	 * 
	 * [SB0016] Calcular Desconto
	 * 
	 * @author Hugo Azevedo
	 * @date 21/03/2013
	 */
	public ActionForward calcularDesconto(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		httpServletResponse.setContentType("text");
		String valorAcordo = form.getValorAcordo();
		String valorDebito = form.getValorDebito();
		
		try {
			
			BigDecimal valorAcordoBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorAcordo);
			BigDecimal valorDebitoBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorDebito);
			BigDecimal valorPorcentagem  = new BigDecimal("0.00");
			
			//[FE0016] Verificar Valor do Acordo
			//------------------------------------------------------------------
			//1. Caso o Valor do Acordo informado seja maior do que o Valor do D�bito
			if(valorAcordoBigDecimal.compareTo(valorDebitoBigDecimal) > 0){
				//1. o sistema dever� exibir a mensagem 
				//   "Valor do Acordo deve ser menor ou igual que o Valor do D�bito selecionado."
				httpServletResponse.setStatus(300);
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.getWriter().write("Valor do Acordo deve ser menor ou igual que o Valor do D�bito selecionado.");
				return null;
			}
			
			//Caso o valor do acordo informado seja igual a zero
			if(valorAcordoBigDecimal.compareTo(new BigDecimal("0")) == 0){
				httpServletResponse.setStatus(300);
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.getWriter().write("Valor do Acordo deve ser maior que 0 (zero).");
				return null;
			}
			//------------------------------------------------------------------
			
			//1. Caso o Valor do Acordo seja menor do que o Valor do D�bito
			if(valorAcordoBigDecimal.compareTo(valorDebitoBigDecimal) < 0){
				//1.1. O sistema dever� atribuir ao Percentual de Desconto, no formato "999,99%", o valor de
				//    (1 - (Valor do Acordo/Valor do D�bito)) *100;
				valorPorcentagem = new BigDecimal("1")
								  .subtract(valorAcordoBigDecimal.divide(valorDebitoBigDecimal,20,BigDecimal.ROUND_DOWN))
								  .multiply(new BigDecimal("100"));
				form.setPercentualDesconto(Util.formatarMoedaReal(valorPorcentagem));
				
			}
			//2. Caso contr�rio:
			else{
				//2.1. O sistema dever� atribuir ao Percentual de Desconto o valor "0,00%"
				form.setPercentualDesconto(Util.formatarMoedaReal(valorPorcentagem));
			}
		
		
			httpServletResponse.getWriter().write(form.getPercentualDesconto());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * [SB0017] Calcular Custas
	 * 
	 * @author Hugo Azevedo
	 * @date 25/03/2013
	 */
	public ActionForward calcularCustas(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		httpServletResponse.setContentType("text");
		
		String valorCustas = form.getValorCustas();
		String percentualCustas = form.getPercentualCustas();
		String valorAcordo = form.getValorAcordo();
		BigDecimal valorAcordoBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorAcordo);
		String quemChamou = (String)httpServletRequest.getParameter("quemChamou");
		try {
			
			//Caso o valor do acordo informado seja igual a zero
			if(valorAcordoBigDecimal.compareTo(new BigDecimal("0")) == 0){
				httpServletResponse.setStatus(300);
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.getWriter().write("Valor do Acordo deve ser maior que 0 (zero).");
				return null;
			}
			
			//1. Caso tenha sido informado o Valor de Custas
			if(valorCustas != null && !valorCustas.trim().equals("") && quemChamou != null && quemChamou.equals("valor")){
				
				//[FE0005] Verificar Valor das Custas
				//--------------------------------------------------------------------
				//1. Caso o Valor das Custas informado seja maior do que o Valor do Acordo
				if(Util.formatarMoedaRealparaBigDecimal(valorCustas).compareTo(Util.formatarMoedaRealparaBigDecimal(valorAcordo)) > 0){
					
					//1. o sistema dever� exibir a mensagem "Valor das Custas deve ser menor ou igual que o Valor do Acordo."
					httpServletResponse.setStatus(300);
					httpServletResponse.setCharacterEncoding("UTF-8");
					httpServletResponse.getWriter().write("Valor das Custas deve ser menor ou igual que o Valor do Acordo.");
					return null;
				}
				
				//--------------------------------------------------------------------
				
				
				//1. o sistema dever� atribuir ao Percentual de Custas o valor de
				//   1.1. (Valor de Custas/Valor do Acordo) *100
				BigDecimal percentualCustasBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorCustas)
															.divide(Util.formatarMoedaRealparaBigDecimal(valorAcordo),4,BigDecimal.ROUND_DOWN)
															.multiply(new BigDecimal("100"));
				
				form.setPercentualCustas(Util.formatarMoedaReal(percentualCustasBigDecimal));
				httpServletResponse.getWriter().write(form.getPercentualCustas()+"-"+EfetuarParcelamentoJudicialActionForm.BLOQUEIO_PERCENTUAL);
			}
			
			//2. Caso contr�rio, caso tenha sido informado o Percentual de Custas,
			else if(percentualCustas != null && !percentualCustas.trim().equals("") && quemChamou != null && quemChamou.equals("percentual")){
				
				//[FE0007] Verificar Percentual das Custas
				//------------------------------------------------------------------
				//1. Caso o Percentual das Custas informado seja maior do que "100%"
				if(Util.formatarMoedaRealparaBigDecimal(percentualCustas).compareTo(new BigDecimal("100")) > 0){
					//o sistema dever� exibir a mensagem "Percentual das Custas inv�lido."
					httpServletResponse.setStatus(300);
					httpServletResponse.setCharacterEncoding("UTF-8");
					httpServletResponse.getWriter().write("Percentual das Custas inv�lido.");
					return null;
				}
				//------------------------------------------------------------------
				
				//2. o sistema dever� atribuir ao Valor de Custas o valor de
				//2.1. Valor do Acordo * (Percentual de Custas/100);
				BigDecimal valorCustasBigDecimal = Util.formatarMoedaRealparaBigDecimal(percentualCustas)
													   .divide(new BigDecimal("100"),4,BigDecimal.ROUND_DOWN)
													   .multiply(Util.formatarMoedaRealparaBigDecimal(valorAcordo));
				
				form.setValorCustas(Util.formatarMoedaReal(valorCustasBigDecimal));
				httpServletResponse.getWriter().write(form.getValorCustas()+"-"+EfetuarParcelamentoJudicialActionForm.BLOQUEIO_VALOR);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return null;
	}
	
	/**
	 * 
	 * [SB0018] Calcular Honor�rios
	 * 
	 * @author Hugo Azevedo
	 * @date 25/03/2013
	 */
	public ActionForward calcularHonorarios(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		httpServletResponse.setContentType("text");
		
		String valorHonorarios = form.getValorHonorarios();
		String percentualHonorarios = form.getPercentualHonorarios();
		String valorAcordo = form.getValorAcordo();
		BigDecimal valorAcordoBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorAcordo);
		String quemChamou = (String)httpServletRequest.getParameter("quemChamou");
		try {
			
			//Caso o valor do acordo informado seja igual a zero
			if(valorAcordoBigDecimal.compareTo(new BigDecimal("0")) == 0){
				httpServletResponse.setStatus(300);
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.getWriter().write("Valor do Acordo deve ser maior que 0 (zero).");
				return null;
			}
			
			//1. Caso tenha sido informado o Valor de Honor�rios
			if(valorHonorarios != null && !valorHonorarios.trim().equals("") && quemChamou != null && quemChamou.equals("valor")){
				
				//[FE0006] Verificar Valor dos Honor�rios
				//--------------------------------------------------------------------
				//1. Caso o Valor dos Honor�rios informado seja maior do que o Valor do Acordo
				if(Util.formatarMoedaRealparaBigDecimal(valorHonorarios).compareTo(Util.formatarMoedaRealparaBigDecimal(valorAcordo)) > 0){
					
					//1. o sistema dever� exibir a mensagem "Valor dos Honor�rios deve ser menor ou igual que o Valor do Acordo."
					httpServletResponse.setStatus(300);
					httpServletResponse.setCharacterEncoding("UTF-8");
					httpServletResponse.getWriter().write("Valor dos Honor�rios deve ser menor ou igual que o Valor do Acordo.");
					return null;
				}
				//--------------------------------------------------------------------
				
				
				//1. o sistema dever� atribuir ao Percentual de Honor�rios o valor de
				//   1.1. (Valor de Honor�rios /Valor do Acordo) *100;
				BigDecimal percentualHonorariosBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorHonorarios)
															.divide(Util.formatarMoedaRealparaBigDecimal(valorAcordo),4,BigDecimal.ROUND_DOWN)
															.multiply(new BigDecimal("100"));
				
				form.setPercentualHonorarios(Util.formatarMoedaReal(percentualHonorariosBigDecimal));
				httpServletResponse.getWriter().write(form.getPercentualHonorarios()+"-"+EfetuarParcelamentoJudicialActionForm.BLOQUEIO_PERCENTUAL);
			}
			
			//2. Caso contr�rio, caso tenha sido informado o Percentual de Honor�rios,
			else if(percentualHonorarios != null && !percentualHonorarios.trim().equals("") && quemChamou != null && quemChamou.equals("percentual")){
				
				//[FE0008] Verificar Percentual dos Honor�rios
				//------------------------------------------------------------------
				//1. Caso o Percentual dos Honor�rios informado seja maior do que "100%"
				if(Util.formatarMoedaRealparaBigDecimal(percentualHonorarios).compareTo(new BigDecimal("100")) > 0){
					//o sistema dever� exibir a mensagem "Percentual dos Honor�rios inv�lido."
					httpServletResponse.setStatus(300);
					httpServletResponse.setCharacterEncoding("UTF-8");
					httpServletResponse.getWriter().write("Percentual dos Honor�rios inv�lido.");
					return null;
				}
				//------------------------------------------------------------------
				
				//2. Caso contr�rio, caso tenha sido informado o Percentual de Honor�rios, 
				//   o sistema dever� atribuir ao Valor de Honor�rios o valor de:
				//	 2.1. Valor do Acordo * (Percentual de Honor�rios /100);
				BigDecimal valorHonorariosBigDecimal = Util.formatarMoedaRealparaBigDecimal(percentualHonorarios)
													   .divide(new BigDecimal("100"),4,BigDecimal.ROUND_DOWN)
													   .multiply(Util.formatarMoedaRealparaBigDecimal(valorAcordo));
				
				form.setValorHonorarios(Util.formatarMoedaReal(valorHonorariosBigDecimal));
				httpServletResponse.getWriter().write(form.getValorHonorarios()+"-"+EfetuarParcelamentoJudicialActionForm.BLOQUEIO_VALOR);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return null;
		
	}
	
	/**
	 *
	 * Remover Documento Judicial
	 * 
	 * @author Hugo Azevedo
	 * @date 25/03/2013
	 */
	public ActionForward removerDocumentoJudicial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		form.setDocumentoAcordoJudicial(null);
		form.setDocumentoAcordoJudicialCopia(null);
		
		return actionMapping.findForward("efetuarParcelamentoJudicialNegociacaoAction"); 
	}
	
	
	/*
	 * M�todos auxiliares
	 */
	private BigDecimal obterValorTotalDebitos(String[] idsSelecionados, 
					   Collection<ContaParcelamentoJudicialHelper> listaContaParcelamentoJudicialHelper){
		
		BigDecimal retorno = new BigDecimal(0);
		
		for(String id : idsSelecionados){
			if(!id.equals("-1")){
				for(Iterator<ContaParcelamentoJudicialHelper> it =
					listaContaParcelamentoJudicialHelper.iterator();it.hasNext();){
					ContaParcelamentoJudicialHelper helper = it.next();
					if(helper.getIdConta().toString().equals(id)){
						retorno = retorno.add(helper.getValorConta()).add(helper.getAcrescimoImpontualidade());
					}
				}
			}
		}
		return retorno;	
	}
}
