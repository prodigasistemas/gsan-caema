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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.FiltroArrecadadorMovimento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroGuiaPagamentoItem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarGuiaPagamento");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		// Recebe o id da guia de pagamento para fazer a consulta
		String guiaPagamentoId = httpServletRequest
				.getParameter("guiaPagamentoId");
		
		String guiaPagamentoHistoricoId = httpServletRequest
		.getParameter("guiaPagamentoHistoricoId");
		
		String nossoNumero = "";
		if(guiaPagamentoId != null && !guiaPagamentoId.equals("")){
			StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(DocumentoTipo.GUIA_PAGAMENTO.toString(),guiaPagamentoId);
			nossoNumero = nossoNumero2.toString();
		}else if(guiaPagamentoHistoricoId != null && !guiaPagamentoHistoricoId.equals("")){
			StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(DocumentoTipo.GUIA_PAGAMENTO.toString(),guiaPagamentoHistoricoId);
			nossoNumero = nossoNumero2.toString();
		}
		
		// Se chegar na funcionalidade sem o par�metro indica situa��o de erro
		if ((guiaPagamentoId == null || guiaPagamentoId.trim().equals(""))
				&& (guiaPagamentoHistoricoId == null || guiaPagamentoHistoricoId.trim().equals(""))) {
			throw new ActionServletException("erro.sistema");

		}
		
		if(guiaPagamentoId != null && !guiaPagamentoId.equals("")){
			Collection<Pagamento> colPagamentos = fachada.pesquisarPagamentoPeloId(Integer.parseInt(guiaPagamentoId), DocumentoTipo.GUIA_PAGAMENTO);
			
			if(Util.isVazioOrNulo(colPagamentos)){
				Collection<PagamentoHistorico> colHistoricoPagamentos = fachada.pesquisarPagamentoHistoricoPeloId(Integer.parseInt(guiaPagamentoId), DocumentoTipo.GUIA_PAGAMENTO);
				if(!Util.isVazioOrNulo(colHistoricoPagamentos)){
					PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colHistoricoPagamentos);
					ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(pagamentoHistorico, null);
					ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamentoHistorico.getArrecadadorMovimentoItem();
					
					httpServletRequest.setAttribute("pagamento", pagamentoHistorico);
					httpServletRequest.setAttribute("nossoNumero", nossoNumero);
					httpServletRequest.setAttribute("arrecadador", arrecadador);
					if(arrecadadorMovimentoItem != null){
						httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
					}
					sessao.setAttribute("contaPaga", "pago");
				}else{
					sessao.removeAttribute("contaPaga");
				}
			}else{
				Pagamento pagamento = (Pagamento) Util.retonarObjetoDeColecao(colPagamentos);
				ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(null, pagamento);
				ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamento.getArrecadadorMovimentoItem();
				
				httpServletRequest.setAttribute("pagamento", pagamento);
				httpServletRequest.setAttribute("nossoNumero", nossoNumero);
				httpServletRequest.setAttribute("arrecadador", arrecadador);
				if(arrecadadorMovimentoItem != null){
					httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
				}
				sessao.setAttribute("contaPaga", "pago");
			}
			
		}else if(guiaPagamentoHistoricoId != null && !guiaPagamentoHistoricoId.equals("")){
			Collection<Pagamento> colPagamentos = fachada.pesquisarPagamentoPeloId(Integer.parseInt(guiaPagamentoHistoricoId), DocumentoTipo.GUIA_PAGAMENTO);
			if(Util.isVazioOrNulo(colPagamentos)){
				Collection<PagamentoHistorico> colHistoricoPagamentos = fachada.pesquisarPagamentoHistoricoPeloId(Integer.parseInt(guiaPagamentoHistoricoId), DocumentoTipo.GUIA_PAGAMENTO);
				if(!Util.isVazioOrNulo(colHistoricoPagamentos)){
					PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colHistoricoPagamentos);
					ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(pagamentoHistorico, null);
					ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamentoHistorico.getArrecadadorMovimentoItem();
					
					httpServletRequest.setAttribute("pagamento", pagamentoHistorico);
					httpServletRequest.setAttribute("nossoNumero", nossoNumero);
					httpServletRequest.setAttribute("arrecadador", arrecadador);
					if(arrecadadorMovimentoItem != null){
						httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
					}
					sessao.setAttribute("contaPaga", "pago");
				}else{
					sessao.removeAttribute("contaPaga");
				}
			}else{
				Pagamento pagamento = (Pagamento) Util.retonarObjetoDeColecao(colPagamentos);
				ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(null, pagamento);
				ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamento.getArrecadadorMovimentoItem();
				
				httpServletRequest.setAttribute("pagamento", pagamento);
				httpServletRequest.setAttribute("nossoNumero", nossoNumero);
				httpServletRequest.setAttribute("arrecadador", arrecadador);
				if(arrecadadorMovimentoItem != null){
					httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
				}
				sessao.setAttribute("contaPaga", "pago");
			}
		}
		
		if (guiaPagamentoHistoricoId != null){
			// GUIA PAGAMENTO HISTORICO
			
			FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();
			filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(
					FiltroGuiaPagamentoHistorico.ID, guiaPagamentoHistoricoId));

			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoByDcstIdatual");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			// Para a exibi��o do endere�o do im�vel
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");

			GuiaPagamentoHistorico guiaPagamentoHistorico = (GuiaPagamentoHistorico)Util.retonarObjetoDeColecao
				(fachada.pesquisar(filtroGuiaPagamentoHistorico,GuiaPagamentoHistorico.class.getName()));

			// Envia o objeto consultado para a p�gina
			httpServletRequest.setAttribute("guiaPagamentoHistorico", guiaPagamentoHistorico);
			
			sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamentoHistorico.getId()));
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = fachada.pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			
			
			if(colecaoGuiaPagamentoItem.isEmpty()){
				GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
				guiaPagamentoItem.setDebitoTipo(guiaPagamentoHistorico.getDebitoTipo());
				guiaPagamentoItem.setValorDebito(guiaPagamentoHistorico.getValorDebito());
				colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
			}
			sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecaoGuiaPagamentoItem);
			
			
		}else{	
			
			// GUIA PAGAMENTO
			
//			 Consulta do GuiaPagamento
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
					FiltroGuiaPagamento.ID, guiaPagamentoId));

			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente.ramoAtividade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			// Para a exibi��o do endere�o do im�vel
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("usuario");

			GuiaPagamento guiaPagamento = (GuiaPagamento)Util.retonarObjetoDeColecao
				(fachada.pesquisar(filtroGuiaPagamento,GuiaPagamento.class.getName()));

			// Envia o objeto consultado para a p�gina
			httpServletRequest.setAttribute("guiaPagamento", guiaPagamento);
			
			sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamento.getId()));
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			
			
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = fachada.pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			
			if(colecaoGuiaPagamentoItem.isEmpty()){
				GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
				guiaPagamentoItem.setDebitoTipo(guiaPagamento.getDebitoTipo());
				guiaPagamentoItem.setValorDebito(guiaPagamento.getValorDebito());
				colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
			}
			sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecaoGuiaPagamentoItem);
			
			
		}
		
		// envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaGuiaPagamento") != null) {
			sessao.setAttribute("caminhoRetornoTelaConsultaGuiaPagamento",httpServletRequest.getParameter("caminhoRetornoTelaConsultaGuiaPagamento"));
		}

		return retorno;
	}
	
	/**
	 * Pesquisa o Arrecadador do Movimento
	 * 
	 * @author Davi
	 * @date 21/10/2011
	 * 
	 * @param pagamentoHistorico
	 * @param pagamento
	 * @return
	 */
	private ArrecadadorMovimento pesquisarArrecadadorMovimento(PagamentoHistorico pagamentoHistorico, Pagamento pagamento){
		ArrecadadorMovimento arrecadador = null;
		if(pagamentoHistorico != null){
			if(pagamentoHistorico.getArrecadadorMovimentoItem() != null){
				if(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento() != null){
					FiltroArrecadadorMovimento filtroArrecadador = new FiltroArrecadadorMovimento();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.SEQUENCIA_ARQUIVO, 
						pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()));
					
					Fachada fachada = Fachada.getInstancia();
					Collection<ArrecadadorMovimento> colArrecadador = fachada.pesquisar(filtroArrecadador, ArrecadadorMovimento.class.getName());
					
					arrecadador = (ArrecadadorMovimento) Util.retonarObjetoDeColecao(colArrecadador);
				}
			}
		}else if(pagamento != null){
			if(pagamento.getArrecadadorMovimentoItem() != null){
				if(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento() != null){
					FiltroArrecadadorMovimento filtroArrecadador = new FiltroArrecadadorMovimento();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.SEQUENCIA_ARQUIVO, 
							pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()));
					
					Fachada fachada = Fachada.getInstancia();
					Collection<ArrecadadorMovimento> colArrecadador = fachada.pesquisar(filtroArrecadador, ArrecadadorMovimento.class.getName());
					
					arrecadador = (ArrecadadorMovimento) Util.retonarObjetoDeColecao(colArrecadador);
				}
			}
		}
		
		return arrecadador;
	}
	
}
