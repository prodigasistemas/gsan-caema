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
* Genival Soares Barbosa Filho
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
package gcom.gui.cobranca;

import gcom.cobranca.ReabrirComandoAtividadeAcaoCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1384] - Reabrir Comando de Atividade de A��o de Cobran�a
 * 
 * @author Hugo Azevedo
 * @date 29/10/2012
 * 
 */

public class ExibirReabrirComandoAtividadeAcaoCobrancaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("reabrirComandoAtivAcaoCobranca");

		//Op��o desfazer
		String desfazer = (String)httpServletRequest.getParameter("desfazer");
		
		//Primeiro Acesso
		String primeiroAcesso = (String)httpServletRequest.getParameter("primeiroAcesso");
		
		ReabrirComandoAtividadeAcaoCobrancaActionForm form = (ReabrirComandoAtividadeAcaoCobrancaActionForm) actionForm;
		
		//Caso desfazer tenha sido selecionado
		if((desfazer != null && desfazer.equals("sim")) || 
			(primeiroAcesso == null || primeiroAcesso.equals(""))){			
			form.reset();
		}
		
		primeiroAcesso = "nao";
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Totalizar OS
		String totalizarOS = (String)httpServletRequest.getParameter("totalizarOS");
		
		//Cole��o de ids de comandos vindos do filtro
		Collection<Integer> colecaoIdsComando =  (Collection<Integer>)sessao.getAttribute("colecaoIdsComando");
		
		//1. Usu�rio seleciona os comandos
		//   tipo de consulta
		String tipoConsulta = (String)sessao.getAttribute("tipoConsulta");
		

		//2. O sistema apresenta os comandos:
		if(tipoConsulta != null){
			
			//2.1. Caso o Tipo do Comando seja "Cronograma"
			if(tipoConsulta.equals("cronograma")){
				
				//2.1. apresenta os comandos de a��o de cobran�a do cronograma
				//     [IT 0001 - Apresenta Comandos de A��o de Cobran�a do Cronograma]
				Collection<ReabrirComandoAtividadeAcaoCobrancaHelper> colecaoHelperCronograma = 
						fachada.obterComandosAcaoCobrancaCronogramaReabrir(colecaoIdsComando);
				
				sessao.setAttribute("colecaoHelperCronograma", colecaoHelperCronograma);
				
				//3. [SB0001 - Totalizar ordem de servi�o]
				//================================================================================
				if(totalizarOS != null && totalizarOS.equals("sim")){
					this.totalizarOS(colecaoHelperCronograma,form);
				}
				//================================================================================
				
				retorno = actionMapping
						.findForward("reabrirComandoAtivAcaoCobrancaCronograma");
			}
			
			//2.2. Caso o Tipo do Comando seja "Eventual"
			else if(tipoConsulta.equals("eventual")){
				
				//2.2. apresenta os comandos de a��o de cobran�a eventuais.
				//     [IT 0002 - Apresenta Comandos de A��o de Cobran�a Eventuais]
				Collection<ReabrirComandoAtividadeAcaoCobrancaHelper> colecaoHelperEventual = 
						fachada.obterComandosAcaoCobrancaEventualReabrir(colecaoIdsComando);
				
				//3. [SB0001 - Totalizar ordem de servi�o]
				//================================================================================
				if(totalizarOS != null && totalizarOS.equals("sim")){
					this.totalizarOS(colecaoHelperEventual,form);
				}
				//================================================================================
				
				sessao.setAttribute("colecaoHelperEventual", colecaoHelperEventual);
				
				retorno = actionMapping
						.findForward("reabrirComandoAtivAcaoCobrancaEventual");
			}
			
		}
		
		httpServletRequest.setAttribute("primeiroAcesso", primeiroAcesso);
		
		return retorno;

	}

	/*
	 * [SB0001 - Totalizar ordem de servi�o]
	 */
	private void totalizarOS(
			Collection<ReabrirComandoAtividadeAcaoCobrancaHelper> colecaoHelper,
			ReabrirComandoAtividadeAcaoCobrancaActionForm form) {
		
		
		//[FE 0001] Verificar Comandos Selecionados
		//1. Caso n�o tenha nenhum comando selecionado
		if(form.getIdsSelecionados() == null || form.getIdsSelecionados().length == 0){
			
			//exibir a mensagem "Selecione pelo menos um comando de a��o de cobran�a"
			throw new ActionServletException("atencao.selecione_um_comando_acao_cob");
		}
		
		//Valor totalizado
		Integer total = new Integer(0);
		
		for(String id : form.getIdsSelecionados()){
			Iterator it = colecaoHelper.iterator();
			while(it.hasNext()){
				ReabrirComandoAtividadeAcaoCobrancaHelper helper = 
						(ReabrirComandoAtividadeAcaoCobrancaHelper)it.next();  
				if(helper.getIdAtividade().equals(id)){
					total = total + new Integer(helper.getQtdOS());
					helper.setChecked(true);
					break;
				}
				else{
					if(!helper.isChecked())
						helper.setChecked(false);
				}
			}
		}

		form.setTotalOSDescursoPrazo(total.toString());
		
	}

}