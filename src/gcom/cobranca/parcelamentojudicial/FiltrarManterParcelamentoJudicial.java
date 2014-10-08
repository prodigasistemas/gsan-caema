package gcom.cobranca.parcelamentojudicial;

import java.io.Serializable;

public class FiltrarManterParcelamentoJudicial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idParcelamentoJudicial;
	private String codigoClienteResponsavel;
	private String nomeClienteResponsavel;
	private String codigoClienteUsuario;
	private String nomeClienteUsuario;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String processoJudicial;
	private String periodoInicialParcelamento;
	private String periodoFinalParcelamento;
	private String numeroPaginaPesquisa;
	
	public FiltrarManterParcelamentoJudicial(){}
	
	public String getIdParcelamentoJudicial() {
		return idParcelamentoJudicial;
	}
	public void setIdParcelamentoJudicial(String idParcelamentoJudicial) {
		this.idParcelamentoJudicial = idParcelamentoJudicial;
	}
	public String getCodigoClienteResponsavel() {
		return codigoClienteResponsavel;
	}
	public void setCodigoClienteResponsavel(String codigoClienteResponsavel) {
		this.codigoClienteResponsavel = codigoClienteResponsavel;
	}
	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}
	public String getCodigoClienteUsuario() {
		return codigoClienteUsuario;
	}
	public void setCodigoClienteUsuario(String codigoClienteUsuario) {
		this.codigoClienteUsuario = codigoClienteUsuario;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getProcessoJudicial() {
		return processoJudicial;
	}
	public void setProcessoJudicial(String processoJudicial) {
		this.processoJudicial = processoJudicial;
	}
	public String getPeriodoInicialParcelamento() {
		return periodoInicialParcelamento;
	}
	public void setPeriodoInicialParcelamento(String periodoInicialParcelamento) {
		this.periodoInicialParcelamento = periodoInicialParcelamento;
	}
	public String getPeriodoFinalParcelamento() {
		return periodoFinalParcelamento;
	}
	public void setPeriodoFinalParcelamento(String periodoFinalParcelamento) {
		this.periodoFinalParcelamento = periodoFinalParcelamento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNumeroPaginaPesquisa() {
		return numeroPaginaPesquisa;
	}
	public void setNumeroPaginaPesquisa(String numeroPaginaPesquisa) {
		this.numeroPaginaPesquisa = numeroPaginaPesquisa;
	}
	
}
