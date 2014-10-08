package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcImovelRetornoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcImoRetornoId;

    /** identifier field */
    private Integer atcImoId;

    /** full constructor */
    public AtcImovelRetornoPK(Integer atcImoRetornoId, Integer atcImoId) {
        this.atcImoRetornoId = atcImoRetornoId;
        this.atcImoId = atcImoId;
    }

    /** default constructor */
    public AtcImovelRetornoPK() {
    }

    public Integer getAtcImoRetornoId() {
        return this.atcImoRetornoId;
    }

    public void setAtcImoRetornoId(Integer atcImoRetornoId) {
        this.atcImoRetornoId = atcImoRetornoId;
    }

    public Integer getAtcImoId() {
        return this.atcImoId;
    }

    public void setAtcImoId(Integer atcImoId) {
        this.atcImoId = atcImoId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("atcImoRetornoId", getAtcImoRetornoId())
            .append("atcImoId", getAtcImoId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcImovelRetornoPK) ) return false;
        AtcImovelRetornoPK castOther = (AtcImovelRetornoPK) other;
        return new EqualsBuilder()
            .append(this.getAtcImoRetornoId(), castOther.getAtcImoRetornoId())
            .append(this.getAtcImoId(), castOther.getAtcImoId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAtcImoRetornoId())
            .append(getAtcImoId())
            .toHashCode();
    }

}
