package gcom.gui.cobranca.cobrancaporresultado;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MovimentarOrdemServicoAction extends GcomAction {
   
    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela de sucesso
    	ActionForward retorno = actionMapping.findForward("telaSucesso");

    	//Cria uma instância da sessão
    	HttpSession sessao = httpServletRequest.getSession(false);
		
        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
        
        if (form.getIdMotivoEncerramento() != null && !form.getIdMotivoEncerramento().equals("")) {
        
      //verifica se o motivo do encerramento é com execucao, caso seja implementa o metodo encerrarOScomExecucao
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro( new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, form.getIdMotivoEncerramento()));
		
		Collection<AtendimentoMotivoEncerramento> colecaoMotivoEncerramento = Fachada.getInstancia().pesquisar(
				filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		
		AtendimentoMotivoEncerramento motivoEncerramento = (AtendimentoMotivoEncerramento) Util.retonarObjetoDeColecao(colecaoMotivoEncerramento);
		
		Short indicadorExecucao = motivoEncerramento.getIndicadorExecucao();
    	
        if (indicadorExecucao == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM) {
			
			if (form.getColecaoOrdemServico() != null && !form.getColecaoOrdemServico().isEmpty()) {
				
				OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(form.getColecaoOrdemServico());
				
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, ordemServico.getServicoTipo().getId()));
			Collection<ServicoTipo> colecaoServicoTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
			
			ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
			
			if (servicoTipo != null) {
				
				Short indicadorComercialAtualiza = new Short(servicoTipo.getIndicadorAtualizaComercial());

				if (indicadorComercialAtualiza.equals(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM)) {

							FiltroServicoTipoOperacao filtroServicoTipoOperacao = new FiltroServicoTipoOperacao();
							filtroServicoTipoOperacao.adicionarCaminhoParaCarregamentoEntidade("operacao");
							filtroServicoTipoOperacao.adicionarParametro(new ParametroSimples(FiltroServicoTipoOperacao.ID_SERVICO_TIPO, servicoTipo.getId()));
							Collection<ServicoTipoOperacao> colecaoServicoTipoOperacao = Fachada.getInstancia().pesquisar(filtroServicoTipoOperacao, ServicoTipoOperacao.class.getName());
							// caso exista uma funcionalidade assiciada ao serviço tipo
							if (colecaoServicoTipoOperacao != null && !colecaoServicoTipoOperacao.isEmpty()) {
								ServicoTipoOperacao servicoTipoOperacao = (ServicoTipoOperacao) Util.retonarObjetoDeColecao(colecaoServicoTipoOperacao);
								String caminhoOperacao = servicoTipoOperacao.getOperacao().getCaminhoUrl();
								// caso exista o caminho da operação
								if (caminhoOperacao != null && !caminhoOperacao.equals("")) {
									int tamanhoOperacao = caminhoOperacao.length();
									// seta o caminho no mapeamento para ser chamado
									String caminhoRetorno = caminhoOperacao.substring(0, tamanhoOperacao - 3);
									httpServletRequest.setAttribute("veioEncerrarOS", ordemServico.getId().toString());
									httpServletRequest.setAttribute("dataEncerramento", form.getDataEncerramento().toString());
									sessao.setAttribute("movimentarOS", "TRUE");
									retorno = actionMapping.findForward(caminhoRetorno);
									if (retorno == null) {
										throw new ActionServletException("atencao.caminho_url_indisponivel");
									}
								}
							}
						}
					}
				}
			}
        }
        
        if (httpServletRequest.getAttribute("tipoMovimentacao") != null 
        		&& !httpServletRequest.getAttribute("tipoMovimentacao").equals("")
        		&& !httpServletRequest.getAttribute("tipoMovimentacao").equals("encerradaExecução")){
        	
	     	if (httpServletRequest.getAttribute("tipoMovimentacao") != null
	    			&& !httpServletRequest.getAttribute("tipoMovimentacao").equals("")) {
	    		// Monta página de sucesso
	        	montarPaginaSucesso(httpServletRequest,
	        			"Ordem(ns) de Serviço " + httpServletRequest.getAttribute("tipoMovimentacao") + " com sucesso!",
	        			"Voltar",
	        			"exibirMovimentarOrdemServicoAction.do?comando=" + form.getIdComandoContaCobranca());
	        	
	    	} else if (httpServletRequest.getAttribute("gerarRelatorioEmitirDocumentoVisitaCobranca") != null
	    			&& !httpServletRequest.getAttribute("gerarRelatorioEmitirDocumentoVisitaCobranca").equals("")) {
	
				return actionMapping.findForward("gerarRelatorioEmitirDocumentoVisitaCobranca");
				
	    	} else {
		    	//Monta página de sucesso
		    	montarPaginaSucesso(httpServletRequest, "Ordem de Serviço movimentada com sucesso!",
		    			"Voltar",
		    			"exibirMovimentarOrdemServicoAction.do?comando=" + form.getIdComandoContaCobranca());
	    	}
        }

    	//Limpa a sessão depois de inserir os dados
        sessao.removeAttribute("grupo");
        sessao.removeAttribute("grupoFuncionalidades");

        //Retorna o mapeamento contido na variável "retorno" 
        return retorno;
    }

}
