package gcom.gui.portal;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 
 * <p>
 * <b>[RM2923]</b> Inserir Solicitação de Servicos - Loja Virtual da Compesa
 * </p>
 * <p>
 * <b>[UC1189]</b> Inserir Registro de Atendimento Loja Virtual</b>
 * </p>
 * <p>
 * Gerar Registro de Atendimento
 * </p>
 * 
 * @author Magno Gouveia
 * @date 19/05/2011
 * 
 */
public class InserirSolicitacaoServicosPortalVazamentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirSolicitacaoServicosPortalActionForm form = (InserirSolicitacaoServicosPortalActionForm) actionForm;

		sessao.removeAttribute("exception");
		sessao.removeAttribute("RASolicitadaComSucesso");
		sessao.removeAttribute("mensagemRA");
		sessao.removeAttribute("dataPrevistaAtendimentoRA");
		sessao.removeAttribute("RAJaSolicitada");
		
		
		//Matricula ou LocalOcorrencia
		if (!Util.verificarIdNaoVazio(form.getMatricula()) && !Util.verificarIdNaoVazio(form.getLocalOcorrencia())){
			sessao.setAttribute("exception", "Selecione o imóvel ou local de ocorrência.");
			return retorno;
		}
		
		// 2.2.1 Nome Solicitante
		if (!Util.verificarNaoVazio(form.getNomeSolicitante())) {
			sessao.setAttribute("exception", "O nome do solicitante é obrigatório!");
			return retorno;
		}
		
		// 2.2.2 Telefone
		if(!Util.verificarNaoVazio(form.getTelefoneContato())){
			sessao.setAttribute("exception", "O telefone de contato é obrigatório!");
			return retorno;
		}
		
		//Ponto Referencia
		if(!Util.verificarNaoVazio(form.getPontoReferencia())){
			sessao.setAttribute("exception", "O ponto de referência é obrigatório!");
			return retorno;
		}
		
		// 2.2.4 Solicitacao Tipo
		if(!Util.verificarIdNaoVazio(form.getSolicitacaoTipo())){
			sessao.setAttribute("exception", "O tipo de solicitação é obrigatório!");
			return retorno;
		}
		
		// 2.2.5 Especificacao
		Date dataPrevista = null;
		if (Util.verificarIdNaoVazio(form.getEspecificacao())) {
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = 
				this.getFachada().definirDataPrevistaUnidadeDestinoEspecificacao(
					new Date(),
					Integer.parseInt(form.getEspecificacao()));

			dataPrevista = 
				Util.formatarDataFinal(definirDataPrevistaUnidadeDestinoEspecificacaoHelper.getDataPrevista());
		} else {
			sessao.setAttribute("exception", "A especificação é obrigatória!");
			return retorno;
		}
		
		if(form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_AGUA.toString()) 
				|| form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_ESGOTO.toString())){
			
			//CPF/CNPJ
			if (!Util.verificarIdNaoVazio(form.getCpfCnpj())){
				sessao.setAttribute("exception", "Informe um CPF/CNPJ.");
				return retorno;
			}else if(Util.isInteger(form.getCpfCnpj())){
				sessao.setAttribute("exception", "Informe um CPF/CNPJ Válido.");
				return retorno;
			}else{
				if(form.getCpfCnpj().length() == 11 && !Util.validacaoCPF(form.getCpfCnpj())){
					sessao.setAttribute("exception", "CPF informado está Inválido.");
					return retorno;
				}else if(form.getCpfCnpj().length() == 14 && !Util.validacaoCNPJ(form.getCpfCnpj())){
					sessao.setAttribute("exception", "CNPJ informado está Inválido.");
					return retorno;
				}else if(form.getCpfCnpj().length() != 11 && form.getCpfCnpj().length() != 14){
					sessao.setAttribute("exception", "Informe um CPF/CNPJ Válido.");
					return retorno;
				}
			}
			
			//Municipio
			if (!Util.verificarIdNaoVazio(form.getMunicipio())){
				sessao.setAttribute("exception", "Selecione o Município.");
				return retorno;
			}
			
			//Bairro
			if (!Util.verificarIdNaoVazio(form.getBairro())){
				sessao.setAttribute("exception", "Selecione o Bairro.");
				return retorno;
			}
			
			//Pavimento Rua
			if (!Util.verificarIdNaoVazio(form.getPavimentoRua())){
				sessao.setAttribute("exception", "Selecione o Pavimento Rua.");
				return retorno;
			}
			
			//Pavimento Calcada
			if (!Util.verificarIdNaoVazio(form.getPavimentoCalcada())){
				sessao.setAttribute("exception", "Selecione o Pavimento Calçada.");
				return retorno;
			}			
		}
		
		//[SB0003] - Verificar Existencia CPF/CNPJ			
		Integer idCliente = null;
		if(form.getCpfCnpj() != null && !form.getCpfCnpj().trim().equals("")){
			FiltroCliente filtroCliente = new FiltroCliente();
			if(form.getCpfCnpj().length() == 11){
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, form.getCpfCnpj()));
			}
			if(form.getCpfCnpj().length() == 14){
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, form.getCpfCnpj()));
			}
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Cliente> colecaoCliente =  this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
			
			if(cliente != null){
				idCliente = cliente.getId();
			}
		}
		
		//Municipio
		Municipio municipio = null;
		if(form.getMunicipio() != null && !form.getMunicipio().equals("-1")){
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, Integer.parseInt(form.getMunicipio())));
			Collection<Municipio> colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
			municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
		}
		
		//Bairro
		Bairro bairro = null;
		Integer idBairroArea = null;
		if(form.getBairro() != null && !form.getBairro().equals("-1")){
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID, Integer.parseInt(form.getBairro())));
			Collection<Bairro> colecaoBairro = this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());
			bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
			
			//Bairro Area
			if(bairro != null && bairro.getId() != null){
				FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
				filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO, bairro.getId()));
				Collection colecaoBairroArea = this.getFachada().pesquisar(filtroBairroArea, BairroArea.class.getName());
				BairroArea bairroArea = (BairroArea) Util.retonarObjetoDeColecao(colecaoBairroArea);
				idBairroArea = bairroArea.getId();
			}
		}
		

		
		Collection colecaoRegistroAtendimento = null;
		
		if(Util.verificarIdNaoVazio(form.getMatricula())){
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.IMOVEL_ID, form.getMatricula()));
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO, form.getEspecificacao()));
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, RegistroAtendimento.SITUACAO_PENDENTE));
			colecaoRegistroAtendimento = 
				this.getFachada().pesquisar(filtroRegistroAtendimento, 
					RegistroAtendimento.class.getName());
		}

		if (Util.isVazioOrNulo(colecaoRegistroAtendimento) || Util.verificarNaoVazio(form.getLocalOcorrencia())) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Usuario usuarioLogado = 
				(Usuario) Util.retonarObjetoDeColecao(this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName()));
			
			Integer idUsuarioLogado = (usuarioLogado != null)? usuarioLogado.getId() : null;
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.CODIGO_CONSTANTE, 
					1));
			
			Collection colecaoUnidadeOrganizacional = 
					this.getFachada().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			Integer idUnidadeOrganizacional = null;
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				idUnidadeOrganizacional = unidadeOrganizacional.getId();
			}
			
			Collection<ClienteFone> colecaoTelefone = null;
			if(form.getTelefoneContato() != null && !form.getTelefoneContato().trim().equals("")){
				colecaoTelefone = new ArrayList<ClienteFone>();
				
				FoneTipo foneTipo = new FoneTipo();
				foneTipo.setId(FoneTipo.RESIDENCIAL);
				
				String telefone = form.getTelefoneContato().trim();
				telefone = telefone.replace("(", "");
				telefone = telefone.replace(")", "");
				telefone = telefone.replace("-", "");
				telefone = telefone.replace(" ", "");
			
				String ddd = telefone.substring(0, 2);
				String numeroTelefone = telefone.substring(2);
				
				ClienteFone clienteFone = new ClienteFone();
				clienteFone.setFoneTipo(foneTipo);
				clienteFone.setDdd(ddd);
				clienteFone.setTelefone(numeroTelefone);
				clienteFone.setUltimaAlteracao(new Date());
				
				colecaoTelefone.add(clienteFone);
			}
			
			
			Imovel imovel = null;
			Integer idImovel = null;
			Collection colecaoEndereco = new ArrayList();
			Imovel imovelEndereco = null;
			String imovelInvalido = " Matrícula inválida.";
			
			if(Util.verificarIdNaoVazio(form.getMatricula())){
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatricula()));
				
				Collection colecaoImoveis = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
				if(!Util.isVazioOrNulo(colecaoImoveis)){
					imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
					idImovel = imovel.getId();
					imovelEndereco = this.getFachada().pesquisarImovelParaEndereco(imovel.getId());
					colecaoEndereco.add(imovelEndereco);
				}
				else{
					sessao.setAttribute("exception",imovelInvalido);
					return retorno;
				}
			}
			
			
			Date date = new Date();
			
			String observacao = form.getObservacoes();
			if(observacao.length() > 400){
				observacao = observacao.substring(0, 400);
			}
			
			if(observacao != null){
				observacao = observacao.toUpperCase();
			}
			
