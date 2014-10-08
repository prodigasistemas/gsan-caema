package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

public class RelatorioSituacaoEspecialFaturamentoBean implements RelatorioBean {
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidade;
	private String situacao;
	
	private String inscricaoImovel;
	private String matricula;
	private String motivo;
	private String inicio;
	private String termino;
	
	public RelatorioSituacaoEspecialFaturamentoBean(RelatorioSituacaoEspecialFaturamentoHelper helper) {
		this.gerenciaRegional = helper.getGerenciaRegional();
		this.unidadeNegocio = helper.getUnidadeNegocio();
		this.localidade = helper.getLocalidade();
		this.situacao = helper.getSituacao();
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.matricula = helper.getMatricula();
		this.motivo = helper.getMotivo();
		this.inicio = helper.getInicio();
		this.termino = helper.getTermino();
	}
	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getTermino() {
		return termino;
	}
	public void setTermino(String termino) {
		this.termino = termino;
	}
}
