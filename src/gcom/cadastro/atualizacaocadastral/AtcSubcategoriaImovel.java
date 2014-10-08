package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcSubcategoriaImovel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcSciId;

    /** persistent field */
    private int atcSubId;

    /** persistent field */
    private int atcSciQtdEconomias;

    /** persistent field */
    private int atcImoId;

    /** full constructor */
    public AtcSubcategoriaImovel(Integer atcSciId, int atcSubId, int atcSciQtdEconomias, int atcImoId) {
        this.atcSciId = atcSciId;
        this.atcSubId = atcSubId;
        this.atcSciQtdEconomias = atcSciQtdEconomias;
        this.atcImoId = atcImoId;
    }

    /** default constructor */
    public AtcSubcategoriaImovel() {
    }

    public Integer getAtcSciId() {
        return this.atcSciId;
    }

    public void setAtcSciId(Integer atcSciId) {
        this.atcSciId = atcSciId;
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
            .append("atcSciId", getAtcSciId())
            .toString();
    }

}
