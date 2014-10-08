package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroUsuarioNaturezaAcompEquipe extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public final static String ID_NATUREZA_EQUIPE = "equipeNatureza.id";
	
	public final static String NATUREZA_EQUIPE = "equipeNatureza";
	
	public final static String ID_USUARIO = "usuario.id";
	
	public final static String USUARIO = "usuario";

}
