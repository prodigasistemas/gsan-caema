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
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.imovel;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.bean.FiltrarImoveisCortadosHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.bean.FiltrarImovelOutrosCriteriosHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.cadastro.GerarRelatorioImoveisDoacoesHelper;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * < <Descri��o da Interface>>
 * 
 * @author Administrador
 * @created 16 de Junho de 2004
 */
public interface IRepositorioImovel {

	/**
	 * Atualizar um os campos lastId,dataUltimaAtualiza��o do imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelExecucaoOrdemServicoLigacaoAgua(Imovel imovel,
			LigacaoAguaSituacao situacaoAgua) throws ErroRepositorioException;

	/**
	 * Atualizar um os campos lastId,dataUltimaAtualiza��o do imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(Imovel imovel,
			LigacaoEsgotoSituacao situacaoEsgoto, LigacaoEsgotoSituacao situacaoEsgotoAnterior)
			throws ErroRepositorioException;

	/**
	 * Inseri um imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void inserirImovel(Imovel imovel) throws ErroRepositorioException;

	/**
	 * Atualiza um imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovel(Imovel imovel) throws ErroRepositorioException;

	/**
	 * Remove um cliente imovel economia
	 * 
	 * @param id
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void removerClienteImovelEconomia(Integer id)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param objeto
	 *            Descri��o do par�metro
	 * @param condicional
	 *            Descri��o do par�metro
	 * @param id
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void removerTodos(String objeto, String condicional, Integer id)
			throws ErroRepositorioException;

	/**
	 * Pesquisa uma cole��o de im�veis com uma query especifica
	 * 
	 * @param idLocalidade
	 *            parametros para a consulta
	 * @param idSetorComercial
	 *            parametros para a consulta
	 * @param idQuadra
	 *            parametros para a consulta
	 * @param lote
	 *            Descri��o do par�metro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarImovel(Integer idLocalidade,
			Integer idSetorComercial, Integer idQuadra, Short lote,
			int indicadorExclusao) throws ErroRepositorioException;

	/**
	 * Atualiza apenas os dados (Localidade, Setor, Quadra e lote) do im�vel
	 * 
	 * @param imovel
	 *            parametros para a consulta
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelInscricao(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovelSubcategoria
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelSubCategoria(
			ImovelSubcategoria imovelSubcategoria)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Object pesquisarObterQuantidadeEconomias(Imovel imovel)
			throws ErroRepositorioException;
	
	public Short pesquisarObterQuantidadeEconomias(Imovel imovel, Categoria categoria) 
		throws ErroRepositorioException;


	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarObterQuantidadeEconomiasCategoria(
			Integer idImovel) throws ErroRepositorioException;

	public Collection obterQuantidadeEconomiasCategoria(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection obterDescricoesCategoriaImovel(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * [UC0164] Filtrar Imovel Por Outros Criterios
	 * 
	 * @author Rhawi Dantas
	 * @created 29/12/2005
	 * 
	 */
	public Collection<Imovel> pesquisarImovelParametrosClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0164] Filtrar Imovel Por Outros Criterios
	 * 
	 * @author Rhawi Dantas
	 * @created 29/12/2005
	 * 
	 */
	/*--<merge>--*/
	 public Collection pesquisarImovelOutrosCriterios(
	 FiltrarImovelOutrosCriteriosHelper filtrarImovelOutrosCriteriosHelper)
	 throws ErroRepositorioException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialFaturamento(String valor,
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ErroRepositorioException;

	/**
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @created 07/03/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialCobranca(String valor,
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ErroRepositorioException;

	public Integer verificarExistenciaImovel(Integer idImovel)
			throws ErroRepositorioException;

	public Integer recuperarMatriculaImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public Integer validarMesAnoReferencia(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ErroRepositorioException;

	/**
	 * Atualiza os ids do faturamento situa��o tipo da tabela im�vel com o id do
	 * faturamento escolhido pelo usuario
	 * 
	 * [UC0156] Informar Situacao Especial de Faturamento
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void atualizarFaturamentoSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idFaturamentoTipo) throws ErroRepositorioException;

	/**
	 * Seta para null o id da cobran�a situa��o tipo da tabela im�vel
	 * 
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void retirarSituacaoEspecialFaturamento(Collection colecaoIdsImoveis)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisIds(Integer rotaId)
			throws ErroRepositorioException;

	public Object pesquisarImovelIdComConta(Integer imovelId,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Pesquisa o maior ano mes de referencia da tabela de faturamento grupo
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public Integer validarMesAnoReferenciaCobranca(
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ErroRepositorioException;

	/**
	 * Atualiza o id da cobran�a situa��o tipo da tabela im�vel com o id da
	 * situa��o escolhido pelo usuario
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */

	public void atualizarCobrancaSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idCobrancaTipo) throws ErroRepositorioException;

	/**
	 * Seta para null o id da cobran�a situa��o tipo da tabela im�vel
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void retirarSituacaoEspecialCobranca(Collection colecaoIdsImoveis)
			throws ErroRepositorioException;

	/**
	 * Obt�m o indicador de exist�ncia de hidr�metro para o im�vel, caso exista
	 * retorna 1(um) indicando SIM caso contr�rio retorna 2(dois) indicando N�O
	 * 
	 * [UC0307] Obter Indicador de Exist�ncia de Hidr�metro no Im�vel
	 * 
	 * @author Thiago Toscano
	 * @date 02/06/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */

	public Integer obterIndicadorExistenciaHidrometroImovel(Integer idImovel)
			throws ErroRepositorioException;

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
	 * @date 07/07/2006
	 * 
	 * @param imovel
	 * @param idLigacaoAguaSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLigacaoAgua(Imovel imovel,
			Integer idLigacaoAguaSituacao) throws ErroRepositorioException;

	/**
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * @author Rafael Corr�a
	 * @date 24/07/2006
	 * 
	 */
	public Collection gerarRelatorioImovelOutrosCriterios(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal) throws ErroRepositorioException;

	public Collection pesquisarSubcategoriasImovelRelatorio(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0164] Filtrar Imoveis por Outros Criterios
	 * 
	 * Filtra para saber a quantidade de imoveis antes de executar o filtro
	 * 
	 * @author Rafael Santos
	 * @date 01/08/2006
	 * 
	 */
	public Integer obterQuantidadaeRelacaoImoveisDebitos(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, Integer relatorio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal)
			throws ErroRepositorioException;

	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o para exibi��o.
	 * 
	 * acima no controlador ser� montada a inscri��o
	 */
	public Object[] pesquisarInscricaoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Esse m�todo � usado para fzazer uma pesquisa na tabela im�vel e confirmar
	 * se o id passado � de um im�vel exclu�do(idExclusao)
	 * 
	 * Fl�vio Cordeiro
	 */

	public Boolean confirmarImovelExcluido(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Gerar Relat�rio de Dados de Economias do Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelOutrosCriterios(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio) throws ErroRepositorioException;

	/**
	 * 
	 * Obtem os dados da Subcategoria do Imovel para o Relatorio de Dados
	 * Economias para o Imovel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelSubcategoria(
			String idImovel) throws ErroRepositorioException;

	/**
	 * 
	 * Obtem os dados do Imovel Economia do Imovel para o Relatorio de Dados
	 * Economias para o Imovel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelEconomia(
			String idImovel, String idSubCategoria)
			throws ErroRepositorioException;

	/**
	 * 
	 * Obtem os dados do Cliente Imovel Economia do Imovel para o Relatorio de
	 * Dados Economias para o Imovel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelClienteEconomia(
			String idImovelEconomia) throws ErroRepositorioException;

	/**
	 * 
	 * [UC164] Filtrar Imoveis Outros Criterios
	 * 
	 * Monta os inner join da query de acordo com os parametros informados
	 * 
	 * @author Rafael Santos
	 * @date 03/08/2006
	 * 
	 * @return
	 */
	public String montarInnerJoinFiltrarImoveisOutrosCriterios(

	String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String idCliente,
			String idClienteTipo, String idClienteRelacaoTipo, Integer relatorio, String cpfCnpj);

	/**
	 * Permite pesquisar entidade beneficente [UC0389] Inserir Autoriza��o para
	 * Doa��o Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param filtroEntidadeBeneficente -
	 *            Filtro com os valores para pesquisa
	 * @return Collection<EntidadeBeneficente> - Cole��o de entidade(s)
	 *         beneficente(s)
	 * @throws ErroRepositorioException
	 */
	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(
			FiltroEntidadeBeneficente filtroEntidadeBeneficente)
			throws ErroRepositorioException;

	/**
	 * Permite pesquisar im�vel doa��o [UC0389] Inserir Autoriza��o para Doa��o
	 * Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param filtroImoveldoacao -
	 *            Filtro com os valores para pesquisa
	 * @return Collection<ImovelDoacao> - Cole��o de im�vei(s) doa��o
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelDoacao> pesquisarImovelDoacao(
			FiltroImovelDoacao filtroImovelDoacao)
			throws ErroRepositorioException;

	/**
	 * Permite atualizar as informa��es referentes ao im�vel doa��o [UC0390]
	 * Manter Autoriza��o para Doa��o Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param imovelDoacao -
	 *            inst�ncia de im�vel doa��o que servir� de base para a
	 *            atuali��o
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao)
			throws ErroRepositorioException;

	/**
	 * Pesquisa um im�vel a partir do seu id.Retorna os dados que comp�em a
	 * inscri��o e o endere�o do mesmo
	 * 
	 * @author Raphael Rossiter
	 * @date 01/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelRegistroAtendimento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados Cadastrais do Imovel [UC0472] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelDadosCadastrais(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de clientes do imovel [UC0472] Consultar Cliente
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarClientesImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a cole��o de clientes do imovel pela maior data [UC0472] Consultar Cliente
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarClientesImovelDataMax(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a cole��o de clientes do imovel mesmo que o im�vel j� tenha sido exclu�do [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 * parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesImovelExcluidoOuNao(Integer idImovel) throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCategoriasImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0475] Consultar Perfil Imovel
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idImovel
	 * @return Perfil do Im�vel
	 * @exception ErroRepositorioException
	 */
	public ImovelPerfil obterImovelPerfil(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados Complementares do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelDadosComplementares(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de vencimento alternativos do imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarVencimentoAlternativoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de Debitos Automaticos do imovel [UC0473] Consultar
	 * Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarDebitosAutomaticosImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de Faturamento Situa��o Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarFaturamentosSituacaoHistorico(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de cobran�as Situa��o Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCobrancasSituacaoHistorico(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados de Analise da Medi��o e Consumo do Imovel [UC0473]
	 * Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelAnaliseMedicaoConsumo(Integer idImovel)
			throws ErroRepositorioException;

	// ----------Savio
	public void atualizarImovelLeituraAnormalidade(
			Map<Integer, MedicaoHistorico> mapAtualizarLeituraAnormalidadeImovel,Date dataAtual)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados do Historico de Faturamento [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelHistoricoFaturamento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar o cliente usu�rio do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public String consultarClienteUsuarioImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar as contas do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar as contas Historicos do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasHistoricosImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consultar os dados de parcelamentos do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 20/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarParcelamentosDebitosImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteUsuarioImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UCXXXX] Consultar Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 22/05/2009
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteUsuarioImovelExcluidoOuNao(Integer idImovel)
		throws ErroRepositorioException;

	/**
	 * Atualiza apenas os dados (numeroParcelamento,
	 * numeroParcelamentoConsecutivo, numeroReparcelamentoConsecutivo) do im�vel
	 * 
	 * @param imovel
	 *            parametros para a consulta
	 * 
	 * Author: Fernanda Paiva
	 * 
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */

	public void atualizarDadosImovel(Integer codigoImovel,
			Integer numeroParcelamento,
			Integer numeroReparcelamentoConsecutivo,
			Integer numeroReparcelamento) throws ErroRepositorioException;

	public Collection<Categoria> pesquisarCategoriasImovel(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLigacaoAguaEsgoto(Integer idImovel,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoAgua(Integer idImovel,
			Integer consumoMinimo,short indiacadorConsumoFixado, Integer idLigacaoAguaSituacaoAntigo) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoEsgoto(Integer idLigacaoEsgoto,
			Integer consumoMinimo,short indicadorVolumeFixado) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o 
	 * 
	 * @date 20/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoTarifa(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Filtrar o Imovel pelos parametros informados
	 *
	 * @author Rafael Santos
	 * @date 24/11/2006
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovel(
			String idImovel, 
			String idLocalidade,
		    String codigoSetorComercial,
		    String numeroQuadra,
		    String lote,
		    String subLote,
		    String codigoCliente,
		    String idMunicipio,
		    String cep,
		    String idBairro,
		    String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
		    boolean pesquisarImovelManterVinculo,boolean pesquisarImovelCondominio, Integer numeroPagina) throws ErroRepositorioException; 

	/**
	 * 
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis
	 *
	 * @author Rafael Santos
	 * @date 24/11/2006
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarQuantidadeImovel(
			String idImovel, 
			String idLocalidade,
		    String codigoSetorComercial,
		    String numeroQuadra,
		    String lote,
		    String subLote,
		    String codigoCliente,
		    String idMunicipio,
		    String cep,
		    String idBairro,
		    String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
		    boolean pesquisarImovelManterVinculo, boolean pesquisarImovelCondominio) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa o Imovel pelos parametros informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelInscricao(
			String idImovel,
			String idLocalidade,
		    String codigoSetorComercial,
		    String numeroQuadra,
		    String lote,
		    String subLote,
		    String codigoCliente,
		    String idMunicipio,
		    String cep,
		    String idBairro,
		    String idLogradouro, boolean pesquisarImovelCondominio, Integer numeroPagina) throws ErroRepositorioException; 

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da liga��o de esgoto
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisaridLigacaoEsgotoSituacao(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da ligacao de agua
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisaridLigacaoAguaSituacao(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da ligacao de agua
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarTarifaImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa os im�veis do cliente de acordo com o tipo de rela��o
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @author Leonardo Vieira
	 * @created 12/12/2006
	 * 
	 * @param cliente
	 * @param relacaoClienteImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisClientesRelacao(Cliente cliente,
			ClienteRelacaoTipo relacaoClienteImovel,Integer numeroInicial)
			throws ErroRepositorioException;
	
	
	public Integer pesquisarExistenciaImovelSubCategoria(Integer idImovel, Integer idCategoria) throws ErroRepositorioException;
	
	public Collection pesquisarImoveisPorRota(Integer idRota) throws ErroRepositorioException;
	
	
	/**
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Im�veis
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @created 20/12/2006
	 * 
	 * @param dLocalidadeInicial,
	 *            idLocalidadeFinal, codigoSetorComercialInicial,
	 *            codigoSetorComercialFinal
	 * @param quadraInicial,
	 *            quadraFinal, String loteInicial, loteFinal, subLoteInicial,
	 *            subLoteFinal, idTarifaAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarImoveisTarifaConsumo(String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String quadraInicial,
			String quadraFinal, String loteInicial, String loteFinal,
			String subLoteInicial, String subLoteFinal, String idTarifaAnterior)
			throws ErroRepositorioException;

	
	
	
	/**
	 * Atualiza a tarifa de consumo de um ou mais imoveis
	 *  
	 * [UC0378] Associar Tarifa de Consumo a Im�veis 
	 * 
	 * @author R�mulo Aurelio
	 * @created 19/12/2006
	 * 
	 * @param matricula,
	 *            tarifaAtual, colecaoImoveis
	 * @return
	 * @throws ErroRepositorioException
	 */

	public void atualizarImoveisTarifaConsumo(String matricula,
			String tarifaAtual, Collection colecaoImoveis)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Atualiza o perfil do im�vel para o perfil normal
	 * 
	 * @date 04/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelPerfilNormal(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0490] - Informar Situa��o de Cobran�a
	 * 
	 * Pesquisa o im�vel com a situa��o da liga��o de �gua e a de esgoto
	 * 
	 * @date 13/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovelComSituacaoAguaEsgoto(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisar imoveis por rota, situacao de ligacao de agua e situacao de ligacao
	 * de esgoto, utilizando paginacao
	 * 
	 * Utilizado no  
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @param idRota
	 * @param idsSituacaoLigacaoAgua
	 * @param idsSituacaoLigacaoEsgoto
	 * @param numeroInicial
	 * @return 
	 * @throws ControladorException
	 * 
	 * @author Breno Santos / Rodrigo Cabral
	 * @date 21/10/2009 / 14/05/2014
	 * 
	 */	
	public Collection pesquisarImoveisPorRotaComPaginacaoSemRotaAlternativa(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	
	/**
	 * Pesquisar imoveis por rota, situacao de ligacao de agua e situacao de ligacao
	 * de esgoto, utilizando paginacao
	 * 
	 * Utilizado no  
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @param idRota
	 * @param idsSituacaoLigacaoAgua
	 * @param idsSituacaoLigacaoEsgoto
	 * @param numeroInicial
	 * @return 
	 * @throws ControladorException
	 * 
	 * @author Breno Santos / Rodrigo Cabral
	 * @date 21/10/2009 / 14/05/2014
	 * 
	 */	
	public Collection pesquisarImoveisPorRotaComPaginacaoComRotaAlternativa(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
/**
     * 
     * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
     * Matricula e Endere�o
     * 
     * @author Rafael Santos
     * @date 27/11/2006
     * 
     * @param idImovel
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarImovelInscricaoNew(String idImovel,
            String idLocalidade, String codigoSetorComercial,
            String numeroQuadra, String lote, String subLote,
            String codigoCliente, String idMunicipio, String cep,
            String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,  
            boolean pesquisarImovelCondominio, Integer numeroPagina)
            throws ErroRepositorioException ;
	
	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0006] - Verificar n�mero de IPTU
	 * 
	 * Verifica se j� existe outro im�vel ou economia com o mesmo n�mero de IPTU
	 * no mesmo munic�pio
	 * 
	 * @date 13/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public Integer verificarNumeroIptu(BigDecimal numeroIptu, Integer idImovel,
			Integer idImovelEconomia, Integer idMunicipio)
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0007] - Verificar n�mero de contrato da companhia de energia el�trica
	 * 
	 * Verifica se j� existe outro im�vel ou economia com o mesmo n�mero de
	 * contrato da companhia el�trica
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public Integer verificarNumeroCompanhiaEletrica(Long numeroCompanhiaEletrica, Integer idImovel,
			Integer idImovelEconomia)
			throws ErroRepositorioException;
	
	/**
	 * Obt�m o quantidade de economias da subCategoria por imovel
	 * 
	 * @param idImovel 		O identificador do im�vel
	 * @return 				Cole��o de SubCategorias
	 * @exception ErroRepositorioException Descri��o da exce��o
	 */
	public Collection obterQuantidadeEconomiasSubCategoria(Integer idImovel)
			throws ErroRepositorioException ;
	
	
	/**
	 * Obt�m o quantidade de economias da subCategoria por imovel
	 * @autor R�mulo Aur�lio 
	 * @param idImovel 		O identificador do im�vel
	 * @return 				Cole��o de imovelSubCategorias
	 * @exception ErroRepositorioException Descri��o da exce��o
	 * @date 08/02/2007
	 */
	
	
	public Collection pesquisarImovelSubcategorias(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * @date 21/02/2007
	 * @author Vivianne Sousa
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovel(Integer idImovel)
		throws ErroRepositorioException;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais im�veis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
	/**
	 * Atualiza logradouroCep de um ou mais im�veis  
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
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
	/**
	 * [UC0302] Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade
	 *
	 * Pequisa o identificador de cobranca de acr�scimo pro impontualidade 
	 * para o im�vel do cliente respons�vel.
	 *
	 * @author Pedro Alexandre
	 * @date 26/02/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterIndicadorGeracaoAcrescimosClienteImovel(Integer idImovel)	throws ErroRepositorioException ;
	
//	public Integer verificarExistenciaImovelParaCliente(Integer idImovel)
//	throws ErroRepositorioException;
	
	
	
	/**  
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Obter o consumo m�dio como n�o medido para o im�vel passado
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2007
	 */
	public Integer obterConsumoMedioNaoMedidoImovel(Imovel imovel)
			throws ErroRepositorioException ;
	
	
	/**
	 * Filtra o Pagamento pelo seu id carregando os dados necess�rios
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author K�ssia Albuquerque
	 * @date 12/07/2007
	 * 
	 * @param idPagamentoHistorico
	 * @return Collection<PagamentoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento) throws ErroRepositorioException ;
	
	/**
	 * Filtra o Pagamento Historico pelo seu id carregando os dados necess�rios
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author K�ssia Albuquerque
	 * @date 12/07/2007
	 * 
	 * @param idPagamentoHistorico
	 * @return Collection<PagamentoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(Integer idPagamentoHistorico) throws ErroRepositorioException;
	
	/**  
	 * Obter a situa��o de cobran�a para o im�vel passado
	 * 
	 * @author Vivianne Sousa
	 * @date 07/03/2007
	 */
	public String obterSituacaoCobrancaImovel(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * Pesquisa uma cole��o de im�veis
	 * 
	 * @author Ana Maria
	 * @date 16/03/2007
	 */
	public Collection pesquisarColecaoImovel(FiltrarImovelInserirManterContaHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa uma cole��o de im�veis com perfil bloqueado
	 * 
	 * @return Boolean
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarColecaoImovelPerfilBloqueado(FiltrarImovelInserirManterContaHelper filtro)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa uma cole��o de im�veis do cliente
	 * 	
	 * @author Ana Maria
	 * @date 20/03/2007
	 */
	public Collection pesquisarColecaoImovelCliente(Integer codigoCliente, Integer relacaoTipo, Boolean verificarImovelPerfilBloqueado, 
			Integer idGrupoFaturamento, FiltrarImovelInserirManterContaHelper filtro) 
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa quantidade de im�veis do cliente com perfil bloqueado
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 */
	public Integer pesquisarColecaoImovelClienteBloqueadoPerfil(Integer codigoCliente,
			Integer relacaoTipo) throws ErroRepositorioException;
	
	public Integer pesquisarContaMotivoRevisao(Integer idImovel)
		    throws ErroRepositorioException;	
	
	/**  
	 * [UC0150] - Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 16/04/2007
	 */
	public Collection obterQuantidadeEconomiasCategoriaPorSubcategoria(Integer conta)
	throws ErroRepositorioException ;
	
	/**
	 * Obtem as subcategorias de uma determinada categoria
	 * @param id Id da categoria a ser pesquisada
	 * @return Colecao de subcategorias
	 * @throws ErroRepositorioException
	 */
	public Collection obterSubCategoriasPorCategoria( Integer idCategoria, Integer idImovel ) 
	  		throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os ids do perfil do im�vel.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsPerfilImovel() throws ErroRepositorioException ;
	
	/**
	 * Consutlar o cliente usu�rio do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Bruno Barros
	 * @date 27/04/2007
	 * 
	 * @param imovel
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente consultarClienteUsuarioImovel( Imovel imovel )
			throws ErroRepositorioException;
	
	/**  
	 * [UC0591] - Gerar Relat�rio de Clientes Especiais
	 * 
	 * Pesquisas os im�veis de acordo com os par�metros da pesquisa
	 * 
	 * @author Rafael Corr�a
	 * @date 31/05/2007
	 */
	public Collection pesquisarImovelClientesEspeciaisRelatorio(
			String idUnidadeNegocio, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorInicial, String codigoSetorFinal,
			String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria,
			String[] idsSubcategoria, String idSituacaoAgua,
			String idSituacaoEsgoto, String qtdeEconomiasInicial,
			String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,
			String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial,
			String intervaloConsumoEsgotoFinal, String idClienteResponsavel,
			String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal,
			Date dataInstalacaoHidrometroInicial,
			Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,
			Integer anoMesFaturamento, String idLeituraAnormalidade,
			String leituraAnormalidade, String idConsumoAnormalidade,
			String consumoAnormalidade)
		throws ErroRepositorioException;
	

	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situa��o da liga��o de esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoEsgotoSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgotoSituacao pesquisarLigacaoEsgotoSituacao(Integer idImovel)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situa��o da liga��o de agua
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoAguaSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoAguaSituacao pesquisarLigacaoAguaSituacao(Integer idImovel)
			throws ErroRepositorioException ;

	
	public FaturamentoGrupo pesquisarGrupoImovel(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * Obt�m a descri��o de uma categoria
	 *
	 * @author Rafael Corr�a
	 * @date 04/06/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterDescricaoSubcategoria(Integer idSubcategoria)
			throws ErroRepositorioException;
	
 	/**
 	 * 
 	 * Gerar Relat�rio de Im�vel Outros Crit�rios
 	 * 
 	 * @author Rafael Corr�a,Rafael Santos
 	 * @date 24/07/2006,01/08/2006
 	 * 
 	 */
 	public Collection gerarRelatorioImovelEnderecoOutrosCriterios(
 			String idImovelCondominio, String idImovelPrincipal,
 			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
 			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
 			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
 			String intervaloValorPercentualEsgotoInicial,
 			String intervaloValorPercentualEsgotoFinal,
 			String intervaloMediaMinimaImovelInicial,
 			String intervaloMediaMinimaImovelFinal,
 			String intervaloMediaMinimaHidrometroInicial,
 			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
 			String idPocoTipo, String idFaturamentoSituacaoTipo,
 			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
 			String idEloAnormalidade, String areaConstruidaInicial,
 			String areaConstruidaFinal, String idCadastroOcorrencia,
 			String idConsumoTarifa, String idGerenciaRegional,
 			String idLocalidadeInicial, String idLocalidadeFinal,
 			String setorComercialInicial, String setorComercialFinal,
 			String quadraInicial, String quadraFinal, String loteOrigem,
 			String loteDestno, String cep, String logradouro, String bairro,
 			String municipio, String idTipoMedicao, String indicadorMedicao,
 			String idSubCategoria, String idCategoria,
 			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
 			String diaVencimento, String idCliente, String idClienteTipo,
 			String idClienteRelacaoTipo, String numeroPontosInicial,
 			String numeroPontosFinal, String numeroMoradoresInicial,
 			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
 			String idUnidadeNegocio, String seqRotaInicial, String seqRotaFinal,
 			String rotaInicial, String rotaFinal, 
 			String ordenacaoRelatorio) throws ErroRepositorioException;	

 	
	/**
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * @author Rafael Corr�a,Rafael Santos
	 * @date 24/07/2006,01/08/2006
	 * 
	 */
	public Collection gerarRelatorioCadastroConsumidoreInscricao(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal, String ordenacao, String[] pocoTipoListIds,
			String indicadorCpfCnpj, String cpfCnpj) throws ErroRepositorioException;
 	
	
	/**
	 * Obt�m a o nome do cliente
	 *
	 * @author Kassia Albuquerque
	 * @date 05/07/2007
	 *
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDescricaoIdCliente(Integer idImovel)
			throws ErroRepositorioException;
 	
	
	/**
	 * Obt�m a o nome do arrecadador
	 *
	 * @author Kassia Albuquerque
	 * @date 09/07/2007
	 *
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarNomeAgenteArrecadador(Integer idPagamentoHistorico)
			throws ErroRepositorioException;
	
	/**
	 * Obt�m a o 117� caracter de uma String
	 *
	 * @author Kassia Albuquerque
	 * @date 20/07/2007
	 *
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarCaracterRetorno(Integer idConteudoRegistro) throws ErroRepositorioException;
	
	/**
	 * [UC0623] - GERAR RESUMO DE METAS EXECUTDO NO M�S(CAERN)
	 * 
	 * @author S�vio Luiz
	 * @date 08/08/2007
	 */
	public Object[] obterSubCategoriasComCodigoGrupoPorCategoria(
			Integer idCategoria, Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o para exibi��o.
	 * 
	 * acima no controlador ser� montada a inscri��o
	 */
	public Object[] pesquisarLocalidadeSetorImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author S�vio Luiz
	 * @date 24/08/2007
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarSequencialRota(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Retorna o cep do im�vel
	 * 
	 * @param imovel
	 * 
	 * @return Descri��o do retorno
	 * 
	 * @exception ErroRepositorioException
	 * 
	 */
	public Cep pesquisarCepImovel(Imovel imovel)
			throws ErroRepositorioException;

	
	/**
	 * Gerar Boletim de Cadastro
	 * 
	 * @date 20/08/2007
	 */
	public Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> pesquisarBoletimCadastro(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, int quantidadeCobrancaDocumentoInicio, 
			String indicadorCpfCnpj, String cpfCnpj) throws ErroRepositorioException;
	/**
	 * @author Vivianne Sousa
	 * @date 19/09/2007
	 * 
	 * @return ImovelCobrancaSituacao
	 * @throws ErroRepositorioException
	 */

	public CobrancaSituacao pesquisarImovelCobrancaSituacao(Integer idImovel) throws ErroRepositorioException;
    
    /**
     * [UC0541] Emitir 2 Via de Conta Internet
     * 
     * @author Vivianne Sousa
     * @date 02/09/2007
     * 
     * @throws ErroRepositorioException
     */

    public Imovel pesquisarDadosImovel(Integer idImovel) throws ErroRepositorioException;
    
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarInscricaoImoveleRota(String idsImovel)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa os im�veis do cliente de acordo com o tipo de rela��o
	 * 
	 * 
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * 
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
	 * 
	 * @created 23/11/2007
	 * 
	 * 
	 * 
	 * @param cliente
	 * 
	 * @param relacaoClienteImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImoveisClientesRelacao(Collection idsCliente,Integer numeroInicial)

	throws ErroRepositorioException;
	
	/**
	 * 
	 * Buscar Empresa apatir da Matr�cula de um Im�vel .
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 21/01/2008
	 * 
	 * @param dados
	 * 
	 * @throws ControladorException
	 */
	public Empresa buscarEmpresaPorMatriculaImovel(Integer imovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Atualiza a situa��o de cobran�a do im�vel
	 * 
	 */

	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobranca, Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis.
	 * Utilizado para corrigir o erro da listagem de Imoveis: o metodo pesquisarQuantidadeImovel nao
	 * traz a mesma quantidade de imovel do metodo pesquisarImovelInscricaoNew.  
	 * 
	 * @author Ivan S�rgio
	 * @date 11/03/2008
	 * 
	 * @param idImovel
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param lote
	 * @param subLote
	 * @param codigoCliente
	 * @param idMunicipio
	 * @param cep
	 * @param idBairro
	 * @param idLogradouro
	 * @param pesquisarImovelCondominio
	 * @param numeroPagina
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarQuantidadeImovelInscricao(
		String idImovel, String idLocalidade, String codigoSetorComercial,
		String numeroQuadra, String lote, String subLote,
		String codigoCliente, String idMunicipio, String cep,
		String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal, 
		boolean pesquisarImovelCondominio) throws ErroRepositorioException;
	
	/**
	 * 
	 * recupera os ImovelSubcategoria, com 5 resultados, ordenados por Qt economias desc
	 *
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarImovelSubcategoriasMaxCinco(Integer idImovel)

	throws ErroRepositorioException;
	
	
	/**
	 * [UC0800] - Obter Consumo N�o Medido
	 *
	 * [FS0001] - Verificar �rea N�o Informada 
	 *
	 * @author Raphael Rossiter
	 * @date 22/05/2008
	 *
	 * @param idImovel
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarAreaConstruida(Integer idImovel) throws ErroRepositorioException ;
	
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarInscricaoImoveleRota(Collection colecaoIdsImovel)
		throws ErroRepositorioException;
	
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */
	public Collection pesquisarInscricaoImoveleRota(FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper, Integer anoMes)
		throws ErroRepositorioException;
    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilDiferenteCorporativo() throws ErroRepositorioException;

	/**
	 * M�todo para obter o id da esfera de poder de um imovel
	 * @param idImovel
	 * @return
	 * 
	 * @author Francisco do Nascimento
	 * @date 22-05-2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdEsferaPoder(Integer idImovel)
		throws ErroRepositorioException;

	/**
	 * Consulta o ID da categoria principal, considerando que a categoria principal � a quem tem
	 * maior soma de qtd de economias em suas subcategorias e tenha o 
	 * idCategoria menor (ou maior caso exija ordemDecrescente seja true)
	 * 
	 *  @author Francisco do Nascimento
	 *  @date 22/05/2008
	 *  
	 */
	public Integer obterIdCategoriaPrincipal(Integer idImovel, boolean ordemDecrescente)
		throws ErroRepositorioException;
	
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilTarifaSocialDiferenteCorporativo() throws ErroRepositorioException;
    
    
    
    
    /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
    public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise()throws ErroRepositorioException;
    
    
    /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	public Collection pesquisarImoveisComSituacaoLigadoEmAnalise(Integer idLocalidade)throws ErroRepositorioException;
	
	   /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	
	public void atualizarSituacaoAguaPorImovel(String id, String situacaoAguaLigado) throws ErroRepositorioException;
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que n�o estejam com a situa��o
	 * atual igual a "PR�-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelIdComContaNaoPreFaturada(Integer imovelId,
	Integer anoMesReferencia) throws ErroRepositorioException ;
	
	/**
	 * 
	 * Consultar Perfil Quadra
	 * 
	 * @param idImovel
	 * 
	 * @return Perfil da Quadra
	 * 
	 * @exception ErroRepositorioException
	 * 
	 */

	public Integer obterQuadraPerfil(Integer idImovel)
		 throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral) throws ErroRepositorioException;
	
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa, Integer idParametroTabelaAtualizacaoCadastro) throws ErroRepositorioException;

	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 26/09/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaImovelAtualizacaoCadastral(Integer idImovel, Integer idParametro) throws ErroRepositorioException;

	/**
	 * Informar Economia
	 * 
	 * @author Vivianne Sousa
	 * @date 23/10/2008
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel, Integer idSubcategoria) 
			throws ErroRepositorioException;
	
    /**
     * Pesquisar Im�vel Atualiza��o Cadastral(Dados da Inscri��o)
     * 
     * @author Ana Maria
     * @date 17/09/2008
     * 
     * @throws ErroRepositorioException
     */
    public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastralInscricao(Integer idImovel, Integer idEmpresa) 
    	throws ErroRepositorioException;
    
	/**
	 * Consultar Im�veis Atualiza��o Cadastral por Quadra
	 * 
	 * @param idSetorComercial
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param idEmpresa
	 * @return Collection<Imovel> - Cole��o de im�veis.
	 * 
	 * @author Ana Maria
     * @date 18/09/2008
	 * @exception ErroRepositorioException
	 */
	public Collection obterImoveisAtualizacaoCadastral(Integer idLocalidade, String codigoSetor,
			Integer quadraInicial, Integer quadraFinal, Integer idEmpresa, Integer idRota)throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Im�vel Atualiza��o Cadastral
	 * 
	 * @param idImovel
	 * @return Imovel
	 * 
	 * @author Ana Maria
     * @date 17/09/2008
	 * @exception ErroRepositorioException
	 */
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisar Im�vel Subcategoria Atualiza��o Cadastral
	 * 
	 * @param idImovel
	 * 
	 * @author Ana Maria
     * @date 17/09/2008
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisar exist�ncia de im�vel economia
	 * 
	 * @author Ana Maria
	 * @date 05/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean pesquisarExistenciaImovelEconomia(Integer idImovel, Integer idSubcategoria) 
			throws ErroRepositorioException;
	/**
	 * Validar a(s) categoria(s) do imovel com a(s) respectivas tarifas 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 19/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarExistenciaTarifaConsumoCategoriaParaCategoriaImovel(Integer idImovel, Integer idCategoria)
	throws ErroRepositorioException ;
	
	/**
	 * Remover Im�vel Subcategoria
	 *  
	 * @author Ana Maria
	 * @date 10/02/2009
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void removerImovelSubcategoria(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * Remover Im�vel Ramo Atividade
	 *  
	 * @author Jos� Guilherme
	 * @date 02/10/2009
	 * 
	 * @param Integer idImovel
	 * @throws ErroRepositorioException
	 */
	public void removerImovelRamoAtividade(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * @author Victor Cisneiros
	 * @date 18/02/2009
	 */
    public Integer pesquisarMaiorNumeroLoteDaQuadra(Integer idQuadra) throws ErroRepositorioException;
    
    /**
	 * Consultar os dodos cliente usu�rio do Imovel 
	 * 
	 * @author Arthur Carvalho
	 * @date 12/03/2009
	 */
    public Object[] consultarDadosClienteUsuarioImovel(
			String idImovel) throws ErroRepositorioException ;
    
    /**
	 * 
	 * 
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * 
	 * 
	 * @author Rafael Corr�a,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Integer gerarRelatorioCadastroConsumidoresInscricaoCount(String idImovelCondominio, 
											String idImovelPrincipal,
											String idSituacaoLigacaoAgua, 
											String consumoMinimoInicialAgua,
											String consumoMinimoFinalAgua, 
											String idSituacaoLigacaoEsgoto,
											String consumoMinimoInicialEsgoto, 
											String consumoMinimoFinalEsgoto,
											String intervaloValorPercentualEsgotoInicial,
											String intervaloValorPercentualEsgotoFinal,
											String intervaloMediaMinimaImovelInicial,
											String intervaloMediaMinimaImovelFinal,
											String intervaloMediaMinimaHidrometroInicial,
											String intervaloMediaMinimaHidrometroFinal, 
											String idImovelPerfil,
											String idPocoTipo, 
											String idFaturamentoSituacaoTipo,
											String idCobrancaSituacaoTipo, 
											String idSituacaoEspecialCobranca,
											String idEloAnormalidade, 
											String areaConstruidaInicial,
											String areaConstruidaFinal, 
											String idCadastroOcorrencia,
											String idConsumoTarifa, 
											String idGerenciaRegional,
											String idLocalidadeInicial, 
											String idLocalidadeFinal,
											String setorComercialInicial, 
											String setorComercialFinal,
											String quadraInicial, 
											String quadraFinal, 
											String loteOrigem,
											String loteDestno, 
											String cep, 
											String logradouro, 
											String bairro,
											String municipio, 
											String idTipoMedicao, 
											String indicadorMedicao,
											String idSubCategoria, 
											String idCategoria,
											String quantidadeEconomiasInicial, 
											String quantidadeEconomiasFinal,
											String diaVencimento, 
											String idCliente, 
											String idClienteTipo,
											String idClienteRelacaoTipo, 
											String numeroPontosInicial,
											String numeroPontosFinal, 
											String numeroMoradoresInicial,
											String numeroMoradoresFinal, 
											String idAreaConstruidaFaixa,
											String idUnidadeNegocio, 
											String cdRotaInicial,
											String cdRotaFinal, 
											String sequencialRotaInicial,
											String sequencialRotaFinal,
											String[] pocoTipoListIds,
											String indicadorCpfCnpj,
											String cpfCnpj
											) throws ErroRepositorioException; 
	
	
	/**
	 * 
	 * 
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * 
	 * 
	 * @author Rafael Corr�a,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Integer gerarRelatorioImovelEnderecoOutrosCriteriosCount(
							String idImovelCondominio, 
							String idImovelPrincipal,
							String idSituacaoLigacaoAgua, 
							String consumoMinimoInicialAgua,
							String consumoMinimoFinalAgua, 
							String idSituacaoLigacaoEsgoto,
							String consumoMinimoInicialEsgoto, 
							String consumoMinimoFinalEsgoto,
							String intervaloValorPercentualEsgotoInicial,
							String intervaloValorPercentualEsgotoFinal,
							String intervaloMediaMinimaImovelInicial,
							String intervaloMediaMinimaImovelFinal,
							String intervaloMediaMinimaHidrometroInicial,
							String intervaloMediaMinimaHidrometroFinal, 
							String idImovelPerfil,
							String idPocoTipo, 
							String idFaturamentoSituacaoTipo,
							String idCobrancaSituacaoTipo, 
							String idSituacaoEspecialCobranca,
							String idEloAnormalidade, 
							String areaConstruidaInicial,
							String areaConstruidaFinal, 
							String idCadastroOcorrencia,
							String idConsumoTarifa, 
							String idGerenciaRegional,
							String idLocalidadeInicial, 
							String idLocalidadeFinal,
							String setorComercialInicial, 
							String setorComercialFinal,
							String quadraInicial, 
							String quadraFinal, 
							String loteOrigem,
							String loteDestno, 
							String cep, 
							String logradouro, 
							String bairro,
							String municipio, 
							String idTipoMedicao, 
							String indicadorMedicao,
							String idSubCategoria, 
							String idCategoria,
							String quantidadeEconomiasInicial, 
							String quantidadeEconomiasFinal,
							String diaVencimento, 
							String idCliente, 
							String idClienteTipo,
							String idClienteRelacaoTipo, 
							String numeroPontosInicial,
							String numeroPontosFinal, 
							String numeroMoradoresInicial,
							String numeroMoradoresFinal, 
							String idAreaConstruidaFaixa,
							String idUnidadeNegocio,
							String rotaInicial, 
							String rotaFinal,
							String ordenacaoRelatorio,
							String seqRotaInicial, 
							String seqRotaFinal) throws ErroRepositorioException;
    
	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o para exibi��o, independente do
	 * im�vel ter sido exclu�do ou n�o.
	 * 
	 * acima no controlador ser� montada a inscri��o
	 */
	public Object[] pesquisarInscricaoImovelExcluidoOuNao(Integer idImovel)
		throws ErroRepositorioException;
	
    /**
	 * 
	 * Atualiza a situa��o de cobran�a e a situa��o do tipo de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/02/2009
	 */

	public void atualizarSituacaoCobrancaETipoIdsImoveis(Integer idSituacaoCobranca, Collection<Integer> idsImovel)
		throws ErroRepositorioException; 
	/**
	 * Verificar se o im�vel perfil est� bloqueado
	 * 
	 * @author Ana Maria
	 * @date 22/04/2009
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarImovelPerfilBloqueado(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * Verificar se a ultima alteracao do im�vel 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 27/05/2009
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaAlteracaoImovel(Integer idImovel) 
			throws ErroRepositorioException ;

	/**
	 * Verifica se existe imovel com n�mero do Medidor de Energia informado
	 * 
	 * @author R�mulo Aur�lio
	 * @date 08/06/2009
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelComNumeroMedidorEnergiaImovel(String numeroMedidorEnergia) 
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa a cole��o de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
	 * 
	 * @since 07/09/2006
	 * 
	 * 
	 * 
	 * @param filtroClienteImovel
	 * 
	 * parametros para a consulta
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarCategoriaSubcategoriaImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa qual foi o consumo faturado do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 18/12/2009
	 * @return consumoFaturadoMes
	 * @throws ControladorException
	 */
	public Integer obterConsumoFaturadoImovelNoMes(Integer idImovel,
			Integer mesAnoReferencia) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa o perfil do imovel do imovel informado
	 * 
	 * @author R�mulo Aur�lio
	 * @date 03/03/2010
	 * @throws ControladorException
	 */
	public ImovelPerfil recuperaPerfilImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que n�o estejam com a situa��o
	 * atual igual a "PR�-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelCondominioIdComContaNaoPreFaturada(Integer imovelId,

	Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
     * 
     * M�todo criado para atualizar apenas os campos necess�rios para
     * imovel.
     * 	 
     * @author Bruno Barros
     * @date 31/03/2010
     * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLeituraAnormalidadeProcessoMOBILE( Imovel imovel ) throws ErroRepositorioException;
	
	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	public void atualizarDataRetiradaImovelSituacaoCobranca(
			Integer idImovelSituacaoCobranca, Date dataRetirada) throws ErroRepositorioException;
	
	/**
	 * [UC0672] - Registrar Movimento de Retorno dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 12/03/2010
	 */
	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobrancaNova, 
			Integer idSituacaoCobrancaBanco, Integer idImovel)	throws ErroRepositorioException;

	/** 
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Atualizar N�mero Medidor de Energia do Im�vel.
	 * 
	 * @author Hugo Leonardo
	 * @date 15/03/2010
	 *
	 */
	public void atualizarNumeroMedidorEnergiaImovel(Integer matricula, String medidorEnergia)
		throws ErroRepositorioException; 
	
	/**  
	 * [UC0591] - Gerar Relat�rio de Clientes Especiais
	 * 
	 * 					Count
	 * 
	 * @author Hugo Amorim
	 * @date 11/05/2010
	 */
	public Integer pesquisarImovelClientesEspeciaisRelatorioCount(
			String idUnidadeNegocio, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,			
			String codigoSetorInicial, String codigoSetorFinal,			
			String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria,
			String[] idsSubcategoria, String idSituacaoAgua,
			String idSituacaoEsgoto, String qtdeEconomiasInicial,
			String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,
			String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial,
			String intervaloConsumoEsgotoFinal, String idClienteResponsavel,
			String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal,
			Date dataInstalacaoHidrometroInicial,
			Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,
			Integer anoMesFaturamento, String idLeituraAnormalidade,
			String leituraAnormalidade, String idConsumoAnormalidade,
			String consumoAnormalidade) throws ErroRepositorioException;

	/**
	 * @author Vivianne Sousa
	 * @date 11/05/2010
	 */
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(
			String[] idsImovelCobrancaSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0490] Informar Situa��o de Cobran�a
	 * [SB0004] � Selecionar Situa��es de Cobran�a
	 * 
	 * seleciona as situa��es de cobran�a 
	 * (a partir da tabela COBRAN�A_SITUACAO com CBST_ICUSO=1 
	 * e CBST_ICBLOQUEIOINCLUSAO=2)retirando as ocorr�ncias 
	 * com CBST_ID=CBST_ID da tabela IMOVEL_COBRANCA_SITUACAO 
	 * para IMOV_ID=Id do im�vel recebido e 
	 * ISCB_DTRETIRADACOBRANCA com valor igual a nulo
	 * 
	 * @author Vivianne Sousa
	 * @date 12/05/2010
	 */
	public Collection pesquisarCobrancaSituacao(
			Integer idImovel, boolean temPermissaoEspecial) throws ErroRepositorioException ;
	
	
	/**
	 * 
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * 
	 * id localidade e codigo do setor
	 * 
	 * @author Hugo Amorim
	 * @date 01/06/2010
	 */
	public Object[] pesquisarLocalidadeCodigoSetorImovel(Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * Inserir e Atualizar Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2010
	 *
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarClienteImovelUsuario(Integer idImovel) throws ErroRepositorioException;
	
	/**
 	 * @author R�mulo Aur�lio
	 * @date 23/06/2010
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisarDebitoTipoImovelDoacao(			
			Integer idImovel) throws ErroRepositorioException;
	
	
	/**
 	 * @author Daniel Alves
	 * @date 20/07/2010
	 * @param idImovelPerfil
	 * @return ImovelPerfil
	 * @throws ErroRepositorioException
	 */
	public ImovelPerfil  pesquisarImovelPerfil(
			Integer idImovelPerfil) throws ErroRepositorioException;
	
	/**
	 * @author Daniel Alves
	 * @date 28/07/2010
	 * @param idImovel
	 * @return Colecao de imovelSubcategoriaHelper
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaHelper> consultaSubcategorias(int idImovel)
		throws ErroRepositorioException;
	

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param idQuadraAnterior
	 * @param idQuadraAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo[] verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idQuadraAnterior, Integer idQuadraAtual)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @param faturamentoGrupoOrigem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificaGeracaoDadosLeituraGrupoFaturamento(FaturamentoGrupo faturamentoGrupo) 
		throws ErroRepositorioException;
	
	/**
	 * 	@author Hugo Leonardo
	 *  @date 21/09/2010
	 *  
	 * 	UC_0009 - Manter Cliente
	 *  	[FS0008] ? Verificar permiss�o especial para cliente de im�vel p�blico
	 *  
	 * 	Verifica se o Cliente possui algum im�vel, cujo o tipo da categoria 
	 *  em subcategoria seja igual a PUBLICO.
	 *  
	 * 	Caso o cliente possua algum im�vel, retornar a quantidade de imoveis nesta situa��o
	 * 	Caso contr�rio retorna zero. 
	 * 
	 *  @param idCliente
	 *  @return Integer
	 *  
	 *  @throws RepositorioException
	 */
	public Integer obterQuantidadeImoveisPublicosAssociadoAoCliente(Integer idCliente) 
		throws ErroRepositorioException;

	/**
	 * [UC0074] Alterar Inscri��o de Im�vel
	 * [FS0010] � Verificar Duplicidade de Inscri��o
	 * @author Arthur Carvalho
	 * @date 19/09/2010
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelInscricaoAlterada verificarDuplicidadeImovelInscricaoAlterada(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idQuadraFace, Integer subLote, Integer lote) throws ErroRepositorioException;
	
	/**
	 * [UC0995] Emitir Declara��o Transfer�ncia de D�bitos/Cr�ditos
	 * @author Daniel Alves
	 * @date 23/09/2010
	 * @return Municipio
	 */
	public Municipio pesquisarMunicipioImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0630] Solicitar Emiss�o do Extrato de D�bitos
	 * 
	 * [SB0001] � Calcular valor dos descontos pagamento � vista.
	 * 
	 * @author Vivianne Sousa
	 * @date 21/10/2010
	 * 
	 * @throws ControladorException
	 */
	public Short consultarNumeroReparcelamentoConsecutivosImovel(Integer idImovel)
		throws ErroRepositorioException;

	/**
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * @author Mariana Victor
	 * @date 07/02/2011
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public Collection<Imovel> consultarImovelLocalidade(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * @author Mariana Victor
	 * @date 07/02/2011
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public void atualizarImovelPerfil(Integer idImovel, Integer idPerfil)
			throws ErroRepositorioException;
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovel(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovelCadastro(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer anoMesPesquisaInicial,Integer anoMesPesquisaFinal)throws ErroRepositorioException;
	

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocialDadoEconomia(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialCarta pesquisarTarifaSocialCarta(Integer idImovel,Integer codigoTipoCarta) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQuantidadeImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException;


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta, Integer qtdeImoveis)
			throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal pesquisarValorContaTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao,Integer numeroPagina) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado
	 *  
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerComando(Integer idTarifaSocialComandoCarta)
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta,Integer codigoTipoCarta) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado 
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarContasTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0004]�Retirar Im�vel tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer motivoExclusao, Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDataExecucaoTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta)
			throws ErroRepositorioException ;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocial(Integer idLocalidade) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 05/04/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialComandoCarta pesquisarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeImoveisExcluidostarifaSocial(
			Integer codigoTipoCarta,Integer idGerencia,Integer idUnidade,Integer idLocalidade, 
			Integer referenciaInicial, Integer refereciaFinal)throws ErroRepositorioException;
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeImoveisExcluidostarifaSocial(Integer referenciaInicial, Integer refereciaFinal,
			Integer codigoTipoCarta,RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoMotivoCarta(Integer idCodigoMotivo) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeTarifaSocialDadoEconomia(Integer idtarifaSocialExclusaoMotivo,
			Integer referenciaInicial, Integer refereciaFinal,Integer idGerencia,Integer idUnidade,Integer idLocalidade) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1161] Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 11/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDadosFaturamentoSituacaoHistorico(Integer idImovel,
			Integer referenciaFaturamentoGrupo,String observacaoRetira)
			throws ErroRepositorioException;
	
	/**
	 * [UC1161] Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 11/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDadosImovel(Integer idImovel, Integer referencia)
			throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0008]-Verificar carta para o comando
	 *  
	 * @author Vivianne Sousa
	 * @date 19/04/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerCartasComando(Integer idTarifaSocialComandoCarta, 
			Integer idLocalidade, Integer tipoCarta)throws ErroRepositorioException;
	
	/**
	 * [UC0352] Emitir Contas e Cartas
	 * [SB0017] � Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Vivianne Sousa
	 * @date 29/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarAnoMesExclusaoTarifaSocialImovel(Integer idImovel) 
		throws ErroRepositorioException;

	/**
	 *  [UC1168] Encerrar Comandos de Cobran�a por Empresa
	 *
	 * @author Mariana Victor
	 * @created 10/05/2011
	 */
	public void retirarSituacaoCobrancaImovel(Integer idImovel, Integer idCobrancaSituacao)
		throws ErroRepositorioException;

	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Emitir OS de Empresa de Cobran�a - 
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Integer[]> pesquisarIdsImoveis(String[] idsOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1174] Gerar Relat�rio Im�veis com Doa��es
	 * 
	 * Quantidade de imoveis com doa��es - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Integer pesquisarQuantidadeImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ErroRepositorioException;
	
	/**
	 * [UC1174] Gerar Relat�rio Im�veis com Doa��es
	 * 
	 * Pesquisar Imoveis com Doa��es - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Collection pesquisarImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ErroRepositorioException;
	
	/**
	 * [UC0000] Obter Consumo N�o Medido por Par�metro
	 * [FS0001] - Verificar "Pontos de Utiliza��o" N�o Informado.
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @throws ControladorException
	 */

	public Short pesquisarPontosUtilizacaoImovel(Integer idImovel) 
		throws ErroRepositorioException;

	/**
	 * [UC0000] Obter Consumo N�o Medido por Par�metro
	 * [FS0002] - Verificar �N�mero de Moradores� N�o Informado.
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @throws ControladorException
	 */

	public Short pesquisarNumeroMoradoresImovel(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2011
	 */
	public void atualizarSituacaoEspecialFaturamentoImovel(Integer idImovel,
			Integer idFaturamentoSituacaoTipo, Integer idFaturamentoSituacaoMotivo)	throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o id do im�vel �rea comum
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovelCondomio
	 * @return imovel.id
	 */
	public Integer pesquisarImovelAreaComum(Integer idImovelCondominio) throws ErroRepositorioException;

	/**
	 * <p>
	 * [UC0098] Manter V�nculos de Im�veis para Rateio Comum
	 * </p>
	 * <p>
	 * [SB0001] - [FS0012] - Caso a matr�cula do im�vel para �rea comum
	 * informada n�o exista na tabela IMOVEL, exibir a mensagem "Matr�cula
	 * inexistente no cadastro" e retornar para o passo correspondente no fluxo
	 * principal
	 * </p>
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * @param idImovel
	 * @return
	 */
	public Short verificarExistenciaDoImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o id do im�vel condom�nio
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovel
	 * @return imovel.id
	 */
	public Integer pesquisarImovelCondominio(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o id do im�vel condominio
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * 
	 * @param idImovel, indicadorImovelAreaComum
	 */
	public void atualizarIndicadorImovelAreaComumDoImovel(Integer idImovel, Short indicadorImovelAreaComum)	throws ErroRepositorioException;
	
	/**
	 * [UC0457] - Encerrar Ordem de Servi�o.
	 * [SB0009] - Verificar Situa��o Especial de Faturamento.
	 * 
	 * Verifica se um im�vel est� em situa��o especial de faturamento
	 * para um dado imovel (idImovel). 
	 * A situa��o especial de faturamento tem o ftst_id = 2
	 * 
	 * @param idImovel
	 * @return Imovel
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovelSituacaoEspecialFaturamento(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * Verificar se o imovel tem situacao especial de cobranca (cbsp_id <> null DA TABELA IMOVEL ) , caso tenha informar a mensagem:
	 * "Imovel est� em situa��o especial de cobran�a, n�o � poss�vel colocar no programa especial."
	 *
	 * @author Th�lio Ara�jo
	 * @since 14/10/2011
	 * @param idImovel
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean confirmarImovelTemSituacaoEspecialCobranca(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC1243] Pesquisar Pagamento
	 * 
	 * @author Davi Menezes
	 * @data 16/11/2011
	 * 
	 * @param idPagamento, DocumentoTipo
	 * @return Collection<Pagamento>
	 */
	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento, Integer idDocumentoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC1243] Pesquisar Pagamento Historico
	 * 
	 * @author Davi Menezes
	 * @data 16/11/2011
	 * 
	 * @param idPagamento, DocumentoTipo
	 * @return Collection<Pagamento>
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(Integer idPagamentoHistorico, Integer idDocumentoTipo) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por verificar a exist�ncia de cpf/cnpj para a matricula do imovel.
	 * [UC1259] Inserir Cliente no Abiente Virtual
	 * @author Arthur Carvalho
	 * @date 13/12/2011
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaCpfCnpj(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * Metodo responsavel por verificar a exist�ncia de imovel cadastrado na tabela de cliente_virtual.
	 * [UC1259] Inserir Cliente no Abiente Virtual
	 * @author Arthur Carvalho
	 * @date 13/12/2011
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaClienteVirtual(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 12/12/2011
	 */
	public ImovelCobrancaSituacao obterImovelCobrancaSituacao(
			Integer idImovelSituacaoCobranca) throws ErroRepositorioException ;
	
	/**
	 * Atualiza a tarifa de consumo de um ou mais imoveis
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Im�veis
	 * 
	 * @author Th�lio Ara�jo
	 * @created 30/12/2011
	 * @param Imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelTarifaConsumoAtual(Imovel imovel)
					throws ErroRepositorioException;
	
	/**
	 * Atualiza a tarifa de consumo de um ou mais imoveis
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Im�veis
	 * 
	 * @author Th�lio Ara�jo
	 * @created 30/12/2011
	 * @param Imovel
	 * @throws ErroRepositorioException
	 */
	public ConsumoTarifa pesquisarConsumoTarifaInformada(Integer idConsumoTarifa)
					throws ErroRepositorioException;
	/**
	 * 
	 * Pesquisa a cole��o de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * 
	 * 
	 * @author Gustavo Amaral
	 * 
	 * @since 28/12/2011
	 * 
	 * 
	 * 
	 * @param filtroClienteImovel
	 * 
	 * parametros para a consulta
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarCategoriaSubcategoriaConta(Integer idConta) throws ErroRepositorioException;
	
	
	/**
	 * 
	 * [UC0366] - Inserir Registro de Atendimento
	 * [FS0054] - Verificar se o im�vel informado tem cliente usu�rio desconhecido
	 * 
	 * Valida��o do im�vel, caso ele tenha algum "cliente desconhecido" do tipo usu�rio
	 * 
	 * @return true, caso ele n�o tenha "cliente desconhecido" do tipo usu�rio. False, caso contr�rio.
	 * @author Hugo Azevedo
	 * @date 15/02/2012
	 * 
	 * */
	public boolean verificarImovelClienteDesconhecido(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * UC1279ConsutlarImovelComLigacaoDeAguaNaSituacaoCortado
	 * [SB0001] - Pesquisar Im�veis Cortados
	 * 
	 * @author Arthur Carvalho
	 * @since 15/02/2012
	 */
	public Collection pesquisarImoveisCortados( FiltrarImoveisCortadosHelper filtro, Integer numeroPaginas) throws ErroRepositorioException;
	
	/**
	 * UC1279ConsutlarImovelComLigacaoDeAguaNaSituacaoCortado
	 * [SB0003] - Calcula Debito do Imovel
	 * 
	 * @author Arthur Carvalho
	 * @date 02/01/2012
	 * @throws ErroRepositorioException
	 */
	public BigDecimal calculaDebitoImovel(Integer idImovel) throws ErroRepositorioException ;
	

	/**
	 * 
	 * UC1279ConsutlarImovelComLigacaoDeAguaNaSituacaoCortado
	 * 
	 * @author Arthur Carvalho
	 * @since 15/02/2012
	 */
	public Collection gerarRelatorioImoveisCortados( FiltrarImoveisCortadosHelper filtro) throws ErroRepositorioException ;
	
	/**
	 * 
	 * UC1279ConsutlarImovelComLigacaoDeAguaNaSituacaoCortado
	 * 
	 * @author Arthur Carvalho
	 * @since 15/02/2012
	 */
	public Collection pesquisarDebitosDataCorte(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * 
	 * UC1279ConsutlarImovelComLigacaoDeAguaNaSituacaoCortado
	 * 
	 * @author Arthur Carvalho
	 * @since 15/02/2012
	 */
	public Collection pesquisarPagamentosAposDataCorte(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * UC1297 Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * SB0002 Pesquisar por Movimento
	 * 
	 * @author Arthur Carvalho
	 * @since 22/03/2012
	 * 
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param idCadastrador
	 * @param periodoMovimentoInicial
	 * @param periodoMovimentoFinal
	 * @param indicadorSituacaoMovimento
	 * @param tipoInconsistencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosMovimentoAtualizacaoCadastralHelper(String idLocalidade, String codigoSetorComercial, String quadraInicial, String quadraFinal,
			String idCadastrador , String periodoMovimentoInicial , String periodoMovimentoFinal, String indicadorSituacaoMovimento, String tipoInconsistencia, String idEmpresa) 
					throws ErroRepositorioException ;

	/**
	 *  * UC1297 Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * 
	 * @author Arthur Carvalho
	 * @since 22/03/2012
	 * @param idParametro
	 * @param dataMovimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosImovelAtualizacaoCadastralHelper(String idParametro, String dataMovimento, String idImovel, 
			String codigoCliente, String numeroDocumento, String situacao, String idLocalidade, String codigoSetorComercial, String quadraInicial, String quadraFinal,
			String idCadastrador , String periodoMovimentoInicial , String periodoMovimentoFinal, String indicadorSituacaoMovimento, String tipoInconsistencia,
			String idEmpresa ) throws ErroRepositorioException;
	
	/**
	 *  UC1297 Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * 
	 * @author Arthur Carvalho
	 * @since 22/03/2012
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarSituacaoRetornoAtualizacaoCadastral(String idImovel, String idImovelAtlzCadastral) throws ErroRepositorioException; 
	
	/**
	 *  UC1297 Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * 
	 * @author Arthur Carvalho
	 * @since 22/03/2012
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarAtributoInconsistente(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * UC1297 Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterCpfCnpjClienteAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * UC1297 Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterCpfCnpjClienteUsuario(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * UC1297 Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012 
	 * @param idRetornoAtlzCadastral
	 * @param codigoAlteracao
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAtualizacaoAtributo(Integer idRetornoAtlzCadastral, Short codigoAlteracao, Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * 
	 * UC1279ConsultarImovelComLigacaoDeAguaNaSituacaoCortado
	 * [SB0001] - Pesquisar Im�veis Cortados
	 * 
	 * @author Arthur Carvalho
	 * @since 15/02/2012
	 */
	public Integer pesquisarImoveisCortadosCount( FiltrarImoveisCortadosHelper filtro) throws ErroRepositorioException;
	
	/**
	 * [UC1287] - Consultar Im�veis com Ocorr�ncia de Corte na Liga��o de  �gua
	 * 
	 * @author Arthur Carvalho
	 * @since 23/04/2012
	 */
	public Collection pesquisarQuantidadeValorDebitosOrdemCorte(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1287] - Consultar Im�veis com Ocorr�ncia de Corte na Liga��o de  �gua
	 * 
	 * @author Arthur Carvalho
	 * @since 23/04/2012
	 */
	public Collection pesquisarQuantidadeValorDebitosConta(Integer idImovel) throws ErroRepositorioException;
	
	 /**
     * [UC0214] Efetuar Parcelamento de D�bitos
     * [SB0002] Obter Op��es Parcelamento
     * 
     * @author Vivianne Sousa
     * @date 21/05/2012
     * 
     * @throws ErroRepositorioException
     */
    public Imovel pesquisarDadosImovelParcelamento(Integer idImovel) throws ErroRepositorioException ;

	/**
	 * [UC0367] Atualizar Dados da Liga��o Agua
	 * 
	 * Consulta o Comando pelo id do imovel
	 * 
	 * @author Diego Maciel
	 * @date 21/05/2012
	 * 
	 * @param idImoivel
	 * @return Imovel
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarComandosPorImovel(Integer idImovel) 
			throws ErroRepositorioException;

	public Collection pesquisarMotivoNaoPagamentoCobrancaResultado(
			Integer idImovel, List<Integer> idsComandos,
			Integer referenciaInicial, Integer referenciaFinal) throws ErroRepositorioException;
	
	 /**
	 * 
	 * @date 14/06/2012
	 * 
	 * @author Raimundo Martins
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */
	public Imovel pesquisarImovelPorId(Integer idImovel) throws ErroRepositorioException;

	/**
	 *  Metodo responsavel por verificar se existe categoria pro imovel diferente de residencial
	 *   
	 *  @author Arthur Carvalho
	 *  @date 22/06/2012
	 *  
	 */
	public boolean existeCategoriaDiferenteResidencial(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * [SB0015] - Atualizar/Inserir Subcategorias e Economias do Im�vel
	 * 
	 * @author Arthur Carvalho
	 * @since 17/09/2012
	 * 
	 * @param idImovel
	 * @param idCategoriaPrincipal
	 * @param idSubCategoriaPrincipal
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelCategoriaSubcategoriaPrincipal(Integer idImovel, Integer idCategoriaPrincipal, Integer idSubCategoriaPrincipal, Integer quantidadeEconomia ) throws ErroRepositorioException ;
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * [SB0019] - Atualizar Situa��o da Liga��o de Esgoto do Im�vel 
	 * 
	 * @author Arthur Carvalho
	 * @since 17/09/2012
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoEsgotoSituacao(Integer idImovel, Integer idLigacaoEsgotoSituacao) throws ErroRepositorioException ;
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * [SB0018] - Atualizar Situa��o da Liga��o de �gua do Im�vel 
	 * 
	 * @author Arthur Carvalho
	 * @since 17/09/2012
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoAguaSituacao(Integer idImovel, Integer idLigacaoAguaSituacao) throws ErroRepositorioException;
	
	
	/**	 
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * [FS0004] - Obter refer�ncia do grupo de faturamento do im�vel
	 * 
	 * @author Mariana Victor
	 * @date 28/08/2012
	 * */	
	public Integer obterReferenciaGrupoFaturamentoImovel(
			Integer idImovel)
			throws ErroRepositorioException;	
	
	/**	 
	 * [UC0014] Manter Im�vel
	 * [FS0048] - Verificar permiss�o especial para o cliente associado
	 * 
	 * Caso o im�vel selecionado esteja associado a um cliente 
	 *   que n�o permite altera��o sem permiss�o especial
	 * 
	 * @author Mariana Victor
	 * @date 30/08/2012
	 * */	
	public boolean verificarImovelAssociadoClienteBloqueiaAlteracao(
			Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisar tipo de medi��o do Retorno Atualiza��o Cadastral
	 * 
	 * @return medicaoTipo
	 * 
	 * @author Vivianne Sousa
     * @date 01/10/2012
	 * @exception ErroRepositorioException
	 */
	public MedicaoTipo pesquisarMedicaoTipoRetornoAtualizacaoCadastral(Integer idRetornoAtlzCadastral)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa a cole��o de clientes da conta 
	 * [UC0204] Consultar Conta
	 * [SB0002]-Pesquisar Cliente Conta
	 * 
	 * @param idConta
	 * @return Collection
	 * @exception ErroRepositorioException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesConta(Integer idConta) 
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a cole��o de clientes da conta 
	 * [UC0204] Consultar Conta
	 * [SB0003]-Pesquisar Cliente Conta Anterior
	 * 
	 * @param idConta
	 * @return Collection
	 * @exception ErroRepositorioException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesContaAnterior(Integer idConta) throws ErroRepositorioException;

	/**
	 * Pesquisar cole��o de ImovelSubcategoria do im�vel
	 * 
	 * @author Vivianne Sousa
     * @date 03/10/2012
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarImovelSubcategoria(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a
	 * [SB0008] Gerar Atividade de A��o de Cobran�a para a Rela��o de Im�veis do Arquivo
	 * 
	 * @author Hugo Azevedo
	 * @created 16/10/2012
	 * 
	 */ 
	public Collection obterParametrosImovelRelacaoArquivo(Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo M�vel
	 * 
	 * [IT 0008 - Pesquisar Im�veis N�o Resetorizados]
	 * 
	 * @author Davi Menezes
	 * @date 19/11/2012
	 */
	public Collection<Object []> pesquisarImoveisNaoResetorizados(String idLocalidade, 
			Collection<Integer> colecaoLigacaoAguaSituacao, String clienteUsuario, Collection<Integer> colecaoImoveis) 
					throws ErroRepositorioException;
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo M�vel
	 * 
	 * [IT 0009 - Pesquisar Im�veis]
	 * 
	 * @author Davi Menezes
	 * @date 19/11/2012
	 */
	public Collection<Object []> pesquisarImoveisRoteiroDispositivoMovel(String idLocalidade, 
			String codigoSetorComercial, Collection<Integer> quadrasSelecionadas, 
			Collection<Integer> colecaoLigacaoAguaSituacao, String clienteUsuario, String[] indicadorSituacaoImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1390] Retirar Situacao Especial De Faturamento De Suspensao Do Faturamento Por Inadiplencia
	 * [SB0002] Gerar Atividade de A��o de Cobran�a para a Rela��o de Im�veis do Arquivo
	 * 
	 * @author Carlos Chaves
	 * @date 22/11/2012
	 * 
	 */ 
	public void atualizarTipoSituacaoFaturamento(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1396] Informar Dados para Devolu��o de Valor Faturado e Pago Indevidamente
	 * 
	 * @author Hugo Azevedo
	 * @date 20/11/2012
	 * 
	 */
	public Collection obterQuantidadeEconomiasCategoriaHistorico(Integer conta) throws ErroRepositorioException;

	/**
	 * Obtem o pr�ximo valor do sequence do Banco do Imovel
	 * 
	 * @author Arthur Carvalho
	 * @since 05/02/2013
	 * 
	 * @param objeto
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Object obterNextValImovel() throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por verificar se existe endereco ja cadastrado para outro imovel.
	 * 
	 * @author Arthur Carvalho
	 * @since 06/02/2013
	 * 
	 */
	public Collection pesquisarEnderecoExistente(Integer idLogradouro, Integer idBairro, Integer idCep, String numeroImovel, Integer matricula) throws ErroRepositorioException ;

	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [IT0003] Pesquisar Matr�cula do Im�vel
	 * 
	 * @author Hugo Azevedo
	 * @date 19/03/2013
	 */
	public Imovel obterImovel(Integer idImovel) throws ErroRepositorioException;

	
	/**
	 *  Metodo responsavel por pesquisar os dados dos im�veis no ambiente pre-gsan 
	 * @author Arthur Carvalho
	 * @date 20/03/2013
	 * 
	 * [UC1447] Consultar Im�veis no Ambiente Pr�-GSAN
	 * [IT0004] - Pesquisar Im�veis com Ocorr�ncia de Cadastro
	 * [IT0005] - Pesquisar Im�veis Novos
	 * [IT0006] - Pesquisar Endere�o de Im�vel Novo
	 * 
	 * @param helper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarImovelPreGsan(DadosImovelPreGsanHelper helper, boolean paraRelatorios) throws ErroRepositorioException ;
	
	/**
	 * @author Arthur Carvalho
	 * @date 20/03/2013
	 * 
	 * [UC1447] Consultar Im�veis no Ambiente Pr�-GSAN
	 * @param helper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarCadastradorAtualizacaoCadastralSemLogin(DadosImovelPreGsanHelper helper) throws ErroRepositorioException;
	
	
	/**
	 * @author Arthur Carvalho
	 * @date 20/03/2013
	 * 
	 * [UC1447] Consultar Im�veis no Ambiente Pr�-GSAN
	 * @param helper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarImovelEnderecoIgualImovelAtualizacaoCadstral(Integer idImovelAtlzCad) throws ErroRepositorioException;
	
	
	/**
	 * [UC1447] - Consultar Im�veis no Ambiente Pr�-GSAN
	 * [SB0004] - Atualizar im�vel
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoImovelAtualizacaoCadastralPreGsan(String codigoSituacao, String indicadorDadosRetorno, String idImovelAtlzCad)throws ErroRepositorioException;
	
	/**
	 * [UC1447] - Consultar Im�veis no Ambiente Pr�-GSAN
	 * [SB0004] - Atualizar im�vel
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarImovelFotoAtualizacaoCadastral(Integer id) throws ErroRepositorioException;
	
	/**
	 * [UC1447] - Consultar Im�veis no Ambiente Pr�-GSAN
	 * [SB0004] - Atualizar im�vel
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarImovelFotoAtualizacaoCadastralFoto(Integer id)throws ErroRepositorioException;
	
	/**
	 * [UC 0512] - Inserir Contrato Demanda
	 * [SB 0001.6] Pesquisar Liga��o Esgoto do Imovel
	 * 
	 * @author Davi Menezes
	 * @date 23/04/2013
	 */
	public LigacaoEsgoto pesquisarLigacaoEsgotoImovel(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * [UC1393] Processar Requisi��es do Dispositivo M�vel Atualiza��o Cadastral.
	 * @author Arthur Carvalho
	 * @date 05/06/2013
	 * 
	 * @param idParametroTabelaAtlzCadastral
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoTransmissaoImovel(Integer idParametroTabelaAtlzCadastral) throws ErroRepositorioException ;
	
	/**
	 * M�todo que verifica se existe pendencias na altera��o de inconsistencia de im�veis
	 * 
	 * @author Rodrigo Cabral
	 * @date 14/06/2013
	 */
	public boolean existeImovelComAlteracoesPendentes(Integer idImovel) throws ErroRepositorioException;
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel Atualiza��o Cadastral
	 * 
	 * @author Anderson Cabral
	 * @date 25/06/2013
	 *  
	 * @throws ErroRepositorioException
	 * 
	 */
	public Imovel pesquisarImovelGerarRoteiro(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * @author Rodrigo Cabral
	 * @date 14/06/2013
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorImovelPendenteAtualizacaoCadastral(String idImovelAtlzCad)throws ErroRepositorioException;
	
	/**
	 * Metodo respos�vel por atualizar uma coluna na tabela imovel - 
	 * Recebe como parametro a coluna que vai ser atualizada e o novo valor dessa coluna - Para o im�vel informado.
	 * @author Arthur Carvalho
	 * 
	 * @param idImovel
	 * @param descricaoColuna
	 * @param valorAtualizado
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelColunaDinamica(Integer idImovel, String descricaoColuna, String valorAtualizado) throws ErroRepositorioException ;
	
	/**
	 * 
	 * O m�todo realiza uma pesquisa em imovel pela inscricao

	 * @author Rafael Pinto
	 * @date 18/07/2013
	 * 
	 * [UC1447] Consultar Im�veis no Ambiente Pr�-GSAN
	 */
	public Collection pesquisarImovelPorInscricao(Integer idLocalidade,Integer codigoSetor,
		Integer numeroQuadra,Integer numeroLote, Integer numeroSubLote) 
		throws ErroRepositorioException ;
	
	/**
	 * Pesquisa o imovel passando a inscricao para ver se existe imovel com essa inscricao.
	 * 
	 * @author Arthur Carvalho
	 * @date 21/08/2013
	 * 
	 * @param idLocalidade
	 * @param codigoSetor
	 * @param numeroQuadra
	 * @param numeroLote
	 * @param numeroSubLote
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelPorInscricao(Integer idLocalidade,Integer codigoSetor, Integer numeroQuadra,Integer numeroLote, Integer numeroSubLote, Integer idImovel) 
		throws ErroRepositorioException ;
	
	/**
	 * Atualiza os im�veis micros com o indicador de consumo franquia
	 * @author Arthur Carvalho
	 * @param idImovelMacro
	 * @param indicadorFranquia
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorConsumoFranquiaImovelMicro(Integer idImovelMacro, Integer indicadorFranquia) throws ErroRepositorioException ;
	
	/**
	 * [UC1311] Relatorio Posicao Atualizacao Cadastral
	 * 
	 * M�todo usado para retornar a quantidade de imoveis por localidade
	 * 
	 * @author Rafael Pinto
	 * @since 29/08/2013
	 * @param id localidade
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorLocalidade(Integer idLocalidade) 
			throws ErroRepositorioException;
	
	
	/**
	 * [FE0012] - Verificar im�vel com inscri��o em duplicidade no ambiente virtual 2
	 * 
	 * @author Arthur Carvalho
	 * @date 21/08/2013
	 * 
	 * @param idLocalidade
	 * @param codigoSetor
	 * @param numeroQuadra
	 * @param numeroLote
	 * @param numeroSubLote
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastrlaInscricaoDuplicada(Integer idLocalidade,Integer codigoSetor, Integer numeroQuadra,Integer numeroLote,
			Integer numeroSubLote, Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por remover ocliente fone atualizacao cadastral
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void removerClienteFoneAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
	/**
	 * Metodo responsavel por remover ocliente fone atualizacao cadastral
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void removerClienteAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	

	/**
	 * Metodo responsavel por remover Categoria e Subcategoria do imovel atualizacao cadastral
	 * 
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void removerImovelSubcategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por remover Hidrometro atualizacao cadastral
	 * 
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void removerHidrometroAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
	/**
	 * Metodo responsavel por remover o registro de imovel atualizacao cadastral
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void removerImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * O sistema pesquisa o imovel gerado para atualizacao cadastral com os dados o imovel de retorno.
	 * 
	 * @author Arthur Carvalho
	 * @date 21/08/2013
	 * 
	 * @param idLocalidade
	 * @param codigoSetor
	 * @param numeroQuadra
	 * @param numeroLote
	 * @param numeroSubLote
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralEnvio(Integer idImovel, Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * Metodo responsavel por remover Imovel ocorrencia cadastro atualizacao cadastral
	 * 
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void removerImovelOcorrenciaCadastroAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
	/**
	 * Metodo responsavel por remover as fotos do imovel atualizacao cadastral
	 * 
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void removerImovelFotoAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
	/**
	 * UC1562 AtualizarDadosEnderecosDosImoveisEnviadosPeloGEO
	 * 
	 * Verifica se existe algum imovel com o logradouro informado
	 * 
	 * @author Anderson Cabral
	 * @date 08/10/2013
	 * 
	 * @param idLogradouro
	 * @return ArrayList<Integer> idsImovel
	 * @throws ErroRepositorioException
	 */
	public ArrayList<Integer> pesquisarImovelPorLogradouro(Integer idLogradouro) throws ErroRepositorioException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 11/10/2013
	 * [UC1567] Consultar Debitos imoveis webservice
	 */
	public Short verificarExistenciaMatriculaImovel(Integer idImovel)
	 throws ErroRepositorioException;
	
	/**
	 *  Metodo responsavel por pesquisar os dados dos im�veis no ambiente pre-gsan 
	 * @author Anderson Cabral
	 * @date 12/11/2013
	 * 
	 * @param helper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarImovelPreGsanResumoPorCadastroOcorrencia(DadosImovelPreGsanHelper helper) throws ErroRepositorioException;
	
	/**
	* [UC0353] Efetuar Liga��o de �gua 
	* 
	* @author Flavio Ferreira
	* @date 21/11/2013
	* 
	* @throws ErroRepositorioException
	*/
    
	public Integer pesquisarClienteDesconhecidoAssociadoImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * O sistema pesquisa o imovel gerado para atualizacao cadastral com os dados o imovel de retorno.
	 * 
	 * @author Vivianne Sousa
	 * @date 07/01/2014
	 * 
	 * @param idImovel
	 * @param idComando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralRetorno(Integer idImovel, Integer idComando) throws ErroRepositorioException;
	
	/**
	 * @author Anderson Cabral
	 * @param idLogradouroAtualizacaoCadastral
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisaLogradouroAtualizacaoCadastral(Long idLogradouroAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por remover o logradouro atualizacao cadastral que nao esteja associado a nenhum outro imovel.
	 * 
	 * @author Anderson Cabral
	 * @param idLogradouroAtualizacaoCadastral
	 * @throws ErroRepositorioException
	 */
	public void removerLogradouroAtualizacaoCadastral(Long idLogradouroAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Imovel Atualiza��o Cadastral
	 * 
	 * @param idImovel
	 * @return Imovel
	 * 
	 * @author Anderson Cabral
     * @date 30/12/2013
	 * @exception ErroRepositorioException
	 */
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCad(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por remover o logradouro bairro atualizacao cadastral.
	 * 
	 * @author Anderson Cabral
	 * @param idLogradouroAtualizacaoCadastral
	 * @throws ErroRepositorioException
	 */
	public void removerLogradouroBairroAtualizacaoCadastral(Long idLogradouroAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por remover o logradouro cep atualizacao cadastral.
	 * 
	 * @author Anderson Cabral
	 * @param idLogradouroAtualizacaoCadastral
	 * @throws ErroRepositorioException
	 */
	public void removerLogradouroCepAtualizacaoCadastral(Long idLogradouroAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	* [UC0352] Emitir Contas e Cartas
	* 
	* [SB0029] - Gerar Arquivo TXT das Fichas de Compensa��o - Compesa
	* 
	* @author Mariana Victor
	* @date 23/12/2013
	* 
	* @throws ErroRepositorioException
	*/
	public Integer pesquisarCodigoDebitoAutomaticoImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 15/04/2014
	 * 
	 * [UC1597] Gerar Relat�rio dos Im�veis Ligados para Quadra sem Rede
	 * 
	 * @param helper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarImovelRedeAguaEsgotoEmQuadraSemRede(DadosImovelPreGsanHelper helper) throws ErroRepositorioException;
	
}