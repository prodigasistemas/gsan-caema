package gcom.cobranca.parcelamentojudicial;

import java.io.Serializable;

public class ManterParcelamentoJudicialObterListaParcelamentoJudicialHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeClienteResponsavel;
	private String dataParcelamento;
	private String numeroParcelas;
	private String situacao;
	private String id;	
	
	public ManterParcelamentoJudicialObterListaParcelamentoJudicialHelper(){}

	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}
	public String getDataParcelamento() {
		return dataParcelamento;
	}
	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}
	public String getNumeroParcelas() {
		return numeroParcelas;
	}
	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
