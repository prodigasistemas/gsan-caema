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
public class TarefaBatchInserirDadosAdmin extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchInserirDadosAdmin(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchInserirDadosAdmin() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer idParametro = (Integer) getParametro("idParametro");
		
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_INSERIR_DADOS_ADMIN,
				new Object[]{this.getIdFuncionalidadeIniciada(), idParametro});
				
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
		AgendadorTarefas.agendarTarefa("InserirDadosAdminBatch", this);
	}

}
