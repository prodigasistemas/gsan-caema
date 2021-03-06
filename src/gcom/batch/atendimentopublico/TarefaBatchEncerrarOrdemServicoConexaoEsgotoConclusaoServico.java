package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchEncerrarOrdemServicoConexaoEsgotoConclusaoServico extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEncerrarOrdemServicoConexaoEsgotoConclusaoServico(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEncerrarOrdemServicoConexaoEsgotoConclusaoServico() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {
    	//Chama o controladorBatch
    	enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENCERRAR_ORDEM_SERVICO_CONEXAO_ESGOTO_CONCLUSAO_SERVICO_MDB,
			new Object[] {
						this.getIdFuncionalidadeIniciada()});
         return null;
    }
    
    @Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

    @Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

    @Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("EncerrarOrdemServicoConexaoEsgotoConclusaoServico",
				this);
	}

}
