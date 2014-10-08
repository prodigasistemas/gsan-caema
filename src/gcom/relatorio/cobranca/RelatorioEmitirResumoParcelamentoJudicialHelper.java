package gcom.relatorio.cobranca;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * [UC1461] Emitir Resumo do Parcelamento Judicial
 * 
 * @author Maxwell Moreira
 *
 * @date 10/04/2013
 */

public class RelatorioEmitirResumoParcelamentoJudicialHelper implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
	private String numeroProcessoJudicial;
	private String imovelPrincipal;
	private String enderecoImovel;
	private String clienteResponsavel;
	private String funcionario;
	private String advogadoResponsavel;
	private String numeroOAB;
	private String situacao;
	private String dataParcelamento;
	private String valorDebito;
	private String valorAcordo;
	private String valorEntrada;
	private String valorCustas;
	private String valorHonorarios;
	private String valorParcelado;
	private String percentualDesconto;
	private String percentualCustas;
	private String percentualHonorarios;
	private String taxaJuros;
	private String quantidadeParcelas;
	private String indicadorDesconto;
	private String indicadorCustas;
	private String indicadorHonorarios;
	private String indicadorJuros;
	private String indicadorInformarValorParcela;
	private String indicadorEntrada;
	private BigDecimal somatorioValorAtualizado;
	
	public String getNumeroProcessoJudicial() {
		return numeroProcessoJudicial;
	}
	public void setNumeroProcessoJudicial(String numeroProcessoJudicial) {
		this.numeroProcessoJudicial = numeroProcessoJudicial;
	}
	public String getImovelPrincipal() {
		return imovelPrincipal;
	}
	public void setImovelPrincipal(String imovelPrincipal) {
		this.imovelPrincipal = imovelPrincipal;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	public String getClienteResponsavel() {
		return clienteResponsavel;
	}
	public void setClienteResponsavel(String clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	public String getAdvogadoResponsavel() {
		return advogadoResponsavel;
	}
	public void setAdvogadoResponsavel(String advogadoResponsavel) {
		this.advogadoResponsavel = advogadoResponsavel;
	}
	public String getNumeroOAB() {
		return numeroOAB;
	}
	public void setNumeroOAB(String numeroOAB) {
		this.numeroOAB = numeroOAB;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDataParcelamento() {
		return dataParcelamento;
	}
	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}
	public String getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	public String getValorAcordo() {
		return valorAcordo;
	}
	public void setValorAcordo(String valorAcordo) {
		this.valorAcordo = valorAcordo;
	}
	public String getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(String valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public String getValorCustas() {
		return valorCustas;
	}
	public void setValorCustas(String valorCustas) {
		this.valorCustas = valorCustas;
	}
	public String getValorHonorarios() {
		return valorHonorarios;
	}
	public void setValorHonorarios(String valorHonorarios) {
		this.valorHonorarios = valorHonorarios;
	}
	public String getValorParcelado() {
		return valorParcelado;
	}
	public void setValorParcelado(String valorParcelado) {
		this.valorParcelado = valorParcelado;
	}
	public String getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(String percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public String getPercentualCustas() {
		return percentualCustas;
	}
	public void setPercentualCustas(String percentualCustas) {
		this.percentualCustas = percentualCustas;
	}
	public String getPercentualHonorarios() {
		return percentualHonorarios;
	}
	public void setPercentualHonorarios(String percentualHonorarios) {
		this.percentualHonorarios = percentualHonorarios;
	}
	public String getTaxaJuros() {
		return taxaJuros;
	}
	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}
	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public String getIndicadorDesconto() {
		return indicadorDesconto;
	}
	public void setIndicadorDesconto(String indicadorDesconto) {
		this.indicadorDesconto = indicadorDesconto;
	}
	public String getIndicadorCustas() {
		return indicadorCustas;
	}
	public void setIndicadorCustas(String indicadorCustas) {
		this.indicadorCustas = indicadorCustas;
	}
	public String getIndicadorHonorarios() {
		return indicadorHonorarios;
	}
	public void setIndicadorHonorarios(String indicadorHonorarios) {
		this.indicadorHonorarios = indicadorHonorarios;
	}
	public String getIndicadorJuros() {
		return indicadorJuros;
	}
	public void setIndicadorJuros(String indicadorJuros) {
		this.indicadorJuros = indicadorJuros;
	}
	public String getIndicadorInformarValorParcela() {
		return indicadorInformarValorParcela;
	}
	public void setIndicadorInformarValorParcela(
			String indicadorInformarValorParcela) {
		this.indicadorInformarValorParcela = indicadorInformarValorParcela;
	}
	public String getIndicadorEntrada() {
		return indicadorEntrada;
	}
	public void setIndicadorEntrada(String indicadorEntrada) {
		this.indicadorEntrada = indicadorEntrada;
	}
	public BigDecimal getSomatorioValorAtualizado() {
		return somatorioValorAtualizado;
	}
	public void setSomatorioValorAtualizado(BigDecimal somatorioValorAtualizado) {
		this.somatorioValorAtualizado = somatorioValorAtualizado;
	}
}