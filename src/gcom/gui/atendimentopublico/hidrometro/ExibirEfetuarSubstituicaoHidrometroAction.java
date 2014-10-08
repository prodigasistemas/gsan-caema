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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.FiltroHidrometroSituacao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de efetuar substituição de
 * hidrômetro
 * 
 * @author Ana Maria
 * @date 19/07/2006
 */
public class ExibirEfetuarSubstituicaoHidrometroAction extends GcomAction {
	/**
	 * Este caso de uso permite efetuar a substituição de hidrômetro, sendo
	 * chamado pela funcionalidade que encerra a execução da ordem de serviço ou
	 * chamada diretamente do Menu.
	 * 
	 * [UC0364] Efetuar Substituição de Hidrômetro
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("efetuarSubstituicaoHidrometro");

		EfetuarSubstituicaoHidrometroActionForm form = (EfetuarSubstituicaoHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (form.getVeioEncerrarOS() != null && 
				!form.getVeioEncerrarOS().equals("")) {
				
				if (form.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		this.pesquisarObjetosObrigatorios(httpServletRequest);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
        // Colocado por Vivianne Sousa em 05/12/2007
        // ------------------------------------------------------------
        if(form.getIndicadorTrocaProtecao() == null){
            form.setIndicadorTrocaProtecao(ConstantesSistema.NAO.toString());
        }
        
        if(form.getIndicadorTrocaRegistro() == null){
            form.setIndicadorTrocaRegistro(ConstantesSistema.NAO.toString());
        }
        // ------------------------------------------------------------
        
        if(form.getIndicadorTipoHidrometro() == null || 
        	form.getIndicadorTipoHidrometro().equals("")){		
        	
        	form.setIndicadorTipoHidrometro("2");
		}
        
        /*
		 * author Jonathan Marcos
		 * date 16/01/2014
		 * RM7833
		 * indicador medicao Telemedido
		 */
        if(form.getIndicadorMedicaoTelemedido() == null){
        	form.setIndicadorMedicaoTelemedido(ConstantesSistema.NAO.toString());
        }
        
		String idOrdemServico = null;
		
