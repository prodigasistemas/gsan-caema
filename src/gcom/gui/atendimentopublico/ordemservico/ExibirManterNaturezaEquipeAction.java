package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.EquipeNatureza;
import gcom.atendimentopublico.FiltroEquipeNatureza;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1543] - Manter Natureza de Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class ExibirManterNaturezaEquipeAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterNaturezaEquipe");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<EquipeNatureza> colecaoEquipeNatureza = null;
		
		FiltroEquipeNatureza filtroEquipeNatureza = new FiltroEquipeNatureza(FiltroEquipeNatureza.ID);
		
		if(sessao.getAttribute("filtroNaturezaEquipe") != null){
			filtroEquipeNatureza = (FiltroEquipeNatureza) 
					sessao.getAttribute("filtroNaturezaEquipe");
		}	
		
		Map<?, ?> resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroEquipeNatureza, EquipeNatureza.class.getName());
		
		colecaoEquipeNatureza = (Collection<EquipeNatureza>) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");			
		
		String indicadorAtualizar = (String) sessao.getAttribute("indicadorAtualizar");
		
		if(Util.isVazioOrNulo(colecaoEquipeNatureza)){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		if(colecaoEquipeNatureza.size() == 1 && indicadorAtualizar != null && !indicadorAtualizar.equals("")){
			
			retorno = actionMapping.findForward("exibirNaturezaEquipe");
			EquipeNatureza equipeNatureza = (EquipeNatureza) Util.retonarObjetoDeColecao(colecaoEquipeNatureza);
			
			sessao.setAttribute("equipeNatureza", equipeNatureza);
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarNaturezaEquipeAction.do");
		}else{
			sessao.setAttribute("colecaoNaturezaEquipe", colecaoEquipeNatureza);
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterNaturezaEquipeAction.do");
		}
		
		return retorno;
	}
}
