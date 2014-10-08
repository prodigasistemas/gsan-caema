package gcom.relatorio.faturamento;

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
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioQuantitativoContasReimpressas extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioQuantitativoContasReimpressas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_QUANTITATIVO_CONTAS_REIMPRESSAS);
	}
	
	public RelatorioQuantitativoContasReimpressas() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	@Override
	public Object executar() throws TarefaException {
		
		//valor de Retorno
		byte[] retorno = null;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		RelatorioQuantitativoContasReimpressasHelper helper = 
			(RelatorioQuantitativoContasReimpressasHelper) getParametro("helper");
		
		String anoMesReferencia = String.valueOf(helper.getAnoMesReferencia());
		
		List beans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<?> relatorioBeans = fachada.pesquisarQuantidadeContasReimpressas(helper); 
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}else{
			Iterator iterator = relatorioBeans.iterator();
			while(iterator.hasNext()){
				RelatorioQuantitativoContasReimpressasBean bean = 
					(RelatorioQuantitativoContasReimpressasBean) iterator.next();
				beans.add(bean);
			}
		}
		
		//Paramêtros do Relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//adiciona os parametros no relatorio
		//parametros.put("tipoFormatoRelatorio", String.valueOf(tipoFormatoRelatorio));
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMesReferencia", Util.formatarAnoMesParaMesAno(anoMesReferencia));
		
		if(helper.getRota() != null && !helper.getRota().equals("")){
			parametros.put("codigoRota", "Rota: " + helper.getRota());
		}else{
			parametros.put("codigoRota", "");
		}
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_QUANTITATIVO_CONTAS_REIMPRESSAS;
		
		//cria uma instancia do data source no relatório
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		retorno = gerarRelatorio(nomeRelatorio, parametros, ds, tipoFormatoRelatorio);
		
		//retorna o relatório gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioQuantitativoContasReimpressas", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

}
