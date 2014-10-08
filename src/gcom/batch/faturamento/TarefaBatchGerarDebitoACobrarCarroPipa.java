package gcom.batch.faturamento;

import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * [UC1564] Gerar Débito A Cobrar Carro-Pipa
 *  
 * @author Mariana Victor
 * @created 14/10/2013
 */
public class TarefaBatchGerarDebitoACobrarCarroPipa extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarDebitoACobrarCarroPipa(
			Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDebitoACobrarCarroPipa() {
		super(null, 0);
	}


	public Object executar() throws TarefaException {
		
		Collection<Object[]> colecaoRotasParaExecucao = (Collection<Object[]>) 
				getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");

		Iterator<Object[]> iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();
			Rota rota = (Rota) Util.retonarObjetoDeColecao(
				Collections.singletonList((Rota) array[1]));
		       
	        enviarMensagemControladorBatch(
	        		ConstantesJNDI.BATCH_GERAR_DEBITO_A_COBRAR_CARRO_PIPA,
	                new Object[] {
	        			this.getIdFuncionalidadeIniciada(),	
	        			rota,
	        			anoMesFaturamentoGrupo
	                 }
	        );
		}
		
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
		AgendadorTarefas.agendarTarefa(
			"GerarDebitoACobrarCarroPipaBatch", this);
	}

}
