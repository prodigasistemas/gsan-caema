package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcClienteTelefoneRetorno implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private AtcClienteTelefoneRetornoPK comp_id;

    /** persistent field */
    private String atcCtlDdd;

    /** persistent field */
    private String atcCtlNumero;

    /** nullable persistent field */
    private String atcCtlRamal;

    /** persistent field */
    private long atcCliId;

    /** nullable persistent field */
    private String atcCtlNomeContato;

    /** nullable persistent field */
    private Integer atcTtpId;

    /** nullable persistent field */
    private Boolean atcCliFonePadrao;

    /** full constructor */
    public AtcClienteTelefoneRetorno(AtcClienteTelefoneRetornoPK comp_id, String atcCtlDdd, String atcCtlNumero, String atcCtlRamal, int atcCliId, String atcCtlNomeContato, Integer atcTtpId, Boolean atcCliFonePadrao) {
        this.comp_id = comp_id;
        this.atcCtlDdd = atcCtlDdd;
        this.atcCtlNumero = atcCtlNumero;
        this.atcCtlRamal = atcCtlRamal;
        this.atcCliId = atcCliId;
        this.atcCtlNomeContato = atcCtlNomeContato;
        this.atcTtpId = atcTtpId;
        this.atcCliFonePadrao = atcCliFonePadrao;
    }

    /** default constructor */
    public AtcClienteTelefoneRetorno() {
    }

    /** minimal constructor */
    public AtcClienteTelefoneRetorno(AtcClienteTelefoneRetornoPK comp_id, String atcCtlDdd, String atcCtlNumero, int atcCliId) {
        this.comp_id = comp_id;
        this.atcCtlDdd = atcCtlDdd;
        this.atcCtlNumero = atcCtlNumero;
        this.atcCliId = atcCliId;
    }

    public AtcClienteTelefoneRetornoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(AtcClienteTelefoneRetornoPK comp_id) {
        this.comp_id = comp_id;
    }

    public String getAtcCtlDdd() {
        return this.atcCtlDdd;
    }

    public void setAtcCtlDdd(String atcCtlDdd) {
        this.atcCtlDdd = atcCtlDdd;
    }

    public String getAtcCtlNumero() {
        return this.atcCtlNumero;
    }

    public void setAtcCtlNumero(String atcCtlNumero) {
        this.atcCtlNumero = atcCtlNumero;
    }

    public String getAtcCtlRamal() {
        return this.atcCtlRamal;
    }

    public void setAtcCtlRamal(String atcCtlRamal) {
        this.atcCtlRamal = atcCtlRamal;
    }

    public long getAtcCliId() {
        return this.atcCliId;
    }

    public void setAtcCliId(long atcCliId) {
        this.atcCliId = atcCliId;
    }

    public String getAtcCtlNomeContato() {
        return this.atcCtlNomeContato;
    }

    public void setAtcCtlNomeContato(String atcCtlNomeContato) {
        this.atcCtlNomeContato = atcCtlNomeContato;
    }

    public Integer getAtcTtpId() {
        return this.atcTtpId;
    }

    public void setAtcTtpId(Integer atcTtpId) {
        this.atcTtpId = atcTtpId;
    }

    public Boolean getAtcCliFonePadrao() {
        return this.atcCliFonePadrao;
    }

    public void setAtcCliFonePadrao(Boolean atcCliFonePadrao) {
        this.atcCliFonePadrao = atcCliFonePadrao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcClienteTelefoneRetorno) ) return false;
        AtcClienteTelefoneRetorno castOther = (AtcClienteTelefoneRetorno) other;
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
