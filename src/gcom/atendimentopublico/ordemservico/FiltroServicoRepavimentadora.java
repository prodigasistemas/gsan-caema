package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroServicoRepavimentadora extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public FiltroServicoRepavimentadora(){
		
	}
	
	public FiltroServicoRepavimentadora(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String DESCRICAO = "descricao";
	
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String ID_UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora.id";
	
	public final static String UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora";
	
	public final static String ID_MATERIAL_UNIDADE = "materialUnidade.id";
	
	public final static String MATERIAL_UNIDADE = "materialUnidade";

}
