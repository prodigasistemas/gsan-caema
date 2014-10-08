package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.bean.PesquisaSatisfacaoHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
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

/**
 * classe responsável por criar o relatório de certidao negativa
 * 
 * @author Davi Menezes
 * @created 29/02/2012
 */
public class RelatorioPesquisaSatisfacaoSintentico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioPesquisaSatisfacaoSintentico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PESQUISA_SATISFACAO_SINTETICO);
	}
	
	public RelatorioPesquisaSatisfacaoSintentico(){
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	@Override
	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;
		
		//Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		//Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		PesquisaSatisfacaoHelper helper = (PesquisaSatisfacaoHelper) getParametro("helperPesquisa");
		
		Collection relatorioBeans = null;
		
		List beans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		relatorioBeans = fachada.pesquisarPesquisaSatisfacao(helper);
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		if(!Util.isVazioOrNulo(relatorioBeans)){
			Iterator iterator = relatorioBeans.iterator();
			while(iterator.hasNext()){
				RelatorioPesquisaSatisfacaoSinteticoBean pesquisaBean = (RelatorioPesquisaSatisfacaoSinteticoBean) 
						iterator.next();
				
				beans.add(pesquisaBean);
			}
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		//parametros.put("tipoFormatoRelatorio", String.valueOf(tipoFormatoRelatorio));
		parametros.put("dataInicial", helper.getDataPeriodoInicial());
		parametros.put("dataFinal", helper.getDataPeriodoFinal());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_PESQUISA_SATISFACAO_SINTETICO;
		
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
		AgendadorTarefas.agendarTarefa("RelatorioPesquisaSatisfacaoSintetico", this);
	}

}
