package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.FiltroImovelInscricaoResetorizada;
import gcom.cadastro.ImovelInscricaoResetorizada;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1389] - Limpar Mensagens de Crítica Para Atualização das Inscrições - GSAN
 * 
 * @author Davi Menezes
 * @date 09/11/2012
 *
 */
public class LimparMensagemCriticaAtualizacaoInscricoesAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm formulario, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward retorno = 
				mapping.findForward("telaSucesso");
		
		LimparMensagemCriticaAtualizacaoInscricoesActionForm form = 
				(LimparMensagemCriticaAtualizacaoInscricoesActionForm) formulario;
		
		Fachada fachada = Fachada.getInstancia();
		
		if(form.getIdsRegistro() == null || form.getIdsRegistro().length < 1){
			throw new ActionServletException("atencao.nenhum_registro_selecionado");
		}else{
			for (int i = 0; i < form.getIdsRegistro().length; i++){
				FiltroImovelInscricaoResetorizada filtroImovel = new FiltroImovelInscricaoResetorizada();
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.ID, form.getIdsRegistro()[i]));
				
				Collection<?> colImovel = fachada.pesquisar(filtroImovel, ImovelInscricaoResetorizada.class.getName());
				if(!Util.isVazioOrNulo(colImovel)){
					ImovelInscricaoResetorizada imovelInscricaoResetorizada = 
							(ImovelInscricaoResetorizada) Util.retonarObjetoDeColecao(colImovel);
					
					imovelInscricaoResetorizada.setOcorrenciaResetorizacao(null);
					imovelInscricaoResetorizada.setUltimaAlteracao(new Date());
					
					fachada.atualizar(imovelInscricaoResetorizada);
				}
			}
		}
		
		this.montarPaginaSucesso(request, "Mensagens Excluídas com Sucesso.", "Limpar Mensagem de Crítica para Atualizacao das Inscricoes", 
				"exibirLimparMensagemCriticaAtualizacaoInscricoesAction.do?menu=sim");
		
		return retorno;
	}
}
