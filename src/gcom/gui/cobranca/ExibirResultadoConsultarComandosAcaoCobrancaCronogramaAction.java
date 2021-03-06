/**
 * 
 */
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

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.cobranca.bean.CobrancaAcaoAtividadeCronogramaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;

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
 * Permite consultar comandos de a��o de cobran�a 
 * [UC0325] Consultar Comandos de A��o de Cobran�a - Resultado de Cronograma
 * @author Rafael Santos
 * @since 10/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaCronogramaAction  extends GcomAction{
	
	
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

        ActionForward retorno = actionMapping.findForward("exibirComandosAcaoCobrancaCronograma");
        
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String reloadPage = httpServletRequest.getParameter("reloadPage");
		if(reloadPage != null && reloadPage.equalsIgnoreCase("OK")){
			retorno = actionMapping.findForward("telaSucesso");
			
			montarPaginaSucesso(httpServletRequest, "Data Prevista de Encerramento alterada com sucesso", 
				"Consultar Comando de Atividade de A��o de Cobran�a", 
				"exibirConsultarComandosAcaoCobrancaAction.do?menu=sim");
			
			return retorno;
		}
		
        FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma;
        if (sessao.getAttribute("filtroCobrancaAcaoAtividadeCronograma") != null){
        	filtroCobrancaAcaoAtividadeCronograma = (FiltroCobrancaAcaoAtividadeCronograma) sessao.getAttribute("filtroCobrancaAcaoAtividadeCronograma");
        	
    		Collection colecaoCobrancaAcaoAtividadeCronograma;
    		
    		boolean permissaoEspecial = fachada.verificarPermissaoEspecial(PermissaoEspecial.CANCELAR_VARIOS_COMANDOS_COBRANCA, usuarioLogado);
    		
    		if(permissaoEspecial){
    			sessao.setAttribute("possuiPermissao", true);
    		}else{
    			sessao.removeAttribute("possuiPermissao");
    		}
        	
            Map resultado = controlarPaginacao(httpServletRequest, retorno,
            		filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
            
            colecaoCobrancaAcaoAtividadeCronograma = (Collection) resultado.get("colecaoRetorno");
           	retorno = (ActionForward) resultado.get("destinoActionForward");

      		if (colecaoCobrancaAcaoAtividadeCronograma == null
       				|| colecaoCobrancaAcaoAtividadeCronograma.isEmpty()) {
       			throw new ActionServletException("atencao.pesquisa.nenhumresultado",
       					null, "Comando A��o de Cobran�a - Cronograma");
       		}else{
       		sessao.setAttribute("colecaoCobrancaAcaoAtividadeCronograma",colecaoCobrancaAcaoAtividadeCronograma);
       			
       			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
       			
       			CobrancaAcaoAtividadeCronogramaHelper helper = null;
       			
       			Collection colecaoHelper = new ArrayList();
       			
       			Iterator<?> it = colecaoCobrancaAcaoAtividadeCronograma.iterator();
       			while(it.hasNext()){
       				cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) it.next();
       				
       				helper = new CobrancaAcaoAtividadeCronogramaHelper();
       				
       				helper.setCobrancaAcaoAtividadeCronograma(cobrancaAcaoAtividadeCronograma);
       				helper.setDataRealizacao(fachada.pesquisarDataRealizacaoComandoCronograma(cobrancaAcaoAtividadeCronograma.getId()));
       				
       				colecaoHelper.add(helper);
       			}
       			
       			sessao.setAttribute("colecaoCobrancaAcaoAtividadeCronogramaHelper", colecaoHelper);
       		}
        }
        
        return retorno;
    }

}
