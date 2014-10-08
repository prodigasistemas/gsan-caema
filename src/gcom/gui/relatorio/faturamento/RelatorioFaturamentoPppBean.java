package gcom.gui.relatorio.faturamento;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1481] Gerar Relatório de Faturamento PPP
 * 
 * @author Jonathan Marcos
 * @date 29/05/2013
 */
public class RelatorioFaturamentoPppBean implements RelatorioBean {

	private String idImovel;
	private String nomeCliente;
	private String nomeLocalidade;
	private String descricaoSituacaoEsgoto;
	private String mesAnoConta;
	private BigDecimal esgoto;
	private BigDecimal coleta;
	private BigDecimal volume;
	private BigDecimal valorEsgoto;
	private BigDecimal valorEsgotoPpp;
	private String nomeGerenciaRegional;
	private String nomeUnidadeNegocio;
	private BigDecimal valorRemuneracao;
	private String idLocalidade;
	
	public RelatorioFaturamentoPppBean(){}
	public RelatorioFaturamentoPppBean(RelatorioFaturamentoPppHelper relatorioFaturamentoPppHelper){
		this.idImovel = relatorioFaturamentoPppHelper.getIdImovel();
		this.nomeCliente = relatorioFaturamentoPppHelper.getNomeCliente();
		this.nomeLocalidade = relatorioFaturamentoPppHelper.getNomeLocalidade();
		this.descricaoSituacaoEsgoto = relatorioFaturamentoPppHelper.getDescricaoSituacaoEsgoto();
		this.mesAnoConta = relatorioFaturamentoPppHelper.getMesAnoConta();
		this.esgoto = relatorioFaturamentoPppHelper.getEsgoto();
		this.coleta = relatorioFaturamentoPppHelper.getColeta();
		this.volume = relatorioFaturamentoPppHelper.getVolume();
		this.valorEsgoto = relatorioFaturamentoPppHelper.getValorEsgoto();
		this.valorEsgotoPpp = relatorioFaturamentoPppHelper.getValorEsgotoPpp();
		this.nomeGerenciaRegional = relatorioFaturamentoPppHelper.getNomeGerenciaRegional();
		this.nomeUnidadeNegocio = relatorioFaturamentoPppHelper.getNomeUnidadeNegocio();
		this.valorRemuneracao = relatorioFaturamentoPppHelper.getValorRemuneracao();
		this.idLocalidade = relatorioFaturamentoPppHelper.getIdLocalidade();
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getDescricaoSituacaoEsgoto() {
		return descricaoSituacaoEsgoto;
	}
	public void setDescricaoSituacaoEsgoto(String descricaoSituacaoEsgoto) {
		this.descricaoSituacaoEsgoto = descricaoSituacaoEsgoto;
	}
	public String getMesAnoConta() {
		return mesAnoConta;
	}
	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}
	public BigDecimal getEsgoto() {
		return esgoto;
	}
	public void setEsgoto(BigDecimal esgoto) {
		this.esgoto = esgoto;
	}
	public BigDecimal getColeta() {
		return coleta;
	}
	public void setColeta(BigDecimal coleta) {
		this.coleta = coleta;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}
	public BigDecimal getValorEsgotoPpp() {
		return valorEsgotoPpp;
	}
	public void setValorEsgotoPpp(BigDecimal valorEsgotoPpp) {
		this.valorEsgotoPpp = valorEsgotoPpp;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeUnidadeNegocio(){
		return this.nomeUnidadeNegocio;
	}
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio){
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	public BigDecimal getValorRemuneracao() {
		return valorRemuneracao;
	}
	public void setValorRemuneracao(BigDecimal valorRemuneracao) {
		this.valorRemuneracao = valorRemuneracao;
	}
	public String getIdLocalidade(){
		return this.idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade){
		this.idLocalidade = idLocalidade;
	}
}
