package gcom.relatorio.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.atendimentopublico.bean.PesquisaSatisfacaoHelper;
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

public class RelatorioPesquisaSatisfacaoAnalitico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioPesquisaSatisfacaoAnalitico(Usuario usuario){
		super(usuario, ConstantesRelatorios.RELATORIO_PESQUISA_SATISFACAO_ANALITICO);
	}
	
	public RelatorioPesquisaSatisfacaoAnalitico(){
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		//valor Retorno
		byte[] retorno = null;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		
		PesquisaSatisfacaoHelper helper = (PesquisaSatisfacaoHelper) getParametro("helperPesquisa");
		
		Collection relatorioBean = null;
		
		List beans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		relatorioBean = fachada.pesquisarPesquisaSatisfacaoRelatorioAnalitico(helper);
		
		if(Util.isVazioOrNulo(relatorioBean)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		if(!Util.isVazioOrNulo(relatorioBean)){
			Iterator iterator = relatorioBean.iterator();
			while(iterator.hasNext()){
				RelatorioPesquisaSatisfacaoAnaliticoBean analiticoBean = 
					(RelatorioPesquisaSatisfacaoAnaliticoBean) iterator.next();
				
				beans.add(analiticoBean);
			}
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		// adiciona os parâmetros do relatório
		//parametros.put("tipoFormatoRelatorio", String.valueOf(tipoFormatoRelatorio));
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("dataInicial", helper.getDataPeriodoInicial());
		parametros.put("dataFinal", helper.getDataPeriodoFinal());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_PESQUISA_SATISFACAO_ANALITICO;
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPesquisaSatisfacaoAnalitico", this);
	}

}
