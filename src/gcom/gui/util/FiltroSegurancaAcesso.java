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
package gcom.gui.util;

import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Abrangencia;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



/**
 * Filtro respons�vel pela seguran�a do sistema verificando se o usu�rio que est� 
 * requisitando a funcionalidade ou opera��o, tem acesso ou algum tipo de restri��o 
 *
 * @author Pedro Alexandre
 * @date 20/07/2006
 */
public class FiltroSegurancaAcesso extends HttpServlet implements Filter {
	//Vari�vel que aramzena as configura��es iniciais do filtro
	private FilterConfig filterConfig;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Met�do respons�vel por setaas configura��es inicias necess�rias
	 *
	 * @author Pedro Alexandre
	 * @date 20/07/2006
	 *
	 * @param filterConfig
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	
	/**
	 * Met�do respons�vel por verificar se o usu�rio tem acesso a funcionalidade ou opera��o
	 *
	 * @author Pedro Alexandre
	 * @date 20/07/2006
	 *
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) 
		throws ServletException, IOException {
		
		try {
			
			
			//Faz um cast no request para recuperar a sess�o do usu�rio
			HttpServletRequest requestPagina = ((HttpServletRequest) request);
		
			//Recupera a sess�o do usu�rio logado
			HttpSession sessao = requestPagina.getSession(false);

			//Recupera o usu�rio que est� logado da sess�o
			Usuario usuarioLogado = null;
			
			if(sessao != null){
				usuarioLogado= (Usuario) sessao.getAttribute("usuarioLogado");
			}

			Abrangencia abrangencia = 
        		(Abrangencia)request.getAttribute(Abrangencia.ABRANGENCIA);
            
			//Recupera a cole��o de grupos que o usu�rio logado pertence
            Collection<Grupo> colecaoGruposUsuario = null;
            
            if(sessao != null){
            	colecaoGruposUsuario = (Collection) 
            		sessao.getAttribute("colecaoGruposUsuario");
            }
            	
			
			//Recupera a url do request
			String enderecoURL = requestPagina.getServletPath();
			
			/*
			 * Caso a url seja de um processo de abas 
			 * recupera a url pelo parametro do wizard adicionando o ".do" no final
			 */
			if(enderecoURL.contains("Wizard")){
				enderecoURL = requestPagina.getParameter("action") + ".do";
			}
			
			//Alterado por S�vio Luiz.
			//Data:29/02/2008
			//verifica se o endere�o da funcionalidade existe a palavra tabela auxiliar
			//se existir ent�o acrescenta o parametros ao caminho para 
			//que ele seja �nico.
			if(enderecoURL.contains("TabelaAuxiliar")){
				if(requestPagina.getParameter("tela") != null && 
					!requestPagina.getParameter("tela").equals("")){
					
					enderecoURL = 
						enderecoURL+ "?tela="+requestPagina.getParameter("tela") ;
				}
			}
			
			//Caso seja um acesso ao olap, a url � a mesma para todos
			//por isso pega o id da funcionalidade direto
			Integer idFuncionalidade = null;
			if(enderecoURL.contains("selecaoOLAPAction")){
				String id = requestPagina.getParameter("id");
				if(id != null && !id.equals("")){
					idFuncionalidade = new Integer(requestPagina.getParameter("id"));	
				}
			}

			if(enderecoURL.contains("informarDadosGeracaoRelatorioConsultaAction")){
				String id = requestPagina.getParameter("id");
				if(id != null && !id.equals("")){
					idFuncionalidade = new Integer(requestPagina.getParameter("id"));	
				}
			}
			
			
			Fachada fachada = Fachada.getInstancia();
			
			//Verifica se a url requisitada pelo usu�rio � uma opera��o ou uma funcionalidade
			String tipoURL = fachada.verificarTipoURL(enderecoURL);
			
			setCaminhoMenu(sessao, idFuncionalidade, enderecoURL, tipoURL);
			
