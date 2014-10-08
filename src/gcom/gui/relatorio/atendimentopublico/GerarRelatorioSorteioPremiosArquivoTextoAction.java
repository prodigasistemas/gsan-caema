package gcom.gui.relatorio.atendimentopublico;


import gcom.gui.atendimentopublico.EfetuarSorteioPremiosActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioSorteioPremiosArquivoTexto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1295] Efetuar Sorteio de Prêmios
 * 
 * @author Mariana Victor
 * @since 09/03/2012
 */
public class GerarRelatorioSorteioPremiosArquivoTextoAction extends
	ExibidorProcessamentoTarefaRelatorio {
		
	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
	
		ActionForward retorno = null;
		
		

		EfetuarSorteioPremiosActionForm form = (EfetuarSorteioPremiosActionForm) actionForm;
		
	
		// cria uma instância da classe do relatório
		RelatorioSorteioPremiosArquivoTexto relatorioSorteioPremiosArquivoTexto = 
				new RelatorioSorteioPremiosArquivoTexto(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
				ConstantesRelatorios.RELATORIO_SORTEIO_PREMIOS_ARQUIVO_TEXTO);
		
		String tipoRelatorio = TarefaRelatorio.TIPO_HTML + "";
		
		relatorioSorteioPremiosArquivoTexto.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		relatorioSorteioPremiosArquivoTexto.addParametro("idSorteio", form.getIdSorteio());
		relatorioSorteioPremiosArquivoTexto.addParametro("numeroSorteioFiqueLegal2013", form.getIdNumeroPremioSorteio());
		
		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		
		try {
			
			retorno = processarExibicaoRelatorio(relatorioSorteioPremiosArquivoTexto,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
			
			
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
		
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		
		}
		
		
		
		return retorno;
	}

}
