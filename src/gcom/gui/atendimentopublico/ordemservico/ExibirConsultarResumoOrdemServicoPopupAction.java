package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.DadosGeracaoResumoOSConsultaHelper;
import gcom.cadastro.imovel.bean.ImovelPerfilHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  #ID 7768 [RM8425][UC1559] - Consultar Resumo das Ações de Ordem de Serviço. 
 * 
 * @author Diogo Luiz
 * @date 23/09/2013 
 */
public class ExibirConsultarResumoOrdemServicoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarResumoOrdemServicoPopupAction");

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = getSessao(httpServletRequest);
		
		DadosGeracaoResumoOSConsultaHelper helper = (DadosGeracaoResumoOSConsultaHelper) sessao.getAttribute("dadosGeracaoResumoOSConsultaHelper");
		
		Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(sessao.getAttribute("mesAnoReferencia").toString());
		
		Integer idServicoTipo =  new Integer(httpServletRequest.getParameter("idServicoTipo").trim());
		
		FiltroTipoServico filtroTipoServico =  new FiltroTipoServico();
		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, idServicoTipo));
		Collection<?> colecaoServicoTipo = fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName());

		if (!Util.isVazioOrNulo(colecaoServicoTipo)) {
			ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
			httpServletRequest.setAttribute("servicoTipo", servicoTipo.getDescricao());
			
		}
		
		Integer idOrdemServicoSituacao = null;
		if(httpServletRequest.getParameter("idOrdemServicoSituacao") != null){
			idOrdemServicoSituacao =  new Integer(httpServletRequest.getParameter("idOrdemServicoSituacao").trim());
			httpServletRequest.setAttribute("ordemServicoSituacao", httpServletRequest.getParameter("ordemServicoSituacao"));
		}else{
			httpServletRequest.setAttribute("ordemServicoSituacao", "EMITIDOS");
		}
		String qtdTotal = Util.formatarMoedaRealNumber(new BigDecimal(httpServletRequest.getParameter("quantidadeTotal")));
		httpServletRequest.setAttribute("quantidadeTotal",qtdTotal);
		httpServletRequest.setAttribute("valorTotal",httpServletRequest.getParameter("valorTotal").trim());
		String valorTotalFormatado = Util.formatarMoedaReal(new BigDecimal(httpServletRequest.getParameter("valorTotal").trim()));
		httpServletRequest.setAttribute("valorTotalFormatado",valorTotalFormatado);
		
		Integer idCobrancaDebito = null;
		if(httpServletRequest.getParameter("idCobrancaDebito") != null){
		  idCobrancaDebito =  new Integer(httpServletRequest.getParameter("idCobrancaDebito").trim());
		  httpServletRequest.setAttribute("cobrancaDebito", httpServletRequest.getParameter("cobrancaDebito"));	
		  httpServletRequest.setAttribute("idCobrancaDebito", idCobrancaDebito); 
		}
		
		Collection<ImovelPerfilHelper> colecaoImovelPerfilHelper = fachada.pesquisarResumoImovelPerfilOrdemServico(helper, 
				anoMesReferencia, idServicoTipo, idOrdemServicoSituacao, idCobrancaDebito, 
				Integer.parseInt(httpServletRequest.getParameter("quantidadeTotal")), 
				new BigDecimal(httpServletRequest.getParameter("valorTotal").trim()));
		
		sessao.setAttribute("colecaoImovelPerfilHelper", colecaoImovelPerfilHelper);
		
		return retorno;
	}
}