			//Caso o usu�rio esteja logado e n�o tenha clicado no link de logoff
			if(usuarioLogado != null  && 
				!enderecoURL.contains("Logoff") && 
				!enderecoURL.contains("Login") && 
				!enderecoURL.contains("telaPrincipal") && 
//				!enderecoURL.contains("cancelar") && 
				!enderecoURL.contains("executarBatch") && 
				!enderecoURL.toLowerCase().contains("pesquisar") && 
				!enderecoURL.toLowerCase().contains("relatorio") && 
				!enderecoURL.contains("telaSucessoBaixarArquivoTxtAction") && 
				!enderecoURL.contains("efetuarAlteracaoSenhaAction") && 
				!enderecoURL.contains("carregarParametrosAction") && 
				!enderecoURL.contains("exibirInformarMelhoriasGcomAction") && 
				!enderecoURL.contains("informarMelhoriasGcomAction") && 
				!enderecoURL.contains("exibirEfetuarAlteracaoSenhaSimplificadaAction") && 
				!enderecoURL.contains("efetuarAlteracaoSenhaSimplificadaAction") && 
				!enderecoURL.contains("exibirConsultarSistemaAlteracaoHistoricoAction") && 
				!enderecoURL.contains("exibirSistemaHistAlteracaoDetalharPopupAction") && 
				!enderecoURL.contains("exibirConsultarDadosPagamentoAction") && 
				!enderecoURL.contains("exibirConsultarSituacaoEspecialFaturamentoPopupAction") && 
				!enderecoURL.contains("exibirConsultarSituacaoEspecialCobrancaPopupAction") && 
				!enderecoURL.contains("processarRequisicaoDipositivoMovelAction") && 
				!enderecoURL.contains("processarRequisicaoTelemetriaAction") &&
				!enderecoURL.contains("processarRequisicaoGisAction") &&
				!enderecoURL.contains("processarCoordenadasGisAction") && 
				!enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelFiscalizacaoAnormalidadeAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelAtualizacaoCadastralAction") &&
				!enderecoURL.contains("processarRequisicaoIntegracaoProgisAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelExecucaoOSAction") &&
				!enderecoURL.contains("exibirManterGrupoAction") &&
				!enderecoURL.contains("processarRequisicaoTerminalAutoatendimentoAction") &&
				!enderecoURL.contains("processarRequisicaoGpipaAction") &&
				!enderecoURL.contains("processarRequisicaoCompGisAction")
				
				 ){
                
				//Caso o tipo da url n�o esteja nulo
				if(tipoURL != null){
					
					//Caso o usu�rio tenha solicitado uma funcionalidade 
					if(tipoURL.equalsIgnoreCase("funcionalidade")){
												
						/*
						 * Caso usu�rio n�o tenha acesso a funcionalidade
						 * exibe a tela de acesso negado para funcionalidade
						 * Caso contr�rio chama o pr�ximo filtro na fila
						 */ 
						if(!fachada.verificarAcessoPermitidoFuncionalidade(usuarioLogado,
							enderecoURL, colecaoGruposUsuario,idFuncionalidade)){
							
							RequestDispatcher rd = 
								filterConfig.getServletContext().
									getRequestDispatcher("/jsp/util/acesso_negado_funcionalidade.jsp");
							
							request.setAttribute("URL", enderecoURL);
							rd.forward(request,response);
						}else{
							doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
						}
						
						//Caso o usu�rio tenha solicitado uma opera��o 
					}else if(tipoURL.equalsIgnoreCase("operacao")){
						
						
						/*
						 * Caso o usu�rio n�o tenha acesso a opera��o
						 * exibe a tela de acesso negado para opera��o
						 * Caso contr�rio chama o pr�ximo filtro na fila
						 */
						if(!fachada.verificarAcessoPermitidoOperacao(usuarioLogado,
							enderecoURL, colecaoGruposUsuario)){
							
							RequestDispatcher rd = 
								filterConfig.getServletContext().
									getRequestDispatcher("/jsp/util/acesso_negado_operacao.jsp");
							
							request.setAttribute("URL", enderecoURL);
							rd.forward(request,response);
							
						}else{
                            
							if(abrangencia != null){
                                
								if(!fachada.verificarAcessoAbrangencia(abrangencia)){
                                    RequestDispatcher rd = 
                                    	filterConfig.getServletContext().
                                    		getRequestDispatcher("/jsp/util/acesso_negado_abrangencia.jsp");
                                    rd.forward(request,response);
                                }else{
                                	doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
                                }
                            }else{
                            	doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
                            }
						}
					}
				}else{
				
					RequestDispatcher rd = 
						filterConfig.getServletContext().
							getRequestDispatcher("/jsp/util/acesso_negado_funcionalidade.jsp");
					request.setAttribute("URL", enderecoURL);
					rd.forward(request,response);	
				}
				
				
			// Lista de todas as funcionalidades que podem ser acessadas sem que exista um usuario logado na sessao
			} else if (enderecoURL.contains("Logoff") || 
				enderecoURL.contains("Login") || 
				enderecoURL.contains("telaPrincipal") || 
//				enderecoURL.contains("cancelar") || 
				enderecoURL.contains("executarBatch") || 
				enderecoURL.toLowerCase().contains("pesquisar") || 
				enderecoURL.toLowerCase().contains("relatorio") || 
				enderecoURL.contains("telaSucessoBaixarArquivoTxtAction") ||
				enderecoURL.contains("efetuarAlteracaoSenhaAction") || 
				enderecoURL.contains("carregarParametrosAction") || 
				enderecoURL.contains("exibirInformarMelhoriasGcomAction") || 
				enderecoURL.contains("informarMelhoriasGcomAction") || 
				enderecoURL.contains("exibirEfetuarAlteracaoSenhaSimplificadaAction") || 
				enderecoURL.contains("efetuarAlteracaoSenhaSimplificadaAction") || 
				enderecoURL.contains("exibirConsultarSistemaAlteracaoHistoricoAction") || 
				enderecoURL.contains("exibirSistemaHistAlteracaoDetalharPopupAction") ||
				enderecoURL.contains("exibirLembrarSenhaAction") || 
				enderecoURL.contains("lembrarSenhaAction") || 
				enderecoURL.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction") || 
				enderecoURL.contains("emitirSegundaViaContaInternetAcessoGeralAction") || 
				enderecoURL.contains("enviarDadosBancosAcessoGeralAction") || 
				enderecoURL.contains("exibirSelecionarBancoAcessoGeralAction") || 
				enderecoURL.contains("exibirSelecionarBancoAction") || 
				enderecoURL.contains("enviarDadosBancosAction") || 
				enderecoURL.contains("exibirLogTelaInicialAction") || 
				enderecoURL.contains("exibirLogTelaFinalAction") || 
				enderecoURL.contains("exibirConsultarDadosPagamentoAction") || 
				enderecoURL.contains("processarRequisicaoDipositivoMovelAction") || 
				enderecoURL.contains("exibirConsultarSituacaoEspecialFaturamentoPopupAction") || 
				enderecoURL.contains("exibirConsultarSituacaoEspecialCobrancaPopupAction") || 
				enderecoURL.contains("processarRequisicaoTelemetriaAction")||
				enderecoURL.contains("processarRequisicaoGisAction") ||
				enderecoURL.contains("processarCoordenadasGisAction") ||
				enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction" ) ||
				enderecoURL.contains("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction") ||
				enderecoURL.contains("exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction") ||
				enderecoURL.contains("emitir2viaDeclaracaoAnualQuitacaoDebitosAction") ||
				enderecoURL.contains("exibirInserirCadastroEmailClienteAction") ||
				enderecoURL.contains("inserirCadastroEmailClienteAction") || 
				enderecoURL.contains("gerarRelatorio2ViaContaAction") || 
				enderecoURL.contains("exibirInserirCadastroContaBraileAction") ||  
				enderecoURL.contains("inserirCadastroContaBraileAction") || 
				enderecoURL.contains("exibirServicosPortalCompesaAction") || 
				enderecoURL.contains("inserirCadastroEmailClientePortalAction") || 
				enderecoURL.contains("exibirInserirSolicitacaoServicosPortalAction") || 
				enderecoURL.contains("inserirSolicitacaoServicosPortalAction") || 
				enderecoURL.contains("exibirInserirCadastroEmailClientePortalAction") || 
				enderecoURL.contains("inserirCadastroContaBrailePortalAction") ||
				enderecoURL.contains("exibirInserirCadastroContaBrailePortalAction") ||
				enderecoURL.contains("emitirSegundaViaContaAction") ||
				enderecoURL.contains("exibirCanaisAtendimentoCompesaAction")|| 				 
				enderecoURL.contains("exibirQuestionarioSatisfacaoAction") || 
				enderecoURL.contains("questionarioSatisfacaoAction") ||
				enderecoURL.contains("exibirInformacoesPortalCompesaAction") ||   
				enderecoURL.contains("exibirInformacoesTarifaSocialPortalCompesaAction") ||   
				enderecoURL.contains("exibirInformacoesNegociacaoDebitosPortalCompesaAction") ||   
				enderecoURL.contains("exibirNormasInstalacaoPortalCompesaAction") ||   
				enderecoURL.contains("exibirCalendarioAbastecimentoPortalCompesaAction") ||
				enderecoURL.contains("exibirPortalInformacoesCompesaAction") ||
				enderecoURL.contains("exibirEfetuarParcelamentoDebitosPortalAction") ||
				enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCompesaAction") ||
				enderecoURL.contains("efetuarParcelamentoDebitosPortalAction") ||
				enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalAction") ||
				enderecoURL.contains("processarRequisicaoDispositivoMovelFiscalizacaoAnormalidadeAction")||
				enderecoURL.contains("exibirInserirClientePortalAction")||
				enderecoURL.contains("inserirClientePortalAction") || 
				enderecoURL.contains("exibirInformarVencimentoAlternativoPortalAction") ||
				enderecoURL.contains("inserirDiaVencimentoAlternativoAction") ||
				enderecoURL.contains("gerarSegundaViadeContratoAdesaoTacita") ||
				enderecoURL.contains("gerarCertidaoNegativaAction") ||
				enderecoURL.contains("gerarRelatorioPagamentoPortalAction")||
				enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCompesaAction") ||
				enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCompesaAction") ||
				enderecoURL.contains("exibirAcompanhamentoRAPortalAction") ||
				enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAndroidAction" ) ||
				enderecoURL.contains("exibirCadastrarImovelSorteioAction") || 
				enderecoURL.contains("cadastrarImovelSorteioAction") || 
				enderecoURL.contains("exibirEmitirComprovanteCadastroSorteioAction") ||
				enderecoURL.contains("gerarComprovanteCadastramentoSorteioAction") ||
				enderecoURL.contains("exibirInserirSolicitacaoServicosPortalVazamentoAction") ||
				enderecoURL.contains("inserirSolicitacaoServicosPortalVazamentoAction") ||
				enderecoURL.contains("exibirConsultarPagamentoPortalAction") ||
				enderecoURL.contains("efetuarPagamentoDebitosPortalAction") ||
				enderecoURL.contains("processarRequisicaoDispositivoMovelAtualizacaoCadastralAction") ||
				enderecoURL.contains("processarRequisicaoIntegracaoProgisAction" ) || 
				enderecoURL.contains("processarRequisicaoDispositivoMovelExecucaoOSAction") ||
				enderecoURL.contains("exibirManterGrupoAction") ||
				enderecoURL.contains("processarRequisicaoTerminalAutoatendimentoAction") ||
				enderecoURL.contains("processarRequisicaoGpipaAction") ||
				enderecoURL.contains("processarRequisicaoCompGisAction")
	
				){
				doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
				
			} else {

				RequestDispatcher rd = filterConfig.getServletContext()
						.getRequestDispatcher(
								"/jsp/util/acesso_negado_funcionalidade.jsp");
				
				request.setAttribute("URL", enderecoURL);
				rd.forward(request, response);

			}
		} catch (ServletException sx) {
			throw sx;
		} catch (IOException iox) {
			throw iox;
		}
	}


	private void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain, Usuario usuarioLogado, String enderecoURL) throws IOException, ServletException {
		long tempoInicial =System.currentTimeMillis();
		
		filterChain.doFilter(request, response);
		
		long tempoFinal = System.currentTimeMillis() - tempoInicial;
				
		Logger log = Logger.getLogger("GSAN_TEMPO");
		
		if (usuarioLogado != null) { 
			log.debug(usuarioLogado.getNomeUsuario()+":"+ enderecoURL+": " + tempoFinal + "ms");
		} else {
			log.debug(enderecoURL+": " + tempoFinal + "ms");
		}
	}


	private void setCaminhoMenu(HttpSession sessao, Integer idFuncionalidade, String enderecoURL, String tipoURL) {
		
		String caminhoMenuFuncionalidade = "";
		
		Fachada fachada = Fachada.getInstancia();
		
		//Ajuste que monta o caminho correto da funcionalidade Manter Vinculos de Imoveis para Rateio de Consumo.
		//Pois o mesmo re-utiliza classes da funcionalidade Manter Imovel, ocorrendo conflito no momento que carrega o caminho do menu.
		if ( sessao != null && sessao.getAttribute("caminhoMenuFuncionalidade") != null && 
				sessao.getAttribute("caminhoMenuFuncionalidade").equals("Gsan -> Micromedicao -> Medicao Individualizada -> Manter Vinculos de Imoveis para Rateio de Consumo") ) {
		
			caminhoMenuFuncionalidade = "Gsan -> Micromedicao -> Medicao Individualizada -> Manter Vinculos de Imoveis para Rateio de Consumo";
		
		} else if (tipoURL != null) {
			
			Funcionalidade funcionalidade = recuperarFuncionalidadePelaURL(enderecoURL, tipoURL, fachada);
			
			if (idFuncionalidade != null) {
				FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID, idFuncionalidade));
				
				Collection colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade, Funcionalidade.class.getName());
				funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
			} else {
				funcionalidade = recuperarFuncionalidadePelaURL(enderecoURL, tipoURL, fachada);
			}
			
			if (funcionalidade != null && funcionalidade.getFuncionalidadeCategoria() != null) {
				FuncionalidadeCategoria funcionalidadeCategoria = funcionalidade.getFuncionalidadeCategoria();

				caminhoMenuFuncionalidade = funcionalidadeCategoria
						.getNome() + " -> " + funcionalidade.getDescricao();

				while (funcionalidadeCategoria
						.getFuncionalidadeCategoriaSuperior() != null) {

					FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();
					filtroFuncionalidadeCategoria
							.adicionarParametro(new ParametroSimples(
									FiltroFuncionalidadeCategoria.ID,
									funcionalidadeCategoria
											.getFuncionalidadeCategoriaSuperior()
											.getId()));

					Collection colecaoFuncionalidadeCategoria = fachada
							.pesquisar(filtroFuncionalidadeCategoria,
									FuncionalidadeCategoria.class.getName());

					funcionalidadeCategoria = (FuncionalidadeCategoria) Util
							.retonarObjetoDeColecao(colecaoFuncionalidadeCategoria);

					caminhoMenuFuncionalidade = funcionalidadeCategoria
							.getNome()
							+ " -> " + caminhoMenuFuncionalidade;
				}
			}
			
			if(enderecoURL != null) { 
				if(enderecoURL.contains("exibirAtualizarResolucaoDiretoriaAction")){
					caminhoMenuFuncionalidade = "Gsan -> Cobranca -> Resolucao de Diretoria -> Manter Resolucao de Diretoria";
				}else if(enderecoURL.contains("exibirInserirRegistroAtendimentoDadosGeraisAction") || 
						 enderecoURL.contains("inserirRegistroAtendimentoDadosGeraisAction") ||
						 enderecoURL.contains("exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction") || 
						 enderecoURL.contains("inserirRegistroAtendimentoDadosLocalOcorrenciaAction") ||
						 enderecoURL.contains("exibirInserirRegistroAtendimentoDadosSolicitanteAction") ||
						 enderecoURL.contains("inserirRegistroAtendimentoDadosSolicitanteAction")) {
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Registro Atendimento -> Inserir Registro Atendimento";
				}else if(enderecoURL.contains("exibirConsultarRegistroAtendimentoAction") ||
						 enderecoURL.contains("exibirAtualizarRegistroAtendimentoAction") ||
						 enderecoURL.contains("exibirAtualizarRegistroAtendimentoDadosGeraisAction") || 
						 enderecoURL.contains("atualizarRegistroAtendimentoDadosGeraisAction") ||
						 enderecoURL.contains("exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction") || 
						 enderecoURL.contains("atualizarRegistroAtendimentoDadosLocalOcorrenciaAction") ||
						 enderecoURL.contains("exibirAtualizarRegistroAtendimentoDadosSolicitanteAction") ||
						 enderecoURL.contains("atualizarRegistroAtendimentoDadosSolicitanteAction")) {
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Registro Atendimento -> Manter Registro Atendimento";
				}else if(enderecoURL.contains("exibirAtualizarRotaAction")){
					caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Rota -> Manter Rota";
				}else if(enderecoURL.contains("exibirAtualizarServicoRepavimentadoraAction") || 
						 enderecoURL.contains("exibirManterServicoRepavimentadoraAction")){
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Manter Servico Repavimentadora ";
				}else if(enderecoURL.contains("exibirEncerrarOrdemServicoAction") ||
						 enderecoURL.contains("exibirAtualizarOrdemServicoAction")) {
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Manter Ordem de Servico";
				}else if(enderecoURL.contains("exibirMonitorarLeituraMobilePopupAction")){
					caminhoMenuFuncionalidade = "Gsan -> Micromedicao -> Monitorar Leituras Transmitidas ";
				}else if(enderecoURL.contains("validarExcecoesLeiturasConsumosLocalizacao")){
					caminhoMenuFuncionalidade = "Gsan -> Faturamento -> Leituras e Consumos -> Analisar Excecoes de Leituras e Consumos";
				}else if(enderecoURL.contains("exibirFiltrarExcecoesLeiturasConsumosAnormalidade")){
					caminhoMenuFuncionalidade = "Gsan -> Faturamento -> Leituras e Consumos -> Analisar Excecoes de Leituras e Consumos";
				}else if(enderecoURL.contains("exibirFiltrarExcecoesLeiturasConsumosLocalidade")){
					caminhoMenuFuncionalidade = "Gsan -> Faturamento -> Leituras e Consumos -> Analisar Excecoes de Leituras e Consumos";				
				}else if(enderecoURL.contains("exibirFiltrarExcecoesLeiturasConsumosCaracteristicas")){
					caminhoMenuFuncionalidade = "Gsan -> Faturamento -> Leituras e Consumos -> Analisar Excecoes de Leituras e Consumos";
				}else if(enderecoURL.contains("exibirAtualizarMotivoDeNaoExecucaoDoServicoAction")){
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Manter Motivo de Nao Execucao do Servico";
				}else if(enderecoURL.contains("exibirAtualizarNaturezaEquipeAction")){
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Manter Natureza de Equipe";
				}else if(enderecoURL.contains("exibirFiltrarTipoServicoAction") ||
						 enderecoURL.contains("exibirAtualizarTipoServicoAction") ||
						 enderecoURL.contains("filtrarTipoServicoAction")){
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Manter Tipo de Servico";
				}else if(enderecoURL.contains("exibirDadosGeracaoConsultaOSAction") ||
						 enderecoURL.contains("exibirConsultarResumoAcaoOSAction")) {
					caminhoMenuFuncionalidade = "Gsan -> Cobranca -> Consultar Resumo das Acoes de Ordem de Servico";
				}else if(enderecoURL.contains("exibirAtualizarFeriadoAction")){
					caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Sistema Parametro -> Manter Feriado";
				}else if(enderecoURL.contains("exibirInserirImovelLocalidadeAction") || 
						 enderecoURL.contains("inserirImovelLocalidadeAction") || 
						 enderecoURL.contains("exibirInserirImovelEnderecoAction") ||
						 enderecoURL.contains("inserirImovelEnderecoAction") ||
						 enderecoURL.contains("exibirInserirImovelClienteAction") ||
						 enderecoURL.contains("inserirImovelClienteAction") ||
						 enderecoURL.contains("exibirInserirImovelSubCategoriaAction") ||
						 enderecoURL.contains("inserirImovelSubCategoriaAction") ||
						 enderecoURL.contains("exibirInserirImovelCaracteristicasAction") ||
						 enderecoURL.contains("inserirImovelCaracteristicasAction")) {
					caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Imovel -> Inserir Imovel";
				}else if(enderecoURL.contains("exibirAtualizarImovelAction") ||
						 enderecoURL.contains("exibirAtualizarImovelLocalidadeAction") || 
						 enderecoURL.contains("atualizarImovelLocalidadeAction") || 
						 enderecoURL.contains("exibirAtualizarImovelEnderecoAction") ||
						 enderecoURL.contains("atualizarImovelEnderecoAction") ||
						 enderecoURL.contains("exibirAtualizarImovelClienteAction") ||
						 enderecoURL.contains("atualizarImovelClienteAction") ||
						 enderecoURL.contains("exibirAtualizarImovelSubCategoriaAction") ||
						 enderecoURL.contains("atualizarImovelSubCategoriaAction") ||
						 enderecoURL.contains("exibirAtualizarImovelCaracteristicasAction") ||
						 enderecoURL.contains("atualizarImovelCaracteristicasAction")){
					caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Imovel -> Manter Imovel";
				}else if(enderecoURL.contains("exibirAtualizarImovelAction") ||
						 enderecoURL.contains("exibirAtualizarImovelCaracteristicasAction") ||
						 enderecoURL.contains("exibirAtualizarImovelClienteAction") || 
						 enderecoURL.contains("exibirAtualizarImovelConclusaoAction") ||
						 enderecoURL.contains("exibirAtualizarImovelEnderecoAction") || 
						 enderecoURL.contains("exibirAtualizarImovelLocalidadeAction") ||
						 enderecoURL.contains("exibirAtualizarImovelSubCategoriaAction") ||
						 enderecoURL.contains("atualizarImovelCaracteristicasAction") ||
						 enderecoURL.contains("atualizarImovelClienteAction") || 
						 enderecoURL.contains("atualizarImovelConclusaoAction") ||
						 enderecoURL.contains("atualizarImovelEnderecoAction") || 
						 enderecoURL.contains("atualizarImovelLocalidadeAction") ||
						 enderecoURL.contains("atualizarImovelSubCategoriaAction")) {
					caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Imovel -> Manter Imovel";
				}else if(enderecoURL.contains("exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction")){
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Acompanhamento OS de Cobranca -> Consultar Arquivo Texto de Ordens de Servico";
				}else if(enderecoURL.contains("consultarDadosArquivoTextoOSCobrancaSmartphoneAction")){
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Acompanhamento OS de Cobranca -> Consultar Arquivo Texto de Ordens de Servico";
				}else if(enderecoURL.contains("exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction")){
					caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ordem de Servico -> Inspecao de Anormalidade -> Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade";
				}else if(enderecoURL.contains("exibirFiltrarLogradouroAction") ||
						 enderecoURL.contains("filtrarLogradouroAction")){
					caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Endereco -> Logradouro -> Manter Logradouro";
				}else if(enderecoURL.contains("exibirFiltrarSolicitacaoAcessoAction")){
					caminhoMenuFuncionalidade = "Gsan -> Seguranca -> Acesso -> Solicitacao de Acesso -> Manter Solicitacao de Acesso";
				}
			}			
		}
				
		if (sessao != null) {		
		   sessao.setAttribute("caminhoMenuFuncionalidade", caminhoMenuFuncionalidade);
		}
	}


	private Funcionalidade recuperarFuncionalidadePelaURL(String enderecoURL, String tipoURL, Fachada fachada) {
		
		Funcionalidade retorno = null;
		
		if (tipoURL.equalsIgnoreCase("funcionalidade")) {
			// Cria o filtro para pesquisar a funcionalidade com a url informada
			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
			if (enderecoURL.startsWith("/")) {
				filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
						FiltroFuncionalidade.CAMINHO_URL, enderecoURL
								.substring(1)));
			} else {
				filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
						FiltroFuncionalidade.CAMINHO_URL, enderecoURL));
			}
			
			// Pesquisa a funcionalidade com a url informada
			Collection colecaoFuncionalidade = fachada.pesquisar(
					filtroFuncionalidade, Funcionalidade.class.getName());
			
			if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {
				retorno = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
			}
			
		} else {
			// Cria o filtro para pesquisar a opera��o da url informada
			// e carrega a funcionalidade da opera��o
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			// filtroOperacao.adicionarParametro(new
			// ParametroSimples(FiltroOperacao.CAMINHO_URL,urlOperacao));
			if (enderecoURL.startsWith("/")) {
				filtroOperacao.adicionarParametro(new ComparacaoTexto(
						FiltroOperacao.CAMINHO_URL, enderecoURL
								.substring(1)));
			} else {
				filtroOperacao.adicionarParametro(new ComparacaoTexto(
						FiltroOperacao.CAMINHO_URL, enderecoURL));
			}

			filtroOperacao
					.adicionarCaminhoParaCarregamentoEntidade("funcionalidade.funcionalidadeCategoria");

			// Pesquisa a opera��o no sistema com a url informada
			Collection colecaoOperacao = fachada.pesquisar(filtroOperacao,
					Operacao.class.getName());

			if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {

				Operacao operacao = (Operacao) Util
						.retonarObjetoDeColecao(colecaoOperacao);

				retorno = operacao.getFuncionalidade();
				
			}
		}
		return retorno;
	}

	
	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 *
	 */
	public void destroy() {
	}
}
