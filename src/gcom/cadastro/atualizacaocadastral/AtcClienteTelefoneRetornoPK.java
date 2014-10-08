package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcClienteTelefoneRetornoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcImoRetornoId;

    /** identifier field */
    private Long atcCtlId;

    /** full constructor */
    public AtcClienteTelefoneRetornoPK(Integer atcImoRetornoId, Long atcCtlId) {
        this.atcImoRetornoId = atcImoRetornoId;
        this.atcCtlId = atcCtlId;
    }

    /** default constructor */
    public AtcClienteTelefoneRetornoPK() {
    }

    public Integer getAtcImoRetornoId() {
        return this.atcImoRetornoId;
    }

    public void setAtcImoRetornoId(Integer atcImoRetornoId) {
        this.atcImoRetornoId = atcImoRetornoId;
    }

    public Long getAtcCtlId() {
        return this.atcCtlId;
    }

    public void setAtcCtlId(Long atcCtlId) {
        this.atcCtlId = atcCtlId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("atcImoRetornoId", getAtcImoRetornoId())
            .append("atcCtlId", getAtcCtlId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcClienteTelefoneRetornoPK) ) return false;
        AtcClienteTelefoneRetornoPK castOther = (AtcClienteTelefoneRetornoPK) other;
        return new EqualsBuilder()
            .append(this.getAtcImoRetornoId(), castOther.getAtcImoRetornoId())
            .append(this.getAtcCtlId(), castOther.getAtcCtlId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAtcImoRetornoId())
            .append(getAtcCtlId())
            .toHashCode();
    }

}
