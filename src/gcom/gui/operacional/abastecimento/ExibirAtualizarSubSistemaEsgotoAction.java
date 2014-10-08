package gcom.gui.operacional.abastecimento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.FiltroSubSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1521] Manter SubSistema Esgoto
 * 
 * @author Maxwell Moreira
 * @date 08/07/2013
 */

public class ExibirAtualizarSubSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarSubSistemaEsgoto");
			
		AtualizarSubSistemaEsgotoActionForm atualizarSubSistemaEsgotoActionForm = (AtualizarSubSistemaEsgotoActionForm)actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();			
					
		SubSistemaEsgoto subSistemaEsgoto = null;
		
		String idSubSistemaEsgoto = null;
		
		if (httpServletRequest.getParameter("idSubSistemaEsgoto") != null) {
			//tela do manter
			idSubSistemaEsgoto = (String) httpServletRequest.getParameter("idSubSistemaEsgoto");
			sessao.setAttribute("idSubSistemaEsgoto", idSubSistemaEsgoto);
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterSubSistemaEsgotoAction.do");
		} else if (sessao.getAttribute("idSubSistemaEsgoto") != null) {
			//tela do filtrar
			idSubSistemaEsgoto = (String) sessao.getAttribute("idSubSistemaEsgoto");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSubSistemaEsgotoAction.do");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir sistema esgoto
			idSubSistemaEsgoto = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSubSistemaEsgotoAction.do?menu=sim");
		}
			
		if (idSubSistemaEsgoto == null || idSubSistemaEsgoto.equals("")){
			
			if (sessao.getAttribute("idRegistroAtualizar") != null){
				subSistemaEsgoto = (SubSistemaEsgoto) sessao.getAttribute("idRegistroAtualizar");
			}else if(httpServletRequest.getParameter("idSubSistemaEsgoto") != null){
				idSubSistemaEsgoto = (String) httpServletRequest.getParameter("idSubSistemaEsgoto");
			}else{
				idSubSistemaEsgoto = (String) httpServletRequest.getAttribute("idSubSistemaEsgoto");
			}
		}
			
		//------Inicio da parte que verifica se vem da página de sistema_esgoto_manter.jsp
		
		if (subSistemaEsgoto == null){
		
			if (idSubSistemaEsgoto != null && !idSubSistemaEsgoto.equals("")) {

				FiltroSubSistemaEsgoto filtroSubSistemaEsgoto = new FiltroSubSistemaEsgoto();
				
				filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSubSistemaEsgoto.ID, idSubSistemaEsgoto));

				Collection colecaoSubSistemaEsgoto = fachada.pesquisar(filtroSubSistemaEsgoto, SubSistemaEsgoto.class.getName());

				if (colecaoSubSistemaEsgoto != null && !colecaoSubSistemaEsgoto.isEmpty()) {
					
					subSistemaEsgoto = (SubSistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSubSistemaEsgoto);
					
				}
			}
		}
				
		if (subSistemaEsgoto.getDescricao() != null){
			atualizarSubSistemaEsgotoActionForm.setDescricao(subSistemaEsgoto.getDescricao());
		}
		
		if (subSistemaEsgoto.getDescricaoAbreviada() != null){
			atualizarSubSistemaEsgotoActionForm.setDescricaoAbreviada(subSistemaEsgoto.getDescricaoAbreviada());
		}

		
		FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
		filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSistemaEsgoto.setCampoOrderBy(FiltroSistemaEsgoto.DESCRICAO);
		Collection colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
		httpServletRequest.setAttribute("colecaoSistemaEsgoto", colecaoSistemaEsgoto);
		
		if ((subSistemaEsgoto != null) && (subSistemaEsgoto.getSistemaEsgoto() != null)){
			atualizarSubSistemaEsgotoActionForm.setSistemaEsgoto(subSistemaEsgoto.getSistemaEsgoto().getId().toString());
		} else {
			atualizarSubSistemaEsgotoActionForm.setSistemaEsgoto("-1");
		}
		
		atualizarSubSistemaEsgotoActionForm.setIndicadorUso(""+subSistemaEsgoto.getIndicadorUso());
		
		sessao.setAttribute("subSistemaEsgoto", subSistemaEsgoto);
		httpServletRequest.setAttribute("idSubSistemaEsgoto", idSubSistemaEsgoto);
	
		return retorno;
	}

}
