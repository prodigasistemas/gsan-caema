package gcom.gui.cobranca.cobrancaporresultado;

import java.io.Serializable;

public class ConsultarNaoGeracaoImoveisContasComandosHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idComando;
	
	public ConsultarNaoGeracaoImoveisContasComandosHelper(Integer idComando){
		this.idComando = idComando;
	}

	public Integer getIdComando() {
		return idComando;
	}

	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}
	
	

}
