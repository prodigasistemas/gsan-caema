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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 16/11/2010
 */
public class ExibirFiltrarSolicitacaoAcessoAction extends GcomAction {

	/**
	 * [UC0984] Filtrar tipo de débito vigência
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa do Solicitação Acesso.
	 * 
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarSolicitacaoAcesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		/**
		 * RM8093 - Validar se o usuário logado têm permissão para inserir solicitação de acesso
		 */
		boolean possuiPermissao = 
    			Fachada.getInstancia().verificarPermissaoSolicitacaoAcesso(usuario);
		if (!possuiPermissao){
			throw new ActionServletException("atencao.tipo_usuario_sem_permissao_manter");	
		}
		
		FiltrarSolicitacaoAcessoActionForm form = (FiltrarSolicitacaoAcessoActionForm) actionForm;
		
		String objeto = "";
		
		if(sessao.getAttribute("objeto") == null){
			objeto = httpServletRequest.getParameter("objeto");
			
			if(objeto != null && (objeto.equals("autorizar") || objeto.equals("cadastrar")
					|| objeto.equals("atualizar") || objeto.equals("relatorio"))){
				
				sessao.setAttribute("objeto", objeto);
			}
		}else{
			
			objeto = (String) sessao.getAttribute("objeto");
		}
		
		if (sessao.getAttribute("colecaoSolicitacaoAcesso") != null) {
			sessao.removeAttribute("colecaoSolicitacaoAcesso");
		}
		
		if (sessao.getAttribute("objetoSolicitacaoAcesso") != null) {
			sessao.removeAttribute("objetoSolicitacaoAcesso");
		}
		
		if (sessao.getAttribute("solicitacaoAcesso") != null){
			sessao.removeAttribute("solicitacaoAcesso");
		}

		if (sessao.getAttribute("telaAtualizar") != null){
			sessao.removeAttribute("telaAtualizar");
		}
				
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			if(objeto.equals("atualizar")){
				form.setAtualizar("1");
			}
			
			if(usuario!=null){
				
				if(objeto.equals("atualizar")){
					if (usuario.getFuncionario() != null){
						form.setIdFuncionarioSolicitante(""+usuario.getFuncionario().getId());
						form.setNomeFuncionarioSolicitante(usuario.getFuncionario().getNome());
					} else {
						form.setIdFuncionarioSolicitante(""+usuario.getLogin());
						form.setNomeFuncionarioSolicitante(usuario.getNomeUsuario());
					}
				}else{
					if(!objeto.equals("cadastrar") && !objeto.equals("relatorio")){
						if (usuario.getFuncionario() != null){
							form.setIdFuncionarioSuperior(""+usuario.getFuncionario().getId());
							form.setNomeFuncionarioSuperior(usuario.getFuncionario().getNome());
						} else {
							form.setIdFuncionarioSuperior(""+usuario.getLogin());
							form.setNomeFuncionarioSuperior(usuario.getNomeUsuario());
						}						
					}
				}
			}else{
				
				throw new ActionServletException("atencao.acesso.solicitacao_funcionario");
			}
			
			//Pesquisar Empresa
			if(sessao.getAttribute("colecaoEmpresa") == null){
				this.pesquisarEmpresa(sessao, form);
			}
			
			// Pesquisar Situação
			if(sessao.getAttribute("colecaoSituacao") == null){
				this.pesquisarSolicitacaoAcessoSituacao(sessao, form);
			}
			
			// Pesquisar usuário tipo
			if(sessao.getAttribute("colecaoUsuarioTipo") == null){
				this.pesquisarUsuarioTipo(sessao);
			}
			
