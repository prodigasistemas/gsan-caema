package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarComandosAcaoCobrancaEventualReabrirAction extends GcomAction{
	
	
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

        ActionForward retorno = actionMapping.findForward("retornarComandosAcaoCobrancaEventualReabrir");
        
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarComandosAcaoCobrancaEventualReabrirActionForm filtrarComandosAcaoCobrancaEventualReabrirActionForm = (FiltrarComandosAcaoCobrancaEventualReabrirActionForm)actionForm; 

        if(sessao.getAttribute("filtroCobrancaAcaoAtividadeComando") == null){
	        
			//[FS0014 - Validar período de emissão];
			String dataInicial = filtrarComandosAcaoCobrancaEventualReabrirActionForm.getDataEmissaoInicio();
			String dataFinal = filtrarComandosAcaoCobrancaEventualReabrirActionForm.getDataEmissaoFim();
		
			if ((dataInicial.trim().length() == 10)
					&& (dataFinal.trim().length() == 10)) {
				
				Calendar calendarInicio = new GregorianCalendar();
				Calendar calendarFim = new GregorianCalendar();
	            
	            calendarInicio.setTime( Util.converteStringParaDate( dataInicial ) );
	            calendarFim.setTime( Util.converteStringParaDate( dataFinal ) );

				if (calendarFim.compareTo(calendarInicio) < 0) {
					throw new ActionServletException(
							"atencao.data_fim_menor_inicio");
				}
			}
	        
	        	       
	        Collection colecaoIdsComandos = fachada.consultarComandosAtividadeCronogramaEventual(
    			filtrarComandosAcaoCobrancaEventualReabrirActionForm.getGrupoCobranca(),
    			filtrarComandosAcaoCobrancaEventualReabrirActionForm.getAcaoCobranca(),
    			filtrarComandosAcaoCobrancaEventualReabrirActionForm.getPeriodoEncerramentoComandoInicial(),
    			filtrarComandosAcaoCobrancaEventualReabrirActionForm.getPeriodoEncerramentoComandoFinal(),
    			filtrarComandosAcaoCobrancaEventualReabrirActionForm.getCriterioCobranca(),
    			filtrarComandosAcaoCobrancaEventualReabrirActionForm.getDataEmissaoInicio(),
    			filtrarComandosAcaoCobrancaEventualReabrirActionForm.getDataEmissaoFim());
	        
	        
	        if(colecaoIdsComandos != null && !colecaoIdsComandos.isEmpty()){
	        
		        sessao.setAttribute("colecaoIdsComando",
		        	colecaoIdsComandos);
	        }else{
	        	throw new ActionServletException("atencao.pesquisa.nenhumresultado");
	        }
	        
	        
	        sessao.setAttribute("filtrarComandosAcaoCobrancaEventualReabrirActionForm",
	        	filtrarComandosAcaoCobrancaEventualReabrirActionForm);    
	        sessao.setAttribute("tipoConsulta", "eventual");
        }
        
        return retorno;
    }

}
