package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1543] - Manter Natureza de Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class AtualizarNaturezaEquipeActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String indicadorUso;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
