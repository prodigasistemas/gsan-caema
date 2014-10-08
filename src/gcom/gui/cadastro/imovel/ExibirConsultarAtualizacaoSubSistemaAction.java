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
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.imovel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.SubSistemaEsgotoArquivoTexto;
import gcom.operacional.SubSistemaEsgotoArquivoTextoErro;
import gcom.operacional.bean.SubSistemaEsgotoArquivoTextoHelper;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
 * @author Anderson Cabral
 * @since 07/07/2013
 * 
 */
public class ExibirConsultarAtualizacaoSubSistemaAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        // localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping
                .findForward("exibirConsultarAtualizacaoSubSistemaAction");
        ConsultarAtualizacaoSubSistemaActionForm consultarAtualizacaoSubSistemaActionForm = (ConsultarAtualizacaoSubSistemaActionForm) actionForm;
        //obtém a instância da sessão
        HttpSession sessao = getSessao(httpServletRequest);
        
        Fachada fachada = Fachada.getInstancia();
        Date dataInicial = null;
        Date dataFinal = null;
        
        String dataInicialStr = consultarAtualizacaoSubSistemaActionForm.getDataInicio();
        String dataFinalStr   = consultarAtualizacaoSubSistemaActionForm.getDataFinal();
        
        if(Util.validarDataInvalida(dataInicialStr, "dd/mm/yyyy")){
        	dataInicial = Util.converteStringParaDate(dataInicialStr);
        }
        
        if(Util.validarDataInvalida(dataFinalStr, "dd/mm/yyyy")){
        	dataFinal = Util.converteStringParaDate(dataFinalStr);
        }
        
        if(httpServletRequest.getParameter("pesquisar") != null){
        	
        	
    		if ( dataInicial != null && dataFinal == null ) {
    			throw new ActionServletException("atencao.campo.informado", "Periodo Execução Final");
    		} else if ( dataFinal != null && dataInicial == null ) {
    			throw new ActionServletException("atencao.campo.informado", "Periodo Execução Inicial");
    		} else if ( dataInicial != null && dataFinal != null && Util.compararData(dataInicial, dataFinal) > 0 ) {
    			throw new ActionServletException("atencao.periodo_execucao_final_menor_inicial");
    		}
        	
	        Collection<SubSistemaEsgotoArquivoTexto> colecaoSubSistemaEsgotoArquivoTexto = null;
	        ArrayList<SubSistemaEsgotoArquivoTextoErro> colecaoSubSistemaEsgotoArquivoTextoErro = null;
	        ArrayList<SubSistemaEsgotoArquivoTextoHelper> colecaoSubSistemaEsgotoArquivoHelper = new ArrayList<SubSistemaEsgotoArquivoTextoHelper>();
	        
	        colecaoSubSistemaEsgotoArquivoTexto	= fachada.pesquisarSubSistemaEsgotoArquivoTexto(dataInicial, dataFinal);
	        
	        if(colecaoSubSistemaEsgotoArquivoTexto != null){
	        	
	        	for(SubSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto : colecaoSubSistemaEsgotoArquivoTexto){
	        		colecaoSubSistemaEsgotoArquivoTextoErro = (ArrayList<SubSistemaEsgotoArquivoTextoErro>) fachada.pesquisarSubSistemaEsgotoArquivoTextoErro(subSistemaEsgotoArquivoTexto.getId());
	        		
	        		SubSistemaEsgotoArquivoTextoHelper helper = new SubSistemaEsgotoArquivoTextoHelper();
	        		helper.setSubSistemaEsgotoArquivoTexto(subSistemaEsgotoArquivoTexto);
	        		
	        		
	        		if(colecaoSubSistemaEsgotoArquivoTextoErro != null){
	        			helper.setSubSistemaEsgotoArquivoTextoErro(colecaoSubSistemaEsgotoArquivoTextoErro);
	        			helper.setQuantidadeErros(colecaoSubSistemaEsgotoArquivoTextoErro.size());
	        		}
	        		
	        		colecaoSubSistemaEsgotoArquivoHelper.add(helper);
	        		
	        	}
	        }
	        
	        sessao.setAttribute("colecaoSubSistemaEsgotoArquivoHelper", colecaoSubSistemaEsgotoArquivoHelper);
        }
        
        return retorno;
    }

}