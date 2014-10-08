package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1541] - Inserir Natureza Equipe
 * 
 * @author Davi Menezes
 * @date 26/08/2013
 *
 */
public class InserirNaturezaEquipeActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String descricaoAbreviada;

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
	
}
