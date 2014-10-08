package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.EquipeNatureza;
import gcom.atendimentopublico.FiltroEquipeNatureza;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTextoCompleto;

/**
 * [UC 1541] - Inserir Natureza Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class InserirNaturezaEquipeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirNaturezaEquipeActionForm form = (InserirNaturezaEquipeActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String descricao = form.getDescricao();
		if(descricao == null || descricao.equals("")){
			throw new ActionServletException("atencao.campo.informada", null, "Descrição");
		}
		
		FiltroEquipeNatureza filtroEquipeNatureza = new FiltroEquipeNatureza();
		filtroEquipeNatureza.adicionarParametro(new ComparacaoTextoCompleto(FiltroEquipeNatureza.DESCRICAO, descricao));
		
		Collection<?> colecaoEquipeNatureza = fachada.pesquisar(filtroEquipeNatureza, EquipeNatureza.class.getName());
		if(!Util.isVazioOrNulo(colecaoEquipeNatureza)){
			throw new ActionServletException("atencao.descricao_natureza_equipe_ja_existe");
		}
		
		String descricaoAbreviada = null;
		if(form.getDescricaoAbreviada() != null && !form.getDescricaoAbreviada().equals("")){
			descricaoAbreviada = form.getDescricaoAbreviada();
		}
		
		EquipeNatureza naturezaEquipe = new EquipeNatureza();
		
		naturezaEquipe.setDescricao(descricao);
		naturezaEquipe.setDescricaoAbreviada(descricaoAbreviada);
		naturezaEquipe.setIndicadorUso(ConstantesSistema.SIM);
		naturezaEquipe.setUltimaAlteracao(new Date());
		
		Integer idNaturezaEquipe = (Integer) fachada.inserir(naturezaEquipe);
		
		montarPaginaSucesso(httpServletRequest, "Natureza de Equipe Inserida com Sucesso", 
				"Inserir Outra Natureza de Equipe", "exibirInserirNaturezaEquipeAction.do?menu=sim", 
				"exibirAtualizarNaturezaEquipeAction.do?idNatureza=" + idNaturezaEquipe , "Atualizar Natureza de Equipe");
		
		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarNaturezaEquipeAction.do?menu=sim");
		
		return retorno;
	}
	
}
