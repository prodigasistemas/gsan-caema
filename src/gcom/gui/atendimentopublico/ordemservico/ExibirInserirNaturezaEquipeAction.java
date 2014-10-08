package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1541] - Inserir Natureza Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class ExibirInserirNaturezaEquipeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("naturezaEquipeInserir");
		
		return retorno;
	}
}
