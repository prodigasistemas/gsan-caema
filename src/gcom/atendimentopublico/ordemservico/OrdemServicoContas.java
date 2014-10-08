package gcom.atendimentopublico.ordemservico;

import gcom.faturamento.conta.ContaGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Davi Menezes
 * @date 13/09/2013
 */
public class OrdemServicoContas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private BigDecimal valorConta;

	private Integer anoMesReferenciaEncerramento;
	
	private Date ultimaAlteracao;
	
	private OrdemServico ordemServico;
	
	private ContaGeral contaGeral;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public Integer getAnoMesReferenciaEncerramento() {
		return anoMesReferenciaEncerramento;
	}

	public void setAnoMesReferenciaEncerramento(Integer anoMesReferenciaEncerramento) {
		this.anoMesReferenciaEncerramento = anoMesReferenciaEncerramento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}
	
}
