package gcom.gui.cadastro;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteVirtual;
import gcom.cadastro.cliente.FiltroClienteVirtual;
import gcom.cadastro.cliente.MotivoRejeicaoClienteVirtual;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
public class InserirMotivoRejeicaoClienteVirtualAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucessoPopup");
		
		InserirMotivoRejeicaoClienteVirtualActionForm form =
				(InserirMotivoRejeicaoClienteVirtualActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idClienteVirtual = form.getIdClienteVirtual();
		
		MotivoRejeicaoClienteVirtual motivoRejeicaoClienteVirtual = new MotivoRejeicaoClienteVirtual();
		motivoRejeicaoClienteVirtual.setId(Integer.parseInt(form.getMotivoRejeicao()));
		
		String observacao = null;
		if(form.getObservacao() != null && !form.getObservacao().equals("")){
			observacao = form.getObservacao();
			
			if(observacao.length() > 400){
				String[] msg = new String[2];
				msg[0]="Observação";
				msg[1]="400";
				
				throw new ActionServletException("atencao.execedeu_limit_observacao", null, msg);
			}
		}
		
		if(idClienteVirtual == null){
			Collection colecaoCliente = (Collection) sessao.getAttribute("imovelClientesNovos");
			ClienteImovel cliente = new ClienteImovel();
			cliente =  (ClienteImovel) Util.retonarObjetoDeColecao(colecaoCliente);
			idClienteVirtual = cliente.getCliente().getId().toString();
		}
		
		FiltroClienteVirtual filtroClienteVirtual = new FiltroClienteVirtual();
		filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.ID, idClienteVirtual));
		
		Collection<?> colClienteVirtual = fachada.pesquisar(filtroClienteVirtual, ClienteVirtual.class.getName());
		
		if(!Util.isVazioOrNulo(colClienteVirtual)){
			ClienteVirtual clienteVirtual = (ClienteVirtual) Util.retonarObjetoDeColecao(colClienteVirtual);
			
			clienteVirtual.setCodigoSituacao(new Short("3"));
			clienteVirtual.setMotivoRejeicao(motivoRejeicaoClienteVirtual);
			clienteVirtual.setObservacao(observacao);
			clienteVirtual.setUltimaAlteracao(new Date());
			
			fachada.atualizar(clienteVirtual);
		}
		
		
		sessao.setAttribute("inserirClienteVirtual", true);
		
		montarPaginaSucesso(httpServletRequest,
			"Cliente Virtual Rejeitado.", "", "");
		
		return retorno;
	}
}
