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
package gcom.gui.seguranca;



import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.MotivoNaoAutorizacao;
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

public class ExibirAtualizarMotivoNaoAutorizacaoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Localiza o action no objeto 
		ActionForward retorno = actionMapping.findForward("atualizarMotivoNaoAutorizacao");
		//Obt�m a inst�ncia da sess�o
		AtualizarMotivoNaoAutorizacaoActionForm atualizarMotivoNaoAutorizacaoActionForm = (AtualizarMotivoNaoAutorizacaoActionForm)actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		MotivoNaoAutorizacao motivoNaoAutorizacao = null;
		String idMotivoNaoAutorizacao = null;
		
		if (httpServletRequest.getParameter("idMotivoNaoAutorizacao") != null) {
			//tela do manter
			idMotivoNaoAutorizacao = (String) httpServletRequest.getParameter("idMotivoNaoAutorizacao");
			sessao.setAttribute("idMotivoNaoAutorizacao", idMotivoNaoAutorizacao);
			//sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMotivoNaoAutorizacaoAction.do?menu=sim");
		} else if (sessao.getAttribute("idMotivoNaoAutorizacao") != null) {
			//link na tela de sucesso do inserir motivo nao autorizacao
			idMotivoNaoAutorizacao = (String) sessao.getAttribute("idMotivoNaoAutorizacao");
		}else{
			idMotivoNaoAutorizacao = (String) httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("idMotivoNaoAutorizacao", idMotivoNaoAutorizacao);
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMotivoNaoAutorizacaoAction.do?menu=sim");
		}

		if (idMotivoNaoAutorizacao == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				motivoNaoAutorizacao = (MotivoNaoAutorizacao) sessao.getAttribute("motivoNaoAutorizacaoAtualizar");
			}else{
				idMotivoNaoAutorizacao = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}

		//------Inicio da parte que verifica se vem da p�gina de
		// 		motivo_nao_autorizacao_manter.jsp
		if (motivoNaoAutorizacao == null){

			if (idMotivoNaoAutorizacao != null && !idMotivoNaoAutorizacao.equals("")) {
				FiltroMotivoNaoAutorizacao filtroMotivoNaoAutorizacao = new FiltroMotivoNaoAutorizacao();
				
				filtroMotivoNaoAutorizacao.adicionarParametro(new ParametroSimples(
						FiltroMotivoNaoAutorizacao.ID, idMotivoNaoAutorizacao));
				
				Collection<MotivoNaoAutorizacao> colecaoMotivoNaoAutorizacao = fachada.pesquisar(filtroMotivoNaoAutorizacao, MotivoNaoAutorizacao.class.getName());

				if (colecaoMotivoNaoAutorizacao != null && !colecaoMotivoNaoAutorizacao.isEmpty()) {
					motivoNaoAutorizacao = (MotivoNaoAutorizacao) Util.retonarObjetoDeColecao(colecaoMotivoNaoAutorizacao);
				}
			}
		}

		

		//  ------  O motivo nao autorizacao foi encontrado
		atualizarMotivoNaoAutorizacaoActionForm.setId(String.valueOf(motivoNaoAutorizacao.getId()));
		if(motivoNaoAutorizacao.getId() == null){
			atualizarMotivoNaoAutorizacaoActionForm.setId("");
		}else{
			atualizarMotivoNaoAutorizacaoActionForm.setId(String.valueOf(motivoNaoAutorizacao.getId()));
		}
		
		atualizarMotivoNaoAutorizacaoActionForm.setDescricao(String.valueOf(motivoNaoAutorizacao.getDescricao()));
				
		atualizarMotivoNaoAutorizacaoActionForm.setIndicadorUso(String.valueOf(motivoNaoAutorizacao.getIndicadorUso()));
		sessao.setAttribute("motivoNaoAutorizacao", motivoNaoAutorizacao);
		// ------ Fim da parte que verifica se vem da p�gina de motivo_nao_autorizacao_manter.jsp

		return retorno;

	}
}