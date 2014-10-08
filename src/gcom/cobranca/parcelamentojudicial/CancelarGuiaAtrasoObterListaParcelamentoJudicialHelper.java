package gcom.cobranca.parcelamentojudicial;

import java.io.Serializable;

public class CancelarGuiaAtrasoObterListaParcelamentoJudicialHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroProcesso;
	private String idImovel;
	private String clienteResponsavel;
	private String dataParcelamento;
	
	public CancelarGuiaAtrasoObterListaParcelamentoJudicialHelper(){}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getClienteResponsavel() {
		return clienteResponsavel;
	}
	public void setClienteResponsavel(String clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}
	public String getDataParcelamento() {
		return dataParcelamento;
	}
	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
