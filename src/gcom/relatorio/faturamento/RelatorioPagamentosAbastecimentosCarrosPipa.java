package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1565] Gerar Relatorio Pagamentos Abastecimento Carro Pipa
 * 
 * @author Diogo Luiz
 * @Date 15/10/2013
 * 
 */
public class RelatorioPagamentosAbastecimentosCarrosPipa extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioPagamentosAbastecimentosCarrosPipa(
			Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PAGAMENTOS_ABASTECIMENTOS_CARROS_PIPA);		
	}

	@Override
	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Integer idFaturamentoGrupo = (Integer) getParametro("idFaturamentoGrupo");
		Integer mesAnoReferencia = (Integer) getParametro("mesAnoReferencia");
		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		
		UnidadeNegocio unidadeNegocio = null;
		
		GerenciaRegional gerenciaRegional = null;
		
		if(idFaturamentoGrupo != null && !idFaturamentoGrupo.equals("")){
			idGerenciaRegional = null;
			idUnidadeNegocio = null;
		}
		
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.ID, idGerenciaRegional));
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, 
				GerenciaRegional.class.getName());		
		
			if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
				gerenciaRegional = (GerenciaRegional) 
						Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
			}
		}	
		
		if(idUnidadeNegocio != null && !idUnidadeNegocio.equals("")){
		
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, 
				UnidadeNegocio.class.getName());			
			
			if(!Util.isVazioOrNulo(colecaoUnidadeNegocio)){
				 unidadeNegocio = (UnidadeNegocio) 
						Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			}	
		}
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");			
		
		List relatorioBeans = new ArrayList();
		
		relatorioBeans = fachada.pesquisarRelatorioCarroPipa(idFaturamentoGrupo, mesAnoReferencia, 
			idGerenciaRegional, idUnidadeNegocio);			
		
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("periodo", Util.formatarAnoMesParaMesAno(mesAnoReferencia));
		
		parametros.put("relatorio", "R1565");	
		
		parametros.put("grupo",idFaturamentoGrupo);
		
		if(unidadeNegocio != null && !unidadeNegocio.equals("")){
			parametros.put("unidadeNegocio", unidadeNegocio.getNome());
		}	
		
		if(gerenciaRegional != null && !gerenciaRegional.equals("")){
			parametros.put("gerenciaRegional", gerenciaRegional.getNome());
		}
		
		// cria uma instancia do dataSource do relatorio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PAGAMENTOS_ABASTECIMENTOS_CARROS_PIPA,
				parametros, ds, tipoFormatoRelatorio);
		
		// Grava o relatorio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_PAGAMENTOS_ABASTECIMENTOS_CARROS_PIPA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}				

		// retorna o relatorio gerado
			return retorno;		
}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamentosAbastecimentosCarrosPipa", this);		
	}
	
}