//			if(form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_AGUA.toString()) 
//				|| form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_ESGOTO.toString())){
				
				if(idCliente == null && form.getCpfCnpj() != null && !form.getCpfCnpj().equals("")){
					if(form.getCpfCnpj().length() == 11){
						observacao += " CPF: " + form.getCpfCnpj();
					}else if(form.getCpfCnpj().length() == 14){
						observacao += " CNPJ: " + form.getCpfCnpj();
					}
				}
				
//			}
			
			String pontoReferencia = form.getPontoReferencia();
			if(pontoReferencia.length() > 200){
				pontoReferencia = pontoReferencia.substring(0, 200);
			}
			
			Collection<RegistroAtendimentoAnexo> colecaoRegistroAtendimentoAnexos = new ArrayList<RegistroAtendimentoAnexo>();
	        FormFile arquivoInformado = null;
	        String observacaoAnexo = null;

	        String arquivoInvalido =  "O arquivo em anexo tem que ser menor que 3MB e conter a extensão JPG, DOC ou PDF.";
			if ( form.getArquivo1() != null  && form.getArquivo1().getFileSize() > 0 ) {
				arquivoInformado =  form.getArquivo1();
				observacaoAnexo = (String) "";
				//VALIDAÇÃO DO ARQUIVO
				if (validarRegistroAtendimentoAnexos(arquivoInformado) ) {
					RegistroAtendimentoAnexo registroAtendimentoanexo = this.gerarRegistroAtendimentoAnexo(
					arquivoInformado, observacaoAnexo);
					colecaoRegistroAtendimentoAnexos.add(registroAtendimentoanexo);
				} else {
					sessao.setAttribute("exception",arquivoInvalido);
					return retorno;
				}
			}
			
			if ( form.getArquivo2() != null && form.getArquivo2().getFileSize() > 0 ) {
				arquivoInformado =  form.getArquivo2();
				observacaoAnexo = (String) "";
				//VALIDAÇÃO DO ARQUIVO
				if (validarRegistroAtendimentoAnexos(arquivoInformado) ) {
					RegistroAtendimentoAnexo registroAtendimentoanexo = this.gerarRegistroAtendimentoAnexo(
					arquivoInformado, observacaoAnexo);
					colecaoRegistroAtendimentoAnexos.add(registroAtendimentoanexo);
				} else {
					sessao.setAttribute("exception", arquivoInvalido);
					return retorno;
				}
			}
			
			if ( form.getArquivo3() != null && form.getArquivo3().getFileSize() > 0) {
				arquivoInformado =  form.getArquivo3();
				observacaoAnexo = (String) "";
				//VALIDAÇÃO DO ARQUIVO
				if (validarRegistroAtendimentoAnexos(arquivoInformado) ) {
					RegistroAtendimentoAnexo registroAtendimentoanexo = this.gerarRegistroAtendimentoAnexo(
					arquivoInformado, observacaoAnexo);
					colecaoRegistroAtendimentoAnexos.add(registroAtendimentoanexo);
				} else {
					sessao.setAttribute("exception", arquivoInvalido);
					return retorno;
				}
			}
			
			if ( form.getArquivo4() != null && form.getArquivo4().getFileSize() > 0) {
				arquivoInformado =  form.getArquivo4();
				observacaoAnexo = (String) "";
				//VALIDAÇÃO DO ARQUIVO
				if (validarRegistroAtendimentoAnexos(arquivoInformado) ) {
					RegistroAtendimentoAnexo registroAtendimentoanexo = this.gerarRegistroAtendimentoAnexo(
					arquivoInformado, observacaoAnexo);
					colecaoRegistroAtendimentoAnexos.add(registroAtendimentoanexo);
				} else {
					sessao.setAttribute("exception", arquivoInvalido);
					return retorno;
				}
			}
			
			
			try {
				
				ClienteImovel clienteImovel = null;
				String localOcorrencia = "";
				if ( form.getLocalOcorrencia() != null ) {
					localOcorrencia = form.getLocalOcorrencia().toUpperCase();
				}
				
//				if(form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_AGUA.toString()) 
//						|| form.getSolicitacaoTipo().equalsIgnoreCase(SolicitacaoTipo.RAMAL_DE_ESGOTO.toString())){
//					
//					if(municipio != null && municipio.getId() != null){
//						localOcorrencia += " - " + municipio.getNome();
//					}
//					
//					if(bairro != null && bairro.getId() != null){
//						localOcorrencia += " - " + bairro.getNome();
//					}
//				}
				
				String nomeSolicitante = form.getNomeSolicitante();
				
				if(Util.verificarIdNaoVazio(form.getMatricula())){
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 2));
					filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
					
					clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName()));
				}
				
				String protocolo = this.getFachada().obterProtocoloAtendimento();
				
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,  new Integer(form.getEspecificacao())));
				filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
				Collection<SolicitacaoTipoEspecificacao> colecaoEspecificacao = this.getFachada().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
				
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoEspecificacao);
				ServicoTipo servicoTipo = solicitacaoTipoEspecificacao.getServicoTipo();
				
				if ( solicitacaoTipoEspecificacao.getIndicadorAnexoObrigatorio() != null && 
						(solicitacaoTipoEspecificacao.getIndicadorAnexoObrigatorio().equals(ConstantesSistema.SIM) || 
								solicitacaoTipoEspecificacao.getIndicadorAnexoObrigatorio().equals(ConstantesSistema.TODOS)) ){
					if (arquivoInformado == null  ) {
							sessao.setAttribute("exception", "É obrigatório anexar um arquivo.");
							return retorno;
					}
				} 
				
				Integer idServicoTipo = null;
				if ( servicoTipo != null && servicoTipo.getId() != null ) {
					idServicoTipo = servicoTipo.getId();
				} 
				
				
				
				
				Integer idPavimentoRua = null;
				if ( form.getPavimentoRua() != null && !form.getPavimentoRua().equals("") ) {
					idPavimentoRua = Integer.valueOf(form.getPavimentoRua());
				}
				
				Integer idPavimentoCalcada = null;
				if ( form.getPavimentoCalcada() != null && !form.getPavimentoCalcada().equals("") ) {
					idPavimentoCalcada = Integer.valueOf(form.getPavimentoCalcada());
				}
				
				
				
				
				Integer[] idRASolicitada = null;
				if(Util.verificarIdNaoVazio(form.getMatricula())){
				idRASolicitada =
					this.getFachada().inserirRegistroAtendimento(
						ConstantesSistema.SIM, //indicadorAtendimentoOnLine
						Util.formatarData(date), //dataAtendimento
						Util.formatarHoraSemData(date), //horaAtendimento
						null, //tempoEsperaInicial
						null, //tempoEsperaFinal
						MeioSolicitacao.INTERNET, //idMeioSolicitacao
						new Integer(form.getEspecificacao()), //idSolicitacaoTipoEspecificacao
						Util.formatarData(dataPrevista), //dataPrevista 
						observacao, //observacao
						imovel.getId(), //idImovel
						null, //descricaoLocalOcorrencia
						new Integer(form.getSolicitacaoTipo()), //idSolicitacaoTipo
						colecaoEndereco, //colecaoEndereco
						null, //pontoReferenciaLocalOcorrencia
						idBairroArea, //idBairroArea
						imovel.getLocalidade().getId(), //idLocalidade 
						imovel.getSetorComercial().getId(), //idSetorComercial
						imovel.getQuadra().getId(), //idQuadra 
						null, //idDivisaoEsgoto
						null, //idLocalOcorrencia
						idPavimentoRua, //idPavimentoRua 
						idPavimentoCalcada, //idPavimentoCalcada
						idUnidadeOrganizacional, //idUnidadeAtendimento 
						idUsuarioLogado, //idUsuarioLogado 
						clienteImovel.getCliente().getId(), //idCliente
						pontoReferencia, //pontoReferenciaSolicitante 
						null, //nomeSolicitante
						false, //novoSolicitante
						null, //idUnidadeSolicitante
						null, //idFuncionario
						colecaoTelefone, //colecaoFone
						null, //colecaoEnderecoSolicitante 
						null, //idUnidadeDestino
						null, //parecerUnidadeDestino 
						idServicoTipo, //idServicoTipo
						null, //numeroRAManual 
						null, //idRAJAGerado 
						null, //nnCoordenadaNorte
						null, //nnCoordenadaLeste 
						ConstantesSistema.NAO, //indicCoordenadaSemLogradouro
						colecaoRegistroAtendimentoAnexos, //colecaoRegistroAtendimentoAnexo
						protocolo, //protocoloAtendimento
						null, //colecaoContas
						observacao, //observacaoOS
						null, //colecaoPagamentos
						null, //habilitarCampoSatisfacaoEmail 
						null, //enviarEmailSatisfacao 
						form.getEmail(), //enderecoEmail
						null,
						null, //  colecaoPagValorMaiorDocumento
						null, // colecaoPagamentosValoresCobIndevidamente
						ConstantesSistema.NAO, // indicadorInformarPagamentoDuplicidade
						ConstantesSistema.NAO , // indicadorDocPagosValorMaiorRA
						ConstantesSistema.NAO, // indicadorDocPagosValorCobradosIndev
						null);
				}
				else{
					idRASolicitada = this.getFachada().inserirRegistroAtendimento(
							ConstantesSistema.SIM, //indicadorAtendimentoOnLine
							Util.formatarData(date), //dataAtendimento
							Util.formatarHoraSemData(date), //horaAtendimento
							null, //tempoEsperaInicial
							null, //tempoEsperaFinal
							MeioSolicitacao.INTERNET, //idMeioSolicitacao
							new Integer(form.getEspecificacao()), //idSolicitacaoTipoEspecificacao
							Util.formatarData(dataPrevista), //dataPrevista 
							observacao, //observacao
							idImovel, //idImovel
							localOcorrencia, //descricaoLocalOcorrencia
							SolicitacaoTipo.VAZAMENTO, //idSolicitacaoTipo
							colecaoEndereco, //colecaoEndereco
							null, //pontoReferenciaLocalOcorrencia
							idBairroArea, //idBairroArea
							null, //idLocalidade 
							null, //idSetorComercial
							null, //idQuadra 
							null, //idDivisaoEsgoto
							null, //idLocalOcorrencia
							idPavimentoRua, //idPavimentoRua 
							idPavimentoCalcada, //idPavimentoCalcada
							idUnidadeOrganizacional, //idUnidadeAtendimento 
							idUsuarioLogado, //idUsuarioLogado 
							idCliente, //idCliente
							pontoReferencia, //pontoReferenciaSolicitante 
							nomeSolicitante, //nomeSolicitante
							false, //novoSolicitante
							null, //idUnidadeSolicitante
							null, //idFuncionario
							colecaoTelefone, //colecaoFone
							null, //colecaoEnderecoSolicitante 
							null, //idUnidadeDestino
							null, //parecerUnidadeDestino 
							idServicoTipo, //idServicoTipo
							null, //numeroRAManual 
							null, //idRAJAGerado 
							null, //nnCoordenadaNorte
							null, //nnCoordenadaLeste 
							ConstantesSistema.NAO, //indicCoordenadaSemLogradouro
							colecaoRegistroAtendimentoAnexos, //colecaoRegistroAtendimentoAnexo
							protocolo, //protocoloAtendimento
							null, //colecaoContas
							observacao, //observacaoOS
							null, //colecaoPagamentos
							null, //habilitarCampoSatisfacaoEmail 
							null, //enviarEmailSatisfacao 
							form.getEmail(), //enderecoEmail
							null,
							null, //  colecaoPagValorMaiorDocumento
							null, // colecaoPagamentosValoresCobIndevidamente
							ConstantesSistema.NAO, // indicadorInformarPagamentoDuplicidade
							ConstantesSistema.NAO , // indicadorDocPagosValorMaiorRA
							ConstantesSistema.NAO, // indicadorDocPagosValorCobradosIndev
							null);
				}
				
				Collection<ClienteEndereco> colecaoEnderecoSolicitante =
						new ArrayList<ClienteEndereco>();
				
				if (imovelEndereco != null ) {
					
					ClienteEndereco clienteEndereco = new ClienteEndereco();
					
					// LogradouroBairro
					clienteEndereco.setLogradouroBairro(imovelEndereco.getLogradouroBairro());
							
					// LogradouroCep
					clienteEndereco.setLogradouroCep(imovelEndereco.getLogradouroCep());
					
					// Complemento endereco
					if (imovelEndereco.getComplementoEndereco() != null) {
						clienteEndereco.setComplemento(imovelEndereco.getComplementoEndereco());
					}
					
					// numero do Imovel
					clienteEndereco.setNumero(imovelEndereco.getNumeroImovel());
					
					// Perimetro
					clienteEndereco.setPerimetroInicial(imovelEndereco.getPerimetroInicial());
					clienteEndereco.setPerimetroFinal(imovelEndereco.getPerimetroFinal());
					
					colecaoEnderecoSolicitante.add(clienteEndereco);
				}
				
				if(Util.verificarIdNaoVazio(form.getMatricula())){
					this.getFachada().inserirRegistroAtendimentoSolicitante(
						idRASolicitada[0], 
						null,
						colecaoEnderecoSolicitante, 
						pontoReferencia, 
						form.getNomeSolicitante().toUpperCase(), 
						true,
						null, 
						null,
						colecaoTelefone, 
						protocolo);
				}
				
				sessao.setAttribute("RASolicitadaComSucesso", true);
				sessao.setAttribute("mensagemRA", protocolo);
				sessao.setAttribute("dataPrevistaAtendimentoRA", Util.formatarData(dataPrevista));
				
				retorno = actionMapping.findForward("RASolicitadaComSucesso");

			} catch (Exception e) {
				sessao.setAttribute("exception", "Não foi possível inserir a solicitação: \n" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
			
			FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
			filtroRegistroAtendimentoSolicitante.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
			
			RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(this.getFachada().pesquisar(filtroRegistroAtendimentoSolicitante, RegistroAtendimentoSolicitante.class.getName()));
			
			sessao.setAttribute("RAJaSolicitada", true);
			sessao.setAttribute("mensagemRA", registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
			sessao.setAttribute("dataPrevistaAtendimentoRA", Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		}
		
		return retorno;
	}

	/**
	 * Gerando o objeto que referencia o arquivo que será anexado ao RA
	 * 
	 * @author Arthur Carvalho
	 * @date 26/01/2012
	 * 
	 * @param FileItem
	 * @param String
	 */
	private RegistroAtendimentoAnexo gerarRegistroAtendimentoAnexo(FormFile arquivoAnexo, 
			String observacaoAnexo){
		
		RegistroAtendimentoAnexo anexo = new RegistroAtendimentoAnexo();
		
		//ARQUIVO EM BYTES
		try {
			anexo.setImagemDocumento(arquivoAnexo.getFileData());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//EXTENSÃO
		anexo.setNomeExtensaoDocumento( Util.obterExtensaoDoArquivo(arquivoAnexo.getFileName()).trim().toUpperCase() );
		
		//OBSERVAÇÃO
		if (observacaoAnexo != null && !observacaoAnexo.equals("")){
			
			anexo.setDescricaoDocumento(observacaoAnexo.trim());
		}
		
		//ÚLTIMA ALTERAÇÃO
		anexo.setUltimaAlteracao(new Date());
		
		return anexo;
		
	}
	
	public boolean validarRegistroAtendimentoAnexos(FormFile arquivoAnexo) {
		boolean arquivoValido = true;
		//Só poderão ser informados arquivos com as extensões: JPG, DOC ou PDF
		String arquivoExtensao = Util.obterExtensaoDoArquivo(arquivoAnexo.getFileName());
		
		if (!arquivoExtensao.equalsIgnoreCase("JPG") &&
			!arquivoExtensao.equalsIgnoreCase("DOC") &&
			!arquivoExtensao.equalsIgnoreCase("PDF") ){
			
			arquivoValido = false;
		}
		//O tamanho máximo para o arquivo será de 200kb
		if (arquivoAnexo.getFileSize() > 3145728){
			arquivoValido = false;
		}
		return arquivoValido;
	}
}