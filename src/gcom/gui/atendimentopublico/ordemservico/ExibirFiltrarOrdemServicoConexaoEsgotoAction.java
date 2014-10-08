package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroPerfilLigacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * 
 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
 * @author Hugo Azevedo
 * @date 02/08/2013
 * 
 */
public class ExibirFiltrarOrdemServicoConexaoEsgotoAction extends GcomAction {

	@SuppressWarnings("rawtypes")
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");		
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;

		//Coleções da tela principal
		//========================================================================
		
		//1.9.3 Diâmetro da Ligação
		FiltroDiametroLigacao filtroDiametroLig = new FiltroDiametroLigacao();
		filtroDiametroLig.adicionarParametro(new ParametroSimples(FiltroDiametroLigacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroDiametroLig.setCampoOrderBy(FiltroDiametroLigacao.ID);
		Collection colecaoDiametroLig = (Collection)this.getFachada().pesquisar(filtroDiametroLig, LigacaoEsgotoDiametro.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoDiametroLigacao", colecaoDiametroLig);
		
		//1.9.4 Material da Ligação
		FiltroMaterialLigacao filtroMaterial = new FiltroMaterialLigacao();
		filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterialLigacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroMaterial.setCampoOrderBy(FiltroMaterialLigacao.DESCRICAO);
		Collection colecaoMaterialLigacao = (Collection)this.getFachada().pesquisar(filtroMaterial, LigacaoEsgotoMaterial.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoMaterialLigacao", colecaoMaterialLigacao);
		
		//1.9.5 Perfil da Ligação
		FiltroPerfilLigacao filtroPerfil = new FiltroPerfilLigacao();
		filtroPerfil.adicionarParametro(new ParametroSimples(FiltroPerfilLigacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroPerfil.setCampoOrderBy(FiltroPerfilLigacao.DESCRICAO);
		Collection colecaoPerfilLigacao = (Collection)this.getFachada().pesquisar(filtroPerfil, LigacaoEsgotoPerfil.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoPerfilLigacao", colecaoPerfilLigacao);
		
		
		//1.9.6 Percentual de Coleta
		//[FB0008] - Validar Percentual de Coleta
		//			 Caso o usuário não possua permissão especial para alterar o percentual de coleta, exibir o campo bloqueado para alteração.
		if (!this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO, usuarioLogado)) {
			form.setBloquearPercentualColeta(ConstantesSistema.SIM.toString());
		}
		if(form.getPercentualColeta() == null){
			form.setPercentualColeta("100,00");
		}
		
		
		//1.9.8 Ligação Origem
		FiltroLigacaoOrigem filtroLigacaoOrigem = new FiltroLigacaoOrigem();
		filtroLigacaoOrigem.adicionarParametro(new ParametroSimples(FiltroLigacaoOrigem.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLigacaoOrigem.setCampoOrderBy(FiltroLigacaoOrigem.DESCRICAO);
		Collection colecaoLigacaoOrigem = (Collection)this.getFachada().pesquisar(filtroLigacaoOrigem, LigacaoOrigem.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoLigacaoOrigem", colecaoLigacaoOrigem);
		
		//1.9.9 Condição do Esgotamento
		FiltroLigacaoEsgotoEsgotamento filtroEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
		filtroEsgotamento.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEsgotamento.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);
		Collection colecaoCondicaoEsgotamento = (Collection)this.getFachada().pesquisar(filtroEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoCondicaoEsgotamento", colecaoCondicaoEsgotamento);
		
		//1.9.11 Situação da Caixa de Inspeção
		FiltroLigacaoEsgotoCaixaInspecao filtroLigEsgotoCaixa = new FiltroLigacaoEsgotoCaixaInspecao();
		filtroLigEsgotoCaixa.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoCaixaInspecao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLigEsgotoCaixa.setCampoOrderBy(FiltroLigacaoEsgotoCaixaInspecao.DESCRICAO);
		Collection colecaoSituacaoCaixaInspecao = (Collection)this.getFachada().pesquisar(filtroLigEsgotoCaixa, LigacaoEsgotoCaixaInspecao.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoSituacaoCaixaInspecao", colecaoSituacaoCaixaInspecao);
		
		//1.9.12 Destino Dejetos
		FiltroLigacaoEsgotoDestinoDejetos filtroDestinoDejetos = new FiltroLigacaoEsgotoDestinoDejetos();
		filtroDestinoDejetos.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoDestinoDejetos.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroDestinoDejetos.setCampoOrderBy(FiltroLigacaoEsgotoDestinoDejetos.DESCRICAO);
		Collection colecaoDestinoDejetos = (Collection)this.getFachada().pesquisar(filtroDestinoDejetos, LigacaoEsgotoDestinoDejetos.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoDestinoDejetos", colecaoDestinoDejetos);
		
		//Destino de Aguas Pluviais
		FiltroLigacaoEsgotoDestinoAguasPluviais filtroDestinoAguasPluviais = new FiltroLigacaoEsgotoDestinoAguasPluviais();
		filtroDestinoAguasPluviais.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoDestinoAguasPluviais.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroDestinoAguasPluviais.setCampoOrderBy(FiltroLigacaoEsgotoCaixaInspecao.DESCRICAO);
		Collection colecaoDestinoAguasPluviais = (Collection)this.getFachada().pesquisar(filtroDestinoAguasPluviais, LigacaoEsgotoDestinoAguasPluviais.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoDestinoAguasPluviais", colecaoDestinoAguasPluviais);
		
		//Grupo de Faturamento
		FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
		filtroFatGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisar(filtroFatGrupo, FaturamentoGrupo.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		//========================================================================
		
		
		if(form.getIdImovel()!=null && !form.getIdImovel().equals("")){
			this.pesquisarImovel(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		
		if(form.getIdMunicipio()!=null && !form.getIdMunicipio().equals("")){
			this.pesquisarMunicipio(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		
		if(form.getIdLocalidadeInicial()!=null && !form.getIdLocalidadeInicial().equals("")){
			this.pesquisarLocalidadeInicial(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		
		if(form.getIdLocalidadeFinal()!=null && !form.getIdLocalidadeFinal().equals("")){
			this.pesquisarLocalidadeFinal(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}

		
		if(form.getIdSetorComercialInicial()!=null && !form.getIdSetorComercialInicial().equals("")){
			this.pesquisarSetorComercialInicial(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		
		if(form.getIdSetorComercialFinal()!=null && !form.getIdSetorComercialFinal().equals("")){
			this.pesquisarSetorComercialFinal(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		//[FB0016] Verificar Permissão Informar Encerramento Automático
		//---------------------------------------------------------------------------
		//Caso o usuário logado possua permissão especial para informar encerramento automático, 
		//habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		//---------------------------------------------------------------------------
		
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
		
	}


	/**[FB0016] Verificar Permissão Informar Encerramento Automático
	 * Caso o usuário logado possua permissão especial para informar encerramento automático, 
	 * habilitar o campo "Informar encerramento automático"
	 */
	private void verificarPermissaoEspecialEncerramentoAutomatico(
			HttpServletRequest httpServletRequest, Usuario usuarioLogado,
			FiltrarOrdemServicoConexaoEsgotoActionForm form) {
		
		//Variavel para controle de valores do radioButton, não estava setando o valor do form.
		String indicadorPermissao = httpServletRequest.getParameter("indicadorPermissao");
		
		if(indicadorPermissao==null){
			
			indicadorPermissao = "1";
		}
		
		String indicadorPermissaoEspecialEncerramentoAutomatico = httpServletRequest.getParameter("indicadorPermissaoEspecialEncerramentoAutomatico");
		
		if(indicadorPermissaoEspecialEncerramentoAutomatico == null){
			
			indicadorPermissaoEspecialEncerramentoAutomatico = "1";
		}
		if (this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.INFORMA_ENCERRAMENTO_AUTOMATICO, usuarioLogado)) {
			
			if(indicadorPermissaoEspecialEncerramentoAutomatico.equals("1")){
					
				if(indicadorPermissaoEspecialEncerramentoAutomatico.equals("1") && indicadorPermissao.equals("1") 
						&& form.getIndicadorPermissaoEspecialEncerramentoAutomatico()==null 
						|| form.getIndicadorPermissaoEspecialEncerramentoAutomatico().equals("")){
					
					httpServletRequest.setAttribute("indicadorPermissaoEspecialEncerramentoAutomatico", ConstantesSistema.SIM.toString());
					
					form.setIndicadorEncerramentoAutomatico(ConstantesSistema.SIM.toString());
					
					form.setIndicadorPermissaoEspecialEncerramentoAutomatico(ConstantesSistema.SIM.toString());
					
					httpServletRequest.setAttribute("indicadorPermissao", String.valueOf(ConstantesSistema.SIM));
					
					form.setBloquearGrupoFaturamento(String.valueOf(ConstantesSistema.NAO));
					
				}else{
					form.setBloquearGrupoFaturamento(String.valueOf(ConstantesSistema.NAO));
				}
				
			}else{
				httpServletRequest.setAttribute("indicadorPermissaoEspecialEncerramentoAutomatico", ConstantesSistema.NAO.toString());
				httpServletRequest.setAttribute("indicadorPermissao", String.valueOf(ConstantesSistema.NAO));
				form.setIndicadorPermissaoEspecialEncerramentoAutomatico(ConstantesSistema.SIM.toString());
			}
		}else{
			httpServletRequest.setAttribute("indicadorPermissaoEspecialEncerramentoAutomatico", ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("indicadorPermissao", String.valueOf(ConstantesSistema.NAO));
			form.setIndicadorPermissaoEspecialEncerramentoAutomatico(ConstantesSistema.NAO.toString());
		}
		
		if(form.getIndicadorEncerramentoAutomatico().equals("2")){
			form.setBloquearGrupoFaturamento(String.valueOf(ConstantesSistema.SIM));
		}
	}
	
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0001] Verificar Existência de Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 02/08/2013
	 * 
	 */
	public ActionForward pesquisarImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");		
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		String idImovel = form.getIdImovel();
		
		Imovel imovel = this.getFachada().pesquisarImovel(Integer.parseInt(idImovel));
		if(imovel != null){
			
			//Caso a situação da ligação de esgoto não seja factível ou potencial
			if(imovel.getLigacaoEsgotoSituacao() == null || (
			   imovel.getLigacaoEsgotoSituacao().getId().compareTo(LigacaoEsgotoSituacao.FACTIVEL) != 0 && 
			   imovel.getLigacaoEsgotoSituacao().getId().compareTo(LigacaoEsgotoSituacao.POTENCIAL) != 0)){
				//exibir a mensagem "A situação da ligação de esgoto deve ser Potencial ou Factível"
				throw new ActionServletException("atencao.situacao_esgoto_potencial_factivel");
			}
			
			//Caso o imóvel esteja excluído
			if(imovel.getIndicadorExclusao().compareTo(ConstantesSistema.SIM) == 0){
				
				//exibir a mensagem "Imóvel <<IMOV_ID>> excluído."
				throw new ActionServletException("atencao.pesquisa.imovel.matricula.excluido",null,imovel.getId().toString());
			}
			
			//Caso o imóvel não possua perfil com geração de dados para leitura
			if(imovel.getImovelPerfil() == null || (
					imovel.getImovelPerfil().getIndicadorGerarDadosLeitura().compareTo(ConstantesSistema.NAO) == 0)){
				
				//exibir a mensagem "Imóvel <<IMOV_ID>> com perfil << IPER_DSIMOVELPERFIL>> não gera dados para leitura."
				throw new ActionServletException("atencao.imovel_nao_gera_dados_leitura", 
														imovel.getId().toString(),
														imovel.getImovelPerfil().getDescricao());
				
			}
			
			
			// Caso o imovel possua ordem de servido de conexao de esgoto pendente
			Integer idOrdemServico = this.getFachada().pesquisarOSConexaoEsgotoPendente(imovel.getId());
			if(idOrdemServico !=null){
				throw new ActionServletException("atencao.imovel_com_os_conexao_esgoto_pendente", 
						imovel.getId().toString(),idOrdemServico.toString());
			}
			
			
			// Caso a situacao de ligacao de agua do imovel seja potencial ou
			// factivel
			if (imovel.getLigacaoAguaSituacao() != null
					&& (imovel.getLigacaoAguaSituacao().getId().toString()
							.equals(LigacaoAguaSituacao.POTENCIAL.toString()) || imovel
							.getLigacaoAguaSituacao().getId().toString()
							.equals(LigacaoAguaSituacao.FACTIVEL.toString()))) {

				throw new ActionServletException(
						"atencao.situacao_agua_potencial_factivel", imovel
								.getId().toString(), imovel
								.getLigacaoAguaSituacao().getDescricao());
			}

			// Caso a categoria do imovel seja publica
			if (imovel.getCategoriaPrincipalId().toString()
					.equals(Categoria.PUBLICO.toString())) {

				throw new ActionServletException(
						"atencao.imovel_categoria_publico", null, imovel
								.getId().toString());
			}
			
			
			String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(imovel.getId());
			form.setDescricaoImovel(inscricaoImovel);
			this.getSessao(httpServletRequest).removeAttribute("imovelInexistenteException");
			
			form.setBloquearGrupoFaturamento(String.valueOf(ConstantesSistema.NAO));
			FaturamentoGrupo faturamentoGrupo = this.getFachada().pesquisarGrupoImovel(new Integer(imovel.getId()));
			form.setIdGrupoFaturamento(faturamentoGrupo.getId().toString());
			
			/*//[FE0010] - Verificar indicador Telemedido
			if(faturamentoGrupo != null && faturamentoGrupo.getId() != null){
				boolean indicadorMedicaoTelemedido = this.getFachada().verificarIndicadorTelemedido(imovel.getId());
				if(indicadorMedicaoTelemedido){
					throw new ActionServletException(
							"atencao.imovel_telemedido", null, imovel
									.getId().toString());
				}
			}*/
			
			
			//1.4.2.1 Caso o Imóvel seja informado, o sistema deverá bloquear os campos: 
			//        município, logradouro, inscrição inicial, inscrição final
			form.setBloquearMunicipio(String.valueOf(ConstantesSistema.SIM));
			form.setBloquearLogradouro(String.valueOf(ConstantesSistema.SIM));
			form.setBloquearInscricaoInicial(String.valueOf(ConstantesSistema.SIM));
			form.setBloquearInscricaoFinal(String.valueOf(ConstantesSistema.SIM));

		}
		
		//Caso a matrícula do imóvel não exista, exibir a mensagem "Imóvel Inexistente"
		//e retornar para o passo correspondente no fluxo principal
		else{
			this.getSessao(httpServletRequest).setAttribute("imovelInexistenteException", "sim");
			form.setIdImovel("");
			form.setDescricaoImovel("IMÓVEL INEXISTENTE");
			form.setBloquearMunicipio(String.valueOf(ConstantesSistema.NAO));
			form.setBloquearLogradouro(String.valueOf(ConstantesSistema.NAO));
			form.setBloquearInscricaoInicial(String.valueOf(ConstantesSistema.NAO));
			form.setBloquearInscricaoFinal(String.valueOf(ConstantesSistema.NAO));
			//form.setBloquearGrupoFaturamento(String.valueOf(ConstantesSistema.NAO));
			
		}
		
		
		//[FB0016] Verificar Permissão Informar Encerramento Automático
		//---------------------------------------------------------------------------
		//Caso o usuário logado possua permissão especial para informar encerramento automático, 
		//habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
		
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FB0001] Limpar Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	public ActionForward apagarImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");	
		
		form.setIdImovel(null);
		form.setDescricaoImovel(null);
		form.setIdGrupoFaturamento("");
		this.getSessao(httpServletRequest).removeAttribute("imovelInexistenteException");
		form.setBloquearMunicipio(String.valueOf(ConstantesSistema.NAO));
		form.setBloquearLogradouro(String.valueOf(ConstantesSistema.SIM));
		form.setBloquearInscricaoInicial(String.valueOf(ConstantesSistema.NAO));
		form.setBloquearInscricaoFinal(String.valueOf(ConstantesSistema.NAO));
		form.setBloquearGrupoFaturamento(String.valueOf(ConstantesSistema.NAO));
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
		
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0002] Validar município
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ActionForward pesquisarMunicipio(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		String idMunicipio = form.getIdMunicipio();
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		//Removendo o logradouro dependente
		form.setIdLogradouro(null);
		form.setDescricaoLogradouro(null);
		this.getSessao(httpServletRequest).removeAttribute("logradouroInexistenteException");
		
		
		Municipio mun = this.getFachada().pesquisarMunicipio(Integer.parseInt(idMunicipio));
		if(mun != null){
			form.setIdMunicipio(idMunicipio);
			form.setDescricaoMunicipio(mun.getNome());
			this.getSessao(httpServletRequest).removeAttribute("municipioInexistenteException");
			
			form.setBloquearLogradouro(String.valueOf(ConstantesSistema.NAO));
			
			form.setBloquearInscricaoInicial(String.valueOf(ConstantesSistema.SIM));
			form.setBloquearInscricaoFinal(String.valueOf(ConstantesSistema.SIM));
			form.setBloquearImovel(String.valueOf(ConstantesSistema.SIM));
			
			Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisarGruposFaturamentoMunicipio(mun.getId());
			this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
			
		}
		else{
			form.setIdMunicipio(null);
			form.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
			this.getSessao(httpServletRequest).setAttribute("municipioInexistenteException", "sim");
			
			form.setBloquearLogradouro(String.valueOf(ConstantesSistema.SIM));
			
			form.setBloquearInscricaoInicial(String.valueOf(ConstantesSistema.NAO));
			form.setBloquearInscricaoFinal(String.valueOf(ConstantesSistema.NAO));
			
			//Grupo de Faturamento
			FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
			filtroFatGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
			Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisar(filtroFatGrupo, FaturamentoGrupo.class.getName());
			this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
			
		}
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
		
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0002] Validar município
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ActionForward apagarMunicipio(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		// Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest)
				.getAttribute("usuarioLogado");
		
		form.setIdMunicipio(null);
		form.setDescricaoMunicipio(null);
		form.setIdLogradouro(null);
		form.setDescricaoLogradouro(null);
		
		this.getSessao(httpServletRequest).removeAttribute("municipioInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("logradouroInexistenteException");
		
		form.setBloquearLogradouro(String.valueOf(ConstantesSistema.SIM));
		form.setBloquearInscricaoInicial(String.valueOf(ConstantesSistema.NAO));
		form.setBloquearInscricaoFinal(String.valueOf(ConstantesSistema.NAO));
		form.setBloquearImovel(String.valueOf(ConstantesSistema.NAO));
		
		
		//Grupo de Faturamento
		FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
		filtroFatGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisar(filtroFatGrupo, FaturamentoGrupo.class.getName());
		this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	
	}
	

	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0003] Validar logradouro
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward pesquisarLogradouro(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		String idMunicipio = form.getIdMunicipio();
		String idLogradouro = form.getIdLogradouro();
		
		FiltroLogradouro filtro = new FiltroLogradouro();
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, idLogradouro));
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID_MUNICIPIO, idMunicipio));
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Logradouro> colecaoLog = this.getFachada().pesquisar(filtro,Logradouro.class.getName());
		if(colecaoLog != null && colecaoLog.size() == 1){
			Logradouro log = (Logradouro)Util.retonarObjetoDeColecao(colecaoLog);
			form.setIdLogradouro(idLogradouro);
			form.setDescricaoLogradouro(log.getDescricaoFormatada());
			this.getSessao(httpServletRequest).removeAttribute("logradouroInexistenteException");
			
		}
		else{
			form.setIdLogradouro(null);
			form.setDescricaoLogradouro("LOGRADOURO INEXISTENTE");
			this.getSessao(httpServletRequest).setAttribute("logradouroInexistenteException","sim");
			
		}
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
		
	}
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0004] Verificar Existência Localidade
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ActionForward pesquisarLocalidadeInicial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		
		//Apagando o Setor Comercial e Quadra antes de pesquisar
		form.setIdSetorComercialInicial(null);
		form.setCodSetorComercialInicial(null);
		form.setDescricaoSetorComercialInicial(null);
		form.setIdSetorComercialFinal(null);
		form.setCodSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setQuadraInicial(null);
		form.setQuadraFinal(null);
		
		this.getSessao(httpServletRequest).removeAttribute("setorComercialInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		Localidade loc = this.getFachada().pesquisarLocalidade(Integer.parseInt(idLocalidadeInicial));
		if(loc != null){
			form.setIdLocalidadeInicial(idLocalidadeInicial);
			form.setDescricaoLocalidadeInicial(loc.getDescricao());
			form.setIdLocalidadeFinal(idLocalidadeInicial);
			form.setDescricaoLocalidadeFinal(loc.getDescricao());
			this.getSessao(httpServletRequest).removeAttribute("localidadeInicialInexistenteException");
			
			//Desbloqueando a edição do setor comercial
			form.setBloquearSetorComercial(String.valueOf(ConstantesSistema.NAO));
			
			//E bloqueando Município/ Logradouro
			form.setBloquearMunicipio(String.valueOf(ConstantesSistema.SIM));
			form.setBloquearLogradouro(String.valueOf(ConstantesSistema.SIM));
			form.setBloquearImovel(String.valueOf(ConstantesSistema.SIM));
			
			Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisarGruposFaturamentoIntervaloLocalidade(
					new Integer(form.getIdLocalidadeInicial()), new Integer(form.getIdLocalidadeFinal()));
			this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		}
		
		else{
			form.setIdLocalidadeInicial(null);
			form.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
			form.setIdLocalidadeFinal(null);
			form.setDescricaoLocalidadeFinal(null);
			this.getSessao(httpServletRequest).setAttribute("localidadeInicialInexistenteException","sim");
			
			//Bloqueando a edição do setor comercial
			form.setBloquearSetorComercial(String.valueOf(ConstantesSistema.SIM));
			
			//E desbloqueando município
			form.setBloquearMunicipio(String.valueOf(ConstantesSistema.NAO));
			form.setBloquearImovel(String.valueOf(ConstantesSistema.NAO));
			
			//Grupo de Faturamento
			FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
			filtroFatGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
			
			Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisar(filtroFatGrupo, FaturamentoGrupo.class.getName());
			this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		}
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0004] Verificar Existência Localidade
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ActionForward pesquisarLocalidadeFinal(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		
		//Apagando o Setor Comercial e Quadra antes de pesquisar
		form.setIdSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setQuadraFinal(null);
		
		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		Localidade loc = this.getFachada().pesquisarLocalidade(Integer.parseInt(idLocalidadeFinal));
		if(loc != null){
	
			form.setIdLocalidadeFinal(idLocalidadeFinal);
			form.setDescricaoLocalidadeFinal(loc.getDescricao());
			this.getSessao(httpServletRequest).removeAttribute("localidadeFinalInexistenteException");
			
			
			Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisarGruposFaturamentoIntervaloLocalidade(
					new Integer(form.getIdLocalidadeInicial()), new Integer(form.getIdLocalidadeFinal()));
			this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		}
		
		else{
			form.setIdLocalidadeFinal(null);
			form.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
			this.getSessao(httpServletRequest).setAttribute("localidadeFinalInexistenteException","sim");
			
			//Grupo de Faturamento
			FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
			filtroFatGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
			Collection colecaoGrupoFaturamento = (Collection)this.getFachada().pesquisar(filtroFatGrupo, FaturamentoGrupo.class.getName());
			this.getSessao(httpServletRequest).setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		}
		
		
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FB0003] Limpar Localidade.
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ActionForward apagarLocalidadeInicial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");

		form.setIdLocalidadeInicial(null);
		form.setDescricaoLocalidadeInicial(null);
		form.setIdLocalidadeFinal(null);
		form.setDescricaoLocalidadeFinal(null);
		form.setIdSetorComercialInicial(null);
		form.setCodSetorComercialInicial(null);
		form.setDescricaoSetorComercialInicial(null);
		form.setIdSetorComercialFinal(null);
		form.setCodSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setQuadraInicial(null);
		form.setQuadraFinal(null);
		form.setRotaInicial(null);
		form.setRotaSequenciaInicial(null);
		form.setRotaFinal(null);
		form.setRotaSequenciaFinal(null);
		
		form.setBloquearMunicipio(String.valueOf(ConstantesSistema.NAO));
		form.setBloquearSetorComercial(String.valueOf(ConstantesSistema.SIM));
		form.setBloquearQuadra(String.valueOf(ConstantesSistema.SIM));
		form.setBloquearRota(String.valueOf(ConstantesSistema.SIM));
		form.setBloquearImovel(String.valueOf(ConstantesSistema.NAO));
		

		this.getSessao(httpServletRequest).removeAttribute("localidadeInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("localidadeFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("setorComercialInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		// Grupo de Faturamento
		FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
		filtroFatGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoGrupoFaturamento = (Collection) this.getFachada()
				.pesquisar(filtroFatGrupo, FaturamentoGrupo.class.getName());
		this.getSessao(httpServletRequest).setAttribute(
				"colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FB0003] Limpar Localidade.
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ActionForward apagarLocalidadeFinal(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;

		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
	
		form.setIdLocalidadeFinal(null);
		form.setDescricaoLocalidadeFinal(null);
		form.setIdSetorComercialFinal(null);
		form.setCodSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setQuadraFinal(null);
		form.setRotaFinal(null);
		form.setRotaSequenciaFinal(null);
		


		this.getSessao(httpServletRequest).removeAttribute("localidadeFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		// Grupo de Faturamento
		FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
		filtroFatGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoGrupoFaturamento = (Collection) this.getFachada()
				.pesquisar(filtroFatGrupo, FaturamentoGrupo.class.getName());
		this.getSessao(httpServletRequest).setAttribute(
				"colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0005] Verificar existência do setor Comercial
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	public ActionForward pesquisarSetorComercialInicial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String codSetorComercialInicial = form.getCodSetorComercialInicial();
		
		//Apagando a Quadra antes de pesquisar
		form.setQuadraInicial(null);
		form.setQuadraFinal(null);
		
		
		this.getSessao(httpServletRequest).removeAttribute("setorComercialInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		SetorComercial stcm = this.getFachada().obterSetorComercialLocalidade(idLocalidadeInicial,codSetorComercialInicial);
		if(stcm != null){
			
			form.setIdSetorComercialInicial(stcm.getId().toString());
			form.setCodSetorComercialInicial(codSetorComercialInicial);
			form.setDescricaoSetorComercialInicial(stcm.getDescricao());
			form.setIdSetorComercialFinal(stcm.getId().toString());
			form.setCodSetorComercialFinal(codSetorComercialInicial);
			form.setDescricaoSetorComercialFinal(stcm.getDescricao());
			this.getSessao(httpServletRequest).removeAttribute("setorComercialInicialInexistenteException");
			this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
			
			//Desbloqueando a edição da Quadra
			form.setBloquearQuadra(String.valueOf(ConstantesSistema.NAO));
			
		}
		
		else{
			form.setIdSetorComercialInicial(null);
			form.setCodSetorComercialInicial(null);
			form.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
			form.setIdSetorComercialFinal(null);
			form.setCodSetorComercialFinal(null);
			form.setDescricaoSetorComercialFinal(null);
			this.getSessao(httpServletRequest).setAttribute("setorComercialInicialInexistenteException","sim");
			
			//Bloqueando a edição do setor comercial
			form.setBloquearQuadra(String.valueOf(ConstantesSistema.SIM));
		}
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0005] Verificar existência do setor Comercial
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	public ActionForward pesquisarSetorComercialFinal(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String codSetorComercialFinal = form.getCodSetorComercialFinal();
		
		//Apagando a Quadra antes de pesquisar
		form.setQuadraFinal(null);
		

		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		SetorComercial stcm = this.getFachada().obterSetorComercialLocalidade(idLocalidadeFinal,codSetorComercialFinal);
		if(stcm != null){
			
			form.setIdSetorComercialFinal(stcm.getId().toString());
			form.setCodSetorComercialFinal(codSetorComercialFinal);
			form.setDescricaoSetorComercialFinal(stcm.getDescricao());
			this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
			
		}		
		else{

			form.setIdSetorComercialFinal(null);
			form.setDescricaoSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
			this.getSessao(httpServletRequest).setAttribute("setorComercialFinalInexistenteException","sim");
		}
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FB0004] Limpar Setor Comercial
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	public ActionForward apagarSetorComercialInicial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");

		form.setIdSetorComercialInicial(null);
		form.setCodSetorComercialInicial(null);
		form.setDescricaoSetorComercialInicial(null);
		form.setIdSetorComercialFinal(null);
		form.setCodSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setQuadraInicial(null);
		form.setQuadraFinal(null);
		form.setRotaInicial(null);
		form.setRotaSequenciaInicial(null);
		form.setRotaFinal(null);
		form.setRotaSequenciaFinal(null);
		

		form.setBloquearQuadra(String.valueOf(ConstantesSistema.SIM));
		form.setBloquearRota(String.valueOf(ConstantesSistema.SIM));

		this.getSessao(httpServletRequest).removeAttribute("setorComercialInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FB0004] Limpar Setor Comercial
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	public ActionForward apagarSetorComercialFinal(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");

		form.setIdSetorComercialFinal(null);
		form.setCodSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setQuadraFinal(null);
		form.setRotaFinal(null);
		form.setRotaSequenciaFinal(null);

		
		this.getSessao(httpServletRequest).removeAttribute("setorComercialFinalInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0005] Verificar existência do setor Comercial
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	public ActionForward pesquisarQuadraInicial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;

		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		String idSetorComercialInicial = form.getIdSetorComercialInicial();
		String idQuadra = form.getQuadraInicial();
		
		
		this.getSessao(httpServletRequest).removeAttribute("quadraInicialInexistenteException");
		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		Quadra quadra = this.getFachada().obterQuadraSetorComercial(Integer.parseInt(idSetorComercialInicial), Integer.parseInt(idQuadra));
		if(quadra != null){
			form.setQuadraFinal(quadra.getNumeroQuadra()+"");
			this.getSessao(httpServletRequest).removeAttribute("quadraInicialInexistenteException");
			this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
			
			//Desbloquear rota para edição
			form.setBloquearRota(String.valueOf(ConstantesSistema.NAO));
			
		}
		else{
			form.setQuadraInicial(null);
			form.setQuadraFinal(null);
			this.getSessao(httpServletRequest).setAttribute("quadraInicialInexistenteException","sim");
			
			form.setBloquearRota(String.valueOf(ConstantesSistema.SIM));
		}
		
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [FE0005] Verificar existência do setor Comercial
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	public ActionForward pesquisarQuadraFinal(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		
		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		String idSetorComercialFinal = form.getIdSetorComercialFinal();
		String idQuadra = form.getQuadraFinal();
		
		

		this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		
		Quadra quadra = this.getFachada().obterQuadraSetorComercial(Integer.parseInt(idSetorComercialFinal), Integer.parseInt(idQuadra));
		if(quadra != null){
			form.setQuadraFinal(quadra.getNumeroQuadra()+"");
			this.getSessao(httpServletRequest).removeAttribute("quadraFinalInexistenteException");
		}
		else{
			form.setQuadraFinal(null);
			this.getSessao(httpServletRequest).setAttribute("quadraFinalInexistenteException","sim");
		}
		
		// [FB0016] Verificar Permissão Informar Encerramento Automático
		// ---------------------------------------------------------------------------
		// Caso o usuário logado possua permissão especial para informar
		// encerramento automático,
		// habilitar o campo "Informar encerramento automático"
		verificarPermissaoEspecialEncerramentoAutomatico(httpServletRequest,
				usuarioLogado, form);
		
		//Tela inicial
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOrdemServicoConexaoEsgoto");	
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexão Esgoto
	 * [IT0009] Selecionar Percentual de Esgoto
	 * 
	 * @author Hugo Azevedo
	 * @date 06/08/2013
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward selecionarPercentualEsgoto(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FiltrarOrdemServicoConexaoEsgotoActionForm form = (FiltrarOrdemServicoConexaoEsgotoActionForm) actionForm;
		String idPerfilLigacao = form.getIdPerfilLigacao();
		
		if(idPerfilLigacao != null && !idPerfilLigacao.equals("-1")){
			
			FiltroPerfilLigacao filtroPerfil = new FiltroPerfilLigacao();
			filtroPerfil.adicionarParametro(new ParametroSimples(FiltroPerfilLigacao.ID, idPerfilLigacao));
			Collection colecaoPerfilLigacao = (Collection)this.getFachada().pesquisar(filtroPerfil, LigacaoEsgotoPerfil.class.getName());
			LigacaoEsgotoPerfil perfil = (LigacaoEsgotoPerfil)Util.retonarObjetoDeColecao(colecaoPerfilLigacao);
			
			try {
				httpServletResponse.getWriter().println(Util.formatarMoedaReal(perfil.getPercentualEsgotoConsumidaColetada()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//Retornando via ajax
		return null;
		
		
	}
	
}
