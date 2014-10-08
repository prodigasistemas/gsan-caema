package gcom.gui.operacional.abastecimento;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSubSistemaEsgoto;
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
 * [UC1520] Filtrar SubSistema de Esgoto
 * 
 * @author Maxwell Moreira
 * @date 08/07/2013
 */
public class FiltrarSubSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
			ActionForward retorno = actionMapping.findForward("exibirManterSubSistemaEsgoto");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			FiltrarSubSistemaEsgotoActionForm form = (FiltrarSubSistemaEsgotoActionForm) actionForm;
			
			// Recupera todos os campos da página para ser colocada no filtro
			// posteriormente
			String codigo = form.getCodigoSistemaEsgoto();
			String descricao = form.getDescricaoSistemaEsgoto();
			String indicadorUso = form.getIndicadorUso();
			String descricaoAbreviada = form.getDescricaoAbreviada();
			String tipoPesquisa = form.getTipoPesquisa();
			String sistemaEsgoto = form.getSistemaEsgoto();
			
			// Indicador Atualizar
			String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
			
			if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
				
				sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
			} else {
				
				sessao.removeAttribute("indicadorAtualizar");
			}
			
			
			FiltroSubSistemaEsgoto filtroSubSistemaEsgoto = new FiltroSubSistemaEsgoto();
			
			boolean peloMenosUmParametroInformado = false;
				
			// Código do Subsistema de Esgoto
			if (codigo != null && !codigo.equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSubSistemaEsgoto.ID, codigo));
			}
			
			// Descrição do Subsistema de Esgoto
			if (descricao != null && !descricao.equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				
				if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
					filtroSubSistemaEsgoto.adicionarParametro(new ComparacaoTextoCompleto(FiltroSubSistemaEsgoto.DESCRICAO, descricao.trim()));
				} else {	
					filtroSubSistemaEsgoto.adicionarParametro(new ComparacaoTexto(FiltroSubSistemaEsgoto.DESCRICAO, descricao.trim()));
				}
			}
			
			// Descrição abreviada do Subsistema de Esgoto
			if (descricaoAbreviada != null && !descricaoAbreviada.equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSubSistemaEsgoto.DESCRICAO_ABREVIADA, descricaoAbreviada.trim()));
			}			
			
			// Sistema de Esgoto
			if (sistemaEsgoto != null && !sistemaEsgoto.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				peloMenosUmParametroInformado = true;
				filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSubSistemaEsgoto.SISTEMA_ESGOTO_ID, sistemaEsgoto));
			}
			
			// Situacao do Esgoto
			if ( indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase( "" ) ){
				peloMenosUmParametroInformado = true;
				filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSubSistemaEsgoto.INDICADOR_USO, indicadorUso));				
			}
			
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}								
			
			filtroSubSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade(FiltroSubSistemaEsgoto.SISTEMA_ESGOTO);
			
			// Manda o filtro pela sessao para o ExibirManterSistemaEsgotoAction
			sessao.setAttribute("filtroSistemaEsgotoSessao", filtroSubSistemaEsgoto);
					
			return retorno;
		}

}
