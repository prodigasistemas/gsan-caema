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
* Thiago Silva Toscano de Brito
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
package gcom.integracao;

import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoMovimentoHistorico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.Cadastrador;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.ParametroTabelaAtualizacaoCadastro;
import gcom.cadastro.RepositorioCadastroHBM;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.AcaAtualizadores;
import gcom.cadastro.atualizacaocadastral.AtcBairro;
import gcom.cadastro.atualizacaocadastral.AtcCliente;
import gcom.cadastro.atualizacaocadastral.AtcClienteImovel;
import gcom.cadastro.atualizacaocadastral.AtcClienteImovelRetorno;
import gcom.cadastro.atualizacaocadastral.AtcClienteRetorno;
import gcom.cadastro.atualizacaocadastral.AtcClienteTelefone;
import gcom.cadastro.atualizacaocadastral.AtcClienteTelefoneRetorno;
import gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacao;
import gcom.cadastro.atualizacaocadastral.AtcHidrometroInstalacaoRetorno;
import gcom.cadastro.atualizacaocadastral.AtcImovel;
import gcom.cadastro.atualizacaocadastral.AtcImovelRetorno;
import gcom.cadastro.atualizacaocadastral.AtcLogradouro;
import gcom.cadastro.atualizacaocadastral.AtcMunicipio;
import gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovel;
import gcom.cadastro.atualizacaocadastral.AtcSubcategoriaImovelRetorno;
import gcom.cadastro.atualizacaocadastral.BairroAdmin;
import gcom.cadastro.atualizacaocadastral.BairroGsanAdmin;
import gcom.cadastro.atualizacaocadastral.BairroGsanAdminPK;
import gcom.cadastro.atualizacaocadastral.FiltroCadastrador;
import gcom.cadastro.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.LogradouroAdmin;
import gcom.cadastro.atualizacaocadastral.LogradouroGsanAdmin;
import gcom.cadastro.atualizacaocadastral.LogradouroGsanAdminPK;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.integracao.CarroPipaAbastecimentoHelper;
import gcom.gui.integracao.ProcessarRequisicaoGpipaAction;
import gcom.integracao.upa.OrdensServico;
import gcom.integracao.webservice.sms.WebServiceSms;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.faturamento.RelatorioPagamentosAbastecimentosCarrosPipaBean;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ControladorUtilSEJB;
import gcom.util.ErroRepositorioException;
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.servlet.http.HttpServletRequest;


