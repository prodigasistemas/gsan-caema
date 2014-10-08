package gcom.cobranca.parcelamentojudicial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterParcelamentoJudicialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManterParcelamentoJudicial");
		
		ManterParcelamentoJudicialActionForm manterParcelamentoJudicialActionForm = (ManterParcelamentoJudicialActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigoPesquisaCliente = null;
		String nomePesquisaCliente = null;
		
		String codigoClienteResponsavel = manterParcelamentoJudicialActionForm.getCodigoClienteResponsavel();
		String nomeClienteResposavel = manterParcelamentoJudicialActionForm.getNomeClienteResponsavel();
		
		String codigoClienteUsuario = manterParcelamentoJudicialActionForm.getCodigoClienteUsuario();
		String nomeClienteUsuario = manterParcelamentoJudicialActionForm.getNomeClienteUsuario();
		
		String matriculaImovel = manterParcelamentoJudicialActionForm.getMatriculaImovel();
		String inscricaoImovel = manterParcelamentoJudicialActionForm.getInscricaoImovel();
		
		if (codigoClienteResponsavel != null && !codigoClienteResponsavel.equals("")
				&& (nomeClienteResposavel == null || nomeClienteResposavel.equals(""))) {
			
			
			codigoPesquisaCliente = codigoClienteResponsavel;
			nomePesquisaCliente = nomeClienteResposavel;
		}
		
		if (codigoClienteUsuario != null && !codigoClienteUsuario.equals("")
				&& (nomeClienteUsuario == null || nomeClienteUsuario.equals(""))) {
		
			codigoPesquisaCliente = codigoClienteUsuario;
			nomePesquisaCliente = nomeClienteUsuario;
		
		}
		
		
		if (codigoPesquisaCliente != null && !codigoPesquisaCliente.equals("")
				&& (nomePesquisaCliente == null || nomePesquisaCliente.equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, codigoPesquisaCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Cliente> colecaoCliente = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);
				
				if (codigoClienteResponsavel != null && !codigoClienteResponsavel.equals("")
						&& (nomeClienteResposavel == null || nomeClienteResposavel.equals(""))) {
					
					manterParcelamentoJudicialActionForm.setNomeClienteResponsavel(cliente
						.getNome());
				}
				
				if (codigoClienteUsuario != null && !codigoClienteUsuario.equals("")
						&& (nomeClienteUsuario == null || nomeClienteUsuario.equals(""))) {
				
					manterParcelamentoJudicialActionForm.setNomeClienteUsuario(cliente
						.getNome());
				}
				
			} else {
				
				if (codigoClienteResponsavel != null && !codigoClienteResponsavel.equals("")
						&& (nomeClienteResposavel == null || nomeClienteResposavel.equals(""))) {
				
					manterParcelamentoJudicialActionForm.setNomeClienteResponsavel("");
					manterParcelamentoJudicialActionForm
							.setNomeClienteResponsavel("CLIENTE INEXISTENTE");
					httpServletRequest.setAttribute("codigoClienteResponsavelInexistente", "sim");
					
				}
				
				if (codigoClienteUsuario != null && !codigoClienteUsuario.equals("")
						&& (nomeClienteUsuario == null || nomeClienteUsuario.equals(""))) {
					
					manterParcelamentoJudicialActionForm.setNomeClienteUsuario("");
					manterParcelamentoJudicialActionForm
							.setNomeClienteUsuario("CLIENTE INEXISTENTE");
					httpServletRequest.setAttribute("codigoClienteUsuarioInexistente", "sim");
					
				
				}
				
				
			}
		}
		
		if(matriculaImovel !=null && !matriculaImovel.equals("")
				&& (inscricaoImovel==null || inscricaoImovel.equals(""))){
			
			Imovel imovel = this.getFachada().pesquisarImovel(Integer.parseInt(manterParcelamentoJudicialActionForm.getMatriculaImovel()));
			
		 	if(imovel !=null && !imovel.equals("") ){
		 		
		 		inscricaoImovel = fachada.pesquisarInscricaoImovel(new Integer(matriculaImovel));
		 		
		 		manterParcelamentoJudicialActionForm.setInscricaoImovel(inscricaoImovel);
		 		
		 		
		 		
		 	}else{
		 		
		 		manterParcelamentoJudicialActionForm.setMatriculaImovel("");
		 		manterParcelamentoJudicialActionForm.setInscricaoImovel("MATRÍCULA INEXISTENTE");
		 		httpServletRequest.setAttribute("matriculaInexistente", true);
		 		
		 	}
			
		}
		
		
		String visualizar = httpServletRequest.getParameter("visualizar");
		
		if(visualizar != null && !visualizar.isEmpty()){
		
			//OBTENDO ARQUIVO PARA VISUALIZAÇÃO
			ParcelamentoJudicial parcelamentoJudicial = fachada.obterParcelamentoJudicial(new Integer(visualizar));
			
			//PREPARANDO VISUALIZAÇÃO DO ARQUIVO
			if (parcelamentoJudicial.getArquivoPDF() != null){
				
				OutputStream out = null;
				
				String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;
				
	
				mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
	
				try {
					httpServletResponse.setContentType(mimeType);
					out = httpServletResponse.getOutputStream();
					
					out.write(parcelamentoJudicial.getArquivoPDF());
					out.flush();
					out.close();
					
					httpServletRequest.setAttribute("verificadorPopup", true);
				} 
				catch (IOException e) {
					throw new ActionServletException("erro.sistema", e);
				}
			}else{
				throw new ActionServletException("atencao.nao_existe_arquivo_anexo_parcelamento_judicial");
			}
		}
		
		
		return retorno;
	}
}
