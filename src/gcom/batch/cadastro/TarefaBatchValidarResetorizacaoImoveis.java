package gcom.batch.cadastro;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1387] - Validar Resetorização de Imóveis - GSAN
 * 
 * @author Davi Menezes
 * @date 08/11/2012
 *
 */
public class TarefaBatchValidarResetorizacaoImoveis extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchValidarResetorizacaoImoveis(Usuario usuario, 
			int idFuncionalidadeIniciada) {
		
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchValidarResetorizacaoImoveis(){
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_VALIDAR_RESETORIZACAO_IMOVEIS_MDB, 
				new Object[]{ this.getIdFuncionalidadeIniciada() });
		
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
		AgendadorTarefas.agendarTarefa("ValidarResetorizacaoImoveis", this);
	}

}
