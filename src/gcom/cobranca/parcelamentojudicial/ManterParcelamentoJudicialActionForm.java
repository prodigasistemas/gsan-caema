package gcom.cobranca.parcelamentojudicial;

import org.apache.struts.action.ActionForm;

public class ManterParcelamentoJudicialActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoClienteResponsavel;
	private String nomeClienteResponsavel;
	private String codigoClienteUsuario;
	private String nomeClienteUsuario;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String processoJudicial;
	private String periodoInicialParcelamento;
	private String periodoFinalParcelamento;
	private String numeroPrestacao;
	private String[] idsGuiaAtraso;
	private String tipoParcelaEmissao;
	
	public ManterParcelamentoJudicialActionForm(){}
	
	
	public String getNumeroPrestacao() {
		return numeroPrestacao;
	}
	public void setNumeroPrestacao(String numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}
	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getCodigoClienteResponsavel() {
		return codigoClienteResponsavel;
	}

	public void setCodigoClienteResponsavel(String codigoClienteResponsavel) {
		this.codigoClienteResponsavel = codigoClienteResponsavel;
	}

	public String getCodigoClienteUsuario() {
		return codigoClienteUsuario;
	}
	public void setCodigoClienteUsuario(String codigoClienteUsuario) {
		this.codigoClienteUsuario = codigoClienteUsuario;
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
	public String[] getIdsGuiaAtraso() {
		return idsGuiaAtraso;
	}
	public void setIdsGuiaAtraso(String[] idsGuiaAtraso) {
		this.idsGuiaAtraso = idsGuiaAtraso;
	}
	public String getTipoParcelaEmissao() {
		return tipoParcelaEmissao;
	}
	public void setTipoParcelaEmissao(String tipoParcelaEmissao) {
		this.tipoParcelaEmissao = tipoParcelaEmissao;
	}	
}
