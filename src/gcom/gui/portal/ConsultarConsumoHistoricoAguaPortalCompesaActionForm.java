package gcom.gui.portal;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Consultar Consumo Historico Volume Agua
 * 
 * @author Nathalia Santos
 * @since 22/03/2012
 */

public class ConsultarConsumoHistoricoAguaPortalCompesaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idTipoMedicao;
	private String consumoFaturado;
	private String consumoMedido;
	private String diasConsumo;
	private String consumoMedioImovel;
	private String dtLeituraAnterior;
	
	
	public String getIdTipoMedicao() {
		return idTipoMedicao;
	}
	public void setIdTipoMedicao(String idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}
	public String getConsumoFaturado() {
		return consumoFaturado;
	}
	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}
	public String getConsumoMedido() {
		return consumoMedido;
	}
	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}
	public String getDiasConsumo() {
		return diasConsumo;
	}
	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}
	public String getConsumoMedioImovel() {
		return consumoMedioImovel;
	}
	public void setConsumoMedioImovel(String consumoMedioImovel) {
		this.consumoMedioImovel = consumoMedioImovel;
	}
	public String getDtLeituraAnterior() {
		return dtLeituraAnterior;
	}
	public void setDtLeituraAnterior(String dtLeituraAnterior) {
		this.dtLeituraAnterior = dtLeituraAnterior;
	}
		
}