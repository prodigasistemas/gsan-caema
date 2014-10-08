package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1473] - Filtrar Serviço da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 16/05/2013
 *
 */
public class FiltrarServicoRepavimentadoraActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String unidadeMaterial;
	
	private String unidadeRepavimentadora;
	
	private String indicadorUso;

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

	public String getUnidadeMaterial() {
		return unidadeMaterial;
	}

	public void setUnidadeMaterial(String unidadeMaterial) {
		this.unidadeMaterial = unidadeMaterial;
	}

	public String getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(String unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
}
