package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC 1559] - Consultar Resumo de Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 24/09/2013
 *
 */
public class ImovelPerfilHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Integer quantidade;
	
	private BigDecimal percentualQuantidade;
	
	private BigDecimal valor;
	
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPercentualQuantidade() {
		return percentualQuantidade;
	}

	public void setPercentualQuantidade(BigDecimal percentualQuantidade) {
		this.percentualQuantidade = percentualQuantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getPercentualValor() {
		return percentualValor;
	}

	public void setPercentualValor(BigDecimal percentualValor) {
		this.percentualValor = percentualValor;
	}
	
}
