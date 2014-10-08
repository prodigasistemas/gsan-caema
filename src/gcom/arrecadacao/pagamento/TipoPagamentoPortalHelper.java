package gcom.arrecadacao.pagamento;

import java.io.Serializable;

public class TipoPagamentoPortalHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoPagamento;
	
	private String referenciaConta;
	
	private Pagamento pagamento;
	
	private PagamentoHistorico pagamentoHistorico;

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public PagamentoHistorico getPagamentoHistorico() {
		return pagamentoHistorico;
	}

	public void setPagamentoHistorico(PagamentoHistorico pagamentoHistorico) {
		this.pagamentoHistorico = pagamentoHistorico;
	}

	public String getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(String referenciaConta) {
		this.referenciaConta = referenciaConta;
	}
	
}
