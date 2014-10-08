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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.bean.PagamentoRADevolucaoValoresHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.CreditosHelper;
import gcom.faturamento.bean.GuiaDevolucaoHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoOrigem;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0630]Solicitar Emissão do Extrato de Débitos
 * 
 * Esta classe tem por finalidade exibir para o usuário a tela que exibirá
 * as contas, débitos, créditos e guias para seleção e posteriormente 
 * emissao do extrato de débito dos selecionados
 * 
 * @author Vivianne Sousa
 * @date 02/08/2007
 */
public class ExibirEfetuarDevolucaoValoresPagosDuplicidadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirEfetuarDevolucaoValoresPagosDuplicidade");

		FiltrarRegistroAtendimentoDevolucaoValoresActionForm form = 
		(FiltrarRegistroAtendimentoDevolucaoValoresActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		httpServletRequest.setAttribute("habilitarRadioGerar", "1");
		
		String idImovel = form.getIdImovelSelecionado();
		
		if(httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer").trim().equalsIgnoreCase("sim")){

			limparCampos(httpServletRequest, form, sessao);
			
		}
		
		//[FS0002] - Verificar existência do tipo de débito
		if(httpServletRequest.getParameter("pesquisarTipoDebito") != null
			&& httpServletRequest.getParameter("pesquisarTipoDebito").trim().equalsIgnoreCase("sim")
			&& form.getTipoDebito() != null 
			&&	!form.getTipoDebito().trim().equals("")) {
			
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, form.getTipoDebito()));
			
			Collection<DebitoTipo> colecaoDebitoTipo = 
					fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
			
			if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {
				sessao.setAttribute("debitoTipoEncontrado", "true");
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();
				form.setNomeTipoDebito(debitoTipo.getDescricao());
			} else {
				sessao.removeAttribute("debitoTipoEncontrado");
				form.setTipoDebito("");
				form.setNomeTipoDebito("Tipo de Débito inexistente");
			}		
		} else if(httpServletRequest.getParameter("pesquisarFuncAnalista") != null
				&& httpServletRequest.getParameter("pesquisarFuncAnalista").trim().equalsIgnoreCase("sim")
				&& form.getIdFuncionarioAnalista() != null 
				&&	!form.getIdFuncionarioAnalista().trim().equals("")){
			//[UC0234] - Pesquisar Funcionário
			//[FS0001] - Verificar existência do funcionário
			
			Funcionario funcionario = this.pesquisarFuncionario(form.getIdFuncionarioAnalista());
			
			if(funcionario != null){
				sessao.setAttribute("funcionarioAnalistaEncontrado", "true");
				form.setIdFuncionarioAnalista(""+funcionario.getId());
				form.setNomeFuncionarioAnalista(funcionario.getNome());
			}else{
				sessao.removeAttribute("funcionarioAnalistaEncontrado");
				form.setIdFuncionarioAnalista("");
				form.setNomeFuncionarioAnalista("Funcionário inexistente");
			}
		} else if(httpServletRequest.getParameter("pesquisarFuncAutorizador") != null
				&& httpServletRequest.getParameter("pesquisarFuncAutorizador").trim().equalsIgnoreCase("sim")
				&& form.getIdFuncionarioAutorizador() != null 
				&&	!form.getIdFuncionarioAutorizador().trim().equals("")){
			//[UC0234] - Pesquisar Funcionário
			//[FS0001] - Verificar existência do funcionário
			
			Funcionario funcionario = this.pesquisarFuncionario(form.getIdFuncionarioAutorizador());
			
			if(funcionario != null){
				sessao.setAttribute("funcionarioAutorizadorEncontrado", "true");
				form.setIdFuncionarioAutorizador(""+funcionario.getId());
				form.setNomeFuncionarioAutorizador(funcionario.getNome());
			}else{
				sessao.removeAttribute("funcionarioAutorizadorEncontrado");
				form.setIdFuncionarioAutorizador("");
				form.setNomeFuncionarioAutorizador("Funcionário inexistente");
			}
		} else if(httpServletRequest.getParameter("reloadPage")== null){

			limparCampos(httpServletRequest, form, sessao);
			
			
			if (idImovel != null && !idImovel.equals("")){
				
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(idImovel));
				form.setNomeClienteUsuarioImovelSelecionado(cliente.getNome());
				
				//CONTA
				ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = apresentarContasImovel(new Integer(idImovel),form.getIdRAConsulta());
				sessao.setAttribute("colecaoConta", obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel());
				
				//PAGAMENTOS EM DUPLICIDADE
				//Collection colecaoPagamento = fachada.pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(new Integer(form.getIdRAConsulta()));
				Collection<PagamentoRADevolucaoValoresHelper> colecaoPagamentoValoresPagos = 
						fachada.pesquisaDadosRegistroAtendimentoDevolucaoValores(
							new Integer(form.getIdRAConsulta()), new Short(form.getCodigoTipoDevolucao()),
							new Integer(form.getIdImovelSelecionado()));
				sessao.setAttribute("colecaoPagamentoValoresPagos", colecaoPagamentoValoresPagos);
				
				
				
				httpServletRequest.setAttribute("habilitaIncluirDebito", 1);
				
			}
			
		}else{

			httpServletRequest.setAttribute("habilitarRadioGerar", "2");
			form.setIndicadorGerarCreditoARealizar(null);
//			String[] idsContas = httpServletRequest.getParameterValues("conta");
//			form.setIdsConta(Arrays.toString(idsContas).replace("[","").replace("]",""));
			if (idImovel != null && !idImovel.equals("")){
				
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(idImovel));
				form.setNomeClienteUsuarioImovelSelecionado(cliente.getNome());
				
				//CONTA
				ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = apresentarContasImovel(new Integer(idImovel),form.getIdRAConsulta());
				sessao.setAttribute("colecaoConta", obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel());
			}
			//botão calcular
			calcular(httpServletRequest, sessao, form);
			
		}
		
		 if ( fachada.verificarPermissaoEspecial(PermissaoEspecial.DEVOLUCAO_DE_VALORES_POR_GUIA_DE_DEVOLUCAO, usuario) ) {
			 sessao.setAttribute("permissaoEspecialGuiaDevolucao", true);
		 } else {
			 sessao.removeAttribute("permissaoEspecialGuiaDevolucao");
		 }
		
		return retorno;
	}

	private void limparCampos(HttpServletRequest httpServletRequest,
			FiltrarRegistroAtendimentoDevolucaoValoresActionForm form,
			HttpSession sessao) {

		httpServletRequest.setAttribute("habilitarRadioGerar", "2");
		httpServletRequest.setAttribute("habilitarBotaoTransferir", "2");

		form.setSaldoCreditoATransferir("0,00");
		form.setValorGuiaDevolucao("");
		form.setTipoDocumento("");
		form.setTipoDebito("");
		form.setNomeTipoDebito("");
		form.setIdFuncionarioAnalista("");
		form.setNomeFuncionarioAnalista("");
		form.setIdFuncionarioAutorizador("");
		form.setNomeFuncionarioAutorizador("");
		
		form.setIndicadorGerarCreditoARealizar(null);
		sessao.removeAttribute("colecaoConta");
		//sessao.removeAttribute("colecaoPagamento");
		sessao.removeAttribute("colecaoPagamentoValoresPagos");
		sessao.removeAttribute("colecaoContaASerRetificada");
		sessao.removeAttribute("colecaoCreditoASerTransferido");
		sessao.removeAttribute("colecaoCreditoARealizar");
		sessao.removeAttribute("guiaDevolucaoHelper");
		
//		form.setTotalPagamentoSelecionado("0");
		httpServletRequest.removeAttribute("habilitaIncluirDebito");
	}

	/**
	 * @param httpServletRequest
	 * @param sessao
	 */
	private void calcular(HttpServletRequest httpServletRequest, 
			HttpSession sessao,
			FiltrarRegistroAtendimentoDevolucaoValoresActionForm form) {
		
		if(httpServletRequest.getParameter("calcular") != null){
			
			BigDecimal valorCreditoSelecionado = ConstantesSistema.VALOR_ZERO;
			BigDecimal valorContaSelecionada = ConstantesSistema.VALOR_ZERO;
			BigDecimal saldoCreditoATransferir = ConstantesSistema.VALOR_ZERO;
			
			int qtdePagamentos = 0;
			int qtdeContas = 0;
			
			String idsPagamentos = httpServletRequest.getParameter("pagamento");
			Object[] pagamentos = this.obterPagamentosSelecionadas(idsPagamentos, sessao);
			Collection<PagamentoRADevolucaoValoresHelper> colecaoPagamentos = null;
			if(pagamentos != null){
		    	colecaoPagamentos = (Collection<PagamentoRADevolucaoValoresHelper>)pagamentos[0];
		    	valorCreditoSelecionado = (BigDecimal)pagamentos[1];
				qtdePagamentos = colecaoPagamentos.size();
		    }
			sessao.setAttribute("colecaoPagamentosSelecionados",colecaoPagamentos);
			
			String idsContas = httpServletRequest.getParameter("conta");
			Object[] contas = this.obterContasSelecionadas(idsContas, sessao);
			Collection colecaoContas = null;
			if(contas != null){
		    	colecaoContas = (Collection)contas[0];
		    	valorContaSelecionada = (BigDecimal)contas[1];
		    	qtdeContas = colecaoContas.size();
		    }
			
			saldoCreditoATransferir = valorCreditoSelecionado.subtract(valorContaSelecionada);
			
			if (saldoCreditoATransferir.compareTo(new BigDecimal("0")) >= 0) {
				form.setSaldoCreditoATransferir(Util.formatarMoedaReal(saldoCreditoATransferir));
			} else {
				form.setSaldoCreditoATransferir("0,00");
			}
			
			Iterator<PagamentoRADevolucaoValoresHelper> iteratorPagamentos = colecaoPagamentos.iterator();
			
			CreditosHelper creditoASerTransferido = null;
			Collection colecaoCreditoASerTransferido = new ArrayList();
			CreditosHelper creditoARealizar = null;
			Collection colecaoCreditoARealizar = new ArrayList();
			Collection colecaoContaASerRetificada = new ArrayList();
			GuiaDevolucaoHelper guiaDevolucaoHelper = new GuiaDevolucaoHelper();
			
			///////////////////////////////////////////////////////////////////////////////
			if(qtdeContas == 1 && qtdePagamentos == 1){
				//SITUAÇÃO 1(uma conta para um pagamento)
				
				PagamentoRADevolucaoValoresHelper pagamentoRA = (PagamentoRADevolucaoValoresHelper) Util.retonarObjetoDeColecao(colecaoPagamentos);
				ContaValoresHelper helper = (ContaValoresHelper) Util.retonarObjetoDeColecao(colecaoContas);
				Conta conta = helper.getConta();
				
				if(valorCreditoSelecionado.compareTo(valorContaSelecionada) == 1){
					
					helper.setValorCreditoConta(conta.getValorTotal());
					helper.setValorAtualConta(ConstantesSistema.VALOR_ZERO);
					colecaoContaASerRetificada.add(helper);
					
					//Crédito Realizado
					creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),conta.getValorTotal(),conta.getId(),
						form.getCodigoTipoDevolucao());
					colecaoCreditoASerTransferido.add(creditoASerTransferido);
					
					//Crédito a Realizar
					BigDecimal valorCredito = valorCreditoSelecionado.subtract(conta.getValorTotal());
					creditoARealizar = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),valorCredito,null,
						form.getCodigoTipoDevolucao());
					colecaoCreditoARealizar.add(creditoARealizar);
					
					//Guia de Devolução
					guiaDevolucaoHelper = this.montarObjetoGuiaDevolucaoHelper(valorCredito, guiaDevolucaoHelper);
					
				}else{
					
					helper.setValorCreditoConta(pagamentoRA.getValorDevolucao());
					helper.setValorAtualConta(conta.getValorTotal().subtract(valorCreditoSelecionado));
					colecaoContaASerRetificada.add(helper);
					
					//Crédito Realizado
					creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),pagamentoRA.getValorDevolucao(),conta.getId(),
						form.getCodigoTipoDevolucao());
					colecaoCreditoASerTransferido.add(creditoASerTransferido);
				}
			}
			
			if(qtdeContas > 1 && qtdePagamentos == 1){
				//SITUAÇÃO 2 (mais de uma conta para um pagamento)
				
				PagamentoRADevolucaoValoresHelper pagamentoRA = (PagamentoRADevolucaoValoresHelper) Util.retonarObjetoDeColecao(colecaoPagamentos);
				BigDecimal saldoCredito = valorCreditoSelecionado;
				Iterator iteratorContas = colecaoContas.iterator();
				while (iteratorContas.hasNext()) {
					ContaValoresHelper helper = (ContaValoresHelper) iteratorContas.next();
					Conta conta = helper.getConta();
					
					
					if(valorCreditoSelecionado.compareTo(valorContaSelecionada) == 1){
						
						helper.setValorCreditoConta(conta.getValorTotal());
						helper.setValorAtualConta(ConstantesSistema.VALOR_ZERO);
						colecaoContaASerRetificada.add(helper);
						
						//Crédito Realizado
						creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),conta.getValorTotal(),conta.getId(),
							form.getCodigoTipoDevolucao());
						colecaoCreditoASerTransferido.add(creditoASerTransferido);
						
					}else{
						
						
						if(saldoCredito.compareTo(ConstantesSistema.VALOR_ZERO) ==1 &&
								saldoCredito.compareTo(conta.getValorTotalContaBigDecimal()) == 1){
							
							helper.setValorCreditoConta(conta.getValorTotal());
							helper.setValorAtualConta(ConstantesSistema.VALOR_ZERO);
							colecaoContaASerRetificada.add(helper);
							
							//Crédito Realizado
							creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),conta.getValorTotal(),conta.getId(),
								form.getCodigoTipoDevolucao());
							colecaoCreditoASerTransferido.add(creditoASerTransferido);
							
							saldoCredito = saldoCredito.subtract(conta.getValorTotal());
						}else if(saldoCredito.compareTo(ConstantesSistema.VALOR_ZERO) == 1){
							
							helper.setValorCreditoConta(saldoCredito);
							helper.setValorAtualConta(conta.getValorTotal().subtract(saldoCredito));
							colecaoContaASerRetificada.add(helper);
							
							//Crédito Realizado
							creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),saldoCredito,conta.getId(),
								form.getCodigoTipoDevolucao());
							colecaoCreditoASerTransferido.add(creditoASerTransferido);
							saldoCredito = ConstantesSistema.VALOR_ZERO;
						}
						
					}
					
				}
				
				if(valorCreditoSelecionado.compareTo(valorContaSelecionada) == 1){
					//Crédito a Realizar
					BigDecimal valorCredito = valorCreditoSelecionado.subtract(valorContaSelecionada);
					creditoARealizar = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),valorCredito,null,
						form.getCodigoTipoDevolucao());
					colecaoCreditoARealizar.add(creditoARealizar);

					//Guia de Devolução
					guiaDevolucaoHelper = this.montarObjetoGuiaDevolucaoHelper(valorCredito, guiaDevolucaoHelper);
				}
				
			}
			
			if(qtdeContas == 1 && qtdePagamentos > 1){
				//SITUAÇÃO 3 (uma conta para mais de um pagamento)
				
				ContaValoresHelper helper = (ContaValoresHelper) Util.retonarObjetoDeColecao(colecaoContas);
				Conta conta = helper.getConta();
				helper.setValorCreditoConta(ConstantesSistema.VALOR_ZERO);
				
				BigDecimal saldoCredor = valorCreditoSelecionado;
				BigDecimal valorDevolucao = conta.getValorTotalContaBigDecimal();
				
				if(saldoCredor.compareTo(valorContaSelecionada) == 1){
					
					while (iteratorPagamentos.hasNext()) {
						PagamentoRADevolucaoValoresHelper pagamentoRA = (PagamentoRADevolucaoValoresHelper) iteratorPagamentos.next();
						
						if(valorDevolucao.compareTo(pagamentoRA.getValorDevolucao()) >= 0){
							
							helper.setValorCreditoConta(helper.getValorCreditoConta().add(pagamentoRA.getValorDevolucao()));//vldevolucao
							helper.setValorAtualConta(conta.getValorTotal().subtract(helper.getValorCreditoConta()));
							if (iteratorPagamentos.hasNext()){
								colecaoContaASerRetificada.add(helper);
							}
							
							valorDevolucao = valorDevolucao.subtract(pagamentoRA.getValorDevolucao());
							
							//Crédito Realizado
							creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),pagamentoRA.getValorDevolucao(),conta.getId(),
								form.getCodigoTipoDevolucao());
							colecaoCreditoASerTransferido.add(creditoASerTransferido);
							
						}else{
							
							if(valorDevolucao.compareTo(ConstantesSistema.VALOR_ZERO) == 1){
								
								helper.setValorCreditoConta(helper.getValorCreditoConta().add(valorDevolucao));
								helper.setValorAtualConta(conta.getValorTotal().subtract(helper.getValorCreditoConta()));
								if (iteratorPagamentos.hasNext()){
									colecaoContaASerRetificada.add(helper);
								}
								//Crédito Realizado
								creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),valorDevolucao,conta.getId(),
									form.getCodigoTipoDevolucao());
								colecaoCreditoASerTransferido.add(creditoASerTransferido);
								
								//Crédito a Realizar
								BigDecimal valorCredito = pagamentoRA.getValorDevolucao().subtract(valorDevolucao);
								creditoARealizar = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),valorCredito,null,
									form.getCodigoTipoDevolucao());
								colecaoCreditoARealizar.add(creditoARealizar);

								//Guia de Devolução
								guiaDevolucaoHelper = this.montarObjetoGuiaDevolucaoHelper(valorCredito, guiaDevolucaoHelper);
								
								valorDevolucao = ConstantesSistema.VALOR_ZERO;
								
							}else if(valorDevolucao.compareTo(ConstantesSistema.VALOR_ZERO) == 0){

								//Crédito a Realizar
								creditoARealizar = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),pagamentoRA.getValorDevolucao(),null,
									form.getCodigoTipoDevolucao());
								colecaoCreditoARealizar.add(creditoARealizar);

								//Guia de Devolução
								guiaDevolucaoHelper = this.montarObjetoGuiaDevolucaoHelper(pagamentoRA.getValorDevolucao(), guiaDevolucaoHelper);
							}
						}
					}
					
				}else{
					
					while (iteratorPagamentos.hasNext()) {
						PagamentoRADevolucaoValoresHelper pagamentoRA = (PagamentoRADevolucaoValoresHelper) iteratorPagamentos.next();
					
						helper.setValorCreditoConta(helper.getValorCreditoConta().add(pagamentoRA.getValorDevolucao()));
						helper.setValorAtualConta(conta.getValorTotal().subtract(helper.getValorCreditoConta()));
						if (!iteratorPagamentos.hasNext()){
							colecaoContaASerRetificada.add(helper);
						}
						
						//Crédito Realizado
						creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),pagamentoRA.getValorDevolucao(),conta.getId(),
							form.getCodigoTipoDevolucao());
						colecaoCreditoASerTransferido.add(creditoASerTransferido);
						
					}
				}
			}


			if(qtdeContas >= 2 && qtdePagamentos >= 2){
				//a SITUAÇÃO 4 (mais de uma conta para mais de um pagamento)
				
				BigDecimal valorDevolucao = ConstantesSistema.VALOR_ZERO;
				BigDecimal valorAtualConta = ConstantesSistema.VALOR_ZERO;
				Iterator iteratorContas = colecaoContas.iterator();
				ContaValoresHelper helper = null;
				int contadorContas = 0;
				boolean mudouConta = false;
				
				while (iteratorPagamentos.hasNext()) {
					
					PagamentoRADevolucaoValoresHelper pagamentoRA = (PagamentoRADevolucaoValoresHelper) iteratorPagamentos.next();
					
					BigDecimal valorSaldo = pagamentoRA.getValorDevolucao();
					boolean aindaTemConta = false;
					while (iteratorContas.hasNext() && valorSaldo.compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
						
						if (valorAtualConta.compareTo(ConstantesSistema.VALOR_ZERO) == 0){
							
							helper = (ContaValoresHelper) iteratorContas.next();
							contadorContas = contadorContas + 1;
							mudouConta = true;
						}
						Conta conta = helper.getConta();

						
						if(qtdeContas == contadorContas){
							aindaTemConta = false;
						}else{
							aindaTemConta = true;
						}
						
						if(helper.getValorAtualConta() == null){
							helper.setValorAtualConta(conta.getValorTotalContaBigDecimal());
						}
						
						valorDevolucao = helper.getValorAtualConta();
						
						if(valorDevolucao.compareTo(ConstantesSistema.VALOR_ZERO) == 1){
							if(valorSaldo.compareTo(helper.getValorAtualConta()) == 1){
								
								valorDevolucao = valorDevolucao.subtract(helper.getValorAtualConta());
								valorSaldo = valorSaldo.subtract(helper.getValorAtualConta());
								
								helper.setValorCreditoConta(helper.getValorCreditoConta().add(helper.getValorAtualConta()));
								helper.setValorAtualConta(conta.getValorTotalContaBigDecimal().subtract(helper.getValorCreditoConta()));
								
								if (!mudouConta){
									colecaoCreditoASerTransferido.remove(creditoASerTransferido);
								}
								creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),conta.getValorTotalContaBigDecimal(),conta.getId(),
									form.getCodigoTipoDevolucao());
								if (mudouConta){
								colecaoContaASerRetificada.add(helper);
								
								//Crédito Realizado
								
								colecaoCreditoASerTransferido.add(creditoASerTransferido);
								mudouConta = false;
								} else if (!mudouConta){
									
									colecaoCreditoASerTransferido.add(creditoASerTransferido);
								}
								
								valorAtualConta = helper.getValorAtualConta();
								
							}else{
								
								valorDevolucao = valorDevolucao.subtract(valorSaldo);
								
								helper.setValorCreditoConta(helper.getValorCreditoConta().add(valorSaldo));
								helper.setValorAtualConta(helper.getValorAtualConta().subtract(valorSaldo));
								if (!mudouConta){
									colecaoCreditoASerTransferido.remove(creditoASerTransferido);
								}
								creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),valorSaldo,conta.getId(),
									form.getCodigoTipoDevolucao());
								
								if (mudouConta){
									colecaoContaASerRetificada.add(helper);
//									Crédito Realizado
									
									colecaoCreditoASerTransferido.add(creditoASerTransferido);
									mudouConta = false;
								} else if (!mudouConta){
									
									colecaoCreditoASerTransferido.add(creditoASerTransferido);
								}
								valorSaldo = ConstantesSistema.VALOR_ZERO;
								valorAtualConta = helper.getValorAtualConta();
								
							}
						}
						
					}
					
					
					if(!aindaTemConta){
						Iterator iterContaRetificada = colecaoContaASerRetificada.iterator();
						
						while (iterContaRetificada.hasNext() && valorSaldo.compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
							ContaValoresHelper helperCR = (ContaValoresHelper) iterContaRetificada.next();
							Conta conta = helperCR.getConta();

							if(helperCR.getValorAtualConta() == null ){
								helperCR.setValorAtualConta(conta.getValorTotalContaBigDecimal());
							}
							
							valorDevolucao = helperCR.getValorAtualConta();
							
							if(valorDevolucao.compareTo(ConstantesSistema.VALOR_ZERO) == 1){
								if(valorSaldo.compareTo(helperCR.getValorAtualConta()) == 1){
									
									valorDevolucao = valorDevolucao.subtract(helperCR.getValorAtualConta());
									valorSaldo = valorSaldo.subtract(helperCR.getValorAtualConta());
									
									helperCR.setValorCreditoConta(helperCR.getValorCreditoConta().add(helperCR.getValorAtualConta()));
									
									//Crédito Realizado
									creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),helperCR.getValorAtualConta(),conta.getId(),
										form.getCodigoTipoDevolucao());
									colecaoCreditoASerTransferido.add(creditoASerTransferido);
	
									helperCR.setValorAtualConta(conta.getValorTotal().subtract(helperCR.getValorCreditoConta()));
									
								}else{
									
									valorDevolucao = valorDevolucao.subtract(valorSaldo);
									
									helperCR.setValorCreditoConta(helperCR.getValorCreditoConta().add(valorSaldo));
									helperCR.setValorAtualConta(helperCR.getValorAtualConta().subtract(valorSaldo));
									
									//Crédito Realizado
									creditoASerTransferido = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),valorSaldo,conta.getId(),
										form.getCodigoTipoDevolucao());
									colecaoCreditoASerTransferido.add(creditoASerTransferido);
									
									valorSaldo = ConstantesSistema.VALOR_ZERO;
									
								}
							}
							
						}
					}
					
					if(valorSaldo.compareTo(ConstantesSistema.VALOR_ZERO) == 1){
						//Crédito a Realizar
						creditoARealizar = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),valorSaldo,null,
							form.getCodigoTipoDevolucao());
						colecaoCreditoARealizar.add(creditoARealizar);

						//Guia de Devolução
						guiaDevolucaoHelper = this.montarObjetoGuiaDevolucaoHelper(valorSaldo, guiaDevolucaoHelper);
					}
					
				}
					
				
			}
			
			if(qtdeContas == 0 && qtdePagamentos == 1){
				//SITUAÇÃO 5 (um pagamento selecionado sem conta selecionada)
				PagamentoRADevolucaoValoresHelper pagamentoRA = (PagamentoRADevolucaoValoresHelper) Util.retonarObjetoDeColecao(colecaoPagamentos);

				//Crédito a Realizar
				creditoARealizar = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),pagamentoRA.getValorDevolucao(),null,
					form.getCodigoTipoDevolucao());
				colecaoCreditoARealizar.add(creditoARealizar);

				//Guia de Devolução
				guiaDevolucaoHelper = this.montarObjetoGuiaDevolucaoHelper(pagamentoRA.getValorDevolucao(), guiaDevolucaoHelper);
				
			}
			
			if(qtdeContas == 0 && qtdePagamentos > 1){
				//SITUAÇÃO 6 (mais de um pagamento selecionado sem conta selecionada)
				
				while (iteratorPagamentos.hasNext()) {
					PagamentoRADevolucaoValoresHelper pagamentoRA = (PagamentoRADevolucaoValoresHelper) iteratorPagamentos.next();
					
					//Crédito a Realizar
					creditoARealizar = montarObjetoCreditosHelper(pagamentoRA.getAnoMes(),pagamentoRA.getValorDevolucao(),null,
						form.getCodigoTipoDevolucao());
					colecaoCreditoARealizar.add(creditoARealizar);

					//Guia de Devolução
					guiaDevolucaoHelper = this.montarObjetoGuiaDevolucaoHelper(pagamentoRA.getValorDevolucao(), guiaDevolucaoHelper);
					
				}
				
			}
			
			sessao.setAttribute("colecaoContaASerRetificada",colecaoContaASerRetificada);
			sessao.setAttribute("colecaoCreditoASerTransferido",colecaoCreditoASerTransferido);
			sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
			sessao.setAttribute("guiaDevolucaoHelper",guiaDevolucaoHelper);
			
			if (guiaDevolucaoHelper.getValorDevolucao() != null
					&& guiaDevolucaoHelper.getValorDevolucao().compareTo(new BigDecimal("0")) > 0) {
				
				form.setTipoDocumento(
					guiaDevolucaoHelper.getDocumentoTipo().getDescricaoDocumentoTipo());
				form.setValorGuiaDevolucao(
					Util.formatarMoedaReal(guiaDevolucaoHelper.getValorDevolucao()));

				httpServletRequest.setAttribute("habilitarRadioGerar", "1");
				httpServletRequest.setAttribute("habilitarBotaoTransferir", "2");
				
			} else {
				form.setTipoDocumento("");
				form.setTipoDebito("");
				form.setNomeTipoDebito("");
				form.setValorGuiaDevolucao("");
				form.setIdFuncionarioAnalista("");
				form.setIdFuncionarioAnalista("");

				httpServletRequest.setAttribute("habilitarRadioGerar", "2");
				form.setIndicadorGerarCreditoARealizar(null);
				
				if (colecaoContaASerRetificada != null
						&& !colecaoContaASerRetificada.isEmpty()) {
					httpServletRequest.setAttribute("habilitarBotaoTransferir", "1");
				}
			}

			
		}
	}
	
	private Object[] obterContasSelecionadas(String idsContas, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<ContaValoresHelper> colecaoContas = null;
		BigDecimal valorTotalConta = BigDecimal.ZERO;

		if (idsContas != null && !idsContas.equals("")){
			retorno = new Object[6];
			colecaoContas = new ArrayList();
			
			Collection colecaoContasSessao = (Collection) sessao.getAttribute("colecaoConta");
			Iterator itColecaoContasSessao = colecaoContasSessao.iterator();
			ContaValoresHelper contaValoresHelper = null;
			
			String[] idsContasArray = idsContas.split(",");
			
			while (itColecaoContasSessao.hasNext()){
				
				contaValoresHelper = (ContaValoresHelper) itColecaoContasSessao.next();
				contaValoresHelper.setValorCreditoConta(ConstantesSistema.VALOR_ZERO);
				for(int x=0; x<idsContasArray.length; x++){
					
					if (contaValoresHelper.getConta().getId().equals(new Integer(idsContasArray[x]))){
						colecaoContas.add(contaValoresHelper);
						valorTotalConta = valorTotalConta.add(contaValoresHelper.getValorTotalConta());
						
					}
				}
			}
			
			
			retorno[0] = colecaoContas;
			retorno[1] = valorTotalConta;

		}

		return retorno;
	}
	
	
	private Object[] obterPagamentosSelecionadas(String idsPagamentos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<PagamentoRADevolucaoValoresHelper> colecaoPagamentos = null;
		BigDecimal valorTotalPagamentos = BigDecimal.ZERO;
		
		if (idsPagamentos != null && !idsPagamentos.equals("")){
			retorno = new Object[2];
			colecaoPagamentos = new ArrayList<PagamentoRADevolucaoValoresHelper>();
			
			Collection<PagamentoRADevolucaoValoresHelper> colecaoPagamentosSessao = 
					(Collection<PagamentoRADevolucaoValoresHelper>) sessao.getAttribute("colecaoPagamentoValoresPagos");
			Iterator<PagamentoRADevolucaoValoresHelper> itColecaoPagamentosSessao = 
					colecaoPagamentosSessao.iterator();
			PagamentoRADevolucaoValoresHelper pagamentoRA = null;
			
			String[] idsDebitosArray = idsPagamentos.split(",");
			
			while (itColecaoPagamentosSessao.hasNext()){
				
				pagamentoRA = (PagamentoRADevolucaoValoresHelper) itColecaoPagamentosSessao.next();
				
				for(int x=0; x<idsDebitosArray.length; x++){
					
					if (pagamentoRA.getIdPagamento().equals(new Integer(idsDebitosArray[x]))){
						colecaoPagamentos.add(pagamentoRA);
						valorTotalPagamentos = valorTotalPagamentos.add(pagamentoRA.getValorDevolucao());
						
					}
				}
			}
			
			Collections.sort((List<PagamentoRADevolucaoValoresHelper>) colecaoPagamentos, new Comparator() {
    			public int compare(Object a, Object b) {
    			BigDecimal valor1 = ((PagamentoRADevolucaoValoresHelper)a).getValorDevolucao();
    			BigDecimal valor2 = ((PagamentoRADevolucaoValoresHelper)b).getValorDevolucao();
    			
    			return valor2.compareTo(valor1);
    			
    			}
    		});
			
			
			retorno[0] = colecaoPagamentos;
			retorno[1] = valorTotalPagamentos;
		}else{
			throw new ActionServletException("atencao.nenhum.pagamento.selecionado");
		}

		return retorno;
	}
	
	/**
	 * retira contas parceladas ou em revisão da coleção de contas em débito
	 * 
	 * @author Vivianne Sousa
	 * @created 16/03/2011
	 */
	private ObterDebitoImovelOuClienteHelper apresentarContasImovel(Integer idImovel, String numeroRA) {

		Date dataVencimentoInicial = Util.criarData(1, 1, 0001);
		Date dataVencimentoFinal = Util.criarData(31, 12, 9999);

		// [UC0067] Obter Débito do Imóvel ou Cliente
		ObterDebitoImovelOuClienteHelper imovelDebitoCredito = Fachada.getInstancia()
				.obterDebitoImovelOuCliente(1, // indicadorDebito
						idImovel.toString(), // idImovel
						null, // codigoCliente
						null, // clienteRelacaoTipo
						"000101", // anoMesInicialReferenciaDebito
						"999912", // anoMesFinalReferenciaDebito
						dataVencimentoInicial, // anoMesInicialVencimentoDebito
						dataVencimentoFinal, // anoMesFinalVencimentoDebito
						1, // indicadorPagamento
						1, // indicadorConta
						2, // indicadorDebitoACobrar
						2, // indicadorCreditoARealizar
						2, // indicadorNotasPromissorias
						2, // indicadorGuiasPagamento
						2, // indicadorCalcularAcrescimoImpontualidade
						true);// indicadorContas

		// CONTA
		if (imovelDebitoCredito.getColecaoContasValoresImovel() != null
				&& !imovelDebitoCredito.getColecaoContasValoresImovel().isEmpty()) {

			Collection<ContaValoresHelper> colecaoContaValoresNaoParcelamento = new ArrayList();

			// Selecionar apenas as contas que não estejam parceladas
			Iterator itColecaoConta = imovelDebitoCredito.getColecaoContasValoresImovel().iterator();

			while (itColecaoConta.hasNext()) {

				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) itColecaoConta.next();
				contaValoresHelper.setValorCreditoConta(ConstantesSistema.VALOR_ZERO);
				
				//Caso a conta não esteja em revisão ou esteja em revisão pelo motivo 
				//"SOLICITACAO DE DEVOLUCAO" e o registro de atendimento esteja associado a ele
				if(contaValoresHelper.getConta().getContaMotivoRevisao() == null || 
					(
						contaValoresHelper.getConta().getContaMotivoRevisao().getId().compareTo(ContaMotivoRevisao.SOLICITACAO_DE_DEVOLUCAO) == 0) &&
						this.getFachada().verificarContaAcossiadaRA(contaValoresHelper.getConta().getId(),new Integer(numeroRA))
					)

				colecaoContaValoresNaoParcelamento.add(contaValoresHelper);
				
			}

			imovelDebitoCredito.setColecaoContasValoresImovel(colecaoContaValoresNaoParcelamento);

		}

		return imovelDebitoCredito;

	}
	
	private CreditosHelper montarObjetoCreditosHelper(Integer referenciaCredito, 
			BigDecimal valorCredito,Integer idContaCreditorealizado,
			String codigoTipoDevolucao){
		
		CreditosHelper helper = new CreditosHelper();
		helper.setReferenciaCredito(referenciaCredito);
		helper.setValorCredito(valorCredito);
		if(idContaCreditorealizado != null){
			helper.setIdContaCreditorealizado(idContaCreditorealizado);
		}
		
		//CreditoOrigem
		FiltroCreditoOrigem filtroCreditoOrigem = new FiltroCreditoOrigem();
		 if(codigoTipoDevolucao.trim().equals(ConstantesSistema.DEVOLUCAO_PAGAMENTO_EM_DUPLICIDADE)
				 || codigoTipoDevolucao.trim().equals(ConstantesSistema.DEVOLUCAO_PAGAMENTO_VALOR_A_MAIOR)) {
	        filtroCreditoOrigem.adicionarParametro(new ParametroSimples(FiltroCreditoOrigem.ID,
        		CreditoOrigem.CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO));
        } else {
	        filtroCreditoOrigem.adicionarParametro(new ParametroSimples(FiltroCreditoOrigem.ID,
	        	CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE));
        }
        
        filtroCreditoOrigem.adicionarParametro(new ParametroSimples(
        	FiltroCreditoOrigem.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
    	Collection<CreditoOrigem> colecaoCreditoOrigem = Fachada.getInstancia().pesquisar(filtroCreditoOrigem,
    		CreditoOrigem.class.getName());
    	if (colecaoCreditoOrigem == null || colecaoCreditoOrigem.isEmpty()){
    		throw new ActionServletException(
                "atencao.pesquisa.nenhum_registro_tabela", null,
                "CREDITO_ORIGEM");
    	}
		CreditoOrigem objCreditoOrigem = (CreditoOrigem) Util.retonarObjetoDeColecao(colecaoCreditoOrigem);
		helper.setOrigemCredito(objCreditoOrigem);
		
		//CreditoTipo
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
        if(codigoTipoDevolucao.trim().equals(ConstantesSistema.DEVOLUCAO_PAGAMENTO_EM_DUPLICIDADE)) {
	        filtroCreditoTipo.adicionarParametro(new ParametroSimples(
	        	FiltroCreditoTipo.ID, CreditoTipo.DEVOLUCAO_PAGAMENTOS_DUPLICIDADE));
        } else {
	        filtroCreditoTipo.adicionarParametro(new ParametroSimples(
	        	FiltroCreditoTipo.ID, CreditoTipo.DEVOLUCAO_OUTROS_VALORES));
        }
        
        filtroCreditoTipo.adicionarParametro(new ParametroSimples(
        	FiltroCreditoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroCreditoTipo.adicionarCaminhoParaCarregamentoEntidade(
        	FiltroCreditoTipo.LANCAMENTO_ITEM_CONTABIL);
    	Collection<CreditoTipo> colecaoCreditoTipo = 
    			Fachada.getInstancia().pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
    	if (colecaoCreditoTipo == null || colecaoCreditoTipo.isEmpty()){
	    	throw new ActionServletException(
	                "atencao.pesquisa.nenhum_registro_tabela", null,
	                "CREDITO_TIPO");
    	}
		CreditoTipo objCreditoTipo = (CreditoTipo) Util.retonarObjetoDeColecao(colecaoCreditoTipo);
		helper.setTipoCredito(objCreditoTipo);
	
		return helper;
	}
	
	private GuiaDevolucaoHelper montarObjetoGuiaDevolucaoHelper(
			BigDecimal valorDevolucao, GuiaDevolucaoHelper helper){
		
		if (helper.getValorDevolucao() != null
				&& helper.getValorDevolucao().compareTo(new BigDecimal("0")) > 0) {
			
			helper.setValorDevolucao(
				helper.getValorDevolucao().add(valorDevolucao));
			
		} else {
			
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.adicionarParametro(
				new ParametroSimples(FiltroDocumentoTipo.ID, DocumentoTipo.DEVOLUCAO_VALOR));
			Collection<DocumentoTipo> colecaoDocumentoTipo = 
					Fachada.getInstancia().pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
			DocumentoTipo documentoTipo = (DocumentoTipo) Util.retonarObjetoDeColecao(colecaoDocumentoTipo);
			
			helper.setDocumentoTipo(documentoTipo);
			helper.setValorDevolucao(valorDevolucao);
			
		}
		
	
		return helper;
	}

	private Funcionario pesquisarFuncionario(String idFuncionario) {
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		Funcionario funcionario = null;

		FiltroFuncionario filtro = new FiltroFuncionario();
		filtro.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, new Integer(idFuncionario)));

		Collection<Funcionario> colecao = 
			Fachada.getInstancia().pesquisar(filtro, Funcionario.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecao);
		}
		
		return funcionario;
	}
}
