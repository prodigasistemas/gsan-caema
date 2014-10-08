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
package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelaEntradaDesconto;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de remover um objeto do tipo ParcelaEntradaDesconto
 * da collectionPercentualDesconto
 * 
 * @author Anderson Cabral
 * @created 01/08/2013
 */
public class RemoverPercentualDescontoAction extends GcomAction {
	/**
	 * @author Anderson Cabral
	 * @date 01/08/2013
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
    	
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	ActionForward retorno = actionMapping.findForward("inserirPerfilRemoverPercentualDesconto");	
    	
    	String percentualEntrada = httpServletRequest.getParameter("percentualEntrada");

        if (percentualEntrada != null && !percentualEntrada.equalsIgnoreCase("") 
        		&& sessao.getAttribute("collectionParcelaEntradaDesconto") != null){
        	        	
        	Collection<ParcelaEntradaDesconto> collectionParcelaEntradaDesconto = (Collection<ParcelaEntradaDesconto>) sessao.getAttribute("collectionParcelaEntradaDesconto");
        	
        	ParcelaEntradaDesconto parcelaEntradaDesconto = null;
        	ParcelaEntradaDesconto parcelaEntradaDescontoExcluir = null;
        	Collection<ParcelaEntradaDesconto> collectionParcelaEntradaDescontoLinhaRemovidas = null;
        	
    		if (sessao.getAttribute("collectionParcelaEntradaDescontoLinhaRemovidas") != null
    				&& !sessao.getAttribute("collectionParcelaEntradaDescontoLinhaRemovidas").equals("")) {
    			collectionParcelaEntradaDescontoLinhaRemovidas = (Collection<ParcelaEntradaDesconto>) sessao.getAttribute("collectionParcelaEntradaDescontoLinhaRemovidas");
    		} else {
    			collectionParcelaEntradaDescontoLinhaRemovidas = new ArrayList<ParcelaEntradaDesconto>();
    		}
        	
			Iterator<ParcelaEntradaDesconto> iterator = collectionParcelaEntradaDesconto.iterator();
						
			while (iterator.hasNext()) {
				parcelaEntradaDesconto = (ParcelaEntradaDesconto) iterator.next();

				if (parcelaEntradaDesconto.getPercentualEntrada().toString().equals(percentualEntrada)){
					parcelaEntradaDescontoExcluir =  parcelaEntradaDesconto;
					break;
				}
			}
			
			collectionParcelaEntradaDesconto.remove(parcelaEntradaDescontoExcluir);
       	 	sessao.setAttribute("collectionParcelaEntradaDesconto", collectionParcelaEntradaDesconto);
       	 	
			collectionParcelaEntradaDescontoLinhaRemovidas.add(parcelaEntradaDescontoExcluir);
       	 	sessao.setAttribute("collectionParcelaEntradaDescontoLinhaRemovidas", collectionParcelaEntradaDescontoLinhaRemovidas);       	
        }        
        
       return retorno;
    }
}