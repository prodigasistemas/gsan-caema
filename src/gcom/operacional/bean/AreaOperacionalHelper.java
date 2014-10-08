package gcom.operacional.bean;

import java.io.Serializable;

/**
 * [UC1604]	Filtra Área Operacional
 * 
 * @author Ana Maria
 * @date 05/06/2014
 */

public class AreaOperacionalHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idAreaOperacional;
	
	private String descricaoAreaOperacional;
	
	private String subSistema;
	
	private String distrito;

	public AreaOperacionalHelper() {
	}
	
	public Integer getIdAreaOperacional() {
		return idAreaOperacional;
	}

	public void setIdAreaOperacional(Integer idAreaOperacional) {
		this.idAreaOperacional = idAreaOperacional;
	}

	public String getDescricaoAreaOperacional() {
		return descricaoAreaOperacional;
	}

	public void setDescricaoAreaOperacional(String descricaoAreaOperacional) {
		this.descricaoAreaOperacional = descricaoAreaOperacional;
	}

	public String getSubSistema() {
		return subSistema;
	}

	public void setSubSistema(String subSistema) {
		this.subSistema = subSistema;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

}
