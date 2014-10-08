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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroNormaDocumentoTipo;
import gcom.atendimentopublico.registroatendimento.NormaDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1601] - Filtrar/Manter Normas e Procedimentos
 * [UC1602] - Consultar Normas e Procedimentos
 * 
 * @author Vivianne Sousa
 * @created 13/05/2014
 */
public class ExibirFiltrarNormasProcedimentosAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        ActionForward retorno = actionMapping.findForward("filtrarNormasProcedimentos");
        NormaProcedimentosActionForm normaProcedimentosActionForm = (NormaProcedimentosActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
//        HttpSession sessao = httpServletRequest.getSession(false);

        FiltroNormaDocumentoTipo filtroNormaDocumentoTipo = new FiltroNormaDocumentoTipo();
        filtroNormaDocumentoTipo.setCampoOrderBy(FiltroNormaDocumentoTipo.DESCRICAO);
        filtroNormaDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroNormaDocumentoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection<NormaDocumentoTipo> colecaoNormaDocumentoTipo = fachada.pesquisar(filtroNormaDocumentoTipo, NormaDocumentoTipo.class.getName());
        httpServletRequest.setAttribute("colecaoNormaDocumentoTipo", colecaoNormaDocumentoTipo);
        
        if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	normaProcedimentosActionForm.setIdTipoDocumento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	normaProcedimentosActionForm.setDescTitulo("");
        	normaProcedimentosActionForm.setDescAssunto("");

//        	sessao.setAttribute("indicadorAtualizar","1");
        }

	    // c�digo para checar ou n�o o Atualizar
//	    String primeiraVez = httpServletRequest.getParameter("menu");
//		if (primeiraVez != null && !primeiraVez.equals("")
//        && sessao.getAttribute("voltar") !=null && sessao.getAttribute("voltar").equals("filtrar")) {
//			sessao.setAttribute("indicadorAtualizar","1");
//		}
//		sessao.removeAttribute("voltar");
//		sessao.removeAttribute("idRegistroAtualizacao");

        
       return retorno;
    }

}
 