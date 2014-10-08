package gcom.batch.cobranca.cobrancaporresultado;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1345] Encerrar Automaticamente Comandos de Cobrança por Resultado
 * Tarefa que manda para batch 'Encerrar Automaticamente Comandos de Cobrança por Resultado'
 * 
 * @author Mariana Victor
 * @created 04/06/2012
 */
public class TarefaBatchEncerrarAutomaticamenteComandosCobResultado extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEncerrarAutomaticamenteComandosCobResultado(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEncerrarAutomaticamenteComandosCobResultado() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENCERRAR_AUTOMATICAMENTE_COMANDOS_COB_RESULTADO,
			new Object[]{this.getIdFuncionalidadeIniciada()});
				
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
		AgendadorTarefas.agendarTarefa("EncerrarAutomaticamenteComandosCobResultadoBatch", this);
	}

}
