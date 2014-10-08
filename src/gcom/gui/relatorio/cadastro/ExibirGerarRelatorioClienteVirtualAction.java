package gcom.gui.relatorio.cadastro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.imovel.Imovel;
import gcom.gui.GcomAction;
import gcom.gui.cadastro.cliente.FiltrarClienteVirtualActionForm;

/**
 * [UC 1304] Gerar Relatório Clientes Cadastrados no Ambiente Virtual
 * 
 * @author Davi Menezes
 * @date 04/04/2012
 *
 */
public class ExibirGerarRelatorioClienteVirtualAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioClienteVirtualAction");
		FiltrarClienteVirtualActionForm form = (FiltrarClienteVirtualActionForm) actionForm;
		
		if ( httpServletRequest.getParameter("limparFormulario") != null && httpServletRequest.getParameter("limparFormulario").equals("sim") ) {
			form.setPeriodoAtendimentoInicial("");
			form.setPeriodoAtendimentoFinal("");
			form.setMatriculaImovel("");
			form.setDescricaoImovel("");
			form.setSituacaoCliente("");
			form.setTempoCadastro("2");
		}
		
		if ( httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim") ) {
			form.setTempoCadastro("2");
		}
		
		if(httpServletRequest.getParameter("pesquisarImovel") != null && httpServletRequest.getParameter("pesquisarImovel").equals("sim")){
			this.pesquisarImovel(httpServletRequest, form);
			return retorno;
		}
	
		return retorno;
	}
	
	private void pesquisarImovel(HttpServletRequest httpServletRequest, FiltrarClienteVirtualActionForm form) {
		if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			Imovel imovel = this.getFachada().pesquisarImovel(Integer.parseInt(form.getMatriculaImovel()));
			if(imovel != null){
				form.setMatriculaImovel(String.valueOf(imovel.getId()));
				form.setDescricaoImovel(imovel.getInscricaoFormatada());
				httpServletRequest.removeAttribute("matriculaInexistente");
			}else{
				form.setMatriculaImovel("");
				form.setDescricaoImovel("Matricula Inexistente");
				httpServletRequest.setAttribute("matriculaInexistente", true);
			}
		}
	}
		
}
