package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1542] - Filtrar Natureza Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class FiltrarNaturezaEquipeActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String tipoPesquisa;
	
	private String descricaoAbreviada;
	
	private String indicadorUso;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
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
