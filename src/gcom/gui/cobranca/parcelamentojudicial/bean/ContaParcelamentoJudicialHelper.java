package gcom.gui.cobranca.parcelamentojudicial.bean;

import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

public class ContaParcelamentoJudicialHelper implements Comparator<ContaParcelamentoJudicialHelper>{

	private Integer idConta;
	private Integer idImovel;
	private Integer amConta;
	private Date dataVencimentoConta;
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private BigDecimal valorDebitos;
	private BigDecimal valorCreditos;
	private BigDecimal valorImpostos;
	private BigDecimal valorMulta;
	private BigDecimal valorJurosMora;
	private BigDecimal valorAtualizacaoMonetaria;
	private String situacaoConta;
	

	public String getIdConta() {
		return idConta.toString();
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public void setAmConta(Integer amConta) {
		this.amConta = amConta;
	}
	public void setDataVencimentoConta(Date dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}
	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}
	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}
	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}
	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}
	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}
	
	public Integer getIdImovel(){
		return this.idImovel;
	}
	
	public Integer getAmConta(){
		return this.amConta;
	}
	
	public String getMatriculaImovelFormatada(){
		String id = null;
		if(this.idImovel != null){
			id = this.idImovel.toString();
			id = id.substring(0,id.length() - 1) + "." + id.substring(id.length() - 1,id.length());
		}
		return id;
	}
	
	public String getAnoMesConta(){
		return Util.formatarAnoMesParaMesAno(this.amConta);
	}
	
	public String getVencimentoConta(){
		return Util.formatarData(this.dataVencimentoConta);
	}
	
	public BigDecimal getValorConta(){
		return this.valorAgua
							   .add(this.valorEsgoto)
							   .add(this.valorDebitos)
							   .subtract(this.valorCreditos)
							   .subtract(this.valorImpostos);
		
	}
	
	public String getValorContaFormatado(){
		BigDecimal total = this.valorAgua
							   .add(this.valorEsgoto)
							   .add(this.valorDebitos)
							   .subtract(this.valorCreditos)
							   .subtract(this.valorImpostos);
		
		return Util.formatarMoedaReal(total);
	}
	
	public BigDecimal getAcrescimoImpontualidade(){
		return this.valorMulta
							   .add(this.valorJurosMora)
							   .add(this.valorAtualizacaoMonetaria);
		
	}
	
	public String getAcrescimoImpontualidadeFormatado(){
		BigDecimal total = this.valorMulta
							   .add(this.valorJurosMora)
							   .add(this.valorAtualizacaoMonetaria);
		return Util.formatarMoedaReal(total);
	}
	
	public String getSituacaoConta(){
		return this.situacaoConta;
	}
	
	public int compare(ContaParcelamentoJudicialHelper o1, ContaParcelamentoJudicialHelper o2) {	
		
		//1.3.6.3. o sistema deverá ordenar pelas colunas "Imóvel" e "Mês/Ano"
		if(o1.getIdImovel().compareTo(o2.getIdImovel()) == 0)
			return o1.getAmConta().compareTo(o2.getAmConta());
		else
			return o1.getIdImovel().compareTo(o2.getIdImovel());
	}
	public BigDecimal getValorMulta() {
		return valorMulta;
	}
	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}
	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}
	
	public boolean equals(Object o){
		if(o instanceof ContaParcelamentoJudicialHelper){
			ContaParcelamentoJudicialHelper helper = (ContaParcelamentoJudicialHelper)o;
			return helper.getIdConta().equals(this.getIdConta());
		}
		else{
			return false;
		}
	}
}
