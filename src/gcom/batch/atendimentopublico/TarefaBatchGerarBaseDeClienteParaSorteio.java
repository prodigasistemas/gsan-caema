package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1289] Gerar Base de Cliente para Sorteio
 * Tarefa que manda para batch Gerar Base de Cliente para Sorteio
 * 
 * @author Mariana Victor
 * @created 02/03/2012
 */
public class TarefaBatchGerarBaseDeClienteParaSorteio extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarBaseDeClienteParaSorteio(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarBaseDeClienteParaSorteio() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection<Integer> colecaoIdsLocalidade = (Collection<Integer>) getParametro("colecaoIdsLocalidade");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_BASE_DE_CLIENTE_PARA_SORTEIO_MDB,
			new Object[]{colecaoIdsLocalidade,this.getIdFuncionalidadeIniciada()});
				
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
		AgendadorTarefas.agendarTarefa("GerarBaseDeClienteParaSorteioBatch", this);
	}

}
