package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1323] Rotina de Realizar Recepção de Dados Admin
 * 
 * @author Arthur Carvalho
 * @created 18/04/2012
 */
public class TarefaBatchRecepcaoDadosAdmin extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchRecepcaoDadosAdmin(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchRecepcaoDadosAdmin() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

				
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_RECEPCAO_DADOS_ADMIN,
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
		AgendadorTarefas.agendarTarefa("RecepcaoDadosAdminBatch", this);
	}

}
