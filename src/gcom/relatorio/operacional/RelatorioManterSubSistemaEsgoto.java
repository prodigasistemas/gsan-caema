package gcom.relatorio.operacional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.FiltroSubSistemaEsgoto;
import gcom.operacional.SubSistemaEsgoto;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioManterSubSistemaEsgoto extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public  RelatorioManterSubSistemaEsgoto(Usuario usuario){
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_IMOVEL_PERFIL);
	}
	
	@Deprecated
	public RelatorioManterSubSistemaEsgoto() {
		super(null, "");
	}

	public Object executar() throws TarefaException {	

		FiltroSubSistemaEsgoto filtroSubSistemaEsgoto = (FiltroSubSistemaEsgoto) getParametro("filtroSubSistemaEsgoto");
		SubSistemaEsgoto subSistemaEsgoto = (SubSistemaEsgoto) getParametro("subSistemaEsgotoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterSubSistemaEsgotoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroSubSistemaEsgoto.setConsultaSemLimites(true);

		Collection<SubSistemaEsgoto> colecaoSubSistemaEsgoto = fachada.pesquisar(filtroSubSistemaEsgoto, SubSistemaEsgoto.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoSubSistemaEsgoto != null && !colecaoSubSistemaEsgoto.isEmpty()) {

			// la�o para criar a cole��o de par�metros da analise
			for (SubSistemaEsgoto subSistEsgoto : colecaoSubSistemaEsgoto) {

				relatorioBean = new RelatorioManterSubSistemaEsgotoBean(
						// C�digo
						subSistEsgoto.getId().toString(),					 
						// Descri��o
						subSistEsgoto.getDescricao(),
						//Sistema Esgoto
						subSistEsgoto.getSistemaEsgoto().getDescricao(),
						//Descricao Abreviada
						subSistEsgoto.getDescricaoAbreviada()
						);						

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
				
			}
			
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1521");

		if (subSistemaEsgoto.getId() != null) {
			parametros.put("id",
					subSistemaEsgoto.getId().toString());
		} else {
			parametros.put("id", "");
		}

		if ( subSistemaEsgoto.getDescricao() != null &&
				!subSistemaEsgoto.getDescricao().equals("") ) {
			
			parametros.put("descricao", subSistemaEsgoto.getDescricao());
		} else {
			parametros.put("descricao", "" );
		}
		
		if ( subSistemaEsgoto.getDescricaoAbreviada() != null &&
				!subSistemaEsgoto.getDescricaoAbreviada().equals("") ) {
			
			parametros.put("descricaoAbreviada", subSistemaEsgoto.getDescricaoAbreviada());
		} else {
			parametros.put("descricaoAbreviada", "" );
		}
		
		if ( (subSistemaEsgoto.getSistemaEsgoto() != null) &&
				(!subSistemaEsgoto.getSistemaEsgoto().getDescricao().equals("")) ) {
			
			parametros.put("sistemaEsgoto", subSistemaEsgoto.getSistemaEsgoto().getDescricao());
		} else {
			parametros.put("sistemaEsgoto", "" );
		}
		
		if (subSistemaEsgoto.getIndicadorUso() != null) {
			
			if (subSistemaEsgoto.getIndicadorUso() == 1){
				parametros.put("indicadorUso", "ATIVO");
			} else if (subSistemaEsgoto.getIndicadorUso() == 2){
				parametros.put("indicadorUso", "INATIVO");
			}

		} else {
			parametros.put("indicadorUso", "TODOS" );
		}
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_SUBSISTEMA_ESGOTO, parametros,
				ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterSubSistemaEsgoto", this);
	}
	
}
