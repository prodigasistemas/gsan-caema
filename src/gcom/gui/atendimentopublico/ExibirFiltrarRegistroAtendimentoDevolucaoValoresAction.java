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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarRegistroAtendimentoDevolucaoValoresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarRegistroAtendimentoDevolucaoValores");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRegistroAtendimentoDevolucaoValoresActionForm form = (FiltrarRegistroAtendimentoDevolucaoValoresActionForm) actionForm;

		// Limpa o atributo se o usu�rio voltou o filtrar
		if (sessao.getAttribute("guiaDevolucaoAtualizar") != null) {
			sessao.removeAttribute("guiaDevolucaoAtualizar");
		}

		// 1.1. Tipo de Especifica��o (Obrigat�rio)
		Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao =
			fachada.pesquisarSolicitacaoTipoEspecificacao();
		
		if (colecaoSolicitacaoTipoEspecificacao != null 
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {

			sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", 
				colecaoSolicitacaoTipoEspecificacao);
		} else {
			sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
		}
		
		
		// Pegando valores para Tipo de Relacao do Cliente
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		Collection<ImovelPerfil> colecaoPerfilImovel = fachada.pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());

		if (colecaoPerfilImovel != null	&& !colecaoPerfilImovel.isEmpty()) {

			httpServletRequest.setAttribute("colecaoPerfilImovel",colecaoPerfilImovel);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Im�vel Perfil");
		}

//		if (httpServletRequest.getParameter("menu") != null
//				&& httpServletRequest.getParameter("menu").equalsIgnoreCase(
//						"sim")) {
//			form.setAtualizar("1");
//		}
//		
//		 Seta o cursor para o primeiro campo do tipo texto
//		httpServletRequest.setAttribute("nomeCampo", "idImovel");
		
		// Im�vel
		String idImovel = "";
		
		if (httpServletRequest.getParameter("paginacao") != null) {
			idImovel = (String) form.getIdImovelHidden();
		} else {
			idImovel = (String) form.getIdImovel();
		}

		if (idImovel != null && !idImovel.equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				httpServletRequest.setAttribute("imovel", imovel);
				form.setDescricaoImovel(imovel.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", "true");
				form.setIdImovel("");
				form.setDescricaoImovel("MATR�CULA INEXISTENTE");
			}
		} else {
			form.setIdImovel("");
			form.setDescricaoImovel("");
		}
		
		return retorno;
	}

}