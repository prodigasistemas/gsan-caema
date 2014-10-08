package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Map;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1385] - Conceder Crédito Conjunto Conta
 * 
 * @author Davi Menezes
 * @date 23/10/2012
 *
 */
public class TarefaBatchConcederCreditoConjuntoConta extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchConcederCreditoConjuntoConta(Usuario usuario, int idFuncionalidadeIniciada){
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchConcederCreditoConjuntoConta(){
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Map parametros = (Map) getParametro(ConstantesSistema.PARAMETROS_BATCH); 
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_CONCEDER_CREDITO_CONJUNTO_CONTAS_MDB , new Object[] {this.getIdFuncionalidadeIniciada(),parametros});
		
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
		AgendadorTarefas.agendarTarefa("ConcederCreditoConjuntoContasBatch", this);
	}

}
