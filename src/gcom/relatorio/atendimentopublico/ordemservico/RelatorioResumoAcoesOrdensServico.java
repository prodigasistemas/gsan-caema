package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OrdemServicoSituacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.ServicoTipoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.CobrancaDebitoSituacaoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC 1559] - Consultar Resumo das Ações de Ordem de Serviço
 * 
 * @author Davi Menezes
 * @date 23/09/2013
 *
 */
public class RelatorioResumoAcoesOrdensServico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioResumoAcoesOrdensServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_ACOES_ORDEM_SERVICO);
	}
	
	@Deprecated
	public RelatorioResumoAcoesOrdensServico(){
		super(null, "");
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Fachada fachada = Fachada.getInstancia();
		
		//coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Integer tipoRelatorio =  Integer.parseInt((String) getParametro("tipoRelatorio"));
		Collection<ServicoTipoHelper> colecaoServicoTipoHelper = (Collection<ServicoTipoHelper>) getParametro("colecaoServicoTipoHelper");
		Integer idServicoTipo = Integer.parseInt((String) getParametro("idServicoTipo"));
		String mesAnoReferencia = (String) getParametro("mesAnoReferencia");
		
		RelatorioResumoAcoesOrdensServicoBean relatorioBean = null;
		
		ServicoTipoHelper servicoTipoHelper = null;
		
		boolean primeiraOSSituacao = true;
		
		Iterator<ServicoTipoHelper> it = colecaoServicoTipoHelper.iterator();
		while(it.hasNext()){
			servicoTipoHelper = (ServicoTipoHelper) it.next();
			
			if(servicoTipoHelper.getId().equals(idServicoTipo)){
				for(OrdemServicoSituacaoHelper osHelper : servicoTipoHelper.getColecaoSituacaoOS()){
					primeiraOSSituacao = true;
					
					relatorioBean = new RelatorioResumoAcoesOrdensServicoBean();
					relatorioBean.setIdServicoTipo(idServicoTipo);
					relatorioBean.setDescricaoServicoTipo(servicoTipoHelper.getDescricao());
					relatorioBean.setQtdServicoTipo(servicoTipoHelper.getQuantidadeOS());
					relatorioBean.setValorServicoTipo(servicoTipoHelper.getValorContas());
					
					relatorioBean.setIdOrdemServicoSituacao(osHelper.getId());
					relatorioBean.setDescricaoOrdemServicoSituacao(osHelper.getDescricao());
					relatorioBean.setQtdOrdemServicoSituacao(osHelper.getQuantidadeOS());
					relatorioBean.setPercentualQtdOrdemServicoSituacao(osHelper.getPercentualQuantidade());
					relatorioBean.setValorOrdemServicoSituacao(osHelper.getValorContas());
					relatorioBean.setPercentualValorOrdemServicoSituacao(osHelper.getPercentualValor());
					
					if(!Util.isVazioOrNulo(osHelper.getColecaoDebitos())){
						for(CobrancaDebitoSituacaoHelper cobrancaDebitoHelper : osHelper.getColecaoDebitos()){
							if(!primeiraOSSituacao){
								relatorioBean = new RelatorioResumoAcoesOrdensServicoBean();
								relatorioBean.setIdServicoTipo(idServicoTipo);
								relatorioBean.setDescricaoServicoTipo(servicoTipoHelper.getDescricao());
								relatorioBean.setQtdServicoTipo(servicoTipoHelper.getQuantidadeOS());
								relatorioBean.setValorServicoTipo(servicoTipoHelper.getValorContas());
								
								relatorioBean.setIdOrdemServicoSituacao(osHelper.getId());
								relatorioBean.setDescricaoOrdemServicoSituacao(osHelper.getDescricao());
								relatorioBean.setQtdOrdemServicoSituacao(osHelper.getQuantidadeOS());
								relatorioBean.setPercentualQtdOrdemServicoSituacao(osHelper.getPercentualQuantidade());
								relatorioBean.setValorOrdemServicoSituacao(osHelper.getValorContas());
								relatorioBean.setPercentualValorOrdemServicoSituacao(osHelper.getPercentualValor());
							}
							
							relatorioBean.setIdCobrancaDebitoSituacao(cobrancaDebitoHelper.getId());
							relatorioBean.setDescricaoCobrancaDebitoSituacao(cobrancaDebitoHelper.getDescricao());
							relatorioBean.setQtdCobrancaDebitoSituacao(cobrancaDebitoHelper.getQuantidadeOS());
							relatorioBean.setPercentualQtdCobrancaDebitoSituacao(cobrancaDebitoHelper.getPercentualQuantidade());
							relatorioBean.setValorCobrancaDebitoSituacao(cobrancaDebitoHelper.getValorContas());
							relatorioBean.setPercentualValorCobrancaDebitoSituacao(cobrancaDebitoHelper.getPercentualValor());
							
							relatorioBeans.add(relatorioBean);
							primeiraOSSituacao = false;
						}
					}else{
						relatorioBeans.add(relatorioBean);
					}
				}
			}
		}
		
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1559");
		parametros.put("mesAnoReferencia", mesAnoReferencia);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_ACOES_ORDEM_SERVICO,
				parametros, ds, tipoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_RESUMO_ACOES_ORDEM_SERVICO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoAcoesOrdensServico", this);
	}

}
