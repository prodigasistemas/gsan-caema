package gcom.gui.relatorio.cobranca;

import java.util.Collection;
import java.util.Date;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.FiltrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper;
import gcom.relatorio.cobranca.RelatorioParcelasEmAtrasoParcelamentoJudicial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1465] Gerar Relatório das Parcelas em Atraso do Parcelamento Judicial
 * 
 * @author Mariana Victor
 * @date 22/04/2013
 */
public class GerarRelatorioParcelasEmAtrasoParcelamentoJudicialAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm form = 
				(GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm) actionForm;
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_PARCELAS_EM_ATRASO_PARCELAMENTO_JUDICIAL;
		RelatorioParcelasEmAtrasoParcelamentoJudicial relatorio = new RelatorioParcelasEmAtrasoParcelamentoJudicial(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);

		FiltrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper helper = new FiltrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper();

		Fachada fachada = Fachada.getInstancia();
		
		boolean peloMenosUmFiltroInformado = false;
				
		String idClienteResponsavel = form.getIdClienteResponsavel();
		if(Util.verificarNaoVazio(idClienteResponsavel)){
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, idClienteResponsavel));
			
			Collection<Cliente> colecaoCliente = fachada.pesquisar(
				filtroCliente, Cliente.class.getName());
			
			// [FE0001] - Validar cliente
			if (colecaoCliente != null 
					&& !colecaoCliente.isEmpty()) {
				
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				
				helper.setIdClienteResponsavel(Integer.valueOf(idClienteResponsavel));
				helper.setNomeClienteResponsavel(cliente.getNome());
				
			} else {
				throw new ActionServletException(
					"atencao.pesquisa_inexistente", "Cliente");
			}
			
			peloMenosUmFiltroInformado = true;
		}
		
		String idImovelPrincipal = form.getIdImovelPrincipal();
		if(Util.verificarNaoVazio(idImovelPrincipal)){
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, form.getIdImovelPrincipal()));
			
			Collection<Imovel> colecaoImovel = fachada.pesquisar(
				filtroImovel, Imovel.class.getName());
			
			// [FE0002] - Verificar existência da matrícula do imóvel
			if (colecaoImovel != null 
					&& !colecaoImovel.isEmpty()) {
				
				helper.setIdImovelPrincipal(
					Integer.valueOf(idImovelPrincipal));
				
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", "Matrícula");
			}
			
			peloMenosUmFiltroInformado = true;
		}
		
		String quantidadeDiasAtraso = form.getQuantidadeDiasAtraso();
		if(Util.verificarNaoVazio(quantidadeDiasAtraso)){
			helper.setQuantidadeDiasAtraso(Integer.valueOf(quantidadeDiasAtraso));
			
			peloMenosUmFiltroInformado = true;
		}

		String numeroProcessoJudicial = form.getNumeroProcessoJudicial();
		if(Util.verificarNaoVazio(numeroProcessoJudicial)){
			helper.setNumeroProcessoJudicial(numeroProcessoJudicial);

			peloMenosUmFiltroInformado = true;
		}
		
		String dataInicial = form.getDataParcelamentoInicial();
		String dataFinal = form.getDataParcelamentoFinal();
		if(Util.verificarNaoVazio(dataInicial)){
			Date dataInicialFormatada = null;
			Date dataFinalFormatada = null;
			
			if(Util.verificarNaoVazio(dataFinal)){
				dataInicialFormatada = Util.converteStringParaDate(form.getDataParcelamentoInicial());
				dataFinalFormatada = Util.converteStringParaDate(form.getDataParcelamentoFinal());
				
				// [FE0005] - Verificar data final menor que data inicial.
				if (Util.compararData(dataInicialFormatada, dataFinalFormatada) > 0) {
					throw new ActionServletException(
							"atencao.data_final_periodo.anterior.data_inicial_periodo");
				}
				
			} else {
				throw new ActionServletException(
						"atencao.campo_selecionado.obrigatorio", "Data Final do Período");
			}
			
			helper.setDataParcelamentoFinal(dataFinalFormatada);
			helper.setDataParcelamentoInicial(
				Util.converteStringParaDate(form.getDataParcelamentoInicial()));
			
			peloMenosUmFiltroInformado = true;
			
		} else if(Util.verificarNaoVazio(dataFinal)){
			throw new ActionServletException(
				"atencao.campo_selecionado.obrigatorio", "Data Inicial do Período");
		}
		
		
		// [FE0003] Verificar seleção do filtro
		if (!peloMenosUmFiltroInformado) {
			throw new ActionServletException(
				"atencao.selecionar.nenhum_parametro_informado");
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("filtrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper", helper);

		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);
		
	}

}
