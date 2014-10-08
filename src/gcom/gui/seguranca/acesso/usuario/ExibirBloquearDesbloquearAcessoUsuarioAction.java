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
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAfastamentoMotivo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAfastamento;
import gcom.seguranca.acesso.usuario.UsuarioAfastamentoMotivo;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 08/06/2006
 */
public class ExibirBloquearDesbloquearAcessoUsuarioAction extends GcomAction {

	/**
	 * Este caso de uso permite bloquear ou desbloquear o acesso do usuário ao sistema.
	 * 
	 * [UC0291] Bloquear/Desbloquear Acesso
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/06/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws ControladorException
	 */

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ControladorException {

		//Inicializando variaveis
		ActionForward retorno = actionMapping.findForward("bloquearDesbloquearAcessoUsuario");
		BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm = (BloquearDesbloquearAcessoUsuarioActionForm) actionForm;
		bloquearDesbloquearAcessoUsuarioActionForm.setErroUsuarioEspelho(null);
		httpServletRequest.removeAttribute("usuarioPrincipalNaoEncontrado");
		httpServletRequest.removeAttribute("nomeCampo");
		
		//Busca colecao de situacoes
		buscarUsuarioSituacao(httpServletRequest);
		
		//Buscar Colecao de motivos de afastamento 
		buscarMotivosAfastamento(httpServletRequest, bloquearDesbloquearAcessoUsuarioActionForm);
		
		String limpar = httpServletRequest.getParameter("limpar");
		if (limpar!=null && limpar.equals("sim")){
			limparForm(bloquearDesbloquearAcessoUsuarioActionForm);
			return retorno;
		}
		
		String objConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		if (objConsulta!=null) {
			if (objConsulta.equals("1")){ //Pesquisar usuario	
				this.pesquisarUsuario(httpServletRequest, bloquearDesbloquearAcessoUsuarioActionForm);				
			} else if (objConsulta.equals("2")){ //Pesquisar usuario espelho
				this.pesquisarUsuarioEspelho(bloquearDesbloquearAcessoUsuarioActionForm, httpServletRequest);
			}
		}		
		
		return retorno;
	}

