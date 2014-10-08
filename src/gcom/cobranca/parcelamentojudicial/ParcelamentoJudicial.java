package gcom.cobranca.parcelamentojudicial;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.parcelamento.ParcelamentoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ControleAlteracao()
public class ParcelamentoJudicial implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;
	
	private Integer anoMesReferenciafaturamento;
	
	private Date dataParcelamento;
	
	private Date dataCancelamento;
	
	private String numeroProcesso;
	
	private String numeroOAB;
	
	private String observacao;
	
	private Short indicadorValorCusta;
	
	private Short indicadorValorHonorario;
	
	private Short indicadorValorInformaValorParc;
	
	private Short indicadorParcelamentoComJuros;
	
	private Short indicadorPerderDesconto;
	
	private Integer numeroDiasVencimentoParcelas;
	
	private Short indicadorParcelamentoEntrada;
	
	private Integer numeroPrestacoes;
	
	private BigDecimal taxaJuros;
	
	private BigDecimal percentualCustas;

	private BigDecimal percentualHonorario;

	private BigDecimal percentualDesconto;
	
	private BigDecimal valorConta;
	
	private BigDecimal valorAcrescimos;
	
	private BigDecimal valorAcordo;
	
	private BigDecimal valorCustas;
	
	private BigDecimal valorHonorario;
	
	private BigDecimal valorParcelado;
	
	private BigDecimal valorEntrada;
	
	private Cliente clienteResponsavel;
	
	private String nomeAdvogado;
	
	private Cliente clienteUsuario;
	
	private Usuario usuarioResponsavel;
	
	private Usuario usuarioCancelamento;
	
	private ParcelamentoSituacao parcelamentoSituacao;
	
	private ParcelamentoTipo parcelamentoTipo;
	
	private ParcelamentoMotivoDesfazer motivoDesfazer;
	
    private byte[] arquivoPDF;
	
	private Date ultimaAlteracao;
	
	/** default constructor */
	public ParcelamentoJudicial() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAnoMesReferenciafaturamento() {
		return anoMesReferenciafaturamento;
	}

	public void setAnoMesReferenciafaturamento(Integer anoMesReferenciafaturamento) {
		this.anoMesReferenciafaturamento = anoMesReferenciafaturamento;
	}

	public Date getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(Date dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public String getNumeroOAB() {
		return numeroOAB;
	}

	public void setNumeroOAB(String numeroOAB) {
		this.numeroOAB = numeroOAB;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Short getIndicadorValorCusta() {
		return indicadorValorCusta;
	}

	public void setIndicadorValorCusta(Short indicadorValorCusta) {
		this.indicadorValorCusta = indicadorValorCusta;
	}

	public Short getIndicadorValorHonorario() {
		return indicadorValorHonorario;
	}

	public void setIndicadorValorHonorario(Short indicadorValorHonorario) {
		this.indicadorValorHonorario = indicadorValorHonorario;
	}

	public Short getIndicadorValorInformaValorParc() {
		return indicadorValorInformaValorParc;
	}

	public void setIndicadorValorInformaValorParc(
			Short indicadorValorInformaValorParc) {
		this.indicadorValorInformaValorParc = indicadorValorInformaValorParc;
	}

	public Short getIndicadorParcelamentoComJuros() {
		return indicadorParcelamentoComJuros;
	}

	public void setIndicadorParcelamentoComJuros(Short indicadorParcelamentoComJuros) {
		this.indicadorParcelamentoComJuros = indicadorParcelamentoComJuros;
	}

	public Short getIndicadorPerderDesconto() {
		return indicadorPerderDesconto;
	}

	public void setIndicadorPerderDesconto(Short indicadorPerderDesconto) {
		this.indicadorPerderDesconto = indicadorPerderDesconto;
	}

	public Integer getNumeroDiasVencimentoParcelas() {
		return numeroDiasVencimentoParcelas;
	}

	public void setNumeroDiasVencimentoParcelas(Integer numeroDiasVencimentoParcelas) {
		this.numeroDiasVencimentoParcelas = numeroDiasVencimentoParcelas;
	}

	public Integer getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Integer numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public BigDecimal getPercentualCustas() {
		return percentualCustas;
	}

	public void setPercentualCustas(BigDecimal percentualCustas) {
		this.percentualCustas = percentualCustas;
	}

	public BigDecimal getPercentualHonorario() {
		return percentualHonorario;
	}

	public void setPercentualHonorario(BigDecimal percentualHonorario) {
		this.percentualHonorario = percentualHonorario;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}

	public BigDecimal getValorAcordo() {
		return valorAcordo;
	}

	public void setValorAcordo(BigDecimal valorAcordo) {
		this.valorAcordo = valorAcordo;
	}

	public BigDecimal getValorCustas() {
		return valorCustas;
	}

	public void setValorCustas(BigDecimal valorCustas) {
		this.valorCustas = valorCustas;
	}

	public BigDecimal getValorHonorario() {
		return valorHonorario;
	}

	public void setValorHonorario(BigDecimal valorHonorario) {
		this.valorHonorario = valorHonorario;
	}

	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	public Cliente getClienteResponsavel() {
		return clienteResponsavel;
	}

	public void setClienteResponsavel(Cliente clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}

	public String getNomeAdvogado() {
		return nomeAdvogado;
	}

	public void setNomeAdvogado(String nomeAdvogado) {
		this.nomeAdvogado = nomeAdvogado;
	}

	public Cliente getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(Cliente clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public Usuario getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(Usuario usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}

	public ParcelamentoSituacao getParcelamentoSituacao() {
		return parcelamentoSituacao;
	}

	public void setParcelamentoSituacao(ParcelamentoSituacao parcelamentoSituacao) {
		this.parcelamentoSituacao = parcelamentoSituacao;
	}

	public ParcelamentoTipo getParcelamentoTipo() {
		return parcelamentoTipo;
	}

	public void setParcelamentoTipo(ParcelamentoTipo parcelamentoTipo) {
		this.parcelamentoTipo = parcelamentoTipo;
	}

	public ParcelamentoMotivoDesfazer getMotivoDesfazer() {
		return motivoDesfazer;
	}

	public void setMotivoDesfazer(ParcelamentoMotivoDesfazer motivoDesfazer) {
		this.motivoDesfazer = motivoDesfazer;
	}

	public byte[] getArquivoPDF() {
		return arquivoPDF;
	}

	public void setArquivoPDF(byte[] arquivoPDF) {
		this.arquivoPDF = arquivoPDF;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
		
	public String getAnoMesReferenciFaturamentoFormatado(){
		if(this.getAnoMesReferenciafaturamento() == null){
			return "";
		}
		return this.getAnoMesReferenciafaturamento().toString().substring(4, 6) +"/"+ this.getAnoMesReferenciafaturamento().toString().substring(0, 4);
	}

	public Short getIndicadorParcelamentoEntrada() {
		return indicadorParcelamentoEntrada;
	}

	public void setIndicadorParcelamentoEntrada(Short indicadorParcelamentoEntrada) {
		this.indicadorParcelamentoEntrada = indicadorParcelamentoEntrada;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

}
