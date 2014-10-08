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
package gcom.cadastro;

import gcom.atendimentopublico.bean.DadosContratoPPPHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.LogradouroAdmin;
import gcom.cadastro.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarRoteiroDispositivoMovelHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaSituacao;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioTipoServicoHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.CollectionUtil;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author Administrador
 */
public class RepositorioCadastroHBM implements IRepositorioCadastro {

	private static IRepositorioCadastro instancia;

	/**
	 * Construtor da classe RepositorioFaturamentoHBM
	 */
	private RepositorioCadastroHBM() {
	}

	/**
	 * Retorna o valor de instance
	 * 
	 * @return O valor de instance
	 */
	public static IRepositorioCadastro getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioCadastroHBM();
		}
		return instancia;
	}

	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina, Short indicadorPerene) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (!tipoFeriado.equals(ConstantesSistema.SIM)) {

				consulta = " select 2 as tipoFeriado, mfer.mfer_id as id, mfer.mfer_dsferiado as descricao, "
						+ " muni.muni_nmmunicipio as descricaoMunicipio, mfer.mfer_dtferiado as data"
						+ " from cadastro.municipio_feriado mfer"
						+ " inner join cadastro.municipio muni on(mfer.muni_id = muni.muni_id)";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (idMunicipio != null && !idMunicipio.equals(""))
						|| (indicadorPerene != null && !indicadorPerene.equals(ConstantesSistema.TODOS))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(mfer.mfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "mfer.mfer_dtferiado between :dataInicio and :dataFim and ";
					}

					if (idMunicipio != null && !idMunicipio.equals("")) {
						consulta += "mfer.muni_id = " + idMunicipio + " and ";
					}
					
					if(!indicadorPerene.equals(ConstantesSistema.TODOS)){
						consulta += "mfer.mfer_icperene = " + indicadorPerene + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			if (tipoFeriado.equals(ConstantesSistema.TODOS)) {
				consulta += "union all";
			}

			if (!tipoFeriado.equals(ConstantesSistema.NAO)) {
				consulta += " select 1 as tipoFeriado, nfer_id as id, nfer_dsferiado as descricao,"
						+ " '' as descricaoMunicipio, nfer_dtferiado as data"
						+ " from cadastro.nacional_feriado ";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio.equals(""))
						|| (indicadorPerene != null && !indicadorPerene.equals(ConstantesSistema.TODOS))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(nfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";

					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "nfer_dtferiado between :dataInicio and :dataFim and ";
					}
					
					if(!indicadorPerene.equals(ConstantesSistema.TODOS)){
						consulta += "nfer_icperene = " + indicadorPerene + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			consulta = consulta + "order by data";

			if (dataFeriadoInicio != null && !dataFeriadoInicio.equals("")) {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setDate("dataInicio", dataFeriadoInicio).setDate(
								"dataFim", dataFeriadoFim).setFirstResult(
								10 * numeroPagina).setMaxResults(10).list();
			} else {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setFirstResult(10 * numeroPagina).setMaxResults(10)
						.list();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	
	public Collection pesquisarFeriadoTotal(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina, Short indicadorPerene) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (!tipoFeriado.equals(ConstantesSistema.SIM)) {

				consulta = " select 2 as tipoFeriado, mfer.mfer_id as id, mfer.mfer_dsferiado as descricao, "
						+ " muni.muni_nmmunicipio as descricaoMunicipio, mfer.mfer_dtferiado as data"
						+ " from cadastro.municipio_feriado mfer"
						+ " inner join cadastro.municipio muni on(mfer.muni_id = muni.muni_id)";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (idMunicipio != null && !idMunicipio.equals(""))
						|| (indicadorPerene != null && !indicadorPerene.equals(ConstantesSistema.TODOS))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(mfer.mfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "mfer.mfer_dtferiado between :dataInicio and :dataFim and ";
					}

					if (idMunicipio != null && !idMunicipio.equals("")) {
						consulta += "mfer.muni_id = " + idMunicipio + " and ";
					}
					
					if(!indicadorPerene.equals(ConstantesSistema.TODOS)){
						consulta += "mfer.mfer_icperene = " + indicadorPerene + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			if (tipoFeriado.equals(ConstantesSistema.TODOS)) {
				consulta += "union all";
			}

			if (!tipoFeriado.equals(ConstantesSistema.NAO)) {
				consulta += " select 1 as tipoFeriado, nfer_id as id, nfer_dsferiado as descricao,"
						+ " '' as descricaoMunicipio, nfer_dtferiado as data"
						+ " from cadastro.nacional_feriado ";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio.equals(""))
						|| (indicadorPerene != null && !indicadorPerene.equals(ConstantesSistema.TODOS))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(nfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";

					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "nfer_dtferiado between :dataInicio and :dataFim and ";
					}
					
					if(!indicadorPerene.equals(ConstantesSistema.TODOS)){
						consulta += "nfer_icperene = " + indicadorPerene + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			consulta = consulta + "order by data";

			if (dataFeriadoInicio != null && !dataFeriadoInicio.equals("")) {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setDate("dataInicio", dataFeriadoInicio).setDate(
								"dataFim", dataFeriadoFim).setFirstResult(
								10 * numeroPagina).list();
			} else {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setFirstResult(10 * numeroPagina).list();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio, Short indicadorPerene)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (!tipoFeriado.equals(ConstantesSistema.SIM)) {

				consulta = " select count(mfer.mfer_id) as id"
						+ " from cadastro.municipio_feriado mfer"
						+ " inner join cadastro.municipio muni on(mfer.muni_id = muni.muni_id)";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (idMunicipio != null && !idMunicipio.equals(""))
						|| (indicadorPerene != null && !indicadorPerene.equals(ConstantesSistema.TODOS))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(mfer.mfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "mfer.mfer_dtferiado between :dataInicio and :dataFim and ";
					}

					if (idMunicipio != null && !idMunicipio.equals("")) {
						consulta += "mfer.muni_id = " + idMunicipio + " and ";
					}
					
					if(!indicadorPerene.equals(ConstantesSistema.TODOS)){
						consulta += "mfer_icperene = " + indicadorPerene + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			if (tipoFeriado.equals(ConstantesSistema.TODOS)) {
				consulta += "union all";
			}

			if (!tipoFeriado.equals(ConstantesSistema.NAO)) {
				consulta += " select count(nfer_id) as id"
						+ " from cadastro.nacional_feriado ";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (indicadorPerene != null && !indicadorPerene.equals(ConstantesSistema.TODOS))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(nfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "nfer_dtferiado between :dataInicio and :dataFim and ";
					}
					
					if(!indicadorPerene.equals(ConstantesSistema.TODOS)){
						consulta += "nfer_icperene = " + indicadorPerene + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}
			
			Collection valores = null;
			if (dataFeriadoInicio != null && !dataFeriadoInicio.equals("")) {
				valores = (Collection) session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER).setDate(
								"dataInicio", dataFeriadoInicio).setDate(
								"dataFim", dataFeriadoFim).list();
			} else {
				valores = (Collection) session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER).list();
			}

			Integer valor = 0;
			Iterator iteratorValor = valores.iterator();
			while (iteratorValor.hasNext()) {
				valor = valor + (Integer) iteratorValor.next();
			}

			retorno = valor;

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Faz um Update na base
	 * 
	 * @author Kassia Albuquerque e Ana Maria
	 * @date 06/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarMensagemSistema(String mensagemSistema)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "update SistemaParametro sp "
				+ "set sp.mensagemSistema =:mensagemSistema, sp.ultimaAlteracao = :dataAtual ";

		try {

			session.createQuery(consulta).setString("mensagemSistema",
					mensagemSistema).setTimestamp("dataAtual", new Date())
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ErroRepositorioException {

		EnvioEmail retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select envioEmail " + "from EnvioEmail envioEmail "
					+ "where envioEmail.id = :idEnvioEmail";

			retorno = (EnvioEmail) session.createQuery(consulta).setInteger(
					"idEnvioEmail", idEnvioEmail).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
			throws ErroRepositorioException {

		DadosEnvioEmailHelper retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select new gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper(ipServidorSmtp, dsEmailResponsavel, nomeAbreviadoEmpresa) "
					+ "from SistemaParametro sistemaParametro ";

			retorno = (DadosEnvioEmailHelper) session.createQuery(consulta)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar todos ids dos setores comerciais.
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial()
			throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select stcm.id from SetorComercial stcm  ";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Migração dos dados do município de Ribeirão - O sistema gerar as tabelas
	 * cliente, cliente_endereço, imovel, cliente_imovel, imovel_subcategoria,
	 * ligacao_agua a parti da tabela Cadastro_ribeirao;
	 * 
	 * @author Ana Maria
	 * 
	 * @throws ControladorException
	 */

	public Object[] pesquisarSetorQuadra(Integer idLocalidade)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select stcm.id, qdra.id " + "from Quadra qdra "
					+ "inner join qdra.setorComercial stcm "
					+ "where stcm.localidade.id = :idLocalidade";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarCEP() throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select cep.cepId " + "from Cep cep "
					+ "where cep.municipio like 'RIBEIRAO'";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarBairro() throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select bair.id " + "from Bairro bair "
					+ "where bair.municipio.id = 1180";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarLogradouroBairro(Integer codigoLogradouro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logB.id " + "from LogradouroBairro logB "
					+ "where logB.logradouro.id = :codigoLogradouro";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"codigoLogradouro", codigoLogradouro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarLogradouroCep(Integer codigoLogradouro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logC.id " + "from LogradouroCep logC "
					+ "where logC.logradouro.id = :codigoLogradouro";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"codigoLogradouro", codigoLogradouro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void inserirClienteEndereco(Integer idCliente,
			String numeroImovelMenor, String numeroImovelMaior, Integer idCep,
			Integer idBairro, Integer idLograd, Integer idLogradBairro,
			Integer idLogradCep) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;
		String sequence = Util
				.obterNextValSequence("cadastro.seq_cliente_endereco");
		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into cadastro.cliente_endereco(cled_id, clie_id, edtp_id, "
					+ "edrf_id, cled_nnimovel, cled_dscomplementoendereco, "
					+ "cep_id, bair_id, cled_icenderecocorrespondencia, "
					+ "cled_tmultimaalteracao, logr_id, lgbr_id, lgcp_id) "
					+ "values ( "
					+ sequence
					+ ", "
					+ idCliente
					+ ", 1, 1, "
					+ numeroImovelMenor
					+ ", "
					+ numeroImovelMaior
					+ ", "
					+ idCep
					+ ", "
					+ idBairro
					+ ", 1, "
					+ Util.obterSQLDataAtual()
					+ " , "
					+ idLograd
					+ ", "
					+ idLogradBairro + ", " + idLogradCep + ")";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public void inserirClienteImovel(Integer idCliente, Integer idImovel,
			String data) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
			String sequence = Util
					.obterNextValSequence("cadastro.seq_cliente_imovel");
			insert = "insert into cadastro.cliente_imovel(clim_id, "
					+ "clie_id, imov_id, clim_dtrelacaoinicio, "
					+ "clim_tmultimaalteracao, "
					+ "crtp_id, clim_icnomeconta) " + "values ( " + sequence
					+ ", " + idCliente + ", " + idImovel + ", " + data + ", "
					+ Util.obterSQLDataAtual() + " , " + "2, " + "1)";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public void inserirImovelSubcategoria(Integer idImovel,
			Integer idSubcategoria) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into cadastro.imovel_subcategoria(imov_id, scat_id, "
					+ "imsb_qteconomia, imsb_tmultimaalteracao) "
					+ "values ( "
					+ idImovel
					+ ", "
					+ idSubcategoria
					+ ", "
					+ "1, "
					+ Util.obterSQLDataAtual() + " )";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public void inserirLigacaoAgua(Integer idImovel, String dataBD)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into atendimentopublico.ligacao_agua(lagu_id, lagu_dtimplantacao, lagu_dtligacaoagua, "
					+ "lagu_icemissaocortesupressao, lagd_id, lagm_id, lapf_id, lagu_tmultimaalteracao) "
					+ "values ( "
					+ idImovel
					+ ", "
					+ dataBD
					+ ", "
					+ dataBD
					+ ", 1, 2, 1, 1," + Util.obterSQLDataAtual() + " )";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public Collection pesquisarCadastroRibeiraop()
			throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select crp " + "from CadastroRibeiraop crp "
					+ "where crp.imovel.id is null " + "order by crp.codigo";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void atualizarImovelRibeirao(Integer idImovel, Integer codigo)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizarValorExcedente;

		try {

			atualizarValorExcedente = "UPDATE CadastroRibeiraop "
					+ "SET imov_id = :idImovel " + "WHERE codigo = :codigo ";

			session.createQuery(atualizarValorExcedente).setInteger("idImovel",
					idImovel).setInteger("codigo", codigo).executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação [UC0251]
	 * Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de Ação de
	 * Cobrança para os Imóveis do Cliente
	 * 
	 * @author Sávio Luiz
	 * @created 23/11/2007
	 * 
	 * @param cliente
	 * @param relacaoClienteImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClientesSubordinados(Integer idCliente)

	throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection retorno = null;

		// Query
		String consulta;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			consulta = "select ci.id " + "from Cliente ci "
					+ "where ci.cliente.id = :idCliente";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * [UC0624] Gerar Relatório para Atualização Cadastral
	 */

	public Collection pesquisarRelatorioAtualizacaoCadastral(
			Collection idLocalidades, Collection idSetores,
			Collection idQuadras, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select imovel.id, "// 0
					+ " cliente.nome, "// 1
					+ " localidade.id, "// 2
					+ " localidade.descricao, "// 3
					+ " setorComercial.codigo, "// 4
					+ " setorComercial.descricao, "// 5
					+ " unidadeNegocio.nome,"// 6
					+ " imovel.quantidadeEconomias, "// 7
					+ " rota.codigo,"// 8
					+ " imovel.numeroSequencialRota, "// 9
					+ " imovel.indicadorExclusao,"// 10
					+ " unidadeNegocio.id "// 11
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota "
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " where relacaoTipo.id =:idRelacaoTipo ";

			if (idLocalidades != null && !idLocalidades.isEmpty()) {
				consulta = consulta + " and localidade.id in (";
				Iterator iterator = idLocalidades.iterator();
				while (iterator.hasNext()) {
					Localidade localidade = (Localidade) iterator.next();
					consulta = consulta + localidade.getId().toString() + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (idSetores != null && !idSetores.isEmpty()) {
				consulta = consulta
						+ " and setorComercial.codigo in (:setores)";
				Iterator iterator = idSetores.iterator();
				while (iterator.hasNext()) {
					SetorComercial setorComercial = (SetorComercial) iterator
							.next();
					consulta = consulta + setorComercial.getId().toString()
							+ ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (idQuadras != null && !idQuadras.isEmpty()) {
				consulta = consulta + " and quadra.numeroQuadra in (:quadras)";
				Iterator iterator = idQuadras.iterator();
				while (iterator.hasNext()) {
					Quadra quadra = (Quadra) iterator.next();
					consulta = consulta + quadra.getId().toString() + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (rotaInicial != null && !rotaInicial.trim().equals("")
					&& rotaFinal != null && !rotaFinal.trim().equals("")) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null
					&& !sequencialRotaInicial.trim().equals("")
					&& sequencialRotaFinal != null
					&& !sequencialRotaFinal.trim().equals("")) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			retorno = session.createQuery(consulta).setInteger("idRelacaoTipo",
					ClienteRelacaoTipo.USUARIO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 03/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoSituacaoLigacaoEsgoto = filtro
				.getSituacaoLigacaoEsgoto();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id " // 18
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua) "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoEsgoto != null
					&& !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				consulta = consulta
						+ " and ligacaoEsgotoSituacao.id in (:situacaoEsgoto) ";
				parameters.put("situacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and (localidade.id >= "
						+ localidadeInicial + " and localidade.id <= "
						+ localidadeFinal + ")";
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and (setorComercial.codigo >= "
						+ setorComercialInicial
						+ " and setorComercial.codigo <= "
						+ setorComercialFinal + ")";
			}

			if (rotaInicial != null) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Rafael Pinto
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = 0;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoSituacaoLigacaoEsgoto = filtro
				.getSituacaoLigacaoEsgoto();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) " // 0
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua) "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoEsgoto != null
					&& !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				consulta = consulta
						+ " and ligacaoEsgotoSituacao.id in (:situacaoEsgoto) ";
				parameters.put("situacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and (localidade.id >= "
						+ localidadeInicial + " and localidade.id <= "
						+ localidadeFinal + ")";
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and (setorComercial.codigo >= "
						+ setorComercialInicial
						+ " and setorComercial.codigo <= "
						+ setorComercialFinal + ")";
			}

			if (rotaInicial != null) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtraso
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Integer quantidadeFaturasAtrasoInicial = filtro
				.getQuantidadeFaturasAtrasoInicial();
		Integer quantidadeFaturasAtrasoFinal = filtro
				.getQuantidadeFaturasAtrasoFinal();

		Float valorFaturasAtrasoInicial = filtro.getValorFaturasAtrasoInicial();
		Float valorFaturasAtrasoFinal = filtro.getValorFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

		Integer situacaoCobranca = filtro.getSituacaoCobranca();
		
		String hidrometro = filtro.getHidrometro();

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			Map<String, Object> parameters = new HashMap<String, Object>();

			String consulta = " select \n"
					+
					// 0
					"   gr.id, \n"
					+
					// 1
					"   gr.nome, \n"
					+
					// 2
					"   un.id, \n"
					+
					// 3
					"   un.nome, \n"
					+
					// 4
					"   sc.codigo, \n"
					+
					// 5
					"   sc.descricao, \n"
					+
					// 6
					"   loc.id, \n"
					+
					// 7
					"   loc.descricao, \n"
					+
					// 8
					"   cli.nome, \n"
					+
					// 9
					"   las.descricaoAbreviado, \n"
					+
					// 10
					"   rot.codigo, \n"
					+
					// 11
					"   imo.numeroSequencialRota, \n"
					+
					// 12
					"   imo.id, \n"
					+
					// 13
					"   les.id, \n"
					+
					// 14
					"   les.descricaoAbreviado, \n"
					+
					// 15
					"   qua.numeroQuadra, \n"
					+
					// 16
					"   min( c.referencia ) as referenciaMinima, \n"
					+
					// 17
					"   count(*) as quatidadeContas, \n"
					+
					// 18
					"   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as total, \n"
					+
					// 19
					"   max( c.referencia ) as referenciaMaxima, \n" +
					// 20
					"   imo.lote, \n" +
					// 21
					"   imo.subLote, \n" +
					// 22
					"   cli.cpf, \n" +
					// 23
					"   cli.cnpj \n" +

					" from \n" + "   Conta c, ClienteImovel ci  " + categoria
					+ " \n" + "   inner join c.imovel imo \n"
					+ "   inner join imo.localidade loc \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join loc.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "	  inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join qua.rota rot \n"
					+ "   inner join ci.cliente cli \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   imo.id = ci.imovel.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " and \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";

				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";

				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			//Consulta com o Hidrômetro
			if (hidrometro != null && !hidrometro.equals("0")) {
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";

				// consulta += " and imo.cobrancaSituacao.id = :situacaoCobranca
				// ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by \n" + "   gr.id, \n" + "   gr.nome, \n"
					+ "   un.id, \n" + "   un.nome, \n" + "   sc.codigo, \n"
					+ "   sc.descricao, \n" + "   loc.id, \n"
					+ "   loc.descricao, \n" + "   cli.nome, \n"
					+ "   cli.cpf, \n" + "   cli.cnpj, \n"
					+ "   las.descricaoAbreviado, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota, \n" + "   imo.id, \n"
					+ "   les.id, \n" + "   les.descricaoAbreviado, \n"
					+ "   qua.numeroQuadra, \n" + "   imo.lote, \n"
					+ "   imo.subLote \n";

			if (valorFaturasAtrasoInicial != null
					|| quantidadeFaturasAtrasoInicial != null) {
				consulta += " having ";

				if (quantidadeFaturasAtrasoInicial != null) {
					consulta += "  count(*) between :quantidadeFaturasAtrasoInicial and :quantidadeFaturasAtrasoFinal";

					parameters.put("quantidadeFaturasAtrasoInicial",
							quantidadeFaturasAtrasoInicial);
					parameters.put("quantidadeFaturasAtrasoFinal",
							quantidadeFaturasAtrasoFinal);
				}

				if (valorFaturasAtrasoInicial != null) {
					if (quantidadeFaturasAtrasoInicial != null) {
						consulta += " and ";
					}

					consulta += " sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) between :valorFaturasAtrasoInicial and :valorFaturasAtrasoFinal";

					parameters.put("valorFaturasAtrasoInicial",
							valorFaturasAtrasoInicial);
					parameters.put("valorFaturasAtrasoFinal",
							valorFaturasAtrasoFinal);
				}
			}

			consulta += " order by \n" + "   gr.id, \n" + "   un.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   rot.codigo, \n" + "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			Integer referenciaImoveisFaturasAtrasoInicial = filtro
					.getReferenciaFaturasAtrasoInicial();
			Integer referenciaImoveisFaturasAtrasoFinal = filtro
					.getReferenciaFaturasAtrasoFinal();

			Integer quantidadeFaturasAtrasoInicial = filtro
					.getQuantidadeFaturasAtrasoInicial();
			Integer quantidadeFaturasAtrasoFinal = filtro
					.getQuantidadeFaturasAtrasoFinal();

			Float valorFaturasAtrasoInicial = filtro
					.getValorFaturasAtrasoInicial();
			Float valorFaturasAtrasoFinal = filtro.getValorFaturasAtrasoFinal();

			Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
					.getSituacaoLigacaoAgua();
			Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
			Collection<Integer> colecaoCategorias = filtro.getCategorias();
			Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

			Integer situacaoCobranca = filtro.getSituacaoCobranca();
			
			String hidrometro = filtro.getHidrometro();

			Integer clienteSuperior = filtro.getClienteSuperior();
			Integer cliente = filtro.getCliente();
			Integer tipoRelacao = filtro.getTipoRelacao();
			Integer responsavel = filtro.getResponsavel();

			String clienteConta = "";
			if (cliente != null && responsavel != null
					&& !responsavel.equals(1)) {
				clienteConta = " , ClienteConta clienteConta ";
			}

			String categoria = "";
			if (!Util.isVazioOrNulo(colecaoCategorias)) {
				categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
			}

			String consulta = "";
			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta = " select \n"
					+
					// 1
					"   cli.id, \n"
					+
					// 2
					"   cli.nome as  cliente, \n"
					+
					// 3
					"   gr.id, \n"
					+
					// 4
					"   loc.id, \n"
					+
					// 5
					"   sc.codigo, \n"
					+
					// 6
					"   qua.numeroQuadra, \n"
					+
					// 7
					"   las.descricaoAbreviado, \n"
					+
					// 8
					"   min( c.referencia ) as referenciaMinima, \n"
					+
					// 9
					"   count(*) as quatidadeContas, \n"
					+
					// 10
					"   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as totalSemEncargos, \n"
					+
					// 11
					"   rot.codigo, \n" +
					// 12
					"   imo.numeroSequencialRota, \n" +
					// 13
					"   imo.id, \n" +
					// 14
					"   les.descricaoAbreviado, \n" +
					// 15
					"   max( c.referencia ) as referenciaMaxima \n" +

					" from Cliente cli " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "	  inner join imo.ligacaoAgua la \n"	
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join imo.localidade loc \n"
					+ "   inner join qua.rota rot \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo.id = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2 ) ";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id ";
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";

				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			// Consulta com o Hidrômetro
			if (hidrometro != null && !hidrometro.equals("0")) {
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca) ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by  cli.id,cli.nome,gr.id,loc.id,sc.codigo,"
					+ "qua.numeroQuadra,las.descricaoAbreviado,rot.codigo,"
					+ "imo.numeroSequencialRota,imo.id,les.descricaoAbreviado ";

			if (valorFaturasAtrasoInicial != null
					|| quantidadeFaturasAtrasoInicial != null) {
				consulta += " having ";

				if (quantidadeFaturasAtrasoInicial != null) {
					consulta += "  count(*) between :quantidadeFaturasAtrasoInicial and :quantidadeFaturasAtrasoFinal";

					parameters.put("quantidadeFaturasAtrasoInicial",
							quantidadeFaturasAtrasoInicial);
					parameters.put("quantidadeFaturasAtrasoFinal",
							quantidadeFaturasAtrasoFinal);
				}

				if (valorFaturasAtrasoInicial != null) {
					if (quantidadeFaturasAtrasoInicial != null) {
						consulta += " and ";
					}

					consulta += " sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) between :valorFaturasAtrasoInicial and :valorFaturasAtrasoFinal";

					parameters.put("valorFaturasAtrasoInicial",
							valorFaturasAtrasoInicial);
					parameters.put("valorFaturasAtrasoFinal",
							valorFaturasAtrasoFinal);
				}
			}

			consulta += " order by \n" + "   cli.id, \n" + "   gr.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Bruno Barros
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer qtd1 = filtro.getQuantidadeFaturasAtrasoInicial();
		Integer qtd2 = filtro.getQuantidadeFaturasAtrasoFinal();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer situacaoCobranca = filtro.getSituacaoCobranca();

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			Map<String, Object> parameters = new HashMap<String, Object>();

			String consulta = " select "
					+ "   imo.id, "
					+ "   count(*) as quatidadeContas "
					+ " from "
					+ "   Conta c "
					+ "   inner join c.imovel imo "
					+ "   inner join imo.localidade loc "
					+ "   inner join loc.gerenciaRegional gr "
					+ "   inner join loc.unidadeNegocio un "
					+ "   inner join imo.setorComercial sc "
					+ "   inner join imo.ligacaoAguaSituacao las "
					+ "   inner join imo.ligacaoEsgotoSituacao les "
					+ "   inner join imo.quadra.rota rot, "
					+ "   ClienteImovel ci  "
					+ categoria
					+ "   inner join ci.cliente cli "
					+ "   inner join cli.clienteTipo cltp "
					+ " where "
					+ "   imo.id = ci.imovel.id and "
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " and "
					+ "   ci.dataFimRelacao is null and "
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and "
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) ";

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";

				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (colecaoEsferasPoder != null) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca)";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by imo.id " +

			" having " + "   count(*) between :qtd1 and :qtd2 ";

			parameters.put("qtd1", qtd1);
			parameters.put("qtd2", qtd2);

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list().size();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer qtd1 = filtro.getQuantidadeFaturasAtrasoInicial();
		Integer qtd2 = filtro.getQuantidadeFaturasAtrasoFinal();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer situacaoCobranca = filtro.getSituacaoCobranca();

		Integer clienteSuperior = filtro.getClienteSuperior();
		Integer cliente = filtro.getCliente();
		Integer tipoRelacao = filtro.getTipoRelacao();
		Integer responsavel = filtro.getResponsavel();

		String clienteConta = "";
		if (cliente != null && responsavel != null && !responsavel.equals(1)) {
			clienteConta = " , ClienteConta clienteConta ";
		}

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		String consulta = "";
		Map<String, Object> parameters = new HashMap<String, Object>();

		try {
			consulta = " select " + "   imo.id, "
					+ "   count(*) as quatidadeContas " + " from "
					+ "   Cliente cli  " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where "
					+ "   ci.dataFimRelacao is null and "
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and "
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) ";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2) ";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id ";
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {

					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (colecaoEsferasPoder != null) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca) ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by imo.id " +

			" having " + "   count(*) between :qtd1 and :qtd2 ";

			parameters.put("qtd1", qtd1);
			parameters.put("qtd2", qtd2);

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list().size();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros, Raphael Rossiter
	 * @date 17/12/2007, 11/06/2008
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();

		Collection<Integer> colecaoPerfisImovel = filtro.getColecaoPerfisImovel();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		Integer indicadorMedicaoComHidrometro = filtro
				.getIndicadorMedicaoComHidrometro();
		
		Integer anoMesReferencia = filtro
				.getAnoMesReferencia();

		String consulta = "";
		/*
		 * Query query = null; Map parameters = new HashMap();
		 */

		try {
			consulta = "select greg.greg_id as gerencia, " + // 0
					"greg.greg_nmregional as nomeGerencia, " + // 1
					"uneg.uneg_id as unidadeNegocio, " + // 2
					"uneg.uneg_nmunidadenegocio as nomeUnidadeNegocio, " + // 3
					"loca.loca_id as localidade, " + // 4
					"loca.loca_nmlocalidade as nomeLocalidade," + // 5
					"stcm.stcm_cdsetorcomercial as codigoSetor, " + // 6
					"stcm.stcm_nmsetorcomercial as nomeSetor, " + // 7
					"clie.clie_nmcliente as nomeCliente, " + // 8
					"last.last_dsligacaoaguasituacao as ligacaoAguaSituacao, " + // 9
					"consumoAgua.cshi_nnconsumomedio as consumoAgua, " + // 10
					"rota.rota_cdrota as codigoRota, " + // 11
					"imov.imov_nnsequencialrota as sequencialRota, " + // 12
					"imov.imov_id as imovel, " + // 13
					"lest.lest_dsligacaoesgotosituacao as ligacaoEsgotoSituacao, "
					+ // 14
					"consumoEsgoto.cshi_nnconsumomedio as consumoEsgoto, " + // 15
					"imov.imov_nnlote as lote, " + // 16
					"imov.imov_nnsublote as subLote, " + // 17
					"qdra.qdra_nnquadra as numeroQuadra "; // 18

			consulta += "from cadastro.cliente_imovel clim "
					+

					"inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "inner join atendimentopublico.ligacao_agua lagu on imov.imov_id=lagu.lagu_id "
					+

					"left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					// CLIENTE USUÁRIO
					"and (crtp.crtp_id = 2 ) "
					+

					// AGUA
					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesReferencia ) "
					+

					// ESGOTO
					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesReferencia ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				String clausulaIn = "";
				Iterator perfis = colecaoPerfisImovel.iterator();

				while (perfis.hasNext()) {
					clausulaIn += String.valueOf(perfis.next());
					if (perfis.hasNext()) {
						clausulaIn += ",";
					}
				}

				consulta += " and imov.iper_id in ( " + clausulaIn + " )";
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 1) {
				consulta += " and lagu.hidi_id is not null ";
			} else if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 2) {
				consulta += " and lagu.hidi_id is null ";
			}

			// Ordenamos
			consulta += " order by greg.greg_id, uneg.uneg_id, loca.loca_id, stcm.stcm_cdsetorcomercial, "
					+ " rota.rota_cdrota, imov.imov_nnsequencialrota ";

			retorno = session.createSQLQuery(consulta).addScalar("gerencia",
					Hibernate.INTEGER).addScalar("nomeGerencia",
					Hibernate.STRING).addScalar("unidadeNegocio",
					Hibernate.INTEGER).addScalar("nomeUnidadeNegocio",
					Hibernate.STRING).addScalar("localidade", Hibernate.INTEGER)
						.addScalar("nomeLocalidade", Hibernate.STRING)
						.addScalar("codigoSetor", Hibernate.INTEGER)
						.addScalar("nomeSetor", Hibernate.STRING)
						.addScalar("nomeCliente", Hibernate.STRING)
						.addScalar("ligacaoAguaSituacao", Hibernate.STRING)
						.addScalar("consumoAgua", Hibernate.INTEGER)
						.addScalar("codigoRota", Hibernate.SHORT)
						.addScalar("sequencialRota", Hibernate.INTEGER)
						.addScalar("imovel", Hibernate.INTEGER)
						.addScalar("ligacaoEsgotoSituacao", Hibernate.STRING)
						.addScalar("consumoEsgoto", Hibernate.INTEGER)
						.addScalar( "lote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT)
						.addScalar("numeroQuadra", Hibernate.INTEGER)
						.setInteger("anoMesReferencia", anoMesReferencia.intValue())
						.list();

			/*
			 * query = session.createQuery(consulta);
			 * 
			 * Set set = parameters.keySet(); Iterator iterMap = set.iterator();
			 * while (iterMap.hasNext()) { String key = (String) iterMap.next();
			 * if (parameters.get(key) instanceof Collection) { Collection
			 * collection = (ArrayList) parameters.get(key);
			 * query.setParameterList(key, collection); } else {
			 * query.setParameter(key, parameters.get(key)); }
			 *  }
			 * 
			 * retorno = query.list();
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio Pesquisa a
	 * quantidade de imoveis para o relatorio de imoveis por consumo medio
	 * 
	 * @author Bruno Barros, Hugo Leonardo
	 * @data 17/12/2007, 12/07/2010
	 * 
	 * @param filtro
	 * @param anoMesFaturamento
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();
		
		Collection<Integer> colecaoPerfisImovel = filtro.getColecaoPerfisImovel();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		Integer indicadorMedicaoComHidrometro = filtro
				.getIndicadorMedicaoComHidrometro();
		
		Integer anoMesReferencia = filtro.getAnoMesReferencia();

		String consulta = "";
		// Map parameters = new HashMap();

		try {
			consulta = "select count(clim.clim_id) as clienteImo "; // 18

			consulta += "from cadastro.cliente_imovel clim "
					+ "inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "inner join atendimentopublico.ligacao_agua lagu on imov.imov_id=lagu.lagu_id "
					+

					"left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					// CLIENTE USUÁRIO
					"and (crtp.crtp_id = 2 ) "
					+

					// AGUA
					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesReferencia ) "
					+

					// ESGOTO
					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesReferencia ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";
			

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}
			
			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {

				Iterator perfis = colecaoPerfisImovel.iterator();

				String perfisImovel = "";
				
				while (perfis.hasNext()) {
					perfisImovel += perfis.next();
					if (perfis.hasNext()) {
						perfisImovel += ", ";
					}
				}
				
				consulta += " and imov.iper_id in ( " + perfisImovel + " )";
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 1) {
				consulta += " and lagu.hidi_id is not null ";
			} else if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 2) {
				consulta += " and lagu.hidi_id is null ";
			}

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"clienteImo", Hibernate.INTEGER).setInteger(
					"anoMesReferencia", anoMesReferencia.intValue())
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 18/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfil = filtro.getPerfilImovel();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			
			consulta = "select imov.imov_id as idImovel, "//0
			  + " greg.greg_id as idGerenciaRegional, "//1
			  + " greg.greg_nmregional as nomeGerenciaRegional, "//2
			  + " uneg.uneg_id as idUnidadeNegocio, "//3
			  + " uneg.uneg_nmunidadenegocio as nomeUnidadeNegocio, "//4
			  + " loca.loca_id as idLocalidade, "//5
			  + " loca.loca_nmlocalidade as nomeLocalidade, "//6
			  + " stcm.stcm_cdsetorcomercial as codigoSetorComercial, "//7
			  + " stcm.stcm_nmsetorcomercial as nomeSetorComercial, "//8
			  + " qdra.qdra_nnquadra as numeroQuadra, "//9
			  + " clie.clie_nmcliente as nomeCliente, "//10
			  + " lastt.last_dsligacaoaguasituacao as descricaoLigacaoAguaSituacao, "//11
			  + " lestt.lest_dsligacaoesgotosituacao as descricaoLigacaoEsgotoSituacao, "//12
			  + " rota.rota_cdrota as codigoRota, "//13
			  + " imov.imov_nnsequencialrota as numeroSequencialRota, "//14
			  + " imov.imov_nnlote as numeroLote, "//15
			  + " imov.imov_nnsublote as numeroSublote, "//16
			  + " stcm.stcm_id as idSetorComercial, "//17
			  + " rota.rota_id as idRota, "//18
			  + " imov.imov_qteconomia as quantidadeEconomias, "//19
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento1) as numeroConsumoFaturadoMes1, "//20
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento2) as numeroConsumoFaturadoMes2, "//21
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento3) as numeroConsumoFaturadoMes3, "//22
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento4)  as numeroConsumoFaturadoMes4, "//23
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento5) as numeroConsumoFaturadoMes5, "//24
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento6) as numeroConsumoFaturadoMes6, "//25
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento7) as numeroConsumoFaturadoMes7, "//26
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento8) as numeroConsumoFaturadoMes8, "//27
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento9) as numeroConsumoFaturadoMes9, "//28
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento10) as numeroConsumoFaturadoMes10, "//29
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento11) as numeroConsumoFaturadoMes11, "//30
			  + " (select consumoHistorico.cshi_nnconsumofaturadomes "
			  + " from micromedicao.consumo_historico consumoHistorico " 
			  + " left join micromedicao.consumo_anormalidade consAnormalidade on consAnormalidade.csan_id = consumoHistorico.csan_id "
			  + " where consumoHistorico.imov_id = imov.imov_id "
			  + " and consumoHistorico.lgti_id = :ligTipo "
			  + " and consumoHistorico.cshi_amfaturamento = :anoMesFaturamento12) as numeroConsumoFaturadoMes12, "//31
			  + " imov.imov_idsubcategoriaprincipal as idSubcategoriaPrincipal " //32
			  + " from cadastro.imovel imov "
			  + " INNER JOIN cadastro.cliente_imovel clim on clim.imov_id = imov.imov_id "
			  + " INNER JOIN cadastro.imovel_subcategoria iscat on iscat.imov_id = imov.imov_id "
			  + " INNER JOIN cadastro.cliente clie ON clim.clie_id=clie.clie_id "
			  + " INNER JOIN cadastro.cliente_relacao_tipo crtp ON clim.crtp_id=crtp.crtp_id "
			  + " INNER JOIN cadastro.localidade loca ON imov.loca_id=loca.loca_id "
			  + " INNER JOIN cadastro.unidade_negocio uneg ON loca.uneg_id=uneg.uneg_id "
			  + " INNER JOIN cadastro.gerencia_regional greg ON loca.greg_id=greg.greg_id "
			  + " INNER JOIN cadastro.imovel_perfil iper ON imov.iper_id=iper.iper_id "
			  + " INNER JOIN cadastro.setor_comercial stcm ON imov.stcm_id=stcm.stcm_id "
			  + " INNER JOIN cadastro.quadra qdra ON imov.qdra_id=qdra.qdra_id "
			  + " INNER JOIN micromedicao.rota rota ON qdra.rota_id = rota.rota_id "
			  + " INNER JOIN atendimentopublico.ligacao_agua_situacao lastt ON imov.last_id=lastt.last_id "

			  + " LEFT JOIN atendimentopublico.ligacao_esgoto_situacao lestt ON imov.lest_id=lestt.lest_id "
			  + " LEFT JOIN cadastro.subcategoria scat ON iscat.scat_id=scat.scat_id "
			  + " LEFT JOIN cadastro.categoria catg ON scat.catg_id=catg.catg_id "
			  + " WHERE crtp.crtp_id             = :idRelacaoTipo "
			  + " AND (clim.clim_dtrelacaofim IS NULL) ";

			 
			
			
			parameters.put("anoMesFaturamento1", filtro.getAnoMesSubtraido1());
			parameters.put("anoMesFaturamento2", filtro.getAnoMesSubtraido2());
			parameters.put("anoMesFaturamento3", filtro.getAnoMesSubtraido3());
			parameters.put("anoMesFaturamento4", filtro.getAnoMesSubtraido4());
			parameters.put("anoMesFaturamento5", filtro.getAnoMesSubtraido5());
			parameters.put("anoMesFaturamento6", filtro.getAnoMesSubtraido6());
			parameters.put("anoMesFaturamento7", filtro.getAnoMesSubtraido7());
			parameters.put("anoMesFaturamento8", filtro.getAnoMesSubtraido8());
			parameters.put("anoMesFaturamento9", filtro.getAnoMesSubtraido9());
			parameters.put("anoMesFaturamento10", filtro.getAnoMesSubtraido10());
			parameters.put("anoMesFaturamento11", filtro.getAnoMesSubtraido11());
			parameters.put("anoMesFaturamento12", filtro.getAnoMesSubtraido12());
			parameters.put("ligTipo", LigacaoTipo.LIGACAO_AGUA);
			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);
			
			
			if (colecaoSituacaoLigacaoAgua != null && colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta + " and lastt.last_id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}
			

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta + " and catg.catg_id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (colecaoPerfil != null && colecaoPerfil.size() > 0){
				consulta = consulta + " and iper.iper_id in (:colecaoPerfil) ";
				parameters.put("colecaoPerfil", colecaoPerfil);
			}
			
			if (unidadeNegocio != null) {
				consulta = consulta + " and uneg.uneg_id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta + " and greg.greg_id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and loca.loca_id between " + localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and stcm.stcm_cdsetorcomercial between "+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.rota_cdrota between " + rotaInicial + " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta + " and imov.imov_nnsequencialrota between " + sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta+= " ORDER BY greg.greg_id, "
						  + " uneg.uneg_id, "
						  + " loca.loca_id, "
						  + " stcm.stcm_cdsetorcomercial, "
						  + " qdra.qdra_nnquadra, "
						  + " imov.imov_nnlote, "
						  + " imov.imov_nnsublote, "
						  + " rota.rota_cdrota, "
						  + " imov.imov_nnsequencialrota ";
			

			query = session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("idGerenciaRegional", Hibernate.INTEGER)
					.addScalar("nomeGerenciaRegional", Hibernate.STRING)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("nomeUnidadeNegocio", Hibernate.STRING)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("codigoSetorComercial", Hibernate.INTEGER)
					.addScalar("nomeSetorComercial", Hibernate.STRING)
					.addScalar("numeroQuadra", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("descricaoLigacaoAguaSituacao", Hibernate.STRING)
					.addScalar("descricaoLigacaoEsgotoSituacao", Hibernate.STRING)
					.addScalar("codigoRota", Hibernate.SHORT)
					.addScalar("numeroSequencialRota", Hibernate.INTEGER)
					.addScalar("numeroLote", Hibernate.SHORT)
					.addScalar("numeroSublote", Hibernate.SHORT)
					.addScalar("idSetorComercial", Hibernate.INTEGER)
					.addScalar("idRota", Hibernate.INTEGER)
					.addScalar("quantidadeEconomias", Hibernate.SHORT)
					.addScalar("numeroConsumoFaturadoMes1", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes2", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes3", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes4", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes5", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes6", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes7", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes8", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes9", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes10", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes11", Hibernate.STRING)
					.addScalar("numeroConsumoFaturadoMes12", Hibernate.STRING)
					.addScalar("idSubcategoriaPrincipal", Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 18/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfilImovel = filtro.getPerfilImovel();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) "
					+ " from ClienteImovel clienteImovel,"
					+ " ImovelSubcategoria imovelSubcateg"
					+ " left join imovelSubcateg.comp_id.subcategoria subcateg"
					+ " left join subcateg.categoria categ"
					+ " left join imovelSubcateg.comp_id.imovel imovelCateg"
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join imovel.imovelPerfil perfil "
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where imovelCateg.id = clienteImovel.imovel.id "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAguaSituacao.id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta + " and categ.id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}
			
			if (colecaoPerfilImovel != null && colecaoPerfilImovel.size() > 0) {
				consulta = consulta + " and perfil.id in (:colecaoPerfilImovel) ";
				parameters.put("colecaoPerfilImovel", colecaoPerfilImovel);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id " // 18
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " inner join imovel.ligacaoAgua ligacaoAgua"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua1,:situacaoAgua2) "
					+ " and ligacaoAgua.hidrometroInstalacaoHistorico is null"
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null "
					+ " and imovel.indicadorExclusao <> :indicadorExclusao";

			parameters.put("situacaoAgua1", LigacaoAguaSituacao.LIGADO);
			parameters.put("situacaoAgua2", LigacaoAguaSituacao.CORTADO);
			parameters.put("indicadorExclusao", Imovel.IMOVEL_EXCLUIDO);

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) "
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua1,:situacaoAgua2) "
					+ " and imovel.hidrometroInstalacaoHistorico is null"
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null "
					+ " and imovel.indicadorExclusao <> :indicadorExclusao";

			parameters.put("situacaoAgua1", LigacaoAguaSituacao.LIGADO);
			parameters.put("situacaoAgua2", LigacaoAguaSituacao.CORTADO);
			parameters.put("indicadorExclusao", Imovel.IMOVEL_EXCLUIDO);

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param
	 * FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer referenciaDiaInicial = filtro.getReferenciaFaturasDiaInicial();
		Integer referenciaDiaFinal = filtro.getReferenciaFaturasDiaFinal();

		Integer referenciaAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "select "
					+ "imoveis_em_dia.imov_id as idImovel, " // 0
					+ "gerencia.greg_id as idGerencia, " // 1
					+ "gerencia.greg_nmregional as nomeGerencia, " // 2
					+ "unidade.uneg_id as idUnidade, " // 3
					+ "unidade.uneg_nmunidadenegocio as nomeUnidade, " // 4
					+ "local.loca_id as idLocalidade, " // 5
					+ "local.loca_nmlocalidade as nomeLocalidade, " // 6
					+ "setor.stcm_cdsetorcomercial as codigoSetor, "// 7
					+ "setor.stcm_nmsetorcomercial as nomeSetor, " // 8
					+ "qua.qdra_nnquadra as numeroQuadra, " // 9
					+ "cli.clie_nmcliente as nomeCliente, " // 10
					+ "ligacaoAgua.last_dsligacaoaguasituacao as situacaoAgua, " // 11
					+ "ligacaoEsgoto.lest_dsligacaoesgotosituacao as situacaoEsgoto, " // 12
					+ "rot.rota_cdrota as codigoRota, " // 13
					+ "imov.imov_nnsequencialrota as sequenciaRota, " // 14
					+ "imov.imov_nnlote as numeroLote, " // 15
					+ "imov.imov_nnsublote as numeroSubLote, " // 16
					+ "setor.stcm_id as idSetor, " // 17
					+ "rot.rota_id as idRota, " // 18
					+ "imov.imov_qteconomia as qtdEconomia "// 19
					+ "from "
					+ "( select i.imov_id from "
					+ "cadastro.imovel i "
					+ "where not exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaDiaInicial
					+ " and "
					+ referenciaDiaFinal
					+ " and "
					+ "contaAtual.imov_id = i.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "
					+ ") imoveis_em_dia "

					+ "inner join cadastro.imovel imov on imov.imov_id = imoveis_em_dia.imov_id "
					+ "inner join cadastro.cliente_imovel cliImovel on cliImovel.imov_id = imov.imov_id "
					+ "inner join cadastro.cliente cli on cli.clie_id = cliImovel.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo relacaoTipo on relacaoTipo.crtp_id = cliImovel.crtp_id "
					+ "inner join cadastro.localidade local on local.loca_id = imov.loca_id "
					+ "inner join cadastro.setor_comercial setor on setor.stcm_id = imov.stcm_id "
					+ "inner join cadastro.quadra qua on qua.qdra_id = imov.qdra_id "
					+ "inner join micromedicao.rota rot on rot.rota_id = qua.rota_id "
					+ "inner join cadastro.unidade_negocio unidade on unidade.uneg_id = local.uneg_id "
					+ "inner join cadastro.gerencia_regional gerencia on gerencia.greg_id = local.greg_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoAgua on ligacaoAgua.last_id = imov.last_id "
					+ "left join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgoto on ligacaoEsgoto.lest_id = imov.lest_id ";

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ "inner join cadastro.imovel_subcategoria imovelSubCat on imovelSubCat.imov_id = imov.imov_id "
						+ "inner join cadastro.subcategoria subCat on subCat.scat_id = imovelSubCat.scat_id ";
			}

			consulta = consulta
					+ "where exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaAtrasoInicial
					+ " and "
					+ referenciaAtrasoFinal
					+ " and "
					+ "contaAtual.imov_id = imoveis_em_dia.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "

					+ "and relacaoTipo.crtp_id = :idRelacaoTipo "
					+ "and cliImovel.clim_dtrelacaofim is null ";

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAgua.last_id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ " and subCat.catg_id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidade.uneg_id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta + " and gerencia.greg_id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and local.loca_id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta
						+ " and setor.stcm_cdsetorcomercial between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rot.rota_cdrota between "
						+ rotaInicial + " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by idGerencia,idUnidade,idLocalidade,"
					+ "codigoSetor,numeroQuadra,numeroLote,numeroSubLote,"
					+ "codigoRota,sequenciaRota";

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("nomeGerencia",
					Hibernate.STRING).addScalar("idUnidade", Hibernate.INTEGER)
					.addScalar("nomeUnidade", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"codigoSetor", Hibernate.INTEGER).addScalar(
							"nomeSetor", Hibernate.STRING).addScalar(
							"numeroQuadra", Hibernate.INTEGER).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar(
							"situacaoAgua", Hibernate.STRING).addScalar(
							"situacaoEsgoto", Hibernate.STRING).addScalar(
							"codigoRota", Hibernate.SHORT).addScalar(
							"sequenciaRota", Hibernate.INTEGER).addScalar(
							"numeroLote", Hibernate.SHORT).addScalar(
							"numeroSubLote", Hibernate.SHORT).addScalar(
							"idSetor", Hibernate.INTEGER).addScalar("idRota",
							Hibernate.INTEGER).addScalar("qtdEconomia",
							Hibernate.SHORT);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param
	 * FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer referenciaDiaInicial = filtro.getReferenciaFaturasDiaInicial();
		Integer referenciaDiaFinal = filtro.getReferenciaFaturasDiaFinal();

		Integer referenciaAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "select count(*) as quantidade "
					+ "from "
					+ "( select i.imov_id from "
					+ "cadastro.imovel i "
					+ "where exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaDiaInicial
					+ " and "
					+ referenciaDiaFinal
					+ " and "
					+ "contaAtual.imov_id = i.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "
					+ ") as imoveis_em_dia "

					+ "inner join cadastro.imovel imov on imov.imov_id = imoveis_em_dia.imov_id "
					+ "inner join cadastro.cliente_imovel cliImovel on cliImovel.imov_id = imov.imov_id "
					+ "inner join cadastro.cliente cli on cli.clie_id = cliImovel.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo relacaoTipo on relacaoTipo.crtp_id = cliImovel.crtp_id "
					+ "inner join cadastro.localidade local on local.loca_id = imov.loca_id "
					+ "inner join cadastro.setor_comercial setor on setor.stcm_id = imov.stcm_id "
					+ "inner join cadastro.quadra qua on qua.qdra_id = imov.qdra_id "
					+ "inner join micromedicao.rota rot on rot.rota_id = qua.rota_id "
					+ "inner join cadastro.unidade_negocio unidade on unidade.uneg_id = local.uneg_id "
					+ "inner join cadastro.gerencia_regional gerencia on gerencia.greg_id = local.greg_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoAgua on ligacaoAgua.last_id = imov.last_id "
					+ "left join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgoto on ligacaoEsgoto.lest_id = imov.lest_id ";

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ "inner join cadastro.imovel_subcategoria imovelSubCat on imovelSubCat.imov_id = imov.imov_id "
						+ "inner join cadastro.subcategoria subCat on subCat.scat_id = imovelSubCat.scat_id ";
			}

			consulta = consulta
					+ "where not exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaAtrasoInicial
					+ " and "
					+ referenciaAtrasoFinal
					+ " and "
					+ "contaAtual.imov_id = imoveis_em_dia.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "

					+ "and relacaoTipo.crtp_id = :idRelacaoTipo "
					+ "and cliImovel.clim_dtrelacaofim is null ";

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAgua.last_id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ " and subCat.catg_id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidade.uneg_id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta + " and gerencia.greg_id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and local.loca_id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta
						+ " and setor.stcm_cdsetorcomercial between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rot.rota_cdrota between "
						+ rotaInicial + " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createSQLQuery(consulta).addScalar("quantidade",
					Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisInicial = filtro.getReferenciaInicial();
		Integer referenciaImoveisFinal = filtro.getReferenciaFinal();

		Collection<Integer> colecaoTiposConsumo = filtro.getTiposConsumo();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select \n" +
			// Gerencia Regional id - Indice 0
					"   gr.id, \n" +
					// Gerencia Regional nome - Indice 1
					"   gr.nome, \n" +
					// Unidade Negócio id - Indice 2
					"   un.id, \n" +
					// Unidade Negócio nome - Indice 3
					"   un.nome, \n" +
					// Localidade id - Indice 4
					"   loca.id, \n" +
					// Localidade descricao - Indice 5
					"   loca.descricao, \n" +
					// Setor Comercial codigo - Indice 6
					"   sc.codigo, \n" +
					// Setor Comercial descricao - Indice 7
					"   sc.descricao, \n" +
					// Imovel id - Indice 8
					"   imo.id, \n" +
					// Cliente nome- Indice 9
					"   cl.nome, \n" +
					// Ligacao Agua Situacao Descricao - Indice 10
					"   las.descricao, \n" +
					// Consumo Tipo descricao - Indice 11
					"   ct.descricao, \n" +
					// Rota codigo - Indice 12
					"   rt.codigo, \n" +
					// Numero do Sequencial da Rota - Indice 13
					"   imo.numeroSequencialRota, \n" +
					// Ligacao Esgoto Situacao - Indice 14
					"   les.descricao, \n" +
					// Numero da Quadra - Indice 15
					"   qua.numeroQuadra, \n" +
					// Lote - Indice 16
					"   imo.lote, \n" +
					// Sublote - Indice 17
					" 	imo.subLote, \n" +
					// Referencia - Indice 18
					"   ch.referenciaFaturamento \n" + " from \n"
					+ "   ConsumoHistorico ch \n"
					+ "   inner join ch.imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join ch.consumoTipo ct \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les, \n"
					+ "   ClienteImovel ci \n"
					+ "   inner join ci.cliente cl \n" + " where \n"
					+ "   ci.imovel.id = imo.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO + " and \n"
					+ "   ci.dataFimRelacao is null \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (referenciaImoveisInicial != null) {
				consulta += " and ch.referenciaFaturamento between :amInicial and :amFinal \n";

				parameters.put("amInicial", referenciaImoveisInicial);
				parameters.put("amFinal", referenciaImoveisFinal);
			}

			if (colecaoTiposConsumo != null && colecaoTiposConsumo.size() > 0) {
				consulta += " and ch.consumoTipo.id in (:tiposConsumo) \n";
				parameters.put("tiposConsumo", colecaoTiposConsumo);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.codigo, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota, \n "
					+ "   imo.id";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 * 
	 * A pesquisa retorna uma colecao de Imoveis para que a partir daí comece a
	 * geracao das linhas TXTs.
	 * 
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 * 
	 * @param ArquivoTextoDadosCadastraisHelper
	 * 
	 * @return Collection<Imovel>
	 * @throws ControladorException
	 */
	public Collection<Imovel> pesquisarImovelArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper objeto)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = objeto.getLocalidadeInicial();
		Integer localidadeFinal = objeto.getLocalidadeFinal();

		Integer setorComercialInicial = objeto.getSetorComercialInicial();
		Integer setorComercialFinal = objeto.getSetorComercialFinal();

		Integer rotaInicial = objeto.getRotaInicial();
		Integer rotaFinal = objeto.getRotaFinal();

		Integer sequencialRotaInicial = objeto.getSequencialRotalInicial();
		Integer sequencialRotaFinal = objeto.getSequencialRotalFinal();

		Integer unidadeNegocio = objeto.getUnidadeNegocio();
		Integer gerencia = objeto.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select imo " + " from \n" + "   Imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   left join imo.ligacaoEsgoto le \n"
					+ "   left join le.ligacaoEsgotoPerfil le \n" + " where \n"
					+ "   imo.indicadorExclusao = 2 \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.id, \n " + "   imo.id, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN
	 * 
	 * @author Tiago Moreno
	 * @date 10/04/2008
	 * 
	 * @param ArquivoTextoLigacoesHidrometroHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */

	public Collection<HidrometroInstalacaoHistorico> pesquisarImovelArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper objeto)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = objeto.getLocalidadeInicial();
		Integer localidadeFinal = objeto.getLocalidadeFinal();

		Integer setorComercialInicial = objeto.getSetorComercialInicial();
		Integer setorComercialFinal = objeto.getSetorComercialFinal();

		Integer rotaInicial = objeto.getRotaInicial();
		Integer rotaFinal = objeto.getRotaFinal();

		Integer sequencialRotaInicial = objeto.getSequencialRotalInicial();
		Integer sequencialRotaFinal = objeto.getSequencialRotalFinal();

		Integer unidadeNegocio = objeto.getUnidadeNegocio();
		Integer gerencia = objeto.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select iih " + " from \n"
					+ "   HidrometroInstalacaoHistorico iih , Imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   left join fetch iih.hidrometro hd \n"
					+ "   left join fetch iih.hidrometroLocalInstalacao hli \n"
					+ "   left join fetch hd.hidrometroCapacidade hc \n"
					+ " where \n" + "   imo.indicadorExclusao = 2 \n"
					+ "   and las.id in (3,5) and iih.dataRetirada is null \n"
					+ "   and iih.ligacaoAgua.id = imo.id \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.id, \n " + "   imo.id, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisInicial = filtro.getReferenciaInicial();
		Integer referenciaImoveisFinal = filtro.getReferenciaFinal();

		Collection<Integer> colecaoTiposConsumo = filtro.getTiposConsumo();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select \n" + "   count(*) \n" + " from \n"
					+ "   ConsumoHistorico ch \n"
					+ "   inner join ch.imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join ch.consumoTipo ct \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les, \n"
					+ "   ClienteImovel ci \n"
					+ "   inner join ci.cliente cl \n" + " where \n"
					+ "   ci.imovel.id = imo.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO + " and \n"
					+ "   ci.dataFimRelacao is null \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.id between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.id between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
			}

			if (referenciaImoveisInicial != null) {
				consulta += " and ch.referenciaFaturamento between :amInicial and :amFinal \n";

				parameters.put("amInicial", referenciaImoveisInicial);
				parameters.put("amFinal", referenciaImoveisFinal);
			}

			if (colecaoTiposConsumo != null && colecaoTiposConsumo.size() > 0) {
				consulta += " and ch.consumoTipo.id in (:tiposConsumo) \n";
				parameters.put("tiposConsumo", colecaoTiposConsumo);
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Pesquisa o id localidade,codigo setor e codigo da rota apartir do id da
	 * rota
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 02/06/2008
	 * 
	 * @throws ErroRepositorioException
	 * @return Object[3] onde :
	 * 
	 * Object[0]=id localidade Object[1]=codigo do setor Object[2]=codigo da
	 * rota
	 */
	public Object[] pesquisarDadosRotaEntregaContaPorRota(Integer idRota)
			throws ErroRepositorioException {

		Object[] retorno = null;

		String consulta = "";

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT local.id," + "setor.codigo," + "rot.codigo "
					+ "FROM Rota rot "
					+ "LEFT JOIN FETCH rot.setorComercial setor "
					+ "LEFT JOIN FETCH setor.localidade local "
					+ "WHERE rot.id = :idRota ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRota", idRota).setMaxResults(1).uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna
		return retorno;
	}

	/**
	 * [UC0330] Inserir Mensagem da Conta
	 * 
	 * [SB0001] Pesquisar Setor Comercial
	 * 
	 * @author Raphael Rossiter
	 * @date 25/06/2008
	 * 
	 * @param tipoArgumento
	 * @param indiceInicial
	 * @param indiceFinal
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSetorComercialPorQualidadeAgua(
			int tipoArgumento, BigDecimal indiceInicial,
			BigDecimal indiceFinal, Integer anoMesReferencia)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {

			consulta = "SELECT DISTINCT(loca.id), loca.descricao, stcm "
					+ "FROM QualidadeAgua qlag "
					+ "LEFT JOIN qlag.localidade loca "
					+ "LEFT JOIN qlag.setorComercial stcm "
					+ "WHERE qlag.anoMesReferencia = :anoMesReferencia ";

			switch (tipoArgumento) {

			case ConstantesSistema.TURBIDEZ:

				consulta += "AND qlag.numeroIndiceTurbidez BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.CLORO:

				consulta += "AND qlag.numeroCloroResidual BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.PH:

				consulta += "AND qlag.numeroIndicePh BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COR:

				consulta += "AND qlag.numeroIndiceCor BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.FLUOR:

				consulta += "AND qlag.numeroIndiceFluor BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.FERRO:

				consulta += "AND qlag.numeroIndiceFerro BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COLIFORMES_TOTAIS:

				consulta += "AND qlag.numeroIndiceColiformesTotais BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COLIFORMES_FECAIS:

				consulta += "AND qlag.numeroIndiceColiformesFecais BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			default:

				consulta += "AND qlag.numeroNitrato BETWEEN :indiceInicial AND :indiceFinal ";
				break;
			}

			consulta += "ORDER BY loca.descricao, stcm.descricao";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setBigDecimal(
					"indiceInicial", indiceInicial).setBigDecimal(
					"indiceFinal", indiceFinal).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Object[] obterImovelGeracaoTabelasTemporarias(Integer idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retorno = null;

		Query query = null;

		try {

			consulta = "SELECT distinct (imov.imov_id) as idImovel, "
					+ // 0
					" imov.loca_id as idLocalidade, "
					+ // 1
					" stcm.stcm_cdsetorcomercial as codigoSetorComercial, "
					+ // 2
					" qdra.qdra_nnquadra as numeroQuadra, "
					+ // 3
					" imov_nnlote as lote, "
					+ // 4
					" imov_nnsublote as sublote, "
					+ // 5
					" imov_nnsequencialrota as numSequencialRota, "
					+ // 6
					" imov_nnmorador as numMorador, "
					+ // 7
					" lgcp.logr_id as cepLogradouro, "
					+ // 8
					" lgbr.logr_id as bairroLogradouro, "
					+ // 9
					" cep.cep_cdcep as codigoCep,"
					+ // 10
					" bairro.bair_id as idBairro,"
					+ // 11
					" bairro.bair_nmbairro as descricaoBairro,"
					+ // 12
					" imov.edrf_id as enderecoReferencia, "
					+ // 13
					" imov_nnimovel as numImovel, "
					+ // 14
					" imov_dscomplementoendereco as complementoEndereco, "
					+ // 15
					" imov_nnareaconstruida as areaConstruidaFaixa, "
					+ // 16
					" last_id as ligacaoAguaSituacao, "
					+ // 17
					" imov_voreservatorioinferior as volResInferior, "
					+ // 18
					" imov_voreservatoriosuperior as volResSuperior, "
					+ // 19
					" imov_vopiscina as volumePiscina, "
					+ // 20
					" imov_icjardim as indJardim, "
					+ // 21
					" pcal_id as pavimentoCalcada, "
					+ // 22
					" prua_id as pavimentoRua, "
					+ // 23
					" ftab_id as fonteAbastecimento, "
					+ // 24
					" poco_id as pocoTipo, "
					+ // 25
					" imov_nnpontosutilizacao as numPontosUtilizacao, "
					+ // 26
					" lest_id as ligacaoEsgotoSituacao, "
					+ // 27
					" iper_id  as imovelPerfil, "
					+ // 28
					" depj_id as despejo, "
					+ // 29
					" imov_nncoordenadax as coordenadaX, "
					+ // 30
					" imov_nncoordenaday as coordenadaY, "
					+ // 31
					" imov_idimovelprincipal as imovelPrincipal, "
					+ // 32
					" imov_nniptu as numIptu, "
					+ // 33
					" imov_nncontratoenergia as numCelpe, "
					+ // 34
					" acon_id as idAreaConstruidaFaixa, "
					+ // 35
					" poco_id as idPoco, "
					+ // 36
					" imov.lgbr_id as logBairro, "
					+ // 37
					" imov.lgcp_id as logCep " 
					+ // 38
					" from cadastro.imovel imov "
					+ " inner join cadastro.setor_comercial stcm on(imov.stcm_id = stcm.stcm_id)"
					+ " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
					+ " left join cadastro.logradouro_cep lgcp on(imov.lgcp_id = lgcp.lgcp_id)"
					+ " left join cadastro.cep cep on (lgcp.cep_id = cep.cep_id)"
					+ " left join cadastro.logradouro_bairro lgbr on(imov.lgbr_id = lgbr.lgbr_id)"
					+ " left join cadastro.bairro bairro on (lgbr.bair_id = bairro.bair_id)"
					+ " where imov.imov_id =:idImovel";

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("codigoSetorComercial",
					Hibernate.INTEGER).addScalar("numeroQuadra",
					Hibernate.INTEGER).addScalar("lote", 
					Hibernate.INTEGER).addScalar("sublote", 
					Hibernate.INTEGER).addScalar("numSequencialRota", 
					Hibernate.INTEGER).addScalar("numMorador", 
					Hibernate.SHORT).addScalar("cepLogradouro", 
					Hibernate.INTEGER).addScalar("bairroLogradouro", 
					Hibernate.INTEGER).addScalar("codigoCep", 
					Hibernate.INTEGER).addScalar("idBairro", 
					Hibernate.INTEGER).addScalar("descricaoBairro", 
					Hibernate.STRING).addScalar("enderecoReferencia", 
					Hibernate.INTEGER).addScalar("numImovel", 
					Hibernate.STRING).addScalar("complementoEndereco", 
					Hibernate.STRING).addScalar("areaConstruidaFaixa", 
					Hibernate.BIG_DECIMAL).addScalar("ligacaoAguaSituacao", 
					Hibernate.INTEGER).addScalar("volResInferior", 
					Hibernate.BIG_DECIMAL).addScalar("volResSuperior", 
					Hibernate.BIG_DECIMAL).addScalar("volumePiscina", 
					Hibernate.BIG_DECIMAL).addScalar("indJardim", 
					Hibernate.SHORT).addScalar("pavimentoCalcada", 
					Hibernate.INTEGER).addScalar("pavimentoRua", 
					Hibernate.INTEGER).addScalar("fonteAbastecimento", 
					Hibernate.INTEGER).addScalar("pocoTipo", 
					Hibernate.INTEGER).addScalar("numPontosUtilizacao", 
					Hibernate.SHORT).addScalar("ligacaoEsgotoSituacao", 
					Hibernate.INTEGER).addScalar("imovelPerfil", 
					Hibernate.INTEGER).addScalar("despejo", 
					Hibernate.INTEGER).addScalar("coordenadaX", 
					Hibernate.BIG_DECIMAL).addScalar("coordenadaY", 
					Hibernate.BIG_DECIMAL).addScalar("imovelPrincipal", 
					Hibernate.INTEGER).addScalar("numIptu", 
					Hibernate.BIG_DECIMAL).addScalar("numCelpe", 
					Hibernate.LONG).addScalar("idAreaConstruidaFaixa", 
					Hibernate.INTEGER).addScalar("idPoco", 
					Hibernate.INTEGER).addScalar("logBairro", 
					Hibernate.INTEGER).addScalar("logCep", 
					Hibernate.INTEGER)
					.setInteger("idImovel",idImovel);

			retorno = (Object[]) query.uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Vinicius Medeiros
	 * @date 20/09/2008
	 * 
	 * @return ImovelSubcategoria
	 * @throws ErroRepositorioException
	 */

	public Collection obterImovelSubcategoriaAtualizacaoCadastral(
			Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection retornoConsulta = null;
		Collection imovelSubcategorias = new ArrayList();

		try {

			consulta = " select scat.scat_id as idSubcategoria,"
					+ // 0
					" imsb_qteconomia as qteEconomia,"
					+ // 1
					" scat_dssubcategoria as descricaoSubcategoria,"
					+ // 2
					" catg.catg_id as idCategoria,"
					+ // 3
					" catg_dscategoria as descricaoCategoria"
					+ // 4
					" from cadastro.imovel_subcategoria isac"
					+ " inner join cadastro.subcategoria scat on (isac.scat_id = scat.scat_id)"
					+ " inner join cadastro.categoria  catg on (scat.catg_id = catg.catg_id)"
					+ " where imov_id = :idImovel";

			retornoConsulta = session.createSQLQuery(consulta).addScalar(
					"idSubcategoria", Hibernate.INTEGER).addScalar(
					"qteEconomia", Hibernate.SHORT).addScalar(
					"descricaoSubcategoria", Hibernate.STRING).addScalar(
					"idCategoria", Hibernate.INTEGER).addScalar(
					"descricaoCategoria", Hibernate.STRING).setInteger(
					"idImovel", idImovel).list();

			if (retornoConsulta.size() > 0) {
				Iterator imovelSubcategoriaIter = retornoConsulta.iterator();
				while (imovelSubcategoriaIter.hasNext()) {

					Object[] element = (Object[]) imovelSubcategoriaIter.next();

					ImovelSubcategoriaAtualizacaoCadastral imovSubAtual = new ImovelSubcategoriaAtualizacaoCadastral();

					imovSubAtual.setIdImovel(idImovel);

					imovSubAtual.setIdSubcategoria((Integer) element[0]);

					imovSubAtual.setQuantidadeEconomias((Short) element[1]);

					imovSubAtual.setDescricaoSubcategoria((String) element[2]);

					imovSubAtual.setIdCategoria((Integer) element[3]);

					imovSubAtual.setDescricaoCategoria((String) element[4]);

					imovelSubcategorias.add(imovSubAtual);
				}
			}

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return imovelSubcategorias;

	}

	/*
	 * [CRC] - 1672 - Melhorar performance das consultas do relatorio de imóveis
	 * com faturas em atraso.
	 * 
	 * O filtro da qtd de faturas em atraso e valor das Faturas em Atraso não
	 * são usados nas querys pois para que ela trouxesse o valor correto seria
	 * necessário um subselect, o que tornaria a consulta mais lenta. Assim,
	 * esse filtro é usado no momento de preparar os relatoriosBeans.
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

		String hidrometro = filtro.getHidrometro();
		
		Integer situacaoCobranca = filtro.getSituacaoCobranca();

		String categoria = "";
		if (!Util.isVazioOrNulo(colecaoCategorias)) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			String consulta = "";
			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta = " select \n"
					+
					// 0
					"   gr.id, \n"
					+
					// 1
					"   gr.nome, \n"
					+
					// 2
					"   un.id, \n"
					+
					// 3
					"   un.nome, \n"
					+
					// 4
					"   sc.codigo, \n"
					+
					// 5
					"   sc.descricao, \n"
					+
					// 6
					"   loc.id, \n"
					+
					// 7
					"   loc.descricao, \n"
					+
					// 8
					"   cli.nome, \n"
					+
					// 9
					"   las.descricaoAbreviado, \n"
					+
					// 10
					"   rot.codigo, \n"
					+
					// 11
					"   imo.numeroSequencialRota, \n"
					+
					// 12
					"   imo.id, \n"
					+
					// 13
					"   les.id, \n"
					+
					// 14
					"   les.descricaoAbreviado, \n"
					+
					// 15
					"   qua.numeroQuadra, \n"
					+
					// 16
					"   c.referencia as referenciaMinima, \n"
					+
					// 17
					"  sum( ( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) )) as valor, \n"
					+
					// 18
					"   imo.lote, \n" +
					// 19
					"   imo.subLote, \n" +
					// 20
					"   c.dataVencimentoConta, \n" +
					// 21
					"   cli.cpf, \n" +
					// 22
					"   cli.cnpj \n" +

					" from \n" + "   Conta c, ClienteImovel ci  " + categoria
					+ " \n" + "   inner join c.imovel imo \n"
					+ "   inner join imo.localidade loc \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join loc.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join qua.rota rot \n"
					+ "   inner join ci.cliente cli \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   imo.id = ci.imovel.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " and \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			//Consulta com Hidrômetro
			if(hidrometro != null && !hidrometro.equals("0")){
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca)";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by gr.id,gr.nome,un.id,un.nome,sc.codigo,sc.descricao,loc.id,loc.descricao,cli.nome,las.descricaoAbreviado,"
					+ " rot.codigo,imo.numeroSequencialRota,imo.id,les.id,les.descricaoAbreviado,qua.numeroQuadra,c.referencia,imo.lote,"
					+ " imo.subLote, c.dataVencimentoConta, c.dataVencimentoConta, cli.cpf, cli.cnpj ";

			consulta += " order by \n" + "   gr.id, \n" + "   un.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   rot.codigo, \n" + "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/*
	 * [CRC] - 1672 - Melhorar performance das consultas do relatorio de imóveis
	 * com faturas em atraso.
	 * 
	 * O filtro da qtd de faturas em atraso e valor das Faturas em Atraso não
	 * são usados nas querys pois para que ela trouxesse o valor correto seria
	 * necessário um subselect, o que tornaria a consulta mais lenta. Assim,
	 * esse filtro é usado no momento de preparar os relatoriosBeans.
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			Integer clienteSuperior = filtro.getClienteSuperior();
			Integer cliente = filtro.getCliente();
			Integer tipoRelacao = filtro.getTipoRelacao();
			Integer responsavel = filtro.getResponsavel();

			Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
					.getSituacaoLigacaoAgua();
			Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
			Collection<Integer> colecaoCategorias = filtro.getCategorias();
			Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

			Integer situacaoCobranca = filtro.getSituacaoCobranca();
			
			String hidrometro = filtro.getHidrometro();

			Integer referenciaImoveisFaturasAtrasoInicial = filtro
					.getReferenciaFaturasAtrasoInicial();
			Integer referenciaImoveisFaturasAtrasoFinal = filtro
					.getReferenciaFaturasAtrasoFinal();

			String clienteConta = "";
			if (cliente != null && responsavel != null
					&& !responsavel.equals(1)) {
				clienteConta = " , ClienteConta clienteConta ";
			}

			String categoria = "";
			if (!Util.isVazioOrNulo(colecaoCategorias)) {
				categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
			}

			String consulta = "";

			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta =

			" select \n"
					+
					// 1
					"   cli.id, \n"
					+
					// 2
					"   cli.nome, \n"
					+
					// 3
					"   gr.id, \n"
					+
					// 4
					"   loc.id, \n"
					+
					// 5
					"   sc.codigo, \n"
					+
					// 6
					"   qua.numeroQuadra, \n"
					+
					// 7
					"   rot.codigo, \n"
					+
					// 8
					"   imo.numeroSequencialRota, \n"
					+
					// 9
					"   imo.id, \n"
					+
					// 10
					"   las.descricaoAbreviado, \n"
					+
					// 11
					"   les.descricaoAbreviado, \n"
					+
					// 12
					"   c.id, \n"
					+
					// 13
					"   c.referencia , \n"
					+
					// 14
					"   c.dataVencimentoConta , \n"
					+
					// 15
					"   c.indicadorCobrancaMulta , \n"
					+
					// 16
					"   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as totalSemEncargos \n"
					+

					" from Cliente cli " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "   inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join imo.localidade loc \n"
					+ "   inner join qua.rota rot \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2) \n";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id \n";
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao \n";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo = :tipoRelacao \n";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder in(:esferaPoder) \n";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) \n";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) \n";
				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			// Consulta com Hidrômetro
			if(hidrometro != null && !hidrometro.equals("0")){
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca) \n ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by \n" + "   cli.id, \n" + "   cli.nome, \n"
					+ "   gr.id, \n" + "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota, \n" + "   imo.id, \n"
					+ "   las.descricaoAbreviado, \n"
					+ "   les.descricaoAbreviado, \n" + "   imo.subLote, \n"
					+ "	c.id, \n" + "	c.referencia, \n"
					+ " 	c.dataVencimentoConta, \n"
					+ " 	c.indicadorCobrancaMulta \n";

			consulta += " order by \n" + "   cli.id, \n" + "   gr.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Metodo seta os parametros numa determinada consulta e retorna um objeto
	 * Query com o sql/hql já com parametros.
	 * 
	 * @since 02/09/2009
	 * @author Marlon Patrick
	 */
	private Query criarQueryComParametros(String consulta,
			Map<String, Object> parameters, Session session) {

		Query query = session.createQuery(consulta);

		Set<String> set = parameters.keySet();
		Iterator<String> iterMap = set.iterator();
		while (iterMap.hasNext()) {
			String key = iterMap.next();
			if (parameters.get(key) instanceof Collection) {
				Collection<? extends Object> collection = (ArrayList<? extends Object>) parameters
						.get(key);
				query.setParameterList(key, collection);
			} else {
				query.setParameter(key, parameters.get(key));
			}
		}

		return query;
	}

	/**
	 * 
	 * [UC0535] Manter Feriado
	 * 
	 * @author bruno
	 * @date 12/01/2009
	 * 
	 * @param anoOrigemFeriado
	 */
	public Collection<NacionalFeriado> pesquisarFeriadosNacionais(
			String anoOrigemFeriado) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "from " + "  NacionalFeriado " + "where "
					+ "  to_char( data, 'yyyy' ) = :ano";

			retorno = session.createQuery(consulta).setString("ano",
					anoOrigemFeriado).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * [UC0535] Manter Feriado
	 * 
	 * @author bruno
	 * @date 12/01/2009
	 * 
	 * @param anoOrigemFeriado
	 */
	public Collection<MunicipioFeriado> pesquisarFeriadosMunicipais(
			String anoOrigemFeriado) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "from " + "  MunicipioFeriado " + "where "
					+ "  to_char( dataFeriado, 'yyyy' ) = :ano";

			retorno = session.createQuery(consulta).setString("ano",
					anoOrigemFeriado).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * [UC0535] Manter Feriados
	 * 
	 * @author bruno
	 * @date 13/01/2009
	 * 
	 * @param anoDestino
	 * @throws ErroRepositorioException
	 */
	public void excluirFeriadosNacionais(String anoDestino)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "delete " + "from " + "  NacionalFeriado " + "where "
					+ "  to_char( data, 'yyyy' ) = :ano";

			session.createQuery(consulta).setString("ano", anoDestino)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * [UC0535] Manter Feriados
	 * 
	 * @author bruno
	 * @date 13/01/2009
	 * 
	 * @param anoDestino
	 * @throws ErroRepositorioException
	 */
	public void excluirFeriadosMunicipais(String anoDestino)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "delete " + "from " + "  MunicipioFeriado " + "where "
					+ "  to_char( dataFeriado, 'yyyy' ) = :ano";

			session.createQuery(consulta).setString("ano", anoDestino)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0880] - Gerar Movimento de Extensao de Contas em Cobranca por Empresa
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/02/2009
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */

	public Collection pesquisarLocalidades() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection retorno = null;
		try {
			consulta = "select DISTINCT loca.id " + "from Localidade loca ";

			retorno = session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(
			String idEmpresa, String idLocalidade, String idAgenteComercial,
			String idSituacaoTransmissao) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac.id, "// 0
					+ " loc.id,"// 1
					+ " txac.codigoSetorComercial,"// 2
					+ " txac.codigoRota,"// 3
					+ " txac.descricaoArquivo,"// 4
					+ " txac.quantidadeImovel,"// 5
					+ " clie.nome,"// 6
					+ " func.nome,"// 7
					+ " sitTram.descricaoSituacao"// 8
					+ " from ArquivoTextoAtualizacaoCadastral txac "
					+ " inner join txac.leiturista leit"
					+ " left join txac.localidade loc"
					+ " left join txac.situacaoTransmissaoLeitura sitTram"
					+ " left join leit.cliente clie"
					+ " left join leit.funcionario func"
					+ " where leit.empresa.id = " + idEmpresa;

			if (idLocalidade != null && !idLocalidade.equals("")) {
				consulta = consulta + " and txac.localidade.id = "
						+ idLocalidade;
			}

			if (idAgenteComercial != null
					&& !idAgenteComercial.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				consulta = consulta + " and leit.id = " + idAgenteComercial;
			}

			if (idSituacaoTransmissao != null
					&& !idSituacaoTransmissao.equals("")) {
				consulta = consulta + " and sitTram.id = "
						+ idSituacaoTransmissao;
			}

			consulta = consulta + " order by txac.id";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(
			String idArquivoTxt) throws ErroRepositorioException {

		ArquivoTextoAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac"// 2
					+ " from ArquivoTextoAtualizacaoCadastral txac"
					+ " inner join fetch txac.leiturista leit"
					+ " where txac.id = " + idArquivoTxt;

			retorno = (ArquivoTextoAtualizacaoCadastral) session.createQuery(
					consulta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 05/03/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt,
			Integer idSituacaoTransmissao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String consulta = " update ArquivoTextoAtualizacaoCadastral txac"
				+ " set txac.situacaoTransmissaoLeitura.id =:idSituacaoTransmissao,"
				+ " txac.ultimaAlteracao = :dataAtual" + " where txac.id = "
				+ idArquivoTxt;

		try {

			session.createQuery(consulta).setInteger("idSituacaoTransmissao",
					idSituacaoTransmissao)
					.setTimestamp("dataAtual", new Date()).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(
			Integer idSetor,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Integer> retorno = null;

		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "SELECT distinct(imov.imov_id) as idImovel "
					+ "from cadastro.imovel imov "
					+ "inner JOIN cadastro.setor_comercial setor ON setor.stcm_id = imov.stcm_id "
					;

			if (helper.getCliente() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.cliente_imovel clim ON clim.imov_id = imov.imov_id ";
			}

			if (helper.getLocalidadeInicial() != null || helper.getElo() != null || 
					(helper.getLocalidade() != null && !helper.getLocalidade().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) ) {
				consulta = consulta + "INNER JOIN cadastro.localidade local ON local.loca_id = imov.loca_id ";
			}

			if ( (helper.getRotaInicial() != null || helper.getQuadraInicial() != null) || 
					( (helper.getNumeroQuadra() != null && !helper.getNumeroQuadra().isEmpty()) || (helper.getCodigoRota() != null && !helper.getCodigoRota().isEmpty()))) {
				consulta = consulta
						+ "INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id "
						+ "INNER JOIN micromedicao.rota rota ON rota.rota_id = qdra.rota_id "
						;
			}

			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.imovel_subcategoria imsb ON imsb.imov_id = imov.imov_id "
						+ "LEFT JOIN cadastro.subcategoria scat ON scat.scat_id = imsb.scat_id ";
			}

			consulta = consulta
					+ "where imov.imov_icexclusao <> 1 AND (imov.siac_id is null OR imov.siac_id = 0) AND ";

			

			if (helper.getMatricula() != null) {

				consulta = consulta + "imov.imov_id = :matricula AND ";
				parameters.put("matricula", helper.getMatricula());
			}

			if (helper.getCliente() != null) {

				consulta = consulta + "clim.clie_id = :cliente AND ";
				parameters.put("cliente", helper.getCliente());
			}

			if (helper.getElo() != null) {

				consulta = consulta + "local.loca_cdelo = :elo AND ";
				parameters.put("elo", helper.getElo());
			}

			if (helper.getLocalidadeInicial() != null) {

				consulta = consulta
						+ " local.loca_id between :localidadeInicial AND :localidadeFinal AND ";

				parameters.put("localidadeInicial", helper
						.getLocalidadeInicial());
				parameters.put("localidadeFinal", helper.getLocalidadeFinal());
			}
			
			if (helper.getSetorComercialInicial() != null) {

				consulta = consulta
						+ " imov.stcm_id between :setorComercialInicial AND :setorComercialFinal AND ";

				parameters.put("setorComercialInicial", helper
						.getSetorComercialInicial());
				parameters.put("setorComercialFinal", helper
						.getSetorComercialFinal());

			}
			
			if (helper.getCodigoSetorComercialInicial() != null) {

				consulta = consulta
						+ " setor.stcm_cdsetorcomercial between :codigoSetorComercialInicial AND :codigoSetorComercialFinal AND ";

				parameters.put("codigoSetorComercialInicial", helper.getCodigoSetorComercialInicial());
				parameters.put("codigoSetorComercialFinal", helper.getCodigoSetorComercialFinal());
			}
			
			if (helper.getQuadraInicial() != null) {

				consulta = consulta
						+ " qdra.qdra_nnquadra between :numeroQuadraInicial AND :numeroQuadraFinal AND ";

				parameters.put("numeroQuadraInicial", helper.getQuadraInicial());
				parameters.put("numeroQuadraFinal", helper.getQuadraFinal());
			}

			if (helper.getIdQuadraInicial() != null) {

				consulta = consulta
						+ " imov.qdra_id between :quadraInicial AND :quadraFinal AND ";

				parameters.put("quadraInicial", helper.getIdQuadraInicial());
				parameters.put("quadraFinal", helper.getIdQuadraFinal());
			}

			if (helper.getRotaInicial() != null) {

				consulta = consulta
						+ " rota.rota_cdrota between :rotaInicial AND :rotaFinal AND ";

				parameters.put("rotaInicial", helper.getRotaInicial());
				parameters.put("rotaFinal", helper.getRotaFinal());
			}

			if (helper.getRotaSequenciaInicial() != null) {

				consulta = consulta
						+ " imov.imov_nnsequencialrota between :rotaSequenciaInicial AND :rotaSequenciaFinal AND ";

				parameters.put("rotaSequenciaInicial", helper
						.getRotaSequenciaInicial());
				parameters.put("rotaSequenciaFinal", helper
						.getRotaSequenciaFinal());
			}

			if (helper.getPerfilImovel() != null) {

				consulta = consulta + "imov.iper_id = :perfilImovel AND ";
				parameters.put("perfilImovel", helper.getPerfilImovel());
			}

			if (helper.getCategoria() != null) {

				consulta = consulta + "scat.catg_id = :categoria AND ";
				parameters.put("categoria", helper.getCategoria());
			}

			if (helper.getSubCategoria() != null) {

				consulta = consulta + "scat.scat_id = :subCategoria AND ";
				parameters.put("subCategoria", helper.getSubCategoria());
			}

			if (helper.getIdSituacaoLigacaoAgua() != null) {
				consulta = consulta
						+ "imov.last_id = :idSituacaoLigacaoAgua AND ";
				parameters.put("idSituacaoLigacaoAgua", helper
						.getIdSituacaoLigacaoAgua());
			}
			
			if ( helper.getLocalidade() != null && !helper.getLocalidade().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {

				consulta = consulta+ " local.loca_id = :localidade and ";

				parameters.put("localidade", helper.getLocalidade());
			}
			
			

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				query.setParameter(key, parameters.get(key));
			}

			retorno = (Collection<Integer>) query.list();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Ana Maria
	 * @date 26/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelDebitoAtualizacaoCadastral(
			Collection colecaoIdsImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " SELECT	distinct(debitos.idImovel) as idImovel"
					+ " FROM (SELECT imov.imov_id idImovel, 'CONTA' as tipoDebito, 	sum(CASE WHEN pagtoConta.pgmt_id is null and conta.cnta_id is not null THEN conta.cnta_vlagua +  "
					+ "       conta.cnta_vlesgoto + conta.cnta_vldebitos - conta.cnta_vlcreditos -coalesce(conta.cnta_vlimpostos, 0 ) ELSE 0.00 END) valorDebitos, "
					+ "       count(CASE WHEN pagtoConta.pgmt_id is null and conta.cnta_id is not null THEN conta.cnta_id END) qtdeDebitos 	 "
					+ " 	   FROM cadastro.imovel imov  "
					+ " 	   LEFT OUTER JOIN faturamento.conta conta on conta.imov_id = imov.imov_id and conta.dcst_idatual in (0, 1, 2)  "
					+ " 	   LEFT OUTER JOIN arrecadacao.pagamento pagtoConta on conta.cnta_id = pagtoConta.cnta_id  "
					+ "	   WHERE cnta_dtvencimentoconta < :dataAtual "
					+ "       and imov.imov_id in(:colecaoIdsImovel)  "
					+ " 	   GROUP BY distinct(debitos.idimovel), tipoDebito  "
					+ "       UNION  "
					+ " 	   SELECT imov.imov_id as idImovel, 'GUIA' as tipoDebito, sum(CASE WHEN pagtoGuia.pgmt_id is null and gpag.gpag_id is not null THEN gpag.gpag_vldebito ELSE 0.00 END) as valorDebitos, "
					+ "       count(CASE WHEN pagtoGuia.pgmt_id is null and gpag.gpag_id is not null THEN gpag.gpag_id END) as qtdeDebitos  "
					+ " 	   FROM cadastro.imovel imov  "
					+ " 	   LEFT OUTER JOIN faturamento.guia_pagamento gpag on gpag.imov_id = imov.imov_id  "
					+ " 	   LEFT OUTER JOIN arrecadacao.pagamento pagtoGuia on gpag.gpag_id = pagtoGuia.gpag_id "
					+ " 	   WHERE gpag_dtvencimento < :dataAtual  "
					+ " 	   and imov.imov_id in (:colecaoIdsImovel)  "
					+ " 	   GROUP BY 	(imov.imov_id), tipoDebito) debitos "
					+ " INNER JOIN cadastro.imovel imov on debitos.idImovel = imov.imov_id  "
					+ " WHERE debitos.valorDebitos is null or debitos.valorDebitos > 0 "
					+ " order by 1 ";

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setParameterList("colecaoIdsImovel",
					colecaoIdsImovel).setTimestamp("dataAtual", new Date())
					.list();


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0893] - Inserir Unidade de Negocio
	 * 
	 * Verificar se o Cliente Selecionado existe na tabela Funcionario
	 * 
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 * 
	 * @param idCliente
	 * @throws ControladorException
	 */

	public Integer verificarClienteSelecionadoFuncionario(Integer idCliente)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Integer retorno = null;

		try {
			consulta = "select func.id "
					+ "from Funcionario func, Cliente clie "
					+ "where func.numeroCpf = clie.cpf "
					+ " and clie.id = :idCliente";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa a(s) quadra face associada a quadra
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(
			Integer idQuadra) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = " SELECT qFace.numeroQuadraFace, bacia.descricao, qFace.indicadorRedeEsgoto, " // 0,
																										// 1, 2
					+ " qFace.indicadorRedeAgua, distritoOperacional.descricao, " // 3, 4
					+ " sistemaEsgoto.descricao " // 5
					+ " FROM QuadraFace qFace "
					+ " left join qFace.bacia bacia "
					+ " left join bacia.sistemaEsgoto sistemaEsgoto "
					+ " left join qFace.distritoOperacional distritoOperacional "
					+ " WHERE " + " qFace.quadra.id = :idQuadra";

			retorno = (Collection<Object[]>) session.createQuery(consulta)
					.setInteger("idQuadra", idQuadra).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Integer> retorno = null;

		Query query = null;

		try {

			consulta = "SELECT distinct (imov.stcm_id) as idSetor "
				+ "from cadastro.imovel imov "
				+ "INNER JOIN cadastro.setor_comercial setor ON setor.stcm_id = imov.stcm_id ";

			if (helper.getCliente() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.cliente_imovel clim ON clim.imov_id = imov.imov_id ";
			}

			if (helper.getLocalidadeInicial() != null || helper.getElo() != null || 
					(helper.getLocalidade() != null && !helper.getLocalidade().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) ) {
				consulta = consulta
						+ "INNER JOIN cadastro.localidade local ON local.loca_id = imov.loca_id ";
			}

			
			if (helper.getNumeroQuadra() != null && !helper.getNumeroQuadra().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {

				consulta = consulta+ "INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id ";
			}
			
			if (helper.getRotaInicial() != null || 
					(helper.getCodigoRota() != null && !helper.getCodigoRota().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) ) {
				
				consulta = consulta
						+ "INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id "
						+ "INNER JOIN micromedicao.rota rota ON rota.rota_id = qdra.rota_id ";
			}
			
			

			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.imovel_subcategoria imsb ON imsb.imov_id = imov.imov_id "
						+ "LEFT JOIN cadastro.subcategoria scat ON scat.scat_id = imsb.scat_id ";
			}

			consulta = consulta
					+ "where imov.imov_icexclusao <> 1 AND (imov.siac_id is null OR imov.siac_id = 0) ";

			if (helper.getMatricula() != null) {

				consulta = consulta + " AND imov.imov_id = "
						+ helper.getMatricula();
			}

			if (helper.getCliente() != null) {

				consulta = consulta + " AND clim.clie_id = "
						+ helper.getCliente();
			}

			if (helper.getElo() != null) {

				consulta = consulta + " AND local.loca_cdelo = "
						+ helper.getElo();
			}

			if (helper.getLocalidadeInicial() != null) {

				consulta = consulta + " AND local.loca_id between "
						+ helper.getLocalidadeInicial() + " AND "
						+ helper.getLocalidadeFinal();

			}

			if (helper.getCodigoSetorComercialInicial() != null) {

				consulta = consulta + " AND setor.stcm_cdsetorcomercial between "
						+ helper.getCodigoSetorComercialInicial() + " AND "
						+ helper.getCodigoSetorComercialFinal();

			}
			
			if (helper.getSetorComercialInicial() != null) {

				consulta = consulta + " AND imov.stcm_id between "
						+ helper.getSetorComercialInicial() + " AND "
						+ helper.getSetorComercialFinal();

			}


			if (helper.getIdQuadraInicial() != null) {

				consulta = consulta + " AND imov.qdra_id between "
						+ helper.getIdQuadraInicial() + " AND "
						+ helper.getIdQuadraFinal();

			}

			if (helper.getRotaInicial() != null) {

				consulta = consulta + " AND rota.rota_cdrota between "
						+ helper.getRotaInicial() + " AND "
						+ helper.getRotaFinal();
			}

			if (helper.getRotaSequenciaInicial() != null) {

				consulta = consulta
						+ " AND imov.imov_nnsequencialrota between "
						+ helper.getRotaSequenciaInicial() + " AND "
						+ helper.getRotaSequenciaFinal();

			}

			if (helper.getPerfilImovel() != null) {

				consulta = consulta + " AND imov.iper_id = "
						+ helper.getPerfilImovel();
			}

			if (helper.getCategoria() != null) {

				consulta = consulta + " AND scat.catg_id = "
						+ helper.getCategoria();
			}

			if (helper.getSubCategoria() != null) {

				consulta = consulta + " AND scat.scat_id = "
						+ helper.getSubCategoria();
			}

			if (helper.getIdSituacaoLigacaoAgua() != null) {

				consulta = consulta + " AND imov.last_id = "
						+ helper.getIdSituacaoLigacaoAgua();
			}
			
			if (helper.getLocalidade() != null && !helper.getLocalidade().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {

				consulta = consulta + " AND local.loca_id = " + helper.getLocalidade();
			}
			
			if (helper.getCodigoSetorComercial() != null && !helper.getCodigoSetorComercial().isEmpty() ) {
			
				String codigoSetor = "";
				Iterator<Integer> iteratorSetor = helper.getCodigoSetorComercial().iterator();
				while( iteratorSetor.hasNext() ) {
					codigoSetor += iteratorSetor.next() +",";
				}
				codigoSetor = codigoSetor.substring(0, codigoSetor.length() -1);
				consulta = consulta + " AND setor.stcm_cdsetorcomercial in ( "+ codigoSetor + " )";
			}

			if (helper.getNumeroQuadra() != null && !helper.getNumeroQuadra().isEmpty() ) {

				String numeroQuadra = "";
				Iterator<Integer> iteratorQuadra = helper.getNumeroQuadra().iterator();
				while( iteratorQuadra.hasNext() ) {
					numeroQuadra += iteratorQuadra.next() +",";
				}
				numeroQuadra = numeroQuadra.substring(0, numeroQuadra.length() -1);
				consulta = consulta + " AND qdra.qdra_id in ( "+numeroQuadra+" ) ";
			}
			
			if ( helper.getCodigoRota() != null && !helper.getCodigoRota().isEmpty() )  {
				String codigoRota = "";
				Iterator<Integer> iteratorRota = helper.getCodigoRota().iterator();
				while( iteratorRota.hasNext() ) {
					codigoRota += iteratorRota.next() +",";
				}
				codigoRota = codigoRota.substring(0, codigoRota.length() -1);
				consulta = consulta + " AND rota.rota_cdrota in ( "+codigoRota+" ) ";
			}
			
			query = session.createSQLQuery(consulta).addScalar("idSetor",
					Hibernate.INTEGER);

			if (helper.getQuantidadeMaxima() == null) {
				retorno = (Collection<Integer>) query.list();
			} else {
				retorno = (Collection<Integer>) query.setMaxResults(
						helper.getQuantidadeMaxima()).list();
			}

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema obtém os dados do contrato com a empresa (a partir da tabela
	 * EMPRESA_CONTRATO_CADASTRO com EMPR_ID=Id da empresa retornado e
	 * ECCD_DTFINALCONTRATO maior ou igual à data corrente e
	 * ECCD_DTCANCELCONTRATO com o valor nulo)
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public EmpresaContratoCadastro pesquisarEmpresaContratoCadastro(
			Integer idEmpresa) throws ErroRepositorioException {

		EmpresaContratoCadastro retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select ecc " + "from EmpresaContratoCadastro ecc "
					+ "where ecc.empresa = :idEmpresa "
					+ "and ecc.dataFinalContrato >= :dataAtual "
					+ "and ecc.dataCancelamentoContrato is null "
					+ "order by ecc.dataInicioContrato  desc ";

			retorno = (EmpresaContratoCadastro) session.createQuery(consulta)
					.setInteger("idEmpresa", idEmpresa).setDate("dataAtual",
							new Date()).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema seleciona as operações efetuadas pela empresa no período
	 * informado e com imóvel associado [SB0001 - Selecionar Operações Efetuadas
	 * com Imóvel Associado].
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasComImovelAssociado(
			Date dataInicio, Date dataFim, Integer idEmpresa)
			throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select "
					+ "opef.opef_cnargumento as argumento, "
					+ "tbla.tbla_id2 as id2, "
					+ "atrb.atrb_id as idAtributo, "
					+ "ecca.ecca_vlatualizacao as valorAtualizacao, "
					+ "atrb.atgr_id as idGrupo, "
					+ "atrb.atrb_nnordememissao as ordemEmissao, "
					+ "count(opef.opef_cnargumento) as qtdade "
					+ "from seguranca.OPERACAO_EFETUADA opef "
					+ "  inner join seguranca.OPERACAO oper on oper.oper_id=opef.oper_id "
					+ "  inner join seguranca.FUNCIONALIDADE_ATRIBUTO fnat on fnat.fncd_id=oper.fncd_id "
					+ "  inner join seguranca.ATRIBUTO atrb on fnat.atrb_id=atrb.atrb_id "
					+ "  inner join seguranca.ATRIBUTO_GRUPO atgr on atgr.atgr_id=atrb.atgr_id and atgr.atgr_icimovel=1 "
					+ "  inner join seguranca.USUARIO_ALTERACAO usat on usat.tref_id=opef.opef_id and usat.usac_id=1 "
					+ "  inner join seguranca.usuario usur on usur.usur_id=usat.usis_id and usur.empr_id= :idEmpresa "
					+ "  inner join seguranca.TABELA_LINHA_ALTERACAO tbla on tbla.tref_id=opef.opef_id "
					+ "  inner join seguranca.tab_linha_col_alteracao tlco on tlco.tbla_id=tbla.tbla_id "
					+ "  inner join seguranca.TABELA_COLUNA_ATRIBUTO tcat on tcat.tbco_id=tlco.tbco_id and tcat.atrb_id=fnat.atrb_id "
					+ "  inner join cadastro.empr_contrato_cad_atrib ecca on ecca.atrb_id = atrb.atrb_id "
					+ "where   "
					+ "   to_date(to_char(OPEF_TMULTIMAALTERACAO,'YYYY/MM/DD'),'YYYY/MM/DD') between :dataInicio and :dataFim "
					+ "group by  opef.opef_cnargumento, tcat.atrb_id, tbla.tbla_id2 , atrb.atrb_id , ecca.ecca_vlatualizacao , atrb.atgr_id , atrb.atrb_nnordememissao "
					+ "order by " + "   opef.OPEF_CNARGUMENTO, tcat.ATRB_ID ";

			retornoConsulta = session
					.createSQLQuery(consulta)
					.addScalar("argumento", Hibernate.INTEGER)
					.addScalar("id2", Hibernate.INTEGER)
					.addScalar("idAtributo", Hibernate.INTEGER)
					.addScalar("valorAtualizacao", Hibernate.BIG_DECIMAL)
					.addScalar("idGrupo", Hibernate.INTEGER)
					.addScalar("ordemEmissao", Hibernate.SHORT)
					.addScalar("qtdade", Hibernate.INTEGER)
					.setDate("dataInicio", Util.formatarDataInicial(dataInicio))
					.setDate("dataFim", Util.formatarDataFinal(dataFim))
					.setInteger("idEmpresa", idEmpresa).list();

			if (retornoConsulta.size() > 0) {
				Iterator operacoesEfetuadasIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				AtributosBoletimChaveHelper atributosBoletimChaveHelper = null;
				OperacoesEfetuadasHelper helper = null;
				while (operacoesEfetuadasIter.hasNext()) {

					Object[] element = (Object[]) operacoesEfetuadasIter.next();

					helper = new OperacoesEfetuadasHelper();

					helper.setArgumento((Integer) element[0]);
					if (element[1] != null) {
						helper.setId2TabelaLinhaAlteracao((Integer) element[1]);
					} else {
						helper.setId2TabelaLinhaAlteracao(0);
					}

					helper.setValorAtualizacaoAtributo((BigDecimal) element[3]);

					atributosBoletimChaveHelper = new AtributosBoletimChaveHelper(
							(Integer) element[2], (Integer) element[4],
							(Short) element[5]);

					helper
							.setAtributosBoletimChaveHelper(atributosBoletimChaveHelper);

					retorno.add(helper);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema seleciona as operações efetuadas pela empresa no período
	 * informado e sem imóvel associado [SB0002] - Selecionar Operações
	 * Efetuadas sem Imóvel Associado
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasSemImovelAssociado(
			Date dataInicio, Date dataFim, Integer idEmpresa)
			throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select "
					+ "opef.opef_cnargumento as argumento, "
					+ "tbla.tbla_id2 as id2, "
					+ "atrb.atrb_id as idAtributo, "
					+ "ecca.ecca_vlatualizacao as valorAtualizacao, "
					+ "atrb.atgr_id as idGrupo, "
					+ "atrb.atrb_nnordememissao as ordemEmissao, "
					+ "count(opef.opef_cnargumento) as qtdade "
					+ "from seguranca.OPERACAO_EFETUADA opef "
					+ "  inner join seguranca.OPERACAO oper on oper.oper_id=opef.oper_id "
					+ "  inner join seguranca.FUNCIONALIDADE_ATRIBUTO fnat on fnat.fncd_id=oper.fncd_id "
					+ "  inner join seguranca.ATRIBUTO atrb on fnat.atrb_id=atrb.atrb_id "
					+ "  inner join seguranca.ATRIBUTO_GRUPO atgr on atgr.atgr_id=atrb.atgr_id and atgr.atgr_id=opef.atgr_id "
					+
					// "on atgr.atgr_id=atrb.atgr_id and atgr.atgr_icimovel=2 "
					// +
					"  inner join seguranca.USUARIO_ALTERACAO usat on usat.tref_id=opef.opef_id and usat.usac_id=1 "
					+ "  inner join seguranca.usuario usur on usur.usur_id=usat.usis_id and usur.empr_id= :idEmpresa "
					+ "  inner join seguranca.TABELA_LINHA_ALTERACAO tbla on tbla.tref_id=opef.opef_id "
					+ "  inner join seguranca.tab_linha_col_alteracao tlco on tlco.tbla_id=tbla.tbla_id "
					+ "  inner join seguranca.TABELA_COLUNA_ATRIBUTO tcat on tcat.tbco_id=tlco.tbco_id and tcat.atrb_id=fnat.atrb_id "
					+ " inner join cadastro.empr_contrato_cad_atrib ecca on ecca.atrb_id = atrb.atrb_id "
					+ "where "
					+ "   to_date(to_char(OPEF_TMULTIMAALTERACAO,'YYYY/MM/DD'),'YYYY/MM/DD') between :dataInicio and :dataFim "
					+ "group by  opef.opef_cnargumento, tcat.atrb_id, tbla.tbla_id2 , atrb.atrb_id , ecca.ecca_vlatualizacao , atrb.atgr_id , atrb.atrb_nnordememissao "
					+ "order by " + "   tbla.TBLA_ID2, tcat.ATRB_ID ";

			retornoConsulta = session
					.createSQLQuery(consulta)
					.addScalar("argumento", Hibernate.INTEGER)
					.addScalar("id2", Hibernate.INTEGER)
					.addScalar("idAtributo", Hibernate.INTEGER)
					.addScalar("valorAtualizacao", Hibernate.BIG_DECIMAL)
					.addScalar("idGrupo", Hibernate.INTEGER)
					.addScalar("ordemEmissao", Hibernate.SHORT)
					.addScalar("qtdade", Hibernate.INTEGER)
					.setDate("dataInicio", Util.formatarDataInicial(dataInicio))
					.setDate("dataFim", Util.formatarDataFinal(dataFim))
					.setInteger("idEmpresa", idEmpresa).list();

			if (retornoConsulta.size() > 0) {
				Iterator operacoesEfetuadasIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				AtributosBoletimChaveHelper atributosBoletimChaveHelper = null;
				OperacoesEfetuadasHelper helper = null;
				while (operacoesEfetuadasIter.hasNext()) {

					Object[] element = (Object[]) operacoesEfetuadasIter.next();

					helper = new OperacoesEfetuadasHelper();

					// 9.2 9.2. Neste caso, o Conteúdo do Argumento deve
					// corresponder ao conteúdo do segundo argumento (TBLA_ID2).
					helper.setArgumento((Integer) element[1]);
					// helper.setArgumento((Integer)element[0]);

					if (element[1] != null) {
						helper.setId2TabelaLinhaAlteracao((Integer) element[1]);
					} else {
						helper.setId2TabelaLinhaAlteracao(0);
					}

					helper.setValorAtualizacaoAtributo((BigDecimal) element[3]);

					atributosBoletimChaveHelper = new AtributosBoletimChaveHelper(
							(Integer) element[2], (Integer) element[4],
							(Short) element[5]);

					helper
							.setAtributosBoletimChaveHelper(atributosBoletimChaveHelper);

					retorno.add(helper);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema seleciona os atributos que compõem o boletim (a partir da
	 * tabela ATRIBUTO ordenando pelo grupo do atributo (ATGR_ID) e pela ordem
	 * de emissão (ATRB_NNORDEMEMISSAO)).
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAtributosBoletim()
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select atributo "
					+ "from Atributo atributo "
					+ "inner join fetch atributo.atributoGrupo atributoGrupo "
					+ "order by atributoGrupo.id, atributo.numeroOrdemEmissao  ";

			retorno = (Collection) session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * Valor de Atualização do Atributo (ECCA_VLATUALIZACAO da tabela
	 * EMPRESA_CONTRATO_CADASTRO_ATRIBUTO com ATRB_ID=ATRB_ID da tabela ATRIBUTO
	 * e ECCD_ID=ECCD_ID da tabela EMPRESA_CONTRATO_CADASTRO);
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorAtualizacaoAtributo(Integer idAtributo,
			Integer idEmpresaContratoCadastro) throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select ecca.valorAtualizacaoAtributo "
					+ "from EmpresaContratoCadastroAtributo ecca "
					+ "where ecca.atributo.id = :idAtributo "
					+ "and ecca.empresaContratoCadastro.id = :idEmpresaContratoCadastro ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idAtributo", idAtributo).setInteger(
					"idEmpresaContratoCadastro", idEmpresaContratoCadastro)
					.uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0925] Emitir Boletos
	 * 
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosBoleto(int quantidadeInicio, Integer grupo,
			String nomeEmpresa) throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select distinct "
					+ "	im.loca_id AS idLocalidade,  "
					+ "	sc.stcm_cdsetorcomercial AS codigoSetor,  "
					+ "	qd.qdra_nnquadra AS numeroQuadra, "
					+ "	im.imov_nnlote AS lote,	 "
					+ "	im.imov_nnsublote AS sublote, "
					+ "	im.imov_id AS matricula,  "
					+ "	clie_nmcliente AS nomeCliente, "
					+ "	rota.ftgr_id AS grupo, "
					+ "   rota.empr_id AS empresa, "
					+ "   rota.rota_cdrota AS codigoRota, "
					+ "   im.imov_nnsequencialrota as sequencialRota "
					+

					"from   "
					+ "	cadastro.imovel		 		            im "
					+ "	INNER JOIN cadastro.cliente_imovel 	    clieImov   ON im.imov_id = clieImov.imov_id "
					+ "	INNER JOIN cadastro.cliente 		    cliente	   ON clieImov.clie_id = cliente.clie_id "
					+ "	INNER JOIN cadastro.setor_comercial     sc		   ON im.stcm_id = sc.stcm_id "
					+ "	INNER JOIN cadastro.quadra 		        qd		   ON im.qdra_id = qd.qdra_id "
					+ "	INNER JOIN micromedicao.rota		    rota	   ON qd.rota_id = rota.rota_id "
					+ "	INNER JOIN cadastro.imovel_subcategoria isc1 	   ON im.imov_id = isc1.imov_id "
					+ "	INNER JOIN cadastro.subcategoria        scat1 	   ON scat1.scat_id = isc1.scat_id "
					+

					"where  "
					+ "	(im.last_id in (:ligadoAgua, :ligadoAnaliseAgua ) or im.lest_id = :ligadoEsgoto)  ";

			// Caso seja CAERN, considera todos os municipios
			// Alterado por Rômulo Aurélio / Analista: Rafael Pinto
			// Data: 25/11/2009
			if (!nomeEmpresa.equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN)) {

				consulta = consulta
						+ "   and sc.muni_id in (005, 090, 105, 290, 345, 520, 680, 720, 760, 775, 790, 940, 1070, 1130, 1140, 1160, 1640, 960) ";
			}

			// Fim da alteracao
			consulta = consulta + "   and rota.ftgr_id = :grupo "
					+ "	and ClieImov.crtp_id = :clienteRelacaoTipo "
					+ "	and ClieImov.clim_dtrelacaofim is null "
					+ "	and scat1.catg_id <> :categoria  ";

			// Caso seja CAERN, Ordena também por codigo da rota e Sequencial da
			// Rota
			// Alterado por Rômulo Aurélio / Analista: Rafael Pinto
			// Data: 25/11/2009
			if (!nomeEmpresa.equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN)) {
				consulta = consulta
						+ "order by grupo, empresa, idLocalidade, codigoSetor, numeroQuadra, lote, sublote  ";
			} else {
				consulta = consulta + "order by idLocalidade, codigoSetor, "
						+ " codigoRota, sequencialRota, lote, sublote  ";
			}
			retornoConsulta = session.createSQLQuery(consulta).addScalar(
					"idLocalidade", Hibernate.INTEGER).addScalar("codigoSetor",
					Hibernate.INTEGER).addScalar("numeroQuadra",
					Hibernate.INTEGER).addScalar("lote", Hibernate.SHORT)
					.addScalar("sublote", Hibernate.SHORT).addScalar(
							"matricula", Hibernate.INTEGER).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar("grupo",
							Hibernate.INTEGER).addScalar("empresa",
							Hibernate.INTEGER).addScalar("codigoRota",
							Hibernate.SHORT).addScalar("sequencialRota",
							Hibernate.INTEGER).setInteger("ligadoAgua",
							LigacaoAguaSituacao.LIGADO).setInteger(
							"ligadoAnaliseAgua",
							LigacaoAguaSituacao.LIGADO_EM_ANALISE).setInteger(
							"ligadoEsgoto", LigacaoEsgotoSituacao.LIGADO)
					.setInteger("clienteRelacaoTipo",
							ClienteRelacaoTipo.USUARIO).setInteger("categoria",
							Categoria.PUBLICO).setInteger("grupo", grupo).

					setFirstResult(quantidadeInicio).setMaxResults(1000).list();

			if (retornoConsulta.size() > 0) {
				Iterator dadosBoletoIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				DadosBoletoHelper helper = null;
				Imovel imovel = null;

				while (dadosBoletoIter.hasNext()) {

					Object[] element = (Object[]) dadosBoletoIter.next();
					helper = new DadosBoletoHelper();
					imovel = new Imovel();
					Localidade localidade = new Localidade();
					SetorComercial setorComercial = new SetorComercial();
					Quadra quadra = new Quadra();

					if (element[0] != null) {
						localidade.setId((Integer) element[0]);
						imovel.setLocalidade(localidade);
					}

					if (element[1] != null) {
						setorComercial.setCodigo((Integer) element[1]);
						imovel.setSetorComercial(setorComercial);
					}

					if (element[2] != null) {
						quadra.setNumeroQuadra((Integer) element[2]);
						imovel.setQuadra(quadra);
					}

					if (element[3] != null) {
						imovel.setLote((Short) element[3]);
					}

					if (element[4] != null) {
						imovel.setSubLote((Short) element[4]);
					}

					if (element[5] != null) {
						imovel.setId((Integer) element[5]);
					}

					helper.setImovel(imovel);

					if (element[6] != null) {
						helper.setNomeCliente((String) element[6]);
					}

					if (element[7] != null) {
						helper.setIdGrupoFaturamento((Integer) element[7]);
					}

					if (element[8] != null) {
						helper.setIdEmpresa((Integer) element[8]);
					}

					if (element[9] != null) {
						helper.setCodigoRota((Short) element[9]);
					}

					if (element[10] != null) {
						helper.setSequencialRota((Integer) element[10]);
					}

					retorno.add(helper);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0925] Emitir Boletos
	 * 
	 * retrona DBTP_VLLIMITE para DBTP_ID = idDebitoTipo
	 * 
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select dbtp.valorLimite " + "from DebitoTipo dbtp "
					+ "where dbtp.id = :idDebitoTipo ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idDebitoTipo", idDebitoTipo).uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta [FS0011]-Verificar
	 * a abrangência do código do usuário
	 * 
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)
			throws ErroRepositorioException {

		UnidadeNegocio retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select unidadeNegocio " + "FROM Usuario usuario "
					+ "INNER JOIN usuario.unidadeNegocio unidadeNegocio "
					+ "WHERE usuario.id = :idUsuario ";

			retorno = (UnidadeNegocio) session.createQuery(consulta)
					.setInteger("idUsuario", idUsuario).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public List pesquisarImoveisExcluirDaTarifaSocial(Integer idSetor,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select i.id, " + "i.quantidadeEconomias, "
					+ "ch.consumoMedio " + "FROM ConsumoHistorico ch "
					+ "INNER JOIN ch.imovel i "
					+ "WHERE i.imovelPerfil = 4 and "
					+ "i.indicadorExclusao = 2 and "
					+ "ch.consumoMedio > 19 and " + "i.setorComercial = "
					+ idSetor + " and ch.ligacaoTipo = 1 and "
					+ "ch.referenciaFaturamento = " + anoMesFaturamento
					+ " GROUP BY i.id, i.quantidadeEconomias, ch.consumoMedio ";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaDadoEconomia(String idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		// Update na tabela TarifaSocialDadoEconomia
		String consulta = " update TarifaSocialDadoEconomia tsde "
				+ " set tsde.tarifaSocialExclusaoMotivo = 37, "
				+ " tsde.dataExclusao =  " + Util.obterSQLDataAtual() + " , "
				+ " tsde.dataRevisao = null, "
				+ " tsde.tarifaSocialRevisaoMotivo = null, "
				+ " tsde.ultimaAlteracao =  " + Util.obterSQLDataAtual()
				+ " where tsde.imovel = " + idImovel;

		try {

			session.createQuery(consulta).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaImovel(String idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		// update na tabela Imovel
		String consulta = " update Imovel imov "
				+ " set imov.imovelPerfil = 5, " + " imov.ultimaAlteracao = "
				+ Util.obterSQLDataAtual() + " where imov.id = " + idImovel;

		try {

			session.createQuery(consulta).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros, Raphael Rossiter
	 * @date 17/12/2007, 11/06/2008
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		String consulta = "";

		try {
			consulta = "select count(distinct imov.imov_id) as cont "
					+

					"from cadastro.cliente_imovel clim "
					+

					"inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					// CLIENTE USUÁRIO
					"and (crtp.crtp_id = 2 ) "
					+

					// AGUA
					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesFaturamento ) "
					+

					// ESGOTO
					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesFaturamento ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).setInteger("anoMesFaturamento",
					anoMesFaturamento.intValue()).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros, Raphael Rossiter
	 * @date 17/12/2007, 11/06/2008
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount()
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select count(distinct imov_id) as cont " +

			"from cadastro.imovel_atlz_cadastral imovel ";

			consulta += " where  siac_id = " + ConstantesSistema.ZERO;

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Integer> pesquisarIdsImoveisAtualizacaoCadastral(
			Integer idEmpresaLeiturista)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select idImovel from ImovelAtualizacaoCadastral "

			+ "where idSituacaoAtualizacaoCadastral = "
					+ ConstantesSistema.ZERO

					+ " and idEmpresa = :idEmpresaLeiturista ";

			retorno = session.createQuery(consulta).setInteger(
					"idEmpresaLeiturista", idEmpresaLeiturista.intValue())
					.list();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * Pesquisa as críticas existentes para um determinado arquivo importado.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo
	 *            Id do arquivo
	 * @return Coleção de críticas do arquivo
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(
			int idArquivo) throws ErroRepositorioException {
		Collection<AtualizacaoCadastralSimplificadoCritica> retorno = new ArrayList<AtualizacaoCadastralSimplificadoCritica>();

		String consulta = "from AtualizacaoCadastralSimplificadoCritica critica"
				+ " join fetch critica.tipo tipo"
				+ " join fetch critica.linhas linha"
				+ " where linha.arquivo.id = :idArquivo"
				+ " order by tipo.descricao, critica.descricao";

		Session session = HibernateUtil.getSession();

		try {
			Query q = session.createQuery(consulta);
			q.setInteger("idArquivo", idArquivo);
			retorno = q.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		retorno = new HashSet<AtualizacaoCadastralSimplificadoCritica>(retorno);

		return retorno;
	}

	/**
	 * [UC0925] Emitir Boletos
	 * 
	 * retrona DBTP_VLSUGERIDO para DBTP_ID = idDebitoTipo
	 * 
	 * @author Rômulo Aurélio
	 * @date 22/12/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorSugeridoDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select dbtp.valorSugerido " + "from DebitoTipo dbtp "
					+ "where dbtp.id = :idDebitoTipo ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idDebitoTipo", idDebitoTipo).uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(
			String idArquivoTxt, Integer idSituacaoTransmissao)
			throws ErroRepositorioException {

		ArquivoTextoAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac"// 2
					+ " from ArquivoTextoAtualizacaoCadastral txac"
					+ " inner join fetch txac.leiturista leit"
					+ " where txac.id = " + idArquivoTxt
					+ " and txac.situacaoTransmissaoLeitura.id ="
					+ idSituacaoTransmissao;

			retorno = (ArquivoTextoAtualizacaoCadastral) session.createQuery(
					consulta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch Pesquisa imoveis
	 * para execução do batch
	 * 
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 * 
	 */
	public Collection pesquisarImovelEmProgramaEspecial(
			Integer idPerfilProgramaEspecial, Rota rota, int numeroIndice,
			int quantidadeRegistros) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT "
					+ " imovelProgramaEspecial,"
					+ " imovel,"
					+ " quadra.id,"
					+ " rota.id,"
					+ " faturamentoGrupo.id,"
					+ " faturamentoGrupo.anoMesReferencia, "
					+ " imovelProgramaEspecial.id "
					+ "FROM ImovelProgramaEspecial imovelProgramaEspecial"
					+ " INNER JOIN FETCH imovelProgramaEspecial.imovel imovel"
					+ " INNER JOIN imovel.quadra quadra"
					+ " INNER JOIN quadra.rota rota"
					+ " INNER JOIN rota.faturamentoGrupo faturamentoGrupo"
					+ " WHERE imovelProgramaEspecial.imovelPerfil.id = :idPerfilProgramaEspecial "
					+ " AND (imovelProgramaEspecial.dataSuspensao IS NULL or imovelProgramaEspecial.formaSuspensao = :formaSuspensao)"
					+ " AND rota.id = :idRota "

					+ "ORDER BY imovelProgramaEspecial.id";

			retorno = session.createQuery(consulta).setInteger(
					"idPerfilProgramaEspecial", idPerfilProgramaEspecial)
					.setInteger("idRota", rota.getId()).setShort(
							"formaSuspensao",
							ImovelProgramaEspecial.FORMA_SUSPENSAO_OPERADOR)
					.setMaxResults(quantidadeRegistros).setFirstResult(
							numeroIndice).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo, Ivan Sergio
	 * @date 18/01/2010 15/09/2010 - CRC4734: Retirar do filtro o perfil do
	 *       imóvel e obter as contas a partir de fatura item da fatura
	 *       selecionada;
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select "
					+ "	imov.imov_id as idImovelEsp, "
					+ "	rgds.rdes_id as idRegDes, "
					+ "	rgds.rdes_nmregiaodesenvolvimento as nomeRegDes, "
					+ "	loca.loca_id as idLocalidade, "
					+ "	loca.loca_nmlocalidade as nomeLocalidade, "
					+ "	clie.clie_nmcliente as nomeCliente, "
					+ "	case when (lagu.hidi_id is null) then "
					+ "		'SEM HIDR.' "
					+ "	else "
					+ "		'COM HIDR.' "
					+ "	end as hidi, "
					+ "	( coalesce(cnta.cnta_nnconsumoagua, 0) + coalesce(cths.cnhi_nnconsumoagua, 0) ) as consumoAgua, "
					+ "	( ( ( coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + coalesce(cnta.cnta_vldebitos, 0) ) "
					+ "	- ( coalesce(cnta.cnta_vlcreditos, 0) + coalesce(cnta.cnta_vlimpostos, 0) ) ) + "
					+ "	( ( coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + coalesce(cths.cnhi_vldebitos, 0) ) "
					+ "	- ( coalesce(cths.cnhi_vlcreditos, 0) + coalesce(cths.cnhi_vlimpostos, 0) ) ) ) as valorConta "
					+ "from "
					+ "	faturamento.fatura fatu "
					+ "	inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "	inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "	left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "	left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "	inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "	inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "	inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "	inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "	inner join cadastro.regiao_desenvolvimento rgds on rgds.rdes_id = muni.rdes_id "
					+ "	inner join atendimentopublico.ligacao_agua lagu on lagu.lagu_id = imov.imov_id "
					+ "where "
					+ "	fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "	and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta = consulta + "	and loca.loca_id = "
						+ helper.getIdLocalidade() + " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta = consulta + "	and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			consulta = consulta
					+ "	order by idRegDes, idLocalidade, idImovelEsp";

			retorno = session.createSQLQuery(consulta).addScalar("idImovelEsp",
					Hibernate.INTEGER).addScalar("idRegDes", Hibernate.INTEGER)
					.addScalar("nomeRegDes", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar("hidi",
							Hibernate.STRING).addScalar("consumoAgua",
							Hibernate.INTEGER).addScalar("valorConta",
							Hibernate.BIG_DECIMAL).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais - Relatório
	 * Analítico
	 * 
	 * @author Hugo Leonardo, Ivan Sergio
	 * @date 18/01/2010 15/09/2010 - CRC4734: Retirar do filtro o perfil do
	 *       imóvel e obter as contas a partir de fatura item da fatura
	 *       selecionada;
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Integer retorno = 0;
		String consulta = "";
		Collection colecao = null;

		Session session = HibernateUtil.getSession();

		try {
			consulta = "select "
					+ "	count(ftit.cnta_id) as qtd "
					+ "from "
					+ "	faturamento.fatura fatu "
					+ "	inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "	inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "	left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "	left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "	inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "	inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "	inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "	inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "where "
					+ "	fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "	and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta += "	and loca.loca_id = " + helper.getIdLocalidade()
						+ " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta += "	and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			colecao = (Collection) session.createSQLQuery(consulta).addScalar(
					"qtd", Hibernate.INTEGER).list();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator iter = colecao.iterator();
				Integer element = (Integer) iter.next();
				retorno = (Integer) element;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais - Relatório
	 * Sintético
	 * 
	 * @author Hugo Leonardo, Ivan Sergio
	 * @date 25/01/2010 15/09/2010 - CRC4734: Retirar do filtro o perfil do
	 *       imóvel e obter as contas a partir de fatura item da fatura
	 *       selecionada;
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */

	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select A.idRegDes as idReg, "
					+ "	A.nomeRegDes as nomeReg, "
					+ "	A.idLocalidade as idLoca, "
					+ "	A.nomeLocalidade as nomeLoca, "
					+ "	SUM(A.qtdContaSemHidr) as qtdContaSemHidr, "
					+ "	SUM(A.valorContasSemHidr) as valorContasSemHidr, "
					+ "	SUM(A.qtdContaComHidr) as qtdContaComHidr, "
					+ "	SUM(A.valorContasComHidr) as valorContasComHidr "
					+ "FROM ( "
					+ "	select "
					+ "		rgds.rdes_id idRegDes, "
					+ "		rgds.rdes_nmregiaodesenvolvimento as nomeRegDes, "
					+ "		loca.loca_id as idLocalidade, "
					+ "		loca.loca_nmlocalidade as nomeLocalidade, "
					+ "		case when (lagu.hidi_id is null) then 1 else 0 end as qtdContaSemHidr, "
					+ "		case when (lagu.hidi_id is null) then "
					+ "		( ( ( (coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + "
					+ "		coalesce(cnta.cnta_vldebitos, 0)) - (coalesce(cnta.cnta_vlcreditos, 0) + "
					+ "		coalesce(cnta.cnta_vlimpostos, 0)) ) ) ) + "
					+ "		( ( ( (coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + "
					+ "		coalesce(cths.cnhi_vldebitos, 0)) - (coalesce(cths.cnhi_vlcreditos, 0) + "
					+ "		coalesce(cths.cnhi_vlimpostos,0)) ) ) ) "
					+ "		else 0 end as valorContasSemHidr, "
					+ "		case when (lagu.hidi_id is not null) then 1 else 0 end as qtdContaComHidr, "
					+ "		case when (lagu.hidi_id is not null) then "
					+ "		( ( ( (coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + "
					+ "		coalesce(cnta.cnta_vldebitos, 0)) - (coalesce(cnta.cnta_vlcreditos, 0) + "
					+ "		coalesce(cnta.cnta_vlimpostos, 0)) ) ) ) + "
					+ "		( ( ( (coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + "
					+ "		coalesce(cths.cnhi_vldebitos, 0)) - (coalesce(cths.cnhi_vlcreditos, 0) + "
					+ "		coalesce(cths.cnhi_vlimpostos, 0)) ) ) ) "
					+ "		else 0 end as valorContasComHidr "
					+ "	from "
					+ "		faturamento.fatura fatu "
					+ "		inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "		inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "		left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "		left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "		inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "		inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "		inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "		inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "		inner join cadastro.regiao_desenvolvimento rgds on rgds.rdes_id = muni.rdes_id "
					+ "		left join atendimentopublico.ligacao_agua lagu on lagu.lagu_id = imov.imov_id "
					+ "	where "
					+ "		fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "		and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta += "		and loca.loca_id = " + helper.getIdLocalidade()
						+ " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta += "		and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			consulta += ") A "
					+ "group by "
					+ "	A.idRegDes, A.nomeRegDes, A.idLocalidade, A.nomeLocalidade "
					+ "order by " + "	idReg, idLoca";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"idReg", Hibernate.INTEGER).addScalar("nomeReg",
					Hibernate.STRING).addScalar("idLoca", Hibernate.INTEGER)
					.addScalar("nomeLoca", Hibernate.STRING).addScalar(
							"qtdContaSemHidr", Hibernate.INTEGER).addScalar(
							"valorContasSemHidr", Hibernate.BIG_DECIMAL)
					.addScalar("qtdContaComHidr", Hibernate.INTEGER).addScalar(
							"valorContasComHidr", Hibernate.BIG_DECIMAL).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * 
	 * @author Hugo Leonardo
	 * @date 10/02/2010
	 * 
	 * @param idImovel
	 * 
	 * @return Quantidade de Parcelamentos do Imóvel
	 * @throws FachadaException
	 */
	public Integer verificarExistenciaParcelamentoImovel(Integer idImovel)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta += " select count (dc.imov_id) as cont "
				+ " from faturamento.debito_a_cobrar dc "
				+ " where dc.parc_id is not null "
				+ " and dc.dbac_nnprestacaocobradas <> dc.dbac_nnprestacaodebito "
				+ " and dc.imov_id = " + idImovel + " ";
		try {
			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @return Collection<RelatorioColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial,
			String idLocalidadeFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		try {

			consulta += " select distinct faturamentoGrupo.id, " // 0
					+ " faturamentoGrupo.descricao, " // 1
					+ " imovel.localidade.id, " // 2
					+ " imovel.localidade.descricao, " // 3
					+ " rota.codigo, " // 4
					+ " cliente.nome, " // 5
					+ " imovel.id, " // 6
					+ " localidade.gerenciaRegional, " // 7
					+ " imovel.localidade, " // 8
					+ " setor.codigo, " // 9
					+ " quadra.numeroQuadra, " // 10
					+ " roteiro.numeroLoteImovel, " // 11
					+ " roteiro.numeroSubloteImovel " // 12
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.clienteImoveis ci "
					+ " inner join ci.cliente cliente "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join imovel.localidade localidade "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo "
					+ " where roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
					+ " and imovel.ligacaoAguaSituacao in (:last) "
					+ " and imovel.ligacaoEsgotoSituacao in (:lest) "
					+ " and ci.dataFimRelacao is null "
					+ " and ci.clienteRelacaoTipo = :cliRelacaoTipo ";

			if (faturamentoGrupo != null && !faturamentoGrupo.equals("")) {

				consulta += " and faturamentoGrupo.id = :faturamentoGrupo ";

				parameters.put("faturamentoGrupo",
						new Integer(faturamentoGrupo));
			}

			if (idLocalidadeInicial != null && idLocalidadeFinal != null
					&& !idLocalidadeInicial.equals("")
					&& !idLocalidadeFinal.equals("")) {

				consulta += " and imovel.localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

				parameters.put("idLocalidadeInicial", new Integer(
						idLocalidadeInicial));
				parameters.put("idLocalidadeFinal", new Integer(
						idLocalidadeFinal));

				// Setor Comercial
				if (idSetorComercialInicial != null
						&& idSetorComercialFinal != null
						&& !idSetorComercialInicial.equals("")
						&& !idSetorComercialFinal.equals("")) {

					consulta += " and setor.codigo between :idSetorComercialInicial and :idSetorComercialFinal ";

					parameters.put("idSetorComercialInicial", new Integer(
							idSetorComercialInicial));
					parameters.put("idSetorComercialFinal", new Integer(
							idSetorComercialFinal));
				}

			}

			if (rotaInicial != null && rotaFinal != null
					&& !rotaInicial.equals("") && !rotaFinal.equals("")) {

				consulta += " and rota.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", new Integer(rotaInicial));
				parameters.put("rotaFinal", new Integer(rotaFinal));

			}

			if (sequencialRotaInicial != null && sequencialRotaFinal != null
					&& !sequencialRotaInicial.equals("")
					&& !sequencialRotaFinal.equals("")) {

				consulta += " and imovel.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", new Integer(
						sequencialRotaInicial));
				parameters.put("sequencialRotaFinal", new Integer(
						sequencialRotaFinal));

			}

			consulta += " order by localidade.gerenciaRegional, imovel.localidade, setor.codigo, quadra.numeroQuadra, "
					+ " roteiro.numeroLoteImovel, roteiro.numeroSubloteImovel, imovel.id, faturamentoGrupo.id, "
					+ " faturamentoGrupo.descricao, imovel.localidade.id, imovel.localidade.descricao, "
					+ " rota.codigo, cliente.nome ";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			Collection colecaoLast = new ArrayList();
			colecaoLast.add(LigacaoAguaSituacao.LIGADO);
			colecaoLast.add(LigacaoAguaSituacao.LIGADO_EM_ANALISE);
			colecaoLast.add(LigacaoAguaSituacao.CORTADO);
			colecaoLast.add(LigacaoAguaSituacao.SUPRIMIDO);

			Collection colecaoLest = new ArrayList();
			colecaoLest.add(LigacaoEsgotoSituacao.POTENCIAL);
			colecaoLest.add(LigacaoEsgotoSituacao.LIGADO);
			colecaoLest.add(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			colecaoLest.add(LigacaoEsgotoSituacao.TAMPONADO);

			retorno = query.setParameterList("last", colecaoLast)
					.setParameterList("lest", colecaoLest).setInteger(
							"cliRelacaoTipo", ClienteRelacaoTipo.USUARIO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial,
			String idLocalidadeFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		String consulta = "";

		try {

			consulta += " select count (roteiro.imovel.id) as cont "
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo "
					+ " inner join rota.empresa empresa "
					+ " where roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
					+ " and imovel.ligacaoAguaSituacao in (:last) "
					+ " and imovel.ligacaoEsgotoSituacao in (:lest) ";

			if (faturamentoGrupo != null && !faturamentoGrupo.equals("")) {

				consulta += " and faturamentoGrupo.id = :faturamentoGrupo ";
				parameters.put("faturamentoGrupo",
						new Integer(faturamentoGrupo));
			}

			if (idLocalidadeInicial != null && idLocalidadeFinal != null
					&& !idLocalidadeInicial.equals("")
					&& !idLocalidadeFinal.equals("")) {

				consulta += " and imovel.localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

				parameters.put("idLocalidadeInicial", new Integer(
						idLocalidadeInicial));
				parameters.put("idLocalidadeFinal", new Integer(
						idLocalidadeFinal));

				// Setor Comercial
				if (idSetorComercialInicial != null
						&& idSetorComercialFinal != null
						&& !idSetorComercialInicial.equals("")
						&& !idSetorComercialFinal.equals("")) {

					consulta += " and setor.codigo between :idSetorComercialInicial and :idSetorComercialFinal ";

					parameters.put("idSetorComercialInicial", new Integer(
							idSetorComercialInicial));
					parameters.put("idSetorComercialFinal", new Integer(
							idSetorComercialFinal));
				}

			}

			if (rotaInicial != null && rotaFinal != null
					&& !rotaInicial.equals("") && !rotaFinal.equals("")) {

				consulta += " and rota.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", new Integer(rotaInicial));
				parameters.put("rotaFinal", new Integer(rotaFinal));

			}

			if (sequencialRotaInicial != null && sequencialRotaFinal != null
					&& !sequencialRotaInicial.equals("")
					&& !sequencialRotaFinal.equals("")) {

				consulta += " and imovel.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", new Integer(
						sequencialRotaInicial));
				parameters.put("sequencialRotaFinal", new Integer(
						sequencialRotaFinal));
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			Collection colecaoLast = new ArrayList();
			colecaoLast.add(LigacaoAguaSituacao.LIGADO);
			colecaoLast.add(LigacaoAguaSituacao.LIGADO_EM_ANALISE);
			colecaoLast.add(LigacaoAguaSituacao.CORTADO);
			colecaoLast.add(LigacaoAguaSituacao.SUPRIMIDO);

			Collection colecaoLest = new ArrayList();
			colecaoLest.add(LigacaoEsgotoSituacao.POTENCIAL);
			colecaoLest.add(LigacaoEsgotoSituacao.LIGADO);
			colecaoLest.add(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			colecaoLest.add(LigacaoEsgotoSituacao.TAMPONADO);

			retorno = (Integer) query.setParameterList("last", colecaoLast)
					.setParameterList("lest", colecaoLest).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do
	 * imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010
	 */
	public Collection<Integer> pesquisarIdsImoveisDoSetorComercial(
			Integer idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException {
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " SELECT id " + " FROM Imovel"
					+ " WHERE setorComercial = :idSetor" + " ORDER BY id";

			retorno = (Collection<Integer>) session.createQuery(consulta)
					.setInteger("idSetor", idSetor).setFirstResult(
							quantidadeInicio).setMaxResults(quantidadeMaxima)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do
	 * imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010
	 */
	public void atualizarCodigoDebitoAutomatico(Integer idImovel,
			Integer codigoDebitoAutomatico) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "UPDATE Imovel "
				+ "set codigoDebitoAutomatico =:codigoDebitoAutomatico where id = :idImovel ";

		try {

			session.createQuery(consulta).setInteger("codigoDebitoAutomatico",
					codigoDebitoAutomatico).setInteger("idImovel", idImovel)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método que baixa a nova versão do JAD do mobile para o celular
	 * 
	 * @author Bruno Barros
	 * @date 08/06/2010
	 * 
	 * @param
	 * @throws IOException
	 */
	public byte[] baixarNovaVersaoJad() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		byte[] retorno = null;

		try {
			consulta = " select vemo_arquivojad as jad from cadastro.versao_mobile order by replace( vemo_nnversao, '.', '' ) desc";

			retorno = (byte[]) session.createSQLQuery(consulta).addScalar(
					"jad", Hibernate.BINARY).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		/*
		 * if ( retorno != null) {
		 * 
		 * StringBuilder sb = new StringBuilder();
		 * 
		 * try { sb = new StringBuilder( retorno.toString() ); } catch
		 * (IOException e) { throw new ErroRepositorioException( "Erro em
		 * Transformar Array de Byte em Object"); } catch
		 * (ClassNotFoundException e) { throw new ErroRepositorioException(
		 * "Erro em Transformar Array de Byte em Object"); } retorno =
		 * sb.toString().getBytes(); }
		 */

		return retorno;
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método que baixa a nova versão do JAR do mobile para o celular
	 * 
	 * @author Bruno Barros
	 * @date 08/06/2010
	 * 
	 * @param
	 * @throws IOException
	 */
	public byte[] baixarNovaVersaoJar() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		byte[] retorno = null;

		try {
			consulta = " select vemo_arquivojar as jar from cadastro.versao_mobile order by replace( vemo_nnversao, '.', '' ) desc";

			retorno = (byte[]) session.createSQLQuery(consulta).addScalar(
					"jar", Hibernate.BINARY).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 07/07/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel)
			throws ErroRepositorioException {

		boolean retorno = false;

		List resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  i.imov_id as idImovel, "
					+ "   cs.cbst_dscobrancasituacao as situacao"
					+ " FROM cadastro.imovel i"
					+ " INNER JOIN faturamento.conta c on c.imov_id = i.imov_id"
					+ " INNER JOIN cobranca.cobranca_situacao cs on cs.cmrv_id = c.cmrv_id"
					+ " WHERE cs.cbst_id = :cobrancaSituacao"
					+ " and i.imov_id = :idImovel";

			resultado = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("situacao", Hibernate.STRING)
					.setInteger("cobrancaSituacao",
							CobrancaSituacao.EM_COBRANCA_JUDICIAL).setInteger(
							"idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		if (resultado != null && resultado.size() > 0) {
			// Imovel esta em cobranca judicial
			retorno = true;

		} else {
			// imovel nao esta em cobranca judicial
			retorno = false;
		}

		return retorno;

	}

	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 07/07/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarSituacaoImovelNegativacao(Integer idImovel)
			throws ErroRepositorioException {

		boolean retorno = false;

		List resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  i.imov_id as idImovel "
					+ " FROM cadastro.imovel i"
					+ " INNER JOIN cobranca.negatd_movimento_reg neg on neg.imov_id = i.imov_id"
					+ " WHERE (neg.nmrg_icaceito is null or neg.nmrg_icaceito = 1) "
					+ " and neg.nmrg_idreginclusao is null and neg.nmrg_cdexclusaotipo is null "
					+ " and i.imov_id = :idImovel";

			resultado = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setInteger("idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		if (resultado != null && resultado.size() > 0) {
			// Imovel esta em negativacao
			retorno = true;

		} else {
			// imovel nao esta em negativacao
			retorno = false;
		}

		return retorno;

	}

	/**
	 * 
	 * [UC1036] - Inserir Cadastro de Email do Cliente
	 * 
	 * @author Fernando Fontelles
	 * @date 09/07/2010
	 * 
	 * @param idCliente
	 * @param nomeClienteAnterior
	 * @param cpfAnterior
	 * @param cnpjAnterior
	 * @param emailAnterior
	 * @param nomeSolicitante
	 * @param cpfSolicitante
	 * @param nomeClienteAtual
	 * @param cpfClienteAtual
	 * @param cnpjClienteAtual
	 * @param emailAtual
	 * @return
	 */
	public Integer inserirCadastroEmailCliente(Integer idCliente,
			String nomeClienteAnterior, String cpfAnterior,
			String cnpjAnterior, String emailAnterior, String nomeSolicitante,
			String cpfSolicitante, String nomeClienteAtual,
			String cpfClienteAtual, String cnpjClienteAtual, String emailAtual)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Integer retorno;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
			String sequence = Util
					.obterNextValSequence("cadastro.sequence_email_client_alterada");
			insert = "insert into cadastro.email_client_alterada( ecla_id, "
					+ " clie_id, " + " ecla_nmclienteanterior, "
					+ " ecla_nncpfanterior, " + " ecla_nncnpjanterior, "
					+ " ecla_dsemailanterior, " + " ecla_nmsolicitante, "
					+ " ecla_nncpfsolicitante, " + " ecla_nmclienteatual, "
					+ " ecla_nncpfatual, " + " ecla_nncnpjatual, "
					+ " ecla_dsemailatual, " + " ecla_tmconfirmacaoonline, "
					+ " ecla_tmsolicitacaoonline, "
					+ " ecla_tmultimaalteracao ) " + "values ( " + sequence
					+ ", " + idCliente + ", " + nomeClienteAnterior + ", "
					+ cpfAnterior + ", " + cnpjAnterior + ", " + emailAnterior
					+ ", " + nomeSolicitante + ", " + cpfSolicitante + ", "
					+ nomeClienteAtual + ", " + cpfClienteAtual + ", "
					+ cnpjClienteAtual + ", " + emailAtual + ", " + null + ", "
					+ Util.obterSQLDataAtual() + ", "
					+ Util.obterSQLDataAtual() + ")";

			retorno = (Integer) stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}

		return retorno;

	}

	/**
	 * Atualiza o sequencial de rota do imóvel correspondente ao
	 * RotaAtualizacaoSeq recebido
	 * 
	 * @author Bruno Barros
	 * @date 11/08/2010
	 * 
	 * @param rotaAtualizacaoSeq -
	 *            Registro da tabela micromedicao.rota_atualizacao_sequencial
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarSequenciaRotaImovel(RotaAtualizacaoSeq seq)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String delete = "update Imovel as imovel "
					+ "set imovel.numeroSequencialRota = :sequencial "
					+ "where imovel.id = :idImovel";

			session.createQuery(delete).setInteger("sequencial",
					seq.getSequencialRota() * 10).setInteger("idImovel",
					seq.getImovel().getId()).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/09/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(
			Integer idImovel) throws ErroRepositorioException {

		ClienteImovel resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN clienteImovel.cliente cliente "
					+ " INNER JOIN clienteImovel.imovel imovel "
					+ " LEFT JOIN cliente.clienteTipo clienteTipo "
					+ " WHERE clienteImovel.clienteRelacaoTipo = :responsavel "
					+ " and clienteImovel.dataFimRelacao is NULL "
					+ " and clienteTipo.esferaPoder.id in ("
					+ EsferaPoder.ESTADUAL + "," + EsferaPoder.MUNICIPAL + ","
					+ EsferaPoder.FEDERAL + ") "
					+ " and clienteImovel.imovel.id = :idImovel";

			resultado = (ClienteImovel) session.createQuery(consulta)
					.setInteger("responsavel", ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return resultado;

	}

	/**
	 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
	 * 
	 * @author Hugo Amorim
	 * @date 08/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(
			GerarRelatorioAlteracoesSistemaColunaHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT us.unid_dsunidade as unidSuperior,"
					+ " uo.unid_dsunidade as unidOrganizacional,"
					+ " u.usur_nmusuario as usuario,"
					+ " ms.meso_dsmeiosolicitacao as meio,"
					+ " count(*) as contador"
					+ " FROM seguranca.tab_linha_col_alteracao tbco"
					+ " INNER JOIN seguranca.tabela_coluna tc on tc.tbco_id = tbco.tbco_id"
					+ " INNER JOIN seguranca.tabela_linha_alteracao tla on tla.tbla_id = tbco.tbla_id"
					+ " INNER JOIN seguranca.operacao_efetuada oe on oe.opef_id = tla.tref_id"
					+ " INNER JOIN seguranca.operacao o on o.oper_id = oe.oper_id"
					+ " INNER JOIN seguranca.usuario_alteracao ua on ua.tref_id = oe.opef_id"
					+ " INNER JOIN seguranca.usuario u on u.usur_id = ua.usis_id"
					+ " INNER JOIN cadastro.unidade_organizacional uo on uo.unid_id = u.unid_id"
					+ " LEFT JOIN cadastro.unidade_organizacional us on us.unid_id = uo.unid_idsuperior"
					+ " INNER JOIN atendimentopublico.meio_solicitacao ms on ms.meso_id = uo.meso_id"
					+ " WHERE tbco.tbco_id = :idColuna"
					+ " and to_date(to_char(tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoInicial and :periodoFinal"
					+ " and ";

			if (helper.getIdMeioSolicitacao() != null
					&& !helper.getIdMeioSolicitacao().equals("-1")
					&& !helper.getIdMeioSolicitacao().equals("0")) {
				consulta += " uo.meso_id = :meioSolicitacao and ";
				parameters.put("meioSolicitacao", Integer.parseInt(helper
						.getIdMeioSolicitacao()));
			}
			if (helper.getIdFuncionalidade() != null
					&& !helper.getIdFuncionalidade().equals("")) {
				consulta += " o.fncd_id = :idFuncionalidade and ";
				parameters.put("idFuncionalidade", Integer.parseInt(helper
						.getIdFuncionalidade()));
			}
			if (helper.getIdOperacao() != null
					&& !helper.getIdOperacao().equals("")) {
				consulta += " o.oper_id = :idOperacao and ";
				parameters.put("idOperacao", Integer.parseInt(helper
						.getIdOperacao()));
			}
			if (helper.getIdUnidadeSuperior() != null
					&& !helper.getIdUnidadeSuperior().equals("")) {
				consulta += " uo.unid_idsuperior = :idUnidadeSuperior and ";
				parameters.put("idUnidadeSuperior", Integer.parseInt(helper
						.getIdUnidadeSuperior()));
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUnidadeOrganizacional())) {
				consulta += " uo.unid_id in ( :idsUnidadeOrganizacional ) and ";
				parameters.put("idsUnidadeOrganizacional", helper
						.getColecaoUnidadeOrganizacional());
			}
			if (helper.getIdUsuario() != null
					&& !helper.getIdUsuario().equals("")) {
				consulta += " u.usur_id = :idUsuario and ";
				parameters.put("idUsuario", Integer.parseInt(helper
						.getIdUsuario()));
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY us.unid_dsunidade,uo.unid_dsunidade,ms.meso_dsmeiosolicitacao,u.usur_nmusuario";
			consulta += " ORDER BY us.unid_dsunidade,uo.unid_dsunidade,ms.meso_dsmeiosolicitacao,u.usur_nmusuario";

			query = session.createSQLQuery(consulta).addScalar("unidSuperior",
					Hibernate.STRING).addScalar("unidOrganizacional",
					Hibernate.STRING).addScalar("usuario", Hibernate.STRING)
					.addScalar("meio", Hibernate.STRING).addScalar("contador",
							Hibernate.INTEGER).setInteger("idColuna",
							Integer.parseInt(helper.getIdColuna()))
					.setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
	 * 
	 * @author Hugo Amorim
	 * @date 08/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(
			GerarRelatorioAlteracoesSistemaColunaHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT g.greg_nmregional as gerencia,\n "
					+ "  un.uneg_nmunidadenegocio as unidade,\n "
					+ "   l.loca_nmlocalidade as localidade,\n  "
					+ "   ms.meso_dsmeiosolicitacao as meio,\n "
					+ "  count(*) as contador \n"
					+ "  FROM seguranca.tab_linha_col_alteracao tbco \n"
					+ "   INNER JOIN seguranca.tabela_coluna tc on tc.tbco_id = tbco.tbco_id \n"
					+ "   INNER JOIN seguranca.tabela_linha_alteracao tla on tla.tbla_id = tbco.tbla_id \n"
					+ "   INNER JOIN seguranca.operacao_efetuada oe on oe.opef_id = tla.tref_id \n"
					+ "  INNER JOIN seguranca.operacao o on o.oper_id = oe.oper_id \n"
					+ "   INNER JOIN seguranca.usuario_alteracao ua on ua.tref_id = oe.opef_id \n"
					+ "   INNER JOIN seguranca.usuario u on u.usur_id = ua.usis_id \n"
					+ "   INNER JOIN cadastro.imovel ic on ic.imov_id = tla.tbla_id1 \n"
					+ "   LEFT JOIN cadastro.imovel i on i.imov_id = tla.tbla_id2 \n"
					+ "  INNER JOIN cadastro.localidade l on l.loca_id = ic.loca_id  \n"
					+ "   INNER JOIN cadastro.unidade_negocio un on un.uneg_id = l.uneg_id \n"
					+ "   INNER JOIN cadastro.gerencia_regional g on g.greg_id = un.greg_id \n"
					+ "   INNER JOIN cadastro.unidade_organizacional uo on uo.loca_id = l.loca_id \n"
					+ "   INNER JOIN atendimentopublico.meio_solicitacao ms on ms.meso_id = uo.meso_id \n"
					+ " WHERE tbco.tbco_id = :idColuna \n"
					+ " and to_date(to_char(tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoInicial and :periodoFinal \n"
					+ " and ";

			if (helper.getIdMeioSolicitacao() != null
					&& !helper.getIdMeioSolicitacao().equals("-1")
					&& !helper.getIdMeioSolicitacao().equals("0")) {
				consulta += " uo.meso_id = :meioSolicitacao and ";
				parameters.put("meioSolicitacao", Integer.parseInt(helper
						.getIdMeioSolicitacao()));
			}
			if (helper.getIdFuncionalidade() != null
					&& !helper.getIdFuncionalidade().equals("")) {
				consulta += " o.fncd_id = :idFuncionalidade and ";
				parameters.put("idFuncionalidade", Integer.parseInt(helper
						.getIdFuncionalidade()));
			}
			if (helper.getIdOperacao() != null
					&& !helper.getIdOperacao().equals("")) {
				consulta += " o.oper_id = :idOperacao and ";
				parameters.put("idOperacao", Integer.parseInt(helper
						.getIdOperacao()));
			}
			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("")) {
				consulta += " g.greg_id = :idGerenciaRegional and ";
				parameters.put("idGerenciaRegional", Integer.parseInt(helper
						.getIdGerenciaRegional()));
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("")) {
				consulta += " un.uneg_id = :idUnidadeNegocio and ";
				parameters.put("idUnidadeNegocio", Integer.parseInt(helper
						.getIdUnidadeNegocio()));
			}
			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equals("")) {
				consulta += " l.loca_id = :idLocalidade and ";
				parameters.put("idLocalidade", Integer.parseInt(helper
						.getIdLocalidade()));
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY g.greg_nmregional,un.uneg_nmunidadenegocio,l.loca_nmlocalidade,ms.meso_dsmeiosolicitacao \n";
			consulta += " ORDER BY g.greg_nmregional,un.uneg_nmunidadenegocio,l.loca_nmlocalidade,ms.meso_dsmeiosolicitacao";

			query = session.createSQLQuery(consulta).addScalar("gerencia",
					Hibernate.STRING).addScalar("unidade", Hibernate.STRING)
					.addScalar("localidade", Hibernate.STRING).addScalar(
							"meio", Hibernate.STRING).addScalar("contador",
							Hibernate.INTEGER).setInteger("idColuna",
							Integer.parseInt(helper.getIdColuna()))
					.setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
	 * 
	 * [FS0007]
	 * 
	 * @author Hugo Amorim
	 * @date 08/09/2010
	 */
	public boolean verificarRelacaoColuna(Integer idColuna)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retornoConsulta = null;
		boolean retorno = false;
		try {
			String consulta = "SELECT tbco_id as coluna"
					+ " FROM seguranca.tabela_coluna"
					+ " WHERE tabe_id = (SELECT tabe_id"
					+ " FROM seguranca.tabela_coluna WHERE tbco_id  = :idColuna)"
					+ " and (tbco_nmcoluna like 'clie_id' or tbco_nmcoluna like 'imov_id')";

			retornoConsulta = (Integer) session.createSQLQuery(consulta)
					.addScalar("coluna", Hibernate.INTEGER).setInteger(
							"idColuna", idColuna).setMaxResults(1)
					.uniqueResult();

			if (retornoConsulta != null) {
				retorno = true;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
	 * 
	 * @author Daniel Alves
	 * @date 28/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			consulta = " SELECT imovel.id,"
					+ " localidade.id, "
					+ " emailClienteAlterado.nomeClienteAnterior, "
					+ " emailClienteAlterado.nomeClienteAtual, "
					+ " emailClienteAlterado.cpfAnterior, "
					+ " emailClienteAlterado.cpfAtual, "
					+ " emailClienteAlterado.cnpjAnterior, "
					+ " emailClienteAlterado.cnpjAtual, "
					+ " emailClienteAlterado.emailAnterior, "
					+ " emailClienteAlterado.emailAtual, "
					+ " emailClienteAlterado.confirmacaoOnline, "
					+ " emailClienteAlterado.nomeSolicitante, "
					+ " emailClienteAlterado.cpfSolicitante, "
					+ " emailClienteAlterado.telefoneContato, "
					+ " unidadeNegocio.nome, "
					+ " gerenciaRegional.nome, "
					+ " emailClienteAlterado.confirmacaoOnline, "
					+ " emailClienteAlterado.idCliente.id "
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " and clienteImovel.clienteRelacaoTipo.id = 2 and clienteImovel.dataFimRelacao is null "
					+ " and imovel.imovelContaEnvio = :idImovelContaEnvio ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();
			}

			consulta += " order by emailClienteAlterado.confirmacaoOnline,gerenciaRegional.id,unidadeNegocio.id,localidade.id ";

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59")).setInteger("idImovelContaEnvio",
					ImovelContaEnvio.ENVIAR_PARA_IMOVEL_E_PARA_EMAIL);

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
	 * 
	 * @author Daniel Alves
	 * @date 28/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			consulta = " SELECT SUM(CASE WHEN emailClienteAlterado.nomeClienteAtual != nvl(emailClienteAlterado.nomeClienteAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.cpfAtual != nvl(emailClienteAlterado.cpfAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.cnpjAtual != nvl(emailClienteAlterado.cnpjAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.emailAtual != nvl(emailClienteAlterado.emailAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " COUNT(distinct cliente.id)"
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " and clienteImovel.clienteRelacaoTipo = 2 and clienteImovel.dataFimRelacao is null ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();

			}

			// consulta+=" GROUP BY unidadeNegocio.nome,gerenciaRegional.nome";

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59"));

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
	 * 
	 * @author Hugo Amorim de Lyra
	 * @date 06/10/2010
	 */
	public Integer countRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Integer retorno = new Integer(0);
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		// Date dataInicio =
		// Util.converteStringParaDate(helper.getPeriodoReferenciaInicial());
		// Date dataFim =
		// Util.converteStringParaDate(helper.getPeriodoReferenciaFinal());

		try {
			consulta = " SELECT count(*) "
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " AND clienteImovel.clienteRelacaoTipo.id = 2 and clienteImovel.dataFimRelacao is null ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();

			}

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59"));

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 * 
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select iia "
					+ " from ImovelInscricaoAlterada iia "
					+ " inner join fetch iia.imovel imov "
					+ " inner join fetch imov.clienteImoveis cliImo "
					+ " inner join fetch cliImo.cliente clie "
					+ " inner join iia.localidadeAtual locaAtual "
					+ " inner join fetch iia.setorComercialAtual setorAtual "
					+ " inner join fetch iia.quadraAtual quadraAtual "
					+ " inner join quadraAtual.rota rotaAtual "
					+ " inner join iia.localidadeAnterior locaAnterior "
					+ " inner join fetch iia.setorComercialAnterior setorAnterior "
					+ " inner join fetch iia.quadraAnterior quadraAnterior "
					+ " where iia.indicadorAtualizacaoExcluida = 2 "
					+ " and cliImo.clienteRelacaoTipo = 2 "
					+ " and cliImo.dataFimRelacao is null ";
					

			parameters.put("dataInicial", relatorioHelper.getDataInicio());
			parameters.put("dataFinal", relatorioHelper.getDataFim());

			// Tipo da Consulta

			// Imóveis alterados com sucesso.
			if (relatorioHelper.getEscolhaRelatorio().intValue() == 1) {

				consulta += " and iia.indicadorAtualizado = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóveis sem alteração devido a erro.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 2) {

				consulta += " and iia.indicadorErroAlteracao = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóvel pendente de alteração.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 3) {

				consulta += " and iia.indicadorAtualizado = 2 and iia.indicadorErroAlteracao is null "
						  + " and iia.ultimaAlteracao between :dataInicial and :dataFinal ";
			}

			
			if(relatorioHelper.getIndicadorInscricaoAtualAnterior() != null &&
					relatorioHelper.getIndicadorInscricaoAtualAnterior().equals(ConstantesSistema.NAO)){
				//pesquisa por inscrição ANTERIOR
				
				// Localidade
				if (relatorioHelper.getLocalidadeInicial() != null && relatorioHelper.getLocalidadeFinal() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());
					parameters.put("localidadeFinal", relatorioHelper.getLocalidadeFinal());

					consulta += " and iia.localidadeAnterior between :localidadeInicial and :localidadeFinal ";
					
				} else if (relatorioHelper.getLocalidadeInicial() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());

					consulta += " and iia.localidadeAnterior =:localidadeInicial ";
				}

				// Setor Comercial
				if (relatorioHelper.getSetorComercialInicial() != null && relatorioHelper.getSetorComercialFinal() != null) {
					
					parameters.put("setoComercialInicial", relatorioHelper.getCodigoSetorComercialInicial());
					parameters.put("setoComercialFinal", relatorioHelper.getCodigoSetorComercialFinal());

					consulta += " and setorAnterior.codigo between :setoComercialInicial and :setoComercialFinal ";
					
					
				} else if (relatorioHelper.getSetorComercialInicial() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getSetorComercialInicial());

					consulta += " and iia.setorComercialAnterior =:setoComercialInicial ";
				}

				// Quadra
				if (relatorioHelper.getQuadraInicial() != null && relatorioHelper.getQuadraFinal() != null) {

					parameters.put("quadraInicial", relatorioHelper.getNumeroQuadraInicial());
					parameters.put("quadraFinal", relatorioHelper.getNumeroQuadraFinal());

					consulta += " and quadraAnterior.numeroQuadra between :quadraInicial and :quadraFinal ";
					
					
					
				} else if (relatorioHelper.getQuadraInicial() != null) {

					parameters.put("quadraInicial", relatorioHelper.getQuadraInicial());

					consulta += " and iia.quadraAnterior =:quadraInicial ";
				}

				// Lote
				if (relatorioHelper.getLoteInicial() != null && relatorioHelper.getLoteFinal() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());
					parameters.put("loteFinal", relatorioHelper.getLoteFinal());

					consulta += " and iia.loteAnterior between :loteInicial and :loteFinal ";
				} else if (relatorioHelper.getLoteInicial() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());

					consulta += " and iia.loteAnterior =:loteInicial ";
				}

				// Sub-Lote
				if (relatorioHelper.getSubLoteInicial() != null	&& relatorioHelper.getSubLoteFinal() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());
					parameters.put("subLoteFinal", relatorioHelper.getSubLoteFinal());

					consulta += " and iia.subLoteAnterior between :subLoteInicial and :subLoteFinal ";
					
				} else if (relatorioHelper.getSubLoteInicial() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());

					consulta += " and iia.subLoteAnterior =:subLoteInicial ";
				}

				
			}else{
				//pesquisa por inscrição ATUAL
				
				// Localidade
				if (relatorioHelper.getLocalidadeInicial() != null && relatorioHelper.getLocalidadeFinal() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());
					parameters.put("localidadeFinal", relatorioHelper.getLocalidadeFinal());

					consulta += " and iia.localidadeAtual between :localidadeInicial and :localidadeFinal ";
					
				} else if (relatorioHelper.getLocalidadeInicial() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());

					consulta += " and iia.localidadeAtual =:localidadeInicial ";
				}

				// Setor Comercial
				if (relatorioHelper.getSetorComercialInicial() != null && relatorioHelper.getSetorComercialFinal() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getCodigoSetorComercialInicial());
					parameters.put("setoComercialFinal", relatorioHelper.getCodigoSetorComercialFinal());

					consulta += " and setorAtual.codigo between :setoComercialInicial and :setoComercialFinal ";
					
				} else if (relatorioHelper.getSetorComercialInicial() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getSetorComercialInicial());

					consulta += " and iia.setorComercialAtual =:setoComercialInicial ";
				}

				// Quadra
				if (relatorioHelper.getQuadraInicial() != null && relatorioHelper.getQuadraFinal() != null) {

					parameters.put("quadraInicial", relatorioHelper.getNumeroQuadraInicial());
					parameters.put("quadraFinal", relatorioHelper.getNumeroQuadraFinal());

					consulta += " and quadraAtual.numeroQuadra between :quadraInicial and :quadraFinal ";
					
				} else if (relatorioHelper.getQuadraInicial() != null) {

					parameters.put("quadraInicial", relatorioHelper.getQuadraInicial());

					consulta += " and iia.quadraAtual =:quadraInicial ";
				}

				// Lote
				if (relatorioHelper.getLoteInicial() != null && relatorioHelper.getLoteFinal() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());
					parameters.put("loteFinal", relatorioHelper.getLoteFinal());

					consulta += " and iia.loteAtual between :loteInicial and :loteFinal ";
				} else if (relatorioHelper.getLoteInicial() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());

					consulta += " and iia.loteAtual =:loteInicial ";
				}

				// Sub-Lote
				if (relatorioHelper.getSubLoteInicial() != null	&& relatorioHelper.getSubLoteFinal() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());
					parameters.put("subLoteFinal", relatorioHelper.getSubLoteFinal());

					consulta += " and iia.subLoteAtual between :subLoteInicial and :subLoteFinal ";
					
				} else if (relatorioHelper.getSubLoteInicial() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());

					consulta += " and iia.subLoteAtual =:subLoteInicial ";
				}

			}
			
			if(relatorioHelper.getOrigemAtualizacao() != null){
				
				if(relatorioHelper.getOrigemAtualizacao().equals("1")){
					
					parameters.put("origemAtualizacao", new Integer("4"));

					consulta += " and iia.codigoOrigem = :origemAtualizacao ";
					
				}else 	if(relatorioHelper.getOrigemAtualizacao().equals("2")){
					
					parameters.put("origemAtualizacao", new Integer("4"));

					consulta += " and iia.codigoOrigem <> :origemAtualizacao ";
					
				}
			}
			
			consulta += " order by locaAtual.id, setorAtual.codigo, "
					+ " rotaAtual.codigo, imov.numeroSequencialRota, quadraAtual.numeroQuadra, "
					+ " iia.loteAtual, iia.subLoteAtual ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 * 
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ErroRepositorioException {

		Integer retorno = new Integer(0);

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " SELECT count(iia.id) "
					+ " from ImovelInscricaoAlterada iia "
					+ " inner join iia.imovel imov "
					+ " inner join imov.clienteImoveis cliImo "
					+ " inner join cliImo.cliente clie "
					+ " inner join iia.localidadeAtual locaAtual "
					+ " inner join iia.setorComercialAtual setorAtual "
					+ " inner join iia.quadraAtual quadraAtual "
					+ " inner join iia.localidadeAnterior locaAnterior "
					+ " inner join iia.setorComercialAnterior setorAnterior "
					+ " inner join iia.quadraAnterior quadraAnterior "
					+ " where iia.indicadorAtualizacaoExcluida = 2 "
					+ " and cliImo.clienteRelacaoTipo = 2 "
					+ " and cliImo.dataFimRelacao is null ";

			parameters.put("dataInicial", relatorioHelper.getDataInicio());
			parameters.put("dataFinal", relatorioHelper.getDataFim());

			// Tipo da Consulta

			// Imóveis alterados com sucesso.
			if (relatorioHelper.getEscolhaRelatorio().intValue() == 1) {

				consulta += " and iia.indicadorAtualizado = 1 "
					      + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóveis sem alteração devido a erro.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 2) {

				consulta += " and iia.indicadorErroAlteracao = 1 "
					      + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóvel pendente de alteração.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 3) {

				consulta += " and iia.indicadorAtualizado = 2 and iia.indicadorErroAlteracao is null "
					      + " and iia.ultimaAlteracao between :dataInicial and :dataFinal ";
			}

			if(relatorioHelper.getIndicadorInscricaoAtualAnterior() != null &&
					relatorioHelper.getIndicadorInscricaoAtualAnterior().equals(ConstantesSistema.NAO)){
				//pesquisa por inscrição ANTERIOR
				
				// Localidade
				if (relatorioHelper.getLocalidadeInicial() != null && relatorioHelper.getLocalidadeFinal() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());
					parameters.put("localidadeFinal", relatorioHelper.getLocalidadeFinal());

					consulta += " and iia.localidadeAnterior between :localidadeInicial and :localidadeFinal ";
					
				} else if (relatorioHelper.getLocalidadeInicial() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());

					consulta += " and iia.localidadeAnterior =:localidadeInicial ";
				}

				// Setor Comercial
				if (relatorioHelper.getSetorComercialInicial() != null && relatorioHelper.getSetorComercialFinal() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getCodigoSetorComercialInicial());
					parameters.put("setoComercialFinal", relatorioHelper.getCodigoSetorComercialFinal());

					consulta += " and setorAnterior.codigo between :setoComercialInicial and :setoComercialFinal ";
					
					
				} else if (relatorioHelper.getSetorComercialInicial() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getSetorComercialInicial());

					consulta += " and iia.setorComercialAnterior =:setoComercialInicial ";
				}

				// Quadra
				if (relatorioHelper.getQuadraInicial() != null && relatorioHelper.getQuadraFinal() != null) {

					parameters.put("quadraInicial", relatorioHelper.getNumeroQuadraInicial());
					parameters.put("quadraFinal", relatorioHelper.getNumeroQuadraFinal());

					consulta += " and quadraAnterior.numeroQuadra between :quadraInicial and :quadraFinal ";

					
				} else if (relatorioHelper.getQuadraInicial() != null) {

					parameters.put("quadraInicial", relatorioHelper.getQuadraInicial());

					consulta += " and iia.quadraAnterior =:quadraInicial ";
				}

				// Lote
				if (relatorioHelper.getLoteInicial() != null && relatorioHelper.getLoteFinal() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());
					parameters.put("loteFinal", relatorioHelper.getLoteFinal());

					consulta += " and iia.loteAnterior between :loteInicial and :loteFinal ";
				} else if (relatorioHelper.getLoteInicial() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());

					consulta += " and iia.loteAnterior =:loteInicial ";
				}

				// Sub-Lote
				if (relatorioHelper.getSubLoteInicial() != null	&& relatorioHelper.getSubLoteFinal() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());
					parameters.put("subLoteFinal", relatorioHelper.getSubLoteFinal());

					consulta += " and iia.subLoteAnterior between :subLoteInicial and :subLoteFinal ";
					
				} else if (relatorioHelper.getSubLoteInicial() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());

					consulta += " and iia.subLoteAnterior =:subLoteInicial ";
				}

				
			}else{
				//pesquisa por inscrição ATUAL
				
				// Localidade
				if (relatorioHelper.getLocalidadeInicial() != null && relatorioHelper.getLocalidadeFinal() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());
					parameters.put("localidadeFinal", relatorioHelper.getLocalidadeFinal());

					consulta += " and iia.localidadeAtual between :localidadeInicial and :localidadeFinal ";
					
				} else if (relatorioHelper.getLocalidadeInicial() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());

					consulta += " and iia.localidadeAtual =:localidadeInicial ";
				}

				// Setor Comercial
				if (relatorioHelper.getSetorComercialInicial() != null && relatorioHelper.getSetorComercialFinal() != null) {
					
					parameters.put("setoComercialInicial", relatorioHelper.getCodigoSetorComercialInicial());
					parameters.put("setoComercialFinal", relatorioHelper.getCodigoSetorComercialFinal());

					consulta += " and setorAtual.codigo between :setoComercialInicial and :setoComercialFinal ";

					
				} else if (relatorioHelper.getSetorComercialInicial() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getSetorComercialInicial());

					consulta += " and iia.setorComercialAtual =:setoComercialInicial ";
				}

				// Quadra
				if (relatorioHelper.getQuadraInicial() != null && relatorioHelper.getQuadraFinal() != null) {

					parameters.put("quadraInicial", relatorioHelper.getNumeroQuadraInicial());
					parameters.put("quadraFinal", relatorioHelper.getNumeroQuadraFinal());

					consulta += " and quadraAtual.numeroQuadra between :quadraInicial and :quadraFinal ";
					
				} else if (relatorioHelper.getQuadraInicial() != null) {

					parameters.put("quadraInicial", relatorioHelper.getQuadraInicial());

					consulta += " and iia.quadraAtual =:quadraInicial ";
				}

				// Lote
				if (relatorioHelper.getLoteInicial() != null && relatorioHelper.getLoteFinal() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());
					parameters.put("loteFinal", relatorioHelper.getLoteFinal());

					consulta += " and iia.loteAtual between :loteInicial and :loteFinal ";
				} else if (relatorioHelper.getLoteInicial() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());

					consulta += " and iia.loteAtual =:loteInicial ";
				}

				// Sub-Lote
				if (relatorioHelper.getSubLoteInicial() != null	&& relatorioHelper.getSubLoteFinal() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());
					parameters.put("subLoteFinal", relatorioHelper.getSubLoteFinal());

					consulta += " and iia.subLoteAtual between :subLoteInicial and :subLoteFinal ";
					
				} else if (relatorioHelper.getSubLoteInicial() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());

					consulta += " and iia.subLoteAtual =:subLoteInicial ";
				}

			}
			
			if(relatorioHelper.getOrigemAtualizacao() != null){
				
				if(relatorioHelper.getOrigemAtualizacao().equals("1")){
					
					parameters.put("origemAtualizacao", new Integer("4"));

					consulta += " and iia.codigoOrigem = :origemAtualizacao ";
					
				}else 	if(relatorioHelper.getOrigemAtualizacao().equals("2")){
					
					parameters.put("origemAtualizacao", new Integer("4"));

					consulta += " and iia.codigoOrigem <> :origemAtualizacao ";
					
				}
			}

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Usuário
	 * 
	 * @author Mariana Victor
	 * @date 16/02/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT usur.usur_nmusuario AS nome, usur.usur_nmlogin AS login, "
					+ " unid.unid_dsunidade AS unidade, meso.meso_dsmeiosolicitacao AS meio, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) contadorCnpj, "
					+ " count(*) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN atendimentopublico.meio_solicitacao meso ON unid.meso_id = meso.meso_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ "  AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ "  between :periodoInicial and :periodoFinal" + " and ";

			if (helper.getIdUnidadeSuperior() != null
					&& !helper.getIdUnidadeSuperior().equals("")) {
				consulta += " unid.unid_idsuperior = :idUnidadeSuperior and ";
				parameters.put("idUnidadeSuperior", Integer.parseInt(helper
						.getIdUnidadeSuperior()));
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUnidadeOrganizacional())) {
				consulta += " unid.unid_id in ( :idsUnidadeOrganizacional ) and ";
				parameters.put("idsUnidadeOrganizacional", helper
						.getColecaoUnidadeOrganizacional());
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUsuario())) {
				consulta += " usur.usur_id in ( :idsUsuario ) and ";
				parameters.put("idsUsuario", helper.getColecaoUsuario());
			}
			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " meso.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY usur.usur_nmusuario, usur.usur_nmlogin, unid.unid_dsunidade, meso.meso_dsmeiosolicitacao";

			consulta += " ORDER BY usur.usur_nmusuario, usur.usur_nmlogin, unid.unid_dsunidade, meso.meso_dsmeiosolicitacao";

			query = session.createSQLQuery(consulta).addScalar("nome",
					Hibernate.STRING).addScalar("login", Hibernate.STRING)
					.addScalar("unidade", Hibernate.STRING).addScalar("meio",
							Hibernate.STRING).addScalar("contadorCpf",
							Hibernate.DOUBLE).addScalar("contadorCnpj",
							Hibernate.DOUBLE).addScalar("contador",
							Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Localidade
	 * 
	 * @author Mariana Victor
	 * @date 17/02/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT greg.greg_nmregional AS gerenciaRegional, uneg.uneg_nmunidadenegocio AS unidadeNegocio, "
					+ " loca.loca_nmlocalidade AS localidade, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) contadorCnpj, "
					+ " count(tbca.tbca_id) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON  usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN cadastro.imovel imov ON imov.imov_id = tbla.tbla_id2 "
					+ " INNER JOIN cadastro.localidade loca ON loca.loca_id = imov.loca_id "
					+ " INNER JOIN cadastro.unidade_negocio uneg ON loca.uneg_id = uneg.uneg_id "
					+ " INNER JOIN cadastro.gerencia_regional greg ON greg.greg_id = loca.greg_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ " AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ " between :periodoInicial and :periodoFinal" + " and ";

			if (helper.getOpcaoTotalizacao().equals("gerenciaRegional")
					&& helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("")) {
				consulta += " greg.greg_id = :idGerenciaRegional and ";
				parameters.put("idGerenciaRegional", Integer.parseInt(helper
						.getIdGerenciaRegional()));
			} else if (helper.getOpcaoTotalizacao().equals(
					"gerenciaRegionalLocalidade")
					&& helper.getIdGerenciaRegionalPorLocalidade() != null
					&& !helper.getIdGerenciaRegionalPorLocalidade().equals("")) {
				consulta += " greg.greg_id = :idGerenciaRegionalLocalidade and ";
				parameters.put("idGerenciaRegionalLocalidade", Integer
						.parseInt(helper.getIdGerenciaRegionalPorLocalidade()));
			} else if (helper.getOpcaoTotalizacao().equals("unidadeNegocio")
					&& helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("")) {
				consulta += " uneg.uneg_id = :idUnidadeNegocio and ";
				parameters.put("idUnidadeNegocio", Integer.parseInt(helper
						.getIdUnidadeNegocio()));
			} else if (helper.getOpcaoTotalizacao().equals("localidade")
					&& helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equals("")) {
				consulta += " loca.loca_id = :idLocalidade and ";
				parameters.put("idLocalidade", Integer.parseInt(helper
						.getIdLocalidade()));
			}
			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " unid.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY greg.greg_nmregional, uneg.uneg_nmunidadenegocio, loca.loca_nmlocalidade ";
			consulta += " ORDER BY greg.greg_nmregional, uneg.uneg_nmunidadenegocio, loca.loca_nmlocalidade ";

			query = session.createSQLQuery(consulta).addScalar(
					"gerenciaRegional", Hibernate.STRING).addScalar(
					"unidadeNegocio", Hibernate.STRING).addScalar("localidade",
					Hibernate.STRING)
					.addScalar("contadorCpf", Hibernate.DOUBLE).addScalar(
							"contadorCnpj", Hibernate.DOUBLE).addScalar(
							"contador", Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Meio de
	 * Solicitação
	 * 
	 * @author Mariana Victor
	 * @date 16/02/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT meso.meso_dsmeiosolicitacao AS meio, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) AS contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) AS contadorCnpj, "
					+ " count(tbca.tbca_id) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON  usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN atendimentopublico.meio_solicitacao meso ON unid.meso_id = meso.meso_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ "  AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ "  between :periodoInicial and :periodoFinal" + " and ";

			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " meso.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY meso.meso_dsmeiosolicitacao ";
			consulta += " ORDER BY meso.meso_dsmeiosolicitacao ";

			query = session.createSQLQuery(consulta).addScalar("meio",
					Hibernate.STRING)
					.addScalar("contadorCpf", Hibernate.DOUBLE).addScalar(
							"contadorCnpj", Hibernate.DOUBLE).addScalar(
							"contador", Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * UC1162 AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * 
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(
			ImovelInscricaoAlteradaHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		Integer idLocalidade = helper.getIdLocalidade();
		ArrayList<Integer> colecaoIdSetorComercial = helper.getColecaoIdSetorComercial();
		ArrayList<Integer> colecaoIdQuadra =helper.getColecaoIdQuadra();
		String consulta = "";
		
		try {
			consulta = "SELECT count(distinct(imia.imov_id)) as qtdImoveis, "
					+ " imia.qdra_idatual as idQuadra, "
					+ " stcm.stcm_id as idSetor "
					+ " FROM cadastro.imovel_inscr_alterada imia "
					+ " INNER JOIN cadastro.setor_comercial stcm ON stcm.stcm_id = imia.stcm_idatual "
					+ " WHERE imia.imia_icatualizado = :indicadorAtualizado "
					+ " AND imia.imia_icalteracaoexcluida = :alteracaoExcluida "
					+ " AND imia.imia_icerroalteracao is null "
					+ " AND imia.imia_icautorizado = :indicadorAutorizado "
					+ " AND imia.loca_idatual = :idLocalidade ";
			
					if (!Util.isVazioOrNulo(colecaoIdSetorComercial)) {
						consulta += " AND stcm.stcm_id in ( :colecaoIdSetorComercial ) ";
						
						if(!Util.isVazioOrNulo(colecaoIdQuadra)){
							consulta += " AND imia.qdra_idatual in ( :colecaoIdQuadra ) ";
						}
					}
					
					if ( helper.getIndicadorImovelAlteradoExcluido() != null && !helper.getIndicadorImovelAlteradoExcluido().equals("")) {
						consulta = consulta + " AND imia.imia_icimovelexcluido = " + helper.getIndicadorImovelAlteradoExcluido();
					}
					
					if ( helper.getIndicadorOrigemAtualizacao() != null && !helper.getIndicadorOrigemAtualizacao().equals("")) {
						if(helper.getIndicadorOrigemAtualizacao().equals("1")){
							consulta = consulta + " AND imia.imia_cdorigem = " + ImovelInscricaoAlterada.MANTER_IMOVEL;
						}else{
							consulta = consulta + " AND imia.imia_cdorigem <> " + ImovelInscricaoAlterada.MANTER_IMOVEL;
						}
					}
					
					consulta = consulta
					+ " GROUP BY stcm.stcm_id, imia.qdra_idatual "
					+ " ORDER BY stcm.stcm_id, imia.qdra_idatual, qtdImoveis";

			if(!Util.isVazioOrNulo(colecaoIdQuadra)){
				retorno = session.createSQLQuery(consulta)
						.addScalar("qtdImoveis",Hibernate.INTEGER)
						.addScalar("idQuadra", Hibernate.INTEGER)
						.addScalar("idSetor", Hibernate.INTEGER)
						.setInteger("idLocalidade", idLocalidade)
						.setParameterList("colecaoIdSetorComercial", colecaoIdSetorComercial)
						.setParameterList("colecaoIdQuadra", colecaoIdQuadra)
						.setShort("indicadorAtualizado",ConstantesSistema.INDICADOR_USO_DESATIVO)
						.setShort("alteracaoExcluida",ConstantesSistema.INDICADOR_USO_DESATIVO)
						.setShort("indicadorAutorizado",ConstantesSistema.INDICADOR_USO_DESATIVO).list();
			}else{
				retorno = session.createSQLQuery(consulta)
						.addScalar("qtdImoveis",Hibernate.INTEGER)
						.addScalar("idQuadra", Hibernate.INTEGER)
						.addScalar("idSetor", Hibernate.INTEGER)
						.setInteger("idLocalidade", idLocalidade)
						.setParameterList("colecaoIdSetorComercial", colecaoIdSetorComercial)
						.setShort("indicadorAtualizado",ConstantesSistema.INDICADOR_USO_DESATIVO)
						.setShort("alteracaoExcluida",ConstantesSistema.INDICADOR_USO_DESATIVO)
						.setShort("indicadorAutorizado",ConstantesSistema.INDICADOR_USO_DESATIVO).list();
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * UC1162 AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * 
	 * @author Anderson Cabral
	 * @date 22/07/2013
	 */
	public Collection pesquisaImovelInscricaoAlteradaPorImoveis(
			ImovelInscricaoAlteradaHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		Integer idLocalidade = helper.getIdLocalidade();
		ArrayList<Integer> colecaoMatriculasImovel = helper.getColecaoMatriculas();
		String consulta = "";
		
		try {
			consulta = "SELECT count(distinct(imia.imov_id)) as qtdImoveis, "
					+ " imia.qdra_idatual as idQuadra, "
					+ " stcm.stcm_id as idSetor "
					+ " FROM cadastro.imovel_inscr_alterada imia "
					+ " INNER JOIN cadastro.setor_comercial stcm ON stcm.stcm_id = imia.stcm_idatual "
					+ " WHERE imia.imia_icatualizado = :indicadorAtualizado "
					+ " AND imia.imia_icalteracaoexcluida = :alteracaoExcluida "
					+ " AND imia.imia_icerroalteracao is null "
					+ " AND imia.imia_icautorizado = :indicadorAutorizado ";
			
					if (!Util.isVazioOrNulo(colecaoMatriculasImovel)) {
						consulta += " AND imia.imov_id in ( :colecaoMatriculasImovel ) ";
					}
					
					if ( helper.getIndicadorImovelAlteradoExcluido() != null ) {
						consulta = consulta + " AND imia.imia_icimovelexcluido = " + helper.getIndicadorImovelAlteradoExcluido();
					}
					
					consulta = consulta
					+ " GROUP BY stcm.stcm_id, imia.qdra_idatual "
					+ " ORDER BY stcm.stcm_id, imia.qdra_idatual, qtdImoveis";
					
			retorno = session.createSQLQuery(consulta)
					.addScalar("qtdImoveis",Hibernate.INTEGER)
					.addScalar("idQuadra", Hibernate.INTEGER)
					.addScalar("idSetor", Hibernate.INTEGER)
					.setParameterList("colecaoMatriculasImovel", colecaoMatriculasImovel)
					.setShort("indicadorAtualizado",ConstantesSistema.INDICADOR_USO_DESATIVO)
					.setShort("alteracaoExcluida",ConstantesSistema.INDICADOR_USO_DESATIVO)
					.setShort("indicadorAutorizado",ConstantesSistema.INDICADOR_USO_DESATIVO).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idGerenciaRegional", idGerenciaRegional).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(
			Integer idUnidadeNegocio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.id = :idUnidadeNegocio ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idUnidadeNegocio", idUnidadeNegocio).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.indicadorUso = :indicadorUso ";

			retorno = (Collection) session.createQuery(consulta).setShort(
					"indicadorUso", ConstantesSistema.SIM).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialMotivoCarta pesquisarTarifaSocialMotivoCarta(
			Integer idTarifaSocialMotivoCarta) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		TarifaSocialMotivoCarta retorno = null;

		try {

			String consulta = "SELECT tsmc "
					+ " FROM TarifaSocialMotivoCarta as tsmc "
					+ " WHERE tsmc.id = :idTarifaSocialMotivoCarta ";

			retorno = (TarifaSocialMotivoCarta) session.createQuery(consulta)
					.setInteger("idTarifaSocialMotivoCarta",
							idTarifaSocialMotivoCarta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(
			Integer idGerenciaRegional, Integer idUnidadeNegocio)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional and "
					+ " loca.unidadeNegocio.id = :idUnidadeNegocio";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idGerenciaRegional", idGerenciaRegional).setInteger(
					"idUnidadeNegocio", idUnidadeNegocio).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 * 
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcessoSPC(
			FiltrarRelatorioAcessoSPCHelper filtro)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		StringBuilder sb = new StringBuilder();
		Query query = null;

		try {

			sb.append(" SELECT ");
			sb.append(" usu.unidadeOrganizacional.id, "); // 0
			sb.append(" usu.unidadeOrganizacional.descricao, "); // 1
			sb.append(" receitaFederal.usuarioSolicitante.nomeUsuario, "); // 2
			sb.append(" receitaFederal.cpfCliente, "); // 3
			sb.append(" receitaFederal.cnpjCliente, "); // 4
			sb.append(" receitaFederal.nomeCliente, "); // 5
			sb.append(" receitaFederal.dataUltimaAlteracaoConsulta "); // 6
			sb.append(" FROM gcom.seguranca.ConsultarReceitaFederal receitaFederal ");
			sb.append(" INNER JOIN receitaFederal.usuarioSolicitante usu ");
			sb.append(" INNER JOIN usu.unidadeOrganizacional uni ");
			sb.append(" WHERE ");

			String joinWhere = "";
			if (filtro.getLoginUsuarioResponsavel() != null
					&& !filtro.getLoginUsuarioResponsavel().equals("")) {
				joinWhere = " usu.login = :loginUsuario AND ";
			}
			if (filtro.getIdUnidadaOrganizacional() != null
					&& filtro.getIdUnidadaOrganizacional() > 0) {
				joinWhere += " uni.id = :idUnidade AND ";
			}
			if (filtro.getReferenciaInicial() != null
					&& filtro.getReferenciaFinal() != null) {
				joinWhere += " receitaFederal.dataUltimaAlteracaoConsulta BETWEEN :dataInicio and :dataFim AND ";
			}
			joinWhere = Util.removerUltimosCaracteres(joinWhere, 4);
			sb.append(joinWhere);
			sb.append(" GROUP BY ");
			sb.append(" usu.unidadeOrganizacional.id, ");
			sb.append(" usu.unidadeOrganizacional.descricao, ");
			sb.append(" receitaFederal.dataUltimaAlteracaoConsulta, ");
			sb.append(" receitaFederal.cpfCliente, ");
			sb.append(" receitaFederal.cnpjCliente, ");
			sb.append(" receitaFederal.nomeCliente, ");
			sb.append(" receitaFederal.usuarioSolicitante.nomeUsuario ");

			sb.append(" ORDER BY ");
			sb.append("usu.unidadeOrganizacional.id, ");
			sb.append("receitaFederal.usuarioSolicitante.nomeUsuario ");

			query = session.createQuery(sb.toString());

			if (filtro.getLoginUsuarioResponsavel() != null
					&& !filtro.getLoginUsuarioResponsavel().equals("")) {
				query.setParameter("loginUsuario", filtro
						.getLoginUsuarioResponsavel());
			}
			if (filtro.getIdUnidadaOrganizacional() != null
					&& filtro.getIdUnidadaOrganizacional() > 0) {
				query.setParameter("idUnidade", filtro
						.getIdUnidadaOrganizacional());
			}
			if (filtro.getReferenciaInicial() != null
					&& filtro.getReferenciaFinal() != null) {
				query.setParameter("dataInicio", filtro.getReferenciaInicial());
				query.setParameter("dataFim", filtro.getReferenciaFinal());
			}
			retorno = (Collection<Object[]>) query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterCategorias() throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta = "";
		
		consulta = "SELECT cat.id, cat.descricao"
		         + " FROM Categoria cat";
		
		try{
			retorno = session.createQuery(consulta).list();
		}
		 catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}
		
		return retorno;
		
	}
	
	/**
     * Obtém a coleção de perfis dos imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterPerfisImoveis() throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta = "";
		
		consulta = "SELECT per.id,per.descricao"
			      +" FROM ImovelPerfil per"
			      +" WHERE per.indicadorUso = :indicador";
		
		try{
			retorno = session.createQuery(consulta)
							.setInteger("indicador",ConstantesSistema.INDICADOR_USO_ATIVO)
							.list();
		}
		 catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
		}		
		return retorno;
	}
	


	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarIdentificacaoUsuario(Integer idUsuario) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade " //0
				+ "  FROM seguranca.usuario "
				+ "  WHERE usur_id = :idUsuario ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idUsuario", idUsuario)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarUsuarioEmpresaComandoCobranca(Integer idUsuario, Integer idComando) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade "
				+ " FROM cadastro.unidade_organizacional unid "
				+ "   INNER JOIN cobranca.cmd_empr_cobr_conta cecc ON cecc.empr_id = unid.empr_id "
				+ "   INNER JOIN seguranca.usuario usur ON unid.unid_id = usur.unid_id "
				+ " WHERE usur.usur_id = :idUsuario "
				+ "   AND cecc.cecc_id = :idComando ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idUsuario", idUsuario)
				.setInteger("idComando", idComando)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Pesquisa o email da Empresa 
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2011
	 */
	public String pesquisarEmailEmpresa(Integer idEmpresa) throws ErroRepositorioException {

		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT empr_dsemail AS email "
				+ " FROM cadastro.empresa "
				+ " WHERE empr_id = :idEmpresa ";
			
			retorno = (String) session.createSQLQuery(consulta)
				.addScalar("email", Hibernate.STRING)
				.setInteger("idEmpresa", idEmpresa)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	/**
	 * [UC34] Importância Logradouro
	 * 
	 * Atualiza a Importância do Logradouro
	 * 
	 * @author Fernanda Almeida
	 * @data 30/06/2011
	 */
	
	public void atualizarGrauImportancia(LogradouroBairro logradouroBairro, Integer grauImportancia)
			throws ErroRepositorioException {

				Session session = HibernateUtil.getSession();
				try {
					String update = "update LogradouroBairro "
							+ "set ospc_id = :grauImportancia, lgbr_tmultimaalteracao = :dataAtual "
							+ "where lgbr_id = :idLogradouro";
				
					session.createQuery(update).setInteger("grauImportancia",
							grauImportancia).setInteger("idLogradouro",
									logradouroBairro.getId()).setTimestamp("dataAtual", new Date()).
									executeUpdate();
				
				} catch (HibernateException e) {
					// levanta a exceção para a próxima camada
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				} finally {
					// fecha a sessão
					HibernateUtil.closeSession(session);
				}
			}
	

	/**
	 * [MA2011061013]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idImovel
	 * 
	 * @return HidrometroMovimentado
	 * @throws ErroRepositorioException
	 */
	public List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		List<HidrometroInstalacaoHistorico> retorno = null;
		
		try {
			
			Criteria crit = session.createCriteria(HidrometroInstalacaoHistorico.class);
			crit.setFetchMode("ligacaoAgua", FetchMode.JOIN);
			crit.add(Restrictions.eq("ligacaoAgua.id",idImovel.intValue()));
			retorno = (List<HidrometroInstalacaoHistorico>) crit.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0588 / UC0589] Verifica existencia de DDD 
	 * 
	 * @author Nathalia Santos 
	 * @data 23/09/2011
	 */
	public Boolean verificarDdd(Short Ddd) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade "
				+ " FROM cadastro.municipio "
				+ " WHERE muni_cdddd = :Ddd ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setShort("Ddd", Ddd)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * [UC0588] Inserir Leiturista
	 * 
	 * @author Nathalia Santos
	 * @data 03/10/2011
	 */
	public Boolean pesquisarFuncionarioOuCliente(Integer IdFuncionario, Integer IdCliente) throws ErroRepositorioException { 
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = " SELECT  count(*) AS quantidade " 
					+ " FROM micromedicao.leiturista";
					
			if (IdFuncionario != null) {
				consulta = consulta
					+ " WHERE func_id = :IdFuncionario";
			} else {
				consulta = consulta
						+ "  WHERE clie_id = :IdCliente";
			} 
			
			Query query = session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER);
			
			if (IdFuncionario != null) {
				query.setInteger("IdFuncionario", IdFuncionario);
			} else {
				query.setInteger("IdCliente", IdCliente);
			} 
			
			retorno = (Integer) query
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Collection<Logradouro> pesquisarLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) 
			throws ErroRepositorioException{
		Collection<Logradouro> colLogradouros = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select log " +
					"from Logradouro log " +
					"where log.nome = :logradouroNome and " +
					"log.municipio.id = :idMunicipio";
					
			colLogradouros = session.createQuery(consulta)
					.setString("logradouroNome", logradouroNome)
					.setInteger("idMunicipio", idMunicipio)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
	
		if (!colLogradouros.isEmpty()){
			return colLogradouros;
		}else{
			return null;
		}
	}
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * Função utilizada para retornar os imóveis que contém o mesmo informado pelo usuário 
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Collection<Logradouro> filtrarLogradouroMesmoNome(String logradouroNome, Integer numeroPagina,
			Integer idMunicipio) 
			throws ErroRepositorioException{
		Collection<Logradouro> colLogradouros= null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select distinct log.id, log.nome, bairro.nome, muni.nome, cep.codigo " +
					"from Logradouro log " +
					"left join log.logradouroBairros logBai " +
					"left join logBai.bairro bairro " +
					"left join log.municipio muni " +
					"left join log.logradouroCeps logCep " +
					"left join logCep.cep cep " +
					"where log.nome = :logradouroNome and " +
					"log.municipio.id = :idMunicipio";
					
			colLogradouros = session.createQuery(consulta)
					.setString("logradouroNome", logradouroNome)
					.setInteger("idMunicipio", idMunicipio)
					.setFirstResult(10 * numeroPagina).setMaxResults(10)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return colLogradouros;
	}
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * Método usado para retornar a quantidade de logradouros com o mesmo nome
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) 
			throws ErroRepositorioException{
		Integer colLogradouros= null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select distinct count(*) " +
					"from Logradouro log " +
					"left join log.logradouroBairros logBai " +
					"left join logBai.bairro bairro " +
					"left join log.municipio muni " +
					"left join log.logradouroCeps logCep " +
					"left join logCep.cep cep " +
					"where log.nome = :logradouroNome and " +
					"log.municipio.id = :idMunicipio";
					
			colLogradouros = (Integer) session.createQuery(consulta)
					.setString("logradouroNome", logradouroNome)
					.setInteger("idMunicipio", idMunicipio)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return colLogradouros;
	}
	
	

	
	/**
	 * Método que pesquisa uma 
	 * EmpresaCobrancaFaixa pelo id
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */
	public EmpresaCobrancaFaixa pesquisarEmpresaCobrancaFaixa(Integer idCobrancaFaixa) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try{
			
			return (EmpresaCobrancaFaixa) session.createQuery("SELECT emcf FROM EmpresaCobrancaFaixa emcf WHERE emcf.id = :idCobFaixa")
					.setInteger("idCobFaixa", idCobrancaFaixa)
					.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Atualizar nome do usuario com id de funcionario igual ao informado 
	 * 
	 * @author Erivan Sousa
	 * @date 06/12/2011
	 * 
	 * @param idFuncionario
	 * @param nomeFuncionario
	 * 
	 * @throws ErroRepositorioException
	 */

	public void atualizarNomeUsuarioComIdFuncionario(Integer idFuncionario,	String nomeFuncionario)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE Usuario usur ");
		builder.append("SET usur.nomeUsuario = :nomeFuncionario ");
		builder.append("WHERE usur.funcionario.id = :funcId ");
		
		try{
			
			session.createQuery(builder.toString())
					.setString("nomeFuncionario", nomeFuncionario)
					.setInteger("funcId", idFuncionario)
					.executeUpdate();
			
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelCobrancaSituacao(Integer idImovel, Integer idCobrancaSituacao)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();		
		try{
			String sql = "UPDATE gcom.cadastro.imovel.ImovelCobrancaSituacao SET iscb_dtretiradacobranca = :dataAtual, " +
					" iscb_tmultimaalteracao = :dataAtual " +
					" WHERE imov_id = :idImovel AND iscb_dtretiradacobranca IS NULL AND cbst_id = :idCobrancaSituacao";
			
			session.createQuery(sql)
				.setInteger("idImovel", idImovel)
				.setDate("dataAtual", new Date())
				.setInteger("idCobrancaSituacao", idCobrancaSituacao)
				.executeUpdate();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelSituacao(Integer idImovel)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();		
		try{
			String sql = "UPDATE gcom.cadastro.imovel.Imovel SET cbst_id = null, imov_tmultimaalteracao = :dataAtual " +
					" WHERE imov_id = :idImovel ";
			session.createQuery(sql).setInteger("idImovel", idImovel).setDate("dataAtual", new Date()).executeUpdate();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	

	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param indicadorAtualizacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelAtualizacaoCadastral> pesquisarImovelAtualizacaoCadastral(Short indicadorAtualizacao, Short indicadorDadosRetorno, 
			Integer idLocalidade, int quantidadeRegistros ) throws ErroRepositorioException {
	
		Collection<ImovelAtualizacaoCadastral> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT imac"
				     + " FROM ImovelAtualizacaoCadastral imac" 				    				    
				     + " WHERE" 
				     + " imac.indicadorAtualizado = :indicadorAtualizacao "
				     + " and imac.indicadorDadosRetorno = :indicadorDadosRetorno"
				     + " and imac.idLocalidade = :idLocalidade"
				     + " and (imac.codigoSituacao <> :codigoSituacao or imac.codigoSituacao is null)"; 

			colecao = (Collection<ImovelAtualizacaoCadastral>)session.createQuery(consulta)
										.setShort("indicadorAtualizacao", indicadorAtualizacao)
										.setShort("indicadorDadosRetorno", indicadorDadosRetorno)
										.setInteger("idLocalidade", idLocalidade) 
										.setInteger("codigoSituacao", ImovelAtualizacaoCadastral.SITUACAO_RETORNADO_PARA_CAMPO)
										.setMaxResults(quantidadeRegistros)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	
	}
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0004] - Validar Atributo Economias
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterQuantidadeEconomiaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select sum(isac_qteconomia) quantidadeEconomias"//0 
					+ " from cadastro.imovel_subcatg_atlz_cad "
					+ " where imac_id = :idImovelAtualizacaoCadastral ";

			retorno =  (Short) session.createSQLQuery(consulta)
					.addScalar("quantidadeEconomias", Hibernate.SHORT)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0004] - Validar Atributo Economias
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterSomatorioQuantidadeEconomiaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select sum(isac_qteconomia) quantidadeEconomias"//0 
					+ " from cadastro.imovel_subcatg_atlz_cad "
					+ " where imac_id = :idImovelAtualizacaoCadastral ";

			retorno =  (Short) session.createSQLQuery(consulta)
					.addScalar("quantidadeEconomias", Hibernate.SHORT)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0008] - Validar Cliente usuario do imovel
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterCpfCnpjClienteUsuarioAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)
			throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select clac_nncpfcnpj cpfcnpj "//0 
					+  " from cadastro.cliente_atlz_cadastral "
					+  " where imac_id = :idImovelAtualizacaoCadastral "
					+  " and crtp_id = :clienteUsuario "
					+  " and clac_dtrelacaofim is null";

			retorno =  (String) session.createSQLQuery(consulta)
					.addScalar("cpfcnpj", Hibernate.STRING)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.setShort("clienteUsuario", ClienteRelacaoTipo.USUARIO)
					.setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0002] - Validar Atributo Categoria
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select catg_id categoria"//0 
					+ " from cadastro.imovel_subcatg_atlz_cad "
					+ " where imac_id = :idImovelAtualizacaoCadastral ";

			retorno =  (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0006] - Validar Atributo Situacao do Hidrometro
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @param medicaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometroAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral, Integer medicaoTipo)
			throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select hiac_nnhidrometro numeroHidrometro "//0 
					+  " from cadastro.hidrom_inst_hist_atl_cad "
					+  " where imac_id = :idImovelAtualizacaoCadastral "
					+  " and medt_id = :medicaoTipo ";

			retorno =  (String) session.createSQLQuery(consulta)
					.addScalar("numeroHidrometro", Hibernate.STRING)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.setInteger("medicaoTipo", medicaoTipo)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * 
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 07/03/2012
	 * @param idImovelAtualizacaoCadastral
	 * @param medicaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral> pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral,
			Integer medicaoTipo) throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral> hiac = null;

		// Query
		String consulta = "";

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT hiac " 
				+ " FROM HidrometroInstalacaoHistoricoAtualizacaoCadastral hiac "
				+ " LEFT JOIN FETCH hiac.hidrometroLocalInstalacao "
				+ " LEFT JOIN FETCH hiac.hidrometroProtecao "
				+ " LEFT JOIN FETCH hiac.imovelAtualizacaoCadastral "
				+ " LEFT JOIN FETCH hiac.medicaoTipo "
				+ " WHERE hiac.imovelAtualizacaoCadastral = :idImovelAtualizacaoCadastral";
				
				if ( medicaoTipo != null ) {
					consulta += " and hiac.medicaoTipo =" + medicaoTipo;
				}

			hiac = (Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral>) session.createQuery(consulta)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o imóvel
		return hiac;
	}

	

	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * [SB0002 - Atualizar Imóvel/Ligação de Água]
	 * 
	 * @author Arthur Carvalho
	 * @since 08/03/2012
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovel(Integer idImovel, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String update;

		try {

			update = "update gcom.cadastro.imovel.Imovel set "
					+ "hidi_id = :hidrometroInstalacaoHistorico, " 
					+ "imov_tmultimaalteracao = :dataAtual "
					+ "where imov_id = :imovelId ";

			session.createQuery(update)
			.setInteger("imovelId",idImovel)
			.setInteger("hidrometroInstalacaoHistorico", idHidrometroInstalacaoHistorico)
			.setDate("dataAtual", new Date()).executeUpdate();


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * [SB0002 - Atualizar Imóvel/Ligação de Água]
	 * 
	 * @author Arthur Carvalho
	 * @since 08/03/2012
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoAgua(Integer idImovel, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String update;

		try {

			update = "update gcom.atendimentopublico.ligacaoagua.LigacaoAgua set "
					+ "hidi_id = :hidrometroInstalacaoHistorico, " 
					+ "lagu_tmultimaalteracao = :dataAtual "
					+ "where lagu_id = :imovelId ";

			session.createQuery(update)
			.setInteger("imovelId",idImovel)
			.setInteger("hidrometroInstalacaoHistorico", idHidrometroInstalacaoHistorico)
			.setTimestamp("dataAtual", new Date()).executeUpdate();


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 *  - Validar Atributo Ligacao de Agua
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarParametroTabelaAtualizacaoCadastral(Integer idParametroTabelaAtualizacaoCadastral, Date dataSituacao) throws ErroRepositorioException {

		Integer retorno = null;
		boolean existe = true;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select ptac_id parametro "//0 
					+  " from cadastro.param_tab_atualiz_cadast "
					+  " where ptac_id = :idParametroTabelaAtualizacaoCadastral "
					+  " and ptac_tmultimaalteracao < :dataSituacao ";

			retorno =  (Integer) session.createSQLQuery(consulta)
					.addScalar("parametro", Hibernate.INTEGER)
					.setInteger("idParametroTabelaAtualizacaoCadastral", idParametroTabelaAtualizacaoCadastral)
					.setDate("dataSituacao", dataSituacao)
					.uniqueResult();

			if ( retorno == null || retorno.intValue() == 0 ) {
				existe = false;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return existe;
	}

	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastral> pesquisarClienteAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral, Integer clienteRelacaoTipo, 
			boolean dataRelacaoFim ) throws ErroRepositorioException {
	
		Collection<ClienteAtualizacaoCadastral> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT clie"
				     + " FROM ClienteAtualizacaoCadastral clie" 				    				    
				     + " WHERE clie.imovelAtualizacaoCadastral = :imovelAtualizacaoCadastral "; 
			
					if (clienteRelacaoTipo != null) {
						consulta = consulta + "and clie.idClienteRelacaoTipo = " + clienteRelacaoTipo + " ";
					}
					
					if ( dataRelacaoFim ) {
						consulta = consulta + "and clie.dataRelacaoFim is null ";
					}
			
					consulta = consulta + " order by clie.dataRelacaoFim  ";
					
			colecao = (Collection<ClienteAtualizacaoCadastral>)session.createQuery(consulta)
										.setInteger("imovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	
	}
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastral> pesquisarClienteAtualizacaoCadastralClienteUsuario(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException {
	
		Collection<ClienteAtualizacaoCadastral> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT clie"
				     + " FROM ClienteAtualizacaoCadastral clie" 				    				    
				     + " WHERE clie.imovelAtualizacaoCadastral = :imovelAtualizacaoCadastral"
				     + " and clie.dataRelacaoFim is null" 
				     + " and clie.idClienteRelacaoTipo = :clienteUsuario";
			
			colecao = (Collection<ClienteAtualizacaoCadastral>)session.createQuery(consulta)
										.setInteger("imovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.setInteger("clienteUsuario", ClienteRelacaoTipo.USUARIO)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	
	}
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ParametroTabelaAtualizacaoCadastro
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public ParametroTabelaAtualizacaoCadastro pesquisarParametroTabelaAtualizacaoCadastro(Integer idParametroTabelaAtualizacaoCadastro)
		throws ErroRepositorioException {
	
		ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT ptac"
				     + " FROM ParametroTabelaAtualizacaoCadastro ptac" 				    				    
				     + " WHERE ptac.id = :idParametroTabelaAtualizacaoCadastro";
			
			parametroTabelaAtualizacaoCadastro = (ParametroTabelaAtualizacaoCadastro)session.createQuery(consulta)
										.setInteger("idParametroTabelaAtualizacaoCadastro", idParametroTabelaAtualizacaoCadastro)
										.uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return parametroTabelaAtualizacaoCadastro;
	
	}
	
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0010] - Verificar Alteração do Cliente por Usuário da COMPESA
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param idImovel
	 * @param dataEnvio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean  verificarRegistroAtendimentoAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException {

		Integer retorno = 0;
		boolean existe = false;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +=" SELECT COUNT(*) contador " 
				+ " FROM atendimentopublico.registro_atendimento ra " 
				+ " WHERE ra.step_id = :averbacao "
				+ " and ra.imov_id = :idImovel";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("contador", Hibernate.INTEGER)
				.setInteger("averbacao", Integer.valueOf("3"))	
				.setInteger("idImovel", idImovel)
				.setMaxResults(1).uniqueResult();
			
			if (retorno != null && retorno.intValue() > 0 ) {
				existe = true;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return existe;
	}

	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [FS0005] - Pesquisar Órgão Expedidor do RG
	 * 
	 * @author Arthur Carvalho 
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrgaoExpedidorDoRG(Integer idImovelAtualizacaoCadastral ) throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +=" SELECT oerg.oerg_id id " 
				+ " FROM cadastro.orgao_expedidor_rg oerg " 
				+ " inner join cadastro.cliente_atlz_cadastral clac on clac.clac_dsabreviadaoerg = oerg.oerg_dsorgaoexpedidorrg "
				+ " WHERE clac.imac_id = :idImovelAtualizacaoCadastral ";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("id", Hibernate.INTEGER)
				.setInteger( "idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral )
				.setMaxResults(1).uniqueResult();
			
			
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	


	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [FS0006] - Pesquisar Unidade Federação do RG
	 * 
	 * @author Arthur Carvalho 
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarUnidadeFederacaoDoRG(Integer idImovelAtualizacaoCadastral ) throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +=" SELECT unfe.unfe_id id " 
				+ " FROM cadastro.unidade_federacao unfe " 
				+ " inner join cadastro.cliente_atlz_cadastral clac on clac.clac_dsufsiglaoerg = unfe.unfe_dsufsigla "
				+ " WHERE clac.imac_id = :idImovelAtualizacaoCadastral ";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
				.setInteger( "idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral )
				.setMaxResults(1).uniqueResult();
			
			
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Pesquisar dados do Retorno Atualização Cadastral
	 * 
	 * @return RetornoAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<RetornoAtualizacaoCadastral> pesquisarRetornoAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException {
	
		Collection<RetornoAtualizacaoCadastral> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT reat"
				     + " FROM RetornoAtualizacaoCadastral reat" 				    				    
				     + " WHERE reat.imovelAtualizacaoCadastral = :imovelAtualizacaoCadastral";
			
			colecao = (Collection<RetornoAtualizacaoCadastral>)session.createQuery(consulta)
										.setInteger("imovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	
	}
	
	
	/**
	 * 
	 * @return ImovelSubcategoriaAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public ImovelSubcategoriaAtualizacaoCadastral pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException {
	
		ImovelSubcategoriaAtualizacaoCadastral colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT isac"
				     + " FROM ImovelSubcategoriaAtualizacaoCadastral isac" 				    				    
				     + " WHERE isac.imovelAtualizacaoCadastral = :imovelAtualizacaoCadastral"
				     + " and isac.id = ( select max(i.id) from ImovelSubcategoriaAtualizacaoCadastral i "
				     									+ " WHERE i.imovelAtualizacaoCadastral = :imovelAtualizacaoCadastral)";
			
			colecao = (ImovelSubcategoriaAtualizacaoCadastral)session.createQuery(consulta)
										.setInteger("imovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	}
	
	/**
	 * [UC1299] Atualizar Cliente para Atualização Cadastral
	 * @return Cliente
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Cliente pesquisarCliente(String cpfCnpj) throws ErroRepositorioException {
	
		Cliente cliente = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT clie"
				     + " FROM Cliente clie" 				    				    
				     + " WHERE clie.cpf = :cpfCnpj or clie.cnpj = :cpfCnpj ";
			
			cliente = (Cliente) session.createQuery(consulta)
										.setString("cpfCnpj", cpfCnpj)
										.setMaxResults(1)
										.uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return cliente;
	}
	
	 /**
	  * Pesquisa o setor comercial passando como parametro o id da localidade e o codigo do setor
	  * 
	  * @param idLocalidade
	  * @param codigoSetor
	  * @return
	  * @throws ErroRepositorioException
	  */
    public SetorComercial pesquisarSetorComercial(int idLocalidade, Integer codigoSetor)
            throws ErroRepositorioException {

        //cria a coleção de retorno
    	SetorComercial retorno = null;
        //Query
        String consulta;
        //obtém a sessão
        Session session = HibernateUtil.getSession();

        try {
            //pesquisa a coleção de atividades e atribui a variável "retorno"
            consulta = "select new gcom.cadastro.localidade.SetorComercial(setorComercial.id, setorComercial.codigo,"
                    + "setorComercial.descricao) "
                    + "from gcom.cadastro.localidade.SetorComercial as setorComercial "
                    + "where setorComercial.localidade.id = "
                    + idLocalidade
                    + " and setorComercial.indicadorUso = 1"
                    + " and setorComercial.codigo = " + codigoSetor;

            retorno = (SetorComercial) session.createQuery(consulta).uniqueResult();

            //erro no hibernate
        } catch (HibernateException e) {
            //levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            //fecha a sessão
            HibernateUtil.closeSession(session);
        }
        //retorna a coleção de atividades pesquisada(s)
        return retorno;
    }
    
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 
	 * Retorna os números gerados para o sorteio.
	 * 
	 * @author Mariana Victor
	 * @date 03/03/2012
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImoveisSorteio() 
			throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imas_nnnumerogerado AS numeroGerado "
					+ " from cadastro.imovel_apto_sorteio ";
			
			retorno = (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("numeroGerado",Hibernate.INTEGER)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * 2. O sistema exibe o primeiro prêmio a ser sorteado.
	 * 
	 * @author Mariana Victor
	 * @date 06/03/2012
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarProximoPremio(Integer idSorteio) 
			throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select prso.prso_id AS idPremio, "
					+ "   prso.prso_dssorteio AS descricao, "
					+ "   (prso.prso_qtinicial - prso.prso_qtsorteiada) AS quantidade, "
					+ "   prso.prso_nnordemsorteio AS ordemPremio "
					+ " from cadastro.premio_sorteio prso "
					+ "	where prso.prso_qtinicial - prso.prso_qtsorteiada > 0 "
					+ "   and prso.sort_id = :idSorteio "
					+ " order by (prso.prso_nnordemsorteio) ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
					.addScalar("idPremio",Hibernate.INTEGER)
					.addScalar("descricao",Hibernate.STRING)
					.addScalar("quantidade",Hibernate.INTEGER)
					.addScalar("ordemPremio",Hibernate.INTEGER)
					.setInteger("idSorteio",idSorteio)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 07/03/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarImoveisAptoSorteio() 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imas_nnnumerogerado AS numeroGerado, "
					+ "		imov_id AS idImovel "
					+ " from cadastro.imovel_apto_sorteio "
					+ " where prso_id is null ";
			
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("numeroGerado",Hibernate.INTEGER)
					.addScalar("idImovel",Hibernate.INTEGER)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}


	/**
	 * [[UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0002] - Atualizar Tabelas.
	 * 
	 * @author Mariana Victor
	 * @data 09/03/2012
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarImovelSorteio(Integer idImovel, 
			Integer idPremio, Integer numeroOrdemSorteio) 
			throws ErroRepositorioException {
		
		String consulta;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.cadastro.ImovelAptoSorteio "
				+ " set imas_icsorteio = :indicadorSorteio, imas_dtsorteio = :dataSorteio, prso_id = :idPremio, "
				+ " 	imas_nnordemsorteio = :numeroOrdemSorteio, imas_tmultimaalteracao = :ultimaAlteracao "
				+ " where imov_id = :idImovel ";
		
			session.createQuery(consulta)
				.setInteger("indicadorSorteio", ConstantesSistema.SIM)
				.setDate("dataSorteio", new Date())
				.setInteger("idPremio", idPremio)
				.setInteger("numeroOrdemSorteio", numeroOrdemSorteio)
				.setTimestamp("ultimaAlteracao", new Date())
				.setInteger("idImovel", idImovel)
				.executeUpdate();

			
			consulta = "update gcom.cadastro.PremioSorteio "
				+ " set prso_qtsorteiada = (prso_qtsorteiada + 1), prso_tmultimaalteracao = :ultimaAlteracao "
				+ " where prso_id = :idPremio ";
		
			session.createQuery(consulta)
				.setInteger("idPremio", idPremio)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 09/03/2012
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroOrdemSorteio() 
			throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select coalesce(max(imas_nnordemsorteio), 0) AS numeroOrdemSorteio "
					+ " from cadastro.imovel_apto_sorteio "
					+ " where prso_id is not null ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("numeroOrdemSorteio",Hibernate.INTEGER)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0003] - Emitir Relatório
	 * 
	 * @author Mariana Victor
	 * @date 09/03/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioImoveisSorteados() 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select prso.prso_dssorteio AS premio, " //0
					+ "		imas.imov_id AS idImovel, " //1
					+ "		imas.imas_nnordemsorteio AS numeroOrdem, " //2
					+ "		prso.prso_nnordemsorteio AS ordemPremio, " //3
					+ "		imas.imas_nnnumerogerado AS numeroSorteio, " //4
					+ "		greg.greg_nmregional AS gerenciaRegional, " //5
					+ "		loca.loca_nmlocalidade AS localidade " //6
					+ " from cadastro.imovel_apto_sorteio imas "
					+ " 	inner join cadastro.premio_sorteio prso on prso.prso_id = imas.prso_id "
					+ " 	inner join cadastro.imovel imov on imov.imov_id = imas.imov_id "
					+ " 	inner join cadastro.localidade loca on loca.loca_id = imov.loca_id "
					+ " 	inner join cadastro.gerencia_regional greg on greg.greg_id = loca.greg_id "
					+ " order by prso.prso_nnordemsorteio, imas.imas_nnordemsorteio ";
			
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("premio",Hibernate.STRING)
					.addScalar("idImovel",Hibernate.INTEGER)
					.addScalar("numeroOrdem",Hibernate.INTEGER)
					.addScalar("ordemPremio",Hibernate.INTEGER)
					.addScalar("numeroSorteio",Hibernate.INTEGER)
					.addScalar("gerenciaRegional",Hibernate.STRING)
					.addScalar("localidade",Hibernate.STRING)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}


	/**
	 * [UC1297] Atualizar Dados Cadastrais para Imoveis Inconsistentes
	 * 
	 * [SB0006] Relatório dos Imoveis Inconsistentes
	 * 
	 * @author Davi Menezes
	 * @date 26/03/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisInconsistentes(Integer idMovimento, Date dataMovimento,
			String idLocalidade, String codigoSetorComercial, String numeroQuadraInicial,
			String numeroQuadraFinal, String idCadastrador, String indicadorSituacaoMovimento,
			String tipoInconsistencia)throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		Date dataMovimentoInicial = Util.formatarDataInicial(dataMovimento);
		Date dataMovimentoFinal = Util.formatarDataFinal(dataMovimento);
		
		try{
			String consulta = "SELECT iac.imov_id idImovel, " + //0
					"iac.imac_dtrecebimentomovimento dataRecebimento, " + //1
					"loc.loca_id idLocalidade, " + //2
					"loc.loca_nmlocalidade nomeLocalidade, " + //3
					"iac.imac_cdsetorcomercial codigoSetor, " + //4
					"iac.imac_nnquadra numeroQuadra, " + //5
					"sac.siac_dssituacao descSituacao, " + //6
					"aac.atac_nmatributo numeroAtributo, " +//7
					"mac.matc_dsmensagem descMensagem, " +//8
					"usurCad.usur_nmusuario nomeCadastrador " +//9
					
					"FROM CADASTRO.imovel_atlz_cadastral iac " +
					"left join cadastro.imovel imov on imov.imov_id = iac.imov_id " + 
					"inner join cadastro.localidade loc on loc.loca_id = iac.loca_id " +
					"left join CADASTRO.situacao_atlz_cadastral sac on sac.siac_id = imov.siac_id " +
					
 	 				"left join seguranca.usuario usurCad on iac.imac_nmlogin = usurCad.usur_nmlogin or iac.imac_nmlogin = usurCad.usur_nncpf " +
 	 				
					"left join CADASTRO.cadastrador ca on ca.cadt_id = iac.cadt_id " +
					
					"inner join CADASTRO.retorno_atlz_cadastral rac on rac.matc_id <> 10 and REAT_CDOPCAOALTERACAO is null " +
					"and rac.imov_id = iac.imov_id and rac.imac_id = iac.imac_id " +
					"left join CADASTRO.atributo_atlz_cadastral aac on aac.atac_id = rac.atac_id " +
					"left join CADASTRO.mensagem_atlz_cadastral mac  on mac.matc_id = rac.matc_id " +
					"where iac.ptac_id = :idMovimento " +
					"and iac.imac_dtrecebimentomovimento between :dataMovimentoInicial and :dataMovimentoFinal " +
					"and iac.imac_dtrecebimentomovimento is not null ";
			
			parameters.put("idMovimento", idMovimento);
			parameters.put("dataMovimentoInicial", dataMovimentoInicial);
			parameters.put("dataMovimentoFinal", dataMovimentoFinal);
			
			//Localidade
			if ( Util.verificarNaoVazio(idLocalidade) ){ 
				consulta += " and iac.loca_id = :idLocalidade ";
				parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			}
			
			//Setor Comercial
			if ( Util.verificarNaoVazio(codigoSetorComercial) ){ 
				consulta += " and iac.imac_cdsetorcomercial = :codigoSetorComercial ";
				parameters.put("codigoSetorComercial", Integer.parseInt(codigoSetorComercial));
			}
			
			//Quadra 
			if ( Util.verificarNaoVazio(numeroQuadraInicial) && Util.verificarNaoVazio(numeroQuadraFinal) ) { 
				consulta += " and iac.imac_nnquadra between :numeroQuadraInicial and :numeroQuadraFinal ";
				parameters.put("numeroQuadraInicial", Integer.parseInt(numeroQuadraInicial));
				parameters.put("numeroQuadraFinal", Integer.parseInt(numeroQuadraFinal));
			} 
			
			//Cadastrador
			if ( Util.parametroNumericoValido(idCadastrador) ){ 
				consulta += " and usurCad.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(idCadastrador));
			}
						
			if ( indicadorSituacaoMovimento != null && 
					(indicadorSituacaoMovimento.equals("1") || indicadorSituacaoMovimento.equals("2"))) {
				consulta += " and iac.imac_icpendente = :indicadorSituacaoMovimento ";
				parameters.put("indicadorSituacaoMovimento", Short.parseShort(indicadorSituacaoMovimento));
			} 
			
			if ( Util.parametroNumericoValido(tipoInconsistencia) ) { 
				consulta += " and rac.matc_id = :tipoInconsistencia ";
				parameters.put("tipoInconsistencia", Integer.parseInt(tipoInconsistencia));
			}
			
			consulta += "order by iac.imac_dtrecebimentomovimento, loc.loca_id, iac.imov_id ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.STRING)
					.addScalar("dataRecebimento", Hibernate.DATE)
					.addScalar("idLocalidade", Hibernate.STRING)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("codigoSetor", Hibernate.STRING)
					.addScalar("numeroQuadra", Hibernate.STRING)
					.addScalar("descSituacao", Hibernate.STRING)
					.addScalar("numeroAtributo", Hibernate.STRING)
					.addScalar("descMensagem", Hibernate.STRING)
					.addScalar("nomeCadastrador", Hibernate.STRING);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
//			
//			retorno = session.createSQLQuery(consulta)
//					.addScalar("idImovel", Hibernate.STRING)
//					.addScalar("dataRecebimento", Hibernate.DATE)
//					.addScalar("idLocalidade", Hibernate.STRING)
//					.addScalar("nomeLocalidade", Hibernate.STRING)
//					.addScalar("codigoSetor", Hibernate.STRING)
//					.addScalar("numeroQuadra", Hibernate.STRING)
//					.addScalar("descSituacao", Hibernate.STRING)
//					.addScalar("numeroAtributo", Hibernate.STRING)
//					.addScalar("descMensagem", Hibernate.STRING)
//					.addScalar("nomeCadastrador", Hibernate.STRING)
////					.setInteger("idMovimento", idMovimento)
////					.setDate("dataMovimentoInicial", dataMovimentoInicial)
////					.setDate("dataMovimentoFinal", dataMovimentoFinal)
//					.list();
			
		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

    
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 
	 * Para cada imóvel apto sorteado, o sistema deverá verificar se o mesmo tem o documento de qualquer dos clientes associados ao imóvel 
	 * 
	 * @author Mariana Victor
	 * @date 28/03/2012
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<String> pesquisarCpfImpedido() 
			throws ErroRepositorioException {
		
		Collection<String> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select diso_nncpf AS cpf "
					+ " from cadastro.docto_impedido_sorteio ";
			
			retorno = (Collection<String>) session.createSQLQuery(consulta)
					.addScalar("cpf",Hibernate.STRING)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0003] - Emitir Relatório
	 * 
	 * @author Mariana Victor
	 * @date 30/03/2012
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosImoveisSorteados(Integer idImovel) 
			throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select greg.greg_nmregional AS gerenciaRegional, " 
					+ "		loca.loca_nmlocalidade AS localidade " 
					+ " from cadastro.imovel imov "
					+ " 	inner join cadastro.localidade loca on loca.loca_id = imov.loca_id "
					+ " 	inner join cadastro.gerencia_regional greg on greg.greg_id = loca.greg_id "
					+ "	where imov.imov_id = :idImovel ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
					.addScalar("gerenciaRegional",Hibernate.STRING)
					.addScalar("localidade",Hibernate.STRING)
					.setInteger("idImovel", idImovel)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1297] Pesquisar Imovel Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param indicadorAtualizacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral ) throws ErroRepositorioException {
	
		ImovelAtualizacaoCadastral colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT imac"
				     + " FROM ImovelAtualizacaoCadastral imac"
					 + " WHERE imac.id = :idImovelAtualizacaoCadastral "; 
			
			colecao = (ImovelAtualizacaoCadastral) session.createQuery(consulta)
										.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	
	}
	
	/**
	 * [UC1297] Pesquisar Cliente Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @since 29/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastral> pesquisarClienteAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		
		Collection<ClienteAtualizacaoCadastral> cliente = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSession();

		try{
			consulta =  "SELECT clac "
					 +	"FROM ClienteAtualizacaoCadastral clac "
					 +	"WHERE clac.imovelAtualizacaoCadastral.id = :idImovelAtualizacaoCadastral ";
			
			cliente = (Collection<ClienteAtualizacaoCadastral>) session.createQuery(consulta)
							.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
							.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return cliente;
	}
	
	/**
	 * [UC1297] Pesquisar Imovel Subcategoria Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @since 29/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		
		Collection<ImovelSubcategoriaAtualizacaoCadastral> imovelSubcategoria = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSession();
		
		try{
			consulta =  "Select scac "
					 +	"FROM ImovelSubcategoriaAtualizacaoCadastral scac "
					 +	"WHERE scac.imovelAtualizacaoCadastral.id = :idImovelAtualizacaoCadastral ";
			
			imovelSubcategoria = (Collection<ImovelSubcategoriaAtualizacaoCadastral>) session.createQuery(consulta)
									.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
									.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return imovelSubcategoria;
	}
	
	/**
 * [UC1297] Pesquisar Imovel Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 *
	 * @param numeroHidrometro
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Hidrometro pesquisarHidrometroPeloNumero(String numeroHidrometro) throws ErroRepositorioException {
		Hidrometro retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			
			consulta = "SELECT hidr " 
					+ "FROM Hidrometro hidr "
					+ "WHERE hidr.numero =:numeroHidrometro";

			retorno = (Hidrometro) session.createQuery(consulta).setString(
					"numeroHidrometro", numeroHidrometro.toUpperCase()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

    
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 12/04/2012
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataSorteio() 
			throws ErroRepositorioException {
		
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select max(imas_dtsorteio) AS dataSorteio "
					+ " from cadastro.imovel_apto_sorteio ";
			
			retorno = (Date) session.createSQLQuery(consulta)
					.addScalar("dataSorteio",Hibernate.DATE)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	 
	/**
	 * [UC1309] Download nova versão Sistemas Android
	 * 
	 * @author Fernanda Almeida
	 * @date 23/04/2012
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] baixarNovaVersaoApk( Integer idSistemaAndroid ) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Object[] retorno = null;

		try {
			consulta = " select vsan_arquivoapk as apk," //0
					   + "vsan_nnversao as versao"//1
					   + " from cadastro.versao_sistemas_android vsa " 
					   + " INNER JOIN cadastro.sistema_android sa ON sa.sian_id = vsa.sian_id and vsa.sian_id = :idSistemaAndroid" 
					   + " order by replace( vsan_nnversao, '.', '' ) desc";

			retorno =(Object[]) session.createSQLQuery(consulta).addScalar(
					"apk", Hibernate.BINARY).addScalar(
					"versao", Hibernate.STRING)
					.setInteger("idSistemaAndroid",idSistemaAndroid)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 18/04/2012
	 * 
	 * @param indicadorAtualizacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelAtualizacaoCadastral> pesquisarColecaoImovelAtualizacaoCadastral( Integer idParametroTabelaAtualizacaoCadastro )throws ErroRepositorioException {
	
		Collection<ImovelAtualizacaoCadastral> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT imac"
				     + " FROM ImovelAtualizacaoCadastral imac" 				    				    
				     + " WHERE imac.parametroTabelaAtualizacaoCadastro = :idParametroTabelaAtualizacaoCadastro "
				     + " AND imac.indicadorDadosRetorno = :indicadorRetorno "; 
			
			colecao = (Collection<ImovelAtualizacaoCadastral>)session.createQuery(consulta)
										.setInteger("idParametroTabelaAtualizacaoCadastro", idParametroTabelaAtualizacaoCadastro)
										.setInteger("indicadorRetorno", ConstantesSistema.NAO)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	}
	
	/**
	 * [UC 1311] Gerar Resumo da Posição de Atualização Cadastral
	 * 
	 * @author Anderson Cabral
	 * @since 13/08/2013
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarResumoPosicaoAtualizacaoCadastral(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			
		    consulta =	" SELECT principal.idLocalidade as idLocalidade, "+
						"  principal.nomeLocalidade as nomeLocalidade, "+
						"  SUM(principal.quantidadeImoveisRoteiro) as quantidadeImoveisRoteiro, "+
						"  SUM(principal.quantidadePreGsan) as quantidadePreGsan, "+
						"  SUM(principal.quantidadeAmbienteII) as quantidadeAmbienteII, "+
						"  SUM(principal.quantidadeAtualizadosGSAN) as quantidadeAtualizadosGSAN, "+
						"  SUM(principal.parametroAC) as parametroAC, "+
						"  SUM(principal.quantidadeIncluidos) as quantidadeIncluidos, "+
						"  SUM(principal.quantidadeAtualizados) as quantidadeAtualizados, "+
						"  principal.nomeUsuario as nomeUsuario, "+
						"  principal.codigoSetor as codigoSetor, "+
						"  SUM(principal.preGsanAtualizados) as preGsanAtualizados, "+
						"  SUM(principal.preGsanIncluidos) as preGsanIncluidos, "+
						"  SUM(principal.comInconsistencia) as comInconsistencia, "+
						"  SUM(principal.semInconsistencia) as semInconsistencia, "+
						"  SUM(principal.retornoCampo) as retornoCampo, "+
						"  SUM(principal.removido) as removido "+
						"  FROM "+
					   	"  ( ";
			
			consulta += "SELECT imac.loca_id as idLocalidade, " +
					   "loc.loca_nmlocalidade as nomeLocalidade, " +
					   "arquivo.txat_qtdimovel  as quantidadeImoveisRoteiro, " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 3 AND imac.IMAC_CDSITUACAO is null THEN 1 ELSE 0 END) as quantidadePreGsan, " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado = 2 AND (imac.IMAC_CDSITUACAO <> 2 or imac.IMAC_CDSITUACAO is null) THEN 1 ELSE 0 END) as quantidadeAmbienteII, " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado = 1 THEN 1 ELSE 0 END) as quantidadeAtualizadosGSAN, " +
					   "imac.ptac_id as parametroAC, " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 1 and imac.imac_icdadosretorno = 1 THEN 1 ELSE 0 END) as quantidadeIncluidos, " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 2 and imac.imac_icdadosretorno = 1 THEN 1 ELSE 0 END) as quantidadeAtualizados, " +
					   "usua.usur_nmusuario as nomeUsuario, " +
					   "param.PTAC_CDSETORCOMERCIALINICIAL as codigoSetor, " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 2 AND imac.imac_icdadosretorno = 3 THEN 1 ELSE 0 END) as preGsanAtualizados,  " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 1 AND imac.imac_icdadosretorno = 3 THEN 1 ELSE 0 END) as preGsanIncluidos,  " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado    = 1 "+
					   " 		AND NOT EXISTS "+
					   " 	       (SELECT * "+
					   " 	       FROM CADASTRO.retorno_atlz_cadastral rac "+
					   " 	       WHERE rac.matc_id <> 10 "+
					   " 	       AND imac.imac_id   = rac.imac_id "+
					   " 	       ) "+
					   " 	     THEN 1 "+
					   " 	     ELSE 0 "+
					   " 	   END) AS semInconsistencia, "+
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado = 1 "+
					   "	     AND EXISTS "+
					   "	       (SELECT * "+
					   "	       FROM CADASTRO.retorno_atlz_cadastral rac "+
					   "	       WHERE rac.matc_id <> 10 "+
					   "	       AND imac.imac_id   = rac.imac_id "+
					   "	       ) "+
					   "	     THEN 1 "+
					   "	     ELSE 0 "+
					   "	   END) AS comInconsistencia, "+
					   "SUM (CASE WHEN imac.imac_cdsituacao    = 2 AND imac.imac_icdadosretorno = 1 THEN 1 ELSE 0 END) AS retornoCampo, "+
					   "0 as removido "+
					   "FROM cadastro.imovel_atlz_cadastral imac " +
					   "INNER JOIN cadastro.localidade loc on loc.loca_id = imac.loca_id " +
					   "INNER JOIN CADASTRO.param_tab_atualiz_cadast param on param.ptac_id = imac.ptac_id " +
					   "INNER join CADASTRO.ATLZ_CADASTRAL_ARQ_TXT arquivo on arquivo.ptac_id = imac.ptac_id " +
					   "INNER join micromedicao.leiturista leitu on leitu.LEIT_ID = arquivo.LEIT_ID " +
					   "INNER JOIN seguranca.USUARIO usua on usua.usur_id=leitu.usur_id " +
					   "WHERE imac.empr_id = :idEmpresa ";

			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and loc.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and imac.loca_id >= :idLocalidadeInicial AND imac.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and param.ptac_cdsetorcomercialinicial >= :idSetorInicial AND param.ptac_cdsetorcomercialinicial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and imac.imac_nnquadra >= :idQuadraInicial AND imac.imac_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}

			if(helper.getIdCadastrador() != null){
				consulta += "and usua.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
//			if(helper.getIdAnalista() != null){
//				consulta += "and retorno.usur_id = :idAnalista ";
//				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
//			}
//			
//			if(helper.getMensagem() != null){
//				consulta += "AND imac.imac_ID in (SELECT imac_ID  FROM cadastro.retorno_atlz_cadastral WHERE MATC_ID = :idTipoInconsistencia) ";
//				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
//			}
			
			if(helper.getIdAnalista() != null || helper.getMensagem() != null){
				String whereIn = " WHERE ";
				
				boolean analistaInformado = false;
				if(helper.getIdAnalista() != null){
					whereIn += " usur_id = :idAnalista ";
					analistaInformado = true;
					
					parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
				}
				
				if(helper.getMensagem() != null){
					if(analistaInformado){
						whereIn += " and MATC_ID = :idTipoInconsistencia ";	
					}else{
						whereIn += " MATC_ID = :idTipoInconsistencia ";
					}
					
					parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
				}
				
				consulta += "AND imac.imac_ID in (SELECT imac_ID  FROM cadastro.retorno_atlz_cadastral "+whereIn+") ";
			}
			
			consulta += "group by imac.loca_id, arquivo.txat_qtdimovel,loc.loca_nmlocalidade , imac.ptac_id,usua.usur_nmusuario,param.PTAC_CDSETORCOMERCIALINICIAL ";
			
			//============= Query para obter os removidos =======================
			
			consulta += " UNION ALL "+
						" SELECT iacr.loca_id                  AS idLocalidade, "+
						" loc.loca_nmlocalidade              AS nomeLocalidade, "+
						" 0            						 AS quantidadeImoveisRoteiro, "+
						" 0                                  AS quantidadePreGsan, "+
						" 0                                  AS quantidadeAmbienteII, "+
						" 0                                  AS quantidadeAtualizadosGSAN, "+
						" iacr.ptac_id                       AS parametroAC, "+
						" 0                                  AS quantidadeIncluidos, "+
						" 0                                  AS quantidadeAtualizados, "+
						" usua.usur_nmusuario                AS nomeUsuario, "+
						" param.PTAC_CDSETORCOMERCIALINICIAL AS codigoSetor, "+
						" 0                                  AS preGsanAtualizados, "+
						" 0                                  AS preGsanIncluidos, "+
						" 0                                  AS semInconsistencia, "+
						" 0                                  AS comInconsistencia, "+
						" 0                                  AS retornoCampo, "+
						" SUM(1)                             AS removido "+
						" FROM CADASTRO.imovel_atlz_cad_removido iacr "+
						" INNER JOIN cadastro.localidade loc "+
						" ON loc.loca_id = iacr.loca_id "+
						" INNER JOIN CADASTRO.param_tab_atualiz_cadast param "+
						" ON param.ptac_id = iacr.ptac_id "+
						" INNER JOIN CADASTRO.ATLZ_CADASTRAL_ARQ_TXT arquivo "+
						" ON arquivo.ptac_id = iacr.ptac_id "+
						" INNER JOIN micromedicao.leiturista leitu "+
						" ON leitu.LEIT_ID = arquivo.LEIT_ID "+
						" INNER JOIN seguranca.USUARIO usua "+
						" ON usua.usur_id    =leitu.usur_id "+
						" WHERE iacr.empr_id = :idEmpresa ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += " and iacr.iacr_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and loc.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and iacr.loca_id >= :idLocalidadeInicial AND iacr.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and param.ptac_cdsetorcomercialinicial >= :idSetorInicial AND param.ptac_cdsetorcomercialinicial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and iacr.iacr_nnquadra >= :idQuadraInicial AND iacr.iacr_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}

			if(helper.getIdCadastrador() != null){
				consulta += "and usua.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			consulta += " GROUP BY iacr.loca_id, "+
						" arquivo.txat_qtdimovel, "+
						" loc.loca_nmlocalidade , "+
						" iacr.ptac_id, "+
						" usua.usur_nmusuario, "+
						" param.PTAC_CDSETORCOMERCIALINICIAL "+
						" ORDER BY idLocalidade, "+
						" codigoSetor ";
			
			consulta +=
						") principal " +
						"  GROUP BY principal.idLocalidade,"+
						"  principal.nomeLocalidade,"+
						"  principal.nomeUsuario,"+
						"  principal.codigoSetor"+
						"  ORDER BY principal.idLocalidade,"+
						"  principal.nomeUsuario," +
						"  principal.codigoSetor";
						
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER)                //0
					.addScalar("nomeLocalidade", Hibernate.STRING)				 //1
					.addScalar("quantidadeImoveisRoteiro", Hibernate.INTEGER)    //2 
					.addScalar("quantidadePreGsan", Hibernate.INTEGER)           //3
					.addScalar("quantidadeAmbienteII", Hibernate.INTEGER)        //4
					.addScalar("quantidadeAtualizadosGSAN", Hibernate.INTEGER)   //5 
					.addScalar("parametroAC", Hibernate.INTEGER)                 //6 
					.addScalar("quantidadeIncluidos", Hibernate.INTEGER)         //7
					.addScalar("quantidadeAtualizados", Hibernate.INTEGER)       //8
					.addScalar("nomeUsuario", Hibernate.STRING)                  //9  
					.addScalar("codigoSetor", Hibernate.INTEGER)                 //10 
					.addScalar("preGsanAtualizados", Hibernate.INTEGER)          //11 
					.addScalar("preGsanIncluidos", Hibernate.INTEGER)            //12 
					.addScalar("comInconsistencia", Hibernate.INTEGER)           //13 
					.addScalar("semInconsistencia", Hibernate.INTEGER)           //14 
					.addScalar("retornoCampo", Hibernate.INTEGER)           	 //15
					.addScalar("removido", Hibernate.INTEGER);           	     //16
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();
		
		}catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC 1312] Gerar Resumo da Situação dos imóveis por cadastrador/analista
	 * 
	 * @author Davi Menezes
	 * @since 12/04/2012
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarResumoSituacaoImoveisPorCadastradorAnalista(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta = 	"select idLocalidade, nomeLocalidade, idCliente, nomeCliente, qtd1, qtd2, qtd3, qtd4, tipo from( "
					 +	"select imac.loca_id idLocalidade, loca.loca_nmlocalidade nomeLocalidade, " 
//					 +	"cadt.cadt_id idCliente, cadt.cadt_nmcadastrador nomeCliente, "
					 +  "usurCad.usur_id idCliente, usurCad.usur_nmusuario nomeCliente, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 1 then raet.reat_id end)) qtd1, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 2 then raet.reat_id end)) qtd2, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 3 then raet.reat_id end)) qtd3, "
					 +	"0 qtd4, "
					 +	"case when usurCad.usur_id is not null then 'cad' end tipo "
					 +	"from cadastro.imovel_atlz_cadastral imac "
					 +	"inner join cadastro.localidade loca on loca.loca_id = imac.loca_id "
//					 +	"inner join cadastro.setor_comercial sec ON sec.loca_id = loca.loca_id and imac.imac_cdsetorcomercial = sec.stcm_cdsetorcomercial "
//					 +	"inner join cadastro.quadra qdra ON qdra.stcm_id = sec.stcm_id "
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loca.greg_id "
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loca.uneg_id "
					 +	"left join cadastro.retorno_atlz_cadastral raet on raet.imac_id = imac.imac_id "
//					 +	"left join cadastro.cadastrador cadt on cadt.cadt_id = imac.cadt_id "
					 +  "left join seguranca.usuario usurCad on imac.imac_nmlogin = usurCad.usur_nmlogin "
					 +	"left join seguranca.usuario usur on usur.usur_id = raet.usur_id "
					 +	"where imac.empr_id = :idEmpresa " 
					 +	"and usurCad.usur_id is not null ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and imac.loca_id >= :idLocalidadeInicial AND imac.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and imac.imac_cdsetorcomercial >= :idSetorInicial AND imac.imac_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and imac.imac_nnquadra >= :idQuadraInicial AND imac.imac_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += "and usurCad.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and raet.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and raet.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			} 
			
			consulta += "group by imac.loca_id, loca.loca_nmlocalidade , usurCad.usur_id, usurCad.usur_nmusuario, 0, "
					 +	"case when usurCad.usur_id is not null then 'cad' end ";
			
			consulta += "UNION ALL ";
			
			consulta += "select imac.loca_id localidade, loca.loca_nmlocalidade nomeLocalidade, usur.usur_id cliente, usur.usur_nmusuario nomeCliente, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 1 then raet.reat_id end)) qtd1, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 2 then raet.reat_id end)) qtd2, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 3 then raet.reat_id end)) qtd3, "
					 +	"0 qtd4, "
					 +	"case when raet.usur_id is not null then 'ana' end tipo "
    				 +	"from cadastro.imovel_atlz_cadastral imac "
    				 +	"inner join cadastro.localidade loca on loca.loca_id = imac.loca_id "
    				 +	"inner join cadastro.setor_comercial sec ON sec.loca_id = loca.loca_id "
					 +	"inner join cadastro.quadra qdra ON qdra.stcm_id = sec.stcm_id "
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loca.greg_id "
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loca.uneg_id "
    				 +	"left join cadastro.retorno_atlz_cadastral raet on raet.imac_id = imac.imac_id "
    				 +	"left join seguranca.usuario usur on usur.usur_id = raet.usur_id "
    				 +  "left join seguranca.usuario usurCad on imac.imac_nmlogin = usurCad.usur_nmlogin "
    				 +	"where imac.empr_id = :idEmpresa and usur.usur_id is not null ";
    				 
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}

			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and imac.loca_id >= :idLocalidadeInicial AND imac.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and imac.imac_cdsetorcomercial >= :idSetorInicial AND imac.imac_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and imac.imac_nnquadra >= :idQuadraInicial AND imac.imac_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += "and usurCad.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and raet.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and raet.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			consulta +=	"group by imac.loca_id, loca.loca_nmlocalidade,  usur.usur_id, usur.usur_nmusuario, "
					 +	"case when raet.usur_id is not null then 'ana' end "
					 +	")  order by idLocalidade, tipo, nomeCliente ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER) //0
					.addScalar("nomeLocalidade", Hibernate.STRING) //1
					.addScalar("idCliente", Hibernate.INTEGER) //2
					.addScalar("nomeCliente", Hibernate.STRING) //3
					.addScalar("qtd1", Hibernate.INTEGER) //4
					.addScalar("qtd2", Hibernate.INTEGER) //5
					.addScalar("qtd3", Hibernate.INTEGER) //6
					.addScalar("qtd4", Hibernate.INTEGER) //7
					.addScalar("tipo", Hibernate.STRING); //8
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
		
		}catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Collection<Object[]> pesquisarResumoQuantitativoMensagensPendentes(
			DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta += "select loc.loca_id idLocalidade, loc.loca_nmlocalidade nomeLocalidade, "
					 +	"matc.matc_dsmensagem descricaoMensagem, count(imac.imac_id) quantidade " 
					 +	"from cadastro.imovel_atlz_cadastral imac "
					 +	"inner join cadastro.retorno_atlz_cadastral ratc on ratc.imac_id = imac.imac_id "
					 +	"inner join cadastro.mensagem_atlz_cadastral matc on matc.matc_id = ratc.matc_id "
					 +	"left  join cadastro.imovel imo on imo.imov_id = imac.imov_id "
					 +	"inner join cadastro.localidade loc on imac.loca_id = loc.loca_id "
					 +	"inner join cadastro.setor_comercial sec ON sec.stcm_id = imo.stcm_id "
					 +	"inner join cadastro.quadra qdra ON qdra.qdra_id = imo.qdra_id "
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loc.greg_id "
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loc.uneg_id "
					 
					 +  "left join CADASTRO.ATLZ_CADASTRAL_ARQ_TXT arquivo on arquivo.ptac_id = imac.ptac_id " 
					 +  "left join micromedicao.leiturista leitu on leitu.LEIT_ID = arquivo.LEIT_ID " 
					 +  "left JOIN seguranca.USUARIO usua on usua.usur_id=leitu.usur_id " 
					 
					 
					 + 	"where imac.empr_id = :idEmpresa and matc.matc_id <> :atualizacaoComSucesso ";
				
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			parameters.put("atualizacaoComSucesso", MensagemAtualizacaoCadastral.ATUALIZACAO_COM_SUCESSO);
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and imac.loca_id >= :idLocalidadeInicial AND imac.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and sec.stcm_cdsetorcomercial >= :idSetorInicial AND sec.stcm_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and qdra.qdra_nnquadra >= :idQuadraInicial AND qdra.qdra_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += "and usua.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and ratc.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and ratc.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			consulta += "group by loc.loca_id, loc.loca_nmlocalidade, matc.matc_dsmensagem "
					 +	"order by loc.loca_id,matc.matc_dsmensagem ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("descricaoMensagem", Hibernate.STRING)
					.addScalar("quantidade", Hibernate.INTEGER);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();
		
		}catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC 1313] Gerar Resumo Quantitativo de Mensagens Pendentes por Cadastrador
	 * 
	 * @author Davi Menezes
	 * @since 18/04/2012
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarResumoQuantitativoMensagensPendentesCadastrador(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta = 	"select loc.loca_id idLocalidade, loc.loca_nmlocalidade nomeLocalidade, "
//					 +	"cad.cadt_id idCadastrador, cad.cadt_nmcadastrador nomeCadastrador, "
					 +  "usur.usur_id idCadastrador, usur.usur_nmusuario nomeCadastrador, "
					 +	"matc.matc_dsmensagem descricaoMensagem, count( imac.imac_id) quantidade "
					 +	"from cadastro.retorno_atlz_cadastral ratc "
					 +	"inner join cadastro.imovel_atlz_cadastral imac on ratc.imac_id = imac.imac_id " 
					 +	"inner join cadastro.mensagem_atlz_cadastral matc on matc.matc_id = ratc.matc_id "
//					 +	"inner join cadastro.cadastrador cad on cad.cadt_id = imac.cadt_id "
					 +  "inner join seguranca.usuario usur on imac.imac_nmlogin = usur.usur_nmlogin "
					 +	"left join cadastro.imovel imo on imo.imov_id = imac.imov_id "
					 +	"inner join cadastro.localidade loc on imac.loca_id = loc.loca_id " 
					 +	"inner join cadastro.setor_comercial sec ON sec.stcm_id = imo.stcm_id " 
					 +	"inner join cadastro.quadra qdra ON qdra.qdra_id = imo.qdra_id " 
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loc.greg_id " 
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loc.uneg_id "
					 +	"where imac.empr_id = :idEmpresa and matc.matc_id <> :atualizacaoComSucesso ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			parameters.put("atualizacaoComSucesso", MensagemAtualizacaoCadastral.ATUALIZACAO_COM_SUCESSO);
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and imac.loca_id >= :idLocalidadeInicial AND imac.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and sec.stcm_cdsetorcomercial >= :idSetorInicial AND sec.stcm_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and qdra.qdra_nnquadra >= :idQuadraInicial AND qdra.qdra_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			if(helper.getIdCadastrador() != null){
//				consulta += "and imac.cadt_id = :idCadastrador ";
				consulta += "and usur.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and ratc.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and ratc.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			consulta += "group by loc.loca_id, loc.loca_nmlocalidade, "
					 +	"usur.usur_id, usur.usur_nmusuario, matc.matc_dsmensagem "
					 +	"order by loc.loca_id, usur.usur_nmusuario, matc.matc_dsmensagem ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("idCadastrador", Hibernate.INTEGER)
					.addScalar("nomeCadastrador", Hibernate.STRING)
					.addScalar("descricaoMensagem", Hibernate.STRING)
					.addScalar("quantidade", Hibernate.INTEGER);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();
		
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC 1315] Gerar Resumo da Posição de Atualização Cadastral por Pacote
	 * 
	 * @author Davi Menezes
	 * @since 11/04/2012
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarResumoPosicaoAtualizacaoCadastralPacoteRecebido(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta += "Select loc.loca_id as idLocalidade, loc.loca_nmlocalidade as nomeLocalidade, "
					 +	"imac.imac_dtrecebimentomovimento as dataRecebimento, ptac_id as parametro, "
					 +	"count(case when IMAC_ICATUALIZADO = 1 then 1 end) as qtd1, "
					 +	"count(case when IMAC_ICATUALIZADO = 2 then 1 end) as qtd2, "
					 +	"count(*) as qtd3 "
					 +	"from cadastro.imovel_atlz_cadastral imac "
					 +	"inner join cadastro.localidade loc on loc.loca_id = imac.loca_id "
					 +	"where empr_id = :idEmpresa and imac_icdadosretorno = 1 ";
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and loc.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdUnidade() != null){
				consulta += "and loc.uneg_id = :idUnidade ";
				parameters.put("idUnidade", Integer.parseInt(helper.getIdUnidade()));
			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and loc.loca_id >= :idLocalidadeInicial AND loc.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and imac.imac_cdsetorcomercial >= :idSetorInicial AND imac.imac_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and imac.imac_nnquadra >= :idQuadraInicial AND imac.imac_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += "and imac.cadt_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and imac.imac_id in (Select imac_id from cadastro.retorno_atlz_cadastral "  
						 + "where usur_id = :idAnalista) ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and imac.imac_id in (Select imac_id from cadastro.retorno_atlz_cadastral " 
						 + "where matc_id = :idTipoInconsistencia) ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			consulta += "group by loc.loca_id, loc.loca_nmlocalidade, imac_dtrecebimentomovimento, ptac_id "
					 +	"order by loc.loca_id, imac_dtrecebimentomovimento ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER) //0
					.addScalar("nomeLocalidade", Hibernate.STRING) //1
					.addScalar("dataRecebimento", Hibernate.DATE) //2
					.addScalar("parametro", Hibernate.INTEGER) //3
					.addScalar("qtd1", Hibernate.INTEGER) //4 Qtde Retornados Atualizados
					.addScalar("qtd2", Hibernate.INTEGER) //5 Qtde Retonardos Inconsistentes
					.addScalar("qtd3", Hibernate.INTEGER); //6 Qtde Retornados
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC 1315] Gerar Resumo da Posição de Atualização Cadastral por Pacote Enviados
	 * 
	 * @author Davi Menezes
	 * @since 02/05/2012
	 * 
	 * @param Helper, ptac_id
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarResumoPosicaoAtualizacaoCadastralPacoteEnviado(DadosResumoMovimentoAtualizacaoCadastralHelper helper, Integer parametro)
		throws ErroRepositorioException{
		
		Integer quantidade = 0;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta += "Select count(*) as qtdEnviados "
					 +	"from cadastro.imovel_atlz_cadastral imac "
					 +	"inner join cadastro.localidade loc on loc.loca_id = imac.loca_id "
					 +	"where empr_id = :idEmpresa and imac_icdadosretorno = 2 "
					 +	"and ptac_id = :parametro and loc.loca_id = :idLocalidade ";
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			parameters.put("parametro", parametro);
			parameters.put("idLocalidade", Integer.parseInt(helper.getIdLocalidadeInicial()));
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and loc.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdUnidade() != null){
				consulta += "and loc.uneg_id = :idUnidade ";
				parameters.put("idUnidade", Integer.parseInt(helper.getIdUnidade()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and imac.imac_cdsetorcomercial >= :idSetorInicial AND imac.imac_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and imac.imac_nnquadra >= :idQuadraInicial AND imac.imac_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			query = session.createSQLQuery(consulta)
					.addScalar("qtdEnviados", Hibernate.INTEGER); //0
					 
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			quantidade = (Integer) query.uniqueResult();
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return quantidade;
	}
	
	/**
	 * [UC 1313] Quantidade total de mensagens Pendentes Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @since 20/04/2012
	 * 
	 * @param idEmpresa, idLocalidade
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeMensagensPendentesAtualizacaoCadastal(Integer idEmpresa, Integer idLocalidade)
		throws ErroRepositorioException{
		
		Integer quantidade = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try{
			consulta += "select count(rac.reat_id) quantidade "
					 +	"from CADASTRO.retorno_atlz_cadastral rac "
					 +	"inner join CADASTRO.imovel_atlz_cadastral imac ON rac.imac_id = imac.imac_id " 
					 +	"where imac.empr_id = :idEmpresa "
					 +	"and imac.loca_id = :idLocalidade ";
			
			quantidade = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("idLocalidade", idLocalidade).uniqueResult();
			
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return quantidade;
	}
	
	/**
	 * [UC 1326] Consultar Setores/Quadra Não Migrados para o Admin
	 * 
	 * @author Davi Menezes
	 * @since 30/04/2012
	 * 
	 * @param idLocalidade, codigoSetor, numeroQuadra
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object []> pesquisarSetoresNaoMigrados(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			//Pesquisar Setor Comercial
//			consulta = 	"Select distinct sc.stcm_cdsetorcomercial as codSetor "
//					 +	"from cadastro.imovel imov "
//					 +	"inner join cadastro.setor_comercial sc on sc.stcm_id = imov.stcm_id "
//					 +	"where imov_icexclusao = 2 and imov.loca_id = :idLocalidade "
//					 +	"and (imov_id not in (select imov_id from cadastro.imovel_atlz_cadastral where imac_icdadosretorno = 2 and loca_id = :idLocalidade) "
//					 +	"or imov_id in (select imov_id from cadastro.retorno_atlz_cadastral reat where reat_cdopcaoalteracao = 3 and imov.imov_id = reat.imov_id)) "
//					 +	"order by sc.stcm_cdsetorcomercial ";
			
			consulta = 	"select distinct sc.stcm_cdsetorcomercial as codSetor "
					+	"from cadastro.imovel imov "
					+	"inner join cadastro.setor_comercial sc on sc.stcm_id = imov.stcm_id " 
					+	"inner join cadastro.area_atlz_cadastral area on area.loca_id = imov.loca_id and area.arac_cdsituacao = 1 "
					+	"left join cadastro.retorno_atlz_cadastral reat on reat.imov_id = imov.imov_id and reat.reat_cdopcaoalteracao = 3 " 
					+	"where imov.loca_id = :idLocalidade "
					+	"and not exists " 
					+	"(select imovelAtua.imov_id from cadastro.imovel_atlz_cadastral imovelAtua "
					+	"where imovelAtua.imov_id = imov.imov_id and imac_icdadosretorno = 2) "
					+	"and imov_icexclusao = 2 "
					+	"and (area.stcm_id = sc.stcm_id or area.stcm_id is null) "
					+	"order by sc.stcm_cdsetorcomercial ";
			
			
			parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			
			query = session.createSQLQuery(consulta)
					.addScalar("codSetor", Hibernate.INTEGER);
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
	
			}
			
			retorno = query.list();
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Collection<Object []> pesquisarQuadrasNaoMigradas(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			//Pesquisar Quadra
//			consulta = 	"Select distinct qd.qdra_nnquadra as numeroQuadra "
//					 +	"from cadastro.imovel imov "
//					 +	"inner join cadastro.setor_comercial sc on sc.stcm_id = imov.stcm_id "
//					 +	"inner join cadastro.quadra qd on qd.qdra_id = imov.qdra_id "
//					 +	"where imov_icexclusao = 2 and imov.loca_id = :idLocalidade and stcm_cdsetorcomercial = :codSetorComercial "
//				     +	"and (imov_id not in (select imov_id from cadastro.imovel_atlz_cadastral where imac_icdadosretorno = 2 and loca_id = :idLocalidade ) " 
//				     +	"or imov_id in (select imov_id from cadastro.retorno_atlz_cadastral reat where reat_cdopcaoalteracao = 3 and imov.imov_id = reat.imov_id)) "
//					 +	"order by qd.qdra_nnquadra ";
			
			consulta = 	"select distinct qd.qdra_nnquadra as numeroQuadra "
					+	"from cadastro.imovel imov "
					+	"inner join cadastro.setor_comercial sc on sc.stcm_id = imov.stcm_id " 
					+	"inner join cadastro.quadra qd on qd.qdra_id = imov.qdra_id "
					+	"left join cadastro.retorno_atlz_cadastral reat on reat.imov_id = imov.imov_id and reat.reat_cdopcaoalteracao = 3 " 
					+	"where imov.loca_id = :idLocalidade "
					+	"and sc.stcm_cdsetorcomercial = :codSetorComercial " 
					+	"and imov_icexclusao = 2 "
					+	"and not exists "
					+	"(select imovelAtua.imov_id from cadastro.imovel_atlz_cadastral imovelAtua "
					+	"where imovelAtua.imov_id = imov.imov_id and imac_icdadosretorno = 2) "
					+	"and imov_icexclusao = 2 "
					+	"order by qd.qdra_nnquadra ";			
			
			parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			parameters.put("codSetorComercial", Integer.parseInt(codigoSetor));
			
			query = session.createSQLQuery(consulta)
					.addScalar("numeroQuadra", Hibernate.INTEGER);
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Collection<Object []> pesquisarImoveisNaoMigrados(String idLocalidade, String codigoSetor, Collection<Integer> numeroQuadra)
		throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			//Pesquisar Imovel
//			consulta = 	"Select distinct(imov_id) as imovel "
//					 +	"from cadastro.imovel imov "
//					 +	"inner join cadastro.setor_comercial sc on sc.stcm_id = imov.stcm_id "
//					 +	"inner join cadastro.quadra qd on qd.qdra_id = imov.qdra_id "
//					 +	"where imov_icexclusao = 2 and imov.loca_id = :idLocalidade "
//					 +	"and stcm_cdsetorcomercial = :codSetorComercial and qd.qdra_nnquadra in (:numeroQuadra) " 
//				     +	"and (imov_id not in (select imov_id from cadastro.imovel_atlz_cadastral where imac_icdadosretorno = 2 and loca_id = :idLocalidade) " 
//				     +	"or imov_id in (select imov_id from cadastro.retorno_atlz_cadastral reat where reat_cdopcaoalteracao = 3 and imov.imov_id = reat.imov_id)) "
//					 +	"order by imov_id ";
			
			consulta = 	"select distinct(imov.imov_id) as imovel " 
					+	"from cadastro.imovel imov "
					+	"inner join cadastro.setor_comercial sc on sc.stcm_id = imov.stcm_id " 
					+	"inner join cadastro.quadra qd on qd.qdra_id = imov.qdra_id "
					+	"left join cadastro.retorno_atlz_cadastral reat on reat.imov_id = imov.imov_id and reat.reat_cdopcaoalteracao = 3 "
					+	"where imov.loca_id = :idLocalidade  "
					+	"and stcm_cdsetorcomercial = :codSetorComercial "
					+	"and qd.qdra_nnquadra in (:numeroQuadra)  "
					+	"and imov_icexclusao = 2 "
					+	"and not exists "
					+	"(select imovelAtua.imov_id from cadastro.imovel_atlz_cadastral imovelAtua "
					+	"where imovelAtua.imov_id = imov.imov_id and imac_icdadosretorno = 2) "
					+	"order by imov.imov_id ";
			
			parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			parameters.put("codSetorComercial", Integer.parseInt(codigoSetor));
			parameters.put("numeroQuadra", numeroQuadra);
			
			query = session.createSQLQuery(consulta)
					.addScalar("imovel", Hibernate.INTEGER);
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC 1326] Consultar Setores/Quadra Retornados do Admin
	 * 
	 * @author Davi Menezes
	 * @since 30/04/2012
	 * 
	 * @param idLocalidade, codigoSetor, numeroQuadra
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarSetoresRetornados(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			//Pesquisar Setor Comercial
			consulta = 	"SELECT distinct imac1.imac_cdsetorcomercial codSetor "
					 +	"FROM CADASTRO.imovel_atlz_cadastral imac1 "
					 +	"inner join CADASTRO.imovel_atlz_cadastral imac2 on imac1.ptac_id = imac2.ptac_id and imac1.imov_id = imac2.imov_id "
					 +	"where imac1.loca_id = :idLocalidade "
					 +	"and  imac2.imac_icdadosretorno = 1 "
					 +	"and  imac1.imac_icdadosretorno = 2 "
					 +	"order by imac1.imac_cdsetorcomercial ";
	
			parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			
			query = session.createSQLQuery(consulta)
					.addScalar("codSetor", Hibernate.INTEGER);
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}
			
			retorno = query.list();
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Collection<Object []> pesquisarQuadrasRetornadas(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			//Pesquisar Quadra
			consulta = 	"SELECT distinct imac1.imac_nnquadra numeroQuadra "
					 +	"FROM CADASTRO.imovel_atlz_cadastral imac1 "
					 +	"inner join CADASTRO.imovel_atlz_cadastral imac2 on imac1.ptac_id = imac2.ptac_id and imac1.imov_id = imac2.imov_id "
					 +	"where imac1.imac_icdadosretorno = 2 "
					 +	"and imac2.imac_icdadosretorno = 1 "
					 +	"and imac1.loca_id = :idLocalidade "
					 +	"and imac1.imac_cdsetorcomercial = :codSetor "
					 +	"order by imac1.imac_nnquadra ";
	
			parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			parameters.put("codSetor", Integer.parseInt(codigoSetor));
			
			query = session.createSQLQuery(consulta)
					.addScalar("numeroQuadra", Hibernate.INTEGER);
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Collection<Object []> pesquisarImoveisRetornados(String idLocalidade, String codigoSetor, Collection<Integer> numeroQuadra)
		throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			//Pesquisar Imovel
			consulta = 	"SELECT distinct imac1.imov_id imovel "
					 +	"FROM CADASTRO.imovel_atlz_cadastral imac1 "
					 +	"inner join CADASTRO.imovel_atlz_cadastral imac2 on imac1.ptac_id = imac2.ptac_id and imac1.imov_id = imac2.imov_id "
					 +	"where imac1.imac_icdadosretorno = 2 "
					 +	"and imac2.imac_icdadosretorno = 1 "
					 +	"and imac1.loca_id = :idLocalidade "
					 +	"and imac1.imac_cdsetorcomercial = :codSetor "
					 +	"and imac1.imac_nnquadra in (:numeroQuadra) "
					 +	"order by imac1.imov_id ";
	
			parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			parameters.put("codSetor", Integer.parseInt(codigoSetor));
			parameters.put("numeroQuadra", numeroQuadra);
			
			query = session.createSQLQuery(consulta)
					.addScalar("imovel", Hibernate.INTEGER);
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}
			
			retorno = query.list();
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosHidrometroInstalacaoHistorico(Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection retorno = null;

		try {

			consulta = "select hidr.hidr_nnhidrometro numeroHidrometro, " 
					+"hidi.hidi_dtinstalacaohidrometro dataInstalacao, " 
					+"hidi.medt_id idMedicaoTipo, "
					+"hidi.hili_id idHidrometroLocalInstalacao, " 
					+"hidi.hipr_id idHidrometroProtecao," 
					+"hidi.hidi_nnleitinstalacaohidmt numeroLeitura, "
					+"hidi.hidi_iccavalete indicadorCavalete "
					+"from MICROMEDICAO.hidrometro_inst_hist hidi "
					+"inner join micromedicao.hidrometro hidr on hidr.hidr_id = hidi.hidr_id and hidi_dtretiradahidrometro is null "
					+"where lagu_id = :idImovel or imov_id = :idImovel";

			retorno = session.createSQLQuery(consulta)
					.addScalar("numeroHidrometro", Hibernate.STRING)
					.addScalar("dataInstalacao", Hibernate.DATE)
					.addScalar("idMedicaoTipo", Hibernate.INTEGER)
					.addScalar("idHidrometroLocalInstalacao", Hibernate.INTEGER)
					.addScalar("idHidrometroProtecao", Hibernate.INTEGER)
					.addScalar("numeroLeitura", Hibernate.INTEGER)
					.addScalar("indicadorCavalete", Hibernate.SHORT)
					.setInteger("idImovel", idImovel)
					.list();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 12/04/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarImovelSubCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		
		Collection<ImovelSubcategoriaAtualizacaoCadastral> imovelSubcategoria = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSession();
		
		try{
			consulta =  "Select scac "
					 +	"FROM ImovelSubcategoriaAtualizacaoCadastral scac "
					 +	"WHERE scac.imovelAtualizacaoCadastral.id = :idImovelAtualizacaoCadastral ";
			
			imovelSubcategoria = (Collection<ImovelSubcategoriaAtualizacaoCadastral>) session.createQuery(consulta)
									.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
									.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return imovelSubcategoria;
	}
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0009] - Validar Atualização de Logradouro
	 * @author Arthur Carvalho
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean  existeLogradouroGsanAdmin(Integer idLogradouro) throws ErroRepositorioException {

		Integer retorno = 0;
		boolean existe = false;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +=" SELECT COUNT(*) contador " 
				+ " FROM cadastro.logradouro_gsan_admin  " 
				+ " where load_id = :idLogradouro";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("contador", Hibernate.INTEGER)
				
				.setInteger("idLogradouro", idLogradouro)
				.setMaxResults(1).uniqueResult();
			
			if (retorno != null && retorno.intValue() > 0 ) {
				existe = true;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return existe;
	}
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0009] - Validar Atualização de Logradouro
	 * @author Arthur Carvalho
	 * 
	 * @param idBairro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean  existeBairroGsanAdmin(Integer idBairro) throws ErroRepositorioException {

		Integer retorno = 0;
		boolean existe = false;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +=" SELECT COUNT(*) contador " 
				+ " FROM cadastro.bairro_gsan_admin  " 
				+ " where baad_id = :idBairro";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("contador", Hibernate.INTEGER)
				.setInteger("idBairro", idBairro)
				.setMaxResults(1).uniqueResult();
			
			if (retorno != null && retorno.intValue() > 0 ) {
				existe = true;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return existe;
	}

	
	/**
	 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
	 * [SB0001]-Inserir Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisarMaiorCodigoBairro(Integer idMunicipio) throws ErroRepositorioException {

		Integer retorno = 0;
		
		Session session = HibernateUtil.getSession();
		String consulta = "";

		consulta = " select max(bair_cdbairro) codigo from cadastro.bairro"
				 + " where muni_id = :idMunicipio";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("codigo", Hibernate.INTEGER)
				.setInteger("idMunicipio", idMunicipio)
				.setMaxResults(1).uniqueResult();
			
			if ( retorno == null ) {
				retorno = 0;
			}
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
	 * [SB0002]-Atualizar Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarBairro(Integer idBairro, String nomeBairro)throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizar;

		try {

			atualizar = "UPDATE Bairro "
					+ "SET bair_nmbairro = :nomeBairro , bair_tmultimaalteracao = :ultimaAlteracao " 
					+ "WHERE bair_id = :idBairro ";

			session.createQuery(atualizar)
				.setString("nomeBairro",nomeBairro)
				.setInteger("idBairro", idBairro)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
	 * [SB0004]-Atualizar Logradouro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarLogradouro(LogradouroAdmin logradouroAdmin)throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizar;

		try {

			atualizar = "UPDATE Logradouro "
					+ "SET logr_nmlogradouro = :nomeLogradouro , logr_tmultimaalteracao = :ultimaAlteracao ";
			
			if ( logradouroAdmin.getLogradouroTipo() != null && logradouroAdmin.getLogradouroTipo().getId() != null ) {
				atualizar += ", lgtp_id =  " + logradouroAdmin.getLogradouroTipo().getId() + " ";
			}
			if ( logradouroAdmin.getLogradouroTitulo() != null && logradouroAdmin.getLogradouroTitulo().getId() != null ) {
				atualizar += ", lgtt_id =  " + logradouroAdmin.getLogradouroTitulo().getId() + " ";
			}
			
			atualizar +="WHERE logr_id = :idLogradouro ";

			session.createQuery(atualizar)
				.setString("nomeLogradouro",logradouroAdmin.getNomelogradouro())
				.setInteger("idLogradouro", logradouroAdmin.getId())
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade
	 * 
	 * @author Arthur Carvalho
	 * @since 12/05/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Localidade> pesquisarLocalidadeAreaAtualizacaoCadastral(Integer idEmpresa) throws ErroRepositorioException{
		
		Collection<Localidade> areaAtualizacaoCadastral = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSession();
		
		try{
			consulta =  "Select distinct(loca) "
					 +	"FROM AreaAtualizacaoCadastral area "
					 +  "inner join area.localidade loca "
					 +	"WHERE area.empresa.id = :idEmpresa "
					 +  "AND area.codigoSituacao = :situacao "
					 +  "ORDER BY loca.descricao ";
			
			areaAtualizacaoCadastral = (Collection<Localidade>) session.createQuery(consulta)
									.setInteger("idEmpresa", idEmpresa)
									.setShort("situacao", ConstantesSistema.SIM)
									.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return areaAtualizacaoCadastral;
	}
	
	/**
	 * Pesquisar dados do Retorno Atualização Cadastral
	 * 
	 * @return RetornoAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<RetornoAtualizacaoCadastral> pesquisarRetornoAtualizacaoCadastralPendenteLogradouro() throws ErroRepositorioException {
	
		Collection<RetornoAtualizacaoCadastral> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT reat"
				     + " FROM RetornoAtualizacaoCadastral reat"
					 + " inner join fetch reat.imovelAtualizacaoCadastral imac"
				     + " WHERE reat.codigoSituacao = :situacao" 
					 + " AND reat.codigoAlteracao = :aprovado"
				     + " AND reat.dataAtualizacao is null";
			
			colecao = (Collection<RetornoAtualizacaoCadastral>)session.createQuery(consulta)
										.setShort("situacao", RetornoAtualizacaoCadastral.PENDENTE_POR_LOGRADOURO)
										.setShort("aprovado", RetornoAtualizacaoCadastral.APROVADO)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	}
	
	/**
	 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisarIdLogradouroGsanAdmin(Integer idLogradouro) throws ErroRepositorioException {

		Integer retorno = 0;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +=" SELECT logr_id idLogr " 
				+ " FROM cadastro.logradouro_gsan_admin  " 
				+ " where load_id = :idLogradouro";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("idLogr", Hibernate.INTEGER)
				.setInteger("idLogradouro", idLogradouro)
				.setMaxResults(1).uniqueResult();
			
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
	 * [SB0002]-Atualizar Bairro 
	 * @author Arthur Carvalho
	 * 
	 * @param idBairro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisaridBairroGsanAdmin(Integer idBairro) throws ErroRepositorioException {

		Integer retorno = 0;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +=" SELECT bair_id idBair " 
				+ " FROM cadastro.bairro_gsan_admin  " 
				+ " where baad_id = :idBairro";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("idBair", Hibernate.INTEGER)
				.setInteger("idBairro", idBairro)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	

	
	/**
	 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
	 *  [SB0003]-Atualizar Logradouro Bairro 
	 * 
	 * @author Arthur Carvalho
	 * @since 08/05/2012
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarLogradouroBairroDoImovel(Integer idImovel, Integer idLogradouroBairro) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String update;

		try {

			update =  "update gcom.cadastro.imovel.Imovel set "
					+ "lgbr_id = :idLogradouroBairro, " 
					+ "imov_tmultimaalteracao = :dataAtual "
					+ "where imov_id = :imovelId ";

			session.createQuery(update)
			.setInteger("imovelId",idImovel)
			.setInteger("idLogradouroBairro", idLogradouroBairro)
			.setDate("dataAtual", new Date()).executeUpdate();


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
	 *  [SB0004]-Atualizar Logradouro Cep
	 * 
	 * @author Arthur Carvalho
	 * @since 08/05/2012
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarLogradouroCepDoImovel(Integer idImovel, Integer idLogradouroCep) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String update;

		try {

			update =  "update gcom.cadastro.imovel.Imovel set "
					+ "lgcp_id = :idLogradouroCep, " 
					+ "imov_tmultimaalteracao = :dataAtual "
					+ "where imov_id = :imovelId ";

			session.createQuery(update)
			.setInteger("imovelId",idImovel)
			.setInteger("idLogradouroCep", idLogradouroCep)
			.setDate("dataAtual", new Date()).executeUpdate();


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1336] Relatorio por tipo de servico por (PARCELAMENTO meio de solicitacao
	 * 
	 * @author Carlos Chaves
	 * @date 04/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorMeioSolicitacao(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT CASE WHEN ( su.usur_icinternet = 1 ) THEN 'INTERNET' ELSE " +
						"'BALCAO' END as DESCRICAO, COUNT(*) as QUANT FROM COBRANCA.parcelamento cp " +
						"JOIN SEGURANCA.usuario su on cp.USUR_ID = su.USUR_ID " +
						"WHERE cp.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal ";

			if(helper.getColecaoMeio() != null){
			
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			
			}
		
			
					
			consulta += 
						"GROUP BY " +
						"CASE WHEN ( su.usur_icinternet = 1 ) THEN 'INTERNET' ELSE 'BALCAO' END "+
						"ORDER BY DESCRICAO";

		
			query = session.createSQLQuery(consulta).addScalar("descricao",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));



			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1336] Relatorio por tipo de servico (SegundaVia) por meio de solicitacao
	 * 
	 * @author Carlos Chaves
	 * @date 04/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorMeioSolicitacao(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT CASE WHEN (su.usur_icinternet = 1 ) THEN 'INTERNET' ELSE 'BALCAO' END AS DESCRICAO ," +
						"COUNT(*) AS QUANT " +
						"FROM " +
						"FATURAMENTO.conta_emissao_segundavia ces " +
						"JOIN " +
						"SEGURANCA.usuario su " +
						"ON su.usur_id = ces.usur_id " +
						"WHERE ces.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal ";
	
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i < helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
			
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			
			}
			
			consulta += 
						"GROUP BY " +
						"CASE WHEN (su.usur_icinternet = 1 ) THEN 'INTERNET' ELSE 'BALCAO' END ";

			
			query = session.createSQLQuery(consulta).addScalar("descricao",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));



			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1336] Relatorio por tipo de servico (ExtratoDebito) por meio de solicitacao
	 * 
	 * @author Carlos Chaves
	 * @date 04/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorMeioSolicitacao(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT CASE WHEN ( su.usur_icinternet = 1 )  THEN 'INTERNET' ELSE 'BALCAO' END AS DESCRICAO, " +
					"COUNT(*) AS QUANT " +
					"FROM COBRANCA.cobranca_documento cd " +
					"JOIN " +
					"SEGURANCA.usuario su " +
					"ON su.usur_id = cd.usur_id " +
					"WHERE cd.dotp_id = 14 " +
					"AND cd.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal ";
		
			if(helper.getColecaoMeio() != null){
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			
			}
				
			consulta += 
					"GROUP BY " +
					"CASE WHEN ( su.usur_icinternet = 1 )  THEN 'INTERNET' ELSE 'BALCAO' END"; 

			

			query = session.createSQLQuery(consulta).addScalar("descricao",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));



			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
     * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

     * @author Pintovisk
     * @since 10/05/2012
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Integer[]> pesquisarColecaoQuadraTabelasTemporarias(String idLocalidade, String colecaoCodigoSetor)
            throws ErroRepositorioException {

        Collection<Integer[]> retorno = null;
       
        Session session = HibernateUtil.getSession();
        String consulta = "";
        consulta = "select distinct(qdra.qdra_nnquadra) numero,stcm.stcm_cdsetorcomercial codigoSetor " 
        	+ "from cadastro.quadra qdra "
            + "inner join cadastro.imovel imov on qdra.qdra_id = imov.qdra_id "
            + "inner join cadastro.setor_comercial stcm on stcm.stcm_id = qdra.stcm_id "
            + "inner join cadastro.localidade loca on loca.loca_id = stcm.loca_id "
            + "where stcm.stcm_cdsetorcomercial in (" + colecaoCodigoSetor + " ) "
            + "and loca.loca_id = :idLocalidade "
            + "and (imov.siac_id is null or imov.siac_id = 0) "
            + "and imov.imov_icexclusao <> 1 "
            +  "ORDER BY 1";

        try {
           
            retorno = (Collection<Integer[]>) session.createSQLQuery(consulta).
            		addScalar("numero", Hibernate.INTEGER).
            		addScalar("codigoSetor", Hibernate.INTEGER).
            		setInteger("idLocalidade", Integer.valueOf(idLocalidade)).
            		list();
           
        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
	
	/**
	 * [UC1336] Relatorio por tipo de servico (Averbacao) por meio de solicitacao
	 * 
	 * @author Carlos Chaves
	 * @date 04/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorMeioSolicitacao(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT ms.meso_dsmeiosolicitacao AS descricao, " +
						"COUNT(*) AS QUANT " +
						"FROM " +
						"ATENDIMENTOPUBLICO.registro_atendimento ra " +
						"JOIN ATENDIMENTOPUBLICO.meio_solicitacao ms " +
						"ON ra.meso_id = ms.meso_id " +
						"WHERE ra.step_id = 3 AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal ";
			

			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ms.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			consulta += 
					"GROUP BY ra.meso_id, ms.meso_dsmeiosolicitacao " +
					"ORDER BY descricao ";
			

			query = session.createSQLQuery(consulta).addScalar("descricao",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));



			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1336] Relatorio por tipo de servico (Revisao Conssumo) por meio de solicitacao
	 * 
	 * @author Carlos Chaves
	 * @date 04/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorMeioSolicitacao(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
				consulta = "SELECT ms.meso_dsmeiosolicitacao AS descricao, " +
						"COUNT(*) AS QUANT " +
						"FROM " +
						"ATENDIMENTOPUBLICO.registro_atendimento ra " +
						"LEFT JOIN ATENDIMENTOPUBLICO.meio_solicitacao ms " +
						"ON ra.meso_id = ms.meso_id " +
						"WHERE ( ra.step_id = 108 OR ra.step_id = 109 ) " +
						"AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal ";
				
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
					
					String meiosSolicitacao = "";
					
					for(int i=0; i<helper.getColecaoMeio().length; i++ ){
						meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
					}
					
					//RetirarUltima virgula		
					meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
					
					consulta += " AND ms.meso_id IN ("+meiosSolicitacao+") ";
				
			}
			
			consulta += 
					"GROUP BY ra.meso_id, ms.meso_dsmeiosolicitacao " +
					"ORDER BY descricao"; 	
			
			
			query = session.createSQLQuery(consulta).addScalar("descricao",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER)
					.setTimestamp("periodoInicial", 
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
					.setTimestamp("periodoFinal", 
							Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));
			
			
			/* session.createSQLQuery(consulta)
			.addScalar("idOS", Hibernate.INTEGER).
			
			setDate("dataProgramacaoInicial",Util.formatarDataInicial(helper.getPeriodoInicial()))
			.setDate(
					"dataProgramacaoFinal",
					Util.formatarDataFinal(dataProgramacaoFinal)).list();
*/


			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1334] Relatorio por tipo de servico (PARCELAMENTO) por Usuario
	 * 
	 * @author Carlos Chaves
	 * @date 10/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorUsuario(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT "+
						" CASE WHEN ( su.usur_icinternet = 1 )  THEN 'INTERNET' "+
						"      WHEN ( su.usur_icinternet = 2 )  THEN 'BALCAO' "+
						" 	   END AS MEIO, "+
						" su.usur_nmusuario AS NOME, "+
						" su.usur_nmlogin AS LOGIN, "+
						" count (*) as QUANT, "+
						" uo.unid_dsunidade AS LOTACAO "+
						" FROM "+
						" COBRANCA.parcelamento parc "+
						" JOIN SEGURANCA.usuario su ON parc.usur_id = su.usur_id "+
						" LEFT JOIN CADASTRO.unidade_organizacional uo on uo.unid_id = su.unid_id "+
						" WHERE parc.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal ";

			//Filtro unidade superior
			if(! helper.getIdUnidadeSuperior().equals("") && helper.getIdUnidadeSuperior() !=null ){
				String idUnidadeSuperior = helper.getIdUnidadeSuperior();
				consulta += " AND uo.unid_idsuperior =("+idUnidadeSuperior+") "; 
			}
			
			//filtro por unidade organizacional
			if(helper.getColecaoUnidadeOrganizacional() != null){
				
				String unidadesOrganizacionais = "";
				
				for(UnidadeOrganizacional u : helper.getColecaoUnidadeOrganizacional() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						unidadesOrganizacionais += u.getId() + ",";	
					}
				}
				
				if(!unidadesOrganizacionais.equals("")){
					unidadesOrganizacionais = unidadesOrganizacionais.substring(0, unidadesOrganizacionais.length()-1);
					consulta += " AND su.unid_id IN ("+unidadesOrganizacionais+") ";	
				}
				
			}
			
			//filtro por usuarios
			if(helper.getColecaoUsuario() != null){
				
				String usuarios = "";
				
				for(Usuario u : helper.getColecaoUsuario() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						usuarios += u.getId() + ",";	
					}
				}
				
				
				
				if(!usuarios.equals("")){
					usuarios = usuarios.substring(0, usuarios.length()-1);
					consulta += " AND parc.usur_id IN ("+usuarios+") ";	
				}
				
			}
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			
			} 
		
			consulta += 
					" GROUP BY CASE WHEN ( su.usur_icinternet = 1 )  THEN 'INTERNET' "+
							"      WHEN ( su.usur_icinternet = 2 )  THEN 'BALCAO' "+
							" END, su.usur_nmusuario, su.usur_nmlogin, unid_dsunidade "+   
							" ORDER BY su.usur_nmusuario ";  
			
			
			query = session.createSQLQuery(consulta).addScalar("NOME",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).addScalar("MEIO", Hibernate.STRING)
					.addScalar("LOGIN", Hibernate.STRING).addScalar("LOTACAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));



			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC1334] Relatorio por tipo de servico (Segunda Via) por Usuario
	 * 
	 * @author Carlos Chaves
	 * @date 10/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorUsuario(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT CASE WHEN  ( su.usur_icinternet = 1 ) THEN 'INTERNET' "+
						"WHEN  ( su.usur_icinternet = 2 ) THEN 'BALCAO' END AS MEIO, "+
						"su.usur_nmusuario AS NOME, "+
						"su.usur_nmlogin AS LOGIN, "+
						"COUNT(*) AS QUANT, "+
						"uo.unid_dsunidade AS LOTACAO "+
						"FROM FATURAMENTO.conta_emissao_segundavia seg "+
						"JOIN SEGURANCA.usuario su "+
						"ON su.usur_id = seg.usur_id "+
						"LEFT JOIN CADASTRO.unidade_organizacional uo ON uo.unid_id = su.unid_id "+
						"WHERE seg.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal ";

			//Filtro unidade superior
			if(! helper.getIdUnidadeSuperior().equals("") && helper.getIdUnidadeSuperior() !=null ){
				String idUnidadeSuperior = helper.getIdUnidadeSuperior();
				consulta += " AND uo.unid_idsuperior =("+idUnidadeSuperior+") "; 
			}
			
			//filtro por unidade organizacional
			if(helper.getColecaoUnidadeOrganizacional() != null){
				
				String unidadesOrganizacionais = "";
				
				for(UnidadeOrganizacional u : helper.getColecaoUnidadeOrganizacional() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						unidadesOrganizacionais += u.getId() + ",";	
					}
				}
				
				if(!unidadesOrganizacionais.equals("")){
					unidadesOrganizacionais = unidadesOrganizacionais.substring(0, unidadesOrganizacionais.length()-1);
					consulta += " AND su.unid_id IN ("+unidadesOrganizacionais+") ";	
				}
				
			}
			
			//filtro por usuarios
			if(helper.getColecaoUsuario() != null){
				
				String usuarios = "";
				
				for(Usuario u : helper.getColecaoUsuario() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						usuarios += u.getId() + ",";	
					}
				}
				
				if(!usuarios.equals("")){
					usuarios = usuarios.substring(0, usuarios.length()-1);
					consulta += " AND su.usur_id IN ("+usuarios+") ";	
				}
			}
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			
			} 
		
			consulta += 
					"GROUP BY "+ 
					"CASE WHEN  ( su.usur_icinternet = 1 ) THEN 'INTERNET' "+
					"WHEN  ( su.usur_icinternet = 2 ) THEN 'BALCAO' END, "+
					"su.usur_nmusuario, "+
					"su.usur_nmlogin, "+
					"uo.unid_dsunidade "+
					"ORDER BY "+
					"su.usur_nmusuario";
			
			
			query = session.createSQLQuery(consulta).addScalar("NOME",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).addScalar("MEIO", Hibernate.STRING)
					.addScalar("LOGIN", Hibernate.STRING).addScalar("LOTACAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	

	/**
	 * [UC1334] Relatorio por tipo de servico (Extrato de debito) por Usuario
	 * 
	 * @author Carlos Chaves
	 * @date 10/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorUsuario(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT "+
						"CASE WHEN ( su.usur_icinternet = 1 ) THEN ('INTERNET') "+
						"     WHEN ( su.usur_icinternet = 2 ) THEN ('BALCAO') END AS MEIO, "+
						"su.usur_nmusuario as NOME, "+
						"su.usur_nmlogin as LOGIN, "+
						"COUNT(*) AS QUANT, "+
						"uo.unid_dsunidade AS LOTACAO "+
						"FROM COBRANCA.cobranca_documento cd "+
						"JOIN SEGURANCA.usuario su on cd.usur_id  = su.usur_id "+
						"LEFT JOIN CADASTRO.unidade_organizacional uo ON uo.unid_id = su.unid_id "+
						"WHERE cd.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal AND  cd.dotp_id=14 ";

			//Filtro unidade superior
			if(! helper.getIdUnidadeSuperior().equals("") && helper.getIdUnidadeSuperior() !=null ){
				String idUnidadeSuperior = helper.getIdUnidadeSuperior();
				consulta += " AND uo.unid_idsuperior =("+idUnidadeSuperior+") "; 
			}
			
			//filtro por unidade organizacional
			if(helper.getColecaoUnidadeOrganizacional() != null){
				
				String unidadesOrganizacionais = "";
				
				for(UnidadeOrganizacional u : helper.getColecaoUnidadeOrganizacional() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						unidadesOrganizacionais += u.getId() + ",";	
					}
				}
				
				if(!unidadesOrganizacionais.equals("")){
					unidadesOrganizacionais = unidadesOrganizacionais.substring(0, unidadesOrganizacionais.length()-1);
					consulta += " AND su.unid_id IN ("+unidadesOrganizacionais+") ";	
				}
				
			}
			
			//filtro por usuarios
			if(helper.getColecaoUsuario() != null){
				
				String usuarios = "";
				
				for(Usuario u : helper.getColecaoUsuario() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						usuarios += u.getId() + ",";	
					}
				}
				
				if(!usuarios.equals("")){
					usuarios = usuarios.substring(0, usuarios.length()-1);
					consulta += " AND su.usur_id IN ("+usuarios+") ";	
				}
			}
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			
			} 
		
			consulta += 
					"GROUP BY "+ 
					"CASE WHEN  ( su.usur_icinternet = 1 ) THEN 'INTERNET' "+
					"WHEN  ( su.usur_icinternet = 2 ) THEN 'BALCAO' END, "+
					"su.usur_nmusuario, "+
					"su.usur_nmlogin, "+
					"uo.unid_dsunidade "+
					"ORDER BY "+
					"su.usur_nmusuario";
			
			
			query = session.createSQLQuery(consulta).addScalar("NOME",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).addScalar("MEIO", Hibernate.STRING)
					.addScalar("LOGIN", Hibernate.STRING).addScalar("LOTACAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC1334] Relatorio por tipo de servico (Averbacao) por Usuario
	 * 
	 * @author Carlos Chaves
	 * @date 10/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorUsuario(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT "+ 
						"ms.meso_dsmeiosolicitacao AS MEIO, "+
						"su.usur_nmusuario AS NOME, "+
						"su.usur_nmlogin AS LOGIN, "+
						"COUNT(*) AS QUANT, "+
						"uo.unid_dsunidade AS LOTACAO "+
						"FROM CADASTRO.unidade_organizacional uo "+
						"LEFT JOIN ATENDIMENTOPUBLICO.ra_unidade un "+
						"ON uo.unid_id = un.unid_id "+
						"LEFT JOIN ATENDIMENTOPUBLICO.registro_atendimento ra "+
						"ON ra.rgat_id = un.rgat_id "+ 
						"LEFT JOIN SEGURANCA.usuario su "+
						"ON su.usur_id = un.usur_id "+
						"LEFT JOIN ATENDIMENTOPUBLICO.meio_solicitacao ms "+
						"ON ms.meso_id = ra.meso_id "+
						"WHERE attp_id = 1 AND step_id = 3 AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 

			//Filtro unidade superior
			if(! helper.getIdUnidadeSuperior().equals("") && helper.getIdUnidadeSuperior() !=null ){
				String idUnidadeSuperior = helper.getIdUnidadeSuperior();
				consulta += " AND uo.unid_idsuperior =("+idUnidadeSuperior+") "; 
			}
			
			//filtro por unidade organizacional
			if(helper.getColecaoUnidadeOrganizacional() != null){
				
				String unidadesOrganizacionais = "";
				
				for(UnidadeOrganizacional u : helper.getColecaoUnidadeOrganizacional() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						unidadesOrganizacionais += u.getId() + ",";	
					}
				}
				
				if(!unidadesOrganizacionais.equals("")){
					unidadesOrganizacionais = unidadesOrganizacionais.substring(0, unidadesOrganizacionais.length()-1);
					consulta += " AND su.unid_id IN ("+unidadesOrganizacionais+") ";	
				}
				
			}
			
			//filtro por usuarios
			if(helper.getColecaoUsuario() != null){
				
				String usuarios = "";
				
				for(Usuario u : helper.getColecaoUsuario() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						usuarios += u.getId() + ",";	
					}
				}
				
				if(!usuarios.equals("")){
					usuarios = usuarios.substring(0, usuarios.length()-1);
					consulta += " AND su.usur_id IN ("+usuarios+") ";	
				}
			}
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ms.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
		
			consulta += "GROUP BY "+ 
						"su.usur_nmusuario, "+
						"su.usur_nmlogin, "+
						"uo.unid_dsunidade, "+
						"ms.meso_dsmeiosolicitacao "+
						"ORDER BY "+
						"su.usur_nmusuario";
			
			query = session.createSQLQuery(consulta).addScalar("NOME",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).addScalar("MEIO", Hibernate.STRING)
					.addScalar("LOGIN", Hibernate.STRING).addScalar("LOTACAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1334] Relatorio por tipo de servico (Revisao Consumo) por Usuario
	 * 
	 * @author Carlos Chaves
	 * @date 10/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorUsuario(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT "+ 
						"ms.meso_dsmeiosolicitacao AS MEIO, "+
						"su.usur_nmusuario AS NOME, "+
						"su.usur_nmlogin AS LOGIN, "+
						"COUNT(*) AS QUANT, "+
						"uo.unid_dsunidade AS LOTACAO "+
						"FROM CADASTRO.unidade_organizacional uo "+
						"JOIN ATENDIMENTOPUBLICO.ra_unidade un "+
						"ON uo.unid_id = un.unid_id "+
						"JOIN ATENDIMENTOPUBLICO.registro_atendimento ra "+
						"ON ra.rgat_id = un.rgat_id "+ 
						"JOIN SEGURANCA.usuario su "+
						"ON su.usur_id = un.usur_id "+
						"JOIN ATENDIMENTOPUBLICO.meio_solicitacao ms "+
						"ON ms.meso_id = ra.meso_id "+
						"WHERE attp_id = 1 AND ( step_id = 108 OR step_id = 109 )  AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 

			//Filtro unidade superior
			if(! helper.getIdUnidadeSuperior().equals("") && helper.getIdUnidadeSuperior() !=null ){
				String idUnidadeSuperior = helper.getIdUnidadeSuperior();
				consulta += " AND uo.unid_idsuperior =("+idUnidadeSuperior+") "; 
			}
			
			//filtro por unidade organizacional
			if(helper.getColecaoUnidadeOrganizacional() != null){
				
				String unidadesOrganizacionais = "";
				
				for(UnidadeOrganizacional u : helper.getColecaoUnidadeOrganizacional() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						unidadesOrganizacionais += u.getId() + ",";	
					}
				}
				
				if(!unidadesOrganizacionais.equals("")){
					unidadesOrganizacionais = unidadesOrganizacionais.substring(0, unidadesOrganizacionais.length()-1);
					consulta += " AND su.unid_id IN ("+unidadesOrganizacionais+") ";	
				}
				
			}
			
			//filtro por usuarios
			if(helper.getColecaoUsuario() != null){
				
				String usuarios = "";
				
				for(Usuario u : helper.getColecaoUsuario() ){
					
					if(! u.getId().equals("") && u.getId() !=null ){
						usuarios += u.getId() + ",";	
					}
				}
				
				if(!usuarios.equals("")){
					usuarios = usuarios.substring(0, usuarios.length()-1);
					consulta += " AND su.usur_id IN ("+usuarios+") ";	
				}
			}
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ms.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
		
			consulta += "GROUP BY "+ 
						"su.usur_nmusuario, "+
						"su.usur_nmlogin, "+
						"uo.unid_dsunidade, "+
						"ms.meso_dsmeiosolicitacao "+
						"ORDER BY "+
						"su.usur_nmusuario";
			
			query = session.createSQLQuery(consulta).addScalar("NOME",
					Hibernate.STRING)
					.addScalar("QUANT", Hibernate.INTEGER).addScalar("MEIO", Hibernate.STRING)
					.addScalar("LOGIN", Hibernate.STRING).addScalar("LOTACAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));

			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Parcelamento) por Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 14/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade as LOCALIDADE, "+
        " COUNT(*) AS QUANT "+
        " FROM COBRANCA.parcelamento  parc "+
        " JOIN SEGURANCA.usuario su "+
        " ON su.usur_id = parc.usur_id "+
        " JOIN CADASTRO.imovel imo "+
        " ON imo.imov_id = parc.imov_id "+
        " JOIN CADASTRO.localidade loc "+
        " ON loc.loca_id = imo.loca_id "+
        " WHERE  parc.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			}
			
			/* if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1")){
				consulta += " AND imo.loca_id = "+helper.getIdLocalidade();
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") && !helper.getIdUnidadeNegocio().equals("-1") ) {
				consulta += "  AND loc.uneg_id = " +helper.getIdUnidadeNegocio();
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1") ){
				consulta += " AND loc.greg_id = " +helper.getIdGerenciaRegional();
			} */

			consulta += " GROUP BY loc.loca_nmlocalidade "+
					    " ORDER BY loc.loca_nmlocalidade";
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("LOCALIDADE", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Segunda Via) por Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 14/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS LOCALIDADE ,COUNT(*) AS QUANT "+
						" FROM FATURAMENTO.conta_emissao_segundavia seg "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = seg.usur_id "+
						" JOIN CADASTRO.imovel imo "+
						" ON seg.imov_id = imo.imov_id "+
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" WHERE seg.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal "; 
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			/* if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND imo.loca_id = "+helper.getIdLocalidade();
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") && !helper.getIdUnidadeNegocio().equals("-1") ) {
				consulta += "  AND loc.uneg_id = " +helper.getIdUnidadeNegocio();
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1") ){
				consulta += " AND loc.greg_id = " +helper.getIdGerenciaRegional();
			} */

			consulta += " GROUP BY loc.loca_nmlocalidade "+
					    " ORDER BY loc.loca_nmlocalidade";
			
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("LOCALIDADE", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Extrato de debito) por Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 14/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS LOCALIDADE ,COUNT(*) AS QUANT "+
					    " FROM COBRANCA.cobranca_documento cob "+
					    " JOIN SEGURANCA.usuario usu "+
					    " ON usu.usur_id = cob.usur_id "+
					    " JOIN CADASTRO.imovel imo "+
					    " ON cob.imov_id = imo.imov_id "+
					    " JOIN CADASTRO.localidade loc "+
					    " ON loc.loca_id = imo.loca_id "+
					    " WHERE cob.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal "+
					    " AND cob.dotp_id = 14"; 
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			/* if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND imo.loca_id = "+helper.getIdLocalidade();
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") && !helper.getIdUnidadeNegocio().equals("-1") ) {
				consulta += "  AND loc.uneg_id = " +helper.getIdUnidadeNegocio();
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1") ){
				consulta += " AND loc.greg_id = " +helper.getIdGerenciaRegional();
			} */

			consulta += " GROUP BY loc.loca_nmlocalidade "+
					    " ORDER BY loc.loca_nmlocalidade";
			
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("LOCALIDADE", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Averbacao) por Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS LOCALIDADE, COUNT(*) AS QUANT "+
						" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+
						" LEFT JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = ra.imov_id "+
						" LEFT JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" where ra.step_id = 3 " +
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			
			/* if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND ra.loca_id = "+helper.getIdLocalidade();
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") && !helper.getIdUnidadeNegocio().equals("-1") ) {
				consulta += "  AND loc.uneg_id = " +helper.getIdUnidadeNegocio();
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1") ){
				consulta += " AND loc.greg_id = " +helper.getIdGerenciaRegional();
			} */

			consulta += " GROUP BY loc.loca_nmlocalidade "+
					    " ORDER BY loc.loca_nmlocalidade";
			
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("LOCALIDADE", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Revisao de consumo) por Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS LOCALIDADE, COUNT(*) AS QUANT "+
						" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+
						" LEFT JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = ra.imov_id "+
						" LEFT JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" where ( ra.step_id = 108 OR ra.step_id = 109 ) " +
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			
			/* if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") && helper.getOpcaoTotalizacao().equals("localidade")){
				consulta += " AND ra.loca_id = "+helper.getIdLocalidade();
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") && !helper.getIdUnidadeNegocio().equals("-1") && helper.getOpcaoTotalizacao().equals("unidadeNegocio") ) {
				consulta += "  AND loc.uneg_id = " +helper.getIdUnidadeNegocio();
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1") && helper.getOpcaoTotalizacao().equals("gerenciaRegional") ){
				consulta += " AND loc.greg_id = " +helper.getIdGerenciaRegional();
			}*/

			consulta += " GROUP BY loc.loca_nmlocalidade "+
					    " ORDER BY loc.loca_nmlocalidade";
			
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("LOCALIDADE", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Revisao de consumo) por Localidade Agrupando por estado
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstado(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT 'TOTAL PARA O ESTADO' AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+					
						" where (ra.step_id = 108 OR ra.step_id = 109) "+
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));
			


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Revisao de consumo) por Localidade Agrupando por estado gerencia
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstadoGerencia(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT ger.greg_nmregional AS DESCRICAO, COUNT(*) AS QUANT "+
				        " FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+
				        " LEFT JOIN CADASTRO.imovel imo "+
				        " ON imo.imov_id = ra.imov_id "+
				        " LEFT JOIN CADASTRO.localidade loc "+
				        " ON loc.loca_id = imo.loca_id "+
				        " LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+
				        " ON ger.greg_id = loc.greg_id "+
				        " where (ra.step_id = 108 OR ra.step_id = 109) "+ 
				        " AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
						
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegional();
							
			}
			
			consulta+=" GROUP BY ger.greg_nmregional "+
					  " ORDER BY ger.greg_nmregional";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Revisao de consumo) por Localidade Agrupando por estado negocio
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstadoNegocio(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT neg.uneg_nmunidadenegocio AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+ 
						" LEFT JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = ra.imov_id "+
						" LEFT JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+ 
						" LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" LEFT JOIN CADASTRO.unidade_negocio neg "+
						" ON neg.uneg_id = loc.uneg_id "+
						" where (ra.step_id = 108 OR ra.step_id = 109 ) "+
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
						
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") & !helper.getIdUnidadeNegocio().equals("-1") ){
				consulta+="AND neg.uneg_id = " +helper.getIdUnidadeNegocio(); 
			}
			
			consulta+=" GROUP BY neg.uneg_nmunidadenegocio "+
					  " ORDER BY neg.uneg_nmunidadenegocio";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Revisao de consumo) por Localidade Agrupando por estado gerencia
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstadoLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta; 

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS DESCRICAO, COUNT(*) AS QUANT "+
					 	" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+ 
					    " LEFT JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = ra.imov_id "+
						" LEFT JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+ 
						" LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" where (ra.step_id = 108 OR ra.step_id = 109) "+
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
						
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND ra.loca_id = "+helper.getIdLocalidade();
			}
			
			consulta+=" GROUP BY loc.loca_nmlocalidade "+
					  " ORDER BY loc.loca_nmlocalidade";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Averbacao) por Localidade Agrupando por estado
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstado(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT 'TOTAL PARA O ESTADO' AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+					
						" where ra.step_id = 3 "+
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));

			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Averbacao) por Localidade Agrupando por estado gerencia
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstadoGerencia(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT ger.greg_nmregional AS DESCRICAO, COUNT(*) AS QUANT "+
				        " FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+
				        " LEFT JOIN CADASTRO.imovel imo "+
				        " ON imo.imov_id = ra.imov_id "+
				        " LEFT JOIN CADASTRO.localidade loc "+
				        " ON loc.loca_id = imo.loca_id "+
				        " LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+
				        " ON ger.greg_id = loc.greg_id "+
				        " where ra.step_id = 3 "+ 
				        " AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
						
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegional();
							
			}
			
			consulta+=" GROUP BY ger.greg_nmregional "+
					  " ORDER BY ger.greg_nmregional";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Averbacao) por Localidade Agrupando por estado negocio
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstadoNegocio(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT neg.uneg_nmunidadenegocio AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+ 
						" LEFT JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = ra.imov_id "+
						" LEFT JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+ 
						" LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" LEFT JOIN CADASTRO.unidade_negocio neg "+
						" ON neg.uneg_id = loc.uneg_id "+
						" where ra.step_id = 3 "+
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
						
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") & !helper.getIdUnidadeNegocio().equals("-1") ){
				consulta+="AND neg.uneg_id = " +helper.getIdUnidadeNegocio(); 
			}
			
			consulta+=" GROUP BY neg.uneg_nmunidadenegocio "+
					  " ORDER BY neg.uneg_nmunidadenegocio";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Averbacao) por Localidade Agrupando por estado localidade
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstadoLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS DESCRICAO, COUNT(*) AS QUANT "+
					 	" FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+ 
					    " LEFT JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = ra.imov_id "+
						" LEFT JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+ 
						" LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" where ra.step_id = 3 "+
						" AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
						
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			
			}
			
			if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND ra.loca_id = "+helper.getIdLocalidade();
			}

			
			consulta+=" GROUP BY loc.loca_nmlocalidade "+
					  " ORDER BY loc.loca_nmlocalidade";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Extrato debito) por Localidade Agrupando por estado
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstado(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT 'TOTAL PARA O ESTADO' AS DESCRICAO, COUNT(loc.loca_nmlocalidade) AS QUANT "+
						" FROM CADASTRO.localidade loc "+
						" JOIN CADASTRO.imovel imo "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN  COBRANCA.cobranca_documento cob "+
						" ON cob.imov_id = imo.imov_id "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = cob.usur_id "+
						" WHERE cob.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal "+ 
						" AND cob.dotp_id = 14 "; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (ExtratoDebito) por Localidade Agrupando por estado
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstadoGerencia(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT ger.greg_nmregional AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM CADASTRO.localidade loc "+
						" JOIN CADASTRO.imovel imo "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN  COBRANCA.cobranca_documento cob "+
						" ON cob.imov_id = imo.imov_id "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = cob.usur_id "+
						" JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" WHERE cob.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal "+ 
						" AND cob.dotp_id = 14"; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegional();
							
			}
			
			consulta+=" GROUP BY ger.greg_nmregional " +
					"ORDER BY DESCRICAO";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (ExtratoDebito) por Localidade Agrupando por estado negocio
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstadoNegocio(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT neg.uneg_nmunidadenegocio AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM CADASTRO.localidade loc "+
						" JOIN CADASTRO.imovel imo "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN  COBRANCA.cobranca_documento cob "+
						" ON cob.imov_id = imo.imov_id "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = cob.usur_id "+
						" JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" JOIN CADASTRO.unidade_negocio neg "+ 
						" ON neg.uneg_id = loc.uneg_id "+
						" WHERE cob.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal "+ 
						" AND cob.dotp_id = 14 "; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") & !helper.getIdUnidadeNegocio().equals("-1") ){
				consulta+="AND neg.uneg_id = " +helper.getIdUnidadeNegocio(); 
			}
			
			consulta+=" GROUP BY neg.uneg_nmunidadenegocio " +
					"ORDER BY DESCRICAO";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (ExtratoDebito) por Localidade Agrupando por estado localidade
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstadoLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM CADASTRO.localidade loc "+
						" JOIN CADASTRO.imovel imo "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN  COBRANCA.cobranca_documento cob "+
						" ON cob.imov_id = imo.imov_id "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = cob.usur_id "+
						" WHERE cob.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal "+ 
						" AND cob.dotp_id = 14 "; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND loc.loca_id = "+helper.getIdLocalidade();
			}
			
			consulta+=" GROUP BY loc.loca_nmlocalidade" +
					" ORDER BY DESCRICAO ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (SegundaVia) por Localidade Agrupando por estado
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstado(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT 'TOTAL PARA O ESTADO' as DESCRICAO ,COUNT(seg.imov_id) AS QUANT "+
						" FROM FATURAMENTO.conta_emissao_segundavia seg "+ 
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = seg.usur_id "+
						" JOIN CADASTRO.imovel imo "+
						" ON seg.imov_id = imo.imov_id "+
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" WHERE seg.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));

	
			
			retorno = query.list(); 
			
			

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (SegundaVia) por Localidade Agrupando por estado Gerencia
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstadoGerencia(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT ger.greg_nmregional AS DESCRICAO, COUNT(*) AS QUANT "+ 
						" FROM FATURAMENTO.conta_emissao_segundavia seg "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = seg.usur_id "+
						" JOIN CADASTRO.imovel imo "+
						" ON seg.imov_id = imo.imov_id "+ 
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" WHERE seg.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal "; 
				
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegional();
				
			}
			
			consulta+=" GROUP BY ger.greg_nmregional " +
					"ORDER BY DESCRICAO";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (SegundaVia) por Localidade Agrupando por estado Negocio
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstadoNegocio(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT neg.uneg_nmunidadenegocio AS DESCRICAO, COUNT(*) AS QUANT "+ 
						" FROM FATURAMENTO.conta_emissao_segundavia seg "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = seg.usur_id "+
						" JOIN CADASTRO.imovel imo "+
						" ON seg.imov_id = imo.imov_id "+ 
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN CADASTRO.GERENCIA_REGIONAL ger "+
						" ON ger.greg_id = loc.greg_id "+
						" JOIN CADASTRO.unidade_negocio neg "+ 
						" ON neg.uneg_id = loc.uneg_id "+
						" WHERE seg.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal "; 
					
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") & !helper.getIdUnidadeNegocio().equals("-1") ){
				consulta+="AND neg.uneg_id = " +helper.getIdUnidadeNegocio(); 
			}
			
			consulta+="GROUP BY neg.uneg_nmunidadenegocio " +
					"ORDER BY DESCRICAO";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (SegundaVia) por Localidade Agrupando por estado Gerencia
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstadoLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT loc.loca_nmlocalidade AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM FATURAMENTO.conta_emissao_segundavia seg "+
						" JOIN SEGURANCA.usuario usu "+
						" ON usu.usur_id = seg.usur_id "+
						" JOIN CADASTRO.imovel imo "+
						" ON seg.imov_id = imo.imov_id "+ 
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" WHERE seg.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal "; 
					
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND loc.loca_id = "+helper.getIdLocalidade();
			}
			
			consulta+=" GROUP BY loc.loca_nmlocalidade " +
					"ORDER BY DESCRICAO";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Parcelamento) por Localidade Agrupando por estado
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstado(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT 'TOTAL PARA O ESTADO' AS DESCRICAO, "+
						" COUNT(*) AS QUANT "+
						" FROM COBRANCA.parcelamento  parc "+ 
						" JOIN SEGURANCA.usuario su "+
						" ON su.usur_id = parc.usur_id "+ 
						" JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = parc.imov_id "+
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" WHERE  parc.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal "; 
					
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			}
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Parcelamento) por Localidade Agrupando por estado Gerencia
	 * 
	 * @author Carlos Chaves
	 * @date 15/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstadoGerencia(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT ger.greg_nmregional AS DESCRICAO, COUNT(*) AS QUANT "+ 
						" FROM COBRANCA.parcelamento  parc "+
						" JOIN SEGURANCA.usuario su "+
						" ON su.usur_id = parc.usur_id "+ 
						" JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = parc.imov_id "+ 
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
						" ON ger.greg_id = loc.greg_id "+
						" WHERE  parc.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal "; 
						
				
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			}
			
			//filtro gerencia Regional
			if(helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().equals("") && !helper.getIdGerenciaRegional().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegional();
				
			}
			
			consulta+= " GROUP BY ger.greg_nmregional "+
					   " ORDER BY ger.greg_nmregional ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Parcelamento) por Localidade Agrupando por estado Negocio
	 * 
	 * @author Carlos Chaves
	 * @date 16/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstadoNegocio(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT  neg.uneg_nmunidadenegocio AS DESCRICAO, COUNT(*) AS QUANT "+
						" FROM COBRANCA.parcelamento  parc "+ 
						" JOIN SEGURANCA.usuario su "+
						" ON su.usur_id = parc.usur_id "+ 
						" JOIN CADASTRO.imovel imo "+
						" ON imo.imov_id = parc.imov_id "+
						" JOIN CADASTRO.localidade loc "+
						" ON loc.loca_id = imo.loca_id "+
						" JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
						" ON ger.greg_id = loc.greg_id "+
						" JOIN CADASTRO.unidade_negocio neg "+
						" ON neg.uneg_id = loc.uneg_id "+
						" WHERE  parc.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal "; 
						
				
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().equals("") & !helper.getIdUnidadeNegocio().equals("-1") ){
				consulta+="AND neg.uneg_id = " +helper.getIdUnidadeNegocio(); 
			}
			
			consulta+= " GROUP BY neg.uneg_nmunidadenegocio "+
					   " ORDER BY neg.uneg_nmunidadenegocio ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Parcelamento) por Localidade Agrupando por estado Negocio
	 * 
	 * @author Carlos Chaves
	 * @date 16/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstadoLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =  "SELECT  loc.loca_nmlocalidade AS DESCRICAO, COUNT(*) AS QUANT "+
					  	" FROM COBRANCA.parcelamento  parc "+ 
					  	" JOIN SEGURANCA.usuario su "+
					  	" ON su.usur_id = parc.usur_id "+ 
					  	" JOIN CADASTRO.imovel imo "+
					  	" ON imo.imov_id = parc.imov_id "+
					  	" JOIN CADASTRO.localidade loc "+
					  	" ON loc.loca_id = imo.loca_id "+
					  	" JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
						" ON ger.greg_id = loc.greg_id "+
					  	" WHERE  parc.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			}
			
			
			if(helper.getIdLocalidade() != null && !helper.getIdLocalidade().equals("") && !helper.getIdLocalidade().equals("-1") ){
				consulta += " AND loc.loca_id = "+helper.getIdLocalidade();
			}
			
			consulta+= " GROUP BY loc.loca_nmlocalidade "+
					   " ORDER BY loc.loca_nmlocalidade ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (Parcelamento) por Localidade Agrupando por Gerencia Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 16/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeGerenciaLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =   "SELECT loc.loca_nmlocalidade AS DESCRICAO, "+
					     " COUNT(ger.greg_nmregional) AS QUANT, "+
					     " ger.greg_nmregional AS GERENCIA "+
            			 " FROM COBRANCA.parcelamento parc "+ 
						 " JOIN SEGURANCA.usuario su "+
						 " ON su.usur_id = parc.usur_id "+  
						 " JOIN CADASTRO.imovel imo "+
						 " ON imo.imov_id = parc.imov_id "+  
						 " JOIN CADASTRO.localidade loc "+
						 " ON loc.loca_id = imo.loca_id"+
						 " JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
						 " ON ger.greg_id = loc.greg_id "+
						 " WHERE  parc.parc_tmparcelamento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND su.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND su.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdGerenciaRegionalPorLocalidade() != null && !helper.getIdGerenciaRegionalPorLocalidade().equals("") && !helper.getIdGerenciaRegionalPorLocalidade().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegionalPorLocalidade();
				
			}
			
			consulta+= " GROUP BY ger.greg_nmregional, loc.loca_nmlocalidade "+
					   " ORDER BY  ger.greg_nmregional, loc.loca_nmlocalidade ";
		
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.addScalar("GERENCIA", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (SegundaVia) por Localidade Agrupando por Gerencia Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 16/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeGerenciaLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =   "SELECT loc.loca_nmlocalidade AS DESCRICAO, "+
					     " COUNT(ger.greg_nmregional) AS QUANT, "+
					     " ger.greg_nmregional AS GERENCIA  "+
					     " FROM FATURAMENTO.conta_emissao_segundavia seg "+ 
					     " JOIN SEGURANCA.usuario usu "+
					     " ON usu.usur_id = seg.usur_id "+ 
					     " JOIN CADASTRO.imovel imo "+
					     " ON seg.imov_id = imo.imov_id "+ 
						 " JOIN CADASTRO.localidade loc "+
						 " ON loc.loca_id = imo.loca_id "+
						 " JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
						 " ON ger.greg_id = loc.greg_id "+
						 " WHERE seg.ctem_tmemissao BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdGerenciaRegionalPorLocalidade() != null && !helper.getIdGerenciaRegionalPorLocalidade().equals("") && !helper.getIdGerenciaRegionalPorLocalidade().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegionalPorLocalidade();
				
			}
			
			consulta+= " GROUP BY ger.greg_nmregional, loc.loca_nmlocalidade "+
					   " ORDER BY  ger.greg_nmregional, loc.loca_nmlocalidade ";
		
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.addScalar("GERENCIA", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (ExtratoDebito) por Localidade Agrupando por Gerencia Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 16/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeGerenciaLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =   "SELECT loc.loca_nmlocalidade AS DESCRICAO, "+
					     " COUNT(ger.greg_nmregional) AS QUANT, "+
					     " ger.greg_nmregional AS GERENCIA "+
					     " FROM CADASTRO.localidade loc "+
					     " JOIN CADASTRO.imovel imo "+
					     " ON loc.loca_id = imo.loca_id "+ 
					     " JOIN  COBRANCA.cobranca_documento cob "+ 
					     " ON cob.imov_id = imo.imov_id "+
					     " JOIN SEGURANCA.usuario usu "+
					     " ON usu.usur_id = cob.usur_id "+
					     " JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
					     " ON ger.greg_id = loc.greg_id "+
					     " WHERE cob.cbdo_tmemissao BETWEEN :periodoInicial AND :periodoFinal "+
					     " AND cob.dotp_id = 14 "; 
			
			
			//filtro por meio de solitacao
			if(helper.getColecaoMeio() != null){
				
				boolean icBalcao = false;
				boolean icInternet = false; 
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					
					if(helper.getColecaoMeio()[i].equals("1")){
						icBalcao = true;
					}else if(helper.getColecaoMeio()[i].equals("8")){
						icInternet = true;
					}
				}
				
				if(icBalcao==true && icInternet==false){
					consulta += " AND usu.usur_icinternet = 2 ";
				}else if(icInternet==true && icBalcao==false){
					consulta += " AND usu.usur_icinternet = 1 ";
				}
			}
			
			if(helper.getIdGerenciaRegionalPorLocalidade() != null && !helper.getIdGerenciaRegionalPorLocalidade().equals("") && !helper.getIdGerenciaRegionalPorLocalidade().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegionalPorLocalidade();
				
			}
			
			consulta+= " GROUP BY ger.greg_nmregional, loc.loca_nmlocalidade "+
					   " ORDER BY  ger.greg_nmregional, loc.loca_nmlocalidade ";
		
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.addScalar("GERENCIA", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (AVERBACAO) por Localidade Agrupando por Gerencia Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 16/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
								
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeGerenciaLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =   "SELECT loc.loca_nmlocalidade AS DESCRICAO, "+ 
						 " COUNT(ger.greg_nmregional) AS QUANT, "+
						 " ger.greg_nmregional AS GERENCIA "+
						 " FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+ 
						 " LEFT JOIN CADASTRO.localidade loc "+
						 " ON loc.loca_id = ra.loca_id "+
						 " LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
						 " ON ger.greg_id = loc.greg_id "+ 
						 " where ra.step_id = 3 "+
						 " AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			}
			
			if(helper.getIdGerenciaRegionalPorLocalidade() != null && !helper.getIdGerenciaRegionalPorLocalidade().equals("") && !helper.getIdGerenciaRegionalPorLocalidade().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegionalPorLocalidade();
				
			}
			
			consulta+= " GROUP BY ger.greg_nmregional, loc.loca_nmlocalidade "+
					   " ORDER BY  ger.greg_nmregional, loc.loca_nmlocalidade ";
		
			
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.addScalar("GERENCIA", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1335] Relatorio por tipo de servico (REVISAO CONSUMO) por Localidade Agrupando por Gerencia Localidade
	 * 
	 * @author Carlos Chaves
	 * @date 16/05/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeGerenciaLocalidade(
			GerarRelatorioTipoServicoHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta =   "SELECT loc.loca_nmlocalidade AS DESCRICAO, "+ 
						 " COUNT(*) AS QUANT, "+
						 " ger.greg_nmregional AS GERENCIA "+
						 " FROM ATENDIMENTOPUBLICO.registro_atendimento ra "+ 
						 " LEFT JOIN CADASTRO.localidade loc "+
						 " ON loc.loca_id = ra.loca_id "+
						 " LEFT JOIN CADASTRO.GERENCIA_REGIONAL ger "+ 
						 " ON ger.greg_id = loc.greg_id "+ 
						 " where ( ra.step_id = 108 OR ra.step_id = 109 ) "+
						 " AND ra.rgat_tmregistroatendimento BETWEEN :periodoInicial AND :periodoFinal "; 
			
			
			if(helper.getColecaoMeio() != null && !helper.getColecaoMeio()[0].equals("")){
				
				String meiosSolicitacao = "";
				
				for(int i=0; i<helper.getColecaoMeio().length; i++ ){
					meiosSolicitacao += helper.getColecaoMeio()[i] + ",";
				}
				
				//RetirarUltima virgula		
				meiosSolicitacao = meiosSolicitacao.substring(0, meiosSolicitacao.length()-1);
				
				consulta += " AND ra.meso_id IN ("+meiosSolicitacao+") ";
			}
			
			if(helper.getIdGerenciaRegionalPorLocalidade() != null && !helper.getIdGerenciaRegionalPorLocalidade().equals("") && !helper.getIdGerenciaRegionalPorLocalidade().equals("-1")){
				
				consulta += " AND ger.greg_id = " +helper.getIdGerenciaRegionalPorLocalidade();
				
			}
			
			consulta+= " GROUP BY ger.greg_nmregional, loc.loca_nmlocalidade "+
					   " ORDER BY  ger.greg_nmregional, loc.loca_nmlocalidade ";
		
		
			query = session.createSQLQuery(consulta)
					.addScalar("QUANT", Hibernate.INTEGER)
					.addScalar("DESCRICAO", Hibernate.STRING)
					.addScalar("GERENCIA", Hibernate.STRING)
					.setTimestamp(
							"periodoInicial",
							Util.formatarDataInicial(Util.converteStringParaDate(helper.getPeriodoInicial())))
							.setTimestamp("periodoFinal", 
									Util.formatarDataFinal(Util.converteStringParaDate(helper.getPeriodoFinal())));


			retorno = query.list(); 

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * 
	 * @param idBairroAdmin
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeBairroAdmin(Integer idBairroAdmin) throws ErroRepositorioException {

		Integer retorno = null;

		boolean existe = false;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = "select baad.id " + "from BairroAdmin baad "
					+ "where baad.id = :idBairroAdmin ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idBairroAdmin", idBairroAdmin)
					.setMaxResults(1).uniqueResult();

			
			if ( retorno != null && retorno.intValue() > 0 ) {
				existe = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return existe;
	}
	

	/**
	 * 
	 * @param idLogradouroAdmin
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeLogradouroAdmin(Integer idLogradouroAdmin) throws ErroRepositorioException {

		Integer retorno = null;

		boolean existe = false;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = "select load.id " + "from LogradouroAdmin load "
					+ "where load.id = :idLogradouroAdmin ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idLogradouroAdmin", idLogradouroAdmin)
					.setMaxResults(1).uniqueResult();

			
			if ( retorno != null && retorno.intValue() > 0 ) {
				existe = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return existe;
	}

	/**
	 * [UC 1328] - Suspender Localidade para Atualização Cadastral
	 * [SF 0002] - Atualizar o indicador de bloquear logradouro do municipio
	 * 
	 * @author Davi Menezes
	 * @date 01/06/2012
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaAtualizacaoCadastral> pesquisarMunicipioAreaAtualizacaoCadastral(
			Integer idMunicipio) throws ErroRepositorioException {
		
		Collection<AreaAtualizacaoCadastral> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta =  "Select areaAtualizacaoCadastral "
					 +	"from AreaAtualizacaoCadastral areaAtualizacaoCadastral "
					 +	"inner join areaAtualizacaoCadastral.localidade localidade "
					 +	"inner join localidade.municipio municipio "
					 +	"where areaAtualizacaoCadastral.codigoSituacao = 1 "
					 +	"and municipio.id = :idMunicipio ";
			
			retorno = session.createQuery(consulta).setInteger("idMunicipio", idMunicipio).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados ADMIN
	 * [SB0005] - Atualizar Tabelas Básicas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Logradouro> pesquisarLogradouroLiberadoParaAtualizacaoCadastral() throws ErroRepositorioException {

		Collection<Logradouro> colecaoLogradouro = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = "select load from Logradouro load "
					+ "inner join load.municipio muni "
					+ "where load.indicadorUso =  :ativo "
					+ "and load.indicadorEnvioAtualizacaoCadastral = :naoEnviado "
					+ "and muni.indicadorBloquearLogradouro = :logradouroBloqueado";

			colecaoLogradouro = (Collection<Logradouro>) session.createQuery(consulta)
					.setShort("ativo", ConstantesSistema.SIM)
					.setShort("naoEnviado", ConstantesSistema.NAO)
					.setShort("logradouroBloqueado", ConstantesSistema.SIM)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoLogradouro;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados ADMIN
	 * [SB0005] - Atualizar Tabelas Básicas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Bairro> pesquisarBairroLiberadoParaAtualizacaoCadastral() throws ErroRepositorioException {

		Collection<Bairro> colecaoBairro = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = "select bair from Bairro bair "
					+ "inner join bair.municipio muni "
					+ "where bair.indicadorUso =  :ativo "
					+ "and bair.indicadorEnvioAtualizacaoCadastral = :naoEnviado "
					+ "and muni.indicadorBloquearLogradouro = :logradouroBloqueado";

			colecaoBairro = (Collection<Bairro>) session.createQuery(consulta)
					.setShort("ativo", ConstantesSistema.SIM)
					.setShort("naoEnviado", ConstantesSistema.NAO)
					.setShort("logradouroBloqueado", ConstantesSistema.SIM)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoBairro;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados ADMIN
	 * [SB0005] - Atualizar Tabelas Básicas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Municipio> pesquisarMunicipioLiberadoParaAtualizacaoCadastral() throws ErroRepositorioException {

		Collection<Municipio> colecaoMunicipio = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = "select muni from Municipio muni "
					+ "where muni.indicadorBloquearLogradouro = :logradouroLiberado";

			colecaoMunicipio = (Collection<Municipio>) session.createQuery(consulta)
					.setShort("logradouroLiberado", ConstantesSistema.SIM)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoMunicipio;
	}
	
	
	
	/**
	 * [UC0870] - Gerar Movimento de Contas em Cobranca por Empresa
	 *  
	 * Retorna o setor comercial do imovel
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/07/2012
	 * */	
	public Integer pesquisarIdSetorComercialImovel(Integer idImovel) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = "select imov.setorComercial.id from Imovel imov "
					+ "where imov.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	
	/**
	 * Recupera quantidade de imoveis enviados para atualziação cadastral
	 * 
	 * @author Vivianne Sousa
	 * @date 31/07/12
	 */
	public Integer recuperaQtdeImoveisPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)
			throws ErroRepositorioException {

		Integer retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT COUNT(imov_id) AS qtdeImoveis"
					+ " FROM cadastro.imovel_atlz_cadastral"
					+ " WHERE loca_id = :idLocalidade " 
					+ " AND imac_icdadosretorno in (1,3) "
					+ " AND empr_id = :idEmpresa ";

			retornoConsulta = (Integer)session.createSQLQuery(consulta)
					.addScalar("qtdeImoveis", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idEmpresa", idEmpresa)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}
	
	/**
	 *  [UC 1312] Gerar Resumo da Situação dos imóveis por cadastrador
	 * 
	 * @author Anderson Cabral
	 * @since 16/08/2013
	 * 
	 * @param Helper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeInconsistenciaCadastrador(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper,
		Integer idLocalidade, Integer idCadastrador) throws ErroRepositorioException{
		
		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta =  "select count( ratc.imac_id ) quantidade "
					 +	"from cadastro.retorno_atlz_cadastral ratc "
					 +	"inner join cadastro.imovel_atlz_cadastral imac on ratc.imac_id = imac.imac_id " 
					 +	"inner join cadastro.mensagem_atlz_cadastral matc on matc.matc_id = ratc.matc_id "
					 +  "inner join seguranca.usuario usur on imac.imac_nmlogin = usur.usur_nmlogin "
					 +	"left join cadastro.imovel imo on imo.imov_id = imac.imov_id "
					 +	"inner join cadastro.localidade loc on imac.loca_id = loc.loca_id " 
					 +	"inner join cadastro.setor_comercial sec ON sec.stcm_id = imo.stcm_id " 
					 +	"inner join cadastro.quadra qdra ON qdra.qdra_id = imo.qdra_id " 
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loc.greg_id " 
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loc.uneg_id "
					 +	"where imac.empr_id = :idEmpresa and matc.matc_id <> :atualizacaoComSucesso "
					 +  "and (ratc.reat_cdopcaoalteracao = 1 OR ratc.reat_cdopcaoalteracao = 2 OR ratc.reat_cdopcaoalteracao = 3) ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			parameters.put("atualizacaoComSucesso", MensagemAtualizacaoCadastral.ATUALIZACAO_COM_SUCESSO);
			
			if(idLocalidade != null){
				consulta += "and imac.loca_id = :idLocalidade ";
				parameters.put("idLocalidade", idLocalidade);
			}
			
			if(idCadastrador != null){
				consulta += "and usur.usur_id = :idCadastrador "; 
				parameters.put("idCadastrador", idCadastrador);
			}
			
			query = session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer)query.uniqueResult();
		
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 *  [UC 1312] Gerar Resumo da Situação dos imóveis por cadastrador
	 * 
	 * @author Vivianne Sousa
	 * @since 02/08/2012
	 * 
	 * @param Helper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeMensagensPendentesCadastrador(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper,
		Integer idLocalidade, Integer idCadastrador) throws ErroRepositorioException{
		
		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta =  "select COALESCE(sum(quantidade),0) qtdeTotal "
					 +  "from( " 	
					 +  "select count(distinct matc.matc_id) quantidade, imac.imac_id "
					 +	"from cadastro.retorno_atlz_cadastral ratc "
					 +	"inner join cadastro.imovel_atlz_cadastral imac on ratc.imac_id = imac.imac_id " 
					 +	"inner join cadastro.mensagem_atlz_cadastral matc on matc.matc_id = ratc.matc_id "
					 +  "inner join seguranca.usuario usur on imac.imac_nmlogin = usur.usur_nmlogin "
//					 +	"inner join cadastro.cadastrador cad on cad.cadt_id = imac.cadt_id "
					 +	"left join cadastro.imovel imo on imo.imov_id = imac.imov_id "
					 +	"inner join cadastro.localidade loc on imac.loca_id = loc.loca_id " 
					 +	"inner join cadastro.setor_comercial sec ON sec.stcm_id = imo.stcm_id " 
					 +	"inner join cadastro.quadra qdra ON qdra.qdra_id = imo.qdra_id " 
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loc.greg_id " 
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loc.uneg_id "
					 +	"where imac.empr_id = :idEmpresa and matc.matc_id <> :atualizacaoComSucesso ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			parameters.put("atualizacaoComSucesso", MensagemAtualizacaoCadastral.ATUALIZACAO_COM_SUCESSO);
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}

			if(idLocalidade != null){
				consulta += "and imac.loca_id = :idLocalidade ";
				parameters.put("idLocalidade", idLocalidade);
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and sec.stcm_cdsetorcomercial >= :idSetorInicial AND sec.stcm_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and qdra.qdra_nnquadra >= :idQuadraInicial AND qdra.qdra_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			if(idCadastrador != null){
//				consulta += "and imac.cadt_id = :idCadastrador ";
				consulta += "and usur.usur_id = :idCadastrador "; 
				parameters.put("idCadastrador", idCadastrador);
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and ratc.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and ratc.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			consulta += "group by imac.imac_id ) ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("qtdeTotal", Hibernate.INTEGER);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer)query.uniqueResult();
		
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	/**
	 *  [UC 1312] Gerar Resumo da Situação dos imóveis por cadastrador
	 * 
	 * @author Vivianne Sousa
	 * @since 03/08/2012
	 * 
	 * @param Helper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeMensagensPendentesLocalidade(
			DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException {
		
		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta += "select sum(quantidade) as qtdeTotal "
					 + "from ( " 
					 + "select matc.matc_id, count(distinct imac.imac_id) quantidade " 
					 +	"from cadastro.imovel_atlz_cadastral imac "
					 +	"inner join cadastro.retorno_atlz_cadastral ratc on ratc.imac_id = imac.imac_id "
					 +	"inner join cadastro.mensagem_atlz_cadastral matc on matc.matc_id = ratc.matc_id "
					 +	"inner join cadastro.localidade loc on imac.loca_id = loc.loca_id "
					 + 	"where imac.empr_id = :idEmpresa and matc.matc_id <> :atualizacaoComSucesso ";
				
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			parameters.put("atualizacaoComSucesso", MensagemAtualizacaoCadastral.ATUALIZACAO_COM_SUCESSO);
			
//			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
//					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
//				
//				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
//				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
//				
//				dataInicial = Util.formatarDataInicial(dataInicial);
//				dataFinal = Util.formatarDataFinal(dataFinal);
//				
//				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
//				parameters.put("periodoInicial", dataInicial);
//				parameters.put("periodoFinal", dataFinal);
//			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and imac.loca_id >= :idLocalidadeInicial AND imac.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			consulta += "group by matc.matc_id )";
			
			query = session.createSQLQuery(consulta)
					.addScalar("qtdeTotal", Hibernate.INTEGER);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer)query.uniqueResult();
		
		}catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa a descrição da Profissão a partir do id informado
	 * 
	 * @author Vivianne Sousa
	 * @date 24/08/12
	 */
	public String pesquisarDescricaoProfissao(Integer idProfissao)
			throws ErroRepositorioException {

		String retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " select prof.descricao "
					+ " from Profissao prof "
					+ " where prof.id = :idProfissao ";

			retornoConsulta = (String)session.createQuery(consulta)
					.setInteger("idProfissao", idProfissao)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}
	/**
	 * Recupera quantidade de imoveis com algum tipo de inconsistencia enviados para atualziacao cadastral
	 * 
	 * @author Anderson Cabral
	 * @date 27/08/12
	 */
	public Integer recuperaQtdeImoveisComInconsistenciasPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)
			throws ErroRepositorioException {

		Integer retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			
			consulta = "SELECT count(distinct imac.imov_id) AS qtdeImoveis " 
				+ "FROM cadastro.imovel_atlz_cadastral imac "
				+ "INNER JOIN cadastro.localidade loc ON loc.loca_id = imac.loca_id "
				+ "INNER JOIN cadastro.retorno_atlz_cadastral ratc on ratc.imac_id = imac.imac_id "
				+ "INNER JOIN cadastro.mensagem_atlz_cadastral matc on matc.matc_id = ratc.matc_id "
				+ "WHERE empr_id = :idEmpresa " 
				+ "AND matc.matc_id <> :atualizacaoComSucesso " 
				+ "AND loc.loca_id = :idLocalidade";

			retornoConsulta = (Integer)session.createSQLQuery(consulta)
					.addScalar("qtdeImoveis", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("atualizacaoComSucesso", MensagemAtualizacaoCadastral.ATUALIZACAO_COM_SUCESSO)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}	
	
	/**
	 * Metodo resposavel por verificar se existe cadastrado imovel subcategoria.
	 * 
	 * @author Arthur Carvalho
	 * @since 17/09/2012
	 * 
	 * @param idImovel
	 * @param idSubcategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelSubcategoria pesquisarImovelSubCategoria(Integer idImovel, Integer idSubcategoria) throws ErroRepositorioException {

			// cria a coleção de retorno
			ImovelSubcategoria imovelSubcategoria = null;

			// Query
			String consulta;

			// obtém a sessão
			Session session = HibernateUtil.getSession();

			try {

				consulta = "select imovelSubcategoria "
						+ "from ImovelSubcategoria imovelSubcategoria "
						+ "where imovelSubcategoria.comp_id.imovel.id = :idImovel "
						+ "and imovelSubcategoria.comp_id.subcategoria.id = :idSubcategoria ";

				imovelSubcategoria = (ImovelSubcategoria) session.createQuery(consulta)
				 .setInteger("idImovel", idImovel)
				 .setInteger("idSubcategoria", idSubcategoria).setMaxResults(1)
				 .uniqueResult();

				// erro no hibernate
			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			// retorna a coleção de atividades pesquisada(s)
			return imovelSubcategoria;
		}
	

	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ParametroTabelaAtualizacaoCadastro
	 * 
	 * @author Arthur Carvalho
	 * @exception ErroRepositorioException
	 */
	public ClienteAtualizacaoCadastral pesquisarClienteAtlzCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException {
	
		ClienteAtualizacaoCadastral parametroTabelaAtualizacaoCadastro = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT clie"
				     + " FROM ClienteAtualizacaoCadastral clie" 				    				    
				     + " WHERE clie.imovelAtualizacaoCadastral = :idImovelAtualizacaoCadastral"
				     + " and clie.idClienteRelacaoTipo = :clienteUsuario";
			
			parametroTabelaAtualizacaoCadastro = (ClienteAtualizacaoCadastral)session.createQuery(consulta)
										.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.setInteger("clienteUsuario", ClienteRelacaoTipo.USUARIO)
										.uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return parametroTabelaAtualizacaoCadastro;
	
	}

	public Collection<ImovelInscricaoResetorizada> pesquisarImoveisInscricaoResetorizada() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
//		String hql = "";
		String sql = "";
		Collection<ImovelInscricaoResetorizada> retorno = null;
		
		try{
//			hql = "Select imovelInscricaoResetorizada "
//				+ "from ImovelInscricaoResetorizada imovelInscricaoResetorizada "
//				+ "inner join imovelInscricaoResetorizada.imovel imovel "
//				+ "inner join imovelInscricaoResetorizada.localidade localidade "
//				+ "where imovelInscricaoResetorizada.indicadorAtualizacao = :indicadorAtualizacao "
//				+ "and imovelInscricaoResetorizada.ocorrenciaResetorizacao is null ";
//			
//			retorno = (Collection<ImovelInscricaoResetorizada>) 
//						session.createQuery(hql)
//						.setShort("indicadorAtualizacao", ConstantesSistema.NAO)
//						.list();
			
			sql = " SELECT imovelinsc.imir_id as imir_id,"
					+ "        imovelinsc.imir_cdsetorcomercial as cdSetor,"
					+ "        imovelinsc.imir_nnquadra as nnQuadra,"
					+ "        imovelinsc.imir_nnlote as nnLote,"
					+ "        imovelinsc.imir_nnsublote as nnSubLote,"
					+ "        imovelinsc.imir_icatualizacao as icAtualiz,"
					+ "        imovelinsc.imir_tmultimaalteracao as ultimaAlteracao,"
					+ "        imovelinsc.imov_id as idImovel,"
					+ "        imovelinsc.loca_id as idLocalidade,"
					+ "        imovelinsc.ocre_id as idOcorrencia"
					+ " FROM   cadastro.imovel_insc_resetorizada imovelinsc"
					+ " WHERE  imovelinsc.imir_icatualizacao = :indicadorAtualizacao"
					+ "        AND ( imovelinsc.ocre_id IS NULL )";
			
			Collection<Object[]> colecaoRetorno = (Collection<Object[]>) session.createSQLQuery(sql)
					.addScalar("imir_id",Hibernate.INTEGER)
					.addScalar("cdSetor", Hibernate.INTEGER)
					.addScalar("nnQuadra", Hibernate.INTEGER)
					.addScalar("nnLote", Hibernate.INTEGER)
					.addScalar("nnSubLote", Hibernate.INTEGER)
					.addScalar("icAtualiz", Hibernate.SHORT)
					.addScalar("ultimaAlteracao", Hibernate.DATE)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idOcorrencia", Hibernate.INTEGER)
					.setShort("indicadorAtualizacao", ConstantesSistema.NAO)
					.list();
			
			if(colecaoRetorno != null && !colecaoRetorno.isEmpty()){
				retorno = new ArrayList<ImovelInscricaoResetorizada>();
				for (Object[] objects : colecaoRetorno) {
					ImovelInscricaoResetorizada imovelInscricaoResetorizada = new ImovelInscricaoResetorizada();
					
					imovelInscricaoResetorizada.setId((Integer)objects[0]);
					imovelInscricaoResetorizada.setCodigoSetorComercial((Integer)objects[1]);
					imovelInscricaoResetorizada.setNumeroQuadra((Integer)objects[2]);
					imovelInscricaoResetorizada.setNumeroLote((Integer)objects[3]);
					imovelInscricaoResetorizada.setNumeroSubLote((Integer)objects[4]);
					imovelInscricaoResetorizada.setIndicadorAtualizacao((Short)objects[5]);
					imovelInscricaoResetorizada.setUltimaAlteracao((Date)objects[6]);
					imovelInscricaoResetorizada.setImovel(new Imovel((Integer)objects[7]));
					imovelInscricaoResetorizada.setLocalidade(new Localidade((Integer)objects[8]));
					imovelInscricaoResetorizada.setOcorrenciaResetorizacao(new OcorrenciaResetorizacao((Integer)objects[9]));
					
					retorno.add(imovelInscricaoResetorizada);
				}
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0003] Pesquisar Imóvel Apto para Sorteio
	 * 
	 * @author Hugo Azevedo
	 * @date 18/10/2012
	 * 
	 **/
	public Collection pesquisarImovelAptoSorteio(Integer idImovel) throws ErroRepositorioException{

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();
		
		try{
			
			consulta = " select imcs_id as id " +
					   " from cadastro.imovel_cadastro_sorteio " +
					   " where imov_id = :idImovel " +
					   " and imcs_icimovelapto = 1 " +
					   " AND imcs_icparticipacaosorteio = :indicadorPartSorteio";
			
			retorno = session.createSQLQuery(consulta)
							 .addScalar("id", Hibernate.INTEGER)
							 .setInteger("idImovel",idImovel)
							 .setShort("indicadorPartSorteio", ConstantesSistema.SIM)
							 .list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0004] Pesquisar Dados Cadastro Sorteio
	 * 
	 * @author Hugo Azevedo
	 * @date 18/10/2012
	 * 
	 **/
	public Object[] pesquisarDadosCadastroSorteio(Integer idImovel)  throws ErroRepositorioException{
		
		Object[] retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();
		
		try{
			
			consulta = " select imcs.imcs_nmcliente as nomeCliente," + //0
					   " imcs.imcs_dslogradouro as descricaoLogradouro, " + //1
					   " imcs.imcs_nnendereco as numeroEndereco, " + //2
					   " imcs.imcs_dscomplemento as complemento, " + //3
					   " bair.bair_nmbairro as nomeBairro, " + //4
					   " muni.muni_nmmunicipio as nomeMunicipio, " + //5
					   " unfe.unfe_dsufsigla as estado," + //6
					   " imcs.imcs_dsemail as email, " + //7
					   " imcs.imcs_nndddfixo as dddFixo, " + //8
					   " imcs.imcs_nntelefonefixo as telFixo, "+ //9
					   " imcs.imcs_nndddcelular as dddCelular, " + //10
					   " imcs.imcs_nntelefonecelular as telCelular, " + //11
					   " unid.unid_dsunidade as unidadeOrg, " + //12
					   " imcs.imcs_tminscricao as dataInscricao, " + //13
					   " imcs.imcs_nngerado as numeroSorteio " + //14
					   " from cadastro.imovel_cadastro_sorteio imcs" +
					   " inner join cadastro.bairro bair on bair.bair_id = imcs.bair_id " +
					   " inner join cadastro.municipio muni on muni.muni_id = bair.muni_id " +
					   " inner join cadastro.unidade_federacao unfe on unfe.unfe_id = muni.unfe_id " +
					   " left join cadastro.unidade_organizacional unid on unid.unid_id = imcs.unid_id " +
					   " where imcs.imov_id = :idImovel" +
					   " AND imcs.imcs_icparticipacaosorteio = :indicadorPartSorteio ";
					   
			
				retorno = (Object[])session.createSQLQuery(consulta)
									 .addScalar("nomeCliente", Hibernate.STRING) //0
									 .addScalar("descricaoLogradouro", Hibernate.STRING) //1
									 .addScalar("numeroEndereco", Hibernate.STRING) //2
									 .addScalar("complemento", Hibernate.STRING) //3
									 .addScalar("nomeBairro", Hibernate.STRING) //4
									 .addScalar("nomeMunicipio", Hibernate.STRING) //5
									 .addScalar("estado", Hibernate.STRING) //6
									 .addScalar("email", Hibernate.STRING) //7
									 .addScalar("dddFixo", Hibernate.STRING) //8
									 .addScalar("telFixo", Hibernate.STRING) //9
									 .addScalar("dddCelular", Hibernate.STRING) //10
									 .addScalar("telCelular", Hibernate.STRING) //11
									 .addScalar("unidadeOrg", Hibernate.STRING) //12
									 .addScalar("dataInscricao", Hibernate.TIMESTAMP) //13
									 .addScalar("numeroSorteio", Hibernate.STRING) //14
									 .setInteger("idImovel",idImovel)
									 .setShort("indicadorPartSorteio", ConstantesSistema.SIM)
									 .setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0005] Obter E-mail cadastrado
	 * 
	 * @author Hugo Azevedo
	 * @date 22/10/2012
	 * 
	 **/
	public String obterEmailCadastrado(Integer idImovel) throws ErroRepositorioException{
		String retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();
		
		try{
			
			consulta = " select imcs_dsemail as email " +
					   " from cadastro.imovel_cadastro_sorteio " +
					   " where imov_id = :idImovel " +
					   " AND imcs_icparticipacaosorteio = :indicadorPartSorteio";
			
			retorno = (String)session.createSQLQuery(consulta)
							 .addScalar("email", Hibernate.STRING)
							 .setInteger("idImovel",idImovel)
							 .setShort("indicadorPartSorteio", ConstantesSistema.SIM)
							 .setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio Mensal
	 * 
	 * Retorna os números gerados para o sorteio mensal.
	 * 
	 * @author Ana Maria	
	 * @date 18/10/2012
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImoveisSorteioMensal() 
			throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select iasm_nnnumerogerado AS numeroGerado "
					+ " from cadastro.imov_apto_sorteio_mensal ";
			
			retorno = (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("numeroGerado",Hibernate.INTEGER)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0008] Pesquisar Imóvel Cadastrado para Sorteio.
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public boolean pesquisarImovelCadastradoSorteio(Integer idImovel) 
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();
		boolean retorno = false;

		try {

			consulta = "select count(*) AS quantidade "
					+ "from cadastro.imovel_cadastro_sorteio imcs "
					+ "where imcs.imov_id = :idImovel "
					+ "and imcs.imcs_icimovelapto = :indicadorImovelApto " 
					+ "AND imcs.imcs_icparticipacaosorteio = :indicadorPartSorteio";
			
			Integer quantidade = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel)
					.setShort("indicadorPartSorteio", ConstantesSistema.SIM)
					.setShort("indicadorImovelApto", ConstantesSistema.SIM).setMaxResults(1)
					.uniqueResult();

			if(quantidade.compareTo(new Integer(0)) > 0){
				retorno = true;
			}
			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * [IT0009] Pesquisar Situação de Ligação do Imóvel.
	 * [IT0012] Obter Situação de Ligação do Imóvel Inválida.
	 * 
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * [SB011] Verificar Situação de Ligação do Imóvel. 
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public String pesquisarSituacaoLigacaoImovel(Integer idImovel) 
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();
		String retorno = null;

		try {

			consulta = "select case when(last.last_id <> :ligadoAgua) then "
					+ "		LAST_DSLIGACAOAGUASITUACAO "
					+ "	  else "
					+ "		LEST_DSLIGACAOESGOTOSITUACAO "
					+ "	  end AS ligacaoAgua "
					+ "from cadastro.imovel imov "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on last.last_id = imov.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on lest.lest_id = imov.lest_id "
					+ "where imov.imov_id = :idImovel "
					+ "and last.last_id <> :ligadoAgua and lest.lest_id <> :ligadoEsgoto ";

			retorno = (String) session.createSQLQuery(consulta)
					.addScalar("ligacaoAgua", Hibernate.STRING)
					.setInteger("idImovel", idImovel)
					.setInteger("ligadoAgua", LigacaoAguaSituacao.LIGADO)
					.setInteger("ligadoEsgoto", LigacaoEsgotoSituacao.LIGADO)
					.setMaxResults(1)
					.uniqueResult();
			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0004] Obter dados do Imóvel.
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public DadosImovelHelper obterDadosImovelSorteio(Integer idImovel) 
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();
		DadosImovelHelper retorno = new DadosImovelHelper();

		try {

			consulta = "select imov_idcategoriaprincipal AS categoria, "
					+ "sum((select sum(imsb.imsb_qteconomia) "
					+ "		from cadastro.imovel_subcategoria imsb "
					+ "		 inner join cadastro.subcategoria scat on scat.scat_id = imsb.scat_id "
					+ "		where imsb.imov_id = imov.imov_id "
					+ " 	 and scat.catg_id = imov.imov_idcategoriaprincipal)) AS quantidadeEconomias, "
					+ "lgbr.bair_id AS bairro, "
					+ "bair.muni_id AS municipio "
					+ "from cadastro.imovel imov "
					+ "inner join cadastro.logradouro_bairro lgbr on lgbr.lgbr_id = imov.lgbr_id "
					+ "inner join cadastro.bairro bair on bair.bair_id = lgbr.bair_id "
					+ "where imov.imov_id = :idImovel "
					+ "group by imov_idcategoriaprincipal, lgbr.bair_id, bair.muni_id";

			Object[] dados = (Object[]) session.createSQLQuery(consulta)
					.addScalar("categoria", Hibernate.INTEGER)
					.addScalar("quantidadeEconomias", Hibernate.INTEGER)
					.addScalar("bairro", Hibernate.INTEGER)
					.addScalar("municipio", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel)
					.setMaxResults(1)
					.uniqueResult();
			
			if (dados != null) {
				if (dados[0] != null) {
					retorno.setIdCategoria((Integer)dados[0]); 
				}
				if (dados[1] != null) {
					retorno.setQuantidadeEconomias((Integer)dados[1]); 
				}
				if (dados[2] != null) {
					retorno.setIdBairro((Integer)dados[2]); 
				}
				if (dados[3] != null) {
					retorno.setIdMunicipio((Integer)dados[3]); 
				}
			}

			consulta = "select clieUsuario.clie_nmcliente AS nomeUsuario, "
					+ " case when (clieUsuario.clie_nncpf is not null) then "
					+ "   clieUsuario.clie_nncpf "
					+ " else clieUsuario.clie_nncnpj end AS documentoUsuario "
					+ " from cadastro.imovel imov "
					+ " inner join cadastro.cliente_imovel clim on (clim.imov_id = imov.imov_id "
					+ "                       and clim.clim_dtrelacaofim is null and  clim.crtp_id = :usuario) "
					+ " left join cadastro.cliente clieUsuario on (clieUsuario.clie_id = clim.clie_id)  "
					+ " where imov.imov_id = :idImovel ";

			Object[] dadosUsuario = (Object[]) session.createSQLQuery(consulta)
					.addScalar("nomeUsuario", Hibernate.STRING)
					.addScalar("documentoUsuario", Hibernate.STRING)
					.setInteger("idImovel", idImovel)
					.setInteger("usuario", ClienteRelacaoTipo.USUARIO)
					.setMaxResults(1)
					.uniqueResult();
			
			if (dadosUsuario != null) {
				if (dadosUsuario[0] != null) {
					retorno.setNomeClienteUsuario((String)dadosUsuario[0]); 
				}
				if (dadosUsuario[1] != null) {
					retorno.setDocumentoClienteUsuario((String)dadosUsuario[1]); 
				}
			}

			consulta = "select clieProprietario.clie_nmcliente AS nomeProprietario,  "
					+ " case when (clieProprietario.clie_nncpf is not null) then "
					+ "   clieProprietario.clie_nncpf "
					+ " else clieProprietario.clie_nncnpj end AS documentoProprietario "
					+ " from cadastro.imovel imov "
					+ "   inner join cadastro.cliente_imovel clim on (clim.imov_id = imov.imov_id "
					+ "                         and clim.clim_dtrelacaofim is null and clim.crtp_id = :proprietario) "
					+ "   inner join cadastro.cliente clieProprietario on (clieProprietario.clie_id = clim.clie_id)  "
					+ " where imov.imov_id = :idImovel ";

			Object[] dadosProprietario = (Object[]) session.createSQLQuery(consulta)
					.addScalar("nomeProprietario", Hibernate.STRING)
					.addScalar("documentoProprietario", Hibernate.STRING)
					.setInteger("idImovel", idImovel)
					.setInteger("proprietario", ClienteRelacaoTipo.PROPRIETARIO)
					.setMaxResults(1)
					.uniqueResult();
			

			if (dadosProprietario != null) {
				if (dadosProprietario[0] != null) {
					retorno.setNomeClienteProprietario((String)dadosProprietario[0]); 
				}
				if (dadosProprietario[1] != null) {
					retorno.setDocumentoClienteProprietario((String)dadosProprietario[1]); 
				}
			}
			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0011] Cadastrar o Imóvel para o Sorteio.
	 * 
	 * Retorna os números que já foram cadastrados para sorteio
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public Collection<Integer> pesquisarNumerosSorteio() 
			throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imcs_nngerado AS numeroGerado "
					+ " from cadastro.imovel_cadastro_sorteio ";
			
			retorno = (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("numeroGerado",Hibernate.INTEGER)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0001] Exibir Quantidade Total de Inscritos
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public Integer pesquisarQuantidadeTotalInscritos() 
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();
		Integer retorno = null;

		try {

			consulta = "select count(*) AS quantidade "
					+ "from cadastro.imovel_cadastro_sorteio imcs "
					+ "where imcs.imcs_icimovelapto = :indicadorImovelApto " 
					+ "AND imcs.imcs_icparticipacaosorteio = :indicadorPartSorteio ";

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setShort("indicadorPartSorteio", ConstantesSistema.SIM)
					.setShort("indicadorImovelApto", ConstantesSistema.SIM).setMaxResults(1)
					.uniqueResult();

			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarImoveisAptoSorteioFiqueLegal() 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imcs_nngerado AS numeroGerado, " //0
					+ "		imov_id AS idImovel, " //1
					+ "		imcs_nmcliente AS nomeCliente, " //2
					+ "		imcs_nncpf AS cpf, " //3
					+ "		imcs_nncnpj AS cnpj " //4
					+ " from cadastro.imovel_cadastro_sorteio "
					+ " where prso_id is null and imcs_icimovelapto = :imovelApto ";
			
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("numeroGerado",Hibernate.INTEGER)
					.addScalar("idImovel",Hibernate.INTEGER)
					.addScalar("nomeCliente",Hibernate.STRING)
					.addScalar("cpf",Hibernate.STRING)
					.addScalar("cnpj",Hibernate.STRING)
					.setShort("imovelApto", ConstantesSistema.SIM)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarImoveisAptoSorteioMensal() 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select iasm_nnnumerogerado AS numeroGerado, "
					+ "		imov_id AS idImovel "
					+ " from cadastro.imov_apto_sorteio_mensal "
					+ " where prso_id is null and iasm_amsorteio = :anoMesAtual ";
			
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("numeroGerado",Hibernate.INTEGER)
					.addScalar("idImovel",Hibernate.INTEGER)
					.setInteger("anoMesAtual", Util.recuperaAnoMesDaData(new Date()))
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroOrdemSorteioFiqueLegal() 
			throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select coalesce(max(imcs_nnordemsorteio), 0) AS numeroOrdemSorteio "
					+ " from cadastro.imovel_cadastro_sorteio "
					+ " where prso_id is not null ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("numeroOrdemSorteio",Hibernate.INTEGER)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroOrdemSorteioMensal() 
			throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select coalesce(max(iasm_nnordemsorteio), 0) AS numeroOrdemSorteio "
					+ " from cadastro.imov_apto_sorteio_mensal "
					+ " where prso_id is not null and iasm_amsorteio = :anoMesAtual ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("numeroOrdemSorteio",Hibernate.INTEGER)
					.setInteger("anoMesAtual", Util.recuperaAnoMesDaData(new Date()))
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB012] Verificar Categoria Residencial do Imóvel.
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 */
	public Integer obterCategoriaPrincipalImovel(Integer idImovel) 
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();
		Integer retorno;

		try {

			consulta = "select imov_idcategoriaprincipal AS categoria "
					+ "from cadastro.imovel imov "
					+ "where imov.imov_id = :idImovel ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("categoria",Hibernate.INTEGER)
					.setInteger("idImovel", idImovel)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}


	/**
	 * [[UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB013] - Atualizar Tabelas Sorteio Fique Legal
	 * 
	 * @author Mariana Victor
	 * @data 25/10/2012
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarImovelSorteioFiqueLegal(Integer idImovel, 
			Integer idPremio, Integer numeroOrdemSorteio) 
			throws ErroRepositorioException {
		
		String consulta;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.cadastro.ImovelCadastroSorteio "
				+ " set imcs_dtsorteio = :dataSorteio, prso_id = :idPremio, "
				+ " 	imcs_nnordemsorteio = :numeroOrdemSorteio, imcs_tmultimaalteracao = :ultimaAlteracao "
				+ " where imov_id = :idImovel ";
		
			session.createQuery(consulta)
				//.setInteger("indicadorSorteio", ConstantesSistema.SIM)
				.setDate("dataSorteio", new Date())
				.setInteger("idPremio", idPremio)
				.setInteger("numeroOrdemSorteio", numeroOrdemSorteio)
				.setTimestamp("ultimaAlteracao", new Date())
				.setInteger("idImovel", idImovel)
				.executeUpdate();

			
			consulta = "update gcom.cadastro.PremioSorteio "
				+ " set prso_qtsorteiada = (prso_qtsorteiada + 1), prso_tmultimaalteracao = :ultimaAlteracao "
				+ " where prso_id = :idPremio ";
		
			session.createQuery(consulta)
				.setInteger("idPremio", idPremio)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}


	/**
	 * [[UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0005] - Atualizar Tabelas Sorteio Mensal.
	 * 
	 * @author Mariana Victor
	 * @data 25/10/2012
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarImovelSorteioMensal(Integer idImovel, 
			Integer idPremio, Integer numeroOrdemSorteio) 
			throws ErroRepositorioException {
		
		String consulta;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.cadastro.ImovelAptoSorteioMensal "
				+ " set iasm_icsorteio = :indicadorSorteio, iasm_dtsorteio = :dataSorteio, prso_id = :idPremio, "
				+ " 	iasm_nnordemsorteio = :numeroOrdemSorteio, iasm_tmultimaalteracao = :ultimaAlteracao "
				+ " where imov_id = :idImovel and iasm_amsorteio = :anoMesAtual ";
		
			session.createQuery(consulta)
				.setInteger("indicadorSorteio", ConstantesSistema.SIM)
				.setDate("dataSorteio", new Date())
				.setInteger("idPremio", idPremio)
				.setInteger("numeroOrdemSorteio", numeroOrdemSorteio)
				.setTimestamp("ultimaAlteracao", new Date())
				.setInteger("idImovel", idImovel)
				.setInteger("anoMesAtual", Util.recuperaAnoMesDaData(new Date()))
				.executeUpdate();

			
			consulta = "update gcom.cadastro.PremioSorteio "
				+ " set prso_qtsorteiada = (prso_qtsorteiada + 1), prso_tmultimaalteracao = :ultimaAlteracao "
				+ " where prso_id = :idPremio ";
		
			session.createQuery(consulta)
				.setInteger("idPremio", idPremio)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB014] - Emitir Relatório Sorteio Fique Legal
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioImoveisSorteadosFiqueLegal() 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select prso.prso_dssorteio AS premio, " //0
					+ "		imcs.imov_id AS idImovel, " //1
					+ "		imcs.imcs_nnordemsorteio AS numeroOrdem, " //2
					+ "		prso.prso_nnordemsorteio AS ordemPremio, " //3
					+ "		imcs.imcs_nngerado AS numeroSorteio, " //4
					+ "		imcs.imcs_nmcliente AS cliente, " //5
					+ "		imcs.imcs_nncpf AS cpf, " //6
					+ "		imcs.imcs_nncnpj AS cnpj, " //7
					+ "		greg.greg_nmregional AS gerenciaRegional, " //8
					+ "		loca.loca_nmlocalidade AS localidade " //9
					+ " from cadastro.imovel_cadastro_sorteio imcs "
					+ " 	inner join cadastro.premio_sorteio prso on prso.prso_id = imcs.prso_id "
					+ " 	inner join cadastro.imovel imov on imov.imov_id = imcs.imov_id "
					+ " 	inner join cadastro.localidade loca on loca.loca_id = imov.loca_id "
					+ " 	inner join cadastro.gerencia_regional greg on greg.greg_id = loca.greg_id "
					+ " order by prso.prso_nnordemsorteio, imcs.imcs_nnordemsorteio ";
			
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("premio",Hibernate.STRING)
					.addScalar("idImovel",Hibernate.INTEGER)
					.addScalar("numeroOrdem",Hibernate.INTEGER)
					.addScalar("ordemPremio",Hibernate.INTEGER)
					.addScalar("numeroSorteio",Hibernate.INTEGER)
					.addScalar("cliente",Hibernate.STRING)
					.addScalar("cpf",Hibernate.STRING)
					.addScalar("cnpj",Hibernate.STRING)
					.addScalar("gerenciaRegional",Hibernate.STRING)
					.addScalar("localidade",Hibernate.STRING)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0006] - Emitir Relatório Sorteio Mensal
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioImoveisSorteadosMensal() 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select prso.prso_dssorteio AS premio, " //0
					+ "		iasm.imov_id AS idImovel, " //1
					+ "		iasm.iasm_nnordemsorteio AS numeroOrdem, " //2
					+ "		prso.prso_nnordemsorteio AS ordemPremio, " //3
					+ "		iasm.iasm_nnnumerogerado AS numeroSorteio, " //4
					+ "		greg.greg_nmregional AS gerenciaRegional, " //5
					+ "		loca.loca_nmlocalidade AS localidade " //6
					+ " from cadastro.imov_apto_sorteio_mensal iasm "
					+ " 	inner join cadastro.premio_sorteio prso on prso.prso_id = iasm.prso_id "
					+ " 	inner join cadastro.imovel imov on imov.imov_id = iasm.imov_id "
					+ " 	inner join cadastro.localidade loca on loca.loca_id = imov.loca_id "
					+ " 	inner join cadastro.gerencia_regional greg on greg.greg_id = loca.greg_id "
					+ " where iasm.iasm_amsorteio = :anoMesAtual "
					+ " order by prso.prso_nnordemsorteio, iasm.iasm_nnordemsorteio ";
			
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("premio",Hibernate.STRING)
					.addScalar("idImovel",Hibernate.INTEGER)
					.addScalar("numeroOrdem",Hibernate.INTEGER)
					.addScalar("ordemPremio",Hibernate.INTEGER)
					.addScalar("numeroSorteio",Hibernate.INTEGER)
					.addScalar("gerenciaRegional",Hibernate.STRING)
					.addScalar("localidade",Hibernate.STRING)
					.setInteger("anoMesAtual", Util.recuperaAnoMesDaData(new Date()))
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

    
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataSorteioFiqueLegal() 
			throws ErroRepositorioException {
		
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select max(imcs_dtsorteio) AS dataSorteio "
					+ " from cadastro.imovel_cadastro_sorteio ";
			
			retorno = (Date) session.createSQLQuery(consulta)
					.addScalar("dataSorteio",Hibernate.DATE)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

    
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataSorteioMensal() 
			throws ErroRepositorioException {
		
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select max(iasm_dtsorteio) AS dataSorteio "
					+ " from cadastro.imov_apto_sorteio_mensal ";
			
			retorno = (Date) session.createSQLQuery(consulta)
					.addScalar("dataSorteio",Hibernate.DATE)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0472] Consultar Imóvel
	 * 
	 * [SB0006 - Verificar Categoria Residencial do Imóvel] 
	 * [SB0007 - Verificar Cliente do Imóvel Apto para Sorteio]
	 * 
	 * @author Ana Maria
	 * @date 25/10/2012
	 */
	public boolean verificarImovelAptoSorteio(Integer idImovel) 
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();
		boolean retorno = false;

		try {

			consulta = "select count(CLIE_NNCPF) AS quantidade "
					 + "from CADASTRO.IMOVEL imov "
					 + "inner join CADASTRO.CLIENTE_IMOVEL clim on(clim.imov_id = imov.imov_id) "
					 + "inner join CADASTRO.CLIENTE clie on(clim.clie_id = clie.clie_id) "
					 + "where imov.imov_id = :idImovel "
					 + "and imov.imov_idcategoriaprincipal = 1 "
					 + "and CLIM_DTRELACAOFIM is null and (CLIE_NNCPF is null or CLIE_NNCPF not in "
                     + "                                 (select DISO_NNCPF "
                     + "                                 from CADASTRO.DOCTO_IMPEDIDO_SORTEIO))";

			Integer quantidade = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

			if(quantidade.compareTo(new Integer(0)) > 0){
				retorno = true;
			}
			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio Mensal
	 * 
	 * Retorna os números gerados para o sorteio mensal.
	 * 
	 * @author Ana Maria	
	 * @date 18/10/2012
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImoveisSorteioMensal(Integer anoMes) 
			throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select iasm_nnnumerogerado AS numeroGerado "
					+ " from cadastro.imov_apto_sorteio_mensal "
					+ " where iasm_amsorteio =:anoMes";
			
			retorno = (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("numeroGerado",Hibernate.INTEGER)
					.setInteger("anoMes",anoMes)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio Mensal
	 * 
	 * [SB0004 - Verificar Situação de Ligação do Imóvel] 
	 * 
	 * @author Ana Maria
	 * @date 29/10/2012
	 */
	public boolean verificarSituacoaLigacaoAguaEsgotoImovel(Integer idImovel) 
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " select case when(last_id =:ligadoAgua) then last_id "
					  +" else lest_id "
					  +" end AS situcaoLigacao "
					  +" from cadastro.imovel imov "
					  +" where imov.imov_id = :idImovel "
					  +" and (last_id = :ligadoAgua or lest_id = :ligadoEsgoto)";
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("situcaoLigacao", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel)
					.setInteger("ligadoAgua", LigacaoAguaSituacao.LIGADO)
					.setInteger("ligadoEsgoto", LigacaoEsgotoSituacao.LIGADO)
					.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;		
	}

	public Collection<Object []> pesquisarImoveisResetorizados(String idLocalidade, String codigoSetorComercial, 
			String numeroQuadraInicial, String numeroQuadraFinal, Collection<Integer> colecaoLigacaoAguaSituacao,
			String clienteUsuario) throws ErroRepositorioException {
		
		Collection<Object []> colecaoImoveis = new ArrayList<Object[]>();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"Select distinct im.imov_id as idImovel, " //0
					 +	"ir.loca_id as idLocalidade, " //1
					 +	"ir.imir_cdsetorcomercial as codigoSetorComercial, " //2
					 +	"ir.imir_nnquadra as numeroQuadra, " //3
					 +	"ir.imir_nnlote as numeroLote, " //4
					 +	"ir.imir_nnsublote as numeroSubLote " //5
					 + 	"from cadastro.imovel_insc_resetorizada ir "
					 +	"inner join cadastro.imovel im on ir.imov_id = im.imov_id "
					 +	"inner join cadastro.cliente_imovel ci on ci.imov_id = im.imov_id "
					 +	"inner join cadastro.cliente cl on ci.clie_id = cl.clie_id "
					 +	"inner join cadastro.localidade loc on ir.loca_id = loc.loca_id "
					 +	"inner join atendimentopublico.ligacao_agua_situacao last on im.last_id = last.last_id "
					 +	"where loc.loca_id = :idLocalidade "
					 +	"and ir.imir_cdsetorcomercial = :codigoSetor ";
			
			if(numeroQuadraInicial != null && !numeroQuadraInicial.equals("") 
					&& numeroQuadraFinal != null && !numeroQuadraFinal.equals("")){
				consulta += "and ir.imir_nnquadra between " + Integer.parseInt(numeroQuadraInicial) 
						 + " and " + Integer.parseInt(numeroQuadraFinal) + " ";
			}
			
			if(!Util.isVazioOrNulo(colecaoLigacaoAguaSituacao)){
				consulta += "and last.last_id IN (:colecaoLigacaoAguaSituacao) ";
			}
			
			if(clienteUsuario != null && clienteUsuario.equals("1")){
				consulta += "and (cl.clie_nncpf is not null OR cl.clie_nncnpj is not null) and ci.crtp_id = :relacaoTipo and ci.clim_dtrelacaofim is null ";
			}else{
				consulta += "and (cl.clie_nncpf is null AND cl.clie_nncnpj is null) and ci.crtp_id = :relacaoTipo and ci.clim_dtrelacaofim is null ";
			}
			
			if(!Util.isVazioOrNulo(colecaoLigacaoAguaSituacao)){
				colecaoImoveis = (Collection<Object []>) session.createSQLQuery(consulta)
										.addScalar("idImovel", Hibernate.INTEGER)
										.addScalar("idLocalidade", Hibernate.INTEGER)
										.addScalar("codigoSetorComercial", Hibernate.INTEGER)
										.addScalar("numeroQuadra", Hibernate.INTEGER)
										.addScalar("numeroLote", Hibernate.INTEGER)
										.addScalar("numeroSubLote", Hibernate.INTEGER)
										.setInteger("idLocalidade", Integer.parseInt(idLocalidade))
										.setInteger("codigoSetor", Integer.parseInt(codigoSetorComercial))
										.setParameterList("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao)
										.setInteger("relacaoTipo", ClienteRelacaoTipo.USUARIO.intValue())
										.list();
			
			}else{
				colecaoImoveis = (Collection<Object []>) session.createSQLQuery(consulta)
										.addScalar("idImovel", Hibernate.INTEGER)
										.addScalar("idLocalidade", Hibernate.INTEGER)
										.addScalar("codigoSetorComercial", Hibernate.INTEGER)
										.addScalar("numeroQuadra", Hibernate.INTEGER)
										.addScalar("numeroLote", Hibernate.INTEGER)
										.addScalar("numeroSubLote", Hibernate.INTEGER)
										.setInteger("idLocalidade", Integer.parseInt(idLocalidade))
										.setInteger("codigoSetor", Integer.parseInt(codigoSetorComercial))
										.setInteger("relacaoTipo", ClienteRelacaoTipo.USUARIO.intValue())
										.list();
			}
					 
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoImoveis;
	}
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * [SB 0002] - Inserir Imóvel no Ambiente Virtual
	 * 
	 * @author Davi Menezes
	 * @date 21/11/2012
	 * 
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosImovelInsricaoResetorizada(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retorno = null;

		Query query = null;
		
		try{
			consulta = 	"SELECT loca_id as idLocalidade, " //0
					 +	"imir_cdsetorcomercial as codigoSetorComercial, " //1
					 +	"imir_nnquadra as numeroQuadra, " //2
					 +	"imir_nnlote as numeroLote, " //3
					 +	"imir_nnsublote as numeroSublote " //4
					 +	"FROM cadastro.imovel_insc_resetorizada "
					 +	"WHERE imov_id = :idImovel "
					 +	"AND imir_tmultimaalteracao = "
					 +	"(SELECT max(imir_tmultimaalteracao) "
					 +	"FROM cadastro.imovel_insc_resetorizada "
					 +	"WHERE imov_id = :idImovel) ";
			
			query = session.createSQLQuery(consulta)
							.addScalar("idLocalidade", Hibernate.INTEGER)
							.addScalar("codigoSetorComercial", Hibernate.INTEGER)
							.addScalar("numeroQuadra", Hibernate.INTEGER)
							.addScalar("numeroLote", Hibernate.INTEGER)
							.addScalar("numeroSublote", Hibernate.INTEGER)
							.setInteger("idImovel", idImovel);
							
			retorno = (Object[]) query.uniqueResult();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * @author Davi Menezes
	 * @date 21/11/2012
	 */
	public Collection<Object []> pesquisarBairrosImovel(Collection<Integer> colecaoImoveis) throws ErroRepositorioException{
		Collection<Object []> colecaoBairros =  new ArrayList<Object[]>();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = "select distinct ba.bair_id as idBairro, " //0 
					 +	"ba.bair_cdbairro as codigoBairro, " //1 
					 +	"ba.bair_nmbairro as nomeBairro " //2
					 +	"from cadastro.bairro ba "
					 +	"inner join CADASTRO.logradouro_bairro lb on ba.bair_id = lb.bair_id "
					 +	"inner join CADASTRO.imovel im on im.lgbr_id = lb.lgbr_id "
					 +	"where im.imov_id in (:colecaoImoveis) ";
			
			if(colecaoImoveis.size() > 999){
				List<List<Integer>> particoes = CollectionUtil.particao((List<Integer>) colecaoImoveis, 999);

				int qtdQuebras = 999;
				int indice = colecaoImoveis.size() / qtdQuebras;
				
				if (colecaoImoveis.size() % qtdQuebras !=0){
					indice ++;
				}
				
				for (int i = 0; i < indice; i++) {
					Collection<Object[]> retornoPart = null;
					
					retornoPart = (Collection<Object[]>) session.createSQLQuery(consulta)
									.addScalar("idBairro", Hibernate.INTEGER)
									.addScalar("codigoBairro", Hibernate.INTEGER)
									.addScalar("nomeBairro", Hibernate.STRING)
									.setParameterList("colecaoImoveis", particoes.get(i))
									.list();
					if ( retornoPart != null  ) {
						colecaoBairros.addAll(retornoPart);
					}
				}
			}else{
				colecaoBairros = (Collection<Object[]>) session.createSQLQuery(consulta)
															.addScalar("idBairro", Hibernate.INTEGER)
															.addScalar("codigoBairro", Hibernate.INTEGER)
															.addScalar("nomeBairro", Hibernate.STRING)
															.setParameterList("colecaoImoveis", colecaoImoveis)
															.list();
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoBairros;
	}
	
	public Collection<Object []> pesquisarCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoCep = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct cep.cep_id as idCep, " //0
					 +	"cep.cep_cdcep as codigoCep " //1
					 +	"from cadastro.cep cep "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.cep_id = cep.cep_id "
					 +	"inner join CADASTRO.logradouro lo on lo.logr_id = lc.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = lo.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) "; 
			
			colecaoCep = (Collection<Object[]>) session.createSQLQuery(consulta)
													.addScalar("idCep", Hibernate.INTEGER)
													.addScalar("codigoCep", Hibernate.INTEGER)
													.setParameterList("colecaoBairro", colecaoBairros)
													.list();
													
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoCep;
	}
	
	/**
	 * 
	 * @param colecaoBairros
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarLogradouroCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoCep = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct lc.lgcp_id as idLogradouroCep, " //0
					 +	"lc.cep_id as idCep, " //1
					 +  "lc.logr_id as idLogradouro " //3
					 +	"from cadastro.cep cep "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.cep_id = cep.cep_id "
					 +	"inner join CADASTRO.logradouro lo on lo.logr_id = lc.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = lo.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) "; 
			
			colecaoCep = (Collection<Object[]>) session.createSQLQuery(consulta)
													.addScalar("idLogradouroCep", Hibernate.INTEGER)
													.addScalar("idCep", Hibernate.INTEGER)
													.addScalar("idLogradouro", Hibernate.INTEGER)
													.setParameterList("colecaoBairro", colecaoBairros)
													.list();
													
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoCep;
	}
	
	public Collection<Object []> pesquisarLogradouroBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoLogradouros = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct lb.lgbr_id as idLogradouroBairro, " 
					 +	"lb.bair_id as idBairro, " 
					 +  "lb.logr_id as idLogradouro "
					 +	"from CADASTRO.logradouro logr "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.logr_id = logr.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = logr.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) ";
			
			colecaoLogradouros = (Collection<Object[]>) session.createSQLQuery(consulta)
															.addScalar("idLogradouroBairro", Hibernate.INTEGER)
															.addScalar("idBairro", Hibernate.INTEGER)
															.addScalar("idLogradouro", Hibernate.INTEGER)
															.setParameterList("colecaoBairro", colecaoBairros)
															.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoLogradouros;
	}

	public Collection<Object []> pesquisarLogradouro(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoLogradouros = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct logr.logr_id as idLogradouro, " 
					 +	"logr.logr_nmlogradouro as descricaoLogradouro, " 
					 +	"logr.logr_nmpopular as descricaoPopular, "
					 +	"logr.logr_nmloteamento as loteamento, "
					 +	"logr.muni_id as municipio, "
					 +	"logr.lgtp_id as tipo, "
					 +	"logr.lgtt_id as titulo "
					 +	"from CADASTRO.logradouro logr "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.logr_id = logr.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = logr.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) ";
			
			colecaoLogradouros = (Collection<Object[]>) session.createSQLQuery(consulta)
															.addScalar("idLogradouro", Hibernate.INTEGER)
															.addScalar("descricaoLogradouro", Hibernate.STRING)
															.addScalar("descricaoPopular", Hibernate.STRING)
															.addScalar("loteamento", Hibernate.STRING)
															.addScalar("municipio", Hibernate.INTEGER)
															.addScalar("tipo", Hibernate.INTEGER)
															.addScalar("titulo", Hibernate.INTEGER)
															.setParameterList("colecaoBairro", colecaoBairros)
															.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoLogradouros;
	}
	
	public Collection<Object[]> pesquisarArquivoRoteiroAtualizacaoCadastral(ConsultarRoteiroDispositivoMovelHelper helper)
			throws ErroRepositorioException {
		
		Collection<Object []> colecaoArquivos = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select txat_id as idArquivo, " //0
					 +	"ptac.ptac_id as idParametro, " //1
					 +	"ptac.loca_idinicial as idLocalidade, " //2
					 +	"ptac.ptac_cdsetorcomercialinicial as codigoSetor, " //3
					 +	"ptac.ptac_nnquadrainicial as numeroQuadra, " //4
					 +	"txat_qtdimovel as qtdImovelEnviado, " //5
					 +	"usur_nmusuario as nomeUsuario, " //6
					 +	"sitl_dssituacao as descricaoSituacaoArquivo, " //7
					 +	"sitl.sitl_id as idSituacaoArquivo, " //8 
					 +	"txat_dtarqliberado as dataArquivoLiberado, " //9
					 +	"txat_dtarqfinalizado as dataFinalizacaoArquivo " //10
					 +	"from cadastro.atlz_cadastral_arq_txt txat "
					 +	"inner join cadastro.param_tab_atualiz_cadast ptac "
					 +	"on txat.ptac_id = ptac.ptac_id "
					 +	"inner join MICROMEDICAO.situacao_transm_leitura sitl "
					 +	"on txat.sitl_id = sitl.sitl_id "
					 +	"inner join MICROMEDICAO.leiturista leit "
					 +	"on txat.leit_id = leit.leit_id "
					 +	"inner join SEGURANCA.usuario usur "
					 +	"on leit.usur_id = usur.usur_id ";
			
			if(helper.getNumeroQuadra() != null){
				consulta +=	" inner join cadastro.roteiro_quadra_atlz_cad roteiroQuadra on roteiroQuadra.ptac_id = ptac.ptac_id ";
			}
			
			consulta +=	"where ptac.loca_idinicial = :idLocalidade "; 
			
			if(helper.getNumeroQuadra() != null){
				consulta +=	" and roteiroQuadra.rqac_nnquadra = " + helper.getNumeroQuadra();
			}
			
			if(helper.getCodigoSetorComercial() != null){
				consulta += " and ptac.ptac_cdsetorcomercialinicial = " + helper.getCodigoSetorComercial();
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += " and txat.leit_id = " + helper.getIdCadastrador();
			}
			
			if(helper.getSituacaoArquivoTexto() != null){
				consulta += " and txat.sitl_id = " + helper.getSituacaoArquivoTexto();
			}
			
			consulta += " order by txat_id ";
			
			colecaoArquivos = (Collection<Object[]>) session.createSQLQuery(consulta)
															.addScalar("idArquivo", Hibernate.INTEGER)
															.addScalar("idParametro", Hibernate.INTEGER)
															.addScalar("idLocalidade", Hibernate.INTEGER)
															.addScalar("codigoSetor", Hibernate.INTEGER)
															.addScalar("numeroQuadra", Hibernate.INTEGER)
															.addScalar("qtdImovelEnviado", Hibernate.INTEGER)
															.addScalar("nomeUsuario", Hibernate.STRING)
															.addScalar("descricaoSituacaoArquivo", Hibernate.STRING)
															.addScalar("idSituacaoArquivo", Hibernate.INTEGER)
															.addScalar("dataArquivoLiberado", Hibernate.DATE)
															.addScalar("dataFinalizacaoArquivo", Hibernate.DATE)
															.setInteger("idLocalidade", helper.getIdLocalidade())
															.list();
															
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoArquivos;
	}

	public Integer pesquisarQuantidadeImoveisRecebidosAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException {
		Integer quantidade = null;
		Integer somaCadastral = null;		
		Session session = HibernateUtil.getSession();
		String consulta = "";		
		
		try{

			consulta = 	"select count(*) as quantidade "
					 +	"from CADASTRO.imovel_atlz_cadastral "
					 +	"where imac_icdadosretorno in (1,3) "					 
					 +	"and ptac_id = :idParametro "; 					
			
			quantidade = (Integer) 
				session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("idParametro", idParametro)
					.uniqueResult();
			
			consulta = 	"select count(*) as quantidade "
					 +	"from CADASTRO.imovel_atlz_cad_removido "
					 +  "where ptac_id = :idParametro ";					 			 
			
			somaCadastral = (Integer) 
						session.createSQLQuery(consulta)
						.addScalar("quantidade", Hibernate.INTEGER)	
						.setInteger("idParametro", idParametro)
						.uniqueResult();
			
			quantidade = quantidade.intValue() + somaCadastral.intValue();					
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return quantidade;
	}
	

    
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB013] - Verificar se Imóvel está impedido para sorteio
	 * 
	 * @author Mariana Victor
	 * @date 27/11/2012
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImovelImpedidoSorteio() 
			throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imov_id AS imovel "
					+ " from cadastro.imovel_impedido_sorteio ";
			
			retorno = (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("imovel",Hibernate.INTEGER)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 *
	 */
	public void atualizarListaAtualizacaoCadastralArquivoTexto(
			Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoAtualizacaoCadastralArquivoTexto,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String sql = "";
		
		Collection<Integer> colecaoIds = new ArrayList<Integer>();
		Iterator iterator = colecaoAtualizacaoCadastralArquivoTexto.iterator();
		while(iterator.hasNext()){
			AtualizacaoCadastralArquivoTextoHelper arquivo = (AtualizacaoCadastralArquivoTextoHelper) iterator.next();
			colecaoIds.add(new Integer(arquivo.getId()));
		}
		
		try{
			sql = 	"update gcom.cadastro.AtualizacaoCadastralArquivoTexto set "
				+	"sitl_id = :idSituacao, "
				+	"txat_tmultimaalteracao = :dataUltimaAlteracao ";
			
			if(leiturista != null){
				sql += ", leit_id  = :idLeiturista ";
			}
			
			if ( idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.LIBERADO) ) {
				sql += ", txat_dtarqliberado  = :dataUltimaAlteracao ";
			} else if ( idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.DISPONIVEL) ) {
				sql += ", txat_dtarqliberado  = null ";
			} else if( idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				sql += ", txat_dtarqfinalizado = :dataArquivoFinalizado ";
			}
			
			sql += " where txat_id in ( :conjuntoArquivos )";
			
			Query query = session.createQuery(sql)
					.setInteger("idSituacao", idSituacaoLeituraNova)	
					.setTimestamp("dataUltimaAlteracao", date)
					.setParameterList("conjuntoArquivos", colecaoIds);
			
			if(leiturista != null){
				query.setInteger("idLeiturista", leiturista.getId());
			}
			
			if(idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				query.setTimestamp("dataArquivoFinalizado", date);
			}
			
			query.executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 
	 * [FE0002] Verificar Dados Armazenados
	 * [IT0010] Pesquisar Dados
	 * 
	 * @author Mariana Victor
	 * @date 21/01/2013
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public boolean verificarDadosClientesParaUnificar() 
			throws ErroRepositorioException {
		
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select "
					+ "    count(*) AS quantidade "
					+ "  from dados_clientes_rm7620 "
					+ "  where dcli_icunificado = '2' ";
			
			Integer quantidade = (Integer) 
						session.createSQLQuery(consulta)
						.addScalar("quantidade",Hibernate.INTEGER)
						.setMaxResults(1).uniqueResult();

			if (quantidade != null 
					&& quantidade.compareTo(new Integer("0")) > 0) {
				retorno = true;
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 
	 * [IT0003] Obter Clientes
	 * 
	 * @author Mariana Victor
	 * @date 21/01/2013
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterClientesParaUnificar(Integer quantidadeRegistros) 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select "
					+ "    cltp.cltp_icpessoafisicajuridica AS tipoDocumento, "
					+ "    clie_nncpf AS documento, "
					+ "    min(clie_id) AS codigoCliente "
					+ "  from dados_clientes_rm7620 aux "
					+ "    inner join cadastro.cliente_tipo cltp on cltp.cltp_id = aux.cltp_id "
					+ "  where aux.dcli_icunificado = '2' "
					+ "       and cltp.cltp_icpessoafisicajuridica = 1 "
					+ "       and clie_nncpf is not null "
					+ "       and LENGTH(trim(clie_nncpf)) > 0 "
					+ "       and clie_nncpf != '00000000000' "
					+ "  group by cltp.cltp_icpessoafisicajuridica, clie_nncpf "
					+ "  union "
					+ "  select "
					+ "    cltp.cltp_icpessoafisicajuridica AS tipoDocumento, "
					+ "    clie_nncnpj AS documento, "
					+ "    min(clie_id) AS codigoCliente "
					+ "  from dados_clientes_rm7620 aux "
					+ "    inner join cadastro.cliente_tipo cltp on cltp.cltp_id = aux.cltp_id "
					+ "  where aux.dcli_icunificado = '2' "
					+ "       and cltp.cltp_icpessoafisicajuridica = 2 "
					+ "       and clie_nncnpj is not null "
					+ "       and LENGTH(trim(clie_nncnpj)) > 0 "
					+ "       and clie_nncnpj != '00000000000000' "
					+ "  group by cltp.cltp_icpessoafisicajuridica, clie_nncnpj "
					+ "  order by documento ";
			
			retorno = (Collection<Object[]>) 
						session.createSQLQuery(consulta)
						.addScalar("tipoDocumento",Hibernate.SHORT)
						.addScalar("documento",Hibernate.STRING)
						.addScalar("codigoCliente",Hibernate.INTEGER)
						.setMaxResults(quantidadeRegistros)
						.list();

		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 
	 * [IT0005] Obter Clientes com mesmo CPF/CNPJ
	 * 
	 * @author Mariana Victor
	 * @date 21/01/2013
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterClientesComMesmoCpfCnpj(Integer codigoClienteAtual, 
			String numeroCPF, String numeroCNPJ) throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select clie_id AS codigoCliente "
					+ " from dados_clientes_rm7620 "
					+ " where dcli_icunificado = '2' "
					+ "   and clie_id <> :codigoClienteAtual ";
			
			if (numeroCPF != null && !numeroCPF.trim().equals("")) {
				consulta = consulta + " and clie_nncpf = :numeroCPF ";
			}
			if (numeroCNPJ != null && !numeroCNPJ.trim().equals("")) {
				consulta = consulta + " and clie_nncnpj = :numeroCNPJ ";
			}
			
			Query query = session.createSQLQuery(consulta)
					.addScalar("codigoCliente",Hibernate.INTEGER)
					.setInteger("codigoClienteAtual", codigoClienteAtual);

			if (numeroCPF != null && !numeroCPF.trim().equals("")) {
				query.setString("numeroCPF", numeroCPF);
			}
			if (numeroCNPJ != null && !numeroCNPJ.trim().equals("")) {
				query.setString("numeroCNPJ", numeroCNPJ);
			}
			
			retorno = (Collection<Integer>) 
						query.list();

		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 
	 * [IT0010] Obter Dados dos Clientes Unificados
	 * 
	 * @author Mariana Victor
	 * @date 22/01/2013
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosClientesUnificados() 
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select clie_idatual AS clienteAtual, "
					+ "   clie_idanterior AS clienteAnt, "
					+ "   clun_nncpf AS cpf, "
					+ "   clun_nncnpj AS cnpj, "
					+ "   clun_nmclienteatual AS nomeAtual, "
					+ "   clun_nmclienteanterior AS nomeAnt, "
					+ "   imov_id AS imovel "
					+ " from cadastro.cliente_unificado  "
					+ " group by clie_idatual, clie_idanterior, "
					+ "   clun_nncpf, clun_nncnpj, clun_nmclienteatual, "
					+ "   clun_nmclienteanterior, imov_id "
					+ " order by clie_idatual ";
			
			retorno = (Collection<Object[]>) 
						session.createSQLQuery(consulta)
						.addScalar("clienteAtual",Hibernate.INTEGER)
						.addScalar("clienteAnt",Hibernate.INTEGER)
						.addScalar("cpf",Hibernate.STRING)
						.addScalar("cnpj",Hibernate.STRING)
						.addScalar("nomeAtual",Hibernate.STRING)
						.addScalar("nomeAnt",Hibernate.STRING)
						.addScalar("imovel",Hibernate.INTEGER)
						.list();

		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 
	 * [IT0013] Atualizar Cliente Unificado
	 * 
	 * 1. O sistema atualiza o cliente na tabela DADOS_CLIENTES_RM7620 
	 * 
	 * @author Mariana Victor
	 * @date 25/01/2013
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarClienteUnificado(Integer idCliente)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();

		String sqlQueryFaturSituacaoHist;
		PreparedStatement st = null;
		
		String update;

		try {
			
			// declara o tipo de conexao
			Connection jdbcCon = session.connection();
			
			update ="update dados_clientes_rm7620 "+
					  "	SET dcli_icunificado =  ? "+
						 " WHERE clie_id = ?  ";
			
			st = jdbcCon.prepareStatement(update);
			st.setString(1, "1");
			st.setInt(2, idCliente);
		
			// executa o update
			st.executeUpdate();
			session.flush();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st){
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			}
			HibernateUtil.closeSession(session);
		}
		
	}

	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 
	 * [IT0015] Obter Total Clientes Anteriores
	 * 
	 * @author Mariana Victor
	 * @date 31/01/2013
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer obterTotalClientesAnteriores() 
			throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select count(distinct clie_idanterior) AS quantidade "
					+ " from cadastro.cliente_unificado  ";
			
			retorno = (Integer) 
						session.createSQLQuery(consulta)
						.addScalar("quantidade",Hibernate.INTEGER)
						.setMaxResults(1).uniqueResult();

		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}	

	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0002] - Validar Atributo Categoria
	 * 
	 * @author Vivianne Sousa
	 * @since 29/01/2013
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterSubcategoriaAtualizacaoCadastral(
			Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select scat_id subcategoria"//0 
					+ " from cadastro.imovel_subcatg_atlz_cad "
					+ " where imac_id = :idImovelAtualizacaoCadastral ";

			retorno =  (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("subcategoria", Hibernate.INTEGER)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [IT0005] Pesquisar Dados Registro Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 19/03/2013
	 */
	public Collection<ClienteImovel> pesquisarDadosRegistroImovel(Integer idImovel) throws ErroRepositorioException{
		
		Collection<ClienteImovel> retorno = new ArrayList<ClienteImovel>();
		Session session = HibernateUtil.getSession();
		
		try{
			String sql = " select clim " +
					     " from ClienteImovel clim " +
					     " inner join fetch clim.imovel imov " +
					     " inner join fetch clim.cliente clie " +
					     " where clim.dataFimRelacao is null " +
					     " and clim.clienteRelacaoTipo = 2 " +
					     " and imov.id = :idImovel ";
			
			retorno = session.createQuery(sql)
							 .setInteger("idImovel", idImovel)
							 .list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * Retorna os logradouros, logradouroBairro e logradouroCep
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCadastral(String idEmpresa, String idLocalidade) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = ""
					+ "SELECT logra.loac_id                       AS idLogradouro, " //0
					+ "       lograTipo.lgtp_id                   AS idTipo, " //1
					+ "       lograTipo.lgtp_dslogradourotipo     AS nomeTipo, " //2
					+ "       lograTitulo.lgtt_id                 AS idTitulo, " //3
					+ "       lograTitulo.lgtt_dslogradourotitulo AS nomeTitulo, " //4
					+ "       logra.loac_nmlogradouro             AS nomeLogradouro, " //5
					+ "       logra.loac_nmpopular                AS nomePopular, " //6
					+ "       municipio.muni_id                   AS idMunicipio, " //7
					+ "       municipio.muni_nmmunicipio          AS nomeMunicipio, " //8
					+ "       bairro.bair_id                      AS idBairro, " //9
					+ "       bairro.bair_nmbairro                AS nomeBairro, " //10
					+ "       cep.ceac_id                         AS idCep, " //11
					+ "       cep.ceac_cdcep                      AS codigoCep " //12
					+ "FROM   cadastro.logradouro_atl_cadastral logra "
					+ "       left join cadastro.logradouro_tipo lograTipo "
					+ "              ON lograTipo.lgtp_id = logra.lgtp_id "
					+ "       left join cadastro.logradouro_titulo lograTitulo "
					+ "              ON lograTitulo.lgtt_id = logra.lgtt_id "
					+ "       left join cadastro.municipio municipio "
					+ "              ON logra.muni_id = municipio.muni_id "
					+ "       left join cadastro.log_bairro_atl_cadastral logBairro "
					+ "              ON logra.loac_id = logBairro.loac_id "
					+ "       left join cadastro.bairro bairro "
					+ "              ON logBairro.bair_id = bairro.bair_id "
					+ "       left join cadastro.log_cep_atlz_cadastral logCep "
					+ "              ON logra.loac_id = logCep.loac_id "
					+ "       left join cadastro.cep_atlz_cadastral cep "
					+ "              ON logCep.ceac_id = cep.ceac_id "
					+ "WHERE  logra.loca_id = :idLocalidade "
					+ "       AND logra.empr_id = :idEmpresa "
					+ "GROUP  BY logra.loac_id, "
					+ "          lograTipo.lgtp_id, "
					+ "          lograTipo.lgtp_dslogradourotipo, "
					+ "          lograTitulo.lgtt_id, "
					+ "          lograTitulo.lgtt_dslogradourotitulo, "
					+ "          logra.loac_nmlogradouro, "
					+ "          logra.loac_nmpopular, "
					+ "          municipio.muni_id, "
					+ "          municipio.muni_nmmunicipio, "
					+ "          bairro.bair_id, "
					+ "          bairro.bair_nmbairro, "
					+ "          cep.ceac_id, "
					+ "          cep.ceac_cdcep";
			
			retorno = (Collection<Object[]>) 
						session.createSQLQuery(consulta)
						.addScalar("idLogradouro",Hibernate.STRING)
						.addScalar("idTipo",Hibernate.STRING)
						.addScalar("nomeTipo",Hibernate.STRING)
						.addScalar("idTitulo",Hibernate.STRING)
						.addScalar("nomeTitulo",Hibernate.STRING)
						.addScalar("nomeLogradouro",Hibernate.STRING)
						.addScalar("nomePopular",Hibernate.STRING)
						.addScalar("idMunicipio",Hibernate.STRING)
						.addScalar("nomeMunicipio",Hibernate.STRING)
						.addScalar("idBairro",Hibernate.STRING)
						.addScalar("nomeBairro",Hibernate.STRING)
						.addScalar("idCep",Hibernate.STRING)
						.addScalar("codigoCep",Hibernate.STRING)
						.setString("idEmpresa", idEmpresa)
						.setString("idLocalidade", idLocalidade)
						.list();

		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = ""
					+ "SELECT logra.loac_id                       AS idLogradouro, " //0
					+ "       lograTipo.lgtp_id                   AS idTipo, " //1
					+ "       lograTipo.lgtp_dslogradourotipo     AS nomeTipo, " //2
					+ "       lograTitulo.lgtt_id                 AS idTitulo, " //3
					+ "       lograTitulo.lgtt_dslogradourotitulo AS nomeTitulo, " //4
					+ "       logra.loac_nmlogradouro             AS nomeLogradouro, " //5
					+ "       logra.loac_nmpopular                AS nomePopular, " //6
					+ "       municipio.muni_id                   AS idMunicipio, " //7
					+ "       municipio.muni_nmmunicipio          AS nomeMunicipio " //8
					+ "FROM   cadastro.logradouro_atl_cadastral logra "
					+ "       left join cadastro.logradouro_tipo lograTipo "
					+ "              ON lograTipo.lgtp_id = logra.lgtp_id "
					+ "       left join cadastro.logradouro_titulo lograTitulo "
					+ "              ON lograTitulo.lgtt_id = logra.lgtt_id "
					+ "       left join cadastro.municipio municipio "
					+ "              ON logra.muni_id = municipio.muni_id "
					+ "WHERE  logra.loca_id = :idLocalidade "
					+ "       AND logra.empr_id = :idEmpresa ";
			
			retorno = (Collection<Object[]>) 
					session.createSQLQuery(consulta)
					.addScalar("idLogradouro",Hibernate.STRING)
					.addScalar("idTipo",Hibernate.STRING)
					.addScalar("nomeTipo",Hibernate.STRING)
					.addScalar("idTitulo",Hibernate.STRING)
					.addScalar("nomeTitulo",Hibernate.STRING)
					.addScalar("nomeLogradouro",Hibernate.STRING)
					.addScalar("nomePopular",Hibernate.STRING)
					.addScalar("idMunicipio",Hibernate.STRING)
					.addScalar("nomeMunicipio",Hibernate.STRING)
					.setString("idEmpresa", idEmpresa)
					.setString("idLocalidade", idLocalidade)
					.list();
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroBairroAtlzCad(String idLogradouro) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = ""
					+ "SELECT lograBairro.lbac_id AS idLograBairro, "
					+ "       lograbairro.bair_id AS idBairro, "
					+ "       bairro.bair_nmbairro AS nomeBairro "
					+ "FROM   cadastro.log_bairro_atl_cadastral lograBairro "
					+ "       left join cadastro.bairro bairro "
					+ "              ON lograBairro.bair_id = bairro.bair_id "
					+ "WHERE  lograBairro.loac_id = :idLogradouro";
			
			retorno = (Collection<Object[]>) 
					session.createSQLQuery(consulta)
					.addScalar("idLograBairro",Hibernate.STRING)
					.addScalar("idBairro",Hibernate.STRING)
					.addScalar("nomeBairro",Hibernate.STRING)
					.setString("idLogradouro", idLogradouro)
					.list();			
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroCepAtlzCad(String idLogradouro) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String consulta = ""
					+ "SELECT lograCep.lcac_id AS idLograCep, "
					+ "       cep.ceac_id      AS idCep, "
					+ "       cep.ceac_cdcep   AS codCep "
					+ "FROM   cadastro.log_cep_atlz_cadastral lograCep "
					+ "       left join cadastro.cep_atlz_cadastral cep "
					+ "              ON cep.ceac_id = lograCep.ceac_id "
					+ "WHERE  lograCep.loac_id = :idLogradouro";
			
			retorno = (Collection<Object[]>) 
					session.createSQLQuery(consulta)
					.addScalar("idLograCep",Hibernate.STRING)
					.addScalar("idCep",Hibernate.STRING)
					.addScalar("codCep",Hibernate.STRING)
					.setString("idLogradouro", idLogradouro)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 *  [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * 
	 * @author Anderson Cabral
	 * @since 18/03/2013
	 * 
	 * @param idParametro
	 * @return Retorna um Collection<Integer> com os numeros das quadras
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer>  pesquisarRoteiroQuadra(Integer idParametro) throws ErroRepositorioException{
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String consulta = ""
					+ "SELECT roteiroQuadra.rqac_nnquadra AS numeroQuadra "
					+ "FROM   cadastro.roteiro_quadra_atlz_cad roteiroQuadra "
					+ "WHERE  roteiroQuadra.ptac_id = :idParametro "
					+ "ORDER BY  roteiroQuadra.rqac_nnquadra  ";
			
			retorno = (Collection<Integer>) 
					session.createSQLQuery(consulta)
					.addScalar("numeroQuadra",Hibernate.INTEGER)
					.setInteger("idParametro", idParametro)
					.list();
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * @author Arthur Carvalho
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdImovelAtualizacaoCadastral(String codigo) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select imac.id " + "from ImovelAtualizacaoCadastral imac "
					+ "where imac.codigo = :codigo ";

			retorno = (Integer) session.createQuery(consulta)
					.setString("codigo", codigo)
					.setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * @author Arthur Carvalho
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteAtualizacaoCadastral(String codigo) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select clac.id " + "from ClienteAtualizacaoCadastral clac "
					+ " inner join clac.imovelAtualizacaoCadastral imac "
					+ "where imac.codigo = :codigo ";

			retorno = (Integer) session.createQuery(consulta)
					.setString("codigo", codigo)
					.setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa se o imovel ja foi transmitido do tablet pro pre-gsan
	 * 
	 * @author Arthur Carvalho
	 * @since 20/04/2012
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtlzCadastralJaTransmitido(Integer idImovel, Integer idComando)throws ErroRepositorioException{
		
		Integer quantidade = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try{
			consulta += "select count(imac.imac_id) quantidade "
					 +	"from CADASTRO.imovel_atlz_cadastral imac " 
					 +	"where imac.imac_icdadosretorno in (1, 3) and imac.imov_id = :idImovel and imac.ptac_id = :idComando";
			
			quantidade = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel)
					.setInteger("idComando", idComando)
					.uniqueResult();
			
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return quantidade;
	}
	
	
	/**
	 * Pesquisa se o imovel novo ja foi transmitido do tablet pro pre-gsan
	 * 
	 * @author Rafael Pinto
	 * @since 19/07/2013
	 * 
	 * @param codigoImovel
	 * @param idComando
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtlzCadastralNovoJaTransmitido(String codigoImovel, Integer idComando)
		throws ErroRepositorioException{
		
		Integer quantidade = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try{
			consulta += "select count(imac.imac_id) quantidade "
					 +	"from CADASTRO.imovel_atlz_cadastral imac " 
					 +	"where imac.imac_icdadosretorno in (1, 3) and imac.imac_cdimovel = :codigoImovel and imac.ptac_id = :idComando";
			
			quantidade = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setString("codigoImovel", codigoImovel)
					.setInteger("idComando", idComando)
					.uniqueResult();
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return quantidade;
	}
	
	
	/**
	 * @author Arthur Carvalho
	 * @date 22/05/2013
	 * 
	 * @param helper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImovelPPP(DadosContratoPPPHelper helper) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Integer> retorno = null;

		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "SELECT distinct(imov.imov_id) as idImovel "
					+ "from cadastro.imovel imov "
					+ "inner join cadastro.localidade loca on loca.loca_id = imov.loca_id "
					+ "inner join cadastro.municipio muni on muni.muni_id = loca.muni_idprincipal "
					+ "inner join cadastro.logradouro_bairro lgbr on imov.lgbr_id = lgbr.lgbr_id "
					+ "inner join cadastro.logradouro logr on logr.logr_id = lgbr.logr_id ";

			//Monta o INNER JOIN
			if ( helper.getCodigoSetorComercialInicial() != null && !"".equals(helper.getCodigoSetorComercialInicial())) {
				consulta += "inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id ";
			}
			
			if ( helper.getNumeroQuadraInicial() != null && !"".equals(helper.getNumeroQuadraInicial())) {
				consulta += "inner join cadastro.quadra qdra on imov.qdra_id = qdra.qdra_id ";
			}
			
			if ( helper.getNumeroRotaInicial() != null && !"".equals(helper.getNumeroRotaInicial())) {
				consulta += "inner join micromedicao.rota rota on rota.rota_id = qdra.rota_id ";
			}
			//Monta o INNER JOIN
			
			//adiciona os Parametros da pesquisa
			consulta += "where loca.loca_iccontratoppp = 1 and imov.imov_icexclusao <> 1 and "
					 + "muni.muni_id = :idMunicipio and logr.logr_id = :idLogradouro and ";
					
			parameters.put("idMunicipio", helper.getIdMunicipio());
			parameters.put("idLogradouro", helper.getIdLogradouro());
					
			if ( helper.getIndicadorTipoOperacao().equals("2") ) {
				consulta += "imov.lest_id = " + LigacaoEsgotoSituacao.FACTIVEL_FATURAVEL+" and ";
			} else {
				//Potencial e factivel
				consulta += "imov.lest_id in (1, 2) and "; 
			}
			
			//localidade
			if (helper.getIdLocalidadeInicial() != null && !"".equals(helper.getIdLocalidadeInicial())) {

				consulta += " loca.loca_id between :localidadeInicial AND :localidadeFinal AND ";
				parameters.put("localidadeInicial", helper.getIdLocalidadeInicial());
				
				if ( helper.getIdLocalidadeFinal() != null && !helper.getIdLocalidadeFinal().equals("") ) {
					parameters.put("localidadeFinal", helper.getIdLocalidadeFinal());	
				} else {
					parameters.put("localidadeFinal", helper.getIdLocalidadeInicial());
				}
			}
			
			//setor comercial
			if ( helper.getCodigoSetorComercialInicial() != null && !"".equals(helper.getCodigoSetorComercialInicial())) {

				consulta += " stcm.stcm_cdsetorcomercial between :codigoSetorComercialInicial AND :codigoSetorComercialFinal AND ";
				parameters.put("codigoSetorComercialInicial", helper.getCodigoSetorComercialInicial());
				
				if ( helper.getCodigoSetorComercialFinal() != null && !helper.getCodigoSetorComercialFinal().equals("") ) {
					parameters.put("codigoSetorComercialFinal", helper.getCodigoSetorComercialFinal());	
				} else {
					parameters.put("codigoSetorComercialFinal", helper.getCodigoSetorComercialInicial());
				}
			}

			//quadra
			if ( helper.getNumeroQuadraInicial() != null && !"".equals(helper.getNumeroQuadraInicial())) {

				consulta += " qdra.qdra_nnquadra between :numeroQuadraInicial AND :numeroQuadraFinal AND ";
				parameters.put("numeroQuadraInicial", helper.getNumeroQuadraInicial());
				
				if ( helper.getNumeroQuadraFinal()!= null && !helper.getNumeroQuadraFinal().equals("") ) {
					parameters.put("numeroQuadraFinal", helper.getNumeroQuadraFinal());	
				} else {
					parameters.put("numeroQuadraFinal", helper.getNumeroQuadraInicial());
				}
			}
			
			//rota
			if ( helper.getNumeroRotaInicial() != null && !"".equals(helper.getNumeroRotaInicial())) {

				consulta += " rota.rota_cdrota between :rotaInicial AND :rotaFinal AND ";
				parameters.put("rotaInicial", helper.getNumeroRotaInicial());
				
				if ( helper.getNumeroRotaFinal() != null && !helper.getNumeroRotaFinal().equals("") ) {
					parameters.put("rotaFinal", helper.getNumeroRotaFinal());	
				} else {
					parameters.put("rotaFinal", helper.getNumeroRotaInicial());
				}
			}
			
			//sequencial rota
			if ( helper.getNumeroSequencialRotaInicial() != null && !"".equals(helper.getNumeroSequencialRotaInicial())) {

				consulta += " imov.imov_nnsequencialrota between :rotaSequenciaInicial AND :rotaSequenciaFinal AND ";
				parameters.put("rotaSequenciaInicial", helper.getNumeroSequencialRotaInicial());
				
				if ( helper.getNumeroSequencialRotaFinal() != null && !helper.getNumeroSequencialRotaFinal().equals("") ) {
					parameters.put("rotaSequenciaFinal", helper.getNumeroSequencialRotaFinal());	
				} else {
					parameters.put("rotaSequenciaFinal", helper.getNumeroSequencialRotaInicial());
				}
			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				query.setParameter(key, parameters.get(key));
			}

			retorno = (Collection<Integer>) query.list();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * @author Anderson Cabral
	 * @date 20/06/2013
	 */
	public Collection<Object []> pesquisarBairrosImovel(Integer idLocalidade) throws ErroRepositorioException{
		Collection<Object []> colecaoBairros =  new ArrayList<Object[]>();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = "select distinct bairro.bair_id as idBairro, " //0 
					 +	"bairro.bair_cdbairro as codigoBairro, " //1 
					 +	"bairro.bair_nmbairro as nomeBairro " //2
					 +	"FROM CADASTRO.bairro bairro "
					 +	"INNER JOIN CADASTRO.localidade localidade ON localidade.muni_idprincipal = bairro.muni_id "
					 +	"WHERE localidade.loca_id = :idLocalidade ";
			

				colecaoBairros = (Collection<Object[]>) session.createSQLQuery(consulta)
										.addScalar("idBairro", Hibernate.INTEGER)
										.addScalar("codigoBairro", Hibernate.INTEGER)
										.addScalar("nomeBairro", Hibernate.STRING)
										.setInteger("idLocalidade", idLocalidade)
										.list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoBairros;
	}

	/**
	 *  [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * @author Maxwell Moreira
	 * @since 20/06/2013
	 * 
	 * @param idParametro
	 * @return Retorna um Collection<Integer> com os numeros de imoveis
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer>  pesquisarImoveisAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException{
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String consulta = ""
					
					+ "SELECT imovelAtualizacaoCadastral.imov_id as imovel FROM CADASTRO.IMOVEL_ATLZ_CADASTRAL imovelAtualizacaoCadastral WHERE IMAC_ICDADOSRETORNO = 2 " +
					" AND imovelAtualizacaoCadastral.ptac_id = :idParametro ORDER BY imovel";
				
			retorno = (Collection<Integer>) 
					session.createSQLQuery(consulta)
					.addScalar("imovel",Hibernate.INTEGER)
					.setInteger("idParametro", idParametro)
					.list();
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Metodo responsavel por verificar se existe subsistema com essa descricao
	 * @author Arthur Carvalho
	 * @date 18/06/2013
	 * @param descricaoSubSistema
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaSubSistema(String descricaoSubSistema) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "select subsis.id from SubSistemaEsgoto subsis "
					+  " where upper(subsis.descricao) = upper(translate(:descricaoSubSistema,'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç','AAAAAAAAEEEEIIOOOOOOUUUUCC')) ";

			retorno = (Integer) session.createQuery(consulta)
					.setString("descricaoSubSistema", descricaoSubSistema).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {

			//levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			//fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Metodo responsavel por pesquisar os usuários da atualização cadastral
	 * 
	 * @author Vivianne Sousa
	 * @date 22/07/2013
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarUsuarioAtuCadastral(Integer idEmpresa) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = " select distinct usur " 
					+ " from Leiturista leit " 
					+ " inner join leit.usuario usur "
					+ " inner join leit.empresa empr "
					+ " where leit.indicadorAtualizacaoCadastral = :indicadorAtualizacaoCadastral " 
					+ " and empr.id = :idEmpresa "
					+ " order by usur.nomeUsuario ";

			retorno = (Collection) session.createQuery(consulta)
					.setShort("indicadorAtualizacaoCadastral", ConstantesSistema.SIM)
					.setInteger("idEmpresa", idEmpresa)
					.list();

		} catch (HibernateException e) {

			//levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			//fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 * 
	 * @author Arthur Carvalho
	 * @date 24/07/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarResumoImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper, String indicadorTipoPesquisa)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		
		String select = "";
		
		if ( indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("USUARIO") ) {
			
			select = " usur.login , usur.nomeUsuario, count(iia.imovel.id) ";
			
		} else if (indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("SITUACAOLIGACAO") ) {
			
			select = " last.descricao , count(iia.imovel.id) ";
		} else if(relatorioHelper.getIndicadorInscricaoAtualAnterior() != null &&
				relatorioHelper.getIndicadorInscricaoAtualAnterior().equals(ConstantesSistema.NAO)){
			
			select = " iia.localidadeAnterior.id , iia.localidadeAnterior.descricao ";
			if (indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("SETOR") ) {
				
				select = "  iia.setorComercialAnterior.codigo, count(iia.imovel.id) ";
			}
		} else {
			
			select = " iia.localidadeAtual.id , iia.localidadeAtual.descricao ";
			if (indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("SETOR") ) {
				
				select = " iia.setorComercialAtual.codigo, count(iia.imovel.id) ";
			}
		}
		
		try {

			consulta = " select  " + select
					+ " from ImovelInscricaoAlterada iia "
					+ " INNER JOIN iia.imovel imov "
					+ " INNER JOIN imov.ligacaoAguaSituacao last "
					+ " LEFT JOIN iia.usuarioAutorizacao usur "
					+ " where iia.indicadorAtualizacaoExcluida = 2 ";
					

			parameters.put("dataInicial", relatorioHelper.getDataInicio());
			parameters.put("dataFinal", relatorioHelper.getDataFim());

			// Tipo da Consulta

			// Imóveis alterados com sucesso.
			if (relatorioHelper.getEscolhaRelatorio().intValue() == 1) {

				consulta += " and iia.indicadorAtualizado = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóveis sem alteração devido a erro.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 2) {

				consulta += " and iia.indicadorErroAlteracao = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóvel pendente de alteração.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 3) {

				consulta += " and iia.indicadorAtualizado = 2 and iia.indicadorErroAlteracao is null "
						  + " and iia.ultimaAlteracao between :dataInicial and :dataFinal ";
			}

			
			if(relatorioHelper.getIndicadorInscricaoAtualAnterior() != null &&
					relatorioHelper.getIndicadorInscricaoAtualAnterior().equals(ConstantesSistema.NAO)){
				//pesquisa por inscrição ANTERIOR
				
				if ( relatorioHelper.getIdLocalidadeEncontrada() != null ) {
					parameters.put("localidadeInicial", relatorioHelper.getIdLocalidadeEncontrada());

					consulta += " and iia.localidadeAnterior =:localidadeInicial ";
					
				} else 
				// Localidade
				if (relatorioHelper.getLocalidadeInicial() != null && relatorioHelper.getLocalidadeFinal() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());
					parameters.put("localidadeFinal", relatorioHelper.getLocalidadeFinal());

					consulta += " and iia.localidadeAnterior between :localidadeInicial and :localidadeFinal ";
					
				} else if (relatorioHelper.getLocalidadeInicial() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());

					consulta += " and iia.localidadeAnterior =:localidadeInicial ";
				}

				// Setor Comercial
				if (relatorioHelper.getSetorComercialInicial() != null && relatorioHelper.getSetorComercialFinal() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getCodigoSetorComercialInicial());
					parameters.put("setoComercialFinal", relatorioHelper.getCodigoSetorComercialFinal());

					consulta += " and iia.setorComercialAnterior.codigo between :setoComercialInicial and :setoComercialFinal ";
					
					
				} else if (relatorioHelper.getSetorComercialInicial() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getSetorComercialInicial());

					consulta += " and iia.setorComercialAnterior =:setoComercialInicial ";
				}

				// Quadra
				if (relatorioHelper.getQuadraInicial() != null && relatorioHelper.getQuadraFinal() != null) {

					parameters.put("quadraInicial", relatorioHelper.getNumeroQuadraInicial());
					parameters.put("quadraFinal", relatorioHelper.getNumeroQuadraFinal());

					consulta += " and iia.quadraAnterior.numeroQuadra between :quadraInicial and :quadraFinal ";
					
					
				} else if (relatorioHelper.getQuadraInicial() != null) {

					parameters.put("quadraInicial", relatorioHelper.getQuadraInicial());

					consulta += " and iia.quadraAnterior =:quadraInicial ";
				}

				// Lote
				if (relatorioHelper.getLoteInicial() != null && relatorioHelper.getLoteFinal() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());
					parameters.put("loteFinal", relatorioHelper.getLoteFinal());

					consulta += " and iia.loteAnterior between :loteInicial and :loteFinal ";
				} else if (relatorioHelper.getLoteInicial() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());

					consulta += " and iia.loteAnterior =:loteInicial ";
				}

				// Sub-Lote
				if (relatorioHelper.getSubLoteInicial() != null	&& relatorioHelper.getSubLoteFinal() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());
					parameters.put("subLoteFinal", relatorioHelper.getSubLoteFinal());

					consulta += " and iia.subLoteAnterior between :subLoteInicial and :subLoteFinal ";
					
				} else if (relatorioHelper.getSubLoteInicial() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());

					consulta += " and iia.subLoteAnterior =:subLoteInicial ";
				}

				
			}else{
				//pesquisa por inscrição ATUAL
				if ( relatorioHelper.getIdLocalidadeEncontrada() != null ) {
					parameters.put("localidadeInicial", relatorioHelper.getIdLocalidadeEncontrada());

					consulta += " and iia.localidadeAtual =:localidadeInicial ";
					
				} else 
				// Localidade
				if (relatorioHelper.getLocalidadeInicial() != null && relatorioHelper.getLocalidadeFinal() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());
					parameters.put("localidadeFinal", relatorioHelper.getLocalidadeFinal());

					consulta += " and iia.localidadeAtual between :localidadeInicial and :localidadeFinal ";
					
				} else if (relatorioHelper.getLocalidadeInicial() != null) {

					parameters.put("localidadeInicial", relatorioHelper.getLocalidadeInicial());

					consulta += " and iia.localidadeAtual =:localidadeInicial ";
				}

				// Setor Comercial
				if (relatorioHelper.getSetorComercialInicial() != null && relatorioHelper.getSetorComercialFinal() != null) {

					
					parameters.put("setoComercialInicial", relatorioHelper.getCodigoSetorComercialInicial());
					parameters.put("setoComercialFinal", relatorioHelper.getCodigoSetorComercialFinal());

					consulta += " and iia.setorComercialAtual.codigo between :setoComercialInicial and :setoComercialFinal ";

					
				} else if (relatorioHelper.getSetorComercialInicial() != null) {

					parameters.put("setoComercialInicial", relatorioHelper.getSetorComercialInicial());

					consulta += " and iia.setorComercialAtual =:setoComercialInicial ";
				}

				// Quadra
				if (relatorioHelper.getQuadraInicial() != null && relatorioHelper.getQuadraFinal() != null) {
					
					parameters.put("quadraInicial", relatorioHelper.getNumeroQuadraInicial());
					parameters.put("quadraFinal", relatorioHelper.getNumeroQuadraFinal());

					consulta += " and iia.quadraAtual.numeroQuadra between :quadraInicial and :quadraFinal ";
					
					
				} else if (relatorioHelper.getQuadraInicial() != null) {

					parameters.put("quadraInicial", relatorioHelper.getQuadraInicial());

					consulta += " and iia.quadraAtual =:quadraInicial ";
				}

				// Lote
				if (relatorioHelper.getLoteInicial() != null && relatorioHelper.getLoteFinal() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());
					parameters.put("loteFinal", relatorioHelper.getLoteFinal());

					consulta += " and iia.loteAtual between :loteInicial and :loteFinal ";
				} else if (relatorioHelper.getLoteInicial() != null) {

					parameters.put("loteInicial", relatorioHelper.getLoteInicial());

					consulta += " and iia.loteAtual =:loteInicial ";
				}

				// Sub-Lote
				if (relatorioHelper.getSubLoteInicial() != null	&& relatorioHelper.getSubLoteFinal() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());
					parameters.put("subLoteFinal", relatorioHelper.getSubLoteFinal());

					consulta += " and iia.subLoteAtual between :subLoteInicial and :subLoteFinal ";
					
				} else if (relatorioHelper.getSubLoteInicial() != null) {

					parameters.put("subLoteInicial", relatorioHelper.getSubLoteInicial());

					consulta += " and iia.subLoteAtual =:subLoteInicial ";
				}

			}
			
			if(relatorioHelper.getOrigemAtualizacao() != null){
				
				if(relatorioHelper.getOrigemAtualizacao().equals("1")){
					
					parameters.put("origemAtualizacao", new Integer("4"));

					consulta += " and iia.codigoOrigem = :origemAtualizacao ";
					
				}else 	if(relatorioHelper.getOrigemAtualizacao().equals("2")){
					
					parameters.put("origemAtualizacao", new Integer("4"));

					consulta += " and iia.codigoOrigem <> :origemAtualizacao ";
					
				}
			}
			
			
			
			String orderby = "";
			String groupby = "";
			if ( indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("USUARIO") ) {
				
				orderby = " order by usur.login, usur.nomeUsuario ";
				groupby = " group by usur.login, usur.nomeUsuario ";
				
			} else if (indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("SITUACAOLIGACAO") ) {
				
				groupby = " group by last.descricao";
				orderby = " order by last.descricao";
			} else if(relatorioHelper.getIndicadorInscricaoAtualAnterior() != null &&
					relatorioHelper.getIndicadorInscricaoAtualAnterior().equals(ConstantesSistema.NAO)){
				
				orderby = " order by iia.localidadeAnterior.id, iia.localidadeAnterior.descricao ";
				groupby = " group by iia.localidadeAnterior.id, iia.localidadeAnterior.descricao ";
				if (indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("SETOR") ) {
					
					groupby = " group by iia.setorComercialAnterior.codigo";
					orderby = " order by iia.setorComercialAnterior.codigo";
				} 
			} else {
				groupby = " group by iia.localidadeAtual.id, iia.localidadeAtual.descricao ";
				orderby = " order by iia.localidadeAtual.id, iia.localidadeAtual.descricao ";
				if (indicadorTipoPesquisa != null && indicadorTipoPesquisa.equals("SETOR") ) {
					
					groupby = " group by iia.setorComercialAtual.codigo";
					orderby = " order by iia.setorComercialAtual.codigo";
				} 
			}
			
			
			
			
			
			consulta += groupby + orderby;

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexao Esgoto
	 * @author Hugo Azevedo
	 * @date 02/08/2013
	 * 
	 */
	public Municipio pesquisarMunicipio(Integer idMunicipio) throws ErroRepositorioException{
		
		Municipio retorno = null;
		Session session = HibernateUtil.getSession();
		
		try{
			String sql = " Select muni " +
						 " from Municipio muni " +
						 " where muni.id = :idMunicipio " +
						 " and muni.indicadorUso = :indicadorUso ";
			
			retorno = (Municipio)session.createQuery(sql)
										.setInteger("idMunicipio", idMunicipio)
										.setShort("indicadorUso", ConstantesSistema.INDICADOR_USO_ATIVO)
										.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}

	
	/**
	 * [UC0011] Inserir Imovel
	 * [FS0025] Validar Número do Medidor de Energia
	*/
	public Collection pesquisarMedidorEnergia(String numeroMedidorEnergia) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection retorno = null;
		try {
			consulta = "SELECT imov_id as idImovel," +
						" imov_nnmedidorenergia as medidorEnergia" +
						" FROM cadastro.imovel" +
						" where imov_nnmedidorenergia ='"+numeroMedidorEnergia+"'";
			
			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("medidorEnergia", Hibernate.STRING)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
			return retorno;
	}
	
	/**
	 * [UC 1392] Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT 0006] Exibir Dados Cadastrador
	 */
	public Collection pesquisarDadosCadastrador(Integer idParametroAtualizacaoCadastral) throws ErroRepositorioException{
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"select imac_nmlogin as cpf, "
					 + 	"usur_nmusuario as nomeusuario, "
					 +	"count(distinct (imac_id)) as quantidadeImoveis "
					 +	"from cadastro.imovel_atlz_cadastral imac "
					 +	"left join seguranca.usuario usur "
					 +	"on (usur.usur_nmlogin = imac.imac_nmlogin " 
					 +	"or usur.usur_nncpf = imac.imac_nmlogin) "
					 +	"where imac.imac_icdadosretorno in (1,3) "
					 +	"and imac.ptac_id = :idParametro "
					 +	"group by imac_nmlogin, usur_nmusuario "
					 +	"order by usur_nmusuario "; 
			
			retorno = session.createSQLQuery(consulta)
								.addScalar("cpf", Hibernate.STRING)
								.addScalar("nomeUsuario", Hibernate.STRING)
								.addScalar("quantidadeImoveis", Hibernate.INTEGER)
								.setInteger("idParametro", idParametroAtualizacaoCadastral)
								.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0004] - Pesquisar Quantidade de Imóveis Transferidos 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public Integer pesquisarQtdeImoveisTransferidos(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException{
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT COUNT(DISTINCT imovel.imov_id) quantidadetrasnferidos " +
						"FROM CADASTRO.imovel_end_arq_txt arquivo " +
						"INNER JOIN CADASTRO.imovel imovel ON arquivo.imov_id = imovel.imov_id " +
						"INNER JOIN CADASTRO.logradouro_bairro logbairro ON arquivo.logr_id = logbairro.logr_id AND arquivo.bair_id = logbairro.bair_id " +
						"WHERE imovel.lgbr_id <> logbairro.lgbr_id " +
						"AND arquivo.logr_id = :idLogradouro " +
						"AND arquivo.bair_id = :idBairro "; 
			
			retorno = (Integer) session.createSQLQuery(consulta)
								.addScalar("quantidadetrasnferidos", Hibernate.INTEGER)
								.setInteger("idLogradouro", idLogradouro)
								.setInteger("idBairro", idBairro).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0003] - Pesquisar Quantidade de Imóveis Total 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public Integer pesquisarQtdeTotalImoveis(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException{
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT COUNT(DISTINCT imovel.imov_id) quantidadetotal " +
						"FROM CADASTRO.imovel_end_arq_txt arquivo " +
						"INNER JOIN CADASTRO.imovel imovel ON arquivo.imov_id = imovel.imov_id " +
						"WHERE arquivo.logr_id = :idLogradouro " +
						"AND arquivo.bair_id = :idBairro "; 
			
			retorno = (Integer) session.createSQLQuery(consulta)
								.addScalar("quantidadetotal", Hibernate.INTEGER)
								.setInteger("idLogradouro", idLogradouro)
								.setInteger("idBairro", idBairro).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	
	
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Integer pesquisarPremioPrincipal(Integer idPremio)
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String scriptSql = "";
		Integer retorno = null;
		
		try {
			
			scriptSql +="SELECT COUNT(*) AS quantidadeRetorno" +
						" FROM cadastro.premio_sorteio" +
						" WHERE prso_id = :idPremio" +
						" AND prso_icpremioprincipal = :indicadorSim"; 
			
			retorno = (Integer)session.createSQLQuery(scriptSql)
					.addScalar("quantidadeRetorno", Hibernate.INTEGER)
					.setInteger("idPremio", idPremio)
					.setInteger("indicadorSim", ConstantesSistema.SIM)
					.setMaxResults(1)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
			return retorno;
	}
	
	/**
	 * [UC1295] Efetuar Sorteio Premio
	 * 
	 * @author Jonathan Marcos, Mariana Victor
	 * @date 26/09/2013, 08/11/2013
	 */
	public List<Object[]> pesquisarImoveisAptosSorteioFiqueLegal2013(PremioSorteio premioSorteio)
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String scriptSql = "";
		List retorno = null;
		try {
			// [2.2.3.2.1.1] Caso o premio selecionado seja o principal
			if(premioSorteio.getIndicadorInformarPremioPrincipal() != null
					&& premioSorteio.getIndicadorInformarPremioPrincipal().compareTo(ConstantesSistema.SIM) == 0){
				scriptSql+="SELECT "+
				           "ics.imcs_nngerado AS numeroGerado,"+
				           "ics.imov_id AS idImovel,"+
				           "ics.imcs_nmcliente AS nomeCliente,"+
				           "ics.imcs_nncpf AS numeroCpf,"+
				           "ics.imcs_nncnpj AS numeroCnpj," +
				           "ics.imcs_id AS idImovelCadastroSorteio"+
				           " FROM cadastro.imovel_cadastro_sorteio ics"+
				           " WHERE ics.imcs_icimovelapto = :indicadorImovelApto"+
				           " AND ics.imcs_icparticipacaosorteio = :indicadorParticipacaoSorteio"+
				           " AND to_date(to_char(ics.imcs_tminscricao, 'dd/MM/yyyy'), 'dd/MM/yyyy') between :dataInicialPremio and :dataFinalPremio "+
				           " AND ics.imcs_id NOT IN ("+
				           "    SELECT imps2.imcs_id from cadastro.imovel_premio_sorteio imps2" +
				           "	INNER JOIN cadastro.premio_sorteio ps ON (imps2.prso_id=ps.prso_id)" +
				           "	WHERE prso_icpremiosecundario = :indicadorPremioSecundario"+
				           " )";
				retorno = (List<Object>)session.createSQLQuery(scriptSql)
						.addScalar("numeroGerado", Hibernate.INTEGER)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("nomeCliente", Hibernate.STRING)
						.addScalar("numeroCpf", Hibernate.STRING)
						.addScalar("numeroCnpj", Hibernate.STRING)
						.addScalar("idImovelCadastroSorteio",Hibernate.INTEGER)
						.setInteger("indicadorImovelApto", ConstantesSistema.SIM)
						.setInteger("indicadorParticipacaoSorteio", ConstantesSistema.SIM)
						.setInteger("indicadorPremioSecundario", ConstantesSistema.SIM)
						.setDate("dataInicialPremio", premioSorteio.getDataCadastroInicial())
						.setDate("dataFinalPremio", premioSorteio.getDataCadastroFinal())
						.list();
				
			}else{
				// [2.2.3.2.2.1] Caso o premio selecionado nao seja o principal
				scriptSql+="SELECT "+
				           "ics.imcs_nngerado AS numeroGerado,"+
				           "ics.imov_id AS idImovel,"+
				           "ics.imcs_nmcliente AS nomeCliente,"+
				           "ics.imcs_nncpf AS numeroCpf,"+
				           "ics.imcs_nncnpj AS numeroCnpj,"+
				           "ics.imcs_id AS idImovelCadastroSorteio"+
				           " FROM CADASTRO.imovel_cadastro_sorteio ics"+
				           " WHERE ics.imcs_icimovelapto = :indicadorImovelApto"+
				           " AND ics.imcs_icparticipacaosorteio = :indicadorParticipacaoSorteio"+
				           " AND ics.imcs_tminscricao between :dataInicialPremio and :dataFinalPremio "+
				           " AND ics.imcs_id NOT IN ("+
				           "    SELECT imcs2.imcs_id from cadastro.imovel_cadastro_sorteio imcs2" +
				           "	INNER JOIN cadastro.imovel_premio_sorteio imps2 ON (imps2.imcs_id=imcs2.imcs_id)" +
				           ")";
				retorno = (List<Object[]>)session.createSQLQuery(scriptSql)
						.addScalar("numeroGerado", Hibernate.INTEGER)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("nomeCliente", Hibernate.STRING)
						.addScalar("numeroCpf", Hibernate.STRING)
						.addScalar("numeroCnpj", Hibernate.STRING)
						.addScalar("idImovelCadastroSorteio",Hibernate.INTEGER)
						.setInteger("indicadorImovelApto", ConstantesSistema.SIM)
						.setInteger("indicadorParticipacaoSorteio", ConstantesSistema.SIM)
						.setDate("dataInicialPremio", premioSorteio.getDataCadastroInicial())
						.setDate("dataFinalPremio", premioSorteio.getDataCadastroFinal())
						.list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public void atualizarPremioSorteio(Integer idPremioSorteio)
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String scriptSql = "";
		
		try {
			scriptSql+="UPDATE gcom.cadastro.PremioSorteio " +
					   "SET prso_qtsorteiada = (prso_qtsorteiada + 1)," +
					   "prso_tmultimaalteracao = :ultimaAlteracao" +
					   " WHERE prso_id = :idPremio";
			
			 session.createQuery(scriptSql)
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("idPremio", idPremioSorteio)
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Collection<Object[]> obterDadosRelatorioSorteioFiqueLegal2013(String numeroSorteio)
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String scriptSql = "";
		Collection retorno = null;
		try {
			
			scriptSql+="SELECT "+
					   "ps.prso_nnordemsorteio AS numeroOrdemSorteio,"+
					   "ps.prso_dssorteio AS descricaoPremioSorteado,"+
					   "imps.imps_nnordemsorteio AS numeroOrdemSorteioPremio,"+
					   "imcs.imcs_nngerado AS numeroGeradoSorteio,"+
					   "imcs.imov_id AS idImovel,"+
					   "cgr.greg_nmregional AS nomeGerenciaRegional,"+
					   "cl.loca_nmlocalidade AS nomeLocalidade,"+
					   "imcs.imcs_nmcliente AS nomeCliente,"+
					   "imcs.imcs_nncpf AS cpf,"+
					   "imcs.imcs_nncnpj AS cnpj"+
					   " FROM"+
					   " cadastro.imovel_premio_sorteio imps"+
					   " INNER JOIN cadastro.imovel_cadastro_sorteio imcs ON (imps.imcs_id=imcs.imcs_id)"+
					   " INNER JOIN cadastro.premio_sorteio ps ON (imps.prso_id=ps.prso_id)"+
					   " INNER JOIN cadastro.imovel ci ON (imcs.imov_id=ci.imov_id)"+
					   " INNER JOIN cadastro.localidade cl ON (ci.loca_id=cl.loca_id)"+
					   " INNER JOIN CADASTRO.gerencia_regional cgr ON (cl.greg_id=cgr.greg_id)";
			
			if(numeroSorteio!=null && !numeroSorteio.trim().equals("")){
				scriptSql+=" WHERE ps.prso_nnsorteio = :numeroSorteio" +
						   " ORDER BY ps.prso_nnsorteio,ps.prso_nnordemsorteio,imps.imps_nnordemsorteio";
				
				retorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
						.addScalar("numeroOrdemSorteio", Hibernate.INTEGER)
						.addScalar("descricaoPremioSorteado", Hibernate.STRING)
						.addScalar("numeroOrdemSorteioPremio", Hibernate.INTEGER)
						.addScalar("numeroGeradoSorteio", Hibernate.INTEGER)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("nomeGerenciaRegional", Hibernate.STRING)
						.addScalar("nomeLocalidade", Hibernate.STRING)
						.addScalar("nomeCliente", Hibernate.STRING)
						.addScalar("cpf", Hibernate.STRING)
						.addScalar("cnpj", Hibernate.STRING)
						.setInteger("numeroSorteio", Integer.valueOf(numeroSorteio))
						.list();
			}else{
				scriptSql+=" ORDER BY ps.prso_nnsorteio,ps.prso_nnordemsorteio,imps.imps_nnordemsorteio";
				retorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
						.addScalar("numeroOrdemSorteio", Hibernate.INTEGER)
						.addScalar("descricaoPremioSorteado", Hibernate.STRING)
						.addScalar("numeroOrdemSorteioPremio", Hibernate.INTEGER)
						.addScalar("numeroGeradoSorteio", Hibernate.INTEGER)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("nomeGerenciaRegional", Hibernate.STRING)
						.addScalar("nomeLocalidade", Hibernate.STRING)
						.addScalar("nomeCliente", Hibernate.STRING)
						.addScalar("cpf", Hibernate.STRING)
						.addScalar("cnpj", Hibernate.STRING)
						.list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Date obterDataSorteioFiqueLegal2013() 
			throws ErroRepositorioException {
		
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "SELECT MAX(imps_dtsorteio) AS dataSorteio "
					+ " FROM cadastro.imovel_premio_sorteio ";
			
			retorno = (Date) session.createSQLQuery(consulta)
					.addScalar("dataSorteio",Hibernate.DATE)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 30/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Collection<Object[]> obterNumeroSorteioFuqueLegal2013(Integer idSorteio)
		throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String scriptSql = "";
		Collection retorno = null;
		
		try {
			
			scriptSql+="SELECT DISTINCT(ps.prso_nnsorteio) AS numeroSorteio," +
					   "cs.sort_dssorteio AS descricaoSorteio" +
					   " FROM cadastro.premio_sorteio ps" +
					   " INNER JOIN cadastro.sorteios cs ON (ps.sort_id=cs.sort_id)" +
					   " WHERE cs.sort_id = :idSorteio" +
					   " ORDER BY ps.prso_nnsorteio";
			
			retorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
					.addScalar("numeroSorteio", Hibernate.INTEGER)
					.addScalar("descricaoSorteio", Hibernate.STRING)
					.setInteger("idSorteio", idSorteio)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1321] Gerar Motivos Não Geração de Contas e Imóveis em Cobrança por Empresa
	 *  
	 * Pesquisa o Indicador de Exclusão e a Categoria Principal do imóvel.
	 *  
	 * @author Mariana Victor
	 * @date 13/09/2013
	 */
	public Object[] pesquisarIndicadorExclusaoCategoriaImovel(Integer idImovel)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Object[] retorno = null;
		
		try {
			consulta = "SELECT imov_icexclusao AS indicExclusao, " +
					"   imov_idcategoriaprincipal AS categPrincipal " +
					" FROM cadastro.imovel WHERE imov_id = :idImovel ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
					.addScalar("indicExclusao", Hibernate.SHORT)
					.addScalar("categPrincipal", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel)
					.setMaxResults(1)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	
	public Integer pesquisarNumeroOrdemSorteioFiqueLegal2013() 
			throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select coalesce(max(imps_nnordemsorteio), 0) AS numeroOrdemSorteio "
					+ " from cadastro.imovel_premio_sorteio "
					+ " where prso_id is not null ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("numeroOrdemSorteio",Hibernate.INTEGER)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	

	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * 
	 * @author Anderson Cabral
	 * @since 27/09/2013
	 */
	public Collection<Object[]> pesquisarLogradourosParaAtualizar(Integer idMunicipio) throws ErroRepositorioException{
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT arquivo.logr_id idlogradouro, " +
								"logradouro.logr_nmlogradouro nomelogradouro, " +
								"bairro.bair_id idbairro, " +
								"bairro.bair_nmbairro nomebairro, " +
								"logradouroTipo.lgtp_dslogradourotipo logTipo, " +
								"logradouroTitulo.lgtt_dslogradourotitulo logTitulo " +
								"FROM CADASTRO.imovel_end_arq_txt arquivo " +
								"INNER JOIN CADASTRO.logradouro logradouro ON arquivo.logr_id = logradouro.logr_id " +
								"INNER JOIN CADASTRO.logradouro_tipo logradouroTipo ON logradouro.lgtp_id = logradouroTipo.lgtp_id " +
								"LEFT JOIN CADASTRO.logradouro_titulo logradouroTitulo ON logradouro.lgtt_id = logradouroTitulo.lgtt_id " +
								"INNER JOIN CADASTRO.municipio municipio ON logradouro.muni_id = municipio.muni_id " +
								"LEFT JOIN CADASTRO.bairro bairro ON arquivo.bair_id = bairro.bair_id " +
								"WHERE municipio.muni_id = :idMunicipio " +
								"AND arquivo.ieat_icatualizacao = 2 " +
								"GROUP BY arquivo.logr_id, " +
								"logradouro.logr_nmlogradouro, " +
								"bairro.bair_id, " +
								"bairro.bair_nmbairro, " +
								"logradouroTipo.lgtp_dslogradourotipo, " +
								"logradouroTitulo.lgtt_dslogradourotitulo " +
								"ORDER BY logradouroTipo.lgtp_dslogradourotipo, " +
								"logradouroTitulo.lgtt_dslogradourotitulo, " +
								"logradouro.logr_nmlogradouro, " +
								"bairro.bair_nmbairro ";
			
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
								.addScalar("idlogradouro", Hibernate.INTEGER)
								.addScalar("nomelogradouro", Hibernate.STRING)
								.addScalar("idbairro", Hibernate.INTEGER)
								.addScalar("nomebairro", Hibernate.STRING)
								.addScalar("logTipo", Hibernate.STRING)
								.addScalar("logTitulo", Hibernate.STRING)
								.setInteger("idMunicipio", idMunicipio).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [SB0001] - Gerar relatório 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public ArrayList<ImovelEnderecoArquivoTexto> pesquisarTotalImoveisArquivo(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException{
		
		ArrayList<ImovelEnderecoArquivoTexto> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try{
			
			String hql = " SELECT arquivo " +
				     " FROM ImovelEnderecoArquivoTexto arquivo " +
				     " INNER JOIN FETCH arquivo.imovel imov " +
				     " INNER JOIN FETCH imov.setorComercial setor " +
				     " INNER JOIN FETCH imov.quadra quadra " +
				     " INNER JOIN FETCH imov.localidade loc " +
				     " INNER JOIN FETCH imov.logradouroBairro logbairro " +
				     " LEFT  JOIN FETCH imov.logradouroCep logCep " +
				     " LEFT  JOIN FETCH logCep.cep cepl " +
				     " INNER JOIN FETCH logbairro.bairro bairrol " +
				     " INNER JOIN FETCH logbairro.logradouro logimovel " +
				     " INNER JOIN FETCH logimovel.logradouroTipo logtipo " +
				     " LEFT  JOIN FETCH logimovel.logradouroTitulo logtitulo " +
				     " INNER JOIN FETCH arquivo.logradouro log " +
				     " INNER JOIN FETCH log.municipio muni " +
				     " INNER JOIN FETCH arquivo.bairro bair " +
				     " INNER JOIN FETCH arquivo.cep cep " +
				     " WHERE log.id = :idLogradouro " +
				     " AND bair.id = :idBairro ";
			
				retorno = (ArrayList<ImovelEnderecoArquivoTexto>) session.createQuery(hql)
							 .setInteger("idLogradouro", idLogradouro)
							 .setInteger("idBairro", idBairro).list();
		
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0004] - Pesquisar Imóveis Transferidos 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public ArrayList<Integer> pesquisarImoveisTransferidos(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException{
		
		ArrayList<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT arquivo.ieat_id idArquivo " +
						"FROM CADASTRO.imovel_end_arq_txt arquivo " +
						"INNER JOIN CADASTRO.imovel imovel ON arquivo.imov_id = imovel.imov_id " +
						"LEFT JOIN CADASTRO.logradouro_bairro logbairro ON arquivo.logr_id = logbairro.logr_id AND arquivo.bair_id = logbairro.bair_id " +
						"WHERE (imovel.lgbr_id <> logbairro.lgbr_id or logbairro.lgbr_id is null) " +
						"AND arquivo.logr_id = :idLogradouro " +
						"AND arquivo.bair_id = :idBairro "; 
			
			retorno = (ArrayList<Integer>) session.createSQLQuery(consulta)
								.addScalar("idArquivo", Hibernate.INTEGER)
								.setInteger("idLogradouro", idLogradouro)
								.setInteger("idBairro", idBairro).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * Pesquisa Imovel Endereco Arquivo Texto
	 * 
	 * @author Anderson Cabral
	 * @since 30/09/2013
	 */
	public ImovelEnderecoArquivoTexto pesquisarImovelEnderecoArquivo(Integer idArquivo) throws ErroRepositorioException{
		
		ImovelEnderecoArquivoTexto retorno = null;
		Session session = HibernateUtil.getSession();
		
		try{
			String hql = " SELECT arquivo " +
				     " FROM ImovelEnderecoArquivoTexto arquivo " +
				     " INNER JOIN FETCH arquivo.imovel imov " +
				     " INNER JOIN FETCH imov.setorComercial setor " +
				     " LEFT JOIN FETCH imov.quadra quadra " +
				     " INNER JOIN FETCH imov.localidade loc " +
				     " INNER JOIN FETCH arquivo.logradouro log " +
				     " INNER JOIN FETCH log.municipio muni " +
				     " INNER JOIN FETCH arquivo.bairro bair " +
				     " INNER JOIN FETCH arquivo.cep cep " +
				     " WHERE arquivo.id = :idArquivo ";
			
			retorno = (ImovelEnderecoArquivoTexto) session.createQuery(hql)
					 .setInteger("idArquivo", idArquivo).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * @author Anderson Cabral
	 * @since 17/10/2013
	 */
	public boolean verificarCadastradorComArquivoEmCampo(Integer idCadastrador)  throws ErroRepositorioException{
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " SELECT arquivo.txat_id idArquivo" +
							" FROM CADASTRO.atlz_cadastral_arq_txt arquivo " +
							" WHERE arquivo.leit_id = :idCadastrador " +
							" AND (arquivo.sitl_id = 2 OR arquivo.sitl_id = 3)";
			
			Integer idArquivo = (Integer) session.createSQLQuery(consulta)
					.addScalar("idArquivo",Hibernate.INTEGER)
					.setInteger("idCadastrador", idCadastrador)
					.setMaxResults(1).uniqueResult();
			
			if(idArquivo != null && idArquivo.intValue() > 0){
				retorno = true;
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * Metodo responsavel que verifica se o imovel contem todos os itens obrigatorios.
	 * 
	 * @param codigoImovelAtualizacaoCadastra
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIntegridadeImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastra)throws ErroRepositorioException{
		
		Integer quantidade = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try{
			consulta += "select count(imac.imac_id) quantidade "
					 +	"from CADASTRO.imovel_atlz_cadastral imac "
					 +  "inner join cadastro.imovel_subcatg_atlz_cad isac on isac.imac_id = imac.imac_id "
					 +  "inner join cadastro.imovel_ocorr_atlz_cad ioac on ioac.imac_id = imac.imac_id "
					 +	"where imac.imac_id = :codigo ";
			
			quantidade = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("codigo", idImovelAtualizacaoCadastra)
					.uniqueResult();
			
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return quantidade;
	}
	
	
	/**
	 *  Metodo responsavel por remover todos os dados do imovel atualizacao cadastral, caso ocorra erro no carregamento
	 * @param codigoImovel
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		PreparedStatement st = null;
		try {
			Connection jdbcCon = session.connection();

			//cliente fone
			String deleteFone =  " DELETE FROM CADASTRO.cliente_fone_atlz_cad "
					+ "where clac_id in (select clac_id FROM CADASTRO.cliente_atlz_cadastral "
										+ "where imac_id in ("+idImovelAtualizacaoCadastral+" ) )";
			st = jdbcCon.prepareStatement(deleteFone);
			st.executeUpdate();
			
			st = null;	
			//cliente		
			String deleteCliente = "DELETE FROM CADASTRO.cliente_atlz_cadastral "
					+ " where imac_id in (select imac_id from cadastro.imovel_atlz_cadastral where imac_id = "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteCliente);
			st.executeUpdate();
			
			st = null;
			//subcategoria
			String deleteCategoria = "DELETE FROM CADASTRO.imovel_subcatg_atlz_cad "
					+ " where imac_id in ( "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteCategoria);
			st.executeUpdate();
			
			st = null;
			
			//hidrometro		
			String deleteHidrometro = "DELETE FROM MICROMEDICAO.hidrom_inst_hist_atl_cad "
					+ "where  imac_id in ("+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteHidrometro);
			st.executeUpdate();
			
			st = null;

			//ocorrencia
			String deleteOcorrencia = "DELETE FROM CADASTRO.imovel_ocorr_atlz_cad "
					+ "where imac_id in ( "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteOcorrencia);
			st.executeUpdate();
			
			st = null;
			
			//foto
			String deleteFoto = "DELETE FROM CADASTRO.imovel_foto_atlz_cad "
					+ "where imac_id in ( "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteFoto);
			st.executeUpdate();
			
			st = null;

			//imovel
			String deleteImovel = "DELETE FROM CADASTRO.imovel_atlz_cadastral "
					+ "WHERE imac_id = "+idImovelAtualizacaoCadastral;
			st = jdbcCon.prepareStatement(deleteImovel);
			st.executeUpdate();
			
			st = null;
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Metodo responsavel por verificar se o setor comercial e a quadra existe no GSAN.
	 * Atualizacao cadastral.
	 * @author Arthur Carvalho
	 */
	public boolean verificarInscricaoInformadaValida(DadosImovelPreGsanHelper helper)  throws ErroRepositorioException{
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " SELECT loca.loca_id as idLocalidade from cadastro.localidade loca " 
						+ "inner join cadastro.setor_comercial stcm on stcm.loca_id = loca.loca_id "
						+ "inner join cadastro.quadra qdra on qdra.stcm_id = stcm.stcm_id " 
						+ "where stcm.stcm_cdsetorcomercial =:codigoSetor "
						+ "and qdra.qdra_nnquadra =:numeroQuadra "
						+ "and loca.loca_id = :idlocalidade";
			
			Integer idArquivo = (Integer) session.createSQLQuery(consulta)
					.addScalar("idLocalidade",Hibernate.INTEGER)
					.setInteger("codigoSetor", Integer.valueOf(helper.getCodigoSetorComercial()))
					.setInteger("numeroQuadra", Integer.valueOf(helper.getNumeroQuadra()))
					.setInteger("idlocalidade", Integer.valueOf(helper.getIdLocalidade()))
					.setMaxResults(1).uniqueResult();
			
			if(idArquivo != null && idArquivo.intValue() > 0){
				retorno = true;
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1442] Inserir Novos Logradouros Atualização Cadastral
	 * IT010 - Pesquisar Imóveis associados ao Logradouro
	 * 
	 * @author Anderson Cabral
	 * @since 27/12/2013
	 * 
	 * @param idLogradouro
	 * @return ArrayList<Integer>
	 * @throws ErroRepositorioException
	 */
	public ArrayList<Integer> pesquisarImovelAtualizacaoCadastralPorLogradouro(Integer idLogradouro ) throws ErroRepositorioException {
	
		ArrayList<Integer> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
			
			consulta =  " SELECT imov_id imoveID " +
						" FROM CADASTRO.imovel_atlz_cadastral imov " +
						" INNER JOIN CADASTRO.logradouro_atl_cadastral logra ON logra.loac_cdlogradouro = imov.logr_id " +
						" WHERE logra.loac_id = :idLogradouro "; 
			
			colecao = (ArrayList<Integer>) session.createSQLQuery(consulta)
										.addScalar("imoveID", Hibernate.INTEGER)
										.setInteger("idLogradouro", idLogradouro)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	
	}
	
	
	
	/**
	 * [UC1583] Relatório de Análise das Inconsistências da Atualização Cadastral
	 * 
	 * @author Hugo Azevedo
	 * @since 20/02/2014
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarDadosAnaliseInconsistenciasAtualizacaoCadastral(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta = 	
					" SELECT "+
					"  CASE "+
					"    WHEN ra.REAT_CDOPCAOALTERACAO = 1 "+
					"     THEN 'Aprovado' "+
					"    WHEN ra.REAT_CDOPCAOALTERACAO = 2 "+
					"    THEN 'Aceito' "+
					"     WHEN ra.REAT_CDOPCAOALTERACAO = 3 "+
					"     THEN 'Reprovado' "+
					"   END                   AS tipo_atualizacao, "+
					"  loca_nmlocalidade      AS localidade, "+
					"  usua.usur_nmusuario    AS analista, "+
					"  IMAC_CDSETORCOMERCIAL  AS setor, "+
					"  IMAC_NNQUADRA 		  AS quadra, "+
					"  ra.imov_id             AS imovel, "+
					"  usuc.usur_nmusuario    AS cadastrador, "+
					"  ATAC_NMATRIBUTO        AS dado_inconsistente, "+
					"  matc_dsmensagem        AS inconsistencia "+
					" FROM CADASTRO.retorno_atlz_cadastral ra "+
					" INNER JOIN cadastro.imovel_atlz_cadastral imac ON imac.imac_id = ra.imac_id "+
					" INNER JOIN cadastro.mensagem_atlz_cadastral ma ON ra.matc_id = ma.matc_id "+
					" INNER JOIN cadastro.atributo_atlz_cadastral aa ON aa.atac_id = ra.atac_id "+
					" INNER JOIN seguranca.usuario usua ON usua.usur_id = ra.usur_id "+
					" INNER JOIN seguranca.usuario usuc ON usuc.usur_nmlogin = imac.imac_nmlogin "+
					" INNER JOIN cadastro.localidade loc ON loc.loca_id = imac.loca_id "+
					" WHERE ra.REAT_CDOPCAOALTERACAO IS NOT NULL "+
					" AND imac.empr_id                  = :idEmpresa ";			  
					  
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdLocalidadeInicial() != null && helper.getIdLocalidadeFinal() != null){
				consulta += "and imac.loca_id >= :idLocalidadeInicial AND imac.loca_id <= :idLocalidadeFinal ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				parameters.put("idLocalidadeFinal", Integer.parseInt(helper.getIdLocalidadeFinal()));
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and imac.imac_cdsetorcomercial >= :idSetorInicial AND imac.imac_cdsetorcomercial <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and imac.imac_nnquadra >= :idQuadraInicial AND imac.imac_nnquadra <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += "and usuc.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and ra.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and ra.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			} 
			
			consulta += " ORDER BY tipo_atualizacao, localidade, analista, "+
						" setor, quadra, cadastrador asc ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("tipo_atualizacao", Hibernate.STRING)   //0
					.addScalar("localidade", Hibernate.STRING) 		   //1
					.addScalar("analista", Hibernate.STRING)		   //2
					.addScalar("setor", Hibernate.INTEGER) 			   //3
					.addScalar("quadra", Hibernate.INTEGER) 		   //4
					.addScalar("imovel", Hibernate.INTEGER) 		   //5
					.addScalar("cadastrador", Hibernate.STRING) 	   //6
					.addScalar("dado_inconsistente", Hibernate.STRING) //7
					.addScalar("inconsistencia", Hibernate.STRING);    //8
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
		
		}catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Jonathan Marcos
	 * @date 10/04/2014
	 * @param idConta
	 */
	public Collection<Object []> obterCpfCnpjClienteConta(Integer idConta,Short idClienteRelacaoTipo)
			throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		
		String scriptSql = "";
		Collection retorno;
		
		try {
			
			scriptSql = "SELECT"+ 
					    " cc.clie_nncpf AS cpfResponsavel,"+
					    " cc.clie_nncnpj AS cnpjResponsavel"+
						" FROM cadastro.cliente  cc"+
						" INNER JOIN cadastro.cliente_conta ccc ON (cc.clie_id=ccc.clie_id)"+
						" WHERE ccc.cnta_id = :idConta"+
						" AND ccc.crtp_id = :relacaoTipo";
			
			retorno = session.createSQLQuery(scriptSql)
					.addScalar("cpfResponsavel", Hibernate.STRING)
					.addScalar("cnpjResponsavel", Hibernate.STRING)
					.setInteger("idConta", idConta)
					.setShort("relacaoTipo", idClienteRelacaoTipo)
					.list();
			
			
		} catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Collection<Object []> obterOcorrenciasImovelAtualizacaoCadastral(Integer imac)
			throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		
		String scriptSql = "";
		Collection retorno;
		
		try {
			
			scriptSql = "SELECT"+
					    " co.COCR_ID AS idOcorrencia,"+
						" co.COCR_DSCADASTROOCORRENCIA AS descricaoOcorrencia"+  
					    " FROM CADASTRO.CADASTRO_OCORRENCIA co"+
					    " INNER JOIN CADASTRO.IMOVEL_OCORR_ATLZ_CAD ioac ON (co.cocr_id=ioac.cocr_id)"+
					    " WHERE ioac.imac_id = :idImac";
			
			retorno = session.createSQLQuery(scriptSql)
					.addScalar("idOcorrencia", Hibernate.INTEGER)
					.addScalar("descricaoOcorrencia", Hibernate.STRING)
					.setInteger("idImac", imac)
					.list();
			
			
		} catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
}
