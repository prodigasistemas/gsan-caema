package gcom.autoatendimento;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC1553] - Consultar Parcelamento de Débitos Webservice
 * 
 * @author Anderson Cabral
 * @date 05/09/2013
 */
public class ParcelamentoDebitoHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	private String parcelamentoPossivel; 
	private String valorTotalConta;
	private String valorGuiaPagamento;
	private String valorAcrescimoImpotualidade;
	private String debitoCobrarServico;
	private String debitoCobrarParcelamento;
	private String creditoRealizar;
	//Debito Total = Pagamento a Vista - Valor atualizado
	private String debitoTotal;
	private String aVistaImpostos;
	private String aVistaDescontos;
	private String aVistaPagamento;
	private String quantidadeDeParcelasPermitidas;
	private String valorMinimoEntrada;  
	private ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> opcoesParcelamento;
	private ArrayList<ContaParcelamentoDebitoHelper> contas;
	private String mensagemErro;	
	
	public ParcelamentoDebitoHelper() {
		super();
	}
	public ParcelamentoDebitoHelper(
			String parcelamentoPossivel,
			String valorTotalConta,
			String valorGuiaPagamento,
			String valorAcrescimoImpotualidade,
			String debitoCobrarServico,
			String debitoCobrarParcelamento,
			String creditoRealizar,
			String debitoTotal,
			String aVistaImpostos,
			String aVistaDescontos,
			String aVistaPagamento,
			String quantidadeDeParcelasPermitidas,
			String valorMinimoEntrada,
			ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> opcoesParcelamento,
			ArrayList<ContaParcelamentoDebitoHelper> contas,
			String mensagemErro) {
		super();
		this.parcelamentoPossivel = parcelamentoPossivel;
		this.valorTotalConta = valorTotalConta;
		this.valorGuiaPagamento = valorGuiaPagamento;
		this.valorAcrescimoImpotualidade = valorAcrescimoImpotualidade;
		this.debitoCobrarServico = debitoCobrarServico;
		this.debitoCobrarParcelamento = debitoCobrarParcelamento;
		this.creditoRealizar = creditoRealizar;
		this.debitoTotal = debitoTotal;
		this.aVistaImpostos = aVistaImpostos;
		this.aVistaDescontos = aVistaDescontos;
		this.aVistaPagamento = aVistaPagamento;
		this.quantidadeDeParcelasPermitidas = quantidadeDeParcelasPermitidas;
		this.valorMinimoEntrada = valorMinimoEntrada;
		this.opcoesParcelamento = opcoesParcelamento;
		this.contas = contas;
		this.mensagemErro = mensagemErro;
	}
	public String getParcelamentoPossivel() {
		return parcelamentoPossivel;
	}
	public void setParcelamentoPossivel(String parcelamentoPossivel) {
		this.parcelamentoPossivel = parcelamentoPossivel;
	}
	public String getValorTotalConta() {
		return valorTotalConta;
	}
	public void setValorTotalConta(String valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}
	public String getValorGuiaPagamento() {
		return valorGuiaPagamento;
	}
	public void setValorGuiaPagamento(String valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}
	public String getValorAcrescimoImpotualidade() {
		return valorAcrescimoImpotualidade;
	}
	public void setValorAcrescimoImpotualidade(String valorAcrescimoImpotualidade) {
		this.valorAcrescimoImpotualidade = valorAcrescimoImpotualidade;
	}
	public String getDebitoCobrarServico() {
		return debitoCobrarServico;
	}
	public void setDebitoCobrarServico(String debitoCobrarServico) {
		this.debitoCobrarServico = debitoCobrarServico;
	}
	public String getDebitoCobrarParcelamento() {
		return debitoCobrarParcelamento;
	}
	public void setDebitoCobrarParcelamento(String debitoCobrarParcelamento) {
		this.debitoCobrarParcelamento = debitoCobrarParcelamento;
	}
	public String getCreditoRealizar() {
		return creditoRealizar;
	}
	public void setCreditoRealizar(String creditoRealizar) {
		this.creditoRealizar = creditoRealizar;
	}
	/**Debito Total = Pagamento a Vista - Valor atualizado**/
	public String getDebitoTotal() {
		return debitoTotal;
	}
	/**Debito Total = Pagamento a Vista - Valor atualizado**/
	public void setDebitoTotal(String debitoTotal) {
		this.debitoTotal = debitoTotal;
	}
	public String getaVistaImpostos() {
		return aVistaImpostos;
	}
	public void setaVistaImpostos(String aVistaImpostos) {
		this.aVistaImpostos = aVistaImpostos;
	}
	public String getaVistaDescontos() {
		return aVistaDescontos;
	}
	public void setaVistaDescontos(String aVistaDescontos) {
		this.aVistaDescontos = aVistaDescontos;
	}
	public String getaVistaPagamento() {
		return aVistaPagamento;
	}
	public void setaVistaPagamento(String aVistaPagamento) {
		this.aVistaPagamento = aVistaPagamento;
	}
	public String getQuantidadeDeParcelasPermitidas() {
		return quantidadeDeParcelasPermitidas;
	}
	public void setQuantidadeDeParcelasPermitidas(
			String quantidadeDeParcelasPermitidas) {
		this.quantidadeDeParcelasPermitidas = quantidadeDeParcelasPermitidas;
	}
	public ArrayList<ContaParcelamentoDebitoHelper> getContas() {
		return contas;
	}
	public void setContas(ArrayList<ContaParcelamentoDebitoHelper> contas) {
		this.contas = contas;
	}
	public String getValorMinimoEntrada() {
		return valorMinimoEntrada;
	}
	public void setValorMinimoEntrada(String valorMinimoEntrada) {
		this.valorMinimoEntrada = valorMinimoEntrada;
	}
	public ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> getOpcoesParcelamento() {
		return opcoesParcelamento;
	}
	public void setOpcoesParcelamento(
			ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> opcoesParcelamento) {
		this.opcoesParcelamento = opcoesParcelamento;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

}
