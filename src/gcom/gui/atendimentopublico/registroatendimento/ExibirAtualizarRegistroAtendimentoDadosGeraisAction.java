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

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da atualização de um R.A (Aba nº 01 - Dados
 * gerais)
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class ExibirAtualizarRegistroAtendimentoDadosGeraisAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarRegistroAtendimentoDadosGerais");


		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		sessao.removeAttribute("gis");
		
		// recupara o id da especificação para verificar se
		// será gerado a ordem de serviço ou não dependendo da mudança
		// da especificação
		Integer idEspecificacaoBase = (Integer) sessao
				.getAttribute("idEspecificacaoBase");

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = null;

		if (httpServletRequest
				.getAttribute("AtualizarRegistroAtendimentoActionForm") != null) {
			sessao
					.setAttribute(
							"AtualizarRegistroAtendimentoActionForm",
							httpServletRequest
									.getAttribute("AtualizarRegistroAtendimentoActionForm"));
			atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) httpServletRequest
					.getAttribute("AtualizarRegistroAtendimentoActionForm");
		} else {
			atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;
		}

		Fachada fachada = Fachada.getInstancia();
		
		//[SB0040] - Habilita/Desabilita Unidade de Atendimento
		FiltroUsuarioPemissaoEspecial filtroPermissaoUsuario = new FiltroUsuarioPemissaoEspecial();
		filtroPermissaoUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.ALTERA_UNIDADE_ATENDIMENTO_MEIO_SOLICITACAO));
		filtroPermissaoUsuario.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID,usuario.getId()));
		
		Collection<?> colecaoUsuarioPermisao = fachada.pesquisar(filtroPermissaoUsuario, UsuarioPermissaoEspecial.class.getName());
		
		if ( (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) ) {
			
			sessao.setAttribute("desabilitaUnidade", "SIM");
		}else{
			sessao.removeAttribute("desabilitaUnidade");
		}

		/*
		 * Unidade de Atendimento (Permite que o usuário informe ou selecione
		 * outra)
		 * 
		 * [FS0004] - Verificar existência da unidade de atendimento
		 * 
		 * [FS0033] - Verificar autorização da unidade de atendimento para
		 * abertura de registro de atendimento
		 */

		String pesquisarUnidade = (String) httpServletRequest.getParameter("pesquisarUnidade");
		if (pesquisarUnidade != null && pesquisarUnidade.equalsIgnoreCase("OK")) {
			
			String idUnidade = atualizarRegistroAtendimentoActionForm.getUnidade();
			
			UnidadeOrganizacional unidadeOrganizacionalSelecionada = fachada
					.verificarAutorizacaoUnidadeAberturaRA(new Integer(
							idUnidade), false);

			if (unidadeOrganizacionalSelecionada != null) {
				atualizarRegistroAtendimentoActionForm
						.setUnidade(unidadeOrganizacionalSelecionada.getId()
								.toString());
				atualizarRegistroAtendimentoActionForm
						.setDescricaoUnidade(unidadeOrganizacionalSelecionada
								.getDescricao());

				if (unidadeOrganizacionalSelecionada.getMeioSolicitacao() != null) {

					atualizarRegistroAtendimentoActionForm
							.setMeioSolicitacao(unidadeOrganizacionalSelecionada
									.getMeioSolicitacao().getId().toString());
				}

				httpServletRequest.setAttribute("nomeCampo", "meioSolicitacao");
				
				
			} else {

				atualizarRegistroAtendimentoActionForm.setUnidade("");
				atualizarRegistroAtendimentoActionForm
						.setDescricaoUnidade("Unidade Inexistente");

				httpServletRequest.setAttribute("corUnidade", "exception");
				httpServletRequest.setAttribute("nomeCampo", "unidade");
			}

		}

		/*
		 * Meio de Solicitação - Carregando a coleção que irá ficar disponível
		 * para escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados
		 */
		Collection colecaoMeioSolicitacao = (Collection) sessao
				.getAttribute("colecaoMeioSolicitacao");

		if (colecaoMeioSolicitacao == null) {

			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao(
					FiltroMeioSolicitacao.DESCRICAO);

			filtroMeioSolicitacao.setConsultaSemLimites(true);

			filtroMeioSolicitacao.adicionarParametro(new ParametroSimples(
					FiltroMeioSolicitacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			

			colecaoMeioSolicitacao = fachada.pesquisar(filtroMeioSolicitacao,
					MeioSolicitacao.class.getName());

			if (colecaoMeioSolicitacao == null
					|| colecaoMeioSolicitacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"MEIO_SOLICITACAO");
			} else {
				verificarMeioSolicitacaoPermissaoEspecial(colecaoMeioSolicitacao, usuario, fachada);
				
				sessao.setAttribute("colecaoMeioSolicitacao",
						colecaoMeioSolicitacao);
			}
		}
		
		/*
		 * Grupo de Atendimento - Carregando a coleção que irá ficar disponível
		 * para escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados
		 */
		Collection colecaoGrupoAtendimento = (Collection) sessao.
				getAttribute("colecaoGrupoAtendimento");
		
		if(colecaoGrupoAtendimento == null) {
			FiltroSolicitacaoTipoGrupo filtroSolicitacaoTipoGrupo = 
				new FiltroSolicitacaoTipoGrupo(FiltroSolicitacaoTipoGrupo.DESCRICAO);
			
			filtroSolicitacaoTipoGrupo.setConsultaSemLimites(true);
			
			filtroSolicitacaoTipoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoGrupoAtendimento = fachada.pesquisar(filtroSolicitacaoTipoGrupo, 
				SolicitacaoTipoGrupo.class.getName());
			
			if(Util.isVazioOrNulo(colecaoGrupoAtendimento)){
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"GRUPO_ATENDIMENTO");
			}else{
				sessao.setAttribute("colecaoGrupoAtendimento", colecaoGrupoAtendimento);
			}
		}
		
		boolean limparCampos = false;
		String pesquisarTipoSolicitacao = (String) httpServletRequest.getParameter("pesquisarTipoSolicitacao");
		if(pesquisarTipoSolicitacao != null && pesquisarTipoSolicitacao.equalsIgnoreCase("OK")){
			sessao.removeAttribute("colecaoSolicitacaoTipo");
			
			atualizarRegistroAtendimentoActionForm.setTipoSolicitacao("");
			atualizarRegistroAtendimentoActionForm.setEspecificacao("");
			
			limparCampos = true;
		}

		/*
		 * Tipo de Solicitação - Carregando a coleção que irá ficar disponível
		 * para escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados
		 */
		Collection colecaoSolicitacaoTipo = (Collection) sessao
				.getAttribute("colecaoSolicitacaoTipo");
		
		if (colecaoSolicitacaoTipo == null) {

			FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo(
					FiltroSolicitacaoTipo.DESCRICAO);

			filtroSolicitacaoTipo.setConsultaSemLimites(true);
			
			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
					SolicitacaoTipo.INDICADOR_USO_SISTEMA_NAO));
			
			if(atualizarRegistroAtendimentoActionForm.getGrupoAtendimento() != null && 
					!atualizarRegistroAtendimentoActionForm.getGrupoAtendimento().equals("")){
				
				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID,
						atualizarRegistroAtendimentoActionForm.getGrupoAtendimento()));
			}
			
			colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo,
					SolicitacaoTipo.class.getName());
			if(!Util.isVazioOrNulo(colecaoSolicitacaoTipo)) {
				sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
			}
		}

		/*
		 * Especificação - Carregando a coleção que irá ficar disponível para
		 * escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados
		 */
		String pesquisarEspecificacao = httpServletRequest.getParameter("pesquisarEspecificacao");

		if (pesquisarEspecificacao != null
				&& !pesquisarEspecificacao.equalsIgnoreCase("")
				|| (atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null && !atualizarRegistroAtendimentoActionForm
						.getTipoSolicitacao().equals(""))) {

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(
					FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			
			if(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null && 
					!atualizarRegistroAtendimentoActionForm.getTipoSolicitacao().equals("")){
			
				filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
							new Integer(atualizarRegistroAtendimentoActionForm
									.getTipoSolicitacao())));

				filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);
	
				Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
						filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
	
				if(!Util.isVazioOrNulo(colecaoSolicitacaoTipoEspecificacao)) {
					sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
					
					if(atualizarRegistroAtendimentoActionForm.getEspecificacao() == null || 
							atualizarRegistroAtendimentoActionForm.getEspecificacao().equals("")){
						atualizarRegistroAtendimentoActionForm.setDataPrevista("");
						atualizarRegistroAtendimentoActionForm.setValorSugerido("");
						atualizarRegistroAtendimentoActionForm.setPrazoPrevistoArpe("");
					}
				}
			}else{
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
				atualizarRegistroAtendimentoActionForm.setDataPrevista("");
				atualizarRegistroAtendimentoActionForm.setValorSugerido("");
				atualizarRegistroAtendimentoActionForm.setPrazoPrevistoArpe("");
			}
		}else{
			sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
		}

		/*
		 * Data Prevista - (exibir a data prevista calculada no SB0003 e não
		 * permitir alteração).
		 * 
		 * [SB0003 - Define Data Prevista e Unidade Destino da Especificação]
		 * [FS0018] - Verificar existência da unidade centralizadora
		 */
		String definirDataPrevista = httpServletRequest
				.getParameter("definirDataPrevista");

		if (definirDataPrevista != null
				&& !definirDataPrevista.equalsIgnoreCase("")
				&& atualizarRegistroAtendimentoActionForm.getDataAtendimento() != null
				&& !atualizarRegistroAtendimentoActionForm.getDataAtendimento()
						.equalsIgnoreCase("")
				&& !atualizarRegistroAtendimentoActionForm
						.getEspecificacao()
						.equalsIgnoreCase(
								String
										.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			Date dataAtendimento = Util
					.converteStringParaDate(atualizarRegistroAtendimentoActionForm
							.getDataAtendimento());

			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper dataPrevistaUnidadeDestino = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(
							dataAtendimento, new Integer(
									atualizarRegistroAtendimentoActionForm
											.getEspecificacao()));
			
			//Definir Prazo repassado ao Cliente (ARPE)
			//[SB0054] - Define Prazo Repassado ao Cliente (ARPE)
			Date dataArpe = fachada.obterPrazoRepassadoClienteArpe(
					atualizarRegistroAtendimentoActionForm.getEspecificacao(),Util.converteStringParaDate(atualizarRegistroAtendimentoActionForm.getDataAtendimento()));
			
			atualizarRegistroAtendimentoActionForm.setPrazoPrevistoArpe(Util.formatarData(dataArpe));
			

			if (dataPrevistaUnidadeDestino.getDataPrevista() != null) {
				atualizarRegistroAtendimentoActionForm.setDataPrevista(Util
						.formatarData(dataPrevistaUnidadeDestino
								.getDataPrevista()));
			}

			atualizarRegistroAtendimentoActionForm
					.setIndFaltaAgua(dataPrevistaUnidadeDestino
							.getIndFaltaAgua());

			atualizarRegistroAtendimentoActionForm
					.setIndMatricula(dataPrevistaUnidadeDestino
							.getIndMatricula());

			atualizarRegistroAtendimentoActionForm
					.setImovelObrigatorio(dataPrevistaUnidadeDestino
							.getImovelObrigatorio());

			atualizarRegistroAtendimentoActionForm
					.setPavimentoRuaObrigatorio(dataPrevistaUnidadeDestino
							.getPavimentoRuaObrigatorio());

			atualizarRegistroAtendimentoActionForm
					.setPavimentoCalcadaObrigatorio(dataPrevistaUnidadeDestino
							.getPavimentoCalcadaObrigatorio());

			// Identificar tipo de geração da ordem de serviço (AUTOMÁTICA,
			// OPCIONAL ou NÃO GERAR)
			if ((fachada
					.gerarOrdemServicoAutomatica(Util
							.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
									.getEspecificacao())))
					&& (!idEspecificacaoBase.equals(new Integer(
							atualizarRegistroAtendimentoActionForm
									.getEspecificacao())))) {
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
				
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.ID,
								atualizarRegistroAtendimentoActionForm.getEspecificacao()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroNaoNulo(
								FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO_ID));
				
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
								filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				Integer idServicoTipo = ((SolicitacaoTipoEspecificacao)
				colecaoSolicitacaoTipoEspecificacao.iterator().next()).getServicoTipo().getId();
				
				sessao.setAttribute("servicoTipo", idServicoTipo);
			
				
				sessao.setAttribute("gerarOSAutomativa", "OK");
			} else {
				sessao.removeAttribute("gerarOSAutomativa");
				sessao.removeAttribute("servicoTipo");
			}

			httpServletRequest.setAttribute("nomeCampo", "observacao");
		}
		/*
		 * Caso o Tempo de Espera Final esteja desabilitado e o Tempo de Espera
		 * Inicial para Atendimento esteja preenchido, atribuir o valor
		 * correspondente à hora corrente e não permitir alteração
		 */
		String tempoEsperaFinalDesabilitado = httpServletRequest
				.getParameter("tempoEsperaFinalDesabilitado");

		if (tempoEsperaFinalDesabilitado != null
				&& !tempoEsperaFinalDesabilitado.equalsIgnoreCase("")) {
			this
					.atribuirHoraCorrenteTempoEsperaFinal(atualizarRegistroAtendimentoActionForm);
			httpServletRequest.setAttribute("nomeCampo", "unidade");
		}
		
		// [FS0045] - Verificar existência de Contas Associadas
		// Mariana Victor - 31/01/2011
		if (atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null
				&& !atualizarRegistroAtendimentoActionForm.getTipoSolicitacao().equals("")
				&& atualizarRegistroAtendimentoActionForm.getEspecificacao() != null
				&& !atualizarRegistroAtendimentoActionForm.getEspecificacao().equals("")) {
			FiltroRegistroAtendimentoConta filtroRegistroAtendimentoConta = new FiltroRegistroAtendimentoConta();
			filtroRegistroAtendimentoConta.adicionarParametro(
					new ParametroSimples(FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO_ID, atualizarRegistroAtendimentoActionForm.getNumeroRA()));
			Collection colecaoRAConta = fachada.pesquisar(filtroRegistroAtendimentoConta, RegistroAtendimentoConta.class.getName());
			
			if (colecaoRAConta != null && !colecaoRAConta.isEmpty()) {
				sessao.setAttribute("contasAssociadas", true);
			} else {
				sessao.removeAttribute("contasAssociadas");
			}
		}
		
		// [SB0033] - Desabilita dados para especificação de devolução
		// Mariana Victor - 14/01/2013
		if (atualizarRegistroAtendimentoActionForm.getEspecificacao() != null
				&& !atualizarRegistroAtendimentoActionForm.getEspecificacao().equals("")
				&& fachada.verificarRegistroAtendimentoEspecificacaoDevolucao(
			new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()))) {
			sessao.setAttribute("bloquearEspecificacao", true);
		} else {
			sessao.removeAttribute("bloquearEspecificacao");
		}
		
		//Davi Menezes - 20/06/2012
		if(limparCampos){
			atualizarRegistroAtendimentoActionForm.setDataPrevista("");
			atualizarRegistroAtendimentoActionForm.setValorSugerido("");
			atualizarRegistroAtendimentoActionForm.setPrazoPrevistoArpe("");
			
			limparCampos = false;
		}
		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Atribui o valor correspondente à hora corrente
	 * 
	 * @author Sávio Luiz
	 * @date 10/08/2006
	 * 
	 * @param AtualizarRegistroAtendimentoActionForm
	 * @return void
	 */
	private void atribuirHoraCorrenteTempoEsperaFinal(
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm) {

		Date dataCorrente = new Date();

		atualizarRegistroAtendimentoActionForm.setTempoEsperaFinal(Util
				.formatarHoraSemSegundos(dataCorrente));
	}
	
	/**
	 * [FS 0061] - Verificar Meio de Solicitação com Permissão Especial
	 * 
	 * @author Davi Menezes
	 * @date 08/08/2013
	 */
	private void verificarMeioSolicitacaoPermissaoEspecial(Collection colecaoMeioSolicitacao, Usuario usuario, Fachada fachada){
		Collection<Integer> colecaoIdMeioSolicitacao = new ArrayList<Integer>();
		
		MeioSolicitacao meioSolicitacao = null;
		
		Iterator<?> it = colecaoMeioSolicitacao.iterator();
		while(it.hasNext()){
			meioSolicitacao = (MeioSolicitacao) it.next();
			
			if(meioSolicitacao.getIndicadorPermissaoEspecial().equals(ConstantesSistema.SIM)){
				colecaoIdMeioSolicitacao.add(meioSolicitacao.getId());
			}
		}
		
		if(!Util.isVazioOrNulo(colecaoIdMeioSolicitacao)){
			boolean possuiPermissaoEspecial = fachada.verificarPermissaoEspecialAtiva(
				PermissaoEspecial.SELECIONAR_MEIO_SOLICITACAO_RESTRITO, usuario);
			
			if(!possuiPermissaoEspecial){
				for(Integer idMeioSolicitacao : colecaoIdMeioSolicitacao){
					it = colecaoMeioSolicitacao.iterator();
					while(it.hasNext()){
						meioSolicitacao = (MeioSolicitacao) it.next();
						
						if(meioSolicitacao.getId().equals(idMeioSolicitacao)){
							colecaoMeioSolicitacao.remove(meioSolicitacao);
							break;
						}
					}
				}
			}
		}
	}

}
