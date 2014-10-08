package gcom.gui.portal;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável pela tarifa social do portal 
 * 
 * @author Nathalia Santos 
 * @date 26/03/2012
 */
public class ExibirInformacoesTarifaSocialPortalCompesaAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirInformacoesTarifaSocialPortalCompesaAction");
		
		ExibirInformacoesPortalCompesaActionForm form = (ExibirInformacoesPortalCompesaActionForm) actionForm;
		
		Collection<ConsultarEstruturaTarifariaPortalHelper> colecaoResidencial = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		
		HttpSession sessao = request.getSession(false);
		request.setAttribute("voltarInformacoes", true);
		
		String ip = request.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.TARIFA_SOCIAL, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		
		//Consumos Não medidos
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> consumosNaoMedidos = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		ConsultarEstruturaTarifariaPortalHelper consumoNaoMedido = null;
		
		//Consumos Medidos Residencial
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperResidencial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.RESIDENCIAL);
		if(!Util.isVazioOrNulo(helperResidencial)){
			request.setAttribute("helperResidencial", helperResidencial);
			
			//------------------------- Carregando os consumos não medidos (Residencial) -------------------------//
			//Recupera os dados referentes ao consumo da tarifa social
			consumoNaoMedido = helperResidencial.get(0); 
			if(consumoNaoMedido != null){
				//String valor = Util.formataBigDecimal(numero, casas, agruparMilhares)
				BigDecimal valor = Util.formatarStringParaBigDecimal(consumoNaoMedido.getValor().replace(',', '.'));
				String valorExtenso = Util.valorExtenso(valor).toLowerCase();
				String valorMontado = consumoNaoMedido.getValor() + "("+valorExtenso+")";
				
				form.setValorResidencial(valorMontado);
			}
			
			//Recupera os dados referentes ao consumo da tarifa mínima
			consumoNaoMedido = helperResidencial.get(1);
			if(consumoNaoMedido != null){
				
				//String valor = Util.formataBigDecimal(numero, casas, agruparMilhares)
				BigDecimal valor = Util.formatarStringParaBigDecimal(consumoNaoMedido.getValor().replace(',', '.'));
				String valorExtenso = Util.valorExtenso(valor).toLowerCase();
				String valorMontado = consumoNaoMedido.getValor() + "("+valorExtenso+")";
				
				form.setValorResidencialAtual(valorMontado);
			}
			
			//------------------------- Fim do carregar os consumos não medidos (Residencial) -------------------------//

			
		}
		request.setAttribute("colecaoResidencial", colecaoResidencial);
		return retorno;
	}		
}