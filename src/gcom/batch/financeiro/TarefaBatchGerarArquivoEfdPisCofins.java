package gcom.batch.financeiro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Map;


/**
 * [UC1272] Gerar Arquivos EFD-PIS/Confins
 * 
 * @author Erivan Sousa
 * @date 02/07/2012
 */
public class TarefaBatchGerarArquivoEfdPisCofins extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarArquivoEfdPisCofins(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarArquivoEfdPisCofins() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Map parametros = (Map) getParametro(ConstantesSistema.PARAMETROS_BATCH);
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_ARQUIVOS_EFD_PIS_CONFINS, 
			new Object[] {this.getIdFuncionalidadeIniciada(),parametros});

			
		return null;
	}
	
	@Override
	protected Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}	

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarArquivoEfdPisCofins", this);
	}

}
