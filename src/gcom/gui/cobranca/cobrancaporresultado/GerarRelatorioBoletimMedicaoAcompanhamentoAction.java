package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cadastro.EnvioEmail;
import gcom.cobranca.bean.RelatorioBoletimMedicaoAcompanhamentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.GerenciadorExecucaoTarefaRelatorio;
import gcom.relatorio.RelatorioProcessado;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioAcompanhamentoCreditosPagoAVista;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditos;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioAcompanhamentoParcelamentosRecuperacaoCreditos;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioBoletimMedicaoRecuperacaoCreditos;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioBoletimMedicaoRecuperacaoCreditosParcelados;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ServicosEmail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
 * 
 * @author Hugo Azevedo
 * @date 13/10/2011
 */
public class GerarRelatorioBoletimMedicaoAcompanhamentoAction extends ExibidorProcessamentoTarefaRelatorio {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioBoletimMedicaoAcompanhamento");
        
        Fachada fachada = Fachada.getInstancia();
        
        GerarRelatorioBoletimMedicaoAcompanhamentoActionForm form = (GerarRelatorioBoletimMedicaoAcompanhamentoActionForm) actionForm;
        
        int tipoFormatoRelatorio = TarefaRelatorio.TIPO_PDF;
        
        TarefaRelatorio relatorio = null;
        TarefaRelatorio relatorio2 = null;
        
        StringBuilder txt = null;
        StringBuilder txt2 = null;
        
        String idEmpresa = form.getIdEmpresaContratada();
        String[] idsGerenciaRegional = form.getIdsGerenciaRegional();
        String[] idsUnidadeNegocio = form.getIdsUnidadeNegocio();
        String idLocalidade = form.getIdLocalidade();	
        Integer IntIdLocalidade = null;
        	if(idLocalidade != null && !idLocalidade.equals(""))
        		IntIdLocalidade = new Integer(idLocalidade);
        String[] idsRegiao = form.getIdsRegiao();
        String[] idsMicroRegiao = form.getIdsMicroregiao();
        String[] idsMunicipios = form.getIdsMunicipio();
        String opcaoBoletim = form.getOpcaoBoletim();
        String opcaoRelatorio = form.getOpcaoRelatorio();
        String formatoRelatorio = form.getFormatoRelatorio();
        
        //Validação ano/mês de referência
        String anoMesReferencia = "";
        if(this.validarMesAno(form.getPeriodoApuracao()))
        	anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getPeriodoApuracao());
        else
        	throw new ActionServletException("atencao.data_invalida", null,form.getPeriodoApuracao());
        
        
        //validação da data de encerramento    	
    	//Somando um mês ao período de referência
		String mesSomado = Util.somaMesMesAnoComBarra(form.getPeriodoApuracao(), 1);
		
		//Número do Processo
		Integer idProcesso = new Integer(50);
		
		Date dataEncerramento = fachada.obterDataEncerramentoProcesso(idProcesso, mesSomado);
//		
//		if(dataEncerramento == null){
//			throw new ActionServletException("atencao.dados_gerados_boletim_inexistentes", null, form.getPeriodoApuracao());
//		}
//    	
		String encerramentoArrecadacao = Util.formatarData(dataEncerramento);
//        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
//        Integer mesAnoArrecadacao = sistemaParametro.getAnoMesArrecadacao();
//
//        if(Util.converterStringParaInteger(anoMesReferencia).intValue() >= mesAnoArrecadacao.intValue() ){
//        	throw new ActionServletException("atencao.dados_gerados_boletim_inexistentes", null, form.getPeriodoApuracao());
//        }
        
        
        short indicadorOperacao = 0;
        short indicadorLocalidade;
        
        //4.1. Caso informada a Opção do Boletim=Geral e Opção do Relatório=Resumido,
        //     [SB0001 - Emitir Relatório de Boletim de Medição - Recuperação de Créditos]
        if("1".equals(opcaoBoletim) && "2".equals(opcaoRelatorio)){
        	indicadorOperacao = 1;
        }
        
        //4.2. Caso informada a Opção do Boletim=Geral e Opção do Relatório=Analítico, 
        //     [SB0002 - Emitir Relatório de Acompanhamento dos Créditos Pagos Ã  Vista];
        else if("1".equals(opcaoBoletim) && "1".equals(opcaoRelatorio)){
        	indicadorOperacao = 2;   
        }
        
        //4.3. Caso informada a Opção do Boletim=Parcelamento e Opção do Relatório=Resumido,
        //     [SB0003 - Emitir Relatório de Boletim de Medição - Recuperação de Créditos Parcelados]
        else if("2".equals(opcaoBoletim) && "2".equals(opcaoRelatorio)){
        	indicadorOperacao = 3;   
	        
        }
        
        //4.4. Caso informada a Opção do Boletim= Parcelamento e Opção do Relatório=Analítico,
        else if("2".equals(opcaoBoletim) && "1".equals(opcaoRelatorio)){
        	indicadorOperacao = 4;
        }
        
     
        