	/**
	 * Pesquisa usuário e seta os campos correspondentes do form
	 * @param form
	 * @param httpServletRequest
	 */
	private void pesquisarUsuario(HttpServletRequest httpServletRequest, 
			BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm) throws ControladorException {
		
		Usuario usuario = new Usuario();
		
		String login =  bloquearDesbloquearAcessoUsuarioActionForm.getLogin();
		bloquearDesbloquearAcessoUsuarioActionForm.setIdAfastamento(null);
		bloquearDesbloquearAcessoUsuarioActionForm.setNomeUsuarioEspelho("");
		
		if (login != null && !login.equals("")) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, login.toLowerCase()));

			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
						
			if (colecaoUsuario == null || colecaoUsuario.isEmpty()) {
				
				httpServletRequest.setAttribute("usuarioPrincipalNaoEncontrado", "exception");
				httpServletRequest.setAttribute("nomeCampo", "login");
				
				bloquearDesbloquearAcessoUsuarioActionForm.setNomeUsuario("USUÁRIO INEXISTENTE");
				bloquearDesbloquearAcessoUsuarioActionForm.setLogin(" ");
				
			} else {
				Iterator<Usuario> colecaoUsuarioIterator = colecaoUsuario.iterator(); 
					while( colecaoUsuarioIterator.hasNext() ){
						usuario = (Usuario) colecaoUsuarioIterator.next();
					
						if ( usuario.getLogin().equalsIgnoreCase(login) ) {
							bloquearDesbloquearAcessoUsuarioActionForm
								.setUsuarioSituacao(usuario.getUsuarioSituacao()
										.getId().toString());
							//RM 7146
							bloquearDesbloquearAcessoUsuarioActionForm.setNomeUsuario(usuario.getNomeUsuario());
						}
					}
				
				if (bloquearDesbloquearAcessoUsuarioActionForm.getIdAfastamento()==null || bloquearDesbloquearAcessoUsuarioActionForm.getIdAfastamento()==0){
					UsuarioAfastamento usuarioAfastamento = Fachada.getInstancia().pesquisarAfastamentoVigente(usuario.getId());
					if (usuarioAfastamento!=null){
						bloquearDesbloquearAcessoUsuarioActionForm.setIcAfastamentoTemporario(usuarioAfastamento.getIcAfastamentoTemporario().toString());
						bloquearDesbloquearAcessoUsuarioActionForm.setMotivoAfastamento(usuarioAfastamento.getUsuarioAfastamentoMotivo().getId().toString());
						bloquearDesbloquearAcessoUsuarioActionForm.setDtAfastamentoInicial(Util.formatarData(usuarioAfastamento.getDtAfastamentoInicial()));
						bloquearDesbloquearAcessoUsuarioActionForm.setDtAfastamentoFinal(Util.formatarData(usuarioAfastamento.getDtAfastamentoFinal()));
						bloquearDesbloquearAcessoUsuarioActionForm.setDtAfastamentoFinalAux(Util.formatarData(usuarioAfastamento.getDtAfastamentoFinal()));
						bloquearDesbloquearAcessoUsuarioActionForm.setObservacaoAfastamento(usuarioAfastamento.getDescricaoObservacao());
						bloquearDesbloquearAcessoUsuarioActionForm.setIdAfastamento(usuarioAfastamento.getId());
						if (usuarioAfastamento.getUsuarioEspelho()!=null){
							bloquearDesbloquearAcessoUsuarioActionForm.setIdUsuarioEspelho(usuarioAfastamento.getUsuarioEspelho().getLogin());
							bloquearDesbloquearAcessoUsuarioActionForm.setNomeUsuarioEspelho(usuarioAfastamento.getUsuarioEspelho().getNomeUsuario());
						} else {
							bloquearDesbloquearAcessoUsuarioActionForm.setIdUsuarioEspelho("");
							bloquearDesbloquearAcessoUsuarioActionForm.setNomeUsuarioEspelho("");
						}
					}else {
						bloquearDesbloquearAcessoUsuarioActionForm.setIdAfastamento(0);
					}
					
					//Atualizar
					buscarMotivosAfastamento(httpServletRequest, bloquearDesbloquearAcessoUsuarioActionForm);
				}
			}
		}
	}

	/**
	 * Busca colecao de situacoes e seta no request
	 * @param httpServletRequest
	 * @param fachada
	 */
	private void buscarUsuarioSituacao(HttpServletRequest httpServletRequest) {
		
		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();		
		filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(FiltroUsuarioSituacao.INDICADOR_DE_USO_SISTEMA, "2"));
		Collection<UsuarioSituacao> colecaoUsuarioSituacao = Fachada.getInstancia().pesquisar(
				filtroUsuarioSituacao, UsuarioSituacao.class.getName());
		if (colecaoUsuarioSituacao == null || colecaoUsuarioSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Usuário Situação");
		}
		httpServletRequest.setAttribute("colecaoUsuarioSituacao", colecaoUsuarioSituacao);
	}

	/**
	 * Busca colecao de motivos afastamento e seta no request
	 * @param httpServletRequest
	 * @param fachada
	 * @param bloquearDesbloquearAcessoUsuarioActionForm
	 */
	private void buscarMotivosAfastamento(HttpServletRequest httpServletRequest,BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm) {
		
		FiltroUsuarioAfastamentoMotivo filtroUsuarioAfastamentoMotivo = new FiltroUsuarioAfastamentoMotivo();
		filtroUsuarioAfastamentoMotivo.adicionarParametro(new ParametroSimples(FiltroUsuarioAfastamentoMotivo.INDICADOR_USO, 1));
		if (bloquearDesbloquearAcessoUsuarioActionForm.getIcAfastamentoTemporario()!=null && 
				!bloquearDesbloquearAcessoUsuarioActionForm.getIcAfastamentoTemporario().equals("3")){
			filtroUsuarioAfastamentoMotivo.adicionarParametro(new ParametroSimples(FiltroUsuarioAfastamentoMotivo.INDICADOR_AFASTAMENTO_TEMPORARIO,
					bloquearDesbloquearAcessoUsuarioActionForm.getIcAfastamentoTemporario()));
		}
		
		Collection<UsuarioAfastamentoMotivo> colecaoMotivoAfastamento = Fachada.getInstancia().pesquisar(filtroUsuarioAfastamentoMotivo,
				UsuarioAfastamentoMotivo.class.getName());
		if (colecaoMotivoAfastamento == null || colecaoMotivoAfastamento.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Usuário Afastamento Motivo");
		}
		httpServletRequest.setAttribute("colecaoMotivoAfastamento", colecaoMotivoAfastamento);
	}
	
	
	private void limparForm(BloquearDesbloquearAcessoUsuarioActionForm form) {
		form.setLogin("");
		form.setUsuarioSituacao("");
		form.setIcAfastamentoTemporario("3");
		form.setMotivoAfastamento("");
		form.setDtAfastamentoInicial("");
		form.setDtAfastamentoFinal("");
		form.setIdUsuarioEspelho("");
		form.setNomeUsuarioEspelho("");
		form.setObservacaoAfastamento("");
		form.setNomeUsuario("");
		form.setUsuarioNaoEncontrado("true");
		form.setIdAfastamento(null);
	}

	/**
	 * Pesquisa usuário espelho e setar os campos correspondentes do form
	 * @param form
	 * @param httpServletRequest
	 */
	private void pesquisarUsuarioEspelho(BloquearDesbloquearAcessoUsuarioActionForm form, HttpServletRequest httpServletRequest) {
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuarioEspelho()));
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
		Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		Usuario user = null;
		
		if (colecaoUsuario.size()!=0){
			user = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			form.setNomeUsuarioEspelho(user.getNomeUsuario());
			form.setIdUsuarioEspelho(user.getLogin());
			validaSituacaoUsuarioEspelho(user, form);
			if (form.getErroUsuarioEspelho()==null){
				httpServletRequest.setAttribute("nomeCampo", "observacaoAfastamento");
			} else {
				httpServletRequest.setAttribute("nomeCampo", "idUsuarioEspelho");
			}
		} else {
			form.setNomeUsuarioEspelho("USUÁRIO INEXISTENTE");
			form.setIdUsuarioEspelho(" ");
			httpServletRequest.setAttribute("usuarioNaoEncontrado", "exception");
			httpServletRequest.setAttribute("nomeCampo", "idUsuarioEspelho");
		}
		
	}

	/* [UC0291] Bloquear/Desbloquear Acesso
	 * [FS0008] Validar Usuário Espelho 
	 * RM 3892 - Inclusão de dados de afastamento do usuário
	 */
	private void validaSituacaoUsuarioEspelho(Usuario user, BloquearDesbloquearAcessoUsuarioActionForm form) {
		UsuarioSituacao situacao = user.getUsuarioSituacao();
		
		if (situacao.getId()==4){
			form.setErroUsuarioEspelho("Usuário espelho informado foi afastado definitivamente do sistema.");
		} else if (situacao.getId()==5){
			form.setErroUsuarioEspelho("Usuário espelho informado está afastado temporariamente do sistema.");
		} else if (situacao.getId()!=1){
			form.setErroUsuarioEspelho("Usuário espelho informado não está ATIVO.");
		} else {
			form.setErroUsuarioEspelho(null);
		}
		if (form.getErroUsuarioEspelho()!=null){
			form.setNomeUsuarioEspelho("");
			form.setIdUsuarioEspelho("");
			user.setUsuarioSituacao(null);
		}
	}	

}