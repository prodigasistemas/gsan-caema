package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**[UC1534] Gerar Ordem de Servico Conexao Esgoto
 * @author: Jonathan Marcos
 * @date:12/08/2013
 */
public class TarefaBatchGerarOrdemServicoConexaoEsgoto extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarOrdemServicoConexaoEsgoto(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarOrdemServicoConexaoEsgoto() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {
    	Usuario usuario = (Usuario) getParametro("usuario");
    	Integer idComandoOsConexaoEsgoto = (Integer) getParametro("idComOsConexaoEsgoto");
    	//Chama o controladorBatch
    	enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_ORDEM_SERVICO_CONEXAO_ESGOTO_MDB,
			new Object[] {
						this.getIdFuncionalidadeIniciada(),
						usuario,
						idComandoOsConexaoEsgoto});
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
		AgendadorTarefas.agendarTarefa("GerarOrdemServicoConexaoEsgotoBatch",
				this);
	}

}
