package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcClienteImovelRetornoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcImoRetornoId;

    /** identifier field */
    private Integer atcCimId;

    /** full constructor */
    public AtcClienteImovelRetornoPK(Integer atcImoRetornoId, Integer atcCimId) {
        this.atcImoRetornoId = atcImoRetornoId;
        this.atcCimId = atcCimId;
    }

    /** default constructor */
    public AtcClienteImovelRetornoPK() {
    }

    public Integer getAtcImoRetornoId() {
        return this.atcImoRetornoId;
    }

    public void setAtcImoRetornoId(Integer atcImoRetornoId) {
        this.atcImoRetornoId = atcImoRetornoId;
    }

    public Integer getAtcCimId() {
        return this.atcCimId;
    }

    public void setAtcCimId(Integer atcCimId) {
        this.atcCimId = atcCimId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("atcImoRetornoId", getAtcImoRetornoId())
            .append("atcCimId", getAtcCimId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcClienteImovelRetornoPK) ) return false;
        AtcClienteImovelRetornoPK castOther = (AtcClienteImovelRetornoPK) other;
        return new EqualsBuilder()
            .append(this.getAtcImoRetornoId(), castOther.getAtcImoRetornoId())
            .append(this.getAtcCimId(), castOther.getAtcCimId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAtcImoRetornoId())
            .append(getAtcCimId())
            .toHashCode();
    }

}
