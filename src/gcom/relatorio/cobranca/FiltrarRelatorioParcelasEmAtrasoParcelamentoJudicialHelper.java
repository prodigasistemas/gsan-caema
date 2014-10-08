package gcom.relatorio.cobranca;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1465] Gerar Relatório das Parcelas em Atraso do Parcelamento Judicial
 * 
 * @author Mariana Victor
 * @date 22/04/2013
 */
public class FiltrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idClienteResponsavel;
	
	private String nomeClienteResponsavel;
	
	private Integer idImovelPrincipal;
	
	private String numeroProcessoJudicial;
	
	private Integer quantidadeDiasAtraso;
	
	private Date dataParcelamentoInicial;
	
	private Date dataParcelamentoFinal;

	public Integer getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(Integer idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	public Integer getIdImovelPrincipal() {
		return idImovelPrincipal;
	}

	public void setIdImovelPrincipal(Integer idImovelPrincipal) {
		this.idImovelPrincipal = idImovelPrincipal;
	}

	public String getNumeroProcessoJudicial() {
		return numeroProcessoJudicial;
	}

	public void setNumeroProcessoJudicial(String numeroProcessoJudicial) {
		this.numeroProcessoJudicial = numeroProcessoJudicial;
	}

	public Integer getQuantidadeDiasAtraso() {
		return quantidadeDiasAtraso;
	}

	public void setQuantidadeDiasAtraso(Integer quantidadeDiasAtraso) {
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	public Date getDataParcelamentoInicial() {
		return dataParcelamentoInicial;
	}

	public void setDataParcelamentoInicial(Date dataParcelamentoInicial) {
		this.dataParcelamentoInicial = dataParcelamentoInicial;
	}

	public Date getDataParcelamentoFinal() {
		return dataParcelamentoFinal;
	}

	public void setDataParcelamentoFinal(Date dataParcelamentoFinal) {
		this.dataParcelamentoFinal = dataParcelamentoFinal;
	}


}
