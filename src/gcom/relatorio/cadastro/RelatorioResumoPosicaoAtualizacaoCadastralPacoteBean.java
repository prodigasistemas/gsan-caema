package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoPosicaoAtualizacaoCadastralPacoteBean implements RelatorioBean {
	
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private String dataMovimentacao;
	
	private Integer qtdImoveisLiberados;
	private Integer qtdImoveisAtualizados;
	private Integer qtdImoveisInconsistentes;
	private Integer qtdTotalImoveis;
	
	private Integer qtdImoveisVisitados;
	private Integer qtdImoveisNaoVisitadas;
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public Integer getQtdImoveisLiberados() {
		return qtdImoveisLiberados;
	}
	public void setQtdImoveisLiberados(Integer qtdImoveisLiberados) {
		this.qtdImoveisLiberados = qtdImoveisLiberados;
	}
	public Integer getQtdImoveisAtualizados() {
		return qtdImoveisAtualizados;
	}
	public void setQtdImoveisAtualizados(Integer qtdImoveisAtualizados) {
		this.qtdImoveisAtualizados = qtdImoveisAtualizados;
	}
	public Integer getQtdImoveisInconsistentes() {
		return qtdImoveisInconsistentes;
	}
	public void setQtdImoveisInconsistentes(Integer qtdImoveisInconsistentes) {
		this.qtdImoveisInconsistentes = qtdImoveisInconsistentes;
	}
	public Integer getQtdTotalImoveis() {
		return qtdTotalImoveis;
	}
	public void setQtdTotalImoveis(Integer qtdTotalImoveis) {
		this.qtdTotalImoveis = qtdTotalImoveis;
	}
	public Integer getQtdImoveisVisitados() {
		return qtdImoveisVisitados;
	}
	public void setQtdImoveisVisitados(Integer qtdImoveisVisitados) {
		this.qtdImoveisVisitados = qtdImoveisVisitados;
	}
	public Integer getQtdImoveisNaoVisitadas() {
		return qtdImoveisNaoVisitadas;
	}
	public void setQtdImoveisNaoVisitadas(Integer qtdImoveisNaoVisitadas) {
		this.qtdImoveisNaoVisitadas = qtdImoveisNaoVisitadas;
	}
	
}