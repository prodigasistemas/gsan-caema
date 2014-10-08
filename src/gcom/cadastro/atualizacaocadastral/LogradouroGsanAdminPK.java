package gcom.cadastro.atualizacaocadastral;

import gcom.interceptor.ObjetoGcom;


/** @author Hibernate CodeGenerator */
public class LogradouroGsanAdminPK extends ObjetoGcom {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer idLogradouro;

    /** identifier field */
    private Integer idLogradouroAdmin;

    public LogradouroGsanAdminPK(){
    	
    }
    
	public LogradouroGsanAdminPK(Integer idLogradouro, Integer idLogradouroAdmin) {
		super();
		this.idLogradouro = idLogradouro;
		this.idLogradouroAdmin = idLogradouroAdmin;
	}

	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public Integer getIdLogradouroAdmin() {
		return idLogradouroAdmin;
	}

	public void setIdLogradouroAdmin(Integer idLogradouroAdmin) {
		this.idLogradouroAdmin = idLogradouroAdmin;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

    
   
}
