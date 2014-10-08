package gcom.gui.operacional.abastecimento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarSubSistemaEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			AtualizarSubSistemaEsgotoActionForm atualizarSubSistemaEsgotoActionForm = 
												(AtualizarSubSistemaEsgotoActionForm) actionForm;
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);		
			
			SubSistemaEsgoto subSistemaEsgoto = (SubSistemaEsgoto) sessao.getAttribute("subSistemaEsgoto");
			
			subSistemaEsgoto.setDescricao(atualizarSubSistemaEsgotoActionForm.getDescricao().trim());
			
			
			if(atualizarSubSistemaEsgotoActionForm.getDescricaoAbreviada() != null && 
					!atualizarSubSistemaEsgotoActionForm.getDescricaoAbreviada().equals("")){
				
				subSistemaEsgoto.setDescricaoAbreviada(atualizarSubSistemaEsgotoActionForm.getDescricaoAbreviada().trim());
			}else{
				subSistemaEsgoto.setDescricaoAbreviada(null);
			}
			
			
			FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, atualizarSubSistemaEsgotoActionForm.getSistemaEsgoto().toString()));
			Collection colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			
			SistemaEsgoto sistEsgoto = (SistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSistemaEsgoto);
			subSistemaEsgoto.setSistemaEsgoto(sistEsgoto);
			
			subSistemaEsgoto.setIndicadorUso(Short.valueOf(atualizarSubSistemaEsgotoActionForm.getIndicadorUso()));
			
			fachada.atualizarSubSistemaEsgoto(subSistemaEsgoto);
			
			montarPaginaSucesso(httpServletRequest, "Subsistema de Esgoto de código "+ subSistemaEsgoto.getId() +" alterado com sucesso", "Realizar outra Manutenção de Subsistema de Esgoto",
					"exibirFiltrarSubSistemaEsgotoAction.do?menu=sim");
			
			
			return retorno;
	}
	
}
