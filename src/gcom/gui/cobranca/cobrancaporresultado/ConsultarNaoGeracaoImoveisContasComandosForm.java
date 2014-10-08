package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * @author Raimundo Martins
 * */
public class ConsultarNaoGeracaoImoveisContasComandosForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	private String descricaoEmpresa;
	private String dataInicial;
	private String dataFinal;
	private String idRegistro;
	
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	
}
