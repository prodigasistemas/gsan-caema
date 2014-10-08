package gcom.gui.relatorio.micromedicao;

import java.io.Serializable;

public class RelatorioAnormalidadeImoveisCorrigidosHelper implements Serializable {
	
	/**
	 * [UC1434] Gerar Relatório Anormalidade de Imóveis Corrigidos.
	 * 
	 * @author Jonathan Marcos
	 *
	 * @date 06/02/2013
	 */

	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	
	private String anormalidadeAnterior;
	private String dataCorrecaoAnormalidadeAnterior;
	
	private String anormalidadeAtual;
	private String dataLeituraAnormalidadeAtual;

	private String nomeGerenciaRegional;
	
	private String nomeUnidadeNegocio;
	
	private String nomeLocalidade;
	
	public RelatorioAnormalidadeImoveisCorrigidosHelper(){}

	
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}


	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}


	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}


	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}


	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getAnormalidadeAnterior() {
		return anormalidadeAnterior;
	}

	public void setAnormalidadeAnterior(String anormalidadeAnterior) {
		this.anormalidadeAnterior = anormalidadeAnterior;
	}

	public String getDataCorrecaoAnormalidadeAnterior() {
		return dataCorrecaoAnormalidadeAnterior;
	}

	public void setDataCorrecaoAnormalidadeAnterior(String dataCorrecaoAnormalidadeAnterior) {
		this.dataCorrecaoAnormalidadeAnterior = dataCorrecaoAnormalidadeAnterior;
	}

	public String getAnormalidadeAtual() {
		return anormalidadeAtual;
	}

	public void setAnormalidadeAtual(String anormalidadeAtual) {
		this.anormalidadeAtual = anormalidadeAtual;
	}

	public String getDataLeituraAnormalidadeAtual() {
		return dataLeituraAnormalidadeAtual;
	}

	public void setDataLeituraAnormalidadeAtual(String dataLeituraAnormalidadeAtual) {
		this.dataLeituraAnormalidadeAtual = dataLeituraAnormalidadeAtual;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
