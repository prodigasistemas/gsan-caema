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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.NormaArquivos;
import gcom.atendimentopublico.registroatendimento.NormaDocumentoTipo;
import gcom.atendimentopublico.registroatendimento.NormaProcedimentos;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1601] - Filtrar/Manter Normas e Procedimentos
 * 
 * @author Vivianne Sousa
 * @created 15/05/2014
 */
public class AtualizarNormasProcedimentosAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	InserirNormasProcedimentosActionForm inserirNormasProcedimentosActionForm = (InserirNormasProcedimentosActionForm) actionForm;
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
    
        String idNormaProcedimentos = (String)sessao.getAttribute("idRegistroAtualizacao");

    	String idTipoDocumento = inserirNormasProcedimentosActionForm.getIdDocumentoTipo();
    	String descTitulo = inserirNormasProcedimentosActionForm.getDescricaoTitulo();
    	String descAssunto = inserirNormasProcedimentosActionForm.getDescricaoAssunto();

    	Collection<NormaArquivos> colecaoNormaArquivos = null; 
    	
    	if (sessao.getAttribute("colecaoAnexo") != null){
    		colecaoNormaArquivos = (Collection) sessao.getAttribute("colecaoAnexo");
		}
    	
        this.validacaoNormasProcedimentos(idTipoDocumento, descTitulo, descAssunto, colecaoNormaArquivos, httpServletRequest);

        NormaProcedimentos normaProcedimentosAtu = new NormaProcedimentos();
        
        normaProcedimentosAtu.setId(new Integer(idNormaProcedimentos));

    	NormaDocumentoTipo normaDocumentoTipo = new NormaDocumentoTipo();
    	normaDocumentoTipo.setId(new Integer (idTipoDocumento));
    	normaProcedimentosAtu.setNormaDocumentoTipo(normaDocumentoTipo);
        
        normaProcedimentosAtu.setDescricaoTitulo(descTitulo);
        normaProcedimentosAtu.setDescricaoAssunto(descAssunto);
        normaProcedimentosAtu.setUltimaAlteracao(new Date());
    
        this.getFachada().atualizarNormasProcedimentos(normaProcedimentosAtu, colecaoNormaArquivos);
               
		sessao.removeAttribute("idRegistroAtualizacao");
		
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, "Normas e Procedimentos atualizada com sucesso.",
                    "Realizar outra Manutenção de Normas e Procedimentos", "exibirFiltrarNormasProcedimentosAction.do?desfazer=S");
        }
        
       return retorno;
    }
    
    private void validacaoNormasProcedimentos (String idTipoDocumento,String descTitulo,
    		String descAssunto,Collection<NormaArquivos> colecaoNormaArquivos, HttpServletRequest httpServletRequest){
    	
    	if ((idTipoDocumento == null) || (idTipoDocumento.equals(""	+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","idTipoDocumento");
			throw new ActionServletException("atencao.campo.informado", null,"Tipo de Documento");
		}
    	
    	if ((descTitulo == null) || (descTitulo.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","descTitulo");
			throw new ActionServletException("atencao.campo.informado", null,"Título");
		}
    	
    	if ((descAssunto == null) || (descAssunto.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","descAssunto");
			throw new ActionServletException("atencao.campo.informado", null,"Assunto");
		}
    	
    	if(colecaoNormaArquivos == null || colecaoNormaArquivos.isEmpty()){
    		throw new ActionServletException("atencao.campo.informado", null,"Arquivo");
    	}
    }
    
}
 