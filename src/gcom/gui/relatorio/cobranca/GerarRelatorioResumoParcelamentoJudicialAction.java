package gcom.gui.relatorio.cobranca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioEmitirResumoParcelamentoJudicial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

/**
 * 
 * [UC1461] Emitir Resumo do Parcelamento Judicial
 * 
 * @author Maxwell Moreira
 *
 * @date 18/04/2013
 */

public class GerarRelatorioResumoParcelamentoJudicialAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
				
		ActionForward retorno = null;
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		String idParcelamentoJudicial = (String) httpServletRequest.getParameter("idParcelamentoJudicial");
		
		TarefaRelatorio relatorio = new RelatorioEmitirResumoParcelamentoJudicial((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("idParcelamentoJudicial",idParcelamentoJudicial);
		
		try {	
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
		} catch (SistemaException ex) {
			
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
		
		return retorno;
		
	}
}