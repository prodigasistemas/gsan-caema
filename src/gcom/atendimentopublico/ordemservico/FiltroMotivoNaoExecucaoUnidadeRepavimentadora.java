package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroMotivoNaoExecucaoUnidadeRepavimentadora extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroMotivoNaoExecucaoUnidadeRepavimentadora(){
	}
	
	public FiltroMotivoNaoExecucaoUnidadeRepavimentadora(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID_MOTIVO_DE_NAO_EXECUCAO_DO_SERVICO = "comp_id.motivoDeNaoExecucaoDoServico.id";
			
	public final static String MOTIVO_DE_NAO_EXECUCAO_DO_SERVICO = "comp_id.motivoDeNaoExecucaoDoServico";
	
	public final static String ID_UNIDADE_REPAVIMENTADORA = "comp_id.unidadeRepavimentadora.id";
	
	public final static String UNIDADE_REPAVIMENTADORA = "comp_id.unidadeRepavimentadora";
	
	public final static String DESCRICAO = "comp_id.motivoDeNaoExecucaoDoServico.descricao";
	
	public final static String DESCRICAO_ABREVIADA = "comp_id.motivoDeNaoExecucaoDoServico.descricaoAbreviada";
	
	public final static String INDICADOR_USO = "comp_id.motivoDeNaoExecucaoDoServico.indicadorUso";

}
