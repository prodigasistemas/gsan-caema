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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirImovelClienteAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
    	
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Recupera a variável para indicar se o usuário apertou o botão de
		// confirmar da tela de confirmação
		String confirmado = httpServletRequest.getParameter("confirmado");
		
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");
		int validar = fachada.validarImovelAbaCliente(clientes, usuario);
		
		if(validar == -1){
			throw new ActionServletException("atencao.imovel_cliente_sem_documento");
		}
		
		//[FS0035] - Associar cliente usuário desconhecido
		else if(validar == 1){
			
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteUsuarioDesconhecido");
			
			Collection colecaoSis = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
			SistemaParametro sistemaParametro = (SistemaParametro)Util.retonarObjetoDeColecao(colecaoSis);
			
			Cliente clienteUsuarioDesconhecido = sistemaParametro.getClienteUsuarioDesconhecido();
			
			//Caso o cliente usuário não seja informado e não 
			//exista parametrização do cliente usuário desconhecido
			if(clienteUsuarioDesconhecido == null){
				throw new ActionServletException("atencao.required", null, "Um Cliente do tipo Usuário");
			}
			
			else{
				
				
				// Caso o usuário não tenha passado pela página de
				// confirmação
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {

					// Monta a página de confirmação para perguntar se o
					// usuário quer associar um cliente
					// usuário desconhecido ao imóvel
					return montarPaginaConfirmacaoWizard(
							"atencao.imovel_associar_cliente_desconhecido",
							httpServletRequest, actionMapping);
				}
				else{
					
					if(clientes == null)
						clientes = new ArrayList();
					
 					clientes.add(clienteUsuarioDesconhecido);
					
					sessao.setAttribute("imovelClientesNovos",clientes);
				}
				
			}
			
			
		}
		
		sessao.removeAttribute("gis");

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        return retorno;
    }

}