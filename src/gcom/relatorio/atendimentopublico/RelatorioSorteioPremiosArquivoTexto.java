package gcom.relatorio.atendimentopublico;

import gcom.cadastro.Sorteios;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;

/**
 * [UC1295] Efetuar Sorteio de Prêmios
 * 
 * @author Mariana Victor
 * @since 09/03/2012
 */
public class RelatorioSorteioPremiosArquivoTexto extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioSorteioPremiosArquivoTexto(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Deprecated
	public RelatorioSorteioPremiosArquivoTexto() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		Integer idSorteio = new Integer((String) getParametro("idSorteio"));
		
		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		//Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Fachada fachada = Fachada.getInstancia();
		
		String numeroSorteioFiqueLegal2013 = (String) getParametro("numeroSorteioFiqueLegal2013");
		
		Collection<RelatorioSorteioPremiosHelper> colecaoHelper = 
				new ArrayList<RelatorioSorteioPremiosHelper>();
		
		// 2.1.	Caso o tipo do sorteio seja "Sorteio para Adimplentes":
		if (idSorteio.compareTo(Sorteios.SORTEIO_ADIMPLENTES) == 0) {
			
			colecaoHelper = 
					fachada.pesquisarDadosRelatorioImoveisSorteados();
			
		// 2.2.	Caso Contrário, caso o tipo do sorteio seja "Sorteio Fique Legal"
		} else if (idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL) == 0) {
			
			colecaoHelper = 
					fachada.pesquisarDadosRelatorioImoveisSorteadosFiqueLegal();
			
		// 2.3.	Caso contrário, ou seja, "Sorteio Mensal":
		} else if (idSorteio.compareTo(Sorteios.SORTEIO_MENSAL) == 0) {
			
			colecaoHelper = 
					fachada.pesquisarDadosRelatorioImoveisSorteadosMensal();
			
			
		} else if(idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL_2013) == 0){
			colecaoHelper = 
					fachada.obterDadosRelatorioSorteioFiqueLegal2013(numeroSorteioFiqueLegal2013);
		}
		
		retorno = fachada.emitirRelatorioSorteioPremiosArquivoTexto(colecaoHelper);

		// ------------------------------------
		// Grava o relatório no sistema
		/*try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}*/
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;
   
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioSorteioPremiosArquivoTexto", this);
	}

}
