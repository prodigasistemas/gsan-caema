package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.ServicoRepavimentadoraHelper;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 1475] - Informar Serviços da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 20/05/2013
 *
 */
public class InformarServicoRepavimentadoraPopupActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idOrdemServico;
	
	private String idUnidadeRepavimentadora;
	
	private String servico;
	
	private String quantidade;
	
	private Collection<ServicoRepavimentadoraHelper> colecaoServicoRepavimentadoraHelper;

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}

	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}
	
	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Collection<ServicoRepavimentadoraHelper> getColecaoServicoRepavimentadoraHelper() {
		return colecaoServicoRepavimentadoraHelper;
	}

	public void setColecaoServicoRepavimentadoraHelper(Collection<ServicoRepavimentadoraHelper> colecaoServicoRepavimentadoraHelper) {
		this.colecaoServicoRepavimentadoraHelper = colecaoServicoRepavimentadoraHelper;
	}
	
}
