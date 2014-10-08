
package gcom.gui.cobranca.cobrancaporresultado;


import gcom.cobranca.FiltroMotivoNaoGeracaoCobrancaResultado;
import gcom.cobranca.MotivoNaoGeracaoCobrancaResultado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Diego Maciel
 * @date 27/03/2008
 */
public class InserirMotivoNaoGeracaoCobrancaResultadoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um motivo de corte
	 * 
	 * [UC0754] Inserir Motivo Nao GeracaoCobrancaResultado
	 * 
	 * 
	 * @author Diego Maciel
	 * @date 27/04/2012
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		InserirMotivoNaoGeracaoCobrancaResultadoActionForm form = (InserirMotivoNaoGeracaoCobrancaResultadoActionForm) actionForm;
				
		

    	String descricao = form.getDescricao().toUpperCase().replaceAll("\r\n", " ");
    	String descricaoAbreviada = form.getDescricaoAbreviada().toUpperCase().trim();
    	String indicadorUso = form.getIndicadorUso();
    	String tipoMotivo = form.getTipoMotivo();
    	
 

		MotivoNaoGeracaoCobrancaResultado motivo = new MotivoNaoGeracaoCobrancaResultado();
		Collection colecaoPesquisa = null;


		// Verifica se o campo tipo Motivo está preenchido
    	if(tipoMotivo == null || tipoMotivo.trim().equals("")){
    		throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio",null,"Tipo do Motivo");
    	}
    	// Verifica se o campo indicador Uso está preenchido
    	if(indicadorUso == null || indicadorUso.trim().equals("")){
    		throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio",null,"Indicador de Uso");
    	}
		
	
		//DESCRIÇÃO
		if(descricao == null || descricao.trim().equals("")){

			//Descrição  não informada
			throw new ActionServletException("atencao.descricao_motivo_nao_geracao_cobranca_resultado_nao_informada");
			//[FS0002 - Verificar existência do motivo de não geração para cobrança por resultado]
		} else {
			
			int colecaoMotivo = Fachada.getInstancia().pesquisarMotivoNaoGeracaoPorDescricao(descricao, null);
			if ( colecaoMotivo > 0 ) {
				throw new ActionServletException("atencao.descricao_existente",null,descricao);	        	
			} 
		}
		
		//Se descrição for maior que 100 
		if(descricao !=null && descricao.length() > 100)
			throw new ActionServletException("atencao.msg_personalizada","Descrição excedeu limite de 100 caracteres");
		
		//Se descrição abreviada for maior que 100
		if(descricaoAbreviada !=null && descricaoAbreviada.length() > 100)
			throw new ActionServletException("atencao.msg_personalizada","Descrição Abreviada excedeu limite de 100 caracteres");
		
    	motivo.setDescricao(descricao.trim());
    	motivo.setDescricaoAbreviada(descricaoAbreviada);
    	motivo.setIndicadorUso(new Short(indicadorUso));
    	motivo.setCodigoTipoMotivo(new Integer(tipoMotivo));
    
	
		// Ultima alteração
		motivo.setUltimaAlteracao(new Date());
		
		
        
		//Cria uma instância do FiltroMotivoNaoGeracaoCobrancaResultado 
		FiltroMotivoNaoGeracaoCobrancaResultado filtroMotivo = new FiltroMotivoNaoGeracaoCobrancaResultado();

		filtroMotivo.adicionarParametro(new ParametroSimples(
				FiltroMotivoNaoGeracaoCobrancaResultado.DESCRICAO, motivo.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroMotivo,
				MotivoNaoGeracaoCobrancaResultado.class.getName());

		// Caso já haja um Motivo Nao Geracao Cobrança Resultado com a descriçao passada
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.motivo_nao_geracao_combranca_por_resultado_ja_cadastrado", null, motivo.getDescricao());
		} else {
			// Caso não haja, irá inserir
			motivo.setDescricao(descricao);

			Integer idMotivoNaoGeracaoCobrancaResultado = (Integer) fachada.inserir(motivo);

			montarPaginaSucesso(httpServletRequest,
					"Motivo de Não Geração Cobrança por Resultado de código " + idMotivoNaoGeracaoCobrancaResultado
							+ " inserido com sucesso.",
					"Inserir outro Motivo de Não Geração Cobrança por Resultado",
					"exibirInserirMotivoNaoGeracaoCobrancaResultadoAction.do?menu=sim",
					"exibirAtualizarMotivoNaoGeracaoCobrancaResultadoAction.do?idMotivoAtualizar="
							+ idMotivoNaoGeracaoCobrancaResultado,
					"Atualizar Motivo de Não Geração Cobrança por Resultado inserido");

			sessao.removeAttribute("InserirMotivoNaoGeracaoCobrancaResultadoActionForm");

			return retorno;
		}

	}
}