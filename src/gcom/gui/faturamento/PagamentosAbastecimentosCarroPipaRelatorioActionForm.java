package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;
/**
 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
 * 
 * @author Diogo Luiz
 * @Date 15/10/2013
 * 
 */
public class PagamentosAbastecimentosCarroPipaRelatorioActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	private String idFaturamentoGrupo;	
	private String gerenciaRegional;
	private String unidadeNegocio;
	
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}	
	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}
	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
}
