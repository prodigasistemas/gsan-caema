package gcom.gui.relatorio.micromedicao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.seguranca.RelatorioAlteracoesSistemaColunaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioAnormalidadeImoveisCorrigidos extends TarefaRelatorio {

	/**
	 * [UC1434] Gerar Relatório Anormalidade de Imóveis Corrigidos.
	 * 
	 * @author Jonathan Marcos
	 *
	 * @date 06/02/2013
	 */

	private static final long serialVersionUID = 1L;

	public RelatorioAnormalidadeImoveisCorrigidos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANORMALIDADE_IMOVEIS_CORRIGIDOS);
	}
	
	@Deprecated
	public RelatorioAnormalidadeImoveisCorrigidos() {
		super(null, "");
	}

	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarRelatorioAnormalidadeImoveisCorrigidosHelper filtro = 
				(FiltrarRelatorioAnormalidadeImoveisCorrigidosHelper) getParametro("filtrarRelatorioAnormalidadeImoveisCorrigidosHelper");
		
		Integer pesquisaCount = fachada.pesquisarRelatorioAnormalidadeImoveisCorrigidosCount(filtro);
		
		
		
		return pesquisaCount;
	}

	@Override
	public Object executar() throws TarefaException {
		// valor de retorno
				byte[] retorno = null;

				// ------------------------------------
				Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
				// ------------------------------------

				FiltrarRelatorioAnormalidadeImoveisCorrigidosHelper filtro = 
					(FiltrarRelatorioAnormalidadeImoveisCorrigidosHelper) getParametro("filtrarRelatorioAnormalidadeImoveisCorrigidosHelper");
				
				int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

				// coleção de beans do relatório
				List relatorioBeans = new ArrayList();

				Fachada fachada = Fachada.getInstancia();
				
				Collection<RelatorioAnormalidadeImoveisCorrigidosHelper> colecao =   
					fachada.pesquisarRelatorioAnormalidadeImoveisCorrigidos(filtro);

				// se a coleção de parâmetros da analise não for vazia
				if (colecao != null && !colecao.isEmpty()) {

					// coloca a coleção de parâmetros da analise no iterator
					Iterator colecaoIterator = colecao.iterator();

					// laço para criar a coleção de parâmetros da analise
					while (colecaoIterator.hasNext()) {

						RelatorioAnormalidadeImoveisCorrigidosHelper helper = 
							(RelatorioAnormalidadeImoveisCorrigidosHelper) colecaoIterator.next();
						
						RelatorioAnormalidadeImoveisCorrigidosBean relatorioAnormalidadeImoveisCorrigidosBean = 
							new RelatorioAnormalidadeImoveisCorrigidosBean(helper);

						// adiciona o bean a coleção
						relatorioBeans.add(relatorioAnormalidadeImoveisCorrigidosBean);				
						
						
					}
				}
				// __________________________________________________________________

				// Parâmetros do relatório
				Map parametros = new HashMap();
				
				// adiciona os parâmetros do relatório
				// adiciona o laudo da análise
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());
				parametros.put("tipoFormatoRelatorio", "R1434");
				parametros.put("periodoInicial", filtro.getPeriodoDiaMesAnoInicial());
				parametros.put("periodoFinal", filtro.getPeriodoDiaMesAnoFinal());
				
				// cria uma instância do dataSource do relatório
				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
				
				if(Util.isVazioOrNulo(relatorioBeans)){
					
					this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
					
					RelatorioAlteracoesSistemaColunaBean relatorioAlteracoesSistemaColunaBean = 
							new RelatorioAlteracoesSistemaColunaBean();
						
						relatorioBeans.add(relatorioAlteracoesSistemaColunaBean);
						
					retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
							parametros, ds, tipoFormatoRelatorio);
				
				}else{
					
					retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ANORMALIDADE_IMOVEIS_CORRIGIDOS,
						parametros, ds, tipoFormatoRelatorio);
									
				}
				
				

				// ------------------------------------
				// Grava o relatório no sistema
				try {
					persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ANORMALIDADE_IMOVEIS_CORRIGIDOS,
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
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnormalidadeImoveisCorrigidos", this);
		
	}

}
