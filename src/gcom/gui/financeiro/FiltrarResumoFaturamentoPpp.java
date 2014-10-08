package gcom.gui.financeiro;

import java.io.Serializable;

/**
 * [UC1481] Gerar Relatório de Faturamento PPP
 * 
 * @author Jonathan Marcos
 * @date 29/05/2013
 */
public class FiltrarResumoFaturamentoPpp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String anoMes;
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idSistema;
	private String idSubSistemaEsgoto;
	private String idLocalidade;
	private String idMunicipio;
	private String opcaoTotalizacao;
	
	public FiltrarResumoFaturamentoPpp(){}

	public String getAnoMes() {
		return anoMes;
	}
	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getIdSubSistemaEsgoto() {
		return idSubSistemaEsgoto;
	}

	public void setIdSubSistemaEsgoto(String idSubSistemaEsgoto) {
		this.idSubSistemaEsgoto = idSubSistemaEsgoto;
	}

	public String getIdLocalidade(){
		return this.idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade){
		this.idLocalidade = idLocalidade;
	}
	public String getIdMunicipio(){
		return this.idMunicipio;
	}
	public void setIdMunicipio(String idMUnicipio){
		this.idMunicipio = idMUnicipio;
	}
	public String getOpcaoTotalizacao(){
		return this.opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(String opcaoTotalizacao){
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
}
