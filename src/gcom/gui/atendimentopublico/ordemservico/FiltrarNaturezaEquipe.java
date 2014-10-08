package gcom.gui.atendimentopublico.ordemservico;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.FiltroEquipeNatureza;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1542] - Filtrar Natureza de Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class FiltrarNaturezaEquipe extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManterNaturezaEquipe");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarNaturezaEquipeActionForm form = (FiltrarNaturezaEquipeActionForm) actionForm;
		
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();
		
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");		
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		boolean peloMenosUmParametroInformado = false;
		
		FiltroEquipeNatureza filtroNaturezaEquipe = new FiltroEquipeNatureza(FiltroEquipeNatureza.DESCRICAO);
		
		//Descrição da Natureza de Equipe
		if(descricao != null && !descricao.trim().equals("")){
			peloMenosUmParametroInformado = true;
			
			if(tipoPesquisa != null && !tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){
				filtroNaturezaEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipeNatureza.DESCRICAO, descricao));
			}else{
				filtroNaturezaEquipe.adicionarParametro(new ComparacaoTextoCompleto(FiltroEquipeNatureza.DESCRICAO, descricao));
			}
		}
		
		//Descrição Abreviada da Natureza de Equipe
		if(descricaoAbreviada != null && !descricaoAbreviada.trim().equals("")){
			peloMenosUmParametroInformado = true;
			
			filtroNaturezaEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipeNatureza.DESCRICAO_ABREVIADA, descricaoAbreviada));
		}
		
		//Indicador de Uso
		if(indicadorUso != null && !indicadorUso.trim().equals("") && !indicadorUso.equals(""+ConstantesSistema.TODOS)){
			peloMenosUmParametroInformado = true;
			
			filtroNaturezaEquipe.adicionarParametro(new ParametroSimples(FiltroEquipeNatureza.INDICADOR_USO, indicadorUso));
		}
		
		//Erro caso o usuário mandar filtrar sem parametros
		if(!peloMenosUmParametroInformado){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		sessao.setAttribute("filtroNaturezaEquipe", filtroNaturezaEquipe);
		
		return retorno;
	}

}
