package gcom.gui.relatorio.faturamento;

import java.util.Collection;

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
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoMotivo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioSituacaoEspecialFaturamentoAction extends GcomAction{
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoSituacao = null;
		sessao.setAttribute("colecaoSituacao",colecaoSituacao);
		
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioSituacaoEspecialFaturamentoAction");
		
		GerarRelatorioSituacaoEspecialFaturamentoActionForm form = 
				(GerarRelatorioSituacaoEspecialFaturamentoActionForm) actionForm;
		
		if (httpServletRequest.getParameter("campoDesabilita") != null
				&& !httpServletRequest.getParameter("campoDesabilita").equals("")) {
			
			if ((form.getLocalidadeInicialID().toString() != null) &&
					(!form.getLocalidadeInicialID().trim().equals("")) &&
					(form.getLocalidadeFinalID().toString() != null) &&
					(!form.getLocalidadeFinalID().trim().equals("")) &&
					((Integer.valueOf(form.getLocalidadeInicialID().toString())) > 
			(Integer.valueOf(form.getLocalidadeFinalID().toString())))){
				throw new ActionServletException("atencao.localidade_inicial_maior_que_final", null, "Situação Especial Faturamento");
			} 
		 
			else if ((form.getSetorComercialInicialID().toString() != null) &&
						(!form.getSetorComercialInicialID().trim().equals("")) &&
						(form.getSetorComercialFinalID().toString() != null) &&
						(!form.getSetorComercialFinalID().trim().equals("")) &&
						((Integer.valueOf(form.getSetorComercialInicialID().toString())) > 
				(Integer.valueOf(form.getSetorComercialFinalID().toString())))){
					throw new ActionServletException("atencao.setor_comercial_inicial_maior_que_final", null, "Situação Especial Faturamento");
				}
			
		}
		
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("3"))  ) {
			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form,objetoConsulta, httpServletRequest);		
		}
						
		// Pesquisar Setor Comercial		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 	
				(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4"))  ) {
					
			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(form,objetoConsulta, httpServletRequest);	
		}
		
		// Exibir Lista de Situação Especial de Faturamento
		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
/*		filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(
				filtroFaturamentoSituacaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));*/
		filtroFaturamentoSituacaoTipo.setCampoOrderBy(FiltroFaturamentoSituacaoTipo.DESCRICAO);

		Collection<FaturamentoSituacaoTipo> colecaoFaturamentoSituacaoTipo = 
				this.getFachada().pesquisar(
						filtroFaturamentoSituacaoTipo, 
						FaturamentoSituacaoTipo.class.getName());
		
		sessao.setAttribute("colecaoFaturamentoSituacaoTipo",colecaoFaturamentoSituacaoTipo);
		
		// Exibir Lista de Motivos
		FiltroFaturamentoSituacaoMotivo filtroFaturamentoSituacaoMotivo = new FiltroFaturamentoSituacaoMotivo();
