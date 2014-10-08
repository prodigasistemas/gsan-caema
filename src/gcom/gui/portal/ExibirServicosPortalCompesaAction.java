package gcom.gui.portal;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.ComparatorPagamentos;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.arrecadacao.pagamento.TipoPagamentoPortalHelper;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteVirtual;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteVirtual;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaGeral;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir os Serviços do Portal Da Compesa
 * 
 * @author Diogo Peixoto
 * @date 13/05/2011
 */
public class ExibirServicosPortalCompesaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String retorno = "exibirServicosPortalCompesaAction";

		ExibirServicosPortalCompesaActionForm form = (ExibirServicosPortalCompesaActionForm) actionForm;

		String method = httpServletRequest.getParameter("method");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		if ( httpServletRequest.getParameter("cadastroVirtual") != null && httpServletRequest.getParameter("cadastroVirtual").equals("sucesso") ) {
			
			String matricula = (String) httpServletRequest.getParameter("matricula");
			FiltroClienteVirtual filtroClienteVirtual = new FiltroClienteVirtual();
			filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.ID_IMOVEL, matricula));
			
			Collection<ClienteVirtual> colecaoClienteVirtual = this.getFachada().pesquisar(filtroClienteVirtual, ClienteVirtual.class.getName());
			
			if ( colecaoClienteVirtual != null && !colecaoClienteVirtual.isEmpty() ) {
				
				for (ClienteVirtual clienteVirtual : colecaoClienteVirtual) {
					
					String nomeUsuario = clienteVirtual.getNome();
					form.setNomeUsuario(nomeUsuario);
					sessao.setAttribute("nomeUsuario", nomeUsuario);
					form.setMatricula(matricula);
					sessao.setAttribute("matricula", Integer.parseInt(matricula));
					String cpfCnpj = "";
					if ( clienteVirtual.getCpf() != null && !clienteVirtual.getCpf().equals("") ) {
						cpfCnpj = clienteVirtual.getCpf();
					} else {
						cpfCnpj = clienteVirtual.getCnpj();
					}
					
					sessao.setAttribute("cpfCnpj", cpfCnpj);
					sessao.setAttribute("imovelCadastroVirtual", true);

//					httpServletRequest.removeAttribute("solicitarCpfCnpj");
					httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
//					httpServletRequest.removeAttribute("cpfCnpjInvalido");

					retorno = "servicosPortalCompesaAction";

					break;
				}

			}
			
			
			return actionMapping.findForward(retorno);
		}
		

		if (method != null) {
			if (method.equalsIgnoreCase("servicos")) {

//				String cpfDigitado = httpServletRequest.getParameter("vcpf");
				
				try {
					Integer matricula = Integer.valueOf(form.getMatricula());

					Integer matriculaExistente = this.getFachada().verificarExistenciaImovel(matricula);

					if (matriculaExistente == 1){// || this.getFachada().verificarExistenciaClienteVirtual(matricula)) {

						sessao.setAttribute("matricula", matricula);
						sessao.removeAttribute("cpfCnpj");
						sessao.removeAttribute("imovelCadastroVirtual");
						
						httpServletRequest.removeAttribute("solicitarCpfCnpj");
						httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
						httpServletRequest.removeAttribute("cpfCnpjInvalido");

						retorno = "servicosPortalCompesaAction";
												
						//Aqui estava o código de validação do CPF
						
					} else {
						
						httpServletRequest.setAttribute("nomeCampo", "matricula");
						httpServletRequest.setAttribute("imovelInvalido", true);
					}

				} catch (NumberFormatException e) {
					httpServletRequest.setAttribute("nomeCampo", "matricula");
					httpServletRequest.setAttribute("imovelInvalido", true);
				}
			} else if (method.equalsIgnoreCase("declaracaoAnual")) {
				retorno = "servicosPortalCompesaAction";
				if(validarCPF(form, httpServletRequest)){
						Integer matricula = (Integer) sessao.getAttribute("matricula");
						this.pesquisarAnosImovel(matricula, httpServletRequest, form);
						httpServletRequest.setAttribute("voltarServicos", true);
						retorno = "servicoDeclaracaoAnual";
						String ip = httpServletRequest.getRemoteAddr(); 
						Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.DECLARACAO_ANUAL_QUITACAO_DEBITO, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO); 
				}else{
					if(httpServletRequest.getAttribute("solicitarCadastroVirtual") != null){
						httpServletRequest.setAttribute("solicitarCadastroVirtual", true);
						retorno = "exibirServicosPortalCompesaAction";
					}else{
						httpServletRequest.setAttribute("metodo", "declaracaoAnual");
					}
				}
				
			} else if (method.equalsIgnoreCase("consultarPagamentos")) {
				Integer matricula = (Integer) sessao.getAttribute("matricula");
				this.pesquisarPagamentosImovel(matricula, httpServletRequest, form);
				httpServletRequest.setAttribute("voltarServicos", true);
				retorno = "servicoConsultarPagamento";
				String ip = httpServletRequest.getRemoteAddr();
				Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CONSULTAR_PAGAMENTOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
			
			}else if (method.equalsIgnoreCase("voltarServico")) {
				retorno = "servicosPortalCompesaAction";
			} else if (method.equalsIgnoreCase("contratoAdesao")) {
				
				String ip = httpServletRequest.getRemoteAddr(); 
				Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CONTRATO_ADESAO_TACITA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
//			   Matrícula do imóvel informado não tem situação da ligação de água/esgoto ativa, 
//			    		 condição não permite geração de contrato de adesão  tácita.
				Integer matricula = (Integer) sessao.getAttribute("matricula");
				Imovel imovel = this.getFachada().pesquisarImovel(matricula);
				if ( (imovel.getLigacaoAguaSituacao().getId().toString().equals("1") || 
								imovel.getLigacaoAguaSituacao().getId().toString().equals("2") || 
								imovel.getLigacaoAguaSituacao().getId().toString().equals("6") ) &&
								( imovel.getLigacaoEsgotoSituacao().getId().toString().equals("1")  ||
										 imovel.getLigacaoEsgotoSituacao().getId().toString().equals("2") )  ){
					httpServletRequest.setAttribute("ligacaoAguaEsgotoInativa", true);	
				} else {
					httpServletRequest.setAttribute("ligacaoAguaEsgotoAtiva", true);
				}
				httpServletRequest.setAttribute("voltarServicos", true);
				sessao.setAttribute("lojaVirtual", "S");
				retorno = "contratoAdesao";
				
			}else if(method.equalsIgnoreCase("vencimentoAlternativo")){
				retorno = "servicosPortalCompesaAction";
				if(validarCPF(form, httpServletRequest)){
					retorno = "vencimentoAlternativoAction";
				}else{
					if(httpServletRequest.getAttribute("solicitarCadastroVirtual") != null){
						httpServletRequest.setAttribute("solicitarCadastroVirtual", true);
						retorno = "exibirServicosPortalCompesaAction";
					}else{
						httpServletRequest.setAttribute("metodo", "vencimentoAlternativo");
					}
				}
			}
		} 
		else {
			form.setCpfCnpjSolicitante(null);
			sessao.removeAttribute("cpfCnpj");
		}

		if (httpServletRequest.getAttribute("imovelSemQuitacaoAnual") != null) {
			retorno = "servicosPortalCompesaAction";
			httpServletRequest.removeAttribute("voltarServicos");
			httpServletRequest.removeAttribute("exibirDeclaracaoAnual");
		}
		
		if(httpServletRequest.getAttribute("imovelSemPagamento")!= null){
			retorno = "servicosPortalCompesaAction";
			httpServletRequest.removeAttribute("voltarServicos");
		}

		return actionMapping.findForward(retorno);
	}
	
	private boolean validarCPF(ExibirServicosPortalCompesaActionForm form, HttpServletRequest httpServletRequest){
			
			HttpSession sessao = httpServletRequest.getSession(false);
			Integer matricula = null;
			boolean retorno = false;
			
			if(form.getMatricula() != null){
				matricula = Integer.parseInt(form.getMatricula());
			}
			
			// Caso o usuário já tenha informado o CPF / CNPJ
			if (Util.verificarNaoVazio(form.getCpfCnpjSolicitante())) {
				boolean isValidCpfOrCnpj = false;
				if (form.getCpfCnpjSolicitante().length() == 11) {
					isValidCpfOrCnpj = Util.validacaoCPF(form.getCpfCnpjSolicitante());
				} else if (form.getCpfCnpjSolicitante().length() > 11) {
					isValidCpfOrCnpj = Util.validacaoCNPJ(form.getCpfCnpjSolicitante());
				}
	
				if (isValidCpfOrCnpj) {
					int quantidadeRejeitado = 0;
					
					Collection<ClienteVirtual> colecaoClientes = this.getFachada().pesquisarSituacaoClienteCadastradoPortal(matricula);
					
					if(!Util.isVazioOrNulo(colecaoClientes)){
						for(ClienteVirtual clienteSituacao : colecaoClientes){
							if(String.valueOf(clienteSituacao.getCodigoSituacao()).equals(ConstantesSistema.CODIGO_SITUACAO_CADASTRO_REJEITADO)){
								quantidadeRejeitado++;
							}else{
								break;
							}
						}
					}
					
					if(!Util.isVazioOrNulo(colecaoClientes)){
						
						for (ClienteVirtual clienteVirtual : colecaoClientes) {
							
							String nomeUsuario = clienteVirtual.getNome();
							form.setNomeUsuario(nomeUsuario);
							sessao.setAttribute("nomeUsuario", nomeUsuario);
							String cpfCnpj = "";
							if ( clienteVirtual.getCpf() != null && !clienteVirtual.getCpf().equals("") ) {
								cpfCnpj = clienteVirtual.getCpf();
							} else {
								cpfCnpj = clienteVirtual.getCnpj();
							}
							
							if(!form.getCpfCnpjSolicitante().equals(clienteVirtual.getCpf()) && 
									!form.getCpfCnpjSolicitante().equals(clienteVirtual.getCnpj())){
								
								httpServletRequest.setAttribute("solicitarCpfCnpj", true);
								httpServletRequest.setAttribute("cpfCnpjNaoCadastrado", true);
								httpServletRequest.removeAttribute("cpfCnpjInvalido");
								
								break;
							}
							
							if(String.valueOf(clienteVirtual.getCodigoSituacao()).equals(ConstantesSistema.CODIGO_SITUACAO_CADASTRO_ATUALIZADO) ||
								String.valueOf(clienteVirtual.getCodigoSituacao()).equals(ConstantesSistema.CODIGO_SITUACAO_CADASTRO_PENDENTE) ||
								String.valueOf(clienteVirtual.getCodigoSituacao()).equals(ConstantesSistema.CODIGO_SITUACAO_REMOVIDO_DE_EM_ANALISE)){
								
								sessao.setAttribute("cpfCnpj", cpfCnpj);
								sessao.setAttribute("imovelCadastroVirtual", true);
								
								httpServletRequest.removeAttribute("solicitarCpfCnpj");
								httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
								httpServletRequest.removeAttribute("cpfCnpjInvalido");
								httpServletRequest.removeAttribute("cadastroEmAnalise");
								httpServletRequest.removeAttribute("cadastroRejeitado");
	
								retorno = true;
	
								break;
							}else if(String.valueOf(clienteVirtual.getCodigoSituacao()).equals(ConstantesSistema.CODIGO_SITUACAO_EM_ANALISE)){
								httpServletRequest.removeAttribute("solicitarCpfCnpj");
								httpServletRequest.setAttribute("cadastroEmAnalise", true);
								
								break;
							}else{
								if(quantidadeRejeitado >= 4){
									httpServletRequest.removeAttribute("solicitarCpfCnpj");
									httpServletRequest.setAttribute("cadastroRejeitado", true);
									
									break;
								}else{
									sessao.setAttribute("matricula", matricula);
									form.setMatricula(matricula.toString());
									httpServletRequest.setAttribute("solicitarCadastroVirtual", true);
									sessao.setAttribute("solicitarCadastroVirtual", true);
									
									break;
								}
							}
						}
						
					}else {
					
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, matricula));
						filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
						
						Collection<ClienteImovel> colecaoClienteImovel = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
	
						for (ClienteImovel clienteImovel : colecaoClienteImovel) {
	
							if (form.getCpfCnpjSolicitante().equals(clienteImovel.getCliente().getCpf())
									|| form.getCpfCnpjSolicitante().equals(clienteImovel.getCliente().getCnpj())) {
								// Pesquisa o nome do usuário e coloca
								// na sessão
								String nomeUsuario = this.getFachada().consultarClienteUsuarioImovel(matricula);
								form.setNomeUsuario(nomeUsuario);
								sessao.setAttribute("nomeUsuario", nomeUsuario);
								sessao.setAttribute("cpfCnpj", form.getCpfCnpjSolicitante());
	
								httpServletRequest.removeAttribute("solicitarCpfCnpj");
								httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
								httpServletRequest.removeAttribute("cpfCnpjInvalido");
	
								retorno = true;
	
								break;
							} else {
								httpServletRequest.setAttribute("solicitarCpfCnpj", true);
								httpServletRequest.setAttribute("cpfCnpjNaoCadastrado", true);
								httpServletRequest.removeAttribute("cpfCnpjInvalido");
								httpServletRequest.setAttribute("solicitarCadastroVirtual", true);
								sessao.setAttribute("solicitarCadastroVirtual", true);
							}
						}
					}
				} 
				else {
					httpServletRequest.setAttribute("solicitarCpfCnpj", true);
					httpServletRequest.setAttribute("cpfCnpjInvalido", true);
					httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
				}
			} 
			else {
				
				if ( !this.getFachada().verificarExistenciaCpfCnpj(matricula) && !this.getFachada().verificarExistenciaClienteVirtual(matricula) ) {
					//Caso o imovel nao possua CPF/CNPJ
					sessao.setAttribute("matricula", matricula);
					form.setMatricula(matricula.toString());
					httpServletRequest.setAttribute("solicitarCadastroVirtual", true);
					sessao.setAttribute("solicitarCadastroVirtual", true);
				} else {
					// Caso o usuário ainda não tenha informado o CPF / CNPJ
					httpServletRequest.setAttribute("solicitarCpfCnpj", true);	
				}
			}
			
			return retorno;

	}
	
