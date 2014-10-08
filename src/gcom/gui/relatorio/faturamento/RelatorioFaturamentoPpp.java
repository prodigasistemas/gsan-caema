package gcom.gui.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.financeiro.FiltrarResumoFaturamentoPpp;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1481] Gerar Relatório de Faturamento PPP
 * 
 * @author Jonathan Marcos
 * @date 29/05/2013
 */
public class RelatorioFaturamentoPpp extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioFaturamentoPpp(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FATURAMENTO_PPP);
	}
	
	@Deprecated
	public RelatorioFaturamentoPpp() {
		super(null, "");
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		// valor de retorno
				byte[] retorno = null;

				// ------------------------------------
				Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
				// ------------------------------------

				FiltrarResumoFaturamentoPpp filtro = 
						(FiltrarResumoFaturamentoPpp) getParametro("filtroResumoFaturamentoPpp");
				
				int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

				// coleção de beans do relatório
				List relatorioBeans = new ArrayList();

				Fachada fachada = Fachada.getInstancia();
				
				Collection<RelatorioFaturamentoPppHelper> colecao =   
					fachada.pesquisarGerarRelatorioFaturamentoPpp(filtro.getAnoMes(),filtro.getOpcaoTotalizacao(),
						filtro.getIdGerenciaRegional(), filtro.getIdUnidadeNegocio(), filtro.getIdSistema(),
							filtro.getIdSubSistemaEsgoto(), filtro.getIdLocalidade(), filtro.getIdMunicipio());

				// se a coleção de parâmetros da analise não for vazia
				if (colecao != null && !colecao.isEmpty()) {

					// coloca a coleção de parâmetros da analise no iterator
					Iterator colecaoIterator = colecao.iterator();

					// laço para criar a coleção de parâmetros da analise
					while (colecaoIterator.hasNext()) {

						RelatorioFaturamentoPppHelper helper = 
							(RelatorioFaturamentoPppHelper) colecaoIterator.next();
						
						RelatorioFaturamentoPppBean relatorioFaturamentoPppBean = 
							new RelatorioFaturamentoPppBean(helper);

						// adiciona o bean a coleção
						relatorioBeans.add(relatorioFaturamentoPppBean);				
						
						
					}
				}
				// __________________________________________________________________

				// Parâmetros do relatório
				Map parametros = new HashMap();
				
				// adiciona os parâmetros do relatório
				// adiciona o laudo da análise
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
				String nomeBusca = (String) getParametro("nomeBusca");
				String mesAnoRelatorio = filtro.getAnoMes().substring(4,6)+"/"+filtro.getAnoMes().substring(0,4);
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());
				parametros.put("tipoFormatoRelatorio", "R1481");
				parametros.put("mesAno",mesAnoRelatorio);
				
				if(opcaoTotalizacao.equals("gerenciaRegional")){
					opcaoTotalizacao = "Gerência Regional";
					parametros.put("nomeBuscaTipo", "Gerência Regional:");
					parametros.put("nomeBusca", nomeBusca);
				}else if(opcaoTotalizacao.equals("gerenciaRegionalLocalidade")){
					opcaoTotalizacao = "Gerência Regional por Localidade";
					parametros.put("nomeBuscaTipo", "Gerência Regional:");
					parametros.put("nomeBusca", nomeBusca);
					parametros.put("agrupaPorLocalidade", "1");
				}else if(opcaoTotalizacao.equals("localidade")){
					opcaoTotalizacao = "Localidade";
					parametros.put("nomeBuscaTipo", "Lacalidade:");
					parametros.put("nomeBusca", nomeBusca);
				}else if(opcaoTotalizacao.equals("municipio")){
					opcaoTotalizacao = "Município";
					parametros.put("nomeBuscaTipo", "Município:");
					parametros.put("nomeBusca", nomeBusca);
				}else if(opcaoTotalizacao.equals("unidadeNegocio")){
					opcaoTotalizacao = "Unidade Negócio";
					parametros.put("nomeBuscaTipo", "Unidade de Negócio:");
					parametros.put("nomeBusca", nomeBusca);
				}else if(opcaoTotalizacao.equals("subSistemaEsgoto")){
					opcaoTotalizacao = "Subsistema";
					parametros.put("nomeBuscaTipo", "Subsistema:");
					parametros.put("nomeBusca", nomeBusca);
				}else if(opcaoTotalizacao.equals("sistema")){
					opcaoTotalizacao = "Sistema";
					parametros.put("nomeBuscaTipo", "Sistema Esgoto:");
					parametros.put("nomeBusca", nomeBusca);
				}
				
				parametros.put("opcaoTotalizacao",opcaoTotalizacao);
				
				// cria uma instância do dataSource do relatório
				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
				
				if(Util.isVazioOrNulo(relatorioBeans)){
					
					// Não existem dados para a exibição do relatório.
					throw new ActionServletException("atencao.relatorio.vazio");
					
//					this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
//					
//					RelatorioAlteracoesSistemaColunaBean relatorioAlteracoesSistemaColunaBean = 
//							new RelatorioAlteracoesSistemaColunaBean();
//						
//						relatorioBeans.add(relatorioAlteracoesSistemaColunaBean);
//						
//					retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
//							parametros, ds, tipoFormatoRelatorio);
				
				}else{
					
					retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_FATURAMENTO_PPP,
						parametros, ds, tipoFormatoRelatorio);
									
				}
				
				

				// ------------------------------------
				// Grava o relatório no sistema
				try {
					persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_FATURAMENTO_PPP,
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
		
	}
}
