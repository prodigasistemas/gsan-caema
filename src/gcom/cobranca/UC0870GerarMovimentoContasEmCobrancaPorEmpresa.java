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
package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.EmpresaCobranca;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaCobranca;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.ControladorUnidadeLocal;
import gcom.cadastro.unidade.ControladorUnidadeLocalHome;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.conta.ContaGeral;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0745] - GerarArquivoTextoFaturamento, gerando
 * maior facilidade na manutenção do mesmo.  
 *
 * @author Raphael Rossiter
 * @date 30/04/2008
 */
public class UC0870GerarMovimentoContasEmCobrancaPorEmpresa {

	private static UC0870GerarMovimentoContasEmCobrancaPorEmpresa instancia;
	
	private IRepositorioCobranca repositorioCobranca;

	
	private UC0870GerarMovimentoContasEmCobrancaPorEmpresa(IRepositorioCobranca repositorioCobranca) {

		this.repositorioCobranca = repositorioCobranca;
	}

	public static UC0870GerarMovimentoContasEmCobrancaPorEmpresa getInstancia(IRepositorioCobranca repositorioCobranca) {
		
		if (instancia == null) {
			instancia = new UC0870GerarMovimentoContasEmCobrancaPorEmpresa(repositorioCobranca);
		}
		return instancia;
	}
	
	
	/**
	 * Controlador Util 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorUtilLocal
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

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
	 * Controlador Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

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
	 * Controlador Faturamento 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorFaturamentoLocal
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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
	 * Controlador Cobranca 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorCobrancaLocal
	 */
	protected ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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
	
	
	
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	/**
	 * [UC0870] - Gerar Movimento de Contas em Cobrança por Empresa
	 *
	 * @author Rafael Corrêa
	 * @date 17/04/2008
	 *
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
	@SuppressWarnings("unchecked")
	public void gerarMovimentoContasEmCobranca(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) throws ControladorException {

		try {

			// [SB0002 - Verificar Registros Já inseridos];
			System.out.println("-----------------------");
			System.out.println("INICIO: [SB0002 - Verificar Registros Ja inseridos] - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");
			
			this.verificarRegistrosInseridos(comandoEmpresaCobrancaConta);
			
			System.out.println("-----------------------");
			System.out.println("FIM: [SB0002 - Verificar Registros Ja inseridos] - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");

			boolean percentualInformado = false;
			EmpresaCobranca empresaCobranca = null;
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = null;

			SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();
			Integer anoMesArrecadacaoInicio = sistemaParametros.getAnoMesArrecadacao();
			Integer anoMesArrecadacaoFim = Util.somaMesAnoMesReferencia(sistemaParametros.getAnoMesArrecadacao(), 36);

			FiltroEmpresaCobranca filtroEmpresaCobranca = new FiltroEmpresaCobranca();
			filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(	FiltroEmpresaCobranca.EMPRESA_ID,
																			comandoEmpresaCobrancaConta.getEmpresa().getId()));
			filtroEmpresaCobranca.adicionarParametro(new ParametroNulo(FiltroEmpresaCobranca.DATA_FIM_CONTRATO));

			@SuppressWarnings("rawtypes")
			Collection colecaoEmpresaCobranca = getControladorUtil().pesquisar(filtroEmpresaCobranca, EmpresaCobranca.class.getName());

			if (colecaoEmpresaCobranca != null && !colecaoEmpresaCobranca.isEmpty()) {
				empresaCobranca = (EmpresaCobranca) Util.retonarObjetoDeColecao(colecaoEmpresaCobranca);

				//PERCENTUAL INFORMADO
				if (empresaCobranca.getPercentualContratoCobranca() != null) {
					percentualInformado = true;
				}
				
				//OBTENDO AS FAIXAS CADASTRADAS PARA A EMPRESA DE COBRANÇA
				FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();
				filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(
						FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresaCobranca.getId()));
				filtroEmpresaCobrancaFaixa.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);
				
				colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) this.getControladorUtil().pesquisar(
						filtroEmpresaCobrancaFaixa, EmpresaCobrancaFaixa.class.getName());
			}

			Collection<Integer> idsImoveisAtualizar = new ArrayList<Integer>();
			Map<Integer, Integer> imovelOS = new HashMap<Integer, Integer>();
			@SuppressWarnings("rawtypes")
			Collection collCobrancaSituacaoHistorico = new ArrayList();
			Collection<ImovelCobrancaSituacao> collImovelCobrancaSituacao = new ArrayList<ImovelCobrancaSituacao>();

			Collection<GerenciaRegional> collGerenciaRegional = new ArrayList<GerenciaRegional>();
			Collection<UnidadeNegocio> collUnidadeNegocio = new ArrayList<UnidadeNegocio>();
			Collection<ImovelPerfil> collImovelPerfil = new ArrayList<ImovelPerfil>();
			Collection<LigacaoAguaSituacao> collLigacaoAguaSituacao = new ArrayList<LigacaoAguaSituacao>();

			ServicoTipo servicoTipo = null;

			ComandoEmpresaCobrancaContaHelper helper = new ComandoEmpresaCobrancaContaHelper();

			helper.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);

			FiltroComandoEmpresaCobrancaContaGerencia filtroComandoEmpresaCobrancaContaGerencia = new FiltroComandoEmpresaCobrancaContaGerencia();
			filtroComandoEmpresaCobrancaContaGerencia.adicionarParametro(new ParametroSimples(	FiltroComandoEmpresaCobrancaContaGerencia.ID_COMANDO_EMPRESA_COBRANCA_CONTA,
																								comandoEmpresaCobrancaConta.getId()));
			@SuppressWarnings("rawtypes")
			Collection colecaoComandoEmpresaCobrancaContaGerencia = this.getControladorUtil().pesquisar(filtroComandoEmpresaCobrancaContaGerencia, ComandoEmpresaCobrancaContaGerencia.class.getName());

			if (colecaoComandoEmpresaCobrancaContaGerencia != null && !colecaoComandoEmpresaCobrancaContaGerencia.isEmpty()) {
				@SuppressWarnings("rawtypes")
				Iterator iterator = colecaoComandoEmpresaCobrancaContaGerencia.iterator();
				while (iterator.hasNext()) {
					GerenciaRegional gerenciaRegional = (GerenciaRegional) iterator.next();
					collGerenciaRegional.add(gerenciaRegional);
				}
				helper.setColecaoGerenciaRegional(collGerenciaRegional);
			}

			FiltroComandoEmpresaCobrancaContaUnidadeNegocio filtroComandoEmpresaCobrancaContaUnidadeNegocio = new FiltroComandoEmpresaCobrancaContaUnidadeNegocio();
			filtroComandoEmpresaCobrancaContaUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaContaUnidadeNegocio.ID_COMANDO_EMPRESA_COBRANCA_CONTA,
																									comandoEmpresaCobrancaConta.getId()));
			@SuppressWarnings("rawtypes")
			Collection colecaoComandoEmpresaCobrancaContaUnidadeNegocio = this.getControladorUtil().pesquisar(filtroComandoEmpresaCobrancaContaUnidadeNegocio, ComandoEmpresaCobrancaContaUnidadeNegocio.class.getName());

			if (colecaoComandoEmpresaCobrancaContaUnidadeNegocio != null
					&& !colecaoComandoEmpresaCobrancaContaUnidadeNegocio.isEmpty()) {
				@SuppressWarnings("rawtypes")
				Iterator iterator = colecaoComandoEmpresaCobrancaContaUnidadeNegocio.iterator();
				while (iterator.hasNext()) {
					UnidadeNegocio unidadeNegocio = (UnidadeNegocio) iterator.next();
					collUnidadeNegocio.add(unidadeNegocio);
				}
				helper.setColecaoUnidadeNegocio(collUnidadeNegocio);
			}

			FiltroComandoEmpresaCobrancaContaImovelPerfil filtroComandoEmpresaCobrancaContaImovelPerfil = new FiltroComandoEmpresaCobrancaContaImovelPerfil();
			filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarParametro(new ParametroSimples(	FiltroComandoEmpresaCobrancaContaImovelPerfil.ID_COMANDO_EMPRESA_COBRANCA_CONTA,
																									comandoEmpresaCobrancaConta.getId()));
			filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
			@SuppressWarnings("rawtypes")
			Collection colecaoComandoEmpresaCobrancaContaImovelPerfil = this.getControladorUtil().pesquisar(filtroComandoEmpresaCobrancaContaImovelPerfil, ComandoEmpresaCobrancaContaImovelPerfil.class.getName());

			if (colecaoComandoEmpresaCobrancaContaImovelPerfil != null
					&& !colecaoComandoEmpresaCobrancaContaImovelPerfil.isEmpty()) {
				@SuppressWarnings("rawtypes")
				Iterator iterator = colecaoComandoEmpresaCobrancaContaImovelPerfil.iterator();
				while (iterator.hasNext()) {
					ComandoEmpresaCobrancaContaImovelPerfil comandoEmpresaCobrancaContaImovelPerfil = (ComandoEmpresaCobrancaContaImovelPerfil) iterator.next();
					ImovelPerfil imovelPerfil = comandoEmpresaCobrancaContaImovelPerfil.getImovelPerfil();

					collImovelPerfil.add(imovelPerfil);
				}
				helper.setColecaoImovelPerfil(collImovelPerfil);
			}

			FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao = new FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao();
			filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(	FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao.ID_COMANDO_EMPRESA_COBRANCA_CONTA,
																										comandoEmpresaCobrancaConta.getId()));
			filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao.adicionarCaminhoParaCarregamentoEntidade(FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao.LIGACAO_AGUA_SITUACAO);
			@SuppressWarnings("rawtypes")
			Collection colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao = this.getControladorUtil().pesquisar(filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao, CmdEmpresaCobrancaContaLigacaoAguaSituacao.class.getName());

			if (colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao != null
					&& !colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao.isEmpty()) {
				@SuppressWarnings("rawtypes")
				Iterator iterator = colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao.iterator();
				while (iterator.hasNext()) {
					CmdEmpresaCobrancaContaLigacaoAguaSituacao cmdEmpresaCobrancaContaLigacaoAguaSituacao = (CmdEmpresaCobrancaContaLigacaoAguaSituacao) iterator.next();
					LigacaoAguaSituacao ligacaoAguaSituacao = cmdEmpresaCobrancaContaLigacaoAguaSituacao.getLigacaoAguaSituacao();

					collLigacaoAguaSituacao.add(ligacaoAguaSituacao);
				}
				helper.setColecaoLigacaoAguaSituacao(collLigacaoAguaSituacao);
			}

			if (comandoEmpresaCobrancaConta.getServicoTipo() != null
					&& comandoEmpresaCobrancaConta.getServicoTipo().getId() != null) {
				
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
				filtroServicoTipo.adicionarParametro(new ParametroSimples(	FiltroServicoTipo.ID,
																			comandoEmpresaCobrancaConta.getServicoTipo().getId()));
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoTipo.SERVICO_TIPO_REFERENCIA);
				@SuppressWarnings("rawtypes")
				Collection colecaoServTipo = this.getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

				if (colecaoServTipo != null && !colecaoServTipo.isEmpty()) {
					servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServTipo);
				}
			}
			if (sistemaParametros.getIndicadorTotalDebito() == 1) {
				helper.setIndicadorTotalDebito(true);
			} else {
				helper.setIndicadorTotalDebito(false);
			}

			helper.setColecaoSetoresComponent(getControladorCobranca().pesquisarSetoresComerciaisComandoEmpresaCobrancaContaSetorComercial(comandoEmpresaCobrancaConta.getId()));

			// Colecao de cobranca ação associada ao comando
			helper.setColecaoAcoesCobrancaNaoGeracao(getControladorCobranca().pesquisarComandoEmpresaCobrancaContaAcaoCobranca(comandoEmpresaCobrancaConta.getId()));

			// Colecao de situações de cobrança associadas ao comando
			helper.setColecaoCobrancaSituacao(getControladorCobranca().pesquisarComandoEmpresaCobrancaContaCobrancaSituacao(comandoEmpresaCobrancaConta.getId()));
			
			
			//*********** Pesquisa Imoveis**************
			System.out.println("-----------------------");
			System.out.println("INICIO: Selecionando os imoveis - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");
			
			Integer quantidadeMenorFaixa = null;
			
			if (!percentualInformado) {
				
				quantidadeMenorFaixa = this.getControladorFaturamento().pesquisarQuantidadeContasMenorFaixa(
				helper.getComandoEmpresaCobrancaConta().getEmpresa().getId());
			}
			
			this.getControladorCobranca().inserirComandoEmpresaCobrancaContaImovelParcial(helper, percentualInformado);
			
			this.getControladorCobranca().inserirImovelEmpresaCobrancaComandoAberto();
			
			this.getControladorCobranca().inserirComandoEmpresaCobrancaContaImovel(helper, percentualInformado, sistemaParametros, quantidadeMenorFaixa);
			
			System.out.println("-----------------------");
			System.out.println("FIM: Selecionando os imoveis - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");
			//*****************************************
			
			if (((helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty())
					|| (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty())
					|| comandoEmpresaCobrancaConta.getLocalidadeInicial() != null
					|| comandoEmpresaCobrancaConta.getLocalidadeInicial() != null
					|| comandoEmpresaCobrancaConta.getGerenciaRegional() != null || comandoEmpresaCobrancaConta.getUnidadeNegocio() != null)
					&& comandoEmpresaCobrancaConta.getQtdImoveisComando() != null) {

				//COM PROPORCIONALIDADE
				this.gerarDadosEmpresaCobrancaComProporcionalidade(comandoEmpresaCobrancaConta, empresaCobranca, sistemaParametros, 
					anoMesArrecadacaoInicio, anoMesArrecadacaoFim, idsImoveisAtualizar, imovelOS, collCobrancaSituacaoHistorico, 
					collImovelCobrancaSituacao, servicoTipo, helper, colecaoEmpresaCobrancaFaixa);

			} else {

				//SEM PROPORCIONALIDADE
				this.gerarDadosEmpresaCobrancaSemProporcionalidade(comandoEmpresaCobrancaConta, empresaCobranca, sistemaParametros, 
					anoMesArrecadacaoInicio, anoMesArrecadacaoFim, idsImoveisAtualizar, imovelOS, collCobrancaSituacaoHistorico, 
					collImovelCobrancaSituacao, servicoTipo, helper, colecaoEmpresaCobrancaFaixa);
			}

			if (percentualInformado) {
				getControladorBatch().inserirColecaoObjetoParaBatch(collCobrancaSituacaoHistorico);
			}
			for (ImovelCobrancaSituacao iscb : collImovelCobrancaSituacao) {
				Integer iscbId = (Integer) getControladorUtil().inserir(iscb);

				// Objeto que guarda a associação da situação de cobrança do
				// imóvel ao comando correspondente.
				ComandoImovelCobrancaSituacao comandoImovelCobrancaSituacao = new ComandoImovelCobrancaSituacao();
				comandoImovelCobrancaSituacao.setComando(comandoEmpresaCobrancaConta);
				comandoImovelCobrancaSituacao.setImovelCobranca(iscb);
				comandoImovelCobrancaSituacao.setUltimaAlteracao(new Date());

				ComandoImovelCobrancaSituacaoPK comandoImovelCobrancaSituacaoPK = new ComandoImovelCobrancaSituacaoPK();
				comandoImovelCobrancaSituacaoPK.setIdComando(comandoEmpresaCobrancaConta.getId());
				comandoImovelCobrancaSituacaoPK.setIdImovelCobrancaSituacao(iscbId);
				comandoImovelCobrancaSituacao.setPk(comandoImovelCobrancaSituacaoPK);

				getControladorUtil().inserir(comandoImovelCobrancaSituacao);
			}

			Integer idCobSituacao = repositorioCobranca.pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);

			if (idCobSituacao != null && idsImoveisAtualizar != null && !idsImoveisAtualizar.isEmpty()) {
				getControladorImovel().atualizarSituacaoCobrancaETipoIdsImoveis(idCobSituacao, idsImoveisAtualizar);
			}
			
			//REMOVENDO TABELA AUXILIAR DOS IMÓVEIS PARCIAL
			getControladorCobranca().removerComandoEmpresaCobrancaContaImovelParcial(comandoEmpresaCobrancaConta.getId());
			
			//REMOVENDO TABELA AUXILIAR DOS IMÓVEIS
			getControladorCobranca().removerComandoEmpresaCobrancaContaImovel(comandoEmpresaCobrancaConta.getId());
			
			//REMOVENDO TABELA AUXILIAR DAS CONTAS
			getControladorCobranca().removerComandoEmpresaCobrancaContaConta(comandoEmpresaCobrancaConta.getId());

		} catch (Exception e) {
			throw new ControladorException(	"erro.sistema", e);
		}
	}

	/**
	 * @param comandoEmpresaCobrancaConta
	 * @param percentualInformado
	 * @param empresaCobranca
	 * @param sistemaParametros
	 * @param anoMesArrecadacaoInicio
	 * @param anoMesArrecadacaoFim
	 * @param idsImoveis
	 * @param idsImoveisAtualizar
	 * @param idsContas
	 * @param imovelOS
	 * @param collCobrancaSituacaoHistorico
	 * @param collImovelCobrancaSituacao
	 * @param servicoTipo
	 * @throws ErroRepositorioException
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	private void gerarDadosEmpresaCobrancaComProporcionalidade(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta,
			EmpresaCobranca empresaCobranca, SistemaParametro sistemaParametros,
			Integer anoMesArrecadacaoInicio, Integer anoMesArrecadacaoFim,
			Collection<Integer> idsImoveisAtualizar, Map<Integer, Integer> imovelOS,
			@SuppressWarnings("rawtypes") Collection collCobrancaSituacaoHistorico, Collection<ImovelCobrancaSituacao> collImovelCobrancaSituacao,
			ServicoTipo servicoTipo, ComandoEmpresaCobrancaContaHelper helper, List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException {
		
		try{
			
			// GERANDO TABELA AUXILIAR DAS CONTAS
			System.out.println("-----------------------");
			System.out.println("INICIO: GERANDO TABELA AUXILIAR DAS CONTAS - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");
			
			repositorioCobranca.inserirComandoEmpresaCobrancaContaConta(comandoEmpresaCobrancaConta, sistemaParametros, helper);
			
			System.out.println("-----------------------");
			System.out.println("FIM: GERANDO TABELA AUXILIAR DAS CONTAS - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");
			
			Collection<Object[]> colecaoDadosEmpresaCobrancaConta = null;
			Collection<EmpresaCobrancaContaProporcionalidade> colecaoEmpresaCobrancaContaProporcionalidadeINSERT = null;
			
			Integer indice = 0;
			final int qtdRegistros = 100000;
			boolean terminou = false;
			
			while (!terminou) {
				
				colecaoDadosEmpresaCobrancaConta = repositorioCobranca
				.pesquisarComandoEmpresaCobrancaContaConta(comandoEmpresaCobrancaConta, indice, qtdRegistros);
				
				if (colecaoDadosEmpresaCobrancaConta == null || colecaoDadosEmpresaCobrancaConta.size() < qtdRegistros) {
					terminou = true;
				} 
				else {
					indice = indice + 1;
				}
				
				if (colecaoDadosEmpresaCobrancaConta != null && !colecaoDadosEmpresaCobrancaConta.isEmpty()) {
					
					EmpresaCobrancaContaProporcionalidade empresaCobrancaContaProporcionalidade = null;
					colecaoEmpresaCobrancaContaProporcionalidadeINSERT = new ArrayList<EmpresaCobrancaContaProporcionalidade>();
					
					//PARA CADA CONTA SELECIONADA DO IMÓVEL
					for (Object[] dadosEmpresaCobrancaConta : colecaoDadosEmpresaCobrancaConta) {
						
						//GERANDO A PROPORCIONALIDADE
						empresaCobrancaContaProporcionalidade = this.criarEmpresaCobrancaContaProporcionalidade(
									dadosEmpresaCobrancaConta,
									comandoEmpresaCobrancaConta,
									empresaCobranca,
									(Integer) dadosEmpresaCobrancaConta[7],
									colecaoEmpresaCobrancaFaixa);
						
						if (empresaCobrancaContaProporcionalidade.getPercentualEmpresaConta() != null){
							
							colecaoEmpresaCobrancaContaProporcionalidadeINSERT.add(empresaCobrancaContaProporcionalidade);
						}
					}
					
					// insere os registros em empresaCobrancaConta
					this.getControladorCobranca().inserirColecaoEmpresaCobrancaContaProporcionalidadeSemTransacao(colecaoEmpresaCobrancaContaProporcionalidadeINSERT);
					
					//LIBERANDO MEMÓRIA
					colecaoEmpresaCobrancaContaProporcionalidadeINSERT.clear();
					colecaoEmpresaCobrancaContaProporcionalidadeINSERT = null;	
				}
				
				if (colecaoDadosEmpresaCobrancaConta != null){
					
					colecaoDadosEmpresaCobrancaConta.clear();
					colecaoDadosEmpresaCobrancaConta = null;
				}
			}
			
			
			
			//************** Inicio RM 7313 - Proporcionalidade***********************
			
			/**
			 * Para cada faixa do comando, são pesquisados os imoveis de cadas faixa
			 */
			FiltroComandoEmpresaCobrancaContaFaixa filtroComandoEmpresaCobrancaContaFaixa = new FiltroComandoEmpresaCobrancaContaFaixa();
			filtroComandoEmpresaCobrancaContaFaixa.adicionarParametro(
				new ParametroSimples(FiltroComandoEmpresaCobrancaContaFaixa.COMANDO_EMPRESA_COBRANCA_CONTA_ID, comandoEmpresaCobrancaConta.getId()));
			
