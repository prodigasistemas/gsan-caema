package gcom.gui.relatorio.cadastro;

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioTipoServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioTipoServicoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		GerarRelatorioTipoServicoActionForm form = (GerarRelatorioTipoServicoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		String opcaoTipoRelatorio = form.getOpcaoTipoRelatorio();
		
		if (opcaoTipoRelatorio == null || opcaoTipoRelatorio.equalsIgnoreCase("")) {
			
			if (sessao.getAttribute("opcaoTipoRelatorio") == null) {
				throw new ActionServletException("atencao.required", null, "Opção de Tipo de Relatório ");
			} else {
				opcaoTipoRelatorio = (String) sessao.getAttribute("opcaoTipoRelatorio");
			}
		}
		
		String nomeRelatorio = "";

		if (opcaoTipoRelatorio.trim().equals("1")) {
			nomeRelatorio = ConstantesRelatorios.RELATORIO_TIPO_SERVICO_USUARIO;
		}else if(opcaoTipoRelatorio.trim().equals("2")){
			nomeRelatorio = ConstantesRelatorios.RELATORIO_TIPO_SERVICO_LOCALIDADE;
		}
		else if (opcaoTipoRelatorio.trim().equals("3")){
			nomeRelatorio = ConstantesRelatorios.RELATORIO_TIPO_SERVICO_MEIO;
			
		}

		GerarRelatorioTipoServicoHelper helper = this.criarHelperRelatorioTipoServico(form, sessao);
		
		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relató0rio
		RelatorioTipoServico relatorioTipoServico = new RelatorioTipoServico(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
				nomeRelatorio);
		relatorioTipoServico.addParametro("opcaoTipoRelatorio",
				opcaoTipoRelatorio);

		//Adiciona filtro escolhido pelo usuario ao relatorio
		relatorioTipoServico.addParametro("filtroHelper", helper);
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF+"";
		
		relatorioTipoServico.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		try {
			retorno = processarExibicaoRelatorio(relatorioTipoServico,
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

	private GerarRelatorioTipoServicoHelper criarHelperRelatorioTipoServico(
			GerarRelatorioTipoServicoActionForm form, HttpSession sessao) {
		
		GerarRelatorioTipoServicoHelper retorno = null;
				
		//Caso tipo do relatorio a ser executado seja servico por "MEIO DE SOLICITACAO"
		if(form.getOpcaoTipoRelatorio().equals("3")){
			retorno = new GerarRelatorioTipoServicoHelper(
					form.getOpcaoTipoRelatorio(),		
					form.getDataInicial(),
					form.getDataFinal(),
					form.getMeio(),
					(Collection<MeioSolicitacao>)sessao.getAttribute("colecaoMeiosSolicitacao"),
					form.getIdServico());
		}
		
		
		//Caso tipo do relatorio a ser executado seja servico por "LOCALIDADE"
		else if (form.getOpcaoTipoRelatorio().equals("2")){
			
			retorno = new  GerarRelatorioTipoServicoHelper(
					form.getOpcaoTipoRelatorio(), 
					form.getDataInicial(), 
					form.getDataFinal(), 
					form.getMeio(), 
					(Collection<MeioSolicitacao>)sessao.getAttribute("colecaoMeiosSolicitacao"),
					form.getIdGerenciaRegional(), 
					form.getIdGerenciaRegionalporLocalidade(), 
					form.getIdUnidadeNegocio(), 
					form.getIdLocalidade(), 
					form.getOpcaoTotalizacao(), 
					form.getIdServico());
		}
		
		//Caso tipo do relatorio a ser executado seja servico por "USUARIO"
		else if(form.getOpcaoTipoRelatorio().equals("1")){
					retorno = new GerarRelatorioTipoServicoHelper(
							form.getOpcaoTipoRelatorio(), 
							form.getDataInicial(), 
							form.getDataFinal(), 
							form.getMeio(),
							(Collection<MeioSolicitacao>)sessao.getAttribute("colecaoMeiosSolicitacao"),
							form.getIdUnidadeSuperior(), 
							(Collection<UnidadeOrganizacional>)sessao.getAttribute("colecaoUnidadeOrganizacional"),
							(Collection<Usuario>)sessao.getAttribute("colecaoUsuario"),
							form.getIdServico() );
				}
		
		
		return retorno;
	}
}
