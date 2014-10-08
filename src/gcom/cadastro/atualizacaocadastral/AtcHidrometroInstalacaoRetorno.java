package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcHidrometroInstalacaoRetorno implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetornoPK comp_id;

    /** nullable persistent field */
    private String atcInsDataInstalacao;

    /** nullable persistent field */
    private Integer atcImoId;

    /** nullable persistent field */
    private Integer atcHliId;

    /** nullable persistent field */
    private Integer atcHprId;

    /** nullable persistent field */
    private Integer atcMtpId;

    /** nullable persistent field */
    private Integer atcInsLeitura;

    /** nullable persistent field */
    private String acaInsNumeroHidrometro;

    /** full constructor */
    public AtcHidrometroInstalacaoRetorno(gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetornoPK comp_id, String atcInsDataInstalacao, Integer atcImoId, Integer atcHliId, Integer atcHprId, Integer atcMtpId, Integer atcInsLeitura, String acaInsNumeroHidrometro) {
        this.comp_id = comp_id;
        this.atcInsDataInstalacao = atcInsDataInstalacao;
        this.atcImoId = atcImoId;
        this.atcHliId = atcHliId;
        this.atcHprId = atcHprId;
        this.atcMtpId = atcMtpId;
        this.atcInsLeitura = atcInsLeitura;
        this.acaInsNumeroHidrometro = acaInsNumeroHidrometro;
    }

    /** default constructor */
    public AtcHidrometroInstalacaoRetorno() {
    }

    /** minimal constructor */
    public AtcHidrometroInstalacaoRetorno(gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetornoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetornoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetornoPK comp_id) {
        this.comp_id = comp_id;
    }

    public String getAtcInsDataInstalacao() {
        return this.atcInsDataInstalacao;
    }

    public void setAtcInsDataInstalacao(String atcInsDataInstalacao) {
        this.atcInsDataInstalacao = atcInsDataInstalacao;
    }

    public Integer getAtcImoId() {
        return this.atcImoId;
    }

    public void setAtcImoId(Integer atcImoId) {
        this.atcImoId = atcImoId;
    }

    public Integer getAtcHliId() {
        return this.atcHliId;
    }

    public void setAtcHliId(Integer atcHliId) {
        this.atcHliId = atcHliId;
    }

    public Integer getAtcHprId() {
        return this.atcHprId;
    }

    public void setAtcHprId(Integer atcHprId) {
        this.atcHprId = atcHprId;
    }

    public Integer getAtcMtpId() {
        return this.atcMtpId;
    }

    public void setAtcMtpId(Integer atcMtpId) {
        this.atcMtpId = atcMtpId;
    }

    public Integer getAtcInsLeitura() {
        return this.atcInsLeitura;
    }

    public void setAtcInsLeitura(Integer atcInsLeitura) {
        this.atcInsLeitura = atcInsLeitura;
    }

    public String getAcaInsNumeroHidrometro() {
        return this.acaInsNumeroHidrometro;
    }

    public void setAcaInsNumeroHidrometro(String acaInsNumeroHidrometro) {
        this.acaInsNumeroHidrometro = acaInsNumeroHidrometro;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcHidrometroInstalacaoRetorno) ) return false;
        AtcHidrometroInstalacaoRetorno castOther = (AtcHidrometroInstalacaoRetorno) other;
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
