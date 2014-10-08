package gcom.relatorio.faturamento;

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
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;

public class RelatorioSituacaoEspecialFaturamento extends TarefaRelatorio {
	
    private static final long serialVersionUID = 1L;
	
	public RelatorioSituacaoEspecialFaturamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SITUACAO_ESPECIAL_FATURAMENTO);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();	
		
		FiltrarRelatorioSituacaoEspecialFaturamentoHelper filtro = 
				(FiltrarRelatorioSituacaoEspecialFaturamentoHelper) getParametro("filtrarRelatorioSituacaoEspecialFaturamentoHelper");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		
		RelatorioSituacaoEspecialFaturamentoBean relatorioSituacaoEspecialFaturamentoBean = null;

		Collection<RelatorioSituacaoEspecialFaturamentoHelper> colecao =  
				fachada.pesquisarRelatorioSituacaoEspecialFaturamento(filtro);
		
		// se a coleção de parâmetros da analise não for vazia				
		if (colecao != null && !colecao.isEmpty()) {		
			
			// coloca a coleção de parâmetros da analise no iterator
			Iterator<RelatorioSituacaoEspecialFaturamentoHelper> colecaoIterator = colecao.iterator();
		
			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {
			
				RelatorioSituacaoEspecialFaturamentoHelper helper = 
						(RelatorioSituacaoEspecialFaturamentoHelper) colecaoIterator.next();
											
				relatorioSituacaoEspecialFaturamentoBean = 				
						new RelatorioSituacaoEspecialFaturamentoBean(helper);
		
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioSituacaoEspecialFaturamentoBean);					
			}	
		}
		
		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap<String, String>();
				
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
				
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_SITUACAO_ESPECIAL_FATURAMENTO,
		parametros, ds, tipoFormatoRelatorio);
		
		return retorno;
	}
	

	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub
		
	}

}
