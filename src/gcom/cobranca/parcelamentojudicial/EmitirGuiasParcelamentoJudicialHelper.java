package gcom.cobranca.parcelamentojudicial;

import java.io.Serializable;

public class EmitirGuiasParcelamentoJudicialHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idGuiaParcelamentoJudicial;
	private String numeroPrestacao;
	private String numeroPrestacaoTotal;
	private String dataVencimento;
	private String valorPrestacao;
	private String situacao;
	private String verificaContrato;
	
	public EmitirGuiasParcelamentoJudicialHelper(){}
	
	
	public String getNumeroPrestacao() {
		return numeroPrestacao;
	}
	public void setNumeroPrestacao(String numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}
	public String getNumeroPrestacaoTotal() {
		return numeroPrestacaoTotal;
	}
	public void setNumeroPrestacaoTotal(String numeroPrestacaoTotal) {
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getValorPrestacao() {
		return valorPrestacao;
	}
	public void setValorPrestacao(String valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getIdGuiaParcelamentoJudicial() {
		return idGuiaParcelamentoJudicial;
	}
	public void setIdGuiaParcelamentoJudicial(String idGuiaParcelamentoJudicial) {
		this.idGuiaParcelamentoJudicial = idGuiaParcelamentoJudicial;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getVerificaContrato() {
		return verificaContrato;
	}
	public void setVerificaContrato(String verificaContrato) {
		this.verificaContrato = verificaContrato;
	}
	
}
