package gcom.gui.cadastro.cliente;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1429] Filtrar Cliente para Validar na Base da Receita Federal
 * 
 * @author Maxwell Moreira
 * @created 14/01/2013
 */

public class ExibirFiltrarClienteReceitaFederalAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarClienteReceitaFederal");
		
		FiltrarClienteReceitaFederalActionForm exibirFiltrarClienteReceitaFederal = (FiltrarClienteReceitaFederalActionForm) actionForm;
		ArrayList<Cliente> colecaoCliente = new ArrayList<Cliente>();
		
		if ((httpServletRequest.getParameter("limpar") != null) && (httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim"))){
			exibirFiltrarClienteReceitaFederal.setIndicadorValidacao("-1");
			exibirFiltrarClienteReceitaFederal.setCpf("");
			exibirFiltrarClienteReceitaFederal.setCnpj("");
			sessao.removeAttribute("colecaoCliente");
		}
		
		if (((httpServletRequest.getParameter("filtrar") != null) && (httpServletRequest.getParameter("filtrar").equalsIgnoreCase("sim")))
				|| (httpServletRequest.getParameter("page.offset") != null)){
			
			if ((exibirFiltrarClienteReceitaFederal.getCpf() != null) && (!exibirFiltrarClienteReceitaFederal.getCpf().equals(""))){
				
				boolean cpfValido = Util.validacaoCPF(exibirFiltrarClienteReceitaFederal.getCpf());
				
				if (cpfValido == false){
					throw new ActionServletException("atencao.cpf_invalido");
				}
			}
		
			if ((exibirFiltrarClienteReceitaFederal.getCnpj() != null) && (!exibirFiltrarClienteReceitaFederal.getCnpj().equals(""))){
				boolean validarCNPJ = Util.validacaoCNPJ(exibirFiltrarClienteReceitaFederal.getCnpj());
				if((!validarCNPJ) || (exibirFiltrarClienteReceitaFederal.getCnpj().equals("00000000000000"))){
					throw new ActionServletException("atencao.cnpj.invalido.sem.argumento");
				}
			}
			
			if  ((exibirFiltrarClienteReceitaFederal.getIndicadorValidacao() != null) && 
					(exibirFiltrarClienteReceitaFederal.getIndicadorValidacao().equals("2"))){
				
				String documentoValidacao = "";
			    String documentoFiltro = "";
				
				if ((exibirFiltrarClienteReceitaFederal.getCpf() != null) && (!exibirFiltrarClienteReceitaFederal.getCpf().equals(""))){
					documentoValidacao = exibirFiltrarClienteReceitaFederal.getCpf();
					documentoFiltro = FiltroCliente.CPF;
					
				} else if ((exibirFiltrarClienteReceitaFederal.getCnpj() != null) && (!exibirFiltrarClienteReceitaFederal.getCnpj().equals(""))){
					documentoValidacao = exibirFiltrarClienteReceitaFederal.getCnpj();
					documentoFiltro = FiltroCliente.CNPJ;
				}
				
				FiltroCliente filtro = new FiltroCliente();
				filtro.adicionarParametro(new ParametroSimples(documentoFiltro, documentoValidacao));
				filtro.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.SIM));
				
				Integer totalRegistros = 1;
				retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
				
				colecaoCliente = (ArrayList<Cliente>) getFachada().pesquisar(filtro, Cliente.class.getName());
				
			} else if ((exibirFiltrarClienteReceitaFederal.getIndicadorValidacao() != null) 
					&& (exibirFiltrarClienteReceitaFederal.getIndicadorValidacao().equals("1"))){
				
				if((httpServletRequest.getParameter("pesquisar") != null && httpServletRequest.getParameter("pesquisar").equals("sim"))
						|| httpServletRequest.getParameter("page.offset") != null || httpServletRequest.getParameter("tipoConsulta") != null){
					
					Integer totalRegistros = 20;
					retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
					//(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")
					colecaoCliente = (ArrayList<Cliente>) getFachada().pesquisarClientesValidacao(
							Integer.valueOf(httpServletRequest.getParameter("page.offset")));
				    
				} else {
					
					Integer totalRegistros;
					Integer maiorPag = 0;
					
					if (colecaoCliente.size() == 1){
						totalRegistros = 1;
					} else {
						totalRegistros = 20;
						
						Integer totalClientes = getFachada().pesquisarQuantidadeClientesValidacao();
						if (totalClientes > 20){
							Random gerador = new Random();
							 
					        maiorPag = gerador.nextInt(totalClientes/20);
						}
						
					}
					 
					retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
	                colecaoCliente = (ArrayList<Cliente>) getFachada().pesquisarClientesValidacao(maiorPag);
				}
			}
			
			sessao.setAttribute("colecaoCliente", colecaoCliente);
			
		}
		
		return retorno;

	}
}