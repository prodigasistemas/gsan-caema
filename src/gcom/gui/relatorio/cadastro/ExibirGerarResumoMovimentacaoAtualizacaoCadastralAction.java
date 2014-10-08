package gcom.gui.relatorio.cadastro;

import gcom.cadastro.MensagemAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroMensagemAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarResumoMovimentacaoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("resumoMovimentacaoAtualizacaoCadastral");
		
		GerarResumoMovimentacaoAtualizacaoCadastralActionForm form =
				(GerarResumoMovimentacaoAtualizacaoCadastralActionForm) actionForm;
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		// Verificamos se foi chamado do menu
		if ( httpServletRequest.getParameter("menu") != null &&
			 ( (String ) httpServletRequest.getParameter("menu") ).equals("sim") ){
			form.reset();
		}
		
		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("3"))) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form,objetoConsulta);
		}
		
		// Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4"))) {

			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(form,objetoConsulta);
		}
		
		//Pesquisar Quadra
		if(objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("5") || objetoConsulta.trim().equals("6"))){
			
			//Faz a consulta de quadra
			this.pesquisarQuadra(form, objetoConsulta);
		}
		
		this.pesquisarEmpresa(httpServletRequest);
		this.pesquisarGerenciaRegional(httpServletRequest);
		this.pesquisarAnalista(httpServletRequest);
		this.pesquisarMensagemAtualizacaoCadastral(httpServletRequest);
		
		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
			this.pesquisarCadastrador(httpServletRequest, form);
		}else{
			httpServletRequest.removeAttribute("colecaoCadastrador");
		}
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		this.setaRelatorios(httpServletRequest);
		
		if(form != null && form.getIdsRegistro() != null){
			httpServletRequest.setAttribute("IdsRegistroSelecionado", form.getIdsRegistro()[0]);
		}
		
		return retorno;
	}
	
	private void pesquisarMensagemAtualizacaoCadastral(HttpServletRequest request) {
		
		Collection<Integer> colecaoIdsMensagens = new ArrayList<Integer>();
		colecaoIdsMensagens.add(9);
		colecaoIdsMensagens.add(10);
		colecaoIdsMensagens.add(23);
		FiltroMensagemAtualizacaoCadastral filtroMensagemAtualizacaoCadastral = new FiltroMensagemAtualizacaoCadastral();
		filtroMensagemAtualizacaoCadastral.adicionarParametro( new ParametroSimplesNotIn(FiltroMensagemAtualizacaoCadastral.ID, colecaoIdsMensagens));
		filtroMensagemAtualizacaoCadastral.setCampoOrderBy(FiltroMensagemAtualizacaoCadastral.MENSAGEM);
		Collection<?> colecaoMensagemAtualizacaoCadastral = Fachada.getInstancia().pesquisar(filtroMensagemAtualizacaoCadastral, MensagemAtualizacaoCadastral.class.getName());
		
		if ( colecaoMensagemAtualizacaoCadastral != null && !colecaoMensagemAtualizacaoCadastral.isEmpty() ) {
			request.setAttribute("colecaoMensagem", colecaoMensagemAtualizacaoCadastral);
		}else{
			request.removeAttribute("colecaoMensagem");
		}
	}

	private void pesquisarAnalista(HttpServletRequest httpServletRequest) {
		FiltroUsuario filtroUsuario = new FiltroUsuario(FiltroUsuario.NOME_USUARIO);
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.USUARIO_SITUACAO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colUsuario = this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName());
		
		if(!Util.isVazioOrNulo(colUsuario)){
			httpServletRequest.setAttribute("colecaoUsuario", colUsuario);
		}else{
			httpServletRequest.removeAttribute("colecaoUsuario");
		}
		
	}

	private void pesquisarCadastrador(HttpServletRequest httpServletRequest,
			GerarResumoMovimentacaoAtualizacaoCadastralActionForm form) {
		
//		FiltroCadastrador filtroCadastrador = new FiltroCadastrador(FiltroCadastrador.NOME);
//		
//		filtroCadastrador.adicionarParametro(new ParametroSimples(FiltroCadastrador.EMPRESA_ID, form.getIdEmpresa()));
//		
//		Collection<?> colCadastrador = this.getFachada().pesquisar(filtroCadastrador, Cadastrador.class.getName());
		
		Collection<Usuario> colCadastrador = this.getFachada().pesquisarUsuarioAtuCadastral(new Integer (form.getIdEmpresa()));
		
		if(!Util.isVazioOrNulo(colCadastrador)){
			httpServletRequest.setAttribute("colecaoCadastrador", colCadastrador);
		}else{
			httpServletRequest.removeAttribute("colecaoCadastrador");
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

	private void pesquisarLocalidade(GerarResumoMovimentacaoAtualizacaoCadastralActionForm form, 
			String objetoConsulta) {
		
		String local = form.getLocalidadeInicial();
		
		if(!objetoConsulta.trim().equals("1")){
			local = form.getLocalidadeFinal();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,local));
		
		if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")){
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
		}
		
		// Recupera Localidade
		Collection<?> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (!Util.isVazioOrNulo(colecaoLocalidade)) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}
			
			form.setLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());
			
		} else {
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial("");
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");
				
				form.setLocalidadeFinal("");
				form.setNomeLocalidadeFinal("");
			}else{
				form.setLocalidadeFinal("");
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}
	
	private void pesquisarSetorComercial(GerarResumoMovimentacaoAtualizacaoCadastralActionForm form,
		String objetoConsulta) {

		Object local = form.getLocalidadeInicial();
		Object setor = form.getSetorComercialInicial();
		
		if(!objetoConsulta.trim().equals("2")){
			local = form.getLocalidadeFinal();
			setor = form.getSetorComercialFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setor));
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE,local));
		
		// Recupera Setor Comercial
		Collection<?> colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if (!Util.isVazioOrNulo(colecaoSetorComercial)) {

			SetorComercial setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			if(objetoConsulta.trim().equals("2")){
				form.setSetorComercialInicial(""+setorComercial.getCodigo());
				form.setNomeSetorComercialInicial(setorComercial.getDescricao());
			}

			form.setSetorComercialFinal(""+setorComercial.getCodigo());
			form.setNomeSetorComercialFinal(setorComercial.getDescricao());
			
		} else {
			if(objetoConsulta.trim().equals("2")){
				form.setSetorComercialInicial("");
				form.setNomeSetorComercialInicial("Setor Comercial Inicial inexistente");
				
				form.setSetorComercialFinal("");
				form.setNomeSetorComercialFinal("");
			}else{
				form.setSetorComercialFinal("");
				form.setNomeSetorComercialFinal("Setor Comercial Final inexistente");
			}
			
		}
	}
	
	private void pesquisarQuadra(GerarResumoMovimentacaoAtualizacaoCadastralActionForm form, String objetoConsulta) {
		String local = form.getLocalidadeInicial();
		String setor = form.getSetorComercialInicial();
		String quadra = form.getQuadraInicial();
		
		if(!objetoConsulta.trim().equals("5")){
			local = form.getLocalidadeFinal();
			setor = form.getSetorComercialFinal();
			quadra = form.getQuadraFinal();
		}
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, local));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, setor));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadra));
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
		
		Collection<?> colQuadra = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if(!Util.isVazioOrNulo(colQuadra)){
			Quadra novaQuadra = (Quadra) Util.retonarObjetoDeColecao(colQuadra);
			
			if(objetoConsulta.trim().equals("5")){
				form.setQuadraInicial(""+novaQuadra.getNumeroQuadra());
				if(novaQuadra.getBairro() != null){
					form.setNomeQuadraInicial(novaQuadra.getBairro().getNome() + " " + novaQuadra.getDescricao());
				}else{
					form.setNomeQuadraInicial(novaQuadra.getDescricao());
				}
			}
			form.setQuadraFinal(""+novaQuadra.getNumeroQuadra());
			if(novaQuadra.getBairro() != null){
				form.setNomeQuadraFinal(novaQuadra.getBairro().getNome() + " " + novaQuadra.getDescricao());
			}else{
				form.setNomeQuadraFinal(novaQuadra.getDescricao());
			}
		}else{
			if(objetoConsulta.trim().equals("5")){
				form.setQuadraInicial("");
				form.setNomeQuadraInicial("Quadra Inexistente");
				
				form.setQuadraFinal("");
				form.setNomeQuadraFinal("Quadra Inexistente");
			}else{
				form.setQuadraFinal("");
				form.setNomeQuadraFinal("Quadra Inexistente");
			}
		}
	}
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		if (Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			httpServletRequest.removeAttribute("colecaoGerenciaRegional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarResumoMovimentacaoAtualizacaoCadastralActionForm form){
		
		//Localidade Inicial
		if(form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") && 
			form.getNomeLocalidadeInicial() != null && 
			!form.getNomeLocalidadeInicial().equals("")){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}else{

			if(form.getLocalidadeFinal() != null && 
				!form.getLocalidadeFinal().equals("") && 
				form.getNomeLocalidadeFinal() != null && 
				!form.getNomeLocalidadeFinal().equals("")){
								
				httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
			}
		}
		
		//Setor Comercial Inicial
		if(form.getSetorComercialInicial() != null && 
			!form.getSetorComercialInicial().equals("") && 
			form.getNomeSetorComercialInicial() != null && 
			!form.getNomeSetorComercialInicial().equals("")){
					
			httpServletRequest.setAttribute("setorComercialInicialEncontrado","true");
			httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
		}else{

			if(form.getSetorComercialFinal() != null && 
				!form.getSetorComercialFinal().equals("") && 
				form.getNomeSetorComercialFinal() != null && 
				!form.getNomeSetorComercialFinal().equals("")){
								
				httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
			}
		}
		
		//Quadra Inicial
		if(form.getQuadraInicial() != null && !form.getQuadraInicial().equals("") 
			&& form.getNomeQuadraInicial() != null && !form.getNomeQuadraInicial().equals("")){
			
			httpServletRequest.setAttribute("quadraInicialEncontrada", "true");
			httpServletRequest.setAttribute("quadraFinalEncontrada", "true");
		}else{
			if(form.getQuadraFinal() != null && !form.getQuadraFinal().equals("")
				&& form.getNomeQuadraFinal() != null && !form.getNomeQuadraFinal().equals("")){
				
				httpServletRequest.setAttribute("quadraFinalEncontrada", "true");
			}
		}
	}
	
	private void setaRelatorios(HttpServletRequest httpServletRequest) {
		DadosResumoMovimentoAtualizacaoCadastralHelper helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		Collection<DadosResumoMovimentoAtualizacaoCadastralHelper> colecaoHelper = 
				new ArrayList<DadosResumoMovimentoAtualizacaoCadastralHelper>();
		
		helper.setId("1");
		helper.setNomeRelatorio("Resumo da Posição da Atualização Cadastral");
		colecaoHelper.add(helper);
		
		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		helper.setId("2");
		helper.setNomeRelatorio("Resumo da Situação dos Imóveis por Cadastrador/Analista");
		colecaoHelper.add(helper);
		
		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		helper.setId("3");
		helper.setNomeRelatorio("Resumo das Mensagens Pendentes por Cadastrador");
		colecaoHelper.add(helper);
		
		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		helper.setId("4");
		helper.setNomeRelatorio("Resumo do Quantitativo das Mensagens Pendentes");
		colecaoHelper.add(helper);
		
		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		helper.setId("5");
		helper.setNomeRelatorio("Relatório de Análise das Inconsistências");
		colecaoHelper.add(helper);
		
//		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
//		helper.setId("5");
//		helper.setNomeRelatorio("Resumo da Posição da Atualização Cadastral por Pacote");
//		colecaoHelper.add(helper);
		
		httpServletRequest.setAttribute("colecaoDadosResumoMovimentoAtualizacaoCadastralHelper", colecaoHelper);
		
	}
	
}
