package gcom.relatorio.faturamento;

import java.io.Serializable;
import java.util.Collection;

public class FiltrarRelatorioSituacaoEspecialFaturamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer gerenciaRegional;
	private Integer unidadeNegocio;
	
	private Integer localidadeInicial;
	private Integer setorComercialInicial;

	private Integer localidadeFinal;
	private Integer setorComercialFinal;

	private Collection<Integer> situacao;
	private Collection<Integer> motivo;
	
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}
	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}
	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}
	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}
	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}
	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	public Collection<Integer> getSituacao() {
		return situacao;
	}
	public void setSituacao(Collection<Integer> situacao) {
		this.situacao = situacao;
	}
	public Collection<Integer> getMotivo() {
		return motivo;
	}
	public void setMotivo(Collection<Integer> motivo) {
		this.motivo = motivo;
	}
	
}
