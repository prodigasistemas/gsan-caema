package gcom.integracao;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jonathan Marcos
 * @date 17/10/2013
 * [UC1568] Receber Informações Abastecimentos Carros Pipa Via Webservice   
 * [Observacao] 
 * 		1 - Classe Basica
 */
public class CarroPipaAbastecimento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricaoPlaca;
	private Date dataAbastecimento;
	private Imovel imovel;
	private BigDecimal volumeAbastecimento;
	private Short indicadorCobranca;
	private Short indicadorAbastecimentoAvulso;
	private Date ultimaAlteracao;
	private Integer anoMesReferenciaFaturamento;
	private BigDecimal valorAbastecimento;
	
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricaoPlaca() {
		return descricaoPlaca;
	}
	public void setDescricaoPlaca(String descricaoPlaca) {
		this.descricaoPlaca = descricaoPlaca;
	}
	public Date getDataAbastecimento() {
		return dataAbastecimento;
	}
	public void setDataAbastecimento(Date dataAbastecimento) {
		this.dataAbastecimento = dataAbastecimento;
	}
	public BigDecimal getVolumeAbastecimento() {
		return volumeAbastecimento;
	}
	public void setVolumeAbastecimento(BigDecimal volumeAbastecimento) {
		this.volumeAbastecimento = volumeAbastecimento;
	}
	public Short getIndicadorCobranca() {
		return indicadorCobranca;
	}
	public void setIndicadorCobranca(Short indicadorCobranca) {
		this.indicadorCobranca = indicadorCobranca;
	}
	public Short getIndicadorAbastecimentoAvulso() {
		return indicadorAbastecimentoAvulso;
	}
	public void setIndicadorAbastecimentoAvulso(Short indicadorAbastecimentoAvulso) {
		this.indicadorAbastecimentoAvulso = indicadorAbastecimentoAvulso;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Integer getAnoMesReferenciaFaturamento() {
		return anoMesReferenciaFaturamento;
	}
	public void setAnoMesReferenciaFaturamento(Integer anoMesReferenciaFaturamento) {
		this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
	}
	public BigDecimal getValorAbastecimento() {
		return valorAbastecimento;
	}
	public void setValorAbastecimento(BigDecimal valorAbastecimento) {
		this.valorAbastecimento = valorAbastecimento;
	}
}
