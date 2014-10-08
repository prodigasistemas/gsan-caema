package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;

public class ImoveisNaoMigradosHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String codigoSetor;
	private String numeroQuadra;
	
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getCodigoSetor() {
		return codigoSetor;
	}
	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}
	public String getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
}
