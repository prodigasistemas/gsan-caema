package gcom.gui.atendimentopublico.registroatendimento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarNormasProcedimentosAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        ActionForward retorno = actionMapping.findForward("retornoConsultarNormasProcedimentos");
        NormaProcedimentosActionForm normaProcedimentosActionForm = (NormaProcedimentosActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        
        Integer idTipoDocumento = new Integer(normaProcedimentosActionForm.getIdTipoDocumento());
    	String descricaoTitulo = normaProcedimentosActionForm.getDescTitulo().trim();
    	String descricaoAssunto = normaProcedimentosActionForm.getDescAssunto().trim();
    	Short indicadorTextoTitulo = new Short(normaProcedimentosActionForm.getTipoPesquisaTitulo());
    	Short indicadorTextoAssunto = new Short(normaProcedimentosActionForm.getTipoPesquisaAssunto());
       
    	if(  (idTipoDocumento == null || idTipoDocumento.equals(ConstantesSistema.NUMERO_NAO_INFORMADO))
    			  && (descricaoTitulo == null || descricaoTitulo.equals(""))
    			  && (descricaoAssunto == null || descricaoAssunto.equals(""))){
    				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
    	}
    	
    	Integer totalRegistros = fachada.obterNormasProcedimentosCount(idTipoDocumento,
        	indicadorTextoTitulo, indicadorTextoAssunto, descricaoTitulo, descricaoAssunto);
		
		retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
        
    	ArrayList<ConsultarNormasProcedimentosHelper> lista = fachada.obterNormasProcedimentos(idTipoDocumento, 
    		indicadorTextoTitulo, indicadorTextoAssunto, descricaoTitulo,
    		descricaoAssunto, (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
    	
    	if(lista.size()!=0){
    		httpServletRequest.setAttribute("listaConsultarNormasProcedimentosHelper", lista);
    	}else{
    		throw new ActionServletException("atencao.nenhum.resultado.encontrado.consulta.normas.procedimentos");
    	}
    	
       return retorno;
    }
   
    
}