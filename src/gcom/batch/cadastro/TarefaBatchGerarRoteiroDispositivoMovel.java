package gcom.batch.cadastro;

import java.util.Collection;

import gcom.cadastro.atualizacaocadastral.bean.ComandoAtualizacaoCadastralHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 23/11/2012
 *
 */
public class TarefaBatchGerarRoteiroDispositivoMovel extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarRoteiroDispositivoMovel(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarRoteiroDispositivoMovel(){
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
		
		Collection<String> colecaoImoveis = (Collection<String>) getParametro("colecaoImoveis");
		ComandoAtualizacaoCadastralHelper comandoHelper = (ComandoAtualizacaoCadastralHelper) getParametro("helper");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_ROTEIRO_DISPOSITIVO_MOVEL_MDB, 
				new Object[]{ this.getIdFuncionalidadeIniciada(), colecaoImoveis, comandoHelper});
		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarRoteiroDispositivoMovel", this);
	}

}
