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
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioTipoServicoHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
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
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Administrador 
 */
public interface IRepositorioCadastro {
	
	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio, 
						Integer numeroPagina, Short indicadoPerene) throws ErroRepositorioException;	

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio, Short indicadorPerene)throws ErroRepositorioException;
	
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
	public void atualizarMensagemSistema(String mensagemSistema)throws ErroRepositorioException ;
	
	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ErroRepositorioException;
	
	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
	throws ErroRepositorioException;
	
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
	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ErroRepositorioException ;
	
	/**
	 * Migração dos dados do município de Ribeirão - O sistema
	 * gerar as tabelas cliente, cliente_endereço, imovel, cliente_imovel,
	 * imovel_subcategoria, ligacao_agua a parti da tabela Cadastro_ribeirao;
	 * 
	 * @author Ana Maria
	 * 
	 * @throws ControladorException
	 */	
	public Object[] pesquisarSetorQuadra(Integer idLocalidade)throws ErroRepositorioException;
	
	public Integer pesquisarCEP()throws ErroRepositorioException;
	
	public Integer pesquisarBairro() throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroBairro(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroCep(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public void inserirClienteEndereco(Integer idCliente, String numeroImovelMenor, String numeroImovelMaior,
			Integer idCep, Integer idBairro, Integer idLograd, Integer idLogradBairro, Integer idLogradCep) throws ErroRepositorioException;

	public void inserirClienteImovel(Integer idCliente, Integer idImovel, String data)throws ErroRepositorioException;	
	
	public void inserirImovelSubcategoria(Integer idImovel, Integer idSubcategoria)throws ErroRepositorioException;
	
	public void inserirLigacaoAgua(Integer idImovel, String dataBD)throws ErroRepositorioException;	
	
	public Collection pesquisarCadastroRibeiraop() throws ErroRepositorioException;
	
	public void atualizarImovelRibeirao(Integer idImovel, Integer codigo)throws ErroRepositorioException;

	/**
	 * Fim - Migração dos dados do município de Ribeirão
	 */
	
	/**
	 * 
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação
	 * 
	 * 
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * 
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
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

	public Collection pesquisarClientesSubordinados(Integer idCliente)

	throws ErroRepositorioException;



	
	/**
	 * [UC0624] Gerar Relatório para Atualização Cadastral
	 */
	
	public Collection pesquisarRelatorioAtualizacaoCadastral(Collection idLocalidades,
			Collection idSetores, Collection idQuadras, String rotaInicial,
			String rotaFinal, String sequencialRotaInicial, String sequencialRotaFinal) 
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;

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
		throws ErroRepositorioException;	

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;	

	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros
	 * @date 17/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
		FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * Pesquisa a quantidade de imoveis para  o relatorio de imoveis por consumo medio
	 * 
	 * @author Bruno Barros
	 * @data 17/12/2007
	 * 
	 * @param filtro
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
		FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;	
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	
	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 * 
	 *  A pesquisa retorna uma colecao de Imoveis para que a partir
	 *  daí comece a geracao das linhas TXTs.
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
				throws ErroRepositorioException;
	
	/**
	 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN 
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
				throws ErroRepositorioException;
	
	/**
	 * Pesquisa o id localidade,codigo setor e codigo da rota 
	 * apartir do id da rota
	 * 
	 * @author Rafael Pinto

	 * @date 02/06/2008
	 * 
	 * @throws ErroRepositorioException
	 * @return Object[3] onde :
	 * 
	 * Object[0]=id localidade
	 * Object[1]=codigo do setor
	 * Object[2]=codigo da rota
	 */
	public Object[] pesquisarDadosRotaEntregaContaPorRota(Integer idRota)
			throws ErroRepositorioException ;
	
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
	public Collection pesquisarSetorComercialPorQualidadeAgua(int tipoArgumento, BigDecimal indiceInicial, 
			BigDecimal indiceFinal, Integer anoMesReferencia) throws ErroRepositorioException ;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Object[] obterImovelGeracaoTabelasTemporarias(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 20/09/2008
	 * 
	 * @return ImovelSubcategoria
	 * @throws ErroRepositorioException
	 */

	public Collection obterImovelSubcategoriaAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
		

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso Contas Descritas
	 * 
	 * @author Flávio Leonardo
	 * @date 08/09/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtraso
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso Contas Descritas
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;

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
            String anoOrigemFeriado  )
        throws ErroRepositorioException;
    
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
            String anoOrigemFeriado  )
        throws ErroRepositorioException; 
    
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
    public void excluirFeriadosNacionais( String anoDestino ) 
        throws ErroRepositorioException;
        
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
    public void excluirFeriadosMunicipais( String anoDestino ) 
        throws ErroRepositorioException;
    
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
    public Collection pesquisarLocalidades() throws ErroRepositorioException ;
    
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, 
			String idLocalidade, String idAgenteComercial, String idSituacaoTransmissao)throws ErroRepositorioException;
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt)
		throws ErroRepositorioException;
	
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
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt, Integer idSituacaoTransmissao)
			throws ErroRepositorioException;
	
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

	public Collection<Integer>  obterIdsImovelGeracaoTabelasTemporarias(Integer idSetor, ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 26/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelDebitoAtualizacaoCadastral(Collection colecaoIdsImovel)
	throws ErroRepositorioException;
	
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
    	throws ErroRepositorioException ;

    /**
	 * Pesquisa a(s) quadra face associada a quadra 
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra) 
			throws ErroRepositorioException ;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
	throws ErroRepositorioException;

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona as operações efetuadas pela empresa no período informado e com imóvel associado
	 * [SB0001 - Selecionar Operações Efetuadas com Imóvel Associado].
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasComImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona as operações efetuadas pela empresa no período informado e sem imóvel associado
	 * [SB0002] - Selecionar Operações Efetuadas sem Imóvel Associado
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasSemImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema obtém os dados do contrato com a empresa 
	 * (a partir da tabela EMPRESA_CONTRATO_CADASTRO com EMPR_ID=Id da empresa retornado 
	 * e ECCD_DTFINALCONTRATO maior ou igual à data corrente e ECCD_DTCANCELCONTRATO com o valor nulo)
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public EmpresaContratoCadastro pesquisarEmpresaContratoCadastro(Integer idEmpresa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona os atributos que compõem o boletim 
	 * (a partir da tabela ATRIBUTO ordenando pelo grupo do atributo (ATGR_ID) 
	 * e pela ordem de emissão (ATRB_NNORDEMEMISSAO)).
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAtributosBoletim()
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * Valor de Atualização do Atributo 
	 * (ECCA_VLATUALIZACAO da tabela EMPRESA_CONTRATO_CADASTRO_ATRIBUTO 
	 * com ATRB_ID=ATRB_ID da tabela ATRIBUTO e 
	 * ECCD_ID=ECCD_ID da tabela EMPRESA_CONTRATO_CADASTRO);
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorAtualizacaoAtributo(
			Integer idAtributo,Integer idEmpresaContratoCadastro)throws ErroRepositorioException;
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosBoleto(int quantidadeInicio, Integer grupo, String nomeEmpresa)throws ErroRepositorioException;
	
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
	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)throws ErroRepositorioException;
	
	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrangência do código do usuário
	 *
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)throws ErroRepositorioException;
	
	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public List pesquisarImoveisExcluirDaTarifaSocial(Integer idSetor, Integer anoMesFaturamento)throws ErroRepositorioException;

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaDadoEconomia(String idImovel)throws ErroRepositorioException;
	
	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaImovel(String idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Arthur Carvalho
	 * @date 02/10/2009
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
		FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
	/**
	 *  Gerar Relatório de Imóveis 
	 * 
	 * @author Arthur Carvalho
	 * @date 08/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ErroRepositorioException;
	
	
	/**
	 *  Pesquisar Ids dos Imoveis com siac_id = 0 and empresa = a empresa do leiturista 
	 * 
	 * @author Arthur Carvalho
	 * @date 27/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsImoveisAtualizacaoCadastral(Integer idEmpresaLeiturista) throws ErroRepositorioException;
	
	/**
	 * Pesquisa as críticas existentes para um determinado arquivo importado da atualizacao cadastral simplificado.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo Id do arquivo
	 * @return Coleção de críticas do arquivo
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ErroRepositorioException;
	
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
	public BigDecimal pesquisarValorSugeridoDebitoTipo(
			Integer idDebitoTipo)throws ErroRepositorioException;
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt, Integer idSituacaoTransmissao)
		throws ErroRepositorioException;

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Sem Hidrometro
	 * 
	 * @author Hugo Leonardo
	 * @date 25/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *  	Pesquisa imoveis para execução do batch
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public Collection pesquisarImovelEmProgramaEspecial(
			Integer idPerfilProgramaEspecial,
			Rota rota,
			int numeroIndice, 
			int quantidadeRegistros)
		throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	
    /**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Collection<RelatorioColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal, 
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal, 
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	
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
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal,
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public Collection<Integer> pesquisarIdsImoveisDoSetorComercial(Integer idSetor,
			int quantidadeInicio, int quantidadeMaxima)throws ErroRepositorioException;
	
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public void atualizarCodigoDebitoAutomatico(Integer idImovel,
			Integer codigoDebitoAutomatico)throws ErroRepositorioException;
	
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
    public byte[] baixarNovaVersaoJad() throws ErroRepositorioException;
    
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
    public byte[] baixarNovaVersaoJar() throws ErroRepositorioException;  
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ErroRepositorioException
     */
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ErroRepositorioException; 
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ErroRepositorioException;
    
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
    public Integer inserirCadastroEmailCliente( Integer idCliente, String nomeClienteAnterior, 
     		String cpfAnterior, String cnpjAnterior, String emailAnterior, String nomeSolicitante, 
     		String cpfSolicitante, String nomeClienteAtual, String cpfClienteAtual,
 			String cnpjClienteAtual, String emailAtual) throws ErroRepositorioException;
    
    /**
     * Atualiza o sequencial de rota do imóvel correspondente ao
     * RotaAtualizacaoSeq recebido 
     *  
     * @author bruno
     * @date 11/08/2010
     * 
     * @param rotaAtualizacaoSeq - Registro da tabela micromedicao.rota_atualizacao_sequencial
     * 
     * @throws ErroRepositorioException
     */
    public void atualizarSequenciaRotaImovel( 
            RotaAtualizacaoSeq seq )
            throws ErroRepositorioException;
    /**
     * 
     * @author Rômulo Aurélio
     * @date 28/09/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico( Integer idImovel ) 
    		throws ErroRepositorioException;
    
    /**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     *  	-TIPO USUARIO
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 		-TIPO LOCALIDADE
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * [FS0007] 
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
    public boolean verificarRelacaoColuna(Integer idColuna) throws ErroRepositorioException;
    
    
    
    /**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Hugo Amorim de Lyra
     * @date 06/10/2010
     */
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
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
		throws ErroRepositorioException;
 	
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
		throws ErroRepositorioException;

    /**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;

    /**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 17/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Meio de Solicitação
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;

 	/**
	 * UC1162 AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper)
		throws ErroRepositorioException;
	
	/**
	 * UC1162 AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * 
	 * @author Anderson Cabral
	 * @date 22/07/2013
	 */
	public Collection pesquisaImovelInscricaoAlteradaPorImoveis(ImovelInscricaoAlteradaHelper helper)
		throws ErroRepositorioException;

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ErroRepositorioException;
	
		/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade()throws ErroRepositorioException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialMotivoCarta pesquisarTarifaSocialMotivoCarta(
			Integer idTarifaSocialMotivoCarta)throws ErroRepositorioException;

	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional
			,Integer idUnidadeNegocio)throws ErroRepositorioException;
	
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
	public Collection<Object[]> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper) throws ErroRepositorioException;

	
	
	/**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterCategorias() throws ErroRepositorioException;
	
	
	/**
     * Obtém a coleção de perfis dos imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterPerfisImoveis() throws ErroRepositorioException;
	
	

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarIdentificacaoUsuario(Integer idUsuario) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarUsuarioEmpresaComandoCobranca(Integer idUsuario, Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Pesquisa o email da Empresa 
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2011
	 */
	public String pesquisarEmailEmpresa(Integer idEmpresa) throws ErroRepositorioException;
	
	
	/**
	 * [UC34] Importância Logradouro
	 * 
	 * Atualiza a Importância do Logradouro
	 * 
	 * @author Fernanda Almeida
	 * @data 30/06/2011
	 */
	public void atualizarGrauImportancia(LogradouroBairro logradouroBairro, Integer grauImportancia) throws ErroRepositorioException;
	
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
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0588 / UC0589] Verifica existência do DDD
	 * 
	 * @author Nathalia Santos
	 * @data 23/09/2011
	 */
	public Boolean verificarDdd(Short Ddd) throws ErroRepositorioException;
	
	/**
	 * [UC0588 / UC0589] Verifica existência do funcionáriio ou do cliente
	 * 
	 * @author Nathalia Santos
	 * @throws ErroRepositorioException 
	 * @data 03/10/2011
	 */
	public  Boolean pesquisarFuncionarioOuCliente(Integer IdFuncionario, Integer idCliente) throws ErroRepositorioException;

		
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
	public Collection<Logradouro> pesquisarLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) throws ErroRepositorioException;
	
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
	public Collection<Logradouro> filtrarLogradouroMesmoNome(String logradouroNome, Integer numeroPagina, Integer idMunicipio) 
			throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;

	/**
	 * Método que pesquisa uma 
	 * EmpresaCobrancaFaixa pelo id
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */
	public EmpresaCobrancaFaixa pesquisarEmpresaCobrancaFaixa(Integer idCobrancaFaixa) throws ErroRepositorioException;
	
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

	public void atualizarNomeUsuarioComIdFuncionario(Integer idFuncionario,	String nomeFuncionario)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação de cobrança do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelCobrancaSituacao(Integer idImovel, Integer idCobrancaSituacao)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelSituacao(Integer idImovel)throws ErroRepositorioException;

	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param indicadorAtualizacao
	 * @param indicadorDadosRetorno
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelAtualizacaoCadastral> pesquisarImovelAtualizacaoCadastral(Short indicadorAtualizacao , Short indicadorDadosRetorno, 
			Integer idLocalidade, int quantidadeRegistros) throws ErroRepositorioException;
	
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
	public Short obterQuantidadeEconomiaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
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
	public Short obterSomatorioQuantidadeEconomiaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
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
	public String obterCpfCnpjClienteUsuarioAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
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
	public Collection<Integer> obterCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0006] - Validar Atributo Situacao do Hidrometro
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometroAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral, Integer medicaoTipo)throws ErroRepositorioException ;
	
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
			Integer medicaoTipo) throws ErroRepositorioException ;
	
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
	public void atualizarLigacaoAgua(Integer idImovel, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException;
	
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
	public void atualizarImovel(Integer idImovel, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException ;
	
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
	public boolean pesquisarParametroTabelaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral, Date dataSituacao) throws ErroRepositorioException ;
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastral> pesquisarClienteAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral, Integer idClienteRelacaoTipo, 
			boolean dataRelacaoFim ) throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastral> pesquisarClienteAtualizacaoCadastralClienteUsuario(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
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
	public boolean verificarRegistroAtendimentoAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ParametroTabelaAtualizacaoCadastro
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public ParametroTabelaAtualizacaoCadastro pesquisarParametroTabelaAtualizacaoCadastro(Integer idParametroTabelaAtualizacaoCadastro) throws ErroRepositorioException ;
	
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
	public Integer pesquisarUnidadeFederacaoDoRG(Integer idImovelAtualizacaoCadastral ) throws ErroRepositorioException ;
	
	
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
	public Integer pesquisarOrgaoExpedidorDoRG(Integer idImovelAtualizacaoCadastral ) throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Retorno Atualização Cadastral
	 * 
	 * @return RetornoAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<RetornoAtualizacaoCadastral> pesquisarRetornoAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException ;
	
	/**
	 * 
	 * @return ImovelSubcategoriaAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public ImovelSubcategoriaAtualizacaoCadastral pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException ;
	
	/**
	 * [UC1299] Atualizar Cliente para Atualização Cadastral
	 * @return Cliente
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Cliente pesquisarCliente(String cpfCnpj) throws ErroRepositorioException;
	
	/**
	  * Pesquisa o setor comercial passando como parametro o id da localidade e o codigo do setor
	  * 
	  * @param idLocalidade
	  * @param codigoSetor
	  * @return
	  * @throws ErroRepositorioException
	  */
   public SetorComercial pesquisarSetorComercial(int idLocalidade, Integer codigoSetor) throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;


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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

	/**
	 * [UC1309] Download nova versão Sistemas Android
	 * 
	 * @author Fernanda Almeida
	 * @date 23/04/2012
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] baixarNovaVersaoApk( Integer idSistemaAndroid ) throws ErroRepositorioException;
	
	
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
	public Collection<Object []> pesquisarRelatorioImoveisInconsistentes(Integer idMovimento, Date dataMovimento,
			String idLocalidade, String codigoSetorComercial, String numeroQuadraInicial,
			String numeroQuadraFinal, String idCadastrador, String indicadorSituacaoMovimento,
			String tipoInconsistencia)	throws ErroRepositorioException;
	
	/**
	 * [UC1297] Pesquisar Imovel Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	
    
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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;
	
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
	public Collection<ClienteAtualizacaoCadastral> pesquisarClienteAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) 
			throws ErroRepositorioException;
	
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
	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) 
			throws ErroRepositorioException;
	
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
	public Hidrometro pesquisarHidrometroPeloNumero(String numeroHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC 1311] Gerar Resumo da Posição de Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @since 11/04/2012
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarResumoPosicaoAtualizacaoCadastral(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	

	/**
     * [UC1336] Relatorio por tipo de servico por meio de solicitacao
     * 
     * @author Carlos Chaves
     * @date 04/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorMeioSolicitacao(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1336] Relatorio por tipo de servico (Segunda Via) por meio de solicitacao
     * 
     * @author Carlos Chaves
     * @date 04/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorMeioSolicitacao(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1336] Relatorio por tipo de servico (ExtratoDebito) por meio de solicitacao
     * 
     * @author Carlos Chaves
     * @date 08/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorMeioSolicitacao(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1336] Relatorio por tipo de servico (Averbacao) por meio de solicitacao
     * 
     * @author Carlos Chaves
     * @date 08/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorMeioSolicitacao(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1336] Relatorio por tipo de servico (Revisao de consumo) por meio de solicitacao
     * 
     * @author Carlos Chaves
     * @date 08/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorMeioSolicitacao(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;

 	
 	/**
     * [UC1334] Relatorio por tipo de servico (Parcelamento) por meio de usuario
     * 
     * @author Carlos Chaves
     * @date 10/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorUsuario(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1334] Relatorio por tipo de servico (Parcelamento) por meio de usuario
     * 
     * @author Carlos Chaves
     * @date 10/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorUsuario(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1334] Relatorio por tipo de servico (Parcelamento) por meio de usuario
     * 
     * @author Carlos Chaves
     * @date 10/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorUsuario(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1334] Relatorio por tipo de servico (AVERBACAO) por meio de usuario
     * 
     * @author Carlos Chaves
     * @date 10/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorUsuario(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1334] Relatorio por tipo de servico (REVISAO DE CONSUMO) por meio de usuario
     * 
     * @author Carlos Chaves
     * @date 10/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorUsuario(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (PARCELAMENTO) por localizacao
     * 
     * @author Carlos Chaves
     * @date 11/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (PARCELAMENTO) por localizacao
     * 
     * @author Carlos Chaves
     * @date 14/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (EXTRATO DE DEBITO) por localizacao
     * 
     * @author Carlos Chaves
     * @date 14/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (AVERBACAO) por localizacao
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (REVISAO CONSUMO) por localizacao
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (REVISAO CONSUMO) por localizacao Agrupando Por Estado
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstado(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (REVISAO CONSUMO) por localizacao Agrupando Por Gerencia Regional
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstadoGerencia(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (REVISAO CONSUMO) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstadoNegocio(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (REVISAO CONSUMO) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeEstadoLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (AVERBACAO) por localizacao Agrupando Por Estado
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstado(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (AVERBACA) por localizacao Agrupando Por Gerencia Regional
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstadoGerencia(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (AVERBACA) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstadoNegocio(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (AVERBACA) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeEstadoLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Extrato de debito) por localizacao Agrupando Por Estado
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstado(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (AExtrato de debito) por localizacao Agrupando Por Gerencia Regional
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstadoGerencia(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Extrato de debito) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstadoNegocio(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Extrato de debito) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeEstadoLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Segunda Via) por localizacao Agrupando Por Estado
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstado(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Segunda Via) por localizacao Agrupando Por Estado Gerencia
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstadoGerencia(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Segunda Via) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstadoNegocio(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Segunda Via) por localizacao Agrupando Por Estado Localidade
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeEstadoLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;

 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Parcelamento) por localizacao Agrupando Por Estado
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstado(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Parcelamento) por localizacao Agrupando Por Estado Gerencia
     * 
     * @author Carlos Chaves
     * @date 15/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstadoGerencia(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Parcelamento) por localizacao Agrupando Por Estado Negocio
     * 
     * @author Carlos Chaves
     * @date 16/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstadoNegocio(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Parcelamento) por localizacao Agrupando Por Estado Localidade
     * 
     * @author Carlos Chaves
     * @date 16/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeEstadoLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Parcelamento) por localizacao Agrupando Por Gerencia Localidade
     * 
     * @author Carlos Chaves
     * @date 16/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoParcelamentoPorLocalidadeGerenciaLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Segunda Via) por localizacao Agrupando Por Gerencia Localidade
     * 
     * @author Carlos Chaves
     * @date 16/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoSegundaViaPorLocalidadeGerenciaLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Extrato Debito) por localizacao Agrupando Por Gerencia Localidade
     * 
     * @author Carlos Chaves
     * @date 16/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoExtratoDebitoPorLocalidadeGerenciaLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Averbacao) por localizacao Agrupando Por Gerencia Localidade
     * 
     * @author Carlos Chaves
     * @date 16/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoAverbacaoPorLocalidadeGerenciaLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1335] Relatorio por tipo de servico (Revisao COnsumo) por localizacao Agrupando Por Gerencia Localidade
     * 
     * @author Carlos Chaves
     * @date 16/05/2012
     */
 	public Collection<Object[]> pesquisarDadosRelatorioTipoServicoRevisaoConsumoPorLocalidadeGerenciaLocalidade(GerarRelatorioTipoServicoHelper helper)
 		throws ErroRepositorioException;
 	

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
	public Collection<ImovelAtualizacaoCadastral> pesquisarColecaoImovelAtualizacaoCadastral( Integer idParametroTabelaAtualizacaoCadastro )throws ErroRepositorioException;
	
	/**
	 * [UC 1314] Gerar Resumo Quantitativo de Mensagens Pendentes
	 * 
	 * @author Davi Menezes
	 * @since 18/04/2012
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarResumoQuantitativoMensagensPendentes(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException;
	
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
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC 1315] Gerar Resumo da Posição de Atualização Cadastral por Pacote Recebidos
	 * 
	 * @author Davi Menezes
	 * @since 19/04/2012
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarResumoPosicaoAtualizacaoCadastralPacoteRecebido(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
	/**
	 * [UC 1326] Consultar Setores/Quadra Não Migrados para o Admin
	 * 
	 * @author Davi Menezes
	 * @since 30/04/2012, 02/05/2012
	 * 
	 * @param idLocalidade, codigoSetor, numeroQuadra
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarSetoresNaoMigrados(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarQuadrasNaoMigradas(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarImoveisNaoMigrados(String idLocalidade, String codigoSetor, Collection<Integer> numeroQuadra)
		throws ErroRepositorioException;
	
	/**
	 * [UC 1326] Consultar Setores/Quadra Retornados do Admin
	 * 
	 * @author Davi Menezes
	 * @since 30/04/2012, 02/05/2012
	 * 
	 * @param idLocalidade, codigoSetor, numeroQuadra
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarSetoresRetornados(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarQuadrasRetornadas(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarImoveisRetornados(String idLocalidade, String codigoSetor, Collection<Integer> numeroQuadra)
		throws ErroRepositorioException;
	
	/**
	 * @author Arthur Carvalho
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosHidrometroInstalacaoHistorico(Integer idImovel) throws ErroRepositorioException;
	
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
	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarImovelSubCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean  existeLogradouroGsanAdmin(Integer idLogradouro) throws ErroRepositorioException;
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0009] - Validar Atualização de Logradouro
	 * @author Arthur Carvalho
	 * 
	 * @param idBairro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean  existeBairroGsanAdmin(Integer idBairro) throws ErroRepositorioException;
	
	/**
	 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
	 * [SB0001]-Inserir Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisarMaiorCodigoBairro(Integer idMunicipio) throws ErroRepositorioException ;
	
	/**
	 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
	 * [SB0002]-Atualizar Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarBairro(Integer idBairro, String nomeBairro)throws ErroRepositorioException ;
	
	/**
	 * [UC1338] Atualizar Tabelas Básicas Recepção de Dados ADMIN
	 * [SB0004]-Atualizar Logradouro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarLogradouro(LogradouroAdmin logradouroAdmin)throws ErroRepositorioException;
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade
	 * 
	 * @author Arthur Carvalho
	 * @since 12/05/2012
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Localidade> pesquisarLocalidadeAreaAtualizacaoCadastral(Integer idEmpresa) throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Retorno Atualização Cadastral
	 * 
	 * @return RetornoAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/05/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<RetornoAtualizacaoCadastral> pesquisarRetornoAtualizacaoCadastralPendenteLogradouro()throws ErroRepositorioException ;
	
	/**
	 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisarIdLogradouroGsanAdmin(Integer idLogradouro) throws ErroRepositorioException;
	
	/**
	 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
	 * [SB0002]-Atualizar Bairro 
	 * @author Arthur Carvalho
	 * 
	 * @param idBairro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisaridBairroGsanAdmin(Integer idBairro) throws ErroRepositorioException;
	

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
	public void atualizarLogradouroBairroDoImovel(Integer idImovel, Integer idLogradouroBairro) throws ErroRepositorioException;
	
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
	public void atualizarLogradouroCepDoImovel(Integer idImovel, Integer idLogradouroCep) throws ErroRepositorioException;
	
	/**
	 * 
	 * @param idLogradouroAdmin
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeLogradouroAdmin(Integer idLogradouroAdmin) throws ErroRepositorioException;
	
	/**
	 * 
	 * @param idBairroAdmin
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean existeBairroAdmin(Integer idBairroAdmin) throws ErroRepositorioException ;
	
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
	public Collection<AreaAtualizacaoCadastral> pesquisarMunicipioAreaAtualizacaoCadastral(Integer idMunicipio) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados ADMIN
	 * [SB0005] - Atualizar Tabelas Básicas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Bairro> pesquisarBairroLiberadoParaAtualizacaoCadastral() throws ErroRepositorioException;
	
	/**
	 * [UC1322] Rotina de Realizar Inclusão de Dados ADMIN
	 * [SB0005] - Atualizar Tabelas Básicas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Logradouro> pesquisarLogradouroLiberadoParaAtualizacaoCadastral() throws ErroRepositorioException;

	
	/**
     * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

     * @author Arthur Carvalho
     * @since 10/05/2012
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Integer[]> pesquisarColecaoQuadraTabelasTemporarias(String idLocalidade, String colecaoCodigoSetor) throws ErroRepositorioException;
    
    /**
	 * [UC1322] Rotina de Realizar Inclusão de Dados ADMIN
	 * [SB0005] - Atualizar Tabelas Básicas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Municipio> pesquisarMunicipioLiberadoParaAtualizacaoCadastral() throws ErroRepositorioException;
	
	/**
	 * [UC0870] - Gerar Movimento de Contas em Cobranca por Empresa
	 *  
	 * Retorna o setor comercial do imovel
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/07/2012
	 * */	
	public Integer pesquisarIdSetorComercialImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * Recupera quantidade de imoveis enviados para atualziação cadastral
	 * 
	 * @author Vivianne Sousa
	 * @date 31/07/12
	 */
	public Integer recuperaQtdeImoveisPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)throws ErroRepositorioException;
	
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
		Integer idLocalidade, Integer idCadastrador) throws ErroRepositorioException;
	
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
			DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException;

	/**
	 * Pesquisa a descrição da Profissão a partir do id informado
	 * 
	 * @author Vivianne Sousa
	 * @date 24/08/12
	 */
	public String pesquisarDescricaoProfissao(Integer idProfissao)
			throws ErroRepositorioException;
	/**
	 * Recupera quantidade de imoveis com algum tipo de inconsistencia enviados para atualziacao cadastral
	 * 
	 * @author Anderson Cabral
	 * @date 27/08/12
	 */
	public Integer recuperaQtdeImoveisComInconsistenciasPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)
			throws ErroRepositorioException;
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
	public ImovelSubcategoria pesquisarImovelSubCategoria(Integer idImovel, Integer idSubcategoria) throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ParametroTabelaAtualizacaoCadastro
	 * 
	 * @author Arthur Carvalho
	 * @exception ErroRepositorioException
	 */
	public ClienteAtualizacaoCadastral pesquisarClienteAtlzCadastral(Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException;

	/**
	 * Pesquisar dados do Imovel Inscrição Resetorizada
	 * 
	 * @return Collection
	 * 
	 * @author Davi Menezes
	 * @date 08/11/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ImovelInscricaoResetorizada> pesquisarImoveisInscricaoResetorizada() throws ErroRepositorioException;
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0003] Pesquisar Imóvel Apto para Sorteio
	 * 
	 * @author Hugo Azevedo
	 * @date 18/10/2012
	 * 
	 **/
	public Collection pesquisarImovelAptoSorteio(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0004] Pesquisar Dados Cadastro Sorteio
	 * 
	 * @author Hugo Azevedo
	 * @date 18/10/2012
	 * 
	 **/
	public  Object[] pesquisarDadosCadastroSorteio(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0005] Obter E-mail cadastrado
	 * 
	 * @author Hugo Azevedo
	 * @date 22/10/2012
	 * 
	 **/
	public String obterEmailCadastrado(Integer idImovel) throws ErroRepositorioException;

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
			throws ErroRepositorioException;
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0004] Obter dados do Imóvel.
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public DadosImovelHelper obterDadosImovelSorteio(Integer idImovel) 
			throws ErroRepositorioException;
    
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
			throws ErroRepositorioException;
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0001] Exibir Quantidade Total de Inscritos
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public Integer pesquisarQuantidadeTotalInscritos() 
			throws ErroRepositorioException;

	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0008] Pesquisar Imóvel Cadastrado para Sorteio.
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public boolean pesquisarImovelCadastradoSorteio(Integer idImovel) 
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;
	
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB012] Verificar Categoria Residencial do Imóvel.
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 */
	public Integer obterCategoriaPrincipalImovel(Integer idImovel) 
			throws ErroRepositorioException;


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
			throws ErroRepositorioException;


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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

    
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
			throws ErroRepositorioException;

    
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
			throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio Mensal
	 * 
	 * [SB0004 - Verificar Situação de Ligação do Imóvel] 
	 * 
	 * @author Ana Maria
	 * @date 29/10/2012
	 */
	public boolean verificarSituacoaLigacaoAguaEsgotoImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * [IT 0005 - Pesquisar Imóveis Resetorizados]
	 * 
	 * @author Davi Menezes
	 * @date 19/11/2012
	 */
	public Collection<Object []> pesquisarImoveisResetorizados(String idLocalidade, 
			String codigoSetorComercial, String numeroQuadraInicial, String numeroQuadraFinal, 
			Collection<Integer> colecaoLigacaoAguaSituacao, String clienteUsuario) throws ErroRepositorioException;
	
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

	public Object[] obterDadosImovelInsricaoResetorizada(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * @author Davi Menezes
	 * @date 21/11/2012
	 */
	public Collection<Object []> pesquisarBairrosImovel(Collection<Integer> colecaoImoveis) throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarLogradouroBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
	/**
	 * [UC 1392] - Consultar Roteiro Dispositivo Movel
	 * 
	 * [IT 0003] - Selecionar Arquivos
	 * 
	 * @author Davi Menezes
	 * @date 27/11/2012
	 */
	public Collection<Object []> pesquisarArquivoRoteiroAtualizacaoCadastral (ConsultarRoteiroDispositivoMovelHelper helper) 
				throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeImoveisRecebidosAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 *
	 */
	public void atualizarListaAtualizacaoCadastralArquivoTexto(
			Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoAtualizacaoCadastralArquivoTexto,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date)  throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarLogradouro(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
	/**
	 * 
	 * @param colecaoBairros
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarLogradouroCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	
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
			String numeroCPF, String numeroCNPJ) throws ErroRepositorioException;

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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;
	
	
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
			Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException; 
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
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [IT0005] Pesquisar Dados Registro Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 19/03/2013
	 */
	public Collection<ClienteImovel> pesquisarDadosRegistroImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return  Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCadastral(String idEmpresa, String idLocalidade) 
			throws ErroRepositorioException;
	
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
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade) throws ErroRepositorioException;
	
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
	public Collection<Object[]>  pesquisarLogradouroBairroAtlzCad(String idLogradouro) throws ErroRepositorioException;
	
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
	public Collection<Object[]>  pesquisarLogradouroCepAtlzCad(String idLogradouro) throws ErroRepositorioException;
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * @author Anderson Cabral
	 * @since 18/03/2013
	 * 
	 * @param idParametro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer>  pesquisarRoteiroQuadra(Integer idParametro) throws ErroRepositorioException;
	
	/**
	 * @author Arthur Carvalho
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdImovelAtualizacaoCadastral(String codigo) throws ErroRepositorioException ;
	
	/**
	 * @author Arthur Carvalho
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteAtualizacaoCadastral(String codigo) throws ErroRepositorioException ;
	
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
	public Integer pesquisarImovelAtlzCadastralJaTransmitido(Integer idImovel, Integer idComando)throws ErroRepositorioException;
		
	/**
	 * @author Arthur Carvalho
	 * @date 22/05/2013
	 * 
	 * @param helper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImovelPPP(DadosContratoPPPHelper helper) throws ErroRepositorioException;
		
	/**
	 *  [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * 
	 * @author Maxwell Moreira
	 * @since 20/06/2013
	 * 
	 * @param idParametro
	 * @return Retorna um Collection<Integer> com os numeros de imoveis
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer>  pesquisarImoveisAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException;

	
	/**
	 * Metodo responsavel por verificar se existe subsistema com essa descricao
	 * @author Arthur Carvalho
	 * @date 18/06/2013
	 * @param descricaoSubSistema
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaSubSistema(String descricaoSubSistema) throws ErroRepositorioException ;
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * @author Anderson Cabral
	 * @date 20/06/2013
	 */
	public Collection<Object []> pesquisarBairrosImovel(Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por pesquisar os usuários da atualização cadastral
	 * @author Vivianne Sousa
	 * @date 22/07/2013
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarUsuarioAtuCadastral(Integer idEmpresa) throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 * 
	 * @author Arthur Carvalho
	 * @date 24/07/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoImoveisAlteracaoInscricaoViaBatch(FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper, String indicadorTipoPesquisa) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0011] Inserir Imovel
	 * [FS0025] Validar Número do Medidor de Energia
	*/
	public Collection pesquisarMedidorEnergia(String numeroMedidorEnergia)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexao Esgoto
	 * @author Hugo Azevedo
	 * @date 02/08/2013
	 * 
	 */
	public Municipio pesquisarMunicipio(Integer idMunicipio) throws ErroRepositorioException;
	
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
		Integer idLocalidade, Integer idCadastrador) throws ErroRepositorioException;
	
	/**
	 * [UC 1392] Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT 0006] Exibir Dados Cadastrador
	 */
	public Collection pesquisarDadosCadastrador(Integer idParametroAtualizacaoCadastral)
			throws ErroRepositorioException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Integer pesquisarPremioPrincipal(Integer idPremio)
		throws ErroRepositorioException;
	
	/**
	 * [UC1295] Efetuar Sorteio Premio
	 * 
	 * @author Jonathan Marcos, Mariana Victor
	 * @date 26/09/2013, 08/11/2013
	 */
	public List<Object[]> pesquisarImoveisAptosSorteioFiqueLegal2013(PremioSorteio premioSorteio)
		throws ErroRepositorioException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public void atualizarPremioSorteio(Integer idPremioSorteio)
		throws ErroRepositorioException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Collection<Object[]> obterDadosRelatorioSorteioFiqueLegal2013(String numeroSorteio)
		throws ErroRepositorioException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Date obterDataSorteioFiqueLegal2013() 
			throws ErroRepositorioException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 30/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Collection<Object[]> obterNumeroSorteioFuqueLegal2013(Integer idSorteio)
		throws ErroRepositorioException;
	
	/**
	 * [UC1321] Gerar Motivos Não Geração de Contas e Imóveis em Cobrança por Empresa
	 *  
	 * Pesquisa o Indicador de Exclusão e a Categoria Principal do imóvel.
	 *  
	 * @author Mariana Victor
	 * @date 13/09/2013
	 */
	public Object[] pesquisarIndicadorExclusaoCategoriaImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 01/10/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Integer pesquisarNumeroOrdemSorteioFiqueLegal2013() 
			throws ErroRepositorioException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * 
	 * @author Anderson Cabral
	 * @since 27/09/2013
	 */
	public Collection<Object[]> pesquisarLogradourosParaAtualizar(Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0004] - Pesquisar Quantidade de Imóveis Transferidos 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public Integer pesquisarQtdeImoveisTransferidos(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0003] - Pesquisar Quantidade de Imóveis Total 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public Integer pesquisarQtdeTotalImoveis(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [SB0001] - Gerar relatório 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public ArrayList<ImovelEnderecoArquivoTexto> pesquisarTotalImoveisArquivo(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0004] - Pesquisar Imóveis Transferidos 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public ArrayList<Integer> pesquisarImoveisTransferidos(Integer idLogradouro, Integer idBairro) throws ErroRepositorioException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * Pesquisa Imovel Endereco Arquivo Texto
	 * 
	 * @author Anderson Cabral
	 * @since 30/09/2013
	 */
	public ImovelEnderecoArquivoTexto pesquisarImovelEnderecoArquivo(Integer idArquivo) throws ErroRepositorioException;

	public Collection pesquisarFeriadoTotal(Short tipoFeriado, String descricao, Date dataFeriadoInicio,
			Date dataFeriadoFim, Integer idMunicipio, Integer numeroPagina, Short indicadorPerene)
					throws ErroRepositorioException;
	
	/**
	 * @author Anderson Cabral
	 * @since 17/10/2013
	 */
	public boolean verificarCadastradorComArquivoEmCampo(Integer idCadastrador)  throws ErroRepositorioException;

	/**
	 * Metodo responsavel que verifica se o imovel contem todos os itens obrigatorios.
	 * 
	 * @param codigoImovelAtualizacaoCadastra
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIntegridadeImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)throws ErroRepositorioException;
	
	/**
	 *  Metodo responsavel por remover todos os dados do imovel atualizacao cadastral, caso ocorra erro no carregamento
	 * @param codigoImovel
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)throws ErroRepositorioException ;
	
	/**
	 * Metodo responsavel por verificar se o setor comercial e a quadra existe no GSAN.
	 * Atualizacao cadastral.
	 * @author Arthur Carvalho
	 */
	public boolean verificarInscricaoInformadaValida(DadosImovelPreGsanHelper helper)  throws ErroRepositorioException;
	
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
	public ArrayList<Integer> pesquisarImovelAtualizacaoCadastralPorLogradouro(Integer idLogradouro ) throws ErroRepositorioException;
	
	
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
		throws ErroRepositorioException;
	
	/**
	 * @author Jonathan Marcos
	 * @date 10/04/2014
	 * @param idConta
	 */
	public Collection<Object []> obterCpfCnpjClienteConta(Integer idConta,Short idClienteRelacaoTipo)
			throws ErroRepositorioException;
	
	/**
	 * @author Jonathan Marcos
	 * @date 15/04/2014
	 * @param imac
	 */
	public Collection<Object []> obterOcorrenciasImovelAtualizacaoCadastral(Integer imac)
			throws ErroRepositorioException;
	
}