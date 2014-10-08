package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcImovelRetorno implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.cadastro.atualizacaocadastral.AtcImovelRetornoPK comp_id;

    /** nullable persistent field */
    private String atcImoLote;

    /** nullable persistent field */
    private String atcImoSublote;

    /** nullable persistent field */
    private Integer atcBaiId;

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
    private String atcImoRota;

    /** nullable persistent field */
    private Integer acaAtuId;

    /** nullable persistent field */
    private String acaImoObs;

    /** nullable persistent field */
    private String localidade;

    /** nullable persistent field */
    private String setor;

    /** nullable persistent field */
    private String quadra;

    /** nullable persistent field */
    private String logradouro;

    /** nullable persistent field */
    private Integer atcNqdId;

    /** nullable persistent field */
    private Integer atcNstId;

    /** nullable persistent field */
    private Integer atcNlgId;

    /** nullable persistent field */
    private Integer atcImoSitCad;

    /** nullable persistent field */
    private String atcImoNovoLote;

    /** nullable persistent field */
    private String atcImoNovoSublote;

    /** persistent field */
    private Date acaImoDataHoraAtualizacao;

    /** nullable persistent field */
    private String atcImoIdRef;

    /** nullable persistent field */
    private byte[] acaImoFachada;

    /** nullable persistent field */
    private byte[] acaImoHidrometro;

    /** nullable persistent field */
    private Boolean acaImoDefinitivo;

    /** nullable persistent field */
    private Date acaImoDataHoraImportacao;

    /** nullable persistent field */
    private Integer atcLocId;

    private Integer usurCodigo;
    
    private Integer idParametroAtualizacaoCadastral;
    
    /** full constructor */
    public AtcImovelRetorno(gcom.cadastro.atualizacaocadastral.AtcImovelRetornoPK comp_id, String atcImoLote, String atcImoSublote, Integer atcBaiId, String atcImoNumero, String atcImoReferencia, String atcImoComplemento, Integer atcPvcId, Integer atcPvrId, Integer atcFabId, Integer atcSlaId, Integer atcSleId, Integer atcIpfId, Boolean atcImoAnalizarTarifaSocial, String atcImoContratoEnergia, String atcImoNumeroMedidorEnergia, Integer atcAcfId, Boolean atcImoTemPoco, String atcImoRota, Integer acaAtuId, String acaImoObs, String localidade, String setor, String quadra, String logradouro, Integer atcNqdId, Integer atcNstId, Integer atcNlgId, Integer atcImoSitCad, String atcImoNovoLote, String atcImoNovoSublote, Date acaImoDataHoraAtualizacao, String atcImoIdRef, byte[] acaImoFachada, byte[] acaImoHidrometro, Boolean acaImoDefinitivo, Date acaImoDataHoraImportacao, Integer atcLocId) {
        this.comp_id = comp_id;
        this.atcImoLote = atcImoLote;
        this.atcImoSublote = atcImoSublote;
        this.atcBaiId = atcBaiId;
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
        this.atcImoRota = atcImoRota;
        this.acaAtuId = acaAtuId;
        this.acaImoObs = acaImoObs;
        this.localidade = localidade;
        this.setor = setor;
        this.quadra = quadra;
        this.logradouro = logradouro;
        this.atcNqdId = atcNqdId;
        this.atcNstId = atcNstId;
        this.atcNlgId = atcNlgId;
        this.atcImoSitCad = atcImoSitCad;
        this.atcImoNovoLote = atcImoNovoLote;
        this.atcImoNovoSublote = atcImoNovoSublote;
        this.acaImoDataHoraAtualizacao = acaImoDataHoraAtualizacao;
        this.atcImoIdRef = atcImoIdRef;
        this.acaImoFachada = acaImoFachada;
        this.acaImoHidrometro = acaImoHidrometro;
        this.acaImoDefinitivo = acaImoDefinitivo;
        this.acaImoDataHoraImportacao = acaImoDataHoraImportacao;
        this.atcLocId = atcLocId;
    }

    /** default constructor */
    public AtcImovelRetorno() {
    }

    /** minimal constructor */
    public AtcImovelRetorno(gcom.cadastro.atualizacaocadastral.AtcImovelRetornoPK comp_id, Date acaImoDataHoraAtualizacao) {
        this.comp_id = comp_id;
        this.acaImoDataHoraAtualizacao = acaImoDataHoraAtualizacao;
    }

    public gcom.cadastro.atualizacaocadastral.AtcImovelRetornoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.cadastro.atualizacaocadastral.AtcImovelRetornoPK comp_id) {
        this.comp_id = comp_id;
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

    public String getAtcImoRota() {
        return this.atcImoRota;
    }

    public void setAtcImoRota(String atcImoRota) {
        this.atcImoRota = atcImoRota;
    }

    public Integer getAcaAtuId() {
        return this.acaAtuId;
    }

    public void setAcaAtuId(Integer acaAtuId) {
        this.acaAtuId = acaAtuId;
    }

    public String getAcaImoObs() {
        return this.acaImoObs;
    }

    public void setAcaImoObs(String acaImoObs) {
        this.acaImoObs = acaImoObs;
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

    public Integer getAtcNqdId() {
        return this.atcNqdId;
    }

    public void setAtcNqdId(Integer atcNqdId) {
        this.atcNqdId = atcNqdId;
    }

    public Integer getAtcNstId() {
        return this.atcNstId;
    }

    public void setAtcNstId(Integer atcNstId) {
        this.atcNstId = atcNstId;
    }

    public Integer getAtcNlgId() {
        return this.atcNlgId;
    }

    public void setAtcNlgId(Integer atcNlgId) {
        this.atcNlgId = atcNlgId;
    }

    public Integer getAtcImoSitCad() {
        return this.atcImoSitCad;
    }

    public void setAtcImoSitCad(Integer atcImoSitCad) {
        this.atcImoSitCad = atcImoSitCad;
    }

    public String getAtcImoNovoLote() {
        return this.atcImoNovoLote;
    }

    public void setAtcImoNovoLote(String atcImoNovoLote) {
        this.atcImoNovoLote = atcImoNovoLote;
    }

    public String getAtcImoNovoSublote() {
        return this.atcImoNovoSublote;
    }

    public void setAtcImoNovoSublote(String atcImoNovoSublote) {
        this.atcImoNovoSublote = atcImoNovoSublote;
    }

    public Date getAcaImoDataHoraAtualizacao() {
        return this.acaImoDataHoraAtualizacao;
    }

    public void setAcaImoDataHoraAtualizacao(Date acaImoDataHoraAtualizacao) {
        this.acaImoDataHoraAtualizacao = acaImoDataHoraAtualizacao;
    }

    public String getAtcImoIdRef() {
        return this.atcImoIdRef;
    }

    public void setAtcImoIdRef(String atcImoIdRef) {
        this.atcImoIdRef = atcImoIdRef;
    }

    public byte[] getAcaImoFachada() {
        return this.acaImoFachada;
    }

    public void setAcaImoFachada(byte[] acaImoFachada) {
        this.acaImoFachada = acaImoFachada;
    }

    public byte[] getAcaImoHidrometro() {
        return this.acaImoHidrometro;
    }

    public void setAcaImoHidrometro(byte[] acaImoHidrometro) {
        this.acaImoHidrometro = acaImoHidrometro;
    }

    public Boolean getAcaImoDefinitivo() {
        return this.acaImoDefinitivo;
    }

    public void setAcaImoDefinitivo(Boolean acaImoDefinitivo) {
        this.acaImoDefinitivo = acaImoDefinitivo;
    }

    public Date getAcaImoDataHoraImportacao() {
        return this.acaImoDataHoraImportacao;
    }

    public void setAcaImoDataHoraImportacao(Date acaImoDataHoraImportacao) {
        this.acaImoDataHoraImportacao = acaImoDataHoraImportacao;
    }

    public Integer getAtcLocId() {
        return this.atcLocId;
    }

    public void setAtcLocId(Integer atcLocId) {
        this.atcLocId = atcLocId;
    }

	public Integer getUsurCodigo() {
		return usurCodigo;
	}

	public void setUsurCodigo(Integer usurCodigo) {
		this.usurCodigo = usurCodigo;
	}

	public Integer getIdParametroAtualizacaoCadastral() {
		return idParametroAtualizacaoCadastral;
	}

	public void setIdParametroAtualizacaoCadastral(
			Integer idParametroAtualizacaoCadastral) {
		this.idParametroAtualizacaoCadastral = idParametroAtualizacaoCadastral;
	}

}
