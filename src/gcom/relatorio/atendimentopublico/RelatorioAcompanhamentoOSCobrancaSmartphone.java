package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.atendimentopublico.RelatorioAcompanhamentoOSCobrancaSmartphoneHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobrança para Smartphone 
 * 
 * @author Anderson Cabral
 * @since 19/08/2013
 */
public class RelatorioAcompanhamentoOSCobrancaSmartphone extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioAcompanhamentoOSCobrancaSmartphone(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {

		RelatorioAcompanhamentoOSCobrancaSmartphoneHelper helper = (RelatorioAcompanhamentoOSCobrancaSmartphoneHelper) getParametro("helper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap<String, String>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1536");
		parametros.put("desEmpresa", helper.getEmpresa().getDescricao());
		parametros.put("anoMesReferencia", Util.formatarAnoMesParaMesAno(helper.getAnoMesReferencia()));
		if(helper.getGerenciaRegional() != null && helper.getGerenciaRegional().getId() != null){
			parametros.put("desGerenciaRegional", helper.getGerenciaRegional().getNome());
		}
		if(helper.getUnidadeNegocio() != null && helper.getUnidadeNegocio().getId() != null){
			parametros.put("desUnidadeNegocio", helper.getUnidadeNegocio().getNome());
		}
		if(helper.getLocalidade() != null && helper.getLocalidade().getId() != null){
			parametros.put("desLocalidade", helper.getLocalidade().getDescricao());
		}
		
		String nomesServicoTipo = "";
		
		if(helper.getIdsTipoServico() != null && helper.getIdsTipoServico().size() > 0){
			int cont = 0;
			for(String id : helper.getIdsTipoServico()){
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.SIM));
				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, Integer.parseInt(id)));
				Collection<ServicoTipo> colecaoServicoTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
				
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				
				nomesServicoTipo += servicoTipo.getDescricao() + ", ";
				
				if(cont == 6){
					nomesServicoTipo = nomesServicoTipo.substring(0, nomesServicoTipo.length() -2) + "...";
					break;
				}else{
					cont++;
				}
				
			}
			
			if(cont != 6){
				nomesServicoTipo = nomesServicoTipo.substring(0, nomesServicoTipo.length() -2);
			}
		}
		
		parametros.put("servicoTipo", nomesServicoTipo);
		
		if(helper.getTipoRelatorio().equals("1")){
			parametros.put("tipoRelatorio", "Analítico");
		}else{
			parametros.put("tipoRelatorio", "Sintético");
		}
		
		ArrayList<RelatorioAcompanhamentoOSCobrancaSmartphoneBean> colecaoBean = Fachada.getInstancia().pesquisarOSCobrancaSmartphone(helper);
		
		if(helper == null || Util.isVazioOrNulo(colecaoBean)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioAcompanhamentoOSCobrancaSmartphoneBean>) colecaoBean);
		
		byte[] retorno = null;
		if(helper.getTipoRelatorio().equalsIgnoreCase("1")){
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_ANALITICO, parametros, ds, tipoFormatoRelatorio);
		}else if(helper.getTipoRelatorio().equalsIgnoreCase("2")){
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_SINTETICO, parametros, ds, tipoFormatoRelatorio);
		}
		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoOSCobrancaSmartphone", this);
	}
}