package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ResumoFaturamentoRelatorioHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1480] Gerar Relatório Resumo do Faturamento PPP
 * 
 * @author Jonathan Marcos
 * @date 28/05/2013
 */
public class RelatorioResumoFaturamentoPpp extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoFaturamentoPpp (Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO_PPP);
	}


	private Collection<RelatorioResumoFaturamentoBean> inicializarBeanRelatorio(Collection listaCamposConsultaRelatorio) {

		Iterator iterator = listaCamposConsultaRelatorio.iterator();

		Collection<RelatorioResumoFaturamentoBean> retorno = new ArrayList();

		while (iterator.hasNext()) {
			ResumoFaturamentoRelatorioHelper resumoFaturamentoRelatorioHelper = (ResumoFaturamentoRelatorioHelper) iterator.next();
			
			RelatorioResumoFaturamentoBean relatorioResumoFaturamentoBean = 
				new RelatorioResumoFaturamentoBean(
						resumoFaturamentoRelatorioHelper.getValorItemFaturamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoTipoLancamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoItemLancamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoItemContabil(),
						resumoFaturamentoRelatorioHelper.getIndicadorImpressao(),
						resumoFaturamentoRelatorioHelper.getIndicadorTotal(),
						resumoFaturamentoRelatorioHelper.getLancamentoTipo(),
						resumoFaturamentoRelatorioHelper.getLancamentoTipoSuperior(),
						false,
						resumoFaturamentoRelatorioHelper.getDescricaoGerencia(),
						resumoFaturamentoRelatorioHelper.getGerencia(),
						resumoFaturamentoRelatorioHelper.getDescricaoLocalidade(),
						resumoFaturamentoRelatorioHelper.getLocalidade(),
						resumoFaturamentoRelatorioHelper.getDescricaoMunicipio(),
						resumoFaturamentoRelatorioHelper.getMunicipio(),
						resumoFaturamentoRelatorioHelper.getDescLancamentoTipoSuperior(),
						resumoFaturamentoRelatorioHelper.getDescricaoUnidadeNegocio(),
						resumoFaturamentoRelatorioHelper.getUnidadeNegocio(),
						resumoFaturamentoRelatorioHelper.getCodigoCentroCusto() );

			retorno.add(relatorioResumoFaturamentoBean);
		}		
		return retorno;
	}

	
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoMunicipio = (Integer) getParametro("municipio");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String opcaoRelatorio = (String) getParametro("opcaoRelatorio");
		Integer subSistemaEsgoto = (Integer) getParametro("subSistemaEsgoto");
		Integer sistema = (Integer) getParametro("sistema");

		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection dadosRelatorio = fachada.pesquisarResumoFaturamentoPppRelatorio(opcaoTotalizacao, mesAno,
						codigoGerencia, codigoLocalidade, codigoMunicipio, unidadeNegocio,opcaoRelatorio,subSistemaEsgoto,sistema);

		Collection<RelatorioResumoFaturamentoBean> colecaoBean = this.inicializarBeanRelatorio(dadosRelatorio);

		Integer lancamentoTipoAnterior = null;
		Integer lancamentoTipoAnteriorSuperior = null;
		
		for (Iterator<RelatorioResumoFaturamentoBean> iter = colecaoBean.iterator(); iter.hasNext();) {
			RelatorioResumoFaturamentoBean bean = iter.next();

			// Se o tipo de lançamento for subordinado a outro tipo de
			// lançamento
			if (!bean.getLancamentoTipo().equals(bean.getLancamentoTipoSuperior())) {

				// Recua a descrição neste caso
				bean.setDescricaoTipoLancamento("    "	+ bean.getDescricaoTipoLancamento());

				bean.setDescricaoItemLancamento("    " 	+ bean.getDescricaoItemLancamento());

				if (bean.getDescricaoItemContabil() != null){
					bean.setDescricaoItemContabil("    "  + bean.getDescricaoItemContabil());	
				}
				
				if (lancamentoTipoAnterior != null 
						&& !lancamentoTipoAnterior.equals(bean.getLancamentoTipoSuperior())
						&& !lancamentoTipoAnteriorSuperior.equals(bean.getLancamentoTipoSuperior())){
					String descricaoLancamentoTipoSuperior = fachada.obterDescricaoLancamentoTipo(bean.getLancamentoTipoSuperior());
					bean.setDescLancamentoTipoSuperior(descricaoLancamentoTipoSuperior);
				}
				
			}
			
			lancamentoTipoAnterior = bean.getLancamentoTipo();
			lancamentoTipoAnteriorSuperior = bean.getLancamentoTipoSuperior();
			
			// Se o indicador impressão não estiver setado então a linha de
			// detalhe não aparecerá no relatório
			if (bean.getIndicadorImpressao() == null || !bean.getIndicadorImpressao().toString().equals("1")) {
				bean.setDescricaoTipoLancamento("");
				//iter.remove();
				continue;
			}
			
		}

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		String opcaoTotalizacaoDesc = "";
		if (opcaoTotalizacao.equalsIgnoreCase("estado")) {
			opcaoTotalizacaoDesc = "PPP";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			opcaoTotalizacaoDesc = "PPP por Gerência Regional";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")) {
			opcaoTotalizacaoDesc = "PPP por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")) {
			opcaoTotalizacaoDesc = "PPP por Município";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
			opcaoTotalizacaoDesc = "Gerência Regional";
		} else if (opcaoTotalizacao
				.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			opcaoTotalizacaoDesc = "Gerência Regional por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("localidade")) {
			opcaoTotalizacaoDesc = "Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("municipio")) {
			opcaoTotalizacaoDesc = "Município";
		} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
			opcaoTotalizacaoDesc = "Estado por Unidade de Negócio";
		} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
			opcaoTotalizacaoDesc = "Unidade de Negócio";
		} else if(opcaoTotalizacao.equals("subSistemaEsgoto")){
			opcaoTotalizacaoDesc = "Subsistema";
		} else if (opcaoTotalizacao.equals("sistema")){
			opcaoTotalizacaoDesc = "Sistema";
		} else if(opcaoTotalizacao.equals("estadoSubSistemaEsgoto")){
			opcaoTotalizacaoDesc = "PPP Por Subsistema";
		} else if(opcaoTotalizacao.equals("estadoSistema")){
			opcaoTotalizacaoDesc = "PPP por Sistema Esgoto";
		}

		parametros.put("opcaoTotalizacaoDesc", opcaoTotalizacaoDesc);

		String mesAnoString = "" + mesAno;
		if (mesAnoString.length() == 5) {
			mesAnoString = "0" + mesAnoString;
		}
		mesAnoString = mesAnoString.substring(0, 2) + "/" + mesAnoString.substring(2, 6);

		parametros.put("mesAnoArrecadacao", mesAnoString);

		parametros.put("tipoFormatoRelatorio", "");
		
		
		if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
			parametros.put("agrupaPorUnidadeNegocio", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			parametros.put("agrupaPorGerencia", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio") || opcaoTotalizacao.equalsIgnoreCase("municipio")) {
			parametros.put("agrupaPorMunicipio", "1");
		}else if(opcaoTotalizacao.equalsIgnoreCase("subSistemaEsgoto") || opcaoTotalizacao.equalsIgnoreCase("estadoSubSistemaEsgoto")){
			parametros.put("agrupaPorSubSistemaEsgoto", "1");
		} else if(opcaoTotalizacao.equalsIgnoreCase("sistema") || opcaoTotalizacao.equalsIgnoreCase("estadoSistema")){
			parametros.put("agrupaPorSistema", "1");
		}else {
			parametros.put("agrupaPorGerencia", "0");
		}
		
		parametros.put("opcaoRelatorio", "");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("tipoFormatoRelatorio", "R1480");

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO_PPP, parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_FATURAMENTO_PPP,idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Fachada fachada = Fachada.getInstancia();
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer idLocalidade = (Integer) getParametro("localidade");
		Integer idGerencia = (Integer) getParametro("gerenciaRegional");
		Integer idMunicipio = (Integer) getParametro("municipio");
		Integer idSubSistemaEsgoto = (Integer) getParametro("subSistemaEsgoto");
		Integer idSistemaEsgoto = (Integer) getParametro("sistema");
		
		Integer totalRegistrosRel;
		totalRegistrosRel = fachada.pesquisarQtdeRegistrosResumoFaturamentoPppRelatorio(mesAno, idLocalidade, 
					idMunicipio, idGerencia, opcaoTotalizacao,idSubSistemaEsgoto,idSistemaEsgoto);
		return totalRegistrosRel.intValue();
	}


	public void agendarTarefaBatch() {
	}
}
