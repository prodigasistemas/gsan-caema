package gcom.atendimentopublico.bean;

import java.io.Serializable;

public class PesquisaSatisfacaoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String idUnidade;
	private String dataPeriodoInicial;
	private String dataPeriodoFinal;
	private String criterio;
	
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}
	public String getDataPeriodoInicial() {
		return dataPeriodoInicial;
	}
	public void setDataPeriodoInicial(String dataPeriodoInicial) {
		this.dataPeriodoInicial = dataPeriodoInicial;
	}
	public String getDataPeriodoFinal() {
		return dataPeriodoFinal;
	}
	public void setDataPeriodoFinal(String dataPeriodoFinal) {
		this.dataPeriodoFinal = dataPeriodoFinal;
	}
	public String getCriterio() {
		return criterio;
	}
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
}
