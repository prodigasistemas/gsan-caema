package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMotivoDeNaoExecucaoDoServico;
import gcom.atendimentopublico.ordemservico.FiltroMotivoNaoExecucaoUnidadeRepavimentadora;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC 1513] - Filtrar Motivo de não execução do serviço
 * 
 * @author Diogo Luiz
 * @date 27/06/2013
 *
 */
public class FiltrarMotivoDeNaoExecucaoDoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirMotivoDeNaoExecucaoDoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarMotivoDeNaoExecucaoDoServicoActionForm form = (FiltrarMotivoDeNaoExecucaoDoServicoActionForm) actionForm;
		
		//adicionar entidade com os dados vindo form
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String unidadeRepavimentadora = form.getUnidadeRepavimentacao();
		String indicadorUso = form.getIndicadorUso();		
		String tipoPesquisa = form.getTipoPesquisa();

		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");		
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		boolean peloMenosUmParametroInformado = false;
		
		FiltroMotivoNaoExecucaoUnidadeRepavimentadora filtroMotivoNaoExecucao = 
				new FiltroMotivoNaoExecucaoUnidadeRepavimentadora(
						FiltroMotivoNaoExecucaoUnidadeRepavimentadora.ID_MOTIVO_DE_NAO_EXECUCAO_DO_SERVICO);
		
		filtroMotivoNaoExecucao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroMotivoNaoExecucaoUnidadeRepavimentadora.MOTIVO_DE_NAO_EXECUCAO_DO_SERVICO);
		
		filtroMotivoNaoExecucao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroMotivoNaoExecucaoUnidadeRepavimentadora.UNIDADE_REPAVIMENTADORA);
		
		//Descrição do Serviço
		if(descricao != null && !descricao.trim().equals("")){
			peloMenosUmParametroInformado = true;
			if(tipoPesquisa != null && !tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){			
			filtroMotivoNaoExecucao.adicionarParametro(new ComparacaoTexto(FiltroMotivoNaoExecucaoUnidadeRepavimentadora.DESCRICAO, 
					descricao));			
			}else{
				filtroMotivoNaoExecucao.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroMotivoNaoExecucaoUnidadeRepavimentadora.DESCRICAO, 
						descricao));
			}
		}
		
		//Descrição Abreviada do Serviço
		if(descricaoAbreviada != null && !descricaoAbreviada.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroMotivoNaoExecucao.adicionarParametro(new ComparacaoTexto(
					FiltroMotivoNaoExecucaoUnidadeRepavimentadora.DESCRICAO_ABREVIADA,descricaoAbreviada));
		}
		
		//Unidade Repavimentadora
		if(unidadeRepavimentadora != null && !unidadeRepavimentadora.trim().equals("") && 
				!unidadeRepavimentadora.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			peloMenosUmParametroInformado = true;
			filtroMotivoNaoExecucao.adicionarParametro(new ParametroSimples(
					FiltroMotivoNaoExecucaoUnidadeRepavimentadora.ID_UNIDADE_REPAVIMENTADORA, unidadeRepavimentadora));
		}
		
		//Indicador de Uso
		if(indicadorUso != null && !indicadorUso.equals("") && !indicadorUso.equals("3")){
			peloMenosUmParametroInformado = true;
			filtroMotivoNaoExecucao.adicionarParametro(new ParametroSimples(
					FiltroMotivoNaoExecucaoUnidadeRepavimentadora.INDICADOR_USO,indicadorUso));			
		}
		
		//Erro caso o usuário mandar filtrar sem parametros
		if(!peloMenosUmParametroInformado){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		sessao.setAttribute("filtroMotivoNaoExecucao", filtroMotivoNaoExecucao);
		
		return retorno;
	}	
}