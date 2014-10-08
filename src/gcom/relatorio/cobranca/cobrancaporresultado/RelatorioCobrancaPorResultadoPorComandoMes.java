package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.RelatorioCobrancaPorResultadoPorComandoMesHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioCobrancaPorResultadoPorComandoMes extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioCobrancaPorResultadoPorComandoMes(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_COBRANCA_POR_RESULTADO_POR_COMANDO_MES);
		}

		@Deprecated
		public RelatorioCobrancaPorResultadoPorComandoMes() {
			super(null, "");
		}
		
		public Object executar() throws TarefaException {

			ArrayList<RelatorioCobrancaPorResultadoPorComandoMesHelper> colecaoRelatorioHelper = 
					(ArrayList<RelatorioCobrancaPorResultadoPorComandoMesHelper> ) getParametro("colecaoRelatorioHelper");
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
			Short exibeTotalizacaoGerencia = (Short) getParametro("exibeTotalizacaoGerencia");
			Short exibeMesAnoNaTotalizacao = (Short) getParametro("exibeMesAnoNaTotalizacao");
			Short filtroLocalidade = (Short)getParametro("filtroLocalidade");
			String encerramentoArrecadacao = (String)getParametro("encerramentoArrecadacao");
			String opcaoRelatorio = (String)getParametro("opcaoRelatorio"); 
			String periodo = (String)getParametro("periodo"); 

			// valor de retorno
			byte[] retorno = null;

			// Parâmetros do relatório
			Fachada fachada = Fachada.getInstancia();
			Map parametros = new HashMap();
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			
			//Recuperar o nome da empresa e numero do contrato
			RelatorioCobrancaPorResultadoPorComandoMesHelper helper = 
					(RelatorioCobrancaPorResultadoPorComandoMesHelper)Util.retonarObjetoDeColecao(colecaoRelatorioHelper);
			
			parametros.put("empresa", helper.getNomeEmpresa());
			parametros.put("contrato", helper.getNumeroContrato());
			parametros.put("encerramentoArrecadacao", encerramentoArrecadacao);
			
			String opcaoTotalizacao = "TOTALIZAÇÃO POR COMANDO";
			String descPeriodo = "Período de Apuração:";
			if(opcaoRelatorio.equals(ConstantesSistema.TOTALIZACAO_POR_MES_DE_APURACAO)){
				opcaoTotalizacao = "TOTALIZAÇÃO POR MÊS DE APURAÇÃO";
				descPeriodo = "Período Geração do Comando:";
			}
				
			parametros.put("opcaoTotalizacao", opcaoTotalizacao);
			parametros.put("codOpcaoTotalizacao", opcaoRelatorio);
			parametros.put("descPeriodo", descPeriodo);
			parametros.put("periodo", periodo);
			parametros.put("exibeTotalizacaoGerencia", exibeTotalizacaoGerencia.toString());
			parametros.put("exibeMesAnoNaTotalizacao", exibeMesAnoNaTotalizacao.toString());
			
			List<RelatorioCobrancaPorResultadoPorComandoMesBean> relatorioBeans = montarRelatorio(colecaoRelatorioHelper,filtroLocalidade,exibeMesAnoNaTotalizacao);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COBRANCA_POR_RESULTADO_POR_COMANDO_MES,
			parametros, ds, tipoFormatoRelatorio);
			
			return retorno;
		}
	    
		private ArrayList<RelatorioCobrancaPorResultadoPorComandoMesBean> montarRelatorio(
				ArrayList<RelatorioCobrancaPorResultadoPorComandoMesHelper> colecaoBoletins,
				Short filtroLocalidade, Short exibeMesAnoNaTotalizacao) {
			
			ArrayList<RelatorioCobrancaPorResultadoPorComandoMesBean> retorno = 
					new ArrayList<RelatorioCobrancaPorResultadoPorComandoMesBean>();
			
			List<RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean> colecaoSubRelatorioGerencia = 
					new ArrayList<RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean>();
			
			Iterator it = colecaoBoletins.iterator();
			int contador = 0;
			
			while(it.hasNext()){
				RelatorioCobrancaPorResultadoPorComandoMesBean bean = new RelatorioCobrancaPorResultadoPorComandoMesBean();
				
				RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean subBeanGerencia = new RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean();
				
				RelatorioCobrancaPorResultadoPorComandoMesHelper helper = (RelatorioCobrancaPorResultadoPorComandoMesHelper)it.next();
				
				RelatorioCobrancaPorResultadoPorComandoMesHelper helperNext = null;
				if(contador != colecaoBoletins.size() - 1){
					helperNext = colecaoBoletins.get(contador + 1);
				}
				
				if(filtroLocalidade == ConstantesSistema.SIM){
					bean.setIdLocalidade(helper.getIdLocalidade());
					bean.setDescLocalidade(helper.getDescLocalidade());
					bean.setDescGerenciaRegional(helper.getDescGerenciaRegional());
					bean.setIdUnidadeNegocio(helper.getIdUnidadeNegocio());
					bean.setDescUnidadeNegocio(helper.getDescUnidadeNegocio());
				}
				else{
					bean.setIdMunicipio(helper.getIdMunicipio());
					bean.setDescMunicipio(helper.getDescMunicipio());
					bean.setDescMicroregiao(helper.getDescMicroregiao());
					bean.setDescRegiao(helper.getDescRegiao());
				}
				
				bean.setAnoMesRef(Util.formatarAnoMesParaMesAno(helper.getAnoMesRef()));
				
				bean.setDescFaixaContas(helper.getDescFaixaContas());
				bean.setQtdeClientes(helper.getQtdeClientes());
				bean.setQtdContas(helper.getQtdContas());
				
				bean.setQtdePagtoAVista(helper.getQtdePagtoAVista());
				bean.setQtdePagtoParcelado(helper.getQtdePagtoParcelado());
				int qtdePagtoTotal = (helper.getQtdePagtoAVista().intValue() + helper.getQtdePagtoParcelado().intValue());
				bean.setQtdePagtoTotal(qtdePagtoTotal);
				
				if(helper.getVlDesconto() != null)
					bean.setVlDesconto(helper.getVlDesconto());
				else
					bean.setVlDesconto(new BigDecimal("0"));
				
				if(helper.getVlPagtoAVista() != null)
					bean.setVlPagtoAVista(helper.getVlPagtoAVista());
				else
					bean.setVlPagtoAVista(new BigDecimal("0"));
				
				if(helper.getVlPagtoParcelado() != null)
					bean.setVlPagtoParcelado(helper.getVlPagtoParcelado());
				else
					bean.setVlPagtoParcelado(new BigDecimal("0"));
				
				if(helper.getVlTotalDivida() != null)
					bean.setVlTotalDivida(helper.getVlTotalDivida());
				else
					bean.setVlTotalDivida(new BigDecimal("0"));
				
				
				BigDecimal valorAVistaMenosDesconto = Util.subtrairBigDecimal(bean.getVlPagtoAVista(), bean.getVlDesconto()); 
				
				bean.setVlPagtoAVista(valorAVistaMenosDesconto);
				bean.setVlPagtoTotal(Util.somaBigDecimal(valorAVistaMenosDesconto, bean.getVlPagtoParcelado()));
				bean.setPercentualFaixa(helper.getPercentualFaixa());
				bean.setVlPagtoAVistaPrestadora((valorAVistaMenosDesconto.multiply(bean.getPercentualFaixa())).divide(new BigDecimal("100"),2,BigDecimal.ROUND_UP));
				bean.setVlPagtoParceladoPrestadora((bean.getVlPagtoParcelado().multiply(bean.getPercentualFaixa())).divide(new BigDecimal("100"),2,BigDecimal.ROUND_UP));
				bean.setVlPagtoTotalPrestadora(Util.somaBigDecimal(bean.getVlPagtoAVistaPrestadora(), bean.getVlPagtoParceladoPrestadora()));
											
				bean.setIdComando(helper.getIdComando());
				bean.setDataExecucao(Util.formatarData(helper.getDataExecucao()));
				bean.setDataInicio(Util.formatarData(helper.getDataInicio()));
				bean.setDataFinal(Util.formatarData(helper.getDataFinal()));
				
				subBeanGerencia = atualizarDadosSubrelatorio(bean, subBeanGerencia, helper,exibeMesAnoNaTotalizacao);
				
				if(filtroLocalidade == ConstantesSistema.SIM){

					if(helperNext == null || (helper.getIdGerenciaRegional().intValue() != helperNext.getIdGerenciaRegional().intValue())){
						
						colecaoSubRelatorioGerencia = adicionarDadosSubGerencia(colecaoSubRelatorioGerencia, bean, subBeanGerencia,"Total Gerência Regional:");
						
					}else {
						subBeanGerencia.setMsgTotal("Total Gerência Regional:");	
						colecaoSubRelatorioGerencia.add(subBeanGerencia);
					}
						
				}else{
						
					if(helperNext == null || (helper.getIdRegiao().intValue() != helperNext.getIdRegiao().intValue())){
						
						colecaoSubRelatorioGerencia = adicionarDadosSubGerencia(colecaoSubRelatorioGerencia, bean, subBeanGerencia, "Total Região:");
						
					}else{
						subBeanGerencia.setMsgTotal("Total Região:");
						colecaoSubRelatorioGerencia.add(subBeanGerencia);
					}
				}
				
				contador++;
				retorno.add(bean);
			}
			
			return retorno;
		}

		private RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean atualizarDadosSubrelatorio(
				RelatorioCobrancaPorResultadoPorComandoMesBean bean,
				RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean subBean,
				RelatorioCobrancaPorResultadoPorComandoMesHelper helper,
				Short exibeMesAnoNaTotalizacao) {
			
			subBean.setIdFaixaContas(helper.getIdFaixaContas());
			subBean.setDescFaixaContas(bean.getDescFaixaContas());
			subBean.setQtdeClientes(bean.getQtdeClientes());
			subBean.setQtdContas(bean.getQtdContas());
			subBean.setVlTotalDivida(bean.getVlTotalDivida());
			subBean.setVlPagtoAVista(bean.getVlPagtoAVista());
			subBean.setVlPagtoParcelado(bean.getVlPagtoParcelado());
			subBean.setPercentualFaixa(bean.getPercentualFaixa());
			subBean.setVlPagtoTotal(bean.getVlPagtoTotal());
			subBean.setVlPagtoAVistaPrestadora(bean.getVlPagtoAVistaPrestadora());
			subBean.setVlPagtoParceladoPrestadora(bean.getVlPagtoParceladoPrestadora());
			subBean.setVlPagtoTotalPrestadora(bean.getVlPagtoTotalPrestadora());
			subBean.setVlDesconto(bean.getVlDesconto());
			subBean.setQtdePagtoAVista(bean.getQtdePagtoAVista());
			subBean.setQtdePagtoParcelado(bean.getQtdePagtoParcelado());
		
			subBean.setQtdePagtoTotal(bean.getQtdePagtoTotal());
			
			if(exibeMesAnoNaTotalizacao.equals(ConstantesSistema.SIM)){
				subBean.setAnoMesRef(bean.getAnoMesRef());
			}else{
				subBean.setAnoMesRef("0");
			}
			
			return subBean;
		}

		private List<RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean> adicionarDadosSubGerencia(
				List<RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean> colecaoSubRelatorioGerencia,
				RelatorioCobrancaPorResultadoPorComandoMesBean bean,
				RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean subBeanGerencia,
				String msgTotal) {
			subBeanGerencia.setMsgTotal(msgTotal);
			colecaoSubRelatorioGerencia.add(subBeanGerencia);
			
			//Re-Ordenar pela faixa
			Collections.sort(colecaoSubRelatorioGerencia);
			
			bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
			colecaoSubRelatorioGerencia = new ArrayList<RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean>();
			return colecaoSubRelatorioGerencia;
		}
	
		@Override
		public int calcularTotalRegistrosRelatorio() {
			return 2;
		}

		public void agendarTarefaBatch() {
		}
}
