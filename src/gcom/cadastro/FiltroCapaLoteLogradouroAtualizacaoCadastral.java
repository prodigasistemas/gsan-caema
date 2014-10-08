package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro Capa lote Atualizacao Cadastral
 * 
 * @author Davi Menezes
 * @date 24/07/2012
 *
 */
public class FiltroCapaLoteLogradouroAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ID = "id";
	
	public static final String ID_LOGRADOURO = "logradouro.id";
	
	public static final String LOGRADOURO = "logradouro";
	
	public static final String LOGRADOURO_TIPO = "logradouro.logradouroTipo";
	
	public static final String LOGRADOURO_TITULO = "logradouro.logradouroTitulo";
	
	public static final String ID_CAPA_LOTE_ATUALIZACAO_CADASTRAL = "capaLoteAtualizacaoCadastral.id";
	
	public static final String CAPA_LOTE_ATUALIZACAO_CADASTRAL = "capaLoteAtualizacaoCadastral";

}
