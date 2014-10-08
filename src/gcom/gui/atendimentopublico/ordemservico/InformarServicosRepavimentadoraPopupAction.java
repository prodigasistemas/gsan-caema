package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemRepavimentadoraServico;
import gcom.atendimentopublico.ordemservico.OrdemRepavimentadoraServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoRepavimentadora;
import gcom.atendimentopublico.ordemservico.bean.ServicoRepavimentadoraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1475] - Informar Serviços da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 20/05/2013
 *
 */
public class InformarServicosRepavimentadoraPopupAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarOrdemProcessoRepavimentacaoPopUp");
		
		//Formulário
		InformarServicoRepavimentadoraPopupActionForm form = (InformarServicoRepavimentadoraPopupActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Coleção de Serviços da Repavimentadora
		Collection<ServicoRepavimentadoraHelper> colecaoHelper = form.getColecaoServicoRepavimentadoraHelper();
		
		if(Util.isVazioOrNulo(colecaoHelper)){
			throw new ActionServletException("atencao.nenhum_servico_informado");
		}
		
		Integer idOrdemServico = Integer.parseInt(form.getIdOrdemServico());
		
		FiltroOrdemRepavimentadoraServico filtroOrdemRepavimentadoraServico = new FiltroOrdemRepavimentadoraServico();
		filtroOrdemRepavimentadoraServico.adicionarParametro(new ParametroSimples(FiltroOrdemRepavimentadoraServico.ID_ORDEM_SERVICO, idOrdemServico));
		
		Collection<OrdemRepavimentadoraServico> colecaoOrdemRemovidas = 
				fachada.pesquisar(filtroOrdemRepavimentadoraServico, OrdemRepavimentadoraServico.class.getName());
		
		Collection<OrdemRepavimentadoraServico> colecaoOrdemRepavimentadoraServico = new ArrayList<OrdemRepavimentadoraServico>();
		
		Iterator<ServicoRepavimentadoraHelper> it = colecaoHelper.iterator();
		
		while(it.hasNext()){
			ServicoRepavimentadoraHelper helper = (ServicoRepavimentadoraHelper) it.next();
			
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(idOrdemServico);
			
			ServicoRepavimentadora servicoRepavimentadora = new ServicoRepavimentadora();
			servicoRepavimentadora.setId(Integer.parseInt(helper.getIdServico()));
			
			String quantidade = helper.getQuantidade().replaceAll(",", ".");
			BigDecimal quantidadeServico = Util.formatarMoedaRealparaBigDecimal(quantidade);
			
			OrdemRepavimentadoraServico ordemRepavimentadoraServico = new OrdemRepavimentadoraServico();
			ordemRepavimentadoraServico.setOrdemServico(ordemServico);
			ordemRepavimentadoraServico.setServicoRepavimentadora(servicoRepavimentadora);
			ordemRepavimentadoraServico.setQuantidadeServico(quantidadeServico);
			
			colecaoOrdemRepavimentadoraServico.add(ordemRepavimentadoraServico);
		}
		
		fachada.informarServicosRepavimentadora(colecaoOrdemRepavimentadoraServico, colecaoOrdemRemovidas);
		
		return retorno;
	}
}
