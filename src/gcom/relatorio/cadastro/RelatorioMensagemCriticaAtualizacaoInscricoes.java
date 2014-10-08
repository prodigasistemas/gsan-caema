package gcom.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.cadastro.FiltroImovelInscricaoResetorizada;
import gcom.cadastro.ImovelInscricaoResetorizada;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1389] - Limpar Mensagens de Crítica Para Atualização das Inscrições
 * 
 * @author Davi Menezes
 * @date 12/11/2012
 *
 */
public class RelatorioMensagemCriticaAtualizacaoInscricoes extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioMensagemCriticaAtualizacaoInscricoes(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MENSAGEM_CRITICA_ATUALIZACAO_INSCRICOES);
	}
	
	public RelatorioMensagemCriticaAtualizacaoInscricoes(){
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		//valor de Retorno
		byte[] retorno = null;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String idLocalidade = (String) getParametro("idLocalidade");
		
		String descricaoLocalidade = (String) getParametro("descricaoLocalidade");
		
		String codigoSetorComercial = (String) getParametro("codigoSetorComercial");
		
		String descricaoSetorComercial = (String) getParametro("descricaoSetorComercial");
		
		String mensagemCritica = (String) getParametro("mensagemCritica");
		
		List<RelatorioMensagemCriticaAtualizacaoInscricoesBean> beans = new ArrayList<RelatorioMensagemCriticaAtualizacaoInscricoesBean>();
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroImovelInscricaoResetorizada filtroImovel = new FiltroImovelInscricaoResetorizada(FiltroImovelInscricaoResetorizada.OCORRENCIA_RESETORIZACAO);
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.ID_LOCALIDADE, idLocalidade));
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.INDICADOR_ATUALIZACAO, ConstantesSistema.NAO));
		
		if(codigoSetorComercial != null && !codigoSetorComercial.equals("")){
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		}
		
		if(mensagemCritica != null && !mensagemCritica.equals("") && !mensagemCritica.equals("-1")){
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.ID_OCORRENCIA_RESETORIZACAO, mensagemCritica));
		}else{
			filtroImovel.adicionarParametro(new ParametroNaoNulo(FiltroImovelInscricaoResetorizada.OCORRENCIA_RESETORIZACAO));
		}
		
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelInscricaoResetorizada.OCORRENCIA_RESETORIZACAO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelInscricaoResetorizada.IMOVEL);
		
		Collection<?> colecaoClientesBean = fachada.pesquisar(filtroImovel, ImovelInscricaoResetorizada.class.getName());
		
		if(Util.isVazioOrNulo(colecaoClientesBean)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}else{
			ImovelInscricaoResetorizada imovelInscricaoResetorizada = null;
			RelatorioMensagemCriticaAtualizacaoInscricoesBean bean = null;
			
			Iterator<?> it = colecaoClientesBean.iterator();
			while(it.hasNext()){
				bean = new RelatorioMensagemCriticaAtualizacaoInscricoesBean();
				imovelInscricaoResetorizada = (ImovelInscricaoResetorizada) it.next();
				
				bean.setIdMensagemCritica(String.valueOf(imovelInscricaoResetorizada.getOcorrenciaResetorizacao().getId()));
				bean.setDescricaoMensagemCritica(imovelInscricaoResetorizada.getOcorrenciaResetorizacao().getDescricaoMensagem());
				bean.setIdImovel(imovelInscricaoResetorizada.getImovel().getMatriculaFormatada());
				
				beans.add(bean);
			}
		}
		
		String localidade = idLocalidade + " - " + descricaoLocalidade;
		String setorComercial = ""; 
		
		if(codigoSetorComercial != null && !codigoSetorComercial.equals("") 
				&& descricaoSetorComercial != null && !descricaoSetorComercial.equals("")){
			setorComercial = codigoSetorComercial + " - " + descricaoSetorComercial;
		}
		
		//Paramêtros do Relatório
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//adiciona os parametros no relatorio
		parametros.put("tipoFormatoRelatorio", "R1389");
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("descricaoLocalidade", localidade);
		parametros.put("descricaoSetorComercial", setorComercial);
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_MENSAGEM_CRITICA_ATUALIZACAO_INSCRICOES;
		
		//cria uma instancia do data source no relatório
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		retorno = gerarRelatorio(nomeRelatorio, parametros, ds, tipoFormatoRelatorio);
		
		//retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMensagemCriticaAtualizacaoInscricoes", this);
	}

}
