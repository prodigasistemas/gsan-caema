package gcom.gui.atendimentopublico;

import gcom.cadastro.ImovelSorteadoHelper;
import gcom.cadastro.Sorteios;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1295] Efetuar Sorteio de Prêmios
 * 
 * @author Mariana Victor
 * @since 06/03/2012
 */
public class EfetuarSorteioPremiosAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("efetuarSorteioPremios");
		
		EfetuarSorteioPremiosActionForm form = (EfetuarSorteioPremiosActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
	
		form.setDataAtual(
			Util.formatarDataComHora(new Date()));
		
		Integer idPremio = new Integer (form.getIdPremio());
		Integer idSorteio = new Integer (form.getIdSorteio());
		Integer quantidadePremio = new Integer (form.getQuantidadePremio());
		
		Collection<ImovelSorteadoHelper> colecaoImovelSorteadoHelper = 
				new ArrayList<ImovelSorteadoHelper>();
		
		// 2.1.	Caso o tipo do sorteio seja "Sorteio para Adimplentes":
		if (idSorteio.compareTo(Sorteios.SORTEIO_ADIMPLENTES) == 0) {
			
			colecaoImovelSorteadoHelper = fachada.efetuarSorteioAdimplentes(
				idPremio, quantidadePremio);
			
		// 2.2.	Caso Contrário, caso o tipo do sorteio seja "Sorteio Fique Legal"
		} else if (idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL) == 0) {
			
			colecaoImovelSorteadoHelper = fachada.efetuarSorteioFiqueLegal(
				idPremio, quantidadePremio);
			
		// 2.3.	Caso contrário, ou seja, "Sorteio Mensal":
		} else if (idSorteio.compareTo(Sorteios.SORTEIO_MENSAL) == 0) {
			
			colecaoImovelSorteadoHelper = fachada.efetuarSorteioMensal(
				idPremio, quantidadePremio);
			
		} else if(idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL_2013) == 0){
			/* Autor: Jonathan Marcos
			 * Data: 26/09/2013
			 * [2.2.3.2] Caso o sorteio seja Sorteio Fique Legal 2013
			 */
			colecaoImovelSorteadoHelper = fachada.efetuarSorteioFiqueLegal2013(
				idPremio, quantidadePremio);
		}
		
		sessao.setAttribute("colecaoImovelSorteadoHelper", colecaoImovelSorteadoHelper);
		
		return retorno;
	}

	
}
