package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class AcaAtualizadores implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer acaAtuId;
	
	private String acaAtuNome;
	
	private String acaAtuLogin;
	
	private String acaAtuSenha;
	
	private Boolean acaAtuDesativado;

	public AcaAtualizadores(Integer acaAtuId, String acaAtuNome,
			String acaAtuLogin, String acaAtuSenha, Boolean acaAtuDesativado) {
		super();
		this.acaAtuId = acaAtuId;
		this.acaAtuNome = acaAtuNome;
		this.acaAtuLogin = acaAtuLogin;
		this.acaAtuSenha = acaAtuSenha;
		this.acaAtuDesativado = acaAtuDesativado;
	}

	public AcaAtualizadores(){
		
	}

	public Integer getAcaAtuId() {
		return acaAtuId;
	}

	public void setAcaAtuId(Integer acaAtuId) {
		this.acaAtuId = acaAtuId;
	}

	public String getAcaAtuNome() {
		return acaAtuNome;
	}

	public void setAcaAtuNome(String acaAtuNome) {
		this.acaAtuNome = acaAtuNome;
	}

	public String getAcaAtuLogin() {
		return acaAtuLogin;
	}

	public void setAcaAtuLogin(String acaAtuLogin) {
		this.acaAtuLogin = acaAtuLogin;
	}

	public String getAcaAtuSenha() {
		return acaAtuSenha;
	}

	public void setAcaAtuSenha(String acaAtuSenha) {
		this.acaAtuSenha = acaAtuSenha;
	}

	public Boolean getAcaAtuDesativado() {
		return acaAtuDesativado;
	}

	public void setAcaAtuDesativado(Boolean acaAtuDesativado) {
		this.acaAtuDesativado = acaAtuDesativado;
	}
	
}
