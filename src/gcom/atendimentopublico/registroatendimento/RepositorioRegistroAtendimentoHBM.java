/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestejao de ServiÁoss de Saneamento
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
* GSAN - Sistema Integrado de Gestejao de ServiÁoss de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara√∫jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl√°udio de Andrade Lira
* Denys Guimar√£es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab√≠ola Gomes de Ara√∫jo
* Fl√°vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J√∫nior
* Homero Sampaio Cavalcanti
* Ivan SÈrgio da Silva J√∫nior
* JosÈ Edmar de Siqueira
* JosÈ Thiago TenÛrio Lopes
* KÈrcia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M√°rcio Roberto Batista da Silva
* Maria de F√°tima Sampaio Leite
* Micaela Maria Coelho de Ara√∫jo
* Nelson Mendon√ßa de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael CorrÍia Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara√∫jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S·vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa È software livre; voc√™ pode redistribu√≠-lo e/ou
* modific√°-lo sob os termos de Licen√ßa P√∫blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers√£o 2 da
* Licen√ßa.
* Este programa È distribu√≠do na expectativa de ser √∫til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl√≠cita de
* COMERCIALIZA√á√ÉO ou de ADEQUA√á√ÉO A QUALQUER PROP√ìSITO EM
* PARTICULAR. Consulte a Licen√ßa P√∫blica Geral GNU para obter mais
* detalhes.
* voc√™ deve ter recebido uma c√≥pia da Licen√ßa P√∫blica Geral GNU
* junto com este programa; se n√£o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.MensagemSms;
import gcom.atendimentopublico.SmsTipo;
import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarGestaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper;
import gcom.atendimentopublico.registroatendimento.bean.GestaoRegistroAtendimentoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimento;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gui.atendimentopublico.registroatendimento.popprocedimentooperacionalpadrao.FiltrarArquivoProcedimentoOperacionalPadraoHelper;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.GeradorSqlRelatorio;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.math.BigDecimal;
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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

