package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.AreaAtualizacaoCadastral;
import gcom.cadastro.FiltroAreaAtualizacaoCadastral;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1327] - Suspender Localidade Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 25/04/2012
 *
 */
public class ExibirSuspenderLocalidadeAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("suspenderLocalidadeAtualizacaoCadastral");
		
		SuspenderLocalidadeAtualizacaoCadastralActionForm form = 
				(SuspenderLocalidadeAtualizacaoCadastralActionForm) actionForm;
		
		if ( httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim") ) {
			form.setIdEmpresa("-1");
			form.setIdsRegistro(null);
		}
		
		this.pesquisarEmpresa(httpServletRequest);
		
		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
			this.pesquisarLocalidade(httpServletRequest, form);
		}else{
			httpServletRequest.removeAttribute("colecaoAreaAtualizacaoCadastral");
		}
		
		return retorno;
	}
	
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, SuspenderLocalidadeAtualizacaoCadastralActionForm form) {
		FiltroAreaAtualizacaoCadastral filtroArea = new FiltroAreaAtualizacaoCadastral();
		filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.EMPRESA_ID, form.getIdEmpresa()));
		filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.LOCALIDADE);
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL);
		
		Collection<?> colecaoAreaAtualizacao = Fachada.getInstancia().pesquisar(filtroArea, AreaAtualizacaoCadastral.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoAreaAtualizacao)){
			httpServletRequest.removeAttribute("semLocalidades");
			httpServletRequest.setAttribute("colecaoAreaAtualizacaoCadastral", colecaoAreaAtualizacao);
		}else{
			httpServletRequest.setAttribute("semLocalidades", true);
		}
	}

	private void pesquisarEmpresa(HttpServletRequest httpServletRequest) {
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
		
		if(!Util.isVazioOrNulo(colEmpresa)){
			httpServletRequest.setAttribute("colecaoEmpresa", colEmpresa);
		}else{
			httpServletRequest.removeAttribute("colecaoEmpresa");
		}
	}
	
}
