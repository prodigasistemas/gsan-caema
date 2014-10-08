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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.SolicitacaoEspecificacaoHelper;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 13/11/2006
 */
public class AtualizarAdicionarSolicitacaoEspecificacaoAction extends
		GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAdicionarSolicitacaoEspecificacao");

		Collection colecaoSolicitacaoTipoEspecificacao = null;
		if (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao") == null) {
			colecaoSolicitacaoTipoEspecificacao = new ArrayList();
		} else {
			colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");
		}

		// String idSolicitacaoTipo = sessao.getAttribute("idTipoSolicitacao",
		// idSolicitacaoTipo);

		AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm = (AtualizarAdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		if (sessao.getAttribute("atualizar") != null) {

			// Obs: Se remover tudo da cole��o, qdo a cole�ao tiver mais de um
			// elemento vai dar pau. Entao, terei que Instaciar um objeto pra
			// receber os dados da colecao que esta na sessao, para depois
			// comparar com a que esta sendo alterada, pra depois fazer
			// altera��o.

			Integer posicaoComponente = (Integer) sessao
					.getAttribute("posicaoComponente");

			int index = 0;

			Iterator colecaoSolicitacaoTipoEspecificacaoIterator = colecaoSolicitacaoTipoEspecificacao
					.iterator();

			while (colecaoSolicitacaoTipoEspecificacaoIterator.hasNext()) {

				index++;

				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoIterator
						.next();

				if (index == posicaoComponente) {

					solicitacaoTipoEspecificacao
							.setIndicadorSolicitante(new Short("1"));

					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getPrazoPrevisaoAtendimento().equals("")) {
						// Prazo de previs�o de atendimento
						solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getPrazoPrevisaoAtendimento()));
					}
					// Descri��o da especifica��o
					solicitacaoTipoEspecificacao
							.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getDescricaoSolicitacao());

					// Pavimento de cal�ada obrigat�rio
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoCalcada() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPavimentoCalcada().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPavimentoCalcada(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPavimentoCalcada()));
					}
					// Pavimento de rua obrigat�rio
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPavimentoRua().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPavimentoRua(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPavimentoRua()));
					}

					// refere-se a liga��o de agua
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorLigacaoAgua().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorLigacaoAgua(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorLigacaoAgua()));
					}
					// Cobran�a de material
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrancaMaterial() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCobrancaMaterial().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorCobrancaMaterial(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCobrancaMaterial()));
					}
					// Matricula do im�vel obrigat�rio
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorMatriculaImovel().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorMatricula(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorMatriculaImovel()));
					}
					// indicador Urg�ncia
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUrgencia() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorUrgencia().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorUrgencia(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorUrgencia()));
					}
					// Parecer de encerramento obrigat�rio
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorParecerEncerramento() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorParecerEncerramento().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorParecerEncerramento(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorParecerEncerramento()));
					}
					// Gera d�bito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGerarDebito().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoDebito(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGerarDebito()));
					}
					// Gera Cr�dito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarCredito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGerarCredito().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoCredito(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGerarCredito()));
					}
					// hora e data correntes
					solicitacaoTipoEspecificacao.setUltimaAlteracao(new Date());

					// Unidade inicial tramita��o
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdUnidadeTramitacao() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdUnidadeTramitacao().equals("")) {
						// Verifica se o c�digo foi modificado
						if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoUnidadeTramitacao() == null
								|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getDescricaoUnidadeTramitacao().trim()
										.equals("")) {

							FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

							filtroUnidadeOrganizacional
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.ID,
											atualizarAdicionarSolicitacaoEspecificacaoActionForm
													.getIdUnidadeTramitacao()));

							filtroUnidadeOrganizacional
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.INDICADOR_USO,
											ConstantesSistema.INDICADOR_USO_ATIVO));

							Collection unidadeOrganizacionalEncontrado = fachada
									.pesquisar(filtroUnidadeOrganizacional,
											UnidadeOrganizacional.class
													.getName());

							if (unidadeOrganizacionalEncontrado != null
									&& !unidadeOrganizacionalEncontrado
											.isEmpty()) {
								UnidadeOrganizacional uinidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
										.get(0);
								solicitacaoTipoEspecificacao
										.setUnidadeOrganizacional(uinidadeOrganizacional);

							} else {
								throw new ActionServletException(
										"atencao.pesquisa_inexistente", null,
										"Unidade Organizacional");
							}

						} else {
							UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
							unidadeOrganizacional.setId(new Integer(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdUnidadeTramitacao()));
							solicitacaoTipoEspecificacao
									.setUnidadeOrganizacional(unidadeOrganizacional);
						}
					} else {
						solicitacaoTipoEspecificacao
								.setUnidadeOrganizacional(null);
					}
					
					// id do tipo da solicita��o gerada
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdServicoOS() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS().equals("")) {

						// Verifica se o c�digo foi modificado
						if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoServicoOS() == null
								|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getDescricaoServicoOS().trim().equals(
												"")) {

							FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

							filtroServicoTipo
									.adicionarParametro(new ParametroSimples(
											FiltroServicoTipo.ID,
											atualizarAdicionarSolicitacaoEspecificacaoActionForm
													.getIdServicoOS()));

							Collection servicoTipoEncontrado = fachada
									.pesquisar(filtroServicoTipo,
											ServicoTipo.class.getName());

							if (servicoTipoEncontrado != null
									&& !servicoTipoEncontrado.isEmpty()) {
								// [SF0003] - Validar Tipo Servi�o
								fachada
										.verificarServicoTipoReferencia(new Integer(
												atualizarAdicionarSolicitacaoEspecificacaoActionForm
														.getIdServicoOS()));

								ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
										.get(0);
								solicitacaoTipoEspecificacao
										.setServicoTipo(servicoTipo);
							} else {
								throw new ActionServletException(
										"atencao.pesquisa_inexistente", null,
										"Servi�o Tipo");
							}
//						} else if(solicitacaoTipoEspecificacao.getServicoTipo() != null){
//							solicitacaoTipoEspecificacao.getServicoTipo().setId(new Integer(
//									atualizarAdicionarSolicitacaoEspecificacaoActionForm
//											.getIdServicoOS()));
//
//							solicitacaoTipoEspecificacao.getServicoTipo()
//									.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
//											.getDescricaoServicoOS());

						}else{
							ServicoTipo servicoTipo = new ServicoTipo();
							servicoTipo.setId(new Integer(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdServicoOS()));

							servicoTipo
									.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getDescricaoServicoOS());

							solicitacaoTipoEspecificacao
									.setServicoTipo(servicoTipo);
						}

					}

					// Gera ordem de servi�o
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGeraOrdemServico() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGeraOrdemServico().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoOrdemServico(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGeraOrdemServico()));
					}
					// Cliente Obrigat�rio
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCliente().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorCliente(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCliente()));
					}
					
					// Coordenadas Obrigat�ria
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCoordenadaProgisRA() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCoordenadaProgisRA().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorCoordenadaProgisRA(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCoordenadaProgisRA()));
					}

					// Indicador verfificca��o de d�bito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorVerificarDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorVerificarDebito().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorVerificarDebito(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorVerificarDebito()));
					}

					// Indicador encerramento autom�tico
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorEncerramentoAutomatico() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorEncerramentoAutomatico()
									.equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorEncerramentoAutomatico(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorEncerramentoAutomatico()));
					}
					
					//Prazo Atendimento Agencia Reguladora(Arpe)
					if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getPrazoAtendimentoArpe())){
						solicitacaoTipoEspecificacao.setDiasPrazoArpe(Integer.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getPrazoAtendimentoArpe()));
					}
					else
						solicitacaoTipoEspecificacao.setDiasPrazoArpe(null);
					
					//Indicador loja virtual
					if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual())){
						solicitacaoTipoEspecificacao.setIndicadorLojaVirtual(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual()));
					}

					//RM 5759 - Am�lia Pessoa - 05/12/2011
					//indicador Permite Cancelar Debito
					if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito())){
						solicitacaoTipoEspecificacao.setIndicadorPermiteCancelarDebito(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito()));
					}
					
					
					//RM 5924 - Am�lia Pessoa 15/12/2011
					//Adicionar arquivo de procedimentos operacionais padr�es POPs
					Collection<ArquivoProcedimentoOperacionalPadrao> colecaoArquivos = (ArrayList<ArquivoProcedimentoOperacionalPadrao>) sessao.getAttribute("colecaoArquivos");
					Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers = (Collection<SolicitacaoEspecificacaoHelper>) sessao.getAttribute("colecaoHelpers");
					SolicitacaoEspecificacaoHelper helperRemove=null;
					if (colecaoHelpers!=null){
						for (SolicitacaoEspecificacaoHelper seh : colecaoHelpers){
							if (seh.getIdHelper()==solicitacaoTipoEspecificacao.getId()){
								helperRemove = seh;
							}
						}
					}
					if (helperRemove!=null){
						colecaoHelpers.remove(helperRemove);
					}
					if (colecaoHelpers==null){
						colecaoHelpers = new ArrayList<SolicitacaoEspecificacaoHelper>();
					}
					colecaoHelpers.add(new SolicitacaoEspecificacaoHelper(solicitacaoTipoEspecificacao.getId(), colecaoArquivos));
					//Atualizar dados da sessao
					//sessao.removeAttribute("colecaoArquivos");
					sessao.removeAttribute("colecaoHelpers");
					sessao.setAttribute("colecaoHelpers", colecaoHelpers);
					solicitacaoTipoEspecificacao.setColecaoArquivosPOP(colecaoArquivos);
					
					// Situa��o imovel
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdSituacaoImovel() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdSituacaoImovel().equals("")) {
						EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
						especificacaoImovelSituacao.setId(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdSituacaoImovel()));
						solicitacaoTipoEspecificacao
								.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
					}
					Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
							.getAttribute("colecaoEspecificacaoServicoTipo");
					// recupera a cole��o de especificacao servico tipo
					if (colecaoEspecificacaoServicoTipo != null
							&& !colecaoEspecificacaoServicoTipo.isEmpty()) {

						Collection colecao = new ArrayList();
						colecao.addAll(colecaoEspecificacaoServicoTipo);
						// [SF0004] - Validar Valor Ordem de Servi�o 2� parte
						fachada.verificarOrdemExecucaoForaOrdem(colecao);
						solicitacaoTipoEspecificacao
								.setEspecificacaoServicoTipos(new HashSet(
										colecaoEspecificacaoServicoTipo));
						sessao
								.removeAttribute("colecaoEspecificacaoServicoTipo");
					}

					// Colocado por Raphael Rossiter em 25/02/2008
					// Tipo de D�bito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdDebitoTipo() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdDebitoTipo().equals("")) {

						FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

						filtroDebitoTipo
								.adicionarParametro(new ParametroSimples(
										FiltroDebitoTipo.ID,
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdDebitoTipo()));

						Collection debitoTipoEncontrado = fachada.pesquisar(
								filtroDebitoTipo, DebitoTipo.class.getName());

						if (debitoTipoEncontrado != null
								&& !debitoTipoEncontrado.isEmpty()) {

							DebitoTipo debitoTipo = (DebitoTipo) Util
									.retonarObjetoDeColecao(debitoTipoEncontrado);

							solicitacaoTipoEspecificacao
									.setDebitoTipo(debitoTipo);
						} else {

							// [FS0007] - Validar Tipo de d�bito
							throw new ActionServletException(
									"atencao.pesquisa_inexistente", null,
									"Tipo de D�bito");
						}

					} else {
						solicitacaoTipoEspecificacao.setDebitoTipo(null);
					}

					// Valor do D�bito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getValorDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getValorDebito().equals("")) {

						solicitacaoTipoEspecificacao
								.setValorDebito(Util
										.formatarMoedaRealparaBigDecimal(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getValorDebito()));
					} else {
						solicitacaoTipoEspecificacao.setValorDebito(null);
					}

					// Alterar Valor do d�bito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPermiteAlterarValor() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPermiteAlterarValor().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPermiteAlterarValor(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPermiteAlterarValor()));
					}

					// Cobrar Juros
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrarJuros() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCobrarJuros().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorCobrarJuros(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCobrarJuros()));
					}
					
					// Instala��o de hidr�metro
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInstalacaoHidrometro() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInstalacaoHidrometro().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorInstalacaoHidrometro(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorInstalacaoHidrometro()));
					}
					
					//Indicador Tipo Instala��o de Hidr�metro, o usu�rio deve informar se a especifica��o � do tipo instala��o de hidr�metro
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorTipoInstalacaoHidrometro() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorTipoInstalacaoHidrometro().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorTipoInstalacaoHidrometro(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorTipoInstalacaoHidrometro()));
					}
					
					//
					// Instala��o de hidr�metro
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorTipoSubstituicaoHidrometro() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorTipoSubstituicaoHidrometro().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorTipoSubstituicaoHidrometro(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorTipoSubstituicaoHidrometro()));
					}
					
					// Instala��o de hidr�metro
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorFactivelFaturavelEsp() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorFactivelFaturavelEsp().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorFactivelFaturavel(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorFactivelFaturavelEsp()));
					}
					
					//indicador anexo obrigatorio
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorAnexoObrigatorio() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorAnexoObrigatorio().equals("") && !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorAnexoObrigatorio().equals("null")) {
						solicitacaoTipoEspecificacao
								.setIndicadorAnexoObrigatorio(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorAnexoObrigatorio()));
					}

					// indicador de uso ativo
					solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
							ConstantesSistema.INDICADOR_USO_ATIVO));

					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdEspecificacao() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdEspecificacao().equals("")) {
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
						solicitacaoTipoEspecificacaoNovoRA
								.setId(Integer
										.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdEspecificacao()));

						SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
						solicitacaoTipo
								.setId(Integer
										.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdTipoSolicitacao()));
						solicitacaoTipoEspecificacaoNovoRA
								.setSolicitacaoTipo(solicitacaoTipo);

						solicitacaoTipoEspecificacao
								.setSolicitacaoTipoEspecificacaoNovoRA(solicitacaoTipoEspecificacaoNovoRA);
					}
					else {
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
						
						solicitacaoTipoEspecificacaoNovoRA
								.setSolicitacaoTipo(null);

						solicitacaoTipoEspecificacao
								.setSolicitacaoTipoEspecificacaoNovoRA(null);
					}

					// Informar conta no Registro de Atendimento
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarContaRA() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInformarContaRA().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorInformarContaRA(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorInformarContaRA()));
					}

					// Informar conta no Registro de Atendimento
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarPagamentoDP() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInformarPagamentoDP().equals(
											"")) {
						FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();

						filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.INDICADOR_INFORMAR_PAGAMENTO_DUPLICIDADE, ConstantesSistema.INDICADOR_USO_ATIVO));

						Collection colecaoTipoEspecificacao = fachada.pesquisar(
								filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
						
						if ((colecaoTipoEspecificacao != null) && (!colecaoTipoEspecificacao.isEmpty()) && 
									(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarPagamentoDP().equals("1"))){
							
							Iterator colecaoTipoEspecificacaoIterator = colecaoTipoEspecificacao.iterator();

							while (colecaoTipoEspecificacaoIterator.hasNext()) {
							
								SolicitacaoTipoEspecificacao retornoSolicitacaoTipoEspecificacao = 
										(SolicitacaoTipoEspecificacao) colecaoTipoEspecificacaoIterator.next();
								
								if (!retornoSolicitacaoTipoEspecificacao.getId().equals(solicitacaoTipoEspecificacao.getId())){
									throw new ActionServletException(
											"atencao.pagamento_duplicidade_existente", retornoSolicitacaoTipoEspecificacao.getDescricao());
								}
							}
				
						}else{
							solicitacaoTipoEspecificacao.setIndicadorInformarPagamentoDuplicidade(new Short(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarPagamentoDP()));
						}
					}
					
					// Informar Documento Pagamento A Maior	
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoAMaior() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoAMaior().equals("")){
						
						FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();

						filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.INDICADOR_INFORMAR_PAGAMENTO_A_MAIOR, ConstantesSistema.INDICADOR_USO_ATIVO));

						Collection colecaoTipoEspecificacao = fachada.pesquisar(
								filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
						
                        if ((colecaoTipoEspecificacao != null) && (!colecaoTipoEspecificacao.isEmpty()) && 
        						(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoAMaior().equals("1"))){
							
                        	Iterator colecaoTipoEspecificacaoIterator = colecaoTipoEspecificacao.iterator();

							while (colecaoTipoEspecificacaoIterator.hasNext()) {
							
								SolicitacaoTipoEspecificacao retornoSolicitacaoTipoEspecificacao = 
										(SolicitacaoTipoEspecificacao) colecaoTipoEspecificacaoIterator.next();
								
								if (retornoSolicitacaoTipoEspecificacao.getId() != solicitacaoTipoEspecificacao.getId()){
									throw new ActionServletException(
											"atencao.documento_pago_valor_maior_existente", retornoSolicitacaoTipoEspecificacao.getDescricao());
								}
							}	
                        }else{
							solicitacaoTipoEspecificacao.setIndicadorInformarDocumentoPagamentoAMaior(new Short(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoAMaior()));
						}
					}
					
					// Informar Documento Pagamento Indevido	
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoIndevido() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoIndevido().equals("")){
						
						FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();

						filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.INDICADOR_INFORMAR_PAGAMENTO_INDEVIDO, ConstantesSistema.INDICADOR_USO_ATIVO));

						Collection colecaoTipoEspecificacao = fachada.pesquisar(
								filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());

						if ((colecaoTipoEspecificacao != null) && (!colecaoTipoEspecificacao.isEmpty()) && 
								(atualizarAdicionarSolicitacaoEspecificacaoActionForm.
										getIndicadorInformarDocumentoPagamentoIndevido().equals("1"))){
							
                        	Iterator colecaoTipoEspecificacaoIterator = colecaoTipoEspecificacao.iterator();

							while (colecaoTipoEspecificacaoIterator.hasNext()) {
							
								SolicitacaoTipoEspecificacao retornoSolicitacaoTipoEspecificacao = 
										(SolicitacaoTipoEspecificacao) colecaoTipoEspecificacaoIterator.next();
								
								if (retornoSolicitacaoTipoEspecificacao.getId() != solicitacaoTipoEspecificacao.getId()){
									throw new ActionServletException(
											"atencao.documento_pago_valor_indevidamente", retornoSolicitacaoTipoEspecificacao.getDescricao());
								}
								
							}
						}else{
							solicitacaoTipoEspecificacao.setIndicadorInformarDocumentoPagamentoIndevido(new Short(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoIndevido()));
						}						
					}
					
					// Informar altera��o no vencimento alternativo
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorAlterarVencimentoAlternativo() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorAlterarVencimentoAlternativo()
									.equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorAlterarVencimentoAlternativo(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorAlterarVencimentoAlternativo()));
					}
					
					// Motivo Corte
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdMotivoCorte() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdMotivoCorte().equals("")) {
						
						MotivoCorte motivoCorte = new MotivoCorte();
						motivoCorte.setId(Integer
								.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdMotivoCorte()));
						solicitacaoTipoEspecificacao.setMotivoCorte(motivoCorte);
					}
			
					//RM 6899 - Fernanda Almeida
					
					// Indicador de uso
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUso() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorUso().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorUso(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorUso()));
					}

				}
			}

		} else {

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();

			solicitacaoTipoEspecificacao
					.setIndicadorSolicitante(new Short("1"));

			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getPrazoPrevisaoAtendimento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento().equals("")) {
				// Prazo de previs�o de atendimento
				solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getPrazoPrevisaoAtendimento()));
			}
			// Descri��o da especifica��o
			solicitacaoTipoEspecificacao
					.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getDescricaoSolicitacao());

			// Pavimento de cal�ada obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoCalcada() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoCalcada().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoCalcada(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoCalcada()));
			}
			// Pavimento de rua obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoRua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoRua(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoRua()));
			}

			// refere-se a liga��o de agua
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorLigacaoAgua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorLigacaoAgua(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorLigacaoAgua()));
			}
			// Cobran�a de material
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCobrancaMaterial() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrancaMaterial().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorCobrancaMaterial(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorCobrancaMaterial()));
			}
			// Matricula do im�vel obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorMatriculaImovel() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorMatricula(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorMatriculaImovel()));
			}
			// Indicador Urg�ncia
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorUrgencia() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUrgencia().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorUrgencia(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorUrgencia()));
			}
			// Parecer de encerramento obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorParecerEncerramento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorParecerEncerramento().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorParecerEncerramento(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorParecerEncerramento()));
			}
			// Gera d�bito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoDebito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarDebito()));
			}
			// Gera Cr�dito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarCredito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarCredito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoCredito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarCredito()));
			}

			// Encerramento Autom�tico
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorEncerramentoAutomatico() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorEncerramentoAutomatico().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorEncerramentoAutomatico(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorEncerramentoAutomatico()));
			}
			
			//Indicador loja virtual
			if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual())){
				solicitacaoTipoEspecificacao.setIndicadorLojaVirtual(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual()));
			}

			// hora e data correntes
			solicitacaoTipoEspecificacao.setUltimaAlteracao(new Date());

			// Unidade inicial tramita��o
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdUnidadeTramitacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdUnidadeTramitacao().equals("")) {
				// Verifica se o c�digo foi modificado
				if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.getDescricaoUnidadeTramitacao() == null
						|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoUnidadeTramitacao().trim().equals(
										"")) {

					FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

					filtroUnidadeOrganizacional
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.ID,
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdUnidadeTramitacao()));

					filtroUnidadeOrganizacional
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection unidadeOrganizacionalEncontrado = fachada
							.pesquisar(filtroUnidadeOrganizacional,
									UnidadeOrganizacional.class.getName());

					if (unidadeOrganizacionalEncontrado != null
							&& !unidadeOrganizacionalEncontrado.isEmpty()) {
						UnidadeOrganizacional uinidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
								.get(0);
						solicitacaoTipoEspecificacao
								.setUnidadeOrganizacional(uinidadeOrganizacional);

					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Unidade Organizacional");
					}

				} else {
					UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
					unidadeOrganizacional.setId(new Integer(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdUnidadeTramitacao()));
					solicitacaoTipoEspecificacao
							.setUnidadeOrganizacional(unidadeOrganizacional);
				}
			}

			// id do tipo da solicita��o gerada
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdServicoOS() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdServicoOS().equals("")) {

				// Verifica se o c�digo foi modificado
				if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.getDescricaoServicoOS() == null
						|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoServicoOS().trim().equals("")) {

					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

					filtroServicoTipo.adicionarParametro(new ParametroSimples(
							FiltroServicoTipo.ID,
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));

					Collection servicoTipoEncontrado = fachada.pesquisar(
							filtroServicoTipo, ServicoTipo.class.getName());

					if (servicoTipoEncontrado != null
							&& !servicoTipoEncontrado.isEmpty()) {
						// [SF0003] - Validar Tipo Servi�o
						fachada.verificarServicoTipoReferencia(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdServicoOS()));

						ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
								.get(0);
						solicitacaoTipoEspecificacao
								.setServicoTipo(servicoTipo);
					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Servi�o Tipo");
					}
				} else {
					ServicoTipo servicoTipo = new ServicoTipo();
					servicoTipo.setId(new Integer(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));
					solicitacaoTipoEspecificacao.setServicoTipo(servicoTipo);
				}

			}

			// Gera ordem de servi�o
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGeraOrdemServico() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGeraOrdemServico().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoOrdemServico(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGeraOrdemServico()));
			}
			// Cliente Obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCliente() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente().equals("")) {

				solicitacaoTipoEspecificacao.setIndicadorCliente(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorCliente()));
			}

			// Indicador verfificca��o de d�bito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorVerificarDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorVerificarDebito().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorVerificarDebito(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorVerificarDebito()));
			}

			// Situa��o imovel
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdSituacaoImovel() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdSituacaoImovel().equals("")) {
				EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
				especificacaoImovelSituacao.setId(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdSituacaoImovel()));
				solicitacaoTipoEspecificacao
						.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
			}
			Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
					.getAttribute("colecaoEspecificacaoServicoTipo");
			// recupera a cole��o de especificacao servico tipo
			if (colecaoEspecificacaoServicoTipo != null
					&& !colecaoEspecificacaoServicoTipo.isEmpty()) {

				Collection colecao = new ArrayList();
				colecao.addAll(colecaoEspecificacaoServicoTipo);

				// [SF0004] - Validar Valor Ordem de Servi�o 2� parte
				fachada.verificarOrdemExecucaoForaOrdem(colecao);
				solicitacaoTipoEspecificacao
						.setEspecificacaoServicoTipos(new HashSet(
								colecaoEspecificacaoServicoTipo));
				sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
			}

			// Colocado por Raphael Rossiter em 25/02/2008
			// Tipo de D�bito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdDebitoTipo() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdDebitoTipo().equals("")) {
				// Verifica se o c�digo foi modificado
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getDescricaoDebitoTipo() == null
					|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getDescricaoDebitoTipo().trim().equals("")) {

				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
						FiltroDebitoTipo.ID,
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdDebitoTipo()));

				Collection debitoTipoEncontrado = fachada.pesquisar(
						filtroDebitoTipo, DebitoTipo.class.getName());

				if (debitoTipoEncontrado != null
						&& !debitoTipoEncontrado.isEmpty()) {

					DebitoTipo debitoTipo = (DebitoTipo) Util
							.retonarObjetoDeColecao(debitoTipoEncontrado);

					solicitacaoTipoEspecificacao.setDebitoTipo(debitoTipo);
				} else {

					// [FS0007] - Validar Tipo de d�bito
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de D�bito");
				}

			}else {
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdDebitoTipo()));
				solicitacaoTipoEspecificacao.setDebitoTipo(debitoTipo);
			}
		}

			// Valor do D�bito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getValorDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getValorDebito().equals("")) {

				solicitacaoTipoEspecificacao
						.setValorDebito(Util
								.formatarMoedaRealparaBigDecimal(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getValorDebito()));
			}

			// Alterar Valor do d�bito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPermiteAlterarValor() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPermiteAlterarValor().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPermiteAlterarValor(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPermiteAlterarValor()));
			}

			// Cobrar Juros
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCobrarJuros() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrarJuros().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorCobrarJuros(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorCobrarJuros()));
			}
			
			// Instalacao de hidrometro
						if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorInstalacaoHidrometro() != null
								&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorInstalacaoHidrometro().equals("")) {
							solicitacaoTipoEspecificacao.setIndicadorInstalacaoHidrometro(new Short(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIndicadorInstalacaoHidrometro()));
						}

			// indicador de uso ativo
			solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
					ConstantesSistema.INDICADOR_USO_ATIVO));

			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdEspecificacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdEspecificacao().equals("")) {
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
				solicitacaoTipoEspecificacaoNovoRA
						.setId(Integer
								.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdEspecificacao()));

				SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
				solicitacaoTipo
						.setId(Integer
								.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdTipoSolicitacao()));
				solicitacaoTipoEspecificacaoNovoRA
						.setSolicitacaoTipo(solicitacaoTipo);

				solicitacaoTipoEspecificacao
						.setSolicitacaoTipoEspecificacaoNovoRA(solicitacaoTipoEspecificacaoNovoRA);
			}else {
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
				
				solicitacaoTipoEspecificacaoNovoRA
						.setSolicitacaoTipo(null);

				solicitacaoTipoEspecificacao
						.setSolicitacaoTipoEspecificacaoNovoRA(null);
			}

			// Informar conta no Registro de Atendimento
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorInformarContaRA() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarContaRA().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorInformarContaRA(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorInformarContaRA()));
			}
			
			// Informar Documento Pagamento A Maior	
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoAMaior() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoAMaior().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorInformarDocumentoPagamentoAMaior(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoAMaior()));			
			}

			// Informar Documento Pagamento Indevido	
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoIndevido() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoIndevido().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorInformarDocumentoPagamentoIndevido(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorInformarDocumentoPagamentoIndevido()));						
			}
			
			// Informar Pagamento em duplicidade
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorInformarPagamentoDP() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarPagamentoDP().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorInformarPagamentoDuplicidade(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorInformarPagamentoDP()));
			}

			// Informar Altera��o no Vencimento Alternativo
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorAlterarVencimentoAlternativo() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorAlterarVencimentoAlternativo().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorAlterarVencimentoAlternativo(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorAlterarVencimentoAlternativo()));
			}
			if (!colecaoSolicitacaoTipoEspecificacao
					.contains(solicitacaoTipoEspecificacao)) {
				colecaoSolicitacaoTipoEspecificacao
						.add(solicitacaoTipoEspecificacao);
			}

		}

		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
				colecaoSolicitacaoTipoEspecificacao);

		
		// manda um parametro para fechar o popup
		httpServletRequest.setAttribute("fecharPopup", 1);

		return retorno;
	}
}
