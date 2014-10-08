package gcom.cobranca.parcelamentojudicial;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

public class ExibirEmitirParcelasParcelamentoJudicialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirEmitirParcelasParcelamentoJudicial");
		Fachada fachada = Fachada.getInstancia();
		Collection<EmitirGuiasParcelamentoJudicialHelper> colecaoParcelasParcelamentoJudicial = null;
		List<EmitirGuiasParcelamentoJudicialHelper> colecaoVerificarSituacaoContrato = null;
		DebitoTipo objetoDebitoTipo = new DebitoTipo();
		ParcelamentoSituacao objetoParcelamentoSituacao = new ParcelamentoSituacao();
		String idCliente = httpServletRequest.getParameter("idCliente");
		String nomeCliente = httpServletRequest.getParameter("nomeCliente");
		String numeroProcesso = httpServletRequest.getParameter("numeroProcesso");
		String dataParcelamento = httpServletRequest.getParameter("dataParcelamento");
		String idParcelamentoJudicial = httpServletRequest.getParameter("idParcelamentoJudicial");
		String idConcluido = null;
		String idCancelado = null;
		String situacaoVerificacao = null;
		Integer debitoTipo = Integer.parseInt(httpServletRequest.getParameter("debitoTipo"));
		
		
		if(debitoTipo==objetoDebitoTipo.PARCELAMENTO_JUDICIAL_CONTAS){
			
			debitoTipo = objetoDebitoTipo.PARCELAMENTO_JUDICIAL_CONTAS;
			
		}
		
		idConcluido=objetoParcelamentoSituacao.CONCLUIDO.toString();
		idCancelado=objetoParcelamentoSituacao.CANCELADO.toString();
		
		colecaoVerificarSituacaoContrato = fachada.pesquisarContratoConcluidoCanceladoParcelamentoJudicial(idParcelamentoJudicial, idConcluido, idCancelado);
		
		
		if(colecaoVerificarSituacaoContrato.size()>0){
			
			for(int a=0;a<colecaoVerificarSituacaoContrato.size();a++){
				
				situacaoVerificacao = colecaoVerificarSituacaoContrato.get(a).getVerificaContrato();
			}
			
			if(situacaoVerificacao.equals("5")){
				
				throw new ActionServletException("atencao.parcelamento_concluido");
				
			}
			
			if(situacaoVerificacao.equals("4")){
				
				throw new ActionServletException("atencao.parcelamento_cancelado");
			
			}

			
		}
		
		colecaoParcelasParcelamentoJudicial = fachada.pesquisarParcelasParcelamentoJudicial(idParcelamentoJudicial, debitoTipo);
		
		if(colecaoParcelasParcelamentoJudicial.size()>0){
			
			httpServletRequest.setAttribute("idCliente", idCliente);
			httpServletRequest.setAttribute("nomeCliente", nomeCliente);
			httpServletRequest.setAttribute("numeroProcesso", numeroProcesso);
			httpServletRequest.setAttribute("dataParcelamento", dataParcelamento);
			httpServletRequest.setAttribute("idParcelamentoJudicial", idParcelamentoJudicial);
			httpServletRequest.setAttribute("colecaoParcelasParcelamentoJudicial", colecaoParcelasParcelamentoJudicial);
			httpServletRequest.setAttribute("debitoTipo", debitoTipo);
			httpServletRequest.setAttribute("tamanhoColecaoParcelasParcelamentoJudicial", colecaoParcelasParcelamentoJudicial.size());
			
		}else{
			
			throw new ActionServletException("atencao.nao_existem_parcela_emissao");
			
		}
		
		
		return retorno;
		
	}
}
