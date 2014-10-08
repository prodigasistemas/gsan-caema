package gcom.gui.atendimentopublico.ordemservico;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

/**
 * [UC 1542] - Filtrar Natureza de Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class ExibirFiltrarNaturezaEquipeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarNaturezaEquipe");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarNaturezaEquipeActionForm form = (FiltrarNaturezaEquipeActionForm) actionForm;
		
		String menu = httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.setIndicadorUso(""+ConstantesSistema.TODOS);
			sessao.setAttribute("indicadorAtualizar", ""+ConstantesSistema.SIM);
		}
		
		return retorno;
	}
	
}
