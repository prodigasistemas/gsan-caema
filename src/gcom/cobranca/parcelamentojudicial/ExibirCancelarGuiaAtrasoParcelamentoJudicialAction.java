package gcom.cobranca.parcelamentojudicial;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;

public class ExibirCancelarGuiaAtrasoParcelamentoJudicialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarManterParcelamentoJudicial filtroManterParcelamentoJudicial = new FiltrarManterParcelamentoJudicial();
		Fachada fachada = Fachada.getInstancia();
		Collection<CancelarGuiaAtrasoObterListaParcelamentoJudicialHelper> colecaoObterListaParcelamentoJudicial= new ArrayList<CancelarGuiaAtrasoObterListaParcelamentoJudicialHelper>();
		Collection<CancelarGuiaAtrasoParcelamentoJudicialHelper> colecaoGuiaAtraso= new ArrayList<CancelarGuiaAtrasoParcelamentoJudicialHelper>();
		DebitoTipo objetoDebitoTipo = new DebitoTipo();
		String idParcelamentoJudicial = httpServletRequest.getParameter("idParcelamentoJudicial");
		Integer debitoTipo = Integer.parseInt(httpServletRequest.getParameter("debitoTipo"));
		filtroManterParcelamentoJudicial.setIdParcelamentoJudicial(idParcelamentoJudicial);
		ActionForward retorno = actionMapping.findForward("exibirCancelarGuiaAtrasoParcelamentoJudicial");
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if(debitoTipo.equals(objetoDebitoTipo.GUIA_ADICION_ATRASO_PARC_JUD)){
			
			debitoTipo=objetoDebitoTipo.GUIA_ADICION_ATRASO_PARC_JUD;
			
		}
		
		colecaoObterListaParcelamentoJudicial = fachada.pesquisarObterListaParcelamentoJudicialCancelarGuiaAtraso(idParcelamentoJudicial);
		colecaoGuiaAtraso = fachada.pesquisarGuiasAtraso(idParcelamentoJudicial, debitoTipo);
		
		if (fachada.verificarPermissaoEspecial(PermissaoEspecial.CANCELAR_GUIA_DE_ATRASO_PARCELAMENTO_JUDICIAL, usuarioLogado)) {
			
			if(colecaoObterListaParcelamentoJudicial.size()>0){
				
				httpServletRequest.setAttribute("colecaoObterListaParcelamentoJudicial", colecaoObterListaParcelamentoJudicial);
				
			}else{
				throw new ActionServletException("atencao.nao_existe_guia_atraso_parcelamento_judicial");
			}
			
			if(colecaoGuiaAtraso.size()>0){
				
				httpServletRequest.setAttribute("colecaoGuiaAtraso", colecaoGuiaAtraso);
				
			}else{
				
				throw new ActionServletException("atencao.existem_pagamentos_guia_atraso_parcelamento_judicial");
				
			}

		}else{
			
			throw new ActionServletException("atencao.usuario_nao_possui_permissao_cancelar_guia_atraso");
			
		}
		
				
		return retorno;
	}
}
