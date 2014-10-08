package gcom.batch.faturamento;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 0000] - Atualizar Mês/Ano Contábil dos Débitos a Cobrar
 * 
 * @author Davi Menezes
 * @date 15/02/2013
 *
 */
public class TarefaBatchAtualizarMesAnoContabilDebitosACobrar extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarMesAnoContabilDebitosACobrar(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchAtualizarMesAnoContabilDebitosACobrar() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_ATUALIZAR_MES_ANO_CONTABIL_DEBITOS_A_COBRAR, 
				new Object[] { this.getIdFuncionalidadeIniciada() } );
		
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
		AgendadorTarefas.agendarTarefa("AtualizarMesAnoContabilDebitosACobrarBatch", this);
	}

}
