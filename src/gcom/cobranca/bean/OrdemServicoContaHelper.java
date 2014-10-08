package gcom.cobranca.bean;

import java.math.BigDecimal;

public class OrdemServicoContaHelper{
	
	private Integer idOrdemServico;

	private BigDecimal valorDocumento;

	private BigDecimal percentualValorMinimoCobrancaCriterio;
	
	private BigDecimal percentualQuantidadeMinimaCobrancaCriterio;
	
	private BigDecimal valorLimitePrioridadeCobrancaCriterio;
	
	private Integer idSituacaoDebito;
	
	private Integer idImovel;
	
	private Integer idSituacaoOS;
	
	private Short cdSituacaoOS;
	
	private Integer idMotivoNaoExecucao;
	
	private Short indicadorExecucao;
	
	private Integer idFiscalizacao;
	
	public OrdemServicoContaHelper() {
	}
	
	public OrdemServicoContaHelper(Integer idOrdemServico,
			Integer idImovel, Short cdSituacaoOS,
			Integer idMotivoNaoExecucao, Short indicadorExecucao) {
		
		this.idOrdemServico = idOrdemServico;
		this.idImovel = idImovel;
		this.cdSituacaoOS = cdSituacaoOS;
		this.idMotivoNaoExecucao = idMotivoNaoExecucao;
		this.indicadorExecucao = indicadorExecucao;
	}
	

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public BigDecimal getPercentualValorMinimoCobrancaCriterio() {
		return percentualValorMinimoCobrancaCriterio;
	}

	public void setPercentualValorMinimoCobrancaCriterio(BigDecimal percentualValorMinimoCobrancaCriterio) {
		this.percentualValorMinimoCobrancaCriterio = percentualValorMinimoCobrancaCriterio;
	}

	public BigDecimal getPercentualQuantidadeMinimaCobrancaCriterio() {
		return percentualQuantidadeMinimaCobrancaCriterio;
	}

	public void setPercentualQuantidadeMinimaCobrancaCriterio(BigDecimal percentualQuantidadeMinimaCobrancaCriterio) {
		this.percentualQuantidadeMinimaCobrancaCriterio = percentualQuantidadeMinimaCobrancaCriterio;
	}

	public BigDecimal getValorLimitePrioridadeCobrancaCriterio() {
		return valorLimitePrioridadeCobrancaCriterio;
	}

	public void setValorLimitePrioridadeCobrancaCriterio(BigDecimal valorLimitePrioridadeCobrancaCriterio) {
		this.valorLimitePrioridadeCobrancaCriterio = valorLimitePrioridadeCobrancaCriterio;
	}

	public Integer getIdSituacaoDebito() {
		return idSituacaoDebito;
	}

	public void setIdSituacaoDebito(Integer idSituacaoDebito) {
		this.idSituacaoDebito = idSituacaoDebito;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdSituacaoOS() {
		return idSituacaoOS;
	}

	public void setIdSituacaoOS(Integer idSituacaoOS) {
		this.idSituacaoOS = idSituacaoOS;
	}

	public Short getCdSituacaoOS() {
		return cdSituacaoOS;
	}

	public void setCdSituacaoOS(Short cdSituacaoOS) {
		this.cdSituacaoOS = cdSituacaoOS;
	}

	public Integer getIdMotivoNaoExecucao() {
		return idMotivoNaoExecucao;
	}

	public void setIdMotivoNaoExecucao(Integer idMotivoNaoExecucao) {
		this.idMotivoNaoExecucao = idMotivoNaoExecucao;
	}

	public Short getIndicadorExecucao() {
		return indicadorExecucao;
	}

	public void setIndicadorExecucao(Short indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public Integer getIdFiscalizacao() {
		return idFiscalizacao;
	}

	public void setIdFiscalizacao(Integer idFiscalizacao) {
		this.idFiscalizacao = idFiscalizacao;
	}
	
}
