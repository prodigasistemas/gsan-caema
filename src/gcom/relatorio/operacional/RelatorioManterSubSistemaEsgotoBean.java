package gcom.relatorio.operacional;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterSubSistemaEsgotoBean implements RelatorioBean {
	
    private String codigo;
	private String descricao;
	private String descricaoAbreviada;
	private String sistemaEsgoto;
	
	public RelatorioManterSubSistemaEsgotoBean(String codigo, String descricao, String sistemaEsgoto, String descricaoAbreviada) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.sistemaEsgoto = sistemaEsgoto;
		this.descricaoAbreviada = descricaoAbreviada;
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public String getSistemaEsgoto() {
		return sistemaEsgoto;
	}

	public void setSistemaEsgoto(String sistemaEsgoto) {
		this.sistemaEsgoto = sistemaEsgoto;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	
}
