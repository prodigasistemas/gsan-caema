package gcom.gui.cobranca.cobrancaporresultado;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.imovel.bean.ImovelCobrancaSituacaoHelper;
import gcom.gui.GcomAction;

public class ExibirContasEnviadasPagasParceladasEmCobrancaAction  extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Integer ind = new Integer(httpServletRequest.getParameter("ind"));
		HttpSession sessao = httpServletRequest.getSession(false);
		ArrayList<ImovelCobrancaSituacaoHelper> colecao = new ArrayList<ImovelCobrancaSituacaoHelper>((Collection) sessao.
				getAttribute("colecaoDadosImovelCobrancaSituacao"));
		ImovelCobrancaSituacaoHelper helper = colecao.get(ind);
		sessao.setAttribute("contasEnviadasCobranca", helper.getContasEnviadasCobrancaHelper());
		sessao.setAttribute("contasPagasCobranca", helper.getContasPagasCobrancaHelper());
		sessao.setAttribute("totalEnviadas", helper.getValorTotalEnviadas());
		sessao.setAttribute("totalPagas", helper.getValorTotalPagas());
		return actionMapping.findForward("exibirContasEnviadasPagasParceladasEmCobranca");
	}
}
