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

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.help.plaf.basic.BasicFavoritesNavigatorUI.RemoveAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0217] Inserir Resolu��o de Diretoria
 * [SB0001] Adicionar Limita��o Geogr�fica da RD
 * POPUP
 * 
 * @author Nathalia Santos 
 * @since 21/05/2012
 */
public class ExibirAdicionarLimitacaoGeograficaRDPopupAction extends GcomAction {

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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarLimitacaoGeograficaRD");
		
		AdicionarLimitacaoGeograficaRDPopupActionForm form = (AdicionarLimitacaoGeograficaRDPopupActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		String limparForm = httpServletRequest.getParameter("limparForm");
		
		if(limparForm !=  null){
			form.limparTodosCamposForm();
		}
		
		String tipoPequisa = httpServletRequest.getParameter("tipoPequisa");
		if(tipoPequisa !=  null){
			
		   	switch (new Integer(tipoPequisa)){
			case 1:
				form.setIdUnidadeNegocio(null);
				form.setIdLocalidade(null);
				form.setCodigoSetorComercial(null);
				form.setNumeroQuadra(null);
		  	    break;   
			case 2:
				form.setIdLocalidade(null);
				form.setCodigoSetorComercial(null);
				form.setNumeroQuadra(null);
				break;
			case 3:
				form.setCodigoSetorComercial(null);
				form.setNumeroQuadra(null);
				break;
			case 4:
				form.setNumeroQuadra(null);
				break;   
				   
		   	}
		}
		
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		if(retornarTela != null){
			sessao.setAttribute("retornarTela",retornarTela);
		}
     	
		this.pesquisarGerenciaRegional(httpServletRequest);
		
    	//Unidade de Negocio
    	if (form.getIdGerenciaRegional() != null && 
    		!form.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
    		
    		this.pesquisarUnidadeNegocio(form, httpServletRequest);
    	}
    	
        // Localidade
    	if 	(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
			
    		this.pesquisarLocalidade(form, httpServletRequest);
		} 	
        
        // Setor Comercial
    	if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
   			this.pesquisarSetorComercial(form, httpServletRequest);
    	}	
    	
    	// Quadra
		if (form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
			this.pesquisarQuadra(form, httpServletRequest);
		}	
        
        String numeroRD = (String)httpServletRequest.getParameter("numeroRD");
        
        if(numeroRD != null && !numeroRD.equals("") && !numeroRD.equals("null")){
        	form.setNumero(numeroRD);
        }      
     
    	//Seta a flag para indicar que o popupvai ser fechado
        sessao.setAttribute("fechaPopup", "false");
 
		return retorno;

	}

	public void  pesquisarGerenciaRegional(HttpServletRequest httpServletRequest) {
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO,ConstantesSistema.SIM));
		
		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

		httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
	}
	
	public void  pesquisarUnidadeNegocio(AdicionarLimitacaoGeograficaRDPopupActionForm form, HttpServletRequest httpServletRequest) {
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA,form.getIdGerenciaRegional()));
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		Collection colecaoUnidadeNegocio = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
	}

	public void  pesquisarLocalidade(AdicionarLimitacaoGeograficaRDPopupActionForm form, HttpServletRequest httpServletRequest) {
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA,form.getIdGerenciaRegional()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_UNIDADE_NEGOCIO,form.getIdUnidadeNegocio()));
		
		filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		
		Collection colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
	
			httpServletRequest.setAttribute("colecaoLocalidade", colecaoLocalidade);
		
	}
	
	public void  pesquisarSetorComercial(AdicionarLimitacaoGeograficaRDPopupActionForm form, HttpServletRequest httpServletRequest) {
		Integer[] idLocalidade = form.getIdLocalidade();
		if(idLocalidade != null && 
			idLocalidade.length == 1 && 
			!idLocalidade[0].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,idLocalidade[0]));
			filtroSetorComercial.setCampoOrderBy(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL);
			Collection colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			
				httpServletRequest.setAttribute("colecaoSetorComercial", colecaoSetorComercial);
		}
	}

	public void  pesquisarQuadra(AdicionarLimitacaoGeograficaRDPopupActionForm form, HttpServletRequest httpServletRequest) {
		Integer[] idLocalidade = form.getIdLocalidade();
		Integer[] codigoSetor = form.getCodigoSetorComercial();
		if(idLocalidade != null && 
			idLocalidade.length == 1 && 
			!idLocalidade[0].equals(ConstantesSistema.NUMERO_NAO_INFORMADO) &&
			codigoSetor != null && 
			codigoSetor.length == 1 && 
			!codigoSetor[0].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidade[0]));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetor[0]));
			
			filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
			Collection colecaoQuadra = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());			
			
				httpServletRequest.setAttribute("colecaoQuadra", colecaoQuadra);
		}
	}
}

