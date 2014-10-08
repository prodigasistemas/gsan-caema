package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class ConsultarImoveisContratoDemandaPopupActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String volumeAgua;

	public String getVolumeAgua() {
		return volumeAgua;
	}

	public void setVolumeAgua(String volumeAgua) {
		this.volumeAgua = volumeAgua;
	}
	
}
