package gcom.gui.relatorio.atendimentopublico.ordemservico;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.bean.ServicoTipoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioResumoAcoesOrdensServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

/**
 * [UC 1559] - Consultar Resumo das Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 23/09/2013
 *
 */
public class GerarRelatorioResumoAcoesOrdensServicoAction extends ExibidorProcessamentoTarefaRelatorio {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<ServicoTipoHelper> colecaoServicoTipoHelper = (Collection<ServicoTipoHelper>) sessao.getAttribute("colecaoServicoTipoHelper");
		
		String mesAnoReferencia = (String) sessao.getAttribute("mesAnoReferencia");
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String tipoRelatorio;		
		tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		String idServicoTipo = (String) httpServletRequest.getParameter("id");
		
		RelatorioResumoAcoesOrdensServico relatorioResumoAcoesOrdensServico = new RelatorioResumoAcoesOrdensServico(usuario);
		
		relatorioResumoAcoesOrdensServico.addParametro("tipoRelatorio", tipoRelatorio);
		relatorioResumoAcoesOrdensServico.addParametro("colecaoServicoTipoHelper", colecaoServicoTipoHelper);
		relatorioResumoAcoesOrdensServico.addParametro("idServicoTipo", idServicoTipo);
		relatorioResumoAcoesOrdensServico.addParametro("mesAnoReferencia", mesAnoReferencia);
		
		try {
			retorno = processarExibicaoRelatorio(
					relatorioResumoAcoesOrdensServico, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
		}
		
		return retorno;
	}
}
