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

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.DescontoValorDebitoPeriodo;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelaEntradaDesconto;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por inserir um Perfil de Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 02/05/2006
 */

public class InserirPerfilParcelamentoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    //Obtém a instância da fachada
    Fachada fachada = Fachada.getInstancia();

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        HttpSession sessao = httpServletRequest.getSession(false);
        ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm)actionForm;
        
        String idResolucaoDiretoria = parcelamentoPerfilActionForm.getResolucaoDiretoria();
        String idImovelSituacaoTipo = parcelamentoPerfilActionForm.getImovelSituacaoTipo();
		
        String idImovelPerfil = null;
        if (parcelamentoPerfilActionForm.getImovelPerfil() != null &&
        		!parcelamentoPerfilActionForm.getImovelPerfil().equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idImovelPerfil = parcelamentoPerfilActionForm.getImovelPerfil();	
        }
        
        String idSubcategoria = null;
        if (parcelamentoPerfilActionForm.getSubcategoria() != null &&
        		!parcelamentoPerfilActionForm.getSubcategoria().equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idSubcategoria = parcelamentoPerfilActionForm.getSubcategoria();
        }
        
        String idCategoria = null;
        if (parcelamentoPerfilActionForm.getCategoria() != null &&
        		!parcelamentoPerfilActionForm.getCategoria().equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idCategoria = parcelamentoPerfilActionForm.getCategoria();
        }
		
		String percentualDescontoAcrescimo = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo().equalsIgnoreCase("")){
			percentualDescontoAcrescimo = parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo().toString().
			replace(",", ".");
		}
		
		String percentualDescontoAcrescimoPagamentoAVista = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista() != null
			&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista().equalsIgnoreCase("")){
			percentualDescontoAcrescimoPagamentoAVista = parcelamentoPerfilActionForm
			.getPercentualDescontoAcrescimoPagamentoAVista().toString().replace(",", ".");
		}
		
		String percentualTarifaMinimaPrestacao = null ;
		if (parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao() != null
				&& !parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase("")){
			percentualTarifaMinimaPrestacao = parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().toString().
			replace(",", ".");
		}
		
		String valorFixoParcelaMinima = null;
		if (parcelamentoPerfilActionForm.getValorFixoParcelaMinima() != null
				&& !parcelamentoPerfilActionForm.getValorFixoParcelaMinima().equalsIgnoreCase("")){
			valorFixoParcelaMinima = parcelamentoPerfilActionForm.getValorFixoParcelaMinima().toString().replace(",", ".");
		}
		
		String percentualDescontoDebitoPagamentoAVista = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista() != null 
				&& !parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista().equals("")){
			percentualDescontoDebitoPagamentoAVista = parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista().toString().replace(",", ".");
			
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoAVista);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "Desconto Debito Pagamento A Vista");
			}
		}
		
		String percentualDescontoDebitoPagamentoParcelado = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado().equals("")){
			percentualDescontoDebitoPagamentoParcelado = parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado().toString().replace(",", ".");
			
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoParcelado);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "Desconto Debito Pagamento Parcelado");
			}
		}
		
		String qtdFaturasAnterioresPagVista = null;
		if(parcelamentoPerfilActionForm.getQtdFaturasAnterioresPagVista() != null 
				&& !parcelamentoPerfilActionForm.getQtdFaturasAnterioresPagVista().equals("")){
			qtdFaturasAnterioresPagVista = parcelamentoPerfilActionForm.getQtdFaturasAnterioresPagVista().toString();
		}
		
		String qtdFaturasAnterioresPagParcelado = null;
		if(parcelamentoPerfilActionForm.getQtdFaturasAnterioresPagParcelado() != null 
				&& !parcelamentoPerfilActionForm.getQtdFaturasAnterioresPagParcelado().equals("")){
			qtdFaturasAnterioresPagParcelado = parcelamentoPerfilActionForm.getQtdFaturasAnterioresPagParcelado().toString();
		}
		
		String limiteVencimentoContaPagamentoAVista = null;
		if(parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista() != null
				&& !parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista().equals("")){
			limiteVencimentoContaPagamentoAVista = parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista();
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());
			
			Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataInicial);
			Integer dataLimiteVencimento = Util.formatarDiaMesAnoComBarraParaAnoMesDia(limiteVencimentoContaPagamentoAVista);
			
			if(dataLimiteVencimento < dataAtual){
				throw new ActionServletException("atencao.data_inferior_que_atual", null, "Limite Vencimento Conta Pagamento A Vista");
			}
		}
		
		String limiteVencimentoContaPagamentoParcelado = null;
		if(parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado() != null
				&& !parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado().equals("")){
			limiteVencimentoContaPagamentoParcelado = parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado();
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());
			
			Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataInicial);
			Integer dataLimiteVencimento = Util.formatarDiaMesAnoComBarraParaAnoMesDia(limiteVencimentoContaPagamentoParcelado);
			
			if(dataLimiteVencimento < dataAtual){
				throw new ActionServletException("atencao.data_inferior_que_atual", null, "Limite Vencimento Conta Pagamento Parcelado");
			}
		}
		
		if(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior() != null 
			&& !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior().equals("")){
			if(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior() != null 
				&& !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior().equals("")){
				
				Integer anoMesInferior = Util.formatarMesAnoComBarraParaAnoMes(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior());
				Integer anoMesSuperior = Util.formatarMesAnoComBarraParaAnoMes(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior());
				
				if(anoMesInferior > anoMesSuperior){
					throw new ActionServletException("atencao.mes_ano_inicial_maior_mes_ano_final");
				}
			}
		}
             
        atualizaColecoesNaSessao(sessao,httpServletRequest);
        
    	//collectionParcelamentoQuantidadeReparcelamentoHelper
		Collection collectionParcelamentoQuantidadeReparcelamentoHelper = null;	       
        if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null) {
        	collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
                    .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
        }
        
    	//collectionParcelamentoDescontoInatividade
		Collection collectionParcelamentoDescontoInatividade = null;	       
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
        	collectionParcelamentoDescontoInatividade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividade");
        }
        
    	//collectionParcelamentoDescontoAntiguidade
		Collection collectionParcelamentoDescontoAntiguidade = null;	       
        if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
        	collectionParcelamentoDescontoAntiguidade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoAntiguidade");
        }
        
        //collectionParcelamentoDescontoInatividade
		Collection collectionParcelamentoDescontoInatividadeAVista = null;	       
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
        	collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        }
        
        
    	ArrayList<DescontoValorDebitoPeriodo> collectionDescontoValorDebitoPeriodo = null;
        if (sessao.getAttribute("collectionDescontoValorDebitoPeriodo") != null) {
        	collectionDescontoValorDebitoPeriodo = (ArrayList<DescontoValorDebitoPeriodo>) 
        			sessao.getAttribute("collectionDescontoValorDebitoPeriodo");
        }
        
        validacaoFinal( idResolucaoDiretoria,idImovelSituacaoTipo,idImovelPerfil ,
        		idSubcategoria ,percentualDescontoAcrescimo ,httpServletRequest,
        		collectionParcelamentoQuantidadeReparcelamentoHelper,
        		collectionParcelamentoDescontoAntiguidade,
			    collectionParcelamentoDescontoInatividade,
			    percentualTarifaMinimaPrestacao,
			    valorFixoParcelaMinima,
			    collectionParcelamentoDescontoInatividadeAVista,
			    collectionDescontoValorDebitoPeriodo,
			    parcelamentoPerfilActionForm);
        
        
        ParcelamentoPerfil parcelamentoPerfilNova =  new ParcelamentoPerfil();
        
        ResolucaoDiretoria resolucaoDiretoria = null;
        if(idResolucaoDiretoria != null && !idResolucaoDiretoria.equals("")){
        	resolucaoDiretoria = new ResolucaoDiretoria();
        	resolucaoDiretoria.setId(new Integer (idResolucaoDiretoria));
        	parcelamentoPerfilNova.setResolucaoDiretoria(resolucaoDiretoria);
		}
        
        ImovelSituacaoTipo imovelSituacaoTipo = null;
        if(idImovelSituacaoTipo != null && !idImovelSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	imovelSituacaoTipo = new ImovelSituacaoTipo();
        	imovelSituacaoTipo.setId(new Integer (idImovelSituacaoTipo));
        	parcelamentoPerfilNova.setImovelSituacaoTipo(imovelSituacaoTipo);
		}

        ImovelPerfil imovelPerfil = null;
        if(idImovelPerfil != null && !idImovelPerfil.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	imovelPerfil = new ImovelPerfil();
        	imovelPerfil.setId(new Integer (idImovelPerfil));
		}
        parcelamentoPerfilNova.setImovelPerfil(imovelPerfil);
        
        Subcategoria subcategoria = null;
        if(idSubcategoria != null && !idSubcategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	subcategoria = new Subcategoria();
        	subcategoria.setId(new Integer (idSubcategoria));
		}
        parcelamentoPerfilNova.setSubcategoria(subcategoria);
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo().equalsIgnoreCase("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimo(percentual);	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimo(new BigDecimal(0));
        }
        
        if (percentualDescontoAcrescimoPagamentoAVista != null){
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoPagamentoAVista);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVista(percentual);	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVista(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagAVistaValorDebito() != null
        		 && !parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagAVistaValorDebito().equals("")){
        	parcelamentoPerfilNova.setIndicadorDescontoVistaValorDebito(new Short(parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagAVistaValorDebito()));
        }else{
        	throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "O percentual de desconto a ser utilizado para pagamento à vista leva em consideração o valor do débito no período");
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagParceladoEntrada() != null
       		 && !parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagParceladoEntrada().equals("")){
        	parcelamentoPerfilNova.setIndicadorDescontoParcelaValorEntrada(new Short(parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagParceladoEntrada()));
        }else{
        	throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "O percentual de desconto para pagamento parcelado levará em consideração o percentual da entrada?");
        }
                
        if (parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao() != null
				&& !parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualTarifaMinimaPrestacao(percentual);	
        }else{
        	parcelamentoPerfilNova.setPercentualTarifaMinimaPrestacao(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getValorFixoParcelaMinima() != null
				&& !parcelamentoPerfilActionForm.getValorFixoParcelaMinima().equalsIgnoreCase("")){
        	
        	BigDecimal valor = Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getValorFixoParcelaMinima());      	
        	parcelamentoPerfilNova.setValorFixoParcelaMinima(valor);
        	
        }else{
        	parcelamentoPerfilNova.setValorFixoParcelaMinima(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido() != null
         		 && !parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido().equals("")){
         	parcelamentoPerfilNova.setIndicadorChequeDevolvido(new Short(parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido()));
       }else{
   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Não parcelar com cheque devolvido");
       }
        
        if (parcelamentoPerfilActionForm.getConsumoMinimo() != null && !parcelamentoPerfilActionForm.getConsumoMinimo().equals("")){
        	parcelamentoPerfilNova.setNumeroConsumoMinimo(new Integer(parcelamentoPerfilActionForm.getConsumoMinimo()));
        }
        
        if (parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio() != null && !parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualVariacaoConsumoMedio(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta() != null 
        	&& !parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta().equals("")){
          	parcelamentoPerfilNova.setIndicadorSancoesUnicaConta(new Short(parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta()));
        }else{
    		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Não parcelar com sanções em mais de uma conta");
        }
        
        /////////////////////////////////////////////
        Categoria categoria = null;
        if(idCategoria != null && !idCategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	categoria = new Categoria();
        	categoria.setId(new Integer (idCategoria));
		}
        parcelamentoPerfilNova.setCategoria(categoria);
        
        if (parcelamentoPerfilActionForm.getNumeroConsumoEconomia() != null 
        	&& !parcelamentoPerfilActionForm.getNumeroConsumoEconomia().trim().equals("")){
        	parcelamentoPerfilNova.setNumeroConsumoEconomia(new Integer(parcelamentoPerfilActionForm.getNumeroConsumoEconomia()));
        }
        
        if (parcelamentoPerfilActionForm.getNumeroAreaConstruida() != null 
        		&& !parcelamentoPerfilActionForm.getNumeroAreaConstruida().trim().equals("")){
        	parcelamentoPerfilNova.setNumeroAreaConstruida(Util.formatarMoedaRealparaBigDecimal(
        		parcelamentoPerfilActionForm.getNumeroAreaConstruida()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial() != null
         		 && !parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial().equals("")){
         	parcelamentoPerfilNova.setIndicadorRetroativoTarifaSocial(
         		new Short(parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial()));
        }else{
	   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de retroativo de tarifa social");
        }
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior() != null 
        		&& !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteInferior(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior())));
        } 
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior() != null && 
        		!parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteSuperior(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior())));
        } 
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoAVista() != null && 
        		!parcelamentoPerfilActionForm.getPercentualDescontoAVista().trim().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(
        			parcelamentoPerfilActionForm.getPercentualDescontoAVista());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoAVista(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura() != null && 
        		!parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura().trim().equals("")){
        	parcelamentoPerfilNova.setParcelaQuantidadeMinimaFatura(
        			new Integer(parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima() != null
        		 && !parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima().equals("")){
        	parcelamentoPerfilNova.setIndicadorAlertaParcelaMinima(
        			new Short(parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima()));
        }else{
	   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de alerta de parcela mínima");
        }
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoSancao() != null && 
        		!parcelamentoPerfilActionForm.getPercentualDescontoSancao().trim().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(
        			parcelamentoPerfilActionForm.getPercentualDescontoSancao());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoSancao(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getQuantidadeEconomias()  != null && 
        		!parcelamentoPerfilActionForm.getQuantidadeEconomias().trim().equals("")){
        	parcelamentoPerfilNova.setQuantidadeEconomias(new Integer(parcelamentoPerfilActionForm.getQuantidadeEconomias()));
        }
        
        if (parcelamentoPerfilActionForm.getCapacidadeHidrometro()  != null && 
        		!parcelamentoPerfilActionForm.getCapacidadeHidrometro().trim().equals("")){
        	parcelamentoPerfilNova.setCapacidadeHidrometro(
        			new Short(parcelamentoPerfilActionForm.getCapacidadeHidrometro()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorEntradaMinima() != null
        		&& !parcelamentoPerfilActionForm.getIndicadorEntradaMinima().equals("")){
        	parcelamentoPerfilNova.setIndicadorEntradaMinima(
        			new Short(parcelamentoPerfilActionForm.getIndicadorEntradaMinima()));
       }else{
	   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de entrada mínima");
       }
        
       if (parcelamentoPerfilActionForm.getIndicadorDescontoVistaDebParcelado() != null
        		&& !parcelamentoPerfilActionForm.getIndicadorDescontoVistaDebParcelado().equals("")){
        	parcelamentoPerfilNova.setIndicadorDescontoVistaDebParcelado(
        			new Short(parcelamentoPerfilActionForm.getIndicadorDescontoVistaDebParcelado()));
       }
       
        if (parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento()  != null && 
        		!parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento().trim().equals("")){
        	parcelamentoPerfilNova.setQuantidadeMaximaReparcelamento(
        			new Integer(parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento()));
        }

        if (parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()  != null && 
        		!parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista().trim().equals("")){
        	parcelamentoPerfilNova.setDataLimiteDescontoPagamentoAVista(
        			Util.converteStringParaDate(parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()));
        }
        
        //Inclusão de novos parâmetros
        if(percentualDescontoDebitoPagamentoAVista != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoAVista);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoAVista(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoAVista(new BigDecimal(0));
        }
        
        if(percentualDescontoDebitoPagamentoParcelado != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoParcelado);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoParcelado(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoParcelado(new BigDecimal(0));
        }
        
        if(qtdFaturasAnterioresPagVista != null){
        	parcelamentoPerfilNova.setQtdFaturasAnterioresPagVista(Integer.parseInt(qtdFaturasAnterioresPagVista));
        }else{
        	parcelamentoPerfilNova.setQtdFaturasAnterioresPagVista(0);
        }
        
        if(qtdFaturasAnterioresPagParcelado != null){
        	parcelamentoPerfilNova.setQtdFaturasAnterioresPagParcelado(Integer.parseInt(qtdFaturasAnterioresPagParcelado));
        }else{
        	parcelamentoPerfilNova.setQtdFaturasAnterioresPagParcelado(0);
        }
        
        if(limiteVencimentoContaPagamentoAVista != null){
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaAVista(Util.converteStringParaDate(limiteVencimentoContaPagamentoAVista));
        }else{
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaAVista(null);
        }
        
        if(limiteVencimentoContaPagamentoParcelado != null){
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaParcelado(Util.converteStringParaDate(limiteVencimentoContaPagamentoParcelado));
        }else{
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaParcelado(null);
        }
        
        parcelamentoPerfilNova.setUltimaAlteracao(new Date());
        
        Integer idPerfilParcelamento = fachada.inserirPerfilParcelamento(parcelamentoPerfilNova,
        		collectionParcelamentoQuantidadeReparcelamentoHelper,
        		collectionParcelamentoDescontoInatividade,
        		collectionParcelamentoDescontoAntiguidade,
        		this.getUsuarioLogado(httpServletRequest),
        		collectionParcelamentoDescontoInatividadeAVista,
        		collectionDescontoValorDebitoPeriodo);
        
    	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
    	sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
    	sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
    	sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
    	sessao.removeAttribute("collectionDescontoValorDebitoPeriodo");
    	
    	FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
		filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
				FiltroResolucaoDiretoria.CODIGO,idResolucaoDiretoria));
        Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        String numeroResolucaoDiretoria = ((ResolucaoDiretoria)Util.retonarObjetoDeColecao(collectionResolucaoDiretoria)).getNumeroResolucaoDiretoria();
    	
		montarPaginaSucesso(httpServletRequest, "Perfil de Parcelamento da RD de número " + numeroResolucaoDiretoria + " inserido com sucesso.",
				"Inserir outro Perfil de Parcelamento",
                "exibirInserirPerfilParcelamentoAction.do?desfazer=S",
				"exibirAtualizarPerfilParcelamentoAction.do?idRegistroInseridoAtualizar="
						+ idPerfilParcelamento,
				"Atualizar Perfil de Parcelamento Inserido");
        
        //devolve o mapeamento de retorno
        return retorno;

    }
    
    private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){

		// collectionParcelamentoDescontoAntiguidade
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoAntiguidade").equals("")) {
		
			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
				.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoDescontoAntiguidade
			String vlSemRestAntiguidade = null;
			String vlComRestAntiguidade = null;
			String vlDescontoAtivo = null;
			
			
			Iterator iteratorParcelamentoDescontoAntiguidade = 
				collectionParcelamentoDescontoAntiguidade.iterator();
			
			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {
				
				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade
					.next();
				long valorTempo = parcelamentoDescontoAntiguidade.getUltimaAlteracao()
					.getTime();
				vlSemRestAntiguidade = (String) httpServletRequest.getParameter("vlSemRestAntiguidade"
					+ valorTempo);
				vlComRestAntiguidade = httpServletRequest.getParameter("vlComRestAntiguidade"
					+ valorTempo);
				vlDescontoAtivo = httpServletRequest.getParameter("vlDescontoAtivo"
					+ valorTempo);
				
				// inseri essas variáveis no objeto ParcelamentoDescontoAntiguidade
				BigDecimal percentualDescontoSemRestabelecimentoAntiguidade  = null;
				if (vlSemRestAntiguidade != null && !vlSemRestAntiguidade.equals("")) {
					percentualDescontoSemRestabelecimentoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
					verificarPercentualMaximo(percentualDescontoSemRestabelecimentoAntiguidade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null && !vlComRestAntiguidade.equals("")) {
					percentualDescontoComRestabelecimentoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
					verificarPercentualMaximo(percentualDescontoComRestabelecimentoAntiguidade);
				}
				
				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null && !vlDescontoAtivo.equals("")) {
					percentualDescontoAtivoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
					verificarPercentualMaximo(percentualDescontoAtivoAntiguidade);
				}
				
				parcelamentoDescontoAntiguidade.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);
			}
		}
		
		//collectionParcelamentoDescontoInatividade
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals("")) {
		
			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividade");
			
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;
			
			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
			
				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = 
				(ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao()
					.getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividade"
					+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividade"
					+ valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
					verificarPercentualMaximo(percentualDescontoSemRestabelecimentoInatividade);
				}
			
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
					verificarPercentualMaximo(percentualDescontoComRestabelecimentoInatividade);
				}
				
				parcelamentoDescontoInatividade.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);
			}
		}	        	
		
		//collectionParcelamentoQuantidadeReparcelamentoHelper
		/*
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null
		&& !sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper").equals(
			"")) {
		
			Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
				.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoQuantidadeReparcelamentoHelper
			String vlMinPrest = null;
			
			Iterator iteratorParcelamentoQuantidadeReparcelamentoHelper = 
				collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
			
			while (iteratorParcelamentoQuantidadeReparcelamentoHelper.hasNext()) {
				ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = 
					(ParcelamentoQuantidadeReparcelamentoHelper) iteratorParcelamentoQuantidadeReparcelamentoHelper
					.next();
				long valorTempo = parcelamentoQuantidadeReparcelamentoHelper.getUltimaAlteracao()
					.getTime();
				vlMinPrest = (String) httpServletRequest.getParameter("vlMinPrest"
					+ valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoQuantidadeReparcelamentoHelper
				BigDecimal valorMinimoPrestacao  = null;
				if (vlMinPrest != null 
					&& !vlMinPrest.equals("")) {
				valorMinimoPrestacao = Util.formatarMoedaRealparaBigDecimal(vlMinPrest);
				}
				
				parcelamentoQuantidadeReparcelamentoHelper.setValorMinimoPrestacao(valorMinimoPrestacao);
				
			}
		}	*/
    }

    private void validacaoFinal(String numeroResolucaoDiretoria,
    							String imovelSituacaoTipo,
								String imovelPerfil ,
								String subcategoria ,
								String percentualDescontoAcrescimo ,
							    HttpServletRequest httpServletRequest,
							    Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
							    Collection collectionParcelamentoDescontoAntiguidade,
							    Collection collectionParcelamentoDescontoInatividade,
							    String percentualTarifaMinimaPrestacao,
							    String valorFixoParcelaMinima,
							    Collection collectionParcelamentoDescontoInatividadeAVista,
							    ArrayList<DescontoValorDebitoPeriodo> collectionDescontoValorDebitoPeriodo,
							    ParcelamentoPerfilActionForm form){
							    	
    	if ( !Util.verificarNaoVazio(numeroResolucaoDiretoria)){
    		httpServletRequest.setAttribute("nomeCampo","numeroResolucaoDiretoria");
    		// Informe Numero da RD.
    		throw new ActionServletException("atencao.numero_rd_nao_informado");
    	}
    	
		if ((imovelSituacaoTipo == null)
				|| (imovelSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","imovelSituacaoTipo");
			//Informe Tipo da Situação do Imóvel
			throw new ActionServletException("atencao.tipo_situacao_imovel_nao_informado");
		}
		/*
		if ((imovelPerfil == null)
				|| (imovelPerfil.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","imovelPerfil");
			//Informe Perfil do Imóvel
			throw new ActionServletException("atencao.perfil_imovel_nao_informado");
		}
		
		if ((subcategoria == null)
				|| (subcategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","subcategoria");
			//Informe Subcategoria
			throw new ActionServletException("atencao.subcategoria_nao_informado");
		}
		
    	if (percentualDescontoAcrescimo == null ||
    			percentualDescontoAcrescimo.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimo");
    		//Informe Percentual de Desconto sobre os Acréscimos  por Impontualidade
    		throw new ActionServletException("atencao.percentual_desconto_nao_informado");
    	}
    	*/
		
		if ((percentualTarifaMinimaPrestacao == null || percentualTarifaMinimaPrestacao.equalsIgnoreCase(""))
				&& (valorFixoParcelaMinima == null || valorFixoParcelaMinima.equalsIgnoreCase(""))){
			
    		httpServletRequest.setAttribute("nomeCampo","percentualTarifaMinimaPrestacao");
    		//Informe  Percentual da Tarifa Mínima para Cálculo do Valor Mínimo da Prestação ou Valor Fixo Parcela Mínima
    		throw new ActionServletException("atencao.required", null, " Percentual da Tarifa Mínima para Cálculo do Valor Mínimo da Prestação OU Valor Fixo Parcela Mínima ");
    	}
		
    	// [FS0008]Verificar existência do perfil de parcelamento
    	FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.RESOLUCAO_DIRETORIA_ID, numeroResolucaoDiretoria));
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.IMOVEL_SITUACAO_TIPO_ID, imovelSituacaoTipo));
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
    	
    	if (imovelPerfil == null){
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroNulo(FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID));	
    	}else{
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID, imovelPerfil));
    	}
    
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
    	if (subcategoria == null){
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroNulo(FiltroParcelamentoPerfil.SUBCATEGORIA_ID));
    	}else{
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.SUBCATEGORIA_ID, subcategoria));
    	}
    	
		Collection	colecaoParcelamentoPerfil = fachada.pesquisar(filtroParcelamentoPerfil,	ParcelamentoPerfil.class.getName());

		if (colecaoParcelamentoPerfil != null && !colecaoParcelamentoPerfil.isEmpty()) {
			throw new ActionServletException("atencao.perfil_parcelamento_ja_existe");
		}
		
		if(form.getIndicadorPercentualDescontoPagAVistaValorDebito().equals(ConstantesSistema.SIM.toString()) 
				&& Util.isVazioOrNulo(collectionDescontoValorDebitoPeriodo)){
			throw new ActionServletException("atencao.campo.informado", null, "Valores de Desconto");
		}
		
		boolean  informadoParcelaEntradaDesconto = false;
		if (collectionParcelamentoQuantidadeReparcelamentoHelper == null ||
				collectionParcelamentoQuantidadeReparcelamentoHelper.isEmpty()){
			//Informe os dados de Reparcelamento Consecutivo
			throw new ActionServletException("atencao.required", null, "Reparcelamento Consecutivo");
		}else{
			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();

			 while (iterator.hasNext()) {
				
				 ParcelamentoQuantidadeReparcelamentoHelper  parcelamentoQuantidadeReparcelamentoHelper = 
					 			(ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
				/*
				 if (parcelamentoQuantidadeReparcelamentoHelper.getValorMinimoPrestacao() == null){
					 //Informe Valor Mínimo da Prestação	
					 throw new ActionServletException(
								"atencao.required", null, "Valor Mínimo da Prestação");	
				 }*/
				 
				Collection<ParcelaEntradaDesconto> collectionParcelaEntradaDesconto = parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelaEntradaDesconto();
			
				if (collectionParcelaEntradaDesconto != null &&	!collectionParcelaEntradaDesconto.isEmpty()){
					informadoParcelaEntradaDesconto = true;
				}
				 
				Collection collectionParcelamentoQuantidadePrestacaoHelper = 
					parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelamentoQuantidadePrestacaoHelper();
				 
				if (collectionParcelamentoQuantidadePrestacaoHelper == null ||
						collectionParcelamentoQuantidadePrestacaoHelper.isEmpty()){
					//Informações do Parcelamento por Quantidade de Reparcelamentos deve ser informado
					throw new ActionServletException("atencao.campo.informado", null, "Informações do Parcelamento por Quantidade de Reparcelamentos");
				}
			 }
		}
		
		if(form.getIndicadorPercentualDescontoPagParceladoEntrada().equals(ConstantesSistema.SIM.toString()) 
				&& !informadoParcelaEntradaDesconto){
			//Informações de Desconto da parcela de Entrada deve ser informada
			throw new ActionServletException("atencao.campo.informado", null, "Informações do Percentual de Desconto por Percentual de Entrada");
		}
		
		//filtro para descobrir o percentual máximo de desconto permitido para financiamento
		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametros;

			colecaoSistemaParametros = fachada.pesquisar(
					filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();
		BigDecimal percentualMaximoAbatimentoPermitido = sistemaParametro.getPercentualMaximoAbatimento();*/
		
		if (collectionParcelamentoDescontoAntiguidade != null &&
				!collectionParcelamentoDescontoAntiguidade.isEmpty()){
			
			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade.iterator();

			 while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {
				
				 ParcelamentoDescontoAntiguidade  parcelamentoDescontoAntiguidade = 
					 (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade.next();
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento() == null){
					 //Percentual de Desconto Sem Restabelecimento
						throw new ActionServletException("atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
				 }else{
						//[FS0006]Verificar percentual de desconto
						//BigDecimal percentualDescontoSemRestabelecimento = parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento();
						/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0 ){
							//Percentual de Desconto Sem Restabelecimento é superior ao 
							//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
							throw new ActionServletException(
							"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
						}	*/
				}
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento() == null){
					 //Informe  Percentual de Desconto Com Restabelecimento
						throw new ActionServletException("atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
				 }else{
						//[FS0006]Verificar percentual de desconto
						//BigDecimal percentualDescontoComRestabelecimento = parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento();
						/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
							//Percentual de Desconto Cem Restabelecimento é superior ao 
							//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
							throw new ActionServletException(
							"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
						}*/	
				}
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo() == null){
						throw new ActionServletException(
								// Informe  Percentual de Desconto Ativo
								"atencao.required", null, "  Percentual de Desconto Ativo");	
				 }else{
						//[FS0006]Verificar percentual de desconto
						//BigDecimal percentualDescontoAtivo = parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo();
						/*if (percentualDescontoAtivo.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
							//Percentual de Desconto Ativo é superior ao 
							//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
							throw new ActionServletException(
							"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
						}	*/
				 }
				
			 }
			
		}

		 if (collectionParcelamentoDescontoInatividade != null &&
					!collectionParcelamentoDescontoInatividade.isEmpty()){
				
				Iterator iteratorParcelamentoDescontoInatividade = 
					collectionParcelamentoDescontoInatividade.iterator();
	
				 while (iteratorParcelamentoDescontoInatividade.hasNext()) {
					
					 ParcelamentoDescontoInatividade  parcelamentoDescontoInatividade = 
						 (ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade.next();
					 
					 if (parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento() == null){
							throw new ActionServletException(
									//  Percentual de Desconto Sem Restabelecimento
									"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
					 }//else{
							//[FS0006]Verificar percentual de desconto
							//BigDecimal percentualDescontoSemRestabelecimento = parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento();
							/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
								//Percentual de Desconto Sem Restabelecimento é superior ao 
								//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
								throw new ActionServletException(
								"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
							}	*/
					// }
					 
					 if (parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento() == null){
							throw new ActionServletException(
									// Informe  Percentual de Desconto Com Restabelecimento
									"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
					 }//else{
							//[FS0006]Verificar percentual de desconto
							//BigDecimal percentualDescontoComRestabelecimento = parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento();
							/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
								//Percentual de Desconto Cem Restabelecimento é superior ao 
								//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
								throw new ActionServletException(
								"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
							}*/	
					// }
					
				 }
		}
		 
		 if (collectionParcelamentoDescontoInatividadeAVista != null &&
					!collectionParcelamentoDescontoInatividadeAVista.isEmpty()){
				
			Iterator iteratorParcelamentoDescontoInatividade = 
				collectionParcelamentoDescontoInatividadeAVista.iterator();

			 while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				
				 ParcDesctoInativVista  parcelamentoDescontoInatividade = 
					 (ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				 
				 if (parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento() == null){
						throw new ActionServletException(
								//  Percentual de Desconto Sem Restabelecimento
								"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
				 }
				 
				 if (parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento() == null){
						throw new ActionServletException(
								// Informe  Percentual de Desconto Com Restabelecimento
								"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
				 }
				
			 }
		}
		 
		 
		
    }
    
    //[FS0010]-Verificar Percentual Máximo
    private void verificarPercentualMaximo(BigDecimal percentual){
    	
    	BigDecimal percentualMaximo = new BigDecimal("100");
    	
    	if (percentual.compareTo(percentualMaximo) == 1){
    		throw new ActionServletException(
					"atencao.percentual_maior_percentual_maximo");	
    	}
    	
    }
    

}
