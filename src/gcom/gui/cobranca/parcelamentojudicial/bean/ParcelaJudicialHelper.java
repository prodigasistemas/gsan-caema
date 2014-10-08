package gcom.gui.cobranca.parcelamentojudicial.bean;

import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

public class ParcelaJudicialHelper{

	private Integer numeroParcela;
	private Date dataVencimento;
	private BigDecimal valorParcelaFinal;
	private BigDecimal valorParcelaBruto;
	private BigDecimal valorParcelaDescontos;
	private BigDecimal valorParcelaCustas;
	private BigDecimal valorParcelaHonorarios;
	private BigDecimal valorParcelaJuros;
	public Integer getNumeroParcela() {
		return numeroParcela;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public BigDecimal getValorParcelaFinal() {
		return valorParcelaFinal;
	}
	public BigDecimal getValorParcelaBruto() {
		return valorParcelaBruto;
	}
	public BigDecimal getValorParcelaDescontos() {
		return valorParcelaDescontos;
	}
	public BigDecimal getValorParcelaCustas() {
		return valorParcelaCustas;
	}
	public BigDecimal getValorParcelaHonorarios() {
		return valorParcelaHonorarios;
	}
	public BigDecimal getValorParcelaJuros() {
		return valorParcelaJuros;
	}
	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public void setValorParcelaFinal(BigDecimal valorParcelaFinal) {
		this.valorParcelaFinal = valorParcelaFinal;
	}
	public void setValorParcelaBruto(BigDecimal valorParcelaBruto) {
		this.valorParcelaBruto = valorParcelaBruto;
	}
	public void setValorParcelaDescontos(BigDecimal valorParcelaDescontos) {
		this.valorParcelaDescontos = valorParcelaDescontos;
	}
	public void setValorParcelaCustas(BigDecimal valorParcelaCustas) {
		this.valorParcelaCustas = valorParcelaCustas;
	}
	public void setValorParcelaHonorarios(BigDecimal valorParcelaHonorarios) {
		this.valorParcelaHonorarios = valorParcelaHonorarios;
	}
	public void setValorParcelaJuros(BigDecimal valorParcelaJuros) {
		this.valorParcelaJuros = valorParcelaJuros;
	}
	
	//Métodos de exibição no formulário
	//--------------------------------------------------
	public String getNumeroParcelaFormatado(){
		return this.numeroParcela.toString();
	}
	
	public String getValorParcelaFormatado(){
		return Util.formatarMoedaReal(this.valorParcelaFinal);
	}
	
	public String getDataVencimentoFormatada(){
		return Util.formatarData(this.dataVencimento);
	}
	//--------------------------------------------------
	
	
	
}
