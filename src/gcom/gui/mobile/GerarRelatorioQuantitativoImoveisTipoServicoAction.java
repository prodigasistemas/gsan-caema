package gcom.gui.mobile;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.ordemservico.GerarArquivoTextoOrdensServicoSmartphoneActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.mobile.RelatorioQuantitativoImoveisTipoServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioQuantitativoImoveisTipoServicoAction extends
			ExibidorProcessamentoTarefaRelatorio{
	
	/**
	 * Action que gera o relatório com as quantidades de OS que serão geradas no arquivo
	 * 
	 * [UC1497] Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * @author Rafael Corrêa
	 * @date 01/07/2013
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;		
		GerarArquivoTextoOrdensServicoSmartphoneActionForm form = (GerarArquivoTextoOrdensServicoSmartphoneActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		TarefaRelatorio relatorio = new RelatorioQuantitativoImoveisTipoServico((Usuario) sessao.getAttribute("usuarioLogado"));
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		//Identificador do tipo de OS
		String identificadorOS = httpServletRequest.getParameter("identificadorOS");
				
		//Comando Selecionado
		String comando = httpServletRequest.getParameter("comando");
		
		String idEmpresaInformada = form.getEmpresa();
		Integer idEmpresa = null;		
		
		String idGerenciaRegionalInformada = form.getGerenciaRegional();
		Integer idGerenciaRegional = null;
		
		String idUnidadeNegocioInformada = form.getUnidadeNegocio();
		Integer idUnidadeNegocio = null;
		
		String idLocalidadeInformada = form.getLocalidade();
		Integer idLocalidade = null;
		
		String codigoSetorInicialInformado = form.getCodigoSetorComercialOrigem();
		Integer codigoSetorInicial = null;
		
		String quadraInicialInformada = form.getCodigoQuadraInicial();
		Integer quadraInicial = null;
		
		String codigoSetorFinalInformado = form.getCodigoSetorComercialDestino();
		Integer codigoSetorFinal = null;
		
		String quadraFinalInformada = form.getCodigoQuadraFinal();
		Integer quadraFinal = null;
		
		if (idEmpresaInformada != null && !idEmpresaInformada.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			idEmpresa = new Integer(idEmpresaInformada);
		}
		
		if (idGerenciaRegionalInformada != null && !idGerenciaRegionalInformada.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			idGerenciaRegional = new Integer(idGerenciaRegionalInformada);
		}
		
		if (idUnidadeNegocioInformada != null && !idUnidadeNegocioInformada.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			idUnidadeNegocio = new Integer(idUnidadeNegocioInformada);
		}
		
		if(idLocalidadeInformada != null && !idLocalidadeInformada.trim().equals("")){
			existeLocalidade(idLocalidadeInformada);
			idLocalidade = new Integer(idLocalidadeInformada);
			
			if(codigoSetorInicialInformado != null && !codigoSetorInicialInformado.trim().equals("")){
				existeSetorComercial(idLocalidadeInformada, codigoSetorInicialInformado, "Setor Comercial Inicial");
				codigoSetorInicial = new Integer(codigoSetorInicialInformado);
				
				if (quadraInicialInformada != null && !quadraInicialInformada.trim().equals("")) {
					existeQuadra(idLocalidadeInformada, codigoSetorInicialInformado, quadraInicialInformada, "Quadra Inicial");
					quadraInicial = new Integer(quadraInicialInformada);
				}
			}
			
			if(codigoSetorFinalInformado != null && !codigoSetorFinalInformado.trim().equals("")){
				existeSetorComercial(idLocalidadeInformada, codigoSetorFinalInformado, "Setor Comercial Final");
				codigoSetorFinal = new Integer(codigoSetorFinalInformado);
				
				if (quadraFinalInformada != null && !quadraFinalInformada.trim().equals("")) {
					existeQuadra(idLocalidadeInformada, codigoSetorFinalInformado, quadraFinalInformada, "Quadra Final");
					quadraFinal = new Integer(quadraFinalInformada);
				}
			}
		}
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("idEmpresa", idEmpresa);		
		relatorio.addParametro("idGerenciaRegional", idGerenciaRegional);
		relatorio.addParametro("idUnidadeNegocio", idUnidadeNegocio);
		relatorio.addParametro("idLocalidade", idLocalidade);
		relatorio.addParametro("codigoSetorInicial", codigoSetorInicial);
		relatorio.addParametro("quadraInicial", quadraInicial);
		relatorio.addParametro("codigoSetorFinal", codigoSetorFinal);
		relatorio.addParametro("quadraFinal", quadraFinal);
		relatorio.addParametro("idsTipoServico", form.getIdsServicoTipo());
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("identificacaoOS",identificadorOS);
		relatorio.addParametro("comando",comando);
		
		retorno = processarExibicaoRelatorio(relatorio, Integer.parseInt(tipoRelatorio), httpServletRequest, httpServletResponse, actionMapping);
		
		return retorno;
	}
	
	private void existeLocalidade(String idLocalidade){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
	
		Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", "Localidade");
		}
	}
	
	private void existeSetorComercial(String idLocalidade, String codigoSetorComercial, String mensagem){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		
		Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {				
			throw new ActionServletException("atencao.pesquisa_inexistente", mensagem);
		}
	}
	
	private void existeQuadra(String idLocalidade, String codigoSetorComercial, String numeroQuadra, String mensagem){
		FiltroQuadra filtroQuadraInicial = new FiltroQuadra();
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidade));
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercial));
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, numeroQuadra));
		Collection<Quadra> colecaoQuadra = Fachada.getInstancia().pesquisar(filtroQuadraInicial, Quadra.class.getName());
		
		if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", mensagem);
		}
	}

}
