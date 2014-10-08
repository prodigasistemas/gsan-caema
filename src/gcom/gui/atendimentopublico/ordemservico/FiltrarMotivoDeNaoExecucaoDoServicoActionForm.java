package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1513] - Filtrar Motivo de não execução do serviço
 * 
 * @author Diogo Luiz
 * @date 27/06/2013
 *
 */

public class FiltrarMotivoDeNaoExecucaoDoServicoActionForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String tipoPesquisa;
	
	private String descricaoAbreviada;
	
	private String unidadeRepavimentacao;
	
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
	
	public String getUnidadeRepavimentacao() {
		return unidadeRepavimentacao;
	}
	
	public void setUnidadeRepavimentacao(String unidadeRepavimentacao) {
		this.unidadeRepavimentacao = unidadeRepavimentacao;
	}
	
	public String getIndicadorUso() {
		return indicadorUso;
	}
	
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	

}
