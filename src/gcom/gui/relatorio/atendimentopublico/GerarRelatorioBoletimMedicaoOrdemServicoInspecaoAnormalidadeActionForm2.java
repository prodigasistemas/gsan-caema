package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * [UC1221] – Gerar Boletim de Medição Ordem de Serviço Inspeção Anormalidade
 * 
 * @since 19/08/2011
 * @author Diogo Peixoto
 *
 */
public class GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 extends ActionForm {

	private static final long serialVersionUID = 1L;

	private List<ComandoOrdemSeletiva> comandosOS;
	private String periodoApuracao;
	
	private String idGerencia;
	private String idUnidadeNegocio;
	private String idLocalidade;
	private String nomeLocalidade;
	
	private String idRegiao;
	private String idMicroregiao;
	private String idMunicipio;
	private String opcaoRelatorio;
	
	public List<ComandoOrdemSeletiva> getComandosOS() {
		return comandosOS;
	}
	public void setComandosOS(List<ComandoOrdemSeletiva> comandosOS) {
		this.comandosOS = comandosOS;
	}
	public String getPeriodoApuracao() {
		return periodoApuracao;
	}
	public void setPeriodoApuracao(String periodoApuracao) {
		this.periodoApuracao = periodoApuracao;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getIdRegiao() {
		return idRegiao;
	}
	public void setIdRegiao(String idRegiao) {
		this.idRegiao = idRegiao;
	}
	public String getIdMicroregiao() {
		return idMicroregiao;
	}
	public void setIdMicroregiao(String idMicroregiao) {
		this.idMicroregiao = idMicroregiao;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
}