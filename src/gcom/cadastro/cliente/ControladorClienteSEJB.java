/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.cliente;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.cliente.bean.ClienteEmitirBoletimCadastroHelper;
import gcom.cadastro.cliente.bean.ClienteImovelHelper;
import gcom.cadastro.cliente.bean.FiltrarQuantidadeAcessosReceitaFederalHelper;
import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.unidade.ControladorUnidadeLocal;
import gcom.cadastro.unidade.ControladorUnidadeLocalHome;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.gui.ActionServletException;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.relatorio.cadastro.RelatorioClienteVirtualBean;
import gcom.relatorio.cadastro.cliente.RelatorioClienteReceitaFederalHelper;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.ConsultarReceitaFederal;
import gcom.seguranca.ControladorPermissaoEspecialLocal;
import gcom.seguranca.ControladorPermissaoEspecialLocalHome;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.seguranca.transacao.ControladorTransacaoLocalHome;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public class ControladorClienteSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;

	private IRepositorioCliente repositorioCliente = null;

	private IRepositorioClienteImovel repositorioClienteImovel = null;

	private IRepositorioClienteEndereco repositorioClienteEndereco = null;
	
	private IRepositorioFaturamento repositorioFaturamento = null;
	
	private IRepositorioMicromedicao repositorioMicromedicao = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

		repositorioCliente = RepositorioClienteHBM.getInstancia();
		repositorioClienteImovel = RepositorioClienteImovelHBM.getInstancia();
		repositorioClienteEndereco = RepositorioClienteEnderecoHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
	/**
	 * Retorna o valor de controladorSpcSeraca
	 * 
	 * @return O valor de controladorSpcSerasa
	 */
	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;
		
		//pega a instância do ServiceLocator
		
		ServiceLocator locator = null;
		
		try{
			locator = ServiceLocator.getInstancia();
			
			localHome = (ControladorSpcSerasaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
			
			//guarda a referencia de um objeto capaz de fazer chamadas à
			//objetos remotamente
			local = localHome.create();
			
			return local;
		}catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorEndereco
	 * 
	 * @return O valor de controladorEndereco
	 */
	private ControladorEnderecoLocal getControladorEndereco() {

		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorEndereco
	 * 
	 * @return O valor de controladorEndereco
	 */
	private ControladorImovelLocal getControladorImovel() {

		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorPermissaoEspecial
	 * 
	 * @return O valor de controladorPermissaoEspecial
	 */
	private ControladorPermissaoEspecialLocal getControladorPermissaoEspecial() {
		
		ControladorPermissaoEspecialLocalHome localHome = null;
		ControladorPermissaoEspecialLocal local = null;
		
		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorPermissaoEspecialLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_PERMISSAO_ESPECIAL_SEJB);
			
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorRegistroAtendimento
	 * 
	 * @return O valor de controladorRegistroAtendimento
	 */
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {
		
		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;
		
		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);
			
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorUnidade
	 * 
	 * @return O valor de controladorUnidade
	 */
	protected ControladorUnidadeLocal getControladorUnidade() {
		ControladorUnidadeLocalHome localHome = null;
		ControladorUnidadeLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUnidadeLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UNIDADE_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Insere um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser inserido
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endereços do cliente
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Integer inserirCliente(Cliente cliente, Collection telefones,
			Collection enderecos, Usuario usuario, Integer idOperacao,boolean ehAtuCadastral) throws ControladorException {
		FiltroCliente filtroCliente = new FiltroCliente();

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				idOperacao, cliente.getId(), cliente.getId(), 
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------


		// Validar CPF de cliente
		if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CPF, cliente.getCpf()));

			Collection clienteComCpfExistente = getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());

			if (!clienteComCpfExistente.isEmpty()) {
				Cliente clienteComCpf = (Cliente) clienteComCpfExistente
						.iterator().next();
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.cpf.cliente.ja_cadastrado", null, ""
								+ clienteComCpf.getId());

			}
		}

		// Validar CNPJ de cliente
		if (cliente.getCnpj() != null && !cliente.getCnpj().equals("")) {
			filtroCliente.limparListaParametros();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CNPJ, cliente.getCnpj()));

			Collection clienteComCnpjExistente = getControladorUtil()
					.pesquisar(filtroCliente, Cliente.class.getName());

			if (!clienteComCnpjExistente.isEmpty()) {
				Cliente clienteComCnpj = (Cliente) clienteComCnpjExistente
						.iterator().next();
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.cnpj.cliente.ja_cadastrado", null, ""
								+ clienteComCnpj.getId());
			}
		}

		// Validar RG de cliente
		if (cliente.getRg() != null && !cliente.getRg().equals("")&& 
				cliente.getOrgaoExpedidorRg() != null && !cliente.getOrgaoExpedidorRg().equals("") ) {
			
			filtroCliente.limparListaParametros();
			
              filtroCliente.adicionarParametro(new ParametroSimples(
                                  FiltroCliente.RG, cliente.getRg()));
          
	          if(cliente.getOrgaoExpedidorRg() != null){
	                  filtroCliente.adicionarParametro(new ParametroSimples(
	                                  FiltroCliente.ORGAO_EXPEDIDOR_RG_ID, cliente
	                                                  .getOrgaoExpedidorRg().getId()));
	          }
	          
	          if(cliente.getUnidadeFederacao() != null){
	                  filtroCliente.adicionarParametro(new ParametroSimples(
	                                  FiltroCliente.UNIDADE_FEDERACAO_ID, cliente
	                                                  .getUnidadeFederacao().getId()));
	          }

			Collection clienteComRgExistente = getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());

			if (!clienteComRgExistente.isEmpty()) {
				
				if(ehAtuCadastral){
					cliente.setRg(null);
					cliente.setOrgaoExpedidorRg(null);
					cliente.setUnidadeFederacao(null);
				}else{
					Cliente clienteComRg = (Cliente) clienteComRgExistente.iterator().next();
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.rg.cliente.ja_cadastrado", null, ""	+ clienteComRg.getId());
				}
			}
		}

		// -------------Parte que insere um cliente na
		// base----------------------
		cliente.setIndicadorGeraArquivoTexto(new Short("2"));
		
		//*************************************************************************
		// Autor: Ivan Sergio
		// Data: 06/08/2009
		// CRC2103
		// Caso em que o Inserir Cliente eh chamdo como PopUp pelo Manter Imovel 
		//*************************************************************************
		AtributoGrupo atributoGrupo = null;
		if (cliente.getOperacaoEfetuada() != null) {
			if (cliente.getOperacaoEfetuada().getAtributoGrupo() != null) {
				atributoGrupo = cliente.getOperacaoEfetuada().getAtributoGrupo();
			}
		}

		registradorOperacao.registrarOperacao(cliente);
		
		if (atributoGrupo != null) {
			cliente.getOperacaoEfetuada().setAtributoGrupo(atributoGrupo);
		}
		//*************************************************************************
		
