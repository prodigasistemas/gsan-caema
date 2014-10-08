package gcom.cobranca.parcelamentojudicial;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.pagamento.RelatorioEmitirGuiaPagamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ErroRepositorioException;

public class EmitirParcelasParcelamentoJudicialAction extends ExibidorProcessamentoTarefaRelatorio {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		DebitoTipo objetoDebitoTipo = new DebitoTipo();
		String idParcelamentoJudicial = httpServletRequest.getParameter("idParcelamentoJudicial");
		Integer debitoTipo  = Integer.parseInt(httpServletRequest.getParameter("debitoTipo"));
		String tipoParcelaEmissao = httpServletRequest.getParameter("tipoParcelaEmissao");
		String intervaloInicialPrestacao = httpServletRequest.getParameter("intervaloInicialPrestacao");
		String intervaloFinalPrestacao = httpServletRequest.getParameter("intervaloFinalPrestacao");
		List<EmitirGuiasParcelamentoJudicialHelper> idsGuiaPagementoParcelamentoJudicial =  new ArrayList<EmitirGuiasParcelamentoJudicialHelper>();
		String[] ids = null;
		
		
		if(debitoTipo.equals(objetoDebitoTipo.PARCELAMENTO_JUDICIAL_CONTAS)){
			debitoTipo = objetoDebitoTipo.PARCELAMENTO_JUDICIAL_CONTAS;
		}
		
		try {
			idsGuiaPagementoParcelamentoJudicial = fachada.pesquisarEmitirParcelasParcelamentoJudicial(idParcelamentoJudicial, debitoTipo, tipoParcelaEmissao, intervaloInicialPrestacao, intervaloFinalPrestacao);
		} catch (ErroRepositorioException e) {
			System.out.println("-------------------------ERRO------------------------------");
			e.printStackTrace();
		}
			
		
		if(idsGuiaPagementoParcelamentoJudicial.size()>0){
			
			ids = new String[idsGuiaPagementoParcelamentoJudicial.size()];
			
			for(int a=0;a<idsGuiaPagementoParcelamentoJudicial.size();a++){
				
				ids[a]= idsGuiaPagementoParcelamentoJudicial.get(a).getIdGuiaParcelamentoJudicial();
			}
				
			// Parte que vai mandar o relatório para a tela
			// cria uma instância da classe do relatório
			RelatorioEmitirGuiaPagamento relatorioEmitirGuiaPagamento = new RelatorioEmitirGuiaPagamento((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorioEmitirGuiaPagamento.addParametro("ids",ids);
			//String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			//if (tipoRelatorio == null) {
			String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			//}
			
			StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(
					DocumentoTipo.GUIA_PAGAMENTO.toString(),ids[0]);
			String nossoNumero = nossoNumero2.toString();
			
			relatorioEmitirGuiaPagamento.addParametro("nossoNumero", nossoNumero);

			relatorioEmitirGuiaPagamento.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorioEmitirGuiaPagamento,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");

			}

			return retorno;
			
		}else{
			
			if(debitoTipo.equals(objetoDebitoTipo.PARCELAMENTO_JUDICIAL_CONTAS) && tipoParcelaEmissao.equals("2")){
				
				throw new ActionServletException("atencao.nao_existem_parcelas_vencidas_nao_pagas_emissao");
			
			}
			
			if(debitoTipo.equals(objetoDebitoTipo.PARCELAMENTO_JUDICIAL_CONTAS) && tipoParcelaEmissao.equals("3")){
				
				throw new ActionServletException("atencao.nao_existem_parcelas_a_vencer_para_emissao");
			}
			
		}
		
		return retorno;
		
	}

}
