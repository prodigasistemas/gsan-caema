package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.ComandoOsSetor;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
 * 
 * @author Vivianne Sousa
 * @since 12/07/2011
 */
public class ExibirConsultarComandosOSSeletivaInspecaoAnormalidadePopupAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarComandosOSSeletivaInspecaoAnormalidadePopup");

		HttpSession sessao = httpServletRequest.getSession(false);
	
		Fachada fachada = Fachada.getInstancia();
		
		String idComando = httpServletRequest.getParameter("idComando");
		
		sessao.removeAttribute("comandoOrdemSeletiva");
		sessao.removeAttribute("colecaoAnormalidadeComandoOSS");
		sessao.removeAttribute("colecaoCapacidHidrComandoOSS");
		sessao.removeAttribute("colecaoLigacaoSitComandoOSS");
		sessao.removeAttribute("setoresComponent");
		sessao.removeAttribute("exibeTabelaLocalidade");
		
		ComandoOrdemSeletiva comandoOrdemSeletiva = fachada.
			pesquisarDadosComandoOSSeletiva(new Integer(idComando));
		sessao.setAttribute("comandoOrdemSeletiva", comandoOrdemSeletiva);		
		Collection<ComandoOsSetor> setores = fachada.pesquisarSetoresAssociadosComandoOrdemSeletiva(new Integer(idComando));
		if(setores !=null && !setores.isEmpty()){
			sessao.setAttribute("setoresComponent", setores);
			sessao.setAttribute("exibeTabelaLocalidade", true);
		}
		
		Collection colecaoAnormalidadeComandoOSS = fachada.pesquisarDadosAnormalidadeComandoOSS(new Integer(idComando));
		if(colecaoAnormalidadeComandoOSS != null && !colecaoAnormalidadeComandoOSS.isEmpty()){
			sessao.setAttribute("colecaoAnormalidadeComandoOSS", colecaoAnormalidadeComandoOSS);
		}
		
		Collection colecaoCapacidHidrComandoOSS = fachada.pesquisarDadosCapacidHidrComandoOSS(new Integer(idComando));
		if(colecaoCapacidHidrComandoOSS != null && !colecaoCapacidHidrComandoOSS.isEmpty()){
			sessao.setAttribute("colecaoCapacidHidrComandoOSS", colecaoCapacidHidrComandoOSS);
		}
		
		Collection colecaoLigacaoSitComandoOSS = fachada.pesquisarDadosLigacaoSitComandoOSS(new Integer(idComando));
		if(colecaoLigacaoSitComandoOSS != null && !colecaoLigacaoSitComandoOSS.isEmpty()){
			sessao.setAttribute("colecaoLigacaoSitComandoOSS", colecaoLigacaoSitComandoOSS);
		}
		
		return retorno;

	}
}
