package gcom.cobranca.cobrancaporresultado;

import java.io.Serializable;

public class QuantidadeImoveisSetorCobrancaResultadoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer idSetorComercial;
	
	Integer quantidadeImoveis;
	
	Integer quantidadeComandos;

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Integer getQuantidadeImoveis() {
		return quantidadeImoveis;
	}

	public void setQuantidadeImoveis(Integer quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}

	public Integer getQuantidadeComandos() {
		return quantidadeComandos;
	}

	public void setQuantidadeComandos(Integer quantidadeComandos) {
		this.quantidadeComandos = quantidadeComandos;
	}

}
