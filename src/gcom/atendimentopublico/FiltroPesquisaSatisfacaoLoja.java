package gcom.atendimentopublico;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroPesquisaSatisfacaoLoja extends Filtro implements Serializable {

	private static final long serialVersionUID = 3127056305065218234L;
	
	public static final String ID = "id";
	
	public static final String ID_UNIDADE = "unidadeOrganizacional.id";
	
	public static final String ID_IMOVEL = "imovel.id";

}
