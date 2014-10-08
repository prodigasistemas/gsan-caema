package gcom.relatorio.atendimentopublico;

import java.math.BigDecimal;
import java.util.Date;

import gcom.relatorio.RelatorioBean;

/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobrança para Smartphone 
 * 
 * @author Anderson Cabral
 * @since 21/08/2013
 */
public class RelatorioAcompanhamentoOSCobrancaSmartphoneBean implements RelatorioBean {
	
	//Geral
	private String servicoTipo;
	private String motivo;
	
	//Analitico
	private String numeroOS;
	private String matriculaImovel;
	private Date dataGeracao;
	private Date dataEncerramento;
	private String loginUsuario;
	private String parecerEncerramento;
	private String indicadorOrigem;
	private BigDecimal valorDocumentoCobranca;
	private BigDecimal valorAtualDebito;
	private String endImovel;
	
	
	//Sintetico
	private Integer quantidadeOSCronograma;
	private BigDecimal valorDocumentoCronograma;
	private Integer quantidadeOSEventual;
	private BigDecimal valorDocumentoEventual;
	private Integer quantidadeOSOnline;
	private BigDecimal valorDocumentoOnline;
	private Integer quantidadeOSRA;
	private BigDecimal valorDocumentoRA;
	private Integer quantidadeTotal;
	private BigDecimal valorTotal;
	private Integer idServicoTipo;
	
	
	public RelatorioAcompanhamentoOSCobrancaSmartphoneBean() {
		super();
	}


	public String getServicoTipo() {
		return servicoTipo;
	}
	public void setServicoTipo(String servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public Date getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public String getParecerEncerramento() {
		return parecerEncerramento;
	}
	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}
	public String getIndicadorOrigem() {
		return indicadorOrigem;
	}
	public void setIndicadorOrigem(String indicadorOrigem) {
		this.indicadorOrigem = indicadorOrigem;
	}
	public BigDecimal getValorDocumentoCobranca() {
		return valorDocumentoCobranca;
	}
	public void setValorDocumentoCobranca(BigDecimal valorDocumentoCobranca) {
		this.valorDocumentoCobranca = valorDocumentoCobranca;
	}
	public BigDecimal getValorAtualDebito() {
		return valorAtualDebito;
	}
	public void setValorAtualDebito(BigDecimal valorAtualDebito) {
		this.valorAtualDebito = valorAtualDebito;
	}
	public String getEndImovel() {
		return endImovel;
	}
	public void setEndImovel(String endImovel) {
		this.endImovel = endImovel;
	}
	public Integer getQuantidadeOSCronograma() {
		return quantidadeOSCronograma;
	}
	public void setQuantidadeOSCronograma(Integer quantidadeOSCronograma) {
		this.quantidadeOSCronograma = quantidadeOSCronograma;
	}
	public BigDecimal getValorDocumentoCronograma() {
		return valorDocumentoCronograma;
	}
	public void setValorDocumentoCronograma(BigDecimal valorDocumentoCronograma) {
		this.valorDocumentoCronograma = valorDocumentoCronograma;
	}
	public Integer getQuantidadeOSEventual() {
		return quantidadeOSEventual;
	}
	public void setQuantidadeOSEventual(Integer quantidadeOSEventual) {
		this.quantidadeOSEventual = quantidadeOSEventual;
	}
	public BigDecimal getValorDocumentoEventual() {
		return valorDocumentoEventual;
	}
	public void setValorDocumentoEventual(BigDecimal valorDocumentoEventual) {
		this.valorDocumentoEventual = valorDocumentoEventual;
	}
	public Integer getQuantidadeOSOnline() {
		return quantidadeOSOnline;
	}
	public void setQuantidadeOSOnline(Integer quantidadeOSOnline) {
		this.quantidadeOSOnline = quantidadeOSOnline;
	}
	public BigDecimal getValorDocumentoOnline() {
		return valorDocumentoOnline;
	}
	public void setValorDocumentoOnline(BigDecimal valorDocumentoOnline) {
		this.valorDocumentoOnline = valorDocumentoOnline;
	}
	public Integer getQuantidadeOSRA() {
		return quantidadeOSRA;
	}
	public void setQuantidadeOSRA(Integer quantidadeOSRA) {
		this.quantidadeOSRA = quantidadeOSRA;
	}
	public BigDecimal getValorDocumentoRA() {
		return valorDocumentoRA;
	}
	public void setValorDocumentoRA(BigDecimal valorDocumentoRA) {
		this.valorDocumentoRA = valorDocumentoRA;
	}
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	
}
