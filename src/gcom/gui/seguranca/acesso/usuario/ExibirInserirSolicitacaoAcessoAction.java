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
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1092] Inserir Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 03/11/2010
 */
public class ExibirInserirSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirSolicitacaoAcessoAction");
				
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		/**
		 * RM8093 - Validar se o usuário logado têm permissão para inserir solicitação de acesso
		 */
		boolean possuiPermissao = 
    			Fachada.getInstancia().verificarPermissaoSolicitacaoAcesso(usuario);
		if (!possuiPermissao){
			throw new ActionServletException("atencao.tipo_usuario_sem_permissao");	
		}
			
		//Verifica se o usuario faz parte do grupo de seguranca para permitir editar o usuario solicitante
		if(Fachada.getInstancia().isGrupoEspecial(usuario.getId())){
			httpServletRequest.setAttribute("grupoSeguranca", true);	
		} else {
			httpServletRequest.removeAttribute("grupoSeguranca");
		}	
		
		// Form
		ExibirInserirSolicitacaoAcessoActionForm form = 
			(ExibirInserirSolicitacaoAcessoActionForm) actionForm;
		
		retiraMsgAnteriores(form);
		
		
		//RM 3892
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		form.setDominioEmail(sistemaParametro.getDominioEmailCorporativo());
		

		if (form.getUsuarioPesquisaNaoEncontrado().equals("FALSE")){
			form.setUsuarioPesquisaNaoEncontrado("false");
		}else if (form.getUsuarioPesquisaNaoEncontrado().equals("TRUE")){
			form.setUsuarioPesquisaNaoEncontrado("true");
		}
		
		// RM3892.2 - Implementar Normas de Senhas no GSAN 
		String objConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		if (objConsulta!=null && (objConsulta.equals("4") || objConsulta.equals("6"))){
			this.pesquisarUsuario(form, httpServletRequest, objConsulta);	
			if(objConsulta.equals("6") && !form.getIdFuncionarioSolicitante().equals("")){
				//Verifica se o usuário pode inserir solicitação de acesso
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdFuncionarioSolicitante().trim()));
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
				Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
				Usuario user = null;				
				if (colecaoUsuario.size()!=0){
					user = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
					boolean podeInserir = 
			    			Fachada.getInstancia().verificarPermissaoSolicitacaoAcesso(user);
					if(!podeInserir){
						form.setNomeFuncionarioSolicitante("USUÁRIO NAO PODE SOLICITAR ACESSO.");
						form.setIdFuncionarioSolicitante("");
						httpServletRequest.setAttribute("usuarioSolicitanteNaoEncontrado", "exception");
						httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSolicitante");
					}
				}				
			}
			return retorno;
		}
		//Fim RM3892.2
				
				
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {

			//Login e Nome do Usuário solicitante 
			form.setIdFuncionarioSolicitante(usuario.getLogin());
			form.setNomeFuncionarioSolicitante(usuario.getNomeUsuario());
						
			// Verifica se o Usuário pertence ao grupo "Superintendente"
			boolean superintendente = false;
			
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
			
			filtroUsuarioGrupo.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUsuarioGrupo.GRUPO);
			
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
			
			Collection<UsuarioGrupo> colecaoUsuarioGrupo = this.getFachada().pesquisar(filtroUsuarioGrupo, 
					UsuarioGrupo.class.getName());
			
			if(colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()){
				
				Iterator<UsuarioGrupo> colecaoIterator = colecaoUsuarioGrupo.iterator();

				while (colecaoIterator.hasNext()) {
					
					UsuarioGrupo usuarioGrupo = (UsuarioGrupo) colecaoIterator.next();
				
					if(usuarioGrupo.getGrupo().getIndicadorSuperintendencia()
							.compareTo(ConstantesSistema.INDICADOR_SUPERINTENDENCIA) == 0){
						
						superintendente = true;
						break;
					}
				}
			}
			
			// Caso Usuário pertença ao grupo "Superintendente"
			if(superintendente){

				form.setIdFuncionarioSuperior(""+usuario.getFuncionario().getId());
				form.setNomeFuncionarioSuperior(usuario.getFuncionario().getNome());
				form.setIcNotificar("1");
				form.setIcSuperintendente("1");
			}else{
				form.setIcNotificar("0");
				form.setIcSuperintendente("2");
			}
			
			// Pesquisar Niveis
			this.pesquisarNiveis(sessao, form, usuario);
						
			// Pesquisar Grupo
			this.pesquisarGrupo(sessao, form, usuario);
			
			//Pesquisar Tipo Usuario
			this.pesquisarTipoUsuario(sessao, form);

			//Pesquisar Empresa
			this.pesquisarEmpresa(sessao, form);
			
			/* RM 3892 Implantar norma de senhas no GSAN */
			FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();

			filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(
					FiltroUsuarioAbrangencia.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUsuarioAbrangencia = Fachada.getInstancia()
					.pesquisar(filtroUsuarioAbrangencia,
							UsuarioAbrangencia.class.getSimpleName());
			sessao.setAttribute("colecaoUsuarioAbrangencia",
					colecaoUsuarioAbrangencia);

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
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = Fachada.getInstancia()
					.pesquisar(filtroUnidadeNegocio,
							UnidadeNegocio.class.getSimpleName());
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);			
			//Fim RM 3892
			
			boolean temCompetenciaRetificacao = fachada.verificarPermissaoEspecial(PermissaoEspecial.INFORMAR_COMPETENCIA_RETIFICACAO,usuario);
			if(!temCompetenciaRetificacao){
				sessao.setAttribute("desabilitaCompetenciaRetificacao", "SIM");
				form.setCompetenciaRetificacao("");
			}else{
				sessao.removeAttribute("desabilitaCompetenciaRetificacao");
			}
			
		}
		
		if(httpServletRequest.getParameter("usuarioTipo") != null && 
	        	!httpServletRequest.getParameter("usuarioTipo").equals("") ){
			
			String idUsuarioTipo = httpServletRequest.getParameter("usuarioTipo").toString();
			
			// Se não for "Prestador de Serviços"
			if(!idUsuarioTipo.equals("8") && Util.verificarNaoVazio(form.getIdFuncionario())){
				
				// Pesquisa a funcionário
				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, form.getIdFuncionario()));

				Collection<Funcionario> funcionarioPesquisado = this.getFachada().pesquisar(
						filtroFuncionario, Funcionario.class.getName());

				// Se nenhum usuário for encontrado a mensagem é enviada para a página
				if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
					
					Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
					
					if(funcionario.getNumeroCpf() != null){
						form.setCpf(""+funcionario.getNumeroCpf());
					}
					if(funcionario.getDataNascimento() != null){
						
						form.setDataNascimento(""+Util.formatarData(funcionario.getDataNascimento()));
					}
					
					int tamanhoMaximoCampo = 50;
					
					if (tamanhoMaximoCampo < funcionario.getNome().length()) {
						// trunca a String
						String strTruncado = funcionario.getNome().substring(0, tamanhoMaximoCampo);
						form.setNomeUsuario(strTruncado);
					}else{
						
						form.setNomeUsuario(funcionario.getNome());
					}
				}
			}else{
				
				form.setCpf("");
				form.setDataNascimento("");
				form.setNomeFuncionario("");
				form.setNomeUsuario("");
			}
		}

		httpServletRequest.setAttribute("dataAtual", Util.formatarData(new Date()));
		
		// IDADE MÍNIMA
        httpServletRequest.setAttribute("idadeMinimaUsuario", sistemaParametro.getIdadeMinimaUsuario());
        
		
		if (objConsulta != null && !objConsulta.trim().equals("")) {			 
			if (objConsulta.trim().equals("1")|| objConsulta.trim().equals("2")) {
				// Pega os codigos que o usuario digitou para a pesquisa direta do funcionário
				if (form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")
						|| (form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().trim().equals(""))) {					
					this.pesquisarFuncionario( httpServletRequest, form, objConsulta);
				}
			} else if (objConsulta.trim().equals("3")){
				// Pega os codigos que o usuario digitou para a pesquisa direta do funcionário
				if (form.getLoginUsuarioPesquisa() != null && !form.getLoginUsuarioPesquisa().trim().equals("")) {
					this.pesquisarUsuario(httpServletRequest, form);
				}
			} else if (objConsulta.trim().equals("5")){
				//Se o usuário solicitante pertencer ao grupo especial de segurança
				if(fachada.isGrupoEspecial(usuario.getId())){
					//Obter permissoes especiais
					String nivel = httpServletRequest.getParameter("nivel");
					String especial = httpServletRequest.getParameter("especial");
					pesquisarPermissaoEspecial(nivel, especial, sessao, form);	
				}											
			}
		}		
		
		String consultaUsuarioPopup = httpServletRequest.getParameter("consultaPopup");
		
		if (consultaUsuarioPopup != null && !consultaUsuarioPopup.trim().equals("") && 
				consultaUsuarioPopup.trim().equals("true")){
			// Pega os codigos que o usuario digitou para a pesquisa direta do funcionário
			if (form.getLoginUsuarioPesquisa() != null && !form.getLoginUsuarioPesquisa().trim().equals("")) {
				this.pesquisarUsuario(httpServletRequest, form);
			}
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da lotação
		if(form.getIdLotacao() != null && !form.getIdLotacao().trim().equals("")){
			this.pesquisarLotacao(httpServletRequest, form);
		}
		
		/* RM 3892 Implantar norma de senhas no GSAN */
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
				
				httpServletRequest.setAttribute("nomeCampo", "nivel");
				
			} else {
				form.setIdLocalidade("");
				form.setNomeLocalidade("Localidade inexistente");
				form.setLocalidadeNaoEncontrada("true");
				
				httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
			}
		}
		//Fim RM 3892
		
		return retorno;
	}
	
	/**
	 * Pesquisa a lista das permissões especiais disponíveis para o grupo selecionado 
	 * 
	 * @param idGrupo
	 * @param sessao
	 * @param form 
	 */
	private void pesquisarPermissaoEspecial(String nivel, String especial, HttpSession sessao, ExibirInserirSolicitacaoAcessoActionForm form) {
		Integer idNivel = null;
		Integer idEspecial = null;
		if(nivel!=null && !nivel.equals("")){
			idNivel = Integer.parseInt(nivel);
		}
		if(especial!=null && !especial.equals("")){
			idEspecial = Integer.parseInt(especial);
		}
		
		Collection<PermissaoEspecial> colecaoPermissaoEspecial = 
				Fachada.getInstancia().pesquisarPermissaoEspecial(idNivel, idEspecial);
		
        
		
        if (colecaoPermissaoEspecial!=null && colecaoPermissaoEspecial.size()>0){
        	sessao.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
        } else {
        	sessao.removeAttribute("colecaoPermissaoEspecial");
        }       
	}

	/**
	 * Pesquisar Funcionário
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			ExibirInserirSolicitacaoAcessoActionForm form, String objetoConsulta) {

		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getIdFuncionarioSuperior();
			
		}else if(objetoConsulta.trim().equals("2")){
			
			local = form.getIdFuncionario();
		}

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, local));

		Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());
		Funcionario funcionario = null;
				
		if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
			funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, funcionario.getId()));
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_ABRANGENCIA);
			Collection<Usuario> CollUsuarioResponsavel = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			Usuario usuarioResponsavel = (Usuario) Util.retonarObjetoDeColecao(CollUsuarioResponsavel);
			if (usuarioResponsavel!=null && usuarioResponsavel.getUsuarioAbrangencia()!=null){
				form.setAbrangencia(usuarioResponsavel.getUsuarioAbrangencia().getId().toString());
			}
			
			// verifica se o funcionário responsável tem permissão especial para ser autorizador
			if(objetoConsulta.trim().equals("1")){
				
				
				/**
				 * RM9331 - Ajustes nas funcionalidades de Segurança de Acesso
				 * Verifica se o usuário tem acesso a permissão especial AUTORIZAR SOLICITACAO ACESSO
				 */
				boolean possuiPermissao = 
		    			Fachada.getInstancia().verificarPermissaoEspecialAtiva(
		    					PermissaoEspecial.AUTORIZAR_SOLICITACAO_ACESSO,usuarioResponsavel);
				if (!possuiPermissao){
					throw new ActionServletException("atencao.usuario_sem_permissao_autorizar");	
				}
			}
			
			if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSuperior(""+funcionario.getId());
				form.setNomeFuncionarioSuperior(funcionario.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idUsuarioResponsavel");
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario("" + funcionario.getId());
				form.setNomeFuncionario( funcionario.getNome());
				form.setNomeUsuario(funcionario.getNome());
				httpServletRequest.setAttribute("nomeCampo", "nomeUsuario");
				
				if(funcionario.getNumeroCpf() != null){
					form.setCpf(funcionario.getNumeroCpf());
				}
				
				if(funcionario.getDataNascimento() != null){
					form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
				}
				
				if(funcionario.getUnidadeOrganizacional() != null){
					form.setIdLotacao("" + funcionario.getUnidadeOrganizacional().getId());
					form.setNomeLotacao(funcionario.getUnidadeOrganizacional().getDescricao());
				}
			}

		} else {
			
			if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSuperior(null);
				httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSuperior");
				form.setNomeFuncionarioSuperior("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente1","true");
	
			}
			
			if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario(null);
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
				form.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente","true");
	
			}
		}
		
		if (objetoConsulta.trim().equals("1") && funcionario!=null && funcionario.getId()!=null){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, funcionario.getId()));
			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario==null || colecaoUsuario.size()==0){
				form.setIdFuncionarioSuperior(" ");
				form.setNomeFuncionarioSuperior("FUNCIONÁRIO NÃO TEM USUÁRIO.");				
				httpServletRequest.setAttribute("funcionarioNaoUsuario", "exception");				
			}
		}
		
	}
	
	/**
	 * Pesquisar Grupo
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarGrupo(HttpSession sessao,	ExibirInserirSolicitacaoAcessoActionForm form,Usuario usuarioLogado) {
	
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
	 * @author Amelia Pessoa
	 * @date 26/06/2012
	 */
	private void pesquisarNiveis(HttpSession sessao,	ExibirInserirSolicitacaoAcessoActionForm form, Usuario usuarioLogado) {
	
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
	
	/**
	 * Pesquisar Usuario Tipo
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarTipoUsuario(HttpSession sessao, ExibirInserirSolicitacaoAcessoActionForm form) {
	
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		
		filtroUsuarioTipo.setConsultaSemLimites(true);
		filtroUsuarioTipo.adicionarParametro(new ParametroSimples( 
				FiltroUsuarioTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUsuarioTipo.setCampoOrderBy(FiltroUsuarioTipo.DESCRICAO);
		
		Collection colecaoUsuarioTipo = this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
		
		if(colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Usuário Tipo");
		}else{
			
			sessao.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);
		}
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao, ExibirInserirSolicitacaoAcessoActionForm form) {
	
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
	 * Pesquisar Lotação
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			ExibirInserirSolicitacaoAcessoActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));

		Collection<UnidadeOrganizacional> lotacaoPesquisada = fachada.pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (lotacaoPesquisada != null && !lotacaoPesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(lotacaoPesquisada);
			form.setIdLotacao("" + unidadeOrganizacional.getId());
			form.setNomeLotacao( unidadeOrganizacional.getDescricao());

			httpServletRequest.setAttribute("nomeCampo", "login");
		} else {
			form.setIdLotacao("");
			form.setNomeLotacao("LOTAÇÃO INEXISTENTE");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}
	
	private void retiraMsgAnteriores(ExibirInserirSolicitacaoAcessoActionForm form){
		String msgFunc = "FUNCIONARIO INEXISTENTE.";
		if ((form.getNomeFuncionario()!=null && form.getNomeFuncionario().equalsIgnoreCase(msgFunc)) || 
				(form.getNomeFuncionarioSolicitante()!=null && form.getNomeFuncionarioSolicitante().equalsIgnoreCase(msgFunc))
				|| (form.getNomeFuncionarioSuperior()!=null && form.getNomeFuncionarioSuperior().equalsIgnoreCase(msgFunc))){
			form.setNomeFuncionario("");
			//form.setNomeFuncionarioSolicitante("");
			form.setNomeFuncionarioSuperior("");
		}
		if (form.getNomeLotacao()!=null && form.getNomeLotacao().equalsIgnoreCase("LOTAÇÃO INEXISTENTE.")){
			form.setNomeLotacao("");
		}
		if (form.getNomeLocalidade()!=null && form.getNomeLocalidade().equalsIgnoreCase("Localidade inexistente")){
			form.setNomeLocalidade("");
		}
		if (form.getNomeFuncionarioSuperior()!=null && form.getNomeFuncionarioSuperior().equalsIgnoreCase("Funcionário não tem usuário.")){
			form.setNomeFuncionarioSuperior(" ");
		}
		if (form.getNomeUsuarioResponsavel()!=null && form.getNomeUsuarioResponsavel().equalsIgnoreCase("USUÁRIO INEXISTENTE.")){
			form.setNomeUsuarioResponsavel("");
		}
		if (form.getNomeUsuarioPesquisa()!=null && form.getNomeUsuarioPesquisa().equalsIgnoreCase("USUÁRIO INEXISTENTE")){
			form.setNomeUsuarioPesquisa("");
		}
	}
	
	/**
	 * Pesquisar Usuário
	 *
	 * @author Thúlio Araújo
	 * @date 25/01/2012
	 */
	private void pesquisarUsuario(HttpServletRequest httpServletRequest, 
			ExibirInserirSolicitacaoAcessoActionForm form) {

		//Limpa dados anteriores
		form.setIdEmpresa(" ");
		form.setIdFuncionario(" ");
		form.setNomeFuncionario(" ");
		form.setNomeUsuario(" ");
		form.setNomeUsuarioPesquisa(" ");
		form.setCpf(" ");
		form.setDataNascimento(" ");
		form.setIdLotacao(" ");
		form.setNomeLotacao(" ");
		form.setLogin(" ");
		form.setEmail(" ");
		form.setDataInicial(" ");
		form.setDataFinal(" ");
				
		
		Fachada fachada = Fachada.getInstancia();		
		String local = form.getLoginUsuarioPesquisa().replace('*', ' ');
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.EMPRESA);
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_TIPO);
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.FUNCIONARIO);
		
		//RM2418 - Trazer informações do campo Abrangência, quando informar o usuário
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.USUARIO_ABRANGENCIA);
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.GERENCIA_REGIONAL);
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_NEGOCIO);
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.LOCALIDADE);
				
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, local.trim()));

		Collection<Usuario> usuarioPesquisado = fachada.pesquisar(
				filtroUsuario, Usuario.class.getName());

		if (usuarioPesquisado != null && !usuarioPesquisado.isEmpty()) {
			
			Usuario usuario = null;
			//Se trouxer mais de um usuário usar o que tem o CPF diferente de nulo
			if (usuarioPesquisado.size()>1){
				for (Usuario usur : usuarioPesquisado){
					if (usur.getCpf()!=null){
						usuario = usur;
						break;
					}
				}
			} else {
				usuario = (Usuario) Util.retonarObjetoDeColecao(usuarioPesquisado);
			}
			
			form.setLoginUsuarioPesquisa(usuario.getLogin());
			form.setIdTipoUsuario(usuario.getUsuarioTipo().getId().toString());
			form.setIdEmpresa(usuario.getEmpresa().getId().toString());
			if (usuario.getFuncionario() != null){
				form.setIdFuncionario(usuario.getFuncionario().getId().toString());
				form.setNomeFuncionario(usuario.getFuncionario().getNome());
			}
			form.setNomeUsuario(usuario.getNomeUsuario());
			form.setNomeUsuarioPesquisa(usuario.getNomeUsuario());
			form.setCpf(usuario.getCpf());
			form.setDataNascimento(Util.formatarData(usuario.getDataNascimento()));
			
			if (usuario.getUnidadeOrganizacional()!=null){
				form.setIdLotacao(usuario.getUnidadeOrganizacional().getId().toString());
			}
			
			form.setLogin(usuario.getLogin());
			form.setEmail(usuario.getDescricaoEmail());
			form.setDataInicial(Util.formatarData(usuario.getDataCadastroInicio()));
			form.setDataFinal(Util.formatarData(usuario.getDataCadastroFim()));
			form.setUsuarioPesquisaNaoEncontrado("false");
			
			if (usuario.getUsuarioAbrangencia()!=null){
				form.setAbrangencia(usuario.getUsuarioAbrangencia().getId().toString());
			}
			if (usuario.getGerenciaRegional()!=null){
				form.setGerenciaRegional(usuario.getGerenciaRegional().getId().toString());
			}
			if (usuario.getUnidadeNegocio()!=null){
				form.setUnidadeNegocio(usuario.getUnidadeNegocio().getId().toString());
			}
			if (usuario.getLocalidade()!=null){
				form.setIdLocalidade(usuario.getLocalidade().getId().toString());
			}
			
		} else {
			form.setLoginUsuarioPesquisa("");
			form.setNomeUsuarioPesquisa("Usuário inexistente");
			form.setUsuarioPesquisaNaoEncontrado("true");
			httpServletRequest.setAttribute("nomeCampo", "loginUsuarioPesquisa");			
		}
	}
	
	//Método auxiliar para pesquisar usuário e setar os campos correspondentes do form
	private void pesquisarUsuario(ExibirInserirSolicitacaoAcessoActionForm form, HttpServletRequest httpServletRequest, String objeto) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			if(objeto.equals("6")){
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdFuncionarioSolicitante().trim()));
			} else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuarioResponsavel().trim()));
			}
			
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			Usuario user = null;
			
			if (colecaoUsuario.size()!=0){
				user = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				if(objeto.equals("6")){
					form.setNomeFuncionarioSolicitante(user.getNomeUsuario());
					form.setIdFuncionarioSolicitante(user.getLogin());
					httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSuperior");
				} else {
					// verifica se o usuário responsável por revalidar tem permissão especial para ser autorizador
					/**
					 * RM9331 - Ajustes nas funcionalidades de Segurança de Acesso
					 * Verifica se o usuário tem acesso a permissão especial REVALIDAR USUARIO
					 */
					boolean possuiPermissao = 
			    			Fachada.getInstancia().verificarPermissaoEspecialAtiva(
			    					PermissaoEspecial.REVALIDAR_USUARIO,user);
					if (!possuiPermissao){
						throw new ActionServletException("atencao.usuario_sem_permissao_revalidar_soli_acesso");	
					}
					
					form.setNomeUsuarioResponsavel(user.getNomeUsuario());
					form.setIdUsuarioResponsavel(user.getLogin());
					httpServletRequest.setAttribute("nomeCampo", "loginUsuarioPesquisa");
				}
			} else if(!objeto.equals("6")) {
				form.setNomeUsuarioResponsavel("USUÁRIO INEXISTENTE.");
				form.setIdUsuarioResponsavel(" ");
				httpServletRequest.setAttribute("usuarioNaoEncontrado", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idUsuarioResponsavel");
			} else {
				form.setNomeFuncionarioSolicitante("USUÁRIO INEXISTENTE.");
				form.setIdFuncionarioSolicitante("");
				httpServletRequest.setAttribute("usuarioSolicitanteNaoEncontrado", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSolicitante");
			}
			
		}
}