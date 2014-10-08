package gcom.gui.relatorio.faturamento;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.GcomAction;
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
 * [UC 1293] - Gerar Relatorio Quantitativo Contas Reimpressas
 * 
 * @author Davi Menezes
 * @date 07/03/2012
 *
 */
public class ExibirGerarRelatorioQuantitativoContasReimpressasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("quantitativoContasReimpressasGerarRelatorio");
		
		GerarRelatorioQuantitativoContasReimpressasActionForm form = 
				(GerarRelatorioQuantitativoContasReimpressasActionForm) actionForm;
		
		HttpSession session = httpServletRequest.getSession();
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		if(httpServletRequest.getParameter("limpar") != null && httpServletRequest.getParameter("limpar").equals("sim")){
			form.setAnoMesReferencia("");
			form.setGerenciaRegional("-1");
			form.setUnidadeNegocio("-1");
			form.setLocalidade("");
			form.setNomeLocalidade("");
			form.setSetorComercial("");
			form.setNomeSetorComercial("");
			form.setRota("");
			form.setEmpresa("-1");
		}
		
		this.pesquisarEmpresa(httpServletRequest, session);
		this.pesquisarGerenciaRegional(httpServletRequest, session);
		
		if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")){
			this.pesquisarUnidadeNegocio(httpServletRequest, form, session);
		}
		
		if((objetoConsulta != null && objetoConsulta.equals("1")) || 
			(form.getLocalidade() != null && !form.getLocalidade().equals(""))){
			this.pesquisarLocalidade(httpServletRequest, form);
		}
		
		if((objetoConsulta != null && objetoConsulta.equals("2")) || 
			(form.getSetorComercial() != null && !form.getSetorComercial().equals(""))){
			this.pesquisarSetorComercial(httpServletRequest, form);
		}
		
		return retorno;
	}


	/**
	 * Pesquisar Localidade
	 * 
	 * @author Davi Menezes
	 * @date 07/03/2012
	 * @param httpServletRequest
	 * @param form
	 */
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, GerarRelatorioQuantitativoContasReimpressasActionForm form) {
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));
		
		if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("-1")){
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID, form.getUnidadeNegocio()));
		}
		
		if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")){
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
		}
		
		Collection<?> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if(Util.isVazioOrNulo(colecaoLocalidade)){
			form.setLocalidade("");
			form.setNomeLocalidade("Localidade Inexistente");
			httpServletRequest.setAttribute("localidadeNaoEncontrada", true);
		}else{
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			form.setNomeLocalidade(localidade.getDescricao());
			httpServletRequest.removeAttribute("localidadeNaoEncontrada");
		}
	}
	
	/**
	 * Pesquisar Setor Comercial
	 * 
	 * @author Davi Menezes
	 * @date 07/03/2012
	 * @param httpServletRequest
	 * @param form
	 */
	private void pesquisarSetorComercial(HttpServletRequest httpServletRequest, GerarRelatorioQuantitativoContasReimpressasActionForm form) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidade()));
		
		Collection<?> colSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if(Util.isVazioOrNulo(colSetorComercial)){
			form.setSetorComercial("");
			form.setNomeSetorComercial("Setor Comercial Inexistente");
			httpServletRequest.setAttribute("setorComercialNaoEncontrado", true);
		}else{
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
			form.setNomeSetorComercial(setorComercial.getDescricao());
			httpServletRequest.removeAttribute("setorComercialNaoEncontrado");
		}
	}
	
	/**
	 * Pesquisa Gerencia Regional
	 * 
	 * @author Davi Menezes
	 * @date 07/03/2012
	 */
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest, HttpSession session) {
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoGerenciaRegional = this.getFachada().pesquisar(
			filtroGerenciaRegional, GerenciaRegional.class.getName());

		if (Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			session.removeAttribute("colecaoGerenciaRegional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}
	}
	
	/**
	 * Pesquisa Unidade Negocio
	 * 
	 * @author Davi Menezes
	 * @date 07/03/2012
	 */
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioQuantitativoContasReimpressasActionForm form, HttpSession session) {

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

		if (form.getGerenciaRegional() != null 
			&& !form.getGerenciaRegional().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.ID_GERENCIA, form.getGerenciaRegional()));
		}

		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
			FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoUnidadeNegocio = this.getFachada().pesquisar(
			filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			session.removeAttribute("colecaoUnidadeNegocio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}
	
	/**
	 * Pesquisar Empresa
	 * 
	 * @author Davi Menezes
	 * @date 07/03/2012
	 * @param httpServletRequest
	 */
	private void pesquisarEmpresa(HttpServletRequest httpServletRequest, HttpSession session){
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		filtroEmpresa.adicionarParametro(new ParametroSimples(
			FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.adicionarParametro(new ParametroSimples(
			FiltroEmpresa.INDICADOR_LEITURA, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoEmpresa = this.getFachada().pesquisar(
			filtroEmpresa, Empresa.class.getName());
		
		if(Util.isVazioOrNulo(colecaoEmpresa)){
			session.removeAttribute("colecaoEmpresa");
		}else{
			httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
}
