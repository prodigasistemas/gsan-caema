package gcom.atendimentopublico;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroUnidadeRepavimentadoraMunicipio extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroUnidadeRepavimentadoraMunicipio(){
		
	}
	
	public FiltroUnidadeRepavimentadoraMunicipio(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public static final String ID = "id";
	
	public static final String ID_UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora.id";
	
	public static final String UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora";
	
	public static final String ID_MUNICIPIO = "municipio.id";
	
	public static final String MUNICIPIO = "municipio";
	
	public static final String DESCRICAO_AGRUPAMENTO = "descricaoAgrupamento";
	
	public static final String INDICADOR_USO = "indicadorUso";

}
