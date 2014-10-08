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
package gcom.relatorio.faturamento.conta;

import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * @author Vivianne Sousa
 * @date 15/09/2006
 */
public class Relatorio2ViaContaBean implements RelatorioBean {
	
	private String indicadorPrimeiraPagina;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorio2ViaContaDetailBean;
	
	private JRBeanCollectionDataSource arrayJRBoleto;
	private ArrayList arrayRelatorio2ViaContaBoletoBancarioBean;
	private String boleto;
	
	//Linha 1
	private String empresa;
	private String enderecoEmpresa;
	//Linha 2 
	private String descricaoLocalidade;
	//Linha 3 
	private String matriculaImovelFormatada;
	private String dataVencimento;
	//Linha 4 
	private String nomeCliente;
	//Linha 5 
	private String enderecoImovel;
	private String fatura;
	//Linha 6 
	private String inscricao;
	//Linha 7
	private String idClienteResponsavel;
	private String enderecoClienteResponsavel;
	private String enderecoClienteResponsavelLinha2;
	private String descricaoAguaSituacao;
	private String descricaoEsgotoSituacao;
	//Linha 9
	private String dadosConsumoMes1;
	private String dadosConsumoMes4;
	//Linha 10
	private String dadosConsumoMes2;
	private String dadosConsumoMes5;
	private String leituraAnterior;
	private String leituraAtual;
	private String consumoFaturamento;
	private String diasConsumo;
	private String consumoMedioDiario;
	//Linha 11
	private String dadosConsumoMes3;
	private String dadosConsumoMes6;
	private String dataLeituraAnterior;
	private String dataLeituraAtual;
	//Linha 12
	private String descricaoTipoConsumo;
	private String descricaoAnormalidadeConsumo;
	//Linha 13
	private String quantidadeEconomiaConta;
	private String consumoEconomia;
	private String codigoAuxiliarString;
	private String mesagemConsumoString;

	//Linha 17
	private String valorContaString;
	//Linha 18
	private String primeiraParte;
	//Linha 19
	private String segundaParte;
	//Linha 20
	private String terceiraParte;
	//Linha 21
	private String nomeGerenciaRegional;
	private String mesAnoFormatado;
	//Linha 22
	private String numeroIndiceTurbidez;
	private String numeroCloroResidual;
	
	//Linha 24
	private String representacaoNumericaCodBarraFormatada;
	//Linha 25
	private String representacaoNumericaCodBarraSemDigito;
	//Linha28
	private String dataValidade;
	//Linha 31
	private String grupo;	
	private String firma;
	
	private String idConta;
	
	private String totalPaginasRelatorio;
	
	private String contaSemCodigoBarras;
	
	private String numeroDocumento;
	private String numeroCpfCnpj;
	
	private String codigoDebitoAutomatico;
	
	//só aparece na CAERN
	private String rotaEntrega;
	private String debitoCreditoSituacaoAtualConta;
	private String contaPaga;
	
	private String numeroNitrato;
	private String numeroColiformesTotais;
	private String numeroPH;
	
	
	// -------------------------------------------- INICIO PARTE ESTATICA --------------------------------------------------------
	
	// QUALIDADE DE AGUA
		
	private String corAparenteExigida;
	private String corAparenteRealizada;
	private String corAparenteLegislacao;
		
	private String turbidezExigida;
	private String turbidezRealizada;
	private String turbidezLegislacao;
		
	private String cloroResidualLivreExigido;
	private String cloroResidualLivreRealizado;
	private String cloroResidualLivreLegislacao;
		
	private String coliformesTotaisExigidos;
	private String coliformesTotaisRealizado;
	private String coliformesTotaisLegislacao;
		
	private String escherichiaColiExigida;
	private String escherichiaColiRealizada;
	private String escherichiaColiLegislacao;
	
	// DADOS HIDROMETRO
	private String numHidrometro;
	
	// DADOS TIPOS DE CONSUMO AGUA ESGOTO
	private String consumoTipoDescricaoAbreviadaAgua;
	private String descricaoAnormalidadeConsumoAgua;
		
	private String consumoTipoDescricaoAbreviadaEsgoto;
	private String descricaoAnormalidadeConsumoEsgoto;
	
	// MEDICAO CONTA
	private String leituraAnteriorAgua;
	private String leituraAtualAgua;
	private String leituraFaturamentoAgua;
	private String descricaoAnormalidadeLeituraAgua;
	private String consumoMedioMesAgua;
		
	private String leituraAnteriorEsgoto;
	private String leituraAtualEsgoto;
	private String leituraFaturamentoEsgoto;
	private String descricaoAnormalidadeLeituraEsgoto;
	private String consumoMedioMesEsgoto;
	
	private String consumoAguaMedioDiario;
	private String volumeEsgotoMedioDiario;
	
	private String dtLeituraAnterior;
	private String dtLeituraAtual;
	
	// CONSUMO ANTERIOR AGUA
	private String consumoHistoricoAguaMes1;
	private String consumoHistoricoAguaMes2;
	private String consumoHistoricoAguaMes3;
	private String consumoHistoricoAguaMes4;
	private String consumoHistoricoAguaMes5;
	private String consumoHistoricoAguaMes6;
		
