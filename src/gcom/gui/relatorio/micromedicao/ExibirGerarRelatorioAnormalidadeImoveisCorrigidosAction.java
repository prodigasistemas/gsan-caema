package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.micromedicao.FiltroAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirGerarRelatorioAnormalidadeImoveisCorrigidosAction extends GcomAction {
	
	/**
	 * [UC1434] Gerar Relatório Anormalidade de Imóveis Corrigidos.
	 * 
	 * @author Jonathan Marcos
	 *
	 * @date 06/02/2013
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAnormalidadeImoveisCorrigidos");
		
		GerarRelatorioAnormalidadeImoveisCorrigidosActionForm form = (GerarRelatorioAnormalidadeImoveisCorrigidosActionForm) actionForm;
		
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("3"))  ) {

			this.consultarLocalidade(form,objetoConsulta);
				
		}
		
	
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4"))  ) {

			this.consultarSetorComercial(form,objetoConsulta);
		}

		this.consultarGerenciaRegional(httpServletRequest);
		this.consultarUnidadeNegocio(httpServletRequest, form);
		
		this.request(httpServletRequest, form);
		
		return retorno;
	}
	
	private void consultarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	private void consultarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioAnormalidadeImoveisCorrigidosActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getIdGerenciaRegional() != null && 
			!form.getIdGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getIdGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}
	
	private void consultarLocalidade(GerarRelatorioAnormalidadeImoveisCorrigidosActionForm form,
			String objetoConsulta) {

			Object local = form.getLocalidadeInicial();
			
			if(!objetoConsulta.trim().equals("1")){
				local = form.getLocalidadeFinal();
			}
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.ID,local));
			
			// Recupera Localidade
			Collection colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

				Localidade localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				
				if(objetoConsulta.trim().equals("1")){
					form.setLocalidadeInicial(localidade.getId().toString());
					form.setNomeLocalidadeInicial(localidade.getDescricao());
				}
				
				form.setLocalidadeFinal(localidade.getId().toString());
				form.setNomeLocalidadeFinal(localidade.getDescricao());

				
			} else {
				if(objetoConsulta.trim().equals("1")){
					form.setLocalidadeInicial(null);
					form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
					
					form.setLocalidadeFinal(null);
					form.setNomeLocalidadeFinal(null);
				}else{
					form.setLocalidadeFinal(null);
					form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
				}
			}
		}

	private void request(HttpServletRequest httpServletRequest,
			GerarRelatorioAnormalidadeImoveisCorrigidosActionForm form){
		
		Fachada fachada = Fachada.getInstancia();
		
		//Localidade Inicial
		if(form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") && 
			form.getNomeLocalidadeInicial() != null && 
			!form.getNomeLocalidadeInicial().equals("")){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
			
			if(form.getLocalidadeFinal() != null && 
					!form.getLocalidadeFinal().equals("") && 
					form.getNomeLocalidadeFinal() != null && 
					!form.getNomeLocalidadeFinal().equals("")){
									
					httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
				}
			
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
			
			if(form.getSetorComercialFinal() != null && 
					!form.getSetorComercialFinal().equals("") && 
					form.getNomeSetorComercialFinal() != null && 
					!form.getNomeSetorComercialFinal().equals("")){
											
					httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
				}
			
		}else{
			if(form.getSetorComercialFinal() != null && 
				!form.getSetorComercialFinal().equals("") && 
				form.getNomeSetorComercialFinal() != null && 
				!form.getNomeSetorComercialFinal().equals("")){
										
				httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
			}

		}
			
		Collection<FiltroAnormalidade> colecaoAnormalidade = fachada.pesquisarAnormalidade();
		
		if(colecaoAnormalidade == null || colecaoAnormalidade.isEmpty()){
			
			System.out.println("request enviado com sucesso!");
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null," Anormalidade");
			
		}else{
			
			httpServletRequest.setAttribute("colecaoAnormalidade", colecaoAnormalidade);
			
		}
		
		
	}
	
	
	private void consultarSetorComercial(GerarRelatorioAnormalidadeImoveisCorrigidosActionForm form,
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
			Collection colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

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
					form.setSetorComercialInicial(null);
					form.setNomeSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
					
					form.setSetorComercialFinal(null);
					form.setNomeSetorComericalFinal(null);
				}else{
					form.setSetorComercialFinal(null);
					form.setNomeSetorComericalFinal("SETOR COMERCIAL INEXISTENTE");
				}
				
			}
		}
	
	
				
		
}
