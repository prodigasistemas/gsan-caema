package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Raimundo Martins
 * @date 03/05/2012
 * */
public class ArrecadadorCritica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricaoCritica;
	private Integer indicadorUso;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricaoCritica() {
		return descricaoCritica;
	}
	public void setDescricaoCritica(String descricaoCritica) {
		this.descricaoCritica = descricaoCritica;
	}
	public Integer getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Integer indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	
}
