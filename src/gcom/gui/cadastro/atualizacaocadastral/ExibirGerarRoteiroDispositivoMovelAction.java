package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.AreaAtualizacaoCadastral;
import gcom.cadastro.FiltroAreaAtualizacaoCadastral;
import gcom.cadastro.FiltroImovelInscricaoResetorizada;
import gcom.cadastro.ImovelInscricaoResetorizada;
import gcom.cadastro.atualizacaocadastral.bean.ImoveisRoteiroDispositivoMovelHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * [UC 1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 16/11/2012
 *
 */
public class ExibirGerarRoteiroDispositivoMovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("gerarRoteiroDispositivoMovel");
		
		//Form
		GerarRoteiroDispositivoMovelActionForm form = (GerarRoteiroDispositivoMovelActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Sessao
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		String pesquisar = httpServletRequest.getParameter("pesquisar");
		
		//Se veio do menu, limpar a tela
		String menu = httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.reset();
		}
		
		//Pesquisar Empresa
		this.pesquisarEmpresa(form, fachada, httpServletRequest);
		
		//Pesquisar Ligação Água Situação
		this.pesquisarLigacaoAguaSituacao(form, fachada, httpServletRequest);
		
		//Pesquisar Localidade
		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("-1")){
			this.pesquisarCadastradores(form, fachada, httpServletRequest);
			this.pesquisarLocalidade(form, fachada, httpServletRequest);
		}else{
			form.setCadastrador("-1");
			form.setIdLocalidade("-1");
			httpServletRequest.removeAttribute("colecaoLeiturista");
			httpServletRequest.removeAttribute("colecaoLocalidade");
		}
		
		//apagas as quadras selecionadas
		if(httpServletRequest.getParameter("apagarQuadrasSelecionadas") != null){
			form.setQuadrasSelecionadas(new Integer[0]);
		}
		
		//Pesquisar Setor Comercial
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") && !form.getIdLocalidade().equals("-1")){
			this.pesquisarSetorComercial(form, fachada, httpServletRequest);

			//Pesquisar Quadra Inicial e Final
			if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("") && !form.getCodigoSetorComercial().equals("-1")){
				this.pesquisarQuadra(form, fachada, sessao);
			}else{
				Integer[] quadrasSelecionadas = new Integer[0];
				form.setQuadrasSelecionadas(quadrasSelecionadas);
				sessao.setAttribute("colecaoQuadra", new ArrayList<Quadra>());
			}
		}else{			
			form.setCodigoSetorComercial("-1");
			Integer[] quadrasSelecionadas = new Integer[0];
			form.setQuadrasSelecionadas(quadrasSelecionadas);
			sessao.setAttribute("colecaoQuadra", new ArrayList<Quadra>());
			httpServletRequest.removeAttribute("colecaoSetorComercial");
		}
		
		ArrayList<Quadra> colecaoQuadrasSelecionadas = new ArrayList<Quadra>();
		ArrayList<Quadra> colecaoQuadra = (ArrayList<Quadra>) sessao.getAttribute("colecaoQuadra");
		
		
		Integer[] idsQuadrasSelecionadas = 	(Integer[]) form.getQuadrasSelecionadas();
		ArrayList<Integer> idsQuadrasSelecionadasArray = new ArrayList<Integer>();
		if(idsQuadrasSelecionadas.length > 0){
			if(idsQuadrasSelecionadas[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				idsQuadrasSelecionadas = null;
			}else{
				for(Integer idQuadra : idsQuadrasSelecionadas){
					idsQuadrasSelecionadasArray.add(idQuadra);
					
					Quadra novaQuadra = new Quadra();
					novaQuadra.setNumeroQuadra(idQuadra);
					//Adiciona as quadras na colecao de quadras selecionadas
					colecaoQuadrasSelecionadas.add(novaQuadra);
					
					//retira as quadras selecionadas da colecao de quadras
					if(colecaoQuadra != null){
						for(int index = 0; index < colecaoQuadra.size(); index++){
							if(colecaoQuadra.get(index).getNumeroQuadra() == idQuadra.intValue()){
								colecaoQuadra.remove(index);
								break;
							}
						}
					}
				}
			}
		}
		
		//Pesquisar Imóvel
		if(pesquisar != null && pesquisar.equals("sim")){
			Collection<Integer> colLigacaoAguaSituacao = null;
			
			if(form.getLigacaoAguaSituacao() != null && form.getLigacaoAguaSituacao().length > 0){
				colLigacaoAguaSituacao = new ArrayList<Integer>();
				for(int i = 0; i < form.getLigacaoAguaSituacao().length; i++){
					if(!form.getLigacaoAguaSituacao()[i].equals("-1")){
						colLigacaoAguaSituacao.add(Integer.parseInt(form.getLigacaoAguaSituacao()[i]));
					}
				}
			}
			
			Collection<ImoveisRoteiroDispositivoMovelHelper> colecaoHelper = null;

			colecaoHelper = fachada.pesquisarImoveisRoteiroDispositivoMovel(form.getIdLocalidade(), form.getCodigoSetorComercial(), 
								idsQuadrasSelecionadasArray, colLigacaoAguaSituacao, form.getClienteUsuario(), form.getIndicadorSituacaoImovel());

			
			if(!Util.isVazioOrNulo(colecaoHelper)){
				httpServletRequest.setAttribute("colecaoImoveis", colecaoHelper);
				form.setTotalMatriculas("" + colecaoHelper.size());
			}else{
				httpServletRequest.removeAttribute("colecaoImoveis");
				form.setTotalMatriculas("0");
				throw new ActionServletException( "atencao.pesquisa.nenhumresultado", "exibirGerarRoteiroDispositivoMovelAction.do", null, new String[] {} );
			}
		}

		sessao.setAttribute("colecaoQuadrasSelecionadas", colecaoQuadrasSelecionadas);
	
		
		return retorno;
	}
	
	/**
	 * Pesquisar Empresas Liberadas
	 * 
	 * @author Davi Menezes
	 * @date 20/11/2012
	 */
	private void pesquisarEmpresa(GerarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		Collection<Empresa> colEmpresa = null;
		AreaAtualizacaoCadastral areaAtualizacaoCadastral = null;
		
		FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = 
				new FiltroAreaAtualizacaoCadastral(FiltroAreaAtualizacaoCadastral.EMPRESA_DESCRICAO);
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
		filtroAreaAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
				FiltroAreaAtualizacaoCadastral.EMPRESA);
		
		Collection<?> colAreaAtualizacaoCadastral = fachada.pesquisar(filtroAreaAtualizacaoCadastral, AreaAtualizacaoCadastral.class.getName());
		if(!Util.isVazioOrNulo(colAreaAtualizacaoCadastral)){
			colEmpresa = new ArrayList<Empresa>();
			
			Iterator<?> it = colAreaAtualizacaoCadastral.iterator();
			while(it.hasNext()){
				areaAtualizacaoCadastral = (AreaAtualizacaoCadastral) it.next();
				
				if(!colEmpresa.contains(areaAtualizacaoCadastral.getEmpresa())){
					colEmpresa.add(areaAtualizacaoCadastral.getEmpresa());
				}
			}
			
			request.setAttribute("colecaoEmpresa", colEmpresa);
		}else{
			request.removeAttribute("colecaoEmpresa");
		}
	}
	
	/**
	 * Pesquisar Localidades Liberadas
	 * 
	 * @author Davi Menezes
	 * @date 16/11/2012
	 */
	private void pesquisarLocalidade(GerarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		Collection<Localidade> colLocalidade = null;
		AreaAtualizacaoCadastral areaAtualizacaoCadastral = null;
		
		FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = 
				new FiltroAreaAtualizacaoCadastral(FiltroAreaAtualizacaoCadastral.LOCALIDADE_DESCRICAO);
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
		filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroAreaAtualizacaoCadastral.EMPRESA_ID, form.getIdEmpresa()));
		filtroAreaAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
				FiltroAreaAtualizacaoCadastral.LOCALIDADE);
		
		Collection<?> colAreaAtualizacaoCadastral = fachada.pesquisar(filtroAreaAtualizacaoCadastral, AreaAtualizacaoCadastral.class.getName());
		if(!Util.isVazioOrNulo(colAreaAtualizacaoCadastral)){
			colLocalidade = new ArrayList<Localidade>();
			
			Iterator<?> it = colAreaAtualizacaoCadastral.iterator();
			while(it.hasNext()){
				areaAtualizacaoCadastral = (AreaAtualizacaoCadastral) it.next();
				
				if(!colLocalidade.contains(areaAtualizacaoCadastral.getLocalidade())){
					colLocalidade.add(areaAtualizacaoCadastral.getLocalidade());
				}
			}
			
			request.setAttribute("colecaoLocalidade", colLocalidade);
		}else{
			request.removeAttribute("colecaoLocalidade");
		}
	}
	
	/**
	 * Pesquisar Cadastradores de Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 16/11/2012
	 */
	private void pesquisarCadastradores(GerarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		Collection<DadosLeiturista> colecaoLeiturista = new ArrayList<DadosLeiturista>();
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(
				FiltroLeiturista.USUARIO_NOME);
		filtroLeiturista.adicionarParametro(new ParametroSimples(
				FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
		filtroLeiturista.adicionarParametro(new ParametroSimples(
				FiltroLeiturista.ATUALIZACAO_CADASTRAL, ConstantesSistema.SIM));
		filtroLeiturista.adicionarParametro(new ParametroSimples(
				FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));
		filtroLeiturista
				.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
		
		Collection<?> colecao = fachada.pesquisar(filtroLeiturista,
				Leiturista.class.getName());

		if (!Util.isVazioOrNulo(colecao)) {
			Iterator<?> it = colecao.iterator();
			while (it.hasNext()) {
				Leiturista leitu = (Leiturista) it.next();
				DadosLeiturista dadosLeiu = null;
				
				if(leitu.getUsuario() != null){
					dadosLeiu = new DadosLeiturista(leitu.getId(), leitu.getUsuario().getNomeUsuario());
				}
				
				if(!colecaoLeiturista.contains(dadosLeiu)){
					colecaoLeiturista.add(dadosLeiu);
				}
			}
		}

		request.setAttribute("colecaoLeiturista", colecaoLeiturista);
	}
	
	/**
	 * Pesquisar Situação da Ligação de Água
	 * 
	 * @author Davi Menezes
	 * @date 16/11/2012
	 */
	private void pesquisarLigacaoAguaSituacao(GerarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(
				FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if(!Util.isVazioOrNulo(colLigacaoAguaSituacao)){
			request.setAttribute("colecaoLigacaoAguaSituacao", colLigacaoAguaSituacao);
		}else{
			request.removeAttribute("colecaoLigacaoAguaSituacao");
		}
	}
	
	/**
	 * Pesquisar Setores Comerciais Liberados
	 * 
	 * @author Davi Menezes
	 * @date 16/11/2012
	 */
	private void pesquisarSetorComercial(GerarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpServletRequest request){
		Collection<SetorComercial> colecaoSetorComercial = null;
		
//		FiltroImovelInscricaoResetorizada filtroImovelInscricaoResetorizada = 
//				new FiltroImovelInscricaoResetorizada(FiltroImovelInscricaoResetorizada.CODIGO_SETOR_COMERCIAL);
//		filtroImovelInscricaoResetorizada.adicionarParametro(new ParametroSimples(
//				FiltroImovelInscricaoResetorizada.ID_LOCALIDADE, form.getIdLocalidade()));
//		
//		Collection<?> colImovelInscricaoResetorizada = fachada.pesquisar(
//				filtroImovelInscricaoResetorizada, ImovelInscricaoResetorizada.class.getName());
//		if(!Util.isVazioOrNulo(colImovelInscricaoResetorizada)){
//			ImovelInscricaoResetorizada imovelInscricaoResetorizada = null;
//			
//			colecaoSetorComercial = new ArrayList<SetorComercial>();
//			
//			SetorComercial setorComercial = new SetorComercial();
//			
//			String codigoSetorAnterior = "";
//			
//			Iterator<?> it = colImovelInscricaoResetorizada.iterator();
//			while(it.hasNext()){
//				imovelInscricaoResetorizada = (ImovelInscricaoResetorizada) it.next();
//				
//				setorComercial = new SetorComercial();
//				setorComercial.setCodigo(imovelInscricaoResetorizada.getCodigoSetorComercial());
//				
//				if(!codigoSetorAnterior.equals(String.valueOf(setorComercial.getCodigo()))){
//					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
//					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
//					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, imovelInscricaoResetorizada.getCodigoSetorComercial()));
//					
//					Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
//					if(!Util.isVazioOrNulo(colSetorComercial)){
//						SetorComercial auxSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
//						colecaoSetorComercial.add(auxSetorComercial);
//					}else{
//						colecaoSetorComercial.add(setorComercial);
//					}
//				}
//				
//				codigoSetorAnterior = String.valueOf(setorComercial.getCodigo()); 
//			}
//			
//			request.setAttribute("colecaoSetorComercial", colecaoSetorComercial);
//		}else{
			FiltroAreaAtualizacaoCadastral filtroAreaAtualizacaoCadastral = 
					new FiltroAreaAtualizacaoCadastral(FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL_CODIGO);
			filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroAreaAtualizacaoCadastral.CODIGO_SITUACAO, ConstantesSistema.SIM));
			filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroAreaAtualizacaoCadastral.LOCALIDADE_ID, form.getIdLocalidade()));
			filtroAreaAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroAreaAtualizacaoCadastral.EMPRESA_ID, form.getIdEmpresa()));
			filtroAreaAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
					FiltroAreaAtualizacaoCadastral.SETOR_COMERCIAL);
			
			Collection<?> colAreaAtualizacaoCadastral = fachada.pesquisar(
					filtroAreaAtualizacaoCadastral, AreaAtualizacaoCadastral.class.getName());
			if(!Util.isVazioOrNulo(colAreaAtualizacaoCadastral)){
				AreaAtualizacaoCadastral areaAtualizacaoCadastral = null;
				
				colecaoSetorComercial = new ArrayList<SetorComercial>();
				
				Iterator<?> it = colAreaAtualizacaoCadastral.iterator();
				while(it.hasNext()){
					areaAtualizacaoCadastral = (AreaAtualizacaoCadastral) it.next();
					
					if(areaAtualizacaoCadastral.getSetorComercial() != null){
						if(!colecaoSetorComercial.contains(areaAtualizacaoCadastral.getSetorComercial())){
							colecaoSetorComercial.add(areaAtualizacaoCadastral.getSetorComercial());
						}
					}
				}
				
				request.setAttribute("colecaoSetorComercial", colecaoSetorComercial);
			}
			
			if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty() ) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL);
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				if(!Util.isVazioOrNulo(colSetorComercial)){
					SetorComercial setorComercial = null;
					
					colecaoSetorComercial = new ArrayList<SetorComercial>();
					
					Iterator<?> it = colSetorComercial.iterator();
					while(it.hasNext()){
						setorComercial = (SetorComercial) it.next();
						
						if(!colecaoSetorComercial.contains(setorComercial)){
							colecaoSetorComercial.add(setorComercial);
						}
					}
					
					request.setAttribute("colecaoSetorComercial", colecaoSetorComercial);
				}
			}