//		cliente.setClienteFones(new HashSet(telefones));
//		cliente.setClienteEnderecos(new HashSet(enderecos));	
		cliente.setIndicadorAcaoCobranca(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		Integer chaveClienteGerada = (Integer) getControladorUtil().inserir(
				cliente);

		cliente.setId(chaveClienteGerada);

		if (telefones != null) {

			// Inserir os fones do cliente
			Iterator iteratorTelefones = telefones.iterator();

			while (iteratorTelefones.hasNext()) {
				ClienteFone clienteFone = (ClienteFone) iteratorTelefones
						.next();

				clienteFone.setCliente(cliente);
				registradorOperacao.registrarOperacao(clienteFone);
				getControladorUtil().inserir(clienteFone);

			}
		}

		// Inserir os endereços do cliente
		Iterator iteratorEnderecos = enderecos.iterator();

		while (iteratorEnderecos.hasNext()) {
			ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
					.next();

			clienteEndereco.setCliente(cliente);
			registradorOperacao.registrarOperacao(clienteEndereco);
			getControladorUtil().inserir(clienteEndereco);

		}

		return chaveClienteGerada;
		/*
		 * } catch (ErroRepositorioException ex) {
		 * sessionContext.setRollbackOnly(); throw new
		 * ControladorException("erro.sistema", ex); }
		 */
		// -------------Fim da parte que insere um cliente na
		// base---------------
	}

	/**
	 * Atualiza um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser atualizado
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endereços do cliente
	 * @throws ControladorException
	 */
	public void atualizarCliente(Cliente cliente, Collection telefones,
			Collection enderecos, Usuario usuario) throws ControladorException {

		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CLIENTE_ATUALIZAR, cliente.getId(), cliente.getId(), 
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		FiltroCliente filtroCliente = new FiltroCliente();

		try {
			// -------------Parte que atualiza um cliente na
			// base----------------------
			// Verifica se já existe o cnpj cadastrado na base
			if (cliente.getCnpj() != null) {
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, cliente.getCnpj()));
				filtroCliente.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCliente.ID, cliente.getId()));
				
				Collection colecaoClientes = getControladorUtil().pesquisar(filtroCliente, Cliente.class.getName());
				
				if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
					Cliente clienteComCnpj = (Cliente) Util
							.retonarObjetoDeColecao(colecaoClientes);
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.cnpj.cliente.ja_cadastrado", null, ""
									+ clienteComCnpj.getId());
				}
			}
			
			
			// Parte de Validacao com Timestamp
			filtroCliente.limparListaParametros();

			// Seta o filtro para buscar o cliente na base
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, cliente.getId()));

			Collection colecaoCliente = getControladorUtil()
			.pesquisar(filtroCliente, Cliente.class.getName());

			//verifica se o cliente ainda existe na base, porque ele pode ter sido excluido com isso
			//não é possível analizar a data de ultima alteração
			if(colecaoCliente == null || colecaoCliente.isEmpty()){
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
			
			// Procura o cliente na base
			Cliente clienteNaBase = (Cliente) ((List)colecaoCliente).get(0);

			// Verificar se o cliente já foi atualizado por outro usuário
			// durante
			// esta atualização
			if (clienteNaBase.getUltimaAlteracao().after(
					cliente.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			//Altualiza o indicadorGeraArquivotexto
			cliente.setIndicadorGeraArquivoTexto(clienteNaBase.getIndicadorGeraArquivoTexto());
			// Atualiza a data de última alteração
			cliente.setUltimaAlteracao(new Date());
			
			//*************************************************************************
			// Autor: Ivan Sergio
			// Data: 06/08/2009
			// CRC2103
			// Caso em que o Inserir Cliente eh chamdo como PopUp pelo Manter Imovel 
			//*************************************************************************
			AtributoGrupo atributoGrupo = null;
			if (cliente.getOperacaoEfetuada() != null) {
				if (cliente.getOperacaoEfetuada().getAtributoGrupo() != null) {
					atributoGrupo = cliente.getOperacaoEfetuada().getAtributoGrupo();
				}
			}

			registradorOperacao.registrarOperacao(cliente);
			
			if (atributoGrupo != null) {
				cliente.getOperacaoEfetuada().setAtributoGrupo(atributoGrupo);
			}
			//*************************************************************************
			
			cliente.setClienteFones(new HashSet(telefones));
			cliente.setClienteEnderecos(new HashSet(enderecos));
			
			// Atualiza o cliente
			getControladorUtil().atualizar(cliente);

			// Atualizar os fones do cliente
			Iterator iteratorTelefones = telefones.iterator();

			// Remover a lista dos telefones do cliente para adicionar a nova
			// lista depois
			repositorioCliente.removerTodosTelefonesPorCliente(cliente.getId());

			// Adicionar os telefones novos informados para o cliente
			while (iteratorTelefones.hasNext()) {
				ClienteFone clienteFone = (ClienteFone) iteratorTelefones
						.next();

				clienteFone.setUltimaAlteracao(new Date());
				clienteFone.setCliente(cliente);
				getControladorUtil().inserir(clienteFone);

			}

			// Inserir os endereços do cliente
			Iterator iteratorEnderecos = enderecos.iterator();

			// Remover a lista dos endereços do cliente para adicionar a nova
			// lista depois
			repositorioCliente.removerTodosEnderecosPorCliente(cliente.getId());

			// Adicionar os endereços novos informados para o cliente
			while (iteratorEnderecos.hasNext()) {
				ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
						.next();

				clienteEndereco.setUltimaAlteracao(new Date());
				clienteEndereco.setCliente(cliente);
				getControladorUtil().inserir(clienteEndereco);

			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		// -------------Fim da parte que atualiza um cliente na
		// base---------------
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void inserirClienteImovel(ClienteImovel clienteImovel)
			throws ControladorException {

		clienteImovel.setDataInicioRelacao(new Date());

		getControladorUtil().inserir(clienteImovel);
	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ControladorException {
		try {
			return repositorioClienteImovel.pesquisarClienteImovel(
					filtroClienteImovel, numeroPagina);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa uma a quantidade de cliente imovel com uma query especifica
	 * [UC0015] Filtrar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @author Rafael Santos
	 * @since 26/06/2006
	 * 
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public int pesquisarQuantidadeClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		try {
			return repositorioClienteImovel
					.pesquisarQuantidadeClienteImovel(filtroClienteImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovelTarifaSocial(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ControladorException {
		try {
			return repositorioClienteImovel.pesquisarClienteImovel(
					filtroClienteImovel, numeroPagina);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Metodo que retorno todos os clinte do filtro passado
	 * 
	 * @param filtroCliente
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @autor thiago toscano
	 * @date 15/12/2005
	 * @throws ControladorException
	 */
	public Collection pesquisarCliente(FiltroCliente filtroCliente)
			throws ControladorException {
		Collection coll = getControladorUtil().pesquisar(filtroCliente,
				Cliente.class.getSimpleName());
		return coll;
	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteEndereco
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteEndereco(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ControladorException {
		try {
			return repositorioClienteEndereco
					.pesquisarClienteEndereco(filtroClienteEndereco);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * atualiza um cliente imovel economia com a data final da relação e o
	 * motivo.
	 * 
	 * @param clientesImoveisEconomia
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void atualizarClienteImovelEconomia(
			Collection clientesImoveisEconomia) throws ControladorException {

		// try {
		// -------------Parte que atualiza um cliente imovel economia na
		// base---------------------

		Iterator clienteImovelEconomiaIterator = clientesImoveisEconomia
				.iterator();

		while (clienteImovelEconomiaIterator.hasNext()) {
			ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
					.next();

			// Parte de Validacao com Timestamp
			FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();

			// Seta o filtro para buscar o cliente imovel economia na base
			filtroClienteImovelEconomia
					.adicionarParametro(new ParametroSimples(
							FiltroClienteImovelEconomia.ID,
							clienteImovelEconomia.getId()));

			// Procura o cliente na base
			ClienteImovelEconomia clienteImovelEconomiaNaBase = (ClienteImovelEconomia) ((List) (getControladorUtil()
					.pesquisar(filtroClienteImovelEconomia,
							ClienteImovelEconomia.class.getName()))).get(0);

			// Verificar se o cliente já foi atualizado por outro usuário
			// durante
			// esta atualização
			if (clienteImovelEconomiaNaBase.getUltimaAlteracao().after(
					clienteImovelEconomia.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Atualiza a data de última alteração
			clienteImovelEconomia.setUltimaAlteracao(new Date());

			// Atualiza o cliente
			getControladorUtil().atualizar(clienteImovelEconomia);
		}
		/*
		 * } catch (ErroRepositorioException ex) {
		 * sessionContext.setRollbackOnly(); throw new
		 * ControladorException("erro.sistema", ex); }
		 */

	}

	/**
	 * Pesquisa ClienteEndereco percorrendo o ClienteImovel
	 * 
	 * @param filtroClienteEndereco
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Cliente> pesquisarClienteEnderecoClienteImovel(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ControladorException {
		try {
			return repositorioClienteEndereco
					.pesquisarClienteEnderecoClienteImovel(filtroClienteEndereco);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(
			FiltroCliente filtroCliente, Integer numeroPagina)
			throws ControladorException {

		try {
			return repositorioCliente.pesquisarClienteDadosClienteEndereco(
					filtroCliente, numeroPagina);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(
			FiltroCliente filtroCliente) throws ControladorException {

		try {
			return repositorioCliente
					.pesquisarClienteDadosClienteEnderecoRelatorio(filtroCliente);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * Retrona a quantidade de endereços que existem para o Cliente
	 * 
	 * pesquisarClienteDadosClienteEnderecoCount
	 * 
	 * @author Roberta Costa
	 * @date 29/06/2006
	 * 
	 * @param filtroCliente
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisarClienteDadosClienteEnderecoCount(
			FiltroCliente filtroCliente) throws ControladorException {

		try {
			return repositorioCliente
					.pesquisarClienteDadosClienteEnderecoCount(filtroCliente);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection<Cliente> pesquisarClienteOutrosCriterios(
			FiltroCliente filtroCliente) throws ControladorException {

		try {
			return repositorioCliente
					.pesquisarClienteOutrosCriterios(filtroCliente);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Este fluxo secundário tem como objetivo pesquisar o cliente digitado pelo
	 * usuário
	 * 
	 * [FS0011] - Verificar existência do código do cliente
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idClienteDigitado
	 * @return
	 * @throws ControladorException
	 */
	public Cliente pesquisarClienteDigitado(Integer idClienteDigitado)
			throws ControladorException {

		// Cria a variável que vai armazenar o cliente pesquisado
		Cliente clienteDigitado = null;

		// Pesquisa o cliente informado pelo usuário no sistema
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				idClienteDigitado));
		
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ESFERA_PODER);
			
		Collection<Cliente> colecaoCliente = getControladorUtil().pesquisar(
				filtroCliente, Cliente.class.getName());

		// Caso exista o cliente no sistema
		// Retorna para o usuário o cliente retornado pela pesquisa
		// Caso contrário retorna um objeto nulo
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
			clienteDigitado = (Cliente) Util
					.retonarObjetoDeColecao(colecaoCliente);
		}

		// Retorna o cliente encontrado ou nulo se não existir
		return clienteDigitado;
	}

	/**
	 * Pesquisa um cliente carregando os dados do relacionamento com ClienteTipo
	 * 
	 * [UC0321] Emitir Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2006
	 * 
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */
	public Cliente pesquisarCliente(Integer idCliente)
			throws ControladorException {

		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioCliente.pesquisarCliente(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioClienteImovel
					.pesquisarClienteImovel(filtroClienteImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovelRelatorio(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioClienteImovel
					.pesquisarClienteImovelRelatorio(filtroClienteImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Pesquisa um cliente pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Cliente
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Cliente pesquisarObjetoClienteRelatorio(Integer idCliente)
			throws ControladorException {

		try {
			// pesquisa as gerencias regionais existentes no sisitema
			Object[] objetoCliente = repositorioCliente
					.pesquisarObjetoClienteRelatorio(idCliente);

			Cliente cliente = new Cliente();

			cliente.setId((Integer) objetoCliente[0]);
			cliente.setNome((String) objetoCliente[1]);

			return cliente;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Pesquisa as quantidades de imóveis e as quantidades de economias
	 * associadas a um cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 23/08/2007
	 * 
	 * @return Object[]
	 * @exception ControladorException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente)
			throws ControladorException {

		try {
			// pesquisa as gerencias regionais existentes no sisitema
			return repositorioCliente
					.pesquisarQtdeImoveisEEconomiasCliente(idCliente);

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * O método abaixo realiza uma pesquisa em cliente e verifica se ele existe
	 * 
	 */
	public Integer verificarExistenciaCliente(Integer codigoCliente)
			throws ControladorException {

		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioCliente.verificarExistenciaCliente(codigoCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * 
	 * @param idCliente,
	 *            idImovel
	 * @return Colletion
	 * @throws ControladorException
	 */
	public ClienteImovel pesquisarClienteImovel(Integer idCliente,
			Integer idImovel) throws ControladorException {

		Collection colecaoClienteImovel = null;

		ClienteImovel retorno = null;

		try {
			colecaoClienteImovel = this.repositorioClienteImovel
					.pesquisarClienteImovel(idCliente, idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			retorno = new ClienteImovel();
			Cliente cliente = new Cliente();

			Iterator raIterator = colecaoClienteImovel.iterator();

			Object[] arrayClienteIMovel = (Object[]) raIterator.next();

			cliente.setId((Integer) arrayClienteIMovel[0]);

			cliente.setNome((String) arrayClienteIMovel[1]);

			retorno.setCliente(cliente);
		}

		return retorno;
	}

	/**
	 * Pesquisa todos os telefones de um cliente
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteFone(Integer idCliente)
			throws ControladorException {

		//Collection colecaoFone = null;
		//Collection colecaoRetorno = null;

		try {
			return this.repositorioCliente
					.pesquisarClienteFone(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		/*if (colecaoFone != null && !colecaoFone.isEmpty()) {

			Iterator foneIterator = colecaoFone.iterator();
			ClienteFone clienteFone = null;
			FoneTipo foneTipo = null;

			colecaoRetorno = new ArrayList();

			while (foneIterator.hasNext()) {

				clienteFone = new ClienteFone();

				Object[] arrayFone = (Object[]) foneIterator.next();

				clienteFone.setId((Integer) arrayFone[0]);

				clienteFone.setUltimaAlteracao((Date) arrayFone[5]);

				if (arrayFone[1] != null) {
					clienteFone.setDdd((String) arrayFone[1]);
				}

				clienteFone.setTelefone((String) arrayFone[2]);

				if (arrayFone[3] != null) {
					clienteFone
							.setIndicadorTelefonePadrao((Short) arrayFone[3]);
				}

				foneTipo = new FoneTipo();
				foneTipo.setDescricao((String) arrayFone[4]);

				clienteFone.setFoneTipo(foneTipo);

				colecaoRetorno.add(clienteFone);
			}
		}*/

		//return colecaoRetorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecosClienteAbreviado(Integer idCliente)
			throws ControladorException {

		Collection colecaoEndereco = null;
		Collection colecaoRetorno = null;

		try {
			colecaoEndereco = this.repositorioClienteEndereco
					.pesquisarEnderecosClienteAbreviado(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {

			Iterator enderecoIterator = colecaoEndereco.iterator();
			ClienteEndereco clienteEndereco = null;

			colecaoRetorno = new ArrayList();

			while (enderecoIterator.hasNext()) {

				clienteEndereco = new ClienteEndereco();

				// cria um array de objetos para pegar os parametros de
				// retorno da pesquisa
				Object[] arrayEndereco = (Object[]) enderecoIterator.next();

				LogradouroCep logradouroCep = null;
				if (arrayEndereco[20] != null) {

					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) arrayEndereco[20]);

					// id do Logradouro
					Logradouro logradouro = null;
					if (arrayEndereco[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) arrayEndereco[19]);
						logradouro.setNome("" + arrayEndereco[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// Descrição logradouro tipo
					if (arrayEndereco[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada(""
								+ arrayEndereco[22]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descrição logradouro titulo
					if (arrayEndereco[23] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada(""
								+ arrayEndereco[23]);
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					// id do CEP
					Cep cep = null;
					if (arrayEndereco[10] != null) {
						cep = new Cep();
						cep.setCepId((Integer) arrayEndereco[10]);
						cep.setCodigo((Integer) arrayEndereco[16]);
					}

					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setCep(cep);
				}

				clienteEndereco.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (arrayEndereco[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayEndereco[21]);

					Bairro bairro = null;
					// nome bairro
					if (arrayEndereco[3] != null) {
						bairro = new Bairro();
						bairro.setId((Integer) arrayEndereco[3]);
						bairro.setNome("" + arrayEndereco[4]);
					}

					Municipio municipio = null;
					// nome municipio
					if (arrayEndereco[5] != null) {
						municipio = new Municipio();
						municipio.setId((Integer) arrayEndereco[5]);
						municipio.setNome("" + arrayEndereco[6]);

						// id da unidade federação
						if (arrayEndereco[7] != null) {
							UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
							unidadeFederacao.setId((Integer) arrayEndereco[7]);
							unidadeFederacao.setSigla("" + arrayEndereco[8]);
							municipio.setUnidadeFederacao(unidadeFederacao);
						}

						bairro.setMunicipio(municipio);
					}

					logradouroBairro.setBairro(bairro);
				}

				clienteEndereco.setLogradouroBairro(logradouroBairro);

				// descricao de endereço referência
				if (arrayEndereco[24] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[24]);
					clienteEndereco.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					clienteEndereco.setNumero("" + arrayEndereco[17]);
				}

				// complemento endereço
				if (arrayEndereco[18] != null) {
					clienteEndereco.setComplemento("" + arrayEndereco[18]);
				}

				// indicador endereço correspondencia
				if (arrayEndereco[25] != null) {
					clienteEndereco
							.setIndicadorEnderecoCorrespondencia((Short) arrayEndereco[25]);
				}

				clienteEndereco.setId((Integer) arrayEndereco[26]);

				clienteEndereco.setUltimaAlteracao((Date) arrayEndereco[27]);
				
				// Perímetro
				if (arrayEndereco[28] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[28]);
					
					if (arrayEndereco[29] != null) {
						perimetroInicial.setNome((String) arrayEndereco[29]);
					}
					
					if (arrayEndereco[30] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[30]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[31] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[31]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					clienteEndereco.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[32] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[32]);
					
					if (arrayEndereco[33] != null) {
						perimetroFinal.setNome((String) arrayEndereco[33]);
					}
					
					if (arrayEndereco[34] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[34]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[35] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[35]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					clienteEndereco.setPerimetroFinal(perimetroFinal);
				}

				colecaoRetorno.add(clienteEndereco);
			}
		}

		return colecaoRetorno;
	}

	/**
	 * Pesquisa os dados do Cliente
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Cliente consultarCliente(Integer idCliente)
			throws ControladorException {

		Collection colecaoClientes = null;
		// Collection colecaoRetorno = null;
		Cliente cliente = null;

		try {
			colecaoClientes = this.repositorioCliente
					.consultarCliente(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClientes != null && !colecaoClientes.isEmpty()) {

			Object[] objetoCliente = (Object[]) colecaoClientes.iterator()
					.next();

			cliente = new Cliente();

			// 0 - Nome do Cliente
			if (objetoCliente[0] != null) {
				cliente.setNome((String) objetoCliente[0]);
			}

			// 1 - Nome do Cliente Abreviado
			if (objetoCliente[1] != null) {
				cliente.setNomeAbreviado((String) objetoCliente[1]);
			}

			// 2 - Data Vencimento
			if (objetoCliente[2] != null) {
				cliente.setDataVencimento((Short) objetoCliente[2]);
			}

			// 3 - Cliente Tipo Descricao
			ClienteTipo clienteTipo = null;
			if (objetoCliente[3] != null) {
				clienteTipo = new ClienteTipo();
				clienteTipo.setDescricao((String) objetoCliente[3]);
				cliente.setClienteTipo(clienteTipo);
			}

			// 4 - Indicado Pessoa Fisica ou Juridica
			if (objetoCliente[4] != null) {
				if (clienteTipo == null) {
					clienteTipo = new ClienteTipo();
				}
				clienteTipo
						.setIndicadorPessoaFisicaJuridica((Short) objetoCliente[4]);
				cliente.setClienteTipo(clienteTipo);
			}

			// 5 - E-mail
			if (objetoCliente[5] != null) {
				cliente.setEmail((String) objetoCliente[5]);
			}

			// 6 - Indicador de Acao Cobranca
			if (objetoCliente[6] != null) {
				cliente.setIndicadorAcaoCobranca((Short) objetoCliente[6]);
			}

			// 7 - CPF
			if (objetoCliente[7] != null) {
				cliente.setCpf((String) objetoCliente[7]);
			}

			// 8 - RG
			if (objetoCliente[8] != null) {
				cliente.setRg((String) objetoCliente[8]);
			}

			// 9 - Data Emissao RG
			if (objetoCliente[9] != null) {
				cliente.setDataEmissaoRg((Date) objetoCliente[9]);
			}

			// 10 - Orgao Expedidor RG
			OrgaoExpedidorRg orgaoExpedidorRg = null;
			if (objetoCliente[10] != null) {
				orgaoExpedidorRg = new OrgaoExpedidorRg();
				orgaoExpedidorRg
						.setDescricaoAbreviada((String) objetoCliente[10]);
				cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			}

			// 11 - Unidade Federacao
			UnidadeFederacao unidadeFederacao = null;
			if (objetoCliente[11] != null) {
				unidadeFederacao = new UnidadeFederacao();
				unidadeFederacao.setSigla((String) objetoCliente[11]);
				cliente.setUnidadeFederacao(unidadeFederacao);
			}

			// 12 - Data Nascimento
			if (objetoCliente[12] != null) {
				cliente.setDataNascimento((Date) objetoCliente[12]);
			}

			// 13 - Profissao
			Profissao profissao = null;
			if (objetoCliente[13] != null) {
				profissao = new Profissao();
				profissao.setDescricao((String) objetoCliente[13]);
				cliente.setProfissao(profissao);
			}

			// 14 - Pessoa Sexo
			PessoaSexo pessoaSexo = null;
			if (objetoCliente[14] != null) {
				pessoaSexo = new PessoaSexo();
				pessoaSexo.setDescricao((String) objetoCliente[14]);
				cliente.setPessoaSexo(pessoaSexo);
			}

			// 15 - CNPJ
			if (objetoCliente[15] != null) {
				cliente.setCnpj((String) objetoCliente[15]);
			}

			// 16 - Ramo Atividade
			RamoAtividade ramoAtividade = null;
			if (objetoCliente[16] != null) {
				ramoAtividade = new RamoAtividade();
				ramoAtividade.setDescricao((String) objetoCliente[16]);
				cliente.setRamoAtividade(ramoAtividade);
			}

			// 17 - Codigo Cliente Responsavel
			Cliente clienteResponsavel = null;
			if (objetoCliente[17] != null) {
				clienteResponsavel = new Cliente();
				clienteResponsavel.setId((Integer) objetoCliente[17]);
				cliente.setCliente(cliente);
			}

			// 18 - Nome Cliente Responsavel
			if (objetoCliente[17] != null) {
				if (clienteResponsavel == null) {
					clienteResponsavel = new Cliente();
				}
				clienteResponsavel.setNome((String) objetoCliente[18]);
				cliente.setCliente(cliente);
			}

		}

		return cliente;
	}

	/**
	 * Pesquisa todos os endereços do cliente
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecoCliente(Integer idCliente)
			throws ControladorException {

		Collection colecaoClienteEndereco = null;
		Collection colecaoRetorno = null;

		try {
			colecaoClienteEndereco = this.repositorioCliente
					.pesquisarEnderecosCliente(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()) {

			Iterator clienteEnderecoIterator = colecaoClienteEndereco
					.iterator();
			ClienteEndereco clienteEndereco = null;
			colecaoRetorno = new ArrayList();

			while (clienteEnderecoIterator.hasNext()) {

				Object[] array = (Object[]) clienteEnderecoIterator.next();

				clienteEndereco = new ClienteEndereco();

				// 0- Endereco Tipo
				EnderecoTipo enderecoTipo = null;
				if (array[0] != null) {
					enderecoTipo = new EnderecoTipo();
					enderecoTipo.setDescricao((String) array[0]);
					clienteEndereco.setEnderecoTipo(enderecoTipo);
				}

				// 1- Indicador Endereco Correspondencia
				if (array[0] != null) {
					clienteEndereco
							.setIndicadorEnderecoCorrespondencia((Short) array[1]);
				}
				colecaoRetorno.add(clienteEndereco);
			}
		}

		return colecaoRetorno;

	}

	/**
	 * Pesquisa o nome do cliente a partir do imóvel Autor: Sávio Luiz Data:
	 * 21/12/2005
	 */
	public String pesquisarNomeClientePorImovel(Integer idImovel)
			throws ControladorException {

		String nomeCliente = "";

		try {
			nomeCliente = this.repositorioClienteImovel
					.pesquisarNomeClientePorImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return nomeCliente;
	}

	/**
	 * Pesquisa o nome, cnpj e id do cliente a partir do imóvel Autor: Rafael Corrêa Data:
	 * 25/09/2006
	 */
	public Cliente pesquisarDadosClienteRelatorioParcelamentoPorImovel(Integer idImovel)
			throws ControladorException {

		Cliente cliente = null;
		Object[] dadosCliente = null;

		try {
			dadosCliente = this.repositorioClienteImovel
					.pesquisarDadosClienteRelatorioParcelamentoPorImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosCliente != null) {
			
			cliente = new Cliente();
			
			if (dadosCliente[0] != null) {
				//Id
				cliente.setId((Integer) dadosCliente[0]);
			}
			
			if (dadosCliente[1] != null) {
				// Nome
				cliente.setNome((String) dadosCliente[1]);
			} else {
				cliente.setNome("");
			}
			
			if (dadosCliente[2] != null) {
				// CPF
				cliente.setCpf((String) dadosCliente[2]);
			}
			
			if (dadosCliente[3] != null) {
				// CNPJ
				cliente.setCnpj((String) dadosCliente[3]);
			}
			
			if (dadosCliente[4] != null) {
				// orgão expedidor
				OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
				orgaoExpedidorRg.setDescricao((String)dadosCliente[4]);
				cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			}
			
			if (dadosCliente[5] != null) {
				// unidade federativa
				UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
				unidadeFederacao.setSigla((String) dadosCliente[5]);
				cliente.setUnidadeFederacao(unidadeFederacao);
			}
			
			if (dadosCliente[6] != null) {
				// rg
				cliente.setRg((String)dadosCliente[6]);
			}
			
		}

		return cliente;
	}
	
	/**
	 * [UC0458] - Imprimir Ordem de Serviço
	 * 
	 * Pesquisa o telefone principal do Cliente para o
	 * Relatório de OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	
	public String pesquisarClienteFonePrincipal(
			Integer idCliente) throws ControladorException {

		Object[] dadosClienteFone = null;
		String telefoneFormatado = "";

		try {

			dadosClienteFone = this.repositorioCliente
					.pesquisarClienteFonePrincipal(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosClienteFone != null) {
			
			// DDD
			if (dadosClienteFone[0] != null) {
				telefoneFormatado = telefoneFormatado + "(" + dadosClienteFone[0].toString() + ")";
			}
			
			// Número do Telefone
			if (dadosClienteFone[1] != null) {
				telefoneFormatado = telefoneFormatado + dadosClienteFone[1];
			}
			
			// Ramal
			if (dadosClienteFone[2] != null) {
				telefoneFormatado = telefoneFormatado + "/" + dadosClienteFone[2];
			}
			
		}

		return telefoneFormatado;

	}
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Pesquisa os dados do cliente para a emissão do boletim
	 * 
	 * @author Rafael Corrêa
	 * @date 16/05/2007
	 * 
	 * @param idImovel, clienteRelacaoTipo
	 * @throws ControladorException
	 */
	public ClienteEmitirBoletimCadastroHelper pesquisarClienteEmitirBoletimCadastro(
			Integer idImovel, Short clienteRelacaoTipo)
			throws ControladorException {

		Collection colecaoDadosCliente = null;
		ClienteEmitirBoletimCadastroHelper clienteEmitirBoletimCadastroHelper = null;

		try {

			colecaoDadosCliente = this.repositorioCliente
					.pesquisarClienteEmitirBoletimCadastro(idImovel,
							clienteRelacaoTipo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDadosCliente != null && !colecaoDadosCliente.isEmpty()) {

			Iterator colecaoDadosClienteIterator = colecaoDadosCliente
					.iterator();

			boolean primeiroRegistro = true;

			Collection clientesFone = new ArrayList();

			while (colecaoDadosClienteIterator.hasNext()) {

				Object[] dadosCliente = (Object[]) colecaoDadosClienteIterator
						.next();

				if (primeiroRegistro) {

					clienteEmitirBoletimCadastroHelper = new ClienteEmitirBoletimCadastroHelper();
					Cliente cliente = new Cliente();
					ClienteEndereco clienteEndereco = new ClienteEndereco();
					LogradouroCep logradouroCep = new LogradouroCep();
					LogradouroBairro logradouroBairro = new LogradouroBairro();

					// Dados do Cliente
					// Id do Cliente
					if (dadosCliente[0] != null) { // 0
						cliente.setId((Integer) dadosCliente[0]);
					}

					// Nome do Cliente
					if (dadosCliente[1] != null) { // 1
						cliente.setNome((String) dadosCliente[1]);
					}

					// Tipo do Cliente
					if (dadosCliente[2] != null) { // 2
						ClienteTipo clienteTipo = new ClienteTipo();
						clienteTipo.setId((Integer) dadosCliente[2]);
						cliente.setClienteTipo(clienteTipo);
					}

					// CPF do Cliente
					if (dadosCliente[3] != null) { // 3
						cliente.setCpf((String) dadosCliente[3]);
					}

					// CNPJ do Cliente
					if (dadosCliente[4] != null) { // 4
						cliente.setCnpj((String) dadosCliente[4]);
					}

					// RG do Cliente
					if (dadosCliente[5] != null) { // 5
						cliente.setRg((String) dadosCliente[5]);
					}

					// Data de Emissão RG
					if (dadosCliente[6] != null) { // 6
						cliente.setDataEmissaoRg((Date) dadosCliente[6]);
					}

					// Órgão Expedidor RG
					if (dadosCliente[7] != null) { // 7
						OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
						orgaoExpedidorRg.setDescricaoAbreviada((String) dadosCliente[7]);
						cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
					}

					// Unidade Federação
					if (dadosCliente[8] != null) { // 8
						UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
						unidadeFederacao.setSigla((String) dadosCliente[8]);
						cliente.setUnidadeFederacao(unidadeFederacao);
					}

					// Data de Nascimento
					if (dadosCliente[9] != null) { // 9
						cliente.setDataNascimento((Date) dadosCliente[9]);
					}

					// Profissão
					if (dadosCliente[10] != null) { // 10
						Profissao profissao = new Profissao();
						profissao.setDescricao((String) dadosCliente[10]);
						cliente.setProfissao(profissao);
					}

					// Sexo
					if (dadosCliente[11] != null) { // 11
						PessoaSexo pessoaSexo = new PessoaSexo();
						pessoaSexo.setId((Integer) dadosCliente[11]);
						cliente.setPessoaSexo(pessoaSexo);
					}

					// Nome da Mãe
					if (dadosCliente[12] != null) { // 12
						cliente.setNomeMae((String) dadosCliente[12]);
					}

					// Indicador de Uso
					if (dadosCliente[13] != null) { // 13
						cliente.setIndicadorUso((Short) dadosCliente[13]);
					}

					clienteEmitirBoletimCadastroHelper.setCliente(cliente);

					// Dados do Endereço do Cliente
					// Tipo de Endereço
					if (dadosCliente[14] != null) { // 14
						EnderecoTipo enderecoTipo = new EnderecoTipo();
						enderecoTipo.setId((Integer) dadosCliente[14]);
						clienteEndereco.setEnderecoTipo(enderecoTipo);
					}

					// Id do Logradouro
					if (dadosCliente[15] != null) { // 15
						Logradouro logradouro = new Logradouro();
						logradouro.setId((Integer) dadosCliente[15]);
						logradouroCep.setLogradouro(logradouro);
						logradouroBairro.setLogradouro(logradouro);
					}

					// Endereço do Cliente
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
					filtroClienteEndereco.adicionarParametro(new ParametroSimples(
							FiltroClienteEndereco.CLIENTE_ID, cliente.getId()));
					filtroClienteEndereco.adicionarParametro(new ParametroSimples(
							FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA, ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA));
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
					
					Collection colecaoEnderecos = getControladorUtil()
							.pesquisar(filtroClienteEndereco,
									ClienteEndereco.class.getName());
					
					if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
						ClienteEndereco clienteEnderecoCorrespondencia = (ClienteEndereco) Util
								.retonarObjetoDeColecao(colecaoEnderecos);

						String endereco = clienteEnderecoCorrespondencia
								.getEnderecoFormatado();

						if (endereco != null && !endereco.trim().equals("")) {
							clienteEmitirBoletimCadastroHelper
									.setEnderecoFormatado(endereco);
						}
					}
					
					// CEP
					if (dadosCliente[16] != null) { // 16
						Cep cep = new Cep();
						cep.setCodigo((Integer) dadosCliente[16]);
						logradouroCep.setCep(cep);
					}

					// Id do Bairro
					if (dadosCliente[17] != null) { // 17
						Bairro bairro = new Bairro();
						bairro.setCodigo((Integer) dadosCliente[17]);
						
						Municipio municipio = new Municipio();
						municipio.setId((Integer) dadosCliente[25]);
						
						bairro.setMunicipio(municipio);
						
						logradouroBairro.setBairro(bairro);
					}

					// Endereço de Referência
					if (dadosCliente[18] != null) { // 18
						EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
						enderecoReferencia.setId((Integer) dadosCliente[18]);
						clienteEndereco
								.setEnderecoReferencia(enderecoReferencia);
					}

					// Número do Imóvel
					if (dadosCliente[19] != null) { // 19
						clienteEndereco.setNumero((String) dadosCliente[19]);
					}

					// Complemento
					if (dadosCliente[20] != null) { // 20
						clienteEndereco
								.setComplemento((String) dadosCliente[20]);
					}

					clienteEndereco.setLogradouroCep(logradouroCep);
					clienteEndereco.setLogradouroBairro(logradouroBairro);
					clienteEmitirBoletimCadastroHelper
							.setClienteEndereco(clienteEndereco);

					primeiroRegistro = false;
					
					// Tarifa Social Dado Economia
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel.adicionarParametro(
							new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, cliente.getId()));
					filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(
							FiltroClienteImovel.IMOVEL);
					
					Collection colecaoClienteImovel = getControladorUtil()
							.pesquisar(filtroClienteImovel,
									ClienteImovel.class.getName());
					
					if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
						ClienteImovel clienteImovel = (ClienteImovel) Util
						.retonarObjetoDeColecao(colecaoClienteImovel);
						
						FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = 
								new FiltroTarifaSocialDadoEconomia();
						filtroTarifaSocialDadoEconomia.adicionarParametro(
								new ParametroSimples(FiltroTarifaSocialDadoEconomia.IMOVEL_ID, clienteImovel.getImovel().getId()));
						filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(
								FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO);
						filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(
								FiltroTarifaSocialDadoEconomia.RENDA_TIPO);
						
						Collection colecaoTarifaSocial = getControladorUtil()
								.pesquisar(filtroTarifaSocialDadoEconomia,
										TarifaSocialDadoEconomia.class.getName());

						if (colecaoTarifaSocial != null && !colecaoTarifaSocial.isEmpty()) {
							TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) Util
									.retonarObjetoDeColecao(colecaoTarifaSocial);
							clienteEmitirBoletimCadastroHelper.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
						}
						
					}
					

				}

				ClienteFone clienteFone = new ClienteFone();

				// Dados do Telefone do Cliente
				// Tipo do Telefone
				if (dadosCliente[21] != null) { // 21
					FoneTipo foneTipo = new FoneTipo();
					foneTipo.setId((Integer) dadosCliente[21]);
					clienteFone.setFoneTipo(foneTipo);
				}

				// DDD
				if (dadosCliente[22] != null) { // 22
					clienteFone.setDdd((String) dadosCliente[22]);
				}

				// Número Telefone
				if (dadosCliente[23] != null) { // 23
					clienteFone.setTelefone((String) dadosCliente[23]);
				}

				// Ramal
				if (dadosCliente[24] != null) { // 24
					clienteFone.setRamal((String) dadosCliente[24]);
				}

				clientesFone.add(clienteFone);
				
				
			}

			clienteEmitirBoletimCadastroHelper.setClienteFone(clientesFone);

		}

		return clienteEmitirBoletimCadastroHelper;

	}
	
	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra o Cliente usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, String idEsferaPoder,
		    Integer numeroPagina, String nomeClienteReceitaFederal,
		    String tipoPesquisaNomeCliente) throws ControladorException {
		
		
		Collection colecaoDadosCliente = null;
		Collection colecaoClientes = null;

		try {

			colecaoDadosCliente = this.repositorioCliente
					.filtrarCliente(codigo,
							cpf,
							rg,
							cnpj,
							nome,
							nomeMae,		
							cep,
							idMunicipio,
							idBairro,
							idLogradouro,
							indicadorUso,
							tipoPesquisa,
							tipoPesquisaNomeMae,
							clienteTipo, idEsferaPoder,
						    numeroPagina, nomeClienteReceitaFederal,
						    tipoPesquisaNomeCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (colecaoDadosCliente != null && !colecaoDadosCliente.isEmpty()) {
			
			Iterator iteratorColecaoDadosCliente = colecaoDadosCliente.iterator();
			Cliente cliente =  null;
			colecaoClientes = new ArrayList();
			
			while(iteratorColecaoDadosCliente.hasNext()){
				
				cliente = new Cliente();
				
				Object[] array = (Object[]) iteratorColecaoDadosCliente.next();
				
				// codigo
				if (array[0] != null) {
					cliente.setId((Integer) array[0]);
				}
				
				// nome
				if (array[1] != null) {
					cliente.setNome((String) array[1]);
				}
				
				// rg
				if (array[2] != null) {
					cliente.setRg((String) array[2]);
				}

				// cpf
				if (array[3] != null) {
					cliente.setCpf((String) array[3]);
				}

				// cnpj
				if (array[4] != null) {
					cliente.setCnpj((String) array[4]);
				}
				
				ClienteTipo tipoCliente = null;
				// indicadorPessoaFisicaJuridica
				if (array[5] != null) {
					tipoCliente = new ClienteTipo();
					tipoCliente.setIndicadorPessoaFisicaJuridica((Short)array[5]);
					cliente.setClienteTipo(tipoCliente);
				}
				
				// descricao orgaoExpedidorRg
				if (array[6] != null) {
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg.setDescricao((String) array[6]);
					cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
				}
				
				// silga orgaoExpedidorRg
				if (array[7] != null) {
					UnidadeFederacao unidadeFederacao  = new UnidadeFederacao();
					
					unidadeFederacao.setSigla((String) array[7]);
					cliente.setUnidadeFederacao(unidadeFederacao);
				}
				// descricao tipo cliente
				if (array[8] != null) {
					if(tipoCliente == null){
						tipoCliente = new ClienteTipo();
					}
					tipoCliente.setDescricao((String)array[8]);
					cliente.setClienteTipo(tipoCliente);
				}
				
				// indicador uso
				if (array[9] != null) {
					cliente.setIndicadorUso((Short) array[9]);
				}
								
				
				colecaoClientes.add(cliente);
			}
			
		}

		return colecaoClientes;
	}
	
	
	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra a quantidade de Clientes usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object filtrarQuantidadeCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, 
			String idEsferaPoder,
			String nomeClienteReceitaFederal,
			String tipoPesquisaNomeCliente) throws ControladorException{ 	
		
		Object quantidade = null;
		Integer retorno = null;
		
		try {
			quantidade = repositorioCliente.filtrarQuantidadeCliente(
					 codigo,
					 cpf,
					 rg,
					 cnpj,
					 nome,
					 nomeMae,		
					 cep,
					 idMunicipio,
					 idBairro,
					 idLogradouro,
					 indicadorUso,
					 tipoPesquisa,
					 tipoPesquisaNomeMae,
					 clienteTipo, idEsferaPoder,
					 nomeClienteReceitaFederal, tipoPesquisaNomeCliente);  
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		if(quantidade != null){
			retorno = (Integer) quantidade;
			
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Cliente, indicador de uso, motivo
	 * do fim da relação, pelo perfil do imóvel e pelo tipo da relação do cliente carregando o imóvel
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloClienteTarifaSocial(Integer idCliente)
			throws ControladorException {

		try {
			return repositorioClienteImovel.pesquisarClienteImovelPeloClienteTarifaSocial(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Imóvel carregando o imóvel, o cliente, o perfil do imóvel, 
	 * o orgão expedidor do RG e a unidade da federação
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelTarifaSocial(Integer idImovel) 
			throws ControladorException {

		try {
			return repositorioClienteImovel.pesquisarClienteImovelPeloImovelTarifaSocial(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Imóvel carregando os dados necessários para retornar o seu endereço 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelParaEndereco(Integer idImovel) 
			throws ControladorException {

		try {
			return repositorioClienteImovel.pesquisarClienteImovelPeloImovelParaEndereco(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * 
	 * Verifica se é usuario iquilino ou não
	 *
	 * @author Sávio Luiz
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public boolean verificaUsuarioinquilino(Integer idImovel) throws ControladorException{
		Collection colecao = null;
		Integer idClienteUsuario = null;
		boolean naoInquilino = false;
		try {
			idClienteUsuario = repositorioClienteImovel.retornaIdClienteUsuario(idImovel);
			colecao = repositorioClienteImovel.retornaClientesRelacao(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		if(colecao != null && !colecao.isEmpty()){
			Iterator iteParmsCliente = colecao.iterator();
			while(iteParmsCliente.hasNext()){
				Integer idClienteBase = (Integer)iteParmsCliente.next();
				if(idClienteBase.equals(idClienteUsuario)){
					naoInquilino = true;
					break;
				}
				
			}
		}else{
			naoInquilino = true;
		}
		return naoInquilino;
	}
	
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ControladorException {
		
		try {
		
			this.repositorioClienteEndereco
					.atualizarLogradouroCep(logradouroCepAntigo, logradouroCepNovo);
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ControladorException {
		
		try {
		
			this.repositorioClienteEndereco
					.atualizarLogradouroBairro(logradouroBairroAntigo, logradouroBairroNovo);
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0544] Gerar Arwuivo Texto do Faturamento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Flávio Cordeiro
	 * @date 4/04/2006
	 * 
	 * 
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovelGerarArquivoFaturamento()
		throws ControladorException {
		
		try {
		
			Collection clientes = new HashSet();
			
			Collection colecaoObjetos = this.repositorioClienteImovel
					.pesquisarClienteImovelGerarArquivoFaturamento();
			if(!colecaoObjetos.isEmpty()){
				Iterator iterator = colecaoObjetos.iterator();
				while(iterator.hasNext()){
					Object[] objeto = (Object[])iterator.next();
					
					Cliente cliente = new Cliente();
					if(objeto[0] != null){
						cliente.setId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						cliente.setNome((String)objeto[1]);
					}
					clientes.add(cliente);
				}
			}
			return clientes;
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0864] Gerar Certidão Negativa por Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente) throws ControladorException {
	
		try {

			return this.repositorioCliente.pesquisarClientesAssociadosResponsavel(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Pesquisa o rg do cliente do parcelamento a partir do idParcelamento
	 * Autor: Vivianne Sousa 
	 * Data: 20/06/2007
	 */
	public Cliente pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(Integer idParcelamento)
			throws ControladorException {

		Cliente cliente = null;
		Object[] dadosCliente = null;

		try {
			dadosCliente = this.repositorioClienteImovel
					.pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(idParcelamento);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosCliente != null) {
			
			cliente = new Cliente();
			
					
			if (dadosCliente[0] != null) {
				// orgão expedidor
				OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
				orgaoExpedidorRg.setDescricao((String)dadosCliente[0]);
				cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			}
			
			if (dadosCliente[1] != null) {
				// unidade federativa
				UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
				unidadeFederacao.setSigla((String) dadosCliente[1]);
				cliente.setUnidadeFederacao(unidadeFederacao);
			}
			
			if (dadosCliente[2] != null) {
				// rg
				cliente.setRg((String)dadosCliente[2]);
			}
			
		}

		return cliente;
	}
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 * @author Vivianne Sousa
	 * @date 27/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterIdENomeCliente(String cpf) throws ControladorException{
		Object[] dadosCliente = null;
		Cliente cliente = null;

		try {

			dadosCliente = this.repositorioCliente.obterIdENomeCliente(cpf);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosCliente != null) {
			cliente = new Cliente();
			// id do Cliente
			if (dadosCliente[0] != null) {
				cliente.setId((Integer)dadosCliente[0]);
			}
			
			// nome de Cliente
			if (dadosCliente[1] != null) {
				cliente.setNome((String)dadosCliente[1]);
			}
			
		}

		return cliente;
	}
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 *Alterado para registrar a transação na atualização
	 *do CPF do cliente.
	 *
	 * @author Anderson Italo, Vivianne Sousa
	 * @date 11/08/2009, 30/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarCPFCliente(String cpf,Integer idCliente, Usuario usuarioLogado) throws ControladorException{
	
		try {
			
			String zeros = "";
			for (int a = 0; a < (11 - cpf.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			cpf = zeros.concat(cpf);
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			
			Collection colecaoClientes = this.getControladorUtil().pesquisar(filtroCliente, Cliente.class.getName());
			
			Cliente cliente = (Cliente)Util.retonarObjetoDeColecao(colecaoClientes);
			
			cliente.setCpf(cpf);
			
			//------------ <REGISTRAR TRANSAÇÃO>----------------------------

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_CLIENTE_ATUALIZAR, cliente.getId(),
					cliente.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(cliente);
			String[] atributos = new String[1];
			atributos[0] = "cpf";
			//Registra a transação apenas para os atributos que quer-se registrar
			getControladorTransacao().registrarTransacao(cliente, atributos);
			
			// ------------ </REGISTRAR TRANSAÇÃO>----------------------------

			this.repositorioCliente.atualizarCPFCliente(cpf,idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	private ControladorTransacaoLocal getControladorTransacao() {
		ControladorTransacaoLocalHome localHome = null;
		ControladorTransacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorTransacaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_TRANSACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
/////////////////////////////////////////////////////////////////////////	
 	
	/**
	 * 
	 *Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Flávio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteUsuario(Integer idImovel)throws ControladorException{
	
		try {

			return this.repositorioClienteImovel.retornaClienteUsuario(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * 
	 *Retorna o cliente proprietario a partir do id do imovel
	 *
	 * @author Vinicius Medeiros
	 * @date 29/08/2008
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteProprietario(Integer idImovel) throws ControladorException{
	
		try {

			return this.repositorioClienteImovel.retornaClienteProprietario(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */

	public ClienteAtualizacaoCadastral obterClientetuAlizacaoCadastral(Integer idImovel, 
			Short idClienteRelacaoTipo) throws ControladorException{
	
		try {
			ClienteAtualizacaoCadastral cliente = null;	
			
			Object[] element =  this.repositorioCliente.obterDadosCliente(idImovel, idClienteRelacaoTipo);
			
			if (element != null) {				

				cliente = new ClienteAtualizacaoCadastral();		
			
				cliente.setIdCliente((Integer) element[0]);					
				
				cliente.setIdImovel(idImovel);
					
				if (element[1] != null){
					cliente.setNomeCliente((String) element[1]);
				}
				
				if (element[2] != null){
					cliente.setIdClienteTipo((Integer) element[2]);
				}
				
				if (element[3] != null){
					cliente.setCpfCnpj((String) element[3]);
				}else if(element[4] != null){
					cliente.setCpfCnpj((String) element[4]);
				}
				
				if (element[5] != null){
					cliente.setRg((String) element[5]);
				}
				
				if (element[6] != null){
					cliente.setDataEmissaoRg((Date) element[6]);
				}
				
				if (element[7] != null){
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg.setId((Integer) element[7]);
					cliente.setOrgaoExpedidorRG(orgaoExpedidorRg);
				}
				
				if (element[8] != null){
					cliente.setIdUinidadeFederacao((Integer)element[8]);
				}
				
				if (element[9] != null){
					cliente.setDataNascimento((Date) element[9]);
				}
				
				//Profissão ou Ramo de Atividade
				if(element[16] != null){
					if(((Short)element[16]).equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
						if (element[10] != null){
							cliente.setIdProfissao((Integer)element[10]);
						}	
					}else{
						if(element[15] != null){
							cliente.setIdRamoAtividade((Integer)element[15]);
						}	
					}
				}
				
				if (element[11] != null){
					cliente.setIdPessoaSexo((Integer)element[11]);
				}
				
				if (element[12] != null){
					cliente.setNomeMae((String) element[12]);
				}
				
				if (element[13] != null){
					cliente.setIndicadorUso((Short) element[13]);
				}
				
				if (element[14] != null){
					cliente.setEmail((String) element[14]);
				}
//				
//				if (element[15] != null){
//					cliente.setIdEnderecoTipo((Integer)element[15]);
//				}
//				
//				if(element[16] != null){
//					cliente.setIdLogradouro((Integer) element[16]);
//				}else if(element[17] != null){
//					cliente.setIdLogradouro((Integer) element[17]);
//				}
//				
//				//Logradouro
//				Collection colecaoEndereco = getControladorEndereco().pesquisarLogradouroCliente((Integer) element[0]);
//				if(colecaoEndereco != null && !colecaoEndereco.isEmpty()){
//					
//					Iterator enderecoIterator = colecaoEndereco.iterator();
//					
//					Object[] arrayEndereco = (Object[]) enderecoIterator.next();
//					
//					String nome = (String)arrayEndereco[0];				
//					cliente.setDescricaoLogradouro(nome);
//										
//					if(arrayEndereco[3] != null){
//						Integer idTipo = (Integer)arrayEndereco[3];
//						cliente.setIdLogradouroTipo(idTipo);
//						String tipo = (String)arrayEndereco[1];
//						cliente.setDsLogradouroTipo(tipo);
//					}
//					
//					if(arrayEndereco[4] != null){
//						Integer idTitulo = (Integer)arrayEndereco[4];
//						cliente.setIdLogradouroTitulo(idTitulo);
//						String titulo = (String)arrayEndereco[2];
//						cliente.setDsLogradouroTitulo(titulo);
//					}	
//					
//					if(arrayEndereco[5] != null){
//						Integer idMunicipio = (Integer)arrayEndereco[5];
//						cliente.setIdMunicipio(idMunicipio);
//						String nomeMunicipio = (String)arrayEndereco[6];
//						cliente.setNomeMunicipio(nomeMunicipio);
//					}
//					
//					if(arrayEndereco[7] != null){
//						Integer idUnidadeFederacao = (Integer)arrayEndereco[7];
//						cliente.setIdUinidadeFederacao(idUnidadeFederacao);
//						String dsUnidadeFederacao = (String)arrayEndereco[8];
//						cliente.setDsUFSiglaMunicipio(dsUnidadeFederacao);
//					}
//				}
//				
//				Cep
//				if(element[18] != null){
//					cliente.setCodigoCep((Integer) element[18]);
//				}
//				
//				//Bairro
//				if(element[19] != null){
//					cliente.setIdBairro((Integer) element[19]);
//				}
//				
//				//Descrição do bairro
//				if(element[20] != null){
//					cliente.setNomeBairro((String) element[20]);
//				}
//				
//				//Código de referência
//				if (element[21] != null){
//					cliente.setIdEnderecoReferencia((Integer) element[21]);
//				}
//				
//				//Número do imóvel
//				String numeroImovel = (String)element[22];
//				if(numeroImovel != null && !numeroImovel.trim().equals("")){
//					cliente.setNumeroImovel(numeroImovel);
//				}
//				
//				//Complemento do Imóvel
//				String trunk = ((String) element[23]);
//				
//				if ( trunk != null && trunk.length() > 25 ){
//					trunk = trunk.substring( 0, 24 );
//				}				
//								
//				if(element[23] != null){
//					cliente.setComplementoEndereco(trunk);
//				}
				
				cliente.setIdClienteRelacaoTipo(new Integer(idClienteRelacaoTipo));
				
				cliente.setIndicadorExclusao(ConstantesSistema.NAO);
				
				if(element[17] != null){
					cliente.setDataRelacaoInicio((Date) element[17]);
				}
				
				if(element[18] != null){
					cliente.setDataRelacaoFim((Date) element[18]);
				}
				
				if(element[19] != null){
					cliente.setNomeAbreviadoCliente((String) element[19]);
				}
			}
			
			return cliente;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente Fone
	 * @throws ControladorException
	 */

	public Collection obterDadosClienteFone(Integer idCliente)
			throws ControladorException {
	
		try {

			return this.repositorioCliente.obterDadosClienteFone(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
    /**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/09/2008
	 */
	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ControladorException{
		
		try {

			return this.repositorioCliente.verificaExistenciaClienteAtualizacaoCadastral(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0014] Manter Imóvel
	 * [FS0017] Registra Fim de Relação do(s) Cliente(s) com Imóvel
	 *
	 * @author Ana Maria
	 * @date 13/10/2008
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovel(Integer idImovel)
		throws ControladorException{
		try {

			return repositorioClienteImovel.pesquisarClienteImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}	
	}
	
	/**
	 * Pesquisa a quantidade de clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ControladorException {
		try {
			return this.repositorioCliente.pesquisarClienteResponsavelSuperiorParaPaginacaoCount(helper);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Pesquisa os clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina) throws ControladorException {
		
		try {
			return this.repositorioCliente.pesquisarClienteResponsavelSuperiorParaPaginacao(helper, numeroPagina);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @param idCliente, idImovel
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Ana Maria
     * @date 15/05/2009
	 * @exception ErroRepositorioException
	 */
	public ClienteAtualizacaoCadastral pesquisarClienteAtualizacaoCadastral(Integer idCliente, Integer idImovel, Integer idClienteRelacaoTipo)
		throws ControladorException {
	    try {
	        return repositorioCliente.pesquisarClienteAtualizacaoCadastral(idCliente, idImovel, idClienteRelacaoTipo);
	    } catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}

	/**
	 * 
	 * Pesquisar Cliente Fone Atualização Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection<ClienteFoneAtualizacaoCadastral> pesquisarClienteFoneAtualizacaoCadastral(Integer idCliente, Integer idMatricula, 
			Integer idTipoFone, Integer idClienteRelacaoTipo,String numeroFone)
		throws ControladorException {
	    try {
	        return repositorioCliente.pesquisarClienteFoneAtualizacaoCadastral(idCliente, idMatricula, idTipoFone, idClienteRelacaoTipo,numeroFone);
	    } catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * @author Daniel Alves
	 * @date 02/09/2010
	 * @param idClienteImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorNomeContaClienteImovel(int idClienteImovel) throws ControladorException{
		
		try{
			repositorioClienteImovel.atualizarIndicadorNomeContaClienteImovel(idClienteImovel);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
	
	/**
	 * 
	 * Atualiza telefone padrão
	 *
	 * @author Daniel Alves
	 * @date 06/09/2010
	 *
	 * @param idCliente
	 * @param idClienteFonePadrao  (novo telefone padrão do cliente).
	 * @throws ControladorException 
	 */
	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao) throws ControladorException{
		
		try{			
			repositorioCliente.atualizarTelefonePadrao(idCliente, idClienteFonePadrao);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
	/**
	 * Remove todos os telefones de um determinado cliente
	 * 
	 * @param idCliente
	 *            Código do cliente que terá seus telefones apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosTelefonesPorCliente(Integer idCliente) throws ControladorException{			
		try{			
			repositorioCliente.removerTodosTelefonesPorCliente(idCliente);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	
	/**
	 * 
	 * Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Mariana Victor
	 * @date 17/01/2011
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaDadosClienteUsuario(Integer idImovel) throws ControladorException {
		try{			
			return repositorioClienteImovel.retornaDadosClienteUsuario(idImovel);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	

	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 * 
	 * [SB0003] – Obter Nome do Cliente
	 * @author Mariana Victor
	 * @date 11/03/2011
	 * 
	 * */
	public String obterNomeCliente(Integer idImovel) throws ControladorException {
		
		try{
			
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			Cliente cliente  = this.repositorioClienteImovel.retornaDadosClienteUsuario(idImovel);
			
			if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().equals(ConstantesSistema.SIM)
				&& cliente.getIndicadorUsoNomeFantasiaConta().equals(ConstantesSistema.SIM)
				&& cliente.getNomeAbreviado() != null) {
				
				return cliente.getNomeAbreviado();
					
			} else {
				return cliente.getNome();
			}
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0002]–Verifica Critério Recadastramento
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel) throws ControladorException {
		try{			
			return repositorioCliente.pesquisarClienteUsuarioDoImovel(idImovel);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}

	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Clientes por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteCliente(String valor) throws ControladorException{
		try{			
			return repositorioCliente.filtrarAutocompleteCliente(valor);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Clientes Responsavel por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteClienteResponsavel(String valor) throws ControladorException{
		try{			
			return repositorioCliente.filtrarAutocompleteCliente(valor);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	
	/**
	 * Verifica Clientes Associados a um Cliente sem CNPJ ou ICPESSOAFISICAJURIDICA diferente de 2
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQtdClientesAssociadosResponsavelNaoJuridica(Integer idCliente)
			throws ControladorException{
		try{			
			return repositorioCliente.pesquisarQtdClientesAssociadosResponsavelNaoJuridica(idCliente);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Retorna Lista de Imóveis associados ao cliente
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo )throws ControladorException{
		try{			
			return repositorioCliente.pesquisarImoveisAssociadosCliente(idCliente, relacaoTipo);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos Através da Loja Virtual
	 * 
	 * Caso o CPF do cliente passado no parâmetro seja do cliente proprietário
	 * ou do cliente usuário do imóvel o método retorna o nome do cliente, caso
	 * contrário o método retorna null.
	 * 
	 * @author Diogo Peixoto
	 * @date 28/06/2011
	 * 
	 * @param CPFCliente
	 * @param Matricula
	 * 
	 * @throws ControladorException
	 * @return String
	 */
	public String validarCliente(String cpfCliente, Integer matricula) throws ControladorException{
		try{			
			return repositorioCliente.validarCliente(cpfCliente, matricula);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar os clientes a partir do imóvel e o tipo de relação com o cliente
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ControladorException{
		try{
			
			return repositorioCliente.obterClienteImovelporRelacaoTipo(idImovel,idRelacaoTipo);
				
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
	/**
	 * 
	 * Retorna o cliente responsável
	 *
	 * @author Sávio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavelIndicadorEnvioConta(Integer idImovel)
			throws ControladorException{
		try{
					
		  return repositorioClienteImovel.retornaIdClienteResponsavelIndicadorEnvioConta(idImovel);
					
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
				
	}

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public Cliente pesquisarDadosCliente(Integer idCliente)
			throws ControladorException {
		try{
					
		  return repositorioCliente.pesquisarDadosCliente(idCliente);
					
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
				
	}
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public ClienteImovel pesquisarClienteImovelOSFiscalizada(
			Integer idImovel) throws ControladorException{
		try{
			
			  return repositorioClienteImovel.pesquisarClienteImovelOSFiscalizada(idImovel);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public Cliente pesquisarDadosClientePessoaFisica(String cpf)throws ControladorException{
			try{
			
			  return repositorioCliente.pesquisarDadosClientePessoaFisica(cpf);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ClienteFone pesquisarDadosClienteFonePadrao(Integer idCliente) 
		throws ControladorException{
			try{
			
			  return repositorioCliente.pesquisarDadosClienteFonePadrao(idCliente);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ClienteEndereco pesquisarDadosClienteEnderecoCorrespondecia(Integer idCliente) throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosClienteEnderecoCorrespondecia(idCliente);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public Cliente pesquisarDadosClientePessoaJuridica(String cnpj) throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosClientePessoaJuridica(cnpj);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ConsultarReceitaFederal pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(String cpf) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(cpf);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ConsultarReceitaFederal pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(String cnpj) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(cnpj);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	

	/**
	 * UC[0009] manter Cliente
	 *
	 * @author Fernanda Almeida
	 * @date 15/12/2011
	 *
	 */
	public Collection<?> obterImovelCorporativo(Integer idCliente) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.obterImovelCorporativo(idCliente);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * 
	 * UC[0378] -Associar Tarifa de Consumo a Imóveis
	 *
	 * @author Fernanda Almeida
	 * @date 15/12/2011
	 *
	 */
	public Collection<Object[]> obterClientePorImovel(Integer idImovel) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.obterClientePorImovel(idImovel);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}

	/**
	 * Metodo responsavel por pesquisar todos os clientes cadastrado no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ErroRepositorioException
	 */
	public Collection<ClienteVirtual> pesquisarClienteVirtual(Date periodoInicial , Date periodoFinal , int numeroPagina, String idImovel, String situacaoCliente, String tempoCadastro) throws ControladorException {
		try{
			Collection<?> retorno = null;
			Collection<ClienteVirtual> colecaoCliente = new ArrayList<ClienteVirtual>();
			
			retorno = repositorioCliente.pesquisarClienteVirtual(periodoInicial, periodoFinal, numeroPagina, idImovel, situacaoCliente, tempoCadastro);
			
			if(!Util.isVazioOrNulo(retorno)){
				Iterator<?> iterator = retorno.iterator();
				
				while(iterator.hasNext()){
					ClienteVirtual clienteVirtual = (ClienteVirtual) iterator.next();
					
					FiltroClienteVirtual filtroClienteVirtual = new FiltroClienteVirtual();
					filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.ID_IMOVEL, clienteVirtual.getImovel().getId()));
					
					if(clienteVirtual.getCpf() != null){
						filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.CPF, clienteVirtual.getCpf()));
					}else if(clienteVirtual.getCnpj() != null){
						filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.CNPJ, clienteVirtual.getCnpj()));
					}
					
					Collection<?> colCliente = this.getControladorUtil().pesquisar(filtroClienteVirtual, ClienteVirtual.class.getName());
					
					if(!Util.isVazioOrNulo(colCliente)){
						Iterator iter = colCliente.iterator();
						while(iter.hasNext()){
							ClienteVirtual cliente = (ClienteVirtual) iter.next();
							if(!String.valueOf(clienteVirtual.getId()).equals(String.valueOf(cliente.getId()))){
								int numeroMeses = Util.dataDiff(clienteVirtual.getDataCadastro(), cliente.getDataCadastro());
								if(numeroMeses <= 1){
									String hint = "Existe mais de um cadastro para o mesmo imóvel.";
									clienteVirtual.setHintCadastro(hint);
									break;
								}
							}
						}
					}
					colecaoCliente.add(clienteVirtual);
				}
			}
			return colecaoCliente;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Metodo responsavel por pesquisar quantidade todos os clientes cadastrado no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeClienteVirtual(Date periodoInicial , Date periodoFinal, String idImovel, String situacaoCliente, String tempoCadastro ) throws  ControladorException {
		try{
		
		  return repositorioCliente.pesquisarQuantidadeClienteVirtual(periodoInicial, periodoFinal, idImovel, situacaoCliente, tempoCadastro);
					
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	

	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0005] - Gerar Negativação para o Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @date 06/12/2011
	 */
	public Short pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(
			Integer idImovel, Integer idClienteRelacaoTipo)throws ControladorException{
		try{
		  return repositorioClienteImovel.
			  pesquisarIndicadorNegativacaoPeriodoClienteResponsavel
			  (idImovel,idClienteRelacaoTipo);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0005] - Gerar Negativação para o Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @date 07/12/2011
	 */
	public Cliente pesquisarDadosClienteParaNegativacao(Integer idCliente, String cnpjEmpresa)
			throws ControladorException{
		try{
		  return repositorioCliente.pesquisarDadosClienteParaNegativacao(idCliente, cnpjEmpresa);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
	 * @author Vivianne Sousa
	 * @date 15/12/2011
	 */
	public boolean existeEnderecoParaCliente(Integer idCliente)throws ControladorException{
		try{
			boolean retorno = false;
			Integer idEnderecoCliente = repositorioCliente.pesquisarEnderecoClienteParaNegativacao(idCliente);
			if(idEnderecoCliente != null){
				retorno = true;
			}
			return retorno;
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Metodo responsavel por pesquisar situacao dos clientes cadastrado no portal.
	 * 
	 * @author Davi Menezes
	 * @date 03/04/2011
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ControladorException
	 */
	public Collection<ClienteVirtual> pesquisarSituacaoClienteCadastradoPortal(Integer idImovel) 
			throws ControladorException {
		try{
			return repositorioCliente.pesquisarSituacaoClienteCadastradoPortal(idImovel);
		}catch(ErroRepositorioException ex){
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Metodo responsavel por pesquisar todos os clientes cadastrado no portal por um intervalo de tempo.
	 * 
	 * @author Davi Menezes
	 * @date 04/04/2012
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioClienteVirtualBean> pesquisarClienteVirtual(Date periodoInicial , Date periodoFinal , String idImovel, String situacaoCliente, String tempoCadastro) 
			throws ControladorException {
		try{
			
			Collection<RelatorioClienteVirtualBean> colecaoBean = null;
			
			Collection<ClienteVirtual> colecaoClientes = 
				repositorioCliente.pesquisarClienteVirtual(periodoInicial, periodoFinal, idImovel, situacaoCliente, tempoCadastro);
			
			if(!Util.isVazioOrNulo(colecaoClientes)){
				colecaoBean = new ArrayList<RelatorioClienteVirtualBean>();
				Iterator<?> iterator = colecaoClientes.iterator();
				while(iterator.hasNext()){
					RelatorioClienteVirtualBean bean = new RelatorioClienteVirtualBean();
					ClienteVirtual clienteVirtual = (ClienteVirtual) iterator.next();
					
					bean.setSituacao(Integer.parseInt(clienteVirtual.getCodigoSituacao().toString()));
					bean.setDescricaoSituacao(clienteVirtual.getCodigoSituacaoFormatado());
					
					if(clienteVirtual.getIndicadorPessoaFisicaJuridica().toString().equals("1")){
						bean.setIndicadorPessoa("1");
						bean.setCpfCnpj(clienteVirtual.getCpfFormatado());
						bean.setProfissaoAtividade(clienteVirtual.getProfissao().getDescricao());
					}else{
						bean.setIndicadorPessoa("2");
						bean.setCpfCnpj(clienteVirtual.getCnpjFormatado());
						bean.setProfissaoAtividade(clienteVirtual.getRamoAtividade().getDescricao());
					}
					
					if(clienteVirtual.getImovel() != null){
						bean.setIdImovel(clienteVirtual.getImovel().getMatriculaFormatada());
					}else{
						bean.setIdImovel("");
					}
					
					if(clienteVirtual.getNome() != null){
						bean.setNomeCliente(clienteVirtual.getNome());
					}else{
						bean.setNomeCliente("");
					}
					
					if(clienteVirtual.getRg() != null){
						bean.setRg(clienteVirtual.getRg());
					}else{
						bean.setRg("");
					}
					
					if(clienteVirtual.getDataEmissaoRg() != null){
						bean.setDataEmissao(Util.formatarData(clienteVirtual.getDataEmissaoRg()));
					}else{
						bean.setDataEmissao("");
					}
					
					if(clienteVirtual.getDataNascimento() != null){
						bean.setDataNascimento(Util.formatarData(clienteVirtual.getDataNascimento()));
					}else{
						bean.setDataNascimento("");
					}
					
					if(clienteVirtual.getEmail() != null){
						bean.setEmail(clienteVirtual.getEmail());
					}else{
						bean.setEmail("");
					}
					
					if(clienteVirtual.getNomeMae() != null){
						bean.setNomeMae(clienteVirtual.getNomeMae());
					}else{
						bean.setNomeMae("");
					}
					
					if(clienteVirtual.getDdd() != null){
						bean.setDdd(clienteVirtual.getDdd());
					}else{
						bean.setDdd("");
					}
					
					if(clienteVirtual.getNumeroTelefone() != null){
						bean.setNumeroTelefone(clienteVirtual.getNumeroTelefone());
					}else{
						bean.setNumeroTelefone("");
					}
					
					if(clienteVirtual.getNumeroFoneRamal() != null){
						bean.setRamal(clienteVirtual.getNumeroFoneRamal());
					}else{
						bean.setRamal("");
					}
					
					if(clienteVirtual.getNomeContato() != null){
						bean.setNomeContato(clienteVirtual.getNomeContato());
					}else{
						bean.setNomeContato("");
					}
					
					if(clienteVirtual.getOrgaoExpedidorRg() != null){
						bean.setOrgaoExpedidor(clienteVirtual.getOrgaoExpedidorRg().getDescricaoAbreviada());
					}else{
						bean.setOrgaoExpedidor("");
					}
					
					if(clienteVirtual.getUnidadeFederacao() != null){
						bean.setEstado(clienteVirtual.getUnidadeFederacao().getSigla());
					}else{
						bean.setEstado("");
					}
					
					if(clienteVirtual.getPessoaSexo() != null){
						bean.setSexo(clienteVirtual.getPessoaSexo().getDescricao());
					}else{
						bean.setSexo("");
					}
					
					if(clienteVirtual.getFoneTipo() != null){
						bean.setTipoTelefone(clienteVirtual.getFoneTipo().getDescricao());
					}else{
						bean.setTipoTelefone("");
					}
					colecaoBean.add(bean);
				}
			}
			
			return colecaoBean;
			
		}catch(ErroRepositorioException ex){
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0001] Inserir Municipio
	 * 
	 * @author Carlos Chaves
	 * @date 04/06/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarDadosClientePrefeitura(Integer idClinete) throws ControladorException {
		try{			
			return repositorioCliente.pesquisarDadosClientePrefeitura(idClinete);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	/**
	 * Pesquisa CPF ou CNPJ do cliente 
	 * 
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0001] - Validar Atributo Cpf Cnpj
	 * 
	 * @author Vivianne Sousa
	 * @date 09/07/2012
	 * 
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String retornaCPFCNPJCliente(Integer idCliente)throws ControladorException {
		try{			
			Object[] dadosCPFCNPJ =  repositorioCliente.pesquisarCPFCNPJCliente(idCliente);
			String retorno = null;
			
			if(dadosCPFCNPJ != null){
				if(dadosCPFCNPJ[0] != null){
					retorno = (String)dadosCPFCNPJ[0];
				}else if(dadosCPFCNPJ[1] != null){
					retorno = (String)dadosCPFCNPJ[1];
				}
			}
			
			return retorno;
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Pesquisa o telefone padrão do cliente pelo seu id 
	 * 
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * 
	 * @author Vivianne Sousa
	 * @date 09/07/2012
	 * 
	 * @return ClienteFone
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteFonePadrao(Integer idCliente)
			throws ControladorException {
		try{			
			return repositorioCliente.pesquisarClienteFonePadrao(idCliente);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosClienteFoneAtualizacaoCadastral
		(Integer idClienteAtualizacaoCadastral) throws ControladorException {
			try{			
				return repositorioCliente.obterDadosClienteFoneAtualizacaoCadastral(idClienteAtualizacaoCadastral);
				
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC0150] - Retifcar Conta
	 * Pesquisar Cliente Imovel
	 * 
	 * @param idImovel
	 * @param indicadoNomeConta
	 * @author Anderson Cabral
	 * @date 27/09/2012
	 * @throws ErroControladorException
	 */
	public ClienteImovel pesquisarClienteImovel(String idImovel, int indicadoNomeConta)	throws ControladorException {
		try {
			return repositorioClienteImovel.pesquisarClienteImovel(idImovel, indicadoNomeConta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC 1366] - Alterar Vínculo de Clientes com o Imóvel e Contas
	 * [FE 0002] - Validar Período Inicial do Vínculo de Cliente com imóvel
	 * 
	 * @author Davi Menezes
	 * @date 04/10/2012
	 */
	public Date pesquisarMenorDataClienteImovel (Integer idImovel) throws ControladorException{
		try{
			return repositorioClienteImovel.pesquisarMenorDataClienteImovel(idImovel);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC 1366] - Alterar Vínculo de Clientes com o Imóvel e Contas
	 * [SB 0003] - Alterar Vínculo Clientes Imóvel
	 * 
	 * @author Davi Menezes
	 * @date 05/10/2012
	 */
	public void alterarVinculoClienteImovel(Collection colClienteImovelHelper, 
			Collection colClienteImovelHelperRemovidos, Integer idImovel, Usuario usuarioLogado) throws ControladorException {
		
		try{
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema(); 
			
			if(!Util.isVazioOrNulo(colClienteImovelHelperRemovidos)){
				this.manterClienteImovel(colClienteImovelHelperRemovidos, "2");
			}
			
			this.manterClienteImovel(colClienteImovelHelper, "1");
			
			Imovel imovel = this.getControladorImovel().pesquisarImovel(idImovel);
			
			if(imovel != null){
				imovel.setIndicadorVinculoCliente(ConstantesSistema.SIM);
				imovel.setUltimaAlteracao(new Date());
				
				this.getControladorUtil().atualizar(imovel);
			}
			
			Collection<Conta> colecaoConta = repositorioFaturamento.pesquisarContasImovel(idImovel);
			
			if(!Util.isVazioOrNulo(colecaoConta)){
				
				Conta conta = null;
				Date dataLeitura = null;
				Collection dadosClienteImovel = null;
				ClienteConta clienteConta = null;
				Integer idClienteImovel = null;
				Integer idClienteRelacaoTipo = null;
				Short indicadorNomeConta = null;
				
				boolean temPermissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VINCULO_CLIENTE_CONTA_ACIMA_LIMITE, usuarioLogado);
				
				Iterator<?> iteratorConta = colecaoConta.iterator();
				
				while(iteratorConta.hasNext()){
					conta = (Conta) iteratorConta.next();
					boolean alterouVinculoConta = false;
					//obter data de leitura da conta
					dataLeitura = this.obterDataLeituraConta(conta);
					
					if(dataLeitura != null){
					
						//pesquisa os clientes responsaveis pela conta de acordo com a data de leitura da conta
						dadosClienteImovel = repositorioClienteImovel.pesquisarClienteImovelPeriodo(idImovel, dataLeitura);
						
						if( dadosClienteImovel != null && !dadosClienteImovel.isEmpty() ){
							
							//pra cada cliente do imovel verifica se ja existe na tabela de cliente conta, caso contrario inseri um novo registro.
							Iterator<?> itClienteImovel  = dadosClienteImovel.iterator();
							while(itClienteImovel.hasNext()){
								
								Object [] array = (Object []) itClienteImovel.next();
								
								idClienteImovel = (Integer) array[0];
								idClienteRelacaoTipo = (Integer) array[1];
								indicadorNomeConta = (Short) array[2];
						
								if(idClienteImovel != null){
									
									//pesquisa o cliente conta cadastrado
									ClienteConta clienteContaCadastrado = existeClienteConta(idClienteImovel, conta.getId(), idClienteRelacaoTipo);
									
									//caso o cliente exista verifica o indicador nome conta
									if ( clienteContaCadastrado != null ) {
										if ( !clienteContaCadastrado.getIndicadorNomeConta().equals(indicadorNomeConta) ) {
											//caso o indicador nome conta seja diferente, atualiza o indicador nome conta.
											clienteContaCadastrado.setIndicadorNomeConta(indicadorNomeConta);
											getControladorUtil().atualizar(clienteContaCadastrado);
										}
										
									} else {
										
										ClienteConta clienteContaInserir = new ClienteConta();
										clienteContaInserir.setCliente(new Cliente(idClienteImovel));
										clienteContaInserir.setClienteRelacaoTipo(new ClienteRelacaoTipo(idClienteRelacaoTipo));
										clienteContaInserir.setConta(conta);
										clienteContaInserir.setIndicadorNomeConta(indicadorNomeConta);
										clienteContaInserir.setUltimaAlteracao(new Date());
										
										
										this.getControladorUtil().inserir(clienteContaInserir);
										alterouVinculoConta = true;
									}
								}
							}
							
							//pesquisa todos os clientes contas
							Collection colecaoClienteConta = repositorioFaturamento.pesquisarClienteConta(conta.getId());
							
							ClienteContaAnterior clienteContaAnterior = null;
							Iterator<?> itClienteConta = colecaoClienteConta.iterator();
							while(itClienteConta.hasNext()){
								clienteConta = (ClienteConta) itClienteConta.next();
								
								//verifica se o cliente da conta, ainda possui vinculo com o imóvel. Caso nao possua remove de cliente conta e inseri em cliente conta anterior
								if ( !validaClienteVinculoComImovel(clienteConta.getCliente().getId(), idImovel, clienteConta.getClienteRelacaoTipo().getId(), dataLeitura) ) {
									
									clienteContaAnterior = new ClienteContaAnterior();
									
									clienteContaAnterior.setDataVinculo(new Date());
									clienteContaAnterior.setCliente(clienteConta.getCliente());
									clienteContaAnterior.setClienteRelacaoTipo(clienteConta.getClienteRelacaoTipo());
									ContaGeral contaGeral = new ContaGeral();
									contaGeral.setId(conta.getId());
									contaGeral.setConta(conta);
									clienteContaAnterior.setContaGeral(contaGeral);
									clienteContaAnterior.setIndicadorNomeConta(clienteConta.getIndicadorNomeConta());
									clienteContaAnterior.setUsuario(usuarioLogado);
									clienteContaAnterior.setUltimaAlteracao(new Date());
									
									this.getControladorUtil().inserir(clienteContaAnterior);
									this.getControladorUtil().remover(clienteConta);
									alterouVinculoConta = true;
									
									
									getControladorSpcSerasa().verificarRelacaoDoCancelamentoComItensNegativacao(conta, null);
								}
							}
						}
						
						if(alterouVinculoConta){
							if(!temPermissaoEspecial){
								Integer qtdAlteracoes = conta.getQuantidadeAlteracaoVinculo();
								Integer qtdMaxPermitida = sistemaParametro.getNumeroVezesAlterarVinculo();
								
								if(qtdAlteracoes >= qtdMaxPermitida){
									throw new ActionServletException("atencao.limite_maxito_alterar_vinculo_ultrapassado");
								}
							}
							
							//Atualizar a quantidade de vínculos alterados
							repositorioFaturamento.atualizarQuantidadeVinculosAlteradosConta(conta.getId());							
						}

					}
				}
				
				//Recuperar o id do Registro Atendimento
				Integer idRA = this.getControladorRegistroAtendimento().obterRAVinculo(idImovel);
				
				//[UC 0435] - Encerrar Registro de Atendimento
				AtendimentoMotivoEncerramento motivoEncerramento = new AtendimentoMotivoEncerramento();
				motivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
				
				//Registro Atendimento
				RegistroAtendimento registroAtendimento = 
						this.getControladorRegistroAtendimento().pesquisarRegistroAtendimentoPorRA(idRA);
				registroAtendimento.setAtendimentoMotivoEncerramento(motivoEncerramento);
				registroAtendimento.setParecerEncerramento("RA encerrada pela funcionalidade Alterar Vinculo Cliente Com Imovel e Contas");
				registroAtendimento.setDataEncerramento(new Date());
				
				UnidadeOrganizacional unidadeOrganizacional = this.getControladorRegistroAtendimento()
						.obterUnidadeAtualRA(idRA);
				
				/*
				 * Caso a Unidade Atual da RA seja nula, pesquisar
				 * a unidade do usuário logado
				 */
				if(unidadeOrganizacional == null){
					unidadeOrganizacional = this.getControladorUnidade().pesquisarUnidadeUsuario(usuarioLogado.getId());
				}
				
				//Atendimento Relação Tipo
				AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
				atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
				
				//Registro Atendimento Unidade
				RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
				registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
				registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
				registroAtendimentoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
				registroAtendimentoUnidade.setUsuario(usuarioLogado);
				registroAtendimentoUnidade.setUltimaAlteracao(new Date());
				
				this.getControladorRegistroAtendimento().encerrarRegistroAtendimento(
						registroAtendimento, 
						registroAtendimentoUnidade, 
						usuarioLogado, 
						null, null, null, null, false, null, false);
			}
		
		}catch (ErroRepositorioException e){
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}catch (ControladorException e){
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}catch (ActionServletException e){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.limite_maxito_alterar_vinculo_ultrapassado", e);
		}
	}
	
	/**
	 * Metodo responsavel por verificar se existe cliente imovel.
	 * @param idCliente
	 * @param idImovel
	 * @param tipoRelacao
	 * @return
	 */
	public boolean validaClienteVinculoComImovel(Integer idCliente, Integer idImovel, Integer tipoRelacao, Date dataLeitura){
		
		boolean clienteComVinculo = false;

		try {
			
			Collection colecaoClienteImovel = repositorioClienteImovel.pesquisarClienteImovelPeriodo(idCliente, idImovel, tipoRelacao, dataLeitura);
			
			if ( colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty() ) {
				clienteComVinculo = true;
			}
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return clienteComVinculo;
	}
	
	/**
	 * Metodo responsavel por verificar se existe o cliente conta.
	 * @param idCliente
	 * @param idConta
	 * @param idTipoRelacao
	 * @return
	 */
	public ClienteConta existeClienteConta(Integer idCliente, Integer idConta, Integer idTipoRelacao){
		
		//Cliente conta possui a chave (conta, cliente e tipo de relacao)
		FiltroClienteConta filtroClienteConta = new FiltroClienteConta();
		filtroClienteConta.adicionarParametro( new ParametroSimples( FiltroClienteConta.CLIENTE_ID, idCliente));
		filtroClienteConta.adicionarParametro( new ParametroSimples( FiltroClienteConta.CONTA_ID, idConta));
		filtroClienteConta.adicionarParametro( new ParametroSimples( FiltroClienteConta.CLIENTE_RELACAO_TIPO, idTipoRelacao));
		ClienteConta clienteConta = null;
		try {
			
			Collection<ClienteConta> colecaoClienteConta = getControladorUtil().pesquisar(filtroClienteConta, ClienteConta.class.getName());
		
			if ( colecaoClienteConta != null && !colecaoClienteConta.isEmpty() ) {
				clienteConta = (ClienteConta)Util.retonarObjetoDeColecao(colecaoClienteConta);
			}
		
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		return clienteConta;
	}
	
	private Date obterDataLeituraConta(Conta conta) throws ControladorException{
		try {
			Date dataLeitura = null;
			
			Integer idImovel = conta.getImovel().getId();
			Integer anoMesReferencia = conta.getReferencia();
			dataLeitura = repositorioMicromedicao.obterDataLeituraMovimentoRoteiroEmpresa(idImovel, anoMesReferencia);
			
			if(dataLeitura == null){
				dataLeitura = repositorioMicromedicao.obterDataLeituraMedicaoHistorico(idImovel, anoMesReferencia);
				
				if(dataLeitura == null){
					Integer idFaturamentoGrupo = repositorioFaturamento.pesquisarFaturamentoGrupoImovel(idImovel);
					
					if(idFaturamentoGrupo != null){
						dataLeitura = repositorioFaturamento.obterDataLeituraConta(idFaturamentoGrupo, anoMesReferencia);
						
						if ( dataLeitura == null ) {
							dataLeitura = repositorioFaturamento.obterDataLeituraConta(idFaturamentoGrupo, anoMesReferencia);
						}
					}
					
					if ( dataLeitura == null ) {
						dataLeitura = conta.getDataEmissao();
					}
				}
			}
			
			return dataLeitura;
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	private void manterClienteImovel(Collection colecaoClienteImovelHelper, String tipo) throws ControladorException {
		if(tipo.equals("2")){
			ClienteImovelHelper clienteImovelHelper = null;
			ClienteImovel clienteImovel = null;
			Iterator<?> it = colecaoClienteImovelHelper.iterator();
			while(it.hasNext()){
				clienteImovelHelper = (ClienteImovelHelper) it.next();
				
				if(clienteImovelHelper.getIdClienteImovel() != null){
					clienteImovel = new ClienteImovel();
					clienteImovel.setId(Integer.parseInt(clienteImovelHelper.getIdClienteImovel()));
					clienteImovel.setCliente(new Cliente(Integer.parseInt(clienteImovelHelper.getIdCliente())));
					clienteImovel.setImovel(new Imovel(Integer.parseInt(clienteImovelHelper.getIdImovel())));
					clienteImovel.setDataInicioRelacao(Util.converteStringParaDate(clienteImovelHelper.getDataInicioRelacao()));
					clienteImovel.setIndicadorNomeConta(Short.parseShort(clienteImovelHelper.getIndicadorNomeConta()));
					clienteImovel.setClienteRelacaoTipo(new ClienteRelacaoTipo(Integer.parseInt(clienteImovelHelper.getIdClienteRelacaoTipo())));
					
					if(clienteImovelHelper.getDataFimRelacao() != null && !clienteImovelHelper.getDataFimRelacao().equals("")){
						clienteImovel.setDataFimRelacao(Util.converteStringParaDate(clienteImovelHelper.getDataFimRelacao()));
					
						if(clienteImovelHelper.getIdClienteImovelFimRelacaoMotivo() != null && 
								!clienteImovelHelper.getIdClienteImovelFimRelacaoMotivo().equals("")){
							ClienteImovelFimRelacaoMotivo fimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
							fimRelacaoMotivo.setId(Integer.parseInt(clienteImovelHelper.getIdClienteImovelFimRelacaoMotivo()));
						
							clienteImovel.setClienteImovelFimRelacaoMotivo(fimRelacaoMotivo);
						}
					}
					
					getControladorUtil().remover(clienteImovel);
				}
			}
		}else if(tipo.equals("1")){
			ClienteImovelHelper clienteImovelHelper = null;
			ClienteImovel clienteImovel = null;
			Iterator<?> it = colecaoClienteImovelHelper.iterator();
			while(it.hasNext()){
				clienteImovelHelper = (ClienteImovelHelper) it.next();
				clienteImovel = new ClienteImovel();
				
				if(clienteImovelHelper.getIdClienteImovel() != null){
					clienteImovel.setId(Integer.parseInt(clienteImovelHelper.getIdClienteImovel()));
				}
				
				if(clienteImovelHelper.getDataInicioRelacao() != null){
					clienteImovel.setDataInicioRelacao(Util.converteStringParaDate(clienteImovelHelper.getDataInicioRelacao()));
				}
				
				if(clienteImovelHelper.getDataFimRelacao() != null){
					clienteImovel.setDataFimRelacao(Util.converteStringParaDate(clienteImovelHelper.getDataFimRelacao()));
					
					if(clienteImovelHelper.getIdClienteImovelFimRelacaoMotivo() != null){
						ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
						clienteImovelFimRelacaoMotivo.setId(Integer.parseInt(clienteImovelHelper.getIdClienteImovelFimRelacaoMotivo()));
						clienteImovel.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
					}else{
						ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
						clienteImovelFimRelacaoMotivo.setId(ClienteImovelFimRelacaoMotivo.ATUALIZACAO_VINCULO_CLIENTE);
						clienteImovel.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
					}
				}
				
				if(clienteImovelHelper.getIdImovel() != null){
					clienteImovel.setImovel(new Imovel(Integer.parseInt(clienteImovelHelper.getIdImovel())));
				}
				
				if(clienteImovelHelper.getIndicadorNomeConta() != null){
					clienteImovel.setIndicadorNomeConta(Short.parseShort(clienteImovelHelper.getIndicadorNomeConta()));
				}else{
					clienteImovel.setIndicadorNomeConta(ConstantesSistema.NAO);
				}
				
				if(clienteImovelHelper.getIdCliente() != null){
					clienteImovel.setCliente(new Cliente(Integer.parseInt(clienteImovelHelper.getIdCliente())));
				}
				
				if(clienteImovelHelper.getIdClienteRelacaoTipo() != null){
					clienteImovel.setClienteRelacaoTipo(new ClienteRelacaoTipo(Integer.parseInt(clienteImovelHelper.getIdClienteRelacaoTipo())));
				}
				
				clienteImovel.setUltimaAlteracao(new Date());
				
				getControladorUtil().inserirOuAtualizar(clienteImovel);
			}
		}
	}
		
	/**
	 * Pesquisar Cliente Imovel
	 * 
	 * @param idImovel
	 * @author Anderson Cabral
	 * @date 03/10/2012
	 * @throws ErroRepositorioException
	 */
	public Collection<ClienteImovel> pesquisarClientesImovelDadosComplementares(Integer idImovel)
			throws ControladorException {
	
		try {
			return this.repositorioClienteImovel.pesquisarClientesImovelDadosComplementares(idImovel);
	
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	/**
	 * [UC1388] Consultar Quantidade Acessos a Base da Receita Federal
	 *
	 * @author Ricardo Germinio
	 * @date 27/11/2012
	 *
	 */
	public Collection<QuantidadeAcessosReceitaFederal> consultarQuantidadeAcessosReceitaFederal(Date dataInicial, Date dataFinal , String mensagemRetorno) throws ControladorException {
		try{
			return repositorioCliente.consultarQuantidadeAcessosReceitaFederal(dataInicial, dataFinal, mensagemRetorno);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * [UC1388] Consultar Quantidade Acessos a Base da Receita Federal
	 *
	 * @author Ricardo Germinio
	 * @date 27/11/2012
	 *
	 */
	public Collection<QuantidadeAcessosReceitaFederal> filtrarQuantidadeAcessosReceitaFederal(
			FiltrarQuantidadeAcessosReceitaFederalHelper helper)throws ControladorException{
		try {
			
			Collection<QuantidadeAcessosReceitaFederal> colecaoQuantidadeAcessosReceitaFederal;
			Collection<QuantidadeAcessosReceitaFederal> retorno = null;
			colecaoQuantidadeAcessosReceitaFederal = repositorioCliente.filtrarQuantidadeAcessosReceitaFederal(helper);

			if (colecaoQuantidadeAcessosReceitaFederal != null 
				&& !colecaoQuantidadeAcessosReceitaFederal.isEmpty()) {
				
				Iterator receitaFederalIterator = colecaoQuantidadeAcessosReceitaFederal.iterator();
				QuantidadeAcessosReceitaFederal quantidadeAcessosReceitaFederal = null;
				
				retorno = new ArrayList<QuantidadeAcessosReceitaFederal>();
								
				while (receitaFederalIterator.hasNext()) {
				
					quantidadeAcessosReceitaFederal = new QuantidadeAcessosReceitaFederal();
				
					Object[] arrayAcessosReceitaFederal = (Object[]) receitaFederalIterator.next();
					
					Util.formatarData(quantidadeAcessosReceitaFederal.getDataAcesso());					
					quantidadeAcessosReceitaFederal.setMensagemRetorno((String) arrayAcessosReceitaFederal[0]);
					quantidadeAcessosReceitaFederal.setQuantidadeAcessos((Integer) arrayAcessosReceitaFederal[1]);
					
					retorno.add(quantidadeAcessosReceitaFederal);
					
				}
			}
			
			return retorno;
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}	
	}
	
	/**
	 * [UC1388] Consultar Quantidade Acessos a Base da Receita Federal
	 *
	 * @author Ricardo Germinio
	 * @date 27/11/2012
	 *
	 */
	
	public Integer countQuantidadeAcessosReceitaFederal(
			FiltrarQuantidadeAcessosReceitaFederalHelper helper)throws ControladorException{
		try {
			
			return repositorioCliente.countQuantidadeAcessosReceitaFederal(helper);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}	
	}
	
	/**
	* [UC1429] - Validar Cliente na Base da Receita Federal 
	* 
	* @author Maxwell Moreira
	* @date 17/01/2013
	* 
	* @throws ControladorException
	*/
		
	public Collection<Cliente> pesquisarClientesValidacao(int numeroPagina) throws ControladorException{
        try {
        	
        	Collection<Cliente> colecaoCliente = new ArrayList<Cliente>();
        	
        	Collection colecaoClienteDados = repositorioCliente.pesquisarClientesValidacao(numeroPagina);
        	
        	if ((colecaoClienteDados != null) && !colecaoClienteDados.isEmpty()){
        		
        		Iterator iteratorColecaoClientes = colecaoClienteDados.iterator();
        		while (iteratorColecaoClientes.hasNext()) {
        			
        			Object[] array = (Object[]) iteratorColecaoClientes.next();
        			Cliente cliente = new Cliente();
        			
        			if (array[0] != null) {	
        				cliente.setId((Integer) array[0]);
					}
        			
                    if (array[1] != null) {
        				cliente.setNome("" + array[1]);
					}
                    
                    if (array[2] != null) {
						cliente.setNomeAbreviado("" + array[2]);
					}
                    
                    if ((array[3] != null) && (!array[3].equals(""))) {
						cliente.setCpf("" + array[3]);
					} else {
						cliente.setCpf("");
					}
                    
                    if ((array[4] != null) && (!array[4].equals(""))) {
						cliente.setCnpj("" + array[4]);
					} else {
						cliente.setCnpj("");
					}
                    
                    if (array[5] != null) {
						cliente.setRg("" + array[5]);
					}
        		
                    if (array[6] != null) {
						cliente.setDataEmissaoRg((Date)array[6]);
					}
                    
                    if (array[7] != null) {
						cliente.setDataNascimento((Date)array[7]);
					}
                    
                    if (array[8] != null) {
						cliente.setEmail("" + array[8]);
					}
                    
                    if (array[9] != null) {
						cliente.setIndicadorUso((Short)array[9]);
					}
                 
                    if (array[10] != null) {
                    	OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
                    	orgaoExpedidorRg.setId((Integer)array[10]);
						cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
					}
                    
                    if (array[11] != null) {
						Cliente clienteResponsavel = new Cliente();
						clienteResponsavel.setId((Integer)array[11]);
						cliente.setCliente(clienteResponsavel);
					}
                    
                    if (array[12] != null) {
						PessoaSexo pessoaSexo = new PessoaSexo();
						pessoaSexo.setId((Integer) array[12]);
					}
                    
                    if (array[13] != null) {
                    	Profissao profissao = new Profissao();
                    	profissao.setId((Integer)array[13]);
						cliente.setProfissao(profissao);
					}
                    
                    if (array[14] != null) {
                    	UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
						unidadeFederacao.setId((Integer)array[14]);
                    	cliente.setUnidadeFederacao(unidadeFederacao);
					}
                    
                    if (array[15] != null) {
                    	ClienteTipo clienteTipo = new ClienteTipo();
                    	clienteTipo.setId((Integer)array[15]);
						cliente.setClienteTipo(clienteTipo);
					}
                    
                    if (array[16] != null) {
						cliente.setIndicadorUsoNomeFantasiaConta((Short) array[16]);
					}
                    
                    if (array[17] != null) {
                    	RamoAtividade ramoAtividade = new RamoAtividade();
                    	ramoAtividade.setId((Integer)array[17]);
						cliente.setRamoAtividade(ramoAtividade);
					}
                    
                    if (array[18] != null) {
						cliente.setUltimaAlteracao((Date)array[18]);
					}
                    
                    if (array[19] != null) {
						cliente.setIndicadorAcrescimos((Short)array[19]);
					}
                    
                    if (array[20] != null) {
						cliente.setIndicadorGeraArquivoTexto((Short)array[20]);
					}
                    
                    if (array[21] != null) {
						cliente.setIndicadorVencimentoMesSeguinte((Short)array[21]);
					}
                    
                    if (array[22] != null) {
						cliente.setIndicadorGeraFaturaAntecipada((Short)array[22]);
					}
                    
                    if (array[23] != null) {
						cliente.setIndicadorUsoNomeFantasiaConta((Short)array[23]);
					}
                    
                    if (array[24] != null) {
						cliente.setIndicadorPermiteNegativacao((Short)array[24]);
					}
                    
                    if (array[25] != null) {
						cliente.setIndicadorNegativacaoPeriodo((Short)array[25]);
					}
                    
                    if (array[26] != null) {
						cliente.setIndicadorBloquearDespesasPostais((Short)array[26]);
					}
                    
                    if (array[27] != null) {
						cliente.setIndicadorBloquearAlterarImoveis((Short)array[27]);
					}
                
                    if (array[28] != null) {
						cliente.setNomeClienteReceitaFederal((String)array[28]);
					}
                     
                    if (array[29] != null) {
                    	Cliente responsavel = new Cliente();
						responsavel.setId((Integer)array[29]);
                    	cliente.setCliente(responsavel);
					}
                    
                    colecaoCliente.add(cliente);
                    
        		}
        	}
        	
			return colecaoCliente;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	* [UC1429] - Validar Cliente na Base da Receita Federal 
	* 
	* @author Maxwell Moreira
	* @date 25/01/2013
	* 
	* @throws ErroRepositorioException
	*/
	public int pesquisarQuantidadeClientesValidacao() throws ControladorException{

		try {
        	int quantidadeCliente = repositorioCliente.pesquisarQuantidadeClientesValidacao();
			return quantidadeCliente;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	* [UC1429] - Validar Cliente na Base da Receita Federal 
	* 
	* @author Maxwell Moreira
	* @date 30/01/2013
	* 
	* @throws ControladorException
	*/
		
	public Collection gerarRelatorioClientesValidadosReceitaFederal(Collection colecaoClientesParametro) throws ControladorException{
        
		Collection<RelatorioClienteReceitaFederalHelper> colecaoRetorno = new ArrayList(); 
		
		try {
        	
        	Collection colecaoClienteDados = repositorioCliente.gerarRelatorioClientesValidadosReceitaFederal(colecaoClientesParametro);
			
			Iterator iterator = colecaoClienteDados.iterator();
			
			while (iterator.hasNext()) {
			
				RelatorioClienteReceitaFederalHelper relatorioHelper = new RelatorioClienteReceitaFederalHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				// ID
				String id = "";
				if ( objeto[0] != null ) {
					id = objeto[0].toString();
				}
				
				relatorioHelper.setId(Integer.valueOf(id));

				// Nome
				String nome = "";
				if ( objeto[1] != null) {
					nome = objeto[1].toString();
				}
				
				relatorioHelper.setNome(nome);

				// DOCUMENTO
				String documento = "";
				
				if ( objeto[2] != null ) {
					documento = objeto[2].toString();
				} else 
				if ( objeto[3] != null ) {
					documento = objeto[3].toString();
				}
				
				relatorioHelper.setDocumento(documento);

				// MENSAGEM
				String mensagem = "";
				if (( objeto[4] != null) && (!objeto[4].equals(""))) {
					mensagem = objeto[4].toString();
				} else {
					mensagem = "Consulta Realizada com Sucesso";
				}
				
				relatorioHelper.setMensagem(mensagem);
				
				relatorioHelper.setErroValidacao(false);
				
				colecaoRetorno.add(relatorioHelper);	

			}
        	
			return colecaoRetorno;
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}	
	
}
