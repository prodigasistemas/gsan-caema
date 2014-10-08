package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1560] - Validar Dados dos Endereços Enviados pelo GEO
 * 
 * @author Anderson Cabral
 * @date 19/09/2013
 */
public class TarefaBatchValidarDadosDoEnderecoEnviadosPeloGEO extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchValidarDadosDoEnderecoEnviadosPeloGEO(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchValidarDadosDoEnderecoEnviadosPeloGEO(){
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

		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_VALIDAR_DADOS_DO_ENDERECO_ENVIADOS_PELO_GEO, 
				new Object[]{ this.getIdFuncionalidadeIniciada()});
		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("ValidarDadosDoEnderecoEnviadosPeloGEO", this);
	}
}
