package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcClienteImovel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcCimId;

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

    /** full constructor */
    public AtcClienteImovel(Integer atcCimId, int atcCliId, int atcImoId, int atcRltId, String atcCimDataInicio, String atcCimDataFim) {
        this.atcCimId = atcCimId;
        this.atcCliId = atcCliId;
        this.atcImoId = atcImoId;
        this.atcRltId = atcRltId;
        this.atcCimDataInicio = atcCimDataInicio;
        this.atcCimDataFim = atcCimDataFim;
    }

    /** default constructor */
    public AtcClienteImovel() {
    }

    /** minimal constructor */
    public AtcClienteImovel(Integer atcCimId, int atcCliId, int atcImoId, int atcRltId) {
        this.atcCimId = atcCimId;
        this.atcCliId = atcCliId;
        this.atcImoId = atcImoId;
        this.atcRltId = atcRltId;
    }

    public Integer getAtcCimId() {
        return this.atcCimId;
    }

    public void setAtcCimId(Integer atcCimId) {
        this.atcCimId = atcCimId;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("atcCimId", getAtcCimId())
            .toString();
    }

}
