package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcClienteImovelRetorno implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private AtcClienteImovelRetornoPK comp_id;

    /** persistent field */
    private int atcCliId;

    /** persistent field */
    private int atcImoId;

    /** persistent field */
    private int atcRltId;

    /** nullable persistent field */
    private String atcCimDataInicio;

    /** nullable persistent field */
    private String atcCimDataFim;

    /** nullable persistent field */
    private Date atcCimDesde;

    /** full constructor */
    public AtcClienteImovelRetorno(AtcClienteImovelRetornoPK comp_id, int atcCliId, int atcImoId, int atcRltId, String atcCimDataInicio, String atcCimDataFim, Date atcCimDesde) {
        this.comp_id = comp_id;
        this.atcCliId = atcCliId;
        this.atcImoId = atcImoId;
        this.atcRltId = atcRltId;
        this.atcCimDataInicio = atcCimDataInicio;
        this.atcCimDataFim = atcCimDataFim;
        this.atcCimDesde = atcCimDesde;
    }

    /** default constructor */
    public AtcClienteImovelRetorno() {
    }

    /** minimal constructor */
    public AtcClienteImovelRetorno(AtcClienteImovelRetornoPK comp_id, int atcCliId, int atcImoId, int atcRltId) {
        this.comp_id = comp_id;
        this.atcCliId = atcCliId;
        this.atcImoId = atcImoId;
        this.atcRltId = atcRltId;
    }

    public AtcClienteImovelRetornoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(AtcClienteImovelRetornoPK comp_id) {
        this.comp_id = comp_id;
    }

    public int getAtcCliId() {
        return this.atcCliId;
    }

    public void setAtcCliId(int atcCliId) {
        this.atcCliId = atcCliId;
    }

    public int getAtcImoId() {
        return this.atcImoId;
    }

    public void setAtcImoId(int atcImoId) {
        this.atcImoId = atcImoId;
    }

    public int getAtcRltId() {
        return this.atcRltId;
    }

    public void setAtcRltId(int atcRltId) {
        this.atcRltId = atcRltId;
    }

    public String getAtcCimDataInicio() {
        return this.atcCimDataInicio;
    }

    public void setAtcCimDataInicio(String atcCimDataInicio) {
        this.atcCimDataInicio = atcCimDataInicio;
    }

    public String getAtcCimDataFim() {
        return this.atcCimDataFim;
    }

    public void setAtcCimDataFim(String atcCimDataFim) {
        this.atcCimDataFim = atcCimDataFim;
    }

    public Date getAtcCimDesde() {
        return this.atcCimDesde;
    }

    public void setAtcCimDesde(Date atcCimDesde) {
        this.atcCimDesde = atcCimDesde;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcClienteImovelRetorno) ) return false;
        AtcClienteImovelRetorno castOther = (AtcClienteImovelRetorno) other;
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
