package gcom.relatorio.atendimentopublico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioComandosConexaoEsgotoAnalitico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioComandosConexaoEsgotoAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COMANDOS_CONEXAO_ESGOTO_ANALITICO);
	}

	@Deprecated
	public RelatorioComandosConexaoEsgotoAnalitico() {
		super(null, "");
	}
	
	public Object executar() throws TarefaException {

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		FiltrarRelatorioComandosConexaoEsgotoHelper helperFiltro = (FiltrarRelatorioComandosConexaoEsgotoHelper) 
				getParametro("filtrarRelatorioComandosConexaoEsgotoHelper");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map<String, Object>parametros = new HashMap<String, Object>();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Recuperar o nome da empresa e numero do contrato
		Collection<RelatorioComandosConexaoEsgotoHelper> colecaoHelperBean =
				fachada.pesquisarDadosRelatorioComandosConexaoEsgotoAnalitico(helperFiltro);
		
		if (colecaoHelperBean == null || colecaoHelperBean.isEmpty()) {
			throw new ActionServletException( 
					"atencao.pesquisa.nenhumresultado");
		}
		
		parametros.put("descricaoComando", helperFiltro.getDescricaoComando());
		parametros.put("descricaoExecucao",helperFiltro.getDescricaoExecucao());
		parametros.put("descricaoFaturamentoGrupo", helperFiltro.getDescricaoFaturamentoGrupo());
		parametros.put("idImovel", helperFiltro.getIdImovel());
		parametros.put("descricaoMunicipio", helperFiltro.getDescricaoMunicipio());
		parametros.put("descricaoLogradouro", helperFiltro.getDescricaoLogradouro());
		parametros.put("idLocalidadeInicial", helperFiltro.getIdLocalidadeInicial());
		parametros.put("idLocalidadeFinal", helperFiltro.getIdLocalidadeFinal());
		parametros.put("codigoSetorComercialInicial", helperFiltro.getCodigoSetorComercialInicial());
		parametros.put("codigoSetorComercialFinal", helperFiltro.getCodigoSetorComercialFinal());
		parametros.put("quadraInicial", helperFiltro.getQuadraInicial());
		parametros.put("quadraFinal", helperFiltro.getQuadraFinal());
		parametros.put("codigoRotaInicial", helperFiltro.getCodigoRotaInicial());
		parametros.put("codigoRotaFinal", helperFiltro.getCodigoRotaFinal());
		parametros.put("sequencialRotaInicial", helperFiltro.getSequencialRotaInicial());
		parametros.put("sequencialRotaFinal", helperFiltro.getSequencialRotaFinal());
		parametros.put("descricaoSituacaoOS", helperFiltro.getDescricaoSituacaoOS());
		if (helperFiltro.getDataGeracaoInicial() != null) {
			parametros.put("dataGeracaoInicial", Util.formatarData(helperFiltro.getDataGeracaoInicial()));
		}
		if (helperFiltro.getDataGeracaoFinal() != null) {
			parametros.put("dataGeracaoFinal", Util.formatarData(helperFiltro.getDataGeracaoFinal()));
		}
		
		List<RelatorioComandosConexaoEsgotoBean> relatorioBeans = 
				montarRelatorio(colecaoHelperBean);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMANDOS_CONEXAO_ESGOTO_ANALITICO,
		parametros, ds, tipoFormatoRelatorio);
		
		
		return retorno;
	}
    
	private List<RelatorioComandosConexaoEsgotoBean> montarRelatorio(
		Collection<RelatorioComandosConexaoEsgotoHelper> colecaoBoletins) {
		
		List<RelatorioComandosConexaoEsgotoBean> retorno = 
				new ArrayList<RelatorioComandosConexaoEsgotoBean>();
		
		Iterator<RelatorioComandosConexaoEsgotoHelper> iterator = 
				colecaoBoletins.iterator();
		
		while(iterator.hasNext()){
			RelatorioComandosConexaoEsgotoBean bean = 
					new RelatorioComandosConexaoEsgotoBean();
			
			RelatorioComandosConexaoEsgotoHelper helper = (RelatorioComandosConexaoEsgotoHelper) iterator.next();

			bean.setIdMunicipio(helper.getIdMunicipio().toString());
			bean.setDescricaoMunicipio(helper.getDescricaoMunicipio());
			
			bean.setIdLocalidade(helper.getIdLocalidade().toString());
			bean.setDescricaoLocalidade(helper.getDescricaoLocalidade());
			
			bean.setIdSetorComercial(helper.getIdSetorComercial().toString());
			bean.setDescricaoSetorComercial(helper.getDescricaoSetorComercial());

			bean.setNumeroOS(helper.getNumeroOS().toString());
			bean.setMatricula(helper.getMatricula().toString());
			
			bean.setDataEncerramento(Util.formatarData(
				helper.getDataEncerramento()));
			
			bean.setSituacaoEncerramento(helper.getSituacaoEncerramento());
			bean.setSituacaoLigacaoEsgoto(helper.getSituacaoLigacaoEsgoto());
			
			retorno.add(bean);
		}
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	public void agendarTarefaBatch() {
	}

}
