/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Anderson Cabral do Nascimento
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cobranca.parcelamento;

import java.util.ArrayList;

import edu.emory.mathcs.backport.java.util.Collections;
import gcom.cobranca.parcelamento.DescontoValorDebitoPeriodo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de 
 * Percentual Desconto Pagamento à Vista por Valor de Débito
 * 
 * @author Anderson Cabral
 * @created 02/08/2013
 */
public class ExibirInformarPercentualDescPagAVistaDebitoPopupAction  extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Anderson Cabral
	 * @date 02/08/2013
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

        ActionForward retorno = actionMapping.findForward("exibirInformarPercentualDescPagAVistaDebitoPopupAction");
        InformarPercentualDescPagAVistaDebitoPopupActionForm form = (InformarPercentualDescPagAVistaDebitoPopupActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        
        
        if(httpServletRequest.getParameter("adicionar") != null){
        	adicionarDescontoValorDebitoPeriodo(sessao, form);
        }else if(httpServletRequest.getParameter("remover") != null){
			String valorMaximo = httpServletRequest.getParameter("valorMaximoDebito");
			removerDescontoValorDebitoPeriodo(sessao, valorMaximo);
	    	limparForm(form);
		}else if (httpServletRequest.getParameter("limpar") != null){      	
        	limparTela(sessao,form);
        }else if (httpServletRequest.getParameter("fechar") != null){
 
         	if (sessao.getAttribute("UseCase")!= null &&
	    		sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
				httpServletRequest.setAttribute("reloadPage","FECHARINSERIR");
	    	}else{
	    		httpServletRequest.setAttribute("reloadPage","FECHARATUALIZAR");
	    	}
        }else{
        	atualizaFormNaSessao(sessao, httpServletRequest);
        }
        
        return retorno;
    }
    
    private void adicionarDescontoValorDebitoPeriodo(HttpSession sessao, InformarPercentualDescPagAVistaDebitoPopupActionForm form){
    	
    	String valoMaximoDebito   = form.getValorMaximoDebito();
    	String percentualDesconto = form.getPercentualDesconto();
    	String quantidadeMeses 	  = form.getQuantidadeMeses();
    	
    	DescontoValorDebitoPeriodo descontoValorDebitoPeriodo = new DescontoValorDebitoPeriodo();
    	descontoValorDebitoPeriodo.setValorMaximoDebito(Util.formatarMoedaRealparaBigDecimal(valoMaximoDebito));
    	descontoValorDebitoPeriodo.setPercentualDesconto(Util.formatarMoedaRealparaBigDecimal(percentualDesconto));
    	descontoValorDebitoPeriodo.setQtdeMeses(Integer.parseInt(quantidadeMeses));
    	
    	ArrayList<DescontoValorDebitoPeriodo> collectionDescontoValorDebitoPeriodo = (ArrayList<DescontoValorDebitoPeriodo>) sessao.getAttribute("collectionDescontoValorDebitoPeriodo");
    
    	if(collectionDescontoValorDebitoPeriodo == null){
    		collectionDescontoValorDebitoPeriodo = new ArrayList<DescontoValorDebitoPeriodo>();
    	}
    	
    	boolean ValorMaximoDebitoJaExiste = false;
    	for(DescontoValorDebitoPeriodo item : collectionDescontoValorDebitoPeriodo){
    		if(item.getValorMaximoDebito().equals(descontoValorDebitoPeriodo.getValorMaximoDebito())){
    			ValorMaximoDebitoJaExiste = true;
    			break;
    		}
    	}
		
		if(ValorMaximoDebitoJaExiste){
			throw new ActionServletException("atencao.valor_maximo_ja_existe");
		}
    	
    	collectionDescontoValorDebitoPeriodo.add(descontoValorDebitoPeriodo);
    	
    	Collections.sort(collectionDescontoValorDebitoPeriodo);
    	
    	sessao.setAttribute("collectionDescontoValorDebitoPeriodo", collectionDescontoValorDebitoPeriodo);
    	
    	limparForm(form);
    }
    
    private void removerDescontoValorDebitoPeriodo(HttpSession sessao, String valorMaximo){
    	ArrayList<DescontoValorDebitoPeriodo> collectionDescontoValorDebitoPeriodo = (ArrayList<DescontoValorDebitoPeriodo>) sessao.getAttribute("collectionDescontoValorDebitoPeriodo");
    	
    	DescontoValorDebitoPeriodo descontoValorDebitoPeriodoExcluir = null;
    	for(DescontoValorDebitoPeriodo descontoValorDebitoPeriodo : collectionDescontoValorDebitoPeriodo){
    		if(descontoValorDebitoPeriodo.getValorMaximoDebito().toString().replace(",", ".").equals(valorMaximo)){
    			descontoValorDebitoPeriodoExcluir = descontoValorDebitoPeriodo;
    			break;
    		}
    	}
    	
    	if(descontoValorDebitoPeriodoExcluir != null){   	
    		ArrayList<DescontoValorDebitoPeriodo> collectionDescontoValorDebitoPeriodoLinhaRemovidas = null;    	
			if (sessao.getAttribute("collectionDescontoValorDebitoPeriodoLinhaRemovidas") != null
					&& !sessao.getAttribute("collectionDescontoValorDebitoPeriodoLinhaRemovidas").equals("")) {
				collectionDescontoValorDebitoPeriodoLinhaRemovidas = (ArrayList<DescontoValorDebitoPeriodo>) sessao.getAttribute("collectionDescontoValorDebitoPeriodoLinhaRemovidas");
			} else {
				collectionDescontoValorDebitoPeriodoLinhaRemovidas = new ArrayList<DescontoValorDebitoPeriodo>();
			}
			
			collectionDescontoValorDebitoPeriodo.remove(descontoValorDebitoPeriodoExcluir);	
			
			Collections.sort(collectionDescontoValorDebitoPeriodo);
			
			collectionDescontoValorDebitoPeriodoLinhaRemovidas.add(descontoValorDebitoPeriodoExcluir);
			sessao.setAttribute("collectionDescontoValorDebitoPeriodoLinhaRemovidas", collectionDescontoValorDebitoPeriodoLinhaRemovidas);		
    	}
		
		
    }
   
    private void limparTela(HttpSession sessao,	InformarPercentualDescPagAVistaDebitoPopupActionForm form){
    	
    	limparForm(form);
    	
    	sessao.removeAttribute("collectionDescontoValorDebitoPeriodo");
    }
    
    private void limparForm(InformarPercentualDescPagAVistaDebitoPopupActionForm form){
    	form.setValorMaximoDebito("");
    	form.setPercentualDesconto("");
    	form.setQuantidadeMeses("");
    }

    private void atualizaFormNaSessao(HttpSession sessao, HttpServletRequest httpServletRequest){
    	
    	if(sessao.getAttribute("UseCase").equals("INSERIRPERFIL") ){
    		ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm)sessao.getAttribute("ParcelamentoPerfilActionForm");	  		
    		
    		parcelamentoPerfilActionForm.setResolucaoDiretoria(httpServletRequest.getParameter("resolucaoDiretoria"));
    		parcelamentoPerfilActionForm.setImovelSituacaoTipo(httpServletRequest.getParameter("imovelSituacaoTipo"));
    		parcelamentoPerfilActionForm.setImovelPerfil(httpServletRequest.getParameter("imovelPerfil"));
    		parcelamentoPerfilActionForm.setSubcategoria(httpServletRequest.getParameter("subcategoria"));
    		parcelamentoPerfilActionForm.setCategoria(httpServletRequest.getParameter("categoria"));
    		
    		parcelamentoPerfilActionForm.setPercentualDescontoAcrescimo(httpServletRequest.getParameter("percentualDescontoAcrescimo"));
    		parcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao(httpServletRequest.getParameter("percentualTarifaMinimaPrestacao"));
    		parcelamentoPerfilActionForm.setConsumoMinimo(httpServletRequest.getParameter("consumoMinimo"));
    		parcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio(httpServletRequest.getParameter("percentualVariacaoConsumoMedio"));
    		parcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido(httpServletRequest.getParameter("indicadorParcelarChequeDevolvido"));
    		parcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta(httpServletRequest.getParameter("indicadorParcelarSancoesMaisDeUmaConta"));
    		
    		parcelamentoPerfilActionForm.setNumeroConsumoEconomia(httpServletRequest.getParameter("numeroConsumoEconomia"));
			parcelamentoPerfilActionForm.setNumeroAreaConstruida(httpServletRequest.getParameter("numeroAreaConstruida"));    
			parcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial(httpServletRequest.getParameter("indicadorRetroativoTarifaSocial"));
			parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior(httpServletRequest.getParameter("anoMesReferenciaLimiteInferior"));
			parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(httpServletRequest.getParameter("anoMesReferenciaLimiteSuperior"));
			parcelamentoPerfilActionForm.setPercentualDescontoAVista(httpServletRequest.getParameter("percentualDescontoAVista")); 
			parcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura(httpServletRequest.getParameter("parcelaQuantidadeMinimaFatura"));
			parcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima(httpServletRequest.getParameter("indicadorAlertaParcelaMinima"));
			parcelamentoPerfilActionForm.setPercentualDescontoSancao(httpServletRequest.getParameter("percentualDescontoSancao"));
			parcelamentoPerfilActionForm.setQuantidadeEconomias(httpServletRequest.getParameter("quantidadeEconomias"));
			parcelamentoPerfilActionForm.setCapacidadeHidrometro(httpServletRequest.getParameter("capacidadeHidrometro")); 
			parcelamentoPerfilActionForm.setIndicadorEntradaMinima(httpServletRequest.getParameter("indicadorEntradaMinima"));
			parcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(httpServletRequest.getParameter("quantidadeMaximaReparcelamento"));
			parcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(httpServletRequest.getParameter("dataLimiteDescontoPagamentoAVista"));
			
			parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoAcrescimoPagamentoAVista"));
			parcelamentoPerfilActionForm.setIndicadorPercentualDescontoPagAVistaValorDebito(httpServletRequest.getParameter("indicadorPercentualDescontoPagAVistaValorDebito"));
			parcelamentoPerfilActionForm.setIndicadorPercentualDescontoPagParceladoEntrada(httpServletRequest.getParameter("indicadorPercentualDescontoPagParceladoEntrada"));		
			parcelamentoPerfilActionForm.setIdContaMotivoRevisao(httpServletRequest.getParameter("idContaMotivoRevisao"));
			
			parcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoAVista"));
			parcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoParcelado(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoParcelado"));
			parcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoAVista(httpServletRequest.getParameter("limiteVencimentoContaPagamentoAVista"));
			parcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoParcelado(httpServletRequest.getParameter("limiteVencimentoContaPagamentoParcelado"));
    		    		
    		sessao.setAttribute("ParcelamentoPerfilActionForm",parcelamentoPerfilActionForm);
    		
    	}else if(sessao.getAttribute("UseCase").equals("ATUALIZARPERFIL")){
    		AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm)sessao.getAttribute("AtualizarParcelamentoPerfilActionForm");
    		
    		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimo(httpServletRequest.getParameter("percentualDescontoAcrescimo"));
    		atualizarParcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao(httpServletRequest.getParameter("percentualTarifaMinimaPrestacao"));
    		atualizarParcelamentoPerfilActionForm.setConsumoMinimo(httpServletRequest.getParameter("consumoMinimo"));
    		atualizarParcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio(httpServletRequest.getParameter("percentualVariacaoConsumoMedio"));
    		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido(httpServletRequest.getParameter("indicadorParcelarChequeDevolvido"));
    		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta(httpServletRequest.getParameter("indicadorParcelarSancoesMaisDeUmaConta"));
    	
    		atualizarParcelamentoPerfilActionForm.setNumeroConsumoEconomia(httpServletRequest.getParameter("numeroConsumoEconomia"));
			atualizarParcelamentoPerfilActionForm.setNumeroAreaConstruida(httpServletRequest.getParameter("numeroAreaConstruida"));    
			atualizarParcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial(httpServletRequest.getParameter("indicadorRetroativoTarifaSocial"));
			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior(httpServletRequest.getParameter("anoMesReferenciaLimiteInferior"));
			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(httpServletRequest.getParameter("anoMesReferenciaLimiteSuperior"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAVista(httpServletRequest.getParameter("percentualDescontoAVista")); 
			atualizarParcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura(httpServletRequest.getParameter("parcelaQuantidadeMinimaFatura"));
			atualizarParcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima(httpServletRequest.getParameter("indicadorAlertaParcelaMinima"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoSancao(httpServletRequest.getParameter("percentualDescontoSancao"));
			atualizarParcelamentoPerfilActionForm.setQuantidadeEconomias(httpServletRequest.getParameter("quantidadeEconomias"));
			atualizarParcelamentoPerfilActionForm.setCapacidadeHidrometro(httpServletRequest.getParameter("capacidadeHidrometro")); 
			atualizarParcelamentoPerfilActionForm.setIndicadorEntradaMinima(httpServletRequest.getParameter("indicadorEntradaMinima"));
			atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(httpServletRequest.getParameter("quantidadeMaximaReparcelamento"));
			atualizarParcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(httpServletRequest.getParameter("dataLimiteDescontoPagamentoAVista"));
			
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoAcrescimoPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setIndicadorPercentualDescontoPagAVistaValorDebito(httpServletRequest.getParameter("indicadorPercentualDescontoPagAVistaValorDebito"));
			atualizarParcelamentoPerfilActionForm.setIndicadorPercentualDescontoPagParceladoEntrada(httpServletRequest.getParameter("indicadorPercentualDescontoPagParceladoEntrada"));			
			atualizarParcelamentoPerfilActionForm.setIdContaMotivoRevisao(httpServletRequest.getParameter("idContaMotivoRevisao"));
			
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoParcelado(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoParcelado"));
			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoAVista(httpServletRequest.getParameter("limiteVencimentoContaPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoParcelado(httpServletRequest.getParameter("limiteVencimentoContaPagamentoParcelado"));
			
			sessao.setAttribute("AtualizarParcelamentoPerfilActionForm",atualizarParcelamentoPerfilActionForm);
    	}
    	
    }
}
