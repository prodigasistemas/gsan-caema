package gcom.gui.relatorio.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.ResumoAnoMesRetornoFiscalizacaoHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioRetornoOSFiscalizacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
				
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioRetornoOSFiscalizacao");
 
		HttpSession sessao = getSessao(httpServletRequest);

		Collection situacoes = Fachada.getInstancia()
				.pesquisar(new FiltroFiscalizacaoSituacao(FiltroFiscalizacaoSituacao.DESCRICAO), FiscalizacaoSituacao.class.getName());
		
		if(situacoes !=null && !situacoes.isEmpty())
			httpServletRequest.getSession(false).setAttribute("situacoes", situacoes);
		
		Collection<ResumoAnoMesRetornoFiscalizacaoHelper> colecaoResumoCobrancaAcaoSituacaoAcaoAnoMesDetalhes = (Collection)sessao.getAttribute("colecaoResumoCobrancaAcaoSituacaoAcaoAnoMesDetalhes");
		
		Collection<String> colecaoMesAno = new ArrayList<String>();
		
		if (colecaoResumoCobrancaAcaoSituacaoAcaoAnoMesDetalhes != null && !colecaoResumoCobrancaAcaoSituacaoAcaoAnoMesDetalhes.isEmpty()) {
	       Iterator anoMesResumoRetornoFiscalizacaoIterator = colecaoResumoCobrancaAcaoSituacaoAcaoAnoMesDetalhes.iterator();
		   while (anoMesResumoRetornoFiscalizacaoIterator.hasNext()) {
			   ResumoAnoMesRetornoFiscalizacaoHelper resumoAnoMesRetornoFiscalizacaoHelper = (ResumoAnoMesRetornoFiscalizacaoHelper)anoMesResumoRetornoFiscalizacaoIterator.next();
			   String mesAno =resumoAnoMesRetornoFiscalizacaoHelper.getAnoMesFormatado();
			   if(!mesAno.equals("TODOS")){
				   colecaoMesAno.add(mesAno);
			   }
		  }
		}
		  
		if(colecaoMesAno !=null && !colecaoMesAno.isEmpty()){
			sessao.setAttribute("colecaoMesAno", colecaoMesAno);    
		}
		
		return retorno;
	}
}
