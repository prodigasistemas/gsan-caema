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

package gcom.gui.cobranca.parcelamentojudicial;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.parcelamentojudicial.bean.ParcelaJudicialHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.RegistroImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 
 * [UC1441] Efetuar Parcelamento Judicial
 * 
 * @author Hugo Azevedo
 * @date 26/03/2013
 */

public class ExibirEfetuarParcelamentoJudicialConclusaoAction extends GcomAction {
	
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//VALIDAÇÃO DA 1ª ABA
		//-----------------------------------------------------------------------------------------------------------------
		//Campos da 1ª aba para validar
		Collection<RegistroImovelHelper> listaImoveis = form.getListaRegistroImovelHelper();
		String idRegistroPrincipal = form.getIdRegistroPrincipal();
		String periodoInicial = form.getAmReferenciaInicial();
		String periodoFinal = form.getAmReferenciaFinal();		
		boolean debitosImoveisInformados = form.verificarDebitosImoveisInformados();
		
		//Validando a 1º aba
		this.getFachada().validarEfetuarParcelamentoJudicialImovel(listaImoveis,idRegistroPrincipal,periodoInicial,periodoFinal,debitosImoveisInformados);
		
		//[SB0039] Verificar Campos Alterados 1ª Aba
		form.verificarCamposAlteradosPrimeiraAba();
		form.setListaRegistroImovelHelperAnterior(new ArrayList<RegistroImovelHelper>(form.getListaRegistroImovelHelper()));
		
		//Selecionando o registro principal
		for(Iterator<RegistroImovelHelper> it = listaImoveis.iterator();it.hasNext();){
			RegistroImovelHelper helper = it.next();
			if(helper.getIdColecao().equals(idRegistroPrincipal)){
				form.setRegistroImovelPrincipal(helper);
				break;
			}
		}
		
		//-----------------------------------------------------------------------------------------------------------------
		
		//VALIDAÇÃO DA 2ª ABA
		//-----------------------------------------------------------------------------------------------------------------
		//Campos da 2ª aba para validar
		String[] idsContasSelecionadas = form.getIdsContasSelecionadas();
		
		//Validando a 2º aba
		this.getFachada().validarEfetuarParcelamentoJudicialDebitos(idsContasSelecionadas,form.getRegistroImovelPrincipal());
		
		//[SB0040] Verificar Campos Alterados 2ª Aba
		form.verificarCamposAlteradosSegundaAba();
		form.setIdsContasSelecionadasAnterior(form.getIdsContasSelecionadas());
		form.setListaContaParcelamentoJudicialHelperAnterior(form.getListaContaParcelamentoJudicialHelper());
		//-----------------------------------------------------------------------------------------------------------------
		
		//VALIDAÇÃO DA 3ª ABA
		//-----------------------------------------------------------------------------------------------------------------
		//Campos da 3ª aba para validar
		String valorDebito = form.getValorDebito();
		String valorAcordo = form.getValorAcordo();
		String percentualDesconto = form.getPercentualDesconto();
		String valorCustas = form.getValorCustas();
		String percentualCustas = form.getPercentualCustas();
		String valorHonorarios = form.getValorHonorarios();
		String percentualHonorarios = form.getPercentualHonorarios();
		String numeroProcesspJudicial = form.getNumeroProcessoJudicial();
		String idClienteresponsavel = form.getIdClienteResponsavel();
		String nomeAdvogado = form.getAdvogadoResponsavel();
		String numeroOAB = form.getNumeroOAB();
		FormFile arquivo = form.getDocumentoAcordoJudicial();
		byte[] conteudoArquivo = null;
		String nomeArquivo = null;
		
