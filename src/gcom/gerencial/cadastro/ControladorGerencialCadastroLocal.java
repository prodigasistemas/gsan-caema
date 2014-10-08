/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest„o de ServiÁos de Saneamento
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
* GSAN - Sistema Integrado de Gest„o de ServiÁos de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara˙jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl·udio de Andrade Lira
* Denys Guimar„es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* FabÌola Gomes de Ara˙jo
* Fl·vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J˙nior
* Homero Sampaio Cavalcanti
* Ivan SÈrgio da Silva J˙nior
* JosÈ Edmar de Siqueira
* JosÈ Thiago TenÛrio Lopes
* K·ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M·rcio Roberto Batista da Silva
* Maria de F·tima Sampaio Leite
* Micaela Maria Coelho de Ara˙jo
* Nelson MendonÁa de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael CorrÍa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara˙jo
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
* Este programa È software livre; vocÍ pode redistribuÌ-lo e/ou
* modific·-lo sob os termos de LicenÁa P˙blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers„o 2 da
* LicenÁa.
* Este programa È distribuÌdo na expectativa de ser ˙til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implÌcita de
* COMERCIALIZA«√O ou de ADEQUA«√O A QUALQUER PROP”SITO EM
* PARTICULAR. Consulte a LicenÁa P˙blica Geral GNU para obter mais
* detalhes.
* VocÍ deve ter recebido uma cÛpia da LicenÁa P˙blica Geral GNU
* junto com este programa; se n„o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gerencial.cadastro;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialCadastroLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * MÈtodo que gera o resumo das ligaÁıes e economias
	 * 
	 * [UC0275] - Gerar Resumo das LigaÁıes/Economias
	 * 
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 * 
	 */
	public void gerarResumoLigacoesEconomias(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo Anormalidade
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoLigacoesEconomias(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	public void gerarResumoConsumoAgua(int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * MÈtodo que gera o resumo do Parcelamento
	 * 
	 * [UC0565] - Gerar Resumo do Parcelamento
	 * 
	 * @author Marcio Roberto
	 * @param anoMes
	 * @date 04/05/2007
	 * 
	 */
	public void gerarResumoParcelamento(int idLocalidade,
			int idFuncionalidadeIniciada, int anoMes)
			throws ControladorException;

	/**
	 * MÈtodo que gera o resumo dos histogramas de ·gua e esgoto
	 * 
	 * [UC0566] - Gerar Histograma de ¡gua e Esgoto
	 * 
	 * @author Bruno Barros
	 * @date 09/05/2007
	 * 
	 */
	public void gerarResumoHistograma(int anoMesReferencia, int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * MÈtodo que gera resumo indicadores de comercializaÁ„o
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da ComercializaÁ„o
	 * 
	 * @author Rafael CorrÍa
	 * @date 06/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresComercializacao(int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * MÈtodo que gera o resumo de Metas da CAERN
	 * 
	 * [UC0623] - Gerar Resumo de Metas
	 * 
	 * @author S·vio Luiz
	 * @date 20/07/2007
	 * 
	 */
	public void gerarResumoMetas(int idSetor, Date dataInicial, Date dataFinal,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * MÈtodo que gera o resumo de Metas da CAERN
	 * 
	 * [UC0623] - Gerar Resumo de Metas
	 * 
	 * @author S·vio Luiz
	 * @date 20/07/2007
	 * 
	 */
	public void gerarResumoMetasAcumulado(int idSetor,
			Integer anoMesArrecadacao, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * MÈtodo que gera o resumo do coleta esgoto
	 * 
	 * [UC0573] - Gerar Resumo do coleta esgoto
	 * 
	 * @author Marcio Roberto
	 * @date 18/09/2007
	 * 
	 */
	public void gerarResumoColetaEsgoto(int idSetor, int idFuncionalidadeIniciada)
			throws ControladorException;

	public void excluirResumoGerencial(Integer anoMesFaturamento, String string, String string2, int idSetor) throws ControladorException;
	
    public void excluirResumoGerencial ( 
   		  int anomesreferencia , 
   		  String resumoGerencial, 
   		  String resumoCampoAnoMes,
   		  String resumoCampoUnidadeProcessamento,
   		  int idUnidadeProcessamento ) throws ControladorException;	
	
	public void excluirResumoGerencialSQL(Integer anoMesFaturamento, String string, String string2, int idSetor) throws ControladorException;	

	public void excluirResumoGerencialC(Integer anoMesFaturamento, String string, String string2, String string3, int idCampo) throws ControladorException;	
	
	/**
	 * MÈtodo que gera o resumo das ligaÁıes e economias Por Ano
	 * 
	 *Gerar Resumo das LigaÁıes/Economias Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 29/04/2010
	 * 
	 */
	public void gerarResumoLigacoesEconomiasPorAno(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * MÈtodo que gera o resumo do Consumo de Agua Por Ano
	 * 
	 *Gerar Resumo Consumo de Agua Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 24/05/2010
	 * 
	 */
	public void gerarResumoConsumoAguaPorAno(int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * MÈtodo que gera o resumo do coleta esgoto Por Ano
	 * 
	 * Gerar Resumo do coleta esgoto Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 15/06/2010
	 * 
	 */
	public void gerarResumoColetaEsgotoPorAno(int idSetor, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * MÈtodo que gera o resumo do Parcelamento PorAno
	 * 
	 * Gerar Resumo do Parcelamento Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 21/06/2010
	 * 
	 */
	public void gerarResumoParcelamentoPorAno(int idLocalidade,
			int idFuncionalidadeIniciada, int anoMes)
			throws ControladorException;
	
	/***
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 11/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param idSetor
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoHistogramaSemQuadra(
			int anoMesReferencia,
			int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * 
	 * @author Rafael Corrêa
	 * @date 28/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoConsumoAguaBD(int idFuncionalidadeIniciada)	throws ControladorException;
	
}