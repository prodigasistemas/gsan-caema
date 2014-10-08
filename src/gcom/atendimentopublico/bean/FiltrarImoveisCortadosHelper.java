package gcom.atendimentopublico.bean;

import java.io.Serializable;


public class FiltrarImoveisCortadosHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Parametros na pesquisa
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String localidadeInicial;
	private String setorComercialInicial;
	private String quadraInicial;
	private String localidadeFinal;
	private String setorComercialFinal;
	private String quadraFinal;
	private String idMotivoCorte;
	private String dataCorteInicial;
	private String dataCorteFinal;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String indicadorGerarOSFiscalizacao;
	
	//Parametros de retorno da pesquisa
	private String matricula;
	private String categoria;
	private String imovelPerfil;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String valorDebito;
	
	
	//Parametros de retorno da pesquisa no relatorio de imoves cortados
	private String descricaoGerencia;
	private String idGerencia ;	
	private String descricaoLocalidade ;
	private String idLocalidade ;
	private String descricaoUnidadeNegocio ;
	private String idUnidadeNegocio ;
	private String motivoCorte;
	private String dataCorte;
	private String qtdDebitosAntesCorte;
	private String valorDebitosAntesCorte;
	private String qtdDebitosAposCorte;
	private String valorDebitosAposCorte;
	private String qtdPagamentosAposCorte;
	private String valorPagamentosAposCorte;
	/**
	 * @return the unidadeNegocio
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	/**
	 * @param unidadeNegocio the unidadeNegocio to set
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	/**
	 * @return the gerenciaRegional
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	/**
	 * @param gerenciaRegional the gerenciaRegional to set
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	/**
	 * @return the localidadeInicial
	 */
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}
	/**
	 * @param localidadeInicial the localidadeInicial to set
	 */
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	/**
	 * @return the setorComercialInicial
	 */
	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}
	/**
	 * @param setorComercialInicial the setorComercialInicial to set
	 */
	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	/**
	 * @return the quadraInicial
	 */
	public String getQuadraInicial() {
		return quadraInicial;
	}
	/**
	 * @param quadraInicial the quadraInicial to set
	 */
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}
	/**
	 * @return the localidadeFinal
	 */
	public String getLocalidadeFinal() {
		return localidadeFinal;
	}
	/**
	 * @param localidadeFinal the localidadeFinal to set
	 */
	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}
	/**
	 * @return the setorComercialFinal
	 */
	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}
	/**
	 * @param setorComercialFinal the setorComercialFinal to set
	 */
	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	/**
	 * @return the quadraFinal
	 */
	public String getQuadraFinal() {
		return quadraFinal;
	}
	/**
	 * @param quadraFinal the quadraFinal to set
	 */
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	/**
	 * @return the idMotivoCorte
	 */
	public String getIdMotivoCorte() {
		return idMotivoCorte;
	}
	/**
	 * @param idMotivoCorte the idMotivoCorte to set
	 */
	public void setIdMotivoCorte(String idMotivoCorte) {
		this.idMotivoCorte = idMotivoCorte;
	}
	/**
	 * @return the dataCorteInicial
	 */
	public String getDataCorteInicial() {
		return dataCorteInicial;
	}
	/**
	 * @param dataCorteInicial the dataCorteInicial to set
	 */
	public void setDataCorteInicial(String dataCorteInicial) {
		this.dataCorteInicial = dataCorteInicial;
	}
	/**
	 * @return the dataCorteFinal
	 */
	public String getDataCorteFinal() {
		return dataCorteFinal;
	}
	/**
	 * @param dataCorteFinal the dataCorteFinal to set
	 */
	public void setDataCorteFinal(String dataCorteFinal) {
		this.dataCorteFinal = dataCorteFinal;
	}
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return the imovelPerfil
	 */
	public String getImovelPerfil() {
		return imovelPerfil;
	}
	/**
	 * @param imovelPerfil the imovelPerfil to set
	 */
	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	/**
	 * @return the situacaoAgua
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	/**
	 * @param situacaoAgua the situacaoAgua to set
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	/**
	 * @return the situacaoEsgoto
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	/**
	 * @param situacaoEsgoto the situacaoEsgoto to set
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	/**
	 * @return the valorDebito
	 */
	public String getValorDebito() {
		return valorDebito;
	}
	/**
	 * @param valorDebito the valorDebito to set
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	/**
	 * @return the valorDebitoInicial
	 */
	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}
	/**
	 * @param valorDebitoInicial the valorDebitoInicial to set
	 */
	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}
	/**
	 * @return the valorDebitoFinal
	 */
	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}
	/**
	 * @param valorDebitoFinal the valorDebitoFinal to set
	 */
	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}
	/**
	 * @return the indicadorGerarOSFiscalizacao
	 */
	public String getIndicadorGerarOSFiscalizacao() {
		return indicadorGerarOSFiscalizacao;
	}
	/**
	 * @param indicadorGerarOSFiscalizacao the indicadorGerarOSFiscalizacao to set
	 */
	public void setIndicadorGerarOSFiscalizacao(String indicadorGerarOSFiscalizacao) {
		this.indicadorGerarOSFiscalizacao = indicadorGerarOSFiscalizacao;
	}
	/**
	 * @return the descricaoGerencia
	 */
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	/**
	 * @param descricaoGerencia the descricaoGerencia to set
	 */
	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}
	/**
	 * @return the idGerencia
	 */
	public String getIdGerencia() {
		return idGerencia;
	}
	/**
	 * @param idGerencia the idGerencia to set
	 */
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	/**
	 * @return the descricaoLocalidade
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade the descricaoLocalidade to set
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return the idLocalidade
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade the idLocalidade to set
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return the descricaoUnidadeNegocio
	 */
	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}
	/**
	 * @param descricaoUnidadeNegocio the descricaoUnidadeNegocio to set
	 */
	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}
	/**
	 * @return the idUnidadeNegocio
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	/**
	 * @param idUnidadeNegocio the idUnidadeNegocio to set
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	/**
	 * @return the motivoCorte
	 */
	public String getMotivoCorte() {
		return motivoCorte;
	}
	/**
	 * @param motivoCorte the motivoCorte to set
	 */
	public void setMotivoCorte(String motivoCorte) {
		this.motivoCorte = motivoCorte;
	}
	/**
	 * @return the dataCorte
	 */
	public String getDataCorte() {
		return dataCorte;
	}
	/**
	 * @param dataCorte the dataCorte to set
	 */
	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}
	/**
	 * @return the qtdDebitosAntesCorte
	 */
	public String getQtdDebitosAntesCorte() {
		return qtdDebitosAntesCorte;
	}
	/**
	 * @param qtdDebitosAntesCorte the qtdDebitosAntesCorte to set
	 */
	public void setQtdDebitosAntesCorte(String qtdDebitosAntesCorte) {
		this.qtdDebitosAntesCorte = qtdDebitosAntesCorte;
	}
	/**
	 * @return the valorDebitosAntesCorte
	 */
	public String getValorDebitosAntesCorte() {
		return valorDebitosAntesCorte;
	}
	/**
	 * @param valorDebitosAntesCorte the valorDebitosAntesCorte to set
	 */
	public void setValorDebitosAntesCorte(String valorDebitosAntesCorte) {
		this.valorDebitosAntesCorte = valorDebitosAntesCorte;
	}
	/**
	 * @return the qtdDebitosAposCorte
	 */
	public String getQtdDebitosAposCorte() {
		return qtdDebitosAposCorte;
	}
	/**
	 * @param qtdDebitosAposCorte the qtdDebitosAposCorte to set
	 */
	public void setQtdDebitosAposCorte(String qtdDebitosAposCorte) {
		this.qtdDebitosAposCorte = qtdDebitosAposCorte;
	}
	/**
	 * @return the valorDebitosAposCorte
	 */
	public String getValorDebitosAposCorte() {
		return valorDebitosAposCorte;
	}
	/**
	 * @param valorDebitosAposCorte the valorDebitosAposCorte to set
	 */
	public void setValorDebitosAposCorte(String valorDebitosAposCorte) {
		this.valorDebitosAposCorte = valorDebitosAposCorte;
	}
	/**
	 * @return the qtdPagamentosAposCorte
	 */
	public String getQtdPagamentosAposCorte() {
		return qtdPagamentosAposCorte;
	}
	/**
	 * @param qtdPagamentosAposCorte the qtdPagamentosAposCorte to set
	 */
	public void setQtdPagamentosAposCorte(String qtdPagamentosAposCorte) {
		this.qtdPagamentosAposCorte = qtdPagamentosAposCorte;
	}
	/**
	 * @return the valorPagamentosAposCorte
	 */
	public String getValorPagamentosAposCorte() {
		return valorPagamentosAposCorte;
	}
	/**
	 * @param valorPagamentosAposCorte the valorPagamentosAposCorte to set
	 */
	public void setValorPagamentosAposCorte(String valorPagamentosAposCorte) {
		this.valorPagamentosAposCorte = valorPagamentosAposCorte;
	}


}
