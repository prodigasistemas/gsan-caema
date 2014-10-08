package gcom.batch.cobranca;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * Tarefa que manda para batch Gerar Resumo das Ações de Ordem de Serviço
 * 
 * @author Diogo Luiz
 * @created 17/09/2013
 */

public class TarefaBatchGerarResumoAcoesOrdemDeServico extends TarefaBatch{
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarResumoAcoesOrdemDeServico(Usuario usuario,
			int idFuncionalidadeIniciada) {
		
		super(usuario, idFuncionalidadeIniciada);
		
	}	
	
	@Deprecated
	public TarefaBatchGerarResumoAcoesOrdemDeServico() {
		super(null, 0);
	}

	
	@Override
	public Object executar() throws TarefaException {
		
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_RESUMO_ACOES_ORDEM_DE_SERVICO_MDB,
				new Object[] {this.getIdFuncionalidadeIniciada()});		
		
		return null;
	}
	
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"GerarResumoAcoesOrdemDeServicoBatch", this);
		
	}
	

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		
		return null;
	}

	

	

}
