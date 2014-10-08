package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

/**
 * [UC 1400] - Integração Progis com Gsan Via Celular 
 * 
 * @author Davi Menezes
 * @date 18/10/2013
 *
 */
public class FiltroRAReiteracao extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public FiltroRAReiteracao(){
		
	}
	
	public FiltroRAReiteracao(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String NUMERO_PROTOCOLO = "numeroProtocoloAtendimento";
	
	public final static String ID_REGISTRO_ATENDIMENTO = "registroAtendimento.id";
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";

}
