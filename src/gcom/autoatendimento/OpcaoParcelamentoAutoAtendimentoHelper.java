package gcom.autoatendimento;

/**
 * [UC1553] - Consultar Parcelamento de Débitos Webservice
 * 
 * @author Anderson Cabral
 * @date 05/09/2013
 */
public class OpcaoParcelamentoAutoAtendimentoHelper {

	private String numeroParcela;
	private String valorPrestacao;
	private String taxaJuros;
	
	public OpcaoParcelamentoAutoAtendimentoHelper() {
		super();
	}
	public OpcaoParcelamentoAutoAtendimentoHelper(String numeroParcela,
			String valorPrestacao, String taxaJuros) {
		super();
		this.numeroParcela = numeroParcela;
		this.valorPrestacao = valorPrestacao;
		this.taxaJuros = taxaJuros;
	}
	public String getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public String getValorPrestacao() {
		return valorPrestacao;
	}
	public void setValorPrestacao(String valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}
	public String getTaxaJuros() {
		return taxaJuros;
	}
	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}
	
	
}
