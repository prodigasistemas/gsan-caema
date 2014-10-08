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
package gcom.gui.cadastro.localidade;

import gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupo;
import gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupoPK;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.bean.InserirTramitesGruposSolicitacoesHelper;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirTramitesGruposSolicitacoesAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        
        //Form
      	InserirTramitesGruposSolicitacoesActionForm form = (InserirTramitesGruposSolicitacoesActionForm) actionForm;
        
      	//Ids inseridos
        String idLocalidadeInserida = form.getIdLocalidade();
        String idUnidadeOrgInserida = form.getIdUnidadeOrganizacional();
        
        //Unidade Organizacional inserida
        UnidadeOrganizacional unidadeInserida = fachada.pesquisarUnidadeOrganizacional(new Integer(idUnidadeOrgInserida));
        
        //Localidade Inserida
        Localidade localidade = new Localidade();
        localidade.setId(new Integer(idLocalidadeInserida));
        
        Collection colecaoHelperBusca = (Collection)sessao.getAttribute("colecaoTramitesGruposSolicitacoesHelper");
        
        atualizarColecaoHelper(colecaoHelperBusca,form.getIdsUnidadeOrganizacional(),form.getDescricoesUnidadeOrganizacional());
           
        
           //[FS0011] - Verificar Unidade Organizacional para Tr�mite.
	       Iterator validador = colecaoHelperBusca.iterator();
	       while(validador.hasNext()){
    	   
	    	   InserirTramitesGruposSolicitacoesHelper helper = (InserirTramitesGruposSolicitacoesHelper)validador.next();
	    	   UnidadeOrganizacional unidadeBusca = null;
	    	   
	    	   if(helper.getUnidadeOrganizacional().getId() != null)
	    		   unidadeBusca = fachada.pesquisarUnidadeOrganizacional(helper.getUnidadeOrganizacional().getId());
	    	   
	    	   //Caso alguma unidade organizacional de tr�mite n�o foi informada
	    	   if(unidadeBusca == null){
	    		   throw new ActionServletException("atencao.informe_unidades_sugeridas_tramite");
	    	   }
	    	   else{
	    		   if(unidadeBusca.getUnidadeSuperior() == null ||
	    				   unidadeBusca.getUnidadeSuperior().getId().compareTo(unidadeInserida.getUnidadeSuperior().getId()) != 0){
						throw new ActionServletException("atencao.unidade_tramite_fora_unidade_negocio");
	    		   }
	    	   }   
	       }
	       
	       
	       //[SB0002] - Inserir Tr�mites dos Grupos de Solicita��es
	       //Para cada item do grupo de solicita��o e unidade correspondente o sistema inclui os dados na tabela
	       Iterator inserirHelper = colecaoHelperBusca.iterator();
	       while(inserirHelper.hasNext()){
	    	   
	    	   InserirTramitesGruposSolicitacoesHelper helper = (InserirTramitesGruposSolicitacoesHelper)inserirHelper.next();
	    	   
	    	   LocalidadeSolicTipoGrupo localidadeSOlicTipoGr = new LocalidadeSolicTipoGrupo();
	    	   localidadeSOlicTipoGr.setLocalidade(localidade);
	    	   localidadeSOlicTipoGr.setSolicitacaoTipoGrupo(helper.getSolicitacaoTipoGrupo());
	    	   localidadeSOlicTipoGr.setUltimaAlteracao(new Date());
	    	   localidadeSOlicTipoGr.setUnidadeOrganizacional(helper.getUnidadeOrganizacional());
	    	   localidadeSOlicTipoGr.setComp_id(new LocalidadeSolicTipoGrupoPK(localidade.getId(),helper.getSolicitacaoTipoGrupo().getId()));
	    	   
	    	   fachada.inserir(localidadeSOlicTipoGr);
	    	   
	       }
	       
			
	       montarPaginaSucesso(httpServletRequest, "Localidade de c�digo  "
                + idLocalidadeInserida
                + " inserida com sucesso. "
                + " <br> Unidade Organizacional de c�digo "
                + idUnidadeOrgInserida
                + " inserida com sucesso. ", "Inserir outra Localidade",
                "exibirInserirLocalidadeAction.do?menu=sim",
                "exibirAtualizarLocalidadeAction.do?idRegistroInseridoAtualizar="
				+ idLocalidadeInserida,
				"Atualizar Localidade Inserida");


        //devolve o mapeamento de retorno
        
        return retorno;
    }
    
    
    
    private void atualizarColecaoHelper(Collection colecaoHelper, String[] idsUnidades, String[] descricoes){
    	
    	Iterator it = colecaoHelper.iterator();
    	int contador = 0;
    	while(it.hasNext()){
    		
    		InserirTramitesGruposSolicitacoesHelper helper = (InserirTramitesGruposSolicitacoesHelper)it.next();
    		
    		Integer idUnidade = null;
    		String descricao = null;
    		if(idsUnidades[contador] != null && 
    				!idsUnidades[contador].equals("") 
    				&& Util.validarStringNumerica(idsUnidades[contador])){
    			idUnidade = new Integer(idsUnidades[contador]);
    			descricao = descricoes[contador];
    		}
    		else
    			idUnidade = null;
    		
    		UnidadeOrganizacional unidade = new UnidadeOrganizacional();
    		unidade.setId(idUnidade);
    		unidade.setDescricao(descricao);
    		helper.setUnidadeOrganizacional(unidade);
    		
    		contador++;
    	}
    }
    

}