		//Separando os elementos do arquivo
		try {
			if(arquivo != null){
				conteudoArquivo = arquivo.getFileData();
				nomeArquivo = arquivo.getFileName();
			}
		} catch (FileNotFoundException e) {
			throw new ActionServletException("atencao.elemento_invalido",null,"Documento do Acordo Judicial");
		} catch (IOException e) {
			throw new ActionServletException("atencao.elemento_invalido",null,"Documento do Acordo Judicial");
		}
		
		
		//Validando a 3ª aba
		this.getFachada().validarEfetuarParcelamentoJudicialNegociacao(valorDebito,
																	   valorAcordo,
																	   percentualDesconto,
																	   valorCustas,
																	   percentualCustas,
																	   valorHonorarios,
																	   percentualHonorarios,
																	   numeroProcesspJudicial,
																	   idClienteresponsavel,
																	   nomeAdvogado,
																	   numeroOAB,
																	   conteudoArquivo,
																	   nomeArquivo);
		
		//[SB0041] Verificar Campos Alterados 3ª Aba
		form.verificarCamposAlteradosTerceiraAba();
		form.setValorAcordoAnterior(form.getValorAcordo());
		form.setValorCustasAnterior(form.getValorCustas());
		form.setPercentualCustasAnterior(form.getPercentualCustas());
		form.setValorHonorariosAnterior(form.getValorHonorarios());
		form.setPercentualHonorariosAnterior(form.getPercentualHonorarios());
		
		if(form.getDocumentoAcordoJudicial() != null && !form.getDocumentoAcordoJudicial().getFileName().equals("")){
			form.setDocumentoAcordoJudicialCopia(form.getDocumentoAcordoJudicial());
		}
		else if(form.getDocumentoAcordoJudicialCopia() != null){
			form.setDocumentoAcordoJudicial(form.getDocumentoAcordoJudicialCopia());
		}
		//-----------------------------------------------------------------------------------------------------------------
		
		//Variáveis que vieram do popup
		//-------------------------------------------------------------------------------------------------
		if(this.getSessao(httpServletRequest).getAttribute("valorParcelado") != null){
			form.setValorParcelado((String)this.getSessao(httpServletRequest).getAttribute("valorParcelado"));
			this.getSessao(httpServletRequest).removeAttribute("valorParcelado");
		}
		if(this.getSessao(httpServletRequest).getAttribute("qtdParcelas") != null){
			form.setQtdParcelas((String)this.getSessao(httpServletRequest).getAttribute("qtdParcelas"));
			httpServletRequest.setAttribute("indicadorNaoApagarQtdParcelas", "sim");
			this.getSessao(httpServletRequest).removeAttribute("qtdParcelas");
		}
		//-------------------------------------------------------------------------------------------------
		
