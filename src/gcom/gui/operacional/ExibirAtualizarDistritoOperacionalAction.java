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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.DistritoSetorAbastecimento;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroDistritoSetorAbastecimento;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SetorSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
 * [UC0522] Manter Distrito Operacional [SB0001]Atualizar Distrito Operacional
 *
 * @author Eduardo Bianchi
 * @date 09/02/2007
 */

public class ExibirAtualizarDistritoOperacionalAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarDistritoOperacional");				
		AtualizarDistritoOperacionalActionForm form = (AtualizarDistritoOperacionalActionForm)actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		String idDistritoOperacional = (String)httpServletRequest.getParameter("idDistritoOperacional");
		if(!Util.verificarIdNaoVazio(idDistritoOperacional)){
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Distrito Operacional");
		}
		
		
		// Sistema Abastecimento
		//-----------------------------------------------------------------------------------------
		FiltroSistemaAbastecimento  filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
		filtroSistemaAbastecimento.adicionarParametro( new ParametroSimples
				(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO) );		
		
		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(
				filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
				
		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Sistema Abastecimento");
		}
		
		sessao.setAttribute("colecaoSistemaAbastecimentoAtualizar", colecaoSistemaAbastecimento);
		//-----------------------------------------------------------------------------------------
		
		//Carregando par�metros
		FiltroDistritoOperacional filtro = new FiltroDistritoOperacional();
		filtro.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID,idDistritoOperacional));
		

		DistritoOperacional distrito = (DistritoOperacional)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, DistritoOperacional.class.getName()));
				
		//Preenchendo o formul�rio
		form.setCodigoDistritoOperacional(distrito.getId().toString());
		form.setDescricao(distrito.getDescricao());
		form.setDescricaoAbreviada(distrito.getDescricaoAbreviada());
		form.setIndicadorUso(distrito.getIndicadorUso().toString());
		form.setUltimaAlteracao(new Date());
		
		//Buscando os setores de abastecimento do distrito operacional
		FiltroDistritoSetorAbastecimento filtroDistritoSet = new FiltroDistritoSetorAbastecimento();
		filtroDistritoSet.adicionarCaminhoParaCarregamentoEntidade(FiltroDistritoSetorAbastecimento.SETOR_ABASTECIMENTO);
		filtroDistritoSet.adicionarParametro(new ParametroSimples(FiltroDistritoSetorAbastecimento.DISTRITO_OPERACIONAL_ID, idDistritoOperacional));
		
		Collection<DistritoSetorAbastecimento> colecaoDistritoSetor = 
				(Collection<DistritoSetorAbastecimento>)fachada.pesquisar(filtroDistritoSet, DistritoSetorAbastecimento.class.getName());
		
		
		//Montando a lista de setores selecionados
		if(colecaoDistritoSetor != null && !colecaoDistritoSetor.isEmpty()){
			Collection<SetorAbastecimento> colecaoSetores = new ArrayList<SetorAbastecimento>();
			Iterator<DistritoSetorAbastecimento> itSetor = colecaoDistritoSetor.iterator();
			while(itSetor.hasNext()){
				DistritoSetorAbastecimento distritoSet = itSetor.next();
				colecaoSetores.add(distritoSet.getSetorAbastecimento());
				
				//Marcar o radio caso ele seja o principal
				if(distritoSet.getIndicadorPrincipal().compareTo(ConstantesSistema.SIM) == 0){
					form.setIdSetorAbastecimentoPrincipal(distritoSet.getSetorAbastecimento().getId().toString());
				}
			}
			
			form.setSetoresSelecionados(colecaoSetores);
		}
		
		return retorno;
	}
	
	
	public ActionForward atualizarListaSubsistemaAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		AtualizarDistritoOperacionalActionForm form = (AtualizarDistritoOperacionalActionForm)actionForm;
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
		
		sessao.setAttribute("colecaoSubsistemaPrincipalAtualizar", colTelaPrincipal);
		sessao.removeAttribute("colecaoSetorAbastecimentoAtualizar");
		
		
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
		
		AtualizarDistritoOperacionalActionForm form = (AtualizarDistritoOperacionalActionForm)actionForm;
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
		
		sessao.setAttribute("colecaoSetorAbastecimentoAtualizar", colTelaPrincipal);
		
		
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
		
		
		AtualizarDistritoOperacionalActionForm form = (AtualizarDistritoOperacionalActionForm)actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		ActionForward retorno = actionMapping.findForward("atualizarDistritoOperacional");	
		
		Collection<SetorAbastecimento> colecaoSetor = (Collection<SetorAbastecimento>)sessao.getAttribute("colecaoSetorAbastecimentoAtualizar");
		Collection<SetorAbastecimento> setoresForm = form.getSetoresSelecionados();
		String idSetorAbastecimento = form.getSetorAbastecimento();
		
		//verificar se o setor j� foi adicionado � lista
		Iterator<SetorAbastecimento> itCheck = setoresForm.iterator();
		while(itCheck.hasNext()){
			SetorAbastecimento check = itCheck.next();
			if(check.getId().toString().equals(idSetorAbastecimento)){
				throw new ActionServletException("atencao.setor_abastecimento_ja_adicionado", "exibirAtualizarDistritoOperacionalAction.do?action=botaolVoltar", null);
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
		
		AtualizarDistritoOperacionalActionForm form = (AtualizarDistritoOperacionalActionForm)actionForm;
		
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
		
		ActionForward retorno = actionMapping.findForward("atualizarDistritoOperacional");				
		
		return retorno;
	}
	

}
					
		


