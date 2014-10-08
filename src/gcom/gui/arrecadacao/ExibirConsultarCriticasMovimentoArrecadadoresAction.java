package gcom.gui.arrecadacao;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ExibirConsultarCriticasMovimentoArrecadadoresAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarCriticasMovimentoArrecadadoresAction");
		ConsultarCriticasMovimentoArrecadadoresActionForm form = (ConsultarCriticasMovimentoArrecadadoresActionForm) actionForm;
		String pesquisaArrecadador = httpServletRequest.getParameter("pesquisaArrecadador");
		
		if(pesquisaArrecadador !=null && !pesquisaArrecadador.trim().equals("") && pesquisaArrecadador.equalsIgnoreCase("true"))
			pesquisarArrecadador(form.getCodigoArrecadador(), form, httpServletRequest);
		
		
		return retorno;
	}
	
	private void pesquisarArrecadador(String codigoArrecadador, ConsultarCriticasMovimentoArrecadadoresActionForm form, 
			HttpServletRequest httpServletRequest){
		if (codigoArrecadador != null && !codigoArrecadador.trim().equals("") && Integer.parseInt(codigoArrecadador) > 0) {
			if(Util.isInteger(codigoArrecadador)){
				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
				filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, codigoArrecadador));
				filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
	
				Collection<Arrecadador> arrecadadores = this.getFachada().pesquisar(filtroArrecadador, Arrecadador.class.getName());
	
				if (arrecadadores != null && !arrecadadores.isEmpty()) {
					
					Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(arrecadadores);
					form.setCodigoArrecadador(arrecadador.getCodigoAgente().toString());
					form.setNomeArrecadador(arrecadador.getCliente().getNome());				
				}
				else{
					httpServletRequest.setAttribute("arrecadadorNaoEncontrado", true);
					form.setCodigoArrecadador("");
					form.setNomeArrecadador("ARRECADADOR INEXISTENTE");
				}
			}
			else
				throw new ActionServletException("atencao.msg_personalizada","Arrecadador deve conter apenas números");
		}
	}
}
