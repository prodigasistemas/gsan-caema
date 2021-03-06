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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.bean.SolicitacaoEspecificacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 07/11/2006
 */
public class AtualizarTipoSolicitacaoEspecificacaoAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarTipoSolicitacaoEspecificacaoActionForm atualizarTipoSolicitacaoEspecificacaoActionForm = (AtualizarTipoSolicitacaoEspecificacaoActionForm) actionForm;

		Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
				.getAttribute("colecaoSolicitacaoTipoEspecificacao");
		if (colecaoSolicitacaoTipoEspecificacao == null
				|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			throw new ActionServletException("atencao.required", null,
					" Especifica��o do Tipo da Solicita��o");
		}

		// Fachada fachada = Fachada.getInstancia();

		SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();

		// id da solicitacao tipo
		solicitacaoTipo.setId(new Integer(
				atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIdTipoSolicitacao()));

		// descri��o da solicita��o tipo
		if (atualizarTipoSolicitacaoEspecificacaoActionForm.getDescricao() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getDescricao().equals("")) {
			solicitacaoTipo
					.setDescricao(atualizarTipoSolicitacaoEspecificacaoActionForm
							.getDescricao());
		}
		// id do grupo de solicita��o da descri��o selecionada
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIdgrupoTipoSolicitacao() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIdgrupoTipoSolicitacao().equals("")) {
			SolicitacaoTipoGrupo solicitacaoTipoGrupo = new SolicitacaoTipoGrupo();
			solicitacaoTipoGrupo.setId(new Integer(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIdgrupoTipoSolicitacao()));
			solicitacaoTipo.setSolicitacaoTipoGrupo(solicitacaoTipoGrupo);
		}

		// indicativo de falta d'agua
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorFaltaAgua() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorFaltaAgua().equals("")) {
			solicitacaoTipo.setIndicadorFaltaAgua(new Short(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorFaltaAgua()));
		} else {
			throw new ActionServletException("atencao.indicador.selecionado",
					null, "Falta D'�gua");
		}
		// indicativo de tarifa social
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorTarifaSocial() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorTarifaSocial().equals("")) {
			solicitacaoTipo.setIndicadorTarifaSocial(new Short(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorTarifaSocial()));
		} else {
			throw new ActionServletException("atencao.indicador.selecionado",
					null, "Tarifa Social");

		}
		
		// indicativo de tarifa social
		if (atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorFactivelFaturavel() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorFactivelFaturavel().equals("")) {
				
			solicitacaoTipo.setIndicadorFactivelFaturavel(new Short(atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorFactivelFaturavel()));
			
		} else {
			throw new ActionServletException("atencao.indicador.selecionado",
					null, "Ligado Decreto");

		}
		
		// indicativo de uso do sistema
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorUsoSistema() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorUsoSistema().equals("")) {
			solicitacaoTipo.setIndicadorUsoSistema(new Short(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorUsoSistema()));
		} else {
			throw new ActionServletException("atencao.indicador.selecionado",
					null, "Uso Sistema");

		}
		
		// data e hora correntes
		solicitacaoTipo.setUltimaAlteracao(new Date());
		
		solicitacaoTipo.setIndicadorPermiteClienteDesconhecido(new Short(
				atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorPermiteClienteDesconhecido()));

		// indicador uso
		if ( atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorUso() != null ) {
			solicitacaoTipo.setIndicadorUso(new Short(atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorUso()));	
		}
		

		Collection colecaoSolicitacaoTipoEspecificacaoRemovidos = null;

		if (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos") != null) {

			colecaoSolicitacaoTipoEspecificacaoRemovidos = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos");
		}

		//RM 5924 Adicionar arquivo de procedimentos operacionais padr�es POPs
		Collection<SolicitacaoEspecificacaoHelper> colecaoHelper = (Collection<SolicitacaoEspecificacaoHelper>) sessao.getAttribute("colecaoHelpers");
		Collection<SolicitacaoTipoEspecificacao> colecaoSTE = colecaoSolicitacaoTipoEspecificacao;
		sessao.removeAttribute("colecaoHelpers");
		if (colecaoHelper!=null){
			for (SolicitacaoEspecificacaoHelper helper : colecaoHelper){
				for (SolicitacaoTipoEspecificacao ste: colecaoSTE){
					if (ste.getId()==helper.getIdHelper()){
						ste.setColecaoArquivosPOP(helper.getColecaoArquivos());
					}
				}
			}
		}
		// atualiza o tipo de solicita��o com especifica��es na base
		fachada.atualizarTipoSolicitacaoEspecificacao(solicitacaoTipo,
				colecaoSolicitacaoTipoEspecificacao, usuario,
				colecaoSolicitacaoTipoEspecificacaoRemovidos);

		// remove o parametro de retorno
		sessao.removeAttribute("retornarTela");
		sessao.removeAttribute("retornarTelaPopup");
		sessao.removeAttribute("colecaoImovelSituacao");
		sessao.removeAttribute("colecaoSolicitacaoTipoGrupo");

		montarPaginaSucesso(httpServletRequest,
				"Tipo de Solicita��o com Especifica��es "
						+ solicitacaoTipo.getDescricao()
						+ " atualizado com sucesso!",
				"Realizar outra Manuten��o Tipo Solicita��o com Especifica��o",
				"exibirFiltrarTipoSolicitacaoEspecificacaoAction.do?menu=sim");

		return retorno;
	}
}
