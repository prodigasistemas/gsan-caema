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
			
			boolean existeImovelComAlteraçõesPendentes = fachada.existeImovelComAlteracoesPendentes(new Integer(dadosMovimentoHelper.getMatricula()));
			
			if (!existeImovelComAlteraçõesPendentes){
				
				fachada.atualizarIndicadorImovelPendenteAtualizacaoCadastral(dadosMovimentoHelper.getIdImovelAtlzCadastral());
			}
		}
		
		
		sessao.removeAttribute("colecaoImoveisInconsistentesHelper");
		
		montarPaginaSucesso(httpServletRequest, "Atualizado com sucesso.",
				"Realizar outra Manutenção",
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
			
			//Verifica se existe o atributo categoria e o atributo economia. Caso existam verifica se é inconsistente
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