/*		filtroFaturamentoSituacaoMotivo.adicionarParametro(new ParametroSimples(
				filtroFaturamentoSituacaoMotivo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));*/
		filtroFaturamentoSituacaoMotivo.setCampoOrderBy(FiltroFaturamentoSituacaoTipo.DESCRICAO);

		Collection<FaturamentoSituacaoMotivo> colecaoFaturamentoSituacaoMotivo = 
				this.getFachada().pesquisar(
						filtroFaturamentoSituacaoMotivo, 
						FaturamentoSituacaoMotivo.class.getName());
		
		sessao.setAttribute("colecaoFaturamentoSituacaoMotivo",colecaoFaturamentoSituacaoMotivo);
		
		this.pesquisarGerenciaRegional(httpServletRequest);
		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		
		if (httpServletRequest.getParameter("limpar") != null
				&& httpServletRequest.getParameter("limpar").equals("sim")) {
			form.setGerenciaRegional("-1");
			form.setUnidadeNegocio("-1");
			form.setLocalidadeInicialID("");
			form.setLocalidadeInicialDesc("");
			form.setLocalidadeFinalID("");
			form.setLocalidadeFinalDesc("");
			form.setSetorComercialInicialID("");
			form.setSetorComercialInicialDesc("");
			form.setSetorComercialFinalID("");
			form.setSetorComercialFinalDesc("");
			form.setMotivo(null);
			form.setSituacao(null);
		} 
		return retorno;
	}
	
    private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<GerenciaRegional> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioSituacaoEspecialFaturamentoActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);	
		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
						ConstantesSistema.INDICADOR_USO_ATIVO));		
		
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}

	private void pesquisarLocalidade(GerarRelatorioSituacaoEspecialFaturamentoActionForm form,
			String objetoConsulta, HttpServletRequest httpServletRequest) {

			Object local = form.getLocalidadeInicialID();
			
			if(!objetoConsulta.trim().equals("1")){
				local = form.getLocalidadeFinalID();
			}
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,local));
			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.INDICADORUSO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
			
			if (!form.getUnidadeNegocio().equals("-1")){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID, form.getUnidadeNegocio()));
			}
			
			if (!form.getGerenciaRegional().equals("-1")){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
			}
			
			// Recupera Localidade
			Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
			if (!form.getUnidadeNegocio().equals("-1") && !form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_gerencia_unidade", form.getUnidadeNegocio(), form.getGerenciaRegional());
			}
			
			if (!form.getUnidadeNegocio().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_unidade_negocio",form.getUnidadeNegocio());
			}
			
			if (!form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_gerencia",form.getGerenciaRegional());
			}
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

				Localidade localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				
				if(objetoConsulta.trim().equals("1")){
					form.setLocalidadeInicialID(localidade.getId().toString());
					form.setLocalidadeInicialDesc(localidade.getDescricao());
				}
				
				form.setLocalidadeFinalID(localidade.getId().toString());
				form.setLocalidadeFinalDesc(localidade.getDescricao());
				httpServletRequest.setAttribute("localidadeInicialEncontrada", "true");
				httpServletRequest.setAttribute("localidadeFinalEncontrada", "true");
			} else {
				if(objetoConsulta.trim().equals("1")){
					form.setLocalidadeInicialID("");
					form.setLocalidadeInicialDesc("LOCALIDADE INEXISTENTE");
					form.setLocalidadeFinalID("");
					form.setLocalidadeFinalDesc("");
					httpServletRequest.removeAttribute("localidadeInicialEncontrada");

				}else{
					form.setLocalidadeFinalID("");
					form.setLocalidadeFinalDesc("LOCALIDADE INEXISTENTE");
					httpServletRequest.removeAttribute("localidadeFinalEncontrada");
				}
			}
		}
	
	private void pesquisarSetorComercial(GerarRelatorioSituacaoEspecialFaturamentoActionForm form,
			String objetoConsulta, HttpServletRequest httpServletRequest) {

			Object local = form.getLocalidadeInicialID();
			Object setor = form.getSetorComercialInicialID();
			
			if(!objetoConsulta.trim().equals("2")){
				local = form.getLocalidadeFinalID();
				setor = form.getSetorComercialFinalID();
			}

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setor));
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			if (local != null && !local.equals("")){
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE,local));
			}
			
			// Recupera Setor Comercial
			Collection<SetorComercial> colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if (((!form.getLocalidadeInicialID().equals("")) && (!form.getLocalidadeInicialID().equals(null)))
					&& ((!form.getLocalidadeInicialDesc().equals("")) & (!form.getLocalidadeInicialDesc().equals(null)))){
				httpServletRequest.setAttribute("localidadeInicialEncontrada", "true");
			} else {
				httpServletRequest.removeAttribute("localidadeInicialEncontrada");	
			}
					
					
			if (((!form.getLocalidadeFinalID().equals("")) && (!form.getLocalidadeFinalID().equals(null)))
					&& ((!form.getLocalidadeFinalDesc().equals("")) & (!form.getLocalidadeFinalDesc().equals(null)))){
				httpServletRequest.setAttribute("localidadeFinalEncontrada", "true");
			} else {
				httpServletRequest.removeAttribute("localidadeFinalEncontrada");	
			}
			
			if (((!form.getSetorComercialInicialID().equals("")) && (!form.getSetorComercialInicialID().equals(null)))
					&& ((!form.getSetorComercialInicialDesc().equals("")) & (!form.getSetorComercialInicialDesc().equals(null)))){
				httpServletRequest.setAttribute("setorComercialInicialEncontrado", "true");
			} else {
				httpServletRequest.removeAttribute("setorComercialInicialEncontrado");	
			}
			
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

				SetorComercial setorComercial = 
					(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
				
				if(objetoConsulta.trim().equals("2")){
					form.setSetorComercialInicialID(""+setorComercial.getCodigo());
					form.setSetorComercialInicialDesc(setorComercial.getDescricao());
				}

				form.setSetorComercialFinalID(""+setorComercial.getCodigo());
				form.setSetorComercialFinalDesc(setorComercial.getDescricao());
				httpServletRequest.setAttribute("setorComercialInicialEncontrado", "true");
				httpServletRequest.setAttribute("setorComercialFinalEncontrado", "true");
				
			} else {

				if(objetoConsulta.trim().equals("2")){
					form.setSetorComercialInicialID("");
					form.setSetorComercialInicialDesc("SETOR COMERCIAL INEXISTENTE");
					form.setSetorComercialFinalID("");
					form.setSetorComercialFinalDesc("");
					httpServletRequest.removeAttribute("setorComercialInicialEncontrado");
				}else{
					form.setSetorComercialFinalID("");
					form.setSetorComercialFinalDesc("SETOR COMERCIAL INEXISTENTE");
					httpServletRequest.removeAttribute("setorComercialFinalEncontrado");
				}	
			}
		}	
}