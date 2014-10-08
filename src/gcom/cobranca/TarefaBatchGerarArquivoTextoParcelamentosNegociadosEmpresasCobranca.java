package gcom.cobranca;

import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.empresa.EmpresaCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchGerarArquivoTextoParcelamentosNegociadosEmpresasCobranca extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarArquivoTextoParcelamentosNegociadosEmpresasCobranca(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);		
	}
	
	@Deprecated
	public TarefaBatchGerarArquivoTextoParcelamentosNegociadosEmpresasCobranca() {
		super(null, 0);
	}		

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {	
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {		
		return null;
	}

	public Object executar() throws TarefaException {		
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_PARCELAMENTOS_NEGOCIADOS_EMPRESAS_COBRANCA,
				new Object[] { this.getIdFuncionalidadeIniciada() });
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarArquivoTextoOParcelamentosNegociadosEmpresasCobranca", this);		
	}

}
