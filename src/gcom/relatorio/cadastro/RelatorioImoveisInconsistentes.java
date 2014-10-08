package gcom.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

/**
 * [UC 1297] - Relatório Imóveis Inconsistentes - Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 23/03/2012
 *
 */
public class RelatorioImoveisInconsistentes extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisInconsistentes(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_INCONSISTENTES);
	}
	
	@Deprecated
	public RelatorioImoveisInconsistentes(){
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		//valor de Retorno
		byte retorno[] = null;
		
		String nomeEmpresa = (String) getParametro("empresa");
		String descricaoLocalidade = (String) getParametro("descricaoLocalidade");
		String descricaoSetor = (String) getParametro("descricaoSetor");
		String quadraInicial = (String) getParametro("quadraInicial");
		String quadraFinal = (String) getParametro("quadraFinal");
		String cadastrador = (String) getParametro("cadastrador");
		String situacaoMovimento = (String) getParametro("situacaoMovimento");
		String tipoInconsistencia = (String) getParametro("tipoInconsistencia");
		
		String [] idsRegistro = (String []) getParametro("idsRegistro");
		String idLocalidade = (String) getParametro("idLocalidade");
		String codigoSetorComercial = (String) getParametro("codigoSetorComercial");
		String numeroQuadraInicial = (String) getParametro("numeroQuadraInicial");
		String numeroQuadraFinal = (String) getParametro("numeroQuadraFinal");
		String idCadastrador = (String) getParametro("idCadastrador");
		String indicadorSituacaoMovimento = (String) getParametro("indicadorSituacaoMovimento");
		String idTipoInconsistencia = (String) getParametro("idTipoInconsistencia");
		
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// coleção de beans do relatório
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		Collection<RelatorioImoveisInconsistentesBean> colBeans = 
			Fachada.getInstancia().pesquisarRelatorioImoveisInconsistentes(idsRegistro,
					idLocalidade,codigoSetorComercial,numeroQuadraInicial,numeroQuadraFinal,
					idCadastrador, indicadorSituacaoMovimento, idTipoInconsistencia);
		
		if(!Util.isVazioOrNulo(colBeans)){
			Iterator<?> iterator = colBeans.iterator();
			while(iterator.hasNext()){
				RelatorioImoveisInconsistentesBean bean = 
					(RelatorioImoveisInconsistentesBean) iterator.next();
				relatorioBeans.add(bean);
			}
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// __________________________________________________________________
		
		// Parâmetros do relatório
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("descricaoLocalidade", descricaoLocalidade);
		parametros.put("descricaoSetor", descricaoSetor);
		parametros.put("quadraInicial", quadraInicial);
		parametros.put("quadraFinal", quadraFinal);
		parametros.put("cadastrador", cadastrador);
		parametros.put("situacaoMovimento", situacaoMovimento);
		parametros.put("tipoInconsistencia", tipoInconsistencia);
		parametros.put("tipoRelatorio", "R1297");
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_INCONSISTENTES, 
			parametros, ds, tipoRelatorio);
		
		//retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisInconsistentes", this);
	}

}
