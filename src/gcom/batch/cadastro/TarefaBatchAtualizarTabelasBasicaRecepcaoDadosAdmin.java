package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
 * 
 * @author Arthur Carvalho
 * @created 18/04/2012
 */
public class TarefaBatchAtualizarTabelasBasicaRecepcaoDadosAdmin extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarTabelasBasicaRecepcaoDadosAdmin(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarTabelasBasicaRecepcaoDadosAdmin() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

				
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ATUALIZAR_TABELA_BASICA_RECEPCAO_DADOS_ADMIN,
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
		AgendadorTarefas.agendarTarefa("AtualizarTabelasBasicasRecepcaoDadosAdminBatch", this);
	}

}
