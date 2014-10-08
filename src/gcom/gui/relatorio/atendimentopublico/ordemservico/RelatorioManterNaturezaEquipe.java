package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.EquipeNatureza;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioManterNaturezaEquipe extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioManterNaturezaEquipe(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NATUREZA_EQUIPE_MANTER);
	}
	
	@Deprecated
	public RelatorioManterNaturezaEquipe(){
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Collection<EquipeNatureza> colecaoNaturezaEquipe = (Collection<EquipeNatureza>) getParametro("colecaoNaturezaEquipe");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		RelatorioManterNaturezaEquipeBean relatorioBean = null;
		
		if(!Util.isVazioOrNulo(colecaoNaturezaEquipe)){
			for(EquipeNatureza equipeNatureza : colecaoNaturezaEquipe){
				relatorioBean = new RelatorioManterNaturezaEquipeBean();
				relatorioBean.setDescricao(equipeNatureza.getDescricao());
				relatorioBean.setDescricaoAbreviada(equipeNatureza.getDescricaoAbreviada());
				
				if(equipeNatureza.getIndicadorUso().equals(ConstantesSistema.SIM)){
					relatorioBean.setIndicadorUso("Ativo");
				}else{
					relatorioBean.setIndicadorUso("Inativo");
				}
				
				relatorioBeans.add(relatorioBean);
			}
		
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_NATUREZA_EQUIPE_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_CLIENTE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterNaturezaEquipe", this);
	}

}
