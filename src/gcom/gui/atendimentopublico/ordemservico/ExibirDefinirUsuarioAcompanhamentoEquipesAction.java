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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.EquipeNatureza;
import gcom.atendimentopublico.FiltroEquipeNatureza;
import gcom.atendimentopublico.ordemservico.FiltroUsuarioEmpresaAcompEquipe;
import gcom.atendimentopublico.ordemservico.FiltroUsuarioNaturezaAcompEquipe;
import gcom.atendimentopublico.ordemservico.FiltroUsuarioUnidadeAcompEquipe;
import gcom.atendimentopublico.ordemservico.UsuarioEmpresaAcompEquipe;
import gcom.atendimentopublico.ordemservico.UsuarioNaturezaAcompEquipe;
import gcom.atendimentopublico.ordemservico.UsuarioUnidadeAcompEquipe;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1398] - Definir Equipes a Serem Acompanhadas
 * 
 * @author Amelia Pessoa
 *
 * @date 23/11/2012
 */
public class ExibirDefinirUsuarioAcompanhamentoEquipesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirDefinirUsuarioAcompanhamentoEquipesAction");
		
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Form
		ExibirDefinirUsuarioAcompanhamentoEquipesActionForm form = 
			(ExibirDefinirUsuarioAcompanhamentoEquipesActionForm) actionForm;
		
		// Menu
		String menu = httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.reset();
		}
		
		//Busca as Unidades Organizacionais
		pesquisarUnidadesOrganizacionais(sessao, fachada);
		
		//Busca as Empresas
		pesquisarEmpresas(sessao);
		
		//Busca as Naturezas de Equipe
		pesquisarNaturezaEquipe(sessao, fachada);
		
		String objConsulta = httpServletRequest.getParameter("objeto");
		if (objConsulta!=null && objConsulta.equals("1")){
			this.pesquisarUsuario(form, httpServletRequest);
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa a lista das Unidades Organizacionais 
	 * 
	 * @param sessao
	 * @param form 
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarUnidadesOrganizacionais(HttpSession sessao, Fachada fachada) {
		//Busca colecao na sessao
        Collection<UnidadeOrganizacional> colecaoUnidadesOrganizacionaisSession = 
        		(Collection<UnidadeOrganizacional>) sessao.getAttribute("colecaoUnidadesOrganizacionais");
        
        //Se a colecao for nula
        if (colecaoUnidadesOrganizacionaisSession==null) { 
	        Collection<UnidadeOrganizacional> colecaoUnidadesOrganizacionais = null;
						
			//BUSCA TODAS AS UNIDADES
			colecaoUnidadesOrganizacionais = fachada.obterUnidadesOrganizacionaisAssociadasEquipe(); 
			
			sessao.setAttribute("colecaoUnidadesOrganizacionais", colecaoUnidadesOrganizacionais);			
        } 
	     
	}
	/**
	 * Pesquisa a lista de empresas
	 * 
	 * @author Davi Menezes
	 * @date 10/10/2013
	 * 
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarEmpresas(HttpSession sessao){
		//Busca colecao na sessao
        Collection<UnidadeOrganizacional> colecaoUnidadesOrganizacionaisSession = 
        		(Collection<UnidadeOrganizacional>) sessao.getAttribute("colecaoUnidadesOrganizacionais");
        
        Collection<Empresa> colecaoEmpresas = (Collection<Empresa>) sessao.getAttribute("colecaoEmpresas");
        
        if(Util.isVazioOrNulo(colecaoEmpresas) && !Util.isVazioOrNulo(colecaoUnidadesOrganizacionaisSession)){
        	if(colecaoEmpresas == null){
        		colecaoEmpresas = new ArrayList<Empresa>();
        	}
        	
        	for(UnidadeOrganizacional unidade : colecaoUnidadesOrganizacionaisSession){
        		if(unidade.getEmpresa() != null){
        			if(!colecaoEmpresas.contains(unidade.getEmpresa())){
        				colecaoEmpresas.add(unidade.getEmpresa());
        			}
        		}
        	}
        	
        	sessao.setAttribute("colecaoEmpresas", colecaoEmpresas);
        }
	}
	
	/**
	 * Pesquisar a lista de Natureza de Equipe
	 * 
	 * @author Davi Menezes
	 * @date 10/10/2013
	 * 
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarNaturezaEquipe(HttpSession sessao, Fachada fachada){
		//Busca colecao na sessao
		Collection<EquipeNatureza> colecaoNaturezaEquipe = (Collection<EquipeNatureza>) sessao.getAttribute("colecaoNaturezaEquipe");
		
		if(Util.isVazioOrNulo(colecaoNaturezaEquipe)){
			FiltroEquipeNatureza filtroEquipeNatureza = new FiltroEquipeNatureza(FiltroEquipeNatureza.DESCRICAO);
			filtroEquipeNatureza.adicionarParametro(new ParametroSimples(FiltroEquipeNatureza.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoNaturezaEquipe = fachada.pesquisar(filtroEquipeNatureza, EquipeNatureza.class.getName());
		}
		
		sessao.setAttribute("colecaoNaturezaEquipe", colecaoNaturezaEquipe);
	}
	
	/**
	 * Recebe colecao de Unidades Organizacionais e retorna array de Strings contendo o id delas
	 * 
	 * @param colecaoUnidadesOrganizacionaisParaAtualizar
	 * @return
	 */
	private String[] retornarIds(ArrayList<UsuarioUnidadeAcompEquipe> colecaoUnidadesOrganizacionaisParaAtualizar) {
		String[] retorno = new String[colecaoUnidadesOrganizacionaisParaAtualizar.size()];
		
		for (int i=0;i<colecaoUnidadesOrganizacionaisParaAtualizar.size();i++){
			retorno[i] = colecaoUnidadesOrganizacionaisParaAtualizar.get(i).getUnidadeOrganizacional().getId().toString();
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	private void obterUnidadesPorUsuario(HttpSession sessao, ExibirDefinirUsuarioAcompanhamentoEquipesActionForm form){
		
		Fachada fachada = this.getFachada();
		
		if (form.getIdUsuario()!=null && !form.getIdUsuario().equals("")){
			//Se houver usuário selecionado buscar unidades já associadas a ele
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuario().trim()));
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			Usuario user = null;
			
			if (colecaoUsuario!=null){
				user = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			}
			
			ArrayList<UsuarioUnidadeAcompEquipe> colecaoUnidadesOrganizacionaisParaAtualizar = null;
			
			if (user!=null){
				if(user.getUnidadeOrganizacional() != null){
					FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
					filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, user.getUnidadeOrganizacional().getId()));
					filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO, UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));
					filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacional.UNIDADE_REPAVIMENTADORA);
					
					Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = fachada.pesquisar(
							filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
					
					if(!Util.isVazioOrNulo(colecaoUnidadeRepavimentadora)){
						sessao.setAttribute("colecaoUnidadesOrganizacionais", colecaoUnidadeRepavimentadora);
					}
				}
				
				FiltroUsuarioUnidadeAcompEquipe filtro = new FiltroUsuarioUnidadeAcompEquipe();
				filtro.adicionarParametro(new ParametroSimples(FiltroUsuarioUnidadeAcompEquipe.USUARIO_ID,
						user.getId()));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioUnidadeAcompEquipe.UNIDADE_ORGANIZACIONAL);
				
				colecaoUnidadesOrganizacionaisParaAtualizar = (ArrayList<UsuarioUnidadeAcompEquipe>) 
						fachada.pesquisar(filtro, UsuarioUnidadeAcompEquipe.class.getName());
				
			}else{
				pesquisarUnidadesOrganizacionais(sessao, fachada);
			}
						
			if (colecaoUnidadesOrganizacionaisParaAtualizar != null
					&& !colecaoUnidadesOrganizacionaisParaAtualizar.isEmpty()) {
				
				String[] unidadesMarcadas = retornarIds(colecaoUnidadesOrganizacionaisParaAtualizar);
				
				//seta os campos de permissão especial no form para aparecer no jsp como checado
				form.setUnidadesMarcadas(unidadesMarcadas);
			}
		}
	}
	
	/**
	 * Recebe colecao de Empresas e retorna array de Strings contendo o id delas
	 * 
	 * @param colecaoEmpresasParaAtualizar
	 * @return
	 */
	private String[] retornarIdsEmpresas(ArrayList<UsuarioEmpresaAcompEquipe> colecaoEmpresasParaAtualizar) {
		
		String[] retorno = new String[colecaoEmpresasParaAtualizar.size()];
		
		for (int i=0; i<colecaoEmpresasParaAtualizar.size(); i++){
			retorno[i] = colecaoEmpresasParaAtualizar.get(i).getEmpresa().getId().toString();
		}
		
		return retorno;
	}
	
	/**
	 * Obter as empresas por Usuario
	 * 
	 * @author Davi Menezes
	 * @date 10/10/2013
	 * 
	 * @param sessao
	 * @param form
	 */
	@SuppressWarnings("unchecked")
	private void obterEmpresasPorUsuario(HttpSession sessao, ExibirDefinirUsuarioAcompanhamentoEquipesActionForm form){
		
		Fachada fachada = this.getFachada();
		
		if (form.getIdUsuario()!=null && !form.getIdUsuario().equals("")){
			//Se houver usuário selecionado buscar unidades já associadas a ele
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuario().trim()));
			
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			Usuario user = null;
			
			if (colecaoUsuario!=null){
				user = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			}
			
			ArrayList<UsuarioEmpresaAcompEquipe> colecaoEmpresasParaAtualizar = null;
			
			if (user!=null){
				FiltroUsuarioEmpresaAcompEquipe filtro = new FiltroUsuarioEmpresaAcompEquipe();
				filtro.adicionarParametro(new ParametroSimples(FiltroUsuarioUnidadeAcompEquipe.USUARIO_ID, user.getId()));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioEmpresaAcompEquipe.EMPRESA);
				
				colecaoEmpresasParaAtualizar = (ArrayList<UsuarioEmpresaAcompEquipe>) Fachada.getInstancia().
							pesquisar(filtro, UsuarioEmpresaAcompEquipe.class.getName());
				
			}else{
				pesquisarEmpresas(sessao);
			}
						
			if (!Util.isVazioOrNulo(colecaoEmpresasParaAtualizar)) {
				
				String[] empresasMarcadas = retornarIdsEmpresas(colecaoEmpresasParaAtualizar);
				
				//seta os campos de permissão especial no form para aparecer no jsp como checado
				form.setEmpresasMarcadas(empresasMarcadas);
			}
		}
	}
	
	/**
	 * Recebe colecao de Natureza de Equipes e retorna array de Strings contendo o id delas
	 * 
	 * @param colecaoEmpresasParaAtualizar
	 * @return
	 */
	private String[] retornarIdsNaturezaEquipe(ArrayList<UsuarioNaturezaAcompEquipe> colecaoNaturezaEquipeParaAtualizar) {
		
		String[] retorno = new String[colecaoNaturezaEquipeParaAtualizar.size()];
		
		for (int i=0; i<colecaoNaturezaEquipeParaAtualizar.size(); i++){
			retorno[i] = colecaoNaturezaEquipeParaAtualizar.get(i).getEquipeNatureza().getId().toString();
		}
		
		return retorno;
	}
	
	/**
	 * Obter as natureza de equipes por usuario
	 * 
	 * @author Davi Menezes
	 * @date 10/10/2013
	 * 
	 * @param sessao
	 * @param form
	 */
	@SuppressWarnings("unchecked")
	private void obterNaturezaEquipePorUsuario(HttpSession sessao, ExibirDefinirUsuarioAcompanhamentoEquipesActionForm form){
		
		Fachada fachada = this.getFachada();
		
		if (form.getIdUsuario()!=null && !form.getIdUsuario().equals("")){
			//Se houver usuário selecionado buscar unidades já associadas a ele
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuario().trim()));
			
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			Usuario user = null;
			
			if (colecaoUsuario!=null){
				user = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			}
			
			ArrayList<UsuarioNaturezaAcompEquipe> colecaoNaturezaEquipeParaAtualizar = null;
			
			if (user!=null){
				FiltroUsuarioNaturezaAcompEquipe filtro = new FiltroUsuarioNaturezaAcompEquipe();
				filtro.adicionarParametro(new ParametroSimples(FiltroUsuarioNaturezaAcompEquipe.ID_USUARIO, user.getId()));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioNaturezaAcompEquipe.NATUREZA_EQUIPE);
				
				colecaoNaturezaEquipeParaAtualizar = (ArrayList<UsuarioNaturezaAcompEquipe>) 
						fachada.pesquisar(filtro, UsuarioNaturezaAcompEquipe.class.getName());
				
			}else{
				pesquisarNaturezaEquipe(sessao, fachada);
			}
						
			if (!Util.isVazioOrNulo(colecaoNaturezaEquipeParaAtualizar)) {
				
				String[] naturezaEquipeMarcadas = retornarIdsNaturezaEquipe(colecaoNaturezaEquipeParaAtualizar);
				
				//seta os campos de permissão especial no form para aparecer no jsp como checado
				form.setNaturezaEquipeMarcadas(naturezaEquipeMarcadas);
			}
		}
	}
	
	/**
	 * Método auxiliar para pesquisar usuário e setar os campos correspondentes do form 
	 * @param form
	 * @param httpServletRequest
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarUsuario(ExibirDefinirUsuarioAcompanhamentoEquipesActionForm form, HttpServletRequest httpServletRequest) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuario().trim()));
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		Usuario user = null;
		
		if (colecaoUsuario.size()!=0){
			user = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
			//Se usuario for diferente de ATIVO
			if(!user.getUsuarioSituacao().getId().equals(UsuarioSituacao.ATIVO)){
				form.setNomeUsuario("Usuário informado não está ATIVO.");
				form.setIdUsuario("");
				httpServletRequest.setAttribute("usuarioNaoEncontrado", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idUsuario");
			} else {			
				form.setNomeUsuario(user.getNomeUsuario());
				form.setIdUsuario(user.getLogin());
				httpServletRequest.setAttribute("nomeCampo", "idUsuario");
				
				//Buscar unidades marcadas para o usuario
				this.obterUnidadesPorUsuario(sessao, form);
				
				//Buscar empresas marcadas para o usuario
				this.obterEmpresasPorUsuario(sessao, form);
				
				//Buscar naturezas de equipe marcadas para o usuario
				this.obterNaturezaEquipePorUsuario(sessao, form);
				
			}
		} else {
			form.setNomeUsuario("USUÁRIO INEXISTENTE.");
			form.setIdUsuario(" ");
			httpServletRequest.setAttribute("usuarioNaoEncontrado", "exception");
			httpServletRequest.setAttribute("nomeCampo", "idUsuario");
		}		
	}
	
}