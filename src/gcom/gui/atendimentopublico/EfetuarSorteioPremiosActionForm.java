package gcom.gui.atendimentopublico;

import org.apache.struts.action.ActionForm;

/**
 * [UC1295] Efetuar Sorteio de Prêmios
 * 
 * @author Mariana Victor
 * @since 06/03/2012
 */
public class EfetuarSorteioPremiosActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idPremio;
	
	private String descricaoPremio;
	
	private String ordemPremio;
	
	private String quantidadePremio;

	private String dataAtual;

	private String dataSorteio;

	private String idSorteio;

	private String descricaoSorteio;
	
	private String idNumeroPremioSorteio;

	
	public EfetuarSorteioPremiosActionForm() {
		super();
	}

	public String getIdPremio() {
		return idPremio;
	}

	public void setIdPremio(String idPremio) {
		this.idPremio = idPremio;
	}

	public String getDescricaoPremio() {
		return descricaoPremio;
	}

	public void setDescricaoPremio(String descricaoPremio) {
		this.descricaoPremio = descricaoPremio;
	}

	public String getQuantidadePremio() {
		return quantidadePremio;
	}

	public void setQuantidadePremio(String quantidadePremio) {
		this.quantidadePremio = quantidadePremio;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getOrdemPremio() {
		return ordemPremio;
	}

	public void setOrdemPremio(String ordemPremio) {
		this.ordemPremio = ordemPremio;
	}

	public String getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(String dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

	public String getIdSorteio() {
		return idSorteio;
	}

	public void setIdSorteio(String idSorteio) {
		this.idSorteio = idSorteio;
	}

	public String getDescricaoSorteio() {
		return descricaoSorteio;
	}

	public void setDescricaoSorteio(String descricaoSorteio) {
		this.descricaoSorteio = descricaoSorteio;
	}

	public String getIdNumeroPremioSorteio() {
		return idNumeroPremioSorteio;
	}

	public void setIdNumeroPremioSorteio(String idNumeroPremioSorteio) {
		this.idNumeroPremioSorteio = idNumeroPremioSorteio;
	}
}
