package gcom.cobranca;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Classe da entidade COBRANCA.HIST_PAG_COBRESULTADO:
 * 
 * @author Raimundo Martins
 * @date 05/06/2012
 * */

public class HistoricoPagamentoCobrancaResultado implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Imovel imovel;
	private ContaGeral conta;
	private GuiaPagamento guia;
	private DebitoACobrarGeral debitoACobrar;
	private Integer numeroParcela;
	private OrdemServico ordemServico;
	private ComandoEmpresaCobrancaConta comando;
	private BigDecimal valorPago;
	private Date dataPagamento;
	private String faixaAtrasoDebito;
	private Short indicadorEnviado;
	private Date dataEnviado;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public GuiaPagamento getGuia() {
		return guia;
	}
	public void setGuia(GuiaPagamento guia) {
		this.guia = guia;
	}
	public DebitoACobrarGeral getDebitoACobrar() {
		return debitoACobrar;
	}
	public void setDebitoACobrar(DebitoACobrarGeral debitoACobrar) {
		this.debitoACobrar = debitoACobrar;
	}
	public Integer getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public ComandoEmpresaCobrancaConta getComando() {
		return comando;
	}
	public void setComando(ComandoEmpresaCobrancaConta comando) {
		this.comando = comando;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Short getIndicadorEnviado() {
		return indicadorEnviado;
	}
	public void setIndicadorEnviado(Short indicadorEnviado) {
		this.indicadorEnviado = indicadorEnviado;
	}
	public Date getDataEnviado() {
		return dataEnviado;
	}
	public void setDataEnviado(Date dataEnviado) {
		this.dataEnviado = dataEnviado;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public String getFaixaAtrasoDebito() {
		return faixaAtrasoDebito;
	}
	public void setFaixaAtrasoDebito(String faixaAtrasoDebito) {
		this.faixaAtrasoDebito = faixaAtrasoDebito;
	}
}
