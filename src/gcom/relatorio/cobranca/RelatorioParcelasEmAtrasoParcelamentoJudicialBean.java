package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;


/**
 * [UC1465] Gerar Relatório das Parcelas em Atraso do Parcelamento Judicial
 * 
 * @author Mariana Victor
 * @date 22/04/2013
 */
public class RelatorioParcelasEmAtrasoParcelamentoJudicialBean implements RelatorioBean {

	private String numeroParcela;
	
	private String dataVencimento;
	
	private String valorParcela;
	
	private String quantidadeDiasAtraso;
	
	private String idParcelamento;
	
	private String numeroProcesso;
	
	private String imovelPrincipal;
	
	private String clienteResponsavel;

	
	public String getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}

	public String getQuantidadeDiasAtraso() {
		return quantidadeDiasAtraso;
	}

	public void setQuantidadeDiasAtraso(String quantidadeDiasAtraso) {
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	public String getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(String idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public String getImovelPrincipal() {
		return imovelPrincipal;
	}

	public void setImovelPrincipal(String imovelPrincipal) {
		this.imovelPrincipal = imovelPrincipal;
	}

	public String getClienteResponsavel() {
		return clienteResponsavel;
	}

	public void setClienteResponsavel(String clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}
	
	

}
