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
package gcom.operacional;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroAbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroManutencaoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.operacional.bean.AreaOperacionalHelper;import gcom.operacional.bean.ZonaPressaoHelper;
import gcom.relatorio.operacional.RelatorioTotalizadorSistemaAbastecimentoBean;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.RemocaoInvalidaException;
import gcom.util.RemocaoRegistroNaoExistenteException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.hibernate.exception.ConstraintViolationException;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Leandro Cavalcanti
 * @created 12 de junho de 2006
 */
public class ControladorOperacionalSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	SessionContext sessionContext;
	
	protected IRepositorioOperacional repositorioOperacional;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioOperacional = RepositorioOperacionalHBM.getInstancia();
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
	 * Faz o controle de concorrencia de programação de abastecimento
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 04/12/2006
	 * @throws ControladorException
	 */
	private void verificarAbastecimentoProgramacaoControleConcorrencia(
			AbastecimentoProgramacao abastecimentoProgramacao)
			throws ControladorException {

		FiltroAbastecimentoProgramacao filtro = new FiltroAbastecimentoProgramacao();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.ID, abastecimentoProgramacao
						.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro,
				AbastecimentoProgramacao.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		AbastecimentoProgramacao abastecimentoProgramacaoAtual = (AbastecimentoProgramacao) Util
				.retonarObjetoDeColecao(colecao);

		if (abastecimentoProgramacaoAtual.getUltimaAlteracao().after(
				abastecimentoProgramacao.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * Faz o controle de concorrencia da programação da manutenção
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 04/12/2006
	 * @throws ControladorException
	 */
	private void verificarManutencaoProgramacaoControleConcorrencia(
			ManutencaoProgramacao manutencaoProgramacao)
			throws ControladorException {

		FiltroManutencaoProgramacao filtro = new FiltroManutencaoProgramacao();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroManutencaoProgramacao.ID, manutencaoProgramacao.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro,
				ManutencaoProgramacao.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ManutencaoProgramacao manutencaoProgramacaoAtual = (ManutencaoProgramacao) Util
				.retonarObjetoDeColecao(colecao);

		if (manutencaoProgramacaoAtual.getUltimaAlteracao().after(
				manutencaoProgramacaoAtual.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * [UC0001] Inserir Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 26/01/2007
	 * 
	 * @param Distrito
	 *            Operaciona Descrição do parâmetro
	 */
	public Integer inserirDistritoOperacional(String descricao,
			String descricaoAbreviada, String idSetorAbastecimento,
			Usuario usuarioLogado, Collection<SetorAbastecimento> colecaoSetores,
			String idSetorPrincipal)
			throws ControladorException {
		
		if (colecaoSetores == null || colecaoSetores.size() == 0){
			throw new ControladorException("atencao.required", "exibirInserirDistritoOperacionalAction.do?action=botaolVoltar", null, "Setor de Abastecimento");
		}
		
		if (idSetorPrincipal == null || idSetorPrincipal.equals("")){
			throw new ControladorException("atencao.required", "exibirInserirDistritoOperacionalAction.do?action=botaolVoltar", null, "Setor de Abastecimento Principal");
		}

		DistritoOperacional distritoOperacional = new DistritoOperacional();
		distritoOperacional.setDescricao(descricao);
		distritoOperacional.setDescricaoAbreviada(descricaoAbreviada);

		distritoOperacional.setSetorAbastecimento(null);

		distritoOperacional.setUltimaAlteracao(new Date());
		distritoOperacional.setIndicadorUso( new Integer(1).shortValue() );

		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.DESCRICAO, descricao));

		Collection colecaoDistritoOperacional = getControladorUtil().pesquisar(
				filtroDistritoOperacional, DistritoOperacional.class.getName());

		Integer idDistritoOperacional = null;

		if (colecaoDistritoOperacional.isEmpty()) {
			idDistritoOperacional = (Integer) getControladorUtil().inserir(
					distritoOperacional);
		} else {
			throw new ControladorException(
					"atencao.distrito_operacional_existente");
		}
		
		//Inserindo Distrito operacional setor de abastecimento
		DistritoOperacional distrito = new DistritoOperacional();
		distrito.setId(idDistritoOperacional);
		
		Iterator<SetorAbastecimento> itSetores = colecaoSetores.iterator();
		while(itSetores.hasNext()){
			DistritoSetorAbastecimento distritoSetor = new DistritoSetorAbastecimento();
			SetorAbastecimento setor = itSetores.next();
			
			distritoSetor.setDistritoOperacional(distrito);
			distritoSetor.setSetorAbastecimento(setor);
			
			if(setor.getId().toString().equals(idSetorPrincipal)){
				distritoSetor.setIndicadorPrincipal(ConstantesSistema.SIM);
			}
			else{
				distritoSetor.setIndicadorPrincipal(ConstantesSistema.NAO);
			}
			
			distritoSetor.setUltimaAlteracao(new Date());
			
			 getControladorUtil().inserir(distritoSetor);
		}

		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DISTRITO_OPERACIONAL_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_DISTRITO_OPERACIONAL_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		distritoOperacional.setOperacaoEfetuada(operacaoEfetuada);
		distritoOperacional.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(distritoOperacional);

		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		return idDistritoOperacional;
	}

	/**
	 * [UC0414] - Informar Programação de Abastecimento e Manutenção
	 * 
	 * [SB0006] - Atualizar Programação de Abastecimento na Base de Dados
	 * [SB0007] - Atualizar Programação de Manutenção na Base de Dados
	 * 
	 * @author Rafael Pinto
	 * @date 09/11/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarProgramacaoAbastecimentoManutencao(
			Collection colecaoProgramacaoAbastecimento,
			Collection colecaoProgramacaoAbastecimentoRemovidas,
			Collection colecaoProgramacaoManutencao,
			Collection colecaoProgramacaoManutencaoRemovidas, Usuario usuario)
			throws ControladorException {

		// [UC0107] - Registrar Transação
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao
				.setId(Operacao.OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		Iterator itera = null;

		// [SB0006] - Atualizar Programação de Abastecimento na Base de Dados
		if (colecaoProgramacaoAbastecimento != null
				&& !colecaoProgramacaoAbastecimento.isEmpty()) {

			itera = colecaoProgramacaoAbastecimento.iterator();

			while (itera.hasNext()) {

				AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) itera
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (abastecimentoProgramacao.getId() != null
						&& abastecimentoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarAbastecimentoProgramacaoControleConcorrencia(abastecimentoProgramacao);
				}

				abastecimentoProgramacao.setUltimaAlteracao(new Date());

				// [UC0107] - Registrar Transação
				abastecimentoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				abastecimentoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(abastecimentoProgramacao);

				this.getControladorUtil().inserirOuAtualizar(
						abastecimentoProgramacao);
			}
		}

		if (colecaoProgramacaoAbastecimentoRemovidas != null
				&& !colecaoProgramacaoAbastecimentoRemovidas.isEmpty()) {

			Iterator iter = colecaoProgramacaoAbastecimentoRemovidas.iterator();

			while (iter.hasNext()) {

				AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) iter
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (abastecimentoProgramacao.getId() != null
						&& abastecimentoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarAbastecimentoProgramacaoControleConcorrencia(abastecimentoProgramacao);
				}

				abastecimentoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				abastecimentoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(abastecimentoProgramacao);

				this.getControladorUtil().remover(abastecimentoProgramacao);
			}
		}

		if (colecaoProgramacaoManutencao != null
				&& !colecaoProgramacaoManutencao.isEmpty()) {
			itera = colecaoProgramacaoManutencao.iterator();

			// [SB0007] - Atualizar Programação de Manutenção na Base de Dados
			while (itera.hasNext()) {

				ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) itera
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (manutencaoProgramacao.getId() != null
						&& manutencaoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarManutencaoProgramacaoControleConcorrencia(manutencaoProgramacao);
				}
				manutencaoProgramacao.setUltimaAlteracao(new Date());

				// [UC0107] - Registrar Transação
				manutencaoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				manutencaoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(manutencaoProgramacao);

				this.getControladorUtil().inserirOuAtualizar(
						manutencaoProgramacao);
			}
		}

		if (colecaoProgramacaoManutencaoRemovidas != null
				&& !colecaoProgramacaoManutencaoRemovidas.isEmpty()) {

			Iterator iter = colecaoProgramacaoManutencaoRemovidas.iterator();

			while (iter.hasNext()) {

				ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) iter
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (manutencaoProgramacao.getId() != null
						&& manutencaoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarManutencaoProgramacaoControleConcorrencia(manutencaoProgramacao);
				}

				manutencaoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				manutencaoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(manutencaoProgramacao);

				this.getControladorUtil().remover(manutencaoProgramacao);
			}
		}

	}
	
	/**
	 * [UC0522] MANTER DISTRITO OPERACIONAL 
	 * 			
	 * 			Remover Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 05/02/2007
	 * 
	 * @pparam distritoOperacional
	 * @throws ControladorException
	 */
	public void removerDistritoOperacional(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MUNICIPIO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
	       // [FS0003]Municipiopossui vinculos no sistema
        this.getControladorUtil().remover(ids, DistritoOperacional.class.getName(),null, null);
}

	/**
	 * [UC0522] Manter Distrito Operacional [SB0001]Atualizar Municipio
	 * 
	 * @author Eduardo Bianchi
	 * @date 09/02/2007
	 * 
	 * @pparam distritoOperacional
	 * @throws ControladorException
	 */
	public void atualizarDistritoOperacional(DistritoOperacional distritoOperacional,
			Usuario usuarioLogado, String idSetorPrincipal, Collection<SetorAbastecimento> colecaoSetores) throws ControladorException {		
		
		if (colecaoSetores == null || colecaoSetores.size() == 0){
			throw new ControladorException("atencao.required", "exibirAtualizarDistritoOperacionalAction.do?action=botaolVoltar", null, "Setor de Abastecimento");
		}
		
		if (idSetorPrincipal == null || idSetorPrincipal.equals("")){
			throw new ControladorException("atencao.required", "exibirAtualizarDistritoOperacionalAction.do?action=botaolVoltar", null, "Setor de Abastecimento Principal");
		}
		
		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		distritoOperacional.setOperacaoEfetuada(operacaoEfetuada);
		distritoOperacional.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(distritoOperacional);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		// [FS0002] - Atualização realizada por outro usuário
		
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
		// Seta o filtro para buscar o Distrito Operacional na base
		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, distritoOperacional.getId()));

		// Procura o Distrito Operacional na base
		Collection distritosOperacionaisAtualizados = getControladorUtil().pesquisar(filtroDistritoOperacional,DistritoOperacional.class.getName());

		DistritoOperacional distritoOperacionalNaBase = (DistritoOperacional) Util.retonarObjetoDeColecao(distritosOperacionaisAtualizados);

		if (distritoOperacionalNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o distrito Operacional já foi atualizado por outro usuário
		// durante esta atualização

		if (distritoOperacionalNaBase.getUltimaAlteracao().after(distritoOperacional.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		distritoOperacional.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(distritoOperacional);
		
		
		//Removendo os distritos setores operacionais para atualizar
		try{
			this.repositorioOperacional.removerDistritoSetorAbastecimento(distritoOperacional.getId());
		} catch (ConstraintViolationException e) {	
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.dependencias.existentes",e);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		//Inserindo os novos distritos setores
		Iterator<SetorAbastecimento> itSetores = colecaoSetores.iterator();
		while(itSetores.hasNext()){
			DistritoSetorAbastecimento distritoSetor = new DistritoSetorAbastecimento();
			SetorAbastecimento setor = itSetores.next();
			
			distritoSetor.setDistritoOperacional(distritoOperacional);
			distritoSetor.setSetorAbastecimento(setor);
			
			if(setor.getId().toString().equals(idSetorPrincipal)){
				distritoSetor.setIndicadorPrincipal(ConstantesSistema.SIM);
			}
			else{
				distritoSetor.setIndicadorPrincipal(ConstantesSistema.NAO);
			}
			
			distritoSetor.setUltimaAlteracao(new Date());
			
			 getControladorUtil().inserir(distritoSetor);
		}
		
	}
	

	/**
	 * [UC0524] Inserir Sistema de Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/03/2007
	 * 
	 * 
	 */
	
	public Integer inserirSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado)
			throws ControladorException {

			// [FS0003] - Verificando a existência do Sistema de Esgoto
	
			FiltroSistemaEsgoto filtroSistemaEsgoto= new FiltroSistemaEsgoto();
			
			filtroSistemaEsgoto.adicionarParametro(new ComparacaoTextoCompleto(FiltroSistemaEsgoto.DESCRICAO,sistemaEsgoto.getDescricao()));
			
			Collection colecaoSistemaEsgoto = getControladorUtil().pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			
			if (colecaoSistemaEsgoto != null && !colecaoSistemaEsgoto.isEmpty()){
				throw new ControladorException("atencao.divisao_esgoto.existente", null, sistemaEsgoto.getDescricao());
			}
	
			
			sistemaEsgoto.setUltimaAlteracao(new Date());
	
			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_INSERIR_SISTEMA_ESGOTO, new UsuarioAcaoUsuarioHelper
					(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	
			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_INSERIR_SISTEMA_ESGOTO);
	
			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);
	
			sistemaEsgoto.setOperacaoEfetuada(operacaoEfetuada);
			sistemaEsgoto.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(sistemaEsgoto);
			// ------------ REGISTRAR TRANSAÇÃO----------------------------
	
			Integer idSistemaEsgoto = (Integer) getControladorUtil().inserir(sistemaEsgoto);
			sistemaEsgoto.setId(idSistemaEsgoto);
	
			return idSistemaEsgoto;
	}
	
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0001]Atualizar Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 19/03/2007
	 * 
	 */
	
	
	public void atualizarSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado) 
						throws ControladorException {

		
		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SISTEMA_ESGOTO_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SISTEMA_ESGOTO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		sistemaEsgoto.setOperacaoEfetuada(operacaoEfetuada);
		sistemaEsgoto.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(sistemaEsgoto);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		// [FS0002] - Atualização realizada por outro usuário
		
		FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
		// Seta o filtro para buscar o sistema de esgoto na base
		filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, sistemaEsgoto.getId()));

		// Procura sistema de esgoto na base
		Collection sistemaEsgotoAtualizados = getControladorUtil().pesquisar(filtroSistemaEsgoto,SistemaEsgoto.class.getName());

		SistemaEsgoto sistemaEsgotoNaBase = (SistemaEsgoto) Util.retonarObjetoDeColecao(sistemaEsgotoAtualizados);

		if (sistemaEsgotoNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o sistema de esgoto já foi atualizado por outro usuário
		// durante esta atualização

		if (sistemaEsgotoNaBase.getUltimaAlteracao().after(sistemaEsgoto.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		sistemaEsgoto.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(sistemaEsgoto);

	}
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0002]Remover Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/03/2007
	 * 
	 */
	
	public void removerSistemaEsgoto(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SISTEMA_ESGOTO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		

	       // [FS0003]Sistema de Esgoto possui vinculos no sistema
        this.getControladorUtil().remover(ids, SistemaEsgoto.class.getName(),operacaoEfetuada, colecaoUsuarios);

	}
	
	/**
	 * [UC0081] Manter Marca Hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2007
	 * 
	 */
	
	public void removerHidrometroMarca(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REMOVER_MARCA_HIDROMETRO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		

	       // [FS0003]Sistema de Esgoto possui vinculos no sistema
        this.getControladorUtil().remover(ids, HidrometroMarca.class.getName(),operacaoEfetuada, colecaoUsuarios);

	}
	
	/**
	 * [UC0081] Manter Hidrometro Marca
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2007
	 * 
	 */		
	public void atualizarHidrometroMarca(HidrometroMarca hidrometroMarca,Usuario usuarioLogado) 
						throws ControladorException {

		
		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_MARCA_HIDROMETRO,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_MARCA_HIDROMETRO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		hidrometroMarca.setOperacaoEfetuada(operacaoEfetuada);
		hidrometroMarca.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(hidrometroMarca);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		// [FS0002] - Atualização realizada por outro usuário
		
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		// Seta o filtro para buscar a marca de hidrometro na base
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID, hidrometroMarca.getId()));

		// Procura sistema de esgoto na base
		Collection hidrometromMarcaAtualizados = getControladorUtil().pesquisar(filtroHidrometroMarca,HidrometroMarca.class.getName());

		HidrometroMarca hidrometromMarcaNaBase = (HidrometroMarca) Util.retonarObjetoDeColecao(hidrometromMarcaAtualizados);

		if (hidrometromMarcaNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o sistema de esgoto já foi atualizado por outro usuário
		// durante esta atualização

		if (hidrometromMarcaNaBase.getUltimaAlteracao().after(hidrometroMarca.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		hidrometroMarca.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(hidrometroMarca);

	}
	
	/**
	 * [UC0596] - Inserir Qualidade de agua
	 * 
	 * Pesquisa as fonte de captacao apatir da tabela de SetorFonteCaptacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 * 
	 * @param Collection colecaoSetorComercial
	 * @throws ControladorException
	 */
	
	public Collection<FonteCaptacao> pesquisarFonteCaptacao(Collection colecaoSetorComercial)
		throws ControladorException {
		
		try {
			return this.repositorioOperacional.pesquisarFonteCaptacao(colecaoSetorComercial);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);

		} 
	}
	
	/**
	 * [UC1519] - Inserir Subsistema de Esgoto 
	 * 
	 * @author Maxwell Moreira
	 * @date 03/07/2013
	 */
	public Integer inserirSubSistemaEsgoto(SubSistemaEsgoto subSistemaEsgoto) throws ControladorException {
		
		FiltroSubSistemaEsgoto filtroSubSistemaEsgoto= new FiltroSubSistemaEsgoto();
		filtroSubSistemaEsgoto.adicionarParametro(new ComparacaoTextoCompleto(FiltroSubSistemaEsgoto.DESCRICAO,subSistemaEsgoto.getDescricao()));
		Collection colecaoSubSistemaEsgoto = getControladorUtil().pesquisar(filtroSubSistemaEsgoto, SubSistemaEsgoto.class.getName());
		
		if (colecaoSubSistemaEsgoto != null && !colecaoSubSistemaEsgoto.isEmpty()){
			throw new ControladorException("atencao.subsistema_esgoto_ja_cadastrado", null, subSistemaEsgoto.getDescricao());
		}
		
		subSistemaEsgoto.setUltimaAlteracao(new Date());
		subSistemaEsgoto.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		Integer idSubSistemaEsgoto = (Integer) getControladorUtil().inserir(subSistemaEsgoto);
			
		return idSubSistemaEsgoto;
	}
	
	/**
	 * [UC1521] Manter SubSistema Esgoto
	 * 
	 * @author Maxwell Moreira
	 * @date 05/07/2013
	 */
	public void removerSubSistemaEsgoto(String[] ids) throws ControladorException {

		for (int i = 0; i < ids.length; i++) {
			String subSistemaEsgotoRemover = ids[i];
			
			FiltroSubSistemaEsgoto filtroSubSistemaEsgoto= new FiltroSubSistemaEsgoto();
			filtroSubSistemaEsgoto.adicionarParametro(new ComparacaoTextoCompleto(FiltroSubSistemaEsgoto.ID, subSistemaEsgotoRemover));
			Collection colecaoSubSistemaEsgotoRemover = getControladorUtil().pesquisar(filtroSubSistemaEsgoto, SubSistemaEsgoto.class.getName());
			
			SubSistemaEsgoto subsistemaEsgoto = (SubSistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSubSistemaEsgotoRemover);
			
	        this.getControladorUtil().remover(subsistemaEsgoto);
		}
		
	}
	
	/**
	 * [UC1521] Manter SubSistema Esgoto
	 * 
	 * @author Maxwell Moreira
	 * @date 08/07/2013
	 */
	public void atualizarSubSistemaEsgoto(SubSistemaEsgoto subSistemaEsgoto) throws ControladorException {

		FiltroSubSistemaEsgoto filtroSubSistemaEsgoto= new FiltroSubSistemaEsgoto();
		filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSubSistemaEsgoto.DESCRICAO,subSistemaEsgoto.getDescricao()));
        filtroSubSistemaEsgoto.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroSubSistemaEsgoto.ID,subSistemaEsgoto.getId()));
		
        Collection colecaoSubSistemaEsgoto = getControladorUtil().pesquisar(filtroSubSistemaEsgoto, SubSistemaEsgoto.class.getName());
		
		if (colecaoSubSistemaEsgoto != null && !colecaoSubSistemaEsgoto.isEmpty()){
			throw new ControladorException("atencao.subsistema_esgoto_ja_cadastrado", null, subSistemaEsgoto.getDescricao());
		}
		
		subSistemaEsgoto.setUltimaAlteracao(new Date());
		getControladorUtil().atualizar(subSistemaEsgoto);

	}
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 * 
	 */
	public Collection<SubSistemaEsgotoArquivoTexto> pesquisarSubSistemaEsgotoArquivoTexto(Date dataInicial, Date dataFinal)
			throws ControladorException{
		
		try{
			return repositorioOperacional.pesquisarSubSistemaEsgotoArquivoTexto(dataInicial, dataFinal);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 * 
	 */
	public Collection<SubSistemaEsgotoArquivoTextoErro> pesquisarSubSistemaEsgotoArquivoTextoErro(Integer idArquivo)
			throws ControladorException{
		
		try{
			return repositorioOperacional.pesquisarSubSistemaEsgotoArquivoTextoErro(idArquivo);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/** 
     * [UCXXXX] Inserir Subsistema de Abastecimento 
     *  
     * @author Raphael Rossiter 
     * @date 02/06/2014 
     *  
     * @param subsistemaAbastecimento 
     * @return Integer 
     * @throws ControladorException 
     */
	public Integer inserirSubsistemaAbastecimento(SubsistemaAbastecimento subsistemaAbastecimento) throws ControladorException {
		
		Integer idSubsistemaAbastecimento = null;
		
		subsistemaAbastecimento.setIndicadorUso(ConstantesSistema.SIM);
		subsistemaAbastecimento.setUltimaAlteracao(new Date());
		
		if (subsistemaAbastecimento.getDescricao() == null || subsistemaAbastecimento.getDescricao().equals("")){
			throw new ControladorException("atencao.required", null, "Descrição");
		}
		
		Set<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = subsistemaAbastecimento.getSubsistemaSistemaAbastecimento();
		
		if (colecaoSubsistemaSistemaAbastecimento == null || colecaoSubsistemaSistemaAbastecimento.size() == 0){
			throw new ControladorException("atencao.required", null, "Sistema de Abastecimento Principal");
		}
		
		Iterator<SubsistemaSistemaAbastecimento> it = colecaoSubsistemaSistemaAbastecimento.iterator();
		SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimentoPrincipal = null;
		
		while (it.hasNext()){
			
			SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento = (SubsistemaSistemaAbastecimento) it.next();
			
			if (subsistemaSistemaAbastecimento.getIndicadorPrincipal().equals(ConstantesSistema.SIM)){
				subsistemaSistemaAbastecimentoPrincipal = subsistemaSistemaAbastecimento;
				break;
			}
		}
		
		if (subsistemaSistemaAbastecimentoPrincipal == null){
			throw new ControladorException("atencao.required", null, "Sistema de Abastecimento Principal");
		}
		
		//INSERINDO NA BASE (SUBSISTEMA)
		subsistemaAbastecimento.setSubsistemaSistemaAbastecimento(null);
		idSubsistemaAbastecimento = (Integer) this.getControladorUtil().inserir(subsistemaAbastecimento);
		
		//INSERINDO NA BASE (PRINCIPAL SISTEMA DE ABASTECIMENTO DO SUBSISTEMA QUE ESTA SENDO CADASTRADO)
		subsistemaSistemaAbastecimentoPrincipal.setSubsistemaAbastecimento(subsistemaAbastecimento);
		subsistemaSistemaAbastecimentoPrincipal.setUltimaAlteracao(new Date());
		
		this.getControladorUtil().inserir(subsistemaSistemaAbastecimentoPrincipal);
		
		//INSERINDO NA BASE (SISTEMA DE ABASTECIMENTO SECUNDÁRIOS)
		it = colecaoSubsistemaSistemaAbastecimento.iterator();
		
		while (it.hasNext()){
			
			SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento = (SubsistemaSistemaAbastecimento) it.next();
			
			if (!subsistemaSistemaAbastecimento.getSistemaAbastecimento().getId().equals(subsistemaSistemaAbastecimentoPrincipal.getSistemaAbastecimento().getId())){
				
				subsistemaSistemaAbastecimento.setSubsistemaAbastecimento(subsistemaAbastecimento);
				subsistemaSistemaAbastecimento.setUltimaAlteracao(new Date());
				
				this.getControladorUtil().inserir(subsistemaSistemaAbastecimento);
			}
			else if (subsistemaSistemaAbastecimento.getIndicadorPrincipal().equals(ConstantesSistema.NAO)){
				FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
				filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, 
					subsistemaSistemaAbastecimentoPrincipal.getSistemaAbastecimento().getId()));
				
				Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = this.getControladorUtil().pesquisar(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
				
				SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSistemaAbastecimento);
				
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.subsistema_abastecimento.sistema_ja_informado", null, sistemaAbastecimento.getDescricao());
			}
		}
		
		
		return idSubsistemaAbastecimento;
	}
	
	/** 
     * [UCXXXX] Manter Subsistema de Abastecimento 
     *  
     * @author Raphael Rossiter 
     * @date 05/06/2014 
     *  
     * @param idsSubsistemaAbastecimento 
     * @throws ControladorException 
     */
	public void removerSubsistemaAbastecimento(String[] idsSubsistemaAbastecimento) throws ControladorException {

		try{
			
			if (idsSubsistemaAbastecimento != null && idsSubsistemaAbastecimento.length != 0) {
				
				for (int i = 0; i < idsSubsistemaAbastecimento.length; i++) {
					
					int id = Integer.parseInt(idsSubsistemaAbastecimento[i]);
					
					SubsistemaAbastecimento subsistemaAbastecimento = new SubsistemaAbastecimento();
					subsistemaAbastecimento.setId(id);

					FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
					filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, id));
					Collection<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = getControladorUtil().pesquisar(filtroSubsistemaSistemaAbastecimento,
						SubsistemaSistemaAbastecimento.class.getName());
					
					if(colecaoSubsistemaSistemaAbastecimento != null && !colecaoSubsistemaSistemaAbastecimento.isEmpty()){
						
						Iterator<SubsistemaSistemaAbastecimento> iteratorSubsistemaSistemaAbastecimento = colecaoSubsistemaSistemaAbastecimento.iterator();
						
						while (iteratorSubsistemaSistemaAbastecimento.hasNext()){
							SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento = (SubsistemaSistemaAbastecimento) iteratorSubsistemaSistemaAbastecimento.next();
							getControladorUtil().remover(subsistemaSistemaAbastecimento);
						}
					}
					
					getControladorUtil().remover(subsistemaAbastecimento);
				}
			}
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(e.getMessage());
		}
	}
	
	public void removerLocalidadeSistemaAbastecimento(Integer id) throws ControladorException{
		try{
			repositorioOperacional.removerLocalidadeSistemaAbastecimento(id);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/** 
     * [UCXXXX] Atualizar Subsistema de Abastecimento 
     *  
     * @author Raphael Rossiter 
     * @date 02/06/2014 
     *  
     * @param subsistemaAbastecimento 
     * @throws ControladorException 
     */
	public void atualizarSubsistemaAbastecimento(SubsistemaAbastecimento subsistemaAbastecimento) throws ControladorException {
		
		try{
			
			subsistemaAbastecimento.setUltimaAlteracao(new Date());
			
			if (subsistemaAbastecimento.getDescricao() == null || subsistemaAbastecimento.getDescricao().equals("")){
				throw new ControladorException("atencao.required", null, "Descrição");
			}
			
			Set<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = subsistemaAbastecimento.getSubsistemaSistemaAbastecimento();
			
			if (colecaoSubsistemaSistemaAbastecimento == null || colecaoSubsistemaSistemaAbastecimento.size() == 0){
				throw new ControladorException("atencao.required", null, "Sistema de Abastecimento Principal");
			}
			
			Iterator<SubsistemaSistemaAbastecimento> it = colecaoSubsistemaSistemaAbastecimento.iterator();
			SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimentoPrincipal = null;
			
			while (it.hasNext()){
				
				SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento = (SubsistemaSistemaAbastecimento) it.next();
				
				if (subsistemaSistemaAbastecimento.getIndicadorPrincipal().equals(ConstantesSistema.SIM)){
					subsistemaSistemaAbastecimentoPrincipal = subsistemaSistemaAbastecimento;
					subsistemaSistemaAbastecimentoPrincipal.setSubsistemaAbastecimento(subsistemaAbastecimento);
					break;
				}
			}
			
			if (subsistemaSistemaAbastecimentoPrincipal == null){
				throw new ControladorException("atencao.required", null, "Sistema de Abastecimento Principal");
			}
			
			//ATUALIZANDO NA BASE (SUBSISTEMA)
			subsistemaAbastecimento.setSubsistemaSistemaAbastecimento(null);
			this.getControladorUtil().atualizar(subsistemaAbastecimento);
			
			//ATUALIZANDO O SISTEMA DE ABSTECIMENTO PRINCIPAL
			try{
				repositorioOperacional.atualizarSubsistemaSistemaAbastecimentoPrincipal(subsistemaSistemaAbastecimentoPrincipal);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			
			//INSERINDO O SISTEMA DE ABSTECIMENTO SECUNDÁRIO
			it = colecaoSubsistemaSistemaAbastecimento.iterator();
			
			while (it.hasNext()){
				
				SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento = (SubsistemaSistemaAbastecimento) it.next();
				
				if (!subsistemaSistemaAbastecimento.getSistemaAbastecimento().getId().equals(subsistemaSistemaAbastecimentoPrincipal.getSistemaAbastecimento().getId())){
					
					FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
					filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, 
						subsistemaAbastecimento.getId()));
					filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SISTEMA_ABASTECIMENTO, 
						subsistemaSistemaAbastecimento.getSistemaAbastecimento().getId()));
					
					Collection<SubsistemaSistemaAbastecimento> colecaoJaExiste = getControladorUtil().pesquisar(filtroSubsistemaSistemaAbastecimento,
						SubsistemaSistemaAbastecimento.class.getName());
					
					if(colecaoJaExiste == null || colecaoJaExiste.isEmpty()){
						
						subsistemaSistemaAbastecimento.setSubsistemaAbastecimento(subsistemaAbastecimento);
						subsistemaSistemaAbastecimento.setUltimaAlteracao(new Date());
						this.getControladorUtil().inserir(subsistemaSistemaAbastecimento);
					}
				}
				else if (subsistemaSistemaAbastecimento.getIndicadorPrincipal().equals(ConstantesSistema.NAO)){
					FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
					filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, 
						subsistemaSistemaAbastecimentoPrincipal.getSistemaAbastecimento().getId()));
					
					Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = this.getControladorUtil().pesquisar(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
					
					SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSistemaAbastecimento);
					
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.subsistema_abastecimento.sistema_ja_informado", null, sistemaAbastecimento.getDescricao());
				}
			}
			
			//REMOVENDO O SISTEMA DE ABSTECIMENTO SECUNDÁRIO
			FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento();
			filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, 
				subsistemaAbastecimento.getId()));
			
			Collection<SubsistemaSistemaAbastecimento> colecaoRemover = getControladorUtil().pesquisar(filtroSubsistemaSistemaAbastecimento,
				SubsistemaSistemaAbastecimento.class.getName());
			
			it = colecaoRemover.iterator();
			boolean remover = true;
			
			while (it.hasNext()){
				
				SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento = (SubsistemaSistemaAbastecimento) it.next();
				
				Iterator<SubsistemaSistemaAbastecimento> iterator = colecaoSubsistemaSistemaAbastecimento.iterator();
				
				remover = true;
				
				while(iterator.hasNext()){
					
					SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimentoInformado = (SubsistemaSistemaAbastecimento) iterator.next();
					
					if (subsistemaSistemaAbastecimentoInformado.getSistemaAbastecimento().getId()
						.equals(subsistemaSistemaAbastecimento.getSistemaAbastecimento().getId()) &&
						!subsistemaSistemaAbastecimentoInformado.getSistemaAbastecimento().getId()
						.equals(subsistemaSistemaAbastecimentoPrincipal.getSistemaAbastecimento().getId())){
						
						remover = false;
						break;
					}
				}
				
				if (remover && subsistemaSistemaAbastecimento.getIndicadorPrincipal().equals(ConstantesSistema.NAO)){
					
					this.getControladorUtil().remover(subsistemaSistemaAbastecimento);
				}
			}
	
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			
			if (e instanceof ControladorException) {
				ControladorException exception = (ControladorException) e;
				throw new ControladorException(exception.getMessage(), null, exception.getParametroMensagem().get(0));
			}
			else{
				throw new ControladorException(e.getMessage());
			}
		}
	}

	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Collection<Object[]> pesquisarSetorAbastecimento(String codigo,
			String descricao, String descricaoAbreviada,
			String indicadorPosicaoTexto, String sistemaAgua,
			String subsistemaAgua, String indicadorUso,String numeroDaPagina, boolean relatorio)  throws ControladorException{
		
		try{
			return repositorioOperacional.pesquisarSetorAbastecimento(codigo,descricao,descricaoAbreviada,
																	 indicadorPosicaoTexto,sistemaAgua,
																	 subsistemaAgua,indicadorUso, numeroDaPagina, relatorio);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		
	}
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Integer pesquisarQtdSetorAbastecimento(String codigo,
			String descricao, String descricaoAbreviada,
			String indicadorPosicaoTexto, String sistemaAgua,
			String subsistemaAgua, String indicadorUso) throws ControladorException{
		
		try{
			return repositorioOperacional.pesquisarQtdSetorAbastecimento(codigo,descricao,descricaoAbreviada,
																	 indicadorPosicaoTexto,sistemaAgua,
																	 subsistemaAgua,indicadorUso);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorAbastecimento(Integer idSetor) throws ControladorException{
		try{
			repositorioOperacional.removerSetorAbastecimento(idSetor);
		} catch (ConstraintViolationException e) {	
			throw new ControladorException("atencao.excluir_setor_vinculo",e);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Collection<SubsistemaSistemaAbastecimento> pesquisarSubSistemaSistemaAbastecimento(Integer idSistemaAbastecimento) throws ControladorException{
		try{
			return repositorioOperacional.pesquisarSubSistemaSistemaAbastecimento(idSistemaAbastecimento);
		} catch (ConstraintViolationException e) {	
			throw new ControladorException("atencao.dependencias.existentes",e);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Collection<SetorSubsistemaAbastecimento> pesquisarSetorSubsistemaAbastecimento(Integer idSetor)  throws ControladorException{
		try{
			return repositorioOperacional.pesquisarSetorSubsistemaAbastecimento(idSetor);
		} catch (ConstraintViolationException e) {	
			throw new ControladorException("atencao.dependencias.existentes",e);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorSubsistemaAbastecimento(Integer idSetor) throws ControladorException{
		try{
			repositorioOperacional.removerSetorSubsistemaAbastecimento(idSetor);
		} catch (ConstraintViolationException e) {	
			throw new ControladorException("atencao.dependencias.existentes",e);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [SB0001] Inserir Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Integer inserirAreaOperacional(AreaOperacional areaOperacional)
			throws ControladorException{
		
		//[SB0001] - Inserir Área Operacional
		areaOperacional.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		areaOperacional.setUltimaAlteracao(new Date()); 
		
		FiltroAreaOperacional filtroAreaOperacional = new FiltroAreaOperacional();
		filtroAreaOperacional.adicionarParametro(new ParametroSimples(
			FiltroAreaOperacional.DESCRICAO, areaOperacional.getDescricao()));

		Collection colecaoAreaOperacional = getControladorUtil().pesquisar(
			filtroAreaOperacional, AreaOperacional.class.getName());

		Integer idAreaOperacional = null;

		if (colecaoAreaOperacional.isEmpty()) {
			idAreaOperacional = (Integer) getControladorUtil().inserir(areaOperacional);
		} else {
			throw new ControladorException(
					"atencao.area_operacional_existente");
		}
			
		if (areaOperacional.getColecaoAreaDistritoOperacional()!=null){
			for (AreaDistritoOperacional areaDistritoOperacional : areaOperacional.getColecaoAreaDistritoOperacional()){
				areaOperacional.setId(idAreaOperacional);
				areaDistritoOperacional.setAreaOperacional(areaOperacional);
				areaDistritoOperacional.setUltimaAlteracao(new Date());
				getControladorUtil().inserir(areaDistritoOperacional);
			}
		}
		
		return idAreaOperacional;
	}
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [IT0002] Exibir Lista de Subsistema de Abastecimento.
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<SubsistemaAbastecimento> obterColecaoSubsistemaAbastecimento(Integer idSistemaAbastecimento)
			throws ControladorException{
		
		Collection<SubsistemaAbastecimento> colecaoSubsistemaAbastecimento = new ArrayList<SubsistemaAbastecimento>();
		
		try{
			colecaoSubsistemaAbastecimento = repositorioOperacional.obterColecaoSubsistemaAbastecimento(idSistemaAbastecimento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}
		return colecaoSubsistemaAbastecimento;
	}
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [IT0003] Exibir Lista de Distrito Operacional.
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<DistritoOperacional> obterColecaoDistritoOperacional(Integer idSubsistemaAbastecimento)
			throws ControladorException{
		
		Collection<DistritoOperacional> colecaoDistritoOperacional = new ArrayList<DistritoOperacional>();
		
		try{
			colecaoDistritoOperacional = repositorioOperacional.obterColecaoDistritoOperacional(idSubsistemaAbastecimento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}
		return colecaoDistritoOperacional;
	}
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 04/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterAreaOperacionalCount(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso) 
					 throws ControladorException{
		try {
			return repositorioOperacional.obterAreaOperacionalCount(descricao, tipoPesquisa, 
				idSistemaAbastecimento, idSubsistemaAbastecimento, idDistritoOperacional, indicadorUso);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaOperacionalHelper> obterAreaOperacional(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, Integer numeroPagina) throws ControladorException{
				
		try {
			
			Collection<AreaOperacionalHelper> colecaoAreaDistritoOperacional = repositorioOperacional.obterAreaOperacional(descricao, tipoPesquisa, 
				idSistemaAbastecimento, idSubsistemaAbastecimento, idDistritoOperacional, indicadorUso, numeroPagina);
			
			SubsistemaAbastecimento subsistemaAbastecimento = null;
			if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				FiltroSubsistemaAbastecimento filtroSubsistemaAbastecimento = new FiltroSubsistemaAbastecimento();
				filtroSubsistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, idSubsistemaAbastecimento));
	
				Collection<SubsistemaAbastecimento> colecaoSubsistemaAbastecimento = getControladorUtil().pesquisar(filtroSubsistemaAbastecimento, SubsistemaAbastecimento.class.getName());
				
				subsistemaAbastecimento = (SubsistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSubsistemaAbastecimento);
			}
			
			DistritoOperacional distritoOperacional = null;
			if(idDistritoOperacional != null && !idDistritoOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
				filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, idDistritoOperacional));
	
				Collection<DistritoOperacional> colecaoDistritoOperacional = getControladorUtil().pesquisar(filtroDistritoOperacional, DistritoOperacional.class.getName());
				
				distritoOperacional = (DistritoOperacional) Util.retonarObjetoDeColecao(colecaoDistritoOperacional);
			}
			
			Iterator it = colecaoAreaDistritoOperacional.iterator();
			
			if (colecaoAreaDistritoOperacional!=null && !colecaoAreaDistritoOperacional.isEmpty()){
				while (it.hasNext()){
					
					AreaOperacionalHelper areaOperacionalHelper = (AreaOperacionalHelper) it.next();
					
					if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
						//Subsistema Abastecimento Informado
						areaOperacionalHelper.setSubSistema(subsistemaAbastecimento.getDescricao());
					}else{
						//Pesquisa Subsistema Principal
						SubsistemaAbastecimento subsistemaAbastecimentoPrincipal = repositorioOperacional.obterSubsistemaPrincipalAreaOperacional(areaOperacionalHelper.getIdAreaOperacional());
						areaOperacionalHelper.setSubSistema(subsistemaAbastecimentoPrincipal.getDescricao());
					}
					
					if(idDistritoOperacional != null && !idDistritoOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
						//Distrito Operacional Informado
						areaOperacionalHelper.setDistrito(distritoOperacional.getDescricao());
					}else{
						//Pesquisa Distrito Principal
						DistritoOperacional distritoOperacionalPrincipal = repositorioOperacional.obterDistritoPrincipalAreaOperacional(areaOperacionalHelper.getIdAreaOperacional());
						areaOperacionalHelper.setDistrito(distritoOperacionalPrincipal.getDescricao());
					}
				}
			}
			
			return colecaoAreaDistritoOperacional;
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public AreaOperacional obterAreaOperacionalEDistritos(Integer idAreaOperacional) throws ControladorException{
				
		try {
			
			AreaOperacional areaOperacional = new AreaOperacional();
			
			areaOperacional = repositorioOperacional.obterAreaOperacional(idAreaOperacional);
			
			Collection<AreaDistritoOperacional> colecaoAreaDistritoOperacional = repositorioOperacional.obterAreaDistritoOperacional(idAreaOperacional);
			if(colecaoAreaDistritoOperacional != null && !colecaoAreaDistritoOperacional.isEmpty()){
				areaOperacional.setColecaoAreaDistritoOperacional(colecaoAreaDistritoOperacional);
			}
			
			return areaOperacional;
			 
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarAreaOperacional(AreaOperacional areaOperacional)throws ControladorException{
		
		try{
			AreaOperacional areaOperacionalNaBase = repositorioOperacional.obterAreaOperacional(areaOperacional.getId());

			if (areaOperacionalNaBase == null) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.registro_remocao_nao_existente");
			}

			// Verificar se Area Operacional ja foi atualizado por outro usuário durante esta atualização
			if (areaOperacionalNaBase.getUltimaAlteracao().after(areaOperacional.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
					
			FiltroAreaOperacional filtroAreaOperacional = new FiltroAreaOperacional();
			filtroAreaOperacional.adicionarParametro(new ParametroSimples(
				FiltroAreaOperacional.DESCRICAO, areaOperacional.getDescricao()));
			filtroAreaOperacional.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroAreaOperacional.ID, areaOperacional.getId()));

			Collection colecaoAreaOperacional = getControladorUtil().pesquisar(
				filtroAreaOperacional, AreaOperacional.class.getName());

			if (colecaoAreaOperacional.isEmpty()) {
				areaOperacional.setUltimaAlteracao(new Date());
				this.getControladorUtil().atualizar(areaOperacional);
			} else {
				throw new ControladorException(
						"atencao.area_operacional_existente");
			}
			
			repositorioOperacional.removerAreaDistritoOperacional(areaOperacional.getId()); 
			
			Iterator iterAreaDistritoOperacional = areaOperacional.getColecaoAreaDistritoOperacional().iterator();
			while (iterAreaDistritoOperacional.hasNext()) {
				AreaDistritoOperacional areaDistritoOperacional = (AreaDistritoOperacional) iterAreaDistritoOperacional.next();
				areaDistritoOperacional.setAreaOperacional(areaOperacional);
				this.getControladorUtil().inserir(areaDistritoOperacional);
			}
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerAreaOperacional(String[] ids) throws ControladorException{
		
		try {
			int i = 0;
			while (i < ids.length) {
				
				Integer idAreaOperacional = new Integer(ids[i]);
				
				repositorioOperacional.removerAreaOperacional(idAreaOperacional);
	
				i = i + 1;
			}
		} catch (RemocaoRegistroNaoExistenteException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente", ex);
		} catch (RemocaoInvalidaException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.dependencias.existentes", ex);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaOperacionalHelper> pesquisarAreaOperacional(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, String idSetorAbastecimento)throws ControladorException{
				
		try {
			return repositorioOperacional.pesquisarAreaOperacional(descricao, tipoPesquisa, 
				idSistemaAbastecimento, idSubsistemaAbastecimento, idDistritoOperacional, indicadorUso, idSetorAbastecimento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public DistritoOperacional obterDistritoPrincipalAreaOperacional(Integer idAreaOperacional) throws ControladorException{
		
	try {
			return repositorioOperacional.obterDistritoPrincipalAreaOperacional(idAreaOperacional);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SubsistemaAbastecimento obterSubsistemaPrincipalAreaOperacional(Integer idAreaOperacional) throws ControladorException{
		try {
			return repositorioOperacional.obterSubsistemaPrincipalAreaOperacional(idAreaOperacional);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
	}
}
	
	/**
	 * [UC523] - Filtrar Distrito Operacional
	 * 
	 * @author Hugo Azevedo
	 * @date 13/06/2014
	 * 
	 */
	public Collection<Object[]> pesquisarDistritoOperacional(
			String descricao, String indicadorPosicaoTexto, String sistemaAgua,
			String subsistemaAgua, String setorAbastecimento, 
			String indicadorUso,String numeroPagina, boolean relatorio) throws ControladorException{
		
		try {
			return repositorioOperacional.pesquisarDistritoOperacional(descricao, indicadorPosicaoTexto, 
					sistemaAgua, subsistemaAgua, setorAbastecimento, indicadorUso,numeroPagina,relatorio);
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
	}
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ControladorException
	 */
	public Integer obterZonaPressaoCount(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, String idDistritoOperacional) throws ControladorException{
		try {
			return repositorioOperacional.obterZonaPressaoCount(codigo, descricao, tipoPesquisa, descricaoAbreviada, idSistemaAbastecimento,
				 idSubsistemaAbastecimento, idSetorAbastecimento, idAreaOperacional, indicadorUso, idDistritoOperacional);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ControladorException
	 */
	public Collection<ZonaPressaoHelper> obterZonaPressao(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, Integer numeroPagina, String idDistritoOperacional) throws ControladorException{
				
		try {
			
			Collection<ZonaPressaoHelper> colecaoZonaPressao = repositorioOperacional.obterZonaPressao(codigo, descricao, tipoPesquisa, descricaoAbreviada, idSistemaAbastecimento,
				 idSubsistemaAbastecimento, idSetorAbastecimento, idAreaOperacional, indicadorUso, numeroPagina, idDistritoOperacional);
			
			SubsistemaAbastecimento subsistemaAbastecimento = null;
			if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				FiltroSubsistemaAbastecimento filtroSubsistemaAbastecimento = new FiltroSubsistemaAbastecimento();
				filtroSubsistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, Integer.valueOf(idSubsistemaAbastecimento)));
	
				Collection<SubsistemaAbastecimento> colecaoSubsistemaAbastecimento = getControladorUtil().pesquisar(filtroSubsistemaAbastecimento, SubsistemaAbastecimento.class.getName());
				
				subsistemaAbastecimento = (SubsistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSubsistemaAbastecimento);
			}
			
			SetorAbastecimento setorAbastecimento = null;
			if(idSetorAbastecimento != null && !idSetorAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				FiltroSetorAbastecimento filtroSetorAbastecimento = new FiltroSetorAbastecimento();
				filtroSetorAbastecimento.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, Integer.valueOf(idSetorAbastecimento)));
	
				Collection<SetorAbastecimento> colecaoSetorAbastecimento = getControladorUtil().pesquisar(filtroSetorAbastecimento, SetorAbastecimento.class.getName());
				
				setorAbastecimento = (SetorAbastecimento) Util.retonarObjetoDeColecao(colecaoSetorAbastecimento);
			}
			
			
			if (colecaoZonaPressao != null && !colecaoZonaPressao.isEmpty()){
				
				Iterator<ZonaPressaoHelper> it = colecaoZonaPressao.iterator();
				
				while (it.hasNext()){
					
					ZonaPressaoHelper zonaPressaoHelper = (ZonaPressaoHelper) it.next();
					
					if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
						//Subsistema Abastecimento Informado
						zonaPressaoHelper.setSubSistema(subsistemaAbastecimento.getDescricao());
					}else{
						//Pesquisa Subsistema Principal
						SubsistemaAbastecimento subsistemaAbastecimentoPrincipal = repositorioOperacional.obterSubsistemaPrincipalZonaPressao(zonaPressaoHelper.getIdZonaPressao());
						zonaPressaoHelper.setSubSistema(subsistemaAbastecimentoPrincipal.getDescricao());
					}
					
					if(idSetorAbastecimento != null && !idSetorAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
						//Distrito Operacional Informado
						zonaPressaoHelper.setSetor(setorAbastecimento.getDescricao());
					}else{
						//Pesquisa Distrito Principal
						SetorAbastecimento setorAbastecimentoPrincipal = repositorioOperacional.obterSetorPrincipalZonaPressao(zonaPressaoHelper.getIdZonaPressao());
						zonaPressaoHelper.setSetor(setorAbastecimentoPrincipal.getDescricao());
					}
				}
			}
			
			return colecaoZonaPressao;
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/** 
     * [UC0798] Manter Zona de Pressao 
     *  
     * @author Raphael Rossiter 
     * @date 20/06/2014 
     *  
     * @param idsZonaPressao 
     * @throws ControladorException 
     */
	public void removerZonaPressao(String[] idsZonaPressao) throws ControladorException {

		try{
			
			if (idsZonaPressao != null && idsZonaPressao.length != 0) {
				
				for (int i = 0; i < idsZonaPressao.length; i++) {
					
					int id = Integer.parseInt(idsZonaPressao[i]);
					
					ZonaPressao zonaPressao = new ZonaPressao();
					zonaPressao.setId(id);

					FiltroZonaAreaOperacional filtroZonaAreaOperacional = new FiltroZonaAreaOperacional();
					filtroZonaAreaOperacional.adicionarParametro(new ParametroSimples(FiltroZonaAreaOperacional.ID_ZONA_PRESSAO, id));
					Collection<ZonaAreaOperacional> colecaoZonaAreaOperacional = getControladorUtil().pesquisar(filtroZonaAreaOperacional,
						ZonaAreaOperacional.class.getName());
					
					if(colecaoZonaAreaOperacional != null && !colecaoZonaAreaOperacional.isEmpty()){
						
						Iterator<ZonaAreaOperacional> iteratorZonaAreaOperacional = colecaoZonaAreaOperacional.iterator();
						
						while (iteratorZonaAreaOperacional.hasNext()){
							ZonaAreaOperacional zonaAreaOperacional = (ZonaAreaOperacional) iteratorZonaAreaOperacional.next();
							getControladorUtil().remover(zonaAreaOperacional);
						}
					}
					
					getControladorUtil().remover(zonaPressao);
				}
			}
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(e.getMessage());
		}
	}
	
	/** 
     * [UC0798] Manter Zona de Pressao 
     *  
     * @author Raphael Rossiter 
     * @date 20/06/2014 
     *  
     * @param zonaPressao 
     * @throws ControladorException 
     */
	public void atualizarZonaPressao(ZonaPressao zonaPressao) throws ControladorException {
		
		try{
			
			zonaPressao.setUltimaAlteracao(new Date());
			
			if (zonaPressao.getDescricaoZonaPressao() == null || zonaPressao.getDescricaoZonaPressao().equals("")){
				throw new ControladorException("atencao.required", null, "Descrição");
			}
			
			Set<ZonaAreaOperacional> colecaoZonaAreaOperacional = zonaPressao.getAreasOperacionais();
			
			if (colecaoZonaAreaOperacional == null || colecaoZonaAreaOperacional.size() == 0){
				throw new ControladorException("atencao.required", null, "Área Operacional");
			}
			
			Iterator<ZonaAreaOperacional> it = colecaoZonaAreaOperacional.iterator();
			ZonaAreaOperacional zonaAreaOperacionalPrincipal = null;
			
			while (it.hasNext()){
				
				ZonaAreaOperacional zonaAreaOperacional = (ZonaAreaOperacional) it.next();
				
				if (zonaAreaOperacional.getIndicadorPrincipal().equals(ConstantesSistema.SIM)){
					zonaAreaOperacionalPrincipal = zonaAreaOperacional;
					zonaAreaOperacionalPrincipal.setZonaPressao(zonaPressao);
					break;
				}
			}
			
			if (zonaAreaOperacionalPrincipal == null){
				throw new ControladorException("atencao.required", null, "Área Operacional Principal");
			}
			
			//ATUALIZANDO NA BASE (ZONA DE PRESSAO)
			zonaPressao.setAreasOperacionais(null);
			this.getControladorUtil().atualizar(zonaPressao);
			
			//ATUALIZANDO O SISTEMA DE ABSTECIMENTO PRINCIPAL
			try{
				repositorioOperacional.atualizarZonaAreaOperacionalPrincipal(zonaAreaOperacionalPrincipal);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			
			//INSERINDO O SISTEMA DE ABSTECIMENTO SECUNDÁRIO
			it = colecaoZonaAreaOperacional.iterator();
			
			while (it.hasNext()){
				
				ZonaAreaOperacional zonaAreaOperacional = (ZonaAreaOperacional) it.next();
				
				if (!zonaAreaOperacional.getAreaOperacional().getId().equals(zonaAreaOperacionalPrincipal.getAreaOperacional().getId())){
					
					FiltroZonaAreaOperacional filtroZonaAreaOperacional = new FiltroZonaAreaOperacional();
					filtroZonaAreaOperacional.adicionarParametro(new ParametroSimples(FiltroZonaAreaOperacional.ID_ZONA_PRESSAO, 
						zonaPressao.getId()));
					filtroZonaAreaOperacional.adicionarParametro(new ParametroSimples(FiltroZonaAreaOperacional.ID_AREA_OPERACIONAL, 
						zonaAreaOperacional.getAreaOperacional().getId()));
					
					Collection<ZonaAreaOperacional> colecaoJaExiste = getControladorUtil().pesquisar(filtroZonaAreaOperacional,
						ZonaAreaOperacional.class.getName());
					
					if(colecaoJaExiste == null || colecaoJaExiste.isEmpty()){
						
						zonaAreaOperacional.setZonaPressao(zonaPressao);
						zonaAreaOperacional.setUltimaAlteracao(new Date());
						this.getControladorUtil().inserir(zonaAreaOperacional);
					}
				}
				else if (zonaAreaOperacional.getIndicadorPrincipal().equals(ConstantesSistema.NAO)){
					FiltroAreaOperacional filtroAreaOperacional = new FiltroAreaOperacional();
					filtroAreaOperacional.adicionarParametro(new ParametroSimples(FiltroAreaOperacional.ID, 
						zonaAreaOperacionalPrincipal.getAreaOperacional().getId()));
					
					Collection<AreaOperacional> colecaoAreaOperacional = this.getControladorUtil().pesquisar(filtroAreaOperacional, AreaOperacional.class.getName());
					
					AreaOperacional areaOperacional = (AreaOperacional) Util.retonarObjetoDeColecao(colecaoAreaOperacional);
					
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.zona_pressao.area_ja_informada", null, areaOperacional.getDescricao());
				}
			}
			
			//REMOVENDO O SISTEMA DE ABSTECIMENTO SECUNDÁRIO
			FiltroZonaAreaOperacional filtroZonaAreaOperacional = new FiltroZonaAreaOperacional();
			filtroZonaAreaOperacional.adicionarParametro(new ParametroSimples(FiltroZonaAreaOperacional.ID_ZONA_PRESSAO, 
				zonaPressao.getId()));
			
			Collection<ZonaAreaOperacional> colecaoRemover = getControladorUtil().pesquisar(filtroZonaAreaOperacional,
				ZonaAreaOperacional.class.getName());
			
			it = colecaoRemover.iterator();
			boolean remover = true;
			
			while (it.hasNext()){
				
				ZonaAreaOperacional zonaAreaOperacional = (ZonaAreaOperacional) it.next();
				
				Iterator<ZonaAreaOperacional> iterator = colecaoZonaAreaOperacional.iterator();
				
				remover = true;
				
				while(iterator.hasNext()){
					
					ZonaAreaOperacional zonaAreaOperacionalInformado = (ZonaAreaOperacional) iterator.next();
					
					if (zonaAreaOperacionalInformado.getAreaOperacional().getId()
						.equals(zonaAreaOperacional.getAreaOperacional().getId()) &&
						!zonaAreaOperacionalInformado.getAreaOperacional().getId()
						.equals(zonaAreaOperacionalPrincipal.getAreaOperacional().getId())){
						
						remover = false;
						break;
					}
				}
				
				if (remover && zonaAreaOperacional.getIndicadorPrincipal().equals(ConstantesSistema.NAO)){
					
					this.getControladorUtil().remover(zonaAreaOperacional);
				}
			}
	
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			
			if (e instanceof ControladorException) {
				ControladorException exception = (ControladorException) e;
				throw new ControladorException(exception.getMessage(), null, exception.getParametroMensagem().get(0));
			}
			else{
				throw new ControladorException(e.getMessage());
			}
		}
	}

	
	
	/**
	 * [UC1613] - Gerar Relatório Totalizador por Sistema de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 20/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioTotalizadorSistemaAbastecimentoBean> pesquisarTotalizacaoSetoresAbastecimento(String sistemaAbastecimento, 
																		 String subsistemaAbastecimento,
																		 String setorAbastecimento,
																		 String distritoOperacional,
																		 String areaOperacional,
																		 String zonaPressao,
																		 String tipoRelatorio) throws ControladorException{
		
		
		Collection<RelatorioTotalizadorSistemaAbastecimentoBean> retorno = new ArrayList<RelatorioTotalizadorSistemaAbastecimentoBean>();
		
		
		try{
			
			SistemaParametro sistParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			Integer sistemaParametroMenosUm = Util.subtrairMesDoAnoMes(sistParametro.getAnoMesFaturamento(), 1);
			
			
			//Sintetico
			if(tipoRelatorio.equals("1")){
				Collection<Object[]> colecaoBeans = this.repositorioOperacional.pesquisarTotalizacaoSetoresAbastecimentoSintetico(sistemaAbastecimento, 
						subsistemaAbastecimento, setorAbastecimento, distritoOperacional, areaOperacional, zonaPressao);
				if(colecaoBeans != null && !colecaoBeans.isEmpty()){
				
					Iterator<Object[]> it = colecaoBeans.iterator();
					while(it.hasNext()){
						
						Object[] obj = it.next();
						RelatorioTotalizadorSistemaAbastecimentoBean bean = new RelatorioTotalizadorSistemaAbastecimentoBean();
						
						bean.setSistemaAbastecimento((String)obj[0]);
						bean.setSubsistemaAbastecimento((String)obj[1]);
						bean.setSetorAbastecimento((String)obj[2]);
						bean.setDistritoOperacional((String)obj[3]);
						bean.setAreaOperacional((String)obj[4]);
						bean.setZonaPressao((String)obj[5]);
						bean.setQuantidade((Integer)obj[6]);
						
						retorno.add(bean);
					}
				
				}
			}
			
			//Analítico
			else{
				
				Collection<Object[]> colecaoBeans = this.repositorioOperacional.pesquisarTotalizacaoSetoresAbastecimentoAnalitico(sistemaAbastecimento, 
																					subsistemaAbastecimento, setorAbastecimento, distritoOperacional, 
																					areaOperacional, zonaPressao,sistemaParametroMenosUm);
				
				if(colecaoBeans != null && !colecaoBeans.isEmpty()){
					
					Iterator<Object[]> it = colecaoBeans.iterator();
					while(it.hasNext()){
						Object[] obj = it.next();
						RelatorioTotalizadorSistemaAbastecimentoBean bean = new RelatorioTotalizadorSistemaAbastecimentoBean();
						
						bean.setSistemaAbastecimento((String)obj[0]);
						bean.setSubsistemaAbastecimento((String)obj[1]);
						bean.setSetorAbastecimento((String)obj[2]);
						bean.setDistritoOperacional((String)obj[3]);
						bean.setAreaOperacional((String)obj[4]);
						bean.setZonaPressao((String)obj[5]);
						bean.setMatriculaImovel((String)obj[6]);
						bean.setNomeUsuario((String)obj[7]);
						bean.setLocalidade((String)obj[8]);
						bean.setSituacaoAgua((String)obj[9]);
						bean.setSituacaoMedicao((String)obj[10]);
						bean.setVolumeMedido((String)obj[11]);
						bean.setVolumeFaturado((String)obj[12]);
						
						retorno.add(bean);
					}
					
				}
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return retorno;
		
	}
	

}