package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class AtcBairro implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcBaiId;

    /** persistent field */
    private String atcBaiNome;
    
    private String atcBaiAtualizado;
    
    private Date atcBaiDataHoraImportacao;
    
    private Integer atcMunId;
    
    public final static String ATUALIZADO = "A";
    
    public final static String INSERIDO = "I";

    /** full constructor */
    public AtcBairro(String atcBaiNome, String atcBaiAtualizado, Integer atcMunId) {
        this.atcBaiNome = atcBaiNome;
        this.atcBaiAtualizado = atcBaiAtualizado;
        this.atcMunId = atcMunId;
    }

    /** default constructor */
    public AtcBairro() {
    }

    public Integer getAtcBaiId() {
        return this.atcBaiId;
    }

    public void setAtcBaiId(Integer atcBaiId) {
        this.atcBaiId = atcBaiId;
    }

    public String getAtcBaiNome() {
        return this.atcBaiNome;
    }

    public void setAtcBaiNome(String atcBaiNome) {
        this.atcBaiNome = atcBaiNome;
    }

	public String getAtcBaiAtualizado() {
		return atcBaiAtualizado;
	}

	public void setAtcBaiAtualizado(String atcBaiAtualizado) {
		this.atcBaiAtualizado = atcBaiAtualizado;
	}

	public Date getAtcBaiDataHoraImportacao() {
		return atcBaiDataHoraImportacao;
	}

	public void setAtcBaiDataHoraImportacao(Date atcBaiDataHoraImportacao) {
		this.atcBaiDataHoraImportacao = atcBaiDataHoraImportacao;
	}

	public Integer getAtcMunId() {
		return atcMunId;
	}

	public void setAtcMunId(Integer atcMunId) {
		this.atcMunId = atcMunId;
	}

}
