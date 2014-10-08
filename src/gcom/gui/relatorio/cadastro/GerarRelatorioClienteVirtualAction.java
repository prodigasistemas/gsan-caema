package gcom.gui.relatorio.cadastro;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.cliente.FiltrarClienteVirtualActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.RelatorioClienteVirtual;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1304] - Gerar Relatório Clientes Cadastrados no Ambiente Virtual
 * 
 * @author Davi Menezes
 * @date 04/04/2012
 *
 */
public class GerarRelatorioClienteVirtualAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// cria a variável de retorno
		ActionForward retorno = null;
		
		FiltrarClienteVirtualActionForm form = (FiltrarClienteVirtualActionForm) actionForm;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		Fachada fachada = Fachada.getInstancia();
		
		if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(form.getMatriculaImovel()));
			if(imovel == null){
				throw new ActionServletException("atencao.imovel_inexistente");
			}
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

    	Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
    	
    	TarefaRelatorio relatorio = new RelatorioClienteVirtual(usuarioLogado);
    	
    	if(tipoRelatorio == null){
    		tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
    	}
    	
    	String periodoInicial = null;
    	if(form.getPeriodoAtendimentoInicial() != null && !form.getPeriodoAtendimentoInicial().equals("")){
    		periodoInicial = form.getPeriodoAtendimentoInicial();
    	}
    	
    	String periodoFinal = null;
    	if(form.getPeriodoAtendimentoFinal() != null && !form.getPeriodoAtendimentoFinal().equals("")){
    		periodoFinal = form.getPeriodoAtendimentoFinal();
    	}
    	
    	String idImovel = null;
    	if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
    		idImovel = form.getMatriculaImovel();
    	}
    	
    	String codigoSituacao = null;
    	if(form.getSituacaoCliente() != null && !form.getSituacaoCliente().equals("")){
    		codigoSituacao = form.getSituacaoCliente();
    	}
    	
    	String tempoCadastro = null;
    	if(form.getTempoCadastro() != null && !form.getTempoCadastro().equals("")){
    		tempoCadastro = form.getTempoCadastro();
    	}
    	
    	relatorio.addParametro("usuarioLogado", usuarioLogado);
    	relatorio.addParametro("periodoInicial", periodoInicial);
    	relatorio.addParametro("periodoFinal", periodoFinal);
    	relatorio.addParametro("matriculaImovel", idImovel);
    	relatorio.addParametro("codigoSituacao", codigoSituacao);
    	relatorio.addParametro("tempoCadastro", tempoCadastro);
    	relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
    	
    	retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
    			httpServletResponse, actionMapping);
    	
    	return retorno;
		
	}
}