			// Pesquisar usuário situação
			if(sessao.getAttribute("colecaoUsuarioSituacao") == null){
				this.pesquisarUsuarioSituacao(sessao);
			}
			// Pesquisar abrangencia acesso
			if(sessao.getAttribute("colecaoUsuarioAbrangencia") == null){
				this.pesquisarAbrangenciaAcesso(sessao);
			}
			// Pesquisar grupo
			if(sessao.getAttribute("colecaoGrupo") == null){
				this.pesquisarGrupo(sessao,usuario);
			}
			// Pesquisar grupo de nível
			if(sessao.getAttribute("colecaoNivel") == null){
				this.pesquisarNiveis(sessao,usuario);
			}
			
		}

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("2")
						|| objetoConsulta.trim().equals("3"))) {

			//Pesquisa usuario
			if (objetoConsulta.trim().equals("1")){
				this.pesquisarUsuario(httpServletRequest, form);
			}
			// Pega os codigos que o usuario digitou para a pesquisa direta do funcionário
			else if (form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")
					|| (form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().trim().equals(""))
					|| (form.getIdFuncionarioSolicitante() != null && !form.getIdFuncionarioSolicitante().trim().equals(""))) {
				
				this.pesquisarFuncionario( httpServletRequest, form, objetoConsulta);
			}
		}else{
			//pesquisa a localidade digitada
			if (form.getIdLocalidade() != null
					&& !form.getIdLocalidade().equals("")) {
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdLocalidade()));

				Collection colecaoLocalidade = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
					Localidade l = (Localidade) colecaoLocalidade.iterator().next();
					form.setIdLocalidade(l.getId().toString());
					form.setNomeLocalidade(l.getDescricao());
					form.setLocalidadeNaoEncontrada("false");
				} else {
					form.setIdLocalidade("");
					form.setNomeLocalidade(" Localidade inexistente.");
					form.setLocalidadeNaoEncontrada("true");
				}
			}
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da lotação
		if(form.getIdLotacao() != null && !form.getIdLotacao().trim().equals("")){
			
			this.pesquisarLotacao(httpServletRequest, form);
		}

		return retorno;
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao, FiltrarSolicitacaoAcessoActionForm form) {
	
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro(new ParametroSimples( 
				FiltroUsuarioTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
		
		if(colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		}else{
			
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	/**
	 * Pesquisar Funcionário
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			FiltrarSolicitacaoAcessoActionForm form, String objetoConsulta) {

		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("3")){
			local = form.getIdFuncionarioSuperior();
			
		}else if(objetoConsulta.trim().equals("2")){
			
			local = form.getIdFuncionario();
		}else if(objetoConsulta.trim().equals("1")){
			
			local = form.getIdFuncionarioSolicitante();
		}

		// Pesquisa a usuário na base
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, local));

		Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
			Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
			
			if(objetoConsulta.trim().equals("3")){
				form.setIdFuncionarioSuperior(""+funcionario.getId());
				form.setNomeFuncionarioSuperior(funcionario.getNome());
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario("" + funcionario.getId());
				form.setNomeFuncionario( funcionario.getNome());
			
				httpServletRequest.setAttribute("nomeCampo", "nomeUsuario");
			}else if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSolicitante("" + funcionario.getId());
				form.setNomeFuncionarioSolicitante( funcionario.getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "dataInicial");
			}

		} else {
			
			if(objetoConsulta.trim().equals("3")){
				form.setIdFuncionarioSuperior(null);
				form.setNomeFuncionarioSuperior("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente1","true");
				httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSuperior");
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente","true");
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
			}else if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSolicitante(null);
				form.setNomeFuncionarioSolicitante("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente2","true");
				httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSolicitante");
			}
		}
	}
	
	/**
	 * Pesquisar Usuario
	 *
	 * @author Amelia Pessoa
	 * @date 18/10/2012
	 */
	private void pesquisarUsuario(HttpServletRequest httpServletRequest, 
			FiltrarSolicitacaoAcessoActionForm form) {

		// Pesquisa a usuário na base
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdFuncionarioSolicitante().toLowerCase()));
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_ABRANGENCIA);
		Collection<Usuario> CollUsuarioResponsavel = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(CollUsuarioResponsavel);

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (usuario != null) {
			form.setIdFuncionarioSolicitante(usuario.getLogin());
			form.setNomeFuncionarioSolicitante(usuario.getNomeUsuario());				
			httpServletRequest.setAttribute("nomeCampo", "dataInicial");
		} else {
			form.setIdFuncionarioSolicitante(null);
			form.setNomeFuncionarioSolicitante("USUÁRIO INEXISTENTE");
			httpServletRequest.setAttribute("funcionarioInexistente2","true");
			httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSolicitante");			
		}
	}
	
	/**
	 * Pesquisar Lotação
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			FiltrarSolicitacaoAcessoActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a usuário na base
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));

		Collection<UnidadeOrganizacional> lotacaoPesquisada = fachada.pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (lotacaoPesquisada != null && !lotacaoPesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(lotacaoPesquisada);
			form.setIdLotacao("" + unidadeOrganizacional.getId());
			form.setNomeLotacao( unidadeOrganizacional.getDescricao());

		} else {
			form.setIdLotacao("");
			form.setNomeLotacao("LOTAÇÃO INEXISTENTE");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}
	
	/**
	 * Pesquisar Situação
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarSolicitacaoAcessoSituacao(HttpSession sessao,	FiltrarSolicitacaoAcessoActionForm form) {
	
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		
		filtroSolicitacaoAcessoSituacao.setConsultaSemLimites(true);
		filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
				FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(sessao.getAttribute("objeto") != null){
			String objeto = (String) sessao.getAttribute("objeto");
			
			if(objeto.equals("autorizar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP, ConectorOr.CONECTOR_OR, 2));
				
				//RM7146 - Exibir também opção de filtrar solicitações não autorizadas
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.NAO_AUTORIZADO));
				
			}else if(objeto.equals("cadastrar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_CADASTRAMENTO));
			}
		}
		
		filtroSolicitacaoAcessoSituacao.setCampoOrderBy(FiltroSolicitacaoAcessoSituacao.DESCRICAO);
		
		Collection colecaoSituacao = this.getFachada().pesquisar(
				filtroSolicitacaoAcessoSituacao, SolicitacaoAcessoSituacao.class.getName());
		
		if(colecaoSituacao == null || colecaoSituacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Solicitação Acesso Situação");
		}else{
			
			sessao.setAttribute("colecaoSituacao", colecaoSituacao);
		}
	}
	
	/**
	 * Pesquisar Usuário Tipo
	 *
	 * @author Sávio Luiz
	 * @date 27/08/2012
	 */
	private void pesquisarUsuarioTipo(HttpSession sessao){
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

		filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
				FiltroUsuarioTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoUsuarioTipo = Fachada.getInstancia().pesquisar(
				filtroUsuarioTipo, UsuarioTipo.class.getSimpleName());
		if (colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					" Tipo do Usúario está ");
		}
		sessao.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);
	}
	
	/**
	 * Pesquisar Usuário Tipo
	 *
	 * @author Sávio Luiz
	 * @date 27/08/2012
	 */
	private void pesquisarUsuarioSituacao(HttpSession sessao){
		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();

		filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
				FiltroUsuarioTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		
		Collection colecaoUsuarioSituacao = Fachada.getInstancia()
				.pesquisar(new FiltroUsuarioSituacao(),
						UsuarioSituacao.class.getSimpleName());
		
		if (colecaoUsuarioSituacao == null || colecaoUsuarioSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					" Situação do Usúario está ");
		}
		sessao.setAttribute("colecaoUsuarioSituacao", colecaoUsuarioSituacao);
	}
	
	/**
	 * Pesquisar Usuário Tipo
	 *
	 * @author Sávio Luiz
	 * @date 27/08/2012
	 */
	private void pesquisarAbrangenciaAcesso(HttpSession sessao){
		FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();

		filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(
				FiltroUsuarioAbrangencia.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoUsuarioAbrangencia = Fachada.getInstancia()
				.pesquisar(filtroUsuarioAbrangencia,
						UsuarioAbrangencia.class.getSimpleName());
		if (colecaoUsuarioAbrangencia == null || colecaoUsuarioAbrangencia.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					" Abrangência de Acesso está ");
		}
		sessao.setAttribute("colecaoUsuarioAbrangencia",
				colecaoUsuarioAbrangencia);
		
		//gerencia regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = Fachada.getInstancia()
				.pesquisar(filtroGerenciaRegional,
						GerenciaRegional.class.getSimpleName());
		sessao
				.setAttribute("colecaoGerenciaRegional",
						colecaoGerenciaRegional);
		//Unidade de Negocio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoUnidadeNegocio = Fachada.getInstancia()
				.pesquisar(filtroUnidadeNegocio,
						UnidadeNegocio.class.getSimpleName());
		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

	}
	
	
	/**
	 * Pesquisar Grupo
	 *
	 * @author Sávio Luiz
	 * @date 24/09/2012
	 */
	private void pesquisarGrupo(HttpSession sessao,Usuario usuarioLogado) {
		
		//pesquisa se o usuário logado tem acesso a algum grupo especial com visualização restrita
		Collection colecaoGrupoVisualizacaoRestrita = this.getFachada().pesquisarGruposUsuarioComVisualizacaoRestrita(usuarioLogado.getId(),ConstantesSistema.SIM);
	
		FiltroGrupo filtroGrupo = new FiltroGrupo();		
		filtroGrupo.setConsultaSemLimites(true);
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.INDICADOR_GRUPO_ESPECIAL, ConstantesSistema.SIM));
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.INDICADOR_VISUALIZACAO_RESTRITA, ConstantesSistema.NAO));
		
		filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
		
		Collection<Grupo> colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
		
		if(colecaoGrupo != null && !colecaoGrupo.isEmpty()){
			if(colecaoGrupoVisualizacaoRestrita != null && !colecaoGrupoVisualizacaoRestrita.isEmpty()){
				colecaoGrupo.addAll(colecaoGrupoVisualizacaoRestrita);
			}
			sessao.setAttribute("colecaoGrupo", colecaoGrupo);
		}else{
			if(colecaoGrupoVisualizacaoRestrita != null && !colecaoGrupoVisualizacaoRestrita.isEmpty()){
				sessao.setAttribute("colecaoGrupo", colecaoGrupoVisualizacaoRestrita);
			}
		}
	}
	
	/**
	 * RM 7344
	 * Pesquisar Niveis (grupos nao especiais)
	 *
	 * @author Sávio Luiz
	 * @date 24/09/2012
	 */
	private void pesquisarNiveis(HttpSession sessao,Usuario usuarioLogado) {
	
		//pesquisa se o usuário logado tem acesso a algum grupo especial com visualização restrita
		Collection colecaoGrupoVisualizacaoRestrita = this.getFachada().pesquisarGruposUsuarioComVisualizacaoRestrita(usuarioLogado.getId(),ConstantesSistema.NAO);
		FiltroGrupo filtroGrupo = new FiltroGrupo();		
		filtroGrupo.setConsultaSemLimites(true);
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.INDICADOR_GRUPO_ESPECIAL, ConstantesSistema.NAO));
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.INDICADOR_VISUALIZACAO_RESTRITA, ConstantesSistema.NAO));
		
		filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
		
		Collection<Grupo> colecaoNivel = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
		
		if(colecaoNivel != null && !colecaoNivel.isEmpty()){
			if(colecaoGrupoVisualizacaoRestrita != null && !colecaoGrupoVisualizacaoRestrita.isEmpty()){
				colecaoNivel.addAll(colecaoGrupoVisualizacaoRestrita);
			}
			sessao.setAttribute("colecaoNivel", colecaoNivel);
		}else{
			if(colecaoGrupoVisualizacaoRestrita != null && !colecaoGrupoVisualizacaoRestrita.isEmpty()){
				sessao.setAttribute("colecaoNivel", colecaoGrupoVisualizacaoRestrita);
			}
		}
	}
	

}