package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.CapaLoteAtualizacaoCadastral;
import gcom.cadastro.CapaLoteLogradouroAtualizacaoCadastral;
import gcom.cadastro.FiltroCapaLoteAtualizacaoCadastral;
import gcom.cadastro.FiltroCapaLoteLogradouroAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC9999] Efetuar Digitacao de Dados para Atualização Cadastral
 * 
 * @since 13/07/2012
 * @author Rafael Pinto
 *
 */
public class ExibirEfetuarDigitacaoDadosAtualizacaoCadastralAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = 
			mapping.findForward("exibirEfetuarDigitacaoDadosAtualizacaoCadastral");

		EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form = 
				(EfetuarDigitacaoDadosAtualizacaoCadastralActionForm) formulario;
		
		HttpSession sessao = request.getSession(false);
		
		String finalizar = (String) request.getParameter("finalizar");
		
		if(finalizar != null && finalizar.equals("sim")){
			Integer idCapaLoteAtualizacaoCadastral = (Integer) sessao.getAttribute("idCapaLoteAtualizacaoCadastral");
			
			this.atualizarCapaLoteAtualizacaoCadastral(idCapaLoteAtualizacaoCadastral);
			
			sessao.removeAttribute("colecaoLogradourosSelecionados");
			
			form.reset();
		}
		
		String objetoConsulta = (String) request.getParameter("objetoConsulta");
		
		if(objetoConsulta != null && (objetoConsulta.equals("1") 
				|| objetoConsulta.equals("2") ) ){
			
			Collection<Logradouro> colecaoLogradourosSelecionados = (Collection<Logradouro>)
					sessao.getAttribute("colecaoLogradourosSelecionados");
			
			Collection<Logradouro> colecaoLogradourosRemovidos = (Collection<Logradouro>)
					sessao.getAttribute("colecaoLogradourosRemovidos");
			
//			String[] idsLogradouroRemover = form.getLogradouroSelecionado();
//			this.verificarSeEhPossivelExcluir(form, idsLogradouroRemover, request);
			
			if(colecaoLogradourosSelecionados != null){
				if(Util.isVazioOrNulo(colecaoLogradourosRemovidos)){
					colecaoLogradourosRemovidos = new ArrayList<Logradouro>();
				}
				colecaoLogradourosRemovidos.addAll(colecaoLogradourosSelecionados);
			}
			sessao.setAttribute("colecaoLogradourosRemovidos", colecaoLogradourosRemovidos);
			sessao.removeAttribute("colecaoLogradourosSelecionados");
			
			form.setLogradouroSelecao(null);
			form.setNomeLogradouroSelecao(null);
			
		}
		
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			Localidade localidade = pesquisarLocalidade(request,form);
			
			if(localidade != null){
				this.pesquisarBairro(request,localidade);
			}
		}else{
			form.setBairro(null);
			request.removeAttribute("colecaoBairro");
		}
		
		if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
			pesquisarSetorComercial(request,form);
			
			if(form.getNumeroQuadra() != null && !form.getNumeroQuadra().equals("")){
				pesquisarQuadra(request,form);	
			}
		}else{
			form.setNumeroQuadra(null);
		}
				
		if(form.getBairro() != null && 
			!form.getBairro().equals("") && 
			!form.getBairro().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			pesquisarLogradouro(request,form);	
		}
		
		String adicionar = (String) request.getParameter("adicionar");
		
		if(adicionar != null && !adicionar.equals("")){
			this.adicionarLogradouro(request,form);
		}
		
		String remover = (String) request.getParameter("remover");
		if(remover != null && !remover.equals("")){
			
			Collection<Logradouro> colecaoLogradourosSelecionados = (Collection<Logradouro>)
					sessao.getAttribute("colecaoLogradourosSelecionados");
			
			Collection<Logradouro> colecaoLogradourosRemovidos = (Collection<Logradouro>)
					sessao.getAttribute("colecaoLogradourosRemovidos");
			
			String[] idsLogradouroRemover = form.getLogradouroSelecionado();
			
			this.verificarSeEhPossivelExcluir(form, idsLogradouroRemover, request);
			
			if(colecaoLogradourosSelecionados != null){
				
				if(Util.isVazioOrNulo(colecaoLogradourosRemovidos)){
					colecaoLogradourosRemovidos = new ArrayList<Logradouro>();
				}
				
				Iterator iteraColecaoLogradourosSelecionados = colecaoLogradourosSelecionados.iterator();
				
				while (iteraColecaoLogradourosSelecionados.hasNext()) {
					Logradouro logradouroColecao = (Logradouro) iteraColecaoLogradourosSelecionados.next();
					
					for (int i = 0; i < idsLogradouroRemover.length; i++) {
						String idLogradouroRemover = idsLogradouroRemover[i];
						
						if(idLogradouroRemover.equals(""+logradouroColecao.getId())){
							colecaoLogradourosRemovidos.add(logradouroColecao);
							iteraColecaoLogradourosSelecionados.remove();
						}
						
					}
				}
				sessao.setAttribute("colecaoLogradourosRemovidos", colecaoLogradourosRemovidos);
			}
		}
		
		
		return retorno;
	}
	
	private void pesquisarCapaLote(EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form,
			HttpServletRequest request) {
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") && 
				form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("") && 
				form.getNumeroQuadra() != null && !form.getNumeroQuadra().equals("") ){
//			&&	form.getBairro() != null && !form.getBairro().equals("") && !form.getBairro().equals("-1")
			HttpSession sessao = request.getSession();
			
			this.validarLocalidade(form, request);
			this.validarSetorComercial(form, request);
			this.validarQuadra(form, request);
			
			FiltroCapaLoteAtualizacaoCadastral filtroCapaLote = new FiltroCapaLoteAtualizacaoCadastral();
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.NUMERO_QUADRA, form.getNumeroQuadra()));
//			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID_BAIRRO, form.getBairro()));
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.INDICADOR_FINALIZADO, ConstantesSistema.NAO));
			
			Collection<?> colCapaLote = this.getFachada().pesquisar(filtroCapaLote, CapaLoteAtualizacaoCadastral.class.getName());
			if(!Util.isVazioOrNulo(colCapaLote)){
				CapaLoteAtualizacaoCadastral capaLote = (CapaLoteAtualizacaoCadastral) Util.retonarObjetoDeColecao(colCapaLote);
				
				form.setBairro(capaLote.getBairro().getId().toString());
				form.setQuantidadeDocumentos(capaLote.getQuantidadeLimiteDocumentos().toString());
				
				FiltroCapaLoteLogradouroAtualizacaoCadastral filtroCapaLoteLogradouro = new FiltroCapaLoteLogradouroAtualizacaoCadastral();
				filtroCapaLoteLogradouro.adicionarParametro(new ParametroSimples(
						FiltroCapaLoteLogradouroAtualizacaoCadastral.ID_CAPA_LOTE_ATUALIZACAO_CADASTRAL, capaLote.getId()));
				filtroCapaLoteLogradouro.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCapaLoteLogradouroAtualizacaoCadastral.LOGRADOURO);
				filtroCapaLoteLogradouro.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCapaLoteLogradouroAtualizacaoCadastral.LOGRADOURO_TIPO);
				filtroCapaLoteLogradouro.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCapaLoteLogradouroAtualizacaoCadastral.LOGRADOURO_TITULO);
				
				Collection<?> colCapaLoteLogradouro = this.getFachada().pesquisar(filtroCapaLoteLogradouro, CapaLoteLogradouroAtualizacaoCadastral.class.getName());
				if(!Util.isVazioOrNulo(colCapaLoteLogradouro)){
					Collection<Logradouro> colecaoLogradouro = new ArrayList<Logradouro>();
					
					Iterator<?> iterator = colCapaLoteLogradouro.iterator();
					
					while(iterator.hasNext()){
						CapaLoteLogradouroAtualizacaoCadastral capaLoteLogradouro = (CapaLoteLogradouroAtualizacaoCadastral)
								iterator.next();
						colecaoLogradouro.add(capaLoteLogradouro.getLogradouro());
					}
					
					sessao.setAttribute("colecaoLogradourosSelecionados", colecaoLogradouro);
				}
			}
			
		}
	}
	
	private void atualizarCapaLoteAtualizacaoCadastral(Integer idCapaLoteAtualizacaoCadastral){
		FiltroCapaLoteAtualizacaoCadastral filtroCapaLote = new FiltroCapaLoteAtualizacaoCadastral();
		filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID, idCapaLoteAtualizacaoCadastral));
		
		Collection<?> colCapaLote = this.getFachada().pesquisar(filtroCapaLote, CapaLoteAtualizacaoCadastral.class.getName());
		if(!Util.isVazioOrNulo(colCapaLote)){
			CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral = (CapaLoteAtualizacaoCadastral)
					Util.retonarObjetoDeColecao(colCapaLote);
			
			capaLoteAtualizacaoCadastral.setIndicadorFinalizado(ConstantesSistema.SIM);
			
			this.getFachada().atualizar(capaLoteAtualizacaoCadastral);
		}
	}
	
	private void verificarSeEhPossivelExcluir(EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form, 
			String[] idsLogradouroRemover, HttpServletRequest request) {
		
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")
				&& form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("") 
				&& form.getNumeroQuadra() != null && !form.getNumeroQuadra().equals("") 
				&& form.getBairro() != null && !form.getBairro().equals("") && !form.getBairro().equals("-1")){
		
			this.validarLocalidade(form, request);
			this.validarSetorComercial(form, request);
			this.validarQuadra(form, request);
			
			FiltroCapaLoteAtualizacaoCadastral filtroCapaLote = new FiltroCapaLoteAtualizacaoCadastral();
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.NUMERO_QUADRA, form.getNumeroQuadra()));
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID_BAIRRO, form.getBairro()));
			filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.INDICADOR_FINALIZADO, ConstantesSistema.NAO));
			
			Collection<?> colCapaLote = this.getFachada().pesquisar(filtroCapaLote, CapaLoteAtualizacaoCadastral.class.getName());
			if(!Util.isVazioOrNulo(colCapaLote)){
				CapaLoteAtualizacaoCadastral capaLote = (CapaLoteAtualizacaoCadastral) Util.retonarObjetoDeColecao(colCapaLote);
				
				Collection<Integer> colecaoIdsLogradouro = new ArrayList<Integer>();
				if(idsLogradouroRemover != null && idsLogradouroRemover.length > 0){
					for(int i = 0 ; i < idsLogradouroRemover.length; i++){
						colecaoIdsLogradouro.add(Integer.parseInt(idsLogradouroRemover[i]));
					}
				}
				
				FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
						FiltroImovelAtualizacaoCadastral.ID_CAPA_LOTE_ATUALIZACAO_CADASTRAL, capaLote.getId()));
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimplesIn(
						FiltroImovelAtualizacaoCadastral.ID_LOGRADOURO, colecaoIdsLogradouro));
