package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

public class OrdemServicoNaoEmissaoCartaEsgoto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OrdemServico ordemServico;
	
	private MotivoNaoEmissaoCartaEsgoto motivoNaoEmissaoCartaEsgoto;
	
	private OrdemServicoNaoEmissaoCartaEsgotoPK comp_id;
	
	private Date ultimaAlteracao;
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public MotivoNaoEmissaoCartaEsgoto getMotivoNaoEmissaoCartaEsgoto() {
		return motivoNaoEmissaoCartaEsgoto;
	}

	public void setMotivoNaoEmissaoCartaEsgoto(MotivoNaoEmissaoCartaEsgoto motivoNaoEmissaoCartaEsgoto) {
		this.motivoNaoEmissaoCartaEsgoto = motivoNaoEmissaoCartaEsgoto;
	}

	public OrdemServicoNaoEmissaoCartaEsgotoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(OrdemServicoNaoEmissaoCartaEsgotoPK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
