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
 * Interfe, que disponibiliza os serviços do Repositório Atendimento Público 
 *
 * @author Leandro Cavalcanti
 * @date 10/07/2006
 */
public interface IRepositorioAtendimentoPublico {
	
	/**
	 * [UC-0355] - Efetuar Corte de Ligaçã de Àgua
	 * [SB001] Atualizar Hidrometro - (corte de ligação de água)
	 * Atualizar um os campos lastId,dataUltimaAtualização do imovel na base
	 * 
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizarLigacaoAgua(Integer idImovel, Integer idLigacaoAguaSituacao, Integer numeroSeloCorte ) throws ErroRepositorioException;
	/**
	 * [UC-0355] - Efetuar Corte de Ligaçã de Àgua
	 * [SB001] Atualizar Hidrometro - (corte de ligação de água)
	 * Atualizar os campos hidi_nnleituracorte e hidi_tmultimaalteracao de HidrometroInstalacaoHistorico
	 *			
	 *
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizarHidrometroLIgacaoAgua(Integer imovelId,Integer numeroLeituraCorte) throws ErroRepositorioException ;
		
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB002] Atualizar Ligação de Água 
	 * Atualizar os campos hidi_id e lagu_tmultimaalteracao de LigacaoAgua
	 * 		
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
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
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB002] Atualizar Imóvel
	 * Atualizar os campos hidi_id e imov_tmultimaalteracao de Imovel
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
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
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB003] Atualizar Hidrômetro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
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
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarConsumoMinimoLigacaoAgua(LigacaoAgua ligacaoAgua) throws ErroRepositorioException;	
	

	
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB003] Atualizar Hidrômetro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarLocalArmazanagemHidrometro(Integer idHidrometro,Integer localArmazanagemHidrometro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB003] Atualizar Hidrômetro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroInstalacoHistorico(HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico) 
		throws ErroRepositorioException;	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro na ligação de água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro no imóvel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Capacidade de Hidrômetro pela Ligação de Água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Capacidade de Hidrômetro pelo Imóvel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Valor do Debito pelos parâmtros passados.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param obterValorDebitoHelper
	 * @return o valor do débito 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorDebito(ObterValorDebitoHelper params) throws ErroRepositorioException;	
	
	/**
	 * Método que retorna o número do hidrômetro da ligação de água
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
	 * Método que retorna o tipo da ligação de água e a data do corte da ligação de água
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
	 * Consulta os dados das ordens de serviço para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @created 07/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoProgramacaoRelatorio(Integer idEquipe, Date dataRoteiro)
			throws ErroRepositorioException;
	
	/**
	 * [UC0404] Manter Especificação da Situação do Imovel
	 * 
	 * Este caso de uso remove a especificação e os critério
	 * 
	 * [SB0002] Remover Especificação da situacao
	 * 
	 * @author Rafael Pinto
	 * @created 08/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void removerEspecificacaoSituacaoImovelCriterio(String[] idsEspecificacaoSituacaoImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os ids das situações de ligação de água.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoAgua() throws ErroRepositorioException ;
	
	
	/**
	 * Pesquisa todos os ids das situações de ligação de esgoto.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
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
	 * Este cso de uso permite efetuar a ligação de água e eventualmente 
	 * a instalação de hidrômetro, sem informação de RA sendo chamado direto pelo menu.
	 *
	 * [UC0579] - Efetuar Ligação de Água com Intalação de Hidrômetro
	 *
	 * @author Flávio Leonardo
	 * @date 25/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEfetuarLigacaoAguaHidrometroSemRA(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0XXX] Gerar Contrato de Prestação de Serviço
	 * 
	 * @author Rafael Corrêa
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
	 * Obtém os dados necessário da ligação de água, de esgoto e do hidrômetro
	 * instalado na ligação de água
	 * 
	 * @author Rafael Corrêa
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
	 * [UC0482]Emitir 2ª Via de Conta
	 *obter numero do hidrômetro na ligação de água.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2007
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public String obterNumeroHidrometroEmLigacaoAgua(Integer imovelId)
			throws ErroRepositorioException;
	
	/**
	 * [UC0738] Retorna as informações para o relatório de certidão negativa
	 * @param imo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioCertidaoNegativa( Imovel imo )
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados necessários para a geração do relatório
	 * 
	 * [UC0864] Gerar Certidão Negativa por Cliente
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
	 * [FS0004] - Cliente não associado ao documento
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
	 * [FS0004] - Cliente não associado ao documento
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
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
    public Collection<AutosInfracao> pesquisarAutoInfracaoRecursoVencido(Integer idSituacaoAutoInfracao,Date prazoEntregaRecursoVencido)
            throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * [SB0001] Atualizar Autos Infração
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
    public void atualizarAutosInfracao(Collection idsAutosInfracao,
    		Integer idSituacaoAutoInfracao) throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
    public Collection<FiscalizacaoSituacaoServicoACobrar> pesquisarFiscalizacaoSituacaoServicoACobrar(Integer idFiscalizacaoSituacao)
            throws ErroRepositorioException;
    
    /**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
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
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
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
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
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
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo serviço, e retorna uma coleção. 
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Collection<ServicoCobrancaValor> pesquisarServicoCobrancaValorUltimaVigencia(Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 [SB0002] – Replicar os serviços existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo serviço, e retorna o total.   
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Integer pesquisarServicoCobrancaValorUltimaVigenciaTotal() throws ErroRepositorioException;
	
	/**
	 * [SB0002] – Replicar os Valores de Cobrança de Serviço existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo Cobrança, e retorna uma coleção. 
	 * 
	 * @author Hugo Leonardo
	 * @date 23/04/2010
	 */
	public Collection<ServicoCobrancaValor> replicarServicoCobrancaValorUltimaVigencia(String[] selecionados) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0391] Inserir valor cobrança Serviço.
	 * 
	 * Verificar se existe vigência já cadastrada para o Serviço Tipo.
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
	 * @see Caso a opcao = 1 - verifica as situações de inserir e atualizar Serviço Tipo.
	 * @see Caso a opcao = 2 - verifica a situação de retificar Serviço Tipo.
	 */
	public String verificarExistenciaVigenciaServicoTipo(String dataVigenciaInicial, String dataVigenciaFinal, Integer idServicoTipo)
		throws ErroRepositorioException;	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento tem o nivel selecionado como Urgência
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
	 *  [SB0034] – Verificar RA de urgência
	 *  
	 * Retorna um ou todos usuários da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usuário 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] – Verificar RA de urgência
	 *  
	 * Retorna um ou todos usuários da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usuário 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarVisualizacaoRaUrgencia(Integer idRegistroAtendimento, Integer idUnidade, Integer idUsuario)
		throws ErroRepositorioException;
		
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento já está relacionado a uma Unidade informada.
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
	 * [UC1028] Exibir Registro Atendimento Urgência
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
	 * Atualiza um ou vários campos da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usuário, indicador Tramite e indicador Visualizacao 
	 * @throws ErroRepositorioException
	 * @data   10/06/2010
	 * 
	 */
	public void atualizarUsuarioRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, String idUsuarios, Integer idUsuario, Integer indicadorTramite, Integer indicadorVisualizacao)
            throws ErroRepositorioException;
	
    /**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Hugo Amorim
	 * @date 15/07/2010
	 */
	public Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> 
		pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando, Collection idsSituacos)throws ErroRepositorioException;
	
	/**
	 * [UC1056] ? Gerar Relatório de Acompanhamento dos Registros de Atendimento
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
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento
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
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento
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
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento
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
 	 * @param idRepavimentadora, idPavimento, indicadorPavimento: 1-Rua, 2-Calçada
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
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
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
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return Integer
	 * 
	 * VerificarExistenciDiasSemValor
	 */
	public Integer verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, 
			Date dataFinal, Integer indicadorPavimento, Integer tipo) throws ErroRepositorioException;
	
	/**
     * [UC0412] Manter Tipo de Serviço
     * 
     * @author Vivianne Sousa
     * @created 07/01/2011
     */
    public void removerServicoTipoBoletim(Integer idServicoTipo)
            throws ErroRepositorioException ;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
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
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 28/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( 
			Integer os, Integer imovel, Date dataEncerramentoOS, Integer tipo) throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 31/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( Collection<Integer> idsOS) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
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
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
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
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
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
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0008 – Validar Motivo Encerramento]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Boolean verificarAtendimentoMotivoEncerramento(Integer idMotivoEncerramento) throws ErroRepositorioException;
	
	/**
	 * [UC1177] Gerar Relatório de Ordens de Serviço por Situação
	 * 
	 * O segundo parâmetro (boletimGerado) é um booleano que
	 * indica se para um dado grupo de cobrança e um mês referencia
	 * foi gerado um boletim de medição.
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
     * Obtém a coleção de perfis de tipo de serviço para OS.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	public Collection obterColecaoTipoOSgerada() throws ErroRepositorioException;
	
	
	/**
     * Obtém a coleção de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
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
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * O segundo parâmetro (relatorioDefinitivo) é um booleano que
	 * indica se o relatório é definitivo ou não, pois o resultado
	 * da query é diferente para os relatórios definitivos e os
	 * de simulação
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
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * Método que vai retornar as quantidades acumuladas e os valores acumulados
	 * no período para geração do relatório de acompanhamento do boletim de medição.
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
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * O segundo parâmetro (relatorioDefinitivo) é um booleano que
	 * indica se o relatório é definitivo ou não, pois o resultado
	 * da query é diferente para os relatórios definitivos e os
	 * de simulação
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
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * @author Diogo Peixoto
	 * @date 01/08/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return Taxa de Sucesso do Boletim de Medição
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarTaxaSucessoBoletimMedicao(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ErroRepositorioException;
	
	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar as Ordens de serviços a partir de seu imóvel e tipo de serviço
	 * 
	 * @author Hugo Azevedo
	 * @data 14/01/2011
	 */
	
	public Collection obterOSImovelTipoServico(Integer id, Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
     * Obtém a quantida de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
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
	 * [SB0001] Selecionar Municípios da Região
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
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * Método que vai retornar um booleano indicando se o relatório de acompanhamento de boletim
	 * medição é definitivo ou não definitivo.
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
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Penalizadas para determinado boletim de medição
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSPenalizadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Executadas para determinado boletim de medição
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSExecutadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	public Collection obterColecaoOSFiscalizacaoNaoExecutadas() throws ErroRepositorioException;
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * Método que vai retornar as fotos de uma determinada
	 * ordem de serviço passada no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Ordem de Serviço
	 * 
	 * @return Collection<Object[]> - Coleção das Fotos da OS
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServico(Integer idFoto) throws ErroRepositorioException;
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * Método que vai retornar as fotos de uma determinada
	 * ordem de serviço passada no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Foto da Ordem de Serviço
	 * 
	 * @return Collection<Object[]> - Foto da Ordem de Serviço
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServicoPorIdFoto(Integer idFoto) throws ErroRepositorioException;
	
	/**
	 * Método que pesquisa as ordens seletivas para um
	 * determinado comando
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * [SB 0001] Consultar Ordem de Serviço Seletiva
	 * @author Raimundo Martins
	 * @date 15/09/2011
	 * 
	 * @return coleção das ordens de serviços ses
	 */
	
	public Collection<Object[]> consultarOrdemServicoSeletiva(String comOrdemSeletiva, String local, String setorInicial, 
			String setorFinal, String quadraIni, String quadraFin) throws ErroRepositorioException;
	
	/**
	 * Método que verifica a existência do arquivo
	 * texto para aquele comando, localidade,
	 * setor comercial inicial, setor comercial final
	 * quadra inicial e quadra final	 * 
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * [FS 0005] Geração do Arquivo Texto
	 * @author Raimundo Martins
	 * @date 22/09/2011
	 * 
	 * @return true se tiver o arquivo, false senão
	 */
	public Boolean verificarExistenciaArquivoTextoVisitaCampo(String comOrdemSeletiva, String local, String setorInicial, 
			String setorFinal, String quadraIni, String quadraFin) throws ErroRepositorioException;

	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 *  1. O sistema exibe as informações do arquivo texto;
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 *  2.	O sistema deverá exibir a lista das ordens de serviço associadas ao arquivo texto 
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 *  Pesquisa o id do imóvel associado à ordem de serviço
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 *  3. Anormalidade Registrada 
	 *  4. Anormalidade Encontrada 
	 *  5. Tipo de Pavimento de Calçada 
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 *  7.1.1. O sistema deverá exibir todas as ações para correção das anormalidades encontradas 
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 * 8.2.	Para as ações selecionadas e com ordem de serviço gerada, o sistema deverá atualizar a tabela de retorno
	 * 8.3.	Para as ações não selecionadas, o sistema deverá atualizar a tabela de retorno  
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 *  9. Caso o usuário não selecione ações da tabela e selecione a opção de atualizar a ordem de serviço de 
	 *  visita em campo, o sistema deverá atualizar a ordem de serviço de visita de campo  como  conferida
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarOSVisitaCampoConferida(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
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
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * 2. O sistema recupera os dados do cliente inicialmente enviados 
	 *  para as equipes de campo para atendimento da ordem de serviço informada 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public ClieOsSeletivaVisitaCampo pesquisarClieOsSeletivaVisitaCampo(
			Integer idOrdemServico) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * 3. O sistema recupera os dados de retorno da visita de campo transmitidos pelo dispositivo móvel
	 *  para atualização do cliente associado ao imóvel da ordem de serviço informada 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public ArquivoTextoRetornoClienteVisitaCampo pesquisarArquivoTextoRetornoClienteVisitaCampo(
			Integer idOrdemServico) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * [SB0006] - Atualizar Movimento do Cliente 
	 *  1. O sistema atualiza a tabela de movimento do cliente transmitida do dispositivo móvel 
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * [SB0007] - Atualizar Movimento do Fone do Cliente 
	 *  1. O sistema atualiza a tabela de movimento de fone do cliente transmitida do dispositivo móvel 
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
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * @author Mariana Victor
	 * @data 29/09/2011
	 */
	public ClieFoneSeletivaVisitaCampo pesquisarClieFoneSeletivaVisitaCampo(
			Integer idClieOSVisitaCampo) 
		throws ErroRepositorioException;
	

	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
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
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
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
	 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
	 * @author Amélia Pessoa
	 * @date 24/11/2011
	 */
	public Collection<Object[]> filtrarRelatorioDebitosCobradosImovel(
			FiltrarRelatorioDebitosCobrancaImovelHelper filtro) throws ErroRepositorioException;
	
	/**
	 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
	 * @author Amélia Pessoa
	 * @date 24/11/2011
	 */
	public Collection<Object[]> filtrarRelatorioTiposServico(
			FiltrarRelatorioTiposServicoHelper filtro) throws ErroRepositorioException;
	

	/**
	 * [UC1249] Filtro OS Encerradas por Acompanhamento de Serviço
	 * [SB0010] – Pesquisar Fotos do RA
	 * 
	 * Método que vai retornar as fotos de um determinado
	 * registro de atendimento passada no parâmetro.
	 * 
	 * @author Fernanda Almeida
	 * @date 17/11/2011
	 * 
	 * @param Integer - ID do Registro Atendimento
	 * 
	 * @return Collection<Object[]> - Coleção das Fotos da OS
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosRA(Integer id)
			throws ErroRepositorioException;
	
	/**
	 * Verifica se o Registro de Atendimento pode cancelar débitos
	 * 
	 * @author Amélia Pessoa
	 * @date 06/12/2011
	 * @param idRegistroAtendimento
	 * @return boolean
	 * RM 5759
	 */
	public boolean verificarPermissaoRACancelamentoDebito(
			int idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * RM1165 - Registrar em tabela os parâmetros que foram utilizados para calcular o valor do 
	 *          débito a cobrar gerado decorrente da situação de fiscalização informada
	 * UC0210 - Consultar débito a cobrar
	 * Analista: Cláudio Lira
	 * 
	 * SB0001- Exibir Parâmetros de Cálculo
	 * 
	 * Pesquisar na tabela atendimentopublico.fiscaliz_param_calc_deb com dbac_id = dbac_id
     * recebido
	 * 
	 * @autor Thúlio Araújo
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
	 * Método que que retorna a tabela SOLICITACAO_TIPO_GRUPO
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
	 * [UC 1282] Relatorio de Pesquisa de Satisfacao Analítico
	 * @author Davi Menezes
	 * @date 01/03/2012
	 * */
	 public Collection<Object []> pesquisarPesquisaSatisfacaoRelatorioAnalitico(PesquisaSatisfacaoHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC1289] Gerar Base de Cliente para Sorteio
	 * 2. O sistema deverá selecionar os imóveis residenciais com apenas de uma economia 
	 * 	e que o cliente usuário não tenham CNPJ como documento principal
	 * 
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * [SB0001] - Verificar Critérios Cadastrais.
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
	 * 3.1.	O Sistema deverá obter todas as contas da fatura 07/2011 até a fatura 12/2011
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
	 * 3.4.1. Imóvel com conta vencida anterior a 01/07/2011 e pagamento posterior a 02/07/2011
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
	 * 3.4.2. Imóvel com parcelamento efetuado após 01/07/2011 
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
	 * 3.4.3. Imóvel com a conta da fatura 01/2012 não quitada 
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
		 * RM6655 - criação de etapa no programa de acompanhamento de OS no smartphone 
		 * [UC1325] Substituir Hidrômetro Acompanhamento Serviço
		 * [SB0003] - Atualizar Imóvel/Ligação de Água.
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
	 * [UC1295] Efetuar Sorteio de Prêmios
	 * 
	 * [SB008] - Verificar Critérios Cadastrais Sorteio Mensal.
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
	 * Retorna o único registro do ParametrosSms.
	 * @author Carlos Chaves
	 * @date 14/12/2012
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException Descrição da exceção
	 */
	public ParametrosSms pesquisarParametrosSms() throws ErroRepositorioException;
	/**
	 * [UC0250] Consultar Devolução
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
	 * [UC1469] - Retirar Esgoto de Factível Faturável
	 * [SB0001 - Excluir Ligação de Esgoto para Situação Factível Faturável].
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
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobrança para Smartphone
	 * 
	 * Retorna as informações das ordens de serviço 
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
	
	/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobrança para Smartphone 
	 * 
	 * @author Anderson Cabral
	 * @since 19/08/2013
	 */
	public ArrayList<Object[]> pesquisarOSCobrancaSmartphoneAnalitico(RelatorioAcompanhamentoOSCobrancaSmartphoneHelper helper) 
			throws ErroRepositorioException;

	/** [UC1536] Gerar relatorio de Acompanhamento das O.S. de Cobrança para Smartphone 
	 * 
	 * @author Anderson Cabral
	 * @since 19/08/2013
	 */
	public ArrayList<Object[]> pesquisarOSCobrancaSmartphoneSintetico(RelatorioAcompanhamentoOSCobrancaSmartphoneHelper helper) 
			throws ErroRepositorioException;

	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * [FB0003] Verificar Existência de Comando para execução
	 * 
	 * @author Hugo Azevedo	
	 * @date 15/08/2013
	 * 
	 */
	public Collection<Integer> obterComandoOSConexaoEsgotoExecucao(Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * [FB0001] Selecionar Imóveis
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Collection<Object[]> obterImoveisOSFactivelFaturavel(Integer idFaturamentoGrupo, Integer idComando, Object[] objComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public ComandoOSConexaoEsgoto obterComandoOSConexaoEsgoto(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0354] - Efetuar Ligação de Esgoto
	 * RM9021 - Obter dados do comando de conexão de esgoto.
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
	 * [UC1539] Encerrar Ordem de Serviço Por Não Execução
	 * [FB0003] Verificar Existência de Comando para execução
	 * 
	 * @author Hugo Azevedo	
	 * @date 21/08/2013
	 * 
	 */
	public Collection<Integer> obterComandoOSConexaoEsgotoExecucaoEncerramentoNaoExecucao(Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	
	/**
	 * [UC1539] Encerrar Ordem de Serviço Por Não Execução
	 * [IT0002] Selecionar Imóveis
	 * 
	 * @author Hugo Azevedo	
	 * @date 21/08/2013
	 * 
	 */
	public Collection<Object[]> obterImoveisOSNaoExecucao(Integer idFaturamentoGrupo, Integer quantidadeInicio, Integer quantidadeMaxima, Integer idComando, Integer ref) throws ErroRepositorioException;
	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * [FB0001] Selecionar Imóveis
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Object[] obterValoresComandoOSConexaoEsgoto(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1538] Gerar Relatórios dos Comandos de Ordem de Serviço Conexão de Esgoto
	 * 
	 * [IT0003] Pesquisar OS de Conexão de Esgoto
	 * [IT0005] Obter Dados Analíticos das OS's
	 * 
	 * @author Mariana Victor	
	 * @date 21/08/2013
	 */
	public Collection<Object[]> pesquisarDadosRelatorioComandosConexaoEsgotoAnalitico(
		FiltrarRelatorioComandosConexaoEsgotoHelper helperFiltro) throws ErroRepositorioException;
	
	/**
	 * [UC1538] Gerar Relatórios dos Comandos de Ordem de Serviço Conexão de Esgoto
	 * 
	 * [IT0003] Pesquisar OS de Conexão de Esgoto
	 * [IT0004] Obter Dados Sintéticos das OS's
	 * 
	 * @author Mariana Victor	
	 * @date 22/08/2013
	 */
	public Collection<Object[]> pesquisarDadosRelatorioComandosConexaoEsgotoSintetico(
		FiltrarRelatorioComandosConexaoEsgotoHelper helperFiltro) throws ErroRepositorioException;
	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * [FB0002] Inserir Motivos de Geração da Carta
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Collection<Integer> obterAnormalidadesNaoImpressaoImovel(Integer idImovel,Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * [FB0002] Inserir Motivos de Geração da Carta
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public BigDecimal obterValorContaPreFaturadaImovel(Integer idImovel,Integer idComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * [FB0002] Inserir Motivos de Geração da Carta
	 * 
	 * @author Hugo Azevedo	
	 * @date 16/08/2013
	 * 
	 */
	public Collection<Object[]> selecionarOSMotivoEntregaOutroEndereco(Integer idComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1535] Gerar Ordens de Serviço Factível Faturável
	 * [FB0004] Gerar Relatório de Imóveis com Cartas não Impressas
	 * 
	 * @param idGrupo - Id do grupo de faturamento
	 * 
	 * @author Hugo Azevedo	
	 * @date 26/08/2013
	 * 
	 */
	public Collection<Object[]> obterDadosRelatorioImoveisCartasNaoImpressas(Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	
	/**
	 * [UC1557] Gerar Resumo Ações Ordem de Serviço
	 * 
	 * @author Diogo Luiz
	 * @date 18/09/2013
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	*/
	
	public Collection<Object[]> pesquisarContaOrdemServico(SistemaParametro sistemaParametros,int numeroPaginas, int quantidadeRegistros) throws ErroRepositorioException;
	
	

	/**
	 * [UC1557] Gerar Resumo Ações Ordem de Serviço
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
	 * [SB 0002] - Determinar Situação Predominante do Débito da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public Collection<Object[]> recuperarDadosContasOS(Integer idOrdemServico)
			throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0002] - Determinar Situação Predominante do Débito da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public void atualizarOSResumo(Collection<OrdemServicoContaHelper> collOSContaHelperParaAtualizar) throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0001] - Gerar Resumo Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public void gerarResumoOrdemServico() throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0002] - Determinar Situação Predominante do Débito da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 19/09/2013
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */	
	public void atualizarOSContas(SistemaParametro sistemaParametro) throws ErroRepositorioException;
	
	/**
	 * [UC 1757] - Gerar Resumo das Acoes de Ordem de Servico
	 * [SB 0002] - Determinar Situação Predominante do Débito da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
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
	 * [SB0053] - Remover Registro de Integração com o CompGis
	 * 
	 * @author Anderson Cabral
	 * @date 14/01/2013
	 *  
	 * @throws ErroRepositorioException
	 */
	public void excluirRegistroCompGis(Integer idUsuario) throws ErroRepositorioException;
	
	
	/**
	 * [UC1203] Incluir Ordem de Serviço na Programação
	 * [FS0011] Validar Bairro da Ordem de Serviço
	 * 
	 * @author Hugo Azevedo
	 * @date 13/05/2014
	 */
	public boolean validarBairroOrdemServico(Date dataRoteiro, Equipe equipe,
			OrdemServico os) throws ErroRepositorioException;
	
}