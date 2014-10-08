package gcom.arrecadacao.pagamento;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.MotivoNaoGeracaoCobrancaResultado;
import gcom.faturamento.conta.ContaGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Classe da entidade COBRANCA.PAGTO_NAO_GER_COB_RES
 * 
 * @author Raimundo Martins, Mariana Victor
 * @date 03/05/2012, 30/05/2012
 * */
public class PagamentoNaoGeracaoCobrancaResultado implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private MotivoNaoGeracaoCobrancaResultado motivoNaoGeracao;
	private Imovel imovel;
	private ContaGeral conta;
	private Date dataProcessamentoPagamento;
	private Date dataPagamento;
	private BigDecimal valorPagamento;
	private Integer anoMesReferenciaPagamento;
	private Integer anoMesReferenciaArrecadacao;
	private Date dataNaoGeracao;
	private Date ultimaAlteracao;
	private Integer anoMesReferenciaGeracao;
	private Integer numeroDiasVencimento;
	private Short indicadorAtualizarPagamentos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MotivoNaoGeracaoCobrancaResultado getMotivoNaoGeracao() {
		return motivoNaoGeracao;
	}
	public void setMotivoNaoGeracao(
			MotivoNaoGeracaoCobrancaResultado motivoNaoGeracao) {
		this.motivoNaoGeracao = motivoNaoGeracao;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public ContaGeral getConta() {
		return conta;
	}
	public void setConta(ContaGeral conta) {
		this.conta = conta;
	}
	public Date getDataProcessamentoPagamento() {
		return dataProcessamentoPagamento;
	}
	public void setDataProcessamentoPagamento(Date dataProcessamentoPagamento) {
		this.dataProcessamentoPagamento = dataProcessamentoPagamento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}
	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}
	public Integer getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}
	public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}
	public Date getDataNaoGeracao() {
		return dataNaoGeracao;
	}
	public void setDataNaoGeracao(Date dataNaoGeracao) {
		this.dataNaoGeracao = dataNaoGeracao;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Integer getAnoMesReferenciaGeracao() {
		return anoMesReferenciaGeracao;
	}
	public void setAnoMesReferenciaGeracao(Integer anoMesReferenciaGeracao) {
		this.anoMesReferenciaGeracao = anoMesReferenciaGeracao;
	}
	public Integer getNumeroDiasVencimento() {
		return numeroDiasVencimento;
	}
	public void setNumeroDiasVencimento(Integer numeroDiasVencimento) {
		this.numeroDiasVencimento = numeroDiasVencimento;
	}
	public Short getIndicadorAtualizarPagamentos() {
		return indicadorAtualizarPagamentos;
	}
	public void setIndicadorAtualizarPagamentos(Short indicadorAtualizarPagamentos) {
		this.indicadorAtualizarPagamentos = indicadorAtualizarPagamentos;
	}
	
}
