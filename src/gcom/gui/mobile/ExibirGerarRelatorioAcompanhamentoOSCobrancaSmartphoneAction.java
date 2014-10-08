package gcom.gui.mobile;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
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

/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobrança para Smartphone 
 * 
 * @author Anderson Cabral
 * @since 19/08/2013
 */
public class ExibirGerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAcompanhamentoOSCobrancaSmartphoneAction");
		GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm form = (GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);

		String limparForm = httpServletRequest.getParameter("limparForm");
		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
		String carregarUnidade = httpServletRequest.getParameter("carregarUnidade");
		
		// Pesquisando a Localidade pelo código que o usuário digitou
		if(Util.verificarNaoVazio(pesquisarLocalidade)){
			String codigoLocalidade = form.getIdLocalidade();
			if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
				pesquisarLocalidade(httpServletRequest, form);
			}
			// Localidade
			if (Util.verificarIdNaoVazio(form.getIdLocalidade())) {
				httpServletRequest.setAttribute("localidadeEncontrada", true);
			}

		}else if(Util.verificarNaoVazio(limparForm)){
			form.setOpcaoRelatorio("");
			form.setDataReferencia("");
			form.setIdEmpresa("");
			form.setIdGerenciaRegional("");
			form.setIdLocalidade("");
			form.setDescricaoLocalidade("");
			form.setIdsTipoServico(null);
			form.setPeriodoGeracaoOSInicial("");
			form.setPeriodoGeracaoOSFinal("");
			httpServletRequest.removeAttribute("colecaoUnidadeNegocio");
		}
		
		if(Util.verificarNaoVazio(carregarUnidade) 
				|| (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals(""))){
			//Unidade de Negocio
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, Integer.parseInt(form.getIdGerenciaRegional())));
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			Collection<UnidadeNegocio> colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
		
		//Empresa
		String empresaSelecionada = httpServletRequest.getParameter("empresaSelecionada");
		if(empresaSelecionada == null || !empresaSelecionada.equalsIgnoreCase("sim")){
			this.pesquisarEmpresa(sessao);
		}
		
		//Gerencia Regional
		FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
		filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerencia.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection<GerenciaRegional> gerenciasRegionais = this.getFachada().pesquisar(filtroGerencia, GerenciaRegional.class.getName());
		httpServletRequest.setAttribute("colecaoGerenciaRegional",gerenciasRegionais);
		
		//Servico Tipo
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_SERVICO_COBRANCA, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
		Collection<ServicoTipo> colecaoTipoServico = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		httpServletRequest.setAttribute("colecaoTipoServico", colecaoTipoServico);
		
		return retorno;
	}
	
	private void pesquisarEmpresa(HttpSession sessao){
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroOperacao.DESCRICAO);		

		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	//Métodos para carregar os dados que o usuário apertou ENTER
	private void pesquisarLocalidade(HttpServletRequest request, GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm form) {
		Fachada fachada = Fachada.getInstancia();
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		Collection<Localidade> localidadePesquisada = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			
			form.setIdLocalidade(localidade.getId().toString());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setIdLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
		}
	}
	
}