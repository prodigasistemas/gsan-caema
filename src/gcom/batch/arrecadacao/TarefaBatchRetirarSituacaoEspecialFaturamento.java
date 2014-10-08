package gcom.batch.arrecadacao;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchRetirarSituacaoEspecialFaturamento extends TarefaBatch{

	private static final long serialVersionUID = 1L;

	public TarefaBatchRetirarSituacaoEspecialFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);

	}

	@Deprecated
	public TarefaBatchRetirarSituacaoEspecialFaturamento() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO_MDB,
				new Object[] {this.getIdFuncionalidadeIniciada() });
		return null;

	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchRetirarSituacaoEspecialFaturamento",
				this);
	}

}
