package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;
import java.io.Serializable;

/**
 * FiltroCobrancaIndevidaMotivo 
 *
 * @author Maxwell Moreira
 * @date 29/11/2012
 */

public class FiltroCobrancaIndevidaMotivo extends Filtro implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
	/**
     * Constructor for the FiltroCobrancaIndevidaMotivo object
     */
    public FiltroCobrancaIndevidaMotivo(){
    }
    
}