package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.bean.GerarArquivoTxtSmartphoneHelper;
import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirGerarArquivoTextoOrdensServicoSmartphoneAction extends GcomAction {

	private Fachada fachada;
	private GerarArquivoTextoOrdensServicoSmartphoneActionForm form;
	private HttpSession sessao;
	private Usuario usuario;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarArquivoTextoOrdensServicoSmartphone");
		
		fachada = Fachada.getInstancia();
		form = (GerarArquivoTextoOrdensServicoSmartphoneActionForm) actionForm;
		sessao = httpServletRequest.getSession(false);
		usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Identificador do tipo de OS
		String identificadorOS = httpServletRequest.getParameter("identificadorOS");
		
		//CARREGAMENTO INICIAL DO FORMULARIO
		this.carregamentoInicial(httpServletRequest, identificadorOS);
		
		//CARREGAMENTO DO FILTRO
		String tipoPesquisa = httpServletRequest.getParameter("tipoPesquisa");
		
		//Comando Selecionado
		String comando = httpServletRequest.getParameter("comando");
		
		
		//1.2.2. Caso tenha sido passado como parâmetro o Identificador do Tipo de OS
		if(identificadorOS != null && !identificadorOS.equals("")){
			
			//1.2.2.1. Textbox desabilitado com a descrição do Identificador do Tipo de OS.
			form.setIdTipoOS(identificadorOS);
			
			//1.3. Caso o Tipo da Ordem de Serviço corresponda a "O.S. DE MICROMEDICAO":
			if(identificadorOS.equals(ConstantesSistema.IDENTIFICADOR_TIPO_OS_MICROMEDICAO.toString())){
				form.setDescricaoTipoOS("O.S. DE MICROMEDICAO");
				
				if(comando != null){
					//1.3.1.2. Textbox desabilitado com a descrição do comando 
					//         [IT0014 - Obter Descrição do Comando Correção de Anormalidade]
					ComandoOrdemSeletiva  comandoOrdemSeletiva = fachada.pesquisarComandoOSSeletiva(new Integer(comando));
					
					if(comandoOrdemSeletiva != null){
						form.setIdComandoCorrecaoAnormalidade(comandoOrdemSeletiva.getId());
						form.setDescricaoComandoCorrecaoAnormalidade(comandoOrdemSeletiva.getDescricaoComando().toUpperCase());
					}
					else{
						form.setIdComandoCorrecaoAnormalidade(null);
						form.setDescricaoComandoCorrecaoAnormalidade(null);
					}
					
					//[IT0015] Obter Descrição do Tipo de Serviço do Comando
					form.setDescricaoTipoServicoComando(comandoOrdemSeletiva.getServicoTipo().getDescricao());
					form.setIdTipoServicoComando(comandoOrdemSeletiva.getServicoTipo().getId().toString());
					
				}
				else{
					form.setIdComandoCorrecaoAnormalidade(null);
					form.setDescricaoComandoCorrecaoAnormalidade(null);
				}
				
			}
			else {
				form.setDescricaoTipoOS("O.S. DE COBRANÇA");
			}
			
			
			
		}
		
		else{
			form.setIdTipoOS(ConstantesSistema.IDENTIFICADOR_TIPO_OS_COBRANCA.toString());
			form.setDescricaoTipoOS("O.S. DE COBRANÇA");
		}

		
		if (tipoPesquisa != null && tipoPesquisa.equals("gerenciaRegional")) {
			
			carregamentoGerenciaRegional();
		}
		else if (tipoPesquisa != null && tipoPesquisa.equals("localidade")) {
			if (!existeLocalidade()) {
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		} 
		else if (tipoPesquisa != null && tipoPesquisa.equals("setorComercial")) {
			int tipoSetor = Integer.parseInt(httpServletRequest.getParameter("tipoSetor"));
			if (!existeSetorComercial(tipoSetor)) {
				if(tipoSetor == 1){
					httpServletRequest.setAttribute("setorComercialOrigemInexistente", true);
					httpServletRequest.setAttribute("setorComercialDestinoInexistente", true);
				}
				else{
					httpServletRequest.setAttribute("setorComercialDestinoInexistente", true);
				}
			}
		}
		else if (tipoPesquisa != null && tipoPesquisa.equals("quadra")) {
			int tipoQuadra = Integer.parseInt(httpServletRequest.getParameter("tipoQuadra"));
			if (existeQuadra(tipoQuadra) == false) {
				if(tipoQuadra == 1){
					form.setCodigoQuadraInicial("");					
					form.setCodigoQuadraFinal("");					
					httpServletRequest.setAttribute("quadraInicialInexistente", true);
					httpServletRequest.setAttribute("quadraFinalInexistente", true);
					
				}
				else{
					form.setCodigoQuadraFinal("");
					httpServletRequest.setAttribute("quadraFinalInexistente", true);
				}
			}
		}
		else if(tipoPesquisa !=null && tipoPesquisa.equals("consultarQdt")){					
			
			Integer qtd = fachada.consultarOrdemServicoTXTSmartphone(
				this.conveterParametro(form.getEmpresa()),
				this.conveterParametro(form.getGerenciaRegional()),
				this.conveterParametro(form.getUnidadeNegocio()),
				this.conveterParametro(form.getLocalidade()),
				this.conveterParametro(form.getCodigoSetorComercialOrigem()),
				this.conveterParametro(form.getCodigoSetorComercialDestino()),
				this.conveterParametro(form.getCodigoQuadraInicial()),
				this.conveterParametro(form.getCodigoQuadraFinal()),
				form.getIdsServicoTipo(),
				comando, identificadorOS
				).size();			
			
			form.setQtdOsSeletiva(qtd.toString());
			
		}
		
		else if(tipoPesquisa !=null && tipoPesquisa.equals("consultarOS")){
			Collection<Object[]> colecaoOS = fachada.consultarOrdemServicoTXTSmartphone(
					this.conveterParametro(form.getEmpresa()),
					this.conveterParametro(form.getGerenciaRegional()),
					this.conveterParametro(form.getUnidadeNegocio()),
					this.conveterParametro(form.getLocalidade()),
					this.conveterParametro(form.getCodigoSetorComercialOrigem()),
					this.conveterParametro(form.getCodigoSetorComercialDestino()),
					this.conveterParametro(form.getCodigoQuadraInicial()),
					this.conveterParametro(form.getCodigoQuadraFinal()),
					form.getIdsServicoTipo(),
					comando, identificadorOS);
			
			//Marcando as unidades no formulário
			if(form.getQtdMaxOS() != null 
					&& !form.getQtdMaxOS().equals("")){
				
				Integer qtd = Integer.parseInt(form.getQtdMaxOS());
				String[] selecionados;
				
				if(qtd.compareTo(colecaoOS.size()) > 0){
					selecionados = new String[colecaoOS.size()];
				}
				else{
					selecionados = new String[qtd];
				}
				
				//Preenchendo com os ids da coleção
				Iterator it = colecaoOS.iterator();
				int contador = 0;
				while(it.hasNext() && contador < selecionados.length){
					Object[] obj = (Object[])it.next();
					selecionados[contador] = ((Integer)obj[1]).toString();
					contador++;
				}
				
				form.setIdsRegistros(selecionados);
				
				//Criando os helpers
				Iterator it2 = colecaoOS.iterator();
				Collection<GerarArquivoTxtSmartphoneHelper> colecaoHelper = new ArrayList<GerarArquivoTxtSmartphoneHelper>();
				while(it2.hasNext()){
					Object[] obj = (Object[])it2.next();
					String inscricaoImovel = fachada.pesquisarInscricaoImovel((Integer)obj[3]);
					
					GerarArquivoTxtSmartphoneHelper helper = new GerarArquivoTxtSmartphoneHelper(
							obj,
							((Integer)obj[1]).toString(), //Id OS
							((Integer)obj[3]).toString(), //Id Imovel
							inscricaoImovel,              //Inscricao Imovel
							(String)obj[14]   			  //Tipo Servico
					);
					
					colecaoHelper.add(helper);
				}
				
				//Adicionando ao formulário
				form.setColecaoOS(colecaoHelper);
			}
		}
		
		else if(tipoPesquisa !=null && tipoPesquisa.equals("excluirColecaoOS")){
			form.setIdsRegistros(null);
			form.setColecaoOS(null);
			return null;
		}
		
		else {
			form.setUnidadeNegocio(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
			form.setLocalidade("");
			form.setNomeLocalidade("");
			form.setIdSetorComercialOrigem("");
			form.setCodigoSetorComercialOrigem("");
			form.setDescricaoSetorComercialOrigem("");
			form.setIdSetorComercialDestino("");
			form.setCodigoSetorComercialDestino("");
			form.setDescricaoSetorComercialDestino("");
			form.setCodigoQuadraFinal("");
			form.setCodigoQuadraInicial("");
			form.setQtdOsSeletiva("");
			form.setColecaoOS(null);
		}
		
		
		return retorno;
	}
	
	private Integer conveterParametro(String parametro){
		
		Integer retorno = null;
		
		if (parametro != null && !parametro.equals("")){
			
			retorno = Integer.valueOf(parametro);
		}
		
		return retorno;
	}
	
	
	private void carregamentoGerenciaRegional(){
		
		form.setUnidadeNegocio(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		form.setLocalidade("");
		form.setNomeLocalidade("");
		form.setIdSetorComercialOrigem("");
		form.setCodigoSetorComercialOrigem("");
		form.setDescricaoSetorComercialOrigem("");
		form.setIdSetorComercialDestino("");
		form.setCodigoSetorComercialDestino("");
		form.setDescricaoSetorComercialDestino("");
		form.setCodigoQuadraFinal("");
		form.setCodigoQuadraInicial("");
		form.setQtdOsSeletiva("");
		form.setColecaoOS(null);
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroGerenciaRegional.NOME);

		filtroUnidadeNegocio.setConsultaSemLimites(true);

		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
			FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if (form.getGerenciaRegional() != null &&
			!(Integer.valueOf(form.getGerenciaRegional())).equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.ID_GERENCIA, Integer.valueOf(form.getGerenciaRegional())));
		}
		

		Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
			UnidadeNegocio.class.getName());

		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
	}
	
	
	private void carregamentoInicial(HttpServletRequest httpServletRequest, String identificadorOS){
		
		
		Collection colecaoEmpresa = (Collection) sessao.getAttribute("colecaoEmpresa");
		
		if (colecaoEmpresa == null && (form.getDescricaoEmpresa() == null || form.getDescricaoEmpresa().equals(""))){
			
			colecaoEmpresa = fachada.validarEmpresaPrincipal(usuario);
			
			if (colecaoEmpresa.size() == 1){
				
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
				form.setEmpresa(empresa.getId().toString());
				form.setDescricaoEmpresa(empresa.getDescricao());
			}
			else{
				
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}
		
		String idTipoOrdemServico = httpServletRequest.getParameter("idTipoOrdemServico");
		
		if (idTipoOrdemServico != null && !idTipoOrdemServico.equals("")){
			
			if ((Integer.valueOf(idTipoOrdemServico)).equals(OrdemServico.ORDEM_SERVICO_COBRANCA)){
				form.setIdTipoOS(idTipoOrdemServico);
				form.setDescricaoTipoOS(OrdemServico.DESCRICAO_ORDEM_SERVICO_COBRANCA);
			}
		}
		
		/*Collection colecaoGerenciaRegional = (Collection) sessao.getAttribute("colecaoGerenciaRegional");

		if (colecaoGerenciaRegional == null) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);

			filtroGerenciaRegional.setConsultaSemLimites(true);

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
				GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
				
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"GERENCIA_REGIONAL");
			} 
			else {
				
				sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
			}
		}
		
		Collection colecaoUnidadeNegocio = (Collection) sessao.getAttribute("colecaoUnidadeNegocio");

		if (colecaoUnidadeNegocio == null) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroGerenciaRegional.NOME);

			filtroUnidadeNegocio.setConsultaSemLimites(true);

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
				UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
				
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"UNIDADE_NEGOCIO");
			} 
			else {
				
				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			}
		}*/
		
		Collection colecaoServicoTipo = (Collection) sessao.getAttribute("colecaoServicoTipo");

		if (colecaoServicoTipo == null) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo(FiltroServicoTipo.DESCRICAO);

			filtroServicoTipo.setConsultaSemLimites(true);

			filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.INDICADOR_SERVICO_COBRANCA, ConstantesSistema.INDICADOR_USO_ATIVO));								
			

			colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo,
				ServicoTipo.class.getName());

			if (colecaoServicoTipo == null || colecaoServicoTipo.isEmpty()) {
				
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"SERVICO_TIPO");
			} 
			else {
				
				sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
			}
		}
		
		SistemaParametro sis = fachada.pesquisarParametrosDoSistema();
		form.setQtdMaxOS( sis.getNumeroLimiteOSCobranca() != null ? sis.getNumeroLimiteOSCobranca()+"" : "");
	}
	
	private Boolean existeLocalidade() {
		String localidadeCod = form.getLocalidade();

		if (localidadeCod != null && !localidadeCod.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeCod));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				form.setLocalidade(localidade.getId().toString());
				form.setNomeLocalidade(localidade.getDescricao());

				return true;
			} else {
				form.setLocalidade("");
				form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
				return false;
			}
		} else {
			return false;
		}
	}

	private Boolean existeSetorComercial(int tipo) {

		String codigoSetorComercial;
		if (tipo == 1) {
			codigoSetorComercial = form.getCodigoSetorComercialOrigem();
		} else {
			codigoSetorComercial = form.getCodigoSetorComercialDestino();
		}

		if (codigoSetorComercial != null && !codigoSetorComercial.trim().equals("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(form.getLocalidade())));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorComercial));

			Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
				if (tipo == 1) {
					form.setIdSetorComercialOrigem(setorComercial.getId().toString());
					form.setCodigoSetorComercialOrigem(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialOrigem(setorComercial.getDescricao());
					form.setIdSetorComercialDestino(""+ setorComercial.getId());
					form.setCodigoSetorComercialDestino(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
				} else {
					form.setIdSetorComercialDestino(""+ setorComercial.getId());
					form.setCodigoSetorComercialDestino(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
				}
				return true;
			} else {
				if (tipo == 1) {
					form.setCodigoSetorComercialOrigem("");
					form.setDescricaoSetorComercialOrigem("SETOR COMERCIAL INEXISTENTE");
					form.setCodigoSetorComercialDestino("");
					form.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");
				} else {
					form.setCodigoSetorComercialDestino("");
					form.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");
				}
				return false;
			}
		} else {
			return false;
		}

	}
	
	private Boolean existeQuadra(int tipo) {
		String quadra;
		if(tipo == 1){
			quadra = form.getCodigoQuadraInicial();
		}
		else{
			quadra = form.getCodigoQuadraFinal();
		}
		
		if(quadra !=null && !quadra.trim().equals("")){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getLocalidade()));
			if(tipo == 1){
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialOrigem()));
			}
			else{
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialDestino()));
			}
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadra));
			Collection<Quadra> colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			
			if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
				if(tipo == 1){
					form.setCodigoQuadraInicial(quadra);
				}else{
					form.setCodigoQuadraFinal(quadra);
				}
				return true;
			}
		}
		return false;
	}
}
