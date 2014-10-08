package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.DadosGeracaoResumoOSConsultaHelper;
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
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
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
 * [UC 1558] - Filtrar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 19/09/2013
 *
 */
public class InformarDadosGeracaoResumoOSConsultaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("consultarResumoAcaoOSParametros");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"consultarResumoAcaoOSWizardAction",
				"exibirInformarDadosGeracaoResumoOSConsultaAction",
				"cancelarConsultarResumoAcaoOSAction",
				"exibirInformarDadosGeracaoResumoOSConsultaAction",
				"informarDadosGeracaoResumoOrdemServicoConsultaAction.do");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ParametrosPrimeiraAbaA.gif",
						"ParametrosPrimeiraAbaD.gif",
						"exibirDadosGeracaoConsultaOSAction", ""));
		
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "ResumoUltimaAbaA.gif", "ResumoUltimaAbaD.gif",
						"exibirConsultarResumoAcaoOSAction", ""));

		// manda o statusWizard para a sessão
		sessao.setAttribute("statusWizard", statusWizard);
		
		InformarDadosGeracaoResumoOrdemServicoConsultaActionForm form = 
				(InformarDadosGeracaoResumoOrdemServicoConsultaActionForm) actionForm;
		
		DadosGeracaoResumoOSConsultaHelper helper = new DadosGeracaoResumoOSConsultaHelper();
		
		String mesAnoReferencia = form.getMesAnoReferencia();
		if(mesAnoReferencia == null || mesAnoReferencia.equals("")){
			throw new ActionServletException("atencao.campo.informado", null, "Mês/Ano Referência");
		}else{
			helper.setAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(mesAnoReferencia));				
			
			 // [FS0002] - Validar Referencia Resumo
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			String anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao().toString();
			
			String dataReferencia = helper.getAnoMesReferencia().toString();
			
			if(Util.compararAnoMesReferenciaSemBarra(dataReferencia, anoMesArrecadacao, ">")){
				throw new ActionServletException("atencao.anoMesReferencia_anoMesArrecadacao");
			}			
		}		
		
		String eloPolo = form.getEloPolo();
		if(eloPolo != null && !eloPolo.equals("")){
			Integer idEloPolo = Integer.parseInt(eloPolo);
			
			this.validarEloPolo(idEloPolo, fachada);
			
			helper.setEloPolo(idEloPolo);
		}
		
		String localidade = form.getLocalidade();
		if(localidade != null && !localidade.equals("")){
			Integer idLocalidade = Integer.parseInt(localidade);
			
			this.validarLocalidade(idLocalidade, fachada);
			
			helper.setIdLocalidade(idLocalidade);
			
			String setorComercial = form.getSetorComercial();
			if(setorComercial != null && !setorComercial.equals("")){
				Integer codigoSetorComercial = Integer.parseInt(setorComercial);
				
				this.validarSetorComercial(idLocalidade, codigoSetorComercial, fachada);
				
				helper.setCodigoSetorComercial(codigoSetorComercial);
				
			}
		}
		
		String[] servicoTipo = form.getServicoTipo();
		if(servicoTipo != null && servicoTipo.length > 0){
			Collection<Integer> colecaoServicoTipo = new ArrayList<Integer>();
			
			Integer idServicoTipo = null;
			for(int i = 0; i < servicoTipo.length; i++){
				idServicoTipo = Integer.parseInt(servicoTipo[i]);
				
				if(idServicoTipo != null && !idServicoTipo.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoServicoTipo.add(idServicoTipo);
				}
			}
			
			helper.setColecaoServicoTipo(colecaoServicoTipo);
		}
		
		String[] gerenciaRegional = form.getGerencialRegional();
		if(gerenciaRegional != null && gerenciaRegional.length > 0){
			Collection<Integer> colecaoGerenciaRegional = new ArrayList<Integer>();
			
			Integer idGerenciaRegional = null;
			for(int i = 0; i < gerenciaRegional.length; i++){
				idGerenciaRegional = Integer.parseInt(gerenciaRegional[i]);
				
				if(idGerenciaRegional != null && !idGerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoGerenciaRegional.add(idGerenciaRegional);
				}
			}
			
			helper.setColecaoGerenciaRegional(colecaoGerenciaRegional);
		}
		
		String[] unidadeNegocio = form.getUnidadeNegocio();
		if(unidadeNegocio != null && unidadeNegocio.length > 0){
			Collection<Integer> colecaoUnidadeNegocio = new ArrayList<Integer>();
			
			Integer idUnidadeNegocio = null;
			for(int i = 0; i < unidadeNegocio.length; i++){
				idUnidadeNegocio = Integer.parseInt(unidadeNegocio[i]);
				
				if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoUnidadeNegocio.add(idUnidadeNegocio);
				}
			}
			
			helper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);
		}
		
		String[] imovelPerfil = form.getPerfilImovel();
		if(imovelPerfil != null && imovelPerfil.length > 0){
			Collection<Integer> colecaoImovelPerfil = new ArrayList<Integer>();
			
			Integer idImovelPerfil = null;
			for(int i = 0; i < imovelPerfil.length; i++){
				idImovelPerfil = Integer.parseInt(imovelPerfil[i]);
				
				if(idImovelPerfil != null && !idImovelPerfil.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoImovelPerfil.add(idImovelPerfil);
				}
			}
			
			helper.setColecaoImovelPerfil(colecaoImovelPerfil);
		}
		
		String[] ligacaoAguaSituacao = form.getSituacaoLigacaoAgua();
		if(ligacaoAguaSituacao != null && ligacaoAguaSituacao.length > 0){
			Collection<Integer> colecaoLigacaoAguaSituacao = new ArrayList<Integer>();
			
			Integer idLigacaoAguaSituacao = null;
			for(int i = 0; i < ligacaoAguaSituacao.length; i++){
				idLigacaoAguaSituacao = Integer.parseInt(ligacaoAguaSituacao[i]);
				
				if(idLigacaoAguaSituacao != null && !idLigacaoAguaSituacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoLigacaoAguaSituacao.add(idLigacaoAguaSituacao);
				}
			}
			
			helper.setColecaoLigacaoAguaSituacao(colecaoLigacaoAguaSituacao);
		}
		
		String[] ligacaoEsgotoSituacao = form.getSituacaoLigacaoEsgoto();
		if(ligacaoEsgotoSituacao != null && ligacaoEsgotoSituacao.length > 0){
			Collection<Integer> colecaoLigacaoEsgotoSituacao = new ArrayList<Integer>();
			
			Integer idLigacaoEsgotoSituacao = null;
			for(int i = 0; i < ligacaoEsgotoSituacao.length; i++){
				idLigacaoEsgotoSituacao = Integer.parseInt(ligacaoEsgotoSituacao[i]);
				
				if(idLigacaoEsgotoSituacao != null && !idLigacaoEsgotoSituacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoLigacaoEsgotoSituacao.add(idLigacaoEsgotoSituacao);
				}
			}
			
			helper.setColecaoLigacaoEsgotoSituacao(colecaoLigacaoEsgotoSituacao);
		}
		
		String[] categoria = form.getCategoria();
		if(categoria != null && categoria.length > 0){
			Collection<Integer> colecaoCategoria  = new ArrayList<Integer>();
			
			Integer idCategoria = null;
			for(int i = 0; i < categoria.length; i++){
				idCategoria = Integer.parseInt(categoria[i]);
				
				if(idCategoria != null && !idCategoria.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoCategoria.add(idCategoria);
				}
				
				helper.setColecaoCategorias(colecaoCategoria);
			}
		}
		
		String[] esferaPoder = form.getEsferaPoder();
		if(esferaPoder != null && esferaPoder.length > 0){
			Collection<Integer> colecaoEsferaPoder = new ArrayList<Integer>();
			
			Integer idEsferaPoder = null;
			for(int i = 0; i < esferaPoder.length; i++){
				idEsferaPoder = Integer.parseInt(esferaPoder[i]);
				
				if(idEsferaPoder != null && !idEsferaPoder.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoEsferaPoder.add(idEsferaPoder);
				}
			}
			
			helper.setColecaoEsferaPoder(colecaoEsferaPoder);
		}
		
		String[] empresa = form.getEmpresa();
		if(empresa != null && empresa.length > 0){
			Collection<Integer> colecaoEmpresa = new ArrayList<Integer>();
			
			Integer idEmpresa = null;
			for(int i = 0; i < empresa.length; i++){
				idEmpresa = Integer.parseInt(empresa[i]);
				
				if(idEmpresa != null && !idEmpresa.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoEmpresa.add(idEmpresa);
				}
			}
			
			helper.setColecaoEmpresa(colecaoEmpresa);
		}
		
		sessao.setAttribute("dadosGeracaoResumoOSConsultaHelper", helper);
		
		this.montarColecaoServicoTipo(servicoTipo, fachada, sessao);
		this.montarColecaoGerenciaRegional(gerenciaRegional, fachada, sessao);
		this.montarColecaoUnidadeNegocio(unidadeNegocio, fachada, sessao);
		this.montarColecaoImovelPerfil(imovelPerfil, fachada, sessao);
		this.montarColecaoLigacaoAguaSituacao(ligacaoAguaSituacao, fachada, sessao);
		this.montarColecaoLigacaoEsgotoSituacao(ligacaoEsgotoSituacao, fachada, sessao);
		this.montarColecaoCategoria(categoria, fachada, sessao);
		this.montarColecaoEsferaPoder(esferaPoder, fachada, sessao);
		this.montarColecaoEmpresa(empresa, fachada, sessao);
		
		return retorno;
	}
	
	/**
	 * Validar o Elo Polo Informado
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param idEloPolo
	 * @param fachada
	 */
	private void validarEloPolo(Integer idEloPolo, Fachada fachada){
		boolean ehEloPolo = true;
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
    	filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.ID_ELO);
    	
        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idEloPolo));

        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection<?> colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
        
        if (Util.isVazioOrNulo(colecaoPesquisa)){
        	throw new ActionServletException("pesquisa.elo.inexistente");
        }else{
        	Localidade eloPolo = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
        	
        	if (eloPolo.getLocalidade() == null){
        		ehEloPolo = false;
            }else if (!eloPolo.getLocalidade().getId().equals(idEloPolo)){
        		ehEloPolo = false;
            }
        }
        
        if(!ehEloPolo){
        	throw new ActionServletException("atencao.localidade.nao.elo");
        }
    }
	
	/**
	 * Validar Localidade Informada
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param idLocalidade
	 * @param fachada
	 */
	private void validarLocalidade(Integer idLocalidade, Fachada fachada){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		if(Util.isVazioOrNulo(colecaoLocalidade)){
			throw new ActionServletException("pesquisa.localidade.inexistente");
		}
	}
	
	/**
	 * Validar Setor Comercial Informado
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param fachada
	 */
	private void validarSetorComercial(Integer idLocalidade, Integer codigoSetorComercial, Fachada fachada){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if(Util.isVazioOrNulo(colecaoSetorComercial)){
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}
	}
	
	/**
	 * Montar a Coleção de Servico Tipo
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param servicoTipo
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoServicoTipo(String[] servicoTipo, Fachada fachada, HttpSession sessao){
		ServicoTipo servicoTipoColecao = new ServicoTipo();
		servicoTipoColecao.setId(-1);
		
		Collection<ServicoTipo> colecaoServicoTipo = new ArrayList<ServicoTipo>();
		
		if(servicoTipo != null && servicoTipo.length > 0 && !servicoTipo[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			servicoTipoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoServicoTipo.add(servicoTipoColecao);
			
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico(FiltroTipoServico.DESCRICAO);
			
			for(int i = 0; i < servicoTipo.length; i++){
				if(!servicoTipo[i].equals("") && !servicoTipo[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					if(i + 1 < servicoTipo.length){
						filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, servicoTipo[i], 
								ConectorOr.CONECTOR_OR, servicoTipo.length));
					}else{
						filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, servicoTipo[i]));
					}
				}
			}
			
			Collection<ServicoTipo> colecaoServicoTipoPesquisa = fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoServicoTipoPesquisa)){
				colecaoServicoTipo.addAll(colecaoServicoTipoPesquisa);
			}
			
		}else{
			servicoTipoColecao.setDescricao("TODOS");
			colecaoServicoTipo.add(servicoTipoColecao);
		}
		
		sessao.setAttribute("colecaoServicoTipoResultado", colecaoServicoTipo);
	}
	
	/**
	 * Montar a Coleção de Gerência Regional
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param gerenciaRegional
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoGerenciaRegional(String[] gerenciaRegional, Fachada fachada, HttpSession sessao){
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection<GerenciaRegional> colecaoGerenciaRegional = new ArrayList<GerenciaRegional>();

		if (gerenciaRegional != null && gerenciaRegional.length > 0 && !gerenciaRegional[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			gerenciaRegionalColecao.setNome("OPÇÕES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);

			for (int i = 0; i < gerenciaRegional.length; i++) {
				if (!gerenciaRegional[i].equals("") && !gerenciaRegional[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < gerenciaRegional.length) {
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, gerenciaRegional[i],
								ConectorOr.CONECTOR_OR, gerenciaRegional.length));
					} else {
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, gerenciaRegional[i]));
					}
				}
			}

			Collection<GerenciaRegional> colecaoGerenciaRegionalPesquisa = 
					fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (!Util.isVazioOrNulo(colecaoGerenciaRegionalPesquisa)) {
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
			}
		
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}

		sessao.setAttribute("colecaoGerenciaRegionalResultado", colecaoGerenciaRegional);
	}
	
	/**
	 * Montar Coleção de Unidade de Negócio
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param unidadeNegocio
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoUnidadeNegocio(String[] unidadeNegocio, Fachada fachada, HttpSession sessao){
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = new ArrayList<UnidadeNegocio>();

		if (unidadeNegocio != null && unidadeNegocio.length > 0 && !unidadeNegocio[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			unidadeNegocioColecao.setNome("OPÇÕES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);

			for (int i = 0; i < unidadeNegocio.length; i++) {
				if (!unidadeNegocio[i].equals("") && !unidadeNegocio[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < unidadeNegocio.length) {
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, unidadeNegocio[i],
								ConectorOr.CONECTOR_OR, unidadeNegocio.length));
					} else {
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, unidadeNegocio[i]));
					}
				}
			}

			Collection<UnidadeNegocio> colecaoUnidadeNegocioPesquisa = 
					fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (!Util.isVazioOrNulo(colecaoUnidadeNegocioPesquisa)) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		
		} else {
			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}

		sessao.setAttribute("colecaoUnidadeNegocioResultado", colecaoUnidadeNegocio);
	}
	
	/**
	 * Montar a Coleção de Imóvel Perfil
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param imovelPerfil
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoImovelPerfil(String[] imovelPerfil, Fachada fachada, HttpSession sessao){
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection<ImovelPerfil> colecaoImovelPerfil = new ArrayList<ImovelPerfil>();

		if (imovelPerfil != null && imovelPerfil.length > 0 && !imovelPerfil[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			imovelPerfilColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil(FiltroImovelPerfil.DESCRICAO);

			for (int i = 0; i < imovelPerfil.length; i++) {
				if (!imovelPerfil[i].equals("") && !imovelPerfil[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < imovelPerfil.length) {
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, imovelPerfil[i],
								ConectorOr.CONECTOR_OR, imovelPerfil.length));
					} else {
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, imovelPerfil[i]));
					}
				}
			}

			Collection<ImovelPerfil> colecaoImovelPerfilPesquisa = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

			if (!Util.isVazioOrNulo(colecaoImovelPerfilPesquisa)) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}

		sessao.setAttribute("colecaoImovelPerfilResultado", colecaoImovelPerfil);
	}
	
	/**
	 * Montar Coleção de Ligação Água Situação
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param ligacaoAguaSituacao
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoLigacaoAguaSituacao(String[] ligacaoAguaSituacao, Fachada fachada, HttpSession sessao){
		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = new ArrayList<LigacaoAguaSituacao>();

		if (ligacaoAguaSituacao != null && ligacaoAguaSituacao.length > 0 && !ligacaoAguaSituacao[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ligacaoAguaSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(FiltroLigacaoAguaSituacao.DESCRICAO);

			for (int i = 0; i < ligacaoAguaSituacao.length; i++) {
				if (!ligacaoAguaSituacao[i].equals("") && !ligacaoAguaSituacao[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < ligacaoAguaSituacao.length) {
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.ID, ligacaoAguaSituacao[i], ConectorOr.CONECTOR_OR, ligacaoAguaSituacao.length));

					} else {
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.ID, ligacaoAguaSituacao[i]));
					}
				}
			}

			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (!Util.isVazioOrNulo(colecaoLigacaoAguaSituacaoPesquisa)) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoAguaSituacaoResultado", colecaoLigacaoAguaSituacao);
	}
	
	/**
	 * Montar Coleção de Ligação Esgoto Situação
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param ligacaoEsgotoSituacao
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoLigacaoEsgotoSituacao(String[] ligacaoEsgotoSituacao, Fachada fachada, HttpSession sessao){
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoColecao.setId(-1);

		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = new ArrayList<LigacaoEsgotoSituacao>();

		if (ligacaoEsgotoSituacao != null && ligacaoEsgotoSituacao.length > 0 && !ligacaoEsgotoSituacao[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			for (int i = 0; i < ligacaoEsgotoSituacao.length; i++) {
				if (!ligacaoEsgotoSituacao[i].equals("") && !ligacaoEsgotoSituacao[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < ligacaoEsgotoSituacao.length) {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.ID, ligacaoEsgotoSituacao[i], ConectorOr.CONECTOR_OR, ligacaoEsgotoSituacao.length));
					} else {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.ID, ligacaoEsgotoSituacao[i]));
					}
				}
			}

			Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacaoPesquisa = 
					fachada.pesquisar(filtroLigacaoEsgotoSituacao,LigacaoEsgotoSituacao.class.getName());

			if (!Util.isVazioOrNulo(colecaoLigacaoEsgotoSituacaoPesquisa)) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoEsgotoSituacaoResultado", colecaoLigacaoEsgotoSituacao);
	}
	
	/**
	 * Montar Coleção de Categoria
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param categoria
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoCategoria(String[] categoria, Fachada fachada, HttpSession sessao){
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection<Categoria> colecaoCategoria = new ArrayList<Categoria>();

		if (categoria != null && categoria.length > 0 && !categoria[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			categoriaColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			
			FiltroCategoria filtroCategoria = new FiltroCategoria(FiltroCategoria.DESCRICAO);

			for (int i = 0; i < categoria.length; i++) {
				if (!categoria[i].equals("") && !categoria[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < categoria.length) {
						filtroCategoria.adicionarParametro(new ParametroSimples(
								FiltroCategoria.CODIGO, categoria[i], ConectorOr.CONECTOR_OR, categoria.length));
					} else {
						filtroCategoria.adicionarParametro(new ParametroSimples(
								FiltroCategoria.CODIGO, categoria[i]));
					}
				}
			}

			Collection<Categoria> colecaoCategoriaPesquisa = fachada.pesquisar(filtroCategoria, Categoria.class.getName());

			if (!Util.isVazioOrNulo(colecaoCategoriaPesquisa)) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}

		sessao.setAttribute("colecaoCategoriaResultado", colecaoCategoria);
	}
	
	/**
	 * Montar Coleção de Esfera Poder
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param esferaPoder
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoEsferaPoder(String[] esferaPoder, Fachada fachada, HttpSession sessao){
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection<EsferaPoder> colecaoEsferaPoder = new ArrayList<EsferaPoder>();

		if (esferaPoder != null && esferaPoder.length > 0 && !esferaPoder[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			esferaPoderColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);

			for (int i = 0; i < esferaPoder.length; i++) {
				if (!esferaPoder[i].equals("") && !esferaPoder[i].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < esferaPoder.length) {
						filtroEsferaPoder.adicionarParametro(new ParametroSimples(
								FiltroEsferaPoder.ID, esferaPoder[i], ConectorOr.CONECTOR_OR, esferaPoder.length));

					} else {
						filtroEsferaPoder.adicionarParametro(new ParametroSimples(
								FiltroEsferaPoder.ID, esferaPoder[i]));
					}
				}
			}

			Collection<EsferaPoder> colecaoEsferaPoderPesquisa = 
					fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());

			if (!Util.isVazioOrNulo(colecaoEsferaPoderPesquisa)) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}

		sessao.setAttribute("colecaoEsferaPoderResultado", colecaoEsferaPoder);
	}
	
	/**
	 * Montar Coleção de Empresa da Unidade Atual
	 * 
	 * @author Davi Menezes
	 * @date 19/09/2013
	 * 
	 * @param empresa
	 * @param fachada
	 * @param sessao
	 */
	@SuppressWarnings("unchecked")
	private void montarColecaoEmpresa(String[] empresa, Fachada fachada, HttpSession sessao){
		Empresa empresaColecao = new Empresa();
		empresaColecao.setId(-1);

		Collection<Empresa> colecaoEmpresa = new ArrayList<Empresa>();

		if (empresa != null && empresa.length > 0 && !empresa[0].equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			empresaColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoEmpresa.add(empresaColecao);
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);

			for (int i = 0; i < empresa.length; i++) {
				if (!empresa[i].equals("") && !empresa[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < empresa.length) {
						filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, empresa[i],
								ConectorOr.CONECTOR_OR, empresa.length));
					} else {
						filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, empresa[i]));
					}
				}
			}

			Collection<Empresa> colecaoEmpresaPesquisa = 
					fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

			if (!Util.isVazioOrNulo(colecaoEmpresaPesquisa)) {
				colecaoEmpresa.addAll(colecaoEmpresaPesquisa);
			}
		
		} else {
			empresaColecao.setDescricao("TODOS");
			colecaoEmpresa.add(empresaColecao);
		}

		sessao.setAttribute("colecaoEmpresaResultado", colecaoEmpresa);
	}
	
}
