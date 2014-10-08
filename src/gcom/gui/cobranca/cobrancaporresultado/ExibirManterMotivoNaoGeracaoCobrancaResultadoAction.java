package gcom.gui.cobranca.cobrancaporresultado;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1318] Filtrar Motivo de Não Geração Cobrança por Resultado
 * 
 * @author Hugo Azevedo
 * @date 17/04/2012
 */
public class ExibirManterMotivoNaoGeracaoCobrancaResultadoAction extends GcomAction {

    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	
        //Localiza o action no objeto
        ActionForward retorno = actionMapping.findForward("manterMotivoNaoGeracaoCobrancaResultado");
        
        Fachada fachada = Fachada.getInstancia();
        
        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        sessao.removeAttribute("colecaoMotivosExibicao");
   
        FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm form = 
        		(FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm) sessao.getAttribute("formParametros");
        		
        Collection colecaoMotivos = null;
        Integer totalRegistros = 0;
        
        // 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
        if((Integer)sessao.getAttribute("qtdMotivos") != null)
        	totalRegistros = (Integer)sessao.getAttribute("qtdMotivos");
        
        // 2º Passo - Chamar a função de Paginação passando o total de registros
     	retorno = this.controlarPaginacao(httpServletRequest, retorno,
     			totalRegistros);
        
	     // 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
	     // da pesquisa que está no request
     	colecaoMotivos = fachada.obterColecaoNaoGeracaoCobrancaResultado(
				form.getCodigo(),
    			form.getDescricao(),
    			form.getTipoDescricao(),
    			form.getDescricaoAbreviada().toUpperCase(),
    			form.getIndicadorUso(),
    			form.getTipoMotivo(),
    			((Integer) httpServletRequest
 						.getAttribute("numeroPaginasPesquisa"))
		);
     	
     	if(colecaoMotivos != null && !colecaoMotivos.isEmpty()){
     		sessao.setAttribute("colecaoMotivosExibicao", colecaoMotivos);
     	}
     	else{
     		throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado");
     	}
        
        //retorna o mapeamento contido na variável retorno
     	sessao.setAttribute("totalRegistros", totalRegistros);
        return retorno;
    }
}
