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

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		
		RelatorioSituacaoEspecialFaturamentoBean relatorioSituacaoEspecialFaturamentoBean = null;

		Collection<RelatorioSituacaoEspecialFaturamentoHelper> colecao =  
				fachada.pesquisarRelatorioSituacaoEspecialFaturamento(filtro);
		
		// se a cole��o de par�metros da analise n�o for vazia				
		if (colecao != null && !colecao.isEmpty()) {		
			
			// coloca a cole��o de par�metros da analise no iterator
			Iterator<RelatorioSituacaoEspecialFaturamentoHelper> colecaoIterator = colecao.iterator();
		
			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {
			
				RelatorioSituacaoEspecialFaturamentoHelper helper = 
						(RelatorioSituacaoEspecialFaturamentoHelper) colecaoIterator.next();
											
				relatorioSituacaoEspecialFaturamentoBean = 				
						new RelatorioSituacaoEspecialFaturamentoBean(helper);
		
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioSituacaoEspecialFaturamentoBean);					
			}	
		}
		
		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap<String, String>();
				
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
				
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma inst�ncia do dataSource do relat�rio
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
