package gcom.relatorio.faturamento;

import java.io.Serializable;

/**
 * [UC 1293] - Helper para o Relatorio Quantitativo de Contas Reimpressas
 * 
 * @author Davi Menezes
 * @date 08/03/2012
 *
 */
public class RelatorioQuantitativoContasReimpressasHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer anoMesReferencia;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidade;
	private String setorComercial;
	private String rota;
	private String empresa;
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
}
