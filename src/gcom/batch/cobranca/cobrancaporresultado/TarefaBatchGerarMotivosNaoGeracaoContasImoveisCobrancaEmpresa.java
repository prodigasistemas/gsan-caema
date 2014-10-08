package gcom.batch.cobranca.cobrancaporresultado;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1321] Gerar Motivos Não Geração de Contas e Imóveis em Cobrança por Empresa
 * [SB0010] Atualizar Comando de Cobrança
 * 
 * @author Hugo Azevedo
 * @date 02/05/2012
 */
public class TarefaBatchGerarMotivosNaoGeracaoContasImoveisCobrancaEmpresa extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarMotivosNaoGeracaoContasImoveisCobrancaEmpresa(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarMotivosNaoGeracaoContasImoveisCobrancaEmpresa() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Usuario usuario = (Usuario) getParametro("usuario");
		
		Collection<Integer> colecaoLocalidades = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Collection<Object[]> colecaoComandoEmpresaCobrancaConta = (Collection<Object[]>) getParametro("colecaoComandos");
		
		Iterator iterator = colecaoLocalidades.iterator();

		while (iterator.hasNext()) {
				
			Integer idLocalidade = (Integer)iterator.next();
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_MOTIVOS_NAO_GERACAO_CONTAS_IMOVEIS_COB_EMPRESA,
						new Object[] {
									usuario,
									this.getIdFuncionalidadeIniciada(),
									idLocalidade,
									colecaoComandoEmpresaCobrancaConta
									});
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
		AgendadorTarefas.agendarTarefa("GerarMotivosNaoGeracaoContasImoveisCobrancaEmpresaBatch", this);
	}

}
