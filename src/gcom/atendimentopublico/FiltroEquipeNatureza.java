package gcom.atendimentopublico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEquipeNatureza extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public FiltroEquipeNatureza(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public FiltroEquipeNatureza(){
		
	}
	
	 public final static String ID = "id";
	 
	 public final static String DESCRICAO = "descricao";
	 
	 public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	 
	 public final static String INDICADOR_USO = "indicadorUso";

}
