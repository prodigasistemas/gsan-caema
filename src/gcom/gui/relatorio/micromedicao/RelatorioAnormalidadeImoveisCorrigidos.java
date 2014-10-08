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
	 * [UC1434] Gerar Relat�rio Anormalidade de Im�veis Corrigidos.
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

				// cole��o de beans do relat�rio
				List relatorioBeans = new ArrayList();

				Fachada fachada = Fachada.getInstancia();
				
				Collection<RelatorioAnormalidadeImoveisCorrigidosHelper> colecao =   
					fachada.pesquisarRelatorioAnormalidadeImoveisCorrigidos(filtro);

				// se a cole��o de par�metros da analise n�o for vazia
				if (colecao != null && !colecao.isEmpty()) {

					// coloca a cole��o de par�metros da analise no iterator
					Iterator colecaoIterator = colecao.iterator();

					// la�o para criar a cole��o de par�metros da analise
					while (colecaoIterator.hasNext()) {

						RelatorioAnormalidadeImoveisCorrigidosHelper helper = 
							(RelatorioAnormalidadeImoveisCorrigidosHelper) colecaoIterator.next();
						
						RelatorioAnormalidadeImoveisCorrigidosBean relatorioAnormalidadeImoveisCorrigidosBean = 
							new RelatorioAnormalidadeImoveisCorrigidosBean(helper);

						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioAnormalidadeImoveisCorrigidosBean);				
						
						
					}
				}
				// __________________________________________________________________

				// Par�metros do relat�rio
				Map parametros = new HashMap();
				
				// adiciona os par�metros do relat�rio
				// adiciona o laudo da an�lise
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());
				parametros.put("tipoFormatoRelatorio", "R1434");
				parametros.put("periodoInicial", filtro.getPeriodoDiaMesAnoInicial());
				parametros.put("periodoFinal", filtro.getPeriodoDiaMesAnoFinal());
				
				// cria uma inst�ncia do dataSource do relat�rio
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
				// Grava o relat�rio no sistema
				try {
					persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ANORMALIDADE_IMOVEIS_CORRIGIDOS,
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
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnormalidadeImoveisCorrigidos", this);
		
	}

}
