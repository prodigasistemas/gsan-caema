package gcom.gui.portal;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * serviços na Loja Virtual da Compesa
 * </p>
 * 
 * @author Magno Gouveia
 * @date 28/06/2011
 */
public class ExibirInserirSolicitacaoServicosPortalVazamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirInserirSolicitacaoServicosPortal");

		HttpSession sessao = httpServletRequest.getSession(false);

		//httpServletRequest.setAttribute("voltarServicos", true);

		Fachada fachada = Fachada.getInstancia();

		InserirSolicitacaoServicosPortalActionForm form = (InserirSolicitacaoServicosPortalActionForm) actionForm;
		
		sessao.removeAttribute("exception");
		sessao.removeAttribute("RASolicitadaComSucesso");
		sessao.removeAttribute("mensagemRA");
		sessao.removeAttribute("dataPrevistaAtendimentoRA");
		sessao.removeAttribute("RAJaSolicitada");
		
		String permissaoExibirNomeEndereco = httpServletRequest.getParameter("permissaoExibirNomeEndereco");		
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.OUTROS_SERVICOS, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO); 
		
		if (httpServletRequest.getParameter("init") != null && httpServletRequest.getParameter("init").equals("1")) {
			form.reset();
			sessao.removeAttribute("dataPrevista");
			sessao.removeAttribute("mensagemAnexo");
		}
		
		// 2.1.3
		boolean imovelInvalido = false;
		String matriculaString = form.getMatricula();			
		String enderecoFormatado = null;			
		permissaoExibirNomeEndereco = "nao";			
		sessao.removeAttribute("enderecoImovel");
		
		if(httpServletRequest.getParameter("pesquisarMatricula") != null){			

			if (Util.verificarIdNaoVazio(matriculaString)) {
				
				try {
					Integer matricula = Integer.parseInt(matriculaString);
					Integer matriculaExistente = this.getFachada().verificarExistenciaImovel(matricula);
		
					if (matriculaExistente == 1 || this.getFachada().verificarExistenciaClienteVirtual(matricula)) {
						
						Imovel imovel = this.getFachada().pesquisarImovel(matricula);
						
						//Municipio
						if(imovel.getLogradouroBairro() != null && imovel.getLogradouroBairro().getBairro() != null
								&& imovel.getLogradouroBairro().getBairro().getMunicipio() != null
								&& imovel.getLogradouroBairro().getBairro().getMunicipio().getId() != null){
							form.setMunicipio(imovel.getLogradouroBairro().getBairro().getMunicipio().getId().toString());
							carregarBairro(form, httpServletRequest);
						}
											
						//Bairro
						if(imovel.getLogradouroBairro() != null && imovel.getLogradouroBairro().getBairro() != null
								&& imovel.getLogradouroBairro().getBairro().getId() != null){
							form.setBairro(imovel.getLogradouroBairro().getBairro().getId().toString());
						}
						
						//Pavimento Rua
						if(imovel.getPavimentoRua() != null && imovel.getPavimentoRua().getId() != null){
							form.setPavimentoRua(imovel.getPavimentoRua().getId().toString());
						}
						
						//Pavimento Calcada
						if(imovel.getPavimentoCalcada() != null && imovel.getPavimentoCalcada().getId() != null){
							form.setPavimentoCalcada(imovel.getPavimentoCalcada().getId().toString());
						}
						
						//Endereço do usuário
						try {
							enderecoFormatado = this.getFachada().pesquisarEnderecoFormatado(matricula);
						} catch (ControladorException e) {
							httpServletRequest.setAttribute("exception", "Erro no sistema!");
							return retorno;
							// throw new ActionServletException("erro.sistema");
						}
						if (enderecoFormatado != null) {
							sessao.setAttribute("enderecoImovel", enderecoFormatado);
						}
						
						//Nome do usuário
						Cliente cliente = fachada.pesquisarClienteUsuarioImovel(matricula);
						form.setNomeUsuario(cliente.getNome());
						permissaoExibirNomeEndereco = "sim";
						
					} else {
						imovelInvalido = true;
						httpServletRequest.setAttribute("imovelInvalido", true);
					}
				} catch (NumberFormatException e) {
					httpServletRequest.setAttribute("nomeCampo", "matricula");
					httpServletRequest.setAttribute("imovelInvalido", true);
				}
			}else if(sessao.getAttribute("solicitacaoTp") != null 
					&& sessao.getAttribute("solicitacaoTp").equals("vazamento")){
				form.setNomeUsuario("");
				form.setMunicipio("");
				form.setBairro("");
				form.setPavimentoRua("");
				form.setPavimentoCalcada("");
			}
		}
		
			// 2.2.4 Tipo Solicitação
			Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = new ArrayList();
			if(httpServletRequest.getParameter("solicitacao") != null && httpServletRequest.getParameter("solicitacao").equalsIgnoreCase("agua")){
				
				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.ID, SolicitacaoTipo.RAMAL_DE_AGUA));
				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				colecaoSolicitacaoTipo  = fachada.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
				
				form.setSolicitacaoTipo(SolicitacaoTipo.RAMAL_DE_AGUA.toString());
				
				sessao.setAttribute("solicitacaoTp", "aguaEsgoto");
				
			}else if(httpServletRequest.getParameter("solicitacao") != null && httpServletRequest.getParameter("solicitacao").equalsIgnoreCase("esgoto")){
				
				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.ID, SolicitacaoTipo.RAMAL_DE_ESGOTO));
				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				colecaoSolicitacaoTipo  = fachada.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
				
				form.setSolicitacaoTipo(SolicitacaoTipo.RAMAL_DE_ESGOTO.toString());
				
				sessao.setAttribute("solicitacaoTp", "aguaEsgoto");
				
			}else{
			
				Collection<Object[]> objectsSolicitacaoTipo =  fachada.pesquisarSolicitacaoTipoLojaVirtual();
				
				if (!Util.isVazioOrNulo(objectsSolicitacaoTipo)) {
					for (Object[] tipo : objectsSolicitacaoTipo) {				
						Short indicadorRA = (Short)tipo[2];			
						if (indicadorRA.compareTo(ConstantesSistema.SIM) == 0){
							SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
							solicitacaoTipo.setId(Integer.parseInt(((Short) tipo[0]).toString()));
							solicitacaoTipo.setDescricao((String) tipo[1]);	
							colecaoSolicitacaoTipo.add(solicitacaoTipo);
						}
					}
				}
				
				sessao.setAttribute("solicitacaoTp", "vazamento");
			}
			
			if (!Util.isVazioOrNulo(colecaoSolicitacaoTipo)) {
				sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
			} else {
				httpServletRequest.setAttribute("exception", "Nenhum tipo de solicitação encontrado!");
				return retorno;
			}
		
		// 2.2.5 Tipo de Especificação
		if (Util.verificarIdNaoVazio(form.getSolicitacaoTipo())) {
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_LOJA_VIRTUAL, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			if(!form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_AGUA.toString()) 
					&& !form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_ESGOTO.toString())){
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_RA_LOJA_VIRTUAL_SEM_MATRICULA, ConstantesSistema.INDICADOR_USO_ATIVO));
			}
			
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, new Integer(form.getSolicitacaoTipo())));
			
			if (!Util.verificarIdNaoVazio(matriculaString) && !imovelInvalido) {
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_OBRIGATORIEDADE_MATRICULA, ConstantesSistema.NAO));
			}
			
			Collection colecaoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			if (!Util.isVazioOrNulo(colecaoEspecificacao)) {
				sessao.setAttribute("colecaoEspecificacao", colecaoEspecificacao);
			} else {
				httpServletRequest.setAttribute("exception", "Nenhuma especificação encontrada!");
				return retorno;
				// throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "SOLICITACAO_TIPO_ESPECIFICACAO");
			}
		} 
		
		//Municipio
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio(FiltroMunicipio.NOME);
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if (!Util.isVazioOrNulo(colecaoMunicipio)) {
			sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
		} else {
			httpServletRequest.setAttribute("exception", "Nenhum Municipio encontrado!");
			return retorno;
		}
		
		//Bairro
		if(httpServletRequest.getParameter("carregarBairro") != null && form.getMunicipio() != null){
			boolean erro = carregarBairro(form, httpServletRequest);
			
			if(erro){
				return retorno;
			}
		}
		
		//Pavimento Rua
		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua(FiltroPavimentoRua.DESCRICAO);
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoPavimentoRua = fachada.pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());
		
		if (!Util.isVazioOrNulo(colecaoPavimentoRua)) {
			sessao.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
		} else {
			httpServletRequest.setAttribute("exception", "Nenhum Pavimento Rua encontrado!");
			return retorno;
		}
		
		//Pavimento Calcada
		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada(FiltroPavimentoCalcada.DESCRICAO);
		filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoPavimentoCalcada = fachada.pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
		
		if (!Util.isVazioOrNulo(colecaoPavimentoCalcada)) {
			sessao.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
		} else {
			httpServletRequest.setAttribute("exception", "Nenhum Pavimento Calçada encontrado!");
			return retorno;
		}
		
		// Calcula a data prevista para o atendimento a solicitação
		Date dataPrevista = null;
		if(Util.verificarIdNaoVazio(form.getEspecificacao())){
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
							Integer.parseInt(form.getEspecificacao()));

			dataPrevista = Util
					.formatarDataFinal(definirDataPrevistaUnidadeDestinoEspecificacaoHelper
							.getDataPrevista());
			
			sessao.setAttribute("dataPrevista", Util.formatarData(dataPrevista));
			
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, Integer.parseInt(form.getEspecificacao())));
			Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = getFachada().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			String mensagemAnexo = "";
			if(solicitacaoTipoEspecificacao != null && solicitacaoTipoEspecificacao.getId() != null){
				mensagemAnexo = solicitacaoTipoEspecificacao.getDescricao() + " - " + "O Cliente deverá apresentar Escritura do Imóvel, Recibo ou Promessa de Compra e Venda registrada em cartório, ou outro documento comprobatório de propriedade do imóvel, CPF e RG."; 
			}
			
			sessao.setAttribute("mensagemAnexo", mensagemAnexo);		
		}		
		
		sessao.setAttribute("dataSolicitacao", Util.formatarData(new Date()));
		httpServletRequest.setAttribute("permissaoExibirNomeEndereco", permissaoExibirNomeEndereco);

		if (form.getNomeSolicitante() == null)
			form.setNomeSolicitante("");
		if (form.getEmail() == null)
			form.setEmail("");
		if (form.getObservacoes() == null)
			form.setObservacoes("");
		if (form.getPontoReferencia() == null)
			form.setPontoReferencia("");
		
		return retorno;
	}
	
	private boolean carregarBairro(InserirSolicitacaoServicosPortalActionForm form, HttpServletRequest httpServletRequest){
		HttpSession sessao = httpServletRequest.getSession(false);
		boolean erro = false;
		
		FiltroBairro filtroBairro = new FiltroBairro(FiltroBairro.NOME);
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, Integer.parseInt(form.getMunicipio())));
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Bairro> colecaoBairro = this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());
		
		if (!Util.isVazioOrNulo(colecaoBairro)) {
			sessao.setAttribute("colecaoBairro", colecaoBairro);
		} else {
			httpServletRequest.setAttribute("exception", "Nenhum Bairro encontrado!");
			erro = true;
		}
		
		return erro;
	}
}