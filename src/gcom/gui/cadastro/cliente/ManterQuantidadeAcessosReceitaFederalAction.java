package gcom.gui.cadastro.cliente;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ManterQuantidadeAcessosReceitaFederalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		ManterQuantidadeAcessosReceitaFederalActionForm form = (ManterQuantidadeAcessosReceitaFederalActionForm) actionForm;
			
		// Registros selecionados para reprocessamento.
		String[] ids = form.getIdQuantidadeAcessos();
		
		// Mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro;
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		// Obtem data Atual
		Date dataAtual = new Date();
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Quantidade de Acessos Reprocessados com sucesso.",
				"Realizar outra Consulta referente a Quantidade de Acessos a Base da Receita Federal",
				"exibirFiltrarQuantidadeAcessosReceitaFederalAction.do?menu=sim");

		return retorno;
	}
}
