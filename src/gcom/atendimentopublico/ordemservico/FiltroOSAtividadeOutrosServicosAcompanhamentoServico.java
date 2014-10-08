package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroOSAtividadeOutrosServicosAcompanhamentoServico extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public FiltroOSAtividadeOutrosServicosAcompanhamentoServico(){
	}

	public FiltroOSAtividadeOutrosServicosAcompanhamentoServico(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String ID_ORDEM_SERVICO = "ordemServico.id";
	
	public final static String ORDEM_SERVICO = "ordemServico";
	
	public final static String ID_SERVICO_REPAVIMENTADORA = "servicoRepavimentadora.id";
	
	public final static String SERVICO_REPAVIMENTADORA = "servicoRepavimentadora";
	
	public final static String ID_OS_ATIV_PROG_ACOMPANHAMENTO_SERVICO = "osAtividadeProgramacaoAcompanhamentoServico.id";
	
	public final static String OS_ATIV_PROG_ACOMPANHAMENTO_SERVICO = "osAtividadeProgramacaoAcompanhamentoServico";
	
}
