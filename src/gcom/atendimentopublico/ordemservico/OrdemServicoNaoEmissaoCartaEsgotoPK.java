package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoGcom;

public class OrdemServicoNaoEmissaoCartaEsgotoPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer motivoNaoEmissaoCartaEsgotoId;

    private Integer ordemServicoId;

	public OrdemServicoNaoEmissaoCartaEsgotoPK() {
		super();
	}

	public OrdemServicoNaoEmissaoCartaEsgotoPK(Integer motivoNaoEmissaoCartaEsgotoId, Integer ordemServicoId) {
		super();
		this.motivoNaoEmissaoCartaEsgotoId = motivoNaoEmissaoCartaEsgotoId;
		this.ordemServicoId = ordemServicoId;
	}

	public Integer getMotivoNaoEmissaoCartaEsgotoId() {
		return motivoNaoEmissaoCartaEsgotoId;
	}

	public void setMotivoNaoEmissaoCartaEsgotoId(Integer motivoNaoEmissaoCartaEsgotoId) {
		this.motivoNaoEmissaoCartaEsgotoId = motivoNaoEmissaoCartaEsgotoId;
	}

	public Integer getOrdemServicoId() {
		return ordemServicoId;
	}

	public void setOrdemServicoId(Integer ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "motivoNaoEmissaoCartaEsgotoId";
		retorno[1] = "ordemServicoId";
		return retorno;
	} 
}
