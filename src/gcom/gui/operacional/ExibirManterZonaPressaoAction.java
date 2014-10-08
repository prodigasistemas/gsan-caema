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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.bean.ZonaPressaoHelper;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vinícius Medeiros
 * 
 */
public class ExibirManterZonaPressaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	ActionForward retorno = actionMapping.findForward("exibirManterZonaPressao");
		
    	HttpSession sessao = httpServletRequest.getSession(false);
    	Fachada fachada = Fachada.getInstancia();
    	
    	FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm = (FiltrarZonaPressaoActionForm) actionForm;
		
    	String codigo = filtrarZonaPressaoActionForm.getId();
		String descricao = filtrarZonaPressaoActionForm.getDescricao();
		String descricaoAbreviada = filtrarZonaPressaoActionForm.getDescricaoAbreviada();
		String tipoPesquisa = filtrarZonaPressaoActionForm.getTipoPesquisa();
		String sistemaAbastecimento = filtrarZonaPressaoActionForm.getIdSistemaAbastecimento();
		String subsistemaAbastecimento = filtrarZonaPressaoActionForm.getIdSubsistemaAbastecimento();
		String setorAbastecimento = filtrarZonaPressaoActionForm.getIdSetorAbastecimento();
		String areaOperacional = filtrarZonaPressaoActionForm.getIdAreaOperacional();
		String indicadorUso = filtrarZonaPressaoActionForm.getIndicadorUso();
		String idDistritoOperacional = filtrarZonaPressaoActionForm.getIdDistritoOperacional();
        
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("exibirManterZonaPressao"))) {
			
			Integer totalRegistros = fachada.obterZonaPressaoCount(codigo, descricao, tipoPesquisa, descricaoAbreviada, sistemaAbastecimento,
				subsistemaAbastecimento, setorAbastecimento, areaOperacional, indicadorUso,idDistritoOperacional);
			
			retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
			
			Collection<ZonaPressaoHelper> colecaoZonaPressao = fachada.obterZonaPressao(codigo, descricao, tipoPesquisa, descricaoAbreviada, sistemaAbastecimento,
				subsistemaAbastecimento, setorAbastecimento, areaOperacional, indicadorUso,
				(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"),idDistritoOperacional);
			
			
			// [FS0003] Nenhum registro encontrado				
			if (colecaoZonaPressao == null || colecaoZonaPressao.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoZonaPressao.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarZonaPressao");
				ZonaPressaoHelper zonaPressaoHelper = (ZonaPressaoHelper) colecaoZonaPressao.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", zonaPressaoHelper.getIdZonaPressao().toString());
			}
			else{
				sessao.setAttribute("colecaoZonaPressao", colecaoZonaPressao);
			}

		}

		sessao.removeAttribute("UseCase");
		return retorno;
    }
}