package gcom.autoatendimento;

import java.io.Serializable;

/**
 * [UC1553] - Consultar Parcelamento de Débitos Webservice
 * 
 * @author Anderson Cabral
 * @date 06/09/2013
 */
public class ContaParcelamentoDebitoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	private String periodo;
	private String vencimento;
	private String valor;
	private String valorImpontualidade;
	
	public ContaParcelamentoDebitoHelper() {
		super();
	}
	
	public ContaParcelamentoDebitoHelper(String periodo, String vencimento,
			String valor, String valorImpontualidade) {
		super();
		this.periodo = periodo;
		this.vencimento = vencimento;
		this.valor = valor;
		this.valorImpontualidade = valorImpontualidade;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getValorImpontualidade() {
		return valorImpontualidade;
	}
	public void setValorImpontualidade(String valorImpontualidade) {
		this.valorImpontualidade = valorImpontualidade;
	}
}
