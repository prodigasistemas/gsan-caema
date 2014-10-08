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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.FiltroSmsTipo;
import gcom.atendimentopublico.SmsTipo;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de encerra OS caso precise
 * entrar na tela
 * 
 * @author S�vio Luiz
 * @created 18/09/2006
 */
public class EncerrarOrdemServicoAction extends GcomAction {
	/**
	 * Execute do Consultar OS Popup
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm = (EncerrarOrdemServicoActionForm) actionForm;

        if ("confirmaEncerramentoRA".equals(httpServletRequest.getParameter("tipoRelatorio"))) {
			sessao.setAttribute("confirmaEncerramentoRA", httpServletRequest.getParameter("confirmado"));
        }
        
        String valorConfirmacaoEncerramentoRA = (String) sessao.getAttribute("confirmaEncerramentoRA"); 
        
        ////////////////////////////////////////////////////////////////////////////////////////////////

        if ("confirmaBoletimValorZero".equals(httpServletRequest.getParameter("tipoRelatorio"))) {
			sessao.setAttribute("confirmaBoletimValorZero", httpServletRequest.getParameter("confirmado"));
		}
        
        
        String valorConfirmacaoBoletimValorZero = (String) sessao.getAttribute("confirmaBoletimValorZero"); 
        ///////////////////////////////////////////////////////////////////////////////////////////////
        
    	
		Integer idOSDiagnostico = null;
		OrdemServicoBoletim ordemServicoBoletim = null;
		// parte da integra��o com o sistema comercial
		IntegracaoComercialHelper integracaoComercialHelper = (IntegracaoComercialHelper) sessao.getAttribute("integracaoComercialHelper");
		
		Short indicadorServicoAceito = null;
		if (encerrarOrdemServicoActionForm.getIndicadorServicoAceito() != null
				&& !encerrarOrdemServicoActionForm.getIndicadorServicoAceito().equals("")) {
			indicadorServicoAceito = new Short(encerrarOrdemServicoActionForm.getIndicadorServicoAceito());
		}
		
		/*
		 * Colocado por Raphael Rossiter em 09/05/2007
		 * Caso o id do motivo de n�o cobran�a venha com o valor -1 n�o ser� gerado o objeto
		 * ServicoNaoCobrancaMotivo dentro da OS
		 */
        
		ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo  = null;
		BigDecimal valorDebito = null;
		if (integracaoComercialHelper != null &&
			integracaoComercialHelper.getOrdemServico() != null &&
			integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo() != null &&
			integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo()
			.getId().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			integracaoComercialHelper.getOrdemServico().setServicoNaoCobrancaMotivo(null);
			
		}else if (integracaoComercialHelper != null &&
				integracaoComercialHelper.getOrdemServico() != null &&
				integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo() != null ) {

			servicoNaoCobrancaMotivo  = 
				integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo();
				
		}
		
		if(integracaoComercialHelper != null &&
			integracaoComercialHelper.getOrdemServico() != null){
			
			valorDebito = integracaoComercialHelper.getOrdemServico().getValorAtual();
		}
		
		RegistroAtendimento registroAtendimento = null;
		
