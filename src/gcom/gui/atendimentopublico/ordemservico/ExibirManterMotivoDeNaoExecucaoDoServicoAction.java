package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMotivoDeNaoExecucaoDoServico;
import gcom.atendimentopublico.ordemservico.FiltroMotivoNaoExecucaoUnidadeRepavimentadora;
import gcom.atendimentopublico.ordemservico.FiltroServicoRepavimentadora;
import gcom.atendimentopublico.ordemservico.MotivoDeNaoExecucaoDoServico;
import gcom.atendimentopublico.ordemservico.MotivoNaoExecucaoUnidadeRepavimentadora;
import gcom.atendimentopublico.ordemservico.ServicoRepavimentadora;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC 1513] - Manter Motivo de não execução do serviço 
 * 
 * @author Diogo Luiz
 * @date 01/07/2013
 *
 */
public class ExibirManterMotivoDeNaoExecucaoDoServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterMotivoDeNaoExecucaoDoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<?> colecaoMotivoDeNaoExecucaoDoServico = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		// Parte da verificação do filtro
		FiltroMotivoNaoExecucaoUnidadeRepavimentadora filtroMotivoNaoExecucao = new FiltroMotivoNaoExecucaoUnidadeRepavimentadora(
				FiltroMotivoNaoExecucaoUnidadeRepavimentadora.ID_MOTIVO_DE_NAO_EXECUCAO_DO_SERVICO);
		
		// Verifica se o filtro foi informado pela página de filtragem 		
		if(sessao.getAttribute("filtroMotivoNaoExecucao") != null){
			filtroMotivoNaoExecucao = (FiltroMotivoNaoExecucaoUnidadeRepavimentadora) 
					sessao.getAttribute("filtroMotivoNaoExecucao");
		}		
		
		if ((retorno != null) && (retorno.getName().equalsIgnoreCase("manterMotivoDeNaoExecucaoDoServico"))) {
			
			Collection<?> colecaoMotivoNaoExecucao = fachada.pesquisar(filtroMotivoNaoExecucao, 
					MotivoNaoExecucaoUnidadeRepavimentadora.class.getName());			
			
			if (Util.isVazioOrNulo(colecaoMotivoNaoExecucao)) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			Collection<Integer> colecaoIdsMotivoNaoExeucao = new ArrayList<Integer>();
			
			Integer idAnterior = 0;
			Iterator<?> iterator = colecaoMotivoNaoExecucao.iterator();
			while(iterator.hasNext()){
				MotivoNaoExecucaoUnidadeRepavimentadora motivoNaoExecucaoUnidadeRepavimentadora = 
					(MotivoNaoExecucaoUnidadeRepavimentadora) iterator.next();
				
				if(!idAnterior.equals(motivoNaoExecucaoUnidadeRepavimentadora.getComp_id().getMotivoDeNaoExecucaoDoServico().getId())){
					colecaoIdsMotivoNaoExeucao.add(motivoNaoExecucaoUnidadeRepavimentadora.getComp_id().getMotivoDeNaoExecucaoDoServico().getId());
				}
				
				idAnterior = motivoNaoExecucaoUnidadeRepavimentadora.getComp_id().getMotivoDeNaoExecucaoDoServico().getId();
			}
			
			FiltroMotivoDeNaoExecucaoDoServico filtroMotivoDeNaoExecucaoDoServico = new FiltroMotivoDeNaoExecucaoDoServico();
			filtroMotivoDeNaoExecucaoDoServico.adicionarParametro(new ParametroSimplesIn(FiltroMotivoDeNaoExecucaoDoServico.ID, 
					colecaoIdsMotivoNaoExeucao));
			
			Map<?, ?> resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroMotivoDeNaoExecucaoDoServico, MotivoDeNaoExecucaoDoServico.class.getName());
			
			colecaoMotivoDeNaoExecucaoDoServico = (Collection<?>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");			
			
			String identificadorAtualizar = (String) sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoMotivoDeNaoExecucaoDoServico.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirMotivoDeNaoExecucaoDoServico");
				MotivoDeNaoExecucaoDoServico motivoDeNaoExecucaoDoServico =  (MotivoDeNaoExecucaoDoServico) 
						colecaoMotivoDeNaoExecucaoDoServico.iterator().next();
				sessao.setAttribute("motivoDeNaoExecucaoDoServicoAtualizar", motivoDeNaoExecucaoDoServico);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMotivoDeNaoExecucaoDoServicoAction.do");
			
			}else{
				sessao.setAttribute("colecaoMotivoDeNaoExecucaoDoServico", colecaoMotivoDeNaoExecucaoDoServico);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMotivoDeNaoExecucaoDoServicoAction.do");
			}
		}		
		return retorno;	
	}
}
