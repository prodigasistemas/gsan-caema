package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.MotivoDeNaoExecucaoDoServico;
import gcom.atendimentopublico.ordemservico.bean.UnidadeRepavimentadoraHelper;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.ordemservico.AtualizarMotivoDeNaoExecucaoDoServicoActionForm;
import gcom.util.Util;
/**
 * [UC 1513] - Manter Motivo de não execução do serviço 
 * 
 * @author Diogo Luiz
 * @date 01/07/2013
 *
 */
public class AtualizarMotivoDeNaoExecucaoDoServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarMotivoDeNaoExecucaoDoServicoActionForm atualizarMotivoDeNaoExecucaoDoServicoActionForm = 
				(AtualizarMotivoDeNaoExecucaoDoServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession();
		
		Collection<UnidadeRepavimentadoraHelper> colecaoHelper = (Collection<UnidadeRepavimentadoraHelper>) sessao.getAttribute("colecaoHelper");
		if(Util.isVazioOrNulo(colecaoHelper)){
			throw new ActionServletException("atencao.unidade_repavimentadora_nao_adicionado");
		}
		
		if(validarCampos(atualizarMotivoDeNaoExecucaoDoServicoActionForm)){		
			
			MotivoDeNaoExecucaoDoServico motivoDeNaoExecucaoDoServico = new MotivoDeNaoExecucaoDoServico();
			motivoDeNaoExecucaoDoServico.setId(Integer.valueOf(atualizarMotivoDeNaoExecucaoDoServicoActionForm.getId()));			
			
			motivoDeNaoExecucaoDoServico.setId(new Integer(atualizarMotivoDeNaoExecucaoDoServicoActionForm.getId()));
			motivoDeNaoExecucaoDoServico.setDescricao(atualizarMotivoDeNaoExecucaoDoServicoActionForm.getDescricao());
			
			if(atualizarMotivoDeNaoExecucaoDoServicoActionForm.getDescricaoAbreviada() != null 
				&& !atualizarMotivoDeNaoExecucaoDoServicoActionForm.getDescricaoAbreviada().equals("")){
				motivoDeNaoExecucaoDoServico.setDescricaoAbreviada(atualizarMotivoDeNaoExecucaoDoServicoActionForm.getDescricaoAbreviada());
			}else{
				motivoDeNaoExecucaoDoServico.setDescricaoAbreviada(null);
			}
			
			motivoDeNaoExecucaoDoServico.setIndicadorUso(new Short(atualizarMotivoDeNaoExecucaoDoServicoActionForm.getIndicadorUso()));
			motivoDeNaoExecucaoDoServico.setUltimaAlteracao(new Date());
			
			fachada.atualizarMotivoDeNaoExecucaoDoServicoAction(motivoDeNaoExecucaoDoServico, colecaoHelper);			
		}else{
			throw new ActionServletException("atencao.parametros.obrigatorios.nao.selecionados");
		}
		
		//[FS0003] - Verificar sucesso da transação
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Motivo de não execução do serviço "+ 
		atualizarMotivoDeNaoExecucaoDoServicoActionForm.getDescricao() +" atualizado com sucesso.", 
		"Realizar outra Manutenção do Motivo de não execução do serviço","exibirFiltrarMotivoDeNaoExecucaoDoServicoAction.do?menu=sim");		
		
		return retorno;		
	}	
	
	//[FS0001] - Verificar preenchimento dos campos
		private boolean validarCampos(AtualizarMotivoDeNaoExecucaoDoServicoActionForm form){
			boolean valido = false;
			
			if(Util.verificarIdNaoVazio(form.getId()) && Util.verificarNaoVazio(form.getDescricao()) && 
					Util.verificarNaoVazio(form.getIndicadorUso())){
				valido = true;
			}
			return valido;
		}	
}