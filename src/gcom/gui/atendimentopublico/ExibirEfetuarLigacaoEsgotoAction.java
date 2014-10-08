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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroDiametroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoMaterialEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.ComandoOSConexaoEsgoto;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir bairro
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarLigacaoEsgotoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = 
			actionMapping.findForward("efetuarLigacaoEsgoto");

		EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (EfetuarLigacaoEsgotoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		this.consultaSelectObrigatorio(this.getSessao(httpServletRequest));

		Boolean veioEncerrarOS = null;

		if(this.getSessao(httpServletRequest).getAttribute("resetarForm") != null && 
				this.getSessao(httpServletRequest).getAttribute("resetarForm").equals(ConstantesSistema.SIM)){
			ligacaoEsgotoActionForm.reset();
			this.getSessao(httpServletRequest).removeAttribute("resetarForm");
		}
		
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (ligacaoEsgotoActionForm.getVeioEncerrarOS() != null && 
				ligacaoEsgotoActionForm.getVeioEncerrarOS().equalsIgnoreCase("TRUE")) {
				veioEncerrarOS = Boolean.TRUE;
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		// Permissao Especial Efetuar Ligacao de Esgoto sem RA
		boolean efetuarLigacaoEsgotoSemRA = 
			this.getFachada().verificarPermissaoEspecial(
				PermissaoEspecial.EFETUAR_LIGACAO_DE_ESGOTO_SEM_RA,
					this.getUsuarioLogado(httpServletRequest));

		ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("false");

		if (!veioEncerrarOS) {

			httpServletRequest.setAttribute("efetuarLigacaoEsgotoSemRA",efetuarLigacaoEsgotoSemRA);

			if (efetuarLigacaoEsgotoSemRA) {
				ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("true");
			}
		}

		ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("false");

		if (!veioEncerrarOS) {
			
			httpServletRequest.setAttribute(
				"efetuarLigacaoAguaComInstalacaodeHidrometroSemRA",efetuarLigacaoEsgotoSemRA);

			if (efetuarLigacaoEsgotoSemRA) {
				ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("true");
			}
		}

		String idImovel = ligacaoEsgotoActionForm.getIdImovel();

		if (idImovel != null && !idImovel.trim().equals("")) {
			
			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = this.getFachada().pesquisarInscricaoImovel(new Integer(idImovel));
			
			if (inscricaoImovelEncontrado != null && !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				ligacaoEsgotoActionForm.setMatriculaImovel(idImovel);
				ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovelEncontrado);

				Imovel imovel = (Imovel) this.getFachada().pesquisarDadosImovel(new Integer(idImovel));

				// [FS0002] Validar Situa��o de �gua do Im�vel.
				if (imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.POTENCIAL.intValue() && 
					imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.FACTIVEL.intValue() && 
					imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.EM_FISCALIZACAO.intValue() &&
					imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.FACTIVEL_FATURAVEL.intValue()) {

					throw new ActionServletException(
						"atencao.situacao_validar_ligacao_esgoto_invalida_exibir",
						null, 
						imovel.getLigacaoAguaSituacao().getDescricao());
				}

				/*
				 * [FS0008] Verificar Situa��o Rede de �gua na Quadra
				 * 
				 * Integra��o com o conceito de face da quadra
				 * Raphael Rossiter em 21/05/2009
				 */
				IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
				
				if ((integracao.getIndicadorRedeEsgoto()).equals(Quadra.SEM_REDE)) {
					
					throw new ActionServletException("atencao.percentual_rede_esgoto_quadra", 
					null, imovel.getId() + "");
				}

				// [FS0006] Verificar Situa��o do Imovel
				if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
					
					throw new ActionServletException(
						"situacao_imovel_indicador_exclusao_esgoto", 
						null,
						imovel.getId() + "");
				}

				// Matricula Im�vel
				ligacaoEsgotoActionForm.setMatriculaImovel(imovel.getId().toString());

				// Situa��o da Liga��o de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
				ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situa��o da Liga��o de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				this.pesquisarCliente(ligacaoEsgotoActionForm, imovel.getId());

				if (ligacaoEsgotoActionForm.getPerfilLigacao() != null && 
					!ligacaoEsgotoActionForm.getPerfilLigacao().equals("-1")) {

					FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();
					filtroLigacaoPercentualEsgoto.adicionarParametro(
						new ParametroSimples(
							FiltroLigacaoEsgotoPerfil.ID,
							ligacaoEsgotoActionForm.getPerfilLigacao()));

					Collection colecaoPercentualEsgoto = 
						this.getFachada().pesquisar(
							filtroLigacaoPercentualEsgoto,
							LigacaoEsgotoPerfil.class.getName());

					if (colecaoPercentualEsgoto != null && 
						!colecaoPercentualEsgoto.isEmpty()) {

						LigacaoEsgotoPerfil percentualEsgotoPerfil = 
							(LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();

						String percentualFormatado = 
							percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");

						ligacaoEsgotoActionForm.setPercentualEsgoto(percentualFormatado);
					}
				}
				
				this.validarPermissaoEspecial(
						ligacaoEsgotoActionForm,null,httpServletRequest,false);
				
				if(httpServletRequest.getParameter("refresh") == null){
					this.exibirDadosDaLigacaoEsgoto(ligacaoEsgotoActionForm, imovel);
				}
				
			} else {

				httpServletRequest.setAttribute("corImovel", "exception");
				ligacaoEsgotoActionForm.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		}
		
		ligacaoEsgotoActionForm.setIndicadorCaixaGordura("2");
		ligacaoEsgotoActionForm.setVeioEncerrarOS("" + veioEncerrarOS);
		ligacaoEsgotoActionForm.setIndicadorLigacao("1");

		// Variavel responsav�l pelo preenchimento do imovel no formul�rio
		String idOrdemServico = null;
		//if (ligacaoEsgotoActionForm.getIdOrdemServico() != null) {
		
		if ((httpServletRequest.getAttribute("veioEncerrarOS") == null) || (httpServletRequest.getAttribute("veioEncerrarOS").equals(""))){
			idOrdemServico = ligacaoEsgotoActionForm.getIdOrdemServico();
		} else {
			
			idOrdemServico = (String) httpServletRequest.getAttribute("veioEncerrarOS");
			ligacaoEsgotoActionForm.setDataLigacao((String) httpServletRequest.getAttribute("dataEncerramento"));
			
			this.getSessao(httpServletRequest).setAttribute("caminhoRetornoIntegracaoComercial",
				httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			this.getSessao(httpServletRequest).setAttribute("semMenu", "SIM");
		} else {
			this.getSessao(httpServletRequest).removeAttribute("semMenu");
		}

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(
				new ParametroSimples(
				FiltroOrdemServico.ID, 
				idOrdemServico));

			OrdemServico ordemServico = 
				this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				//Caso OS do tipo ligado decreto, situacao da ligacao de esgoto = factivel faturavel (ligado decreto)
				if ( ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getId() != null &&
						ordemServico.getServicoTipo().getId().equals(ServicoTipo.LIGADO_DECRETO) ) {
					this.getSessao(httpServletRequest).setAttribute("factivelFaturavel", true);
					/*
					 * RM9021 - Carta de obrigatoriedade de conexao de esgoto
					 * Selecionar dados da ligacao de esgoto do comando conexao esgoto
					 * Ana Maria em 21/08/2013
					 */
					if(ordemServico.getComandoOSConexaoEsgoto() != null && !ordemServico.getComandoOSConexaoEsgoto().equals("")){
						if(httpServletRequest.getParameter("refresh") == null){
							ComandoOSConexaoEsgoto comandoOSConexaoEsgoto = fachada.obterDadosComandoOSConexaoEsgoto(ordemServico.getComandoOSConexaoEsgoto().getId());
							if(comandoOSConexaoEsgoto != null && !comandoOSConexaoEsgoto.equals("")){
								this.exibirDadosDaLigacaoEsgotoComando(ligacaoEsgotoActionForm, comandoOSConexaoEsgoto);
							}
						}
					}
				} else {
					this.getSessao(httpServletRequest).removeAttribute("factivelFaturavel");
				}
				this.getFachada().validarLigacaoEsgotoExibir(ordemServico,veioEncerrarOS);
				this.getSessao(httpServletRequest).setAttribute("ordemServico", ordemServico);

				ligacaoEsgotoActionForm.setIdOrdemServico(idOrdemServico);
				ligacaoEsgotoActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());

				Imovel imovel = null;
				if(ordemServico.getRegistroAtendimento() != null){
					imovel = ordemServico.getRegistroAtendimento().getImovel();
				}else{
					imovel = ordemServico.getImovel();
				}
				
				this.getSessao(httpServletRequest).setAttribute("imovel", imovel);

				String matriculaImovel = imovel.getId().toString();
				Integer imovelId = Integer.parseInt(matriculaImovel);
				Integer clienteDesconhecido = Fachada.getInstancia().pesquisarClienteDesconhecidoAssociadoImovel(imovelId);

				if (clienteDesconhecido != null) {
					throw new ActionServletException("atencao.nao.permitido.ligacao.agua",	null, imovel.getId().toString());
				}

				if (imovel != null) {

					// Matricula Im�vel
					ligacaoEsgotoActionForm.setMatriculaImovel(imovel.getId().toString());

					// Inscri��o Im�vel
					String inscricaoImovel = 
						this.getFachada().pesquisarInscricaoImovel(imovel.getId());

					ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);

					// Situa��o da Liga��o de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(ligacaoEsgotoActionForm, new Integer(matriculaImovel));
					
					this.validarPermissaoEspecial(
						ligacaoEsgotoActionForm,null,httpServletRequest,false);
					
					if(httpServletRequest.getParameter("refresh") == null){
						this.exibirDadosDaLigacaoEsgoto(ligacaoEsgotoActionForm, imovel);
					}
				}

				if (ordemServico.getServicoTipo() != null && 
					ordemServico.getServicoTipo().getDebitoTipo() != null) {
					
					ligacaoEsgotoActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
					ligacaoEsgotoActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());
				} else {
					ligacaoEsgotoActionForm.setIdTipoDebito("");
					ligacaoEsgotoActionForm.setDescricaoTipoDebito("");
				}

				// [FS0013] - Altera��o de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(),ligacaoEsgotoActionForm);
				String calculaValores = httpServletRequest.getParameter("calculaValores");

				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				
				Integer qtdeParcelas = new Integer(1);
				if(ligacaoEsgotoActionForm.getQuantidadeParcelas() == null || 
						ligacaoEsgotoActionForm.getQuantidadeParcelas().equals(""))
					ligacaoEsgotoActionForm.setQuantidadeParcelas(qtdeParcelas.toString());
				
				BigDecimal valorDebito = new BigDecimal(0);

				if (calculaValores != null && calculaValores.equals("S"))
					calcularValorPrestacao(
							ligacaoEsgotoActionForm, ordemServico,
							sistemaParametro, valorDebito);
				else {

					valorDebito = 
						this.getFachada().obterValorDebito(
							ordemServico.getServicoTipo().getId(), 
							imovel.getId(),
							new Short(LigacaoTipo.LIGACAO_AGUA + ""));
					
					ligacaoEsgotoActionForm.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
					
					calcularValorPrestacao(
							ligacaoEsgotoActionForm, ordemServico,
							sistemaParametro, valorDebito);
				}

				if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
					ligacaoEsgotoActionForm.setMotivoNaoCobranca(
						ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
				}

				if (ordemServico.getPercentualCobranca() != null) {
					ligacaoEsgotoActionForm.setPercentualCobranca(
						ordemServico.getPercentualCobranca().toString());
				}

				if (ordemServico.getDataEncerramento() != null) {
					ligacaoEsgotoActionForm.setDataLigacao(
						Util.formatarData(ordemServico.getDataEncerramento()));
				}

				// Inscri��o do Imov�l
				String inscricaoImovel = imovel.getInscricaoFormatada();

				ligacaoEsgotoActionForm.setMatriculaImovel(matriculaImovel);
				ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);

				// Situa��o da Liga��o de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
						.getDescricao();
				ligacaoEsgotoActionForm
						.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				this.getSessao(httpServletRequest).setAttribute(
					"ligacaoAguaSituacao", imovel.getLigacaoAguaSituacao());

				// Situa��o da Liga��o de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				this.pesquisarCliente(ligacaoEsgotoActionForm, new Integer(matriculaImovel));

				/*-------------------- Dados da Liga��o ----------------------------*/

				this.consultaSelectObrigatorio(this.getSessao(httpServletRequest));

				// Carregando campo Percentual de Esgoto
				// Item 4.6
				if (ligacaoEsgotoActionForm.getPerfilLigacao() != null && 
					!ligacaoEsgotoActionForm.getPerfilLigacao().equals("")) {

					FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();

					filtroLigacaoPercentualEsgoto.adicionarParametro(
						new ParametroSimples(
							FiltroLigacaoEsgotoPerfil.ID,
							ligacaoEsgotoActionForm.getPerfilLigacao()));

					Collection colecaoPercentualEsgoto = 
						this.getFachada().pesquisar(
							filtroLigacaoPercentualEsgoto,
							LigacaoEsgotoPerfil.class.getName());

					if (colecaoPercentualEsgoto != null && 
						!colecaoPercentualEsgoto.isEmpty()) {

						LigacaoEsgotoPerfil percentualEsgotoPerfil = 
							(LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();

						String percentualFormatado = 
							percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");

						ligacaoEsgotoActionForm.setPercentualEsgoto(percentualFormatado);
					}
				}

				this.validarPermissaoEspecial(ligacaoEsgotoActionForm,valorDebito,httpServletRequest,true);

			} else {
				ligacaoEsgotoActionForm.setNomeOrdemServico("Ordem de Servi�o inexistente");
				ligacaoEsgotoActionForm.setIdOrdemServico("");
				httpServletRequest.setAttribute("OrdemServioInexistente", true);
			}

		} 
		
		return retorno;
	}

	private void calcularValorPrestacao(
			EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm,
			OrdemServico ordemServico, SistemaParametro sistemaParametro,
			BigDecimal valorDebito) {
		Integer qtdeParcelas;
		{

			// [UC0186] - Calcular Presta��o
			BigDecimal taxaJurosFinanciamento = null;
			qtdeParcelas = new Integer(ligacaoEsgotoActionForm.getQuantidadeParcelas());

			if (ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
				qtdeParcelas.intValue() != 1) {

				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			} else {
				taxaJurosFinanciamento = new BigDecimal(0);
			}

			BigDecimal valorPrestacao = null;
			if (taxaJurosFinanciamento != null) {

				valorDebito = 
					new BigDecimal(ligacaoEsgotoActionForm.getValorDebito().replace(",", "."));

				String percentualCobranca = ligacaoEsgotoActionForm.getPercentualCobranca();

				if (percentualCobranca != null && percentualCobranca.equals("70")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				} else if (percentualCobranca != null && percentualCobranca.equals("50")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}

				valorPrestacao = 
					this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento, 
						qtdeParcelas,
						valorDebito, 
						new BigDecimal("0.00"));

				valorPrestacao.setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = 
					Util.formataBigDecimal(valorPrestacao, 2, true);
				
				ligacaoEsgotoActionForm.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				ligacaoEsgotoActionForm.setValorParcelas("0,00");
			}

		}
	}
	
	/*
	 * Validar os campos apatir da permiss�o especial
	 * 
	 * autor: Rafael Pinto
	 */
	private void validarPermissaoEspecial(EfetuarLigacaoEsgotoActionForm form,
		BigDecimal valorDebito,HttpServletRequest httpServletRequest,boolean validaMotivoNaoCobranca){

		boolean alterarPercentualColetaEsgoto = 
			this.getFachada().verificarPermissaoEspecial(
				PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO,
				this.getUsuarioLogado(httpServletRequest));

		if (alterarPercentualColetaEsgoto) {
			httpServletRequest.setAttribute("alterarPercentualColetaEsgoto",alterarPercentualColetaEsgoto);
		} 
			
		if(!alterarPercentualColetaEsgoto || form.getPercentualColeta() == null || form.getPercentualColeta().equals("") ){
			form.setPercentualColeta("100,00");
		}
		
		if(validaMotivoNaoCobranca){
			boolean temPermissaoMotivoNaoCobranca = 
				this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(
					this.getUsuarioLogado(httpServletRequest));

			if (temPermissaoMotivoNaoCobranca) {
				httpServletRequest.setAttribute(
					"permissaoMotivoNaoCobranca",temPermissaoMotivoNaoCobranca);
			} else {
				form.setPercentualCobranca("100");
			}

			if (temPermissaoMotivoNaoCobranca) {
				form.setPermissaoMotivoNaoCobranca("true");
			}
		}

	}
	

	/*
	 * [FS0013 - Altera��o de Valor]
	 * 
	 * autor: Raphael Rossiter data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo,
			EfetuarLigacaoEsgotoActionForm form) {

		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO
				.shortValue()) {

			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}

	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(
			EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

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
			ligacaoEsgotoActionForm.setClienteUsuario(cliente.getNome());
			ligacaoEsgotoActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Monta os select�s obrigatorios
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void consultaSelectObrigatorio(HttpSession sessao) {
		
		// Filtro para o campo Diametro Liga��o �gua
		Collection colecaoDiametroLigacao = (Collection) sessao.getAttribute("colecaoDiametroLigacaoAgua");
		
		if (colecaoDiametroLigacao == null){

			FiltroDiametroLigacaoEsgoto filtroDiametroLigacaoEsgoto = new FiltroDiametroLigacaoEsgoto();

			filtroDiametroLigacaoEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroDiametroLigacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDiametroLigacaoEsgoto
					.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoDiametroLigacao = this.getFachada().pesquisar(
					filtroDiametroLigacaoEsgoto, LigacaoEsgotoDiametro.class
							.getName());

			if (colecaoDiametroLigacao != null
					&& !colecaoDiametroLigacao.isEmpty()) {
				sessao.setAttribute("colecaoDiametroLigacao",
						colecaoDiametroLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Diametro da Liga��o");
			}
		}

		// Filtro para o campo Material da Liga��o
		Collection colecaoMaterialLigacao = (Collection) sessao
				.getAttribute("colecaoMaterialLigacao");

		if (colecaoMaterialLigacao == null) {

			FiltroLigacaoMaterialEsgoto filtroLigacaoMaterialEsgoto = new FiltroLigacaoMaterialEsgoto();
			filtroLigacaoMaterialEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoMaterialEsgoto.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoMaterialEsgoto
					.setCampoOrderBy(FiltroLigacaoMaterialEsgoto.DESCRICAO);

			colecaoMaterialLigacao = this.getFachada().pesquisar(
					filtroLigacaoMaterialEsgoto, LigacaoEsgotoMaterial.class
							.getName());

			if (colecaoMaterialLigacao != null
					&& !colecaoMaterialLigacao.isEmpty()) {
				sessao.setAttribute("colecaoMaterialLigacao",
						colecaoMaterialLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Liga��o");
			}
		}

		// Filtro para o campo Perfil da Liga��o
		Collection colecaoPerfilLigacao = (Collection) sessao
				.getAttribute("colecaoPerfilLigacao");

		if (colecaoPerfilLigacao == null) {

			FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoPerfil
					.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.DESCRICAO);

			colecaoPerfilLigacao = this.getFachada().pesquisar(filtroLigacaoEsgotoPerfil,
					LigacaoEsgotoPerfil.class.getName());

			if (colecaoPerfilLigacao != null && !colecaoPerfilLigacao.isEmpty()) {
				sessao.setAttribute("colecaoPerfilLigacao",
						colecaoPerfilLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Perfil de Liga��o");
			}
		}

		// Filtro para o campo Motivo nao cobranca
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = this.getFachada().pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da N�o Cobran�a");
			}
		}

		// Filtro para o campo liga��o esgotamento
		Collection colecaoLigacaoEsgotoEsgotamento = (Collection) sessao.getAttribute("colecaoLigacaoEsgotoEsgotamento");

		if (colecaoLigacaoEsgotoEsgotamento == null){

			FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
			filtroLigacaoEsgotoEsgotamento
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoEsgotamento
					.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);

			colecaoLigacaoEsgotoEsgotamento = this.getFachada().pesquisar(
					filtroLigacaoEsgotoEsgotamento,
					LigacaoEsgotoEsgotamento.class.getName());

			if (colecaoLigacaoEsgotoEsgotamento != null
					&& !colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoEsgotoEsgotamento",
						colecaoLigacaoEsgotoEsgotamento);
			}
		}

		// Filtro para o campo destino dos dejetos
		Collection colecaoDestinoDejetos = (Collection) sessao
				.getAttribute("colecaoDestinoDejetos");

		if (colecaoDestinoDejetos == null) {

			FiltroLigacaoEsgotoDestinoDejetos filtroDestinoDejetos = new FiltroLigacaoEsgotoDestinoDejetos();
			filtroDestinoDejetos.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoDejetos.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoDejetos
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoDejetos.DESCRICAO);

			colecaoDestinoDejetos = this.getFachada().pesquisar(filtroDestinoDejetos,
					LigacaoEsgotoDestinoDejetos.class.getName());

			if (colecaoDestinoDejetos != null
					&& !colecaoDestinoDejetos.isEmpty()) {
				sessao.setAttribute("colecaoDestinoDejetos",
						colecaoDestinoDejetos);
			}
		}

		// Filtro para o campo caixa de inspe��o
		Collection colecaoSituacaoCaixaInspecao = (Collection) sessao.getAttribute("colecaoSituacaoCaixaInspecao");

		if (colecaoSituacaoCaixaInspecao == null) {

			FiltroLigacaoEsgotoCaixaInspecao filtroSituacaoCaixaInspecao = new FiltroLigacaoEsgotoCaixaInspecao();
			filtroSituacaoCaixaInspecao
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoCaixaInspecao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSituacaoCaixaInspecao
					.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			colecaoSituacaoCaixaInspecao = this.getFachada().pesquisar(
					filtroSituacaoCaixaInspecao,
					LigacaoEsgotoCaixaInspecao.class.getName());

			if (colecaoSituacaoCaixaInspecao != null
					&& !colecaoSituacaoCaixaInspecao.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoCaixaInspecao",
						colecaoSituacaoCaixaInspecao);
			}
		}

		// Filtro para o campo destino caixas pluviais
		Collection colecaoDestinoAguasPluviais = (Collection) sessao
				.getAttribute("colecaoDestinoAguasPluviais");

		if (colecaoDestinoAguasPluviais == null) {

			FiltroLigacaoEsgotoDestinoAguasPluviais filtroDestinoAguasPluviais = new FiltroLigacaoEsgotoDestinoAguasPluviais();
			filtroDestinoAguasPluviais.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoAguasPluviais.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoAguasPluviais
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoAguasPluviais.DESCRICAO);

			colecaoDestinoAguasPluviais = this.getFachada().pesquisar(
					filtroDestinoAguasPluviais,
					LigacaoEsgotoDestinoAguasPluviais.class.getName());

			if (colecaoDestinoAguasPluviais != null
					&& !colecaoDestinoAguasPluviais.isEmpty()) {
				sessao.setAttribute("colecaoDestinoAguasPluviais",
						colecaoDestinoAguasPluviais);
			}
		}

		// Filtro para o campo Ligacao origem
		Collection colecaoLigacaoOrigem = (Collection) sessao
				.getAttribute("colecaoLigacaoOrigem");

		if (colecaoLigacaoOrigem == null) {

			FiltroLigacaoOrigem filtroLigacaoOrigem = new FiltroLigacaoOrigem();

			filtroLigacaoOrigem.adicionarParametro(new ParametroSimples(
					FiltroLigacaoOrigem.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoOrigem.setCampoOrderBy(FiltroLigacaoOrigem.DESCRICAO);

			colecaoLigacaoOrigem = this.getFachada().pesquisar(filtroLigacaoOrigem,
					LigacaoOrigem.class.getName());

			if (colecaoLigacaoOrigem != null && !colecaoLigacaoOrigem.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoOrigem",
						colecaoLigacaoOrigem);
			} else {
				sessao.setAttribute("colecaoLigacaoOrigem", new ArrayList());
			}
		}
	}
	
	//Exibi os dados da ligacao, quando o imovel tem ligacao de esgoto (Factivel Faturavel)
	private void exibirDadosDaLigacaoEsgoto(EfetuarLigacaoEsgotoActionForm form, Imovel imovel){
		
		if(imovel.getLigacaoEsgoto() != null){
			
			//Diametro Ligacao
			if(imovel.getLigacaoEsgoto().getLigacaoEsgotoDiametro() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoEsgotoDiametro().getId() != null){
				form.setDiametroLigacao(imovel.getLigacaoEsgoto().getLigacaoEsgotoDiametro().getId().toString());
			}
			
			//Material de Ligacao
			if(imovel.getLigacaoEsgoto().getLigacaoEsgotoMaterial() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoEsgotoMaterial().getId() != null){
				form.setMaterialLigacao(imovel.getLigacaoEsgoto().getLigacaoEsgotoMaterial().getId().toString());
			}
			
			//Perfil de Ligacao
			if(imovel.getLigacaoEsgoto().getLigacaoEsgotoPerfil() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoEsgotoPerfil().getId() != null){
				form.setPerfilLigacao(imovel.getLigacaoEsgoto().getLigacaoEsgotoPerfil().getId().toString());
			}
			
			//Percentual de Esgoto
			if(imovel.getLigacaoEsgoto().getPercentual() != null){
				form.setPercentualEsgoto(imovel.getLigacaoEsgoto().getPercentual().toString());
			}
			
			//Ligacao Origem
			if(imovel.getLigacaoEsgoto().getLigacaoOrigem() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoOrigem().getId() != null){
				form.setIdLigacaoOrigem(imovel.getLigacaoEsgoto().getLigacaoOrigem().getId().toString());
			}
			
			//Indicador Caixa Gordura
			if(imovel.getLigacaoEsgoto().getIndicadorCaixaGordura() != null){
				form.setIndicadorCaixaGordura(imovel.getLigacaoEsgoto().getIndicadorCaixaGordura().toString());
			}
			
			//Indicador Ligacao Esgoto
			if(imovel.getLigacaoEsgoto().getIndicadorLigacaoEsgoto() != null){
				form.setIndicadorLigacao(imovel.getLigacaoEsgoto().getIndicadorLigacaoEsgoto().toString());
			}
			
			//Condicao Esgotamento
			if(imovel.getLigacaoEsgoto().getLigacaoEsgotoEsgotamento() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoEsgotoEsgotamento().getId() != null){
				form.setCondicaoEsgotamento(imovel.getLigacaoEsgoto().getLigacaoEsgotoEsgotamento().getId().toString());
			}
			
			//Caixa de Inspecao
			if(imovel.getLigacaoEsgoto().getLigacaoEsgotoCaixaInspecao() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoEsgotoCaixaInspecao().getId() != null){
				form.setSituacaoCaixaInspecao(imovel.getLigacaoEsgoto().getLigacaoEsgotoCaixaInspecao().getId().toString());
			}
			
			//Destino Dejetos
			if(imovel.getLigacaoEsgoto().getLigacaoEsgotoDestinoDejetos() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoEsgotoDestinoDejetos().getId() != null){
				form.setDestinoDejetos(imovel.getLigacaoEsgoto().getLigacaoEsgotoDestinoDejetos().getId().toString());
			}
			
			//Destino Aguas Pluviais
			if(imovel.getLigacaoEsgoto().getLigacaoEsgotoDestinoAguasPluviais() != null 
					&& imovel.getLigacaoEsgoto().getLigacaoEsgotoDestinoAguasPluviais().getId() != null){
				form.setDestinoAguasPluviais(imovel.getLigacaoEsgoto().getLigacaoEsgotoDestinoAguasPluviais().getId().toString());
			}
		}
	}
	
	/*
	 * RM9021 - Carta de obrigatoriedade de conexao de esgoto
	 * Exibir dados da ligacao, quando tiver comando de conexao de esgoto para OS
	 * Ana Maria em 21/08/2013
	 */
	private void exibirDadosDaLigacaoEsgotoComando(EfetuarLigacaoEsgotoActionForm form, ComandoOSConexaoEsgoto comandoOSConexaoEsgoto){
			
		//Diametro Ligacao
		if(comandoOSConexaoEsgoto.getDiametroLigacaoEsgoto() != null 
				&& comandoOSConexaoEsgoto.getDiametroLigacaoEsgoto().getId() != null){
			form.setDiametroLigacao(comandoOSConexaoEsgoto.getDiametroLigacaoEsgoto().getId().toString());
		}
		
		//Material de Ligacao
		if(comandoOSConexaoEsgoto.getMaterialLigacaoEsgoto() != null 
				&& comandoOSConexaoEsgoto.getMaterialLigacaoEsgoto().getId() != null){
			form.setMaterialLigacao(comandoOSConexaoEsgoto.getMaterialLigacaoEsgoto().getId().toString());
		}
		
		//Perfil de Ligacao
		if(comandoOSConexaoEsgoto.getPerfilLigacaoEsgoto() != null 
				&& comandoOSConexaoEsgoto.getPerfilLigacaoEsgoto().getId() != null){
			form.setPerfilLigacao(comandoOSConexaoEsgoto.getPerfilLigacaoEsgoto().getId().toString());
		}
		
		//Percentual de Esgoto
		if(comandoOSConexaoEsgoto.getPercentualEsgoto() != null){
			form.setPercentualEsgoto(comandoOSConexaoEsgoto.getPercentualEsgoto().toString());
		}
		
		//Percentual de Coleta
		if(comandoOSConexaoEsgoto.getPercentualColeta() != null){
			form.setPercentualColeta(comandoOSConexaoEsgoto.getPercentualColeta().toString());
		}
		
		//Ligacao Origem
		if(comandoOSConexaoEsgoto.getOrigemLigacao() != null 
				&& comandoOSConexaoEsgoto.getOrigemLigacao().getId() != null){
			form.setIdLigacaoOrigem(comandoOSConexaoEsgoto.getOrigemLigacao().getId().toString());
		}
		
		//Indicador Caixa Gordura
		if(comandoOSConexaoEsgoto.getIndicadorCaixaGordura() != null){
			form.setIndicadorCaixaGordura(comandoOSConexaoEsgoto.getIndicadorCaixaGordura().toString());
		}
		
		//Indicador Ligacao Esgoto
		if(comandoOSConexaoEsgoto.getIndicadorLigacaoEsgoto() != null){
			form.setIndicadorLigacao(comandoOSConexaoEsgoto.getIndicadorLigacaoEsgoto().toString());
		}
		
		//Condicao Esgotamento
		if(comandoOSConexaoEsgoto.getCondicaoEsgotamento() != null 
				&& comandoOSConexaoEsgoto.getCondicaoEsgotamento().getId() != null){
			form.setCondicaoEsgotamento(comandoOSConexaoEsgoto.getCondicaoEsgotamento().getId().toString());
		}
		
		//Caixa de Inspecao
		if(comandoOSConexaoEsgoto.getSituacaoCaixaInspecao() != null 
				&& comandoOSConexaoEsgoto.getSituacaoCaixaInspecao().getId() != null){
			form.setSituacaoCaixaInspecao(comandoOSConexaoEsgoto.getSituacaoCaixaInspecao().getId().toString());
		}
		
		//Destino Dejetos
		if(comandoOSConexaoEsgoto.getDestinoDejetos() != null 
				&& comandoOSConexaoEsgoto.getDestinoDejetos().getId() != null){
			form.setDestinoDejetos(comandoOSConexaoEsgoto.getDestinoDejetos().getId().toString());
		}
		
		//Destino Aguas Pluviais
		if(comandoOSConexaoEsgoto.getDestinoAguasPluviais() != null 
				&& comandoOSConexaoEsgoto.getDestinoAguasPluviais().getId() != null){
			form.setDestinoAguasPluviais(comandoOSConexaoEsgoto.getDestinoAguasPluviais().getId().toString());
		}
	}
}