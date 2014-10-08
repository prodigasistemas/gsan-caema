package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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
import java.util.List;
import java.util.Map;

public class RelatorioImoveisContasRetiradosEmpresasCobranca extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioImoveisContasRetiradosEmpresasCobranca(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_CONTAS_RETIRADOS_EMPRESAS_COBRANCA);
		}

		@Deprecated
		public RelatorioImoveisContasRetiradosEmpresasCobranca() {
			super(null, "");
		}
		
		public Object executar() throws TarefaException {

			
			// ------------------------------------
			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
			// ------------------------------------
			
			
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
			Empresa empresa = (Empresa)getParametro("empresa");
			String referenciaInicial = (String)getParametro("referenciaInicial");
			String referenciaFinal = (String)getParametro("referenciaFinal");
			
			// valor de retorno
			byte[] retorno = null;

			Fachada fachada = Fachada.getInstancia();
			
			//Busca
			Collection colecaoRelatorio = 
					fachada.obterRelatorioImoveisContasRetiradosEmpresasCobranca(empresa.getId(), Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(referenciaInicial)), Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(referenciaFinal)));
			
			//Convertendo para lista
			List lista = new ArrayList(colecaoRelatorio);
			//Collections.sort(lista);
			

			// Par�metros do relat�rio
			Map parametros = new HashMap();
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("empresa", empresa.getDescricao());
			parametros.put("referenciaInicial", referenciaInicial);
			parametros.put("referenciaFinal", referenciaFinal);
			
			
			RelatorioDataSource ds = new RelatorioDataSource(lista);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_CONTAS_RETIRADOS_EMPRESAS_COBRANCA,
			parametros, ds, tipoFormatoRelatorio);
			
			
			// ------------------------------------
			// Grava o relat�rio no sistema
			try {
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_CONTAS_RETIRADOS_EMPRESAS_COBRANCA, idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException(	"Erro ao gravar relat�rio no sistema",
											e);
			}
			// ------------------------------------
			
			return retorno;
		}
	    

		@Override
		public int calcularTotalRegistrosRelatorio() {
			return 1;
		}

		public void agendarTarefaBatch() {
			AgendadorTarefas.agendarTarefa("RelatorioImoveisContasRetiradosEmpresasCobranca", this);
		}
		
}
