package gcom.gui.cobranca.cobrancaporresultado;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.ImovelContaNaoGeracaoCobrancaResultado;
import gcom.cobranca.ImovelContaNaoGeracaoCobrancaResultadoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

public class ConsultarNaoGeracaoImoveisContasComandosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultarNaoGeracaoImoveisContasComandos");		
		Integer comando = new Integer(httpServletRequest.getParameter("comando"));
		
		httpServletRequest.setAttribute("comando", comando);
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		boolean exibeMsg = true;
		sessao.removeAttribute("imoveis");
		sessao.removeAttribute("contas");
				
		Collection<ImovelContaNaoGeracaoCobrancaResultadoHelper> imoveis = fachada
				.pesquisarImoveisContasNaoGeradasCobrancaPorResultadoImovel(comando);
		Collection<ImovelContaNaoGeracaoCobrancaResultadoHelper> contas = fachada
				.pesquisarImoveisContasNaoGeradasCobrancaPorResultadoContas(comando);
		
		if(imoveis !=null && !imoveis.isEmpty()){			
			sessao.setAttribute("imoveis", imoveis);
			exibeMsg = false;
		}
		if(contas!=null && !contas.isEmpty()){
			sessao.setAttribute("contas", contas);
			exibeMsg = false;
		}
		if(!fachada.motivosGerados(comando))
			exibeMsg = true;
		if(exibeMsg)
			throw new ActionServletException("atencao.msg_personalizada","A pesquisa não retornou nenhum resultado.");		
		
		return retorno;
	}
}