		//Recalcular a porcentagem de desconto
		//-------------------------------------------------------------------------------------------------
		BigDecimal valorAcordoBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorAcordo);
		BigDecimal valorDebitoBigDecimal = Util.formatarMoedaRealparaBigDecimal(form.getValorDebito());
		BigDecimal valorPorcentagem  = new BigDecimal("0.00");
		
		//1. Caso o Valor do Acordo seja menor do que o Valor do Débito
		if(valorAcordoBigDecimal.compareTo(valorDebitoBigDecimal) < 0){
			//1.1. O sistema deverá atribuir ao Percentual de Desconto, no formato "999,99%", o valor de
			//    (1 - (Valor do Acordo/Valor do Débito)) *100;
			valorPorcentagem = new BigDecimal("1")
							  .subtract(valorAcordoBigDecimal.divide(valorDebitoBigDecimal,20,BigDecimal.ROUND_DOWN))
							  .multiply(new BigDecimal("100"));
			form.setPercentualDesconto(Util.formatarMoedaReal(valorPorcentagem));
			
		}
		//2. Caso contrário:
		else{
			//2.1. O sistema deverá atribuir ao Percentual de Desconto o valor "0,00%"
			form.setPercentualDesconto(Util.formatarMoedaReal(valorPorcentagem));
		}
		//-------------------------------------------------------------------------------------------------
		
		return actionMapping.findForward("efetuarParcelamentoJudicialConclusaoAction");
	}
	
	/**
	 * 
	 * [SB0024] Calcular Valor Parcelas
	 * 
	 * @author Hugo Azevedo
	 * @date 27/03/2013
	 */
	public ActionForward calcularValorParcelas(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		BigDecimal valorAcordo = Util.formatarMoedaRealparaBigDecimal(form.getValorAcordo());
		BigDecimal valorEntrada = new BigDecimal("0");
		BigDecimal percentualJuros = null;
		
		//[FE0011] Verificar Percentual de Juros informado
		//-------------------------------------------------------------------------------------------------------		
		if(form.getIndicadorParcelamentoComJuros().equals(ConstantesSistema.SIM.toString())){
			percentualJuros = Util.formatarMoedaRealparaBigDecimalComErro(form.getPercentualJuros());
			
			//1. Caso o Indicador Parcelamento com Juros possua valor igual a "Sim" 
			//   e Percentual de Juros não tenha sido informado ou possua valor igual a zero ("0,00")
			if(percentualJuros == null || percentualJuros.compareTo(new BigDecimal("0")) == 0){	
				//1. o sistema deverá exibir a mensagem "Informe o Percentual de Juros."
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o Percentual dos Juros");
			}
			
			//[FE0009] Verificar Percentual dos Juros.
			//-------------------------------------------------------------------------------------------------------
			//1. Caso o Percentual dos Juros informado seja maior do que "100%"
			if(percentualJuros.compareTo(new BigDecimal("100")) > 0){
				throw new ActionServletException("atencao.elemento_invalido",null,"Percentual dos Juros");
			}
			//-------------------------------------------------------------------------------------------------------
		}
		//-------------------------------------------------------------------------------------------------------	
		
		//[FE0017] Verificar Valor da Entrada
		//-------------------------------------------------------------------------------------------------------
		if(form.getIndicadorEntradaParcelamento().equals(ConstantesSistema.SIM.toString())){
			if(form.getValorEntrada() == null || form.getValorEntrada().equals("")){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Valor da Entrada");
			}
			
			valorEntrada = Util.formatarMoedaRealparaBigDecimal(form.getValorEntrada());
			//1. Caso o Valor da Entrada informado seja maior ou igual ao Valor do Acordo
			if(valorEntrada.compareTo(valorAcordo) >= 0){
				
				//1. o sistema deverá exibir a mensagem "Valor da Entrada deve ser menor do que o Valor do Acordo."
				throw new ActionServletException("atencao.valor_entrada_maior_valor_acordo");
			}
		}
		//-------------------------------------------------------------------------------------------------------
		
		
		//Validar campo obrigatório
		if(!Util.verificarNaoVazio(form.getDiaVencimentoParcelas())){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Dia do vencimento das Parcelas");
		}
		if(!this.verificarDiaVencimento(form.getDiaVencimentoParcelas())){
			throw new ActionServletException("atencao.elemento_invalido",null,"Dia do vencimento das Parcelas");
		}
		
		
		//1. O sistema deverá atribuir Número da Parcela = 0;
		Integer parcelaNumero = new Integer(0);
		
		//2. O sistema deverá atribuir Data de Vencimento = Data de Vencimento 1ª Parcela
		Date dataVencimento = Util.converteStringParaDate(form.getDataVencimentoPrimeiraParcela());
		
		//Caso a data de vencimento da primeira parcela seja menor do que a data corrente
		if(Util.compararData(dataVencimento, new Date()) < 0){
			throw new ActionServletException("atencao.data_venc_menor_corrente");
		}
		
		
		//3. O sistema deverá atribuir Valor Parcela Custas = 0;
		BigDecimal valorParcelaCustas = new BigDecimal(0);
		
		//4. O sistema deverá atribuir Valor Parcela Honorários = 0;
		BigDecimal valorParcelaHonorarios = new BigDecimal(0);
		
		//5. O sistema deverá atribuir Valor Parcelado Final = Valor do Acordo;
		BigDecimal valorParceladoFinal = Util.formatarMoedaRealparaBigDecimal(form.getValorAcordo());
		
		
		BigDecimal valorDebito = Util.formatarMoedaRealparaBigDecimal(form.getValorDebito());
		BigDecimal percentualDesconto = Util.formatarMoedaRealparaBigDecimal(form.getPercentualDesconto());	
		
		
		//6. O sistema deverá atribuir Valor do Desconto = (Valor do Débito * Percentual de Desconto) / 100;
		BigDecimal valorDesconto =  valorDebito.multiply(percentualDesconto)
											   .divide(new BigDecimal("100"),20,BigDecimal.ROUND_DOWN);
		
		//Valores auxiliares
		BigDecimal valorCustas = Util.formatarMoedaRealparaBigDecimal(form.getValorCustas());
		BigDecimal valorHonorarios = Util.formatarMoedaRealparaBigDecimal(form.getValorHonorarios());
		BigDecimal qtdParcelas = new BigDecimal(form.getQtdParcelas());
		int diaVencimentoParcelas = new Integer(form.getDiaVencimentoParcelas()).intValue();		
		
		//7. Caso o Indicador Valor das Custas informado na 4ª Aba possua valor igual a "Sim"
		if(form.getIndicadorValorCustas().equals(ConstantesSistema.SIM.toString())){
			
			//7.1. O sistema deverá atribuir Valor Parcelado Final = Valor Parcelado Final + Valor das Custas
			valorParceladoFinal = valorParceladoFinal.add(valorCustas);
			
			//7.2. O sistema deverá atribuir Valor Parcela Custas = Valor das Custas / Quantidade de Parcelas
			valorParcelaCustas = valorCustas.divide(qtdParcelas,2,BigDecimal.ROUND_HALF_DOWN);
		}
		
		//8. Caso o Indicador Valor dos Honorários informado na 4ª Aba possua valor igual a "Sim"
		if(form.getIndicadorValorHonorarios().equals(ConstantesSistema.SIM.toString())){
			
			//8.1. O sistema deverá atribuir Valor Parcelado Final = Valor Parcelado Final + Valor dos Honorários
			valorParceladoFinal = valorParceladoFinal.add(valorHonorarios);
			
			//8.2. O sistema deverá atribuir Valor Parcela Honorários = Valor dos Honorários / Quantidade de Parcelas
			valorParcelaHonorarios = valorHonorarios.divide(qtdParcelas,2,BigDecimal.ROUND_HALF_DOWN);
		}
		
		//9. Caso o Indicador Entrada Parcelamento informado na 4ª Aba possua valor igual a "Sim"
		if(form.getIndicadorEntradaParcelamento().equals(ConstantesSistema.SIM.toString())){
			
			//9.1. O sistema deverá atribuir Percentual da Entrada = Valor da Entrada * 100 / Valor do Acordo
			BigDecimal percentualEntrada = valorEntrada.multiply(new BigDecimal("100"))
													   .divide(valorAcordo,20,BigDecimal.ROUND_DOWN);
			
			//9.2. O sistema deverá atribuir 
			//     Valor do Desconto = Valor do Desconto - (Valor do Desconto * Percentual da Entrada / 100);
			BigDecimal parte1 = valorDesconto.multiply(percentualEntrada)
					   						 .divide(new BigDecimal("100"),20,BigDecimal.ROUND_DOWN); 
			
			valorDesconto = valorDesconto.subtract(parte1);
			
			//9.1. O sistema deverá atribuir Valor Parcelado Final = Valor Parcelado Final - Valor da Entrada
			valorParceladoFinal = valorParceladoFinal.subtract(valorEntrada);
			
			//9.2. O sistema deverá atribuir Valor do Acordo = Valor do Acordo - Valor da Entrada
			valorAcordo = valorAcordo.subtract(valorEntrada);
		}
		
		
		//10. O sistema deverá criar uma Lista de Parcelas com a quantidade 
		//   de itens da lista igual a Quantidade de Parcelas informada na 4ª Aba
		
		Collection<ParcelaJudicialHelper> colecaoParcelas = new ArrayList<ParcelaJudicialHelper>();
		BigDecimal valorParcelaTotal = new BigDecimal("0");
		
		for(int i = 0; i < qtdParcelas.intValue(); i ++){
			
			ParcelaJudicialHelper helper = new ParcelaJudicialHelper();
			
			//11. Para cada item de parcela criado na Lista de Parcelas, o sistema deverá atribuir os seguintes valores:
			
			//Número da Parcela
			parcelaNumero += 1;
			helper.setNumeroParcela(parcelaNumero);
			
			//Data de Vencimento
			//Caso o Número da Parcela seja igual a 1
			if(parcelaNumero.intValue() == 1){
				//o sistema deverá atribuir Data de Vencimento = Data de Vencimento
				helper.setDataVencimento(dataVencimento);
			}
			//caso contrário,
			else{
				//o sistema deverá atribuir Data de Vencimento = data com o dia igual ao Dia do vencimento 
				//das Parcelas e com o mês imediatamente posterior ao mês da Data de Vencimento da parcela anterior
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataVencimento);
				calendar.add(Calendar.MONTH, 1);
				
				//Verificar se o dia passado é maior do que o último dia do mês seguinte. Caso verdadeiro,
				//definir o dia da data de vencimento como o último dia do mês
				if(diaVencimentoParcelas >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
					dataVencimento = Util.criarData(calendar.getActualMaximum(Calendar.DAY_OF_MONTH), Util.getMes(dataVencimento) + 1, Util.getAno(dataVencimento));
				else
					dataVencimento = Util.criarData(diaVencimentoParcelas, Util.getMes(dataVencimento) + 1, Util.getAno(dataVencimento));
				helper.setDataVencimento(dataVencimento);
			}
			
			//Valor Parcela Final
			helper.setValorParcelaFinal(this.calcularValorParcela(
											valorAcordo, 
											percentualJuros, 
											valorParcelaCustas, 
											valorParcelaHonorarios, 
											qtdParcelas,
											parcelaNumero,
											valorParceladoFinal,
											valorParcelaTotal));
			
			//Valor Parcela Bruto
			//(Valor do Acordo + Valor do Desconto Final) / Quantidade de Parcelas
			helper.setValorParcelaBruto(valorAcordo.add(valorDesconto)
												   .divide(qtdParcelas,20,BigDecimal.ROUND_DOWN)); 
										
			
			//Valor Parcela Descontos
			//Caso o Valor do Desconto seja maior do que 0,00 (zero)
			if(valorDesconto.compareTo(new BigDecimal("0.00")) > 0){
				
				//Valor do Desconto / Quantidade de Parcelas
				helper.setValorParcelaDescontos(valorDesconto.divide(qtdParcelas,20,BigDecimal.ROUND_DOWN));
			}
			else
				helper.setValorParcelaDescontos(new BigDecimal("0.00"));
			
			//Valor Parcela Custas
			helper.setValorParcelaCustas(valorParcelaCustas);
			
			//Valor Parcela Honorários
			helper.setValorParcelaHonorarios(valorParcelaHonorarios);
			
			//Valor Parcela Juros
			//Valor Parcela Final + Valor Parcela Descontos - Valor Parcela Bruto
			helper.setValorParcelaJuros(helper.getValorParcelaFinal()
											  .add(helper.getValorParcelaDescontos())
											  .subtract(helper.getValorParcelaBruto())
											  .subtract(helper.getValorParcelaCustas())
											  .subtract(helper.getValorParcelaHonorarios()));
			
			//Somar o valor parcelado calculado
			valorParcelaTotal = valorParcelaTotal.add(helper.getValorParcelaFinal());
			colecaoParcelas.add(helper);
		}
		
		//1.5.12.2.2. atualizar com o Valor Parcelado Final
		form.setValorParcelado(Util.formatarMoedaReal(valorParcelaTotal));
		form.setListaParcelaJudicial(colecaoParcelas);
		
		return actionMapping.findForward("efetuarParcelamentoJudicialConclusaoAction");
	}
	
	
	/**
	 * 
	 * [SB0025] Calcular Valor Parcela
	 * 
	 * @author Hugo Azevedo
	 * @param valorParcelaTotal 
	 * @param valorParceladoFinal 
	 * @date 27/03/2013
	 */
	private BigDecimal calcularValorParcela(BigDecimal valorAcordo, BigDecimal percentualJuros, 
										    BigDecimal valorParcelaCustas, BigDecimal valorParcelaHonorarios,
										    BigDecimal qtdParcelas, Integer numeroParcela, BigDecimal valorParceladoFinal,
										    BigDecimal valorParcelaTotal){
		
		BigDecimal retorno = null;
		
		//1. Caso o Percentual de Juros tenha sido informado na 4ª aba:
		if(percentualJuros != null){
			
			//1.1. Caso o indicador da fórmula de cálculo dos juros de parcelamento não seja pela Tabela Price
			//	   [IT0010 - Obter Indicador Tabela Price]
			SistemaParametro sistemaParametro = this.getSistemaParametro();
			Short indicadorTabelaPrice = sistemaParametro.getIndicadorTabelaPrice();
			if(indicadorTabelaPrice.shortValue() == ConstantesSistema.NAO.shortValue()){
				
				//1.1.1. O sistema deverá atribuir 
				//Valor Parcelado = Valor do Acordo + (Valor do Acordo * Percentual de Juros / 100)
				BigDecimal valorParcelado = valorAcordo.multiply(percentualJuros)
													   .divide(new BigDecimal("100"),2,BigDecimal.ROUND_DOWN)
													   .add(valorAcordo);
				
				//1.1.2. O sistema deverá atribuir 
				//   Valor da Parcela = Valor Parcela Custas + 
				//   Valor Parcela Honorários + (Valor Parcelado / Quantidade de Parcelas);
				BigDecimal valorParcela = valorParcelado.divide(qtdParcelas,2,BigDecimal.ROUND_DOWN)
											 			.add(valorParcelaCustas).add(valorParcelaHonorarios);
				
				retorno = valorParcela;
			}
			
			//1.2. Caso contrário
			else{
				//1.2.1. O sistema deverá atribuir ao Valor Prestacao o valor calculado pelo [UC0186], passando como parâmetro
				BigDecimal valorPrestacao =
							this.getFachada().calcularPrestacao(
								percentualJuros,     	 	//Taxa de Juros do Financiamento (Percentual de Juros)
								qtdParcelas.intValue(), 	//Número de Prestações (Quantidade de Parcelas);
								valorAcordo, 		 	 	//Valor Total do Serviço (Valor do Acordo)
								new BigDecimal("0.00")); 	//Valor da Entrada ("0" - zero)
				
				//1.2.2.  O sistema deverá atribuir 
				//        Valor da Parcela = Valor Prestacao + Valor Parcela Custas + Valor Parcela Honorários
				BigDecimal valorParcela = valorPrestacao.add(valorParcelaCustas).add(valorParcelaHonorarios);
				
				retorno = valorParcela.setScale(2,BigDecimal.ROUND_DOWN);
			}
		}
		//2. Caso contrário
		else{
			//O sistema deverá atribuir 
			//Valor da Parcela = Valor Parcela Custas + Valor Parcela Honorários + (Valor do Acordo / Quantidade de Parcelas)
			retorno = valorAcordo.divide(qtdParcelas,2,BigDecimal.ROUND_DOWN)
							  .add(valorParcelaCustas)
							  .add(valorParcelaHonorarios).setScale(2,BigDecimal.ROUND_DOWN);
			
			BigDecimal parte1 = valorParcelaTotal.add(retorno);
			
			//2.2. Caso seja a última parcela da Lista de Parcelas e o Valor Parcelado Final 
			//   seja diferente do somatório de Valor da Parcela da Lista de Parcelas
			if(new BigDecimal(numeroParcela).compareTo(qtdParcelas) == 0
					&& valorParceladoFinal.compareTo(parte1) != 0){
				
				//2.2. o sistema deverá atribuir Valor da Parcela = Valor da Parcela + 
				//   ((somatório de Valor da Parcela da Lista de Parcelas) - Valor Parcelado Final)
				BigDecimal parte2 = valorParceladoFinal.subtract(parte1);
				retorno = retorno.add(parte2);
			}
		}
		
		
		
		return retorno;
	}
	
	
	/**
	 * 
	 * [SB0037] Calcular Data de Vencimento 1ª Parcela
	 * 
	 * @author Hugo Azevedo
	 * @date 27/03/2013
	 */
	public ActionForward calcularDataVencPrimeiraParcela(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		int diaAtual = Util.getDiaMes(new Date());
		try {
			
			httpServletResponse.setCharacterEncoding("UTF-8");
			
			//Verificar se o dia passado está entre o dia 1 e 31
			if(!this.verificarDiaVencimento(form.getDiaVencimentoParcelas())){
				httpServletResponse.setStatus(300);
				httpServletResponse.getWriter().write("Dia do vencimento das Parcelas inválido");
				return null;
			}
			
			int diaVencimentoParcelas = new Integer(form.getDiaVencimentoParcelas()).intValue();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			
			//1. Caso dia corrente seja superior ao Dia do vencimento das Parcelas
			if(diaAtual > diaVencimentoParcelas){
				
				//1. o sistema deverá preencher a Data de Vencimento 1ª Parcela
				//   com a data do Dia do vencimento das Parcelas do mês 
				//   imediatamente posterior ao mês corrente
				calendar.add(Calendar.MONTH,1);
				Date dataVencimento;
				
				
				//Verificar se o dia passado é maior do que o último dia do mês seguinte. Caso verdadeiro,
				//definir o dia da data de vencimento como o último dia do mês
				if(diaVencimentoParcelas >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
					dataVencimento = Util.criarData(calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 
													calendar.get(Calendar.MONTH) +1, 
													calendar.get(Calendar.YEAR));
				else
					dataVencimento = Util.criarData(diaVencimentoParcelas,
													calendar.get(Calendar.MONTH) +1, 
													calendar.get(Calendar.YEAR));
				
				form.setDataVencimentoPrimeiraParcela(Util.formatarData(dataVencimento));
			}
			
			//2. Caso contrário,
			else{
				//2. o sistema deverá preencher a Data de Vencimento 1ª Parcela com a data do Dia do vencimento 
				//   das Parcelas do mês corrente
				Date dataVencimento;
				
				//Verificar se o dia passado é maior do que o último dia do mês seguinte. Caso verdadeiro,
				//definir o dia da data de vencimento como o último dia do mês
				if(diaVencimentoParcelas >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
					dataVencimento = Util.criarData(calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 
													calendar.get(Calendar.MONTH) +1, 
													calendar.get(Calendar.YEAR));
				else
					dataVencimento = Util.criarData(diaVencimentoParcelas,
													calendar.get(Calendar.MONTH) +1, 
													calendar.get(Calendar.YEAR));
				
				form.setDataVencimentoPrimeiraParcela(Util.formatarData(dataVencimento));
			}	
		
			httpServletResponse.getWriter().write(form.getDataVencimentoPrimeiraParcela());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	public ActionForward limparListaParcelas(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		form.setListaParcelaJudicial(null);
		form.setValorParcelado(Util.formatarMoedaReal(new BigDecimal("0")));
		return actionMapping.findForward("efetuarParcelamentoJudicialConclusaoAction");
	}
	
	//[FE0020] Verificar Dia do vencimento
	private boolean verificarDiaVencimento(String dia){
		if(Util.verificarIdNaoVazio(dia)){
			int diaVencimentoParcelas = new Integer(dia).intValue();
			if(diaVencimentoParcelas >= 1 && diaVencimentoParcelas <= 31)
				return true;
			else
				return false;
		}
		else
			return false;
		
	}
	
}
