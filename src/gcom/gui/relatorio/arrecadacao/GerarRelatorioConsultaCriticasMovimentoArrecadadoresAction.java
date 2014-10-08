package gcom.gui.relatorio.arrecadacao;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.ArrecadadorMovimentoCriticas;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.ConsultarCriticasMovimentoArrecadadoresActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioConsultaCriticasMovimentoArrecadadores;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

public class GerarRelatorioConsultaCriticasMovimentoArrecadadoresAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		HttpSession sessao = httpServletRequest.getSession(false);
		ConsultarCriticasMovimentoArrecadadoresActionForm form = (ConsultarCriticasMovimentoArrecadadoresActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		Date geracaoInicial = null;
		Date geracaoFinal = null;
		Date processamentoInicial = null;
		Date processamentoFinal = null;
		
		if(form.getPeriodoGeracaoInicial() !=null && !form.getPeriodoGeracaoInicial().trim().equals(""))
			geracaoInicial = Util.converteStringParaDate(form.getPeriodoGeracaoInicial());
		if(form.getPeriodoGeracaoFinal() !=null && !form.getPeriodoGeracaoFinal().trim().equals(""))
			geracaoFinal = Util.converteStringParaDate(form.getPeriodoGeracaoFinal());
		if(form.getPeriodoProcessamentoInicial() !=null && !form.getPeriodoProcessamentoInicial().trim().equals(""))
			processamentoInicial = Util.converteStringParaDate(form.getPeriodoProcessamentoInicial());
		if(form.getPeriodoProcessamentoFinal() !=null && !form.getPeriodoProcessamentoFinal().trim().equals(""))
			processamentoFinal = Util.converteStringParaDate(form.getPeriodoProcessamentoFinal());
		
		Collection<ArrecadadorMovimentoCriticas> criticas = fachada.pesquisarArrecadadorMovimentoCriticas(
					form.getCodigoArrecadador(), 
					form.getIdServico(), 
					form.getNumeroSequencialArquivo(), 
					geracaoInicial, 
					geracaoFinal, 
					processamentoInicial, 
					processamentoFinal, 
					0, false);
		
		RelatorioConsultaCriticasMovimentoArrecadadores relatorio = new RelatorioConsultaCriticasMovimentoArrecadadores((Usuario)sessao
																														.getAttribute("usuarioLogado"));
		relatorio.addParametro("criticas", criticas);
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
		ActionForward retorno = null;

		try {
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
}
