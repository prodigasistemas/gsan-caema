package gcom.batch.atendimentopublico;

import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1535] Gerar Ordens de Serviço Factível Faturável
 * 
 * @author Hugo Azevedo	
 * @date 15/08/2013
 * 
 */
public class TarefaBatchGerarOrdensServicoFactivelFaturavel extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarOrdensServicoFactivelFaturavel(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarOrdensServicoFactivelFaturavel() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		Usuario usuarioLogado = this.getUsuario();
		
		
		Collection<Integer> colecaoComandos = 
	        	(Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
		
		Integer idFaturamentoGrupo = (Integer) getParametro("idFaturamentoGrupo"); 
	       
        enviarMensagemControladorBatch(
        		ConstantesJNDI.BATCH_GERAR_OS_FACTIVEL_FATURAVEL,
                new Object[] {
        			this.getIdFuncionalidadeIniciada(),	
        			colecaoComandos,
        			idFaturamentoGrupo,
        			usuarioLogado
                 }
        );
		
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
		AgendadorTarefas.agendarTarefa("BatchGerarOrdensServicoFactivelFaturavel",this);

	}

}
