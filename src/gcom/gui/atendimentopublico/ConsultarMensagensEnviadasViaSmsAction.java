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
* along with this program; if not, wrie to the Free Software
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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.MensagemSms;
import gcom.atendimentopublico.bean.MensagemSmsHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1388] Consultar Quantidade Acessos a Base da Receita Federal
 *
 * @author Ricardo Germinio
 * @date 27/11/2012
 *
 */

public class ConsultarMensagensEnviadasViaSmsAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		ActionForward retorno = actionMapping
				.findForward("consultarMensagensEnviadasViaSms");
		
		ExibirConsultarMensagensEnviadasViaSmsActionForm form = (ExibirConsultarMensagensEnviadasViaSmsActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		this.executarValidacoes(form);
		
		MensagemSmsHelper helper =
			this.criarHelper(form);
		
		sessao.setAttribute("filtro", helper);
	
		Collection<MensagemSms> colecaoMensagemSms =
			this.getFachada().consultarMensagensEnviadasViaSms(helper);
		
		if(Util.isVazioOrNulo(colecaoMensagemSms)){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		sessao.setAttribute("colecaoMensagemSms",colecaoMensagemSms);
		return retorno;
	}
	
	/**
	 * Método que filtra quantidade de acessos a base da receita federal 
	 * 
	 * @param ExibirConsultarQuantidadeAcessosReceitaFederalActionForm form
	 * @return retorno
	 */
	
	private MensagemSmsHelper criarHelper(
			ExibirConsultarMensagensEnviadasViaSmsActionForm form) {
		
		MensagemSmsHelper retorno = 
			new MensagemSmsHelper();
		
		
		
		if (Util.verificarNaoVazio(form.getDataInicial())){
			
			Date dataInicial = 
				Util.converteStringParaDate(form.getDataInicial());
			
			retorno.setDataInicial(dataInicial);
		}
		
		if (Util.verificarNaoVazio(form.getDataFinal())){
			
			Date dataFinal = 
				Util.converteStringParaDate(form.getDataFinal());
			
			retorno.setDataFinal(dataFinal);
		}		
		
		retorno.setSituacaoMensagem(new Short(form.getSituacaoMensagem()));
		retorno.setTipoSms(form.getTipoSms());
		
		return retorno;
	}
	
	/**
	 * Método que Executa as validações dos campos de exibir consultar quantidade de
	 * acessos a base da receita federal 
	 * 
	 * @param ExibirConsultarQuantidadeAcessosReceitaFederalActionForm form
	 */
	
	private void executarValidacoes(ExibirConsultarMensagensEnviadasViaSmsActionForm form) {
		
		//Valida Datas no filtro.
		if (Util.verificarNaoVazio(form.getDataInicial())
				&& Util.verificarNaoVazio(form.getDataFinal())) {
	
			Date dataInicial = Util.converteStringParaDate(form
					.getDataInicial());
			Date dataFinal = Util.converteStringParaDate(form
					.getDataFinal());
			
			if (dataFinal.compareTo(dataInicial) < 0) {
				throw new ActionServletException(
						"atencao.data_final_periodo.anterior.data_inicial_periodo");
			}
			
			
		}
	}
}