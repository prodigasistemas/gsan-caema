package gcom.gui.operacional.abastecimento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

/**
 * [UC1521] Manter SubSistema Esgoto
 * 
 * @author Maxwell Moreira
 * @date 05/07/2013
 */
public class RemoverSubSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
	
	        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();
	
	        ActionForward retorno = actionMapping.findForward("telaSucesso");
	
	        Fachada fachada = Fachada.getInstancia();
	
	        // mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
	        // nenhum registro
	        if (ids == null || ids.length == 0) {
	            throw new ActionServletException("atencao.registros.nao_selecionados");
	        }
	
	       fachada.removerSubSistemaEsgoto(ids);
	        
	        //Monta a p�gina de sucesso
	        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
	            montarPaginaSucesso(httpServletRequest,ids.length + " Subsistema(s) de Esgoto removido(s) com sucesso.",
	                    "Realizar outra Manuten��o de Subsistema de Esgoto",
	                    "exibirFiltrarSubSistemaEsgotoAction.do?menu=sim");
	        }
	
	        return retorno;

	}

}
