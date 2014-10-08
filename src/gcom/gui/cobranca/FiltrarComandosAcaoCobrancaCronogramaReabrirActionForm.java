package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class FiltrarComandosAcaoCobrancaCronogramaReabrirActionForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	private String[] grupoCobranca;
	
	private String[] acaoCobranca;
	
	private String[] atividadeCobranca;
	
	private String periodoReferenciaCobrancaInicial;
	
	private String periodoReferenciaCobrancaFinal;
	
	private String periodoEncerramentoComandoInicial;
	
	private String periodoEncerramentoComandoFinal;

	public String[] getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String[] grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String[] getAcaoCobranca() {
		return acaoCobranca;
	}

	public void setAcaoCobranca(String[] acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	public String[] getAtividadeCobranca() {
		return atividadeCobranca;
	}

	public void setAtividadeCobranca(String[] atividadeCobranca) {
		this.atividadeCobranca = atividadeCobranca;
	}

	public String getPeriodoReferenciaCobrancaInicial() {
		return periodoReferenciaCobrancaInicial;
	}

	public void setPeriodoReferenciaCobrancaInicial(String periodoReferenciaCobrancaInicial) {
		this.periodoReferenciaCobrancaInicial = periodoReferenciaCobrancaInicial;
	}

	public String getPeriodoReferenciaCobrancaFinal() {
		return periodoReferenciaCobrancaFinal;
	}

	public void setPeriodoReferenciaCobrancaFinal(String periodoReferenciaCobrancaFinal) {
		this.periodoReferenciaCobrancaFinal = periodoReferenciaCobrancaFinal;
	}

	public String getPeriodoEncerramentoComandoInicial() {
		return periodoEncerramentoComandoInicial;
	}

	public void setPeriodoEncerramentoComandoInicial(String periodoEncerramentoComandoInicial) {
		this.periodoEncerramentoComandoInicial = periodoEncerramentoComandoInicial;
	}

	public String getPeriodoEncerramentoComandoFinal() {
		return periodoEncerramentoComandoFinal;
	}

	public void setPeriodoEncerramentoComandoFinal(String periodoEncerramentoComandoFinal) {
		this.periodoEncerramentoComandoFinal = periodoEncerramentoComandoFinal;
	}
	
}
