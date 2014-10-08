package gcom.gui.faturamento.contratodemanda;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ContratoDemandaImovelHelper;
import gcom.faturamento.contratodemanda.ContratoDemandaImovelComercialIndustrial;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaImovelComercialIndustrial;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.ConsultarImoveisContratoDemandaPopupActionForm;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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

/**
 * Consultar os imovéis que pertencem ao contrato de demanda
 * e mostrar no popup
 * 
 * @author Davi Menezes
 * @date 29/04/2013
 *
 */
public class ExibirConsultarImoveisContratoDemandaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("contratoDemandaImoveisPopupConsultar");
		
		ConsultarImoveisContratoDemandaPopupActionForm form = (ConsultarImoveisContratoDemandaPopupActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession();
		
		boolean primeiraVez = true;
		
		String idContratoDemanda = httpServletRequest.getParameter("idContratoDemanda");
		if(idContratoDemanda != null && !idContratoDemanda.equals("")){
			FiltroContratoDemandaImovelComercialIndustrial filtro = new FiltroContratoDemandaImovelComercialIndustrial(
					FiltroContratoDemandaImovelComercialIndustrial.IMOVEL);
			filtro.adicionarParametro(new ParametroSimples(FiltroContratoDemandaImovelComercialIndustrial.
					ID_CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL, idContratoDemanda));
			filtro.adicionarParametro(new ParametroNulo(FiltroContratoDemandaImovelComercialIndustrial.
					DATA_CONTRATO_ENCERRAMENTO));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaImovelComercialIndustrial.IMOVEL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaImovelComercialIndustrial.
					CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL);
			
			FiltroCategoria filtroCategoria = null;
			
			Collection<?> colecao = fachada.pesquisar(filtro, ContratoDemandaImovelComercialIndustrial.class.getName());
			if(!Util.isVazioOrNulo(colecao)){
				Collection<ContratoDemandaImovelHelper> colecaoHelper = new ArrayList<ContratoDemandaImovelHelper>();
				
				ContratoDemandaImovelComercialIndustrial contratoDemandaImovel = null;
				ContratoDemandaImovelHelper helper = null;
				Imovel imovel = null;
				String inscricao = "";
				Integer idCategoria = null;
				
				Collection<?> colecaoCategoria = null;
				Categoria categoria = null;
				Cliente clienteUsuario = null;
				
				Iterator<?> it = colecao.iterator();
				while(it.hasNext()){
					contratoDemandaImovel = (ContratoDemandaImovelComercialIndustrial) it.next();
					imovel = contratoDemandaImovel.getComp_id().getImovel();
					
					inscricao = fachada.pesquisarInscricaoImovel(imovel.getId());
					
					idCategoria = imovel.getCategoriaPrincipalId();
					
					helper = new ContratoDemandaImovelHelper();
					helper.setIdImovel(String.valueOf(imovel.getId()));
					helper.setInscricaoImovel(inscricao);
					helper.setQuantidadeEconomias(String.valueOf(imovel.getQuantidadeEconomias()));
					
					if(primeiraVez){
						if(contratoDemandaImovel.getComp_id().getContratoDemandaComercialIndustrial().getNumeroVolumeAgua() != null){
							form.setVolumeAgua(String.valueOf(contratoDemandaImovel.getComp_id().
									getContratoDemandaComercialIndustrial().getNumeroVolumeAgua()));
						}else{
							form.setVolumeAgua("");
						}
						
						primeiraVez = false;
					}
					
					if(idCategoria != null){
						filtroCategoria = new FiltroCategoria();
						filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, idCategoria));
						
						colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
						if(!Util.isVazioOrNulo(colecaoCategoria)){
							categoria = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoria);
							helper.setDescricaoCategoria(categoria.getDescricao());
						}
					}
					
					clienteUsuario = fachada.pesquisarClienteUsuarioImovel(imovel.getId());
					if(clienteUsuario != null){
						helper.setNomeClienteUsuario(clienteUsuario.getDescricao());
					}
				
					colecaoHelper.add(helper);
				}
			
				sessao.setAttribute("colecaoContratoDemandaImovelHelper", colecaoHelper);
			}
		}
		
		return retorno;
	}
}
