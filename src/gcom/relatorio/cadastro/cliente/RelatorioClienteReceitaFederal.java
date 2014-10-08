package gcom.relatorio.cadastro.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * 
 * [UC1429] Filtrar Cliente para Validar na Base da Receita Federal.
 * 
 * @author Maxwell Moreira
 *
 * @date 25/01/2011
 * 
 */

public class RelatorioClienteReceitaFederal extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;
	
	public RelatorioClienteReceitaFederal(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_VALIDAR_CLIENTE_RECEITA_FEDERAL);
	}

	@Deprecated
	public RelatorioClienteReceitaFederal() {
		super(null, "");
	}
	
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioClienteReceitaFederalHelper relatorioHelper = 
			(FiltrarRelatorioClienteReceitaFederalHelper) getParametro("filtrarRelatorioClienteReceitaFederalHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Collection colecaoClientesRelatorio = (Collection) getParametro("colecaoClientesRelatorio");
		Collection colecaoClienteErroValidacao = (Collection) getParametro("colecaoClienteErroValidacao");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioClienteReceitaFederalBean relatorioClienteReceitaFederalBean = null;
		Collection colecao = null;
		
		if ((colecaoClientesRelatorio != null) && (!colecaoClientesRelatorio.isEmpty())){
			colecao = fachada.gerarRelatorioClientesValidadosReceitaFederal(colecaoClientesRelatorio);
		}

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioClienteReceitaFederalHelper helper = 
					(RelatorioClienteReceitaFederalHelper) colecaoIterator.next();
				
				relatorioClienteReceitaFederalBean = 
					new RelatorioClienteReceitaFederalBean(
							
							helper.getId(),
							helper.getNome(),
							helper.getDocumento(),
							helper.getMensagem(),
							helper.getErroValidacao());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioClienteReceitaFederalBean);				
			}
		}
		
		if ((colecaoClienteErroValidacao != null) && (!colecaoClienteErroValidacao.isEmpty())){
			
			Iterator colecaoIteratorErroValidacao = colecaoClienteErroValidacao.iterator();
			
			while (colecaoIteratorErroValidacao.hasNext()) {
				RelatorioClienteReceitaFederalHelper helperErroValidacao = 
						(RelatorioClienteReceitaFederalHelper) colecaoIteratorErroValidacao.next();
					
					relatorioClienteReceitaFederalBean = 
						new RelatorioClienteReceitaFederalBean(
								
								helperErroValidacao.getId(),
								helperErroValidacao.getNome(),
								helperErroValidacao.getDocumento(),
								helperErroValidacao.getMensagem(),
								helperErroValidacao.getErroValidacao());

					// adiciona o bean a coleção
					relatorioBeans.add(relatorioClienteReceitaFederalBean);
			}
		}
		
		if ((colecao != null) && (colecao.isEmpty()) && 
		(colecaoClienteErroValidacao != null) && (colecaoClienteErroValidacao.isEmpty())){
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VALIDAR_CLIENTE_RECEITA_FEDERAL,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_VALIDAR_CLIENTE_RECEITA_FEDERAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioClienteReceitaFederal", this);
	}
}
