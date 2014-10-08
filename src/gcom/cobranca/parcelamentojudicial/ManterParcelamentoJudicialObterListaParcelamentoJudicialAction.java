package gcom.cobranca.parcelamentojudicial;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1451] - Manter Parcelamento Judicial
 * 
 * @author Jonathan 
 * @Date 15/04/2013
 *
 */


public class ManterParcelamentoJudicialObterListaParcelamentoJudicialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManterParcelamentoJudicialActionForm manterParcelamentoJudicialActionForm = (ManterParcelamentoJudicialActionForm) actionForm;
		
		FiltrarManterParcelamentoJudicial filtroManterParcelamentoJudicial = new FiltrarManterParcelamentoJudicial();
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<ManterParcelamentoJudicialObterListaParcelamentoJudicialHelper> colecaoObterListaPrcelamentoJudicial = null;
		
		ActionForward retorno = actionMapping.findForward("manterParcelamentoJudicialObterListaParcelamentoJudicial");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String numeroDaPagina = httpServletRequest.getParameter("page.offset");
		
		int tamanhoColecaoObterListaPrcelamentoJudicial = 0;

		if (manterParcelamentoJudicialActionForm.getCodigoClienteResponsavel() != null
				&& !manterParcelamentoJudicialActionForm.getCodigoClienteResponsavel().equals("")) {

			filtroManterParcelamentoJudicial.setCodigoClienteResponsavel(
				manterParcelamentoJudicialActionForm.getCodigoClienteResponsavel().toString());
		}

		if (manterParcelamentoJudicialActionForm.getCodigoClienteUsuario() != null
				&& !manterParcelamentoJudicialActionForm.getCodigoClienteUsuario().equals("")) {

			filtroManterParcelamentoJudicial.setCodigoClienteUsuario(
				manterParcelamentoJudicialActionForm.getCodigoClienteUsuario().toString());
		}

		if (manterParcelamentoJudicialActionForm.getMatriculaImovel() != null
				&& !manterParcelamentoJudicialActionForm.getMatriculaImovel().equals("")) {

			filtroManterParcelamentoJudicial.setMatriculaImovel(
				manterParcelamentoJudicialActionForm.getMatriculaImovel().toString());
		}

		if (manterParcelamentoJudicialActionForm.getProcessoJudicial() != null
				&& !manterParcelamentoJudicialActionForm.getProcessoJudicial().equals("")) {

			filtroManterParcelamentoJudicial.setProcessoJudicial(
				manterParcelamentoJudicialActionForm.getProcessoJudicial().toString());
		}

		if (manterParcelamentoJudicialActionForm.getPeriodoInicialParcelamento() != null
				&& manterParcelamentoJudicialActionForm.getPeriodoFinalParcelamento() != null
				&& !manterParcelamentoJudicialActionForm.getPeriodoInicialParcelamento().equals("")
				&& !manterParcelamentoJudicialActionForm.getPeriodoFinalParcelamento().equals("")) {

			filtroManterParcelamentoJudicial.setPeriodoInicialParcelamento(
				manterParcelamentoJudicialActionForm.getPeriodoInicialParcelamento().toString());

			filtroManterParcelamentoJudicial.setPeriodoFinalParcelamento(
				manterParcelamentoJudicialActionForm.getPeriodoFinalParcelamento().toString());
		}

		if (numeroDaPagina == null || numeroDaPagina.equals("")) {

			filtroManterParcelamentoJudicial.setNumeroPaginaPesquisa(
				String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		}

		if (numeroDaPagina != null && !numeroDaPagina.equals("")) {

			filtroManterParcelamentoJudicial.setNumeroPaginaPesquisa(numeroDaPagina);

		}


		
		tamanhoColecaoObterListaPrcelamentoJudicial = fachada.tamanhoPesquisarObterListaParcelamentoJudicial(filtroManterParcelamentoJudicial);

			

		colecaoObterListaPrcelamentoJudicial = fachada.pesquisarObterListaParcelamentoJudicial(filtroManterParcelamentoJudicial);

		if (colecaoObterListaPrcelamentoJudicial.size() > 0) {

			retorno = this.controlarPaginacao(httpServletRequest, retorno, tamanhoColecaoObterListaPrcelamentoJudicial);

			if (httpServletRequest.getParameter("page.offset") != null) {

				sessao.setAttribute("page.offset", httpServletRequest.getAttribute("page.offset"));
			}

			sessao.setAttribute("colecaoObterListaPrcelamentoJudicial", colecaoObterListaPrcelamentoJudicial);
			sessao.setAttribute("totalRegistros", tamanhoColecaoObterListaPrcelamentoJudicial);

		} else {

			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}

		return retorno;
	}
}
