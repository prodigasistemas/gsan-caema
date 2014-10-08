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
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoPermissao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoPermissao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
public class ExibirAtualizarSolicitacaoAcessoAction extends GcomAction {
	/**
	 * [UC1093] Manter Solicitação de Acesso.
	 * 
	 * Este caso de uso permite cadastrar um novo Usuário.
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarSolicitacaoAcesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		AtualizarSolicitacaoAcessoActionForm form = (AtualizarSolicitacaoAcessoActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Verifica se o usuario faz parte do grupo de seguranca para permitir editar o usuario solicitante
		if(Fachada.getInstancia().isGrupoEspecial(usuario.getId())){
			httpServletRequest.setAttribute("grupoSeguranca", true);	
		} else {
			httpServletRequest.removeAttribute("grupoSeguranca");
		}
		
		SolicitacaoAcesso solicitacaoAcesso = null;		
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
  		form.setDominioEmail(sistemaParametro.getDominioEmailCorporativo());
		String idSolicitacaoAcesso = "";
		
		String limparUsuario = httpServletRequest.getParameter("limparUsuario");
		if (limparUsuario!=null && limparUsuario.equals("sim")){
			limparUsuario(form);	
			return retorno;
		}
		
		String objConsulta = httpServletRequest.getParameter("objetoConsulta");
		if (objConsulta!=null) {
			
			if (objConsulta.equals("2")){
				this.pesquisarUsuario(form, httpServletRequest);
			} else if (objConsulta.equals("3")){
				this.pesquisarFuncionario(httpServletRequest, form, objConsulta);
			} else if (objConsulta.equals("1")){
				this.pesquisarFuncionario(httpServletRequest, form, objConsulta);
			} else if (objConsulta.trim().equals("5")){
				//Se o usuário solicitante pertencer ao grupo especial de segurança
				if(fachada.isGrupoEspecial(usuario.getId())){
					//Obter permissoes especiais
					String nivel = httpServletRequest.getParameter("nivel");
					String especial = httpServletRequest.getParameter("especial");
					pesquisarPermissaoEspecial(nivel, especial, sessao, form);
				}
			} else if(objConsulta.equals("6") && !form.getIdFuncionarioSolicitante().equals("")){
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
					} else {
						form.setNomeFuncionarioSolicitante(user.getNomeUsuario());
						form.setIdFuncionarioSolicitante(user.getLogin());
						httpServletRequest.setAttribute("nomeCampo", "idFuncionarioSuperior");
					}
				}				
			}
			
			return retorno;
		}
		
		//Retira mensagens de erro
		retiraMensagens(form);
				
		/* Preencher combos da tela */
		//Tipos Usuario
		this.pesquisarTipoUsuario(sessao);
		//Empresas
		this.pesquisarEmpresa(sessao);
		//Grupos
		this.pesquisarGrupo(sessao,usuario);
		//Niveis
		this.pesquisarNiveis(sessao, form,usuario);
		//Abrangencias
		this.pesquisarAbrangencias(sessao);
		//Gerencias Regionais
		this.pesquisarGerenciaRegional(sessao);
		//Unidades de Negocio
		this.pesquisarUnidadeNegocio(sessao);
		
		
		if ( sessao.getAttribute("solicitacaoAcesso") != null ) {
			solicitacaoAcesso = (SolicitacaoAcesso) sessao.getAttribute("solicitacaoAcesso");
		
		} else if(sessao.getAttribute("objetoSolicitacaoAcesso") != null){
			solicitacaoAcesso = (SolicitacaoAcesso) sessao.getAttribute("objetoSolicitacaoAcesso");			
		} 
		
		if(solicitacaoAcesso!=null && solicitacaoAcesso.getId()!=null){
			solicitacaoAcesso = pesquisarSolicitacaoAcesso(solicitacaoAcesso.getId());
		}
		
