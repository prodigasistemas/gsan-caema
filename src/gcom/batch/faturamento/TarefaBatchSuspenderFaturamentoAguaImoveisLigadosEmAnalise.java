package gcom.batch.faturamento;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1438] - Suspender Faturamento de Água dos Imóveis Ligados em Análise
 * 
 * @author Davi Menezes
 * @date 15/02/2013
 *
 */
public class TarefaBatchSuspenderFaturamentoAguaImoveisLigadosEmAnalise extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchSuspenderFaturamentoAguaImoveisLigadosEmAnalise(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchSuspenderFaturamentoAguaImoveisLigadosEmAnalise(){
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_SUSPENDER_FATURAMENTO_AGUA_IMOVEIS_LIGADOS_EM_ANALISE, 
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
		AgendadorTarefas.agendarTarefa("SuspenderFaturamentoAguaImoveisLigadosEmAnaliseBatch", this);
	}

}
