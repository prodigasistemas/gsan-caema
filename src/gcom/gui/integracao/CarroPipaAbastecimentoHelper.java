package gcom.gui.integracao;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarroPipaAbastecimentoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idImovel;
	private Integer sequencial;
	private String descricaoPlaca;
	private Integer volumeAbastecimento;
	private String dataHoraAbastecimento;
	private Short indicadorCobranca;
	private Short indicadorAbastecimentoAvulso;
	private Integer retornoErro;
	
	public CarroPipaAbastecimentoHelper(){}
	
	public CarroPipaAbastecimentoHelper(Integer idImovel,Integer sequencial,
			String descricaoPlaca,Integer volumeAbastecimento,String dataHoraAbastecimento,
			Short indicadorCobranca,Short indicadorAbastecimentoAvulso,Integer retornoErro){
		this.idImovel = idImovel;
		this.sequencial = sequencial;
		this.descricaoPlaca = descricaoPlaca;
		this.volumeAbastecimento = volumeAbastecimento;
		this.dataHoraAbastecimento = dataHoraAbastecimento;
		this.indicadorCobranca = indicadorCobranca;
		this.indicadorAbastecimentoAvulso = indicadorAbastecimentoAvulso;
		this.retornoErro = retornoErro;
	}

	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public String getDescricaoPlaca() {
		return descricaoPlaca;
	}
	public void setDescricaoPlaca(String descricaoPlaca) {
		this.descricaoPlaca = descricaoPlaca;
	}
	public Integer getVolumeAbastecimento() {
		return volumeAbastecimento;
	}
	public void setVolumeAbastecimento(Integer volumeAbastecimento) {
		this.volumeAbastecimento = volumeAbastecimento;
	}
	public String getDataHoraAbastecimento() {
		return dataHoraAbastecimento;
	}
	public void setDataHoraAbastecimento(String dataHoraAbastecimento) {
		this.dataHoraAbastecimento = dataHoraAbastecimento;
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
	public Integer getRetornoErro() {
		return retornoErro;
	}
	public void setRetornoErro(Integer retornoErro) {
		this.retornoErro = retornoErro;
	}
}
