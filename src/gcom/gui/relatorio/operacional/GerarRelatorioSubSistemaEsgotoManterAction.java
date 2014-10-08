package gcom.gui.relatorio.operacional;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.FiltrarImovelPerfilActionForm;
import gcom.gui.operacional.abastecimento.FiltrarSubSistemaEsgotoActionForm;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.FiltroSubSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioManterImovelPerfil;
import gcom.relatorio.operacional.RelatorioManterSubSistemaEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class GerarRelatorioSubSistemaEsgotoManterAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarSubSistemaEsgotoActionForm filtrarSubSistemaEsgotoActionForm = (FiltrarSubSistemaEsgotoActionForm) actionForm;

		FiltroSubSistemaEsgoto filtroSubSistemaEsgoto = (FiltroSubSistemaEsgoto) sessao.getAttribute("filtroSistemaEsgotoSessao");

		// Inicio da parte que vai mandar os parametros para o relatório

		SubSistemaEsgoto subSistemaEsgoto = new SubSistemaEsgoto();
		String id = null;

		String idSistemaEsgotoPesquisar = filtrarSubSistemaEsgotoActionForm.getCodigoSistemaEsgoto().toString();

		if (idSistemaEsgotoPesquisar != null && !idSistemaEsgotoPesquisar.equals("")) {
			id = idSistemaEsgotoPesquisar;
		}				
		// seta os parametros que serão mostrados no relatório

		subSistemaEsgoto.setId(id == null ? null : new Integer(id));
		
		if ((filtrarSubSistemaEsgotoActionForm.getDescricaoSistemaEsgoto() != null)
				&& (!filtrarSubSistemaEsgotoActionForm.getDescricaoSistemaEsgoto().equals(""))){
			subSistemaEsgoto.setDescricao(""+filtrarSubSistemaEsgotoActionForm.getDescricaoSistemaEsgoto());
		} else{
			subSistemaEsgoto.setDescricao("");
		}
		
		if ((filtrarSubSistemaEsgotoActionForm.getDescricaoAbreviada() != null)
				&& (!filtrarSubSistemaEsgotoActionForm.getDescricaoAbreviada().equals(""))){
			subSistemaEsgoto.setDescricaoAbreviada(""+filtrarSubSistemaEsgotoActionForm.getDescricaoAbreviada());
		} else{
			subSistemaEsgoto.setDescricaoAbreviada("");
		}
		
		if ((filtrarSubSistemaEsgotoActionForm.getSistemaEsgoto() != null)
				&& (!filtrarSubSistemaEsgotoActionForm.getSistemaEsgoto().equals("-1"))){
		
			FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, filtrarSubSistemaEsgotoActionForm.getSistemaEsgoto()));
			Collection colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			
			SistemaEsgoto sistemaEsgotoParametro = (SistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSistemaEsgoto);
			subSistemaEsgoto.setSistemaEsgoto(sistemaEsgotoParametro);
			
		} else{
			subSistemaEsgoto.setSistemaEsgoto(null);
		}
		
		if ((filtrarSubSistemaEsgotoActionForm.getIndicadorUso() != null) && 
		(!filtrarSubSistemaEsgotoActionForm.getIndicadorUso().equals(""))){
			subSistemaEsgoto.setIndicadorUso(Short.valueOf(filtrarSubSistemaEsgotoActionForm.getIndicadorUso().toString()));
		} else{
			subSistemaEsgoto.setIndicadorUso(null);
		}
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterSubSistemaEsgoto relatorioManterSubSistemaEsgoto = new RelatorioManterSubSistemaEsgoto(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterSubSistemaEsgoto.addParametro("filtroSubSistemaEsgoto", filtroSubSistemaEsgoto);
		relatorioManterSubSistemaEsgoto.addParametro("subSistemaEsgotoParametros", subSistemaEsgoto);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterSubSistemaEsgoto.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterSubSistemaEsgoto,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
	
}
