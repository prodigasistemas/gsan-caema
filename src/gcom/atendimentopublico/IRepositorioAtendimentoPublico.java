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
package gcom.atendimentopublico;

import gcom.atendimentopublico.bean.MensagemSmsHelper;
import gcom.atendimentopublico.bean.PesquisaSatisfacaoHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteFoneVisitaCampo;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteVisitaCampo;
import gcom.atendimentopublico.ordemservico.ClieFoneSeletivaVisitaCampo;
import gcom.atendimentopublico.ordemservico.ClieOsSeletivaVisitaCampo;
import gcom.atendimentopublico.ordemservico.ComandoOSConexaoEsgoto;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.bean.ObterValorDebitoHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoDevolucaoValores;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSuprimido;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.cobranca.bean.OrdemServicoContaHelper;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioDebitosCobrancaImovelHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioTiposServicoHelper;
import gcom.gui.relatorio.atendimentopublico.RelatorioAcompanhamentoOSCobrancaSmartphoneHelper;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.atendimentopublico.FiltrarRelatorioComandosConexaoEsgotoHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarRelatorioReligacaoClientesInadiplentesHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Interfe, que disponibiliza os servi�os do Reposit�rio Atendimento P�blico 
 *
 * @author Leandro Cavalcanti
 * @date 10/07/2006
 */
public interface IRepositorioAtendimentoPublico {
	
	/**
	 * [UC-0355] - Efetuar Corte de Liga�� de �gua
	 * [SB001] Atualizar Hidrometro - (corte de liga��o de �gua)
	 * Atualizar um os campos lastId,dataUltimaAtualiza��o do imovel na base
	 * 
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarLigacaoAgua(Integer idImovel, Integer idLigacaoAguaSituacao, Integer numeroSeloCorte ) throws ErroRepositorioException;
	/**
	 * [UC-0355] - Efetuar Corte de Liga�� de �gua
	 * [SB001] Atualizar Hidrometro - (corte de liga��o de �gua)
	 * Atualizar os campos hidi_nnleituracorte e hidi_tmultimaalteracao de HidrometroInstalacaoHistorico
	 *			
	 *
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarHidrometroLIgacaoAgua(Integer imovelId,Integer numeroLeituraCorte) throws ErroRepositorioException ;
		
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB002] Atualizar Liga��o de �gua 
	 * Atualizar os campos hidi_id e lagu_tmultimaalteracao de LigacaoAgua
	 * 		
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 13/07/2006
	 *
	 * @param idLigacaoAgua
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroInstalacaoHistoricoLigacaoAgua(Integer idLigacaoAgua, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException;
	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB002] Atualizar Im�vel
	 * Atualizar os campos hidi_id e imov_tmultimaalteracao de Imovel
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 13/07/2006
	 *
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroIntalacaoHistoricoImovel(Integer idImovel, Integer idHidrometroInstalacaoHistorico, Integer idPocoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB003] Atualizar Hidr�metro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoHidrometro(Integer idHidrometro, Integer situacaoHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC0396] - Inserir Tipo de retorno da OS Referida
	 * 
	 * [FS0005] Validar indicador de deferimento
	 * 
	 * @author lms
	 * @date 31/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public int consultarTotalIndicadorDeferimentoAtivoPorServicoTipoReferencia(OsReferidaRetornoTipo osReferidaRetornoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0463] Atualizar Consumo M�nimo da Liga��o de �gua
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarConsumoMinimoLigacaoAgua(LigacaoAgua ligacaoAgua) throws ErroRepositorioException;	
	

	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB003] Atualizar Hidr�metro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarLocalArmazanagemHidrometro(Integer idHidrometro,Integer localArmazanagemHidrometro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB003] Atualizar Hidr�metro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroInstalacoHistorico(HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico) 
		throws ErroRepositorioException;	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Verificar exist�ncia de hidr�metro na liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Verificar exist�ncia de hidr�metro no im�vel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Capacidade de Hidr�metro pela Liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Capacidade de Hidr�metro pelo Im�vel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Valor do Debito pelos par�mtros passados.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param obterValorDebitoHelper
	 * @return o valor do d�bito 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorDebito(ObterValorDebitoHelper params) throws ErroRepositorioException;	
	
	/**
	 * M�todo que retorna o n�mero do hidr�metro da liga��o de �gua
	 * 
	 * @author Ana Maria
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */

	public String pesquisarNumeroHidrometroLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o tipo da liga��o de �gua e a data do corte da liga��o de �gua
	 * 
	 * @author Ana Maria
	 * @date 18/08/2006
	 * 
	 * 
	 * @param idLigacaoAgua
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException;
	
