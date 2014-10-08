package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.RelatorioArquivosSubSistemaErro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;


/**
 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
 * @author Anderson Cabral
 * @since 07/07/2013
 * 
 */
public class GerarRelatorioArquivosSubSistemaErroAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// cria a variável de retorno
		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		String idArquivo = httpServletRequest.getParameter("idArquivo");
		
		RelatorioArquivosSubSistemaErro relatorioArquivosSubSistemaErro 
				= new RelatorioArquivosSubSistemaErro((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), ConstantesRelatorios.RELATORIO_ARQUIVOS_SUBSISTEMA_ESGOTO_ERRO);
		
		relatorioArquivosSubSistemaErro.addParametro("idArquivo", idArquivo);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioArquivosSubSistemaErro.addParametro("tipoRelatorio", tipoRelatorio);
		
		retorno = processarExibicaoRelatorio(relatorioArquivosSubSistemaErro,
				tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		
		return retorno;		
	}
}
