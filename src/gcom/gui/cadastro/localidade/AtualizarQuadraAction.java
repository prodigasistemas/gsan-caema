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

import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/***
 * @author Administrador, Ivan Sergio
 * @date 16/02/2009
 * @alteracao 16/02/2009 - CRC1178 - Adicionado o Indicador de Incremento do Lote
 */
public class AtualizarQuadraAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarQuadraActionForm atualizarQuadraActionForm = (AtualizarQuadraActionForm) actionForm;
        
        Quadra quadraParaManter = (Quadra) sessao.getAttribute("quadraManter");
        
        //CARREGANDO O OBJETO INSERIR_QUADRA_HELPER
		InserirQuadraHelper helper = this.carregarInserirQuadraHelper(atualizarQuadraActionForm);
		
        //VALIDANDO OS DADOS DA QUADRA
		Quadra quadraAtualizar = fachada.validarQuadra(helper);
		quadraAtualizar.setId(quadraParaManter.getId());
		quadraAtualizar.setUltimaAlteracao(quadraParaManter.getUltimaAlteracao());
		quadraAtualizar.setIndicadorBloqueio(new Short(atualizarQuadraActionForm.getIndicadorBloqueio()));
		
		//OBTENDO AS FACES DA QUADRA
		Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");

        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        if(quadraAtualizar.getRota() != null){
        	if(quadraAtualizar.getRota().getIndicadorRotaAlternativa().shortValue() == 2){
            	fachada.atualizarQuadra(quadraAtualizar, usuarioLogado, colecaoQuadraFace);	
            }else{
            	throw new ActionServletException(
				"atencao.rota_alternativa_nao_pode_associar_quadra");
            }
        }

        montarPaginaSucesso(httpServletRequest,
                "Quadra de n�mero  " + helper.getQuadraNM() +
                " do setor comercial " + helper.getSetorComercialCD() + 
                "-" + quadraAtualizar.getSetorComercial().getDescricao() +
                " da localidade " + helper.getLocalidadeID() + 
                "-" + quadraAtualizar.getSetorComercial().getLocalidade().getDescricao() +
                " atualizada com sucesso.",
                "Realizar outra Manuten��o de Quadra", "exibirFiltrarQuadraAction.do?desfazer=S");
        
        sessao.removeAttribute("quadraManter");
        sessao.removeAttribute("colecaoPerfilQuadra");
        sessao.removeAttribute("colecaoSistemaEsgoto");
        sessao.removeAttribute("colecaoZeis");
        sessao.removeAttribute("colecaoBacia");
        sessao.removeAttribute("colecaoQuadraFace");

        return retorno;
    }
    
    private InserirQuadraHelper carregarInserirQuadraHelper(AtualizarQuadraActionForm atualizarQuadraActionForm){
		
		InserirQuadraHelper helper = new InserirQuadraHelper();
		
		helper.setQuadraId(atualizarQuadraActionForm.getQuadraID());
		helper.setIndicadorUso(atualizarQuadraActionForm.getIndicadorUso());
		
		helper.setLocalidadeID(atualizarQuadraActionForm.getLocalidadeID());
		helper.setSetorComercialCD(atualizarQuadraActionForm.getSetorComercialCD());
		helper.setQuadraNM(atualizarQuadraActionForm.getQuadraNM());
		helper.setPerfilQuadraID(atualizarQuadraActionForm.getPerfilQuadra());
		helper.setAreaTipoID(atualizarQuadraActionForm.getAreaTipoID());
		helper.setIndicadorRedeAgua(atualizarQuadraActionForm.getIndicadorRedeAguaAux());
		helper.setIndicadorRedeEsgoto(atualizarQuadraActionForm.getIndicadorRedeEsgotoAux());
		helper.setSistemaEsgotoID(atualizarQuadraActionForm.getSistemaEsgotoID());
		helper.setSubsistemaID(atualizarQuadraActionForm.getSubsistemaID());
		helper.setDistritoOperacionalID(atualizarQuadraActionForm.getDistritoOperacionalID());
		helper.setSetorCensitarioID(atualizarQuadraActionForm.getSetorCensitarioID());
		helper.setZeisID(atualizarQuadraActionForm.getZeisID());
		helper.setRotaCD(atualizarQuadraActionForm.getCodigoRota());
		helper.setIndicadorIncrementoLote(atualizarQuadraActionForm.getIndicadorIncrementoLote());
		helper.setIndicadorAtualizacaoCadastral(atualizarQuadraActionForm.getIndicadorAtualizacaoCadastral());
		
		helper.setBairroCD(atualizarQuadraActionForm.getBairroID());
		helper.setMunicipioID(atualizarQuadraActionForm.getMunicipioID());
		
		return helper;
	}

}