public class RepositorioRegistroAtendimentoHBM implements
		IRepositorioRegistroAtendimento {

	private static IRepositorioRegistroAtendimento instancia;

	/**
	 * Construtor da classe RepositorioAcessoHBM
	 */

	protected RepositorioRegistroAtendimentoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	 
	public static IRepositorioRegistroAtendimento getInstancia() {
			
			String dialect = HibernateUtil.getDialect();
			
			if (dialect.toUpperCase().contains("ORACLE")){
				if (instancia == null) {
					instancia = new RepositorioRegistroAtendimentoHBM();
				}
			} else {
				if (instancia == null) {
					instancia = new RepositorioRegistroAtendimentoPostgresHBM();
				}
			}

			return instancia;
		}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o ImÛvel com a mesma
	 * especificaÁ„o (existe acorrÍcia na tabela REGISTRO_ATENDIMENTO com
	 * IMOV_ID=matÌcula do ImÛvel e STEP_ID=Id da especificaÁ„o selecionada e
	 * RGAT_CDSITUACAO=1).
	 * 
	 * [FS0020] - Verificar existÍncia de registro de atendimento para o ImÛvel
	 * com a mesma especificaÁ„o
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento verificarExistenciaRAImovelMesmaEspecificacao(
			Integer idImovel, Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException {

		RegistroAtendimento retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat "
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN FETCH rgat.imovel imov  "
					+ "INNER JOIN FETCH rgat.solicitacaoTipoEspecificacao step  "
					+ "WHERE imov.id = :idImovel AND step.id = :idSolicitacaoTipoespecificacao "
					+ "AND rgat.codigoSituacao = 1 ";

			retorno = (RegistroAtendimento) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setInteger(
							"idSolicitacaoTipoespecificacao",
							idSolicitacaoTipoEspecificacao).setMaxResults(1)
					.uniqueResult();

			/*
			 * if (retorno != null) { Hibernate.initialize(retorno.getImovel());
			 * Hibernate.initialize(retorno.getSolicitacaoTipoEspecificacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o ImÛvel com a mesma
	 * especificaÁ„o (existe acorrÍcia na tabela REGISTRO_ATENDIMENTO com
	 * IMOV_ID=matÌcula do ImÛvel e STEP_ID=Id da especificaÁ„o selecionada e
	 * RGAT_CDSITUACAO=1 e RA_ID<> ID da RA que esteja° sendo atualizado).
	 * 
	 * [FS0020] - Verificar existÍncia de registro de atendimento para o ImÛvel
	 * com a mesma especificaÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento verificarExistenciaRAAtualizarImovelMesmaEspecificacao(
			Integer idImovel, Integer idRA,
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException {

		RegistroAtendimento retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat "
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN FETCH rgat.imovel imov  "
					+ "INNER JOIN FETCH rgat.solicitacaoTipoEspecificacao step  "
					+ "WHERE imov.id = :idImovel AND step.id = :idSolicitacaoTipoespecificacao "
					+ "AND rgat.codigoSituacao = 1 AND rgat.id <> :idRA";

			retorno = (RegistroAtendimento) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setInteger(
							"idSolicitacaoTipoespecificacao",
							idSolicitacaoTipoEspecificacao).setInteger("idRA",
							idRA).setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) { Hibernate.initialize(retorno.getImovel());
			 * Hibernate.initialize(retorno.getSolicitacaoTipoEspecificacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0424] Consultar Registro Atendimento
	 * 
	 * Retorno o Tramite mais atual a partir
	 * 
	 * @author Rafael Pinto
	 * @date 10/08/2006
	 * 
	 * @param idRA
	 * @return Tramite
	 * @throws ErroRepositorioException
	 */
	public Tramite recuperarTramiteMaisAtualPorRA(Integer idRA)
			throws ErroRepositorioException {

		Tramite retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			DetachedCriteria maxData = DetachedCriteria.forClass(Tramite.class)
					.setProjection(Property.forName("dataTramite").max()).add(
							Expression.eq("registroAtendimento.id", idRA));

			Criteria criteria = session.createCriteria(Tramite.class);
			criteria.setFetchMode("unidadeOrganizacionalDestino",
					FetchMode.JOIN);
			criteria
					.setFetchMode("unidadeOrganizacionalOrigem", FetchMode.JOIN);
			criteria.setFetchMode("usuarioResponsavel", FetchMode.JOIN);
			criteria.setFetchMode("usuarioRegistro", FetchMode.JOIN);
			criteria.add(Property.forName("dataTramite").eq(maxData));
			criteria.add(Expression.eq("registroAtendimento.id", idRA));
			criteria.setMaxResults(1);

			retorno = (Tramite) criteria.uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getUnidadeOrganizacionalDestino());
			 * Hibernate.initialize(retorno.getUnidadeOrganizacionalOrigem());
			 * Hibernate.initialize(retorno.getUsuarioResponsavel()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programa√ßao de Ordens de ServiÁos
	 * 
	 * @author Rafael Pinto
	 * @date 06/09/2006
	 */
	public Tramite recuperarTramiteMaisAtualPorUnidadeDestino(Integer idUnidade)
			throws ErroRepositorioException {

		Tramite retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			DetachedCriteria maxData = DetachedCriteria.forClass(Tramite.class)
					.setProjection(Property.forName("dataTramite").max()).add(
							Expression.eq("unidadeOrganizacionalDestino.id",
									idUnidade));

			Criteria criteria = session.createCriteria(Tramite.class);
			criteria.add(Property.forName("dataTramite").eq(maxData));
			criteria.add(Expression.eq("unidadeOrganizacionalDestino.id",
					idUnidade));
			criteria.setFetchMode("registroAtendimento", FetchMode.JOIN);
			criteria.setMaxResults(1);

			retorno = (Tramite) criteria.uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getRegistroAtendimento()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o ImÛvel (existe
	 * acorrÍcia na tabela REGISTRO_ATENDIMENTO com IMOV_ID=matÌcula do ImÛvel
	 * e RGAT_CDSITUACAO=1)
	 * 
	 * [SB0021] Verifica existÍncia de Registro de Atendimento Pendente para o
	 * ImÛvel
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel
	 * @return quantidadeRA
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaRAPendenteImovel(Integer idImovel)
			throws ErroRepositorioException {

		Integer retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT COUNT(*) " + "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.imovel imov  "
					+ "WHERE imov.id = :idImovel AND rgat.codigoSituacao = 1 ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Definir a unidade destino a partir da localidade informada/selecionada
	 * (UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com
	 * UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da tabela LOCALIDADE_SOLIC_TIPO_GRUPO
	 * com LOCA_ID=Id da Localidade e SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO
	 * com SOTP_ID=Id do Tipo de SolicitaÁıes selecionado).
	 * 
	 * [SB0005] Define Unidade Destino da Localidade
	 * 
	 * @author Raphael Rossiter
	 * @date 04/08/2006
	 * 
	 * @param idLocalidade,
	 *            idSolicitacaoTipo
	 * @return UnidadeDestino
	 * @throws ErroRepositorioException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoLocalidade(
			Integer idLocalidade, Integer idSolicitacaoTipo)
			throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT lstg.unidadeOrganizacional "
					+ "FROM LocalidadeSolicTipoGrupo lstg, SolicitacaoTipo sotp "
					+ "INNER JOIN lstg.localidade loca  "
					+ "INNER JOIN lstg.unidadeOrganizacional unid  "
					+ "INNER JOIN lstg.solicitacaoTipoGrupo sotg "
					+ "WHERE loca.id = :idLocalidade AND sotp.solicitacaoTipoGrupo.id = sotg.id "
					+ "AND sotp.id = :idSolicitacaoTipo AND unid.indicadorTramite = 1 ";

			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("idLocalidade", idLocalidade).setInteger(
							"idSolicitacaoTipo", idSolicitacaoTipo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento abertos para a unidade de atendimento
	 * informada (existe acorrÍcia na tabela REGISTRO_ATENDIMENTO com
	 * REGISTRO_ATENDIMENTO_UNIDADE=Id da Unidade de Atendimento e ATTP_ID=1 -
	 * ABRIR/REGISTRAR)
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<RegistroAtendimento>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaRAPorUnidadeAtendimento(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat.id "
					+ "FROM RegistroAtendimentoUnidade rau "
					+ "INNER JOIN rau.atendimentoRelacaoTipo art  "
					+ "INNER JOIN rau.registroAtendimento rgat  "
					+ "INNER JOIN rau.unidadeOrganizacional unid  "
					+ "WHERE unid.id = :idUnidade AND art.id = :idAtendimentoTipo ";

			retornoConsulta = session.createQuery(consulta).setInteger(
					"idUnidade", unidadeOrganizacional.getId())
					.setInteger("idAtendimentoTipo",
							AtendimentoRelacaoTipo.ABRIR_REGISTRAR).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento que esteja£o na unidade atual informada
	 * (existe acorrÍcia na tabela REGISTRO_ATENDIMENTO com TRAMITE=Id da
	 * Unidade Atual e maior TRAM_TMTRAMITE)
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<RegistroAtendimento>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaRAPorUnidadeAtual(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ErroRepositorioException {

		Collection<Integer> retorno = null;
		Collection<Object[]> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat.id, max(tram.dataTramite) "
					+ "FROM Tramite tram "
					+ "INNER JOIN tram.registroAtendimento rgat  "
					+ "INNER JOIN tram.unidadeOrganizacionalDestino unid  "
					+ "WHERE unid.id = :idUnidade " + "GROUP BY rgat.id";

			retornoConsulta = session.createQuery(consulta).setInteger(
					"idUnidade", unidadeOrganizacional.getId()).list();

			if (retornoConsulta.size() > 0) {
				retorno = new ArrayList();
				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {
					Object[] element = (Object[]) iter.next();
					retorno.add((Integer) element[0]);
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
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [SB004] Selecionar Registro de Atendimento por Munic√≠pio [SB005]
	 * Selecionar Registro de Atendimento por Bairro [SB006] Selecionar Registro
	 * de Atendimento por Logradouro
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return Collection<RegistroAtendimento>
	 * @throws ErroRepositorioException
	 */
	public Collection<RegistroAtendimento> filtrarRA(
			FiltrarRegistroAtendimentoHelper filtroRA)
			throws ErroRepositorioException {

		Collection<RegistroAtendimento> retorno = null;
		Collection<Object[]> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		boolean finalQuery = false;
		
		try {

			if (filtroRA.getUnidadeAtual() != null
					|| filtroRA.getUnidadeSuperior() != null
					|| filtroRA.getUnidadeAnterior() != null) {
				consulta = "select * from ( SELECT ra.rgat_id as idRA, ste.step_id as idEspecificacao, "
						+ "ste.step_dssolcttipoespec as descricaoEspecificacao, "
						+ "ra.rgat_tmregistroatendimento as dataRA, ra.rgat_tmencerramento as dataEncerramentoRA, "
						+ "ra.rgat_cdsituacao as codigoSituacaoRA, loca.loca_nmlocalidade as descricaoLocalidade, "
						+ "ra.unid_idatual as unidAtual, ra.rgat_dsobservacao as observacao, ra.imov_id as idImovel, "
						+ "ra.rgat_nncoordenadanorte as numeroCoordenadaNorte, ra.rgat_nncoordenadaleste as numeroCoordenadaLeste, "
						+ "imovel_perfil.iper_id as idPerfilImovel, "
						+ "imovel_perfil.iper_dsimovelperfil as perfilImovel "
						+ "FROM atendimentopublico.registro_atendimento ra "
						+ "LEFT JOIN cadastro.localidade loca on ra.loca_id = loca.loca_id "
						+ "LEFT JOIN atendimentopublico.tramite tram on ra.rgat_id = tram.rgat_id "; 
				
				consulta = montarCondicionaisFiltrarRa(filtroRA, consulta,
						parameters, false, 
						filtroRA.getRegistroAtendimentoSolicitante() == null ? !finalQuery : finalQuery);

				if (filtroRA.getRegistroAtendimentoSolicitante() != null) {

					consulta = consulta + "UNION ALL "

							+ "SELECT ra.rgat_id as idRA, ste.step_id as idEspecificacao, "
							+ "ste.step_dssolcttipoespec as descricaoEspecificacao, "
							+ "ra.rgat_tmregistroatendimento as dataRA, ra.rgat_tmencerramento as dataEncerramentoRA, "
							+ "ra.rgat_cdsituacao as codigoSituacaoRA, loca.loca_nmlocalidade as descricaoLocalidade, "
							+ "ra.unid_idatual as unidAtual, ra.rgat_dsobservacao as observacao, ra.imov_id as idImovel, "
							+ "ra.rgat_nncoordenadanorte as numeroCoordenadaNorte, ra.rgat_nncoordenadaleste as numeroCoordenadaLeste, "
							+ "imovel_perfil.iper_id as idPerfilImovel, "
							+ "imovel_perfil.iper_dsimovelperfil as perfilImovel "
							+ "FROM atendimentopublico.registro_atendimento ra "
							+ "LEFT JOIN cadastro.localidade loca on ra.loca_id = loca.loca_id "
							+ "LEFT JOIN atendimentopublico.tramite tram on ra.rgat_id = tram.rgat_id ";
					consulta =  montarCondicionaisFiltrarRa(filtroRA, consulta,
							parameters, true, true);
				}

			} else {
				consulta = "select * from ( SELECT distinct(ra.rgat_id) as idRA, ste.step_id as idEspecificacao, "
						+ "ste.step_dssolcttipoespec as descricaoEspecificacao, "
						+ "ra.rgat_tmregistroatendimento as dataRA, ra.rgat_tmencerramento as dataEncerramentoRA, "
						+ "ra.rgat_cdsituacao as codigoSituacaoRA, loca.loca_nmlocalidade as descricaoLocalidade, "
						+ "ra.unid_idatual as unidAtual, ra.rgat_dsobservacao as observacao, ra.imov_id as idImovel, "
						+ "ra.rgat_nncoordenadanorte as numeroCoordenadaNorte, ra.rgat_nncoordenadaleste as numeroCoordenadaLeste, "
						+ "imovel_perfil.iper_id as idPerfilImovel, "
						+ "imovel_perfil.iper_dsimovelperfil as perfilImovel "
						+ "FROM atendimentopublico.registro_atendimento ra "
						+ "LEFT JOIN cadastro.localidade loca on ra.loca_id = loca.loca_id "
						+ "LEFT JOIN atendimentopublico.tramite tram on ra.rgat_id = tram.rgat_id ";
				consulta = montarCondicionaisFiltrarRa(filtroRA, consulta,parameters, false, 
						filtroRA.getRegistroAtendimentoSolicitante() == null ? !finalQuery : finalQuery);
				
				if (filtroRA.getRegistroAtendimentoSolicitante() != null) {

					consulta = consulta + "UNION ALL "
							+ "SELECT distinct(ra.rgat_id) as idRA, ste.step_id as idEspecificacao, "
							+ "ste.step_dssolcttipoespec as descricaoEspecificacao, "
							+ "ra.rgat_tmregistroatendimento as dataRA, ra.rgat_tmencerramento as dataEncerramentoRA, "
							+ "ra.rgat_cdsituacao as codigoSituacaoRA, loca.loca_nmlocalidade as descricaoLocalidade, "
							+ "ra.unid_idatual as unidAtual, ra.rgat_dsobservacao as observacao, ra.imov_id as idImovel, "
							+ "ra.rgat_nncoordenadanorte as numeroCoordenadaNorte, ra.rgat_nncoordenadaleste as numeroCoordenadaLeste, "
							+ "imovel_perfil.iper_id as idPerfilImovel, "
							+ "imovel_perfil.iper_dsimovelperfil as perfilImovel "
							+ "FROM atendimentopublico.registro_atendimento ra "
							+ "LEFT JOIN cadastro.localidade loca on ra.loca_id = loca.loca_id "
							+ "LEFT JOIN atendimentopublico.tramite tram on ra.rgat_id = tram.rgat_id ";
					consulta = montarCondicionaisFiltrarRa(filtroRA, consulta,parameters, true,
							true);
				}

			}

			

			query = session.createQuery(consulta);

			if (filtroRA.getUnidadeAtual() != null
					|| filtroRA.getUnidadeSuperior() != null) {

				query = session.createSQLQuery(consulta).addScalar("idRA",
						Hibernate.INTEGER).addScalar("idEspecificacao",
						Hibernate.INTEGER).addScalar("descricaoEspecificacao",
						Hibernate.STRING).addScalar("dataRA",
						Hibernate.TIMESTAMP).addScalar("dataEncerramentoRA",
						Hibernate.TIMESTAMP).addScalar("codigoSituacaoRA",
						Hibernate.SHORT).addScalar("descricaoLocalidade",
						Hibernate.STRING).addScalar("unidAtual", 
						Hibernate.INTEGER).addScalar("observacao",
						Hibernate.STRING).addScalar("idImovel", 
						Hibernate.INTEGER).addScalar("numeroCoordenadaNorte", 
						Hibernate.BIG_DECIMAL).addScalar("numeroCoordenadaLeste",								
						Hibernate.BIG_DECIMAL)
						.addScalar("idPerfilImovel", Hibernate.INTEGER)
						.addScalar("perfilImovel", Hibernate.STRING);
			} else {

				query = session.createSQLQuery(consulta).addScalar("idRA",
						Hibernate.INTEGER).addScalar("idEspecificacao",
						Hibernate.INTEGER).addScalar("descricaoEspecificacao",
						Hibernate.STRING).addScalar("dataRA",
						Hibernate.TIMESTAMP).addScalar("dataEncerramentoRA",
						Hibernate.TIMESTAMP).addScalar("codigoSituacaoRA",
						Hibernate.SHORT).addScalar("descricaoLocalidade",
						Hibernate.STRING).addScalar("unidAtual", 
						Hibernate.INTEGER).addScalar("observacao",
						Hibernate.STRING).addScalar("idImovel", 
						Hibernate.INTEGER).addScalar("numeroCoordenadaNorte", 
						Hibernate.BIG_DECIMAL).addScalar("numeroCoordenadaLeste",								
						Hibernate.BIG_DECIMAL)
						.addScalar("idPerfilImovel", Hibernate.INTEGER)
						.addScalar("perfilImovel", Hibernate.STRING);
				
			}

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
			if (filtroRA.getNumeroPagina().intValue() == -1) {
				retornoConsulta = query.list();
			} else {
				retornoConsulta = query.setFirstResult(
						10 * filtroRA.getNumeroPagina()).setMaxResults(10)
						.list();
			}
			
			if (retornoConsulta.size() > 0) {
				retorno = new ArrayList();
				RegistroAtendimento ra = null;
				SolicitacaoTipoEspecificacao step = null;
				Localidade loca = null;
				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {
					Object[] element = (Object[]) iter.next();
					ra = new RegistroAtendimento();
					ra.setId((Integer) element[0]);
					step = new SolicitacaoTipoEspecificacao();
					step.setId((Integer) element[1]);
					step.setDescricao((String) element[2]);
					ra.setSolicitacaoTipoEspecificacao(step);
					ra.setRegistroAtendimento((Date) element[3]);
					ra.setDataEncerramento((Date) element[4]);
					ra.setCodigoSituacao(((Short) element[5]).shortValue());
					loca = new Localidade();
					loca.setDescricao((String) element[6]);
					ra.setLocalidade(loca);
					UnidadeOrganizacional unidadeAtual = new UnidadeOrganizacional();
					unidadeAtual.setId((Integer) element[7]);
					ra.setUnidadeAtual(unidadeAtual);
					ra.setObservacao((String) element[8]);
					Imovel imovel = new Imovel();
					imovel.setId((Integer) element[9]);					
					ImovelPerfil imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId((Integer)element[12]);
					imovelPerfil.setDescricao((String)element[13]);
					imovel.setImovelPerfil(imovelPerfil);					
					ra.setImovel(imovel);
					ra.setNnCoordenadaNorte((BigDecimal) element[10]);
					ra.setNnCoordenadaLeste((BigDecimal) element[11]);
					retorno.add(ra);
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
	 * @param filtroRA
	 * @param consulta
	 * @param parameters
	 * @return
	 */
	private String montarCondicionaisFiltrarRa(
			FiltrarRegistroAtendimentoHelper filtroRA, String consulta,
			Map parameters, boolean verificarNumeroProtocoloReiteracao, boolean finalQuery) {
		
		// Tipo da Solicita√ßao e especificaÁ„o
		consulta = consulta
				+ "INNER JOIN atendimentopublico.solicitacao_tipo_espec ste on ra.step_id = ste.step_id "
				+ "INNER JOIN atendimentopublico.solicitacao_tipo st on ste.sotp_id = st.sotp_id "
				+ "INNER JOIN atendimentopublico.solicitacao_tipo_grupo stg on stg.sotg_id = st.sotg_id ";
				
		if (filtroRA.getRegistroAtendimento().getImovel() != null || filtroRA.getRegistroAtendimento().getBairroArea() != null || filtroRA.getBairroId() != null || filtroRA.getMunicipioId() != null
				|| filtroRA.getLogradouroId() != null) {
			consulta = consulta
					+ "LEFT JOIN cadastro.imovel im on ra.imov_id = im.imov_id "
					+ "LEFT JOIN cadastro.logradouro_bairro lb on im.lgbr_id = lb.lgbr_id "
					+ "LEFT JOIN cadastro.logradouro lo on lb.logr_id = lo.logr_id "
					+ "LEFT JOIN cadastro.bairro ba on lb.bair_id = ba.bair_id "
					+ "LEFT JOIN cadastro.municipio mu on  ba.muni_id = mu.muni_id "
					+ "LEFT JOIN cadastro.logradouro_bairro lbra on ra.lgbr_id = lbra.lgbr_id "
					+ "LEFT JOIN cadastro.bairro bara on lbra.bair_id = bara.bair_id "
					+ "LEFT JOIN cadastro.logradouro lora on lbra.logr_id = lora.logr_id "
					+ "LEFT JOIN cadastro.municipio mura on  bara.muni_id = mura.muni_id "
					+ "LEFT JOIN cadastro.bairro_area baa on ra.brar_id = baa.brar_id "
					+ "LEFT JOIN cadastro.bairro barea on baa.bair_id = barea.bair_id "
					+ "LEFT JOIN cadastro.municipio muarea on  barea.muni_id = muarea.muni_id ";
		}

		// Registro Atendimento Unidade
		if (filtroRA.getUnidadeAtendimento() != null
				|| filtroRA.getRegistroAtendimentoUnidade() != null) {
			consulta = consulta
					+ "LEFT JOIN atendimentopublico.ra_unidade rau on ra.rgat_id = rau.rgat_id "
					+ "LEFT JOIN seguranca.usuario usuario on usuario.usur_id = rau.usur_id ";
		}
		
		// Registro Atendimento Solicitante
		if (filtroRA.getRegistroAtendimentoSolicitante() != null && !verificarNumeroProtocoloReiteracao) {
			consulta = consulta
					+ "LEFT JOIN atendimentopublico.ra_solicitante raso on ra.rgat_id = raso.rgat_id ";
				
					
		}
		
		if(filtroRA.getRegistroAtendimentoSolicitante() != null && verificarNumeroProtocoloReiteracao){
			consulta = consulta+ "LEFT JOIN atendimentopublico.ra_reiteracao rart on rart.rgat_id = ra.rgat_id ";
		}
			
		
		//Perfil Imovel
		consulta = consulta
		        + "LEFT JOIN cadastro.imovel imovel on (ra.imov_id = imovel.imov_id) "
		        + "LEFT JOIN cadastro.imovel_perfil imovel_perfil on (imovel.iper_id = imovel_perfil.iper_id) and (imovel_perfil.iper_icuso = 1) ";

		consulta = consulta
				+ "WHERE 1=1 ";

		if (filtroRA.getRegistroAtendimento().getId() != null) {
			consulta += " AND ra.rgat_id = (:idRA) ";
			parameters.put("idRA", filtroRA.getRegistroAtendimento()
					.getId());
		}
		
		if (filtroRA.getRegistroAtendimentoSolicitante() != null && !verificarNumeroProtocoloReiteracao) {
			consulta += " AND raso.raso_nnprotocoloatendimento = (:numeroProtocolo) ";
			parameters.put("numeroProtocolo", filtroRA.getRegistroAtendimentoSolicitante()
			.getNumeroProtocoloAtendimento());
		}
		
		if (filtroRA.getRegistroAtendimentoSolicitante() != null && verificarNumeroProtocoloReiteracao) {
			consulta += " AND rart.rart_nnprotocoloatendimento = (:numeroProtocolo) ";
			parameters.put("numeroProtocolo", filtroRA.getRegistroAtendimentoSolicitante()
			.getNumeroProtocoloAtendimento());
		}
		
		
		if (filtroRA.getRegistroAtendimento().getManual() != null) {
			consulta += " AND ra.rgat_idmanual = (:manual) ";
			parameters.put("manual", filtroRA.getRegistroAtendimento()
					.getManual());
		}
		// Quantidade RestriÁoes
		if (filtroRA.getQuantidadeRAReiteradasIncial() != null) {
			consulta += " AND ra.rgat_qtreiteracoes between (:qtdeRAReiteradasInicial) AND (:qtdeRAReiteradasFinal) ";
			parameters.put("qtdeRAReiteradasInicial", filtroRA.getQuantidadeRAReiteradasIncial());
			parameters.put("qtdeRAReiteradasFinal", filtroRA.getQuantidadeRAReiteradasFinal()); 
		}
		
		if (filtroRA.getColecaoAtendimentoMotivoEncerramento() != null && 
			filtroRA.getColecaoAtendimentoMotivoEncerramento().size() > 0) {
			consulta += " AND ra.amen_id IN (:colecaoAtendimentoMotivoEncerramento) ";
			parameters.put("colecaoAtendimentoMotivoEncerramento", filtroRA.getColecaoAtendimentoMotivoEncerramento());
		}
		
		if (filtroRA.getRegistroAtendimento().getImovel() != null) {
			consulta += " AND im.imov_id = (:idImovel) ";
			parameters.put("idImovel", filtroRA.getRegistroAtendimento()
					.getImovel().getId());
		}
		if (new Short(filtroRA.getRegistroAtendimento().getCodigoSituacao())
				.intValue() != RegistroAtendimento.SITUACAO_TODOS) {
			consulta += " AND ra.rgat_cdsituacao = (:idSituacao) ";
			parameters.put("idSituacao", filtroRA.getRegistroAtendimento()
					.getCodigoSituacao());
		}

		// Apenas RAs com coordenadas sem logradouro identificado
		if (filtroRA.getRegistroAtendimento().getIndicadorCoordenadaSemLogradouro() != null &&
				new Short(filtroRA.getRegistroAtendimento().getIndicadorCoordenadaSemLogradouro()).intValue() != ConstantesSistema.NAO) {
			consulta += " AND ra.rgat_iccorrdenadassemlogr = (:iccorrdenadassemlogr) ";
			parameters.put("iccorrdenadassemlogr", filtroRA.getRegistroAtendimento()
					.getIndicadorCoordenadaSemLogradouro());
		}
		
		if (filtroRA.getColecaoTipoSolicitacaoEspecificacao() != null
				&& !filtroRA.getColecaoTipoSolicitacaoEspecificacao()
						.isEmpty()) {
			consulta += " AND ste.step_id in (:idSolicitacaoTipoEspecificacao) ";
			parameters.put("idSolicitacaoTipoEspecificacao", filtroRA
					.getColecaoTipoSolicitacaoEspecificacao());
		}
		// ------------------------------------------------------

		if (filtroRA.getColecaoTipoSolicitacao() != null
				&& !filtroRA.getColecaoTipoSolicitacao().isEmpty()) {
			consulta += " AND st.sotp_id in (:idSolicitacaoTipo) ";
			parameters.put("idSolicitacaoTipo", filtroRA
					.getColecaoTipoSolicitacao());
		}
		// ------------------------------------------------------
		if (filtroRA.getColecaoTipoSolicitacaoGrupo() != null 
				&& !filtroRA.getColecaoTipoSolicitacaoGrupo().isEmpty()) {
			consulta += " AND stg.sotg_id in (:idSolicitacaoGrupo) ";
			parameters.put("idSolicitacaoGrupo", filtroRA
					.getColecaoTipoSolicitacaoGrupo());
		}
		
		//Perfil do imovel
		if (filtroRA.getColecaoPerfilImovel() != null
				&& !filtroRA.getColecaoPerfilImovel().isEmpty()) {
			consulta += " AND imovel_perfil.iper_id in (:idPerfilImovel) ";
			parameters.put("idPerfilImovel", filtroRA.getColecaoPerfilImovel());
		}

		// ------------------------------------------------------

		if (filtroRA.getDataAtendimentoInicial() != null
				&& filtroRA.getDataAtendimentoFinal() != null) {
			consulta += " AND ra.rgat_tmregistroatendimento BETWEEN (:dataInicial) AND (:dataFinal) ";
			parameters.put("dataInicial", filtroRA
					.getDataAtendimentoInicial());
			parameters.put("dataFinal", filtroRA.getDataAtendimentoFinal());
		}
		if (filtroRA.getDataEncerramentoInicial() != null
				&& filtroRA.getDataEncerramentoFinal() != null) {
			consulta += " AND ra.rgat_tmencerramento BETWEEN (:dataInicial) AND (:dataFinal) ";
			parameters.put("dataInicial", filtroRA
					.getDataEncerramentoInicial());
			parameters
					.put("dataFinal", filtroRA.getDataEncerramentoFinal());
		}
		
		//---------------------------------------
		//colocado por flavio a pedido de Cl√°udio lira - 17/03/2008
		if (filtroRA.getDataTramitacaoInicial() != null
				&& filtroRA.getDataTramitacaoFinal() != null) {
			consulta += " AND tram.tram_tmtramite BETWEEN (:dataInicial) AND (:dataFinal) ";
			
			parameters.put("dataInicial", filtroRA
					.getDataTramitacaoInicial());
			parameters
					.put("dataFinal", filtroRA.getDataTramitacaoFinal());
			
			consulta += " AND (tram.unid_idorigem <> tram.unid_iddestino)";
		}
		//---------------------------------------
		
		// [SB004] Selecionar Registro de Atendimento por Munic√≠pio
		if (filtroRA.getMunicipioId() != null
				&& !filtroRA.getMunicipioId().equals("")) {
			// 1.3 Munic√≠pio por Bairro √Årea
			consulta += " AND (muarea.muni_id = :municipioId ";
			// 1.2 Munic√≠pio por ImÛvel
			consulta += " OR mu.muni_id = :municipioId ";
			// 1.1 Munic√≠pio por Logradouro Bairro
			consulta += " OR mura.muni_id = :municipioId) ";
			parameters.put("municipioId", new Integer(filtroRA
					.getMunicipioId()));
		}

		// [SB005] Selecionar Registro de Atendimento por Bairro
		if (filtroRA.getBairroId() != null
				&& !filtroRA.getBairroId().equals("")) {
			// 1.3 Bairro por Bairro √Årea
			consulta += " AND (barea.bair_id = :bairroId ";
			// 1.2 Bairro por ImÛvel
			consulta += " OR ba.bair_id = :bairroId ";
			// 1.1 Bairro por Logradouro Bairro
			consulta += " OR bara.bair_id = :bairroId) ";
			parameters.put("bairroId", new Integer(filtroRA.getBairroId()));

			if (filtroRA.getRegistroAtendimento().getBairroArea() != null) {
				if (filtroRA.getRegistroAtendimento().getBairroArea()
						.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					consulta += " AND baa.brar_id = :bairroAreaId ";
					parameters.put("bairroAreaId", filtroRA
							.getRegistroAtendimento().getBairroArea()
							.getId());
				}
			}
		}
		// Caso venha informado apenas o codigo do bairro
		else if (filtroRA.getBairroCodigo() != null
				&& !filtroRA.getBairroCodigo().equals("")) {

			// 1.3 Bairro por Bairro √Årea
			consulta += " AND (barea.bair_cdbairro = :bairroCodigo ";
			// 1.2 Bairro por ImÛvel
			consulta += " OR ba.bair_cdbairro = :bairroCodigo ";
			// 1.1 Bairro por Logradouro Bairro
			consulta += " OR bara.bair_cdbairro = :bairroCodigo) ";

			parameters.put("bairroCodigo", new Integer(filtroRA
					.getBairroCodigo()));

			if (filtroRA.getRegistroAtendimento().getBairroArea() != null) {
				if (filtroRA.getRegistroAtendimento().getBairroArea()
						.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					consulta += " AND baa.brar_id = :bairroAreaId ";
					parameters.put("bairroAreaId", filtroRA
							.getRegistroAtendimento().getBairroArea()
							.getId());
				}
			}
		}

		// [SB006] Selecionar Registro de Atendimento por Logradouro
		if (filtroRA.getLogradouroId() != null
				&& !filtroRA.getLogradouroId().equals("")) {
			// 1.2 Logradouro por ImÛvel
			consulta += " AND (lo.logr_id = :logradouroId ";
			// 1.1 Logradouro por Logradouro Bairro
			consulta += " OR lora.logr_id = :logradouroId) ";
			parameters.put("logradouroId", new Integer(filtroRA
					.getLogradouroId()));
		}

		// Ra das unidades filtradas (atendimento, atual e superior)
		
		//****************************************************
		// Autor: Ivan Sergio
		// Data: 24/09/2009
		// CRC2790
		//****************************************************
		if (filtroRA.getColecaoRAPorUnidades() != null && !filtroRA.getColecaoRAPorUnidades().isEmpty()) {
			consulta += "AND ra.rgat_id in (:listaIdRA) ";
			parameters.put("listaIdRA", filtroRA.getColecaoRAPorUnidades());
		}
		//****************************************************

		// UnidadeAtendimento
		if (filtroRA.getUnidadeAtendimento() != null) {

			consulta += " AND rau.unid_id = (:idUnidadeAtendimento) ";
			consulta += " AND rau.attp_id = (:idAtendimentoTipo) ";
			parameters.put("idUnidadeAtendimento", filtroRA
					.getUnidadeAtendimento().getId());
			parameters.put("idAtendimentoTipo",
					AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		}

		// RegistroAtendimentoUnidade (Usu·rio)
		if (filtroRA.getRegistroAtendimentoUnidade() != null) {

			consulta += " AND usuario.usur_nmlogin = (:loginUsuario) ";
			parameters.put("loginUsuario", filtroRA
					.getRegistroAtendimentoUnidade().getUsuario()
					.getLogin());
		}

		// UnidadeAtual
		if (filtroRA.getUnidadeAtual() != null) {

//				consulta += " AND tram.unid_iddestino = (:idUnidadeAtual) "
//						+ " AND tram.tram_tmtramite in (select max(tr.tram_tmtramite) from atendimentopublico.tramite tr  "
//						+ " where tr.rgat_id = ra.rgat_id)";
			
			// inclus√£o da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
			// Vivianne Sousa 10/06/2008 analista:F√°tima Sampaio
			consulta += " AND ra.unid_idatual = (:idUnidadeAtual) ";

			parameters.put("idUnidadeAtual", filtroRA.getUnidadeAtual()
					.getId());
		}

		
		//UnidadeAnterior
		if (filtroRA.getUnidadeAnterior() != null) {

			consulta += " AND tram.unid_idorigem = (:idUnidadeAnterior) "
					+ " AND tram.tram_tmtramite in (select max(tr.tram_tmtramite) from atendimentopublico.tramite tr  "
					+ " where tr.rgat_id = ra.rgat_id)";

			parameters.put("idUnidadeAnterior", filtroRA.getUnidadeAnterior().getId());
		}
		
		
		// Quanto a pesquisa eh por unidade superior o controlador popula essa colecao
		if (filtroRA.getCollectionIdsUnidadeAtual() != null) {
			consulta += " AND ra.unid_idatual IN (:collectionIdsUnidadeAtual) ";
			parameters.put("collectionIdsUnidadeAtual", filtroRA.getCollectionIdsUnidadeAtual());
		}
		
		
		if(finalQuery){
			consulta = consulta + " )  T ";
	
			if (filtroRA.getUnidadeAtual() != null
					|| filtroRA.getUnidadeSuperior() != null) {
	
				/*consulta += " GROUP BY ra.unid_idatual, ste.step_id, ste.step_dssolcttipoespec,"
					     +" ra.rgat_tmregistroatendimento, ra.rgat_tmencerramento," 
					    +" ra.rgat_cdsituacao, ra.rgat_id, loca.loca_nmlocalidade, ra.rgat_dsobservacao,"
					    +"ra.imov_id, ra.rgat_nncoordenadanorte, ra.rgat_nncoordenadaleste, "
					    + "imovel_perfil.iper_id, imovel_perfil.iper_dsimovelperfil ";*/
				
				consulta += " GROUP BY unidAtual, idEspecificacao, descricaoEspecificacao,"
				     +" dataRA, dataEncerramentoRA," 
				    +" codigoSituacaoRA, idRA , descricaoLocalidade, observacao,"
				    +"idImovel, numeroCoordenadaNorte, numeroCoordenadaLeste, "
				    + "idPerfilImovel, perfilImovel ";
				
			}
	
			/*consulta += " ORDER BY  ra.unid_idatual, ste.step_id, ra.rgat_tmregistroatendimento,"
				     + " ra.rgat_tmencerramento, ra.rgat_id ";*/
			
			consulta += " ORDER BY   unidAtual, idEspecificacao, dataRA, dataEncerramentoRA , idRA "; 
		}
		return consulta;
	}

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [SB004] Selecionar Registro de Atendimento por Munic√≠pio [SB005]
	 * Selecionar Registro de Atendimento por Bairro [SB006] Selecionar Registro
	 * de Atendimento por Logradouro
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return Collection<RegistroAtendimento>
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarRATamanho(FiltrarRegistroAtendimentoHelper filtroRA)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {

			if (filtroRA.getDataTramitacaoInicial() != null || filtroRA.getUnidadeAnterior() != null) {
				consulta = "SELECT count(distinct ra.rgat_id) as tamanho "
						+ "FROM atendimentopublico.registro_atendimento ra "
						+ "LEFT JOIN atendimentopublico.tramite tram on ra.rgat_id = tram.rgat_id ";
				
				consulta = montarCondicionaisFiltrarRATamanho(filtroRA, consulta,
						parameters, false);
				
				if(filtroRA.getRegistroAtendimentoSolicitante() != null){
				
					consulta = " UNION "
						+ "SELECT count(distinct ra.rgat_id) as tamanho "
						+ "FROM atendimentopublico.registro_atendimento ra "
						+ "LEFT JOIN atendimentopublico.tramite tram on ra.rgat_id = tram.rgat_id ";
					
					consulta = montarCondicionaisFiltrarRATamanho(filtroRA, consulta,
						parameters, true);
				}
				
				
			} else {
				
				consulta = "SELECT count(distinct ra.rgat_id)  as tamanho "
						+ "FROM atendimentopublico.registro_atendimento ra ";
				consulta = montarCondicionaisFiltrarRATamanho(filtroRA, consulta,
						parameters, false);
				
				if(filtroRA.getRegistroAtendimentoSolicitante() != null){
					
					consulta = consulta + " UNION "
						+ "SELECT count(distinct ra.rgat_id)  as tamanho "
						+ "FROM atendimentopublico.registro_atendimento ra ";
					
					consulta = montarCondicionaisFiltrarRATamanho(filtroRA, consulta,
						parameters, true);
				}
			}

			

			query = session.createQuery(consulta);
			query = session.createSQLQuery(consulta).addScalar("tamanho",
					Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (Set) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (Collection) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			List lista = (List) query.list();
			retorno = 0;
			for (int i = 0; i < lista.size(); i++) {
				retorno = retorno + (Integer) lista.get(i); 
				
			}
			
			//retorno = (Integer)lista.get(0) + (Integer)lista.get(1);
			
			//retorno = (Integer) query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * @param filtroRA
	 * @param consulta
	 * @param parameters
	 * @return
	 */
	private String montarCondicionaisFiltrarRATamanho(
			FiltrarRegistroAtendimentoHelper filtroRA, String consulta,
			Map parameters, boolean verificarNumeroProtocoloReiteracao) {
		if (filtroRA.getRegistroAtendimento().getImovel() != null || filtroRA.getRegistroAtendimento().getBairroArea() != null || filtroRA.getBairroId() != null || filtroRA.getMunicipioId() != null
				|| filtroRA.getLogradouroId() != null) {
			consulta = consulta
					+ "LEFT JOIN cadastro.imovel im on ra.imov_id = im.imov_id "
					+ "LEFT JOIN cadastro.logradouro_bairro lb on im.lgbr_id = lb.lgbr_id "
					+ "LEFT JOIN cadastro.logradouro lo on lb.logr_id = lo.logr_id "
					+ "LEFT JOIN cadastro.bairro ba on lb.bair_id = ba.bair_id "
					+ "LEFT JOIN cadastro.municipio mu on  ba.muni_id = mu.muni_id "
					+ "LEFT JOIN cadastro.logradouro_bairro lbra on ra.lgbr_id = lbra.lgbr_id "
					+ "LEFT JOIN cadastro.bairro bara on lbra.bair_id = bara.bair_id "
					+ "LEFT JOIN cadastro.logradouro lora on lbra.logr_id = lora.logr_id "
					+ "LEFT JOIN cadastro.municipio mura on  bara.muni_id = mura.muni_id "
					+ "LEFT JOIN cadastro.bairro_area baa on ra.brar_id = baa.brar_id "
					+ "LEFT JOIN cadastro.bairro barea on baa.bair_id = barea.bair_id "
					+ "LEFT JOIN cadastro.municipio muarea on  barea.muni_id = muarea.muni_id ";
		}

		// Registro Atendimento Unidade
		if (filtroRA.getUnidadeAtendimento() != null
				|| filtroRA.getRegistroAtendimentoUnidade() != null) {
			consulta = consulta
					+ "LEFT JOIN atendimentopublico.ra_unidade rau on ra.rgat_id = rau.rgat_id ";
		}
		
		//Registro Atendimento Solicitante
		if (filtroRA.getRegistroAtendimentoSolicitante() != null && !verificarNumeroProtocoloReiteracao) {
			consulta = consulta
					+ "LEFT JOIN atendimentopublico.ra_solicitante raso on ra.rgat_id = raso.rgat_id ";
			
		}
		if(filtroRA.getRegistroAtendimentoSolicitante() != null && verificarNumeroProtocoloReiteracao){
			consulta = consulta 
			+  "LEFT JOIN atendimentopublico.ra_reiteracao rart on rart.rgat_id = ra.rgat_id " ;
		}

		// Solicitacao Tipo Grupo, Tipo da Solicita√ßao e especificaÁ„o
		consulta = consulta
				+ "INNER JOIN atendimentopublico.solicitacao_tipo_espec ste on ra.step_id = ste.step_id "
				+ "INNER JOIN atendimentopublico.solicitacao_tipo st on ste.sotp_id = st.sotp_id "
				+ "INNER JOIN atendimentopublico.solicitacao_tipo_grupo stg on stg.sotg_id = st.sotg_id ";
				
		//Perfil Imovel
		consulta = consulta
		        + "LEFT JOIN cadastro.imovel imovel on (ra.imov_id = imovel.imov_id) "
		        + "LEFT JOIN cadastro.imovel_perfil imovel_perfil on (imovel.iper_id = imovel_perfil.iper_id) and (imovel_perfil.iper_icuso = 1) ";
		
		consulta = consulta
		        + "WHERE 1=1 ";

		if (filtroRA.getRegistroAtendimento().getId() != null) {
			consulta += " AND ra.rgat_id = (:idRA) ";
			parameters.put("idRA", filtroRA.getRegistroAtendimento()
					.getId());
		}
		
		if (filtroRA.getRegistroAtendimentoSolicitante() != null && !verificarNumeroProtocoloReiteracao) {
			consulta += " AND raso.raso_nnprotocoloatendimento = (:numeroProtocolo)  ";
			parameters.put("numeroProtocolo", filtroRA.getRegistroAtendimentoSolicitante()
			.getNumeroProtocoloAtendimento());
		}
		if(filtroRA.getRegistroAtendimentoSolicitante() != null && verificarNumeroProtocoloReiteracao){
			consulta += " AND rart.rart_nnprotocoloatendimento = (:numeroProtocolo)  ";
			parameters.put("numeroProtocolo", filtroRA.getRegistroAtendimentoSolicitante()
			.getNumeroProtocoloAtendimento());
		}
		
		if (filtroRA.getRegistroAtendimento().getManual() != null) {
			consulta += " AND ra.rgat_idmanual = (:manual) ";
			parameters.put("manual", filtroRA.getRegistroAtendimento()
					.getManual());
		}
		
		// Quantidade RestriÁoes
		if (filtroRA.getQuantidadeRAReiteradasIncial() != null) {
			consulta += " AND ra.rgat_qtreiteracoes between (:qtdeRAReiteradasInicial) AND (:qtdeRAReiteradasFinal) ";
			parameters.put("qtdeRAReiteradasInicial", filtroRA.getQuantidadeRAReiteradasIncial());
			parameters.put("qtdeRAReiteradasFinal", filtroRA.getQuantidadeRAReiteradasFinal()); 
		}
		
		if (filtroRA.getRegistroAtendimento().getImovel() != null) {
			consulta += " AND im.imov_id = (:idImovel) ";
			parameters.put("idImovel", filtroRA.getRegistroAtendimento()
					.getImovel().getId());
		}
		if (new Short(filtroRA.getRegistroAtendimento().getCodigoSituacao())
				.intValue() != RegistroAtendimento.SITUACAO_TODOS) {
			consulta += " AND ra.rgat_cdsituacao = (:idSituacao) ";
			parameters.put("idSituacao", filtroRA.getRegistroAtendimento()
					.getCodigoSituacao());
		}

		// Apenas RAs com coordenadas sem logradouro identificado
		if (filtroRA.getRegistroAtendimento().getIndicadorCoordenadaSemLogradouro() != null && 
				new Short(filtroRA.getRegistroAtendimento().getIndicadorCoordenadaSemLogradouro())
				.intValue() != ConstantesSistema.NAO) {
			consulta += " AND ra.rgat_iccorrdenadassemlogr = (:iccorrdenadassemlogr) ";
			parameters.put("iccorrdenadassemlogr", filtroRA.getRegistroAtendimento()
					.getIndicadorCoordenadaSemLogradouro());
		}
		
		if (filtroRA.getColecaoTipoSolicitacaoEspecificacao() != null
				&& !filtroRA.getColecaoTipoSolicitacaoEspecificacao()
						.isEmpty()) {
			consulta += " AND ste.step_id in (:idSolicitacaoTipoEspecificacao) ";
			parameters.put("idSolicitacaoTipoEspecificacao", filtroRA
					.getColecaoTipoSolicitacaoEspecificacao());
		}
		// ------------------------------------------------------

		if (filtroRA.getColecaoTipoSolicitacao() != null
				&& !filtroRA.getColecaoTipoSolicitacao().isEmpty()) {
			consulta += " AND st.sotp_id in (:idSolicitacaoTipo) ";
			parameters.put("idSolicitacaoTipo", filtroRA
					.getColecaoTipoSolicitacao());
		}
		//-------------------------------------------------------
		
		if(filtroRA.getColecaoTipoSolicitacaoGrupo() != null 
				&& !filtroRA.getColecaoTipoSolicitacaoGrupo().isEmpty()) {
			consulta += " AND stg.sotg_id in (:idSolicitacaoGrupo) ";
			parameters.put("idSolicitacaoGrupo", filtroRA
					.getColecaoTipoSolicitacaoGrupo());
		}
		
		//Motivo encerramento
		if (filtroRA.getColecaoAtendimentoMotivoEncerramento() != null
				&& !filtroRA.getColecaoAtendimentoMotivoEncerramento().isEmpty()){
			consulta += "AND ra.amen_id in (:motivoEncerramento)";
			parameters.put("motivoEncerramento", filtroRA
				.getColecaoAtendimentoMotivoEncerramento());
		}
		
		
		//Perfil do imovel
		if (filtroRA.getColecaoPerfilImovel() != null
				&& !filtroRA.getColecaoPerfilImovel().isEmpty()) {
			consulta += " AND imovel_perfil.iper_id in (:idPerfilImovel) ";
			parameters.put("idPerfilImovel", filtroRA.getColecaoPerfilImovel());
		}

		// ------------------------------------------------------

		if (filtroRA.getDataAtendimentoInicial() != null
				&& filtroRA.getDataAtendimentoFinal() != null) {
			consulta += " AND ra.rgat_tmregistroatendimento BETWEEN (:dataInicial) AND (:dataFinal) ";
			parameters.put("dataInicial", filtroRA
					.getDataAtendimentoInicial());
			parameters.put("dataFinal", filtroRA.getDataAtendimentoFinal());
		}
		
		if (filtroRA.getDataEncerramentoInicial() != null
				&& filtroRA.getDataEncerramentoFinal() != null) {
			consulta += " AND ra.rgat_tmencerramento BETWEEN (:dataInicial) AND (:dataFinal) ";
			parameters.put("dataInicial", filtroRA
					.getDataEncerramentoInicial());
			parameters
					.put("dataFinal", filtroRA.getDataEncerramentoFinal());
		}
		
		if (filtroRA.getDataTramitacaoInicial() != null && 
			filtroRA.getDataTramitacaoFinal() != null) {
				
			consulta += " AND tram.tram_tmtramite BETWEEN (:dataInicial) AND (:dataFinal) ";
			
			parameters.put("dataInicial", filtroRA.getDataTramitacaoInicial());
			parameters.put("dataFinal", filtroRA.getDataTramitacaoFinal());
			
			consulta += " AND (tram.unid_idorigem <> tram.unid_iddestino)";
		}
		
		// [SB004] Selecionar Registro de Atendimento por Munic√≠pio
		if (filtroRA.getMunicipioId() != null
				&& !filtroRA.getMunicipioId().equals("")) {
			// 1.3 Munic√≠pio por Bairro √Årea
			consulta += " AND (muarea.muni_id = :municipioId ";
			// 1.2 Munic√≠pio por ImÛvel
			consulta += " OR mu.muni_id = :municipioId ";
			// 1.1 Munic√≠pio por Logradouro Bairro
			consulta += " OR mura.muni_id = :municipioId) ";
			parameters.put("municipioId", new Integer(filtroRA
					.getMunicipioId()));
		}

		// [SB005] Selecionar Registro de Atendimento por Bairro
		if (filtroRA.getBairroId() != null
				&& !filtroRA.getBairroId().equals("")) {
			// 1.3 Bairro por Bairro √Årea
			consulta += " AND (barea.bair_id = :bairroId ";
			// 1.2 Bairro por ImÛvel
			consulta += " OR ba.bair_id = :bairroId ";
			// 1.1 Bairro por Logradouro Bairro
			consulta += " OR bara.bair_id = :bairroId) ";
			parameters.put("bairroId", new Integer(filtroRA.getBairroId()));

			if (filtroRA.getRegistroAtendimento().getBairroArea() != null) {
				if (filtroRA.getRegistroAtendimento().getBairroArea()
						.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					consulta += " AND baa.brar_id = :bairroAreaId ";
					parameters.put("bairroAreaId", filtroRA
							.getRegistroAtendimento().getBairroArea()
							.getId());
				}
			}
		}
		// Caso venha informado apenas o codigo do bairro
		else if (filtroRA.getBairroCodigo() != null
				&& !filtroRA.getBairroCodigo().equals("")) {

			// 1.3 Bairro por Bairro √Årea
			consulta += " AND (barea.bair_cdbairro = :bairroCodigo ";
			// 1.2 Bairro por ImÛvel
			consulta += " OR ba.bair_cdbairro = :bairroCodigo ";
			// 1.1 Bairro por Logradouro Bairro
			consulta += " OR bara.bair_cdbairro = :bairroCodigo) ";

			parameters.put("bairroCodigo", new Integer(filtroRA
					.getBairroCodigo()));

			if (filtroRA.getRegistroAtendimento().getBairroArea() != null) {
				if (filtroRA.getRegistroAtendimento().getBairroArea()
						.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					consulta += " AND baa.brar_id = :bairroAreaId ";
					parameters.put("bairroAreaId", filtroRA
							.getRegistroAtendimento().getBairroArea()
							.getId());
				}
			}
		}

		// [SB006] Selecionar Registro de Atendimento por Logradouro
		if (filtroRA.getLogradouroId() != null
				&& !filtroRA.getLogradouroId().equals("")) {
			// 1.2 Logradouro por ImÛvel
			consulta += " AND (lo.logr_id = :logradouroId ";
			// 1.1 Logradouro por Logradouro Bairro
			consulta += " OR lora.logr_id = :logradouroId) ";
			parameters.put("logradouroId", new Integer(filtroRA
					.getLogradouroId()));
		}

		// UnidadeAtendimento
		if (filtroRA.getUnidadeAtendimento() != null) {

			consulta += " AND rau.unid_id = (:idUnidadeAtendimento) ";
			consulta += " AND rau.attp_id = (:idAtendimentoTipo) ";
			parameters.put("idUnidadeAtendimento", filtroRA
					.getUnidadeAtendimento().getId());
			parameters.put("idAtendimentoTipo",
					AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		}

		// RegistroAtendimentoUnidade (Usu·rio)
		if (filtroRA.getRegistroAtendimentoUnidade() != null) {

			consulta += " AND rau.usur_id = (:idUsuario) ";
			parameters.put("idUsuario", filtroRA
					.getRegistroAtendimentoUnidade().getUsuario().getId());
		}

		// UnidadeAtual
		if (filtroRA.getUnidadeAtual() != null) {

//				consulta += " AND tram.unid_iddestino = (:idUnidadeAtual) "
//						+ " AND tram.tram_tmtramite in (select max(tr.tram_tmtramite) from atendimentopublico.tramite tr  "
//						+ " where tr.rgat_id = ra.rgat_id)";
			
//				 inclus√£o da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
			// Vivianne Sousa 10/06/2008 analista:F√°tima Sampaio
			consulta += " AND ra.unid_idatual = (:idUnidadeAtual) ";

			parameters.put("idUnidadeAtual", filtroRA.getUnidadeAtual()
					.getId());
		}
		
		//UnidadeAnterior
		if (filtroRA.getUnidadeAnterior() != null) {

			consulta += " AND tram.unid_idorigem = (:idUnidadeAnterior) "
					+ " AND tram.tram_tmtramite in (select max(tr.tram_tmtramite) from atendimentopublico.tramite tr  "
					+ " where tr.rgat_id = ra.rgat_id)";

			parameters.put("idUnidadeAnterior", filtroRA.getUnidadeAnterior().getId());
		}
		

		// UnidadeSuperior
		if (filtroRA.getUnidadeSuperior() != null) {

//				consulta += " AND tram.unid_iddestino = (:idUnidadeSuperior) ";
			
//				 inclus√£o da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
			// Vivianne Sousa 10/06/2008 analista:F√°tima Sampaio
			consulta += " AND (ra.unid_idatual = (:idUnidadeSuperior) "
					+"OR ra.unid_idatual IN (:collectionIdsUnidadeAtual))";
			
			parameters.put("idUnidadeSuperior", filtroRA
					.getUnidadeSuperior().getId());
			
			parameters.put("collectionIdsUnidadeAtual", filtroRA.getCollectionIdsUnidadeAtual());
		}
		
		return consulta;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Definir a unidade destino a partir da Divis„o de esgoto
	 * informada/selecionada (UNID_ID e UNID_DSUNIDADE da tabela
	 * UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da tabela
	 * DIVISAO_ESGOTO com DVES_ID=Id da Divis„o selecionada)
	 * 
	 * [SB0007] Define Unidade Destino da Divis„o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idDivisaoEsgoto
	 * @return UnidadeDestino
	 * @throws ErroRepositorioException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoDivisaoEsgoto(
			Integer idDivisaoEsgoto) throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT dves.unidadeOrganizacional "
					+ "FROM DivisaoEsgoto dves "
					+ "INNER JOIN dves.unidadeOrganizacional unid  "
					+ "WHERE dves.id = :idDivisaoEsgoto AND unid.indicadorTramite = 1 ";

			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("idDivisaoEsgoto", idDivisaoEsgoto)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso o Tipo de Solicita√ßao seja relativo a √°rea de esgoto (SOTG_ICESGOTO
	 * da tabela SOLICITACAO_TIPO_GRUPO com o valor correspondente a um para
	 * SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com SOTP_ID=Id do Tipo de
	 * SolicitaÁıes selecionado). Caso a quadra esteja preenchida, obter a
	 * Divis„o de esgoto da quadra (DVES_ID e DVES_DSDIVISAOESGOTO da tabela
	 * DIVISAO_ESGOTO com DVES_ID=DVES_ID da tabela SISTEMA_ESGOTO com
	 * SESG_ID=SESG_ID da tabela BACIA com BACI_ID=BACI_ID da tabela QUADRA com
	 * QDRA_ID=Id da quadra informada/selecionada).
	 * 
	 * [SB0006] Obter Divis„o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param solicitacaoTipoRelativoAreaEsgoto,
	 *            idQuadra
	 * @return UnidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto obterDivisaoEsgoto(Integer idQuadra)
			throws ErroRepositorioException {

		DivisaoEsgoto retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT dves " + "FROM Quadra qdra "
					+ "INNER JOIN qdra.bacia baci  "
					+ "INNER JOIN baci.sistemaEsgoto sesg  "
					+ "INNER JOIN sesg.divisaoEsgoto dves "
					+ "WHERE qdra.id = :idQuadra ";

			retorno = (DivisaoEsgoto) session.createQuery(consulta).setInteger(
					"idQuadra", idQuadra).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso tenha informado a quadra e a mesma n√£o perten√ßa a Divis„o de esgoto
	 * informada (Id da Divis„o de esgoto È diferente de DVES_ID da tabela
	 * QUADRA com QDRA_ID=Id da quadra informada)
	 * 
	 * [SB0006] Obter Divis„o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idQuadra,
	 *            idDivisaoEsgoto
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoQuadra(
			Integer idQuadra, Integer idDivisaoEsgoto)
			throws ErroRepositorioException {

		DivisaoEsgoto retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT dves "
					+ "FROM Quadra qdra "
					+ "INNER JOIN qdra.bacia baci  "
					+ "INNER JOIN baci.sistemaEsgoto sesg  "
					+ "INNER JOIN sesg.divisaoEsgoto dves "
					+ "WHERE qdra.id = :idQuadra AND dves.id = :idDivisaoEsgoto ";

			retorno = (DivisaoEsgoto) session.createQuery(consulta).setInteger(
					"idQuadra", idQuadra).setInteger("idDivisaoEsgoto",
					idDivisaoEsgoto).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso tenha informado o setor comercial sem a quadra e o setor comercial
	 * n√£o perten√ßaa a Divis„o de esgoto informada (Id da Divis„o de esgoto È
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=Id do setor
	 * comercial informado).
	 * 
	 * [SB0006] Obter Divis„o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idSetorComercial,
	 *            idDivisaoEsgoto
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoSetor(
			Integer idSetorComercial, Integer idDivisaoEsgoto)
			throws ErroRepositorioException {

		DivisaoEsgoto retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT dves "
					+ "FROM Quadra qdra "
					+ "INNER JOIN qdra.setorComercial stcm "
					+ "INNER JOIN qdra.bacia baci  "
					+ "INNER JOIN baci.sistemaEsgoto sesg  "
					+ "INNER JOIN sesg.divisaoEsgoto dves "
					+ "WHERE stcm.id = :idSetorComercial AND dves.id = :idDivisaoEsgoto ";

			retorno = (DivisaoEsgoto) session.createQuery(consulta).setInteger(
					"idSetorComercial", idSetorComercial).setInteger(
					"idDivisaoEsgoto", idDivisaoEsgoto).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso tenha informado a localidade sem o setor comercial e a localidade
	 * n√£o perten√ßa a Divis„o de esgoto informada (Id da Divis„oo de esgoto È
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=STCM_ID da
	 * tabela SETOR_COMERCIAL com LOCA_ID=Id da localidade informada).
	 * 
	 * [SB0006] Obter Divis„o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param idLocalidade,
	 *            idDivisaoEsgoto
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoLocalidade(
			Integer idLocalidade, Integer idDivisaoEsgoto)
			throws ErroRepositorioException {

		DivisaoEsgoto retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT dves "
					+ "FROM Quadra qdra "
					+ "INNER JOIN qdra.setorComercial stcm "
					+ "INNER JOIN stcm.localidade loca "
					+ "INNER JOIN qdra.bacia baci  "
					+ "INNER JOIN baci.sistemaEsgoto sesg  "
					+ "INNER JOIN sesg.divisaoEsgoto dves "
					+ "WHERE loca.id = :idLocalidade AND dves.id = :idDivisaoEsgoto ";

			retorno = (DivisaoEsgoto) session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade).setInteger("idDivisaoEsgoto",
					idDivisaoEsgoto).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0399] Inserir Tipo de Solicita√£o com especificaÁıes
	 * 
	 * Verifica se o ServiÁos tipo tem como ServiÁos automatico gera√ß√£o
	 * autom√°tica.
	 * 
	 * [SF0003] Validar Tipo de ServiÁos
	 * 
	 * @author S·vio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarServicoTipoReferencia(Integer idServicoTipo)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT servTipo.id "
					+ "FROM ServicoTipo servTipo "
					+ "LEFT JOIN servTipo.servicoTipoReferencia servTipoRef "
					+ "WHERE servTipo.id =:idServicoTipo AND "
					+ "servTipoRef.id is not null "
					+ "AND servTipoRef.indicadorExistenciaOsReferencia = :indicadorExistencia ";

			retorno = (Integer) session
					.createQuery(consulta)
					.setInteger("idServicoTipo", idServicoTipo)
					.setShort(
							"indicadorExistencia",
							ServicoTipoReferencia.INDICADOR_EXISTENCIA_OS_REFERENCIA_ATIVO)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0399] Inserir Tipo de SolicitaÁıes com Especific√µes
	 * 
	 * Verificar descriÁ„o do tipo de SolicitaÁıes com especificaÁ„o e indicador
	 * uso ativo se j· inserido na base .
	 * 
	 * [SF0001] Verificar existencia da descriÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaDescricaoTipoSolicitacao(
			String descricaoTipoSolicitacao) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT solicitacaoTipo.id "
					+ "FROM SolicitacaoTipo solicitacaoTipo "
					+ "WHERE solicitacaoTipo.descricao =:descricaoTipoSolicitacao AND "
					+ "solicitacaoTipo.indicadorUso =:indicadorUso";

			retorno = (Integer) session.createQuery(consulta).setString(
					"descricaoTipoSolicitacao", descricaoTipoSolicitacao)
					.setShort("indicadorUso",
							SolicitacaoTipo.INDICADOR_USO_ATIVO).setMaxResults(
							1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0020] Verifica SituaÁ„o do ImÛvel e especificaÁ„o
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection verificarSituacaoImovelEspecificacao(
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT eisc.ligacaoAguaSituacao.id, eisc.ligacaoEsgotoSituacao.id, "
					+ "eisc.indicadorHidrometroLigacaoAgua, eisc.indicadorHidrometroPoco, "
					+ "step.descricao "
					+ "FROM SolicitacaoTipoEspecificacao step, EspecificacaoImovSitCriterio eisc "
					+ "INNER JOIN step.especificacaoImovelSituacao esim "
					+ "WHERE step.id = :idSolicitacaoTipoEspecificacao AND "
					+ "esim.id = eisc.especificacaoImovelSituacao.id ";

			retorno = session.createQuery(consulta).setInteger(
					"idSolicitacaoTipoEspecificacao",
					idSolicitacaoTipoEspecificacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0418] Obter Unidade Atual do RA
	 * 
	 * verifica a unidade atual do registro de atendimento pelo ˙ltimo tr·mite
	 * efetuado
	 * 
	 * 
	 * @author Ana Maria,Vivianne Sousa
	 * @date 04/08/2006,10/06/2008
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificaUnidadeAtualRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

//			consulta = "SELECT unde " + "FROM Tramite tram "
//					+ "INNER JOIN tram.registroAtendimento rgat "
//					+ "LEFT JOIN tram.unidadeOrganizacionalDestino unde "
//					+ "LEFT JOIN FETCH unde.unidadeTipo untp "
//					+ "LEFT JOIN FETCH unde.unidadeCentralizadora unce "
//					+ "WHERE rgat.id = :idRegistroAtendimento "
//					+ "ORDER BY tram.dataTramite desc ";

			// inclus√£o da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
			// Vivianne Sousa 10/06/2008 analista:F√°tima Sampaio
			
			consulta = "SELECT unde " 
					+ "FROM RegistroAtendimento rgat "
					+ "LEFT JOIN rgat.unidadeAtual unde "
					+ "LEFT JOIN FETCH unde.unidadeTipo untp "
					+ "LEFT JOIN FETCH unde.unidadeCentralizadora unce "
					+ "WHERE rgat.id = :idRegistroAtendimento ";
			
			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getUnidadeTipo());
			 * Hibernate.initialize(retorno.getUnidadeCentralizadora()); }
			 */
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0419] Obter Indicador de AutorizaÁ„o para ManutenÁ„o do RA
	 * 
	 * Verifica a unidade superior da unidade do RA
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idUnidade
	 * @throws ControladorException
	 */
	public Integer verificaUnidadeSuperiorUnidade(Integer idUnidade)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select unsu.id " + " from UnidadeOrganizacional unid "
					+ " inner join unid.unidadeSuperior unsu "
					+ " where unid.id = :idUnidade";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idUnidade", idUnidade).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0420] Obter descriÁ„o da SituaÁ„o do RA
	 * 
	 * verifica a situa√ßao(RGAT_CDSITUACAO) do registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 03/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Short verificaSituacaoRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select rgat.codigoSituacao "
					+ "from RegistroAtendimento rgat "
					+ "where rgat.id = :idRegistroAtendimento ";

			retorno = (Short) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0421] Obter Unidade de Atendimento do RA ou [UC0434] Obter Unidade de
	 * Encerramento do RA
	 * 
	 * Verifica a unidade de atendimento do registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 04/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificaUnidadeAtendimentoRA(
			Integer idRegistroAtendimento, Integer idAtendimentoRelacaoTipo)
			throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select raun.unidadeOrganizacional "
					+ "from RegistroAtendimentoUnidade raun "
					+ "inner join raun.registroAtendimento rgat "
					+ "inner join raun.atendimentoRelacaoTipo attp "
					+ "where rgat.id = :idRegistroAtendimento and attp.id = :idAtendimentoRelacaoTipo";

			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setInteger("idAtendimentoRelacaoTipo",
							idAtendimentoRelacaoTipo).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0422] Obter EndereÁo da acorrÍcia do RA
	 * 
	 * Verifica existÍncia do logradouro(lgbr_id) e do imovel no registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaEnderecoOcorrenciaRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select lgbr.id, imov.id "
					+ "from RegistroAtendimento rgat "
					+ "left join rgat.logradouroBairro lgbr "
					+ "left join rgat.imovel imov "
					+ "where rgat.id = :idRegistroAtendimento";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0423] Obter EndereÁo do Solicitante do RA
	 * 
	 * Verifica existÍncia do logradouro(lgbr_id) e do cliente no registro de
	 * atendimento solicitante
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaEnderecoRASolicitante(
			Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select lgbr.id, clie.id "
					+ "from RegistroAtendimentoSolicitante raso  "
					+ "left join raso.logradouroBairro lgbr "
					+ "left join raso.cliente clie "
					+ "where raso.id = :idRegistroAtendimentoSolicitante";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimentoSolicitante",
					idRegistroAtendimentoSolicitante).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica duplicidade no registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaDuplicidadeRegistroAtendimentoConsultarRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT rgatDuplicidade.id, rgatDuplicidade.codigoSituacao, "
					+ " raMotReativacao.id, raMotReativacao.descricao, "
					+ " rgatDuplicidade.registroAtendimento, rgatDuplicidade.dataPrevistaAtual "
					+ " FROM RegistroAtendimento rgat "
					+ " INNER JOIN rgat.registroAtendimentoDuplicidade rgatDuplicidade "
					+ " LEFT JOIN rgatDuplicidade.raMotivoReativacao raMotReativacao "
					+ " WHERE rgat.id = :idRegistroAtendimento ";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getRaMotivoReativacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento foi reativado
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaRegistroAtendimentoReativadoConsultarRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT rgat.id, rgat.codigoSituacao, "
					+ " raMotReativacao.id, raMotReativacao.descricao, "
					+ " rgat.registroAtendimento, rgat.dataPrevistaAtual " 
					+ " FROM RegistroAtendimento rgat "
					+ " INNER JOIN rgat.registroAtendimentoReativacao rart "
					+ " LEFT JOIN rgat.raMotivoReativacao raMotReativacao "
					+ " WHERE rart.id = :idRegistroAtendimento ";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getRaMotivoReativacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento È relativaÁıes de outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaRegistroAtendimentoReativacaoConsultarRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT raReativacao.id, raReativacao.codigoSituacao, "
					+ " raMotReativacao.id, raMotReativacao.descricao, "
					+ " raReativacao.registroAtendimento, raReativacao.dataPrevistaAtual "
					+ " FROM RegistroAtendimento rgat "
					+ " INNER JOIN rgat.registroAtendimentoReativacao raReativacao "
					+ " LEFT JOIN raReativacao.raMotivoReativacao raMotReativacao "
					+ " WHERE rgat.id = :idRegistroAtendimento ";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getRaMotivoReativacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica duplicidade no registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificaDuplicidadeRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		RegistroAtendimento retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rgat.registroAtendimentoDuplicidade "
					+ " from RegistroAtendimento rgat "
					+ " left join fetch rgat.raMotivoReativacao "
					+ " where rgat.id = :idRegistroAtendimento ";

			retorno = (RegistroAtendimento) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getRaMotivoReativacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento foi reativado
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificaRegistroAtendimentoReativado(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		RegistroAtendimento retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rgat " + " from RegistroAtendimento rgat "
					+ " inner join rgat.registroAtendimentoReativacao rart "
					+ " left join fetch rgat.raMotivoReativacao "
					+ " where rart.id = :idRegistroAtendimento ";

			retorno = (RegistroAtendimento) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getRaMotivoReativacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento È relativaÁıes de outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificaRegistroAtendimentoReativacao(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		RegistroAtendimento retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rgat.registroAtendimentoReativacao "
					+ " from RegistroAtendimento rgat "
					+ " left join fetch rgat.raMotivoReativacao"
					+ " where rgat.id = :idRegistroAtendimento ";

			retorno = (RegistroAtendimento) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getRaMotivoReativacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0445] Obter Nome do Solicitante do RA
	 * 
	 * Pesquisa o registro de atendimento solicitante
	 * 
	 * 
	 * @author Ana Maria, Raphael Rossiter
	 * @date 09/08/2006, 07/02/2008
	 * 
	 * 
	 * @param idRegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public Object[] pesquisarRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select raso.solicitante, " // 0
					+ " clie.id, " // 1
					+ " clie.nome,"// 2
					+ " unid.id," // 3
					+ " func.nome "// 4
					+ " from RegistroAtendimentoSolicitante raso "
					+ " left join raso.cliente clie "
					+ " left join raso.unidadeOrganizacional unid "
					+ " left join raso.funcionario func "
					+ " inner join raso.registroAtendimento rgat "
					+ " where rgat.id = :idRegistroAtendimentoSolicitante "
					+ " and raso.indicadorSolicitantePrincipal = :indicadorAtivo ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimentoSolicitante",
					idRegistroAtendimento)
					.setShort("indicadorAtivo", ConstantesSistema.INDICADOR_USO_ATIVO)
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
	 * [UC0419] Obter Indicador de AutorizaÁ„o para ManutenÁ„o do RA
	 * 
	 * Pesquisar unidade organizacional e o indicador da unidade de central de
	 * atendimento ao cliente
	 * 
	 * @author Ana Maria
	 * @date 09/08/2006
	 * 
	 * 
	 * @param idUsuario
	 * @throws ControladorException
	 */
	public Object[] pesquisarUnidadeOrganizacionalUsuario(Integer idUsuario)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select unid.id," // 0
					+ " unid.indicadorCentralAtendimento"// 1
					+ " from Usuario usur"
					+ " left join usur.unidadeOrganizacional unid"
					+ " where usur.id =  :idUsuario ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idUsuario", idUsuario).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar os parametros para atualizar o registro atendimento escolhido
	 * pelo Usu·rio
	 * 
	 * @author S·vio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select regAtendimento.id," // 0
					+ "regAtendimento.indicadorAtendimentoOnline,"// 1
					+ "regAtendimento.registroAtendimento,"// 2
					+ "regAtendimento.dataInicioEspera,"// 3
					+ "regAtendimento.dataFimEspera,"// 4
					+ "meioSolic.id,"// 5
					+ "solicTipo.id,"// 6
					+ "solicTipoEspecificacao.id,"// 7
					+ "regAtendimento.dataPrevistaAtual,"// 8
					+ "regAtendimento.observacao, "// 9
					+ "imov.id, "// 10
					+ "regAtendimento.pontoReferencia, "// 11
					+ "baiArea.id, "// 12
					+ "munic.id, "// 13
					+ "munic.nome, "// 14
					+ "bairr.id, "// 15
					+ "bairr.codigo, "// 16
					+ "bairr.nome, "// 17
					+ "loc.id, "// 18
					+ "loc.descricao, "// 19
					+ "setComerc.id, "// 20
					+ "setComerc.codigo, "// 21
					+ "setComerc.descricao, "// 22
					+ "quad.id, "// 23
					+ "quad.numeroQuadra, "// 24
					+ "divEsgoto.id, "// 25
					+ "locOco.id, "// 26
					+ "pavRua.id, "// 27
					+ "pavCalcada.id, "// 28
					+ "regAtendimento.descricaoLocalOcorrencia, "// 29
					+ "solicTipoEspecificacao.indicadorMatricula, "// 30
					+ "solicTipoEspecificacao.indicadorPavimentoRua, "// 31
					+ "solicTipoEspecificacao.indicadorPavimentoCalcada, "// 32
					+ "solicTipo.indicadorFaltaAgua, "// 33
					+ "regAtendimento.ultimaAlteracao, "// 34
					+ "regAtendimento.manual,  "// 35
					+ "servTipo.id, "// 36
					+ "servTipo.valor, " //37
					+ "regAtendimento.nnCoordenadaNorte, " // 38
					+ "regAtendimento.nnCoordenadaLeste, " // 39
					+ "regAtendimento.nnDiametro, " // 40
					+ "solicTipoGrupo.id, " //41
					+ "solicTipoEspecificacao.indicadorInformarPagamentoDuplicidade, " //42
					+ "solicTipoEspecificacao.indicadorInformarDocumentoPagamentoAMaior, " //43
					+ "solicTipoEspecificacao.indicadorInformarDocumentoPagamentoIndevido " //44
					+ " FROM RegistroAtendimento regAtendimento "
					+ " INNER JOIN regAtendimento.meioSolicitacao meioSolic "
					+ " INNER JOIN regAtendimento.solicitacaoTipoEspecificacao solicTipoEspecificacao "
					+ " INNER JOIN solicTipoEspecificacao.solicitacaoTipo solicTipo "
					+ " LEFT JOIN solicTipo.solicitacaoTipoGrupo solicTipoGrupo "
					+ " LEFT JOIN solicTipoEspecificacao.servicoTipo servTipo "
					+ " LEFT JOIN regAtendimento.imovel imov "
					+ " LEFT JOIN regAtendimento.bairroArea baiArea"
					+ " LEFT JOIN baiArea.bairro bairr"
					+ " LEFT JOIN bairr.municipio munic "
					+ " LEFT JOIN regAtendimento.localidade loc "
					+ " LEFT JOIN regAtendimento.setorComercial setComerc "
					+ " LEFT JOIN regAtendimento.quadra quad "
					+ " LEFT JOIN regAtendimento.divisaoEsgoto divEsgoto"
					+ " LEFT JOIN regAtendimento.localOcorrencia locOco"
					+ " LEFT JOIN regAtendimento.divisaoEsgoto divEsgoto"
					+ " LEFT JOIN regAtendimento.pavimentoRua pavRua"
					+ " LEFT JOIN regAtendimento.pavimentoCalcada pavCalcada"
					+ " WHERE regAtendimento.id =  :idRegistroAtendimento ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Verificar existencia ordem de ServiÁos para o registro atendimento
	 * pesquisado
	 * 
	 * @author S·vio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOrdemServicoParaRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select ordemServ.id "
					+ "FROM OrdemServico ordemServ "
					+ "INNER JOIN ordemServ.registroAtendimento regAtendimento "
					+ "WHERE regAtendimento.id =  :idRegistroAtendimento ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0446] Consultar tr·mites
	 * 
	 * Retorna a ColeÁıes de Tramites do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<Tramite>
	 * @throws ControladorException
	 */
	public Collection<Tramite> obterTramitesRA(Integer idRA)
			throws ErroRepositorioException {

		Collection<Tramite> retorno = null;
		Collection<Object[]> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT tram.id, " // 0
					+ "  tram.dataTramite, " // 1
					+ "  unid.id, " // 2
					+ "  unid.descricao,  " // 3
					+ "  user.id, " // 4
					+ "  user.nomeUsuario " // 5
					+ " FROM Tramite tram "
					+ " INNER JOIN tram.registroAtendimento rgat  "
					+ " INNER JOIN tram.unidadeOrganizacionalDestino unid  "
					+ " INNER JOIN tram.usuarioResponsavel user "
					+ " WHERE rgat.id = :idRA " + " ORDER BY tram.dataTramite ";

			retornoConsulta = session.createQuery(consulta).setInteger("idRA",
					idRA).list();

			if (retornoConsulta.size() > 0) {
				retorno = new ArrayList();
				Tramite tramite = null;
				UnidadeOrganizacional unidade = null;
				Usuario usuario = null;
				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {
					Object[] element = (Object[]) iter.next();
					tramite = new Tramite();
					tramite.setId((Integer) element[0]);
					tramite.setDataTramite((Date) element[1]);
					unidade = new UnidadeOrganizacional();
					unidade.setId((Integer) element[2]);
					unidade.setDescricao((String) element[3]);
					tramite.setUnidadeOrganizacionalDestino(unidade);
					usuario = new Usuario();
					usuario.setId((Integer) element[4]);
					usuario.setNomeUsuario((String) element[5]);
					tramite.setUsuarioResponsavel(usuario);
					retorno.add(tramite);
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
	 * [UC0447] Consultar RA Solicitantes
	 * 
	 * Retorna a ColeÁıes de registro de atendimento solicitantes
	 * 
	 * @author Rafael Pinto
	 * @date 14/08/2006
	 * 
	 * @param idRA
	 * @return Collection<RegistroAtendimentoSolicitante>
	 * @throws ControladorException
	 */
	public Collection<RegistroAtendimentoSolicitante> obterRASolicitante(
			Integer idRA) throws ErroRepositorioException {

		Collection<RegistroAtendimentoSolicitante> retorno = null;
		Collection<Object[]> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT RASolicitante.id," // 0
					+ " RASolicitante.pontoReferencia," // 1
					+ " RASolicitante.indicadorSolicitantePrincipal," // 2
					+ " unid.id," // 3
					+ " unid.descricao," // 4
					+ " clie.id," // 5
					+ " clie.nome," // 6
					+ " func.id," // 7
					+ " func.nome," // 8
					+ " RASolicitante.solicitante, " // 9
					+ " RASolicitante.numeroProtocoloAtendimento " // 10
					+ " FROM RegistroAtendimentoSolicitante RASolicitante"
					+ " INNER JOIN RASolicitante.registroAtendimento rgat"
					+ " LEFT JOIN RASolicitante.unidadeOrganizacional unid"
					+ " LEFT JOIN RASolicitante.cliente clie"
					+ " LEFT JOIN RASolicitante.funcionario func"
					+ " WHERE rgat.id = :idRA"
					+ " ORDER BY RASolicitante.indicadorSolicitantePrincipal,RASolicitante.id ";

			retornoConsulta = session.createQuery(consulta).setInteger("idRA",
					idRA).list();

			if (retornoConsulta.size() > 0) {

				retorno = new ArrayList();


				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();

					RegistroAtendimentoSolicitante registroAtendimentoSolicitante = null;
					UnidadeOrganizacional unidade = null;
					Cliente cliente = null;
					Funcionario funcionario = null;

					registroAtendimentoSolicitante = new RegistroAtendimentoSolicitante();
					registroAtendimentoSolicitante.setID((Integer) element[0]);
					registroAtendimentoSolicitante.setPontoReferencia((String) element[1]);
					registroAtendimentoSolicitante.setIndicadorSolicitantePrincipal((Short) element[2]);

					if (element[3] != null) {
						unidade = new UnidadeOrganizacional();
						unidade.setId((Integer) element[3]);
						unidade.setDescricao((String) element[4]);
					}

					if (element[5] != null) {
						cliente = new Cliente();
						cliente.setId((Integer) element[5]);
						cliente.setNome((String) element[6]);

					}

					if (element[7] != null) {
						funcionario = new Funcionario();
						funcionario.setId((Integer) element[7]);
						funcionario.setNome((String) element[8]);
					}

					registroAtendimentoSolicitante.setUnidadeOrganizacional(unidade);
					registroAtendimentoSolicitante.setCliente(cliente);
					registroAtendimentoSolicitante.setFuncionario(funcionario);
					registroAtendimentoSolicitante
							.setSolicitante((String) element[9]);
					
					if (element[10] != null){
						registroAtendimentoSolicitante.setNumeroProtocoloAtendimento((String) element[10]);
					}

					retorno.add(registroAtendimentoSolicitante);
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
	 * [UC0431] Consultar Ordens de ServiÁos do Registro Atendimento
	 * 
	 * Retorna a ColeÁıes de OS's do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> obterOSRA(Integer idRA)
			throws ErroRepositorioException {
		Collection<OrdemServico> retorno = null;
		Collection<Object[]> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT os.id, " // 0
					+ "  os.dataGeracao, " // 1
					+ "  st.id, " // 2
					+ "  st.descricao,  " // 3
					+ "  os.situacao, " // 4
					+ "  st.indicadorTerceirizado, " // 5
					+ "  osr.id, " // 6
					+ "  os.ultimaAlteracao " // 7
					+ " FROM OrdemServico os "
					+ " LEFT  JOIN os.osReferencia osr "
					+ " INNER JOIN os.registroAtendimento rgat  "
					+ " INNER JOIN os.servicoTipo st  "
					+ " WHERE rgat.id = :idRA " + " ORDER BY os.id ";

			retornoConsulta = session.createQuery(consulta).setInteger("idRA",
					idRA).list();

			if (retornoConsulta.size() > 0) {
				retorno = new ArrayList();
				OrdemServico os = null;
				ServicoTipo servicoTipo = null;
				OrdemServico osr = null;
				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {
					Object[] element = (Object[]) iter.next();
					os = new OrdemServico();
					os.setId((Integer) element[0]);
					os.setDataGeracao((Date) element[1]);
					servicoTipo = new ServicoTipo();
					servicoTipo.setId((Integer) element[2]);
					servicoTipo.setDescricao((String) element[3]);
					servicoTipo.setIndicadorTerceirizado((Short) element[5]);
					os.setServicoTipo(servicoTipo);
					os.setSituacao((Short) element[4]);
					if (element[6] != null) {
						osr = new OrdemServico();
						osr.setId((Integer) element[6]);
					}
					os.setOsReferencia(osr);
					os.setUltimaAlteracao((Date) element[7]);
					retorno.add(os);
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
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * verificar duplicidade do registro de atendimento e cÛdigo SituaÁ„o
	 * 
	 * [FS0012] - Verificar possibilidade de atualizaÁ„o do registro de
	 * atendimento
	 * 
	 * @author S·vio Luiz
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificarParmsRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select regAtendDuplicidade.id," // 0
					+ "regAtendimento.codigoSituacao" // 1
					+ " FROM RegistroAtendimento regAtendimento "
					+ " LEFT JOIN regAtendimento.registroAtendimentoDuplicidade regAtendDuplicidade "
					+ " WHERE regAtendimento.id =  :idRegistroAtendimento ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * verificar existencai da ordem de servico ProgramaÁıes para o RA
	 * 
	 * [FS0012] - Verificar possibilidade de atualizaÁ„o do registro de
	 * atendimento
	 * 
	 * @author S·vio Luiz
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaOrdemServicoProgramacaoRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select ordemServProg.id "
					+ "FROM OrdemServicoProgramacao ordemServProg "
					+ "LEFT JOIN ordemServProg.ordemServico ordemServ "
					+ "LEFT JOIN ordemServ.registroAtendimento regAtendimento "
					+ "WHERE regAtendimento.id =  :idRegistroAtendimento AND "
					+ "ordemServ.situacao <> :codigoSituacao AND "
					+ "ordemServProg.indicadorAtivo = :indAtivo";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento).setShort(
					"codigoSituacao", OrdemServico.SITUACAO_ENCERRADO)
					.setShort("indAtivo",
							OrdemServicoProgramacao.INDICADOR_ATIVO)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0409] Obter Indicador de existÍncia de HidrÙmetro na ligaÁ„o de √Ågua e
	 * no poÁo
	 * 
	 * Pesquisar a SituaÁ„o e o indicador de existÍncia de HidrÙmetro na ligaÁ„o
	 * de √Ågua e no poÁo
	 * 
	 * @author Ana Maria, RÛmulo AurÈlio
	 * @date 09/08/2006, 20/05/2010
	 * 
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public Object[] pesquisarHidrometroImovel(Integer idImovel)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select last.indicadorExistenciaLigacao," // 0
					+ " lest.indicadorExistenciaLigacao," // 1
					+ " hidi.id," // 2
					+ " hilg.id "// 3
					+ " from Imovel imov"
					+ " inner join imov.ligacaoAguaSituacao last"
					+ " inner join imov.ligacaoEsgotoSituacao lest"
					+ " left join imov.hidrometroInstalacaoHistorico hidi"
					+ " left join imov.ligacaoAgua lagu"
					+ " left join lagu.hidrometroInstalacaoHistorico hilg"
					+ " where imov.id = :idImovel";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar os parametros para saber como esteja° a situacao do registro de
	 * antendimento
	 * 
	 * @author S·vio Luiz
	 * @date 15/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Short pesquisarCdSituacaoRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select regAtendimento.codigoSituacao "
					+ " FROM RegistroAtendimento regAtendimento "
					+ " WHERE regAtendimento.id =  :idRegistroAtendimento ";

			retorno = (Short) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar se o ImÛvel È descritivo
	 * 
	 * @author S·vio Luiz
	 * @date 15/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelDescritivo(Integer idImovel)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logBairro.id " + " FROM Imovel imov "
					+ " INNER JOIN imov.logradouroBairro logBairro"
					+ " WHERE imov.id =  :idImovel ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0430] Gerar Ordem de ServiÁos
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public RegistroAtendimento pesquisarRegistroAtendimento(Integer id)
			throws ErroRepositorioException {
		RegistroAtendimento retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat " + "FROM RegistroAtendimento rgat "
					+ "left join fetch rgat.solicitacaoTipoEspecificacao stes "
					+ "left join fetch stes.solicitacaoTipo sotp "
					+ "left join fetch stes.especificacaoServicoTipos estp "
					+ "WHERE rgat.id = " + id;

			retorno = (RegistroAtendimento) session.createQuery(consulta)
					.uniqueResult();

			/*
			 * if (retorno != null) {
			 * Hibernate.initialize(retorno.getSolicitacaoTipoEspecificacao());
			 * Hibernate.initialize(retorno.getSolicitacaoTipoEspecificacao()
			 * .getSolicitacaoTipo());
			 * Hibernate.initialize(retorno.getSolicitacaoTipoEspecificacao()
			 * .getEspecificacaoServicoTipos()); Collection collection = retorno
			 * .getSolicitacaoTipoEspecificacao()
			 * .getEspecificacaoServicoTipos(); for (Iterator iter =
			 * collection.iterator(); iter.hasNext();) {
			 * EspecificacaoServicoTipo est = (EspecificacaoServicoTipo) iter
			 * .next(); Hibernate.initialize(est.getServicoTipo()); } }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do EndereÁo da acorrÍcia e LGCP_ID=LGCP_ID do EndereÁo
	 * da acorrÍcia e STEP_ID=Id da especificaÁ„o selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * [SB0008] Verifica existÍncia de Registro de Atendimento Pendente para o
	 * Local da acorrÍcia
	 * 
	 * @author Raphael Rossiter
	 * @date 15/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroCep, idLogradouroBairro
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection verificaExistenciaRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" step.descricao, "
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" bairro.codigo, "
					+ // 17
					" rgat.complementoEndereco,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao, "
					+ // 23
					" step.id " // 24
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.solicitacaoTipoEspecificacao step "
					+ "INNER JOIN step.solicitacaoTipo sotp "
					+ "left join rgat.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join rgat.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "WHERE rgat.imovel.id IS NULL AND rgat.codigoSituacao = 1 "
					+ "AND logradouroBairro.id = :idLogradouroBairro AND logradouroCep.id = :idLogradouroCep "
					+ "AND step.id = :idSolicitacaoTipoEspecificacao AND sotp.indicadorFaltaAgua = 2";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idLogradouroBairro", idLogradouroBairro).setInteger(
					"idLogradouroCep", idLogradouroCep).setInteger(
					"idSolicitacaoTipoEspecificacao",
					idSolicitacaoTipoEspecificacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do EndereÁo da acorrÍcia e LGCP_ID=LGCP_ID do EndereÁo
	 * da acorrÍcia e STEP_ID=Id da especificaÁ„o selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroCep, idLogradouroBairro
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" step.descricao, "
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" bairro.codigo, "
					+ // 17
					" rgat.complementoEndereco,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao, "
					+ // 23
					" step.id, "
					+ // 24
					" sotp.id, "
					+ // 25
					" sotp.descricao, "
					+ // 26
					" rgat.id, "
					+ // 27
					" rgat.pontoReferencia, "
					+ // 28
					" rgat.registroAtendimento " // 29
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.solicitacaoTipoEspecificacao step "
					+ "INNER JOIN step.solicitacaoTipo sotp "
					+ "left join rgat.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join rgat.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "WHERE rgat.imovel.id IS NULL AND rgat.codigoSituacao = 1 "
					+ "AND logradouroBairro.id = :idLogradouroBairro AND logradouroCep.id = :idLogradouroCep "
					+ "AND step.id = :idSolicitacaoTipoEspecificacao AND sotp.indicadorFaltaAgua = 2";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idLogradouroBairro", idLogradouroBairro).setInteger(
					"idLogradouroCep", idLogradouroCep).setInteger(
					"idSolicitacaoTipoEspecificacao",
					idSolicitacaoTipoEspecificacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * Pesquisar dados do registro de atendimento
	 * 
	 * @author Ana Maria, Raphael Rossiter, Ivan Sergio
	 * @date 14/08/2006, 03/03/2008, 09/09/2009
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rgat.id," // 0
					+ " rgat.codigoSituacao," // 1
					+ " step.id, step.descricao," // 2,3
					+ " sotp.id, sotp.descricao," // 4,5
					+ " meso.id, meso.descricao," // 6,7
					+ " imov.id," // 8
					+ " loca.id," // 9
					+ " stcm.codigo," // 10
					+ " qdra.numeroQuadra," // 11
					+ " imov.lote," // 12
					+ " imov.subLote," // 13
					+ " rgat.registroAtendimento," // 14
					+ " rgat.dataPrevistaAtual," // 15
					+ " rgat.dataEncerramento," // 16
					+ " amen.id, amen.descricao," // 17, 18
					+ " rgat.pontoReferencia," // 19
					+ " brar.id,brar.nome,"// 20,21
					+ " bair.id, bair.nome,"// 22,23
					+ " dves.id, dves.descricao," // 24,25
					+ " lcrg.id, scrg.codigo, qdrg.numeroQuadra, " // 26,27,28
					+ " sotp.indicadorTarifaSocial, " // 29
					+ " rgat.ultimaAlteracao, " // 30
					+ " step.indicadorCliente, step.indicadorParecerEncerramento, " // 31,
					// 32
					+ " rgat.logradouroBairro.id, rgat.logradouroCep.id," // 33,
					// 34
					+ " rgat.complementoEndereco," // 35
					+ " localOcorrencia.id, pavimentoRua.id, " // 36, 37
					+ " pavimentoCalcada.id, rgat.descricaoLocalOcorrencia, " // 38,
					// 39
					+ " scrg.id, qdrg.id, "// 40, 41
					+ " muni.id, muni.nome, "// 42, 43
					+ " raMotivoReativacao.id, raMotivoReativacao.descricao, "// 44,
					// 45
					+ " rgat.dataPrevistaOriginal, " // 46
					+ " rgat.observacao, " // 47
					+ " rgat.indicadorAtendimentoOnline, " // 48
					+ " rgat.dataInicioEspera, " // 49
					+ " rgat.dataFimEspera, " // 50
					+ " lcrg.descricao, " // 51
					+ " localOcorrencia.descricao, "// 52
					+ " pavimentoRua.descricao, " // 53
					+ " pavimentoCalcada.descricao, " // 54
					+ " scrg.descricao, " // 55
					+ " rgat.quantidadeReiteracao, " // 56
					+ " rgat.ultimaReiteracao, " // 57
					+ " rgat.parecerEncerramento, "// 58
					+ " last.id, last.descricao, "// 59, 60
					+ " lest.id, lest.descricao, "// 61, 62
					+ " step.indicadorGeracaoDebito, "// 63
					+ " step.indicadorGeracaoCredito, "// 64
					+ " rgat.manual, "// 65
					+ " amen.indicadorExecucao, " // 66
					+ " rota.codigo, " // 67
					+ " imov.numeroSequencialRota, " // 68
					+ " imov.numeroImovel, " // 69
					+ " dbtp.id, dbtp.descricao, " // 70, 71
					+ " step.valorDebito, " // 72
					+ " step.indicadorPermiteAlterarValor, step.indicadorCobrarJuros, " // 73, 74
					+ " qdra.id, " // 75
					+ " svtp.id, "//76
					+ " svtp.valor, "//77
					+ " sncm.id, sncm.descricao, " // 78, 79
					+ " rgat.nnCoordenadaNorte, rgat.nnCoordenadaLeste, " // 80, 81
					+ " imovelPerfil.descricao, " //82
					+ " sotg.id, " //83 
					+ " sotg.descricao, " //84
					+ " step.indicadorInformarPagamentoDuplicidade," //85
					+ " step.indicadorInformarDocumentoPagamentoAMaior," //86
					+ " step.indicadorInformarDocumentoPagamentoIndevido" //87
					+ " from RegistroAtendimento rgat "
					+ " left join rgat.solicitacaoTipoEspecificacao step"
					+ " left join step.solicitacaoTipo sotp"
					+ " left join rgat.meioSolicitacao meso"
					+ " left join rgat.imovel imov"
					+ " left join imov.imovelPerfil imovelPerfil "
					+ " left join imov.localidade loca"
					+ " left join imov.setorComercial stcm"
					+ " left join imov.quadra qdra"
					+ " left join qdra.rota rota"
					+ " left join imov.ligacaoAguaSituacao last"
					+ " left join imov.ligacaoEsgotoSituacao lest"
					+ " left join rgat.atendimentoMotivoEncerramento amen"
					+ " left join rgat.bairroArea brar"
					+ " left join brar.bairro bair"
					+ " left join bair.municipio muni"
					+ " left join rgat.divisaoEsgoto dves"
					+ " left join rgat.localidade lcrg"
					+ " left join rgat.setorComercial scrg"
					+ " left join rgat.quadra qdrg"
					+ " left join rgat.raMotivoReativacao raMotivoReativacao"
					+ " left join rgat.pavimentoRua pavimentoRua"
					+ " left join rgat.pavimentoCalcada pavimentoCalcada"
					+ " left join rgat.localOcorrencia localOcorrencia"
					+ " left join rgat.localOcorrencia localOcorrencia"
					+ " left join rgat.servicoNaoCobrancaMotivo sncm"
					+ " left join step.servicoTipo svtp "
					+ " left join step.debitoTipo dbtp "
					+ " left join sotp.solicitacaoTipoGrupo sotg "

					+ " where rgat.id  = :idRegistroAtendimento";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * Pesquisar dados do registro de atendimento solicitante
	 * 
	 * @author Ana Maria
	 * @date 15/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosRASolicitante(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select clie.id, clie.nome, " // 0,1
					+ " unid.id, unid.descricao," // 2,3
					+ " raso.solicitante, raso.id, raso.numeroProtocoloAtendimento, " // 4, 5, 6
					+ " raso.numeroCPF " // 7
					+ " from RegistroAtendimentoSolicitante raso"
					+ " left join raso.registroAtendimento rgat"
					+ " left join raso.cliente clie"
					+ " left join raso.unidadeOrganizacional unid"
					+ " where rgat.id  = :idRegistroAtendimento and raso.indicadorSolicitantePrincipal = :indicadorSolicitantePrincipal";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento).setInteger(
					"indicadorSolicitantePrincipal", 1).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar Indicador falta de agua da RA
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarIndicadorFaltaAguaRA(Integer idEspecificacao)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select sotp.indicadorFaltaAgua, "
					+ "step.indicadorMatricula "
					+ " from SolicitacaoTipoEspecificacao step"
					+ " left join step.solicitacaoTipo sotp"
					+ " where step.id  = :idEspecificacao";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idEspecificacao", idEspecificacao).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * [SB0018] Verificar ProgramaÁıes de Abastecimento e/ou ManutenÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOcorrenciaAbastecimentoProgramacao(
			Date dataAbastecimentoRA, Integer idBairroArea, Integer idBairro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Integer amAtendimentoRA = Util
				.recuperaAnoMesDaData(dataAbastecimentoRA);

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select abastProgramacao.id "
					+ " from AbastecimentoProgramacao abastProgramacao"
					+ " LEFT JOIN abastProgramacao.bairroArea baiArea"
					+ " LEFT JOIN abastProgramacao.bairro bai"
					+ " LEFT JOIN bai.municipio municBairro"
					+ " LEFT JOIN abastProgramacao.municipio municAbastProgramacao"
					+ " LEFT JOIN baiArea.distritoOperacional disOpeBaiArea"
					+ " LEFT JOIN abastProgramacao.distritoOperacional disOpeAbastProgramacao"
					+ " LEFT JOIN disOpeBaiArea.zonaAbastecimento zonaAbasDisOpe"
					+ " LEFT JOIN abastProgramacao.zonaAbastecimento zonaAbasAbastProgramacao"
					+ " LEFT JOIN disOpeBaiArea.setorAbastecimento setorAbasDisOpe"
					+ " LEFT JOIN abastProgramacao.setorAbastecimento setorAbasAbastProgramacao"
					+ " LEFT JOIN setorAbasDisOpe.sistemaAbastecimento sistAbasSetAbas"
					+ " LEFT JOIN abastProgramacao.sistemaAbastecimento sistAbasAbastProgramacao"
					+ " where abastProgramacao.anoMesReferencia = :amAtendimentoRA AND"
					+ " ((abastProgramacao.dataInicio < :dataAbastecimentoRA AND "
					+ " abastProgramacao.dataFim > :dataAbastecimentoRA) OR"
					+ "(abastProgramacao.dataInicio = :dataAbastecimentoRA AND"
					+ " abastProgramacao.horaInicio <= :horaAtendimento AND"
					+ " abastProgramacao.horaFim >= :horaAtendimento ) OR"
					+ "(abastProgramacao.dataFim = :dataAbastecimentoRA AND"
					+ " abastProgramacao.horaInicio <= :horaAtendimento AND"
					+ " abastProgramacao.horaFim >= :horaAtendimento)) AND "
					+ " (baiArea.id = :idBairroArea OR bai.id = :idBairro OR "
					+ " (bai.id = :idBairro AND municBairro.id = municAbastProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND disOpeBaiArea.id = disOpeAbastProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND zonaAbasDisOpe.id = zonaAbasAbastProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND setorAbasDisOpe.id = setorAbasAbastProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND sistAbasSetAbas.id = sistAbasAbastProgramacao.id))";

			retorno = (Integer) session.createQuery(consulta).setDate(
					"dataAbastecimentoRA", dataAbastecimentoRA).setInteger(
					"amAtendimentoRA", amAtendimentoRA).setTime(
					"horaAtendimento", dataAbastecimentoRA).setInteger(
					"idBairroArea", idBairroArea).setInteger("idBairro",
					idBairro).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * [SB0018] Verificar ProgramaÁıes de Abastecimento e/ou ManutenÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String pesquisarNomeBairroArea(Integer idBairroArea)
			throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select bairroArea.nome "
					+ " from BairroArea bairroArea"
					+ " Where bairroArea.id = :idBairroArea";
			retorno = (String) session.createQuery(consulta).setInteger(
					"idBairroArea", idBairroArea).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0366] Inserir Registro Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 09/02/2007
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoSolicitacaoTipoEspecificacao(
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT step.descricao"
					+ " FROM SolicitacaoTipoEspecificacao step"
					+ " WHERE step.id = :idSolicitacaoTipoEspecificacao ";

			retorno = (String) session.createQuery(consulta).setInteger(
					"idSolicitacaoTipoEspecificacao",
					idSolicitacaoTipoEspecificacao).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * [SB0018] Verificar ProgramaÁıes de Abastecimento e/ou ManutenÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOcorrenciaManutencaoProgramacao(
			Date dataAbastecimentoRA, Integer idBairroArea, Integer idBairro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Integer amAtendimentoRA = Util
				.recuperaAnoMesDaData(dataAbastecimentoRA);

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select manutProgramacao.id "
					+ " from ManutencaoProgramacao manutProgramacao"
					+ " LEFT JOIN manutProgramacao.bairroArea baiArea"
					+ " LEFT JOIN manutProgramacao.bairro bai"
					+ " LEFT JOIN bai.municipio municBairro"
					+ " LEFT JOIN manutProgramacao.municipio municManuProgramacao"
					+ " LEFT JOIN baiArea.distritoOperacional disOpeBaiArea"
					+ " LEFT JOIN manutProgramacao.distritoOperacional disOpeManuProgramacao"
					+ " LEFT JOIN disOpeBaiArea.zonaAbastecimento zonaAbasDisOpe"
					+ " LEFT JOIN manutProgramacao.zonaAbastecimento zonaAbasManuProgramacao"
					+ " LEFT JOIN disOpeBaiArea.setorAbastecimento setorAbasDisOpe"
					+ " LEFT JOIN manutProgramacao.setorAbastecimento setorAbasManuProgramacao"
					+ " LEFT JOIN setorAbasDisOpe.sistemaAbastecimento sistAbasSetAbas"
					+ " LEFT JOIN manutProgramacao.sistemaAbastecimento sistAbasManuProgramacao"
					+ " where manutProgramacao.anoMesReferencia = :amAtendimentoRA AND"
					+ " ((manutProgramacao.dataInicio < :dataAbastecimentoRA AND "
					+ " manutProgramacao.dataFim > :dataAbastecimentoRA) OR"
					+ "(manutProgramacao.dataInicio = :dataAbastecimentoRA AND"
					+ " manutProgramacao.horaInicio <= :horaAtendimento AND"
					+ " manutProgramacao.horaFim >= :horaAtendimento ) OR"
					+ "(manutProgramacao.dataFim = :dataAbastecimentoRA AND"
					+ " manutProgramacao.horaInicio <= :horaAtendimento AND"
					+ " manutProgramacao.horaFim >= :horaAtendimento)) AND "
					+ " (baiArea.id = :idBairroArea OR bai.id = :idBairro OR "
					+ " (bai.id = :idBairro AND municBairro.id = municManuProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND disOpeBaiArea.id = disOpeManuProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND zonaAbasDisOpe.id = zonaAbasManuProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND setorAbasDisOpe.id = setorAbasManuProgramacao.id) OR"
					+ " (baiArea.id = :idBairroArea AND sistAbasSetAbas.id = sistAbasManuProgramacao.id))";

			retorno = (Integer) session.createQuery(consulta).setDate(
					"dataAbastecimentoRA", dataAbastecimentoRA).setInteger(
					"amAtendimentoRA", amAtendimentoRA).setTime(
					"horaAtendimento", dataAbastecimentoRA).setInteger(
					"idBairroArea", idBairroArea).setInteger("idBairro",
					idBairro).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar Indicador falta de agua da RA
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Collection pesquisarRAAreaBairro(Integer idRegistroAtendimento,
			Integer idBairroArea, Integer idEspecificacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.atendimentopublico.registroatendimento.bean.ExibirRAFaltaAguaImovelHelper"
					+ "(rgat.id,rgat.imovel.id,rgat.registroAtendimento) "
					+ " from RegistroAtendimento rgat"
					+ " left join rgat.bairroArea baiArea"
					+ " left join rgat.solicitacaoTipoEspecificacao step"
					+ " where baiArea.id  = :idBairroArea AND"
					+ " rgat.codigoSituacao = :codSituacao AND"
					+ " rgat.id <> :idRegistroAtendimento AND"
					+ " step.id = :idEspecificacao";

			retorno = session.createQuery(consulta).setInteger("idBairroArea",
					idBairroArea).setInteger("idRegistroAtendimento",
					idRegistroAtendimento).setShort("codSituacao",
					RegistroAtendimento.SITUACAO_PENDENTE).setInteger(
					"idEspecificacao", idEspecificacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro Atendimento
	 * 
	 * Pesquisar Indicador falta de agua da RA
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAAreaBairroInserir(Integer idBairroArea,
			Integer idEspecificacao) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.atendimentopublico.registroatendimento.bean.ExibirRAFaltaAguaImovelHelper"
					+ "(rgat.id,rgat.imovel.id,rgat.registroAtendimento) "
					+ " from RegistroAtendimento rgat"
					+ " left join rgat.bairroArea baiArea"
					+ " left join rgat.solicitacaoTipoEspecificacao step"
					+ " where baiArea.id  = :idBairroArea AND"
					+ " rgat.codigoSituacao = :codSituacao AND"
					+ " step.id = :idEspecificacao";

			retorno = session.createQuery(consulta).setInteger("idBairroArea",
					idBairroArea).setShort("codSituacao",
					RegistroAtendimento.SITUACAO_PENDENTE).setInteger(
					"idEspecificacao", idEspecificacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar id do AtendimentoEncerramentoMotivo
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarIdAtendimentoEncerramentoMotivo()
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select atenMotEncerramento.id "
					+ " from RegistroAtendimento rgat"
					+ " left join rgat.atendimentoMotivoEncerramento atenMotEncerramento"
					+ " where atenMotEncerramento.indicadorDuplicidade  = :indicadorDuplicidade";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"indicadorDuplicidade",
					AtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE_ATIVO)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar o id do registro atendimento para a area bairro especifica
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarRAAreaBairroFaltaAguaImovel(Integer idBairroArea)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rgat.id " + " from RegistroAtendimento rgat"
					+ " left join rgat.bairroArea baiArea"
					+ " left join rgat.solicitacaoTipoEspecificacao step"
					+ " left join step.solicitacaoTipo sotp"
					+ " where baiArea.id  = :idBairroArea AND"
					+ " rgat.codigoSituacao = :codSituacao AND"
					+ " step.indicadorMatricula = :indicadorMatricula AND"
					+ " sotp.indicadorFaltaAgua = :indicadorFalAgua ";

			retorno = (Integer) session
					.createQuery(consulta)
					.setInteger("idBairroArea", idBairroArea)
					.setShort("codSituacao",
							RegistroAtendimento.SITUACAO_PENDENTE)
					.setInteger(
							"indicadorMatricula",
							SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_NAO_OBRIGATORIO)
					.setShort("indicadorFalAgua",
							SolicitacaoTipo.INDICADOR_USO_ATIVO).setMaxResults(
							1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar a descriÁ„o da solicitacao do tipo de especificaÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String descricaoSolTipoEspecAguaGeneralizada(
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select step.descricao "
					+ " from SolicitacaoTipoEspecificacao step"
					+ " where step.id  = :idSolicitacaoTipoEspecificacao";

			retorno = (String) session.createQuery(consulta).setInteger(
					"idSolicitacaoTipoEspecificacao",
					idSolicitacaoTipoEspecificacao).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar a descriÁ„o da solicitacao do tipo de especificaÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String descricaoSolTipoEspecFaltaAguaGeneralizada()
			throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select step.descricao "
					+ " from SolicitacaoTipoEspecificacao step"
					+ " left join step.solicitacaoTipo solTipo"
					+ " where step.indicadorMatricula  = :indMatricula AND"
					+ " solTipo.indicadorFaltaAgua = :indFaltaAgua";

			retorno = (String) session
					.createQuery(consulta)
					.setInteger(
							"indMatricula",
							SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_NAO_OBRIGATORIO)
					.setShort("indFaltaAgua",
							SolicitacaoTipo.INDICADOR_FALTA_AGUA_SIM)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar a descriÁ„o da solicitacao do tipo de especificaÁ„o
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String descricaoSolTipoEspecAguaImovel(
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select step.descricao "
					+ " from SolicitacaoTipoEspecificacao step"
					+ " left join step.solicitacaoTipo sotp"
					+ " where step.id  = :idSolicitacaoTipoEspecificacao AND"
					+ " step.indicadorMatricula = :indicadorMatricula AND"
					+ " sotp.indicadorFaltaAgua = :indicadorFalAgua";

			retorno = (String) session
					.createQuery(consulta)
					.setInteger("idSolicitacaoTipoEspecificacao",
							idSolicitacaoTipoEspecificacao)
					.setInteger(
							"indicadorMatricula",
							SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_OBRIGATORIO)
					.setShort("indicadorFalAgua",
							SolicitacaoTipo.INDICADOR_USO_ATIVO).setMaxResults(
							1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar parms registro atendimento e jogando o objeto helper
	 * 
	 * @author S·vio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsRAFaltaAguaImovel(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select sotp.id,sotp.descricao,step.id,step.descricao,"// 0,1,2,3
					+ " bai.codigo,bai.nome,baiArea.id,baiArea.nome "// 4,5,6,7
					+ " from RegistroAtendimento rgat"
					+ " left join rgat.solicitacaoTipoEspecificacao step"
					+ " left join step.solicitacaoTipo sotp"
					+ " left join rgat.bairroArea baiArea"
					+ " left join baiArea.bairro bai"
					+ " left join rgat.imovel imov"
					+ " where rgat.id  = :idRegistroAtendimento";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0413] Pesquisar Tipo de ServiÁos
	 * 
	 * select a.svtp_id from ATENDIMENTOPUBLICO.SERVICO_TIPO A,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_ATIVIDADE B,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_MATERIAL C WHERE A.SVTP_DSSERVICOTIPO
	 * LIKE '%DESC%' AND A.SVTP_DSABREVIADO LIKE '%DESC%' AND (A.SVTP_VLSERVICO >=
	 * 000000 AND A.SVTP_VLSERVICO <= 99999) AND A.SVTP_ICPAVIMENTO = 1 OU 2 and
	 * A.SVTP_ICATUALIZACOMERCIAL = 1 OU 2 AND A.SVTP_ICTERCEIRIZADO = 1 OU 2
	 * AND A.SVTP_CDSERVICOTIPO = ("O" OR "C") AND (A.SVTP_NNTEMPOMEDIOEXECUCAO >=
	 * 0000 AND A.SVTP_NNTEMPOMEDIOEXECUCAO <= 9999) AND DBTP_ID = ID INFORMADO
	 * AND AND CRTP_ID = ID INFORMADO AND STSG_ID = ID INFORMADO AND STRF_ID =
	 * ID INFORMADO AND STPR_ID = ID INFORMADO AND A.SVTP_ID = B.SVTP_ID AND
	 * B.ATIV_ID IN (ID's INFORMADOS) AND A.SVTP_ID = C.SVTP_ID AND C.MATE_ID IN
	 * (ID's INFORMADOS)
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 * 
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal)
			throws ErroRepositorioException {

		Collection<ServicoTipo> retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "SELECT svtp.id,svtp.descricao,"
					+ "svtp.servicoTipoPrioridade.descricao,"
					+ "svtp.indicadorAtualizaComercial,"
					+ "svtp.indicadorPavimento," + "svtp.indicadorTerceirizado"
					+ " FROM ServicoTipo svtp  "
					+ " LEFT JOIN svtp.servicoTipoSubgrupo subg "
					+ " LEFT JOIN svtp.debitoTipo tpdb"
					+ " LEFT JOIN svtp.creditoTipo tpcd "
					+ " LEFT JOIN svtp.servicoTipoPrioridade tppri "
					+ " LEFT JOIN svtp.servicoTipoReferencia tpref  "
					+ " LEFT JOIN svtp.servicoPerfilTipo perftp "
					+ " WHERE 1=1 ";

			if (servicoTipo.getDescricao() != null
					&& !servicoTipo.getDescricao().equals("")) {
				consulta += " AND svtp.descricao LIKE %:descricao% AND svtp.descricao LIKE :descricaoAbreviada";
				parameters.put("descricao", servicoTipo.getDescricao());
				parameters
						.put("descricaoAbreviada", servicoTipo.getDescricao());
			}
			if (servicoTipo.getDescricaoAbreviada() != null
					&& !servicoTipo.getDescricaoAbreviada().equals("")) {
				consulta += " AND svtp.descricaoAbreviada LIKE :descricao% AND svtp.descricaoAbreviada LIKE :descricaoAbreviada%";
				parameters
						.put("descricao", servicoTipo.getDescricaoAbreviada());
				parameters.put("descricaoAbreviada", servicoTipo
						.getDescricaoAbreviada());
			}

			if (servicoTipo.getServicoTipoSubgrupo() != null
					&& !servicoTipo.getServicoTipoSubgrupo().equals("")) {
				consulta += " AND subg.id = (:idSubg) ";
				parameters.put("idSubg", servicoTipo.getServicoTipoSubgrupo()
						.getId());
			}

			if (new Short(servicoTipo.getIndicadorPavimento()) != 0) {
				consulta += " AND indpv.id = (:idIndpv) ";
				parameters.put("idIndpv", servicoTipo.getIndicadorPavimento());
			}

			if (!valorServicoInicial.equalsIgnoreCase("")
					&& !valorServicoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.valor BETWEEN (:valorInicial) AND (:valorFinal) ";
				parameters.put("valorInicial", valorServicoInicial);
				parameters.put("valorFinal", valorServicoFinal);
			}

			if (new Short(servicoTipo.getIndicadorAtualizaComercial()) != 0) {

				if (servicoTipo.getIndicadorAtualizaComercial() == 1) {
					consulta += " AND indac.id in (1,3) ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 2) {
					consulta += " AND indac.id = 1 ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 3) {
					consulta += " AND indac.id = 3 ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 4) {
					consulta += " AND indac.id in (1,2,3)  ";
				} else if (servicoTipo.getIndicadorAtualizaComercial() == 5) {
					consulta += " AND indac.id = 2 ";
				}
			}

			if (servicoTipo.getCodigoServicoTipo() != null
					&& !servicoTipo.getCodigoServicoTipo().equals("")) {
				consulta += " AND codsvtp.id = (:codSvtp) ";
				parameters.put("codSvtp", servicoTipo.getCodigoServicoTipo());
			}

			if (!tempoMedioExecucaoInicial.equalsIgnoreCase("")
					&& tempoMedioExecucaoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempmedio BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", tempoMedioExecucaoInicial);
				parameters.put("tempoFinal", tempoMedioExecucaoFinal);

			} else if (!tempoMedioExecucaoInicial.equalsIgnoreCase("")
					&& tempoMedioExecucaoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempmedio BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", tempoMedioExecucaoInicial);
				parameters.put("tempoFinal", 9999);
			} else if (!tempoMedioExecucaoFinal.equalsIgnoreCase("")
					&& tempoMedioExecucaoInicial.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempmedio BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", 0000);
				parameters.put("tempoFinal", tempoMedioExecucaoFinal);
			}
			if (servicoTipo.getDebitoTipo() != null) {
				if (servicoTipo.getDebitoTipo().getId() != null
						&& !servicoTipo.getDebitoTipo().getId().equals("")) {
					consulta += " AND tpdb.id = (:idDeb) ";
					parameters
							.put("idDeb", servicoTipo.getDebitoTipo().getId());
				}
			}
			if (servicoTipo.getCreditoTipo() != null) {
				if (servicoTipo.getCreditoTipo().getId() != null
						&& !servicoTipo.getCreditoTipo().getId().equals("")) {
					consulta += " AND tpcd.id = (:idTpcd) ";
					parameters.put("idTpcd", servicoTipo.getCreditoTipo()
							.getId());
				}
			}
			if (servicoTipo.getServicoTipoPrioridade() != null) {
				if (servicoTipo.getServicoTipoPrioridade().getId() != null
						&& !servicoTipo.getServicoTipoPrioridade().getId()
								.equals("")) {
					consulta += " AND tppri.id = (:idTpcd) ";
					parameters.put("idTpcd", servicoTipo
							.getServicoTipoPrioridade().getId());
				}
			}
			if (servicoTipo.getServicoPerfilTipo() != null) {
				if (servicoTipo.getServicoPerfilTipo().getId() != null
						&& !servicoTipo.getServicoPerfilTipo().getId().equals(
								"")) {
					consulta += " AND perftp.id = (:idPerf) ";
					parameters.put("idPerf", servicoTipo.getServicoPerfilTipo()
							.getId());
				}
			}
			if (servicoTipo.getServicoTipoReferencia() != null) {
				if (servicoTipo.getServicoTipoReferencia().getId() != null
						&& servicoTipo.getServicoTipoReferencia().getId()
								.equals("")) {
					consulta += " AND tpref.id = (:idRef) ";
					parameters.put("idRef", servicoTipo
							.getServicoTipoReferencia().getId());
				}
			}

			if (colecaoMaterial != null && !colecaoMaterial.isEmpty()) {
				consulta += " AND srvtpmat.id in (:idMat) ";
				parameters.put("idMat", colecaoMaterial);
			}

			if (colecaoAtividades != null && !colecaoAtividades.isEmpty()) {
				consulta += " AND srvtpatv.id in (:idAt) ";
				parameters.put("idAt", colecaoAtividades);
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

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * [FS009] atualizaÁ„o realizada por outro Usu·rio
	 * 
	 * @author Leonardo Regis
	 * @date 22/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @return dataAtual
	 * @throws ErroRepositorioException
	 */
	public Date verificarConcorrenciaRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Date retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " select rgat.ultimaAlteracao" // 0
					+ " from RegistroAtendimento rgat"
					+ " where rgat.id  = :idRegistroAtendimento";

			retorno = (Date) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente j· seja um
	 * solicitante do registro de atendimento (existe acorrÍcia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado).
	 * 
	 * [FS0012] Verificar existÍncia do cliente solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaClienteSolicitante(
			Integer idRegistroAtendimento, Integer idCliente)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select COUNT(*)"
					+ " from RegistroAtendimentoSolicitante raso"
					+ " left join raso.registroAtendimento rgat"
					+ " left join raso.cliente clie"
					+ " where rgat.id  = :idRegistroAtendimento AND clie.id = :idCliente";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento).setInteger(
					"idCliente", idCliente).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade j· seja um
	 * solicitante do registro de atendimento (existe acorrÍcia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada).
	 * 
	 * [FS0026] Verificar existÍncia da unidade solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaUnidadeSolicitante(
			Integer idRegistroAtendimento, Integer idUnidade)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select COUNT(*)"
					+ " from RegistroAtendimentoSolicitante raso"
					+ " left join raso.registroAtendimento rgat"
					+ " left join raso.unidadeOrganizacional unid"
					+ " where rgat.id  = :idRegistroAtendimento AND unid.id = :idUnidade";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento).setInteger(
					"idUnidade", idUnidade).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Verificar maior data de encerramento das ordens de ServiÁos de um RA
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @return Maior Data de Encerramento da OS de um RA
	 * @throws ControladorException
	 */
	public Date obterMaiorDataEncerramentoOSRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException {
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT max(os.dataEncerramento) "
					+ " FROM OrdemServico os "
					+ " INNER JOIN os.registroAtendimento rgat  "
					+ " WHERE rgat.id = :idRA "
					+ " AND os.situacao = :situacao";

			retorno = (Date) session.createQuery(consulta).setInteger("idRA",
					idRegistroAtendimento).setShort("situacao",
					OrdemServico.SITUACAO_ENCERRADO).setMaxResults(1)
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
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Permite encerrar o ra atualizando a tabela REGISTRO_ATENDIMENTO
	 * 
	 * @author Leonardo Regis
	 * @date 29/08/2006
	 * 
	 * @param registroAtendimento
	 */
	public void encerrarRegistroAtendimento(
			RegistroAtendimento registroAtendimento)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Query query = null;
		String encerrarRA;
		try {

			encerrarRA = " UPDATE RegistroAtendimento "
					+ " SET atendimentoMotivoEncerramento.id = :motivoEncerramentoId, "
					+ " codigoSituacao = :situacao, ";
			if (registroAtendimento.getRegistroAtendimentoDuplicidade() != null) {
				encerrarRA += "registroAtendimentoDuplicidade.id = :raDuplicidadeId, ";
			}
			if (registroAtendimento.getServicoNaoCobrancaMotivo() != null) {
				encerrarRA += " servicoNaoCobrancaMotivo = :servicoNaoCobrancaMotivo, ";
			}
			encerrarRA += " dataEncerramento = :dataEncerramento, "
					+ " parecerEncerramento = :parecerEncerramento, "
					+ " ultimaAlteracao = :dataUltimaAlteracao "
					+ " WHERE id = :registroAtendimentoId";

			query = session.createQuery(encerrarRA)
					.setInteger("motivoEncerramentoId",
							registroAtendimento.getAtendimentoMotivoEncerramento()
								.getId())
					.setShort("situacao",
							RegistroAtendimento.SITUACAO_ENCERRADO)
					.setTimestamp("dataEncerramento",
							registroAtendimento.getDataEncerramento())
					.setString("parecerEncerramento",
							registroAtendimento.getParecerEncerramento())
					.setTimestamp(
							"dataUltimaAlteracao", new Date())
					.setInteger(
							"registroAtendimentoId", registroAtendimento.getId());
			
			if (registroAtendimento.getRegistroAtendimentoDuplicidade() != null) {
				query.setInteger("raDuplicidadeId", registroAtendimento
						.getRegistroAtendimentoDuplicidade().getId());
			}
			
			if(registroAtendimento.getServicoNaoCobrancaMotivo() != null) {
				query.setInteger("servicoNaoCobrancaMotivo",
						registroAtendimento.getServicoNaoCobrancaMotivo().getId());
			}
			
			query.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisar o registro atendimento unidade com o id do registro atendimento
	 * e o id do atendimento relaÁoes tipo igual a abrir/registrar
	 * 
	 * @author S·vio Luiz
	 * @date 30/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimentoUnidade pesquisarRAUnidade(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		RegistroAtendimentoUnidade retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rau" + " from RegistroAtendimentoUnidade rau"
					+ " left join rau.atendimentoRelacaoTipo atenRelTipo"
					+ " left join rau.registroAtendimento rgat"
					//+ " inner join fetch rau.unidadeOrganizacional "
					+ " where rgat.id  = :idRegistroAtendimento AND"
					+ " atenRelTipo.id = :idAtenRelacaoTipo";

			retorno = (RegistroAtendimentoUnidade) session
					.createQuery(consulta).setInteger("idRegistroAtendimento",
							idRegistroAtendimento).setInteger(
							"idAtenRelacaoTipo",
							AtendimentoRelacaoTipo.ABRIR_REGISTRAR)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisar o registro atendimento unidade com o id do registro atendimento
	 * e o id do atendimento relaÁoes tipo igual a abrir/registrar
	 * 
	 * @author S·vio Luiz
	 * @date 30/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public void removerSolicitanteFone(Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException {
		// Obter a Sess„o
		Session session = HibernateUtil.getSession();

		// Cria a vari√°vel que vai conter o hql
		String consulta;

		try {

			// Constroi o hql para remover os dados di√°rios da arrecada√ß√£o
			// referentes ao ano/m√™s de arrecada√ß√£o atual
			consulta = "delete gcom.atendimentopublico.registroatendimento.SolicitanteFone solFone where solFone.registroAtendimentoSolicitante.id = :idRegistroAtendimentoSolicitante";

			// Executa o hql
			session.createQuery(consulta).setInteger(
					"idRegistroAtendimentoSolicitante",
					idRegistroAtendimentoSolicitante).executeUpdate();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a ses√É¬£o com o hibernate
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param especificacao
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeDestinoEspecificacao(
			Integer especificacao) throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select unid "
					+ " from SolicitacaoTipoEspecificacao step "
					+ " inner join step.unidadeOrganizacional unid "
					+ " where step.id = :especificacao";

			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("especificacao", especificacao)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param idRaSolicitante
	 * @return RegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public RegistroAtendimentoSolicitante obterRegistroAtendimentoSolicitante(
			Integer idRaSolicitante) throws ErroRepositorioException {

		RegistroAtendimentoSolicitante retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select raso "
					+ " from RegistroAtendimentoSolicitante raso"
					+ " where raso.id = :idRaSolicitante";

			retorno = (RegistroAtendimentoSolicitante) session.createQuery(
					consulta).setInteger("idRaSolicitante", idRaSolicitante)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @param idRaSolicitante
	 * @return RegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public Collection pesquisarFoneSolicitante(Integer idRaSolicitante)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select sofo " + " from SolicitanteFone sofo "
					+ " inner join sofo.registroAtendimentoSolicitante raso "
					+ " where raso.id = :idRaSolicitante";

			retorno = session.createQuery(consulta).setInteger(
					"idRaSolicitante", idRaSolicitante).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * SOTP_ICFALTAAGUA da tabela SOLICITACAO_TIPO com o valor correspondente a
	 * um e STEP_ICMATRICULA com o valor correspondente a dois na tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO).
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @return idEspecificacao, idTipo
	 * @throws ControladorException
	 */
	public Collection pesquisarTipoEspecificacaoFaltaAguaGeneralizada()
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT sotp.id, step.id "
					+ " FROM SolicitacaoTipoEspecificacao step "
					+ " INNER JOIN step.solicitacaoTipo sotp "
					+ " WHERE step.indicadorMatricula = 2 AND sotp.indicadorFaltaAgua = 1";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisa os fones do regsitro atendimento solicitante
	 * 
	 * @author S·vio Luiz
	 * @date 05/09/2006
	 * 
	 * @return idRASolicitante
	 * @throws ControladorException
	 */
	public Collection pesquisarParmsFoneRegistroAtendimentoSolicitante(
			Integer idRASolicitante) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT solFone.ddd, solFone.fone,solFone.ramal,solFone.indicadorPadrao,ft.id,ft.descricao,solFone.ultimaAlteracao "
					+ " FROM SolicitanteFone solFone "
					+ " INNER JOIN solFone.foneTipo ft "
					+ " WHERE solFone.registroAtendimentoSolicitante.id = :idRASolicitante";

			retorno = session.createQuery(consulta).setInteger(
					"idRASolicitante", idRASolicitante).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente j· seja um
	 * solicitante do registro de atendimento (existe acorrÍcia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado e o registro
	 * atendimento solicitante for diferente do que esteja° sendo atualizado).
	 * 
	 * [FS0027] Verificar existÍncia do cliente solicitante
	 * 
	 * @author S·vio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaClienteSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idCliente,
			Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select COUNT(*)"
					+ " from RegistroAtendimentoSolicitante raso"
					+ " left join raso.registroAtendimento rgat"
					+ " left join raso.cliente clie"
					+ " where rgat.id  = :idRegistroAtendimento AND clie.id = :idCliente AND "
					+ " raso.id <> :idRegistroAtendimentoSolicitante";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento).setInteger(
					"idCliente", idCliente).setInteger(
					"idRegistroAtendimentoSolicitante",
					idRegistroAtendimentoSolicitante).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade j· seja um
	 * solicitante do registro de atendimento (existe acorrÍcia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada e RASO_ID<>id
	 * do Registro atendimento solicitante).
	 * 
	 * [FS0018] Verificar existÍncia da unidade solicitante
	 * 
	 * @author S·vio Luiz
	 * @date 07/09/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaUnidadeSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idUnidade,
			Integer idRASolicitante) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select COUNT(*)"
					+ " from RegistroAtendimentoSolicitante raso"
					+ " left join raso.registroAtendimento rgat"
					+ " left join raso.unidadeOrganizacional unid"
					+ " where rgat.id  = :idRegistroAtendimento AND unid.id = :idUnidade AND"
					+ " raso.id <> :idRASolicitante";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento).setInteger(
					"idUnidade", idUnidade).setInteger("idRASolicitante",
					idRASolicitante).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de ProgramaÁıes de Ordens de ServiÁos
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param idRa
	 * @return dataPrevisaoAtual
	 * @throws ErroRepositorioException
	 */
	public Date obterDataAgenciaReguladoraPrevisaoAtual(Integer idRa)
			throws ErroRepositorioException {

		Date retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT raDados.dataPrevisaoAtual "
					+ "FROM RaDadosAgenciaReguladora raDados "
					+ "INNER JOIN raDados.registroAtendimento ra "
					+ "WHERE ra.id = :idRa";

			retorno = (Date) session.createQuery(consulta).setInteger("idRa",
					idRa).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Consultar os registros de atendimento do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 25/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarRegistroAtendimentoImovel(Integer idImovel,
			String situacao) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT registroAtendimento.id, " // 0
					+ " solicitacaoTipo.descricao, " // 1
					+ " solicitacaoTipoEspecificacao.descricao, " // 2
					+ " registroAtendimento.registroAtendimento, " // 3
					+ " registroAtendimento.dataEncerramento, " // 4
					+ " atendimento.id, " // 5
					+ " atendimento.descricao " // 6
					+ "from RegistroAtendimento registroAtendimento "
					+ "left join registroAtendimento.solicitacaoTipoEspecificacao solicitacaoTipoEspecificacao "
					+ "left join solicitacaoTipoEspecificacao.solicitacaoTipo solicitacaoTipo "
					+ "left join registroAtendimento.atendimentoMotivoEncerramento atendimento "

					+ "where registroAtendimento.imovel.id = :idImovel ";

			if (situacao != null) {
				consulta = consulta
						+ " and registroAtendimento.codigoSituacao = :codigoSituacao ";
			}

			Query query = session.createQuery(consulta);

			query.setInteger("idImovel", idImovel.intValue());

			// colocar a situacao se houver
			if (situacao != null) {
				query.setInteger("codigoSituacao", new Integer(situacao));
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com RGAT_CDSITUACAO=1 e BRAR_ID=Id da √Årea do Bairro
	 * selecionada e STEP_ID=STEP_ID da tabela SOLICITACAO_TIPO_ESPECIFICACAO
	 * com STEP_ICMATRICULA com o valor correspondente a um e SOTP_ID=SOTP_ID da
	 * tabela SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente
	 * a um.
	 * 
	 * [SB0025] Verifica Registro de Atendimento de √Ågua Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idBairroArea
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAFaltaAguaGeneralizada(Integer idBairroArea)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" step.descricao, "
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" bairro.codigo, "
					+ // 17
					" rgat.complementoEndereco,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao, "
					+ // 23
					" step.id, "
					+ // 24
					" sotp.id, "
					+ // 25
					" sotp.descricao, "
					+ // 26
					" rgat.id, "
					+ // 27
					" rgat.pontoReferencia, "
					+ // 28
					" rgat.registroAtendimento, "
					+ // 29
					" brar.id, "
					+ // 30
					" brar.nome, "
					+ // 31
					" bair.codigo, "
					+ // 32
					" bair.nome, "
					+ // 33
					" imovel.id " // 34
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.bairroArea brar "
					+ "INNER JOIN brar.bairro bair "
					+ "INNER JOIN rgat.solicitacaoTipoEspecificacao step "
					+ "INNER JOIN step.solicitacaoTipo sotp "
					+ "left join rgat.imovel imovel "
					+ "left join rgat.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join rgat.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "WHERE brar.id = :idBairroArea AND rgat.codigoSituacao = 1 "
					+ "AND step.indicadorMatricula = 1 AND sotp.indicadorFaltaAgua = 1";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idBairroArea", idBairroArea).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0015] Verifica Registro de Atendimento de √Ågua Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idImovel,
	 *            idEspecificacao, dataReativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAEncerradoImovel(Integer idImovel,
			Integer idEspecificacao, Date dataReativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" step.descricao, "
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" bairro.codigo, "
					+ // 17
					" rgat.complementoEndereco,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao, "
					+ // 23
					" step.id, "
					+ // 24
					" sotp.id, "
					+ // 25
					" sotp.descricao, "
					+ // 26
					" rgat.id, "
					+ // 27
					" rgat.pontoReferencia, "
					+ // 28
					" rgat.registroAtendimento, "
					+ // 29
					" imovel.id, "
					+ // 30
					" loca.id, "
					+ // 31
					" stcm.codigo, "
					+ // 32
					" qdra.numeroQuadra, "
					+ // 33
					" imovel.lote, "
					+ // 34
					" imovel.subLote, "
					+ // 35
					" rgat.dataEncerramento, "
					+ // 36
					" amen.id, "
					+ // 37
					" amen.descricao, "
					+ // 38
					" imovel.numeroImovel, "
					+ // 39
					" imovel.complementoEndereco " // 40
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.imovel imovel "
					+ "INNER JOIN rgat.solicitacaoTipoEspecificacao step "
					+ "INNER JOIN step.solicitacaoTipo sotp "
					+ "left join imovel.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join imovel.localidade loca "
					+ "left join imovel.setorComercial stcm "
					+ "left join imovel.quadra qdra "
					+ "left join rgat.atendimentoMotivoEncerramento amen "
					+ "WHERE imovel.id = :idImovel AND rgat.codigoSituacao = 2 "
					+ "AND step.id = :idEspecificacao AND rgat.dataEncerramento >= :dataReativacao ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("idEspecificacao",
					idEspecificacao).setTimestamp("dataReativacao",
					dataReativacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0015] Verifica Registro de Atendimento de √Ågua Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idLogradouroBairro,
	 *            idLogradouroCep, idEspecificacao, dataReativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAEncerradoLocalOcorrencia(
			Integer idLogradouroBairro, Integer idLogradouroCep,
			Integer idEspecificacao, Date dataReativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" step.descricao, "
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" bairro.codigo, "
					+ // 17
					" rgat.complementoEndereco,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao, "
					+ // 23
					" step.id, "
					+ // 24
					" sotp.id, "
					+ // 25
					" sotp.descricao, "
					+ // 26
					" rgat.id, "
					+ // 27
					" rgat.pontoReferencia, "
					+ // 28
					" rgat.registroAtendimento, "
					+ // 29
					" rgat.dataEncerramento, "
					+ // 30
					" amen.id, "
					+ // 31
					" amen.descricao " // 32
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.solicitacaoTipoEspecificacao step "
					+ "INNER JOIN step.solicitacaoTipo sotp "
					+ "left join rgat.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join rgat.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join rgat.atendimentoMotivoEncerramento amen "
					+ "WHERE rgat.imovel.id IS NULL AND rgat.codigoSituacao = 2 "
					+ "AND logradouroCep.id = :idLogradouroCep AND logradouroBairro.id = :idLogradouroBairro "
					+ "AND step.id = :idEspecificacao AND rgat.dataEncerramento >= :dataReativacao "
					+ "AND sotp.indicadorFaltaAgua = 2 ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idLogradouroCep", idLogradouroCep).setInteger(
					"idLogradouroBairro", idLogradouroBairro).setInteger(
					"idEspecificacao", idEspecificacao).setTimestamp(
					"dataReativacao", dataReativacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa os dados do Registro Atendimento Solicitante para o RelatÛrios de
	 * OS
	 * 
	 * @author Rafael CorrÍia
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT cliente.id, cliente.nome, " // 0,1
					+ "unid.id, unid.descricao, " // 2,3
					+ "func.id, func.nome, " // 4,5
					+ "raso.solicitante " // 6
					+ "FROM RegistroAtendimentoSolicitante raso "
					+ "INNER JOIN raso.registroAtendimento ra "
					+ "LEFT JOIN raso.cliente cliente "
					+ "LEFT JOIN raso.unidadeOrganizacional unid "
					+ "LEFT JOIN raso.funcionario func "
					+ "WHERE ra.id =  :idRegistroAtendimento AND "
					+ "raso.indicadorSolicitantePrincipal = "
					+ RegistroAtendimentoSolicitante.INDICADOR_PRINCIPAL;

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0458] - Imprimir Ordem de ServiÁos
	 * 
	 * Pesquisa o telefone principal do Registro Atendimento Solicitante para o
	 * RelatÛrios de OS
	 * 
	 * @author Rafael CorrÍia
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarSolicitanteFonePrincipal(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT sf.ddd, sf.fone, sf.ramal "
					+ "FROM SolicitanteFone sf "
					+ "INNER JOIN sf.registroAtendimentoSolicitante raso "
					+ "INNER JOIN raso.registroAtendimento ra "
					+ "WHERE ra.id =  :idRegistroAtendimento AND "
					+ "raso.indicadorSolicitantePrincipal = "
					+ RegistroAtendimentoSolicitante.INDICADOR_PRINCIPAL
					+ " AND " + "sf.indicadorPadrao = "
					+ SolicitanteFone.INDICADOR_FONE_PADRAO;

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0482] - Obter EndereÁo Abreviado da acorrÍcia do RA
	 * 
	 * Pesquisa os dados necess√°rios do RA para verificar como o EndereÁo ser·
	 * obtido
	 * 
	 * @author Rafael CorrÍia
	 * @date 18/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */

	public Object[] obterLogradouroBairroImovelRegistroAtendimento(Integer idRA)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT logrbairro.id, imov.id "
					+ "FROM RegistroAtendimento ra "
					+ "LEFT JOIN ra.logradouroBairro logrbairro "
					+ "LEFT JOIN ra.imovel imov " + "WHERE ra.id =  :idRA";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRA", idRA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0482] - Obter EndereÁo Abreviado da acorrÍcia do RA
	 * 
	 * Pesquisa o Endereco Descritivo do RA
	 * 
	 * @author Rafael CorrÍia
	 * @date 18/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */

	public Object[] obterEnderecoDescritivoRA(Integer idRA)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT raenddesc.descricao, raenddesc.descricaoBairro "
					+ "FROM RaEnderecoDescritivo raenddesc "
					+ "INNER JOIN raenddesc.registroAtendimento ra "
					+ "WHERE ra.id =  :idRA";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRA", idRA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0014] Manter ImÛvel
	 * 
	 * [FS0006] - Verificar existÍncia de RA verifica se existe para o ImÛvel RA
	 * encerrada por exclusıes com especificaÁ„o da SolicitaÁıes que permita a
	 * ManutenÁ„o do ImÛvel
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento verificarExistenciaRAManutencaoImovel(
			Integer idImovel, Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException {

		RegistroAtendimento retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat "
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN FETCH rgat.imovel imov  "
					+ "INNER JOIN FETCH rgat.solicitacaoTipoEspecificacao step  "
					+ "INNER JOIN rgat.atendimentoMotivoEncerramento amen  "
					+ "WHERE imov.id = :idImovel AND step.id = :idSolicitacaoTipoespecificacao "
					+ "AND rgat.codigoSituacao = 1 AND amen.id = 1 ";

			retorno = (RegistroAtendimento) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setInteger(
							"idSolicitacaoTipoespecificacao",
							idSolicitacaoTipoEspecificacao).setMaxResults(1)
					.uniqueResult();

			/*
			 * if (retorno != null) { Hibernate.initialize(retorno.getImovel());
			 * Hibernate.initialize(retorno.getSolicitacaoTipoEspecificacao()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0042] Verifica n˙mero informado ImÛvel
	 * 
	 * @author Raphael Rossiter
	 * @date 07/11/2006
	 * 
	 * @param ultimoRAManual
	 * @return quantidadeRA
	 * @throws ErroRepositorioException
	 */
	public Integer verificaNumeracaoRAManualInformada(Integer ultimoRAManual)
			throws ErroRepositorioException {

		Integer retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat.id " + "FROM RegistroAtendimento rgat "
					+ "WHERE rgat.manual = :ultimoRAManual ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"ultimoRAManual", ultimoRAManual).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Cria a query de acordo com os par√¢metros de pesquisa informados pelo
	 * Usu·rio
	 * 
	 * [UC0492] - Gerar RelatÛrios Acompanhamento de exclusıes de Ordem de ServiÁos
	 * 
	 * @author Rafael CorrÍia
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return Collection
	 */
	public Collection pesquisarRAGerarRelatorioGestaoSolicitacaoRAPorUnidade(
			Date periodoAtendimentoInicial, Date periodoAtendimentoFinal,
			String situacaoRA, String[] idsSolicitacoesTipos,
			String[] idsEspecificacoes, String idUnidade,
			String idUnidadeSuperior, String idMunicipio, String codigoBairro,
			String tipoOrdenacao) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT DISTINCT solTpEsp.step_id as idSolTpEsp, " // 0
					+ " solTpEsp.step_dssolcttipoespec as descricaoSolTpEsp, " // 1
					+ " solTpEsp.step_nndiaprazo as diasPrazo, " // 2
					+ " tramite.unid_iddestino as idUnidade, " // 3
					+ " ra.rgat_id as idRA, " // 4
					+ " ra.rgat_tmencerramento as dataEncerramento, " // 5
					+ " ra.amen_id as idAtendMotEnc, " // 6
					+ " ra.rgat_dtprevistaatual as dataPrevista, " // 7
					+ " unidadeAtendimento.unid_iccentralatendimento as indCentralAtendimento " // 8
					+ " FROM "
					+ " atendimentopublico.registro_atendimento ra "
					+ " INNER JOIN "
					+ " atendimentopublico.solicitacao_tipo_espec solTpEsp "
					+ " on ra.step_id = solTpEsp.step_id "
					+ " INNER JOIN "
					+ " atendimentopublico.solicitacao_tipo solTp "
					+ " on solTpEsp.sotp_id = solTp.sotp_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.imovel imovel "
					+ " on ra.imov_id = imovel.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.logradouro_bairro logrBairroImovel "
					+ " on imovel.lgbr_id = logrBairroImovel.lgbr_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.bairro bairroImovel "
					+ " on logrBairroImovel.bair_id = bairroImovel.bair_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.municipio municipioImovel "
					+ " on bairroImovel.muni_id = municipioImovel.muni_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.logradouro_bairro logrBairroRA "
					+ " on ra.lgbr_id = logrBairroRA.lgbr_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.bairro bairroRA "
					+ " on logrBairroRA.bair_id = bairroRA.bair_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.municipio municipioRA "
					+ " on bairroRA.muni_id = municipioRA.muni_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.bairro_area areaBairroRA "
					+ " on ra.brar_id = areaBairroRA.brar_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.bairro bairroAreaBairro "
					+ " on areaBairroRA.bair_id = bairroAreaBairro.bair_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.municipio municipioAreaBairro "
					+ " on bairroAreaBairro.muni_id = municipioAreaBairro.muni_id "
					+ " LEFT OUTER JOIN "
					+ " atendimentopublico.tramite tramite "
					+ " on tramite.rgat_id = ra.rgat_id "
					+ " LEFT OUTER JOIN "
					+ " atendimentopublico.ra_unidade raUnidade "
					+ " on raUnidade.rgat_id = ra.rgat_id and "
					+ " raUnidade.attp_id = "
					+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR
					+ " LEFT OUTER JOIN "
					+ " cadastro.unidade_organizacional unidadeAtendimento "
					+ " on raUnidade.unid_id = unidadeAtendimento.unid_id ";

			consulta = consulta
					+ criarCondicionaisRAGerarRelatorioGestaoRA(
							periodoAtendimentoInicial, periodoAtendimentoFinal,
							situacaoRA, idsSolicitacoesTipos,
							idsEspecificacoes, idUnidade, idUnidadeSuperior,
							idMunicipio, codigoBairro);

			if (tipoOrdenacao != null && tipoOrdenacao.equals("1")) {
				consulta = consulta + " order by servTp.svtp_id, ra.rgat_id ";
			}

			retorno = session.createSQLQuery(consulta).addScalar("idSolTpEsp",
					Hibernate.INTEGER).addScalar("descricaoSolTpEsp",
					Hibernate.STRING).addScalar("diasPrazo", Hibernate.INTEGER)
					.addScalar("idUnidade", Hibernate.INTEGER).addScalar(
							"idRA", Hibernate.INTEGER).addScalar(
							"dataEncerramento", Hibernate.TIMESTAMP).addScalar(
							"idAtendMotEnc", Hibernate.INTEGER).addScalar(
							"dataPrevista", Hibernate.DATE).addScalar(
							"indCentralAtendimento", Hibernate.SHORT).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Cria as condicionais de acordo com os par√¢metros de pesquisa informados
	 * pelo Usu·rio
	 * 
	 * [UC0499] - Gerar RelatÛrios de Gestejao de SolicitaÁıes de RA por
	 * Unidade/Chefia
	 * 
	 * @author Rafael CorrÍia
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return String
	 */
	public String criarCondicionaisRAGerarRelatorioGestaoRA(
			Date periodoAtendimentoInicial, Date periodoAtendimentoFinal,
			String situacaoRA, String[] idsSolicitacoesTipos,
			String[] idsEspecificacoes, String idUnidade,
			String idUnidadeSuperior, String idMunicipio, String codigoBairro) {

		String sql = " WHERE ";

		if (periodoAtendimentoInicial != null
				&& !periodoAtendimentoFinal.equals("")) {
			String data1 = Util
					.recuperaDataInvertida(periodoAtendimentoInicial);

			if (data1 != null && !data1.equals("")
					&& data1.trim().length() == 8) {

				data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
						+ "-" + data1.substring(6, 8);
			}
			sql = sql + " ra.rgat_tmregistroatendimento >= to_date('" + data1
					+ "' and ";
		}

		if (periodoAtendimentoFinal != null
				&& !periodoAtendimentoFinal.equals("")) {
			String data2 = Util.recuperaDataInvertida(periodoAtendimentoFinal);

			if (data2 != null && !data2.equals("")
					&& data2.trim().length() == 8) {

				data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
						+ "-" + data2.substring(6, 8);
			}
			sql = sql + " ra.rgat_tmregistroatendimento <= to_date('" + data2
					+ "' and ";
		}

		if (situacaoRA != null && !situacaoRA.equals("")) {
			sql = sql + " ra.rgat_cdsituacao = " + situacaoRA + " and ";
		}

		if (idsSolicitacoesTipos != null
				&& !idsSolicitacoesTipos.equals("")
				&& !idsSolicitacoesTipos[0].equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			String valoresIn = "";
			for (int i = 0; i < idsSolicitacoesTipos.length; i++) {
				if (!idsSolicitacoesTipos[i].equals("")) {
					valoresIn = valoresIn + idsSolicitacoesTipos[i] + ",";
				}
			}
			if (!valoresIn.equals("")) {
				sql = sql + " solTp.sotp_id in (" + valoresIn;
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
			}
		}

		if (idsEspecificacoes != null
				&& !idsEspecificacoes.equals("")
				&& !idsEspecificacoes[0].equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			String valoresIn = "";
			for (int i = 0; i < idsEspecificacoes.length; i++) {
				if (!idsEspecificacoes[i].equals("")) {
					valoresIn = valoresIn + idsEspecificacoes[i] + ",";
				}
			}
			if (!valoresIn.equals("")) {
				sql = sql + " solTpEsp.step_id in (" + valoresIn;
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
			}
		}

		if (idUnidade != null && !idUnidade.equals("")) {
			sql = sql
					+ " ra.rgat_id in (SELECT tr.rgat_id from atendimentopublico.tramite tr "
					+ " INNER JOIN atendimentopublico.registro_atendimento ra on ra.rgat_id = tr.rgat_id "
					+ " WHERE tr.unid_iddestino = "
					+ idUnidade
					+ " and tr.tram_tmtramite in (SELECT max(tr.tram_tmtramite) FROM atendimentopublico.tramite tr "
					+ " WHERE ra.rgat_id = tr.rgat_id)) " + " and ";
		}

		if (idUnidadeSuperior != null && !idUnidadeSuperior.equals("")) {
			sql = sql
					+ " ra.rgat_id in (SELECT tr.rgat_id from atendimentopublico.tramite tr "
					+ " INNER JOIN atendimentopublico.registro_atendimento ra on ra.rgat_id = tr.rgat_id "
					+ " WHERE tr.unid_iddestino = "
					+ idUnidadeSuperior
					+ " or tr.unid_iddestino in (SELECT unidade.unid_id "
					+ " FROM cadastro.unidade_organizacional unidade WHERE unidade.unid_idsuperior = "
					+ idUnidadeSuperior
					+ ") "
					+ " and tr.tram_tmtramite in (SELECT max(tr.tram_tmtramite) FROM atendimentopublico.tramite tr "
					+ " WHERE ra.rgat_id = tr.rgat_id)) " + " and ";
		}

		if (idMunicipio != null && !idMunicipio.equals("")) {
			sql = sql
					+ " ((municipioRA.muni_id = "
					+ idMunicipio
					+ ")"
					+ " or (logrBairroRA.lgbr_id is null and municipioImovel.muni_id = "
					+ idMunicipio + ")" + " or (municipioAreaBairro.muni_id = "
					+ idMunicipio + "))" + " and ";
		}

		if (codigoBairro != null && !codigoBairro.equals("")) {
			sql = sql
					+ " ((bairroRA.bair_cdbairro = "
					+ codigoBairro
					+ ")"
					+ " or (logrBairroRA.lgbr_id is null and bairroImovel.bair_cdbairro = "
					+ codigoBairro + ")"
					+ " or (bairroAreaBairro.bair_cdbairro = " + codigoBairro
					+ "))" + " and ";
		}

		// retira o " and " q fica sobrando no final da query
		sql = Util.removerUltimosCaracteres(sql, 4);

		return sql;
	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Pesquisa o registro de atendimento fazendo os carregamentos necess√°rios
	 * 
	 * @author Rafael CorrÍia
	 * @date 03/01/2007
	 * 
	 * @param idRegistroAtendimento
	 * @return registroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento pesquisarRegistroAtendimentoTarifaSocial(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		RegistroAtendimento registroAtendimento = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT registroAtendimento "
					+ " FROM RegistroAtendimento registroAtendimento "
					+ " INNER JOIN FETCH registroAtendimento.solicitacaoTipoEspecificacao step "
					+ " INNER JOIN FETCH step.solicitacaoTipo solTp "
					+ " LEFT JOIN FETCH registroAtendimento.imovel imov "
					+ " LEFT JOIN FETCH registroAtendimento.atendimentoMotivoEncerramento atendMotEnc "
					+ " WHERE registroAtendimento.id = :idRegistroAtendimento ";

			registroAtendimento = (RegistroAtendimento) session.createQuery(
					consulta).setInteger("idRegistroAtendimento",
					idRegistroAtendimento).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return registroAtendimento;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * 
	 * @author S·vio Luiz
	 * @date 12/01/2007
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public Integer verificarMesmaRA(Integer idImovel, Integer idRA)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT rgat.id " + "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.imovel imov  "
					+ "WHERE imov.id = :idImovel AND rgat.id = :idRA";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("idRA", idRA)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public Short verificarIndicadorTarifaSocialRA(Integer idRA)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select sotp.indicadorTarifaSocial"
					+ " from RegistroAtendimento rgat"
					+ " inner join rgat.solicitacaoTipoEspecificacao step"
					+ " inner join step.solicitacaoTipo sotp"
					+ " where rgat_id = :idRA";

			retorno = (Short) session.createQuery(consulta).setInteger("idRA",
					idRA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public Short verificarIndicadorTarifaSocialUsuario(Integer idUsuario)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select unid.indicadorTarifaSocial"
					+ " from Usuario usua"
					+ " inner join usua.unidadeOrganizacional unid"
					+ " where usua.id = :idUsuario";

			retorno = (Short) session.createQuery(consulta).setInteger(
					"idUsuario", idUsuario).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Atualiza logradouroBairro de um ou mais imÛveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroBairro(
			LogradouroBairro logradouroBairroAntigo,
			LogradouroBairro logradouroBairroNovo)
			throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimento SET "
					+ "lgbr_id = :idLogradouroBairroNovo, rgat_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE lgbr_id = :idLogradouroBairroAntigo ";

			session.createQuery(consulta).setInteger("idLogradouroBairroNovo",
					logradouroBairroNovo.getId()).setTimestamp(
					"ultimaAlteracao", new Date()).setInteger(
					"idLogradouroBairroAntigo", logradouroBairroAntigo.getId())
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Atualiza logradouroCep de um ou mais imÛveis
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
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimento SET "
					+ "lgcp_id = :idLogradouroCepNovo, rgat_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE lgcp_id = :idLogradouroCepAntigo ";

			session.createQuery(consulta).setInteger("idLogradouroCepNovo",
					logradouroCepNovo.getId()).setTimestamp("ultimaAlteracao",
					new Date()).setInteger("idLogradouroCepAntigo",
					logradouroCepAntigo.getId()).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Atualiza logradouroBairro de um ou mais imÛveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroBairroSolicitante(
			LogradouroBairro logradouroBairroAntigo,
			LogradouroBairro logradouroBairroNovo)
			throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante SET "
					+ "lgbr_id = :idLogradouroBairroNovo, raso_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE lgbr_id = :idLogradouroBairroAntigo ";

			session.createQuery(consulta).setInteger("idLogradouroBairroNovo",
					logradouroBairroNovo.getId()).setTimestamp(
					"ultimaAlteracao", new Date()).setInteger(
					"idLogradouroBairroAntigo", logradouroBairroAntigo.getId())
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Atualiza logradouroCep de um ou mais imÛveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroCepSolicitante(
			LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo)
			throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante SET "
					+ "lgcp_id = :idLogradouroCepNovo, raso_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE lgcp_id = :idLogradouroCepAntigo ";

			session.createQuery(consulta).setInteger("idLogradouroCepNovo",
					logradouroCepNovo.getId()).setTimestamp("ultimaAlteracao",
					new Date()).setInteger("idLogradouroCepAntigo",
					logradouroCepAntigo.getId()).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * UC?? - ????????
	 * 
	 * @author RÛmulo AurÈlio Filho
	 * @date 20/03/2007
	 * @descricao O mÈtodo retorna um objeto com a maior data de Tramite
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Tramite pesquisarUltimaDataTramite(Integer idRA)
			throws ErroRepositorioException {

		Tramite tramite = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT tram " + " FROM Tramite tram "
					+ " WHERE tram.dataTramite = "
					+ " (SELECT MAX(trambase.dataTramite) "
					+ " FROM Tramite trambase"
					+ " WHERE trambase.registroAtendimento.id = :idRA)";

			tramite = (Tramite) session.createQuery(consulta).setInteger(
					"idRA", idRA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tramite;
	}

	/**
	 * Consultar Observacao Registro Atendimento Solicitacao da CAER
	 * 
	 * @author Rafael Pinto
	 * @date 14/03/2007
	 */
	public Collection pesquisarObservacaoRegistroAtendimento(
			Integer matriculaImovel, Date dataInicialAtendimento,
			Date dataFinalAtendimento) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";
		Map parameters = new HashMap();
		Query query = null;

		try {
			consulta = "SELECT ra.id,ra.registroAtendimento,ra.observacao "
					+ "FROM RegistroAtendimento ra "
					+ "WHERE ra.imovel.id = :idImovel "
					+ "AND ra.observacao IS NOT NULL ";

			parameters.put("idImovel", matriculaImovel);

			if (dataInicialAtendimento != null && dataFinalAtendimento != null) {
				consulta += "AND ra.registroAtendimento BETWEEN (:dataInicial) AND (:dataFinal) ";

				parameters.put("dataInicial", dataInicialAtendimento);
				parameters.put("dataFinal", dataFinalAtendimento);
			}

			consulta = consulta + "ORDER BY ra.registroAtendimento DESC";

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

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * mÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id rota id guradra numero da quadra id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Thiago TenÛrio, Ivan SÈrgio
	 * @date 25/04/2007, 14/01/2009
	 * @alteracao 14/01/2009 - CRC811 - Adicionado o campo amen_id(Motivo Encerramento) a consulta.
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection getImoveisResumoRegistroAtendimento(int idLocalidade,
			Integer anoMesReferencia, Integer dtAtual) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		
		//dtAtual = 20070228;

		try {
			String hql = 
				"select distinct" + 
				"	imo.id, " + 
				"	case when ( not imo is null ) then " + 
				"		locPorImo.gerenciaRegional.id " + 
				"	else " +
				"		locPorRa.gerenciaRegional.id " + 
				"	end as greg_id, " +
				
				"	case when ( not imo is null ) then " + 
				"		locPorImo.unidadeNegocio.id " + 
				"	else " +
				"		locPorRa.unidadeNegocio.id " + 
				"	end as uneg_id, " +
				
				"	case when ( not imo is null ) then " + 
				"		locPorImo.id " +
				" 	else " +
				"		locPorRa.id " + 
				"	end as loca_id, " +
				
				"	case when ( not imo is null ) then " + 
				"		locPorImo.localidade.id " + 
				"	else " +
				"		locPorRa.localidade.id " + 
				"	end as loca_cdelo, " + 
				
				"	case when ( not imo is null ) then " + 
				"		scom.id " + 
				"	else " +
				"		null " + 
				"	end as stcm_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		quad.rota.id " + 
				"	else " + 
				"		null " + 
				"	end as rota_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		quad.id " + 
				"	else " + 
				"		null " + 
				"	end as qdra_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		scom.codigo " + 
				"	else " + 
				"		null " + 
				"	end as rera_cdsetorcomercial, " + 
				
				"	case when ( not imo is null ) then " + 
				"		quad.numeroQuadra " + 
				"	else " + 
				"		null " + 
				"	end as rera_nnquadra, " + 
				
				"	case when ( not imo is null ) then " + 
				"		imo.imovelPerfil.id " + 
				"	else " + 
				"		null " + 
				"	end as iper_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		imo.ligacaoAguaSituacao.id " + 
				"	else " + 
				"		null " + 
				"	end as last_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		imo.ligacaoEsgotoSituacao.id " + 
				"	else " + 
				"		null " + 
				"	end as lest_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		plAgua.ligacaoAguaPerfil.id " + 
				"	else " + 
				"		null " + 
				"	end as lapf_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		plEsgoto.ligacaoEsgotoPerfil.id " + 
				"	else " + 
				"		null " + 
				"	end as lepf_id, " + 
				
				"	ra.indicadorAtendimentoOnline, " + 
				"	soltipo.id, " + 
				"	solTipEsp.id, " + 
				"	ra.meioSolicitacao.id, " + 
				
				"	case when (cast((substring(ra.registroAtendimento, 1, 4) || " +
	  			"			         substring(ra.registroAtendimento, 6, 2)) as integer) = :anoMesReferencia) then " + 
	  			"		1 " +
	  			"	else " +
	  			"		0 " +
	  			"	end as qtGeradas, " +
	  			
	  			"	case when ( ra.codigoSituacao = 1 ) then " + 
	  			"		case when ( cast(substring(ra.dataPrevistaOriginal, 1, 4) || " +
	  			"						 substring(ra.dataPrevistaOriginal, 6, 2) || " +
	  			"		 				 substring(ra.dataPrevistaOriginal, 9, 2) as integer) >= :dtAtual ) then " +
	  			//"		 				 substring(ra.dataPrevistaOriginal, 9, 2) as integer) >= (select logradouro.id from gcom.cadastro.sistemaparametro.SistemaParametro) ) then " +
	  			
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " +
	  			"	end as qtRaPendenteNoPrazo, " + 
	  
	  			"	case when ( ra.codigoSituacao = 1 ) then " + 
	  			"		case when ( cast(substring(ra.dataPrevistaOriginal, 1, 4) || " +
	  			"					     substring(ra.dataPrevistaOriginal, 6, 2) || " +
	  			"						 substring(ra.dataPrevistaOriginal, 9, 2) as integer) < :dtAtual ) then " +
	  			//"						 substring(ra.dataPrevistaOriginal, 9, 2) as integer) < (select logradouro.id from gcom.cadastro.sistemaparametro.SistemaParametro) ) then " +
	  			
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " + 
	  			"		end " +
	  			"	end as qtRaPendenteForaPrazo, " +
	  			
	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			"		case when ( cast(ra.dataEncerramento as date) <= ra.dataPrevistaOriginal ) then " +
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " + 
	  			"	end as qtraEncerradaNoPrazo, " + 
	  			
	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			"		case when ( cast(ra.dataEncerramento as date) > ra.dataPrevistaOriginal ) then " +
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " +
	  			"	end as qtraEncerradaForaPrazo, " +
	  			
	  			"	case when ( ra.codigoSituacao = 3 ) then " + 
	  			"		1 " + 
	  			"	else " + 
	  			"		0 " + 
	  			"	end as qtBloqueada, " +

	  			// a "substring([texto] from [inicio] for [final])"  È usado para	  			
	  			// transformar data, de (yyyy-mm-dd) para (yyyy-mm)
	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			//"		case when ( substring(cast(ra.dataEncerramento as date) from 1 for 7) <= substring(ra.dataPrevistaOriginal from 1 for 7) ) then " +
	  			"		case when (  to_char(ra.dataEncerramento,'YYYYMMDD') <= to_char(ra.dataPrevistaOriginal,'YYYYMMDD')  ) then " +
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " + 
	  			"	end as qtRaGeradasNoMesPrazo, " + 
	  			
	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			//"		case when ( substring(cast(ra.dataEncerramento as date) from 1 for 7) > substring(ra.dataPrevistaOriginal from 1 for 7) ) then " +
	  			"		case when (  to_char(ra.dataEncerramento,'YYYYMMDD') > to_char(ra.dataPrevistaOriginal,'YYYYMMDD')  ) then " +
	  			
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " +
	  			"	end as qtRaGeradasNoMesForaPrazo, " +
	  			
	  			"	ra.id, " +
	  			"   rota.codigo, " +
	  			"	ramot.id " +
	  			"from " + 
	  			"	gcom.atendimentopublico.registroatendimento.RegistroAtendimento ra " + 
	  			"	inner join ra.solicitacaoTipoEspecificacao solTipEsp " + 
	  			"	inner join solTipEsp.solicitacaoTipo soltipo " + 
	  			"	left join ra.localidade locPorRa " + 
	  			"	left join ra.imovel imo " + 
	  			"	left join imo.localidade locPorImo " + 
	  			"	left join imo.quadra quad " + 
	  			"   left join quad.rota rota " +
	  			"	left join imo.setorComercial scom " + 
	  			"	left join imo.ligacaoAgua plAgua " + 
	  			"	left join imo.ligacaoEsgoto plEsgoto " + 
	  			"	left join ra.registroAtendimentoUnidades rauni " +
	  			"	left join ra.atendimentoMotivoEncerramento ramot " +
	  			"where " + 
	  			"	locPorRa.id = :idLocalidade and " + 
	  			"	substring(ra.registroAtendimento,1,4) || substring(ra.registroAtendimento,6,2) = :anoMesReferencia";

			retorno = session.createQuery(hql)
						.setInteger("idLocalidade", idLocalidade)
						.setInteger("anoMesReferencia", anoMesReferencia)
						.setInteger("dtAtual", dtAtual).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * mÈtodo que insere em batch uma lista de ResumoRegistroAtendimento
	 * 
	 * [UC0275] - Gerar Resumo Resgistro Atendimento
	 * 
	 * @author Thiago TenÛrio
	 * @date 30/04/2007
	 * 
	 * @param listaResumoRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoRegistroAtendimentoBatch(
			List<UnResumoRegistroAtendimento> listaResumoRegistroAtendimento)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		if (listaResumoRegistroAtendimento != null
				&& !listaResumoRegistroAtendimento.isEmpty()) {
			Iterator it = listaResumoRegistroAtendimento.iterator();
			int i = 1;
			try {
				while (it.hasNext()) {

					Object obj = it.next();

					session.save(obj);

					if (i % 50 == 0 || !it.hasNext()) {
						// 20, same as the JDBC batch size
						// flush a batch of inserts and release memory:
						session.flush();
						session.clear();
					}
					i++;
				}

				session.flush();
				session.clear();

			} finally {
				HibernateUtil.closeSession(session);
			}
		}
	}

	/**
	 * [UC0269] - Consultar Resumo dos Registro de Atendimentos
	 * 
	 * @author Thiago TenÛrio
	 * @date 25/04/2007
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoRegistroAtendimento(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// cria a ColeÁıes de retorno
		List retorno = null;
		// Obter a Sess„o
		Session session = HibernateUtil.getSession();

		try {

			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
					GeradorSqlRelatorio.RESUMO_REGISTRO_ATENDIMENTO,
					informarDadosGeracaoRelatorioConsultaHelper);

			String sql = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getDescricaoOpcaoTotalizacao() + "'", "", "", "",
					false, false);

			// faz a pesquisa
			retorno = session
					.createSQLQuery(sql)
					.addScalar("estado", Hibernate.STRING)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("descricaoGerencia", Hibernate.STRING)
					.addScalar("idElo", Hibernate.INTEGER)
					.addScalar("descricaoElo", Hibernate.STRING)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("descricaoLocalidade", Hibernate.STRING)
					.addScalar("idSetorComercial", Hibernate.INTEGER)
					.addScalar("descricaoSetorComercial", Hibernate.STRING)
					.addScalar("idQuadra", Hibernate.INTEGER)
					.addScalar("descricaoQuadra", Hibernate.STRING)
					.addScalar("idLigacaoAguaSituacao", Hibernate.INTEGER)
					.addScalar("descricaoLigacaoAguaSituacao", Hibernate.STRING)
					.addScalar("idLigacaoEsgotoSituacao", Hibernate.INTEGER)
					.addScalar("descricaoLigacaoEsgotoSituacao",
							Hibernate.STRING).addScalar("idCategoria",
							Hibernate.INTEGER).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		// retorna a ColeÁıes com os resultados da pesquisa
		return retorno;
	}

	public Long maximoIdImovel() throws ErroRepositorioException {

		Long retorno = 0L;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select max(imovel.id) from Imovel imovel";
			retorno = ((Integer) session.createQuery(hql).uniqueResult())
					.longValue();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * @author Ana Maria
	 * @date 03/05/2007
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarUnidadeCentralizadoraRa(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select unid.unid_idcentralizadora as unidadeCentralizadora"
					+ " from atendimentopublico.registro_atendimento rgat"
					+ " inner join cadastro.localidade loca on rgat.loca_id=loca.loca_id"
					+ " inner join atendimentopublico.solicitacao_tipo_espec step on rgat.step_id=step.step_id"
					+ " inner join atendimentopublico.solicitacao_tipo sotp on step.sotp_id=sotp.sotp_id"
					+ " inner join atendimentopublico.solicitacao_tipo_grupo sotg on sotp.sotg_id=sotg.sotg_id"
					+ " inner join atendimentopublico.localid_solic_tipo_grupo lstg on (loca.loca_id = lstg.loca_id) and (sotg.sotg_id = lstg.sotg_id)"
					+ " inner join cadastro.unidade_organizacional unid on(unid.unid_id = lstg.unid_id)"
					+ " WHERE rgat.rgat_id =  :idRegistroAtendimento ";

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"unidadeCentralizadora", Hibernate.INTEGER).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * @author Francisco do Nascimento
	 * @date 09/12/2009
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarPossiveisUnidadesCentralizadorasRa(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select unid.unid_idcentralizadora as unidadeCentralizadora"
					+ " from atendimentopublico.registro_atendimento rgat"
					+ " inner join atendimentopublico.localid_solic_tipo_grupo lstg on lstg.loca_id=rgat.loca_id"
					+ " inner join cadastro.unidade_organizacional unid on(unid.unid_id = lstg.unid_id)"
					+ " WHERE rgat.rgat_id =  :idRegistroAtendimento and unid.unid_idcentralizadora is not null ";

			retorno = session.createSQLQuery(consulta).addScalar(
					"unidadeCentralizadora", Hibernate.INTEGER).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Procura a quantidade de dias de prazo
	 * 
	 * [UC0459] Informar Dados da Agencia Reguladora
	 * 
	 * @author KÈrcia Albuquerque
	 * @date 19/04/2007
	 * 
	 */

	public Integer procurarDiasPazo(Integer raId)
			throws ErroRepositorioException {

		Integer retorno = null;

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select step.diasPrazo "
					+ "from RegistroAtendimento ra "
					+ "inner join ra.solicitacaoTipoEspecificacao step "
					+ "where ra.id = :raId";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"raId", raId).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
  	 * Pesquisa a Unidade Solicitante de acordo com a RA  
  	 * 
  	 * @author Ivan SÈrgio
  	 * @date 17/08/2007
  	 * 
  	 */
	
	public Integer pesquisaUnidadeSolicitacaoRa(Integer idRa) throws ErroRepositorioException {
		Integer retorno = null;
		String hql = "";
		Session session = HibernateUtil.getSession();

		try {
			hql = 
				"select " +
				"	raUni.unidadeOrganizacional.id " +
				"from " +
				"	gcom.atendimentopublico.registroatendimento.RegistroAtendimento ra " +
				"	inner join ra.registroAtendimentoUnidades raUni " +
				"where " +
				"	ra.id = :idRa and " +
				"	raUni.atendimentoRelacaoTipo.id = 1";

			retorno = (Integer) session.createQuery(hql)
						.setInteger("idRa", idRa).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
  	 * Pesquisa a Unidade Encerramento de acordo com a RA  
  	 * 
  	 * @author Ivan SÈrgio
  	 * @date 17/08/2007
  	 * 
  	 */
	
	public Integer pesquisaUnidadeEncerradaRa(Integer idRa) throws ErroRepositorioException {
		Integer retorno = null;
		String hql = "";
		Session session = HibernateUtil.getSession();

		try {
			hql = 
				"select " +
				"	case when (ra.codigoSituacao = 2) then " +
				"		raUni.unidadeOrganizacional.id " +
				"	else " +
				"		null " +
				"	end " +
				"from " +
				"	gcom.atendimentopublico.registroatendimento.RegistroAtendimento ra " +
				"	inner join ra.registroAtendimentoUnidades raUni " +
				"where " +
				"	ra.id = :idRa and " +
				"	raUni.atendimentoRelacaoTipo.id = 3";
			
			retorno = (Integer) session.createQuery(hql)
						.setInteger("idRa", idRa).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Pesquisa a quantidade de OS em aberto associadas a uma RA
	 *
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * @author Rafael CorrÍia
	 * @date 31/01/2008
	 *
	 * @param idRA
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSEmAbertoAssociadasRA(Integer idRA) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT count(os.id) " 
					+ " FROM OrdemServico os "
					+ " INNER JOIN os.registroAtendimento ra "
					+ " WHERE os.situacao <> :naoProgramada "
					+ " and ra.id = :idRA "
					+ " and os.atendimentoMotivoEncerramento.id is null";

			retorno = (Integer) session.createQuery(consulta)
			.setInteger("naoProgramada", OrdemServico.NAO_PROGRAMADA)
			.setInteger("idRA", idRA)
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
	 * Pesquisa os registros de atendimento de acordo com o comando solicitado
	 *
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * @author Rafael CorrÍia, Pedro Alexandre
	 * @date 31/01/2008, 11/06/2008
	 *
	 * @param raEncerramentoComando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRAExecutarComandoEncerramento(RaEncerramentoComando raEncerramentoComando, Integer idLocalidade, Collection<Integer> idsEspecificacoes) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		String where = "";

		try {
			consulta = "SELECT ra.rgat_id as idRA, " + //0
						"step.step_id as idEspecificacaoTipo, " + //1
						"step.step_vldebito as valorDebito, " + //2
						"ra.rgat_idduplicidade as idRADuplicidade, " + //3
						"ra.rgat_tmultimaalteracao as dataUltimaAlteracao, " + //4
						"dbtp.dbtp_id as idDebitoTipo " + //5
						"FROM atendimentopublico.registro_atendimento ra " +						
						"INNER JOIN atendimentopublico.solicitacao_tipo_espec step on ra.step_id = step.step_id " +
						"LEFT JOIN faturamento.debito_tipo dbtp on step.dbtp_id = dbtp.dbtp_id " +
						"LEFT OUTER JOIN atendimentopublico.ordem_servico os on ra.rgat_id = os.rgat_id and os.orse_icprogramada = :indicadorProgramacao " ;

			where = where
					+ " WHERE ra.loca_id =:idLocalidade " 
					+ " and substr(ra.rgat_tmregistroatendimento,1,10) between :dataAtendimentoInicial and :dataAtendimentoFinal "
					+ " and ra.rgat_cdsituacao = :situacaoPendente "
					+ " and os.orse_id is null ";
			
			
			if (raEncerramentoComando.getUnidadeOrganizacionalAtendimento() != null) {
				consulta = consulta + "INNER JOIN atendimentopublico.ra_unidade raUnidAtendimento on ra.rgat_id = raUnidAtendimento.rgat_id and raUnidAtendimento.attp_id = " + AtendimentoRelacaoTipo.ABRIR_REGISTRAR + " " ;
				where = where + " and raUnidAtendimento.unid_id = " + raEncerramentoComando.getUnidadeOrganizacionalAtendimento().getId();
			}
			
			if (idsEspecificacoes != null && !idsEspecificacoes.isEmpty()) {
				where = where + " and step.step_id in (:idsEspecificacoes) ";
			}

			
			//monta o sql completo
			consulta = consulta + where;
			
			if (idsEspecificacoes != null && !idsEspecificacoes.isEmpty()) {
			
				retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("idRA",Hibernate.INTEGER)
					.addScalar("idEspecificacaoTipo",Hibernate.INTEGER)
					.addScalar("valorDebito",Hibernate.BIG_DECIMAL)
					.addScalar("idRADuplicidade", Hibernate.INTEGER)
					.addScalar("dataUltimaAlteracao",Hibernate.TIMESTAMP)
					.addScalar("idDebitoTipo",Hibernate.INTEGER)
					.setInteger("idLocalidade",idLocalidade)
					.setShort("indicadorProgramacao", OrdemServico.PROGRAMADA)
					.setDate("dataAtendimentoInicial",raEncerramentoComando.getDataAtendimentoInicial())
					.setDate("dataAtendimentoFinal",raEncerramentoComando.getDataAtendimentoFinal())
					.setShort("situacaoPendente",RegistroAtendimento.SITUACAO_PENDENTE)
					.setParameterList("idsEspecificacoes", idsEspecificacoes)
					.list();
			
			} else {
				retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("idRA",Hibernate.INTEGER)
					.addScalar("idEspecificacaoTipo",Hibernate.INTEGER)
					.addScalar("valorDebito",Hibernate.BIG_DECIMAL)
					.addScalar("idRADuplicidade", Hibernate.INTEGER)
					.addScalar("dataUltimaAlteracao",Hibernate.TIMESTAMP)
					.addScalar("idDebitoTipo",Hibernate.INTEGER)
					.setInteger("idLocalidade",idLocalidade)
					.setShort("indicadorProgramacao", OrdemServico.PROGRAMADA)
					.setDate("dataAtendimentoInicial",raEncerramentoComando.getDataAtendimentoInicial())
					.setDate("dataAtendimentoFinal",raEncerramentoComando.getDataAtendimentoFinal())
					.setShort("situacaoPendente",RegistroAtendimento.SITUACAO_PENDENTE)
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
	 * Pesquisa os dados do telefone do solicitante do
	 * Registro de atendimento.
	 *
	 * [UC0719] Obter Telefone do Solicitante do RA
	 * 
	 * @author Pedro Alexandre
	 * @date 26/12/2007
	 *
	 * @param idRegistroAtendimentoSolicitante
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarTipoSolicitanteRA(Integer idRASolicitante) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select raso.solicitante, " // 0
					+ " clie.id, " // 1
					+ " unid.id " // 2
					+ " from RegistroAtendimentoSolicitante raso "
					+ " inner join raso.registroAtendimento rgat "
					+ " left join raso.cliente clie "
					+ " left join raso.unidadeOrganizacional unid "
					+ " where rgat.id = :idRASolicitante ";

			retorno = (Object[]) session.createQuery(consulta)
			.setInteger("idRASolicitante",idRASolicitante)
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
	 * Obter os dados do telefone do cliente solicitante da RA.
	 *
	 * [UC0719] Obter Telefone do Solicitante do RA
	 *
	 * @author Pedro Alexandre
	 * @date 26/12/2007
	 *
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarTelefoneSolicitanteRACliente(Integer idCliente) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select cfon.ddd, " // 0
					+ " cfon.telefone, " // 1
					+ " cfon.ramal " // 2
					+ " from ClienteFone cfon "
					+ " inner join cfon.cliente clie "
					+ " where clie.id = :idCliente "
					+ " and cfon.indicadorTelefonePadrao = :indicadorPadrao";

			retorno = (Object[]) session.createQuery(consulta)
			.setInteger("idCliente",idCliente)
			.setInteger("indicadorPadrao", ConstantesSistema.SIM)
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
	 * Obter os dados do telefone do solicitante da RA.
	 *
	 * [UC0719] Obter Telefone do Solicitante do RA
	 *
	 * @author Pedro Alexandre
	 * @date 26/12/2007
	 *
	 * @param idRASolicitante
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarTelefoneSolicitanteRASolicitante(Integer idRASolicitante) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select sofo.ddd, " // 0
					+ " sofo.fone, " // 1
					+ " sofo.ramal " // 2
					+ " from SolicitanteFone sofo "
					+ " inner join sofo.registroAtendimentoSolicitante raso "
					+ " inner join raso.registroAtendimento rgat "
					+ " where rgat.id = :idRASolicitante "
					+ " and sofo.indicadorPadrao = :indicador ";

			retorno = (Object[]) session.createQuery(consulta)
			.setInteger("idRASolicitante",idRASolicitante)
			.setInteger("indicador", ConstantesSistema.SIM)
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
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * [SB0002 - Selecionar Registro de Atendimento por Unidade Superior]
	 * 
	 * Pesquisa a ColeÁıes de ids das unidades subordinadas a unidade superior informada
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @param idUnidadeSuperior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsUnidadesSubordinadas(Integer idUnidadeSuperior) throws ErroRepositorioException {

		Collection  retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		try {
			consulta = "SELECT unid.unid_id as id " +
					   "FROM cadastro.unidade_organizacional unid " +	
					   "WHERE unid.unid_idsuperior = :idUnidadeSuperior";

			
			retorno = session.createSQLQuery(consulta)
					.addScalar("id",Hibernate.INTEGER)
					.setInteger("idUnidadeSuperior",idUnidadeSuperior)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
     * [UC0427] Tramitar Registro de Atendimento
     * 
     * @author Vivianne Sousa
     * @date 10/06/2008
     * 
     * @throws ControladorException
     */
    public void atualizarUnidadeOrganizacionalAtualRA(Integer idUnidadeOrganizacionalAtual,
            Integer idRA)
            throws ErroRepositorioException {

        Session session = HibernateUtil.getSession();

        try {

            String atualizarRA = "";

            atualizarRA = "update gcom.atendimentopublico.registroatendimento.RegistroAtendimento "
                    + "set rgat_tmultimaalteracao = :ultimaAlteracao, "
                    + "unid_idatual = :idUnidadeOrganizacionalAtual ";

            atualizarRA = atualizarRA + " where rgat_id = :idRA";

            session.createQuery(atualizarRA)
                    .setInteger("idUnidadeOrganizacionalAtual", idUnidadeOrganizacionalAtual)
                    .setTimestamp("ultimaAlteracao", new Date())
                    .setInteger("idRA", idRA)
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
	/**
	 * Pesquisa os registros de atendimento de acordo com o comando solicitado
	 * e a unidade atual informada.
	 *
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @param raEncerramentoComando
	 * @param idUnidadeAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRAExecutarComandoEncerramento(RaEncerramentoComando raEncerramentoComando, Integer idUnidadeAtual, Integer idLocalidade, Collection<Integer> idsEspecificacoes) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		String where = "";

		try {
			consulta = "SELECT ra.rgat_id as idRA, " + //0
						"step.step_id as idEspecificacaoTipo, " + //1
						"step.step_vldebito as valorDebito, " + //2
						"ra.rgat_idduplicidade as idRADuplicidade, " + //3
						"ra.rgat_tmultimaalteracao as dataUltimaAlteracao, " + //4
						"dbtp.dbtp_id as idDebitoTipo " + //5
						"FROM atendimentopublico.registro_atendimento ra " +						
						"INNER JOIN atendimentopublico.solicitacao_tipo_espec step on ra.step_id = step.step_id " +
						"LEFT JOIN faturamento.debito_tipo dbtp on step.dbtp_id = dbtp.dbtp_id " +
						"LEFT OUTER JOIN atendimentopublico.ordem_servico os on ra.rgat_id = os.rgat_id and os.orse_icprogramada = :indicadorProgramacao " + 
						"INNER JOIN atendimentopublico.tramite tramiteAtual on ra.rgat_id = tramiteAtual.rgat_id and tramiteAtual.tram_tmtramite = (SELECT max(tr.tram_tmtramite) FROM atendimentopublico.tramite tr WHERE tr.rgat_id = ra.rgat_id ) ";

			where = where
					+ " WHERE ra.loca_id = :idLocalidade " 
					+ " and substr(ra.rgat_tmregistroatendimento,1,10) between :dataAtendimentoInicial and :dataAtendimentoFinal "
					+ " and ra.rgat_cdsituacao = :situacaoPendente "
					+ " and os.orse_id is null "
					+ " and ra.unid_idatual =:idUnidadeAtual ";
			
			//caso for informado a undade de atendimento
			//adiciona o join e na clasula where a unidade de atendimento 
			if (raEncerramentoComando.getUnidadeOrganizacionalAtendimento() != null) {
				consulta = consulta + "INNER JOIN atendimentopublico.ra_unidade raUnidAtendimento on ra.rgat_id = raUnidAtendimento.rgat_id and raUnidAtendimento.attp_id = " + AtendimentoRelacaoTipo.ABRIR_REGISTRAR + " " ;
				where = where + " and raUnidAtendimento.unid_id = " + raEncerramentoComando.getUnidadeOrganizacionalAtendimento().getId();
			}
			
			if (idsEspecificacoes != null && !idsEspecificacoes.isEmpty()) {
				where = where + " and step.step_id in (:idsEspecificacoes) ";
			}
		
			//monta o sql completo
			consulta = consulta + where;
			
			//pesquisa os dados das RA's
			if (idsEspecificacoes != null && !idsEspecificacoes.isEmpty()) {
			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("idRA",Hibernate.INTEGER)
					.addScalar("idEspecificacaoTipo",Hibernate.INTEGER)
					.addScalar("valorDebito",Hibernate.BIG_DECIMAL)
					.addScalar("idRADuplicidade", Hibernate.INTEGER)
					.addScalar("dataUltimaAlteracao",Hibernate.TIMESTAMP)
					.addScalar("idDebitoTipo",Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setShort("indicadorProgramacao", OrdemServico.PROGRAMADA)
					.setDate("dataAtendimentoInicial",raEncerramentoComando.getDataAtendimentoInicial())
					.setDate("dataAtendimentoFinal",raEncerramentoComando.getDataAtendimentoFinal())
					.setShort("situacaoPendente",RegistroAtendimento.SITUACAO_PENDENTE)
					.setInteger("idUnidadeAtual",idUnidadeAtual)
					.setParameterList("idsEspecificacoes", idsEspecificacoes)
					.list();
			} else {
				retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("idRA",Hibernate.INTEGER)
					.addScalar("idEspecificacaoTipo",Hibernate.INTEGER)
					.addScalar("valorDebito",Hibernate.BIG_DECIMAL)
					.addScalar("idRADuplicidade", Hibernate.INTEGER)
					.addScalar("dataUltimaAlteracao",Hibernate.TIMESTAMP)
					.addScalar("idDebitoTipo",Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setShort("indicadorProgramacao", OrdemServico.PROGRAMADA)
					.setDate("dataAtendimentoInicial",raEncerramentoComando.getDataAtendimentoInicial())
					.setDate("dataAtendimentoFinal",raEncerramentoComando.getDataAtendimentoFinal())
					.setShort("situacaoPendente",RegistroAtendimento.SITUACAO_PENDENTE)
					.setInteger("idUnidadeAtual",idUnidadeAtual)
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
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * Pesquisa a ColeÁıes de ids das localidades que possuem Registro de Atendimento
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesRA() throws ErroRepositorioException {

		Collection  retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		try {
			consulta = "SELECT distinct(ra.loca_id) as id " +
					   "FROM atendimentopublico.registro_atendimento ra " +
					   "WHERE ra.loca_id is not null ";

			
			retorno = session.createSQLQuery(consulta)
					.addScalar("id",Hibernate.INTEGER)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * Pesquisa a ColeÁıes de ids das especificaÁıes que possuem Registro de Atendimento
	 *
	 * @author Rafael CorrÍia
	 * @date 25/11/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsEspecificacoesRAEncerramentoComando(Integer idRaEncerramentoComando) throws ErroRepositorioException {

		Collection  retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		try {
			consulta = "SELECT distinct(raEncComEspecificacoes.solicitacaoTipoEspecificacao.id) as id " +
					   "FROM RaEncerramentoComandoEspecificacoes raEncComEspecificacoes " +
					   "WHERE raEncComEspecificacoes.raEncerramentoComando.id = :idRaEncerramentoComando ";

			
			retorno = session.createQuery(consulta).setInteger("idRaEncerramentoComando", idRaEncerramentoComando)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

     /**
     * [UC0457] Encerra Ordem de ServiÁos
     *
     * @author Vivianne Sousa
     * @date 11/07/2008
     *
     * @param idRA
     * @return
     * @throws ErroRepositorioException
     */
    public Integer obterUnidadeOrigemDoUltimoTramiteRA(Integer idRA) throws ErroRepositorioException {

        Integer retorno = null;

        Session session = HibernateUtil.getSession();

        String consulta = "";

        try {
            consulta = "SELECT t.unidadeOrganizacionalOrigem.id   " +
            "FROM Tramite t " +
            "WHERE t.id =  " +
            "   (SELECT max(tram.id) " +
            "   FROM Tramite tram " +
            "   INNER JOIN tram.registroAtendimento rgat " +
            "    WHERE rgat.id = :idRA)"; 

            retorno = (Integer) session.createQuery(consulta)
                        .setInteger("idRA", idRA)
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
     * [UC0496] Filtrar Relatorio de Gestao do Registro de atendimento
     * 
     * @author Victor Cisneiros
     * @date 20/06/2008
     */
    public List<GestaoRegistroAtendimentoHelper> filtrarGestaoRA(
    		FiltrarGestaoRAHelper filtro) throws ErroRepositorioException {
    	
    	List<GestaoRegistroAtendimentoHelper> retorno = new ArrayList<GestaoRegistroAtendimentoHelper>();
       	Session session = HibernateUtil.getSession();
       	
       	String where = "";
       	
       	if (filtro.getSituacao() != null) {
       		where += " AND ra.rgat_cdsituacao = :situacao ";
       	}
       	if (filtro.getDataAtendimentoInicial() != null && filtro.getDataAtendimentoFinal() != null) {
       		where += " AND ra.rgat_tmregistroatendimento BETWEEN :dataAtendimentoInicial AND :dataAtendimentoFinal ";
       	}
       	if (filtro.getIdsUnidadeAtual() != null) { 
       		where += " AND ra.unid_idatual IN ( :id_unidade_atual ) ";
       	}
       	if (filtro.getIdsSolicitacaoTipo() != null) {
       		where += " AND step.sotp_id IN ( :idsSolicitacaoTipo ) ";
       	}
       	if (filtro.getIdsSolicitacaoTipoEspecificacao() != null) { 
       		where += " AND ra.step_id IN ( :id_solicitacao_tipo_especifica ) ";
       	}	
       	if (filtro.getIdMunicipio() != null) {
       		where += " AND ( (b1.muni_id = :idMunicipio) OR (ra.lgbr_id IS NULL AND b2.muni_id = :idMunicipio) OR (b3.muni_id = :idMunicipio) ) ";
       	}
       	if (filtro.getIdBairro() != null) {
       		where += " AND ( (b1.bair_id = :idBairro) OR (ra.lgbr_id IS NULL AND b2.bair_id = :idBairro) OR (b3.bair_id = :idBairro) ) ";
       	}
    	
    	String consulta = 
    		"SELECT " +
	    		"ra.rgat_id as id_registro, " + // 0
	    		"ra.rgat_cdsituacao as situacao, " + // 1
	    		"ra.rgat_tmencerramento as data_encerramento, " + // 2
	    		"ra.amen_id as atend_motivo_encmt, " + // 3 
	    		"ra.unid_idatual as id_unidade_atual, " + // 4
	    		"step.step_id as id_solicitacao_tipo_espec, " + // 5
	    		"step.step_dssolcttipoespec as solicitacao_tipo_espec, " + // 6
	    		"ra.rgat_dtprevistaatual as data_prevista_atual, " + // 7
	    		"( " + 
	    				"SELECT ( " + 
	    					"CASE WHEN attp.attp_id = 1 THEN 1 ELSE 0 END " + 
	    				") FROM atendimentopublico.ra_unidade raun " + 
	    					"INNER JOIN atendimentopublico.atendimento_relacao_tipo attp ON (raun.attp_id = attp.attp_id) " + 
	    				"WHERE " + 
	    					"raun.unid_id IN ( SELECT u.unid_id FROM cadastro.unidade_organizacional u WHERE u.unid_iccentralatendimento = 1 ) " + 
	    					"AND raun.rgat_id = ra.rgat_id " + 
	    					"AND attp.attp_id = 1 " + 
	    		") as flag0800 " + 
	    	"FROM atendimentopublico.registro_atendimento ra " +
	    		"INNER JOIN atendimentopublico.solicitacao_tipo_espec step ON (ra.step_id = step.step_id) " +
	    		"INNER JOIN atendimentopublico.solicitacao_tipo sotp ON (step.sotp_id = sotp.sotp_id) ";
    	if (filtro.getIdMunicipio() != null || filtro.getIdBairro() != null) {
    		consulta += 
	    		// por local de ocorrencia do registro
	    		"LEFT JOIN cadastro.logradouro_bairro lb1 ON (ra.lgbr_id = lb1.lgbr_id) " +
	    		"LEFT JOIN cadastro.bairro b1 ON (lb1.bair_id = b1.bair_id) " +
	    		// por local de ocorrencia do imovel
	    		"LEFT JOIN cadastro.imovel im ON (ra.imov_id = im.imov_id) " +
	    		"LEFT JOIN cadastro.logradouro_bairro lb2 ON (im.lgbr_id = lb2.lgbr_id) " +
	    		"LEFT JOIN cadastro.bairro b2 ON (lb2.bair_id = b2.bair_id) " +
	    		// por bairro area
	    		"LEFT JOIN cadastro.bairro_area ba ON (ra.brar_id = ba.brar_id) " +
	    		"LEFT JOIN cadastro.bairro b3 ON (ba.bair_id = b3.bair_id) ";
    	}
    	consulta +=
	    	"WHERE " +
	    		"1 = 1 " + where;
    		
    	try {
    		SQLQuery q = session.createSQLQuery(consulta);
    		
    		q.addScalar("id_registro", Hibernate.INTEGER);
    		q.addScalar("situacao", Hibernate.SHORT);
    		q.addScalar("data_encerramento", Hibernate.DATE);
    		q.addScalar("atend_motivo_encmt", Hibernate.INTEGER);
    		q.addScalar("id_unidade_atual", Hibernate.INTEGER);
    		q.addScalar("id_solicitacao_tipo_espec", Hibernate.INTEGER);
    		q.addScalar("solicitacao_tipo_espec", Hibernate.STRING);
    		q.addScalar("data_prevista_atual", Hibernate.DATE);
    		q.addScalar("flag0800", Hibernate.INTEGER);
    		
           	if (filtro.getSituacao() != null) {
           		q.setInteger("situacao", filtro.getSituacao());
           	}
           	if (filtro.getDataAtendimentoInicial() != null && filtro.getDataAtendimentoFinal() != null) {
           		q.setTimestamp("dataAtendimentoInicial", filtro.getDataAtendimentoInicial());
           		q.setTimestamp("dataAtendimentoFinal", filtro.getDataAtendimentoFinal());
           	}
           	if (filtro.getIdsUnidadeAtual() != null) { 
           		q.setParameterList("id_unidade_atual", filtro.getIdsUnidadeAtual());
           	}
           	if (filtro.getIdsSolicitacaoTipo() != null) {
           		q.setParameterList("idsSolicitacaoTipo", filtro.getIdsSolicitacaoTipo());
           	}
           	if (filtro.getIdsSolicitacaoTipoEspecificacao() != null) { 
           		q.setParameterList("id_solicitacao_tipo_especifica", filtro.getIdsSolicitacaoTipoEspecificacao());
           	}
           	if (filtro.getIdMunicipio() != null) {
           		q.setInteger("idMunicipio", filtro.getIdMunicipio());
           	}
           	if (filtro.getIdBairro() != null) {
           		q.setInteger("idBairro", filtro.getIdBairro());
           	}
    		
    		for (Object[] linha : (List<Object[]>) q.list()) {
    			GestaoRegistroAtendimentoHelper registro = new GestaoRegistroAtendimentoHelper();
    			
    			if (linha[0] != null) registro.setIdRegistroAtendimento((Integer) linha[0]);
    			if (linha[1] != null) registro.setSituacao((Short) linha[1]);
    			if (linha[2] != null) registro.setDataEncerramento((Date) linha[2]);
    			if (linha[3] != null) registro.setIdAtendimentoMotivoEncerramento((Integer) linha[3]);
    			if (linha[4] != null) registro.setIdUnidadeAtual((Integer) linha[4]);
    			if (linha[5] != null) registro.setIdSolicitacaoTipoEspecificacao((Integer) linha[5]);
    			if (linha[6] != null) registro.setDescricaoSolicitacaoTipoEspecificacao((String) linha[6]);
    			if (linha[7] != null) registro.setDataPrevistaAtual((Date) linha[7]);
    			
    			Integer int0800 = (Integer) linha[8];
    			if (int0800 != null && int0800.equals(1)) {
    				registro.setFlag0800(true);
    			} else {
    				registro.setFlag0800(false);
    			}

    			retorno.add(registro);
    		}
    	} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
    }
    
    /**
     * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
     * 
     * @author Victor Cisneiros
     * @date 14/11/2008
     */
    public List<GestaoRegistroAtendimentoHelper> filtrarRelatorioResumoSolicitacoesRAPorUnidade(
    		FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper filtro) throws ErroRepositorioException {
    	
    	List<GestaoRegistroAtendimentoHelper> retorno = new ArrayList<GestaoRegistroAtendimentoHelper>();
       	Session session = HibernateUtil.getSession();
       	
       	String where = "";
       	
       	if (filtro.getSituacao() != null) {
       		where += " AND ra.rgat_cdsituacao = :situacao ";
       	}
       	if (filtro.getDataAtendimentoInicial() != null && filtro.getDataAtendimentoFinal() != null) {
       		where += " AND rau.raun_tmultimaalteracao BETWEEN :dataAtendimentoInicial AND :dataAtendimentoFinal ";
       	}
       	if (filtro.getIdsUnidadeAtual() != null) { 
       		where += " AND rau.unid_id IN ( :idsUnidadeAtual ) ";
       	}
       	if (filtro.getIdsSolicitacaoTipo() != null) {
       		where += " AND step.sotp_id IN ( :idsSolicitacaoTipo ) ";
       	}
       	if (filtro.getIdsSolicitacaoTipoEspecificacao() != null) { 
       		where += " AND ra.step_id IN ( :idsSolicitacaoTipoEspec ) ";
       	}	
       	if (filtro.getIdMunicipio() != null) {
       		where += " AND ( (b1.muni_id = :idMunicipio) OR (ra.lgbr_id IS NULL AND b2.muni_id = :idMunicipio) OR (b3.muni_id = :idMunicipio) ) ";
       	}
       	if (filtro.getIdBairro() != null) {
       		where += " AND ( (b1.bair_id = :idBairro) OR (ra.lgbr_id IS NULL AND b2.bair_id = :idBairro) OR (b3.bair_id = :idBairro) ) ";
       	}
        
    	String consulta = 
    		"SELECT " +
	    		"distinct(ra.rgat_id) as id_registro, " + // 0
	    		"ra.rgat_cdsituacao as situacao, " + // 1
	    		"ra.rgat_tmencerramento as data_encerramento, " + // 2
	    		"ra.amen_id as atend_motivo_encmt, " + // 3 
	    		"rau.unid_id as id_unidade, " + // 4
	    		"step.step_id as id_solicitacao_tipo_espec, " + // 5
	    		"step.step_dssolcttipoespec as solicitacao_tipo_espec, " + // 6
	    		"ra.rgat_dtprevistaatual as data_prevista_atual " + // 7
	    	"FROM atendimentopublico.ra_unidade rau " +
	    		"INNER JOIN atendimentopublico.registro_atendimento ra ON (rau.rgat_id = ra.rgat_id) " +
	    		"INNER JOIN atendimentopublico.solicitacao_tipo_espec step ON (ra.step_id = step.step_id) " +
	    		"INNER JOIN atendimentopublico.solicitacao_tipo sotp ON (step.sotp_id = sotp.sotp_id) ";
    	if (filtro.getIdMunicipio() != null || filtro.getIdBairro() != null) {
    		consulta += 
	    		// por local de ocorrencia do registro
	    		"LEFT JOIN cadastro.logradouro_bairro lb1 ON (ra.lgbr_id = lb1.lgbr_id) " +
	    		"LEFT JOIN cadastro.bairro b1 ON (lb1.bair_id = b1.bair_id) " +
	    		// por local de ocorrencia do imovel
	    		"LEFT JOIN cadastro.imovel im ON (ra.imov_id = im.imov_id) " +
	    		"LEFT JOIN cadastro.logradouro_bairro lb2 ON (im.lgbr_id = lb2.lgbr_id) " +
	    		"LEFT JOIN cadastro.bairro b2 ON (lb2.bair_id = b2.bair_id) " +
	    		// por bairro area
	    		"LEFT JOIN cadastro.bairro_area ba ON (ra.brar_id = ba.brar_id) " +
	    		"LEFT JOIN cadastro.bairro b3 ON (ba.bair_id = b3.bair_id) ";
    	}
    	consulta +=
	    	"WHERE rau.attp_id = 1 " + where;
    	 
    	try {
    		SQLQuery q = session.createSQLQuery(consulta);
    		q.addScalar("id_registro", Hibernate.INTEGER);
    		q.addScalar("situacao", Hibernate.SHORT);
    		q.addScalar("data_encerramento", Hibernate.DATE);
    		q.addScalar("atend_motivo_encmt", Hibernate.INTEGER);
    		q.addScalar("id_unidade", Hibernate.INTEGER);
    		q.addScalar("id_solicitacao_tipo_espec", Hibernate.INTEGER);
    		q.addScalar("solicitacao_tipo_espec", Hibernate.STRING);
    		q.addScalar("data_prevista_atual", Hibernate.DATE);
    		
           	if (filtro.getSituacao() != null) {
           		q.setInteger("situacao", filtro.getSituacao());
           	}
           	if (filtro.getDataAtendimentoInicial() != null && filtro.getDataAtendimentoFinal() != null) {
           		q.setTimestamp("dataAtendimentoInicial", filtro.getDataAtendimentoInicial());
           		q.setTimestamp("dataAtendimentoFinal", filtro.getDataAtendimentoFinal());
           	}
           	if (filtro.getIdsUnidadeAtual() != null) { 
           		q.setParameterList("idsUnidadeAtual", filtro.getIdsUnidadeAtual());
           	}
           	if (filtro.getIdsSolicitacaoTipo() != null) {
           		q.setParameterList("idsSolicitacaoTipo", filtro.getIdsSolicitacaoTipo());
           	}
           	if (filtro.getIdsSolicitacaoTipoEspecificacao() != null) { 
           		q.setParameterList("idsSolicitacaoTipoEspec", filtro.getIdsSolicitacaoTipoEspecificacao());
           	}
           	if (filtro.getIdMunicipio() != null) {
           		q.setInteger("idMunicipio", filtro.getIdMunicipio());
           	}
           	if (filtro.getIdBairro() != null) {
           		q.setInteger("idBairro", filtro.getIdBairro());
           	}
    		
    		for (Object[] linha : (List<Object[]>) q.list()) {
    			GestaoRegistroAtendimentoHelper registro = new GestaoRegistroAtendimentoHelper();
    			
    			if (linha[0] != null) registro.setIdRegistroAtendimento((Integer) linha[0]);
    			if (linha[1] != null) registro.setSituacao((Short) linha[1]);
    			if (linha[2] != null) registro.setDataEncerramento((Date) linha[2]);
    			if (linha[3] != null) registro.setIdAtendimentoMotivoEncerramento((Integer) linha[3]);
    			if (linha[4] != null) registro.setIdUnidadeAtual((Integer) linha[4]);
    			if (linha[5] != null) registro.setIdSolicitacaoTipoEspecificacao((Integer) linha[5]);
    			if (linha[6] != null) registro.setDescricaoSolicitacaoTipoEspecificacao((String) linha[6]);
    			if (linha[7] != null) registro.setDataPrevistaAtual((Date) linha[7]);

    			retorno.add(registro);
    		}
    	} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
    }
    
    
    /**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
    //@Override Metodo sobrescrito na classe RepositorioRegistroAtendimentoPostgresHBM
    public Integer pesquisarSequencialProtocoloAtendimento() throws ErroRepositorioException {

        Session session = HibernateUtil.getSession();

        Integer retorno = null;
        String consulta = null;
        
        try {
        	String dialect = HibernateUtil.getDialect();
    		
    		if (dialect.toUpperCase().contains("ORACLE")){
    			consulta = "select atendimentopublico.seq_ra_protocolo.nextval as sequencial from dual ";
    		} else {
    			consulta = "select nextval('atendimentopublico.seq_ra_protocolo') as sequencial ";
    		}
    		
            retorno = (Integer) session.createSQLQuery(consulta).addScalar("sequencial", Hibernate.INTEGER)
            		.uniqueResult();

        } catch (HibernateException e) {
            // levanta a exeÁ„oo para a PrÛxma camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a Sess„o
            HibernateUtil.closeSession(session);
        }

        return retorno;

    }
    
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * Pesquisar o n˙mero de protocolo do RA
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public String pesquisarProtocoloAtendimentoPorRA(Integer idRegistroAtendimento) 
		throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = " select raso.numeroProtocoloAtendimento"
				+ " from RegistroAtendimentoSolicitante raso"
				+ " inner join raso.registroAtendimento rgat"
				+ " where rgat.id  = :idRegistroAtendimento and raso.indicadorSolicitantePrincipal = :indicadorSolicitantePrincipal";

		retorno = (String) session.createQuery(consulta).setInteger(
				"idRegistroAtendimento", idRegistroAtendimento).setShort(
				"indicadorSolicitantePrincipal", ConstantesSistema.SIM).setMaxResults(1)
				.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * mÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection getImoveisResumoRegistroAtendimentoPorAno(int idLocalidade,
			Integer anoMesReferencia, Integer dtAtual) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = 
				"select distinct" + 
				"	imo.id, " + //0
				"	case when ( not imo is null ) then " + 
				"		locPorImo.gerenciaRegional.id " + 
				"	else " +
				"		locPorRa.gerenciaRegional.id " + 
				"	end as greg_id, " +//1
				
				"	case when ( not imo is null ) then " + 
				"		locPorImo.unidadeNegocio.id " + 
				"	else " +
				"		locPorRa.unidadeNegocio.id " + 
				"	end as uneg_id, " +//2
				
				"	case when ( not imo is null ) then " + 
				"		locPorImo.id " +
				" 	else " +
				"		locPorRa.id " + 
				"	end as loca_id, " +//3
				
				"	case when ( not imo is null ) then " + 
				"		locPorImo.localidade.id " + 
				"	else " +
				"		locPorRa.localidade.id " + 
				"	end as loca_cdelo, " + //4
				
				"	case when ( not imo is null ) then " + 
				"		scom.id " + 
				"	else " +
				"		null " + 
				"	end as stcm_id, " + //5
				
//				"	case when ( not imo is null ) then " + 
//				"		quad.rota.id " + 
//				"	else " + 
//				"		null " + 
//				"	end as rota_id, " + 
				
//				"	case when ( not imo is null ) then " + 
//				"		quad.id " + 
//				"	else " + 
//				"		null " + 
//				"	end as qdra_id, " + 
				
				"	case when ( not imo is null ) then " + 
				"		scom.codigo " + 
				"	else " + 
				"		null " + 
				"	end as rera_cdsetorcomercial, " + //6
				
//				"	case when ( not imo is null ) then " + 
//				"		quad.numeroQuadra " + 
//				"	else " + 
//				"		null " + 
//				"	end as rera_nnquadra, " + 
				
				"	case when ( not imo is null ) then " + 
				"		imo.imovelPerfil.id " + 
				"	else " + 
				"		null " + 
				"	end as iper_id, " + //7
				
				"	case when ( not imo is null ) then " + 
				"		imo.ligacaoAguaSituacao.id " + 
				"	else " + 
				"		null " + 
				"	end as last_id, " + //8
				
				"	case when ( not imo is null ) then " + 
				"		imo.ligacaoEsgotoSituacao.id " + 
				"	else " + 
				"		null " + 
				"	end as lest_id, " + //9
				
				"	case when ( not imo is null ) then " + 
				"		plAgua.ligacaoAguaPerfil.id " + 
				"	else " + 
				"		null " + 
				"	end as lapf_id, " + //10
				
				"	case when ( not imo is null ) then " + 
				"		plEsgoto.ligacaoEsgotoPerfil.id " + 
				"	else " + 
				"		null " + 
				"	end as lepf_id, " + //11
				
				"	ra.indicadorAtendimentoOnline, " + //12
				"	soltipo.id, " + //13
				"	solTipEsp.id, " + //14
				"	ra.meioSolicitacao.id, " + //15
				
				"	case when (cast((substring(ra.registroAtendimento, 1, 4) || " +
	  			"			         substring(ra.registroAtendimento, 6, 2)) as integer) = :anoMesReferencia) then " + 
	  			"		1 " +
	  			"	else " +
	  			"		0 " +
	  			"	end as qtGeradas, " + //16
	  			
	  			"	case when ( ra.codigoSituacao = 1 ) then " + 
	  			"		case when ( cast(substring(ra.dataPrevistaOriginal, 1, 4) || " +
	  			"						 substring(ra.dataPrevistaOriginal, 6, 2) || " +
	  			"		 				 substring(ra.dataPrevistaOriginal, 9, 2) as integer) >= :dtAtual ) then " +
	  			
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " +
	  			"	end as qtRaPendenteNoPrazo, " + //17
	  
	  			"	case when ( ra.codigoSituacao = 1 ) then " + 
	  			"		case when ( cast(substring(ra.dataPrevistaOriginal, 1, 4) || " +
	  			"					     substring(ra.dataPrevistaOriginal, 6, 2) || " +
	  			"						 substring(ra.dataPrevistaOriginal, 9, 2) as integer) < :dtAtual ) then " +
	  			
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " + 
	  			"		end " +
	  			"	end as qtRaPendenteForaPrazo, " + //18
	  			
	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			"		case when ( cast(ra.dataEncerramento as date) <= ra.dataPrevistaOriginal ) then " +
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " + 
	  			"	end as qtraEncerradaNoPrazo, " + //19
	  			
	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			"		case when ( cast(ra.dataEncerramento as date) > ra.dataPrevistaOriginal ) then " +
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " +
	  			"	end as qtraEncerradaForaPrazo, " +//20
	  			
	  			"	case when ( ra.codigoSituacao = 3 ) then " + 
	  			"		1 " + 
	  			"	else " + 
	  			"		0 " + 
	  			"	end as qtBloqueada, " +//21

	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			"		case when (  to_char(ra.dataEncerramento,'YYYYMMDD') <= to_char(ra.dataPrevistaOriginal,'YYYYMMDD')  ) then " +
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " + 
	  			"	end as qtRaGeradasNoMesPrazo, " + //22
	  			
	  			"	case when ( ra.codigoSituacao = 2 ) then " + 
	  			"		case when (  to_char(ra.dataEncerramento,'YYYYMMDD') > to_char(ra.dataPrevistaOriginal,'YYYYMMDD')  ) then " +
	  			
	  			"			1 " + 
	  			"		else " + 
	  			"			0 " +
	  			"		end " +
	  			"	end as qtRaGeradasNoMesForaPrazo, " + //23
	  			
	  			"	ra.id, " + //24
//	  			"   rota.codigo, " +
	  			"	ramot.id " + //25
	  			"from " + 
	  			"	gcom.atendimentopublico.registroatendimento.RegistroAtendimento ra " + 
	  			"	inner join ra.solicitacaoTipoEspecificacao solTipEsp " + 
	  			"	inner join solTipEsp.solicitacaoTipo soltipo " + 
	  			"	left join ra.localidade locPorRa " + 
	  			"	left join ra.imovel imo " + 
	  			"	left join imo.localidade locPorImo " + 
//	  			"	left join imo.quadra quad " + 
//	  			"   left join quad.rota rota " +
	  			"	left join imo.setorComercial scom " + 
	  			"	left join imo.ligacaoAgua plAgua " + 
	  			"	left join imo.ligacaoEsgoto plEsgoto " + 
	  			"	left join ra.registroAtendimentoUnidades rauni " +
	  			"	left join ra.atendimentoMotivoEncerramento ramot " +
	  			"where " + 
	  			"	locPorRa.id = :idLocalidade and " + 
	  			"	substring(ra.registroAtendimento,1,4) || substring(ra.registroAtendimento,6,2) = :anoMesReferencia";

			retorno = session.createQuery(hql)
						.setInteger("idLocalidade", idLocalidade)
						.setInteger("anoMesReferencia", anoMesReferencia)
						.setInteger("dtAtual", dtAtual).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 * 
	 * verifica a unidade anterior do registro de atendimento pelo ˙ltimo tr·mite
	 * efetuado
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 04/02/2011
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificaUnidadeAnteriorRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT unde " + "FROM Tramite tram "
					+ "INNER JOIN tram.registroAtendimento rgat "
					+ "INNER JOIN tram.unidadeOrganizacionalOrigem unde "
					+ "WHERE rgat.id = :idRegistroAtendimento "
					+ "ORDER BY tram.dataTramite desc ";


			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 09/02/2011
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public Integer verificaSolicitacaoTipoEspecificacaoRA(
			Integer idImovel) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT rgat.id "
					+ "FROM RegistroAtendimento rgat "
					+ "INNER JOIN rgat.imovel imov  "
					+ "INNER JOIN rgat.solicitacaoTipoEspecificacao step  "
					+ "WHERE imov.id = :idImovel " 
					+ "AND step.indicadorInformarContaRA = :indicadorInformarContaRA "
					+ "AND rgat.dataEncerramento is null ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.setShort("indicadorInformarContaRA",ConstantesSistema.SIM)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoConta pesquisaRegistroAtendimentoConta(
			Integer idConta,Integer idRA) throws ErroRepositorioException {

		RegistroAtendimentoConta retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT ract "
					+ "FROM RegistroAtendimentoConta ract "
					+ "INNER JOIN ract.registroAtendimento rgat  "
					+ "INNER JOIN ract.contaGeral cnta  "
					+ "WHERE rgat.id = :idRA " 
					+ "AND cnta.id = :idConta ";

			retorno = (RegistroAtendimentoConta) session.createQuery(consulta)
					.setInteger("idConta", idConta)
					.setInteger("idRA", idRA)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public ContaMotivoRetificacao pesquisaContaMotivoRetificacao(
			Integer idMotivo) throws ErroRepositorioException {

		ContaMotivoRetificacao retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT cmrt "
					+ "FROM ContaMotivoRetificacao cmrt "
					+ "WHERE cmrt.id = :idMotivo ";

			retorno = (ContaMotivoRetificacao) session.createQuery(consulta)
					.setInteger("idMotivo", idMotivo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * UC1130 - Filtrar Registro Atendimento de DevoluÁ„o de Valores
	 * 
	 * @author Vivianne Sousa, Mariana Victor
	 * @date 11/03/2011, 04/12/2012
	 */
	public Collection obterRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper, Integer numeroPagina)throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {
			consulta = "SELECT distinct(rgat) " 
					+ "FROM RegistroAtendimentoDevolucaoValores radv " 
					+ "INNER JOIN radv.registroAtendimento rgat "
					+ "WHERE radv.indicadorPagamentoDevolvido = :indicadorPagamentoDevolvido " 
					+ "and rgat.codigoSituacao = :codigoSituacao " 
					+ "and radv.codigoTipoDevolucao = :codigoTipoDevolucao ";
					
					parameters.put("codigoSituacao", RegistroAtendimento.SITUACAO_PENDENTE);
					parameters.put("codigoTipoDevolucao", helper.getCodigoTipoDevolucao());
					parameters.put("indicadorPagamentoDevolvido",ConstantesSistema.NAO);

			if(helper.getIdImovel() != null){
				consulta = consulta + " and rgat.imovel.id = :idImovel ";
				parameters.put("idImovel", helper.getIdImovel());
			}
			
			if(helper.getNumeroRA() != null){
				consulta = consulta + " and rgat.id = :numeroRA ";
				parameters.put("numeroRA", helper.getNumeroRA());
			}
			
			if(helper.getDataAtendimentoInicioFormatada() != null){
				consulta = consulta + " and rgat.registroAtendimento >= :dataAtendimentoInicio ";
				parameters.put("dataAtendimentoInicio", Util.formatarDataInicial(helper.getDataAtendimentoInicioFormatada()));
			}
			
			if(helper.getDataAtendimentoFimFormatada() != null){
				consulta = consulta + " and rgat.registroAtendimento <= :dataAtendimentoFim ";
				parameters.put("dataAtendimentoFim", Util.formatarDataFinal(helper.getDataAtendimentoFimFormatada()));
			}
			
			if (helper.getIdPerfilImovel() != null	&& !helper.getIdPerfilImovel().equals("")
				&& !helper.getIdPerfilImovel()[0].equals(""	+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				String[] idPerfilImovel = helper.getIdPerfilImovel();
				String valoresIn = "";
				for (int i = 0; i < idPerfilImovel.length; i++) {
					if (!idPerfilImovel[i].equals("")) {
						valoresIn = valoresIn + idPerfilImovel[i] + ",";
					}
				}
				if (!valoresIn.equals("")) {
					consulta = consulta + " and  rgat.imovel.imovelPerfil.id in (" + valoresIn;
					consulta = Util.removerUltimosCaracteres(consulta, 1);
					consulta = consulta + ") ";
				}
			}
			
			consulta = consulta + " order by rgat.registroAtendimento, rgat.id ";

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
			if (numeroPagina.intValue() == -1) {
				retorno = query.list();
			} else {
				retorno = query.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * UC1130 - Filtrar Registro Atendimento de DevoluÁ„o de Valores
	 * 
	 * @author Vivianne Sousa, Mariana Victor
	 * @date 14/03/2011, 04/12/2012
	 */
	public Integer obterQtdeRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper)throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {
			consulta = "SELECT (count(distinct rgat.id)) " 
					+ "FROM RegistroAtendimentoDevolucaoValores radv " 
					+ "INNER JOIN radv.registroAtendimento rgat "
					+ "WHERE radv.indicadorPagamentoDevolvido = :indicadorPagamentoDevolvido " 
					+ "and rgat.codigoSituacao = :codigoSituacao " 
					+ "and radv.codigoTipoDevolucao = :codigoTipoDevolucao ";
					
					parameters.put("codigoSituacao", RegistroAtendimento.SITUACAO_PENDENTE);
					parameters.put("codigoTipoDevolucao", helper.getCodigoTipoDevolucao());
					parameters.put("indicadorPagamentoDevolvido",ConstantesSistema.NAO);

			if(helper.getIdImovel() != null){
				consulta = consulta + " and rgat.imovel.id = :idImovel ";
				parameters.put("idImovel", helper.getIdImovel());
			}
			
			if(helper.getNumeroRA() != null){
				consulta = consulta + " and rgat.id = :numeroRA ";
				parameters.put("numeroRA", helper.getNumeroRA());
			}
			
			if(helper.getDataAtendimentoInicioFormatada() != null){
				consulta = consulta + " and rgat.registroAtendimento >= :dataAtendimentoInicio ";
				parameters.put("dataAtendimentoInicio", Util.formatarDataInicial(helper.getDataAtendimentoInicioFormatada()));
			}
			
			if(helper.getDataAtendimentoFimFormatada() != null){
				consulta = consulta + " and rgat.registroAtendimento <= :dataAtendimentoFim ";
				parameters.put("dataAtendimentoFim", Util.formatarDataFinal(helper.getDataAtendimentoFimFormatada()));
			}
			
			if (helper.getIdPerfilImovel() != null	&& !helper.getIdPerfilImovel().equals("")
				&& !helper.getIdPerfilImovel()[0].equals(""	+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				String[] idPerfilImovel = helper.getIdPerfilImovel();
				String valoresIn = "";
				for (int i = 0; i < idPerfilImovel.length; i++) {
					if (!idPerfilImovel[i].equals("")) {
						valoresIn = valoresIn + idPerfilImovel[i] + ",";
					}
				}
				if (!valoresIn.equals("")) {
					consulta = consulta + " and  rgat.imovel.imovelPerfil.id in (" + valoresIn;
					consulta = Util.removerUltimosCaracteres(consulta, 1);
					consulta = consulta + ") ";
				}
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
			
			retorno = (Integer)query.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1131] Efetuar DevoluÁ„o de Valores Pagos em Duplicidade
	 * [SB0001] ‚Äì Pesquisar os pagamentos associados ao RA
	 * 
	 * @author Vivianne Sousa
	 * @date 15/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(
			Integer idRA) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT pgto, cnta, cntaH "
					+ "FROM RegistroAtendimentoDevolucaoValores radv "
					+ "INNER JOIN rapd.registroAtendimento rgat  "
					+ "INNER JOIN rapd.pagamento pgto "
					+ "INNER JOIN rapd.contaGeral cntaGeral "
					+ "LEFT JOIN cntaGeral.conta cnta "
					+ "LEFT JOIN cntaGeral.contaHistorico cntaH "
					+ "WHERE rgat.id = :idRA " +
							" and rapd.indicadorPagamentoDevolvido = :indicadorPagamentoDevolvido" ;

			retorno = session.createQuery(consulta)
					.setInteger("idRA", idRA)
					.setShort("indicadorPagamentoDevolvido", ConstantesSistema.NAO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1131] Efetuar DevoluÁ„o de Valores Pagos em Duplicidade
	 * [SB0007] Atualiza Pagamento Devolvido
	 * 
	 * @author Vivianne Sousa
	 * @date 21/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarRegistroAtendimentoPagamentoDuplicidade(
			Integer idRa,
			Integer idPagamento)
			throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade SET "
					+ "rapd_icpagtodevolvido = :indicador, rapd_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE pgmt_id = :idPagamento and rgat_id = :idRa ";

			session.createQuery(consulta)
					.setShort("indicador",ConstantesSistema.SIM)
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idRa", idRa)
					.setInteger("idPagamento", idPagamento)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1131] Efetuar DevoluÁ„o de Valores Pagos em Duplicidade
	 * [SB0007] Atualiza Pagamento Devolvido
	 * 
	 * @author Mariana Victor
	 * @date 07/12/2012
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarRegistroAtendimentoDevolucaoValores(
			Integer idRa,
			Integer idPagamento)
			throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimentoDevolucaoValores SET "
					+ "radv_icpagtodevolvido = :indicador, radv_tmultimaalteracao = :ultimaAlteracao, radv_dtdevolucao = :dataAtual "
					+ "WHERE pgmt_id = :idPagamento and rgat_id = :idRa ";

			session.createQuery(consulta)
					.setShort("indicador",ConstantesSistema.SIM)
					.setTimestamp("ultimaAlteracao", new Date())
					.setDate("dataAtual", new Date())
					.setInteger("idRa", idRa)
					.setInteger("idPagamento", idPagamento)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}


	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0037] ‚Äì Define Unidade Destino por SituaÁ„o de CobranÁa.
	 * 
	 * @author Mariana Victor
	 * @date 05/04/2011
	 * 
	 * @param idEspecificacao
	 * @param idImovel
	 * @return Collection
	 */
	public Object[] pesquisarUnidadeDestinoEspecificacaoRA(
			Integer idEspecificacao, Integer idImovel) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = " SELECT DISTINCT unid.unid_id AS idUnidade, unid.unid_dsunidade AS descricaoUnidade "
					+ " FROM atendimentopublico.espec_unid_cobr esuc "
					+ " INNER JOIN cadastro.imovel_cobranca_situacao iscb ON iscb.cbst_id = esuc.cbst_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON unid.unid_id = esuc.unid_id "
					+ " WHERE iscb.imov_id = :idImovel and iscb.iscb_dtretiradacobranca is null"
					+ " AND esuc.step_id = :idEspecificacao "
					+ " AND unid.unid_ictramite = 1 ";


			retorno = (Object[]) session.createSQLQuery(consulta)
					.addScalar("idUnidade",	Hibernate.INTEGER)
					.addScalar("descricaoUnidade",	Hibernate.STRING)
					.setInteger("idImovel", idImovel)
					.setInteger("idEspecificacao", idEspecificacao)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * 
	 * Pesquisar dados do registro de atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/05/2011
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento pesquisarDadosRegistroAtendimentoParaReiteracao(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		RegistroAtendimento retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rgat " 
//					+ "rgat.id," // 0
//					+ " step.id, " //1
//					+ " step.descricao," //2
//					+ " sotp.id, " //3
//					+ " sotp.descricao," //4
//					+ " imov.id," // 5
//					+ " rgat.dataPrevistaAtual" // 6
					+ " from RegistroAtendimento rgat "
					+ " left join fetch rgat.solicitacaoTipoEspecificacao step"
					+ " left join fetch step.solicitacaoTipo sotp"
//					+ " left join rgat.imovel imov"
					+ " where rgat.id  = :idRegistroAtendimento";

			retorno = (RegistroAtendimento) session.createQuery(consulta).setInteger(
					"idRegistroAtendimento", idRegistroAtendimento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] ‚Äì Verificar RA de urgÍncia
	 *  
	 * Pesquisar os Usu·rios da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   03/06/2010 
	 * 
	 */
	public Collection pesquisarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		Collection retorno = null;
		
		String consulta = "";
		
		consulta +="SELECT usuario.id " +
				  "FROM gcom.atendimentopublico.registroatendimento.RegistroAtendimento ra " +
				  "INNER JOIN ra.unidadeAtual unidade, " +
				   "gcom.seguranca.acesso.usuario.Usuario usuario " +
				  "WHERE usuario.unidadeOrganizacional.id = unidade.id " +
				    "AND ra.id = :registroAtendimento ";

		try {
			
			retorno = (Collection) session.createQuery(consulta).setInteger("registroAtendimento",
                    idRegistroAtendimento).list();
			
		} catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
			
		return retorno;				
	}
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0008] ‚Äì Verificar reiterÁıes do RA pelo cliente 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 * 
	 */
	public Integer verificarExistenciaClienteSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idCliente)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		String data1 = Util.recuperaDataInvertida(new Date());
		if (data1 != null && !data1.equals("") && data1.trim().length() == 8) {
			data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
					+ "-" + data1.substring(6, 8);
		}
		
		try {
			consulta = " select COUNT(*)"
					+ " from RAReiteracao rart"
					+ " left join rart.registroAtendimento rgat"
					+ " left join rart.cliente clie"
					+ " where rgat.id  = :idRegistroAtendimento AND clie.id = :idCliente " 
					+ " AND rart.ultimaAlteracao  between to_date('" + data1 + " 00:00:00','YYYY-MM-DD HH24:MI:SS') " +
							"and to_date('" + data1 + " 23:59:59','YYYY-MM-DD HH24:MI:SS') "; 

			retorno = (Integer) session.createQuery(consulta)
			.setInteger("idRegistroAtendimento", idRegistroAtendimento)
			.setInteger("idCliente", idCliente).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0008] DevoluÁ„o Verificar reiterÁıes do RA pelo cliente 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 * 
	 */
	public Integer verificarExistenciaUnidadeSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idUnidade)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		String data1 = Util.recuperaDataInvertida(new Date());
		if (data1 != null && !data1.equals("") && data1.trim().length() == 8) {
			data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
					+ "-" + data1.substring(6, 8);
		}
		
		try {
			consulta = " select COUNT(*)"
					+ " from RAReiteracao rart"
					+ " left join rart.registroAtendimento rgat"
					+ " left join rart.unidadeOrganizacional unid"
					+ " where rgat.id  = :idRegistroAtendimento AND unid.id = :idUnidade " 
					+ " AND rart.ultimaAlteracao  between to_date('" + data1 + " 00:00:00','YYYY-MM-DD HH24:MI:SS') " +
					"and to_date('" + data1 + " 23:59:59','YYYY-MM-DD HH24:MI:SS') "; 

			retorno = (Integer) session.createQuery(consulta)
			.setInteger("idRegistroAtendimento", idRegistroAtendimento)
			.setInteger("idUnidade", idUnidade).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 *  
	 * @author Vivianne Sousa
	 * @data 16/05/2011
	 */
	public Collection pesquisarRAReiteracao(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rart"
					+ " from RAReiteracao rart"
					+ " inner join rart.registroAtendimento rgat"
					+ " left join fetch rart.cliente "
					+ " left join fetch rart.unidadeOrganizacional "
					+ " where rgat.id  = :idRegistroAtendimento "
					+ " order by rart.ultimaAlteracao "; 

			retorno = (Collection) session.createQuery(consulta)
			.setInteger("idRegistroAtendimento", idRegistroAtendimento)
			.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 *  
	 * @author Vivianne Sousa
	 * @data 16/05/2011
	 */
	public Collection pesquisarRAReiteracaoFone(Integer idRAReiteracao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select rtfo"
					+ " from RAReiteracaoFone rtfo"
					+ " inner join rtfo.raReiteracao rart"
					+ " where rart.id  = :idRAReiteracao "; 

			retorno = (Collection) session.createQuery(consulta)
			.setInteger("idRAReiteracao", idRAReiteracao)
			.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * pesquisa quantidade de RestriÁoes do RA
	 *  
	 * @author Vivianne Sousa
	 * @data 18/05/2011
	 */
	public Short pesquisarQtdeReiteracaoRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		
		try {
			consulta = " select rgat.quantidadeReiteracao "
					+ " from RegistroAtendimento rgat"
					+ " where rgat.id  = :idRegistroAtendimento "; 

			retorno = (Short) session.createQuery(consulta)
			.setInteger("idRegistroAtendimento", idRegistroAtendimento)
			.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	/**
	 * [UC0428] Imprimir Registro Atendimento
	 * 
	 * @author Rodrigo Cabral
	 * @date 10/05/2011
	 */
	public Collection pesquisarDadosReiteracao(
			Integer idRA) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT rart.rart_tmultimaalteracao AS ultimaAlteracao, " + //0
					" rart.rart_nmsolicitante AS nomeSolicitante," + //1
					" clie.clie_nmcliente AS nomeCliente, " + //2
					" unid.unid_dsunidade AS descricaoUnidade," + //3
					" rart.clie_id AS idCliente," + //4
					" rart.unid_id AS idUnidade," + //5
					" rtfo.rtfo_cdddd AS ddd," + //6
					" rtfo.rtfo_nnfone AS fone," + //7
					" rtfo.rtfo_nnfoneramal AS ramal " //8
				+ " FROM atendimentopublico.ra_reiteracao rart "
				+ " LEFT JOIN atendimentopublico.ra_reiteracao_fone rtfo ON rtfo.rart_id = rart.rart_id and rtfo.rtfo_icfonepadrao = " + ConstantesSistema.SIM
				+ " LEFT JOIN cadastro.cliente clie ON clie.clie_id = rart.clie_id "
				+ " LEFT JOIN cadastro.unidade_organizacional unid ON unid.unid_id = rart.unid_id "
				+ " WHERE rart.rgat_id = :idRA "
				+ " ORDER BY rart.rart_tmultimaalteracao desc";

			retorno = session.createSQLQuery(consulta)
					.addScalar("ultimaAlteracao",	Hibernate.TIMESTAMP)
					.addScalar("nomeSolicitante",	Hibernate.STRING)
					.addScalar("nomeCliente",	Hibernate.STRING)
					.addScalar("descricaoUnidade",	Hibernate.STRING)
					.addScalar("idCliente",	Hibernate.INTEGER)
					.addScalar("idUnidade",	Hibernate.INTEGER)
					.addScalar("ddd",	Hibernate.STRING)
					.addScalar("fone",	Hibernate.STRING)
					.addScalar("ramal",	Hibernate.STRING)
					.setInteger("idRA", idRA)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public RegistroAtendimentoSolicitante pesquisarDadosEnvioEmailPesquisaPortal(int idRegistroAtendimentoSolicitante) throws ErroRepositorioException {
		RegistroAtendimentoSolicitante retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT RASO_ICENVIOEMAILPESQUISA, " + //0
					" RASO_DSEMAIL FROM " + //1
					"  ATENDIMENTOPUBLICO.RA_SOLICITANTE WHERE RASO_ID = :idRegistroAtendimentoSolicitante ";
			
			Object[] dadosEmail = (Object[]) session.createSQLQuery(consulta)
					.addScalar("RASO_ICENVIOEMAILPESQUISA",	Hibernate.SHORT)
					.addScalar("RASO_DSEMAIL",	Hibernate.STRING)
					.setInteger("idRegistroAtendimentoSolicitante", idRegistroAtendimentoSolicitante)
					.uniqueResult();
			
			if(dadosEmail != null && dadosEmail.length == 2){
				retorno = new RegistroAtendimentoSolicitante();
				retorno.setIndicadorEnvioEmailPesquisa(new Short(dadosEmail[0].toString()));
				retorno.setEnderecoEmail(dadosEmail[1]+"");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1181] Registrar Informacoes de Pesquisa de Satisfacao
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public boolean verificaExistenciaQuestionarioSatisfacaoRespondido(Integer idRA)
			throws ErroRepositorioException {
		
		boolean retorno = false;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = "SELECT COUNT(*) " + "FROM QuestionarioSatisfacaoCliente pqsa "
					+ "INNER JOIN pqsa.registroAtendimento rgat  "
					+ "WHERE rgat.id = :idRA";
		
			Integer quantidade = (Integer) session.createQuery(consulta).setInteger(
					"idRA", idRA).setMaxResults(1).uniqueResult();
			
			if(quantidade != null && quantidade.intValue() > 0){
				retorno = true;
			}
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1227] Atualizar Ordens ServiÁos de Acompanhamento de Celular
	 * 
	 * @autor S·vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosRA(
			Integer idRa,String descricaoPontoreferencia, String numeroImovel)
			throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimento SET "
					+ "rgat_dspontoreferencia = :descricaoPontoreferencia,rgat_nnimovel = :numeroImovel, rgat_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE rgat_id = :idRa ";

			session.createQuery(consulta)
					.setString("descricaoPontoreferencia",descricaoPontoreferencia)
					.setString("numeroImovel",numeroImovel)
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idRa", idRa)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Pesquisar Id Tipo Solicitacao por RA [UC1249] Filtro OS Encerradas por Acompanhamento de ServiÁos
	 * 
	 * @author Fernanda Almeida
	 * @date 09/11/2011
	 * 
	 * @param idRA
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarTipoSolicitacao(Integer idRA) throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT registroAtendimento.id , " // 0
					+ " solicitacaoTipoEspecificacao.solicitacaoTipo.id " // 1					
					+ "from RegistroAtendimento registroAtendimento "
					+ "left join registroAtendimento.solicitacaoTipoEspecificacao solicitacaoTipoEspecificacao "

					+ "where registroAtendimento.id = :idRegistroAtendimento AND " +
					"solicitacaoTipoEspecificacao.id = registroAtendimento.solicitacaoTipoEspecificacao.id";

			
			retorno = (Object[]) session.createQuery(consulta).setInteger(
				"idRegistroAtendimento", idRA).setMaxResults(1)
				.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * RM5924 - Adicionar arquivo de procedimentos operacionais Pdrıes POPs
	 * [UC1258] Consultar Procedimento Operacional Padr„o
	 * 
	 * @autorAmÈlia Pessoa
	 * @date 20/12/2011
	 * @throws ErroRepositorioException
	 */
	public Collection<ArquivoProcedimentoOperacionalPadrao> pesquisarArquivoProcedimentoOperacionalPadrao(
			FiltrarArquivoProcedimentoOperacionalPadraoHelper helper)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta=null;
		String where="";
		try {
		consulta = 
			"SELECT pop "+
			"FROM ArquivoProcedimentoOperacionalPadrao pop \n" +
			"left join FETCH pop.solicitacaoTipoEspecificacao ste \n"+
			"left join FETCH ste.solicitacaoTipo st \n"; 
			  
			if (helper.getIdEspecificacao()!= null && !helper.getIdEspecificacao().equals("")) {
				where += " where ste.id = :idEspecificacao \n";
			}
			if (helper.getIdTipoSolicitacao()!= null && !helper.getIdTipoSolicitacao().equals("")) {
				if(!where.equals("")){
					where += " and st.id = :idTipoSolicitacao \n";
				} else {
					where += " where st.id = :idTipoSolicitacao \n";
				}
			}
			if (helper.getIndicadorProcedimento()!= null && !helper.getIndicadorProcedimento().equals("3")) {
				if(!where.equals("")){
					where += "and ste.servicoTipo.codigoServicoTipo = :servicoTipo \n";
				} else {
					where += " where  ste.servicoTipo.codigoServicoTipo = :servicoTipo \n";
				}
			}
			
			if(!where.equals("")){
				consulta+=where;
			}
			
			Query query = session.createQuery(consulta);
			if (helper.getIdEspecificacao()!= null && !helper.getIdEspecificacao().equals("")) {
				query.setInteger("idEspecificacao",Integer.parseInt(helper.getIdEspecificacao()));
			}
			if (helper.getIdTipoSolicitacao()!= null && !helper.getIdTipoSolicitacao().equals("")) {
				query.setInteger("idTipoSolicitacao",Integer.parseInt(helper.getIdTipoSolicitacao()));
			}
			if (helper.getIndicadorProcedimento()!= null && !helper.getIndicadorProcedimento().equals("3")) {
				query.setString("servicoTipo", helper.getIndicadorProcedimento());
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
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * 1.1.1.	Caso a quadra da matricula do ImÛvel esteja associada 
	 * a uma Divis„o de esgoto( DVES_ID e DVES_DSDIVISAOESGOTO da tabela
	 *  DIVISAO_ESGOTO com DVES_ID=DVES_ID da tabela SISTEMA_ESGOTO com 
	 *  SESG_ID=SESG_ID da tabela BACIA com BACI_ID=BACI_ID da tabela 
	 *  QUADRA com QDRA_ID = QDRA_ID da tabela CADASTRO.IMOVEL onde 
	 *  IMOV_ID = <<matricula do ImÛvel informado>> .
	 * 
	 * [SB0006] Obter Divis„o de Esgoto
	 * 
	 * @author Fernanda Almeida
	 * @date 23/03/2012
	 * 
	 * @param idImovel
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto obterDivisaoEsgotoQuadra(Integer idImovel)
			throws ErroRepositorioException {

		DivisaoEsgoto retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT dves " + "FROM Imovel imov "
					+ "INNER JOIN imov.quadra qdra "
					+ "INNER JOIN qdra.bacia baci  "
					+ "INNER JOIN baci.sistemaEsgoto sesg  "
					+ "INNER JOIN sesg.divisaoEsgoto dves "
					+ "WHERE imov.id = :idImovel ";

			retorno = (DivisaoEsgoto) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * 1.1.2.	Caso a localidade da ImÛvel esteja° ligada a uma Divis„o de 
	 * esgoto(  DVES_ID e DVES_DSDIVISAOESGOTO da tabela DIVISAO_ESGOTO com 
	 * UNID_ID = UNID_ID da tabela UNIDADE_ORGANIZACIONAL com LOCA_ID = LOCA_ID 
	 * da tabela IMOVEL com IMOV_ID = <<matricula do ImÛvel informado>>.
	 * 
	 * [SB0006] Obter Divis„o de Esgoto
	 * 
	  * @author Fernanda Almeida
	 * @date 23/03/2012
	 * 
	 * @param idImovel
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto obterDivisaoEsgotoImovelLocalidade(Integer idImovel)
			throws ErroRepositorioException {

		DivisaoEsgoto retorno = null;
		
		Object[] query = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
						
			consulta = "Select dves.dves_id as idDivisao, dves.dves_dsdivisaoesgoto as descricao " 
					 + "FROM cadastro.imovel imov "
					 + "INNER JOIN cadastro.localidade loca ON imov.loca_id = loca.loca_id "
					 + "INNER JOIN cadastro.unidade_organizacional unid ON imov.loca_id = unid.loca_id "
					 + "INNER JOIN operacional.divisao_esgoto dves ON dves.unid_id = unid.unid_id "
					 + "WHERE imov.imov_id = :idImovel ";

			query = (Object[] ) session.createSQLQuery(consulta)
					.addScalar("idDivisao", Hibernate.INTEGER)					
					.addScalar("descricao", Hibernate.STRING)
					.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

			if(query != null){
				retorno = new DivisaoEsgoto();
				if (query[0] != null) retorno.setId((Integer) query[0]);
				if (query[1] != null) retorno.setDescricao((String) query[1]);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	* 1.2.1.	Caso a localidade informada no EndereÁo da RA 
	 * esteja° ligada a uma Divis„o de esgoto (  DVES_ID e DVES_DSDIVISAOESGOTO 
	 * da tabela DIVISAO_ESGOTO com UNID_ID = UNID_ID da tabela
	 *  UNIDADE_ORGANIZACIONAL com LOCA_ID = <<localidade informada no EndereÁo>>. * 
	 *  
	 * [SB0006] Obter Divis„o de Esgoto
	 * 
	  * @author Fernanda Almeida
	 * @date 23/03/2012
	 * 
	 * @param idImovel
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto obterDivisaoEsgotoLocalidade(Integer idLocalidade)
			throws ErroRepositorioException {

		DivisaoEsgoto retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT dves FROM DivisaoEsgoto dves "
					+ "INNER JOIN dves.unidadeOrganizacional unid "
					+ "INNER JOIN unid.localidade loca "
					+ "WHERE loca.id = :idLocalidade ";

			retorno = (DivisaoEsgoto) session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * 1.1.1.	Caso a quadra da matricula do ImÛvel esteja associada a uma 
	 * Divis„o de esgoto( UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL 
	 * com UNID_ID = UNID_ID da tabela DIVISAO_ESGOTO com DVES_ID=DVES_ID da
	 * tabela SISTEMA_ESGOTO com SESG_ID=SESG_ID da tabela BACIA com BACI_ID=BACI_ID 
	 * da tabela QUADRA com QDRA_ID = QDRA_ID da tabela CADASTRO.IMOVEL 
	 * onde IMOV_ID = <<matricula do ImÛvel informado>> 
	 * 
	 * [SB0007] Define Unidade Destino da Divis„o de Esgotoo
	 * 
	 * @author Fernanda Almeida
	 * @date 23/03/2012
	 * 
	 * @param idImovel
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public UnidadeOrganizacional obterDivisaoEsgotoQuadraUnidade(Integer idImovel)
			throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT unid " + "FROM Imovel imov "
					+ "INNER JOIN imov.quadra qdra "
					+ "INNER JOIN qdra.bacia baci "
					+ "INNER JOIN baci.sistemaEsgoto sesg  "
					+ "INNER JOIN sesg.divisaoEsgoto dves "
					+ "INNER JOIN dves.unidadeOrganizacional unid "
					+ "WHERE imov.id = :idImovel ";

			retorno = (UnidadeOrganizacional) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();
			
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 *a unidade da localidade da matricula do ImÛvel( UNID_ID e UNID_DSUNIDADE da 
	 *tabela UNIDADE_ORGANIZACIONAL com LOCA_ID = LOCA_ID da tabela IMOVEL
	 *com IMOV_ID = <<matricula do ImÛvel informado>>
	 * 
	 * [SB0007] Define Unidade Destino da Divis„o de Esgotoo
	 * 
	 * @author Fernanda Almeida
	 * @date 23/03/2012
	 * 
	 * @param idImovel
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public UnidadeOrganizacional obterUnidadeOrganizacional(Integer idImovel)
			throws ErroRepositorioException {

		Object[] query = null;
		
		UnidadeOrganizacional retorno = null;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "Select unid_id," //0
					 + "unid.unid_dsunidade, "//1
					 + "unid.unid_idunidadetramitacao "//2
					 + "FROM cadastro.imovel imov "
					 + "INNER JOIN cadastro.unidade_organizacional unid ON imov.loca_id = unid.loca_id "
					 + "WHERE imov.imov_id = :idImovel ";

			query =  (Object[]) session.createSQLQuery(consulta)
					.addScalar("unid_id", Hibernate.INTEGER)
					.addScalar("unid_dsunidade", Hibernate.STRING)
					.addScalar("unid_idunidadetramitacao", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
			
			if(query != null){
				
				retorno = new UnidadeOrganizacional();
				
				UnidadeOrganizacional unidTramite =new UnidadeOrganizacional();
				
				
				if (query[0] != null) retorno.setId((Integer) query[0]);
				if (query[1] != null) retorno.setDescricao((String) query[1]);
				if (query[2] != null) {
					unidTramite.setId((Integer) query[2]);
					retorno.setUnidadeTramitacao(unidTramite);
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
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	* Caso a localidade seja informada no EndereÁo da RA( Selecionar UNID_ID e 
	* UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com
	*  LOCA_ID = <<localidade informada no EndereÁo>>)  * 
	 *  
	 * [SB0007] Obter Divis„o de Esgoto
	 * 
	  * @author Fernanda Almeida
	 * @date 23/03/2012
	 * 
	 * @param idLocalidade
	 * @return UnidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
	public UnidadeOrganizacional obterUnidadeOrganizacionalLocalidade(Integer idLocalidade)
			throws ErroRepositorioException {

		Object[] query = null;
		
		UnidadeOrganizacional retorno = null;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT unid_id, " 
					 + " unid_dsunidade,"
					 + " UNID_IDUNIDADETRAMITACAO from cadastro.localidade loca "
					 + "INNER JOIN cadastro.unidade_organizacional unid ON unid.loca_id = loca.loca_id " 
					 + "WHERE loca.loca_id = :idLocalidade " ;

			query =  (Object[]) session.createSQLQuery(consulta)
					.addScalar("unid_id", Hibernate.INTEGER)
					.addScalar("unid_dsunidade", Hibernate.STRING)
					.addScalar("UNID_IDUNIDADETRAMITACAO", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade).setMaxResults(1).uniqueResult();
			
			if(query != null){
				
				retorno = new UnidadeOrganizacional();
				if (query[0] != null) retorno.setId((Integer) query[0]);
				if (query[1] != null) retorno.setDescricao((String) query[1]);
				
				if (query[2] != null){
					UnidadeOrganizacional unidadeTramitacao = new UnidadeOrganizacional();
					
					unidadeTramitacao.setId( (Integer) query[2] );
					retorno.setUnidadeTramitacao( unidadeTramitacao );
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
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0037] ‚Äì Define Unidade Destino por SituaÁ„o de CobranÁa.
	 * 
	* @author Fernanda Almeida
	 * @date 23/03/2012
	 * 
	 * @param idEspecificacao
	 * @param idImovel
	 * @return Collection
	 */
	public Object[] pesquisarUnidDestinoLocalidadeSolTipoGrupo(
			Integer idSolicitacaoTipoGrupo, Integer idLocalidade,Integer idSolicitacaoTipo) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = " SELECT lstg.unid_id as unidade,sotp.sotp_id as solicitacaoTipo FROM atendimentopublico.localid_solic_tipo_grupo lstg "
				+ "   INNER JOIN cadastro.unidade_organizacional unid ON unid.unid_id= lstg.unid_id "
				+ "   INNER JOIN atendimentopublico.solicitacao_tipo sotp ON sotp.sotg_id = lstg.sotg_id"
				+ "   INNER JOIN cadastro.localidade loca ON loca.loca_id = lstg.loca_id and lstg.loca_id = :localidade"
				+ "   WHERE unid.unid_ictramite= :icTramite and lstg.sotg_id= :idSolicitacaoTipoGrupo and sotp.sotp_id = :idSolicitacaoTipo";

			retorno = (Object[]) session.createSQLQuery(consulta)
					.addScalar("unidade",	Hibernate.INTEGER)
					.addScalar("solicitacaoTipo",	Hibernate.INTEGER)
					.setInteger("localidade", idLocalidade)
					.setInteger("idSolicitacaoTipo", idSolicitacaoTipo)
					.setInteger("idSolicitacaoTipoGrupo", idSolicitacaoTipoGrupo)
					.setInteger("icTramite", ConstantesSistema.SIM)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**	 
	 * 
	 * [UC0366] Inserir Registro de Atendimento
	 * [SB0038] Verificar instalaÁ„o/SubstituiÁ„o de HidrÙmetro para ImÛvel em SituaÁ„o especial de faturamento
	 *  
	 * @author Hugo Azevedo
	 * @date 17/09/2012
	 * 
	 * */	
	public Integer verificarInstalacaoSubstituicaoHidr(Integer idEspecificacao) throws ErroRepositorioException{
		
		Integer retorno = new Integer(0);
		Session session = HibernateUtil.getSession();
		
		try{
			
			String sql = " select count(*) as qtd " +
					     " from atendimentopublico.solicitacao_tipo_espec step " +
					     " where step.step_id = :idEspecificacao " +
					     " and (step.step_ictipoinstalacaohidr = 1 or step.step_ictiposubstituicaohidr = 1) ";
			
			retorno = (Integer)session.createSQLQuery(sql)
									  .addScalar("qtd", Hibernate.INTEGER)
									  .setInteger("idEspecificacao", idEspecificacao)
									  .setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**	 
	 * 
	 * [UC0366] Inserir Registro de Atendimento
	 * [SB0038] Verificar instalaÁ„o/SubstituiÁ„o de HidrÙmetro para ImÛvel em SituaÁ„o especial de faturamento
	 *  
	 * @author Hugo Azevedo
	 * @date 17/09/2012
	 * 
	 * */	
	public FaturamentoSituacaoTipo obterFaturamentoSituacaoTipoImovel(Integer idImovel) throws ErroRepositorioException{
		
		Imovel imov = null;
		Session session = HibernateUtil.getSession();
		
		try{
			
			String sql = " select imov " +
					     " from Imovel imov " +
					     " inner join fetch imov.faturamentoSituacaoTipo fatu " +
					     " where imov.id = :idImovel ";
					    
			imov = (Imovel)session.createQuery(sql)
													  .setInteger("idImovel", idImovel)
													  .setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		if(imov != null)
			return imov.getFaturamentoSituacaoTipo();
		else
			return null;
		
	}
	
	/**
	 * [UC0449] Consultar Registro Atendimento do ImÛvel
	 * 
	 * @author Davi Menezes
	 * @date 17/10/2012
	 * 
	 */
	public Collection consultarRegistroAtendimentoConta(Integer idImovel) throws ErroRepositorioException{
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT distinct registroAtendimento.id, " //0
					 +	"solicitacaoTipo.descricao, " //1
					 +	"solicitacaoTipoEspecificacao.descricao, " //2
					 +	"registroAtendimento.registroAtendimento, " //3
					 +	"registroAtendimento.dataEncerramento, " //4
					 +	"atendimento.id, " //5
					 +	"atendimento.descricao " //6
					 +	"from Conta conta "
					 +	"inner join conta.imovel imovel "
					 +	"inner join conta.debitoCreditoSituacaoAtual debitoCreditoSituacaoAtual "
					 +	"inner join conta.registroAtendimento registroAtendimento "
					 + 	"left join registroAtendimento.solicitacaoTipoEspecificacao solicitacaoTipoEspecificacao "
					 + 	"left join solicitacaoTipoEspecificacao.solicitacaoTipo solicitacaoTipo "
					 +	"left join registroAtendimento.atendimentoMotivoEncerramento atendimento "
					 +	"where imovel.id = :idImovel "
					 +	"and debitoCreditoSituacaoAtual.id in (:retificada, :incluida) ";
			
			Query query = session.createQuery(consulta);

			query.setInteger("idImovel", idImovel.intValue());
			
			query.setInteger("retificada", DebitoCreditoSituacao.RETIFICADA);
			
			query.setInteger("incluida", DebitoCreditoSituacao.INCLUIDA);
			
			retorno = query.list();
			
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Collection consultarRegistroAtendimentoContaHistorico(Integer idImovel) throws ErroRepositorioException{
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT distinct registroAtendimento.id, " //0
					 +	"solicitacaoTipo.descricao, " //1
					 +	"solicitacaoTipoEspecificacao.descricao, " //2
					 +	"registroAtendimento.registroAtendimento, " //3
					 +	"registroAtendimento.dataEncerramento, " //4
					 +	"atendimento.id, " //5
					 +	"atendimento.descricao " //6
					 +	"from ContaHistorico contaHistorico "
					 +	"inner join contaHistorico.imovel imovel "
					 +	"inner join contaHistorico.debitoCreditoSituacaoAtual debitoCreditoSituacaoAtual "
					 +	"inner join contaHistorico.registroAtendimento registroAtendimento "
					 + 	"left join registroAtendimento.solicitacaoTipoEspecificacao solicitacaoTipoEspecificacao "
					 + 	"left join solicitacaoTipoEspecificacao.solicitacaoTipo solicitacaoTipo "
					 +	"left join registroAtendimento.atendimentoMotivoEncerramento atendimento "
					 +	"where imovel.id = :idImovel "
					 +	"and debitoCreditoSituacaoAtual.id in (:retificada, :incluida) ";
			
			Query query = session.createQuery(consulta);

			query.setInteger("idImovel", idImovel.intValue());
			
			query.setInteger("retificada", DebitoCreditoSituacao.RETIFICADA);
			
			query.setInteger("incluida", DebitoCreditoSituacao.INCLUIDA);
			
			retorno = query.list();
			
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * UC1130 - Filtrar Registro Atendimento de DevoluÁ„o de Valores
	 * 
	 * 1.1.	  Tipo de especificaÁ„o 
	 * 
	 * @author Mariana Victor
	 * @date 04/12/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<SolicitacaoTipoEspecificacao> pesquisarSolicitacaoTipoEspecificacao()
			throws ErroRepositorioException {

		Collection<SolicitacaoTipoEspecificacao> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT step "
					+ " FROM SolicitacaoTipoEspecificacao step "
					+ " WHERE step.indicadorUso = :indicadorSim AND "
					+ "  (step.indicadorInformarPagamentoDuplicidade = :indicadorSim "
					+ "	  or step.indicadorInformarDocumentoPagamentoAMaior = :indicadorSim "
					+ "	  or step.indicadorInformarDocumentoPagamentoIndevido = :indicadorSim) "
					+ " ORDER BY step.descricao ";

			retorno = session.createQuery(consulta)
					.setShort("indicadorSim", ConstantesSistema.SIM)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0435] Encerrar Registro Atendimento
	 * [UC1396] Informar Dados para DevoluÁ„o de Valor Faturado e Pago Indevidamente
	 * 
	 * @author Fernanda
	 * @date 17/12/2012
	 * 
	 * @param idRA 
	 * 
	 */
	public Collection<MensagemSms> pesquisarMensagemSmsEncerrar(Integer idRA) throws ErroRepositorioException{
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT msg from MensagemSms msg " //6
					 +	"inner join msg.registroAtendimento rgat "
					 +	"WHERE rgat.id = :idRA "
					 +	"and msg.smsTipo.id IN (:idRAInserida, :idRAExistente) ";
			
			Query query = session.createQuery(consulta);

			query.setInteger("idRA", idRA);
			
			query.setInteger("idRAInserida", SmsTipo.ENVIO_RA_INSERIDA);
			
			query.setInteger("idRAExistente", SmsTipo.ENVIO_RA_EXISTENTE);
			
			retorno = query.list();
			
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1400] Atualizar Numero Protocolo
	 * 
	 * @author Carlos Chaves
	 * @date 12/13/2012
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarNumeroProtocoloAtendimento(String numeroProtocoAtendimento, Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {
			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante SET "
					+ "raso_nnprotocoloatendimento = :numeroProtocoloAtendimento, raso_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE raso_id = :idRegistroAtendimentoSolicitante ";

			session.createQuery(consulta)
					.setString("numeroProtocoloAtendimento", numeroProtocoAtendimento)		
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idRegistroAtendimentoSolicitante",idRegistroAtendimentoSolicitante)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC 1366] Alterar VÌnculo Clientes com ImÛvel e Contas
	 * [IT 0006] Obter RA especifica de VÌnculo
	 * 
	 * @author Davi Menezes
	 * @date 26/12/2012
	 */
	public Integer obterRAVinculo(Integer idImovel) throws ErroRepositorioException {
		Integer idRA = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"select rgat_id as idRa "
					 +	"from ATENDIMENTOPUBLICO.espec_tipo_validacao estv "
					 +	"inner join ATENDIMENTOPUBLICO.solicitacao_tipo_espec step on estv.step_id = step.step_id "
					 +	"inner join ATENDIMENTOPUBLICO.registro_atendimento ra on ra.step_id = step.step_id "
					 +	"where ra.imov_id = :idImovel "
					 +	"and rgat_cdsituacao = :codigoSituacao "
					 +	"and estv.estv_icuso = :ativo "
					 +	"and estv.estv_cdconstante = :codigoConstante ";
			
			idRA = (Integer) session.createSQLQuery(consulta)
										.addScalar("idRa", Hibernate.INTEGER)
										.setInteger("idImovel", idImovel)
										.setShort("codigoSituacao", RegistroAtendimento.SITUACAO_PENDENTE)
										.setShort("ativo", ConstantesSistema.INDICADOR_USO_ATIVO)
										.setCharacter("codigoConstante", EspecificacaoTipoValidacao.ALTERACAO_VINCULO)
										.uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return idRA;
	}

	/**
	 * [UC1401] - Solicitar Registro de Atendimento Via Celular
	 * 
	 * @author Amelia Pessoa
	 * @date 02/01/2013
	 *  
	 */
	public void atualizarSituacaoRA(Integer idRA, Integer novaSituacao)
			throws ErroRepositorioException {

		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "UPDATE gcom.atendimentopublico.registroatendimento.RegistroAtendimento SET "
					+ "rgat_cdsituacao = :novaSituacao, rgat_tmultimaalteracao = :ultimaAlteracao WHERE id = :idRA ";

			session.createQuery(consulta)
					.setInteger("novaSituacao", novaSituacao)		
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idRA",idRA)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1396] Informar Dados para DevoluÁ„o de Valor Faturado e Pago Indevidamente
	 * [UC1396] Informar Dados para DevoluÁıes de Valor Faturado e Pago Indevidamente
	 * [UC1396] Informar Dados para DevoluÁ„o de Valor Faturado e Pago Indevidamente
	 * [UC1396] Informar Dados para DevoluÁ„o de Valor Faturado e Pago Indevidamente
	 * 
	 * @author Hugo Azevedo
	 * @date 10/12/2012
	 * 
	 */
	public CobrancaIndevidaMotivo pesquisarCobrancaIndevidaMotivo(Integer idMotivo) throws ErroRepositorioException{
		CobrancaIndevidaMotivo retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			
			String sql = " select cim " +
						 " from CobrancaIndevidaMotivo cim " +
						 " where cim.id = :idMotivo";
			
			retorno = (CobrancaIndevidaMotivo)session.createQuery(sql)
										 			 .setInteger("idMotivo", idMotivo)
										 			 .setMaxResults(1)
										 			 .uniqueResult();
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1396] Informar Dados para DevoluÁ„o de Valor Faturado e Pago Indevidamente
	 * 
	 * @author Hugo Azevedo
	 * @date 10/12/2012
	 * 
	 */
	public Collection pesquisarCobrancaIndevidaMotivo() throws ErroRepositorioException{
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			
			String sql = " select cim " +
						 " from CobrancaIndevidaMotivo cim " +
						 " where cim.indicadorUso = 1 " +
						 " order by cim.descricao";
			
			retorno = (Collection)session.createQuery(sql).list();
										 			 
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Hugo Azevedo
	 * @date 14/12/2012
	 * 
	 */
	public Collection<RegistroAtendimentoDevolucaoValores> pesquisarRegistroAtendimentoDevolucaoValores(Integer idRa) throws ErroRepositorioException{
		
		Collection<RegistroAtendimentoDevolucaoValores> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			
			String sql = " select radv " +
						 " from RegistroAtendimentoDevolucaoValores radv " +
						 " left join fetch radv.contaGeral cg " +
						 " left join fetch radv.guiaPagamentoGeral gpg" +
						 " left join fetch radv.debitoACobrarGeral dacg " +
						 " left join fetch cg.conta " +
						 " left join fetch cg.contaHistorico " +
						 " left join fetch gpg.guiaPagamento " +
						 " left join fetch gpg.guiaPagamentoHistorico " +
						 " left join fetch dacg.debitoACobrar " +
						 " left join fetch dacg.debitoACobrarHistorico " +
						 " left join fetch radv.cobrancaIndevidaMotivo " +
						 " where radv.comp_id.registroAtendimentoId = :idRa "; 
						 
			
			retorno = (Collection)session.createQuery(sql)
										 .setInteger("idRa", idRa)
										 .list();
										 			 
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;	
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Hugo Azevedo
	 * @date 18/12/2012
	 * 
	 */
	public void removerRegistroAtendimentoDevolucaoValores(Integer idRa) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			
			String sql = " delete gcom.atendimentopublico.registroatendimento.RegistroAtendimentoDevolucaoValores radv " +
					     " where radv.comp_id.registroAtendimentoId = :idRa"; 
						 
			session.createQuery(sql).setInteger("idRa", idRa).executeUpdate();
										 			 
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1131] Efetuar DevoluÁ„o de Valores Pagos em Duplicidade
	 * 
	 * [SB0001] - Pesquisar os pagamentos associados ao RA
	 * 
	 * @author Mariana Victor
	 * @date 05/12/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisaDadosRegistroAtendimentoDevolucaoValores(
			Integer idRA, Short codigoTipoDevolucao, Integer idImovel) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select pgmt.pgmt_id as idPagamento, "
					+ "   case when (pgmt.cnta_id is not null) then "
					+ "     'Conta' "
					+ "    when (pgmt.gpag_id is not null) then "
					+ "     'Guia de Pagamento' "
					+ "    when (pgmt.dbac_id is not null) then "
					+ "     'DÈbito a Cobrar' "
					+ "   else '' end as documento, "
					+ "   radv.radv_amreferenciapagamento as anoMes, "
					+ "   radv.radv_dtpagamento as dataPagamento, "
					+ "   radv.radv_vlpagamento	 as valorPago, "
					+ "   radv.radv_vldevolucao as valorCredito, "
					+ "   pgmt.cnta_id as idConta, "
					+ "   cntg.cntg_ichistorico as indicadorContaHistorico "
					+ " from arrecadacao.pagamento pgmt "
					+ "   inner join atendimentopublico.ra_devolucao_valores radv on radv.pgmt_id = pgmt.pgmt_id "
					+ "   inner join atendimentopublico.registro_atendimento rgat on rgat.rgat_id = radv.rgat_id "
					+ "	  left join faturamento.conta_geral cntg on cntg.cnta_id = pgmt.cnta_id "
					+ " where rgat.rgat_id = :idRA "
					+ "	  and pgmt.imov_id = :idImovel "
					+ "   and radv.radv_cdtipodevolucao = :codigoTipoDevolucao ";
			
			switch (codigoTipoDevolucao) {
			case 1:
				consulta = consulta
					+ " and pgmt.pgst_idatual = 1 ";
				break;

			case 2:
				consulta = consulta
					+ " and pgmt.pgst_idatual = 5 "
					+ " and pgmt.pgmt_vlexcedente is not null ";
				break;

			case 3:
				consulta = consulta
					+ " union "
					+ " select pghi.pghi_id as idPagamento, "
					+ "   case when (pghi.cnta_id is not null) then "
					+ "     'Conta' "
					+ "    when (pghi.gpag_id is not null) then "
					+ "     'Guia de Pagamento' "
					+ "    when (pghi.dbac_id is not null) then "
					+ "     'DÈbito a Cobrar' "
					+ "   else '' end as documento, "
					+ "   radv.radv_amreferenciapagamento as anoMes, "
					+ "   radv.radv_dtpagamento as dataPagamento, "
					+ "   radv.radv_vlpagamento	 as valorPago, "
					+ "   radv.radv_vldevolucao as valorCredito, "
					+ "   pghi.cnta_id as idConta, "
					+ "   cntg.cntg_ichistorico as indicadorContaHistorico "
					+ " from arrecadacao.pagamento_historico pghi "
					+ "   inner join atendimentopublico.ra_devolucao_valores radv on radv.pgmt_id = pghi.pghi_id "
					+ "   inner join atendimentopublico.registro_atendimento rgat on rgat.rgat_id = radv.rgat_id "
					+ "	  left join faturamento.conta_geral cntg on cntg.cnta_id = pghi.cnta_id "
					+ " where rgat.rgat_id = :idRA "
					+ "	  and pghi.imov_id = :idImovel "
					+ "   and radv.radv_cdtipodevolucao = :codigoTipoDevolucao ";
				
				break;

			default:
				break;
			}

			retorno = session.createSQLQuery(consulta)
					.addScalar("idPagamento", Hibernate.INTEGER)
					.addScalar("documento", Hibernate.STRING)
					.addScalar("anoMes", Hibernate.INTEGER)
					.addScalar("dataPagamento", Hibernate.DATE)
					.addScalar("valorPago", Hibernate.BIG_DECIMAL)
					.addScalar("valorCredito", Hibernate.BIG_DECIMAL)
					.addScalar("idConta", Hibernate.INTEGER)
					.addScalar("indicadorContaHistorico", Hibernate.SHORT)
					.setInteger("idRA", idRA)
					.setInteger("idImovel", idImovel)
					.setShort("codigoTipoDevolucao", codigoTipoDevolucao)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  
	 * [FS0060] - Verificar inclus√£o das InformaÁ„o do PROGIS
	 * 
	 * @author Maxwell Moreira
	 * @date 08/01/2013
	 * 
	 * @return Integer
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public Integer verificaInclusaoPROGIS(Integer idLocalidade) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT COUNT(*) as quantidadeLocalidade FROM cadastro.localidade WHERE loca_id = :idLocalidade AND LOCA_ICINCLCOORDPROGISRA = 1";
			retorno =  (Integer) session.createSQLQuery(consulta).addScalar("quantidadeLocalidade", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}	
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [FS0037] - Verificar SituaÁ„o do contrato anterior
	 * [SB0033] - Desabilita dados para especificaÁ„o de DevoluÁ„o
	 * [SB0033] - Desabilita dados para especificaÁ„o de DevoluÁ„o
	 * 
	 * @author Mariana Victor
	 * @date 14/01/2013
	 */
	public Boolean verificarRegistroAtendimentoEspecificacaoDevolucao(Integer idEspecificacao) 
	throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " select count(*) AS quantidade "
					 + " from atendimentopublico.solicitacao_tipo_espec "
					 + " where step_id = :idEspecificacao " 
					 + "   and (step_icinformarpgtoduplicidade = 1 "
					 + "     or step_icinformardocpgtoamaior = 1 "
					 + "     or step_icinformardocpgtoindevido = 1) ";
					
			retorno = (Integer) session.createSQLQuery(consulta)
						.addScalar("quantidade", 
								Hibernate.INTEGER)
						.setInteger("idEspecificacao",
							idEspecificacao)
						.setMaxResults(1).uniqueResult();
			
			if (retorno != null 
					&& retorno.compareTo(new Integer("0")) > 0) {
				return true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return false;
		
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [FS0012] - Verificar possibilidade de atualizaÁ„o do registro de atendimento 
	 * . Caso exista DevoluÁ„o de valores para essa RA 
	 * 
	 * @author Mariana Victor
	 * @date 14/01/2013
	 */
	public Boolean verificarRegistroAtendimentoDevolucaoValoresEfetuada(
			Integer idRegistroAtendimento) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " select count(*) AS quantidade "
					 + " from atendimentopublico.ra_devolucao_valores "
					 + " where rgat_id = :idRegistroAtendimento "
					 + "   and radv_dtdevolucao is not null ";
					
			retorno = (Integer) session.createSQLQuery(consulta)
						.addScalar("quantidade", 
								Hibernate.INTEGER)
						.setInteger("idRegistroAtendimento",
							idRegistroAtendimento)
						.setMaxResults(1).uniqueResult();
			
			if (retorno != null 
					&& retorno.compareTo(new Integer("0")) > 0) {
				return true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return false;
		
	}
	
	/**
	 * [RM8399] Tornar Obrigatorio a Inclus„o do RA no PROGIS 
	 * 
	 * @author Maxwell Moreira
	 * @date 28/01/2013
	 */
	public Boolean verificarLocalidadeInclusaoCoordenadasPROGIS(int idLocalidade) 
			throws ErroRepositorioException{
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
	
		String consulta = "SELECT COUNT(*) AS localidade FROM cadastro.localidade WHERE loca_id = :idLocalidade  AND loca_icinclcoordprogisra = 1";
		
		try{	
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("localidade", 
							Hibernate.INTEGER)
					.setInteger("idLocalidade",
							idLocalidade)
					.setMaxResults(1).uniqueResult();
		
			if (retorno != null 
					&& retorno.compareTo(new Integer("0")) > 0) {
				return false;
			}else{
				return true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0366] - Inserir Registro Atendimento 
	 * [SB0027] - Incluir Solicitante do Registro Atendimento
	 * 
	 * @author Maxwell Moreira
	 * @date 26/02/2013
	 */
	public void atualizaEmailSolicitanteClienteRegistroAtendimento(String emailCliente, String idCliente) throws ErroRepositorioException{
		
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.cadastro.cliente.Cliente clie SET clie.email = :emailCliente WHERE clie.id = :idCliente";

			
			session.createQuery(consulta)
					.setString("emailCliente", emailCliente)		
					.setString("idCliente",idCliente)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}
	
	/**
	 * [UC0366] - Inserir Registro Atendimento 
	 * [SB0027] - O sistema remove os telefones associados ao cliente infomado
	 * 
	 * @author Maxwell Moreira
	 * @date 26/02/2013
	 */
	public void removerTelefonesCliente(String idCliente) throws ErroRepositorioException{
		
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "delete gcom.cadastro.cliente.ClienteFone cliFone where cliFone.cliente.id = :idCliente";
			
			session.createQuery(consulta)		
					.setString("idCliente",idCliente)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	  * [UC0457] - Encerrar Ordem de ServiÁo
	  * [SB0010] - Emitir Mensagem de SMS para Encerramento da OS com Sucesso
	  * 
	  * @author Maxwell Moreira
	  * @created 07/03/2013
	  */
	public Boolean quantidadeEncerramentoConclusaoDeServico(Integer idRegistroAtendimento, Integer idOrdemServico) throws ErroRepositorioException{

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = " SELECT COUNT(*) AS quantidade " +
							" FROM atendimentopublico.registro_atendimento rgat " +
							"   INNER JOIN atendimentopublico.ordem_servico orse on orse.rgat_id = rgat.rgat_id " +
							" WHERE rgat.rgat_id = :idRegistroAtendimento " +
							"   AND rgat.meso_id !=  " + MeioSolicitacao.CELULAR_PROGIS +
							"   AND orse.orse_id = :idOrdemServico  " +
							"   AND orse.amen_id = 2  ";
		
		try{	
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("quantidade", 
							Hibernate.INTEGER)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();
		
			if (retorno != null 
					&& retorno.compareTo(new Integer("0")) > 0) {
				return true;
			}else{
				return false;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	  * [UC0457] - Encerrar Ordem de ServiÁo
	  * [SB0010] - Emitir Mensagem de SMS para Encerramento da OS com Sucesso
	  * 
	  * @author Maxwell Moreira
	  * @created 07/03/2013
	  */
	public Collection clienteRegistroAtendimentoTemCelular(String registroAtendimento) throws ErroRepositorioException{

		Session session = HibernateUtil.getSession();
		String consulta = null;
		Collection retorno = null;
		
		try{	
			consulta = "SELECT cfon_cdddd as ddd, cfon_nnfone AS celular FROM cadastro.cliente_fone WHERE fnet_id = 3 AND clie_id " + 
			" IN (SELECT clie_id FROM atendimentopublico.ra_solicitante WHERE rgat_id =:registroAtendimento)";
		
			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("ddd", Hibernate.STRING)
					.addScalar("celular", Hibernate.STRING)
					.setString("registroAtendimento", registroAtendimento).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;			
	}
	
	/**
	  * [UC0457] - Encerrar Ordem de ServiÁo
	  * [SB0010] - Emitir Mensagem de SMS para Encerramento da OS com Sucesso 
	  * 
	  * @author Maxwell Moreira
	  * @created 19/03/2013
	  */
	public Integer menorTempoGeracaoOrdemDeServico(String registroAtendimento) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Integer retorno = null;
		
		try{	
			consulta = " SELECT orse1.orse_id as orse_id " +
			" FROM atendimentopublico.ordem_servico orse1 " +
			" WHERE orse1.ORSE_TMGERACAO = (select min(orse2.ORSE_TMGERACAO) from atendimentopublico.ordem_servico orse2 where orse2.rgat_id =:registroAtendimento) ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("orse_id", Hibernate.INTEGER)
					.setString("registroAtendimento", registroAtendimento).setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0449] Consultar Registro Atendimento do ImÛvel
	 * 
	 * @author Anderson Cabral
	 * @date 17/05/2013
	 * 
	 */
	public Collection consultarRegistroAtendimentoLigacaoEsgoto(Integer idImovel) throws ErroRepositorioException{
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"SELECT distinct registroAtendimento.id, " //0
					 +	"solicitacaoTipo.descricao, " //1
					 +	"solicitacaoTipoEspecificacao.descricao, " //2
					 +	"registroAtendimento.registroAtendimento, " //3
					 +	"registroAtendimento.dataEncerramento, " //4
					 +	"atendimento.id, " //5
					 +	"atendimento.descricao " //6
					 +	"from LigacaoEsgoto ligacaoEsgoto "
					 +	"inner join ligacaoEsgoto.imovel imovel "
					 +	"inner join ligacaoEsgoto.registroAtendimento registroAtendimento "
					 + 	"left join registroAtendimento.solicitacaoTipoEspecificacao solicitacaoTipoEspecificacao "
					 + 	"left join solicitacaoTipoEspecificacao.solicitacaoTipo solicitacaoTipo "
					 +	"left join registroAtendimento.atendimentoMotivoEncerramento atendimento "
					 +	"where imovel.id = :idImovel ";
			
			Query query = session.createQuery(consulta);

			query.setInteger("idImovel", idImovel.intValue());
			
			retorno = query.list();
			
		}catch (HibernateException e) {
			// levanta a exeÁ„oo para a PrÛxma camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a Sess„o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0430] - [SB0006]
	 * Obter unidade repavimentadora sugerida
	 * @author Carlos Chaves
	 * @date 05/08/2013
	 * 
	 * @param idRA
	 * @return idUnidadeRepavimentadora
	 * @throws ErroRepositorioException
	 */
	public Integer obterUnidadeRepavimentadoraSugerida(Integer idRa)
			throws ErroRepositorioException {

		// Cria uma sess„o com o hibernate
		Session session = HibernateUtil.getSession();
		// Retorno Consulta
		Integer retorno = null;
		// Cria a vari·vel que vai conter o hql
		String consulta;

		try {
			consulta = "Select urm.UNID_IDREPAVIMENTADORA as idUnidade "+
					   "from  atendimentopublico.registro_atendimento  ra "+
					   "inner join cadastro.logradouro_bairro loba on loba.LGBR_ID = ra.LGBR_ID "+
					   "inner join atendimentopublico.UNID_REPAV_MUNI_BAIRROS urmb on urmb.bair_id = loba.bair_id "+
					   "inner join atendimentopublico.UNID_REPAV_MUNICIPIO urm on urm.UNRM_ID = urmb.UNRM_ID "+
					   "where ra.rgat_id = :idRa";

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("idUnidade", Hibernate.INTEGER)
					.setInteger("idRa",idRa)
					.setMaxResults(1)
					.uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceÁ„o para a prÛxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sess„o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC 1401] - Solicitar Registro de Atendimento Via Celular
	 * [IT 0001] - Obter Unidade de Destino
	 * 
	 * @author Davi Menezes
	 * @date 02/10/2013
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public UnidadeOrganizacional obterUnidadeOrganizacionalDivisaoEsgoto(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		UnidadeOrganizacional unidadeDestino = null;
		String consulta = "";
		
		try{
			consulta = 	"select unidadeDestino "
					 +	"from Imovel imovel "
					 +	"inner join imovel.subSistemaEsgoto subSistemaEsgoto "
					 +	"inner join subSistemaEsgoto.sistemaEsgoto sistemaEsgoto "
					 +	"inner join sistemaEsgoto.divisaoEsgoto divisaoEsgoto "
					 +	"inner join divisaoEsgoto.unidadeOrganizacional unidadeDestino "
					 +	"where imovel.id = :idImovel ";
					 
			unidadeDestino = (UnidadeOrganizacional) session.createQuery(consulta)
															.setInteger("idImovel", idImovel)
															.setMaxResults(1)
															.uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return unidadeDestino;
	}
	
	/**
	 * [UC1401] - Solicitar Registro de Atendimento Via Celular
	 * 
	 * @author Davi Menezes
	 * @date 18/10/2013
	 * 
	 * @param idRA
	 * @param cpf
	 * @param nomeSolicitante
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroCpfRASolicitante(Integer idRA, String cpf, String nomeSolicitante) throws ErroRepositorioException{
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = 	"update gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante "
					 +	"SET raso_nncpf = :numeroCpf, raso_tmultimaalteracao =:ultimaalteracao " 
					 +	"WHERE rgat_id = :idRA "
					 +	"AND raso_nmsolicitante like :nomeSolicitante ";

			session.createQuery(consulta)
					.setString("numeroCpf", cpf)		
					.setTimestamp("ultimaalteracao", new Date())
					.setInteger("idRA", idRA)
					.setString("nomeSolicitante", nomeSolicitante)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 25/11/2013
	 * RM8974
	 * UC0364
	 */
	public void removerRAUnidade(Integer idRA) 
			throws ErroRepositorioException{
		
		String scriptSql = "";
		Session session = HibernateUtil.getSession();
		
		try {
			scriptSql = "DELETE gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade rau" +
						" WHERE rau.registroAtendimento = :idRegistroAtendimento" +
						" AND rau.atendimentoRelacaoTipo = :encerrada";
			
			session.createQuery(scriptSql)
			.setInteger("idRegistroAtendimento", idRA)
			.setInteger("encerrada", AtendimentoRelacaoTipo.ENCERRAR)
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}

	
	/**
	 * [UC1600] Inserir Normas e Procedimentos
	 * 
	 * @author Ana Maria
	 * @date 08/05/2014
	 */
	public Collection<NormaDocumentoTipo> obterColecaoNormaDocumentoTipo()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<NormaDocumentoTipo> colecaoNormaDocumentoTipo = new ArrayList<NormaDocumentoTipo>();

		try {
			colecaoNormaDocumentoTipo = session
					.createCriteria(NormaDocumentoTipo.class)
					.add(Restrictions.eq("indicadorUso",
							ConstantesSistema.INDICADOR_USO_ATIVO)).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoNormaDocumentoTipo;
	}
	
	/**
	 * [UC1601] - Filtrar/Manter Normas e Procedimentos
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2014
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public Collection<NormaProcedimentos> obterNormasProcedimentos(String idTipoDocumento, String descTitulo, 
		String descAssunto,String tipoPesquisaTitulo,String tipoPesquisaAssunto,Integer numeroPagina) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection<NormaProcedimentos> colecaoNormaProcedimentos = null;
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		
		try{
			consulta = 	"SELECT npro "
					 +	"FROM NormaProcedimentos npro "
					 +	"INNER JOIN FETCH npro.normaDocumentoTipo ndtp "
					 +	"WHERE ";
			
			if(idTipoDocumento != null && !idTipoDocumento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " ndtp.id = :idTipoDocumento AND";
				parameters.put("idTipoDocumento", new Integer(idTipoDocumento));
			}

			if(descTitulo != null && !descTitulo.equals("")){
				
				if (tipoPesquisaTitulo == null || tipoPesquisaTitulo.equals("")
				 || tipoPesquisaTitulo.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(npro.descricaoTitulo) LIKE '"
							+ descTitulo.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(npro.descricaoTitulo) LIKE '%"
							+ descTitulo.toUpperCase() + "%'  AND";
				}
			}
			
			if(descAssunto != null && !descAssunto.equals("")){
				
				if (tipoPesquisaAssunto == null || tipoPesquisaAssunto.equals("")
				 || tipoPesquisaAssunto.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(npro.descricaoAssunto) LIKE '"
							+ descAssunto.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(npro.descricaoAssunto) LIKE '%"
							+ descAssunto.toUpperCase() + "%'  AND";
				}

			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4) + " ORDER BY ndtp.id ";
			
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

			colecaoNormaProcedimentos = (Collection<NormaProcedimentos>)query
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoNormaProcedimentos;
	}
	
	
	/**
	 * [UC1601] - Filtrar/Manter Normas e Procedimentos
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerNormaProcedimentos(Integer idNormaProcedimentos) throws ErroRepositorioException{
		
		String scriptSql = "";
		Session session = HibernateUtil.getSession();
		
		try {
			//Deleta NormaArquivos
			scriptSql = "DELETE gcom.atendimentopublico.registroatendimento.NormaArquivos narq " +
						" WHERE narq.normaProcedimentos.id = :idNormaProcedimentos";
			
			session.createQuery(scriptSql).setInteger("idNormaProcedimentos", idNormaProcedimentos).executeUpdate();
			
			//Deleta NormaProcedimentos
			scriptSql = "DELETE gcom.atendimentopublico.registroatendimento.NormaProcedimentos npro " +
					" WHERE npro.id = :idNormaProcedimentos";
		
			session.createQuery(scriptSql).setInteger("idNormaProcedimentos", idNormaProcedimentos).executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1601] - Filtrar/Manter Normas e Procedimentos
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2014
	 * 
	 * @param idNormaProcedimentos
	 * @throws ErroRepositorioException
	 */
	public NormaProcedimentos obterNormasProcedimentos(Integer idNormaProcedimentos) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		NormaProcedimentos normaProcedimentos = null;
		String consulta = "";
		
		try{
			consulta = 	"SELECT npro "
					 +	"FROM NormaProcedimentos npro "
					 +	"INNER JOIN FETCH npro.normaDocumentoTipo ndtp "
					 +	"WHERE npro.id = :idNormaProcedimentos ";

			normaProcedimentos = (NormaProcedimentos) session.createQuery(consulta)
					.setInteger("idNormaProcedimentos", idNormaProcedimentos)
					.setMaxResults(1)
					.uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return normaProcedimentos;
	}
	
	/**
	 * [UC1601] - Filtrar/Manter Normas e Procedimentos
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2014
	 * 
	 * @param idNormaProcedimentos
	 * @throws ErroRepositorioException
	 */
	public Collection<NormaArquivos> obterNormaArquivos(Integer idNormaProcedimentos) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection<NormaArquivos> colecaoNormaArquivos = null;
		String consulta = "";
		
		try{
			consulta = 	"SELECT narq "
					 +	"FROM NormaArquivos narq "
					 +	"WHERE narq.normaProcedimentos.id = :idNormaProcedimentos ";

			colecaoNormaArquivos = (Collection<NormaArquivos>) session.createQuery(consulta)
					.setInteger("idNormaProcedimentos", idNormaProcedimentos)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoNormaArquivos;
	}
	
	/**
	 * [UC1601] - Filtrar/Manter Normas e Procedimentos
	 * 
	 * @author Vivianne Sousa
	 * @date 15/05/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerNormaArquivos(Integer idNormaProcedimentos) throws ErroRepositorioException{
		
		String scriptSql = "";
		Session session = HibernateUtil.getSession();
		
		try {
			//Deleta NormaArquivos
			scriptSql = "DELETE gcom.atendimentopublico.registroatendimento.NormaArquivos narq " +
						" WHERE narq.normaProcedimentos.id = :idNormaProcedimentos";
			
			session.createQuery(scriptSql).setInteger("idNormaProcedimentos", idNormaProcedimentos).executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 19/05/2014
	 */
	public void removerRegistroAtendimentoAnexo(Integer idRA) throws ErroRepositorioException{
		
		String scriptSql = "";
		Session session = HibernateUtil.getSession();
		
		try {
			scriptSql = "DELETE gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo raan" +
						" WHERE raan.registroAtendimento.id = :idRegistroAtendimento";
			
			session.createQuery(scriptSql)
			.setInteger("idRegistroAtendimento", idRA)
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}
	
	/**
	 * [UC1602] Consultar Normas e Procedimentos
	 * @author Jonathan Marcos
	 * @date 21/05/2014
	 * @param idTipoDocumento
	 * @param descricaoTitulo
	 * @param descricaoAssunto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterNormasProcedimentos(Integer idTipoDocumento,Short indicadorTextoTitulo,Short indicadorTextoAssunto,
		String descricaoTitulo,String descricaoAssunto,Integer numeroPagina)	throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String scripSql = "";
		Query query = null;
		Map parameters = new HashMap();
		Collection<Object[]> retorno = null;
		
		try {
			scripSql = "SELECT "+
					   "ndt.ndtp_dsdocumentotipo AS descricaoTipoDocumento,"+
					   "np.npro_dstitulo AS descricaoTitulo,"+
					   "na.narq_id AS idArquivo,"+
					   "na.narq_dsarquivotexto AS descricaoArquivo," +
					   "np.npro_dsassunto AS descricaoAssunto"+
					   " FROM atendimentopublico.norma_procedimentos np"+
					   " INNER JOIN atendimentopublico.norma_documento_tipo ndt ON (np.ndtp_id=ndt.ndtp_id)"+
					   " INNER JOIN atendimentopublico.norma_arquivos na ON (np.npro_id=na.npro_id)" +
					   " WHERE";
						
			if(idTipoDocumento!=null && idTipoDocumento.compareTo(Integer.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))!=0){
				parameters.put("idTipoDocumento", idTipoDocumento);
				scripSql+= " ndt.ndtp_id = :idTipoDocumento";
			}
			
			if(descricaoTitulo!=null && descricaoTitulo.compareTo("")!=0){
				if(indicadorTextoTitulo.compareTo((short) 1)==0){
					descricaoTitulo=descricaoTitulo+"%";
				}else{
					descricaoTitulo="%"+descricaoTitulo+"%";
				}
				
				if(idTipoDocumento!=null && idTipoDocumento.compareTo(Integer.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))!=0){
					scripSql+=" AND";
				}
				
				parameters.put("descricaoTitulo", descricaoTitulo);
				scripSql+=" np.npro_dstitulo LIKE :descricaoTitulo";
			}
			
			if(descricaoAssunto!=null && descricaoAssunto.compareTo("")!=0){
				if(indicadorTextoAssunto.compareTo((short)1)==0){
					descricaoAssunto = descricaoAssunto+"%";
				}else{
					descricaoAssunto = "%"+descricaoAssunto+"%";
				}
				
				if((idTipoDocumento!=null && idTipoDocumento.compareTo(Integer.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))!=0)
						||(descricaoTitulo!=null && descricaoTitulo.compareTo("")!=0)){
					scripSql+=" AND";
				}
				
				parameters.put("descricaoAssunto", descricaoAssunto);
				scripSql+=" np.npro_dsassunto LIKE :descricaoAssunto";
			}
			
			scripSql+=" ORDER BY ndt.ndtp_dsdocumentotipo";
			
			query = session.createSQLQuery(scripSql)
					.addScalar("descricaoTipoDocumento", Hibernate.STRING)
					.addScalar("descricaoTitulo", Hibernate.STRING)
					.addScalar("idArquivo", Hibernate.INTEGER)
					.addScalar("descricaoArquivo", Hibernate.STRING)
					.addScalar("descricaoAssunto", Hibernate.STRING);

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

			retorno = (Collection<Object[]>)query
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1602] Consultar Normas e Procedimentos
	 * @author Jonathan Marcos
	 * @date 21/05/2014
	 * @param idTipoDocumento
	 * @param descricaoTitulo
	 * @param descricaoAssunto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterNormasProcedimentosCount(Integer idTipoDocumento,Short indicadorTextoTitulo,Short indicadorTextoAssunto,
		String descricaoTitulo,String descricaoAssunto)	throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String scripSql = "";
		Query query = null;
		Map parameters = new HashMap();
		Integer totalRegistros = null;
		
		try {
			scripSql = "SELECT "+
					   "COUNT(ndt.ndtp_dsdocumentotipo) AS totalRegistros"+
					   " FROM atendimentopublico.norma_procedimentos np"+
					   " INNER JOIN atendimentopublico.norma_documento_tipo ndt ON (np.ndtp_id=ndt.ndtp_id)"+
					   " INNER JOIN atendimentopublico.norma_arquivos na ON (np.npro_id=na.npro_id)" +
					   " WHERE";
						
			if(idTipoDocumento!=null && idTipoDocumento.compareTo(Integer.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))!=0){
				parameters.put("idTipoDocumento", idTipoDocumento);
				scripSql+= " ndt.ndtp_id = :idTipoDocumento";
			}
			
			if(descricaoTitulo!=null && descricaoTitulo.compareTo("")!=0){
				if(indicadorTextoTitulo.compareTo((short) 1)==0){
					descricaoTitulo=descricaoTitulo+"%";
				}else{
					descricaoTitulo="%"+descricaoTitulo+"%";
				}
				
				if(idTipoDocumento!=null && idTipoDocumento.compareTo(Integer.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))!=0){
					scripSql+=" AND";
				}
				
				parameters.put("descricaoTitulo", descricaoTitulo);
				scripSql+=" np.npro_dstitulo LIKE :descricaoTitulo";
			}
			
			if(descricaoAssunto!=null && descricaoAssunto.compareTo("")!=0){
				if(indicadorTextoAssunto.compareTo((short)1)==0){
					descricaoAssunto = descricaoAssunto+"%";
				}else{
					descricaoAssunto = "%"+descricaoAssunto+"%";
				}
				
				if((idTipoDocumento!=null && idTipoDocumento.compareTo(Integer.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))!=0)
						||(descricaoTitulo!=null && descricaoTitulo.compareTo("")!=0)){
					scripSql+=" AND";
				}
				
				parameters.put("descricaoAssunto", descricaoAssunto);
				scripSql+=" np.npro_dsassunto LIKE :descricaoAssunto";
			}
			
			query = session.createSQLQuery(scripSql)
					.addScalar("totalRegistros", Hibernate.INTEGER);

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

			totalRegistros = (Integer)query
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return totalRegistros;
	}
	
	/**
	 * [UC1601] - Filtrar/Manter Normas e Procedimentos
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2014
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public Integer obterNormasProcedimentosCount(String idTipoDocumento,
			String descTitulo, String descAssunto,String tipoPesquisaTitulo,String tipoPesquisaAssunto) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Integer qtdeNormaProcedimentos = null;
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		
		try{
			consulta = 	"SELECT count(npro.id) "
					 +	"FROM NormaProcedimentos npro "
					 +	"INNER JOIN npro.normaDocumentoTipo ndtp "
					 +	"WHERE ";
			
			if(idTipoDocumento != null && !idTipoDocumento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " ndtp.id = :idTipoDocumento AND";
				parameters.put("idTipoDocumento", new Integer(idTipoDocumento));
			}

			if(descTitulo != null && !descTitulo.equals("")){
				
				if (tipoPesquisaTitulo == null || tipoPesquisaTitulo.equals("")
				 || tipoPesquisaTitulo.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(npro.descricaoTitulo) LIKE '"
							+ descTitulo.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(npro.descricaoTitulo) LIKE '%"
							+ descTitulo.toUpperCase() + "%'  AND";
				}
			}
			
			if(descAssunto != null && !descAssunto.equals("")){
				
				if (tipoPesquisaAssunto == null || tipoPesquisaAssunto.equals("")
				 || tipoPesquisaAssunto.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(npro.descricaoAssunto) LIKE '"
							+ descAssunto.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(npro.descricaoAssunto) LIKE '%"
							+ descAssunto.toUpperCase() + "%'  AND";
				}

			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4);
			
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

			qtdeNormaProcedimentos = (Integer)query.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return qtdeNormaProcedimentos;
	}

}