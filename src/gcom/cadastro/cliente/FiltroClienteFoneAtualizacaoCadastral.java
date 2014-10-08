package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Davi Menezes
 * @date 22/11/2012
 *
 */
public class FiltroClienteFoneAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroClienteFoneAtualizacaoCadastral(){
	}
	
	public FiltroClienteFoneAtualizacaoCadastral(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String ID_CLIENTE_ATUALIZACAO_CADASTRAL = "clienteAtualizacaoCadastral.id";
	
	public final static String CLIENTE_ATUALIZACAO_CADASTRAL = "clienteAtualizacaoCadastral";

}
