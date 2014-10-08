package gcom.relatorio.atendimentopublico.ordemservico;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC 1559] - Consultar Resumo das Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 23/09/2013
 *
 */
public class RelatorioResumoAcoesOrdensServicoBean implements RelatorioBean {

	private Integer idServicoTipo;
	
	private String descricaoServicoTipo;
	
	private Integer qtdServicoTipo;
	
	private BigDecimal valorServicoTipo;
	
	private Integer idOrdemServicoSituacao;
	
	private String descricaoOrdemServicoSituacao;
	
	private Integer qtdOrdemServicoSituacao;
	
	private BigDecimal percentualQtdOrdemServicoSituacao;
	
	private BigDecimal valorOrdemServicoSituacao;
	
	private BigDecimal percentualValorOrdemServicoSituacao;
	
	private Integer idCobrancaDebitoSituacao;
	
	private String descricaoCobrancaDebitoSituacao;
	
	private Integer qtdCobrancaDebitoSituacao;
	
	private BigDecimal percentualQtdCobrancaDebitoSituacao;
	
	private BigDecimal valorCobrancaDebitoSituacao;
	
	private BigDecimal percentualValorCobrancaDebitoSituacao;

	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}
	
	public Integer getQtdServicoTipo() {
		return qtdServicoTipo;
	}

	public void setQtdServicoTipo(Integer qtdServicoTipo) {
		this.qtdServicoTipo = qtdServicoTipo;
	}

	public BigDecimal getValorServicoTipo() {
		return valorServicoTipo;
	}

	public void setValorServicoTipo(BigDecimal valorServicoTipo) {
		this.valorServicoTipo = valorServicoTipo;
	}

	public Integer getIdOrdemServicoSituacao() {
		return idOrdemServicoSituacao;
	}

	public void setIdOrdemServicoSituacao(Integer idOrdemServicoSituacao) {
		this.idOrdemServicoSituacao = idOrdemServicoSituacao;
	}

	public String getDescricaoOrdemServicoSituacao() {
		return descricaoOrdemServicoSituacao;
	}

	public void setDescricaoOrdemServicoSituacao(String descricaoOrdemServicoSituacao) {
		this.descricaoOrdemServicoSituacao = descricaoOrdemServicoSituacao;
	}

	public Integer getQtdOrdemServicoSituacao() {
		return qtdOrdemServicoSituacao;
	}

	public void setQtdOrdemServicoSituacao(Integer qtdOrdemServicoSituacao) {
		this.qtdOrdemServicoSituacao = qtdOrdemServicoSituacao;
	}

	public BigDecimal getPercentualQtdOrdemServicoSituacao() {
		return percentualQtdOrdemServicoSituacao;
	}

	public void setPercentualQtdOrdemServicoSituacao(
			BigDecimal percentualQtdOrdemServicoSituacao) {
		this.percentualQtdOrdemServicoSituacao = percentualQtdOrdemServicoSituacao;
	}

	public BigDecimal getValorOrdemServicoSituacao() {
		return valorOrdemServicoSituacao;
	}

	public void setValorOrdemServicoSituacao(BigDecimal valorOrdemServicoSituacao) {
		this.valorOrdemServicoSituacao = valorOrdemServicoSituacao;
	}

	public BigDecimal getPercentualValorOrdemServicoSituacao() {
		return percentualValorOrdemServicoSituacao;
	}

	public void setPercentualValorOrdemServicoSituacao(
			BigDecimal percentualValorOrdemServicoSituacao) {
		this.percentualValorOrdemServicoSituacao = percentualValorOrdemServicoSituacao;
	}

	public Integer getIdCobrancaDebitoSituacao() {
		return idCobrancaDebitoSituacao;
	}

	public void setIdCobrancaDebitoSituacao(Integer idCobrancaDebitoSituacao) {
		this.idCobrancaDebitoSituacao = idCobrancaDebitoSituacao;
	}

	public String getDescricaoCobrancaDebitoSituacao() {
		return descricaoCobrancaDebitoSituacao;
	}

	public void setDescricaoCobrancaDebitoSituacao(
			String descricaoCobrancaDebitoSituacao) {
		this.descricaoCobrancaDebitoSituacao = descricaoCobrancaDebitoSituacao;
	}

	public Integer getQtdCobrancaDebitoSituacao() {
		return qtdCobrancaDebitoSituacao;
	}

	public void setQtdCobrancaDebitoSituacao(Integer qtdCobrancaDebitoSituacao) {
		this.qtdCobrancaDebitoSituacao = qtdCobrancaDebitoSituacao;
	}

	public BigDecimal getPercentualQtdCobrancaDebitoSituacao() {
		return percentualQtdCobrancaDebitoSituacao;
	}

	public void setPercentualQtdCobrancaDebitoSituacao(
			BigDecimal percentualQtdCobrancaDebitoSituacao) {
		this.percentualQtdCobrancaDebitoSituacao = percentualQtdCobrancaDebitoSituacao;
	}

	public BigDecimal getValorCobrancaDebitoSituacao() {
		return valorCobrancaDebitoSituacao;
	}

	public void setValorCobrancaDebitoSituacao(
			BigDecimal valorCobrancaDebitoSituacao) {
		this.valorCobrancaDebitoSituacao = valorCobrancaDebitoSituacao;
	}

	public BigDecimal getPercentualValorCobrancaDebitoSituacao() {
		return percentualValorCobrancaDebitoSituacao;
	}

	public void setPercentualValorCobrancaDebitoSituacao(
			BigDecimal percentualValorCobrancaDebitoSituacao) {
		this.percentualValorCobrancaDebitoSituacao = percentualValorCobrancaDebitoSituacao;
	}
	
}
