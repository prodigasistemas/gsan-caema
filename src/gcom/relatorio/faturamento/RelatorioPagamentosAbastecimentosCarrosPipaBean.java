package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioPagamentosAbastecimentosCarrosPipaBean implements RelatorioBean {

	private Integer qtdeEnviados;
	private Integer qtdeComContrato;
	private Integer qtdeSemContrato;
	private Integer qtdeFaturaveis;
	private Integer qtdeNaoFaturaveis;
	
	private BigDecimal qtdTotalDebitoCobrarVolume;
	private BigDecimal qtdTotalDebitoCobrarValor;
	
	private BigDecimal qtdTotalGuiaPagamentoValor;
	
	private BigDecimal qtdTotalCobradaGsanVolume;
	private BigDecimal qtdTotalCobradaGsanValor;
	
	private BigDecimal qtdTotalAguaFaturaveisVolume;
	private BigDecimal qtdTotalAguaFaturaveisValor;
	
	private BigDecimal qtdTotalAguaNaoFaturaveisVolume;
	private BigDecimal qtdTotalAguaNaoFaturaveisValor;
	
	private BigDecimal qtdTotalAguaGpipaVolume;
	private BigDecimal qtdTotalAguaGpipaValor;
	
	private BigDecimal diferencaQtdGsanGpipaVolume;
	private BigDecimal diferencaQtdGsanGpipaValor;	
	
	private Integer gerenciaRegional;
	private String nomeGerencia;
	
	public Integer getQtdeEnviados() {
		return qtdeEnviados;
	}
	public void setQtdeEnviados(Integer qtdeEnviados) {
		this.qtdeEnviados = qtdeEnviados;
	}
	public Integer getQtdeComContrato() {
		return qtdeComContrato;
	}
	public void setQtdeComContrato(Integer qtdeComContrato) {
		this.qtdeComContrato = qtdeComContrato;
	}
	public Integer getQtdeSemContrato() {
		return qtdeSemContrato;
	}
	public void setQtdeSemContrato(Integer qtdeSemContrato) {
		this.qtdeSemContrato = qtdeSemContrato;
	}
	public Integer getQtdeFaturaveis() {
		return qtdeFaturaveis;
	}
	public void setQtdeFaturaveis(Integer qtdeFaturaveis) {
		this.qtdeFaturaveis = qtdeFaturaveis;
	}
	public Integer getQtdeNaoFaturaveis() {
		return qtdeNaoFaturaveis;
	}
	public void setQtdeNaoFaturaveis(Integer qtdeNaoFaturaveis) {
		this.qtdeNaoFaturaveis = qtdeNaoFaturaveis;
	}
	public BigDecimal getQtdTotalDebitoCobrarVolume() {
		return qtdTotalDebitoCobrarVolume;
	}
	public void setQtdTotalDebitoCobrarVolume(BigDecimal qtdTotalDebitoCobrarVolume) {
		this.qtdTotalDebitoCobrarVolume = qtdTotalDebitoCobrarVolume;
	}
	public BigDecimal getQtdTotalDebitoCobrarValor() {
		return qtdTotalDebitoCobrarValor;
	}
	public void setQtdTotalDebitoCobrarValor(BigDecimal qtdTotalDebitoCobrarValor) {
		this.qtdTotalDebitoCobrarValor = qtdTotalDebitoCobrarValor;
	}
	public BigDecimal getQtdTotalGuiaPagamentoValor() {
		return qtdTotalGuiaPagamentoValor;
	}
	public void setQtdTotalGuiaPagamentoValor(BigDecimal qtdTotalGuiaPagamentoValor) {
		this.qtdTotalGuiaPagamentoValor = qtdTotalGuiaPagamentoValor;
	}
	public BigDecimal getQtdTotalCobradaGsanVolume() {
		return qtdTotalCobradaGsanVolume;
	}
	public void setQtdTotalCobradaGsanVolume(BigDecimal qtdTotalCobradaGsanVolume) {
		this.qtdTotalCobradaGsanVolume = qtdTotalCobradaGsanVolume;
	}
	public BigDecimal getQtdTotalCobradaGsanValor() {
		return qtdTotalCobradaGsanValor;
	}
	public void setQtdTotalCobradaGsanValor(BigDecimal qtdTotalCobradaGsanValor) {
		this.qtdTotalCobradaGsanValor = qtdTotalCobradaGsanValor;
	}
	public BigDecimal getQtdTotalAguaFaturaveisVolume() {
		return qtdTotalAguaFaturaveisVolume;
	}
	public void setQtdTotalAguaFaturaveisVolume(BigDecimal qtdTotalAguaFaturaveisVolume) {
		this.qtdTotalAguaFaturaveisVolume = qtdTotalAguaFaturaveisVolume;
	}
	public BigDecimal getQtdTotalAguaFaturaveisValor() {
		return qtdTotalAguaFaturaveisValor;
	}
	public void setQtdTotalAguaFaturaveisValor(BigDecimal qtdTotalAguaFaturaveisValor) {
		this.qtdTotalAguaFaturaveisValor = qtdTotalAguaFaturaveisValor;
	}
	public BigDecimal getQtdTotalAguaNaoFaturaveisVolume() {
		return qtdTotalAguaNaoFaturaveisVolume;
	}
	public void setQtdTotalAguaNaoFaturaveisVolume(BigDecimal qtdTotalAguaNaoFaturaveisVolume) {
		this.qtdTotalAguaNaoFaturaveisVolume = qtdTotalAguaNaoFaturaveisVolume;
	}
	public BigDecimal getQtdTotalAguaNaoFaturaveisValor() {
		return qtdTotalAguaNaoFaturaveisValor;
	}
	public void setQtdTotalAguaNaoFaturaveisValor(BigDecimal qtdTotalAguaNaoFaturaveisValor) {
		this.qtdTotalAguaNaoFaturaveisValor = qtdTotalAguaNaoFaturaveisValor;
	}
	public BigDecimal getQtdTotalAguaGpipaVolume() {
		return qtdTotalAguaGpipaVolume;
	}
	public void setQtdTotalAguaGpipaVolume(BigDecimal qtdTotalAguaGpipaVolume) {
		this.qtdTotalAguaGpipaVolume = qtdTotalAguaGpipaVolume;
	}
	public BigDecimal getQtdTotalAguaGpipaValor() {
		return qtdTotalAguaGpipaValor;
	}
	public void setQtdTotalAguaGpipaValor(BigDecimal qtdTotalAguaGpipaValor) {
		this.qtdTotalAguaGpipaValor = qtdTotalAguaGpipaValor;
	}
	public BigDecimal getDiferencaQtdGsanGpipaVolume() {
		return diferencaQtdGsanGpipaVolume;
	}
	public void setDiferencaQtdGsanGpipaVolume(BigDecimal diferencaQtdGsanGpipaVolume) {
		this.diferencaQtdGsanGpipaVolume = diferencaQtdGsanGpipaVolume;
	}
	public BigDecimal getDiferencaQtdGsanGpipaValor() {
		return diferencaQtdGsanGpipaValor;
	}
	public void setDiferencaQtdGsanGpipaValor(BigDecimal diferencaQtdGsanGpipaValor) {
		this.diferencaQtdGsanGpipaValor = diferencaQtdGsanGpipaValor;
	}
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getNomeGerencia() {
		return nomeGerencia;
	}
	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}
	
	
}
