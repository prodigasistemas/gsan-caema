package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroUsuarioEmpresaAcompEquipe extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public final static String ID_EMPRESA = "empresa.id";
	
	public final static String EMPRESA = "empresa";
			
	public final static String ID_USUARIO = "usuario.id";
	
	public final static String USUARIO = "usuario";

}
