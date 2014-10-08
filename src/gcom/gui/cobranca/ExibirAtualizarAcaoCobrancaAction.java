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
 * Anderson Italo Felinto de Lima
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
package gcom.gui.cobranca;

import java.util.Collection;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para atualiza o criterio da cobran�a
 * 
 * @author S�vio Luiz
 * @date 06/11/2006
 */
public class ExibirAtualizarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// caso seja a primeira vez
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.removeAttribute("voltar");
		}


		AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;
		if ((httpServletRequest.getParameter("idRegistroAtualizar") != null && !httpServletRequest
				.getParameter("idRegistroAtualizar").equals(""))
				|| (sessao.getAttribute("cobrancaAcao") != null && !sessao
						.getAttribute("cobrancaAcao").equals(""))) {

			if (httpServletRequest.getParameter("objetoConsulta") == null) {

				CobrancaAcao cobrancaAcao = null;
				if (httpServletRequest.getParameter("idRegistroAtualizar") != null
						&& !httpServletRequest.getParameter(
								"idRegistroAtualizar").equals("")) {
					String idAcaoCobranca = httpServletRequest
							.getParameter("idRegistroAtualizar");
					FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
					filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcao.ID, idAcaoCobranca));
					
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
					
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.documentoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoAguaSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoEsgotoSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.cobrancaCriterio");
					

					Collection colecaoCobrancaAcao = fachada.pesquisar(
							filtroCobrancaAcao, CobrancaAcao.class.getName());
					if (colecaoCobrancaAcao != null
							&& !colecaoCobrancaAcao.isEmpty()) {

						cobrancaAcao = (CobrancaAcao) Util
								.retonarObjetoDeColecao(colecaoCobrancaAcao);
					}
					sessao.setAttribute("voltar", "manter");
				} else {
					cobrancaAcao = (CobrancaAcao) sessao
							.getAttribute("cobrancaAcao");
					sessao.setAttribute("voltar", "filtrar");
				}

				if (cobrancaAcao != null && !cobrancaAcao.equals("")) {

					if (cobrancaAcao.getDescricaoCobrancaAcao() != null) {
						acaoCobrancaAtualizarActionForm
								.setDescricaoAcao(cobrancaAcao
										.getDescricaoCobrancaAcao());
					} else {
						acaoCobrancaAtualizarActionForm.setDescricaoAcao("");
					}
				
					if (cobrancaAcao.getCobrancaCriterio() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdCobrancaCriterio(""
										+ cobrancaAcao.getCobrancaCriterio()
												.getId());
						acaoCobrancaAtualizarActionForm
								.setDescricaoCobrancaCriterio(cobrancaAcao
										.getCobrancaCriterio()
										.getDescricaoCobrancaCriterio());
					} else {
						acaoCobrancaAtualizarActionForm
							.setIdCobrancaCriterio("");
					
						acaoCobrancaAtualizarActionForm
							.setDescricaoCobrancaCriterio("");
					}
				
					if (cobrancaAcao.getServicoTipo() != null) {
						acaoCobrancaAtualizarActionForm.setIdServicoTipo(""
								+ cobrancaAcao.getServicoTipo().getId());
						acaoCobrancaAtualizarActionForm
								.setDescricaoServicoTipo(cobrancaAcao
										.getServicoTipo().getDescricao());
					} else {
						acaoCobrancaAtualizarActionForm.setIdServicoTipo("");
						acaoCobrancaAtualizarActionForm.setDescricaoServicoTipo("");
					}
				
					if (cobrancaAcao.getIndicadorObrigatoriedade() != null) {
						acaoCobrancaAtualizarActionForm.setIcAcaoObrigatoria(""
								+ cobrancaAcao.getIndicadorObrigatoriedade());
					} else {
						acaoCobrancaAtualizarActionForm.setIcAcaoObrigatoria("");
					}
				
					if (cobrancaAcao.getIndicadorAcrescimoImpontualidade() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcAcrescimosImpontualidade(""
										+ cobrancaAcao
												.getIndicadorAcrescimoImpontualidade());
					} else {
						acaoCobrancaAtualizarActionForm.setIcAcrescimosImpontualidade("");
					}
				
					if (cobrancaAcao.getIndicadorCronograma() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcCompoeCronograma(""
										+ cobrancaAcao.getIndicadorCronograma());
					} else {
						acaoCobrancaAtualizarActionForm
						.setIcCompoeCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorCobrancaDebACobrar() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcDebitosACobrar(""
										+ cobrancaAcao
												.getIndicadorCobrancaDebACobrar());
					} else {
						acaoCobrancaAtualizarActionForm.setIcDebitosACobrar("");
					}
					
					if (cobrancaAcao.getIndicadorBoletim() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcEmitirBoletimCadastro(""
										+ cobrancaAcao.getIndicadorBoletim());
					} else {
						acaoCobrancaAtualizarActionForm.setIcEmitirBoletimCadastro("");
					}
					
					if (cobrancaAcao.getIndicadorGeracaoTaxa() != null) {
						acaoCobrancaAtualizarActionForm.setIcGeraTaxa(""
								+ cobrancaAcao.getIndicadorGeracaoTaxa());
					} else {
						acaoCobrancaAtualizarActionForm.setIcGeraTaxa("");
					}
					
					if (cobrancaAcao.getIndicadorDebito() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcImoveisSemDebitos(""
										+ cobrancaAcao.getIndicadorDebito());
					} else {
						acaoCobrancaAtualizarActionForm.setIcImoveisSemDebitos("");
					}
					
					if (cobrancaAcao.getIndicadorRepeticao() != null) {
						acaoCobrancaAtualizarActionForm.setIcRepetidaCiclo(""
								+ cobrancaAcao.getIndicadorRepeticao());
					} else {
						acaoCobrancaAtualizarActionForm.setIcRepetidaCiclo("");
					}
					
					if (cobrancaAcao.getIndicadorSuspensaoAbastecimento() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcSuspensaoAbastecimento(""
										+ cobrancaAcao
												.getIndicadorSuspensaoAbastecimento());
					} else {
						acaoCobrancaAtualizarActionForm.setIcSuspensaoAbastecimento("");
					}
					
					if (cobrancaAcao.getCobrancaAcaoPredecessora() != null && 
							!cobrancaAcao.getCobrancaAcaoPredecessora().equals("")) {
						acaoCobrancaAtualizarActionForm
								.setIdAcaoPredecessora(""
										+ cobrancaAcao
												.getCobrancaAcaoPredecessora()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdAcaoPredecessora("");
					}

					if (cobrancaAcao.getLigacaoAguaSituacao() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdSituacaoLigacaoAgua(""
										+ cobrancaAcao.getLigacaoAguaSituacao()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdSituacaoLigacaoAgua("");
					}
					
					if (cobrancaAcao.getLigacaoEsgotoSituacao() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdSituacaoLigacaoEsgoto(""
										+ cobrancaAcao
												.getLigacaoEsgotoSituacao()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdSituacaoLigacaoEsgoto("");
					}
					
					if (cobrancaAcao.getDocumentoTipo() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdTipoDocumentoGerado(""
										+ cobrancaAcao.getDocumentoTipo()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdTipoDocumentoGerado("");
					}
					
					if (cobrancaAcao.getNumeroDiasMinimoAcaoPrecedente() != null) {
						acaoCobrancaAtualizarActionForm
								.setNumeroDiasEntreAcoes(""
										+ cobrancaAcao
												.getNumeroDiasMinimoAcaoPrecedente());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasEntreAcoes("");
					}
					
					if (cobrancaAcao.getNumeroDiasValidade() != null) {
						acaoCobrancaAtualizarActionForm
								.setNumeroDiasValidade(""
										+ cobrancaAcao.getNumeroDiasValidade());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasValidade("");
					}
					
					if (cobrancaAcao.getOrdemRealizacao() != null) {
						acaoCobrancaAtualizarActionForm.setOrdemCronograma(""
								+ cobrancaAcao.getOrdemRealizacao());
					} else {
						acaoCobrancaAtualizarActionForm.setOrdemCronograma("");
					}
					
					if (cobrancaAcao.getNumeroDiasVencimento() != null) {
						acaoCobrancaAtualizarActionForm.setNumeroDiasVencimento(""
								+ cobrancaAcao.getNumeroDiasVencimento());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasVencimento("");
					}
					
					if (cobrancaAcao.getIndicadorMetasCronograma() != null) {
						acaoCobrancaAtualizarActionForm.setIcMetasCronograma(""
								+ cobrancaAcao.getIndicadorMetasCronograma());
					} else {
						acaoCobrancaAtualizarActionForm.setIcMetasCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorOrdenamentoCronograma() != null) {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoCronograma(""
								+ cobrancaAcao.getIndicadorOrdenamentoCronograma());
					} else {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorOrdenamentoEventual() != null) {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoEventual(""
								+ cobrancaAcao.getIndicadorOrdenamentoEventual());
					} else {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoEventual("");
					}
					
					if (cobrancaAcao.getIndicadorDebitoInterfereAcao() != null) {
						acaoCobrancaAtualizarActionForm.setIcDebitoInterfereAcao(""
								+ cobrancaAcao.getIndicadorDebitoInterfereAcao());
					} else { 
						acaoCobrancaAtualizarActionForm.setIcDebitoInterfereAcao("");
					}
					
					if (cobrancaAcao.getNumeroDiasRemuneracaoTerceiro() != null) {
						acaoCobrancaAtualizarActionForm.setNumeroDiasRemuneracaoTerceiro(""
								+ cobrancaAcao.getNumeroDiasRemuneracaoTerceiro());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasRemuneracaoTerceiro("");
					}
					if(cobrancaAcao.getIndicadorCreditosARealizar()!=null){
						acaoCobrancaAtualizarActionForm.setIcCreditosARealizar(
								cobrancaAcao.getIndicadorCreditosARealizar().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcCreditosARealizar("");
					}
					if(cobrancaAcao.getIndicadorNotasPromissoria()!=null){
						acaoCobrancaAtualizarActionForm.setIcNotasPromissoria(
								cobrancaAcao.getIndicadorNotasPromissoria().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcNotasPromissoria("");
					}
					if(cobrancaAcao.getIndicadorOrdenarMaiorValor()!=null){
						acaoCobrancaAtualizarActionForm.setIcOrdenarMaiorValor(
								cobrancaAcao.getIndicadorOrdenarMaiorValor().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcOrdenarMaiorValor("");
					}
					if(cobrancaAcao.getIndicadorValidarItem()!=null){
						acaoCobrancaAtualizarActionForm.setIcValidarItem(
								cobrancaAcao.getIndicadorValidarItem().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcValidarItem("");
					}
					
					if(cobrancaAcao.getTextoPersonalizado() !=null &&
						!cobrancaAcao.getTextoPersonalizado().equals("")){
						
						//Pesquisando texto personalizado
						acaoCobrancaAtualizarActionForm.setTextoPersonalizado(fachada
						.buscarTextoPersonalizadoAcaoCobranca(cobrancaAcao.getId()));
						
					}else{
						acaoCobrancaAtualizarActionForm.setTextoPersonalizado("");
					}
					if(cobrancaAcao.getIndicadorEfetuarAcaoCpfCnpjValido()!=null){
						acaoCobrancaAtualizarActionForm.setIcEfetuarAcaoCpfCnpjValido(cobrancaAcao.getIndicadorEfetuarAcaoCpfCnpjValido().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcEfetuarAcaoCpfCnpjValido("");
					}
				}
				// faz as pesquisas obrigat�rias
				pesquisasObrigatorias(fachada, sessao);

				// seta o objeto na sess�o para ser atualizado
				sessao.setAttribute("cobrancaAcao", cobrancaAcao);

			}
		}

		// pesquisa os dados do enter
		pesquisarEnter(acaoCobrancaAtualizarActionForm, httpServletRequest,
				fachada);

		return retorno;
	}

	private void pesquisarEnter(
			AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		// pesquisa enter de crit�rio de cobran�a
		if (acaoCobrancaAtualizarActionForm.getIdCobrancaCriterio() != null
				&& !acaoCobrancaAtualizarActionForm.getIdCobrancaCriterio()
						.equals("")
				&& (acaoCobrancaAtualizarActionForm
						.getDescricaoCobrancaCriterio() == null || acaoCobrancaAtualizarActionForm
						.getDescricaoCobrancaCriterio().equals(""))) {

			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
			try {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.ID, new Integer(
								acaoCobrancaAtualizarActionForm
										.getIdCobrancaCriterio())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Crit�rio de Cobran�a");
			}
			filtroCobrancaCriterio
					.setCampoOrderBy(FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO);
			Collection colecaoCobrancaCriterio = fachada.pesquisar(
					filtroCobrancaCriterio, CobrancaCriterio.class.getName());

			if (colecaoCobrancaCriterio != null
					&& !colecaoCobrancaCriterio.isEmpty()) {
				CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) Util
						.retonarObjetoDeColecao(colecaoCobrancaCriterio);
				acaoCobrancaAtualizarActionForm
						.setDescricaoCobrancaCriterio(cobrancaCriterio
								.getDescricaoCobrancaCriterio());
			} else {
				acaoCobrancaAtualizarActionForm.setIdCobrancaCriterio("");
				acaoCobrancaAtualizarActionForm
						.setDescricaoCobrancaCriterio("COBRAN�A CRIT�RIO INEXISTENTE");
			}

		}

		// pesquisa enter de tipo de servi�o
		if (acaoCobrancaAtualizarActionForm.getIdServicoTipo() != null
				&& !acaoCobrancaAtualizarActionForm.getIdServicoTipo().equals(
						"")
				&& (acaoCobrancaAtualizarActionForm.getDescricaoServicoTipo() == null || acaoCobrancaAtualizarActionForm
						.getDescricaoServicoTipo().equals(""))) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			try {
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, new Integer(
								acaoCobrancaAtualizarActionForm
										.getIdServicoTipo())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Servi�o Tipo");
			}
			filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util
						.retonarObjetoDeColecao(colecaoServicoTipo);
				acaoCobrancaAtualizarActionForm
						.setDescricaoServicoTipo(servicoTipo.getDescricao());
			} else {
				acaoCobrancaAtualizarActionForm.setIdServicoTipo("");
				acaoCobrancaAtualizarActionForm
						.setDescricaoServicoTipo("TIPO DE SERVI�O INEXISTENTE");
			}

		}
		
		//pesquisa documento tipo
		if (acaoCobrancaAtualizarActionForm.getIdTipoDocumentoGerado() != null && 
			!acaoCobrancaAtualizarActionForm.getIdTipoDocumentoGerado().equals("")) {

			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
			FiltroDocumentoTipo.ID, Integer.valueOf(acaoCobrancaAtualizarActionForm.getIdTipoDocumentoGerado())));
			
			Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
			
			if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Documento Tipo");
			}

			DocumentoTipo documentoTipo = (DocumentoTipo) Util.retonarObjetoDeColecao(colecaoDocumentoTipo);
			
			if (documentoTipo.getIndicadorTextoPersonalizado() != null &&
				documentoTipo.getIndicadorTextoPersonalizado().equals(ConstantesSistema.SIM)){
				
				httpServletRequest.setAttribute("exibirTextoPersonalizado", "OK");
			}
		}
	}

	private void pesquisasObrigatorias(Fachada fachada, HttpSession sessao) {
		// pesquisa as a��es predecessoras
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoAcaoPredecessora = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		if (colecaoAcaoPredecessora == null
				|| colecaoAcaoPredecessora.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Cobran�a A��o");
		} else {
			sessao.setAttribute("colecaoAcaoPredecessora",
					colecaoAcaoPredecessora);
		}

		// pesquisa os tipos de documentos
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());
		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Documento Tipo");
		} else {
			sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		}

		// pesquisa as situa��es de liga��es de agua
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if (colecaoLigacaoAguaSituacao == null
				|| colecaoLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Liga��o Agua Situa��o");
		} else {
			sessao.setAttribute("colecaoLigacaoAguaSituacao",
					colecaoLigacaoAguaSituacao);
		}

		// pesquisa as situa��es de liga��es de agua
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(
				filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class
						.getName());
		if (colecaoLigacaoEsgotoSituacao == null
				|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Liga��o Esgoto Situa��o");
		} else {
			sessao.setAttribute("colecaoLigacaoEsgotoSituacao",
					colecaoLigacaoEsgotoSituacao);
		}
	}
}
