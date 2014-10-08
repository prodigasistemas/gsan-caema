package gcom.batch.faturamento;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1439] - Suspende Faturamento de Esgoto dos Imoveis da Tarifa Social 
 * 
 * @author Davi Menezes
 * @date 19/02/2013
 *
 */
public class TarefaBatchSuspenderFaturamentoEsgotoImoveisTarifaSocial extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchSuspenderFaturamentoEsgotoImoveisTarifaSocial(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchSuspenderFaturamentoEsgotoImoveisTarifaSocial(){
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		Integer idFaturamentoGrupo = (Integer) getParametro("idFaturamentoGrupo");
		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");
		
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_SUSPENDER_FATURAMENTO_ESGOTO_IMOVEIS_TARIFA_SOCIAL, 
				new Object[] { idFaturamentoGrupo,anoMesFaturamentoGrupo,this.getIdFuncionalidadeIniciada() });
		
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
		AgendadorTarefas.agendarTarefa("SuspenderFaturamentoEsgotoImoveisTarifaSocialBatch", this);
	}

}
