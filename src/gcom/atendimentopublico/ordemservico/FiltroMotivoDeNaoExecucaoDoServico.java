package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroMotivoDeNaoExecucaoDoServico extends Filtro{

	
	private static final long serialVersionUID = 1L;
	
	public FiltroMotivoDeNaoExecucaoDoServico(){
		
	}
	
	public FiltroMotivoDeNaoExecucaoDoServico(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
		
	}
	
	public final static String ID = "id";
	
	public final static String DESCRICAO = "descricao";
	
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String ID_UNIDADE_REPAVIMENTADORA = "idUnidadeRepavimentadora";
	
	public final static String UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora";

}
