package gcom.gui.operacional.abastecimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1519] - Inserir Subsistema de Esgoto 
 * 
 * @author Maxwell Moreira
 * @date 03/07/2013
 */
public class FiltrarSubSistemaEsgotoActionForm extends ValidatorActionForm{
	
	private static final long serialVersionUID = 1L;
	private String codigoSistemaEsgoto;
	private String descricaoSistemaEsgoto;
	private String tipoPesquisa;
	
	private String descricaoAbreviada;
	private String sistemaEsgoto;
	private String indicadorUso;	
	
	public String getDescricaoSistemaEsgoto() {
		return descricaoSistemaEsgoto;
	}
	public void setDescricaoSistemaEsgoto(String descricaoSistemaEsgoto) {
		this.descricaoSistemaEsgoto = descricaoSistemaEsgoto;
	}
	
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public String getSistemaEsgoto() {
		return sistemaEsgoto;
	}
	public void setSistemaEsgoto(String sistemaEsgoto) {
		this.sistemaEsgoto = sistemaEsgoto;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getCodigoSistemaEsgoto() {
		return codigoSistemaEsgoto;
	}
	public void setCodigoSistemaEsgoto(String codigoSistemaEsgoto) {
		this.codigoSistemaEsgoto = codigoSistemaEsgoto;
	}
}