//	private boolean validarCPFInformado(ExibirServicosPortalCompesaActionForm form, HttpServletRequest httpServletRequest){
//		boolean retorno = false;
//		
//		// Caso o usuário já tenha informado o CPF / CNPJ
//		if (Util.verificarNaoVazio(form.getCpfCnpjSolicitante())) {
//			boolean isValidCpfOrCnpj = false;
//			if (form.getCpfCnpjSolicitante().length() == 11) {
//				isValidCpfOrCnpj = Util.validacaoCPF(form.getCpfCnpjSolicitante());
//			} else if (form.getCpfCnpjSolicitante().length() > 11) {
//				isValidCpfOrCnpj = Util.validacaoCNPJ(form.getCpfCnpjSolicitante());
//			}
//
//	sair: if (isValidCpfOrCnpj) {
//				Collection<ClienteVirtual> colecaoClientes = this.getFachada().pesquisarSituacaoClienteCadastradoPortal(Integer.valueOf(form.getMatricula()));
//				
//				int quantidadeRejeitado = 0;
//				
//				if(!Util.isVazioOrNulo(colecaoClientes)){
//					for(ClienteVirtual clienteSituacao : colecaoClientes){
//						if(String.valueOf(clienteSituacao.getCodigoSituacao()).equals(ConstantesSistema.CODIGO_SITUACAO_CADASTRO_REJEITADO)){
//							quantidadeRejeitado++;
//						}else{
//							break;
//						}
//					}
//				}
//				
//				for (ClienteVirtual clienteVirtual : colecaoClientes) {
//					if(form.getCpfCnpjSolicitante().equals(clienteVirtual.getCpf()) || form.getCpfCnpjSolicitante().equals(clienteVirtual.getCnpj())){					
//						retorno = true;
//						form.setCpfCnpjSolicitante("");
//						break sair;
//					}
//				}
//				
//				httpServletRequest.setAttribute("solicitarCpfCnpj", true);
//				httpServletRequest.setAttribute("cpfCnpjNaoCadastrado", true);
//				httpServletRequest.removeAttribute("cpfCnpjInvalido");
//			}else{
//				httpServletRequest.setAttribute("solicitarCpfCnpj", true);
//				httpServletRequest.setAttribute("cpfCnpjInvalido", true);
//				httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
//			}
//		}else{
//			// Caso o usuário ainda não tenha informado o CPF / CNPJ
//			httpServletRequest.setAttribute("solicitarCpfCnpj", true);
//		}
//		
//		return retorno;
//	}

	/**
	 * @author Davi Menezes
	 * @date 15/03/2012
	 * @param idImovel
	 * @param httpServletRequest
	 * @param form
	 */
	private void pesquisarPagamentosImovel(Integer idImovel, HttpServletRequest httpServletRequest,
			ExibirServicosPortalCompesaActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession session = httpServletRequest.getSession();
		
		Collection<Pagamento> colecaoImoveisPagamentos = fachada.pesquisarPagamentoImovel(String.valueOf(idImovel).trim(), null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		Collection<PagamentoHistorico> colecaoImoveisPagamentosHistoricos = fachada.pesquisarPagamentoHistoricoImovel(String.valueOf(idImovel).trim(), 
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null); 
		
		Collection<TipoPagamentoPortalHelper> colecaoPagamentosConta = new ArrayList<TipoPagamentoPortalHelper>();
		Collection<TipoPagamentoPortalHelper> colecaoPagamentosGuiaPagamento = new ArrayList<TipoPagamentoPortalHelper>();
		Collection<TipoPagamentoPortalHelper> colecaoPagamentosDebitoACobrar = new ArrayList<TipoPagamentoPortalHelper>();
		
		TipoPagamentoPortalHelper tipoPagamentoHelper = null;
		
		if(!Util.isVazioOrNulo(colecaoImoveisPagamentos)){
			Iterator iterator = colecaoImoveisPagamentos.iterator();
			
			while(iterator.hasNext()){
				Pagamento pagamento = (Pagamento) iterator.next();
				
				tipoPagamentoHelper = new TipoPagamentoPortalHelper();
				tipoPagamentoHelper.setTipoPagamento("pagamento");
				tipoPagamentoHelper.setPagamento(pagamento);
				tipoPagamentoHelper.setPagamentoHistorico(null);
				
				if(pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)){
					colecaoPagamentosDebitoACobrar.add(tipoPagamentoHelper);
				}else if(pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
					colecaoPagamentosGuiaPagamento.add(tipoPagamentoHelper);
				}else if(pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)){
					if(pagamento.getContaGeral() != null){ 
						if(pagamento.getContaGeral().getIndicadorHistorico() == ContaGeral.INDICADOR_HISTORICO){
							tipoPagamentoHelper.setReferenciaConta(pagamento.getContaGeral().getContaHistorico().getFormatarAnoMesParaMesAno());
						}else{
							tipoPagamentoHelper.setReferenciaConta(pagamento.getContaGeral().getConta().getFormatarAnoMesParaMesAno());
						}
					}else{
						tipoPagamentoHelper.setReferenciaConta(pagamento.getFormatarAnoMesPagamentoParaMesAno());
					}
					colecaoPagamentosConta.add(tipoPagamentoHelper);
				}
			}
		}
		
		if(!Util.isVazioOrNulo(colecaoImoveisPagamentosHistoricos)){
			Iterator iterator = colecaoImoveisPagamentosHistoricos.iterator();
			
			while(iterator.hasNext()){
				PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) iterator.next();
				
				if (pagamentoHistorico.getAvisoBancario() == null) {
					AvisoBancario avisoBancario = new AvisoBancario();
					Arrecadador arrecadador = new Arrecadador();
					Cliente cliente = new Cliente();
					
					String nomeCliente = fachada.pesquisarNomeAgenteArrecadador(pagamentoHistorico.getId());
					
					if (nomeCliente != null){
						
						cliente.setNome(nomeCliente);
						arrecadador.setCliente(cliente);
						avisoBancario.setArrecadador(arrecadador);
						pagamentoHistorico.setAvisoBancario(avisoBancario);
					}
				}
				
				tipoPagamentoHelper = new TipoPagamentoPortalHelper();
				tipoPagamentoHelper.setTipoPagamento("pagamentoHistorico");
				tipoPagamentoHelper.setPagamentoHistorico(pagamentoHistorico);
				tipoPagamentoHelper.setPagamento(null);
				
				if(pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)){
					colecaoPagamentosDebitoACobrar.add(tipoPagamentoHelper);
				}else if(pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
					colecaoPagamentosGuiaPagamento.add(tipoPagamentoHelper);
				}else if(pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)){
					if(pagamentoHistorico.getContaGeral() != null){
						if(pagamentoHistorico.getContaGeral().getIndicadorHistorico() == ContaGeral.INDICADOR_HISTORICO){
							tipoPagamentoHelper.setReferenciaConta(pagamentoHistorico.getContaGeral().getContaHistorico().getFormatarAnoMesParaMesAno());
						}else{
							tipoPagamentoHelper.setReferenciaConta(pagamentoHistorico.getContaGeral().getConta().getFormatarAnoMesParaMesAno());
						}
					}else{
						tipoPagamentoHelper.setReferenciaConta(pagamentoHistorico.getFormatarAnoMesReferenciaPagamento());
					}
					colecaoPagamentosConta.add(tipoPagamentoHelper);
				}
			}
		}
		
		if(Util.isVazioOrNulo(colecaoPagamentosConta) && Util.isVazioOrNulo(colecaoPagamentosDebitoACobrar) 
				&& Util.isVazioOrNulo(colecaoPagamentosGuiaPagamento)){
			httpServletRequest.setAttribute("imovelSemPagamento", true);
		}else{
			ArrayList<TipoPagamentoPortalHelper> array = new ArrayList<TipoPagamentoPortalHelper>(colecaoPagamentosConta);
			Collections.sort(array, new ComparatorPagamentos());
			colecaoPagamentosConta = new ArrayList<TipoPagamentoPortalHelper>(array);
			
			httpServletRequest.setAttribute("colecaoPagamentosConta", colecaoPagamentosConta);
			httpServletRequest.setAttribute("colecaoPagamentosDebito", colecaoPagamentosDebitoACobrar);
			httpServletRequest.setAttribute("colecaoPagamentosGuia", colecaoPagamentosGuiaPagamento);
			
			//Seta as coleções para o relatório
			httpServletRequest.getSession(false).setAttribute("colecaoPagamentosConta", colecaoPagamentosConta);
			httpServletRequest.getSession(false).setAttribute("colecaoPagamentosDebito", colecaoPagamentosDebitoACobrar);
			httpServletRequest.getSession(false).setAttribute("colecaoPagamentosGuia", colecaoPagamentosGuiaPagamento);
		}
	}

	/**
	 * @author Magno Gouveia
	 * @date 17/05/2011
	 * @param idImovel,
	 *            httpServletRequest
	 */
	private void pesquisarAnosImovel(Integer idImovel, HttpServletRequest httpServletRequest,
			ExibirServicosPortalCompesaActionForm form) {

		Collection<Object> colecaoAnosImovel = this.getFachada().pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(String.valueOf(idImovel));
		if (colecaoAnosImovel == null || colecaoAnosImovel.isEmpty()) {
			httpServletRequest.setAttribute("imovelSemQuitacaoAnual", true);

		} else {
			httpServletRequest.setAttribute("colecaoAnosImovel", colecaoAnosImovel);

			// Verifica se o usuário digitou o CPF / CNPJ
			httpServletRequest.removeAttribute("exibirDeclaracaoAnual");
			if (form.getCpfCnpjSolicitante() != null) {
				httpServletRequest.setAttribute("exibirDeclaracaoAnual", true);
			} else {
				httpServletRequest.setAttribute("exception", "CPF/CNPJ informado não corresponde com o do cliente");
			}
		}
	}
}