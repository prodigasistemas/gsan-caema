package gcom.gui.atendimentopublico.hidrometro;

import org.apache.struts.action.ActionForm;

public class GerarOrdemServicoRemocaoInstalacaoActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idOrdemServico;
	private Integer servicoTipo;
	
	public GerarOrdemServicoRemocaoInstalacaoActionForm(){}
	
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
 
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
 
	public Integer getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(Integer servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	
}
