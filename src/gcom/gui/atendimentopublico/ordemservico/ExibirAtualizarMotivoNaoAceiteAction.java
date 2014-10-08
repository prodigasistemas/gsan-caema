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



import gcom.atendimentopublico.ordemservico.FiltroOsAcompanhamentoMotivoNaoAceite;
import gcom.atendimentopublico.ordemservico.OsAcompanhamentoMotivoNaoAceite;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroMotivoNaoAutorizacao;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Ricardo Germinio
 * @date 31/07/2012
 */

public class ExibirAtualizarMotivoNaoAceiteAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Localiza o action no objeto 
		ActionForward retorno = actionMapping.findForward("atualizarMotivoNaoAceite");
		//Obtém a instância da sessão
		AtualizarMotivoNaoAceiteActionForm atualizarMotivoNaoAceiteActionForm = (AtualizarMotivoNaoAceiteActionForm)actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		OsAcompanhamentoMotivoNaoAceite osAcompanhamentoMotivoNaoAceite = null;
		String idMotivoNaoAceite = null;
		
		if (httpServletRequest.getParameter("idMotivoNaoAceite") != null) {
			//tela do manter
			idMotivoNaoAceite = (String) httpServletRequest.getParameter("idMotivoNaoAceite");
			sessao.setAttribute("idMotivoNaoAceite", idMotivoNaoAceite);
			//sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMotivoNaoAceiteAction.do?menu=sim");
		} else if (sessao.getAttribute("idMotivoNaoAceite") != null) {
			//link na tela de sucesso do inserir motivo nao aceite
			idMotivoNaoAceite = (String) sessao.getAttribute("idMotivoNaoAceite");
		}else{
			idMotivoNaoAceite = (String) httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("idMotivoNaoAceite", idMotivoNaoAceite);
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMotivoNaoAceiteAction.do?menu=sim");
		}

		if (idMotivoNaoAceite == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				osAcompanhamentoMotivoNaoAceite = (OsAcompanhamentoMotivoNaoAceite) sessao.getAttribute("motivoNaoAceiteAtualizar");
			}else{
				idMotivoNaoAceite = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}

		//------Inicio da parte que verifica se vem da página de
		// 		motivo_nao_aceite_manter.jsp
		if (osAcompanhamentoMotivoNaoAceite == null){

			if (idMotivoNaoAceite != null && !idMotivoNaoAceite.equals("")) {
				FiltroOsAcompanhamentoMotivoNaoAceite filtroOsAcompanhamentoMotivoNaoAceite = new FiltroOsAcompanhamentoMotivoNaoAceite();
				
				filtroOsAcompanhamentoMotivoNaoAceite.adicionarParametro(new ParametroSimples(
						FiltroMotivoNaoAutorizacao.ID, idMotivoNaoAceite));
				
				Collection<OsAcompanhamentoMotivoNaoAceite> colecaoMotivoNaoAceite = fachada.pesquisar(filtroOsAcompanhamentoMotivoNaoAceite, OsAcompanhamentoMotivoNaoAceite.class.getName());

				if (colecaoMotivoNaoAceite != null && !colecaoMotivoNaoAceite.isEmpty()) {
					osAcompanhamentoMotivoNaoAceite = (OsAcompanhamentoMotivoNaoAceite) Util.retonarObjetoDeColecao(colecaoMotivoNaoAceite);
				}
			}
		}

		

		//  ------  O motivo nao aceite foi encontrado
		atualizarMotivoNaoAceiteActionForm.setId(String.valueOf(osAcompanhamentoMotivoNaoAceite.getId()));
		if(osAcompanhamentoMotivoNaoAceite.getId() == null){
			atualizarMotivoNaoAceiteActionForm.setId("");
		}else{
			atualizarMotivoNaoAceiteActionForm.setId(String.valueOf(osAcompanhamentoMotivoNaoAceite.getId()));
		}
		
		atualizarMotivoNaoAceiteActionForm.setDescricao(String.valueOf(osAcompanhamentoMotivoNaoAceite.getDescricao()));
		
		if(osAcompanhamentoMotivoNaoAceite.getDescricaoAbreviada() == null){
			atualizarMotivoNaoAceiteActionForm.setDescricaoAbreviada("");
		} else{
			atualizarMotivoNaoAceiteActionForm.setDescricaoAbreviada(String.valueOf(osAcompanhamentoMotivoNaoAceite.getDescricaoAbreviada()));
		}
		
		atualizarMotivoNaoAceiteActionForm.setIndicadorObservacaoObrigatoriedade(String.valueOf(osAcompanhamentoMotivoNaoAceite.getIndicadorObservacaoObrigatoriedade()));
		
		atualizarMotivoNaoAceiteActionForm.setIndicadorUso(String.valueOf(osAcompanhamentoMotivoNaoAceite.getIndicadorUso()));
		sessao.setAttribute("osAcompanhamentoMotivoNaoAceite", osAcompanhamentoMotivoNaoAceite);
		// ------ Fim da parte que verifica se vem da página de motivo_nao_aceite_manter.jsp

		return retorno;

	}
}