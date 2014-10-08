package gcom.relatorio.cobranca;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIGlobalBinding;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.RelatorioDevolucaoPagamentosDuplicidadeBean;
import gcom.relatorio.faturamento.RelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * 
 * [UC1461] Emitir Resumo do Parcelamento Judicial
 * 
 * @author Maxwell Moreira
 *
 * @date 11/04/2013
 */

public class RelatorioEmitirResumoParcelamentoJudicial extends TarefaRelatorio {
	
    private static final long serialVersionUID = 1L;
	
	public RelatorioEmitirResumoParcelamentoJudicial(Usuario usuario){
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_RESUMO_PARCELAMENTO_JUDICIAL);
	}

	@Deprecated
	public RelatorioEmitirResumoParcelamentoJudicial(){
		super(null, "");
	}
	
	public Object executar() throws TarefaException{

		byte[] retorno = null;
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String idParcelamentoJudicial = (String) getParametro("idParcelamentoJudicial");
		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
				
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		List relatorioEmitirResumoParcelamentoJudicialBeans = new ArrayList();
		
		RelatorioEmitirResumoParcelamentoJudicialHelper relatorioEmitirResumoParcelamentoJudicialHelper = null;
		
		Collection colecaoObterParcelamentoJudicial = fachada.obterParcelamentoJudicial(idParcelamentoJudicial);

		if (colecaoObterParcelamentoJudicial != null && !colecaoObterParcelamentoJudicial.isEmpty()) {
			
			Iterator colecaoIteratorObterParcelamentoJudicial = colecaoObterParcelamentoJudicial.iterator();	
			
			relatorioEmitirResumoParcelamentoJudicialHelper = 
						(RelatorioEmitirResumoParcelamentoJudicialHelper) colecaoIteratorObterParcelamentoJudicial.next();
		
			parametros.put("NumeroProcessoJudicial", relatorioEmitirResumoParcelamentoJudicialHelper.getNumeroProcessoJudicial());
			parametros.put("ImovelPrincipal", relatorioEmitirResumoParcelamentoJudicialHelper.getImovelPrincipal());
			
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getImovelPrincipal().equals("")){
				
				relatorioEmitirResumoParcelamentoJudicialHelper.setEnderecoImovel(
						fachada.pesquisarEndereco(Integer.valueOf(
								relatorioEmitirResumoParcelamentoJudicialHelper.getImovelPrincipal())));
				
			} else {
				relatorioEmitirResumoParcelamentoJudicialHelper.setEnderecoImovel("");
			}
			
			parametros.put("EnderecoImovel", relatorioEmitirResumoParcelamentoJudicialHelper.getEnderecoImovel());
			parametros.put("ClienteResponsavel", relatorioEmitirResumoParcelamentoJudicialHelper.getClienteResponsavel());
			parametros.put("Funcionario", relatorioEmitirResumoParcelamentoJudicialHelper.getFuncionario());
			
			String advogadoResponsavelFormatado = "";
			
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getAdvogadoResponsavel().equals("")  &&
					(!relatorioEmitirResumoParcelamentoJudicialHelper.getNumeroOAB().equals(""))){
		
				advogadoResponsavelFormatado = relatorioEmitirResumoParcelamentoJudicialHelper.getAdvogadoResponsavel().toString() +" - "+
						relatorioEmitirResumoParcelamentoJudicialHelper.getNumeroOAB().toString();
						
			} 
			
			parametros.put("AdvogadoResponsavel", advogadoResponsavelFormatado);
			parametros.put("NumeroOAB", relatorioEmitirResumoParcelamentoJudicialHelper.getNumeroOAB());
			parametros.put("Situacao", relatorioEmitirResumoParcelamentoJudicialHelper.getSituacao());
			
			String dataParcelamentoFormatada = "";
			
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getDataParcelamento().equals("")){
			
				//2013-04-15 17:01:00.811 --> 15/04/2013
				String dataParcelamento = relatorioEmitirResumoParcelamentoJudicialHelper.getDataParcelamento().toString().substring(0, 10);
				
				dataParcelamentoFormatada = String.valueOf(dataParcelamento.charAt(8)) + String.valueOf(dataParcelamento.charAt(9)) +"/" + 
						String.valueOf(dataParcelamento.charAt(5)) + String.valueOf(dataParcelamento.charAt(6)) + "/" +
										String.valueOf(dataParcelamento.charAt(0)) + String.valueOf(dataParcelamento.charAt(1)) +
														String.valueOf(dataParcelamento.charAt(2)) + String.valueOf(dataParcelamento.charAt(3));
				
			} 
	
			parametros.put("DataParcelamento", dataParcelamentoFormatada);
			
			String valorDebitoFormatado = "";
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getValorDebito().equals("")){
				
				BigDecimal valorDebitoBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getValorDebito().toString()); 
				
				valorDebitoFormatado = Util.formatarMoedaReal(valorDebitoBiDecimal);
				
			}
			parametros.put("ValorDebito", valorDebitoFormatado);
			
			String valorAcordoFormatado = ""; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getValorAcordo().equals("")){
				
				BigDecimal valorAcordoBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getValorAcordo().toString()); 
				
				valorAcordoFormatado = Util.formatarMoedaReal(valorAcordoBiDecimal);
				
			}
			parametros.put("ValorAcordo", valorAcordoFormatado);
			
			String valorEntradaFormatado = " - "; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getValorEntrada().equals("")){
				
				BigDecimal valorEntradaBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getValorEntrada().toString()); 
				
				valorEntradaFormatado = Util.formatarMoedaReal(valorEntradaBiDecimal);
				
			} 
			parametros.put("ValorEntrada", valorEntradaFormatado);
			
			String percentualCustasFormatado = ""; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getPercentualCustas().equals("")){
				
				BigDecimal percentualCustasBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getPercentualCustas().toString()); 
				
				percentualCustasFormatado = Util.formatarMoedaReal(percentualCustasBiDecimal);
				
			}
			parametros.put("PercentualCustas", percentualCustasFormatado);
			
			String valorCustasFormatado = ""; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getValorCustas().equals("")){
				
				BigDecimal valorCustasBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getValorCustas().toString()); 
				
				valorCustasFormatado = Util.formatarMoedaReal(valorCustasBiDecimal);
				valorCustasFormatado = valorCustasFormatado + "(" + percentualCustasFormatado + "%" + ")";
				
			}
			parametros.put("ValorCustas", valorCustasFormatado);
	
			String percentualHonorariosFormatado = ""; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getPercentualHonorarios().equals("")){
				
				BigDecimal percentualHonorariosBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getPercentualHonorarios().toString()); 
				
				percentualHonorariosFormatado = Util.formatarMoedaReal(percentualHonorariosBiDecimal);
				
			}
			parametros.put("PercentualHonorarios", percentualHonorariosFormatado);
			
			String valorHonorariosFormatado = ""; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getValorHonorarios().equals("")){
				
				BigDecimal valorHonorariosBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getValorHonorarios().toString()); 
				
				valorHonorariosFormatado = Util.formatarMoedaReal(valorHonorariosBiDecimal);
				valorHonorariosFormatado = valorHonorariosFormatado + "(" + percentualHonorariosFormatado + "%" + ")";
				
			}
			parametros.put("ValorHonorarios", valorHonorariosFormatado);
			
			String valorParceladoFormatado = ""; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getValorParcelado().equals("")){
				
				BigDecimal valorParceladoBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getValorParcelado().toString()); 
				
				valorParceladoFormatado = Util.formatarMoedaReal(valorParceladoBiDecimal);
				
			}
			parametros.put("ValorParcelado", valorParceladoFormatado);
		
			String percentualDescontoFormatado = ""; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getPercentualDesconto().equals("")){
				
				BigDecimal percentualDescontoBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getPercentualDesconto().toString()); 
				
				percentualDescontoFormatado = Util.formatarMoedaReal(percentualDescontoBiDecimal);
				percentualDescontoFormatado = percentualDescontoFormatado + "%";
				
			}
			parametros.put("PercentualDesconto", percentualDescontoFormatado);
			
			String taxaJurosFormatado = " - "; 
			if (!relatorioEmitirResumoParcelamentoJudicialHelper.getTaxaJuros().equals("")){
				
				BigDecimal percentualTaxaJurosBiDecimal = 
						new BigDecimal (relatorioEmitirResumoParcelamentoJudicialHelper.getTaxaJuros().toString()); 
				
				taxaJurosFormatado = Util.formatarMoedaReal(percentualTaxaJurosBiDecimal);
				taxaJurosFormatado = taxaJurosFormatado + "%";
				
			}
			parametros.put("TaxaJuros", taxaJurosFormatado);
			
			parametros.put("QuantidadeParcelas", relatorioEmitirResumoParcelamentoJudicialHelper.getQuantidadeParcelas());
			
	        if (relatorioEmitirResumoParcelamentoJudicialHelper.getIndicadorDesconto().equals("1")){
	        	parametros.put("IndicadorDesconto", "SIM");
	        } else{
	        	parametros.put("IndicadorDesconto", "NÃO");
	        }
			
	        if (relatorioEmitirResumoParcelamentoJudicialHelper.getIndicadorCustas().equals("1")){
	        	parametros.put("IndicadorCustas", "SIM");
	        } else{
	        	parametros.put("IndicadorCustas", "NÃO");
	        }
			 
	        if (relatorioEmitirResumoParcelamentoJudicialHelper.getIndicadorHonorarios().equals("1")){
	        	parametros.put("IndicadorHonorarios", "SIM");
	        } else{
	        	parametros.put("IndicadorHonorarios", "NÃO");
	        }
	        
	        if (relatorioEmitirResumoParcelamentoJudicialHelper.getIndicadorJuros().equals("1")){
	        	parametros.put("IndicadorJuros", "SIM");
	        } else{
	        	parametros.put("IndicadorJuros", "NÃO");
	        }
	        
	        if (relatorioEmitirResumoParcelamentoJudicialHelper.getIndicadorInformarValorParcela().equals("1")){
	        	parametros.put("IndicadorInformarValorParcela", "SIM");
	        } else{
	        	parametros.put("IndicadorInformarValorParcela", "NÃO");
	        }
	        
	        if (relatorioEmitirResumoParcelamentoJudicialHelper.getIndicadorEntrada().equals("1")){
	        	parametros.put("IndicadorEntrada", "SIM");
	        } else{
	        	parametros.put("IndicadorEntrada", "NÃO");
	        }
		
		}else{
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		List relatorioObterListaDeContasParceladasBeans = new ArrayList();
		
		RelatorioListaContasParceladasHelper relatorioListaContasParceladasHelper = null;
		RelatorioListaContasParceladasBean relatorioListaContasParceladasBean = null;
		BigDecimal somatorioValorAtualizado = new BigDecimal(0);
		
		Collection colecaoObterListaDeContasParceladas = fachada.obterListaDeContasParceladas(idParcelamentoJudicial);
		
		if (colecaoObterListaDeContasParceladas != null && !colecaoObterListaDeContasParceladas.isEmpty()) {
			
			Iterator colecaoIteratorObterListaDeContasParceladas = colecaoObterListaDeContasParceladas.iterator();			
			
			while (colecaoIteratorObterListaDeContasParceladas.hasNext()) {

				relatorioListaContasParceladasHelper = 
						(RelatorioListaContasParceladasHelper) colecaoIteratorObterListaDeContasParceladas.next();
			
                String anoMesContaFormatado = "";
				
				if (!relatorioListaContasParceladasHelper.getAnoMesConta().equals("")){
					
					String anoMesConta = relatorioListaContasParceladasHelper.getAnoMesConta().toString().substring(0, 6);
					
					anoMesContaFormatado = String.valueOf(anoMesConta.charAt(4)) + String.valueOf(anoMesConta.charAt(5)) + "/" +
							String.valueOf(anoMesConta.charAt(0)) + String.valueOf(anoMesConta.charAt(1)) + 
											String.valueOf(anoMesConta.charAt(2)) + String.valueOf(anoMesConta.charAt(3));	
				}
				
				String dataVencimentoContaFormatada = "";
				
				if (!relatorioListaContasParceladasHelper.getDataVencimentoConta().equals("")){
				
					//2013-04-15 17:01:00.811 --> 15/04/2013
					String dataVencimentoConta = relatorioListaContasParceladasHelper.getDataVencimentoConta().toString().substring(0, 10);
					
					dataVencimentoContaFormatada = String.valueOf(dataVencimentoConta.charAt(8)) + String.valueOf(dataVencimentoConta.charAt(9)) +"/" + 
							String.valueOf(dataVencimentoConta.charAt(5)) + String.valueOf(dataVencimentoConta.charAt(6)) + "/" +
											String.valueOf(dataVencimentoConta.charAt(0)) + String.valueOf(dataVencimentoConta.charAt(1)) +
															String.valueOf(dataVencimentoConta.charAt(2)) + String.valueOf(dataVencimentoConta.charAt(3));
					
				} 
				
				String valorContaFormatado = ""; 
				if (!relatorioListaContasParceladasHelper.getValorConta().equals("")){
					
					BigDecimal valorContaBiDecimal = 
							new BigDecimal (relatorioListaContasParceladasHelper.getValorConta().toString()); 
					
					valorContaFormatado = Util.formatarMoedaReal(valorContaBiDecimal);
					
				}
				
				String acrescimoImpontualidadeFormatado = ""; 
				if (!relatorioListaContasParceladasHelper.getAcrescimoImpontualidade().equals("")){
					
					BigDecimal acrescimoImpontualidadeBiDecimal = 
							new BigDecimal (relatorioListaContasParceladasHelper.getAcrescimoImpontualidade().toString()); 
					
					acrescimoImpontualidadeFormatado = Util.formatarMoedaReal(acrescimoImpontualidadeBiDecimal);
					
				}
				
				BigDecimal valorAtualizadoBiDecimal = new BigDecimal(0);
				
				String valorAtualizadoFormatado = ""; 
				if ((!valorContaFormatado.equals("")) && (!acrescimoImpontualidadeFormatado.equals(""))){ 
					
					BigDecimal valorContaBiDecimal = 
							new BigDecimal (relatorioListaContasParceladasHelper.getValorConta().toString());
					
					BigDecimal acrescimoImpontualidadeBiDecimal = 
							new BigDecimal (relatorioListaContasParceladasHelper.getAcrescimoImpontualidade().toString());
					
					valorAtualizadoBiDecimal = valorAtualizadoBiDecimal.add(valorContaBiDecimal);
					valorAtualizadoBiDecimal = valorAtualizadoBiDecimal.add(acrescimoImpontualidadeBiDecimal);
					
					valorAtualizadoFormatado = Util.formatarMoedaReal(valorAtualizadoBiDecimal);
					
				}
				
				relatorioListaContasParceladasBean = new RelatorioListaContasParceladasBean(
						relatorioListaContasParceladasHelper.getImovel(),
						anoMesContaFormatado,
						dataVencimentoContaFormatada,
						valorContaFormatado,
						acrescimoImpontualidadeFormatado,
						valorAtualizadoFormatado);
				
				if (!valorAtualizadoFormatado.equals("")){
					somatorioValorAtualizado = somatorioValorAtualizado.add(valorAtualizadoBiDecimal);
				}
				
				relatorioObterListaDeContasParceladasBeans.add(relatorioListaContasParceladasBean);
				
			}
	
			parametros.put("somatorioValorAtualizado", Util.formatarMoedaReal(somatorioValorAtualizado));
			
		}else{
			
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioObterListaDeContasParceladasBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_RESUMO_PARCELAMENTO_JUDICIAL,
				parametros, ds, tipoFormatoRelatorio);

		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_EMITIR_RESUMO_PARCELAMENTO_JUDICIAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirResumoParcelamentoJudicial", this);
	}

}
