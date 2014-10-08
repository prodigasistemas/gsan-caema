package gcom.relatorio.mobile;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioQuantitativoImoveisTipoServico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioQuantitativoImoveisTipoServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_QUANTITATIVO_IMOVEIS_TIPO_SERVICO);
	}
	
	@Deprecated
	public RelatorioQuantitativoImoveisTipoServico(){
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		//Valor de Retorno
		byte[] retorno = null;
		
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		Integer idLocalidade = (Integer) getParametro("idLocalidade");
		Integer codigoSetorInicial = (Integer) getParametro("codigoSetorInicial");
		Integer quadraInicial = (Integer) getParametro("quadraInicial");
		Integer codigoSetorFinal = (Integer) getParametro("codigoSetorFinal");
		Integer quadraFinal = (Integer) getParametro("quadraFinal");
		Integer[] idsTipoServico = (Integer[]) getParametro("idsTipoServico");
		String comando = (String) getParametro("comando");
		String identificacaoOS = (String) getParametro("identificacaoOS");
		
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// coleção de beans do relatório
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		Collection<RelatorioQuantitativoImoveisTipoServicoBean> colecaoBeans = Fachada.getInstancia().pesquisarQuantitativoImoveisTipoServico(
				idEmpresa,
				idGerenciaRegional, 
				idUnidadeNegocio, idLocalidade, codigoSetorInicial, 
				codigoSetorFinal, quadraInicial, quadraFinal, idsTipoServico,
				comando,identificacaoOS);
		
		if(!Util.isVazioOrNulo(colecaoBeans)){
			relatorioBeans.addAll(colecaoBeans);
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		//Parametros do Relatorio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (idLocalidade != null) {
			parametros.put("idLocalidade", idLocalidade.toString());
		} else {
			parametros.put("idLocalidade", null);
		}
		
		if (codigoSetorInicial != null) {
			parametros.put("codigoSetorInicial", codigoSetorInicial.toString());
		} else {
			parametros.put("codigoSetorInicial", null);
		}
		
		if (quadraInicial != null) {
			parametros.put("quadraInicial", quadraInicial.toString());
		} else {
			parametros.put("quadraInicial", null);
		}
		
		if (codigoSetorFinal != null) {
			parametros.put("codigoSetorFinal", codigoSetorFinal.toString());
		} else {
			parametros.put("codigoSetorFinal", null);
		}
		
		if (quadraFinal != null) {
			parametros.put("quadraFinal", quadraFinal.toString());
		} else {
			parametros.put("quadraFinal", null);
		}
		
		parametros.put("tipoFormatoRelatorio", "R1497");
		
		//Cria uma instancia do dataSource do Relatorio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_QUANTITATIVO_IMOVEIS_TIPO_SERVICO, 
				parametros, ds, tipoFormatoRelatorio);
		
		//Retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioQuantitativoImoveisTipoServico", this);
	}

}
