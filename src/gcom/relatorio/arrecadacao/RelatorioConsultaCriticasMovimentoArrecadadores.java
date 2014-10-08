package gcom.relatorio.arrecadacao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.ArrecadadorMovimentoCriticas;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1332] Consultar Crítica Movimento Arrecadadores
 * 
 * @author Raimundo Martins
 * @date 10/05/2012
 * */
public class RelatorioConsultaCriticasMovimentoArrecadadores extends TarefaRelatorio{

	public RelatorioConsultaCriticasMovimentoArrecadadores(Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_CONSULTAR_CRITICAS_MOVIMENTO_ARRECADADORES);		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object executar() throws TarefaException {
		List<RelatorioConsultaCriticasMovimentoArrecadadoresBean> relatorioBeans = new ArrayList<RelatorioConsultaCriticasMovimentoArrecadadoresBean>();
		for(ArrecadadorMovimentoCriticas critica : (Collection<ArrecadadorMovimentoCriticas>) getParametro("criticas")){
			RelatorioConsultaCriticasMovimentoArrecadadoresBean bean = new RelatorioConsultaCriticasMovimentoArrecadadoresBean();
			if(critica.getCodigoBanco()!=null &&  critica.getCodigoBanco().compareTo(0) > 0)
				bean.setArrecadador(critica.getCodigoBanco().toString());
			else
				bean.setArrecadador("");
			
			if(critica.getIdentificacaoServico() !=null && !critica.getIdentificacaoServico().trim().equals(""))
				bean.setIdServico(critica.getIdentificacaoServico());
			else
				bean.setIdServico("");
			
			if(critica.getNsa() !=null && critica.getNsa().compareTo(0) > 0)
				bean.setNsa(critica.getNsa().toString());
			else
				bean.setNsa("");
			
			if(critica.getDataProcessamento() !=null)
				bean.setDataProcessamento(new SimpleDateFormat("dd/MM/yyyy").format(critica.getDataProcessamento()));
			else
				bean.setDataProcessamento("");
			
			if(critica.getArrecadadorCritica() !=null)
				bean.setCriticas(critica.getArrecadadorCritica().getDescricaoCritica().toUpperCase());
			else
				bean.setCriticas("");
			
			relatorioBeans.add(bean);			
		}
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("imagem", Fachada.getInstancia().
			pesquisarParametrosDoSistema().getImagemRelatorio());

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTAR_CRITICAS_MOVIMENTO_ARRECADADORES, parametros,ds, tipoFormatoRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultaCriticasMovimentoArrecadadores", this);
	}


}
