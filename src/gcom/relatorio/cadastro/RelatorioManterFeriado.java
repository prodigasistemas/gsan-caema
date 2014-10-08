package gcom.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.FeriadoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * 
 * @author Diogo Luiz	
 * 
 * @date 01/10/2013
 *
 */
public class RelatorioManterFeriado extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioManterFeriado(
			Usuario usuario) {
		super(usuario,
			 ConstantesRelatorios.RELATORIO_FERIADO_MANTER);		
	}	
	
	@Override
	public Object executar() throws TarefaException {		
		
		Collection colecaoFeriado = (Collection) getParametro("relatorio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");	
		
		Fachada fachada = Fachada.getInstancia();
		
		// valor de retorno
		byte[] retorno = null;
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		RelatorioManterFeriadoBean relatorioBean = null;			
				
		Iterator it = colecaoFeriado.iterator();
		
		while(it.hasNext()){
			
			FeriadoHelper feriadoHelper = new FeriadoHelper();
			feriadoHelper = (FeriadoHelper) it.next();
			
			String tipoFeriado = null;
			
			String municipio = null;
			
			String descricao = null;
			
			String dataFeriado = null;
			
			if(feriadoHelper.getTipoFeriado().equals(ConstantesSistema.SIM)){
				tipoFeriado = "NACIONAL";
				descricao = feriadoHelper.getDescricao();				
				
			}else{
				tipoFeriado = "MUNICIPAL";
				municipio = feriadoHelper.getDescricaoMunicipio();
				descricao = feriadoHelper.getDescricao();
			}
			
			dataFeriado = feriadoHelper.getData();		
			
			relatorioBean = new RelatorioManterFeriadoBean(
				tipoFeriado, municipio, dataFeriado, descricao);
			
			relatorioBeans.add(relatorioBean);			
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_FERIADO_MANTER, parametros,
				ds, tipoFormatoRelatorio);		
		
		return retorno;
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterFeriado", this);
		
	}

	
	
	

}
