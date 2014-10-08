package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1306] Gerar Resumo do Kit CAS de Serviço
 * Tarefa que manda para batch Gerar Resumo do Kit CAS de Serviço
 * 
 * @author Mariana Victor
 * @created 02/04/2012
 */
public class TarefaBatchGerarResumoKitCASServico extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoKitCASServico(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoKitCASServico() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection<Integer> colecaoIdsLocalidade = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

        Integer anoMesReferenciaFaturamento = (Integer) getParametro("anoMesReferenciaFaturamento"); 
        
		if (colecaoIdsLocalidade != null && !colecaoIdsLocalidade.isEmpty()){
			Iterator<Integer> iter = colecaoIdsLocalidade.iterator();
			
			while (iter.hasNext()) {
				Integer idLocalidade = (Integer) iter.next();
				
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_RESUMO_KIT_CAS_SERVICO_MDB,
					new Object[]{idLocalidade, this.getIdFuncionalidadeIniciada(), anoMesReferenciaFaturamento});
				
			}
		}
		
		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarResumoKitCASServicoBatch", this);
	}

}