		if (httpServletRequest.getParameter("idRegistroAtualizar") != null){		
			idSolicitacaoAcesso = (String) httpServletRequest.getParameter("idRegistroAtualizar");			
			if( idSolicitacaoAcesso != null ) {				
				solicitacaoAcesso = pesquisarSolicitacaoAcesso(Integer.parseInt(idSolicitacaoAcesso));
			}
		}
		
		if(httpServletRequest.getParameter("filtrar") != null && httpServletRequest.getParameter("filtrar").equals("sim")){
			preencherForm(form, solicitacaoAcesso, fachada);
			sessao.removeAttribute("colecaoPermissaoEspecial");
			
			boolean temCompetenciaRetificacao = fachada.verificarPermissaoEspecial(PermissaoEspecial.INFORMAR_COMPETENCIA_RETIFICACAO,usuario);
			if(!temCompetenciaRetificacao){
				sessao.setAttribute("desabilitaCompetenciaRetificacao", "SIM");
				form.setCompetenciaRetificacao("");
			}else{
				sessao.removeAttribute("desabilitaCompetenciaRetificacao");
			}
		}
		
		// Validar localidade
		if (form.getIdLocalidade() != null
				&& !form.getIdLocalidade().equals("")) {
			validarLocalidade(form, httpServletRequest);
		}

		
		httpServletRequest.setAttribute("dataAtual", Util.formatarData(new Date()));
		
