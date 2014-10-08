package gcom.gui.operacional.abastecimento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1519] - Inserir Subsistema de Esgoto 
 * 
 * @author Maxwell Moreira
 * @date 03/07/2013
 */
public class InserirSubSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			InserirSubSistemaEsgotoActionForm inserirSubSistemaEsgotoActionForm = (InserirSubSistemaEsgotoActionForm) actionForm;
			
			FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgoto.ID, inserirSubSistemaEsgotoActionForm.getSistemaEsgoto()));
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<SistemaEsgoto> colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto,SistemaEsgoto.class.getName());
			
			SistemaEsgoto sistemaEsgoto = (SistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSistemaEsgoto);
			
			SubSistemaEsgoto subSistemaEsgoto = new SubSistemaEsgoto();
			
			subSistemaEsgoto.setDescricao(inserirSubSistemaEsgotoActionForm.getDescricao().trim());
			if(inserirSubSistemaEsgotoActionForm.getDescricaoAbreviada() != null && 
					!inserirSubSistemaEsgotoActionForm.getDescricaoAbreviada().equals("")){
				subSistemaEsgoto.setDescricaoAbreviada(inserirSubSistemaEsgotoActionForm.getDescricaoAbreviada().trim());
			}
			
			subSistemaEsgoto.setSistemaEsgoto(sistemaEsgoto);
			
			//Inserir na base de dados Sistema de Esgoto
			Integer idSistemaEsgoto = fachada.inserirSubSistemaEsgoto(subSistemaEsgoto);
			
			//Montar a página de sucesso
			montarPaginaSucesso(httpServletRequest,
					"Subsistema de Esgoto de código "+ idSistemaEsgoto +" inserido com sucesso.",
					"Inserir outro Subsistema de Esgoto","exibirInserirSubSistemaEsgotoAction.do?menu=sim",
					"exibirAtualizarSubSistemaEsgotoAction.do?idRegistroInseridoAtualizar="+ 
					idSistemaEsgoto,"Atualizar Subsistema de Esgoto");
	
			return retorno;
	}

}
