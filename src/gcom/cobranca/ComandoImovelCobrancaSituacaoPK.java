package gcom.cobranca;

import java.io.Serializable;

public class ComandoImovelCobrancaSituacaoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idComando;
	private Integer idImovelCobrancaSituacao;
	
	public Integer getIdComando() {
		return idComando;
	}
	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}
	public Integer getIdImovelCobrancaSituacao() {
		return idImovelCobrancaSituacao;
	}
	public void setIdImovelCobrancaSituacao(Integer idImovelCobrancaSituacao) {
		this.idImovelCobrancaSituacao = idImovelCobrancaSituacao;
	}
}
