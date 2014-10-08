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
package gcom.gui.relatorio.operacional;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.AreaOperacional;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.DistritoSetorAbastecimento;
import gcom.operacional.FiltroDistritoSetorAbastecimento;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroZonaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SetorSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.operacional.ZonaAreaOperacional;
import gcom.operacional.ZonaPressao;
import gcom.operacional.bean.AreaOperacionalHelper;
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
 * 
 * @author Hugo Azevedo
 * @since 19/06/2014
 */
public class ExibirGerarRelatorioTotalizadorSistemaAbastecimentoAction extends GcomAction {

    @SuppressWarnings("unchecked")
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorio");
		GerarRelatorioTotalizadorSistemaAbastecimentoActionForm form = 
				(GerarRelatorioTotalizadorSistemaAbastecimentoActionForm)actionForm;
		
		Fachada fachada = this.getFachada();
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		//Sistema de abastecimento
		 FiltroSistemaAbastecimento filtroSA = new FiltroSistemaAbastecimento();
		 filtroSA.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.SIM));

		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = 
				(Collection<SistemaAbastecimento>)fachada.pesquisar(filtroSA, SistemaAbastecimento.class.getName());       
        sessao.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
        //Tipo de relat�rio
        //Sint�tico por padr�o
        form.setTipoRelatorio("1");
		
		return retorno;

	}
	
	
	public ActionForward atualizarListaSubsistemaAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(1,sessao);
		
		GerarRelatorioTotalizadorSistemaAbastecimentoActionForm form = 
				(GerarRelatorioTotalizadorSistemaAbastecimentoActionForm)actionForm;
		
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
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(2,sessao);
		
		GerarRelatorioTotalizadorSistemaAbastecimentoActionForm form = 
				(GerarRelatorioTotalizadorSistemaAbastecimentoActionForm)actionForm;
		
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
	public ActionForward atualizarListaDistritoOperacional(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(3,sessao);
		
		GerarRelatorioTotalizadorSistemaAbastecimentoActionForm form = 
				(GerarRelatorioTotalizadorSistemaAbastecimentoActionForm)actionForm;
		
		String idSetorAbastecimento = form.getSetorAbastecimento();
		
		
		FiltroDistritoSetorAbastecimento filtro = new FiltroDistritoSetorAbastecimento();
		filtro.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtro.adicionarParametro(new ParametroSimples("setorAbastecimento.id",idSetorAbastecimento));
		filtro.adicionarParametro(new ParametroSimples("distritoOperacional.indicadorUso",ConstantesSistema.SIM));
		

		Collection<DistritoSetorAbastecimento> colecaoSetor = 
				(Collection<DistritoSetorAbastecimento>)fachada.pesquisar(filtro,DistritoSetorAbastecimento.class.getName());
		
		Collection<DistritoOperacional> colTelaPrincipal = new ArrayList<DistritoOperacional>();
		Iterator<DistritoSetorAbastecimento> itTelaPrincipal =  colecaoSetor.iterator();
		while(itTelaPrincipal.hasNext()){
			DistritoSetorAbastecimento dist = itTelaPrincipal.next();
			colTelaPrincipal.add(dist.getDistritoOperacional());
		}
		
		sessao.setAttribute("colecaoDistritoOperacional", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<DistritoOperacional> it = colTelaPrincipal.iterator();
		while(it.hasNext()){
			DistritoOperacional dist = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", dist.getId());
				obj.append("descricao", dist.getDescricao());
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
	
	
	public ActionForward atualizarListaAreaOperacional(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(4,sessao);
		
		GerarRelatorioTotalizadorSistemaAbastecimentoActionForm form = 
				(GerarRelatorioTotalizadorSistemaAbastecimentoActionForm)actionForm;
		
		String idDistritoOperacional = form.getDistritoOperacional();
		
		Collection<AreaOperacionalHelper> colAreaOperacional 
			= fachada.pesquisarAreaOperacional(null, null, null, null, idDistritoOperacional, null, null);
		
		Collection<AreaOperacional> colTelaPrincipal = new ArrayList<AreaOperacional>();
		Iterator<AreaOperacionalHelper> itTelaPrincipal =  colAreaOperacional.iterator();
		while(itTelaPrincipal.hasNext()){
			AreaOperacionalHelper dist = itTelaPrincipal.next();
			AreaOperacional area = new AreaOperacional();
			area.setId(dist.getIdAreaOperacional());
			area.setDescricao(dist.getDescricaoAreaOperacional());
			colTelaPrincipal.add(area);
		}
		
		sessao.setAttribute("colecaoAreaOperacional", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<AreaOperacional> it = colTelaPrincipal.iterator();
		while(it.hasNext()){
			AreaOperacional area = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", area.getId());
				obj.append("descricao", area.getDescricao());
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
	public ActionForward atualizarListaZonaPressao(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();
		
		this.removerColecaoSessao(5,sessao);
		
		GerarRelatorioTotalizadorSistemaAbastecimentoActionForm form = 
				(GerarRelatorioTotalizadorSistemaAbastecimentoActionForm)actionForm;
		
		String idAreaOperacional = form.getAreaOperacional();
		
		FiltroZonaAbastecimento filtro = new FiltroZonaAbastecimento();
		filtro.adicionarCaminhoParaCarregamentoEntidade("zonaPressao");
		filtro.adicionarParametro(new ParametroSimples("areaOperacional.id",idAreaOperacional));
		filtro.adicionarParametro(new ParametroSimples("zonaPressao.indicadorUso",ConstantesSistema.SIM));
		

		Collection<ZonaAreaOperacional> colecaoZona = 
				(Collection<ZonaAreaOperacional>)fachada.pesquisar(filtro,ZonaAreaOperacional.class.getName());
		
		Collection<ZonaPressao> colTelaPrincipal = new ArrayList<ZonaPressao>();
		Iterator<ZonaAreaOperacional> itTelaPrincipal =  colecaoZona.iterator();
		while(itTelaPrincipal.hasNext()){
			ZonaAreaOperacional zona = itTelaPrincipal.next();
			colTelaPrincipal.add(zona.getZonaPressao());
		}
		
		sessao.setAttribute("colecaoZonaPressao", colTelaPrincipal);
		
		
		//Inserindo no objeto JSON
		JSONArray arrayJSON = new JSONArray();
		
		Iterator<ZonaPressao> it = colTelaPrincipal.iterator();
		while(it.hasNext()){
			ZonaPressao zona = it.next();
			JSONObject obj = new JSONObject();
			try {
				obj.append("id", zona.getId());
				obj.append("descricao", zona.getDescricaoZonaPressao());
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
	
	
	
	public ActionForward botaoVoltar(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return actionMapping.findForward("exibirGerarRelatorio");
		
	}
	
	private void removerColecaoSessao(int metodo, HttpSession sessao){
		
		switch(metodo){
			case 1:
				sessao.removeAttribute("colecaoSetorAbastecimento");
				sessao.removeAttribute("colecaoSetorAbastecimento");
				sessao.removeAttribute("colecaoDistritoOperacional");
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
				
			case 2:
				sessao.removeAttribute("colecaoSetorAbastecimento");
				sessao.removeAttribute("colecaoDistritoOperacional");
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
			case 3:
				sessao.removeAttribute("colecaoDistritoOperacional");
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
			case 4:
				sessao.removeAttribute("colecaoAreaOperacional");
				sessao.removeAttribute("colecaoZonaPressao");
			case 5:
				sessao.removeAttribute("colecaoZonaPressao");
		}
		
	}
	
	

}
