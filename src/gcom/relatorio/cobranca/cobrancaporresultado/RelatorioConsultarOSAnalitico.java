package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1238] Gerar Relatório de Acompanhamento dos Comandos de Cobrança
 * 
 * @author Mariana Victor
 * @date 08/11/2011
 */
public class RelatorioConsultarOSAnalitico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioConsultarOSAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MOVIMENTAR_OS_CONSULTAR_ANALITICO);
	}

	
	public RelatorioConsultarOSAnalitico() {
		super(null, "");
	}
	
public Object executar() throws TarefaException {
		
        Fachada fachada = Fachada.getInstancia();
        
		String idComando = (String) getParametro("idComando");
		String[] idsUnidadeNegocio = (String[]) getParametro("idsUnidadeNegocio");
		String[] idsGerenciaRegional = (String[]) getParametro("idsGerenciaRegional");
		String[] idsLocalidade = (String[]) getParametro("idsLocalidade");
		String situacaoOS = (String) getParametro("situacaoOS");
		String dataGeracaoInicialOS = (String) getParametro("dataGeracaoInicialOS");
		String dataGeracaoFinalOS = (String) getParametro("dataGeracaoFinalOS");
		String dataEncerramentoInicialOS = (String) getParametro("dataEncerramentoInicialOS");
		String dataEncerramentoFinalOS = (String) getParametro("dataEncerramentoFinalOS");

		
		//
		List<RelatorioConsultarOSBean> colecaoBean = fachada
				.obterDadosConsultarOSAnaliticoComandosCobranca(idComando,
																idsUnidadeNegocio,
																idsGerenciaRegional,
																idsLocalidade,
																situacaoOS,
																dataGeracaoInicialOS,
															    dataGeracaoFinalOS,
															    dataEncerramentoInicialOS,
															    dataEncerramentoFinalOS);
		
		//Nenhum resultado
		if(colecaoBean == null || colecaoBean.size() == 0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// valor de retorno
		byte[] retorno = null;
		
		//objeto do Comando
		ComandoEmpresaCobrancaConta cecc = fachada.obterComandoEmpresaCobrancaConta(new Integer(idComando)); 
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("codigoCobranca", idComando);
		parametros.put("empresa", cecc.getEmpresa().getDescricao());
		parametros.put("dataExecucao", Util.formatarData(cecc.getDataExecucao()));
		
		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MOVIMENTAR_OS_CONSULTAR_ANALITICO,
								parametros, ds, TarefaRelatorio.TIPO_PDF);
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return -1;
	}

	public void agendarTarefaBatch() {
		//AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoComandosCobranca", this);
	}

}
