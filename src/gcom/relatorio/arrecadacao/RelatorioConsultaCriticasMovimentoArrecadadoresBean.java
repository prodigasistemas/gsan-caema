package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

public class RelatorioConsultaCriticasMovimentoArrecadadoresBean implements RelatorioBean{

	private String arrecadador;
	private String idServico;
	private String nsa;
	private String dataProcessamento;
	private String criticas;
	
	public String getArrecadador() {
		return arrecadador;
	}
	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public String getNsa() {
		return nsa;
	}
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	public String getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public String getCriticas() {
		return criticas;
	}
	public void setCriticas(String criticas) {
		this.criticas = criticas;
	}	
}