//				filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
//						FiltroImovelAtualizacaoCadastral.IMOVEL);
				
				Collection<?> colImovelAtualizacaoCadastral = this.getFachada().pesquisar(
						filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
				
				if(!Util.isVazioOrNulo(colImovelAtualizacaoCadastral)){
					Iterator<?> iterator = colImovelAtualizacaoCadastral.iterator();
					
					while(iterator.hasNext()){
						ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral)
								iterator.next();
						
						if(imovelAtualizacaoCadastral.getImovel() != null){
							throw new ActionServletException("atencao.logradouro_nao_pode_ser_removido");
						}
					}
				}
			}
		}
	}

	private void adicionarLogradouro(HttpServletRequest request,
		EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form){
		
		HttpSession sessao = request.getSession(false);
		
		String logradouroSelecionado = form.getLogradouroSelecao();
		
		if(logradouroSelecionado != null && !logradouroSelecionado.equals("")){
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			
			filtroLogradouro.limparListaParametros();
			
			filtroLogradouro.adicionarParametro(
				new ParametroSimples(FiltroLogradouro.INDICADORUSO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLogradouro.adicionarParametro(
				new ParametroSimples(FiltroLogradouro.ID,
					logradouroSelecionado));
			
			filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(
					FiltroLogradouro.LOGRADOUROTIPO);
			
			filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(
					FiltroLogradouro.LOGRADOUROTITULO);
			
			Collection<Logradouro> coleacaoLogradouro = 
				this.getFachada().pesquisar(filtroLogradouro, Logradouro.class.getName());
			
			if (coleacaoLogradouro != null && !coleacaoLogradouro.isEmpty()) {
				
				Logradouro logradouro = (Logradouro)Util.retonarObjetoDeColecao(coleacaoLogradouro);
				
				Collection<Logradouro> colecaoLogradourosSelecionados = (Collection<Logradouro>)
						sessao.getAttribute("colecaoLogradourosSelecionados");
				
				boolean jaExisteLogradouro = false;
				if(colecaoLogradourosSelecionados != null){
					
					Iterator iteraColecaoLogradourosSelecionados = colecaoLogradourosSelecionados.iterator();
					
					while (iteraColecaoLogradourosSelecionados.hasNext()) {
						Logradouro logradouroColecao = (Logradouro) iteraColecaoLogradourosSelecionados.next();
						
						if(logradouroColecao.getId().intValue() == logradouro.getId().intValue()){
							jaExisteLogradouro = true;
						}
						
					}
					
				}else{
					colecaoLogradourosSelecionados = new ArrayList<Logradouro>();
					sessao.setAttribute("colecaoLogradourosSelecionados", colecaoLogradourosSelecionados);
				}
				if(!jaExisteLogradouro){
					colecaoLogradourosSelecionados.add(logradouro);	
				}
				
				form.setLogradouroSelecao("");
				form.setNomeLogradouroSelecao("");
				
			}else {
				throw new ActionServletException("atencao.naocadastrado", null,"Logradouro");
			}			
		}else{
			throw new ActionServletException("atencao.naocadastrado", null,"Logradouro");
		}
		

		
	}
	
	private void pesquisarBairro(HttpServletRequest request,Localidade localidade){
			
		FiltroBairro filtroBairro = new FiltroBairro(FiltroBairro.NOME);
		
		filtroBairro.limparListaParametros();
		
		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.MUNICIPIO_ID,
				localidade.getMunicipio().getId()));
		
		Collection<Bairro> coleacaoBairro = 
				this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());
		
		if (coleacaoBairro != null && !coleacaoBairro.isEmpty()) {
			request.setAttribute("colecaoBairro", coleacaoBairro);
		}else {
			throw new ActionServletException("atencao.naocadastrado", null,"Bairro");
		}
	}
	
	private void pesquisarLogradouro(HttpServletRequest request,
		EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form){
		
		String idLogradouro = form.getLogradouroSelecao();
		String bairro = form.getBairro();
		String idMunicipio = form.getIdMunicipio();
		
		if(idLogradouro != null && !idLogradouro.equals("")){
			
			FiltroLogradouroBairro filtroLogradouroBairro = 
					new FiltroLogradouroBairro(FiltroLogradouroBairro.NOME_LOGRADOURO);
				
			filtroLogradouroBairro.limparListaParametros();
			
			filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO);
			filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_TIPO);
			filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_TITULO);
			
			filtroLogradouroBairro.adicionarParametro(
				new ParametroSimples(FiltroLogradouroBairro.ID_MUNICIPIO,
					idMunicipio));

			filtroLogradouroBairro.adicionarParametro(
				new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO,
					bairro));

			filtroLogradouroBairro.adicionarParametro(
				new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO,
					idLogradouro));
			
			Collection<LogradouroBairro> colecaoLogradouroBairro = 
					this.getFachada().pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
			
			if (colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty()) {
				
				LogradouroBairro logradouroBairro = (LogradouroBairro) Util.retonarObjetoDeColecao(colecaoLogradouroBairro);
				
				form.setLogradouroSelecao(""+logradouroBairro.getLogradouro().getId());
				
				if(logradouroBairro.getLogradouro().getLogradouroTitulo() != null){
					form.setNomeLogradouroSelecao(logradouroBairro.getLogradouro().getLogradouroTipo().getDescricaoAbreviada() + " " +
							logradouroBairro.getLogradouro().getLogradouroTitulo().getDescricaoAbreviada() + " " + 
							logradouroBairro.getLogradouro().getNome());
				}else{
					form.setNomeLogradouroSelecao(logradouroBairro.getLogradouro().getLogradouroTipo().getDescricaoAbreviada() + " " +
							logradouroBairro.getLogradouro().getNome());
				}

			}else {
				form.setLogradouroSelecao(null);
				form.setNomeLogradouroSelecao("LOGRADOURO INEXISTENTE");

				request.setAttribute("logradouroNaoEncontrado",true);
				request.setAttribute("nomeCampo", "logradouroSelecao");
				
			}			
		}

	}

	
	private Localidade pesquisarLocalidade(HttpServletRequest request,
		EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form){
		
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
		
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
			
			localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			String msg = "Localidade:" + localidade.getDescricao();
			request.setAttribute("mensagemLocalidade", msg);
			request.setAttribute("nomeCampo", "codigoSetorComercial");
			
			form.setIdLocalidade(Util.adicionarZerosEsquedaNumero(3,localidade.getId().toString()));
			
			form.setIdMunicipio(""+localidade.getMunicipio().getId());
			form.setNomeMunicipio(localidade.getMunicipio().getNome());
		}else {
			

			form.setIdLocalidade(null);
			form.setCodigoSetorComercial(null);
			form.setNumeroQuadra(null);

			request.setAttribute("localidadeNaoEncontrada", "true");
			request.setAttribute("mensagemLocalidade","LOCALIDADE INEXISTENTE");
			request.setAttribute("nomeCampo", "idLocalidade");
			request.removeAttribute("colecaoBairro");
			
		}
		
		return localidade;
	
	}
	
	
	private void pesquisarSetorComercial(HttpServletRequest request,
		EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form){
		
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

		
		if (colecaoSetor != null && !colecaoSetor.isEmpty()) {
			
			SetorComercial setor = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
			
			String msg = "Setor:" + setor.getDescricao();
			request.setAttribute("mensagemSetorComercial", msg);
			request.setAttribute("nomeCampo", "numeroQuadra");
			
			form.setCodigoSetorComercial(Util.adicionarZerosEsquedaNumero(3,""+setor.getCodigo()));
		}else {

			form.setCodigoSetorComercial(null);
			form.setNumeroQuadra(null);

			request.setAttribute("setorComercialNaoEncontrado", "true");
			request.setAttribute("mensagemSetorComercial","SETOR COMERCIAL INEXISTENTE");
			request.setAttribute("nomeCampo", "codigoSetorComercial");
			
		}
	
	}
	
	
	private void pesquisarQuadra(HttpServletRequest request,
			EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form){
			
			String idLocalidade = form.getIdLocalidade();
			String codigoSetor = form.getCodigoSetorComercial();
			String numeroQuadra = form.getNumeroQuadra();
				
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.limparListaParametros();

			filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

			filtroQuadra.adicionarParametro(
				new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, 
					new Integer(idLocalidade)));

			filtroQuadra.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroQuadra.adicionarParametro(
				new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, 
					new Integer(codigoSetor)));
			
			filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));
			
			Collection<Quadra> colecaoQuadra = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
			
			if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
				
				Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
				
				String msg = "Rota:" + quadra.getRota().getCodigo().toString();
				request.setAttribute("mensagemQuadra", msg);
				request.setAttribute("nomeCampo", "bairro");
				
				form.setNumeroQuadra(Util.adicionarZerosEsquedaNumero(3,""+quadra.getNumeroQuadra()));
				
				
				String objetoConsulta = (String) request.getParameter("objetoConsulta");
				
				if(objetoConsulta == null){
					this.pesquisarCapaLote(form, request);
				}
				
			}else {
				

				form.setNumeroQuadra(null);

				request.setAttribute("quadraNaoEncontrada", "true");
				request.setAttribute("mensagemQuadra","QUADRA INEXISTENTE");
				request.setAttribute("nomeCampo", "numeroQuadra");
				
			}
	}
	
	private void validarLocalidade(EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form, HttpServletRequest request){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		if(Util.isVazioOrNulo(colLocalidade)){
			throw new ActionServletException("pesquisa.localidade.inexistente");
		}
	}
	
	private void validarSetorComercial(EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form, HttpServletRequest request){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if(Util.isVazioOrNulo(colSetorComercial)){
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}
	}
	
	private void validarQuadra(EfetuarDigitacaoDadosAtualizacaoCadastralActionForm form, HttpServletRequest request){
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercial()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getNumeroQuadra()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colQuadra = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
		if(Util.isVazioOrNulo(colQuadra)){
			throw new ActionServletException("atencao.quadra.inexistente");
		}
	}

}