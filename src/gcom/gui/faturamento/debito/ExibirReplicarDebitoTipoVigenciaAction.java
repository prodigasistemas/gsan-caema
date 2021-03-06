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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0985] Inserir tipo de d�bito com vig�ncia
 * 
 * Este caso de uso permite a inclus�o de um novo debito tipo vigencia 
 *
 * @author Josenildo Neves
 * @date 22/02/2010
 */
public class ExibirReplicarDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("replicarDebitoTipoVigencia");

		ReplicarDebitoTipoVigenciaActionForm replicarDebitoTipoVigenciaActionForm = (ReplicarDebitoTipoVigenciaActionForm) actionForm;

		retorno = this.getDebitoTipoVigencia(replicarDebitoTipoVigenciaActionForm,httpServletRequest, retorno);
		
		return retorno;
		
	}
	
	/**
	 * [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e valor
	 * 
	 * Este m�todo exibe a cole��o de D�bito Tipo Vig�ncia que tem a �ltima data de vig�ncia. 
	 *
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	private ActionForward getDebitoTipoVigencia(ReplicarDebitoTipoVigenciaActionForm form, HttpServletRequest httpServletRequest, ActionForward retorno) {
		
		Collection<DebitoTipoVigencia> colecaoDebitoTipoVigencia = null;
		
		// 1� Passo - Pegar o total de registros atrav�s de um count da
		// consulta que aparecer� na tela
		Integer totalRegistros = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigenciaTotal();

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de
		// registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		if (httpServletRequest.getParameter("page.offset") != null) {
			colecaoDebitoTipoVigencia = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigencia(Integer.parseInt(httpServletRequest.getParameter("page.offset")) - 1);
		
		}else{
			colecaoDebitoTipoVigencia = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigencia(0);
		
		}
		
		if (colecaoDebitoTipoVigencia != null && !colecaoDebitoTipoVigencia.isEmpty()) {
			
			form.setMensagem(true);
			form.setCollDebitoTipoVigencia(colecaoDebitoTipoVigencia);			
			
		} else {
			form.setMensagem(false);
			form.setCollDebitoTipoVigencia(null);
		}
		
		return retorno;
	}
	
}