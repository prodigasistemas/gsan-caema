package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorForm;



public class AtualizarMotivoDeNaoExecucaoDoServicoActionForm extends ValidatorForm{

	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String unidadeRepavimentadora;
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
