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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.contratodemanda.ContratoDemandaComercialIndustrial;
import gcom.faturamento.contratodemanda.ContratoDemandaImovelComercialIndustrial;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaComercialIndustrial;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaImovelComercialIndustrial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com os contratos de demanda retornados do
 * FiltrarContratoDemandaAction ou ir para o
 * ExibirAtualizarContratoDemandaAction
 * 
 * @author Rafael Corr�a
 * @since 27/06/2007
 */
public class ExibirManterContratoDemandaAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterContratoDemanda");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("contratoDemandaAtualizar");

		// Recupera o filtro passado pelo FiltrarResolucaoDiretoriaAction para
		// ser efetuada pesquisa
		FiltroContratoDemandaImovelComercialIndustrial filtroContratoDemanda = (FiltroContratoDemandaImovelComercialIndustrial) 
				sessao.getAttribute("filtroContratoDemanda");
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaImovelComercialIndustrial.IMOVEL);
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaImovelComercialIndustrial.
				CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL);
		
		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Collection colecaoContratoDemanda = new ArrayList();
		if(filtroContratoDemanda != null && !filtroContratoDemanda.equals("")){
//			Map resultado = controlarPaginacao(httpServletRequest, retorno,
//					filtroContratoDemanda, ContratoDemandaImovelComercialIndustrial.class.getName());
//			colecaoContratoDemanda = (Collection) resultado
//				.get("colecaoRetorno");
//			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			Collection auxColecaoContratoDemanda = new ArrayList();
			
			colecaoContratoDemanda = (Collection) fachada.pesquisar(filtroContratoDemanda, ContratoDemandaImovelComercialIndustrial.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoContratoDemanda)){
				Collection<Integer> colecaoIdContratoDemanda = new ArrayList<Integer>();
				
				String idContratoDemanda = "";
				Iterator it = colecaoContratoDemanda.iterator();
				while(it.hasNext()){
					ContratoDemandaImovelComercialIndustrial contratoDemandaImovel = (ContratoDemandaImovelComercialIndustrial) it.next();
					
					if(!contratoDemandaImovel.getComp_id().getContratoDemandaComercialIndustrial().getId().toString().equals(idContratoDemanda)){
						auxColecaoContratoDemanda.add(contratoDemandaImovel);
						colecaoIdContratoDemanda.add(contratoDemandaImovel.getComp_id().getContratoDemandaComercialIndustrial().getId());
					}
					
					idContratoDemanda = String.valueOf(contratoDemandaImovel.getComp_id().getContratoDemandaComercialIndustrial().getId());
				}
				
				FiltroContratoDemandaComercialIndustrial novoFiltroContratoDemanda = new FiltroContratoDemandaComercialIndustrial(
						FiltroContratoDemandaComercialIndustrial.ID);
				novoFiltroContratoDemanda.adicionarParametro(new ParametroSimplesIn(FiltroContratoDemandaComercialIndustrial.ID, 
						colecaoIdContratoDemanda));
				
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						novoFiltroContratoDemanda, ContratoDemandaComercialIndustrial.class.getName());
				colecaoContratoDemanda = (Collection) resultado
					.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
			}
			
//			colecaoContratoDemanda = auxColecaoContratoDemanda;
//			
//			retorno = controlarPaginacao(httpServletRequest, retorno, colecaoContratoDemanda.size());
			
		}
		// Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma resolu��o de
		// diretoria cadastrado para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarContratoDemandaAction, se n�o atender manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoContratoDemanda != null
				&& !colecaoContratoDemanda.isEmpty()) {

			// Verifica se a cole��o cont�m apenas um objeto, se est� retornando
			// da pagina��o (devido ao esquema de pagina��o de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usu�rio selecione o link para ir para a
			// segunda p�gina ele n�o v� para tela de atualizar.
			if (colecaoContratoDemanda.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usu�rio marcou o checkbox de atualizar no jsp
				// contrato_demanda_filtrar. Caso todas as condi��es sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarContratoDemandaAction e em caso negativo
				// manda a cole��o pelo request.
				if (sessao.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarContratoDemanda");
					
//					ContratoDemandaImovelComercialIndustrial contratoDemandaImovel = (ContratoDemandaImovelComercialIndustrial) 
//							colecaoContratoDemanda.iterator().next();
//					
//					FiltroContratoDemandaComercialIndustrial filtro = new FiltroContratoDemandaComercialIndustrial();
//					filtro.adicionarParametro(new ParametroSimples(FiltroContratoDemandaComercialIndustrial.ID, 
//							contratoDemandaImovel.getComp_id().getContratoDemandaComercialIndustrial().getId()));
//					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaComercialIndustrial.CONSUMO_TARIFA);
//					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoDemandaComercialIndustrial.CONTRATO_DEMANDA_MOTIVO_ENCERRAMENTO);
					
					ContratoDemandaComercialIndustrial contratoDemanda = (ContratoDemandaComercialIndustrial)
							colecaoContratoDemanda.iterator().next();
					
//					Collection<?> colecao = fachada.pesquisar(filtro, ContratoDemandaComercialIndustrial.class.getName());
//					
//					ContratoDemandaComercialIndustrial contratoDemanda = (ContratoDemandaComercialIndustrial) Util.retonarObjetoDeColecao(colecao);
					
					sessao.setAttribute("contratoDemanda", contratoDemanda);
				} else {
					httpServletRequest.setAttribute(
							"colecaoContratoDemanda",
							colecaoContratoDemanda);
				}
			} else {
				httpServletRequest.setAttribute("colecaoContratoDemanda",
						colecaoContratoDemanda);
			}
		} else {
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