	/**
	 * Consulta os dados das ordens de servi�o para a gera��o do relat�rio
	 * 
	 * @author Rafael Corr�a
	 * @created 07/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoProgramacaoRelatorio(Integer idEquipe, Date dataRoteiro)
			throws ErroRepositorioException;
	
	/**
	 * [UC0404] Manter Especifica��o da Situa��o do Imovel
	 * 
	 * Este caso de uso remove a especifica��o e os crit�rio
	 * 
	 * [SB0002] Remover Especifica��o da situacao
	 * 
	 * @author Rafael Pinto
	 * @created 08/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void removerEspecificacaoSituacaoImovelCriterio(String[] idsEspecificacaoSituacaoImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os ids das situa��es de liga��o de �gua.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoAgua() throws ErroRepositorioException ;
	
	
	/**
	 * Pesquisa todos os ids das situa��es de liga��o de esgoto.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoEsgoto() throws ErroRepositorioException ;
	
	/**
	 * 
	 * Este cso de uso permite efetuar a liga��o de �gua e eventualmente 
	 * a instala��o de hidr�metro, sem informa��o de RA sendo chamado direto pelo menu.
	 *
	 * [UC0579] - Efetuar Liga��o de �gua com Intala��o de Hidr�metro
	 *
	 * @author Fl�vio Leonardo
	 * @date 25/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEfetuarLigacaoAguaHidrometroSemRA(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0XXX] Gerar Contrato de Presta��o de Servi�o
	 * 
	 * @author Rafael Corr�a
	 * @date 03/05/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosContratoPrestacaoServico(
			Integer idImovel) throws ErroRepositorioException;
	
	public void atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(Integer idImovel, Integer idHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Obt�m os dados necess�rio da liga��o de �gua, de esgoto e do hidr�metro
	 * instalado na liga��o de �gua
	 * 
	 * @author Rafael Corr�a
	 * @date 17/05/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosLigacaoAguaEsgoto(
			Integer idImovel) throws ErroRepositorioException;
	
//	*********************************************************
	//****************CONTRATO PESSOA JURIDICA*****************
	
	public Cliente pesquisaClienteContrato(Integer idCliente) throws ErroRepositorioException;
	
	public ClienteImovel pesquisarDadosContratoJuridica(Integer idImovel) throws ErroRepositorioException;
	
	public String pesquisarMunicipio(Integer idImovel) throws ErroRepositorioException;
	
	//*********************************************************

	/**
	 * Substituicao de hidrometro
	 */
	public void atualizarSubstituicaoHidrometroInstalacoHistorico(
			HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico)
			throws ErroRepositorioException;
	
	/**
	 * [UC0482]Emitir 2� Via de Conta
	 *obter numero do hidr�metro na liga��o de �gua.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2007
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public String obterNumeroHidrometroEmLigacaoAgua(Integer imovelId)
			throws ErroRepositorioException;
	
	/**
	 * [UC0738] Retorna as informa��es para o relat�rio de certid�o negativa
	 * @param imo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioCertidaoNegativa( Imovel imo )
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados necess�rios para a gera��o do relat�rio
	 * 
	 * [UC0864] Gerar Certid�o Negativa por Cliente
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioCertidaoNegativaCliente(Collection<Integer> idsClientes)
			throws ErroRepositorioException;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0004] - Cliente n�o associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cpf
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComCPF(Integer idImovel, String cpf)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0004] - Cliente n�o associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cnpj
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComCNPJ(Integer idImovel, String cnpj)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0482] Emitir 2a Via Conta
	 *
	 * [FS0002] - Cliente sem documento
	 *
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 *
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComDocumentoInformado(Integer idImovel)
		throws ErroRepositorioException ;
	
	 /**
     * [UC0150] Retificar Conta
     * @author Vivianne Sousa
     * @date 26/11/2008
     */
    public BigDecimal obterPercentualAguaConsumidaColetadaImovel(Integer idImovel)
            throws ErroRepositorioException ;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
    public Collection<AutosInfracao> pesquisarAutoInfracaoRecursoVencido(Integer idSituacaoAutoInfracao,Date prazoEntregaRecursoVencido)
            throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * [SB0001] Atualizar Autos Infra��o
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
    public void atualizarAutosInfracao(Collection idsAutosInfracao,
    		Integer idSituacaoAutoInfracao) throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
    public Collection<FiscalizacaoSituacaoServicoACobrar> pesquisarFiscalizacaoSituacaoServicoACobrar(Integer idFiscalizacaoSituacao)
            throws ErroRepositorioException;
    
