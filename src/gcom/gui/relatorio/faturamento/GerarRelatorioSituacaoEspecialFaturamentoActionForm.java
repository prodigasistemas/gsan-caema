package gcom.gui.relatorio.faturamento;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioSituacaoEspecialFaturamentoActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidadeInicialID;
	private String localidadeInicialDesc;
	private String setorComercialInicialID;
	private String setorComercialInicialDesc;
	private String localidadeFinalID;
	private String localidadeFinalDesc;
	private String setorComercialFinalID;
	private String setorComercialFinalDesc;
	private String[] situacao;
	private String[] motivo;
	
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
	public String getLocalidadeInicialID() {
		return localidadeInicialID;
	}
	public void setLocalidadeInicialID(String localidadeInicialID) {
		this.localidadeInicialID = localidadeInicialID;
	}
	public String getLocalidadeInicialDesc() {
		return localidadeInicialDesc;
	}
	public void setLocalidadeInicialDesc(String localidadeInicialDesc) {
		this.localidadeInicialDesc = localidadeInicialDesc;
	}
	public String getSetorComercialInicialID() {
		return setorComercialInicialID;
	}
	public void setSetorComercialInicialID(String setorComercialInicialID) {
		this.setorComercialInicialID = setorComercialInicialID;
	}
	public String getSetorComercialInicialDesc() {
		return setorComercialInicialDesc;
	}
	public void setSetorComercialInicialDesc(String setorComercialInicialDesc) {
		this.setorComercialInicialDesc = setorComercialInicialDesc;
	}
	public String getLocalidadeFinalID() {
		return localidadeFinalID;
	}
	public void setLocalidadeFinalID(String localidadeFinalID) {
		this.localidadeFinalID = localidadeFinalID;
	}
	public String getLocalidadeFinalDesc() {
		return localidadeFinalDesc;
	}
	public void setLocalidadeFinalDesc(String localidadeFinalDesc) {
		this.localidadeFinalDesc = localidadeFinalDesc;
	}
	public String getSetorComercialFinalID() {
		return setorComercialFinalID;
	}
	public void setSetorComercialFinalID(String setorComercialFinalID) {
		this.setorComercialFinalID = setorComercialFinalID;
	}
	public String getSetorComercialFinalDesc() {
		return setorComercialFinalDesc;
	}
	public void setSetorComercialFinalDesc(String setorComercialFinalDesc) {
		this.setorComercialFinalDesc = setorComercialFinalDesc;
	}
	public String[] getSituacao() {
		return situacao;
	}
	public void setSituacao(String[] situacao) {
		this.situacao = situacao;
	}
	public String[] getMotivo() {
		return motivo;
	}
	public void setMotivo(String[] motivo) {
		this.motivo = motivo;
	}
	
}