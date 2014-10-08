package gcom.gui.cobranca.cobrancaporresultado;

import java.io.Serializable;

public class MotivosNaoPagamentoCobrancaoResultadoHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private String motivoNaoGeracao;
	
	private String valorPagamento;
	
	private String anoMes;
	
	private String dataPagamento;
	
	private String dtProcessamento;
	
	private String dtNaoGeracao;
	
	private String tipoConta;
	
	private String idConta;


	public String getMotivoNaoGeracao() {
		return motivoNaoGeracao;
	}

	public void setMotivoNaoGeracao(String motivoNaoGeracao) {
		this.motivoNaoGeracao = motivoNaoGeracao;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDtProcessamento() {
		return dtProcessamento;
	}

	public void setDtProcessamento(String dtProcessamento) {
		this.dtProcessamento = dtProcessamento;
	}

	public String getDtNaoGeracao() {
		return dtNaoGeracao;
	}

	public void setDrNaoGeracao(String dtNaoGeracao) {
		this.dtNaoGeracao = dtNaoGeracao;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}	
	
}