    /**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
    public Collection<Object[]> pesquisarImoveisBatchEmitirOrdemFiscalizacao(
    		Integer idSetorComercial,Date data,Integer quantidadeInicio, Integer quantidadeMaxima)throws ErroRepositorioException;
    
	/**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 * 	Inseri objeto na base do tipo ImovelSuprimido
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void inserirImovelSuprimido(ImovelSuprimido imovelSuprimido)throws ErroRepositorioException;
	
	 /**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 * 	Pesquisas linhas do txt
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public Collection<ImovelSuprimido> pesquisarDadosEmitirArquivoTextoDeOrdemFiscalizacao(
			Integer quantidadeInicio, Integer quantidadeMaxima)
			throws ErroRepositorioException;
	
	
	/**
	 * [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo servi�o, e retorna uma cole��o. 
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Collection<ServicoCobrancaValor> pesquisarServicoCobrancaValorUltimaVigencia(Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo servi�o, e retorna o total.   
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Integer pesquisarServicoCobrancaValorUltimaVigenciaTotal() throws ErroRepositorioException;
	
	/**
	 * [SB0002] � Replicar os Valores de Cobran�a de Servi�o existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo Cobran�a, e retorna uma cole��o. 
	 * 
	 * @author Hugo Leonardo
	 * @date 23/04/2010
	 */
	public Collection<ServicoCobrancaValor> replicarServicoCobrancaValorUltimaVigencia(String[] selecionados) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0391] Inserir valor cobran�a Servi�o.
	 * 
	 * Verificar se existe vig�ncia j� cadastrada para o Servi�o Tipo.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idServicoTipo
	 * @param opcao
	 * @throws ErroRepositorioException
	 * @data 03/05/2010
	 * 
	 * return String
	 * 
	 * @see Caso a opcao = 1 - verifica as situa��es de inserir e atualizar Servi�o Tipo.
	 * @see Caso a opcao = 2 - verifica a situa��o de retificar Servi�o Tipo.
	 */
	public String verificarExistenciaVigenciaServicoTipo(String dataVigenciaInicial, String dataVigenciaFinal, Integer idServicoTipo)
		throws ErroRepositorioException;	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] � Verificar RA de urg�ncia
	 * 
	 * Verifica se o Registro de Atendimento tem o nivel selecionado como Urg�ncia
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   03/06/2010
	 * 
	 * 
	 */
	public Integer verificarRegistroAtendimentoUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] � Verificar RA de urg�ncia
	 *  
	 * Retorna um ou todos usu�rios da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usu�rio 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] � Verificar RA de urg�ncia
	 *  
	 * Retorna um ou todos usu�rios da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usu�rio 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarVisualizacaoRaUrgencia(Integer idRegistroAtendimento, Integer idUnidade, Integer idUsuario)
		throws ErroRepositorioException;
		
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] � Verificar RA de urg�ncia
	 * 
	 * Verifica se o Registro de Atendimento j� est� relacionado a uma Unidade informada.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   05/06/2010
	 * 
	 */
	public Integer verificarUsuariosRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, Integer idUnidade)
		throws ErroRepositorioException;
	
	
	/**	 
	 * [UC1028] Exibir Registro Atendimento Urg�ncia
	 *  
	 * Verifica se o Usuario possui algum Registro de Atendimento urgente.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   07/06/2010
	 * 	  
	 */
	public Collection verificarUsuarioRegistroAtendimentoUrgencia(Integer idUsuario)
		throws ErroRepositorioException;
	
	
	/**  
	 * Atualiza um ou v�rios campos da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usu�rio, indicador Tramite e indicador Visualizacao 
	 * @throws ErroRepositorioException
	 * @data   10/06/2010
	 * 
	 */
	public void atualizarUsuarioRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, String idUsuarios, Integer idUsuario, Integer indicadorTramite, Integer indicadorVisualizacao)
            throws ErroRepositorioException;
	
    /**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Hugo Amorim
	 * @date 15/07/2010
	 */
	public Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> 
		pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando, Collection idsSituacos)throws ErroRepositorioException;
	
	/**
	 * [UC1056] ? Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRAAnalitico( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 30/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer countPesquisarRelatorioAcompanhamentoRAAnalitico( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 01/10/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoAberto( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 01/10/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoEncerrado( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 27/12/2010
 	 * 
 	 * @param idRepavimentadora, idPavimento, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return boolean
	 */
	public boolean verificaRemoverCustoPavimentoPorRepavimentadora(Integer idRepavimentadora,
			Integer idPavimento, Integer indicadorPavimento)throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 28/12/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return Integer
	 */
	public Integer verificaAtualizarCustoPavimentoPorRepavimentadora(Integer idAtualizacao, Integer idRepavimentadora,
			Integer idPavimento, Date dataInicio, Date dataFinal, Integer indicadorPavimento, Integer tipo)
		throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * 		[FS0010] Verificar se existem dias sem valor
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 11/01/2011
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return Integer
	 * 
	 * VerificarExistenciDiasSemValor
	 */
	public Integer verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, 
			Date dataFinal, Integer indicadorPavimento, Integer tipo) throws ErroRepositorioException;
	
	/**
     * [UC0412] Manter Tipo de Servi�o
     * 
     * @author Vivianne Sousa
     * @created 07/01/2011
     */
    public void removerServicoTipoBoletim(Integer idServicoTipo)
            throws ErroRepositorioException ;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesOS( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 28/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( 
			Integer os, Integer imovel, Date dataEncerramentoOS, Integer tipo) throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 31/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( Collection<Integer> idsOS) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 01/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesRecorrentes( 
			Integer imovel, FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 09/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesDatasOS( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper, Integer imovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 16/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRecorrenciaPorUsuarioQueAbriuOuEncerrouOS( 
			Integer usuario, FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobran�a
	 * 
	 * Consulta chamada pelo "[FS0008 � Validar Motivo Encerramento]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Boolean verificarAtendimentoMotivoEncerramento(Integer idMotivoEncerramento) throws ErroRepositorioException;
	
	/**
	 * [UC1177] Gerar Relat�rio de Ordens de Servi�o por Situa��o
	 * 
	 * O segundo par�metro (boletimGerado) � um booleano que
	 * indica se para um dado grupo de cobran�a e um m�s referencia
	 * foi gerado um boletim de medi��o.
	 * 
	 * @author Diogo Peixoto
	 * @date 09/06/2011
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 * @param boletimGerado
	 * @return Collection<FiltrarRelatorioOSSituacaoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioOSSituacao(FiltrarRelatorioOSSituacaoHelper helper, boolean boletimGerado)
		throws ErroRepositorioException;
	
	
	/**
     * Obt�m a cole��o de perfis de tipo de servi�o para OS.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	public Collection obterColecaoTipoOSgerada() throws ErroRepositorioException;
	
	
	/**
     * Obt�m a cole��o de OS a partir dos par�metros passados pela funcionalidade de Acompanhamento de Cobran�a por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterColecaoImovelOSCobrancaResultado(String[] categoriaImovel,
			  String[] perfilImovel,
			  String[] gerenciaRegional,
			  String[] unidadeNegocio,
			  String idLocalidadeInicial,
			  String idLocalidadeFinal,
			  String idSetorComercialInicial,
			  String idSetorComercialFinal,
			  String idQuadraInicial,
			  String idQuadraFinal,
			  String tipoServico,
			  String comando) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * O segundo par�metro (relatorioDefinitivo) � um booleano que
	 * indica se o relat�rio � definitivo ou n�o, pois o resultado
	 * da query � diferente para os relat�rios definitivos e os
	 * de simula��o
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicao(
			FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro, boolean relatorioDefinitivo) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * M�todo que vai retornar as quantidades acumuladas e os valores acumulados
	 * no per�odo para gera��o do relat�rio de acompanhamento do boletim de medi��o.
	 * 
	 * @author Diogo Peixoto
	 * @date 01/08/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicaoAcumuladas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * O segundo par�metro (relatorioDefinitivo) � um booleano que
	 * indica se o relat�rio � definitivo ou n�o, pois o resultado
	 * da query � diferente para os relat�rios definitivos e os
	 * de simula��o
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<BigDecimal> filtrarRelatorioAcompanhamentoBoletimMedicaoPenalidades(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro,
			boolean relatorioDefinitivo) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * @author Diogo Peixoto
	 * @date 01/08/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return Taxa de Sucesso do Boletim de Medi��o
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarTaxaSucessoBoletimMedicao(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ErroRepositorioException;
	
	
	/**
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
	 * Pesquisar as Ordens de servi�os a partir de seu im�vel e tipo de servi�o
	 * 
	 * @author Hugo Azevedo
	 * @data 14/01/2011
	 */
	
	public Collection obterOSImovelTipoServico(Integer id, Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
     * Obt�m a quantida de OS a partir dos par�metros passados pela funcionalidade de Acompanhamento de Cobran�a por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterTotalOSColecaoImovelTipoServico(Collection colecaoImovel,Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1189] Inserir Registro de Atendimento Loja Virtual
	 * 
	 * @author Magno Gouveia
	 * @date 12/07/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarSolicitacaoTipoLojaVirtual() throws ErroRepositorioException;
	
	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0001] Selecionar Munic�pios da Regi�o
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @return colecaoDeMunicipios
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMunicipiosLojaVirtualCompesa() throws ErroRepositorioException;

	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0005] Exibir Dados da Loja
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @param id do municipio
	 * @return colecaoDeLojas
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarLojasDeAtendimentoLojaVirtualCompesa(Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * M�todo que vai retornar um booleano indicando se o relat�rio de acompanhamento de boletim
	 * medi��o � definitivo ou n�o definitivo.
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean gruposIniciaodsJaForamEncerrados(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro)	throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Penalizadas para determinado boletim de medi��o
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSPenalizadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Executadas para determinado boletim de medi��o
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSExecutadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	public Collection obterColecaoOSFiscalizacaoNaoExecutadas() throws ErroRepositorioException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * [SB0003] � Pesquisar Fotos da OS
	 * 
	 * M�todo que vai retornar as fotos de uma determinada
	 * ordem de servi�o passada no par�metro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Ordem de Servi�o
	 * 
	 * @return Collection<Object[]> - Cole��o das Fotos da OS
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServico(Integer idFoto) throws ErroRepositorioException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * [SB0003] � Pesquisar Fotos da OS
	 * 
	 * M�todo que vai retornar as fotos de uma determinada
	 * ordem de servi�o passada no par�metro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Foto da Ordem de Servi�o
	 * 
	 * @return Collection<Object[]> - Foto da Ordem de Servi�o
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServicoPorIdFoto(Integer idFoto) throws ErroRepositorioException;
	
	/**
	 * M�todo que pesquisa as ordens seletivas para um
	 * determinado comando
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * [SB 0001] Consultar Ordem de Servi�o Seletiva
	 * @author Raimundo Martins
	 * @date 15/09/2011
	 * 
	 * @return cole��o das ordens de servi�os ses
	 */
	
	public Collection<Object[]> consultarOrdemServicoSeletiva(String comOrdemSeletiva, String local, String setorInicial, 
			String setorFinal, String quadraIni, String quadraFin) throws ErroRepositorioException;
	
	/**
	 * M�todo que verifica a exist�ncia do arquivo
	 * texto para aquele comando, localidade,
	 * setor comercial inicial, setor comercial final
	 * quadra inicial e quadra final	 * 
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * [FS 0005] Gera��o do Arquivo Texto
	 * @author Raimundo Martins
	 * @date 22/09/2011
	 * 
	 * @return true se tiver o arquivo, false sen�o
	 */
	public Boolean verificarExistenciaArquivoTextoVisitaCampo(String comOrdemSeletiva, String local, String setorInicial, 
			String setorFinal, String quadraIni, String quadraFin) throws ErroRepositorioException;

	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 *  1. O sistema exibe as informa��es do arquivo texto;
	 * 
	 * @author Mariana Victor
	 * @date 22/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosArquivoTextoOSVisita(Integer idArquivoTexto) throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 *  2.	O sistema dever� exibir a lista das ordens de servi�o associadas ao arquivo texto 
	 * 
	 * @author Mariana Victor
	 * @date 22/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosOrdensServicoArquivoTextoVisitaCampo(Integer idArquivoTexto) throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 *  Pesquisa o id do im�vel associado � ordem de servi�o
	 * 
	 * @author Mariana Victor
	 * @date 23/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdImovelAssociadoAOrdemVisitaCampo(Integer idOrdemServico) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Servi�o
	 *  3. Anormalidade Registrada 
	 *  4. Anormalidade Encontrada 
	 *  5. Tipo de Pavimento de Cal�ada 
	 *  6. Tipo de Pavimento de Rua 
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosAnormalidadePavimentoOSVisitaCampo(Integer idOrdemServico) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Servi�o
	 *  7.1.1. O sistema dever� exibir todas as a��es para corre��o das anormalidades encontradas 
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarAcoesParaCorrecaoAnormalidadesEncontradas(
		Integer idArquivoTextoRetornoVisitaCampo) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Servi�o
	 * 8.2.	Para as a��es selecionadas e com ordem de servi�o gerada, o sistema dever� atualizar a tabela de retorno
	 * 8.3.	Para as a��es n�o selecionadas, o sistema dever� atualizar a tabela de retorno  
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarArquivoTextoRetornoAcaoVisitaCampo(Integer idArquivoTexto, Integer idServicoTipo,
			Short indicadorGerada) throws ErroRepositorioException;

	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Servi�o
	 *  9. Caso o usu�rio n�o selecione a��es da tabela e selecione a op��o de atualizar a ordem de servi�o de 
	 *  visita em campo, o sistema dever� atualizar a ordem de servi�o de visita de campo  como  conferida
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarOSVisitaCampoConferida(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdComandoOSVisita(Integer idArquivoTexto) throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * 2. O sistema recupera os dados do cliente inicialmente enviados 
	 *  para as equipes de campo para atendimento da ordem de servi�o informada 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public ClieOsSeletivaVisitaCampo pesquisarClieOsSeletivaVisitaCampo(
			Integer idOrdemServico) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * 3. O sistema recupera os dados de retorno da visita de campo transmitidos pelo dispositivo m�vel
	 *  para atualiza��o do cliente associado ao im�vel da ordem de servi�o informada 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public ArquivoTextoRetornoClienteVisitaCampo pesquisarArquivoTextoRetornoClienteVisitaCampo(
			Integer idOrdemServico) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * [SB0002] - Inserir Novo Cliente 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public ArquivoTextoRetornoClienteFoneVisitaCampo pesquisarArquivoTextoRetornoClienteFoneVisitaCampo(
			Integer idArqTxtClie) 
		throws ErroRepositorioException;
	

	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * [SB0006] - Atualizar Movimento do Cliente 
	 *  1. O sistema atualiza a tabela de movimento do cliente transmitida do dispositivo m�vel 
	 *  
	 * @author Mariana Victor
	 * @date 28/09/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarMovimentoCliente(Integer idArquivoTexto, Short indicadorAtualizado, String descricaoMensagem) 
			throws ErroRepositorioException;
	

	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * [SB0007] - Atualizar Movimento do Fone do Cliente 
	 *  1. O sistema atualiza a tabela de movimento de fone do cliente transmitida do dispositivo m�vel 
	 *  
	 * @author Mariana Victor
	 * @date 28/09/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarMovimentoFoneCliente(Integer idArquivoTextoFoneCliente) 
			throws ErroRepositorioException;
	
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * @author Mariana Victor
	 * @data 29/09/2011
	 */
	public ClieFoneSeletivaVisitaCampo pesquisarClieFoneSeletivaVisitaCampo(
			Integer idClieOSVisitaCampo) 
		throws ErroRepositorioException;
	

	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * [SB0004] - Atualizar Dados do RG do Cliente Atual
	 *  
	 * @author Mariana Victor
	 * @date 29/09/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarDadosRGCliente(Integer idCliente, String rg, 
			Integer orgaoExpedidorRg, Integer unidadeFederacaoRg) 
			throws ErroRepositorioException;
	
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * [SB0005] - Atualizar Dados do Fone do Cliente Atual
	 *  
	 * @author Mariana Victor
	 * @date 29/09/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarDadosFoneCliente(Integer idClienteFone, String dddFone, 
			String numeroFone, String ramalFone) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * @author Mariana Victor
	 * @date 04/10/2011
	 * 
	 * @param Integer
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosOSVisitaCampo(Integer idOrdemServico) 
			throws ErroRepositorioException;
	

	/**
	 * [UC1254] Relat�rio Ordem de Servi�o com valores de cobran�a
	 * @author Am�lia Pessoa
	 * @date 24/11/2011
	 */
	public Collection<Object[]> filtrarRelatorioDebitosCobradosImovel(
			FiltrarRelatorioDebitosCobrancaImovelHelper filtro) throws ErroRepositorioException;
	
	/**
	 * [UC1254] Relat�rio Ordem de Servi�o com valores de cobran�a
	 * @author Am�lia Pessoa
	 * @date 24/11/2011
	 */
	public Collection<Object[]> filtrarRelatorioTiposServico(
			FiltrarRelatorioTiposServicoHelper filtro) throws ErroRepositorioException;
	

	/**
	 * [UC1249] Filtro OS Encerradas por Acompanhamento de Servi�o
	 * [SB0010] � Pesquisar Fotos do RA
	 * 
	 * M�todo que vai retornar as fotos de um determinado
	 * registro de atendimento passada no par�metro.
	 * 
	 * @author Fernanda Almeida
	 * @date 17/11/2011
	 * 
	 * @param Integer - ID do Registro Atendimento
	 * 
	 * @return Collection<Object[]> - Cole��o das Fotos da OS
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosRA(Integer id)
			throws ErroRepositorioException;
	
	/**
	 * Verifica se o Registro de Atendimento pode cancelar d�bitos
	 * 
	 * @author Am�lia Pessoa
	 * @date 06/12/2011
	 * @param idRegistroAtendimento
	 * @return boolean
	 * RM 5759
	 */
	public boolean verificarPermissaoRACancelamentoDebito(
			int idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * RM1165 - Registrar em tabela os par�metros que foram utilizados para calcular o valor do 
	 *          d�bito a cobrar gerado decorrente da situa��o de fiscaliza��o informada
	 * UC0210 - Consultar d�bito a cobrar
	 * Analista: Cl�udio Lira
	 * 
	 * SB0001- Exibir Par�metros de C�lculo
	 * 
	 * Pesquisar na tabela atendimentopublico.fiscaliz_param_calc_deb com dbac_id = dbac_id
     * recebido
	 * 
	 * @autor Th�lio Ara�jo
	 * @since 13/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public FiscalizarParametroCalculoDebito pesquisarParametroCalculoDebito(Integer idDebitoACobrar)
			throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por pesquisar quantidade todos os acessos realizados no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeAcessoLojaVirtual(Date periodoInicial , Date periodoFinal , String tipoAtendimento) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por pesquisar quantidade todos os acessos realizados no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarAcessoLojaVirtualTipoAtendimento(Date periodoInicial , Date periodoFinal , String tipoAtendimento, int numeroPagina,String indicadorServicoExecutado) 
		throws ErroRepositorioException;
	
	/**
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarAcessoLojaVirtual(Date periodoInicial , Date periodoFinal, String tipoAtendimento,String indicadorServicoExecutado ) 
		throws ErroRepositorioException ;
	
	/**
	 * Metodo responsavel por pesquisar quantidade de acessos realizados no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeAcessoLojaVirtualTipoAtendimento(Date periodoInicial , Date periodoFinal, String tipoAtendimento ,String indicadorServicoExecutado) throws ErroRepositorioException ;
	
	
	/**
	 * 
	 * M�todo que que retorna a tabela SOLICITACAO_TIPO_GRUPO
	 * 
	 * @author Hugo Azevedo
	 * @date 06/02/2012
	 * 
	 * */
	public Collection<SolicitacaoTipoGrupo> obterSolicitacaoTipoGrupo() throws ErroRepositorioException;
	
	/**
	 * [UC 1282] Relatorio de Pesquisa de Satisfacao Sintetico
	 * @author Davi Menezes
	 * @date 29/02/2012
	 * */
	 public Collection<Object []> pesquisarPesquisaSatisfacao(PesquisaSatisfacaoHelper helper) throws ErroRepositorioException;
	 
	 /**
	 * [UC 1282] Relatorio de Pesquisa de Satisfacao Anal�tico
	 * @author Davi Menezes
	 * @date 01/03/2012
	 * */
	 public Collection<Object []> pesquisarPesquisaSatisfacaoRelatorioAnalitico(PesquisaSatisfacaoHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 2. O sistema dever� selecionar os im�veis residenciais com apenas de uma economia 
	 * 	e que o cliente usu�rio n�o tenham CNPJ como documento principal
	 * 
	 * [UC1295] Efetuar Sorteio de Pr�mios
	 * [SB0001] - Verificar Crit�rios Cadastrais.
	 * 
	 * @author Mariana Victor
	 * @date 02/03/2012
	 * 
	 * @param idLocalidade, idImovel
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarImoveisSorteio(
		Integer idLocalidade, Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 
	 * 3.1.	O Sistema dever� obter todas as contas da fatura 07/2011 at� a fatura 12/2011
	 * 
	 * @author Mariana Victor
	 * @date 02/03/2012
	 * 
	 * @param idImovel
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosContasImovelSorteio(
		Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 
	 * 3.4.1. Im�vel com conta vencida anterior a 01/07/2011 e pagamento posterior a 02/07/2011
	 * 
	 * @author Mariana Victor
	 * @date 27/03/2012
	 * 
	 * @param idImovel
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificarImovelSorteioContaVencidaAnterior(
		Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 
	 * 3.4.2. Im�vel com parcelamento efetuado ap�s 01/07/2011 
	 * 
	 * @author Mariana Victor
	 * @date 27/03/2012
	 * 
	 * @param idImovel
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificarImovelSorteioParcelamentoEfetuado(
		Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 
	 * 3.4.3. Im�vel com a conta da fatura 01/2012 n�o quitada 
	 * 
	 * @author Mariana Victor
	 * @date 27/03/2012
	 * 
	 * @param idImovel
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificarImovelSorteioFaturaNaoQuitada(
		Integer idImovel) throws ErroRepositorioException;
	
		/**
		 * RM6655 - cria��o de etapa no programa de acompanhamento de OS no smartphone 
		 * [UC1325] Substituir Hidr�metro Acompanhamento Servi�o
		 * [SB0003] - Atualizar Im�vel/Liga��o de �gua.
		 * 
		 * @autor Fernanda Almeida
		 * @date 24/04/2012
		 * @throws ErroRepositorioException
		 */
	 public LigacaoAgua pesquisarLigacaoAgua(Integer idImovel) throws ErroRepositorioException;
	 
	 
	 	/**
		 * @author Rodrigo Cabral
		 * @date 06/06/2012
		 **/
	 public Short pesquisarIndicadorTipoCalcArea(Integer servicoTipoId) throws ErroRepositorioException;
		
	/**
	 * [UC1295] Efetuar Sorteio de Pr�mios
	 * 
	 * [SB008] - Verificar Crit�rios Cadastrais Sorteio Mensal.
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2012
	 * 
	 * @param idImovel
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificarImovelAptoSorteioMensal(
		Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC1398] - Definir Equipes a Serem Acompanhadas
	 * [IT0003] - Pesquisar Unidades Organizacionais
	 * 
	 * @return Collection<UnidadeOrganizacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<UnidadeOrganizacional> obterUnidadesOrganizacionaisAssociadasEquipe() throws ErroRepositorioException;
	
	/**
	 * [UC1398] - Definir Equipes a Serem Acompanhadas
	 *  
	 * @return ArrayList<UnidadeOrganizacional>
	 * @throws ErroRepositorioException
	 */
	public void excluirUnidadesOrganizacionaisAssociadasUsuario(Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * [UC1403] - Consultar Mensagens Enviadas Via Sms
	 * 
	 * @return Collection<MensagensEnviadasViaSms>
	 * @throws ErroRepositorioException
	 */
	
	public Collection<MensagemSms> consultarMensagensEnviadasViaSms 
	(MensagemSmsHelper helper) 
			throws ErroRepositorioException;
	
	/**
	 * Retorna o �nico registro do ParametrosSms.
	 * @author Carlos Chaves
	 * @date 14/12/2012
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException Descri��o da exce��o
	 */
	public ParametrosSms pesquisarParametrosSms() throws ErroRepositorioException;
	/**
	 * [UC0250] Consultar Devolu��o
	 * 
	 * @author Ana Maria
	 * @date 28/11/2012
	 * 
	 */
	public Collection<RegistroAtendimentoDevolucaoValores> pesquisarDevolucaoValoresRA(String idImovel)
			 throws ErroRepositorioException;
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 05/12/2012
	 * 
	 */
	public Collection<RegistroAtendimentoDevolucaoValores> pesquisarDevolucaoValoresRegistroAtendimento(String idRA)
			 throws ErroRepositorioException;
	
	/**
	 * [UC1469] - Retirar Esgoto de Fact�vel Fatur�vel
	 * [SB0001 - Excluir Liga��o de Esgoto para Situa��o Fact�vel Fatur�vel].
	 * @author Arthur Carvalho
	 * @date 24/05/2013
	 * 
	 * @param idsLigacaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public void removerLigacaoEsgoto(Collection<Integer> idsLigacaoEsgoto) throws ErroRepositorioException ;
	
	/**
	 * [UC1477] - Atualizar Contas Valores Esgoto PPP
	 * 
	 * @author Mariana Victor,  Ana Maria
	 * @date 14/06/2013
	 * 
	 */
	public boolean verificarIndicadorFaturamentoSituacao(Integer idLigacaoEsgotoSituacao)
			 throws ErroRepositorioException;
	
	/**
	 * @author Anderson Cabral
	 * @date 17/06/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQTDServicoExecutadoLojaVirtual(Date periodoInicial,
			Date periodoFinal, String tipoAtendimento)
			throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobran�a para Smartphone
	 * 
	 * Retorna as informa��es das ordens de servi�o 
	 * 
	 * @author Bruno Barros
	 * @date 27/06/2013
	 * 
	 * @param Integer
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  
		pesquisarDadosOrdensServicoCobrancaSmartphone(Integer idArquivoTexto)
				throws ErroRepositorioException;	
	
	/**
	 * [UC1477] - Atualizar Contas Valores Esgoto PPP
	 * 
	 * @author Mariana Victor,  Ana Maria
	 * @date 17/06/2013
	 * 
	 */
	public Object[] pesquisarDadosImovelCaucularPPP(Integer idImovel) throws ErroRepositorioException;
	
	public Integer pesquisarIdImovelAssociadoAOrdemCobrancaSmartphone(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**[UC1534] Gerar Ordem de Servico Conexao Esgoto
	 * @author: Jonathan Marcos
	 * @date:12/08/2013
	 */
	public Collection<Object[]> pesquisarImoveisComandoOsConexaoEsgoto(
		ComandoOSConexaoEsgoto comandoOSConexaoEsgoto) throws ErroRepositorioException;
	
	/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobran�a para Smartphone 
	 * 
	 * @author Anderson Cabral
	 * @since 19/08/2013
	 */
	public ArrayList<Object[]> pesquisarOSCobrancaSmartphoneAnalitico(RelatorioAcompanhamentoOSCobrancaSmartphoneHelper helper) 
			throws ErroRepositorioException;

	/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobran�a para Smartphone 
	 * 
	 * @author Anderson Cabral
	 * @since 19/08/2013
	 */
	public ArrayList<Object[]> pesquisarOSCobrancaSmartphoneSintetico(RelatorioAcompanhamentoOSCobrancaSmartphoneHelper helper) 
			throws ErroRepositorioException;

	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * [FB0003] Verificar Exist�ncia de Comando para execu��o
	 * 
	 * @author Hugo Azevedo	
	 * @date 15/08/2013
	 * 
	 */
	public Collection<Integer> obterComandoOSConexaoEsgotoExecucao(Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * [FB0001] Selecionar Im�veis
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Collection<Object[]> obterImoveisOSFactivelFaturavel(Integer idFaturamentoGrupo, Integer idComando, Object[] objComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public ComandoOSConexaoEsgoto obterComandoOSConexaoEsgoto(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0354] - Efetuar Liga��o de Esgoto
	 * RM9021 - Obter dados do comando de conex�o de esgoto.
	 * 
	 * @author Ana Maria
	 * @date 20/08/2013
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ComandoOSConexaoEsgoto obterDadosComandoOSConexaoEsgoto(
			Integer idComandoOSConexaoEsgoto) throws ErroRepositorioException;
	
	
	/**
	 * [UC1539] Encerrar Ordem de Servi�o Por N�o Execu��o
	 * [FB0003] Verificar Exist�ncia de Comando para execu��o
	 * 
	 * @author Hugo Azevedo	
	 * @date 21/08/2013
	 * 
	 */
	public Collection<Integer> obterComandoOSConexaoEsgotoExecucaoEncerramentoNaoExecucao(Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	
	/**
	 * [UC1539] Encerrar Ordem de Servi�o Por N�o Execu��o
	 * [IT0002] Selecionar Im�veis
	 * 
	 * @author Hugo Azevedo	
	 * @date 21/08/2013
	 * 
	 */
	public Collection<Object[]> obterImoveisOSNaoExecucao(Integer idFaturamentoGrupo, Integer quantidadeInicio, Integer quantidadeMaxima, Integer idComando, Integer ref) throws ErroRepositorioException;
	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * [FB0001] Selecionar Im�veis
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Object[] obterValoresComandoOSConexaoEsgoto(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1538] Gerar Relat�rios dos Comandos de Ordem de Servi�o Conex�o de Esgoto
	 * 
	 * [IT0003] Pesquisar OS de Conex�o de Esgoto
	 * [IT0005] Obter Dados Anal�ticos das OS's
	 * 
	 * @author Mariana Victor	
	 * @date 21/08/2013
	 */
	public Collection<Object[]> pesquisarDadosRelatorioComandosConexaoEsgotoAnalitico(
		FiltrarRelatorioComandosConexaoEsgotoHelper helperFiltro) throws ErroRepositorioException;
	
	/**
	 * [UC1538] Gerar Relat�rios dos Comandos de Ordem de Servi�o Conex�o de Esgoto
	 * 
	 * [IT0003] Pesquisar OS de Conex�o de Esgoto
	 * [IT0004] Obter Dados Sint�ticos das OS's
	 * 
	 * @author Mariana Victor	
	 * @date 22/08/2013
	 */
	public Collection<Object[]> pesquisarDadosRelatorioComandosConexaoEsgotoSintetico(
		FiltrarRelatorioComandosConexaoEsgotoHelper helperFiltro) throws ErroRepositorioException;
	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * [FB0002] Inserir Motivos de Gera��o da Carta
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Collection<Integer> obterAnormalidadesNaoImpressaoImovel(Integer idImovel,Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * [FB0002] Inserir Motivos de Gera��o da Carta
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public BigDecimal obterValorContaPreFaturadaImovel(Integer idImovel,Integer idComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * [FB0002] Inserir Motivos de Gera��o da Carta
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Collection<Object[]> selecionarOSMotivoEntregaOutroEndereco(Integer idComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Servi�o Fact�vel Fatur�vel
	 * [FB0004] Gerar Relat�rio de Im�veis com Cartas n�o Impressas
	 * 
	 * @param idGrupo - Id do grupo de faturamento
	 * 
	 * @author Hugo Azevedo	
	 * @date 26/08/2013
	 * 
	 */
	public Collection<Object[]> obterDadosRelatorioImoveisCartasNaoImpressas(Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	
	/**
	 * [UC1557] Gerar Resumo A��es Ordem de Servi�o
	 * 
	 * @author Diogo Luiz
	 * @date 18/09/2013
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	*/
	
	public Collection<Object[]> pesquisarContaOrdemServico(SistemaParametro sistemaParametros,int numeroPaginas, int quantidadeRegistros) throws ErroRepositorioException;
	
	

	/**
	 * [UC1557] Gerar Resumo A��es Ordem de Servi�o
	 * 
	 * @author Diogo Luiz
	 * @date 19/09/2013
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	*/
	public void excluirResumoOrdemDeServico() throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0002] - Determinar Situa��o Predominante do D�bito da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public Collection<Object[]> recuperarDadosContasOS(Integer idOrdemServico)
			throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0002] - Determinar Situa��o Predominante do D�bito da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public void atualizarOSResumo(Collection<OrdemServicoContaHelper> collOSContaHelperParaAtualizar) throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0001] - Gerar Resumo Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public void gerarResumoOrdemServico() throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0002] - Determinar Situa��o Predominante do D�bito da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public void atualizarOSContas(SistemaParametro sistemaParametro) throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0002] - Determinar Situa��o Predominante do D�bito da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public Date dataSituacaoParcelamento(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * [UC1398] - Definir Equipes a Serem Acompanhadas
	 * [SB0004] - Excluir Empresa
	 * 
	 * @author Davi Menezes
	 * @date 10/10/2013
	 *  
	 * @throws ErroRepositorioException
	 */
	public void excluirEmpresasAssociadasUsuario(Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * [UC1398] - Definir Equipes a Serem Acompanhadas
	 * [SB0006] - Excluir Natureza de Equipe
	 * 
	 * @author Davi Menezes
	 * @date 10/10/2013
	 *  
	 * @throws ErroRepositorioException
	 */
	public void excluirNaturezaEquipeAssociadasUsuario(Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * [UC0366] - Inserir Registro de Atendimento
	 * [SB0053] - Remover Registro de Integra��o com o CompGis
	 * 
	 * @author Anderson Cabral
	 * @date 14/01/2013
	 *  
	 * @throws ErroRepositorioException
	 */
	public void excluirRegistroCompGis(Integer idUsuario) throws ErroRepositorioException;
	
	
	/**
	 * [UC1203] Incluir Ordem de Servi�o na Programa��o
	 * [FS0011] Validar Bairro da Ordem de Servi�o
	 * 
	 * @author Hugo Azevedo
	 * @date 13/05/2014
	 */
	public boolean validarBairroOrdemServico(Date dataRoteiro, Equipe equipe,
			OrdemServico os) throws ErroRepositorioException;
	
}