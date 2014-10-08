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
package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroMotivoRetificacaoConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1385] - Conceder Crédito Conjunto de Contas 
 *
 * @author Davi Menezes
 * @date 19/10/2012
 */
public class ExibirConcederCreditoConjuntoContaAction extends GcomAction {

	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	    	ActionForward retorno = actionMapping.findForward("exibirConcederCreditoConjuntoConta");
	        
	        ConcederCreditoConjuntoContaActionForm concederCreditoConjuntoContaActionForm = (ConcederCreditoConjuntoContaActionForm) actionForm;
	        
	        Fachada fachada = Fachada.getInstancia();
	        
	        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
	        
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        String pesquisarRA = httpServletRequest.getParameter("pesquisarRA");
	        if(pesquisarRA != null && pesquisarRA.equalsIgnoreCase("sim")){
	        	if(concederCreditoConjuntoContaActionForm.getValorCredito() != null 
	        			&& !concederCreditoConjuntoContaActionForm.getValorCredito().equals("0,00")){
	        		sessao.setAttribute("valorCredito", concederCreditoConjuntoContaActionForm.getValorCredito());
	        	}else{
	        		sessao.removeAttribute("valorCredito");
	        	}
	        	
	        	httpServletRequest.setAttribute("reloadPage", true);
	        	
	        	return retorno;
	        }
	        
	        /*
	         * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
	         * CHAMADO PELO MANTER CONTA DE UM CONJUNTO DE IMÓVEIS
	         */
	        this.recebimentoParametrosManterContaConjuntoImoveis(httpServletRequest, sessao);
	        
	        // -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 02/07/2010 
			// Analista :  Fabiola Araujo.
	        // [SB0008] Retificar um Conjunto de Contas
			//-------------------------------------------------------------------------------------------
	        
	        if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
	        	Date dtCorrente = new Date();	
	        	
	        	Integer diasAdicionais = 0;
	        	
	        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
	        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
	        	}
	        	
				Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dtCorrente, diasAdicionais.intValue());
				
