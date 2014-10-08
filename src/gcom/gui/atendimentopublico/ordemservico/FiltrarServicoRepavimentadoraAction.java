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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoRepavimentadora;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1473] - Filtrar Serviço de Repavimentadora
 * 
 * @author Davi Menezes
 * @date 16/05/2013
 *
 */
public class FiltrarServicoRepavimentadoraAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterServicoRepavimentadora");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		FiltrarServicoRepavimentadoraActionForm form = (FiltrarServicoRepavimentadoraActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String unidadeMaterial = form.getUnidadeMaterial();
		String unidadeRepavimentadora = form.getUnidadeRepavimentadora();
		String indicadorUso = form.getIndicadorUso();
		
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}

		boolean peloMenosUmParametroInformado = false;

		FiltroServicoRepavimentadora filtroServicoRepavimentadora = 
				new FiltroServicoRepavimentadora(FiltroServicoRepavimentadora.DESCRICAO);
		
		filtroServicoRepavimentadora.adicionarCaminhoParaCarregamentoEntidade("materialUnidade");
		filtroServicoRepavimentadora.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
		
		// Descrição do Serviço
		if (descricao != null && !descricao.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroServicoRepavimentadora.adicionarParametro(new ComparacaoTexto(FiltroServicoRepavimentadora.DESCRICAO, descricao));
		}

		// Descrição Abreviada do Serviço
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroServicoRepavimentadora.adicionarParametro(new ComparacaoTexto(FiltroServicoRepavimentadora.DESCRICAO_ABREVIADA,descricaoAbreviada));
		}
		
		// Unidade Material
		if (unidadeMaterial != null && !unidadeMaterial.trim().equals("") && !unidadeMaterial.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroServicoRepavimentadora.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.ID_MATERIAL_UNIDADE, unidadeMaterial));
		}

		// Unidade Repavimentadora
		if (unidadeRepavimentadora != null && !unidadeRepavimentadora.trim().equals("") && !unidadeRepavimentadora.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroServicoRepavimentadora.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.ID_UNIDADE_REPAVIMENTADORA, unidadeRepavimentadora));
		}else{
			UnidadeOrganizacional unidadeOrganizacional = this.pesquisarUnidadeRepavimentadora(fachada, httpServletRequest, usuario);
			if(unidadeOrganizacional != null){
				peloMenosUmParametroInformado = true;
				filtroServicoRepavimentadora.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.ID_UNIDADE_REPAVIMENTADORA, 
						unidadeOrganizacional.getId()));
			}
		}

		// Indicador de Uso
		if (indicadorUso != null && !indicadorUso.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroServicoRepavimentadora.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.INDICADOR_USO, indicadorUso));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pela sessao para o
		// ExibirManterMaterialAction
		sessao.setAttribute("filtroServicoRepavimentadora", filtroServicoRepavimentadora);

		return retorno;
	}
	
	private UnidadeOrganizacional pesquisarUnidadeRepavimentadora(Fachada fachada, HttpServletRequest request, Usuario usuarioLogado){
		
		UnidadeOrganizacional unidadeOrganizacional = usuarioLogado.getUnidadeOrganizacional();
		if(unidadeOrganizacional.getUnidadeTipo().getId().intValue() != UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue()){
			return null;
		}else{
			return unidadeOrganizacional;
		}
	}
	
}
	
