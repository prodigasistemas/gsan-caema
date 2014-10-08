package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC 1559] - Consultar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 20/09/2013
 *
 */
public class CobrancaDebitoSituacaoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Integer quantidadeOS;
	
	private Integer quantidadeContas;
	
	private BigDecimal percentualQuantidade;
	
	private BigDecimal valorContas;
	
	private BigDecimal percentualValor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getQuantidadeOS() {
		return quantidadeOS;
	}

	public void setQuantidadeOS(Integer quantidadeOS) {
		this.quantidadeOS = quantidadeOS;
	}

	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public BigDecimal getPercentualQuantidade() {
		return percentualQuantidade;
	}

	public void setPercentualQuantidade(BigDecimal percentualQuantidade) {
		this.percentualQuantidade = percentualQuantidade;
	}

	public BigDecimal getValorContas() {
		return valorContas;
	}

	public void setValorContas(BigDecimal valorContas) {
		this.valorContas = valorContas;
	}

	public BigDecimal getPercentualValor() {
		return percentualValor;
	}

	public void setPercentualValor(BigDecimal percentualValor) {
		this.percentualValor = percentualValor;
	}
	
}