	// CONSUMO ANTERIOR ESGOTO
	private String consumoHistoricoEsgotoMes1;
	private String consumoHistoricoEsgotoMes2;
	private String consumoHistoricoEsgotoMes3;
	private String consumoHistoricoEsgotoMes4;
	private String consumoHistoricoEsgotoMes5;
	private String consumoHistoricoEsgotoMes6;
	
	// MEDIA CONSUMO AGUA
	private String mediaConsumoAgua;
		
	// MEDIA VOLUME ESGOTO
	private String mediaConsumoEsgoto;
	
	// VALOR PIS COFINS
	private String somaValorAguaEsgoto;
	private String valorImpostoPis;
	private String valorImpostoCofins;
	
	// QUANTIDADE ECONOMIAS CATEGORIA
	private String residencial;
	private String comercial;
	private String industrial;
	private String publico;
	
	// ARRAY COM TAMANHO DO GRAFICO CONSUMO AGUA
		private Integer[] tamanhoGraficoConsumoAgua;
		
	// ARRAY COM TAMANHO DO GRAFICO VOLUME AGUA
	private Integer[] tamanhoGraficoVolumeEsgoto;
		
	// ARRAY COM O TAMANHO DA MEDIA GRAFICO CONSUMO AGUA VOLUME ESGOTO
	private Integer[] tamanhoGraficoMediaConsumoAguaVolumeEsgoto;
		
	// -------------------------------------------- FIM PARTE ESTATICA --------------------------------------------------------
		
	
	public Relatorio2ViaContaBean(
			    String indicadorPrimeiraPagina, 
			    Collection colecaoDetail,
	    		String descricaoLocalidade,
	    		String matriculaImovelFormatada,
	    		String dataVencimento,
	    		String nomeCliente,
	    		String enderecoImovel,
	    		String fatura,
	    		String inscricao,
	    		String idClienteResponsavel,
	    		String enderecoClienteResponsavel,
	    		String descricaoAguaSituacao,
	    		String descricaoEsgotoSituacao,
	    		String dadosConsumoMes1,
	    		String dadosConsumoMes4,
	    		String dadosConsumoMes2,
	    		String dadosConsumoMes5,
	    		String leituraAnterior,
	    		String leituraAtual,
	    		String consumoFaturamento,
	    		String diasConsumo,
	    		String consumoMedioDiario,
	    		String dadosConsumoMes3,
	    		String dadosConsumoMes6,
	    		String dataLeituraAnterior,
	    		String dataLeituraAtual,
	    		String descricaoTipoConsumo,
	    		String descricaoAnormalidadeConsumo,
	    		String quantidadeEconomiaConta,
	    		String consumoEconomia,
	    		String codigoAuxiliarString,
	    		String mesagemConsumoString,
	    		String valorContaString,
	    		String primeiraParte,
	    		String segundaParte,
	    		String terceiraParte,
	    		String nomeGerenciaRegional,
	    		String mesAnoFormatado,
	    		String numeroIndiceTurbidez,
	    		String numeroCloroResidual,	
	    		String numeroNitrato,
	    		String numeroColiformesTotais,
	    		String numeroPH,
	    		String representacaoNumericaCodBarraFormatada,
	    		String representacaoNumericaCodBarraSemDigito,
	    		String dataValidade,
	    		String grupo,	
	    		String firma,
	    		String totalPaginasRelatorio,
	    		String idConta,
	    		String rotaEntrega,
	    		String contaSemCodigoBarras,
	    		String debitoCreditoSituacaoAtualConta,
	    		String contaPaga,
	    		String enderecoClienteResponsavelLinha2,
	    		Collection colecaoBoleto) {
	    	
		    this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
			this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
			this.arrayRelatorio2ViaContaDetailBean.addAll(colecaoDetail);
			this.arrayJRDetail = new JRBeanCollectionDataSource(
					this.arrayRelatorio2ViaContaDetailBean);
		  
	    	this.descricaoLocalidade = descricaoLocalidade;
	    	this.matriculaImovelFormatada = matriculaImovelFormatada;
	    	this.dataVencimento = dataVencimento;
	    	this.nomeCliente = nomeCliente;
	    	this.enderecoImovel = enderecoImovel;
	    	this.fatura = fatura;
	    	this.inscricao = inscricao;
	    	this.idClienteResponsavel = idClienteResponsavel;
	    	this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	    	this.descricaoAguaSituacao = descricaoAguaSituacao;
	    	this.descricaoEsgotoSituacao = descricaoEsgotoSituacao;
	    	this.dadosConsumoMes1 = dadosConsumoMes1;
	    	this.dadosConsumoMes4 = dadosConsumoMes4;
	    	this.dadosConsumoMes2 = dadosConsumoMes2;
	    	this.dadosConsumoMes5 = dadosConsumoMes5;
	    	this.leituraAnterior = leituraAnterior;
	    	this.leituraAtual = leituraAtual;
	    	this.consumoFaturamento = consumoFaturamento;
	    	this.diasConsumo = diasConsumo;
	    	this.consumoMedioDiario = consumoMedioDiario;
	    	this.dadosConsumoMes3 = dadosConsumoMes3;
	    	this.dadosConsumoMes6 = dadosConsumoMes6;
	    	this.dataLeituraAnterior = dataLeituraAnterior;
	    	this.dataLeituraAtual = dataLeituraAtual;
	    	this.descricaoTipoConsumo = descricaoTipoConsumo;
	    	this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	    	this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	    	this.consumoEconomia = consumoEconomia;
	    	this.codigoAuxiliarString = codigoAuxiliarString;
	    	this.mesagemConsumoString = mesagemConsumoString;
	    	this.valorContaString = valorContaString;
	    	this.primeiraParte = primeiraParte;
	    	this.segundaParte = segundaParte;
	    	this.terceiraParte = terceiraParte;
	    	this.nomeGerenciaRegional = nomeGerenciaRegional;
	    	this.mesAnoFormatado = mesAnoFormatado;
	    	this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	    	this.numeroCloroResidual = numeroCloroResidual;
	    	this.numeroNitrato = numeroNitrato;
	    	this.numeroColiformesTotais = numeroColiformesTotais;
	    	this.numeroPH = numeroPH;
	    	this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	    	this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	    	this.dataValidade = dataValidade;
	    	this.grupo = grupo;	
	    	this.firma = firma;
	    	this.totalPaginasRelatorio = totalPaginasRelatorio;
	    	this.idConta = idConta;
	    	this.rotaEntrega = rotaEntrega;
	    	this.contaSemCodigoBarras = contaSemCodigoBarras;
	    	this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	    	this.contaPaga = contaPaga;
	    	this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
	    	
	    	this.arrayRelatorio2ViaContaBoletoBancarioBean = new ArrayList();
			this.arrayRelatorio2ViaContaBoletoBancarioBean.addAll(colecaoBoleto);
			this.arrayJRBoleto = new JRBeanCollectionDataSource(
					this.arrayRelatorio2ViaContaBoletoBancarioBean);
	    }

	

