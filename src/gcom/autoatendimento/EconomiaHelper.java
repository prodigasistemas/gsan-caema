package gcom.autoatendimento;

import java.io.Serializable;

/**
 * 
 * @author Arthur
 *
 */
public class EconomiaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String quantidade;

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
	
	
	
}
