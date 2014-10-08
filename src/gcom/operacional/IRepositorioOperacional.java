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
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 */
public interface IRepositorioOperacional {
	
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
		throws ErroRepositorioException ;
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 * 
	 */
	public Collection<SubSistemaEsgotoArquivoTexto> pesquisarSubSistemaEsgotoArquivoTexto(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
	 * @author Anderson Cabral
	 * @since 11/07/2013
	 */
	public Collection<SubSistemaEsgotoArquivoTextoErro> pesquisarSubSistemaEsgotoArquivoTextoErro(Integer idArquivo)
		throws ErroRepositorioException;
	
	
	public void removerLocalidadeSistemaAbastecimento(Integer id) throws ErroRepositorioException;

	
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
			throws ErroRepositorioException ;
	
	
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
			String subsistemaAgua, String indicadorUso, String numeroDaPagina, boolean relatorio) throws ErroRepositorioException;
	
	
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
			String subsistemaAgua, String indicadorUso) throws ErroRepositorioException;
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorAbastecimento(Integer idSetor) throws ErroRepositorioException, ConstraintViolationException;
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Collection<SubsistemaSistemaAbastecimento> pesquisarSubSistemaSistemaAbastecimento(Integer idSistemaAbastecimento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public Collection<SetorSubsistemaAbastecimento> pesquisarSetorSubsistemaAbastecimento(Integer idSetor)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0000] - Manter Setor de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 03/06/2014
	 * 
	 */
	public void removerSetorSubsistemaAbastecimento(Integer idSetor) throws ErroRepositorioException, ConstraintViolationException;
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [IT0002] Exibir Lista de Subsistema de Abastecimento.
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<SubsistemaAbastecimento> obterColecaoSubsistemaAbastecimento(Integer idSistemaAbastecimento)
			throws ErroRepositorioException;
	
	/**
	 * [UC1603] Inserir Área Operacional
	 * [IT0003] Exibir Lista de Distrito Operacional
	 * 
	 * @author Ana Maria
	 * @date 03/06/2014
	 */
	public Collection<DistritoOperacional> obterColecaoDistritoOperacional(Integer idSubsistemaAbastecimento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 04/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterAreaOperacionalCount(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso) throws ErroRepositorioException;
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaOperacionalHelper> obterAreaOperacional(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public AreaOperacional obterAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaDistritoOperacional> obterAreaDistritoOperacional(Integer idAreaOperacional) throws ErroRepositorioException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerAreaDistritoOperacional(Integer idAreaOperacional) throws ErroRepositorioException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException;
	
	/**
	 * [UC1604] - Filtrar Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AreaOperacionalHelper> pesquisarAreaOperacional(String descricao, String tipoPesquisa, String idSistemaAbastecimento,
		 String idSubsistemaAbastecimento,String idDistritoOperacional, String indicadorUso, String idSetorAbastecimento) throws ErroRepositorioException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public DistritoOperacional obterDistritoPrincipalAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException;
	
	/**
	 * [UC1605] - Manter Área Operacional
	 * 
	 * @author Ana Maria
	 * @date 05/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SubsistemaAbastecimento obterSubsistemaPrincipalAreaOperacional(Integer idAreaOperacional) throws ErroRepositorioException;
	
	
	/**
	 * [UC523] - Filtrar Distrito Operacional
	 * 
	 * @author Hugo Azevedo
	 * @date 13/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDistritoOperacional(
			String descricao, String indicadorPosicaoTexto, String sistemaAgua,
			String subsistemaAgua, String setorAbastecimento, 
			String indicadorUso,String numeroPagina, boolean relatorio) throws ErroRepositorioException;
	
	
	/**
	 * [UC0000] - Manter Distrito Operacional
	 * 
	 * @author Hugo Azevedo
	 * @date 13/06/2014
	 * 
	 */
	public void removerDistritoSetorAbastecimento(Integer idDistrito) throws ErroRepositorioException, ConstraintViolationException;
	
	
	/**
	 * [UC1613] - Gerar Relatório Totalizador por Sistema de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 20/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarTotalizacaoSetoresAbastecimentoAnalitico(String sistemaAbastecimento, 
																		 String subsistemaAbastecimento,
																		 String setorAbastecimento,
																		 String distritoOperacional,
																		 String areaOperacional,
																		 String zonaPressao,
																		 Integer anoMesMenosUm) throws ErroRepositorioException;
	
	
	
	/**
	 * [UC1613] - Gerar Relatório Totalizador por Sistema de Abastecimento
	 * 
	 * @author Hugo Azevedo
	 * @date 20/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarTotalizacaoSetoresAbastecimentoSintetico(String sistemaAbastecimento, 
																				 String subsistemaAbastecimento,
																				 String setorAbastecimento,
																				 String distritoOperacional,
																				 String areaOperacional,
																				 String zonaPressao) throws ErroRepositorioException;
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterZonaPressaoCount(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, String idDistritoOperacional) throws ErroRepositorioException;
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<ZonaPressaoHelper> obterZonaPressao(String codigo, String descricao, String tipoPesquisa, String descricaoAbreviada, String idSistemaAbastecimento,
			 String idSubsistemaAbastecimento,String idSetorAbastecimento, String idAreaOperacional, String indicadorUso, Integer numeroPagina, String idDistritoOperacional) throws ErroRepositorioException;
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SubsistemaAbastecimento obterSubsistemaPrincipalZonaPressao(Integer idZonaPressao) throws ErroRepositorioException;
	
	/**
	 * [UC0797] Filtrar Zona de Pressao
	 * 
	 * @author Raphael Rossiter
	 * @date 19/06/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public SetorAbastecimento obterSetorPrincipalZonaPressao(Integer idZonaPressao) throws ErroRepositorioException;
	
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
			throws ErroRepositorioException ;
}
