
package gcom.gui.cobranca.cobrancaporresultado;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.cobranca.FiltroMotivoNaoGeracaoCobrancaResultado;
import gcom.cobranca.MotivoNaoGeracaoCobrancaResultado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;


/**
 * @author Diego Maciel
 * @created 27/04/2012
 */
public class ExibirInserirMotivoNaoGeracaoCobrancaResultadoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirMotivoNaoGeracaoCobrancaResultado");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		InserirMotivoNaoGeracaoCobrancaResultadoActionForm form = (InserirMotivoNaoGeracaoCobrancaResultadoActionForm) actionForm;
		form.setIndicadorUso(ConstantesSistema.SIM+"");

		  
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {
			

			form.setDescricao("");

			if (form.getDescricao() == null
					|| form.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroMotivoNaoGeracaoCobrancaResultado filtroMotivo = new FiltroMotivoNaoGeracaoCobrancaResultado();

				filtroMotivo.setCampoOrderBy(FiltroMotivoNaoGeracaoCobrancaResultado.DESCRICAO);

				colecaoPesquisa = fachada.pesquisar(filtroMotivo,
						MotivoNaoGeracaoCobrancaResultado.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Motivo de Corte");
				} else {
					sessao.setAttribute("colecaoMotivoCorte", colecaoPesquisa);
				}

				// Coleção de Motivo Nao Geracao Cobranca Resultado
				FiltroMotivoNaoGeracaoCobrancaResultado filtroMotivoCombrancao = new FiltroMotivoNaoGeracaoCobrancaResultado();
				filtroMotivoCombrancao.setCampoOrderBy(FiltroMotivoNaoGeracaoCobrancaResultado.ID);

				Collection colecaoMotivoCombracaResultado = fachada.pesquisar(filtroMotivoCombrancao,
						MotivoCorte.class.getName());
				sessao.setAttribute("colecaoMotivoCombracaResultado", colecaoMotivoCombracaResultado);

			}

		}
		
		return retorno;
	
	}
}
