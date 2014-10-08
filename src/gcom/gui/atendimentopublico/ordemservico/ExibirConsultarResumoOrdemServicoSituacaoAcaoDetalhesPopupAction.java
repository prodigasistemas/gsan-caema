package gcom.gui.atendimentopublico.ordemservico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.DadosGeracaoResumoOSConsultaHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 *  #ID 7768 [RM8425][UC1559] - Consultar Resumo das Ações de Ordem de Serviço. 
 * 
 * @author Diogo Luiz
 * @date 23/09/2013 
 */

public class ExibirConsultarResumoOrdemServicoSituacaoAcaoDetalhesPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("consultarResumoOrdemServicoSituacaoAcaoDetalhesPopup");		
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = getSessao(httpServletRequest);
		
		Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(sessao.getAttribute("mesAnoReferencia").toString());
		
		DadosGeracaoResumoOSConsultaHelper helper = (DadosGeracaoResumoOSConsultaHelper) sessao.getAttribute("dadosGeracaoResumoOSConsultaHelper");
		
		Integer idServicoTipo = null;
		
		if(httpServletRequest.getParameter("idServicoTipo").trim() != null 
				|| !httpServletRequest.getParameter("idServicoTipo").trim().equals("")){
		
			idServicoTipo = new Integer(httpServletRequest.getParameter("idServicoTipo").trim());
		}
		
		FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, idServicoTipo));
		
		Collection<?> colecaoTipoServico = fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName());

		if (!Util.isVazioOrNulo(colecaoTipoServico)) {
			Iterator<?> iteratorServicoTipo = colecaoTipoServico.iterator();
			ServicoTipo servicoTipo = (ServicoTipo) iteratorServicoTipo.next();
			sessao.setAttribute("servicoTipoId", servicoTipo.getId());
			httpServletRequest.setAttribute("servicoTipo", servicoTipo.getDescricao());
		}
		
		Integer idOrdemServicoSituacao = new Integer(httpServletRequest.getParameter("idOrdemServicoSituacao").trim());
		sessao.setAttribute("idOrdemServicoSituacao", idOrdemServicoSituacao);
		
		httpServletRequest.setAttribute("ordemServicoSituacao", httpServletRequest.getParameter("ordemServicoSituacao"));
		String qtdTotal = Util.formatarMoedaRealNumber(new BigDecimal(httpServletRequest.getParameter("quantidadeTotal")));
		httpServletRequest.setAttribute("quantidadeTotal",qtdTotal);
		httpServletRequest.setAttribute("valorTotal", httpServletRequest.getParameter("valorTotal").trim());
		String valorTotalFormatado = Util.formatarMoedaReal(new BigDecimal(httpServletRequest.getParameter("valorTotal").trim()));
		httpServletRequest.setAttribute("valorTotalFormatado", valorTotalFormatado);
		
		Collection colecaoResumoOrdemServicoAcaoSituacaoDetalhes = new ArrayList();
		
		String tipoDetalhe = httpServletRequest.getParameter("tipoDetalhe");
		if (tipoDetalhe.equals("E")){
			colecaoResumoOrdemServicoAcaoSituacaoDetalhes = fachada.pesquisarDetalheResumoOrdemServicoSituacao(
				helper, anoMesReferencia, idServicoTipo, idOrdemServicoSituacao, 
				Integer.parseInt(httpServletRequest.getParameter("quantidadeTotal")), 
				new BigDecimal(httpServletRequest.getParameter("valorTotal").trim()));
		
		} else if (tipoDetalhe.equals("eF")){
			colecaoResumoOrdemServicoAcaoSituacaoDetalhes = fachada.pesquisarResumoFiscalizacaoSituacaoOrdemServico(
				helper, anoMesReferencia, idServicoTipo, idOrdemServicoSituacao, 
				Integer.parseInt(httpServletRequest.getParameter("quantidadeTotal")), 
				new BigDecimal(httpServletRequest.getParameter("valorTotal").trim()));
		}

		sessao.setAttribute("tipoDetalhe", tipoDetalhe);			

		sessao.setAttribute("colecaoResumoOrdemServicoAcaoSituacaoDetalhes",
				colecaoResumoOrdemServicoAcaoSituacaoDetalhes);			
		
		return retorno;
	}

}
