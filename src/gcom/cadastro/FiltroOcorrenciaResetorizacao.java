package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC 1386] - Processar Arquivo de Resetorização dos Imóveis
 * 
 * @author Davi Menezes
 * @date 30/10/2012
 *
 */
public class FiltroOcorrenciaResetorizacao extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public final static String DESCRICAO_MENSAGEM = "descricaoMensagem";

}
