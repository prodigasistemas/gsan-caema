package gcom.gui.atendimentopublico;

import java.util.Collection;
import java.util.Date;

import gcom.cadastro.FiltroSorteios;
import gcom.cadastro.PremioSorteio;
import gcom.cadastro.Sorteios;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
public class ExibirEfetuarSorteioPremiosAction extends GcomAction {

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
				.findForward("exibirEfetuarSorteioPremios");

		HttpSession sessao = httpServletRequest.getSession(false);
		 
		EfetuarSorteioPremiosActionForm form = (EfetuarSorteioPremiosActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
	
		form.setDataAtual(
			Util.formatarDataComHora(new Date()));
		
		if (form.getIdSorteio() == null
				|| form.getIdSorteio().equals("")
				|| form.getIdSorteio().equals("-1")) {

			sessao.setAttribute("selecionarSorteio", true);
			sessao.removeAttribute("sorteioFinalizado");
			sessao.removeAttribute("efetuarSorteio");
			
			FiltroSorteios filtroSorteios = new FiltroSorteios();
			filtroSorteios.setCampoOrderBy(FiltroSorteios.DESCRICAO);
			Collection<Sorteios> colecaoSorteios = fachada.pesquisar(
				filtroSorteios, Sorteios.class.getName());
			
			if (colecaoSorteios != null
					&& !colecaoSorteios.isEmpty()) {
				sessao.setAttribute("colecaoSorteios", colecaoSorteios);
			} else {
				sessao.removeAttribute("colecaoSorteios");
			}

			form.setIdSorteio(Sorteios.SORTEIO_FIQUE_LEGAL.toString());
			
		} else  {
			FiltroSorteios filtroSorteios = new FiltroSorteios();
			filtroSorteios.adicionarParametro(new ParametroSimples(
				FiltroSorteios.ID, form.getIdSorteio()));
			Collection<Sorteios> colecaoSorteios = fachada.pesquisar(
				filtroSorteios, Sorteios.class.getName());
			
			if (colecaoSorteios != null
					&& !colecaoSorteios.isEmpty()) {
				Sorteios sorteio = (Sorteios) Util.retonarObjetoDeColecao(colecaoSorteios);
				form.setDescricaoSorteio(sorteio.getDescricao());
			}
			
			Object[] dadosPremio = fachada.pesquisarProximoPremio(new Integer(form.getIdSorteio()));
			
			if (dadosPremio != null){
				sessao.removeAttribute("selecionarSorteio");
				sessao.setAttribute("efetuarSorteio", true);
				sessao.removeAttribute("sorteioFinalizado");
				sessao.removeAttribute("colecaoImovelSorteadoHelper");
				
				if (dadosPremio[0] != null) {
					Integer idPremio = (Integer) dadosPremio[0];
					form.setIdPremio(idPremio.toString());
				}
				
				if (dadosPremio[1] != null) {
					String descricaoPremio = (String) dadosPremio[1];
					form.setDescricaoPremio(descricaoPremio);
				}
				
				if (dadosPremio[2] != null) {
					Integer quantidadePremio = (Integer) dadosPremio[2];
					form.setQuantidadePremio(quantidadePremio.toString());
				}
				
				if (dadosPremio[3] != null) {
					Integer ordemPremio = (Integer) dadosPremio[3];
					form.setOrdemPremio(ordemPremio.toString());
				}
				
			} else {
				Date dataSorteio = null;
				Integer idSorteio = new Integer(form.getIdSorteio());
				
				// 2.1.	Caso o tipo do sorteio seja "Sorteio para Adimplentes":
				if (idSorteio.compareTo(Sorteios.SORTEIO_ADIMPLENTES) == 0) {
					
					dataSorteio = fachada.pesquisarDataSorteio();
					
				// 2.2.	Caso Contrário, caso o tipo do sorteio seja "Sorteio Fique Legal"
				} else if (idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL) == 0) {

					dataSorteio = fachada.pesquisarDataSorteioFiqueLegal(); 
					
				// 2.3.	Caso contrário, ou seja, "Sorteio Mensal":
				} else if (idSorteio.compareTo(Sorteios.SORTEIO_MENSAL) == 0) {

					dataSorteio = fachada.pesquisarDataSorteioMensal();
					
				} else if(idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL_2013) == 0){
					Collection<PremioSorteio> colecaoPremioSorteio = fachada.
							obterNumeroSorteioFuqueLegal2013(idSorteio);
					dataSorteio = fachada.obterDataSorteioFiqueLegal2013();
					
					httpServletRequest.setAttribute("colecaoNumeroPremioSorteio", colecaoPremioSorteio);
				}
				form.setDataSorteio(Util.formatarData(dataSorteio));
	
				sessao.removeAttribute("selecionarSorteio");
				sessao.removeAttribute("efetuarSorteio");
				sessao.setAttribute("sorteioFinalizado", true);
			}
		}
		
		return retorno;
	}

}
