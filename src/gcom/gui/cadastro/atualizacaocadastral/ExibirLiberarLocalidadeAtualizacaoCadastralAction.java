package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1327] - Liberar Localidade Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 24/04/2012
 *
 */
public class ExibirLiberarLocalidadeAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("liberarLocalidadeAtualizacaoCadastral");
		
		LiberarLocalidadeAtualizacaoCadastralActionForm form = 
				(LiberarLocalidadeAtualizacaoCadastralActionForm) actionForm;
		
		if ( httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim") ) {
			form.setIdEmpresa("-1");
			form.setLocalidade("");
			form.setNomeLocalidade("");
			form.setSetorComercial("");
			form.setNomeSetorComercial("");
		}
		
		if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
			this.pesquisarLocalidade(httpServletRequest, form);
			
			if(form.getSetorComercial() != null && !form.getSetorComercial().equals("")){
				this.pesquisarSetorComercial(httpServletRequest, form);
			}
			
		}
		
		this.pesquisarEmpresa(httpServletRequest);
		
		return retorno;
	}
	
	private void pesquisarSetorComercial(HttpServletRequest httpServletRequest, LiberarLocalidadeAtualizacaoCadastralActionForm form) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidade()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSetor = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if(!Util.isVazioOrNulo(colSetor)){
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetor);
			form.setNomeSetorComercial(setorComercial.getDescricao());
			
			httpServletRequest.setAttribute("setorComercialEncontrado", true);
		}else{
			form.setSetorComercial("");
			form.setNomeSetorComercial("Setor Comercial Inexistente");
		}
	}

	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, LiberarLocalidadeAtualizacaoCadastralActionForm form) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if(!Util.isVazioOrNulo(colLocalidade)){
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
			form.setNomeLocalidade(localidade.getDescricao());
			
			httpServletRequest.setAttribute("localidadeEncontrada", true);
		}else{
			form.setLocalidade("");
			form.setNomeLocalidade("Localidade Inexistente");
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
