package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

public class RelatorioAnaliseInconsistenciasAtualizacaoCadastralBean implements RelatorioBean {

	private String tipoAtualizacao;
	private String localidade;
	private String analista;
	private Integer setor;
	private Integer quadra;
	private String matriculaImovel;
	private String cadastrador;
	private String dadosInconsistentes;
	private String inconsistencia;
	
	
	public String getTipoAtualizacao() {
		return tipoAtualizacao;
	}
	public void setTipoAtualizacao(String tipoAtualizacao) {
		this.tipoAtualizacao = tipoAtualizacao;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public Integer getSetor() {
		return setor;
	}
	public void setSetor(Integer setor) {
		this.setor = setor;
	}
	public Integer getQuadra() {
		return quadra;
	}
	public void setQuadra(Integer quadra) {
		this.quadra = quadra;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String imovel) {
		this.matriculaImovel = imovel;
	}
	public String getCadastrador() {
		return cadastrador;
	}
	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}
	public String getDadosInconsistentes() {
		return dadosInconsistentes;
	}
	public void setDadosInconsistentes(String dadosInconsistentes) {
		this.dadosInconsistentes = dadosInconsistentes;
	}
	public String getInconsistencia() {
		return inconsistencia;
	}
	public void setInconsistencia(String inconsistencia) {
		this.inconsistencia = inconsistencia;
	}
	
	
	
	
}
