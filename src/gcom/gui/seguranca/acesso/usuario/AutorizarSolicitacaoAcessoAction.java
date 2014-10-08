/**
 * 
 */
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
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓ“SITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.EnvioEmail;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoMotivoNaoAutorizacao;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoMotivoNaoAutorizacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

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
 * [UC1093] Manter Solicitação de Acesso.
 * 
 * @author Hugo Leonardo
 * @date 17/11/2010
 */
public class AutorizarSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosAutorizar();

		// mensagem de erro quando o usuário tenta Atualizar sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();

		Collection idsSolicitacaoAcesso = new ArrayList(ids.length);

		for (int i = 0; i < ids.length; i++) {
			idsSolicitacaoAcesso.add(new Integer(ids[i]));
		}

		filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimplesIn(FiltroSolicitacaoAcesso.ID, idsSolicitacaoAcesso));
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.USUARIO_TIPO);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.EMPRESA);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.FUNCIONARIO);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.FUNCIONARIO_SOLICITANTE);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.USUARIO_ABRANGENCIA);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.UNIDADE_ORGANIZACIONAL);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.GERENCIA_REGIONAL);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.UNIDADE_NEGOCIO);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.LOCALIDADE);
		
		Collection<SolicitacaoAcesso> coletionSolicitacaoAcesso = Fachada
				.getInstancia().pesquisar(filtroSolicitacaoAcesso,
						SolicitacaoAcesso.class.getName());

		Iterator<SolicitacaoAcesso> itera = coletionSolicitacaoAcesso
				.iterator();

		String naoAutorizar = httpServletRequest.getParameter("naoautorizar");

		while (itera.hasNext()) {

			SolicitacaoAcesso solicitacaoAcesso = (SolicitacaoAcesso) itera
					.next();

			solicitacaoAcesso.setUltimaAlteracao(new Date());
			solicitacaoAcesso.setDataAutorizacao(new Date());

			// RM 2551 - Não autorização uma solicitação de acesso
			if (naoAutorizar != null && !naoAutorizar.equals("")) {
				// Buscando motivo solicitacao acesso
				FiltroSolicitacaoAcessoMotivoNaoAutorizacao filtroSolicitacaoAcessoMotivoNaoAutorizacao = new FiltroSolicitacaoAcessoMotivoNaoAutorizacao();
				filtroSolicitacaoAcessoMotivoNaoAutorizacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoAcessoMotivoNaoAutorizacao.ID,
								naoAutorizar));
				Collection<SolicitacaoAcessoMotivoNaoAutorizacao> motivos = Fachada
						.getInstancia().pesquisar(
								filtroSolicitacaoAcessoMotivoNaoAutorizacao,
								SolicitacaoAcessoMotivoNaoAutorizacao.class
										.getName());
				solicitacaoAcesso
						.setSolicitacaoAcessoMotivoNaoAutorizacao((SolicitacaoAcessoMotivoNaoAutorizacao) Util
								.retonarObjetoDeColecao(motivos));

				// Situacao de acesso = 5 (NAO AUTORIZADO)
				FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
				filtroSolicitacaoAcessoSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO,
								SolicitacaoAcessoSituacao.NAO_AUTORIZADO));
				Collection<SolicitacaoAcessoSituacao> colecaoSolicitacaoAcessoSituacao = this
						.getFachada().pesquisar(
								filtroSolicitacaoAcessoSituacao,
								SolicitacaoAcessoSituacao.class.getName());
				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) Util
						.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
				solicitacaoAcesso
						.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);

				Fachada.getInstancia().atualizar(solicitacaoAcesso);

				//RM7146 - Solicitante recebe e-mail informando não autorização
				
				//Busca usuário solicitante
				Usuario usuarioSolicitante = null;
				FiltroUsuario filtroUsuarioSolicitante = new FiltroUsuario(); 
				filtroUsuarioSolicitante.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, 
						solicitacaoAcesso.getUsuarioSolicitante().getId()));
				Collection<Usuario> colecaoUsuarioSolicitante = Fachada.getInstancia().pesquisar(
						filtroUsuarioSolicitante, Usuario.class.getName());

				if (!Util.isVazioOrNulo(colecaoUsuarioSolicitante)) {
					usuarioSolicitante = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioSolicitante);
					
					if (usuarioSolicitante!=null){
						// Envia e-mail para o usuario solicitante
						if (usuarioSolicitante.getDescricaoEmail() != null
								&& !usuarioSolicitante.getDescricaoEmail().equals("")) {
							String mensagem = "O usuário de Login: " + solicitacaoAcesso.getLogin() 
								+ ", solicitado por você, não foi autorizado pelo seguinte motivo: "
								+ solicitacaoAcesso.getSolicitacaoAcessoMotivoNaoAutorizacao().getDescricao();
							EnvioEmail envioEmail = Fachada.getInstancia().pesquisarEnvioEmail(EnvioEmail.INSERIR_USUARIO);

							try {
								ServicosEmail.enviarMensagem(envioEmail.getEmailRemetente(), usuarioSolicitante
										.getDescricaoEmail(), 
										envioEmail.getTituloMensagem(), mensagem);
							} catch (ErroEmailException e) {
								//throw new ControladorException("erro.envio.mensagem");
							}
						}
					}
				}
				
			} else {

				FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
				filtroSolicitacaoAcessoSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO,
								SolicitacaoAcessoSituacao.CAD_POR_AUTORIZACAO));
				filtroSolicitacaoAcessoSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoAcessoSituacao.INDICADOR_USO,
								ConstantesSistema.SIM));
				Collection<SolicitacaoAcessoSituacao> colecaoSolicitacaoAcessoSituacao = this
						.getFachada().pesquisar(
								filtroSolicitacaoAcessoSituacao,
								SolicitacaoAcessoSituacao.class.getName());

				if (!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)) {
					SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) Util
							.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
					solicitacaoAcesso
							.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
				} else {

					throw new ActionServletException(
							"atencao.solicitacao_acesso_situacao.cad_por_autorizacao");
				}

				SistemaParametro sistemaParametro = Fachada.getInstancia()
						.pesquisarParametrosDoSistema();
				
				//Preenche obj usuario conforme solicitacao de acesso
				Usuario usuarioCadastro = null;
				
				//Verifica se o usuario já é cadastrado
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				if(solicitacaoAcesso.getUsuario() != null && solicitacaoAcesso.getUsuario().getId() != null){
					filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, solicitacaoAcesso.getUsuario().getId()));
				}else{
					filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, solicitacaoAcesso.getLogin()));
				}
				
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.FUNCIONARIO);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_SITUACAO);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.EMPRESA);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.GERENCIA_REGIONAL);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.LOCALIDADE_ELO);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.LOCALIDADE);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_TIPO);
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_ABRANGENCIA);		
				Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario,Usuario.class.getName());
				if (colecaoUsuario.size()!=0){
					usuarioCadastro = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				}
				
				//Caso o usuário esteja com situação igual a INATIVO
				Usuario novoUsuario  = null;
				if(usuarioCadastro!=null && usuarioCadastro.getUsuarioSituacao().getId().equals(UsuarioSituacao.INATIVO)){
					novoUsuario = preencheUsuario(solicitacaoAcesso,usuarioCadastro);					
				} else {
					usuarioCadastro = preencheUsuario(solicitacaoAcesso,usuarioCadastro);	
				}
				
				Integer[] idGrupos;
				
				idGrupos = Fachada.getInstancia().pesquisarSolicitacaoAcessoGrupo(solicitacaoAcesso.getId());				
				
				if (colecaoUsuario.size()==0 && novoUsuario==null){
					Integer id = Fachada.getInstancia().inserirUsuario(usuarioCadastro, idGrupos, (Usuario) sessao.getAttribute("usuarioLogado"), solicitacaoAcesso.getId().toString());
					usuarioCadastro.setId(id);
					solicitacaoAcesso.setUsuario(usuarioCadastro);
				} else {
					if (novoUsuario==null){
						Fachada.getInstancia().atualizarUsuario(usuarioCadastro, idGrupos, "2", (Usuario) sessao.getAttribute("usuarioLogado"));
						solicitacaoAcesso.setUsuario(usuarioCadastro);
					} else {
						//O sistema atualiza o usuário anterior LOGIN = LOGIN* e CPF = null
						String loginAnterior = usuarioCadastro.getLogin();
						String login = loginAnterior;
						if (login.length()==11){
							login = login.substring(0, 10);
						}
						login += "*";
						usuarioCadastro.setLogin(login);
						String cpf = usuarioCadastro.getCpf();
						usuarioCadastro.setCpf(null);
						Fachada.getInstancia().atualizar(usuarioCadastro);
								
						//Altera situacao para ATIVO
						UsuarioSituacao situacao = new UsuarioSituacao();
						situacao.setId(UsuarioSituacao.ATIVO);
						novoUsuario.setUsuarioSituacao(situacao);
						novoUsuario.setCpf(cpf);
						//Inseri novo usuario
						novoUsuario.setLogin(loginAnterior);
						Integer id = Fachada.getInstancia().inserirUsuario(novoUsuario, idGrupos, (Usuario) sessao.getAttribute("usuarioLogado"), solicitacaoAcesso.getId().toString());
						novoUsuario.setId(id);
						solicitacaoAcesso.setUsuario(novoUsuario);
					}
				}
				
				//Inserir Permissoes Especiais
				//Metodo que obtem as permissoes especiais da solicitacao de acesso e inseri efetivamente para o usuario
				Fachada.getInstancia().inserirPermissoesUsuario(usuarioCadastro.getId(), solicitacaoAcesso.getId());
				
				if (sistemaParametro.getIcModulosSeguranca() == 1) {
					/**
					 * [UC1093] Manter Solicitação de Acesso [FS-0011] Clonar
					 * usuário GSAN na base
					 */
					Fachada.getInstancia().clonarUsuario(solicitacaoAcesso,
							(Usuario) sessao.getAttribute("usuarioLogado"), usuarioCadastro);
				} else {
					Fachada.getInstancia().atualizar(solicitacaoAcesso);
					
				}
				
				Fachada.getInstancia().enviaEmailUsuarioESolicitante(usuarioCadastro, solicitacaoAcesso.getId().toString());
				
				//Busca usuário solicitante
				Usuario usuarioSolicitante = null;
				FiltroUsuario filtroUsuarioSolicitante = new FiltroUsuario(); 
				filtroUsuarioSolicitante.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, 
						solicitacaoAcesso.getUsuarioSolicitante().getId()));
				Collection<Usuario> colecaoUsuarioSolicitante = Fachada.getInstancia().pesquisar(
						filtroUsuarioSolicitante, Usuario.class.getName());
				
				if (!Util.isVazioOrNulo(colecaoUsuarioSolicitante)) {
					usuarioSolicitante = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioSolicitante);
					
					if (usuarioSolicitante!=null){
				
						String mensagem = "O usuário de Login: " + solicitacaoAcesso.getLogin() 
								+ ", solicitado por você, foi autorizado.";
						EnvioEmail envioEmail = Fachada.getInstancia().pesquisarEnvioEmail(EnvioEmail.INSERIR_USUARIO);
		
						try {
							ServicosEmail.enviarMensagem(envioEmail.getEmailRemetente(), usuarioSolicitante
									.getDescricaoEmail(), 
									envioEmail.getTituloMensagem(), mensagem);
						} catch (ErroEmailException e) {
							//throw new ControladorException("erro.envio.mensagem");
						}
					}
				}
			}
		}

		Integer idQt = ids.length;

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Solicitação(ões) de Acesso(s) Autorizada(s) com sucesso.",
				"Manter outra Solicitação de Acesso",
				"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim");

		if (sessao.getAttribute("objeto") != null) {
			String objeto = (String) sessao.getAttribute("objeto");

			if (objeto.equals("atualizar")) {

				montarPaginaSucesso(
						httpServletRequest,
						idQt.toString()
								+ " Solicitação de Acesso(s) Autorizada(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=atualizar");

			} else if (objeto.equals("autorizar")) {
				if (naoAutorizar != null && !naoAutorizar.equals("")) {

					montarPaginaSucesso(
							httpServletRequest,
							idQt.toString()
									+ " Solicitação de Acesso(s) Não Autorizada(s) com sucesso.",
							"Manter outra Solicitação de Acesso",
							"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=autorizar");
				} else {
					montarPaginaSucesso(
							httpServletRequest,
							idQt.toString()
									+ " Solicitação de Acesso(s) Autorizada(s) com sucesso.",
							"Manter outra Solicitação de Acesso",
							"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=autorizar");
				}

			} else if (objeto.equals("cadastrar")) {

				montarPaginaSucesso(
						httpServletRequest,
						idQt.toString()
								+ " Solicitação de Acesso(s) Autorizada(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=cadastrar");
			}
		}

		return retorno;
	}

	
