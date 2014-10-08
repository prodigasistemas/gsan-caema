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

import java.io.IOException;

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

/**
 * Filtro respons�vel por verificar se a sess�o do usu�rio expirou o tempo
 * 
 * @author Pedro Alexandre
 * @date 05/07/2006
 */
public class FiltroSessaoExpirada extends HttpServlet implements Filter {
	// Vari�vel que vai armazenar a configura��o do filtro
	private FilterConfig filterConfig;

	private static final long serialVersionUID = 1L;

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 * 
	 * @param filterConfig
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 * 
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			// Faz um cast no request para recuperar a sess�o do usu�rio
			HttpServletRequest requestPagina = ((HttpServletRequest) request);
			HttpSession sessao = requestPagina.getSession(false);

			// Recupera a url do request
			String enderecoURL = requestPagina.getServletPath();

			if ( sessao == null && enderecoURL.contains("exibirLogTelaFinalAction") ) {
				RequestDispatcher rd = filterConfig.getServletContext()
						.getRequestDispatcher("/jsp/util/sessao_expirada.jsp");
				rd.forward(request, response);
			}else 
			// Caso a sess�o esteja nula(expirou) redireciona o usu�rio para a
			// p�gina de sess�o expirada
			// Caso contr�rio chama o pr�ximo filtro do web.xml se existir
			if (sessao == null && (!enderecoURL
							.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction")
							&& !enderecoURL
									.contains("emitirSegundaViaContaInternetAcessoGeralAction")
							&& !enderecoURL
									.contains("enviarDadosBancosAcessoGeralAction")
							&& !enderecoURL
									.contains("exibirSelecionarBancoAction")
							&& !enderecoURL.contains("enviarDadosBancosAction")
							&& !enderecoURL
									.contains("exibirSelecionarBancoAcessoGeralAction")
							&& !enderecoURL
									.contains("exibirLogTelaInicialAction")
							&& !enderecoURL
									.contains("exibirLogTelaFinalAction")
							&& !enderecoURL
									.contains("processarRequisicaoDipositivoMovelAction")
							&& !enderecoURL
									.contains("processarRequisicaoTelemetriaAction")
							&& !enderecoURL.contains("efetuarLoginAction")
							&& !enderecoURL
									.contains("processarRequisicaoGisAction")
							&& !enderecoURL
									.contains("processarCoordenadasGisAction")
							&& !enderecoURL
									.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction")
                            && !enderecoURL
                            		.contains ("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction") 									
							&& !enderecoURL
									.contains("exibirInserirCadastroEmailClienteAction")
							&& !enderecoURL
									.contains("inserirCadastroEmailClienteAction")
							&& !enderecoURL
									.contains("gerarRelatorio2ViaContaAction")
							&& !enderecoURL
									.contains("exibirInserirCadastroContaBraileAction")
							&& !enderecoURL
									.contains("inserirCadastroContaBraileAction")
							&& !enderecoURL
									.contains("exibirServicosPortalCompesaAction")
							&& !enderecoURL
									.contains("inserirCadastroContaBrailePortalAction")
							&& !enderecoURL
									.contains("exibirInserirSolicitacaoServicosPortalAction")
							&& !enderecoURL
									.contains("inserirSolicitacaoServicosPortalAction")
							&& !enderecoURL
									.contains("exibirInserirCadastroContaBrailePortalAction")
							&& !enderecoURL
									.contains("inserirCadastroEmailClientePortalAction") 
							&& !enderecoURL
									.contains("exibirInserirCadastroEmailClientePortalAction")
							&& !enderecoURL
									.contains("emitirSegundaViaContaAction")
							&& !enderecoURL
									.contains("exibirCanaisAtendimentoCompesaAction")
							&& !enderecoURL
									.contains("exibirQuestionarioSatisfacaoAction")
							&& !enderecoURL
									.contains("questionarioSatisfacaoAction")
							&& !enderecoURL
									.contains("exibirInformacoesPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirInformacoesTarifaSocialPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirInformacoesNegociacaoDebitosPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirNormasInstalacaoPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirCalendarioAbastecimentoPortalCompesaAction")
							&& !enderecoURL.
									contains("exibirEfetuarParcelamentoDebitosPortalAction")
							&& !enderecoURL.
									contains("efetuarParcelamentoDebitosPortalAction")
							&& !enderecoURL.
									contains("exibirLojasAtendimentoPresencialPortalCompesaAction")
							&& !enderecoURL.
									contains("exibirConsultarEstruturaTarifariaPortalAction")
							&& !enderecoURL.
									contains("processarRequisicaoDispositivoMovelFiscalizacaoAnormalidadeAction")
							&& !enderecoURL.
									contains("exibirInserirClientePortalAction")
							&& !enderecoURL.
									contains("inserirClientePortalAction") 
							&& !enderecoURL.
									contains("exibirInformarVencimentoAlternativoPortalAction")
							&& !enderecoURL.
									contains("inserirDiaVencimentoAlternativoAction")
							&& !enderecoURL.
									contains("gerarSegundaViadeContratoAdesaoTacita")
							&& !enderecoURL.
									contains("gerarCertidaoNegativaAction")
							&& !enderecoURL.
									contains("exibirAcompanhamentoRAPortalAction")
							&& !enderecoURL.
									contains("gerarRelatorioPagamentoPortalAction")
							&& !enderecoURL.
									contains("exibirConsultarConsumoHistoricoAguaPortalCompesaAction")
							&& !enderecoURL.
									contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAndroidAction")
							&& !enderecoURL
									.contains("exibirCadastrarImovelSorteioAction")
							&& !enderecoURL
									.contains("cadastrarImovelSorteioAction")
							&& !enderecoURL
									.contains("exibirEmitirComprovanteCadastroSorteioAction")
							&& !enderecoURL
									.contains("gerarComprovanteCadastramentoSorteioAction")
							&& !enderecoURL
									.contains("processarRequisicaoDispositivoMovelAtualizacaoCadastralAction")
							&& !enderecoURL
									.contains("exibirInserirSolicitacaoServicosPortalVazamentoAction")
							&& !enderecoURL
									.contains("inserirSolicitacaoServicosPortalVazamentoAction")
							&& !enderecoURL
									.contains("exibirConsultarPagamentoPortalAction")
									
							&& !enderecoURL.contains("efetuarPagamentoDebitosPortalAction")
							&& !enderecoURL.contains("exibirInserirSolicitacaoServicosPortalVazamentoAction")
							&& !enderecoURL.contains("inserirSolicitacaoServicosPortalVazamentoAction") && !enderecoURL.contains("processarRequisicaoIntegracaoProgisAction")

							&& !enderecoURL
							.contains("processarRequisicaoDispositivoMovelExecucaoOSAction")
							&& !enderecoURL
									.contains("processarRequisicaoTerminalAutoatendimentoAction")
							&& !enderecoURL
									.contains("processarRequisicaoGpipaAction")	
							&& !enderecoURL
									.contains("processarRequisicaoCompGisAction")	
									
					)) {

				RequestDispatcher rd = filterConfig.getServletContext()
						.getRequestDispatcher("/jsp/util/sessao_expirada.jsp");
				rd.forward(request, response);
			} else {
				if (enderecoURL
						.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction")) {
					// Cria uma sessao temporaria para o usuario que entra no
					// EmitirSegundaViaContaInternet sem logar no sistema
					sessao = requestPagina.getSession(true);

				}
				filterChain.doFilter(request, response);
			}

		} catch (ServletException sx) {
			throw sx;
		} catch (IOException iox) {
			throw iox;
		}
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