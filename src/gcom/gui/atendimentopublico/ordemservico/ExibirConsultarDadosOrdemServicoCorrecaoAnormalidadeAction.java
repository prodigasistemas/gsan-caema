package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
 * 
 * SB0001 ? Consultar/Atualizar Dados da Ordem de Servi�o
 * 
 * 1� Aba - "Anormalidade"
 * 
 * @author Mariana Victor
 * @date 23/09/2011
 */
public class ExibirConsultarDadosOrdemServicoCorrecaoAnormalidadeAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //seta o mapeamento de retorno para a p�gina da primeira aba
        ActionForward retorno = actionMapping.findForward("consultarDadosOSCorrecaoAnormalidade");
        
        HttpSession sessao = httpServletRequest.getSession();
        
        String servicoTipo = (String) sessao.getAttribute("servicoTipo");
        
        if(servicoTipo != null){
        	if(servicoTipo.equals("245")){
        		httpServletRequest.setAttribute("tipoOrdemServico", "1");
        	}else if(servicoTipo.equals("308")){
        		httpServletRequest.setAttribute("tipoOrdemServico", "2");
        	}else if(servicoTipo.equals("310")){
        		httpServletRequest.setAttribute("tipoOrdemServico", "3");
        	}else{
        		httpServletRequest.setAttribute("tipoOrdemServico", "4");
        	}
        }
        
		return retorno;
    }
    
}
