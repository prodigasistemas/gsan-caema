package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.EquipeNatureza;
import gcom.atendimentopublico.FiltroEquipeNatureza;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1543] - Manter Natureza de Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class AtualizarNaturezaEquipeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarNaturezaEquipeActionForm form = (AtualizarNaturezaEquipeActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		EquipeNatureza equipeNatureza = null;
		
		String descricao = form.getDescricao();
		if(descricao == null || form.getDescricao().trim().equals("")){
			throw new ActionServletException("atencao.campo.informada", null, "Descrição");
		}
		
		FiltroEquipeNatureza filtroEquipeNatureza = new FiltroEquipeNatureza();
		filtroEquipeNatureza.adicionarParametro(new ComparacaoTextoCompleto(FiltroEquipeNatureza.DESCRICAO, descricao));
		filtroEquipeNatureza.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroEquipeNatureza.ID, form.getId()));
		
		Collection<?> colecaoEquipeNatureza = fachada.pesquisar(filtroEquipeNatureza, EquipeNatureza.class.getName());
		if(!Util.isVazioOrNulo(colecaoEquipeNatureza)){
			throw new ActionServletException("atencao.descricao_natureza_equipe_ja_existe");
		}
		
		String descricaoAbreviada = null;
		if(form.getDescricaoAbreviada() != null && !form.getDescricaoAbreviada().equals("")){
			descricaoAbreviada = form.getDescricaoAbreviada();
		}
		
		filtroEquipeNatureza = new FiltroEquipeNatureza();
		filtroEquipeNatureza.adicionarParametro(new ParametroSimples(FiltroEquipeNatureza.ID, form.getId()));
		
		colecaoEquipeNatureza = fachada.pesquisar(filtroEquipeNatureza, EquipeNatureza.class.getName());
		if(!Util.isVazioOrNulo(colecaoEquipeNatureza)){
			equipeNatureza = (EquipeNatureza) Util.retonarObjetoDeColecao(colecaoEquipeNatureza);
			equipeNatureza.setDescricao(descricao);
			equipeNatureza.setDescricaoAbreviada(descricaoAbreviada);
			equipeNatureza.setIndicadorUso(Short.parseShort(form.getIndicadorUso()));
		}else{
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}
		
		filtroEquipeNatureza = new FiltroEquipeNatureza();
		filtroEquipeNatureza.adicionarParametro(new ParametroSimples(FiltroEquipeNatureza.ID, form.getId()));
		
		Collection<?> colecaoEquipeNaturezaNaBase = fachada.pesquisar(filtroEquipeNatureza, EquipeNatureza.class.getName());
		if(!Util.isVazioOrNulo(colecaoEquipeNaturezaNaBase)){
			EquipeNatureza equipeNaturezaNaBase = (EquipeNatureza) Util.retonarObjetoDeColecao(colecaoEquipeNaturezaNaBase);
			
			if(equipeNaturezaNaBase.getUltimaAlteracao().after(equipeNatureza.getUltimaAlteracao())){
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}
		}
		
		equipeNatureza.setUltimaAlteracao(new Date());
		
		fachada.atualizar(equipeNatureza);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Natureza de Equipe "+ descricao +" atualizada com sucesso.", 
				"Realizar outra Manutenção de Natureza de Equipe","exibirFiltrarNaturezaEquipeAction.do?menu=sim");
		
		return retorno;
	}

}
