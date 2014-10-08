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
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
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
		 * RM7344 - Ajustes nas funcionalidades de Segurança de Acesso
		 * Verifica se o usuário tem acesso a permissão especial REVALIDAR USUARIO
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