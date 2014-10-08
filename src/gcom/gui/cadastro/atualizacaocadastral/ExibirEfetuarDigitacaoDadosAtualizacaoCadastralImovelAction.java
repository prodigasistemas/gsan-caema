package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.Cadastrador;
import gcom.cadastro.CapaLoteAtualizacaoCadastral;
import gcom.cadastro.CapaLoteLogradouroAtualizacaoCadastral;
import gcom.cadastro.FiltroCapaLoteAtualizacaoCadastral;
import gcom.cadastro.FiltroCapaLoteLogradouroAtualizacaoCadastral;
import gcom.cadastro.ParametroTabelaAtualizacaoCadastro;
import gcom.cadastro.atualizacaocadastral.CepAtlzCad;
import gcom.cadastro.atualizacaocadastral.FiltroCadastrador;
import gcom.cadastro.atualizacaocadastral.FiltroCepAtlzCad;
import gcom.cadastro.atualizacaocadastral.FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroEnderecoReferencia;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.InserirRotaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
public class ExibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = 
			mapping.findForward("exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovel");
		
		EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form = 
				(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm) formulario;
		
		Short indicadorConsultaDocumentoReceita = this.getFachada().pesquisarParametrosDoSistema().getIndicadorConsultaDocumentoReceita();

		HttpSession sessao = request.getSession();
		sessao.setAttribute("indicadorConsultaDocumentoReceita", indicadorConsultaDocumentoReceita);

		String inserir = (String) request.getParameter("inserir");
		
		if(inserir != null && !inserir.equals("")){
			this.validarLocalidade(form, request);
			this.validarSetorComercial(form, request);
			this.validarQuadra(form, request);
			
			this.pesquisarCapaLoteAtualizacaoCadastral(form, request);
			
			form.setMatriculaImovel(null);
			this.limparFormulario(form);
			form.setPerfil("5");
			form.setDescricaoPerfil("NORMAL");
			form.setMatriculaNovoImovel("");
		}

		if (request.getParameter("tipoConsulta") != null){ 
			if (request.getParameter("tipoConsulta").equals(
				"leiturista")) {
				 form.setCadastrador(request
					.getParameter("idCampoEnviarDados"));
				 form.setNomeCadastrador(request
							.getParameter("descricaoCampoEnviarDados"));
				 request.setAttribute("idLeituristaEncontrado",
					"true");
				 
			 }
		}
		
		String objetoConsulta = (String) request.getParameter("objetoConsulta");
		
		if(objetoConsulta != null && !objetoConsulta.equals("")){
			
			//Consulta o imovel
			if(objetoConsulta.equals("1")){
				this.limparFormulario(form);
				this.montarDadosImovel(form,request);
			}
			
			//Consulta o Cadastrador
			if(objetoConsulta.equals("2")){
				this.pesquisarCadastradorDigitado(form,request);
			}
			
			//Consulta o Fone Tipo
			if(objetoConsulta.equals("3")){
				this.pesquisarFoneTipoDigitado(form,request);
			}

			//Consulta o Endereco Referencia
			if(objetoConsulta.equals("4")){
				this.pesquisarEnderecoReferenciaDigitado(form,request);
			}

			//Consulta o Pavimento Rua
			if(objetoConsulta.equals("5")){
				this.pesquisarPavimentoRuaDigitado(form,request);
			}

			//Consulta o Pavimento Calcada
			if(objetoConsulta.equals("6")){
				this.pesquisarPavimentoCalcadaDigitado(form,request);
			}
			
			//Consulta o Fonte Abastecimento
			if(objetoConsulta.equals("7")){
				this.pesquisarFonteAbastecimentoDigitado(form,request);
			}

			//Consulta o Situacao de Agua
			if(objetoConsulta.equals("8")){
				this.pesquisarSituacaoAguaDigitado(form,request);
			}
			
			//Consulta o Situacao de Esgoto
			if(objetoConsulta.equals("9")){
				this.pesquisarSituacaoEsgotoDigitado(form,request);
			}
			
			//Consulta o Hidrometro
			if(objetoConsulta.equals("10")){
				this.pesquisarHidrometroDigitado(form,request);
			}
			
			//Consulta o Local de Instalacao
			if(objetoConsulta.equals("11")){
				this.pesquisarLocalInstalacaoDigitado(form,request);
			}

			//Consulta o Protecao
			if(objetoConsulta.equals("12")){
				this.pesquisarProtecaoDigitado(form,request);
			}
			
			//Consulta o Cadastro Ocorrencia
			if(objetoConsulta.equals("13")){
				this.pesquisarOcorrenciaDigitado(form,request);
			}
			
			//Consultar o Cep
			if(objetoConsulta.equals("14")){
				this.pesquisarCep(form, request);
			}
			
			//Consulta o perfil do Imóvel
			if(objetoConsulta.equals("15")){
				this.pesquisarPerfilImovel(form, request);
			}
			
		}
		
		//Solicitado por Aryed dia 21/08/2012 para colocar valor default para cadastrador
