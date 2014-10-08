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
package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.AreaDistritoOperacional;
import gcom.operacional.AreaOperacional;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1605]	Manter Área Operacional
 * 
 * @author Ana Maria
 * @date 05/06/2014
 */
public class AtualizarAreaOperacionalAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	InserirAreaOperacionalActionForm inserirAreaOperacionalActionForm = (InserirAreaOperacionalActionForm) actionForm;
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
    
        String idAreaOperacional = (String)sessao.getAttribute("idRegistroAtualizacao");
   	
		AreaOperacional areaOperacional = new AreaOperacional();
		
		areaOperacional.setId(new Integer(idAreaOperacional));

		areaOperacional.setDescricao(inserirAreaOperacionalActionForm.getDescricao().toUpperCase());
		
		areaOperacional.setIndicadorUso(Short.valueOf(inserirAreaOperacionalActionForm.getIndicadorUso()));
		
		areaOperacional.setUltimaAlteracao(new Date());
		
		if(inserirAreaOperacionalActionForm.getDescricaoAbreviada() != null && 
	        		!inserirAreaOperacionalActionForm.getDescricaoAbreviada().equals("")){
			areaOperacional.setDescricaoAbreviada(inserirAreaOperacionalActionForm.getDescricaoAbreviada().toUpperCase());
		}
		
		Collection<AreaDistritoOperacional> colecaoAreaDistritoOperacional = (Collection) sessao.getAttribute("colecaoAreaDistritoOperacional");
		
		if (colecaoAreaDistritoOperacional != null && !colecaoAreaDistritoOperacional.isEmpty()) {	
			colecaoAreaDistritoOperacional = (ArrayList<AreaDistritoOperacional>) sessao.getAttribute("colecaoAreaDistritoOperacional");
				
			// Indica o distrito principal selecionado
			String indicadorPrincipal = inserirAreaOperacionalActionForm.getIndicadorPrincipal();
			if (indicadorPrincipal != null && !indicadorPrincipal.isEmpty()) {
				Long indicadorPrincipalLong = Long.parseLong(indicadorPrincipal);
				Iterator<AreaDistritoOperacional> iterator = colecaoAreaDistritoOperacional.iterator();
				while (iterator.hasNext()) {
					AreaDistritoOperacional areaDistritoOperacional = (AreaDistritoOperacional) iterator.next();

					if (obterTimestampIdObjeto(areaDistritoOperacional) == indicadorPrincipalLong
							.longValue()) {
						areaDistritoOperacional.setIndicadorPrincipal(ConstantesSistema.SIM);
					} else {
						areaDistritoOperacional.setIndicadorPrincipal(ConstantesSistema.NAO);
					}
				}

			} else {
				// Nenhum distrito foi indicado como principal
				throw new ActionServletException(
						"atencao.distrito_principal.nao_selecionado");
			}
			areaOperacional.setColecaoAreaDistritoOperacional(colecaoAreaDistritoOperacional);
        }else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null,"pelo menos um Distrito Operacional");
        }
    
        this.getFachada().atualizarAreaOperacional(areaOperacional);
               
		sessao.removeAttribute("idRegistroAtualizacao");
		
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, "Área Operacional atualizada com sucesso.",
                    "Realizar outra Manutenção de Área Operacional", "exibirFiltrarAreaOperacionalAction.do?desfazer=S");
        }
        
       return retorno;
    }
    
}
 