package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroOrdemServicoContas extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroOrdemServicoContas(){
		
	}
	
	public FiltroOrdemServicoContas(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String VALOR_CONTA = "valorConta";
	
	public final static String ANO_MES_REFERENCIA_ENCERRAMENTO = "anoMesReferenciaEncerramento";

	public final static String ID_ORDEM_SERVICO = "ordemServico.id";
	
	public final static String ORDEM_SERVICO = "ordemServico";
	
	public final static String ID_CONTA = "contaGeral.conta.id";
	
	public final static String CONTA = "contaGeral.conta";
	
}
