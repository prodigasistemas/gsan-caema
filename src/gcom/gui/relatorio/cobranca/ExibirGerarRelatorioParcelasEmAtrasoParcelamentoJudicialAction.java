package gcom.gui.relatorio.cobranca;

import java.util.Collection;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1465] Gerar Relatório das Parcelas em Atraso do Parcelamento Judicial
 * 
 * @author Mariana Victor
 * @date 22/04/2013
 */
public class ExibirGerarRelatorioParcelasEmAtrasoParcelamentoJudicialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward(
			"exibirGerarRelatorioParcelasEmAtrasoParcJudicial");
		
		GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm form = 
				(GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		// [FE0001] - Validar cliente
		if (form.getIdClienteResponsavel() != null
				&& !form.getIdClienteResponsavel().trim().equals("")) {
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, form.getIdClienteResponsavel()));
			
			Collection<Cliente> colecaoCliente = fachada.pesquisar(
				filtroCliente, Cliente.class.getName());
			
			if (colecaoCliente != null 
					&& !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				
				form.setNomeClienteResponsavel(
					cliente.getNome());
				sessao.removeAttribute("clienteInexistente");
			} else {
				form.setIdClienteResponsavel("");
				form.setNomeClienteResponsavel(
					"CLIENTE INEXISTENTE");
				sessao.setAttribute("clienteInexistente", true);
			}
		}

		// [FE0002] - Verificar existência da matrícula do imóvel
		if (form.getIdImovelPrincipal() != null
				&& !form.getIdImovelPrincipal().trim().equals("")) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, form.getIdImovelPrincipal()));
			
			Collection<Imovel> colecaoImovel = fachada.pesquisar(
				filtroImovel, Imovel.class.getName());
			
			if (colecaoImovel != null 
					&& !colecaoImovel.isEmpty()) {
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
				
				form.setDescricaoImovelPrincipal(
					fachada.pesquisarInscricaoImovel(imovel.getId()));
				sessao.removeAttribute("imovelInexistente");
			} else {
				form.setIdImovelPrincipal("");
				form.setDescricaoImovelPrincipal(
					"MATRÍCULA INEXISTENTE");
				sessao.setAttribute("imovelInexistente", true);
			}
		}
		
		return retorno;
	}
	
}
