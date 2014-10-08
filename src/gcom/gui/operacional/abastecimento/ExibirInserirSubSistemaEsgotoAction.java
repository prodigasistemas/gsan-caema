package gcom.gui.operacional.abastecimento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1519] Inserir SubSistema de Esgoto
 * 
 * @author Maxwell Moreira
 * @date 08/07/2013
 */

public class ExibirInserirSubSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
			ActionForward retorno = actionMapping.findForward("inserirSubSistemaEsgoto");	
			
			Fachada fachada = Fachada.getInstancia();
						
			FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples
					(FiltroDivisaoEsgoto.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSistemaEsgoto.setCampoOrderBy(FiltroDivisaoEsgoto.DESCRICAO);
			
			Collection<SistemaEsgoto> colecaoSistemaEsgoto = fachada.pesquisar(
					filtroSistemaEsgoto, SistemaEsgoto.class.getName());
	
			if (colecaoSistemaEsgoto == null || colecaoSistemaEsgoto.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Sistema de Esgoto");
			}
	
			httpServletRequest.setAttribute("colecaoSistemaEsgoto",colecaoSistemaEsgoto);
	
		
		return retorno;
	}

}
