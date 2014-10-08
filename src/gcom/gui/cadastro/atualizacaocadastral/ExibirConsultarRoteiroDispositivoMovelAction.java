package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo M�vel Atualiza��o Cadastral
 * 
 * @author Davi Menezes
 * @date 26/11/2012
 *
 */
public class ExibirConsultarRoteiroDispositivoMovelAction extends GcomAction {
	
	/**
	 * 
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
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRoteiroDispositivoMovel");
		
		//Form
		ConsultarRoteiroDispositivoMovelActionForm form = (ConsultarRoteiroDispositivoMovelActionForm) actionForm;
		
		//Sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String menu = httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.reset();
		}
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisar Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			this.pesquisarLocalidade(form, fachada, httpServletRequest);
			
			//Pesquisar Setor Comercial
			if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
				this.pesquisarSetorComercial(form, fachada, httpServletRequest);
				
				//Pesquisar Quadra
				if(form.getNumeroQuadra() != null && !form.getNumeroQuadra().equals("")){
					this.pesquisarQuadra(form, fachada, httpServletRequest);
				}
			}
		}
		
		//Pesquisar Cadastrador
		this.pesquisarCadastrador(fachada, httpServletRequest);
		
		//Pesquisar Situa��o Transmiss�o Leitura
		this.pesquisarSituacaoTransmissaoLeitura(fachada, httpServletRequest);
		
		sessao.setAttribute("achou", "2");
		sessao.removeAttribute("colecaoArquivoTextoRoteiroDispositivoMovel");
		
		return retorno;
	}
	
	/**
	 * Pesquisar Localidade
	 * 
	 * @author Davi Menezes
	 * @date 26/11/2012
	 */
	private void pesquisarLocalidade(ConsultarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		if(!Util.isVazioOrNulo(colLocalidade)){
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
			
			form.setDescricaoLocalidade(localidade.getDescricao());
			
			request.removeAttribute("localidadeInexistente");
		}else{
			form.setIdLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
			
			request.setAttribute("localidadeInexistente", true);
		}
	}
	
	/**
	 * Pesquisar Setor Comercial
	 * 
	 * @author Davi Menezes
	 * @date 26/11/2012
	 */
	private void pesquisarSetorComercial(ConsultarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if(!Util.isVazioOrNulo(colSetorComercial)){
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
			
			form.setDescricaoSetorComercial(setorComercial.getDescricao());
			
			request.removeAttribute("setorComercialInexistente");
		}else{
			form.setCodigoSetorComercial("");
			form.setDescricaoSetorComercial("Setor Comercial Inexistente");
			
			request.setAttribute("setorComercialInexistente", true);
		}
	}
	
	/**
	 * Pesquisar Quadra
	 * 
	 * @author Davi Menezes
	 * @date 26/11/2012
	 */
	private void pesquisarQuadra(ConsultarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getNumeroQuadra()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercial()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
		if(!Util.isVazioOrNulo(colQuadra)){
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colQuadra);
			
			form.setDescricaoQuadra(quadra.getDescricao());
			
			request.removeAttribute("quadraInexistente");
		}else{
			form.setNumeroQuadra("");
			form.setDescricaoQuadra("Quadra Inexistente");
			
			request.setAttribute("quadraInexistente", true);
		}
	}
	
	/**
	 * Pesquisar Cadastrador
	 * 
	 * @author Davi Menezes
	 * @date 26/11/2012
	 * 
	 */
	private void pesquisarCadastrador(Fachada fachada, HttpServletRequest request){
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.USUARIO_NOME);
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ATUALIZACAO_CADASTRAL, ConstantesSistema.SIM));
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
		
		Collection<?> colLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
//		if(!Util.isVazioOrNulo(colLeiturista)){
			request.setAttribute("colecaoLeiturista", colLeiturista);
//		}else{
//			request.removeAttribute("colecaoLeiturista");
//		}
	}
	
	/**
	 * Pesquisar Situacao Transmissao Leitura
	 * 
	 * @author Davi Menezes
	 * @date 26/11/2012
	 * 
	 */
	private void pesquisarSituacaoTransmissaoLeitura(Fachada fachada, HttpServletRequest request){
		FiltroSituacaoTransmissaoLeitura filtroSituacao = new FiltroSituacaoTransmissaoLeitura(FiltroSituacaoTransmissaoLeitura.ID);
		filtroSituacao.adicionarParametro(new ParametroSimples(FiltroSituacaoTransmissaoLeitura.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSituacaoTransmissaoLeitura = fachada.pesquisar(filtroSituacao, SituacaoTransmissaoLeitura.class.getName());
		if(!Util.isVazioOrNulo(colSituacaoTransmissaoLeitura)){
			request.setAttribute("colecaoSituacaoTransmissaoLeitura", colSituacaoTransmissaoLeitura);
		}else{
			request.removeAttribute("colecaoSituacaoTransmissaoLeitura");
		}
	}

}
