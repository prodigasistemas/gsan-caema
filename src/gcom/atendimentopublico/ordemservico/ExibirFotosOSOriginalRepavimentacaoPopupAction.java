package gcom.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de exibir consultar Foto OS Popup
 * 
 * @author Fernanda
 * @created 16/04/2012
 */
public class ExibirFotosOSOriginalRepavimentacaoPopupAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultarFotoRepavimentacaoPopup");
		
		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idOs = httpServletRequest.getParameter("idOs");
		
		
		if(idOs != null && !idOs.equals("") ){			

			OrdemServico os = fachada.pesquisarOS(new Integer(idOs));
			Integer idRA = os.getRegistroAtendimento().getId();
			
			Collection<OrdemServicoFoto> fotos = fachada.pesquisarOrdemServicoFotoRepavimentacao(new Integer(idOs), new Integer(idRA));
			
			if(fotos !=null && !fotos.isEmpty()){
				httpServletRequest.setAttribute("fotos", fotos);
			}else{
				throw new ActionServletException("atencao.msg_personalizada", "Não existe foto anexada para Registro Atendimento "+idRA);
			}
			
			Collection<OrdemServico> colOrdemServico = new ArrayList<OrdemServico>();
			OrdemServicoFoto fotoAnterior = null;
			
			for(OrdemServicoFoto foto: fotos){
				if(fotoAnterior == null || fotoAnterior!= null && !foto.getOrdemServico().getId().equals(fotoAnterior.getOrdemServico().getId())){
					fotoAnterior = foto;
					colOrdemServico.add(foto.getOrdemServico());
				}
			}
				
			httpServletRequest.setAttribute("colOrdemServico", colOrdemServico);
		
			
		
		}
		
		
		return retorno;
	}
}
