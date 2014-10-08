package gcom.gui.operacional.abastecimento;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSubSistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;

/**
 * [UC1521] Manter SubSistema Esgoto
 * 
 * @author Maxwell Moreira
 * @date 08/07/2013
 */
public class ExibirManterSubSistemaEsgotoAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
			ActionForward retorno = actionMapping.findForward("manterSubSistemaEsgoto");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Collection colecaoSubSistemaEsgoto = null;
	
			// Parte da verificação do filtro
			FiltroSubSistemaEsgoto filtroSubSistemaEsgoto = null;
	
			// Verifica se o filtro foi informado pela página de filtragem 
			if (sessao.getAttribute("filtroSistemaEsgotoSessao") != null) {
				filtroSubSistemaEsgoto = (FiltroSubSistemaEsgoto) sessao.getAttribute("filtroSistemaEsgotoSessao");
			}
	
			if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterSubSistemaEsgoto"))) {
	
				Map resultado = controlarPaginacao(httpServletRequest, retorno, filtroSubSistemaEsgoto, SubSistemaEsgoto.class.getName());			
				
				colecaoSubSistemaEsgoto = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				if (colecaoSubSistemaEsgoto == null || colecaoSubSistemaEsgoto.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				
				String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
				
				if (colecaoSubSistemaEsgoto.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
					// caso o resultado do filtro só retorne um registro 
					// e o check box Atualizar estiver selecionado
					//o sistema não exibe a tela de manter, exibe a de atualizar 
					retorno = actionMapping.findForward("exibirAtualizarSubSistemaEsgoto");
					SubSistemaEsgoto sistemaEsgoto = (SubSistemaEsgoto) colecaoSubSistemaEsgoto.iterator().next();
					sessao.setAttribute("idSubSistemaEsgoto", sistemaEsgoto.getId().toString());
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSubSistemaEsgotoAction.do");
				}else{
					sessao.setAttribute("colecaoSubSistemaEsgoto", colecaoSubSistemaEsgoto);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterSubSistemaEsgotoAction.do");
				}
	
			}
			sessao.removeAttribute("tipoPesquisaRetorno");
			
			return retorno;
		
		
	}
	
}
