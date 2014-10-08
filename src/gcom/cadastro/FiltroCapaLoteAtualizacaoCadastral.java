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
public class FiltroCapaLoteAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ID = "id";
	
	public static final String ID_LOCALIDADE = "localidade.id";
	
	public static final String LOCALIDADE = "localidade";
	
	public static final String CODIGO_SETOR_COMERCIAL = "setorComercial.codigo";
	
	public static final String SETOR_COMERCIAL = "setorComercial";
	
	public static final String NUMERO_QUADRA = "quadra.numeroQuadra";
	
	public static final String QUADRA = "quadra";
	
	public static final String ID_BAIRRO = "bairro.id";
	
	public static final String BAIRRO = "bairro";
	
	public static final String PARAMETRO_TABELA_ATUALIZACAO_CADASTRAL = "parametroTabelaAtualizacaoCadastro";
	
	public static final String INDICADOR_FINALIZADO = "indicadorFinalizado";

}
