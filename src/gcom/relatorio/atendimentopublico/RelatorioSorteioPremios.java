package gcom.relatorio.atendimentopublico;

import gcom.cadastro.Sorteios;
import gcom.fachada.Fachada;
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

/**
 * [UC1295] Efetuar Sorteio de Prêmios
 * 
 * @author Mariana Victor
 * @since 09/03/2012
 */
public class RelatorioSorteioPremios extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioSorteioPremios(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public RelatorioSorteioPremios() {
		super(null, "");
	}
	
	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		//  tipo relatorio
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String dataSorteio = (String) getParametro("dataSorteio");
		String sorteio = (String) getParametro("sorteio");
		Integer idSorteio = new Integer((String) getParametro("idSorteio"));
		String numeroSorteioFiqueLegal2013 = (String) getParametro("numeroSorteioFiqueLegal2013");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List<RelatorioSorteioPremiosBean> relatorioBeans = new ArrayList<RelatorioSorteioPremiosBean>();
		
		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("imagem", fachada.pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("dataSorteio", dataSorteio);
		parametros.put("sorteio", sorteio);
	
		Collection<RelatorioSorteioPremiosHelper> colecaoHelper = new ArrayList<RelatorioSorteioPremiosHelper>();
		// 2.1.	Caso o tipo do sorteio seja "Sorteio para Adimplentes":
		if (idSorteio.compareTo(Sorteios.SORTEIO_ADIMPLENTES) == 0) {
			
			colecaoHelper = 
					fachada.pesquisarDadosRelatorioImoveisSorteados();
			
		// 2.2.	Caso Contrário, caso o tipo do sorteio seja "Sorteio Fique Legal"
		} else if (idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL) == 0) {
			
			colecaoHelper = 
					fachada.pesquisarDadosRelatorioImoveisSorteadosFiqueLegal();
			
		// 2.3.	Caso contrário, ou seja, "Sorteio Mensal":
		} else if (idSorteio.compareTo(Sorteios.SORTEIO_MENSAL) == 0) {
			
			colecaoHelper = 
					fachada.pesquisarDadosRelatorioImoveisSorteadosMensal();
			
		} else if(idSorteio.compareTo(Sorteios.SORTEIO_FIQUE_LEGAL_2013) == 0){
			colecaoHelper = 
					fachada.obterDadosRelatorioSorteioFiqueLegal2013(numeroSorteioFiqueLegal2013);
		}
		
		if(numeroSorteioFiqueLegal2013!=null && !numeroSorteioFiqueLegal2013.trim().equals("")){
			numeroSorteioFiqueLegal2013+="°";
		}else{
			numeroSorteioFiqueLegal2013 = "";
		}
		parametros.put("numeroSorteio", numeroSorteioFiqueLegal2013);
		
		for (RelatorioSorteioPremiosHelper helper : colecaoHelper) {
			
			RelatorioSorteioPremiosBean bean = new RelatorioSorteioPremiosBean();
			bean.setPremio(helper.getPremio());
			bean.setOrdemPremio(helper.getOrdemPremio());
			bean.setNumeroSorteio(helper.getNumeroSorteio());
			bean.setMatricula(helper.getMatricula());
			bean.setNomeCliente(helper.getNomeCliente());
			bean.setCpfCliente(helper.getCpfCliente());
			bean.setEndereco(helper.getEndereco());
			bean.setGerenciaRegional(helper.getGerenciaRegional());
			bean.setLocalidade(helper.getLocalidade());
			
			relatorioBeans.add(bean);
		}

		//Caso não possua dados aprensetar mensagem de relatorio vazio ao usuario.
		if(Util.isVazioOrNulo(relatorioBeans)){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioSorteioPremiosBean relatorioSorteioPremiosBean = 
				new RelatorioSorteioPremiosBean();
			
			relatorioBeans.add(relatorioSorteioPremiosBean);
			
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(this.nomeRelatorio,parametros, ds, tipoFormatoRelatorio);
			
		// ------------------------------------
		// Grava o relatório no sistema
		/*try {

			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
			
			persistirRelatorioConcluido(retorno,
					Relatorio.REL_ALTERACOES_NO_SISTEMA_COLUNA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}*/
		// ------------------------------------
		
		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioSorteioPremios", this);
	}
}
