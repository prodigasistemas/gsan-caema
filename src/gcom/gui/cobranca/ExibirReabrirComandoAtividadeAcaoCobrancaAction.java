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
* Genival Soares Barbosa Filho
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
 * [UC1384] - Reabrir Comando de Atividade de Ação de Cobrança
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

		//Opção desfazer
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
		
		//Coleção de ids de comandos vindos do filtro
		Collection<Integer> colecaoIdsComando =  (Collection<Integer>)sessao.getAttribute("colecaoIdsComando");
		
		//1. Usuário seleciona os comandos
		//   tipo de consulta
		String tipoConsulta = (String)sessao.getAttribute("tipoConsulta");
		

		//2. O sistema apresenta os comandos:
		if(tipoConsulta != null){
			
			//2.1. Caso o Tipo do Comando seja "Cronograma"
			if(tipoConsulta.equals("cronograma")){
				
				//2.1. apresenta os comandos de ação de cobrança do cronograma
				//     [IT 0001 - Apresenta Comandos de Ação de Cobrança do Cronograma]
				Collection<ReabrirComandoAtividadeAcaoCobrancaHelper> colecaoHelperCronograma = 
						fachada.obterComandosAcaoCobrancaCronogramaReabrir(colecaoIdsComando);
				
				sessao.setAttribute("colecaoHelperCronograma", colecaoHelperCronograma);
				
				//3. [SB0001 - Totalizar ordem de serviço]
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
				
				//2.2. apresenta os comandos de ação de cobrança eventuais.
				//     [IT 0002 - Apresenta Comandos de Ação de Cobrança Eventuais]
				Collection<ReabrirComandoAtividadeAcaoCobrancaHelper> colecaoHelperEventual = 
						fachada.obterComandosAcaoCobrancaEventualReabrir(colecaoIdsComando);
				
				//3. [SB0001 - Totalizar ordem de serviço]
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
	 * [SB0001 - Totalizar ordem de serviço]
	 */
	private void totalizarOS(
			Collection<ReabrirComandoAtividadeAcaoCobrancaHelper> colecaoHelper,
			ReabrirComandoAtividadeAcaoCobrancaActionForm form) {
		
		
		//[FE 0001] Verificar Comandos Selecionados
		//1. Caso não tenha nenhum comando selecionado
		if(form.getIdsSelecionados() == null || form.getIdsSelecionados().length == 0){
			
			//exibir a mensagem "Selecione pelo menos um comando de ação de cobrança"
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