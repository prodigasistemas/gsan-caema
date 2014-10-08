package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchAtualizarContasValoresEsgotoPPP extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarContasValoresEsgotoPPP(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarContasValoresEsgotoPPP() {
		super(null, 0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object executar() throws TarefaException {
		
		Usuario usuarioLogado = this.getUsuario();
		
		 Collection<Integer> colecaoIdsSetores = 
	        	(Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
	        
	        Iterator<Integer> iterator = colecaoIdsSetores.iterator();

	        while (iterator.hasNext()) {

	            Integer idSetor = iterator.next();

	            enviarMensagemControladorBatch(
	            		ConstantesJNDI.BATCH_ATUALIZAR_CONTAS_VALORES_ESGOTO_PPP,
	                    new Object[] {
	            			this.getIdFuncionalidadeIniciada(),	
	            			idSetor,
	            			usuarioLogado
	                     }
	            );
	                            
	        }

	        return null;
	}

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
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchAtualizarContasValoresEsgotoPPP",this);
	}

}