private Usuario preencheUsuario(SolicitacaoAcesso solicitacaoAcesso,Usuario usuarioCadastrado) {
		
		if(usuarioCadastrado == null || usuarioCadastrado.equals("")){
			usuarioCadastrado = new Usuario();
			usuarioCadastrado.setNumeroAcessos(0);
			usuarioCadastrado.setBloqueioAcesso(new Short("0"));
			usuarioCadastrado.setIndicadorUsuarioBatch(new Short("2"));
			usuarioCadastrado.setIndicadorUsuarioInternet(new Short("2"));
			usuarioCadastrado.setDataCadastroAcesso(new Date());
		}

		usuarioCadastrado.setLogin(solicitacaoAcesso.getLogin());
		
		usuarioCadastrado.setDataCadastroInicio(solicitacaoAcesso.getPeriodoInicial());
		usuarioCadastrado.setDataCadastroFim(solicitacaoAcesso.getPeriodoFinal());
		usuarioCadastrado.setDescricaoEmail(solicitacaoAcesso.getEmail());
		usuarioCadastrado.setUltimaAlteracao(new Date());
		// caso o tipo de usuário tenha sido alterado, então colocar o usuário para senha pendente
		if(usuarioCadastrado != null && usuarioCadastrado.getUsuarioTipo() != null &&
				!usuarioCadastrado.getUsuarioTipo().getId().equals(solicitacaoAcesso.getUsuarioTipo().getId())){
			UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
			usuarioSituacao.setId(UsuarioSituacao.PENDENTE_SENHA);
			usuarioCadastrado.setUsuarioSituacao(usuarioSituacao);
			
			String senhaGerada = "gcom";
			String senhaCriptografada = null;
			try {
				senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
			} catch (ErroCriptografiaException e1) {
				throw new ActionServletException(
						"erro.criptografia.senha");
			}
			usuarioCadastrado.setSenha(senhaCriptografada);
		}
		usuarioCadastrado.setUsuarioTipo(solicitacaoAcesso.getUsuarioTipo());
		usuarioCadastrado.setFuncionario(solicitacaoAcesso.getFuncionario());
		usuarioCadastrado.setUsuarioAbrangencia(solicitacaoAcesso.getUsuarioAbrangencia());
		usuarioCadastrado.setGerenciaRegional(solicitacaoAcesso.getGerenciaRegional());
		usuarioCadastrado.setUnidadeNegocio(solicitacaoAcesso.getUnidadeNegocio());
		//usuarioCadastrado.setLocalidadeElo(solicitacaoAcesso.getLocalidadeElo());
		usuarioCadastrado.setLocalidade(solicitacaoAcesso.getLocalidade());
		usuarioCadastrado.setEmpresa(solicitacaoAcesso.getEmpresa());
		usuarioCadastrado.setUnidadeOrganizacional(solicitacaoAcesso.getUnidadeOrganizacional());
		usuarioCadastrado.setNomeUsuario(solicitacaoAcesso.getNomeUsuario());
		usuarioCadastrado.setDataNascimento(solicitacaoAcesso.getDataNascimento());
		usuarioCadastrado.setCpf(solicitacaoAcesso.getCpf());
		usuarioCadastrado.setCompetenciaRetificacao(solicitacaoAcesso.getCompetenciaRetificacao());
		
				
		return usuarioCadastrado;
	}
}
