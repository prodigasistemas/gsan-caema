package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Davi Menezes
 * @date 22/11/2012
 *
 */
public class FiltroImovelSubcategoriaAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroImovelSubcategoriaAtualizacaoCadastral(){
		
	}
	
	public FiltroImovelSubcategoriaAtualizacaoCadastral(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	
	public final static String ID_IMOVEL = "idImovel";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral.id";
	
	public final static String IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral";
}
