package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcHidrometroInstalacao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcInsId;

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
    public AtcHidrometroInstalacao(Integer atcInsId, String atcInsDataInstalacao, Integer atcImoId, Integer atcHliId, Integer atcHprId, Integer atcMtpId, Integer atcInsLeitura, String acaInsNumeroHidrometro) {
        this.atcInsId = atcInsId;
        this.atcInsDataInstalacao = atcInsDataInstalacao;
        this.atcImoId = atcImoId;
        this.atcHliId = atcHliId;
        this.atcHprId = atcHprId;
        this.atcMtpId = atcMtpId;
        this.atcInsLeitura = atcInsLeitura;
        this.acaInsNumeroHidrometro = acaInsNumeroHidrometro;
    }

    /** default constructor */
    public AtcHidrometroInstalacao() {
    }

    /** minimal constructor */
    public AtcHidrometroInstalacao(Integer atcInsId) {
        this.atcInsId = atcInsId;
    }

    public Integer getAtcInsId() {
        return this.atcInsId;
    }

    public void setAtcInsId(Integer atcInsId) {
        this.atcInsId = atcInsId;
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
            .append("atcInsId", getAtcInsId())
            .toString();
    }

}
