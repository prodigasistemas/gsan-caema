package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class AtcLogradouro implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer atcLgrId;

    private String atcLgrDescricao;

    private Integer atcLtpId;

    private Integer atcLttId;
    
    private Integer atcMunId;
    
    private String atcLgrAtualizado;
    
    private Date atcLgrDataHoraImportacao;

    public final static String ATUALIZADO = "A";
    
    public final static String INSERIDO = "I";

    /** full constructor */
    public AtcLogradouro(String atcLgrDescricao, Integer atcLtpId, Integer atcLttId, String atcLgrAtualizado, Date atcLgrDataHoraImportacao, Integer atcMunId) {
        this.atcLgrDescricao = atcLgrDescricao;
        this.atcLtpId = atcLtpId;
        this.atcLttId = atcLttId;
        this.atcLgrAtualizado = atcLgrAtualizado;
        this.atcLgrDataHoraImportacao = atcLgrDataHoraImportacao;
        this.atcMunId = atcMunId;
    }

    /** default constructor */
    public AtcLogradouro() {
    }

    /** minimal constructor */
    public AtcLogradouro(String atcLgrDescricao) {
        this.atcLgrDescricao = atcLgrDescricao;
    }

    public Integer getAtcLgrId() {
        return this.atcLgrId;
    }

    public void setAtcLgrId(Integer atcLgrId) {
        this.atcLgrId = atcLgrId;
    }

    public String getAtcLgrDescricao() {
        return this.atcLgrDescricao;
    }

    public void setAtcLgrDescricao(String atcLgrDescricao) {
        this.atcLgrDescricao = atcLgrDescricao;
    }

    public Integer getAtcLtpId() {
        return this.atcLtpId;
    }

    public void setAtcLtpId(Integer atcLtpId) {
        this.atcLtpId = atcLtpId;
    }

    public Integer getAtcLttId() {
        return this.atcLttId;
    }

    public void setAtcLttId(Integer atcLttId) {
        this.atcLttId = atcLttId;
    }

	public String getAtcLgrAtualizado() {
		return atcLgrAtualizado;
	}

	public void setAtcLgrAtualizado(String atcLgrAtualizado) {
		this.atcLgrAtualizado = atcLgrAtualizado;
	}

	public Date getAtcLgrDataHoraImportacao() {
		return atcLgrDataHoraImportacao;
	}

	public void setAtcLgrDataHoraImportacao(Date atcLgrDataHoraImportacao) {
		this.atcLgrDataHoraImportacao = atcLgrDataHoraImportacao;
	}

	public Integer getAtcMunId() {
		return atcMunId;
	}

	public void setAtcMunId(Integer atcMunId) {
		this.atcMunId = atcMunId;
	}

}
