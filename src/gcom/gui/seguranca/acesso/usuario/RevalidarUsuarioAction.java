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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Realiza a pesquisa de contas de im�vel de acordo com os par�metros informados na p�gina 
 *
 * @author Fernanda Almeida
 * @date 15/01/2012
 */
public class RevalidarUsuarioAction extends GcomAction {
	/**
	 * Pesquisa as contas existentes para o im�vel
	 *
	 * [UC0248] Pesquisar Contas do Im�vel
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Fernanda Almeida
	 * @date 01/02/2012
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno para a tela de resultado da pesquisa de contas do im�vel 
		ActionForward retorno = actionMapping.findForward("telaSucesso");
			
		//Recupera o form de pesquisa de contas do im�vel
		ExibirRevalidarUsuarioActionForm exibirRevalidarUsuarioActionForm = (ExibirRevalidarUsuarioActionForm) actionForm;
		//sessao.setAttribute("idUsuarios", "");
		
			
		String[] idUsuarios =(String[]) exibirRevalidarUsuarioActionForm.getIds();

		SistemaParametro sistemaParametro = this.getSistemaParametro();			

		Usuario userPesquisado = null;
		
		//Atualiza os usuarios selecionados
		for(int i=0;i<idUsuarios.length;i++){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuarios[i]));
			Collection<Usuario> colecao= this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName());
			for(Usuario usuario : colecao){
				userPesquisado = usuario;
			}
			// Adiciona � data atual o numero de dias revalidar senha
			Date hoje = new Date();
			Date dataRevalidada = Util.adicionarNumeroDiasDeUmaData(hoje,sistemaParametro.getPeriodoRevalidarSenha());
			userPesquisado.setDataExpiracaoAcesso(dataRevalidada);
			
			int intervalo = Util.obterQuantidadeDiasEntreDuasDatas(hoje, userPesquisado.getDataExpiracaoAcesso());
			
			if (((intervalo < 0) && (intervalo <= sistemaParametro.getDiasRevalidarSenha()))){
				throw new ActionServletException("atencao.usuario_data_expiracao_atingida", null, 
					userPesquisado.getNomeUsuario());
			}
			
			UsuarioSituacao usst = new UsuarioSituacao();
			usst.setId(UsuarioSituacao.ATIVO);
			
			userPesquisado.setUsuarioSituacao(usst);
			
			this.getFachada().atualizar(userPesquisado);
		}
		
		exibirRevalidarUsuarioActionForm.setIds(null);
	
		montarPaginaSucesso(httpServletRequest, "Usu�rio(s) revalidados com sucesso.",
				"Realizar outra Revalida��o de Usu�rios",
				"exibirRevalidarUsuarioAction.do?menu=sim");
		       	
    	
		//Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
