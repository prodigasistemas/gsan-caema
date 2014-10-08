package gcom.faturamento.consumotarifa;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ConsumoTarifaVigenPpp implements Serializable {

	/**
	 * [UC0168] Inserir Tarifa de Consumo.
	 * 
	 * @author Jonathan Marcos
	 *
	 * @date 13/05/2013
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

    /** nullable persistent field */
    private Date dataVigencia;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    private Set consumoTarifaCategorias;
    
    /** persistent field */
    private gcom.faturamento.consumotarifa.ConsumoTarifaPpp consumoTarifaPpp;
    
    /** full constructor */
    public ConsumoTarifaVigenPpp(Date dataVigencia, Date ultimaAlteracao, gcom.faturamento.consumotarifa.ConsumoTarifaPpp consumoTarifaPpp) {
        this.dataVigencia = dataVigencia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.consumoTarifaPpp = consumoTarifaPpp;
    }

    /** default constructor */
    public ConsumoTarifaVigenPpp() {
    }

    /** minimal constructor */
    public ConsumoTarifaVigenPpp(gcom.faturamento.consumotarifa.ConsumoTarifaPpp consumoTarifaPpp) {
        this.consumoTarifaPpp = consumoTarifaPpp;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVigencia() {
        return this.dataVigencia;
    }

    public void setDataVigencia(Date dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.consumotarifa.ConsumoTarifaPpp getConsumoTarifaPpp() {
        return this.consumoTarifaPpp;
    }

    public void setConsumoTarifaPpp(gcom.faturamento.consumotarifa.ConsumoTarifaPpp consumoTarifaPpp) {
        this.consumoTarifaPpp = consumoTarifaPpp;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getConsumoTarifaCategorias() {
		return consumoTarifaCategorias;
	}

	public void setConsumoTarifaCategorias(Set consumoTarifaCategorias) {
		this.consumoTarifaCategorias = consumoTarifaCategorias;
	}

}
