package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
 * 
 * @author Arthur Carvalho
 * @created 27/12/2011
 */
public class TarefaBatchAtualizarDadosCadastraisDoImovelPeloRecadastramento extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarDadosCadastraisDoImovelPeloRecadastramento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarDadosCadastraisDoImovelPeloRecadastramento() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection<Integer> colecaoIdsLocalidade = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		if (colecaoIdsLocalidade != null && !colecaoIdsLocalidade.isEmpty()){
			Iterator<Integer> iter = colecaoIdsLocalidade.iterator();
			
			while (iter.hasNext()) {
				Integer idLocalidade = (Integer) iter.next();
				
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ATUALIZAR_DADOS_DO_IMOVEL_PELO_RECADASTRAMENTRO_MDB,
					new Object[]{idLocalidade, this.getIdFuncionalidadeIniciada()});
				
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
		AgendadorTarefas.agendarTarefa("AtualizarDadosCadastraisDoImovelPeloRecadastramentoBatch", this);
	}

}
