package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1539] Encerrar Ordem de Serviço Por Não Execução
 * 
 * @author Hugo Azevedo	
 * @date 21/08/2013
 * 
 */
public class TarefaBatchEncerrarOrdemServicoNaoExecucao extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchEncerrarOrdemServicoNaoExecucao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEncerrarOrdemServicoNaoExecucao() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		Usuario usuarioLogado = this.getUsuario();
		
		
		Collection<Integer> colecaoComandos = 
	        	(Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
		
		Integer idFaturamentoGrupo = (Integer) getParametro("idFaturamentoGrupo"); 
	        
			//b. Caso exista comando para o grupo e referência de faturamento informado
			if(colecaoComandos != null && colecaoComandos.size() > 0){

	
			            enviarMensagemControladorBatch(
			            		ConstantesJNDI.BATCH_ENCERRAR_OS_NAO_EXECUCAO,
			                    new Object[] {
			            			this.getIdFuncionalidadeIniciada(),	
			            			colecaoComandos,
			            			idFaturamentoGrupo,
			            			usuarioLogado
			                     }
			            );
			}
			
			//a. Caso não exista comando para execução
			else{
				enviarMensagemControladorBatch(
	            		ConstantesJNDI.BATCH_ENCERRAR_OS_NAO_EXECUCAO,
	                    new Object[] {
	            			this.getIdFuncionalidadeIniciada(),
	            			null,
	            			null,
	            			usuarioLogado
	            		});
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
		AgendadorTarefas.agendarTarefa("BatchEncerrarOrdemServicoNaoExecucao",this);

	}

}
