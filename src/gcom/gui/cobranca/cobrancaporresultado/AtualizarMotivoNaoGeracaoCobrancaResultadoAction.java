package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cobranca.MotivoNaoGeracaoCobrancaResultado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1319] Manter Motivo de N�o Gera��o Cobran�a por Resultado
 * 
 * @author Hugo Azevedo
 * @date 20/04/2012
 */
public class AtualizarMotivoNaoGeracaoCobrancaResultadoAction extends GcomAction {
   
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

    	//Cria uma inst�ncia da sess�o
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	AtualizarMotivoNaoGeracaoCobrancaResultadoActionForm form = (AtualizarMotivoNaoGeracaoCobrancaResultadoActionForm) actionForm;

    	String id = form.getId();
    	String codigo = form.getCodigo();
    	String descricao = form.getDescricao().toUpperCase().replaceAll("\r\n", " ");
    	String descricaoAbreviada = form.getDescricaoAbreviada().toUpperCase().replaceAll("\r\n", " ");
    	String indicadorUso = form.getIndicadorUso();
    	String tipoMotivo = form.getTipoMotivo();
    	Date dataHoraInicioProcesso = form.getDataHoraOperacao();
    	
    	
    	//DESCRI��O
    			if(descricao == null || descricao.trim().equals("")){

    				//Descri��o  n�o informada
    				throw new ActionServletException("atencao.descricao_motivo_nao_geracao_cobranca_resultado_nao_informada");
    				//[FS0002 - Verificar exist�ncia do motivo de n�o gera��o para cobran�a por resultado.
    			} else {
    				
    				int colecaoMotivo = Fachada.getInstancia().pesquisarMotivoNaoGeracaoPorDescricao(descricao, codigo);
    				if ( colecaoMotivo > 0 ) {
    					throw new ActionServletException("atencao.descricao_existente",null,descricao);	        	
    				} 
    			}
    			
    			
    			
    	
    	if(tipoMotivo == null && tipoMotivo.trim().equals("")){
    		throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio",null,"Tipo do Motivo");
    	}
    	
    	if(indicadorUso == null || indicadorUso.trim().equals("")){
    		throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio",null,"Indicador de Uso");
    	}
    	
    	//Se descri��o for maior que 100 
    	if(descricao !=null && descricao.length() > 100)
   			throw new ActionServletException("atencao.msg_personalizada","Descri��o excedeu limite de 100 caracteres");
    			
   		//Se descri��o abreviada for maior que 100
   		if(descricaoAbreviada !=null && descricaoAbreviada.length() > 100)
   			throw new ActionServletException("atencao.msg_personalizada","Descri��o Abreviada excedeu limite de 100 caracteres");
    
    	
    	//[FS0002] - Atualiza��o realizada por outro usu�rio
    	Integer idMotivo = new Integer(id);
    	MotivoNaoGeracaoCobrancaResultado motivo = fachada.obterMotivoNaoGeracaoCobrancaResultado(idMotivo);
    	
    	//Caso o motivo j� tenha sido excluido ou tenha sido atualizado ap�s o usu�rio ter entrado na funcionalidade
    	if(motivo == null || Util.compararDataTime(motivo.getUltimaAlteracao(), dataHoraInicioProcesso) == 1){
    		throw new ActionServletException(
					"atencao.registro_remocao_nao_existente");
    	}
    	
    	//Iniciando opera��o
    	//================================================================
    	
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	
    	motivo.setDescricao(descricao);
    	motivo.setDescricaoAbreviada(descricaoAbreviada);
    	motivo.setIndicadorUso(new Short(indicadorUso));
    	motivo.setCodigoTipoMotivo(new Integer(tipoMotivo));
    	motivo.setUltimaAlteracao(new Date());
    	
    	fachada.atualizarMotivoNaoGeracaoCobrancaResultado(motivo,usuarioLogado);
    	
    	sessao.removeAttribute("dataHoraInicioProcesso");
    	
    	montarPaginaSucesso(
				httpServletRequest,
				"Motivo de N�o Gera��o Cobran�a por Resultado atualizado com sucesso",
				"Realizar outra Manuten��o de Motivo de N�o Gera��o para Cobran�a por Resultado",
				"exibirFiltrarMotivoNaoGeracaoCobrancaResultadoAction.do?menu=sim");
    	
    	
        //Retorna o mapeamento contido na vari�vel "retorno" 
        return retorno;
    }

}
