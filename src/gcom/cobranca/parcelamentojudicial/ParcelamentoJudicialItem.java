package gcom.cobranca.parcelamentojudicial;

import gcom.faturamento.conta.ContaGeral;
import gcom.interceptor.ControleAlteracao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ControleAlteracao()
public class ParcelamentoJudicialItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;
	
	private ParcelamentoJudicial parcelamentoJudicial;
	
	private ContaGeral contaGeral;
	
	private BigDecimal valorConta;
	
	private BigDecimal valorAcrescimosImpontualidade;
	
	private Date ultimaAlteracao;
	
	/** default constructor */
	public ParcelamentoJudicialItem() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ParcelamentoJudicial getParcelamentoJudicial() {
		return parcelamentoJudicial;
	}

	public void setParcelamentoJudicial(ParcelamentoJudicial parcelamentoJudicial) {
		this.parcelamentoJudicial = parcelamentoJudicial;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public BigDecimal getValorAcrescimosImpontualidade() {
		return valorAcrescimosImpontualidade;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public void setValorAcrescimosImpontualidade(
			BigDecimal valorAcrescimosImpontualidade) {
		this.valorAcrescimosImpontualidade = valorAcrescimosImpontualidade;
	}
		
}
