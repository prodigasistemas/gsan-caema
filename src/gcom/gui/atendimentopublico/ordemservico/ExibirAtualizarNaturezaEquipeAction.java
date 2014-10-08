package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.EquipeNatureza;
import gcom.atendimentopublico.FiltroEquipeNatureza;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1543] - Manter Natureza Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class ExibirAtualizarNaturezaEquipeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("atualizarNaturezaEquipe");
		
		AtualizarNaturezaEquipeActionForm form = (AtualizarNaturezaEquipeActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		EquipeNatureza equipeNatureza = null;
		
		String idNatureza = null;
		
		if(httpServletRequest.getParameter("idNatureza") != null){
			//tela do Manter
			idNatureza = (String) httpServletRequest.getParameter("idNatureza");
			sessao.setAttribute("idNatureza", idNatureza);			
		}else if(sessao.getAttribute("idNatureza") != null){
			//tela do filtrar
			idNatureza = (String) sessao.getAttribute("idNatureza");
		}
		
		if(idNatureza == null){
			if(httpServletRequest.getParameter("idRegistroAtualizar") == null){
				equipeNatureza = (EquipeNatureza) 
						sessao.getAttribute("equipeNatureza");				
			}else{
				idNatureza = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}
		
		if(equipeNatureza == null){
			
			if(idNatureza != null && !idNatureza.equals("")){
				FiltroEquipeNatureza filtroEquipeNatureza = new FiltroEquipeNatureza();
				filtroEquipeNatureza.adicionarParametro(new ParametroSimples(FiltroEquipeNatureza.ID, idNatureza));
				
				Collection<?> colecaoEquipeNatureza = fachada.pesquisar(filtroEquipeNatureza, EquipeNatureza.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoEquipeNatureza)){
					equipeNatureza = (EquipeNatureza) Util.retonarObjetoDeColecao(colecaoEquipeNatureza);
				}
			}
		}
		
		form.setId(equipeNatureza.getId().toString());
		form.setDescricao(equipeNatureza.getDescricao());
		
		if(equipeNatureza.getDescricaoAbreviada() != null){
			form.setDescricaoAbreviada(equipeNatureza.getDescricaoAbreviada());
		}
		
		form.setIndicadorUso(String.valueOf(equipeNatureza.getIndicadorUso()));
		
		sessao.setAttribute("equipeNatureza", equipeNatureza);
		
		return retorno;
	}
	
}
