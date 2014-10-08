package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1353] Relatório de Retorno das Ordens de Fiscalização
 * 
 * @author Raimundo Martins
 * @date 09/07/2012
 **/
public class RelatorioRetornoOSFiscalizacao extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioRetornoOSFiscalizacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RETORNO_OS_FISCALIZACAO);
	}
	
	@Deprecated
	public RelatorioRetornoOSFiscalizacao() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String[] situacoes = null;
		if(getParametro("situacoes")!= null && !getParametro("situacoes").equals("")){
			situacoes = (String[])getParametro("situacoes");
		}
		Integer mesAno = (Integer) getParametro("mesAno");		
		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(mesAno));
		Integer cobrancaAcaoId = (Integer) getParametro("cobrancaAcaoId");
		Integer idCobrancaAcaoSituacao = (Integer) getParametro("idCobrancaAcaoSituacao");
		InformarDadosGeracaoResumoAcaoConsultaEventualHelper helper = (InformarDadosGeracaoResumoAcaoConsultaEventualHelper) getParametro("helper");
		
		if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty() && 
			helper.getColecaoGerenciaRegional().size() == 1) {
			GerenciaRegional gr = (GerenciaRegional) helper.getColecaoGerenciaRegional().iterator().next();
			parametros.put("idGerencia", gr.getId().toString());
			parametros.put("descGerencia", gr.getNome());
		}
		
		if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty() && 
			helper.getColecaoUnidadeNegocio().size() == 1) {
			UnidadeNegocio un = (UnidadeNegocio) helper.getColecaoUnidadeNegocio().iterator().next();
			parametros.put("idUnidade", un.getId().toString());
			parametros.put("descUnidade", un.getNome());
		}
		
		List<RelatorioRetornoOrdemServicoFiscalizacaoBean> colecaoBean = Fachada.getInstancia().obterDadosRelatorioRetornoOSFiscalizacao(helper, 
				cobrancaAcaoId, idCobrancaAcaoSituacao, parametros, mesAno, situacoes);
		if(colecaoBean !=null){
			RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RETORNO_OS_FISCALIZACAO,parametros, ds, tipoFormatoRelatorio);			
		}
		else{
			List<RelatorioRetornoOSFiscalizacaoBean> colecaoBean2 = new ArrayList<RelatorioRetornoOSFiscalizacaoBean>();
			colecaoBean2.add(new RelatorioRetornoOSFiscalizacaoBean());
			RelatorioDataSource ds = new RelatorioDataSource(colecaoBean2);
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RETORNO_OS_FISCALIZACAO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {		
		return 1;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRetornoOSFiscalizacao", this);		
	}

}
