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

import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1473] - Filtrar Serviço da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 16/05/2013
 *
 */
public class ExibirFiltrarServicoRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Aponta o caminho de retorno do Action no struts
		ActionForward retorno = actionMapping.findForward("filtrarServicoRepavimentadora");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Usuário Logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarServicoRepavimentadoraActionForm form = (FiltrarServicoRepavimentadoraActionForm) actionForm;
		
		FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();
		
		filtroMaterialUnidade.setCampoOrderBy(FiltroMaterialUnidade.DESCRICAO);
		
		Collection<?> colecaoMaterialUnidade = fachada.pesquisar(filtroMaterialUnidade,MaterialUnidade.class.getName());
		
		// Coloca na sessao os valores da consulta do filtro
		sessao.setAttribute("colecaoMaterialUnidade",colecaoMaterialUnidade);
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorUso("3");
			sessao.setAttribute("indicadorAtualizar","1");
		}
		
		this.pesquisarUnidadeRepavimentadora(fachada, httpServletRequest, usuario);
		
		return retorno;
	}
	
	/**
	 * Método responsável por pesquisar as unidades repavimentadoras
	 * de acordo com a unidade organizacional do usuário
	 * 
	 * @author Davi Menezes
	 * @date 16/05/2013
	 * 
	 * @param fachada
	 * @param request
	 * @param usuarioLogado
	 */
	private void pesquisarUnidadeRepavimentadora(Fachada fachada, HttpServletRequest request, Usuario usuarioLogado){
		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = new ArrayList<UnidadeOrganizacional>();
		
		UnidadeOrganizacional unidadeOrganizacional = usuarioLogado.getUnidadeOrganizacional();
		if(unidadeOrganizacional.getUnidadeTipo() != null && 
				(unidadeOrganizacional.getUnidadeTipo().getId().intValue() != UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue())){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeOrganizacional.ID_UNIDADE_REPAVIMENTADORA));
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
			
			Collection<?> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				Iterator<?> it = colecaoUnidadeOrganizacional.iterator();
				while(it.hasNext()){
					unidadeOrganizacional = (UnidadeOrganizacional) it.next();
					
					if(!colecaoUnidadeRepavimentadora.contains(unidadeOrganizacional.getUnidadeRepavimentadora())){
						colecaoUnidadeRepavimentadora.add(unidadeOrganizacional.getUnidadeRepavimentadora());
					}
				}
			}
		}else{
			colecaoUnidadeRepavimentadora.add(unidadeOrganizacional);
		}
		
		request.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
	}
		
	
}
