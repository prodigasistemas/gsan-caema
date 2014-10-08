package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cobranca.MotivoNaoGeracaoCobrancaResultado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Date;

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
public class ExibirAtualizarMotivoNaoGeracaoCobrancaResultadoAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto
        ActionForward retorno = actionMapping.findForward("atualizarMotivoNaoGeracaoCobrancaResultado");
        
        //Cria uma instância da sessão
    	HttpSession sessao = httpServletRequest.getSession(false);
        
        AtualizarMotivoNaoGeracaoCobrancaResultadoActionForm form = (AtualizarMotivoNaoGeracaoCobrancaResultadoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
        String idMotivo = httpServletRequest.getParameter("idMotivoAtualizar");
        Integer idMotivoINT = new Integer(0);
        
        
        if(idMotivo != null && !idMotivo.trim().equals("")){
        	idMotivoINT = new Integer(idMotivo);
        }
        
        MotivoNaoGeracaoCobrancaResultado motivo = fachada.obterMotivoNaoGeracaoCobrancaResultado(idMotivoINT);
        
        if(motivo != null){
        	form.setId(motivo.getId().toString());
        	form.setCodigo(motivo.getId().toString());
        	form.setDescricao(motivo.getDescricao());
        	form.setDescricaoAbreviada(motivo.getDescricaoAbreviada());
        	form.setIndicadorUso(motivo.getIndicadorUso().toString());
        	form.setTipoMotivo(motivo.getCodigoTipoMotivo().toString());
        	//sessao.setAttribute("dataHoraInicioProcesso", new Date());
        	form.setDataHoraOperacao(new Date());
        }
        else{
        	throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado");
        }
        
    	
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