			filtroComandoEmpresaCobrancaContaFaixa.adicionarCaminhoParaCarregamentoEntidade(FiltroComandoEmpresaCobrancaContaFaixa.EMPRESA_COBRANCA_FAIXA);
			
			Collection colecaoComandoEmpresaCobrancaContaFaixa = getControladorUtil().pesquisar(
				filtroComandoEmpresaCobrancaContaFaixa, ComandoEmpresaCobrancaContaFaixa.class.getName());
			
			if(colecaoComandoEmpresaCobrancaContaFaixa != null && !colecaoComandoEmpresaCobrancaContaFaixa.isEmpty()){
				
				System.out.println("Cobrança por Resultado - Comando: " + comandoEmpresaCobrancaConta.getId() +  
					   " Quantidade de FAIXAS do Comando: " + comandoEmpresaCobrancaConta.getId() + ": " + colecaoComandoEmpresaCobrancaContaFaixa.size());
			
				//SELECIONANDO A SITUAÇÃO DA COBRANÇA QUE SERÁ UTILIZADA NA GERAÇÃO DA TABELA EMPRESA_COBRANCA_CONTA
				Integer idCobSituacao = repositorioCobranca.pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
				
				CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
				cobrancaSituacao.setId(idCobSituacao);
				
				Collection colecaoEmpresaCobrancaConta = null;
				
				Iterator iterator = colecaoComandoEmpresaCobrancaContaFaixa.iterator();
				
				//PARA TESTE
				int countFaixa = 0;
				
				while (iterator.hasNext()) {
	
					ComandoEmpresaCobrancaContaFaixa comandoEmpresaCobrancaContaFaixa = (ComandoEmpresaCobrancaContaFaixa) iterator.next();
					
					//PARA TESTE
					countFaixa++;
	
					EmpresaCobrancaFaixa empresaCobrancaFaixa = comandoEmpresaCobrancaContaFaixa.getEmpresaCobrancaFaixa();
	
					Integer qtdeImoveisFaixa = 0;
	
					BigDecimal qtdeMaximaImoveisFaixa = new BigDecimal(0);
	
					BigDecimal qtdeImoveisComandos = new BigDecimal(comandoEmpresaCobrancaConta.getQtdImoveisComando());
	
					qtdeMaximaImoveisFaixa = (comandoEmpresaCobrancaContaFaixa.getPercentualInformadoFaixa().multiply(qtdeImoveisComandos));
	
					empresaCobrancaFaixa.setNumeroMaximoImoveisFaixa(qtdeMaximaImoveisFaixa.divide(new BigDecimal(100.00), 0, BigDecimal.ROUND_HALF_EVEN).intValue());
	
					// chamar pesquisa dos imoveis por faixa
					Integer numeroIndice = 0;
					final int quantidadeRegistros = 1000;
					boolean flagTerminou = false;
					
					if(empresaCobrancaFaixa.getNumeroMaximoImoveisFaixa() == 0){
						flagTerminou = true;
					}
	
					while (!flagTerminou) {
	
						System.out.println("-----------------------");
						System.out.println("INICIO: Selecionando imoveis para PROPORCIONALIDADE - Comando: " 
						+ comandoEmpresaCobrancaConta.getId() + " Percentual Faixa: " + empresaCobrancaFaixa.getPercentualFaixa().toString());
						System.out.println("FAIXA: " + countFaixa + " / " + colecaoComandoEmpresaCobrancaContaFaixa.size());
						System.out.println("-----------------------");
						
						//SELECIONADO OS IMÓVEIS
						Collection colecaoImoveisProporcionalidade = this.getControladorCobranca().selecionarListaImoveisProporcionalidade(
							empresaCobrancaFaixa.getPercentualFaixa(), comandoEmpresaCobrancaConta.getId(), numeroIndice);
						
						System.out.println("-----------------------");
						System.out.println("FIM: Selecionando imoveis para PROPORCIONALIDADE - Comando: " 
						+ comandoEmpresaCobrancaConta.getId() + " Percentual Faixa: " + empresaCobrancaFaixa.getPercentualFaixa().toString());
						System.out.println("FAIXA: " + countFaixa + " / " + colecaoComandoEmpresaCobrancaContaFaixa.size());
						System.out.println("-----------------------");
	
						/**
						 * Para cada imovel, são pesquisadas as contas que serão incluídas em empresaCobrancaConta
						 */
						if (colecaoImoveisProporcionalidade != null && !colecaoImoveisProporcionalidade.isEmpty()) {
	
							Iterator iteratorImoveisProporcionalidade = colecaoImoveisProporcionalidade.iterator();
	
							colecaoEmpresaCobrancaConta = new ArrayList();
							
							while (iteratorImoveisProporcionalidade.hasNext()) {
								
								Integer idImovel = (Integer) iteratorImoveisProporcionalidade.next();
								
								//SELECIONADO AS EMPRESAS
								Collection colecaoEmpresaCobrancaContaProporcionalidade = this.getControladorCobranca().selecionarListaEmpresaCobrancaContaProporcionalidade(
									empresaCobrancaFaixa.getPercentualFaixa(), comandoEmpresaCobrancaConta.getId(), idImovel);
								
								//System.out.println("Imovel (EmpresaCobrancaConta: " + idImovel + " FAIXA: " + countFaixa + " / " + colecaoComandoEmpresaCobrancaContaFaixa.size());
	
								if (colecaoEmpresaCobrancaContaProporcionalidade != null && !colecaoEmpresaCobrancaContaProporcionalidade.isEmpty()) {
	
									Iterator iteratorProporcionalidade = colecaoEmpresaCobrancaContaProporcionalidade.iterator();
	
									while (iteratorProporcionalidade.hasNext()) {
	
										EmpresaCobrancaContaProporcionalidade empresaCobrancaContaProporcionalidade = (EmpresaCobrancaContaProporcionalidade) iteratorProporcionalidade.next();
	
										//GERANDO EMPRESA_COBRANCA_CONTA
										EmpresaCobrancaConta empresaCobrancaConta = this.criarEmpresaCobrancaContaComProporcionalidade(empresaCobrancaContaProporcionalidade);
	
										if (!idsImoveisAtualizar.contains(idImovel)) {
											
											//GERANADO COBRANCA_SITUACAO_HISTORICO
											CobrancaSituacaoHistorico cobrancaSituacaoHistorico = criarCobrancaSituacaoHistorico(empresaCobrancaContaProporcionalidade.getImovel().getId(), anoMesArrecadacaoInicio, anoMesArrecadacaoFim);
											collCobrancaSituacaoHistorico.add(cobrancaSituacaoHistorico);
	
											//GERANDO COBRANCA_SITUACAO
											ImovelCobrancaSituacao imovelCobrancaSituacao = criarImovelCobrancaSituacao(
												empresaCobrancaContaProporcionalidade.getImovel().getId(), comandoEmpresaCobrancaConta, cobrancaSituacao);
											
											collImovelCobrancaSituacao.add(imovelCobrancaSituacao);
	
											idsImoveisAtualizar.add(empresaCobrancaContaProporcionalidade.getImovel().getId());
	
											// 2.1.19. Caso o comando tenha tipo de serviço informado
											if (servicoTipo != null && servicoTipo.getId() != null) {
	
												if (servicoTipo != null && servicoTipo.getId() != null) {
													UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) this.getControladorUnidade()
															.pesquisarUnidadeOrganizacionalPorEmpresa(comandoEmpresaCobrancaConta.getEmpresa().getId());
	
													OrdemServico ordemServico = new OrdemServico();
													ordemServico.setServicoTipo(servicoTipo);
													ordemServico.setImovel(empresaCobrancaContaProporcionalidade.getImovel());
													ordemServico.setUnidadeAtual(unidadeOrganizacional);
	
													Integer idOS = this.getControladorOrdemServico().gerarOrdemServico(ordemServico, Usuario.USUARIO_BATCH, Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA);
	
													imovelOS.put(empresaCobrancaContaProporcionalidade.getImovel().getId(), idOS);
												}
	
											}
	
										}
	
										if (imovelOS != null
												&& !imovelOS.isEmpty()
												&& imovelOS.get(empresaCobrancaContaProporcionalidade.getImovel().getId()) != null) {
											OrdemServico ordemServico = new OrdemServico();
											ordemServico.setId(imovelOS.get(empresaCobrancaContaProporcionalidade.getImovel().getId()));
	
											empresaCobrancaConta.setOrdemServico(ordemServico);
										}
	
										colecaoEmpresaCobrancaConta.add(empresaCobrancaConta);
									}
	
								}
								colecaoEmpresaCobrancaContaProporcionalidade.clear();
								colecaoEmpresaCobrancaContaProporcionalidade = null;
	
								qtdeImoveisFaixa = qtdeImoveisFaixa + 1;
	
								if (qtdeImoveisFaixa.equals(empresaCobrancaFaixa.getNumeroMaximoImoveisFaixa())) {
									flagTerminou = true;
									break;
	
								}
								
								if (colecaoImoveisProporcionalidade.size() < quantidadeRegistros && !flagTerminou) {
									flagTerminou = true;
								} else if(!flagTerminou){
									numeroIndice = numeroIndice + 1;
								}
							}
							
							// insere os registros em empresaCobrancaConta
							System.out.println("-----------------------");
							System.out.println("Qtde colecaoEmpresaCobrancaConta INSERINDO INICIO: " + colecaoEmpresaCobrancaConta.size() + " ");
							System.out.println("-----------------------");
							//this.inserirEmpresaCobrancaConta(colecaoEmpresaCobrancaConta);
							this.getControladorCobranca().inserirColecaoEmpresaCobrancaContaSemTransacao(colecaoEmpresaCobrancaConta);
							System.out.println("-----------------------");
							System.out.println("Qtde colecaoEmpresaCobrancaConta INSERINDO FIM: " + colecaoEmpresaCobrancaConta.size() + " ");
							System.out.println("-----------------------");
							
							colecaoEmpresaCobrancaConta.clear();
							colecaoEmpresaCobrancaConta = null;	
						}
						else{
							
							//NENHUM REGISTRO FOI ENCONTRADO
							flagTerminou = true;
						}
					}
					
					System.out.println("-----------------------");
					System.out.println("Quantidade FAIXAS: " + countFaixa + " / " + colecaoComandoEmpresaCobrancaContaFaixa.size());
					System.out.println("-----------------------");
				}
			}
		} 
		catch (Exception e) {
			throw new ControladorException(	"erro.sistema", e);
		}
	}
	
	private EmpresaCobrancaContaProporcionalidade criarEmpresaCobrancaContaProporcionalidade(Object[] dadosEmpresaCobrancaConta, 
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, 
			EmpresaCobranca empresaCobranca, Integer quantidadeContas, List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException {
		
		EmpresaCobrancaContaProporcionalidade retorno = new EmpresaCobrancaContaProporcionalidade();
		
		try{
			
			if (dadosEmpresaCobrancaConta != null) {
				
				// Id da Conta
				if (dadosEmpresaCobrancaConta[0] != null) {
					
					ContaGeral contaGeral = new ContaGeral();
					contaGeral.setId((Integer) dadosEmpresaCobrancaConta[0]);
					
					retorno.setContaGeral(contaGeral);
					retorno.setAnoMesReferenciaConta((Integer) dadosEmpresaCobrancaConta[4]);
					
					retorno.setDataEnvioConta(new Date());
					
				}
				
				// Valor Original da Conta
				if (dadosEmpresaCobrancaConta[1] != null) {
					retorno.setValorOriginalConta((BigDecimal) dadosEmpresaCobrancaConta[1]);
				}
				
				// Indicador Pagamento Válido
				if (dadosEmpresaCobrancaConta[2] != null) {
					retorno.setIndicadorPagamentoValido((Short) dadosEmpresaCobrancaConta[2]);
				}
				// imovel
				if(dadosEmpresaCobrancaConta[3] != null) {
					Imovel imovel = new Imovel();
					imovel.setId((Integer) dadosEmpresaCobrancaConta[3]);
					retorno.setImovel(imovel);
				}
				
			}
			
			if (empresaCobranca != null) {
				
				if (empresaCobranca.getPercentualContratoCobranca() != null) {
					
					retorno.setPercentualEmpresaConta(empresaCobranca.getPercentualContratoCobranca());
					
				} else {
					
					retorno.setPercentualEmpresaConta(this.obterFaixaContasDoImovel(empresaCobranca, quantidadeContas, colecaoEmpresaCobrancaFaixa));
				}
				
			}
			
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setId((Integer) dadosEmpresaCobrancaConta[5]);
			retorno.setSetorComercial(setorComercial);
			
			
			if (dadosEmpresaCobrancaConta[6] != null){
				
				retorno.setQuantidadeVezesAssociadoComandos((Integer) dadosEmpresaCobrancaConta[6]);
			}
			else{
				
				retorno.setQuantidadeVezesAssociadoComandos(0);
			}
			
	
			retorno.setEmpresa(comandoEmpresaCobrancaConta.getEmpresa());
			retorno.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);
			retorno.setUltimaAlteracao(new Date());
			
		} 
		catch (Exception e) {
			throw new ControladorException(	"erro.sistema", e);
		}
		
		
		return retorno;
	}
	
	
	
	private EmpresaCobrancaConta criarEmpresaCobrancaContaComProporcionalidade(
			EmpresaCobrancaContaProporcionalidade empresaCobrancaContaProporcionalidade) throws ControladorException, ErroRepositorioException {
		
		EmpresaCobrancaConta empresaCobrancaConta = new EmpresaCobrancaConta();
		
		empresaCobrancaConta.setContaGeral(empresaCobrancaContaProporcionalidade.getContaGeral());			
				
		empresaCobrancaConta.setAnoMesReferenciaConta(empresaCobrancaContaProporcionalidade.getAnoMesReferenciaConta());
				
		empresaCobrancaConta.setDataEnvioConta(new Date());
		
		empresaCobrancaConta.setValorOriginalConta(empresaCobrancaContaProporcionalidade.getValorOriginalConta());
			
		empresaCobrancaConta.setIndicadorPagamentoValido(empresaCobrancaContaProporcionalidade.getIndicadorPagamentoValido());

		empresaCobrancaConta.setImovel(empresaCobrancaContaProporcionalidade.getImovel());
		
		empresaCobrancaConta.setPercentualEmpresaConta(empresaCobrancaContaProporcionalidade.getPercentualEmpresaConta());
		
		empresaCobrancaConta.setEmpresa(empresaCobrancaContaProporcionalidade.getEmpresa());
		
		empresaCobrancaConta.setUltimaAlteracao(new Date());

		empresaCobrancaConta.setComandoEmpresaCobrancaConta(empresaCobrancaContaProporcionalidade.getComandoEmpresaCobrancaConta());
		
		empresaCobrancaConta.setLocalidade(empresaCobrancaContaProporcionalidade.getImovel().getLocalidade());
		
		return empresaCobrancaConta;
	}
	
	

	/**
	 * @param empresaCobranca
	 * @param quantidadeContas
	 * @param retorno
	 */
	private BigDecimal obterFaixaContasDoImovel(EmpresaCobranca empresaCobranca, Integer quantidadeContas,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException {
		
		BigDecimal percentualFaixa = null;
		
		try{
			
			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				
				for (int i = 0; i < colecaoEmpresaCobrancaFaixa.size(); i++) {
					
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i);
					
					Integer numeroMinimoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa();
					
					Integer numeroMaximoContas = null;
					
					if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
						numeroMaximoContas = ((EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i + 1)).getNumeroMinimoContasFaixa() - 1;
					}
					
					if (quantidadeContas >= numeroMinimoContas
							&& (numeroMaximoContas == null || quantidadeContas <= numeroMaximoContas)) {
						
						percentualFaixa = empresaCobrancaFaixa.getPercentualFaixa();
						break;
					}
				}
			}
		}
		catch (Exception e) {
			throw new ControladorException(	"erro.sistema", e);
		}
		
		return percentualFaixa;
	}
	
	
	private CobrancaSituacaoHistorico criarCobrancaSituacaoHistorico(Integer idImovel,Integer anoMesArrecadacaoInicio,Integer anoMesArrecadacaoFim){
		
		
		CobrancaSituacaoHistorico cobrancaSituacaoHistorico = new CobrancaSituacaoHistorico();

		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		cobrancaSituacaoHistorico.setImovel(imovel);

		CobrancaSituacaoMotivo cobrancaSituacaoMotivo = new CobrancaSituacaoMotivo();
		cobrancaSituacaoMotivo
				.setId(CobrancaSituacaoMotivo.IMOVEIS_ENVIADOS_EMPRESA_TERCEIRIZADA);
		cobrancaSituacaoHistorico
				.setCobrancaSituacaoMotivo(cobrancaSituacaoMotivo);

		CobrancaSituacaoTipo cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
		cobrancaSituacaoTipo
				.setId(CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA);
		cobrancaSituacaoHistorico.setCobrancaSituacaoTipo(cobrancaSituacaoTipo);

		cobrancaSituacaoHistorico.setUltimaAlteracao(new Date());

		cobrancaSituacaoHistorico
				.setAnoMesCobrancaSituacaoInicio(anoMesArrecadacaoInicio);
		cobrancaSituacaoHistorico
				.setAnoMesCobrancaSituacaoFim(anoMesArrecadacaoFim);

		cobrancaSituacaoHistorico.setUsuario(Usuario.USUARIO_BATCH);
		cobrancaSituacaoHistorico.setUsuarioInforma(Usuario.USUARIO_BATCH);

		
		
		
	
		return cobrancaSituacaoHistorico;
	}
	

	private ImovelCobrancaSituacao criarImovelCobrancaSituacao(
			Integer idImovel, ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta,
			CobrancaSituacao cobrancaSituacao) throws ControladorException, ErroRepositorioException{

		ImovelCobrancaSituacao imovelCobrancaSituacao = new ImovelCobrancaSituacao();

		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		imovelCobrancaSituacao.setImovel(imovel);

		imovelCobrancaSituacao.setDataImplantacaoCobranca(new Date());
		imovelCobrancaSituacao.setUltimaAlteracao(new Date());

		imovelCobrancaSituacao.setCobrancaSituacao(cobrancaSituacao);

		Cliente cliente = getControladorImovel().pesquisarClienteUsuarioImovel(
				imovelCobrancaSituacao.getImovel().getId());
		if(cliente == null){
			System.out.println("******************* idImovel: "+idImovel);
		}
		imovelCobrancaSituacao.setCliente(cliente);

		
		
				
		return imovelCobrancaSituacao;
	}
	
	/**
	 * [UC0870] Gerar Movimento de Contas em Cobrança por Empresa
	 *
	 * @author Rômulo Aurélio
	 * @date 27/04/2012
	 * 
	 * [SB0002] Verificar Registros ja Inseridos
	 */
	
	private void verificarRegistrosInseridos(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta)
			throws ControladorException {
		
		try {
			
			// 1.1
			@SuppressWarnings("rawtypes")
			Collection colecaoImoveis = getControladorCobranca().obterImovelEmComandoNaoFinalizado(comandoEmpresaCobrancaConta.getId());
	
			FiltroUsuario filtroUsuario = new FiltroUsuario();										
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, Usuario.USUARIO_BATCH.getId()));								
			
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);				
			
			@SuppressWarnings("rawtypes")
			Collection colecaoUsuario =  this.getControladorUtil().pesquisar(filtroUsuario,  Usuario.class.getName());		
			
			Usuario usuarioBatch = (Usuario)  Util.retonarObjetoDeColecao(colecaoUsuario);	
			
			
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
	
				@SuppressWarnings("rawtypes")
				Iterator itImoveis = colecaoImoveis.iterator();
	
				while (itImoveis.hasNext()) {
					Integer idImovel = (Integer) itImoveis.next();
	
					// 1.1.1 Para cada um dos imóveis da coleção o sistema deverá
					// retirar a situação de cobrança indicada no comando
					// que está sendo encerrado (CBST_ID da tabela
					// CMD_EMPR_COBR_CONTA com CECC_ID = CECC_ID do comando
					// selecionado).
					// <<Inclui>> [SB0002 - UC0490 - Informar Situação de Cobrança],
					// passando a situação de cobrança informada (CBST_ID).
					this.getControladorImovel().retirarSituacaoCobrancaImovel(idImovel, CobrancaSituacao.EMPRESA_DE_COBRANCA_COMPESA);
				}
			}
			
			// 1.2
			Collection<Object[]> dadosOrdensServico = repositorioCobranca.pesquisarOrdensServicoAtivasComando(comandoEmpresaCobrancaConta.getId());
	
			if (dadosOrdensServico != null && !dadosOrdensServico.isEmpty()) {
	
				@SuppressWarnings("rawtypes")
				Iterator iteratorOS = dadosOrdensServico.iterator();
	
				while (iteratorOS.hasNext()) {
	
					Object[] dadosOS = (Object[]) iteratorOS.next();
					Integer idOS = (Integer) dadosOS[0];
					// 1.2.1. Para cada uma das ordens de serviços selecionadas que
					// ainda não esteja encerrada,
					// o sistema deverá encerrar a ordem de serviço (ORSE_CDSITUACAO
					// da tabela ORDEM_SERVICO)
					// <<Inclui>> UC0457 Encerrar Ordem de Serviço, passando o
					// motivo de encerramento CANCELAMENTO PELA COMPESA (AMEN_ID).
					this.getControladorOrdemServico().encerrarOSSemExecucao(idOS, new Date(), usuarioBatch, 
						AtendimentoMotivoEncerramento.CANCELAMENTO_PELA_COMPESA.toString(), null, 
						null, null, null, null, null, null, null);
	
				}
			}
			
			//REMOVENDO TABELA AUXILIAR DOS IMÓVEIS PARCIAL
			getControladorCobranca().removerComandoEmpresaCobrancaContaImovelParcial(comandoEmpresaCobrancaConta.getId());
			
			//REMOVENDO TABELA AUXILIAR DOS IMÓVEIS QUE ESTÃO EM COBRANÇA COM COMANDO EM ABERTO
			getControladorCobranca().removerImovelEmpresaCobrancaComandoAberto();
			
			//REMOVENDO TABELA AUXILIAR DOS IMÓVEIS
			getControladorCobranca().removerComandoEmpresaCobrancaContaImovel(comandoEmpresaCobrancaConta.getId());
			
			//REMOVENDO TABELA AUXILIAR DAS CONTAS
			getControladorCobranca().removerComandoEmpresaCobrancaContaConta(comandoEmpresaCobrancaConta.getId());
			
			//1.3.	O sistema deverá remover os registros no comando de cobrança referenciado 
			//(ECPR_ID da tabela COBRANCA.EMP_COB_CONTA_PROP com CECC_ID = CECC_ID do comando selecionado);
			getControladorCobranca().removerContasEmpresaCobrancaContaProporcionalidade(comandoEmpresaCobrancaConta.getId());
			
			//1.3.	O sistema deverá remover os registros no comando de cobrança referenciado 
			//(ECCO_ID da tabela COBRANCA.EMPRESA_COBRANCA_CONTA com CECC_ID = CECC_ID do comando selecionado);
			getControladorCobranca().removerContasEmpresaCobrancaConta(comandoEmpresaCobrancaConta.getId());
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
        }
	}
	
	/**
	 * Retorna o valor do ControladorOrdemServico
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @return O valor de ControladorOrdemServico
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;
		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
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
		
	
	private void gerarDadosEmpresaCobrancaSemProporcionalidade(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta,
			EmpresaCobranca empresaCobranca, SistemaParametro sistemaParametros,
			Integer anoMesArrecadacaoInicio, Integer anoMesArrecadacaoFim,
			Collection<Integer> idsImoveisAtualizar, Map<Integer, Integer> imovelOS,
			@SuppressWarnings("rawtypes") Collection collCobrancaSituacaoHistorico, Collection<ImovelCobrancaSituacao> collImovelCobrancaSituacao,
			ServicoTipo servicoTipo, ComandoEmpresaCobrancaContaHelper helper, List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException {

		/*if (idsImoveis != null && !idsImoveis.isEmpty()) {
		
		Iterator<Integer> iteratorImovel = idsImoveis.iterator();

		System.out.println("Cobrança por Resultado - Comando: " + comandoEmpresaCobrancaConta.getId()
				+ " Quantidade de Imóveis do Comando: " + comandoEmpresaCobrancaConta.getId() + ": "
				+ idsImoveis.size());
		
		//SELECIONANDO A SITUAÇÃO DA COBRANÇA QUE SERÁ UTILIZADA NA GERAÇÃO DA TABELA EMPRESA_COBRANCA_CONTA
		Integer idCobSituacao = repositorioCobranca.pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
		
		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		cobrancaSituacao.setId(idCobSituacao);

		// para cada imóvel pesquisado
		while (iteratorImovel.hasNext()) {
			
			Integer idImovelPesquisado = (Integer) iteratorImovel.next();

			Collection<EmpresaCobrancaConta> colecaoEmpresaCobrancaConta = new ArrayList<EmpresaCobrancaConta>();
			@SuppressWarnings("rawtypes")
			Collection listaIdImovel = new ArrayList<Integer>();
			listaIdImovel.add(idImovelPesquisado);

			Collection<Object[]> colecaoDadosEmpresaCobrancaConta = 
					repositorioCobranca.pesquisarContasInformarContasEmCobranca(comandoEmpresaCobrancaConta, listaIdImovel, sistemaParametros, helper);

			if (colecaoDadosEmpresaCobrancaConta != null && !colecaoDadosEmpresaCobrancaConta.isEmpty()) {

				// para cada registro de conta do imóvel
				for (Object[] dadosEmpresaCobrancaConta : colecaoDadosEmpresaCobrancaConta) {

					if ((!percentualInformado && dadosEmpresaCobrancaConta != null
							&& dadosEmpresaCobrancaConta[2] != null && ((Short) dadosEmpresaCobrancaConta[2]).equals(ConstantesSistema.NAO))
							|| idsContas.contains((Integer) dadosEmpresaCobrancaConta[0])) {
						continue;
					}
					if (colecaoDadosEmpresaCobrancaConta.size() < 2) {
						System.out.println("-----------------------");
						System.out.println("Imovel: " + dadosEmpresaCobrancaConta[3]);
						System.out.println("Quantidade contas: " + colecaoDadosEmpresaCobrancaConta.size());
						System.out.println("-----------------------");
					}
					EmpresaCobrancaConta empresaCobrancaConta = criarEmpresaCobrancaContaSemProporcionalidade(dadosEmpresaCobrancaConta, comandoEmpresaCobrancaConta, empresaCobranca, colecaoDadosEmpresaCobrancaConta.size());

					idsContas.add(empresaCobrancaConta.getContaGeral().getId());

					if (dadosEmpresaCobrancaConta != null) {
						// imovel
						if (dadosEmpresaCobrancaConta[3] != null) {
							Integer idImovel = (Integer) dadosEmpresaCobrancaConta[3];
							if (!idsImoveisAtualizar.contains(idImovel)) {
								CobrancaSituacaoHistorico cobrancaSituacaoHistorico = criarCobrancaSituacaoHistorico(idImovel, anoMesArrecadacaoInicio, anoMesArrecadacaoFim);
								collCobrancaSituacaoHistorico.add(cobrancaSituacaoHistorico);

								ImovelCobrancaSituacao imovelCobrancaSituacao = criarImovelCobrancaSituacao(idImovel, comandoEmpresaCobrancaConta, cobrancaSituacao);
								collImovelCobrancaSituacao.add(imovelCobrancaSituacao);

								idsImoveisAtualizar.add(empresaCobrancaConta.getImovel().getId());

								// 2.1.19. Caso o comando tenha tipo de
								// serviço informado
								if (servicoTipo != null && servicoTipo.getId() != null) {

									Imovel imovel = new Imovel();
									imovel.setId(new Integer(idImovel));

									if (servicoTipo != null && servicoTipo.getId() != null) {
										UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Fachada.getInstancia().pesquisarUnidadeOrganizacionalPorEmpresa(comandoEmpresaCobrancaConta.getEmpresa().getId());

										OrdemServico ordemServico = new OrdemServico();
										ordemServico.setServicoTipo(servicoTipo);
										ordemServico.setImovel(imovel);
										ordemServico.setUnidadeAtual(unidadeOrganizacional);

										Integer idOS = Fachada.getInstancia().gerarOrdemServico(ordemServico, Usuario.USUARIO_BATCH, Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA);

										imovelOS.put(idImovel, idOS);
									}

								}

							}
						}

					}

					if (imovelOS != null && !imovelOS.isEmpty()
							&& imovelOS.get(empresaCobrancaConta.getImovel().getId()) != null) {
						OrdemServico ordemServico = new OrdemServico();
						ordemServico.setId(imovelOS.get(empresaCobrancaConta.getImovel().getId()));

						empresaCobrancaConta.setOrdemServico(ordemServico);
					}

					colecaoEmpresaCobrancaConta.add(empresaCobrancaConta);

				}

				// insere os registros em empresaCobrancaConta
				this.inserirEmpresaCobrancaConta(colecaoEmpresaCobrancaConta);

				colecaoEmpresaCobrancaConta.clear();
				colecaoEmpresaCobrancaConta = null;

			}
		}

	}*/
		
		
		try{
			
			// GERANDO TABELA AUXILIAR DAS CONTAS
			System.out.println("-----------------------");
			System.out.println("INICIO: GERANDO TABELA AUXILIAR DAS CONTAS - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");
			
			repositorioCobranca.inserirComandoEmpresaCobrancaContaConta(comandoEmpresaCobrancaConta, sistemaParametros, helper);
			
			System.out.println("-----------------------");
			System.out.println("FIM: GERANDO TABELA AUXILIAR DAS CONTAS - Comando: " + comandoEmpresaCobrancaConta.getId());
			System.out.println("-----------------------");
			
			//SELECIONANDO A SITUAÇÃO DA COBRANÇA QUE SERÁ UTILIZADA NA GERAÇÃO DA TABELA EMPRESA_COBRANCA_CONTA
			Integer idCobSituacao = repositorioCobranca.pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
			
			CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
			cobrancaSituacao.setId(idCobSituacao);
			
			Collection<Object[]> colecaoDadosEmpresaCobrancaConta = null;
			Collection<EmpresaCobrancaConta> colecaoEmpresaCobrancaContaINSERT = null;
			
			Integer indice = 0;
			final int qtdRegistros = 100000;
			boolean terminou = false;
			
			while (!terminou) {
				
				colecaoDadosEmpresaCobrancaConta = repositorioCobranca
				.pesquisarComandoEmpresaCobrancaContaConta(comandoEmpresaCobrancaConta, indice, qtdRegistros);
				
				if (colecaoDadosEmpresaCobrancaConta == null || colecaoDadosEmpresaCobrancaConta.size() < qtdRegistros) {
					terminou = true;
				} 
				else {
					indice = indice + 1;
				}
				
				if (colecaoDadosEmpresaCobrancaConta != null && !colecaoDadosEmpresaCobrancaConta.isEmpty()) {
					
					EmpresaCobrancaConta empresaCobrancaConta = null;
					colecaoEmpresaCobrancaContaINSERT = new ArrayList<EmpresaCobrancaConta>();
					
					//PARA CADA CONTA SELECIONADA DO IMÓVEL
					for (Object[] dadosEmpresaCobrancaConta : colecaoDadosEmpresaCobrancaConta) {
						
						//GERANDO EMPRESA COBRANCA CONTA
						empresaCobrancaConta = criarEmpresaCobrancaContaSemProporcionalidade(
									dadosEmpresaCobrancaConta, 
									comandoEmpresaCobrancaConta, 
									empresaCobranca, 
									(Integer) dadosEmpresaCobrancaConta[7],
									colecaoEmpresaCobrancaFaixa);
						
						if (dadosEmpresaCobrancaConta != null) {
							
							// imovel
							if (dadosEmpresaCobrancaConta[3] != null) {
								
								Integer idImovel = (Integer) dadosEmpresaCobrancaConta[3];
								
								if (!idsImoveisAtualizar.contains(idImovel)) {
									
									//GERANADO COBRANCA_SITUACAO_HISTORICO
									CobrancaSituacaoHistorico cobrancaSituacaoHistorico = criarCobrancaSituacaoHistorico(idImovel, anoMesArrecadacaoInicio, anoMesArrecadacaoFim);
									collCobrancaSituacaoHistorico.add(cobrancaSituacaoHistorico);
	
									//GERANDO COBRANCA_SITUACAO
									ImovelCobrancaSituacao imovelCobrancaSituacao = criarImovelCobrancaSituacao(
										idImovel, comandoEmpresaCobrancaConta, cobrancaSituacao);
									
									collImovelCobrancaSituacao.add(imovelCobrancaSituacao);
	
									idsImoveisAtualizar.add(empresaCobrancaConta.getImovel().getId());
	
									// 2.1.19. Caso o comando tenha tipo de serviço informado
									if (servicoTipo != null && servicoTipo.getId() != null) {
	
										if (servicoTipo != null && servicoTipo.getId() != null) {
											UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) this.getControladorUnidade()
													.pesquisarUnidadeOrganizacionalPorEmpresa(comandoEmpresaCobrancaConta.getEmpresa().getId());
	
											OrdemServico ordemServico = new OrdemServico();
											ordemServico.setServicoTipo(servicoTipo);
											ordemServico.setImovel(empresaCobrancaConta.getImovel());
											ordemServico.setUnidadeAtual(unidadeOrganizacional);
	
											Integer idOS = this.getControladorOrdemServico().gerarOrdemServico(ordemServico, Usuario.USUARIO_BATCH, Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA);
	
											imovelOS.put(idImovel, idOS);
										}
									}
								}
							}
						}
	
						if (imovelOS != null && !imovelOS.isEmpty() && 
							imovelOS.get(empresaCobrancaConta.getImovel().getId()) != null) {
							
							OrdemServico ordemServico = new OrdemServico();
							ordemServico.setId(imovelOS.get(empresaCobrancaConta.getImovel().getId()));
	
							empresaCobrancaConta.setOrdemServico(ordemServico);
						}
						
						
						colecaoEmpresaCobrancaContaINSERT.add(empresaCobrancaConta);
						
					}
					
					// insere os registros em empresaCobrancaConta
					this.getControladorCobranca().inserirColecaoEmpresaCobrancaContaSemTransacao(colecaoEmpresaCobrancaContaINSERT);
					
					//LIBERANDO MEMÓRIA
					colecaoEmpresaCobrancaContaINSERT.clear();
					colecaoEmpresaCobrancaContaINSERT = null;	
				}
				
				if (colecaoDadosEmpresaCobrancaConta != null){
					
					colecaoDadosEmpresaCobrancaConta.clear();
					colecaoDadosEmpresaCobrancaConta = null;
				}
			}
		}
		catch (Exception e) {
			throw new ControladorException(	"erro.sistema", e);
		}
	}
	
	
	private EmpresaCobrancaConta criarEmpresaCobrancaContaSemProporcionalidade(Object[] dadosEmpresaCobrancaConta, 
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, 
			EmpresaCobranca empresaCobranca,
			Integer quantidadeContas, List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException, ErroRepositorioException {
		
		EmpresaCobrancaConta retorno = new EmpresaCobrancaConta();
		
		if (dadosEmpresaCobrancaConta != null) {
			
			// Id da Conta
			if (dadosEmpresaCobrancaConta[0] != null) {
				
				ContaGeral contaGeral = new ContaGeral();
				contaGeral.setId((Integer) dadosEmpresaCobrancaConta[0]);
				
				retorno.setContaGeral(contaGeral);
				retorno.setAnoMesReferenciaConta((Integer) dadosEmpresaCobrancaConta[4]);
				
				retorno.setDataEnvioConta(new Date());
				
			}
			
			// Valor Original da Conta
			if (dadosEmpresaCobrancaConta[1] != null) {
				retorno.setValorOriginalConta((BigDecimal) dadosEmpresaCobrancaConta[1]);
			}
			
			// Indicador Pagamento Válido
			if (dadosEmpresaCobrancaConta[2] != null) {
				retorno.setIndicadorPagamentoValido((Short) dadosEmpresaCobrancaConta[2]);
			}
			// imovel
			if(dadosEmpresaCobrancaConta[3] != null) {
				Imovel imovel = new Imovel();
				imovel.setId((Integer) dadosEmpresaCobrancaConta[3]);
				retorno.setImovel(imovel);
			}
			
			//Localidade do imóvel
			if(dadosEmpresaCobrancaConta[8] != null) {
				Localidade localidade = new Localidade();
				localidade.setId((Integer)dadosEmpresaCobrancaConta[8]);
				retorno.setLocalidade(localidade);
			}
		}
		
		if (empresaCobranca != null) {
			
			if (empresaCobranca.getPercentualContratoCobranca() != null) {
				
				retorno.setPercentualEmpresaConta(empresaCobranca.getPercentualContratoCobranca());
				
			} else {
				
				retorno.setPercentualEmpresaConta(this.obterFaixaContasDoImovel(empresaCobranca, quantidadeContas, colecaoEmpresaCobrancaFaixa));
			}
			
		}
		
		retorno.setEmpresa(comandoEmpresaCobrancaConta.getEmpresa());
		retorno.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);
		retorno.setUltimaAlteracao(new Date());
		
		return retorno;
	}
	
	/**
	 * Retorna a interface remota de ControladorUnidade
	 * 
	 * @return A interface remota do controlador unidade
	 */
	private ControladorUnidadeLocal getControladorUnidade() {
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
		
}
