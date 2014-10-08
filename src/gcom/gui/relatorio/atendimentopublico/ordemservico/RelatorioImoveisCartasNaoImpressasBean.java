package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

public class RelatorioImoveisCartasNaoImpressasBean implements RelatorioBean{

	private String municipio;
	private String faturamentoGrupo;
	private String anoMesFaturamento;
	private String numeroOS;
	private String matriculaImovel;
	private String motivoEncerramento;
	private String dataGeracao;
	private String dataEncerramento;
	
	private Integer idMunicipio;
	private Integer idGrupoFaturamento;
	private Integer amFaturamentoInt;
	
	
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}
	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}
	public String getAnoMesFaturamento() {
		return anoMesFaturamento;
	}
	public void setAnoMesFaturamento(String anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	public Integer getAmFaturamentoInt() {
		return amFaturamentoInt;
	}
	public void setAmFaturamentoInt(Integer amFaturamentoInt) {
		this.amFaturamentoInt = amFaturamentoInt;
	}
	
}
