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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 9� Aba - Parcelamento efetuados para o im�vel
 * 
 * @author Rafael Santos
 * @since 20/09/2006
 */
public class ExibirConsultarImovelParcelamentosDebitosAction extends GcomAction {

    /**
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("consultarImovelParcelamentosDebitos");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

        //id do imovel da aba documento de cobranca
        String idImovelParcelamentosDebitos = consultarImovelActionForm.getIdImovelParcelamentosDebitos();
        String limparForm = httpServletRequest.getParameter("limparForm");
        String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if(sessao.getAttribute("idImovelPrincipalAba") != null){
			idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		}          
        
        if(limparForm != null && !limparForm.equals("")){
            //limpar os dados 
        	httpServletRequest.setAttribute(
                    "idImovelParcelamentosDebitosNaoEncontrado", null);

        	sessao.removeAttribute("imovelParcelamentosDebitos");
        	sessao.removeAttribute("colecaoParcelamento");
        	sessao.removeAttribute("idImovelPrincipalAba");
        	sessao.removeAttribute("imovelClientes");

			consultarImovelActionForm.setIdImovelDadosComplementares(null);
			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setIdImovelPagamentos(null);
			consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			consultarImovelActionForm.setImovIdAnt(null);

        	consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
        	consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(null);
        	consultarImovelActionForm.setParcelamento(null);
        	consultarImovelActionForm.setReparcelamento(null);
        	consultarImovelActionForm.setReparcelamentoConsecutivo(null);  
        	consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos(null);
            
        }else if( (idImovelParcelamentosDebitos != null && !idImovelParcelamentosDebitos.equalsIgnoreCase(""))
            	|| (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")) ){
            	
        	if(idImovelParcelamentosDebitos != null && !idImovelParcelamentosDebitos.equalsIgnoreCase("")){
        		
           		
        		if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		
        			if(indicadorNovo != null && !indicadorNovo.equals("")){
            			consultarImovelActionForm.setIdImovelParcelamentosDebitos(idImovelParcelamentosDebitos);            		
        				
        			}else if(!(idImovelParcelamentosDebitos.equals(idImovelPrincipalAba))){
            			consultarImovelActionForm.setIdImovelParcelamentosDebitos(idImovelPrincipalAba);            		
                		idImovelParcelamentosDebitos = idImovelPrincipalAba;
            		}
            		
            		
            	}
        	}else if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelPrincipalAba);            		
            		idImovelParcelamentosDebitos = idImovelPrincipalAba;
            } 	                	
        	
	        //Obt�m a inst�ncia da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        Imovel imovel = null;
	        //verifica se o objeto imovel � o mesmo ja pesquisado, para n�o precisar pesquisar mas.
	        boolean imovelNovoPesquisado = false;
	        if(sessao.getAttribute("imovelParcelamentosDebitos") != null){
	        	imovel = (Imovel) sessao.getAttribute("imovelParcelamentosDebitos");
	        	if(!(imovel.getId().toString().equals(idImovelParcelamentosDebitos.trim()))){
	        		imovel = fachada.consultarParcelamentosDebitosImovel(new Integer(idImovelParcelamentosDebitos.trim()));
	        		imovelNovoPesquisado = true;
	        	}
	        }else{
	        	imovel = fachada.consultarParcelamentosDebitosImovel(new Integer(idImovelParcelamentosDebitos.trim()));
	        	imovelNovoPesquisado = true;
	        }
	
            if (imovel != null) {
                sessao.setAttribute("imovelParcelamentosDebitos", imovel);
                sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
                consultarImovelActionForm.setIdImovelParcelamentosDebitos(imovel.getId().toString());
                
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

                //caso o imovel pesquisado seja diferente do pesquisado anterior ou seja a primeira vez que se esteja pesquisando
                if(imovelNovoPesquisado){
	            	//seta na tela a inscri��o do imovel
	                httpServletRequest.setAttribute(
	                        "idImovelParcelamentosDebitosNaoEncontrado", null);
	                
	                consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovelParcelamentosDebitos.trim())));
	                
					//seta a situa��o de agua
					if(imovel.getLigacaoAguaSituacao() != null){
						consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(imovel.getLigacaoAguaSituacao().getDescricao());
					}
					//seta a situa��o de esgoto
					if(imovel.getLigacaoEsgotoSituacao() != null){
						consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(imovel.getLigacaoEsgotoSituacao().getDescricao());
					}

					//numero de parcelamentos
					if (imovel.getNumeroParcelamento() != null)	{
						consultarImovelActionForm.setParcelamento(""
								+ imovel.getNumeroParcelamento());
					}else {
						consultarImovelActionForm.setParcelamento(null);
					}

					//numero de reparcelamento
					if (imovel.getNumeroReparcelamento() != null){
						consultarImovelActionForm.setReparcelamento(""
								+ imovel.getNumeroReparcelamento());
					}else {
						consultarImovelActionForm.setReparcelamento(null);
					}

					//numero de reparcelamento consecutivo
					if (imovel.getNumeroReparcelamentoConsecutivos() != null){
						consultarImovelActionForm.setReparcelamentoConsecutivo(""
								+ imovel.getNumeroReparcelamentoConsecutivos());
					}else {
						consultarImovelActionForm.setReparcelamentoConsecutivo(null);
					}

					FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
					filtroParcelamento.adicionarParametro(new ParametroSimples(
								FiltroParcelamento.IMOVEL_ID, idImovelParcelamentosDebitos.trim()));
					filtroParcelamento
							.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

					Collection<Parcelamento> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName() );
					
					if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()){
						sessao.setAttribute("colecaoParcelamento", colecaoParcelamento);
					}else{
						//Colocado por Raphael Rossiter em 12/01/2007
						sessao.removeAttribute("colecaoParcelamento");
					}
					
					//RM8015 - alterado por Ana Maria - 25/10/2012 
					//[Sb0003] - Exibir Informa��o Im�vel Apto para Sorteio
					Date periodoVencimentoDebitoInicial = Util.converteStringParaDate("01/01/0001");
					Date periodoVencimentoDebitoFinal = new Date();
				
					// Obtendo os d�bitos do imovel
					ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada.obterDebitoImovelOuCliente(
									1,
									imovel.getId().toString(), null, null, "000101",
									"999912", periodoVencimentoDebitoInicial, periodoVencimentoDebitoFinal, 1,
									1, 2, 2, 2, 2, 1, null);
					
					SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
					Date dataSorteio = sistemaParametro.getDataSorteio();
					Date dataLimiteSorteio = sistemaParametro.getDataLimiteCadastroSorteio();
					
					Date dataAtual = new Date();
					
					if(dataSorteio != null && Util.compararData(dataAtual, dataSorteio) !=  1 && fachada.pesquisarImovelCadastradoSorteio(imovel.getId())) {
						consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos("IM�VEL INSCRITO NO SORTEIO");
					}else if(dataLimiteSorteio != null && Util.compararData(dataAtual, dataLimiteSorteio) !=  1){
						if ((colecaoDebitoImovel.getColecaoContasValores()== null 
								|| colecaoDebitoImovel.getColecaoContasValores().size() == 0 )&&
					  	    (colecaoDebitoImovel.getColecaoContasValoresImovel()== null 
					  	    	|| colecaoDebitoImovel.getColecaoContasValoresImovel().size() == 0 )) {
								if(imovel.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO) || 
										imovel.getLigacaoEsgotoSituacao().getId().equals(LigacaoEsgotoSituacao.LIGADO)){
									if(fachada.verificarImovelAptoSorteio(imovel.getId())){
										consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos("IM�VEL APTO N�O INSCRITO NO SORTEIO");
									}else{
										consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos("");
									}
								}else{
									consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos("");
								}
						}else{
							consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos("");
						}
					}else{
						consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos("");
					}
				}
                
            } else {
                httpServletRequest.setAttribute(
                        "idImovelParcelamentosDebitosNaoEncontrado", "true");
                consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos("IM�VEL INEXISTENTE");
                
                //limpar os dados pesquisados
                sessao.removeAttribute("imovelParcelamentosDebitos");
                sessao.removeAttribute("colecaoParcelamento");
                sessao.removeAttribute("idImovelPrincipalAba");

                consultarImovelActionForm.setIdImovelDadosComplementares(null);
				consultarImovelActionForm.setIdImovelDadosCadastrais(null);
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
				consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
				consultarImovelActionForm.setIdImovelDebitos(null);
				consultarImovelActionForm.setIdImovelPagamentos(null);
				consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
				consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
				consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
				consultarImovelActionForm.setImovIdAnt(null);
            	consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(null);
            	consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(null);
            	consultarImovelActionForm.setParcelamento(null);
            	consultarImovelActionForm.setReparcelamento(null);
            	consultarImovelActionForm.setReparcelamentoConsecutivo(null);  
            	consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos(null);
                
            }
        }else{
        	 consultarImovelActionForm.setIdImovelParcelamentosDebitos(idImovelParcelamentosDebitos);
         	httpServletRequest.setAttribute(
                    "idImovelParcelamentosDebitosNaoEncontrado", null);

        	sessao.removeAttribute("imovelParcelamentosDebitos");
        	sessao.removeAttribute("colecaoParcelamento");
        	sessao.removeAttribute("idImovelPrincipalAba");
        	
        	consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(null);
        	consultarImovelActionForm.setParcelamento(null);
        	consultarImovelActionForm.setReparcelamento(null);
        	consultarImovelActionForm.setReparcelamentoConsecutivo(null);        	
        	consultarImovelActionForm.setMsgImovelAptoSorteioParcelamentosDebitos(null);
        
        }

        return retorno;
    }

}
