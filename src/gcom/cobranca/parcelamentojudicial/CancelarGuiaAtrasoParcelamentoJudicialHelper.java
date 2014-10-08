package gcom.cobranca.parcelamentojudicial;

import java.io.Serializable;

public class CancelarGuiaAtrasoParcelamentoJudicialHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idGuia;
	private String valor;
	private String dataVencimento;
	private String observacao;
	
	public CancelarGuiaAtrasoParcelamentoJudicialHelper(){}

	public String getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(String idGuia) {
		this.idGuia = idGuia;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
