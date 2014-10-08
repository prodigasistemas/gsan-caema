package gcom.cadastro;

import java.io.Serializable;

public class ImovelEnderecoArquivoGEOHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String mensagemImovelInvalido;
	
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getMensagemImovelInvalido() {
		return mensagemImovelInvalido;
	}
	public void setMensagemImovelInvalido(String mensagemImovelInvalido) {
		this.mensagemImovelInvalido = mensagemImovelInvalido;
	}
	
	
}
