package gcom.batch.atendimentopublico;

import java.util.Collection;
import java.util.Iterator;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchAtualizarSituacaoLigacaoAguaImovelFiscalizado extends
		TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarSituacaoLigacaoAguaImovelFiscalizado(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarSituacaoLigacaoAguaImovelFiscalizado() {
		super(null, 0);
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
	public Object executar() throws TarefaException {
		Collection colecaoIdsLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Integer numeroDiasAtualizarLigacaoAguaImovelFiscalizado = (Integer) getParametro("diasAtualizarLigacaoAguaImovelFisc");
		
		if (colecaoIdsLocalidade != null && !colecaoIdsLocalidade.isEmpty()){
			Iterator iterator = colecaoIdsLocalidade.iterator();
		
			while (iterator.hasNext()) {
				Integer idLocalidade = ( (Integer) iterator.next() );
	
				System.out.println("Localidade ATUALIZAR SITUACAO LIGACAO AGUA IMOVEL FISCALIZADO "
								+ (idLocalidade)
								+ "*********************************************************");
	
				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_ATUALIZAR_SITUACAO_LIGACAO_AGUA_IMOVEL_FISCALIZADO,
						new Object[]{idLocalidade,
								this.getIdFuncionalidadeIniciada(), numeroDiasAtualizarLigacaoAguaImovelFiscalizado});
	
			}
		}
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("AtualizarSituacaoLigacaoAguaImovelFiscalizadoBatch",
				this);
	}

}
