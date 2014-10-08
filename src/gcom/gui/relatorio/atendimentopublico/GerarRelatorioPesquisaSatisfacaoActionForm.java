package gcom.gui.relatorio.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

public class GerarRelatorioPesquisaSatisfacaoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	
	private String descricaoImovel;
	
	private String idUnidade;
	
	private String descricaoUnidade;
	
	private String dataPesquisaInicio;
	
	private String dataPesquisaFim;
	
	private String criterio;
	
	private String tipo;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public String getDataPesquisaInicio() {
		return dataPesquisaInicio;
	}

	public void setDataPesquisaInicio(String dataPesquisaInicio) {
		this.dataPesquisaInicio = dataPesquisaInicio;
	}

	public String getDataPesquisaFim() {
		return dataPesquisaFim;
	}

	public void setDataPesquisaFim(String dataPesquisaFim) {
		this.dataPesquisaFim = dataPesquisaFim;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
