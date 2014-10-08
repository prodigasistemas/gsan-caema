package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

/**
 * [UC 1581] - Alterar Data Prevista Encerramento Comando Acao Cobranca
 * 
 * @author Davi Menezes
 * @date 15/01/2014
 *
 */
public class ExibirAlterarDataPrevistaEncerramentoComandoAcaoCobrancaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("dataPrevistaEncerramentoAlterarPopup");
        
		//Sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		ConsultarComandosAcaoCobrancaActionForm form = (ConsultarComandosAcaoCobrancaActionForm) actionForm;
        
		if(form.getIdsRegistros() == null || form.getIdsRegistros().length < 1){
			throw new ActionServletException("atencao.nenhum_comando_selecionado_alterar_data_prevista");
		}
		
		String tipoComando = httpServletRequest.getParameter("tipoComando");
		if(tipoComando != null && !tipoComando.equals("")){
			if(tipoComando.equals("1")){
				form.setComando("C");
			}else{
				form.setComando("E");
			}
		}
		
        sessao.setAttribute("fechaPopup", "false");
		
		return retorno;
	}
}
