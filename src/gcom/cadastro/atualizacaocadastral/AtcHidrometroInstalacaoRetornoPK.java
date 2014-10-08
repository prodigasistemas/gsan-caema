package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcHidrometroInstalacaoRetornoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcImoRetornoId;

    /** identifier field */
    private Long atcInsId;

    /** full constructor */
    public AtcHidrometroInstalacaoRetornoPK(Integer atcImoRetornoId, Long atcInsId) {
        this.atcImoRetornoId = atcImoRetornoId;
        this.atcInsId = atcInsId;
    }

    /** default constructor */
    public AtcHidrometroInstalacaoRetornoPK() {
    }

    public Integer getAtcImoRetornoId() {
        return this.atcImoRetornoId;
    }

    public void setAtcImoRetornoId(Integer atcImoRetornoId) {
        this.atcImoRetornoId = atcImoRetornoId;
    }

    public Long getAtcInsId() {
        return this.atcInsId;
    }

    public void setAtcInsId(Long atcInsId) {
        this.atcInsId = atcInsId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("atcImoRetornoId", getAtcImoRetornoId())
            .append("atcInsId", getAtcInsId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcHidrometroInstalacaoRetornoPK) ) return false;
        AtcHidrometroInstalacaoRetornoPK castOther = (AtcHidrometroInstalacaoRetornoPK) other;
        return new EqualsBuilder()
            .append(this.getAtcImoRetornoId(), castOther.getAtcImoRetornoId())
            .append(this.getAtcInsId(), castOther.getAtcInsId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAtcImoRetornoId())
            .append(getAtcInsId())
            .toHashCode();
    }

}
