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
package gcom.gui.cobranca;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioComprovanteCadastramentoSorteio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC1381] Emitir Comprovante de Cadastramento de Sorteio
 * [SB0003] Emitir Comprovante
 * 
 * @author Hugo Azevedo
 * @date 18/10/2012
 * 
 **/
public class GerarComprovanteCadastramentoSorteioAction extends ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		ActionForward retorno = mapping.findForward("telaSucesso");
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		String idImovelString = (String)request.getParameter("idImovel");
		String enviarEmail = (String)request.getParameter("enviarEmail");
		String confirmacao = (String)request.getParameter("confirmacao");
		
		String paginaValidacao = "";
		if (confirmacao != null && confirmacao.trim().equalsIgnoreCase("sim")) {
			paginaValidacao = "confirmacaoCadastroImovelSorteio";
		} else {
			paginaValidacao = "exibirEmitirComprovanteCadastroSorteio";
		}
				
		
		Integer idImovel = new Integer(idImovelString);
		
		
		//[FE0003] Verificar Im�vel Apto para Sorteio
		//===========================================
		//[IT0003] Pesquisar Im�vel Apto para Sorteio
		//--------------------------------------------------------------------------------------------
		//O sistema dever� pesquisar se o im�vel j� foi cadastrado como apto para o sorteio
		Collection  colecaoImovelAptoSorteio =  this.getFachada().pesquisarImovelAptoSorteio(idImovel);	
		//--------------------------------------------------------------------------------------------
		//1. Caso o im�vel n�o esteja cadastrado como apto para o sorteio
		if(colecaoImovelAptoSorteio == null || colecaoImovelAptoSorteio.size() == 0){
			
			//1. o sistema dever� exibir a mensagem "IM�VEL N�O CADASTRADO PARA SORTEIO."
			//   e dever� retornar para o passo correspondente do fluxo principal
			request.setAttribute("mensagemImovelNaoCadastradoSorteio", true);
			return mapping.findForward(paginaValidacao);
			
		}
		//===========================================	
		
		if(enviarEmail != null
				&& enviarEmail.trim().equalsIgnoreCase("sim")) {
			
			//[IT0005] Obter E-mail cadastrado
			String emailCadastrado = this.getFachada().obterEmailCadastrado(idImovel);
			
			//Caso n�o exista nenhum email cadastrado
			if(emailCadastrado == null || emailCadastrado.equals("")){
				request.setAttribute("mensagemEmailNaoCadastrado", true);
				return mapping.findForward(paginaValidacao);
			}
		}
		
			
		RelatorioComprovanteCadastramentoSorteio relatorio = new RelatorioComprovanteCadastramentoSorteio(usuario);
		relatorio.addParametro("tipoFormatoRelatorio",tipoRelatorio);
		relatorio.addParametro("idImovel", idImovel);
		
		if(enviarEmail != null
				&& enviarEmail.trim().equalsIgnoreCase("sim")) {
			relatorio.addParametro("enviarEmail", new Boolean(true));
			request.setAttribute("telaSucessoRelatorio", true);
		} else {
			relatorio.addParametro("enviarEmail", new Boolean(false));
		}
		
		
		retorno = processarExibicaoRelatorio(
			relatorio, tipoRelatorio, request, response, mapping);
		
		if (enviarEmail != null
				&& enviarEmail.trim().equalsIgnoreCase("sim")) {
			request.setAttribute("mensagemEmailEnviado", true);
			retorno = mapping.findForward(paginaValidacao);
		}
		
		return retorno;
			
	}
}