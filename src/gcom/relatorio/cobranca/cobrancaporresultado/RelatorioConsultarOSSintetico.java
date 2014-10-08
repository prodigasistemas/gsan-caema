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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 * 
 * @author Hugo Azevedo
 * @date 11/06/2012
 */
public class RelatorioConsultarOSSintetico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioConsultarOSSintetico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MOVIMENTAR_OS_CONSULTAR_SINTETICO);
	}


	public RelatorioConsultarOSSintetico() {
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
					.obterDadosConsultarOSSinteticoComandosCobranca(idComando,
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
			
			// 4.6.1. incluir no cabeçalho o período solicitado no filtro (item 4.2.5) caso tenha sido informado
			if(dataGeracaoInicialOS != null && !dataGeracaoInicialOS.equals("") && 
			   dataGeracaoFinalOS != null && !dataGeracaoFinalOS.equals("")){
				parametros.put("periodoSolicitado", dataGeracaoInicialOS + " a " + dataGeracaoFinalOS);
			}

			//4.6.1. ou Data de Execução do Comando + Data Corrente.
			else{
				parametros.put("periodoSolicitado", Util.formatarData(cecc.getDataExecucao()) + " a " + Util.formatarData(new Date()));
			}
				
			
			
			RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MOVIMENTAR_OS_CONSULTAR_SINTETICO,
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
