package gcom.faturamento.contratodemanda;

import gcom.util.filtro.Filtro;

public class FiltroContratoDemandaMotivoEncerramento extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public FiltroContratoDemandaMotivoEncerramento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroContratoDemandaMotivoEncerramento() {
    
    }

	public final static String ID = "id";
	
	public final static String DESCRICAO = "descricao";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
}
