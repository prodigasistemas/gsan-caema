package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcClienteRetornoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcImoRetornoId;

    /** identifier field */
    private Long atcCliId;

    /** full constructor */
    public AtcClienteRetornoPK(Integer atcImoRetornoId, Long atcCliId) {
        this.atcImoRetornoId = atcImoRetornoId;
        this.atcCliId = atcCliId;
    }

    /** default constructor */
    public AtcClienteRetornoPK() {
    }

    public Integer getAtcImoRetornoId() {
        return this.atcImoRetornoId;
    }

    public void setAtcImoRetornoId(Integer atcImoRetornoId) {
        this.atcImoRetornoId = atcImoRetornoId;
    }

    public Long getAtcCliId() {
        return this.atcCliId;
    }

    public void setAtcCliId(Long atcCliId) {
        this.atcCliId = atcCliId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("atcImoRetornoId", getAtcImoRetornoId())
            .append("atcCliId", getAtcCliId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcClienteRetornoPK) ) return false;
        AtcClienteRetornoPK castOther = (AtcClienteRetornoPK) other;
        return new EqualsBuilder()
            .append(this.getAtcImoRetornoId(), castOther.getAtcImoRetornoId())
            .append(this.getAtcCliId(), castOther.getAtcCliId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAtcImoRetornoId())
            .append(getAtcCliId())
            .toHashCode();
    }

}
