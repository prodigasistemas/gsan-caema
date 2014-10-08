package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.endereco.Logradouro;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class LogradouroGsanAdmin implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private LogradouroGsanAdminPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Logradouro logradouro;

    /** nullable persistent field */
    private LogradouroAdmin logradouroAdmin;

    public LogradouroGsanAdmin(){
    	
    }
    
	public LogradouroGsanAdmin(LogradouroGsanAdminPK comp_id,
			Date ultimaAlteracao, Logradouro logradouro,
			LogradouroAdmin logradouroAdmin) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouro = logradouro;
		this.logradouroAdmin = logradouroAdmin;
	}

	public LogradouroGsanAdminPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(LogradouroGsanAdminPK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public LogradouroAdmin getLogradouroAdmin() {
		return logradouroAdmin;
	}

	public void setLogradouroAdmin(LogradouroAdmin logradouroAdmin) {
		this.logradouroAdmin = logradouroAdmin;
	}

}
