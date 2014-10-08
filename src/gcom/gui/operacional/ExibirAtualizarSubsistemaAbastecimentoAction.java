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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroSubsistemaSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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

public class ExibirAtualizarSubsistemaAbastecimentoAction extends GcomAction {

	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("subsistemaAbastecimentoAtualizar");
		Collection colecaoSASecundario = new ArrayList();

		AtualizarSubsistemaAbastecimentoActionForm atualizarSubsistemaAbastecimentoActionForm = (AtualizarSubsistemaAbastecimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = new ArrayList<SistemaAbastecimento>();
		
		if (sessao.getAttribute("colecaoSistemaAbastecimentoAtualizar") == null) {
			
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento(FiltroSistemaAbastecimento.DESCRICAO);
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			
			colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
			
			//Clonar a colecao para o campo de abastecimento secund�rio
	        colecaoSASecundario.addAll(colecaoSistemaAbastecimento);
	        
	        sessao.setAttribute("colecaoSistemaAbastecimentoAtualizar", colecaoSistemaAbastecimento);
	        sessao.setAttribute("colecaoSASecundario", colecaoSASecundario);
			
		}

		String idSubsistemaAbastecimento = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizar") != null){
			idSubsistemaAbastecimento = httpServletRequest.getParameter("idRegistroAtualizar");
		}
		else{
			idSubsistemaAbastecimento = (String) sessao.getAttribute("idRegistroAtualizar");
		}
		
		SubsistemaAbastecimento subsistemaAbastecimento= new SubsistemaAbastecimento();
						
		if (idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.trim().equals("") && Integer.parseInt(idSubsistemaAbastecimento) > 0) {

			FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento= new FiltroSubsistemaSistemaAbastecimento();
			filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, 
				Integer.valueOf(idSubsistemaAbastecimento)));
			
			filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("subsistemaAbastecimento");
			filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
			
			Collection<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = fachada.pesquisar(
				filtroSubsistemaSistemaAbastecimento, SubsistemaSistemaAbastecimento.class.getName());
			
			if (colecaoSubsistemaSistemaAbastecimento != null && !colecaoSubsistemaSistemaAbastecimento.isEmpty()) {
				subsistemaAbastecimento = ((SubsistemaSistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSubsistemaSistemaAbastecimento)).getSubsistemaAbastecimento();
			}

			atualizarSubsistemaAbastecimentoActionForm.setCodigo(subsistemaAbastecimento.getId().toString());
			atualizarSubsistemaAbastecimentoActionForm.setDescricao(subsistemaAbastecimento.getDescricao());
			atualizarSubsistemaAbastecimentoActionForm.setDescricaoAbreviada(subsistemaAbastecimento.getDescricaoAbreviada());
			atualizarSubsistemaAbastecimentoActionForm.setIndicadorUso(subsistemaAbastecimento.getIndicadorUso().toString());
			
			
			if (colecaoSubsistemaSistemaAbastecimento.size() > 0){
				
				String[] idsSistemaAbastecimentoSecundario = new String[colecaoSubsistemaSistemaAbastecimento.size() - 1];
				Iterator<SubsistemaSistemaAbastecimento> it = colecaoSubsistemaSistemaAbastecimento.iterator();
				
				int index = 0;
				
				while (it.hasNext()){
					
					SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento = (SubsistemaSistemaAbastecimento) it.next();
					
					if (subsistemaSistemaAbastecimento.getIndicadorPrincipal().equals(ConstantesSistema.SIM)){
						
						atualizarSubsistemaAbastecimentoActionForm.setIdSistemaAbastecimentoPrincipal(subsistemaSistemaAbastecimento.getSistemaAbastecimento().getId().toString());
					}
					else{
						
						idsSistemaAbastecimentoSecundario[index] = subsistemaSistemaAbastecimento.getSistemaAbastecimento().getId().toString();
					
						index++;
					}
				}
				
				atualizarSubsistemaAbastecimentoActionForm.setIdSistemaAbastecimentoSecundario(idsSistemaAbastecimentoSecundario);
			}
			

			if (sessao.getAttribute("colecaoSubsistemaSistemaAbastecimento") != null) {
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/filtrarSubsistemaAbastecimentoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSubsistemaAbastecimentoAction.do");
			}

		}
		
		
		 Iterator<SistemaAbastecimento> it = colecaoSASecundario.iterator();
		 while(it.hasNext()){
			 SistemaAbastecimento sa = (SistemaAbastecimento)it.next();
			 if(sa.getId().toString().equals(atualizarSubsistemaAbastecimentoActionForm.getIdSistemaAbastecimentoPrincipal())){
				 it.remove();
			 }
		 }

		return retorno;
	}
	
	
	public ActionForward atualizarListaAbastecimentoSecundario(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		
		 AtualizarSubsistemaAbastecimentoActionForm form = (AtualizarSubsistemaAbastecimentoActionForm) actionForm;
		 Collection colecaoSA = (Collection)this.getSessao(httpServletRequest).getAttribute("colecaoSistemaAbastecimento");
		 Collection colecaoClone = new ArrayList(colecaoSA);
		 
		 JSONArray array = new JSONArray();
		 
		 String idSistemaAbastecimento = form.getIdSistemaAbastecimentoPrincipal();
		 
		 Iterator it = colecaoClone.iterator();
		 while(it.hasNext()){
			 SistemaAbastecimento sa = (SistemaAbastecimento)it.next();
			 if(sa.getId().toString().equals(idSistemaAbastecimento)){
				 it.remove();
			 }
			 else{
				 JSONObject obj = new JSONObject();
				 try {
					obj.append("id", sa.getId().toString());
					obj.append("descricao", sa.getDescricao());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				 array.put(obj);
			 }
		 }
		
		 this.getSessao(httpServletRequest).setAttribute("colecaoSASecundario", colecaoClone);
		
		 try {
			httpServletResponse.getWriter().write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 return null;
	}
	
}
