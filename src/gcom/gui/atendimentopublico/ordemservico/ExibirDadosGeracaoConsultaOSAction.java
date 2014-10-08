package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.DadosGeracaoResumoOSConsultaHelper;
import gcom.atendimentopublico.ordemservico.bean.ServicoTipoHelper;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.StatusWizard;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1559] - Consultar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 19/09/2013
 *
 */
public class ExibirDadosGeracaoConsultaOSAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirDadosGeracaoConsultaOS");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		
		InformarDadosGeracaoResumoOrdemServicoConsultaActionForm form = (InformarDadosGeracaoResumoOrdemServicoConsultaActionForm) actionForm;
		
		DadosGeracaoResumoOSConsultaHelper helper = (DadosGeracaoResumoOSConsultaHelper) sessao.getAttribute("dadosGeracaoResumoOSConsultaHelper");
		
		sessao.setAttribute("mesAnoReferencia", form.getMesAnoReferencia());
		
		Collection<ServicoTipoHelper> colecaoServicoTipoHelper = fachada.pesquisarResumoAcoesOrdemServico(helper);
		
		sessao.setAttribute("colecaoServicoTipoHelper", colecaoServicoTipoHelper);
		
		StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
		
		adicionarTextoParametrosParaHintStatusWizard(statusWizard, helper, fachada);
		
		sessao.setAttribute("statusWizard", statusWizard);
		
		return retorno;
	}
	
	private void adicionarTextoParametrosParaHintStatusWizard(StatusWizard statusWizard, DadosGeracaoResumoOSConsultaHelper helper, Fachada fachada){
		StringBuffer texto = new StringBuffer();
		
		texto.append("<B>Parâmetros:</B>");
		texto.append("<BR>Mês/Ano de Referência: <I>" + Util.
				formatarAnoMesParaMesAno(helper.getAnoMesReferencia()) + "</I>");

		String servicosTipo = "";
		if (!Util.isVazioOrNulo(helper.getColecaoServicoTipo())){
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico(FiltroTipoServico.DESCRICAO);
			filtroTipoServico.adicionarParametro(new ParametroSimplesIn(FiltroTipoServico.ID, helper.getColecaoServicoTipo()));
			
			Collection<?> colecaoServicoTipo = fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName());
			for (Iterator<?> iter = colecaoServicoTipo.iterator(); iter.hasNext(); ) {
				ServicoTipo servicoTipo = (ServicoTipo) iter.next();
				servicosTipo += servicoTipo.getDescricao() + " / ";
			}
			if (!servicosTipo.equals("")){
				servicosTipo = servicosTipo.substring(0, servicosTipo.length() - 3);
			}
		} else {
			servicosTipo = "TODOS";
		}
		texto.append("<BR>Tipo de Serviço:<I>" + servicosTipo + "</I>");
		
		String gerencias = "";
		if (!Util.isVazioOrNulo(helper.getColecaoGerenciaRegional())){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
			filtroGerenciaRegional.adicionarParametro(new ParametroSimplesIn(FiltroGerenciaRegional.ID, helper.getColecaoGerenciaRegional()));
			
			Collection<?> colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			for (Iterator<?> iter = colecaoGerenciaRegional.iterator();iter.hasNext();) {
				GerenciaRegional GR = (GerenciaRegional) iter.next();
				gerencias += GR.getNome() + " / ";
			}
			if (!gerencias.equals("")){
				gerencias = gerencias.substring(0, gerencias.length() - 3);
			}
		} else {
			gerencias = "TODAS";
		}
		texto.append("<BR>Gerência Regional: <I>" + gerencias + "</I>");
		
		String unidadesNegocio = "";
		if (!Util.isVazioOrNulo(helper.getColecaoUnidadeNegocio())){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimplesIn(FiltroUnidadeNegocio.ID, helper.getColecaoUnidadeNegocio()));
			
			Collection<?> colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			for (Iterator<?> iter = colecaoUnidadeNegocio.iterator();iter.hasNext();) {
				UnidadeNegocio unidadeNeg = (UnidadeNegocio) iter.next();
				unidadesNegocio += unidadeNeg.getNome() + " / ";
			}
			if (!unidadesNegocio.equals("")){
				unidadesNegocio = unidadesNegocio.substring(0, unidadesNegocio.length() - 3);
			}
		} else {
			unidadesNegocio = "TODAS";
		}
		texto.append("<BR>Unidade de Negócio: <I>" + unidadesNegocio + "</I>");
		
		if (helper.getEloPolo() != null){
			Localidade eloPolo = fachada.pesquisarLocalidade(helper.getEloPolo());
			
			texto.append("<BR>Elo Polo: <I>" + eloPolo.getDescricao() + "</I>");
		}
		
		if (helper.getIdLocalidade() != null){
			Localidade localidade = fachada.pesquisarLocalidade(helper.getIdLocalidade());
			
			texto.append("<BR>Localidade: <I>" + localidade.getDescricaoParaRegistroTransacao() + "</I>");
		}
		
		if (helper.getCodigoSetorComercial() != null){
			SetorComercial setorComercial = fachada.obterSetorComercialLocalidade(
					String.valueOf(helper.getIdLocalidade()), String.valueOf(helper.getCodigoSetorComercial()));
			
			texto.append("<BR>Setor Comercial: <I>" + setorComercial.getCodigo() + "</I>");
		}
		
		String perfisImovel = "";
		if (!Util.isVazioOrNulo(helper.getColecaoImovelPerfil())){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil(FiltroImovelPerfil.ID);
			filtroImovelPerfil.adicionarParametro(new ParametroSimplesIn(FiltroImovelPerfil.ID, helper.getColecaoImovelPerfil()));
			
			Collection<?> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			for (Iterator<?> iter = colecaoImovelPerfil.iterator();iter.hasNext();) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) iter.next();
				perfisImovel += imovelPerfil.getDescricao() + " / ";
			}
			if (!perfisImovel.equals("")){
				perfisImovel = perfisImovel.substring(0, perfisImovel.length() - 3);
			}
		} else {
			perfisImovel = "TODOS";
		}
		texto.append("<BR>Perfil do Imóvel: <I>" + perfisImovel + "</I>");
		
		String situacoesLigacaoAgua = "";
		if (!Util.isVazioOrNulo(helper.getColecaoLigacaoAguaSituacao())){
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(FiltroLigacaoAguaSituacao.DESCRICAO);
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimplesIn(FiltroLigacaoAguaSituacao.ID, helper.getColecaoLigacaoAguaSituacao()));
			
			Collection<?> colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			for (Iterator<?> iter = colecaoLigacaoAguaSituacao.iterator();iter.hasNext();) {
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) iter.next();
				situacoesLigacaoAgua += ligacaoAguaSituacao.getDescricao() + " / ";
			}
			if (!situacoesLigacaoAgua.equals("")){
				situacoesLigacaoAgua = situacoesLigacaoAgua.substring(0, situacoesLigacaoAgua.length() - 3);
			}
		} else {
			situacoesLigacaoAgua = "TODAS";
		}
		texto.append("<BR>Situação de Ligação de Água: <I>" + situacoesLigacaoAgua + "</I>");

		String situacoesLigacaoEsgoto = "";
		if (!Util.isVazioOrNulo(helper.getColecaoLigacaoEsgotoSituacao())){
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(FiltroLigacaoEsgotoSituacao.DESCRICAO);
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimplesIn(FiltroLigacaoEsgotoSituacao.ID, helper.getColecaoLigacaoEsgotoSituacao()));
			
			Collection<?> colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			for (Iterator<?> iter = colecaoLigacaoEsgotoSituacao.iterator();iter.hasNext();) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iter.next();
				situacoesLigacaoEsgoto += ligacaoEsgotoSituacao.getDescricao() + " / ";
			}
			if (!situacoesLigacaoEsgoto.equals("")){
				situacoesLigacaoEsgoto = situacoesLigacaoEsgoto.substring(0, situacoesLigacaoEsgoto.length() - 3);
			}
		} else {
			situacoesLigacaoEsgoto = "TODAS";
		}
		texto.append("<BR>Situação de Ligação de Esgoto: <I>" + situacoesLigacaoEsgoto + "</I>");
		
		String categorias = "";
		if (!Util.isVazioOrNulo(helper.getColecaoCategorias())){
			FiltroCategoria filtroCategoria = new FiltroCategoria(FiltroCategoria.DESCRICAO);
			filtroCategoria.adicionarParametro(new ParametroSimplesIn(FiltroCategoria.CODIGO, helper.getColecaoCategorias()));
			
			Collection<?> colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			for (Iterator<?> iter = colecaoCategoria.iterator();iter.hasNext();) {
				Categoria categoria = (Categoria) iter.next();
				categorias += categoria.getDescricao() + " / ";
			}
			if (!categorias.equals("")){
				categorias = categorias.substring(0, categorias.length() - 3);
			}
		} else {
			categorias = "TODAS";
		}
		texto.append("<BR>Categoria: <I>" + categorias + "</I>");
		
		String esferasPoder = "";
		if (!Util.isVazioOrNulo(helper.getColecaoEsferaPoder())){
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);
			filtroEsferaPoder.adicionarParametro(new ParametroSimplesIn(FiltroEsferaPoder.ID, helper.getColecaoEsferaPoder()));
			
			Collection<?> colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
			for (Iterator<?> iter = colecaoEsferaPoder.iterator();iter.hasNext();) {
				EsferaPoder esferaPoder = (EsferaPoder) iter.next();
				esferasPoder += esferaPoder.getDescricao() + " / ";
			}
			if (!esferasPoder.equals("")){
				esferasPoder = esferasPoder.substring(0, esferasPoder.length() - 3);
			}
		} else {
			esferasPoder = "TODAS";
		}
		texto.append("<BR>Esfera de Poder: <I>" + esferasPoder + "</I>");
		
		String empresas = "";
		if (!Util.isVazioOrNulo(helper.getColecaoEmpresa())){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
			filtroEmpresa.adicionarParametro(new ParametroSimplesIn(FiltroEmpresa.ID, helper.getColecaoEmpresa()));
			
			Collection<?> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			for (Iterator<?> iter = colecaoEmpresa.iterator();iter.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				empresas += empresa.getDescricao() + " / ";
			}
			if (!empresas.equals("")){
				empresas = empresas.substring(0, empresas.length() - 3);
			}
		} else {
			empresas = "TODAS";
		}
		texto.append("<BR>Empresa: <I>" + empresas + "</I>");
		
		statusWizard.adicionarItemHint("",texto.toString());
	}
	
}