		if (valorConfirmacaoEncerramentoRA == null 	|| valorConfirmacaoEncerramentoRA.equals("")) {
			// valida enter
			String idServicoTipo = encerrarOrdemServicoActionForm.getIdServicoTipo();
			String descricaoServicoTipo = encerrarOrdemServicoActionForm.getDescricaoServicoTipo();

			Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection) sessao.getAttribute("colecaoManutencao");
			if ((idServicoTipo != null && !idServicoTipo.equals("")) && (descricaoServicoTipo == null || descricaoServicoTipo.equals(""))) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));
				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection servicoTipoEncontrado = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

				if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {
					ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipoEncontrado);
					// O servi�o tipo foi encontrado
					encerrarOrdemServicoActionForm.setIdServicoTipo("" + servicoTipo.getId());
					encerrarOrdemServicoActionForm.setDescricaoServicoTipo(servicoTipo.getDescricao());

				} else {
					throw new ActionServletException("atencao.label_inexistente", null, "Servi�o Tipo");
				}

			}
			// caso seja a primeira vez
			
			if (integracaoComercialHelper == null || integracaoComercialHelper.equals("")) {
				
				// [FS0002] - Validar Tipo Servi�o
				// [FS0004] - Verificar preenchimento dos campos
				// [FS0007] - Validar Data de Encerramento
				// [FS0008] - Validar Data do roteiro
				fachada.validarCamposEncerrarOS(
						encerrarOrdemServicoActionForm.getIndicadorExecucao(), 
						encerrarOrdemServicoActionForm.getNumeroOS(), 
						encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
						encerrarOrdemServicoActionForm.getDataEncerramento(),						
						colecaoManterDadosAtividadesOrdemServicoHelper,
						encerrarOrdemServicoActionForm.getTipoServicoReferenciaId(),
						encerrarOrdemServicoActionForm.getIndicadorPavimento(),
						encerrarOrdemServicoActionForm.getPavimento(),
						encerrarOrdemServicoActionForm.getIdTipoRetornoReferida(),
						encerrarOrdemServicoActionForm.getIndicadorDeferimento(),
						encerrarOrdemServicoActionForm.getIndicadorTrocaServico(), idServicoTipo,
						encerrarOrdemServicoActionForm.getDataRoteiro(),
						encerrarOrdemServicoActionForm.getNumeroRA(),
						encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
						encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
						encerrarOrdemServicoActionForm.getIndicadorDiagnostico(),
                        encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
                        usuarioLogado);
				
				Map validacao = validarInformacoesBoletimMedicao(new Integer(encerrarOrdemServicoActionForm.getNumeroOS()), encerrarOrdemServicoActionForm);
				
				ordemServicoBoletim = (OrdemServicoBoletim) validacao.get("ordemServicoBoletim");
				Boolean exibirMsgConfirmacao = (Boolean) validacao.get("exibirMsgConfirmacao");				
				
				if (exibirMsgConfirmacao && (valorConfirmacaoBoletimValorZero == null || !valorConfirmacaoBoletimValorZero.equalsIgnoreCase("ok"))) {
					
					httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/encerrarOrdemServicoAction.do");
					// httpServletRequest.setAttribute("cancelamento", "TRUE");
					httpServletRequest.setAttribute("nomeBotao1", "Sim");
					// httpServletRequest.setAttribute("nomeBotao2", "N�o");
					httpServletRequest.setAttribute("tipoRelatorio", "confirmaBoletimValorZero");
					
					return montarPaginaConfirmacao("atencao.encerrar_OS_boletim", httpServletRequest, actionMapping);
				}
			} else {
				
				Map validacao = validarInformacoesBoletimMedicao(new Integer(encerrarOrdemServicoActionForm.getNumeroOS()), encerrarOrdemServicoActionForm);
				
				ordemServicoBoletim = (OrdemServicoBoletim) validacao.get("ordemServicoBoletim");
			}

			// indicador execu��o seja diferente de nulo
			if (encerrarOrdemServicoActionForm.getIndicadorExecucao() != null && !encerrarOrdemServicoActionForm.getIndicadorExecucao().equals("")) {
				short indicadorExecucao = Short.parseShort(encerrarOrdemServicoActionForm.getIndicadorExecucao());
				Integer numeroOS = Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroOS());				
				//Date dataEncerramento = Util.converteStringParaDate(encerrarOrdemServicoActionForm.getDataEncerramento());
				Date dataEncerramento = new Date();
				
				if (sessao.getAttribute("ordemServicoFiscalizacao") == null) {

					FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
					filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
						FiltroAtendimentoMotivoEncerramento.ID, encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
					Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(
						filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
					AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) 
							Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
					
					if (atendimentoMotivoEncerramento.getIndicadorFiscalizacao() != null
							&& atendimentoMotivoEncerramento.getIndicadorFiscalizacao().compareTo(ConstantesSistema.SIM) == 0) {
						OrdemServico osFiscalizacao = new OrdemServico();
						
						ServicoTipo servicoTipo = fachada
								.pesquisarSevicoTipo(ServicoTipo.TIPO_FISCALIZACAO);
						osFiscalizacao.setServicoTipo(
							servicoTipo);
						osFiscalizacao.setValorOriginal(
							servicoTipo.getValor());
						osFiscalizacao.setServicoTipoPrioridadeOriginal(
							servicoTipo.getServicoTipoPrioridade());
						osFiscalizacao.setServicoTipoPrioridadeAtual(
							servicoTipo.getServicoTipoPrioridade());
						
						OrdemServico osReferencia = fachada
								.pesquisarOrdemServico(numeroOS);
						osFiscalizacao.setOsReferencia(osReferencia);
						
						osFiscalizacao.setObservacao("Geracao da ordem de fiscalizacao devido ao parametro selecionado no motivo de encerramento.");
						
						if (osReferencia.getImovel() != null) {
							osFiscalizacao.setImovel(
								osReferencia.getImovel());
						}
	
						if (osReferencia.getRegistroAtendimento() != null) {
							osFiscalizacao.setRegistroAtendimento(
								osReferencia.getRegistroAtendimento());
						}
						
						osFiscalizacao.setUnidadeAtual(osReferencia.getUnidadeAtual());
						
						sessao.setAttribute("ordemServicoFiscalizacao", osFiscalizacao);
					}
				}
				
				/*if (encerrarOrdemServicoActionForm.getHoraEncerramento() != null && !encerrarOrdemServicoActionForm.getHoraEncerramento().equals("")) {
					dataEncerramento = Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento() 
											+ " "
											+ encerrarOrdemServicoActionForm.getHoraEncerramento() 
											+ ":00");
				} else {
					dataEncerramento = Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()
											+ " " 
											+ Util.formatarHoraSemSegundos(new Date()) 
											+ ":00");
				}*/
				Date dataUltimaAlteracao = encerrarOrdemServicoActionForm.getUltimaAlteracao();

				// indicador execu��o seja igual a n�o(2)
				if (indicadorExecucao == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO) {

					OrdemServico osFiscalizacao = null;
					
					if (sessao.getAttribute("ordemServicoFiscalizacao") != null) {
						osFiscalizacao = (OrdemServico) sessao.getAttribute("ordemServicoFiscalizacao");
					}
					Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = (Collection<OrdemServicoFoto>) sessao.getAttribute("colecaoOrdemServicoFoto");
					// [SB0001] - Encerrar sem execu��o
					fachada.encerrarOSSemExecucao(numeroOS, 
												  dataEncerramento,
												  usuarioLogado, 
												  encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
												  dataUltimaAlteracao, 
												  encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
												  osFiscalizacao, 
												  encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
												  encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
												  ordemServicoBoletim,
												  indicadorServicoAceito, colecaoOrdemServicoFoto);
					
				} else {
					// indicador execu��o seja igual a sim(1)
					if (indicadorExecucao == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM) {
						//[FS0019 Verificar obrigatoriedade da foto]
						if(encerrarOrdemServicoActionForm.getTipoServicoOSId().equals(ServicoTipo.LIGADO_DECRETO.toString())){
							Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = (Collection<OrdemServicoFoto>) sessao.getAttribute("colecaoOrdemServicoFoto");
							if(colecaoOrdemServicoFoto==null || colecaoOrdemServicoFoto.size()==0){
								throw new ActionServletException("atencao.verificar_obrigatoriedade_foto");
							}
						}
						//[FS0014] Validar Hora de Execu��o.
						//[FS0015] Validar Data de Execu��o.
						Date dataExecucao = validaDataHoraExecucao(encerrarOrdemServicoActionForm, numeroOS, usuarioLogado);
						
						if (encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial() != null
								&& !encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial().equals("")) {
							Short indicadorComercialAtualiza = new Short(encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial());

							if (indicadorComercialAtualiza.equals(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM)) {
								// caso n�o exista o objeto helper na sess�o
								// ent�o � a primeira vez
								if (integracaoComercialHelper == null || integracaoComercialHelper.equals("")) {
									// caso exista tipo de servi�o
									if (encerrarOrdemServicoActionForm.getTipoServicoOSId() != null
											&& !encerrarOrdemServicoActionForm.getTipoServicoOSId().equals("")) {

										FiltroServicoTipoOperacao filtroServicoTipoOperacao = new FiltroServicoTipoOperacao();
										filtroServicoTipoOperacao.adicionarCaminhoParaCarregamentoEntidade("operacao");
										filtroServicoTipoOperacao.adicionarParametro(new ParametroSimples(FiltroServicoTipoOperacao.ID_SERVICO_TIPO, encerrarOrdemServicoActionForm.getTipoServicoOSId()));
										Collection colecaoServicoTipoOperacao = fachada.pesquisar(filtroServicoTipoOperacao, ServicoTipoOperacao.class.getName());
										// caso exista uma funcionalidade assiciada ao servi�o tipo
										if (colecaoServicoTipoOperacao != null && !colecaoServicoTipoOperacao.isEmpty()) {
											ServicoTipoOperacao servicoTipoOperacao = (ServicoTipoOperacao) Util.retonarObjetoDeColecao(colecaoServicoTipoOperacao);
											String caminhoOperacao = servicoTipoOperacao.getOperacao().getCaminhoUrl();
											// caso exista o caminho da opera��o
											if (caminhoOperacao != null && !caminhoOperacao.equals("")) {
												int tamanhoOperacao = caminhoOperacao.length();
												// seta o caminho no mapeamento para ser chamado
												String caminhoRetorno = caminhoOperacao.substring(0, tamanhoOperacao - 3);
												httpServletRequest.setAttribute("veioEncerrarOS", encerrarOrdemServicoActionForm.getNumeroOS());
												httpServletRequest.setAttribute("dataEncerramento", encerrarOrdemServicoActionForm.getDataEncerramento());
												httpServletRequest.setAttribute("caminhoRetornoIntegracaoComercial", "exibirEncerrarOrdemServicoAction.do?retornoConsulta=1");
												retorno = actionMapping.findForward(caminhoRetorno);
												if (retorno == null) {
													throw new ActionServletException("atencao.caminho_url_indisponivel");
												} else {
													/*
													 * caso seja chamado a integra��o ent�o seta a
													 * OS na sess�o com outro nome e remove a OS da
													 * sess�o visto que a integra��o usa o mesmo
													 * nome da OS que est� na sess�o
													 */
													sessao.setAttribute("osFiscalizacao", sessao.getAttribute("ordemServicoFiscalizacao"));
													sessao.removeAttribute("ordemServicoFiscalizacao");
												}
											}
										}
									}
								}
							}
						}
						if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

							OrdemServico osFiscalizacao = null;

							if (integracaoComercialHelper == null
									|| integracaoComercialHelper.equals("")
									&& sessao.getAttribute("ordemServicoFiscalizacao") != null) {
								osFiscalizacao = (OrdemServico) sessao.getAttribute("ordemServicoFiscalizacao");
							}
							 /*
							  * caso a os fiscaliza��o n�o esteja na sess�o
							  * ent�o n�o foi para integra��o e ent�o pode
							  * pegar o OS mesmo
							  */
							if (sessao.getAttribute("osFiscalizacao") != null
									&& !sessao.getAttribute("osFiscalizacao").equals("")) {
								osFiscalizacao = (OrdemServico) sessao.getAttribute("osFiscalizacao");
								
								sessao.removeAttribute("osFiscalizacao");
							}
							// se o servi�o tipo referencia seja igual a nulo
							if (encerrarOrdemServicoActionForm.getTipoServicoReferenciaId() == null
									|| encerrarOrdemServicoActionForm.getTipoServicoReferenciaId().equals("")) {
								
								//pesquisar pela ordem de servi�o informada
								FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
								filtroOrdemServicoPavimento.adicionarParametro(new ParametroSimples(
										filtroOrdemServicoPavimento.ID_ORDEM_SERVICO, numeroOS));
								OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) Util.retonarObjetoDeColecao(
										fachada.pesquisar(filtroOrdemServicoPavimento, OrdemServicoPavimento.class.getName()));		
								
								if(ordemServicoPavimento == null){
									ordemServicoPavimento = new OrdemServicoPavimento();
									OrdemServico orse = new OrdemServico();
									orse.setId(numeroOS);
									ordemServicoPavimento.setOrdemServico(orse);
								}
								
								//Verifica o id pavimento rua.
								if (!"".equals(encerrarOrdemServicoActionForm.getIdPavimentoRua())
										&& encerrarOrdemServicoActionForm.getIdPavimentoRua() != null) {
									
									PavimentoRua pavimentoRua = new PavimentoRua();
									pavimentoRua.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdPavimentoRua()));
									
									ordemServicoPavimento.setPavimentoRuaRetorno(pavimentoRua);
								}
								//Verifica a metragem do pavimento rua se � nulo e maior que zero
								if (!"".equals(encerrarOrdemServicoActionForm.getMetragemPavimentoRua())
										&& encerrarOrdemServicoActionForm.getMetragemPavimentoRua() != null) {
									
									BigDecimal metragem = Util.formatarStringParaBigDecimal(encerrarOrdemServicoActionForm.getMetragemPavimentoRua().replaceAll(",", "."));
									if(metragem == null){
						        		throw new ActionServletException("atencao.metragem_pavimento_rua_invalida");
						        	}
									
									ordemServicoPavimento.setAreaPavimentoRuaRetorno(metragem);
								}
								
								if (!"".equals(encerrarOrdemServicoActionForm.getIdPavimentoCalcada())
										&& encerrarOrdemServicoActionForm.getIdPavimentoCalcada() != null) {
									PavimentoCalcada pavimentoCalcada = new PavimentoCalcada();
									pavimentoCalcada.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdPavimentoCalcada()));
									ordemServicoPavimento.setPavimentoCalcada(pavimentoCalcada);
								}
								//Verifica a metragem do pavimento calcada se � nulo e maior que zero
								if (!"".equals(encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada())
										&& encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada() != null) {
									
									BigDecimal metragem = Util.formatarStringParaBigDecimal(encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada().replaceAll(",", "."));
									if(metragem == null){
						        		throw new ActionServletException("atencao.metragem_pavimento_calcada_invalida");
						        	}
									
									ordemServicoPavimento.setAreaPavimentoCalcada(metragem);		
								}
								
								Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = (Collection<OrdemServicoFoto>) sessao.getAttribute("colecaoOrdemServicoFoto");
								
								// [SB0002] - Encerrar com execu��o e sem
								// refer�ncia
								fachada.encerrarOSComExecucaoSemReferencia(
												numeroOS,
												dataEncerramento,
												usuarioLogado,
												encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
												dataUltimaAlteracao,
												encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
												encerrarOrdemServicoActionForm.getIndicadorPavimento(),
												encerrarOrdemServicoActionForm.getPavimento(),
												colecaoManterDadosAtividadesOrdemServicoHelper,
												integracaoComercialHelper,
												encerrarOrdemServicoActionForm.getTipoServicoOSId(),
												osFiscalizacao,
												encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
												encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
												ordemServicoPavimento,
												ordemServicoBoletim,
												indicadorServicoAceito, dataExecucao, colecaoOrdemServicoFoto);

							} else {
								Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = (Collection<OrdemServicoFoto>) sessao.getAttribute("colecaoOrdemServicoFoto");
								 /*
						 		  * [SB0003] - Encerrar com execu��o e com
								  * refer�ncia
								  */
								
								idOSDiagnostico = fachada
										.encerrarOSComExecucaoComReferencia(
												numeroOS,
												dataEncerramento,
												usuarioLogado,
												encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
												dataUltimaAlteracao,
												encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
												encerrarOrdemServicoActionForm.getIndicadorPavimento(),
												encerrarOrdemServicoActionForm.getPavimento(),
												encerrarOrdemServicoActionForm.getIdTipoRetornoReferida(),
												encerrarOrdemServicoActionForm.getIndicadorDeferimento(),
												encerrarOrdemServicoActionForm.getIndicadorTrocaServico(),
												encerrarOrdemServicoActionForm.getIdServicoTipo(),
												encerrarOrdemServicoActionForm.getNumeroOSReferencia(),
												encerrarOrdemServicoActionForm.getServicoTipoReferenciaOS(),
												colecaoManterDadosAtividadesOrdemServicoHelper,
												integracaoComercialHelper,
												encerrarOrdemServicoActionForm.getTipoServicoOSId(),
												osFiscalizacao,
												encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
												encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
												ordemServicoBoletim,
												indicadorServicoAceito, dataExecucao, colecaoOrdemServicoFoto);
							}	
						}
					}
				}
			}

			/**
			 * [RM5185] 
			 * @author Magno Gouveia
			 * @since 08/08/2011
			 */
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
			filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, encerrarOrdemServicoActionForm.getTipoServicoOSId()));
			ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName()));
			
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				
				if (servicoTipo.getIndicadorEncAutomaticoRAQndEncOS().compareTo(ConstantesSistema.NAO) == 0) {

					boolean telaConfirmacao = fachada.tramitarOuEncerrarRADaOSEncerrada(
														Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroOS()),
														usuarioLogado,
														encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
														encerrarOrdemServicoActionForm.getNumeroRA(),
														encerrarOrdemServicoActionForm.getDataRoteiro());
					// se for para ir para a tela de confirma��o			
					if(telaConfirmacao){
						httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/encerrarOrdemServicoAction.do");
						httpServletRequest.setAttribute("cancelamento", "TRUE");
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao2", "N�o");
		                httpServletRequest.setAttribute( "tipoRelatorio", "confirmaEncerramentoRA" );
		
						return montarPaginaConfirmacao("atencao.encerrar_RA_da_OS", httpServletRequest, actionMapping);
					} else {
						
					}
				} else {
					/**
					  * [UC0457] Encerrar Ordem de Servi�o 5.4 do Fluxo
					  * Principal
					  * 
					  * @author Hugo Leonardo
					  * @created 17/02/2011
					  */
					if (encerrarOrdemServicoActionForm.getNumeroRA() != null && !encerrarOrdemServicoActionForm.getNumeroRA().equals("")) {
						
						FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
						filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, encerrarOrdemServicoActionForm.getNumeroRA()));
					
						Collection colecaoRegistroAtendimento = Fachada.getInstancia().pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName() );
						
						registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
						
						AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
						atendimentoMotivoEncerramento.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
						atendimentoMotivoEncerramento.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
						registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
						registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
						
						this.setDataEncerramentoRA(encerrarOrdemServicoActionForm, registroAtendimento);
							
						registroAtendimento.setUltimaAlteracao(new Date());
	
						/*
						 * cria o objeto para a inser��o do registro de atendimento unidade
						 */
						RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
						registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
	
						if (usuarioLogado.getUnidadeOrganizacional() != null
								&& !usuarioLogado.getUnidadeOrganizacional().equals("")) {

							registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
						}
						registroAtendimentoUnidade.setUsuario(usuarioLogado);
	
						// atendimento rela��o tipo
						AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
						atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
						registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
						registroAtendimentoUnidade.setUltimaAlteracao(new Date());
						
						// Colocado por Raphael Rossiter em 10/03/2008
						ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = Fachada.getInstancia().obterDadosRegistroAtendimento(registroAtendimento.getId());
						
						SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
				        
				        // Validamos se possivel criar um novo RA
				        Object[] arrayValidaGeracaoNovoRA = validarGeracaoNovoRA(registroAtendimentoHelper.getRegistroAtendimento(), httpServletRequest, actionMapping);
				        
				        if ((Boolean) arrayValidaGeracaoNovoRA[2]) {
							return (ActionForward) arrayValidaGeracaoNovoRA[0];
						}
				            
				        Boolean confirmadoGeracaoNovoRA = (Boolean) arrayValidaGeracaoNovoRA[1];                
				    	
				        if (especificacao.getDebitoTipo() != null
								&& servicoNaoCobrancaMotivo == null) {
							
				        	if (valorDebito == null) {
								valorDebito = especificacao.getValorDebito();
							}
				        	
							fachada.encerrarRegistroAtendimento(
									registroAtendimento,
									registroAtendimentoUnidade, 
									usuarioLogado,
									especificacao.getDebitoTipo().getId(),
									valorDebito, 
									1, 
									"100",
									confirmadoGeracaoNovoRA, 
									null, 
									false);
						} else {
							fachada.encerrarRegistroAtendimento(
								registroAtendimento,
								registroAtendimentoUnidade, 
								usuarioLogado, 
								null, 
								null, 
								null, 
								null, 
								confirmadoGeracaoNovoRA,
								null,
								false);
						}	
					}	
				}
			}
		} else {
			/*
			 * se o usu�rio confirmar o encerramento da RA da OS que est� sendo
			 * encerrada
			 */
			if (valorConfirmacaoEncerramentoRA.equals("ok")) {
				FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
				filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, encerrarOrdemServicoActionForm.getNumeroRA()));
			
				Collection colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
				registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
				
				
				AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
				atendimentoMotivoEncerramento.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
				atendimentoMotivoEncerramento.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
				registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
				registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
				
				setDataEncerramentoRA(encerrarOrdemServicoActionForm, registroAtendimento);
				
				if (encerrarOrdemServicoActionForm.getObservacaoEncerramento() != null && 
					!encerrarOrdemServicoActionForm.getObservacaoEncerramento().equals("")) {
					
					registroAtendimento.setObservacao(encerrarOrdemServicoActionForm.getObservacaoEncerramento());
				} else {
					registroAtendimento.setObservacao("ENCERRADO ATRAV�S DA FUNCIONALIDADE ENCERRAMENTO DA ORDEM DE SERVI�O");
				}
				
				registroAtendimento.setUltimaAlteracao(new Date());

				/*
				 * cria o objeto para a inser��o do registro de atendimento
				 * unidade
				 */
				RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
				registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);

				if (usuarioLogado.getUnidadeOrganizacional() != null && 
					!usuarioLogado.getUnidadeOrganizacional().equals("")) {
					
					registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
				}
				registroAtendimentoUnidade.setUsuario(usuarioLogado);

				// atendimento rela��o tipo
				AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
				atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
				registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
				registroAtendimentoUnidade.setUltimaAlteracao(new Date());
				
				//Colocado por Raphael Rossiter em 10/03/2008
				ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(registroAtendimento.getId());
				
				SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
                
                // Validamos se possivel criar um novo RA
                Object[] arrayValidaGeracaoNovoRA = validarGeracaoNovoRA(
						registroAtendimentoHelper.getRegistroAtendimento(),
						httpServletRequest, actionMapping);
                
                if ((Boolean) arrayValidaGeracaoNovoRA[2]) {
					return (ActionForward) arrayValidaGeracaoNovoRA[0];
				}
                
                Boolean confirmadoGeracaoNovoRA = (Boolean) arrayValidaGeracaoNovoRA[1];                
            	
                if (especificacao.getDebitoTipo() != null
						&& servicoNaoCobrancaMotivo == null) {
					
                	if (valorDebito == null) {
						valorDebito = especificacao.getValorDebito();
					}
                	
					fachada.encerrarRegistroAtendimento(registroAtendimento,
							registroAtendimentoUnidade, 
							usuarioLogado, 
							especificacao.getDebitoTipo().getId(), 
							valorDebito, 
							1, 
							"100", 
							confirmadoGeracaoNovoRA,null,false );
				} else {
					fachada.encerrarRegistroAtendimento(
						registroAtendimento,
						registroAtendimentoUnidade, 
						usuarioLogado, 
						null, 
						null, 
						null, 
						null, 
						confirmadoGeracaoNovoRA,null,false );
				}
			}
		}

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {	
	
			/**
			 * [UC0457] Encerrar Ordem de Servi�o
			 * [SB0010] - Emitir Mensagem de SMS para Encerramento da OS com Sucesso 
			 * 
			 * @author Maxwell Moreira
			 * @created 07/03/2013
			 */	
			if (encerrarOrdemServicoActionForm.getNumeroRA() != null && !encerrarOrdemServicoActionForm.getNumeroRA().equals("")) {
				

				Integer ordemServicoOrigem = fachada.menorTempoGeracaoOrdemDeServico(String.valueOf(encerrarOrdemServicoActionForm.getNumeroRA()));
							
				if  (ordemServicoOrigem.compareTo(
						Integer.valueOf(encerrarOrdemServicoActionForm.getNumeroOS())) == 0){
				
					Boolean enviaMensagemSMS = fachada.quantidadeEncerramentoConclusaoDeServico(Integer.valueOf(encerrarOrdemServicoActionForm.getNumeroRA()), Integer.valueOf(encerrarOrdemServicoActionForm.getNumeroOS()));
					 
					if (enviaMensagemSMS){
										
						FiltroSmsTipo filtroSmsTipo = new FiltroSmsTipo();
						filtroSmsTipo.adicionarParametro(new ParametroSimples(
								FiltroSmsTipo.ID, SmsTipo.ORDEM_SERVICO_CONCLUIDA_COM_SUCESSO));
						Collection<SmsTipo> colecaoSmsTipo = fachada.pesquisar(filtroSmsTipo, SmsTipo.class.getName());
						
						SmsTipo smsTipo = (SmsTipo) Util.retonarObjetoDeColecao(colecaoSmsTipo);
						
						String mensagem = fachada.obterMensagemSmsRegistroAtendimento(smsTipo, Integer.valueOf(encerrarOrdemServicoActionForm.getNumeroRA()), Integer.valueOf(encerrarOrdemServicoActionForm.getNumeroOS()));
						
						Collection celularCliente = fachada.clienteRegistroAtendimentoTemCelular(String.valueOf(encerrarOrdemServicoActionForm.getNumeroRA()));
						
						if ((celularCliente != null) && (!celularCliente.isEmpty())){
							 
							Iterator iteratorCelularCliente = celularCliente.iterator();
							
							while (iteratorCelularCliente.hasNext()) {
								
								Object[] celular = (Object[]) iteratorCelularCliente.next();	
								
								if (((celular[0] != null) && (!celular[0].equals(""))) &&	
										((celular[1] != null) && (!celular[1].equals("")))){
									String celularComDDD = String.valueOf(celular[0])  + String.valueOf(celular[1]);
									fachada.enviarSmsWebService(mensagem, Long.valueOf(celularComDDD));
								}
								
							}
						}		
					}
				}
			}
			
			/*
			 * Autor: Jonathan Marcos
			 * Data: 25/11/2013
			 * RM8974
			 * UC0364
			 * [Observacoes] Caso o tipo de ordem de servico
			 * encerra se SUBSTITUICAO DE HIDROMETRO 
			 */
			if(sessao.getAttribute("tipoSubstituicao")!=null){
				
				sessao.setAttribute("ordemServicoEncerrada", sessao.getAttribute("ordemServico"));
				
				montarPaginaSucesso(httpServletRequest, "Substitui��o de Hidr�metro para "+ sessao.getAttribute("tipoMedicaoAtual").toString() +
					" no im�vel "+sessao.getAttribute("matriculaImovel").toString()+ " efetuada com sucesso.", 
					"Efetuar outra Substitui��o de Hidr�metro", "exibirEfetuarSubstituicaoHidrometroAction.do",
					"gerarOrdemServicoRemocaoHidrometroAction.do?servicoTipo="+ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO, "Gerar OS de Remo��o de Hidr�metro",
					"Gerar OS de Instala��o da Caixa de Prote��o", "gerarOrdemServicoInstalacaoCaixaProtecaoHidrometroAction.do?" +
							"servicoTipo="+ServicoTipo.TIPO_INSTALACAO_CAIXA_PROTECAO);
			}
			
			// montando p�gina de sucesso
			if (idOSDiagnostico == null) {
					montarPaginaSucesso(httpServletRequest,
									"Ordem de Servi�o de c�digo " + encerrarOrdemServicoActionForm.getNumeroOS() + " encerrada com sucesso.", 
									"Voltar",
									"exibirConsultarDadosOrdemServicoAction.do?menu=sim&numeroOS=" + encerrarOrdemServicoActionForm.getNumeroOS());
			} else {
				String msg = 
					"Ordem de Servi�o de c�digo " + encerrarOrdemServicoActionForm.getNumeroOS() 
					+ " encerrada com sucesso. Ordem de Servi�o de c�digo " 
					+ idOSDiagnostico 
					+ " gerada com sucesso";
				
				montarPaginaSucesso(
					httpServletRequest,
					msg,
					"Voltar",
					"exibirConsultarDadosOrdemServicoAction.do?menu=sim&numeroOS=" + encerrarOrdemServicoActionForm.getNumeroOS(),
					"gerarRelatorioOrdemServicoAction.do?menu=sim&idsOS=" + encerrarOrdemServicoActionForm.getNumeroOS(),
					"Imprimir Ordem de Servi�o Encerrada",
					"Imprimir Ordem de Servi�o Gerada",
					"gerarRelatorioOrdemServicoAction.do?idsOS=" + idOSDiagnostico);
			}
			sessao.removeAttribute("canceladoOSFiscalizacao");
			sessao.removeAttribute("integracaoComercialHelper");
			sessao.removeAttribute("ordemServico");
			sessao.removeAttribute("ordemServicoFiscalizacao");
			sessao.removeAttribute("confirmaEncerramentoRA");
		}
		
		return retorno;
	}

	private void setDataEncerramentoRA(EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm, RegistroAtendimento registroAtendimento) {
		if (encerrarOrdemServicoActionForm.getHoraEncerramento() != null && 
				!encerrarOrdemServicoActionForm.getHoraEncerramento().equals("")) {
				
			/*
			 * Verifica se a data de encerramento � menor do que
			 * a data de atendimento do RA, Caso seja encerra a
			 * RA com a data atual.
			 */ 
			if (Util.compararDataTime(registroAtendimento.getRegistroAtendimento(),
					Util.converteStringParaDateHora(encerrarOrdemServicoActionForm
						.getDataEncerramento() + " " + encerrarOrdemServicoActionForm.getHoraEncerramento() + ":00")) > 0) {
				
				registroAtendimento.setDataEncerramento(new Date());
			} else {
				registroAtendimento.setDataEncerramento(
					Util.converteStringParaDateHora(encerrarOrdemServicoActionForm
							.getDataEncerramento() + " " + encerrarOrdemServicoActionForm.getHoraEncerramento() + ":00"));
			}	
				
		} else {
			/*
			 * Verifica se a data de encerramento � menor do que
			 * a data de atendimento do RA, Caso seja encerra a
			 * RA com a data atual.
			 */
			if (Util.compararDataTime(registroAtendimento.getRegistroAtendimento(),
					Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()+ " " +
							Util.formatarHoraSemSegundos(new Date()) + ":00")) > 0 ) {
				
				registroAtendimento.setDataEncerramento(new Date());
			} else {
				registroAtendimento
						.setDataEncerramento(Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()
										+ " " + Util.formatarHoraSemSegundos(new Date()) + ":00"));
			}	
			
		}
	}
    
    /**
     * 
     * [UC0435] - Encerrar Registro de Atendimento
     * 
     * M�todo verifica se existe a possibilidade de ser gerada um novo RA.
     * Caso positivo, retorna uma tela de pergunta ao usu�rio para verificar
     * se ele deseja gerar esse novo RA ou n�o. Caso n�o seja necess�ria a pegun-
     * ta, retorna com a tela de sucesso normal ao fluxo.
     *
     * @author bruno
     * @date 15/04/2009
     *
     * @param registroAtendimento: Registro do atendimento.
     * @param request: Onde se ser�o informados os parametros para gera��o da p�gina
     * @param actionMapping: Necess�rio para gera��o da p�gina
     * 
     * @return Object[3]
     * 
     *      Object[0]: ActionFoward com a tela a ser mostrada
     *      Object[1]: Se o usu�rio confirmou ou n�o a inser��o do novo ra
     *      Object[2]: Se ser� redirecionado ao usu�rio perguntando se ser�
     *                 inserido ou n�o o novo RA.
     */
    private Object[] validarGeracaoNovoRA(
			RegistroAtendimento registroAtendimento,
			HttpServletRequest request, ActionMapping actionMapping){
        
        Object[] retorno = new Object[3];
        
        // Verificamos se ja foi confimado...
        retorno[1] = ("confirmaInclusaoNovoRA".equals(request
				.getParameter("tipoRelatorio")) ? request.getParameter(
				"confirmado").equals("ok") : null);
        
        retorno[2] = new Boolean(Boolean.FALSE);
        
        if (retorno[1] == null) {
            Fachada fachada = Fachada.getInstancia();
            
            FiltroSolicitacaoTipoEspecificacao filtro = new FiltroSolicitacaoTipoEspecificacao();
            
            filtro.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.ID, registroAtendimento
							.getSolicitacaoTipoEspecificacao().getId()));
            
            filtro.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacaoNovoRA");
            Collection<SolicitacaoTipoEspecificacao> colSolicitacaoTipoEspecificacao = fachada.pesquisar(filtro, 
            																				SolicitacaoTipoEspecificacao.class.getName());
            
            SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
					.retonarObjetoDeColecao(colSolicitacaoTipoEspecificacao);
            
            if ( solicitacaoTipoEspecificacao.
                    getSolicitacaoTipoEspecificacaoNovoRA()!= null ){
                retorno[2] = new Boolean( Boolean.TRUE );
                
                registroAtendimento.setSolicitacaoTipoEspecificacao( 
                        solicitacaoTipoEspecificacao );
                
                request.setAttribute("caminhoActionConclusao", "/gsan/encerrarOrdemServicoAction.do");
                request.setAttribute("cancelamento", "TRUE");
                request.setAttribute("nomeBotao1", "Sim");
                request.setAttribute("nomeBotao2", "N�o");
                request.setAttribute( "tipoRelatorio", "confirmaInclusaoNovoRA" );
    
                retorno[0] = montarPaginaConfirmacao(
		                        "atencao.encerrar_ra_confirma_geracao_novo_ra",
		                        request, 
		                        actionMapping, 
		                        solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getDescricao());
                return retorno;
            }
        }       
        
        retorno[0] = actionMapping.findForward("telaSucesso");
        return retorno;
    }
    
    /**
     * [UC0457] Encerrar Ordem de Servi�o
     * [SB0007]- Gerar Informa��es para Boletim de Medi��o.
     * 
     * @author Vivianne Sousa
     * @created 28/01/2011
     */
    private Map validarInformacoesBoletimMedicao(Integer idOrdemServico, EncerrarOrdemServicoActionForm form) {
    		
    	OrdemServicoBoletim ordemServicoBoletim = null;
		Boolean exibirMsgConfirmacao = false;
    	
    	if (form.getExibeIndicadorExistePavimento().equals("1")
				|| form.getExibeQtdeReposicaoAsfalto().equals("1")
				|| form.getExibeQtdeReposicaoCalcada().equals("1")
				|| form.getExibeQtdeReposicaoParalelo().equals("1")) {
    		
    		ordemServicoBoletim = new OrdemServicoBoletim();
    		ordemServicoBoletim.setId(idOrdemServico);
    		OrdemServico os = new OrdemServico();
    		os.setId(idOrdemServico);
    		ordemServicoBoletim.setOrdemServico(os);
    		
    		if (form.getExibeIndicadorExistePavimento().equals("1")) {
    			if (form.getIndicadorExistePavimento() == null) {
    				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Existe Pavimento");
    			} else {
    				ordemServicoBoletim.setIndicadorPavimento(new Short(form.getIndicadorExistePavimento()));
    			}
    		} else {
    			ordemServicoBoletim.setIndicadorPavimento(new Short(ConstantesSistema.NAO));
    		}
    		
    		if (form.getExibeQtdeReposicaoAsfalto().equals("1")) {
    			
    			if (form.getQtdeReposicaoAsfalto() == null
						|| form.getQtdeReposicaoAsfalto().equals("")) {

					form.setQtdeReposicaoAsfalto("0");
					ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
					exibirMsgConfirmacao = true;
    				
    			} else if (form.getQtdeReposicaoAsfalto().equals("0")
						|| form.getQtdeReposicaoAsfalto().equals("00")
						|| form.getQtdeReposicaoAsfalto().equals("0,00")) {
					ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
					exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(Util.formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoAsfalto()));
    			}
    		}
    		
    		if (form.getExibeQtdeReposicaoParalelo().equals("1")) {
    			
    			if (form.getQtdeReposicaoParalelo() == null
						|| form.getQtdeReposicaoParalelo().equals("")) {
    				
    				form.setQtdeReposicaoParalelo("0");
    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			} else if (form.getQtdeReposicaoParalelo().equals("0")
						|| form.getQtdeReposicaoParalelo().equals("00")
						|| form.getQtdeReposicaoParalelo().equals("0,00")) {
					ordemServicoBoletim
							.setNumeroReposicaoParalelo(new BigDecimal(0));
					exibirMsgConfirmacao = true;

				} else {
    				
    				ordemServicoBoletim.setNumeroReposicaoParalelo(Util.formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoParalelo()));
    			}
    			
    			
    		}
    		
    		if (form.getExibeQtdeReposicaoCalcada().equals("1")) {
    			
    			if (form.getQtdeReposicaoCalcada() == null
						|| form.getQtdeReposicaoCalcada().equals("")) {
    				
    				form.setQtdeReposicaoCalcada("0");
    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			} else if (form.getQtdeReposicaoCalcada().equals("0")
						|| form.getQtdeReposicaoCalcada().equals("00")
						|| form.getQtdeReposicaoCalcada().equals("0,00")) {
					ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
					exibirMsgConfirmacao = true;
    				
    			} else {
    				
    				ordemServicoBoletim.setNumeroReposicaoCalcada(Util.formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoCalcada()));
    			}
    		}
    	
    		ordemServicoBoletim.setUltimaAlteracao(new Date());
    	}
    	
    	Map retorno = new HashMap();
    	retorno.put("ordemServicoBoletim",ordemServicoBoletim);
    	retorno.put("exibirMsgConfirmacao",exibirMsgConfirmacao);
    	
		return retorno;
	}
    
    private Date validaDataHoraExecucao(EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm, Integer numeroOS, Usuario usuarioLogado){
    	
    	Fachada fachada = Fachada.getInstancia();
    	Date dataExecucao = null;
    	
    	FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento
		.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = fachada
				.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) 
				Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
		if(atendimentoMotivoEncerramento.getIndicadorExecucao() == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO){
			throw new ActionServletException("atencao.msg_personalizada", "N�o � poss�vel informar data e hora de execu��o para o motivo de encerramento selecionado");
		}
    	//Valida hora execucao    	
		if(encerrarOrdemServicoActionForm.getHoraExecucao() == null || encerrarOrdemServicoActionForm.getHoraExecucao().equals(""))
			throw new ActionServletException("atencao.msg_personalizada", "Hora da Execu��o deve ser informada");						
		else{
			String[] horaExec = encerrarOrdemServicoActionForm.getHoraExecucao().split(":");
			if(horaExec.length <=1){
				throw new ActionServletException("atencao.msg_personalizada", "Hora da Execu��o inv�lida");
			}
			Integer hora = new Integer(horaExec[0]);
			Integer min = new Integer(horaExec[1]);
			
			if((hora.compareTo(0) < 0 && hora.compareTo(24) > 0) && (min.compareTo(0) < 0 && min.compareTo(59) > 0))
				throw new ActionServletException("atencao.msg_personalizada", "Hora da Execu��o inv�lida");
		}
		//Valida data Execucao
		if(encerrarOrdemServicoActionForm.getDataExecucao() == null || encerrarOrdemServicoActionForm.getDataExecucao().equals(""))
			throw new ActionServletException("atencao.msg_personalizada", "Data de Execu��o deve ser informada");
		else{									
			OrdemServico osConsultada = fachada.consultarDadosOrdemServico(numeroOS);
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			GregorianCalendar gc = new GregorianCalendar();  
	        gc.setTime(new Date());  
	        gc.set(Calendar.DATE, gc.get(Calendar.DATE) - sistemaParametro.getNumeroDiasEncerramentoOSSeletiva());  
	        gc.getTime();  
			Date dataTeste = null;
			Date dataHoje = null;
			Date dataOs = null;			
			Date dateCompareEncOsSel = null;			
			
			if(!Util.verificaSeDataValida(encerrarOrdemServicoActionForm.getDataExecucao(), "dd/MM/yyyy"))
				throw new ActionServletException("atencao.msg_personalizada", "Data de Execu��o inv�lida");
			try{
				dataTeste = formatador.parse(encerrarOrdemServicoActionForm.getDataExecucao());
				dataOs = formatador.parse(formatador.format(osConsultada.getDataGeracao()));
				dateCompareEncOsSel = formatador.parse(formatador.format(gc.getTime()));
				dataHoje = formatador.parse(formatador.format(new Date()));
			}catch(Exception ex){
				System.out.println("***** Erro no parse de data no Encerramento da OS; Metodo: validaDataHoraExecucao");
			}
			dataExecucao = Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataExecucao() 
				+ " "
				+ encerrarOrdemServicoActionForm.getHoraExecucao() 
				+ ":00");
			
			if(dataTeste.compareTo(dataHoje) > 0)
				throw new ActionServletException("atencao.msg_personalizada", "Data de Execu��o maior que a data corrente");
			
			if(dataTeste.before(dataOs))
				throw new ActionServletException("atencao.msg_personalizada", "N�o � permitido informar Data de Execu��o anterior a Data da Gera��o da OS");
			
			
			if(osConsultada.getRegistroAtendimento() == null && osConsultada.getCobrancaDocumento() == null &&
					dateCompareEncOsSel.compareTo(dataTeste) > 0 && 
					!fachada.verificarPermissaoEspecial(PermissaoEspecial.ENCERRAR_ORDEM_SERVICO_SEM_ATIVIDADES,usuarioLogado))										
				throw new ActionServletException("atencao.msg_personalizada", "Data de Execu��o anterior ao permitido para OS seletiva");
			
		}
		return dataExecucao;
    }
}