	public Relatorio2ViaContaBean(EmitirContaHelper emitirContaHelper,
			int indicadorPrimeiraPagina,
			Collection colecaoDetail,
			String dataVencimentoFormatada,
			String enderecoClienteResponsavel,
			int totalPaginasRelatorio,
			String codigoRota,
			String debitoCreditoSituacaoAtualConta,
			String contaPaga,
			String enderecoClienteResponsavelLinha2,
			Collection colecaoBoleto,
			int boleto){
		
		this.indicadorPrimeiraPagina = "" + indicadorPrimeiraPagina;
		this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
		this.arrayRelatorio2ViaContaDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorio2ViaContaDetailBean);
		this.descricaoLocalidade = emitirContaHelper.getDescricaoLocalidade();
		this.matriculaImovelFormatada = emitirContaHelper.getMatriculaImovelFormatada();
		this.dataVencimento = dataVencimentoFormatada;
		this.nomeCliente = emitirContaHelper.getNomeCliente();
		this.enderecoImovel = emitirContaHelper.getEnderecoImovel();
		this.fatura = emitirContaHelper.getFatura();
		this.inscricao = emitirContaHelper.getInscricaoImovel();
		this.idClienteResponsavel = emitirContaHelper.getIdClienteResponsavel();
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.descricaoAguaSituacao = emitirContaHelper.getDescricaoLigacaoAguaSituacao();
		this.descricaoEsgotoSituacao = emitirContaHelper.getDescricaoLigacaoEsgotoSituacao();
		this.dadosConsumoMes1 = emitirContaHelper.getDadosConsumoMes1();
		this.dadosConsumoMes4 = emitirContaHelper.getDadosConsumoMes4();
		this.dadosConsumoMes2 = emitirContaHelper.getDadosConsumoMes2();
		this.dadosConsumoMes5 = emitirContaHelper.getDadosConsumoMes5();
		this.leituraAnterior = emitirContaHelper.getLeituraAnterior();
		this.leituraAtual = emitirContaHelper.getLeituraAtual();
		this.consumoFaturamento = emitirContaHelper.getConsumoFaturamento();
		this.diasConsumo = emitirContaHelper.getDiasConsumo();
		this.consumoMedioDiario = emitirContaHelper.getConsumoMedioDiario();
		this.dadosConsumoMes3 = emitirContaHelper.getDadosConsumoMes3();
		this.dadosConsumoMes6 = emitirContaHelper.getDadosConsumoMes6();
		this.dataLeituraAnterior = emitirContaHelper.getDataLeituraAnterior();
		this.dataLeituraAtual = emitirContaHelper.getDataLeituraAtual();
		this.descricaoTipoConsumo = emitirContaHelper.getDescricaoTipoConsumo();
		this.descricaoAnormalidadeConsumo = emitirContaHelper.getDescricaoAnormalidadeConsumo();
		this.quantidadeEconomiaConta = emitirContaHelper.getQuantidadeEconomiaConta();
		this.consumoEconomia = emitirContaHelper.getConsumoEconomia();
		this.codigoAuxiliarString = emitirContaHelper.getCodigoAuxiliarString();
		this.mesagemConsumoString = emitirContaHelper.getMensagemConsumoString();
		this.valorContaString = emitirContaHelper.getValorContaString();
		this.primeiraParte = emitirContaHelper.getPrimeiraParte();
		this.segundaParte = emitirContaHelper.getSegundaParte();
		this.terceiraParte = emitirContaHelper.getTerceiraParte();
		this.nomeGerenciaRegional = emitirContaHelper.getNomeGerenciaRegional();
		this.mesAnoFormatado = emitirContaHelper.getMesAnoFormatado();
		this.numeroIndiceTurbidez = emitirContaHelper.getNumeroIndiceTurbidez();
		this.numeroCloroResidual = emitirContaHelper.getNumeroCloroResidual();
		this.numeroNitrato = emitirContaHelper.getNumeroNitrato();
		this.numeroColiformesTotais = emitirContaHelper.getValorMedioColiformesTotais();
		this.numeroPH = emitirContaHelper.getValorMedioPh();
		this.representacaoNumericaCodBarraFormatada = emitirContaHelper.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito = emitirContaHelper.getRepresentacaoNumericaCodBarraSemDigito();
		this.dataValidade = emitirContaHelper.getDataValidade();
		this.grupo = emitirContaHelper.getIdFaturamentoGrupo().toString();
		this.firma = emitirContaHelper.getIdEmpresa().toString();
		this.totalPaginasRelatorio = "" + totalPaginasRelatorio;
		this.idConta = emitirContaHelper.getIdConta().toString();
		this.rotaEntrega = codigoRota;
		this.contaSemCodigoBarras = emitirContaHelper.getContaSemCodigoBarras();
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
		this.contaPaga = contaPaga;
		this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
		this.arrayRelatorio2ViaContaBoletoBancarioBean = new ArrayList();
		this.arrayRelatorio2ViaContaBoletoBancarioBean.addAll(colecaoBoleto);
		this.arrayJRBoleto = new JRBeanCollectionDataSource(
				this.arrayRelatorio2ViaContaBoletoBancarioBean);
		this.boleto = "" + boleto;
		this.numeroDocumento = (""+emitirContaHelper.getAmReferencia() ) +
							   (emitirContaHelper.getIdImovel().toString());
		
