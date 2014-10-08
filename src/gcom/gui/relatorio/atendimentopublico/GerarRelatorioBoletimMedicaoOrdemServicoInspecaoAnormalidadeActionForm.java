package gcom.gui.relatorio.atendimentopublico;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * [UC1221] – Gerar Boletim de Medição Ordem de Serviço Inspeção Anormalidade
 * 
 * @since 19/08/2011
 * @author Diogo Peixoto
 *
 */
public class GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	private String nomeEmpresa;
	
	private String periodoExecucaoInicial;
	private String periodoExecucaoFinal;
	
	private String[] idsComandos;
	
	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getPeriodoExecucaoInicial() {
		return periodoExecucaoInicial;
	}

	public void setPeriodoExecucaoInicial(String periodoExecucaoInicial) {
		this.periodoExecucaoInicial = periodoExecucaoInicial;
	}

	public String getPeriodoExecucaoFinal() {
		return periodoExecucaoFinal;
	}

	public void setPeriodoExecucaoFinal(String periodoExecucaoFinal) {
		this.periodoExecucaoFinal = periodoExecucaoFinal;
	}

	public String[] getIdsComandos() {
		return idsComandos;
	}

	public void setIdsComandos(String[] idsComandos) {
		this.idsComandos = idsComandos;
	}
}