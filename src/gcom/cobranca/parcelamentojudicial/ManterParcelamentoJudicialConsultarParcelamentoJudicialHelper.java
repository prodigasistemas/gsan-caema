package gcom.cobranca.parcelamentojudicial;

import java.io.Serializable;

public class ManterParcelamentoJudicialConsultarParcelamentoJudicialHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idParcelamentoJudicial;
	private String idCliente;
	private String nomeCliente;
	private String situacao;
	private String dataParcelamento;
	private String numeroProcesso;
	private String nomeAdvogado;
	private String debitoAtualizado;
	private String valorAcordo;
	private String desconto;
	private String juros;
	private String percentualDesconto;
	private String custas;
	private String honorarios;
	private String valorParcelamento;
	private String valorEntrada;
	
	public ManterParcelamentoJudicialConsultarParcelamentoJudicialHelper(){}
	
	
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	public String getJuros() {
		return juros;
	}
	public void setJuros(String juros) {
		this.juros = juros;
	}
	public String getIdParcelamentoJudicial() {
		return idParcelamentoJudicial;
	}
	public void setIdParcelamentoJudicial(String idParcelamentoJudicial) {
		this.idParcelamentoJudicial = idParcelamentoJudicial;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}
	public String getNomeAdvogado() {
		return nomeAdvogado;
	}
	public void setNomeAdvogado(String nomeAdvogado) {
		this.nomeAdvogado = nomeAdvogado;
	}
	public String getDebitoAtualizado() {
		return debitoAtualizado;
	}
	public void setDebitoAtualizado(String debitoAtualizado) {
		this.debitoAtualizado = debitoAtualizado;
	}
	public String getValorAcordo() {
		return valorAcordo;
	}
	public void setValorAcordo(String valorAcordo) {
		this.valorAcordo = valorAcordo;
	}
	public String getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(String percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public String getCustas() {
		return custas;
	}
	public void setCustas(String custas) {
		this.custas = custas;
	}
	public String getHonorarios() {
		return honorarios;
	}
	public void setHonorarios(String honorarios) {
		this.honorarios = honorarios;
	}
	public String getValorParcelamento() {
		return valorParcelamento;
	}
	public void setValorParcelamento(String valorParcelamento) {
		this.valorParcelamento = valorParcelamento;
	}
	public String getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(String valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
