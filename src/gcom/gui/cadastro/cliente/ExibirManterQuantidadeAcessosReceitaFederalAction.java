package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.QuantidadeAcessosReceitaFederal;
import gcom.cadastro.cliente.bean.FiltrarQuantidadeAcessosReceitaFederalHelper;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1388] Consultar Quantidade Acessos a Base da Receita Federal
 *
 * @author Ricardo Germinio
 * @date 27/11/2012
 *
 */

public class ExibirManterQuantidadeAcessosReceitaFederalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterQuantidadeAcessosReceitaFederalAction");
							  
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarQuantidadeAcessosReceitaFederalHelper helper = (FiltrarQuantidadeAcessosReceitaFederalHelper) sessao.getAttribute("filtro");
		
		Collection<QuantidadeAcessosReceitaFederal> colecaoQuantidadeAcessosReceitaFederal = (Collection<QuantidadeAcessosReceitaFederal>) sessao.getAttribute("colecaoQuantidadeAcessosReceitaFederal");
		retorno = this.controlarPaginacao(httpServletRequest, retorno, colecaoQuantidadeAcessosReceitaFederal.size());

		sessao.setAttribute("colecaoQuantidadeAcessosReceitaFederal",colecaoQuantidadeAcessosReceitaFederal);
		
		return retorno;
	}
}
