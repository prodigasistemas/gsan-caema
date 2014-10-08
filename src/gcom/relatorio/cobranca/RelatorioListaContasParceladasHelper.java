package gcom.relatorio.cobranca;

import java.io.Serializable;

/**
 * 
 * [UC1461] Emitir Resumo do Parcelamento Judicial
 * 
 * @author Maxwell Moreira
 *
 * @date 16/04/2013
 */

public class RelatorioListaContasParceladasHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String imovel;
	private String anoMesConta;
	private String dataVencimentoConta;
	private String valorConta;
	private String acrescimoImpontualidade;
	private String valorAtualizado;
	
	public String getImovel() {
		return imovel;
	}
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}
	public String getAnoMesConta() {
		return anoMesConta;
	}
	public void setAnoMesConta(String anoMesConta) {
		this.anoMesConta = anoMesConta;
	}
	public String getDataVencimentoConta() {
		return dataVencimentoConta;
	}
	public void setDataVencimentoConta(String dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}
	public String getValorConta() {
		return valorConta;
	}
	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}
	public String getAcrescimoImpontualidade() {
		return acrescimoImpontualidade;
	}
	public void setAcrescimoImpontualidade(String acrescimoImpontualidade) {
		this.acrescimoImpontualidade = acrescimoImpontualidade;
	}
	public String getValorAtualizado() {
		return valorAtualizado;
	}
	public void setValorAtualizado(String valorAtualizado) {
		this.valorAtualizado = valorAtualizado;
	}
}