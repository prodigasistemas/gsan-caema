package gcom.gui.cadastro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.FiltroMotivoRejeicaoClienteVirtual;
import gcom.cadastro.cliente.MotivoRejeicaoClienteVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ExibirInserirMotivoRejeicaoClienteVirtualAction extends GcomAction {

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
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirMotivoRejeicaoClienteVirtual");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession();
		
		InserirMotivoRejeicaoClienteVirtualActionForm form = 
				(InserirMotivoRejeicaoClienteVirtualActionForm) actionForm;
		
		form.setMotivoRejeicao("-1");
		form.setObservacao("");
		
		if(sessao.getAttribute("idClienteVirtual") != null
			&& !sessao.getAttribute("idClienteVirtual").equals("")){
			
			String idCliente = (String) sessao.getAttribute("idClienteVirtual");
			form.setIdClienteVirtual(idCliente);
		}
		
		this.pesquisarMotivoRejeicao(httpServletRequest, fachada);
		
		return retorno;
	}

	private void pesquisarMotivoRejeicao(HttpServletRequest httpServletRequest, Fachada fachada) {
		FiltroMotivoRejeicaoClienteVirtual filtroMotivo = new FiltroMotivoRejeicaoClienteVirtual();
		filtroMotivo.adicionarParametro(new ParametroSimples(FiltroMotivoRejeicaoClienteVirtual.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoMotivo = fachada.pesquisar(filtroMotivo, MotivoRejeicaoClienteVirtual.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMotivo)){
			httpServletRequest.setAttribute("colecaoMotivos", colecaoMotivo);
		}
	}
}
