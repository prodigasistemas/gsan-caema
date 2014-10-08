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

import gcom.cobranca.bean.ContaValoresHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.ContaParcelamentoJudicialHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.ParcelaJudicialHelper;
import gcom.gui.cobranca.parcelamentojudicial.bean.RegistroImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;


/**
 * 
 * [UC1441] Efetuar Parcelamento Judicial
 * 
 * @author Hugo Azevedo
 * @date 15/03/2013
 */
public class EfetuarParcelamentoJudicialActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	//MÉTODOS
	public static final int PESQUISAR_CLIENTE_USUARIO = 1;
	public static final int LIMPAR_CLIENTE_USUARIO = 2;
	public static final int PESQUISAR_IMOVEL = 3;
	public static final int LIMPAR_IMOVEL = 4;
	public static final int ADICIONAR_IMOVEL = 5;
	public static final int REMOVER_IMOVEL = 6;
	public static final int PESQUISAR_CLIENTE_RESPONSAVEL = 7;
	public static final int LIMPAR_CLIENTE_RESPONSAVEL = 8;
	public static final int REMOVER_DOCUMENTO_JUDICIAL = 9;
	public static final int CALCULAR_DESCONTO = 11;
	public static final int CALCULAR_CUSTAS = 12;
	public static final int CALCULAR_HONORARIOS = 13;
	public static final int CALCULAR_VALOR_PARCELAS = 14;
	public static final int ADICIONAR_PARCELA = 15;
	public static final int REMOVER_PARCELA = 16;
	public static final int DESFAZER_PARCELAS_INFORMADAS = 17;
	public static final int CALCULAR_DATA_VENC_PRIMEIRA_PARCELA = 18; 
	public static final int LIMPAR_LISTA_PARCELAS = 19;
	
	public static final int BLOQUEIO_VALOR = 1;
	public static final int BLOQUEIO_PERCENTUAL = 2;
	
	//1ª aba - Imóveis
	private String idClienteUsuario;
	private String descClienteUsuario;
	private String idImovel;
	private String descImovel;
	private String idRegistroPrincipal;
	private Collection<RegistroImovelHelper> listaRegistroImovelHelper;
	private String amReferenciaInicial;
	private String amReferenciaFinal;
	
	//Registro auxiliar que guarda a informação da última lista inserida,
	//para verificar se houve alterações para a verificação [SB0039] Verificar Campos Alterados 1ª Aba
	private Collection<RegistroImovelHelper> listaRegistroImovelHelperAnterior;
	
	//2ª aba - Débitos
	private RegistroImovelHelper registroImovelPrincipal;
	private String matriculaImovelPrincipal;
	private String descSituacaoLigacaoAgua;
	private String descSituacaoLigacaoEsgoto;
	private String enderecoImovel;
	private String[] idsContasSelecionadas;
	private Collection<ContaParcelamentoJudicialHelper> listaContaParcelamentoJudicialHelper;
	
	//Registros auxiliares que guardam as últimas informações inseridas,
	//para verificar se houve alterações para a verificação [SB0040] Verificar Campos Alterados 2ª Aba
	private String[] idsContasSelecionadasAnterior;
	private Collection<ContaParcelamentoJudicialHelper> listaContaParcelamentoJudicialHelperAnterior;
	
	
	//3ª aba - Negociação
	private String valorDebito;
	private String valorAcordo;
	private String percentualDesconto;
	private String valorCustas;
	private String percentualCustas;
	private String valorHonorarios;
	private String percentualHonorarios;
	private String numeroProcessoJudicial;
	private String idClienteResponsavel;
	private String descClienteResponsavel;
	private String advogadoResponsavel;
	private String numeroOAB;
	private String observacao;
	private String bloqueioCustas;
	private String bloqueioHonorarios;
	private FormFile documentoAcordoJudicial;
	private FormFile documentoAcordoJudicialCopia;
	
	//Registros auxiliares que guardam as últimas informações inseridas,
	//para verificar se houve alterações para a verificação [SB0041] Verificar Campos Alterados 3ª Aba
	private String valorAcordoAnterior;
	private String valorCustasAnterior;
	private String percentualCustasAnterior;
	private String valorHonorariosAnterior;
	private String percentualHonorariosAnterior;
	
	
	//4ª aba - Conclusão
	private String indicadorPerdeDesconto;
	private String indicadorValorCustas;
	private String indicadorValorHonorarios;
	private String indicadorParcelamentoComJuros;
	private String indicadorInformarValorParcela;
	private String indicadorEntradaParcelamento;
	private String dataVencimentoEntrada;
	private String valorEntrada;
	private String diaVencimentoParcelas;
	private String dataVencimentoPrimeiraParcela;
	private String qtdDiasEntreParcelas;
	private String qtdParcelas;
	private String percentualJuros;
	private String valorParcelado;
	private Collection<ParcelaJudicialHelper> listaParcelaJudicial;
	
	//Registros auxiliares que guardam as últimas informações inseridas,
	//para verificar se houve alterações para a verificação [SB0042] Verificar Campos Alterados 4ª Aba
	private String indicadorValorCustasAnterior;
	private String indicadorValorHonorariosAnterior;
	private String indicadorParcelamentoComJurosAnterior;
	private String indicadorInformarValorParcelaAnterior;
	private String indicadorEntradaParcelamentoAnterior;
	
	
	//[SB0023] Informar Valor Parcelas
	private String qtdParcelasInformar;
	private String parcelaInicial;
	private String parcelaFinal;
	private String valorParcelaInformar;
	private Collection<ParcelaJudicialHelper> listaParcelaJudicialInformar;
	private String totalParcelasInformar;
	
	
	public EfetuarParcelamentoJudicialActionForm(){
		this.indicadorPerdeDesconto = ConstantesSistema.NAO.toString();
		this.indicadorInformarValorParcela = ConstantesSistema.NAO.toString();
		this.indicadorParcelamentoComJuros = ConstantesSistema.NAO.toString();
		this.indicadorValorCustas = ConstantesSistema.NAO.toString();
		this.indicadorValorHonorarios = ConstantesSistema.NAO.toString();
		this.indicadorEntradaParcelamento = ConstantesSistema.NAO.toString();
		this.valorParcelado = "0,00";
		this.totalParcelasInformar = "0.00";
	}
	
	//--------------------------------------------------
	public String getMetodoPesquisarClienteUsuario(){
		return "" + PESQUISAR_CLIENTE_USUARIO;
	}
	
	public String getMetodoLimparClienteUsuario(){
		return "" + LIMPAR_CLIENTE_USUARIO;
	}
	
	public String getMetodoPesquisarImovel(){
		return "" + PESQUISAR_IMOVEL;
	}
	
	public String getMetodoLimparImovel(){
		return "" + LIMPAR_IMOVEL;
	}
	
	public String getMetodoAdicionarImovel(){
		return "" + ADICIONAR_IMOVEL;
	}
	
	public String getMetodoRemoverImovel(){
		return "" + REMOVER_IMOVEL;
	}
	
	public String getMetodoPesquisarClienteResponsavel(){
		return ""+ PESQUISAR_CLIENTE_RESPONSAVEL;
	}
	
	public String getMetodoLimparClienteResponsavel(){
		return ""+LIMPAR_CLIENTE_RESPONSAVEL;
	}
	
	public String getMetodoRemoverDocumentoJudicial(){
		return ""+REMOVER_DOCUMENTO_JUDICIAL;
	}
	
	public String getMetodoCalcularDesconto(){
		return ""+CALCULAR_DESCONTO;
	}
	
	public String getMetodoCalcularCustas(){
		return ""+ CALCULAR_CUSTAS;
	}
	
	public String getMetodoCalcularHonorarios(){
		return ""+CALCULAR_HONORARIOS;
	}
	
	public String getMetodoCalcularValorParcelas(){
		return ""+CALCULAR_VALOR_PARCELAS;
	}
	
	public String getMetodoAdicionarParcela(){
		return ""+ADICIONAR_PARCELA;
	}
	
	public String getMetodoRemoverParcela(){
		return ""+REMOVER_PARCELA;
	}
	
	public String getMetodoDesfazerParcelasInformadas(){
		return ""+DESFAZER_PARCELAS_INFORMADAS;
	}
	
	public String getMetodoCalcularDataVencPrimeiraParc(){
		return ""+CALCULAR_DATA_VENC_PRIMEIRA_PARCELA;
	}
	
	public String getMetodoLimparListaParcelas(){
		return ""+LIMPAR_LISTA_PARCELAS;
	}
	
	public String getBloqueioValor(){
		return ""+BLOQUEIO_VALOR;
	}
	
	public String getBloqueioPercentual(){
		return ""+BLOQUEIO_PERCENTUAL;
	}
	
	public String getSim(){
		return ConstantesSistema.SIM.toString();
	}
	
	public String getNao(){
		return ConstantesSistema.NAO.toString();
	}
	
	//--------------------------------------------------
	
	
	public String getIdClienteUsuario() {
		return idClienteUsuario;
	}

	public String getDescClienteUsuario() {
		return descClienteUsuario;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public String getDescImovel() {
		return descImovel;
	}

	public String getAmReferenciaInicial() {
		return amReferenciaInicial;
	}

	public String getAmReferenciaFinal() {
		return amReferenciaFinal;
	}

	public void setIdClienteUsuario(String idClienteUsuario) {
		this.idClienteUsuario = idClienteUsuario;
	}

	public void setDescClienteUsuario(String descClienteUsuario) {
		this.descClienteUsuario = descClienteUsuario;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public void setDescImovel(String descImovel) {
		this.descImovel = descImovel;
	}

	public void setAmReferenciaInicial(String amReferenciaInicial) {
		this.amReferenciaInicial = amReferenciaInicial;
	}

	public void setAmReferenciaFinal(String amReferenciaFinal) {
		this.amReferenciaFinal = amReferenciaFinal;
	}

	public Collection<RegistroImovelHelper> getListaRegistroImovelHelper() {
		return listaRegistroImovelHelper;
	}

	public void setListaRegistroImovelHelper(
			Collection<RegistroImovelHelper> listaRegistroImovelHelper) {
		this.listaRegistroImovelHelper = listaRegistroImovelHelper;
	}

	public String getIdRegistroPrincipal() {
		return idRegistroPrincipal;
	}

	public void setIdRegistroPrincipal(String idRegistroPrincipal) {
		this.idRegistroPrincipal = idRegistroPrincipal;
	}

	public RegistroImovelHelper getRegistroImovelPrincipal() {
		return registroImovelPrincipal;
	}

	public void setRegistroImovelPrincipal(
			RegistroImovelHelper registroImovelPrincipal) {
		this.registroImovelPrincipal = registroImovelPrincipal;
	}

	public String getMatriculaImovelPrincipal() {
		return matriculaImovelPrincipal;
	}

	public String getDescSituacaoLigacaoAgua() {
		return descSituacaoLigacaoAgua;
	}

	public String getDescSituacaoLigacaoEsgoto() {
		return descSituacaoLigacaoEsgoto;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public Collection<ContaParcelamentoJudicialHelper> getListaContaParcelamentoJudicialHelper() {
		return listaContaParcelamentoJudicialHelper;
	}

	public void setMatriculaImovelPrincipal(String matriculaImovelPrincipal) {
		this.matriculaImovelPrincipal = matriculaImovelPrincipal;
	}

	public void setDescSituacaoLigacaoAgua(String descSituacaoLigacaoAgua) {
		this.descSituacaoLigacaoAgua = descSituacaoLigacaoAgua;
	}

	public void setDescSituacaoLigacaoEsgoto(String descSituacaoLigacaoEsgoto) {
		this.descSituacaoLigacaoEsgoto = descSituacaoLigacaoEsgoto;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public void setListaContaParcelamentoJudicialHelper(
			Collection<ContaParcelamentoJudicialHelper> listaContaParcelamentoJudicialHelper) {
		this.listaContaParcelamentoJudicialHelper = listaContaParcelamentoJudicialHelper;
	}

	public String[] getIdsContasSelecionadas() {
		return idsContasSelecionadas;
	}

	public void setIdsContasSelecionadas(String[] idsContasSelecionadas) {
		this.idsContasSelecionadas = idsContasSelecionadas;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public String getValorAcordo() {
		if(Util.verificarNaoVazio(this.valorAcordo)){
			BigDecimal ida = Util.formatarMoedaRealparaBigDecimal(this.valorAcordo);
			return Util.formatarMoedaReal(ida);
		}
		else
			return null;
	}

	public String getPercentualDesconto() {
		return percentualDesconto;
	}

	public String getValorCustas() {
		if(Util.verificarNaoVazio(this.valorCustas)){
			BigDecimal ida = Util.formatarMoedaRealparaBigDecimal(this.valorCustas);
			return Util.formatarMoedaReal(ida);
		}
		else
			return null;
	}

	public String getPercentualCustas() {
		return percentualCustas;
	}

	public String getValorHonorarios() {
		if(Util.verificarNaoVazio(this.valorHonorarios)){
			BigDecimal ida = Util.formatarMoedaRealparaBigDecimal(this.valorHonorarios);
			return Util.formatarMoedaReal(ida);
		}
		else
			return null;
	}

	public String getPercentualHonorarios() {
		return percentualHonorarios;
	}

	public String getNumeroProcessoJudicial() {
		return numeroProcessoJudicial;
	}

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public String getDescClienteResponsavel() {
		return descClienteResponsavel;
	}

	public String getAdvogadoResponsavel() {
		return advogadoResponsavel;
	}

	public String getNumeroOAB() {
		return numeroOAB;
	}

	public String getObservacao() {
		return observacao;
	}

	public FormFile getDocumentoAcordoJudicial() {
		return documentoAcordoJudicial;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public void setValorAcordo(String valorAcordo) {
		this.valorAcordo = valorAcordo;
	}

	public void setPercentualDesconto(String percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public void setValorCustas(String valorCustas) {
		this.valorCustas = valorCustas;
	}

	public void setPercentualCustas(String percentualCustas) {
		this.percentualCustas = percentualCustas;
	}

	public void setValorHonorarios(String valorHonorarios) {
		this.valorHonorarios = valorHonorarios;
	}

	public void setPercentualHonorarios(String percentualHonorarios) {
		this.percentualHonorarios = percentualHonorarios;
	}

	public void setNumeroProcessoJudicial(String numeroProcessoJudicial) {
		this.numeroProcessoJudicial = numeroProcessoJudicial;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public void setDescClienteResponsavel(String descClienteResponsavel) {
		this.descClienteResponsavel = descClienteResponsavel;
	}

	public void setAdvogadoResponsavel(String advogadoResponsavel) {
		this.advogadoResponsavel = advogadoResponsavel;
	}

	public void setNumeroOAB(String numeroOAB) {
		this.numeroOAB = numeroOAB;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setDocumentoAcordoJudicial(FormFile documentoAcordoJudicial) {
		this.documentoAcordoJudicial = documentoAcordoJudicial;
	}

	public String getIndicadorPerdeDesconto() {
		return indicadorPerdeDesconto;
	}

	public String getIndicadorValorCustas() {
		return indicadorValorCustas;
	}

	public String getIndicadorValorHonorarios() {
		return indicadorValorHonorarios;
	}

	public String getIndicadorParcelamentoComJuros() {
		return indicadorParcelamentoComJuros;
	}

	public String getIndicadorInformarValorParcela() {
		return indicadorInformarValorParcela;
	}

	public String getDataVencimentoPrimeiraParcela() {
		return dataVencimentoPrimeiraParcela;
	}

	public String getQtdDiasEntreParcelas() {
		return qtdDiasEntreParcelas;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public String getPercentualJuros() {
		return percentualJuros;
	}

	public String getValorParcelado() {
		return valorParcelado;
	}

	public void setIndicadorPerdeDesconto(String indicadorPerdeDesconto) {
		this.indicadorPerdeDesconto = indicadorPerdeDesconto;
	}

	public void setIndicadorValorCustas(String indicadorValorCustas) {
		this.indicadorValorCustas = indicadorValorCustas;
	}

	public void setIndicadorValorHonorarios(String indicadorValorHonorarios) {
		this.indicadorValorHonorarios = indicadorValorHonorarios;
	}

	public void setIndicadorParcelamentoComJuros(
			String indicadorParcelamentoComJuros) {
		this.indicadorParcelamentoComJuros = indicadorParcelamentoComJuros;
	}

	public void setIndicadorInformarValorParcela(
			String indicadorInformarValorParcela) {
		this.indicadorInformarValorParcela = indicadorInformarValorParcela;
	}

	public void setDataVencimentoPrimeiraParcela(
			String dataVencimentoPrimeiraParcela) {
		this.dataVencimentoPrimeiraParcela = dataVencimentoPrimeiraParcela;
	}

	public void setQtdDiasEntreParcelas(String qtdDiasEntreParcelas) {
		this.qtdDiasEntreParcelas = qtdDiasEntreParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public void setPercentualJuros(String percentualJuros) {
		this.percentualJuros = percentualJuros;
	}

	public void setValorParcelado(String valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	public Collection<ParcelaJudicialHelper> getListaParcelaJudicial() {
		return listaParcelaJudicial;
	}

	public void setListaParcelaJudicial(
			Collection<ParcelaJudicialHelper> listaParcelaJudicial) {
		this.listaParcelaJudicial = listaParcelaJudicial;
	}

	public String getQtdParcelasInformar() {
		return qtdParcelasInformar;
	}

	public String getParcelaInicial() {
		return parcelaInicial;
	}

	public String getParcelaFinal() {
		return parcelaFinal;
	}

	public String getValorParcelaInformar() {
		return valorParcelaInformar;
	}

	public String getTotalParcelasInformar() {
		return totalParcelasInformar;
	}

	public void setQtdParcelasInformar(String qtdParcelasInformar) {
		this.qtdParcelasInformar = qtdParcelasInformar;
	}

	public void setParcelaInicial(String parcelaInicial) {
		this.parcelaInicial = parcelaInicial;
	}

	public void setParcelaFinal(String parcelaFinal) {
		this.parcelaFinal = parcelaFinal;
	}

	public void setValorParcelaInformar(String valorParcelaInformar) {
		this.valorParcelaInformar = valorParcelaInformar;
	}

	public void setTotalParcelasInformar(String totalParcelasInformar) {
		this.totalParcelasInformar = totalParcelasInformar;
	}

	public Collection<ParcelaJudicialHelper> getListaParcelaJudicialInformar() {
		return listaParcelaJudicialInformar;
	}

	public void setListaParcelaJudicialInformar(
			Collection<ParcelaJudicialHelper> listaParcelaJudicialInformar) {
		this.listaParcelaJudicialInformar = listaParcelaJudicialInformar;
	}
	
	public String getIndicadorEntradaParcelamento() {
		return indicadorEntradaParcelamento;
	}

	public String getDataVencimentoEntrada() {
		return dataVencimentoEntrada;
	}

	public String getValorEntrada() {
		return valorEntrada;
	}

	public String getDiaVencimentoParcelas() {
		return diaVencimentoParcelas;
	}

	public void setIndicadorEntradaParcelamento(String indicadorEntradaParcelamento) {
		this.indicadorEntradaParcelamento = indicadorEntradaParcelamento;
	}

	public void setDataVencimentoEntrada(String dataVencimentoEntrada) {
		this.dataVencimentoEntrada = dataVencimentoEntrada;
	}

	public void setValorEntrada(String valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public void setDiaVencimentoParcelas(String diaVencimentoParcelas) {
		this.diaVencimentoParcelas = diaVencimentoParcelas;
	}
	
	public Collection<RegistroImovelHelper> getListaRegistroImovelHelperAnterior() {
		return listaRegistroImovelHelperAnterior;
	}

	public String[] getIdsContasSelecionadasAnterior() {
		return idsContasSelecionadasAnterior;
	}

	public Collection<ContaParcelamentoJudicialHelper> getListaContaParcelamentoJudicialHelperAnterior() {
		return listaContaParcelamentoJudicialHelperAnterior;
	}

	public String getValorAcordoAnterior() {
		return valorAcordoAnterior;
	}

	public String getValorCustasAnterior() {
		return valorCustasAnterior;
	}

	public String getPercentualCustasAnterior() {
		return percentualCustasAnterior;
	}

	public String getValorHonorariosAnterior() {
		return valorHonorariosAnterior;
	}

	public String getPercentualHonorariosAnterior() {
		return percentualHonorariosAnterior;
	}

	public String getIndicadorValorCustasAnterior() {
		return indicadorValorCustasAnterior;
	}

	public String getIndicadorValorHonorariosAnterior() {
		return indicadorValorHonorariosAnterior;
	}

	public String getIndicadorParcelamentoComJurosAnterior() {
		return indicadorParcelamentoComJurosAnterior;
	}

	public String getIndicadorInformarValorParcelaAnterior() {
		return indicadorInformarValorParcelaAnterior;
	}

	public String getIndicadorEntradaParcelamentoAnterior() {
		return indicadorEntradaParcelamentoAnterior;
	}

	public void setListaRegistroImovelHelperAnterior(
			Collection<RegistroImovelHelper> listaRegistroImovelHelperAnterior) {
		this.listaRegistroImovelHelperAnterior = listaRegistroImovelHelperAnterior;
	}

	public void setIdsContasSelecionadasAnterior(
			String[] idsContasSelecionadasAnterior) {
		this.idsContasSelecionadasAnterior = idsContasSelecionadasAnterior;
	}

	public void setListaContaParcelamentoJudicialHelperAnterior(
			Collection<ContaParcelamentoJudicialHelper> listaContaParcelamentoJudicialHelperAnterior) {
		this.listaContaParcelamentoJudicialHelperAnterior = listaContaParcelamentoJudicialHelperAnterior;
	}

	public void setValorAcordoAnterior(String valorAcordoAnterior) {
		this.valorAcordoAnterior = valorAcordoAnterior;
	}

	public void setValorCustasAnterior(String valorCustasAnterior) {
		this.valorCustasAnterior = valorCustasAnterior;
	}

	public void setPercentualCustasAnterior(String percentualCustasAnterior) {
		this.percentualCustasAnterior = percentualCustasAnterior;
	}

	public void setValorHonorariosAnterior(String valorHonorariosAnterior) {
		this.valorHonorariosAnterior = valorHonorariosAnterior;
	}

	public void setPercentualHonorariosAnterior(String percentualHonorariosAnterior) {
		this.percentualHonorariosAnterior = percentualHonorariosAnterior;
	}

	public void setIndicadorValorCustasAnterior(String indicadorValorCustasAnterior) {
		this.indicadorValorCustasAnterior = indicadorValorCustasAnterior;
	}

	public void setIndicadorValorHonorariosAnterior(
			String indicadorValorHonorariosAnterior) {
		this.indicadorValorHonorariosAnterior = indicadorValorHonorariosAnterior;
	}

	public void setIndicadorParcelamentoComJurosAnterior(
			String indicadorParcelamentoComJurosAnterior) {
		this.indicadorParcelamentoComJurosAnterior = indicadorParcelamentoComJurosAnterior;
	}

	public void setIndicadorInformarValorParcelaAnterior(
			String indicadorInformarValorParcelaAnterior) {
		this.indicadorInformarValorParcelaAnterior = indicadorInformarValorParcelaAnterior;
	}

	public void setIndicadorEntradaParcelamentoAnterior(
			String indicadorEntradaParcelamentoAnterior) {
		this.indicadorEntradaParcelamentoAnterior = indicadorEntradaParcelamentoAnterior;
	}
	
	public FormFile getDocumentoAcordoJudicialCopia() {
		return documentoAcordoJudicialCopia;
	}

	public void setDocumentoAcordoJudicialCopia(
			FormFile documentoAcordoJudicialCopia) {
		this.documentoAcordoJudicialCopia = documentoAcordoJudicialCopia;
	}
	
	public String getBloqueioCustas() {
		return bloqueioCustas;
	}

	public String getBloqueioHonorarios() {
		return bloqueioHonorarios;
	}

	public void setBloqueioCustas(String bloqueioCustas) {
		this.bloqueioCustas = bloqueioCustas;
	}

	public void setBloqueioHonorarios(String bloqueioHonorarios) {
		this.bloqueioHonorarios = bloqueioHonorarios;
	}

	/****************************************************************
	 ********************** MÉTODOS AUXILIARES **********************
	 ****************************************************************/
	
	public String getMatriculaImovelFormatada(){
		String id = null;
		if(this.matriculaImovelPrincipal != null){
			id = this.matriculaImovelPrincipal.toString();
			id = id.substring(0,id.length() - 1) + "." + id.substring(id.length() - 1,id.length());
		}
		return id;
	}
	
	public Collection<ContaParcelamentoJudicialHelper> selecionarContas(){
		Collection<ContaParcelamentoJudicialHelper> retorno = new ArrayList<ContaParcelamentoJudicialHelper>();
		if(idsContasSelecionadas != null){
			for(String id : this.idsContasSelecionadas){
				if(!id.equals("-1")){
					for(Iterator<ContaParcelamentoJudicialHelper> it =
						this.listaContaParcelamentoJudicialHelper.iterator();it.hasNext();){
						ContaParcelamentoJudicialHelper helper = it.next();
						if(helper.getIdConta().toString().equals(id)){
							retorno.add(helper);
						}
					}
				}
			}
		}
		return retorno;
	}
	
	public BigDecimal somarContas(){

		BigDecimal retorno = new BigDecimal(0);
		if(idsContasSelecionadas != null){
			for(String id : idsContasSelecionadas){
				if(!id.equals("-1")){
					for(Iterator<ContaParcelamentoJudicialHelper> it =
						listaContaParcelamentoJudicialHelper.iterator();it.hasNext();){
						ContaParcelamentoJudicialHelper helper = it.next();
						if(helper.getIdConta().toString().equals(id)){
							retorno = retorno.add(helper.getValorConta());
						}
					}
				}
			}
		}
		return retorno;
	}
	
	public BigDecimal somarAcrescimos(){
		BigDecimal retorno = new BigDecimal(0);
		if(idsContasSelecionadas != null){
			for(String id : idsContasSelecionadas){
				if(!id.equals("-1")){
					for(Iterator<ContaParcelamentoJudicialHelper> it =
						listaContaParcelamentoJudicialHelper.iterator();it.hasNext();){
						ContaParcelamentoJudicialHelper helper = it.next();
						if(helper.getIdConta().toString().equals(id)){
							retorno = retorno.add(helper.getAcrescimoImpontualidade());
						}
					}
				}
			}
		}
		return retorno;
	}
	
	public String getPercentualDescontoComSimbolo(){
		if(Util.verificarNaoVazio(this.percentualDesconto))
			return this.percentualDesconto + "%";
		else
			return "";
	}
	
	public BigDecimal getSomatorioParcelasAInformar(){
		BigDecimal retorno = new BigDecimal("0");
		if(listaParcelaJudicialInformar != null){
			for(Iterator<ParcelaJudicialHelper> it = listaParcelaJudicialInformar.iterator();it.hasNext();){
				ParcelaJudicialHelper helper = it.next();
				retorno = retorno.add(helper.getValorParcelaFinal());
			}
		}
		return retorno;
	}
	
	public String getNomeArquivo(){
		if(documentoAcordoJudicial != null)
			return documentoAcordoJudicial.getFileName();
		else
			return "Nenhum arquivo adicionado.";
	}
	
	
	public boolean verificarDebitosImoveisInformados(){
		if(listaRegistroImovelHelper != null){
			for(Iterator<RegistroImovelHelper> it = listaRegistroImovelHelper.iterator();it.hasNext();){
				RegistroImovelHelper helper = it.next();
				if(helper.getColecaoContaValoresHelper() != null){
					for(Iterator<ContaValoresHelper> it2 = helper.getColecaoContaValoresHelper().iterator();it2.hasNext();){
						ContaValoresHelper helper2 = it2.next();
						if(Util.verificarNaoVazio(this.amReferenciaInicial) && Util.verificarNaoVazio(this.amReferenciaFinal)){
							Integer amRefInicialInt = Util.formatarMesAnoComBarraParaAnoMes(this.amReferenciaInicial);
							Integer amRefinalInt = Util.formatarMesAnoComBarraParaAnoMes(this.amReferenciaFinal);
							
							if(helper2.getConta().getReferencia() >= amRefInicialInt.intValue() 
									&& helper2.getConta().getReferencia() <= amRefinalInt.intValue())
								return true;
						}
						else{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private void apagarSegundaAba(){
		this.registroImovelPrincipal = null;
		this.matriculaImovelPrincipal = null;
		this.descSituacaoLigacaoAgua = null;
		this.descSituacaoLigacaoEsgoto = null;
		this.enderecoImovel = null;
		this.idsContasSelecionadas = null;
		this.listaContaParcelamentoJudicialHelper = null;
		
		this.idsContasSelecionadasAnterior = null;
		this.listaContaParcelamentoJudicialHelperAnterior = null;
	}
	
	private void apagarTerceiraAba(){
		this.valorDebito = null;
		this.valorAcordo = null;
		this.percentualDesconto = null;
		this.valorCustas = null;
		this.percentualCustas = null;
		this.valorHonorarios = null;
		this.percentualHonorarios = null;
		this.numeroProcessoJudicial = null;
		this.idClienteResponsavel = null;
		this.descClienteResponsavel = null;
		this.advogadoResponsavel = null;
		this.numeroOAB = null;
		this.observacao = null;
		this.bloqueioCustas = null;
		this.bloqueioHonorarios = null;
		this.documentoAcordoJudicial = null;
		this.documentoAcordoJudicialCopia = null;
		
		this.valorAcordoAnterior = null;
		this.valorCustasAnterior = null;
		this.percentualCustasAnterior = null;
		this.valorHonorariosAnterior = null;
		this.percentualHonorariosAnterior = null;
	}
	
	private void apagarQuartaAba(){
		this.dataVencimentoEntrada = null;
		this.valorEntrada = null;
		this.diaVencimentoParcelas = null;
		this.dataVencimentoPrimeiraParcela = null;
		this.qtdDiasEntreParcelas = null;
		this.qtdParcelas = null;
		this.percentualJuros = null;
		this.listaParcelaJudicial = null;
		
		this.indicadorValorCustasAnterior = null;
		this.indicadorValorHonorariosAnterior = null;
		this.indicadorParcelamentoComJurosAnterior = null;
		this.indicadorInformarValorParcelaAnterior = null;
		this.indicadorEntradaParcelamentoAnterior = null;
		
		this.indicadorPerdeDesconto = ConstantesSistema.NAO.toString();
		this.indicadorInformarValorParcela = ConstantesSistema.NAO.toString();
		this.indicadorParcelamentoComJuros = ConstantesSistema.NAO.toString();
		this.indicadorValorCustas = ConstantesSistema.NAO.toString();
		this.indicadorValorHonorarios = ConstantesSistema.NAO.toString();
		this.indicadorEntradaParcelamento = ConstantesSistema.NAO.toString();
		this.valorParcelado = "0,00";
		this.totalParcelasInformar = "0.00";
	}
	
	//[SB0039] Verificar Campos Alterados 1ª Aba
	public void verificarCamposAlteradosPrimeiraAba(){
		if(listaRegistroImovelHelperAnterior != null 
				&& !Util.isEqualCollection(listaRegistroImovelHelper, listaRegistroImovelHelperAnterior)){
			this.apagarSegundaAba();
			this.apagarTerceiraAba();
			this.apagarQuartaAba();
		}
	}
	
	//[SB0040] Verificar Campos Alterados 2ª Aba
	public void verificarCamposAlteradosSegundaAba(){
		if((idsContasSelecionadasAnterior != null && !Arrays.equals(idsContasSelecionadas, idsContasSelecionadasAnterior)) ||
		   (listaContaParcelamentoJudicialHelperAnterior != null 
		   		&& !Util.isEqualCollection(listaContaParcelamentoJudicialHelper,listaContaParcelamentoJudicialHelperAnterior))){
			this.apagarTerceiraAba();
			this.apagarQuartaAba();
		}
	}
	
	//[SB0041] Verificar Campos Alterados 3ª Aba
	public void verificarCamposAlteradosTerceiraAba(){
		if((this.getValorAcordoAnterior() != null && !this.getValorAcordo().equals(this.getValorAcordoAnterior())) || 
		   (this.getValorCustasAnterior() != null && !this.getValorCustas().equals(this.getValorCustasAnterior())) || 
		   (this.getPercentualCustasAnterior() != null && !this.getPercentualCustas().equals(this.getPercentualCustasAnterior())) || 
		   (this.getValorHonorariosAnterior() != null && !this.getValorHonorarios().equals(this.getValorHonorariosAnterior())) || 
		   (this.getPercentualHonorariosAnterior() != null && !this.getPercentualHonorarios().equals(this.getPercentualHonorariosAnterior()))){
			this.apagarQuartaAba();
		}
	}
	
	//[SB0042] Verificar Campos Alterados 4ª Aba
	public void verificarCamposAlteradosQuartaAba(){
		if((indicadorValorCustasAnterior != null && !indicadorValorCustas.equals(indicadorValorCustasAnterior)) || 
				(indicadorValorHonorariosAnterior != null && !indicadorValorHonorarios.equals(indicadorValorHonorariosAnterior)) ||
				(indicadorParcelamentoComJurosAnterior != null &&  !indicadorParcelamentoComJuros.equals(indicadorParcelamentoComJurosAnterior)) ||
				(indicadorInformarValorParcelaAnterior != null && !indicadorInformarValorParcela.equals(indicadorInformarValorParcelaAnterior)) || 
				(indicadorEntradaParcelamentoAnterior != null && !indicadorEntradaParcelamento.equals(indicadorEntradaParcelamentoAnterior))){
			this.listaParcelaJudicial = null;
		}
	}
	

}
