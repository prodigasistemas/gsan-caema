package gcom.cadastro.atualizacaocadastral;

import gcom.interceptor.ObjetoGcom;


/** @author Hibernate CodeGenerator */
public class BairroGsanAdminPK extends ObjetoGcom {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer idBairro;

    /** identifier field */
    private Integer idBairroAdmin;

    public BairroGsanAdminPK(){
    	
    }
	public BairroGsanAdminPK(Integer idBairro, Integer idBairroAdmin) {
		super();
		this.idBairro = idBairro;
		this.idBairroAdmin = idBairroAdmin;
	}

	public Integer getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}

	public Integer getIdBairroAdmin() {
		return idBairroAdmin;
	}

	public void setIdBairroAdmin(Integer idBairroAdmin) {
		this.idBairroAdmin = idBairroAdmin;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}
}