public class ControladorIntegracaoSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	SessionContext sessionContext;
	
	private IRepositorioIntegracao repositorioIntegracao = null;
	private IRepositorioUtil repositorioUtil = null;
	private IRepositorioCadastro repositorioCadastro = null;
	private IRepositorioFaturamento repositorioFaturamento = null;
	
	/**
	 * < <DescriÁ„o do mÈtodo>>
	 * 
	 * @exception CreateException
	 *                DescriÁ„o da exceÁ„o
	 */
	public void ejbCreate() throws CreateException {
		repositorioIntegracao = RepositorioIntegracaoHBM.getInstancia();
		repositorioUtil = RepositorioUtilHBM.getInstancia();
		repositorioCadastro = RepositorioCadastroHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
	}

	/**
	 * < <DescriÁ„o do mÈtodo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <DescriÁ„o do mÈtodo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <DescriÁ„o do mÈtodo>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a inst‚ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas ‡
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorImovel
	 */
	protected ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		// pega a inst‚ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas ‡
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	//[UC0741]
	public void enviarMovimentoExportacaoFirma() {
		//selecionar todos os serviÁos pertencentes a um movimento
		try {
			Collection ordensServicoMovimentoParaExportacao = repositorioIntegracao.pesquisarOrdemServicoMovimentoParaEnvioSAM();
			Collection ordensServicoParaExportacao = new ArrayList();
			Iterator iterator = ordensServicoMovimentoParaExportacao.iterator();
			
			while (iterator.hasNext()) {
				OrdemServicoMovimento movimento = (OrdemServicoMovimento) iterator.next();
				ordensServicoParaExportacao.add(montarOrdensServicoParaExportacao(movimento));
								
			}
			
			repositorioIntegracao.exportarOrdemServicoMovimentos(ordensServicoParaExportacao);
			
			//Reiniciar o iterator para atualizar as ordens enviadas somente no final
			iterator = ordensServicoMovimentoParaExportacao.iterator();
			while (iterator.hasNext()) {
				OrdemServicoMovimento movimento = (OrdemServicoMovimento) iterator.next();
			
				if (movimento.getIndicadorMovimento() == 1) {
					movimento.setIndicadorMovimento(new Short("2"));
					repositorioIntegracao.atualizarIndicadorMovimentoOrdemServicoMovimento(movimento);
				}
			
			}
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		}
		
		
	}
	
	private OrdensServico montarOrdensServicoParaExportacao(OrdemServicoMovimento ordemServicoMovimento) {
		
		OrdensServico ordensServico = new OrdensServico();
		
		ordensServico.setId(ordemServicoMovimento.getId());
		ordensServico.setOsUnidadeExecutora(ordemServicoMovimento.getUnidadeOrganizacionalExecutora().getId());
		ordensServico.setOsUnidadeCentralProgramacao(ordemServicoMovimento.getUnidadeOrganizacionalCentralizadora().getId());
		ordensServico.setOsDataInicio(ordemServicoMovimento.getDataTramite());
		ordensServico.setOsDataEncerramento(ordemServicoMovimento.getDataExecucao());
		ordensServico.setServicoId(ordemServicoMovimento.getServicoTipo().getId());
		ordensServico.setOsSolicitacao(""+ordemServicoMovimento.getRegistroAtendimento().getId());
		if (ordemServicoMovimento.getImovel() != null) {
			ordensServico.setOsMatriculaNumero(""+ordemServicoMovimento.getImovel().getId());
			ordensServico.setOsInscricao(ordemServicoMovimento.getImovel().getInscricaoFormatada());
		}
		
		ordensServico.setOsSolicitante(ordemServicoMovimento.getNomeSolicitante());
		ordensServico.setOsTelefoneSolicitante(ordemServicoMovimento.getTelefoneSolicitante());
		ordensServico.setOsEndereco(ordemServicoMovimento.getEndereco());
		ordensServico.setOsPontoReferencia(ordemServicoMovimento.getPontoReferencia());
		ordensServico.setOsBairro(ordemServicoMovimento.getBairro());
		ordensServico.setOsLocalidade(ordemServicoMovimento.getNomeLocalidade());
		
		String codigoElo = ""+ordemServicoMovimento.getCodigoElo();
		if (codigoElo.length() < 4) {
			codigoElo = Util.adicionarZerosEsquedaNumero(4,codigoElo);
			
		}
		
		ordensServico.setOsElo(codigoElo);
		
		String codigoSetor = ""+ordemServicoMovimento.getCodigoSetor();
		
		if (codigoSetor.length() < 4) {
			codigoSetor = Util.adicionarZerosEsquedaNumero(4,codigoSetor);
			
		}
		ordensServico.setOsSetor(codigoSetor);
		
		String numeroQuadra = ""+ordemServicoMovimento.getNumeroQuadra();
		
		if (numeroQuadra.length() < 4) {
			numeroQuadra = Util.adicionarZerosEsquedaNumero(4,numeroQuadra);
			
		}
		
		ordensServico.setOsQuadra(numeroQuadra);
		
		
		
		ordensServico.setOsObservacao(ordemServicoMovimento.getObservacao());
		ordensServico.setOsPrioridade(ordemServicoMovimento.getServicoTipoPrioridadeAtual().getId());
		ordensServico.setOsParecerTramite(ordemServicoMovimento.getParecer());
		if (ordemServicoMovimento.getPavimentoRua() != null) {
			ordensServico.setOsPavimentoRua(ordemServicoMovimento.getPavimentoRua().getId());
		}
		if (ordemServicoMovimento.getAreaPavimentoRua() != null) {
			ordensServico.setOsAreaPvtoRua(ordemServicoMovimento.getAreaPavimentoRua().doubleValue());
		}
		if (ordemServicoMovimento.getPavimentoCalcada() != null) {
			ordensServico.setOsPavimentoCalcada(ordemServicoMovimento.getPavimentoCalcada().getId());
		}
		if (ordemServicoMovimento.getAreaPavimentoCalcada() != null) {
			ordensServico.setOsAreaPvtoCalcada(ordemServicoMovimento.getAreaPavimentoCalcada().doubleValue());
		}
		
		ordensServico.setOsSituacaoData(ordensServico.getOsDataInicio());
		ordensServico.setOsSituacao('I');
		ordensServico.setOsMatriculaReferencia("S");
		ordensServico.setOsDataCadastro(ordensServico.getOsDataInicio());
		
		
		return ordensServico;
	}
	
	public void receberMovimentoExportacaoFirma() {
		//selecionar todos os serviÁos pertencentes a um movimento
		
		HashMap<String, String> empresaOS = new HashMap<String, String>(); 
		
		try {
			Collection ordensServicoParaImportacao = repositorioIntegracao.pesquisarOrdensServicoParaRecebimentoUPA();
			Iterator iterator = ordensServicoParaImportacao.iterator();
			
			
			while (iterator.hasNext()) {
				OrdemServicoMovimento movimento = (OrdemServicoMovimento) iterator.next();
				
				String emailEmpresa = null;
				Empresa empresa = null;
				
				UnidadeOrganizacional unidadeRepavimentadora = movimento.getUnidadeOrganizacionalExecutora().getUnidadeRepavimentadora();
				
				if (unidadeRepavimentadora != null) {
					empresa = movimento.getUnidadeOrganizacionalExecutora().getUnidadeRepavimentadora().getEmpresa();
					emailEmpresa = empresa.getEmail();
				}
				
				if (emailEmpresa == null || emailEmpresa.trim().equals("")) {
					System.out.println("E-mail da empresa "+ (empresa != null ? empresa.getDescricao() : "") +" n„o cadastrado para envio de notificaÁ„o");
					
				} else {
					String listaOS = empresaOS.get(emailEmpresa);
					if (listaOS == null) {
						listaOS = movimento.getId()+";"+movimento.getServicoTipo().getDescricao()+";"+getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(movimento.getRegistroAtendimento().getId())+",\n";
					} else {
						listaOS = listaOS + movimento.getId()+";"+movimento.getServicoTipo().getDescricao()+";"+getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(movimento.getRegistroAtendimento().getId())+",\n";
					}
					empresaOS.put(emailEmpresa, listaOS);
					
				} 
				
				//Encerrar Ordem Servico
				
				Usuario usuario = null;
				try {
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, movimento.getLoginUsuario()==null?"":movimento.getLoginUsuario()));
				usuario = (Usuario)getControladorUtil().pesquisar(filtroUsuario, Usuario.class.getName()).iterator().next();
				} catch (Exception e) {
					System.out.println("A ORDEM DE SERVICO: "+movimento.getId() + " nao possui usuario valido e nao teve a OS fechada");
					continue;
					
				}
				
				if (movimento.getDataExecucao().before(movimento.getDataGeracao())) {
					System.out.println("A ORDEM DE SERVICO: "+movimento.getId() + ": Instante de encerramento anterior ‡ geraÁ„o da ordem");
					continue;
				}
				
				if (movimento.getParecer() == null || movimento.getParecer().trim().equals("")) {
					movimento.setParecer("ENCERRAMENTO NORMAL, TRAMITE AUTOMATICO DE RETORNO DE TERCEIRA");
					
				}
				
				
/*				Integer numeroOS,
				Date dataEncerramento, Usuario usuarioLogado,
				String motivoEncerramento, Date ultimaAlteracao,
				String parecerEncerramento, String indicadorPavimento,
				String pavimento,
				Collection colecaoManterDadosAtividadesOrdemServicoHelper,
				IntegracaoComercialHelper integracaoComercialHelper,
				String tipoServicoOSId, OrdemServico osFiscalizacao,
				String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,OrdemServicoPavimento ordemServicoPavimento
*/	
				/*Alterado por: Anderson Italo
				 * data: 07/01/2010
				 * CRC3441 - Estava sendo passado a descriÁ„o do motivo de encerramento ao invÈs do id,
				 * o que ocasionava erro no controladorOrdemServico na hora de atualizar a ordem de serviÁo
				 * no mÈtodo que segue abaixo.
				 * */
				getControladorOrdemServico().encerrarOSComExecucaoSemReferencia(
						movimento.getId(), new Date(), usuario,
						""+movimento.getAtendimentoMotivoEncerramento().getId(), new Date(), ""+movimento.getParecer(), null, null, null, null,
						null, null, null, null,null,null,null, new Date(), null);

				
				//alterado por Vivianne Sousa 20/06/2008
				//analista responsavel: Fabiola
				getControladorOrdemServico().tramitarOuEncerrarRADaOSEncerrada(
					    movimento.getId(),
					    usuario,
					    null,
					    ""+movimento.getRegistroAtendimento().getId(),
					    null);
				
				//Verificar se È necess·rio a abertura de ordem de serviÁo de repavimentaÁ„o
				if (movimento.getAtendimentoMotivoEncerramento().getIndicadorExecucao() == 1 && 
						movimento.getPavimentoRua() !=null && 
						movimento.getPavimentoRua().getId() != null && !movimento.getPavimentoRua().getId().toString().equals("0") && 
						movimento.getAreaPavimentoRua() != null &&  !movimento.getAreaPavimentoRua().toString().equals("0") ) {
					
					OrdemServico ordemServico = new OrdemServico();
					
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO, ConectorAnd.CONECTOR_AND));
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADORPAVIMENTO, ConstantesSistema.INDICADOR_USO_ATIVO));
					 
					   
					
					ordemServico.setServicoTipo((ServicoTipo)Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName())));
					ordemServico.setAreaPavimento(movimento.getAreaPavimentoRua());
					//ordemServico.setUnidadeAtual(unidadeRepavimentadora);
					ordemServico.setRegistroAtendimento(movimento.getRegistroAtendimento());
				
					Integer idOrdemServico = getControladorOrdemServico().gerarOrdemServico(ordemServico, usuario, null);
					ordemServico.setId(idOrdemServico);
				
					OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
					if ( movimento.getPavimentoRua() != null ) {
						
						UnidadeOrganizacional unidadeOrganizacional =  Fachada.getInstancia().obterUnidadeRepavimentadorAPartirMunicipio(ordemServico, "");
						
						if ( unidadeOrganizacional != null && unidadeOrganizacional.getId() != null ) { 
							
							ordemServicoPavimento.setUnidadeRepavimentadora(unidadeOrganizacional);
						}
						ordemServicoPavimento.setAreaPavimentoRua(movimento.getAreaPavimentoRua());
						ordemServicoPavimento.setPavimentoRua(movimento.getPavimentoRua());
						ordemServicoPavimento.setUltimaAlteracao(new Date());
					}
					
					getControladorOrdemServico()
						.encerrarOSComExecucaoSemReferencia(
							ordemServico.getId(),
							new Date(),
							usuario,
							"2",
							new Date(),
							ordemServico
									.getDescricaoParecerEncerramento(),
							"" + ConstantesSistema.INDICADOR_USO_ATIVO,
							movimento.getAreaPavimentoRua().toString(),
							null, null, null, null, null, null, ordemServicoPavimento,null,null, new Date(), null);

				}
				
				
				movimento.setIndicadorMovimento(new Short("3"));
				getControladorUtil().atualizar(movimento);
				
				//Movendo para o histÛrico
				getControladorUtil().inserir(new OrdemServicoMovimentoHistorico(movimento));
				getControladorUtil().remover(movimento);
				
				
				
			}

			for (String emailEmpresa : empresaOS.keySet()) {

				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_k:mm:ss");

				String assuntoEmail = "ConvÍnio COMPESA - Demanda_"
						+ format.format(new Date());
				String textoEmail = "Prezado(s) Sr(s),\n\nSegue abaixo RelaÁ„o com os n˙meros de Ordens de ServiÁos que compıem esta demanda:\n\n"
						+ empresaOS.get(emailEmpresa)
						+ "\n\nAtenciosamente;\n\nGest„o ConvÍnio de RepavimentaÁ„o da COMPESA";
				ServicosEmail.enviarMensagem("GSAN", emailEmpresa,
						assuntoEmail, textoEmail);

			}
			//repositorioIntegracao.importarOrdensServico(ordensServicoMovimentoParaImportacao);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		} catch (ErroEmailException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	public Integer gerarOS(Usuario usuario, OrdemServico ordemServico) throws ControladorException {
		Integer idOrdemServico = getControladorOrdemServico().gerarOrdemServico(ordemServico, usuario, null);
		return idOrdemServico;
	}

	

	/**
	 * Retorna o valor do ControladorOrdemServico
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @return O valor de ControladorOrdemServico
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;
		// pega a inst‚ncia do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	
	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst·ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {

		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	public Object[] pesquisarHorarioProcessoIntegracaoUPA() throws ControladorException {
		try {
			return repositorioIntegracao.pesquisarHorarioProcessoIntegracaoUPA();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema",e);
		}
		
		
	}
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void recepcaoDadosAdmin(Integer idFuncionalidadeIniciada) throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o inÌcio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.FUNCIONALIDADE,0);
		
		
		try {

			// Vari·veis para a paginaÁ„o da pesquisa de Imovel por Grupo
			// Faturamento
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeRegistros = 1000;
			// ========================================================================

			while (!flagTerminou) {
				
				System.out.println("Realizando a pesquisa.");
				
				Collection<AtcImovelRetorno> colecaoAtcImovelRetorno = repositorioIntegracao.pesquisarAtcImovelRetorno(quantidadeRegistros);
			
				Empresa empresa = null;
				//[FS0001 - Verificar existÍncia de registros para atualizaÁ„o].
				if ( colecaoAtcImovelRetorno != null && !colecaoAtcImovelRetorno.isEmpty() ) {
				
					Iterator<AtcImovelRetorno> iteratorAtcImovelRetorno = colecaoAtcImovelRetorno.iterator();
					while ( iteratorAtcImovelRetorno.hasNext() ) {
	
						AtcImovelRetorno atcImovelRetorno = (AtcImovelRetorno) iteratorAtcImovelRetorno.next();
						
						
						if ( empresa == null ) {
							ParametroTabelaAtualizacaoCadastro atualizacaoCadastro = repositorioCadastro.
									pesquisarParametroTabelaAtualizacaoCadastro(atcImovelRetorno.getIdParametroAtualizacaoCadastral());
							
							empresa = atualizacaoCadastro.getEmpresa();
							Collection<AcaAtualizadores> colecaoAcaAtualizadores = repositorioIntegracao.pesquisarAcaAtualizadores();
							if ( colecaoAcaAtualizadores != null && !colecaoAcaAtualizadores.isEmpty() ) {
							
								Iterator<AcaAtualizadores> iteratorAcaAtualizadores = colecaoAcaAtualizadores.iterator();
								while ( iteratorAcaAtualizadores.hasNext() ) {
	
									AcaAtualizadores acaAtualizadores = (AcaAtualizadores) iteratorAcaAtualizadores.next();
									
									Cadastrador cadastrador = new Cadastrador();
									cadastrador.setId(acaAtualizadores.getAcaAtuId());
									cadastrador.setNome(acaAtualizadores.getAcaAtuNome());
									cadastrador.setUltimaAlteracao(new Date());
									
									cadastrador.setEmpresa(empresa);
									
									FiltroCadastrador filtroCadastrador = new FiltroCadastrador();
									filtroCadastrador.adicionarParametro( new ParametroSimples(FiltroCadastrador.ID , cadastrador.getId()));
									
									Collection colecaoCadastrador =  repositorioUtil.pesquisar(filtroCadastrador, Cadastrador.class.getName());
									
									if ( colecaoCadastrador != null && !colecaoCadastrador.isEmpty() ) {
										repositorioUtil.atualizar(cadastrador);	
									} else {
										repositorioUtil.inserir(cadastrador);
									}
								}
							}
						}
						
						//2.3.	Seleciona as Subcategorias 
						Collection<AtcSubcategoriaImovelRetorno> colecaoAtcCategoriaImovelRetorno = repositorioIntegracao.
								pesquisarAtcSubcategoriaImovelRetorno(atcImovelRetorno.getComp_id().getAtcImoId());
						
						//Caso o imovel nao tenha categoria È atualizado na base admin como invalido para integracao.
						if ( colecaoAtcCategoriaImovelRetorno != null && !colecaoAtcCategoriaImovelRetorno.isEmpty() ) {
							
							boolean novoImovel = false;
							if ( atcImovelRetorno.getAtcImoSitCad() != null && atcImovelRetorno.getAtcImoSitCad().toString().equals("19") ) {
								novoImovel = true;
							}
							//[SB0001-Inserir Dados do ImÛvel]. 
							Integer idImovelAtualizacaoCadastral = this.inserirImovelAtualizacaoCadastral( atcImovelRetorno , novoImovel); 
							
							
							Collection<AtcClienteImovelRetorno> colecaoAtcClienteImovelRetorno = repositorioIntegracao.pesquisarAtcClienteImovelRetorno(
									atcImovelRetorno.getComp_id().getAtcImoId());
							
							if ( colecaoAtcClienteImovelRetorno != null && !colecaoAtcClienteImovelRetorno.isEmpty() ) {
							
								Iterator<AtcClienteImovelRetorno> iteratorAtcClienteImovelRetorno = colecaoAtcClienteImovelRetorno.iterator();
								while( iteratorAtcClienteImovelRetorno.hasNext() ) {
									
									AtcClienteImovelRetorno atcClienteImovelRetorno = (AtcClienteImovelRetorno) iteratorAtcClienteImovelRetorno.next();
									
									boolean clienteExcluido = false;
									if ( atcClienteImovelRetorno.getAtcCimDataFim() != null ) {
										clienteExcluido = true;
									}
									Collection<AtcClienteRetorno> colecaoAtcClienteRetorno = repositorioIntegracao.pesquisarAtcClienteRetorno(atcClienteImovelRetorno.getAtcCliId());
		
									//2.2.	Seleciona os Clientes 
									Iterator<AtcClienteRetorno> iteratorAtcClienteRetorno = colecaoAtcClienteRetorno.iterator();
									
									AtcClienteRetorno atcClienteRetorno = (AtcClienteRetorno) iteratorAtcClienteRetorno.next();
									
									//[SB0002] - Inserir Dados do Cliente
									Integer idClienteAtualizacaoCadastral = this.inserirClienteAtualizacaoCadastral(atcClienteRetorno, idImovelAtualizacaoCadastral, 
											atcClienteImovelRetorno, clienteExcluido, novoImovel, atcImovelRetorno.getIdParametroAtualizacaoCadastral());
									
									
									Collection<AtcClienteTelefoneRetorno> colecaoAtcClienteTelefoneRetorno = repositorioIntegracao.
											pesquisarAtcClienteTelefoneRetorno(atcClienteRetorno.getComp_id().getAtcCliId());
									
									//1.2.	Inserir na tabela CLIENTE_FONE_ATLZ_CAD
									if ( colecaoAtcClienteImovelRetorno != null && !colecaoAtcClienteImovelRetorno.isEmpty() ) {
										
										Iterator<AtcClienteTelefoneRetorno> iteratorAtcClienteTelefoneRetorno = colecaoAtcClienteTelefoneRetorno.iterator();
										while( iteratorAtcClienteTelefoneRetorno.hasNext() ) {
											
											AtcClienteTelefoneRetorno atcClienteTelefoneRetorno = (AtcClienteTelefoneRetorno) iteratorAtcClienteTelefoneRetorno.next();
											
											this.inserirClienteFoneAtualizacaoCadastral(idClienteAtualizacaoCadastral, atcClienteTelefoneRetorno);
										}
									}
								}
							} 
							
							//2.3.	Seleciona as Subcategorias 
							Collection<AtcSubcategoriaImovelRetorno> colecaoAtcSubcategoriaImovelRetorno = repositorioIntegracao.
									pesquisarAtcSubcategoriaImovelRetorno(atcImovelRetorno.getComp_id().getAtcImoId());
							
							if ( colecaoAtcSubcategoriaImovelRetorno != null && !colecaoAtcSubcategoriaImovelRetorno.isEmpty() ) {
								
								Iterator<AtcSubcategoriaImovelRetorno> iteratorAtcSubcategoriaImovelRetorno = colecaoAtcSubcategoriaImovelRetorno.iterator();
								while( iteratorAtcSubcategoriaImovelRetorno.hasNext() ) {
									
									AtcSubcategoriaImovelRetorno atcSubcategoriaImovelRetorno = (AtcSubcategoriaImovelRetorno) iteratorAtcSubcategoriaImovelRetorno.next();
									//[SB0003] - Inserir Dados da Subcategoria
									this.inserirImovelSubcategoriaAtualizacaoCadastral(idImovelAtualizacaoCadastral, atcSubcategoriaImovelRetorno, novoImovel);
								}
							}
							
							
							//2.4.	Seleciona os HidrÙmetros 
							Collection<AtcHidrometroInstalacaoRetorno> colecaoAtcHidrometroInstalacaoRetorno = repositorioIntegracao.
									pesquisarAtcHidrometroInstalacaoRetorno(atcImovelRetorno.getComp_id().getAtcImoId());
							
							if ( colecaoAtcHidrometroInstalacaoRetorno != null && !colecaoAtcHidrometroInstalacaoRetorno.isEmpty() ){
								
								Iterator<AtcHidrometroInstalacaoRetorno> iteratorAtcHidrometroInstalacaoRetorno = colecaoAtcHidrometroInstalacaoRetorno.iterator();
								while( iteratorAtcHidrometroInstalacaoRetorno.hasNext() ) {
									
									AtcHidrometroInstalacaoRetorno atcHidrometroInstalacaoRetorno = (AtcHidrometroInstalacaoRetorno) iteratorAtcHidrometroInstalacaoRetorno.next();
									
									//[SB0004] - Inserir Dados da InstalaÁ„o de HidrÙmetro
									this.inserirHidrometroInstalacaoHistoricoAtualizacaoCadastral(idImovelAtualizacaoCadastral, atcHidrometroInstalacaoRetorno);
									
								}
							}
							
							//2.5
							atcImovelRetorno.setAcaImoDataHoraImportacao(new Date());
							
							repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcImovelRetorno);
					
						} else {
							
							atcImovelRetorno.setAcaImoDefinitivo(false);
							repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcImovelRetorno);
						}
					}
				}
				
				/**
				 * Caso a coleÁ„o de imoveis retornados for menor que a
				 * quantidade de registros seta a flag indicando que a paginaÁ„o
				 * terminou.
				 */
				if (colecaoAtcImovelRetorno == null
						|| colecaoAtcImovelRetorno.size() < quantidadeRegistros) {

					flagTerminou = true;
				}

				if (colecaoAtcImovelRetorno != null) {
					colecaoAtcImovelRetorno.clear();
					colecaoAtcImovelRetorno = null;
				}
			
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			sessionContext.setRollbackOnly();
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0001-Inserir Dados do ImÛvel]. 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirImovelAtualizacaoCadastral(AtcImovelRetorno atcImovelRetorno , boolean novoImovel) throws ControladorException {
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
		Integer idImovelAtualizacaoCadastral = null;
		
		try {
			
			//IMOV_ID
			Imovel imovel = new Imovel();
			boolean imovelValido = true;
			
			Integer idImovel = Integer.valueOf(0);
			if ( !novoImovel ) {
				idImovel = atcImovelRetorno.getComp_id().getAtcImoId();	
			}
			imovelAtualizacaoCadastral.setImovel(idImovel);
			
			//IMAC_NNIMOVEL
			if ( atcImovelRetorno.getAtcImoNumero() != null && !atcImovelRetorno.getAtcImoNumero().trim().equals("") && 
					atcImovelRetorno.getAtcImoNumero().length() < 6){
				imovelAtualizacaoCadastral.setNumeroImovel(atcImovelRetorno.getAtcImoNumero());
			} else {
				imovelAtualizacaoCadastral.setNumeroImovel("NI");
			}
			
			//IMAC_DSCOMPLEMENTOENDERECO
			if ( atcImovelRetorno.getAtcImoComplemento() != null && !atcImovelRetorno.getAtcImoComplemento().trim().equals("") &&
					 atcImovelRetorno.getAtcImoComplemento().length() < 26 ) {
				
				imovelAtualizacaoCadastral.setComplementoEndereco(atcImovelRetorno.getAtcImoComplemento());
			}
			
			//PCAL_ID
			if ( atcImovelRetorno.getAtcPvcId() != null ){
				imovelAtualizacaoCadastral.setIdPavimentoCalcada(atcImovelRetorno.getAtcPvcId());
			}
			
			//PRUA_ID
			if ( atcImovelRetorno.getAtcPvrId() != null ) {
				imovelAtualizacaoCadastral.setIdPavimentoRua(atcImovelRetorno.getAtcPvrId());
			}
			
			//FTAB_ID
			if ( atcImovelRetorno.getAtcFabId() != null ) {
				imovelAtualizacaoCadastral.setIdFonteAbastecimento(atcImovelRetorno.getAtcFabId());
			}
			
			//LAST_ID
			if ( atcImovelRetorno.getAtcSlaId() != null ) {
				imovelAtualizacaoCadastral.setIdLigacaoAguaSituacao(atcImovelRetorno.getAtcSlaId());
			}
			
			//LEST_ID
			if ( atcImovelRetorno.getAtcSleId() != null ) {
				imovelAtualizacaoCadastral.setIdLigacaoEsgotoSituacao(atcImovelRetorno.getAtcSleId());
			}
			
			//IPER_ID
			if ( atcImovelRetorno.getAtcIpfId() != null ) {
				imovelAtualizacaoCadastral.setIdImovelPerfil(atcImovelRetorno.getAtcIpfId());
			}
			
			//IMAC_NNCONTRATOENERGIA
			if ( atcImovelRetorno.getAtcImoContratoEnergia() != null && !atcImovelRetorno.getAtcImoContratoEnergia().trim().equals("") 
					&& Util.validarStringNumerica(atcImovelRetorno.getAtcImoContratoEnergia()) && atcImovelRetorno.getAtcImoContratoEnergia().length() < 11 ) {
				imovelAtualizacaoCadastral.setNumeroContratoEnergia( new Long( atcImovelRetorno.getAtcImoContratoEnergia()));
			}
			
			//IMAC_NNMEDIDORENERGIA
			if ( atcImovelRetorno.getAtcImoNumeroMedidorEnergia() != null && !atcImovelRetorno.getAtcImoNumeroMedidorEnergia().trim().equals("") 
					&& Util.validarStringNumerica(atcImovelRetorno.getAtcImoNumeroMedidorEnergia()) && atcImovelRetorno.getAtcImoNumeroMedidorEnergia().length() < 11 ) {
				imovelAtualizacaoCadastral.setNumeroMedidirEnergia(atcImovelRetorno.getAtcImoNumeroMedidorEnergia());
			}
			
			//ACON_ID
			if ( atcImovelRetorno.getAtcAcfId() != null ) {
				imovelAtualizacaoCadastral.setIdAreaConstruidaFaixa(atcImovelRetorno.getAtcAcfId());
			}
			
			//IMAC_ICALERTATARIFASOCIAL
			if ( atcImovelRetorno.getAtcImoAnalizarTarifaSocial() ) {
				imovelAtualizacaoCadastral.setIndicadorAlertaTarifaSocial(ConstantesSistema.SIM);
			} else {
				imovelAtualizacaoCadastral.setIndicadorAlertaTarifaSocial(ConstantesSistema.NAO);
			}
			
			//IMAC_ICATUALIZADO	
			imovelAtualizacaoCadastral.setIndicadorAtualizado(ConstantesSistema.NAO);
			
			//IMAC_ICPENDENTE	
			imovelAtualizacaoCadastral.setIndicadorPendente(ConstantesSistema.NAO);
			
			//IMAC_ICDADOSRETORNO	
			imovelAtualizacaoCadastral.setIndicadorDadosRetorno(ConstantesSistema.SIM);
			
			//IMAC_DTRECEBIMENTOMOVIMENTO	
			imovelAtualizacaoCadastral.setDataRecebimentoMovimento(new Date());
			
			//SIAC_ID	
			imovelAtualizacaoCadastral.setIdSituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.COLETADO);
			
			//IMAC_TMULTIMAALTERACAO	
			imovelAtualizacaoCadastral.setUltimaAlteracao(new Date());
			
			//IMAC_TMVISITA	
			if ( atcImovelRetorno.getAcaImoDataHoraAtualizacao() != null ) {
				imovelAtualizacaoCadastral.setDataVisita(atcImovelRetorno.getAcaImoDataHoraAtualizacao());
			}
			
			//IMAC_ICPOCO	
			if ( atcImovelRetorno.getAtcImoTemPoco() ) {
				imovelAtualizacaoCadastral.setIndicadorPoco(ConstantesSistema.SIM);
			} else {
				imovelAtualizacaoCadastral.setIndicadorPoco(ConstantesSistema.NAO);
			}
			
			
			if ( atcImovelRetorno.getAtcImoIdRef() != null && !atcImovelRetorno.getAtcImoIdRef().trim().equals("") ) {
				
				if ( Util.validarStringNumerica(atcImovelRetorno.getAtcImoIdRef()) ) {
					imovelAtualizacaoCadastral.setImovelReferencia(Integer.valueOf(atcImovelRetorno.getAtcImoIdRef()));
				}
			}
			
			if ( atcImovelRetorno.getAcaImoFachada() != null ) {
				imovelAtualizacaoCadastral.setImagemFachada(atcImovelRetorno.getAcaImoFachada());
			}
			
			if ( atcImovelRetorno.getIdParametroAtualizacaoCadastral() != null ) {
				ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro = repositorioCadastro.
						pesquisarParametroTabelaAtualizacaoCadastro(atcImovelRetorno.getIdParametroAtualizacaoCadastral());
						
				imovelAtualizacaoCadastral.setParametroTabelaAtualizacaoCadastro(parametroTabelaAtualizacaoCadastro);
				
				imovelAtualizacaoCadastral.setIdEmpresa(parametroTabelaAtualizacaoCadastro.getEmpresa().getId());
			}
			
			if ( atcImovelRetorno.getAcaAtuId() != null ) {
				
				Cadastrador cadastrador = new Cadastrador();
				cadastrador.setId(atcImovelRetorno.getAcaAtuId());
				imovelAtualizacaoCadastral.setCadastrador(cadastrador);
			}
			
			//IMAC_ICEXCLUSAO	
			imovelAtualizacaoCadastral.setIndicadorExclusao(ConstantesSistema.NAO);
			
			//LOCA_ID
			if ( atcImovelRetorno.getAtcLocId() != null ) {
				imovelAtualizacaoCadastral.setIdLocalidade(atcImovelRetorno.getAtcLocId());
			} else {
				imovelValido = false;
			}
			
			//IMAC_CDSETORCOMERCIAL
			if ( atcImovelRetorno.getSetor() != null && !atcImovelRetorno.getSetor().equals("")) {
				imovelAtualizacaoCadastral.setCodigoSetorComercial(new Integer(atcImovelRetorno.getSetor()));
			} else {
				imovelValido = false;
			}
			
			//IMAC_NUMEROQUADRA
			if ( atcImovelRetorno.getQuadra() != null && !atcImovelRetorno.getQuadra().equals("")) {
				imovelAtualizacaoCadastral.setNumeroQuadra(new Integer(atcImovelRetorno.getQuadra()));
			} else {
				imovelValido = false;
			}
			
			//IMAC_NNLOTE
			if ( atcImovelRetorno.getAtcImoNovoLote() != null && !atcImovelRetorno.getAtcImoNovoLote().trim().equals("") ) {
				imovelAtualizacaoCadastral.setLote(new Short(atcImovelRetorno.getAtcImoNovoLote()));
			}
			
			//IMAC_NNSUBLOTE
			if ( atcImovelRetorno.getAtcImoNovoSublote() != null && !atcImovelRetorno.getAtcImoNovoSublote().trim().equals("") ) {
				imovelAtualizacaoCadastral.setSubLote( new Short(atcImovelRetorno.getAtcImoNovoSublote()));
			}
			
			//LOGR_ID
			if ( atcImovelRetorno.getAtcNlgId() != null ) {
				imovelAtualizacaoCadastral.setIdLogradouro(Long.valueOf(atcImovelRetorno.getAtcNlgId()) ) ;
			}
			
			//BAIR_ID
			if ( atcImovelRetorno.getAtcBaiId() != null ) {
				imovelAtualizacaoCadastral.setIdBairro(atcImovelRetorno.getAtcBaiId());	
			}
			
			imovelAtualizacaoCadastral.setIndicadorBairroNovo(ConstantesSistema.NAO);
			imovelAtualizacaoCadastral.setIndicadorLogradouroNovo(ConstantesSistema.NAO);
			
			if ( imovelValido ) {
				idImovelAtualizacaoCadastral = (Integer) repositorioUtil.inserir(imovelAtualizacaoCadastral);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		
		return idImovelAtualizacaoCadastral;
	}
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0002] - Inserir Dados do Cliente
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirClienteAtualizacaoCadastral(AtcClienteRetorno atcClienteRetorno , Integer idImovelAtualizacaoCadastral, 
			AtcClienteImovelRetorno atcClienteImovelRetorno, boolean clienteExcluido, boolean novoImovel, Integer idParametroAtualizacaoCadastral) throws ControladorException {
		
		Integer idClienteAtualizacaoCadastral = null;
		
		try {
			
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = new ClienteAtualizacaoCadastral();
			
			if ( idParametroAtualizacaoCadastral != null ) {
				ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro = new ParametroTabelaAtualizacaoCadastro();
				parametroTabelaAtualizacaoCadastro.setId(idParametroAtualizacaoCadastral);
				clienteAtualizacaoCadastral.setParametroTabelaAtualizacaoCadastro(parametroTabelaAtualizacaoCadastro);
			}
			//CRTP_ID	ATC_RLT_ID
			clienteAtualizacaoCadastral.setIdClienteRelacaoTipo(atcClienteImovelRetorno.getAtcRltId());
			
			//IMOV_ID	ATC_IMO_ID
			if ( novoImovel ) {
			clienteAtualizacaoCadastral.setIdImovel(0);
			} else {
				clienteAtualizacaoCadastral.setIdImovel(atcClienteImovelRetorno.getAtcImoId());	
			}
			
			//CLAC_DTRELACAOFIM	ATC_CIM_DATA_FIM
			clienteAtualizacaoCadastral.setDataRelacaoFim(Util.converteStringParaDate(atcClienteImovelRetorno.getAtcCimDataFim()));
			
			if ( atcClienteImovelRetorno.getAtcCimDataInicio() != null && !atcClienteImovelRetorno.getAtcCimDataInicio().equals("-1") ) { 
				clienteAtualizacaoCadastral.setDataRelacaoInicio(Util.converteStringParaDate(atcClienteImovelRetorno.getAtcCimDataInicio()));
			}
			
			//IMAC_ID
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
			imovelAtualizacaoCadastral.setId(idImovelAtualizacaoCadastral);
			clienteAtualizacaoCadastral.setImovelAtualizacaoCadastral(imovelAtualizacaoCadastral);
			
			//CLIE_ID
			clienteAtualizacaoCadastral.setIdCliente(atcClienteRetorno.getComp_id().getAtcCliId().intValue());
			
			//CLAC_NMCLIENTE
			clienteAtualizacaoCadastral.setNomeCliente(atcClienteRetorno.getAtcCliNomeReceita());
			
			//CLAC_NMABREVIADO
			clienteAtualizacaoCadastral.setNomeAbreviadoCliente(atcClienteRetorno.getAtcCliNomeFantasia());
			
			//CLTP_ID
			clienteAtualizacaoCadastral.setIdClienteTipo(atcClienteRetorno.getAtcCltId());
			
			//CLAC_NNRG
			clienteAtualizacaoCadastral.setRg(atcClienteRetorno.getAtcCliRg());
			
			//CLAC_DTRGEMISSAO
			clienteAtualizacaoCadastral.setDataEmissaoRg(Util.converteStringParaDate(atcClienteRetorno.getAtcCliDataExpedicao()));
			
			//CLAC_DSABREVIADAOERG
			if ( atcClienteRetorno.getAtcOrgId() != null && !atcClienteRetorno.getAtcOrgId().equals(0)) {
				
				FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
				filtroOrgaoExpedidorRg.adicionarParametro( new ParametroSimples(FiltroOrgaoExpedidorRg.ID, atcClienteRetorno.getAtcOrgId()));
				
				Collection<OrgaoExpedidorRg> colecaoOerg = repositorioUtil.pesquisar(filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class.getName());
				if ( colecaoOerg != null && !colecaoOerg.isEmpty() ) {
					OrgaoExpedidorRg orgaoExpedidorRg = (OrgaoExpedidorRg) Util.retonarObjetoDeColecao(colecaoOerg);
					clienteAtualizacaoCadastral.setDsAbreviadaOrgaoExpedidorRg(orgaoExpedidorRg.getDescricaoAbreviada());
				}
			}
			
			//CLAC_DSUFSIGLAOERG
			if ( atcClienteRetorno.getAtcUnfId() != null && !atcClienteRetorno.getAtcUnfId().equals(0)) {
			
				FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
				filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.ID, atcClienteRetorno.getAtcUnfId()));
			
				UnidadeFederacao unidadeFederacao = (UnidadeFederacao) Util.retonarObjetoDeColecao(
						repositorioUtil.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName()));
				
				clienteAtualizacaoCadastral.setDsUFSiglaOrgaoExpedidorRg(unidadeFederacao.getSigla());
			}
			
			//CLAC_DTNASCIMENTO
			if ( atcClienteRetorno.getAtcCliDataNascimento() != null ) {
				clienteAtualizacaoCadastral.setDataNascimento(Util.converteStringParaDate(atcClienteRetorno.getAtcCliDataNascimento()));
			}
			
			//PROF_ID
			if ( atcClienteRetorno.getAtcProId() != null ) {
				clienteAtualizacaoCadastral.setIdProfissao(atcClienteRetorno.getAtcProId());
			}
			
			//PSEX_ID
			if ( atcClienteRetorno.getAtcCliSexo() != null && !atcClienteRetorno.getAtcCliSexo().equals(Integer.valueOf(0))) {
				clienteAtualizacaoCadastral.setIdPessoaSexo(atcClienteRetorno.getAtcCliSexo());
			}
			
			//CLAC_NNCPFCNPJ	Caso ATC_CLI_TIPO_PESSOA = 0, ATC_CLI_CPF. Caso contr·rio, ATC_CLI_CNPJ. 
			if ( atcClienteRetorno.getAtcCliTipoPessoa() == 0 ) {
				if ( atcClienteRetorno.getAtcCliCpf() != null && !atcClienteRetorno.getAtcCliCpf().trim().equals("") ) {
					clienteAtualizacaoCadastral.setCpfCnpj(atcClienteRetorno.getAtcCliCpf().replace(".", "").replace("-", "").replace("/", ""));
				}
			} else {
				if ( atcClienteRetorno.getAtcCliCnpj() != null && !atcClienteRetorno.getAtcCliCnpj().trim().equals("") ) {
					clienteAtualizacaoCadastral.setCpfCnpj(atcClienteRetorno.getAtcCliCnpj().replace(".", "").replace("-", "").replace("/", ""));
				}
			}
			
			//CLAC_DSEMAIL	ATC_CLI_EMAIL
			if ( atcClienteRetorno.getAtcCliEmail() != null && !atcClienteRetorno.getAtcCliEmail().equals("") ) {
				clienteAtualizacaoCadastral.setEmail(atcClienteRetorno.getAtcCliEmail());
			}
			
			//CLAC_NNMAE
			if ( atcClienteRetorno.getAtcCliNomeMae() != null && !atcClienteRetorno.getAtcCliNomeMae().equals("") ) {
				clienteAtualizacaoCadastral.setNomeMae(atcClienteRetorno.getAtcCliNomeMae());
			}
			
			//RATV_ID
			if ( atcClienteRetorno.getAtcRamId() != null ) {
				clienteAtualizacaoCadastral.setIdRamoAtividade(atcClienteRetorno.getAtcRamId());
			}
			
			//CLAC_TMULTIMAALTERACAO	Data corrente.
			clienteAtualizacaoCadastral.setUltimaAlteracao(new Date());
			
			if ( clienteExcluido ) {
				clienteAtualizacaoCadastral.setIndicadorExclusao(ConstantesSistema.SIM);
			} else {
				clienteAtualizacaoCadastral.setIndicadorExclusao(ConstantesSistema.NAO);
			}
		
			idClienteAtualizacaoCadastral = (Integer) repositorioUtil.inserir(clienteAtualizacaoCadastral);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		
		return idClienteAtualizacaoCadastral;
	}
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0002] - Inserir Dados do Cliente
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirClienteFoneAtualizacaoCadastral(Integer idClienteAtualizacaoCadastral, AtcClienteTelefoneRetorno atcClienteTelefoneRetorno) throws ControladorException {
		
		try {
			
			ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
			
			//CLAC_ID
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = new ClienteAtualizacaoCadastral();
			clienteAtualizacaoCadastral.setId(idClienteAtualizacaoCadastral);
			clienteFoneAtualizacaoCadastral.setClienteAtualizacaoCadastral(clienteAtualizacaoCadastral);

			//CFAC_CDDDD
			if ( atcClienteTelefoneRetorno.getAtcCtlDdd() != null && atcClienteTelefoneRetorno.getAtcCtlDdd().equals("") 
					&& atcClienteTelefoneRetorno.getAtcCtlDdd().length() < 3) {
				clienteFoneAtualizacaoCadastral.setDdd(atcClienteTelefoneRetorno.getAtcCtlDdd());
			}
			//CFAC_NNFONE
			clienteFoneAtualizacaoCadastral.setTelefone(atcClienteTelefoneRetorno.getAtcCtlNumero());
			
			//CFAC_NNFONERAMAL
			clienteFoneAtualizacaoCadastral.setRamal(atcClienteTelefoneRetorno.getAtcCtlRamal());
			
			//FNET_ID
			clienteFoneAtualizacaoCadastral.setIdFoneTipo(atcClienteTelefoneRetorno.getAtcTtpId());
			
			//CFAC_TMULTIMAALTERACAO
			clienteFoneAtualizacaoCadastral.setUltimaAlteracao(new Date());
			
			//CFAC_ICFONEPADRAO
			if ( atcClienteTelefoneRetorno.getAtcCliFonePadrao() != null && atcClienteTelefoneRetorno.getAtcCliFonePadrao() ) {
				clienteFoneAtualizacaoCadastral.setIndicadorFonePadrao(ConstantesSistema.SIM);
			} else {
				clienteFoneAtualizacaoCadastral.setIndicadorFonePadrao(ConstantesSistema.NAO);
			}
			
			//CFAC _NMCONTATO
			clienteFoneAtualizacaoCadastral.setContato(atcClienteTelefoneRetorno.getAtcCtlNomeContato());
			
			repositorioUtil.inserir(clienteFoneAtualizacaoCadastral);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
	}
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0003] - Inserir Dados da Subcategoria
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirImovelSubcategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral, AtcSubcategoriaImovelRetorno atcSubcategoriaImovelRetorno,
			boolean novoImovel) throws ControladorException {
		
		try {
			
			ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			
			//IMAC_ID	IMAC_ID
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
			imovelAtualizacaoCadastral.setId(idImovelAtualizacaoCadastral);
			imovelSubcategoriaAtualizacaoCadastral.setImovelAtualizacaoCadastral(imovelAtualizacaoCadastral);
			
			//IMOV_ID
			if ( novoImovel ) {
				imovelSubcategoriaAtualizacaoCadastral.setIdImovel(0);
			} else {
				imovelSubcategoriaAtualizacaoCadastral.setIdImovel(atcSubcategoriaImovelRetorno.getAtcImoId());
			}
			//CATG_ID	Selecionar CATG_ID da tabela CADASTRO.SUBCATEGORIA onde SCAT_ID =  ATC_SUB_ID.
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro( new ParametroSimples(FiltroSubCategoria.ID, atcSubcategoriaImovelRetorno.getAtcSubId()));
			
			Collection<Subcategoria> colecaoSubcategorias = repositorioUtil.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if ( colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty() ) {
				
				Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubcategorias);
				if ( subcategoria != null && subcategoria.getCategoria() != null ) {
					imovelSubcategoriaAtualizacaoCadastral.setIdCategoria(subcategoria.getCategoria().getId());
				
					FiltroCategoria filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro( new ParametroSimples(FiltroCategoria.CODIGO, subcategoria.getCategoria().getId()));
					
					Collection<Categoria> colecaoCategoria = repositorioUtil.pesquisar(filtroCategoria, Categoria.class.getName());
					
					if ( colecaoCategoria != null && !colecaoCategoria.isEmpty() ) {
						
						Categoria categoria = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoria);
						//ISAC_DSCATEGORIA
						imovelSubcategoriaAtualizacaoCadastral.setDescricaoCategoria(categoria.getDescricao());
					}
					
					//ISAC_DSSUBCATEGORIA	
					imovelSubcategoriaAtualizacaoCadastral.setDescricaoSubcategoria(subcategoria.getDescricao());
				}
			}
			
			//SCAT_ID
			imovelSubcategoriaAtualizacaoCadastral.setIdSubcategoria(atcSubcategoriaImovelRetorno.getAtcSubId());
			
			//ISAC_QTECONOMIA
			imovelSubcategoriaAtualizacaoCadastral.setQuantidadeEconomias( (short) atcSubcategoriaImovelRetorno.getAtcSciQtdEconomias());
			
			//ISAC_TMULTIMAALTERACAO
			imovelSubcategoriaAtualizacaoCadastral.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(imovelSubcategoriaAtualizacaoCadastral);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
	}
	
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0004] - Inserir Dados da InstalaÁ„o de HidrÙmetro
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirHidrometroInstalacaoHistoricoAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral, AtcHidrometroInstalacaoRetorno atcHidrometroInstalacaoRetorno) 
			throws ControladorException {
		
		try {
			
			HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroIsntHistAtlzCad = new HidrometroInstalacaoHistoricoAtualizacaoCadastral();
			
			//IMAC_ID
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
			imovelAtualizacaoCadastral.setId(idImovelAtualizacaoCadastral);
			hidrometroIsntHistAtlzCad.setImovelAtualizacaoCadastral(imovelAtualizacaoCadastral);
			
			//HIAC_NNHIDROMETRO
			hidrometroIsntHistAtlzCad.setNumeroHidrometro(atcHidrometroInstalacaoRetorno.getAcaInsNumeroHidrometro().toUpperCase());
			
			//HIAC_DTINSTALACAOHIDROMETRO
			hidrometroIsntHistAtlzCad.setDataInstalacaoHidrometro(Util.converteStringParaDate(atcHidrometroInstalacaoRetorno.getAtcInsDataInstalacao()));
			
			//MEDT_ID
			MedicaoTipo medicaoTipo = new MedicaoTipo();
			medicaoTipo.setId(atcHidrometroInstalacaoRetorno.getAtcMtpId());
			hidrometroIsntHistAtlzCad.setMedicaoTipo(medicaoTipo);
			
			//HILI_ID
			HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
			hidrometroLocalInstalacao.setId(atcHidrometroInstalacaoRetorno.getAtcHliId());
			hidrometroIsntHistAtlzCad.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
			
			//HIPR_ID
			HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
			hidrometroProtecao.setId(atcHidrometroInstalacaoRetorno.getAtcHprId());
			hidrometroIsntHistAtlzCad.setHidrometroProtecao(hidrometroProtecao);
			
			//HIAC_NNINSTALACAOHIDMT
			hidrometroIsntHistAtlzCad.setNumeroInstalacaoHidrometro(atcHidrometroInstalacaoRetorno.getAtcInsLeitura());
			
			//HIAC_TMULTIMAALTERACAO
			hidrometroIsntHistAtlzCad.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(hidrometroIsntHistAtlzCad);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
	}
	
	
	/**
	 * [UC1322] Rotina de Realizar Inclus„o de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDadosAdmin(Integer idFuncionalidadeIniciada,Integer idParametro) throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o inÌcio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.FUNCIONALIDADE,0);

		try {

			Collection<ImovelAtualizacaoCadastral> colecaoImovelAtualizacaoCadastral = repositorioCadastro.pesquisarColecaoImovelAtualizacaoCadastral(idParametro);
		
			//[SB0005] - Atualizar Tabelas B·sicas
			this.atualizarTabelasBasicas();
			
			//[FS0001 - Verificar existÍncia de registros para atualizaÁ„o].
			if ( colecaoImovelAtualizacaoCadastral != null && !colecaoImovelAtualizacaoCadastral.isEmpty() ) {
			
				Iterator<ImovelAtualizacaoCadastral> iteratorImovelAtualizacaoCadastral = colecaoImovelAtualizacaoCadastral.iterator();
				while ( iteratorImovelAtualizacaoCadastral.hasNext() ) {
					ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral) iteratorImovelAtualizacaoCadastral.next();
					
					//Verifica se ja existe registro gerado para esse imovel - Caso existe remove os registros
					this.removerDadosImovelAdmin(imovelAtualizacaoCadastral.getImovel());
					
					//[SB0001-Inserir Dados do ImÛvel]. 
					this.inserirAtcImovel(imovelAtualizacaoCadastral); 
					
					//2.2.	Seleciona os Clientes 
					Collection<ClienteAtualizacaoCadastral> colecaoClienteAtualizacaoCadastral = repositorioCadastro.
							pesquisarClienteAtualizacaoCadastral(imovelAtualizacaoCadastral.getId());
					
					if ( colecaoClienteAtualizacaoCadastral != null && !colecaoClienteAtualizacaoCadastral.isEmpty() ) {
					
						Iterator<ClienteAtualizacaoCadastral> iteratorClienteAtualizacaoCadastral = colecaoClienteAtualizacaoCadastral.iterator();
						while( iteratorClienteAtualizacaoCadastral.hasNext() ) {
							
							ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = (ClienteAtualizacaoCadastral) iteratorClienteAtualizacaoCadastral.next();
							
							//[SB0002] - Inserir Dados do Cliente
							this.inserirAtcCliente(clienteAtualizacaoCadastral, imovelAtualizacaoCadastral.getImovel());
								
							Collection colecaoClienteFoneAtualizacaoCadastral = getControladorCliente().
									obterDadosClienteFoneAtualizacaoCadastral(clienteAtualizacaoCadastral.getId());
								
							//1.2.	Inserir na tabela CLIENTE_FONE_ATLZ_CAD
							if ( colecaoClienteFoneAtualizacaoCadastral != null && !colecaoClienteFoneAtualizacaoCadastral.isEmpty() ) {
								
								Iterator iteratorClienteFoneAtualizacaoCadastral = colecaoClienteFoneAtualizacaoCadastral.iterator();
								while( iteratorClienteFoneAtualizacaoCadastral.hasNext() ) {
									
									ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = (ClienteFoneAtualizacaoCadastral) iteratorClienteFoneAtualizacaoCadastral.next();
									
									this.inserirAtcClienteTelefone(clienteAtualizacaoCadastral.getIdCliente(), clienteFoneAtualizacaoCadastral);
								}
							}
						}
					} 
					
					//2.3.	Seleciona as Subcategorias 
					Collection<ImovelSubcategoriaAtualizacaoCadastral> colecaoImovelSubcategoriaAtualizacaoCadastral = repositorioCadastro.
							pesquisarImovelSubCategoriaAtualizacaoCadastral(imovelAtualizacaoCadastral.getId());
							
					if ( colecaoImovelSubcategoriaAtualizacaoCadastral != null && !colecaoImovelSubcategoriaAtualizacaoCadastral.isEmpty() ) {
						
						Iterator<ImovelSubcategoriaAtualizacaoCadastral> iteratorImovelSubcategoriaAtualizacaoCadastral = colecaoImovelSubcategoriaAtualizacaoCadastral.iterator();
						
						while ( iteratorImovelSubcategoriaAtualizacaoCadastral.hasNext() ) {
							
							ImovelSubcategoriaAtualizacaoCadastral imovelSubCategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral) 
									iteratorImovelSubcategoriaAtualizacaoCadastral.next();
							
							//[SB0003] - Inserir Dados da Subcategoria
							this.inserirAtcSubcategoriaImovel(imovelSubCategoriaAtualizacaoCadastral);
						}
					}
					
					//2.4.	Seleciona os HidrÙmetros 
					Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral> colecaoHidrometro = repositorioCadastro.
							pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastral(imovelAtualizacaoCadastral.getId(), null);
					
					if ( colecaoHidrometro != null && !colecaoHidrometro.isEmpty() ){
						
						Iterator<HidrometroInstalacaoHistoricoAtualizacaoCadastral> iteratorHidrometro = colecaoHidrometro.iterator();
						while( iteratorHidrometro.hasNext() ) {
							
							HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometro = (HidrometroInstalacaoHistoricoAtualizacaoCadastral) iteratorHidrometro.next();
							
							//[SB0004] - Inserir Dados da InstalaÁ„o de HidrÙmetro
							this.inserirAtcHidrometroInstalacao(hidrometro, imovelAtualizacaoCadastral.getImovel());
						}
					}
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * [UC1322] Rotina de Realizar Inclus„o de Dados Admin
	 * [SB0005] - Atualizar Tabelas B·sicas
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarTabelasBasicas() throws ControladorException {
		
		
		try {
			//pesquisa a colecao de municipios liberados para inserir na base de atualizacao cadastral
			Collection<Municipio> colecaoMunicipio = repositorioCadastro.pesquisarMunicipioLiberadoParaAtualizacaoCadastral();
			
			if ( colecaoMunicipio != null && !colecaoMunicipio.isEmpty() ) {
				
				Iterator<Municipio> iteratorMunicipio = colecaoMunicipio.iterator();
				while( iteratorMunicipio.hasNext() ) {
					
					Municipio municipio = (Municipio) iteratorMunicipio.next();
					
					AtcMunicipio atcMunicipio = new AtcMunicipio();
					
					atcMunicipio.setAtcMunId(municipio.getId());
					
					atcMunicipio.setAtcMunNome(municipio.getNome());
					
					//Caso o municipio n„o exista na base vai inserir
					if ( !repositorioIntegracao.existeAtcMunicipio(municipio.getId()) ) {
						repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcMunicipio);
					}
				}
			}
			
			
			//pesquisa a colecao de logradouros liberados para inserir ou atualizar na base de atualizacao cadastral
			Collection<Logradouro> colecaoLogradouro = repositorioCadastro.pesquisarLogradouroLiberadoParaAtualizacaoCadastral();
			
			if ( colecaoLogradouro != null && !colecaoLogradouro.isEmpty() ) {
				
				Iterator<Logradouro> iteratorLogradouro = colecaoLogradouro.iterator();
				
				while ( iteratorLogradouro.hasNext() ) {
					
					Logradouro logradouro = (Logradouro) iteratorLogradouro.next();
					
					AtcLogradouro atcLogradouroExistente = repositorioIntegracao.existeAtcLogradouro(logradouro.getId());
					
					//Caso o logradouro exista na base de atualizacao cadastral, atualiza os dados do logradouro
					if ( atcLogradouroExistente != null && atcLogradouroExistente.getAtcLgrId() != null ) {
						
						atcLogradouroExistente = montarAtcLogradouro(atcLogradouroExistente, logradouro);
						repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcLogradouroExistente);
					
					} else {
						//Caso o logradouro n„o exista na base de atualizacao cadastral, inseri o atclogradouro
						AtcLogradouro atcLogradouro = new AtcLogradouro(); 
						atcLogradouro = montarAtcLogradouro(atcLogradouro, logradouro);
						repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcLogradouro);
					}
					
					//atualiza logradouro com o indicador de que ja foi enviado para base de atualizacao cadastral
					logradouro.setIndicadorEnvioAtualizacaoCadastral(ConstantesSistema.SIM);
					repositorioUtil.atualizar(logradouro);
				}
			
			}
			
			//pesquisa a colecao de bairros liberados para inserir ou atualizar na base de atualizacao cadastral
			Collection<Bairro> colecaoBairro = repositorioCadastro.pesquisarBairroLiberadoParaAtualizacaoCadastral();
			
			if ( colecaoBairro != null && !colecaoBairro.isEmpty() ) {
				
				Iterator<Bairro> iteratorBairro = colecaoBairro.iterator();
				while( iteratorBairro.hasNext() ) {
					
					Bairro bairro = (Bairro) iteratorBairro.next();
					
					//verifica se existe atcbairro, caso exista atualiza o atcBairro
					AtcBairro atcBairroExistente = repositorioIntegracao.existeAtcBairro(bairro.getId());
					if ( atcBairroExistente != null && atcBairroExistente.getAtcBaiId() != null ) {
						
						atcBairroExistente = montarAtcBairro(atcBairroExistente, bairro);
						repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcBairroExistente);
					} else {
						//Caso nao exista atcBairro com esse id, vai inserir um novo atcBairro na base de atualizacao cadastral
						AtcBairro atcBairro = new AtcBairro();
						atcBairro = montarAtcBairro(atcBairro, bairro);
						repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcBairro);
					}
					
					//Atualiza o indicador de enviado para base de atualizacao cadastral do bairro.
					bairro.setIndicadorEnvioAtualizacaoCadastral(ConstantesSistema.SIM);
					repositorioUtil.atualizar(bairro);
				}
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo responsavel por montar o objeto atcBairro a partir do Bairro 
	 * 
	 * @param atcBairro
	 * @param bairro
	 * @return
	 */
	public AtcBairro montarAtcBairro(AtcBairro atcBairro, Bairro bairro) {
		

		atcBairro.setAtcBaiId(bairro.getId());
		
		if ( bairro.getNome() != null ) {
			atcBairro.setAtcBaiNome(bairro.getNome());
		} else {
			atcBairro.setAtcBaiNome("");
		}
		atcBairro.setAtcMunId(bairro.getMunicipio().getId());
	
		return atcBairro;
	}
	
	/**
	 * Metodo responsavel por montar o objeto atcLogradouro a partir do Logradouro
	 * 
	 * @param atcLogradouro
	 * @param logradouro
	 * @return
	 */
	public AtcLogradouro montarAtcLogradouro(AtcLogradouro atcLogradouro, Logradouro logradouro) {
		
		atcLogradouro.setAtcLgrId(logradouro.getId());
		
		atcLogradouro.setAtcLgrDescricao(logradouro.getNome());
		
		atcLogradouro.setAtcLtpId(logradouro.getLogradouroTipo().getId());
		
		if ( logradouro.getLogradouroTitulo() != null && logradouro.getLogradouroTitulo().getId() != null ) {
			atcLogradouro.setAtcLttId(logradouro.getLogradouroTitulo().getId());
		}
		
		atcLogradouro.setAtcMunId(logradouro.getMunicipio().getId());
	
		return atcLogradouro;
	}
	
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0001-Inserir Dados do ImÛvel]. 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirAtcImovel(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral ) throws ControladorException {
		
		AtcImovel atcImovel = new AtcImovel();
		try {
			//IMOV_ID
			atcImovel.setAtcImoId(imovelAtualizacaoCadastral.getImovel());
			
			//IMAC_NNIMOVEL
			if ( imovelAtualizacaoCadastral.getNumeroImovel() != null && !imovelAtualizacaoCadastral.getNumeroImovel().equals("") ){
				atcImovel.setAtcImoNumero(imovelAtualizacaoCadastral.getNumeroImovel());
			}
			
			//IMAC_DSCOMPLEMENTOENDERECO
			if ( imovelAtualizacaoCadastral.getComplementoEndereco() != null && !imovelAtualizacaoCadastral.getComplementoEndereco().equals("") ) {
				atcImovel.setAtcImoComplemento(imovelAtualizacaoCadastral.getComplementoEndereco());
			}
			
			//PCAL_ID
			if ( imovelAtualizacaoCadastral.getIdPavimentoCalcada() != null ){
				atcImovel.setAtcPvcId(imovelAtualizacaoCadastral.getIdPavimentoCalcada());
			}
			
			//PRUA_ID
			if ( imovelAtualizacaoCadastral.getIdPavimentoRua() != null ) {
				atcImovel.setAtcPvrId(imovelAtualizacaoCadastral.getIdPavimentoRua());
			}
			
			//FTAB_ID
			if ( imovelAtualizacaoCadastral.getIdFonteAbastecimento() != null ) {
				atcImovel.setAtcFabId(imovelAtualizacaoCadastral.getIdFonteAbastecimento());
			}
			
			//LAST_ID
			if ( imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao() != null ) {
				atcImovel.setAtcSlaId(imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao());
			}
			
			//LEST_ID
			if ( imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao() != null ) {
				atcImovel.setAtcSleId(imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao());
			}
			
			//IPER_ID
			if ( imovelAtualizacaoCadastral.getIdImovelPerfil() != null ) {
				atcImovel.setAtcIpfId(imovelAtualizacaoCadastral.getIdImovelPerfil());
			}
			
			//IMAC_ICALERTATARIFASOCIAL
			atcImovel.setAtcImoAnalizarTarifaSocial(false); 
					
			//IMAC_NNCONTRATOENERGIA
			if ( imovelAtualizacaoCadastral.getNumeroContratoEnergia() != null ) {
				atcImovel.setAtcImoContratoEnergia(imovelAtualizacaoCadastral.getNumeroContratoEnergia().toString());
			}
			
			//IMAC_NNMEDIDORENERGIA
			if ( imovelAtualizacaoCadastral.getNumeroMedidirEnergia() != null && !imovelAtualizacaoCadastral.getNumeroMedidirEnergia().equals("") ) {
				atcImovel.setAtcImoNumeroMedidorEnergia(imovelAtualizacaoCadastral.getNumeroMedidirEnergia());
			}
			
			
			//IMAC_ICPOCO	
			if ( imovelAtualizacaoCadastral.getIndicadorPoco().equals(ConstantesSistema.SIM) ) {
				atcImovel.setAtcImoTemPoco(true);
			} else {
				atcImovel.setAtcImoTemPoco(false);
			}	

			atcImovel.setSetor(String.valueOf(imovelAtualizacaoCadastral.getCodigoSetorComercial()));
			
			atcImovel.setQuadra(String.valueOf(imovelAtualizacaoCadastral.getNumeroQuadra()));
			
			atcImovel.setAtcImoLote(String.valueOf(imovelAtualizacaoCadastral.getLote()));
			
			atcImovel.setAtcImoSublote(String.valueOf(imovelAtualizacaoCadastral.getSubLote()));
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro( new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, imovelAtualizacaoCadastral.getCodigoSetorComercial()));
			filtroSetorComercial.adicionarParametro( new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, imovelAtualizacaoCadastral.getIdLocalidade()));
			filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
			
			Collection<SetorComercial> colecaoSetorComercial = repositorioUtil.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if ( colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty() ) {
				
				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
				
				atcImovel.setAtcLocId(imovelAtualizacaoCadastral.getIdLocalidade());
				atcImovel.setLocalidade(setorComercial.getLocalidade().getDescricao());
				atcImovel.setAtcSetId(setorComercial.getId());
				
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro( new ParametroSimples( FiltroQuadra.ID_LOCALIDADE, imovelAtualizacaoCadastral.getIdLocalidade()));
				filtroQuadra.adicionarParametro( new ParametroSimples( FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
				filtroQuadra.adicionarParametro( new ParametroSimples( FiltroQuadra.NUMERO_QUADRA, imovelAtualizacaoCadastral.getNumeroQuadra()));
				
				Collection<Quadra> colecaoQuadra = repositorioUtil.pesquisar(filtroQuadra , Quadra.class.getName() ) ;
				
				if ( colecaoQuadra != null && !colecaoQuadra.isEmpty() ) {
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
					atcImovel.setAtcQuaId(quadra.getId());
				}
			}
			
			if (imovelAtualizacaoCadastral.getIdLogradouro() != null ) {
				atcImovel.setAtcLgrId(imovelAtualizacaoCadastral.getIdLogradouro().intValue());
			}
			
			String descricaoLogradouro = "";
			if ( imovelAtualizacaoCadastral.getDsLogradouroTipo() != null ) {
				descricaoLogradouro = imovelAtualizacaoCadastral.getDsLogradouroTipo() + " ";
			}
			
			if ( imovelAtualizacaoCadastral.getDsLogradouroTitulo() != null ) {
				descricaoLogradouro += imovelAtualizacaoCadastral.getDsLogradouroTitulo() + " "; 
			}
			
			if ( imovelAtualizacaoCadastral.getDescricaoLogradouro() != null ) {
				descricaoLogradouro += imovelAtualizacaoCadastral.getDescricaoLogradouro();
			}
			
			atcImovel.setLogradouro(  descricaoLogradouro );
			
			if ( imovelAtualizacaoCadastral.getIdBairro() != null ) {
				atcImovel.setAtcBaiId(imovelAtualizacaoCadastral.getIdBairro());
			}
			
			if ( imovelAtualizacaoCadastral.getIdAreaConstruidaFaixa() != null ) {
				atcImovel.setAtcAcfId(imovelAtualizacaoCadastral.getIdAreaConstruidaFaixa());
			}
			
			atcImovel.setIdParametroTabelaAtualizacaoCadastral(imovelAtualizacaoCadastral.getParametroTabelaAtualizacaoCadastro().getId());
			
			repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcImovel);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0002] - Inserir Dados do Cliente
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirAtcCliente(ClienteAtualizacaoCadastral clienteAtualizacaoCadastral, Integer idImovel) throws ControladorException {
		
		
		try {
			
			AtcCliente atcCliente = new AtcCliente();
			
			//CLAC_NNCPFCNPJ 
			if ( clienteAtualizacaoCadastral.getCpfCnpj() != null && Util.validacaoCPF(clienteAtualizacaoCadastral.getCpfCnpj())) {
				atcCliente.setAtcCliCpf(clienteAtualizacaoCadastral.getCpfCnpj());
				atcCliente.setAtcCliTipoPessoa(0);
			} else if ( clienteAtualizacaoCadastral.getCpfCnpj() != null && Util.validacaoCNPJ(clienteAtualizacaoCadastral.getCpfCnpj()) ){
				atcCliente.setAtcCliCnpj(clienteAtualizacaoCadastral.getCpfCnpj());
				atcCliente.setAtcCliTipoPessoa(1);
			}
			
			//ATC_CLI_NOME_RECEITA 
			if ( clienteAtualizacaoCadastral.getNomeCliente() != null && !clienteAtualizacaoCadastral.getNomeCliente().equals("") ) {
				atcCliente.setAtcCliNomeReceita(clienteAtualizacaoCadastral.getNomeCliente());
			}
			
			//ATC_CLI_NOME_FANTASIA 
			atcCliente.setAtcCliNomeFantasia(clienteAtualizacaoCadastral.getNomeAbreviadoCliente());
			
			//CLTP_ID
			atcCliente.setAtcCltId(clienteAtualizacaoCadastral.getIdClienteTipo());
			
			//CLAC_DTNASCIMENTO
			if ( clienteAtualizacaoCadastral.getDataNascimento() != null ) {
				atcCliente.setAtcCliDataNascimento(Util.formatarData(clienteAtualizacaoCadastral.getDataNascimento()));
			}
			
			//CLAC_NNRG
			atcCliente.setAtcCliRg(clienteAtualizacaoCadastral.getRg());
			
			//CLAC_DTRGEMISSAO
			atcCliente.setAtcCliDataExpedicao(Util.formatarData(clienteAtualizacaoCadastral.getDataEmissaoRg()));
			
			
			//CLAC_DSABREVIADAOERG
			if ( clienteAtualizacaoCadastral.getDsAbreviadaOrgaoExpedidorRg() != null ) {
				
				FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
				filtroOrgaoExpedidorRg.adicionarParametro( new ComparacaoTexto(FiltroOrgaoExpedidorRg.DESCRICAO_ABREVIADA, clienteAtualizacaoCadastral.getDsAbreviadaOrgaoExpedidorRg()));
				
				Collection<OrgaoExpedidorRg> colecaoOerg = repositorioUtil.pesquisar(filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class.getName());
				if ( colecaoOerg != null && !colecaoOerg.isEmpty() ) {
					OrgaoExpedidorRg orgaoExpedidorRg = (OrgaoExpedidorRg) Util.retonarObjetoDeColecao(colecaoOerg);
					atcCliente.setAtcOrgId(orgaoExpedidorRg.getId());
				}
			}
			
			//CLAC_DSUFSIGLAOERG
			if ( clienteAtualizacaoCadastral.getDsUFSiglaOrgaoExpedidorRg()!= null ) {
			
				FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
				filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.SIGLA, clienteAtualizacaoCadastral.getDsUFSiglaOrgaoExpedidorRg()));
			
				Collection<UnidadeFederacao> colecaoUnidadeFederacao = repositorioUtil.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
				if ( colecaoUnidadeFederacao != null && !colecaoUnidadeFederacao.isEmpty() ) {
				
					UnidadeFederacao unidadeFederacao = (UnidadeFederacao) Util.retonarObjetoDeColecao(colecaoUnidadeFederacao);
					atcCliente.setAtcUnfId(unidadeFederacao.getId());
				}
			}
			
			//PROF_ID
			if ( clienteAtualizacaoCadastral.getIdProfissao() != null ) {
				atcCliente.setAtcProId(clienteAtualizacaoCadastral.getIdProfissao());
			}
			
			//RATV_ID
			if ( clienteAtualizacaoCadastral.getIdRamoAtividade() != null ) {
				atcCliente.setAtcRamId(clienteAtualizacaoCadastral.getIdRamoAtividade());
			}
			
			//PSEX_ID
			if ( clienteAtualizacaoCadastral.getIdPessoaSexo() != null ) {
				atcCliente.setAtcCliSexo(clienteAtualizacaoCadastral.getIdPessoaSexo());
			}
			
			//CLAC_NNMAE
			if ( clienteAtualizacaoCadastral.getNomeMae() != null && !clienteAtualizacaoCadastral.getNomeMae().equals("") ) {
				atcCliente.setAtcCliNomeMae(clienteAtualizacaoCadastral.getNomeMae());
			}
			
			//ATC_CLI_EMAIL
			if ( clienteAtualizacaoCadastral.getEmail() != null && !clienteAtualizacaoCadastral.getEmail().equals("") ) {
				atcCliente.setAtcCliEmail(clienteAtualizacaoCadastral.getEmail());
			}
			
			atcCliente.setAtcCliId(new Long(clienteAtualizacaoCadastral.getIdCliente()));
			
			if ( !repositorioIntegracao.existeAtcCliente( new Long(clienteAtualizacaoCadastral.getIdCliente()) ) ) {
			
				repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcCliente);
			}
			AtcClienteImovel atcClienteImovel = new AtcClienteImovel();
			
			atcClienteImovel.setAtcCliId(clienteAtualizacaoCadastral.getIdCliente().intValue());
			
			//ATC_RLT_ID
			atcClienteImovel.setAtcRltId(clienteAtualizacaoCadastral.getIdClienteRelacaoTipo());
			
			//ATC_IMO_ID
			atcClienteImovel.setAtcImoId(clienteAtualizacaoCadastral.getIdImovel());
			
			if ( clienteAtualizacaoCadastral.getDataRelacaoInicio() != null ) {
				atcClienteImovel.setAtcCimDataInicio(Util.formatarData(clienteAtualizacaoCadastral.getDataRelacaoInicio()));
			}
			
			if ( clienteAtualizacaoCadastral.getDataRelacaoFim() != null ) {
				atcClienteImovel.setAtcCimDataFim(Util.formatarData(clienteAtualizacaoCadastral.getDataRelacaoFim()));
			}
			
			repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcClienteImovel);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclus„o de Dados Admin
	 * [SB0002] - Inserir Dados do Cliente
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirAtcClienteTelefone(Integer idClienteAtualizacaoCadastral, ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral) throws ControladorException {
		
		try {
			
			AtcClienteTelefone atcClienteTelefone = new AtcClienteTelefone();
			
			//CLAC_ID
			atcClienteTelefone.setAtcCliId(idClienteAtualizacaoCadastral);
			
			//CFAC_CDDDD
			if ( clienteFoneAtualizacaoCadastral.getDdd() != null ) {
				atcClienteTelefone.setAtcCtlDdd(clienteFoneAtualizacaoCadastral.getDdd());
			}
			//CFAC_NNFONE
			if ( clienteFoneAtualizacaoCadastral.getTelefone() != null ) {
				atcClienteTelefone.setAtcCtlNumero(clienteFoneAtualizacaoCadastral.getTelefone());
			}
			//CFAC_NNFONERAMAL
			if ( clienteFoneAtualizacaoCadastral.getRamal() != null ) {
				atcClienteTelefone.setAtcCtlRamal(clienteFoneAtualizacaoCadastral.getRamal());
			}
			
			//FNET_ID
			if ( clienteFoneAtualizacaoCadastral.getIdFoneTipo() != null ) {
				atcClienteTelefone.setAtcTtpId(clienteFoneAtualizacaoCadastral.getIdFoneTipo());
			}
			
			//CFAC_ICFONEPADRA
			if ( clienteFoneAtualizacaoCadastral.getIndicadorFonePadrao() != null && clienteFoneAtualizacaoCadastral.getIndicadorFonePadrao().equals(ConstantesSistema.SIM) ) {
				atcClienteTelefone.setAtcCliFonePadrao(true);
			} else {
				atcClienteTelefone.setAtcCliFonePadrao(false);
			}
			
			//CFAC _NMCONTATO
			if ( clienteFoneAtualizacaoCadastral.getContato() != null ) {
				int tamanho = 20;
				if(clienteFoneAtualizacaoCadastral.getContato().length() <= 20){
					tamanho = clienteFoneAtualizacaoCadastral.getContato().length();
				}
				atcClienteTelefone.setAtcCtlNomeContato(clienteFoneAtualizacaoCadastral.getContato().substring(0,tamanho));
			}
			
			repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcClienteTelefone);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [UC1323] Rotina de Realizar RecepÁ„o de Dados Admin
	 * [SB0003] - Inserir Dados da Subcategoria
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirAtcSubcategoriaImovel(ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral) throws ControladorException {
		
		try {
			
			AtcSubcategoriaImovel atcSubcategoriaImovel = new AtcSubcategoriaImovel();
			
			atcSubcategoriaImovel.setAtcImoId(imovelSubcategoriaAtualizacaoCadastral.getIdImovel());
			
			atcSubcategoriaImovel.setAtcSubId(imovelSubcategoriaAtualizacaoCadastral.getIdSubcategoria());
			
			atcSubcategoriaImovel.setAtcSciQtdEconomias(imovelSubcategoriaAtualizacaoCadastral.getQuantidadeEconomias());
			
			repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcSubcategoriaImovel);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclus„o de Dados Admin
	 * [SB0004] - Inserir Dados da InstalaÁ„o de HidrÙmetro
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirAtcHidrometroInstalacao(HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstalacaoHistoricoAtualizacaoCadastral, Integer idImovel) 
			throws ControladorException {
		
		try {
			
			AtcHidrometroInstalacao atcHidrometroInstalacao = new AtcHidrometroInstalacao();
			
			atcHidrometroInstalacao.setAtcInsDataInstalacao(Util.formatarData(hidrometroInstalacaoHistoricoAtualizacaoCadastral.getDataInstalacaoHidrometro()));
			
			if ( hidrometroInstalacaoHistoricoAtualizacaoCadastral.getHidrometroLocalInstalacao() != null ) {
				atcHidrometroInstalacao.setAtcHliId(hidrometroInstalacaoHistoricoAtualizacaoCadastral.getHidrometroLocalInstalacao().getId());
			}
			
			if ( hidrometroInstalacaoHistoricoAtualizacaoCadastral.getHidrometroProtecao() != null ) {
				atcHidrometroInstalacao.setAtcHprId(hidrometroInstalacaoHistoricoAtualizacaoCadastral.getHidrometroProtecao().getId());
			}
			
			if ( hidrometroInstalacaoHistoricoAtualizacaoCadastral.getMedicaoTipo() != null ) {
				atcHidrometroInstalacao.setAtcMtpId(hidrometroInstalacaoHistoricoAtualizacaoCadastral.getMedicaoTipo().getId());
			}
			
			atcHidrometroInstalacao.setAtcInsLeitura(hidrometroInstalacaoHistoricoAtualizacaoCadastral.getNumeroInstalacaoHidrometro());
			
			atcHidrometroInstalacao.setAcaInsNumeroHidrometro(hidrometroInstalacaoHistoricoAtualizacaoCadastral.getNumeroHidrometro());
			
			atcHidrometroInstalacao.setAtcImoId(idImovel);
			
			repositorioIntegracao.inserirIntegracaoAtualizacaoCadastral(atcHidrometroInstalacao);
			
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclus„o de Dados Admin
	 *
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void removerDadosImovelAdmin(Integer idImovel) throws ControladorException  {
		
		try {
		
			if ( this.repositorioIntegracao.pesquisarAtcImovel(idImovel) ) {

				Collection<AtcClienteImovel> colecaoAtcClienteImovel = this.repositorioIntegracao.pesquisarAtcClienteImovel(idImovel);
				
				if ( colecaoAtcClienteImovel != null && !colecaoAtcClienteImovel.isEmpty() ) {
					
					Iterator<AtcClienteImovel> iteratorAtcClienteImovel = colecaoAtcClienteImovel.iterator();
					while( iteratorAtcClienteImovel.hasNext() ) {
					
						AtcClienteImovel atcClienteImovel = (AtcClienteImovel) iteratorAtcClienteImovel.next();
						
						this.repositorioIntegracao.deletarAtcClienteTelefone(atcClienteImovel.getAtcCliId());
						
						this.repositorioIntegracao.deletarAtcCliente(atcClienteImovel.getAtcCliId());
						
						this.repositorioIntegracao.deletarAtcClienteImovel(atcClienteImovel.getAtcCimId());
					}
				}
				
				this.repositorioIntegracao.deletarAtcSubcategoriaImovel(idImovel);
				
				this.repositorioIntegracao.deletarAtcHidrometroInstalacao(idImovel);
				
				this.repositorioIntegracao.deletarAtcImovel(idImovel);
			}
		
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarTabelasBasicasRecepcaoDadosAdmin(Integer idFuncionalidadeIniciada) throws ControladorException {
		
		int idUnidadeIniciada = 0;
		/*
		 * Registrar o inÌcio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.FUNCIONALIDADE,0);
		
		try {

			//[SB0001]-Inserir Bairro 
			this.inserirBairro();
			
			//[SB0002] - Atualizar Bairro 
			this.atualizarBairro();		
			
			//[SB0003] - Inserir Logradouro
			this.inserirLogradouro();
			
			//[SB0004] - Atualizar Logradouro 
			this.atualizarLogradouro();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			sessionContext.setRollbackOnly();
			throw new EJBException(ex);
		}
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0004]-Atualizar Logradouro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 */
	public void atualizarLogradouro() throws ControladorException {
		
		try {
			Collection<AtcLogradouro> colecaoAtcLogradouro = repositorioIntegracao.pesquisarAtcLogradouro(AtcLogradouro.ATUALIZADO);
			
			if ( colecaoAtcLogradouro != null && !colecaoAtcLogradouro.isEmpty() ) {
				
				Iterator<AtcLogradouro> iteratorAtcLogradouro = colecaoAtcLogradouro.iterator();
				while ( iteratorAtcLogradouro.hasNext() ) {
					
					AtcLogradouro atcLogradouro = (AtcLogradouro) iteratorAtcLogradouro.next();
					
					//1.1.1
					LogradouroAdmin logradouroAdmin = this.inserirLogradouroAdmin(atcLogradouro);
					
//					//1.1.2
//					repositorioCadastro.atualizarLogradouro(logradouroAdmin);
//					
//					//1.1.3
//					logradouroAdmin.setDataAtualizacaoGsan(new Date());
//					repositorioUtil.atualizar(logradouroAdmin);
					
					atcLogradouro.setAtcLgrDataHoraImportacao(new Date());
					repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcLogradouro);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		}
	}
	
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0003] - Inserir Logradouro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 */
	public void inserirLogradouro() throws ControladorException {
		
		try {
			Collection<AtcLogradouro> colecaoAtcLogradouro = repositorioIntegracao.pesquisarAtcLogradouro(AtcLogradouro.INSERIDO);
			
			if ( colecaoAtcLogradouro != null && !colecaoAtcLogradouro.isEmpty() ) {
	
				Iterator<AtcLogradouro> iteratorAtcLogradouro = colecaoAtcLogradouro.iterator();
				while ( iteratorAtcLogradouro.hasNext() ) {
					
					AtcLogradouro atcLogradouro = (AtcLogradouro) iteratorAtcLogradouro.next();
					
					//1.1.1
					LogradouroAdmin logradouroAdmin = this.inserirLogradouroAdmin(atcLogradouro);
					
					//1.1.2
					atcLogradouro.setAtcLgrDataHoraImportacao(new Date());
					repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcLogradouro);
					
					Integer idMunicipio = new Integer(0);
					if ( atcLogradouro.getAtcMunId() != null ) {
						idMunicipio = atcLogradouro.getAtcMunId();
					}
					//1.1.3
					Integer idLogradouro = this.inserirLogradouro(logradouroAdmin, idMunicipio);
					
					//1.1.4
					this.inserirLogradouroGsanAdmin(logradouroAdmin.getId() , idLogradouro );
					
					//1.1.5
					logradouroAdmin.setDataAtualizacaoGsan(new Date());
					repositorioUtil.atualizar(logradouroAdmin);
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0001]-Inserir Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirLogradouro(LogradouroAdmin  logradouroAdmin, Integer idMunicipio) throws ControladorException{
		
		Integer retorno = null;
		try {
			
			Logradouro logradouro = new Logradouro();
			
			//LOGR_NMLOGRADOURO
			logradouro.setNome(logradouroAdmin.getNomelogradouro());
			
			//LGTP_ID
			if ( logradouroAdmin.getLogradouroTipo() != null && logradouroAdmin.getLogradouroTipo().getId() != null 
					&& !logradouroAdmin.getLogradouroTipo().getId().equals(Integer.valueOf(0)) ) {
				
				LogradouroTipo logradouroTipo = new LogradouroTipo();
				logradouroTipo.setId(logradouroAdmin.getLogradouroTipo().getId());
				logradouro.setLogradouroTipo(logradouroTipo);
			}
			
			//LGTT_ID
			if ( logradouroAdmin.getLogradouroTitulo() != null && logradouroAdmin.getLogradouroTitulo().getId() != null 
					&& !logradouroAdmin.getLogradouroTitulo().getId().equals(Integer.valueOf(0)) ) {
				
				LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
				logradouroTitulo.setId(logradouroAdmin.getLogradouroTitulo().getId());
				logradouro.setLogradouroTitulo(logradouroTitulo);
			}
			
			//MUNI_ID
			Municipio municipio = new Municipio();
			municipio.setId(idMunicipio);
			logradouro.setMunicipio(municipio);	
			
			//LOGR_ICUSO
			logradouro.setIndicadorUso(ConstantesSistema.SIM);
			
			//LOGR_TMULTIMAALTERACAO
			logradouro.setUltimaAlteracao(new Date());
			
			logradouro.setIndicadorEnvioAtualizacaoCadastral(ConstantesSistema.SIM);
			
			retorno = (Integer) repositorioUtil.inserir(logradouro);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		return retorno;
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 *  [SB0003]-Inserir Logradouro  
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public LogradouroAdmin inserirLogradouroAdmin(AtcLogradouro atcLogradouro ) throws ControladorException{
		
		LogradouroAdmin logradouroAdmin = new LogradouroAdmin();
		try {
			
			//LOAD_ID
			logradouroAdmin.setId(atcLogradouro.getAtcLgrId());
			
			//LGTT_ID
			if ( atcLogradouro.getAtcLttId() != null && !atcLogradouro.getAtcLttId().equals(Integer.valueOf(0) ) ) {
				LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
				logradouroTitulo.setId(atcLogradouro.getAtcLttId());
				logradouroAdmin.setLogradouroTitulo(logradouroTitulo);
			}
			
			//LGTP_ID
			if ( atcLogradouro.getAtcLtpId() != null && !atcLogradouro.getAtcLtpId().equals(Integer.valueOf(0)) ) {
				LogradouroTipo logradouroTipo = new LogradouroTipo();
				logradouroTipo.setId(atcLogradouro.getAtcLtpId());
				logradouroAdmin.setLogradouroTipo(logradouroTipo);
			}
			
			//LOAD_NMLOGRADOURO
			logradouroAdmin.setNomelogradouro(atcLogradouro.getAtcLgrDescricao());
			
			//LOAD_ICATUALIZACAO
			if ( atcLogradouro.getAtcLgrAtualizado() != null && atcLogradouro.getAtcLgrAtualizado().equals(String.valueOf(ConstantesSistema.SIM)) ) {
				logradouroAdmin.setIndicadorAtualizado(ConstantesSistema.SIM);
			} else {
				logradouroAdmin.setIndicadorAtualizado(ConstantesSistema.NAO);
			}
			
			logradouroAdmin.setUltimaAlteracao(new Date());
			
			if ( repositorioCadastro.existeLogradouroAdmin(logradouroAdmin.getId()) ) {
				repositorioUtil.atualizar(logradouroAdmin);
			} else {
				repositorioUtil.inserir(logradouroAdmin);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		return logradouroAdmin;
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0002]-Atualizar Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 */
	public void atualizarBairro() throws ControladorException {
		
		try {
			Collection<AtcBairro> colecaoAtcBairro = repositorioIntegracao.pesquisarAtcBairro(AtcBairro.ATUALIZADO);
			
			if ( colecaoAtcBairro != null && !colecaoAtcBairro.isEmpty() ) {
				
				Iterator<AtcBairro> iteratorAtcBairro = colecaoAtcBairro.iterator();
				while ( iteratorAtcBairro.hasNext() ) {
					
					AtcBairro atcBairro = (AtcBairro) iteratorAtcBairro.next();
					
					//1.1.1
					BairroAdmin bairroAdmin = this.inserirBairroAdmin(atcBairro);
					
//					//1.1.2
//					repositorioCadastro.atualizarBairro(atcBairro.getAtcBaiId(), bairroAdmin.getNomeBairro());
//					
//					//1.1.3
//					bairroAdmin.setDataAtualizacaoGsan(new Date());
//					repositorioUtil.atualizar(bairroAdmin);
					
					//1.1.2
					atcBairro.setAtcBaiDataHoraImportacao(new Date());
					repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcBairro);
				}
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0001]-Inserir Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 */
	public void inserirBairro() throws ControladorException {
		
		try {
			Collection<AtcBairro> colecaoAtcBairro = repositorioIntegracao.pesquisarAtcBairro(AtcBairro.INSERIDO);
			
			if ( colecaoAtcBairro != null && !colecaoAtcBairro.isEmpty() ) {
	
				Iterator<AtcBairro> iteratorAtcBairro = colecaoAtcBairro.iterator();
				while ( iteratorAtcBairro.hasNext() ) {
					
					AtcBairro atcBairro = (AtcBairro) iteratorAtcBairro.next();
					
					//1.1.1
					BairroAdmin bairroAdmin = this.inserirBairroAdmin(atcBairro);
					
					//1.1.2
					atcBairro.setAtcBaiDataHoraImportacao(new Date());
					repositorioIntegracao.atualizarIntegracaoAtualizacaoCadastral(atcBairro);
					
					Integer idMunicipio = new Integer(0);
					if ( atcBairro.getAtcMunId() != null ) {
						idMunicipio = atcBairro.getAtcMunId();
					}
					//1.1.3
					Integer idBairro = this.inserirBairro(bairroAdmin, idMunicipio);
					
					//1.1.4
					this.inserirBairroGsanAdmin(bairroAdmin.getId() , idBairro );
					
					//1.1.5
					bairroAdmin.setDataAtualizacaoGsan(new Date());
					repositorioUtil.atualizar(bairroAdmin);
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0001]-Inserir Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BairroAdmin inserirBairroAdmin(AtcBairro atcBairro ) throws ControladorException{
		
		BairroAdmin bairroAdmin = new BairroAdmin();
		try {
			
			//BAAD_ID
			bairroAdmin.setId(atcBairro.getAtcBaiId());
			
			//BAAD_NMBAIRRO
			bairroAdmin.setNomeBairro(atcBairro.getAtcBaiNome());
			
			//BAAD_ICATUALIZACAO
			if ( atcBairro.getAtcBaiAtualizado() != null && atcBairro.getAtcBaiAtualizado().equals("1") ) {
				bairroAdmin.setIndicadorAtualizado(ConstantesSistema.SIM);
			} else {
				bairroAdmin.setIndicadorAtualizado(ConstantesSistema.NAO);
			}
			
			bairroAdmin.setUltimaAlteracao(new Date());
			
			
			if ( repositorioCadastro.existeBairroAdmin(bairroAdmin.getId()) ) {
				repositorioUtil.atualizar(bairroAdmin);
			} else {
				repositorioUtil.inserir(bairroAdmin);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		return bairroAdmin;
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0001]-Inserir Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirBairro(BairroAdmin bairroAdmin, Integer idMunicipio) throws ControladorException{
		
		Integer retorno = null;
		try {
			Bairro bairro = new Bairro();
	
			Integer codigoBairro = repositorioCadastro.pesquisarMaiorCodigoBairro(idMunicipio);
			codigoBairro ++;
			bairro.setCodigo(codigoBairro);

			bairro.setNome(bairroAdmin.getNomeBairro());
			
			bairro.setIndicadorUso(ConstantesSistema.SIM);
			
			bairro.setUltimaAlteracao(new Date());
			
			Municipio municipio = new Municipio();
			municipio.setId(idMunicipio);
			bairro.setMunicipio(municipio);
			
			bairro.setIndicadorEnvioAtualizacaoCadastral(ConstantesSistema.SIM);
			
			retorno = (Integer) repositorioUtil.inserir(bairro);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		return retorno;
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0001]-Inserir Bairro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirBairroGsanAdmin(Integer idBairroAdmin, Integer idBairro ) throws ControladorException{
		
		Integer retorno = null;
		try {
			
			BairroGsanAdmin bairroGsanAdmin = new BairroGsanAdmin();
			
			BairroGsanAdminPK comp_id = new BairroGsanAdminPK(idBairro, idBairroAdmin);
			
			bairroGsanAdmin.setComp_id(comp_id);
			
			bairroGsanAdmin.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(bairroGsanAdmin);
				
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		return retorno;
	}
	
	/**
	 * [UC1338] Atualizar Tabelas B·sicas RecepÁ„o de Dados ADMIN
	 * [SB0003]-Inserir Logradouro 
	 * @author Arthur Carvalho
	 * @since 10/05/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirLogradouroGsanAdmin(Integer idLogradouroAdmin, Integer idLogradouro ) throws ControladorException{
		
		Integer retorno = null;
		try {
			
			LogradouroGsanAdmin logradouroGsanAdmin = new LogradouroGsanAdmin();
			
			LogradouroGsanAdminPK comp_id = new LogradouroGsanAdminPK(idLogradouro, idLogradouroAdmin);
			
			logradouroGsanAdmin.setComp_id(comp_id);
			
			logradouroGsanAdmin.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(logradouroGsanAdmin);
				
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
		}
		return retorno;
	}
	
	/**
	 * [UC1322] Rotina de Realizar Inclus„o de Dados Admin
	 * 
	 * @author Arthur Carvalho
	 * @since 16/04/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDadosAdmin(Integer idParametro) throws ControladorException {
		

		try {

			Collection<ImovelAtualizacaoCadastral> colecaoImovelAtualizacaoCadastral = repositorioCadastro.pesquisarColecaoImovelAtualizacaoCadastral(idParametro);
		
			//[FS0001 - Verificar existÍncia de registros para atualizaÁ„o].
			if ( colecaoImovelAtualizacaoCadastral != null && !colecaoImovelAtualizacaoCadastral.isEmpty() ) {
			
				Iterator<ImovelAtualizacaoCadastral> iteratorImovelAtualizacaoCadastral = colecaoImovelAtualizacaoCadastral.iterator();
				while ( iteratorImovelAtualizacaoCadastral.hasNext() ) {
					ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral) iteratorImovelAtualizacaoCadastral.next();
					
					//Verifica se ja existe registro gerado para esse imovel - Caso existe remove os registros
					this.removerDadosImovelAdmin(imovelAtualizacaoCadastral.getImovel());
					
					//[SB0001-Inserir Dados do ImÛvel]. 
					this.inserirAtcImovel(imovelAtualizacaoCadastral); 
					
					//2.2.	Seleciona os Clientes 
					Collection<ClienteAtualizacaoCadastral> colecaoClienteAtualizacaoCadastral = repositorioCadastro.
							pesquisarClienteAtualizacaoCadastral(imovelAtualizacaoCadastral.getId());
					
					if ( colecaoClienteAtualizacaoCadastral != null && !colecaoClienteAtualizacaoCadastral.isEmpty() ) {
					
						Iterator<ClienteAtualizacaoCadastral> iteratorClienteAtualizacaoCadastral = colecaoClienteAtualizacaoCadastral.iterator();
						while( iteratorClienteAtualizacaoCadastral.hasNext() ) {
							
							ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = (ClienteAtualizacaoCadastral) iteratorClienteAtualizacaoCadastral.next();
							
							//[SB0002] - Inserir Dados do Cliente
							this.inserirAtcCliente(clienteAtualizacaoCadastral, imovelAtualizacaoCadastral.getImovel());
								
							Collection colecaoClienteFoneAtualizacaoCadastral = getControladorCliente().
									obterDadosClienteFoneAtualizacaoCadastral(clienteAtualizacaoCadastral.getId());
								
							//1.2.	Inserir na tabela CLIENTE_FONE_ATLZ_CAD
							if ( colecaoClienteFoneAtualizacaoCadastral != null && !colecaoClienteFoneAtualizacaoCadastral.isEmpty() ) {
								
								Iterator iteratorClienteFoneAtualizacaoCadastral = colecaoClienteFoneAtualizacaoCadastral.iterator();
								while( iteratorClienteFoneAtualizacaoCadastral.hasNext() ) {
									
									ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = (ClienteFoneAtualizacaoCadastral) iteratorClienteFoneAtualizacaoCadastral.next();
									
									this.inserirAtcClienteTelefone(clienteAtualizacaoCadastral.getIdCliente(), clienteFoneAtualizacaoCadastral);
								}
							}
						}
					} 
					
					//2.3.	Seleciona as Subcategorias 
					Collection<ImovelSubcategoriaAtualizacaoCadastral> colecaoImovelSubcategoriaAtualizacaoCadastral = repositorioCadastro.
							pesquisarImovelSubCategoriaAtualizacaoCadastral(imovelAtualizacaoCadastral.getId());
							
					if ( colecaoImovelSubcategoriaAtualizacaoCadastral != null && !colecaoImovelSubcategoriaAtualizacaoCadastral.isEmpty() ) {
						
						Iterator<ImovelSubcategoriaAtualizacaoCadastral> iteratorImovelSubcategoriaAtualizacaoCadastral = colecaoImovelSubcategoriaAtualizacaoCadastral.iterator();
						
						while ( iteratorImovelSubcategoriaAtualizacaoCadastral.hasNext() ) {
							
							ImovelSubcategoriaAtualizacaoCadastral imovelSubCategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral) 
									iteratorImovelSubcategoriaAtualizacaoCadastral.next();
							
							//[SB0003] - Inserir Dados da Subcategoria
							this.inserirAtcSubcategoriaImovel(imovelSubCategoriaAtualizacaoCadastral);
						}
					}
					
					//2.4.	Seleciona os HidrÙmetros 
					Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral> colecaoHidrometro = repositorioCadastro.
							pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastral(imovelAtualizacaoCadastral.getId(), null);
					
					if ( colecaoHidrometro != null && !colecaoHidrometro.isEmpty() ){
						
						Iterator<HidrometroInstalacaoHistoricoAtualizacaoCadastral> iteratorHidrometro = colecaoHidrometro.iterator();
						while( iteratorHidrometro.hasNext() ) {
							
							HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometro = (HidrometroInstalacaoHistoricoAtualizacaoCadastral) iteratorHidrometro.next();
							
							//[SB0004] - Inserir Dados da InstalaÁ„o de HidrÙmetro
							this.inserirAtcHidrometroInstalacao(hidrometro, imovelAtualizacaoCadastral.getImovel());
						}
					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * Enviar Mensagem SMS para celular aparti de um webservice 
	 * Metodo que envia mensagem sms para um celular por meio de um web service. 
	 * @author Carlos Chaves
	 * @since 04/12/2012
	 * @param String Mensagem - Deve conter no maximo 160 caracteres
	 * @param Long nroDestino - Telefone de destino Com 10 caracters.
	 * @return String
	 */
	public String enviarSmsWebService(String mensagem,Long nroDestino) 
			throws ControladorException {
		
		if(nroDestino.toString().length() != WebServiceSms.QUANTIDADE_DIGITOS_TELEFONE){
			throw new ControladorException("atencao.celuar_invalido");
		}
		
		if(mensagem.length()>WebServiceSms.LIMITE_CARACTER_SMS){
			throw new ControladorException("atencao.sms_excede_limite");
		}
		
		WebServiceSms sms = new WebServiceSms();
		
        String retornoWebService = sms.enviarSmsWebService(mensagem, nroDestino);
	    
        if(retornoWebService.startsWith("OK")){
        	return "OK";
        }else{
        	return retornoWebService;
        }
	}
	
	/**
	 * Autor : Jonathan Marcos
	 * Data : 16/10/2013
	 * [UC1568] Receber Informacoes Abastecimento Carro Pipa Via Webservice
	 * [Observacoes]
	 * 		1 - Pamaretros :
	 * 			1.1 - Integer sequencial 
	 * 			1.2 - String descricaoPlaca
	 * 			1.3 - Integer idImovel
	 * 			1.4 - Date dataAbastecimento
	 * 			1.5 - Integer volumeAbastecimento
	 * 			1.6 - Short indicadorCobranca
	 * 			1.7 - Short indicadorAbastecimentoAvulso
	 * 		2 - return : Integer id gerado
	 */
	public Integer inserirCarroPipaAbastecimento(Integer sequencial,String descricaoPlaca,Integer idImovel,
			Date dataAbastecimento,Integer volumeAbastecimento,Short indicadorCobranca,
			Short indicadorAbastecimentoAvulso)
		throws ControladorException{
		
		Integer idCarroPipaAbastecimentoGerado = null;
		BigDecimal valorDebito = null;
		
		CarroPipaAbastecimento carroPipaAbastecimento = new CarroPipaAbastecimento();
		Imovel imovel = new Imovel();
		
		carroPipaAbastecimento.setId(sequencial);
		carroPipaAbastecimento.setDescricaoPlaca(descricaoPlaca);
		imovel.setId(idImovel);
		carroPipaAbastecimento.setImovel(imovel);
		carroPipaAbastecimento.setDataAbastecimento(dataAbastecimento);
		BigDecimal volumeAbastecimentoM3 = new BigDecimal(volumeAbastecimento).divide(new BigDecimal("1000"));
		volumeAbastecimentoM3.setScale(3, BigDecimal.ROUND_HALF_UP);
		carroPipaAbastecimento.setVolumeAbastecimento(volumeAbastecimentoM3);
		carroPipaAbastecimento.setIndicadorCobranca(indicadorCobranca);
		carroPipaAbastecimento.setIndicadorAbastecimentoAvulso(indicadorAbastecimentoAvulso);
		carroPipaAbastecimento.setUltimaAlteracao(new Date());
		
		if(indicadorCobranca.equals(ConstantesSistema.NAO)){
			carroPipaAbastecimento.setAnoMesReferenciaFaturamento(Util.getAnoMesComoInteger(dataAbastecimento));
		}else{
			carroPipaAbastecimento.setAnoMesReferenciaFaturamento(null);
		}
		
		//Obter Principal Categoria do Imovel
		Categoria categoria = Fachada.getInstancia()
				.obterPrincipalCategoriaImovel(idImovel);
		
		try {
			//Publico
			if(categoria.getId().compareTo(Categoria.PUBLICO)==0){
				
				Integer idConsumoTarifaVigencia;
				
					idConsumoTarifaVigencia = this.repositorioFaturamento
							.obterConsumoTarifaVigencia(ConsumoTarifa.TARIFA_CHAFARIZ);
				
						
				BigDecimal valorConsumoTarifa = this.repositorioFaturamento
						.obterValorConsumoTarifa(idConsumoTarifaVigencia, Categoria.PUBLICO);
				
				valorDebito = valorConsumoTarifa.multiply(volumeAbastecimentoM3);
				valorDebito.setScale(2, BigDecimal.ROUND_HALF_UP);
			}else{
			
				Integer idConsumoTarifaVigencia = this.repositorioFaturamento
						.obterConsumoTarifaVigencia(ConsumoTarifa.CONSUMO_NORMAL);
				
				BigDecimal valorConsumoTarifa = this.repositorioFaturamento
						.obterValorConsumoTarifa(idConsumoTarifaVigencia, Categoria.INDUSTRIAL);
				
				valorDebito = valorConsumoTarifa.multiply(volumeAbastecimentoM3);
				valorDebito.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	
		carroPipaAbastecimento.setValorAbastecimento(valorDebito);
		
		idCarroPipaAbastecimentoGerado = (Integer) this.getControladorUtil().inserir(carroPipaAbastecimento);
		
		return idCarroPipaAbastecimentoGerado;
	}
	
	/**
	 * Autor : Jonathan Marcos
	 * Data : 16/10/2013
	 * [UC1567] Consultar Debitos Imovel Webservice
	 * [Observacoes]
	 * 		1 - Pamaretros : 
	 * 			1.1 - String tipoRequisicao
	 * 			1.2 - String idCarroPipaRetornoOcorrenciaGerado
	 * 		2 - return : String numero protocolo gerado
	 */
	public String gerarNumeroProtocoloCarroPipaRetornoOcorrencia(Short tipoRequisicao,
			Integer sequencialGerado) throws ControladorException{
		
		String anoOcorrente = "";
		BigDecimal numeroProtocolo = new BigDecimal("0");
		
		Date data = new Date();
		anoOcorrente = String.valueOf(Util.getAno(data));
		
		BigDecimal numeroProtocoloParcial = new BigDecimal(anoOcorrente+tipoRequisicao.toString()+"000000000");
		
		BigDecimal retorno = numeroProtocolo.add(numeroProtocoloParcial.add(BigDecimal.valueOf(sequencialGerado)));
		
		return String.valueOf(retorno);
	}
	
	/**
	 * Autor : Jonathan Marcos
	 * Data : 16/10/2013
	 * [UC1567] Consultar Debitos Imovel Webservice
	 * [Observacoes]
	 * 		1 - Pamaretros : 
	 * 			1.1 - CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia
	 * 		2 - return : String numero do Protocolo
	 */
	public String inserirCarroPipaRetornoOcorrencia(CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia,
			boolean gerarNumeroProtocolo)
		throws ControladorException{
		
		String numeroProtocoloGerado = null;
		
		Integer sequencialGerado = null;
	
		sequencialGerado = (Integer) this.getControladorUtil().inserir(carroPipaRetornoOcorrencia);
		
		if(gerarNumeroProtocolo==true){
			numeroProtocoloGerado = this.gerarNumeroProtocoloCarroPipaRetornoOcorrencia(
				carroPipaRetornoOcorrencia.getCodigoRequisicao(),
				sequencialGerado);
			
			carroPipaRetornoOcorrencia.setId(sequencialGerado);
			
			carroPipaRetornoOcorrencia.setNumeroProtocoloConsulta(numeroProtocoloGerado);
			
			Fachada.getInstancia().atualizar(carroPipaRetornoOcorrencia);
			
		}
		return numeroProtocoloGerado;
	}
	
	
	/**
	 * [UC1565] Gerar Relatorio Pagamentos Abastecimento Carro Pipa
	 * 
	 * @author Diogo Luiz
	 * @Date 15/10/2013
	 * 
	 */
	public List pesquisarRelatorioCarroPipa(Integer idFaturamentoGrupo, Integer mesAnoReferencia,
			Integer idGerenciaRegional, Integer idUnidadeNegocio) throws ControladorException{
		
		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		
		GerenciaRegional gerenciaRegional = new GerenciaRegional();
		
		List relatorioBeans = new ArrayList();				
		
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.ID, idGerenciaRegional));
			Collection colecaoGerenciaRegional = getControladorUtil().pesquisar(filtroGerenciaRegional, 
				GerenciaRegional.class.getName());		
		
			if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
				gerenciaRegional = (GerenciaRegional) 
						Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
			}
		}
		
		RelatorioPagamentosAbastecimentosCarrosPipaBean relatorioBean = null;
		
		Collection colecaoCarroPipa = null; 
		
		try {
			if(idFaturamentoGrupo != null){
				//[SB0002]-Gerar Relatorio				
				colecaoCarroPipa = repositorioIntegracao.pesquisarRelatorioCarroPipaGRupoFaturamento(idFaturamentoGrupo, 
					mesAnoReferencia);
			
			}else if(idGerenciaRegional != null || idUnidadeNegocio != null){	
				//[SB0002]-Gerar Relatorio
				colecaoCarroPipa = repositorioIntegracao.pesquisarRelatorioCarroPipaGerenciaUnidade(mesAnoReferencia, 
					idGerenciaRegional, idUnidadeNegocio);			
			}else{		
				//[SB0002]-Gerar Relatorio
				colecaoCarroPipa = repositorioIntegracao.pesquisarRelatorioCarroPipa(mesAnoReferencia);		
			}
			
			if(!Util.isVazioOrNulo(colecaoCarroPipa)){
				Iterator it = colecaoCarroPipa.iterator();
				Object[] array = null;
				
				while(it.hasNext()){
					relatorioBean = new RelatorioPagamentosAbastecimentosCarrosPipaBean();
					array = null;
					array = (Object[]) it.next();							
					
					if(idFaturamentoGrupo != null || (idGerenciaRegional != null 
							&& idGerenciaRegional.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO)
							|| idUnidadeNegocio != null && idUnidadeNegocio.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
						//2.7.1. Registros Enviados 
						relatorioBean.setQtdeEnviados((Integer) array[0]);
						
						//2.7.2. Registros com Contrato
						relatorioBean.setQtdeComContrato((Integer) array[1]);
						
						//2.7.3. Registros sem Contrato
						relatorioBean.setQtdeSemContrato((Integer) array[2]);
						
						//2.7.4. Registros Faturaveis
						relatorioBean.setQtdeFaturaveis((Integer) array[3]);
						
						//2.7.5. Registros Nao Faturaveis
						relatorioBean.setQtdeNaoFaturaveis((Integer) array[4]);						
						
						if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
							FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
							filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
								FiltroGerenciaRegional.ID, idGerenciaRegional));
							Collection colecaoGerenciaRegional = getControladorUtil().pesquisar(filtroGerenciaRegional, 
								GerenciaRegional.class.getName());		
						
							if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
								gerenciaRegional = (GerenciaRegional) 
										Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
								
								relatorioBean.setNomeGerencia(gerenciaRegional.getNome());
							}
						}else{
						
							relatorioBean.setNomeGerencia("");								
						}
						
						//2.8.1. Total de agua cobrada via debito a CobrarÄù: Valor
						BigDecimal totalDebitoACobrar = (BigDecimal) array[5];
						
						totalDebitoACobrar = totalDebitoACobrar.add((BigDecimal) array[12]);
						
						
						relatorioBean.setQtdTotalDebitoCobrarValor(totalDebitoACobrar);
						
						//2.8.1. Total de agua cobrada via debito a CobrarÄù: Volume
						relatorioBean.setQtdTotalDebitoCobrarVolume((BigDecimal) array[6]);				
						
						//2.8.2. Total de agua cobrada via ÄúGuia de PagamentoÄù: Valor	
						
						BigDecimal totalGuiaPagamentoValor = (BigDecimal) array[11];
						
						if(totalGuiaPagamentoValor != null && !totalGuiaPagamentoValor.equals("")){
							relatorioBean.setQtdTotalGuiaPagamentoValor(totalGuiaPagamentoValor);	
						}else{
							relatorioBean.setQtdTotalGuiaPagamentoValor(new BigDecimal(0));
						}							
						
						//2.8.3. Total de agua cobrada pelo GSAN: Volume
						relatorioBean.setQtdTotalCobradaGsanVolume((BigDecimal)array[6]);				
						
						//2.8.3. Total de agua cobrada pelo GSAN: Valor
						if (relatorioBean.getQtdTotalDebitoCobrarValor() != null && !relatorioBean.getQtdTotalDebitoCobrarValor().equals("")){
							
							relatorioBean.setQtdTotalCobradaGsanValor(relatorioBean.getQtdTotalGuiaPagamentoValor().add(
								relatorioBean.getQtdTotalDebitoCobrarValor()));		
						}
						
						//2.8.4. Total de agua faturavel: Volume
						relatorioBean.setQtdTotalAguaFaturaveisVolume((BigDecimal) array[6]);				
						
						//2.8.4. Total de agua faturavel: Valor
						relatorioBean.setQtdTotalAguaFaturaveisValor((BigDecimal) array[5]);				
						
						//2.8.5. Total de agua nao faturavel: Volume	
						BigDecimal totalAguaNaoFaturaveisVolume = (BigDecimal) array[7];
						
						if(totalAguaNaoFaturaveisVolume != null && !totalAguaNaoFaturaveisVolume.equals("")){
							relatorioBean.setQtdTotalAguaNaoFaturaveisVolume(totalAguaNaoFaturaveisVolume);	
						}else{
							relatorioBean.setQtdTotalAguaNaoFaturaveisVolume(new BigDecimal(0));	
						}
									
						
						//2.8.5. Total de agua nao faturavel: Valor	
						
						BigDecimal totalAguaNaoFaturaveisValor = (BigDecimal) array[8];
						
						if(totalAguaNaoFaturaveisValor != null && !totalAguaNaoFaturaveisValor.equals("")){
						
							relatorioBean.setQtdTotalAguaNaoFaturaveisValor(totalAguaNaoFaturaveisValor);				
						}else{
							
							totalAguaNaoFaturaveisValor = new BigDecimal(0);
							
							relatorioBean.setQtdTotalAguaNaoFaturaveisValor(totalAguaNaoFaturaveisValor);
						}
						
						//2.8.6. Total de agua fornecida pelo GPIPA: Volume
						BigDecimal totalAguaFornecidaGPipaVolume = (BigDecimal) array[10];
						
						if(totalAguaFornecidaGPipaVolume == null){
							totalAguaFornecidaGPipaVolume = new BigDecimal("0");
						}
						
						relatorioBean.setQtdTotalAguaGpipaVolume(totalAguaFornecidaGPipaVolume);
						
						//2.8.6. Total de agua fornecida pelo GPIPA: Valor
						BigDecimal totalAguaFornecidaGPipaValor = (BigDecimal) array[9];
						
						if(totalAguaFornecidaGPipaValor == null){
							totalAguaFornecidaGPipaValor = new BigDecimal(0);
						}
						
						relatorioBean.setQtdTotalAguaGpipaValor(totalAguaFornecidaGPipaValor);					
								
						BigDecimal diferencaTotalGsanGPipaVolume = new BigDecimal(0);
						//2.8.7. Diferenca entre os totais do GSAN e do GPIPA: Volume
						if (relatorioBean.getQtdTotalCobradaGsanVolume() != null && 
								relatorioBean.getQtdTotalAguaNaoFaturaveisVolume() != null){
							
							diferencaTotalGsanGPipaVolume = totalAguaFornecidaGPipaVolume.subtract(
								relatorioBean.getQtdTotalCobradaGsanVolume());
							
							diferencaTotalGsanGPipaVolume = diferencaTotalGsanGPipaVolume.subtract(
								relatorioBean.getQtdTotalAguaNaoFaturaveisVolume());							
						}
						
						
						relatorioBean.setDiferencaQtdGsanGpipaVolume(diferencaTotalGsanGPipaVolume);				
						
						//2.8.7. Diferenca entre os totais do GSAN e do GPIPA: Valor
						BigDecimal diferencaTotalGsanGPipaValor = new BigDecimal(0);
						
						if (relatorioBean.getQtdTotalCobradaGsanValor() != null && 
								relatorioBean.getQtdTotalAguaNaoFaturaveisValor() != null){
							
							diferencaTotalGsanGPipaValor = totalAguaFornecidaGPipaValor.subtract(
								relatorioBean.getQtdTotalCobradaGsanValor());
							
							diferencaTotalGsanGPipaValor = diferencaTotalGsanGPipaValor.subtract(
								relatorioBean.getQtdTotalAguaNaoFaturaveisValor());
						}
						 
						
						BigDecimal resultado = new BigDecimal(0);
						
						if(diferencaTotalGsanGPipaValor.compareTo(new BigDecimal(0)) < 0){		
							
							BigDecimal valor = diferencaTotalGsanGPipaValor;					
							resultado = valor.multiply(new BigDecimal(-1)); 
							
							relatorioBean.setDiferencaQtdGsanGpipaValor(resultado);	
						} else {
							
							relatorioBean.setDiferencaQtdGsanGpipaValor(diferencaTotalGsanGPipaValor);	
						}
						
						
					
					
					}else{
					
						//2.7.1. Registros Enviados 
					relatorioBean.setQtdeEnviados((Integer) array[0]);
					
					//2.7.2. Registros com Contrato
					relatorioBean.setQtdeComContrato((Integer) array[1]);
					
					//2.7.3. Registros sem Contrato
					relatorioBean.setQtdeSemContrato((Integer) array[2]);
					
					//2.7.4. Registros Faturaveis
					relatorioBean.setQtdeFaturaveis((Integer) array[3]);
					
					//2.7.5. Registros Nao Faturaveis
					relatorioBean.setQtdeNaoFaturaveis((Integer) array[4]);	
					
					//Id da Gerencia
					relatorioBean.setGerenciaRegional((Integer) array[5]);
					
					//Nome da Gerencia
					relatorioBean.setNomeGerencia((String) array[6]);				
					
					//2.8.1. Total de agua cobrada via debito a CobrarÄù: Valor
					
					BigDecimal totalDebitoACobrar = (BigDecimal) array[7];
					
					totalDebitoACobrar = totalDebitoACobrar.add((BigDecimal) array[14]);
					
					
					relatorioBean.setQtdTotalDebitoCobrarValor(totalDebitoACobrar);
					
					//2.8.1. Total de agua cobrada via debito a Cobrar: Volume
					relatorioBean.setQtdTotalDebitoCobrarVolume((BigDecimal)array[8]);				
					
					//2.8.2. Total de agua cobrada via ÄúGuia de PagamentoÄù: Valor	
					
					BigDecimal totalGuiaPagamentoValor = (BigDecimal) array[11];
					
					if(totalGuiaPagamentoValor != null && !totalGuiaPagamentoValor.equals("")){
						relatorioBean.setQtdTotalGuiaPagamentoValor(totalGuiaPagamentoValor);	
					}else{
						relatorioBean.setQtdTotalGuiaPagamentoValor(new BigDecimal(0));
					}							
					
					//2.8.3. Total de agua cobrada pelo GSAN: Volume
					relatorioBean.setQtdTotalCobradaGsanVolume((BigDecimal) array[8]);				
					
					//2.8.3. Total de agua cobrada pelo GSAN: Valor
					relatorioBean.setQtdTotalCobradaGsanValor(relatorioBean.getQtdTotalGuiaPagamentoValor().add(
						relatorioBean.getQtdTotalDebitoCobrarValor()));				
					
					//2.8.4. Total de agua faturavel: Volume
					relatorioBean.setQtdTotalAguaFaturaveisVolume((BigDecimal) array[8]);				
					
					//2.8.4. Total de agua faturavel: Valor
					relatorioBean.setQtdTotalAguaFaturaveisValor((BigDecimal) array[7]);				
					
					//2.8.5. Total de agua nao faturavel: Volume	
					BigDecimal totalAguaNaoFaturaveisVolume = (BigDecimal) array[9];
					
					if(totalAguaNaoFaturaveisVolume != null && !totalAguaNaoFaturaveisVolume.equals("")){
						relatorioBean.setQtdTotalAguaNaoFaturaveisVolume(totalAguaNaoFaturaveisVolume);	
					}else{
						relatorioBean.setQtdTotalAguaNaoFaturaveisVolume(new BigDecimal(0));	
					}
								
					
					//2.8.5. Total de agua nao faturavel: Valor	
					
					BigDecimal totalAguaNaoFaturaveisValor = (BigDecimal) array[10];
					
					if(totalAguaNaoFaturaveisValor != null && !totalAguaNaoFaturaveisValor.equals("")){
					
						relatorioBean.setQtdTotalAguaNaoFaturaveisValor(totalAguaNaoFaturaveisValor);				
					}else{
						totalAguaNaoFaturaveisValor = new BigDecimal(0);
						relatorioBean.setQtdTotalAguaNaoFaturaveisValor(totalAguaNaoFaturaveisValor);
					}
					
					//2.8.6. Total de agua fornecida pelo GPIPA: Volume
					BigDecimal totalAguaFornecidaGPipaVolume = (BigDecimal) array[13];
					
					if(totalAguaFornecidaGPipaVolume == null){
						totalAguaFornecidaGPipaVolume = new BigDecimal("0");
					}
					
					relatorioBean.setQtdTotalAguaGpipaVolume(totalAguaFornecidaGPipaVolume);
					
					//2.8.6. Total de agua fornecida pelo GPIPA: Valor
					BigDecimal totalAguaFornecidaGPipaValor = (BigDecimal) array[12];
					
					if(totalAguaFornecidaGPipaValor == null){
						totalAguaFornecidaGPipaValor = new BigDecimal(0);
					}
					
					relatorioBean.setQtdTotalAguaGpipaValor(totalAguaFornecidaGPipaValor);				
									
					//2.8.7. Diferenca entre os totais do GSAN e do GPIPA: Volume
					BigDecimal diferencaTotalGsanGPipaVolume = relatorioBean.getQtdTotalAguaGpipaVolume().subtract(
							relatorioBean.getQtdTotalCobradaGsanVolume());
					
					diferencaTotalGsanGPipaVolume = diferencaTotalGsanGPipaVolume.subtract(
						relatorioBean.getQtdTotalAguaNaoFaturaveisVolume());					
					
					relatorioBean.setDiferencaQtdGsanGpipaVolume(diferencaTotalGsanGPipaVolume);				
					
					//2.8.7. Diferenca entre os totais do GSAN e do GPIPA: Valor
					BigDecimal diferencaTotalGsanGPipaValor = new BigDecimal(0);
					
					if (relatorioBean.getQtdTotalCobradaGsanValor() != null && 
							relatorioBean.getQtdTotalAguaNaoFaturaveisValor() != null){
						
						diferencaTotalGsanGPipaValor = totalAguaFornecidaGPipaValor.subtract(
							relatorioBean.getQtdTotalCobradaGsanValor());
						
						diferencaTotalGsanGPipaValor = diferencaTotalGsanGPipaValor.subtract(
							relatorioBean.getQtdTotalAguaNaoFaturaveisValor());
					}
					
					BigDecimal resultado = new BigDecimal(0);
					
					if(diferencaTotalGsanGPipaValor.compareTo(new BigDecimal(0)) < 0){		
						
						BigDecimal valor = diferencaTotalGsanGPipaValor;					
						resultado = valor.multiply(new BigDecimal(-1)); 
						
						relatorioBean.setDiferencaQtdGsanGpipaValor(resultado);		
					} else {
						
						relatorioBean.setDiferencaQtdGsanGpipaValor(diferencaTotalGsanGPipaValor);								
						
					}
					
					
				}					
					
//					if(relatorioBean.getQtdTotalGuiaPagamentoValor() == null ||
//							(relatorioBean.getQtdTotalGuiaPagamentoValor().compareTo(new BigDecimal(0)) == 0 &&
//							relatorioBean.getQtdTotalDebitoCobrarValor().compareTo(new BigDecimal(0)) == 0)){
//						//[FE0001] ‚Äì Validar Referencia  
//						throw new ControladorException("atencao.dados_existente");
//					}					
					
					relatorioBeans.add(relatorioBean);									
				}
			}else{
				//[FE0001] ‚Äì Validar Referencia  
				throw new ControladorException("atencao.dados_existente");
			}				
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException(e.getMessage(), e);
		}		
		
		return relatorioBeans;
	}

	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1569] Receber Informacoes Abastecimento Carro-Pipa Via Arquivo Txt
	 * @param buffer
	 * @throws ControladorException
	 */
	public void receberInformacoesAbastecimentoCarroPipaArquivoTxt(BufferedReader buffer,Integer quantidadeLinhas)
			throws ControladorException{
		
		try {
		
			String linha = "";
			String idRetorno = "";
			String descricaoRetorno = "";
			Integer quantidadeLinhasInformadas = Integer.valueOf(buffer.readLine());
			List<CarroPipaRetornoOcorrencia> listaCarroPipaRetornoOcorrencia = 
					new ArrayList<CarroPipaRetornoOcorrencia>();
			CarroPipaRetornoOcorrencia carroPipaRetornoOcorrencia = null;
			Imovel imovel = null;
			CarroPipaRetornoTipo carroPipaRetornoTipo = null;
			
			if(quantidadeLinhasInformadas==quantidadeLinhas){
				while((linha = buffer.readLine()) != null){
					
					String colunasArquivo[] = linha.split("\\;");
					
					CarroPipaAbastecimentoHelper carroPipaAbastecimentoHelper = 
							this.montarHelperCarroPipaAbastecimento(null, colunasArquivo);
					
					if(carroPipaAbastecimentoHelper.getRetornoErro()!=null){
						
						if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_SEQUENCIAL_INVALIDO){
							idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_SEQUENCIAL_INVALIDO);
							descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_SEQUENCIAL_INVALIDO;
						}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO){
							idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO);
							descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_IMOVEL_INVALIDO;
						}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_PLACA_CAMINHAO_INVALIDA){
							idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_PLACA_CAMINHAO_INVALIDA);
							descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_PLACA_CAMINHAO_INVALIDA;
						}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_DATA_HORA_ABASTECIMENTO_INVALIDA){
							idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_DATA_HORA_ABASTECIMENTO_INVALIDA);
							descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_DATA_HORA_ABASTECIMENTO_INVALIDA;
						}else if(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_VOLUME_ABASTECIMENTO_INVALIDO){
							idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_VOLUME_ABASTECIMENTO_INVALIDO);
							descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_VOLUME_ABASTECIMENTO_INVALIDO;
						}else if(carroPipaAbastecimentoHelper.getRetornoErro()==Integer.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_COBRANCA_INVALIDO)){
							idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_COBRANCA_INVALIDO);
							descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_INDICADOR_COBRANCA_INVALIDO;
						}else if(carroPipaAbastecimentoHelper.getRetornoErro()==Integer.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO)){
							idRetorno = String.valueOf(CarroPipaRetornoTipo.ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO);
							descricaoRetorno = CarroPipaRetornoTipo.DESCRICAO_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO;
						}
						
						carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
						carroPipaRetornoOcorrencia.setCodigoRequisicao(ConstantesSistema.NAO);
						
						imovel = new Imovel();
						if(!(carroPipaAbastecimentoHelper.getRetornoErro()==CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO)){
							imovel.setId(carroPipaAbastecimentoHelper.getIdImovel());
						}
						carroPipaRetornoOcorrencia.setImovel(imovel);
						
						carroPipaRetornoTipo = new CarroPipaRetornoTipo();
						carroPipaRetornoTipo.setId(Integer.valueOf(idRetorno));
						carroPipaRetornoTipo.setDescricaoRetorno(descricaoRetorno);
						
						carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
						carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
						carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
						
						this.inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia, false);
						
						listaCarroPipaRetornoOcorrencia.add(carroPipaRetornoOcorrencia);
						
					}else{
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date dataAbastecimento = simpleDateFormat.parse(carroPipaAbastecimentoHelper.getDataHoraAbastecimento());
						
						this.inserirCarroPipaAbastecimento(carroPipaAbastecimentoHelper.getSequencial(),
							carroPipaAbastecimentoHelper.getDescricaoPlaca(), carroPipaAbastecimentoHelper.getIdImovel(), dataAbastecimento, 
							carroPipaAbastecimentoHelper.getVolumeAbastecimento(),carroPipaAbastecimentoHelper.getIndicadorCobranca(),
							carroPipaAbastecimentoHelper.getIndicadorAbastecimentoAvulso());
						
						carroPipaRetornoOcorrencia = new CarroPipaRetornoOcorrencia();
						carroPipaRetornoOcorrencia.setCodigoRequisicao(ConstantesSistema.NAO);
						imovel = new Imovel();
						imovel.setId(carroPipaAbastecimentoHelper.getIdImovel());
						carroPipaRetornoOcorrencia.setImovel(imovel);
						carroPipaRetornoTipo = new CarroPipaRetornoTipo();
						carroPipaRetornoTipo.setId(Integer.valueOf(CarroPipaRetornoTipo.
							ID_CADASTRO_EFETUADO_SUCESSO));
						carroPipaRetornoTipo.setDescricaoRetorno(CarroPipaRetornoTipo.DESCRICAO_CADASTRADO_SUCESSO);
						carroPipaRetornoOcorrencia.setCarroPipaRetornoTipo(carroPipaRetornoTipo);
						carroPipaRetornoOcorrencia.setDataOcorrencia(new Date());
						carroPipaRetornoOcorrencia.setUltimaAlteracao(new Date());
						
						this.inserirCarroPipaRetornoOcorrencia(carroPipaRetornoOcorrencia, false);
						
						listaCarroPipaRetornoOcorrencia.add(carroPipaRetornoOcorrencia);
					}
				}
				
				if(listaCarroPipaRetornoOcorrencia.size()!=0){
					byte [] arquivoGerado = this.gerarArquivoTxtRetornoCarroPipaAbastecimento(listaCarroPipaRetornoOcorrencia,
						quantidadeLinhasInformadas);
					
					this.enviarEmailCarroPipaAbastecimentoArquivoTxt(arquivoGerado);
				}
			}else{
				throw new ActionServletException("atencao.quantidade_linhas_arquivo_invalidas");
			}
		} catch (Exception e) {
			throw new ControladorException(e.getMessage(), e);
		}
		
	}
	
	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1568] Receber Informacoes Abastecimento Carro Pipa Via Webservice
	 * @param request
	 * @param colunasArquivo
	 * @return
	 * @throws ControladorException
	 */
	public CarroPipaAbastecimentoHelper montarHelperCarroPipaAbastecimento(HttpServletRequest request,
			String colunasArquivo[]) throws ControladorException{
		
		CarroPipaAbastecimentoHelper carroPipaAbastecimentoHelper = new CarroPipaAbastecimentoHelper();
		
		ProcessarRequisicaoGpipaAction processarRequisicaoGpipaAction = new ProcessarRequisicaoGpipaAction();
		
		if(colunasArquivo!=null){
			
			if(!colunasArquivo[0].equals("") && processarRequisicaoGpipaAction.validarStringComoInteger(colunasArquivo[0])){
				
				if(Fachada.getInstancia().verificarExistenciaMatriculaImovel(Integer.valueOf(colunasArquivo[0]))){
					
					carroPipaAbastecimentoHelper.setIdImovel(Integer.valueOf(colunasArquivo[0]));
				
					if(!colunasArquivo[1].equals("") && processarRequisicaoGpipaAction.validarStringComoInteger(colunasArquivo[1])){
						
						if(this.validarSequencialCarroPipaAbastecimento(Integer.valueOf(colunasArquivo[1]))==0){
							
							carroPipaAbastecimentoHelper.setSequencial(Integer.valueOf(colunasArquivo[1]));
							
							if(!colunasArquivo[2].equals("") && colunasArquivo[2].length()==7){
								
								carroPipaAbastecimentoHelper.setDescricaoPlaca(colunasArquivo[2]);
								
								if(!colunasArquivo[3].equals("") && !processarRequisicaoGpipaAction.validarDataHoraAbastecimentoInvalida(colunasArquivo[3])){
									
									carroPipaAbastecimentoHelper.setDataHoraAbastecimento(colunasArquivo[3]);
									
									if(!colunasArquivo[4].equals("") && processarRequisicaoGpipaAction.validarStringComoInteger(colunasArquivo[4])){
										
										carroPipaAbastecimentoHelper.setVolumeAbastecimento(Integer.valueOf(colunasArquivo[4]));
										
										if(!colunasArquivo[5].equals("") && processarRequisicaoGpipaAction.validarStringComoInteger(colunasArquivo[5])
												&& (colunasArquivo[5].equals(ConstantesSistema.SIM.toString()) ||
												colunasArquivo[5].equals(ConstantesSistema.NAO.toString()))){
											
											carroPipaAbastecimentoHelper.setIndicadorCobranca(Short.valueOf(colunasArquivo[5]));
											
											if(colunasArquivo.length==7){
												if(!colunasArquivo[6].equals("") && processarRequisicaoGpipaAction.validarStringComoInteger(colunasArquivo[6])
														&& (colunasArquivo[6].equals(ConstantesSistema.SIM.toString())
																|| colunasArquivo[6].equals(ConstantesSistema.NAO.toString()))){
													
													carroPipaAbastecimentoHelper.setIndicadorAbastecimentoAvulso(Short.valueOf(colunasArquivo[6]));
													
													carroPipaAbastecimentoHelper.setRetornoErro(null);
												}else{
													carroPipaAbastecimentoHelper.setRetornoErro(Integer.
														valueOf(CarroPipaRetornoTipo.ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO));
												}
											}else{
												carroPipaAbastecimentoHelper.setRetornoErro(Integer.
													valueOf(CarroPipaRetornoTipo.ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO));
											}
										}else{
											carroPipaAbastecimentoHelper.setRetornoErro(Integer.
												valueOf(CarroPipaRetornoTipo.ID_INDICADOR_COBRANCA_INVALIDO));
										}
									}else{
										carroPipaAbastecimentoHelper.setRetornoErro(Integer.
											valueOf(CarroPipaRetornoTipo.ID_VOLUME_ABASTECIMENTO_INVALIDO));
									}
								}else{
									carroPipaAbastecimentoHelper.setRetornoErro(Integer.
										valueOf(CarroPipaRetornoTipo.ID_DATA_HORA_ABASTECIMENTO_INVALIDA));
								}
							}else{
								carroPipaAbastecimentoHelper.setRetornoErro(Integer.
									valueOf(CarroPipaRetornoTipo.ID_PLACA_CAMINHAO_INVALIDA));
							}
						}else{
							carroPipaAbastecimentoHelper.setRetornoErro(Integer.
								valueOf(CarroPipaRetornoTipo.ID_SEQUENCIAL_INVALIDO));
						}
					}else{
						carroPipaAbastecimentoHelper.setRetornoErro(Integer.
							valueOf(CarroPipaRetornoTipo.ID_SEQUENCIAL_INVALIDO));
					}
				}else{
					carroPipaAbastecimentoHelper.setRetornoErro(Integer.
						valueOf(CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO));
				}
			}else{
				carroPipaAbastecimentoHelper.setRetornoErro(Integer.
					valueOf(CarroPipaRetornoTipo.ID_IMOVEL_INVALIDO));
			}
			
		}else{
		
			if(request.getParameter(ProcessarRequisicaoGpipaAction.ID_IMOVEL)!=null){
				if(!request.getParameter(ProcessarRequisicaoGpipaAction.ID_IMOVEL).toString().trim().equals("")
						&& processarRequisicaoGpipaAction.validarStringComoInteger(request.getParameter(ProcessarRequisicaoGpipaAction.ID_IMOVEL).toString())){
					if(Fachada.getInstancia().verificarExistenciaMatriculaImovel(Integer.
						valueOf(request.getParameter(ProcessarRequisicaoGpipaAction.ID_IMOVEL)))){
						
						carroPipaAbastecimentoHelper.setIdImovel(Integer.valueOf(request.
							getParameter(ProcessarRequisicaoGpipaAction.ID_IMOVEL)));
						
						if(request.getParameter(ProcessarRequisicaoGpipaAction.SEQUENCIAL)!=null){
							
							if(!request.getParameter(ProcessarRequisicaoGpipaAction.SEQUENCIAL).equals("")
									&& processarRequisicaoGpipaAction.validarStringComoInteger(request.getParameter(ProcessarRequisicaoGpipaAction.SEQUENCIAL))){
								
								if(this.validarSequencialCarroPipaAbastecimento(Integer.valueOf(request.
									getParameter(ProcessarRequisicaoGpipaAction.SEQUENCIAL)))==0){
									
									carroPipaAbastecimentoHelper.setSequencial(Integer.valueOf(request.
										getParameter(ProcessarRequisicaoGpipaAction.SEQUENCIAL)));
									
									if(request.getParameter(ProcessarRequisicaoGpipaAction.DESCRICAO_PLACA)!=null){
										if(!request.getParameter(ProcessarRequisicaoGpipaAction.DESCRICAO_PLACA).toString().trim().equals("")){
											if(request.getParameter(ProcessarRequisicaoGpipaAction.DESCRICAO_PLACA).toString().trim().length()==7){
												
												carroPipaAbastecimentoHelper.setDescricaoPlaca(request.getParameter(ProcessarRequisicaoGpipaAction.DESCRICAO_PLACA));
												
												if(request.getParameter(ProcessarRequisicaoGpipaAction.VOLUME_ABASTECIMENTO)!=null){
													if(!request.getParameter(ProcessarRequisicaoGpipaAction.VOLUME_ABASTECIMENTO).toString().trim().equals("")
															&& processarRequisicaoGpipaAction.validarStringComoInteger(request.getParameter(ProcessarRequisicaoGpipaAction.VOLUME_ABASTECIMENTO).toString())){
														
														carroPipaAbastecimentoHelper.setVolumeAbastecimento(Integer.valueOf(request.getParameter(ProcessarRequisicaoGpipaAction.VOLUME_ABASTECIMENTO)));
														
														if(request.getParameter(ProcessarRequisicaoGpipaAction.DATA_HORA_ABASTECIMENTO)!=null){
															if(!request.getParameter(ProcessarRequisicaoGpipaAction.DATA_HORA_ABASTECIMENTO).toString().trim().equals("")
																	&& !processarRequisicaoGpipaAction.validarDataHoraAbastecimentoInvalida(request.getParameter(ProcessarRequisicaoGpipaAction.DATA_HORA_ABASTECIMENTO).toString())){
																
																carroPipaAbastecimentoHelper.setDataHoraAbastecimento(request.getParameter(ProcessarRequisicaoGpipaAction.DATA_HORA_ABASTECIMENTO));
																
																if(request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_COBRANCA)!=null){
																	if(!request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_COBRANCA).toString().trim().equals("")
																			&& processarRequisicaoGpipaAction.validarStringComoInteger(request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_COBRANCA).toString())
																			&& (request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_COBRANCA).toString().equals(ConstantesSistema.SIM.toString())
																					|| request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_COBRANCA).toString().equals("2"))){
																		
																		carroPipaAbastecimentoHelper.setIndicadorCobranca(Short.valueOf(request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_COBRANCA)));
																		
																		if(request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_ABASTECIMENTO_AVULSO)!=null){
																			if(processarRequisicaoGpipaAction.validarStringComoInteger(request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_ABASTECIMENTO_AVULSO).toString())
																					&& (request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_ABASTECIMENTO_AVULSO).toString().trim().equals(ConstantesSistema.SIM.toString())
																							|| request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_ABASTECIMENTO_AVULSO).toString().trim().equals(ConstantesSistema.NAO.toString()))){
																				
																				carroPipaAbastecimentoHelper.setIndicadorAbastecimentoAvulso(Short.valueOf(request.getParameter(ProcessarRequisicaoGpipaAction.INDICADOR_ABASTECIMENTO_AVULSO)));
																				
																				carroPipaAbastecimentoHelper.setRetornoErro(null);
																			}else{
																				carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
																					ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO));
																			}
																		}else{
																			carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
																				ID_INDICADOR_ABASTECIMENTO_AVULSO_INVALIDO));
																		}
																	}else{
																		carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
																			ID_INDICADOR_COBRANCA_INVALIDO));
																	}
																}else{
																	carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
																		ID_INDICADOR_COBRANCA_INVALIDO));
																}
															}else{
																carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
																	ID_DATA_HORA_ABASTECIMENTO_INVALIDA));
															}
														}else{
															carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
																ID_DATA_HORA_ABASTECIMENTO_INVALIDA));
														}
													}else{
														carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
															ID_VOLUME_ABASTECIMENTO_INVALIDO));
													}
												}else{
													carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
														ID_VOLUME_ABASTECIMENTO_INVALIDO));
												}
											}else{
												carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
													ID_PLACA_CAMINHAO_INVALIDA));
											}
										}else{
											carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
												ID_PLACA_CAMINHAO_INVALIDA));
										}
									}else{
										carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
											ID_PLACA_CAMINHAO_INVALIDA));
									}
								}else{
									carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
										ID_SEQUENCIAL_INVALIDO));
								}
							}else{
								carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
									ID_SEQUENCIAL_INVALIDO));
							}
						}else{
							carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
								ID_SEQUENCIAL_INVALIDO));
						}
					}else{
						carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
							ID_IMOVEL_INVALIDO));
					}
				}else{
					carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
						ID_IMOVEL_INVALIDO));
				}
			}else{
				carroPipaAbastecimentoHelper.setRetornoErro(Integer.valueOf(CarroPipaRetornoTipo.
					ID_IMOVEL_INVALIDO));
			}
		}
		return carroPipaAbastecimentoHelper;
	}
	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1569] Receber Informacoes Abastecimento Carro-Pipa Via Arquivo Txt
	 * @param buffer
	 * @throws ControladorException
	 */
	public byte[] gerarArquivoTxtRetornoCarroPipaAbastecimento(List<CarroPipaRetornoOcorrencia>
	listaCarroPipaRetornoOcorrencia,Integer quantidadeLinhas)throws ControladorException{
	
		StringBuilder arquivoRetono = new StringBuilder();
		
		int sequencialImpressao = 0;
		BigDecimal quantidaLinhasBigDecimal = new BigDecimal("0");
		BigDecimal quantidadeLinhasBigecimalAuxiliar = new BigDecimal("1000000000");
		
		
		BigDecimal retorno = quantidadeLinhasBigecimalAuxiliar.add(
			quantidaLinhasBigDecimal.add(new BigDecimal(quantidadeLinhas)));
		
		arquivoRetono.append(retorno.toString().substring(1, retorno.toString().length()));
		arquivoRetono.append(System.getProperty("line.separator"));
		while(listaCarroPipaRetornoOcorrencia.size()>sequencialImpressao){
			
			arquivoRetono.append(listaCarroPipaRetornoOcorrencia.get(sequencialImpressao).getCarroPipaRetornoTipo().getId());
			arquivoRetono.append(";");
			arquivoRetono.append(listaCarroPipaRetornoOcorrencia.get(sequencialImpressao).getCarroPipaRetornoTipo().getDescricaoRetorno());
			arquivoRetono.append(";");
			if(listaCarroPipaRetornoOcorrencia.get(sequencialImpressao).getImovel().getId()!=null){
				arquivoRetono.append(listaCarroPipaRetornoOcorrencia.get(sequencialImpressao).getImovel().getId());
			}else{
				arquivoRetono.append("");
			}
			arquivoRetono.append(";");

			if(sequencialImpressao+1<listaCarroPipaRetornoOcorrencia.size()){
				arquivoRetono.append(System.getProperty("line.separator"));
			}
			
			sequencialImpressao++;
		}
		
		return arquivoRetono.toString().getBytes();
	}
	
	/**
	 * autor: Jonathan Marcos
	 * Data: 22/10/2013
	 * [UC1569] Receber Informacoes Abastecimento Carro-Pipa Via Arquivo Txt
	 * @param buffer
	 * @throws ControladorException
	 */
	public void enviarEmailCarroPipaAbastecimentoArquivoTxt(byte[] arquivoGerado)
		throws ControladorException{
		EnvioEmail envioEmail = Fachada.getInstancia()
                .pesquisarEnvioEmail(
                        EnvioEmail.CARRO_PIPA_ABASTECIMENTO_ARQUIVO_TXT);
		
		 String emailRemetente = envioEmail.getEmailRemetente();
         String tituloMensagem = envioEmail.getTituloMensagem();
         String corpoMensagem = envioEmail.getCorpoMensagem();
         String emailReceptor = envioEmail.getEmailReceptor();
         
        
        ZipOutputStream zos = null;
        FileOutputStream out = null;
        
        Date dataNomeArquivo = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dataFormatada = simpleDateFormat.format(dataNomeArquivo).replace(" ", "").
        		toString().replace("/", "").toString().replace(":", "").toString();
        
        try {
        	String nomeZip = "abastecimento_carro_pipa_"+dataFormatada;
            
         	nomeZip = nomeZip.replace("/","");
         	
         	File leitura = new File(nomeZip +".TXT");
            File compactado = new File(nomeZip + ".zip"); // nomeZip
    		zos = new ZipOutputStream(new FileOutputStream(compactado));
             
    		out = new FileOutputStream(leitura.getAbsolutePath());
            out.write(arquivoGerado);
            out.flush();
            
            ZipUtil.adicionarArquivo(zos, leitura);
    			
            ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor,
            	emailRemetente, 
                tituloMensagem, 
                corpoMensagem, 
                leitura);
            
            leitura.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
	
	/**
	 * autor:Jonathan Marcos
	 * data:23/10/2013
	 * [UC1568] Receber InformaÁıes Abastecimentos Carros Pipa Via Webservice 
	 */
	public Integer validarSequencialCarroPipaAbastecimento(Integer sequencial)
		throws ControladorException{
		try {
			return repositorioIntegracao.validarSequencialCarroPipaAbastecimento(sequencial);
		} catch (Exception e) {
			throw new ControladorException(e.getMessage(), e);
		}
	}
}