//        //Indicador de uso do filtro de Localidade
//        if(((idsGerenciaRegional != null && idsGerenciaRegional.length == 1 && idsGerenciaRegional[0].equals("-1")) 
//        		|| (idsUnidadeNegocio != null  && idsUnidadeNegocio.length == 1 && idsUnidadeNegocio[0].equals("-1"))
//        		|| (IntIdLocalidade != null)) 
//        		||
//        		((idsRegiao == null || (idsRegiao.length == 1 && idsRegiao[0].equals("-1"))) 
//        			&& (idsMicroRegiao == null || (idsMicroRegiao.length == 1 && idsMicroRegiao[0].equals("-1")))
//        			&& (idsMunicipios == null || (idsMunicipios.length == 1 && idsMunicipios[0].equals("-1"))) 
//        			&& (idsGerenciaRegional == null || (idsGerenciaRegional.length == 1 && idsGerenciaRegional[0].equals("-1"))) 
//        			&& (idsUnidadeNegocio == null || (idsUnidadeNegocio.length == 0 && idsUnidadeNegocio[0].equals("-1"))) 
//        			&& (IntIdLocalidade == null || IntIdLocalidade.equals(""))))
//        	indicadorLocalidade = ConstantesSistema.SIM;
//        else
//        	indicadorLocalidade = ConstantesSistema.NAO;
        	
        if((	idsRegiao      != null && (idsRegiao.length >= 1      && !idsRegiao[0].equals("-1"))) 
			|| (idsMicroRegiao != null && (idsMicroRegiao.length >= 1 && !idsMicroRegiao[0].equals("-1")))
			|| (idsMunicipios  != null && (idsMunicipios.length >= 1  && !idsMunicipios[0].equals("-1"))) ){
        	indicadorLocalidade = ConstantesSistema.NAO;
        }else{
        	indicadorLocalidade = ConstantesSistema.SIM;
        }
        
        Collection<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins = null;
        Collection<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins2 = null;
        
        if(indicadorOperacao != 4){
	        colecaoBoletins = fachada.gerarDadosRelatorioBoletimMedicaoAcompanhamento(
												new Integer(idEmpresa),
												anoMesReferencia,
												IntIdLocalidade,
												idsGerenciaRegional,
												idsUnidadeNegocio,
												idsRegiao,
												idsMicroRegiao,
												idsMunicipios,
												indicadorOperacao,
												indicadorLocalidade,
												new Short("0"));
        }
        else{
        	colecaoBoletins = fachada.gerarDadosRelatorioBoletimMedicaoAcompanhamento(
					new Integer(idEmpresa),
					anoMesReferencia,
					IntIdLocalidade,
					idsGerenciaRegional,
					idsUnidadeNegocio,
					idsRegiao,
					idsMicroRegiao,
					idsMunicipios,
					indicadorOperacao,
					indicadorLocalidade,
					new Short("1"));
        	
        	colecaoBoletins2 = fachada.gerarDadosRelatorioBoletimMedicaoAcompanhamento(
					new Integer(idEmpresa),
					anoMesReferencia,
					IntIdLocalidade,
					idsGerenciaRegional,
					idsUnidadeNegocio,
					idsRegiao,
					idsMicroRegiao,
					idsMunicipios,
					indicadorOperacao,
					indicadorLocalidade,
					new Short("2"));
        	
        	
        }
        
        if(colecaoBoletins.size() == 0){
        	throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "relatório de boletim de medição");
        }

        switch(indicadorOperacao){
        
	        //4.1. Caso informada a Opção do Boletim=Geral e Opção do Relatório=Resumido,
	        //     [SB0001 - Emitir Relatório de Boletim de Medição - Recuperação de Créditos]
    		case 1:
	        	relatorio = new RelatorioBoletimMedicaoRecuperacaoCreditos(
						(Usuario) (httpServletRequest.getSession(false))
								.getAttribute("usuarioLogado"));
	        break;
	        
	        //4.2. Caso informada a Opção do Boletim=Geral e Opção do Relatório=Analítico, 
	        //     [SB0002 - Emitir Relatório de Acompanhamento dos Créditos Pagos Ã  Vista];
	        case 2:
	        	
	        	
	            if(formatoRelatorio.equals("1")){
	            	//PDF
	                relatorio = new RelatorioAcompanhamentoCreditosPagoAVista(
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado")); 
	            	
	            }else if(formatoRelatorio.equals("2")){
	            	//TXT
	            	txt = fachada.gerarArquivoTxt( encerramentoArrecadacao,
	        	        	colecaoBoletins, indicadorLocalidade, indicadorOperacao, new Short("0"));
	            }else {
	            	//Ambos
	                relatorio = new RelatorioAcompanhamentoCreditosPagoAVista(
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado")); 
	            	
	                txt = fachada.gerarArquivoTxt( encerramentoArrecadacao,
	        	        	colecaoBoletins, indicadorLocalidade, indicadorOperacao, new Short("0"));
	            }
		     
		    break;    
	        
	        //4.3. Caso informada a Opção do Boletim=Parcelamento e Opção do Relatório=Resumido,
	        //     [SB0003 - Emitir Relatório de Boletim de Medição - Recuperação de Créditos Parcelados]
	        case 3:
		        relatorio = new RelatorioBoletimMedicaoRecuperacaoCreditosParcelados(
						(Usuario) (httpServletRequest.getSession(false))
								.getAttribute("usuarioLogado"));   
		        
	        break;
	        
	        //4.4. Caso informada a Opção do Boletim= Parcelamento e Opção do Relatório=Analítico,
	        case 4:
	        	
	            if(formatoRelatorio.equals("1")){
	            	//PDF
		        	//[SB0004 - Emitir Relatório de Acompanhamento dos Parcelamentos - Recuperação de Créditos]
		        	relatorio = new RelatorioAcompanhamentoParcelamentosRecuperacaoCreditos(
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));
		        	
		        	//[SB0005 - Emitir Relatório de Acompanhamento dos Parcelamentos em Atraso - Recuperação de Créditos]
		        	relatorio2 = new RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditos(
			        			(Usuario) (httpServletRequest.getSession(false))
								.getAttribute("usuarioLogado"));
		        	
	            }else if(formatoRelatorio.equals("2")){
	            	//TXT
	            	txt = fachada.gerarArquivoTxt( encerramentoArrecadacao,
	        	        	colecaoBoletins, indicadorLocalidade, indicadorOperacao, new Short("1"));
	            	
	            	txt2 = fachada.gerarArquivoTxt( encerramentoArrecadacao,
	        	        	colecaoBoletins2, indicadorLocalidade, indicadorOperacao, new Short("2"));
	            }else {
	            	//Ambos
	            	
		        	//[SB0004 - Emitir Relatório de Acompanhamento dos Parcelamentos - Recuperação de Créditos]
		        	relatorio = new RelatorioAcompanhamentoParcelamentosRecuperacaoCreditos(
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));
		        	
		        	//[SB0005 - Emitir Relatório de Acompanhamento dos Parcelamentos em Atraso - Recuperação de Créditos]
		        	relatorio2 = new RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditos(
			        			(Usuario) (httpServletRequest.getSession(false))
								.getAttribute("usuarioLogado"));
		        	
	            	txt = fachada.gerarArquivoTxt( encerramentoArrecadacao,
	        	        	colecaoBoletins, indicadorLocalidade, indicadorOperacao, new Short("1"));
	            	
	            	txt2 = fachada.gerarArquivoTxt( encerramentoArrecadacao,
	        	        	colecaoBoletins2, indicadorLocalidade, indicadorOperacao, new Short("2"));
	            }
	        	
	        break;
        }
        
