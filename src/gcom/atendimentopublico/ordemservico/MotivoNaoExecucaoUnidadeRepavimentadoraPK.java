package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.io.Serializable;

public class MotivoNaoExecucaoUnidadeRepavimentadoraPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private MotivoDeNaoExecucaoDoServico motivoDeNaoExecucaoDoServico;
	
	private UnidadeOrganizacional unidadeRepavimentadora;

	public MotivoDeNaoExecucaoDoServico getMotivoDeNaoExecucaoDoServico() {
		return motivoDeNaoExecucaoDoServico;
	}

	public void setMotivoDeNaoExecucaoDoServico(
			MotivoDeNaoExecucaoDoServico motivoDeNaoExecucaoDoServico) {
		this.motivoDeNaoExecucaoDoServico = motivoDeNaoExecucaoDoServico;
	}

	public UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(
			UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}
	
}
