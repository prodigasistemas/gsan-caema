package gcom.batch.cadastro;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1386] - Processar Arquivo de Resetorização de Imóveis
 * 
 * @author Davi Menezes
 * @date 31/10/2012
 *
 */
public class TarefaBatchProcessarArquivoResetorizacaoImoveis extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchProcessarArquivoResetorizacaoImoveis(Usuario usuario, 
			int idFuncionalidadeIniciada) {
		
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchProcessarArquivoResetorizacaoImoveis(){
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_PROCESSAR_ARQUIVO_RESETORIZACAO_IMOVEIS_MDB, 
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
		AgendadorTarefas.agendarTarefa("ProcessarArquivoResetorizacaoImoveis", this);
	}

}
