package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cobranca.bean.RelatorioCobrancaPorResultadoPorComandoMesHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioCobrancaPorResultadoPorComandoMes;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0000] Relatório Opção Totalização por Comando e Opção de Totalização por Mês de Apuração
 * 
 * @author Vivianne Sousa
 * @date 24/04/2014
 */
public class GerarRelatorioCobrancaPorResultadoPorComandoMesAction extends ExibidorProcessamentoTarefaRelatorio {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioCobrancaPorResultadoComandoMes");
        
        Fachada fachada = Fachada.getInstancia();
        
        GerarRelatorioCobrancaPorResultadoComandoMesActionForm form = (GerarRelatorioCobrancaPorResultadoComandoMesActionForm) actionForm;
        
        int tipoFormatoRelatorio = TarefaRelatorio.TIPO_PDF;
        short exibeTotalizacaoGerencia = ConstantesSistema.SIM;
        short exibeMesAnoNaTotalizacao = ConstantesSistema.SIM;
        String opcaoRelatorio = "";
        String idEmpresa = form.getIdEmpresaContratada();
        String[] idsGerenciaRegional = form.getIdsGerenciaRegional();
        String[] idsUnidadeNegocio = form.getIdsUnidadeNegocio();
        String idLocalidade = form.getIdLocalidade();	
        
        Integer IntIdLocalidade = null;
    	if(idLocalidade != null && !idLocalidade.equals("")){
    		IntIdLocalidade = new Integer(idLocalidade);
    	}
    	
        String[] idsRegiao = form.getIdsRegiao();
        String[] idsMicroRegiao = form.getIdsMicroregiao();
        String[] idsMunicipios = form.getIdsMunicipio();
        String[] idsCategoria = form.getIdsCategoria();
        String idComando = form.getIdComando();	
        
        Integer IntIdComando = null;
        if(idComando != null && !idComando.equals("") && !idComando.equals("-1")){
        	IntIdComando = new Integer(idComando);
        }
        	
        String periodo = "";
		String periodoCicloInicial = form.getPeriodoComandoInicial();
		String periodoCicloFinal = form.getPeriodoComandoFinal();

		Date cicloInicial = null;
		Date cicloFinal = null;
        	
		if (periodoCicloFinal != null && !periodoCicloFinal.equals("")
		 && periodoCicloInicial != null && !periodoCicloInicial.equals("")) {

			cicloInicial = Util.converteStringParaDate(periodoCicloInicial);
			cicloFinal = Util.converteStringParaDate(periodoCicloFinal);

			if (cicloInicial.compareTo(cicloFinal) > 0) {
				throw new ActionServletException("atencao.data_inicial_periodo_ciclo.posterior.data_final_periodo_ciclo");
			}
			opcaoRelatorio = ConstantesSistema.TOTALIZACAO_POR_MES_DE_APURACAO;
			periodo = periodoCicloInicial + " a " + periodoCicloFinal;
		}

		String anoMesReferencia = null;
		String encerramentoArrecadacao  = null;
		if(form.getPeriodoApuracao() != null && !form.getPeriodoApuracao().equals("")){
			  //Validação ano/mês de referência
	        
	        if(this.validarMesAno(form.getPeriodoApuracao())){
	        	anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getPeriodoApuracao());
	        	opcaoRelatorio = ConstantesSistema.TOTALIZACAO_POR_COMANDO;
	        	exibeMesAnoNaTotalizacao = ConstantesSistema.NAO;
	        	periodo = Util.formatarAnoMesParaMesAno(anoMesReferencia);
	        }
	        
	        //validação da data de encerramento    	
	    	//Somando um mês ao período de referência
			String mesSomado = Util.somaMesMesAnoComBarra(form.getPeriodoApuracao(), 1);
			
			//Número do Processo
			Integer idProcesso = new Integer(50);
			
