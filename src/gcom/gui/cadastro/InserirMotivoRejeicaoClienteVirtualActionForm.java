package gcom.gui.cadastro;

import org.apache.struts.action.ActionForm;

public class InserirMotivoRejeicaoClienteVirtualActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idClienteVirtual;
	private String motivoRejeicao;
	private String observacao;
	
	public String getIdClienteVirtual() {
		return idClienteVirtual;
	}
	public void setIdClienteVirtual(String idClienteVirtual) {
		this.idClienteVirtual = idClienteVirtual;
	}
	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}
	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
