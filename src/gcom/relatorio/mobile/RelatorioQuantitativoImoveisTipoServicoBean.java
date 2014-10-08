package gcom.relatorio.mobile;

import gcom.relatorio.RelatorioBean;

public class RelatorioQuantitativoImoveisTipoServicoBean implements RelatorioBean {
	
	private String localidade;
	private String setor;
	private String quadra;
	private String tipoServico;
	private Integer quantidadeImoveis;
	
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public Integer getQuantidadeImoveis() {
		return quantidadeImoveis;
	}
	public void setQuantidadeImoveis(Integer quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}
	
	
	
}
