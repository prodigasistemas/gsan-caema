package gcom.gui.portal;

import java.util.Collection;

import gcom.cadastro.FiltroImovelCadastroSorteio;
import gcom.cadastro.ImovelCadastroSorteio;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir o Questionario de Satisfacao do Cliente
 * 
 * @author Paulo Diniz
 * @date 23/06/2011
 */
public class ExibirEmitirComprovanteCadastroSorteioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirEmitirComprovanteCadastroSorteio");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		CadastrarImovelSorteioActionForm cadastrarImovelSorteioActionForm = 
				(CadastrarImovelSorteioActionForm) actionForm;
		
		if(usuarioLogado == null 
				|| usuarioLogado.getId() == null){
			sessao.removeAttribute("idUsuarioLogado");
		}else{
			sessao.setAttribute("idUsuarioLogado", usuarioLogado.getId());
		}
		
		if (httpServletRequest.getParameter("limparImovel") != null
				&& httpServletRequest.getParameter("limparImovel").toString().trim().equalsIgnoreCase("sim")) {
			
			cadastrarImovelSorteioActionForm.setMatricula(null);
			cadastrarImovelSorteioActionForm.setInscricaoImovel(null);
			cadastrarImovelSorteioActionForm.setInformacaoImovelInexistente(null);
			cadastrarImovelSorteioActionForm.setNome(null);
			
		} else if (cadastrarImovelSorteioActionForm.getMatricula() != null
				&& !cadastrarImovelSorteioActionForm.getMatricula().trim().equals("")
				&& Util.isInteger(cadastrarImovelSorteioActionForm.getMatricula().trim())) {
			
			Integer idImovel = new Integer(cadastrarImovelSorteioActionForm.getMatricula().trim());
			
			String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(idImovel);
			
			if (inscricaoImovel != null
					&& !inscricaoImovel.trim().equals("")) {
				cadastrarImovelSorteioActionForm.setMatricula(
					idImovel.toString());
				cadastrarImovelSorteioActionForm.setInscricaoImovel(
					inscricaoImovel);
				cadastrarImovelSorteioActionForm.setInformacaoImovelInexistente(
						null);
				
				FiltroImovelCadastroSorteio filtroImovelCadastroSorteio = new FiltroImovelCadastroSorteio();
				filtroImovelCadastroSorteio.adicionarParametro(new ParametroSimples(
					FiltroImovelCadastroSorteio.IMOVEL_ID, idImovel));
				filtroImovelCadastroSorteio.adicionarParametro(new ParametroSimples(
					FiltroImovelCadastroSorteio.INDICADOR_IMOVEL_APTO, ConstantesSistema.SIM));
				
				/*
				 * Autor: Jonathan Marcos
				 * Data:18/09/2013
				 * [IT0006] Obter Nome do Cliente cadastrado
				 * Analista : Ana Maria
				 * [Observacao] colocar indicador(IMCS_ICPARTICIPACAOSORTEIO) no filtroImovelCadastroSorteio 
				 * ------------------------- INICIO--------------------------------------------------------
				 */
				filtroImovelCadastroSorteio.adicionarParametro(new ParametroSimples(
					FiltroImovelCadastroSorteio.INDICADOR_IMOVEL_PARTICIPACAO_SORTEIO, ConstantesSistema.SIM));
				//------------------------- FIM -----------------------------------------------------------
				
				Collection<ImovelCadastroSorteio> colecaoImovelCadastroSorteio = 
						this.getFachada().pesquisar(filtroImovelCadastroSorteio, ImovelCadastroSorteio.class.getName());
				
				if (colecaoImovelCadastroSorteio != null 
						&& !colecaoImovelCadastroSorteio.isEmpty()) {
					ImovelCadastroSorteio imovelCadastroSorteio = (ImovelCadastroSorteio) 
							Util.retonarObjetoDeColecao(colecaoImovelCadastroSorteio);
					
					cadastrarImovelSorteioActionForm.setNome(
						imovelCadastroSorteio.getNomeCliente());
					
				} else {
					cadastrarImovelSorteioActionForm.setNome(
						null);
				}
				
				sessao.removeAttribute("imovelInexistente");
				
			} else {
				cadastrarImovelSorteioActionForm.setMatricula(
						null);
				cadastrarImovelSorteioActionForm.setInscricaoImovel(
						null);
				cadastrarImovelSorteioActionForm.setNome(
						null);
				cadastrarImovelSorteioActionForm.setInformacaoImovelInexistente(
						"MATRÍCULA INEXISTENTE");
				sessao.setAttribute("imovelInexistente", true);
			}
			
		}
				
		sessao.setAttribute("operacao", "exibirQuestionario");

		return retorno;
	}
	
}