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
 * Thiago Silva Toscano de Brito
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

package gcom.integracao;

import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.cadastro.atualizacaocadastral.AcaAtualizadores;
import gcom.cadastro.atualizacaocadastral.AtcBairro;
import gcom.cadastro.atualizacaocadastral.AtcClienteImovel;
import gcom.cadastro.atualizacaocadastral.AtcClienteImovelRetorno;
import gcom.cadastro.atualizacaocadastral.AtcClienteRetorno;
import gcom.cadastro.atualizacaocadastral.AtcClienteTelefoneRetorno;
import gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetorno;
import gcom.cadastro.atualizacaocadastral.AtcImovelRetorno;
import gcom.cadastro.atualizacaocadastral.AtcLogradouro;
import gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovelRetorno;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.integracao.upa.OrdensServico;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;

public class RepositorioIntegracaoHBM implements IRepositorioIntegracao {

	private static IRepositorioIntegracao instancia;

	/**
	 * Construtor da classe RepositorioFaturamentoHBM
	 */
	private RepositorioIntegracaoHBM() {
	}

	/**
	 * Retorna o valor de instance
	 * 
	 * @return O valor de instance
	 */
	public static IRepositorioIntegracao getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioIntegracaoHBM();
		}
		return instancia;
	}

	public Collection pesquisarOrdemServicoMovimentoParaEnvioSAM()
			throws ErroRepositorioException {
		// obtém a sessão

		Session session = HibernateUtil.getSession();

		Collection retorno = new ArrayList();

		try {

			retorno = session
					.createQuery(

							"select osmv from OrdemServicoMovimento osmv "
									+ " left join fetch osmv.imovel imov"
									+ " left join fetch imov.setorComercial "
									+ " left join fetch imov.quadra "
									+ " where osmv.indicadorMovimento = 1 "
									+ "and osmv.unidadeOrganizacionalExecutora is not null ")
					//				+ "and osmv.dataGeracao between :data1 and :data2 ")
					//.setTimestamp("data1", Util.formatarDataInicial(new Date()))
					//.setTimestamp("data2", Util.formatarDataFinal(new Date()))
					.list();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public void exportarOrdemServicoMovimentos(
			Collection ordensServicoParaExportacao)
			throws ErroRepositorioException {

		StatelessSession session = HibernateUtil
				.getStatelessSessionIntegracaoSAM();

		if (ordensServicoParaExportacao != null
				&& !ordensServicoParaExportacao.isEmpty()) {
			Iterator it = ordensServicoParaExportacao.iterator();

			try {
				while (it.hasNext()) {

					Object obj = it.next();

					try {
					//System.out.println("INSERINDO: " + i + "-" + obj.getClass().getSimpleName());
					session.insert(obj);
					} catch (ConstraintViolationException exception) {		
						//Não fazer nada pois o registro já está inserido no banco da SAM
						//System.out.println("entrou!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}	 

				}
				
			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}

	}
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0005] Realizar integração com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer valorMaximoChaveIFS()
			throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSessionIntegracaoIFS();
		
		Integer retorno = 1;
		
		Connection con = null;
		Statement stmt = null;
		
		String consulta = null;
		try {
			con = session.connection();
			stmt = con.createStatement();
			
			consulta = "SELECT MAX(TO_NUMBER(LOAD_ID)) as valor FROM IFSCPSA.C_GSAN_CONTABILIDADE_TAB";
			
			ResultSet set = stmt.executeQuery(consulta);
			
			if(set != null){
				while (set.next()) {
					retorno = set.getInt("valor");
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
			try {
				
				if(stmt != null){
					stmt.close();	
				}
				if(con != null){
					con.close();	
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			}
		}
		return retorno;
	}
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0005] Realizar integração com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void exportarDadosContabilidadeIFS(String company,
			String voucher_type,String currency_code,
			String load_id,Date transaction_date,
			String accounting_cod,String cost_center,
			String	accounting_year,String accounting_period,
			BigDecimal debet_amount,BigDecimal credit_amount,
			String	text,String code_c,
			String code_d,String code_g,
			String code_h,String activity_sequence,
			String status,String transaction_type,
			String error_text,Integer record_no,
			Date rowversion)
		throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSessionIntegracaoIFS();
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		String insert = null;
		try {
			con = session.connection();
			
			//String sequence = Util.obterNextValSequence("cobranca.seq_empresa_cobranca_conta");

			insert = "INSERT INTO IFSCPSA.C_GSAN_CONTABILIDADE_TAB " 
				+ " (LOAD_ID," 
				+ "RECORD_NO," 
				+ "COMPANY," 
				+ "CURRENCY_CODE," 
				+ "TRANSACTION_DATE," 
				+ "ACCOUNTING_COD," 
				+ "COST_CENTER," 
				+ "ACCOUNTING_YEAR," 
				+ "ACCOUNTING_PERIOD,"
				+ "DEBET_AMOUNT," 
				+ "CREDIT_AMOUNT," 
				+ "TEXT," 
				+ "ACTIVITY_SEQUENCE," 
				+ "TRANSACTION_TYPE," 
				+ "ERROR_TEXT," 
				+ "STATUS,"
				+ "VOUCHER_TYPE," 
				+ "CODE_C," 
				+ "CODE_D," 
				+ "CODE_G," 
				+ "CODE_H," 
				+ "ROWVERSION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			
			stmt = con.prepareStatement(insert);
			
			stmt.setString(1, load_id);
			stmt.setInt(2, record_no);
			stmt.setString(3, company);
			stmt.setString(4, currency_code);
			stmt.setDate(5,  Util.getSQLDate(transaction_date));
			
			stmt.setString(6, accounting_cod);
			stmt.setString(7, cost_center);
			stmt.setString(8, accounting_year);
			stmt.setString(9, accounting_period);
			
			stmt.setBigDecimal(10, debet_amount);
			stmt.setBigDecimal(11, credit_amount);
			
			stmt.setString(12, text);
			stmt.setString(13, activity_sequence);
			stmt.setString(14, transaction_type);
			stmt.setString(15, error_text);
			stmt.setString(16, status);
			stmt.setString(17, voucher_type);
			stmt.setString(18, code_c);
			stmt.setString(19, code_d);
			stmt.setString(20, code_g);
			stmt.setString(21, code_h);
			
			stmt.setDate(22,  Util.getSQLDate(rowversion));
			
			stmt.executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
			try {
				if(stmt != null){
					stmt.close();	
				}
				if(con != null){
					con.close();	
				}

			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			}
		}
	}

	public Collection pesquisarOrdensServicoParaRecebimentoUPA()
			throws ErroRepositorioException {
		// obtém a sessão

		Session session = HibernateUtil.getSession();

		Collection retorno = new ArrayList();

		try {

			retorno = session
					.createQuery(

							"select os from OrdemServicoMovimento os inner join fetch os.atendimentoMotivoEncerramento"
									+ " left join fetch os.unidadeOrganizacionalExecutora unidExecutora "
									+ " left join fetch unidExecutora.unidadeRepavimentadora unidPavimentadora "
									+ " left join fetch unidPavimentadora.empresa "
									+ " left join fetch os.servicoTipo "
									+ " left join fetch os.registroAtendimento "
									+ " left join fetch os.pavimentoRua "
									+ " where os.indicadorMovimento = 2 and os.atendimentoMotivoEncerramento.id is not null")
					.list();
			

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public void importarOrdensServico(Collection ordensServicoParaImportacao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		if (ordensServicoParaImportacao != null
				&& !ordensServicoParaImportacao.isEmpty()) {
			Iterator it = ordensServicoParaImportacao.iterator();

			try {
				while (it.hasNext()) {

					OrdensServico ordensServico = (OrdensServico) it.next();

					String consulta = "update OrdemServicoMovimento set "
						+ "unidadeOrganizacionalExecutora = :osUnidadeExecutora,"
						+ "loginUsuario = :osUsuarioExecutora,"
						+ "dataExecucao = :osDataEncerramento, "
						+ "servicoTipo =:servicoId, ";
						
						if (ordensServico.getOsPavimentoRua() != null) {		
							consulta = consulta + " pavimentoRua =:osPavimentoRua,";
								
						}
						
						if (ordensServico.getOsPavimentoCalcada() != null) {
							consulta = consulta + "pavimentoCalcada =:osPavimentoCalcada,";
						}
						
						consulta = consulta + "areaPavimentoRua =:osAreaPavimentoRua,"
						+ "areaPavimentoCalcada =:osAreaPavimentoCalcada,"
						+ "parecer =:osParecerEncerramento,"
						+ "dataTramite =:dataTramite, " +
						" atendimentoMotivoEncerramento =:motivoEncerramento where id = :id";
					
					

					
					Query query = session
							.createQuery(consulta)
							.setInteger("motivoEncerramento", ordensServico.getOsMotivoEncerramento())
							.setTimestamp("dataTramite", new Date())
							.setInteger("osUnidadeExecutora",
									ordensServico.getOsUnidadeExecutora())
							.setString("osUsuarioExecutora",
									ordensServico.getOsUsuarioExecutora())
							.setTimestamp("osDataEncerramento",
									ordensServico.getOsDataEncerramento())
							.setInteger("servicoId",
									ordensServico.getServicoId())
							
							
									
							.setBigDecimal(
									"osAreaPavimentoRua",
									ordensServico.getOsAreaPvtoRua() == null ? null
											: new BigDecimal(ordensServico
													.getOsAreaPvtoRua()))
							
									
							.setBigDecimal(
									"osAreaPavimentoCalcada",
									ordensServico.getOsAreaPvtoCalcada() == null ? null
											: new BigDecimal(ordensServico
													.getOsAreaPvtoCalcada()))
							.setString(
									"osParecerEncerramento",
									ordensServico.getOsParecerEncerramento() == null ? ""
											: ordensServico
													.getOsParecerEncerramento())
							.setInteger("id", ordensServico.getId());
							
							
					if (ordensServico.getOsPavimentoRua() != null) {		
						query.setParameter("osPavimentoRua", new PavimentoRua(ordensServico.getOsPavimentoRua()));
							
					}
					
					if (ordensServico.getOsPavimentoCalcada() != null) {
						query.setParameter("osPavimentoCalcada", new PavimentoCalcada(ordensServico.getOsPavimentoCalcada()));
					}
					
					query.executeUpdate();

					// Caso exista dados de Pavimento - atualizar
					// ORDEM_SERVICO_PAVIMENTO
					if (ordensServico.getOsAreaPvtoCalcada() != null
							|| ordensServico.getOsAreaPvtoRua() != null) {
						session
								.createQuery(
										"update OrdemServicoPavimento set"
												+ " pavimentoRuaRetorno =:osPavimentoRua,"
												+ " pavimentoCalcadaRetorno =:osPavimentoCalcada,"
												+ " areaPavimentoRuaRetorno =:osAreaPavimentoRua,"
												+ " areaPavimentoCalcadaRetorno =:osAreaPavimentoCalcada where ordemServico =:ordemServicoId")
								.setInteger("ordemServicoId",
										ordensServico.getId())
								.setParameter("osPavimentoRua",
									ordensServico.getOsPavimentoRua() == null ? null
											: new PavimentoRua(ordensServico.getOsPavimentoRua())
									)
								.setBigDecimal(
										"osAreaPavimentoRua",
										ordensServico.getOsAreaPvtoRua() == null ? null
												: new BigDecimal(ordensServico
														.getOsAreaPvtoRua()))
								.setParameter("osPavimentoCalcada",
									ordensServico.getOsPavimentoCalcada() == null ? null
											: new PavimentoCalcada(ordensServico.getOsPavimentoCalcada()))
								.setBigDecimal(
										"osAreaPavimentoCalcada",
										ordensServico.getOsAreaPvtoCalcada() == null ? null
												: new BigDecimal(ordensServico
														.getOsAreaPvtoCalcada()))
								.executeUpdate();

					}

					//System.out.println(resultado);
				}

			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}

	}

	public void atualizarIndicadorMovimentoOrdemServicoMovimento(
			OrdemServicoMovimento movimento) throws ErroRepositorioException {
		//		 obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			session.update(movimento);
			session.flush();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}
	
	public Object[] pesquisarHorarioProcessoIntegracaoUPA()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno;

		try {

			retorno = (Object[]) session
					.createQuery(

					"select horaInicioProcesso, intervaloHorasProcesso from SistemaParametro")
					.uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcImovelRetorno> pesquisarAtcImovelRetorno(int quantidadeRegistros) throws ErroRepositorioException{
		
		Collection<AtcImovelRetorno> atcImovelRetorno = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcImovelRetorno "
					 +	"FROM AtcImovelRetorno atcImovelRetorno "
					 +	"WHERE atcImovelRetorno.acaImoDefinitivo = :verdadeiro "
					 +  "AND atcImovelRetorno.acaImoDataHoraImportacao is null ";
			
			atcImovelRetorno = (Collection<AtcImovelRetorno>) session.createQuery(consulta)
							.setMaxResults(quantidadeRegistros)
							.setBoolean("verdadeiro", true).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcImovelRetorno;
	}
	
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteRetorno> pesquisarAtcClienteRetorno( Integer idCliente) throws ErroRepositorioException{
		
		Collection<AtcClienteRetorno> atcClienteRetorno = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcClienteRetorno "
					 +	"FROM AtcClienteRetorno atcClienteRetorno "
					 +	"WHERE atcClienteRetorno.comp_id.atcCliId = :idCliente ";
			
			atcClienteRetorno = (Collection<AtcClienteRetorno>) session.createQuery(consulta)
							.setLong("idCliente", idCliente)
							.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcClienteRetorno;
	}
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteImovelRetorno> pesquisarAtcClienteImovelRetorno(Integer idImovel) throws ErroRepositorioException{
		
		Collection<AtcClienteImovelRetorno> atcClienteImovelRetorno = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcClienteImovelRetorno "
					 +	"FROM AtcClienteImovelRetorno atcClienteImovelRetorno "
					 +	"WHERE atcClienteImovelRetorno.atcImoId = :idImovel ";
			
			atcClienteImovelRetorno = (Collection<AtcClienteImovelRetorno>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel)
							.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcClienteImovelRetorno;
	}
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteTelefoneRetorno> pesquisarAtcClienteTelefoneRetorno(Long idCliente) throws ErroRepositorioException{
		
		Collection<AtcClienteTelefoneRetorno> atcClienteTelefoneRetorno = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT distinct(atcClienteTelefoneRetorno) "
					 +	"FROM AtcClienteTelefoneRetorno atcClienteTelefoneRetorno "
					 +	"WHERE atcClienteTelefoneRetorno.atcCliId = :idCliente ";
			
			atcClienteTelefoneRetorno = (Collection<AtcClienteTelefoneRetorno>) session.createQuery(consulta)
							.setLong("idCliente", idCliente).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcClienteTelefoneRetorno;
	}
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcSubcategoriaImovelRetorno> pesquisarAtcSubcategoriaImovelRetorno(Integer idImovel) throws ErroRepositorioException{
		
		Collection<AtcSubcategoriaImovelRetorno> atcSubcategoriaImovelRetorno = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcSubcategoriaImovelRetorno "
					 +	"FROM AtcSubcategoriaImovelRetorno atcSubcategoriaImovelRetorno "
					 +	"WHERE atcSubcategoriaImovelRetorno.atcImoId = :idImovel ";
			
			atcSubcategoriaImovelRetorno = (Collection<AtcSubcategoriaImovelRetorno>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcSubcategoriaImovelRetorno;
	}
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcHidrometroInstalacaoRetorno> pesquisarAtcHidrometroInstalacaoRetorno(Integer idImovel) throws ErroRepositorioException{
		
		Collection<AtcHidrometroInstalacaoRetorno> atcHidrometroInstalacaoRetorno = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcHidrometroInstalacaoRetorno "
					 +	"FROM AtcHidrometroInstalacaoRetorno atcHidrometroInstalacaoRetorno "
					 +	"WHERE atcHidrometroInstalacaoRetorno.atcImoId = :idImovel ";
			
			atcHidrometroInstalacaoRetorno = (Collection<AtcHidrometroInstalacaoRetorno>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcHidrometroInstalacaoRetorno;
	}
	
	/**
	 * Metodo generico para atualizar os objetod na base atualizacao_cadastral_admin
	 * 
	 * @author Arthur Carvalho
	 */
	public void atualizarIntegracaoAtualizacaoCadastral(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();

		try {

			session.update(objeto);
			session.flush();

		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	
	/**
	 * Metodo generico para atualizar os objetod na base atualizacao_cadastral_admin
	 * 
	 * @author Arthur Carvalho
	 */
	public Object inserirIntegracaoAtualizacaoCadastral(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();

		Object retorno = null;

		try {

			retorno = session.save(objeto);
			session.flush();

			return retorno;
		} catch (GenericJDBCException ex) {
			throw new ErroRepositorioException(ex, "Erro no Hibernate");
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarAtcImovel(Integer idImovel) throws ErroRepositorioException{
		
		Integer retorno = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT count(atcImovel.atcImoId) "
					 +	"FROM AtcImovel atcImovel "
					 +	"WHERE atcImovel.atcImoId = :idImovel ";
			
			retorno = (Integer) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		if ( retorno != null && retorno.intValue() > 0){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcClienteImovel> pesquisarAtcClienteImovel(Integer idImovel) throws ErroRepositorioException{
		
		Collection<AtcClienteImovel> atcClienteImovel = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcClienteImovel "
					 +	"FROM AtcClienteImovel atcClienteImovel "
					 +	"WHERE atcClienteImovel.atcImoId = :idImovel ";
			
			atcClienteImovel = (Collection<AtcClienteImovel>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcClienteImovel;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcClienteTelefone(Integer idCliente) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try {
			String delete = "delete AtcClienteTelefone as atcClienteTelefone "
					+ "where atcClienteTelefone.atcCliId = :idCliente ";

			session.createQuery(delete).setLong("idCliente", idCliente).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce??o para a pr?xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess?o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcCliente(Integer idCliente) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try {
			String delete = "delete AtcCliente as atcCliente "
					+ "where atcCliente.atcCliId = :idCliente ";

			session.createQuery(delete).setLong("idCliente", idCliente).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce??o para a pr?xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess?o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcClienteImovel(Integer idAtcClienteImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try {
			String delete = "delete AtcClienteImovel as atcClienteImovel "
					+ "where atcClienteImovel.atcCimId = :idAtcClienteImovel ";

			session.createQuery(delete).setInteger("idAtcClienteImovel", idAtcClienteImovel).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce??o para a pr?xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess?o
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcSubcategoriaImovel(Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try {
			String delete = "delete AtcSubcategoriaImovel as atcSubcategoriaImovel where atcSubcategoriaImovel.atcImoId = :idImovel ";

			session.createQuery(delete).setInteger("idImovel", idImovel).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce??o para a pr?xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess?o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcHidrometroInstalacao(Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try {
			String delete = "delete AtcHidrometroInstalacao as atcHidrometroInstalacao where atcHidrometroInstalacao.atcImoId = :idImovel ";

			session.createQuery(delete).setInteger("idImovel", idImovel).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce??o para a pr?xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess?o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarAtcImovel(Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try {
			String delete = "delete AtcImovel as atcImovel where atcImovel.atcImoId = :idImovel ";

			session.createQuery(delete).setInteger("idImovel", idImovel).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce??o para a pr?xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess?o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeAtcCliente(Long idCliente) throws ErroRepositorioException{
		
		Integer atcClienteImovel = null;
		String consulta = "";
		
		boolean retorno = false;
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT count(atcCliente.atcCliId) "
					 +	"FROM AtcCliente atcCliente "
					 +	"WHERE atcCliente.atcCliId = :idCliente ";
			
			atcClienteImovel = (Integer) session.createQuery(consulta)
							.setLong("idCliente", idCliente).uniqueResult();
			
			if ( atcClienteImovel != null && atcClienteImovel.intValue() > 0) {
				retorno = true;
			}
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * [SB0001]-Inserir Bairro 
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcBairro> pesquisarAtcBairro(String bairroAtualizado) throws ErroRepositorioException{
		
		Collection<AtcBairro> atcBairro = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcBairro "
					 +	"FROM AtcBairro atcBairro "
					 +	"WHERE atcBairro.atcBaiAtualizado = :atualizado "
					 +  "AND atcBairro.atcBaiDataHoraImportacao is null";
			
			atcBairro = (Collection<AtcBairro>) session.createQuery(consulta)
							.setString("atualizado", bairroAtualizado).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcBairro;
	}
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * [SB0003]-Inserir Logradouro 
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AtcLogradouro> pesquisarAtcLogradouro(String logradouroAtualizado) throws ErroRepositorioException {
		
		Collection<AtcLogradouro> atcLogradouro = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcLogradouro "
					 +	"FROM AtcLogradouro atcLogradouro "
					 +	"WHERE atcLogradouro.atcLgrAtualizado = :atualizado "
					 +  "AND atcLogradouro.atcLgrDataHoraImportacao is null";
			
			atcLogradouro = (Collection<AtcLogradouro>) session.createQuery(consulta)
							.setString("atualizado", logradouroAtualizado).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcLogradouro;
	}
	
	/**
	 * [UC1323] Rotina de Realizar Recepção de Dados Admin
	 * [SB0003]-Inserir Logradouro 
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AcaAtualizadores> pesquisarAcaAtualizadores() throws ErroRepositorioException {
		
		Collection<AcaAtualizadores> atcLogradouro = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT acaAtualizadores "
					 +	"FROM AcaAtualizadores acaAtualizadores ";
			
			atcLogradouro = (Collection<AcaAtualizadores>) session.createQuery(consulta).list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcLogradouro;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public AtcBairro existeAtcBairro(Integer idBairro) throws ErroRepositorioException{
		
		AtcBairro atcBairro= null;
		String consulta = "";
		
		boolean retorno = false;
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcBairro "
					 +	"FROM AtcBairro atcBairro "
					 +	"WHERE atcBairro.atcBaiId = :idBairro ";
			
			atcBairro = (AtcBairro) session.createQuery(consulta)
							.setInteger("idBairro", idBairro).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcBairro;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public AtcLogradouro existeAtcLogradouro(Integer idLogradouro) throws ErroRepositorioException{
		
		AtcLogradouro atcLogradouro = null;
		String consulta = "";
		
		boolean retorno = false;
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT atcLogradouro "
					 +	"FROM AtcLogradouro atcLogradouro "
					 +	"WHERE atcLogradouro.atcLgrId = :idLogradouro ";
			
			atcLogradouro = (AtcLogradouro) session.createQuery(consulta)
							.setInteger("idLogradouro", idLogradouro).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return atcLogradouro;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeAtcMunicipio(Integer idMunicipio) throws ErroRepositorioException{
		
		Integer atcMunicipio = null;
		String consulta = "";
		
		boolean retorno = false;
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		try{
			consulta =  "SELECT count(atcMunicipio.atcMunId) "
					 +	"FROM AtcMunicipio atcMunicipio "
					 +	"WHERE atcMunicipio.atcMunId = :idMunicipio ";
			
			atcMunicipio = (Integer) session.createQuery(consulta)
							.setInteger("idMunicipio", idMunicipio).uniqueResult();
			
			if ( atcMunicipio != null && atcMunicipio.intValue() > 0) {
				retorno = true;
			}
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC 1315] Gerar Resumo da Posição de Atualização Cadastral por Pacote Enviados
	 * 
	 * @author Arthur Carvalho
	 * @since 06/06/2012
	 * 
	 * @param Helper, ptac_id
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarQuantidadeImoveisVisitados(DadosResumoMovimentoAtualizacaoCadastralHelper helper, Date dataMovimento) throws ErroRepositorioException{
		
		Object[] quantidade = new Object[2];
		
		Session session = HibernateUtil.getSessionIntegracaoAtualizacaoCadastral();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			
			consulta += "Select count( case when atcImovelRetorno.atc_imo_sit_cad = 0 or atcImovelRetorno.atc_imo_sit_cad =99 then 1 end) as qtdImoNaoVisitados, "
					+ "count( case when atcImovelRetorno.atc_imo_sit_cad <> 0 and atcImovelRetorno.atc_imo_sit_cad <> 99 then 1  end) as qtdImoVisitados "
					+ "from public.atc_imovel_retorno atcImovelRetorno " 
					+ "where atcImovelRetorno.atc_loc_id = :idLocalidade ";
			
			parameters.put("idLocalidade", Integer.parseInt(helper.getIdLocalidadeInicial()));
			
			if(dataMovimento != null){
				consulta += " and to_date(to_char(aca_imo_data_hora_importacao,'YYYY/MM/DD'),'YYYY/MM/DD') = :dataMovimento ";
				parameters.put("dataMovimento", dataMovimento);
			}
			
			if(helper.getIdSetorComercialInicial() != null && helper.getIdSetorComercialFinal() != null){
				consulta += "and atcImovelRetorno.atc_nst_id >= :idSetorInicial AND atcImovelRetorno.atc_nst_id <= :idSetorFinal ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				parameters.put("idSetorFinal", Integer.parseInt(helper.getIdSetorComercialFinal()));
			}
			
			if(helper.getIdQuadraInicial() != null && helper.getIdQuadraFinal() != null){
				consulta += "and atcImovelRetorno.atc_nqd_id >= :idQuadraInicial AND atcImovelRetorno.atc_nqd_id <= :idQuadraFinal ";
				parameters.put("idQuadraInicial", Integer.parseInt(helper.getIdQuadraInicial()));
				parameters.put("idQuadraFinal", Integer.parseInt(helper.getIdQuadraFinal()));
			}
			
			consulta += " group by atc_loc_id ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("qtdImoNaoVisitados", Hibernate.INTEGER)
					.addScalar("qtdImoVisitados", Hibernate.INTEGER);
					 
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
			
			quantidade = (Object[]) query.setMaxResults(1).uniqueResult();
			
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return quantidade;
	}
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SBXXXX] Limpar os dados da integração com sistema IFS para COMPESA
	 * para evitar duplicação
	 *
	 * @author Carlos Chaves
	 * @date 05/05/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public void apagarRegistrosIntegracao(String text,Date transaction_date)
			throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSessionIntegracaoIFS();
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		String delete = null;
		try {
			con = session.connection();

			delete = "DELETE " +
					"FROM IFSCPSA.C_GSAN_CONTABILIDADE_TAB  " +
					"WHERE TRANSACTION_DATE = ? AND TEXT = ?"; 
			
			stmt = con.prepareStatement(delete);
			stmt.setDate(1,  Util.getSQLDate(transaction_date));
			stmt.setString(2, text);
			stmt.executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
			try {
				if(stmt != null){
					stmt.close();	
				}
				if(con != null){
					con.close();	
				}

			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			}
		}
	}

	/**
	 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
	 * [IT0002] - Selecionar Grupo
	 * 
	 * @author Diogo Luiz
	 * @Date 15/10/2013
	 * 
	 */
	public Collection pesquisarRelatorioCarroPipa(Integer mesAnoReferencia) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		Collection colecao = null;		
		
		try {
			String consulta = " select "
					+ " sum(qtde_enviados) as qtde_enviados, "
					+ " sum(qtde_com_contrato) as qtde_com_contrato, "
					+ " sum(qtde_sem_contrato) as qtde_sem_contrato, "
					+ " sum(qtde_faturaveis) as qtde_faturaveis, "
					+ " sum(qtde_nao_faturaveis) as qtde_nao_faturaveis, "
					+ " gerenciaRegional, "
					+ " nomeGerencia, "
					+ " sum(somatorioValorDebitoCobrar) as somatorioValorDebitoCobrar, "
					+ " sum(somatorioVolumeDebitoCobrar) as somatorioVolumeDebitoCobrar, "
					+ " sum(TotalAguaNaoFaturavelVolume) as TotalAguaNaoFaturavelVolume, "
					+ " sum(TotalAguaNaoFaturavelValor) as TotalAguaNaoFaturavelValor, " 
					+ " sum(valorGuiaPagamento) as valorGuiaPagamento, "
					+ " sum(totalAguaGPipaValor) as totalAguaGPipaValor, " 
					+ " sum(totalAguaGPipaVolume) as totalAguaGPipaVolume, " 
					+ " sum(valorDebitoACobrar) as valorDebitoACobrar "
					+ " from ( "
					+ "select "
					+ " qtde_enviados, " // 00
					+ " qtde_com_contrato, " // 01
					+ " qtde_sem_contrato, " // 02
					+ " qtde_faturaveis, " // 03
					+ " qtde_nao_faturaveis, " // 04
					+ " gerenciaRegional, " //05
					+ " nomeGerencia, " //06
					+ " somatorioValorDebitoCobrar, " //07
					+ " somatorioVolumeDebitoCobrar, " //08
					+ " TotalAguaNaoFaturavelVolume, " //09
					+ " TotalAguaNaoFaturavelValor, " //10					
					+ " (COALESCE((Select sum(coalesce(GPAG_VLDEBITO,0)) As Vl_Guia"
					+ " from faturamento.guia_pagamento gp "
					+ " inner join cadastro.localidade loc on loc.loca_id = gp.loca_id "
					+ " where loc.greg_id = temp.gerenciaRegional "
					+ " and gpag_amReferenciaContabil = :mesAnoReferencia "
					+ " and dbtp_id in (20,103) group by loc.greg_id),0)) "
					+ " + "
					+ " (COALESCE((SELECT SUM(coalesce(GPHI_VLDEBITO,0))  "
					+ " FROM faturamento.guia_pagamento_historico gph "
					+ " inner join cadastro.localidade loc on loc.loca_id = gph.loca_id "
					+ " where loc.greg_id             = temp.gerenciaRegional "
					+ " and gphi_amReferenciaContabil = :mesAnoReferencia "
					+ " AND dbtp_id IN (20,103) group by loc.greg_id),0 )) as valorGuiaPagamento, "	//11					
					+ " totalAguaGPipaValor, " //12
					+ " totalAguaGPipaVolume, " //13
					+ " (COALESCE((Select sum(coalesce(DBAC_VLDEBITO,0)) As Vl_Debito "
					+ " from faturamento.debito_a_cobrar db "
					+ " inner join cadastro.localidade loc on loc.loca_id = db.loca_id "
					+ " where loc.greg_id = temp.gerenciaRegional "
					+ " and DBAC_AMREFERENCIACONTABIL = :mesAnoReferencia "
					+ " and dbtp_id in (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) group by loc.greg_id),0)) "
					+ " + "
					+ " (COALESCE((SELECT SUM(coalesce(DAHI_VLDEBITO,0)) "
					+ " FROM faturamento.deb_a_cobrar_hist dbh "
					+ " inner join cadastro.localidade loc on loc.loca_id = dbh.loca_id "
					+ " where loc.greg_id             = temp.gerenciaRegional "
					+ " and dahi_amreferenciacontabil = :mesAnoReferencia "
					+ " AND dbtp_id IN (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) group by loc.greg_id),0 )) as valorDebitoACobrar "	//14
					+ " from( select count(*) as qtde_enviados, "
					+ " count(case when cpab_icabastecimentoavulso = 2 then 1 end) as qtde_com_contrato, "
					+ " count(case when cpab_icabastecimentoavulso = 1 then 1 end) as qtde_sem_contrato, "
					+ " count(case when cpab_iccobranca            = 1 then 1 end) as qtde_faturaveis, "
					+ " count(case when cpab_iccobranca            = 2 then 1 end) as qtde_nao_faturaveis, "
					+ " loc.greg_id as gerenciaRegional, "
					+ " gr.greg_nmregional as nomeGerencia, "
					+ " COALESCE(sum(case when cpab_iccobranca = 1 then cpab_vlabastecimento end),0) as somatorioValorDebitoCobrar, "
					+ " COALESCE(sum(case when cpab_iccobranca = 1 then cpab_nnvolumeabastecimento end),0) as somatorioVolumeDebitoCobrar, "
					+ " COALESCE(sum(case when cpab_iccobranca = 2 then cpab_nnvolumeabastecimento end),0) as TotalAguaNaoFaturavelVolume, "
					+ " COALESCE(sum(case when cpab_iccobranca = 2 then cpab_vlabastecimento end),0) as TotalAguaNaoFaturavelValor, "
					+ " COALESCE(sum(cpab_vlabastecimento),0) as totalAguaGPipaValor, "
					+ " COALESCE(sum(cpab_nnvolumeabastecimento),0) as totalAguaGPipaVolume "
					+ " from INTEGRACAO.carro_pipa_abastecimento cpa "
					+ " inner join cadastro.imovel im on im.imov_id = cpa.imov_id "
					+ " inner join cadastro.localidade loc on loc.loca_id = im.loca_id "
					+ " inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
					+ " inner join micromedicao.rota rt on rt.rota_id = qd.rota_id "
					+ " inner join cadastro.gerencia_regional gr on loc.greg_id = gr.greg_id "
					+ " where cpab_amreferenciafaturamento = :mesAnoReferencia 	"				
					+ " group by loc.greg_id, gr.greg_nmregional ) temp "
					
					+ " UNION ALL " 
					
					+ "select 0, 0, 0, 0, 0, id_gerencia, nome_gerencia, "
					+ " 0, 0, 0, 0, COALESCE(sum(valor_guia),0), 0, 0, 0 from ( select "
					+ " loc.greg_id as id_gerencia, greg_nmregional as nome_gerencia, "
					+ " COALESCE(sum(gpag_vldebito),0) as valor_guia "
					+ " from faturamento.guia_pagamento gp "
					+ " inner join cadastro.localidade loc on loc.loca_id = gp.loca_id "
					+ " inner join cadastro.gerencia_regional gr on gr.greg_id = loc.greg_id "
					+ " where gp.gpag_amreferenciacontabil = :mesAnoReferencia and gp.dbtp_id in (20,103) "
					+ " group by loc.greg_id, greg_nmregional union all select loc.greg_id, "
					+ " greg_nmregional, COALESCE(sum(gphi_vldebito),0) from faturamento.guia_pagamento_historico gp "
					+ " inner join cadastro.localidade loc on loc.loca_id = gp.loca_id "
					+ " inner join cadastro.gerencia_regional gr on gr.greg_id = loc.greg_id "
					+ " where gp.gphi_amreferenciacontabil = :mesAnoReferencia and gp.dbtp_id in (20,103) "
					+ " group by  loc.greg_id,greg_nmregional ) temp where id_gerencia not in "
					+ " (select greg_id from integracao.carro_pipa_abastecimento cpa "
					+ " inner join cadastro.imovel im on im.imov_id = cpa.imov_id "
					+ " inner join cadastro.localidade loc on loc.loca_id = im.loca_id "
					+ " where  CPAB_AMREFERENCIAFATURAMENTO = :mesAnoReferencia group by greg_id) "
					+ " group by id_gerencia, nome_gerencia "
					
			 		+ " UNION ALL "
		
					+ " select 0, 0, 0, 0, 0, id_gerencia, nome_gerencia, "
					+ " 0, 0, 0, 0, 0, 0, 0, COALESCE(sum(valor_debito),0) "
					+ " from ( select loc.greg_id as id_gerencia, greg_nmregional as nome_gerencia, "
					+ " COALESCE(sum(dbac_vldebito),0) as valor_debito "
					+ " from faturamento.debito_a_cobrar db "
					+ " inner join cadastro.localidade loc on loc.loca_id = db.loca_id "
					+ " inner join cadastro.gerencia_regional gr on gr.greg_id = loc.greg_id "
					+ " where db.dbac_amreferenciacontabil = :mesAnoReferencia and db.dbtp_id in (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) "
					+ " group by loc.greg_id, greg_nmregional "
					+ " union all "
					+ " select loc.greg_id, greg_nmregional, COALESCE(sum(dahi_vldebito),0) "
					+ " from faturamento.deb_a_cobrar_hist dbh "
					+ " inner join cadastro.localidade loc on loc.loca_id = dbh.loca_id "
					+ " inner join cadastro.gerencia_regional gr on gr.greg_id = loc.greg_id "
					+ " where dbh.dahi_amreferenciacontabil = :mesAnoReferencia and dbh.dbtp_id in (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) "
					+ " group by  loc.greg_id,greg_nmregional ) temp "
					+ " where id_gerencia not in "
					+ " (select greg_id from integracao.carro_pipa_abastecimento cpa "
					+ " inner join cadastro.imovel im on im.imov_id = cpa.imov_id "
					+ " inner join cadastro.localidade loc on loc.loca_id = im.loca_id "
					+ " where  CPAB_AMREFERENCIAFATURAMENTO = :mesAnoReferencia group by greg_id) "
					+ " group by id_gerencia, nome_gerencia order by 1,2) "
					+ " group by gerenciaRegional, nomeGerencia order by 1,2 " ;
					
			
			colecao = (Collection) session.createSQLQuery(consulta)
					.addScalar("qtde_enviados", Hibernate.INTEGER)
					.addScalar("qtde_com_contrato", Hibernate.INTEGER)
					.addScalar("qtde_sem_contrato", Hibernate.INTEGER)
					.addScalar("qtde_faturaveis", Hibernate.INTEGER)
					.addScalar("qtde_nao_faturaveis", Hibernate.INTEGER)
					.addScalar("gerenciaRegional", Hibernate.INTEGER)
					.addScalar("nomeGerencia", Hibernate.STRING)
					.addScalar("somatorioValorDebitoCobrar", Hibernate.BIG_DECIMAL)
					.addScalar("somatorioVolumeDebitoCobrar", Hibernate.BIG_DECIMAL)
					.addScalar("TotalAguaNaoFaturavelVolume", Hibernate.BIG_DECIMAL)
					.addScalar("TotalAguaNaoFaturavelValor", Hibernate.BIG_DECIMAL)
					.addScalar("valorGuiaPagamento" , Hibernate.BIG_DECIMAL)
					.addScalar("totalAguaGPipaValor", Hibernate.BIG_DECIMAL)
					.addScalar("totalAguaGPipaVolume", Hibernate.BIG_DECIMAL)
					.addScalar("valorDebitoACobrar" , Hibernate.BIG_DECIMAL)
					.setInteger("mesAnoReferencia", mesAnoReferencia)
					.list();			
			
			return colecao;
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}			
	}
	
	
	/**
	 * [UC1565] Gerar Relatório Pagamentos Abastecimento Carro Pipa
	 * [IT0002] - Selecionar Grupo
	 * 
	 * @author Diogo Luiz
	 * @Date 15/10/2013
	 * 
	 */
	public Collection pesquisarRelatorioCarroPipaGRupoFaturamento(Integer idFaturamentoGrupo, Integer mesAnoReferencia) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		
	try{
		String consulta = " select sum(qtde_enviados) as qtde_enviados, " //00
				+ " sum(qtde_com_contrato) as qtde_com_contrato ,  " //01
				+ " sum(qtde_sem_contrato) as qtde_sem_contrato, " //02
				+ " sum(qtde_faturaveis) as qtde_faturaveis, " //03
				+ " sum(qtde_nao_faturaveis) as qtde_nao_faturaveis, " //04
				+ " sum(somatorioValorDebitoCobrar) as somatorioValorDebitoCobrar, " //05
				+ " sum(somatorioVolumeDebitoCobrar) as somatorioVolumeDebitoCobrar, " //06
				+ " sum(totalaguanaofaturavelvolume) as totalaguanaofaturavelvolume, " //07
				+ " sum(totalaguanaofaturavelvalor) as totalaguanaofaturavelvalor, " //08
				+ " sum(totalAguaGPipaValor) as totalAguaGPipaValor, " //09
				+ " sum(totalAguaGPipaVolume)as totalAguaGPipaVolume, " //10
				+ " sum(valorGuiaPagamento) as valorGuiaPagamento, " //11
				+ " sum(valorDebitoACobrar) as valorDebitoACobrar " //12
				+ " from (SELECT sub.*, "
				+ " 	       ( select coalesce(sum(vl_guia),0) as valor_guia "
				+ " 			 FROM(SELECT imov_id, SUM(GPAG_VLDEBITO) AS vl_guia "
				+ "       		      FROM faturamento.guia_pagamento gp "
				+ "       		      WHERE GPAG_AMREFERENCIACONTABIL = :mesAnoReferencia "
				+ "       			  AND dbtp_id IN (20,103) GROUP BY imov_id "
				+ " 			union all "
				+ "       			  SELECT imov_id, SUM(GPHI_VLDEBITO) "
				+ "       		      FROM faturamento.guia_pagamento_historico "
				+ "       		      WHERE gphi_amreferenciacontabil = :mesAnoReferencia "
				+ "       			   AND dbtp_id IN (20,103) GROUP BY imov_id ) temp2 "
				+ "       		INNER JOIN integracao.carro_pipa_abastecimento cpa ON cpa.imov_id = temp2.imov_id "
				+ "                                                          	   and cpab_amreferenciafaturamento = :mesAnoReferencia  "
				+ " 			INNER JOIN cadastro.imovel im on im.imov_id = cpa.imov_id "
				+ " 			INNER JOIN cadastro.quadra qd ON qd.qdra_id = im.qdra_id "
				+ " 			INNER JOIN micromedicao.rota rt ON rt.rota_id = qd.rota_id "
				+ " 											and rt.ftgr_id = :idFaturamentoGrupo "
				+ "             ) AS valorGuiaPagamento, "
				+ "			  ( select coalesce(sum(vl_debito),0) as valor_debito "
			 	+ "				 FROM(SELECT imov_id, SUM(DBAC_VLDEBITO) AS vl_debito "
			    + "   		     	 FROM faturamento.debito_a_cobrar db "
			    + "   		      WHERE DBAC_AMREFERENCIACONTABIL = :mesAnoReferencia "
			    + "   			  AND dbtp_id IN (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) GROUP BY imov_id "
			 	+ "		union all "
			    + "   			  SELECT imov_id, SUM(DAHI_VLDEBITO) "
			    + "   		      FROM FATURAMENTO.deb_a_cobrar_hist "
			    + "   		      WHERE dahi_amreferenciacontabil = :mesAnoReferencia "
			    + "   			   AND dbtp_id IN (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) GROUP BY imov_id ) temp2 "
			    + "   		INNER JOIN integracao.carro_pipa_abastecimento cpa ON cpa.imov_id = temp2.imov_id "
			    + "                                                      	   and cpab_amreferenciafaturamento = :mesAnoReferencia  "
			 	+ "		INNER JOIN cadastro.imovel im on im.imov_id = cpa.imov_id "
			 	+ "		INNER JOIN cadastro.quadra qd ON qd.qdra_id = im.qdra_id "
			 	+ "		INNER JOIN micromedicao.rota rt ON rt.rota_id = qd.rota_id "
			 	+ "										and rt.ftgr_id = :idFaturamentoGrupo "
			    + "         ) AS valorDebitoACobrar "
				+ " 		FROM (SELECT COUNT(*) AS qtde_enviados, "
				+ " 			  COUNT(CASE WHEN cpab_icabastecimentoavulso = 2 THEN 1 END) AS qtde_com_contrato, "
				+ "               COUNT(CASE WHEN cpab_icabastecimentoavulso = 1 THEN 1 END) AS qtde_sem_contrato, "
				+ " 			  COUNT(CASE WHEN cpab_iccobranca = 1 THEN 1 END) AS qtde_faturaveis, "
				+ " 			  COUNT(CASE WHEN cpab_iccobranca = 2 THEN 1 END) AS qtde_nao_faturaveis, "
				+ " 			  SUM(CASE WHEN cpab_iccobranca = 1 THEN cpab_vlabastecimento END) AS somatorioValorDebitoCobrar, "
				+ " 			  SUM(CASE WHEN cpab_iccobranca = 1 THEN cpab_nnvolumeabastecimento END) AS somatorioVolumeDebitoCobrar, "
				+ " 			  SUM(CASE WHEN cpab_iccobranca = 2 THEN cpab_nnvolumeabastecimento END) AS totalaguanaofaturavelvolume, "
				+ " 			  SUM(CASE WHEN cpab_iccobranca = 2 THEN cpab_vlabastecimento END) AS totalaguanaofaturavelvalor, "
				+ " 			  SUM(cpab_vlabastecimento)       AS totalAguaGPipaValor, "
				+ " 			  SUM(cpab_nnvolumeabastecimento) AS totalAguaGPipaVolume "
				+ " 		      FROM INTEGRACAO.carro_pipa_abastecimento cpa "
				+ " 			  INNER JOIN cadastro.imovel im ON im.imov_id = cpa.imov_id "
				+ " 			  INNER JOIN cadastro.localidade loc ON loc.loca_id = im.loca_id "
				+ " 			  INNER JOIN cadastro.quadra qd ON qd.qdra_id = im.qdra_id "
				+ " 			  INNER JOIN micromedicao.rota rt ON rt.rota_id = qd.rota_id "
				+ "  			  INNER JOIN cadastro.gerencia_regional gr ON loc.greg_id = gr.greg_id "
				+ " 			  WHERE cpab_amreferenciafaturamento = :mesAnoReferencia "
				+ " 			        AND rt.ftgr_id = :idFaturamentoGrupo) sub "
				+ "		union all  "
				+ "		select 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, coalesce(sum(valor_guia),0),0 "
				+ " 	from (select sum(gpag_vldebito) as valor_guia "
				+ " 		  from faturamento.guia_pagamento gp "
				+ " 	      inner join cadastro.imovel im on im.imov_id = gp.imov_id "
				+ " 	      inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
				+ " 		  inner join micromedicao.rota rt on rt.rota_id = qd.rota_id and ftgr_id = :idFaturamentoGrupo "
				+ " 		  where gpag_amreferenciacontabil = :mesAnoReferencia and gp.dbtp_id IN (20,103) "
				+ " 		  									and gp.imov_id  not in  "
				+ " 											(select imov_id from INTEGRACAO.carro_pipa_abastecimento "
				+ " 											where CPAB_AMREFERENCIAFATURAMENTO = :mesAnoReferencia)  "
				
				+ "           union all "
				+ " 		  select sum(gphi_vldebito) "
				+ " 		  from faturamento.guia_pagamento_historico gp "
				+ " 		  inner join cadastro.imovel im on im.imov_id = gp.imov_id "
				+ " 		  inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
				+ " 		  inner join micromedicao.rota rt on rt.rota_id = qd.rota_id "
				+ " 		                                  and ftgr_id = :idFaturamentoGrupo "
				+ " 		  where gphi_amreferenciacontabil = :mesAnoReferencia AND gp.dbtp_id IN (20,103) "
				+ " 		  and gp.imov_id  not in (select imov_id from INTEGRACAO.carro_pipa_abastecimento "
				+ " 								  where CPAB_AMREFERENCIAFATURAMENTO = :mesAnoReferencia) )"
				+ "			  union all  "
				+ "  	select 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, coalesce(sum(valor_debito),0) "
				+ " 	from (select sum(dbac_vldebito) as valor_debito "
				+ " 		  from faturamento.debito_a_cobrar db "
				+ " 	      inner join cadastro.imovel im on im.imov_id = db.imov_id "
				+ " 	      inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
				+ " 		  inner join micromedicao.rota rt on rt.rota_id = qd.rota_id and ftgr_id = :idFaturamentoGrupo "
				+ " 		  where dbac_amreferenciacontabil = :mesAnoReferencia and db.dbtp_id IN (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) "
				+ " 		  									and db.imov_id  not in  "
				+ " 											(select imov_id from INTEGRACAO.carro_pipa_abastecimento "
				+ " 											where CPAB_AMREFERENCIAFATURAMENTO = :mesAnoReferencia) " 
				
				+ "           union all "
				+ " 		  select sum(dahi_vldebito) "
				+ " 		  from FATURAMENTO.deb_a_cobrar_hist dbh "
				+ " 		  inner join cadastro.imovel im on im.imov_id = dbh.imov_id "
				+ " 		  inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
				+ " 		  inner join micromedicao.rota rt on rt.rota_id = qd.rota_id "
				+ " 		                                  and ftgr_id = :idFaturamentoGrupo " 
				+ " 		  where dahi_amreferenciacontabil = :mesAnoReferencia AND dbh.dbtp_id IN (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) "
				+ " 		  and dbh.imov_id  not in (select imov_id from INTEGRACAO.carro_pipa_abastecimento "
				+ " 								  where CPAB_AMREFERENCIAFATURAMENTO = :mesAnoReferencia) "
				+ " 	) temp GROUP BY 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) sub2 " ;
		
		
		return (Collection) session.createSQLQuery(consulta)
				.addScalar("qtde_enviados", Hibernate.INTEGER)
				.addScalar("qtde_com_contrato", Hibernate.INTEGER)
				.addScalar("qtde_sem_contrato", Hibernate.INTEGER)
				.addScalar("qtde_faturaveis", Hibernate.INTEGER)
				.addScalar("qtde_nao_faturaveis", Hibernate.INTEGER)				
				.addScalar("somatorioValorDebitoCobrar", Hibernate.BIG_DECIMAL)
				.addScalar("somatorioVolumeDebitoCobrar", Hibernate.BIG_DECIMAL)
				.addScalar("totalaguanaofaturavelvolume", Hibernate.BIG_DECIMAL)
				.addScalar("totalaguanaofaturavelvalor", Hibernate.BIG_DECIMAL)				
				.addScalar("totalAguaGPipaValor", Hibernate.BIG_DECIMAL)
				.addScalar("totalAguaGPipaVolume", Hibernate.BIG_DECIMAL)
				.addScalar("valorGuiaPagamento" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDebitoACobrar" , Hibernate.BIG_DECIMAL)
				.setInteger("mesAnoReferencia", mesAnoReferencia)
				.setInteger("idFaturamentoGrupo" , idFaturamentoGrupo)
				.list();	
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}			
	}
	
	
	
	/**
	 * [UC1565] Gerar Relatorio Pagamentos Abastecimento Carro Pipa
	 * [IT0002] - Selecionar Gerencia e Unidade de Negócio
	 * 
	 * @author Diogo Luiz
	 * @Date 25/10/2013
	 * 
	 */
	
	public Collection pesquisarRelatorioCarroPipaGerenciaUnidade(Integer mesAnoReferencia,
			Integer idGerenciaRegional, Integer idUnidadeNegocio) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();		
		
		if ( idGerenciaRegional == null ){
			idGerenciaRegional = -1;
		}
		
		if ( idUnidadeNegocio == null ){
			idUnidadeNegocio = -1;
		}			
		
		try{
			String consulta = " select coalesce(sum(qtde_enviados),0) as qtde_enviados, " //00
					+ " coalesce(sum(qtde_com_contrato),0) as qtde_com_contrato ,  " //01
					+ " coalesce(sum(qtde_sem_contrato),0) as qtde_sem_contrato, " //02
					+ " coalesce(sum(qtde_faturaveis),0) as qtde_faturaveis, " //03
					+ " coalesce(sum(qtde_nao_faturaveis),0) as qtde_nao_faturaveis, " //04
					+ " coalesce(sum(somatorioValorDebitoCobrar),0) as somatorioValorDebitoCobrar, " //05
					+ " coalesce(sum(somatorioVolumeDebitoCobrar),0) as somatorioVolumeDebitoCobrar, " //06
					+ " coalesce(sum(totalaguanaofaturavelvolume),0) as totalaguanaofaturavelvolume, " //07
					+ " coalesce(sum(totalaguanaofaturavelvalor),0) as totalaguanaofaturavelvalor, " //08
					+ " coalesce(sum(totalAguaGPipaValor),0) as totalAguaGPipaValor, " //09
					+ " coalesce(sum(totalAguaGPipaVolume),0) as totalAguaGPipaVolume, " //10
					+ " coalesce(sum(valorGuiaPagamento),0) as valorGuiaPagamento, " //11
					+ " coalesce(sum(valorDebitoACobrar),0) as valorDebitoACobrar " //12
					+ " from (SELECT sub.*, "
					+ " 	       ( select coalesce(sum(vl_guia),0) as valor_guia "
					+ " 			 FROM(SELECT imov_id, SUM(GPAG_VLDEBITO) AS vl_guia "
					+ "       		      FROM faturamento.guia_pagamento gp "
					+ "                   inner join cadastro.localidade loc on loc.loca_id = gp.loca_id  ";
			
					if(idGerenciaRegional != null && idGerenciaRegional.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.greg_id = " + idGerenciaRegional;
					}
					
					if(idUnidadeNegocio != null && idUnidadeNegocio.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.uneg_id = " + idUnidadeNegocio;
					}	
			
			
					consulta += "       		      WHERE GPAG_AMREFERENCIACONTABIL = :mesAnoReferencia "
					+ "       			  AND dbtp_id IN (20,103) GROUP BY imov_id "
					+ " 			union all "
					+ "       			  SELECT imov_id, SUM(GPHI_VLDEBITO) "
					+ "       		      FROM faturamento.guia_pagamento_historico gph"
					+ " 				  inner join cadastro.localidade loc on loc.loca_id = gph.loca_id "; 
					
					if(idGerenciaRegional != null && idGerenciaRegional.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.greg_id = " + idGerenciaRegional;
					}
					
					if(idUnidadeNegocio != null && idUnidadeNegocio.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.uneg_id = " + idUnidadeNegocio;
					}	
					
					
					consulta += "       		      WHERE gphi_amreferenciacontabil = :mesAnoReferencia "
					+ "       			   AND dbtp_id IN (20,103) GROUP BY imov_id ) temp2 ";					
			
			
					consulta += "             ) AS valorGuiaPagamento,  "
					// Debito A Cobrar
					+ " ( select coalesce(sum(vl_debito),0) as valor_debito "
					+ " 			 FROM(SELECT imov_id, SUM(DBAC_VLDEBITO) AS vl_debito "
					+ "       		      FROM faturamento.debito_a_cobrar db "
					+ "                   inner join cadastro.localidade loc on loc.loca_id = db.loca_id  ";
			
					if(idGerenciaRegional != null && idGerenciaRegional.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.greg_id = " + idGerenciaRegional;
					}
					
					if(idUnidadeNegocio != null && idUnidadeNegocio.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.uneg_id = " + idUnidadeNegocio;
					}	
			
			
					consulta += "       		      WHERE DBAC_AMREFERENCIACONTABIL = :mesAnoReferencia "
					+ "       			  AND dbtp_id IN (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) GROUP BY imov_id "
					+ " 			union all "
					+ "       			  SELECT imov_id, SUM(DAHI_VLDEBITO) "
					+ "       		      FROM faturamento.deb_a_cobrar_hist dbh"
					+ " 				  inner join cadastro.localidade loc on loc.loca_id = dbh.loca_id "; 
					
					if(idGerenciaRegional != null && idGerenciaRegional.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.greg_id = " + idGerenciaRegional;
					}
					
					if(idUnidadeNegocio != null && idUnidadeNegocio.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.uneg_id = " + idUnidadeNegocio;
					}	
					
					
					consulta += "       		      WHERE dahi_amreferenciacontabil = :mesAnoReferencia "
					+ "       			   AND dbtp_id IN (20,103) AND (RGAT_ID IS NOT NULL OR ORSE_ID IS NOT NULL) GROUP BY imov_id ) temp2 ";					
			
			
					consulta += "             ) AS valorDebitoACobrar "		
					+ " 		FROM (SELECT COUNT(*) AS qtde_enviados, "
					+ " 			  COUNT(CASE WHEN cpab_icabastecimentoavulso = 2 THEN 1 END) AS qtde_com_contrato, "
					+ "               COUNT(CASE WHEN cpab_icabastecimentoavulso = 1 THEN 1 END) AS qtde_sem_contrato, "
					+ " 			  COUNT(CASE WHEN cpab_iccobranca = 1 THEN 1 END) AS qtde_faturaveis, "
					+ " 			  COUNT(CASE WHEN cpab_iccobranca = 2 THEN 1 END) AS qtde_nao_faturaveis, "
					+ " 			  SUM(CASE WHEN cpab_iccobranca = 1 THEN cpab_vlabastecimento END) AS somatorioValorDebitoCobrar, "
					+ " 			  SUM(CASE WHEN cpab_iccobranca = 1 THEN cpab_nnvolumeabastecimento END) AS somatorioVolumeDebitoCobrar, "
					+ " 			  SUM(CASE WHEN cpab_iccobranca = 2 THEN cpab_nnvolumeabastecimento END) AS totalaguanaofaturavelvolume, "
					+ " 			  SUM(CASE WHEN cpab_iccobranca = 2 THEN cpab_vlabastecimento END) AS totalaguanaofaturavelvalor, "
					+ " 			  SUM(cpab_vlabastecimento)       AS totalAguaGPipaValor, "
					+ " 			  SUM(cpab_nnvolumeabastecimento) AS totalAguaGPipaVolume "
					+ " 		      FROM INTEGRACAO.carro_pipa_abastecimento cpa "
					+ " 			  INNER JOIN cadastro.imovel im ON im.imov_id = cpa.imov_id "
					+ " inner join cadastro.localidade loc on loc.loca_id = im.loca_id ";					
					
					if(idGerenciaRegional != null && idGerenciaRegional.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.greg_id = " + idGerenciaRegional;
					}
					
					if(idUnidadeNegocio != null && idUnidadeNegocio.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						consulta += " and loc.uneg_id = " + idUnidadeNegocio;
					}						
					
					consulta += " 			  WHERE cpab_amreferenciafaturamento = :mesAnoReferencia ) sub )";					
					
			
			Collection colecao = (Collection) session.createSQLQuery(consulta)
					.addScalar("qtde_enviados", Hibernate.INTEGER)
					.addScalar("qtde_com_contrato", Hibernate.INTEGER)
					.addScalar("qtde_sem_contrato", Hibernate.INTEGER)
					.addScalar("qtde_faturaveis", Hibernate.INTEGER)
					.addScalar("qtde_nao_faturaveis", Hibernate.INTEGER)				
					.addScalar("somatorioValorDebitoCobrar", Hibernate.BIG_DECIMAL)
					.addScalar("somatorioVolumeDebitoCobrar", Hibernate.BIG_DECIMAL)
					.addScalar("totalaguanaofaturavelvolume", Hibernate.BIG_DECIMAL)
					.addScalar("totalaguanaofaturavelvalor", Hibernate.BIG_DECIMAL)						
					.addScalar("totalAguaGPipaValor", Hibernate.BIG_DECIMAL)
					.addScalar("totalAguaGPipaVolume", Hibernate.BIG_DECIMAL)
					.addScalar("valorGuiaPagamento" , Hibernate.BIG_DECIMAL)
					.addScalar("valorDebitoACobrar" , Hibernate.BIG_DECIMAL)
					.setInteger("mesAnoReferencia", mesAnoReferencia)					
					.list();	
			
			return colecao;		
		
		} catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessao
				HibernateUtil.closeSession(session);
			}	
		
	}	
	
	
	
	/**
	 * autor:Jonathan Marcos
	 * data:23/10/2013
	 * [UC1568] Receber Informações Abastecimentos Carros Pipa Via Webservice 
	 */
	public Integer validarSequencialCarroPipaAbastecimento(Integer sequencial)
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();		
		Integer retorno = null;
		String scriptSql = null;
		
		scriptSql = "SELECT COUNT(cpab_id) as quantidade" +
					" FROM integracao.carro_pipa_abastecimento" +
					" WHERE cpab_id = :sequencial";
		
		retorno = (Integer)session.createSQLQuery(scriptSql)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("sequencial", sequencial)
				.setMaxResults(1).uniqueResult();
	
		return retorno;
	}
}