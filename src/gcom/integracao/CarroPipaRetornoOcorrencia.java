package gcom.integracao;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jonathan Marcos
 * @date 17/10/2013
 * [UC1567] Consultar Débitos Imóvel Via Webservice
 * [Observacao] 
 * 		1 - Classe Basica
 */
public class CarroPipaRetornoOcorrencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CarroPipaRetornoTipo carroPipaRetornoTipo;
	private Short codigoRequisicao;
	private Imovel imovel;
	private String numeroProtocoloConsulta;
	private Date dataOcorrencia;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CarroPipaRetornoTipo getCarroPipaRetornoTipo() {
		return carroPipaRetornoTipo;
	}
	public void setCarroPipaRetornoTipo(CarroPipaRetornoTipo carroPipaRetornoTipo) {
		this.carroPipaRetornoTipo = carroPipaRetornoTipo;
	}
	public Short getCodigoRequisicao() {
		return codigoRequisicao;
	}
	public void setCodigoRequisicao(Short codigoRequisicao) {
		this.codigoRequisicao = codigoRequisicao;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public String getNumeroProtocoloConsulta() {
		return numeroProtocoloConsulta;
	}
	public void setNumeroProtocoloConsulta(String numeroProtocoloConsulta) {
		this.numeroProtocoloConsulta = numeroProtocoloConsulta;
	}
	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