		if (form.getIdOrdemServico() != null) {
			idOrdemServico = form.getIdOrdemServico();
		
		} else {
			
			idOrdemServico = (String) httpServletRequest.getAttribute("veioEncerrarOS");

			form.setDataInstalacao((String) httpServletRequest.getAttribute("dataEncerramento"));
			form.setDataRetirada((String) httpServletRequest.getAttribute("dataEncerramento"));

			sessao.setAttribute("caminhoRetornoIntegracaoComercial",
			httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null || 
			sessao.getAttribute("semMenu") != null ) {
		
			sessao.setAttribute("semMenu", "SIM");
			
			form.setIndicadorSemMenu("sim");
			
			if (httpServletRequest.getParameter("idCampoEnviarDados") != null ) {
				form.setNumeroHidrometro( "" + httpServletRequest.getParameter("idCampoEnviarDados"));
			}
		} else {
			sessao.removeAttribute("semMenu");
			form.setIndicadorSemMenu("nao");
		}
		
		
		OrdemServico ordemServico = null;
		Imovel imovel = null;
		Imovel imovelComLocalidade = null;
		
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			
			ordemServico = this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));
			
			if (ordemServico != null){
				
				//[FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(),form);
				
				BigDecimal valorDebito = new BigDecimal(0.00);
				
				// Colocado por Raphael Rossiter em 04/05/2007 (Analista:
				// Rosana Carvalho)
				this.calcularValores(httpServletRequest, ordemServico, form);
			
				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = 
					this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------

				if (temPermissaoMotivoNaoCobranca) {
					
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca",temPermissaoMotivoNaoCobranca);
				} else {
					
					form.setPercentualCobranca("100");
					if(form.getQuantidadeParcelas() == null ||
							form.getQuantidadeParcelas().equals("")){
						form.setQuantidadeParcelas("1");
						form.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
					}
				}
				
				if(ordemServico.getImovel() != null){
                    imovel = ordemServico.getImovel();
                }else{
                    imovel = ordemServico.getRegistroAtendimento().getImovel();
                }
				
				if (imovel != null) {
					
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
					
					Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
					
					imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
					
					if (imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null) {
						form.setIdLocalArmazenagem(
							imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId().toString());
					}
				}
				
			}
		}
		
		String tombamento = httpServletRequest.getParameter("tombamento");
		
		if (httpServletRequest.getParameter("pesquisaHidrometro") != null && 
			!httpServletRequest.getParameter("pesquisaHidrometro").equals("")) {
			
			String numeroHidrometro = form.getNumeroHidrometro();

			// Verificar se o número do hidrômetro não está cadastrado
			if (numeroHidrometro != null && !numeroHidrometro.trim().equals("")) {

				// Filtro para descobrir id do Hidrometro
				FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));

				Collection colecaoHidrometro = 
					this.getFachada().pesquisar(filtroHidrometro,Hidrometro.class.getName());

				if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
					form.setNumeroHidrometro("");
					
					throw new ActionServletException("atencao.numero_hidrometro_inexistente", null,numeroHidrometro);
				} else {
					Hidrometro hidrometro = (Hidrometro) Util.retonarObjetoDeColecao(colecaoHidrometro);
					
					if (imovelComLocalidade != null && 
						imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null && 
						!hidrometro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
						
						throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
					}
				}
			}
			
			httpServletRequest.setAttribute("nomeCampo", "localInstalacao");
			
		} else if (tombamento != null && !tombamento.trim().equals("")) {
				
				String numeroTombamento = form.getNumeroTombamento();
				FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
				
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.TOMBAMENTO,numeroTombamento));
				
				filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");		
				filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
				
				Collection colecaoHidrometro = 
					this.getFachada().pesquisar(filtroHidrometro,Hidrometro.class.getName());
		
				
				//[FS0015] - Verificar Situação do Tombamento
				//Caso o tombamento informado não esteja cadastrado
				if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
					form.setNumeroTombamento("");
					throw new ActionServletException("atencao.tombamento_inexistente");
				}else{
					Hidrometro hidro = (Hidrometro) Util.retonarObjetoDeColecao(colecaoHidrometro);
					
					
					//Caso o hidrômetro informado esteja com a situação diferente de DISPONÍVEL
					if(hidro.getHidrometroSituacao().getId().intValue() != HidrometroSituacao.DISPONIVEL.intValue()){
						throw new ActionServletException("atencao.hidrometro_situacao_nao_pode_instalar",
							null,
							hidro.getHidrometroSituacao().getDescricao());
					}
					
					//Caso tenha local de armazenagem na localidade do imóvel e o hidrômetro informado
					//não esteja armazenado no local de instalação da localidade do imóvel onde o mesmo está sendo instalado
					if (imovelComLocalidade != null && 
						imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null && 
						!hidro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
						
						throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
					}
				}
			} else {

			if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

				if (ordemServico != null) {

					httpServletRequest.setAttribute("ordemServicoEncontrado","true");

					this.getFachada().validarExibirSubstituicaoHidrometro(ordemServico,veioEncerrarOS);

					sessao.setAttribute("ordemServico", ordemServico);

					form.setIdOrdemServico(idOrdemServico);
					form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
					form.setVeioEncerrarOS("" + veioEncerrarOS);

					if (ordemServico.getDataEncerramento() != null && 
						!ordemServico.getDataEncerramento().equals("")) {
						
						form.setDataRetirada(Util.formatarData(ordemServico.getDataEncerramento()));
					}else{
						form.setDataRetirada(Util.formatarData(new Date()));
					}
                    
                    if(ordemServico.getImovel() != null){
                        imovel = ordemServico.getImovel();
                    }else{
                        imovel = ordemServico.getRegistroAtendimento().getImovel();
                    }
                    
					String matriculaImovel = null;

					// Matricula Imóvel
					matriculaImovel = imovel.getId().toString();
					form.setMatriculaImovel(matriculaImovel);

					// Inscrição Imóvel
					String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(imovel.getId());
					form.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					form.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					form.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					// Cliente
					this.pesquisarCliente(form);

					FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
					filtroMedicaoHistorico.setCampoOrderBy(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO);

					// Tipo medição - Ligação Água
					if (ordemServico.getRegistroAtendimento() == null ||
						ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoAgua().equals(MedicaoTipo.LIGACAO_AGUA.shortValue())) {
						
						LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
						hidrometroInstalacaoHistorico = ligacaoAgua.getHidrometroInstalacaoHistorico();
						
						form.setTipoMedicao(MedicaoTipo.LIGACAO_AGUA.toString());
						Integer numeroLeitura = this.getFachada().pesquisarNumeroLeituraRetiradaLigacaoAgua(ligacaoAgua.getId());
						
						if (numeroLeitura != null) {
							form.setNumeroLeitura(numeroLeitura.toString());
						} else {
							form.setNumeroLeitura(null);
						}
						sessao.setAttribute("hidrometroSubstituicaoHistorico",ligacaoAgua.getHidrometroInstalacaoHistorico());					
						
						//Caso HIDR_ICMACRO = 1
						if(ordemServico!= null &&
							ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico() != null &&
							ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO &&
								
							hidrometroInstalacaoHistorico.getHidrometro().getIndicadorMacromedidor().intValue() == 1){
							form.setNumeroTombamentoAtual(hidrometroInstalacaoHistorico.getHidrometro().getTombamento());
							
							this.verificarProtecaoHidrometro("1", form.getNumeroTombamentoAtual() , form);
						}

						// Tipo medição- Poço
					} else {
						hidrometroInstalacaoHistorico = imovel.getHidrometroInstalacaoHistorico();
						form.setTipoMedicao(MedicaoTipo.POCO.toString());
						
						Integer numeroLeitura = this.getFachada().pesquisarNumeroLeituraRetiradaImovel(imovel.getId());
						if (numeroLeitura != null) {
							form.setNumeroLeitura(numeroLeitura.toString());
						} else {
							form.setNumeroLeitura(null);
						}
						
						sessao.setAttribute("hidrometroSubstituicaoHistorico",imovel.getHidrometroInstalacaoHistorico());
						
						//Caso HIDR_ICMACRO = 1
						
						if(ordemServico!= null &&
							ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico() != null &&
							ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO &&
							hidrometroInstalacaoHistorico.getHidrometro().getIndicadorMacromedidor().intValue() == 1){
							
							form.setNumeroTombamentoAtual(hidrometroInstalacaoHistorico.getHidrometro().getTombamento());
							
							this.verificarProtecaoHidrometro("1", form.getNumeroTombamentoAtual(), form);
						}
						
					}

					form.setTipoMedicaoAtual(hidrometroInstalacaoHistorico.getMedicaoTipo().getDescricao());
					
					//Caso HIDR_ICMACRO = 2 (Micromedidor)
					if(ordemServico!= null &&
						ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico() != null &&
						ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO &&
						hidrometroInstalacaoHistorico.getHidrometro().getIndicadorMacromedidor().intValue() == 2){
						
						form.setNumeroHidrometroAtual(hidrometroInstalacaoHistorico.getHidrometro().getNumero());
						
						this.verificarProtecaoHidrometro("2", form.getNumeroHidrometroAtual(), form);
					}
					
					//Caso Servico Tipo Seja Instalacao Caixa Protecao
					if(ordemServico!= null &&
							ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico() != null &&
							ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_INSTALACAO_CAIXA_PROTECAO){
						if(hidrometroInstalacaoHistorico.getHidrometro().getIndicadorMacromedidor().intValue() == 2){
							form.setNumeroHidrometroAtual(hidrometroInstalacaoHistorico.getHidrometro().getNumero());
						}else if(hidrometroInstalacaoHistorico.getHidrometro().getIndicadorMacromedidor().intValue() == 1){
							form.setNumeroTombamentoAtual(hidrometroInstalacaoHistorico.getHidrometro().getTombamento());
						}
						
					}
										
					form.setIndicadorMacromedidorAtual(
						hidrometroInstalacaoHistorico.getHidrometro().getIndicadorMacromedidor().toString());

					//Flag que verifica se a veio do reload do combo de situacao de hidrometro
					if(httpServletRequest.getParameter("comboSituacaoHidrometro") == null){
						if (hidrometroInstalacaoHistorico.getHidrometro().getHidrometroSituacao() != null) {
							form.setSituacaoHidrometro(
								hidrometroInstalacaoHistorico.getHidrometro().getHidrometroSituacao().getId().toString());
	
						}
					}
					
					
					// Data recibida da execução da OS
					Date dataInstalacao = ordemServico.getDataEncerramento();
					if (dataInstalacao != null && !dataInstalacao.equals("")) {
						form.setDataInstalacao(Util.formatarData(dataInstalacao));
					}else{
						form.setDataInstalacao(Util.formatarData(new Date()));
					}

					// Preencher dados da Geração
					// this.pesquisarDadosGeracao(efetuarSubstituicaoHidrometroActionForm,
					// ordemServico);
					// Tipo Débito
					if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
						form.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId()+ "");
						form.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao()+ "");
					} else {
						form.setIdTipoDebito("");
						form.setDescricaoTipoDebito("");
					}

					

					SistemaParametro sistemaParametro = 
						this.getFachada().pesquisarParametrosDoSistema();
					
					form.setQtdeMaxParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento()+ "");
					
					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						form.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
					}
					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						form.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
					}

				} else {
					httpServletRequest.setAttribute("ordemServicoEncontrado","exception");
					form.setNomeOrdemServico("Ordem de Serviço inexistente");
					form.setIdOrdemServico("");
				}
			} else {

				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");

				form.setIdLocalArmazenagem("");
				form.setIdOrdemServico("");
				form.setMatriculaImovel("");
				form.setInscricaoImovel("");
				form.setClienteUsuario("");
				form.setCpfCnpjCliente("");
				form.setSituacaoLigacaoAgua("");
				form.setSituacaoLigacaoEsgoto("");
				form.setNomeOrdemServico("");
				form.setIdTipoDebito("");
				form.setDescricaoTipoDebito("");
				form.setQuantidadeParcelas("");
				form.setValorParcelas("");
				form.setPercentualCobranca("-1");
				form.setMotivoNaoCobranca("-1");
				form.setIdTipoDebito("");
				form.setDescricaoTipoDebito("");
				form.setValorDebito("");
			}

		}
		
		//Verificar Proteção do Hidrômetro
		if(ordemServico!= null && 
			ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico() != null &&
			ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO){

			if(form.getNumeroHidrometroAtual() != null && !form.getNumeroHidrometroAtual().equals("")){
				this.verificarProtecaoHidrometro("2", form.getNumeroHidrometroAtual(), form);
			}else if(form.getNumeroTombamentoAtual() != null && !form.getNumeroTombamentoAtual().equals("")){
				this.verificarProtecaoHidrometro("1", form.getNumeroTombamentoAtual(), form);
			}
			
		}
	
		//Parte em que é Verificado se o hidrometro esta extraviado.
		String idSituacaoHidrometro = (String) form.getHidrometroExtraviado();
		if(idSituacaoHidrometro == null || 
			idSituacaoHidrometro.equals("") || 
			idSituacaoHidrometro.equalsIgnoreCase("nao") || 
			idSituacaoHidrometro.equalsIgnoreCase("sim") ){
			
			idSituacaoHidrometro = form.getSituacaoHidrometro();
		}
				
		if(idSituacaoHidrometro!= null && 
			!idSituacaoHidrometro.equals("") && 
			!idSituacaoHidrometro.equals("-1")){
			
			HidrometroSituacao hidrometroSituacao = new HidrometroSituacao(); 
			FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
			filtroHidrometroSituacao.adicionarParametro(new ParametroSimples(FiltroHidrometroSituacao.ID,idSituacaoHidrometro));
			
			Collection colecaoHidrometroSituacao =  
				this.getFachada().pesquisar(filtroHidrometroSituacao,HidrometroSituacao.class.getName());
			
			hidrometroSituacao = (HidrometroSituacao) colecaoHidrometroSituacao.iterator().next();
			
			Integer hidrometroExtraviado = null;
			if(hidrometroSituacao != null){
				hidrometroExtraviado = new Integer(hidrometroSituacao.getExtraviado().toString()); 
			}
			
			// hidrometroExtraviado==1, significa que o hidrometro esta extraviado,
			// como mostra o campo "hist_ichidrometroextraviado" da tabela "micromedicao.hidrometro_situacao".
			if(hidrometroExtraviado.intValue() == HidrometroSituacao.EXTRAVIADO.intValue()){
				httpServletRequest.setAttribute("hidrometroExtravido","sim");
				sessao.setAttribute("hidrometroExtravido","sim");
				form.setHidrometroExtraviado("sim");
			}
			else{
				sessao.removeAttribute("hidrometroExtravido");
				form.setHidrometroExtraviado("nao");
			}
		}
		//Fim da Verificacao se o hidrometro esta extraviado.
		
		
		if(ordemServico!= null &&
			ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico() != null && 
			(ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO || 
			ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_SUBSTITUICAO_COM_REMOCAO)
					){
			
			String situacao = form.getSituacaoHidrometro();
			
			if(situacao != null && situacao.equals(""+HidrometroSituacao.EM_MANUTENCAO)){
			
				// Pesquisando hidrometro local armazenagem
				FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

				filtroHidrometroLocalArmazenagem.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.DESCRICAO);
				filtroHidrometroLocalArmazenagem.adicionarParametro(
					new ParametroSimples(FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroLocalArmazenagem.adicionarParametro(
					new ParametroSimples(FiltroHidrometroLocalArmazenagem.INDICADOR_OFICINA,
						ConstantesSistema.INDICADOR_USO_ATIVO));


				Collection<HidrometroLocalArmazenagem> colecaoHidrometroLocalArmazenagem = 
					this.getFachada().pesquisar(filtroHidrometroLocalArmazenagem,
						HidrometroLocalArmazenagem.class.getName());
				
				HidrometroLocalArmazenagem hidrometroLocalArmazenagem = 
					(HidrometroLocalArmazenagem) Util.retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);

				form.setLocalArmazenagemHidrometro(""+hidrometroLocalArmazenagem.getId());
			}
		}
				
		/**
		 * [SB0009] - Informar instalacao de caixa de protecao do hidrometro
		 * @autor Anderson Cabral
		 * @since 24/01/2013
		 * *  ***/
		if(ordemServico!= null && 
			ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico() != null &&
			ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().intValue() == ServicoTipo.TIPO_INSTALACAO_CAIXA_PROTECAO){
			
			copiarValoresParaHidrometroNovo(form, hidrometroInstalacaoHistorico);
			form.setIndicadorInstCaixaProtecao(ConstantesSistema.SIM.toString());
			
		}else{
			form.setIndicadorInstCaixaProtecao(ConstantesSistema.NAO.toString());
		}
		
		return retorno;
	}
	
	/**
	 * 	Colocar o mesmo valor do hidrometro atual para o NOVO hidrometro
	 * [SB0009] - Informar instalacao de caixa de protecao do hidrometro
	 * 
	 * @autor Anderson Cabral
	 * @since 24/01/2013
	 * *  ***/
	private void copiarValoresParaHidrometroNovo(EfetuarSubstituicaoHidrometroActionForm form, HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico){
		
		form.setIndicadorTipoHidrometro(form.getIndicadorMacromedidorAtual());
		form.setNumeroHidrometro(form.getNumeroHidrometroAtual());
//		form.setLeituraInstalacao(form.getNumeroLeitura());
//		form.setTipoMedicao();
//		form.setDataInstalacao();
		
		if(hidrometroInstalacaoHistorico != null && hidrometroInstalacaoHistorico.getHidrometro() != null){	
			form.setLeituraInstalacao(hidrometroInstalacaoHistorico.getNumeroLeituraInstalacao().toString());
			form.setNumeroSelo(hidrometroInstalacaoHistorico.getNumeroSelo());
			form.setSituacaoCavalete(hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().toString());
		}
	}
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarSubstituicaoHidrometroActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}
		
	}
	
	
	/*
	 * Calcular valor da prestação com juros
	 * 
	 * return: Retorna o valor total do débito
	 * 
	 * autor: Raphael Rossiter
	 * data: 04/05/2007
	 */
	private void calcularValores(HttpServletRequest httpServletRequest, OrdemServico ordemServico,
			EfetuarSubstituicaoHidrometroActionForm form){
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = new Integer(1);
		
		if(form.getQuantidadeParcelas() == null || 
				form.getQuantidadeParcelas().equals(""))
			form.setQuantidadeParcelas(qtdeParcelas.toString());
		
		if(calculaValores != null && calculaValores.equals("S"))
			calcularValorPrestacao(ordemServico, form,
					valorDebito, sistemaParametro);
		
		else if(ordemServico.getRegistroAtendimento() != null){
			
			valorDebito = Fachada.getInstancia().obterValorDebito(ordemServico.getServicoTipo().getId(),
			ordemServico.getRegistroAtendimento().getImovel().getId(),
			new Short(LigacaoTipo.LIGACAO_AGUA + ""));
			
			if (valorDebito != null) {
				form.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
			} else {
				form.setValorDebito("0");
			}
			
			calcularValorPrestacao(ordemServico, form,
					valorDebito, sistemaParametro);
		}
		else{
			form.setValorDebito(Util.formataBigDecimal(new BigDecimal("0"), 2, true));
		}
	}

	private void calcularValorPrestacao(OrdemServico ordemServico,
			EfetuarSubstituicaoHidrometroActionForm form,
			BigDecimal valorDebito, SistemaParametro sistemaParametro) {
		Integer qtdeParcelas;
		{
			
			//[UC0186] - Calcular Prestação
			BigDecimal  taxaJurosFinanciamento = null; 
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());
			
			if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
					qtdeParcelas.intValue() > 1){
				
				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			}else{
				taxaJurosFinanciamento = new BigDecimal(0);
			}
			
			BigDecimal valorPrestacao = null;
			if(taxaJurosFinanciamento != null){
				
				valorDebito = new BigDecimal(form.getValorDebito().replace(",","."));
				
				String percentualCobranca = form.getPercentualCobranca();
				
				if(percentualCobranca != null && percentualCobranca.equals("70")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				}else if (percentualCobranca != null && percentualCobranca.equals("50")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}
				
				valorPrestacao =
					this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento,
						qtdeParcelas, 
						valorDebito, 
						new BigDecimal("0.00"));
				
				valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao,2,true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}						
			
		}
	}
	

	/**
	 * Pesquisa o local de instalacao do hidrometro Pesquisa a protecao do
	 * hidrometro Pesquisa a situacao do hidrometro
	 */
	private void pesquisarObjetosObrigatorios(
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pesquisando local de instalação
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();

		filtroHidrometroLocalInstalacao
				.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
		filtroHidrometroLocalInstalacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroLocalInstalacao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());

		if (colecaoHidrometroLocalInstalacao == null
				|| colecaoHidrometroLocalInstalacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Hidrômetro local de instalação");
		}

		httpServletRequest.setAttribute("colecaoHidrometroLocalInstalacao",
				colecaoHidrometroLocalInstalacao);

		// Pesquisando proteção
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();

		filtroHidrometroProtecao
				.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroProtecao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroProtecao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroProtecao,
						HidrometroProtecao.class.getName());

		if (colecaoHidrometroProtecao == null
				|| colecaoHidrometroProtecao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Hidrômetro proteção");
		}

		httpServletRequest.setAttribute("colecaoHidrometroProtecao",
				colecaoHidrometroProtecao);

		// Pesquisando situação do hidrômetro
		FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
		filtroHidrometroSituacao.adicionarParametro(
				new ParametroSimples(FiltroHidrometroSituacao.INDICADOR_BLOQUEAR_SUBSTITUICAO, ConstantesSistema.NAO));
		filtroHidrometroSituacao
				.setCampoOrderBy(FiltroHidrometroSituacao.DESCRICAO);

		Collection colecaoHidrometroSituacao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroSituacao,
						HidrometroSituacao.class.getName());

		httpServletRequest.setAttribute("colecaoHidrometroSituacao",
				colecaoHidrometroSituacao);

		// Pesquisando hidrometro local armazenagem
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

		filtroHidrometroLocalArmazenagem
				.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.DESCRICAO);
		filtroHidrometroLocalArmazenagem
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroLocalArmazenagem = Fachada.getInstancia()
				.pesquisar(filtroHidrometroLocalArmazenagem,
						HidrometroLocalArmazenagem.class.getName());

		if (colecaoHidrometroLocalArmazenagem == null
				|| colecaoHidrometroLocalArmazenagem.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Local de Armazenagem do Hidrômetro");
		}

		httpServletRequest.setAttribute("colecaoHidrometroLocalArmazenagem",
				colecaoHidrometroLocalArmazenagem);

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = fachada.pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da Não Cobrança");
			}
		}

	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 25/08/2006
	 */
	private void pesquisarCliente(
			EfetuarSubstituicaoHidrometroActionForm efetuarSubstituicaoHidrometroActionForm) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID,
				efetuarSubstituicaoHidrometroActionForm.getMatriculaImovel()));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarSubstituicaoHidrometroActionForm.setClienteUsuario(cliente
					.getNome());
			efetuarSubstituicaoHidrometroActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}
	
	/**
	 * [SB0008] - Informar proteção do hidrômetro
	 * 
	 * @author Davi Menezes
	 * @date 18/09/2012
	 */
	
	public void verificarProtecaoHidrometro(String indicadorMacro, 
		String valor,EfetuarSubstituicaoHidrometroActionForm form){
		
		if(indicadorMacro != null){
			
			form.setHidrometroProtegido("2");
			Hidrometro hidrometro = null;
			if(indicadorMacro.equals("1")){
				hidrometro = this.getFachada().pesquisarHidrometroPeloTombamento(valor);
			}else{
				hidrometro = this.getFachada().pesquisarHidrometroPeloNumero(valor);
			}
			
			
			if(hidrometro != null){
				
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstHist = new FiltroHidrometroInstalacaoHistorico();
				filtroHidrometroInstHist.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.HIDROMETRO_ID, hidrometro.getId()));
				filtroHidrometroInstHist.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometroInstalacaoHistorico.HIDROMETRO_PROTECAO);
				filtroHidrometroInstHist.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometroInstalacaoHistorico.HIDROMETRO_LOCAL_INSTALACAO);
				filtroHidrometroInstHist.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));
				
				Collection<?> colHidrometro = 
					this.getFachada().pesquisar(filtroHidrometroInstHist, HidrometroInstalacaoHistorico.class.getName());
				
				if(!Util.isVazioOrNulo(colHidrometro)){
					
					form.setHidrometroProtegido("1");
					
					HidrometroInstalacaoHistorico hidrometroInstHist = 
						(HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colHidrometro);
					
					form.setProtecao(String.valueOf(hidrometroInstHist.getHidrometroProtecao().getId()));
					form.setLocalInstalacao(String.valueOf(hidrometroInstHist.getHidrometroLocalInstalacao().getId()));
					
					if(hidrometroInstHist.getIndicadorTrocaProtecao() != null){
						form.setIndicadorTrocaProtecao(String.valueOf(hidrometroInstHist.getIndicadorTrocaProtecao()));	
					}else{
						form.setIndicadorTrocaProtecao(ConstantesSistema.NAO.toString());
					}
					
					if(hidrometroInstHist.getIndicadorTrocaRegistro() != null){
						form.setIndicadorTrocaRegistro(String.valueOf(hidrometroInstHist.getIndicadorTrocaRegistro()));
					}else{
						form.setIndicadorTrocaRegistro(ConstantesSistema.NAO.toString());
					}
				}
			}

		}
	}
	
}