//        if(relatorio != null){
//        
//	        if(indicadorLocalidade == ConstantesSistema.INDICADOR_USO_ATIVO)
//	        	relatorio.addParametro("filtroLocalidade", ConstantesSistema.SIM);
//	        else
//	        	relatorio.addParametro("filtroLocalidade", ConstantesSistema.NAO);
//	        
//	        relatorio.addParametro("colecaoBoletins", colecaoBoletins);
//	        relatorio.addParametro("tipoFormatoRelatorio",tipoFormatoRelatorio);
//	        relatorio.addParametro("mesAno",form.getPeriodoApuracao());
//	        relatorio.addParametro("encerramentoArrecadacao",encerramentoArrecadacao);
//        }
        
        try {
        	boolean gerouTxt = false;
        	ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("relatorio.zip"));
        	FileOutputStream out = null;
        	
	        //Caso tenha entrado no 4.4, tratar o segundo relatório
//	        if(relatorio2 != null){
        	if(indicadorOperacao == 4){
        		
//				ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("relatorio.zip"));
//	        	FileOutputStream out = null;
	        	
                if(relatorio != null){
                    
        	        if(indicadorLocalidade == ConstantesSistema.INDICADOR_USO_ATIVO)
        	        	relatorio.addParametro("filtroLocalidade", ConstantesSistema.SIM);
        	        else
        	        	relatorio.addParametro("filtroLocalidade", ConstantesSistema.NAO);
        	        
        	        relatorio.addParametro("colecaoBoletins", colecaoBoletins);
        	        relatorio.addParametro("tipoFormatoRelatorio",tipoFormatoRelatorio);
        	        relatorio.addParametro("mesAno",form.getPeriodoApuracao());
        	        relatorio.addParametro("encerramentoArrecadacao",encerramentoArrecadacao);
        	        
   		         	RelatorioProcessado relatorioProcessado1 = null;
   		         	
	   	        	//Relatorio1
	   				relatorioProcessado1 = GerenciadorExecucaoTarefaRelatorio
	   						.analisarExecucao(relatorio, tipoFormatoRelatorio);
	   				byte[] 	relatorio1bytes = relatorioProcessado1.getDados();
	   				
					 //Tratando relatório 1
					 File arquivoUM = new File("relatorioParcelamento.pdf");
					 out = new FileOutputStream(arquivoUM
							.getAbsolutePath());
					
					 out.write(relatorio1bytes);
					 out.close();
					 ZipUtil.adicionarArquivo(zip, arquivoUM);
					 arquivoUM.delete();
					 zip.closeEntry();
					
                }
        		
                if(relatorio2 != null){
                	
	   		    	 if(indicadorLocalidade == ConstantesSistema.INDICADOR_USO_ATIVO)
	 		         	relatorio2.addParametro("filtroLocalidade", ConstantesSistema.SIM);
	 		         else
	 		         	relatorio2.addParametro("filtroLocalidade", ConstantesSistema.NAO);
	 		         
	 		         relatorio2.addParametro("colecaoBoletins", colecaoBoletins2);
	 		         relatorio2.addParametro("tipoFormatoRelatorio",tipoFormatoRelatorio);
	 		         relatorio2.addParametro("mesAno",form.getPeriodoApuracao());
	 		         relatorio2.addParametro("encerramentoArrecadacao",encerramentoArrecadacao);
	 		         
			         RelatorioProcessado relatorioProcessado2 = null;
				       
					 //Relatorio2
					 relatorioProcessado2 = GerenciadorExecucaoTarefaRelatorio
							.analisarExecucao(relatorio2, tipoFormatoRelatorio);
					 byte[] relatorio2bytes = relatorioProcessado2.getDados();
					 
					 //Tratando relatório 2
					 File arquivoDOIS = new File("relatorioParcelamentoEmAtraso.pdf");
					 out = new FileOutputStream(arquivoDOIS
							.getAbsolutePath());
					
					 out.write(relatorio2bytes);
					 out.close();
					 ZipUtil.adicionarArquivo(zip, arquivoDOIS);
					 arquivoDOIS.delete();
					 zip.closeEntry();
                }

	        	
                if(txt != null){
                	 gerouTxt = true;
					 File relatorioTXT = new File("relatorioParcelamento.txt");
					 out = new FileOutputStream(relatorioTXT.getAbsolutePath());
					 out.write(txt.toString().getBytes());
					 out.close();
					 ZipUtil.adicionarArquivo(zip, relatorioTXT);
					 relatorioTXT.delete();
					 zip.closeEntry();
	        	 }
                
                if(txt2 != null){
                	 gerouTxt = true;
					 File relatorioTXT = new File("relatorioParcelamentoEmAtraso.txt");
					 out = new FileOutputStream(relatorioTXT.getAbsolutePath());
					 out.write(txt2.toString().getBytes());
					 out.close();
					 ZipUtil.adicionarArquivo(zip, relatorioTXT);
					 relatorioTXT.delete();
					 zip.closeEntry();
	        	 }

//				 //Finalizando arquivo
//				 zip.flush();
//				 zip.close();
//				
//				 //Preparando download
//				 httpServletResponse.setContentType("application/zip");
//				 httpServletResponse.addHeader("Content-Disposition",
//						"attachment; filename=" + "relatorio.zip");
//				 File arquivozip = new File("relatorio.zip");
//				 ServletOutputStream sos = httpServletResponse.getOutputStream();
//				 sos.write(IoUtil.getBytesFromFile(arquivozip));  
//				 sos.flush();
//				 sos.close();
//				
	        } else {
	        	
	        	
//				ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("relatorio.zip"));
//	        	FileOutputStream out = null;
	        	
	        	 if(relatorio != null){
	        		 
	        		if(indicadorLocalidade == ConstantesSistema.INDICADOR_USO_ATIVO)
	     	        	relatorio.addParametro("filtroLocalidade", ConstantesSistema.SIM);
	     	        else
	     	        	relatorio.addParametro("filtroLocalidade", ConstantesSistema.NAO);
	        		
	     	        
	     	        relatorio.addParametro("colecaoBoletins", colecaoBoletins);
	     	        relatorio.addParametro("tipoFormatoRelatorio",tipoFormatoRelatorio);
	     	        relatorio.addParametro("mesAno",form.getPeriodoApuracao());
	     	        relatorio.addParametro("encerramentoArrecadacao",encerramentoArrecadacao); 
	        		 
	 	        	RelatorioProcessado relatorioProcessado = null;
		        	
					relatorioProcessado = GerenciadorExecucaoTarefaRelatorio
							.analisarExecucao(relatorio, tipoFormatoRelatorio);
					byte[] relatoriobytes = relatorioProcessado.getDados();
					
					
					//Preparando para zipar os dois relatórios
					//========================================
					File arquivoUM = new File("relatorio.pdf");
					out = new FileOutputStream(arquivoUM.getAbsolutePath());
					
					out.write(relatoriobytes);
					out.close();
					ZipUtil.adicionarArquivo(zip, arquivoUM);
					arquivoUM.delete();
					zip.closeEntry();
	        	 }
	        	
	        	 if(txt != null){
	        		 gerouTxt = true;
					 File relatorioTXT = new File("relatorio.txt");
					 out = new FileOutputStream(relatorioTXT.getAbsolutePath());
					 out.write(txt.toString().getBytes());
					 out.close();
					 ZipUtil.adicionarArquivo(zip, relatorioTXT);
					 relatorioTXT.delete();
					 zip.closeEntry();
	        	 }
				
//				//Finalizando arquivo
//				zip.flush();
//				zip.close();
//				
//				File arquivozip = new File("relatorio.zip");
//				httpServletResponse.setContentType("application/zip");
//				httpServletResponse.addHeader("Content-Disposition","attachment; filename=" + "relatorio.zip");
//				ServletOutputStream sos = httpServletResponse.getOutputStream();
//				sos.write(IoUtil.getBytesFromFile(arquivozip));  
//				sos.flush();
//				sos.close();
	        }
        	
        	
        	 //Finalizando arquivo
			 zip.flush();
			 zip.close();
			
			 //Preparando download
			 httpServletResponse.setContentType("application/zip");
			 httpServletResponse.addHeader("Content-Disposition",
					"attachment; filename=" + "relatorio.zip");
			 File arquivozip = new File("relatorio.zip");
			 ServletOutputStream sos = httpServletResponse.getOutputStream();
			 sos.write(IoUtil.getBytesFromFile(arquivozip));  
			 sos.flush();
			 sos.close();
			
        	if(gerouTxt){
        		
        	    EnvioEmail envioEmail = fachada.pesquisarEnvioEmail(
        	    	    EnvioEmail.GERAR_TXT_BOLETIM_MEDICAO_ACOMPANHAMENTO);
        	    
        	    String emailEmpresa = fachada.pesquisarEmailEmpresa(new Integer(idEmpresa));
    			String emailRemetente = envioEmail.getEmailRemetente();
    			String tituloMensagem = envioEmail.getTituloMensagem();
    			String corpoMensagem = envioEmail.getCorpoMensagem();
    			String emailReceptor =  envioEmail.getEmailReceptor();	
    			if(emailEmpresa != null){
    				emailReceptor = emailEmpresa + ", " + emailReceptor;
    			}
    			
    			try {
    			ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor,
    				emailRemetente, 
    			    tituloMensagem, 
    			    corpoMensagem, 
    			    arquivozip);
    			
    			} catch (Exception e) {
    			e.printStackTrace();
    			}
        	}
			 
		
				
        	
		} catch (ControladorException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        

		        
        
        
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
