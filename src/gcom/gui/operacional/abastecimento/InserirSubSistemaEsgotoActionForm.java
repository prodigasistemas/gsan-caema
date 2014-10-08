package gcom.gui.operacional.abastecimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1519] - Inserir Subsistema de Esgoto 
 * 
 * @author Maxwell Moreira
 * @date 03/07/2013
 */
public class InserirSubSistemaEsgotoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String descricao;
	
	private String descricaoAbreviada;
	
	private String sistemaEsgoto;

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

	public String getSistemaEsgoto() {
		return sistemaEsgoto;
	}

	public void setSistemaEsgoto(String sistemaEsgoto) {
		this.sistemaEsgoto = sistemaEsgoto;
	}
}
