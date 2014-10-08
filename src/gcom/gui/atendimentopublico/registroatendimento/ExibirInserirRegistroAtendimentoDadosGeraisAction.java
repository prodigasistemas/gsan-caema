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

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
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
 * parâmetros para realização da inserção de um R.A (Aba nº 01 - Dados gerais)
 * 
 * @author Raphael Rossiter
 * @date 24/07/2006
 */
public class ExibirInserirRegistroAtendimentoDadosGeraisAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("inserirRegistroAtendimentoDadosGerais");

		InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm = (InserirRegistroAtendimentoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		sessao.removeAttribute("gis");
		
		// Usuário logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		/*
		 * Tipo de Atendimento (exibir a tela com a opção “on-line” selecionada
		 * e permitir que o usuário selecione entre “on-line” ou “manual”)
		 * 
		 * [SB0001 - Habilita/Desabilita Dados do Momento do Atendimento]
		 */
		if (inserirRegistroAtendimentoActionForm.getTipo() == null
				|| inserirRegistroAtendimentoActionForm.getTipo()
						.equalsIgnoreCase("")) {

			inserirRegistroAtendimentoActionForm.setTipo("1");

			/*
			 * Unidade de Atendimento (exibir a tela com a unidade associada ao
			 * usuário que estiver efetuando o registro de atendimento. (Tela
			 * inicial)
			 * 
			 * Meio de Solicitação (exibir na tela com o meio de solicitação
			 * associado à unidade de atendimento)
			 */

			UnidadeOrganizacional unidadeOrganizacionalUsuario = fachada
					.obterUnidadeOrganizacionalAberturaRAAtivoUsuario(usuario
							.getLogin());

			if (unidadeOrganizacionalUsuario != null) {

				inserirRegistroAtendimentoActionForm
						.setUnidade(unidadeOrganizacionalUsuario.getId()
								.toString());
				inserirRegistroAtendimentoActionForm
						.setDescricaoUnidade(unidadeOrganizacionalUsuario
								.getDescricao());

				if (unidadeOrganizacionalUsuario.getMeioSolicitacao() != null) {

					inserirRegistroAtendimentoActionForm
							.setMeioSolicitacao(unidadeOrganizacionalUsuario
									.getMeioSolicitacao().getId().toString());
				}
			}
			
			//[SB0040] - Habilita/Desabilita Unidade de Atendimento
			FiltroUsuarioPemissaoEspecial filtroPermissaoUsuario = new FiltroUsuarioPemissaoEspecial();
			filtroPermissaoUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.ALTERA_UNIDADE_ATENDIMENTO_MEIO_SOLICITACAO));
			filtroPermissaoUsuario.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID,usuario.getId()));
			
			Collection<?> colecaoUsuarioPermisao = fachada.pesquisar(filtroPermissaoUsuario, UsuarioPermissaoEspecial.class.getName());
			
			if ( (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) && unidadeOrganizacionalUsuario != null) {
				
				sessao.setAttribute("desabilitaUnidade", "SIM");
			}else{
				sessao.removeAttribute("desabilitaUnidade");
			}
						
			// [SB0001 - Habilita/Desabilita Dados do Momento do Atendimento]
			habilitacaoDadosMomentoAtendimento(
					inserirRegistroAtendimentoActionForm, httpServletRequest);
		}

		String mudarTipo = httpServletRequest.getParameter("mudarTipo");

		if (mudarTipo != null && !mudarTipo.equalsIgnoreCase("")) {

			// [SB0001 - Habilita/Desabilita Dados do Momento do Atendimento]
			habilitacaoDadosMomentoAtendimento(
					inserirRegistroAtendimentoActionForm, httpServletRequest);
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
		String pesquisarUnidade = httpServletRequest
				.getParameter("pesquisarUnidade");

		if (pesquisarUnidade != null && !pesquisarUnidade.equalsIgnoreCase("")) {

			UnidadeOrganizacional unidadeOrganizacionalSelecionada = fachada
					.verificarAutorizacaoUnidadeAberturaRA(new Integer(
							inserirRegistroAtendimentoActionForm.getUnidade()),
							false);

			if (unidadeOrganizacionalSelecionada != null) {
				inserirRegistroAtendimentoActionForm
						.setUnidade(unidadeOrganizacionalSelecionada.getId()
								.toString());
				inserirRegistroAtendimentoActionForm
						.setDescricaoUnidade(unidadeOrganizacionalSelecionada
								.getDescricao());

				if (unidadeOrganizacionalSelecionada.getMeioSolicitacao() != null) {

					inserirRegistroAtendimentoActionForm
							.setMeioSolicitacao(unidadeOrganizacionalSelecionada
									.getMeioSolicitacao().getId().toString());
				}

				httpServletRequest.setAttribute("nomeCampo", "meioSolicitacao");

			} else {

				inserirRegistroAtendimentoActionForm.setUnidade("");
				inserirRegistroAtendimentoActionForm
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
				sessao.removeAttribute("colecaoSolicitacaoTipo");
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
			}
		}
			
		String pesquisarTipoSolicitacao = (String) httpServletRequest.getParameter("pesquisarTipoSolicitacao");
		if(pesquisarTipoSolicitacao != null && !pesquisarTipoSolicitacao.equals("")){ 
			
			sessao.removeAttribute("colecaoSolicitacaoTipo");
			sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
			
			if(inserirRegistroAtendimentoActionForm.getGrupoAtendimento() != null 
				&& !inserirRegistroAtendimentoActionForm.getGrupoAtendimento().equals("-1")) {
			
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
							FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID, 
							inserirRegistroAtendimentoActionForm.getGrupoAtendimento()));
		
					filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipo.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
		
					filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
							SolicitacaoTipo.INDICADOR_USO_SISTEMA_NAO));
		
					colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo,
							SolicitacaoTipo.class.getName());
		
					if(!Util.isVazioOrNulo(colecaoSolicitacaoTipo)) {
						sessao.setAttribute("colecaoSolicitacaoTipo",
								colecaoSolicitacaoTipo);
					}else{
						sessao.removeAttribute("colecaoSolicitacaoTipo");
					}
				}
			}
		}
			
		

		/*
		 * Especificação - Carregando a coleção que irá ficar disponível para
		 * escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados [SB0036] –
		 * Habilita/Desabilita Conta
		 */
		String pesquisarEspecificacao = httpServletRequest
				.getParameter("pesquisarEspecificacao");

		if (pesquisarEspecificacao != null
				&& !pesquisarEspecificacao.equalsIgnoreCase("")) {

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(
					FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
							new Integer(inserirRegistroAtendimentoActionForm
									.getTipoSolicitacao())));

			filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());

			if (Util.isVazioOrNulo(colecaoSolicitacaoTipoEspecificacao)) {
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
				//inserirRegistroAtendimentoActionForm.setDataPrevista("");
			} else {
				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);
			}

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
				&& inserirRegistroAtendimentoActionForm.getDataAtendimento() != null
				&& !inserirRegistroAtendimentoActionForm.getDataAtendimento()
						.equalsIgnoreCase("")
				&& !inserirRegistroAtendimentoActionForm
						.getEspecificacao()
						.equalsIgnoreCase(
								String
										.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			this.definirDataPrevistaUnidadeDestinoEspecificacao(
					inserirRegistroAtendimentoActionForm, fachada, sessao);
			

			//Definir Prazo repassado ao Cliente (ARPE)
			//[SB0054] - Define Prazo Repassado ao Cliente (ARPE)
			Date dataArpe = fachada.obterPrazoRepassadoClienteArpe(
					inserirRegistroAtendimentoActionForm.getEspecificacao(),Util.converteStringParaDate(inserirRegistroAtendimentoActionForm.getDataAtendimento()));
			
			inserirRegistroAtendimentoActionForm.setPrazoPrevistoArpe(Util.formatarData(dataArpe));

			// Verifica se o cliente é obrigatório
			if (fachada
					.clienteObrigatorioInserirRegistroAtendimento(new Integer(
							inserirRegistroAtendimentoActionForm
									.getEspecificacao()))) {

				inserirRegistroAtendimentoActionForm.setClienteObrigatorio("1");
			} else {
				inserirRegistroAtendimentoActionForm.setClienteObrigatorio("2");
			}

			httpServletRequest.setAttribute("nomeCampo", "observacao");
		}else{
			if(inserirRegistroAtendimentoActionForm.getEspecificacao() != null 
					&& inserirRegistroAtendimentoActionForm.getEspecificacao().equals("-1")){
				inserirRegistroAtendimentoActionForm.setDataPrevista("");
				inserirRegistroAtendimentoActionForm.setValorSugerido("");
			}
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
					.atribuirHoraCorrenteTempoEsperaFinal(inserirRegistroAtendimentoActionForm);
			httpServletRequest.setAttribute("nomeCampo", "unidade");
		}

		/*
		 * Para os casos que forem inserir RA para falta de água generalizada, o
		 * tipo e a especificação serão pré-determinados e não poderão ser
		 * altarados.
		 */
		if (httpServletRequest.getParameter("raFaltaAguaGeneralizada") != null) {

			sessao.setAttribute("generalizada", "OK");

			SolicitacaoTipoEspecificacao especificacao = fachada
					.pesquisarTipoEspecificacaoFaltaAguaGeneralizada();

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(
					FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
							especificacao.getSolicitacaoTipo().getId()));

			filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());

			sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
					colecaoSolicitacaoTipoEspecificacao);

			inserirRegistroAtendimentoActionForm
					.setTipoSolicitacao(especificacao.getSolicitacaoTipo()
							.getId().toString());
			inserirRegistroAtendimentoActionForm.setEspecificacao(especificacao
					.getId().toString());

			if (inserirRegistroAtendimentoActionForm.getDataAtendimento() != null
					&& !inserirRegistroAtendimentoActionForm
							.getDataAtendimento().equalsIgnoreCase("")) {
				this.definirDataPrevistaUnidadeDestinoEspecificacao(
						inserirRegistroAtendimentoActionForm, fachada, sessao);
			}
		}
		
		//Validar Campos Numericos
		if(inserirRegistroAtendimentoActionForm.getIdImovel() != null && !inserirRegistroAtendimentoActionForm.getIdImovel().equals("")){
			if(!Util.isInteger(inserirRegistroAtendimentoActionForm.getIdImovel())){
				inserirRegistroAtendimentoActionForm.setIdImovel("");
				inserirRegistroAtendimentoActionForm.setInscricaoImovel("");
			}
		}
		
		if(inserirRegistroAtendimentoActionForm.getIdLocalidade() != null && !inserirRegistroAtendimentoActionForm.getIdLocalidade().equals("")){
			if(!Util.isInteger(inserirRegistroAtendimentoActionForm.getIdLocalidade())){
				inserirRegistroAtendimentoActionForm.setIdLocalidade("");
				inserirRegistroAtendimentoActionForm.setDescricaoLocalidade("");
			}
		}
		

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Habilitar ou desabilitar os campos Tempo de Espera para Atendimento, Data
	 * do Atendimento e Hora do Atendimento
	 * 
	 * [SF0001] Habilita/Desabilita Dados do Momento do Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param InserirRegistroAtendimentoActionForm
	 * @return void
	 */
	private void habilitacaoDadosMomentoAtendimento(
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			HttpServletRequest httpServletRequest) {

		// On-line
		if (inserirRegistroAtendimentoActionForm.getTipo()
				.equalsIgnoreCase("1")) {
			Date dataCorrente = new Date();

			inserirRegistroAtendimentoActionForm.setNumeroAtendimentoManual("");
			inserirRegistroAtendimentoActionForm.setDataAtendimento(Util
					.formatarData(dataCorrente));
			inserirRegistroAtendimentoActionForm.setHora(Util
					.formatarHoraSemSegundos(dataCorrente));

			httpServletRequest.setAttribute("nomeCampo", "tempoEsperaInicial");
		}
		// Manual
		else {
			inserirRegistroAtendimentoActionForm.setDataAtendimento("");
			inserirRegistroAtendimentoActionForm.setHora("");
			inserirRegistroAtendimentoActionForm.setTempoEsperaFinal("");
			inserirRegistroAtendimentoActionForm.setDataPrevista("");

			httpServletRequest.setAttribute("nomeCampo",
					"numeroAtendimentoManual");
		}
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Atribui o valor correspondente à hora corrente
	 * 
	 * @author Raphael Rossiter
	 * @date 27/07/2006
	 * 
	 * @param InserirRegistroAtendimentoActionForm
	 * @return void
	 */
	private void atribuirHoraCorrenteTempoEsperaFinal(
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm) {

		Date dataCorrente = new Date();

		inserirRegistroAtendimentoActionForm.setTempoEsperaFinal(Util
				.formatarHoraSemSegundos(dataCorrente));
	}

	private void definirDataPrevistaUnidadeDestinoEspecificacao(
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			Fachada fachada, HttpSession sessao) {

		Date dataAtendimento = Util
				.converteStringParaDate(inserirRegistroAtendimentoActionForm
						.getDataAtendimento());

		DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper dataPrevistaUnidadeDestino = fachada
				.definirDataPrevistaUnidadeDestinoEspecificacao(
						dataAtendimento, new Integer(
								inserirRegistroAtendimentoActionForm
										.getEspecificacao()));

		if (dataPrevistaUnidadeDestino.getDataPrevista() != null) {
			inserirRegistroAtendimentoActionForm
					.setDataPrevista(Util
							.formatarData(dataPrevistaUnidadeDestino
									.getDataPrevista()));
		}

		if (dataPrevistaUnidadeDestino.getUnidadeOrganizacional() != null) {
			inserirRegistroAtendimentoActionForm
					.setIdUnidadeDestino(dataPrevistaUnidadeDestino
							.getUnidadeOrganizacional().getId().toString());
			inserirRegistroAtendimentoActionForm
					.setDescricaoUnidadeDestino(dataPrevistaUnidadeDestino
							.getUnidadeOrganizacional().getDescricao());
		}

		inserirRegistroAtendimentoActionForm
				.setIndFaltaAgua(dataPrevistaUnidadeDestino.getIndFaltaAgua());

		inserirRegistroAtendimentoActionForm
				.setIndMatricula(dataPrevistaUnidadeDestino.getIndMatricula());

		inserirRegistroAtendimentoActionForm
				.setImovelObrigatorio(dataPrevistaUnidadeDestino
						.getImovelObrigatorio());

		inserirRegistroAtendimentoActionForm
				.setPavimentoRuaObrigatorio(dataPrevistaUnidadeDestino
						.getPavimentoRuaObrigatorio());

		inserirRegistroAtendimentoActionForm
				.setPavimentoCalcadaObrigatorio(dataPrevistaUnidadeDestino
						.getPavimentoCalcadaObrigatorio());

		// Identificar tipo de geração da ordem de serviço (AUTOMÁTICA, OPCIONAL
		// ou NÃO GERAR)
		Integer idEspecificacao = Util
				.converterStringParaInteger(inserirRegistroAtendimentoActionForm
						.getEspecificacao());

		if (this.getFachada().gerarOrdemServicoAutomatica(idEspecificacao)) {

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao
					.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.ID,
							inserirRegistroAtendimentoActionForm
									.getEspecificacao()));

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroNaoNulo(
							FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO_ID));

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoSolicitacaoTipoEspecificacao = this.getFachada()
					.pesquisar(filtroSolicitacaoTipoEspecificacao,
							SolicitacaoTipoEspecificacao.class.getName());

			ServicoTipo servicoTipo = ((SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacao
					.iterator().next()).getServicoTipo();

			String valorServico = Util
					.formatarMoedaReal(servicoTipo.getValor());
			inserirRegistroAtendimentoActionForm.setValorSugerido(valorServico);

			sessao.setAttribute("servicoTipo", servicoTipo.getId());
		} else {
			sessao.removeAttribute("servicoTipo");
		}

	}
	
	/**
	 * [FS 0063] - Verificar Meio de Solicitação com Permissão Especial
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
