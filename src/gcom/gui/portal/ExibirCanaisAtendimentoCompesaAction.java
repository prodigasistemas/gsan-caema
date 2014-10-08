package gcom.gui.portal;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir Canais de Atendimento
 * 
 * @author Erivan Sousa
 * @date 28/06/2011
 */
public class ExibirCanaisAtendimentoCompesaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String retorno = "exibirCanaisAtendimentoCompesaAction";
		
		String method = httpServletRequest.getParameter("method");
		String ip = httpServletRequest.getRemoteAddr();
		
		if(method.equalsIgnoreCase("teleatendimento")){
			retorno = "exibirTeleAtendimentoCompesaAction";
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.TELE_ATENDIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		}		
		
		if(method.equalsIgnoreCase("autoatendimento")){
			retorno = "exibirAutoAtendimentoCompesaAction";
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.AUTO_ATENDIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		}
		
		return actionMapping.findForward(retorno);
	}	
}