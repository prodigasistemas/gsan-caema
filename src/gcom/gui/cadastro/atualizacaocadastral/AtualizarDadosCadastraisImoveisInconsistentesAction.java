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
package gcom.gui.cadastro.atualizacaocadastral;


import gcom.cadastro.AtributoAtualizacaoCadastral;
import gcom.cadastro.RetornoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.AtualizacoesPorInconsistenciaHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

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
 * 
 * @author Arthur Carvalho
 * @date 08/05/08
 */

public class AtualizarDadosCadastraisImoveisInconsistentesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoImoveisInconsistentesHelper = (Collection<DadosMovimentoAtualizacaoCadastralHelper>) 
				sessao.getAttribute("colecaoImoveisInconsistentesHelper");
		
		
		validarCampos(colecaoImoveisInconsistentesHelper, requestMap);
		
		Iterator<DadosMovimentoAtualizacaoCadastralHelper> iteratorHelper = colecaoImoveisInconsistentesHelper.iterator();
		
		while( iteratorHelper.hasNext() ) {
			
			DadosMovimentoAtualizacaoCadastralHelper dadosMovimentoHelper = (DadosMovimentoAtualizacaoCadastralHelper) iteratorHelper.next();
			
			Collection<AtualizacoesPorInconsistenciaHelper> colecaoAtualizacoesHelper = dadosMovimentoHelper.getColecaoAtualizacoesHelper();
			
			Iterator<AtualizacoesPorInconsistenciaHelper> iteratorAtualizacoesHelper = colecaoAtualizacoesHelper.iterator();
			while( iteratorAtualizacoesHelper.hasNext() ) {
				
				AtualizacoesPorInconsistenciaHelper atualizacoesHelper = (AtualizacoesPorInconsistenciaHelper) iteratorAtualizacoesHelper.next();
				
				String valorAlteracao = (requestMap.get("alteracao"+ atualizacoesHelper.getIdRetornoAtlzCadastral()))[0];
				
				atualizacoesHelper.setUsuario(usuario);
				
				if ( valorAlteracao != null && !valorAlteracao.equals("") ) {
					atualizacoesHelper.setValorAlteracao(new Short(valorAlteracao));
				} else {
					atualizacoesHelper.setValorAlteracao(null);
				}
				
				fachada.atualizarIndicadorAtualizacaoAtributo(atualizacoesHelper);
			}
			
			boolean existeImovelComAltera��esPendentes = fachada.existeImovelComAlteracoesPendentes(new Integer(dadosMovimentoHelper.getMatricula()));
			
			if (!existeImovelComAltera��esPendentes){
				
				fachada.atualizarIndicadorImovelPendenteAtualizacaoCadastral(dadosMovimentoHelper.getIdImovelAtlzCadastral());
			}
		}
		
		
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
		
		montarPaginaSucesso(httpServletRequest, "Atualizado com sucesso.",
				"Realizar outra Manuten��o",
				"exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?menu=sim");
		
		return retorno;
	}
	
	/**
	 * 
	 * @param colecaoImoveisInconsistentesHelper
	 */
	public void validarCampos( Collection<DadosMovimentoAtualizacaoCadastralHelper> colecaoImoveisInconsistentesHelper , Map<String, String[]> requestMap) {
		
		Iterator<DadosMovimentoAtualizacaoCadastralHelper> iteratorHelper = colecaoImoveisInconsistentesHelper.iterator();
		
		while( iteratorHelper.hasNext() ) {
			
			DadosMovimentoAtualizacaoCadastralHelper dadosMovimentoHelper = (DadosMovimentoAtualizacaoCadastralHelper) iteratorHelper.next();
			
			Collection<AtualizacoesPorInconsistenciaHelper> colecaoAtualizacoesHelper = dadosMovimentoHelper.getColecaoAtualizacoesHelper();
			String valorAlteracaoCategoria = null;
			String valorAlteracaoEconomia = null;
			boolean existeEconomia = false;
			boolean existeCategoria = false;
			
			Iterator<AtualizacoesPorInconsistenciaHelper> iteratorAtualizacoesHelper = colecaoAtualizacoesHelper.iterator();
			while( iteratorAtualizacoesHelper.hasNext() ) {
				
				AtualizacoesPorInconsistenciaHelper atualizacoesHelper = (AtualizacoesPorInconsistenciaHelper) iteratorAtualizacoesHelper.next();
				
				if ( atualizacoesHelper.getIdAtributo().equals(AtributoAtualizacaoCadastral.CATEGORIA_SUBCATEGORIA) ) {
					valorAlteracaoCategoria = (requestMap.get("alteracao"+ atualizacoesHelper.getIdRetornoAtlzCadastral()))[0];
					existeCategoria = true;
				}
				
				if ( atualizacoesHelper.getIdAtributo().equals(AtributoAtualizacaoCadastral.ECONOMIA)  ) {
					valorAlteracaoEconomia = (requestMap.get("alteracao"+ atualizacoesHelper.getIdRetornoAtlzCadastral()))[0];
					existeEconomia = true;
				}
			}
			
			if ( existeCategoria && existeEconomia && valorAlteracaoCategoria != null && !valorAlteracaoCategoria.equals("") && 
					(valorAlteracaoEconomia == null || valorAlteracaoEconomia.equals("")) ) {
				
				throw new ActionServletException("atencao.informar_situacao", "ECONOMIA", dadosMovimentoHelper.getMatricula());	
			}
			
			if ( existeCategoria && existeEconomia && valorAlteracaoEconomia != null && !valorAlteracaoEconomia.equals("") && 
					( valorAlteracaoCategoria == null || valorAlteracaoCategoria.equals("") ) ) {
				
				throw new ActionServletException("atencao.informar_situacao", "CATEGORIA/SUBCATEGORIA", dadosMovimentoHelper.getMatricula());	
			}
			
			//Verifica se existe o atributo categoria e o atributo economia. Caso existam verifica se � inconsistente
			if ( existeCategoria && existeEconomia && ( ( valorAlteracaoCategoria != null && valorAlteracaoCategoria.equals(RetornoAtualizacaoCadastral.APROVADO.toString()) &&
					valorAlteracaoEconomia != null && 
					( valorAlteracaoEconomia.equals(RetornoAtualizacaoCadastral.REPROVADO.toString()) || valorAlteracaoEconomia.equals(RetornoAtualizacaoCadastral.ACEITO.toString()) ) ) 
					||
					( valorAlteracaoEconomia != null && valorAlteracaoEconomia.equals(RetornoAtualizacaoCadastral.APROVADO.toString()) &&
					valorAlteracaoCategoria != null && 
					( valorAlteracaoCategoria.equals(RetornoAtualizacaoCadastral.REPROVADO.toString()) || valorAlteracaoCategoria.equals(RetornoAtualizacaoCadastral.ACEITO.toString()) ) ) ) ) {
				
				throw new ActionServletException("atencao.situacao_economia_categoria_invalida", null, dadosMovimentoHelper.getMatricula());
				
			}
		}
		
	}
}
