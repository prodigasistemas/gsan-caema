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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.bean.PesquisarDocumentosHelper;
import gcom.cadastro.imovel.Categoria;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1396] Informar Dados para Devolu��o de Valor Faturado e Pago Indevidamente
 * 
 * @author Hugo Azevedo
 * @date 20/11/2012
 * 
 */
public class ExibirInformarDadosDevValorFatPagoIndevAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

	
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		InformarDadosDevValorFatPagoIndevActionForm form = (InformarDadosDevValorFatPagoIndevActionForm) actionForm;
		
		//Remover o helper selecionado da sess�o
		sessao.removeAttribute("helperSelecionado");
		
		//Setar a sess�o para indicar o caso de uso que est� sendo chamado
		sessao.setAttribute("UseCase", "INFDADOSVALORPAGINDEV");
		
		//Par�metros obrigat�rios oriundos do endere�o de chamada
		//===============================================================================
		
		//Id do Im�vel
		String idImovel = (String)httpServletRequest.getParameter("idImovel");
		
		//Id do Pagamento
		String idPagamento = (String)httpServletRequest.getParameter("idPagamento");
		
		//Id do Documento
		String idDocumento = (String)httpServletRequest.getParameter("idDocumento");
		//===============================================================================
		
		//Indicador de reload da p�gina
		String reloadPage = (String)httpServletRequest.getParameter("reloadPage");
		
		//Permiss�es de visualiza��es e edi��es de campos na tela
		//===============================================================================
		String habilitarAlteracaoVolumeEsgoto = (String)httpServletRequest.getParameter("habilitarAlteracaoVolumeEsgoto");
		String volumeEsgotoPreenchimentoObr = (String)httpServletRequest.getParameter("volumeEsgotoPreenchimentoObr");
		String percentualColetaPreenchimentoObr = (String)httpServletRequest.getParameter("percentualColetaPreenchimentoObr");
		String consumoAguaPreenchimento = (String)httpServletRequest.getParameter("consumoAguaPreenchimento");
		//===============================================================================
		
		//Cole��o dos documentos pagos indevidamente
		Collection colecaoPagamentosValoresCobIndevidamente = (Collection)sessao.getAttribute("colecaoPagamentosValoresCobIndevidamente");
		
		
		//Recuperando o documento selecionado
		PesquisarDocumentosHelper helper = this.obterDocumentoPagoIndevColecao(colecaoPagamentosValoresCobIndevidamente, idPagamento);
		
		
		//A��o de Limpar o form
		String limpar = (String)httpServletRequest.getParameter("limpar");
		if(limpar != null && limpar.equals("sim")){
			form.reset();
		}
		
		form.setMatriculaImovel(idImovel);
		form.setAnoMesDocumento(Util.formatarAnoMesParaMesAno(helper.getAmReferenciaDocumento()));
		form.setTipoDocumento(helper.getIndicadorTipoDocumento().toString());
		form.setValorFaturado(Util.formataBigDecimal(helper.getValorPagamento(), 2, true));
		
		//Valores que ser�o utilizados na jsp para o reload da p�gina
		//===========================================================
		httpServletRequest.setAttribute("idImovel", idImovel);
		httpServletRequest.setAttribute("idPagamento", idPagamento);
		httpServletRequest.setAttribute("idDocumento", idDocumento);	
		//===========================================================

		
		ActionForward retorno = null;
		
		//Caso o tipo de documento seja Conta
		if(helper.getIndicadorTipoDocumento() != null && helper.getIndicadorTipoDocumento().toString().equals(DocumentoTipo.CONTA.toString())){
			form.setTipoDocumento("Conta");
			retorno = actionMapping.findForward("exibirInformarDadosConta");
			
			
			//Caso tenha sido executado um reload na p�gina
			//-------------------------------------------------------------------------------------------------------------
			if(reloadPage == null || !reloadPage.equals("ok")){
				
				
				//Excluindo os objetos conta da sess�o
				sessao.removeAttribute("contaSelecaoDadosDevValor");
				sessao.removeAttribute("contaHistSelecaoDadosDevValor");
			
				//Motivo da Retifica��o (Carregar cole��o) e remover as cole��es que ainda est�o na sess�o
		        //=========================================================================================
		        if (sessao.getAttribute("colecaoMotivoRetificacaoConta") == null) {
		        	
		        	Collection colecaoMotivoRetificacaoConta = fachada.pesquisarCobrancaIndevidaMotivo(); 
		        	
		            if (colecaoMotivoRetificacaoConta == null
		                    || colecaoMotivoRetificacaoConta.isEmpty()) {
		                throw new ActionServletException(
		                        "atencao.pesquisa.nenhum_registro_tabela", null,
		                        "MOTIVO_RETIFICACAO_CONTA");
		            } else {
		                sessao.setAttribute("colecaoMotivoRetificacaoConta",
		                		colecaoMotivoRetificacaoConta);
		            }
		        }
		       //=========================================================================================
				
		        
		        /*Situa��o Liga��o �gua (Carregar cole��o)
		        ====================================================================== */
		        if (sessao.getAttribute("colecaoSituacaoLigacaoAgua") == null) {
		
		        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(
		        			FiltroLigacaoAguaSituacao.DESCRICAO);
		
		        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
		        			FiltroLigacaoAguaSituacao.INDICADOR_USO,
		                    ConstantesSistema.INDICADOR_USO_ATIVO));
		
		        	Collection colecaoSituacaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao,
		        					LigacaoAguaSituacao.class.getName());
		
		            if (colecaoSituacaoLigacaoAgua == null
		                    || colecaoSituacaoLigacaoAgua.isEmpty()) {
		                throw new ActionServletException(
		                        "atencao.pesquisa.nenhum_registro_tabela", null,
		                        "LIGACAO_AGUA_SITUACAO");
		            } else {
		                sessao.setAttribute("colecaoSituacaoLigacaoAgua",
		                		colecaoSituacaoLigacaoAgua);
		            }
		        }
		        //=====================================================================
		        
		        
		        /*Situa��o Liga��o Esgoto (Carregar cole��o)
		         ====================================================================== */
		        if (sessao.getAttribute("colecaoSituacaoLigacaoEsgoto") == null) {
	
		        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(
		        			FiltroLigacaoEsgotoSituacao.DESCRICAO);
	
		        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
		        			FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
		                    ConstantesSistema.INDICADOR_USO_ATIVO));
	
		        	Collection colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
		        			LigacaoEsgotoSituacao.class.getName());
	
		            if (colecaoSituacaoLigacaoEsgoto == null
		                    || colecaoSituacaoLigacaoEsgoto.isEmpty()) {
		                throw new ActionServletException(
		                        "atencao.pesquisa.nenhum_registro_tabela", null,
		                        "LIGACAO_ESGOTO_SITUACAO");
		            } else {
		                sessao.setAttribute("colecaoSituacaoLigacaoEsgoto",
		                		colecaoSituacaoLigacaoEsgoto);
		            }
		        }
		        //====================================================================
		        
		        
		        /*Situa��o Liga��o Esgoto (Carregar cole��o)
		        ==========================================================================================================================*/
		        if ( sessao.getAttribute( "colecaoConsumoTarifa" ) == null ){
		            FiltroConsumoTarifa filtro = new FiltroConsumoTarifa();
		            filtro.adicionarParametro( new ParametroSimples( FiltroConsumoTarifa.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ) );
		            filtro.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
		            Collection<ConsumoTarifa> colConsumoTarifa = Fachada.getInstancia().pesquisar( filtro, ConsumoTarifa.class.getName() );
		            
		            sessao.setAttribute( "colecaoConsumoTarifa", colConsumoTarifa );
		        }
		        //==========================================================================================================================
		        
		        //Conta
		        //====================================================================
		        
		        Conta contaSelecao = null;
		        ContaHistorico contaSelecaoHist = null;
		        if (idDocumento != null && !idDocumento.equalsIgnoreCase("")){
		        	
		        	FiltroConta filtroConta = new FiltroConta();
		        	filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.LIGACAO_AGUA_SITUACAO);
		        	filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.LIGACAO_ESGOTO_SITUACAO);
		        	filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONSUMO_TARIFA);
		        	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID,idDocumento));
		        
		        	Collection contaCol = fachada.pesquisar(filtroConta, Conta.class.getName());
		        	contaSelecao = (Conta)Util.retonarObjetoDeColecao(contaCol);
	        	
		        	if(contaSelecao == null){
				       
		        	 	FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
			        	filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.LIGACAO_AGUA_SITUACAO);
			        	filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.LIGACAO_ESGOTO_SITUACAO);
			        	filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONSUMO_TARIFA);
			        	filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroConta.ID,idDocumento));
			        	
			        	Collection contaColHist = fachada.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
			        	contaSelecaoHist = (ContaHistorico)Util.retonarObjetoDeColecao(contaColHist);        		
		        	}
		        }
			    //====================================================================	
		        
		        
		        Integer consumoEsgoto = new Integer(0);
	            Integer amReferencia = null;
	            BigDecimal percentualColeta = null;
	            Integer idTarifaConsumo = null;
	            Integer idSituacaoAgua = null;
	            Integer consumoAgua = new Integer(0);
	            Integer volumePoco = new Integer(0);
	            Integer idSituacaoEsgoto = null;
	            BigDecimal percentualEsgoto = null;
	            String situacaoAgua = null;
	            String situacaoEsgoto = null;
	            
				if(contaSelecao != null){
	    			consumoEsgoto = contaSelecao.getConsumoEsgoto();
	    			amReferencia = contaSelecao.getReferencia();
	    			percentualColeta = contaSelecao.getPercentualColeta();
	    			idTarifaConsumo = contaSelecao.getConsumoTarifa().getId();
	    			idSituacaoAgua = contaSelecao.getLigacaoAguaSituacao().getId();
	    			consumoAgua = contaSelecao.getConsumoAgua();
	    			volumePoco = contaSelecao.getNumeroVolumePoco();
	    			idSituacaoEsgoto = contaSelecao.getLigacaoEsgotoSituacao().getId();
	    			percentualEsgoto = contaSelecao.getPercentualEsgoto();
	    			situacaoAgua = contaSelecao.getLigacaoAguaSituacao().getDescricao();
	    			situacaoEsgoto = contaSelecao.getLigacaoEsgotoSituacao().getDescricao();
	    			sessao.setAttribute("contaSelecaoDadosDevValor", contaSelecao);
	    			
	    		}
	    		else if(contaSelecaoHist != null){
	    			consumoEsgoto = contaSelecaoHist.getConsumoEsgoto();
	    			percentualColeta = contaSelecaoHist.getPercentualColeta();
	    			idTarifaConsumo = contaSelecaoHist.getConsumoTarifa().getId();
	    			idSituacaoAgua = contaSelecaoHist.getLigacaoAguaSituacao().getId();
	    			consumoAgua = contaSelecaoHist.getConsumoAgua();
	    			volumePoco = contaSelecaoHist.getNumeroVolumePoco();
	    			idSituacaoEsgoto = contaSelecaoHist.getLigacaoEsgotoSituacao().getId();
	    			percentualEsgoto = contaSelecaoHist.getPercentualEsgoto();
	    			situacaoAgua = contaSelecaoHist.getLigacaoAguaSituacao().getDescricao();
	    			situacaoEsgoto = contaSelecaoHist.getLigacaoEsgotoSituacao().getDescricao();
	    			sessao.setAttribute("contaHistSelecaoDadosDevValor", contaSelecaoHist);
	    		}        
		        
		        
				//1.1.1.2. Situa��o de �gua:
				form.setSituacaoAgua(Util.converterObjetoParaString(situacaoAgua));
				
				//1.1.1.3. Situa��o de Esgoto:
				form.setSituacaoEsgoto(Util.converterObjetoParaString(situacaoEsgoto));
				
		        //1.1.2.2. Tarifa de Consumo
	            form.setIdConsumoTarifa(Util.converterObjetoParaString(idTarifaConsumo));
		        
		        //1.1.2.3. Situa��o de �gua
	            form.setIdSituacaoAguaConta(Util.converterObjetoParaString(idSituacaoAgua));
	            
	            //1.1.2.4. Consumo de �gua
	            //caso a situa��o de �gua corresponda a  ligado ou cortado
	            if(idSituacaoAgua.compareTo(LigacaoAguaSituacao.LIGADO) == 0 
	            		|| idSituacaoAgua.compareTo(LigacaoAguaSituacao.CORTADO) == 0){
	            	
	            	form.setConsumoAgua(Util.converterObjetoParaString(consumoAgua));
	            	consumoAguaPreenchimento = ConstantesSistema.SIM.toString();
	            }
	            
	            //1.1.2.4. caso contr�rio n�o deve ser preenchido
	            else{
	            	consumoAguaPreenchimento = ConstantesSistema.NAO.toString();
	            }
	            
	            //1.1.2.5. Situa��o de Esgoto
	            form.setIdSituacaoEsgotoConta(Util.converterObjetoParaString(idSituacaoEsgoto));   
	            
		        //1.1.2.6. Volume Esgoto
		        //================================================================================================
		        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(
	        			FiltroLigacaoEsgotoSituacao.DESCRICAO);
	
	        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
	        			FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));
	        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
	        			FiltroLigacaoEsgotoSituacao.ID,
	        			form.getIdSituacaoEsgotoConta()));
	        	Collection colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
	        			LigacaoEsgotoSituacao.class.getName());
	
	        	
	            if (colecaoSituacaoLigacaoEsgoto != null
	                    && !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
	            	
	            	LigacaoEsgotoSituacao ligacaoEsgotoSit = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(colecaoSituacaoLigacaoEsgoto);
	            	
	            	
	            	//1.1.2.6.1. Caso a situa��o de esgoto determine faturamento
	            	if(ligacaoEsgotoSit.getIndicadorFaturamentoSituacao().compareTo(ConstantesSistema.SIM) == 0){
	            		
	            		//1.1.2.6.1. colocar o consumo volume de esgoto da conta
	            		if(contaSelecao != null){
	            			form.setConsumoEsgoto(contaSelecao.getConsumoEsgoto().toString());
	            		}
	            		else if(contaSelecaoHist != null){
	            			form.setConsumoEsgoto(contaSelecaoHist.getConsumoEsgoto().toString());
	            		}

	        			if (percentualEsgoto != null && percentualEsgoto.compareTo(BigDecimal.ZERO) > 0) {
	        				form.setPercentualEsgoto(Util.converterObjetoParaString(percentualEsgoto));
	        			}

	        			if (volumePoco != null && volumePoco.compareTo(new Integer("0")) > 0) {
	        				form.setConsumoFaturadoPoco(Util.converterObjetoParaString(volumePoco));
	        			}
	        			
	            		//1.1.2.6.1. habilitando o mesmo para altera��o
	            		habilitarAlteracaoVolumeEsgoto = ConstantesSistema.SIM.toString();
	            		
	            		//1.1.2.6.1.1. Caso n�o tenha sido informado o Percentual de Coleta
	            		//             ser� de preenchimento obrigat�rio;
	            		if(form.getPercentualColeta() == null || form.getPercentualColeta().equals("")){
	            			volumeEsgotoPreenchimentoObr = ConstantesSistema.SIM.toString();
	            		}
	            		
	            		//1.1.2.6.1.2. Caso contr�rio considerar o volume de esgoto como sendo o resultado 
	            		//             da multiplica��o do consumo de �gua e/ou o volume do po�o pelo percentual de coleta dividido por cem
	            		else{
	            			
	            			percentualColeta = Util.formatarMoedaRealparaBigDecimal(form.getPercentualEsgoto());
	            			BigDecimal consumoAguaBD = new BigDecimal(consumoAgua);
	            			BigDecimal volumePocoBD = new BigDecimal(volumePoco);
	            			
	            			
	            			BigDecimal volumeEsgoto = volumePocoBD.multiply(percentualColeta)
	            												.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_EVEN)
	            												.add(consumoAguaBD);
	            			
	            			form.setConsumoEsgoto(Util.converterObjetoParaString(volumeEsgoto));
	            			
	            			//e desabilitar para altera��o;
	            			habilitarAlteracaoVolumeEsgoto = ConstantesSistema.NAO.toString();
	            		}
	            	}
	            	
	            	//1.1.2.6.2. Caso contr�rio
	            	else{
	            		//n�o deve vir preenchido e desabilitar o campo para informa��o
	            		form.setConsumoEsgoto("");
	            		form.setPercentualEsgoto("");
	            		form.setConsumoFaturadoPoco("");
	            		form.setPercentualColeta("");
	            		habilitarAlteracaoVolumeEsgoto = ConstantesSistema.NAO.toString();
	            	}
	            }
	            //================================================================================================
	            
	            
	            //1.1.2.8. Volume de Po�o:
	            //================================================================================================         
	            //1.1.2.8.1. Caso o campo CNTA_NNCONSUMOPOCO da tabela FATURAMENTO.CONTA seja diferente de Nulo, 
	            //           atribuir seu valor ao Volume do po�o
	            if(volumePoco != null && volumePoco.compareTo(new Integer("0")) > 0){
	            	form.setConsumoFaturadoPoco(consumoEsgoto.toString());
	            }
	            
	            //1.1.2.8.2. Caso Contr�rio
	            else{
	            	 if (colecaoSituacaoLigacaoEsgoto != null
	                         && colecaoSituacaoLigacaoEsgoto.isEmpty()) {
	            		 
	            		 LigacaoEsgotoSituacao ligacaoEsgotoSit = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(colecaoSituacaoLigacaoEsgoto);
	            		 
	            		 //1.1.2.8.2.1. Caso a situa��o de esgoto determine faturamento
	            		 if(ligacaoEsgotoSit.getIndicadorFaturamentoSituacao().compareTo(ConstantesSistema.SIM) == 0){
	            			 
	            			 //1.1.2.8.2.1. colocar o consumo medido no po�o
	            			 FiltroMedicaoHistorico filtroHist = new FiltroMedicaoHistorico();
	            			 filtroHist.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.MEDICAO_TIPO_ID,MedicaoTipo.POCO));
	            			 filtroHist.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,amReferencia));
	            			 filtroHist.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.IMOVEL_ID,idImovel));
	            			 
	            			 Collection col = fachada.pesquisar(filtroHist, MedicaoHistorico.class.getName());
	            			 MedicaoHistorico medicao = (MedicaoHistorico)Util.retonarObjetoDeColecao(col);
	            			 
	            			 form.setConsumoFaturadoPoco(Util.converterObjetoParaString(medicao.getNumeroConsumoMes()));
	            			 
	            		 }
	            		 
	            		 //1.1.2.8.2.2. Caso contr�rio
	            		 else{
	            			 
	            			 //1.1.2.8.2.2. n�o deve vir preenchido desabilitar o campo para informa��o
	            			 form.setConsumoFaturadoPoco("");
	            		 }
	            	 }
	            }
	            //================================================================================================
	            
	            
	            //1.1.2.9. Percentual de Coleta
	            //================================================================================================
	            //1.1.2.9.1. Caso o campo CNTA_PCCOLETA da tabela FATURAMENTO.CONTA seja diferente de Null
	            if(percentualColeta != null){
	            	
	            	//atribuir seu valor ao Percentual de Coleta
	            	form.setPercentualColeta(Util.converterObjetoParaString(percentualColeta));
   				 	percentualColetaPreenchimentoObr = ConstantesSistema.SIM.toString();
	            }
	            
	            //1.1.2.9.2. Caso Contr�rio
	            else{
	            	 if (colecaoSituacaoLigacaoEsgoto != null
	                         && !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
	            		 
	            		 LigacaoEsgotoSituacao ligacaoEsgotoSit = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(colecaoSituacaoLigacaoEsgoto);
	            		 
	            		 //1.1.2.9.2.1. Caso a situa��o de esgoto determine faturamento
	            		 if(ligacaoEsgotoSit.getIndicadorFaturamentoSituacao().compareTo(ConstantesSistema.SIM) == 0){
	            			 
	            			 //1.1.2.9.2.1.1. Caso n�o tenha sido informado o Volume de esgoto acima, 
	            			 if(form.getConsumoEsgoto() == null || form.getConsumoEsgoto().equals("")){
	            				 
	            				 //ser� de preenchimento obrigat�rio;
	            				 percentualColetaPreenchimentoObr = ConstantesSistema.SIM.toString();
	            			 }
	            			 
	            			 //1.1.2.9.2.1.2. Caso contr�rio
	            			 else{
	            				 //1.1.2.9.2.1.2. desabilitar o campo para informa��o
	            				 percentualColetaPreenchimentoObr = ConstantesSistema.NAO.toString();
	            			 }
	            		 }
	            		 
	            		 //1.1.2.9.2.2. Caso contr�rio
	            		 else{
	            			 
	            			 //n�o deve vir preenchido e desabilitar o campo para informa��o
	            			 percentualColetaPreenchimentoObr = ConstantesSistema.NAO.toString();
	            			 form.setPercentualColeta("");
	            		 }
	            	 }
	            }
	            //================================================================================================
	            
	            
	            
	            //1.1.2.10. Categoria e Economia:
	            //================================================================================================
	            //1.1.2.10.1. Lista das Categorias e respectivas quantidades de economias
	            Collection colecaoCategoria = new ArrayList();
	            if(contaSelecao != null)
	            	colecaoCategoria = fachada.obterQuantidadeEconomiasContaCategoria(contaSelecao);
	            else
	            	colecaoCategoria = fachada.obterQuantidadeEconomiasContaCategoria(contaSelecaoHist);
		        
	    		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
	            //================================================================================================
	            
		        
		        //1.1.2.11. D�bitos Cobrados
		        //================================================================================================
		        //1.1.2.11.1. Lista dos D�bitos Cobrados 
		        Collection colecaoDebitoCobrado = new ArrayList();
	            if(contaSelecao != null)
	            	colecaoDebitoCobrado = fachada.obterDebitosCobradosConta(contaSelecao);
	            else
	            	colecaoDebitoCobrado = fachada.obterDebitosCobradosContaHistorico(contaSelecaoHist);
		        sessao.setAttribute("colecaoDebitoCobrado", colecaoDebitoCobrado);
		        
		        //Totalizando o valor dos d�bitos
		        BigDecimal valorTotalDebito = new BigDecimal("0.00");
		        if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()){
		        	
		        	Iterator colecaoDebitoCobradoIt = colecaoDebitoCobrado.iterator();
		        	DebitoCobrado debitoCobradoColecao;
					
		        	while (colecaoDebitoCobradoIt.hasNext()) {
		        		debitoCobradoColecao = (DebitoCobrado) colecaoDebitoCobradoIt
								.next();
						
		        		valorTotalDebito = valorTotalDebito.add(debitoCobradoColecao.getValorPrestacao());
					}
		        }
		        //================================================================================================
		        
		        //1.1.2.12. Cr�ditos Realizados
		        //================================================================================================
		        Collection colecaoCreditoRealizado = new ArrayList();
	            if(contaSelecao != null)
	            	colecaoCreditoRealizado = fachada.obterCreditosRealizadosConta(contaSelecao);
	            else
	            	colecaoCreditoRealizado = fachada.obterCreditosRealizadosContaHistorico(contaSelecaoHist);
	            
		        sessao.setAttribute("colecaoCreditoRealizado", colecaoCreditoRealizado);
		        
		        BigDecimal valorTotalCredito = new BigDecimal("0.00");
		        if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()){
		        	
		        	Iterator colecaoCreditoRealizadoIt = colecaoCreditoRealizado.iterator();
		        	CreditoRealizado creditoRealizadoColecao;
					
		        	while (colecaoCreditoRealizadoIt.hasNext()) {
		        		creditoRealizadoColecao = (CreditoRealizado) colecaoCreditoRealizadoIt
								.next();
						
		        		valorTotalCredito = valorTotalCredito.add(creditoRealizadoColecao.getValorCredito());
					}
		        }        
		        //================================================================================================
			}
			//-----------------------------------------------------------------------------------------------------
	        
			//1.1.2.10.1.3. Remover uma categoria que esteja na lista
			String idCategoria = (String)httpServletRequest.getParameter("idCategoria");
			String removerCategoria = (String)httpServletRequest.getParameter("removerCategoria");
			if(removerCategoria != null && removerCategoria.equals("ok")){
				this.removerCategoria(idCategoria,sessao);
			}
			
			//1.1.2.11.2.7. Remover um d�bito que esteja na lista
			String debitoCobradoUltimaAlteracao = (String)httpServletRequest.getParameter("debitoCobradoUltimaAlteracao");
			String removerDebito = (String)httpServletRequest.getParameter("removerDebito");
			if(removerDebito != null && removerDebito.equals("ok")){
				this.removerDebito(debitoCobradoUltimaAlteracao,sessao);
			}
			
			//1.1.2.12.2.3. Remover um cr�dito que esteja na lista.
			String creditoRealizadoUltimaAlteracao = (String)httpServletRequest.getParameter("creditoRealizadoUltimaAlteracao");
			String removerCredito = (String)httpServletRequest.getParameter("removerCredito");
			if(removerCredito != null && removerCredito.equals("ok")){
				this.removerCredito(creditoRealizadoUltimaAlteracao,sessao);
			}
			
			
			//Caso o usu�rio tenha alterado a situa��o da liga��o de esgoto
			String atualizarSituacaoEsgoto = (String)httpServletRequest.getParameter("atualizarSituacaoEsgoto");
			if(atualizarSituacaoEsgoto != null && atualizarSituacaoEsgoto.equals("ok")){
				
				BigDecimal percentualEsgoto = null;
				Integer consumoEsgoto = new Integer(0);
				Conta contaSelecao = null;
				ContaHistorico contaSelecaoHist = null;
				Integer consumoAgua = new Integer(0);
		        Integer volumePoco = new Integer(0);
		        Integer amReferencia = new Integer(0);
				
				if(sessao.getAttribute("contaSelecaoDadosDevValor") != null){
					contaSelecao = (Conta)sessao.getAttribute("contaSelecaoDadosDevValor");
					percentualEsgoto = contaSelecao.getPercentualEsgoto();
					consumoAgua = contaSelecao.getConsumoAgua();
	    			volumePoco = contaSelecao.getNumeroVolumePoco();
	    			consumoEsgoto = contaSelecao.getConsumoEsgoto();
	    			amReferencia = contaSelecao.getReferencia();
				}
				else if(sessao.getAttribute("contaHistSelecaoDadosDevValor") != null){
					contaSelecaoHist = (ContaHistorico)sessao.getAttribute("contaHistSelecaoDadosDevValor");
					percentualEsgoto = contaSelecaoHist.getPercentualEsgoto();
					consumoAgua = contaSelecaoHist.getConsumoAgua();
	    			volumePoco = contaSelecaoHist.getNumeroVolumePoco();
	    			consumoEsgoto = contaSelecaoHist.getConsumoEsgoto();
	    			amReferencia = contaSelecaoHist.getAnoMesReferenciaConta();
				}
				
	            //1.1.2.6. Volume Esgoto
		        //================================================================================================
		        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(
	        			FiltroLigacaoEsgotoSituacao.DESCRICAO);
	
	        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
	        			FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));
	        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
	        			FiltroLigacaoEsgotoSituacao.ID,
	        			form.getIdSituacaoEsgotoConta()));
	        	Collection colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
	        			LigacaoEsgotoSituacao.class.getName());
	
	        	
	            if (colecaoSituacaoLigacaoEsgoto != null
	                    && !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
	            	
	            	LigacaoEsgotoSituacao ligacaoEsgotoSit = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(colecaoSituacaoLigacaoEsgoto);
	            	
	            	
	            	//1.1.2.6.1. Caso a situa��o de esgoto determine faturamento
	            	if(ligacaoEsgotoSit.getIndicadorFaturamentoSituacao().compareTo(ConstantesSistema.SIM) == 0){
	            		
	            		//1.1.2.6.1. colocar o consumo volume de esgoto da conta
	            		if(contaSelecao != null){
	            			form.setConsumoEsgoto(contaSelecao.getConsumoEsgoto().toString());
	            		}
	            		else if(contaSelecaoHist != null){
	            			form.setConsumoEsgoto(contaSelecaoHist.getConsumoEsgoto().toString());
	            		}

	        			if (percentualEsgoto != null && percentualEsgoto.compareTo(BigDecimal.ZERO) > 0) {
	        				form.setPercentualEsgoto(Util.converterObjetoParaString(percentualEsgoto));
	        			}

	        			if (volumePoco != null && volumePoco.compareTo(new Integer("0")) > 0) {
	        				form.setConsumoFaturadoPoco(Util.converterObjetoParaString(volumePoco));
	        			}
	        			
	            		//1.1.2.6.1. habilitando o mesmo para altera��o
	            		habilitarAlteracaoVolumeEsgoto = ConstantesSistema.SIM.toString();
	            		
	            		//1.1.2.6.1.1. Caso n�o tenha sido informado o Percentual de Coleta
	            		//             ser� de preenchimento obrigat�rio;
	            		if(form.getPercentualColeta() == null || form.getPercentualColeta().equals("")){
	            			volumeEsgotoPreenchimentoObr = ConstantesSistema.SIM.toString();
	            		}
	            		
	            		//1.1.2.6.1.2. Caso contr�rio considerar o volume de esgoto como sendo o resultado 
	            		//             da multiplica��o do consumo de �gua e/ou o volume do po�o pelo percentual de coleta dividido por cem
	            		else{
	            			
	            			BigDecimal percentualColeta = Util.formatarMoedaRealparaBigDecimal(form.getPercentualEsgoto());
	            			BigDecimal consumoAguaBD = new BigDecimal(consumoAgua);
	            			BigDecimal volumePocoBD = new BigDecimal(volumePoco);
	            			
	            			
	            			BigDecimal volumeEsgoto = volumePocoBD.multiply(percentualColeta)
	            												.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_EVEN)
	            												.add(consumoAguaBD);
	            			
	            			form.setConsumoEsgoto(Util.converterObjetoParaString(volumeEsgoto));
	            			
	            			//e desabilitar para altera��o;
	            			habilitarAlteracaoVolumeEsgoto = ConstantesSistema.NAO.toString();
	            		}
	            	}
	            	
	            	//1.1.2.6.2. Caso contr�rio
	            	else{
	            		//n�o deve vir preenchido e desabilitar o campo para informa��o
	            		form.setConsumoEsgoto("");
	            		form.setPercentualEsgoto("");
	            		form.setConsumoFaturadoPoco("");
	            		form.setPercentualColeta("");
	            		habilitarAlteracaoVolumeEsgoto = ConstantesSistema.NAO.toString();
	            	}
	            }
	            //================================================================================================
	            
	            
	            if(consumoEsgoto != null && consumoEsgoto.compareTo(new Integer("0")) > 0){
	            	form.setConsumoFaturadoPoco(consumoEsgoto.toString());
	            }
	            
	            //1.1.2.8.2. Caso Contr�rio
	            else{
	            	 if (colecaoSituacaoLigacaoEsgoto != null
	                         && colecaoSituacaoLigacaoEsgoto.isEmpty()) {
	            		 
	            		 LigacaoEsgotoSituacao ligacaoEsgotoSit = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(colecaoSituacaoLigacaoEsgoto);
	            		 
	            		 //1.1.2.8.2.1. Caso a situa��o de esgoto determine faturamento
	            		 if(ligacaoEsgotoSit.getIndicadorFaturamentoSituacao().compareTo(ConstantesSistema.SIM) == 0){
	            			 
	            			 //1.1.2.8.2.1. colocar o consumo medido no po�o
	            			 FiltroMedicaoHistorico filtroHist = new FiltroMedicaoHistorico();
	            			 filtroHist.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.MEDICAO_TIPO_ID,MedicaoTipo.POCO));
	            			 filtroHist.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,amReferencia));
	            			 filtroHist.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.IMOVEL_ID,idImovel));
	            			 
	            			 Collection col = fachada.pesquisar(filtroHist, MedicaoHistorico.class.getName());
	            			 MedicaoHistorico medicao = (MedicaoHistorico)Util.retonarObjetoDeColecao(col);
	            			 
	            			 form.setConsumoFaturadoPoco(Util.converterObjetoParaString(medicao.getNumeroConsumoMes()));
	            			 
	            		 }
	            		 
	            		 //1.1.2.8.2.2. Caso contr�rio
	            		 else{
	            			 
	            			 //1.1.2.8.2.2. n�o deve vir preenchido desabilitar o campo para informa��o
	            			 form.setConsumoFaturadoPoco("");
	            		 }
	            	 }
	            }
	            
	            
			}
			
			//1.1.3. Usu�rio comanda o c�lculo da conta
			//================================================================================================
			String calcularTotalConta = (String)httpServletRequest.getParameter("calcularTotalConta");
			if(calcularTotalConta != null && calcularTotalConta.equals("ok")){
			

	        //1.1.2.6. Volume Esgoto
	        //================================================================================================
	        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(
        			FiltroLigacaoEsgotoSituacao.DESCRICAO);

        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
        			FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
        			FiltroLigacaoEsgotoSituacao.ID,
        			form.getIdSituacaoEsgotoConta()));
        	Collection colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
        			LigacaoEsgotoSituacao.class.getName());

        	Short indicadorFaturamentoSituacao = null;
            if (colecaoSituacaoLigacaoEsgoto != null
                    && !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
            	
            	LigacaoEsgotoSituacao ligacaoEsgotoSit = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(colecaoSituacaoLigacaoEsgoto);
            	indicadorFaturamentoSituacao = ligacaoEsgotoSit.getIndicadorFaturamentoSituacao();
            	
            	// [FS0010]Verificar preenchimento dos campos
            	this.verificarPreenchimentoCampos(form, indicadorFaturamentoSituacao);
            }
			
			// 1.1.2.6.1.2.	Caso contr�rio (tenha sido informado o Percentual de Coleta abaixo) considerar 
			//  o volume de esgoto como sendo o resultado da multiplica��o do consumo de �gua e/ou 
			//  o volume do po�o pelo percentual de coleta dividido por cem 
			if (indicadorFaturamentoSituacao != null
					&& indicadorFaturamentoSituacao.compareTo(ConstantesSistema.SIM) == 0
					&& form.getPercentualColeta() != null && !form.getPercentualColeta().trim().equals("")) {
				BigDecimal volumePocoInformado = BigDecimal.ZERO;
				BigDecimal percentualColetaInformado = BigDecimal.ZERO;
				BigDecimal consumoAguaInformado = BigDecimal.ZERO;
	
				if (form.getConsumoFaturadoPoco() != null && !form.getConsumoFaturadoPoco().trim().equals("")) {
					volumePocoInformado = Util.formatarMoedaRealparaBigDecimal(form.getConsumoFaturadoPoco());
				}
				if (form.getPercentualColeta() != null && !form.getPercentualColeta().trim().equals("")) {
					percentualColetaInformado = Util.formatarMoedaRealparaBigDecimal(form.getPercentualColeta());
				}
				if (form.getConsumoAgua() != null && !form.getConsumoAgua().trim().equals("")) {
					consumoAguaInformado = Util.formatarMoedaRealparaBigDecimal(form.getConsumoAgua());
				}
	
				BigDecimal volumeEsgoto = volumePocoInformado.multiply(percentualColetaInformado)
													.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_EVEN)
													.add(consumoAguaInformado);
				
				form.setConsumoEsgoto("" + volumeEsgoto.intValue());
			}
			
			//1.1.3.1. Caso a situa��o da liga��o de �gua corresponda a ligado ou cortado, 
			//         ou a situa��o da liga��o de esgoto corresponda a ligado
			if(form.getIdSituacaoAguaConta().equals(LigacaoAguaSituacao.LIGADO.toString()) || 
					form.getIdSituacaoAguaConta().equals(LigacaoAguaSituacao.CORTADO.toString()) ||
					form.getIdSituacaoEsgotoConta().equals(LigacaoEsgotoSituacao.LIGADO.toString())){
				
				Collection colecaoCategoria = (Collection) sessao.getAttribute("colecaoCategoria");
				Collection colecaoDebitoCobrado = (Collection)sessao.getAttribute("colecaoDebitoCobrado");
				Collection colecaoCredito = (Collection)sessao.getAttribute("colecaoCreditoRealizado");
				
				
    			//Atualizando a quantidade de economias por categoria de acordo com o informado pelo usu�rio
				this.atualizarQtdEconomiasCategoria(httpServletRequest, sessao, colecaoCategoria);
				
				//Atualizando o valor do debito de acordo com o informado pelo usu�rio				
		        this.atualizarValorDebito(httpServletRequest, colecaoDebitoCobrado);
		        
		        //Atualizando o valor do credito de acordo com o informado pelo usu�rio
		        this.atualizarValorCredito(httpServletRequest, colecaoCredito);
		        
    	        		
				//1.1.3.1.1. Determina os valores para faturamento de �gua e/ou esgoto
				//           [SB0001] Determinar Valores para Faturamento de �gua e/ou Esgoto
				//-----------------------------------------------------------------------------
				Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
			    
				String imovelID = form.getMatriculaImovel();
		        String mesAnoConta = form.getAnoMesDocumento();
		        Integer situacaoAguaConta = new Integer(form.getIdSituacaoAguaConta());

				Integer situacaoEsgotoConta = new Integer(form.getIdSituacaoEsgotoConta());
				String consumoAgua = form.getConsumoAgua();
		        String consumoEsgoto = form.getConsumoEsgoto();
		        String percentualEsgoto = form.getPercentualEsgoto();
				
				Collection<CalcularValoresAguaEsgotoHelper> valoresConta = fachada.calcularValoresConta(mesAnoConta, imovelID,
			    situacaoAguaConta, situacaoEsgotoConta, colecaoCategoria, consumoAgua, consumoEsgoto, 
			    percentualEsgoto, null, usuarioLogado);
				//-----------------------------------------------------------------------------
				
				//C�lcula o valor total dos d�bitos de uma conta de acordo com o informado pelo usu�rio
		        BigDecimal valorTotalDebitosConta = 
		        	this.getFachada().calcularValorTotalDebitoConta(colecaoDebitoCobrado,
		        httpServletRequest.getParameterMap());
		        
		        //C�lcula o valor total dos cr�ditos de uma conta de acordo com o informado pelo usu�rio
		        BigDecimal valorTotalCreditosConta = 
		        	this.getFachada().calcularValorTotalCreditoConta(colecaoCredito,
		        httpServletRequest.getParameterMap());
				
				//1.1.3.1.2. Calcula o valor total de �gua e o valor total de esgoto, 
				//           que � a soma dos valores de �gua e de esgoto, por categoria, 
				//           retornado pelo [UC0120] chamado no [SB0001]
				//-----------------------------------------------------------------------------
				BigDecimal valorTotalAgua = new BigDecimal("0");
		        BigDecimal valorTotalEsgoto = new BigDecimal("0");
		        
		        if (valoresConta != null && !valoresConta.isEmpty()){
		        	
		        	Iterator valoresContaIt = valoresConta.iterator();
		        	CalcularValoresAguaEsgotoHelper valoresContaObjeto = null;
		        	
		        	while (valoresContaIt.hasNext()){
		        		
		        		valoresContaObjeto = (CalcularValoresAguaEsgotoHelper) valoresContaIt.next();
		        		
		        		//Valor Faturado de �gua
		        		if (valoresContaObjeto.getValorFaturadoAguaCategoria() != null){
		        			valorTotalAgua = valorTotalAgua.add(valoresContaObjeto.getValorFaturadoAguaCategoria());
		        		}
		        		
		        		//Valor Faturado de Esgoto
		        		if (valoresContaObjeto.getValorFaturadoEsgotoCategoria() != null){
		        			valorTotalEsgoto = valorTotalEsgoto.add(valoresContaObjeto.getValorFaturadoEsgotoCategoria());
		        		}
		     
		        	}
		            
		        }
		        
		        if(indicadorFaturamentoSituacao != null
						&& indicadorFaturamentoSituacao.compareTo(ConstantesSistema.SIM) == 0
						&& valoresConta != null){
			        //      Consumo de Esgoto
					Integer consumoAgua2 = fachada.calcularConsumoTotalAguaOuEsgotoPorCategoria(
							valoresConta,
									ConstantesSistema.CALCULAR_AGUA);
					
					if(consumoAgua2 != null)
					{
						form.setConsumoAgua(consumoAgua2.toString());
					}
					Integer consumoEsgoto2 = fachada.calcularConsumoTotalAguaOuEsgotoPorCategoria(
							valoresConta,
									ConstantesSistema.CALCULAR_ESGOTO);
					
					if(consumoEsgoto2 != null)
					{
						form.setConsumoEsgoto(consumoEsgoto2.toString());
					}
		        }
		        
		        BigDecimal valorTotalConta = new BigDecimal("0");
		        valorTotalConta = valorTotalConta.add(valorTotalAgua);
		        valorTotalConta = valorTotalConta.add(valorTotalEsgoto);
		        valorTotalConta = valorTotalConta.add(valorTotalDebitosConta);
		        valorTotalConta = valorTotalConta.subtract(valorTotalCreditosConta);
		        
		        if (valorTotalConta.equals(new BigDecimal("0.00")) && 
	            	(valorTotalCreditosConta == null || valorTotalCreditosConta.equals(new BigDecimal("0.00")))) {
	    			throw new ActionServletException("atencao.valor_conta_igual_zero");
	    		}
	    		else if (valorTotalConta.compareTo(new BigDecimal("0.00")) == -1){	
	    			throw new ActionServletException("atencao.valor_conta_negativo");
	    		}
				//-----------------------------------------------------------------------------
				
				
		        //1.1.4. O sistema exibe os Valores da Conta
		        form.setValorAgua(Util.formatarMoedaReal(valorTotalAgua));
		        form.setValorEsgoto(Util.formatarMoedaReal(valorTotalEsgoto));
		        form.setValorCreditos(Util.formatarMoedaReal(valorTotalCreditosConta));
		        form.setValorDebitos(Util.formatarMoedaReal(valorTotalDebitosConta));
		        form.setValorTotalConta(Util.formatarMoedaReal(valorTotalConta));
		        
			} else {

		        form.setValorAgua("");
		        form.setValorEsgoto("");
		        form.setValorCreditos("");
		        form.setValorDebitos("");
		        form.setValorTotalConta("");
		        
				httpServletRequest.setAttribute(
                    "msgAlert",
                    "Situa��es de Liga��o de �gua e de Esgoto inv�lidas para o c�lculo. ");
				
			}
			
			//================================================================================================
			}
			
		}
		
		//Caso seja Guia de Pagamento
		else if(helper.getIndicadorTipoDocumento() != null && helper.getIndicadorTipoDocumento().toString().equals(DocumentoTipo.GUIA_PAGAMENTO.toString())){
			form.setTipoDocumento("Guia de Pagamento");
			retorno = actionMapping.findForward("exibirInformarDadosOutros");
		}
		
		//Caso seja D�bito a Cobrar
		else{
			form.setTipoDocumento("D�bito a Cobrar");
			retorno = actionMapping.findForward("exibirInformarDadosOutros");	
		}
		sessao.setAttribute("helperSelecionado",helper);
		
		//Atualizando as permiss�es da tela
		//===================================================================================================================
		httpServletRequest.setAttribute("habilitarAlteracaoVolumeEsgoto",habilitarAlteracaoVolumeEsgoto);
		httpServletRequest.setAttribute("volumeEsgotoPreenchimentoObr",volumeEsgotoPreenchimentoObr);
		httpServletRequest.setAttribute("percentualColetaPreenchimentoObr",percentualColetaPreenchimentoObr);
		httpServletRequest.setAttribute("consumoAguaPreenchimento",consumoAguaPreenchimento);
		//===================================================================================================================
		
		return retorno;
	}

	
	/**
	 * 
	 * [FS0010] Verificar preenchimento dos campos
	 * 
	 * @param form
	 * @param httpServletRequest
	 */
	private void verificarPreenchimentoCampos(
			InformarDadosDevValorFatPagoIndevActionForm form,
			Short indicadorFaturamentoSituacao) {
		
		//Caso o usu�rio n�o informe ou selecione o conte�do de algum campo necess�rio � opera��o
		//Motivo da Cobran�a Indevida
		if(form.getIdMotivoCobrancaIndevida() == null || 
				form.getIdMotivoCobrancaIndevida().equals(Util.converterObjetoParaString(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			
			 throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Motivo da Cobran�a Indevida");
		}
		
		//Volume Esgoto
		if(indicadorFaturamentoSituacao != null
				&& indicadorFaturamentoSituacao.compareTo(ConstantesSistema.SIM) == 0
				&& (form.getConsumoEsgoto() == null || form.getConsumoEsgoto().equals("")) 
				&& (form.getPercentualColeta() == null || form.getPercentualColeta().equals(""))){
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Volume Esgoto ou Percentual de Coleta");
		}
		
	}

	
	private void atualizarQtdEconomiasCategoria(HttpServletRequest httpServletRequest, HttpSession sessao, Collection colecaoCategoria) {		
		Iterator colecaoCategoriaIt = colecaoCategoria.iterator();
		Categoria categoria;
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		String qtdPorEconomia;
		
		if(colecaoCategoria != null && colecaoCategoria.size() > 0){
			while (colecaoCategoriaIt.hasNext()) {
				categoria = (Categoria) colecaoCategoriaIt.next();
	
				if (requestMap.get("categoria"+categoria.getId().intValue()) != null) {
					qtdPorEconomia = (requestMap.get("categoria" + categoria.getId().intValue()))[0];
	
					if (qtdPorEconomia == null || qtdPorEconomia.equalsIgnoreCase("")) {
						throw new ActionServletException(
							"atencao.campo_texto.obrigatorio", 
							null,
							"Quantidade de economias");
					}
					categoria.setQuantidadeEconomiasCategoria(new Integer(qtdPorEconomia));
				}
			}
		}
		
		else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Categoria e Economia");
		}
	}
	
	
	private void atualizarValorDebito(HttpServletRequest httpServletRequest,
			Collection colecaoDebitoCobrado) {
		Iterator colecaoDebitoIt = colecaoDebitoCobrado.iterator();
		DebitoCobrado debitoCobrado;
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		String valor;
		BigDecimal valor2 = new BigDecimal ("0.00"); 
		
		while (colecaoDebitoIt.hasNext()) {
			debitoCobrado = (DebitoCobrado) colecaoDebitoIt.next();

			if (requestMap.get("debitoCobrado"
					+ GcomAction.obterTimestampIdObjeto(debitoCobrado)) != null) {

				valor = (requestMap.get("debitoCobrado" + GcomAction.obterTimestampIdObjeto(debitoCobrado)))[0];
				
				if (valor == null
						|| valor.equalsIgnoreCase("")) {
					throw new ActionServletException(
							"atencao.campo_texto.obrigatorio", null,
							"D�bito Cobrado");
				}
				else{
					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
				}

				debitoCobrado.setValorPrestacao(valor2);
			}
		}
	}
	
	private void atualizarValorCredito(HttpServletRequest httpServletRequest,
			Collection colecaoCredito) {
		Iterator colecaoCreditoIt = colecaoCredito.iterator();
		CreditoRealizado creditoRealizado;
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		String valor;
		BigDecimal valor2 = new BigDecimal ("0.00"); 
		
		while (colecaoCreditoIt.hasNext()) {
			creditoRealizado = (CreditoRealizado) colecaoCreditoIt.next();

			if (requestMap.get("creditoRealizado"
					+ GcomAction.obterTimestampIdObjeto(creditoRealizado)) != null) {

				valor = (requestMap.get("creditoRealizado" + GcomAction.obterTimestampIdObjeto(creditoRealizado)))[0];
				if (valor == null
						|| valor.equalsIgnoreCase("")) {
					throw new ActionServletException(
							"atencao.campo_texto.obrigatorio", null,
							"Cr�dito Realizado");
				}
				else{
					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
				}

				creditoRealizado.setValorCredito(valor2);
			}
		}
	}


	
	
	private PesquisarDocumentosHelper obterDocumentoPagoIndevColecao(
			Collection colecaoPagamentosValoresCobIndevidamente,
			String idPagamento) {
		
		PesquisarDocumentosHelper retorno = null;
		
		Iterator it = colecaoPagamentosValoresCobIndevidamente.iterator();
		while(it.hasNext()){
			PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper)it.next();
			if(helper.getIdPagamento().toString().equals(idPagamento)){
				retorno = helper;
				break;
			}
		}
		return retorno;		
	}
	
	
	private void removerCategoria(String idCategoria, HttpSession sessao){
    	
    	if (idCategoria != null && !idCategoria.equalsIgnoreCase("") &&
            sessao.getAttribute("colecaoCategoria") != null){
            	
            Collection colecaoCategoria = (Collection) sessao.getAttribute("colecaoCategoria");
            Categoria categoriaSelect = new Categoria();
            categoriaSelect.setId(new Integer(idCategoria));
            	
            colecaoCategoria.remove(categoriaSelect);
            if(colecaoCategoria.isEmpty() || colecaoCategoria == null){
            	sessao.setAttribute("colecao", 1);
            	sessao.removeAttribute("adicionar");
            }
            else{
            	sessao.removeAttribute("existeColecao");
            }
        }
    }
	
	private void removerDebito(String debitoCobradoUltimaAlteracao, HttpSession sessao) {
    	
		if (debitoCobradoUltimaAlteracao != null && !debitoCobradoUltimaAlteracao.equalsIgnoreCase("") &&
	            sessao.getAttribute("colecaoDebitoCobrado") != null){
			
			long debitoCobradoUltimaAlteracaoLong = Long.parseLong(debitoCobradoUltimaAlteracao);
        	
        	Collection colecaoDebitoCobrado = (Collection) sessao.getAttribute("colecaoDebitoCobrado");
        	
        	Iterator colecaoDebitoCobradoIt = colecaoDebitoCobrado.iterator();
        	DebitoCobrado debitoCobradoColecao;
        	
        	while (colecaoDebitoCobradoIt.hasNext()){
        		debitoCobradoColecao = (DebitoCobrado) colecaoDebitoCobradoIt.next(); 
        		
        		if (GcomAction.obterTimestampIdObjeto(debitoCobradoColecao) ==  debitoCobradoUltimaAlteracaoLong){
        			colecaoDebitoCobrado.remove(debitoCobradoColecao);
        			break;
        		}
        	}
		}
	}
	
	private void removerCredito(String creditoRealizadoUltimaAlteracao, HttpSession sessao) {
    	
		if (creditoRealizadoUltimaAlteracao != null && !creditoRealizadoUltimaAlteracao.equalsIgnoreCase("") &&
        	sessao.getAttribute("colecaoCreditoRealizado") != null){
        	
        	long creditoRealizadoUltimaAlteracaoLong = Long.parseLong(creditoRealizadoUltimaAlteracao);
        	
        	Collection colecaoCreditoRealizado = (Collection) sessao.getAttribute("colecaoCreditoRealizado");
        	
        	Iterator colecaoCreditoRealizadoIt = colecaoCreditoRealizado.iterator();
        	CreditoRealizado creditoRealizadoColecao;
        	
        	while (colecaoCreditoRealizadoIt.hasNext()){
        		creditoRealizadoColecao = (CreditoRealizado) colecaoCreditoRealizadoIt.next();
        		
        		if (GcomAction.obterTimestampIdObjeto(creditoRealizadoColecao) == creditoRealizadoUltimaAlteracaoLong){
        			colecaoCreditoRealizado.remove(creditoRealizadoColecao);
        			break;
        		}
        	}
        }
	}
	
	
}