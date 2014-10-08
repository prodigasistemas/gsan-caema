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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.bean.ExibirRevalidarUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirRevalidarUsuarioAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	
	/**
	 * @author Fernanda Almeida
	 * 
	 * @date 28/01/2012
	 *   
	 *   */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		/**
		 * RM7344 - Ajustes nas funcionalidades de Seguran�a de Acesso
		 * Verifica se o usu�rio tem acesso a permiss�o especial REVALIDAR USUARIO
		 */
		boolean possuiPermissao = 
    			Fachada.getInstancia().verificarPermissaoEspecialAtiva(
    					PermissaoEspecial.REVALIDAR_USUARIO,usuarioLogado);
		if (!possuiPermissao){
			throw new ActionServletException("atencao.usuario_sem_permissao_revalidar");	
		}
	
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();	
		
		if(sistemaParametro.getPeriodoRevalidarSenha() == null){
			throw new ActionServletException("atencao.usuario.sem.inferiores");			
		}
		
		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("exibirRevalidarUsuario");		
		
		Collection<ExibirRevalidarUsuarioHelper> colecaoUsuario = null;			
		
		//Collection<ExibirRevalidarUsuarioHelper> helperPrincipal = new ArrayList<ExibirRevalidarUsuarioHelper>();
		ExibirRevalidarUsuarioHelper helperPrincipal = new ExibirRevalidarUsuarioHelper();
		helperPrincipal.setUsuario(usuarioLogado);
		
		if(httpServletRequest.getParameter("id")!=null){
			Usuario userPesquisado =null;
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, httpServletRequest.getParameter("id")));
			Collection<Usuario> colecao= this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName());
			for(Usuario usuario : colecao){
				userPesquisado = usuario;
			}
			if(userPesquisado!=null){
				colecaoUsuario = this.getFachada().buscaUsuariosFilhosRevalidar(userPesquisado);
				sessao.setAttribute("usuarioPai", userPesquisado);
			}
		}
		else{
			colecaoUsuario = this.getFachada().buscaUsuariosFilhosRevalidar(usuarioLogado);	
			sessao.setAttribute("usuarioPai", usuarioLogado);
			
		}
		
		if (colecaoUsuario!=null) {
			sessao.setAttribute("colecaoUsuario", colecaoUsuario);
		}
		
			
		if (colecaoUsuario.size()==0) {
			throw new ActionServletException("atencao.usuario.sem.inferiores");
		}

		return retorno;
	}

	
}