			Date dataEncerramento = fachada.obterDataEncerramentoProcesso(idProcesso, mesSomado);
			
//			if(dataEncerramento == null){
//				throw new ActionServletException("atencao.dados_gerados_boletim_inexistentes", null, form.getPeriodoApuracao());
//			}
//	    	
			encerramentoArrecadacao = Util.formatarData(dataEncerramento);
//	        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
//	        Integer mesAnoArrecadacao = sistemaParametro.getAnoMesArrecadacao();
	//
//	        if(Util.converterStringParaInteger(anoMesReferencia).intValue() >= mesAnoArrecadacao.intValue() ){
//	        	throw new ActionServletException("atencao.dados_gerados_boletim_inexistentes", null, form.getPeriodoApuracao());
//	        }
		}
		
        short indicadorLocalidade;
        //Indicador de uso do filtro de Localidade
        if((	idsRegiao      != null && (idsRegiao.length >= 1      && !idsRegiao[0].equals("-1"))) 
			|| (idsMicroRegiao != null && (idsMicroRegiao.length >= 1 && !idsMicroRegiao[0].equals("-1")))
			|| (idsMunicipios  != null && (idsMunicipios.length >= 1  && !idsMunicipios[0].equals("-1"))) ){
        	indicadorLocalidade = ConstantesSistema.NAO;
        }else{
        	indicadorLocalidade = ConstantesSistema.SIM;
        }

        if((idsMicroRegiao != null && (idsMicroRegiao.length >= 1 && !idsMicroRegiao[0].equals("-1")))
        || (idsMunicipios  != null && (idsMunicipios.length >= 1  && !idsMunicipios[0].equals("-1")))
        ||(idsUnidadeNegocio != null && (idsUnidadeNegocio.length >= 1 && !idsUnidadeNegocio[0].equals("-1")))
        || (idLocalidade  != null &&  !idLocalidade.equals(""))){
            exibeTotalizacaoGerencia = ConstantesSistema.NAO;
        }
        
        Collection<RelatorioCobrancaPorResultadoPorComandoMesHelper> colecaoRelatorioHelper = 
        		fachada.gerarDadosRelatorioCobrancaPorResultadoPorComandoMes(new Integer(idEmpresa),
        		anoMesReferencia, IntIdLocalidade, idsGerenciaRegional, idsUnidadeNegocio, idsRegiao,
        		idsMicroRegiao, idsMunicipios, indicadorLocalidade, cicloInicial, cicloFinal, idsCategoria, IntIdComando);
        
        if(colecaoRelatorioHelper.size() == 0){
        	throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "relatório de acompanhamento dos comandos de cobrança");
        }
        
		// Flag para tela de sucesso apos a tela de espera de processamento de relatorio
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioCobrancaPorResultadoPorComandoMes relatorioCobrancaPorResultadoPorComandoMes = new RelatorioCobrancaPorResultadoPorComandoMes(
			(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		if(indicadorLocalidade == ConstantesSistema.INDICADOR_USO_ATIVO)
			relatorioCobrancaPorResultadoPorComandoMes.addParametro("filtroLocalidade", ConstantesSistema.SIM);
          else
        	relatorioCobrancaPorResultadoPorComandoMes.addParametro("filtroLocalidade", ConstantesSistema.NAO);
          
		relatorioCobrancaPorResultadoPorComandoMes.addParametro("colecaoRelatorioHelper", colecaoRelatorioHelper);
        relatorioCobrancaPorResultadoPorComandoMes.addParametro("tipoFormatoRelatorio",tipoFormatoRelatorio);
        relatorioCobrancaPorResultadoPorComandoMes.addParametro("periodo",periodo);
        relatorioCobrancaPorResultadoPorComandoMes.addParametro("encerramentoArrecadacao",encerramentoArrecadacao);
        relatorioCobrancaPorResultadoPorComandoMes.addParametro("opcaoRelatorio", opcaoRelatorio);
        
        relatorioCobrancaPorResultadoPorComandoMes.addParametro("exibeTotalizacaoGerencia",exibeTotalizacaoGerencia);
        relatorioCobrancaPorResultadoPorComandoMes.addParametro("exibeMesAnoNaTotalizacao",exibeMesAnoNaTotalizacao);
		
		retorno = processarExibicaoRelatorio(relatorioCobrancaPorResultadoPorComandoMes, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
              
        return retorno;
    }
    
    
    private boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			String barra = mesAnoReferencia.substring(2, 3);

			try {
				int mesInt = Integer.parseInt(mes);
				// int anoInt = Integer.parseInt(ano);

				if (mesInt > 12 || !barra.equals("/")) {
					mesAnoValido = false;
				}
			} catch (NumberFormatException e) {
				mesAnoValido = false;
			}

		} else {
			mesAnoValido = false;
		}

		return mesAnoValido;
	}
    
}
