package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1580] - Cancelar Comando Acao Cobranca
 * 
 * @author Davi Menezes
 * @date 16/01/2014
 *
 */
public class CancelarComandoAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		ConsultarComandosAcaoCobrancaActionForm form = (ConsultarComandosAcaoCobrancaActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Colecao de Comandos
		Collection<Integer> colecaoIdsComandos = new ArrayList<Integer>();
		
		//Tipo de Comando
		String tipoComando = httpServletRequest.getParameter("tipoComando");
		
		//Usuario Logado no Sistema
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if(form.getIdsRegistros() == null || form.getIdsRegistros().length < 1){
			throw new ActionServletException("atencao.nenhum_comando_selecionado_cancelar_comando");
		}else{
			for(int i = 0; i < form.getIdsRegistros().length; i++){
				colecaoIdsComandos.add(Integer.parseInt(form.getIdsRegistros()[i]));
			}
		}
		
		//Cancelar os Comandos das Ações de Cobrança
		fachada.cancelarComandoAcaoCobranca(colecaoIdsComandos, tipoComando, usuarioLogado);
		
		montarPaginaSucesso(httpServletRequest, "Comandos de Ações de cobranças cancelados com sucesso", 
			"Consultar Comando de Atividade de Ação de Cobrança", 
			"exibirConsultarComandosAcaoCobrancaAction.do?menu=sim");
		
		return retorno;
	}
}
