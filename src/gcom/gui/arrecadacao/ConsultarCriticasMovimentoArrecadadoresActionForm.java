package gcom.gui.arrecadacao;

import org.apache.struts.action.ActionForm;

public class ConsultarCriticasMovimentoArrecadadoresActionForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoArrecadador;
	private String nomeArrecadador;
	private String idServico;
	private String numeroSequencialArquivo;
	private String periodoGeracaoInicial;
	private String periodoGeracaoFinal;
	private String periodoProcessamentoInicial;
	private String periodoProcessamentoFinal;
	
	public String getCodigoArrecadador() {
		return codigoArrecadador;
	}
	public void setCodigoArrecadador(String codigoArrecadador) {
		this.codigoArrecadador = codigoArrecadador;
	}
	public String getNomeArrecadador() {
		return nomeArrecadador;
	}
	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public String getNumeroSequencialArquivo() {
		return numeroSequencialArquivo;
	}
	public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}
	public String getPeriodoGeracaoInicial() {
		return periodoGeracaoInicial;
	}
	public void setPeriodoGeracaoInicial(String periodoGeracaoInicial) {
		this.periodoGeracaoInicial = periodoGeracaoInicial;
	}
	public String getPeriodoGeracaoFinal() {
		return periodoGeracaoFinal;
	}
	public void setPeriodoGeracaoFinal(String periodoGeracaoFinal) {
		this.periodoGeracaoFinal = periodoGeracaoFinal;
	}
	public String getPeriodoProcessamentoInicial() {
		return periodoProcessamentoInicial;
	}
	public void setPeriodoProcessamentoInicial(String periodoProcessamentoInicial) {
		this.periodoProcessamentoInicial = periodoProcessamentoInicial;
	}
	public String getPeriodoProcessamentoFinal() {
		return periodoProcessamentoFinal;
	}
	public void setPeriodoProcessamentoFinal(String periodoProcessamentoFinal) {
		this.periodoProcessamentoFinal = periodoProcessamentoFinal;
	}
	
}
