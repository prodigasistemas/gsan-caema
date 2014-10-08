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

import gcom.atendimentopublico.ordemservico.FiltroMotivoDeNaoExecucaoDoServico;
import gcom.atendimentopublico.ordemservico.FiltroMotivoNaoExecucaoUnidadeRepavimentadora;
import gcom.atendimentopublico.ordemservico.FiltroServicoRepavimentadora;
import gcom.atendimentopublico.ordemservico.MotivoDeNaoExecucaoDoServico;
import gcom.atendimentopublico.ordemservico.MotivoNaoExecucaoUnidadeRepavimentadora;
import gcom.atendimentopublico.ordemservico.ServicoRepavimentadora;
import gcom.atendimentopublico.ordemservico.bean.UnidadeRepavimentadoraHelper;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;
/**
 * [UC 1513] - Manter Motivo de não execução do serviço 
 * 
 * @author Diogo Luiz
 * @date 01/07/2013
 *
 */
public class ExibirAtualizarMotivoDeNaoExecucaoDoServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarMotivoDeNaoExecucaoDoServico");
		AtualizarMotivoDeNaoExecucaoDoServicoActionForm atualizarMotivoDeNaoExecucaoDoServicoActionForm = 
				 (AtualizarMotivoDeNaoExecucaoDoServicoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
	
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		this.pesquisarUnidadeRepavimentadora(fachada, httpServletRequest, usuario);		
		
		AtualizarMotivoDeNaoExecucaoDoServicoActionForm atualizarMotivoDeNaoExecucaoDoServico = 
				(AtualizarMotivoDeNaoExecucaoDoServicoActionForm) actionForm;
		
		String unidadeRepavimentadora = atualizarMotivoDeNaoExecucaoDoServico.getUnidadeRepavimentadora();
		
		Collection<UnidadeRepavimentadoraHelper> colecaoHelper = (Collection<UnidadeRepavimentadoraHelper>) 
				sessao.getAttribute("colecaoHelper");
		
		String adicionar = httpServletRequest.getParameter("adicionar");
			
		if(adicionar != null && adicionar.equals("sim")){
			this.adicionarUnidadeRepavimentadora(unidadeRepavimentadora,colecaoHelper, fachada, sessao);
			atualizarMotivoDeNaoExecucaoDoServicoActionForm.setUnidadeRepavimentadora(
					String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		}			
						
		String remover = httpServletRequest.getParameter("remover");
		String posicao = httpServletRequest.getParameter("posicao");
		if(remover != null && remover.equals("sim")){
			this.removerUnidadeRepavimentadora(posicao,colecaoHelper,sessao);
		}			
		
		MotivoDeNaoExecucaoDoServico motivoDeNaoExecucaoDoServico = null;
		String idMotivo = null;
		
		if(httpServletRequest.getParameter("idMotivo") != null){
			//tela do Manter
			idMotivo = (String) httpServletRequest.getParameter("idMotivo");
			sessao.setAttribute("idMotivo", idMotivo);			
		}else if(sessao.getAttribute("idMotivo") != null){
			//tela do filtrar
			idMotivo = (String) sessao.getAttribute("idMotivo");
		}
		
		if(idMotivo == null){
			if(httpServletRequest.getParameter("idRegistroAtualizar") == null){
				motivoDeNaoExecucaoDoServico = (MotivoDeNaoExecucaoDoServico) 
						sessao.getAttribute("motivoDeNaoExecucaoDoServicoAtualizar");				
			}else{
				idMotivo = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}	
		
		if (motivoDeNaoExecucaoDoServico == null){

			if (idMotivo != null && !idMotivo.equals("")) {
				FiltroMotivoDeNaoExecucaoDoServico filtroMotivoDeNaoExecucaoDoServico = new FiltroMotivoDeNaoExecucaoDoServico();
				
				filtroMotivoDeNaoExecucaoDoServico.adicionarParametro(
						new ParametroSimples(FiltroMotivoDeNaoExecucaoDoServico.ID, idMotivo));
						Collection<MotivoDeNaoExecucaoDoServico> colecaoMotivoDeNaoExecucaoDoServico = 
								fachada.pesquisar(filtroMotivoDeNaoExecucaoDoServico, MotivoDeNaoExecucaoDoServico.class.getName());

				if (!Util.isVazioOrNulo(colecaoMotivoDeNaoExecucaoDoServico)) {
					motivoDeNaoExecucaoDoServico = (MotivoDeNaoExecucaoDoServico) 
							Util.retonarObjetoDeColecao(colecaoMotivoDeNaoExecucaoDoServico);
						}
					}
				}				
			
				atualizarMotivoDeNaoExecucaoDoServicoActionForm.setId(String.valueOf(motivoDeNaoExecucaoDoServico.getId()));
				atualizarMotivoDeNaoExecucaoDoServicoActionForm.setDescricao(motivoDeNaoExecucaoDoServico.getDescricao());
				
				if(motivoDeNaoExecucaoDoServico.getDescricaoAbreviada() != null){
					atualizarMotivoDeNaoExecucaoDoServicoActionForm.setDescricaoAbreviada(
							motivoDeNaoExecucaoDoServico.getDescricaoAbreviada());
				}else{
					atualizarMotivoDeNaoExecucaoDoServicoActionForm.setDescricaoAbreviada("");
				}
				
				atualizarMotivoDeNaoExecucaoDoServicoActionForm.setIndicadorUso(String.valueOf(
						motivoDeNaoExecucaoDoServico.getIndicadorUso()));
				
				if(adicionar == null && remover == null){
				pesquisarMotivoNaoExecucaoUnidadeRepavimentadora(motivoDeNaoExecucaoDoServico.getId(), fachada, sessao);
			}
				
				sessao.setAttribute("motivoDeNaoExecucaoDoServico", motivoDeNaoExecucaoDoServico);					
		
		return retorno;	
	}	
	/**
	 * Método responsável por pesquisar as unidades repavimentadoras
	 * de acordo com a unidade organizacional do usuário
	 * 
	 * @author Diogo Luiz
	 * @date 01/06/2013
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
	
	private void pesquisarMotivoNaoExecucaoUnidadeRepavimentadora(Integer idMotivo, Fachada fachada, HttpSession sessao){
		Collection<UnidadeRepavimentadoraHelper> colecaoUnidadeRepavimentadoraHelper = new ArrayList<UnidadeRepavimentadoraHelper>();
		
		FiltroMotivoNaoExecucaoUnidadeRepavimentadora filtroMotivoNaoExecucaoUnidadeRepavimentadora = 
				new FiltroMotivoNaoExecucaoUnidadeRepavimentadora();
		filtroMotivoNaoExecucaoUnidadeRepavimentadora.adicionarParametro(new ParametroSimples(
				FiltroMotivoNaoExecucaoUnidadeRepavimentadora.ID_MOTIVO_DE_NAO_EXECUCAO_DO_SERVICO, idMotivo));
		filtroMotivoNaoExecucaoUnidadeRepavimentadora.adicionarCaminhoParaCarregamentoEntidade(
				FiltroMotivoNaoExecucaoUnidadeRepavimentadora.MOTIVO_DE_NAO_EXECUCAO_DO_SERVICO);
		filtroMotivoNaoExecucaoUnidadeRepavimentadora.adicionarCaminhoParaCarregamentoEntidade(
				FiltroMotivoNaoExecucaoUnidadeRepavimentadora.UNIDADE_REPAVIMENTADORA);
		
		Collection<?> colecaoMotivoNaoExecucaoUnidadeRepavimentadora = fachada.pesquisar(
				filtroMotivoNaoExecucaoUnidadeRepavimentadora, MotivoNaoExecucaoUnidadeRepavimentadora.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMotivoNaoExecucaoUnidadeRepavimentadora)){
			Iterator<?> iterator = colecaoMotivoNaoExecucaoUnidadeRepavimentadora.iterator();
			while(iterator.hasNext()){
				UnidadeRepavimentadoraHelper helper = new UnidadeRepavimentadoraHelper();
				
				MotivoNaoExecucaoUnidadeRepavimentadora motivoNaoExeucaoUnidadeRepavimentadora = (MotivoNaoExecucaoUnidadeRepavimentadora) 
						iterator.next();
				
				helper.setId(motivoNaoExeucaoUnidadeRepavimentadora.getComp_id().getUnidadeRepavimentadora().getId());
				helper.setUnidadeRepavimentadora(motivoNaoExeucaoUnidadeRepavimentadora.getComp_id().getUnidadeRepavimentadora().getDescricao());
				
				colecaoUnidadeRepavimentadoraHelper.add(helper);
			}
		}
		
		sessao.setAttribute("colecaoHelper", colecaoUnidadeRepavimentadoraHelper);
	}
	
	
	
	private void adicionarUnidadeRepavimentadora(String unidadeRepavimentadora,Collection<UnidadeRepavimentadoraHelper> colecaoHelper,
			Fachada fachada, HttpSession sessao){
		
		if(colecaoHelper == null){
			colecaoHelper = new ArrayList<UnidadeRepavimentadoraHelper>();
		}
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeRepavimentadora));
		Collection<?>colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoUnidade)){
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
			UnidadeRepavimentadoraHelper unidadeHelper = new UnidadeRepavimentadoraHelper();
			unidadeHelper.setId(unidadeOrganizacional.getId());
			unidadeHelper.setUnidadeRepavimentadora(unidadeOrganizacional.getDescricao());
			
			if(!colecaoHelper.contains(unidadeHelper)){
				colecaoHelper.add(unidadeHelper);
			}else{
				throw new ActionServletException("atencao.unidade_repavimentadora_ja_adicionado");
			}
			
			sessao.setAttribute("colecaoHelper", colecaoHelper);
		}		
		
	}
	
	private void removerUnidadeRepavimentadora(String posicao,Collection<UnidadeRepavimentadoraHelper> colecaoHelper,
			HttpSession sessao){
		Iterator<UnidadeRepavimentadoraHelper> it = colecaoHelper.iterator();
		
		while(it.hasNext()){
			UnidadeRepavimentadoraHelper helper = (UnidadeRepavimentadoraHelper)it.next();
			
			if(helper.getId().toString().equals(posicao)){
				colecaoHelper.remove(helper);
				break;
			}
		}
		sessao.setAttribute("colecaoHelper", colecaoHelper);	
	}
	
}
