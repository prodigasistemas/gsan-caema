package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * [UC1598] Gerar Relatório Cobrança Por Resultado Comando/Mês
 * 
 * @author Ana Maria
 * @date 28/04/2014
 */
public class GerarRelatorioCobrancaPorResultadoComandoMesActionForm extends ActionForm{
    

	private static final long serialVersionUID = 1L;
	
	
	private String idEmpresaContratada;
	private String periodoComandoInicial;
	private String periodoComandoFinal;
	private String idComando;
	private String[] idsCategoria;
	private String periodoApuracao;
	private String encerramentoArrecadacao;
	private String[] idsGerenciaRegional;
	private String[] idsUnidadeNegocio;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String[] idsRegiao;
	private String[] idsMicroregiao;
	private String[] idsMunicipio;
	
	public String[] getIdsCategoria() {
		return idsCategoria;
	}
	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}
	public String getIdEmpresaContratada() {
		return idEmpresaContratada;
	}
	public void setIdEmpresaContratada(String idEmpresaContratada) {
		this.idEmpresaContratada = idEmpresaContratada;
	}
	public String getPeriodoApuracao() {
		return periodoApuracao;
	}
	public void setPeriodoApuracao(String periodoApuracao) {
		this.periodoApuracao = periodoApuracao;
	}
	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}
	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}
	public String[] getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}
	public void setIdsUnidadeNegocio(String[] idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String[] getIdsRegiao() {
		return idsRegiao;
	}
	public void setIdsRegiao(String[] idsRegiao) {
		this.idsRegiao = idsRegiao;
	}
	public String[] getIdsMicroregiao() {
		return idsMicroregiao;
	}
	public void setIdsMicroregiao(String[] idsMicroregiao) {
		this.idsMicroregiao = idsMicroregiao;
	}
	public String[] getIdsMunicipio() {
		return idsMunicipio;
	}
	public void setIdsMunicipio(String[] idsMunicipio) {
		this.idsMunicipio = idsMunicipio;
	}
	public String getEncerramentoArrecadacao() {
		return encerramentoArrecadacao;
	}
	public void setEncerramentoArrecadacao(String encerramentoArrecadacao) {
		this.encerramentoArrecadacao = encerramentoArrecadacao;
	}
	public String getPeriodoComandoInicial() {
		return periodoComandoInicial;
	}
	public void setPeriodoComandoInicial(String periodoComandoInicial) {
		this.periodoComandoInicial = periodoComandoInicial;
	}
	public String getPeriodoComandoFinal() {
		return periodoComandoFinal;
	}
	public void setPeriodoComandoFinal(String periodoComandoFinal) {
		this.periodoComandoFinal = periodoComandoFinal;
	}
	public String getIdComando() {
		return idComando;
	}
	public void setIdComando(String idComando) {
		this.idComando = idComando;
	}
    
	
}
