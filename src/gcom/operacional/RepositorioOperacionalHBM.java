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

import gcom.operacional.bean.AreaOperacionalHelper;
import gcom.operacional.bean.ZonaPressaoHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.RemocaoInvalidaException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Repositorio para modulo operacional
 * 
 * @author Rafael Pinto
 * @since 15/10/2008
 */
public class RepositorioOperacionalHBM implements IRepositorioOperacional {

	private static IRepositorioOperacional instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioOperacionalHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioOperacional getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioOperacionalHBM();
		}

		return instancia;
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
	 * @throws ErroRepositorioException
	 */
	
	public Collection<FonteCaptacao> pesquisarFonteCaptacao(Collection colecaoSetorComercial)
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		String consulta;
		Collection<FonteCaptacao> retornoConsulta = null;
		try {
			consulta = "SELECT distinct fonte " 
				+ "from SetorFonteCaptacao setorFonte "
				+ "inner join setorFonte.comp_id.fonteCaptacao fonte "
				+ "left join setorFonte.comp_id.setorComercial setor "
				+ "where setor.id in (:colecaoSetor)";
				

			retornoConsulta = (Collection<FonteCaptacao>) 
				session.createQuery(consulta).
				setParameterList("colecaoSetor", colecaoSetorComercial).list();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 * 
	 */
	public Collection<SubSistemaEsgotoArquivoTexto> pesquisarSubSistemaEsgotoArquivoTexto(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		String consulta;
		Collection<SubSistemaEsgotoArquivoTexto> retornoConsulta = null;
		
		if(dataFinal != null){
			int dia =  Util.getDiaMes(dataFinal);
			int mes =  Util.getMes(dataFinal);
			int ano =  Util.getAno(dataFinal);
			
			Calendar calendario;

			calendario = Calendar.getInstance();
			calendario.set(ano, mes - 1, dia, 23, 59, 59);
			
			dataFinal = calendario.getTime();
		}
		
		try {
			
			consulta = "SELECT distinct subSistemaEsgotoArquivoTexto " 
					 + "FROM SubSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto ";
			
					 if(dataInicial != null && dataFinal != null){
						 consulta += "WHERE subSistemaEsgotoArquivoTexto.ultimaAlteracao BETWEEN :dataInicial AND :dataFinal ";
					 
						 consulta += "ORDER BY subSistemaEsgotoArquivoTexto.ultimaAlteracao ";
						retornoConsulta = (Collection<SubSistemaEsgotoArquivoTexto>)
								session.createQuery(consulta)
								.setTimestamp("dataInicial", dataInicial)
								.setTimestamp("dataFinal", dataFinal).list();
					 }else{
						 	consulta += "ORDER BY subSistemaEsgotoArquivoTexto.ultimaAlteracao ";
						 
							retornoConsulta = (Collection<SubSistemaEsgotoArquivoTexto>)
									session.createQuery(consulta).list();
					 }
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 * 
	 */
	public Collection<SubSistemaEsgotoArquivoTextoErro> pesquisarSubSistemaEsgotoArquivoTextoErro(Integer idArquivo)
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		String consulta;
		Collection<SubSistemaEsgotoArquivoTextoErro> retornoConsulta = null;
		try {
			
			consulta = "SELECT subSistemaEsgotoArquivoTextoErro " 
					 + "FROM SubSistemaEsgotoArquivoTextoErro subSistemaEsgotoArquivoTextoErro "
					 + "INNER JOIN subSistemaEsgotoArquivoTextoErro.subSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto "
					 + "WHERE subSistemaEsgotoArquivoTexto.id = :idArquivo ";
			
			retornoConsulta = (Collection<SubSistemaEsgotoArquivoTextoErro>)
				session.createQuery(consulta)
				.setInteger("idArquivo", idArquivo).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

	
	public void removerLocalidadeSistemaAbastecimento(Integer id) throws ErroRepositorioException {
		
		String consulta;
		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta = " delete from gcom.operacional.LocalidadeSistemaAbastecimento "+
						" where loca_id = :idLocalidade ";
			
			session.createQuery(consulta).setInteger("idLocalidade", id).executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/** 
     * [UCXXXX] Atualizar Subsistema de Abastecimento 
     *  
     * @author Raphael Rossiter 
     * @date 02/06/2014 
     *  
     * @param subsistemaSistemaAbastecimento 
     * @throws ErroRepositorioException 
     */
	public void atualizarSubsistemaSistemaAbastecimentoPrincipal(SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento)
			throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();

		try {

			update = "UPDATE gcom.operacional.SubsistemaSistemaAbastecimento SET "
					+ "sabs_id = :idSistemaAbastecimento, sbsi_tmultimaalteracao = sysdate "
					+ "WHERE sbab_id = :idSubsistemaAbastecimento and sbsi_icprincipal = 1 ";

			session.createQuery(update)
					.setInteger("idSistemaAbastecimento", subsistemaSistemaAbastecimento.getSistemaAbastecimento().getId())
					.setInteger("idSubsistemaAbastecimento", subsistemaSistemaAbastecimento.getSubsistemaAbastecimento().getId())
					.executeUpdate();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceÃ§Ã£o para a prÃ³xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessÃ£o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<Object[]> pesquisarSetorAbastecimento(String codigo,
			String descricao, String descricaoAbreviada,
			String indicadorPosicaoTexto, String sistemaAgua,
			String subsistemaAgua, String indicadorUso,String numeroPagina, boolean relatorio) throws ErroRepositorioException{
		
		String consulta;
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		HashMap<String,Object> parametros = new HashMap<String,Object>();
		
		try {
			
			consulta =
					" SELECT sa.stab_id                      AS codigo, "+
					" 	sa.stab_dssetorabastecimento         AS descricao, "+
					" 	sa.stab_dsabreviado                  AS descricaoAbreviada, "+
					" 	subsa.sbab_dssubsistemaabastecimento AS subsistemaAgua, "+
					" 	sa.sabs_id 							 AS sistemaAbPrincipal, " +
					"	sa.sabs_idsecundario 				 AS sistemaAbSecundario, "+
					"	sa.stab_icuso 				 		 AS indicadorUso "+
					" FROM OPERACIONAL.setor_abastecimento sa "+
					" LEFT JOIN OPERACIONAL.setor_subsistema_abastec ssa "+
					" ON sa.stab_id            = ssa.stab_id "+
					" AND ssa.ssab_icprincipal = 1 "+
					" LEFT JOIN OPERACIONAL.subsistema_abastecimento subsa "+
					" ON ssa.sbab_id = subsa.sbab_id " +
					" WHERE ";
			
			//Código
			if(Util.verificarNaoVazio(codigo)){
				consulta += " sa.stab_id = :codigo AND ";
				parametros.put("codigo", codigo);
			}
			
			//Descrição
			if(Util.verificarNaoVazio(descricao)){
				
				consulta += " sa.stab_dssetorabastecimento LIKE :descricao AND ";
				
				//Começa com
				if(indicadorPosicaoTexto.equals("1")){
					parametros.put("descricao", descricao.toUpperCase()+"%");
				}
				
				//Está no texto
				else{
					parametros.put("descricao", "%"+descricao.toUpperCase()+"%");
				}
			}
			
			//Descrição abreviada
			if(Util.verificarNaoVazio(descricaoAbreviada)){
				consulta += " sa.stab_dsabreviado LIKE :descricaoAbreviada AND ";
				parametros.put("descricaoAbreviada", descricaoAbreviada.toUpperCase()+"%");
			}
			
			//Sistema de água
			if(Util.verificarIdNaoVazio(sistemaAgua)){
				consulta += " sa.sabs_id = :sistemaAgua AND ";
				parametros.put("sistemaAgua", sistemaAgua);
			}
			
			//Subsistema água
			if(Util.verificarIdNaoVazio(subsistemaAgua)){
				consulta += " subsa.sbab_id = :subsistemaAgua AND ";
				parametros.put("subsistemaAgua", subsistemaAgua);
			}
			
			//Indicador de uso
			if(indicadorUso != null && !indicadorUso.equals("3")){
				consulta += " sa.stab_icuso = :indicadorUso AND ";
				parametros.put("indicadorUso", indicadorUso);
			}
			
			consulta += "1=1 ORDER BY sa.stab_id";
			
			Query query = session.createSQLQuery(consulta)
						  .addScalar("codigo", Hibernate.STRING)             	 //0
						  .addScalar("descricao", Hibernate.STRING)			 	 //1
						  .addScalar("descricaoAbreviada", Hibernate.STRING) 	 //2
						  .addScalar("subsistemaAgua", Hibernate.STRING)	 	 //3
						  .addScalar("sistemaAbPrincipal", Hibernate.STRING)	 //4
						  .addScalar("sistemaAbSecundario", Hibernate.STRING)	 //5
						  .addScalar("indicadorUso", Hibernate.STRING);	 		 //6
			
			Set set = parametros.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				query.setString(key, (String)parametros.get(key));
			}
			
			if(relatorio){
				retorno = (Collection<Object[]>)query.list();
			}
			else if(!Util.verificarIdNaoVazio(numeroPagina))
				retorno = (Collection<Object[]>)query.setMaxResults(10).list();
			else
				retorno = (Collection<Object[]>)query.setFirstResult(10 * (Integer.parseInt(numeroPagina) - 1)).setMaxResults(10).list();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	@SuppressWarnings({ "rawtypes" })
	public Integer pesquisarQtdSetorAbastecimento(String codigo,
			String descricao, String descricaoAbreviada,
			String indicadorPosicaoTexto, String sistemaAgua,
			String subsistemaAgua, String indicadorUso) throws ErroRepositorioException{
		
		String consulta;
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		HashMap<String,Object> parametros = new HashMap<String,Object>();
		
		try {
			
			consulta =
					" SELECT count(*) as qtd "+
					" FROM OPERACIONAL.setor_abastecimento sa "+
					" LEFT JOIN OPERACIONAL.setor_subsistema_abastec ssa "+
					" ON sa.stab_id            = ssa.stab_id "+
					" AND ssa.ssab_icprincipal = 1 "+
					" LEFT JOIN OPERACIONAL.subsistema_abastecimento subsa "+
					" ON ssa.sbab_id = subsa.sbab_id " +
					" WHERE ";
			
			//Código
			if(Util.verificarNaoVazio(codigo)){
				consulta += " sa.stab_id = :codigo AND ";
				parametros.put("codigo", codigo);
			}
			
			//Descrição
			if(Util.verificarNaoVazio(descricao)){
				
				consulta += " sa.stab_dssetorabastecimento LIKE :descricao AND ";
				
				//Começa com
				if(indicadorPosicaoTexto.equals("1")){
					parametros.put("descricao", descricao.toUpperCase()+"%");
				}
				
				//Está no texto
				else{
					parametros.put("descricao", "%"+descricao.toUpperCase()+"%");
				}
			}
			
			//Descrição abreviada
			if(Util.verificarNaoVazio(descricaoAbreviada)){
				consulta += " sa.stab_dsabreviado LIKE :descricaoAbreviada AND ";
				parametros.put("descricaoAbreviada", descricaoAbreviada.toUpperCase()+"%");
			}
			
			//Sistema de água
			if(Util.verificarIdNaoVazio(sistemaAgua)){
				consulta += " sa.sabs_id = :sistemaAgua AND ";
				parametros.put("sistemaAgua", sistemaAgua);
			}
			
			//Subsistema água
			if(Util.verificarIdNaoVazio(subsistemaAgua)){
				consulta += " subsa.sbab_id = :subsistemaAgua AND ";
				parametros.put("subsistemaAgua", subsistemaAgua);
			}
			
			//Indicador de uso
			if(indicadorUso != null && !indicadorUso.equals("3")){
				consulta += " sa.stab_icuso = :indicadorUso AND ";
				parametros.put("indicadorUso", indicadorUso);
			}
			
			consulta += "1=1 ORDER BY sa.stab_id";
			
			Query query = session.createSQLQuery(consulta)
						  		 .addScalar("qtd", Hibernate.INTEGER);
			
			Set set = parametros.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				query.setString(key, (String)parametros.get(key));
			}
			
			
			retorno = (Integer)query.uniqueResult();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorAbastecimento(Integer idSetor) throws ErroRepositorioException, ConstraintViolationException {
		
		String consulta;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
					
		    tx = session.beginTransaction();
			
			//Removendo Setor subsistema abastecimento
			consulta = " delete from gcom.operacional.SetorSubsistemaAbastecimento "+
						" where stab_id = :idSetor ";
			
			session.createQuery(consulta).setInteger("idSetor", idSetor).executeUpdate();
			
			//Removendo setor abastecimento
			consulta = " delete from gcom.operacional.SetorAbastecimento "+
					   " where stab_id = :idSetor ";
		
			session.createQuery(consulta).setInteger("idSetor", idSetor).executeUpdate();
			tx.commit();
			
		} catch (ConstraintViolationException c){
			c.printStackTrace();
			tx.rollback();
			throw new ConstraintViolationException("Erro no Hibernate", null, null);
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Collection<SubsistemaSistemaAbastecimento> pesquisarSubSistemaSistemaAbastecimento(Integer idSistemaAbastecimento)
			throws ErroRepositorioException {
			
			Session session = HibernateUtil.getSession();
			
			String consulta;
			Collection<SubsistemaSistemaAbastecimento> retornoConsulta = null;
			try {
				
				consulta = " SELECT subs " 
						 + " FROM SubsistemaSistemaAbastecimento subs "
						 + " INNER JOIN FETCH subs.subsistemaAbastecimento subs2 "
						 + " INNER JOIN FETCH subs.sistemaAbastecimento subs3 "
						 + " WHERE subs.sistemaAbastecimento.id = :idSistemaAbastecimento "
						 + " AND subs2.indicadorUso = 1";
				
				retornoConsulta = (Collection<SubsistemaSistemaAbastecimento>)
									session.createQuery(consulta)
									.setInteger("idSistemaAbastecimento", idSistemaAbastecimento).list();
				
			} catch (HibernateException e) {
				e.printStackTrace();
				throw new ErroRepositorioException("Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
			return retornoConsulta;
		}
	
	
	@SuppressWarnings("unchecked")
	public Collection<SetorSubsistemaAbastecimento> pesquisarSetorSubsistemaAbastecimento(Integer idSetor)
			throws ErroRepositorioException {
			
			Session session = HibernateUtil.getSession();
			
			String consulta;
			Collection<SetorSubsistemaAbastecimento> retornoConsulta = null;
			try {
				
				consulta = " SELECT subs " 
						 + " FROM SetorSubsistemaAbastecimento subs "
						 + " INNER JOIN FETCH subs.setorAbastecimento subs2 "
						 + " INNER JOIN FETCH subs.subsistemaAbastecimento subs3 "
						 + " WHERE subs2.id = :idSetor "
						 + " AND subs2.indicadorUso = 1";
				
				retornoConsulta = (Collection<SetorSubsistemaAbastecimento>)
									session.createQuery(consulta)
									.setInteger("idSetor", idSetor).list();
				
			} catch (HibernateException e) {
				e.printStackTrace();
				throw new ErroRepositorioException("Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
			return retornoConsulta;
		}
	
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorSubsistemaAbastecimento(Integer idSetor) throws ErroRepositorioException, ConstraintViolationException {
		
		String consulta;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
					
		    tx = session.beginTransaction();
			
			//Removendo Setor subsistema abastecimento
			consulta = " delete from gcom.operacional.SetorSubsistemaAbastecimento "+
						" where stab_id = :idSetor ";
			
			session.createQuery(consulta).setInteger("idSetor", idSetor).executeUpdate();	
			
			tx.commit();
			
		} catch (ConstraintViolationException c){
			c.printStackTrace();
			tx.rollback();
			throw new ConstraintViolationException("Erro no Hibernate", null, null);
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [IT0002] Exibir Lista de Subsistema de Abastecimento.
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<SubsistemaAbastecimento> obterColecaoSubsistemaAbastecimento(Integer idSistemaAbastecimento)
			throws ErroRepositorioException {

		Collection retorno = new ArrayList<SubsistemaAbastecimento>();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection<Object[]> retornoConsulta;

		try {
		   consulta = " SELECT sbab.sbab_id as id,"
					+ " sbab_dssubsistemaabastecimento as descricao "
					+ "	FROM operacional.subsistema_abastecimento sbab"
					+ "	INNER JOIN operacional.subsis_sis_abastecimento sbsi on(sbab.sbab_id = sbsi.sbab_id)"
					+ "	WHERE sbab_icuso = " +ConstantesSistema.INDICADOR_USO_ATIVO
					+ " AND sabs_id =:idSistemaAbastecimento"
		   			+ " ORDER BY sbab_dssubsistemaabastecimento";

		   retornoConsulta = (Collection<Object[]>) session
					.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.setInteger("idSistemaAbastecimento",idSistemaAbastecimento).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {				
				Iterator iterRetornoConsulta = retornoConsulta.iterator();
				while (iterRetornoConsulta.hasNext()) {
					Object[] objeto = (Object[]) iterRetornoConsulta.next();
					SubsistemaAbastecimento sbab = new SubsistemaAbastecimento();
					sbab.setId((Integer) objeto[0]);
					sbab.setDescricao((String) objeto[1]);
					retorno.add(sbab);
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
	 * [UC1603] Inserir Área Operacional
	 * [IT0003] Exibir Lista de Distrito Operacional
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<DistritoOperacional> obterColecaoDistritoOperacional(Integer idSubsistemaAbastecimento)
			throws ErroRepositorioException {

		Collection retorno = new ArrayList<DistritoOperacional>();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection<Object[]> retornoConsulta;

		try {
		   consulta = " SELECT distinct(diop.diop_id) as id,"
					+ " diop_dsdistritooperacional as descricao"
					+ "	FROM  operacional.distrito_operacional diop"
					+ " INNER JOIN operacional.distrito_setor_abastec disa on(disa.diop_id = diop.diop_id)"
					+ "	INNER JOIN operacional.setor_subsistema_abastec stab on(disa.stab_id = stab.stab_id)"
					+ "	WHERE diop_icuso = " +ConstantesSistema.INDICADOR_USO_ATIVO
					+ " AND sbab_id =:idSubsistemaAbastecimento"
					+ " ORDER BY diop_dsdistritooperacional ";

		   retornoConsulta = (Collection<Object[]>) session
					.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.setInteger("idSubsistemaAbastecimento",idSubsistemaAbastecimento).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				Iterator iterRetornoConsulta = retornoConsulta.iterator();
				while (iterRetornoConsulta.hasNext()) {
					Object[] objeto = (Object[]) iterRetornoConsulta.next();
					DistritoOperacional diop = new DistritoOperacional();
					diop.setId((Integer) objeto[0]);
					diop.setDescricao((String) objeto[1]);
					retorno.add(diop);
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
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 04/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterAreaOperacionalCount(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso) throws ErroRepositorioException{
		
		Integer retorno = 0;
		String consulta = "";
		Collection colecao = null;

		Session session = HibernateUtil.getSession();

		try {
		   consulta = " select count(distinct(adop.arop_id)) as qtd"
					+ " from operacional.area_operacional arop"
		   			+ "	inner join operacional.area_distrito_operac adop on (arop.arop_id = adop.arop_id)"
		   			+ " inner join operacional.distrito_setor_abastec disa on(disa.diop_id = adop.diop_id)"
					+ " inner join operacional.setor_subsistema_abastec stab on (stab.stab_id = disa.stab_id)"
				    + " inner join operacional.subsis_sis_abastecimento sbsi on(sbsi.sbab_id = stab.sbab_id)"
					+ " where ";
		   			
			if(descricao != null && !descricao.equals("")){
				
				if (tipoPesquisa == null || tipoPesquisa.equals("")
				 || tipoPesquisa.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(arop.arop_dsareaoperacional) LIKE '"
							+ descricao.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(arop.arop_dsareaoperacional) LIKE '%"
							+ descricao.toUpperCase() + "%'  AND";
				}
			}
			
			if(idSistemaAbastecimento != null && !idSistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " sbsi.sabs_id = "+ new Integer(idSistemaAbastecimento) + " AND";
			}
			
			if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.sbab_id = "+ new Integer(idSubsistemaAbastecimento) + " AND";
			}
			
			if(idDistritoOperacional != null && !idDistritoOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " adop.diop_id = "+ new Integer(idDistritoOperacional) + " AND";
			}
			
			if(indicadorUso != null && !indicadorUso.equals("")){
				consulta += " arop.arop_icuso = "+ new Integer(indicadorUso) + " AND";
			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4);

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
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaOperacionalHelper> obterAreaOperacional(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, Integer numeroPagina) throws ErroRepositorioException{
		
		Collection<Object[]> retornoConsulta = new ArrayList();
		Collection<AreaOperacionalHelper> retorno = new ArrayList<AreaOperacionalHelper>();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
		   consulta = " select distinct(adop.arop_id) as idAreaOperacional, " 
		   		    + " arop.arop_dsareaoperacional as descricaoAreaOperacional" 
					+ " from operacional.area_operacional arop"
		   			+ "	inner join operacional.area_distrito_operac adop on (arop.arop_id = adop.arop_id)"
		   			+ " inner join operacional.distrito_setor_abastec disa on(disa.diop_id = adop.diop_id)"
					+ " inner join operacional.setor_subsistema_abastec stab on (stab.stab_id = disa.stab_id)"
				    + " inner join operacional.subsis_sis_abastecimento sbsi on(sbsi.sbab_id = stab.sbab_id)"
					+ " where ";
			   
			if(descricao != null && !descricao.equals("")){
				
				if (tipoPesquisa == null || tipoPesquisa.equals("")
				 || tipoPesquisa.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(arop.arop_dsareaoperacional) LIKE '"
							+ descricao.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(arop.arop_dsareaoperacional) LIKE '%"
							+ descricao.toUpperCase() + "%'  AND";
				}
			}
			
			if(idSistemaAbastecimento != null && !idSistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " sbsi.sabs_id = "+ new Integer(idSistemaAbastecimento) + " AND";
			}
			
			if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.sbab_id = "+ new Integer(idSubsistemaAbastecimento) + " AND";
			}
			
			if(idDistritoOperacional != null && !idDistritoOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " adop.diop_id = "+ new Integer(idDistritoOperacional) + " AND";
			}
			
			if(indicadorUso != null && !indicadorUso.equals("")){
				consulta += " arop.arop_icuso = "+ new Integer(indicadorUso) + " AND";
			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4) + " ORDER BY arop.arop_dsareaoperacional ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idAreaOperacional", Hibernate.INTEGER)
				    .addScalar("descricaoAreaOperacional", Hibernate.STRING);

			retornoConsulta = (Collection<Object[]>)query
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			
			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				Iterator iterRetornoConsulta = retornoConsulta.iterator();
				while (iterRetornoConsulta.hasNext()) {
					Object[] objeto = (Object[]) iterRetornoConsulta.next();
					AreaOperacionalHelper arop = new AreaOperacionalHelper();
					arop.setIdAreaOperacional((Integer) objeto[0]);
					arop.setDescricaoAreaOperacional((String) objeto[1]);
					retorno.add(arop);
				}
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public AreaOperacional obterAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		AreaOperacional areaOperacional = null;
		String consulta = "";
		
		try{
			consulta = 	"SELECT arop "
					 +	"FROM AreaOperacional arop "
					 +	"WHERE arop.id = :idAreaOperacional ";

			areaOperacional = (AreaOperacional) session.createQuery(consulta)
					.setInteger("idAreaOperacional", idAreaOperacional)
					.setMaxResults(1)
					.uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return areaOperacional;
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaDistritoOperacional> obterAreaDistritoOperacional(Integer idAreaOperacional) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection<AreaDistritoOperacional> colecaoAreaDistritoOperacional = null;
		String consulta = "";
		
		try{
			consulta = 	"SELECT adop "
					 +	"FROM AreaDistritoOperacional adop "
					 +  "INNER JOIN FETCH adop.distritoOperacional "
					 +	"WHERE adop.areaOperacional.id = :idAreaOperacional ";

			colecaoAreaDistritoOperacional = (Collection<AreaDistritoOperacional>) session.createQuery(consulta)
					.setInteger("idAreaOperacional", idAreaOperacional)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoAreaDistritoOperacional;
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerAreaDistritoOperacional(Integer idAreaOperacional) throws ErroRepositorioException{
		
		String scriptSql = "";
		Session session = HibernateUtil.getSession();
		
		try {
			//Deleta NormaArquivos
			scriptSql = "DELETE gcom.operacional.AreaDistritoOperacional adop " +
						" WHERE adop.areaOperacional.id = :idAreaOperacional";
			
			session.createQuery(scriptSql).setInteger("idAreaOperacional", idAreaOperacional).executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
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
	public void removerAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException{
		
		String scriptSql = "";
		Session session = HibernateUtil.getSession();
		
		try {
			//Deleta NormaArquivos
			scriptSql = "DELETE gcom.operacional.AreaDistritoOperacional adop " +
						" WHERE adop.areaOperacional.id = :idAreaOperacional";
			
			session.createQuery(scriptSql).setInteger("idAreaOperacional", idAreaOperacional).executeUpdate();
			
			//Deleta NormaProcedimentos
			scriptSql = "DELETE gcom.operacional.AreaOperacional arop " +
					" WHERE arop.id = :idAreaOperacional";
		
			session.createQuery(scriptSql).setInteger("idAreaOperacional", idAreaOperacional).executeUpdate();
			
		} catch (JDBCException e) {
			throw new RemocaoInvalidaException(e);
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
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
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, String idSetorAbastecimento) throws ErroRepositorioException{
		
		Collection<Object[]> retornoConsulta = new ArrayList();
		Collection<AreaOperacionalHelper> retorno = new ArrayList<AreaOperacionalHelper>();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			   consulta = " select distinct(adop.arop_id) as idAreaOperacional," 
			   		    + " arop.arop_dsareaoperacional as descricaoAreaOperacional" 
						+ " from operacional.area_operacional arop"
			   			+ "	inner join operacional.area_distrito_operac adop on (arop.arop_id = adop.arop_id)"
			   			+ " inner join operacional.distrito_setor_abastec disa on(disa.diop_id = adop.diop_id)"
						+ " inner join operacional.setor_subsistema_abastec stab on (stab.stab_id = disa.stab_id)"
					    + " inner join operacional.subsis_sis_abastecimento sbsi on(sbsi.sbab_id = stab.sbab_id)"
						+ " where ";
			   
			if(descricao != null && !descricao.equals("")){
				
				if (tipoPesquisa == null || tipoPesquisa.equals("")
				 || tipoPesquisa.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(arop.arop_dsareaoperacional) LIKE '"
							+ descricao.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(arop.arop_dsareaoperacional) LIKE '%"
							+ descricao.toUpperCase() + "%'  AND";
				}
			}
			
			if(idSistemaAbastecimento != null && !idSistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " sbsi.sabs_id = "+ new Integer(idSistemaAbastecimento) + " AND";
			}
			
			if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.sbab_id = "+ new Integer(idSubsistemaAbastecimento) + " AND";
			}
			
			if(idSetorAbastecimento != null && !idSetorAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.stab_id = "+ new Integer(idSetorAbastecimento) + " AND";
			}
			
			if(idDistritoOperacional != null && !idDistritoOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " adop.diop_id = "+ new Integer(idDistritoOperacional) + " AND";
			}
			
			if(indicadorUso != null && !indicadorUso.equals("")){
				consulta += " arop.arop_icuso = "+ new Integer(indicadorUso) + " AND";
			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4) + " ORDER BY arop.arop_dsareaoperacional ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idAreaOperacional", Hibernate.INTEGER)
				    .addScalar("descricaoAreaOperacional", Hibernate.STRING);

			retornoConsulta = (Collection<Object[]>)query.list();
			
			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				Iterator iterRetornoConsulta = retornoConsulta.iterator();
				while (iterRetornoConsulta.hasNext()) {
					Object[] objeto = (Object[]) iterRetornoConsulta.next();
					AreaOperacionalHelper arop = new AreaOperacionalHelper();
					arop.setIdAreaOperacional((Integer) objeto[0]);
					arop.setDescricaoAreaOperacional((String) objeto[1]);
					retorno.add(arop);
				}
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public DistritoOperacional obterDistritoPrincipalAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		DistritoOperacional distritoOperacional = null;
		String consulta = "";
		Object[] retornoConsulta;
		
		try{
			consulta = 	"SELECT diop.diop_id as id, "
					 +  "diop_dsdistritooperacional as descricao "
					 +	"FROM operacional.distrito_operacional diop "
					 +  "INNER JOIN operacional.area_distrito_operac adop on (adop.diop_id = diop.diop_id) "
					 +	"WHERE arop_id = :idAreaOperacional " 
					 +  "AND adop.adop_icprincipal = 1";

			retornoConsulta = (Object[]) session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.setInteger("idAreaOperacional", idAreaOperacional)
					.setMaxResults(1)
					.uniqueResult();
			
			if(retornoConsulta != null && retornoConsulta.length == 2){
				distritoOperacional = new DistritoOperacional();
				distritoOperacional.setId((Integer) retornoConsulta[0]);
				distritoOperacional.setDescricao(retornoConsulta[1]+"");
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return distritoOperacional;
	}
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SubsistemaAbastecimento obterSubsistemaPrincipalAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		SubsistemaAbastecimento subsistemaAbastecimento = null;
		String consulta = "";
		Object[] retornoConsulta;
		
		try{
			consulta = 	"SELECT sbab.sbab_id as id, "
					 +  "sbab.sbab_dssubsistemaabastecimento as descricao "
					 +	"FROM operacional.subsistema_abastecimento sbab "
					 +  "INNER JOIN operacional.setor_subsistema_abastec stab ON (stab.sbab_id = sbab.sbab_id and SSAB_ICPRINCIPAL = 1) "
					 +  "INNER JOIN operacional.distrito_setor_abastec disa ON(disa.stab_id = stab.stab_id and DISA_ICPRINCIPAL = 1) "
					 +  "INNER JOIN operacional.area_distrito_operac adop on (adop.diop_id = disa.diop_id and adop.adop_icprincipal = 1) "
					 +	"WHERE arop_id = :idAreaOperacional ";

			retornoConsulta = (Object[]) session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.setInteger("idAreaOperacional", idAreaOperacional)
					.setMaxResults(1)
					.uniqueResult();
			
			if(retornoConsulta != null && retornoConsulta.length == 2){
				subsistemaAbastecimento = new SubsistemaAbastecimento();
				subsistemaAbastecimento.setId((Integer) retornoConsulta[0]);
				subsistemaAbastecimento.setDescricao(retornoConsulta[1]+"");
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return subsistemaAbastecimento;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Collection<Object[]> pesquisarDistritoOperacional(
			String descricao, String indicadorPosicaoTexto, String sistemaAgua,
			String subsistemaAgua, String setorAbastecimento, 
			String indicadorUso,String numeroPagina, boolean relatorio) throws ErroRepositorioException{
	
		String consulta;
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		HashMap<String,Object> parametros = new HashMap<String,Object>();
		
		try{
			
			consulta = 
					
					" select distinct diop.diop_id as id," +
					" diop.diop_dsdistritooperacional as descricao, ";
			
			//Verificando subsistema de abastecimento informado
			//Caso não seja escolhida nenhuma opção no menu de filtro, selecionar o subsistema principal
			if(!Util.verificarIdNaoVazio(subsistemaAgua)){
				consulta +=	" (SELECT subsistema_p.sbab_dssubsistemaabastecimento  "+
						  	" FROM OPERACIONAL.subsistema_abastecimento subsistema_p "+
						  	" LEFT JOIN OPERACIONAL.setor_subsistema_abastec joi ON joi.sbab_id = subsistema_p.sbab_id "+
						  	" LEFT JOIN OPERACIONAL.distrito_setor_abastec joi2 ON joi.stab_id = joi2.stab_id "+
						 	" WHERE joi.ssab_icprincipal = 1 "+
						  	" AND joi2.disa_icprincipal  = 1 "+
						  	" AND joi2.diop_id           = diop.diop_id "+
						  	" ) AS subsistemaAbastecimento, ";
			}
			else{
				consulta +=	" sbab.sbab_dssubsistemaabastecimento as subsistemaAbastecimento, ";
			}	
			
			//verificando setor de abastecimento informado
			//Caso não seja escolhida nenhuma opção no menu de filtro, selecionar o setor principal
			if(!Util.verificarIdNaoVazio(setorAbastecimento)){
				consulta +=	" (SELECT setor_p.stab_dssetorabastecimento "+
							"		  FROM OPERACIONAL.setor_abastecimento setor_p "+
							"		  LEFT JOIN OPERACIONAL.distrito_setor_abastec joi ON setor_p.stab_id = joi.stab_id "+
							"		  WHERE joi.disa_icprincipal = 1 "+
							"		  AND joi.diop_id            = diop.diop_id " +
							" ) as setorAbastecimento, ";
			}
			else{
				consulta +=	" stab.stab_dssetorabastecimento as setorAbastecimento, ";
			}
			
				consulta +=	
							" diop.diop_icuso as indicadorUso "+
							" from OPERACIONAL.distrito_operacional diop " + 
							" left join OPERACIONAL.distrito_setor_abastec disa on disa.diop_id = diop.diop_id " + 
							" left join OPERACIONAL.setor_abastecimento stab on stab.stab_id = disa.stab_id " + 
							" left join OPERACIONAL.setor_subsistema_abastec ssab on ssab.stab_id = stab.stab_id " + 
							" left join OPERACIONAL.subsistema_abastecimento sbab on sbab.sbab_id = ssab.sbab_id " + 
							" left join OPERACIONAL.subsis_sis_abastecimento sbsi on sbsi.sbab_id = sbab.sbab_id " + 
							" left join OPERACIONAL.sistema_abastecimento sisb on sisb.sabs_id = sbsi.sabs_id ";	
			
			consulta +=	" where 1=1 ";			
					
			//Descrição
			if(Util.verificarNaoVazio(descricao)){
				
				consulta += "AND diop.diop_dsdistritooperacional LIKE :descricao ";
				
				//Começa com
				if(indicadorPosicaoTexto.equals("1")){
					parametros.put("descricao", descricao.toUpperCase()+"%");
				}
				
				//Está no texto
				else{
					parametros.put("descricao", "%"+descricao.toUpperCase()+"%");
				}
			}
			
			//Sistema de abstecimento
			if(Util.verificarIdNaoVazio(sistemaAgua)){
				consulta += "AND sisb.sabs_id = :idSistemaAbastecimento ";
				parametros.put("idSistemaAbastecimento", sistemaAgua);
			}
			
			//Subsistema de abastecimento
			if(Util.verificarIdNaoVazio(subsistemaAgua)){
				consulta += "AND sbab.sbab_id = :idSubsistemaAbastecimento ";
				parametros.put("idSubsistemaAbastecimento", subsistemaAgua);
			}
			
			//Setor de abastecimento
			if(Util.verificarIdNaoVazio(setorAbastecimento)){
				consulta += "AND stab.stab_id = :idSetorAbastecimento ";
				parametros.put("idSetorAbastecimento", setorAbastecimento);
			}
			
			//Indicador de uso
			if(Util.verificarIdNaoVazio(indicadorUso)){
				consulta += "AND diop.diop_icuso = :indicadorUso ";
				parametros.put("indicadorUso", indicadorUso);
			}
			
			consulta += " ORDER BY diop.diop_dsdistritooperacional ";
			
			Query query = session.createSQLQuery(consulta)
								.addScalar("id", Hibernate.INTEGER)                       //0
								.addScalar("descricao", Hibernate.STRING)				  //1	
								.addScalar("subsistemaAbastecimento", Hibernate.STRING)   //2
								.addScalar("setorAbastecimento", Hibernate.STRING)        //3
								.addScalar("indicadorUso", Hibernate.STRING);             //4 

			Util.preencherParametrosQuery(parametros, query);
			
			
			if(relatorio){
				retorno = (Collection<Object[]>)query.list();
			}
			else if(!Util.verificarIdNaoVazio(numeroPagina))
				retorno = (Collection<Object[]>)query.setMaxResults(10).list();
			else
				retorno = (Collection<Object[]>)query.setFirstResult(10 * (Integer.parseInt(numeroPagina) - 1)).setMaxResults(10).list(); 
			
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0000] - Manter Distrito Operacional
	 * 
	 * @author Hugo Azevedo
	 * @date 13/06/2014
	 * 
	 */
	public void removerDistritoSetorAbastecimento(Integer idDistrito) throws ErroRepositorioException, ConstraintViolationException {
		
		String consulta;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
					
		    tx = session.beginTransaction();
			
			//Removendo Setor subsistema abastecimento
			consulta = " delete from gcom.operacional.DistritoSetorAbastecimento "+
						" where diop_id = :idDistrito ";
			
			session.createQuery(consulta).setInteger("idDistrito", idDistrito).executeUpdate();
				
			tx.commit();
			
		} catch (ConstraintViolationException c){
			c.printStackTrace();
			tx.rollback();
			throw new ConstraintViolationException("Erro no Hibernate", null, null);
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
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
	@SuppressWarnings("unchecked")
	public Collection<Object[]> pesquisarTotalizacaoSetoresAbastecimentoAnalitico(String sistemaAbastecimento, 
																		 String subsistemaAbastecimento,
																		 String setorAbastecimento,
																		 String distritoOperacional,
																		 String areaOperacional,
																		 String zonaPressao,
																		 Integer anoMesMenosUm) throws ErroRepositorioException{
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		HashMap<String,Object> parametros = new HashMap<String,Object>();
		
		
		try{
			
			String consulta = 
					
					" SELECT sabs.sabs_dssistemaabastecimento   AS sistemaAbastecimento," +
					" sbab.sbab_dssubsistemaabastecimento		AS subsistemaAbastecimento," +
					" stab.stab_dssetorabastecimento			AS setorAbastecimento," +
					" diop.diop_dsdistritooperacional           AS distritoOperacional, " +
					" arop.arop_dsareaoperacional               AS areaOperacional," +
					" znpr.znpr_dszonapressao					AS zonaPressao," +
					" im.imov_id          		 				AS matricula, "+
					" clie_nmcliente             				AS nome_cliente, "+
					" loca_nmlocalidade          				AS localidade, "+
					" last_dsligacaoaguasituacao 				AS situacao_ligacao, "+
					" CASE "+
					" 	WHEN mdhi_id IS NOT NULL "+
					" 	THEN 'MEDIDO' "+
					" 	ELSE 'NÃO MEDIDO' "+
					" END                                   	AS situacao_medicao, " +
					" CASE " +
					"	WHEN mdhi_nnconsumomedidomes IS NOT NULL " +
					"	THEN mdhi_nnconsumomedidomes " +
					"	ELSE coalesce(cshi_nnconsumofaturadomes,0) " +
					" END 										AS volume_medido, "+
					" COALESCE(cshi_nnconsumofaturadomes,0) 	AS volume_faturado "+
					" FROM cadastro.imovel im "+
					" LEFT JOIN operacional.sistema_abastecimento sabs on sabs.sabs_id = im.sabs_id " +
					" LEFT JOIN operacional.subsistema_abastecimento sbab on sbab.sbab_id = im.sbab_id " +
					" LEFT JOIN operacional.setor_abastecimento stab on stab.stab_id = im.stab_id " +
					" LEFT JOIN operacional.distrito_operacional diop on diop.diop_id = im.diop_id " +
					" LEFT JOIN operacional.area_operacional arop on arop.arop_id = im.arop_id " +
					" LEFT JOIN operacional.zona_pressao znpr on znpr.znpr_id = im.znpr_id " +
					" INNER JOIN cadastro.cliente_imovel ci ON ci.imov_id = im.imov_id  AND crtp_id = 2 AND clim_dtrelacaofim IS NULL "+
					" INNER JOIN cadastro.cliente cl ON cl.clie_id = ci.clie_id "+
					" INNER JOIN cadastro.localidade loc ON loc.loca_id = im.loca_id "+
					" INNER JOIN atendimentopublico.ligacao_agua_situacao las ON las.last_id = im.last_id "+
					" LEFT JOIN micromedicao.medicao_historico mh ON lagu_id = im.imov_id AND medt_id = 1 AND mdhi_amleitura = :referencia_faturamento_menos_1 "+
					" LEFT JOIN micromedicao.consumo_historico ch ON ch.imov_id = im.imov_id AND lgti_id = 1 AND cshi_amfaturamento = :referencia_faturamento_menos_1 "+
					" WHERE imov_icexclusao = 2 ";
			
			//Sistema de abastecimento
			if(Util.verificarIdNaoVazio(sistemaAbastecimento)){
				consulta += " AND im.sabs_id = :sistemaAbastecimento ";
				parametros.put("sistemaAbastecimento", sistemaAbastecimento);
			}
			
			//Subsistema de abastecimento
			if(Util.verificarIdNaoVazio(subsistemaAbastecimento)){
				consulta += " AND im.sbab_id = :subsistemaAbastecimento ";
				parametros.put("subsistemaAbastecimento", subsistemaAbastecimento);
			}
			
			//Setor de abastecimento
			if(Util.verificarIdNaoVazio(setorAbastecimento)){
				consulta += " AND im.stab_id = :setorAbastecimento ";
				parametros.put("setorAbastecimento", setorAbastecimento);
			}
			
			//Distrito operacional
			if(Util.verificarIdNaoVazio(distritoOperacional)){
				consulta += " AND im.diop_id = :distritoOperacional ";
				parametros.put("distritoOperacional", distritoOperacional);
			}
			
			//Área operacional
			if(Util.verificarIdNaoVazio(areaOperacional)){
				consulta += " AND im.arop_id = :areaOperacional ";
				parametros.put("areaOperacional", areaOperacional);
			}
			
			//Zona de pressão
			if(Util.verificarIdNaoVazio(zonaPressao)){
				consulta += " AND im.znpr_id = :zonaPressao ";
				parametros.put("zonaPressao", zonaPressao);
			}
			
			consulta += " ORDER BY im.sabs_id, im.sbab_id, im.stab_id, im.diop_id, im.arop_id, im.znpr_id, im.imov_id ";
			
			parametros.put("referencia_faturamento_menos_1", anoMesMenosUm.toString());
					
			Query q = session.createSQLQuery(consulta)
							 .addScalar("sistemaAbastecimento", Hibernate.STRING)      //0
							 .addScalar("subsistemaAbastecimento", Hibernate.STRING)   //1
							 .addScalar("setorAbastecimento", Hibernate.STRING)        //2
							 .addScalar("distritoOperacional", Hibernate.STRING)	   //3	
							 .addScalar("areaOperacional", Hibernate.STRING)           //4
							 .addScalar("zonaPressao", Hibernate.STRING)               //5
							 .addScalar("matricula", Hibernate.STRING)                 //6
							 .addScalar("nome_cliente", Hibernate.STRING)              //7 
							 .addScalar("localidade", Hibernate.STRING)                //8
							 .addScalar("situacao_ligacao", Hibernate.STRING)          //9
							 .addScalar("situacao_medicao", Hibernate.STRING)          //10
							 .addScalar("volume_medido", Hibernate.STRING)             //11
							 .addScalar("volume_faturado", Hibernate.STRING);          //12
							 
		Util.preencherParametrosQuery(parametros, q);				 
		
		retorno = (Collection<Object[]>)q.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	
	/**
	 * [UC1613] - Gerar Relatório Totalizador por Sistema de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 20/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	@SuppressWarnings("unchecked")
	public Collection<Object[]> pesquisarTotalizacaoSetoresAbastecimentoSintetico(String sistemaAbastecimento, 
																				 String subsistemaAbastecimento,
																				 String setorAbastecimento,
																				 String distritoOperacional,
																				 String areaOperacional,
																				 String zonaPressao) throws ErroRepositorioException{
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		HashMap<String,Object> parametros = new HashMap<String,Object>();
		
		
		try{
			
			String consulta = 
					
					" select "+
					" sabs_dssistemaabastecimento as sistema, "+
					" case when im.sbab_id is not null then SBAB_DSSUBSISTEMAABASTECIMENTO "+
					"     else ' ' end as subsistema, "+
					" case when im.stab_id is not null then STAB_DSSETORABASTECIMENTO "+
					"      else ' ' end as setor, "+
					" case when im.diop_id is not null then DIOP_DSDISTRITOOPERACIONAL "+
					"      else ' ' end as distrito, "+
					" case when im.arop_id is not null then AROP_DSAREAOPERACIONAL "+
					"      else ' ' end as area, "+
					" case when im.znpr_id is not null then ZNPR_DSZONAPRESSAO "+
					"      else ' ' end as zona, "+
					" count(*) as qtde "+
					" from cadastro.imovel im "+
					" inner join operacional.sistema_abastecimento    sa  on sa.sabs_id  = im.sabs_id "+
					" left  join operacional.subsistema_abastecimento sba on sba.sbab_id = im.sbab_id "+
					" left  join operacional.setor_abastecimento      sta on sta.stab_id = im.stab_id "+
					" left  join operacional.distrito_operacional     do  on do.diop_id  = im.diop_id "+
					" left  join operacional.area_operacional         ao  on ao.arop_id  = im.arop_id "+
					" left  join operacional.zona_pressao             zn  on zn.znpr_id  = im.znpr_id "+
					" where imov_icexclusao = 2 ";
					
			
			//Sistema de abastecimento
			if(Util.verificarIdNaoVazio(sistemaAbastecimento)){
				consulta += " AND im.sabs_id = :sistemaAbastecimento ";
				parametros.put("sistemaAbastecimento", sistemaAbastecimento);
			}
			
			//Subsistema de abastecimento
			if(Util.verificarIdNaoVazio(subsistemaAbastecimento)){
				consulta += " AND im.sbab_id = :subsistemaAbastecimento ";
				parametros.put("subsistemaAbastecimento", subsistemaAbastecimento);
			}
			
			//Setor de abastecimento
			if(Util.verificarIdNaoVazio(setorAbastecimento)){
				consulta += " AND im.stab_id = :setorAbastecimento ";
				parametros.put("setorAbastecimento", setorAbastecimento);
			}
			
			//Distrito operacional
			if(Util.verificarIdNaoVazio(distritoOperacional)){
				consulta += " AND im.diop_id = :distritoOperacional ";
				parametros.put("distritoOperacional", distritoOperacional);
			}
			
			//Área operacional
			if(Util.verificarIdNaoVazio(areaOperacional)){
				consulta += " AND im.arop_id = :areaOperacional ";
				parametros.put("areaOperacional", areaOperacional);
			}
			
			//Zona de pressão
			if(Util.verificarIdNaoVazio(zonaPressao)){
				consulta += " AND im.znpr_id = :zonaPressao ";
				parametros.put("zonaPressao", zonaPressao);
			}
			
			consulta += 
					" group by sabs_dssistemaabastecimento, "+
					" case when im.sbab_id is not null then SBAB_DSSUBSISTEMAABASTECIMENTO else ' ' end, "+
					" case when im.stab_id is not null then STAB_DSSETORABASTECIMENTO      else ' ' end, "+
					" case when im.diop_id is not null then DIOP_DSDISTRITOOPERACIONAL     else ' ' end, "+
					" case when im.arop_id is not null then AROP_DSAREAOPERACIONAL         else ' ' end, "+
					" case when im.znpr_id is not null then ZNPR_DSZONAPRESSAO             else ' ' end ";
			
			consulta += " order by  1,2,3,4,5,6 ";
					
			Query q = session.createSQLQuery(consulta)
							 .addScalar("sistema", Hibernate.STRING)      //0
							 .addScalar("subsistema", Hibernate.STRING)   //1
							 .addScalar("setor", Hibernate.STRING)        //2
							 .addScalar("distrito", Hibernate.STRING)	  //3	
							 .addScalar("area", Hibernate.STRING)         //4
							 .addScalar("zona", Hibernate.STRING)         //5
							 .addScalar("qtde", Hibernate.INTEGER);       //6
							 
							 
		Util.preencherParametrosQuery(parametros, q);				 
		
		retorno = (Collection<Object[]>)q.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterZonaPressaoCount(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, String idDistritoOperacional) throws ErroRepositorioException{
		
		Integer retorno = 0;
		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {
			consulta = " select count(distinct(znpr.znpr_id)) as qtd"
			+ " from operacional.zona_pressao znpr"
			+ "	inner join operacional.zona_area_operacional zaop on (znpr.znpr_id = zaop.znpr_id)"
			+ "	inner join operacional.area_distrito_operac adop on (adop.arop_id = zaop.arop_id)"
			+ " inner join operacional.distrito_setor_abastec disa on(disa.diop_id = adop.diop_id)"
			+ " inner join operacional.setor_subsistema_abastec stab on (stab.stab_id = disa.stab_id)"
		    + " inner join operacional.subsis_sis_abastecimento sbsi on(sbsi.sbab_id = stab.sbab_id)"
			+ " where ";
			   			
				
			if(codigo != null && !codigo.equals("")){
				consulta += " znpr.znpr_id = "+ new Integer(codigo) + " AND";
			}
			
			if(descricao != null && !descricao.equals("")){
				
				if (tipoPesquisa == null || tipoPesquisa.equals("")
				 || tipoPesquisa.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(znpr.znpr_dszonapressao) LIKE '"
							+ descricao.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(znpr.znpr_dszonapressao) LIKE '%"
							+ descricao.toUpperCase() + "%'  AND";
				}
			}
			
			if(descricaoAbreviada != null && !descricaoAbreviada.equals("")){
				consulta += " upper(znpr.znpr_dsabreviado) = '" + descricaoAbreviada + "'";
			}
			
			if(idSistemaAbastecimento != null && !idSistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " sbsi.sabs_id = "+ new Integer(idSistemaAbastecimento) + " AND";
			}
			
			if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.sbab_id = "+ new Integer(idSubsistemaAbastecimento) + " AND";
			}
			
			if(idSetorAbastecimento != null && !idSetorAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.stab_id = "+ new Integer(idSetorAbastecimento) + " AND";
			}
			
			if(idAreaOperacional != null && !idAreaOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " adop.arop_id = "+ new Integer(idAreaOperacional) + " AND";
			}
			
			if(indicadorUso != null && !indicadorUso.equals("") && !indicadorUso.equals("3")){
				consulta += " znpr.znpr_icuso = "+ new Integer(indicadorUso) + " AND";
			}
			
			if(Util.verificarIdNaoVazio(idDistritoOperacional)){
				consulta += " disa.diop_id = "+ new Integer(idDistritoOperacional) + " AND";
			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4);
			
			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"qtd", Hibernate.INTEGER).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<ZonaPressaoHelper> obterZonaPressao(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, Integer numeroPagina, String idDistritoOperacional) throws ErroRepositorioException{
		
		Collection<Object[]> retornoConsulta = new ArrayList<Object[]>();
		Collection<ZonaPressaoHelper> retorno = new ArrayList<ZonaPressaoHelper>();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			consulta = " select distinct(znpr.znpr_id) as idZonaPressao, "
					+ " znpr.znpr_dszonapressao as descricaoZonaPressao,"
					+ " znpr.znpr_icuso as indicadorUso"
					+ " from operacional.zona_pressao znpr"
					+ "	inner join operacional.zona_area_operacional zaop on (znpr.znpr_id = zaop.znpr_id)"
					+ "	inner join operacional.area_distrito_operac adop on (adop.arop_id = zaop.arop_id)"
					+ " inner join operacional.distrito_setor_abastec disa on(disa.diop_id = adop.diop_id)"
					+ " inner join operacional.setor_subsistema_abastec stab on (stab.stab_id = disa.stab_id)"
				    + " inner join operacional.subsis_sis_abastecimento sbsi on(sbsi.sbab_id = stab.sbab_id)"
					+ " where ";
			   			
				
			if(codigo != null && !codigo.equals("")){
				consulta += " znpr.znpr_id = "+ new Integer(codigo) + " AND";
			}
			
			if(descricao != null && !descricao.equals("")){
				
				if (tipoPesquisa == null || tipoPesquisa.equals("")
				 || tipoPesquisa.trim().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					consulta += " upper(znpr.znpr_dszonapressao) LIKE '"
							+ descricao.toUpperCase() + "%'  AND";
				} else {
					consulta += " upper(znpr.znpr_dszonapressao) LIKE '%"
							+ descricao.toUpperCase() + "%'  AND";
				}
			}
			
			if(descricaoAbreviada != null && !descricaoAbreviada.equals("")){
				consulta += " upper(znpr.znpr_dsabreviado) = '" + descricaoAbreviada + "'";
			}
			
			if(idSistemaAbastecimento != null && !idSistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " sbsi.sabs_id = "+ new Integer(idSistemaAbastecimento) + " AND";
			}
			
			if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.sbab_id = "+ new Integer(idSubsistemaAbastecimento) + " AND";
			}
			
			if(idSetorAbastecimento != null && !idSetorAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " stab.stab_id = "+ new Integer(idSetorAbastecimento) + " AND";
			}
			
			if(idAreaOperacional != null && !idAreaOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				consulta += " adop.arop_id = "+ new Integer(idAreaOperacional) + " AND";
			}
			
			if(indicadorUso != null && !indicadorUso.equals("") && !indicadorUso.equals("3")){
				consulta += " znpr.znpr_icuso = "+ new Integer(indicadorUso) + " AND";
			}
			
			if(Util.verificarIdNaoVazio(idDistritoOperacional)){
				consulta += " disa.diop_id = "+ new Integer(idDistritoOperacional) + " AND";
			}
			
			consulta = Util.removerUltimosCaracteres(consulta, 4) + " ORDER BY znpr.znpr_dszonapressao ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idZonaPressao", Hibernate.INTEGER)
				    .addScalar("descricaoZonaPressao", Hibernate.STRING)
					.addScalar("indicadorUso", Hibernate.SHORT);

			retornoConsulta = (Collection<Object[]>)query
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			
			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				Iterator<Object[]> iterRetornoConsulta = retornoConsulta.iterator();
				while (iterRetornoConsulta.hasNext()) {
					Object[] objeto = (Object[]) iterRetornoConsulta.next();
					ZonaPressaoHelper zona = new ZonaPressaoHelper();
					zona.setIdZonaPressao((Integer) objeto[0]);
					zona.setDescricaoZonaPressao((String) objeto[1]);
					zona.setIndicadorUso((Short) objeto[2]);
					retorno.add(zona);
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
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SubsistemaAbastecimento obterSubsistemaPrincipalZonaPressao(Integer idZonaPressao) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		SubsistemaAbastecimento subsistemaAbastecimento = null;
		String consulta = "";
		Object[] retornoConsulta;
		
		try{
			consulta = 	"SELECT sbab.sbab_id as id, "
					 +  "sbab.sbab_dssubsistemaabastecimento as descricao "
					 +	"FROM operacional.subsistema_abastecimento sbab "
					 +  "INNER JOIN operacional.setor_subsistema_abastec stab ON (stab.sbab_id = sbab.sbab_id and SSAB_ICPRINCIPAL = 1) "
					 +  "INNER JOIN operacional.distrito_setor_abastec disa ON(disa.stab_id = stab.stab_id and DISA_ICPRINCIPAL = 1) "
					 +  "INNER JOIN operacional.area_distrito_operac adop on (adop.diop_id = disa.diop_id and adop.adop_icprincipal = 1) "
					 +  "INNER JOIN operacional.zona_area_operacional zaop on (zaop.arop_id = adop.arop_id and zaop.zaop_icprincipal = 1) "
					 +	"WHERE zaop.znpr_id = :idZonaPressao ";

			retornoConsulta = (Object[]) session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.setInteger("idZonaPressao", idZonaPressao)
					.setMaxResults(1)
					.uniqueResult();
			
			if(retornoConsulta != null && retornoConsulta.length == 2){
				subsistemaAbastecimento = new SubsistemaAbastecimento();
				subsistemaAbastecimento.setId((Integer) retornoConsulta[0]);
				subsistemaAbastecimento.setDescricao(retornoConsulta[1]+"");
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return subsistemaAbastecimento;
	}
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SetorAbastecimento obterSetorPrincipalZonaPressao(Integer idZonaPressao) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		SetorAbastecimento setorAbastecimento = null;
		String consulta = "";
		Object[] retornoConsulta;
		
		try{
			consulta = 	"SELECT stab.stab_id as id, "
					 +  "stab.stab_dssetorabastecimento as descricao "
					 +	"FROM operacional.setor_abastecimento stab "
					 +  "INNER JOIN operacional.distrito_setor_abastec disa ON(disa.stab_id = stab.stab_id and DISA_ICPRINCIPAL = 1) "
					 +  "INNER JOIN operacional.area_distrito_operac adop on (adop.diop_id = disa.diop_id and adop.adop_icprincipal = 1) "
					 +  "INNER JOIN operacional.zona_area_operacional zaop on (zaop.arop_id = adop.arop_id and zaop.zaop_icprincipal = 1) "
					 +	"WHERE zaop.znpr_id = :idZonaPressao ";

			retornoConsulta = (Object[]) session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.setInteger("idZonaPressao", idZonaPressao)
					.setMaxResults(1)
					.uniqueResult();
			
			if(retornoConsulta != null && retornoConsulta.length == 2){
				setorAbastecimento = new SetorAbastecimento();
				setorAbastecimento.setId((Integer) retornoConsulta[0]);
				setorAbastecimento.setDescricao(retornoConsulta[1]+"");
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return setorAbastecimento;
	}
	
	/** 
     * [UC0798] Manter Zona de Pressao 
     *  
     * @author Raphael Rossiter 
     * @date 20/06/2014 
     *  
     * @param zonaAreaOperacional 
     * @throws ErroRepositorioException 
     */
	public void atualizarZonaAreaOperacionalPrincipal(ZonaAreaOperacional zonaAreaOperacional)
			throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();

		try {

			update = "UPDATE gcom.operacional.ZonaAreaOperacional SET "
					+ "arop_id = :idAreaOperacional, ZAOP_TMULTIMAALTERACAO = sysdate "
					+ "WHERE ZNPR_ID = :idZonaPressao and ZAOP_ICPRINCIPAL = 1 ";

			session.createQuery(update)
					.setInteger("idAreaOperacional", zonaAreaOperacional.getAreaOperacional().getId())
					.setInteger("idZonaPressao", zonaAreaOperacional.getZonaPressao().getId())
					.executeUpdate();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceÃ§Ã£o para a prÃ³xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessÃ£o
			HibernateUtil.closeSession(session);
		}
	}
	
	
}
