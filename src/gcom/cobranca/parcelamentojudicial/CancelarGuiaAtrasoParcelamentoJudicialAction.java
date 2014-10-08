package gcom.cobranca.parcelamentojudicial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

public class CancelarGuiaAtrasoParcelamentoJudicialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String[] idsGuiaAtraso = httpServletRequest.getParameterValues("idsGuiaAtraso");
		boolean verificarCancelamentoGuiaAtraso;
		ActionForward retorno = actionMapping.findForward("concluirPoupCancelar");
		Fachada fachada = Fachada.getInstancia();
		
		verificarCancelamentoGuiaAtraso = fachada.cancelarGuiaAtraso(idsGuiaAtraso);
		
		return retorno;
	}
}
