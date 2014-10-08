package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

public class FiltroCoordenadasCompGis extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public FiltroCoordenadasCompGis() {
    }

    public FiltroCoordenadasCompGis(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String USUARIO_ID = "usuario.id";
    
    //PATH
    public final static String LOGRADOURO = "logradouroBairro.logradouro";
    public final static String BAIRRO = "logradouroBairro.bairro";
    

}
