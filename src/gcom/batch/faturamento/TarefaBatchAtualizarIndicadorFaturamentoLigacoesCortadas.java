package gcom.batch.faturamento;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * Tarefa que manda para o batch Atualizar Indicador de Faturamento das Ligações Cortadas
 * 
 * @author Davi Menezes
 * @date 14/02/2013
 *
 */
public class TarefaBatchAtualizarIndicadorFaturamentoLigacoesCortadas extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarIndicadorFaturamentoLigacoesCortadas(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchAtualizarIndicadorFaturamentoLigacoesCortadas(){
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_ATUALIZAR_INDICADOR_FATURAMENTO_LIGACOES_CORTADAS, 
				new Object[] { this.getIdFuncionalidadeIniciada() });
		
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("AtualizarIndicadorFaturamentoLigacoesCortadasBatch", this);
	}
	
}