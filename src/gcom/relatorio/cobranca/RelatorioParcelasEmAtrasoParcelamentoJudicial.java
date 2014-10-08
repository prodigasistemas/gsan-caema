package gcom.relatorio.cobranca;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1465] Gerar Relatório das Parcelas em Atraso do Parcelamento Judicial
 * 
 * @author Mariana Victor
 * @date 22/04/2013
 */
public class RelatorioParcelasEmAtrasoParcelamentoJudicial extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioParcelasEmAtrasoParcelamentoJudicial(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public Object executar() throws TarefaException {
		FiltrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper helper = (FiltrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper) 
				getParametro("filtrarRelatorioParcelasEmAtrasoParcelamentoJudicialHelper");
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		Collection<Object[]> colecaoDados = Fachada.getInstancia()
				.pesquisarParcelasEmAtrasoParcelamentoJudicial(helper);

		// [SB0005] Verificar Nenhum Registro Encontrado.
		if(colecaoDados == null 
				|| colecaoDados.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
//		Collection<RelatorioAcompanhamentoBoletimMedicaoContratoBean> beans = helperRelatorio.getBeans();
		
		Collection<RelatorioParcelasEmAtrasoParcelamentoJudicialBean> beans = this.inicializarBeanRelatorio(colecaoDados);
		
		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", tipoFormatoRelatorio);
		
		if (helper.getNomeClienteResponsavel() != null) {
			parametros.put("nomeCliente", helper.getNomeClienteResponsavel());
		} else {
			parametros.put("nomeCliente", " - ");
		}
		if (helper.getIdImovelPrincipal() != null) {
			parametros.put("idImovel", helper.getIdImovelPrincipal().toString());
		} else {
			parametros.put("idImovel", " - ");
		}
		if (helper.getNumeroProcessoJudicial() != null) {
			parametros.put("numeroProcesso", helper.getNumeroProcessoJudicial());
		} else {
			parametros.put("numeroProcesso", " - ");
		}
		if (helper.getDataParcelamentoInicial() != null
				&& helper.getDataParcelamentoFinal() != null) {
			parametros.put("periodoParcelamento", 
				Util.formatarData(helper.getDataParcelamentoInicial()) 
				+ " a " + Util.formatarData(helper.getDataParcelamentoFinal()));
		} else {
			parametros.put("periodoParcelamento", " - ");
		}
		if (helper.getQuantidadeDiasAtraso() != null) {
			parametros.put("quantidadeDias", helper.getQuantidadeDiasAtraso().toString());
		} else {
			parametros.put("quantidadeDias", " - ");
		}
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource(
				(List<RelatorioParcelasEmAtrasoParcelamentoJudicialBean>) beans);
		
		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PARCELAS_EM_ATRASO_PARCELAMENTO_JUDICIAL, 
				parametros, ds, tipoFormatoRelatorio);
			
		return retorno;
	}
	
	private Collection<RelatorioParcelasEmAtrasoParcelamentoJudicialBean> inicializarBeanRelatorio(
		Collection<Object[]> colecaoDados) {
		Collection<RelatorioParcelasEmAtrasoParcelamentoJudicialBean> beans = 
				new ArrayList<RelatorioParcelasEmAtrasoParcelamentoJudicialBean>();
		
		Iterator<Object[]> iterator = colecaoDados.iterator();
		
		while(iterator.hasNext()) {
			Object[] dados = (Object[]) iterator.next();
			RelatorioParcelasEmAtrasoParcelamentoJudicialBean bean = 
					new RelatorioParcelasEmAtrasoParcelamentoJudicialBean();

			if (dados[0] != null) {
				bean.setIdParcelamento(
					((Integer) dados[0]).toString());
			}
			if (dados[1] != null 
					&& dados[2] != null) {
				bean.setClienteResponsavel(
					((Integer) dados[1]).toString()
					+ " - " + (String) dados[2]);
			}
			if (dados[3] != null) {
				bean.setImovelPrincipal(
					((Integer) dados[3]).toString());
			}
			if (dados[4] != null) {
				bean.setNumeroProcesso(
					(String) dados[4]);
			}
			if (dados[5] != null) {
				bean.setNumeroParcela(
					((Integer) dados[5]).toString());
			}
			if (dados[6] != null) {
				bean.setDataVencimento(
					Util.formatarData((Date) dados[6]));
			}
			if (dados[7] != null) {
				bean.setValorParcela(
					Util.formatarMoedaReal((BigDecimal) dados[7]));
			}
			if (dados[8] != null) {
				bean.setQuantidadeDiasAtraso(
					((Integer) dados[8]).toString());
			}
			
			beans.add(bean);
		}
		
		return beans;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioParcelasEmAtrasoParcelamentoJudicial", this);
	}

}
