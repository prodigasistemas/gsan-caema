package gcom.autoatendimento;

import java.io.Serializable;

/**
 * 
 * @author Arthur Carvalho
 *
 */
public class ErroAutoatendimentoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String mensagemErro;


	public String getMensagemErro() {
		return mensagemErro;
	}


	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
	

}
