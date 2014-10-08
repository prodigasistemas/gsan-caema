package gcom.gui.faturamento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioPagamentosAbastecimentosCarrosPipa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
/**
 * [UC1565] Gerar RelatÃ³rio Pagamentos Abastecimento Carro Pipa
 * 
 * @author Diogo Luiz
 * @Date 15/10/2013
 * 
 */
public class GerarRelatorioPagamentosAbastecimentosCarroPipaAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();
		
		PagamentosAbastecimentosCarroPipaRelatorioActionForm form = 
				(PagamentosAbastecimentosCarroPipaRelatorioActionForm) actionForm;
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Integer idFaturamentoGrupo = null;
		if(form.getIdFaturamentoGrupo() != null && !form.getIdFaturamentoGrupo().equals("-1")){
			idFaturamentoGrupo = Integer.parseInt(form.getIdFaturamentoGrupo());
		}
		
		if(form.getMesAnoReferencia() == null || form.getMesAnoReferencia().isEmpty()){
			throw new ActionServletException("atencao.referencia_vazia");
		}
		
		Integer mesAnoReferencia = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferencia()));
		
		//[FE0001] Validar Referência  
		if(Util.compararAnoMesReferencia(mesAnoReferencia, sistemaParametro.getAnoMesFaturamento(), ">")){
			throw new ActionServletException("atencao.referencia_invalida");
		}
		
		Integer idGerenciaRegional = null;
		if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")){
			idGerenciaRegional = Integer.parseInt(form.getGerenciaRegional());			
		}
		
		Integer idUnidadeNegocio = null;
		if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("-1")){
			idUnidadeNegocio = Integer.parseInt(form.getUnidadeNegocio());
		}					
		
		RelatorioPagamentosAbastecimentosCarrosPipa relatorio = new RelatorioPagamentosAbastecimentosCarrosPipa(
			(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado")); 	
		
		relatorio.addParametro("idFaturamentoGrupo", idFaturamentoGrupo);
		relatorio.addParametro("mesAnoReferencia", mesAnoReferencia);
		relatorio.addParametro("idGerenciaRegional", idGerenciaRegional);
		relatorio.addParametro("idUnidadeNegocio", idUnidadeNegocio);
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
			httpServletRequest, httpServletResponse, actionMapping);		
		
		return retorno;
	}
}
