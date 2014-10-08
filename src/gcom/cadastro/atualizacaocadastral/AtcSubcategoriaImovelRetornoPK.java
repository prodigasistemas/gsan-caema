package gcom.cadastro.atualizacaocadastral;

import gcom.interceptor.ObjetoGcom;


/** @author Hibernate CodeGenerator */
public class AtcSubcategoriaImovelRetornoPK extends ObjetoGcom {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcImoRetornoId;

    /** identifier field */
    private Long atcSciId;

    /** full constructor */
    public AtcSubcategoriaImovelRetornoPK(Integer atcImoRetornoId, Long atcSciId) {
        this.atcImoRetornoId = atcImoRetornoId;
        this.atcSciId = atcSciId;
    }

    /** default constructor */
    public AtcSubcategoriaImovelRetornoPK() {
    }

    public Integer getAtcImoRetornoId() {
        return this.atcImoRetornoId;
    }

    public void setAtcImoRetornoId(Integer atcImoRetornoId) {
        this.atcImoRetornoId = atcImoRetornoId;
    }

    public Long getAtcSciId() {
        return this.atcSciId;
    }

    public void setAtcSciId(Long atcSciId) {
        this.atcSciId = atcSciId;
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}
}
