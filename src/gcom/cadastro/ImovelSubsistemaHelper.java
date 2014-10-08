package gcom.cadastro;

import java.io.Serializable;

/**
 * 
 * @author Arthur
 *
 */
public class ImovelSubsistemaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	
	private String sistema;
	
	private String subSistema;
	
	private Integer idSubSistema;
	
	private String mensagemImovelInvalido;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSubSistema() {
		return subSistema;
	}

	public void setSubSistema(String subSistema) {
		this.subSistema = subSistema;
	}

	public String getMensagemImovelInvalido() {
		return mensagemImovelInvalido;
	}

	public void setMensagemImovelInvalido(String mensagemImovelInvalido) {
		this.mensagemImovelInvalido = mensagemImovelInvalido;
	}

	public Integer getIdSubSistema() {
		return idSubSistema;
	}

	public void setIdSubSistema(Integer idSubSistema) {
		this.idSubSistema = idSubSistema;
	}

}
