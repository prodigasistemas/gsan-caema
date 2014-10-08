package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarArquivoTextoCodigoBarrasDocumentoCobranca extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarArquivoTextoCodigoBarrasDocumentoCobranca(Usuario usuario, 
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarArquivoTextoCodigoBarrasDocumentoCobranca(){
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

	@Override
	public Object executar() throws TarefaException {
		
		String emailEmpresa = (String) getParametro("emailEmpresa");
		Collection<?> colecaoImoveis = (Collection<?>) getParametro("colecaoImoveis");
		
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_CODIGO_BARRAS_DOCUMENTO_COBRANCA, 
				new Object [] { 
						this.getIdFuncionalidadeIniciada(), 
						emailEmpresa,
						colecaoImoveis });
		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarArquivoTextoCodigoBarrasDocumentoCobrancaBatch", this);

	}
	
}
