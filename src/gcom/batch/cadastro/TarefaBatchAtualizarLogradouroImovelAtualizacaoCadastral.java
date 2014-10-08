package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
 * 
 * @author Arthur Carvalho
 * @created 18/05/2012
 */
public class TarefaBatchAtualizarLogradouroImovelAtualizacaoCadastral extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarLogradouroImovelAtualizacaoCadastral(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarLogradouroImovelAtualizacaoCadastral() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

				
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ATUALIZAR_LOGRADOURO_IMOVEL_ATUALIZACAO_CADASTRAL,
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
		AgendadorTarefas.agendarTarefa("AtualizarLogradouroImovelAtualizacaoCadastralBatch", this);
	}

}
