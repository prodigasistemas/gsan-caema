package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcSubcategoriaImovelRetorno implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovelRetornoPK comp_id;

    /** persistent field */
    private int atcSubId;

    /** persistent field */
    private int atcSciQtdEconomias;

    /** persistent field */
    private int atcImoId;

    /** full constructor */
    public AtcSubcategoriaImovelRetorno(gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovelRetornoPK comp_id, int atcSubId, int atcSciQtdEconomias, int atcImoId) {
        this.comp_id = comp_id;
        this.atcSubId = atcSubId;
        this.atcSciQtdEconomias = atcSciQtdEconomias;
        this.atcImoId = atcImoId;
    }

    /** default constructor */
    public AtcSubcategoriaImovelRetorno() {
    }

    public gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovelRetornoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovelRetornoPK comp_id) {
        this.comp_id = comp_id;
    }

    public int getAtcSubId() {
        return this.atcSubId;
    }

    public void setAtcSubId(int atcSubId) {
        this.atcSubId = atcSubId;
    }

    public int getAtcSciQtdEconomias() {
        return this.atcSciQtdEconomias;
    }

    public void setAtcSciQtdEconomias(int atcSciQtdEconomias) {
        this.atcSciQtdEconomias = atcSciQtdEconomias;
    }

    public int getAtcImoId() {
        return this.atcImoId;
    }

    public void setAtcImoId(int atcImoId) {
        this.atcImoId = atcImoId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcSubcategoriaImovelRetorno) ) return false;
        AtcSubcategoriaImovelRetorno castOther = (AtcSubcategoriaImovelRetorno) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
