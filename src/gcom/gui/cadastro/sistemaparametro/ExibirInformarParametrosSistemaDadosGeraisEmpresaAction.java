/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.cadastro.sistemaparametro;

import gcom.atendimentopublico.ResolucaoImagem;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 04/01/2007
 */
public class ExibirInformarParametrosSistemaDadosGeraisEmpresaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
			.findForward("exibirInformarParametrosSistemaDadosGeraisEmpresa");
	
		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;
	
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");
		
		// Seta os dados no form
		if (httpServletRequest.getParameter("menu") != null) {
		
			this.pesquisarEndereco(sistemaParametro, httpServletRequest);
			
			if (sistemaParametro
					.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() != null) {
				form.setClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
						.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados()
						.getId().toString());
		
				form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
						.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados()
						.getDescricao());
		
				httpServletRequest.setAttribute("clienteFicticioEncontrado",
						true);
			}
		
			if (sistemaParametro.getClienteUsuarioDesconhecido() != null) {
				form.setClienteUsuarioDesconhecido(sistemaParametro
						.getClienteUsuarioDesconhecido().getId().toString());
		
				form.setNomeClienteUsuarioDesconhecido(sistemaParametro
						.getClienteUsuarioDesconhecido().getDescricao());
		
				httpServletRequest.setAttribute(
						"clienteUsuarioDesconhecido", true);
			}
			
			if (sistemaParametro.getClienteResponsavelProgramaEspecial() != null) {
				form.setIdClienteResponsavelProgramaEspecial(sistemaParametro
						.getClienteResponsavelProgramaEspecial().getId()
						.toString());
		
				form.setNomeClienteResponsavelProgramaEspecial(sistemaParametro
						.getClienteResponsavelProgramaEspecial().getDescricao());
		
				httpServletRequest.setAttribute(
						"clienteResponsavelProgramaEspecial", true);
			}
			
		}
		
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("1")) {
		
			// Faz a consulta de Unidade Organizacional
			this.pesquisarUnidadeOrganizacional(form, httpServletRequest);
		}

		// Consulta de Presidente / Diretor Comercial / Diretor Gest�o
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("3") || objetoConsulta.trim().equals("10"))) {
			this.pesquisarCliente(form, objetoConsulta, httpServletRequest);
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("7")) {
		
			// Faz a consulta de Cliente - Cliente Fict�cio
			this.pesquisarClienteFicticio(form, httpServletRequest);
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("8")) {
		
			// Faz a consulta de Cliente - Cliente Fict�cio
			this.pesquisarClienteUsuarioDesconhecido(form, httpServletRequest);
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("9")) {
		
			// Faz a consulta de Cliente - Cliente Respons�vel programa especial
			this.pesquisarClientePrograma(form, httpServletRequest);
		}
		
		form.setIndicadorCpfCnpj(""
				+ sistemaParametro.getIndicadorConsultaDocumentoReceita());
		
		form.setIndicadorDocumentoObrigatorio(""
				+ sistemaParametro.getIndicadorDocumentoObrigatorio());
		
		this.setaRequest(httpServletRequest, form);
		this.montarEndereco(form, httpServletRequest);
		this.carregarColecaoPerfisImovel(form, httpServletRequest);
		
		return retorno;
	}
	
	/**
	* Pesquisa Endereco
	* 
	* @author Rafael Pinto
	* @date 30/07/2008
	*/
	private void pesquisarEndereco(SistemaParametro sistemaParametro,
		HttpServletRequest httpServletRequest) {
	
		if (this.getSessao(httpServletRequest).getAttribute("colecaoEnderecos") == null) {
		
			Imovel imovel = new Imovel();
		
			// Pesquisa o Logradouro Cep
			if (sistemaParametro.getLogradouroCep() != null) {
				FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(
						FiltroLogradouroCep.ID, sistemaParametro
								.getLogradouroCep().getId()));
		
				filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");
				filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");
				filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("cep");
		
				Collection colecaoLogradouroCep = this.getFachada().pesquisar(
						filtroLogradouroCep, LogradouroCep.class.getName());
		
				LogradouroCep logradouroCep = (LogradouroCep) Util
						.retonarObjetoDeColecao(colecaoLogradouroCep);
		
				imovel.setLogradouroCep(logradouroCep);
			}
		
			// Pesquisa o Logradouro Bairro
			if (sistemaParametro.getLogradouroBairro() != null) {
				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
						FiltroLogradouroBairro.ID, sistemaParametro
								.getLogradouroBairro().getId()));
		
				filtroLogradouroBairro
						.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio.unidadeFederacao");
		
				Collection colecaoLogradouroBairro = this.getFachada()
						.pesquisar(filtroLogradouroBairro,
								LogradouroBairro.class.getName());
		
				LogradouroBairro logradouroBairro = (LogradouroBairro) Util
						.retonarObjetoDeColecao(colecaoLogradouroBairro);
		
				imovel.setLogradouroBairro(logradouroBairro);
		
			}
		
			imovel.setEnderecoReferencia(sistemaParametro
					.getEnderecoReferencia());
			imovel.setNumeroImovel(sistemaParametro.getNumeroImovel());
			imovel.setComplementoEndereco(sistemaParametro
					.getComplementoEndereco());
		
			Collection colecaoEndereco = new ArrayList();
			colecaoEndereco.add(imovel);
		
			this.getSessao(httpServletRequest).setAttribute("colecaoEnderecos",
					colecaoEndereco);
		}
	}
	
	/**
	* Monta o Endereco
	* 
	* @author Arthur Carvalho
	* @date 22/07/2008
	*/
	private void montarEndereco(InformarSistemaParametrosActionForm form,
		HttpServletRequest httpServletRequest) {
	
		// Removendo endere�o
		String removerEndereco = httpServletRequest
				.getParameter("removerEndereco");
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		if (removerEndereco != null
				&& !removerEndereco.trim().equalsIgnoreCase("")) {
		
			if (sessao.getAttribute("colecaoEnderecos") != null) {
		
				Collection enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");
		
				if (!enderecos.isEmpty()) {
					sessao.removeAttribute("colecaoEnderecos");
				}
			}
		}
	
		// Caso tenha adicionado o endere�o seta os valores dos campos de
		// municipio e bairro
		if (sessao.getAttribute("colecaoEnderecos") != null) {
		
			Collection colecaoEnderecos = (Collection) sessao
					.getAttribute("colecaoEnderecos");
		
			if (!colecaoEnderecos.isEmpty()) {
		
				Imovel imovel = (Imovel) Util
						.retonarObjetoDeColecao(colecaoEnderecos);
		
				if (imovel.getLogradouroBairro() != null) {
					form.setLogradouroBairro(imovel.getLogradouroBairro()
							.getId().toString());
				}
		
				if (imovel.getLogradouroBairro() != null) {
					form.setLogradouroCep(imovel.getLogradouroCep().getId()
							.toString());
				}
				if (imovel.getEnderecoReferencia() != null) {
					form.setEnderecoReferencia(imovel.getEnderecoReferencia()
							.getId().toString());
				}
				form.setNumero(imovel.getNumeroImovel());
				form.setComplemento(imovel.getComplementoEndereco());
		
			}
		
		}
	}
	
	/**
	* Pesquisa Unidade Organizacional
	* 
	* @author Rafael Pinto
	* @date 17/07/2008
	*/
	private void pesquisarUnidadeOrganizacional(
		InformarSistemaParametrosActionForm form,
		HttpServletRequest httpServletRequest) {
		
		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		String idUnidade = form.getUnidadeOrganizacionalPresidencia();
		
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());
		
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
		
			// Obt�m o objeto da cole��o pesquisada
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);
		
			// Exibe o c�digo e a descri��o pesquisa na p�gina
			httpServletRequest.setAttribute(
					"unidadeOrganizacionalPresidenciaInexistente", "true");
		
			form.setUnidadeOrganizacionalPresidencia(unidadeOrganizacional
					.getId().toString());
			form.setNomeUnidadeOrganizacionalPresidencia(unidadeOrganizacional
					.getDescricao());
		
		} else {
		
			form.setNomeUnidadeOrganizacionalPresidencia("Unidade Organizacional inexistente");
			form.setUnidadeOrganizacionalPresidencia(null);
		
		}
	}
	
	/**
	* Pesquisa Cliente
	* 
	* @author Rafael Pinto
	* @date 17/07/2006
	*/
	private void pesquisarCliente(InformarSistemaParametrosActionForm form,
		String tipoConsulta, HttpServletRequest httpServletRequest) {
	
		String codigoCliente = null;
		
		if (tipoConsulta.equals("2")) {
			codigoCliente = form.getPresidente();
		} else if (tipoConsulta.equals("3")) {
			codigoCliente = form.getDiretorComercial();
		} else {
			codigoCliente = form.getDiretorGestao();
		}
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				new Integer(codigoCliente)));
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoCliente = this.getFachada().pesquisar(filtroCliente,
				Cliente.class.getName());
		
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
		
			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoCliente);
		
			if (tipoConsulta.equals("2")) {
				httpServletRequest.setAttribute("presidenteEncontrado", "true");
		
				form.setPresidente(cliente.getId().toString());
				form.setNomePresidente(cliente.getNome());
			} else if (tipoConsulta.equals("3")) {
				httpServletRequest.setAttribute("diretorComercialEncontrado",
						"true");
		
				form.setDiretorComercial(cliente.getId().toString());
				form.setNomeDiretorComercial(cliente.getNome());
			} else {
				httpServletRequest.setAttribute("diretorGestaoEncontrado",
						"true");
		
				form.setDiretorGestao(cliente.getId().toString());
				form.setNomeDiretorGestao(cliente.getNome());
			}
		
		} else {
			if (tipoConsulta.equals("2")) {
				form.setPresidente(null);
				form.setNomePresidente("Cliente inexistente");
			} else if (tipoConsulta.equals("3")) {
				form.setDiretorComercial(null);
				form.setNomeDiretorComercial("Cliente inexistente");
			} else {
				form.setDiretorGestao(null);
				form.setNomeDiretorGestao("Cliente inexistente");
			}
		}
	}
	
	/**
	* Pesquisa Cliente Ficticio
	* 
	* @author Nathalia Santos
	* @date 12/08/2011
	*/
	private void pesquisarClienteFicticio(
		InformarSistemaParametrosActionForm form,
		HttpServletRequest httpServletRequest) {
	
		String codigoClienteFicticio = null;
		
		codigoClienteFicticio = form
				.getClienteFicticioAssociarPagamentosNaoIdentificados();
		FiltroCliente filtroClienteFicticio = new FiltroCliente();
		
		filtroClienteFicticio.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, new Integer(codigoClienteFicticio)));
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoClienteFicticio = this.getFachada().pesquisar(
				filtroClienteFicticio, Cliente.class.getName());
		
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoClienteFicticio != null && !colecaoClienteFicticio.isEmpty()) {
		
			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClienteFicticio);
		
			httpServletRequest.setAttribute("clienteFicticioEncontrado", true);
		
			form.setClienteFicticioAssociarPagamentosNaoIdentificados(cliente
					.getId().toString());
			form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(cliente
					.getNome());
		} else {
			httpServletRequest.setAttribute("clienteFicticioEncontrado", false);
			form.setClienteFicticioAssociarPagamentosNaoIdentificados(null);
			form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados("Cliente inexistente");
		}
	}
	
	/**
	* Pesquisa Cliente Usuario Desconhecido
	* 
	* @author Diego Maciel
	* @date 12/01/2012
	*/
	private void pesquisarClienteUsuarioDesconhecido(
		InformarSistemaParametrosActionForm form,
		HttpServletRequest httpServletRequest) {
	
		String codigoClienteUsuarioDesconhecido = null;
		
		codigoClienteUsuarioDesconhecido = form.getClienteUsuarioDesconhecido();
		FiltroCliente filtroClienteUsuarioDesconhecido = new FiltroCliente();
		
		filtroClienteUsuarioDesconhecido
				.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,
						new Integer(codigoClienteUsuarioDesconhecido)));
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoClienteUsuarioDesconhecido = this.getFachada()
				.pesquisar(filtroClienteUsuarioDesconhecido,
						Cliente.class.getName());
		
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoClienteUsuarioDesconhecido != null
				&& !colecaoClienteUsuarioDesconhecido.isEmpty()) {
		
			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClienteUsuarioDesconhecido);
		
			httpServletRequest.setAttribute(
					"clienteUsuarioDesconhecido", true);
		
			form.setClienteUsuarioDesconhecido(cliente.getId().toString());
			form.setNomeClienteUsuarioDesconhecido(cliente.getNome());
		} else {
			httpServletRequest
					.setAttribute("clienteUsuarioDesconhecido", false);
			form.setClienteUsuarioDesconhecido(null);
			form.setNomeClienteUsuarioDesconhecido("Cliente inexistente");
		}
	}
	
	/**
	* Seta os request com os id encontrados
	* 
	* @author Rafael Pinto
	* @date 17/07/2008
	*/
	private void setaRequest(HttpServletRequest httpServletRequest,
		InformarSistemaParametrosActionForm form) {
	
		// Unidade OrganizacionalPresidencia
		if (form.getUnidadeOrganizacionalPresidencia() != null
				&& !form.getUnidadeOrganizacionalPresidencia().equals("")
				&& form.getNomeUnidadeOrganizacionalPresidencia() != null
				&& !form.getNomeUnidadeOrganizacionalPresidencia().equals("")) {
		
			httpServletRequest.setAttribute(
					"unidadeOrganizacionalPresidenciaEncontrada", "true");
		}
		
		// Presidente
		if (form.getPresidente() != null && !form.getPresidente().equals("")
				&& form.getNomePresidente() != null
				&& !form.getNomePresidente().equals("")) {
		
			httpServletRequest.setAttribute("presidenteEncontrado", "true");
		}
		
		// Diretor Comercial
		if (form.getDiretorComercial() != null
				&& !form.getDiretorComercial().equals("")
				&& form.getNomeDiretorComercial() != null
				&& !form.getNomeDiretorComercial().equals("")) {
		
			httpServletRequest.setAttribute("diretorComercialEncontrado",
					"true");
		}
		
		// Diretor de Gest�o
		if (form.getDiretorGestao() != null
				&& !form.getDiretorGestao().equals("")
				&& form.getNomeDiretorGestao() != null
				&& !form.getNomeDiretorGestao().equals("")) {
		
			httpServletRequest.setAttribute("diretorGestaoEncontrado",
					"true");
		}
		
		// Cliente Ficticio
		if (form.getClienteFicticioAssociarPagamentosNaoIdentificados() != null
				&& !form.getClienteFicticioAssociarPagamentosNaoIdentificados()
						.equals("")
				&& form.getNomeClienteFicticioAssociarPagamentosNaoIdentificados() != null
				&& !form.getNomeClienteFicticioAssociarPagamentosNaoIdentificados()
						.equals("")) {
		
			httpServletRequest
					.setAttribute("clienteFicticioEncontrado", "true");
		}
		
		// ClienteUsuarioDesconhecido
		if (form.getClienteUsuarioDesconhecido() != null
				&& !form.getClienteUsuarioDesconhecido().equals("")
				&& form.getNomeClienteUsuarioDesconhecido() != null
				&& !form.getNomeClienteUsuarioDesconhecido().equals("")) {
		
			httpServletRequest.setAttribute("clienteUsuarioDesconhecido",
					"true");
		}
		
		// ClienteReponsavel
		if (form.getIdClienteResponsavelProgramaEspecial() != null
				&& !form.getIdClienteResponsavelProgramaEspecial().equals("")
				&& form.getNomeClienteResponsavelProgramaEspecial() != null
				&& !form.getNomeClienteResponsavelProgramaEspecial().equals("")) {
		
			httpServletRequest.setAttribute(
					"clienteResponsavelProgramaEspecial", "true");
		}
	
	}
	
	/**
	* Este m�todo faz a consulta Cliente.
	* 
	* @author Hugo Amorim
	* @since 22/12/2009
	*/
	
	private void pesquisarClientePrograma(
		InformarSistemaParametrosActionForm form,
		HttpServletRequest httpServletRequest) {
	
		String clienteProgramaEspecial = null;
		
		clienteProgramaEspecial = form
				.getIdClienteResponsavelProgramaEspecial();
		FiltroCliente filtroClienteProgramaEspecial = new FiltroCliente();
		
		filtroClienteProgramaEspecial.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, new Integer(clienteProgramaEspecial)));
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoClienteProgramaEspecial = this.getFachada()
				.pesquisar(filtroClienteProgramaEspecial,
						Cliente.class.getName());
	
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoClienteProgramaEspecial != null
				&& !colecaoClienteProgramaEspecial.isEmpty()) {
		
			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClienteProgramaEspecial);
		
			httpServletRequest.setAttribute(
					"clienteProgramaEspecialEncontrado", true);
		
			form.setIdClienteResponsavelProgramaEspecial(cliente.getId()
					.toString());
			form.setNomeClienteResponsavelProgramaEspecial(cliente.getNome());
		} else {
			httpServletRequest.setAttribute(
					"idClienteResponsavelProgramaEspecial", false);
			form.setIdClienteResponsavelProgramaEspecial(null);
			form.setNomeClienteResponsavelProgramaEspecial("Cliente inexistente");
		}
	}
	
	/**
	* M�todo consulta os perfis de im�vel ativos e seta essa cole��o no FORM
	* para que seja exibida na tela.
	* 
	* @author Hugo Amorim
	* @since 14/12/2009
	*/
	private void carregarColecaoPerfisImovel(
		InformarSistemaParametrosActionForm form,
		HttpServletRequest httpServletRequest) {
	
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada()
				.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		httpServletRequest.setAttribute("colecaoPerfisImovel",
				colecaoImoveisPerfis);
	}
}