		// IDADE MÍNIMA
        httpServletRequest.setAttribute("idadeMinimaUsuario", sistemaParametro.getIdadeMinimaUsuario());
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("2"))) {

			// Pega os codigos que o usuario digitou para a pesquisa direta do funcionário
			if (form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")
					|| (form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().trim().equals(""))) {
				
				this.pesquisarFuncionario( httpServletRequest, form, objetoConsulta);
			}
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da lotação
		if(form.getIdLotacao() != null && !form.getIdLotacao().trim().equals("")){			
			this.pesquisarLotacao(httpServletRequest, form);
		}
		
		//Se o usuário solicitante pertencer ao grupo especial de segurança
		if(fachada.isGrupoEspecial(usuario.getId())){
			//Permissoes Especiais
			Collection<PermissaoEspecial> colecaoPermissaoEspecialSession = (Collection<PermissaoEspecial>) sessao.getAttribute("colecaoPermissaoEspecial");   
			if (colecaoPermissaoEspecialSession==null) {
				sessao.removeAttribute("colecaoPermissaoEspecial"); 
		        
				Collection<PermissaoEspecial> colecaoPermissaoEspecialDesalibitado = null;
				String[] permissaoEspecial=null;
				
				//Todas as permissoes para os grupos selecionados
				Integer idGrupo1 = null;
				Integer idGrupo2 = null;
				if(form.getGrupo()!=null && !form.getGrupo().equals("")){
					idGrupo2 = Integer.parseInt(form.getGrupo());
				}
				if(form.getNivel()!=null && !form.getNivel().equals("")){
					idGrupo1 = Integer.parseInt(form.getNivel());
				}
				
				Collection<PermissaoEspecial> colecaoPermissaoEspecial = 
						Fachada.getInstancia().pesquisarPermissaoEspecial(idGrupo1, idGrupo2);					
				
				if(colecaoPermissaoEspecial!=null && colecaoPermissaoEspecial.size()>0){		
					//Permissoes ja selecionadas
					//Obtem Permissoes
					FiltroSolicitacaoAcessoPermissao filtroSolicitacaoAcessoPermissao = new FiltroSolicitacaoAcessoPermissao();			
					filtroSolicitacaoAcessoPermissao.setConsultaSemLimites(true);
					filtroSolicitacaoAcessoPermissao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcessoPermissao.PERMISSAO_ESPECIAL);
					filtroSolicitacaoAcessoPermissao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcessoPermissao.SOLICITACAO_ACESSO_ID, solicitacaoAcesso.getId()));				
					ArrayList<SolicitacaoAcessoPermissao> colecaoSolicitacaoAcessoPermissao = (ArrayList<SolicitacaoAcessoPermissao>)
							 Fachada.getInstancia().pesquisar(filtroSolicitacaoAcessoPermissao, SolicitacaoAcessoPermissao.class.getName());
					
					permissaoEspecial = new String[colecaoSolicitacaoAcessoPermissao.size()];
					for (int i=0; i<permissaoEspecial.length; i++){
						permissaoEspecial[i] = ""+colecaoSolicitacaoAcessoPermissao.get(i).getComp_id().getPermissaoEspecialId();
					}
					
					form.setPermissoesEspeciais(permissaoEspecial);
							
					sessao.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
					sessao.setAttribute("colecaoPermissaoEspecialDesalibitado", colecaoPermissaoEspecialDesalibitado);
				} else {
					form.setPermissoesEspeciais(null);
				}
	        }
		}
		
		Date timeStamp = solicitacaoAcesso.getUltimaAlteracao();

		sessao.setAttribute("idSolicitacaoAcesso", idSolicitacaoAcesso);
		sessao.setAttribute("solicitacaoAcesso", solicitacaoAcesso);
		sessao.setAttribute("timeStamp", timeStamp);
		
		httpServletRequest.setAttribute("idSolicitacaoAcesso", idSolicitacaoAcesso);		
		
		return retorno;
		
	}

	private void preencherForm(AtualizarSolicitacaoAcessoActionForm form,
			SolicitacaoAcesso solicitacaoAcesso, Fachada fachada) {
		// pesquisar Funcionário
		if(solicitacaoAcesso.getFuncionario() != null){
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, solicitacaoAcesso.getFuncionario().getId()));

			Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
				
				form.setIdFuncionario(funcionario.getId().toString());
				form.setNomeFuncionario(funcionario.getNome());
			}
		}
		
		//Id
		form.setIdSolicitacao(solicitacaoAcesso.getId().toString());
		
		// Usuário Solicitante
		if(solicitacaoAcesso.getUsuarioSolicitante() != null){			
			form.setIdFuncionarioSolicitante(solicitacaoAcesso.getUsuarioSolicitante().getLogin().toString());
			form.setNomeFuncionarioSolicitante(solicitacaoAcesso.getUsuarioSolicitante().getNomeUsuario());
		}
		
		// Funcionário Responsável
		if(solicitacaoAcesso.getFuncionarioResponsavel() != null){
			
			form.setIdFuncionarioSuperior(solicitacaoAcesso.getFuncionarioResponsavel().getId().toString());
			form.setNomeFuncionarioSuperior(solicitacaoAcesso.getFuncionarioResponsavel().getNome());
		}
		
		// Unidade de Lotação
		if(solicitacaoAcesso.getUnidadeOrganizacional() != null){
			
			form.setIdLotacao(solicitacaoAcesso.getUnidadeOrganizacional().getId().toString());
			form.setNomeLotacao(solicitacaoAcesso.getUnidadeOrganizacional().getDescricao());
		}
		
		// Empresa
		form.setIdEmpresa(solicitacaoAcesso.getEmpresa().getId().toString());
		
		// Tipo de Usuário
		form.setIdTipoUsuario(solicitacaoAcesso.getUsuarioTipo().getId().toString());
		
		// Nome Usuário
		form.setNomeUsuario(solicitacaoAcesso.getNomeUsuario());
		
		// Login
		form.setLogin(solicitacaoAcesso.getLogin());
		
		// Email
		form.setEmail(solicitacaoAcesso.getEmail());
		
		// Data Nascimento
		if(solicitacaoAcesso.getDataNascimento() != null){
			form.setDataNascimento( Util.formatarData(solicitacaoAcesso.getDataNascimento()));
		}
		
		// CPF
		if(Util.verificarNaoVazio(solicitacaoAcesso.getCpf())){
			form.setCpf(solicitacaoAcesso.getCpf());
		}
		
		// Período de Cadastramento
		if(solicitacaoAcesso.getPeriodoInicial() != null
				&& solicitacaoAcesso.getPeriodoFinal() != null){
			
			form.setDataInicial( Util.formatarData(solicitacaoAcesso.getPeriodoInicial()));
			form.setDataFinal( Util.formatarData(solicitacaoAcesso.getPeriodoFinal()));
		}
		
		// Notificar Responsável
		if(solicitacaoAcesso.getIndicadorNotificarResponsavel() != null){
			if(solicitacaoAcesso.getIndicadorNotificarResponsavel().compareTo(ConstantesSistema.SIM) == 0){
				form.setIcNotificar("0");
			}else{
				form.setIcNotificar("1");
			}
		}
		
		if(solicitacaoAcesso.getCompetenciaRetificacao() != null){
			form.setCompetenciaRetificacao(Util.formatarBigDecimalParaStringComVirgula(solicitacaoAcesso.getCompetenciaRetificacao()));
		}
		
		// Abrangência Acesso
		form.setAbrangencia(solicitacaoAcesso.getUsuarioAbrangencia().getId().toString());
		
		// Gerência Regional
		if (solicitacaoAcesso.getGerenciaRegional() != null){
			form.setGerenciaRegional(solicitacaoAcesso.getGerenciaRegional().getId().toString());
		}
		
		// Unidade Negócio
		if (solicitacaoAcesso.getUnidadeNegocio() != null){
			form.setUnidadeNegocio(solicitacaoAcesso.getUnidadeNegocio().getId().toString());
		}
		
		// Localidade
		if (solicitacaoAcesso.getLocalidade() != null){
			form.setIdLocalidade(solicitacaoAcesso.getLocalidade().getId().toString());
		}
		
		//Usuario Responsavel
		if (solicitacaoAcesso.getUsuarioResponsavel()!=null){
			form.setIdUsuarioResponsavel(solicitacaoAcesso.getUsuarioResponsavel().getLogin());
			form.setNomeUsuarioResponsavel(solicitacaoAcesso.getUsuarioResponsavel().getNomeUsuario());
		}
		
		//Grupo
		Integer idGrupo = Fachada.getInstancia().obterGrupo(solicitacaoAcesso.getId());
		if (idGrupo!=null){
			form.setGrupo(""+idGrupo);
		} else {
			form.setGrupo("");
		}
		
		//Nivel
		Integer idNivel = Fachada.getInstancia().obterNivel(solicitacaoAcesso.getId());
		if (idNivel!=null){
			form.setNivel(""+idNivel);
		} else {
			form.setNivel("");
		}
		
		//usuario da solicitacao de acesso
		if(solicitacaoAcesso.getUsuario() != null && solicitacaoAcesso.getUsuario().getId() != null){
			form.setIdUsuarioSolicitacao(""+solicitacaoAcesso.getUsuario().getId());
		}
		
		// Período de Cadastramento
		if(solicitacaoAcesso.getDataCadastramento()!= null){
			form.setDataCadastramento( Util.formatarData(solicitacaoAcesso.getDataCadastramento()));
		}
		
	}

	private void validarLocalidade(AtualizarSolicitacaoAcessoActionForm form, HttpServletRequest httpServletRequest) {
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
			form.setNomeLocalidade("Localidade inexistente.");
			form.setLocalidadeNaoEncontrada("true");
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}

	private void pesquisarUnidadeNegocio(HttpSession sessao) {
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoUnidadeNegocio = Fachada.getInstancia()
				.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getSimpleName());
		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
	}

	private void pesquisarGerenciaRegional(HttpSession sessao) {
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoGerenciaRegional = Fachada.getInstancia()
				.pesquisar(filtroGerenciaRegional,
						GerenciaRegional.class.getSimpleName());
		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
	}

	private void pesquisarAbrangencias(HttpSession sessao) {
		FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
		filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(
				FiltroUsuarioAbrangencia.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoUsuarioAbrangencia = Fachada.getInstancia()
				.pesquisar(filtroUsuarioAbrangencia, UsuarioAbrangencia.class.getSimpleName());
		sessao.setAttribute("colecaoUsuarioAbrangencia", colecaoUsuarioAbrangencia);
	}

	/**
	 * Pesquisa a lista das permissões especiais disponíveis para o grupo selecionado 
	 * 
	 * @param idGrupo
	 * @param sessao
	 * @param form 
	 */
	private void pesquisarPermissaoEspecial(String nivel, String especial, HttpSession sessao, AtualizarSolicitacaoAcessoActionForm form) {
       
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
	 * RM 7344
	 * Pesquisar Niveis (grupos nao especiais)
	 *
	 * @author Amelia Pessoa
	 * @date 26/06/2012
	 */
	private void pesquisarNiveis(HttpSession sessao, AtualizarSolicitacaoAcessoActionForm form, Usuario usuarioLogado) {
	
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
	 * Pesquisar solicitacao de acesso pelo ID
	 * @param idSolicitacaoAcesso
	 * @return
	 */
	private SolicitacaoAcesso pesquisarSolicitacaoAcesso(Integer idSolicitacaoAcesso) {
		SolicitacaoAcesso solicitacaoAcesso;
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.FUNCIONARIO_SOLICITANTE);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.UNIDADE_ORGANIZACIONAL);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.ABRANGENCIA_ACESSO);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.GERENCIA_REGIONAL);			
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.UNIDADE_NEGOCIO);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.EMPRESA);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.LOCALIDADE);		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.USUARIO_RESPONSAVEL);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
			FiltroSolicitacaoAcesso.USUARIO_SOLICITACAO);
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.USUARIO_SOLICITANTE);
		
		filtroSolicitacaoAcesso.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcesso.ID, idSolicitacaoAcesso));
		
		solicitacaoAcesso = (SolicitacaoAcesso) Fachada.getInstancia().pesquisar(filtroSolicitacaoAcesso, 
				SolicitacaoAcesso.class.getName()).iterator().next();
		return solicitacaoAcesso;
	}

	/**
	 * Retira mensagens de erro
	 * @param form
	 */
	private void retiraMensagens(AtualizarSolicitacaoAcessoActionForm form) {
		if (form.getNomeUsuarioResponsavel()!=null && form.getNomeUsuarioResponsavel().equalsIgnoreCase("USUÁRIO INEXISTENTE.")){
			form.setNomeUsuarioResponsavel(" ");
			form.setIdUsuarioResponsavel(" ");
		}
		if (form.getNomeLotacao()!=null && form.getNomeLotacao().equalsIgnoreCase("LOTAÇÃO INEXISTENTE.")){
			form.setIdLotacao(" ");
			form.setNomeLotacao(" ");
		}		
	}

	/**
	 * Limpar usuario
	 * @param form
	 */
	private void limparUsuario(AtualizarSolicitacaoAcessoActionForm form) {
		form.setIdFuncionario("");
		form.setNomeFuncionario("");
		form.setCpf("");
		form.setDataNascimento("");
		form.setNomeUsuario("");
		form.setIdLotacao("");
		form.setNomeLotacao("");
		form.setLogin("");
		form.setIdEmpresa("");
		form.setDataInicial("");
		form.setDataFinal("");
		form.setEmail("");
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao) {
	
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
	 * Pesquisar Usuario Tipo
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarTipoUsuario(HttpSession sessao) {
	
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
	 * Pesquisar Grupo
	 *
	 * @author Hugo Leonardo
	 * @date 17/11/2010
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
		
		Collection colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
		
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
	 * Pesquisar Funcionário
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			AtualizarSolicitacaoAcessoActionForm form, String objetoConsulta) {
				
		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getIdFuncionarioSuperior();
			
		} else if(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("3")){
			
			local = form.getIdFuncionario();
			
			//Limpa dados anteriores
			form.setNomeFuncionario(" ");
			form.setNomeUsuario(" ");
			form.setCpf(" ");
			form.setDataNascimento(" ");
			form.setIdLotacao(" ");
			form.setNomeLotacao(" ");
			form.setLogin(" ");
			form.setEmail(" ");
			form.setDataInicial(" ");
			form.setDataFinal(" ");
		} 

		if (form.getNomeFuncionarioSuperior()!=null && form.getNomeFuncionarioSuperior().equalsIgnoreCase("Funcionário não tem usuário.")){
			form.setNomeFuncionarioSuperior(" ");
		}
		
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, local));
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);

		Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());
		Funcionario funcionario = null;
		if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
			funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);

			if(objetoConsulta.trim().equals("1")){
				// verifica se o funcionário responsável tem permissão especial para ser autorizador
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, funcionario.getId()));
				Collection<Usuario> CollUsuarioResponsavel = fachada.pesquisar(
					filtroUsuario, Usuario.class.getName());
				Usuario usuarioResponsavel = (Usuario) Util.retonarObjetoDeColecao(CollUsuarioResponsavel);
				
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
				form.setIdFuncionarioSuperior(""+funcionario.getId());
				form.setNomeFuncionarioSuperior(funcionario.getNome());
			
			}else if(objetoConsulta.trim().equals("3")){
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
				form.setNomeFuncionarioSuperior("FUNCIONÁRIO INEXISTENTE.");
				httpServletRequest.setAttribute("funcionarioInexistente1","true");	
			}
			
			if(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("3")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE.");				
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
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
				httpServletRequest.setAttribute("funcionarioInexistente1", "exception");				
			}
		}
	}
	
	/**
	 * Pesquisar Lotação
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			AtualizarSolicitacaoAcessoActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));

		Collection<UnidadeOrganizacional> lotacaoPesquisada = fachada.pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (lotacaoPesquisada != null && !lotacaoPesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(lotacaoPesquisada);
			
			if (!form.getNomeLotacao().equals(unidadeOrganizacional.getDescricao())) {
				httpServletRequest.setAttribute("nomeCampo", "login");
			}
			form.setIdLotacao("" + unidadeOrganizacional.getId());
			form.setNomeLotacao( unidadeOrganizacional.getDescricao());
			
		} else {
			form.setIdLotacao("");
			form.setNomeLotacao("LOTAÇÃO INEXISTENTE.");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}

	//Método auxiliar para pesquisar usuário e setar os campos correspondentes do form
	private void pesquisarUsuario(AtualizarSolicitacaoAcessoActionForm form, HttpServletRequest httpServletRequest) {
		Collection<Usuario> colecaoUsuario = null;
		Usuario usuario = null;
		try {
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getIdUsuarioResponsavel().trim()));
		//filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
		colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());		
		} catch (NumberFormatException ex){
			colecaoUsuario = null;
		}
		if (colecaoUsuario!=null && colecaoUsuario.size()!=0){
			usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			// verifica se o usuário responsável por revalidar tem permissão especial para ser autorizador
			/**
			 * RM9331 - Ajustes nas funcionalidades de Segurança de Acesso
			 * Verifica se o usuário tem acesso a permissão especial REVALIDAR USUARIO
			 */
			boolean possuiPermissao = 
	    			Fachada.getInstancia().verificarPermissaoEspecialAtiva(
	    					PermissaoEspecial.REVALIDAR_USUARIO,usuario);
			if (!possuiPermissao){
				throw new ActionServletException("atencao.usuario_sem_permissao_revalidar_soli_acesso");	
			}
			form.setNomeUsuarioResponsavel(usuario.getNomeUsuario());
			form.setIdUsuarioResponsavel(usuario.getLogin());
		} else {
			form.setNomeUsuarioResponsavel("USUÁRIO INEXISTENTE.");
			form.setIdUsuarioResponsavel(" ");
			httpServletRequest.setAttribute("usuarioNaoEncontrado", "exception");
			httpServletRequest.setAttribute("nomeCampo", "idUsuarioResponsavel");
		}
		
	}
	
}
