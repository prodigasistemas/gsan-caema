package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.CapaLoteAtualizacaoCadastral;
import gcom.cadastro.FiltroCapaLoteAtualizacaoCadastral;
import gcom.cadastro.ParametroTabelaAtualizacaoCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroEnderecoReferencia;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.integracao.webservice.neurotech.fachada.ConsultaWebServiceGATEWAY;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.ConsultarReceitaFederal;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
 * [UC 1361] - Efetuar Digitação Dados para Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 25/07/2012
 *
 */
public class EfetuarDigitacaoDadosAtualizacaoCadastralImovelAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = 
			mapping.findForward("exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovel2");
		
		EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form = 
				(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm) formulario;
		
		HttpSession sessao = request.getSession();
		
		Integer idParametroAtualizacaoCadastral = (Integer) sessao.getAttribute("idParametroAtualizacaoCadastral");
		Integer idCapaLoteAtualizacaoCadastral = (Integer) sessao.getAttribute("idCapaLoteAtualizacaoCadastral");
		
		if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			this.validarMatriculaImovel(form);
		}
		
		if(form.getLote() != null && !form.getLote().equals("") 
				&& form.getSubLote() != null && !form.getSubLote().equals("")){
			this.validarInscricaoImovel(form);
		}
		
		if(form.getCadastrador() != null && !form.getCadastrador().equals("")){
			this.validarCadastrador(form);
		}
		
		if(form.getFoneTipoClienteUsuario() != null && !form.getFoneTipoClienteUsuario().equals("")){
			this.validarFoneTipoClienteUsuario(form);
		}
		
		if(form.getPavimentoRua() != null && !form.getPavimentoRua().equals("")){
			this.validarPavimentoRua(form);
		}
		
		if(form.getPavimentoCalcada() != null && !form.getPavimentoCalcada().equals("")){
			this.validarPavimentoCalcada(form);
		}
		
		if(form.getFonteAbastecimento() != null && !form.getFonteAbastecimento().equals("")){
			this.validarFonteAbastecimento(form);
		}
		
		if(form.getSituacaoAgua() != null && !form.getSituacaoAgua().equals("")){
			this.validarLigacaoAguaSituacao(form);
		}
		
		if(form.getSituacaoEsgoto() != null && !form.getSituacaoEsgoto().equals("")){
			this.validarLigacaoEsgotoSituacao(form);
		}
		
		if(form.getIndicadorHidrometro() != null && form.getIndicadorHidrometro().equals(String.valueOf(ConstantesSistema.SIM))){
			if(form.getNumeroHidrometro() != null && !form.getNumeroHidrometro().equals("")){
				this.validarNumeroHidrometro(form);
			}
			if(form.getLocalInstalacao() != null && !form.getLocalInstalacao().equals("")){
				this.validarLocalInstalacaoHidrometro(form);
			}
			if(form.getProtecao() != null && !form.getProtecao().equals("")){
				this.validarTipoProtecaoHidrometro(form);
			}
			if(form.getOcorrenciaCadastro() != null && !form.getOcorrenciaCadastro().equals("")){
				this.validarOcorrenciaCadastro(form);
			}
		}
		
		if(form.getReferencia() != null && !form.getReferencia().equals("")){
			this.validarEnderecoReferencia(form);
		}
		
		if(form.getPerfil() != null && !form.getPerfil().equals("")){
			this.validarImovelPerfil(form);
		}
		
		if(form.getSubCategoria1() != null && !form.getSubCategoria1().equals("")){
			this.validarSubCategoria(form.getSubCategoria1());
		}
		
		if(form.getSubCategoria2() != null && !form.getSubCategoria2().equals("")){
			this.validarSubCategoria(form.getSubCategoria2());
		}
		
		if(form.getSubCategoria3() != null && !form.getSubCategoria3().equals("")){
			this.validarSubCategoria(form.getSubCategoria3());
		}
		
		if(form.getSubCategoria4() != null && !form.getSubCategoria4().equals("")){
			this.validarSubCategoria(form.getSubCategoria4());
		}
		
		if(form.getSubCategoria5() != null && !form.getSubCategoria5().equals("")){
			this.validarSubCategoria(form.getSubCategoria5());
		}
		
		if(form.getSubCategoria6() != null && !form.getSubCategoria6().equals("")){
			this.validarSubCategoria(form.getSubCategoria6());
		}
		
		if ( form.getDataAtualizacao() != null && !form.getDataAtualizacao().equals("") ) {
			this.validarDataAtualizacao(form);
		}
		
		
		
	
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();

		this.validarInformacoesInconsistentes(form);
		
		//Valida o cliente usuario, caso necessario monta tela de confirmacao
		retorno = this.validarObrigatoriedadeClienteUsuario(form, sistemaParametro, request, mapping);

		if ( request.getParameter("confirmado") != null  && request.getParameter("tipoRelatorio") != null && 
				request.getParameter("tipoRelatorio").toString().equals("1")) {
			form.setConfimacaoClienteUsuarioDesconhecido("1");
		}
		
		//Exibe tela de confirmacao montada no passo anterior
		if(retorno.getName().equalsIgnoreCase("telaConfirmacao") && (form.getConfimacaoClienteUsuarioDesconhecido() == null ||
				form.getConfimacaoClienteUsuarioDesconhecido().equals("")) ){
			return retorno;
		}
		
		retorno = mapping.findForward("exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovel2");
		
		
		/**
		 * [FS0023] - Verificar existência de imóvel com o mesmo endereço
		 * @author Arthur Carvalho
		 * @since 07/02/2013
		 */
		
		String confirmacaoEndereco = null;
		if ( request.getParameter("tipoRelatorio") != null  ) {
			confirmacaoEndereco = request.getParameter("tipoRelatorio");
		}
		
		if ( confirmacaoEndereco == null || (confirmacaoEndereco != null && !confirmacaoEndereco.equals("2") && !confirmacaoEndereco.equals("3")) ){
			
			Integer matricula = null;
			if ( form.getMatriculaImovel() != null && !form.getMatriculaImovel().trim().equals("") ) {
				matricula = Integer.valueOf(form.getMatriculaImovel());
			}
			
			Collection<Integer> imoveis = this.getFachada().pesquisarEnderecoExistente(Integer.parseInt(form.getLogradouro()), Integer.parseInt(form.getBairro()), 
					Integer.parseInt(form.getCep()), form.getNumero(), matricula);
			
			
			
			if ( !Util.isVazioOrNulo(imoveis) && (confirmacaoEndereco == null || (confirmacaoEndereco != null && !confirmacaoEndereco.equals("2")) )) {

				String matriculas = "";
				
				for(Integer matriculaImov : imoveis){
					matriculas += matriculaImov.toString() + ", ";
				}
				
				matriculas = matriculas.substring(0, matriculas.length() - 2);
				
				request.setAttribute("tipoRelatorio", "2");
				return montarPaginaConfirmacao("atencao.endereco_cadastrado", request, mapping, matriculas);
			}	
		}
		/**
		 * [FS0023] - Verificar existência de imóvel com o mesmo endereço
		 * Fim
		 */
		
		/**
		 * [FS0024] - Verificar validação CPF/CNPJ
		 * @author Arthur Carvalho
		 * Data: 07/02/2013
		 */
		ConsultarReceitaFederal consultaRF = null;
		
		String confirmadoCpfCnpj = null;
		if ( request.getParameter("tipoRelatorio") != null  ) {
			confirmadoCpfCnpj = request.getParameter("tipoRelatorio");
		}
		
		Short indicadorConsultaDocumentoReceita = this.getSistemaParametro().getIndicadorConsultaDocumentoReceita();
		if(form.getDocumentoClienteUsuario() == null ){
			indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
		}
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		//Verifica se o indicador de consulta na receita esta ativo
		//verifica se a tela de confirmacao ja foi chamada.
		if (indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString()) && 
				((confirmadoCpfCnpj != null && !confirmadoCpfCnpj.equals("3") ) || confirmadoCpfCnpj == null )){
			
			ConsultaWebServiceGATEWAY consultaWebService = new ConsultaWebServiceGATEWAY();

			try {
				//CPF
				if (form.getDocumentoClienteUsuario().length() == 11){
					
					//Verifica se os dados da receita ja estao cadastrado no GSAN para esse cpf informado
					ConsultarReceitaFederal consultaGSAN = Fachada.getInstancia().pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(form.getDocumentoClienteUsuario());
					if(consultaGSAN == null){
						//Caso nao exista os dados cadastrados no GSAN, faz a consulta na receita
						consultaRF = consultaWebService.consultarPessoaFisica(form.getDocumentoClienteUsuario(), usuario, null, null, null);
						System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CPF: "+form.getDocumentoClienteUsuario());
						sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
						sessao.setAttribute("consultaGSAN", false);
						form.setNomeClienteReceitaFederal(consultaRF.getNomePessoaFisica());
					}else{
						System.out.println("CONSULTA GSAN INSERIR CLIENTE CPF: "+form.getDocumentoClienteUsuario());
						sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
						sessao.setAttribute("consultaGSAN", true);
						form.setNomeClienteReceitaFederal(consultaGSAN.getNomePessoaFisica());
					}
					
				}else if (form.getDocumentoClienteUsuario().length() == 14){
				//CNPJ
					//Verifica se os dados da receita ja estao cadastrado no GSAN para esse cnpj informado
					ConsultarReceitaFederal consultaGSAN = Fachada.getInstancia().pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(form.getDocumentoClienteUsuario());
					if(consultaGSAN == null){
						//Caso nao exista os dados cadastrados no GSAN, faz a consulta na receita
						consultaRF = consultaWebService.consultaPessoaJuridica(form.getDocumentoClienteUsuario(), usuario, null, null, null);
						System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CNPJ: "+form.getDocumentoClienteUsuario());
						sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
						sessao.setAttribute("consultaGSAN", false);
						form.setNomeClienteReceitaFederal(consultaRF.getRazaoSocial());
					}else{
						System.out.println("CONSULTA GSAN INSERIR CLIENTE CNPJ: "+form.getDocumentoClienteUsuario());
						sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
						sessao.setAttribute("consultaGSAN", true);
						form.setNomeClienteReceitaFederal(consultaGSAN.getRazaoSocial());
					}
				}
			} catch (Exception e) {
				//Erro na consulta da receita federal.
				if(consultaRF != null && consultaRF.getMensagemRetornoReceitaFederal() != null){
					throw new ActionServletException("atencao.falha_webservice_gateway", consultaRF.getMensagemRetornoReceitaFederal().getCodigoMensagemRetorno() + " - " 
							+ consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno() + ". Os dados do cliente não podem ser atualizados");
				}else{
					throw new ActionServletException("atencao.falha_webservice_gateway", "0199" + " - " + "Falha na consulta"+ ". Os dados do cliente não podem ser atualizados");
				}
			}
			
//			consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
//			if(consultaRF.getMensagemRetornoReceitaFederal() != null && consultaRF.getMensagemRetornoReceitaFederal().getId().intValue() != MensagemRetornoReceitaFederal.ID_CONSULTA_REALIZADA_COM_SUCESSO){
//				throw new ActionServletException("atencao.falha_webservice_gateway", null, consultaRF.getMensagemRetornoReceitaFederal().getCodigoMensagemRetorno() + " - " 
//						+ consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno() + ". Os dados do cliente não podem ser atualizados");
//			}
		}
		
		
		if(indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
			consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
			if ( consultaRF != null ) {
				if(consultaRF.getCpfCliente() != null && !consultaRF.getCpfCliente().equals("")){
					form.setNomeClienteReceitaFederal(consultaRF.getNomePessoaFisica());
					if(consultaRF.getNomePessoaFisica() == null){
						form.setNomeClienteReceitaFederal( consultaRF.getNomeCliente());
					}
				}else if(consultaRF.getCnpjCliente() != null && !consultaRF.getCnpjCliente().equals("")){
					form.setNomeClienteReceitaFederal(consultaRF.getRazaoSocial());
					if(consultaRF.getRazaoSocial() == null){
						form.setNomeClienteReceitaFederal(consultaRF.getNomeCliente());
					}
				}
			}
		} else {
			form.setNomeClienteReceitaFederal(form.getNomeClienteUsuario());
		}
		
		//verifica que nao vai entrar novamente no reload.
		if (  (confirmadoCpfCnpj != null && !confirmadoCpfCnpj.equals("3") ) || confirmadoCpfCnpj == null ){
			
			//Verifica se o nome na receita é igual ao nome informado, caso seja diferente monta uma tela de confirmação
			if ( form.getNomeClienteReceitaFederal() != null && !form.getNomeClienteReceitaFederal().equals("") && !form.getNomeClienteReceitaFederal().equals(form.getNomeClienteUsuario()) && 
					indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString()) ) {
				
				request.setAttribute("nomeBotao1", "Aceitar");
				request.setAttribute("nomeBotao3", "Rejeitar");
				request.setAttribute("tipoRelatorio", "3");
//				request.setAttribute("caminhoActionConclusao", "exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do");
				
				return montarPaginaConfirmacao("atencao.confirmacao_nome_receita_federal",
						request, mapping, 
							form.getNomeClienteUsuario(), form.getNomeClienteReceitaFederal());
				
			}
		} else if ( request.getParameter("tipoRelatorio") != null && request.getParameter("tipoRelatorio").equals("3") &&
				request.getParameter("confirmado") != null && request.getParameter("confirmado").equals("ok") ) {
			
			form.setNomeClienteUsuario(form.getNomeClienteReceitaFederal());
			consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
			Date dataAtual = new Date();
			consultaRF.setDataAcesso(dataAtual);
			consultaRF.setAcaoUsuario(Short.parseShort("1"));
			
			this.getFachada().inserirOuAtualizar(consultaRF);
			
		} else if ( request.getParameter("tipoRelatorio") != null && request.getParameter("tipoRelatorio").equals("3") &&
				request.getParameter("confirmado") != null && request.getParameter("confirmado").equals("nao") ) {
			
			consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
			consultaRF.setAcaoUsuario(Short.parseShort("2"));
			Date dataAtual = new Date();
			consultaRF.setDataAcesso(dataAtual);
			
			this.getFachada().inserirOuAtualizar(consultaRF);

			return retorno;
		}
		
		
		
		/**
		 * FIM
		 * [FS0024] - Verificar validação CPF/CNPJ
		 * @author Arthur Carvalho
		 * Data: 07/02/2013 
		 */
		
		
		boolean clienteUsuarioDesconhecido = false;
		if(form.getConfimacaoClienteUsuarioDesconhecido() != null && form.getConfimacaoClienteUsuarioDesconhecido().equalsIgnoreCase("1")){
			clienteUsuarioDesconhecido = true;
		}
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = null;
		imovelAtualizacaoCadastral = this.inserirDadosImovelAtualizacaoCadastral(
				form, idParametroAtualizacaoCadastral, idCapaLoteAtualizacaoCadastral);
		
		ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = null;
		clienteAtualizacaoCadastral = this.inserirDadosClienteAtualizacaoCadastral(
				form, idParametroAtualizacaoCadastral, clienteUsuarioDesconhecido, sistemaParametro);
		
		ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = null;
		clienteFoneAtualizacaoCadastral = this.inserirDadosClienteFoneAtualizacaoCadastral(form);
		
		Collection<ImovelSubcategoriaAtualizacaoCadastral> colecaoImovelSubcategoria = null;
		colecaoImovelSubcategoria = this.inserirDadosImovelSubCategoriaAtualizacaoCadastral(form);
		
		HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstalacaoHistorico = null;
		if(form.getIndicadorHidrometro() != null && form.getIndicadorHidrometro().equals(String.valueOf(ConstantesSistema.SIM))){
			hidrometroInstalacaoHistorico = this.inserirDadosInstalacaoHidrometro(form);
		}
		
		if(imovelAtualizacaoCadastral.getId() == null 
				|| imovelAtualizacaoCadastral.getId().intValue() == 0){	
			this.atualizarCapaLoteAtualizacaoCadastral(form, idCapaLoteAtualizacaoCadastral);
		}
		
		this.getFachada().inserirDadosAtualizacaoCadastral(imovelAtualizacaoCadastral, 
			clienteAtualizacaoCadastral, 
			clienteFoneAtualizacaoCadastral, 
			colecaoImovelSubcategoria, 
			hidrometroInstalacaoHistorico);
		
		if( (form.getMatriculaImovel() == null || form.getMatriculaImovel().equals("")) &&
				imovelAtualizacaoCadastral.getImovel() != null ){
			
			String matriculaImovelFormatada = "";
			
			Integer matriculaImovel = imovelAtualizacaoCadastral.getImovel();
			
			matriculaImovelFormatada = "" + matriculaImovel;
			
			int quantidadeCaracteresImovel = matriculaImovel.toString()
						.length();
				matriculaImovelFormatada = matriculaImovelFormatada.substring(0, quantidadeCaracteresImovel - 1)
						+ "."
						+ matriculaImovelFormatada.substring(
								quantidadeCaracteresImovel - 1,
								quantidadeCaracteresImovel);
			
			form.setMatriculaNovoImovel(matriculaImovelFormatada);
		}
		
		this.limparFormulario(form);
		this.limparCamposSessao(sessao);
		
		if(form.getQuantidadeDocumentosIncluidos() != null && !form.getQuantidadeDocumentosIncluidos().equals("") 
				&& form.getQuantidadeDocumentos() != null && !form.getQuantidadeDocumentos().equals("")){
			if(Integer.parseInt(form.getQuantidadeDocumentosIncluidos()) > Integer.parseInt(form.getQuantidadeDocumentos())){
				request.setAttribute("documentosExcedidos", true);
			}else{
				request.removeAttribute("documentosExcedidos");
			}
		}
		
		return retorno;
	}
	
	
	
	/**
	 * Validar matrícula do imóvel
	 * 
	 * @author Davi Menezes
	 * @date 25/07/2012
	 * 
	 * @return void
	 */
	private void validarMatriculaImovel(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		
		Imovel imovel = this.getFachada().pesquisarImovel(Integer.parseInt(form.getMatriculaImovel()));
		
		FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral =  null;		
		if(imovel == null){
			filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
			filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroImovelAtualizacaoCadastral.ID_IMOVEL, form.getMatriculaImovel()));
			filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroImovelAtualizacaoCadastral.INDICADOR_IMOVEL_NOVO, ConstantesSistema.SIM));
			
			Collection<?> colImovelAtualizacaoCadastral = this.getFachada().pesquisar(
					filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
			
			if(!Util.isVazioOrNulo(colImovelAtualizacaoCadastral)){
				throw new ActionServletException("atencao.imovel_inexistente");
			}
		}
		
		filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroImovelAtualizacaoCadastral.ID_IMOVEL, form.getMatriculaImovel()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroImovelAtualizacaoCadastral.INDICADOR_DADOS_RETORNO, ConstantesSistema.SIM));
		
		Collection<?> colImovelAtualizacaoCadastral = this.getFachada().pesquisar(
				filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
		
		if(!Util.isVazioOrNulo(colImovelAtualizacaoCadastral)){
			throw new ActionServletException("atencao.matricula_imovel_existente_ambiente_virtual");
		}
	}
	
	/**
	 * Validar Inscrição do imóvel
	 * 
	 * @author Davi Menezes
	 * @date 25/07/2012
	 * 
	 * @return void
	 */
	private void validarInscricaoImovel(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		
		try{
			Integer.parseInt(form.getLote());
			Integer.parseInt(form.getSubLote());
		}catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.lote_sublote_numerico");
		}
		
		FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroImovelAtualizacaoCadastral.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroImovelAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroImovelAtualizacaoCadastral.NUMERO_QUADRA, form.getNumeroQuadra()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroImovelAtualizacaoCadastral.LOTE, form.getLote()));
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
				FiltroImovelAtualizacaoCadastral.SUB_LOTE, form.getSubLote()));
		
		Collection<?> colImovelAtualizacaoCadastral = this.getFachada().pesquisar(
				filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
		
		if(!Util.isVazioOrNulo(colImovelAtualizacaoCadastral)){
			ImovelAtualizacaoCadastral imovel = (ImovelAtualizacaoCadastral) Util.retonarObjetoDeColecao(colImovelAtualizacaoCadastral);
			
			if(!imovel.getImovel().toString().equals(form.getMatriculaImovel())){
				throw new ActionServletException("atencao.inscricao_imovel_existente_ambiente_virtual", imovel.getInscricaoFormatada());
			}
		}
		
		if ( form.getMatriculaImovel() == null || form.getMatriculaImovel().equals("") ) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID, form.getIdLocalidade()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO, form.getCodigoSetorComercial()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO, form.getNumeroQuadra()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOTE, form.getLote()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SUBLOTE, form.getSubLote()));
			
			
			Collection<?> colImovel = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
			
			if(!Util.isVazioOrNulo(colImovel)){
				throw new ActionServletException("atencao.imovel_inscricao_cadastrada_gsan");
			}
		}
		
	}
	
	/**
	 * Validar Cadastrador
	 * 
	 * @author Anderson Cabral
	 * @date 04/05/2013
	 * 
	 * @return void
	 */		
	private void validarCadastrador(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		String cpfCadastrador = form.getCadastrador();
		
		Leiturista leiturista = this.pesquisarCadastrador(cpfCadastrador);

		if(leiturista == null || leiturista.getId() == null || leiturista.getId().equals("")){
			throw new ActionServletException("atencao.cadastrador_inexistente");
		}
	}
	
	/**
	 * 
	 * @param form
	 */
	private void validarDataAtualizacao(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		
		Date dataAtua = Util.converteStringParaDate(form.getDataAtualizacao());

		if(dataAtua.compareTo(new Date()) > 0 ){
			throw new ActionServletException("atencao.data_final_menor_data_atual", null , "Data de Atualização");
		}
	}
	
	/**
	 * Pesquisa o 'Cadastrador' (Leiturista) pelo CPF
	 * 
	 * @author Anderson Cabral
	 * @date 04/05/2013
	 * 
	 * @return void
	 */	
	private Leiturista pesquisarCadastrador(String cpfCadastrador){
		Leiturista leiturista = null;
		
		if(cpfCadastrador != null && !cpfCadastrador.equals("")){
			leiturista = this.getFachada().pesquisarLeituristaPorCPF(cpfCadastrador);
		}
		
		return leiturista;
	}

	
	/**
	 * Validar Fone Tipo Cliente Usuário
	 * 
	 * @author Davi Menezes
	 * @date 25/07/2012
	 * 
	 * @return void
	 */
	private void validarFoneTipoClienteUsuario(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
		filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.ID, form.getFoneTipoClienteUsuario()));
		
		Collection<?> colFoneTipo = this.getFachada().pesquisar(filtroFoneTipo, FoneTipo.class.getName());
		if(Util.isVazioOrNulo(colFoneTipo)){
			throw new ActionServletException("atencao.fone_tipo_inexistente");
		}
	}
	
	/**
	 * Validar Pavimento Rua
	 * 
	 * @author Davi Menezes
	 * @date 25/07/2012
	 * 
	 * @return void
	 */
	private void validarPavimentoRua(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form) {
		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID, form.getPavimentoRua()));
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colPavimentoRua = this.getFachada().pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());
		if(Util.isVazioOrNulo(colPavimentoRua)){
			throw new ActionServletException("atencao.pavimento_rua_inexistente");
		}
	}
	
	/**
	 * Validar Pavimento Calçada
	 * 
	 * @author Davi Menezes
	 * @date 25/07/2012
	 * 
	 * @return void
	 */
	private void validarPavimentoCalcada(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.ID, form.getPavimentoCalcada()));
		filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.INDICADOR_USO, ConstantesSistema.SIM));
	
		Collection<?> colPavimentoCalcada = this.getFachada().pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
		if(Util.isVazioOrNulo(colPavimentoCalcada)){
			throw new ActionServletException("atencao.pavimento_calcada_inexistente");
		}
	}
	
	/**
	 * Validar Fonte de Abastecimento
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarFonteAbastecimento(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form) {
		FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
		filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(
				FiltroFonteAbastecimento.ID, form.getFonteAbastecimento()));
		filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(
				FiltroFonteAbastecimento.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colFonteAbastecimento = this.getFachada().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
		if(Util.isVazioOrNulo(colFonteAbastecimento)){
			throw new ActionServletException("atencao.fonte_abastecimento_inexistente");
		}
	}
	
	/**
	 * Validar Situação Ligação de Água
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarLigacaoAguaSituacao(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, form.getSituacaoAgua()));
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colLigacaoAguaSituacao = this.getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if(Util.isVazioOrNulo(colLigacaoAguaSituacao)){
			throw new ActionServletException("atencao.situacao_ligacao_agua_inexistente");
		}
		
		
		//Caso seja imovel novo
		if ( form.getMatriculaImovel() == null || form.getMatriculaImovel().equals("") ) {
			//Situações permitidas - ligado, factivel e potencial
			if ( (!(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO)) || 
					form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL)) ||
					form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))) )) {
				
				throw new ActionServletException("atencao.nao_permite_ligacao_agua_situacao_imovel_novo");	
			}
		}
	}
	
	/**
	 * Validar Situação Ligação de Esgoto
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarLigacaoEsgotoSituacao(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, form.getSituacaoEsgoto()));
		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colLigacaoEsgotoSituacao = 
				this.getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
		if(Util.isVazioOrNulo(colLigacaoEsgotoSituacao)){
			throw new ActionServletException("atencao.situacao_ligacao_esgoto_inexistente");
		}
		
		//Caso seja imovel novo
		if ( form.getMatriculaImovel() == null || form.getMatriculaImovel().equals("") ) {
			//Situações permitidas - ligado, factivel e potencial
			if ( !(form.getSituacaoEsgoto().equals(String.valueOf(LigacaoAguaSituacao.LIGADO)) || 
					form.getSituacaoEsgoto().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL)) ||
					form.getSituacaoEsgoto().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL)) )) {
				
				throw new ActionServletException("atencao.nao_permite_ligacao_esgoto_situacao_imovel_novo");	
			}
		}
	}
	
	/**
	 * Validar Número do Hidrômetro
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarNumeroHidrometro(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		String numero = form.getNumeroHidrometro(); 
		Hidrometro hidrometro = this.getFachada().pesquisarHidrometroPeloNumero(numero);

		
		if(hidrometro == null){
			throw new ActionServletException("atencao.hidrometro_inexistente");
		} else if( hidrometro != null && hidrometro.getHidrometroSituacao() != null &&
				hidrometro.getHidrometroSituacao().getId().intValue() != HidrometroSituacao.DISPONIVEL.intValue()){
			
			if ( form.getMatriculaImovel() != null && !form.getMatriculaImovel().isEmpty() ) {
				Imovel imovel = this.getFachada().pesquisarImovel(Integer.parseInt(form.getMatriculaImovel()));
				
				if ( imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null && 
						imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() != null && 
						imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() != null &&
						!imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero().equals(hidrometro.getNumero())) {
					
					//Caso o hidrômetro informado esteja com a situação diferente de DISPONÍVEL
					throw new ActionServletException("atencao.hidrometro_situacao_nao_pode_instalar",null,hidrometro.getHidrometroSituacao().getDescricao());
				} else if (imovel.getLigacaoAgua() == null || imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() == null || 
						imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() == null || 
						imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() == null ) {
					//Caso o hidrômetro informado esteja com a situação diferente de DISPONÍVEL
					throw new ActionServletException("atencao.hidrometro_situacao_nao_pode_instalar",null,hidrometro.getHidrometroSituacao().getDescricao());
				}
				
			} else {
				//Caso o hidrômetro informado esteja com a situação diferente de DISPONÍVEL
				throw new ActionServletException("atencao.hidrometro_situacao_nao_pode_instalar",null,hidrometro.getHidrometroSituacao().getDescricao());
			}
		}
		this.validarInconsistenciaHidrometro(form);
	}
	
	/**
	 * Validar Local Instalação do Hidrômetro
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarLocalInstalacaoHidrometro(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();
		filtroHidrometroLocalInstalacao.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalInstalacao.ID, form.getLocalInstalacao()));
		filtroHidrometroLocalInstalacao.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalInstalacao.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colLocalInstalacaoHidrometro = this.getFachada().pesquisar(filtroHidrometroLocalInstalacao, HidrometroLocalInstalacao.class.getName());
		if(Util.isVazioOrNulo(colLocalInstalacaoHidrometro)){
			throw new ActionServletException("atencao.pesquisa.hidrometro_local_instalacao.inexistente");
		}
	}
	
	/**
	 * Validar Tipo de Proteção do Hidrômetro
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarTipoProtecaoHidrometro(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.ID, form.getProtecao()));
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colHidrometroProtecao = this.getFachada().pesquisar(filtroHidrometroProtecao, HidrometroProtecao.class.getName());
		if(Util.isVazioOrNulo(colHidrometroProtecao)){
			throw new ActionServletException("atencao.hidrometro_protecao_inexistente");
		}
	}
	
	/**
	 * Validar Ocorrência de Cadastro
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarOcorrenciaCadastro(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
		filtroCadastroOcorrencia.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.ID, form.getOcorrenciaCadastro()));
		filtroCadastroOcorrencia.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colCadastroOcorrencia = this.getFachada().pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
		if(Util.isVazioOrNulo(colCadastroOcorrencia)){
			throw new ActionServletException("atencao.ocorrencia_cadastro_inexistente");
		}
	}
	
	/**
	 * Validar Endereço de Referência
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarEnderecoReferencia(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();
		filtroEnderecoReferencia.adicionarParametro(new ParametroSimples(FiltroEnderecoReferencia.ID, form.getReferencia()));
		filtroEnderecoReferencia.adicionarParametro(new ParametroSimples(FiltroEnderecoReferencia.INDICADORUSO, ConstantesSistema.SIM));
		
		Collection<?> colEnderecoReferencia = this.getFachada().pesquisar(filtroEnderecoReferencia, EnderecoReferencia.class.getName());
		if(Util.isVazioOrNulo(colEnderecoReferencia)){
			throw new ActionServletException("atencao.pesquisa.endereco_referencia_inexistente");
		}
	}
	
	/**
	 * Validar Imovel Perfil
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarImovelPerfil(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, form.getPerfil()));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colImovelPerfil = this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		if(Util.isVazioOrNulo(colImovelPerfil)){
			throw new ActionServletException("atencao.perfil_imovel_inexistente");
		}
		
		if ( (form.getMatriculaImovel() == null || form.getMatriculaImovel().equals("")) &&
				form.getPerfil().equals(String.valueOf(ImovelPerfil.TARIFA_SOCIAL))) {
			throw new ActionServletException("atencao.nao_permite_inserir_imovel_tarifa_social");
		}
	}
	
	/**
	 * Validar Subcategoria
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void validarSubCategoria(String subcategoria){
		FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
		filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, subcategoria));
		
		Collection<?> colSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
		
		if(Util.isVazioOrNulo(colSubcategoria)){
			throw new ActionServletException("atencao.subcategoria_inexistente");
		}
	}
	
	/**
	 * Validar Número do Hidrômetro
	 * 
	 * @author Davi Menezes
	 * @date 09/08/2012
	 * 
	 * @return void
	 */
	private void validarInconsistenciaHidrometro(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form) {
		
		String codigoHidrometroCapacidade = form.getNumeroHidrometro().substring(0, 1);
		String codigoHidrometroMarca = form.getNumeroHidrometro().substring(3, 4);
		
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.CODIGO_HIDROMETRO_CAPACIDADE, codigoHidrometroCapacidade));
		
		Collection<?> colHidrometroCapacidade = this.getFachada().pesquisar(
				filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
		if(Util.isVazioOrNulo(colHidrometroCapacidade)){
			throw new ActionServletException("atencao.hidrometro_inconsistente");
		}
		
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
				FiltroHidrometroMarca.CODIGO, codigoHidrometroMarca));
		
		Collection<?> colHidrometroMarca = this.getFachada().pesquisar(
				filtroHidrometroMarca, HidrometroMarca.class.getName());
		if(Util.isVazioOrNulo(colHidrometroMarca)){
			throw new ActionServletException("atencao.hidrometro_inconsistente");
		}
		
		
		
		
	}
	
	/**
	 * Validar Informações Inconsistentes
	 * 
	 * @author Davi Menezes
	 * @date 25/07/2012
	 * 
	 * @return void
	 */
	private void validarInformacoesInconsistentes(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		//Validar Situação da Ligação de Água x Hidrômetro
		if(form.getSituacaoAgua() != null && !form.getSituacaoAgua().equals("") 
				&& form.getIndicadorHidrometro() != null && !form.getIndicadorHidrometro().equals("")){
			
			if(form.getIndicadorHidrometro().equals(ConstantesSistema.SIM.toString())){
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.SUPRIMIDO))) {
					
					String situacao = "";
					if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))){
						situacao = "POTENCIAL";
					}else if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL))){
						situacao = "FACTÍVEL";
					}else{
						situacao = "SUPRIMIDO";
					}
					
					throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_instalacao_hidrometro",
							null, situacao);
				}
			}
		}
		
		//Validar Fonte Abastecimento x Situação da Ligação de Água x Ocorrência Cadastro
		if(form.getFonteAbastecimento() != null && !form.getFonteAbastecimento().equals("") 
				&& form.getSituacaoAgua() != null && !form.getSituacaoAgua().equals("") 
				&& form.getOcorrenciaCadastro() != null && !form.getOcorrenciaCadastro().equals("")){
			
			String fonteAbastecimento = "";
			if(form.getFonteAbastecimento().equals(String.valueOf(FonteAbastecimento.COMPESA))){
				fonteAbastecimento = "COMPESA";
				
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.CORTADO))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.SUPRIMIDO))){
					
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.SEM_OCORRENCIAS))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PERMITIU_ACESSO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PODE_RESPONDER))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_VISITADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_FECHADO)) 
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.ANIMAL_BRAVO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_LOCALIZADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}else if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO))){
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}
			}else if(form.getFonteAbastecimento().equals(String.valueOf(FonteAbastecimento.POCO))){
				fonteAbastecimento = "POÇO";
				
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.CORTADO))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.SUPRIMIDO))){
					
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}else if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO))){
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.SEM_OCORRENCIAS))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PERMITIU_ACESSO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PODE_RESPONDER))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_VISITADO)) 
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_FECHADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.ANIMAL_BRAVO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_LOCALIZADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}
			}else if(form.getFonteAbastecimento().equals(String.valueOf(FonteAbastecimento.VIZINHO))){
				fonteAbastecimento = "VIZINHO";
				
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL)) 
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.CORTADO))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.SUPRIMIDO))){
					
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}else if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO))){
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.SEM_OCORRENCIAS))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PERMITIU_ACESSO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PODE_RESPONDER))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_VISITADO)) 
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_FECHADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.ANIMAL_BRAVO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_LOCALIZADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}
			}else if(form.getFonteAbastecimento().equals(String.valueOf(FonteAbastecimento.CACIMBA))){
				fonteAbastecimento = "CACIMBA";
				
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL)) 
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.CORTADO))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.SUPRIMIDO))){
					
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}else if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO))){
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.SEM_OCORRENCIAS))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PERMITIU_ACESSO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PODE_RESPONDER))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_VISITADO)) 
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_FECHADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.ANIMAL_BRAVO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_LOCALIZADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}
			}else if(form.getFonteAbastecimento().equals(String.valueOf(FonteAbastecimento.CHAFARIZ))){
				fonteAbastecimento = "CHAFARIZ";
				
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL)) 
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.CORTADO))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.SUPRIMIDO))){
					
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}else if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO))){
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.SEM_OCORRENCIAS))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PERMITIU_ACESSO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PODE_RESPONDER))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_VISITADO)) 
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_FECHADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.ANIMAL_BRAVO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_LOCALIZADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}
			}else if(form.getFonteAbastecimento().equals(String.valueOf(FonteAbastecimento.CARRO_PIPA))){
				fonteAbastecimento = "CARRO PIPA";
				
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL)) 
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.CORTADO))
						|| form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.SUPRIMIDO))){
					
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}else if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO))){
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.SEM_OCORRENCIAS))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PERMITIU_ACESSO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PODE_RESPONDER))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_VISITADO)) 
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_FECHADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.ANIMAL_BRAVO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_LOCALIZADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}
			}else if(form.getFonteAbastecimento().equals(String.valueOf(FonteAbastecimento.SEM_ABASTECIMENTO))){
				fonteAbastecimento = "SEM ABASTECIMENTO";
				
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.LIGADO))){
					if(form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.SEM_OCORRENCIAS))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PERMITIU_ACESSO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.CLIENTE_NAO_PODE_RESPONDER))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_VISITADO)) 
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_FECHADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.ANIMAL_BRAVO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_NAO_LOCALIZADO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DEMOLIDO))
							|| form.getOcorrenciaCadastro().equals(String.valueOf(CadastroOcorrencia.IMOVEL_DESOCUPADO))){
						throw new ActionServletException("atencao.situacao_ligacao_agua_invalida_fonte_abastecimento",
								null, fonteAbastecimento);
					}
				}
			}
		}
	}
	
	/**
	 * Validar Obrigatoriedade do Cliente Usuário
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private ActionForward validarObrigatoriedadeClienteUsuario(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, 
			SistemaParametro sistemaParametro, HttpServletRequest request, ActionMapping mapping) {
		
		ActionForward retorno = mapping.findForward("telaSucesso");
		
		boolean pessoaJuridica = false;
		
		if(form.getDocumentoClienteUsuario() != null && 
			!form.getDocumentoClienteUsuario().equals("") &&
			form.getDocumentoClienteUsuario().length() == 14 ) {
			
			pessoaJuridica = true;
		}
		
		if(form.getNomeClienteUsuario() == null || form.getNomeClienteUsuario().equals("") || 
			(!pessoaJuridica && (form.getSexoClienteUsuario() == null || form.getSexoClienteUsuario().equals(""))) ){
			
			if(form.getNomeClienteUsuario() != null && !form.getNomeClienteUsuario().equals("") && !pessoaJuridica &&
					(form.getSexoClienteUsuario() == null || form.getSexoClienteUsuario().equals("")) ){
				throw new ActionServletException("atencao.cliente_usuario_obrigatorio", null, "Sexo ");
			}
			
			if(sistemaParametro.getClienteUsuarioDesconhecido() == null){
				
				if(form.getNomeClienteUsuario() == null || form.getNomeClienteUsuario().equals("")){
					throw new ActionServletException("atencao.cliente_usuario_obrigatorio", null, "Nome ");
				}else if(!pessoaJuridica && (form.getSexoClienteUsuario() == null || form.getSexoClienteUsuario().equals("")) ){
					throw new ActionServletException("atencao.cliente_usuario_obrigatorio", null, "Sexo ");
				}
			}
			
			if((!form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL)) && 
				!form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL))) ||
				(!form.getSituacaoEsgoto().equals(String.valueOf(LigacaoEsgotoSituacao.POTENCIAL)) && 
				!form.getSituacaoEsgoto().equals(String.valueOf(LigacaoEsgotoSituacao.FACTIVEL)))){
				
				if(form.getNomeClienteUsuario() == null || form.getNomeClienteUsuario().equals("")){
					throw new ActionServletException("atencao.cliente_usuario_obrigatorio", null, "Nome ");
				}else if(!pessoaJuridica && (form.getSexoClienteUsuario() == null || form.getSexoClienteUsuario().equals(""))){
					throw new ActionServletException("atencao.cliente_usuario_obrigatorio", null, "Sexo ");
				}
			}
			
			if(sistemaParametro.getClienteUsuarioDesconhecido() != null){
				if(form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.POTENCIAL)) || 
					form.getSituacaoAgua().equals(String.valueOf(LigacaoAguaSituacao.FACTIVEL)) ||
					form.getSituacaoEsgoto().equals(String.valueOf(LigacaoEsgotoSituacao.POTENCIAL)) || 
					form.getSituacaoEsgoto().equals(String.valueOf(LigacaoEsgotoSituacao.FACTIVEL))){
					
					request.setAttribute("tipoRelatorio", "1");
					return montarPaginaConfirmacao("atencao.imovel_associar_cliente_desconhecido",
							request, mapping); 
				}
			}
		}
		
		if(form.getDocumentoClienteUsuario() != null && !form.getDocumentoClienteUsuario().equals("")){
			if(form.getDocumentoClienteUsuario().length() != 11 && form.getDocumentoClienteUsuario().length() != 14){
				throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
			}else if(form.getDocumentoClienteUsuario().length() == 11){
				if(!Util.validacaoCPF(form.getDocumentoClienteUsuario())){
					throw new ActionServletException("atencao.cpf_invalido");
				}
			}else{
				if(!Util.validacaoCNPJ(form.getDocumentoClienteUsuario())){
					throw new ActionServletException("atencao.cnpj.invalido", null, form.getDocumentoClienteUsuario());
				}
			}
			if(form.getNomeClienteUsuario() == null || form.getNomeClienteUsuario().equals("")){
				throw new ActionServletException("atencao.nome_cliente_nao_informado", null, "Usuário");
			}else if(!pessoaJuridica && (form.getSexoClienteUsuario() == null || form.getSexoClienteUsuario().equals(""))){
				throw new ActionServletException("atencao.sexo_cliente_nao_informado", null, "Usuário");
			}
		}
		if(form.getNomeClienteUsuario() != null && !form.getNomeClienteUsuario().equals("")){
			if(!pessoaJuridica && (form.getSexoClienteUsuario() == null || form.getSexoClienteUsuario().equals(""))){
				throw new ActionServletException("atencao.sexo_cliente_nao_foi_informado", null, "Usuário");
			}
		}
		if(form.getSexoClienteUsuario() != null && !form.getSexoClienteUsuario().equals("")){
			if(form.getNomeClienteUsuario() == null || form.getNomeClienteUsuario().equals("")){
				throw new ActionServletException("atencao.nome_cliente_nao_informado", null, "Usuário");
			}
		}
		
		return retorno;
	}
	
	/**
	 * Inserir Dados do Imóvel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return Imovel Atualização Cadastral
	 */
	private ImovelAtualizacaoCadastral inserirDadosImovelAtualizacaoCadastral(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
				Integer idParametroAtualizacaoCadastral, Integer idCapaLoteAtualizacaoCadastral) {
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
		
		ParametroTabelaAtualizacaoCadastro parametroAtualizacaoCadastral = new ParametroTabelaAtualizacaoCadastro();
		parametroAtualizacaoCadastral.setId(idParametroAtualizacaoCadastral);
		
		CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral = new CapaLoteAtualizacaoCadastral();
		capaLoteAtualizacaoCadastral.setId(idCapaLoteAtualizacaoCadastral);
		
		Imovel imovel = new Imovel(0);
		if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			imovel = new Imovel(Integer.parseInt(form.getMatriculaImovel()));
			
			//Caso nao seja imovel novo
			imovelAtualizacaoCadastral.setIndicadorImovelNovo(ConstantesSistema.NAO);
		} else {
			//Caso seja imovel novo.
			//obtem o sequence do imovel
			Integer matricula = this.getFachada().obterNextValImovel();
			imovel.setId(matricula);
			imovelAtualizacaoCadastral.setIndicadorImovelNovo(ConstantesSistema.SIM);
			
			//Area Construida Faixa de 0 a 50 metros.
			imovelAtualizacaoCadastral.setIdAreaConstruidaFaixa(1);
		}
		
		//ID Imovel Atualizacao Cadastral
		if(form.getIdImovelAC() != null && !form.getIdImovelAC().equals("")){
			imovelAtualizacaoCadastral.setId(Integer.parseInt(form.getIdImovelAC()));
		}
		
		imovelAtualizacaoCadastral.setImovel(imovel.getId());

		
		Leiturista leiturista = pesquisarCadastrador(form.getCadastrador());
		Integer idEmpresa = leiturista.getEmpresa().getId();
		
		imovelAtualizacaoCadastral.setLogin(form.getCadastrador());
		
		if(form.getDataAtualizacao() != null && !form.getDataAtualizacao().equals("")){
			imovelAtualizacaoCadastral.setDataRecebimentoMovimento(Util.converteStringParaDate(form.getDataAtualizacao()));
			imovelAtualizacaoCadastral.setDataVisita(Util.converteStringParaDate(form.getDataAtualizacao()));
		}
		
		imovelAtualizacaoCadastral.setUltimaAlteracao(new Date());
		imovelAtualizacaoCadastral.setIdLocalidade(Integer.parseInt(form.getIdLocalidade()));
		imovelAtualizacaoCadastral.setCodigoSetorComercial(Integer.parseInt(form.getCodigoSetorComercial()));
		imovelAtualizacaoCadastral.setNumeroQuadra(Integer.parseInt(form.getNumeroQuadra()));
		imovelAtualizacaoCadastral.setLote(Short.parseShort(form.getLote()));
		imovelAtualizacaoCadastral.setSubLote(Short.parseShort(form.getSubLote()));
		imovelAtualizacaoCadastral.setIdImovelPerfil(Integer.parseInt(form.getPerfil()));
		
		if(form.getAnalisarTarifaSocial() != null && !form.getAnalisarTarifaSocial().equals("")){
			imovelAtualizacaoCadastral.setIndicadorAlertaTarifaSocial(Short.parseShort(form.getAnalisarTarifaSocial()));
		}else{
			imovelAtualizacaoCadastral.setIndicadorAlertaTarifaSocial(ConstantesSistema.NAO);
		}
		
		imovelAtualizacaoCadastral.setIdLogradouro(Long.valueOf(form.getLogradouro()));
		imovelAtualizacaoCadastral.setIdEnderecoReferencia(Integer.parseInt(form.getReferencia()));
		imovelAtualizacaoCadastral.setNumeroImovel(form.getNumero());
		imovelAtualizacaoCadastral.setComplementoEndereco(form.getComplemento());
		imovelAtualizacaoCadastral.setIdBairro(Integer.parseInt(form.getBairro()));
		imovelAtualizacaoCadastral.setIdMunicipio(Integer.parseInt(form.getIdMunicipio()));
		
		Integer idCep = Integer.parseInt(form.getCep());
		
		FiltroCep filtroCep = new FiltroCep();
		filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CEPID, idCep));
		Collection colecaoCeps = this.getFachada().pesquisar(filtroCep, Cep.class.getName());
		Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCeps);
		imovelAtualizacaoCadastral.setCodigoCep(cep.getCodigo());
		
		if(form.getNumeroMoradores() != null && !form.getNumeroMoradores().equals("")){
			imovelAtualizacaoCadastral.setNumeroMorador(Short.parseShort(form.getNumeroMoradores()));
		}
		
		if(form.getMedidorCelpe() != null && !form.getMedidorCelpe().equals("")){
			imovelAtualizacaoCadastral.setNumeroMedidirEnergia(form.getMedidorCelpe());
		}
		
		if(form.getOcorrenciaCadastro() != null && !form.getOcorrenciaCadastro().equals("")){
			imovelAtualizacaoCadastral.setIdCadastroOcorrencia(Integer.parseInt(form.getOcorrenciaCadastro()));
		}
		
		imovelAtualizacaoCadastral.setIdPavimentoRua(Integer.parseInt(form.getPavimentoRua()));
		imovelAtualizacaoCadastral.setIdPavimentoCalcada(Integer.parseInt(form.getPavimentoCalcada()));
		imovelAtualizacaoCadastral.setIdFonteAbastecimento(Integer.parseInt(form.getFonteAbastecimento()));
		imovelAtualizacaoCadastral.setIdLigacaoAguaSituacao(Integer.parseInt(form.getSituacaoAgua()));
		imovelAtualizacaoCadastral.setIdLigacaoEsgotoSituacao(Integer.parseInt(form.getSituacaoEsgoto()));
		imovelAtualizacaoCadastral.setDescricaoObservacao(form.getObservacao());
		imovelAtualizacaoCadastral.setIndicadorAtualizado(ConstantesSistema.NAO);
		imovelAtualizacaoCadastral.setIndicadorPendente(ConstantesSistema.NAO);
		imovelAtualizacaoCadastral.setIndicadorDadosRetorno(ConstantesSistema.SIM);
		imovelAtualizacaoCadastral.setIndicadorExclusao(ConstantesSistema.NAO);
		imovelAtualizacaoCadastral.setIndicadorPoco(ConstantesSistema.NAO);
		imovelAtualizacaoCadastral.setIndicadorLogradouroNovo(ConstantesSistema.NAO);
		imovelAtualizacaoCadastral.setIndicadorBairroNovo(ConstantesSistema.NAO);
		imovelAtualizacaoCadastral.setIdSituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.COLETADO);
		imovelAtualizacaoCadastral.setParametroTabelaAtualizacaoCadastro(parametroAtualizacaoCadastral);
		imovelAtualizacaoCadastral.setCapaLoteAtualizacaoCadastral(capaLoteAtualizacaoCadastral);
		imovelAtualizacaoCadastral.setIdEmpresa(idEmpresa);
		imovelAtualizacaoCadastral.setIndicadorResetorizado(ConstantesSistema.NAO);
		
		return imovelAtualizacaoCadastral;
	}
	
	/**
	 * Inserir Dados do Cliente Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return Cliente Atualização Cadastral
	 */
	private ClienteAtualizacaoCadastral inserirDadosClienteAtualizacaoCadastral(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, 
			Integer idParametroAtualizacaoCadastral, 
			boolean clienteUsuarioDesconhecido, SistemaParametro sistemaParametro) {
		
		ParametroTabelaAtualizacaoCadastro parametroAtualizacaoCadastral = new ParametroTabelaAtualizacaoCadastro();
		parametroAtualizacaoCadastral.setId(idParametroAtualizacaoCadastral);
		
		ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = new ClienteAtualizacaoCadastral();
		
		//ID Cliente AC
		if(form.getIdClienteUsuarioAC() != null && !form.getIdClienteUsuarioAC().equals("")){
			clienteAtualizacaoCadastral.setId(Integer.parseInt(form.getIdClienteUsuarioAC()));
		}
		
		clienteAtualizacaoCadastral.setParametroTabelaAtualizacaoCadastro(parametroAtualizacaoCadastral);
		clienteAtualizacaoCadastral.setIdCliente(0);
		clienteAtualizacaoCadastral.setRg(form.getRgClienteUsuario());
		clienteAtualizacaoCadastral.setDsUFSiglaOrgaoExpedidorRg(form.getUfClienteUsuario());
		clienteAtualizacaoCadastral.setIndicadorExclusao(ConstantesSistema.NAO);
		clienteAtualizacaoCadastral.setIdClienteRelacaoTipo(Integer.valueOf((ClienteRelacaoTipo.USUARIO)));
		clienteAtualizacaoCadastral.setUltimaAlteracao(new Date());
		
		if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			clienteAtualizacaoCadastral.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
		}else{
			clienteAtualizacaoCadastral.setIdImovel(0);
		}
		
		String nome = null;
		String documento = null;
		Integer clienteTipo = null;
		Integer sexo = null;

		
		if(clienteUsuarioDesconhecido){
			
			Cliente clienteUsuario = sistemaParametro.getClienteUsuarioDesconhecido();
			Cliente cliente = this.getFachada().pesquisarClienteDigitado(clienteUsuario.getId());
			
			
			nome = cliente.getNome();
			clienteTipo = cliente.getClienteTipo().getId();
			
			if(cliente.getCpf() != null){
				documento = cliente.getCpf();
				clienteTipo = ClienteTipo.PARTICULARES;
				sexo = cliente.getPessoaSexo().getId();
				
			}else if(cliente.getCnpj() != null){
				documento = cliente.getCnpj();
				clienteTipo = ClienteTipo.NAO_PARTICULARES;
			} 
			
			
			
			clienteAtualizacaoCadastral.setNomeCliente(nome);
			clienteAtualizacaoCadastral.setCpfCnpj(documento);
			clienteAtualizacaoCadastral.setIdClienteTipo(clienteTipo);
			clienteAtualizacaoCadastral.setIdPessoaSexo(sexo);
				

			
		}else{
			nome = form.getNomeClienteUsuario().toUpperCase();
			documento = form.getDocumentoClienteUsuario();
			
			if(documento.length() == 11){
				sexo =  Integer.parseInt(form.getSexoClienteUsuario());
				clienteTipo = ClienteTipo.PARTICULARES;
			}else{
				clienteTipo = ClienteTipo.NAO_PARTICULARES;
			}
		}
		
		clienteAtualizacaoCadastral.setNomeCliente(nome);
		clienteAtualizacaoCadastral.setCpfCnpj(documento);
		clienteAtualizacaoCadastral.setIdClienteTipo(clienteTipo);
		clienteAtualizacaoCadastral.setIdPessoaSexo(sexo);
		
		return clienteAtualizacaoCadastral;
	}
	
	/**
	 * Inserir Dados Cliente Fone Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private ClienteFoneAtualizacaoCadastral inserirDadosClienteFoneAtualizacaoCadastral(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form) {
		
		if(form.getDddFoneClienteUsuario() != null && !form.getDddFoneClienteUsuario().equals("") 
				&& form.getFoneClienteUsuario() != null && !form.getFoneClienteUsuario().equals("") 
				&& form.getFoneTipoClienteUsuario() != null && !form.getFoneTipoClienteUsuario().equals("")){
			
			ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
			
			if(form.getIdClienteFoneAC() != null && !form.getIdClienteFoneAC().equals("")){
				clienteFoneAtualizacaoCadastral.setId(Integer.parseInt(form.getIdClienteFoneAC()));
			}
			
			clienteFoneAtualizacaoCadastral.setDdd(form.getDddFoneClienteUsuario());
			clienteFoneAtualizacaoCadastral.setTelefone(form.getFoneClienteUsuario());
			clienteFoneAtualizacaoCadastral.setIdFoneTipo(Integer.parseInt(form.getFoneTipoClienteUsuario()));
			clienteFoneAtualizacaoCadastral.setIndicadorFonePadrao(ConstantesSistema.SIM);
			clienteFoneAtualizacaoCadastral.setUltimaAlteracao(new Date());
			
			return clienteFoneAtualizacaoCadastral;
		}else{
			return null;
		}
	}
	
	/**
	 * Inserir Dados Imovel Subcategoria Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private Collection<ImovelSubcategoriaAtualizacaoCadastral> inserirDadosImovelSubCategoriaAtualizacaoCadastral(
			EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form) {
		
		Collection<ImovelSubcategoriaAtualizacaoCadastral> colecaoImovelSubcategoria = new ArrayList<ImovelSubcategoriaAtualizacaoCadastral>();
		
		ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = null;
		
		Collection<?> colSubcategoria = null;
		
		FiltroSubCategoria filtroSubCategoria = null;
		
		Subcategoria subcategoria = null;
		
		if(form.getSubCategoria1() != null && !form.getSubCategoria1().equals("")){
			imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			
			filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, form.getSubCategoria1()));
			//filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
			
			colSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(!Util.isVazioOrNulo(colSubcategoria)){
				subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colSubcategoria);
				
				if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
				}else{
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(0);
				}
				
				//ID Imovel SubCategoria 1
				if(form.getIdImovelSubCategoria1() != null && !form.getIdImovelSubCategoria1().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setId(Integer.parseInt(form.getIdImovelSubCategoria1()));
				}
				
				imovelSubcategoriaAtualizacaoCadastral.setIdCategoria(subcategoria.getCategoria().getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoCategoria(subcategoria.getCategoria().getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setIdSubcategoria(subcategoria.getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoSubcategoria(subcategoria.getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setQuantidadeEconomias(Short.parseShort(form.getNumeroEconomias1()));
				imovelSubcategoriaAtualizacaoCadastral.setUltimaAlteracao(new Date());
				
				colecaoImovelSubcategoria.add(imovelSubcategoriaAtualizacaoCadastral);
			}
		}
		
		if(form.getSubCategoria2() != null && !form.getSubCategoria2().equals("")){
			imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			
			filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, form.getSubCategoria2()));
			//filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
			
			colSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(!Util.isVazioOrNulo(colSubcategoria)){
				subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colSubcategoria);
				
				if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
				}else{
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(0);
				}
				
				//ID Imovel SubCategoria 2
				if(form.getIdImovelSubCategoria2() != null && !form.getIdImovelSubCategoria2().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setId(Integer.parseInt(form.getIdImovelSubCategoria2()));
				}
				
				imovelSubcategoriaAtualizacaoCadastral.setIdCategoria(subcategoria.getCategoria().getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoCategoria(subcategoria.getCategoria().getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setIdSubcategoria(subcategoria.getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoSubcategoria(subcategoria.getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setQuantidadeEconomias(Short.parseShort(form.getNumeroEconomias2()));
				imovelSubcategoriaAtualizacaoCadastral.setUltimaAlteracao(new Date());
				
				colecaoImovelSubcategoria.add(imovelSubcategoriaAtualizacaoCadastral);
			}
		}
		
		if(form.getSubCategoria3() != null && !form.getSubCategoria3().equals("")){
			imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			
			filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, form.getSubCategoria3()));
			//filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
			
			colSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(!Util.isVazioOrNulo(colSubcategoria)){
				subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colSubcategoria);
				
				if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
				}else{
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(0);
				}
				
				//ID Imovel SubCategoria 3
				if(form.getIdImovelSubCategoria3() != null && !form.getIdImovelSubCategoria3().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setId(Integer.parseInt(form.getIdImovelSubCategoria3()));
				}
				
				imovelSubcategoriaAtualizacaoCadastral.setIdCategoria(subcategoria.getCategoria().getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoCategoria(subcategoria.getCategoria().getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setIdSubcategoria(subcategoria.getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoSubcategoria(subcategoria.getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setQuantidadeEconomias(Short.parseShort(form.getNumeroEconomias3()));
				imovelSubcategoriaAtualizacaoCadastral.setUltimaAlteracao(new Date());
				
				colecaoImovelSubcategoria.add(imovelSubcategoriaAtualizacaoCadastral);
			}
		}
		
		if(form.getSubCategoria4() != null && !form.getSubCategoria4().equals("")){
			imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			
			filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, form.getSubCategoria4()));
			//filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
			
			colSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(!Util.isVazioOrNulo(colSubcategoria)){
				subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colSubcategoria);
				
				if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
				}else{
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(0);
				}
				
				//ID Imovel SubCategoria 4
				if(form.getIdImovelSubCategoria4() != null && !form.getIdImovelSubCategoria4().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setId(Integer.parseInt(form.getIdImovelSubCategoria4()));
				}
				
				imovelSubcategoriaAtualizacaoCadastral.setIdCategoria(subcategoria.getCategoria().getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoCategoria(subcategoria.getCategoria().getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setIdSubcategoria(subcategoria.getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoSubcategoria(subcategoria.getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setQuantidadeEconomias(Short.parseShort(form.getNumeroEconomias4()));
				imovelSubcategoriaAtualizacaoCadastral.setUltimaAlteracao(new Date());
				
				colecaoImovelSubcategoria.add(imovelSubcategoriaAtualizacaoCadastral);
			}
		}
		
		if(form.getSubCategoria5() != null && !form.getSubCategoria5().equals("")){
			imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			
			filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, form.getSubCategoria5()));
			//filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
			
			colSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(!Util.isVazioOrNulo(colSubcategoria)){
				subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colSubcategoria);
				
				if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
				}else{
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(0);
				}
				
				//ID Imovel SubCategoria 5
				if(form.getIdImovelSubCategoria5() != null && !form.getIdImovelSubCategoria5().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setId(Integer.parseInt(form.getIdImovelSubCategoria5()));
				}
				
				imovelSubcategoriaAtualizacaoCadastral.setIdCategoria(subcategoria.getCategoria().getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoCategoria(subcategoria.getCategoria().getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setIdSubcategoria(subcategoria.getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoSubcategoria(subcategoria.getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setQuantidadeEconomias(Short.parseShort(form.getNumeroEconomias5()));
				imovelSubcategoriaAtualizacaoCadastral.setUltimaAlteracao(new Date());
				
				colecaoImovelSubcategoria.add(imovelSubcategoriaAtualizacaoCadastral);
			}
		}
		
		if(form.getSubCategoria6() != null && !form.getSubCategoria6().equals("")){
			imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			
			filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, form.getSubCategoria6()));
			//filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
			
			colSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(!Util.isVazioOrNulo(colSubcategoria)){
				subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colSubcategoria);
				
				if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
				}else{
					imovelSubcategoriaAtualizacaoCadastral.setIdImovel(0);
				}
				
				//ID Imovel SubCategoria 6
				if(form.getIdImovelSubCategoria6() != null && !form.getIdImovelSubCategoria6().equals("")){
					imovelSubcategoriaAtualizacaoCadastral.setId(Integer.parseInt(form.getIdImovelSubCategoria6()));
				}
				
				imovelSubcategoriaAtualizacaoCadastral.setIdCategoria(subcategoria.getCategoria().getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoCategoria(subcategoria.getCategoria().getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setIdSubcategoria(subcategoria.getId());
				imovelSubcategoriaAtualizacaoCadastral.setDescricaoSubcategoria(subcategoria.getDescricao());
				imovelSubcategoriaAtualizacaoCadastral.setQuantidadeEconomias(Short.parseShort(form.getNumeroEconomias6()));
				imovelSubcategoriaAtualizacaoCadastral.setUltimaAlteracao(new Date());
				
				colecaoImovelSubcategoria.add(imovelSubcategoriaAtualizacaoCadastral);
			}
		}
		
		return colecaoImovelSubcategoria;
	}
	
	/**
	 * Inserir Dados da Instalação de Hidrômetro
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private HidrometroInstalacaoHistoricoAtualizacaoCadastral inserirDadosInstalacaoHidrometro(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer.parseInt(form.getLocalInstalacao()));
		
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer.parseInt(form.getProtecao()));
		
		HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstalacaoHistoricoAtualizacaoCadastral = 
				new HidrometroInstalacaoHistoricoAtualizacaoCadastral();

		Date dataInstalacao = new Date();
		
		//ID Hidrometro Instalacao Historico AC
		//Data Instalacao
		if(form.getIdHidrometroInstalacaoHistoricoAC() != null && !form.getIdHidrometroInstalacaoHistoricoAC().equals("")){
			hidrometroInstalacaoHistoricoAtualizacaoCadastral.setId(Integer.parseInt(form.getIdHidrometroInstalacaoHistoricoAC()));
		
			FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral filtroHidrometroInstalacaoHistoricoAC 
																		= new FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral();
			filtroHidrometroInstalacaoHistoricoAC.adicionarParametro( new ParametroSimples(FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral.ID, form.getIdHidrometroInstalacaoHistoricoAC()));
			
			Collection colecaoHidrometroInstalacaoHistoricoAC = Fachada.getInstancia().pesquisar(filtroHidrometroInstalacaoHistoricoAC, HidrometroInstalacaoHistoricoAtualizacaoCadastral.class.getName());
			
			if ( colecaoHidrometroInstalacaoHistoricoAC != null && !colecaoHidrometroInstalacaoHistoricoAC.isEmpty() ) {
				HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstalacaoHistoricoAC = (HidrometroInstalacaoHistoricoAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistoricoAC);
				dataInstalacao = hidrometroInstalacaoHistoricoAC.getDataInstalacaoHidrometro();
			}
		
		}else{		
			Hidrometro hidrometro = this.getFachada().pesquisarHidrometroPeloNumero(form.getNumeroHidrometro());
			if ( hidrometro != null && hidrometro.getId() != null && form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("") ) {
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
				filtroHidrometroInstalacaoHistorico.adicionarParametro( new ParametroSimples(FiltroHidrometroInstalacaoHistorico.HIDROMETRO_ID, hidrometro.getId()));
				filtroHidrometroInstalacaoHistorico.adicionarParametro( new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID, form.getMatriculaImovel()));
				
				Collection colecaoHidrometroInstalacaoHistorico = Fachada.getInstancia().pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());
				if ( colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty() ) {
					HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);
					dataInstalacao = hidrometroInstalacaoHistorico.getDataInstalacao();
				}
			}
		}
		
		hidrometroInstalacaoHistoricoAtualizacaoCadastral.setNumeroHidrometro(form.getNumeroHidrometro());
		hidrometroInstalacaoHistoricoAtualizacaoCadastral.setDataInstalacaoHidrometro(dataInstalacao);
		hidrometroInstalacaoHistoricoAtualizacaoCadastral.setMedicaoTipo(medicaoTipo);
		hidrometroInstalacaoHistoricoAtualizacaoCadastral.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		hidrometroInstalacaoHistoricoAtualizacaoCadastral.setHidrometroProtecao(hidrometroProtecao);
		
		if(form.getLeitura() != null && !form.getLeitura().equals("")){
			hidrometroInstalacaoHistoricoAtualizacaoCadastral.setNumeroInstalacaoHidrometro(Integer.parseInt(form.getLeitura()));
		}
		
		if(form.getDataAtualizacao() != null){
			hidrometroInstalacaoHistoricoAtualizacaoCadastral.setUltimaAlteracao(Util.converteStringParaDate(form.getDataAtualizacao()));
		}else{
			hidrometroInstalacaoHistoricoAtualizacaoCadastral.setUltimaAlteracao(new Date());
		}
		
		if(form.getCavalete() != null && !form.getCavalete().equals("")){
			hidrometroInstalacaoHistoricoAtualizacaoCadastral.setIndicadorCavalete(new Short(form.getCavalete()));
		}else{
			hidrometroInstalacaoHistoricoAtualizacaoCadastral.setIndicadorCavalete(ConstantesSistema.NAO);
		}

		
		return hidrometroInstalacaoHistoricoAtualizacaoCadastral;
	}
	
	/**
	 * Atualizar Capa Lote Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 26/07/2012
	 * 
	 * @return void
	 */
	private void atualizarCapaLoteAtualizacaoCadastral(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, 
		Integer idCapaLoteAtualizacaoCadastral) {
		
		FiltroCapaLoteAtualizacaoCadastral filtroCapaLote = new FiltroCapaLoteAtualizacaoCadastral();
		filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID, idCapaLoteAtualizacaoCadastral));
		
		Collection<?> colCapaLote = this.getFachada().pesquisar(filtroCapaLote, CapaLoteAtualizacaoCadastral.class.getName());
		
		if(!Util.isVazioOrNulo(colCapaLote)){
			CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral = (CapaLoteAtualizacaoCadastral)
					Util.retonarObjetoDeColecao(colCapaLote);
			
			Integer numeroDocumentosDigitados = 0;
			if(capaLoteAtualizacaoCadastral.getQuantidadeDocumentosDigitados() != null){
				numeroDocumentosDigitados = capaLoteAtualizacaoCadastral.getQuantidadeDocumentosDigitados()+1;
			}else{
				numeroDocumentosDigitados = 1;
			}
			
			capaLoteAtualizacaoCadastral.setQuantidadeDocumentosDigitados(numeroDocumentosDigitados);
			
			this.getFachada().atualizar(capaLoteAtualizacaoCadastral);
			
			form.setQuantidadeDocumentosIncluidos(numeroDocumentosDigitados.toString());
		}
	}
	
	/**
	 * Limpa os dados no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
	private void limparFormulario(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		
		//Dados do cliente proprietário
		form.setDocumentoClienteProprietario(null);
		form.setNomeClienteProprietario(null);
		form.setSexoClienteProprietario(null);
		form.setRgClienteProprietario(null);
		form.setUfClienteProprietario(null);
		form.setDddFoneClienteProprietario(null);
		form.setFoneClienteProprietario(null);
		form.setFoneTipoClienteProprietario(null);
		form.setDescricaoFoneTipoClienteProprietario(null);
		form.setEnderecoClienteProprietario(null);
		form.setComplementoClienteProprietario(null);
		form.setBairroClienteProprietario(null);
		form.setMunicipioClienteProprietario(null);
		form.setCepClienteProprietario(null);
		form.setNomeClienteReceitaFederal(null);
		
		//Dados do cliente usuário
		form.setDocumentoClienteUsuario(null);
		form.setNomeClienteUsuario(null);
		form.setSexoClienteUsuario(null);
		form.setRgClienteUsuario(null);
		form.setUfClienteUsuario(null);
		
		form.setDddFoneClienteUsuario(null);
		form.setFoneClienteUsuario(null);
		form.setFoneTipoClienteUsuario(null);
		form.setDescricaoFoneTipoClienteUsuario(null);
		
		//Dados do imóvel
		
		form.setMatriculaImovel(null);
		form.setCadastrador(null);
		form.setNomeCadastrador(null);
		form.setDataAtualizacao(null);
		form.setLote(null);
		form.setSubLote(null);
		form.setPerfil(null);
		form.setDescricaoPerfil(null);
		form.setAnalisarTarifaSocial(null);
		form.setLogradouro(null);
		form.setReferencia(null);
		form.setDescricaoReferencia(null);
		form.setNumero(null);
		form.setComplemento(null);
		form.setCep(null);

		form.setSubCategoria1(null);
		form.setSubCategoria2(null);
		form.setSubCategoria3(null);
		form.setSubCategoria4(null);
		form.setSubCategoria5(null);
		form.setSubCategoria6(null);
		
		
		form.setNumeroEconomias1(null);
		form.setNumeroEconomias2(null);
		form.setNumeroEconomias3(null);
		form.setNumeroEconomias4(null);
		form.setNumeroEconomias5(null);
		form.setNumeroEconomias6(null);
		
		form.setNumeroMoradores(null);
		form.setMedidorCelpe(null);
		form.setPavimentoRua(null);
		form.setDescricaoPavimentoRua(null);
		form.setPavimentoCalcada(null);
		form.setDescricaoPavimentoCalcada(null);
		
		form.setFonteAbastecimento(null);
		form.setDescricaoFonteAbastecimento(null);
		
		//Dados da ligação
		form.setSituacaoAgua(null);
		form.setDescricaoSituacaoAgua(null);
		
		form.setSituacaoEsgoto(null);
		form.setDescricaoSituacaoEsgoto(null);
		
		form.setIndicadorHidrometro(null);
		form.setNumeroHidrometro(null);
		form.setAnoFabricacao(null);
		form.setMarca(null);
		form.setCapacidade(null);
		form.setLocalInstalacao(null);
		form.setDescricaoLocalInstalacao(null);
		
		form.setProtecao(null);
		form.setDescricaoProtecao(null);
		
		form.setCavalete(null);
		form.setOcorrenciaCadastro(null);
		form.setDescricaoOcorrenciaCadastro(null);
		
		form.setLeitura(null);
		form.setCriterioTarifaSocial(null);
		form.setObservacao(null);
		form.setPerfil("5");
		form.setDescricaoPerfil("NORMAL");
	}
	
	/**
	 * Limpar Campos da Sessão
	 * 
	 * @author Davi Menezes
	 * @date 03/08/2012
	 * 
	 * @param sessao
	 * 
	 * @return void
	 */
	private void limparCamposSessao(HttpSession sessao) {
		sessao.removeAttribute("cadastradorInexistente");
		sessao.removeAttribute("foneTipoClienteUsuarioInexistente");
		sessao.removeAttribute("referenciaInexistente");
		sessao.removeAttribute("perfilImovelInexistente");
		sessao.removeAttribute("bloquearCep");
		sessao.removeAttribute("colecaoCep");
		sessao.removeAttribute("pavimentoRuaInexistente");
		sessao.removeAttribute("pavimentoCalcadaInexistente");
		sessao.removeAttribute("fonteAbastecimentoInexistente");
		sessao.removeAttribute("situacaoAguaInexistente");
		sessao.removeAttribute("situacaoEsgotoInexistente");
		sessao.removeAttribute("localInstalacaoInexistente");
		sessao.removeAttribute("tipoProtecaoInexistente");
		sessao.removeAttribute("ocorrenciaCadastroInexistente");
		sessao.removeAttribute("colecaoLogradourosRemovidos");
	}
}