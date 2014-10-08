package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterFeriadoBean implements RelatorioBean {
	
	private String tipoFeriado;

	private String municipio;
	
	private String dataFeriado;
	
	private String descricao;	
	
	public RelatorioManterFeriadoBean(
			String tipoFeriado, String municipio, String dataFeriado, String descricao) {
		super();
		this.tipoFeriado = tipoFeriado;
		this.municipio = municipio;
		this.dataFeriado = dataFeriado;
		this.descricao = descricao;
	}

	public String getTipoFeriado() {
		return tipoFeriado;
	}

	public void setTipoFeriado(String tipoFeriado) {
		this.tipoFeriado = tipoFeriado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(String dataFeriado) {
		this.dataFeriado = dataFeriado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
