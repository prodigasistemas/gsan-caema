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
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da inser��o de um R.A (Aba n� 01 - Dados gerais)
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
		
		// Usu�rio logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		/*
		 * Tipo de Atendimento (exibir a tela com a op��o �on-line� selecionada
		 * e permitir que o usu�rio selecione entre �on-line� ou �manual�)
		 * 
		 * [SB0001 - Habilita/Desabilita Dados do Momento do Atendimento]
		 */
		if (inserirRegistroAtendimentoActionForm.getTipo() == null
				|| inserirRegistroAtendimentoActionForm.getTipo()
						.equalsIgnoreCase("")) {

			inserirRegistroAtendimentoActionForm.setTipo("1");

			/*
			 * Unidade de Atendimento (exibir a tela com a unidade associada ao
			 * usu�rio que estiver efetuando o registro de atendimento. (Tela
			 * inicial)
			 * 
			 * Meio de Solicita��o (exibir na tela com o meio de solicita��o
			 * associado � unidade de atendimento)
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
		 * Unidade de Atendimento (Permite que o usu�rio informe ou selecione
		 * outra)
		 * 
		 * [FS0004] - Verificar exist�ncia da unidade de atendimento
		 * 
		 * [FS0033] - Verificar autoriza��o da unidade de atendimento para
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
		 * Meio de Solicita��o - Carregando a cole��o que ir� ficar dispon�vel
		 * para escolha do usu�rio
		 * 
		 * [FS0003] - Verificar exist�ncia de dados
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
		 * Grupo de Atendimento - Carregando a cole��o que ir� ficar dispon�vel
		 * para escolha do usu�rio
		 * 
		 * [FS0003] - Verificar exist�ncia de dados
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
				 * Tipo de Solicita��o - Carregando a cole��o que ir� ficar dispon�vel
				 * para escolha do usu�rio
				 * 
				 * [FS0003] - Verificar exist�ncia de dados
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
		 * Especifica��o - Carregando a cole��o que ir� ficar dispon�vel para
		 * escolha do usu�rio
		 * 
		 * [FS0003] - Verificar exist�ncia de dados [SB0036] �
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
		 * Data Prevista - (exibir a data prevista calculada no SB0003 e n�o
		 * permitir altera��o).
		 * 
		 * [SB0003 - Define Data Prevista e Unidade Destino da Especifica��o]
		 * [FS0018] - Verificar exist�ncia da unidade centralizadora
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

			// Verifica se o cliente � obrigat�rio
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
		 * correspondente � hora corrente e n�o permitir altera��o
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
		 * Para os casos que forem inserir RA para falta de �gua generalizada, o
		 * tipo e a especifica��o ser�o pr�-determinados e n�o poder�o ser
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
	 * Atribui o valor correspondente � hora corrente
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

		// Identificar tipo de gera��o da ordem de servi�o (AUTOM�TICA, OPCIONAL
		// ou N�O GERAR)
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
	 * [FS 0063] - Verificar Meio de Solicita��o com Permiss�o Especial
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
