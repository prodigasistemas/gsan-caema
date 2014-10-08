
package gcom.cadastro;


import gcom.atendimentopublico.bean.DadosContratoPPPHelper;
import gcom.cadastro.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper;
import gcom.cadastro.atualizacaocadastral.bean.ComandoAtualizacaoCadastralHelper;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarRoteiroDispositivoMovelHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosCadastradorHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.atualizacaocadastral.bean.ImoveisNaoMigradosHelper;
import gcom.cadastro.atualizacaocadastral.bean.ImoveisRoteiroDispositivoMovelHelper;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.bean.ExibirFiltrarLogradouroHelper;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.GerarArquivoTextoAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cobranca.parcelamentojudicial.bean.RegistroImovelHelper;
import gcom.gui.portal.CadastrarImovelSorteioActionHelper;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioTipoServicoHelper;
import gcom.gui.relatorio.cadastro.micromedicao.FiltrarRelatorioColetaMedidorEnergiaHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.atendimentopublico.RelatorioSorteioPremiosHelper;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.RelatorioAcessoSPCBean;
import gcom.relatorio.cadastro.RelatorioAnaliseInconsistenciasAtualizacaoCadastralBean;
import gcom.relatorio.cadastro.RelatorioBoletimCadastroIndividualBean;
import gcom.relatorio.cadastro.RelatorioImoveisInconsistentesBean;
import gcom.relatorio.cadastro.RelatorioMensagensPendentesCadastradorBean;
import gcom.relatorio.cadastro.RelatorioQuantitativoMensagensPendentesBean;
import gcom.relatorio.cadastro.RelatorioResumoPosicaoAtualizacaoCadastralBean;
import gcom.relatorio.cadastro.RelatorioResumoPosicaoAtualizacaoCadastralPacoteBean;
import gcom.relatorio.cadastro.RelatorioResumoSituacaoImoveisAnalistaCadastradorBean;
import gcom.relatorio.cadastro.RelatorioTipoServicoBean;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioResumoImoveisAlteracaoInscricaoViaBatchBean;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.relatorio.cobranca.RelatorioComprovanteCadastramentoSorteioBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;





/**
 * Interface Controlador Cobranca PADRÃO
 * 
 * @author Rômulo Aurélio 
 * @date 24/11/2009
 */

public interface IControladorCadastro {
	
	
	/**
	 * Permite inserir um Histórico Alteração de Sistema
	 * 
	 * [UC0217] Inserir Historico Alteracao Sistema
	 * 
	 * @author Thiago Tenorio
	 * @date 30/03/2006
	 * 
	 */

