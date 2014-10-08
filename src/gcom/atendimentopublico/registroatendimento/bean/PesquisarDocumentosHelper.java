package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.CobrancaIndevidaMotivo;
import gcom.cobranca.DocumentoTipo;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;


public class PesquisarDocumentosHelper {

	private Integer idPagamento;
	private Short indicadorTipoDocumento;
	private Integer idDocumento;
	private Integer amReferenciaDocumento;
	private Integer amReferenciaPagamento;
	private Date dataEmissaoDocumento; 
	private Date dataVencimentoDocumento;
	private Date dataPagamentoDocumento;
	private BigDecimal valorPagamento;
	private BigDecimal valorCorrigido;
	private BigDecimal valorDevolucao;
	private CobrancaIndevidaMotivo motivoCobIndevida;
	
	public Integer getAmReferenciaDocumento() {
		return amReferenciaDocumento;
	}

	public void setAmReferenciaDocumento(Integer amReferenciaDocumento) {
		this.amReferenciaDocumento = amReferenciaDocumento;
	}

	public Integer getIdPagamento() {
		return idPagamento;
	}

	public Short getIndicadorTipoDocumento() {
		return indicadorTipoDocumento;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	public Date getDataVencimentoDocumento() {
		return dataVencimentoDocumento;
	}

	public Date getDataPagamentoDocumento() {
		return dataPagamentoDocumento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public BigDecimal getValorCorrigido() {
		return valorCorrigido;
	}

	public BigDecimal getValorDevolucao() {
		return valorDevolucao;
	}

	public CobrancaIndevidaMotivo getMotivoCobIndevida() {
		return motivoCobIndevida;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public void setIndicadorTipoDocumento(Short indicadorTipoDocumento) {
		this.indicadorTipoDocumento = indicadorTipoDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	public void setDataVencimentoDocumento(Date dataVencimentoDocumento) {
		this.dataVencimentoDocumento = dataVencimentoDocumento;
	}

	public void setDataPagamentoDocumento(Date dataPagamentoDocumento) {
		this.dataPagamentoDocumento = dataPagamentoDocumento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public void setValorCorrigido(BigDecimal valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}

	public void setValorDevolucao(BigDecimal valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public void setMotivoCobIndevida(CobrancaIndevidaMotivo motivoCobIndevida) {
		this.motivoCobIndevida = motivoCobIndevida;
	}
	
	public Integer getAmReferenciaPagamento() {
		return amReferenciaPagamento;
	}

	public void setAmReferenciaPagamento(Integer amReferenciaPagamento) {
		this.amReferenciaPagamento = amReferenciaPagamento;
	}

	//Funções auxiliares para o JSTL da tela
	//--------------------------------------------------
	public String getTipoDocumentoConta(){
		return DocumentoTipo.CONTA.toString();
	}
	
	public String getTipoDocumentoGuiaPagamento(){
		return DocumentoTipo.GUIA_PAGAMENTO.toString();
	}
	
	public String getTipoDocumentoDebitoACobrar(){
		return DocumentoTipo.DEBITO_A_COBRAR.toString();
	}
	
	public String getAmReferenciaDocumentoFormatado(){
		if(this.getAmReferenciaDocumento() != null)
			return Util.formatarAnoMesParaMesAno(this.getAmReferenciaDocumento());
		else
			return null;
	}
	
	public String getAmReferenciaPagamentoFormatado(){
		if(this.getAmReferenciaPagamento() != null)
			return Util.formatarAnoMesParaMesAno(this.getAmReferenciaPagamento());
		else
			return null;
	}
	
	public String getDataEmissaoDocumentoFormatado(){
		return Util.formatarData(this.getDataEmissaoDocumento());
	}
	
	public String getDataVencimentoDocumentoFormatado(){
		return Util.formatarData(this.getDataVencimentoDocumento());
	}
	
	public String getDataPagamentoDocumentoFormatado(){
		return Util.formatarData(this.getDataPagamentoDocumento());
	}
	
	public String getValorPagamentoFormatado(){
		if(this.getValorPagamento() != null)
			return Util.formataBigDecimal(this.getValorPagamento(), 2, true);
		else
			return null;
	}
	
	public String getValorCorrigidoFormatado(){
		if(this.getValorCorrigido() != null)
			return Util.formataBigDecimal(this.getValorCorrigido(), 2, true);
		else
			return null;
	}
	
	public String getValorDevolucaoFormatado(){
		if(this.getValorDevolucao() != null)
			return Util.formatarMoedaReal(this.getValorDevolucao());
		else
			return null;
	}
	
	public boolean getValorDevolucaoNegativo(){
		if(this.getValorDevolucao() != null){
			if(this.getValorDevolucao().compareTo(BigDecimal.valueOf(0)) < 0){
				return true;
			}
		}
		return false;
	}
	//--------------------------------------------------
	
	
	
	
	
}
