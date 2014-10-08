package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Davi Menezes
 * @date 22/11/2012
 *
 */
public class FiltroClienteAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroClienteAtualizacaoCadastral(){
		
	}
	
	public FiltroClienteAtualizacaoCadastral(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	
	public final static String ID_CLIENTE = "idCliente";
	
	public final static String ID_IMOVEL = "idImovel";
	
	public final static String ID_PARAMETRO_TABELA_ATUALIZACAO_CADASTRAL = "parametroTabelaAtualizacaoCadastro.id";
	
	public final static String PARAMETRO_TABELA_ATUALIZACAO_CADASTRAL = "parametroTabelaAtualizacaoCadastro";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral.id";
	
	public final static String IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral";
	
	public final static String DATA_FIM_RELACAO = "dataRelacaoFim";
}
