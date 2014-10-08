package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

public class MotivoNaoExecucaoUnidadeRepavimentadora implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private MotivoNaoExecucaoUnidadeRepavimentadoraPK comp_id;
	
	private Date ultimaAlteracao;

	public MotivoNaoExecucaoUnidadeRepavimentadoraPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(MotivoNaoExecucaoUnidadeRepavimentadoraPK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}	
}