package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC1465] Gerar Relatório das Parcelas em Atraso do Parcelamento Judicial
 * 
 * @author Mariana Victor
 * @date 22/04/2013
 */
public class GerarRelatorioParcelasEmAtrasoParcelamentoJudicialForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idClienteResponsavel;
	
	private String nomeClienteResponsavel;
	
	private String idImovelPrincipal;
	
	private String descricaoImovelPrincipal;
	
	private String numeroProcessoJudicial;
	
	private String quantidadeDiasAtraso;
	
	private String dataParcelamentoInicial;
	
	private String dataParcelamentoFinal;

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	public String getIdImovelPrincipal() {
		return idImovelPrincipal;
	}

	public void setIdImovelPrincipal(String idImovelPrincipal) {
		this.idImovelPrincipal = idImovelPrincipal;
	}

	public String getDescricaoImovelPrincipal() {
		return descricaoImovelPrincipal;
	}

	public void setDescricaoImovelPrincipal(String descricaoImovelPrincipal) {
		this.descricaoImovelPrincipal = descricaoImovelPrincipal;
	}

	public String getNumeroProcessoJudicial() {
		return numeroProcessoJudicial;
	}

	public void setNumeroProcessoJudicial(String numeroProcessoJudicial) {
		this.numeroProcessoJudicial = numeroProcessoJudicial;
	}

	public String getQuantidadeDiasAtraso() {
		return quantidadeDiasAtraso;
	}

	public void setQuantidadeDiasAtraso(String quantidadeDiasAtraso) {
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	public String getDataParcelamentoInicial() {
		return dataParcelamentoInicial;
	}

	public void setDataParcelamentoInicial(String dataParcelamentoInicial) {
		this.dataParcelamentoInicial = dataParcelamentoInicial;
	}

	public String getDataParcelamentoFinal() {
		return dataParcelamentoFinal;
	}

	public void setDataParcelamentoFinal(String dataParcelamentoFinal) {
		this.dataParcelamentoFinal = dataParcelamentoFinal;
	}

}
