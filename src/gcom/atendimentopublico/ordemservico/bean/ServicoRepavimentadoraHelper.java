package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;

/**
 * [UC 1475] - Informar Serviços da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 20/05/2013
 *
 */
public class ServicoRepavimentadoraHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idServico;
	
	private String descricao;
	
	private String quantidade;

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ServicoRepavimentadoraHelper other = (ServicoRepavimentadoraHelper) obj;
		if (idServico == null) {
			if (other.idServico != null)
				return false;
		} else if (!idServico.equals(other.idServico))
			return false;
		
		return true;
	}
	
}