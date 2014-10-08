package gcom.gui.micromedicao;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.MovimentoRoteiroEmpresaFoto;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de exibir consultar Foto Anormalidade
 * 
 * @author Carlos Chaves
 * @created 24/10/2012
 */
public class ExibirConsultarFotoConsumoAnormalidadePopupAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("consultarFotoConsumoAnormalidadePopup");
		Fachada fachada = Fachada.getInstancia();				
		
		Integer idImovel = (new Integer(httpServletRequest.getParameter("id")));
		String anoMesReferencia = httpServletRequest.getParameter("anoMes");
		Integer idMedicaoTipo = (new Integer(httpServletRequest.getParameter("medt")));
		Integer idConsumoAnormalidade = (new Integer(httpServletRequest.getParameter("csan")));
		Integer anoMesReferenciaFormatado = 0;
		
		if( anoMesReferencia.indexOf('/') > -1 ) {
			anoMesReferenciaFormatado = Util.formatarMesAnoComBarraParaAnoMes(anoMesReferencia);
		}else{
			anoMesReferenciaFormatado = Integer.parseInt(anoMesReferencia);
		}
		
		Collection <MovimentoRoteiroEmpresaFoto> fotos = 
				fachada.pesquisarFotosAnormalidadeConsumo (idImovel, anoMesReferenciaFormatado, idMedicaoTipo, idConsumoAnormalidade);
		
		if(!Util.isVazioOrNulo(fotos)){
			httpServletRequest.setAttribute("fotos", fotos);
			httpServletRequest.setAttribute("idImovel", idImovel);
			httpServletRequest.setAttribute("anoMes", anoMesReferencia);
		}else{
			throw new ActionServletException("atencao.msg_personalizada", "Não existe foto registrada para anormalidade de consumo.");
		}
		
		return retorno;
	}
}