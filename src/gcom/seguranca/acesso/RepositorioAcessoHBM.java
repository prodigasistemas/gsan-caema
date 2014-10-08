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
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROPӓSITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.seguranca.acesso;

import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioSolicitacaoAcessoHelper;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.Util;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.ParametroSimples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Descri��o da classe 
 *
 * @author Administrador
 * @date 13/11/2006
 */
public class RepositorioAcessoHBM implements IRepositorioAcesso {

	protected static IRepositorioAcesso instancia;

	
	public RepositorioAcessoHBM() {
	}

	
	public static IRepositorioAcesso getInstancia() {
		
		String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			if (instancia == null) {
				instancia = new RepositorioAcessoHBM();
			}
		} else {
			if (instancia == null) {
				instancia = new RepositorioAcessoPostgresHBM();
			}
		}

		return instancia;
	}

	
	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Administrador
	 * @date 13/11/2006
	 *
	 * @param consulta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarObjetoAbrangencia(String consulta) throws ErroRepositorioException {
		Object retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		

		try {
		
			retorno = session.createQuery(consulta).uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	public void atualizarRegistrarAcessoUsuario(Usuario usuario)
	 	throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();

		String consulta = "update Usuario usu "
				+ "set usu.numeroAcessos =:acesso, usu.ultimoAcesso = :ultimo "
				+ "where usu.id = :idUsuario" ;

		try {

			session.createQuery(consulta).
				setInteger("acesso",usuario.getNumeroAcessos()).
				setTimestamp("ultimo",usuario.getUltimoAcesso()).
				setInteger("idUsuario",usuario.getId()).
				executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
		
	}
	/**
	 * Pesquisa os favoritos do usuario
	 * 
	 * @author: Rafael Pinto
	 * @date: 01/06/2009
	 */	
	public Collection pesquisarUsuarioFavorito(Integer idUsuario)
		throws ErroRepositorioException {
		
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			
			consulta = "SELECT usuarioFav "
				+ "FROM UsuarioFavorito usuarioFav "
				+ "INNER JOIN FETCH usuarioFav.funcionalidade func "
				+ "WHERE usuarioFav.usuario.id = :idUsuario "
				+ "AND usuarioFav.indicadorFavoritosUltimosAcessados = :indicador "
				+ "ORDER BY usuarioFav.ultimaAlteracao DESC" ;
		
			retorno = session.createQuery(consulta).
				setInteger("idUsuario",idUsuario).
				setShort("indicador",ConstantesSistema.SIM).
				list();
		
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/**
	 * [UC0407]-Filtrar Im�veis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrang�ncia do c�digo do usu�rio
	 * 
	 * Verifica se existe localidade que esteja fora da abrang�ncia do usu�rio 
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarLocalidadeForaDaAbrangenciaUsuario(FiltrarImovelInserirManterContaHelper filtro,
			Integer nivelAbrangencia,Usuario usuarioLogado)throws ErroRepositorioException {

		Integer localidadeOrigemID = filtro.getLocalidadeOrigemID();
		Integer setorComercialOrigemID = filtro.getSetorComercialOrigemID();
		Integer quadraOrigemID = filtro.getQuadraOrigemID();
		Integer localidadeDestinoID = filtro.getLocalidadeDestinoID();
		Integer setorComercialDestinoID = filtro.getSetorComercialDestinoID();
		Integer quadraDestinoID = filtro.getQuadraDestinoID();
		Collection colecaoQuadraSelecionada = filtro.getColecaoQuadraSelecionada();
		Integer codigoRotaOrigem = filtro.getCodigoRotaOrigem();
		Integer codigoRotaDestino = filtro.getCodigoRotaDestino();

		// cria a cole��o de retorno
		Collection retorno = null;

		// Query
		String consulta;

		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			consulta = "select distinct(loca.loca_id) as idLocalidade "

			+ "FROM cadastro.localidade loca "
			+ "INNER JOIN cadastro.setor_comercial stcm on stcm.loca_id = loca.loca_id "
			+ "INNER JOIN cadastro.quadra qdra on qdra.stcm_id = stcm.stcm_id "
			+ "INNER JOIN micromedicao.rota rota on rota.rota_id = qdra.rota_id " + "WHERE 1 = 1 ";

			if (localidadeOrigemID != null && localidadeDestinoID != null) {
				consulta = consulta + "and loca.loca_id between " + localidadeOrigemID.intValue() + " and "	+ localidadeDestinoID.intValue();
			}

			if (setorComercialOrigemID != null && setorComercialDestinoID != null) {
				consulta = consulta + " and stcm.stcm_id between " + setorComercialOrigemID.intValue() + " and " + setorComercialDestinoID.intValue();
			}

			if (colecaoQuadraSelecionada != null && !colecaoQuadraSelecionada.isEmpty()) {
				consulta = consulta  + " and qdra_id in (:colecaoQuadraSelecionada)";
			} else if (quadraOrigemID != null && quadraDestinoID != null) {
				consulta = consulta + " and qdra_id between " + quadraOrigemID.intValue() + " and " + quadraDestinoID.intValue();
			}

			if (codigoRotaOrigem != null && codigoRotaDestino != null) {
				consulta = consulta + " and rota_cdrota between " + codigoRotaOrigem.shortValue() + " and " + codigoRotaDestino.shortValue();
			}

			
			switch (nivelAbrangencia.intValue()) {
				case UsuarioAbrangencia.GERENCIA_REGIONAL_INT :
					consulta = consulta + " and loca.greg_id <> " + usuarioLogado.getGerenciaRegional().getId();
					break;
	
				case UsuarioAbrangencia.UNIDADE_NEGOCIO_INT :
					consulta = consulta + " and loca.uneg_id <> " + usuarioLogado.getUnidadeNegocio().getId();
					break;
	
				case UsuarioAbrangencia.ELO_POLO_INT :
					consulta = consulta + " and loca.loca_cdelo <> " + usuarioLogado.getLocalidadeElo().getId();
					break;
	
				case UsuarioAbrangencia.LOCALIDADE_INT :
					consulta = consulta + " and loca.loca_id <> " + usuarioLogado.getLocalidade().getId();
					break;
	
			}
			
			if (colecaoQuadraSelecionada != null && !colecaoQuadraSelecionada.isEmpty()) {
				retorno = session.createSQLQuery(consulta).addScalar("idLocalidade" , Hibernate.INTEGER).setParameterList("colecaoQuadraSelecionada", colecaoQuadraSelecionada).list();
			} else {
				retorno = session.createSQLQuery(consulta).addScalar("idLocalidade" , Hibernate.INTEGER).list();
			}

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		// retorna a cole��o de atividades pesquisada(s)
		return retorno;

	}

	/**
	 * 
	 * Pesquisar senhas invalidas 
	 * 
	 * @author Hugo Amorim	
	 * @date 08/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSenhasInvalidas()
			throws ErroRepositorioException {
		
		// cria a cole��o de retorno
		Collection<String> retorno = null;

		// Query
		String consulta;

		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT distinct(senhaInvalida.sniv_dssenhainvalida) as senha "
					 + "FROM seguranca.senha_invalida senhaInvalida ";
			
			retorno = session.createSQLQuery(consulta).addScalar("senha", Hibernate.STRING).list();
			

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de senhas invalidas pesquisada(s)
		return retorno;
	}
	
	/**
	 * 
	 * Pesquisar senhas anteriores do usuario 
	 * 
	 * @author Hugo Amorim	
	 * @date 08/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSenhasAnterioresUsuario(Usuario usuario)
			throws ErroRepositorioException {
		
		// cria a cole��o de retorno
		Collection<String> retorno = null;

		// Query
		String consulta;

		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT distinct(senhasAnteriores.ushi_nmsenha) as senha"
					 + " FROM seguranca.usuario_senha_historico senhasAnteriores"
					 + " WHERE senhasAnteriores.usur_id = :idUsuario ";
			
			retorno = session.createSQLQuery(consulta)
				.addScalar("senha", Hibernate.STRING)
				.setParameter("idUsuario",usuario.getId())
				.list();
			

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de senhas invalidas pesquisada(s)
		return retorno;
	}
	
	/**
	 * [UC1040] Gerar Relat�rio de Acessos por Usu�rio
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper helper) throws ErroRepositorioException{
		
		Collection retorno = null;
		
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		
		Session session = HibernateUtil.getSession();
		
		try {
			if(helper.getPermissaoEspecial() != null ){
				
				consulta = consulta + " SELECT unid.unid_dsunidade as unid, " //0 
				+ " utip.utip_dsusuariotipo as tipoUsuario, " //1
				+ " usur.usur_nmusuario as nomeUsuario, " //2
				+ " usst.usst_dsusuariosituacao as situacao, " //3
				+ " grup.grup_dsgrupo as grupo, " //4
				+ " fncd.fncd_dsfuncionalidade as func, " //5
				+ " oper.oper_dsoperacao as operacao, " //6
				+ " modu.modu_dsmodulo as modulo, " //7
				+ " pmep.pmep_dspermissaoespecial as perm " //8
				+ " from  	seguranca.usuario usur "
				+ " inner	join seguranca.usuario_tipo utip on (utip.utip_id = usur.utip_id) "
				+ " inner	join seguranca.usuario_situacao usst on (usst.usst_id = usur.usst_id) "
				+ " inner	join seguranca.usuario_permissao_espec uspe on (uspe.usur_id = usur.usur_id) "
				+ " inner	join seguranca.permissao_especial pmep on (pmep.pmep_id = uspe.pmep_id) "
				+ " inner	join seguranca.usuario_grupo usgr on (usgr.usur_id = usur.usur_id) "
				+ " inner	join seguranca.grupo_func_operacao grfo on (grfo.grup_id = usgr.grup_id and grfo.oper_id = pmep.oper_id) "
				+ " inner	join seguranca.operacao oper on (oper.oper_id = grfo.oper_id) "
				+ " inner	join seguranca.funcionalidade fncd on (fncd.fncd_id = grfo.fncd_id) "
				+ " inner	join seguranca.grupo grup on (grup.grup_id = grfo.grup_id) "
				+ " inner 	join seguranca.modulo modu on (modu.modu_id = fncd.modu_id) "
				+ " inner	join cadastro.unidade_organizacional unid on (unid.unid_id = usur.unid_id) "
				+ " where	(grfo.grup_id,grfo.oper_id,grfo.fncd_id) "
				+ "     not in (select 	ugre.grup_id,ugre.oper_id,ugre.fncd_id "
				+ "     from seguranca.usuario_grupo_restricao ugre "
				+ "     where ugre.usur_id = usur.usur_id) ";
				
				// unidade organizacional
				if(helper.getUnidadeOrganizacional() != null ){
						
					consulta = consulta + " and usur.unid_id in ( :unidade2) "; 
					parameters.put("unidade2", helper.getUnidadeOrganizacional());
				}
					
				// usuario
				if(helper.getUsuario() != null 
						&& !helper.getUsuario().equalsIgnoreCase("")){
						
					consulta = consulta + " and usur.usur_id = " + helper.getUsuario() + " "; 
				}
				
				// grupo acesso
				if(helper.getGrupoAcesso() != null ){
						
					consulta = consulta + " and usgr.grup_id in ( :grupoAcesso2) "; 
					parameters.put("grupoAcesso2", helper.getGrupoAcesso());
				}
				
				// modulo
				if(helper.getModulo() != null 
						&& !helper.getModulo().equalsIgnoreCase("")){
						
					consulta = consulta + " and fncd.modu_id = " + helper.getModulo() + " "; 
				}
				
				// funcionalidade
				if(helper.getFuncionalidade() != null 
						&& !helper.getFuncionalidade().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.fncd_id = " + helper.getFuncionalidade() + " "; 
				}
				
				//operacao
				if(helper.getOperacao() !=null 
						&& !helper.getOperacao().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.oper_id = " + helper.getOperacao() + " "; 
				}
				
				// tipo de usuario
				if(helper.getUsuarioTipo() != null ){
						
					consulta = consulta + " and usur.utip_id in ( :usuarioTipo2)"; 
					parameters.put("usuarioTipo2", helper.getUsuarioTipo());
				}
				
				// situacao do usuario
				if(helper.getSituacaoUsuario() != null ){
						
					consulta = consulta + " and usur.usst_id in ( :situacao) ";
					parameters.put("situacao", helper.getSituacaoUsuario());
				}
				
				// permissao especial
				if(helper.getPermissaoEspecial() != null ){
						
					consulta = consulta + " and uspe.pmep_id in ( :permissao) "; 
					parameters.put("permissao", helper.getPermissaoEspecial());
				}		
				
				consulta = consulta + " order by 1,3,2,5,8,6,7,9 ";
			}else{
			
				consulta =	  " ( SELECT unid.unid_dsunidade as unid, " //0 
							+ " utip.utip_dsusuariotipo as tipoUsuario, " //1
							+ " usur.usur_nmusuario as nomeUsuario, " //2
							+ " usst.usst_dsusuariosituacao as situacao, " //3
							+ " grup.grup_dsgrupo as grupo, " //4
							+ " fncd.fncd_dsfuncionalidade as func, " //5
							+ " oper.oper_dsoperacao as operacao, " //6
							+ " modu.modu_dsmodulo as modulo, " //7
							+ " ' ' as perm " //8
							+ " from seguranca.usuario usur "
							+ " inner	join seguranca.usuario_tipo utip on (utip.utip_id = usur.utip_id) "
							+ " inner	join seguranca.usuario_situacao usst on (usst.usst_id = usur.usst_id) "
							+ " inner	join seguranca.usuario_grupo usgr on (usgr.usur_id = usur.usur_id) " 
							+ " inner	join seguranca.grupo_func_operacao grfo on (grfo.grup_id = usgr.grup_id) "
							+ " inner	join seguranca.operacao oper on (oper.oper_id = grfo.oper_id) "
							+ " inner	join seguranca.funcionalidade fncd on (fncd.fncd_id = grfo.fncd_id) "
							+ " inner	join seguranca.grupo grup on (grup.grup_id = grfo.grup_id) "
							+ " inner 	join seguranca.modulo modu on (modu.modu_id = fncd.modu_id) "
							+ " inner	join cadastro.unidade_organizacional unid on (unid.unid_id = usur.unid_id) "
							+ " where	(grfo.grup_id,grfo.oper_id,grfo.fncd_id) "
							+ "     not in (select 	ugre.grup_id,ugre.oper_id,ugre.fncd_id "
							+ "     from seguranca.usuario_grupo_restricao ugre "
							+ "     where ugre.usur_id = usur.usur_id) ";
				
				// unidade organizacional
				if(helper.getUnidadeOrganizacional() != null ){
						
					consulta = consulta + " and usur.unid_id in ( :unidade) "; 
					parameters.put("unidade", helper.getUnidadeOrganizacional());
				}
					
				// usuario
				if(helper.getUsuario() != null 
						&& !helper.getUsuario().equalsIgnoreCase("")){
						
					consulta = consulta + " and usur.usur_id = " + helper.getUsuario() + " "; 
				}
				
				// grupo acesso
				if(helper.getGrupoAcesso() != null ){
						
					consulta = consulta + " and usgr.grup_id in ( :grupoAcesso) "; 
					parameters.put("grupoAcesso", helper.getGrupoAcesso());
				}
				
				// modulo
				if(helper.getModulo() != null 
						&& !helper.getModulo().equalsIgnoreCase("")){
						
					consulta = consulta + " and fncd.modu_id = " + helper.getModulo() + " "; 
				}
				
				// funcionalidade
				if(helper.getFuncionalidade() != null 
						&& !helper.getFuncionalidade().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.fncd_id = " + helper.getFuncionalidade() + " "; 
				}
				
				// operacao
				if(helper.getOperacao() != null 
						&& !helper.getOperacao().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.oper_id = " + helper.getOperacao() + " "; 
				}
				
				// tipo de usuario
				if(helper.getUsuarioTipo() != null ){
						
					consulta = consulta + " and usur.utip_id in ( :usuarioTipo) "; 
					parameters.put("usuarioTipo", helper.getUsuarioTipo());
				}
				
				// situacao do usuario
				if(helper.getSituacaoUsuario() != null ){
						
					consulta = consulta + " and usur.usst_id in ( :situacao) ";
					parameters.put("situacao", helper.getSituacaoUsuario());
				}
				
				consulta = consulta + " ) UNION ALL (" 
						+ " SELECT unid.unid_dsunidade as unid, " //0 
						+ " utip.utip_dsusuariotipo as tipoUsuario, " //1
						+ " usur.usur_nmusuario as nomeUsuario, " //2
						+ " usst.usst_dsusuariosituacao as situacao, " //3
						+ " grup.grup_dsgrupo as grupo, " //4
						+ " fncd.fncd_dsfuncionalidade as func, " //5
						+ " oper.oper_dsoperacao as operacao, " //6
						+ " modu.modu_dsmodulo as modulo, " //7
						+ " pmep.pmep_dspermissaoespecial as perm " //8
						+ " from  	seguranca.usuario usur "
						+ " inner	join seguranca.usuario_tipo utip on (utip.utip_id = usur.utip_id) "
						+ " inner	join seguranca.usuario_situacao usst on (usst.usst_id = usur.usst_id) "
						+ " inner	join seguranca.usuario_permissao_espec uspe on (uspe.usur_id = usur.usur_id) "
						+ " inner	join seguranca.permissao_especial pmep on (pmep.pmep_id = uspe.pmep_id) "
						+ " inner	join seguranca.usuario_grupo usgr on (usgr.usur_id = usur.usur_id) "
						+ " inner	join seguranca.grupo_func_operacao grfo on (grfo.grup_id = usgr.grup_id and grfo.oper_id = pmep.oper_id) "
						+ " inner	join seguranca.operacao oper on (oper.oper_id = grfo.oper_id) "
						+ " inner	join seguranca.funcionalidade fncd on (fncd.fncd_id = grfo.fncd_id) "
						+ " inner	join seguranca.grupo grup on (grup.grup_id = grfo.grup_id) "
						+ " inner 	join seguranca.modulo modu on (modu.modu_id = fncd.modu_id) "
						+ " inner	join cadastro.unidade_organizacional unid on (unid.unid_id = usur.unid_id) "
						+ " where	(grfo.grup_id,grfo.oper_id,grfo.fncd_id) "
						+ "     not in (select 	ugre.grup_id,ugre.oper_id,ugre.fncd_id "
						+ "     from seguranca.usuario_grupo_restricao ugre "
						+ "     where ugre.usur_id = usur.usur_id) ";
						
				// unidade organizacional
				if(helper.getUnidadeOrganizacional() != null ){
						
					consulta = consulta + " and usur.unid_id in ( :unidade2) "; 
					parameters.put("unidade2", helper.getUnidadeOrganizacional());
				}
					
				// usuario
				if(helper.getUsuario() != null 
						&& !helper.getUsuario().equalsIgnoreCase("")){
						
					consulta = consulta + " and usur.usur_id = " + helper.getUsuario() + " "; 
				}
				
				// grupo acesso
				if(helper.getGrupoAcesso() != null ){
						
					consulta = consulta + " and usgr.grup_id in ( :grupoAcesso2) "; 
					parameters.put("grupoAcesso2", helper.getGrupoAcesso());
				}
				
				// usuario
				if(helper.getUsuario() != null 
						&& !helper.getUsuario().equalsIgnoreCase("")){
						
					consulta = consulta + " and usur.usur_id = " + helper.getUsuario() + " "; 
				}

				// modulo
				if(helper.getModulo() != null 
						&& !helper.getModulo().equalsIgnoreCase("")){
						
					consulta = consulta + " and fncd.modu_id = " + helper.getModulo() + " "; 
				}
				
				// modulo
				if(helper.getModulo() != null 
						&& !helper.getModulo().equalsIgnoreCase("")){
						
					consulta = consulta + " and fncd.modu_id = " + helper.getModulo() + " "; 
				}
				
				// funcionalidade
				if(helper.getFuncionalidade() != null 
						&& !helper.getFuncionalidade().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.fncd_id = " + helper.getFuncionalidade() + " "; 
				}
				
				//operacao
				if(helper.getOperacao() !=null 
						&& !helper.getOperacao().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.oper_id = " + helper.getOperacao() + " "; 
				}
				
				// tipo de usuario
				if(helper.getUsuarioTipo() != null ){
						
					consulta = consulta + " and usur.utip_id in ( :usuarioTipo2)"; 
					parameters.put("usuarioTipo2", helper.getUsuarioTipo());
				}
				
				// situacao do usuario
				if(helper.getSituacaoUsuario() != null ){
						
					consulta = consulta + " and usur.usst_id in ( :situacao) ";
					parameters.put("situacao", helper.getSituacaoUsuario());
				}
				
				// permissao especial
				if(helper.getPermissaoEspecial() != null ){
						
					consulta = consulta + " and uspe.pmep_id in ( :permissao) "; 
					parameters.put("permissao", helper.getPermissaoEspecial());
				}		
				
				//consulta = consulta + ") order	by 1,2,3,5,7,8 ";
				consulta = consulta + ") order	by 1,3,2,5,8,6,7,9 ";
			
			}
			
			query = (Query) session.createSQLQuery(consulta)
				.addScalar("unid", Hibernate.STRING)
				.addScalar("tipoUsuario",Hibernate.STRING)
				.addScalar("nomeUsuario",Hibernate.STRING)
				.addScalar("situacao",Hibernate.STRING)
				.addScalar("grupo",Hibernate.STRING)
				.addScalar("func",Hibernate.STRING)
				.addScalar("operacao",Hibernate.STRING)
				.addScalar("modulo",Hibernate.STRING)
				.addScalar("perm",Hibernate.STRING);
			
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
	 * [UC1040] Gerar Relat�rio de Acessos por Usu�rio
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper helper) throws ErroRepositorioException{
		
		Integer retorno = 0;
		
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		Session session = HibernateUtil.getSession();
		
		try {
			
			if(helper.getPermissaoEspecial() != null ){
				
				consulta = consulta + " SELECT count(oper.oper_id) as cont"
				+ " from  	seguranca.usuario usur "
				+ " inner	join seguranca.usuario_tipo utip on (utip.utip_id = usur.utip_id) "
				+ " inner	join seguranca.usuario_situacao usst on (usst.usst_id = usur.usst_id) "
				+ " inner	join seguranca.usuario_permissao_espec uspe on (uspe.usur_id = usur.usur_id) "
				+ " inner	join seguranca.permissao_especial pmep on (pmep.pmep_id = uspe.pmep_id) "
				+ " inner	join seguranca.usuario_grupo usgr on (usgr.usur_id = usur.usur_id) "
				+ " inner	join seguranca.grupo_func_operacao grfo on (grfo.grup_id = usgr.grup_id and grfo.oper_id = pmep.oper_id) "
				+ " inner	join seguranca.operacao oper on (oper.oper_id = grfo.oper_id) "
				+ " inner	join seguranca.funcionalidade fncd on (fncd.fncd_id = grfo.fncd_id) "
				+ " inner	join seguranca.grupo grup on (grup.grup_id = grfo.grup_id) "
				+ " inner 	join seguranca.modulo modu on (modu.modu_id = fncd.modu_id) "
				+ " inner	join cadastro.unidade_organizacional unid on (unid.unid_id = usur.unid_id) "
				+ " where	(grfo.grup_id,grfo.oper_id,grfo.fncd_id) "
				+ "     not in (select 	ugre.grup_id,ugre.oper_id,ugre.fncd_id "
				+ "     from seguranca.usuario_grupo_restricao ugre "
				+ "     where ugre.usur_id = usur.usur_id) ";
				
				// unidade organizacional
				if(helper.getUnidadeOrganizacional() != null ){
						
					consulta = consulta + " and usur.unid_id in ( :unidade2) "; 
					parameters.put("unidade2", helper.getUnidadeOrganizacional());
				}
					
				// usuario
				if(helper.getUsuario() != null 
						&& !helper.getUsuario().equalsIgnoreCase("")){
						
					consulta = consulta + " and usur.usur_id = " + helper.getUsuario() + " "; 
				}
				
				// grupo acesso
				if(helper.getGrupoAcesso() != null ){
						
					consulta = consulta + " and usgr.grup_id in ( :grupoAcesso2) "; 
					parameters.put("grupoAcesso2", helper.getGrupoAcesso());
				}
				
				// modulo
				if(helper.getModulo() != null 
						&& !helper.getModulo().equalsIgnoreCase("")){
						
					consulta = consulta + " and fncd.modu_id = " + helper.getModulo() + " "; 
				}
				
				// funcionalidade
				if(helper.getFuncionalidade() != null 
						&& !helper.getFuncionalidade().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.fncd_id = " + helper.getFuncionalidade() + " "; 
				}
				
				//operacao
				if(helper.getOperacao() !=null 
						&& !helper.getOperacao().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.oper_id = " + helper.getOperacao() + " "; 
				}
				
				// tipo de usuario
				if(helper.getUsuarioTipo() != null ){
						
					consulta = consulta + " and usur.utip_id in ( :usuarioTipo2)"; 
					parameters.put("usuarioTipo2", helper.getUsuarioTipo());
				}
				
				// situacao do usuario
				if(helper.getSituacaoUsuario() != null ){
						
					consulta = consulta + " and usur.usst_id in ( :situacao) ";
					parameters.put("situacao", helper.getSituacaoUsuario());
				}
				
				// permissao especial
				if(helper.getPermissaoEspecial() != null ){
						
					consulta = consulta + " and uspe.pmep_id in ( :permissao) "; 
					parameters.put("permissao", helper.getPermissaoEspecial());
				}		
			}else{
			
				consulta =" SELECT count(oper.oper_id) as cont " //6
						+ " from seguranca.usuario usur "
						+ " inner	join seguranca.usuario_tipo utip on (utip.utip_id = usur.utip_id) "
						+ " inner	join seguranca.usuario_situacao usst on (usst.usst_id = usur.usst_id) "
						+ " inner	join seguranca.usuario_grupo usgr on (usgr.usur_id = usur.usur_id) " 
						+ " inner	join seguranca.grupo_func_operacao grfo on (grfo.grup_id = usgr.grup_id) "
						+ " inner	join seguranca.operacao oper on (oper.oper_id = grfo.oper_id) "
						+ " inner	join seguranca.funcionalidade fncd on (fncd.fncd_id = grfo.fncd_id) "
						+ " inner	join seguranca.grupo grup on (grup.grup_id = grfo.grup_id) "
						+ " inner 	join seguranca.modulo modu on (modu.modu_id = fncd.modu_id) "
						+ " inner	join cadastro.unidade_organizacional unid on (unid.unid_id = usur.unid_id) "
						+ " where	(grfo.grup_id,grfo.oper_id,grfo.fncd_id) "
						+ "     not in (select 	ugre.grup_id,ugre.oper_id,ugre.fncd_id "
						+ "     from seguranca.usuario_grupo_restricao ugre "
						+ "     where ugre.usur_id = usur.usur_id) ";
		
				// unidade organizacional
				if(helper.getUnidadeOrganizacional() !=null ){
						
					consulta = consulta + " and usur.unid_id in ( :unidade) "; 
					parameters.put("unidade", helper.getUnidadeOrganizacional());
				}
					
				// usuario
				if(helper.getUsuario() !=null 
						&& !helper.getUsuario().equalsIgnoreCase("")){
						
					consulta = consulta + " and usur.usur_id = " + helper.getUsuario() + " "; 
				}
				
				// grupo acesso
				if(helper.getGrupoAcesso() !=null){
						
					consulta = consulta + " and usgr.grup_id in ( :grupoAcesso) "; 
					parameters.put("grupoAcesso", helper.getGrupoAcesso());
				}
				
				// modulo
				if(helper.getModulo() !=null 
						&& !helper.getModulo().equalsIgnoreCase("")){
						
					consulta = consulta + " and fncd.modu_id = " + helper.getModulo() + " "; 
				}
				
				// funcionalidade
				if(helper.getFuncionalidade() !=null 
						&& !helper.getFuncionalidade().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.fncd_id = " + helper.getFuncionalidade() + " "; 
				}
				
				//operacao
				if(helper.getOperacao() !=null 
						&& !helper.getOperacao().equalsIgnoreCase("")){
						
					consulta = consulta + " and grfo.oper_id = " + helper.getOperacao() + " "; 
				}
				
				// tipo de usuario
				if(helper.getUsuarioTipo() !=null){
						
					consulta = consulta + " and usur.utip_id in ( :usuarioTipo) "; 
					parameters.put("usuarioTipo", helper.getUsuarioTipo());
				}
				
				// situacao do usuario
				if(helper.getSituacaoUsuario() != null ){
						
					consulta = consulta + " and usur.usst_id in ( :situacao) ";
					parameters.put("situacao", helper.getSituacaoUsuario());
				}
			}
			
			query = (Query) session.createSQLQuery(consulta).addScalar("cont", Hibernate.INTEGER);
			
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
			
			retorno = (Integer) query.setMaxResults(1).uniqueResult();
        
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;	
	}
	
	/**
	 * [UC1039] Gerar Relat�rio de Funcionalidades e Opera��es por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper) throws ErroRepositorioException{
		
		Collection retorno = null;
		
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		
		Session session = HibernateUtil.getSession();
		
		try {
			consulta =	  " select	grup.grup_dsgrupo as grupo, " //0 
						+ " modu.modu_dsmodulo as modulo, " //1
						+ " fncd.fncd_dsfuncionalidade as func, " //2
						+ " oper.oper_dsoperacao as operacao " //3
						+ " from	seguranca.grupo_func_operacao grfo "
						+ " inner	join seguranca.operacao oper on (oper.oper_id = grfo.oper_id) "
						+ " inner	join seguranca.funcionalidade fncd on (fncd.fncd_id = grfo.fncd_id) "
						+ " inner	join seguranca.grupo grup on (grup.grup_id = grfo.grup_id) " 
						+ " inner 	join seguranca.modulo modu on (modu.modu_id = fncd.modu_id) "
						+ " where ";
			
			// Grupo
			if(helper.getGrupo() != null ){
					
				consulta = consulta + " grup.grup_id in ( :grupo) "; 
				parameters.put("grupo", helper.getGrupo());
			}
			
			consulta = consulta + " order	by 1,2,3,4 ";					
			
			query = (Query) session.createSQLQuery(consulta)
				.addScalar("grupo", Hibernate.STRING)
				.addScalar("modulo",Hibernate.STRING)
				.addScalar("func",Hibernate.STRING)
				.addScalar("operacao",Hibernate.STRING);
			
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
	 * [UC1039] Gerar Relat�rio de Funcionalidades e Opera��es por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper) throws ErroRepositorioException{
		
		Integer retorno = 0;
		
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta =" select	count(oper.oper_id) as cont " //6
					+ " from	seguranca.grupo_func_operacao grfo "
					+ " inner	join seguranca.operacao oper on (oper.oper_id = grfo.oper_id) "
					+ " inner	join seguranca.funcionalidade fncd on (fncd.fncd_id = grfo.fncd_id) "
					+ " inner	join seguranca.grupo grup on (grup.grup_id = grfo.grup_id) " 
					+ " inner 	join seguranca.modulo modu on (modu.modu_id = fncd.modu_id) "
					+ " where ";
	
			// Grupo
			if(helper.getGrupo() != null ){
					
				consulta = consulta + " grup.grup_id in ( :grupo) "; 
				parameters.put("grupo", helper.getGrupo());
			}
			
			query = (Query) session.createSQLQuery(consulta).addScalar("cont", Hibernate.INTEGER);
			
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
			
			retorno = (Integer) query.setMaxResults(1).uniqueResult();
        
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;	
	}
	
	/**
	 * Informa o n�mero total de registros do grupo, auxiliando o
	 * esquema de pagina��o
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return n�mero de registros da pesquisa
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarGrupos( FiltroGrupo filtroGrupo, Integer numeroPagina)
			throws ErroRepositorioException {
		
		// cria a cole��o de retorno
		Collection retorno = new ArrayList();
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(
			filtroGrupo,
			"grupo",
			"from Grupo as grupo",

			session).setFirstResult(10 * numeroPagina).setMaxResults(10).list()));

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;

	}
	
	/**
	 * Pesquisa se existe algum controle com permiss�o especial ativa para a funcionalidade.
	 * 
	 * @author: Daniel Alves
	 * @date: 31/08/2010
	 * @return boolean
	 */	
	public boolean existeControlePermissaoEspecialFuncionalidade(Integer idFuncionalidade)
		throws ErroRepositorioException {
		
		
		boolean retorno = false;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			
			consulta = "FROM gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial "
				+ "WHERE funcionalidade = :idFuncionalidade "
				+ "AND indicadorUso = :indicador ";
		
			Collection colecao = session.createQuery(consulta).
				setInteger("idFuncionalidade", idFuncionalidade).
				setShort("indicador",ConstantesSistema.SIM).
				list();
			
			
			if(colecao != null && colecao.size() >0){
				retorno = true;
			}
		
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/**
	 * [UC1093] Gerar Relat�rio Solicita��o de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 23/07/2010
	 * 
	 * @param FiltrarRelatorioSolicitacaoAcessoHelper
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioSolicitacaoAcesso(
			FiltrarRelatorioSolicitacaoAcessoHelper helper) throws ErroRepositorioException{
		
		Collection retorno = null;
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		Session session = HibernateUtil.getSession();
		
		try {

			consulta += " SELECT unid.id, " //0
					 +  " 		 unid.descricao, " //1
					 +  " 		 solAc.dataSolicitacao, " //2
					 +  " 		 usuSol.id, " //3
					 +  " 		 usuSol.nomeUsuario, " //4
					 +  " 		 funcRes.id, " //5
					 +  " 		 funcRes.nome, " //6
					 +  " 		 solAc.dataAutorizacao, " //7
					 +  " 		 func.id, " //8
					 +  " 		 solAc.cpf, " //9
					 +  " 		 solAc.nomeUsuario, " //10
					 +  " 		 solAcSit.descricao, " //11
					 +  " 		 solAc.periodoInicial, " //12
					 +  " 		 solAc.periodoFinal " //13
					 +  " FROM gcom.seguranca.acesso.usuario.SolicitacaoAcesso solAc "
					 +  " INNER JOIN solAc.unidadeOrganizacional unid "
					 +  " INNER JOIN solAc.usuarioSolicitante usuSol " //Usuario Solicitante
					 +  " INNER JOIN solAc.funcionarioResponsavel funcRes "
					 +  " INNER JOIN solAc.solicitacaoAcessoSituacao solAcSit "
					 +  " INNER JOIN solAc.empresa empr "
					 +  " LEFT JOIN solAc.funcionario func "
					 +  " WHERE 1=1 ";
			
			//Usu�rio Solicitante
			if(helper.getIdFuncionarioSolicitante() != null ){
					
				consulta += " and usuSol.id = " + helper.getIdFuncionarioSolicitante() + " "; 
			}
			
			// Per�odo
			if (helper.getDataInicial() != null && !helper.getDataInicial().equals("")
					&& helper.getDataFinal() != null && !helper.getDataFinal().equals("")){
				
				if (!Util.validarDiaMesAno(helper.getDataInicial())) {
					if (!Util.validarDiaMesAno(helper.getDataFinal())) {
						if(Util.compararData(
								Util.converteStringParaDate(
										helper.getDataInicial()), 
										Util.converteStringParaDate(helper.getDataFinal())) == 1){
							
							consulta += " and solAc.periodoInicial >= (:dataInicial)";
							parameters.put("dataInicial", Util.converteStringParaDate(helper.getDataInicial()));
							
							consulta +=  " and solAc.periodoFinal <= (:dataFinal)";
							parameters.put("dataFinal", Util.converteStringParaDate(helper.getDataFinal()));
						}
					}			
				}
			}
			
			// Funcion�rio Respons�vel
			if(helper.getIdFuncionarioSuperior() != null ){
					
				consulta += " and funcRes.id = " + helper.getIdFuncionarioSuperior() + " "; 
			}
			
			// Empresa
			if(helper.getIdEmpresa() != null && !helper.getIdEmpresa().equals("-1")){
				consulta += " and empr.id = " + helper.getIdEmpresa();
			}
			
			// Funcion�rio
			if(helper.getIdFuncionario() != null ){
					
				consulta += " and func.id = " + helper.getIdFuncionario(); 
			}

			// Nome Usu�rio
			if(helper.getNomeUsuario() != null ){
					
				consulta += " and solAc.nomeUsuario = " + helper.getNomeUsuario(); 
			}
			
			// Unidade Lota��o
			if(helper.getIdLotacao() != null ){
					
				consulta += " and unid.id = " + helper.getIdLotacao(); 
			}

			// Situa��o
			if(helper.getIdsSituacao() != null ){
					
				consulta = consulta + " and solAcSit.id in ( :solAcess) "; 
				parameters.put("solAcess", helper.getIdsSituacao());
			}
			
			consulta = consulta + " order by unid.id, solAc.dataSolicitacao ";
			
			query = (Query) session.createQuery(consulta);
			
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
				}else if (parameters.get(key) instanceof Date) {
					Date date = (Date) parameters.get(key);
					query.setDate(key, date);
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
	 * [UC0289] Efetuar Altera��o da Senha
	 * [FP-04] – Alterar o clone do usu�rio no banco de dados com mesmo login.
	 * @author Am�lia Pessoa
	 * @date 13/01/2012
	 * @param usuarioLogado
	 */
	public void alterarSenhaUsuarioBD(Usuario usuarioAlterar, Usuario usuarioBD) throws ErroRepositorioException {
		PreparedStatement st = null;
		Connection conn = null;
		try {			
			conn = DriverManager.getConnection(HibernateUtil.getStringConexao(), "\""+usuarioBD.getLogin()+"\"", usuarioBD.getSenha());  
			
			//Altera senha do usu�rio
			String query = "alter user \""+usuarioAlterar.getLogin()+"\" identified by \""+usuarioAlterar.getSenha()+"\" ";
			System.out.println(usuarioAlterar.getSenha());
			st = conn.prepareStatement(query);
			st.executeUpdate();  
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st!=null){ st.close(); } 
				if (conn!=null){ conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Retorna a lista das permiss�es especiais dispon�veis para o grupo  
	 * @author Amelia Pessoa
	 * @date 25/06/2012
	 * @param idGrupo 
	 */
	public Collection pesquisarPermissaoEspecial(Collection<Integer> idsGrupo1, Integer idGrupo2) throws ErroRepositorioException {
		Collection retorno = null;
		
		String consulta;
		Session session = HibernateUtil.getSession();
		Collection<Integer> idsGrupos = new ArrayList<Integer>();
		
		if(idsGrupo1 != null && !idsGrupo1.isEmpty()){
			idsGrupos.addAll(idsGrupo1);
		} 

		if(idGrupo2 != null){
			idsGrupos.add(idGrupo2);
		}
		
		try {

			consulta = "select distinct pe.PMEP_ID as id, " + //0
				" pe.PMEP_DSPERMISSAOESPECIAL as descricao "+ //1
				" from SEGURANCA.permissao_especial pe "+
				" join SEGURANCA.permissao_espec_operacao peo on pe.PMEP_ID = peo.PMEP_ID "+
				" join SEGURANCA.GRUPO_FUNC_OPERACAO gfo on peo.OPER_ID = gfo.OPER_ID " +
				" LEFT join SEGURANCA.GRUPO gru on gru.GRUP_ID = gfo.GRUP_ID " +
				" where pe.pmep_icuso = 1 ";
			
				consulta += " AND gfo.GRUP_ID in (:idsGrupos) order by pe.PMEP_DSPERMISSAOESPECIAL ";
						
				retorno = session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER)
						.addScalar("descricao", Hibernate.STRING)
						.setParameterList("idsGrupos",idsGrupos)
						.list();	
		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * Retorna uma lista com os ids dos grupos ligados ao grupo
	 * passado como parametro
	 *   
	 * @author Amelia Pessoa
	 * @date 25/06/2012
	 * @param idGrupo 
	 */
	public Collection<Integer> pesquisarArvoreNiveis(Integer idNivel) throws ErroRepositorioException {
		Collection<Integer> retorno = null;
		
		String consulta;
		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT GRUP_ID AS ID \n "+
				" FROM SEGURANCA.GRUPO \n "+
				" CONNECT BY PRIOR GRUP_CDGRUPOINFERIOR = GRUP_ID \n "+
				" START WITH GRUP_ID = :idNivel ";
			 
			retorno = session.createSQLQuery(consulta)
				.addScalar("ID", Hibernate.INTEGER)
				.setParameter("idNivel",idNivel)
				.list();			
			
		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Obtem o id do grupo associado a solicitacao de acesso
	 * @author Amelia Pessoa
	 * @param idSolicitacaoAcesso
	 * @return
	 */
	public Integer obterGrupo(Integer idSolicitacaoAcesso) throws ErroRepositorioException {
		Integer retorno = null;
		
		String consulta;
		Session session = HibernateUtil.getSession();

		try {

			consulta = "select g.GRUP_ID as ID from "+
				" seguranca.solicitacao_acesso_grupo sag "+ 
				" join seguranca.grupo g on sag.GRUP_ID = g.GRUP_ID "+
				" where SOAC_ID = :idSolicitacaoAcesso "+
				" and GRUP_ICGRUPOESPECIAL = 1 ";
			 
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("ID", Hibernate.INTEGER)
				.setParameter("idSolicitacaoAcesso",idSolicitacaoAcesso)
				.uniqueResult();			
			
		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		
		return retorno;		
	}
	
	/**
	 * Obtem o id do Nivel MAIS SUPERIOR associado a solicitacao de acesso
	 * @param idSolicitacaoAcesso
	 * @author Amelia Pessoa
	 * @return
	 */
	public Integer obterNivel(Integer idSolicitacaoAcesso) throws ErroRepositorioException {
		
		Integer retorno = null;
		
		String consulta;
		Session session = HibernateUtil.getSession();

		try {

			consulta = "select sag.GRUP_ID as ID from "+
				" seguranca.solicitacao_acesso_grupo sag "+ 
				" join seguranca.grupo g on sag.GRUP_ID = g.GRUP_ID "+
				" where SOAC_ID = :idSolicitacaoAcesso "+
				" and GRUP_ICGRUPOESPECIAL = 2 "+
				" and sag.GRUP_ID not in "+
				" (select GRUP_CDGRUPOINFERIOR from "+
				" seguranca.solicitacao_acesso_grupo ag "+ 
				" join seguranca.grupo g on ag.GRUP_ID = g.GRUP_ID "+
				" where SOAC_ID = :idSolicitacaoAcesso and GRUP_CDGRUPOINFERIOR is not null) ";
			 
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("ID", Hibernate.INTEGER)
				.setParameter("idSolicitacaoAcesso",idSolicitacaoAcesso).setMaxResults(1)
				.uniqueResult();			
			
		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Informa o n�mero total de registros de solicitacao acesso grupo, auxiliando o
	 * esquema de pagina��o
	 * 
	 * @author S�vio Luiz
	 * @date 25/09/2012
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return N�mero de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public int totalRegistrosPesquisaSolicitacaoAcessoGrupo(
			FiltroSolicitacaoAcessoGrupo filtroSolicitacaoAcessoGrupo)
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		int retorno = 0;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();
			camposOrderBy = filtroSolicitacaoAcessoGrupo.getCamposOrderBy();

			filtroSolicitacaoAcessoGrupo.limparCamposOrderBy();
			filtroSolicitacaoAcessoGrupo.limparColecaoCaminhosParaCarregamentoEntidades();
			
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = 
				(Integer) GeradorHQLCondicional.gerarCondicionalQuery(
					filtroSolicitacaoAcessoGrupo,
					"solicitacaoAcessoGrupo",
					"select count(distinct solicitacaoAcessoGrupo.solicitacaoAcesso.id) from SolicitacaoAcessoGrupo as solicitacaoAcessoGrupo",
					session).
				uniqueResult();

			filtroSolicitacaoAcessoGrupo.setCampoOrderBy((String[]) camposOrderBy.toArray(new String[camposOrderBy.size()]));

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;

	}
	
	/**
	 * Informa o n�mero total de registros de solicitacao acesso grupo, auxiliando o
	 * esquema de pagina��o
	 * 
	 * @author S�vio Luiz
	 * @date 25/09/2012
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return N�mero de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public Collection pesquisarSolicitacaoAcessoGrupo(
			FiltroSolicitacaoAcessoGrupo filtroSolicitacaoAcessoGrupo, Integer numeroPagina)
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		Collection retornoCompleto = new ArrayList();
		
		Collection retorno = new ArrayList();
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();
			camposOrderBy = filtroSolicitacaoAcessoGrupo.getCamposOrderBy();
			
			

			filtroSolicitacaoAcessoGrupo.limparCamposOrderBy();
			filtroSolicitacaoAcessoGrupo.limparColecaoCaminhosParaCarregamentoEntidades();
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"

			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(
			filtroSolicitacaoAcessoGrupo,
			"solicitacaoAcessoGrupo",
			"select distinct solicitacaoAcessoGrupo.solicitacaoAcesso from SolicitacaoAcessoGrupo as solicitacaoAcessoGrupo",
			session).setFirstResult(10 * numeroPagina).setMaxResults(10).list()));

			filtroSolicitacaoAcessoGrupo.setCampoOrderBy((String[]) camposOrderBy
					.toArray(new String[camposOrderBy.size()]));
			
			
			//Pesquisa demais atributos
			FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.FUNCIONARIO_SOLICITANTE);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.UNIDADE_ORGANIZACIONAL);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.EMPRESA);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.ABRANGENCIA_ACESSO);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.GERENCIA_REGIONAL);	
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.UNIDADE_NEGOCIO);	
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.LOCALIDADE);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.USUARIO_SOLICITACAO);
			filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoAcesso.USUARIO_RESPONSAVEL);
			
			for (Object solicitacao : retorno){
				SolicitacaoAcesso solicit = (SolicitacaoAcesso) solicitacao;
				filtroSolicitacaoAcesso.limparListaParametros();
				filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(FiltroSolicitacaoAcesso.ID, solicit.getId()));
				Collection<SolicitacaoAcesso> colecoSolic = RepositorioUtilHBM.getInstancia()
						.pesquisar(filtroSolicitacaoAcesso, SolicitacaoAcesso.class.getName());
				retornoCompleto.addAll(colecoSolic);
			}

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retornoCompleto;

	
	}
	

	/**
	 * Retorna a lista das permiss�es especiais dispon�veis para o usu�rio
	 * RM8046 - Melhoria E-mail Autorizador
	 * @author Ana Maria
	 * @date 22/10/2012
	 * @param loginUsuario
	 */
	public Collection pesquisarPermissaoEspecialUsuario(String loginUsuario) 
			throws ErroRepositorioException {
		Collection retorno = null;
		
		String consulta;
		Session session = HibernateUtil.getSession();
		
		try {

			consulta = " select distinct pe.PMEP_ID as id, " + //0
					   " pe.PMEP_DSPERMISSAOESPECIAL as descricao "+ //1
					   " from SEGURANCA.permissao_especial pe "+
					   " inner join SEGURANCA.usuario_permissao_espec usaf on(usaf.pmep_id = pe.pmep_id) "+
					   " inner join SEGURANCA.usuario usur on(usur.usur_id = usaf.usur_id) " +
					   " where usur.usur_nmlogin = :loginUsuario "+
					   " order by pe.PMEP_DSPERMISSAOESPECIAL ";
						
				retorno = session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER)
						.addScalar("descricao", Hibernate.STRING)
						.setString("loginUsuario",loginUsuario)
						.list();	
		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Valida o acesso do cliente pelo auto atendimento, atraves da matricula e cpf/cnpj
	 * 
	 * @author Anderson Cabral
	 * @date 27/08/2013
	 * @param matricula
	 * @param cpfCnpj
	 */
	public boolean validaLoginAutoAtendimento(Integer matricula, String cpfCnpj) throws ErroRepositorioException {
		boolean retorno = false;
		
		String consulta;
		Session session = HibernateUtil.getSession();
		
		try {

			consulta = " SELECT cliImov.imov_id id " +
					" FROM cadastro.cliente_imovel cliImov " +
					" INNER JOIN cadastro.cliente cli ON cli.clie_id = cliImov.clie_id " +
					" WHERE (cliImov.crtp_id = 1 OR cliImov.crtp_id = 2 OR cliImov.crtp_id = 3) " +
					" AND cliImov.imov_id = :matricula ";
			
			if(cpfCnpj.length() == 11){
				consulta +=  " AND cli.clie_nncpf = :cpfCnpj ";
			}else{
				consulta +=  " AND cli.clie_nncnpj = :cpfCnpj ";
			}
						
			retorno = !session.createSQLQuery(consulta)
					   .addScalar("id", Hibernate.INTEGER)
					   .setInteger("matricula", matricula)
					   .setString("cpfCnpj", cpfCnpj).list().isEmpty();
			
		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
}