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
package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.ArquivoTxtOSVisitaHelper;
import gcom.atendimentopublico.ordemservico.bean.DadosAtualizacaoOSParaInstalacaoHidrometroHelper;
import gcom.atendimentopublico.ordemservico.bean.DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.DadosGeracaoResumoOSConsultaHelper;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.ManterOrdemServicoConcluidaHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoAcompanhamentoServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterCargaTrabalhoEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadesOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterOSDistribuidasPorEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemServicoSituacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSAcompanhamentoHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSEncerradaAcompanhamentoHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.ServicoTipoHelper;
import gcom.atendimentopublico.ordemservico.bean.SituacaoEncontradaHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelPerfilHelper;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoHelper;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEncerrarOSHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarOrdensServicosComandoOrdemSeletivaHelper;
import gcom.gui.relatorio.atendimentopublico.GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.mobile.ExecucaoOSPK;
import gcom.mobile.ParametrosArquivoTextoOSCobranca;
import gcom.mobile.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.mobile.bean.DadosDebitoOSCobrancaSmartphoneHelper;
import gcom.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gcom.relatorio.mobile.RelatorioErrosEncerramentoOSCobrancaBean;
import gcom.relatorio.mobile.RelatorioQuantitativoImoveisTipoServicoBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public interface ControladorOrdemServicoLocal extends javax.ejb.EJBLocalObject {

	/**
	 * [UC0454] Obter Descri��o da situa��o da OS
	 * 
	 * Este caso de uso permite obter a descri��o de uma ordem de servi�o
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idOrdemServico
	 * @throws ControladorException
	 */
	public ObterDescricaoSituacaoOSHelper obterDescricaoSituacaoOS(
			Integer idOrdemServico) throws ControladorException;

	/**
	 * [UC0441] Consultar Dados da Ordem de Servi�o
	 * 
	 * @author Leonardo Regis
	 * @date 15/08/2006
	 * 
	 * @param idOrdemServico
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico consultarDadosOrdemServico(Integer idOrdemServico)
			throws ControladorException;

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * Verifica Exist�ncia de Ordem de Servi�o Programada
	 * 
	 * @author Leonardo Regis
	 * @date 19/08/2006
	 * 
	 * @param idOS
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarExistenciaOSProgramada(Integer idOS)
			throws ControladorException;

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situa��o [SB002] Selecionar Ordem
	 * de Servico por Situa��o da Programa��o [SB003] Selecionar Ordem de
	 * Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico por
	 * Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Munic�pio [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServico(
			PesquisarOrdemServicoHelper filtro) throws ControladorException;

	/**
	 * [UC413]- Pesquisar Tipo de Servi�o
	 * 
	 * Pesquisar o Objeto servicoTipo referente ao idTiposervico recebido na
	 * descri��o da pesquisa, onde o mesmo sera detalhado.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 23/08/2006
	 * 
	 * @param idTipoServico
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarServicoTipo(Integer idTipoServico)
			throws ControladorException;

	/**
	 * [UC0413] Pesquisar Tipo de Servi�o
	 * 
	 * select a.svtp_id from ATENDIMENTOPUBLICO.SERVICO_TIPO A,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_ATIVIDADE B,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_MATERIAL C WHERE A.SVTP_DSSERVICOTIPO
	 * LIKE '%DESC%' AND A.SVTP_DSABREVIADO LIKE '%DESC%' AND (A.SVTP_VLSERVICO >=
	 * 000000 AND A.SVTP_VLSERVICO <= 99999) AND A.SVTP_ICPAVIMENTO = 1 OU 2 and
	 * A.SVTP_ICATUALIZACOMERCIAL = 1 OU 2 AND A.SVTP_ICTERCEIRIZADO = 1 OU 2
	 * AND A.SVTP_CDSERVICOTIPO = ("O" OR "C") AND (A.SVTP_NNTEMPOMEDIOEXECUCAO >=
	 * 0000 AND A.SVTP_NNTEMPOMEDIOEXECUCAO <= 9999) AND DBTP_ID = ID INFORMADO
	 * AND AND CRTP_ID = ID INFORMADO AND STSG_ID = ID INFORMADO AND STRF_ID =
	 * ID INFORMADO AND STPR_ID = ID INFORMADO AND A.SVTP_ID = B.SVTP_ID AND
	 * B.ATIV_ID IN (ID's INFORMADOS) AND A.SVTP_ID = C.SVTP_ID AND C.MATE_ID IN
	 * (ID's INFORMADOS)
	 * 
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada,
			Integer numeroPaginasPesquisa) throws ControladorException;

	/**
	 * [UC0413] Pesquisar Tipo de Servi�o
	 * 
	 * Count
	 * 
	 * @author Fl�vio
	 * @date 17/08/2006
	 */
	public Integer filtrarSTCount(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada)
			throws ControladorException;

	/**
	 * Pesquisar Materiais pelo idServicoTipo na tabela de ServicoTipoMaterial
	 * 
	 * Recupera os materiais do servico tipo material
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarMaterialServicoTipoConsulta(
			Integer idServicoTipoMaterial) throws ControladorException;

	/**
	 * Pesquisar Atividades pelo idServicoTipo na tabela de ServicoTipoAtividade
	 * 
	 * Recupera os Atividades do servico tipo Atividade
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarAtividadeServicoTipoConsulta(
			Integer idServicoTipoAtividade) throws ControladorException;

	/**
	 * [UC0367] Atualizar Dados da Liga��o Agua
	 * 
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Rafael Pinto
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSPorId(Integer idOS)
			throws ControladorException;

	/**
	 * [UC0430] Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public ServicoTipo pesquisarSevicoTipo(Integer id)
			throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 18/08/2006
	 */
	public void validarOrdemServico(OrdemServico ordemServico)
			throws ControladorException;

	/**
	 * [UC0430] Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public OrdemServico pesquisarOrdemServico(Integer id)
			throws ControladorException;

	/**
	 * [UC0806] Gerar Registro Atendimento para Im�veis com Anormalidades
	 * 
	 * @author Diogo Peixoto
	 * @date 14/03/2011
	 */
	public OrdemServico pesquisarOrdemServicoRegistroAtendimento(RegistroAtendimento ra)
			throws ControladorException;
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 18/08/2006
	 */
	public Integer gerarOrdemServico(OrdemServico ordemServico, Usuario usuario, Integer funcionalidade)
			throws ControladorException;

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ControladorException
	 */
	public ObterDadosEquipe obterDadosEquipe(Integer idEquipe)
			throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection pesquisarTipoServicoDisponivelPorCriterio(
			UnidadeOrganizacional unidadeLotacao, int tipoCriterio,
			int origemServico) throws ControladorException;

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB0003] Seleciona Ordem de Servico por Criterio de Sele��o [SB0004]
	 * Seleciona Ordem de Servico por Situacao de Diagnostico [SB0005] Seleciona
	 * Ordem de Servico por Situacao de Acompanhamento pela Agencia [SB0006]
	 * Seleciona Ordem de Servico por Crit�rio Geral
	 * 
	 * @author Rafael Pinto
	 * @date 07/09/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServicoElaborarProgramacao(
			PesquisarOrdemServicoHelper filtro) throws ControladorException;

	/**
	 * [UC0371]Inserir Equipe no sistema.
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipe
	 *            a ser atualizada
	 * 
	 * @throws ControladorException
	 */
	public long inserirEquipe(Equipe equipe, Collection<EquipeComponentes> colecaoEquipeComponentes, 
			Collection<EquipeEquipamentosEspeciais> colecaoEquipeEquipamentosEspeciais,
			Usuario usuario) throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * inser��o de equipe.
	 * 
	 * [FS0007] Verificar quantidade de indicador de respons�vel Validar Carga
	 * Hor�ria M�xima Validar Placa do Ve�culo
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipe
	 * @throws ControladorException
	 */
	public void validarInsercaoEquipe(Equipe equipe)
			throws ControladorException;

	/**
	 * Inserir Componentes da Equipe no sistema.
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipeComponentes
	 *            a ser atualizada
	 * 
	 * @throws ControladorException
	 */
	public void inserirEquipeComponentes(Collection<EquipeComponentes> colecaoEquipeComponentes,
			Equipe equipe, Usuario usuario)
	throws ControladorException ;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * exibi��o da inser��o de componentes da equipe.
	 * 
	 * [FS0003] Validar equipe componente j� existente [FS0004] Verificar
	 * exist�ncia do funcion�rio [FS0007] Verificar quantidade de indicador de
	 * respons�vel
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipeComponentes
	 * @throws ControladorException
	 */
	public void validarExibirInsercaoEquipeComponentes(
			Collection colecaoEquipeComponentes,
			EquipeComponentes equipeComponentes) throws ControladorException;

	/**
	 * [UC] Este m�todo se destina a validar todas as situa��es e
	 * particularidades da inser��o de componentes da equipe.
	 * 
	 * [FS0006] Verificar quantidade de componentes da equipe em Tipo Perfil
	 * Servi�o Validar se possui algum respons�vel
	 * 
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 * 
	 * @param equipeComponentes
	 * @throws ControladorException
	 */
	public void validarInsercaoEquipeComponentes(
			Collection colecaoEquipeComponentes) throws ControladorException;

	/**
	 * Inserir Equipamentos Especiais no Sistema.
	 * 
	 * @author Nathalia Santos 
	 * @date 20/06/2011
	 * 
	 * @param EquipeEquipamentosEspeciais
	 *            a ser atualizada
	 * 
	 * @throws ControladorException
	 */
	public void inserirEquipeEquipamentosEspeciais(Collection <EquipeEquipamentosEspeciais> colecaoEquipeEquipamentosEspeciais, 
			Equipe equipe,Usuario usuario)
			throws ControladorException;
	
	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * exibi��o da inser��o de equipamentos especiais.
	 * 
	 * [FS0011] Validar equipe componente j� existente 
	 * 
	 * @author Nathalia Santos
	 * @date 20/06/2011
	 * 
	 * @param EquipeEquipamentosEspeciais
	 * @throws ControladorException
	 */
	public boolean validarExibirInsercaoEquipeEquipamentosEspeciais(
			Collection colecaoEquipeEquipamentosEspeciais,
			EquipeEquipamentosEspeciais equipeEquipamentosEspeciais) throws ControladorException;

	/**
	 * [UC 0371] Este m�todo se destina a validar todas as situa��es e
	 * particularidades da inser��o de equipamentos especiais.
	 * 
	 * @author Nathalia Santos
	 * @date 20/06/2011
	 * 
	 * @param EquipeEquipamentosEspeciais
	 * @throws ControladorException
	 */
	public void validarInsercaoEquipeEquipamentosEspeciais(
			Collection colecaoEquipeEquipamentosEspeciais) throws ControladorException;

	
	
	
	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return Collection<ObterDadosAtividadesOSHelper>
	 * @throws ControladorException
	 */
	public Collection<ObterDadosAtividadesOSHelper> obterDadosAtividadesOS(
			Integer idOS) throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro
	 * @return collection
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiro(
			Date dataRoteiro) throws ControladorException;

	/**
	 * [UC0479] Gerar D�bito da Ordem de Servi�o
	 * 
	 * [FS0001] Verificar Exist�ncia da Ordem de Servi�o [FS0002] Verificar
	 * Exist�ncia do Tipo de D�bito [FS0003] Validar Valor do D�bito [FS0004]
	 * Validar Quantidade de Parcelas
	 * 
	 * @author Leonardo Regis
	 * @date 11/09/2006
	 * 
	 * @param ordemServicoId
	 * @param tipoDebitoId
	 * @param valorDebito
	 * @param qtdeParcelas
	 * 
	 * @throws ControladorException
	 */
	public DebitoACobrar gerarDebitoOrdemServico(Integer ordemServicoId,
			Integer tipoDebitoId, BigDecimal valorDebito, int qtdeParcelas, String percentualCobranca, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param colecaoOrdemServicoProgramacao
	 * @throws ControladorException
	 */
	public void elaboraRoteiro(Collection colecaoOrdemServicoProgramacao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @param tipoAtividade
	 * @return Collection<ObterDadosAtividadesOSHelper>
	 * @throws ControladorException
	 */
	public Collection<ObterDadosAtividadeOSHelper> obterDadosAtividadeOS(
			Integer idOS, Integer idAtividade, int tipoAtividade)
			throws ControladorException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param equipeId
	 * @param colecaoIdOSProgramadas
	 * @param colecaoOSDistribuidasPorEquipe
	 * @param dataFinalProgramacao
	 * @param dataRoteiro
	 * @return valor da carga de trabalho da equipe
	 * @throws ControladorException
	 */
	public ObterCargaTrabalhoEquipeHelper obterCargaTrabalhoEquipe(
			Integer equipeId,
			Collection<Integer> colecaoIdOSProgramadas,
			Collection<ObterOSDistribuidasPorEquipeHelper> colecaoOSDistribuidasPorEquipe,
			Date dataRoteiro) throws ControladorException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0001] - Verificar Unidade do Usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificarUnidadeUsuario(Integer numeroOS, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0006] - Verificar Origem do Encerramento da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificarOrigemEncerramentoOS(Integer numeroOS,
			Date dataEncerramento) throws ControladorException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [SB0001] - Encerrar sem execu��o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSSemExecucao(Integer numeroOS, Date dataEncerramento,
			Usuario usuarioLogado, String motivoEncerramento,
			Date ultimaAlteracao, String parecerEncerramento,
			OrdemServico osFiscalizacao, String indicadorVistoriaServicoTipo,
			String codigoRetornoVistoriaOs,OrdemServicoBoletim ordemServicoBoletim,
			Short indicadorServicoAceito, Collection<OrdemServicoFoto> colecaoOrdemServicoFoto) throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Atividade>
	 * @throws ErroRepositorioException
	 */
	public Collection<Atividade> obterAtividadesOrdemServico(Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 18/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Equipe>
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> obterEquipesProgramadas(Integer numeroOS)
			throws ControladorException;

	/**
	 * 
	 * [UC0467] - Atualizar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 18/09/2006
	 */
	public void atualizarOrdemServico(OrdemServico ordemServico, Usuario usuario)
			throws ControladorException;

	
	/**
	 * [UC1185] - Informar Calibragem
	 * 
	 * @author Th�lio Ara�jo
	 * @param osProgramacaoCalibragem
	 * @throws ControladorException
	 * @date 28/06/2011
	 */
	public void atualizarInformarCalibragem(OSProgramacaoCalibragem osProgramacaoCalibragem, Usuario usuario)
			throws ControladorException;
	
	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarOSAssociadaDocumentoCobranca(Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarOSAssociadaRA(Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer obterUnidadeDestinoUltimoTramite(Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Equipe>
	 * @throws ControladorException
	 */
	public Collection<Equipe> obterEquipesPorUnidade(Integer idUnidade)
			throws ControladorException;

	/**
	 * [UC0362] Efetuar Instala��o de Hidr�metro
	 * 
	 * @author Leonardo Regis
	 * @date 19/09/2006
	 * 
	 * @param dadosOS
	 * @throws ControladorException
	 */
	public void atualizarOSParaEfetivacaoInstalacaoHidrometro(
			DadosAtualizacaoOSParaInstalacaoHidrometroHelper dadosOS)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 20/09/2006
	 * 
	 * @param dataExecucao,
	 *            horaInicio, horaFim, idEquipe
	 * @return void
	 * @throws ControladorException
	 */
	public void verificaDadosAdicionarPeriodoEquipe(String dataExecucao,
			String horaInicio, String horaFim, Integer idEquipe,
			String dataEncerramentoOS, Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro,idUnidadeOrganizacional
	 * @return collection<OrdemServicoProgramacao>
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiroUnidade(
			Date dataRoteiro, Integer idUnidadeOrganizacional)
			throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o [FS0008] -
	 * Verificar possibilidade da inclus�o da ordem de servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void validarInclusaoOsNaProgramacao(OrdemServico ordemServico,
			Integer unidadeLotacao, Date dataProgramacao, Usuario usuario) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o [FS0012] -
	 * Reordena Sequencial de Programa��o - Inclus�o de Ordem de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialProgramacaoInclusaoOrdemServico(
			Date dataRoteiro, short sequencialAlterado)
			throws ControladorException;

	/**
	 * [UC0455] Exibir Calend�rio para Elabora��o ou Acompanhamento de Roteiro
	 * 
	 * @author R�mulo Aur�lio
	 * @date 21/09/2006
	 * 
	 * @param mesAnoReferencia
	 * @return Collection<ProgramacaoRoteiro>
	 * @throws ControladorException
	 */

	public Collection consultarProgramacaoRoteiro(String mesAnoReferencia,
			Integer unidadeOrganizacional) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void alocaEquipeParaOs(Integer numeroOS, Date dataRoteiro,
			Integer idEquipe, boolean naoRemoverOs, Integer idArquivo, OSProgramacaoAcompanhamentoServicoHelper osProgramacaoAcompanhamentoServico) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void incluirOrdemServicoProgramacao(
			OrdemServicoProgramacao ordemServicoProgramacao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * M�todo que valida a ordem de servi�o utilizado por diversos outros
	 * m�todos do atendimento ao p�blico
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServico(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> recuperaEquipeDaOSProgramacaoPorDataRoteiro(
			Integer idOs, Date dataRoteiro) throws ControladorException;

	/**
	 * Atualiza��o Geral de OS
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param os
	 * @throws ControladorException
	 */
	public void atualizaOSGeral(OrdemServico os) throws ControladorException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarColecaoServicoTipo(Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public void verificaExitenciaProgramacaoAtivaParaDiasAnteriores(
			Integer idOs, Date dataRoteiro) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarOrdemServicoProgramacaoSituacaoOs(Integer numeroOS,
			Date dataRoteiro, short situacaoFechamento,
			Integer idOsProgramNaoEncerMotivo, boolean naoInformaIndicadorAtivo) throws ControladorException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [SB0002] - Encerrar com execu��o e sem refer�ncia
	 * 
	 * @author S�vio Luiz
	 * @date 25/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSComExecucaoSemReferencia(Integer numeroOS,
			Date dataEncerramento, Usuario usuarioLogado,
			String motivoEncerramento, Date ultimaAlteracao,
			String parecerEncerramento, String indicadorPavimento,
			String pavimento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			IntegracaoComercialHelper integracaoComercialHelper,
			String tipoServicoOSId, OrdemServico osFiscalizacao,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
			OrdemServicoPavimento ordemServicoPavimento,OrdemServicoBoletim ordemServicoBoletim,
			Short indicadorServicoAceito, Date dataExecucao, Collection<OrdemServicoFoto> colecaoOrdemServicoFoto)throws ControladorException;
	
	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [SB0003] - Encerrar com execu��o e com refer�ncia
	 * 
	 * @author S�vio Luiz
	 * @date 27/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer encerrarOSComExecucaoComReferencia(Integer numeroOS,
			Date dataEncerramento, Usuario usuarioLogado,
			String motivoEncerramento, Date ultimaAlteracao,
			String parecerEncerramento, String indicadorPavimento,
			String pavimento, String idTipoRetornoOSReferida,
			String indicadorDeferimento, String indicadorTrocaServicoTipo,
			String idServicoTipo, String idOSReferencia,
			String idServicoTipoReferenciaOS,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			IntegracaoComercialHelper integracaoComercialHelper,
			String tipoServicoOSId, OrdemServico osFiscalizacao,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
			OrdemServicoBoletim ordemServicoBoletim,Short indicadorServicoAceito, Date dataExecucao, 
			Collection<OrdemServicoFoto> colecaoOrdemServicoFoto)throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o [FS0012]
	 * Reordena Sequencial de Programa��o - Nova Ordem para Ordem de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialProgramacaoNovaOrdem(Date dataRoteiro,
			short sequencialInformado, short sequencialAtual, Integer idEquipe, boolean verificaOsPendente)
			throws ControladorException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0002] - Validar Tipo Servi�o [FS0004] - Verificar preenchimento dos
	 * campos [FS0007] - Validar Data de Encerramento [FS0008] - Validar Data do
	 * roteiro
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 29/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void validarCamposEncerrarOS(String indicadorExecucao,
			String numeroOS, String motivoEncerramento,
			String dataEncerramento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			String tipoServicoReferenciaId, String indicadorPavimento,
			String pavimento, String idTipoRetornoOSReferida,
			String indicadorDeferimento, String indicadorTrocaServicoTipo,
			String idServicoTipo, String dataRoteiro, String idRA,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
            String indicadorDiagnostico, String observacaoEncerramento, Usuario usuario)
			throws ControladorException;

	public void verificarOrdemServicoControleConcorrencia(
			OrdemServico ordemServico) throws ControladorException;

	/**
	 * [UC0391] Inserir Valor de Cobran�a de Servi�o.
	 * 
	 * Permite a inclus�o de um novo valor de cobran�a de servi�o na tabela
	 * SERVICO_COBRANCA_VALOR.
	 * 
	 * @author Leonardo Regis
	 * @date 29/09/2006
	 * 
	 * @param servicoCobrancaValor
	 * @throws ControladorException
	 */
	public void inserirValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Valor de Cobran�a de Servi�o.
	 * 
	 * Permite a atualiza��o de um novo valor de cobran�a de servi�o na tabela
	 * SERVICO_COBRANCA_VALOR.
	 * 
	 * @author R�mulo Aur�lio
	 * @date 01/11/2006
	 * 
	 * @param servicoCobrancaValor
	 * @param usuarioLogado 
	 * @throws ControladorException
	 */
	public void atualizarValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Tipo de Retorno da OS_Referida.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Ten�rio
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarTipoRetornoOrdemServicoReferida(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 27/09/2006
	 * 
	 * @throws ControladorException
	 */
	public boolean tramitarOuEncerrarRADaOSEncerrada(Integer numeroOS,
			Usuario usuarioLogado, String idMotivoEncerramento, String idRA,
			String dataRoteiro) throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public void validarServicoTipo(Integer idRegistroAtendimento,
			Integer idServicoTipo) throws ControladorException;

	/**
	 * M�todo que valida a ordem de servi�o utilizado por diversos outros
	 * m�todos do atendimento ao p�blico sem a valida��o de indicador comercial
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 12/10/2006
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServicoAtualizacao(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0458] - Imprimir Ordem de Servi�o
	 * 
	 * Pesquisa os campos da OS que ser�o impressos no relat�rio de Ordem de
	 * Servico
	 * 
	 * @author Rafael Corr�a
	 * @date 17/10/2006
	 * 
	 * @param idOrdemServico
	 * @return OSRelatorioHelper
	 * @throws ControladorException
	 */
	public Collection pesquisarOSRelatorio(Collection idsOrdemServico)
			throws ControladorException;

	/**
	 * [UC0482] - Obter Endere�o Abreviado da Ocorr�ncia do RA
	 * 
	 * Pesquisa o Endereco Abreviado da OS
	 * 
	 * @author Rafael Corr�a
	 * @date 19/10/2006
	 * 
	 * 
	 * @param idOrdemServico
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoOS(Integer idOrdemServico)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Material> obterMateriaisProgramados(Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS,
	 *            idMaterial
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterQuantidadePadraoMaterial(Integer numeroOS,
			Integer idMaterial) throws ControladorException;

	/**
	 * Imprimir OS
	 * 
	 * Atualiza a data de emiss�o e a de �ltima altera��o de OS quando gerar o
	 * relat�rio
	 * 
	 * @author Rafael Corr�a
	 * @date 26/10/2006
	 * 
	 * 
	 * @param colecaoIdsOrdemServico
	 * @throws ControladorException
	 */
	public void atualizarOrdemServicoRelatorio(Collection colecaoIdsOrdemServico)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 26/10/2006
	 * 
	 * @param Collection
	 *            <ManterDadosAtividadesOrdemServicoHelper>
	 * @return void
	 * @throws ControladorException
	 */
	public void manterDadosAtividadesOrdemServico(
			Collection<ManterDadosAtividadesOrdemServicoHelper> colecaoDadosAtividades)
			throws ControladorException;

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * Pesquisa os dados da OrdemServicoAtividade
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServico,
	 *            idAtividade
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoAtividade pesquisarOrdemServicoAtividade(
			Integer numeroOS, Integer idAtividade) throws ControladorException;

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * Pesquisa os dados da OsAtividadeMaterialExecucao associada �
	 * OrdemServicoAtividade para a data informada
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServicoAtividade
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtividadeMaterialExecucao(
			Integer idOrdemServicoAtividade) throws ControladorException;

	/**
	 * Retorna o resultado da pesquisa para a pesquisa
	 * 
	 * [UC0492] - Gerar Relat�rio Acompanhamento de Execu��o de Ordem de Servi�o
	 * 
	 * @author Rafael Corr�a
	 * @date 01/11/06
	 * 
	 * @param origemServico
	 * @param situacaoOS
	 * @param idsServicosTipos
	 * @param idUnidadeAtendimento
	 * @param idUnidadeAtual
	 * @param idUnidadeEncerramento
	 * @param periodoAtendimentoInicial
	 * @param periodoAtendimentoFinal
	 * @param periodoEncerramentoInicial
	 * @param periodoEncerramentoFinal
	 * @param idEquipeProgramacao
	 * @param idEquipeExecucao
	 * @param tipoOrdenacao
	 * @return Collection
	 */
	public Collection pesquisarOSGerarRelatorioAcompanhamentoExecucao(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, Date periodoExecucaoInicial, 
			Date periodoExecucaoFinal, String idEquipeProgramacao,
			String idEquipeExecucao, String tipoOrdenacao)
			throws ControladorException;

	/**
	 * Retorna o resultado da pesquisa para a pesquisa
	 * 
	 * [UC0492] - Gerar Relat�rio Acompanhamento de Execu��o de Ordem de Servi�o
	 * 
	 * @author Rafael Corr�a
	 * @date 01/11/06
	 * 
	 * @param origemServico
	 * @param situacaoOS
	 * @param idsServicosTipos
	 * @param idUnidadeAtendimento
	 * @param idUnidadeAtual
	 * @param idUnidadeEncerramento
	 * @param periodoAtendimentoInicial
	 * @param periodoAtendimentoFinal
	 * @param periodoEncerramentoInicial
	 * @param periodoEncerramentoFinal
	 * @param idEquipeProgramacao
	 * @param idEquipeExecucao
	 * @return int
	 */
	public int pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, Date periodoExecucaoInicial,
			Date periodoExecucaoFinal, String idEquipeProgramacao,
			String idEquipeExecucao) throws ControladorException;

	/**
	 * Pesquisa as equipes de acordo com os par�metros informado pelo usu�rio
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corr�a
	 * @date 09/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @param numeroPagina
	 * @return Collection
	 */
	public Collection pesquisarEquipes(String idEquipe, String nome,
			String placa, String cargaTrabalho,String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa, Integer numeroPagina,String equipamentosEspeciasId, String cdUsuarioRespExcServico,
			String indicadorProgramacaoAutomatica,
			String indicadorManterProgramacaoOSdiaAnterior,
			String equipeNatureza) throws ControladorException;
			

	/**
	 * Verifica a quantidade de registros retornados da pesquisa de equipe
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corr�a
	 * @date 09/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @return int
	 */
	public int pesquisarEquipesCount(String idEquipe, String nome,
			String placa, String cargaTrabalho, 
			String codigoDdd, String numeroTelefone, String numeroImei,String idUnidade, 
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa,String equipamentosEspeciasId, String cdUsuarioRespExecServico,
			String indicadorProgramacaoAutomatica,
			String indicadorManterProgramacaoOSdiaAnterior,
			String equipeNatureza)
			throws ControladorException;

	/**
	 * Remove as equipes selecionadas pelo usu�rio e as equipes componentes
	 * associadas a ela
	 * 
	 * [UC0372] - Manter Equipe
	 * 
	 * @author Rafael Corr�a
	 * @date 09/11/06
	 * 
	 * @param idsEquipes
	 * @throws ControladorException
	 */

	public void removerEquipes(String[] idsEquipes, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Valida a ordem de servi�o
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author S�vio Luiz
	 * @date 01/11/06
	 * 
	 * @return Integer
	 */
	public void validarOrdemServico(Integer idOrdemServico)
			throws ControladorException;

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsOS(Integer idOrdemServico)
			throws ControladorException;

	/**
	 * [UC0372] - Manter Equipe
	 * 
	 * Atualiza a equipe e seus componentes na base
	 * 
	 * @author Rafael Corr�a
	 * @date 14/11/2006
	 * 
	 * @param equipe
	 * @throws ControladorException
	 */
	public void atualizarEquipe(Equipe equipe,
			Collection colecaoEquipeComponentes, Usuario usuarioLogado,Collection colecaoEquipeEquipamentosEspeciais)
			throws ControladorException;
	
	/**
	 * [UC0372] - Manter Equipe
	 * 
	 * Atualiza a equipamentos especiais e seus componentes na base
	 * 
	 * @author Nathalia Santos
	 * @date 20/065/2011
	 * 
	 * @param equipamentos especiais
	 * @throws ControladorException
	 */
//	public void atualizarEquipeEquipamentosEspeciais(Equipe equipe,
//			Collection colecaoEquipeEquipamentosEspeciais, Usuario usuarioLogado)
//			throws ControladorException;
	
	
	/**
	 * [UC0383] Manter Material [FS0001] Atualizar Material [FS0002] Atualizar
	 * Material
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/11/2006
	 * 
	 * @param material
	 * @throws ControladorException
	 */

	// [FS0001] VERIFICAR EXISTENCIA DA DESCRI��O
	// [FS0002] VERIFICAR EXISTENCIA DA DESCRI��O ABREVIADA
	public void verificarExistenciaDescricaoMaterial(Material material)
			throws ControladorException;

	/**
	 * [UC0450] Pesquisar Ordem de Servico verifica o tamanho da consulta
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situa��o [SB002] Selecionar Ordem
	 * de Servico por Situa��o da Programa��o [SB003] Selecionar Ordem de
	 * Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico por
	 * Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Munic�pio [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarOrdemServicoTamanho(
			PesquisarOrdemServicoHelper filtro) throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return Integer[]
	 * @throws ControladorException
	 */
	public Integer[] informarRetornoOSFiscalizacao(Integer idOrdemServico,
			String indicadorDocumentoEntregue,
			Integer idLigacaoAguaSituacaoImovel,
			Integer idLigacaoEsgotoSituacaoImovel, 
			Integer idImovel,
			String indicadorMedicaoTipo, 
			String indicadorGeracaoDebito, 
			Integer idCobrancaDocumento,
			Usuario usuarioLogado,
			DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao,
			Short confirmaAtualizacaoSituacaoLigacaoAgua,
			Short confirmaAtualizacaoSituacaoLigacaoEsgoto,
			Collection colecaoFiscalizacaoSituacaoSelecionada,
			LigacaoEsgoto ligacaoEsgoto,
			String indicadorLigacaoEsgoto, ExecucaoOSPK execucaoOSPK) throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * M�todo que � chamado pelo [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/08/2006
	 */
	public Integer gerarOrdemServicoSemValidacao(OrdemServico ordemServico,
			Integer idLocalidade, Usuario usuario) throws ControladorException;

	/**
	 * Atualiza os imoveis da OS que refere a RA passada como parametro
	 * 
	 * @author S�vio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public void atualizarOsDaRA(Integer idRA, Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Raphael Rossiter
	 * @date 25/01/2007
	 * 
	 * @param idOS
	 * @return fiscalizacaoSituacao
	 * @throws ControladorException
	 */
	public void verificarOSJaFiscalizada(Integer idOS)
			throws ControladorException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * [FS0001] - Validar Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 29/01/2007
	 */
	public void validarOrdemServicoInformarRetornoOrdemFiscalizacao(
			OrdemServico ordemServico) throws ControladorException;

	/**
	 * [UC0539] Manter Prioridade do Tipo de Servi�o
	 * 
	 * Remove um ou mais objeto do tipo ServicoTipoPrioridade no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 12/02/2007
	 * @param ids
	 * @return void
	 * @throws ControladorException
	 */
	public void removerPrioridadeTipoServico(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0455] Exibir Calend�rio para Elabora��o ou Acompanhamento de Roteiro
	 * 
	 * @author Raphael Rossiter
	 * @date 14/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaOSProgramacao(Integer idProgramacaoRoteiro)
			throws ControladorException;

	/**
	 * Rotina Batch que encerra todas as OS de um servi�o tipo especifico que
	 * n�o tenha RA
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 23/02/2007
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSDoServicoTipoSemRA(Usuario usuarioLogado,
			Integer idServicoTipo) throws ControladorException;

	/**
	 * 
	 * Pesquisar data e equipe da programa��o da ordem servi�o
	 * 
	 * @author Ana Maria
	 * @date 09/03/2007
	 */
	public OrdemServicoProgramacao pesquisarDataEquipeOSProgramacao(Integer idOs)
			throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o [FS0012]
	 * Reordena Sequencial de Programa��o
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenarSequencialProgramacao(Date dataRoteiro,
			Integer idEquipe) throws ControladorException;

	/**
	 * [UC0513] Manter Tipo Retorno OS Referida
	 * 
	 * Remover Tipo Retorno OS Referida
	 * 
	 * @author Thiago Ten�rio
	 * @date 19/03/2007
	 * 
	 */
	public void removerTipoRetornoOrdemServicoReferida(String[] ids, Usuario usuarioLogado)
			throws ControladorException;
	
	
	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * Pesquisa a opera��o que faz parte da associa��o com o SERVICO_TIPO na
	 * tabela SERVICO_TIPO_OPERACAO
	 * 
	 * @author Raphael Rossiter
	 * @date 26/04/2007
	 * 
	 * @param idServicoTipo, integracaoComercialHelper
	 * @throws ControladorException
	 */
	public Integer pesquisarServicoTipoOperacao(Integer idServicoTipo) throws ControladorException ;

	
	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas
	 *
	 * @author Ivan S�rgio, Raphael Rossiter
	 * @date 08/11/2007, 17/04/2009
	 *
	 * @param helper
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection filtrarImovelEmissaoOrdensSeletivas(ImovelEmissaoOrdensSeletivasHelper helper) 
		throws ControladorException ;
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * M�todo que � chamado pelo [UC0713] Emitir Ordem de Servico Seletiva
	 * 
	 * @author Ivan S�rgio
	 * @date 27/11/2007
	 */
	public Integer gerarOrdemServicoSeletiva(
			OrdemServico ordemServico,
			UnidadeOrganizacional unidadeOrganizacional,
			Imovel imovel,
			Usuario usuario) throws ControladorException;
	
	/**
	 * 
	 * [UC0732] - Gerar Relatorio Acompanhamento de Ordens de Servico Hidrometro
	 * 
	 * @author Ivan S�rgio
	 * @date 27/11/2007, 27/03/2008
	 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdemServico
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa,
			String tipoOrdem,
			String situacaoOrdemServico,
			String idGerenciaRegional,
			String[] colecaoCapacidadeHidrometro,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String dataExecucaoInicial,
			String dataExecucaoFinal,
			String idMotivoEncerramento,
			String idSetorComercialIncial,
			String idSetorComercialFinal,
			String codigoQuadraInicial,
			String codigoQuadraFinal,
			String codigoRotaInicial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal
			) throws ControladorException;

	
	/**
	 * Este caso de uso permite a exporta��o de ordem de servi�o das 
	 * prestadoras.
	 *
	 * [UC0720] Exportar Ordem de Servi�o Prestadoras 
	 *
	 * @author Pedro Alexandre 
	 * @date 21/12/2007
	 *
	 * @param colecaoTramite
	 * @return
	 * @throws ControladorException
	 */
	public Map exportarOrdemServicoPrestadoras(Collection colecaoTramite) throws ControladorException ;

	
	/**
	 * [UC0720] - Exportar Ordem de Servi�o Prestadoras
	 * 
	 * [SB0002] - Gerar ocorr�ncia na tabela OrdemServicoPavimento
	 *
	 * @author Pedro Alexandre
	 * @date 07/01/2008
	 *
	 * @param ordemServicoPavimento
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirOrdemServicoPavimento(OrdemServicoPavimento ordemServicoPavimento) throws ControladorException ;

	
	
	/**
	 * [UC0640] Gerar TXT A��o de Cobran�a
	 * 
	 * @author Raphael Rossiter
	 * @date 04/01/2008
	 * 
	 * @param idCobrancaDocumento
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarOrdemServicoPorCobrancaDocumento(Integer idCobrancaDocumento)
			throws ControladorException ;
	
	/**
	 * [UC0734] Encerrar Ordem Servico Vencida
	 * 
	 * @author Ivan S�rgio
	 * @date 14/01/2008
	 * 
	 * @param idServicoTipo
	 * @param quantidadeDias
	 * @return totalOrdemServicoEncerradas
	 * @throws ControladorException
	 */
	public Integer encerrarOrdemServicoVencida(Integer idServicoTipo, Integer quantidadeDias)
			throws ControladorException;
	
	
	/**
	 * [UC0479] Gerar D�bito da Ordem de Servi�o
	 * 
	 * [FS0002] Verificar Exist�ncia do Tipo de D�bito 
	 * [FS0003] Validar Valor do D�bito 
	 * [FS0004] Validar Quantidade de Parcelas
	 * 
	 * @author Raphael Rossiter
	 * @date 10/03/2008
	 * 
	 * @param ordemServicoId
	 * 
	 * @throws ControladorException
	 */
	public DebitoTipo validacaoGerarDebito(Integer idDebitoTipo, BigDecimal valorDebito, int qtdeParcelas) 
		throws ControladorException ;
	
	
	/**
	 * [UC0479] Gerar D�bito da Ordem de Servi�o
	 * 
	 * Persiste Debito a Cobrar Geral
	 * 
	 * @author Leonardo Regos
	 * @date 12/09/2006
	 * 
	 * @param debitoACobrar
	 * @throws ControladorException
	 */
	public DebitoACobrarGeral inserirDebitoACobrarGeral() throws ControladorException ;
	
	/**
	 * [UC0479] Gerar D�bito da Ordem de Servi�o
	 * 
	 * Persiste Debito a Cobrar
	 * 
	 * @author Leonardo Regos
	 * @date 12/09/2006
	 * 
	 * @throws ControladorException
	 */
	public DebitoACobrar inserirDebitoACobrar(BigDecimal valorDebito,
			int qtdeParcelas, OrdemServico os, DebitoTipo debitoTipo,
			DebitoACobrarGeral debitoACobrarGeral, BigDecimal percentualTaxaJurosFinanciamento,
			RegistroAtendimento registroAtendimento, Usuario usuarioLogado) throws ControladorException ;
	
	/**
	 * [UC0479] Gerar D�bito da Ordem de Servi�o
	 * 
	 * Persiste Debito a Cobrar Categoria
	 * 
	 * @author Leonardo Regis
	 * @date 12/09/2006
	 * 
	 * @param colecaoCategoria
	 * @param colecaoValores
	 * @throws ControladorException
	 */
	public void inserirDebitoACobrarCategoria(
			Collection<Categoria> colecaoCategoria,
			Collection<BigDecimal> colecaoValoresCategorias,
			DebitoACobrar debitoACobrar) throws ControladorException ;
	
	/***
	 * [FS0753] Manter Ordem de Servico Concluida
	 * 
	 * @author Ivan S�rgio
	 * @date 26/03/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ControladorException
	 */
	public ManterOrdemServicoConcluidaHelper pesquisarOrdemServicoConcluida(Integer idOrdemServico) throws ControladorException;
	
	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de Hidrometro
	 * 			RELATORIO: Relacao das Ordens de Servico Concluidas
	 * 
	 * @author Ivan S�rgio
	 * @date 04/04/2008
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdem
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoConcluidaGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa,
			String tipoOrdem,
			String situacaoOrdemServico,
			String idGerenciaRegional,
			String[] colecaoCapacidadeHidrometro,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String dataExecucaoInicial,
			String dataExecucaoFinal,
			String idMotivoEncerramento,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String codigoQuadraincial,
			String codigoQuadraFinal,
			String codigoRotaIncial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal) throws ControladorException;
	
	/**
	 * [UC0753] Manter Ordem Servico Concluida
     * 
     * @author Ivan S�rgio
     * @date 09/04/2008
	 * 
	 * @param idOrdemServico
	 * @param situacaoFiscalizacao
	 * @param dataFiscalizacao1
	 * @param dataFiscalizacao2
	 * @param dataFiscalizacao3
	 * @param idFuncionario
	 * @throws ControladorException
	 */
	public void atualizarDadosFiscalizacao(
			Integer idOrdemServico,
			Short situacaoFiscalizacao,
			String dataFiscalizacao1,
			String dataFiscalizacao2,
			String dataFiscalizacao3,
			Integer idFuncionario,
			Short situacaoFiscalizacaoAnterior) throws ControladorException;
	
	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
     * 
     * @author Ivan S�rgio
     * @date 29/04/2008
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param anoMesReferenciaBoletim
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarResumoOrdensServicoConcluidas(
			Integer idEmpresa,
			Integer idLocalidade,
			String anoMesReferenciaBoletim) throws ControladorException;
	
	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
     * 
     * @author Ivan S�rgio
     * @date 02/05/2008
	 * 
	 * @param colecaoIdOrdemServico
	 * @throws ControladorException
	 */
	public void encerrarBoletimOrdemServicoConcluida(Collection colecaoIdOrdemServico) throws ControladorException;

	
	
	
	

	/**
	 * Pesquisa Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Yara Taciane
	 * @date 02/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacao(OSPavimentoHelper oSPavimentoHelper, 
			Integer numeroPagina)throws ControladorException;

	
	/**
	 * Pesquisa Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Yara Taciane
	 * @date 02/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioOrdemProcessoRepavimentacao(OSPavimentoHelper pavimentoHelper, 
			String relatorioCompletoOuDivergente)throws ControladorException;

	
	/**
	 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
     * 
     * @author Ivan S�rgio
     * @date 07/05/2008
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param referenciaEncerramento
	 * @param situacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimOrdensServicoConcluidasGerarRelatorio(
			Integer idEmpresa,
			Integer idLocalidade,
			Integer idSetorComercial,
			String referenciaEncerramento,
			Short situacao) throws ControladorException;
	
    /**
     * [UC0720] Exportar Ordem de Servi�o Prestadoras
     * 
     * @author Vivianne Sousa
     * @date 02/04/2008
     * 
     * @throws ControladorException
     */
    public void atualizarIndicadorEncerramentoAutomaticoOS(Short indicadorEncerramentoAutomatico,
            Integer idOrdemServico) throws ControladorException;
    
    /**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 *
	 * [FS0006] Verificar data de emiss�o
	 * [FS0007] Verificar data de in�cio do recurso
	 * [FS0008] Verificar data de t�rmino do recurso
	 *
	 * @author Raphael Rossiter
	 * @date 05/09/2008
	 *
	 * @param dadosAutoInfracao
	 * @param documentoEntregue
	 * @throws ControladorException
	 */
	public void validarDadosAutoInfracaoRetornoOSFiscalizacao(
		DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao,
		Short documentoEntregue) throws ControladorException ;

    
	/**
     * [UC0427] Tramitar Registro de Atendimento
     * 
     * @author Vivianne Sousa
     * @date 10/06/2008
     * 
     * @throws ControladorException
     */
    public void atualizarUnidadeOrganizacionalAtualOS(Integer idUnidadeOrganizacionalAtual,
            Integer idRA)throws ControladorException;
    
	/**
	 * [UC0427] - Tramitar Registro Atendimento
	 * [FS0011]Validar Tramite para terceira
	 * 
	 * @author Vivianne Sousa
	 * @date 29/10/2008
	 */
	public boolean existeMaisDeUmaOrdemServicoPendente(Integer idregistroAtendimento)
			throws ControladorException;
	
	/**
	 * [UC0488] - Consultar Dados do Registro de Atendimento 
	 * 
	 * Dados da Os Associadas
	 * 
	 * @author Arthur Carvalho
	 * @date 17/02/2009
	 */
	public Collection<OrdemServicoHelper> pesquisarOrdensServicosAssociados(Integer idregistroAtendimento) 
		throws ControladorException ;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * [SB0004] - Calcular Valor de �gua e/ou Esgoto
	 * 
	 * 
	 * @author S�vio Luiz, Raphael Rossiter
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public ArrayList<BigDecimal> calcularValorAguaEsgoto(int consumoInformado,
			SistemaParametro sistemaParametro, Integer idImovel,
			Integer idSituacaoEncontrada, Integer idLigacaoAguaSituacaoImovel, Integer idLigacaoEsgotoSituacaoImovel) throws ControladorException;
	
	/**
	 * Retorna a quantidade de registros para geracao do relatorio
	 * 
	 * [UC0711] Filtro para Emissao de Ordens Seletivas 
	 * 
	 * @author Anderson Italo
	 * @date 21/08/2009
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer filtrarImovelEmissaoOrdensSeletivasCount(ImovelEmissaoOrdensSeletivasHelper helper) 
		throws ControladorException;
	
	
	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0002] - Validar Tipo Servi�o [FS0004] - Verificar preenchimento dos
	 * campos [FS0007] - Validar Data de Encerramento [FS0008] - Validar Data do
	 * roteiro
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 15/01/2010
	 * 
	 * @throws ControladorException
	 */
	public void validarCamposEncerrarOSPopup(String indicadorExecucao,
			String numeroOS, String motivoEncerramento,
			String dataEncerramento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			String tipoServicoReferenciaId, String indicadorPavimento,
			String pavimento, String idTipoRetornoOSReferida,
			String indicadorDeferimento, String indicadorTrocaServicoTipo,
			String idServicoTipo, String dataRoteiro, String idRA,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
            String indicadorDiagnostico, String observacaoEncerramento, Usuario usuario,
            String idPavimentoRua, String metragemPavimentoRua, String idPavimentoCalcada,
            String metragemPavimentoCalcada, String idUnidadeRepavimentadora)
			throws ControladorException;
	
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 *  
	 * @author Hugo Amorim	
	 * @date 12/02/2010
	 */
	public Integer gerarOrdemServicoFiscalizacao(OrdemServico ordemServico,Usuario usuario, boolean cobrarValorServico, Integer codigoConstanteServicoTipo)
			throws ControladorException;
	
	/**
	 * Pesquisa Servico Tipo por codigo da constante.
	 *  
	 * @author Hugo Amorim	
	 * @date 18/02/2010
	 */
	public ServicoTipo recuperaServicoTipoPorConstante(Integer codigoConstate)
		throws ControladorException;

	/**
	 * Pesquisa Count Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Arthur Carvalho
	 * @date 22/02/2010
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoCount(OSPavimentoHelper oSPavimentoHelper)
			throws ControladorException;
	
	/** 
	 * [UC0457] Encerrar Ordem de Servi�o
	 * [SB0006] � Obter Unidade Repavimentadora do Munic�pio
	 * 
	 * @author Arthur Carvalho
	 * @date 12/04/2010
	 *
	 */
	public UnidadeOrganizacional obterUnidadeRepavimentadorAPartirMunicipio(OrdemServico os,
			String tipoPesquisa) throws ControladorException;

	/**
	 * @author Arthur Carvalho
	 * @date 12/04/2010
	 * [UC0457] Encerrar Ordem de Servi�o
	 * [FS0011 � Verificar exist�ncia da unidade repavimentadora];
	 * @param idUnidadeRepavimentadora
	 */
	public void verificaUnidadeTipoRepavimentadora(String idUnidadeRepavimentadora) throws ControladorException;
	
	/**
	 * Pesquisa Ordens de Repavimenta��o em Processo de Aceite.
	 * 
	 * [UC1019] Filtrar Ordens de Repavimeta��o em Processo de Aceite.
	 * 
	 * @author Hugo Leonardo
	 * @date 17/05/2010
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemRepavimentacaoProcessoAceite(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoHelper, Integer numeroPagina)
		throws ControladorException;
	
	/**
	 * Pesquisa quantidade Ordens de Repavimenta��o em Processo de Aceite.
	 *
	 * [UC1019] Filtrar Ordens de Repavimeta��o em Processo de Aceite.
	 *
	 * @author Hugo Leonardo.
	 * @date 17/05/2010
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoAceiteCount(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoAceiteHelper) 
		throws ControladorException;
	
	/**
	 * [UC1020]	- Exibir Ordens de Repavimenta��o em Processo de Aceite.
	 * 
	 * Verificar se existe Ordem de Repavimenta��o em Aceite entre as Ordens selecionadas.
	 * 
	 * @author Hugo Leonardo
	 * @param Collection
	 * @throws ControladorException
	 * @data 21/05/2010
	 */
	public void verificarExistenciaRepavimentacaoAceite(Collection colecaoOSPavimentoSelecionados) 
			throws ControladorException;
	
	/**
	 * [UC1020]	- Exibir Ordens de Repavimenta��o em Processo de Aceite.
	 * 
	 * Aceitar as Ordens de Servi�o em Processo de Repavimentacao Convergente.
	 * 
	 * @author Hugo Leonardo
	 * @param OrdemRepavimentacaoProcessoAceiteHelper
	 * @throws ControladorException
	 * @data 24/05/2010
	 */
	public void aceitarOSRepavimentacaoConvergente(OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper, Usuario usuario,
			Date dataAceite, Short indicadorSituacaoAceite) throws ControladorException;
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 *  
	 * @author Hugo Amorim	
	 * @date 12/02/2010
	 */
	public Integer gerarOrdemServicoCorte(OrdemServico ordemServico,Usuario usuario)
			throws ControladorException;
	
	/**
	 * Pesquisar Servi�o Tipo Seletivo
	 * 
	 * Seleciona Servi�o Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 26/07/2010
	 * 
	 */
	public ServicoTipo recuperaServicoTipoPorConstanteServicoTipoSeletivo(Integer codigoConstate)
		throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Seleciona OSFS_DTFISCALIZACAOSITUACAO da tabela ORDEM_SERVICO_FISC_SIT 
	 * para ORSE_ID=ORSE_ID da tabela ORDEM_SERVICO
	 * 
	 * @author Vivianne Sousa
	 * @date 28/07/2010

	 * 
	 */
	public Date recuperaDataFiscalizacaoSituacao(
			Integer idOrdemServico,Integer idFiscalizacaoSituacao) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 29/07/2010
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2010
	 */
	public OrdemServico recuperaOrdemServico(
			Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacao(
			Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacaoSelecionada(
			Integer idOrdemServico) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2010
	 */
	public Collection recuperaOrdemServicoFiscSit(
			Integer idOrdemServico) throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * [SB0003] � Calcular/Inserir Valor
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorDebitoOS(Integer indicadorDebito,
			Integer idFiscalizacaoSituacao, Integer idOS)throws ControladorException;
	
	/**
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * @author Vivianne Sousa
	 * @date 30/08/2010
	 * 
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSitComMenorDataFiscalizacao(
			Integer idOrdemServico) throws ControladorException;
	
	/**
	 * [UC0441] Consultar Dados da Ordem de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2010
	 */
	public Collection pesquisaOrdemServicoFiscSit(
			Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 19/08/2010
	 */
	public Short recuperaIndicadorDebitoDaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao)throws ControladorException;
	
	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimCustoPavimento(FiltrarBoletimCustoPavimentoHelper relatorioHelper) 
		throws ControladorException;
	
	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper) 
		throws ControladorException;
	
	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper) 
		throws ControladorException;
	

	/**
	 * Filtrar as Ordens de Servi�o dos Comandos de A��o de Cobran�a
	 * 
	 * [UC1098] Informar N�o Aceita��o de Motivo de Encerramento Ordem de Servi�o
	 * 
	 * @author Mariana Victor
	 * @date 13/12/2010
	 * 
	 * @return filtroCobrancaAcao
	 * @throws ControladorException
	 */
	public FiltroOrdemServico construirFiltroOrdemServico(
			String grupoCobranca, String acaoCobranca,
			String anoMesPeriodoReferenciaContasInicial,
			String anoMesPeriodoReferenciaContasFinal)
			throws ControladorException;

	/**
	 * [UC1098] Informar N�o Aceita��o de Motivo de Encerramento Ordem de Servi�o
	 * 
	 * @author Mariana Victor
	 * @date 23/12/2010
	 */
	public OrdemServico pesquisarOS(Integer id)
			throws ControladorException;
	
	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 18/01/2011
	 */
	public ServicoTipo recuperaServicoTipoDaOrdemServico(
			Integer idOrdemServico)throws ControladorException;     
	
	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 21/01/2011
	 */
	public ServicoTipoBoletim recuperaServicoTipoBoletimDoServicoTipo(
			Integer idServicoTipo)throws ControladorException;
	
	/**
	 * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSEDadosImovel(Integer idOS)
		throws ControladorException;
	
	/**
	 * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletimDaOS(
			Integer idOrdemServico)throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	public Collection recuperaOSExecutadas(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,
			String[] idsMunicipio) throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadas(Date dataInicial,Date dataFinal, 
		Integer idLocalidade) throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * [SB0003] � Gerar Anal�tico TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarTxtOSExecutadasPrestadoraServico(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,String[] idsRegiao,
			String[] idsMicroregiao,String[] idsMunicipio,String header)throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,String[] idsRegiao,
			String[] idsMicroregiao,String[] idsMunicipio)throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 04/05/2011
	 */
	public Integer recuperaTotalOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,
			String[] idsMunicipio) throws ControladorException ;
	
	/**
	 * M�todo que valida a ordem de servi�o utilizado por diversos outros
	 * m�todos do atendimento ao p�blico
	 * 
	 * @author Leonardo Regis,Vivianne Sousa
	 * @date 22/09/2006,19/05/2011
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServicoDiasAditivoPrazo(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * Atualiza o documento de cobran�a da ordem de servi�o que foi 
	 * gerado pelo "[UC0444 Gerar e Emitir Extrato de D�bito]"
	 * 
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * @author Mariana Victor
	 * @date 19/05/2011
	 */
	public void atualizarDocumentoCobrancaOS(Integer idOrdemServico,
			Integer idCobrancaDocumento) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * 
	 * [FS0001] � Validar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer pesquisarOSFiscalizacaoPendente(Integer numeroOS)throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0001] - Selecionar Ordens de Servi�o 
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Map recuperaQtdeOSEncerrada(Integer idGrupoCobranca,Integer idGerencia, 
		Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
		Integer idTipoServico, Integer qtdeDiasEncerramentoOS) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0002] � Verificar Ordem Servi�o  
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarIdMotivoEncerramentoOS(Integer idOrdemServico)throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0003] - Gerar V�rias Ordens de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection gerarVariasOsFiscalizacao(Integer idGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, 
			BigDecimal percentualOSgeradas, Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0004] � Gerar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 27/05/2011
	 */
	public Collection gerarOrdemServicoFiscalizacao(Collection colecaoOrdemServicoSelecionadas,
			ServicoTipo servicoTipo,Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0005] � Gerar Formul�rio em formato pdf
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaDadosOsFiscalizacao(Collection colecaoOSFiscalizacao,
			Integer idGrupoCobranca) throws ControladorException;
	
	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas
	 * [SB0001]-Gerar Comando. 
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2011
	 *
	 * @param helper
	 * @throws ControladorException
	 */
	public Integer gerarComando(ImovelEmissaoOrdensSeletivasHelper helper, int quantidadeOs,Usuario usuarioLogado) 
		throws ControladorException;
	
	/**
	 * [UC0713] Emitir Ordem de Servi�o Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 28/06/2011
	 */
	public void gerarTxtOsInspecaoAnormalidade(Integer idFuncionalidadeIniciada, 
			Integer idComandoOrdemSeletiva,Integer qtdAnormalidadesConsecutivas)
		throws ControladorException;
	
	/**
	 * [UC0412] Manter Tipo de Servi�o
	 * [SB0003] Atualizar Grau de Import�ncia
	 *
	 * @author Th�lio Ara�jo
	 * @date 30/06/2011
	 * 
	 * @param servicoTipo
	 * @param usuario
	 * @throws ControladorException
	 */
	public void atualizarGrauImportanciaServicoTipo(ServicoTipo servicoTipo, Usuario usuario)
			throws ControladorException;
	

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @since 11/07/2011, 14/09/2011
	 * 
	 * @param idEmpresa
	 * @param dataInicial
	 * @param dataFinal
	 * @param numeroIndice
	 * @param quantidadeRegistros
	 * @param qtdeDiasValidadeOSAnormalidadeFiscalizacao
	 * @param comandoEncerrado - Caso a pesquisa retorne apenas os comandos encerrados.
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosComandoOSSeletivaResumido(
			Integer idEmpresa, Date dataInicial, Date dataFinal,
			 int numeroIndice,int quantidadeRegistros, 
			 Integer qtdeDiasValidadeOSAnormalidadeFiscalizacao, boolean comandoEncerrado)throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 12/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarDadosComandoOSSeletiva(
			Integer idComandoOrdemSeletiva)throws ControladorException ;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosAnormalidadeComandoOSS(
			Integer idComandoOrdemSeletiva) throws ControladorException;

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosCapacidHidrComandoOSS(
			Integer idComandoOrdemSeletiva) throws ControladorException;


	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosLigacaoSitComandoOSS(
			Integer idComandoOrdemSeletiva) throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 15/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarComandoOSSeletiva(
			Integer idComandoOrdemSeletiva)  throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 18/07/2011
	 */
	public Collection pesquisarDadosOSEmitir(Integer idComando,
			Integer numeroOSInicial, Integer numeroOSFinal) throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * [FS0001] - Verificar se ordem de servi�o faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public String retornaOsNaoFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosOSPesquisar)  throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * [FS0003] � Verificar se im�vel faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public String retornaImovelNaoFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosImoveisPesquisar) throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * Encerrar ordem(ns) de servi�o.
	 * 
	 * @author: Vivianne Sousa
	 * @date: 20/07/2011
	 */
	public void movimentarOrdemServicoEncerrarOS(Usuario usuarioLogado,
			MovimentarOrdemServicoEncerrarOSHelper helper) throws ControladorException;
	
	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 *
	 * @author Vivianne Sousa
	 * @created 21/07/2011
	 */
	public void encerrarComandoOSSeletivaInspecaoAnormalidade(Integer idFuncionalidadeIniciada,
			Usuario usuarioLogado, Integer idComando,Short idMotivoEncerramento) throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 26/07/2011
	 */
	public Integer pesquisaOrdemServicoNaoPendenteFazParteComando(
			Integer idComandoOrdemSeletiva) throws ControladorException;
	

	/**
	 * [UC0412] Manter Tipo de Servi�o
	 * 
	 * Metodo respons�vel por deletar motivos de
	 * encerramento a partir de um tipo de servi�o  
	 * 
	 * @author Raimundo Martins
	 * @date 26/07/2011
	 * 
	 * @param idServicoTipo
	 * 
	 */
	public void removerServicoTipoMotivoEncerramento(Integer idServicoTipo) throws ControladorException;
	
	
	/**
	 * [UC0412] Manter Tipo de Servi�o
	 * 
	 * Metodo respons�vel por inserir
	 * um motivo de encerramento para
	 * um tipo de servi�o  
	 * 
	 * @author Raimundo Martins
	 * @date 27/07/2011
	 * 
	 * @param servicoTipoMotivoEncerramento
	 * 
	 */
	
	
	/**
	 * [UC0412] Manter Tipo de Servi�o
	 * 
	 * Metodo respons�vel por inserir
	 * um motivo de encerramento para
	 * um tipo de servi�o  
	 * 
	 * @author Raimundo Martins
	 * @date 27/07/2011
	 * 
	 * @param servicoTipoMotivoEncerramento
	 * 
	 */
	public void inserirServicoTipoMotivoEncerramento(ServicoTipoMotivoEncerramento servicoTipoMotivoEncerramento) throws ControladorException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS 
	 * 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public void inserirProgramacaoAutomaticaAcompanhamentoOS(UnidadeOrganizacional unidadeOrganizacional,Date dataProgramacao,int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 30/07/2011
	 */
	public Collection pequisarUnidadesOrganizacionaisdasEquipes() throws ControladorException;
	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * 
	 * 
	 * @author Th�lio Ara�jo
	 * @date 28/07/2011
	 * 
	 * @param ids
	 * @param situacaoNova
	 */
	public void atualizarArquivoTextoAcompArqRoteiro(Vector<Integer> ids, Integer situacaoNova)
		throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * Verificar se ordem de servi�o que faz parte do comando ja esta encerrada
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2011
	 */
	public String retornaOsJaEncerrada(List<Integer> numerosOSPesquisar)  throws ControladorException;
	
	/**
	 * 
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * @author Bruno Barros
	 * @date 28/06/2011
	 * 
	 * @param idsEquipes - Id's das equipes que ter�o seus roteiros gerados. Caso esse
	 *                     parametro venha nulo, iremos gerar de todas as equipes que
	 *                     possuam OS programadas
	 * @param dataServico - Data do servi�o a ser gerado.
	 * 
	 * @throws ControladorException
	 */
	public void gerarArquivoAcompanharServicoRoteiroProgramado(
			Integer idFuncionalidadeIniciada,
			UnidadeOrganizacional unidadeOrganizacional,
			Date dataServico ) throws ControladorException;	
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * [SB0002] - Nome do arquivo texto  
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna o nome do arquivo que ser� gerado
	 * 
	 * @throws ControladorException
	 */
	public String nomeArquivoAcompanhamentoServico( Equipe equipe ) 
		throws ControladorException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * [SB0003] - Nome do arquivo texto  
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * 
	 * @return String - retorna o arquivo
	 * 
	 * @throws ControladorException
	 */
	public byte[] gerarArquivoTextoOrdensServicoAcompanhamentoEquipe( Equipe equipe, Date dataRoteiro , boolean gerarTabelasBasicas, boolean arquivoOnLine)
		throws ControladorException;
	

	
	/**
	 * @author Rodrigo Cabral
	 * @date 11/07/2011
	 * 
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAgua(Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada)
			throws ControladorException;
	
	/**
	 * @author Rodrigo Cabral
	 * @date 11/07/2011
	 * 
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgoto(Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada)
			throws ControladorException;

	/**	 
	 * [UC1199] � Acompanhar Arquivos de Roteiro	 
	 * Pesquisa a(s) equipe(s) da OS Programacao Acompanhamento Servico 
	 * filtrando pelo identificador da Ordem de Servi�o
	 * 
	 * @author Raimundo Martins
	 * @date 09/08/2011
	 * 
	 * @param idOrdemServi�o
	 * @return Collection<Integer>	 
	 * @throws ControladorException	 */		
	
	public Collection<Integer> pesquisarEquipeOSProgramacaoAcompServicoPorIdOrdemServico(Integer idOrdemServico) throws ControladorException;		
	

	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Servi�o na Programa��o 
	 * @author Th�lio Ara�jo
	 * @date 19/07/2011
	 */
	public ProgramacaoRoteiro pesquisarProgramacaoRoteiro(Integer idUnidadeOrganizacional,Date dataProgramacao)
			throws ControladorException;
	
	/**
	 * [UC1211] Inserir Ordem Programa��o Acompanhamento Servi�o
	 * 
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Id da equipe a ter as ordens incluidas
	 * @param dataRoteiro - Data para a incluis�o das OS
	 * 
	 * @return void...
	 * 
	 * @throws ControladorException
	 */	
	public void inserirOrdemProgramacoAcompanhamentoServico( Integer idEquipe, Date dataRoteiro,boolean programacaoAutomatica ) throws ControladorException;

	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ControladorException
	 */	
	public Collection pesquisarOrdensServicoFiscalizacao(int tipoRelatorio, String periodoInicial, String periodoFinal, String idGerenciaRegional, String idUnidadeNegocios,String idLocalidadeInicial, String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS) throws ControladorException;
	
	/**
	 * [UC1205] � Remanejar Ordem de Servico
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por Equipe
	 * 
	 * @author Th�lio Ara�jo
	 * @date 22/08/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return Date - data da OS Programacao Acompanhamento Servico
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSProgramacaoAcompServicoPorEquipeOS(
			Integer idOrdemServico, Date dataProgramacao, Integer idEquipe) throws ControladorException;
	
	/**
	 * [UC1205] - Remanejar Ordem de Servico
	 * 
	 * [FS0007] Validar carga de trabalho da Equipe   
	 * 
	 * @author Th�lio Ara�jo
	 * @date 22/08/2011 ---
	 */
	public boolean validarCargaTrabalhoEquipe(Integer idEquipeRemanejada, Integer idProgramacaoRoteiro,Integer idOrdemServico,Integer idUnidadeOrganizacional) throws ControladorException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public OrdemServico pesquisarOrdemServicoFiscalizada(Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * Reordena Sequencial de Programa��o Acompanhamento de Servi�o
	 * 
	 * @author Th�lio Ara�jo
	 * @date 27/08/2011
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public short reordenaSequencialOSProgramacao(Date dataRoteiro,
			short sequencialInformado, Integer idEquipe, Integer idArquivo)
			throws ControladorException;
	
	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data
	 * informada, que ainda n�o foram encaminhadas para o campo.
	 * 
	 * @author Th�lio Ara�jo
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro - Data para a pesquisa das OS 
	 * 
	 * @return Collection<Integer> - Cole��o com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> 
		pesquisarEquipesOSNaoEnviadasProgramadas(Integer idUnidadeLotacao, Date dataRoteiro, Integer idEquipe ) 
			throws ControladorException;
	
	/**
	 * [UC1206] Informar Situa��o da Ordem de Servi�o
	 * 
	 * 
	 * @author Th�lio Ara�jo
	 * @date 29/08/2011
	 * 
	 * @param idEquipe - Id da equipe atual
	 * @param dataRoteiro - Data para a incluis�o das OS
	 * 
	 * @return void...
	 * 	
	 * @throws ControladorException
	 */	
	public void atualizarOrdemProgramacaoAcompServicoInformarSituacao( Integer idArquivo, Date dataRoteiro, Integer idOrdemServico,
			Short novaSituacao, Integer idOsProgramNaoEncerMotivo) throws ControladorException;
	
	/**
	 * [UC0467] Atualizar Ordem Servi�o
	 * 
	 * Atualiza os dados da tabela de acompanhamento de servi�o de acordo com os dados informados.
	 * 	  
	 * @author Th�lio Ara�jo
	 * @date 31/08/2011
	 * 
	 * @param idEquipe - Id da equipe atual
	 * @param dataRoteiro - Data para a incluis�o das OS
	 * 
	 * @return void...
	 * 	
	 * @throws ControladorException
	 */	
	public void atualizarOSProgramacaoAcompServico( Integer idArquivo, Date dataRoteiro, Integer idOrdemServico, Integer idServicoTipo) throws ControladorException;
	
	/**
	 * [UC-1209] Acompanhar Servi�os no Dispositivo M�vel
	 * 
	 * M�todo que ir� pesquisar o arquivo que ser� carregado no
	 * celular
	 * 
	 * @autor Bruno Barros
	 * @date 31/08/2011
	 * 
	 * @param imei - Imei do aparalho que ir� receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public byte[] baixarArquivoTextoAcompanhamentoServico( long imei ) throws ControladorException;	
	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Th�lio Ara�jo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idArquivo) throws ControladorException;
	
	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Servi�o por Equipe e Data Roteiro
	 * 
	 * @author Th�lio Ara�jo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAcompanhamentoServico 
		pesquisarArquivoTextoAcompanhamentoServicoEquipe( 
			Integer idEquipe,
			Date dataRoteiro) 
			throws ControladorException;
	
	/**
	 * [UC1199] � Acompanhamento Arquivos Roteiro Remanejar Ordem Servico Action
	 * 
	 * @author Th�lio Ara�jo
	 * @date 06/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
			throws ControladorException;
	
	/**
	 * Reordena Sequencial de Programa��o Acompanhamento de Servi�o
	 * 
	 * @author Th�lio Ara�jo
	 * @date 27/08/2011
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialOSProgramacaoAcompServico(Date dataRoteiro,
			short sequencialInformado, Integer idEquipe, short sequencialAtual, Integer idArquivo)
			throws ControladorException;
	
	/**
	 * [UC-1220] Fiscaliza��o Anormalidade no Dispositivo M�vel
	 * 
	 * M�todo que ir� pesquisar o arquivo que ser� carregado no
	 * celular
	 * 
	 * @autor Davi Menezes
	 * @date 08/09/2011
	 * 
	 * @param imei - Imei do aparalho que ir� receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Object[] baixarArquivoTextoFiscalizacaoAnormalidade( long imei ) throws ControladorException;

	/**
	 * [UC1224] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * @author Th�lio Ara�jo
	 * @date 08/09/2011
	 * 
	 * @param imei
	 * 
	 * @return MensagemAcompanhamentoServico - Objeto de Mensagem
	 * 
	 * @throws ControladorException
	 */
	public String retornaMensagemAcompanhamentoArquivosRoteiroImei(long imei)
			throws ControladorException;
	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Collection obterColecaoSituacaoArquivoTexto() throws ControladorException; 
	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Collection obterColecaoAgenteComercial() throws ControladorException;

	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Collection<ArquivoTxtOSVisitaHelper> buscarColecaoArquivoTextoOSVisita(
			String idLocalidade, String idSetorComercialInicial,
			String idSetorComercialFinal, String idQuadraInicial,
			String idQuadraFinal, String idLeiturista, String idSituacaoArquivo) throws ControladorException;

	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 21/09/2011
	 *
	 */
	public void atualizarListaArquivoTextoOSVisita(
			Collection<ArquivoTxtOSVisitaHelper> colecaoArquivoTextoOSVisita,
			Integer idSituacaoLeituraNova, Leiturista leiturista,
			Date date) throws ControladorException;
	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 21/09/2011
	 *
	 */
	public boolean verificarArquivoTextoOSPendente(Integer idArquivoTextoVisitaCampo) throws ControladorException;
	
	
	/**
	 * [UC-1209] Acompanhar Servi�os no Dispositivo M�vel
	 * 
	 * M�todo que ir� pesquisar o arquivo que ser� carregado no
	 * celular
	 * 
	 * @autor Bruno Barros
	 * @date 31/08/2011
	 * 
	 * @param imei - Imei do aparalho que ir� receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public byte[] atualizarArquivoTextoAcompanhamentoServico( long imei ) throws ControladorException;
	
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * M�todo que insere o array de bytes vindo do celular
	 * e o insere no banco
	 * 
	 * @param numeroOS - Id da OS
	 * @param tipoFoto - Se essa foto foi do inicio do meio ou do fim da obra
	 * @param foto     - array de bytes com a foto em si
	 * 
	 * @throws FachadaException
	 */
	public void inserirFotoOrdemServico( int numeroOS, int tipoFoto, byte[] foto,Double coordenadaX, Double coordenadaY ) throws ControladorException;	
	
	
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * Altera a situa��o de uma ordem de servi�o no GSAN
	 * 
	 * @param numeroOS - Id da OS
	 * @param situacao - Id da nova situacao
	 * @param idEquipe    
	 *  
	 * @throws FachadaException
	 */	
	public void atualizarSituacaoProgramacaoOrdemServico( int numeroOS, short situacao, long imei ) throws ControladorException;
	
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 *
	 * Este caso de uso permite a inser��o de dados na tabela de ordem de servi�o para acompanhamento do servi�o.
	 * 
	 * @author Th�lio Ara�jo
	 *
	 * @date 22/09/2011
	 * @param buffer - BufferedReader com o arquivo selecionado
	 * 
	 * @throws ControladorException
	 */
	public void retornoAtualizarOrdemServicoAcompanhamentoServico(
			BufferedReader buffer) throws ControladorException;
	
	/**
	 * [UC-1209] Acompanhar Servi�os no Dispositivo M�vel
	 * 
	 * M�todo que ir� pesquisar o arquivo que ser� carregado no
	 * celular
	 * 
	 * @autor S�vio Luiz
	 * @date 31/08/2011
	 * 
	 * @param imei - Imei do aparalho que ir� receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarSituacaoArquivoTextoAcompanhamentoServico( long imei,Short idSituacaoTransmissaoLeituraAnterior,short idSituacaoTransmissaoLeituraAtual  ) throws ControladorException;
	
	/**
	 * [UC-1209] Acompanhar Servi�os no Dispositivo M�vel
	 * 
	 * M�todo que ir� inserir os dados da coordenada da equipe retornada do celular
	 * 
	 * @autor Th�lio Ara�jo
	 * @date 03/10/2011
	 * 
	 * @param imei
	 * @param arquivo
	 * @throws ControladorException
	 */
	public void inserirCoordenadaPercursoEquipe( long imei, String arquivo ) throws ControladorException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por idOs e idArquivoTexto
	 * 
	 * @author Th�lio Ara�jo
	 * @date 04/10/2011
	 * 
	 * @param idOs, idArquivoTexto
	 * @return OSProgramacaoAcompanhamentoServico
	 * @throws ControladorException
	 */
	public OSProgramacaoAcompanhamentoServico pesquisarOSProgramacaoAcompServicoPorIdArqTxt(
			Integer idOrdemServico, Integer idArqTexto) throws ControladorException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * @author Mariana Victor
	 * @data 11/10/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioSinteticoOSFiscalizacao(String periodoInicial, 
			String periodoFinal, String idGerenciaRegional, String idUnidadeNegocio,String idLocalidadeInicial, 
			String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS) 
		throws ControladorException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * @author Mariana Victor
	 * @data 11/10/2011
	 */
	public Collection<Object[]> pesquisarMotivosEncerramentoPorLocalidadeAnoMes(
			Date anoMes, Integer idLocalidade) throws ControladorException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * @author Mariana Victor
	 * @data 11/10/2011
	 */
	public Collection<Object[]> pesquisarTiposRetornoPorLocalidadeAnoMes(
			Date anoMes, Integer idLocalidade) throws ControladorException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Mariana Victor
	 * @date 11/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public Integer pesquisarQuantidadeOrdensServicoFiscalizacao(String periodoInicial, String periodoFinal, 
			String idGerenciaRegional, String idUnidadeNegocio,String idLocalidadeInicial, 
			String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS)
		throws ControladorException;

	
	/**
	 * [UC1193] - Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Paulo Diniz
	 * @date 13/09/2011
	 * 
	 * @param idComandoOrdemSeletiva
	 * 
	 * @return ArquivoTextoVisitaCampo
	 * 
	 * @throws ErroRepositorioException
	 */
	public int pesquisarArqvTxtVisitaCampoNaoFinalizadoPorComandoOrdemSeletiva(Integer idComandoOrdemSeletiva) throws ControladorException;
	
	/**
	 * [UC1193] - Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Paulo Diniz
	 * @date 13/09/2011
	 * 
	 * @param idComandoOrdemSeletiva
	 * 
	 * @return ArquivoTextoVisitaCampo
	 * 
	 * @throws ErroRepositorioException
	 */
	public int pesquisarArqvTxtVisitaCampoPorComandoOrdemSeletiva(Integer idComandoOrdemSeletiva) throws ControladorException;
	
	/**
	 * [UC1221] - Gerar Boletim de Medi��o Ordem de Servi�o Inspe��o Anormalidade
	 * 
	 * M�todo que vai retornar as ordens de servi�o geradas a partir de um comando
	 * de ordem de servi�o seletiva do tipo de inspe��o de anormalidade gerado.
	 * 
	 * @author Diogo Peixoto
	 * @since 15/09/2011
	 * 
	 * @param FiltrarOrdensServicosComandoOrdemSeletivaHelper
	 * @return List<GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean>
	 * @throws ControladorException
	 */
	public List<GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean> 
		pesquisarRelatorioOrdensServicosComandoOrdemSeletiva(FiltrarOrdensServicosComandoOrdemSeletivaHelper helper) throws ControladorException;
	
	/**
	 * [UC1221] � Gerar Boletim de Medi��o Ordem de Servi�o Inspe��o Anormalidade
	 * 
	 * M�todo respons�vel por gerar o arquivo txt do relat�rio das ordens de servi�o
	 * dos comandos de ordem seletiva.
	 * 
	 * @author Diogo Peixoto
	 * @date 15/09/2011
	 * 
	 * @param FiltrarOrdensServicosComandoOrdemSeletivaHelper
	 * @return byte[]
	 * @throws ControladorException
	 */
	public byte[] emitirRelatorioOrdensServicosComandoOrdemSeletivaTxt(FiltrarOrdensServicosComandoOrdemSeletivaHelper helper) throws ControladorException;
	
	/**
	 * [UC 1232] - Registrar Retorno Arquivo Texto da Visita de Campo
	 * 
	 * M�todo Respons�vel por atualizar o arquivo texto de retorno das ordens de servi�os por abas.
	 * 
	 * @author Davi Menezes
	 * @date 22/09/2011
	 * 
	 * @param BufferedReader
	 * @throws ControladorException
	 */
	public void atualizarMovimentacaoFiscalizacaoAnormalidade(BufferedReader buffer) throws ControladorException;
	
	/**
	 * [UC 1232] - Registrar Retorno Arquivo Texto da Visita de Campo
	 * 
	 * M�todo respons�vel por finalizar a leitura das ordens de servi�os da Fiscaliza��o de Anormalidade.
	 * 
	 * @author Davi Menezes
	 * @date 23/09/2011
	 * 
	 * @param BufferedReader
	 * @throws ControladorException
	 */
	public void finalizarLeituraFiscalizacaoAnormalidade(BufferedReader buffer) throws ControladorException;
	
	/**
	 * M�todo que pesquisa a Leitura Anormalidade de uma
	 * Ordem de Servi�o
	 *  
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * 
	 * @author Raimundo Martins
	 * @date 21/09/2011
	 * 
	 * @return Objeto Leitura Anormalidade
	 */
	
	public LeituraAnormalidade pesquisarLeituraAnormalidade(Integer idOrdemServico) throws ControladorException;
	
	/**
	 * M�todo que retorna a quantidade de imoveis
	 * daquele comando, localidade, setor e quadra
	 *  
	 * [UC 1223] Gerar Relat�rio Quantitativo dos 
	 * Im�veis das Ordens Seletivas
	 * 
	 * @author Raimundo Martins
	 * @date 26/09/2011
	 * 
	 * @return Quantidade de im�veis encontrados
	 */
	
	public Integer pesquisarQuantidadeImoveisOrdensSeletivas(String comando, String localidade, String setor, String quadra) 
			throws ControladorException;
	
	/**
	 * M�todo que retorna os dados do relat�rio
	 *  
	 * [UC 1223] Gerar Relat�rio Quantitativo dos 
	 * Im�veis das Ordens Seletivas
	 * 
	 * @author Raimundo Martins
	 * @date 26/09/2011
	 * 
	 * @return Dados para preenchimento do relat�rio
	 */
	public Collection<Object[]> gerarRelatorioQuantImovOSSeletivaBean (String comOrdemSeletiva, String local, 
		String setorInicial, String setorFinal, String quadraIni, String quadraFin)throws ControladorException;
	
	
	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * Verifica se a OS j� foi encerrada
	 * 
	 * @author Mariana Victor
	 * @date 04/10/2011
	 */
	public Boolean verificarOSEncerrada(Integer idOrdemServico) throws ControladorException;
	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public SetorComercial obterSetorComercialCodigo(String codigo) throws ControladorException;
	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Quadra obterQuadraNumero(String numero) throws ControladorException;

	/**
	 * 
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Raimundo Martins
	 * @since 07/11/2011
	 *  
	 * @param comandoEncerrado - Caso a pesquisa retorne apenas os comandos encerrados.
	 **/
	public Collection<Object[]> pesquisarDadosComandoOSSeletivaResumido(
			Integer idEmpresa, Date dataInicial, Date dataFinal,
			int numeroIndice, int quantidadeRegistros, Integer qtdeDiasValidadeOSAnormalidadeFiscalizacao, 
			boolean comandoEncerrado, Integer idLocalidadeInicial, Integer idLocalidadeFinal, 
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer matriculaImovel, Integer idOrdemServico, String idTipoOS) throws ControladorException;
	/**
	 * [UC1249] � Filtro OS Encerradas por Acompanhamento de Servi�o
	 * 
	 * Filtra OSs encerradas por Acompanhamento de Servi�o
	 * 
	 * @author Fernanda Almeida
	 * @date 31/10/2011
	 * 
	 * @param pesquisarFiscalizarOSAcompanhamentoHelper
	 * 
	 * @returnCollection <OrdemServico>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper> pesquisarFiscalizarOSAcompanhamentoHelper( PesquisarFiscalizarOSAcompanhamentoHelper
		pesquisarFiscalizarOSAcompanhamentoHelper) throws ControladorException;

	/**
	 * [UC1249] � Filtro OS Encerradas por Acompanhamento de Servi�o
	 * 
	 * @author Fernanda Almeida
	 * @date 31/10/2011
	 */
	public Integer pesquisarRAOrdemServico(Integer numeroOS) throws ControladorException;

	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Raimundo Martins
	 * @since 08/11/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal, Integer idLocalidadeInicial, Integer idLocalidadeFinal, 
			Integer idSetorInicial, Integer idSetorFinal, Integer matriculaImovel, Integer idOrdemServico)
			throws ControladorException;
	/**
	 * [UC1249] Filtro OS Encerradas por Acompanhamento de Servi�o
	 * 
	 * @author Fernanda Almeida
	 * @date 09/11/2011
	 * 
	 */
	public void atualizarIndicadorValidaOSProg(Integer idOS) throws ControladorException;


	/**
	 * [UC1249] Filtro OS Encerradas por Acompanhamento de Servi�o
	 * 
	 * @author Fernanda Almeida
	 * @date 09/11/2011
	 * 
	 */
	public Collection<Integer> pesquisarOSProgramacaoASPorIdRA(Integer idRA) throws ControladorException;
	
	public Collection<AcompanhamentoArquivosRoteiroHelper> pesquisarAcompanhamentoArquivosRoteiro( 
			String dataProgramacao,
			String idEmpresa,
			String idSituacao, Integer idUsuario) throws ControladorException;
	
	/**
	 * [UC 1193] Consultar Comando OS Seletiva Inspe��o Anormalidade
	 * 
	 * M�todo que pesquisa os setores relacionados ao comando
	 * de uma inspe��o e anormalidade
	 * 
	 * @author Raimundo Martins
	 * @date 23/01/2012
	 * */
	public Collection<ComandoOsSetor> pesquisarSetoresAssociadosComandoOrdemSeletiva(Integer idComando) 
			throws ControladorException;

	
	/**
	 * [UC0468] Consultar Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/01/2012
	 * 
	 * @param idOrdemServico
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public boolean verificarOrdemServicoCobrada(Integer idOrdemServico) throws ControladorException ;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 01/03/2012
	 */
	public Integer gerarOrdemServicoFiscalizacaoCorte(OrdemServico ordemServico, Usuario usuario) throws ControladorException;
	
	/**
	 * [UC 0153] Apresentar Dados Para An�lise da Medi��o e Consumo
	 * 
	 * Metodo que verifica se um imovel possui Ordens de Servi�o
	 * com fotos associadas
	 * 
	 * @author Raimundo Martins
	 * @date 13/04/2012
	 * */	
	public Boolean imovelOsComFoto(int idImovel) throws ControladorException;
	
	/**
	 * [UC 0153] Apresentar Dados Para An�lise da Medi��o e Consumo
	 * 
	 * Metodo que verifica se uma Ordem de Servi�o
	 * possui fotos associadas
	 * 
	 * @author Raimundo Martins
	 * @date 16/04/2012
	 * */	
	public Boolean osComfoto(int idOrdemServico) throws ControladorException;
	
	/**
	 * [UC 0153] Apresentar Dados Para An�lise da Medi��o e Consumo
	 * 
	 * Metodo que pesquisa as fotos de uma determinada 
	 * Ordem de Servi�o
	 * 
	 * @author Raimundo Martins
	 * @date 16/04/2012
	 * */	
	public Collection<OrdemServicoFoto> pesquisarFotoOs(Integer idOrdemServico) throws ControladorException;
	
	/**
	 * [UC 1325] Substitui��o de hidr�metro
	 *  
	 * @author: Fernanda Almeida
	 * @return Object[]
	 * @date: 08/05/2012
	 * */
	public Object[] substituirHidrometro(BufferedReader buffer,Long imei, boolean prefeitura) throws ControladorException;
	
	/**
	 * [UC 1325] Substitui��o de hidr�metro
	 *  
	 * @author: Fernanda Almeida
	 * @date: 08/05/2012
	 * */
	public void retornoAtualizarOrdemServicoAcompanhamentoServicoSubstituicao(
			Date dataProgramacao, Integer idEquipe, boolean prefeitura) throws ControladorException;

	/**
	 * [UC1199] Acompanhar Arquivos de Roteiro
	 * 
	 * @author Fernanda Almeida
	 * @date 11/05/2012
	 * 
	 * @param numeroOS
	 * @return String
	 * @throws ErroRepositorioException
	 */
	public String obterEquipeOsProgramacaoAcompServico(Integer numeroOS) throws ControladorException;

	
	/**
	 * [UC1199] Acompanhar Arquivos de Roteiro
	 * 
	 * @author Fernanda Almeida
	 * @date 11/05/2012
	 * 
	 * @param idOrdemServico
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short verificaSituacaoOS(Integer idOrdemServico) throws ControladorException;

	
	
	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2012
	 */
	public OrdemServico pesquisarOrdemServicoMovimentarOS(Integer idOS)
			throws ControladorException;

	
	/**
	 * [UC0711] Filtro Para Gera��o de Ordens de Servi�o Seletivas
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2012
	 * 
	 * param idImovel
	 * @throws ErroRepositorioException
	 */
	public LeituraAnormalidade obterLeituraAnormalidadeValidaPorImovel(Integer idImovel) throws ControladorException;

	/**	 
	 * [UC 0441][RM7363] Consultar Dados da Ordem de Servi�o
	 * Metodo que pesquisa as fotos de uma determinada 	 
	 * Ordem de Servi�o	 
	 * @author Fernanda Almeida
	 * @date 25/06/2012	 
	 * */
	public Collection<OrdemServicoFoto> buscarFotosOs(Integer idOrdemServico) throws ControladorException;
	
	/**[UC0441]
	 * Metodo que verifica se h� vers�o	 do gsanas
	 * Ordem de Servi�o	 
	 * @author Fernanda Almeida
	 * @date 13/07/2012	 
	 * */	
	public Integer buscarVersaoGsanas() throws ControladorException;

	/**[UC1209]
	 * @author Fernanda Almeida
	 * @date 19/07/2012	 
	 * */
	public ArquivoTextoAcompanhamentoServico buscarArquivoAcompanahmentoServico(long imei) throws ControladorException;
	
	/**[UC1371]
	 * @author Anderson Cabral
	 * @date 17/08/2012	 
	 * */
	public void atualizarSituacaoLigacaoAguaImovelFiscalizado(Integer idLocalidade, Integer idFuncionalidadeIniciada, Integer numeroDiasAtualizarLigacaoAguaImovelFiscalizado) throws ControladorException;

	
	/** [UC1371]
	 * Mudar Situacao da Ligacao - OS Fiscalizacao
	 * 
	 * [SB0001] - Atualizar Liga��o de �gua.
	 * 
	 * @author Anderson Cabral
	 * @date 22/08/2012
	 * 
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Short atualizarLigacaoAguaImovelFiscalizado(
			Integer idSituacaoEncontrada, 
			Integer idImovel, 
			Integer consumoDefinido, 
			Integer idLigacaoAguaSituacao,
			Short atualizarSituacaoLigacaoAgua,
			Short atualizarSituacaoLigacaoEsgoto)
			throws ControladorException;

	
	/**
	 * Retorna o codigo do servi�o executado recebendo o numero de uma OS como parametro
	 * @param idOrdemServico
	 * @author Amelia Pessoa
	 * @return codigo Servico
	 * @throws ControladorException
	 */
	public String obterCodigoServico(Integer idOrdemServico) throws ControladorException;

	
	/** 
	 * 
	 * @author Ricardo Germinio 
	 * @date 22/08/2012 
	 * 
	 */ 
	public Integer inserirMotivoNaoAceite(String descricao, String descricaoAbreviada, 
			short indicadorObservacaoObrigatoriedade, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC1357] Excluir Motivo n�o aceite
	 * 
	 * @author Ricardo Germinio
	 * @date 31/07/2012
	 * 
	 * @param descricao
	 * @return 
	 */
	
	public void excluirMotivoNaoAceite(String[] ids, Usuario usuarioLogado) 
			throws ControladorException;

	/**
	 * [UC1357] Atualizar Motivo n�o aceite
	 * 
	 * @author Ricardo Germinio
	 * @date 31/07/2012
	 * 
	 * @param descricao
	 */

	public void atualizarMotivoNaoAceite(OsAcompanhamentoMotivoNaoAceite osAcompanhamentoMotivoNaoAceite, 
			Usuario usuarioLogado) throws ControladorException;


	/**
	 * Faz update no parecer da RA
	 * @param idRA
	 * @param parecer
	 * @author Amelia Pessoa
	 * @throws ErroRepositorioException
	 */
	public void atualizarParecerRA(Integer idRA, String parecer) throws ControladorException;

	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * Libera o arquivo antes de tentar baixar
	 * 
	 * @author Fernanda Almeida
	 * @date 28/07/2011
	 * 
	 * @param imei
	 */
	public void liberarArqTextoAcompServico(long imei) throws ControladorException;

	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * Atualiza o indicador de transmiss�o para SIM, apenas ao receber o retorno afirmativo do celular
	 * 
	 * @author Fernanda Almeida
	 * @date 26/10/12
	 * 
	 * @param imei
	 */
	public void atualizarIndicadorTransmissaoEquipeAcompanhamentoServico( long imei ) throws ControladorException;

	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * Atualiza o indicador de transmiss�o da OS para SIM, apenas ao receber o retorno afirmativo do celular
	 * 
	 * @author Fernanda Almeida
	 * @date 26/10/12
	 * 
	 * @param imei
	 */
	public void atualizarIndicadorTransmissaoOsAcompanhamentoServico(long imei,	int numeroOS) throws ControladorException;

	/**
	 * [UC1394] Criar Roteiro em Dia Especial
	 * 
	 * Insere Arquivo texto Acompanhamento Servi�o e Os Programa��o
	 * 
	 * @author Fernanda Almeida
	 * @date 30/11/12
	 * 
	 * @param equipe,dataProgramacao
	 */
	public void inserirProgramacaoAcompServico(ProgramacaoDiasEspeciais programacaoDiasEspeciais,Equipe equipe,Date dataProgramacao) throws ControladorException;

	/**
	 * M�todo que ir� atualizar a situa��o do arquivo para em campo
	 * 
	 * @autor Fernanda Almeida
	 * @date 03/12/2012
	 * 
	 * @param imei - Imei do aparalho
	 * @param equipeId 
	 * 	 
	 * @throws ControladorException
	 */
	public void mudarSituacaoArquivoCampo(Integer equipeId, long imei) throws ControladorException;

	/**
	 * [UC1406] Exibir Fotos Ordem de Servi�o Original de Repavimenta��o
	 * 
	 * @author Fernanda Almeia
	 * @date 13/12/2012
	 */
	public Collection<OrdemServicoFoto> pesquisarOrdemServicoFotoRepavimentacao(
			Integer idOs, Integer idRA) throws ControladorException;
	

	/**
	 * [UC1199] Acompanhamento arquivo roteiro
	 * 
	 * @author Fernanda Almeia
	 * @date 13/12/2012
	 */
	public boolean verificarSituacaoPendenteDevolvidaOsAcompServico( OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico ) throws ControladorException;

	/** [RM6655] Retorna as OSs com indicador de etapa =1 ligadas as RA e EtapaOS
	 *  
	 * @author Fernanda Almeida
	 * @date 13/03/2013
	 * @param idOs
	 * @param idRa
	 * @param icNovaEtapa
	 * @return
	 */
	public Collection<Object[]> pesquisarOrdemServicoEtapaRa(Integer idOs,
			Integer idRa, short icNovaEtapa) throws ControladorException;
	
	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thulio Araujo, Rodrigo Cabral
	 * @date 22/08/2011, 05/04/2013
	 * 
	 */
	public void remanejaOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoHelper helper,
			Usuario usuario, OSProgramacaoAcompanhamentoServicoHelper osProgramacaoAcompanhamentoServico ) throws ControladorException;
	
	/**
	 * [UC1497] Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * 
	 * @author Rafael Corr�a
	 * @since 17/06/2013
	 * 
	 * @return Collection<RelatorioQuantitativoImoveisTipoServicoBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioQuantitativoImoveisTipoServicoBean> pesquisarQuantitativoImoveisTipoServico(
			Integer idEmpresa,
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idLocalidade, 
			Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, 
			Integer codigoQuadraInicial, Integer codigoQuadraFinal,
			Integer[] idsServicoTipo,
			String comando, String identificadorOS) throws ControladorException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 01/07/2013
	 */
	public Collection<RelatorioErrosEncerramentoOSCobrancaBean> efetuarExecucaoColecaoOSCobrancaSmartphone(Integer idArquivo, Collection<Integer> idsOS, Usuario usuario);
	
	/**
	 * [UC1500] Consultar Dados OS de Cobran�a para Smartphone
	 * 
	 * @author Rafael Corr�a
	 * @date 18/06/2013
	 */
	public void efetuarExecucaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS, DadosDebitoOSCobrancaSmartphoneHelper helperDebito, 
			Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */
	public Collection<SituacaoEncontradaHelper> pesquisarDadosFiscalizacaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS)
			throws ControladorException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 05/07/2013
	 */
	public void atualizarSituacaoOSSmartphone(Integer idArquivo,
			Integer idOS, Short situacao) throws ControladorException;
	
	/** [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 *  
	 * @author Raphael Rsossiter
	 * @date 05/06/2013
	 */
	public Collection<Empresa> validarEmpresaPrincipal(Usuario usuario) throws ControladorException;
	
	/**
	 *
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * 
	 * @author Raphael Rsossiter
	 * @date 06/06/2013
	 */
	public Collection<Object[]> consultarOrdemServicoTXTSmartphone(
		Integer idEmpresa,
		Integer idGerenciaRegional,
		Integer idUnidadeNegocio,
		Integer idLocalidade, 
		Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, 
		Integer codigoQuadraInicial, Integer codigoQuadraFinal,
		Integer[] idsServicoTipo, String comando, String identificadorOS) throws ControladorException;
	
	/**
	 *
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * 
	 * @author Raphael Rsossiter
	 * @date 06/06/2013
	 */
	public void inserirParametrosArquivoTextoOSCobranca(ParametrosArquivoTextoOSCobranca parametros, Integer[] idsServicoTipo, Collection<Object[]> listaOSSelecionadas,String comando, String identificadorOS) throws ControladorException;
	
	/**
	 *
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * 
	 * @author Raphael Rsossiter
	 * @date 06/06/2013
	 */
	public byte[] gerarArquivoTextoIdaExecucaoOSCobranca(Integer idArquivoTextoOSCobranca) throws ControladorException;
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param imei - Imei do aparalho que ira receber o arquivo
	 * 
	 * @return Object[] - retorna o arquivo Object[0] = id do arquivo texto
	 *         Object[1] = arquivo texto montado
	 *         
	 * @throws ControladorException
	 */
	public byte[] baixarArquivoTextoExecucaoOS( long imei, String mac ) throws ControladorException;
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 18/06/2013
	 * 
	 * 
	 * @param BufferedReader
	 * @throws ControladorException
	 */
	public void atualizarMovimentacaoExecucaoOS	(BufferedReader buffer) 
			throws ControladorException;
	
	/**
	 * ?????
	 * 
	 * @author Bruno Barros
	 * @date 25/06/2013
	 * 
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ControladorException;
	
	/**
	 * ?????
	 * 
	 * @author Bruno Barros
	 * @date 25/06/2013
	 * 
	 */
	public Collection<ArquivoTxtOSCobrancaSmartphoneHelper> buscarColecaoArquivoTextoOSCobrancaSmartphone(
			String idEmpresa,
			String idGerencia,
			String idUnidade,
			String idLocalidade, 
			String idSetorComercialInicial,
			String idSetorComercialFinal, 
			String idQuadraInicial,
			String idQuadraFinal, 
			String idLeiturista, 
			String idSituacaoArquivo,
			String dtGeracaoInicial,
			String dtGeracaoFinal,
			String idTipoOrdemServico,
			String[] idsServicoTipo) throws ControladorException;

	
	
	
	/**
	 * [UC1512] Inserir Motivo de N�o Execu��o de Servi�o
	 * 
	 * @author Diogo Luiz
	 * @date 20/06/2013
	 */
	public Integer inserirMotivoDeNaoExecucaoDoServico(
			MotivoDeNaoExecucaoDoServico motivoDeNaoExecucaoDoServico,
			Collection colecaoHelper) throws ControladorException;
	
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSAcompanhamentoServico(Integer idEquipe,
			Date dataRoteiro, boolean arquivoOnLine) throws ControladorException;
	
	/**
	 * [UC1487] Processar Requisicao Dispositivo Movel Execucao OS
	 * 
	 * Recebe as fotos envidas pelo sistema gsaneos e as persiste na base de dados
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2013
	 * 
	 * @param idArquivo - Id do arquivo ao qual a foto est� ligada
	 * @param numeroOS - N�mero da Ordem de servi�o a qual a OS pertence
	 * @param tipoFoto - Tipo da foto enviada ( Antes da Execu��o, Durante a Execu��o, Ap�s a Execu��o, Fachada do Im�vel
	 * @param foto     - Array de bytes contendo a foto
	 * @throws FachadaException
	 */
	public void inserirFotoOrdemServicoCobrancaSmartphone(int idArquivo, int numeroOS, int tipoFoto,
			byte[] foto )
			throws ControladorException;
	
	/**
	 * [UC1360] Gerar Arquivo Texto para Faturamento Vers�o Android
	 * [FS0010] Verificar OS para Emiss�o de Carta de Conex�o de Esgoto
	 * 
	 * @author Mariana Victor
	 * @date 13/08/2013
	 */
	public Integer pesquisarOSEmissaoCartaConexaoEsgoto(Integer idImovel, Integer anoMes)
			throws ControladorException;
	
	/**[UC1534] Gerar Ordem de Servico Conexao Esgoto
	 * @author: Jonathan Marcos
	 * @date:12/08/2013
	 */
	public void gerarOrdemServicoConexaoEsgoto(Integer idFuncionalidadeIniciada,Usuario usuario,Integer idComandoOsConexaoEsgoto)
			throws ControladorException;

	/**
	 * [UC1533] Filtrar Ordem Servi�o Conexao Esgoto
	 * [FE0001] Verificar Exist�ncia de Im�vel
	 * 
	 * @author R�mulo Aur�lio
	 * @date 23/08/2013
	 */
	public Integer pesquisarOSConexaoEsgotoPendente(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC1540] Encerrar Ordem de Servico Conexao de Esgoto Conclusao de Servico
	 * @author Jonathan Marcos
	 * @date 23/08/2013
	 */
	public void encerrarOrdemServicoConexaoEsgotoConclusaoServico(Integer idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0016] Excluir Arquivo
	 * 
	 * Exclui os arquivos informados 
	 * 
	 * @author Bruno Barros
	 * @date 19/09/2013
	 * @param ids
	 */
	public void excluirArquivoTextoOrdensServicoCobranca( String[] ids ) throws ControladorException;
	
	/**
	 * [UC1359] Requisicoes Fotos Anormalidade
	 * [SB0002] Inserir Foto Conexao Esgoto
	 * @author Jonathan Marcos
	 * @data 10/09/2013
	 */
	public void inserirFotoConexaoEsgoto(int idImovel,byte[] bytesFoto,int tipoFoto)
			throws ControladorException;
	
	/**
	 * [UC 1559] Consultar Resumo das A��es de Ordem de Servi�o
	 * 
	 * @author Davi Menezes
	 * @date 20/09/2013
	 */
	public Collection<ServicoTipoHelper> pesquisarResumoAcoesOrdemServico(DadosGeracaoResumoOSConsultaHelper helper)
			throws ControladorException;
	
	public Collection<ImovelPerfilHelper> pesquisarResumoImovelPerfilOrdemServico(DadosGeracaoResumoOSConsultaHelper helper, Integer anoMesReferencia, 
			Integer idServicoTipo, Integer idOrdemServicoSituacao, Integer idCobrancaDebitoSituacao, Integer qtdMaxima, BigDecimal valorMaximo)
			throws ControladorException;
	
	public Collection<OrdemServicoSituacaoHelper> pesquisarDetalheResumoOrdemServicoSituacao(DadosGeracaoResumoOSConsultaHelper helper, Integer anoMesReferencia, 
			Integer idServicoTipo, Integer idOrdemServicoSituacao, Integer qtdMaxima, BigDecimal valorMaximo) throws ControladorException;
	
	public Collection<OrdemServicoSituacaoHelper> pesquisarResumoFiscalizacaoSituacaoOrdemServico(DadosGeracaoResumoOSConsultaHelper helper, Integer anoMesReferencia, 
			Integer idServicoTipo, Integer idOrdemServicoSituacao, Integer qtdMaxima, BigDecimal valorMaximo) throws ControladorException;
	
	/**
	 * Autor: Jonathan Marcos
	 * Data: 26/11/2013
	 * RM8974
	 * UC0364
	 */
	public Integer pesquisarComandoOrdemSeletivaOS(Integer idOS)
		throws ControladorException;
	
	/**
	 * autor:Jonathan Marcos
	 * data:31/10/2013
	 * [UC1484] Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * @param idAgenteComercial
	 * @return
	 * @throws ErroRepositorioException
	 */
	public StringBuilder hidrometrosDisponiveisAgenteComercial(Integer idAgenteComercial)
		throws ControladorException;
	
	/**
	 * autor:Jonathan Marcos
	 * data:05/11/2013
	 * [UC1484] Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * @return StringBuilder
	 * @throws ErroRepositorioException
	 */
	public StringBuilder situacaoHidrometro()
		throws ControladorException;
	
	/**
	 * autor:Jonathan Marcos
	 * data:06/11/2013
	 * [UC1484] Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * @return StringBuilder
	 * @throws ErroRepositorioException
	 */
	public StringBuilder localArmazenamentoHidrometro()
		throws ControladorException;
}