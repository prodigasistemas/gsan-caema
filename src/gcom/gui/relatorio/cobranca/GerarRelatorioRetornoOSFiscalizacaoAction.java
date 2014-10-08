package gcom.gui.relatorio.cobranca;

import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioRetornoOSFiscalizacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioRetornoOSFiscalizacaoAction extends ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// cria a variável de retorno
		ActionForward retorno = null;
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		HttpSession sessao = httpServletRequest.getSession(false);
		GerarRelatorioRetornoOSFiscalizacaoActionForm form = (GerarRelatorioRetornoOSFiscalizacaoActionForm) actionForm;
		TarefaRelatorio relatorio = new RelatorioRetornoOSFiscalizacao((Usuario) sessao.getAttribute("usuarioLogado"));
		InformarDadosGeracaoResumoAcaoConsultaEventualHelper helper = (InformarDadosGeracaoResumoAcaoConsultaEventualHelper)
				sessao.getAttribute("informarDadosGeracaoResumoAcaoConsultaEventualHelper");
		String[] situacoes = form.getSituacoes();
		String mesAno = form.getMesAno();
		
		if(mesAno == null || mesAno.equals("-1"))
			throw new ActionServletException("atencao.msg_personalizada","Informe o Mês de Referência");
		if(helper != null)
			relatorio.addParametro("helper", helper);
		if (situacoes != null && situacoes.length >0) {
			int i = 0;
			for (i = 0; i < situacoes.length; i++) {
				if(!situacoes[i].equals("")) 			
					relatorio.addParametro("situacoes", situacoes);
			}	
		}	
		if(mesAno.length() >6 && !Util.validarMesAno(mesAno))
			throw new ActionServletException("atencao.msg_personalizada","Mês de Referência Inválido");
		if(mesAno.length() >6 && Util.validarMesAno(mesAno))
			mesAno = Util.formatarMesAnoParaAnoMesSemBarra(mesAno);
		
		relatorio.addParametro("mesAno", new Integer(mesAno));
		relatorio.addParametro("cobrancaAcaoId", (Integer) sessao.getAttribute("cobrancaAcaoId"));
		relatorio.addParametro("idCobrancaAcaoSituacao", (Integer) sessao.getAttribute("idCobrancaAcaoSituacao"));
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(httpServletRequest.getParameter("tipoRelatorio")));
		sessao.setAttribute("mesAno", mesAno);
		 
		retorno = processarExibicaoRelatorio(relatorio, Integer.parseInt(tipoRelatorio), 
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		return retorno;
	}
}
