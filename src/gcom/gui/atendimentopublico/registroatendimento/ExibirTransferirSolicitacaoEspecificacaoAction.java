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

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Fernanda Almeida
 * @date 07/11/2006
 */
public class ExibirTransferirSolicitacaoEspecificacaoAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirTransferirSolicitacaoEspecificacao");

		// Obt�m a inst�ncia da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		TransferirSolicitacaoTipoActionForm form = (TransferirSolicitacaoTipoActionForm) actionForm;
		
		String idSolicitacaoTipo = null;
		
		if (httpServletRequest.getParameter("limpar") != null) {
			form.setIdNovoGrupoSolicitacaoTipo(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		
		}

		if (httpServletRequest.getParameter("chave") != null) {
			idSolicitacaoTipo = httpServletRequest.getParameter("chave");
			form.setIdSolicitacaoTipo(idSolicitacaoTipo);
			
			// 2.3 retorna cole��o de solicita��o tipo especifica��o com solicita��o tipo selecionada
			FiltroSolicitacaoTipoEspecificacao filtroEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,ConstantesSistema.SIM));
			filtroEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,idSolicitacaoTipo));
			Collection<SolicitacaoTipoEspecificacao> colSolicitacaoTipoEspecificacao =  fachada.pesquisar(filtroEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			if(colSolicitacaoTipoEspecificacao.isEmpty()){
				throw new ActionServletException(
                        "atencao.tipo_solicitacao_sem_especificacao");
			}
			
			// 2.1 e 2.2 retorna a solicita��o tipo selecionada com solicita��o tipo grupo
			FiltroSolicitacaoTipo filtroSolTipo =  new FiltroSolicitacaoTipo();
			filtroSolTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.ID, idSolicitacaoTipo));
			filtroSolTipo.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");
			Collection<?> colSolicitacaoTipo =  fachada.pesquisar(filtroSolTipo, SolicitacaoTipo.class.getName());
			SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) Util.retonarObjetoDeColecao(colSolicitacaoTipo);	
			
			// 2.4
			FiltroSolicitacaoTipoGrupo filtroGrupo = new FiltroSolicitacaoTipoGrupo();
			filtroGrupo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoGrupo.INDICADOR_USO,ConstantesSistema.SIM));
			Collection<SolicitacaoTipoGrupo> colSolicitacaoTipoGrupoNovo =  fachada.pesquisar(filtroGrupo, SolicitacaoTipoGrupo.class.getName());
			
			// 2.5
			FiltroSolicitacaoTipo filtroSolTipoNovo =  new FiltroSolicitacaoTipo();			
			
			if(httpServletRequest.getParameter("pesquisarSolicitacaoTipo") != null){
				filtroSolTipoNovo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID, form.getIdNovoGrupoSolicitacaoTipo()));
			}

			Collection<SolicitacaoTipo> colSolicitacaoTipoNovo =  fachada.pesquisar(filtroSolTipoNovo, SolicitacaoTipo.class.getName());
			
			
			form.setGrupoSolicitacao(solicitacaoTipo.getSolicitacaoTipoGrupo().getDescricao());			
			form.setDescricaoTipoSolicitacao(solicitacaoTipo.getDescricao());	
			
			sessao.setAttribute("colSolicitacaoTipoGrupoNovo", colSolicitacaoTipoGrupoNovo);
			sessao.setAttribute("colSolicitacaoTipoNovo", colSolicitacaoTipoNovo);
			sessao.setAttribute("colSolicitacaoTipoEspecificacao", colSolicitacaoTipoEspecificacao);
		}
				
		return retorno;
	}
}
