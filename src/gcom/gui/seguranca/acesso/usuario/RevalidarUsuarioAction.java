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
 * Realiza a pesquisa de contas de imóvel de acordo com os parâmetros informados na página 
 *
 * @author Fernanda Almeida
 * @date 15/01/2012
 */
public class RevalidarUsuarioAction extends GcomAction {
	/**
	 * Pesquisa as contas existentes para o imóvel
	 *
	 * [UC0248] Pesquisar Contas do Imóvel
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
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

		//Seta o mapeamento de retorno para a tela de resultado da pesquisa de contas do imóvel 
		ActionForward retorno = actionMapping.findForward("telaSucesso");
			
		//Recupera o form de pesquisa de contas do imóvel
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
			// Adiciona à data atual o numero de dias revalidar senha
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
	
		montarPaginaSucesso(httpServletRequest, "Usuário(s) revalidados com sucesso.",
				"Realizar outra Revalidação de Usuários",
				"exibirRevalidarUsuarioAction.do?menu=sim");
		       	
    	
		//Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
