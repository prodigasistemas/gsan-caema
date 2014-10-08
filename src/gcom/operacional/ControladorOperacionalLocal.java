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

import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.operacional.bean.AreaOperacionalHelper;
import gcom.operacional.bean.ZonaPressaoHelper;
import gcom.relatorio.operacional.RelatorioTotalizadorSistemaAbastecimentoBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorOperacionalLocal extends
		javax.ejb.EJBLocalObject {


	/**
	 * [UC0414] - Informar Programação de Abastecimento e Manutenção
	 * 
	 * [SB0006] - Atualizar Programação de Abastecimento na Base de Dados
	 * [SB0007] - Atualizar Programação de Manutenção na Base de Dados
	 * 
	 * @author Rafael Pinto
	 * @created 09/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void atualizarProgramacaoAbastecimentoManutencao(Collection colecaoProgramacaoAbastecimento,
		Collection colecaoProgramacaoAbastecimentoRemovidas,Collection colecaoProgramacaoManutencao,
		Collection colecaoProgramacaoManutencaoRemovidas,Usuario usuario) throws ControladorException ;
	
	/**
	 * Permite inserir um Distrito Operacional
	 * 
	 * [UC0521] Inserir Distrito Operacional
	 * 
	 * @author Eduardo Bianchi	
	 * @date 29/01/2007
	 * 
	 */
	public Integer inserirDistritoOperacional(String descricao, String descricaoAbreviada, String setorAbastecimento,
			 Usuario usuarioLogado,Collection<SetorAbastecimento> colecaoSetores, String idSetorPrincipal)throws ControladorException;	

	/**
	 * [UC0522] Manter Distrito Operacional 
	 * 			
	 * 			Remover Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 05/02/2007
	 * 
	 */
	public void removerDistritoOperacional(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC005] Manter Distrito Operacional [SB0001] Atualizar Distrito Operacional 
	 * 
	 * @author Eduardo Bianchi
	 * @param colecaoSetores 
	 * @param idSetorPrincipal 
	 * @date 09/02/2007
	 * 
	 * @pparam distritoOperacinal
	 * @throws ControladorException
	 */
	public void atualizarDistritoOperacional(DistritoOperacional distritoOperacional,Usuario usuarioLogado, String idSetorPrincipal, Collection<SetorAbastecimento> colecaoSetores) throws ControladorException;
	
	
	/**
	 * Permite inserir um Sistema de Esgoto
	 * 
	 * [UC0524] Inserir Sistema de Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/03/2007
	 * 
	 */
	public Integer inserirSistemaEsgoto(SistemaEsgoto sistemaEsgoto, Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0525] Manter Sistema Esgoto [SB0001]Atualizar Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 19/03/2007
	 * 
	 */

	public void atualizarSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado) throws ControladorException;
	
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0002]Remover Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/03/2007
	 * 
	 */
	public void removerSistemaEsgoto(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC0081] Manter Marca Hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2007
	 * 
	 */
	
	public void removerHidrometroMarca(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC0081] Manter Hidrometro Marca
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2007
	 * 
	 */		
	public void atualizarHidrometroMarca(HidrometroMarca hidrometroMarca,Usuario usuarioLogado) 
						throws ControladorException;
	
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
		throws ControladorException ;

	/**
	 * [UC1519] - Inserir Subsistema de Esgoto 
	 * 
	 * @author Maxwell Moreira
	 * @date 03/07/2013
	 */
	public Integer inserirSubSistemaEsgoto(SubSistemaEsgoto subSistemaEsgoto) throws ControladorException;
	
	
	/**
	 * [UC1521] Manter SubSistema Esgoto
	 * 
	 * @author Maxwell Moreira
	 * @date 05/07/2013
	 */
	public void removerSubSistemaEsgoto(String[] ids) throws ControladorException;
	
	
	/**
	 * [UC1521] Manter SubSistema Esgoto
	 * 
	 * @author Maxwell Moreira
	 * @date 08/07/2013
	 */
	public void atualizarSubSistemaEsgoto(SubSistemaEsgoto subSistemaEsgoto) throws ControladorException;
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 * 
	 */
	public Collection<SubSistemaEsgotoArquivoTexto> pesquisarSubSistemaEsgotoArquivoTexto(Date dataInicial, Date dataFinal)
			throws ControladorException;
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 * 
	 */
	public Collection<SubSistemaEsgotoArquivoTextoErro> pesquisarSubSistemaEsgotoArquivoTextoErro(Integer idArquivo)
			throws ControladorException;
	
	
	public void removerLocalidadeSistemaAbastecimento(Integer id) throws ControladorException;
	
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
	public Integer inserirSubsistemaAbastecimento(SubsistemaAbastecimento subsistemaAbastecimento) throws ControladorException ;
	
	/** 
     * [UCXXXX] Manter Subsistema de Abastecimento 
     *  
     * @author Raphael Rossiter 
     * @date 05/06/2014 
     *  
     * @param idsSubsistemaAbastecimento 
     * @throws ControladorException 
     */
	public void removerSubsistemaAbastecimento(String[] idsSubsistemaAbastecimento) throws ControladorException ;
	
	/** 
     * [UCXXXX] Atualizar Subsistema de Abastecimento 
     *  
     * @author Raphael Rossiter 
     * @date 02/06/2014 
     *  
     * @param subsistemaAbastecimento 
     * @throws ControladorException 
     */
	public void atualizarSubsistemaAbastecimento(SubsistemaAbastecimento subsistemaAbastecimento) throws ControladorException ;

	
	
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
			String subsistemaAgua, String indicadorUso, String numeroDaPagina, boolean relatorio)  throws ControladorException;
	
	
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
			String subsistemaAgua, String indicadorUso) throws ControladorException;
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorAbastecimento(Integer idSetor) throws ControladorException; 
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Collection<SubsistemaSistemaAbastecimento> pesquisarSubSistemaSistemaAbastecimento(Integer idSistemaAbastecimento) throws ControladorException;
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Collection<SetorSubsistemaAbastecimento> pesquisarSetorSubsistemaAbastecimento(Integer idSetor)  throws ControladorException;
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorSubsistemaAbastecimento(Integer idSetor) throws ControladorException;
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [SB0001] Inserir Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Integer inserirAreaOperacional(AreaOperacional areaOperacional)
			throws ControladorException;
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [IT0002] Exibir Lista de Subsistema de Abastecimento.
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<SubsistemaAbastecimento> obterColecaoSubsistemaAbastecimento(Integer idSistemaAbastecimento)
			throws ControladorException;
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [IT0003] Exibir Lista de Distrito Operacional.
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<DistritoOperacional> obterColecaoDistritoOperacional(Integer idSubsistemaAbastecimento)
			throws ControladorException;
	
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
					 throws ControladorException;
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaOperacionalHelper> obterAreaOperacional(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, Integer numeroPagina) throws ControladorException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public AreaOperacional obterAreaOperacionalEDistritos(Integer idAreaOperacional) throws ControladorException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarAreaOperacional(AreaOperacional areaOperacional)throws ControladorException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerAreaOperacional(String[] ids) throws ControladorException;
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaOperacionalHelper> pesquisarAreaOperacional(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, String idSetorAbastecimento)throws ControladorException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public DistritoOperacional obterDistritoPrincipalAreaOperacional(Integer idAreaOperacional) throws ControladorException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SubsistemaAbastecimento obterSubsistemaPrincipalAreaOperacional(Integer idAreaOperacional) throws ControladorException;
	
	
	
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
			String indicadorUso,String numeroPagina, boolean relatorio) throws ControladorException;
	
	
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
																		 String tipoRelatorio) throws ControladorException;
	
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterZonaPressaoCount(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, String idDistritoOperacional) throws ControladorException;
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ControladorException
	 */
	public Collection<ZonaPressaoHelper> obterZonaPressao(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, Integer numeroPagina, String idDistritoOperacional) throws ControladorException;
	
	/** 
     * [UC0798] Manter Zona de Pressao 
     *  
     * @author Raphael Rossiter 
     * @date 20/06/2014 
     *  
     * @param idsZonaPressao 
     * @throws ControladorException 
     */
	public void removerZonaPressao(String[] idsZonaPressao) throws ControladorException ;
	
	/** 
     * [UC0798] Manter Zona de Pressao 
     *  
     * @author Raphael Rossiter 
     * @date 20/06/2014 
     *  
     * @param zonaPressao 
     * @throws ControladorException 
     */
	public void atualizarZonaPressao(ZonaPressao zonaPressao) throws ControladorException ;
	
}