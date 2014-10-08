package gcom.gui.atendimentopublico.ordemservico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.FiltroOrdemRepavimentadoraServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoRepavimentadora;
import gcom.atendimentopublico.ordemservico.OrdemRepavimentadoraServico;
import gcom.atendimentopublico.ordemservico.ServicoRepavimentadora;
import gcom.atendimentopublico.ordemservico.bean.ServicoRepavimentadoraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1475] - Informar Serviços da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 20/05/2013
 *
 */
public class ExibirInformarServicosRepavimentadoraPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("informarServicoRepavimentadoraPopup");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InformarServicoRepavimentadoraPopupActionForm form = (InformarServicoRepavimentadoraPopupActionForm) actionForm;
		
		String associar = (String) httpServletRequest.getParameter("associar");
		
		String remover = (String) httpServletRequest.getParameter("remover");
		
		String idOrdemServico = httpServletRequest.getParameter("idOrdemServico");
		if(idOrdemServico != null && !idOrdemServico.equals("")){
			form.setIdOrdemServico(idOrdemServico);
			
			if(associar == null && remover == null){
				this.pesquisarServicosAssociadosOrdemServico(form, fachada, sessao);
				
				form.setServico("-1");
				form.setQuantidade("");
			}
		}
		
		String idUnidadeRepavimentadora = httpServletRequest.getParameter("idUnidadeRepavimentadora");
		if(idUnidadeRepavimentadora != null && !idUnidadeRepavimentadora.equals("")){
			form.setIdUnidadeRepavimentadora(idUnidadeRepavimentadora);
		}
		
		Collection<ServicoRepavimentadoraHelper> colecaoHelper = form.getColecaoServicoRepavimentadoraHelper();
		
		if(colecaoHelper == null){
			colecaoHelper = new ArrayList<ServicoRepavimentadoraHelper>();
		}
		
		FiltroServicoRepavimentadora filtroServicoRepavimentadora = new FiltroServicoRepavimentadora(FiltroServicoRepavimentadora.DESCRICAO);
		filtroServicoRepavimentadora.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.ID_UNIDADE_REPAVIMENTADORA, idUnidadeRepavimentadora));
		filtroServicoRepavimentadora.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoServicosRepavimentadora = fachada.pesquisar(filtroServicoRepavimentadora, ServicoRepavimentadora.class.getName());
		if(!Util.isVazioOrNulo(colecaoServicosRepavimentadora)){
			httpServletRequest.setAttribute("colecaoServicosRepavimentadora", colecaoServicosRepavimentadora);
		}
		
		if(associar != null && !associar.equals("")){
			colecaoHelper = this.associarServicos(form, fachada, colecaoHelper);
			form.setServico("-1");
			form.setQuantidade("");
			form.setColecaoServicoRepavimentadoraHelper(colecaoHelper);
		}
		
		if(remover != null && !remover.equals("")){
			String posicao = (String) httpServletRequest.getParameter("posicao");
			colecaoHelper = this.removerServicos(posicao, colecaoHelper);
			form.setColecaoServicoRepavimentadoraHelper(colecaoHelper);
		}
		
		return retorno;
	}
	
	private void pesquisarServicosAssociadosOrdemServico(InformarServicoRepavimentadoraPopupActionForm form, Fachada fachada, HttpSession sessao){
		FiltroOrdemRepavimentadoraServico filtro = new FiltroOrdemRepavimentadoraServico();
		filtro.adicionarParametro(new ParametroSimples(FiltroOrdemRepavimentadoraServico.ID_ORDEM_SERVICO, form.getIdOrdemServico()));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemRepavimentadoraServico.SERVICO_REPAVIMENTADORA);
		
		Collection<ServicoRepavimentadoraHelper> colecaoHelper = new ArrayList<ServicoRepavimentadoraHelper>();
		
		Collection<?> colecao = fachada.pesquisar(filtro, OrdemRepavimentadoraServico.class.getName());
		if(!Util.isVazioOrNulo(colecao)){
			ServicoRepavimentadoraHelper helper = null;
			OrdemRepavimentadoraServico ordemRepavimentadoraServico = null;
			
			Iterator<?> it = colecao.iterator();
			while(it.hasNext()){
				ordemRepavimentadoraServico = (OrdemRepavimentadoraServico) it.next();
				
				helper = new ServicoRepavimentadoraHelper();
				helper.setIdServico(ordemRepavimentadoraServico.getServicoRepavimentadora().getId().toString());
				helper.setDescricao(ordemRepavimentadoraServico.getServicoRepavimentadora().getDescricao());
				helper.setQuantidade(ordemRepavimentadoraServico.getQuantidadeServico().toString());
				
				colecaoHelper.add(helper);
			}
		
		}
		
		form.setColecaoServicoRepavimentadoraHelper(colecaoHelper);
	}
	
	private Collection<ServicoRepavimentadoraHelper> associarServicos(InformarServicoRepavimentadoraPopupActionForm form, Fachada fachada, Collection<ServicoRepavimentadoraHelper> colecaoHelper){
		ServicoRepavimentadora servicoRepavimentadora = null;
		
		FiltroServicoRepavimentadora filtro = new FiltroServicoRepavimentadora();
		filtro.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.ID, form.getServico()));
		
		Collection<?> colecao = fachada.pesquisar(filtro, ServicoRepavimentadora.class.getName());
		if(!Util.isVazioOrNulo(colecao)){
			servicoRepavimentadora = (ServicoRepavimentadora) Util.retonarObjetoDeColecao(colecao);
		}
		
		ServicoRepavimentadoraHelper helper = new ServicoRepavimentadoraHelper();
		helper.setIdServico(form.getServico());
		helper.setDescricao(servicoRepavimentadora.getDescricao());
		helper.setQuantidade(form.getQuantidade().replaceAll(",", "."));
		
		if(!colecaoHelper.contains(helper)){
			colecaoHelper.add(helper);
		}else{
			throw new ActionServletException("atencao.servicos_ja_adicionados");
		}
		
		return colecaoHelper;
	}
	
	private Collection<ServicoRepavimentadoraHelper> removerServicos(String posicao, Collection<ServicoRepavimentadoraHelper> colecaoHelper){
		Iterator<ServicoRepavimentadoraHelper> it = colecaoHelper.iterator();
		while(it.hasNext()){
			ServicoRepavimentadoraHelper helper = (ServicoRepavimentadoraHelper) it.next();
			
			if(helper.getIdServico().equals(posicao)){
				colecaoHelper.remove(helper);
				break;
			}
		}
		
		return colecaoHelper;
	}
}
