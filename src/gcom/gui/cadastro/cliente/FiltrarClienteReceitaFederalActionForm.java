package gcom.gui.cadastro.cliente;

import org.apache.struts.action.ActionForm;

/**
 * [UC1429] Validar Cliente na Base da Receita Federal
 * 
 * @author Maxwell Moreira
 * @created 14/01/2013
 */

public class FiltrarClienteReceitaFederalActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String indicadorValidacao;
	private String cpf;
	private String cnpj;
	
	public String getIndicadorValidacao() {
		return indicadorValidacao;
	}

	public void setIndicadorValidacao(String indicadorValidacao) {
		this.indicadorValidacao = indicadorValidacao;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}