package gcom.gui.cobranca.cobrancaporresultado;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 * 
 * Action responsável por exibir a página de Gerar OS do processo 
 * de movimentar ordem de serviço de cobrança por resultado.
 * 
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class ExibirMovimentarOrdemServicoConsultarOSAction extends GcomAction {
    
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

    	//Seta o mapeamento de retorno para a tela de Gerar OS 
        ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoConsultarOS");

        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Coleções da tela
        //=======================================
        
        //Gerência Regional
        Collection colecaoGerencia = fachada.obterColecaoGerenciaRegional();
        sessao.setAttribute("colecaoGerenciaRegional", colecaoGerencia);
        
        //Unidade de negócio
        if(form.getIdsGerenciaRegional() != null && !form.getIdsGerenciaRegional()[0].equals("-1")){
	        Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocio(form.getIdsGerenciaRegional());
	        sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
    	}
        else{
        	sessao.setAttribute("colecaoUnidadeNegocio", null);
        }
        
        //Localidade
        if((form.getIdsUnidadeNegocio() != null && !form.getIdsUnidadeNegocio()[0].equals("-1")) || 
        		(form.getIdsGerenciaRegional() != null && !form.getIdsGerenciaRegional()[0].equals("-1"))){
	        Collection colecaoLocalidade = fachada.obterColecaoLocalidade(form.getIdsGerenciaRegional(), form.getIdsUnidadeNegocio());
	        sessao.setAttribute("colecaoLocalidade", colecaoLocalidade);
        }
        
        else{
        	sessao.setAttribute("colecaoLocalidade", null);
        }
		
        if (this.getSessao(httpServletRequest).getAttribute("statusWizard") != null) {
	        //Monta o Status do Wizard
	        StatusWizard statusWizard = (StatusWizard) this.getSessao(httpServletRequest).getAttribute("statusWizard");
	        
	        statusWizard.setNomeBotaoConcluir("Consultar OS");
	        
	        //manda o statusWizard para a sessão
	        this.getSessao(httpServletRequest).setAttribute("statusWizard", statusWizard);
		}
        
        if(httpServletRequest.getParameter("limparConsulta")== null || httpServletRequest.getParameter("limparConsulta").equals("")){
        	sessao.setAttribute("colecaoUnidadeNegocio", null);
        	sessao.setAttribute("colecaoLocalidade", null);
        	form.setIdsGerenciaRegional(null);
        	form.setIdsUnidadeNegocio(null);
        	form.setIdsLocalidade(null);
        	form.setSituacaoOS("3");
        	form.setDataEncerramentoInicialOS("");
        	form.setDataEncerramentoFinalOS("");
        	form.setDataGeracaoInicialOS("");
        	form.setDataGeracaoFinalOS("");
        	form.setTipoRelatorio("2");
        	
    	}
		
        
	    //Retorna o mapemaneto na variável "retorno"
	    return retorno;
    }
    
    
    
    
}
