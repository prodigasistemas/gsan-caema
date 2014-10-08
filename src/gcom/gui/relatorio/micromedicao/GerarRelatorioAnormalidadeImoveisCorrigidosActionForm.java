package gcom.gui.relatorio.micromedicao;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioAnormalidadeImoveisCorrigidosActionForm extends ActionForm {
	
	/**
	 * [UC1434] Gerar Relatório Anormalidade de Imóveis Corrigidos.
	 * 
	 * @author Jonathan Marcos
	 *
	 * @date 06/02/2013
	 */

	private static final long serialVersionUID = 1L;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	
	private String setorComercialInicial;
	private String nomeSetorComercialInicial;
	
	private String setorComercialFinal;
	private String nomeSetorComercialFinal;
	
	private String[] idsAnormalidadeInformada;
	
	public void setIdsAnormalidadeInformada(String[] idsAnormalidadeInformada) {
		this.idsAnormalidadeInformada = idsAnormalidadeInformada;
	}

	private String periodoDiaMesAnoInicial;
	
	private String periodoDiaMesAnoFinal;
	
	public GerarRelatorioAnormalidadeImoveisCorrigidosActionForm(){}

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

	
	public String getPeriodoDiaMesAnoInicial() {
		return periodoDiaMesAnoInicial;
	}

	public void setPeriodoDiaMesAnoInicial(String periodoDiaMesAnoInicial) {
		this.periodoDiaMesAnoInicial = periodoDiaMesAnoInicial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPeriodoDiaMesAnoFinal() {
		return periodoDiaMesAnoFinal;
	}

	public void setPeriodoDiaMesAnoFinal(String periodoDiaMesAnoFinal) {
		this.periodoDiaMesAnoFinal = periodoDiaMesAnoFinal;
	}

	public String[] getIdsAnormalidadeInformada() {
		return idsAnormalidadeInformada;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComericalFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	
	
	
	

}
