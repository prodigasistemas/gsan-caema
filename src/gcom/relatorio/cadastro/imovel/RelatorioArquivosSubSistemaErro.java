package gcom.relatorio.cadastro.imovel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.SubSistemaEsgotoArquivoTextoErro;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioArquivosSubSistemaErro extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioArquivosSubSistemaErro(Usuario usuario, String nomeRelatorio) {
		super(usuario, ConstantesRelatorios.RELATORIO_ARQUIVOS_SUBSISTEMA_ESGOTO_ERRO);

	}
	
	@Deprecated
	public RelatorioArquivosSubSistemaErro() {
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		byte[] retorno = null;
		
		ArrayList<SubSistemaEsgotoArquivoTextoErro> colecaoSubSistemaEsgotoArquivoTextoErro = null;
		ArrayList<RelatorioArquivosSubSistemaErroBean> colecaoBean = new ArrayList<RelatorioArquivosSubSistemaErroBean>();

		// Parametros do relatorio
		Map<String, String> parametros = new HashMap<String, String>();
		String idArquivo = (String) getParametro("idArquivo");
		String tipoFormatoRelatorio = (String) getParametro("tipoRelatorio");
		
		colecaoSubSistemaEsgotoArquivoTextoErro = (ArrayList<SubSistemaEsgotoArquivoTextoErro>) 
				fachada.pesquisarSubSistemaEsgotoArquivoTextoErro(Integer.parseInt(idArquivo));
		
		if(colecaoSubSistemaEsgotoArquivoTextoErro != null){
			for(SubSistemaEsgotoArquivoTextoErro arquivoErro : colecaoSubSistemaEsgotoArquivoTextoErro){
				RelatorioArquivosSubSistemaErroBean bean = new RelatorioArquivosSubSistemaErroBean();
				bean.setDescricaoRegistro(arquivoErro.getDescricaoRegistro());
				bean.setMensagemErro(arquivoErro.getMensagemErro());
				bean.setUltimaAlteracao(Util.formatarData(arquivoErro.getUltimaAlteracao()));
				
				colecaoBean.add(bean);
			}
		}
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("dataArquivo", colecaoBean.get(0).getUltimaAlteracao());
		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
		
		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ARQUIVOS_SUBSISTEMA_ESGOTO_ERRO, parametros,
				ds, Integer.parseInt(tipoFormatoRelatorio));
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioArquivosSubSistemaErro", this);
		
	}
	
}
