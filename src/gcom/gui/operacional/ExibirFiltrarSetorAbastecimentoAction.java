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
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class ExibirFiltrarSetorAbastecimentoAction extends GcomAction {
	
	@SuppressWarnings({"unchecked" })
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		FiltrarSetorAbastecimentoActionForm form = (FiltrarSetorAbastecimentoActionForm)actionForm;
		ActionForward retorno = actionMapping.findForward("filtrarSetorAbastecimento");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		//Coleção de sistema de água
		FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
		filtro.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.INDICADOR_USO,ConstantesSistema.SIM));
		
		Collection<SistemaAbastecimento> colecao = 
				(Collection<SistemaAbastecimento>)fachada.pesquisar(filtro, SistemaAbastecimento.class.getName());
		
		sessao.setAttribute("colecaoSA", colecao);
		
		//Valores iniciais
		if(form.getIndicadorPosicaoTexto() == null)
			form.setIndicadorPosicaoTexto("1"); //Iniciado pelo texto
		if(form.getIndicadorUso() == null)
			form.setIndicadorUso("3"); //Todos
		
		
		//Check de atualizar
		form.setIndicadorAtualizar(ConstantesSistema.SIM+"");
		
		return retorno;
	}
	
	
	
	public ActionForward atualizarListaSubsistema(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarSetorAbastecimentoActionForm form = (FiltrarSetorAbastecimentoActionForm)actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		String idSistemaPrincipal = form.getSistemaAgua();
		
		Collection<SubsistemaSistemaAbastecimento> colecaoSub = 
				fachada.pesquisarSubSistemaSistemaAbastecimento(Integer.parseInt(idSistemaPrincipal));
		
		Collection<SubsistemaAbastecimento> colTelaPrincipal = new ArrayList<SubsistemaAbastecimento>();
		Iterator<SubsistemaSistemaAbastecimento> itTelaPrincipal =  colecaoSub.iterator();
		while(itTelaPrincipal.hasNext()){
			SubsistemaSistemaAbastecimento sub = itTelaPrincipal.next();
			colTelaPrincipal.add(sub.getSubsistemaAbastecimento());
		}
		
		sessao.setAttribute("colecaoSubsistemaPrincipal", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<SubsistemaSistemaAbastecimento> it = colecaoSub.iterator();
		while(it.hasNext()){
			SubsistemaSistemaAbastecimento sub = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", sub.getSubsistemaAbastecimento().getId());
				obj.append("descricao", sub.getSubsistemaAbastecimento().getDescricao());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			arrayJSON.put(obj);
		}
		
		try {
			httpServletResponse.getWriter().write(arrayJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
