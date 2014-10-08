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
package gcom.gui.seguranca.acesso;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action respons�vel por exibir a p�ginade dados gerais do processo 
 * de inserir grupo.
 * @author Pedro Alexandre
 * @date 15/06/2006
 */
public class ExibirInserirGrupoDadosGeraisAction extends GcomAction {
    
    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * [UC0278] Inserir Grupo
     *
     * @author Pedro Alexandre
     * @date 28/06/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela de dados gerais  
        ActionForward retorno = actionMapping.findForward("inserirGrupoDadosGerais");

        //Obt�m a inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        DynaValidatorForm grupoActionForm = (DynaValidatorForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
  
        if(sessao.getAttribute("grupo") == null){
        	grupoActionForm.set("indicadorGrupoEspecial", ConstantesSistema.SIM);
        }
        
        if(sessao.getAttribute("grupo") == null){
        	grupoActionForm.set("indicadorSuperintendencia", ConstantesSistema.NAO);
        }
        
        if(sessao.getAttribute("grupo") == null){
        	grupoActionForm.set("indicadorVisualizacaoRestrita", ConstantesSistema.NAO);
        }
        if(sessao.getAttribute("grupo") == null){
        	grupoActionForm.set("indicadorEspecialSeguranca", ConstantesSistema.NAO);
        }
        
        
        if(grupoActionForm.get("diasExpiracaoSenha")!=null){
        	
        	if(grupoActionForm.get("diasExpiracaoSenha").toString().equals("0")){
        		grupoActionForm.set("diasExpiracaoSenha", null);
            }
        }
        
        
        
        // Carregar lista de grupo Inferior
 		if (sessao.getAttribute("colecaoGrupoInferior") == null
 				|| sessao.getAttribute("colecaoGrupoInferior").equals("")) {
 			
 			FiltroGrupo filtroGrupo = new FiltroGrupo();
 			
 			filtroGrupo.adicionarParametro(new ParametroSimples(
 					FiltroGrupo.INDICADOR_USO, ConstantesSistema.SIM));
 			
 			filtroGrupo.adicionarParametro(new ParametroSimples(
 					FiltroGrupo.INDICADOR_GRUPO_ESPECIAL, ConstantesSistema.NAO));
 	
 			List<Grupo> grupos = (List<Grupo>) fachada.pesquisar(
 					filtroGrupo, Grupo.class.getName());
 			
 			Comparator<Grupo> comp = new Comparator<Grupo>() {
 				public int compare(Grupo left, Grupo right) {  
 	                return left.getDescricao().compareTo(right.getDescricao());
 	            } 
 			};
 			
 			Collections.sort(grupos, comp);
 			
 			httpServletRequest.setAttribute("colecaoGrupoInferior",grupos);
 		} 
        

      
        /*
         * [RM3892] - Implementar Normas de Senhas no GSAN
         * 
         * @analyst Ana Maria Andrade
         * @autor Th�lio Ara�jo
         * @since 28/12/2011
         */
        
        // 2.1.6. Permiss�o Especial: O sistema exibe a lista das permiss�es especiais
        // com a marca de sele��o (PMEP_DSPERMISSAOESPECIAL da tabela PERMISSAO_ESPECIAL),
        // permitindo que o usu�rio marque ou desmarque as permiss�es.
        SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();        
        if (sistemaParametro.getIndicadorPermissaoEspecialGrupo()==1) {
        	
	        FiltroPermissaoEspecial filtroPemissaoEspecial = new FiltroPermissaoEspecial(FiltroPermissaoEspecial.DESCRICAO);
	        filtroPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPermissaoEspecial.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO)); 
	
	        Collection<PermissaoEspecial> colecaoPermissaoEspecial = Fachada.getInstancia().pesquisar(filtroPemissaoEspecial, PermissaoEspecial.class.getName());
	
	        sessao.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
        }
        
        //Retorna o mapemaneto na vari�vel "retorno"
        return retorno;
    }
}
