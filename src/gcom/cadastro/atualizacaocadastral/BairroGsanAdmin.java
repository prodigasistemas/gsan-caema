package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.geografico.Bairro;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class BairroGsanAdmin extends ObjetoTransacao {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private BairroGsanAdminPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Bairro bairro;

    /** nullable persistent field */
    private BairroAdmin bairroAdmin;

    public BairroGsanAdmin(){
    	
    }
	public BairroGsanAdmin(BairroGsanAdminPK comp_id, Date ultimaAlteracao,
			Bairro bairro, BairroAdmin bairroAdmin) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.bairro = bairro;
		this.bairroAdmin = bairroAdmin;
	}
	public BairroGsanAdminPK getComp_id() {
		return comp_id;
	}
	public void setComp_id(BairroGsanAdminPK comp_id) {
		this.comp_id = comp_id;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	public BairroAdmin getBairroAdmin() {
		return bairroAdmin;
	}
	public void setBairroAdmin(BairroAdmin bairroAdmin) {
		this.bairroAdmin = bairroAdmin;
	}
	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

}
