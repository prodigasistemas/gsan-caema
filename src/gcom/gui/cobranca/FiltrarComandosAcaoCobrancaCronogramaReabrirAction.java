package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0326] Filtrar Comandos de Ação de Cobrança
 * 
 * @author Rômulo Aurélio 
 * @Date 26/10/2012
 * 
 */

public class FiltrarComandosAcaoCobrancaCronogramaReabrirAction  extends GcomAction{
	
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("retornarComandosAcaoCobrancaCronogramaReabrir");
        
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm form = (FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm)actionForm; 
       
        
        @SuppressWarnings("rawtypes")
		Collection colecaoIdsComandos = fachada.consultarComandosAtividadeCronograma(
        			form.getPeriodoReferenciaCobrancaInicial(),
        			form.getPeriodoReferenciaCobrancaFinal(),
        			form.getGrupoCobranca(),
        			form.getAcaoCobranca(),
        			form.getPeriodoEncerramentoComandoInicial(),
        			form.getPeriodoEncerramentoComandoFinal());
       
        if(colecaoIdsComandos != null && !colecaoIdsComandos.isEmpty()){
	        
	        sessao.setAttribute("colecaoIdsComando",
	        	colecaoIdsComandos);
        }else{
        	throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        }
        
      
        sessao.setAttribute("tipoConsulta", "cronograma");
        sessao.setAttribute("form",form);
        
        return retorno;
    }

}
