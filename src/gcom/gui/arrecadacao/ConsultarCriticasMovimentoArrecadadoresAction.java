package gcom.gui.arrecadacao;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorMovimentoCriticas;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ConsultarCriticasMovimentoArrecadadoresAction extends GcomAction{
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("consultarCriticasMovimentoArrecadadoresAction");
		ConsultarCriticasMovimentoArrecadadoresActionForm form = (ConsultarCriticasMovimentoArrecadadoresActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		retorno = this.pesquisarCriticas(httpServletRequest, form, fachada, sessao, retorno);
		
		return retorno;
	}
	
	private ActionForward pesquisarCriticas(HttpServletRequest httpServletRequest,
			ConsultarCriticasMovimentoArrecadadoresActionForm form, Fachada fachada, HttpSession sessao,
			ActionForward retorno){
		String codArrecadador = form.getCodigoArrecadador();
		String periodoGeracaoInicial = form.getPeriodoGeracaoInicial();
		String periodoGeracaoFinal = form.getPeriodoGeracaoFinal();
		String periodoProcessamentoInicial = form.getPeriodoProcessamentoInicial();
		String periodoProcessamentoFinal = form.getPeriodoProcessamentoFinal();
		String nsa = form.getNumeroSequencialArquivo();
		String idServico = form.getIdServico();

		Date geracaoInicial = null;
		Date geracaoFinal = null;
		Date processamentoInicial = null;
		Date processamentoFinal = null;

		
		if (codArrecadador != null && !codArrecadador.trim().equals("")) {
			
			if(Util.isInteger(codArrecadador)){
				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
				filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, codArrecadador));
				filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
	
				Collection<Arrecadador> arrecadadores = this.getFachada().pesquisar(filtroArrecadador, Arrecadador.class.getName());

				if (arrecadadores == null || arrecadadores.isEmpty())
					throw new ActionServletException("atencao.msg_personalizada", "Arrecadador Inexistente");
			}
			else
				throw new ActionServletException("atencao.msg_personalizada","Arrecadador deve conter apenas números");
			
				
		}

		if((periodoGeracaoInicial == null || periodoGeracaoInicial.equals("")) &&(periodoGeracaoFinal !=null && !periodoGeracaoFinal.equals("")))
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Geração Inicial Válido");
			
		if((periodoGeracaoFinal == null || periodoGeracaoFinal.equals("")) && (periodoGeracaoInicial !=null && !periodoGeracaoInicial.equals("")))
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Geração Final Válido");
			
			
		if (periodoGeracaoFinal != null && !periodoGeracaoFinal.equals("") && periodoGeracaoInicial != null && !periodoGeracaoInicial.equals("")) {
				if(periodoGeracaoFinal.length() <10)
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Geração Final Válido");
				if(periodoGeracaoInicial.length() <10)
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Geração Inicial Válido");
				Boolean b1 = Util.verificaSeDataValida(periodoGeracaoInicial, "dd/MM/yyyy");
				
				if(b1){
					geracaoInicial = Util.converteStringParaDate(periodoGeracaoInicial);
					
					b1 = Util.verificaSeDataValida(periodoGeracaoFinal, "dd/MM/yyyy");
					if(b1)
						geracaoFinal = Util.converteStringParaDate(periodoGeracaoFinal);						
					else
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Geração Final Válido");						
				}
				else
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Geração Inicial Válido");
				

				if (geracaoInicial !=null && geracaoFinal!=null && geracaoInicial.compareTo(geracaoFinal) > 0) 
					throw new ActionServletException("atencao.msg_personalizada",
							"Data Geração Final do Período é anterior à Data Geração Inicial do Período");
			}
		
		if((periodoProcessamentoInicial == null || periodoProcessamentoInicial.equals("")) 
				&&(periodoProcessamentoFinal !=null && !periodoProcessamentoFinal.equals("")))
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Processamento Inicial");
		
		if((periodoProcessamentoFinal == null || periodoProcessamentoFinal.equals("")) 
				&& (periodoProcessamentoInicial !=null && !periodoProcessamentoInicial.equals("")))
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Processamento Final");
		
		
		if (periodoProcessamentoFinal != null && !periodoProcessamentoFinal.equals("") 
				&& periodoProcessamentoInicial != null && !periodoProcessamentoInicial.equals("")) {
					
			if(periodoProcessamentoFinal.length() <10)
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Processamento Final Válido");
			if(periodoProcessamentoInicial.length() <10)
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Processamento Inicial Válido");
			
			Boolean b1 = Util.verificaSeDataValida(periodoProcessamentoInicial, "dd/MM/yyyy");
			
			if(b1){
				processamentoInicial = Util.converteStringParaDate(periodoProcessamentoInicial);
				
				b1 = Util.verificaSeDataValida(periodoProcessamentoFinal, "dd/MM/yyyy");
				if(b1)
					processamentoFinal = Util.converteStringParaDate(periodoProcessamentoFinal);						
				else
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Processamento Final Válido");						
			}
			else
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Processamento Inicial Válido");
			

			if (processamentoInicial !=null && processamentoFinal!=null && processamentoInicial.compareTo(processamentoFinal) > 0) 
				throw new ActionServletException("atencao.msg_personalizada",
					"Data Processamento Final do Período é anterior à Data Processamento Inicial do Período");
			

		}
		if(nsa !=null && !nsa.trim().equals("") && !Util.isInteger(nsa))
			throw new ActionServletException("atencao.msg_personalizada","Número Sequencial do Arquivo (NSA) deve conter apenas números");

		Integer totalRegistros = fachada.pesquisarArrecadadorMovimentoCriticasCount(codArrecadador, idServico, nsa, 
			geracaoInicial, geracaoFinal, processamentoInicial, processamentoFinal);

		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		Collection<ArrecadadorMovimentoCriticas> criticas = fachada.pesquisarArrecadadorMovimentoCriticas(codArrecadador, idServico, nsa, 
			geracaoInicial, geracaoFinal, processamentoInicial, processamentoFinal, (Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"), true);
				
		
		if(criticas !=null && !criticas.isEmpty())
			sessao.setAttribute("criticas", criticas);		
		else
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");		
		
		return retorno;
	}
}
