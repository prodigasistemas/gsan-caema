package gcom.gui.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

/**
 * [UC 1581] - Alterar Data Prevista Encerramento Comando Acao Cobranca
 * 
 * @author Davi Menezes
 * @date 15/01/2014
 *
 */
public class AlterarDataPrevistaEncerramentoComandoAcaoCobrancaPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("dataPrevistaEncerramentoAlterarPopup");
        
		//Sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		ConsultarComandosAcaoCobrancaActionForm form = (ConsultarComandosAcaoCobrancaActionForm) actionForm;
		
		//Coleção dos Comandos
		Collection<Integer> colecaoIdsComandos = new ArrayList<Integer>();
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		if(form.getDataPrevistaEncerramento() == null || form.getDataPrevistaEncerramento().equals("")){
			throw new ActionServletException("atencao.campo.informada", null, "Data Prevista Encerramento");
		}
		
		if(Util.validarDiaMesAno(form.getDataPrevistaEncerramento())){
			throw new ActionServletException("atencao.data.invalida");
		}
		
		if(form.getIdsRegistros() != null && form.getIdsRegistros().length > 0){
			for(int i = 0; i < form.getIdsRegistros().length; i++){
				colecaoIdsComandos.add(Integer.parseInt(form.getIdsRegistros()[i]));
			}
		}
		
		Date dataPrevista = Util.converteStringParaDate(form.getDataPrevistaEncerramento());
		
		fachada.atualizarDataPrevistaEncerramentoComando(colecaoIdsComandos, dataPrevista, form.getComando());
		
		if(form.getComando() != null && !form.getComando().equals("")){
			if(form.getComando().equalsIgnoreCase("C")){
				sessao.setAttribute("retornarTela", "filtrarComandosAcaoCobrancaCronogramaAction.do?reloadPage=OK");
			}else{
				sessao.setAttribute("retornarTela", "filtrarComandosAcaoCobrancaEventualAction.do?reloadPage=OK");
			}
		}
		
		sessao.setAttribute("fechaPopup", "true");
		
		return retorno;
	}
}
