package gcom.atendimentopublico.ordemservico.bean;

import gcom.cobranca.bean.CobrancaDebitoSituacaoHelper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * [UC 1559] - Consultar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 20/09/2013
 *
 */
public class OrdemServicoSituacaoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Integer quantidadeContas;
	
	private BigDecimal percentualQuantidade;
	
	private BigDecimal valorContas;
	
	private BigDecimal percentualValor;
	
	private Integer quantidadeOS;
	
	private Collection<CobrancaDebitoSituacaoHelper> colecaoDebitos;

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
	
	public Integer getQuantidadeOS() {
		return quantidadeOS;
	}

	public void setQuantidadeOS(Integer quantidadeOS) {
		this.quantidadeOS = quantidadeOS;
	}

	public Collection<CobrancaDebitoSituacaoHelper> getColecaoDebitos() {
		return colecaoDebitos;
	}

	public void setColecaoDebitos(Collection<CobrancaDebitoSituacaoHelper> colecaoDebitos) {
		this.colecaoDebitos = colecaoDebitos;
	}
	
}
