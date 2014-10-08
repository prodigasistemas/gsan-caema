package gcom.gui.cobranca.cobrancaporresultado;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioConsultarOSAnalitico;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioConsultarOSSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Hugo Azevedo
 * @date 11/06/2012
 */
public class MovimentarOrdemServicoConsultarOSAction extends  ExibidorProcessamentoTarefaRelatorio {
   
    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a página de definir acesso do grupo 
        ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoAction");

        //Cria uma instância da sessão 
        HttpSession sessao = httpServletRequest.getSession(false);

        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Campos do Formumário
		String idComando = form.getIdComandoContaCobranca();
		String[] idsUnidadeNegocio = form.getIdsUnidadeNegocio();
		String[] idsGerenciaRegional = form.getIdsGerenciaRegional();
		String[] idsLocalidade = form.getIdsLocalidade();
		String situacaoOS = form.getSituacaoOS();
		String dataGeracaoInicialOS = form.getDataGeracaoInicialOS();
		String dataGeracaoFinalOS = form.getDataGeracaoFinalOS();
		String dataEncerramentoInicialOS = form.getDataEncerramentoInicialOS();
		String dataEncerramentoFinalOS = form.getDataEncerramentoFinalOS();
		String tipoRelatorio = form.getTipoRelatorio();
		
		//Validar Datas
		//=============================
		
		//Data de Geração
		if(dataGeracaoInicialOS != null && !dataGeracaoInicialOS.equals("") && !Util.verificaSeDataValida(dataGeracaoInicialOS, "dd/MM/yyyy")){
			throw new ActionServletException("atencao.erro_invalido",null,"Data de Geração Inicial");
		}
		
		
		if(dataGeracaoInicialOS != null && !dataGeracaoInicialOS.equals("") && 
		   (dataGeracaoFinalOS == null || dataGeracaoFinalOS.equals(""))){
			throw new ActionServletException("atencao.msg_personalizada","Informe a Data de Geração Final");	
		}
		
		if(dataGeracaoFinalOS != null && !dataGeracaoFinalOS.equals("") && !Util.verificaSeDataValida(dataGeracaoFinalOS, "dd/MM/yyyy")){
			throw new ActionServletException("atencao.erro_invalido",null,"Data de Geração Final");
		}
		
		if(dataGeracaoFinalOS != null && !dataGeracaoFinalOS.equals("") && 
		  (dataGeracaoInicialOS == null || dataGeracaoInicialOS.equals(""))){
			throw new ActionServletException("atencao.msg_personalizada","Informe a Data de Geração Inicial");
		}
		
		//Caso a data inicial seja maior que a data final
		if(dataGeracaoInicialOS != null && !dataGeracaoInicialOS.equals("") && 
		   dataGeracaoFinalOS != null && !dataGeracaoFinalOS.equals("") &&
		   Util.compararData(Util.converteStringParaDate(dataGeracaoInicialOS), Util.converteStringParaDate(dataGeracaoFinalOS)) > 0){
			
			throw new ActionServletException("atencao.gsan.data_final_menor_data_inicial",null,"Data de Geração");
		}
		
		//Data de Execução
		if(dataEncerramentoInicialOS != null && !dataEncerramentoInicialOS.equals("") &&  !Util.verificaSeDataValida(dataEncerramentoInicialOS, "dd/MM/yyyy")){
			throw new ActionServletException("atencao.erro_invalido",null,"Data de Encerramento Inicial");
		}
		
		if(dataEncerramentoInicialOS != null && !dataEncerramentoInicialOS.equals("") &&  
		   (dataEncerramentoFinalOS == null || dataEncerramentoFinalOS.equals(""))){
			throw new ActionServletException("atencao.msg_personalizada","Informe a Data de Encerramento Final");
		}
		
		if(dataEncerramentoFinalOS != null && !dataEncerramentoFinalOS.equals("") &&  !Util.verificaSeDataValida(dataEncerramentoFinalOS, "dd/MM/yyyy")){
			throw new ActionServletException("atencao.erro_invalido",null,"Data de Encerramento Final");
		}
		
		if(dataEncerramentoFinalOS != null && !dataEncerramentoFinalOS.equals("") && 
			(dataEncerramentoInicialOS == null || dataEncerramentoInicialOS.equals(""))){
			
			throw new ActionServletException("atencao.msg_personalizada","Informe a Data de Encerramento Inicial");
		}
		
		//Caso a data inicial seja maior que a data final
		if(dataEncerramentoInicialOS != null && !dataEncerramentoInicialOS.equals("") && 
				dataEncerramentoFinalOS != null && !dataEncerramentoFinalOS.equals("") &&
		   Util.compararData(Util.converteStringParaDate(dataEncerramentoInicialOS), Util.converteStringParaDate(dataEncerramentoFinalOS)) > 0){
			
			throw new ActionServletException("atencao.gsan.data_final_menor_data_inicial",null,"Data de Encerramento");
		}
		
		TarefaRelatorio relatorio = null;		
		
		//Tipo Relatório Sintético
		if(tipoRelatorio.equals("1")){
			relatorio = new RelatorioConsultarOSSintetico();
		}
		
		//Tipo Relatório Analítico
		else{
			relatorio = new RelatorioConsultarOSAnalitico();
		}
		
		relatorio.addParametro("idComando",idComando);
		relatorio.addParametro("idsUnidadeNegocio",idsUnidadeNegocio);
		relatorio.addParametro("idsGerenciaRegional",idsGerenciaRegional);
		relatorio.addParametro("idsLocalidade",idsLocalidade);
		relatorio.addParametro("situacaoOS",situacaoOS);
		relatorio.addParametro("dataGeracaoInicialOS",dataGeracaoInicialOS);
		relatorio.addParametro("dataGeracaoFinalOS",dataGeracaoFinalOS);
		relatorio.addParametro("dataEncerramentoInicialOS",dataEncerramentoInicialOS);
		relatorio.addParametro("dataEncerramentoFinalOS",dataEncerramentoFinalOS);
		relatorio.addParametro("tipoRelatorio",tipoRelatorio);
		
		retorno = processarExibicaoRelatorio(relatorio,
				TarefaRelatorio.TIPO_PDF, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
        
    }
    
}
