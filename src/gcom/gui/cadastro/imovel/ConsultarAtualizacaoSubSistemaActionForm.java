package gcom.gui.cadastro.imovel;

import org.apache.struts.action.ActionForm;

public class ConsultarAtualizacaoSubSistemaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String dataInicio;
	private String dataFinal;
	
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

}