//				Collection<Conta> colecaoContas = (Collection<Conta>) sessao.getAttribute("colecaoContas");
//				
//				boolean verificarConta = false;
//				if(!Util.isVazioOrNulo(colecaoContas)){
//					Iterator<?> it = colecaoContas.iterator();
//					while(it.hasNext()){
//						Conta conta = (Conta) it.next();
//						
//						if(dataCorrenteComDias.before(conta.getDataVencimentoConta())){
//							verificarConta = true;
//							break;
//						}
//					}
//				}
//				
//				if(verificarConta){
//					concederCreditoConjuntoContaActionForm.setVencimentoConta("");
//				}else{
//					concederCreditoConjuntoContaActionForm.setVencimentoConta(Util.formatarData(dataCorrenteComDias));
//				}
				
				concederCreditoConjuntoContaActionForm.setVencimentoConta(Util.formatarData(dataCorrenteComDias));
	        	
	        	httpServletRequest.setAttribute("desabilitaCalendario", true);
	        }else{
	        	concederCreditoConjuntoContaActionForm.setVencimentoConta("");
	        	
	        	httpServletRequest.removeAttribute("desabilitaCalendario");
	        }
	        // -------------------------------------------------------------------------------------------
	        
	        String tipoConsulta = (String) httpServletRequest.getParameter("tipoConsulta");
	        if(tipoConsulta != null && !tipoConsulta.equals("")){
	        	if(tipoConsulta.equalsIgnoreCase("registroAtendimento")){
	        		String id = httpServletRequest.getParameter("idCampoEnviarDados");
	    			concederCreditoConjuntoContaActionForm.setNumeroRA(id);
	    			
	    			if((String) sessao.getAttribute("idMotivoRetificacao") != null){
	    				concederCreditoConjuntoContaActionForm.setMotivoRetificacaoID((String) sessao.getAttribute("idMotivoRetificacao"));
	    			}
	    			
	    			if((String) sessao.getAttribute("valorCredito") != null){
	    				concederCreditoConjuntoContaActionForm.setValorCredito((String) sessao.getAttribute("valorCredito"));
	    			}
	    		}
	        	
	        	if(tipoConsulta.equals("1")){
	        		if(concederCreditoConjuntoContaActionForm.getMotivoRetificacaoID() != null 
	        			&& !concederCreditoConjuntoContaActionForm.getMotivoRetificacaoID().equals("-1")){
	        			sessao.setAttribute("idMotivoRetificacao", concederCreditoConjuntoContaActionForm.getMotivoRetificacaoID());
	        		}else{
	        			sessao.removeAttribute("idMotivoRetificacao");
	        		}
	        		
	        		if(concederCreditoConjuntoContaActionForm.getValorCredito() != null 
	        			&& !concederCreditoConjuntoContaActionForm.getValorCredito().equals("0,00")){
	        			sessao.setAttribute("valorCredito", concederCreditoConjuntoContaActionForm.getValorCredito());
	        		}else{
	        			sessao.removeAttribute("valorCredito");
	        		}
	        		
	        	}
	        	
	        	if(concederCreditoConjuntoContaActionForm.getNumeroRA() != null && !concederCreditoConjuntoContaActionForm.getNumeroRA().equals("")){
	        		this.pesquisarRegistroAtendimento(concederCreditoConjuntoContaActionForm, fachada, httpServletRequest);
	        	}
	        }

	        /*CARREGAMENTO INICIAL DO FORMULÁRIO
	        ========================================================================================== */
	        
	        String inicio = httpServletRequest.getParameter("inicio");
	        if(inicio != null && inicio.equalsIgnoreCase("sim")){
	        	concederCreditoConjuntoContaActionForm.setMotivoRetificacaoID(ConstantesSistema.NUMERO_NAO_INFORMADO + "");
	        	concederCreditoConjuntoContaActionForm.setNumeroRA("");
	        	concederCreditoConjuntoContaActionForm.setDescricaoRA("");
	        	concederCreditoConjuntoContaActionForm.setValorCredito("0,00");
	        }
	        
	        //Motivo da Retificação (Carregar coleção) e remover as coleções que ainda estão na sessão
	        Collection<?> colecaoMotivoRetificacaoConta;
	        
	        if (sessao.getAttribute("colecaoContaMotivoRetificacao") == null) {
	        	
	        	FiltroMotivoRetificacaoConta filtroMotivoRetificacaoConta = new FiltroMotivoRetificacaoConta(
	        			FiltroMotivoRetificacaoConta.DESCRICAO_MOTIVO_RETIFICACAO_CONTA);

	        	filtroMotivoRetificacaoConta.adicionarParametro(new ParametroSimples(
	        			FiltroMotivoRetificacaoConta.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));
	        	
	        	filtroMotivoRetificacaoConta.adicionarParametro(new ParametroSimples(
	        			FiltroMotivoRetificacaoConta.INDICADOR_CONCEDER_CREDITO, 
	        			ConstantesSistema.SIM));

	        	colecaoMotivoRetificacaoConta = fachada.pesquisar(filtroMotivoRetificacaoConta,
	        			ContaMotivoRetificacao.class.getName());

	            if (colecaoMotivoRetificacaoConta == null
	                    || colecaoMotivoRetificacaoConta.isEmpty()) {
	                throw new ActionServletException(
	                        "atencao.pesquisa.nenhum_registro_tabela", null,
	                        "MOTIVO_RETIFICACAO_CONTA");
	            } else {
	                sessao.setAttribute("colecaoContaMotivoRetificacao",
	                		colecaoMotivoRetificacaoConta);
	            }
	        }
	        
	        //Carregar a data corrente do sistema
	        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	        Calendar dataCorrente = new GregorianCalendar();
	        
	        //Ultimo dia do mês corrente.        
	        Date ultimaDataMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente.getTime()), Util.getAno(dataCorrente.getTime()));        
	        httpServletRequest.setAttribute("ultimaDataMes", formatoData.format(ultimaDataMes));
	        
	        httpServletRequest.setAttribute("indicadorCalculaVencimento", sistemaParametro.getIndicadorCalculaVencimento());
	        
	        //Data Corrente
	        httpServletRequest.setAttribute("dataAtual", formatoData
	        .format(dataCorrente.getTime()));
	        
	        //Data Corrente + 60 dias
	        dataCorrente.add(Calendar.DATE, 60);
	        httpServletRequest.setAttribute("dataAtual60", formatoData
	        .format(dataCorrente.getTime()));
	        
	        /*
	         * Costante que informa o ano limite para o campo anoMesReferencia da conta
	         */
	        httpServletRequest.setAttribute("anoLimite", ConstantesSistema.ANO_LIMITE);
	        
	        //========================================================================================
	        
	        
	        httpServletRequest.setAttribute("nomeCampo", "vencimentoConta");
	        
	        
	        return retorno;
	 }
	 
	 /**
	  * Pesquisar Registro de Atendimento pelo Número da RA
	  * 
	  * @author Davi Menezes
	  * @date 19/10/2012
	  */
	 private void pesquisarRegistroAtendimento(ConcederCreditoConjuntoContaActionForm form, Fachada fachada, HttpServletRequest request){
		 FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		 filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, form.getNumeroRA()));
		 filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, ConstantesSistema.PENDENTE));
		 filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO, "110"));
		 filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO);
		 filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO);
		 
		 Collection<?> colRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
		 if(!Util.isVazioOrNulo(colRegistroAtendimento)){
			 RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colRegistroAtendimento);
			 
			 form.setNumeroRA(registroAtendimento.getId().toString());
			 form.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			 
			 request.setAttribute("numeroRAEncontrada", true);
		 }else{
			 form.setNumeroRA("");
			 form.setDescricaoRA("Registro Atendimento Inexistente");
			 
			 request.removeAttribute("numeroRAEncontrada");
		 }
	 }
	 
	/**
	 * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
	 * CHAMADO PELO MANTER CONTA DE UM CONJUNTO DE IMÓVEIS
	 *
	 * @author Raphael Rossiter
	 * @date 05/06/2009
	 *
	 * @param httpServletRequest
	 * @param sessao
	 */
	private void recebimentoParametrosManterContaConjuntoImoveis(HttpServletRequest httpServletRequest,
			 HttpSession sessao){
		
		Integer anoMes = null;
		Integer anoMesFim = null;
		Date dataVencimentoContaInicio = null;
		Date dataVencimentoContaFim = null;
		String indicadorContaPaga = null;
		Integer idGrupoFaturamento = null;
		String codigoCliente = null;
		
		if(httpServletRequest.getParameter("mesAno") != null){
    		anoMes = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAno"));	
    		sessao.setAttribute("anoMes", anoMes);
    	}
    	
    	if(httpServletRequest.getParameter("mesAnoFim") != null){
    		anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAnoFim"));	
    		sessao.setAttribute("anoMesFim", anoMesFim);
    	}
    	
		if (httpServletRequest.getParameter("dataVencimentoContaInicial") != null){
			
			dataVencimentoContaInicio = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaInicial"));
			sessao.setAttribute("dataVencimentoContaInicial", dataVencimentoContaInicio);
		}
		
		if (httpServletRequest.getParameter("dataVencimentoContaFinal") != null){
			
			dataVencimentoContaFim = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaFinal"));
			sessao.setAttribute("dataVencimentoContaFinal", dataVencimentoContaFim);
		}
		
		if (httpServletRequest.getParameter("indicadorContaPaga") != null){
			
			indicadorContaPaga = httpServletRequest.getParameter("indicadorContaPaga");
			
			if(!indicadorContaPaga.equals("2")){
				throw new ActionServletException("atencao.existem_contas_pagas_conceder_credito");
			}
			
			sessao.setAttribute("indicadorContaPaga", indicadorContaPaga);
		}
		
		if (httpServletRequest.getParameter("idGrupoFaturamento") != null){
			
			idGrupoFaturamento = new Integer((String) httpServletRequest.getParameter("idGrupoFaturamento"));
			sessao.setAttribute("idGrupoFaturamento", idGrupoFaturamento);
		}
		
		if (httpServletRequest.getParameter("codigoCliente") != null){
			
			codigoCliente = (String) httpServletRequest.getParameter("codigoCliente");
			sessao.setAttribute("codigoCliente", codigoCliente);
		}
		
	}
	
}
