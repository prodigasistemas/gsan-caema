package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcImovel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer atcImoId;

    /** persistent field */
    private int atcSetId;

    /** persistent field */
    private int atcQuaId;

    /** nullable persistent field */
    private String atcImoLote;

    /** nullable persistent field */
    private String atcImoSublote;

    /** nullable persistent field */
    private Integer atcBaiId;

    /** nullable persistent field */
    private Integer atcLgrId;

    /** nullable persistent field */
    private String atcImoNumero;

    /** nullable persistent field */
    private String atcImoReferencia;

    /** nullable persistent field */
    private String atcImoComplemento;

    /** nullable persistent field */
    private Integer atcPvcId;

    /** nullable persistent field */
    private Integer atcPvrId;

    /** nullable persistent field */
    private Integer atcFabId;

    /** nullable persistent field */
    private Integer atcSlaId;

    /** nullable persistent field */
    private Integer atcSleId;

    /** nullable persistent field */
    private Integer atcIpfId;

    /** nullable persistent field */
    private Boolean atcImoAnalizarTarifaSocial;

    /** nullable persistent field */
    private String atcImoContratoEnergia;

    /** nullable persistent field */
    private String atcImoNumeroMedidorEnergia;

    /** nullable persistent field */
    private Integer atcAcfId;

    /** nullable persistent field */
    private Boolean atcImoTemPoco;

    /** nullable persistent field */
    private String localidade;

    /** nullable persistent field */
    private String setor;

    /** nullable persistent field */
    private String quadra;

    /** nullable persistent field */
    private String logradouro;

    /** nullable persistent field */
    private Integer atcLocId;
    
    private Integer idParametroTabelaAtualizacaoCadastral;

    /** full constructor */
    public AtcImovel(Integer atcImoId, int atcSetId, int atcQuaId, String atcImoLote, String atcImoSublote, Integer atcBaiId, Integer atcLgrId, String atcImoNumero, String atcImoReferencia, String atcImoComplemento, Integer atcPvcId, Integer atcPvrId, Integer atcFabId, Integer atcSlaId, Integer atcSleId, Integer atcIpfId, Boolean atcImoAnalizarTarifaSocial, String atcImoContratoEnergia, String atcImoNumeroMedidorEnergia, Integer atcAcfId, Boolean atcImoTemPoco, String localidade, String setor, String quadra, String logradouro, Integer atcLocId) {
        this.atcImoId = atcImoId;
        this.atcSetId = atcSetId;
        this.atcQuaId = atcQuaId;
        this.atcImoLote = atcImoLote;
        this.atcImoSublote = atcImoSublote;
        this.atcBaiId = atcBaiId;
        this.atcLgrId = atcLgrId;
        this.atcImoNumero = atcImoNumero;
        this.atcImoReferencia = atcImoReferencia;
        this.atcImoComplemento = atcImoComplemento;
        this.atcPvcId = atcPvcId;
        this.atcPvrId = atcPvrId;
        this.atcFabId = atcFabId;
        this.atcSlaId = atcSlaId;
        this.atcSleId = atcSleId;
        this.atcIpfId = atcIpfId;
        this.atcImoAnalizarTarifaSocial = atcImoAnalizarTarifaSocial;
        this.atcImoContratoEnergia = atcImoContratoEnergia;
        this.atcImoNumeroMedidorEnergia = atcImoNumeroMedidorEnergia;
        this.atcAcfId = atcAcfId;
        this.atcImoTemPoco = atcImoTemPoco;
        this.localidade = localidade;
        this.setor = setor;
        this.quadra = quadra;
        this.logradouro = logradouro;
        this.atcLocId = atcLocId;
    }

    /** default constructor */
    public AtcImovel() {
    }

    /** minimal constructor */
    public AtcImovel(Integer atcImoId, int atcSetId, int atcQuaId) {
        this.atcImoId = atcImoId;
        this.atcSetId = atcSetId;
        this.atcQuaId = atcQuaId;
    }

    public Integer getAtcImoId() {
        return this.atcImoId;
    }

    public void setAtcImoId(Integer atcImoId) {
        this.atcImoId = atcImoId;
    }

    public int getAtcSetId() {
        return this.atcSetId;
    }

    public void setAtcSetId(int atcSetId) {
        this.atcSetId = atcSetId;
    }

    public int getAtcQuaId() {
        return this.atcQuaId;
    }

    public void setAtcQuaId(int atcQuaId) {
        this.atcQuaId = atcQuaId;
    }

    public String getAtcImoLote() {
        return this.atcImoLote;
    }

    public void setAtcImoLote(String atcImoLote) {
        this.atcImoLote = atcImoLote;
    }

    public String getAtcImoSublote() {
        return this.atcImoSublote;
    }

    public void setAtcImoSublote(String atcImoSublote) {
        this.atcImoSublote = atcImoSublote;
    }

    public Integer getAtcBaiId() {
        return this.atcBaiId;
    }

    public void setAtcBaiId(Integer atcBaiId) {
        this.atcBaiId = atcBaiId;
    }

    public Integer getAtcLgrId() {
        return this.atcLgrId;
    }

    public void setAtcLgrId(Integer atcLgrId) {
        this.atcLgrId = atcLgrId;
    }

    public String getAtcImoNumero() {
        return this.atcImoNumero;
    }

    public void setAtcImoNumero(String atcImoNumero) {
        this.atcImoNumero = atcImoNumero;
    }

    public String getAtcImoReferencia() {
        return this.atcImoReferencia;
    }

    public void setAtcImoReferencia(String atcImoReferencia) {
        this.atcImoReferencia = atcImoReferencia;
    }

    public String getAtcImoComplemento() {
        return this.atcImoComplemento;
    }

    public void setAtcImoComplemento(String atcImoComplemento) {
        this.atcImoComplemento = atcImoComplemento;
    }

    public Integer getAtcPvcId() {
        return this.atcPvcId;
    }

    public void setAtcPvcId(Integer atcPvcId) {
        this.atcPvcId = atcPvcId;
    }

    public Integer getAtcPvrId() {
        return this.atcPvrId;
    }

    public void setAtcPvrId(Integer atcPvrId) {
        this.atcPvrId = atcPvrId;
    }

    public Integer getAtcFabId() {
        return this.atcFabId;
    }

    public void setAtcFabId(Integer atcFabId) {
        this.atcFabId = atcFabId;
    }

    public Integer getAtcSlaId() {
        return this.atcSlaId;
    }

    public void setAtcSlaId(Integer atcSlaId) {
        this.atcSlaId = atcSlaId;
    }

    public Integer getAtcSleId() {
        return this.atcSleId;
    }

    public void setAtcSleId(Integer atcSleId) {
        this.atcSleId = atcSleId;
    }

    public Integer getAtcIpfId() {
        return this.atcIpfId;
    }

    public void setAtcIpfId(Integer atcIpfId) {
        this.atcIpfId = atcIpfId;
    }

    public Boolean getAtcImoAnalizarTarifaSocial() {
        return this.atcImoAnalizarTarifaSocial;
    }

    public void setAtcImoAnalizarTarifaSocial(Boolean atcImoAnalizarTarifaSocial) {
        this.atcImoAnalizarTarifaSocial = atcImoAnalizarTarifaSocial;
    }

    public String getAtcImoContratoEnergia() {
        return this.atcImoContratoEnergia;
    }

    public void setAtcImoContratoEnergia(String atcImoContratoEnergia) {
        this.atcImoContratoEnergia = atcImoContratoEnergia;
    }

    public String getAtcImoNumeroMedidorEnergia() {
        return this.atcImoNumeroMedidorEnergia;
    }

    public void setAtcImoNumeroMedidorEnergia(String atcImoNumeroMedidorEnergia) {
        this.atcImoNumeroMedidorEnergia = atcImoNumeroMedidorEnergia;
    }

    public Integer getAtcAcfId() {
        return this.atcAcfId;
    }

    public void setAtcAcfId(Integer atcAcfId) {
        this.atcAcfId = atcAcfId;
    }

    public Boolean getAtcImoTemPoco() {
        return this.atcImoTemPoco;
    }

    public void setAtcImoTemPoco(Boolean atcImoTemPoco) {
        this.atcImoTemPoco = atcImoTemPoco;
    }

    public String getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getSetor() {
        return this.setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getQuadra() {
        return this.quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getAtcLocId() {
        return this.atcLocId;
    }

    public void setAtcLocId(Integer atcLocId) {
        this.atcLocId = atcLocId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("atcImoId", getAtcImoId())
            .toString();
    }

	public Integer getIdParametroTabelaAtualizacaoCadastral() {
		return idParametroTabelaAtualizacaoCadastral;
	}

	public void setIdParametroTabelaAtualizacaoCadastral(
			Integer idParametroTabelaAtualizacaoCadastral) {
		this.idParametroTabelaAtualizacaoCadastral = idParametroTabelaAtualizacaoCadastral;
	}

}
