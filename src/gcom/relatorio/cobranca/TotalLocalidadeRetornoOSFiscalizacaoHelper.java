package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

public class TotalLocalidadeRetornoOSFiscalizacaoHelper implements RelatorioBean {

	private String totalLocaPendente; 
	private String totalLocaParcelado;
	private String totalLocaPago;
	private String totalLocaCancelado;
	private String idLocalidade;
	
	public String getTotalLocaPendente() {
		return totalLocaPendente;
	}
	public void setTotalLocaPendente(String totalLocaPendente) {
		this.totalLocaPendente = totalLocaPendente;
	}
	public String getTotalLocaParcelado() {
		return totalLocaParcelado;
	}
	public void setTotalLocaParcelado(String totalLocaParcelado) {
		this.totalLocaParcelado = totalLocaParcelado;
	}
	public String getTotalLocaPago() {
		return totalLocaPago;
	}
	public void setTotalLocaPago(String totalLocaPago) {
		this.totalLocaPago = totalLocaPago;
	}
	public String getTotalLocaCancelado() {
		return totalLocaCancelado;
	}
	public void setTotalLocaCancelado(String totalLocaCancelado) {
		this.totalLocaCancelado = totalLocaCancelado;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}	
}
