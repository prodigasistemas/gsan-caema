package gcom.gui.cobranca.cobrancaporresultado;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Raimundo Martins
 * */
public class ExibirConsultarNaoGeracaoImoveisContasComandosAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirConsultarNaoGeracaoImoveisContasComandos");
		
		String pagina = httpServletRequest.getParameter("page.offset");
		
		ConsultarNaoGeracaoImoveisContasComandosForm form = (ConsultarNaoGeracaoImoveisContasComandosForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			if(pagina==null)
				pagina = "0";
			retorno = this.pesquisarComandos(httpServletRequest, form, fachada, sessao, pagina, retorno);
		}
		
		if (httpServletRequest.getParameter("limpar") != null && httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim")) 	
			sessao.removeAttribute("colecaoConsultarNaoGeracaoImoveisContasComandosHelper");
		
		this.pesquisarCampoEnter(httpServletRequest, form, fachada);
		
		return retorno;
	}

	private ActionForward pesquisarComandos(HttpServletRequest httpServletRequest,
			ConsultarNaoGeracaoImoveisContasComandosForm form, Fachada fachada, HttpSession sessao, String pagina,
			ActionForward retorno) {
		String idEmpresa = form.getIdEmpresa();
		
		ActionForward retorno2 = new ActionForward();

		
		String periodoExecucaoInicial = form.getDataInicial();

		String periodoExecucaoFinal = form.getDataFinal();

		Date execucaoInicial = null;
		Date execucaoFinal = null;

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			
			if(Util.isInteger(idEmpresa)){
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));

				Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

				if (colecaoEmpresa == null || colecaoEmpresa.isEmpty())
					throw new ActionServletException("atencao.empresa.inexistente");
			}
			else
				throw new ActionServletException("atencao.msg_personalizada","Empresa deve conter apenas números");
			
				
		}

		if((periodoExecucaoInicial == null || periodoExecucaoInicial.equals("")) && 
				(periodoExecucaoFinal !=null && !periodoExecucaoFinal.equals(""))){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Inicial");
			}
			if((periodoExecucaoFinal == null || periodoExecucaoFinal.equals("")) && 
					(periodoExecucaoInicial !=null && !periodoExecucaoInicial.equals(""))){
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Final");
			}
			
			if (periodoExecucaoFinal != null && !periodoExecucaoFinal.equals("")
					&& periodoExecucaoInicial != null && !periodoExecucaoInicial.equals("")) {
									
					Boolean b1 = Util.verificaSeDataValida(periodoExecucaoInicial, "dd/MM/yyyy");
					
					if(b1){
						execucaoInicial = Util.converteStringParaDate(periodoExecucaoInicial);
						
						b1 = Util.verificaSeDataValida(periodoExecucaoFinal, "dd/MM/yyyy");
						if(b1)
							execucaoFinal = Util.converteStringParaDate(periodoExecucaoFinal);						
						else
							throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Final Válido");						
					}
					else
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Inicial Válido");
					

					if (execucaoInicial !=null && execucaoFinal!=null && execucaoInicial.compareTo(execucaoFinal) > 0) 
						throw new ActionServletException("atencao.data_final_periodo.anterior.data_inicial_periodo");
					

				}

		Integer totalRegistros = fachada.pesquisarDadosNaoGeracaoImoveisContasComandosCount(
						new Integer(idEmpresa), execucaoInicial, execucaoFinal);

		retorno2 = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		Collection<ConsultarNaoGeracaoImoveisContasComandosHelper> colecaoConsultarNaoGeracaoImoveisContasComandosHelper = fachada
				.pesquisarDadosNaoGeracaoImoveisContasComandos(new Integer(idEmpresa), execucaoInicial, execucaoFinal,
					(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		
		if(colecaoConsultarNaoGeracaoImoveisContasComandosHelper !=null && !colecaoConsultarNaoGeracaoImoveisContasComandosHelper.isEmpty())
			sessao.setAttribute("colecaoConsultarNaoGeracaoImoveisContasComandosHelper", colecaoConsultarNaoGeracaoImoveisContasComandosHelper);		
		else
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");		

		return retorno2;
	}
	
	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			ConsultarNaoGeracaoImoveisContasComandosForm form,
			Fachada fachada) {
	
		String idEmpresa = form.getIdEmpresa();
	
		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			if(Util.isInteger(idEmpresa)){
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));	
				
				Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
				if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
					Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
					form.setIdEmpresa(empresa.getId().toString());
					form.setDescricaoEmpresa(empresa.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
				} else {
					form.setIdEmpresa("");
					form.setDescricaoEmpresa("EMPRESA INEXISTENTE");
		
					httpServletRequest.setAttribute("empresaInexistente", true);
					httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
				}
			}
			else
				throw new ActionServletException("atencao.msg_personalizada","Empresa deve conter apenas números");
	
		} else {
			form.setDescricaoEmpresa("");
		}
	
	}
	
}
