package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.FiltroImovelInscricaoResetorizada;
import gcom.cadastro.FiltroOcorrenciaResetorizacao;
import gcom.cadastro.ImovelInscricaoResetorizada;
import gcom.cadastro.OcorrenciaResetorizacao;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1389] - Limpar Mensagens de Crítica Para Atualização das Inscrições - GSAN
 * 
 * @author Davi Menezes
 * @date 09/11/2012
 *
 */
public class ExibirLimparMensagemCriticaAtualizacaoInscricoesAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm formulario, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward retorno = 
			mapping.findForward("limparMensagemCriticaAtualizacaoInscricoes");
	
		LimparMensagemCriticaAtualizacaoInscricoesActionForm form = 
				(LimparMensagemCriticaAtualizacaoInscricoesActionForm) formulario;
		
		HttpSession sessao = request.getSession(false);
		
		Fachada fachada = this.getFachada();
		
		//Limpar
		String menu = request.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.setIdLocalidade("");
			form.setCodigoSetorComercial("");
			form.setMensagemCritica("-1");
			form.setIdsRegistro(null);
		}
		
		//Pesquisar Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			this.pesquisarLocalidade(request, form);
		}
		
		//Pesquisar Setor Comercial
		if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
			this.pesquisarSetorComercial(request, form);
		}
		
		String filtrar = request.getParameter("filtrar");
		if(filtrar != null && filtrar.equalsIgnoreCase("sim")){
			if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			
				FiltroImovelInscricaoResetorizada filtroImovel = new FiltroImovelInscricaoResetorizada();
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.ID_LOCALIDADE, form.getIdLocalidade()));
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.INDICADOR_ATUALIZACAO, ConstantesSistema.NAO));
				
				if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
				}
				
				if(form.getMensagemCritica() != null && !form.getMensagemCritica().equals("") && !form.getMensagemCritica().equals("-1")){
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelInscricaoResetorizada.ID_OCORRENCIA_RESETORIZACAO, form.getMensagemCritica()));
				}else{
					filtroImovel.adicionarParametro(new ParametroNaoNulo(FiltroImovelInscricaoResetorizada.OCORRENCIA_RESETORIZACAO));
				}
				
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelInscricaoResetorizada.OCORRENCIA_RESETORIZACAO);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelInscricaoResetorizada.IMOVEL);
				
				Collection<?> colImovelInscricaoResetorizada = fachada.pesquisar(filtroImovel, ImovelInscricaoResetorizada.class.getName());
				if(!Util.isVazioOrNulo(colImovelInscricaoResetorizada)){
					request.setAttribute("colecaoImoveisInscricaoResetorizada", colImovelInscricaoResetorizada);
				}else{
					throw new ActionServletException("atencao.nao_existem_imoveis_inscricao_resetorizada");
				}
			}
		
		}else{
			request.removeAttribute("colecaoImoveisInscricaoResetorizada");
		}
		
		//Pesquisar Ocorrência de Resetorização
		if((Collection<?>) sessao.getAttribute("collectionMensagemCritica") == null){
			FiltroOcorrenciaResetorizacao filtroOcorrenciaResetorizacao = new FiltroOcorrenciaResetorizacao();
			
			Collection<?> colecaoMensagemCritica = fachada.pesquisar(filtroOcorrenciaResetorizacao, OcorrenciaResetorizacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoMensagemCritica)){
				sessao.setAttribute("collectionMensagemCritica", colecaoMensagemCritica);
			}
		}
		
		return retorno;
	}
	
	/*
	 * Pesquisar Localidade
	 */
	private void pesquisarLocalidade(HttpServletRequest request, LimparMensagemCriticaAtualizacaoInscricoesActionForm form){
		
		Localidade localidade = null;
		String idLocalidade = form.getIdLocalidade();
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.limparListaParametros();

		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, 
				new Integer(idLocalidade)));
		
		Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoLocalidade)){
			localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			String msg = "Localidade:" + localidade.getDescricao();
			request.setAttribute("mensagemLocalidade", msg);
			request.setAttribute("nomeCampo", "codigoSetorComercial");
			
			form.setIdLocalidade(Util.adicionarZerosEsquedaNumero(3,localidade.getId().toString()));
		}else{
			form.setIdLocalidade(null);
			form.setCodigoSetorComercial(null);
			
			request.setAttribute("localidadeNaoEncontrada", "true");
			request.setAttribute("mensagemLocalidade","LOCALIDADE INEXISTENTE");
			request.setAttribute("nomeCampo", "idLocalidade");
		}
		
	}
	
	/*
	 * Pesquisar Setor Comercial
	 */
	private void pesquisarSetorComercial(HttpServletRequest request, LimparMensagemCriticaAtualizacaoInscricoesActionForm form){
		String idLocalidade = form.getIdLocalidade();
		String codigoSetor = form.getCodigoSetorComercial();
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		filtroSetorComercial.limparListaParametros();
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, 
				new Integer(idLocalidade)));
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
				new Integer(codigoSetor)));
		
		Collection<SetorComercial> colecaoSetor = 
			this.getFachada().pesquisar(
				filtroSetorComercial,
				SetorComercial.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSetor)){
			SetorComercial setor = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
			
			String msg = "Setor:" + setor.getDescricao();
			request.setAttribute("mensagemSetorComercial", msg);
			request.setAttribute("nomeCampo", "mensagemCritica");
			
			form.setCodigoSetorComercial(Util.adicionarZerosEsquedaNumero(3,""+setor.getCodigo()));
		}else{
			form.setCodigoSetorComercial(null);
			
			request.setAttribute("setorComercialNaoEncontrado", "true");
			request.setAttribute("mensagemSetorComercial","SETOR COMERCIAL INEXISTENTE");
			request.setAttribute("nomeCampo", "codigoSetorComercial");
		}
		
	}
	
}
