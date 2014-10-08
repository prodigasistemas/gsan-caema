package gcom.gui.integracao;

import java.io.Serializable;

public class RequisicaoGpipaRetorno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoRetorno;
	private String descricaoRetorno;
	private String protocolo;

	public RequisicaoGpipaRetorno(){}
	
	public RequisicaoGpipaRetorno(String codigoRetorno,
			String descricaoRetorno,String protocolo){
		this.codigoRetorno = codigoRetorno;
		this.descricaoRetorno = descricaoRetorno;
		this.protocolo = protocolo;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}
	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
}
