package gcom.gui.portal;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Exibir referente ao consultar histórico água
 * 
 * @author Nathalia Santos
 * @date 22/03/2012
 */
public class ExibirConsultarConsumoHistoricoAguaPortalCompesaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		
		httpServletRequest.setAttribute("voltarServicos", true);

		ConsultarConsumoHistoricoAguaPortalCompesaActionForm consultarConsumoHistoricoAguaPortalCompesaActionForm = (ConsultarConsumoHistoricoAguaPortalCompesaActionForm) actionForm;
		
		Integer matricula = (Integer) sessao.getAttribute("matricula");
		
		Collection<ImovelMicromedicao> imoveisMicromedicao = 
			Fachada.getInstancia().carregarDadosConsumo(
					matricula,true);
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CONSULTAR_CONSUMO_HISTORICO, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO); 

		if ( !Util.isVazioOrNulo(imoveisMicromedicao)) {				
			Collections.sort((List<ImovelMicromedicao>) imoveisMicromedicao, new ComparatorImovelMicromedicao());

			
			httpServletRequest.setAttribute("imoveisMicromedicao",imoveisMicromedicao);
		}else {
			httpServletRequest.removeAttribute("imoveisMicromedicao");
			
		}
		
			return actionMapping.findForward("exibirConsultarConsumoHistoricoAguaPortalCompesaAction");
	}
	
	class ComparatorImovelMicromedicao implements Comparator<ImovelMicromedicao>{
		public int compare(ImovelMicromedicao a, ImovelMicromedicao b) {
			
			int retorno = 0;
			Integer anoMesReferencia1 = a.getConsumoHistorico().getReferenciaFaturamento();
			Integer anoMesReferencia2 = b.getConsumoHistorico().getReferenciaFaturamento();

			if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
				retorno = -1;
			}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
				retorno = 1;
			}			
			return retorno;
		}
	}
}
