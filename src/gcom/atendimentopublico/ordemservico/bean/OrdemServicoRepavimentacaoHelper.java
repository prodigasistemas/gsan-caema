package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrdemServicoRepavimentacaoHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricaoServico;
	
	private BigDecimal quantidadeServico;
	
	private BigDecimal valorUnitarioServico;
	
	private BigDecimal valorServico;

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public BigDecimal getQuantidadeServico() {
		return quantidadeServico;
	}

	public void setQuantidadeServico(BigDecimal quantidadeServico) {
		this.quantidadeServico = quantidadeServico;
	}

	public BigDecimal getValorUnitarioServico() {
		return valorUnitarioServico;
	}

	public void setValorUnitarioServico(BigDecimal valorUnitarioServico) {
		this.valorUnitarioServico = valorUnitarioServico;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

}
