package gcom.cobranca.parcelamentojudicial;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

public class ManterParcelamentoJudicialConsultarParcelamentoJudicialAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		FiltrarManterParcelamentoJudicial filtroManterParcelamentoJudicial = new FiltrarManterParcelamentoJudicial();
		Collection<ManterParcelamentoJudicialConsultarParcelamentoJudicialHelper>
		colecaoManterParcelamentoJudicialConsultarParcelamentoJudicial = null;
		ActionForward retorno = actionMapping.findForward("manterParcelamentoJudicialConsultarParcelamentoJudicial");
		
		
		filtroManterParcelamentoJudicial.setIdParcelamentoJudicial(httpServletRequest.getParameter("id"));
		
		colecaoManterParcelamentoJudicialConsultarParcelamentoJudicial = fachada.pesquisarConsultarParcelamentoJudicial(filtroManterParcelamentoJudicial);
		
		
		httpServletRequest.setAttribute("colecaoManterParcelamentoJudicialConsultarParcelamentoJudicial", colecaoManterParcelamentoJudicialConsultarParcelamentoJudicial);
		
		return retorno;
	}
}
