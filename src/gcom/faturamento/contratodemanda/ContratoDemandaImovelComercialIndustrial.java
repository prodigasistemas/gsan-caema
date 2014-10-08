package gcom.faturamento.contratodemanda;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 0512] - Inserir Contrato de Demanda
 * 
 * @author Davi Menezes
 * @date 19/04/2013
 *
 */
public class ContratoDemandaImovelComercialIndustrial extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ContratoDemandaImovelComercialIndustrialPK comp_id;
	
	private Integer volumeInformadoEsgotoRateado;
	
	private Short indicadorPrincipal;
	
	private Date dataContratoEncerrado;
	
	private Date ultimaAlteracao;

	public ContratoDemandaImovelComercialIndustrialPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ContratoDemandaImovelComercialIndustrialPK comp_id) {
		this.comp_id = comp_id;
	}

	public Integer getVolumeInformadoEsgotoRateado() {
		return volumeInformadoEsgotoRateado;
	}

	public void setVolumeInformadoEsgotoRateado(Integer volumeInformadoEsgotoRateado) {
		this.volumeInformadoEsgotoRateado = volumeInformadoEsgotoRateado;
	}
	
	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}
	
	public Date getDataContratoEncerrado() {
		return dataContratoEncerrado;
	}

	public void setDataContratoEncerrado(Date dataContratoEncerrado) {
		this.dataContratoEncerrado = dataContratoEncerrado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroContratoDemandaImovelComercialIndustrial filtroContratoDemandaImovel = new FiltroContratoDemandaImovelComercialIndustrial();
		filtroContratoDemandaImovel.adicionarParametro(new ParametroSimples(FiltroContratoDemandaImovelComercialIndustrial.
				ID_CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL, this.getComp_id().getContratoDemandaComercialIndustrial().getId()));
		
		return filtroContratoDemandaImovel;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
}
