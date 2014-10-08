package gcom.gui.faturamento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.RegistrarLeiturasAnormalidadesRelatorioActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
/**
 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
 * 
 * @author Diogo Luiz
 * @Date 15/10/2013
 * 
 */
public class ExibirPagamentosAbastecimentosCarroPipaRelatorioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

				// Seta o retorno
				ActionForward retorno = actionMapping
						.findForward("pagamentosAbastecimentosCarroPipaRelatorio");				
				
				Fachada fachada = Fachada.getInstancia();
				
				PagamentosAbastecimentosCarroPipaRelatorioActionForm form = 
						(PagamentosAbastecimentosCarroPipaRelatorioActionForm) actionForm;
				
				Collection faturamentosGrupos = null;
				
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.ID);
				
				faturamentosGrupos = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
				
				if(Util.isVazioOrNulo(faturamentosGrupos)){
					throw new ActionServletException("atencao.pesquisa.nenhumresultado",
						null, "faturamento grupo");
				}
					
					httpServletRequest.setAttribute("faturamentosGrupos",
						faturamentosGrupos);
				
				this.pesquisarGerenciaRegional(httpServletRequest);
				this.pesquisarUnidadeNegocio(httpServletRequest, form);		

		return retorno;
	}	
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
	
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
	
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			PagamentosAbastecimentosCarroPipaRelatorioActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
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

}