	public Integer inserirHistoricoAlteracaoSistema(
			SistemaAlteracaoHistorico sistemaAlteracaoHistorico)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir uma Gerência Regional
	 * 
	 * [UC0000 - Inserir Gerencia Regional
	 * 
	 * @author Thiago Tenório
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param agencia
	 *            bancaria
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Gerência Regional.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException;

	/**
	 * Pesquisa as empresas que serão processadas no emitir contas
	 * 
	 * @author Sávio Luiz
	 * @date 09/01/2007
	 * 
	 */
	public Collection pesquisarIdsEmpresa() throws ControladorException;

	
	/**
	 * 
	 * Recebe uma arquivo e pra cada linha desse arquivo ele processa o imovelCelular ou ClienteImovelCelular
	 *
	 * @author Thiago Toscano
	 * @date 16/02/2009
	 *
	 * @param file
	 * @throws ControladorException
	 */
	public void carregarImovelAtualizacaoCadastral(BufferedReader buffer) throws ControladorException;

	/**
	 * Informar Parametros do Sistema
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/01/2007
	 * 
	 */

	public void informarParametrosSistema(SistemaParametro sistemaParametro,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0534] Inserir Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 17/01/2007
	 * 
	 */
	public Integer inserirFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao,
			Date dataFeriado, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina, Short indicadorPerene, Boolean impressao) throws ControladorException;

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio, Short indicadorPerene)
			throws ControladorException;

	/**
	 * [UC0535] Manter Feriado [SB0001] Atualizar Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 27/01/2006
	 * 
	 * @pparam feriado
	 * @throws ControladorException
	 */

	public void atualizarFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0535] Manter Feriado
	 * 
	 * Remover Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 29/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void removerFeriado(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Pesquisar os ids do Setor comercial pela localidade
	 * 
	 * @author Ana Maria
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade)
			throws ControladorException;
	
	/**
	 * Informar Mensagem do Sistema 
	 * 
	 * @author Kássia Albuquerque
	 * @date 02/03/2007
	 * 
	 */
	public void atualizarMensagemSistema(SistemaParametro sistemaParametro,Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ControladorException;
	
	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
	throws ControladorException;
	/**
	 * [UC????] Inserir Funcionario
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/04/2007
	 * 
	 */
	public void inserirFuncionario(Funcionario funcionario,
			 Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC????] Atualizar Funcionario
	 * 
	 * @author Rômulo Aurélio
	 * @date 17/04/2007
	 * 
	 * @param funcionario, usuarioLogado, idFuncionario
	 * 
	 */
	public void atualizarFuncionario(Funcionario funcionario,
			 Usuario usuarioLogado)	throws ControladorException;
	
	/**
	 * Pesquisar todos ids dos setores comerciais.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ControladorException;
	
	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 * 
	 * [UC0582] Emitir Boletim de Cadastro
	 * 
	 * @author Rafael Corrêa
	 * @data 15/05/2007
	 * 
	 * @param
	 * @return void
	 */
	public void emitirBoletimCadastro(
			CobrancaAcaoAtividadeCronograma cronogramaAtividadeAcaoCobranca,
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoCobranca, Date dataAtualPesquisa,
			CobrancaAcao cobrancaAcao, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Metódo responsável por inserir um Cliente Tipo
	 * 
	 * [UC???? - Inserir Cliente Tipo
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
	 * 
	 * @param ClienteTipo
	 *           
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirClienteTipo(
			ClienteTipo clienteTipo, Usuario usuarioLogado)
			throws ControladorException;
	
	
	/**
	 * Metódo responsável por inserir um Cliente Tipo
	 * 
	 * [UC???? - Inserir Cliente Tipo
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
	 * 
	 * @param ClienteTipo
	 *           
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarClienteTipo(
			ClienteTipo clienteTipo)
			throws ControladorException;
	
	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 * 
	 * [UC0582] Emitir Boletim de Cadastro pelo Filtro Imóvel por Outros Critérios
	 * 
	 * @param
	 * @return void
	 */
	public byte[] emitirBoletimCadastro(String idImovelCondominio, String idImovelPrincipal,
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
			String idUnidadeNegocio, String indicadorCpfCnpj, String cpfCnpj) throws ControladorException;
	
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

	throws ControladorException;
	
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
	public Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ControladorException;
	
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
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisFaturasAtrasoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ControladorException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ControladorException;

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros
	 * @date 17/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedioHelper
	 * 
	 * @return Collection<RelatorioImoveisConsumoMedioHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
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
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
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
	public List pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 19/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ControladorException;
	
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<RelatorioImoveisAtivosNaoMedidosHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisAtivosNaoMedidosHelper> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ControladorException;	
	
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
	public Integer pesquisarTotalRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ControladorException;	
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 10/01/2008
	 * 
	 * @param RelatorioImoveisTipoConsumoHelper
	 * 
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Collection<RelatorioImoveisTipoConsumoHelper> pesquisarRelatorioImoveisTipoConsumo(
		FiltrarRelatorioImoveisTipoConsumoHelper filtro)
		throws ControladorException;
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 10/01/2008
	 * 
	 * @param RelatorioImoveisTipoConsumoHelper
	 * 
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro) 
			throws ControladorException;	

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN 
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 * 
	 * @param ArquivoTextoDadosCadastraisHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void gerarArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper arquivoTextoDadosCadastraisHelper) throws ControladorException;
	
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
	public Collection<HidrometroInstalacaoHistorico> recuperaImoveisArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper arquivoTextoLigacoesHidrometroHelper) throws ControladorException;
    
    /**
     * Pesquisar os todos os ids de Setor comercial 
     * 
     * @author Vivianne Sousa
     * @date 07/02/2007
     * 
     * @return Collection<Integer>
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCodigosSetorComercial()
            throws ControladorException;
    
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
     * @throws ControladorException
     */
    public Collection pesquisarSetorComercialPorQualidadeAgua(int tipoArgumento, BigDecimal indiceInicial, 
		BigDecimal indiceFinal, Integer anoMesReferencia) throws ControladorException;
    
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Flávio Leonardo
	 * @date 10/09/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

     /**
     * [UC0xxx] Inserir Unidade de Negocio
     * 
     * 
     * @author Rômulo Aurélio
     * @date 29/09/2008
     * 
     * 
     * @return Integer
     * @throws ControladorException 
     * @throws ControladorException
     */
    
    
    public Integer inserirUnidadeNegocio(UnidadeNegocio unidadeNegocio, 
            Usuario usuarioLogado) throws ControladorException;
    
    /**
	 * [UC0???] Atualizar Unidade de Negocio
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 * 
	 * 
	 * @throws ControladorException 
	 * @throws ControladorException
	 */

	public void atualizarUnidadeNegocio(UnidadeNegocio unidadeNegocio,
			Usuario usuarioLogado) throws ControladorException;
	
	
	/**
     * [UC0789] Inserir Empresa
     * 
     * 
     * @author Rômulo Aurélio
     * @date 29/09/2008
     * 
     * 
     * @return Integer
     * @throws ControladorException
     */
    
    public Integer inserirEmpresa(Empresa empresa, EmpresaContratoCobranca empresaCobranca,
            Usuario usuarioLogado, List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException;
    
    
    /**
	 * [UC0784] Manter Empresa
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 * 
	 * 
	 * @throws ControladorException
	 */

    
    public void atualizarEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobranca, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover)
			throws ControladorException ;
    
	
    /**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 */

	public void obterImovelClienteProprietarioUsuario(Integer idSetor , Integer idFuncionalidadeIniciada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper) throws ControladorException;
	
	/**
	 * Gerar Arquivo Texto da Atualização Cadastral 
	 * para Dispositivo Móvel
	 * 
	 * @param helper
	 * 
	 * @author Ana Maria
     * @date 17/09/2008
	 * @exception ControladorException
	 */
    
	public void gerarArquivoTextoAtualizacaoCadastralDispositivoMovel(Integer idFuncionalidadeIniciada, GerarArquivoTextoAtualizacaoCadastralHelper helper)
		throws ControladorException;
    
    /**
     * 
     * [UC0535] Manter Feriado
     * 
     * @author bruno
     * @date 12/01/2009
     *
     * @param indicadorTipoFeriado
     * @param anoOrigemFeriado
     * @param anoDestinoFeriado
     */
    public void espelharFeriados( 
            String indicadorTipoFeriado, 
            String anoOrigemFeriado, 
            String anoDestinoFeriado )
        throws ControladorException;   
    
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
    public Collection pesquisarLocalidades() throws ControladorException ;
    

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, 
			String idLocalidade, String idAgenteComercial, String idSituacaoTransmissao)throws ControladorException;
	
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt)
		throws ControladorException;
	
	/**
	 * 
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 05/03/2009
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt)
		throws ControladorException;

	/** Método para verificar o Cliente é um funcionário
	 * 
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 *
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */
	
	 public Integer clienteSelecionadoFuncionario(Integer idCliente)
		throws ControladorException;
	 
	
	/**
	 * [UC0024] Inserir Quadra
	 *
	 * @author Raphael Rossiter
	 * @date 03/04/2009
	 *
	 * @param quadraFaceNova
	 * @param colecaoQuadraFace
	 * @throws ControladorException
	 */
	public void validarQuadraFace(QuadraFace quadraFaceNova, Collection colecaoQuadraFace, boolean verificarExistencia) 
		throws ControladorException;
        
	/**
	 * Pesquisa a Quadra Face atraves da quadra associada
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra)
			throws ControladorException ;
	
	/**
	 * Valida se o cliente é uma pessoa jurídica. Se não for, lança uma exceção.
	 * 
	 * [FS0002] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 11/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param cliente Cliente a ser verificado
	 * @throws ControladorException 
	 */
	public void validarSeClienteEhPessoaJuridica(Cliente cliente) throws ControladorException;
	
	/**
	 * Valida se o tipo do débito não é gerado automaticamente. Se for, lança uma exceção.
	 * 
	 * [FS0004] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param debitoTipo Tipo de débito a ser verificado
	 * @throws ControladorException
	 */
	public void validarSeDebitoTipoNaoEhGeradoAutomaticamente(DebitoTipo debitoTipo) throws ControladorException;
	
	/**
	 * Valida se já não existe uma entidade beneficente com aquele cliente. Se houver, lança uma exceção.
	 * 
	 * [FS0006] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param entidadeBeneficente Entidade beneficente a ser verificada
	 * @throws ControladorException
	 */
	public void validarPreExistenciaEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) throws ControladorException;
	
	/**
	 * [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param entidadeBeneficente Entidade beneficente a ser inserida
	 * @throws ControladorException
	 */
	public Integer inserirEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) throws ControladorException;
	
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
			throws ControladorException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
			throws ControladorException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param data
	 * @throws ControladorException
	 */
	public Object[] gerarBoletimCustoAtualizacaoCadastral(
			Empresa empresa, Date dataAtualizacaoInicio, Date dataAtualizacaoFim)throws ControladorException;

	/**
	 * [UC0925] Emitir Boletos
	 *
	 * @author Vivianne Sousa
	 * @date 10/07/2009
	 */
	public void emitirBoletos(Integer idFuncionalidadeIniciada,Integer grupo, Integer entidadeBeneficente)throws ControladorException;
	
	/**
	 * Obtém a quantidade de economias da categoria, levando em consideração o
	 * fator de economias
	 * 
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 * 
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasCategoria(Categoria categoria) throws ControladorException;
	
	/**
	 * Obtém a quantidade de economias da subcategoria, levando em consideração o
	 * fator de economias
	 * 
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 * 
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasSubcategoria(Subcategoria subcategoria) throws ControladorException;
	
	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrangência do código do usuário
	 *
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)throws ControladorException;

	/**
	 * [UC0928]-Manter Situação Especial de Faturamento
	 * [FS0003]-Verificar a existência do setor
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaSetorComercial(Integer idSetorComercial)throws ControladorException;
	
	/**
	 * [UCXXXX] Excluir Imoveis Da Tarifa Social 
	 * CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 */	
	public void excluirImoveisDaTarifaSocial(Integer idSetor , Integer idFuncionalidadeIniciada, Integer anoMesFaturamento) 
			throws ControladorException;

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Arthur Carvalho
	 * @date 02/10/2009
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedioHelper
	 * 
	 * @return quantidade de imoveis
	 * @throws ControladorException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
	 * @author Arthur Carvalho
	 * @date 08/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ControladorException;

	

	/**
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param arquivo Arquivo texto a ser importado
	 * @return Id do arquivo texto recém-inserido
	 * @throws ControladorException
	 */
	public Integer inserirArquivoTextoAtualizacaoCadastralSimplificado(AtualizacaoCadastralSimplificado arquivo, AtualizacaoCadastralSimplificadoBinario arquivoBinario,Collection<AtualizacaoCadastralSimplificadoLinha> linhas) throws ControladorException;

	/**
	 * Busca as críticas existentes para o arquivo passado como parâmetro.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo Id do arquivo texto importado
	 * @return Críticas existentes para o arquivo.
	 * @throws ControladorException
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ControladorException;
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * [FS0004] Validar dados do imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 17/12/2009
	 *
	 */
	public void validarDadosInserirImovelProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial
	 * [FS0004] Validar dados da suspensão imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 21/12/2009
	 *
	 */
	public void validarDadosSuspensaoImovelProgramaEspecial(
			ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial
	 *  	Suspende Imóvel em Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialOnline(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado,Short formaSuspensao) throws ControladorException;
	
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 *  	Inseri Imóvel em Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public Integer inserirImovelEmProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *  	Suspende Imóveis ativos no Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialBatch(int idFuncionalidadeIniciada,
			Usuario usuarioLogado,Rota rota)
		throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
	 * 
	 * @author Hugo Leonardo
	 * @date 19/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro) 
			throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisProgramasEspeciaisHelper> pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
		FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro)
		throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais - Relatório Sintético
	 * 
	 * @author Hugo Leonardo
	 * @date 25/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ControladorException; 
	
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * 
	 * Verificar se existe parcelamento para o Imovel em Programa Especial.
	 * 
	 * @author Hugo Leonardo
     * @throws ControladorException 
	 * @since 10/02/2010
	 *
	 */
    public Boolean verificarExistenciaParcelamentoImovel(Integer idImovel) throws ControladorException;
    
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
			FiltrarRelatorioColetaMedidorEnergiaHelper helper)
		throws ControladorException;
	
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
	public Integer countRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper) 
			throws ControladorException;
	
    /**
	 * [UC1011] Emitir Boletim de Cadastro Individual.
	 * 
	 * Criar Dados para Relatório de Boletim de Cadastro Individual
	 * 
	 * @author Hugo Leonardo
	 * @date 24/03/2010
	 * 
	 * @param idImovel
	 * 
	 * @return RelatorioBoletimCadastroIndividualBean
	 * @throws ControladorException
	 */
	public RelatorioBoletimCadastroIndividualBean criarDadosRelatorioBoletimCadastroIndividual(
			Integer idImovel) throws ControladorException;
	
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public void atualizarCodigoDebitoAutomatico(Integer integer,
			SetorComercial setorComercial) throws ControladorException;
	
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
    public byte[] baixarNovaVersaoJad() throws ControladorException;
    
    /**
     * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
     * 
     * Método que baixa a nova versão do JAR do mobile para o celular
     * 
     * @author Bruno Barros
     * @date 08/06/2010
     *  
     * @param 
     * @throws IOException
     */   
    public byte[] baixarNovaVersaoJar() throws ControladorException;
	
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ControladorException;
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ControladorException;
    
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
 			String cnpjClienteAtual, String emailAtual) throws ControladorException;
    
    /**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection pesquisarDadosRelatorioAlteracoesSistemaColuna(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ControladorException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * [FS0007] 
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public boolean verificarRelacaoColuna(Integer idColuna) throws ControladorException;
 	
 	
	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Analítico
     */
 	public Collection pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException;
    
    /**
	 * 
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/09/2010
	 * 
	 * @return
	 */
	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(
			Integer idImovel) throws ControladorException ;
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Resumo
     */
 	public Collection pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException;
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Hugo Amorim de Lyra
     * @date 06/10/2010
     */
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ControladorException;
 	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ControladorException
	 */
	public Collection<ImovelInscricaoAlterada> pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) throws ControladorException;
	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ControladorException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ControladorException;
	  
	/**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpj(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ControladorException;
 	
	/**
	 * Solicitar Conta em Braile.
	 * 
	 * [UC1128] Solicitar Conta Braile
	 * 
	 * @author Hugo Leonardo
	 * @date 04/03/2011
	 * 
	 */
    public Integer inserirSolicitacaoContaBraile(ContaBraileHelper contaBraileHelper) 
    	throws ControladorException;
    
    /**
	 * UC1162 – AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper)
		throws ControladorException;
	
 	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ControladorException;
	
		/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade()throws ControladorException;
	
	/**
     * [UC1160] Processar Comando Gerado Carta Tarifa Social  
     * 
     * @author: Vivianne Sousa
     * @date: 24/03/2011
     */
    public void processarComandoGerado(Integer idLocalidade , Integer idFuncionalidadeIniciada,
    		TarifaSocialComandoCarta tarifaSocialComandoCarta)throws ControladorException;
    
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0007]-Gera Arquivo TXT das Cartas
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarCartaTarifaSocial(TarifaSocialComandoCarta tscc,Integer idFuncionalidadeIniciada)throws ControladorException;
	
	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer idLocalidade,	int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(TarifaSocialComandoCarta tscc,int idFuncionalidadeIniciada) throws ControladorException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional
			,Integer idUnidadeNegocio)throws ControladorException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<RelatorioAcessoSPCBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioAcessoSPCBean> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper) throws ControladorException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<RelatorioAcessoSPCBean>
	 * @throws ControladorException
	 */
	public void atualizarGrauImportancia(LogradouroBairro logradouroBairro, Integer grauImportancia,Usuario usuario) throws ControladorException;

	
	
	
	 /**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */

	public Collection obterCategorias() throws ControladorException;
	
	
	/**
     * Obtém a coleção de perfis de imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */

	public Collection obterPerfisImoveis() throws ControladorException;
	
	/**
	 * [UC0060] Inserir Parametros do Sistema
	 * Validar documentos da loja virtual
	 * 
	 * @author Erivan Sousa
	 * @date 15/07/2011
	 * 
	 * @param byte[], String
	 * @throws ControladorException
	 */
	public void validarSistemaParametroLojaVirtual(byte[] fileData, String extensao) throws ControladorException;
	

	/**
	 * [MA2011061013]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idImovel
	 * 
	 * @return HidrometroMovimentado
	 * @throws ControladorException
	 */
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0588 / UC0589] Verifica existência do DDD
	 * 
	 * @author Nathalia Santos
	 * @data 23/09/2011
	 */
	public Boolean verificarDdd(Short Ddd) throws ControladorException;

	/**
	 * [UC0588 / UC0589] Verifica existência do funcionáriio ou do cliente
	 * 
	 * @author Nathalia Santos
	 * @data 03/10/2011
	 */
	public Boolean pesquisarFuncionarioOuCliente(Integer IdFuncionario, Integer idCliente) throws ControladorException;
	
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
	 * @throws ControladorException
	 */
	public Collection<Logradouro> pesquisarLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) 
			throws ControladorException;
	
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
	public Collection<ExibirFiltrarLogradouroHelper> filtrarLogradouroMesmoNome(String logradouroNome, Integer numeroPagina, Integer idMunicipio) 
			throws ControladorException;
	
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
			throws ControladorException;
	/**
	 * Método que pesquisa uma 
	 * EmpresaCobrancaFaixa pelo id
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */
	public EmpresaCobrancaFaixa pesquisarEmpresaCobrancaFaixa(Integer idCobrancaFaixa) throws ControladorException;
	
	/** Pesquisa se o Imovel teve a inscricao alterada para excluido. 
	 * @author Arthur Carvalho
	 * @date 31/10/11
	 * @param idImovel
	 * @return
	 */
	public boolean verificaImovelExcluidoFinalFaturamento(Integer idImovel) throws ControladorException ;
	
	/**
	 * Atualizar nome do usuario pelo id de funcionario
	 * 
	 * @author Erivan Sousa
	 * @date 06/12/2011
	 * 
	 * @param idFuncionario
	 * @param nomeFuncionario
	 * 
	 * @throws ControladorException
	 */

	public void atualizarNomeUsuarioComIdFuncionario(Integer idFuncionario,	String nomeFuncionario)throws ControladorException;

	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Rafael Pinto
	 * @date 21/12/2011
	 */
	public void obterImovelClienteProprietarioUsuario(ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException ;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Rafael Pinto
	 * @date 27/12/2011
	 */
	
	public void validarImovelGerarTabelasTemporarias(Integer idImovel, Integer idParametro)
		throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * 2. O sistema exibe o primeiro prêmio a ser sorteado.
	 * 
	 * @author Mariana Victor
	 * @date 06/03/2012
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarProximoPremio(Integer idSorteio) throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 07/03/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarImoveisAptoSorteio() throws ControladorException;

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
			Integer idPremio, Integer numeroOrdemSorteio) throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 09/03/2012
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarNumeroOrdemSorteio() throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0003] - Emitir Relatório
	 * 
	 * @author Mariana Victor
	 * @date 09/03/2012
	 * 
	 * @return Collection<RelatorioSorteioPremiosHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioSorteioPremiosHelper> pesquisarDadosRelatorioImoveisSorteados() 
			throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0004] - Gerar TXT.
	 * 
	 * @author Mariana Victor
	 * @date 09/03/2012
	 * 
	 * @param
	 * @return byte[]
	 */
	public byte[] emitirRelatorioSorteioPremiosArquivoTexto(Collection<RelatorioSorteioPremiosHelper> colecao) 
			throws ControladorException;
	

	/**
	
	 * @author Fernanda Almeida
	 * @date 03/05/2012
	 * * @param
	 * @throws IOException
	 */
	public Object[] baixarNovaVersaoApk(Integer idSistemaAndroid) throws ControladorException;
	
	
	
	/**
	  * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	  *  
	  * @author: Arthur Carvalho
	  * @date: 01/03/2012 
	  */
	public void atualizarDadosDosImoveisPeloRecadastramento(Integer idLocalidade, Integer idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1297] Atualizar Dados Cadastrais para Imoveis Inconsistentes
	 * 
	 * [SB0006] Relatório dos Imoveis Inconsistentes
	 * 
	 * @author Davi Menezes
	 * @date 26/03/2012
	 * 
	 * @return Collection<RelatorioImoveisInconsistentesBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisInconsistentesBean> pesquisarRelatorioImoveisInconsistentes(String [] idsRegistro,
			String idLocalidade, String codigoSetorComercial, String numeroQuadraInicial,
			String numeroQuadraFinal, String idCadastrador, String indicadorSituacaoMovimento,
			String tipoInconsistencia)throws ControladorException;
	
	/**
	 * [UC1297] Pesquisar Imovel Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ControladorException
	 */
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral ) throws ControladorException;
	
	/**
	 * [UC1297] Pesquisar Cliente Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @since 29/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ControladorException
	 */
	public Collection<ClienteAtualizacaoCadastral> pesquisarClienteAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) 
			throws ControladorException;
	
	/**
	 * [UC1297] Pesquisar Imovel Subcategoria Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @since 29/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ControladorException
	 */
	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) 
			throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0003] - Emitir Relatório
	 * 
	 * @author Mariana Victor
	 * @date 30/03/2012
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosImoveisSorteados(Integer idImovel) 
			throws ControladorException;

	/**
	 * [UC 1311] Gerar Resumo da Posição de Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @since 11/04/2012
	 * 
	 * @param Helper
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection<RelatorioResumoPosicaoAtualizacaoCadastralBean> pesquisarResumoPosicaoAtualizacaoCadastral(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;
	
	/**
	 * [UC 1312] Gerar Resumo da Situação dos imóveis por cadastrador/analista
	 * 
	 * @author Davi Menezes
	 * @since 12/04/2012
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioResumoSituacaoImoveisAnalistaCadastradorBean> pesquisarResumoSituacaoImoveis(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;

	
	/**
	 * [UC1297] Pesquisar HidrometroInstalacaoHistoricoAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 29/03/2012
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ControladorException
	 */
	public Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral> pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral,
			Integer medicaoTipo) throws ControladorException;
	
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 12/04/2012
	 * 
	 * @return Date
	 * @throws ControladorException
	 */
	public Date pesquisarDataSorteio() 
			throws ControladorException;

	
	/**
     * [UC1333] Gerar Relatório por tipo de servico
     * 
     * @author Carlos Chaves
     * @date 04/05/2012
     */
 	public Collection<RelatorioTipoServicoBean> pesquisarDadosRelatorioTipoServico(GerarRelatorioTipoServicoHelper helper)
 		throws ControladorException;

	
	/**
	 * [UC 1314] Gerar Resumo Quantitativo de Mensagens Pendentes
	 * 
	 * @author Davi Menezes
	 * @since 18/04/2012
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ControladorException
	 */
	public Collection<RelatorioQuantitativoMensagensPendentesBean> pesquisarResumoQuantitativoMensagensPendentes(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;
	
	/**
	 * [UC 1313] Gerar Resumo Quantitativo de Mensagens Pendentes por Cadastrador
	 * 
	 * @author Davi Menezes
	 * @since 18/04/2012
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ControladorException
	 */
	public Collection<RelatorioMensagensPendentesCadastradorBean> pesquisarResumoQuantitativoMensagensPendentesCadastrador(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;


	/**
	 * [UC 1315] Gerar Resumo da Posição de Atualização Cadastral por Pacote
	 * 
	 * @author Davi Menezes
	 * @since 19/04/2012
	 * 
	 * @param Helper
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection<RelatorioResumoPosicaoAtualizacaoCadastralPacoteBean> pesquisarResumoPosicaoAtualizacaoCadastralPacote(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;
	
	/**
	 * [UC 1326] Consultar Setores/Quadra Não Migrados para o Admin
	 * 
	 * @author Davi Menezes
	 * @since 30/04/2012
	 * 
	 * @param idLocalidade, codigoSetor, numeroQuadra
	 * @return Collection<ImoveisNaoMigradosHelper>
	 * @throws ControladorException
	 */
	public Collection<ImoveisNaoMigradosHelper> pesquisarSetoresNaoMigrados(String idLocalidade, String codigoSetor, String numeroQuadra)
		throws ControladorException;
	
	public Collection<ImoveisNaoMigradosHelper> pesquisarQuadrasNaoMigradas(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ControladorException;
	
	public Collection<ImoveisNaoMigradosHelper> pesquisarImoveisNaoMigrados(String idLocalidade, String codigoSetor, Collection<Integer> numeroQuadra)
			throws ControladorException;
	
	/**
	 * [UC 1326] Consultar Setores/Quadra Retornados do Admin
	 * 
	 * @author Davi Menezes
	 * @since 30/04/2012
	 * 
	 * @param idLocalidade, codigoSetor, numeroQuadra
	 * @return Collection<ImoveisNaoMigradosHelper>
	 * @throws ControladorException
	 */
	public Collection<ImoveisNaoMigradosHelper> pesquisarSetoresRetornados(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ControladorException;
	
	public Collection<ImoveisNaoMigradosHelper> pesquisarQuadrasRetornadas(String idLocalidade, String codigoSetor, String numeroQuadra)
			throws ControladorException;
	
	public Collection<ImoveisNaoMigradosHelper> pesquisarImoveisRetornados(String idLocalidade, String codigoSetor, Collection<Integer> numeroQuadra)
		throws ControladorException;
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<SetorComercial> pesquisarColecaoSetorComercialTabelasTemporarias(String idLocalidade, String empresa, Collection<Integer> colecaoSetor, String selecionados) 
			throws ControladorException ;
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<SetorComercial> pesquisarColecaoSetorComercialTabelasTemporariasParcial(String idLocalidade, String idEmpresa, Collection<Integer> colecaoSetorComercial,
			String selecionados) throws ControladorException;
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<SetorComercial> pesquisarColecaoSetorComercialEnviadosTabelasTemporarias(String idLocalidade, String idEmpresa) 
			throws ControladorException;
	

	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<SetorComercial> pesquisarColecaoSetorComercialEnviadosTabelasTemporariasParcial(String idLocalidade, String idEmpresa) 
			throws ControladorException;
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<SetorComercial> pesquisarColecaoSetorComercialEnviadosTabelasTemporarias(String idLocalidade, String idEmpresa, 
			Collection<Integer> colecaoSetorComercialSelecionados) throws ControladorException;
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<SetorComercial> pesquisarColecaoSetorComercialEnviadosTabelasTemporariasParcial(String idLocalidade, String idEmpresa, 
			Collection<Integer> colecaoSetorComercialSelecionados) throws ControladorException;
	
	/**
	 * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade
	 * 
	 * @author Arthur Carvalho
	 * @since 12/05/2012
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Localidade> pesquisarLocalidadeAreaAtualizacaoCadastral(Integer idEmpresa) throws ControladorException;
	
	/**
	 * [UC1337] Atualizar Logradouro do Imóvel da Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 12/05/2012
	 * 
	 * @param idFuncionalidadeIniciada
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarLogradouroImovelAtualizacaoCadastral(Integer idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC 1328] - Suspender Localidade para Atualização Cadastral
	 * [SF 0002] - Atualizar o indicador de bloquear logradouro do municipio
	 * 
	 * @author Davi Menezes
	 * @date 01/06/2012
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ControladorException
	 */
	public Collection<AreaAtualizacaoCadastral> pesquisarMunicipioAreaAtualizacaoCadastral(Integer idMunicipio)
			throws ControladorException;
	
	
	/**
     * [UC1261] - Gerar Tabelas Temporárias para Atualização Cadastral por Localidade

     * @author Arthur Carvalho
     * @since 10/05/2012
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Quadra> pesquisarColecaoQuadraTabelasTemporarias(String idLocalidade, Collection<Integer> colecaoSetorComercialSelecionados)
            throws ControladorException;
    
	/**
	 *  UC1297 Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * 
	 * @author Vivianne Sousa
	 * @date 26/07/2012
	 */
	public Logradouro pesquisarLogradouroImovelAtualizacaoCadastral(Long idLogradouro) throws ControladorException;
	
	/**
	 * [UC1361] EfetuarDigitacaoDadosParaAtualizacaoCadastral
	 * 
	 * @author Davi menezes
	 * @date 03/08/2012
	 * 
	 */
	public void inserirDadosAtualizacaoCadastral(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, 
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastral, ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral,
			Collection<ImovelSubcategoriaAtualizacaoCadastral> colecaoImovelSubcategoria, 
			HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstalacaoHistorico) throws ControladorException;
	/**
	 * [UC1294] Efetuar Ligação Esgoto Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 07/02/2012
	 */
	public boolean efetuarLigacaoEsgotoAtualizacaoCadastral(Imovel imovel, ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, Integer medicaoTipo,
			Integer idParametroTabelaAtualizacaoCadastro, Collection<ParametrosTransacaoBatchHelper> colecaoHelper) throws ControladorException;
	
	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 07/02/2012
	 */
	public void efetuarInstalacaoHidrometroAtualizacaoCadastral(Imovel imovel,  ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, Integer medicaoTipo,
			Integer idParametroTabelaAtualizacaoCadastro, HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstHistAtlzCad, boolean funcionalidadeOnline, Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * 
	 * [SB0001 - Gerar Histórico de Instalação do Hidrômetro] 
	 * 
	 * @author Arthur Carvalho
	 * @since 07/03/2012
	 */
	public HidrometroInstalacaoHistorico inserirHidrometroInstalacaoHistorico(HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstHistAtlzCad, Hidrometro hidrometro,
			Imovel imovel ) throws ControladorException;
	
	/**
	 * [UC1291] Efetuar Ligação Água Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 07/02/2012
	 */
	public boolean efetuarLigacaoAguaAtualizacaoCadastral(Imovel imovel, Integer idParametroTabelaAtualizacaoCadastro, 
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, Collection<ParametrosTransacaoBatchHelper> colecaoHelper) throws ControladorException;
	/**
	 * Metodo responsavel por validar os dados do cliente
	 * 
	 *  @author Arthur Carvalho
	 *  @since 19/09/2012
	 *  
	 * @param clienteImovel
	 * @param clienteAtualizacaoCadastral
	 * @param imovelAtualizacaoCadastral
	 * @param parametroTabelaAtualizacaoCadastro
	 * @param validarCliente
	 * @param funcionalidadeOnline
	 * @return
	 * @throws ControladorException
	 */
	public boolean validarClienteAtualizarClienteAtualizacaoCadastral(ClienteImovel clienteImovel, ClienteAtualizacaoCadastral clienteAtualizacaoCadastral, 
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro, boolean validarCliente, 
			boolean funcionalidadeOnline,SistemaParametro sistemaParametro) throws ControladorException;
	
	/**
	 * [UC1299] Atualizar Cliente para Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param imovelAtualizacaoCadastral
	 * @param clienteAtualizacaoCadastral
	 * @param clienteImovel
	 * @throws ControladorException
	 */
	public boolean atualizarClienteAtualizacaoCadastral(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, ClienteAtualizacaoCadastral clienteAtualizacaoCadastral, 
			ClienteImovel clienteImovel, Integer idParametroTabelaAtualizacaoCadastro, boolean funcionalidadeOnline, Imovel imovel) throws ControladorException ;
    /**
	 * [UC0870] - Gerar Movimento de Contas em Cobranca por Empresa
	 *  
	 * Retorna o setor comercial do imovel
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/07/2012
	 * */	
	public Integer pesquisarIdSetorComercialImovel(Integer idImovel) throws ControladorException;
	/**
	 * [UC]
	 * 
	 * @author Maxwell Moreira
	 * @date 22/08/2012
	 * 
	 */
	public void removerEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobranca, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa)
			throws ControladorException;
	
	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * 
	 * [SB0001 - Gerar Histórico de Instalação do Hidrômetro] 
	 * 
	 * @author Arthur Carvalho
	 * @since 07/03/2012
	 */
	public HidrometroInstalacaoHistorico criarObjetoHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstHistAtlzCad,
			Hidrometro hidrometro, Imovel imovel);
	
	/**
	 * [UC 1386] - Processar Arquivo de Resetorização de Imóveis
	 * 
	 * @author Davi Menezes
	 * @since 31/10/2012
	 */
	public void processarArquivoResetorizacaoImoveis(Integer idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * [UC 1387] - Validar Resetorização de Imóveis - Gsan
	 * 
	 * @author Davi Menezes
	 * @since 08/11/2012
	 */
	public void validarResetorizacaoImoveis(Integer idFuncionalidadeIniciada) 
			throws ControladorException;
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0003] Pesquisar Imóvel Apto para Sorteio
	 * 
	 * @author Hugo Azevedo
	 * @date 18/10/2012
	 * 
	 **/
	public Collection pesquisarImovelAptoSorteio(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0004] Pesquisar Dados Cadastro Sorteio
	 * 
	 * @author Hugo Azevedo
	 * @date 18/10/2012
	 * 
	 **/
	public List<RelatorioComprovanteCadastramentoSorteioBean> pesquisarDadosCadastroSorteio(Integer idImovel)  throws ControladorException;
	
	/**
	 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
	 * [IT0005] Obter E-mail cadastrado
	 * 
	 * @author Hugo Azevedo
	 * @date 22/10/2012
	 * 
	 **/
	public String obterEmailCadastrado(Integer idImovel) throws ControladorException; 
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [FE0004] Verificar Imóvel Apto
	 * 
	 * @author Mariana Victor
	 * @date 19/10/2012
	 * 
	 */
	public String verificarImovelApto(CadastrarImovelSorteioActionHelper helper) 
			throws ControladorException;
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0011] Cadastrar o Imóvel para o Sorteio
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 * 
	 */
	public ImovelCadastroSorteio cadastrarImovelParaSorteio(boolean imovelApto, 
			String descricaoDiferenca, CadastrarImovelSorteioActionHelper helper) 
			throws ControladorException;
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0001] Exibir Quantidade Total de Inscritos
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public Integer pesquisarQuantidadeTotalInscritos() throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarImoveisAptoSorteioFiqueLegal() throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarImoveisAptoSorteioMensal() throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarNumeroOrdemSorteioFiqueLegal() throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarNumeroOrdemSorteioMensal() throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB010] Verificar Débitos do Imóvel.
	 * [SB011] Verificar Situação de Ligação do Imóvel.
	 * [SB012] Verificar Categoria Residencial do Imóvel.
	 * 
	 * @author Mariana Victor
	 * @date 25/10/2012
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public boolean verificarImovelAptoSorteioFiqueLegal(Integer idImovel) 
			throws ControladorException;

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
			Integer idPremio, Integer numeroOrdemSorteio) throws ControladorException;


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
			Integer idPremio, Integer numeroOrdemSorteio) throws ControladorException;
	
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * 2.1. Caso o tipo do sorteio seja "Sorteio para Adimplentes":
	 * 
	 * @author Mariana Victor
	 * @data 25/10/2012
	 * 
	 * @param
	 * @return void
	 */
	public Collection<ImovelSorteadoHelper> efetuarSorteioAdimplentes(
		Integer idPremio, Integer quantidadePremio) throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * 2.2.	Caso contrário, caso o tipo do sorteio seja "Sorteio Fique Legal":
	 * 
	 * @author Mariana Victor
	 * @data 25/10/2012
	 * 
	 * @param
	 * @return void
	 */
	public Collection<ImovelSorteadoHelper> efetuarSorteioFiqueLegal(
		Integer idPremio, Integer quantidadePremio) throws ControladorException;
	
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * 2.3.	Caso contrário, ou seja, "Sorteio Mensal":
	 * 
	 * @author Mariana Victor
	 * @data 25/10/2012
	 * 
	 * @param
	 * @return void
	 */
	public Collection<ImovelSorteadoHelper> efetuarSorteioMensal(
		Integer idPremio, Integer quantidadePremio) throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB014] - Emitir Relatório Sorteio Fique Legal
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Collection<RelatorioSorteioPremiosHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioSorteioPremiosHelper> pesquisarDadosRelatorioImoveisSorteadosFiqueLegal()
			throws ControladorException;

	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB0006] - Emitir Relatório Sorteio Mensal
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Collection<RelatorioSorteioPremiosHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioSorteioPremiosHelper> pesquisarDadosRelatorioImoveisSorteadosMensal()
			throws ControladorException;
	
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Date
	 * @throws ControladorException
	 */
	public Date pesquisarDataSorteioFiqueLegal() 
			throws ControladorException;
	
	/**
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @return Date
	 * @throws ControladorException
	 */
	public Date pesquisarDataSorteioMensal() 
			throws ControladorException;
	
	/**
	 * [UC1380] Cadastrar Imóveis para Sorteio
	 * 
	 * [IT0008] Pesquisar Imóvel Cadastrado para Sorteio.
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2012
	 */
	public boolean pesquisarImovelCadastradoSorteio(Integer idImovel) 
			throws ControladorException;
	
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
			throws ControladorException;
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel Atualização Cadastral
	 * 
	 * [IT 0005] - Pesquisar Imóveis Resetorizados
	 * [IT 0008] - Pesquisar Imóveis Não Resetorizados
	 * 
	 * @author Davi Menezes
	 * @date 19/11/2012
	 */
	public Collection<ImoveisRoteiroDispositivoMovelHelper> pesquisarImoveisRoteiroDispositivoMovel(String idLocalidade, 
			String codigoSetorComercial, Collection<Integer> quadrasSelecionadas, 
			Collection<Integer> colecaoLigacaoAguaSituacao, String clienteUsuario, String[] indicadorSituacaoImovel) throws ControladorException;
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 20/11/2012
	 */
	public void gerarRoteiroDispositivoMovel(Integer idFuncionalidadeIniciada, Collection<String> colecaoImoveis, ComandoAtualizacaoCadastralHelper helper)
			throws ControladorException;
	
	/**
	 * [UC 1392] - Consultar Roteiro Dispositivo Movel
	 * 
	 * [IT 0003] - Selecionar Arquivos
	 * 
	 * @author Davi Menezes
	 * @date 27/11/2012
	 */
	public Collection<AtualizacaoCadastralArquivoTextoHelper> pesquisarArquivoRoteiroAtualizacaoCadastral (
			ConsultarRoteiroDispositivoMovelHelper helper) throws ControladorException;
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 *
	 */
	public void atualizarListaAtualizacaoCadastralArquivoTexto(
			Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoAtualizacaoCadastralArquivoTexto,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ControladorException;
	
	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 1. O sistema executa o step "Processar Unificar ID de Clientes com o Mesmo Documento"
	 *  
	 * @author Mariana Victor
	 * @date 21/01/2013
	 * 
	 * @throws ControladorException
	 */
	public void unificarIDClientesComMesmoDocumento(int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
	 * 2. O sistema executa o step "Gerar Relatorio de Clientes com o Mesmo Cpf/Cnpj"
	 *  
	 * @author Mariana Victor
	 * @date 22/01/2013
	 * 
	 * @throws ControladorException
	 */
	public void gerarRelatorioClientesMesmoCpfCnpj(int idFuncionalidadeIniciada, Usuario usuario) 
			throws ControladorException;

	/**
	 * [UC1393] Processar Requisições do Dispositivo Móvel Atualização Cadastral.
	 * 
	 * Recebe o dados de apenas um imóvel.
	 * 
	 * @author Rafael Pinto
	 * @date 20/03/2013
	 *  
	 * @param buffer BufferedReader
	 * @throws ControladorException
	 */	
	public void atualizarMovimentoImovelAtualizacaoCadastral(BufferedReader buffer) throws ControladorException;
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [IT0005] Pesquisar Dados Registro Imóvel
	 * [IT0006] Obter Dados Registro Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 19/03/2013
	 */
	public Collection<RegistroImovelHelper> obterDadosRegistroImovel(Integer idImovel) throws ControladorException;
	
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 07/03/2013
	 */
	public void inserirFotoAtualizacaoCadastral(String codigo, int tipoFoto,byte[] foto) throws ControladorException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCadastral(String idEmpresa, String idLocalidade) 
			throws ControladorException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade) throws ControladorException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroBairroAtlzCad(String idLogradouro) throws ControladorException;
	
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroCepAtlzCad(String idLogradouro) throws ControladorException;
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * @author Anderson Cabral
	 * @since 18/03/2013
	 * 
	 * @param idParametro
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer>  pesquisarRoteiroQuadra(Integer idParametro) throws ControladorException;
	
	
	/**
	 * @author Arthur Carvalho
	 * @date 22/05/2013
	 * 
	 * @param helper
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer>  pesquisarImovelPPP(DadosContratoPPPHelper helper) throws ControladorException;
	/**
	 * @author Arthur Carvalho
	 * @date 22/05/2013
	 * 
	 * @param helper
	 * @return
	 * @throws ControladorException
	 */
	public void  atualizarSituacaoTransmissaoImovel(Integer idComando) throws ControladorException;
	
	/**
	 * [UC1447] - Consultar Imóveis no Ambiente Pré-GSAN
	 * 
	 * Metodo responsavel por atualizar o novos imoveis de atualizacao cadastral com a matricula de imovel que ja exista no gsan.
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public void associarImovelNovoAImovelExistenteAtualizacaoCadastral(DadosImovelPreGsanHelper helper) throws ControladorException ;
	
	/**
	 *  [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * 
	 * @author Maxwell Moreira
	 * @since 20/06/2013
	 * 
	 * @param idParametro
	 * @return Retorna um Collection<Integer> com os numeros de imoveis
	 * @throws ControladorException
	 */
	public Collection<Integer>  pesquisarImoveisAtualizacaoCadastral(Integer idParametro) throws ControladorException;

	
	/**
	 * Metodo responsavel por atualizar os imovels com os subsistemas informados no arquivo - compesa.
	 * @author Arthur Carvalho
	 * 
	 * @throws ControladorException
	 */
	public void atualizarSubsistemaImovel(Integer idFuncionalidadeIniciada) throws ControladorException ;
	
	/**
	 * Metodo responsavel por pesquisar os usuários da atualização cadastral
	 * @author Vivianne Sousa
	 * @date 22/07/2013
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarUsuarioAtuCadastral(Integer idEmpresa) throws ControladorException;
	
	/**
	 * 
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
	 * @author Arthur Carvalho
	 * 
	 * @param relatorioHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection<RelatorioResumoImoveisAlteracaoInscricaoViaBatchBean> pesquisarResumoImoveisAlteracaoInscricaoViaBatch(FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper 
			relatorioHelper) throws ControladorException;
	
	/**
	 * [UC0011] Inserir Imovel
	 * [FS0025] Validar Número do Medidor de Energia
	*/
	public String pesquisarMedidorEnergia(String numeroMedidorEnergia,Integer idImovel)
			throws ControladorException;
	
	/**
	 * 
	 * [UC1533] Filtrar Ordem Servico Conexao Esgoto
	 * @author Hugo Azevedo
	 * @date 02/08/2013
	 * 
	 */
	public Municipio pesquisarMunicipio(Integer idMunicipio) throws ControladorException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Collection<ImovelSorteadoHelper> efetuarSorteioFiqueLegal2013(Integer idPremio,
		Integer quantidadePremio) throws ControladorException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public boolean pesquisarPremioPrincipal(Integer idPremio)
		throws ControladorException;

	
	/**
	 * [UC 1392] Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT 0006] Exibir Dados Cadastrador
	 */
	public Collection<DadosCadastradorHelper> pesquisarDadosCadastrador(Integer idParametroAtualizacaoCadastral)
			throws ControladorException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 29/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return 
	 * @throws ControladorException
	 */
	public void atualizarDadosFoneClienteAtual(Integer idClienteAtualizacaoCadastral, Integer idCliente, ImovelAtualizacaoCadastral imovelAtualizacaoCadastral) throws ControladorException;
	
	
	
	/**
	  * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	  *  
	  * [SBXXXX] - Inserir Registro no retorno da Atualizacao Cadastral
	  *  
	  * @author: Arthur Carvalho
	  * @date: 01/03/2012 
	  */
	public void inserirRetornoAtualizacaoCadastral(Integer idImovel, Integer idCliente, 
			Integer idAtributoAtualizacaoCadastral, Short codigoSituacao,
			Integer idMensagemAtualizacaoCadastral, Integer idParametroTabelaAtualizacaoCadastro,
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, Integer idMedicaoTipo) 
					throws ControladorException ;
	
	/**
	 * [UC1560] - Validar Dados dos Endereços Enviados pelo GEO
	 * 
	 * @author Anderson Cabral
	 * @date 19/09/2013
	 */
	public void validarDadosDoEnderecoEnviadosPeloGEO(Integer idFuncionalidadeIniciada) throws ControladorException;
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public void atualizarSorteioFiqueLegal2013(Integer idPremio,
			Integer numeroOrdemSorteio,Integer idImovelCadastroSorteio)
		throws ControladorException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Collection<RelatorioSorteioPremiosHelper> obterDadosRelatorioSorteioFiqueLegal2013(String numeroSorteio)
		throws ControladorException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Date obterDataSorteioFiqueLegal2013() 
			throws ControladorException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 30/09/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Collection<PremioSorteio> obterNumeroSorteioFuqueLegal2013(Integer 
		idSorteio) throws ControladorException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 01/10/2013
	 * [UC1295] Efetuar Sorteio Premio
	 */
	public Integer pesquisarNumeroOrdemSorteioFiqueLegal2013() 
			throws ControladorException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * 
	 * @author Anderson Cabral
	 * @since 27/09/2013
	 */
	public ArrayList<ImovelEnderecoArquivoTextoHelper> pesquisarLogradourosParaAtualizar(Integer idMunicipio) throws ControladorException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0004] - Pesquisar Quantidade de Imóveis Transferidos 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public Integer pesquisarQtdeImoveisTransferidos(Integer idLogradouro, Integer idBairro) throws ControladorException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0003] - Pesquisar Quantidade de Imóveis Total 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public Integer pesquisarQtdeTotalImoveis(Integer idLogradouro, Integer idBairro) throws ControladorException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [SB0001] - Gerar relatório 
	 * 
	 * @author Anderson Cabral
	 * @since 26/09/2013
	 */
	public ArrayList<ImovelEnderecoArquivoTexto> pesquisarTotalImoveisArquivo(Integer idLogradouro, Integer idBairro) throws ControladorException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * [IT0004] - Pesquisar Imóveis Transferidos 
	 * 
	 * @author Anderson Cabral
	 * @since 30/09/2013
	 */
	public ArrayList<Integer> pesquisarImoveisTransferidos(Integer idLogradouro, Integer idBairro) throws ControladorException;
	
	/**
	 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
	 * Pesquisa Imovel Endereco Arquivo Texto
	 * 
	 * @author Anderson Cabral
	 * @since 30/09/2013
	 */
	public ImovelEnderecoArquivoTexto pesquisarImovelEnderecoArquivo(Integer idArquivo) throws ControladorException;		

	
	/**
	 * @author Anderson Cabral
	 * @since 17/10/2013
	 */
	public boolean verificarCadastradorComArquivoEmCampo(Integer idCadastrador)  throws ControladorException;
	
	/**
	 * 
	 * @param helper
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarInscricaoInformadaValida(DadosImovelPreGsanHelper helper)  throws ControladorException;

	
	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Pesquisa o email da Empresa 
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2011
	 */
	public String pesquisarEmailEmpresa(Integer idEmpresa)throws ControladorException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 10/12/2013
	 * 
	 * @param imovel
	 * @param numeroImovel
	 * @param complemento
	 * @param logradouroBairro
	 * @param logradouroCep
	 */
	public void atualizarImovelLiberarLogradouros(Imovel imovel, String numeroImovel, String complemento, LogradouroBairro logradouroBairro, LogradouroCep logradouroCep, Usuario usuario,
			Logradouro logradouro) 
			throws ControladorException;
	
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
	public ArrayList<Integer> pesquisarImovelAtualizacaoCadastralPorLogradouro(Integer idLogradouro ) throws ControladorException;
	
	
	/**
	 * [UC1583] Relatório de Análise das Inconsistências da Atualização Cadastral
	 * 
	 * @author Hugo Azevedo
	 * @since 20/02/2014
	 * 
	 * @param Helper
	 * @return Collection<RelatorioAnaliseInconsistenciasAtualizacaoCadastralBean> 
	 */
	public Collection<RelatorioAnaliseInconsistenciasAtualizacaoCadastralBean> 
									obterRelatorioAnaliseInconsistenciasAtualizacaoCadastral(DadosResumoMovimentoAtualizacaoCadastralHelper helper);

	/**
	 * @author Jonathan Marcos
	 * @date 15/04/2014
	 * @param imac
	 */
	public ArrayList<CadastroOcorrencia> obterOcorrenciasImovelAtualizacaoCadastral(Integer imac)
		throws ControladorException;
}
