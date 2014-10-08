package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
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
 * [UC 1558] - Filtrar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 19/09/2013
 *
 */
public class ExibirInformarDadosGeracaoResumoOSConsultaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInformarDadosGeracaoResumoOSConsulta");
		
		//Obtém a Instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Formulário da Tela
		InformarDadosGeracaoResumoOrdemServicoConsultaActionForm form = (InformarDadosGeracaoResumoOrdemServicoConsultaActionForm) actionForm;
		
		String menu = httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.reset();
		}
		
		String pesquisarEloPolo = httpServletRequest.getParameter("pesquisarEloPolo");
		if(pesquisarEloPolo != null && pesquisarEloPolo.equalsIgnoreCase("OK")){
			this.pesquisarEloPolo(form, httpServletRequest, fachada);
		}
		
		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
		if(pesquisarLocalidade != null && pesquisarLocalidade.equalsIgnoreCase("OK")){
			this.pesquisarLocalidade(form, httpServletRequest, fachada);
		}
		
		String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
		if(pesquisarSetorComercial != null && pesquisarSetorComercial.equalsIgnoreCase("OK")){
			this.pesquisarSetorComercial(form, httpServletRequest, fachada);
		}
		
		this.pesquisarServicoTipo(httpServletRequest, fachada);
		this.pesquisarGerenciaRegional(httpServletRequest, fachada);
		this.pesquisarUnidadeNegocio(httpServletRequest, fachada);
		this.pesquisarPerfilImovel(httpServletRequest, fachada);
		this.pesquisarLigacaoAguaSituacao(httpServletRequest, fachada);
		this.pesquisarLigacaoEsgotoSituacao(httpServletRequest, fachada);
		this.pesquisarCategoria(httpServletRequest, fachada);
		this.pesquisarEsferaPoder(httpServletRequest, fachada);
		this.pesquisarEmpresa(httpServletRequest, fachada);
		
		return retorno;
	}
	
	/**
	 * Pesquisar Tipo de Serviço
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	private void pesquisarServicoTipo(HttpServletRequest request, Fachada fachada){
		FiltroTipoServico filtroTipoServico = new FiltroTipoServico(FiltroTipoServico.DESCRICAO);
		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_GERAR_RESUMO, ConstantesSistema.SIM));
		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoServicoTipo = fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName());
		if(!Util.isVazioOrNulo(colecaoServicoTipo)){
			request.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
		}
	}
	
	/**
	 * Pesquisar as Gerência Regionais
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarGerenciaRegional(HttpServletRequest request, Fachada fachada){
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
			request.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}
	}
	
	/**
	 * Pesquisar as Unidades de Negócio
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarUnidadeNegocio(HttpServletRequest request, Fachada fachada){
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		if(!Util.isVazioOrNulo(colecaoUnidadeNegocio)){
			request.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}
	
	/**
	 * Pesquisar os Perfis dos Imóveis
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	private void pesquisarPerfilImovel(HttpServletRequest request, Fachada fachada){
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil(FiltroImovelPerfil.DESCRICAO);
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		if(!Util.isVazioOrNulo(colecaoImovelPerfil)){
			request.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		}
	}
	
	/**
	 * Pesquisar Ligação Água Situação
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	private void pesquisarLigacaoAguaSituacao(HttpServletRequest request, Fachada fachada){
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if(!Util.isVazioOrNulo(colecaoLigacaoAguaSituacao)){
			request.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
		}
	}
	
	/**
	 * Pesquisar Ligação Esgoto Situação
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	private void pesquisarLigacaoEsgotoSituacao(HttpServletRequest request, Fachada fachada){
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(FiltroLigacaoEsgotoSituacao.DESCRICAO);
		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
		if(!Util.isVazioOrNulo(colecaoLigacaoEsgotoSituacao)){
			request.setAttribute("colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao);
		}
	}
	
	/**
	 * Pesquisar Categoria
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	private void pesquisarCategoria(HttpServletRequest request, Fachada fachada){
		FiltroCategoria filtroCategoria = new FiltroCategoria(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
		if(!Util.isVazioOrNulo(colecaoCategoria)){
			request.setAttribute("colecaoCategoria", colecaoCategoria);
		}
	}
	
	/**
	 * Pesquisar Esfera Poder
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	private void pesquisarEsferaPoder(HttpServletRequest request, Fachada fachada){
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		if(!Util.isVazioOrNulo(colecaoEsferaPoder)){
			request.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		}
	}
	
	/**
	 * Pesquisar Empresa
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param request
	 * @param fachada
	 */
	private void pesquisarEmpresa(HttpServletRequest request, Fachada fachada){
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		if(!Util.isVazioOrNulo(colecaoEmpresa)){
			request.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	/**
	 * Pesquisar Elo Polo
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param form
	 * @param request
	 * @param fachada
	 */
	private void pesquisarEloPolo(InformarDadosGeracaoResumoOrdemServicoConsultaActionForm form, HttpServletRequest request, Fachada fachada){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
    	filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.ID_ELO);
    	
        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getEloPolo()));

        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection<?> colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
        
        if (Util.isVazioOrNulo(colecaoPesquisa)){
        	form.setEloPolo("");
        	form.setDescricaoEloPolo("Elo Pólo inexistente");
        	request.setAttribute("nomeCampo", "eloPolo");
        	request.setAttribute("eloPoloInexistente", "true");
        }
        
        else{
        	Localidade eloPolo = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
        	
        	if (eloPolo.getLocalidade() == null){
        		form.setEloPolo("");
            	form.setDescricaoEloPolo("Código informado não é um Elo Pólo");
            	request.setAttribute("nomeCampo", "eloPolo");
            	request.setAttribute("eloPoloInexistente", "true");
            }
        	
        	else if (!eloPolo.getLocalidade().getId().equals(new Integer(form.getEloPolo()))){
        		form.setEloPolo("");
            	form.setDescricaoEloPolo("Código informado não é um Elo Pólo");
            	request.setAttribute("nomeCampo", "eloPolo");
            	request.setAttribute("eloPoloInexistente", "true");
            }
        	
        	else{
        		form.setEloPolo(String.valueOf(eloPolo.getId()));
            	form.setDescricaoEloPolo(eloPolo.getDescricao());
            	request.setAttribute("nomeCampo", "localidade");
        	}
        }
	}
	
	/**
	 * Pesquisar Localidade
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param form
	 * @param request
	 * @param fachada
	 */
	private void pesquisarLocalidade(InformarDadosGeracaoResumoOrdemServicoConsultaActionForm form, HttpServletRequest request, Fachada fachada){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
    	
        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));

        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection<?> colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
        
        if (Util.isVazioOrNulo(colecaoPesquisa)){
        	form.setLocalidade("");
        	form.setDescricaoLocalidade("Localidade inexistente");
        	request.setAttribute("nomeCampo", "localidade");
        	request.setAttribute("localidadeInexistente", "true");
        }
        
        else{
        	Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
        	
        	form.setLocalidade(String.valueOf(localidade.getId()));
            form.setDescricaoLocalidade(localidade.getDescricao());
            request.setAttribute("nomeCampo", "setorComercial");
        }
	}
	
	/**
	 * Pesquisar Setor Comercial
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param form
	 * @param request
	 * @param fachada
	 */
	private void pesquisarSetorComercial(InformarDadosGeracaoResumoOrdemServicoConsultaActionForm form, HttpServletRequest request, Fachada fachada){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	
    	filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));

    	filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidade()));
    	
    	filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection<?> colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
        
        if (Util.isVazioOrNulo(colecaoPesquisa)){
        	form.setSetorComercial("");
        	form.setDescricaoSetorComercial("Setor Comercial inexistente");
        	request.setAttribute("nomeCampo", "setorComercial");
        	request.setAttribute("setorComercialInexistente", "true");
        }
        
        else{
        	SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
        	
        	form.setIdSetorComercial(String.valueOf(setorComercial.getId()));
        	form.setSetorComercial(String.valueOf(setorComercial.getCodigo()));
            form.setDescricaoSetorComercial(setorComercial.getDescricao());
            request.setAttribute("nomeCampo", "perfilImovel");
        }
	}
	
}
