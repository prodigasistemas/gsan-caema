package gcom.atendimentopublico.ordemservico.bean;

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
public class ServicoTipoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Integer quantidadeContas;
	
	private BigDecimal valorContas;
	
	private Integer quantidadeOS;
	
	private Collection<OrdemServicoSituacaoHelper> colecaoSituacaoOS;

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

	public BigDecimal getValorContas() {
		return valorContas;
	}

	public void setValorContas(BigDecimal valorContas) {
		this.valorContas = valorContas;
	}
	
	public Integer getQuantidadeOS() {
		return quantidadeOS;
	}

	public void setQuantidadeOS(Integer quantidadeOS) {
		this.quantidadeOS = quantidadeOS;
	}

	public Collection<OrdemServicoSituacaoHelper> getColecaoSituacaoOS() {
		return colecaoSituacaoOS;
	}

	public void setColecaoSituacaoOS(Collection<OrdemServicoSituacaoHelper> colecaoSituacaoOS) {
		this.colecaoSituacaoOS = colecaoSituacaoOS;
	}
	
}
