package gcom.gui.cadastro.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;


public class RelatorioImoveisLigadosParaQuadraSemRedeBean implements RelatorioBean {
	
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String redeAgua;
	private String redeEsgoto;
	private String matricula;
	private String situacaoAgua;
	private String situacaoEsgoto;
	
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public String getRedeAgua() {
		return redeAgua;
	}
	public void setRedeAgua(String redeAgua) {
		this.redeAgua = redeAgua;
	}
	public String getRedeEsgoto() {
		return redeEsgoto;
	}
	public void setRedeEsgoto(String redeEsgoto) {
		this.redeEsgoto = redeEsgoto;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
}