//		form.setCadastrador(""+Cadastrador.MATRICULA_CADASTRADOR_AUTOMATICO);//Valor default
//		this.pesquisarCadastradorDigitado(form,request);
		
		if(form.getBairro() != null && !form.getBairro().equals("")){
			
			String idBairro = form.getBairro();
			
	        Collection<Bairro> colecaoBairro = this.pesquisarBairro(idBairro);
	        
	        if(colecaoBairro != null && !colecaoBairro.isEmpty()){
	        	
	        	Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
	        	
	        	form.setBairro(""+bairro.getId());
	        	form.setNomeBairro(bairro.getNome());
	        }
		}
		
		
		
		this.montarHelpCadastrador(request);
		this.montarHelpFoneTipo(request);
		this.montarHelpEnderecoReferencia(request);
		this.montarHelpPerfilImovel(request);
		this.montarHelpPavimentoRua(request);
		this.montarHelpPavimentoCalcada(request);
		this.montarHelpFonteAbastecimento(request);
		
		this.montarHelpSituacaoAgua(request);
		this.montarHelpSituacaoEsgoto(request);
		this.montarHelpLocalInstalacao(request);
		this.montarHelpProtecao(request);
		this.montarHelpOcorrencia(request);
		
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
	 * Pesquisa o tipo de telefone informando no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
	private void pesquisarFoneTipoDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
		HttpServletRequest request){
		
		String id = form.getFoneTipoClienteUsuario();
		if(id != null && !id.equals("")){
	        
			HttpSession sessao = request.getSession();
			
	        Collection<FoneTipo> colecaoFoneTipo = this.pesquisarFoneTipo(id);
	        
	        if(colecaoFoneTipo != null && !colecaoFoneTipo.isEmpty()){
	        	FoneTipo foneTipo = (FoneTipo) Util.retonarObjetoDeColecao(colecaoFoneTipo);
	        	
	        	form.setFoneTipoClienteUsuario(""+foneTipo.getId());
	        	form.setDescricaoFoneTipoClienteUsuario(foneTipo.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "lote");
	        	sessao.removeAttribute("foneTipoClienteUsuarioInexistente");
	        	
	        }else{
	        	form.setFoneTipoClienteUsuario(null);
	        	form.setDescricaoFoneTipoClienteUsuario("TIPO DE TEL. INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "foneTipoClienteUsuario");
	        	sessao.setAttribute("foneTipoClienteUsuarioInexistente", true);
	        }
			
		}
	}
	/**
	 * Pesquisa o endereco referencia informando no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
	private void pesquisarEnderecoReferenciaDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
			
			String id = form.getReferencia();
			if(id != null && !id.equals("")){
		        
				HttpSession sessao = request.getSession();
				
		        Collection<EnderecoReferencia> colecaoEnderecoReferencia = this.pesquisarEnderecoReferencia(id);
		        
		        if(colecaoEnderecoReferencia != null && !colecaoEnderecoReferencia.isEmpty()){
		        	EnderecoReferencia enderecoReferencia = 
		        		(EnderecoReferencia) Util.retonarObjetoDeColecao(colecaoEnderecoReferencia);
		        	
		        	form.setReferencia(""+enderecoReferencia.getId());
		        	form.setDescricaoReferencia(enderecoReferencia.getDescricao());
		        	
		        	request.setAttribute("nomeCampo", "numero");
		        	sessao.removeAttribute("referenciaInexistente");
		        	
		        }else{
		        	form.setReferencia(null);
		        	form.setDescricaoReferencia("REFERÊNCIA INEXISTENTE");
		        	
		        	request.setAttribute("nomeCampo", "referencia");
		        	sessao.setAttribute("referenciaInexistente", true);
		        }
				
			}
		}
	/**
	 * Pesquisa o pavimento rua informando no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarPavimentoRuaDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
		HttpServletRequest request){
			
		String id = form.getPavimentoRua();
		if(id != null && !id.equals("")){
			HttpSession sessao = request.getSession();
			
	        Collection<PavimentoRua> colecaoPavimentoRua = this.pesquisarPavimentoRua(id);
	        
	        if(colecaoPavimentoRua != null && !colecaoPavimentoRua.isEmpty()){
	        	PavimentoRua pavimentoRua = 
	        		(PavimentoRua) Util.retonarObjetoDeColecao(colecaoPavimentoRua);
	        	
	        	form.setPavimentoRua(""+pavimentoRua.getId());
	        	form.setDescricaoPavimentoRua(pavimentoRua.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "pavimentoCalcada");
	        	sessao.removeAttribute("pavimentoRuaInexistente");
	        	
	        }else{
	        	form.setPavimentoRua(null);
	        	form.setDescricaoPavimentoRua("PAV. RUA INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "pavimentoRua");
	        	sessao.setAttribute("pavimentoRuaInexistente", true);
	        }
			
		}
	}
	
	/**
	 * Pesquisa o pavimento calcada informando no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarPavimentoCalcadaDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String id = form.getPavimentoCalcada();
		if(id != null && !id.equals("")){
	        HttpSession sessao = request.getSession();
			
	        Collection<PavimentoCalcada> colecaoPavimentoCalcada = this.pesquisarPavimentoCalcada(id);
	        
	        if(colecaoPavimentoCalcada != null && !colecaoPavimentoCalcada.isEmpty()){
	        	PavimentoCalcada pavimentoCalcada = 
	        		(PavimentoCalcada) Util.retonarObjetoDeColecao(colecaoPavimentoCalcada);
	        	
	        	form.setPavimentoCalcada(""+pavimentoCalcada.getId());
	        	form.setDescricaoPavimentoCalcada(pavimentoCalcada.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "fonteAbastecimento");
	        	sessao.removeAttribute("pavimentoCalcadaInexistente");
	        	
	        }else{
	        	form.setPavimentoCalcada(null);
	        	form.setDescricaoPavimentoCalcada("PAV. CALC. INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "pavimentoCalcada");
	        	sessao.setAttribute("pavimentoCalcadaInexistente", true);
	        }
			
		}
	}
	
	/**
	 * Pesquisa a fonte de abastecimento informanda no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarFonteAbastecimentoDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String id = form.getFonteAbastecimento();
		if(id != null && !id.equals("")){
			HttpSession sessao = request.getSession();
			
	        Collection<FonteAbastecimento> colecaoFonteAbastecimento = this.pesquisarFonteAbastecimento(id);
	        
	        if(colecaoFonteAbastecimento != null && !colecaoFonteAbastecimento.isEmpty()){
	        	FonteAbastecimento fonteAbastecimento = 
	        		(FonteAbastecimento) Util.retonarObjetoDeColecao(colecaoFonteAbastecimento);
	        	
	        	form.setFonteAbastecimento(""+fonteAbastecimento.getId());
	        	form.setDescricaoFonteAbastecimento(fonteAbastecimento.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "situacaoAgua");
	        	sessao.removeAttribute("fonteAbastecimentoInexistente");
	        	
	        }else{
	        	form.setFonteAbastecimento(null);
	        	form.setDescricaoFonteAbastecimento("FONTE ABAST. INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "fonteAbastecimento");
	        	sessao.setAttribute("fonteAbastecimentoInexistente", true);
	        }
			
		}
	}
	
	/**
	 * Pesquisa a situação da ligacao de agua informanda no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarSituacaoAguaDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String id = form.getSituacaoAgua();
		if(id != null && !id.equals("")){
			HttpSession sessao = request.getSession();
			
	        Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = this.pesquisarSituacaoAgua(id);
	        
	        if(colecaoLigacaoAguaSituacao != null && !colecaoLigacaoAguaSituacao.isEmpty()){
	        	
	        	LigacaoAguaSituacao ligacaoAguaSituacao = 
	        		(LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
	        	
	        	form.setSituacaoAgua(""+ligacaoAguaSituacao.getId());
	        	form.setDescricaoSituacaoAgua(ligacaoAguaSituacao.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "situacaoEsgoto");
	        	sessao.removeAttribute("situacaoAguaInexistente");
	        	
	        }else{
	        	form.setSituacaoAgua(null);
	        	form.setDescricaoSituacaoAgua("SIT. AGUA INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "situacaoAgua");
	        	sessao.setAttribute("situacaoAguaInexistente", true);
	        }
			
		}
	}
	
	/**
	 * Pesquisa a situação da ligacao de esgoto informanda no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarSituacaoEsgotoDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String id = form.getSituacaoEsgoto();
		if(id != null && !id.equals("")){
			HttpSession sessao = request.getSession();
			
	        Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = this.pesquisarSituacaoEsgoto(id);
	        
	        if(colecaoLigacaoEsgotoSituacao != null && !colecaoLigacaoEsgotoSituacao.isEmpty()){
	        	
	        	LigacaoEsgotoSituacao ligacaoEsgotoSituacao = 
	        		(LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
	        	
	        	form.setSituacaoEsgoto(""+ligacaoEsgotoSituacao.getId());
	        	form.setDescricaoSituacaoEsgoto(ligacaoEsgotoSituacao.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "indicadorHidrometro");
	        	sessao.removeAttribute("situacaoEsgotoInexistente");
	        	
	        }else{
	        	form.setSituacaoEsgoto(null);
	        	form.setDescricaoSituacaoEsgoto("SIT. ESG. INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "situacaoEsgoto");
	        	sessao.setAttribute("situacaoEsgotoInexistente", true);
	        }
			
		}
	}
	
	/**
	 * Pesquisa o local de instalacao informanda no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarLocalInstalacaoDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String id = form.getLocalInstalacao();
		if(id != null && !id.equals("")){
			HttpSession sessao = request.getSession();
			
	        Collection<HidrometroLocalInstalacao> colecaoHidrometroLocalInstalacao = this.pesquisarLocalInstalacao(id);
	        
	        if(colecaoHidrometroLocalInstalacao != null && !colecaoHidrometroLocalInstalacao.isEmpty()){
	        	
	        	HidrometroLocalInstalacao hidrometroLocalInstalacao = 
	        		(HidrometroLocalInstalacao) Util.retonarObjetoDeColecao(colecaoHidrometroLocalInstalacao);
	        	
	        	form.setLocalInstalacao(""+hidrometroLocalInstalacao.getId());
	        	form.setDescricaoLocalInstalacao(hidrometroLocalInstalacao.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "protecao");
	        	sessao.removeAttribute("localInstalacaoInexistente");
	        	
	        }else{
	        	form.setLocalInstalacao(null);
	        	form.setDescricaoLocalInstalacao("LOCAL INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "localInstalacao");
	        	sessao.setAttribute("localInstalacaoInexistente", true);
	        }
			
		}
	}
	
	/**
	 * Pesquisa a protecao do hidrometro informanda no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarProtecaoDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String id = form.getProtecao();
		if(id != null && !id.equals("")){
			HttpSession sessao = request.getSession();
			
	        Collection<HidrometroProtecao> colecaoHidrometroProtecao = this.pesquisarProtecao(id);
	        
	        if(colecaoHidrometroProtecao != null && !colecaoHidrometroProtecao.isEmpty()){
	        	
	        	HidrometroProtecao hidrometroProtecao = 
	        		(HidrometroProtecao) Util.retonarObjetoDeColecao(colecaoHidrometroProtecao);
	        	
	        	form.setProtecao(""+hidrometroProtecao.getId());
	        	form.setDescricaoProtecao(hidrometroProtecao.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "cavalete");
	        	sessao.removeAttribute("tipoProtecaoInexistente");
	        	
	        }else{
	        	form.setProtecao(null);
	        	form.setDescricaoProtecao("PROT. INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "protecao");
	        	sessao.setAttribute("tipoProtecaoInexistente", true);
	        }
			
		}
	}

	/**
	 * Pesquisa a ocorrencia informanda no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarOcorrenciaDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String id = form.getOcorrenciaCadastro();
		if(id != null && !id.equals("")){
			HttpSession sessao = request.getSession();
			
	        Collection<CadastroOcorrencia> colecaoCadastroOcorrencia = this.pesquisarOcorrencia(id);
	        
	        if(colecaoCadastroOcorrencia != null && !colecaoCadastroOcorrencia.isEmpty()){
	        	
	        	CadastroOcorrencia cadastroOcorrencia = 
	        		(CadastroOcorrencia) Util.retonarObjetoDeColecao(colecaoCadastroOcorrencia);
	        	
	        	form.setOcorrenciaCadastro(""+cadastroOcorrencia.getId());
	        	form.setDescricaoOcorrenciaCadastro(cadastroOcorrencia.getDescricao());
	        	
	        	request.setAttribute("nomeCampo", "leitura");
	        	sessao.removeAttribute("ocorrenciaCadastroInexistente");
	        	
	        }else{
	        	form.setOcorrenciaCadastro(null);
	        	form.setDescricaoOcorrenciaCadastro("OCORR. INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "ocorrenciaCadastro");
	        	sessao.setAttribute("ocorrenciaCadastroInexistente", true);
	        }
			
		}
	}	
	
	/**
	 * Pesquisa o hidrometro informanda no formulario
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return void
	 */		
	private void pesquisarHidrometroDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			HttpServletRequest request){
				
		String numero = form.getNumeroHidrometro();
		if(numero != null && !numero.equals("")){
	        
	        Hidrometro hidrometro = this.getFachada().pesquisarHidrometroPeloNumero(numero);
	        
	        if(hidrometro != null){

	        	
//	        	if ( hidrometro != null && hidrometro.getHidrometroSituacao() != null && 
//	        			hidrometro.getHidrometroSituacao().getId().intValue() != HidrometroSituacao.DISPONIVEL.intValue() ) {
//	        		throw new ActionServletException("atencao.hidrometro_situacao_nao_pode_instalar",null,hidrometro.getHidrometroSituacao().getDescricao());
//	        	}
	        	
	        	this.validarNumeroHidrometro(form);
	        	
	        	form.setNumeroHidrometro(hidrometro.getNumero());
	        	form.setMarca(hidrometro.getHidrometroMarca().getDescricao());
	        	form.setCapacidade(hidrometro.getHidrometroCapacidade().getDescricao());
	        	if(hidrometro.getAnoFabricacao() != null){
	        		form.setAnoFabricacao(""+hidrometro.getAnoFabricacao());	
	        	}else{
	        		form.setAnoFabricacao(null);
	        	}
	        	
	        	
	        	request.setAttribute("nomeCampo", "localInstalacao");
	        	
	        }else{
	        	request.setAttribute("nomeCampo", "numeroHidrometro");
	        	
	        	form.setNumeroHidrometro(null);
	        	form.setMarca(null);
	        	form.setCapacidade(null);
	        	form.setAnoFabricacao(null);
	        	
	        	throw new ActionServletException("atencao.hidrometro_inexistente");
	        	
	        }
			
		}
	}		
	
	/**
	 * Pesquisa o cadastrador informando no formulario
	 * 
	 * @author Rafael Pinto / Anderson Cabral
	 * @date 20/07/2012 / 03/05/2013
	 * 
	 * @return void
	 */		
	private void pesquisarCadastradorDigitado(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
		HttpServletRequest request){
		
		HttpSession sessao = request.getSession();
		
		String cpfCadastrador = form.getCadastrador();
		
		if(cpfCadastrador != null && !cpfCadastrador.equals("")){
	        
//	        Collection<Cadastrador> colecaoCadastrador = this.pesquisarCadastrador(matriculaCadastrador);
	        
			Leiturista leiturista = this.getFachada().pesquisarLeituristaPorCPF(cpfCadastrador);
			
	        if(leiturista != null && leiturista.getId() != null && !leiturista.getId().equals("")){

	        	form.setCadastrador(leiturista.getUsuario().getCpf());
	        	form.setNomeCadastrador(leiturista.getUsuario().getNomeUsuario());
	        	
	        	//request.setAttribute("nomeCampo", "dataAtualizacao");
	        	sessao.removeAttribute("cadastradorInexistente");
	        	
	        }else{
	        	form.setCadastrador(null);
	        	form.setNomeCadastrador("CADASTRADOR INEXISTENTE");
	        	
	        	request.setAttribute("nomeCampo", "cadastrador");
	        	sessao.setAttribute("cadastradorInexistente", true);
	        }
			
		}

	}

	/**
	 * Pesquisa o imovel a partir da matricula informada e seta os dados na tela
	 * 
	 * @author Anderson Cabral
	 * @date 23/05/2013
	 * 
	 * @return void
	 */	
	private void montarDadosImovel(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
		HttpServletRequest request){
		
		String matriculaImovel = form.getMatriculaImovel();
		
		if(matriculaImovel != null && !matriculaImovel.equals("")){
			HttpSession sessao = request.getSession();
			
			Integer idCapaLoteAtualizacaoCadastral = (Integer) sessao.getAttribute("idCapaLoteAtualizacaoCadastral");
			
			FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
			filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples
					(FiltroImovelAtualizacaoCadastral.IMOVEL, matriculaImovel));		
			filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples
					(FiltroImovelAtualizacaoCadastral.INDICADOR_DADOS_RETORNO, ConstantesSistema.SIM));
			filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples
					(FiltroImovelAtualizacaoCadastral.INDICADOR_ATUALIZADO, ConstantesSistema.NAO));
			
			if(idCapaLoteAtualizacaoCadastral != null){
				filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples
						(FiltroImovelAtualizacaoCadastral.CAPA_LOTE_ATUALIZACAO_CADASTRAL, idCapaLoteAtualizacaoCadastral));
			}
			
			Collection<ImovelAtualizacaoCadastral> colecaoImovelAtuCadastral 
														= this.getFachada().pesquisar(filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());

			ImovelAtualizacaoCadastral imovelAtuCadastral = (ImovelAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecaoImovelAtuCadastral);
			
			if(imovelAtuCadastral != null && imovelAtuCadastral.getId() != null
					&& !imovelAtuCadastral.getId().equals("")){
				
				this.montarFormImovelAtuCadastral(imovelAtuCadastral, form, request);
				
				request.setAttribute("nomeCampo", "dataAtualizacao");
		        sessao.removeAttribute("perfilImovelInexistente");	
			}else{
				Imovel imovel = this.getFachada().pesquisarImovel(new Integer(matriculaImovel));
				
				if(imovel != null){
					this.montarFormImovel(imovel, form);
					
					request.setAttribute("nomeCampo", "dataAtualizacao");
			        sessao.removeAttribute("perfilImovelInexistente");
				}else{
					throw new ActionServletException("atencao.imovel.inexistente");
				}
			}	
		}
	}
	
	/**
	 * Pesquisa o cadastrador no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return Collection<Cadastrador>
	 */		
	private Collection<Cadastrador> pesquisarCadastrador(String matricula){
	    
		Collection<Cadastrador> colecaoCadastrador = new ArrayList<Cadastrador>();
		
		FiltroCadastrador filtroCadastrador = new FiltroCadastrador(FiltroCadastrador.ID);
		filtroCadastrador.limparListaParametros();
		
		if(matricula != null){
			filtroCadastrador.adicionarParametro(
				new ParametroSimples(FiltroCadastrador.MATRICULA, 
					matricula));
			
		}
		
		colecaoCadastrador = 
			this.getFachada().pesquisar(filtroCadastrador, Cadastrador.class.getName());
		
		return colecaoCadastrador;
	}
	
	/**
	 * Pesquisa o bairro no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 21/08/2012
	 * 
	 * @return Collection<Bairro>
	 */		
	private Collection<Bairro> pesquisarBairro(String id){
	    
		Collection<Bairro> colecaoBairro = new ArrayList<Bairro>();
		
		FiltroBairro filtroBairro = new FiltroBairro(FiltroBairro.NOME);
		filtroBairro.limparListaParametros();
		
		if(id != null){
			filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.ID, 
					id));
			
		}
		
		colecaoBairro = 
			this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());
		
		return colecaoBairro;
	}
	
	/**
	 * Monta o help exibido no formulario de Cadastrador
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
    private void montarHelpCadastrador(HttpServletRequest request){
    	
		String helpCadastrador = "";
		
		Collection<Cadastrador> colecaoCadastrador = 
			this.pesquisarCadastrador(null);
		
		if (colecaoCadastrador != null && !colecaoCadastrador.isEmpty()) {
			
			Iterator iteraColecaoCadastrador = colecaoCadastrador.iterator();
			
			while (iteraColecaoCadastrador.hasNext()) {
				Cadastrador cadastrador = (Cadastrador) iteraColecaoCadastrador.next();
				
				helpCadastrador = helpCadastrador + cadastrador.getMatricula() +"-"+ cadastrador.getNome() + "\n";
			}
		}
		
		request.setAttribute("helpCadastrador",helpCadastrador);    	
		
	}
    
	/**
	 * Pesquisa o tipo de telefone no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return Collection<FoneTipo>
	 */	
	private Collection<FoneTipo> pesquisarFoneTipo(String id){
		
		Collection<FoneTipo> colecaoFoneTipo = new ArrayList<FoneTipo>();
		
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo(FiltroFoneTipo.ID);
		filtroFoneTipo.limparListaParametros();
		
		filtroFoneTipo.adicionarParametro(
			new ParametroSimples(FiltroFoneTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(id != null){
			filtroFoneTipo.adicionarParametro(
				new ParametroSimples(FiltroFoneTipo.ID, 
					id));
			
		}
		
		colecaoFoneTipo = 
			this.getFachada().pesquisar(filtroFoneTipo, FoneTipo.class.getName());
		
		return colecaoFoneTipo;
	}
	
	/**
	 * Monta o help exibido no formulario de Tipo de Telefone
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
	
	private void montarHelpFoneTipo(HttpServletRequest request){

		String helpFoneTipo = "";
		
		Collection<FoneTipo> colecaoFoneTipo = 
			this.pesquisarFoneTipo(null);
		
		if (colecaoFoneTipo != null && !colecaoFoneTipo.isEmpty()) {
			
			Iterator iteraColecaoFoneTipo = colecaoFoneTipo.iterator();
			
			while (iteraColecaoFoneTipo.hasNext()) {
				FoneTipo fone = (FoneTipo) iteraColecaoFoneTipo.next();
				
				helpFoneTipo = helpFoneTipo + fone.getId() +"-"+ fone.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpFoneTipo",helpFoneTipo);
	}
	
	/**
	 * Pesquisa o endereco referencia no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return Collection<EnderecoReferencia>
	 */		
	private Collection<EnderecoReferencia> pesquisarEnderecoReferencia(String id){
		Collection<EnderecoReferencia> colecaoEnderecoReferencia = new ArrayList<EnderecoReferencia>();
		
		FiltroEnderecoReferencia filtroEnderecoReferencia = 
				new FiltroEnderecoReferencia(FiltroEnderecoReferencia.ID);
			
		filtroEnderecoReferencia.limparListaParametros();
		
		filtroEnderecoReferencia.adicionarParametro(
			new ParametroSimples(FiltroEnderecoReferencia.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroEnderecoReferencia.adicionarParametro(
				new ParametroSimples(FiltroEnderecoReferencia.ID, 
					id));
		}
		
		colecaoEnderecoReferencia = 
			this.getFachada().pesquisar(filtroEnderecoReferencia, EnderecoReferencia.class.getName());
		
		return colecaoEnderecoReferencia;
	}

	/**
	 * Monta o help exibido no formulario de Endereco Referencia
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
	
	private void montarHelpEnderecoReferencia(HttpServletRequest request){

		String helpEnderecoReferencia = "";
		
		Collection<EnderecoReferencia> colecaoEnderecoReferencia = 
			this.pesquisarEnderecoReferencia(null);
		
		if (colecaoEnderecoReferencia != null && !colecaoEnderecoReferencia.isEmpty()) {
			
			Iterator iteraColecaoEnderecoReferencia = colecaoEnderecoReferencia.iterator();
			
			while (iteraColecaoEnderecoReferencia.hasNext()) {
				EnderecoReferencia referencia = (EnderecoReferencia) iteraColecaoEnderecoReferencia.next();
				
				helpEnderecoReferencia = 
					helpEnderecoReferencia + referencia.getId() +"-"+ referencia.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpEnderecoReferencia",helpEnderecoReferencia);
	}

	/**
	 * Pesquisa o pavimento rua referencia no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return Collection<PavimentoRua>
	 */	
	private Collection<PavimentoRua> pesquisarPavimentoRua(String id){
		
		Collection<PavimentoRua> colecaoPavimentoRua = new ArrayList<PavimentoRua>();
		
		FiltroPavimentoRua filtroPavimentoRua = 
				new FiltroPavimentoRua(FiltroPavimentoRua.ID);
			
		filtroPavimentoRua.limparListaParametros();
		
		filtroPavimentoRua.adicionarParametro(
			new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroPavimentoRua.adicionarParametro(
				new ParametroSimples(FiltroPavimentoRua.ID, 
					id));
		}
		
		colecaoPavimentoRua = 
			this.getFachada().pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());
		
		return colecaoPavimentoRua;
	}
	
	/**
	 * Monta o help exibido no formulario de Pavimento Rua
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void montarHelpPavimentoRua(HttpServletRequest request){

		String helpPavimentoRua = "";
		
		Collection<PavimentoRua> colecaoPavimentoRua = 
			this.pesquisarPavimentoRua(null);
		
		if (colecaoPavimentoRua != null && !colecaoPavimentoRua.isEmpty()) {
			
			Iterator iteraColecaoPavimentoRua = colecaoPavimentoRua.iterator();
			
			while (iteraColecaoPavimentoRua.hasNext()) {
				PavimentoRua pavimentoRua = (PavimentoRua) iteraColecaoPavimentoRua.next();
				
				helpPavimentoRua = 
						helpPavimentoRua + pavimentoRua.getId() +"-"+ pavimentoRua.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpPavimentoRua",helpPavimentoRua);
	}
	
	/**
	 * Pesquisa o pavimento calcada referencia no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return Collection<PavimentoCalcada>
	 */		
	private Collection<PavimentoCalcada> pesquisarPavimentoCalcada(String id){
		
		Collection<PavimentoCalcada> colecaoPavimentoCalcada = new ArrayList<PavimentoCalcada>();
		
		FiltroPavimentoCalcada filtroPavimentoCalcada = 
				new FiltroPavimentoCalcada(FiltroPavimentoCalcada.ID);
			
		filtroPavimentoCalcada.limparListaParametros();
		
		filtroPavimentoCalcada.adicionarParametro(
			new ParametroSimples(FiltroPavimentoCalcada.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroPavimentoCalcada.adicionarParametro(
				new ParametroSimples(FiltroPavimentoCalcada.ID, 
					id));
		}
		
		colecaoPavimentoCalcada = 
			this.getFachada().pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
		
		return colecaoPavimentoCalcada;
	}
	
	/**
	 * Monta o help exibido no formulario de Pavimento Calcada
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void montarHelpPavimentoCalcada(HttpServletRequest request){

		String helpPavimentoCalcada = "";
		FiltroPavimentoCalcada filtroPavimentoCalcada = 
			new FiltroPavimentoCalcada(FiltroPavimentoCalcada.ID);
		
		filtroPavimentoCalcada.limparListaParametros();
		
		filtroPavimentoCalcada.adicionarParametro(
			new ParametroSimples(FiltroPavimentoCalcada.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<PavimentoCalcada> colecaoPavimentoCalcada = 
			this.getFachada().pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
		
		if (colecaoPavimentoCalcada != null && !colecaoPavimentoCalcada.isEmpty()) {
			
			Iterator iteraColecaoPavimentoCalcada = colecaoPavimentoCalcada.iterator();
			
			while (iteraColecaoPavimentoCalcada.hasNext()) {
				PavimentoCalcada pavimento= (PavimentoCalcada) iteraColecaoPavimentoCalcada.next();
				
				helpPavimentoCalcada = 
						helpPavimentoCalcada + pavimento.getId() +"-"+ pavimento.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpPavimentoCalcada",helpPavimentoCalcada);
	}
	
	/**
	 * Pesquisa a fonte de abastecimento no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return Collection<FonteAbastecimento>
	 */	
	private Collection<FonteAbastecimento> pesquisarFonteAbastecimento(String id){
		
		Collection<FonteAbastecimento> colecaoFonteAbastecimento =  new ArrayList<FonteAbastecimento>();
		
		FiltroFonteAbastecimento filtroFonteAbastecimento = 
				new FiltroFonteAbastecimento(FiltroFonteAbastecimento.ID);
			
		filtroFonteAbastecimento.limparListaParametros();
		
		filtroFonteAbastecimento.adicionarParametro(
			new ParametroSimples(FiltroFonteAbastecimento.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroFonteAbastecimento.adicionarParametro(
				new ParametroSimples(FiltroFonteAbastecimento.ID, 
					id));
		}
		
		colecaoFonteAbastecimento = 
			this.getFachada().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
		
		return colecaoFonteAbastecimento;
	}
	
	
	/**
	 * Monta o help exibido no formulario de Fonte de Abastecimento
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void montarHelpFonteAbastecimento(HttpServletRequest request){

		String helpFonteAbastecimento = "";
		
		Collection<FonteAbastecimento> colecaoFonteAbastecimento = this.pesquisarFonteAbastecimento(null);
		
		if (colecaoFonteAbastecimento != null && !colecaoFonteAbastecimento.isEmpty()) {
			
			Iterator iteraColecaoFonteAbastecimento = colecaoFonteAbastecimento.iterator();
			
			while (iteraColecaoFonteAbastecimento.hasNext()) {
				FonteAbastecimento fonte = (FonteAbastecimento) iteraColecaoFonteAbastecimento.next();
				
				helpFonteAbastecimento = 
						helpFonteAbastecimento + fonte.getId() +"-"+ fonte.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpFonteAbastecimento",helpFonteAbastecimento);
	}
	
	/**
	 * Pesquisa a situacao da ligacao de agua no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return Collection<LigacaoAguaSituacao>
	 */	
	private Collection<LigacaoAguaSituacao> pesquisarSituacaoAgua(String id){
		
		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao =  new ArrayList<LigacaoAguaSituacao>();
		
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = 
				new FiltroLigacaoAguaSituacao(FiltroLigacaoAguaSituacao.ID);
			
		filtroLigacaoAguaSituacao.limparListaParametros();
		
		filtroLigacaoAguaSituacao.adicionarParametro(
			new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroLigacaoAguaSituacao.adicionarParametro(
				new ParametroSimples(FiltroLigacaoAguaSituacao.ID, 
					id));
		}
		
		colecaoLigacaoAguaSituacao = 
			this.getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		
		
		return colecaoLigacaoAguaSituacao;
	}
		
	
	/**
	 * Monta o help exibido no formulario de Ligacao de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
	private void montarHelpSituacaoAgua(HttpServletRequest request){

		String helpSituacaoAgua = "";
		
		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = this.pesquisarSituacaoAgua(null);
		
		if (colecaoLigacaoAguaSituacao != null && !colecaoLigacaoAguaSituacao.isEmpty()) {
			
			Iterator iteraColecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao.iterator();
			
			while (iteraColecaoLigacaoAguaSituacao.hasNext()) {
				LigacaoAguaSituacao ligacaoAgua = (LigacaoAguaSituacao) iteraColecaoLigacaoAguaSituacao.next();
				
				helpSituacaoAgua = 
						helpSituacaoAgua + ligacaoAgua.getId() +"-"+ ligacaoAgua.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpSituacaoAgua",helpSituacaoAgua);
	}
	
	/**
	 * Pesquisa a situacao da ligacao de Esgoto no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return Collection<LigacaoEsgotoSituacao>
	 */	
	private Collection<LigacaoEsgotoSituacao> pesquisarSituacaoEsgoto(String id){
		
		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao =  new ArrayList<LigacaoEsgotoSituacao>();
		
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = 
				new FiltroLigacaoEsgotoSituacao(FiltroLigacaoEsgotoSituacao.ID);
			
		filtroLigacaoEsgotoSituacao.limparListaParametros();
		
		filtroLigacaoEsgotoSituacao.adicionarParametro(
			new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroLigacaoEsgotoSituacao.adicionarParametro(
				new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
					id));
		}
		
		colecaoLigacaoEsgotoSituacao = 
			this.getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
		
		
		return colecaoLigacaoEsgotoSituacao;
	}
	
	
	
	/**
	 * Monta o help exibido no formulario de Ligacao de Esgoto
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void montarHelpSituacaoEsgoto(HttpServletRequest request){

		String helpSituacaoEsgoto = "";

		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao =
			this.pesquisarSituacaoEsgoto(null);
		
		if (colecaoLigacaoEsgotoSituacao != null && !colecaoLigacaoEsgotoSituacao.isEmpty()) {
			
			Iterator iteraColecaoLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao.iterator();
			
			while (iteraColecaoLigacaoEsgotoSituacao.hasNext()) {
				LigacaoEsgotoSituacao ligacaoEsgoto = (LigacaoEsgotoSituacao) iteraColecaoLigacaoEsgotoSituacao.next();
				
				helpSituacaoEsgoto = 
						helpSituacaoEsgoto + ligacaoEsgoto.getId() +"-"+ ligacaoEsgoto.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpSituacaoEsgoto",helpSituacaoEsgoto);
	}
	
	/**
	 * Pesquisa o local de instalacao no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return Collection<HidrometroLocalInstalacao>
	 */	
	private Collection<HidrometroLocalInstalacao> pesquisarLocalInstalacao(String id){
		
		Collection<HidrometroLocalInstalacao> colecaoHidrometroLocalInstalacao =  new ArrayList<HidrometroLocalInstalacao>();

		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = 
				new FiltroHidrometroLocalInstalacao(FiltroHidrometroLocalInstalacao.ID);
			
		filtroHidrometroLocalInstalacao.limparListaParametros();
		
		filtroHidrometroLocalInstalacao.adicionarParametro(
			new ParametroSimples(FiltroHidrometroLocalInstalacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroHidrometroLocalInstalacao.adicionarParametro(
				new ParametroSimples(FiltroHidrometroLocalInstalacao.ID, 
					id));
		}
		
		colecaoHidrometroLocalInstalacao = 
			this.getFachada().pesquisar(filtroHidrometroLocalInstalacao, HidrometroLocalInstalacao.class.getName());
				
		return colecaoHidrometroLocalInstalacao;
	}	
	
	/**
	 * Monta o help exibido no formulario de Local de Instalacao
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void montarHelpLocalInstalacao(HttpServletRequest request){

		String helpLocalInstalacao = "";

		Collection<HidrometroLocalInstalacao> colecaoHidrometroLocalInstalacao = 
		 this.pesquisarLocalInstalacao(null);
		
		if (colecaoHidrometroLocalInstalacao != null && !colecaoHidrometroLocalInstalacao.isEmpty()) {
			
			Iterator iteraColecaoHidrometroLocalInstalacao = colecaoHidrometroLocalInstalacao.iterator();
			
			while (iteraColecaoHidrometroLocalInstalacao.hasNext()) {
				HidrometroLocalInstalacao hidrometroLocalInstalacao = (HidrometroLocalInstalacao) iteraColecaoHidrometroLocalInstalacao.next();
				
				helpLocalInstalacao = 
						helpLocalInstalacao + hidrometroLocalInstalacao.getId() +"-"+ hidrometroLocalInstalacao.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpLocalInstalacao",helpLocalInstalacao);
	}

	/**
	 * Pesquisa a protecao do hidrometro no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return Collection<HidrometroProtecao>
	 */	
	private Collection<HidrometroProtecao> pesquisarProtecao(String id){
		
		Collection<HidrometroProtecao> colecaoHidrometroProtecao = new ArrayList<HidrometroProtecao>();

		FiltroHidrometroProtecao filtroHidrometroProtecao = 
				new FiltroHidrometroProtecao(FiltroHidrometroProtecao.ID);
			
		filtroHidrometroProtecao.limparListaParametros();
		
		filtroHidrometroProtecao.adicionarParametro(
			new ParametroSimples(FiltroHidrometroProtecao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroHidrometroProtecao.adicionarParametro(
				new ParametroSimples(FiltroHidrometroProtecao.ID, 
					id));
		}
		
		colecaoHidrometroProtecao = 
			this.getFachada().pesquisar(filtroHidrometroProtecao, HidrometroProtecao.class.getName());
		
				
		return colecaoHidrometroProtecao;
	}		
	
	/**
	 * Monta o help exibido no formulario de Protecao
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void montarHelpProtecao(HttpServletRequest request){

		String helpProtecao = "";
		
		Collection<HidrometroProtecao> colecaoHidrometroProtecao = this.pesquisarProtecao(null);
		
		if (colecaoHidrometroProtecao != null && !colecaoHidrometroProtecao.isEmpty()) {
			
			Iterator iteraColecaoHidrometroProtecao = colecaoHidrometroProtecao.iterator();
			
			while (iteraColecaoHidrometroProtecao.hasNext()) {
				HidrometroProtecao hidrometroProtecao = (HidrometroProtecao) iteraColecaoHidrometroProtecao.next();
				
				helpProtecao = 
						helpProtecao + hidrometroProtecao.getId() +"-"+ hidrometroProtecao.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpProtecao",helpProtecao);
	}


	/**
	 * Pesquisa a ocorrencia de cadastro de ocorrencia no banco de dados
	 * 
	 * @author Rafael Pinto
	 * @date 23/07/2012
	 * 
	 * @return Collection<CadastroOcorrencia>
	 */	
	private Collection<CadastroOcorrencia> pesquisarOcorrencia(String id){
		
		Collection<CadastroOcorrencia> colecaoCadastroOcorrencia = new ArrayList<CadastroOcorrencia>();

		FiltroCadastroOcorrencia filtroCadastroOcorrencia = 
				new FiltroCadastroOcorrencia(FiltroCadastroOcorrencia.ID);
			
		filtroCadastroOcorrencia.limparListaParametros();
		
		filtroCadastroOcorrencia.adicionarParametro(
			new ParametroSimples(FiltroCadastroOcorrencia.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(id != null){
			filtroCadastroOcorrencia.adicionarParametro(
				new ParametroSimples(FiltroCadastroOcorrencia.ID, 
					id));
		}		
		
		colecaoCadastroOcorrencia = 
			this.getFachada().pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
		
				
		return colecaoCadastroOcorrencia;
	}		
	
	/**
	 * Monta o help exibido no formulario de Ocorrencia de Cadastro
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */		
	private void montarHelpOcorrencia(HttpServletRequest request){

		String helpOcorrencia = "";

		Collection<CadastroOcorrencia> colecaoCadastroOcorrencia =this.pesquisarOcorrencia(null);
		
		if (colecaoCadastroOcorrencia != null && !colecaoCadastroOcorrencia.isEmpty()) {
			
			Iterator iteraColecaoCadastroOcorrencia = colecaoCadastroOcorrencia.iterator();
			
			while (iteraColecaoCadastroOcorrencia.hasNext()) {
				CadastroOcorrencia cadastroOcorrencia = 
					(CadastroOcorrencia) iteraColecaoCadastroOcorrencia.next();
				
				helpOcorrencia = 
						helpOcorrencia + cadastroOcorrencia.getId() +"-"+ cadastroOcorrencia.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpOcorrencia",helpOcorrencia);
	}
	
	/**
	 * Pesquisa o Perfil do Imóvel no banco de dados
	 * 
	 * @author Davi Menezes
	 * @date 27/07/2012
	 * 
	 * @return void
	 */
	private Collection<ImovelPerfil> pesquisarPerfilImovel(String id){
		
		Collection<ImovelPerfil> colecaoImovelPerfil = new ArrayList<ImovelPerfil>();
		
		FiltroImovelPerfil filtroImovelPerfil = 
				new FiltroImovelPerfil(FiltroImovelPerfil.ID);
			
		filtroImovelPerfil.limparListaParametros();
		
		filtroImovelPerfil.adicionarParametro(
			new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		if(id != null){
			filtroImovelPerfil.adicionarParametro(
				new ParametroSimples(FiltroImovelPerfil.ID, 
					id));
		}
		
		colecaoImovelPerfil = 
			this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		return colecaoImovelPerfil;
	}
	
	/**
	 * Monta o help exibido no formulario de Perfil Imovel
	 * 
	 * @author Davi Menezes
	 * @date 20/07/2012
	 * 
	 * @return void
	 */
	private void montarHelpPerfilImovel(HttpServletRequest request){
		
		String helpPerfilImovel = "";
		
		Collection<ImovelPerfil> colecaoImovelPerfil = 
				this.pesquisarPerfilImovel(null);
		
		if(colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()){
			Iterator iteraColecaoImovelPerfil = colecaoImovelPerfil.iterator();
			
			while(iteraColecaoImovelPerfil.hasNext()){
				ImovelPerfil imovelPerfil = (ImovelPerfil) iteraColecaoImovelPerfil.next();
				
				helpPerfilImovel = 
						helpPerfilImovel + imovelPerfil.getId() + "-" + imovelPerfil.getDescricao() + ";\n";
			}
		}
		
		request.setAttribute("helpPerfilImovel", helpPerfilImovel);
	}
	
	/**
	 * Pesquisa o Cep no banco de dados
	 * 
	 * @author Davi Menezes
	 * @date 24/07/2012
	 * 
	 * @return void
	 */
	private void pesquisarCep(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, HttpServletRequest request) {
		HttpSession sessao = request.getSession();
		
		if(form.getLogradouro() != null && !form.getLogradouro().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			
			Collection<LogradouroCep> colecaoLogradouroCep = new ArrayList<LogradouroCep>();
			
			FiltroLogradouroCep filtroLograoduroCep = new FiltroLogradouroCep();
			filtroLograoduroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.LOGRADOURO, form.getLogradouro()));
			filtroLograoduroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.INDICADOR_USO, ConstantesSistema.SIM));
			filtroLograoduroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.CEP);
			
			colecaoLogradouroCep = this.getFachada().pesquisar(filtroLograoduroCep, LogradouroCep.class.getName());
			
			sessao.setAttribute("colecaoCep", colecaoLogradouroCep);
			
			if(colecaoLogradouroCep.size() == 1){
				LogradouroCep logradouroCep = (LogradouroCep) Util.retonarObjetoDeColecao(colecaoLogradouroCep);
				
				request.setAttribute("nomeCampo", "cep");
				sessao.setAttribute("bloquearCep", true);
				form.setCep(logradouroCep.getCep().getCepId().toString());
			}else{
				request.setAttribute("nomeCampo", "referencia");
				sessao.removeAttribute("bloquearCep");
			}
			
		}else{
			sessao.removeAttribute("colecaoCep");
		}
	}
	
	/**
	 * Pesquisa o perfil do imóvel no banco de dados
	 * 
	 * @author Davi Menezes
	 * @date 25/07/2012
	 * 
	 * @return void
	 */
	private void pesquisarPerfilImovel(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, HttpServletRequest request) {
		if(form.getPerfil() != null && !form.getPerfil().equals("")){
			
			HttpSession sessao = request.getSession();
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, form.getPerfil()));
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection<?> colImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			if(!Util.isVazioOrNulo(colImovelPerfil)){
				ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colImovelPerfil);
				
				form.setDescricaoPerfil(imovelPerfil.getDescricao());
				
				request.setAttribute("nomeCampo", "analisarTarifaSocial");
				sessao.removeAttribute("perfilImovelInexistente");
				
			}else{
				form.setPerfil("");
				form.setDescricaoPerfil("Perfil Inexistente");
				
				request.setAttribute("nomeCampo", "analisarTarifaSocial");
				sessao.setAttribute("perfilImovelInexistente", true);
			}
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
		
		//Dados do Cadastrador
		form.setCadastrador(null);
		form.setNomeCadastrador(null);
		
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
	}
	
	/**
	 * Pesquisar Capa Lote Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 24/07/2012
	 * 
	 * @return void
	 */
	private void pesquisarCapaLoteAtualizacaoCadastral(
			EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, HttpServletRequest request) {
		
		HttpSession sessao = request.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Collection<Logradouro> colecaoLogradourosSelecionados = (Collection<Logradouro>)
				sessao.getAttribute("colecaoLogradourosSelecionados");
		
		Collection<Logradouro> colecaoLogradourosRemovidos = (Collection<Logradouro>)
				sessao.getAttribute("colecaoLogradourosRemovidos");
		
		FiltroCapaLoteAtualizacaoCadastral filtroCapaLote = new FiltroCapaLoteAtualizacaoCadastral();
		filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.NUMERO_QUADRA, form.getNumeroQuadra()));
//		filtroCapaLote.adicionarParametro(new ParametroSimples(FiltroCapaLoteAtualizacaoCadastral.ID_BAIRRO, form.getBairro()));
		filtroCapaLote.adicionarCaminhoParaCarregamentoEntidade(FiltroCapaLoteAtualizacaoCadastral.PARAMETRO_TABELA_ATUALIZACAO_CADASTRAL);
		
		Collection<CapaLoteAtualizacaoCadastral> colecaoCapaLote = this.getFachada().pesquisar(filtroCapaLote, CapaLoteAtualizacaoCadastral.class.getName());
		
		Integer idParametroAtualizacaoCadastral = null;
		Integer idCapaLoteAtualizacaoCadastral = null;
		
		if(!Util.isVazioOrNulo(colecaoCapaLote)){
			CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral = (CapaLoteAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecaoCapaLote);
			if(capaLoteAtualizacaoCadastral.getIndicadorFinalizado().equals(ConstantesSistema.SIM)){
				idParametroAtualizacaoCadastral = this.manterParametrosAtualizacaoCadastral(form, usuario, null, "1");
				idCapaLoteAtualizacaoCadastral = this.manterCapaLoteAtualizacaoCadastral(form, idParametroAtualizacaoCadastral, null, "1");
			}else{
				ParametroTabelaAtualizacaoCadastro parametroAtualizacaoCadastral = capaLoteAtualizacaoCadastral.getParametroTabelaAtualizacaoCadastro();
				idParametroAtualizacaoCadastral = this.manterParametrosAtualizacaoCadastral(form, usuario, parametroAtualizacaoCadastral, "2");
				idCapaLoteAtualizacaoCadastral = this.manterCapaLoteAtualizacaoCadastral(form, idParametroAtualizacaoCadastral, 
						capaLoteAtualizacaoCadastral, "2");
			}
			
		}else{
			idParametroAtualizacaoCadastral = this.manterParametrosAtualizacaoCadastral(form, usuario, null, "1");
			idCapaLoteAtualizacaoCadastral = this.manterCapaLoteAtualizacaoCadastral(form, idParametroAtualizacaoCadastral, null, "1");
		}
		
		sessao.setAttribute("idParametroAtualizacaoCadastral", idParametroAtualizacaoCadastral);
		sessao.setAttribute("idCapaLoteAtualizacaoCadastral", idCapaLoteAtualizacaoCadastral);
		
		this.removerCapaLoteLogradouroAtualizacaoCadastral(idCapaLoteAtualizacaoCadastral, colecaoLogradourosRemovidos,request);
		this.inserirCapaLoteLogradouroAtualizacaoCadastral(idCapaLoteAtualizacaoCadastral, colecaoLogradourosSelecionados);

	}

	/**
	 * Inserir/Atualizar Parametro Tabela Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 24/07/2012
	 * 
	 * @return id
	 */
	private Integer manterParametrosAtualizacaoCadastral(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, Usuario usuario,
			ParametroTabelaAtualizacaoCadastro parametroAtualizacaoCadastral, String indicadorInserir) {
		
		Integer idParametroAtualizacaoCadastral = null;
		
		Localidade localidade = new Localidade(Integer.parseInt(form.getIdLocalidade()));
		
		Integer codigoSetorComercial = Integer.parseInt(form.getCodigoSetorComercial());
		
		Integer numeroQuadra = Integer.parseInt(form.getNumeroQuadra());
		
		if(parametroAtualizacaoCadastral == null){
			parametroAtualizacaoCadastral = new ParametroTabelaAtualizacaoCadastro();
		}
		
		parametroAtualizacaoCadastral.setLocalidadeInicial(localidade);
		parametroAtualizacaoCadastral.setLocalidadeFinal(localidade);
		parametroAtualizacaoCadastral.setCodigoSetorComercialInicial(codigoSetorComercial);
		parametroAtualizacaoCadastral.setCodigoSetorComercialFinal(codigoSetorComercial);
		parametroAtualizacaoCadastral.setNumeroQuadraInicial(numeroQuadra);
		parametroAtualizacaoCadastral.setNumeroQuadraFinal(numeroQuadra);
		parametroAtualizacaoCadastral.setEmpresa(pesquisarEmpresa());
		parametroAtualizacaoCadastral.setImovel(null);
		parametroAtualizacaoCadastral.setCodigoRotaInicial(null);
		parametroAtualizacaoCadastral.setCodigoRotaFinal(null);
		parametroAtualizacaoCadastral.setUsuario(usuario);
		parametroAtualizacaoCadastral.setUltimaAlteracao(new Date());
		
		if(indicadorInserir.equals("1")){
			idParametroAtualizacaoCadastral = (Integer) this.getFachada().inserir(parametroAtualizacaoCadastral);
		}else{
			idParametroAtualizacaoCadastral = parametroAtualizacaoCadastral.getId(); 
			
			this.getFachada().atualizar(parametroAtualizacaoCadastral);
		}
		
		return idParametroAtualizacaoCadastral;
	}
	
	/**
	 * Inserir/Atualizar Capa Lote Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 24/07/2012
	 * 
	 * @return id
	 */
	private Integer manterCapaLoteAtualizacaoCadastral(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form,
			Integer idParametroAtualizacaoCadastral, CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral, String indicadorInserir) {
		
		Integer idCapaLoteAtualizacaoCadastral = null;
		
		ParametroTabelaAtualizacaoCadastro parametroAtualizacaoCadastral = new ParametroTabelaAtualizacaoCadastro();
		parametroAtualizacaoCadastral.setId(idParametroAtualizacaoCadastral);
		
		Localidade localidade = new Localidade(Integer.parseInt(form.getIdLocalidade()));
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		
		SetorComercial setorComercial = null;
		
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if(!Util.isVazioOrNulo(colecaoSetorComercial)){
			setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
		}
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercial()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getNumeroQuadra()));
		
		Quadra quadra = null;
		
		Collection<Quadra> colecaoQuadra = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
		if(!Util.isVazioOrNulo(colecaoQuadra)){
			quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
		}
		
		Bairro bairro = new Bairro();
		bairro.setId(Integer.parseInt(form.getBairro()));
		
		Integer quantidadeLimiteDocumentos = Integer.parseInt(form.getQuantidadeDocumentos());
		
		if(capaLoteAtualizacaoCadastral == null){
			capaLoteAtualizacaoCadastral = new CapaLoteAtualizacaoCadastral();
		}
		
		capaLoteAtualizacaoCadastral.setLocalidade(localidade);
		capaLoteAtualizacaoCadastral.setSetorComercial(setorComercial);
		capaLoteAtualizacaoCadastral.setQuadra(quadra);
		capaLoteAtualizacaoCadastral.setBairro(bairro);
		capaLoteAtualizacaoCadastral.setQuantidadeLimiteDocumentos(quantidadeLimiteDocumentos);
		capaLoteAtualizacaoCadastral.setIndicadorFinalizado(ConstantesSistema.NAO);
		capaLoteAtualizacaoCadastral.setParametroTabelaAtualizacaoCadastro(parametroAtualizacaoCadastral);
		capaLoteAtualizacaoCadastral.setUltimaAlteracao(new Date());
		
		if(indicadorInserir.equals("1")){
			capaLoteAtualizacaoCadastral.setQuantidadeDocumentosDigitados(0);
			
			idCapaLoteAtualizacaoCadastral = (Integer) this.getFachada().inserir(capaLoteAtualizacaoCadastral);
		}else{
			idCapaLoteAtualizacaoCadastral = capaLoteAtualizacaoCadastral.getId();
			
			this.getFachada().atualizar(capaLoteAtualizacaoCadastral);
			
			form.setQuantidadeDocumentosIncluidos(capaLoteAtualizacaoCadastral.getQuantidadeDocumentosDigitados().toString());
		}
		
		return idCapaLoteAtualizacaoCadastral;
	}
	
	/**
	 * Inserir Capa Lote Logradouro Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 24/07/2012
	 * 
	 * @return void
	 */
	private void inserirCapaLoteLogradouroAtualizacaoCadastral(Integer idCapaLoteAtualizacaoCadastral,
			Collection<Logradouro> colecaoLogradourosSelecionados) {
		
		if(!Util.isVazioOrNulo(colecaoLogradourosSelecionados)){
			CapaLoteAtualizacaoCadastral capaLoteAtualizacaoCadastral = new CapaLoteAtualizacaoCadastral();
			capaLoteAtualizacaoCadastral.setId(idCapaLoteAtualizacaoCadastral);
			
			Iterator<?> iterator = colecaoLogradourosSelecionados.iterator();
			while(iterator.hasNext()){
				Logradouro logradouro = (Logradouro) iterator.next();
				
				FiltroCapaLoteLogradouroAtualizacaoCadastral filtroCapaLoteLogradouro = 
						new FiltroCapaLoteLogradouroAtualizacaoCadastral();
				filtroCapaLoteLogradouro.adicionarParametro(new ParametroSimples(
						FiltroCapaLoteLogradouroAtualizacaoCadastral.ID_LOGRADOURO, logradouro.getId()));
				filtroCapaLoteLogradouro.adicionarParametro(new ParametroSimples(
						FiltroCapaLoteLogradouroAtualizacaoCadastral.ID_CAPA_LOTE_ATUALIZACAO_CADASTRAL, idCapaLoteAtualizacaoCadastral));
				
				Collection<?> colCapaLoteLogradouro = this.getFachada().pesquisar(
						filtroCapaLoteLogradouro, CapaLoteLogradouroAtualizacaoCadastral.class.getName());
				
				if(Util.isVazioOrNulo(colCapaLoteLogradouro)){
					CapaLoteLogradouroAtualizacaoCadastral capaLoteLogradouro = new CapaLoteLogradouroAtualizacaoCadastral();
					capaLoteLogradouro.setCapaLoteAtualizacaoCadastral(capaLoteAtualizacaoCadastral);
					capaLoteLogradouro.setLogradouro(logradouro);
					capaLoteLogradouro.setUltimaAlteracao(new Date());
					
					this.getFachada().inserir(capaLoteLogradouro);
				}
			}
		}
	}
	
	/**
	 * Remover Capa Lote Logradouro Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 06/08/2012
	 */
	private void removerCapaLoteLogradouroAtualizacaoCadastral(Integer idCapaLoteAtualizacaoCadastral,
			Collection<Logradouro> colecaoLogradourosRemovidos,HttpServletRequest request) {
		
		if(!Util.isVazioOrNulo(colecaoLogradourosRemovidos)){
			FiltroCapaLoteLogradouroAtualizacaoCadastral filtroCapaLoteLogradouro = null;
			Collection<?> colCapaLoteLogradouro = null;
			HttpSession sessao = request.getSession();
			
			Iterator<?> iter = colecaoLogradourosRemovidos.iterator();
			while(iter.hasNext()){
				Logradouro logradouro = (Logradouro) iter.next();
				
				filtroCapaLoteLogradouro = new FiltroCapaLoteLogradouroAtualizacaoCadastral();
				filtroCapaLoteLogradouro.adicionarParametro(new ParametroSimples(
					FiltroCapaLoteLogradouroAtualizacaoCadastral.ID_CAPA_LOTE_ATUALIZACAO_CADASTRAL, idCapaLoteAtualizacaoCadastral));
				filtroCapaLoteLogradouro.adicionarParametro(new ParametroSimples(
						FiltroCapaLoteLogradouroAtualizacaoCadastral.ID_LOGRADOURO, logradouro.getId()));
				
				colCapaLoteLogradouro = this.getFachada().pesquisar(
						filtroCapaLoteLogradouro, CapaLoteLogradouroAtualizacaoCadastral.class.getName());
				if(!Util.isVazioOrNulo(colCapaLoteLogradouro)){
					CapaLoteLogradouroAtualizacaoCadastral capaLoteLogradouro = (CapaLoteLogradouroAtualizacaoCadastral)
							Util.retonarObjetoDeColecao(colCapaLoteLogradouro);
					this.getFachada().remover(capaLoteLogradouro);
				}
			}
			sessao.removeAttribute("colecaoLogradourosRemovidos");
		}
	}
	
	/**
	 * Validar Localidade
	 * 
	 * @author Davi Menezes
	 * @date 01/08/2012
	 */
	private void validarLocalidade(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, HttpServletRequest request) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		if(Util.isVazioOrNulo(colLocalidade)){
			throw new ActionServletException("pesquisa.localidade.inexistente");
		}
	}
	
	/**
	 * Validar Setor Comercial
	 * 
	 * @author Davi Menezes
	 * @date 01/08/2012
	 */
	private void validarSetorComercial(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, HttpServletRequest request) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if(Util.isVazioOrNulo(colSetorComercial)){
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}
	}
	
	/**
	 * Validar Quadra
	 * 
	 * @author Davi Menezes
	 * @date 01/08/2012
	 */
	private void validarQuadra(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, HttpServletRequest request){
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
	
	/**
	 * Validar Número do Hidrômetro
	 * 
	 * @author Davi Menezes
	 * @date 09/08/2012
	 */
	private void validarNumeroHidrometro(EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form) {
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
	
	public Empresa pesquisarEmpresa(){
		Integer idCadastrador = Cadastrador.MATRICULA_CADASTRADOR_AUTOMATICO;
		Empresa empresa = null;
			
		FiltroCadastrador filtroCadastrador = new FiltroCadastrador();
		filtroCadastrador.adicionarParametro(new ParametroSimples(FiltroCadastrador.MATRICULA, idCadastrador));
		
		filtroCadastrador.adicionarCaminhoParaCarregamentoEntidade(FiltroCadastrador.EMPRESA);
		
		Collection<Cadastrador> colCadastrador = Fachada.getInstancia().pesquisar(filtroCadastrador, Cadastrador.class.getName());
		
		if(!Util.isVazioOrNulo(colCadastrador)){
			Cadastrador cadastrador = (Cadastrador) Util.retonarObjetoDeColecao(colCadastrador);
			empresa = cadastrador.getEmpresa();
		}
	
		return empresa;
	}
	
	/**
	 * Preenche os dados do formulario de acordo com o imovel pesquisado
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2012
	 * 
	 * @return void
	 */	
	private void montarFormImovel(Imovel imovel, EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form){
		
		String matriculaImovel = form.getMatriculaImovel();
			
		form.setPerfil(""+imovel.getImovelPerfil().getId());
		form.setDescricaoPerfil(imovel.getImovelPerfil().getDescricao());
		
		form.setLote(""+imovel.getLote());
		form.setSubLote(""+imovel.getSubLote());
		
		if(imovel.getEnderecoReferencia() != null){
			form.setReferencia(""+imovel.getEnderecoReferencia().getId());
			form.setDescricaoReferencia(imovel.getEnderecoReferencia().getDescricao());
		}
		
		form.setNumero(imovel.getNumeroImovel());
		form.setComplemento(imovel.getComplementoEndereco());
		
		if(imovel.getNumeroMorador() != null){
			form.setNumeroMoradores(""+imovel.getNumeroMorador());
		}
		
		form.setMedidorCelpe(imovel.getNumeroMedidorEnergia());
		
		form.setPavimentoCalcada(""+imovel.getPavimentoCalcada().getId()); 
		form.setDescricaoPavimentoCalcada(imovel.getPavimentoCalcada().getDescricao());
		
		form.setPavimentoRua(""+imovel.getPavimentoRua().getId());
		form.setDescricaoPavimentoRua(imovel.getPavimentoRua().getDescricao());
		
		if(imovel.getFonteAbastecimento() != null){
			form.setFonteAbastecimento(""+imovel.getFonteAbastecimento().getId());
			form.setDescricaoFonteAbastecimento(imovel.getFonteAbastecimento().getDescricao());
		}
		
		Collection<ImovelSubcategoria> colecaoImovelSubcategoria = 
			this.getFachada().pesquisarCategoriasImovel(imovel.getId());
		
		if(colecaoImovelSubcategoria != null && !colecaoImovelSubcategoria.isEmpty()){
			Iterator iteratorColecaoImovelSubcategoria = colecaoImovelSubcategoria.iterator();
			
			
			int tamanho = 0;
			while (iteratorColecaoImovelSubcategoria.hasNext()) {
				
				tamanho++;
				
				ImovelSubcategoria imovelSubcategoria = 
					(ImovelSubcategoria) iteratorColecaoImovelSubcategoria.next();
				
				switch (tamanho) {
				case 1:
					form.setSubCategoria1(""+imovelSubcategoria.getComp_id().getSubcategoria().getId());
					form.setNumeroEconomias1(""+imovelSubcategoria.getQuantidadeEconomias());
					break;
				case 2:
					form.setSubCategoria2(""+imovelSubcategoria.getComp_id().getSubcategoria().getId());
					form.setNumeroEconomias2(""+imovelSubcategoria.getQuantidadeEconomias());
					break;
				case 3:
					form.setSubCategoria3(""+imovelSubcategoria.getComp_id().getSubcategoria().getId());
					form.setNumeroEconomias3(""+imovelSubcategoria.getQuantidadeEconomias());
					break;
				case 4:
					form.setSubCategoria4(""+imovelSubcategoria.getComp_id().getSubcategoria().getId());
					form.setNumeroEconomias4(""+imovelSubcategoria.getQuantidadeEconomias());
					break;
				case 5:
					form.setSubCategoria5(""+imovelSubcategoria.getComp_id().getSubcategoria().getId());
					form.setNumeroEconomias5(""+imovelSubcategoria.getQuantidadeEconomias());
					break;
				case 6:
					form.setSubCategoria6(""+imovelSubcategoria.getComp_id().getSubcategoria().getId());
					form.setNumeroEconomias6(""+imovelSubcategoria.getQuantidadeEconomias());
					break;						

				default:
					break;
				}
				
			}
		}

		if(imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null){
			
			Hidrometro hidrometroImovel = 
				imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro();
			
			form.setIndicadorHidrometro(ConstantesSistema.SIM.toString());
			form.setNumeroHidrometro(hidrometroImovel.getNumero());
			form.setAnoFabricacao(""+hidrometroImovel.getAnoFabricacao());
			
			form.setMarca(hidrometroImovel.getHidrometroMarca().getDescricao());
			form.setCapacidade(hidrometroImovel.getHidrometroCapacidade().getDescricao());
			
			form.setLocalInstalacao(""+imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometroLocalInstalacao().getId());
			form.setDescricaoLocalInstalacao(imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometroLocalInstalacao().getDescricao());
			
			form.setProtecao(""+imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometroProtecao().getId());
			form.setDescricaoProtecao(imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometroProtecao().getDescricao());
			
			form.setCavalete(""+imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getIndicadorExistenciaCavalete());
		}else{
			form.setIndicadorHidrometro(ConstantesSistema.NAO.toString());
		}
		
		
		
		form.setSituacaoAgua(""+imovel.getLigacaoAguaSituacao().getId());
		form.setDescricaoSituacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
		form.setSituacaoEsgoto(""+imovel.getLigacaoEsgotoSituacao().getId());
		form.setDescricaoSituacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
		
		
        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        
        //Objetos que serão retornados pelo Hibernate
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.pessoaSexo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteFones");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
               
        filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
        filtroClienteImovel.adicionarParametro(new ParametroSimples(
                FiltroClienteImovel.IMOVEL_ID, new Integer(matriculaImovel)));
        
        Collection imoveisCliente = 
        	this.getFachada().pesquisar(filtroClienteImovel,
                ClienteImovel.class.getName());
        
        if(imoveisCliente != null && !imoveisCliente.isEmpty()){
        	Iterator iteratorClientes = imoveisCliente.iterator();
        	boolean jahAchouProprietario = false;
        	while (iteratorClientes.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) iteratorClientes.next();
				if(clienteImovel.getClienteRelacaoTipo().getId().intValue() == ClienteRelacaoTipo.USUARIO.intValue()){
				
					Cliente clienteUsuario = clienteImovel.getCliente();
					
					String documentoUsuario = "";
					if(clienteUsuario.getCpf() != null && !clienteUsuario.getCpf().equals("")){
						documentoUsuario = clienteUsuario.getCpf();
					}else if(clienteUsuario.getCnpj() != null && !clienteUsuario.getCnpj().equals("")){
						documentoUsuario = clienteUsuario.getCnpj();
					}
					
					form.setDocumentoClienteUsuario(documentoUsuario);
					form.setNomeClienteUsuario(clienteUsuario.getNomeClienteReceitaFederal());
					
					if(clienteUsuario.getPessoaSexo() != null){
						form.setSexoClienteUsuario(""+clienteUsuario.getPessoaSexo().getId());
					}
					
					form.setRgClienteUsuario(clienteUsuario.getRg());
					
					if(clienteUsuario.getUnidadeFederacao() != null){
						form.setUfClienteUsuario(clienteUsuario.getUnidadeFederacao().getSigla());
					}
					
					Collection<ClienteFone> colecaoClienteFone = 
							this.getFachada().pesquisarClienteFone(clienteUsuario.getId());
						
					if (colecaoClienteFone != null && 
						!colecaoClienteFone.isEmpty()) {
						
						Iterator iClienteFone = colecaoClienteFone.iterator();
						
						while (iClienteFone.hasNext()) {
							ClienteFone clienteFone = (ClienteFone) iClienteFone.next();
							
							if (clienteFone.getIndicadorTelefonePadrao().equals(ClienteFone.INDICADOR_FONE_PADRAO)) {
								
								form.setDddFoneClienteUsuario(clienteFone.getDdd());
								form.setFoneClienteUsuario(clienteFone.getTelefone());
								form.setFoneTipoClienteUsuario(""+clienteFone.getFoneTipo().getId());
								form.setDescricaoFoneTipoClienteUsuario(clienteFone.getFoneTipo().getDescricao());
								
								break;
							}
						}
					}						
					
					
				}else if(jahAchouProprietario == false && 
					clienteImovel.getClienteRelacaoTipo().getId().intValue() == ClienteRelacaoTipo.PROPRIETARIO.intValue()){
					
					Cliente clienteProprietario = clienteImovel.getCliente();
					
					String documentoProprietario = "";
					if(clienteProprietario.getCpf() != null && !clienteProprietario.getCpf().equals("")){
						documentoProprietario = clienteProprietario.getCpfFormatado();
					}else if(clienteProprietario.getCnpj() != null && !clienteProprietario.getCnpj().equals("")){
						documentoProprietario = clienteProprietario.getCnpjFormatado();
					}
					
					form.setDocumentoClienteProprietario(documentoProprietario);
					form.setNomeClienteProprietario(clienteProprietario.getNomeClienteReceitaFederal());
					
					if(clienteProprietario.getPessoaSexo() != null){
						form.setSexoClienteProprietario(""+clienteProprietario.getPessoaSexo().getId());
					}
					
					form.setRgClienteProprietario(clienteProprietario.getRg());
					
					if(clienteProprietario.getUnidadeFederacao() != null){
						form.setUfClienteProprietario(clienteProprietario.getUnidadeFederacao().getSigla());
					}
					
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
					filtroClienteEndereco.adicionarParametro(
						new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID, 
							clienteProprietario.getId()));
					
					filtroClienteEndereco.adicionarParametro(
						new ParametroSimples(FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA, 
							ConstantesSistema.SIM));
					

					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.logradouro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
					
					Collection<ClienteEndereco> colecaoClienteEndereco = 
						this.getFachada().pesquisar(filtroClienteEndereco, 
							ClienteEndereco.class.getName());
					
					if(colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
						ClienteEndereco clienteEndereco = 
							(ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);
						
						form.setEnderecoClienteProprietario(clienteEndereco.getEnderecoFormatadoAbreviado());
						form.setComplementoClienteProprietario(clienteEndereco.getComplemento());
						form.setBairroClienteProprietario(clienteEndereco.getLogradouroBairro().getBairro().getNome());
						form.setMunicipioClienteProprietario(clienteEndereco.getLogradouroBairro().getLogradouro().getMunicipio().getNome());
						form.setCepClienteProprietario(clienteEndereco.getLogradouroCep().getCep().getCepFormatado());
					}

					Collection<ClienteFone> colecaoClienteFone = 
						this.getFachada().pesquisarClienteFone(clienteProprietario.getId());
					
					if (colecaoClienteFone != null && 
						!colecaoClienteFone.isEmpty()) {
						
						Iterator iClienteFone = colecaoClienteFone.iterator();
						
						while (iClienteFone.hasNext()) {
							ClienteFone clienteFone = (ClienteFone) iClienteFone.next();
							
							if (clienteFone.getIndicadorTelefonePadrao().equals(ClienteFone.INDICADOR_FONE_PADRAO)) {
								
								form.setDddFoneClienteProprietario(clienteFone.getDdd());
								form.setFoneClienteProprietario(clienteFone.getTelefone());
								form.setFoneTipoClienteProprietario(""+clienteFone.getFoneTipo().getId());
								form.setDescricaoFoneTipoClienteProprietario(clienteFone.getFoneTipo().getDescricao());

								break;
							}
						}
					}
					
					
					jahAchouProprietario = true;
					//request.setAttribute("nomeCampo", "cadastrador");
					
				}
			}
        }
	}
	
	/**
	 * Preenche os dados do formulario de acordo com o imovel pesquisado
	 * 
	 * @author Anderson Cabral
	 * @date 23/05/2013
	 * 
	 * @param ImovelAtualizacaoCadastral
	 * @param EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm
	 * @return void
	 */	
	private void montarFormImovelAtuCadastral(ImovelAtualizacaoCadastral imovelAtuCadastral, EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm form, HttpServletRequest request){
		
		//id Imovel Atualizacao Cadastral
		form.setIdImovelAC(imovelAtuCadastral.getId().toString());
		
        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        
        //Cliente PROPRIETARIO
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.pessoaSexo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteFones");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
               
        filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovelAtuCadastral.getImovel()));
        
        Collection<ClienteImovel> imoveisCliente = this.getFachada().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());
        
        if(imoveisCliente != null && !imoveisCliente.isEmpty()){
        	Iterator<ClienteImovel> iteratorClientes = imoveisCliente.iterator();

        	while (iteratorClientes.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) iteratorClientes.next();

				if(clienteImovel.getClienteRelacaoTipo().getId().intValue() == ClienteRelacaoTipo.PROPRIETARIO.intValue()){
						
					Cliente clienteProprietario = clienteImovel.getCliente();
					
					String documentoProprietario = "";
					if(clienteProprietario.getCpf() != null && !clienteProprietario.getCpf().equals("")){
						documentoProprietario = clienteProprietario.getCpfFormatado();
					}else if(clienteProprietario.getCnpj() != null && !clienteProprietario.getCnpj().equals("")){
						documentoProprietario = clienteProprietario.getCnpjFormatado();
					}
					
					form.setDocumentoClienteProprietario(documentoProprietario);
					form.setNomeClienteProprietario(clienteProprietario.getNomeClienteReceitaFederal());
					
					if(clienteProprietario.getPessoaSexo() != null){
						form.setSexoClienteProprietario(""+clienteProprietario.getPessoaSexo().getId());
					}
					
					form.setRgClienteProprietario(clienteProprietario.getRg());
					
					if(clienteProprietario.getUnidadeFederacao() != null){
						form.setUfClienteProprietario(clienteProprietario.getUnidadeFederacao().getSigla());
					}
					
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
					filtroClienteEndereco.adicionarParametro(
						new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID, 
							clienteProprietario.getId()));
					
					filtroClienteEndereco.adicionarParametro(
						new ParametroSimples(FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA, 
							ConstantesSistema.SIM));
					
		
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.logradouro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
					
					Collection<ClienteEndereco> colecaoClienteEndereco = 
						this.getFachada().pesquisar(filtroClienteEndereco, 
							ClienteEndereco.class.getName());
					
					if(colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
						ClienteEndereco clienteEndereco = 
							(ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);
						
						form.setEnderecoClienteProprietario(clienteEndereco.getEnderecoFormatadoAbreviado());
						form.setComplementoClienteProprietario(clienteEndereco.getComplemento());
						form.setBairroClienteProprietario(clienteEndereco.getLogradouroBairro().getBairro().getNome());
						form.setMunicipioClienteProprietario(clienteEndereco.getLogradouroBairro().getLogradouro().getMunicipio().getNome());
						form.setCepClienteProprietario(clienteEndereco.getLogradouroCep().getCep().getCepFormatado());
					}
		
					Collection<ClienteFone> colecaoClienteFone = 
						this.getFachada().pesquisarClienteFone(clienteProprietario.getId());
					
					if (colecaoClienteFone != null && 
						!colecaoClienteFone.isEmpty()) {
						
						Iterator iClienteFone = colecaoClienteFone.iterator();
						
						while (iClienteFone.hasNext()) {
							ClienteFone clienteFone = (ClienteFone) iClienteFone.next();
							
							if (clienteFone.getIndicadorTelefonePadrao().equals(ClienteFone.INDICADOR_FONE_PADRAO)) {
								
								form.setDddFoneClienteProprietario(clienteFone.getDdd());
								form.setFoneClienteProprietario(clienteFone.getTelefone());
								form.setFoneTipoClienteProprietario(""+clienteFone.getFoneTipo().getId());
								form.setDescricaoFoneTipoClienteProprietario(clienteFone.getFoneTipo().getDescricao());
		
								break;
							}
						}
					}
				}
        	}
		}
					
		//Cliente USUARIO Atualizacao Cadastral
		FiltroClienteAtualizacaoCadastral filtroClienteAtuCadastral = new FiltroClienteAtualizacaoCadastral();
		filtroClienteAtuCadastral.adicionarParametro(new ParametroNulo(FiltroClienteAtualizacaoCadastral.DATA_FIM_RELACAO));
		filtroClienteAtuCadastral.adicionarParametro(new ParametroSimples(FiltroClienteAtualizacaoCadastral.ID_IMOVEL_ATUALIZACAO_CADASTRAL, imovelAtuCadastral.getId()));
		       
        Collection<ClienteAtualizacaoCadastral> colecaoClienteAtuCadastral = this.getFachada().pesquisar(filtroClienteAtuCadastral, ClienteAtualizacaoCadastral.class.getName());
        
        if(colecaoClienteAtuCadastral != null && !colecaoClienteAtuCadastral.isEmpty()){
        	Iterator<ClienteAtualizacaoCadastral> iteratorClienteAtuCadastral = colecaoClienteAtuCadastral.iterator();

        	while (iteratorClienteAtuCadastral.hasNext()) {
        		ClienteAtualizacaoCadastral clienteAtuCadastral = (ClienteAtualizacaoCadastral) iteratorClienteAtuCadastral.next();
				if(clienteAtuCadastral.getIdClienteRelacaoTipo().intValue() == ClienteRelacaoTipo.USUARIO.intValue()){
					
					//Id cliente usuario
					form.setIdClienteUsuarioAC(clienteAtuCadastral.getId().toString());
					
					//CPF ou CNPJ
					form.setDocumentoClienteUsuario(clienteAtuCadastral.getCpfCnpj());
					
					//Nome Cliente
					form.setNomeClienteUsuario(clienteAtuCadastral.getNomeCliente());
					
					//Sexo
					if(clienteAtuCadastral.getSexoFormatado() != null){
						if(clienteAtuCadastral.getSexoFormatado().equalsIgnoreCase("MASCULINO")){
							form.setSexoClienteUsuario("1");
						}else if(clienteAtuCadastral.getSexoFormatado().equalsIgnoreCase("FEMININO")){
							form.setSexoClienteUsuario("2");
						}
					}
					
					//RG
					form.setRgClienteUsuario(clienteAtuCadastral.getRg());
					
					//UF
					if(clienteAtuCadastral.getDsUFSiglaOrgaoExpedidorRg() != null){
						form.setUfClienteUsuario(clienteAtuCadastral.getDsUFSiglaOrgaoExpedidorRg());
					}
					
					//Cliente Fone Atualizacao Cadastral
					FiltroClienteFoneAtualizacaoCadastral filtroClienteFoneAtuCadastral = new FiltroClienteFoneAtualizacaoCadastral();
					filtroClienteFoneAtuCadastral.adicionarParametro(new ParametroSimples(FiltroClienteFoneAtualizacaoCadastral.ID_CLIENTE_ATUALIZACAO_CADASTRAL, clienteAtuCadastral.getId()));
					Collection<ClienteFoneAtualizacaoCadastral> colecaoClienteFoneAtuCadastral = this.getFachada().pesquisar(filtroClienteFoneAtuCadastral, ClienteFoneAtualizacaoCadastral.class.getName());
						
					if (colecaoClienteFoneAtuCadastral != null && 
						!colecaoClienteFoneAtuCadastral.isEmpty()) {
						
						Iterator<ClienteFoneAtualizacaoCadastral> iClienteFoneAtuCadastral = 
																		colecaoClienteFoneAtuCadastral.iterator();
						
						while (iClienteFoneAtuCadastral.hasNext()) {
							ClienteFoneAtualizacaoCadastral clienteFoneAtuCadastral = (ClienteFoneAtualizacaoCadastral) iClienteFoneAtuCadastral.next();
							
							if (clienteFoneAtuCadastral.getIndicadorFonePadrao().equals(ClienteFoneAtualizacaoCadastral.INDICADOR_FONE_PADRAO)) {
								
								//Id Cliente Fone
								form.setIdClienteFoneAC(clienteFoneAtuCadastral.getId().toString());
								
								//DDD
								form.setDddFoneClienteUsuario(clienteFoneAtuCadastral.getDdd());
								
								//Numero
								form.setFoneClienteUsuario(clienteFoneAtuCadastral.getTelefone());
								
								//Fone Tipo
								if(clienteFoneAtuCadastral.getIdFoneTipo() != null && !clienteFoneAtuCadastral.getIdFoneTipo().equals("")){
									FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
									filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.ID, clienteFoneAtuCadastral.getIdFoneTipo()));
									Collection<FoneTipo> colecaoFoneTipo = this.getFachada().pesquisar(filtroFoneTipo, FoneTipo.class.getName());
									
									FoneTipo foneTipo = (FoneTipo) Util.retonarObjetoDeColecao(colecaoFoneTipo);
									
									form.setFoneTipoClienteUsuario(""+foneTipo.getId());
									form.setDescricaoFoneTipoClienteUsuario(foneTipo.getDescricao());
								}
								
								break;
							}
						}
					}						
				}
        	}
        }
		//Lote
		form.setLote(""+imovelAtuCadastral.getLote());
		
		//SubLote
		form.setSubLote(""+imovelAtuCadastral.getSubLote());
		
		//Imovel Perfil
		if(imovelAtuCadastral.getIdImovelPerfil() != null 
				&& !imovelAtuCadastral.getIdImovelPerfil().equals("")){
			
			form.setPerfil(""+imovelAtuCadastral.getIdImovelPerfil());			
			ImovelPerfil imovelPerfil = this.getFachada().pesquisarImovelPerfil(imovelAtuCadastral.getIdImovelPerfil());
			
			if(imovelPerfil != null && imovelPerfil.getDescricao() != null){
				form.setDescricaoPerfil(imovelPerfil.getDescricao());
			}
		}
		
		//Analisar Tarifa Social
		if(imovelAtuCadastral.getIndicadorAlertaTarifaSocial() != null 
				&& imovelAtuCadastral.getIndicadorAlertaTarifaSocial().equals(ConstantesSistema.SIM)){
			form.setAnalisarTarifaSocial("1");
		}else{
			form.setAnalisarTarifaSocial("2");
		}
		
		//Logradouro
		if(imovelAtuCadastral.getIdLogradouro() != null){
			form.setLogradouro(imovelAtuCadastral.getIdLogradouro().toString());
			
			pesquisarCep(form, request);
		}
		
		//CEP
		if(imovelAtuCadastral.getCodigoCep() != null){
			FiltroCep filtroCep = new FiltroCep();
			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO, imovelAtuCadastral.getCodigoCep().toString()));
			Collection<Cep> colecaoCeps = getFachada().pesquisar(filtroCep, Cep.class.getName());
			
			Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCeps);
			
			if(cep != null && cep.getCepId() != null){
				form.setCep(cep.getCepId().toString());
			}
		}
		
		//Endereco Referencia
		if(imovelAtuCadastral.getIdEnderecoReferencia() != null){
			FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia(); 
			filtroEnderecoReferencia.adicionarParametro(new ParametroSimples(
					FiltroEnderecoReferencia.ID, new Integer(imovelAtuCadastral.getIdEnderecoReferencia())));
			
			Collection<EnderecoReferencia> colecaoEnderecoRef = 
					this.getFachada().pesquisar(filtroEnderecoReferencia, EnderecoReferencia.class.getName());
			
			EnderecoReferencia enderecoRef = (EnderecoReferencia) Util.retonarObjetoDeColecao(colecaoEnderecoRef);
			
			if(enderecoRef != null){
				form.setReferencia(""+enderecoRef.getId());
				form.setDescricaoReferencia(enderecoRef.getDescricao());
			}	
		}
			
		//Numero
		form.setNumero(imovelAtuCadastral.getNumeroImovel());
		
		//Complemento
		form.setComplemento(imovelAtuCadastral.getComplementoEndereco());
		
		//Imovel Subcategoria Atualizacao Cadastral
		Collection<ImovelSubcategoriaAtualizacaoCadastral> colecaoImovelSubcategoriaAC = 			
				this.getFachada().pesquisarSubCategoriaAtualizacaoCadastral(imovelAtuCadastral.getId());
		
		if(colecaoImovelSubcategoriaAC != null && !colecaoImovelSubcategoriaAC.isEmpty()){
			Iterator<ImovelSubcategoriaAtualizacaoCadastral> iteratorColecaoImovelSubcategoriaAC = colecaoImovelSubcategoriaAC.iterator();
			
			
			int tamanho = 0;
			while (iteratorColecaoImovelSubcategoriaAC.hasNext()) {
				
				tamanho++;
				
				ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAC = 
					(ImovelSubcategoriaAtualizacaoCadastral) iteratorColecaoImovelSubcategoriaAC.next();
				
				FiltroSubCategoria filtroSubCategoria = new  FiltroSubCategoria(); 
				filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, imovelSubcategoriaAC.getIdSubcategoria()));
				
				Collection<Subcategoria> colecaoSubcategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				
				Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubcategoria);
				
				switch (tamanho) {
				case 1:
					form.setIdImovelSubCategoria1(imovelSubcategoriaAC.getId().toString());
					form.setSubCategoria1(""+subcategoria.getId());
					form.setNumeroEconomias1(""+imovelSubcategoriaAC.getQuantidadeEconomias());
					break;
				case 2:
					form.setIdImovelSubCategoria2(imovelSubcategoriaAC.getId().toString());
					form.setSubCategoria2(""+subcategoria.getId());
					form.setNumeroEconomias2(""+imovelSubcategoriaAC.getQuantidadeEconomias());
					break;
				case 3:
					form.setIdImovelSubCategoria3(imovelSubcategoriaAC.getId().toString());
					form.setSubCategoria3(""+subcategoria.getId());
					form.setNumeroEconomias3(""+imovelSubcategoriaAC.getQuantidadeEconomias());
					break;
				case 4:
					form.setIdImovelSubCategoria4(imovelSubcategoriaAC.getId().toString());
					form.setSubCategoria4(""+subcategoria.getId());
					form.setNumeroEconomias4(""+imovelSubcategoriaAC.getQuantidadeEconomias());
					break;
				case 5:
					form.setIdImovelSubCategoria5(imovelSubcategoriaAC.getId().toString());
					form.setSubCategoria5(""+subcategoria.getId());
					form.setNumeroEconomias5(""+imovelSubcategoriaAC.getQuantidadeEconomias());
					break;
				case 6:
					form.setIdImovelSubCategoria6(imovelSubcategoriaAC.getId().toString());
					form.setSubCategoria6(""+subcategoria.getId());
					form.setNumeroEconomias6(""+imovelSubcategoriaAC.getQuantidadeEconomias());
					break;						

				default:
					break;
				}
				
			}
		}
		
		
		//Numero Morador
		if(imovelAtuCadastral.getNumeroMorador() != null){
			form.setNumeroMoradores(""+imovelAtuCadastral.getNumeroMorador());
		}
		
		//Medidor Celpe
		form.setMedidorCelpe(imovelAtuCadastral.getNumeroMedidirEnergia());
		
		//Pavimento Rua
		if(imovelAtuCadastral.getIdPavimentoRua() != null 
				&& !imovelAtuCadastral.getIdPavimentoRua().equals("")){
			
			FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
			filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID, 
					imovelAtuCadastral.getIdPavimentoRua()));
			
			Collection<PavimentoRua> colecaoPavimentoRua = 
					this.getFachada().pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());
			
			PavimentoRua pavimentoRua = (PavimentoRua) Util.retonarObjetoDeColecao(colecaoPavimentoRua);
			
			if(pavimentoRua != null){
				form.setPavimentoRua(""+pavimentoRua.getId()); 
				form.setDescricaoPavimentoRua(pavimentoRua.getDescricao());
			}
		}
				
		//Pavimento Calcada
		if(imovelAtuCadastral.getIdPavimentoCalcada() != null 
				&& !imovelAtuCadastral.getIdPavimentoCalcada().equals("")){
			
			FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
			filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.ID, 
					imovelAtuCadastral.getIdPavimentoCalcada()));
			
			Collection<PavimentoCalcada> colecaoPavimentoCalcada = 
					this.getFachada().pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
			
			PavimentoCalcada pavimentoCalcada = (PavimentoCalcada) Util.retonarObjetoDeColecao(colecaoPavimentoCalcada);
			
			if(pavimentoCalcada != null){
				form.setPavimentoCalcada(""+pavimentoCalcada.getId()); 
				form.setDescricaoPavimentoCalcada(pavimentoCalcada.getDescricao());
			}
		}
		
		//Fonte Abastecimento
		if(imovelAtuCadastral.getIdFonteAbastecimento() != null
				&& !imovelAtuCadastral.getIdFonteAbastecimento().equals("")){
			
			FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, 
					imovelAtuCadastral.getIdFonteAbastecimento()));
			
			Collection<FonteAbastecimento> colecaoFonteAbast = 
					this.getFachada().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
			
			FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(colecaoFonteAbast);
			
			if(fonteAbastecimento != null){
				form.setFonteAbastecimento(""+fonteAbastecimento.getId());
				form.setDescricaoFonteAbastecimento(fonteAbastecimento.getDescricao());
			}
		}
		
		//Situacao Ligacao Agua
		if(imovelAtuCadastral.getIdLigacaoAguaSituacao() != null 
				&& !imovelAtuCadastral.getIdLigacaoAguaSituacao().equals("")){
			
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, imovelAtuCadastral.getIdLigacaoAguaSituacao()));
			
			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao 
					= this.getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			
			LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
			
			if(ligacaoAguaSituacao != null){
				form.setSituacaoAgua(""+ligacaoAguaSituacao.getId());
				form.setDescricaoSituacaoAgua(ligacaoAguaSituacao.getDescricao());
			}
		}
		
		//Situacao Ligacao Esgoto
		if(imovelAtuCadastral.getIdLigacaoEsgotoSituacao() != null 
				&& !imovelAtuCadastral.getIdLigacaoEsgotoSituacao().equals("")){
			
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, imovelAtuCadastral.getIdLigacaoEsgotoSituacao()));
			
			Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao 
					= this.getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
			
			if(ligacaoEsgotoSituacao != null){
				form.setSituacaoEsgoto(""+ligacaoEsgotoSituacao.getId());
				form.setDescricaoSituacaoEsgoto(ligacaoEsgotoSituacao.getDescricao());
			}
		}
		
		//Hidrometro Instalacao Historico Atualizacao Cadastral
		FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral filtroHidrometroInstHistAtuCadastral 
				= new FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral();
		filtroHidrometroInstHistAtuCadastral.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral.HIDROMETRO_LOCAL_INSTALACAO);
		filtroHidrometroInstHistAtuCadastral.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral.HIDROMETRO_PROTECAO);
		filtroHidrometroInstHistAtuCadastral.adicionarParametro(new ParametroSimples(
				FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastral.ID_IMOVEL_ATUALIZACAO_CADASTRAL, imovelAtuCadastral.getId()));
		Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral> colecaoHidrometroInstHistAtuCadastral 
				= this.getFachada().pesquisar(filtroHidrometroInstHistAtuCadastral, HidrometroInstalacaoHistoricoAtualizacaoCadastral.class.getName());
		
		HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstHistAtuCadastral 
				= (HidrometroInstalacaoHistoricoAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecaoHidrometroInstHistAtuCadastral);
		
		if(hidrometroInstHistAtuCadastral != null){
			form.setIndicadorHidrometro(ConstantesSistema.SIM.toString());
			
			//Id Hidrometro Instalacao Historico
			form.setIdHidrometroInstalacaoHistoricoAC(hidrometroInstHistAtuCadastral.getId().toString());

			//Numero Hidrometro
			form.setNumeroHidrometro(hidrometroInstHistAtuCadastral.getNumeroHidrometro());
			
			Hidrometro hidrometro = this.getFachada().pesquisarHidrometroPeloNumero(hidrometroInstHistAtuCadastral.getNumeroHidrometro());
			
			if(hidrometro != null && hidrometro.getId() != null){
				
				//Hidrometro Marca
				HidrometroMarca hidrometroMarca = hidrometro.getHidrometroMarca();
				if(hidrometroMarca != null && hidrometroMarca.getId() != null){
					form.setMarca(hidrometroMarca.getDescricao());
				}
				
				//Hirometro Capacidade
				HidrometroCapacidade hidrometroCapacidade = hidrometro.getHidrometroCapacidade();
				if(hidrometroCapacidade != null && hidrometroCapacidade.getId() != null){
					form.setCapacidade(hidrometroCapacidade.getDescricao());
				}
				
				//Ano Fabricacao
				if(hidrometro.getAnoFabricacao() != null){
					form.setAnoFabricacao(hidrometro.getAnoFabricacao().toString());
				}
			}
			
			//Local Instalacao
			form.setLocalInstalacao(""+hidrometroInstHistAtuCadastral.getHidrometroLocalInstalacao().getId());
			form.setDescricaoLocalInstalacao(hidrometroInstHistAtuCadastral.getHidrometroLocalInstalacao().getDescricao());
			
			//Tipo de Protecao
			form.setProtecao(""+hidrometroInstHistAtuCadastral.getHidrometroProtecao().getId());
			form.setDescricaoProtecao(hidrometroInstHistAtuCadastral.getHidrometroProtecao().getDescricao());
			
			//Cavalete
			if(hidrometroInstHistAtuCadastral.getIndicadorCavalete() != null){
				form.setCavalete(hidrometroInstHistAtuCadastral.getIndicadorCavalete().toString());
			}
						
			//Leitura
			if(hidrometroInstHistAtuCadastral.getNumeroInstalacaoHidrometro() != null){
				form.setLeitura(hidrometroInstHistAtuCadastral.getNumeroInstalacaoHidrometro().toString());
			}
			
		}else{
			form.setIndicadorHidrometro(ConstantesSistema.NAO.toString());
		}
		
		//Ocorrencia Cadastro
		if(imovelAtuCadastral.getIdCadastroOcorrencia() != null 
				&& !imovelAtuCadastral.getIdCadastroOcorrencia().equals("")){
	       
			Collection<CadastroOcorrencia> colecaoCadastroOcorrencia 
												= this.pesquisarOcorrencia(imovelAtuCadastral.getIdCadastroOcorrencia().toString());
	        
	        if(colecaoCadastroOcorrencia != null && !colecaoCadastroOcorrencia.isEmpty()){
	        	
	        	CadastroOcorrencia cadastroOcorrencia = 
	        		(CadastroOcorrencia) Util.retonarObjetoDeColecao(colecaoCadastroOcorrencia);
	        	
	        	form.setOcorrenciaCadastro(""+cadastroOcorrencia.getId());
	        	form.setDescricaoOcorrenciaCadastro(cadastroOcorrencia.getDescricao());		        	
	        }
		}
		
		//Observacao
		form.setObservacao(imovelAtuCadastral.getDescricaoObservacao());
		
		//Cadastrador
		if(imovelAtuCadastral.getLogin() != null 
				&& !imovelAtuCadastral.getLogin().equals("")){
			Leiturista leiturista = null;
			
			leiturista = this.getFachada().pesquisarLeituristaPorCPF(imovelAtuCadastral.getLogin());
			
			if(leiturista == null){
				leiturista = this.getFachada().pesquisarLeituristaPeloLogin(imovelAtuCadastral.getLogin());
			}
			
	        if(leiturista != null && leiturista.getId() != null && !leiturista.getId().equals("")){
	
	        	form.setCadastrador(leiturista.getUsuario().getCpf());
	        	form.setNomeCadastrador(leiturista.getUsuario().getNomeUsuario());
	        }
		}
		
		//Data Atualizacao
		form.setDataAtualizacao(Util.formatarData(imovelAtuCadastral.getDataVisita()));
		
	}
	
}