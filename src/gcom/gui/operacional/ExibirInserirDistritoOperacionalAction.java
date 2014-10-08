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
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SetorSubsistemaAbastecimento;
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

/**
 * [UC0521]	INSERIR Distrito Operacional
 * 
 * @author Eduardo Bianchi
 * @date 29/01/2007
 */

public class ExibirInserirDistritoOperacionalAction extends GcomAction {
	
	@SuppressWarnings({ "rawtypes"})
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		
		ActionForward retorno = actionMapping.findForward("inserirDistritoOperacional");	
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		// Sistema Abastecimento		
		FiltroSistemaAbastecimento  filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
		filtroSistemaAbastecimento.adicionarParametro( new ParametroSimples
				(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO) );		
		
		Collection colecaoSistemaAbastecimento = fachada.pesquisar(
				filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
				
		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Sistema Abastecimento");
		}
		
		sessao.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
		return retorno;
	}
	
	
	public ActionForward atualizarListaSubsistemaAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		InserirDistritoOperacionalActionForm form = (InserirDistritoOperacionalActionForm)actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		String idSistemaPrincipal = form.getSistemaAbastecimento();
		
		Collection<SubsistemaSistemaAbastecimento> colecaoSub = 
				fachada.pesquisarSubSistemaSistemaAbastecimento(Integer.parseInt(idSistemaPrincipal));
		
		Collection<SubsistemaAbastecimento> colTelaPrincipal = new ArrayList<SubsistemaAbastecimento>();
		Iterator<SubsistemaSistemaAbastecimento> itTelaPrincipal =  colecaoSub.iterator();
		while(itTelaPrincipal.hasNext()){
			SubsistemaSistemaAbastecimento sub = itTelaPrincipal.next();
			colTelaPrincipal.add(sub.getSubsistemaAbastecimento());
		}
		
		sessao.setAttribute("colecaoSubsistemaPrincipal", colTelaPrincipal);
		sessao.removeAttribute("colecaoSetorAbastecimento");
		
		
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
	
	@SuppressWarnings("unchecked")
	public ActionForward atualizarListaSetorAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		InserirDistritoOperacionalActionForm form = (InserirDistritoOperacionalActionForm)actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		String idSistemaPrincipal = form.getSubsistemaAbastecimento();
		
		FiltroSetorAbastecimento filtro = new FiltroSetorAbastecimento();
		filtro.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		filtro.adicionarParametro(new ParametroSimples("subsistemaAbastecimento.id",idSistemaPrincipal));
		filtro.adicionarParametro(new ParametroSimples("setorAbastecimento.indicadorUso",ConstantesSistema.SIM));
		

		Collection<SetorSubsistemaAbastecimento> colecaoSetor = 
				(Collection<SetorSubsistemaAbastecimento>)fachada.pesquisar(filtro,SetorSubsistemaAbastecimento.class.getName());
		
		Collection<SetorAbastecimento> colTelaPrincipal = new ArrayList<SetorAbastecimento>();
		Iterator<SetorSubsistemaAbastecimento> itTelaPrincipal =  colecaoSetor.iterator();
		while(itTelaPrincipal.hasNext()){
			SetorSubsistemaAbastecimento sub = itTelaPrincipal.next();
			colTelaPrincipal.add(sub.getSetorAbastecimento());
		}
		
		sessao.setAttribute("colecaoSetorAbastecimento", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<SetorSubsistemaAbastecimento> it = colecaoSetor.iterator();
		while(it.hasNext()){
			SetorSubsistemaAbastecimento sub = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", sub.getSetorAbastecimento().getId());
				obj.append("descricao", sub.getSetorAbastecimento().getDescricao());
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
	
	
	@SuppressWarnings("unchecked")
	public ActionForward inserirSetorAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		
		InserirDistritoOperacionalActionForm form = (InserirDistritoOperacionalActionForm)actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		ActionForward retorno = actionMapping.findForward("inserirDistritoOperacional");	
		
		Collection<SetorAbastecimento> colecaoSetor = (Collection<SetorAbastecimento>)sessao.getAttribute("colecaoSetorAbastecimento");
		Collection<SetorAbastecimento> setoresForm = form.getSetoresSelecionados();
		String idSetorAbastecimento = form.getSetorAbastecimento();
		
		//verificar se o setor já foi adicionado à lista
		Iterator<SetorAbastecimento> itCheck = setoresForm.iterator();
		while(itCheck.hasNext()){
			SetorAbastecimento check = itCheck.next();
			if(check.getId().toString().equals(idSetorAbastecimento)){
				throw new ActionServletException(
						"atencao.setor_abastecimento_ja_adicionado");
			}
		}
		
		Iterator<SetorAbastecimento> it = colecaoSetor.iterator();
		while(it.hasNext()){
			SetorAbastecimento setor = it.next();
			if(setor.getId().compareTo(Integer.parseInt(idSetorAbastecimento)) == 0){
				setoresForm.add(setor);
				
				/*//Inserindo no objeto JSON
				JSONObject obj = new JSONObject();
				try {
					obj.append("id", setor.getId());
					obj.append("descricao", setor.getDescricao());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				try {
					httpServletResponse.getWriter().write(obj.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;*/
			}
		}
		
		return retorno;
	}
	
	
	public ActionForward removerSetorAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		InserirDistritoOperacionalActionForm form = (InserirDistritoOperacionalActionForm)actionForm;
		
		Collection<SetorAbastecimento> setoresForm = form.getSetoresSelecionados();
		String idARemover = httpServletRequest.getParameter("idSetorARemover");
		
		Iterator<SetorAbastecimento> it = setoresForm.iterator();
		while(it.hasNext()){
			SetorAbastecimento check = it.next();
			if(check.getId().toString().equals(idARemover)){
				it.remove();
				
				if (form.getIdSetorAbastecimentoPrincipal() != null && !form.getIdSetorAbastecimentoPrincipal().equals("") &&
					form.getIdSetorAbastecimentoPrincipal().equals(idARemover)){
						
					form.setIdSetorAbastecimentoPrincipal("");
				}
				
				break;
			}
		}
		
		//Inserindo no objeto JSON
		JSONObject obj = new JSONObject();
		try {
			obj.append("id", idARemover);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			httpServletResponse.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ActionForward botaolVoltar(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("inserirDistritoOperacional");				
		
		return retorno;
	}
	
	
}
