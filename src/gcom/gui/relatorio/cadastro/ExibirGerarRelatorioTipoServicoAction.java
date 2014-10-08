package gcom.gui.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1333] Gerar Relatório Tipo de Servico
 * 
 * @author Carlos Chaves
 * @date 04/05/2012
 */
public class ExibirGerarRelatorioTipoServicoAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioTipoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarRelatorioTipoServicoActionForm form = (GerarRelatorioTipoServicoActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
		
		List colecaoUnidadeOrganizacional = new ArrayList();
		List colecaoUsuario = new ArrayList();

		if (sessao.getAttribute("colecaoUnidadeOrganizacional") != null
				&& !sessao.getAttribute("colecaoUnidadeOrganizacional").equals("")) {
			colecaoUnidadeOrganizacional = (List) sessao.getAttribute("colecaoUnidadeOrganizacional");
		}
		if (sessao.getAttribute("colecaoUsuario") != null
				&& !sessao.getAttribute("colecaoUsuario").equals("")) {
			colecaoUsuario = (List) sessao.getAttribute("colecaoUsuario");
		}
		
		// Carregar lista de Meios
			
			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
			filtroMeioSolicitacao.adicionarParametro(
					new ParametroSimples(FiltroMeioSolicitacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			
			Collection<MeioSolicitacao> colecaoMeiosSolicitacao = null;
		
			
			if( form.getIdServico().equals("1") ||  form.getIdServico().equals("2") ||  form.getIdServico().equals("3")){
				
				filtroMeioSolicitacao.adicionarParametro(
						new ParametroSimples(FiltroMeioSolicitacao.ID, 1, FiltroParametro.CONECTOR_OR, 2));

				filtroMeioSolicitacao.adicionarParametro(
						new ParametroSimples(FiltroMeioSolicitacao.ID, 8));
					
			}	
			
			
			colecaoMeiosSolicitacao = 
					this.getFachada().pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());
			if(!Util.isVazioOrNulo(colecaoMeiosSolicitacao)){
				sessao.setAttribute("colecaoMeiosSolicitacao",colecaoMeiosSolicitacao);
			}
			
	
		
		// Carregar lista das Gerências Regionais
		if (sessao.getAttribute("colecaoGerenciaRegional") == null
				|| sessao.getAttribute("colecaoGerenciaRegional").equals("")) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
	
			List<GerenciaRegional> gerenciasRegionais = (List<GerenciaRegional>) fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());
			
			Comparator comp = new Comparator<GerenciaRegional>() {
				public int compare(GerenciaRegional left, GerenciaRegional right) {  
	                return left.getNome().compareTo(right.getNome());
	            } 
			};
			
			Collections.sort(gerenciasRegionais, comp);
			
			httpServletRequest.setAttribute("colecaoGerenciaRegional",
					gerenciasRegionais);
		}

		// Carregar lista das Unidades de Negócio
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null
				|| sessao.getAttribute("colecaoUnidadeNegocio").equals("")) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
	
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
		}
		
		// Pesquisar Unidade Superior
		if(Util.verificarNaoVazio(form.getIdUnidadeSuperior())){
			if(pesquisarUnidadeSuperior(form,sessao)){
				httpServletRequest.setAttribute("nomeCampo", "idUsuario");
			}else{
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeSuperior");
			}
					
		}
		
		//Pesquisar Unidade Organizacional
		if(Util.verificarNaoVazio(form.getIdUnidadeOrganizacional())){
			if(pesquisarUnidadeOrganizacional(form,sessao)){
				httpServletRequest.setAttribute("nomeCampo", "idUsuario");
			}else{
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeOrganizacional");
			}
		}

		if(httpServletRequest.getParameter("pesquisarUsuario") != null && httpServletRequest.getParameter("pesquisarUsuario").equals("ok")){
			//Pesquisar Usuario
			if(Util.verificarNaoVazio(form.getIdUsuario())){
				if(!pesquisarUsuario(form,sessao)){
					httpServletRequest.setAttribute("nomeCampo", "idUsuario");
				}
			}
		}
		
		//Pesquisar Localidade
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			if(!pesquisarLocalidade(form,sessao)){
				httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
			}
		}

		// Adicionar Unidade Organizacional
		if (httpServletRequest.getParameter("adicionarUnidadeOrganizacional") != null
				&& httpServletRequest.getParameter("adicionarUnidadeOrganizacional").equals("OK")
				&& Util.verificarNaoVazio(form.getIdUnidadeOrganizacional())){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, 
					new Integer(form.getIdUnidadeOrganizacional())));

			Collection colecaoUnidade = this.getFachada().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
			if (!Util.isVazioOrNulo(colecaoUnidade)) {
				
				UnidadeOrganizacional unidadeOrganizacional = 
					(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
								
				if(!contem(colecaoUnidadeOrganizacional, unidadeOrganizacional)){
					colecaoUnidadeOrganizacional.add(unidadeOrganizacional);
					sessao.setAttribute("colecaoUnidadeOrganizacional", colecaoUnidadeOrganizacional);
					form.setIdUnidadeOrganizacional("");
					form.setDescUnidadeOrganizacional("");
				}
				
				
			}
		}

		// Adicionar Usuário
		if (httpServletRequest.getParameter("adicionarUsuario") != null
				&& httpServletRequest.getParameter("adicionarUsuario").equals("OK")
				&& Util.verificarNaoVazio(form.getIdUsuario())){
			
			//Pesquisar Usuario
			if(Util.verificarNaoVazio(form.getIdUsuario())){
				if(pesquisarUsuario(form,sessao)){

					FiltroUsuario filtroUsuario = new FiltroUsuario();

					filtroUsuario.adicionarParametro(new ParametroSimples(
							FiltroUsuario.ID, 
							new Integer(form.getIdUsuario())));

					Collection colecaoUsuarioPesquisa = this.getFachada().pesquisar(
							filtroUsuario, Usuario.class.getName());

					if (!Util.isVazioOrNulo(colecaoUsuarioPesquisa)) {

						Usuario usuario = 
								(Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioPesquisa);
						if(!colecaoUsuario.contains(usuario)){
							colecaoUsuario.add(usuario);
							sessao.setAttribute("colecaoUsuario", colecaoUsuario);
							form.setIdUsuario("");
							form.setDescUsuario("");
						}

					}

				}
			}	
		}
		
		if(httpServletRequest.getParameter("CarregarMeioSolicitacao") != null && httpServletRequest.getParameter("CarregarMeioSolicitacao").equals("OK")){
			
			if( form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("gerenciaRegional")){
				form.setIdGerenciaRegional("");
			}
			
			if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("gerenciaRegionalLocalidade")){
				form.setIdGerenciaRegionalporLocalidade("");
			}
			
			if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("unidadeNegocio")){
				form.setIdUnidadeNegocio("");
			}
			
			if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("localidade")){
				form.setIdLocalidade("");
				form.setDescLocalidade("");
			}
			
		}
		
		if(httpServletRequest.getParameter("limpar") != null && httpServletRequest.getParameter("limpar").equals("sim")){
		
			form.setOpcaoTotalizacao("");
			form.setOpcaoTipoRelatorio("");
			form.setTipoRelatorioEscolhido(-1);
			form.setIdGerenciaRegional("");
			form.setIdGerenciaRegionalporLocalidade("");
			form.setIdUnidadeNegocio("");
			form.setDescLocalidade("");
			form.setIdLocalidade("");
			

		
		//Limpar Unidade Organizacional
		if(colecaoUnidadeOrganizacional != null){
			
			colecaoUnidadeOrganizacional.removeAll(colecaoUnidadeOrganizacional);
			sessao.setAttribute("colecaoUnidadeOrganizacional", colecaoUnidadeOrganizacional);
		}
		
		//LimparUsuario
		if(colecaoUsuario != null){

			colecaoUsuario.removeAll(colecaoUsuario);
			sessao.setAttribute("colecaoUsuario", colecaoUsuario);
		}

		
		
	} 
		
		

		// Remover Unidade Organizacional
		if (httpServletRequest.getParameter("removerUnidadeOrganizacional") != null
				&& httpServletRequest.getParameter("removerUnidadeOrganizacional").equals("OK")
				&& httpServletRequest.getParameter("idRegistro") != null
				&& !httpServletRequest.getParameter("idRegistro").equals("")){
			Integer posicao = new Integer(httpServletRequest.getParameter("idRegistro"));
			
			if (posicao <= colecaoUnidadeOrganizacional.size()) {
				colecaoUnidadeOrganizacional.remove(posicao - 1);
				sessao.setAttribute("colecaoUnidadeOrganizacional", colecaoUnidadeOrganizacional);
			}
			
		}
		
		//Remover Usuário
		if (httpServletRequest.getParameter("removerUsuario") != null
				&& httpServletRequest.getParameter("removerUsuario").equals("OK")
				&& httpServletRequest.getParameter("idRegistro") != null
				&& !httpServletRequest.getParameter("idRegistro").equals("")){
			Integer posicao = new Integer(httpServletRequest.getParameter("idRegistro"));
			
			if (posicao <= colecaoUsuario.size()) {
				colecaoUsuario.remove(posicao - 1);
				sessao.setAttribute("colecaoUsuario", colecaoUsuario);
			}
			
		}
		
		if(colecaoUnidadeOrganizacional != null
				&& !colecaoUnidadeOrganizacional.isEmpty()){
			httpServletRequest.setAttribute("colecaoUnidadeVazia", "nao");
		}
		
		return retorno;
	}
	
	private boolean pesquisarUnidadeSuperior(GerarRelatorioTipoServicoActionForm form, HttpSession sessao){

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, 
				new Integer(form.getIdUnidadeSuperior())));
		
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade(
				FiltroUnidadeOrganizacional.UNIDADE_SUPERIOR);

		Collection colecaoUnidadeSuperior = this.getFachada().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
	
		if(form.getIdUsuario().equals("") && !form.getDescUsuario().equals("") ){
			form.setDescUsuario("");
		}
		
		if ( Util.isVazioOrNulo(colecaoUnidadeSuperior)) {
				form.setIdUnidadeSuperior("");
				form.setDescUnidadeSuperior("Unidade organizacional informada não é uma unidade superior");
				sessao.removeAttribute("unidadeSuperiorEncontrada");
				return false;
		}
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeSuperior);
		
		form.setIdUnidadeSuperior(unidadeOrganizacional.getUnidadeSuperior().getId().toString());
		form.setDescUnidadeSuperior(unidadeOrganizacional.getUnidadeSuperior().getDescricao());
		sessao.setAttribute("unidadeSuperiorEncontrada","");
		
		return true;
	}
	
	private boolean pesquisarUnidadeOrganizacional(GerarRelatorioTipoServicoActionForm form, HttpSession sessao){

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, 
				new Integer(form.getIdUnidadeOrganizacional())));

		Collection colecaoUnidadeOrganizacional = this.getFachada().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if(form.getIdUsuario().equals("") && !form.getDescUsuario().equals("") ){
			form.setDescUsuario("");
		}
		
		if ( Util.isVazioOrNulo(colecaoUnidadeOrganizacional)) {
				form.setIdUnidadeOrganizacional("");
				form.setDescUnidadeOrganizacional("Unidade Organizacional Inexistente");
				sessao.removeAttribute("unidadeOrganizacionalEncontrada");
				return false;
		}
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		form.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
		form.setDescUnidadeOrganizacional(unidadeOrganizacional.getDescricao());
		sessao.setAttribute("unidadeOrganizacionalEncontrada","");

		return true;
	}
	
	
	private boolean pesquisarUsuario(GerarRelatorioTipoServicoActionForm form, HttpSession sessao){

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		boolean filtrarUnidadade = false;
		
		
		
		
		if(form.getDescUnidadeSuperior().equalsIgnoreCase("Unidade organizacional informada não é uma unidade superior")){
				form.setDescUnidadeSuperior("");
		}
		
		if(form.getDescUnidadeOrganizacional().equalsIgnoreCase("Unidade Organizacional Inexistente")){
			form.setDescUnidadeOrganizacional("");
		}
			
		
		
		
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, new Integer(form.getIdUsuario())));
		
	
		if(form.getIdUnidadeSuperior() != null && !form.getIdUnidadeSuperior().equals("")
				&& !form.getIdUnidadeSuperior().equals("-1")){

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.UNIDADE_ORGANIZACIONAL_ID, new Integer(form.getIdUnidadeSuperior())));

			filtrarUnidadade = true;
		}
		
		
		
		
		List <UnidadeOrganizacional> colecaoUnidadeOrganizacional  = new ArrayList();
		colecaoUnidadeOrganizacional = (List) sessao.getAttribute("colecaoUnidadeOrganizacional");
		Object[] dadosUnidadeUsuario = null;
		
		
		Collection colecaoUsuario = this.getFachada().pesquisar(
				filtroUsuario, Usuario.class.getName());
		
		
		if(colecaoUnidadeOrganizacional != null && colecaoUnidadeOrganizacional.size() > 0){
		
			boolean usuarioPertenceUnidadeOrganizacional = false;
			
			for(UnidadeOrganizacional unidadeOrganizacional : colecaoUnidadeOrganizacional){
				
				Integer idUsuario = Integer.parseInt(form.getIdUsuario())  ;
				dadosUnidadeUsuario = this.getFachada().pesquisarUnidadeOrganizacionalUsuario(idUsuario);
				
				if(dadosUnidadeUsuario[0].equals(unidadeOrganizacional.getId())){
					
					usuarioPertenceUnidadeOrganizacional = true;
					
				}

			}
			
			if(usuarioPertenceUnidadeOrganizacional){
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				form.setIdUsuario(usuario.getId().toString());
				form.setDescUsuario(usuario.getNomeUsuario());
				sessao.setAttribute("usuarioEncontrado","");
			
				return true;
			}else{
				form.setIdUsuario("");
				form.setDescUsuario("O usuário não pertence à unidade selecionada.");
				sessao.removeAttribute("usuarioEncontrado");
				return false;
			}
		}
				
				
		if ( Util.isVazioOrNulo(colecaoUsuario)) {
			
			form.setIdUsuario("");
			
			if(filtrarUnidadade){
				form.setDescUsuario("O usuário não pertence à unidade selecionada.");
			}else{
				form.setDescUsuario("Usuario Inexistente");
			}
			
			sessao.removeAttribute("usuarioEncontrado");
			return false;
		}
		
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
		
		form.setIdUsuario(usuario.getId().toString());
		form.setDescUsuario(usuario.getNomeUsuario());
		sessao.setAttribute("usuarioEncontrado","");
	
		return true;
		
	
	}
	
	private boolean pesquisarLocalidade(GerarRelatorioTipoServicoActionForm form, HttpSession sessao){

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, new Integer(form.getIdLocalidade())));

		Collection colecaoLocalidade = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				form.setIdLocalidade("");
				form.setDescLocalidade("Localidade Inexistente");
				sessao.removeAttribute("localidadeEncontrada");
				return false;
		}
		
		Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
		
		form.setIdLocalidade(localidade.getId().toString());
		form.setDescLocalidade(localidade.getDescricao());
		sessao.setAttribute("localidadeEncontrada","");
		
		return true;
	}

	private boolean contem(List colecaoUnidadeOrganizacional, UnidadeOrganizacional unidadeOrganizacional) {
		Iterator iterator = colecaoUnidadeOrganizacional.iterator();
		
		while (iterator.hasNext()) {
			UnidadeOrganizacional unidadeOrganizacionalIterator = (UnidadeOrganizacional) iterator.next();
			if (unidadeOrganizacional.getId().equals(unidadeOrganizacionalIterator.getId()))
				return true;
		}
			
		return false;
	}
	
}
