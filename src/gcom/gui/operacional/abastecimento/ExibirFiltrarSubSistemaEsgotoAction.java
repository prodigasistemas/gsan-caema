package gcom.gui.operacional.abastecimento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1520] Filtrar SubSistema de Esgoto
 * 
 * @author Maxwell Moreira
 * @date 05/07/2013
 */

public class ExibirFiltrarSubSistemaEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
			ActionForward retorno = actionMapping.findForward("filtrarSubSistemaEsgoto");	
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			FiltrarSubSistemaEsgotoActionForm form = (FiltrarSubSistemaEsgotoActionForm) actionForm;
		
		
			//	Código para checar ou não o ATUALIZAR
	
			String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
				sessao.setAttribute("indicadorAtualizar", "1");
				form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
				
			}
	
			// SISTEMA DE ESGOTO
			FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSistemaEsgoto.setCampoOrderBy(FiltroSistemaEsgoto.DESCRICAO);
			
			Collection<SistemaEsgoto> colecaoSistemaEsgoto = fachada.pesquisar
						(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
	
			if (colecaoSistemaEsgoto == null || colecaoSistemaEsgoto.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Sistema de Esgoto");
			}
	
			httpServletRequest.setAttribute("colecaoSistemaEsgoto",colecaoSistemaEsgoto);
			
			//	Se voltou da tela de Atualizar Sistema de Esgoto
			if (sessao.getAttribute("voltar") != null && sessao.getAttribute("voltar").equals("filtrar")) {
				sessao.setAttribute("indicadorAtualizar", "1");
				if(sessao.getAttribute("tipoPesquisa") != null){
					form.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
				}
			}
			sessao.removeAttribute("voltar");
			sessao.removeAttribute("tipoPesquisa");

		
		return retorno;
	}
	
}
