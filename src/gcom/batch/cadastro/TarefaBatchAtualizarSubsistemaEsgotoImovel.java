package gcom.batch.cadastro;

import java.util.Collection;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1523] - Atualizar Subsistema de Esgoto do Imóvel
 * 
 * @author Anderson Cabral
 * @date 04/07/2013
 *
 */
public class TarefaBatchAtualizarSubsistemaEsgotoImovel extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchAtualizarSubsistemaEsgotoImovel(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchAtualizarSubsistemaEsgotoImovel(){
		super(null, 0);
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
	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ATUALIZAR_SUBSISTEMA_ESGOTO_IMOVEL, 
				new Object[]{ this.getIdFuncionalidadeIniciada()});
		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("AtualizarSubsistemaEsgotoImovel", this);
	}

}
