package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Davi Menezes
 * @date 27/02/2012
 *
 */
public class ExibirGerarRelatorioPesquisaSatisfacaoAction extends GcomAction {

	/**
     * Método responsavel por responder a requisicao
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	ActionForward retorno = actionMapping.findForward("pesquisaSatisfacaoGerarRelatorio");
        
        GerarRelatorioPesquisaSatisfacaoActionForm form = (GerarRelatorioPesquisaSatisfacaoActionForm) actionForm;
        
        if(httpServletRequest.getParameter("tipoConsulta") == null){
        	form.setTipo("2");
        }
        
        if(httpServletRequest.getParameter("tipoConsulta") != null 
        		&& httpServletRequest.getParameter("tipoConsulta").equals("imovel")){
        	this.pesquisarImovel(form, httpServletRequest);
        }
        
        if(httpServletRequest.getParameter("tipoConsulta") != null
        		&& httpServletRequest.getParameter("tipoConsulta").equals("unidade")){
        	this.pesquisarUnidadeOrganizacional(form, httpServletRequest);
        }
        
        return retorno;
    }
    
    /**
	 * Pesquisa Imóvel 
	 *
	 * @author Davi Menezes
	 * @date 29/02/2012
	 */
	private void pesquisarImovel(GerarRelatorioPesquisaSatisfacaoActionForm form,
			HttpServletRequest httpServletRequest) {

		// Filtra Imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getIdImovel()));		
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		
		// Recupera Imóvel
		Collection<?> colecaoImovel = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
	
		if (!Util.isVazioOrNulo(colecaoImovel)) {

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			
			form.setIdImovel(imovel.getId().toString());
			form.setDescricaoImovel(imovel.getInscricaoFormatada());
			httpServletRequest.removeAttribute("idImovelNaoEncontrado");
			
		} else {
			form.setIdImovel("");
			form.setDescricaoImovel("Matrícula inexistente");
			httpServletRequest.setAttribute("idImovelNaoEncontrado","true");
		}
	}
	
	/**
	 * Pesquisa Unidade Organizacional
	 * 
	 * @author Davi Menezes
	 * @date 29/02/2012
	 */
	private void pesquisarUnidadeOrganizacional(GerarRelatorioPesquisaSatisfacaoActionForm form, 
			HttpServletRequest httpServletRequest){
		
		//Filtra Unidade Organizacional
		FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
		filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getIdUnidade()));
		
		//Recuper Unidade
		Collection<?> colecaoUnidade = Fachada.getInstancia().pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoUnidade)){
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
		
			form.setIdUnidade(unidadeOrganizacional.getId().toString());
			form.setDescricaoUnidade(unidadeOrganizacional.getDescricao());
			httpServletRequest.removeAttribute("idUnidadeNaoEncontrado");
		}else{
			form.setIdUnidade("");
			form.setDescricaoUnidade("Unidade inexistente");
			httpServletRequest.setAttribute("idUnidadeNaoEncontrado","true");
		}
	}
    
    
}
