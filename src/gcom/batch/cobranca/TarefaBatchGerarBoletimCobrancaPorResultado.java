package gcom.batch.cobranca;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchGerarBoletimCobrancaPorResultado extends TarefaBatch {

	public TarefaBatchGerarBoletimCobrancaPorResultado(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario,idFuncionalidadeIniciada);		
	}

	@Deprecated
	public TarefaBatchGerarBoletimCobrancaPorResultado() {
		super(null, 0);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executar() throws TarefaException {		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_BOLETIM_COBRANCA_POR_RESULTADO,
			new Object[] {this.getUsuario(), this.getIdFuncionalidadeIniciada()});
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub
		AgendadorTarefas.agendarTarefa("GerarBoletimCobrancaPorResultadoBatch", this);
		
	}

}
