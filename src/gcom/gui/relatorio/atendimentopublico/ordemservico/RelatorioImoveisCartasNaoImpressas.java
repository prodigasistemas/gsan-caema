package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.batch.Relatorio;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.seguranca.RelatorioAlteracoesSistemaColunaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
 * [FB0004] Gerar Relat�rio de Im�veis com Cartas n�o Impressas
 * 
 * O sistema gera o relat�rio de im�veis com cartas n�o Impressas 
 * para os im�veis e ordens de servi�o selecionadas
 * 
 * @param idGrupo - Id do grupo de faturamento
 * 
 * @author Hugo Azevedo	
 * @date 26/08/2013
 * 
 */
public class RelatorioImoveisCartasNaoImpressas extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisCartasNaoImpressas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_CARTAS_NAO_IMPRESSAS);
	}
	
	@Deprecated
	public RelatorioImoveisCartasNaoImpressas() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idGrupoFaturamento = (Integer) getParametro("idGrupo");
		Collection<Object[]> colecaoRelatorio = (Collection<Object[]>) getParametro("colecaoRelatorio");
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();
		Fachada fachada = Fachada.getInstancia();
		
		Iterator<Object[]> it = colecaoRelatorio.iterator();
		while(it.hasNext()){
			Object[] obj = it.next();
			RelatorioImoveisCartasNaoImpressasBean bean = new RelatorioImoveisCartasNaoImpressasBean();
			
			//Munic�pio
			bean.setIdMunicipio((Integer)obj[5]);
			bean.setMunicipio((String)obj[8]);
			
			//Grupo Faturamento
			bean.setIdGrupoFaturamento((Integer)obj[6]);
			bean.setFaturamentoGrupo((String)obj[9]);
			
			//M�s/Ano Faturamento
			bean.setAnoMesFaturamento(Util.formatarAnoMesParaMesAno((Integer)obj[7]));
			
			//N�mero da OS
			bean.setNumeroOS(String.valueOf((Integer)obj[0]));
			
			//Matr�cula Im�vel
			bean.setMatriculaImovel(String.valueOf((Integer)obj[1]));
			
			//Motivo
			bean.setMotivoEncerramento((String)obj[2]);
			
			//Data gera��o
			bean.setDataGeracao(Util.formatarData((Date)obj[3]));
			
			//Data encerramento
			bean.setDataEncerramento(Util.formatarData((Date)obj[4]));
			
			relatorioBeans.add(bean);
		}
		

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioAlteracoesSistemaColunaBean relatorioAlteracoesSistemaColunaBean = 
					new RelatorioAlteracoesSistemaColunaBean();
				
				relatorioBeans.add(relatorioAlteracoesSistemaColunaBean);
				
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		
		}else{
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_CARTAS_NAO_IMPRESSAS,
				parametros, ds, tipoFormatoRelatorio);
							
		}
		
		
		//Enviando o relat�rio via email
		//-------------------------------------
		FiltroFaturamentoGrupo filtroFatG = new FiltroFaturamentoGrupo();
		filtroFatG.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, idGrupoFaturamento));
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)Util.retonarObjetoDeColecao(
				fachada.pesquisar(filtroFatG, FaturamentoGrupo.class.getName())			
				);
		
		try {
			
			EnvioEmail envioEmail = 
					fachada
						.pesquisarEnvioEmail(EnvioEmail.RELATORIO_IMOVEIS_CARTAS_NAO_IMPRESSAS);
			
			String titulo = envioEmail.getTituloMensagem();
			String corpo = envioEmail.getCorpoMensagem();
			String from = envioEmail.getEmailRemetente();
			String to = envioEmail.getEmailReceptor();
			String nomeArquivo = "OSEncNaoExecG"+faturamentoGrupo.getDescricaoAbreviada()+faturamentoGrupo.getAnoMesReferencia().toString();
			
			//Gravando o relat�rio em arquivo
			File relatorio = new File(nomeArquivo+".pdf");
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(relatorio.getAbsolutePath()));
			output.write(retorno);
			output.close();
			
			
			//Enviado o relat�rio
			ServicosEmail.enviarMensagemArquivoAnexado(
					to, from, titulo,
					corpo, relatorio);	
			
		} catch (Exception e1) {
			throw new TarefaException("Erro ao enviar o relat�rio por email.", e1);
		}
		
		
		//-------------------------------------
		

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_CARTAS_NAO_IMPRESSAS,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisCartasNaoImpressas", this);
	}

}
