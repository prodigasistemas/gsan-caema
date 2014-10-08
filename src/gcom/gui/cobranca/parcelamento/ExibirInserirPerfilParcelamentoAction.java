/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de inserir perfil de parcelamento
 * 
 * @author Vivianne Sousa
 * @created 02/05/2006
 */
public class ExibirInserirPerfilParcelamentoAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclus�o de um novo perfil de parcelamento
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
    //Obt�m a inst�ncia da fachada
    Fachada fachada = Fachada.getInstancia();

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("inserirPerfilParcelamento");
        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm) actionForm;
        
        if(parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagAVistaValorDebito() == null){
        	  //PercentualDescontoPagAVistaValorDebito - exibir com a op��o NAO selecionada
         	  parcelamentoPerfilActionForm.setIndicadorPercentualDescontoPagAVistaValorDebito("2");
        }
        
        if(parcelamentoPerfilActionForm.getIndicadorPercentualDescontoPagParceladoEntrada() == null){
      	  //PercentualDescontoPagParceladoEntrada - exibir com a op��o NAO selecionada
       	  parcelamentoPerfilActionForm.setIndicadorPercentualDescontoPagParceladoEntrada("2");
        }
        
        if(parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido() == null){
      	  //ParcelarChequeDevolvido - exibir com a op��o SIM selecionada
       	  parcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido("1");
        }
               
        if(parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial() == null){
          //RetroativoTarifaSocial - exibir com a op��o N�O selecionada
          parcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial("2");
        }
               
       if(parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima() == null){
          //AlertaParcelaMinima - exibir com a op��o N�O selecionada
          parcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima("2");
        }
               
       if(parcelamentoPerfilActionForm.getIndicadorEntradaMinima() == null){
         //EntradaMinima - exibir com a op��o N�O selecionada
         	parcelamentoPerfilActionForm.setIndicadorEntradaMinima("2");
       }
            
       if(parcelamentoPerfilActionForm.getCapacidadeHidrometro() == null){
    	   //CapacidadeHidrometro - exibir com a op��o N�O selecionada
      	parcelamentoPerfilActionForm.setCapacidadeHidrometro("2");
       }
       
       if(parcelamentoPerfilActionForm.getIndicadorDescontoVistaDebParcelado() == null){
    	   //IndicadorDescontoVistaDebParcelado - exibir com a op��o N�O selecionada
      	parcelamentoPerfilActionForm.setIndicadorDescontoVistaDebParcelado("2");
       }
               
       if(parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta() == null){
         //ParcelarSancoesMaisDeUmaConta - exibir com a op��o SIM selecionada
           parcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta("1");
       }
        
        FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
        
		Collection colecaoSistemaParametros = fachada.pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();
		String  percentualMaximoAbatimento = "" + sistemaParametro.getPercentualMaximoAbatimento();
		httpServletRequest.setAttribute("percentualMaximoAbatimento",percentualMaximoAbatimento);
	
        //Pesquisando Resolucao Diretoria
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
		filtroResolucaoDiretoria.setCampoOrderBy(FiltroResolucaoDiretoria.NUMERO);
        Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        sessao.setAttribute("collectionResolucaoDiretoria", collectionResolucaoDiretoria);
        //Fim de pesquisando Resolucao Diretoria
        
        //Pesquisando Tipo da Situacao do Im�vel
        FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();
        filtroImovelSituacaoTipo.setCampoOrderBy(FiltroImovelSituacaoTipo.DESCRICAO_IMOVEL_SITUACAO_TIPO);
        Collection<ImovelSituacaoTipo> collectionImovelSituacaoTipo = fachada.pesquisar(filtroImovelSituacaoTipo, ImovelSituacaoTipo.class.getName());
        sessao.setAttribute("collectionImovelSituacaoTipo", collectionImovelSituacaoTipo);
        //Fim de pesquisando Tipo da Situacao do Im�vel

        //Pesquisando Perfil do Im�vel
        FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        filtroImovelPerfil.adicionarParametro(new ParametroSimples(
        		FiltroImovelPerfil.INDICADOR_USO, new Short("1")));
        filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        Collection<ImovelPerfil> collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
        //Fim de pesquisando Perfil do Im�vel
        
        //Pesquisando SubCategoria
        FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
        filtroSubCategoria.adicionarParametro(new ParametroSimples(
        		FiltroSubCategoria.INDICADOR_USO, new Short("1")));
        filtroSubCategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
        Collection<Subcategoria> collectionSubcategoria = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
        sessao.setAttribute("collectionSubcategoria", collectionSubcategoria);
        //Fim de pesquisando SubCategoria
        
        //Pesquisando Categoria
        FiltroCategoria filtroCategoria = new FiltroCategoria();
        filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, new Short("1")));
        filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
        Collection<Categoria> collectionCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
        sessao.setAttribute("collectionCategoria", collectionCategoria);
        //Fim de pesquisando Categoria
        
        //Pesquisando ContaMotivoRevisao
        FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
        filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.INDICADOR_USO, 
        ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.DESCRICAO);
        Collection<ContaMotivoRevisao> collectionContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, 
        ContaMotivoRevisao.class.getName());
        sessao.setAttribute("collectionContaMotivoRevisao", collectionContaMotivoRevisao);
        //Fim de pesquisando ContaMotivoRevisao
        
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")
        ) {
        	
        	//-------------- bt DESFAZER ---------------
        	
        	parcelamentoPerfilActionForm.setResolucaoDiretoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setImovelSituacaoTipo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setSubcategoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setImovelPerfil("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setPercentualDescontoAcrescimo("");
        	parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista("");
        	parcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao("");
         	parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoAntiguidade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoAntiguidade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
        	parcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividade("");
        	parcelamentoPerfilActionForm.setQtdeMaximaReparcelamento("");
        	parcelamentoPerfilActionForm.setPercentualEntradaSugerida("");
        	parcelamentoPerfilActionForm.setQuantidadeMinimaMesesDebito("");
        	
        	parcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoAVista("");
        	parcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoParcelado("");
        	parcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoAVista("");
        	parcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoParcelado("");
        	
        	parcelamentoPerfilActionForm.setConsumoMinimo("");
        	parcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio("");
        	parcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido("1");
        	parcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta("1");
        	
        	parcelamentoPerfilActionForm.setCategoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setNumeroConsumoEconomia("");
        	parcelamentoPerfilActionForm.setNumeroAreaConstruida("");    
        	parcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial("2");
        	parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior("");
        	parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior("");
        	parcelamentoPerfilActionForm.setPercentualDescontoAVista(""); 
        	parcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura("");
        	parcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima("2");
        	parcelamentoPerfilActionForm.setPercentualDescontoSancao("");
        	parcelamentoPerfilActionForm.setQuantidadeEconomias(""); 
        	parcelamentoPerfilActionForm.setCapacidadeHidrometro("2"); 
        	parcelamentoPerfilActionForm.setIndicadorEntradaMinima("2");
        	parcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(""); 
        	parcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(""); 
        	
        	parcelamentoPerfilActionForm.setIdContaMotivoRevisao("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	
        	parcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividadeAVista("");
        	parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividadeAVista("");
        	parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividadeAVista("");
        	
        	
        	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
        	sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
        	sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
        	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
        }
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil") != null
                && httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil").equalsIgnoreCase("S")
                && parcelamentoPerfilActionForm.getQtdeMaximaReparcelamento() != null) {
        	
        	//-------------- bt adicionarParcelamentoQtdePerfil ---------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoQtdePerfil","N");
        }
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade").equalsIgnoreCase("S")
                && !parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoAntiguidade ------------------------
   
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	        	
        	adicionarParcelamentoDescontoAntiguidade(parcelamentoPerfilActionForm, sessao);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoDescontoAntiguidade" , "N");
        }
        
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade").equalsIgnoreCase("S")
                && !parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividade ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividade(parcelamentoPerfilActionForm,sessao);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoDescontoInatividade","N");
        }
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista").equalsIgnoreCase("S")
                && !parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividadeAVista ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividadeAVista(parcelamentoPerfilActionForm,sessao);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoDescontoInatividadeAVista","N");
        }
        
		// DEFINI��O QUE IR� AUXILIAR O RETORNO DOS POPUPS
		sessao.setAttribute("UseCase", "INSERIRPERFIL");
		
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") == null || 
		((Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")).size() == 0){
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","1");
		}else{
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","2");
		}
		
        //devolve o mapeamento de retorno
        return retorno;

    }
        
    private void adicionarParcelamentoDescontoAntiguidade(ParcelamentoPerfilActionForm parcelamentoPerfilActionForm,
    														HttpSession sessao){
    	
    	if (parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito()== null ||
    			parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe Qtde. M�nima Meses de D�bito p/ Desconto
					"atencao.required", null, " Qtde. M�nima Meses de D�bito p/ Desconto");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito())){
			throw new ActionServletException(
					// Qtde. M�nima Meses de D�bito p/ Desconto deve ser numerico
					"atencao.integer", null, " Qtde. M�nima Meses de D�bito p/ Desconto");
		 }
    	
    	Collection<ParcelamentoDescontoAntiguidade> collectionParcelamentoDescontoAntiguidade = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
        	collectionParcelamentoDescontoAntiguidade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoAntiguidade");
        } else {
        	collectionParcelamentoDescontoAntiguidade = new ArrayList();
        }
    	            

        Integer quantidadeMinimaMesesDebito = new Integer(parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito());
        
		//[FS0006]Verificar percentual de desconto
		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		Collection colecaoSistemaParametros = fachada.pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();

		BigDecimal percentualMaximoAbatimento = sistemaParametro.getPercentualMaximoAbatimento();*/
		

        
        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{	
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade());
        	
        	/*//[FS0006]Verificar percentual de desconto
        	if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Sem Restabelecimento � superior ao 
				//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimento);
    		}*/
		}

        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Com Restabelecimento � superior ao 
				//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_com_rest_superior_percentual_max", null,"" + percentualMaximoAbatimento);
    		}*/
        }
        
        BigDecimal percentualDescontoAtivo = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoAtivo()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoAtivo().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Ativo
					"atencao.required", null, "  Percentual de Desconto Ativo");	
        
        }else{
        	percentualDescontoAtivo = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoAtivo());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoAtivo.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Ativo � superior ao 
				//Percentual M�ximo de Desconto de << percentualMaximoAbatimento  >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_ativo_superior_percentual_max",null,"" + percentualMaximoAbatimento);
    		}*/
		}

		
        ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeNovo = 
										new ParcelamentoDescontoAntiguidade();
		 
		if (collectionParcelamentoDescontoAntiguidade != null && !collectionParcelamentoDescontoAntiguidade.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoAntiguidade.iterator();
		
			while (iterator.hasNext()) {
				parcelamentoDescontoAntiguidadeAntigo = (ParcelamentoDescontoAntiguidade) iterator.next();
				
				// Verificar quantidade m�nima meses de debito para desconto
				if (quantidadeMinimaMesesDebito.equals
						(parcelamentoDescontoAntiguidadeAntigo.getQuantidadeMinimaMesesDebito())){
					//Quantidade M�nima Meses de Debito para Desconto j� informada
					throw new ActionServletException(
					"atencao.qtde_minima_meses_debito_desconto_ja_informado");
				}
			}
		}
		
		if (parcelamentoPerfilActionForm.getIdContaMotivoRevisao() != null && 
			!parcelamentoPerfilActionForm.getIdContaMotivoRevisao().equalsIgnoreCase("") &&
			!parcelamentoPerfilActionForm.getIdContaMotivoRevisao().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
			FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
	        
			filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.ID, 
			new Integer(parcelamentoPerfilActionForm.getIdContaMotivoRevisao())));
	        
	        Collection<ContaMotivoRevisao> collectionContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, 
	        ContaMotivoRevisao.class.getName());
	        
	        ContaMotivoRevisao contaMotivoRevisao = (ContaMotivoRevisao)
	        Util.retonarObjetoDeColecao(collectionContaMotivoRevisao);
	        
	        parcelamentoDescontoAntiguidadeNovo.setContaMotivoRevisao(contaMotivoRevisao);
	        
        }


		parcelamentoDescontoAntiguidadeNovo.setQuantidadeMinimaMesesDebito(quantidadeMinimaMesesDebito);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoAtivo(percentualDescontoAtivo);
		parcelamentoDescontoAntiguidadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoAntiguidade.add(parcelamentoDescontoAntiguidadeNovo);

		parcelamentoPerfilActionForm.setQuantidadeMinimaMesesDebito("");
		parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoAntiguidade("");
		parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoAntiguidade("");
		parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		parcelamentoPerfilActionForm.setIdContaMotivoRevisao(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		
		
		//Ordena a cole��o pela Qtde. M�nima Meses de D�bito p/ Desconto
		Collections.sort((List) collectionParcelamentoDescontoAntiguidade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoAntiguidade) a).getQuantidadeMinimaMesesDebito().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoAntiguidade) b).getQuantidadeMinimaMesesDebito().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		
		
		 //manda para a sess�o a cole��o de ParcelamentoDescontoAntiguidade
        sessao.setAttribute("collectionParcelamentoDescontoAntiguidade", collectionParcelamentoDescontoAntiguidade);
    }
    
    private void adicionarParcelamentoDescontoInatividade(ParcelamentoPerfilActionForm parcelamentoPerfilActionForm,
							HttpSession sessao){
    	
    	if (parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade() == null ||
    			parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. M�xima Meses de Inatividade da Lig. de �gua
					"atencao.required", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade())){
			throw new ActionServletException(
					// Qtde. M�xima Meses de Inatividade da Lig. de �gua deve ser numerico
					"atencao.integer", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");
		 }
    	
    	Collection<ParcelamentoDescontoInatividade> collectionParcelamentoDescontoInatividade = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
        	collectionParcelamentoDescontoInatividade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividade");
        } else {
        	collectionParcelamentoDescontoInatividade = new ArrayList();
        }
    	            
        Integer quantidadeMaximaMesesInatividade = new Integer(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade());


		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		Collection colecaoSistemaParametros = fachada.pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();

		BigDecimal percentualMaximoAbatimento = sistemaParametro.getPercentualMaximoAbatimento();*/

		
        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade());

        	//[FS0006]Verificar percentual de desconto
    		/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
    			//Percentual de Desconto Sem Restabelecimento � superior ao 
    			//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
    			throw new ActionServletException(
    				"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimento);
    			
    		}*/
		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
    			//Percentual de Desconto Com Restabelecimento � superior ao 
    			//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
    			throw new ActionServletException(
    				"atencao.percentual_desconto_com_rest_superior_percentual_max", null,"" + percentualMaximoAbatimento);
    			
    		}*/
		}
        						
		
        ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeNovo = 
										new ParcelamentoDescontoInatividade();
		 
		if (collectionParcelamentoDescontoInatividade != null && !collectionParcelamentoDescontoInatividade.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividade.iterator();
		
			while (iterator.hasNext()) {
			parcelamentoDescontoInatividadeAntigo = (ParcelamentoDescontoInatividade) iterator.next();
				
				//[FS0003] Verificar quantidade m�nima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade M�xima Meses de Inatividade de Liga��o de �gua j� informada
					throw new ActionServletException(
					"atencao.qtde_maxima_meses_inatividade_ja_informado");
				}
			}
		}			


		parcelamentoDescontoInatividadeNovo.setQuantidadeMaximaMesesInatividade(quantidadeMaximaMesesInatividade);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoInatividade.add(parcelamentoDescontoInatividadeNovo);

		parcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividade("");
		parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividade("");
		parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividade("");
		parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		
		//Ordena a cole��o pela Qtde. M�xima Meses de Inatividade da Lig. de �gua
		Collections.sort((List) collectionParcelamentoDescontoInatividade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoInatividade) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoInatividade) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		 //manda para a sess�o a cole��o de ParcelamentoDescontoInatividade
        sessao.setAttribute("collectionParcelamentoDescontoInatividade", collectionParcelamentoDescontoInatividade);

    }
    
    private void atualizaColecoesNaSessao(HttpSession sessao,
    							HttpServletRequest httpServletRequest){
    	
    	// collectionParcelamentoDescontoAntiguidade
    	if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoAntiguidade").equals(
						"")) {

			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoAntiguidade
			String vlSemRestAntiguidade = null;
			String vlComRestAntiguidade = null;
			String vlDescontoAtivo = null;


			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
					.iterator();
			
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
				
				// inseri essas vari�veis no objeto ParcelamentoDescontoAntiguidade
				BigDecimal percentualDescontoSemRestabelecimentoAntiguidade  = null;
				if (vlSemRestAntiguidade != null 
						&& !vlSemRestAntiguidade.equals("")) {
					percentualDescontoSemRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null 
						&& !vlComRestAntiguidade.equals("")) {
					percentualDescontoComRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
				}
				
				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null 
						&& !vlDescontoAtivo.equals("")) {
					percentualDescontoAtivoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
				}
				
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);

			}

        }
    	
    	
    	//collectionParcelamentoDescontoInatividade
    	if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals(
						"")) {
	

			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
					.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = 
						(ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade
						.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao()
						.getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividade"
						+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividade"
						+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null 
						&& !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null 
						&& !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}

				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);


			}

        }	        	

    	
    	//collectionParcelamentoDescontoInatividadeAVista
    	if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista").equals("")) {
	

			Collection collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcDesctoInativVista parcelamentoDescontoInatividade = 
						(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao().getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividadeAVista"+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividadeAVista"+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}

				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);


			}

        }	        	

    	
    	//collectionParcelamentoQuantidadeReparcelamentoHelper
    	/*
    	if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null
				&& !sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper").equals(
						"")) {

			Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
					.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoQuantidadeReparcelamentoHelper
			String vlMinPrest = null;

			Iterator iteratorParcelamentoQuantidadeReparcelamentoHelper = collectionParcelamentoQuantidadeReparcelamentoHelper
					.iterator();
			
			while (iteratorParcelamentoQuantidadeReparcelamentoHelper.hasNext()) {
				ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = 
						(ParcelamentoQuantidadeReparcelamentoHelper) iteratorParcelamentoQuantidadeReparcelamentoHelper
						.next();
				long valorTempo = parcelamentoQuantidadeReparcelamentoHelper.getUltimaAlteracao()
						.getTime();
				vlMinPrest = (String) httpServletRequest.getParameter("vlMinPrest"
						+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoQuantidadeReparcelamentoHelper
				BigDecimal valorMinimoPrestacao  = null;
				if (vlMinPrest != null 
						&& !vlMinPrest.equals("")) {
					valorMinimoPrestacao = Util.formatarMoedaRealparaBigDecimal(vlMinPrest);
				}

				parcelamentoQuantidadeReparcelamentoHelper.setValorMinimoPrestacao(valorMinimoPrestacao);

			}
        }	*/
    	
    }
    
    private void adicionarParcelamentoDescontoInatividadeAVista(
    		ParcelamentoPerfilActionForm parcelamentoPerfilActionForm,HttpSession sessao){
    	
    	if (parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista() == null ||
    			parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. M�xima Meses de Inatividade da Lig. de �gua
					"atencao.required", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista())){
			throw new ActionServletException(
					// Qtde. M�xima Meses de Inatividade da Lig. de �gua deve ser numerico
					"atencao.integer", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");
		 }
    	
    	Collection<ParcDesctoInativVista> collectionParcelamentoDescontoInatividadeAVista = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
        	collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        } else {
        	collectionParcelamentoDescontoInatividadeAVista = new ArrayList();
        }
    	            
        Integer quantidadeMaximaMesesInatividade = new Integer(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista());

        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista());

		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista());
        	
		}
        						
		
        ParcDesctoInativVista parcelamentoDescontoInatividadeNovo = new ParcDesctoInativVista();
		 
		if (collectionParcelamentoDescontoInatividadeAVista != null && !collectionParcelamentoDescontoInatividadeAVista.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcDesctoInativVista parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividadeAVista.iterator();
		
			while (iterator.hasNext()) {
			parcelamentoDescontoInatividadeAntigo = (ParcDesctoInativVista) iterator.next();
				
				//[FS0003] Verificar quantidade m�nima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade M�xima Meses de Inatividade de Liga��o de �gua j� informada
					throw new ActionServletException(
					"atencao.qtde_maxima_meses_inatividade_ja_informado");
				}
			}
		}			


		parcelamentoDescontoInatividadeNovo.setQuantidadeMaximaMesesInatividade(quantidadeMaximaMesesInatividade);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoInatividadeAVista.add(parcelamentoDescontoInatividadeNovo);

		parcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividadeAVista("");
		parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividadeAVista("");
		parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividadeAVista("");
//		parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		
		//Ordena a cole��o pela Qtde. M�xima Meses de Inatividade da Lig. de �gua
		Collections.sort((List) collectionParcelamentoDescontoInatividadeAVista, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcDesctoInativVista) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcDesctoInativVista) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		 //manda para a sess�o a cole��o de ParcelamentoDescontoInatividadeAVista
        sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVista", collectionParcelamentoDescontoInatividadeAVista);

    }

}
