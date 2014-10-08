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
public class FiltrarMotivoNaoGeracaoCobrancaResultadoAction extends GcomAction {
   
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

    	//Seta o mapeamento de retorno para a tela de sucesso
    	ActionForward retorno = actionMapping.findForward("retornarFiltroMotivoNaoGeracaoCobrancaResultado");

    	//Cria uma instância da sessão
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm form = (FiltrarMotivoNaoGeracaoCobrancaResultadoActionForm) actionForm;
    	
    	//[FS0001] - Verificar preenchimento dos campos
    	if((form.getCodigo() == null || form.getCodigo().trim().equals("")) &&
    			(form.getDescricao() == null || form.getDescricao().trim().equals("")) &&
    			(form.getDescricaoAbreviada() == null || form.getDescricaoAbreviada().trim().equals("")) &&
    			(form.getIndicadorUso() == null || form.getIndicadorUso().trim().equals("3")) &&
    			(form.getTipoMotivo() == null || form.getTipoMotivo().trim().equals("4"))
    		){
    		throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
    	}
    		
    	Integer qtd = fachada
    				.obterQtdNaoGeracaoCobrancaResultado(
    						form.getCodigo(),
			    			form.getDescricao(),
			    			form.getTipoDescricao(),
			    			form.getDescricaoAbreviada().toUpperCase(),
			    			form.getIndicadorUso(),
			    			form.getTipoMotivo()
			   
    				);
    	
    	
    	
    	//[FS0002] - Nenhum registro encontrado
    	if(qtd.intValue() == 0){
    		throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado");
    	}
    	
    	sessao.setAttribute("qtdMotivos", qtd);
    	sessao.setAttribute("formParametros", form);
    	
        //Retorna o mapeamento contido na variável "retorno" 
        return retorno;
    }

}
