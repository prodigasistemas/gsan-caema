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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoGeral;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroLocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoDevolucaoValores;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoDevolucaoValores;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoDevolucaoValoresPK;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.PesquisarDocumentosHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.integracao.GisHelper;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da Atualização de um R.A (Aba nº 02 - Dados do
 * local de ocorrência)
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class ExibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction
		extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = 
			actionMapping.findForward("atualizarRegistroAtendimentoDadosLocalOcorrencia");

		// Contas selecionadas
		ArrayList<String> listContas = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		 /*
         * @author Jonathan Marcos
         * @date 27/02/2014
         * RM10257
         * [OBSERVACOES]
         * 		- PESQUISAR O IMOVEL E VERIFICAR SE O 
         * 		  IMOVEL POSSUI COORDENADAS X E Y
         */
        RegistroAtendimento RACoordenadasXY = null;
        if(atualizarRegistroAtendimentoActionForm.getIdImovel()!=null && !atualizarRegistroAtendimentoActionForm.getIdImovel().trim().equals("")){
        	RACoordenadasXY = fachada.pesquisarRegistroAtendimentoPorRA((Integer.valueOf(atualizarRegistroAtendimentoActionForm.getNumeroRA())));
        	
        	if(RACoordenadasXY.getNnCoordenadaNorte()!=null){
        		atualizarRegistroAtendimentoActionForm.setNnCoordenadaNorte(RACoordenadasXY.getNnCoordenadaNorte().toString());
        	}
        	if(RACoordenadasXY.getNnCoordenadaLeste()!=null){
        		atualizarRegistroAtendimentoActionForm.setNnCoordenadaLeste(RACoordenadasXY.getNnCoordenadaLeste().toString());
        	}
        }
        
        /*
         * @author Jonathan Marcos
         * @date 27/02/2014
         * RM10257
         * [OBSERVACOES]
         * 		- VERIFICAR SE O USUARIO LOGADO POSSUI 
         * 		  PERMISSAO ESPECIAL ID 147 PARA ALTERAR
         * 		  AS COORDENADAS X E Y
         */
       	if(fachada.verificarPermissaoEspecial(PermissaoEspecial.DIGITAR_COORDENADAS_IMOVEL, usuarioLogado)){
           	sessao.setAttribute("permissaoEspecialDigitarCoordenadasImovel", true);
         }else{
        	 sessao.removeAttribute("permissaoEspecialDigitarCoordenadasImovel");
         }
       
        /*
		 * GIS
		 * ==============================================================================================================	
		 */
		sessao.setAttribute("gis",true);		
		
		GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");	
		
		if(gisHelper!= null){					
		   carregarDadosGis(gisHelper,atualizarRegistroAtendimentoActionForm,sessao);
		}		
		
		/*
		 * Carregamento inicial da tela responsável pelo recebimento das
		 * informações referentes ao local da ocorrência (ABA Nº 02)
		 * ============================================================================================================
		 */

		List<RegistroAtendimentoConta> colecaoRAContasAtualizar = new ArrayList();
		List<RegistroAtendimentoConta> colecaoRAContasRemover = new ArrayList();
		
		if (sessao.getAttribute("colecaoRAContasAtualizar") != null && 
			!sessao.getAttribute("colecaoRAContasAtualizar").equals("") && 
			(httpServletRequest.getParameter("destino") == null || !httpServletRequest.getParameter("destino").equals("2"))){
			
			colecaoRAContasAtualizar = (List<RegistroAtendimentoConta>) sessao.getAttribute("colecaoRAContasAtualizar");
		} else {
			
			// Dados das Contas relacionados
			// Mariana Victor em 28/01/2011
			FiltroRegistroAtendimentoConta filtroRegistroAtendimentoConta = new FiltroRegistroAtendimentoConta();
			
			filtroRegistroAtendimentoConta.adicionarParametro(
				new ParametroSimples(
					FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO_ID,
					atualizarRegistroAtendimentoActionForm.getNumeroRA()));

			filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
				FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO);
			filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
					FiltroRegistroAtendimentoConta.CONTA_GERAL);
			
			colecaoRAContasAtualizar = 
				(List<RegistroAtendimentoConta>) this.getFachada().pesquisar(
					filtroRegistroAtendimentoConta, RegistroAtendimentoConta.class.getName());
			
				
				
				if (colecaoRAContasAtualizar != null && !colecaoRAContasAtualizar.isEmpty()) {
					
					
					Iterator it = colecaoRAContasAtualizar.iterator();
					
					while(it.hasNext()){
						
						RegistroAtendimentoConta registroAtendimento = (RegistroAtendimentoConta) it.next();
					
						if (registroAtendimento != null && registroAtendimento.getContaGeral() != null && 
								registroAtendimento.getContaGeral().getIndicadorHistorico() == ConstantesSistema.NAO){
							
							FiltroConta filtroConta =  new FiltroConta();
							filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, registroAtendimento.getContaGeral().getId()));
							
							Collection colecaoConta = this.getFachada().pesquisar(filtroConta, Conta.class.getName());
							Conta conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);
							registroAtendimento.getContaGeral().setConta(conta);
						}
						else{
							if (registroAtendimento != null && registroAtendimento.getContaGeral() != null){
								
								FiltroContaHistorico filtroContaHistorico =  new FiltroContaHistorico();
								filtroContaHistorico.adicionarParametro(new ParametroSimples(
								FiltroContaHistorico.ID, registroAtendimento.getContaGeral().getId()));
								
								Collection colecaoContaHistorico = this.getFachada().
								pesquisar(filtroContaHistorico,ContaHistorico.class.getName());
								
								ContaHistorico contaHistorico = (ContaHistorico) Util.retonarObjetoDeColecao(colecaoContaHistorico);
								registroAtendimento.getContaGeral().setContaHistorico(contaHistorico);
							}
						}
					}
					
					
					sessao.setAttribute("colecaoRAContasAtualizar", colecaoRAContasAtualizar);
					
					// Caso seja a primeira vez ao entrar na tela, seta contasselecionadas
					ArrayList<String> contas = (ArrayList<String>) sessao.getAttribute("contasSelecionadas");
					if(contas!=null){
						if(contas.size()==0){
							ArrayList<String> idContasSelecionadas = new ArrayList<String>();
							for(int i =0;i<colecaoRAContasAtualizar.size();i++){
								idContasSelecionadas.add(colecaoRAContasAtualizar.get(i).getContaGeral().getId().toString());
							}
							sessao.setAttribute("contasSelecionadas", idContasSelecionadas);
						}
					}else{
						ArrayList<String> idContasSelecionadas = new ArrayList<String>();
						for(int i =0;i<colecaoRAContasAtualizar.size();i++){
							idContasSelecionadas.add(colecaoRAContasAtualizar.get(i).getContaGeral().getId().toString());
						}
						sessao.setAttribute("contasSelecionadas", idContasSelecionadas);
					}
				} else {
					sessao.removeAttribute("colecaoRAContasAtualizar");
				}			
		}

		if (sessao.getAttribute("colecaoRAContasRemover") != null && 
			!sessao.getAttribute("colecaoRAContasRemover").equals("")){
			
			colecaoRAContasRemover = (List<RegistroAtendimentoConta>) sessao.getAttribute("colecaoRAContasRemover");
		}


		
		/*
		 * Adicionar endereço
		 */
		String adicionarEndereco = httpServletRequest.getParameter("tipoPesquisaEndereco");

		if (adicionarEndereco != null && !adicionarEndereco.trim().equalsIgnoreCase("")) {
			retorno = actionMapping.findForward("informarEndereco");
		} else {

			/*
			 * Divisão de Esgoto - Carregando a coleção que irá ficar disponível
			 * para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoDivisaoEsgoto = (Collection) sessao.getAttribute("colecaoDivisaoEsgoto");

			if (colecaoDivisaoEsgoto == null) {

				FiltroDivisaoEsgoto filtroDivisaoEsgoto = 
					new FiltroDivisaoEsgoto(FiltroDivisaoEsgoto.DESCRICAO);

				filtroDivisaoEsgoto.adicionarParametro(
					new ParametroSimples(
						FiltroDivisaoEsgoto.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroDivisaoEsgoto.setConsultaSemLimites(true);

				colecaoDivisaoEsgoto = 
					this.getFachada().pesquisar(
						filtroDivisaoEsgoto,
						DivisaoEsgoto.class.getName());

				if (colecaoDivisaoEsgoto == null || 
					colecaoDivisaoEsgoto.isEmpty()) {
					
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"DIVISAO_ESGOTO");
				} else {
					sessao.setAttribute("colecaoDivisaoEsgoto",colecaoDivisaoEsgoto);
				}
			}

			/*
			 * Local de Ocorrência - Carregando a coleção que irá ficar
			 * disponível para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoLocalOcorrencia = 
				(Collection) sessao.getAttribute("colecaoLocalOcorrencia");

			if (colecaoLocalOcorrencia == null) {

				FiltroLocalOcorrencia filtroLocalOcorrencia = 
					new FiltroLocalOcorrencia(
						FiltroLocalOcorrencia.DESCRICAO);

				filtroLocalOcorrencia.adicionarParametro(
					new ParametroSimples(
						FiltroLocalOcorrencia.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroLocalOcorrencia.setConsultaSemLimites(true);

				colecaoLocalOcorrencia = 
					this.getFachada().pesquisar(
						filtroLocalOcorrencia, LocalOcorrencia.class.getName());

				if (colecaoLocalOcorrencia == null || colecaoLocalOcorrencia.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", 
						null,
						"LOCAL_OCORRENCIA");
				} else {
					sessao.setAttribute("colecaoLocalOcorrencia",colecaoLocalOcorrencia);
				}
			}

			/*
			 * Pavimento Rua - Carregando a coleção que irá ficar disponível
			 * para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoPavimentoRua = 
				(Collection) sessao.getAttribute("colecaoPavimentoRua");

			if (colecaoPavimentoRua == null) {

				FiltroPavimentoRua filtroPavimentoRua = 
					new FiltroPavimentoRua(FiltroPavimentoRua.DESCRICAO);

				filtroPavimentoRua.adicionarParametro(
					new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroPavimentoRua.setConsultaSemLimites(true);

				colecaoPavimentoRua = 
					this.getFachada().pesquisar(filtroPavimentoRua,
						PavimentoRua.class.getName());

				if (colecaoPavimentoRua == null || colecaoPavimentoRua.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"PAVIMENTO_RUA");
				} else {
					sessao.setAttribute("colecaoPavimentoRua",colecaoPavimentoRua);
				}
			}

			/*
			 * Pavimento Calçada - Carregando a coleção que irá ficar disponível
			 * para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoPavimentoCalcada = (Collection) sessao.getAttribute("colecaoPavimentoCalcada");

			if (colecaoPavimentoCalcada == null) {

				FiltroPavimentoCalcada filtroPavimentoCalcada = 
					new FiltroPavimentoCalcada(
						FiltroPavimentoCalcada.DESCRICAO);

				filtroPavimentoCalcada.adicionarParametro(
					new ParametroSimples(
						FiltroPavimentoCalcada.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroPavimentoCalcada.setConsultaSemLimites(true);

				colecaoPavimentoCalcada = 
					this.getFachada().pesquisar(
						filtroPavimentoCalcada, 
						PavimentoCalcada.class.getName());

				if (colecaoPavimentoCalcada == null || colecaoPavimentoCalcada.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"PAVIMENTO_CALCADA");
				} else {
					sessao.setAttribute("colecaoPavimentoCalcada",colecaoPavimentoCalcada);
				}
			}

			// [SB0002] - Habilita/Desabilita Município, Bairro, Área do Bairro
			// e
			// Divisão de Esgoto
			ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = 
				this.getFachada().habilitarGeograficoDivisaoEsgoto(
					new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()));

			if (habilitaGeograficoDivisaoEsgoto != null) {
				if (habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoFaltaAgua()) {
					
					sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua","SIM");
					
					//Verificar carregamento do Município e Bairro de acordo com o tipo de solicitação
					if (atualizarRegistroAtendimentoActionForm.getIdImovel() != null
						&& !atualizarRegistroAtendimentoActionForm.getIdImovel().equalsIgnoreCase("")){
						
						ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = 
							this.getFachada().obterDadosIdentificacaoLocalOcorrencia(
								new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel()), 
								new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()), 
								new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()), 
								false);
						
						this.carregarMunicipioBairroParaImovel(
							habilitaGeograficoDivisaoEsgoto,
							dadosIdentificacaoLocalOcorrencia,
							atualizarRegistroAtendimentoActionForm, 
							sessao);
					}
				} else {
					sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua","NAO");
				}

				if (habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()) {
					sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto","SIM");
				} else {
					if (atualizarRegistroAtendimentoActionForm.getIdImovel() == null || 
						atualizarRegistroAtendimentoActionForm.getIdImovel().equals("")) {
						
						sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "NAO");
					} else {
						sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "SIM");
					}
				}
			} else {
				sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua", "SIM");
				sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "SIM");
			}
			// [SB0030] Habilita/Desabilita Conta
			short indicadorInformarPagamentoDuplicidade = ConstantesSistema.NAO;
	        short indicadorDocPagosValorMaiorRA = ConstantesSistema.NAO;
	        short indicadorDocPagosValorCobradosIndev = ConstantesSistema.NAO;
			if (atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null && 
				!atualizarRegistroAtendimentoActionForm.getTipoSolicitacao().equals("") && 
				!atualizarRegistroAtendimentoActionForm.getEspecificacao().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
						new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao())));
				
//				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
//						FiltroSolicitacaoTipoEspecificacao.INDICADOR_INFORMAR_CONTA_RA,
//						ConstantesSistema.SIM));
				
				Collection colecao = 
					this.getFachada().pesquisar(
						filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());
				
				if (colecao != null && !colecao.isEmpty()) {

					SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);

					indicadorInformarPagamentoDuplicidade = especificacao.getIndicadorInformarPagamentoDuplicidade();
					indicadorDocPagosValorMaiorRA = especificacao.getIndicadorInformarDocumentoPagamentoAMaior();
					indicadorDocPagosValorCobradosIndev = especificacao.getIndicadorInformarDocumentoPagamentoIndevido();
					short indicadorInformarRaConta = especificacao.getIndicadorInformarContaRA();
					
					if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.NAO.shortValue() && 
						indicadorInformarRaConta == ConstantesSistema.SIM.shortValue()){
						
						sessao.setAttribute("conta", "habilita");	
					}else{
						sessao.removeAttribute("conta");
					}
					
				} else {
					sessao.removeAttribute("conta");
				}
			}

	        // 4. E para esses três indicadores de devolução, o sistema exibe uma tabela ("Contas") com as contas em aberto para o imóvel:
	        if(atualizarRegistroAtendimentoActionForm.getIdImovel() != null
	        		&& !atualizarRegistroAtendimentoActionForm.getIdImovel().trim().equals("") 
	        		&& (indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue()
		        		|| indicadorDocPagosValorMaiorRA == ConstantesSistema.SIM.shortValue()
		        		|| indicadorDocPagosValorCobradosIndev == ConstantesSistema.SIM.shortValue())){
	        	
	        	Date dataVencimentoInicial = Util.criarData(1, 1, 0001);
	    		Date dataVencimentoFinal = Util.criarData(31, 12, 9999);

	    		// [UC0067] Obter Débito do Imóvel ou Cliente
	    		ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = Fachada.getInstancia()
					.obterDebitoImovelOuCliente(1, // indicadorDebito
						atualizarRegistroAtendimentoActionForm.getIdImovel(), // idImovel
						null, // codigoCliente
						null, // clienteRelacaoTipo
						"000101", // anoMesInicialReferenciaDebito
						"999912", // anoMesFinalReferenciaDebito
						dataVencimentoInicial, // anoMesInicialVencimentoDebito
						dataVencimentoFinal, // anoMesFinalVencimentoDebito
						1, // indicadorPagamento
						1, // indicadorContaEmRevisao
						2, // indicadorDebitoACobrar
						2, // indicadorCreditoARealizar
						2, // indicadorNotasPromissorias
						2, // indicadorGuiasPagamento
						2, // indicadorCalcularAcrescimoImpontualidade
						true);// indicadorContas

	    		// CONTA
	    		if (obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel() != null
	    				&& !obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel().isEmpty()) {
	    			
	    			Collection<ContaValoresHelper> colecaoContaValoresImovel = new ArrayList<ContaValoresHelper>();
	    			Collection<String> colecaoIdsContas = new ArrayList<String>();

	    			// Selecionar apenas as contas que não estejam em revisão ou que estejam em revisão para este RA
	    			Iterator<ContaValoresHelper> itColecaoConta = obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel().iterator();

	    			while (itColecaoConta.hasNext()) {

	    				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) itColecaoConta.next();
	    				
	    				if (contaValoresHelper.getConta().getContaMotivoRevisao() == null
	    						|| (contaValoresHelper.getConta().getContaMotivoRevisao().getId().compareTo(
	    								ContaMotivoRevisao.SOLICITACAO_DE_DEVOLUCAO) == 0
	    							&& fachada.verificarContaAcossiadaRA(contaValoresHelper.getConta().getId(), 
	    								new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA())))) {

	    					colecaoContaValoresImovel.add(contaValoresHelper);
	    					
	    					if (contaValoresHelper.getConta().getContaMotivoRevisao() != null) {
	    						colecaoIdsContas.add(contaValoresHelper.getConta().getId().toString());
	    					}
	    				}
	    			}
	    			
	    			if (colecaoContaValoresImovel != null
	    					&& !colecaoContaValoresImovel.isEmpty()) {
	    				
	    				if (colecaoIdsContas != null 
	    						&& !colecaoIdsContas.isEmpty()
	    						&& atualizarRegistroAtendimentoActionForm.getContasSelecao() == null) {
	    					atualizarRegistroAtendimentoActionForm.setContasSelecao(
	    						Util.converterColecaoParaArray(colecaoIdsContas));
	    				}
		    			sessao.setAttribute("colecaoContaValoresImovel", colecaoContaValoresImovel);
		    			
	    			} else {
		    			sessao.removeAttribute("colecaoContaValoresImovel");
		    			atualizarRegistroAtendimentoActionForm.setContasSelecao(null);
		    		}
	    			
	    		} else {
	    			sessao.removeAttribute("colecaoContaValoresImovel");
	    			atualizarRegistroAtendimentoActionForm.setContasSelecao(null);
	    		}
	    		
	        } else {
				sessao.removeAttribute("colecaoContaValoresImovel");
    			atualizarRegistroAtendimentoActionForm.setContasSelecao(null);
	        }
			
            //Caso o indicador "Informar Documentos Pagos com Valor Cobrado indevidamente no Registro de Atendimento" esteja com sim
            if(atualizarRegistroAtendimentoActionForm.getIndicadorInformarDocumentoPagamentoIndevido() != null && 
            	atualizarRegistroAtendimentoActionForm.getIndicadorInformarDocumentoPagamentoIndevido().equals(""+ConstantesSistema.SIM)){
            	
            	//Caso o usuário tenha permissão especial para devolução de valores indevidos
            	if ( fachada.verificarPermissaoEspecial( PermissaoEspecial.DEVOLUCAO_VALORES_INDEVIDOS_RA, usuarioLogado ) ){
            		
            		//O sistema exibe um botão "Pesquisar Documentos Pagos" para a seleção dos documentos
        			httpServletRequest.setAttribute( "permissaoDevolucaoValoresIndevidosRA", "SIM" );
        		} else {
        			httpServletRequest.setAttribute( "permissaoDevolucaoValoresIndevidosRA", "NAO" );
        			sessao.removeAttribute("colecaoPagamentosValoresCobIndevidamente");    		
        		}
            }
            //------------------------------------------------------------------------------------------------ 
            
            
			/*
			 * Fim do carregamento inicial
			 * ============================================================================================================
			 * ============================================================================================================
			 */
            
            
            //Remover Documento com Pagamento maior que o documento
            String removerPagValorMaiorDoc = httpServletRequest.getParameter("removerPagamentoValorMaiorDoc");
            if(removerPagValorMaiorDoc != null && removerPagValorMaiorDoc.equals("ok")){
            	
            	String idPagamento = httpServletRequest.getParameter("idPagamento");
            	Collection colecaoDocumentosPagos = (Collection)sessao.getAttribute("colecaoPagValorMaiorDocumento");
            	this.removerDocumento(colecaoDocumentosPagos,idPagamento);
            	sessao.setAttribute("colecaoPagValorMaiorDocumento", colecaoDocumentosPagos);
            	
            }
            
            //Remover Documento com valor Cobrado indevidamente
            String removerPagCobIndev = httpServletRequest.getParameter("removerPagCobIndev");
            if(removerPagCobIndev != null && removerPagCobIndev.equals("ok")){
            	
            	String idPagamento = httpServletRequest.getParameter("idPagamento");
            	Collection colecaoDocumentosPagos = (Collection)sessao.getAttribute("colecaoPagamentosValoresCobIndevidamente");
            	this.removerDocumento(colecaoDocumentosPagos,idPagamento);
            	sessao.setAttribute("colecaoPagamentosValoresCobIndevidamente", colecaoDocumentosPagos);
            	
            }
            
            

			/*
			 * [FS0013] - Verificar compatibilidade entre divisão de esgoto e
			 * localidade/setor/quadra [SB0007] - Define Unidade Destino da
			 * Divisão de Esgoto
			 */
			String verificarCompatibilidade = 
				httpServletRequest.getParameter("verificarCompatibilidade");

			if (verificarCompatibilidade != null && 
				!verificarCompatibilidade.equalsIgnoreCase("")) {

				this.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(atualizarRegistroAtendimentoActionForm);

				httpServletRequest.setAttribute("nomeCampo", "idUnidadeDestino");
			}

			/*
			 * Removendo ColecaoBairroArea
			 */
			String removerColecaoBairroArea = 
				httpServletRequest.getParameter("removerColecaoBairroArea");

			if (removerColecaoBairroArea != null && !removerColecaoBairroArea.equalsIgnoreCase("")) {
				
				sessao.removeAttribute("colecaoBairroArea");
				httpServletRequest.setAttribute("nomeCampo", httpServletRequest.getParameter("campoFoco"));
			}

			/*
			 * Removendo endereço
			 */
			String removerEndereco = httpServletRequest.getParameter("removerEndereco");

			if (removerEndereco != null && !removerEndereco.trim().equalsIgnoreCase("")) {

				if (sessao.getAttribute("colecaoEnderecos") != null) {

					Collection enderecos = 
						(Collection) sessao.getAttribute("colecaoEnderecos");

					if (!enderecos.isEmpty()) {
						enderecos.remove(enderecos.iterator().next());
					}
				}
			}

			
			//Removendo Município Associado ao local de ocorrência
			String removerMunicipioOcorrencia = httpServletRequest.getParameter("limparMunicipioOcorrencia"); 
			if (removerMunicipioOcorrencia != null && !removerMunicipioOcorrencia.trim().equalsIgnoreCase("")) {
				sessao.removeAttribute("desabilitaMunicipioLocalidade");
			}
			/*
			 * Pesquisas realizadas a partir do ENTER
			 * ===========================================================================================================
			 */
			
			/*
			 * Dados da identificação do local de ocorrência
			 * 
			 * [FS0019] - Verificar endereço do imóvel
			 * 
			 */
			// caso esses parametros forem nulos então verifica os enter
			if (removerColecaoBairroArea == null && 
				verificarCompatibilidade == null && 
				removerEndereco == null) {

				String idImovel = atualizarRegistroAtendimentoActionForm.getIdImovel();

				int indValidacaoMatriculaImovel = 0;
				if (atualizarRegistroAtendimentoActionForm.getIndValidacaoMatriculaImovel() == null || 
					atualizarRegistroAtendimentoActionForm.getIndValidacaoMatriculaImovel().equals("")) {
					
					// [SB0024] - Verifica registro de Atendimento Sem
					// Identificação
					// do
					// Local da Ocorrência
					if (idImovel != null && !idImovel.equals("")) {
						
						indValidacaoMatriculaImovel = 
							this.getFachada().verificarRASemIdentificacaoLO(
								new Integer(idImovel), 
								new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()));

						atualizarRegistroAtendimentoActionForm.setIndValidacaoMatriculaImovel(""+indValidacaoMatriculaImovel);
						
						if (indValidacaoMatriculaImovel != 1) {

							ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = 
								this.getFachada().obterDadosIdentificacaoLocalOcorrenciaAtualizar(
									new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel()),
									new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()),
									new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()),
									new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()),
									false);
							
							if (dadosIdentificacaoLocalOcorrencia.isHabilitarAlteracaoEndereco()) {
								sessao.setAttribute("habilitarAlteracaoEndereco", "SIM");
							} else {
								sessao.setAttribute("habilitarAlteracaoEndereco", "NAO");
							}
							if (dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo() != null && 
								!dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo().equals("")) {
								
								sessao.setAttribute("desabilitarMunicipioBairro", "OK");
							}

						}
					}
				} else {
					indValidacaoMatriculaImovel = Integer.parseInt(atualizarRegistroAtendimentoActionForm.getIndValidacaoMatriculaImovel());
				}
				
				// caso seja a pesquisa do enter do imóvel ou o indicador de
				// validação de matrícula do imóvel seja 1
				if ((idImovel != null && !idImovel.equalsIgnoreCase("") ) || indValidacaoMatriculaImovel == 1) {

					/*
					 * [SB0004] - Obtém e Habilita/Desabilita Dados da
					 * Identificação do Local da Ocorrência e Dados do
					 * Solicitante
					 * 
					 * [FS0019] - Verificar endereço do imóvel [FS0020] -
					 * Verificar existência de registro de atendimento para o
					 * imóvel com a mesma especificação
					 * 
					 * [SB0020] - Verifica Situação do Imóvel e Especificação
					 * 
					 * [SB0032] - Verifica se o imóvel informado tem débito
					 * 
					 * [SB0031]- Verificar instalação/substituição de hidrômetro para imóvel em situação especial de faturamento
					 * 
					 */
					ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = 
						this.getFachada().obterDadosIdentificacaoLocalOcorrenciaAtualizar(
							new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel()),
							new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()),
							new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()),
							new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()), 
							false);

					if (dadosIdentificacaoLocalOcorrencia.getImovel() != null) {

						boolean msgAlert = false;

						// [SB0021] - Verifica Existência de Registro de
						// Atendimento
						// Pendente para o Imóvel
						boolean raPendente = 
							this.getFachada().verificaExistenciaRAPendenteImovel(dadosIdentificacaoLocalOcorrencia.getImovel().getId());

						if (raPendente) {
							
							httpServletRequest.setAttribute(
								"msgAlert",
								"Atenção! " 
								+ "Existe Registro de Atendimento pendente para o imóvel "
								+ dadosIdentificacaoLocalOcorrencia.getImovel().getId().toString());
							
							msgAlert = true;
						}

						// [SB0020] - Verifica Situação do Imóvel e
						// Especificação
						this.getFachada().verificarSituacaoImovelEspecificacao(
							dadosIdentificacaoLocalOcorrencia.getImovel(),
							new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()));
						
						//[SB0032] - Verifica se o imóvel informado tem débito
						this.getFachada().verificarImovelComDebitos(
							new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()), 
							dadosIdentificacaoLocalOcorrencia.getImovel().getId());
						
						if(dadosIdentificacaoLocalOcorrencia.getImovel() != null &&
								dadosIdentificacaoLocalOcorrencia.getImovel().getId() != null &&
								!dadosIdentificacaoLocalOcorrencia.getImovel().getId().equals("") &&
								atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null && 
								!atualizarRegistroAtendimentoActionForm.getTipoSolicitacao().equals("")){
							//[FS0054] Verificar se o imóvel informado tem cliente usuário desconhecido
							this.getFachada().verificarImovelClienteDesconhecido(dadosIdentificacaoLocalOcorrencia.getImovel().getId(),
											new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()));
						}
						
						//[SB0031]- Verificar instalação/substituição de hidrômetro para imóvel em situação especial de faturamento
		                //=========================================================================================================
						 //1. Caso tenha sido informado o parâmetro para bloquear instalação/substituição de hidrômetro
		                if(sistemaParametro.getIndicadorBloqFuncInstalacaoSubtHidrometro().compareTo(ConstantesSistema.SIM) == 0){
		                	
		                	 //Busca do Tipo de Solicitação	
		                	 FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(); 
		                     filtroSolicitacaoTipoEspecificacao.adicionarParametro(
		                             new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
		                                     new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao())));
		                     Collection colecao = fachada.pesquisar(
		                             filtroSolicitacaoTipoEspecificacao,
		                             SolicitacaoTipoEspecificacao.class.getName());
		                     
		                     if (colecao != null && !colecao.isEmpty()) {
		                      
		                         SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);
		                         
		                         
		                         boolean informouInstalacaoSubsHidr = fachada
		                        		 .verificarInstalacaoSubstituicaoHidr(new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()));
		                         FaturamentoSituacaoTipo imovelSitEspecialFat = fachada
		                        		 .obterFaturamentoSituacaoTipoImovel(dadosIdentificacaoLocalOcorrencia.getImovel().getId());
		                         
		                         
		                         //1.1. Caso tenha sido informada instalação ou substituição de hidrômetro
		                         //     e o imóvel esteja em situação especial de faturamento
		                         if(informouInstalacaoSubsHidr 
		                        		 && imovelSitEspecialFat != null 
		                        		 && imovelSitEspecialFat.getId().compareTo(FaturamentoSituacaoTipo.FATURAR_MAIOR_ENTRE_MEDIA_E_CONSUMO_FIXO) == 0){
		                        	 
		                        	 //1.1. o sistema deve exibir a mensagem "Não é permitido <<Descrição da Solicitação Tipo Especificação>> 
		                        	 //     para o imóvel informado. O mesmo está na situação especial de faturamento 
		                        	 //     << FTST_DSFATURAMENTOSITUACAOTIPO da tabela FATURAMENTO.FATUR_SITUACAO_TIPO >>"
		                        	 throw new ActionServletException("atencao.solicitacao_nao_permitida_sit_especial", 
		                        			 						   especificacao.getDescricao(), 
		                        			 						   imovelSitEspecialFat.getDescricao());
		                        	 
		                         }
		                     }
		                }
		                //=========================================================================================================

						atualizarRegistroAtendimentoActionForm.setIdImovel(
							dadosIdentificacaoLocalOcorrencia.getImovel().getId().toString());

						atualizarRegistroAtendimentoActionForm.setInscricaoImovel(
							dadosIdentificacaoLocalOcorrencia.getImovel().getInscricaoFormatada());

						if (!dadosIdentificacaoLocalOcorrencia.isInformarEndereco()) {
							
							Collection colecaoEnderecos = new ArrayList();
							colecaoEnderecos.add(dadosIdentificacaoLocalOcorrencia.getImovel());
							sessao.setAttribute("colecaoEnderecos",colecaoEnderecos);
							sessao.setAttribute("enderecoPertenceImovel", "OK");
						
						} else if (dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo() != null) {
							
							atualizarRegistroAtendimentoActionForm.setDescricaoLocalOcorrencia(
								dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo());

							if (msgAlert) {
								httpServletRequest.setAttribute(
									"msgAlert2",
									"O Registro de Atendimento ficará bloqueado até que seja informado o logradouro para o imóvel");
							} else {
								httpServletRequest.setAttribute(
									"msgAlert",
									"O Registro de Atendimento ficará bloqueado até que seja informado o logradouro para o imóvel");
							}

							sessao.removeAttribute("colecaoEnderecos");
							sessao.removeAttribute("enderecoPertenceImovel");
						} else {
							sessao.removeAttribute("colecaoEnderecos");
							sessao.removeAttribute("enderecoPertenceImovel");
						}

						this.carregarMunicipioBairroParaImovel(
								habilitaGeograficoDivisaoEsgoto,
								dadosIdentificacaoLocalOcorrencia,
								atualizarRegistroAtendimentoActionForm, 
								sessao);

						Municipio municipio = dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getMunicipio(); 
						if(municipio != null){
							atualizarRegistroAtendimentoActionForm.setDescricaoMunicipioOcorrencia(municipio.getNome());
							httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
						}
						
						atualizarRegistroAtendimentoActionForm.setIdLocalidade(
							dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getId().toString());

						atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade(
							dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getDescricao());

						atualizarRegistroAtendimentoActionForm.setIdSetorComercial(
							dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getId().toString());

						atualizarRegistroAtendimentoActionForm.setCdSetorComercial(
							String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getCodigo()));

						atualizarRegistroAtendimentoActionForm.setDescricaoSetorComercial(
							dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getDescricao());

						atualizarRegistroAtendimentoActionForm.setIdQuadra(
							String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getQuadra().getId()));

						atualizarRegistroAtendimentoActionForm.setNnQuadra(
							String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getQuadra().getNumeroQuadra()));

						sessao.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");

		                
		                //Pavimento Rua
		                if (dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoRua() != null) {
		                	atualizarRegistroAtendimentoActionForm.setIdPavimentoRua(
		                        dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoRua().getId().toString());
		                } else {
		                	atualizarRegistroAtendimentoActionForm.setIdPavimentoRua("");
		                }

		                //Pavimento Calcada
		                if (dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoCalcada() != null) {
		                	atualizarRegistroAtendimentoActionForm.setIdPavimentoCalcada(
		                            dadosIdentificacaoLocalOcorrencia.getImovel().getPavimentoCalcada().getId().toString());
		                } else {
		                	atualizarRegistroAtendimentoActionForm.setIdPavimentoCalcada("");
		                }
						
						if (dadosIdentificacaoLocalOcorrencia.isHabilitarAlteracaoEndereco()) {
							sessao.setAttribute("habilitarAlteracaoEndereco","SIM");
						    //sessao.setAttribute("habilitarPROGIS", "habilitarPROGIS");
					          
						} else {
							sessao.setAttribute("habilitarAlteracaoEndereco","NAO");
							//sessao.setAttribute("habilitarPROGIS", "habilitarPROGIS");
						}

					} else {

						atualizarRegistroAtendimentoActionForm.setIdImovel("");
						atualizarRegistroAtendimentoActionForm.setInscricaoImovel("Imóvel Inexistente");

						httpServletRequest.setAttribute("corImovel","exception");
						httpServletRequest.setAttribute("nomeCampo", "idImovel");
					}

				}

				String idMunicipio = atualizarRegistroAtendimentoActionForm.getIdMunicipio();
				String descricaoMunicipio = atualizarRegistroAtendimentoActionForm.getDescricaoMunicipio();

				if (idMunicipio != null && 
					!idMunicipio.equalsIgnoreCase("") && 
					(descricaoMunicipio == null || descricaoMunicipio.equals(""))) {

					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

					filtroMunicipio.adicionarParametro(
						new ParametroSimples(
							FiltroMunicipio.ID,
							atualizarRegistroAtendimentoActionForm.getIdMunicipio()));

					filtroMunicipio.adicionarParametro(
						new ParametroSimples(
							FiltroMunicipio.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoMunicipio = 
						this.getFachada().pesquisar(
							filtroMunicipio, 
							Municipio.class.getName());

					if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdMunicipio("");
						atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio("Município Inexistente");

						httpServletRequest.setAttribute("corMunicipio","exception");
						httpServletRequest.setAttribute("nomeCampo","idMunicipio");

					} else {
						Municipio municipio = 
							(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

						atualizarRegistroAtendimentoActionForm.setIdMunicipio(municipio.getId().toString());
						atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio(municipio.getNome());

						httpServletRequest.setAttribute("nomeCampo", "cdBairro");
					}
				}

				String codigoBairro = atualizarRegistroAtendimentoActionForm.getCdBairro();
				String descricaoBairro = atualizarRegistroAtendimentoActionForm.getDescricaoBairro();

				if (codigoBairro != null && !codigoBairro.equalsIgnoreCase("")) {

					if ((descricaoBairro == null || descricaoBairro.equals(""))) {

						FiltroBairro filtroBairro = new FiltroBairro();

						filtroBairro.adicionarParametro(
							new ParametroSimples(
								FiltroBairro.CODIGO,
								atualizarRegistroAtendimentoActionForm.getCdBairro()));

						filtroBairro.adicionarParametro(
							new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								atualizarRegistroAtendimentoActionForm.getIdMunicipio()));

						filtroBairro.adicionarParametro(
							new ParametroSimples(
								FiltroBairro.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

						Collection colecaoBairro = 
							this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());

						if (colecaoBairro == null || colecaoBairro.isEmpty()) {

							atualizarRegistroAtendimentoActionForm.setCdBairro("");
							atualizarRegistroAtendimentoActionForm.setDescricaoBairro("Bairro Inexistente");

							httpServletRequest.setAttribute("corBairro","exception");
							httpServletRequest.setAttribute("nomeCampo","cdBairro");

						} else {
							Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

							atualizarRegistroAtendimentoActionForm.setCdBairro(String.valueOf(bairro.getCodigo()));
							atualizarRegistroAtendimentoActionForm.setCdBairro(String.valueOf(bairro.getId()));
							atualizarRegistroAtendimentoActionForm.setDescricaoBairro(bairro.getNome());
							
							this.pesquisarBairroArea(
								new Integer(atualizarRegistroAtendimentoActionForm.getIdBairro()), 
								sessao);

						}
					}

				}

				String pesquisarBairroArea = httpServletRequest.getParameter("pesquisarBairroArea");

				if (pesquisarBairroArea != null && 
					!pesquisarBairroArea.equalsIgnoreCase("") || 
					(atualizarRegistroAtendimentoActionForm.getIdBairro() != null && 
						!atualizarRegistroAtendimentoActionForm.getIdBairro().equals(""))) {

					this.pesquisarBairroArea(
						new Integer(atualizarRegistroAtendimentoActionForm.getIdBairro()), 
						sessao);

					httpServletRequest.setAttribute("nomeCampo", "idBairroArea");
				}

				String idLocalidade = atualizarRegistroAtendimentoActionForm.getIdLocalidade();
				String descricaoLocalidade = atualizarRegistroAtendimentoActionForm.getDescricaoBairro();

				if (idLocalidade != null && 
					!idLocalidade.equalsIgnoreCase("") && 
					(descricaoLocalidade == null || descricaoLocalidade.equals(""))) {
					
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

					filtroLocalidade.adicionarParametro(
						new ParametroSimples(
							FiltroLocalidade.ID,
							atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

					filtroLocalidade.adicionarParametro(
						new ParametroSimples(
							FiltroLocalidade.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoLocalidade = 
						this.getFachada().pesquisar(
							filtroLocalidade, 
							Localidade.class.getName());

					if (colecaoLocalidade == null || 
						colecaoLocalidade.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdLocalidade("");
						atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade("Localidade Inexistente");

						httpServletRequest.setAttribute("corLocalidade","exception");
						httpServletRequest.setAttribute("nomeCampo","idLocalidade");

					} else {
						Localidade localidade = 
							(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

						atualizarRegistroAtendimentoActionForm.setIdLocalidade(localidade.getId().toString());
						atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade(localidade.getDescricao());

						httpServletRequest.setAttribute("nomeCampo","cdSetorComercial");

					}
				}

				String cdSetorComercial = atualizarRegistroAtendimentoActionForm.getCdSetorComercial();
				String descricaoSetorComercial = atualizarRegistroAtendimentoActionForm.getDescricaoSetorComercial();

				if (cdSetorComercial != null && 
					!cdSetorComercial.equalsIgnoreCase("") && 
					(descricaoSetorComercial == null || descricaoSetorComercial.equals(""))) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

					filtroSetorComercial.adicionarParametro(
						new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE,
							atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

					filtroSetorComercial.adicionarParametro(
						new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							atualizarRegistroAtendimentoActionForm.getCdSetorComercial()));

					filtroSetorComercial.adicionarParametro(
						new ParametroSimples(
							FiltroSetorComercial.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoSetorComercial = 
						this.getFachada().pesquisar(filtroSetorComercial, 
							SetorComercial.class.getName());

					if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdSetorComercial("");
						atualizarRegistroAtendimentoActionForm.setCdSetorComercial("");
						atualizarRegistroAtendimentoActionForm.setDescricaoSetorComercial("Setor Comercial Inexistente");

						httpServletRequest.setAttribute("corSetorComercial","exception");
						httpServletRequest.setAttribute("nomeCampo","cdSetorComercial");

					} else {
						SetorComercial setorComercial = 
							(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

						atualizarRegistroAtendimentoActionForm.setIdSetorComercial(setorComercial.getId().toString());
						atualizarRegistroAtendimentoActionForm.setCdSetorComercial(String.valueOf(setorComercial.getCodigo()));
						atualizarRegistroAtendimentoActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());

						httpServletRequest.setAttribute("nomeCampo", "nnQuadra");

					}
				}

				String nnQuadra = atualizarRegistroAtendimentoActionForm.getNnQuadra();
				String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");

				if (nnQuadra != null && 
					!nnQuadra.equalsIgnoreCase("") && 
					(pesquisarQuadra != null && pesquisarQuadra.equals(""))) {

					FiltroQuadra filtroQuadra = new FiltroQuadra();

					filtroQuadra.adicionarParametro(
						new ParametroSimples(
							FiltroQuadra.ID_SETORCOMERCIAL,
							atualizarRegistroAtendimentoActionForm.getIdSetorComercial()));

					filtroQuadra.adicionarParametro(
						new ParametroSimples(
							FiltroQuadra.NUMERO_QUADRA,
							atualizarRegistroAtendimentoActionForm.getNnQuadra()));

					filtroQuadra.adicionarParametro(
						new ParametroSimples(
							FiltroQuadra.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoQuadra = 
						this.getFachada().pesquisar(
							filtroQuadra,
							Quadra.class.getName());

					if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdQuadra("");
						atualizarRegistroAtendimentoActionForm.setNnQuadra("");

						httpServletRequest.setAttribute("msgQuadra","QUADRA INEXISTENTE");
						httpServletRequest.setAttribute("nomeCampo", "nnQuadra");

					} else {
						Quadra quadra = 
							(Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

						atualizarRegistroAtendimentoActionForm.setIdQuadra(quadra.getId().toString());
						atualizarRegistroAtendimentoActionForm.setNnQuadra(String.valueOf(quadra.getNumeroQuadra()));

						// [SB0006] - Obtém Divisão de Esgoto
						DivisaoEsgoto divisaoEsgoto = 
							this.getFachada().obterDivisaoEsgoto(
								quadra.getId(),
								habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());

						if (divisaoEsgoto != null) {
							atualizarRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgoto.getId().toString());

							/*
							 * [FS0013] - Verificar compatibilidade entre
							 * divisão de esgoto e localidade/setor/quadra
							 * [SB0007] - Define Unidade Destino da Divisão de
							 * Esgoto
							 */
							this.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(atualizarRegistroAtendimentoActionForm);

						} else {
							httpServletRequest.setAttribute("nomeCampo","idDivisaoEsgoto");
						}

					}
				}
				
				String pesquisarImovel = httpServletRequest.getParameter("pesquisarImovel");
				if(pesquisarImovel != null && !pesquisarImovel.equalsIgnoreCase("")){
					sessao.removeAttribute("colecaoPagValorMaiorDocumento");
					sessao.removeAttribute("colecaoPagamentosValoresCobIndevidamente");

					//Caso o indicador "Informar Documentos Pagos com Valor a maior no Registro de Atendimento" esteja com sim
					 if(atualizarRegistroAtendimentoActionForm.getIndicadorInformarDocumentoPagamentoAMaior() != null && 
				            	atualizarRegistroAtendimentoActionForm.getIndicadorInformarDocumentoPagamentoAMaior().equals(""+ConstantesSistema.SIM)){
		            	
		            	if(atualizarRegistroAtendimentoActionForm.getIdImovel() != null && !atualizarRegistroAtendimentoActionForm.getIdImovel().equals("")){
		            		
		    	        	//[FS0059] - Verificar pagamento a maior para imóvel informado
		    	        	this.verificarPagamentoMaiorImovelInformado(atualizarRegistroAtendimentoActionForm.getIdImovel());
		    	        	
		    	        	//O sistema exibe uma tabela ("Pagamentos com Valor a Maior que o Documento") com os pagamentos selecionados
		    	        	Collection colecaoPagValorMaiorDocumento = fachada.pesquisarPagamentosValorMaiorDocumento(new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel()));
		    	        	
		    	        	sessao.setAttribute("colecaoPagValorMaiorDocumento",colecaoPagValorMaiorDocumento);  
		    	        	
		            	}	
		            }
				}
			}
			
			
		
			
			//Pesquisar Localidade
			String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");

			if (pesquisarLocalidade != null	&& !pesquisarLocalidade.equalsIgnoreCase("")) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("municipio");
				
				Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade,
						Localidade.class.getName());

				if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {

					atualizarRegistroAtendimentoActionForm.setIdLocalidade("");
					atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade("Localidade Inexistente");

					httpServletRequest.setAttribute("corLocalidade", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idLocalidade");

				} else {
					Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

					atualizarRegistroAtendimentoActionForm.setIdLocalidade(localidade.getId().toString());
					atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade(localidade.getDescricao());

					httpServletRequest.setAttribute("nomeCampo", "cdSetorComercial");

					Municipio municipio = localidade.getMunicipio();
					if(municipio != null){
						atualizarRegistroAtendimentoActionForm.setDescricaoMunicipioOcorrencia(municipio.getNome());
						httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
					}
					httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
				}
			}
			/*
			 * Fim das pesquisas realizadas pelo ENTER
			 * ===========================================================================================================
			 * ===========================================================================================================
			 */
			
			/**
			 * RM 6417
			 * @author Fernanda Almeida
			 * @date 15/01/2012
			 * ==============================================================================================================	
			 */
			colecaoRAContasAtualizar = new ArrayList();
			// Verifica se foi retornado da tela de pesquisar contas retificacao
			if (sessao.getAttribute("contasSelecionadas") != null
					&& !sessao.getAttribute("contasSelecionadas").equals("")){
				//colecaoConta = (List<Conta>) sessao.getAttribute("colecaoConta");
				listContas = (ArrayList<String>) sessao.getAttribute("contasSelecionadas");
				ArrayList<String> listContasExcluidas = new ArrayList<String>(); 	
				for(int i = 0;i<listContas.size();i++){
					
					RegistroAtendimentoConta registroAtendimentoConta = this.pesquisarConta(listContas.get(i),
						atualizarRegistroAtendimentoActionForm, 
						sessao, 
						usuarioLogado);
				
					if (registroAtendimentoConta != null) {
						atualizarRegistroAtendimentoActionForm.setIdConta("");
						atualizarRegistroAtendimentoActionForm.setDescConta("");
						//sessao.setAttribute("colecaoRAContasAtualizar", "");
						
						if (!this.adicionado(colecaoRAContasAtualizar, registroAtendimentoConta)) {
							colecaoRAContasAtualizar.add(registroAtendimentoConta);
							sessao.setAttribute("colecaoRAContasAtualizar", colecaoRAContasAtualizar);
						}else{
							// Poe num array as contas repetidas que devem ser excluidas
							// do arraylist de sessao 'contasSelecionadas'
							if(sessao.getAttribute("atualizar")!=null){
								String contasRepetidas = listContas.get(i);
								listContasExcluidas.add(contasRepetidas);
							}
						}
					}
				}
				// exclui as contas repetidas
				for(int i=0;i<listContasExcluidas.size();i++){
					listContas.remove(listContasExcluidas.get(i));
					sessao.setAttribute("contasSelecionadas", listContas);
				}
			}
			
			
			/*
			 * ===========================================================================================================
			 * Adicionar Conta
			 * Mariana Victor - 31/01/2011
			 * ===========================================================================================================
			 */
			String adicionarConta = httpServletRequest.getParameter("adicionarConta");

			if (adicionarConta != null && 
				!adicionarConta.equalsIgnoreCase("") && 
				atualizarRegistroAtendimentoActionForm.getIdConta() != null && 
				!atualizarRegistroAtendimentoActionForm.getIdConta().equals("")) {
				
				RegistroAtendimentoConta registroAtendimentoConta = 
					this.pesquisarConta(atualizarRegistroAtendimentoActionForm, sessao, usuarioLogado);
				
				if (registroAtendimentoConta != null) {
					atualizarRegistroAtendimentoActionForm.setIdConta("");
					atualizarRegistroAtendimentoActionForm.setDescConta("");
					
					if (!this.adicionado(colecaoRAContasAtualizar, registroAtendimentoConta)) {
						colecaoRAContasAtualizar.add(registroAtendimentoConta);
						
						sessao.setAttribute("colecaoRAContasAtualizar", colecaoRAContasAtualizar);
					}
				}
			}
			
			/*
			 * ===========================================================================================================
			 * Remover Conta
			 * Mariana Victor - 31/01/2011
			 * ===========================================================================================================
			 */
			String removerConta = httpServletRequest.getParameter("removerConta");

			if (removerConta != null && !removerConta.equalsIgnoreCase("")) {
				Integer indice = new Integer(httpServletRequest.getParameter("removerConta"));
	        	
	        	if (colecaoRAContasAtualizar != null
	        			&& !colecaoRAContasAtualizar.isEmpty()
	        			&& colecaoRAContasAtualizar.size() >= indice) {
					
					if (colecaoRAContasAtualizar.get(indice-1).getUltimaAlteracao() != null){
						colecaoRAContasRemover.add(colecaoRAContasAtualizar.get(indice-1));
	        			sessao.setAttribute("colecaoRAContasRemover",colecaoRAContasRemover);
	        		}
					//Salva a conta a ser excluida para ser retirado do atributo de sessao 'contasSelecionadas'
					String contaAExcluir = colecaoRAContasAtualizar.get(indice-1).getContaGeral().getId().toString();
					
					colecaoRAContasAtualizar.remove(indice-1);
					sessao.setAttribute("colecaoRAContasAtualizar", colecaoRAContasAtualizar);
					
					if( sessao.getAttribute("contasSelecionadas") != null){
	        			
						listContas.remove(contaAExcluir);
	        			
	        			if(listContas.size() == 0){
	        				sessao.removeAttribute("contasSelecionadas");
	        				sessao.removeAttribute("colecaoRAContasAtualizar");
	        			}else{
	        				sessao.setAttribute("contasSelecionadas", listContas);
	        			}
	        		}
	        	}
			}
			
			Integer idImovelPagamentoDuplicidade = (Integer) sessao.getAttribute("idImovelPagamentoDuplicidade");
			Integer idImovelFormulario = null;
			
			if(atualizarRegistroAtendimentoActionForm.getIdImovel() != null && 
				!atualizarRegistroAtendimentoActionForm.getIdImovel().equals("")){
				
				idImovelFormulario =  new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel());
			}
			
			if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue()){
				if(idImovelFormulario != null && !idImovelFormulario.equals(idImovelPagamentoDuplicidade) || 
					sessao.getAttribute("colecaoPagamentosDuplicidade") == null){
					
					if(idImovelPagamentoDuplicidade == null || 
					(idImovelFormulario != null && !idImovelFormulario.equals(idImovelPagamentoDuplicidade))){
						
						boolean existePagamento = 
							this.existePagamentoDuplicadoDevolvido(new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()));
						
						if(!existePagamento){
							Collection colecaoPagamento = 
								this.pesquisaPagamentosEmDuplicidade(idImovelFormulario, 
									new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()));
							
							if(colecaoPagamento != null && 
								!colecaoPagamento.isEmpty()){
								
								sessao.setAttribute("colecaoPagamentosDuplicidade",colecaoPagamento);
								sessao.setAttribute("idImovelPagamentoDuplicidade",idImovelFormulario);
									
							}else{
								throw new ActionServletException("atencao.nao_exite_pagamento_duplicidade");
							}
							
						}else{
							throw new ActionServletException("existe_pagamento_duplicidade_ra");
						}
	
					}else if (sessao.getAttribute("colecaoPagamentosDuplicidade") == null){
						
					
						FiltroRegistroAtendimentoDevolucaoValores filtroRegistroAtendimentoDevolucaoValores = 
							new FiltroRegistroAtendimentoDevolucaoValores();
						
						filtroRegistroAtendimentoDevolucaoValores.adicionarParametro(
							new ParametroSimples(
								FiltroRegistroAtendimentoDevolucaoValores.REGISTRO_ATENDIMENTO,
								atualizarRegistroAtendimentoActionForm.getNumeroRA()));
						
						Collection colecaoRegistroAtendimentoDevolucaoValores = 
							(Collection) this.getFachada().pesquisar(
									filtroRegistroAtendimentoDevolucaoValores, 
								RegistroAtendimentoDevolucaoValores.class.getName());
						
						if(colecaoRegistroAtendimentoDevolucaoValores != null && !colecaoRegistroAtendimentoDevolucaoValores.isEmpty()){
							sessao.setAttribute("idImovelPagamentoDuplicidade",idImovelFormulario);
							sessao.setAttribute("colecaoPagamentosDuplicidade",colecaoRegistroAtendimentoDevolucaoValores);
						}						
					}
				}
			}
			
			
			String removerPagamento = httpServletRequest.getParameter("removerPagamento");

			if (removerPagamento != null && !removerPagamento.equals("")) {
				Integer idPagamento = new Integer(httpServletRequest.getParameter("removerPagamento"));
	        	
				Collection colecaoPagamentosDuplicidade = 
					(Collection) sessao.getAttribute("colecaoPagamentosDuplicidade");
				
				Iterator itera = colecaoPagamentosDuplicidade.iterator();
				
				while (itera.hasNext()) {
					RegistroAtendimentoDevolucaoValores registroAtendimentoDevolucaoValores = 
						(RegistroAtendimentoDevolucaoValores) itera.next();
					
					if(registroAtendimentoDevolucaoValores.getComp_id().getPagamentoId().intValue() == idPagamento.intValue()){
						itera.remove();
						break;
					}
				}
			}
			
			String atualizarPagamentosDuplicidade = httpServletRequest.getParameter("atualizarPagamentosDuplicidade");
			
			if(atualizarPagamentosDuplicidade != null && !atualizarPagamentosDuplicidade.equals("")){
				
				if(idImovelFormulario != null && !idImovelFormulario.equals("")){
					
					Collection colecaoPagamentosDuplicidade = 
						this.pesquisaPagamentosEmDuplicidade(
							new Integer(idImovelFormulario),
							new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()));
					
					if(colecaoPagamentosDuplicidade != null && 
						!colecaoPagamentosDuplicidade.isEmpty()){
						
						sessao.setAttribute("colecaoPagamentosDuplicidade",colecaoPagamentosDuplicidade);
					}else{
						throw new ActionServletException("atencao.nao_exite_pagamento_duplicidade");
					}
				}
			}			

			/*
			 * Limpar Imóvel
			 */
			String limparImovel = httpServletRequest.getParameter("limparImovel");

			if (limparImovel != null && !limparImovel.trim().equalsIgnoreCase("")) {

				this.limparImovel(atualizarRegistroAtendimentoActionForm,sessao, httpServletRequest);
				httpServletRequest.setAttribute("nomeCampo", "idImovel");
			}
		}
		
		if (((atualizarRegistroAtendimentoActionForm.getInscricaoImovel() != null)
				&& (!atualizarRegistroAtendimentoActionForm.getInscricaoImovel().equals("")) 
				&& !atualizarRegistroAtendimentoActionForm.getInscricaoImovel().equals("Imóvel Inexistente"))
				|| ((atualizarRegistroAtendimentoActionForm.getIdLocalidade() != null) 
						&& (!atualizarRegistroAtendimentoActionForm.getIdLocalidade().equals("")))) {

			// Caso a localidade do imóvel possua indicador para incluir as
			// coordenadas no
			// ProGIS (LOCA_ICINCLCOORDPROGISRA = 1 na tabela
			// CADASTRO.LOCALIDADE
			// com LOCA_ID = LOCA_ID da tabela IMOVEL para IMOV_ID=Matrícula do
			// Imóvel)
			String idLocalidade = "";

			if ((atualizarRegistroAtendimentoActionForm.getIdLocalidade() != null)
					&& (!atualizarRegistroAtendimentoActionForm.getIdLocalidade().equals(""))) {
				idLocalidade = atualizarRegistroAtendimentoActionForm.getIdLocalidade();
			} else {
				idLocalidade = atualizarRegistroAtendimentoActionForm.getInscricaoImovel().substring(0, 3);
			}
			boolean indicadorIncluirCoordenaasPROGISLocalidade = fachada.verificarLocalidadeInclusaoCoordenadasPROGIS(Integer.valueOf(idLocalidade));

			// E o serviço tipo especificação possua indicador para incluir as
			// coordenadas
			// PROGIS (STEP_ICCOORDENADAPROGISRA da tabela
			// ATENDIMENTOPUBLICO.SOLICITACAO_TIPO_ESPEC
			// onde STEP_ID = STEP_ID da tabela
			// ATENDIMENTOPUBLICO.REGISTRO_ATENDIMENTO)
			boolean indicadorCoordenadaProgisEspecificacao = false;
			SolicitacaoTipoEspecificacao especificacao = pesquisarSolicitacaoTipoEspecificacao(atualizarRegistroAtendimentoActionForm, fachada);

			if (especificacao != null) {
				if (especificacao.getIndicadorCoordenadaProgisRA().equals(new Short("1"))) {
					indicadorCoordenadaProgisEspecificacao = true;
				} else {
					indicadorCoordenadaProgisEspecificacao = false;
				}
			}

			if (indicadorIncluirCoordenaasPROGISLocalidade == false && indicadorCoordenadaProgisEspecificacao == true) {
				sessao.setAttribute("habilitarPROGIS", "habilitarPROGIS");
			} else {
				sessao.removeAttribute("habilitarPROGIS");
			}

		}

		return retorno;

	}
	
	
	private boolean existePagamentoDuplicadoDevolvido(Integer idRa){
		boolean existePagamento = false;
		
		FiltroRegistroAtendimentoDevolucaoValores filtroRegistroAtendimentoDevolucaoValores = 
			new FiltroRegistroAtendimentoDevolucaoValores();
		
		filtroRegistroAtendimentoDevolucaoValores.adicionarParametro(
			new ParametroSimples(
				FiltroRegistroAtendimentoDevolucaoValores.REGISTRO_ATENDIMENTO,
				idRa));

		filtroRegistroAtendimentoDevolucaoValores.adicionarParametro(
			new ParametroSimples(
					FiltroRegistroAtendimentoDevolucaoValores.INDICADOR_PAGAMENTO_DEVOLVIDO,
				ConstantesSistema.SIM));
		
		Collection colecaoRegistroAtendimentoDevolucaoValores = 
			(Collection) this.getFachada().pesquisar(
					filtroRegistroAtendimentoDevolucaoValores, 
				RegistroAtendimentoDevolucaoValores.class.getName());
		
		if(colecaoRegistroAtendimentoDevolucaoValores != null && !colecaoRegistroAtendimentoDevolucaoValores.isEmpty()){
			existePagamento = true;
		}	
		
		return existePagamento;
	}
	
	/*
		 * Métodos auxiliares
		 * =============================================================================================================
		 */

	public void verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm) {

		this.getFachada().verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdLocalidade()),
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdSetorComercial()),
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdQuadra()),
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdDivisaoEsgoto()));
	}

	public void pesquisarBairroArea(Integer idBairro,
			HttpSession sessao) {

		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

		filtroBairroArea.adicionarParametro(
			new ParametroSimples(
				FiltroBairroArea.ID_BAIRRO, 
				idBairro));

		Collection colecaoBairroArea = 
			this.getFachada().pesquisar(
				filtroBairroArea,
				BairroArea.class.getName());

		if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"BAIRRO_AREA");
		} else {
			sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		}
	}

	public void carregarMunicipioBairroParaImovel(
			ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto,
			ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaHelper,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			HttpSession sessao) {

		if (habilitaGeograficoDivisaoEsgoto != null && 
			habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoFaltaAgua() && 
			obterDadosIdentificacaoLocalOcorrenciaHelper.getEnderecoDescritivo() == null) {

			atualizarRegistroAtendimentoActionForm.setIdMunicipio(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getMunicipio().getId().toString());

			atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getMunicipio().getNome());

			atualizarRegistroAtendimentoActionForm.setIdBairro(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getId().toString());

			atualizarRegistroAtendimentoActionForm.setCdBairro(
				String.valueOf(obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getCodigo()));

			atualizarRegistroAtendimentoActionForm.setDescricaoBairro(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getNome());

			this.pesquisarBairroArea(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getId(),sessao);

			sessao.setAttribute("desabilitarMunicipioBairro", "OK");

		} else {

			atualizarRegistroAtendimentoActionForm.setIdMunicipio("");
			atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio("");
			atualizarRegistroAtendimentoActionForm.setIdBairro("");
			atualizarRegistroAtendimentoActionForm.setCdBairro("");
			atualizarRegistroAtendimentoActionForm.setDescricaoBairro("");

			sessao.removeAttribute("colecaoBairroArea");
			sessao.removeAttribute("desabilitarMunicipioBairro");
		}
	}

	private void limparImovel(AtualizarRegistroAtendimentoActionForm form,
			HttpSession sessao, HttpServletRequest httpServletRequest) {

		form.setIdImovel("");
		form.setInscricaoImovel("");
		form.setDescricaoLocalOcorrencia("");
		form.setIdMunicipio("");
		form.setDescricaoMunicipio("");
		form.setIdBairro("");
		form.setCdBairro("");
		form.setDescricaoBairro("");
		form.setIdLocalidade("");
		form.setDescricaoLocalidade("");
		form.setIdSetorComercial("");
		form.setCdSetorComercial("");
		form.setDescricaoSetorComercial("");
		form.setIdQuadra("");
		form.setNnQuadra("");
		form.setIdLocalOcorrencia("");
		form.setIdPavimentoRua("");
		form.setIdPavimentoCalcada("");
		form.setPontoReferencia("");
		form.setDescricaoMunicipioOcorrencia("");

		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("enderecoPertenceImovel");
		sessao.removeAttribute("habilitarAlteracaoEndereco");
		sessao.removeAttribute("colecaoBairroArea");
		sessao.removeAttribute("colecaoPagamentosDuplicidade");
		httpServletRequest.removeAttribute("desabilitaMunicipioLocalidade");
		sessao.removeAttribute("contasSelecionadas");
		sessao.removeAttribute("habilitarPROGIS");
		
	}

	//=================================================================================================================
	
	
	
	public void carregarDadosGis(			
			GisHelper gisHelper,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			HttpSession sessao) {
	
		String nnCoordenadaNorte = gisHelper.getNnCoordenadaNorte(); 
		String nnCoordenadaLeste = gisHelper.getNnCoordenadaLeste(); 			
		
		atualizarRegistroAtendimentoActionForm.setNnCoordenadaNorte(nnCoordenadaNorte);
		atualizarRegistroAtendimentoActionForm.setNnCoordenadaLeste(nnCoordenadaLeste);
		
	     sessao.removeAttribute("gisHelper");	
	
	}
	
	// Pesquisar Conta
	// Mariana Victor - 31/01/2011
	private RegistroAtendimentoConta pesquisarConta(AtualizarRegistroAtendimentoActionForm form, HttpSession sessao, Usuario usuarioLogado) {
		ContaGeral contaGeral = null;
		RegistroAtendimentoConta registroAtendimentoConta = null;
		
		String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(form.getIdConta());
		
		FiltroConta filtroConta = new FiltroConta();
		if (form.getIdContaPesquisada() != null && 
			!form.getIdContaPesquisada().equals("")) {
			
			filtroConta.adicionarParametro(
				new ParametroSimples(
					FiltroConta.ID, 
					form.getIdContaPesquisada()));
		} else {
			filtroConta.adicionarParametro(
				new ParametroSimples(
					FiltroConta.REFERENCIA, 
					anoMes));
		}
		filtroConta.adicionarParametro(
			new ParametroSimples(
				FiltroConta.IMOVEL_ID, 
				form.getIdImovel()));
		
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL);
		
		Collection colecao = 
			this.getFachada().pesquisar(filtroConta, Conta.class.getName());

		// [FS0046] Verificar existência da conta.
		if (colecao != null && !colecao.isEmpty()) {
			contaGeral = this.retornaConta(colecao);
			
			// [FS0047] Verificar se a conta pode ser associada.
			if (contaGeral != null) {
				
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();

				// Verifica se o usuário possua permissão especial
				boolean temPermissaoParaRetificarContaNorma = 
					getFachada().verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
				
				// [FS0048] Verificar prazo de vencimento das contas para associação
				if (temPermissaoParaRetificarContaNorma || 
					Util.adcionarOuSubtrairMesesAData(contaGeral.getConta().getDataVencimentoConta(), sistemaParametro.getNumeroMesesRetificarConta(), 0).compareTo(new Date()) != -1) {
					
					registroAtendimentoConta = new RegistroAtendimentoConta();
					registroAtendimentoConta.setContaGeral(contaGeral);
					
					form.setIdConta(contaGeral.getConta().getReferencia() + "");
					form.setDescConta(contaGeral.getConta().getFormatarAnoMesParaMesAno());
					sessao.setAttribute("contaEncontrada", "");
					
					return registroAtendimentoConta;
					
				} else {
					form.setIdConta("");
					form.setDescConta("Conta com prazo para associação excedido!");
					sessao.removeAttribute("contaEncontrada");
				}

			} else {
				form.setIdConta("");
				form.setDescConta("Conta não pode ser associada ao Registro de Atendimento!");
				sessao.removeAttribute("contaEncontrada");
			}
		} else {

			form.setIdConta("");
			form.setDescConta("Conta não Localizada");

			sessao.removeAttribute("contaEncontrada");
		}
		
		return null;
	}
	
	private RegistroAtendimentoConta pesquisarConta(String id,AtualizarRegistroAtendimentoActionForm form, HttpSession sessao, Usuario usuarioLogado) {
		ContaGeral contaGeral = null;
		RegistroAtendimentoConta registroAtendimentoConta = null;
				
		FiltroConta filtroConta = new FiltroConta();
		
		filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, id));
		
		
		
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL);
		
		Collection colecao = 
			this.getFachada().pesquisar(filtroConta, Conta.class.getName());

		// [FS0046] Verificar existência da conta.
		if (colecao != null && !colecao.isEmpty()) {
			contaGeral = this.retornaConta(colecao);
			
			// [FS0047] Verificar se a conta pode ser associada.
			if (contaGeral != null) {
				
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();

				// Verifica se o usuário possua permissão especial
				boolean temPermissaoParaRetificarContaNorma = 
					getFachada().verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
				
				// [FS0048] Verificar prazo de vencimento das contas para associação
				if (temPermissaoParaRetificarContaNorma || 
					Util.adcionarOuSubtrairMesesAData(contaGeral.getConta().getDataVencimentoConta(), sistemaParametro.getNumeroMesesRetificarConta(), 0).compareTo(new Date()) != -1) {
					
					registroAtendimentoConta = new RegistroAtendimentoConta();
					registroAtendimentoConta.setContaGeral(contaGeral);
					
					form.setIdConta(contaGeral.getConta().getReferencia() + "");
					form.setDescConta(contaGeral.getConta().getFormatarAnoMesParaMesAno());
					sessao.setAttribute("contaEncontrada", "");
					
					return registroAtendimentoConta;
					
				} else {
					form.setIdConta("");
					form.setDescConta("Conta com prazo para associação excedido!");
					sessao.removeAttribute("contaEncontrada");
				}

			} else {
				form.setIdConta("");
				form.setDescConta("Conta não pode ser associada ao Registro de Atendimento!");
				sessao.removeAttribute("contaEncontrada");
			}
		} else {

			form.setIdConta("");
			form.setDescConta("Conta não Localizada");

			sessao.removeAttribute("contaEncontrada");
		}
		
		return null;
	}
	
	/**
	 * Caso a especificacao tenha o indicador de pagamento em duplicidade,
	 * eh feita a pesquisa dos pagamentos em duplicidade
	 * 
	 * @author Rafael Pinto
	 * @date 15/03/2011
	 * 
	 * @param Integer idImovel
	 * @return Collection colecao de pagamentos em duplicidade
	 */
	private Collection<RegistroAtendimentoDevolucaoValores> pesquisaPagamentosEmDuplicidade(
		Integer idImovel, Integer idRA){
		
		FiltroPagamento filtro = new FiltroPagamento(FiltroPagamento.ANO_MES_REFERENCIA_PAGAMENTO);
		
		filtro.adicionarParametro(
			new ParametroSimples(FiltroPagamento.IMOVEL,
				idImovel));
		
		filtro.adicionarParametro(
			new ParametroSimples(FiltroPagamento.PAGAMENTO_SITUACAO_ATUAL_ID,
				PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.IMOVEL);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.CONTA_GERAL);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.CONTA);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.CONTA_HISTORICO);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.DEBITO_A_COBRAR_GERAL);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.DEBITO_A_COBRAR_);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.DEBITO_A_COBRAR_HISTORICO);
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.GUIA_PAGAMENTO);
        
		Collection<Pagamento> colecaoPagamento = 
			(Collection<Pagamento>) this.getFachada().pesquisar(filtro,Pagamento.class.getName());
		
		Collection<RegistroAtendimentoDevolucaoValores> 
			colecaoRegistroAtendimentoDevolucaoValores = new ArrayList<RegistroAtendimentoDevolucaoValores>();
		if(colecaoPagamento != null && !colecaoPagamento.isEmpty()){
			Iterator<Pagamento> itera = colecaoPagamento.iterator();
			
			while (itera.hasNext()) {
				Pagamento pag = (Pagamento) itera.next();
				RegistroAtendimentoDevolucaoValores radev = new RegistroAtendimentoDevolucaoValores();
				RegistroAtendimentoDevolucaoValoresPK radevPK = new RegistroAtendimentoDevolucaoValoresPK();
				
				//Id do registro de atendimento gerado pelo sistema na inclusão do registro de atendimento
				radevPK.setRegistroAtendimentoId(idRA);
				
				//Id do pagamento da tabela PAGAMENTO informado
				radevPK.setPagamentoId(pag.getId());
				
				radev.setComp_id(radevPK);
				
				//Caso o tipo de documento seja "Conta"
				if(pag.getContaGeral() != null){
					
					//atribuir CNTA_ID da tabela PAGAMENTO do documento informado
					radev.setContaGeral(pag.getContaGeral());
					
					//ANO/MÊS de referencia do documento selecionado
					if (pag.getContaGeral().getIndicadorHistorico() == ConstantesSistema.SIM.shortValue()) {
						radev.setReferenciaConta(pag.getContaGeral().getContaHistorico().getAnoMesReferenciaConta());
					} else {
						radev.setReferenciaConta(pag.getContaGeral().getConta().getReferencia());
					}	
					
					//PGMT_AMREFERENCIAPAGAMENTO da tabela PAGAMENTO do pagamento informado
					radev.setAnoMesReferenciaPagamento(pag.getAnoMesReferenciaPagamento());
				}
				
				//caso contrário, atribuir o valor nulo.
				else{
					radev.setContaGeral(null);
				}
				
				//Caso o tipo de documento seja "Guia de Pagamento"
				if(pag.getGuiaPagamento() != null){
					
					//atribuir GPAG_ID da tabela PAGAMENTO do documento informado
					FiltroGuiaPagamentoGeral filtroGuiaPagamentoGeral = new FiltroGuiaPagamentoGeral();
					filtroGuiaPagamentoGeral.adicionarParametro(new ParametroSimples(
						FiltroGuiaPagamentoGeral.ID, pag.getGuiaPagamento().getId()));
					filtroGuiaPagamentoGeral.adicionarCaminhoParaCarregamentoEntidade(
						FiltroGuiaPagamentoGeral.GUIA_PAGAMENTO);
					filtroGuiaPagamentoGeral.adicionarCaminhoParaCarregamentoEntidade(
						FiltroGuiaPagamentoGeral.GUIA_PAGAMENTO_HISTORICO);
					
					Collection<GuiaPagamentoGeral> colecaoGuia = (Collection<GuiaPagamentoGeral>)
							this.getFachada().pesquisar(filtroGuiaPagamentoGeral, GuiaPagamentoGeral.class.getName());
					if (colecaoGuia != null && !colecaoGuia.isEmpty()) {
						GuiaPagamentoGeral guiaPG = (GuiaPagamentoGeral) 
								Util.retonarObjetoDeColecao(colecaoGuia);

						radev.setGuiaPagamentoGeral(guiaPG);
						
						//ANO/MÊS de referencia do documento selecionado
						if(radev.getGuiaPagamentoGeral().getIndicadorHistorico() == ConstantesSistema.SIM.shortValue()) {
							
							radev.setReferenciaConta(radev.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getAnoMesReferenciaContabil());
							radev.setAnoMesReferenciaPagamento(radev.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getAnoMesReferenciaContabil());
							
						} else {
							
							radev.setReferenciaConta(radev.getGuiaPagamentoGeral().getGuiaPagamento().getAnoMesReferenciaContabil());
							radev.setAnoMesReferenciaPagamento(radev.getGuiaPagamentoGeral().getGuiaPagamento().getAnoMesReferenciaContabil());
							
						}
						
					}
				}
				
				//caso contrário, atribuir o valor nulo.
				else{
					radev.setGuiaPagamentoGeral(null);
				}
				
				//Caso o tipo de documento seja "Débito a Cobrar"
				if(pag.getDebitoACobrarGeral() != null){
					
					//atribuir DBAC_ID da tabela PAGAMENTO do documento informado
					radev.setDebitoACobrarGeral(pag.getDebitoACobrarGeral());
					
					//ANO/MÊS de referencia do documento selecionado
					if(pag.getDebitoACobrarGeral().getIndicadorHistorico() == ConstantesSistema.SIM.shortValue()) {
						radev.setReferenciaConta(pag.getDebitoACobrarGeral().getDebitoACobrarHistorico().getAnoMesReferenciaContabil());
						//ANO/MÊS de referencia do documento selecionado
						radev.setAnoMesReferenciaPagamento(pag.getDebitoACobrarGeral().getDebitoACobrarHistorico().getAnoMesReferenciaContabil());
					} else {
						radev.setReferenciaConta(pag.getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaContabil());
						//ANO/MÊS de referencia do documento selecionado
						radev.setAnoMesReferenciaPagamento(pag.getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaContabil());
					}
				}
				
				//caso contrário, atribuir o valor nulo
				else{
					radev.setDebitoACobrarGeral(null);
				}
				
				//Data e Hora corrente
				radev.setUltimaAlteracao(new Date());
				
				//IMOV_ID da Registro de Atendimento
				Imovel imov = new Imovel();
				imov.setId(idImovel);
				radev.setImovel(imov);
				
				//2 (Dois)
				radev.setIndicadorPagamentoDevolvido(ConstantesSistema.NAO);
				
				//PGMT_DTPAGAMENTO da tabela PAGAMENTO do documento selecionado
				radev.setDataPagamento(pag.getDataPagamento());
				
				//PGMT_VLPAGAMENTO da tabela PAGAMENTO do documento selecionado
				radev.setValorPagamento(pag.getValorPagamento());
				
				//Caso o indicador STEP_INFORMARPGTODUPLICIDADE = 1
				radev.setCodigoTipoDevolucao(new Short("1"));
				
				//Valor de devolução informado
				radev.setValorDevolucao(pag.getValorExcedente());
				
				//Caso exista o Motivo Cobrança Indevida
				radev.setCobrancaIndevidaMotivo(null);
				
				colecaoRegistroAtendimentoDevolucaoValores.add(radev);
			}
			
		
		}
		
		return colecaoRegistroAtendimentoDevolucaoValores;
	}
	
	private boolean adicionado(Collection<RegistroAtendimentoConta> colecaoRegistroAtendimentoConta, RegistroAtendimentoConta registroAtendimentoConta) {
		
		Iterator iterator = colecaoRegistroAtendimentoConta.iterator();
		
		while(iterator.hasNext()) {
			RegistroAtendimentoConta RAContaAdicionado = (RegistroAtendimentoConta) iterator.next();
			
			if (RAContaAdicionado.getContaGeral().getId().equals(registroAtendimentoConta.getContaGeral().getId())
					&& (RAContaAdicionado.getRegistroAtendimento() == null
							|| registroAtendimentoConta.getRegistroAtendimento() == null
							|| RAContaAdicionado.getRegistroAtendimento().getId().equals(registroAtendimentoConta.getRegistroAtendimento().getId()))) {
				return true;
			}
		}
		
		return false;
	}
	
	private ContaGeral retornaConta(Collection colecao) {
		
		Iterator iterator = colecao.iterator();
		
		while(iterator.hasNext()) {
			
			Conta conta = (Conta) iterator.next();
			
			ContaGeral contaGeral = new ContaGeral();
			contaGeral.setId(conta.getId());
			contaGeral.setIndicadorHistorico(ConstantesSistema.NAO);
			contaGeral.setConta(conta);
			
			if (contaGeral.getConta().getDebitoCreditoSituacaoAtual() != null
					&& (contaGeral.getConta().getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_NORMAL)
						|| contaGeral.getConta().getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_RETIFICADA)
						|| contaGeral.getConta().getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_INCLUIDA))) {
				return contaGeral;
			}
		}
		
		return null;
	}
	
	//Remove o documento de id idPagamento da coleção colecaoDocs
    private void removerDocumento(Collection colecaoDocs, String idPagamento) {
		Iterator it = colecaoDocs.iterator();
		while(it.hasNext()){
			PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper)it.next();
			if(helper.getIdPagamento().toString().equals(idPagamento)){
				it.remove();
				break;
			}
		}
	}
    
    /**
     * 
     * [FS0059] - Verificar pagamento a maior para imóvel informado
     * 
     * @param idImovel
     * @date 19/11/2012
     */
	private void verificarPagamentoMaiorImovelInformado(String idImovel) {

		FiltroPagamento filtroPagamento = new FiltroPagamento();
		
		filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.IMOVEL_ID,idImovel));
		filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.PAGAMENTO_SITUACAO_ATUAL_ID,PagamentoSituacao.VALOR_NAO_CONFERE));
		
		Collection colecaoPag = this.getFachada().pesquisar(filtroPagamento, Pagamento.class.getName());
		
		
		//Caso o imóvel informada não tenha pagamento em duplicidade na tabela ARRECADACAO.PAGAMENTO
		if(colecaoPag == null || colecaoPag.size() == 0){
			
			//exibir a mensagem "Não existe pagamento em duplicidade para imóvel informado"
			throw new ActionServletException("atencao.nao_existe_pag_a_maior");
		}
	
	}
	
    /**
     * 
     * Pesquisa a SolicitacaoTipoEspecificacao do RA
     * 
     * @param AtualizarRegistroAtendimentoActionForm
     * @param Fachada
     * @return SolicitacaoTipoEspecificacao
     * 
     * @author Anderson Cabral
     * @date 22/04/2012
     */
    private SolicitacaoTipoEspecificacao pesquisarSolicitacaoTipoEspecificacao(AtualizarRegistroAtendimentoActionForm form, Fachada fachada){
        SolicitacaoTipoEspecificacao especificacao = null;
    	FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
        
        filtroSolicitacaoTipoEspecificacao.adicionarParametro(
                new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
                        new Integer(form.getEspecificacao())));
        
        Collection colecao = fachada.pesquisar(
                filtroSolicitacaoTipoEspecificacao,
                SolicitacaoTipoEspecificacao.class.getName());
        
        if (colecao != null && !colecao.isEmpty()) {
        	especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);
        }
        
        return especificacao;       
    }
	
}
