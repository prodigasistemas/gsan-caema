package gcom.gui.cobranca.cobrancaporresultado;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1318] Filtrar Motivo de Não Geração Cobrança por Resultado
 * 
 * @author Hugo Azevedo
 * @date 17/04/2012
 */
public class ExibirFiltrarMotivoNaoGeracaoCobrancaResultadoAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto
        ActionForward retorno = actionMapping.findForward("filtrarMotivoNaoGeracaoCobrancaResultado");
        
        FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm form = (FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm) actionForm;
        
        //Primeiro acesso, setando valores iniciais
        if(httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
	        form.setIndicadorUso("3");
	        form.setTipoMotivo("4");
	        form.setTipoDescricao("1");
        }
    	
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
