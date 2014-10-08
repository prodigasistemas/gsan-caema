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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 15/05/2006
 */
public class ExibirSubstituirValidadorAutorizadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSubstituirValidadorAutorizador");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		SubstituirValidadorAutorizadorActionForm form = (SubstituirValidadorAutorizadorActionForm) actionForm;
		
		// Limpar
		if(httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").equals("")){
			form.setUsuarioTipo("3");
			form.setCodigoNovoUsuario("");
			form.setNomeNovoUsuario("");
			form.setCodigoUsuario("");
			form.setNomeUsuario("");
			form.setSolicitacaoSituacao(null);
			
		}
		
		//[IT0003] Obter Solicitações de Acesso.
		if(httpServletRequest.getParameter("botao") != null && !httpServletRequest.getParameter("botao").equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getCodigoUsuario()));
			Collection<?> colUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			Usuario usuario = null;
			if(colUsuario != null && !colUsuario.isEmpty()){
				usuario = (Usuario) Util.retonarObjetoDeColecao(colUsuario);
			}else{
				throw new ActionServletException(
						"atencao.pesquisa.usuario.inexistente");
			}
			
			String usuarioTipo = form.getUsuarioTipo();
			if(form.getUsuarioTipo() == null || ( form.getUsuarioTipo()!= null && form.getUsuarioTipo().equals("") ) ){
				throw new ActionServletException(
						"atencao.usuario_tipo_obrigatorio");
			}
			
			FiltroSolicitacaoAcesso filtro = new FiltroSolicitacaoAcesso();
			// 1.1
			if(usuarioTipo.equals("1")){
				filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcesso.USUARIO_RESPONSAVEL_ID, usuario.getId()));
			}else if(usuarioTipo.equals("2")){
				if(usuario.getFuncionario() != null){
					filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL_ID, usuario.getFuncionario().getId()));
				}else{
					throw new ActionServletException("atencao.usuario_informado_nao_autorizador");
				}
			}else{
				if(usuario.getFuncionario() != null){
					filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcesso.USUARIO_RESPONSAVEL_ID, usuario.getId(),ConectorOr.CONECTOR_OR, 2));
					filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL_ID, usuario.getFuncionario().getId()));
				}else{
					filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcesso.USUARIO_RESPONSAVEL_ID, usuario.getId()));
				}
			}
			
			String[] solicitacaoSituacao = form.getSolicitacaoSituacao();
			// 2
			if(solicitacaoSituacao != null && solicitacaoSituacao.length != 0){
				Collection<String> colecaoIdsSolicitacaoSituacao = new ArrayList();
				
				for(int i = 0; i<solicitacaoSituacao.length;i++){
					if(solicitacaoSituacao[i] != null && !(solicitacaoSituacao[i]).equals("")){
					 colecaoIdsSolicitacaoSituacao.add(solicitacaoSituacao[i]);
					}
				}
				
				filtro.adicionarParametro(new ParametroSimplesIn(FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO_ID, colecaoIdsSolicitacaoSituacao));			
			}
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.USUARIO_SOLICITANTE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.USUARIO_RESPONSAVEL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO);
			
			Collection<?> colecaoSolicitacaoAcesso = fachada.pesquisar(filtro, SolicitacaoAcesso.class.getName());
			if(colecaoSolicitacaoAcesso != null && !colecaoSolicitacaoAcesso.isEmpty()){
				sessao.setAttribute("colecaoSolicitacaoAcesso", colecaoSolicitacaoAcesso);
				sessao.setAttribute("totalSolicitacoes", colecaoSolicitacaoAcesso.size());
			}else{
				sessao.removeAttribute("colecaoSolicitacaoAcesso");
				sessao.removeAttribute("totalSolicitacoes");
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
		}else{

        	//remove a lista de solicitação de acesso para que a nova pesquisa seja exibida
        	//sessao.removeAttribute("colecaoSolicitacaoAcesso");
		}

	  	//verifica se o codigo do usuário foi digitado
        String idDigitadoEnterUsuario = (String) form.getCodigoUsuario();
        if (idDigitadoEnterUsuario != null
                && !idDigitadoEnterUsuario.trim().equals("")
                  ) {
            
        	
        	FiltroUsuario filtroUsuario = new FiltroUsuario();
			
        	filtroUsuario.adicionarParametro(new ParametroSimples(
        			FiltroUsuario.LOGIN, idDigitadoEnterUsuario));

			Collection usuarioEncontrado = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (usuarioEncontrado != null && !usuarioEncontrado.isEmpty()) {

				form.setCodigoUsuario(((Usuario) ((List) usuarioEncontrado)
								.get(0)).getLogin().toString());
				form.setNomeUsuario(((Usuario) ((List) usuarioEncontrado)
								.get(0)).getNomeUsuario());
				form.setUsuarioOK("true");
			} else {
				httpServletRequest.setAttribute("corUsuario","exception");
               	form.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
               	form.setCodigoUsuario("");
               	form.setUsuarioOK("false");
			}
        }
        
        //verifica se o codigo do novo usuário foi digitado foi digitado
        String idDigitadoEnterUsuarioNovo = (String) form.getCodigoNovoUsuario();
        if (idDigitadoEnterUsuarioNovo != null
                && !idDigitadoEnterUsuarioNovo.trim().equals("")) {
            
        	FiltroUsuario filtroUsuario = new FiltroUsuario();
			
        	filtroUsuario.adicionarParametro(new ParametroSimples(
        			FiltroUsuario.LOGIN, idDigitadoEnterUsuarioNovo));

			Collection usuarioEncontrado = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (usuarioEncontrado != null && !usuarioEncontrado.isEmpty()) {

				form.setCodigoNovoUsuario(((Usuario) ((List) usuarioEncontrado)
								.get(0)).getLogin().toString());
				form.setNomeNovoUsuario(((Usuario) ((List) usuarioEncontrado)
								.get(0)).getNomeUsuario());
				form.setNovoUsuarioOK("true");
			} else {
				httpServletRequest.setAttribute("corUsuarioNovo","exception");
               	form.setNomeNovoUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
               	form.setCodigoNovoUsuario("");
               	form.setNovoUsuarioOK("false");
			}
        }
        
        FiltroSolicitacaoAcessoSituacao filtro = new FiltroSolicitacaoAcessoSituacao();
        filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
        Collection<?> colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtro, 
				SolicitacaoAcessoSituacao.class.getName());	
        
		sessao.setAttribute("colecaoSolicitacaoAcessoSituacao", colecaoSolicitacaoAcessoSituacao);

		return retorno;

	}

}
