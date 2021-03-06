package gcom.relatorio.cadastro;

import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioAnaliseInconsistenciasatualizacaoCadastral extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;
	
	public RelatorioAnaliseInconsistenciasatualizacaoCadastral(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISE_INCONSISTESNCIAS_ATUALIZACAO_CADASTRAL);
	}
	
	@Deprecated
	public RelatorioAnaliseInconsistenciasatualizacaoCadastral(){
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
		//Valor de Retorno
		byte[] retorno = null;
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		String nomeGerencia = (String) getParametro("nomeGerencia");
		String codSetorInicial = (String) getParametro("codigoSetorInicial");
		String codSetorFinal = (String) getParametro("codigoSetorFinal");
		String quadraInicial = (String) getParametro("quadraInicial");
		String quadraFinal = (String) getParametro("quadraFinal");
		String cadastrador = (String) getParametro("cadastrador");
		String analista = (String) getParametro("analista");
		String periodoInicial = (String) getParametro("periodoInicial"); 
		String periodoFinal = (String) getParametro("periodoFinal");
		String localidadeInicial = (String) getParametro("localidadeInicial");
		String localidadeFinal = (String) getParametro("localidadeFinal");
		String tipoInconsistencia = (String) getParametro("tipoInconsistencia");
		
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		DadosResumoMovimentoAtualizacaoCadastralHelper helper = 
				(DadosResumoMovimentoAtualizacaoCadastralHelper) getParametro("dadosRelatorio");
		
		// cole��o de beans do relat�rio
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		Collection<RelatorioAnaliseInconsistenciasAtualizacaoCadastralBean> colResumos =
			Fachada.getInstancia().obterRelatorioAnaliseInconsistenciasAtualizacaoCadastral(helper);
		
		if(Util.isVazioOrNulo(colResumos)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}else{
			Iterator<?> iterator = colResumos.iterator();
			while(iterator.hasNext()){
				RelatorioAnaliseInconsistenciasAtualizacaoCadastralBean bean = 
					(RelatorioAnaliseInconsistenciasAtualizacaoCadastralBean) iterator.next();
				
				relatorioBeans.add(bean);
			}
		}
		
		//Parametros do Relatorio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relat�rio
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("nomeGerencia", nomeGerencia);
		parametros.put("codigoSetorInicial", codSetorInicial);
		parametros.put("codigoSetorFinal", codSetorFinal);
		parametros.put("quadraInicial", quadraInicial);
		parametros.put("quadraFinal", quadraFinal);
		parametros.put("cadastrador", cadastrador);
		parametros.put("analista", analista);
		parametros.put("periodoInicial", periodoInicial);
		parametros.put("periodoFinal", periodoFinal);
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		parametros.put("tipoInconsistencia", tipoInconsistencia);
		parametros.put("tipoFormatoRelatorio", "R1583");
		
		//Cria uma instancia do dataSource do Relatorio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ANALISE_INCONSISTESNCIAS_ATUALIZACAO_CADASTRAL, 
				parametros, ds, tipoFormatoRelatorio);
		
		//Retorna o relat�rio gerado
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnaliseInconsistenciasatualizacaoCadastral", this);
	}
	
}