		String documentoCpfCnpj = "";
		if (emitirContaHelper.getCpf() != null && !emitirContaHelper.getCpf().equals("")){
			documentoCpfCnpj = Util.formatarCpf(emitirContaHelper.getCpf()) ;
		}else if (emitirContaHelper.getCnpj() != null && !emitirContaHelper.getCnpj().equals("")){
			documentoCpfCnpj = Util.formatarCnpj(emitirContaHelper.getCnpj());
		}
		
		this.numeroCpfCnpj = documentoCpfCnpj;
		
		
	}
	
	
	
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorio2ViaContaDetailBean() {
		return arrayRelatorio2ViaContaDetailBean;
	}

	public void setArrayRelatorio2ViaContaDetailBean(
			ArrayList arrayRelatorio2ViaContaDetailBean) {
		this.arrayRelatorio2ViaContaDetailBean = arrayRelatorio2ViaContaDetailBean;
	}

	public String getIndicadorPrimeiraPagina() {
		return indicadorPrimeiraPagina;
	}

	public void setIndicadorPrimeiraPagina(String indicadorPrimeiraPagina) {
		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
	}

	public String getCodigoAuxiliarString() {
		return codigoAuxiliarString;
	}

	public void setCodigoAuxiliarString(String codigoAuxiliarString) {
		this.codigoAuxiliarString = codigoAuxiliarString;
	}

	public String getConsumoEconomia() {
		return consumoEconomia;
	}

	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}

	public String getConsumoFaturamento() {
		return consumoFaturamento;
	}

	public void setConsumoFaturamento(String consumoFaturamento) {
		this.consumoFaturamento = consumoFaturamento;
	}

	public String getConsumoMedioDiario() {
		return consumoMedioDiario;
	}

	public void setConsumoMedioDiario(String consumoMedioDiario) {
		this.consumoMedioDiario = consumoMedioDiario;
	}

	public String getDadosConsumoMes1() {
		return dadosConsumoMes1;
	}

	public void setDadosConsumoMes1(String dadosConsumoMes1) {
		this.dadosConsumoMes1 = dadosConsumoMes1;
	}

	public String getDadosConsumoMes2() {
		return dadosConsumoMes2;
	}

	public void setDadosConsumoMes2(String dadosConsumoMes2) {
		this.dadosConsumoMes2 = dadosConsumoMes2;
	}

	public String getDadosConsumoMes3() {
		return dadosConsumoMes3;
	}

	public void setDadosConsumoMes3(String dadosConsumoMes3) {
		this.dadosConsumoMes3 = dadosConsumoMes3;
	}

	public String getDadosConsumoMes4() {
		return dadosConsumoMes4;
	}

	public void setDadosConsumoMes4(String dadosConsumoMes4) {
		this.dadosConsumoMes4 = dadosConsumoMes4;
	}

	public String getDadosConsumoMes5() {
		return dadosConsumoMes5;
	}

	public void setDadosConsumoMes5(String dadosConsumoMes5) {
		this.dadosConsumoMes5 = dadosConsumoMes5;
	}

	public String getDadosConsumoMes6() {
		return dadosConsumoMes6;
	}

	public void setDadosConsumoMes6(String dadosConsumoMes6) {
		this.dadosConsumoMes6 = dadosConsumoMes6;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricaoAguaSituacao() {
		return descricaoAguaSituacao;
	}

	public void setDescricaoAguaSituacao(String descricaoAguaSituacao) {
		this.descricaoAguaSituacao = descricaoAguaSituacao;
	}

	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}

	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}

	public String getDescricaoEsgotoSituacao() {
		return descricaoEsgotoSituacao;
	}

	public void setDescricaoEsgotoSituacao(String descricaoEsgotoSituacao) {
		this.descricaoEsgotoSituacao = descricaoEsgotoSituacao;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoTipoConsumo() {
		return descricaoTipoConsumo;
	}

	public void setDescricaoTipoConsumo(String descricaoTipoConsumo) {
		this.descricaoTipoConsumo = descricaoTipoConsumo;
	}

	public String getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}

	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getFatura() {
		return fatura;
	}

	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getMatriculaImovelFormatada() {
		return matriculaImovelFormatada;
	}

	public void setMatriculaImovelFormatada(String matriculaImovelFormatada) {
		this.matriculaImovelFormatada = matriculaImovelFormatada;
	}

	public String getMesagemConsumoString() {
		return mesagemConsumoString;
	}

	public void setMesagemConsumoString(String mesagemConsumoString) {
		this.mesagemConsumoString = mesagemConsumoString;
	}

	public String getMesAnoFormatado() {
		return mesAnoFormatado;
	}

	public void setMesAnoFormatado(String mesAnoFormatado) {
		this.mesAnoFormatado = mesAnoFormatado;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNumeroCloroResidual() {
		return numeroCloroResidual;
	}

	public void setNumeroCloroResidual(String numeroCloroResidual) {
		this.numeroCloroResidual = numeroCloroResidual;
	}

	public String getNumeroIndiceTurbidez() {
		return numeroIndiceTurbidez;
	}

	public void setNumeroIndiceTurbidez(String numeroIndiceTurbidez) {
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	}
	
	public String getNumeroColiformesTotais() {
		return numeroColiformesTotais;
	}
	
	public void setNumeroColiformesTotais(String numeroColiformesTotais) {
		this.numeroColiformesTotais = numeroColiformesTotais;
	}
	
	public String getNumeroNitrato() {
		return numeroNitrato;
	}
	
	public void setNumeroNitrato(String numeroNitrato) {
		this.numeroNitrato = numeroNitrato;
	}
	
	public String getNumeroPH() {
		return numeroPH;
	}

	public void setNumeroPH(String numeroPH) {
		this.numeroPH = numeroPH;
	}

	public String getPrimeiraParte() {
		return primeiraParte;
	}

	public void setPrimeiraParte(String primeiraParte) {
		this.primeiraParte = primeiraParte;
	}

	public String getQuantidadeEconomiaConta() {
		return quantidadeEconomiaConta;
	}

	public void setQuantidadeEconomiaConta(String quantidadeEconomiaConta) {
		this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getSegundaParte() {
		return segundaParte;
	}

	public void setSegundaParte(String segundaParte) {
		this.segundaParte = segundaParte;
	}

	public String getTerceiraParte() {
		return terceiraParte;
	}

	public void setTerceiraParte(String terceiraParte) {
		this.terceiraParte = terceiraParte;
	}

	public String getValorContaString() {
		return valorContaString;
	}

	public void setValorContaString(String valorContaString) {
		this.valorContaString = valorContaString;
	}

	public String getTotalPaginasRelatorio() {
		return totalPaginasRelatorio;
	}

	public void setTotalPaginasRelatorio(String totalPaginasRelatorio) {
		this.totalPaginasRelatorio = totalPaginasRelatorio;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getRotaEntrega() {
		return rotaEntrega;
	}

	public void setRotaEntrega(String rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}


	public String getContaSemCodigoBarras() {
		return contaSemCodigoBarras;
	}


	public void setContaSemCodigoBarras(String contaSemCodigoBarras) {
		this.contaSemCodigoBarras = contaSemCodigoBarras;
	}

	public String getDebitoCreditoSituacaoAtualConta() {
		return debitoCreditoSituacaoAtualConta;
	}

	public void setDebitoCreditoSituacaoAtualConta(
			String debitoCreditoSituacaoAtualConta) {
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	}

	public String getContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(String contaPaga) {
		this.contaPaga = contaPaga;
	}

	public String getEnderecoClienteResponsavelLinha2() {
		return enderecoClienteResponsavelLinha2;
	}

	public void setEnderecoClienteResponsavelLinha2(
			String enderecoClienteResponsavelLinha2) {
		this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
	}

	public JRBeanCollectionDataSource getArrayJRBoleto() {
		return arrayJRBoleto;
	}

	public void setArrayJRBoleto(JRBeanCollectionDataSource arrayJRBoleto) {
		this.arrayJRBoleto = arrayJRBoleto;
	}

	public ArrayList getArrayRelatorio2ViaContaBoletoBancarioBean() {
		return arrayRelatorio2ViaContaBoletoBancarioBean;
	}

	public void setArrayRelatorio2ViaContaBoletoBancarioBean(
			ArrayList arrayRelatorio2ViaContaBoletoBancarioBean) {
		this.arrayRelatorio2ViaContaBoletoBancarioBean = arrayRelatorio2ViaContaBoletoBancarioBean;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroCpfCnpj() {
		return numeroCpfCnpj;
	}

	public void setNumeroCpfCnpj(String numeroCpfCnpj) {
		this.numeroCpfCnpj = numeroCpfCnpj;
	}



	public String getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}



	public void setCodigoDebitoAutomatico(String codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}



	public String getEmpresa() {
		return empresa;
	}



	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}



	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}



	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

	// -------------------------------- JONATHAN GET(S) E SET(S) INICIO --------------------------------------
	
	public String getCorAparenteExigida() {
		return corAparenteExigida;
	}

	public void setCorAparenteExigida(String corAparenteExigida) {
		this.corAparenteExigida = corAparenteExigida;
	}

	public String getCorAparenteRealizada() {
		return corAparenteRealizada;
	}

	public void setCorAparenteRealizada(String corAparenteRealizada) {
		this.corAparenteRealizada = corAparenteRealizada;
	}

	public String getCorAparenteLegislacao() {
		return corAparenteLegislacao;
	}

	public void setCorAparenteLegislacao(String corAparenteLegislacao) {
		this.corAparenteLegislacao = corAparenteLegislacao;
	}

	public String getTurbidezExigida() {
		return turbidezExigida;
	}

	public void setTurbidezExigida(String turbidezExigida) {
		this.turbidezExigida = turbidezExigida;
	}

	public String getTurbidezRealizada() {
		return turbidezRealizada;
	}

	public void setTurbidezRealizada(String turbidezRealizada) {
		this.turbidezRealizada = turbidezRealizada;
	}

	public String getTurbidezLegislacao() {
		return turbidezLegislacao;
	}

	public void setTurbidezLegislacao(String turbidezLegislacao) {
		this.turbidezLegislacao = turbidezLegislacao;
	}

	public String getCloroResidualLivreExigido() {
		return cloroResidualLivreExigido;
	}

	public void setCloroResidualLivreExigido(String cloroResidualLivreExigido) {
		this.cloroResidualLivreExigido = cloroResidualLivreExigido;
	}

	public String getCloroResidualLivreRealizado() {
		return cloroResidualLivreRealizado;
	}

	public void setCloroResidualLivreRealizado(String cloroResidualLivreRealizado) {
			this.cloroResidualLivreRealizado = cloroResidualLivreRealizado;
	}

	public String getCloroResidualLivreLegislacao() {
		return cloroResidualLivreLegislacao;
	}

	public void setCloroResidualLivreLegislacao(String cloroResidualLivreLegislacao) {
		this.cloroResidualLivreLegislacao = cloroResidualLivreLegislacao;
	}

	public String getColiformesTotaisExigidos() {
		return coliformesTotaisExigidos;
	}

	public void setColiformesTotaisExigidos(String coliformesTotaisExigidos) {
		this.coliformesTotaisExigidos = coliformesTotaisExigidos;
	}

	public String getColiformesTotaisRealizado() {
		return coliformesTotaisRealizado;
	}

	public void setColiformesTotaisRealizado(String coliformesTotaisRealizado) {
		this.coliformesTotaisRealizado = coliformesTotaisRealizado;
	}

	public String getColiformesTotaisLegislacao() {
		return coliformesTotaisLegislacao;
	}

	public void setColiformesTotaisLegislacao(String coliformesTotaisLegislacao) {
		this.coliformesTotaisLegislacao = coliformesTotaisLegislacao;
	}

	public String getEscherichiaColiExigida() {
		return escherichiaColiExigida;
	}

	public void setEscherichiaColiExigida(String escherichiaColiExigida) {
		this.escherichiaColiExigida = escherichiaColiExigida;
	}

	public String getEscherichiaColiRealizada() {
		return escherichiaColiRealizada;
	}

	public void setEscherichiaColiRealizada(String escherichiaColiRealizada) {
		this.escherichiaColiRealizada = escherichiaColiRealizada;
	}

	public String getEscherichiaColiLegislacao() {
		return escherichiaColiLegislacao;
	}

	public void setEscherichiaColiLegislacao(String escherichiaColiLegislacao) {
		this.escherichiaColiLegislacao = escherichiaColiLegislacao;
	}

	public String getNumHidrometro() {
		return numHidrometro;
	}

	public void setNumHidrometro(String numHidrometro) {
		this.numHidrometro = numHidrometro;
	}

	public String getConsumoTipoDescricaoAbreviadaAgua() {
		return consumoTipoDescricaoAbreviadaAgua;
	}

	public void setConsumoTipoDescricaoAbreviadaAgua(String consumoTipoDescricaoAbreviadaAgua) {
		this.consumoTipoDescricaoAbreviadaAgua = consumoTipoDescricaoAbreviadaAgua;
	}

	public String getDescricaoAnormalidadeConsumoAgua() {
		return descricaoAnormalidadeConsumoAgua;
	}

	public void setDescricaoAnormalidadeConsumoAgua(String descricaoAnormalidadeConsumoAgua) {
		this.descricaoAnormalidadeConsumoAgua = descricaoAnormalidadeConsumoAgua;
	}

	public String getConsumoTipoDescricaoAbreviadaEsgoto() {
		return consumoTipoDescricaoAbreviadaEsgoto;
	}

	public void setConsumoTipoDescricaoAbreviadaEsgoto(String consumoTipoDescricaoAbreviadaEsgoto) {
		this.consumoTipoDescricaoAbreviadaEsgoto = consumoTipoDescricaoAbreviadaEsgoto;
	}

	public String getDescricaoAnormalidadeConsumoEsgoto() {
		return descricaoAnormalidadeConsumoEsgoto;
	}

	public void setDescricaoAnormalidadeConsumoEsgoto(String descricaoAnormalidadeConsumoEsgoto) {
		this.descricaoAnormalidadeConsumoEsgoto = descricaoAnormalidadeConsumoEsgoto;
	}

	public String getLeituraAnteriorAgua() {
		return leituraAnteriorAgua;
	}

	public void setLeituraAnteriorAgua(String leituraAnteriorAgua) {
		this.leituraAnteriorAgua = leituraAnteriorAgua;
	}

	public String getLeituraAtualAgua() {
		return leituraAtualAgua;
	}

	public void setLeituraAtualAgua(String leituraAtualAgua) {
		this.leituraAtualAgua = leituraAtualAgua;
	}

	public String getLeituraFaturamentoAgua() {
		return leituraFaturamentoAgua;
	}

	public void setLeituraFaturamentoAgua(String leituraFaturamentoAgua) {
		this.leituraFaturamentoAgua = leituraFaturamentoAgua;
	}

	public String getDescricaoAnormalidadeLeituraAgua() {
		return descricaoAnormalidadeLeituraAgua;
	}

	public void setDescricaoAnormalidadeLeituraAgua(String descricaoAnormalidadeLeituraAgua) {
		this.descricaoAnormalidadeLeituraAgua = descricaoAnormalidadeLeituraAgua;
	}

	public String getConsumoMedioMesAgua() {
		return consumoMedioMesAgua;
	}

	public void setConsumoMedioMesAgua(String consumoMedioMesAgua) {
		this.consumoMedioMesAgua = consumoMedioMesAgua;
	}

	public String getLeituraAnteriorEsgoto() {
		return leituraAnteriorEsgoto;
	}

	public void setLeituraAnteriorEsgoto(String leituraAnteriorEsgoto) {
		this.leituraAnteriorEsgoto = leituraAnteriorEsgoto;
	}

	public String getLeituraAtualEsgoto() {
		return leituraAtualEsgoto;
	}

	public void setLeituraAtualEsgoto(String leituraAtualEsgoto) {
		this.leituraAtualEsgoto = leituraAtualEsgoto;
	}

	public String getLeituraFaturamentoEsgoto() {
		return leituraFaturamentoEsgoto;
	}

	public void setLeituraFaturamentoEsgoto(String leituraFaturamentoEsgoto) {
		this.leituraFaturamentoEsgoto = leituraFaturamentoEsgoto;
	}

	public String getDescricaoAnormalidadeLeituraEsgoto() {
		return descricaoAnormalidadeLeituraEsgoto;
	}

	public void setDescricaoAnormalidadeLeituraEsgoto(String descricaoAnormalidadeLeituraEsgoto) {
		this.descricaoAnormalidadeLeituraEsgoto = descricaoAnormalidadeLeituraEsgoto;
	}

	public String getConsumoMedioMesEsgoto() {
		return consumoMedioMesEsgoto;
	}

	public void setConsumoMedioMesEsgoto(String consumoMedioMesEsgoto) {
		this.consumoMedioMesEsgoto = consumoMedioMesEsgoto;
	}

	public String getDtLeituraAnterior() {
		return dtLeituraAnterior;
	}

	public void setDtLeituraAnterior(String dtLeituraAnterior) {
		this.dtLeituraAnterior = dtLeituraAnterior;
	}

	public String getDtLeituraAtual() {
		return dtLeituraAtual;
	}

	public void setDtLeituraAtual(String dtLeituraAtual) {
		this.dtLeituraAtual = dtLeituraAtual;
	}

	public String getConsumoHistoricoAguaMes1() {
		return consumoHistoricoAguaMes1;
	}

	public void setConsumoHistoricoAguaMes1(String consumoHistoricoAguaMes1) {
		this.consumoHistoricoAguaMes1 = consumoHistoricoAguaMes1;
	}

	public String getConsumoHistoricoAguaMes2() {
		return consumoHistoricoAguaMes2;
	}

	public void setConsumoHistoricoAguaMes2(String consumoHistoricoAguaMes2) {
		this.consumoHistoricoAguaMes2 = consumoHistoricoAguaMes2;
	}

	public String getConsumoHistoricoAguaMes3() {
		return consumoHistoricoAguaMes3;
	}

	public void setConsumoHistoricoAguaMes3(String consumoHistoricoAguaMes3) {
		this.consumoHistoricoAguaMes3 = consumoHistoricoAguaMes3;
	}

	public String getConsumoHistoricoAguaMes4() {
		return consumoHistoricoAguaMes4;
	}

	public void setConsumoHistoricoAguaMes4(String consumoHistoricoAguaMes4) {
		this.consumoHistoricoAguaMes4 = consumoHistoricoAguaMes4;
	}

	public String getConsumoHistoricoAguaMes5() {
		return consumoHistoricoAguaMes5;
	}

	public void setConsumoHistoricoAguaMes5(String consumoHistoricoAguaMes5) {
		this.consumoHistoricoAguaMes5 = consumoHistoricoAguaMes5;
	}

	public String getConsumoHistoricoAguaMes6() {
		return consumoHistoricoAguaMes6;
	}

	public void setConsumoHistoricoAguaMes6(String consumoHistoricoAguaMes6) {
		this.consumoHistoricoAguaMes6 = consumoHistoricoAguaMes6;
	}

	public String getConsumoHistoricoEsgotoMes1() {
		return consumoHistoricoEsgotoMes1;
	}

	public void setConsumoHistoricoEsgotoMes1(String consumoHistoricoEsgotoMes1) {
		this.consumoHistoricoEsgotoMes1 = consumoHistoricoEsgotoMes1;
	}

	public String getConsumoHistoricoEsgotoMes2() {
		return consumoHistoricoEsgotoMes2;
	}

	public void setConsumoHistoricoEsgotoMes2(String consumoHistoricoEsgotoMes2) {
		this.consumoHistoricoEsgotoMes2 = consumoHistoricoEsgotoMes2;
	}

	public String getConsumoHistoricoEsgotoMes3() {
		return consumoHistoricoEsgotoMes3;
	}

	public void setConsumoHistoricoEsgotoMes3(String consumoHistoricoEsgotoMes3) {
		this.consumoHistoricoEsgotoMes3 = consumoHistoricoEsgotoMes3;
	}

	public String getConsumoHistoricoEsgotoMes4() {
		return consumoHistoricoEsgotoMes4;
	}

	public void setConsumoHistoricoEsgotoMes4(String consumoHistoricoEsgotoMes4) {
		this.consumoHistoricoEsgotoMes4 = consumoHistoricoEsgotoMes4;
	}

	public String getConsumoHistoricoEsgotoMes5() {
		return consumoHistoricoEsgotoMes5;
	}

	public void setConsumoHistoricoEsgotoMes5(String consumoHistoricoEsgotoMes5) {
		this.consumoHistoricoEsgotoMes5 = consumoHistoricoEsgotoMes5;
	}

	public String getConsumoHistoricoEsgotoMes6() {
		return consumoHistoricoEsgotoMes6;
	}

	public void setConsumoHistoricoEsgotoMes6(String consumoHistoricoEsgotoMes6) {
		this.consumoHistoricoEsgotoMes6 = consumoHistoricoEsgotoMes6;
	}

	public String getConsumoAguaMedioDiario() {
		return consumoAguaMedioDiario;
	}

	public void setConsumoAguaMedioDiario(String consumoAguaMedioDiario) {
		this.consumoAguaMedioDiario = consumoAguaMedioDiario;
	}

	public String getVolumeEsgotoMedioDiario() {
		return volumeEsgotoMedioDiario;
	}
	
	public void setVolumeEsgotoMedioDiario(String volumeEsgotoMedioDiario) {
		this.volumeEsgotoMedioDiario = volumeEsgotoMedioDiario;
	}

	public String getMediaConsumoAgua() {
		return mediaConsumoAgua;
	}

	public void setMediaConsumoAgua(String mediaConsumoAgua) {
		this.mediaConsumoAgua = mediaConsumoAgua;
	}

	public String getMediaConsumoEsgoto() {
		return mediaConsumoEsgoto;
	}

	public void setMediaConsumoEsgoto(String mediaConsumoEsgoto) {
		this.mediaConsumoEsgoto = mediaConsumoEsgoto;
	}

	public String getSomaValorAguaEsgoto() {
		return somaValorAguaEsgoto;
	}

	public void setSomaValorAguaEsgoto(String somaValorAguaEsgoto) {
		this.somaValorAguaEsgoto = somaValorAguaEsgoto;
	}

	public String getValorImpostoPis() {
		return valorImpostoPis;
	}

	public void setValorImpostoPis(String valorImpostoPis) {
		this.valorImpostoPis = valorImpostoPis;
	}

	public String getValorImpostoCofins() {
		return valorImpostoCofins;
	}

	public void setValorImpostoCofins(String valorImpostoCofins) {
		this.valorImpostoCofins = valorImpostoCofins;
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public String getIndustrial() {
		return industrial;
	}

	public void setIndustrial(String industrial) {
		this.industrial = industrial;
	}

	public String getPublico() {
		return publico;
	}

	public void setPublico(String publico) {
		this.publico = publico;
	}

	public Integer[] getTamanhoGraficoConsumoAgua() {
		return tamanhoGraficoConsumoAgua;
	}

	public void setTamanhoGraficoConsumoAgua(Integer[] tamanhoGraficoConsumoAgua) {
		this.tamanhoGraficoConsumoAgua = tamanhoGraficoConsumoAgua;
	}

	public Integer[] getTamanhoGraficoVolumeEsgoto() {
		return tamanhoGraficoVolumeEsgoto;
	}

	public void setTamanhoGraficoVolumeEsgoto(Integer[] tamanhoGraficoVolumeEsgoto) {
		this.tamanhoGraficoVolumeEsgoto = tamanhoGraficoVolumeEsgoto;
	}

	public Integer[] getTamanhoGraficoMediaConsumoAguaVolumeEsgoto() {
		return tamanhoGraficoMediaConsumoAguaVolumeEsgoto;
	}

	public void setTamanhoGraficoMediaConsumoAguaVolumeEsgoto(Integer[] tamanhoGraficoMediaConsumoAguaVolumeEsgoto) {
		this.tamanhoGraficoMediaConsumoAguaVolumeEsgoto = tamanhoGraficoMediaConsumoAguaVolumeEsgoto;
	}
	// -------------------------------- JONATHAN GET(S) E SET(S) FIM --------------------------------------
}
