package gcom.gui.cobranca.cobrancaporresultado;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.batch.Processo;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1375] - Gerar Arquivo Texto Com Informação do Código de Barras
 * 
 * @author Davi Menezes
 * @date 28/08/2012
 *
 */
public class GerarArquivoTextoCodigoBarrasDocumentoCobrancaAction extends GcomAction {
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
		
		Fachada fachada = this.getFachada();
		
		Collection<?> colImoveis = (Collection<?>) sessao.getAttribute("colecaoImoveis");
		
		if(form.getIdEmpresa() == null || form.getIdEmpresa().equals("-1") || form.getIdEmpresa().equals("")){
			throw new ActionServletException("atencao.usuario.empresa.nula");
		}
		
		if(Util.isVazioOrNulo(colImoveis)){
			sessao.removeAttribute("gerarArquivoTexto");
			throw new ActionServletException("atencao.nao_existem_imoveis_selecionados");
		}
		
		if(colImoveis.size() > 300){
			sessao.removeAttribute("gerarArquivoTexto");
			throw new ActionServletException("atencao.quantidade_imoveis_excedido");
		}
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
		
		Collection<?> colEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		String emailEmpresa = "";
		if(!Util.isVazioOrNulo(colEmpresa)){
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colEmpresa);
			
			if(empresa.getEmail() == null || empresa.getEmail().equals("")){
				throw new ActionServletException("atencao.informe_email_empresa");
			}else{
				emailEmpresa = empresa.getEmail();
			}
		}
		
		//Cria o map que vai armazenar os dados para iniciar o processamento do batch
        Map<String, Object> dadosProcessamento = new HashMap();
        dadosProcessamento.put("colecaoImoveis", colImoveis);
        dadosProcessamento.put("emailEmpresa", emailEmpresa);
        
		//Indica que o processo vai ser o de Gerar Arquivo Texto Codigo Barras Documento Cobranca
		int idProcesso = Processo.GERAR_ARQ_TXT_COD_BARRAS_DOC_COBRANCA; 
		
		//Monta as informações para iniciar o processo
		Date dataHoraAgendamento = new Date();
		ProcessoIniciado processoIniciado = new ProcessoIniciado();
		Processo processo = new Processo();
		processo.setId(idProcesso);
		processoIniciado.setDataHoraAgendamento(dataHoraAgendamento);
		
		//Adiciona a situação e o usuário ao objeto.
		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		processoIniciado.setProcesso(processo);
		processoIniciado.setProcessoSituacao(processoSituacao);
		processoIniciado.setUsuario(this.getUsuarioLogado(httpServletRequest));
		
		//Inseri o processo retornando qual o id gerado para o processo 
		//que foi iniciado no banco de dados.
		Integer codigoProcessoIniciado = fachada.gerarArqTxtCodigoBarrasDocumentoCobranca(
				processoIniciado, dadosProcessamento);
		
		//Monta a página de sucesso.
		montarPaginaSucesso(httpServletRequest, "Geração do Arquivo Texto encaminhado para batch." 
				+ "Codigo do Processo: " + codigoProcessoIniciado,
				"Consultar Comandos de Contas em Cobrança por Empresa", 
				"exibirConsultarComandosContasCobrancaEmpresaAction.do?menu=sim");
		
		return retorno;
	}
}
