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

import gcom.cobranca.LimitacaoGeograficaRDHelper;
import gcom.cobranca.RdRestricaoUsuario;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Action que faz a exibi��o da tela para o usu�rio setar os campos e permitir
 * que ele insera uma resolu��o de diretoria [UC0217] Inserir Resolu��o de
 * Diretoria
 * 
 * @author Rafael Corr�a
 * @since 30/03/2006
 */
public class ExibirInserirResolucaoDiretoriaAction extends GcomAction {

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
				.findForward("exibirInserirResolucaoDiretoria");
		
		String veioAdicionar = httpServletRequest.getParameter("veioAdicionar");
		String addLimitacaoGeografica = httpServletRequest.getParameter("addLimitacaoGeografica");
		
		//Recupera a flag para indicar se o usu�rio apertou o bot�ode desfazer
		String desfazer = httpServletRequest.getParameter("desfazer");
		
		HttpSession sessao  = httpServletRequest.getSession();
		InserirResolucaoDiretoriaActionForm inserirResolucaoDiretoriaActionForm = (InserirResolucaoDiretoriaActionForm) actionForm;
		
		if(veioAdicionar == null){
			
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(ConstantesSistema.SIM.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(ConstantesSistema.SIM.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorAcessoRestrito(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(ConstantesSistema.SIM.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorValidoAcaoCobranca(ConstantesSistema.NAO.toString());
			
		}
		
		if(addLimitacaoGeografica != null){
			
			httpServletRequest.setAttribute("addLimitacaoGeograficaChamarPopup", "true");
		}
		
		
		if (httpServletRequest.getParameter("remover") != null && (httpServletRequest.getParameter("remover").equals("sim"))){
			
			
			
			String idRDLimitacao = (String) httpServletRequest.getParameter("idRdLimitacao");
			
			Collection<LimitacaoGeograficaRDHelper> collectionLimitacaoGeograficaRDHelper = 
				(Collection<LimitacaoGeograficaRDHelper>) sessao.getAttribute("collectionLimitacaoGeograficaRDHelper");
			
			if(!Util.isVazioOrNulo(collectionLimitacaoGeograficaRDHelper)){
				Iterator  iterator =   collectionLimitacaoGeograficaRDHelper.iterator();
				
				int count = 1;
				while(iterator.hasNext()){
					LimitacaoGeograficaRDHelper helper = (LimitacaoGeograficaRDHelper) iterator.next();
					
					if(idRDLimitacao.equals(""+count)){
						collectionLimitacaoGeograficaRDHelper.remove(helper);
						break;
					}
					count++;
				}
			}
			sessao.setAttribute("collectionLimitacaoGeograficaRDHelper", collectionLimitacaoGeograficaRDHelper);
		}
		
		if(desfazer != null){
			
			inserirResolucaoDiretoriaActionForm.setNumero("");
			inserirResolucaoDiretoriaActionForm.setAssunto("");
			inserirResolucaoDiretoriaActionForm.setDataInicio("");
			inserirResolucaoDiretoriaActionForm.setDataFim("");
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(ConstantesSistema.SIM.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(ConstantesSistema.SIM.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorAcessoRestrito(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(ConstantesSistema.SIM.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(ConstantesSistema.NAO.toString());
			inserirResolucaoDiretoriaActionForm.setIndicadorValidoAcaoCobranca(ConstantesSistema.NAO.toString());
			
			httpServletRequest.removeAttribute("limitacaoGeograficaRDHelper");
			sessao.removeAttribute("collectionLimitacaoGeograficaRDHelper");
		
		}
		
		//-------------------------------------------------------------------
		//RM8255 - adicionado por Vivianne Sousa - 12/11/2012
		//Usu�rio
		if (inserirResolucaoDiretoriaActionForm.getLoginUsuario() != null &&
			!inserirResolucaoDiretoriaActionForm.getLoginUsuario().equals("")) {
			
			getUsuario(inserirResolucaoDiretoriaActionForm, 
					inserirResolucaoDiretoriaActionForm.getLoginUsuario(), sessao);
		}
		
		
		if (httpServletRequest.getParameter("removerUsuario") != null && (httpServletRequest.getParameter("removerUsuario").equals("sim"))){
			
			String idRdRestricaoUsuario = (String) httpServletRequest.getParameter("idRdRestricaoUsuario");
			
			Collection<RdRestricaoUsuario> colecaoRdRestricaoUsuario = 
				(Collection<RdRestricaoUsuario>) sessao.getAttribute("colecaoRdRestricaoUsuario");
			
			if(!Util.isVazioOrNulo(colecaoRdRestricaoUsuario)){
				Iterator  iterator =  colecaoRdRestricaoUsuario.iterator();
				
				int count = 1;
				while(iterator.hasNext()){
					RdRestricaoUsuario rdRestricaoUsuario = (RdRestricaoUsuario) iterator.next();
					
					if(idRdRestricaoUsuario.equals(""+count)){
						colecaoRdRestricaoUsuario.remove(rdRestricaoUsuario);
						break;
					}
					count++;
				}
			}
			sessao.setAttribute("colecaoRdRestricaoUsuario", colecaoRdRestricaoUsuario);
		}
		
		
		if(httpServletRequest.getParameter("associarUsuario") != null){
			
			Collection colecaoRdRestricaoUsuario = 
					(Collection) sessao.getAttribute("colecaoRdRestricaoUsuario");
				
			if(colecaoRdRestricaoUsuario == null){
				colecaoRdRestricaoUsuario = new ArrayList();	
			}  
	
			Usuario usuario = getUsuario(inserirResolucaoDiretoriaActionForm, 
					inserirResolucaoDiretoriaActionForm.getLoginUsuario(), sessao);
			if(usuario != null){
				RdRestricaoUsuario rdRestricaoUsuario = new RdRestricaoUsuario();
				rdRestricaoUsuario.setUsuario(usuario);
				colecaoRdRestricaoUsuario.add(rdRestricaoUsuario);
				
				inserirResolucaoDiretoriaActionForm.setLoginUsuario("");
				inserirResolucaoDiretoriaActionForm.setNomeUsuario("");
			}

			
			sessao.setAttribute("colecaoRdRestricaoUsuario", colecaoRdRestricaoUsuario);
		}
		
		if(httpServletRequest.getParameter("limparColecaoUsuario") != null){
			sessao.removeAttribute("colecaoRdRestricaoUsuario");
		}
		
		
		
		//-------------------------------------------------------------------
		
		return retorno;

	}

	
	/**
	 * Recupera o Usu�rio
	 */
	private Usuario getUsuario(InserirResolucaoDiretoriaActionForm inserirResolucaoDiretoriaActionForm, 
			String loginUsuario, HttpSession sessao) {
		Usuario usuario = null;
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		//filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);
		
		// Recupera Usu�rio
		Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			inserirResolucaoDiretoriaActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			inserirResolucaoDiretoriaActionForm.setLoginUsuario("");
			inserirResolucaoDiretoriaActionForm.setNomeUsuario("Usu�rio Inexistente");
		}
		return usuario;
	}
}
