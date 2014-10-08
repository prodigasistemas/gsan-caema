/**
 * 
 */
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
package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.acesso.usuario.UsuarioAfastamento;
import gcom.seguranca.acesso.usuario.UsuarioAfastamentoMotivo;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

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
 * @date 08/06/2006
 */
public class BloquearDesbloquearAcessoUsuarioAction extends GcomAction {

	/**
	 * Este caso de uso permite bloquear ou desbloquear o acesso do usu�rio ao
	 * sistema.
	 * 
	 * [UC0291] Bloquear/Desbloquear Acesso Usu�rio
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 08/06/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm = (BloquearDesbloquearAcessoUsuarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Usuario usuario = new Usuario();

		/*
		 * [UC0107] Registrar Transa��o
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ACESSO_USUARIO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ACESSO_USUARIO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		usuario.setOperacaoEfetuada(operacaoEfetuada);
		usuario.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

		// [UC0107] -Fim- Registrar Transa��o
		
		
		
		String login = bloquearDesbloquearAcessoUsuarioActionForm.getLogin();

		if (login != null && !login.equals("")) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, login.toLowerCase()));

			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (colecaoUsuario == null || colecaoUsuario.isEmpty()) {
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + login + "");
			}
			Iterator<Usuario> colecaoUsuarioIterator = colecaoUsuario.iterator();
			
			while (colecaoUsuarioIterator.hasNext() ) {
				usuario = (Usuario) colecaoUsuarioIterator.next();
				
				if ( usuario.getLogin().equalsIgnoreCase(login) ){
					break;
				}
			}
		} else {
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Login");
		}
		
		int idAfastamento = Integer.parseInt(bloquearDesbloquearAcessoUsuarioActionForm.getIcAfastamentoTemporario());
		UsuarioAfastamento usuarioAfastamento = preencheUsuarioAfastamento(bloquearDesbloquearAcessoUsuarioActionForm, usuarioLogado);
		
		if (idAfastamento != 3){			
			/* RM 3892 - Inclus�o de dados de afastamento do usu�rio */
			String afastamento = httpServletRequest.getParameter("afastamento");
			if (afastamento!=null && afastamento.equals("sim")){				
				
				if (usuarioAfastamento.getId()==null || usuarioAfastamento.getId()==0){ //Inserir afastamento
					fachada.inserirAfastamento(usuarioAfastamento);
				} else { //Atualizar afastamento
					fachada.atualizarAfastamento(usuarioAfastamento);	
				}
			
				//Monta a Pagina de sucesso
				montarPaginaSucesso(httpServletRequest, "Dados do afastamento do usu�rio informados/mantidos com sucesso",
								"Bloquear/Desbloquear acesso de outro usu�rio",
								"exibirBloquearDesbloquearAcessoUsuarioAction.do?menu=sim");
		
				return retorno;
			}
			/* Fim RM 3892 */
		} else {			
			
			String idSituacaoUsuario = bloquearDesbloquearAcessoUsuarioActionForm.getUsuarioSituacao();
			if (idSituacaoUsuario != null
					&& !idSituacaoUsuario.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
				//Retira afastamento, se houver
				fachada.excluirAfastamentoVigente(usuarioAfastamento);
				
				FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
				filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
						FiltroUsuarioSituacao.ID, idSituacaoUsuario));
	
				Collection<UsuarioSituacao> colecaoUsarioSituacao = fachada.pesquisar(
						filtroUsuarioSituacao, UsuarioSituacao.class.getName());
	
				UsuarioSituacao usuarioSituacao = (UsuarioSituacao) colecaoUsarioSituacao
						.iterator().next();
				  
				if (usuario.getUsuarioSituacao().getId().equals(idSituacaoUsuario)) {
					throw new ActionServletException("atencao.login_nao_existente",
							null, "" + login + "");
				}
			
					if(usuario.getLogin().equalsIgnoreCase(login)) {
						usuario.setUsuarioSituacao(usuarioSituacao);
					}
			} else {
				 throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Situa��o do Usu�rio");
			}
	
			registradorOperacao.registrarOperacao(usuario);
			
			fachada.bloquearDesbloquearUsuarioSituacao(usuario);
		}		

		//Monta a Pagina de sucesso
		montarPaginaSucesso(httpServletRequest, usuario.getUsuarioSituacao()
				.getDescricaoUsuarioSituacao()
				+ " " + "efetuada com sucesso.",
				"Bloquear/Desbloquear acesso de outro usu�rio",
				"exibirBloquearDesbloquearAcessoUsuarioAction.do?menu=sim");

		return retorno;
	}

	private UsuarioAfastamento preencheUsuarioAfastamento(BloquearDesbloquearAcessoUsuarioActionForm form, Usuario usuarioLogado) {
        UsuarioAfastamento retorno = new UsuarioAfastamento(); 
        FiltroUsuario filtroUsuario = new FiltroUsuario();
        filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLogin().toLowerCase()));
        Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
        retorno.setUsuarioAfastado((Usuario) Util.retonarObjetoDeColecao(colecaoUsuario));
        retorno.setIcAfastamentoTemporario(new Short(form.getIcAfastamentoTemporario()));
        
        UsuarioAfastamentoMotivo usuarioAfastamentoMotivo = new UsuarioAfastamentoMotivo();
        if (retorno.getIcAfastamentoTemporario().intValue()!=3){
            usuarioAfastamentoMotivo.setId(Integer.parseInt(form.getMotivoAfastamento()));
            retorno.setUsuarioAfastamentoMotivo(usuarioAfastamentoMotivo);
            retorno.setDtAfastamentoInicial(Util.converteStringParaDate(form.getDtAfastamentoInicial()));
            retorno.setDtAfastamentoFinal(Util.converteStringParaDate(form.getDtAfastamentoFinal()));
        }
        if (form.getIdUsuarioEspelho()!=null && !form.getIdUsuarioEspelho().equals("")){
			FiltroUsuario filtroUsuarioEspelho = new FiltroUsuario();
			filtroUsuarioEspelho.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, form.getIdUsuarioEspelho().toLowerCase()));

			Collection<Usuario> colecaoUsuarioEspelho = Fachada.getInstancia().pesquisar(filtroUsuarioEspelho,
					Usuario.class.getName());
			if(colecaoUsuarioEspelho != null && !colecaoUsuarioEspelho.isEmpty()){
				Usuario userEspelho = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioEspelho);
	            retorno.setUsuarioEspelho(userEspelho);
			}else{
				throw new ActionServletException("atencao.login_nao_existente",
					null, "" + form.getIdUsuarioEspelho() + "");
			}
            
        }
        
        retorno.setUsuarioInformante(usuarioLogado);
        retorno.setDescricaoObservacao(form.getObservacaoAfastamento());
        if (form.getIdAfastamento()!=null){
            retorno.setId(form.getIdAfastamento());
        }
        return retorno;
    }

}
