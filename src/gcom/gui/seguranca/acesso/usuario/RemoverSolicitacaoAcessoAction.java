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
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoPermissao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoPermissao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1093] Manter Solicita��o de Acesso.
 * 
 * @author Hugo Leonardo
 * @date 17/11/2010
 */
public class RemoverSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();
		
		// mensagem de erro quando o usu�rio tenta Atualizar sem ter selecionado
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
		
		filtroSolicitacaoAcesso.adicionarParametro(
				new ParametroSimplesIn(FiltroSolicitacaoAcesso.ID, idsSolicitacaoAcesso));

		Collection coletionSolicitacaoAcesso = fachada.pesquisar(filtroSolicitacaoAcesso,
				SolicitacaoAcesso.class.getName());
		
		Iterator itera = coletionSolicitacaoAcesso.iterator();
		
		while(itera.hasNext()){
		
			SolicitacaoAcesso solicitacaoAcesso = (SolicitacaoAcesso) itera.next();
			
			//RM 2551 - Caso j� exista um usu�rio cadastrado para a solicita��o de acesso, n�o permitir remo��o
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, solicitacaoAcesso.getLogin()));
			Collection<Usuario> colecaoUsuario = Fachada.getInstancia()
					.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				throw new ActionServletException("atencao.remocao_proibida", null, solicitacaoAcesso.getNomeUsuario());
			}
			
			// remover todos os grupos associado a Solicita��o de Acesso.
			this.getFachada().removerGrupoDeSolicitacaoAcesso(solicitacaoAcesso.getId());
			
			// remover permissoes especiais associadas a Solicitacao de Acesso.
			this.getFachada().removerPermissoesDeSolicitacaoAcesso(solicitacaoAcesso.getId());
						
			// Remover a solicita��o de acesso.
			fachada.remover(solicitacaoAcesso);
		}

		Integer idQt = ids.length;
		
		if(sessao.getAttribute("objeto") != null){
			String objeto = (String) sessao.getAttribute("objeto");
			
			if(objeto.equals("atualizar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicita��o(�es) de Acesso(s) Removido(s) com sucesso.",
						"Manter outra Solicita��o de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=atualizar");
			
			}else if(objeto.equals("autorizar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicita��o(�es) de Acesso(s) Removido(s) com sucesso.",
						"Manter outra Solicita��o de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=autorizar");
			
			}else if(objeto.equals("cadastrar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicita��o(�es) de Acesso(s) Removido(s) com sucesso.",
						"Manter outra Solicita��o de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=cadastrar");
			}
		}

		return retorno;
	}

}
