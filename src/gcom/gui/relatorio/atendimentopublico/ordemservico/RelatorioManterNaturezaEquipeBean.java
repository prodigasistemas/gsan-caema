package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC 1543] - Manter Natureza de Equipe
 * 
 * @author Davi Menezes
 * @date 27/08/2013
 *
 */
public class RelatorioManterNaturezaEquipeBean implements RelatorioBean {

	private String descricao;
	
	private String descricaoAbreviada;
	
	private String indicadorUso;
	
	public RelatorioManterNaturezaEquipeBean(){
		
	}
	
	public RelatorioManterNaturezaEquipeBean(String descricao, String descricaoAbreviada, String indicadorUso){
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
}
