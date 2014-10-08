package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ImoveisNaoMigradosHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1326] - Consultar Migração dos Setores/Quadra Para Atualizacao Cadastral
 * 
 * @author Davi Menezes
 * @date 25/04/2012
 *
 */
public class ExibirConsultarMigracaoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("consultarMigracaoAtualizacaoCadastral");
		
		ConsultarMigracaoAtualizacaoCadastralActionForm form =
				(ConsultarMigracaoAtualizacaoCadastralActionForm) actionForm;
		
		if(httpServletRequest.getParameter("menu") != null && 
			httpServletRequest.getParameter("menu").equals("sim")){
			
			form.reset();
			limparColecoes(httpServletRequest);
		}
		
		if(httpServletRequest.getParameter("limpar") != null && 
			httpServletRequest.getParameter("limpar").equals("sim")){
			
			form.limpar();
			limparColecoes(httpServletRequest);
		}
		
		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1") && 
			form.getLocalidade() != null && !form.getLocalidade().equals("-1")){
			
			this.pesquisarSetorMigrado(form, httpServletRequest);
			this.pesquisarSetorNaoMigrado(form, httpServletRequest);
			this.pesquisarSetorRetornado(form, httpServletRequest);
			
			if(form.getSetorNaoMigrado() != null && !form.getSetorNaoMigrado().equals("-1")){
				this.pesquisarQuadraNaoMigrada(form, httpServletRequest);
			}
			
			if(form.getIdsQuadrasNaoMigradas() != null && form.getIdsQuadrasNaoMigradas().length > 0){
				this.pesquisarImovelNaoMigrado(form, httpServletRequest);
			}
			
			if(form.getSetorMigrado() != null && !form.getSetorMigrado().equals("-1")){
				this.pesquisarQuadraMigrada(form, httpServletRequest);
			}
			
			if(form.getIdsQuadrasMigradas() != null && form.getIdsQuadrasMigradas().length > 0){
				this.pesquisarImovelMigrado(form, httpServletRequest);
			}
			
			if(form.getSetorRetornado() != null && !form.getSetorRetornado().equals("-1")){
				this.pesquisarQuadraRetornada(form, httpServletRequest);
			}
			
			if(form.getIdsQuadrasRetornadas() != null && form.getIdsQuadrasRetornadas().length > 0){
				this.pesquisarImovelRetornado(form, httpServletRequest);
			}
			
		}else if(form.getIdEmpresa() != null && form.getIdEmpresa().equals("-1")
				&& form.getLocalidade() != null && !form.getLocalidade().equals("")){
			limparColecoes(httpServletRequest);
		}
		
		this.pesquisarEmpresa(httpServletRequest);
		this.pesquisarLocalidade(httpServletRequest, form);
		
		return retorno;
	}
	
	private void limparColecoes(HttpServletRequest httpServletRequest){
		httpServletRequest.removeAttribute("colecaoSetor1");
		httpServletRequest.removeAttribute("colecaoQuadra1");
		httpServletRequest.removeAttribute("colecaoImovel1");
		
		httpServletRequest.removeAttribute("colecaoSetor2");
		httpServletRequest.removeAttribute("colecaoQuadra2");
		httpServletRequest.removeAttribute("colecaoImovel2");
		
		httpServletRequest.removeAttribute("colecaoSetor3");
		httpServletRequest.removeAttribute("colecaoQuadra3");
		httpServletRequest.removeAttribute("colecaoImovel3");
	}
	
	private void pesquisarSetorNaoMigrado(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Collection<ImoveisNaoMigradosHelper> colecaoHelper = this.getFachada().pesquisarSetoresNaoMigrados(
			form.getLocalidade(), form.getSetorNaoMigrado(), form.getQuadraNaoMigrada());
		
		if(!Util.isVazioOrNulo(colecaoHelper)){
			httpServletRequest.setAttribute("colecaoSetor1", colecaoHelper);
		}
	}
	
	private void pesquisarQuadraNaoMigrada(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Collection<ImoveisNaoMigradosHelper> colecaoHelper = this.getFachada().pesquisarQuadrasNaoMigradas(
			form.getLocalidade(), form.getSetorNaoMigrado(), form.getQuadraNaoMigrada());
		
		if(!Util.isVazioOrNulo(colecaoHelper)){
			httpServletRequest.setAttribute("colecaoQuadra1", colecaoHelper);
		}
	}
	
	private void pesquisarImovelNaoMigrado(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Collection<ImoveisNaoMigradosHelper> colecaoHelper = new ArrayList<ImoveisNaoMigradosHelper>();
		Collection<Integer> colecaoIdsQuadras = new ArrayList<Integer>();
		
		for(int i = 0 ; i < form.getIdsQuadrasNaoMigradas().length; i++){
			colecaoIdsQuadras.add(Integer.valueOf(form.getIdsQuadrasNaoMigradas()[i]));
		}
			
		colecaoHelper = this.getFachada().pesquisarImoveisNaoMigrados(
			form.getLocalidade(), form.getSetorNaoMigrado(), colecaoIdsQuadras);
			
		if(!Util.isVazioOrNulo(colecaoHelper) && colecaoHelper.size() <= 30){
			httpServletRequest.setAttribute("colecaoImovel1", colecaoHelper);
		}
		form.setTotalImoveisNaoMigrados(String.valueOf(colecaoHelper.size()));
	}


	private void pesquisarSetorMigrado(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID_LOCALIDADE, form.getLocalidade()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.INDICADOR_DADOS_RETORNO, Short.parseShort("2")));
		filtroImovelAtualizacaoCadastral.setCampoOrderBy(FiltroImovelAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL);
		filtroImovelAtualizacaoCadastral.setCampoDistinct("objeto." + FiltroImovelAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL);
		
		Collection<ImoveisNaoMigradosHelper> colecaoRetorno = new ArrayList<ImoveisNaoMigradosHelper>(); 
		Collection<?> colImoveis = this.getFachada().pesquisar(filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
		
		ImoveisNaoMigradosHelper helper = null;
		
		if(!Util.isVazioOrNulo(colImoveis)){
			Iterator<?> iterator = colImoveis.iterator();
			while(iterator.hasNext()){
				helper = new ImoveisNaoMigradosHelper();
				Integer codSetor = (Integer) iterator.next();
				helper.setCodigoSetor(String.valueOf(codSetor));
				colecaoRetorno.add(helper);
			}
			httpServletRequest.setAttribute("colecaoSetor2", colecaoRetorno);
		}
	}
	
	private void pesquisarQuadraMigrada(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
		
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID_LOCALIDADE, form.getLocalidade()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.INDICADOR_DADOS_RETORNO, Short.parseShort("2")));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL, form.getSetorMigrado()));
		filtroImovelAtualizacaoCadastral.setCampoOrderBy(FiltroImovelAtualizacaoCadastral.NUMERO_QUADRA);
		filtroImovelAtualizacaoCadastral.setCampoDistinct("objeto." + FiltroImovelAtualizacaoCadastral.NUMERO_QUADRA);
		
		Collection<?> colImoveis = this.getFachada().pesquisar(filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
		Collection<ImoveisNaoMigradosHelper> colecaoRetorno = new ArrayList<ImoveisNaoMigradosHelper>();
		
		ImoveisNaoMigradosHelper helper = null;
		
		if(!Util.isVazioOrNulo(colImoveis)){
			Iterator<?> iterator = colImoveis.iterator();
			while(iterator.hasNext()){
				helper = new ImoveisNaoMigradosHelper();
				Integer quadra = (Integer) iterator.next();
				helper.setNumeroQuadra(String.valueOf(quadra));
				colecaoRetorno.add(helper);
			}
			httpServletRequest.setAttribute("colecaoQuadra2", colecaoRetorno);
		}
	}
	
	private void pesquisarImovelMigrado(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Collection<?> colImoveis = null;
		Collection<ImovelAtualizacaoCadastral> colImoveisMigrados = new ArrayList<ImovelAtualizacaoCadastral>();
		
		Collection<Integer> colecaoIdsQuadras = new ArrayList<Integer>(); 
		
		for(int i = 0; i < form.getIdsQuadrasMigradas().length; i++){
			colecaoIdsQuadras.add(Integer.valueOf(form.getIdsQuadrasMigradas()[i]));
		}	
		
		FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID_LOCALIDADE, form.getLocalidade()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.INDICADOR_DADOS_RETORNO, Short.parseShort("2")));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL, form.getSetorMigrado()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimplesIn(FiltroImovelAtualizacaoCadastral.NUMERO_QUADRA, colecaoIdsQuadras)); 
		filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroImovelAtualizacaoCadastral.setCampoOrderBy(FiltroImovelAtualizacaoCadastral.ID_IMOVEL);
	
		colImoveis = this.getFachada().pesquisar(filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
	
		if(!Util.isVazioOrNulo(colImoveis)){
			Iterator<?> iterator = colImoveis.iterator();
			while(iterator.hasNext()){
				ImovelAtualizacaoCadastral imovel = (ImovelAtualizacaoCadastral) iterator.next();
				colImoveisMigrados.add(imovel);
			}
		}
				
		if(!Util.isVazioOrNulo(colImoveisMigrados) && colImoveisMigrados.size() <= 30){
			httpServletRequest.setAttribute("colecaoImovel2", colImoveisMigrados);
		}
		
		form.setTotalImoveisMigrados(String.valueOf(colImoveisMigrados.size()));
	}
	
	private void pesquisarSetorRetornado(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Collection<ImoveisNaoMigradosHelper> colecaoHelper = this.getFachada().pesquisarSetoresRetornados(
			form.getLocalidade(), form.getSetorRetornado(), form.getQuadraRetornada());
			
		if(!Util.isVazioOrNulo(colecaoHelper)){
			httpServletRequest.setAttribute("colecaoSetor3", colecaoHelper);
		}
	}
	
	private void pesquisarQuadraRetornada(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Collection<ImoveisNaoMigradosHelper> colecaoHelper = this.getFachada().pesquisarQuadrasRetornadas(
			form.getLocalidade(), form.getSetorRetornado(), form.getQuadraRetornada());
			
		if(!Util.isVazioOrNulo(colecaoHelper)){
			httpServletRequest.setAttribute("colecaoQuadra3", colecaoHelper);
		}
	}
	
	private void pesquisarImovelRetornado(ConsultarMigracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Collection<ImoveisNaoMigradosHelper> colecaoHelper = new ArrayList<ImoveisNaoMigradosHelper>();
		Collection<Integer> colecaoIdsQuadras = new ArrayList<Integer>(); 
		
		for(int i = 0; i < form.getIdsQuadrasRetornadas().length; i++){
			colecaoIdsQuadras.add(Integer.valueOf(form.getIdsQuadrasRetornadas()[i]));
		}
			
		colecaoHelper = this.getFachada().pesquisarImoveisRetornados(
				form.getLocalidade(), form.getSetorRetornado(), colecaoIdsQuadras);
			
		if(!Util.isVazioOrNulo(colecaoHelper) && colecaoHelper.size() <= 30){
			httpServletRequest.setAttribute("colecaoImovel3", colecaoHelper);
		}
		form.setTotalImoveisRetornados(String.valueOf(colecaoHelper.size()));
	}
	
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, ConsultarMigracaoAtualizacaoCadastralActionForm form) {
		
		if(form.getIdEmpresa() != null && 
			!form.getIdEmpresa().equals("-1") && 
			!form.getIdEmpresa().equals("")){

			Collection<Localidade> colLocalidade = 
				this.getFachada().pesquisarLocalidadeAreaAtualizacaoCadastral(new Integer(form.getIdEmpresa()));
				
			if(!Util.isVazioOrNulo(colLocalidade)){
				httpServletRequest.setAttribute("colecaoLocalidade", colLocalidade);
			}else{
				httpServletRequest.removeAttribute("colecaoLocalidade");
			}
			
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
