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

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioClienteReceitaFederalBean relatorioClienteReceitaFederalBean = null;
		Collection colecao = null;
		
		if ((colecaoClientesRelatorio != null) && (!colecaoClientesRelatorio.isEmpty())){
			colecao = fachada.gerarRelatorioClientesValidadosReceitaFederal(colecaoClientesRelatorio);
		}

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// la�o para criar a cole��o de par�metros da analise
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

				// adiciona o bean a cole��o
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

					// adiciona o bean a cole��o
					relatorioBeans.add(relatorioClienteReceitaFederalBean);
			}
		}
		
		if ((colecao != null) && (colecao.isEmpty()) && 
		(colecaoClienteErroValidacao != null) && (colecaoClienteErroValidacao.isEmpty())){
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VALIDAR_CLIENTE_RECEITA_FEDERAL,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_VALIDAR_CLIENTE_RECEITA_FEDERAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
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
