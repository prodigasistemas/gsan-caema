package gcom.gui.mobile;

import org.apache.struts.validator.ValidatorActionForm;

public class GerarRelatorioAcompanhamentoOSCobrancaSmartphoneActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	//Campos obrigatórios
	private String opcaoRelatorio;
	private String dataReferencia;
	private String idEmpresa;
	
	//Campos não obrigatórios
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidade;
	private String descricaoLocalidade;

	private String[] idsTipoServico;
	private String periodoGeracaoOSInicial;
	private String periodoGeracaoOSFinal;
	
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public String getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(String dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
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
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String[] getIdsTipoServico() {
		return idsTipoServico;
	}
	public void setIdsTipoServico(String[] idsTipoServico) {
		this.idsTipoServico = idsTipoServico;
	}
	public String getPeriodoGeracaoOSInicial() {
		return periodoGeracaoOSInicial;
	}
	public void setPeriodoGeracaoOSInicial(String periodoGeracaoOSInicial) {
		this.periodoGeracaoOSInicial = periodoGeracaoOSInicial;
	}
	public String getPeriodoGeracaoOSFinal() {
		return periodoGeracaoOSFinal;
	}
	public void setPeriodoGeracaoOSFinal(String periodoGeracaoOSFinal) {
		this.periodoGeracaoOSFinal = periodoGeracaoOSFinal;
	}

}