//		}
	}
	
	/**
	 * Pesquisar Quadras Liberadas
	 * 
	 * @author Davi Menezes
	 * @date 16/11/2012
	 */
	private void pesquisarQuadra(GerarRoteiroDispositivoMovelActionForm form, Fachada fachada, HttpSession sessao){
		Collection<Quadra> colecaoQuadra = null;
		
//		FiltroImovelInscricaoResetorizada filtroImovelInscricaoResetorizada = 
//				new FiltroImovelInscricaoResetorizada(FiltroImovelInscricaoResetorizada.NUMERO_QUADRA);
//		filtroImovelInscricaoResetorizada.adicionarParametro(new ParametroSimples(
//				FiltroImovelInscricaoResetorizada.ID_LOCALIDADE, form.getIdLocalidade()));
//		filtroImovelInscricaoResetorizada.adicionarParametro(new ParametroSimples(
//				FiltroImovelInscricaoResetorizada.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
//		
//		Collection<?> colImovelInscricaoResetorizada = fachada.pesquisar(
//				filtroImovelInscricaoResetorizada, ImovelInscricaoResetorizada.class.getName());
//		if(!Util.isVazioOrNulo(colImovelInscricaoResetorizada)){
//			ImovelInscricaoResetorizada imovelInscricaoResetorizada = null;
//			Quadra quadra = null;
//			
//			colecaoQuadra = new ArrayList<Quadra>();
//			
//			String numQuadraAnterior = "";
//			
//			Iterator<?> it = colImovelInscricaoResetorizada.iterator();
//			while(it.hasNext()){
//				imovelInscricaoResetorizada = (ImovelInscricaoResetorizada) it.next();
//				quadra = new Quadra();
//				quadra.setNumeroQuadra(imovelInscricaoResetorizada.getNumeroQuadra());
//				
//				if(!numQuadraAnterior.equals(String.valueOf(quadra.getNumeroQuadra()))){
//					colecaoQuadra.add(quadra);
//				}
//				
//				numQuadraAnterior = String.valueOf(imovelInscricaoResetorizada.getNumeroQuadra());
//			}
//			
//			sessao.setAttribute("colecaoQuadra", colecaoQuadra);
//		}else{
			FiltroQuadra filtroQuadra = new FiltroQuadra(FiltroQuadra.NUMERO_QUADRA);
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<?> colQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			if(!Util.isVazioOrNulo(colQuadra)){
				Quadra quadra = null;
				
				colecaoQuadra = new ArrayList<Quadra>();
				
				String numQuadraAnterior = "";
				
				Iterator<?> it = colQuadra.iterator();
				while(it.hasNext()){
					quadra = (Quadra) it.next();
					
					if(!numQuadraAnterior.equals(String.valueOf(quadra.getNumeroQuadra()))){
						colecaoQuadra.add(quadra);
					}
					
					numQuadraAnterior = String.valueOf(quadra.getNumeroQuadra());
				}
			
				sessao.setAttribute("colecaoQuadra", colecaoQuadra);
			}else{
				Integer[] quadrasSelecionadas = new Integer[0];
				form.setQuadrasSelecionadas(quadrasSelecionadas);
				sessao.setAttribute("colecaoQuadra", new ArrayList<Quadra>());
			}
//		}
	}
	
}
