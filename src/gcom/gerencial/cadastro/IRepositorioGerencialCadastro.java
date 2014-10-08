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

import gcom.cadastro.imovel.Categoria;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaEconomiaHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaEconomiaSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaLigacaoHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaLigacaoSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoEconomiaHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoEconomiaSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoLigacaoHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoLigacaoSemQuadraHelper;
import gcom.gerencial.cadastro.bean.ResumoColetaEsgotoHelper;
import gcom.gerencial.cadastro.bean.ResumoColetaEsgotoPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoConsumoAguaHelper;
import gcom.gerencial.cadastro.bean.ResumoConsumoAguaPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoLigacaoEconomiaPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoMetasHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface IRepositorioGerencialCadastro {

	/**
	 * MÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id rota id guradra numero da quadra id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Thiago Toscano, Bruno Barros
	 * @date 19/04/2006 17/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoLigacaoEconomias( int idLocalidade ) throws ErroRepositorioException;
	
	/**
	 * MÈtodo que retona uma lista de objeto xxxxx que representa os dados
	 * iniciais dos imoves selecionados da localidade, contendo os dados 
	 * iniciais para o agrupamento Histograma de Agua e Esgoto
	 * @author Bruno Barros
	 * @date 14/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */	
	public List getContasHistograma( int idLocalidade ) throws ErroRepositorioException;
	
	/**
	 * MÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * de resumo de consumo de agua
	 * 
	 * @author Bruno Barros
	 * @date 20/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosConsumoAgua(int idLocalidade )	throws ErroRepositorioException;
	
	
	/**
	 * MÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel, id da regi„o, id da microrregi„o, id do municÌpio, id do bairro,
	 * id do perfil do imÛvel, esfera de poder, id do tipo de cliente, id da situaÁ„o da ligaÁ„o ·gua,
	 * id situacao da ligacao de esgoto, principal categoria do imovel, principal sub categoria do imovel
	 * perfil da ligaÁ„o da ·gua, perfil da ligaÁ„o do esgoto
	 * 
	 * @author Ivan SÈrgio
	 * @date 19/04/2007
	 * 
	 * @param idLocalidade id da localida a ser pesquisada 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoLigacaoEconomiaRegiao(int idLocalidade ) throws ErroRepositorioException;
	
	
	/**
	 * [UC0269] - Consultar Resumo das Ligacoes / Economia
	 * 
	 * @author Thiago Toscano
	 * @date 29/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoLigacoesEconomias(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException;

	public Long maximoIdImovel() throws ErroRepositorioException;
	
	
	
	/**
	 * MÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id rota id guradra numero da quadra id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Marcio Roberto
	 * @date 04/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoParcelamento( int idLocalidade, int anoMes ) throws ErroRepositorioException;

	/**
	 * < <DescriÁ„o do mÈtodo>>
	 * 
	 * @param 
	 *            DescriÁ„o do par‚metro
	 * @return DescriÁ„o do retorno
	 * @exception ErroRepositorioException
	 *                DescriÁ„o da exceÁ„o
	 */
	public Integer pesquisarObterQuantidadeContas(Integer parcelamentoId) throws ErroRepositorioException;

	/**
	 * pesquisarObterQuantidadeGuias

	 * @author Marcio Roberto
	 * @date 07/05/2007
	 * 
	 * @param parcelamentoId id do parcelamento para relacionar-se com parcelamentoItem 
	 *            DescriÁ„o do par‚metro
	 * @return DescriÁ„o do retorno
	 * @exception ErroRepositorioException
	 *                DescriÁ„o da exceÁ„o
	 */
	public Integer pesquisarObterQuantidadeGuias(Integer parcelamentoId) throws ErroRepositorioException;
	
	/**
	 * pesquisarObterQuantidadeServicosIndiretos

	 * @author Marcio Roberto
	 * @date 07/05/2007
	 * 
	 * @param parcelamentoId id do parcelamento para relacionar-se com parcelamentoItem 
	 *            DescriÁ„o do par‚metro
	 * @return DescriÁ„o do retorno
	 * @exception ErroRepositorioException
	 *                DescriÁ„o da exceÁ„o
	 */
	public Short pesquisarObterQuantidadeServicosIndiretos(Integer parcelamentoId) throws ErroRepositorioException;

	/**
	 * pesquisarObterValorServicosIndiretos

	 * @author Marcio Roberto
	 * @date 07/05/2007
	 * 
	 * @param parcelamentoId id do parcelamento para relacionar-se com parcelamentoItem 
	 *            DescriÁ„o do par‚metro
	 * @return DescriÁ„o do retorno
	 * @exception ErroRepositorioException
	 *                DescriÁ„o da exceÁ„o
	 */
	public BigDecimal pesquisarObterValorServicosIndiretos(Integer parcelamentoId, String condicao)
			throws ErroRepositorioException; 
	
	/**
	 * pesquisaQuantidadeCategorias
	 * 
	 * @author Bruno Barros
	 * @date 14/05/2007
	 * 
	 * @param conta
	 *            id da conta a qual procuraremos as categorias
	 * 
	 * @return quantidade de categorias
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisaQuantidadeCategorias(Integer conta)
	throws ErroRepositorioException;
	
	/**
	 * pesquisaQuantidadeEconomias
	 * 
	 * @author Bruno Barros
	 * @date 14/05/2007
	 * 
	 * @param idConta id da conta a qual procuraremos as categorias	 
	 * @return quantidade de economias
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisaQuantidadeEconomias(Integer idConta, Integer idCategoria)
		throws ErroRepositorioException;
	
	/**
	 * pesquisarEsferaPoderClienteResponsavelImovel
	 * 
	 * @author Bruno Barros
	 * @date 16/05/2007
	 * 
	 * @param imovel a ser pesquisado
	 * @return Esfera de poder do cliente responsavel pelo imovel	
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarEsferaPoderClienteResponsavelImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * pesquisarTipoClienteClienteResponsavelImovel
	 * 
	 * @author Bruno Barros
	 * @date 16/05/2007
	 * 
	 * @param imovel a ser pesquisado	 
	 * @return Tipo de cliente do cliente responsavel pelo imovel
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarTipoClienteClienteResponsavelImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	public void inserirResumoConsumoAgua(
			Integer anoMesReferencia,
			ResumoConsumoAguaHelper resConsumo )
		throws ErroRepositorioException;
	
	/**
	 * Verifica se o consumo do imÛvel È real.
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel id do imovel que ter· seu consumo verificado	 * 
	 * @return 1 se consumo real, 2 sen„o
	 * @throws ErroRepositorioException
	 */	
	public Integer verificarConsumoReal( Integer idImovel )
		throws ErroRepositorioException;
	
	/**
	 * Verifica se o imÛvel possue hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel id do imovel que ter· seu consumo verificado	 * 
	 * @return 1 se possuir hidrimetro, 2 sen„o
	 * @throws ErroRepositorioException
	 */	
	public Integer verificarExistenciaHidrometro( Integer idImovel )
		throws ErroRepositorioException;
	
	/**
	 * Verifica se o imovel possue posso.
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel id do imovel que ter· seu consumo verificado	 * 
	 * @return 1 se consumo real, 2 sen„o
	 * @throws ErroRepositorioException
	 */	
	public Integer verificarExistenciaPoco( Integer idImovel )
		throws ErroRepositorioException;
	
	/**
	 * Verifica se o imovel volume fixo de agua
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel id do imovel que ter· seu volume fixo verificado 
	 * @return 1 se volume existe, 2 sen„o
	 * @throws ErroRepositorioException
	 */	
	public Integer verificarExistenciaVolumeFixoAgua( Integer idImovel )
		throws ErroRepositorioException;
	
	/**
	 * Verifica se o imovel volume fixo de esgoto
	 * 
	 * @author Bruno Barros
	 * @date 31/05/2007
	 * 
	 * @param idImovel id do imovel que ter· seu volume fixo verificado 
	 * @return 1 se volume existe, 2 sen„o
	 * @throws ErroRepositorioException
	 */	
	public Integer verificarExistenciaVolumeFixoEsgoto( Integer idImovel )
		throws ErroRepositorioException;
	
	public void inserirHistogramaAguaLigacao( Integer anoMesReferencia, HistogramaAguaLigacaoHelper helper )
		throws ErroRepositorioException;
	
	public void inserirHistogramaAguaEconomima( Integer anoMesReferencia, HistogramaAguaEconomiaHelper helper )
		throws ErroRepositorioException;

	public void inserirHistogramaEsgotoLigacao( Integer anoMesReferencia, HistogramaEsgotoLigacaoHelper helper )
		throws ErroRepositorioException;
	
	public void inserirHistogramaEsgotoEconomia( Integer anoMesReferencia, HistogramaEsgotoEconomiaHelper helper )
		throws ErroRepositorioException;	
	
	/**
	 * 
	 * [UC0623] - Gerar Resumo de Metas (CAERN)
	 * 
	 * Seleciona todos os imÛveis, com o ano/mes arrecadaÁ„o de sistema
	 * parametros de uma determinada gerÍncia regional por imovel
	 * 
	 * @author S·vio Luiz
	 * @date 20/07/2007
	 * 
	 * @param idLocalidade
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List pesquisarDadosResumoMetas(int idSetor,Date dataInicial,Date dataFinal)
			throws ErroRepositorioException;
	
	public void inserirResumoMetas(Integer anoMesReferencia,
			ResumoMetasHelper resMetas) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0623] - Gerar Resumo de Metas 2 (CAERN)
	 * 
	 * Seleciona todos os imÛveis, com o ano/mes arrecadaÁ„o de sistema
	 * parametros de uma determinada gerÍncia regional por imovel
	 * 
	 * @author S·vio Luiz
	 * @date 20/07/2007
	 * 
	 * @param idLocalidade
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List pesquisarDadosResumoMetasAcumulado(int idSetor)
			throws ErroRepositorioException;
	
	public void inserirResumoMetasAcumulado(Integer anoMesReferencia,
			ResumoMetasHelper resMetas) throws ErroRepositorioException;
	
	/**
	 * Seleciona o percentual de consumo de esgoto
	 * 
	 * @author Bruno Barros
	 * @date 27/07/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter· seu volume fixo verificado
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Float percentualColetaEsgoto(Integer idImovel)
			throws ErroRepositorioException;
	
	public List getConsumoHistoricoImoveisNaoFaturados(int idSetor)
		throws ErroRepositorioException;
	
	public List<Categoria> getCategoriasImovelDistintas( int idImovel ) 
	  throws ErroRepositorioException;
	
	public Integer getConsumoMinimoImovelCategoria( int idImovel, int idCategoria ) 
	  throws ErroRepositorioException;
	
	
	/**
	 * MÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * de resumo de coleta esgoto
	 * 
	 * @author Marcio Roberto
	 * @date 20/09/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosColetaEsgoto(int idSetor, Integer anoMesFaturamento) 
			throws ErroRepositorioException;
	
	/**
	 * Insere resumo Coleta Esgoto
	 * 
	 * @author Marcio Roberto
	 * @date 20/09/2007
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoColetaEsgoto(Integer anoMesReferencia,
			ResumoColetaEsgotoHelper resConsumo) throws ErroRepositorioException;
	
	/**
	 * Deleta resumo Coleta Esgoto antes de gerar
	 * 
	 * @author Marcio Roberto
	 * @date 27/09/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public void deletaResumoColetaEsgoto(Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Seleciona o percentual de consumo de esgoto
	 * 
	 * @author Marcio Roberto
	 * @date 02/10/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter· seu volume fixo verificado
	 * @return 
	 * @throws ErroRepositorioException
	 */	
	public float percentualEsgoto(Integer idImovel) throws ErroRepositorioException;
	
    public Categoria obterPrincipalCategoriaConta( int idConta ) 
    	throws ErroRepositorioException;
    
	/**
	 * Seleciona o maior mÍs/ano de referÍncia da tabela un_resumo_ligacao_economia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da ComercializaÁ„o
	 * 
	 * @author Rafael CorrÍa
	 * @date 06/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoLigacaoEconomia()
			throws ErroRepositorioException;
	
	/**
	 * Seleciona o maior mÍs/ano de referÍncia da tabela un_resumo_indicador_ligacao_economia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da ComercializaÁ„o
	 * 
	 * @author Rafael CorrÍa
	 * @date 06/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadorLigacaoEconomia()
			throws ErroRepositorioException;
	
	/**
	 * Atualiza os dados na tabela un_resumo_indicador_ligacao_economia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da ComercializaÁ„o
	 * 
	 * @author Rafael CorrÍa
	 * @date 06/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosResumoIndicadorLigacaoEconomia(Integer anoMesReferencia)
			throws ErroRepositorioException;
    
    /**
     * Verifica se ja foi gerado o ano mes de referencia de faturamento
     * para o resumo de consumo de agua
     * 
     * @author Bruno Leonardo Rodrigues Barros 
     * @return
     * @throws ErroRepositorioException
     */
    // public boolean resumoConsumoAguaGerado( int anomesreferencia, int idSetor )	throws ErroRepositorioException;
     
     /**
      * Apaga os dados do consumo de agua gerado para o mes de faturamento
      * 
      * @author Bruno Leonardo Rodrigues Barros 
      * @throws ErroRepositorioException
      */
     // public void excluirResumoConsumoAguaJaGerado (  int anomesreferencia, int idSetor  ) throws ErroRepositorioException;
     
     /**
      * Apaga os dados j· gerados do resumo para o ano/mes referencia e o setor comercial / pode ser usado em todos os resumos
      * 
      * @author Bruno Leonardo Rodrigues Barros/ Roberto Barbalho 
      * @throws ErroRepositorioException
      */
      
     public void excluirResumo ( 
    		 int anomesreferencia , 
    		 String resumoGerencial, 
    		 String resumoCampoAnoMes, 
    		 int idSetor,
    		 boolean excluirComercial ) throws ErroRepositorioException;
     
     public void excluirResumoGerencial ( 
   		  int anomesreferencia , 
   		  String resumoGerencial, 
   		  String resumoCampoAnoMes,
   		  String resumoCampoUnidadeProcessamento,
   		  int idUnidadeProcessamento ) throws ErroRepositorioException;     
     
     /**
      * Apaga os dados j· gerados do resumo para o ano/mes referencia e o setor comercial / pode ser usado em todos os resumos
      * 
      * @author Bruno Leonardo Rodrigues Barros/ Roberto Barbalho 
      * @throws ErroRepositorioException
      */
      
     public void excluirResumoSQL ( 
    		 int anomesreferencia , 
    		 String resumoGerencial, 
    		 String resumoCampoAnoMes, 
    		 int idSetor,
    		 boolean excluirComercial ) throws ErroRepositorioException;
     public void excluirResumoGerencialC ( int anomesreferencia , String resumoGerencial, String resumoCampoAnoMes, String campoEspecifico , int idCampoEspecifico) throws ErroRepositorioException;
     
     /***
  	 * [UC0573] - Gerar Resumo Coleta Esgoto
 	 * 
 	 * @author Ivan Sergio
 	 * @date 25/08/2008
  	 * 
  	 * @param idConta
  	 * @return
  	 * @throws ErroRepositorioException
  	 */
  	public Integer pesquisarConsumoMinimoEsgotoConta(Integer idConta) throws ErroRepositorioException;
  	
  	/**
  	 * Gerar Resumo LigaÁıes e Economias Por Ano
	 *
	 * @author Fernando Fontelles Filho
	 * @date 29/04/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException

	 */

	public List getImoveisResumoLigacaoEconomiasPorAno( int idSetor, int numeroIndice,
			int quantidadeRegistros ) throws ErroRepositorioException;
	
	/**
	 * 
	 * Atualizar Resumo de LigaÁıes e Economias Por ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 30/04/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 */
	public int atualizarResumoLigacoesEconomiasPorAno(int idGerenciaRegional,
			int idUnidadeNegocio,
			int idLocalidade,
			int idElo,
			int idSetorComercial,
			int codigoSetorComercial,
			int idPerfilImovel,
			int idEsfera,
			int idTipoClienteResponsavel,
			int idSituacaoLigacaoAgua,
			int idSituacaoLigacaoEsgoto,
			int idCategoria,
			int idSubCategoria,
			int idPerfilLigacaoAgua,
			int idPerfilLigacaoEsgoto,
			short idHidrometro,
			short idHidrometroPoco,
			short idVolFixadoAgua,
			short idVolFixadoEsgoto,
			short idPoco,
			int idTipoTarifaConsumo,
			int qtEconomias,
			int anoMes,
			Boolean incrementaQtdLigacoesNovasAgua,
			Boolean incrementaQtdLigacoesNovasEsgoto)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * Inserir Resumo Ligacoes Economias Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 30/04/2010
	 *
	 */
	public void inserirResumoLigacoesEconomiasPorAno(Integer anoMes, ResumoLigacaoEconomiaPorAnoHelper helper)
		throws ErroRepositorioException;

	
	/**
	 * Metodo pesquisa a quantidade de economias
	 * de uma conta, por subcategoria
	 * 
	 * @author Bruno Barros
	 * @date 25/05/2010
	 * 
	 * @param idConta - id da conta a qual procuraremos as categorias
	 * @param idCategoria - id da categoria a ser pesquisada
	 * @param idSubcategoria - id da subcategoria a ser pesquisada
	 * @return quantidade de economias
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisaQuantidadeEconomiasPorSubcategoria(Integer idConta,
			Integer idCategoria, Integer idSubcategoria ) throws ErroRepositorioException;

	
	/**
	 * MÈtodo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * de resumo de consumo de agua por ano
	 * 
	 * @author Fernando Fontelles
	 * @date 24/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosConsumoAguaPorAno(int idLocalidade )	throws ErroRepositorioException;
	
	/**
	 * Insere os dados do Resumo Consumo de Agua Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 24/05/2010
	 * 
	 * @param anoMesReferencia
	 * @param resConsumo
	 * 
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoConsumoAguaPorAno(Integer anoMesReferencia,
			ResumoConsumoAguaPorAnoHelper resConsumo )
		throws ErroRepositorioException;
	
	/**
	 * Seleciona todos os historicos de coleta de uma determinada localidade
	 * por imovel - Resumo Coleta Esgoto Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 16/06/2010
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 *            anoMesFaturamento da tabela sistema parametros
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosColetaEsgotoPorAno(int idSetor, Integer anoMesFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * Insere resumo Coleta Esgoto Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 16/06/2010
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoColetaEsgotoPorAno(Integer anoMesReferencia,
			ResumoColetaEsgotoPorAnoHelper resConsumo) throws ErroRepositorioException;
	
	/**
	 * MÈtodo que retorna uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Fernando Fontelles
	 * @date 21/06/2010
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoParcelamentoPorAno(int idLocalidade, int anoMes)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Seleciona o valor do Motivo Situacao do Faturamento
	 * 
	 * @author Fernando Fontelles
	 * @date 15/07/2010
	 * 
	 * @param idImovel
	 * @param conta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer getMotivoSituacaoFaturamento(Integer idImovel, Integer amReferenciaConta)
		throws ErroRepositorioException;
	
	/**
	 * [UC1057] - Gerar Resumo Histograma Agua Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 11/08/2010
	 * 
	 * @param idSetor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasHistogramaSemQuadra(int idSetor, int anoMesReferenciaFaturamento)
		throws ErroRepositorioException;
	
	/**
	 * [UC1057] - Gerar Resumo Histograma Agua Esgoto Sem Quadras
	 * Filtra a Conta na tabela ContaCategoria por Categoria ordenando pela quantidade
	 * de Economia da Categoria.
	 * 
	 * @author Ivan Sergio
	 * @date: 12/08/2010
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List filtrarContaCategoriaHistogramaPorCategoria(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * [UC1057] - Gerar Resumo Histograma Agua Esgoto Sem Quadras
	 * Filtra a Conta na tabela ContaCategoria por SubCategoria ordenando pela quantidade
	 * de Economia da SubCategoria.
	 * 
	 * @author Ivan Sergio
	 * @date: 12/08/2010
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List filtrarContaCategoriaHistograma(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * Verifica o consumo real do Esgoto
	 * @author Ivan Sergio
	 * @data 16/08/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarConsumoRealEsgoto(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 19/08/2010
	 * 
	 * @param idConta
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarSubCategoriaContaCategoria(
			Integer idConta, Integer idCategoria) throws ErroRepositorioException;
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaAguaLigacaoSemQuadra(Integer anoMesReferencia,
			HistogramaAguaLigacaoSemQuadraHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaAguaEconomimaSemQuadra(
			Integer anoMesReferencia,
			HistogramaAguaEconomiaSemQuadraHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaEsgotoLigacaoSemQuadra(Integer anoMesReferencia,
			HistogramaEsgotoLigacaoSemQuadraHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaEsgotoEconomiaSemQuadra(Integer anoMesReferencia,
			HistogramaEsgotoEconomiaSemQuadraHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * [SB2000] Gerar Histograma Imoveis Nao Faturados
	 * @author Ivan Sergio
	 * @date 19/08/2010
	 * 
	 * @param idSetor
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getConsumoHistoricoImoveisNaoFaturadosSemQuadra(int idSetor, int anoMesReferencia)
			throws ErroRepositorioException;
	
	/**
	 * [UC1306] Gerar Resumo do Kit CAS de ServiÁo
	 * 
	 * [FS0001] - Verificar existÍncia de resumo para o ano/mÍs de referÍncia
	 * 
	 * @author Mariana Victor
	 * @data 02/04/2012
	 */
	public void removerResumoKisCasServico(Integer anoMesReferenciaFaturamento, Integer idLocalidade) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Rafael Corrêa
	 * @date 28/12/2012
	 *
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoConsumoAguaBD() throws ErroRepositorioException, SQLException;
	
	/**
	 * [UC0275] - Gerar Resumo das LigaÁıes/Economias     
	 *  
	 * @author Raphael Rossiter
	 * @date 09/07/2013
	 * @param idImovel
	 * @param dataExecucaoServico
	 * @param colecaoServicoTipo
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarOrdemServicoEncerradaPorServicoTipo(Integer idImovel, Date dataExecucaoServico, Collection<Integer> colecaoServicoTipo) 
			throws ErroRepositorioException ;
  	
}
