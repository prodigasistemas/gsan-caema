package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de a��o de cobran�a 
 * [UC0326] Filtrar Comandos de A��o de Conbran�a - Cronograma 
 * 
 * [SB0007] Filtrar Reabrir Comandos de Cobran�a
 * 
 * @author R�mulo Aur�lio
 * @since 26/10/2012
 */
public class ExibirFiltrarComandosAcaoCobrancaCronogramaReabrirAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarComandosAcaoCobrancaReabrirCronograma");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm form = (FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm) actionForm;

		// limpa o filtro da sess�o caso ja tenha utilizado
		if (sessao.getAttribute("colecaoCobrancaAcaoAtividadeEventual") != null) {
			sessao.removeAttribute("colecaoCobrancaAcaoAtividadeEventual");
		}

		if (sessao.getAttribute("colecaoCobrancaAcaoAtividadeCronograma") != null) {
			sessao.removeAttribute("colecaoCobrancaAcaoAtividadeCronograma");
		}

		// CARREGAR AS COBRAN�AS GRUPO
		if (sessao.getAttribute("colecaoGrupoCobranca") == null) {
			sessao.setAttribute("colecaoGrupoCobranca", fachada.obterColecaoCobrancaGrupo());
		}

		// CARREGAR AS COBRAN�AS ACAO
		if (sessao.getAttribute("colecaoAcaoCobranca") == null) {
			sessao.setAttribute("colecaoAcaoCobranca", fachada.obterColecaoCobrancaAcao());
		}

		String carregando = httpServletRequest.getParameter("carregando");

		if (carregando != null && !carregando.equals("")) {
			if (sessao.getAttribute("form") != null) {

				FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm filtrarComandosAcaoCobrancaCronogramaReabrirActionFormRecarregar = (FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm) sessao.getAttribute("FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm");

				form.setPeriodoEncerramentoComandoInicial(
					filtrarComandosAcaoCobrancaCronogramaReabrirActionFormRecarregar.getPeriodoEncerramentoComandoInicial());
				form.setPeriodoEncerramentoComandoFinal(
					filtrarComandosAcaoCobrancaCronogramaReabrirActionFormRecarregar.getPeriodoEncerramentoComandoFinal());
				
				form.setGrupoCobranca(filtrarComandosAcaoCobrancaCronogramaReabrirActionFormRecarregar.getGrupoCobranca());
				form.setAcaoCobranca(filtrarComandosAcaoCobrancaCronogramaReabrirActionFormRecarregar.getAcaoCobranca());
				form.setAtividadeCobranca(filtrarComandosAcaoCobrancaCronogramaReabrirActionFormRecarregar.getAtividadeCobranca());

			}
		}

		return retorno;
	}

}
