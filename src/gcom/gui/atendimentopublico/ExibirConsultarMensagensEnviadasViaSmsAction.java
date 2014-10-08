package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.FiltroSmsTipo;
import gcom.atendimentopublico.SmsTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1070] Consultar Quantidade Acessos a base da Receita Federal
 * 
 * @author Ricardo Germinio
 * @date 27/09/2010
 *
 */

public class ExibirConsultarMensagensEnviadasViaSmsAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
				findForward("exibirConsultarMensagensEnviadasViaSms");
		Fachada fachada = Fachada.getInstancia();
		ExibirConsultarMensagensEnviadasViaSmsActionForm form = (ExibirConsultarMensagensEnviadasViaSmsActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(form.getSituacaoMensagem()==null){
			form.setSituacaoMensagem("3");
		}
		
		FiltroSmsTipo filtroSmsTipo = new FiltroSmsTipo();
        filtroSmsTipo.adicionarParametro(new ParametroSimples(FiltroSmsTipo.INDICADOR_USO, ConstantesSistema.SIM));
        Collection colecaoSmsTipo = fachada.pesquisar(filtroSmsTipo, SmsTipo.class.getName());


        sessao.setAttribute("colecaoSmsTipo", colecaoSmsTipo);      

	    return retorno;
	}
}
 