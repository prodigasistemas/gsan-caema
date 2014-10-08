package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class AtcMunicipio implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer atcMunId;

    private String atcMunNome;

	public Integer getAtcMunId() {
		return atcMunId;
	}

	public void setAtcMunId(Integer atcMunId) {
		this.atcMunId = atcMunId;
	}

	public String getAtcMunNome() {
		return atcMunNome;
	}

	public void setAtcMunNome(String atcMunNome) {
		this.atcMunNome = atcMunNome;
	}

}
