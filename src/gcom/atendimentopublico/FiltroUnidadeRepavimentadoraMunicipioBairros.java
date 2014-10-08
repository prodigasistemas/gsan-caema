package gcom.atendimentopublico;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroUnidadeRepavimentadoraMunicipioBairros extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroUnidadeRepavimentadoraMunicipioBairros(){
		
	}
	
	public FiltroUnidadeRepavimentadoraMunicipioBairros(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}

	public static final String ID_UNIDADE_REPAVIMENTADORA = "comp_id.unidadeRepavimentadoraMunicipioId.id";
	
	public static final String UNIDADE_REPAVIMENTADORA_MUNICIPIO = "comp_id.unidadeRepavimentadoraMunicipioId";
	
	public static final String ID_BAIRRO = "comp_id.bairroId.id";
	
	public static final String BAIRRO = "comp_id.bairroId";
	
	public static final String UNIDADE_REPAVIMENTADORA = "comp_id.unidadeRepavimentadoraMunicipioId.unidadeRepavimentadora";
	
}
