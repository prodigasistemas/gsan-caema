package gcom.gui.relatorio.faturamento;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.FiltroSubSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1480] Gerar Relatório Resumo do Faturamento PPP
 * 
 * @author Jonathan Marcos
 * @date 28/05/2013
 */
public class ExibirGerarRelatorioResumoFaturamentoPppAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession session = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioResumoFaturamentoPpp");

		Fachada fachada = Fachada.getInstancia();
		
		GerarRelatorioResumoFaturamentoPppActionForm form = (GerarRelatorioResumoFaturamentoPppActionForm) actionForm;

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		Collection colecaoSistemaEsgoto = new ArrayList();
		
		Collection colecaoSubSistemaEsgoto = new ArrayList();
		
		Collection<GerenciaRegional> gerenciasRegionais = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		httpServletRequest.setAttribute("colecaoGerenciaRegional",
				gerenciasRegionais);
		
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade"); 
		// Pesquisando a Localidade pelo código que o usuário digitou
		if(form.getCodigoLocalidade()!=null && !form.getCodigoLocalidade().equals("")){
			String codigoLocalidade = form.getCodigoLocalidade();
			if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
				pesquisarLocalidade(httpServletRequest, form);
			}
			// Localidade
			if (form.getCodigoLocalidade() != null && 
					!form.getCodigoLocalidade().equals("") &&
					form.getDescricaoLocalidade() != null && 
					!form.getDescricaoLocalidade().equals("")) {
				httpServletRequest.setAttribute("localidadeEncontrada", true);
			}
		}
		
		String pesquisarMunicipio = httpServletRequest.getParameter("pesquisarMunicipio");
		//Pesquisando o município pelo código que o usuário digitou
		if(form.getCodigoMunicipio()!= null && !form.getCodigoMunicipio().equals("")){
			String codigoMunicipio = form.getCodigoMunicipio();
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				pesquisarMunicipio(httpServletRequest, form);
			}
			// Município
			if (form.getCodigoMunicipio() != null && 
					!form.getCodigoMunicipio().equals("") &&
					form.getDescricaoMunicipio() != null && 
					!form.getDescricaoMunicipio().equals("")) {
				httpServletRequest.setAttribute("municipioEncontrado", true);
			}
		}
		
		if (session.getAttribute("colecaoSistemaEsgoto") == null) {
			FiltroSistemaEsgoto filtroSistemaEsgoto= new FiltroSistemaEsgoto(FiltroSistemaEsgoto.DESCRICAO);
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			
			colecaoSistemaEsgoto = fachada.pesquisar(
					filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			
			session.setAttribute("colecaoSistemaEsgoto", colecaoSistemaEsgoto);
		}
		
		if(session.getAttribute("colecaoSubSistemaEsgoto")==null){
			
			FiltroSubSistemaEsgoto filtroSubSistemaEsgoto = new FiltroSubSistemaEsgoto();
			filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSubSistemaEsgoto.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSubSistemaEsgoto = fachada.pesquisar(filtroSubSistemaEsgoto, SubSistemaEsgoto.class.getName());
			
			session.setAttribute("colecaoSubSistemaEsgoto", colecaoSubSistemaEsgoto);
			
		}
		//recupera o tipo de relatorio
		String opcaoRelatorio = form.getOpcaoRelatorio();
		if(opcaoRelatorio == null){
			// Manda o tipo de relatorio pelo request
			form.setOpcaoRelatorio("1");
		}else{
			//Manda o tipo de relatorio pelo request
			form.setOpcaoRelatorio(opcaoRelatorio);
		}

		return retorno;
	}
	
	/*
	 * Métodos que farão a pesquisa da localidade ou do município 
	 * informado pelo usuário (Pressionando a telca ENTER).
	 */
	private void pesquisarLocalidade(HttpServletRequest request, 
			GerarRelatorioResumoFaturamentoPppActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getCodigoLocalidade()));
		
		Collection pesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setCodigoLocalidade("" + localidade.getId());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setCodigoLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
		}
	}
	
	private void pesquisarMunicipio(HttpServletRequest request, 
			GerarRelatorioResumoFaturamentoPppActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, form.getCodigoMunicipio()));
		
		Collection pesquisa = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setCodigoMunicipio("" + municipio.getId());
			form.setDescricaoMunicipio(municipio.getNome());
		} else {
			form.setCodigoMunicipio("");
			form.setDescricaoMunicipio("Município Inexistente");
		}
	}
}
