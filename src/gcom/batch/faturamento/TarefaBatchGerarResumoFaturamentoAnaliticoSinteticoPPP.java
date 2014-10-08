package gcom.batch.faturamento;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1479] - Gerar Resumo do Faturamento Analítico PPP 
 * [UC1476] - Gerar Resumo do Faturamento Sintético PPP
 * 
 * @author Maxwell Moreira
 * @date 28/05/2013
 */

public class TarefaBatchGerarResumoFaturamentoAnaliticoSinteticoPPP extends TarefaBatch{
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoFaturamentoAnaliticoSinteticoPPP(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoFaturamentoAnaliticoSinteticoPPP() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {
        

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_SINTETICO_PPP,
                    new Object[] {this.getIdFuncionalidadeIniciada() });
                            

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
		AgendadorTarefas.agendarTarefa("BatchGerarResumoFaturamentoAnaliticoSintetico",
				this);
	}

}
