package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.AreaAtualizacaoCadastral;
import gcom.cadastro.FiltroAreaAtualizacaoCadastral;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1327] - Suspender Localidade Atualizacao Cadastral
 * 
 * @author Davi Menezes
 * @date 25/04/2012
 *
 */
public class SuspenderLocalidadeAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		SuspenderLocalidadeAtualizacaoCadastralActionForm form = 
				(SuspenderLocalidadeAtualizacaoCadastralActionForm) actionForm;
		
		AreaAtualizacaoCadastral areaAtualizacaoCadastral = new AreaAtualizacaoCadastral();
		
		Fachada fachada = this.getFachada();
		
		if(form.getIdsRegistro() == null || form.getIdsRegistro().length == 0){
			throw new ActionServletException("atencao_ids_localidade_nao_selecionado");
		}
		
		for(int i = 0; i < form.getIdsRegistro().length; i++){
			String id = form.getIdsRegistro()[i];
			
			FiltroAreaAtualizacaoCadastral filtroArea = new FiltroAreaAtualizacaoCadastral();
			filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.ID, id));
			filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.LOCALIDADE);
			filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastral.MUNICIPIO);
			
			Collection<?> colAreaAtualizacaoCadastral = fachada.pesquisar(filtroArea, AreaAtualizacaoCadastral.class.getName());
			
			if(!Util.isVazioOrNulo(colAreaAtualizacaoCadastral)){
				areaAtualizacaoCadastral = (AreaAtualizacaoCadastral) Util.retonarObjetoDeColecao(colAreaAtualizacaoCadastral);
				areaAtualizacaoCadastral.setCodigoSituacao(new Short("2"));
				areaAtualizacaoCadastral.setDataSuspensao(new Date());
				areaAtualizacaoCadastral.setUltimaAlteracao(new Date());
				
				fachada.atualizar(areaAtualizacaoCadastral);
			}
			
			if(areaAtualizacaoCadastral.getLocalidade() != null){
				Municipio municipio = areaAtualizacaoCadastral.getLocalidade().getMunicipio();
				
				if(municipio != null){
					Collection<AreaAtualizacaoCadastral> colecaoAreaAtualizacaoCadastral = 
							getFachada().pesquisarMunicipioAreaAtualizacaoCadastral(municipio.getId());
					
					if(Util.isVazioOrNulo(colecaoAreaAtualizacaoCadastral)){
						municipio.setIndicadorLogradouroBloqueado(new Short("2"));
						
						getFachada().atualizar(municipio);
					}
				}	
			}
		}
		
		String mensagemSucesso = "Localidade(s) suspensa(s) com sucesso.";
		
		montarPaginaSucesso(httpServletRequest, mensagemSucesso,
			"Suspender outra Localidade", "exibirSuspenderLocalidadeAtualizacaoCadastralAction.do?menu=sim");
				
		return retorno;
	}
}