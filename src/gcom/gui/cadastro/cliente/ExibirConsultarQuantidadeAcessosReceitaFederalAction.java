package gcom.gui.cadastro.cliente;

import gcom.cadastro.FiltroMensagemRetornoReceitaFederal;
import gcom.cadastro.MensagemRetornoReceitaFederal;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1070] Consultar Quantidade Acessos a base da Receita Federal
 * 
 * @author Ricardo Germinio
 * @date 27/09/2010
 *
 */

public class ExibirConsultarQuantidadeAcessosReceitaFederalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
				findForward("exibirConsultarQuantidadeAcessosReceitaFederal");
		
		Fachada fachada = Fachada.getInstancia();
		
		 FiltroMensagemRetornoReceitaFederal filtroMensagemRetornoReceitaFederal = new FiltroMensagemRetornoReceitaFederal();
	     filtroMensagemRetornoReceitaFederal.setCampoOrderBy(FiltroMensagemRetornoReceitaFederal.DESCRICAO_MENSAGEM_RETORNO);
	     Collection<MensagemRetornoReceitaFederal> collectionMensagemRetornoReceitaFederal = fachada.pesquisar(filtroMensagemRetornoReceitaFederal, MensagemRetornoReceitaFederal.class.getName());
	     httpServletRequest.setAttribute("collectionMensagemRetornoReceitaFederal", collectionMensagemRetornoReceitaFederal);       

	    return retorno;
	}
}
