package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1289] Gerar Base de Cliente para Sorteio
 * Tarefa que manda para batch Gerar Base de Cliente para Sorteio
 * 
 * @author Ana Maria	
 * @created 18/10/2012
 */
public class TarefaBatchGerarBaseClienteSorteioMensal extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarBaseClienteSorteioMensal(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarBaseClienteSorteioMensal() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		
		Collection<Integer> colecaoIdsLocalidade = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		  Iterator iterator = colecaoIdsLocalidade.iterator();
	        
	        while (iterator.hasNext()) {
	        	Integer idLocalidade = (Integer) iterator.next();
	    		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_BASE_CLIENTE_SORTEIO_MENSAL_MDB,
	    				new Object[]{this.getIdFuncionalidadeIniciada(), idLocalidade});
	            	
	        }
			
		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarBaseClienteSorteioMensalBatch", this);
	}

}
