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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.FiltroMotivoNaoAutorizacao;
import gcom.seguranca.MotivoNaoAutorizacao;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0383] MANTER MOTIVO DE N�O AUTORIZA��O
 * 
 * @author Ricardo Germinio
 * @date 30/07/2012
 */

public class ExibirManterMotivoNaoAutorizacaoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterMotivoNaoAutorizacao");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
 		Collection colecaoMotivoNaoAutorizacao = null;

		// Parte da verifica��o do filtro
		FiltroMotivoNaoAutorizacao filtroMotivoNaoAutorizacao = new FiltroMotivoNaoAutorizacao();
		
		// Organizar motivos por descri��o
				if (sessao.getAttribute("colecaoMotivoNaoAutorizacao") == null
						|| sessao.getAttribute("colecaoMotivoNaoAutorizacao").equals("")) {
					Fachada fachada = Fachada.getInstancia();
					filtroMotivoNaoAutorizacao.setCampoOrderBy(FiltroMotivoNaoAutorizacao.DESCRICAO);
					/*Collection<MotivoNaoAutorizacao> colecaoMotivoNaoAutorizacao = fachada.pesquisar(
							filtroMotivoNaoAutorizacao, MotivoNaoAutorizacao.class.getName());*/
					httpServletRequest.setAttribute("colecaoMotivoNaoAutorizacao",
							colecaoMotivoNaoAutorizacao);
				}
		
		// Verifica se o filtro foi informado pela p�gina de filtragem 
		if (sessao.getAttribute("filtroMotivoNaoAutorizacao") != null) {
			filtroMotivoNaoAutorizacao = (FiltroMotivoNaoAutorizacao) sessao
					.getAttribute("filtroMotivoNaoAutorizacao");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterMotivoNaoAutorizacao"))) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroMotivoNaoAutorizacao, MotivoNaoAutorizacao.class.getName());
			colecaoMotivoNaoAutorizacao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			// [UC 1357]FILTRAR MOTIVO DE N�O AUTORIZA��O
			
			if (colecaoMotivoNaoAutorizacao == null || colecaoMotivoNaoAutorizacao.isEmpty()) {
				throw new ActionServletException("atencao.motivo_nao_autorizacao_inexistente");
			}
			
			
			String identificadorAtualizar = (String)sessao.getAttribute("identificadorAtualizar");
			
			if (colecaoMotivoNaoAutorizacao.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro s� retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema n�o exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarMotivoNaoAutorizacao");
				MotivoNaoAutorizacao motivoNaoAutorizacao = (MotivoNaoAutorizacao) colecaoMotivoNaoAutorizacao.iterator().next();
				sessao.setAttribute("motivoNaoAutorizacaoAtualizar", motivoNaoAutorizacao);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMotivoNaoAutorizacaoAction.do");
				//chama ExibirAtualizarMotivoNaoAutorizacaoAction
			}else{
				httpServletRequest.setAttribute("colecaoMotivoNaoAutorizacao",
						colecaoMotivoNaoAutorizacao);
				//sessao.setAttribute("colecaoMotivoNaoAutorizacao", colecaoMotivoNaoAutorizacao);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMotivoNaoAutorizacaoAction.do");
				//chama ExibirManterMotivoNaoAutorizacaoAction
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
		
	} 
	
}