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
package gcom.gui.cadastro.sistemaparametro;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 10/01/2007
 */
public class ExibirInformarParametrosSistemaAtendimentoPublicoSegurancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirInformarParametrosSistemaAtendimentoPublicoSeguranca");
		
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;
		
		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");
		
		Integer cont;
		// Verifica se ja entrou nesse action, caso nao coloca no form os dados
		// do objeto sistemaParametro
		if (sessao
				.getAttribute("informarParametrosSistemaAtendimentoPublicoSegurancaAction") == null) {
		
			cont = 1;
			sessao.setAttribute(
					"informarParametrosSistemaAtendimentoPublicoSegurancaAction",
					cont);
			
			/*
			 * UC - 0060
			 * RM - 9021
			 * Servico da Ordem de Servico seletiva Factivel Faturavel
			 */
			if(sistemaParametro.getServicoTipo()!=null){
				form.setIdServicoTipo(sistemaParametro.getServicoTipo().getId().toString());
				this.pesquisarServicoTipo(form, "", httpServletRequest);
			}
		
			if (sistemaParametro
					.getUnidadeOrganizacionalTramiteGrandeConsumidor() != null) {
		
				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID,
								sistemaParametro
										.getUnidadeOrganizacionalTramiteGrandeConsumidor()
										.getId()));
		
				Collection<UnidadeOrganizacional> colecao = this.getFachada()
						.pesquisar(filtroUnidadeOrganizacional,
								UnidadeOrganizacional.class.getName());
		
				UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecao);
		
				form.setIdUnidadeDestinoGrandeConsumidor(unidade.getId()
						.toString());
				form.setNomeUnidadeDestinoGrandeConsumidor(unidade
						.getDescricao());
		
				httpServletRequest.setAttribute(
						"idUnidadeDestinoGrandeConsumidor", unidade.getId()
								.toString());
				httpServletRequest.setAttribute("idUnidadeEncontrada", "true");
			}
		
		}
		
		String pesquisaUnidade = httpServletRequest
				.getParameter("pesquisaUnidade");
		String id = httpServletRequest.getParameter("id");
		
		if (pesquisaUnidade != null && pesquisaUnidade.toString().equals("sim")
				&& id != null && !id.equals("")) {
			this.pesquisarUnidadeOrganizacional(new Integer(id), form,
					httpServletRequest);
		}
		
		// Pesquisar Servico Tipo
		String pesquisarServicoTipo = httpServletRequest.getParameter("pesquisarServicoTipo");
		if(pesquisarServicoTipo!=null){
			if(pesquisarServicoTipo.equals("sim")){
				this.pesquisarServicoTipo(form, pesquisarServicoTipo, httpServletRequest);
			}
		}
				
		if(sistemaParametro.getNumeroLimiteOSCobranca() != null){
			form.setNumeroLimiteOSCobranca(sistemaParametro.getNumeroLimiteOSCobranca().toString());
		}
		
		return retorno;
	}

	/**
	* Hugo Amorim
	* 
	* Pesquisa Unidade Organizacional
	*/
	public void pesquisarUnidadeOrganizacional(Integer id,
		InformarSistemaParametrosActionForm form,
		HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();
		
		FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
		
		filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, id));
		
		Collection<UnidadeOrganizacional> unidadeEmpresaEncontrada = fachada
				.pesquisar(filtroUnidadeEmpresa,
						UnidadeOrganizacional.class.getName());
		
		if (unidadeEmpresaEncontrada != null
				&& !unidadeEmpresaEncontrada.isEmpty()) {
			form.setIdUnidadeDestinoGrandeConsumidor(""
					+ ((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada)
							.get(0)).getId());
			form.setNomeUnidadeDestinoGrandeConsumidor(((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada)
					.get(0)).getDescricao());
			httpServletRequest.setAttribute("idUnidadeDestinoGrandeConsumidor",
					id);
			httpServletRequest.setAttribute("idUnidadeEncontrada", "true");
			httpServletRequest.setAttribute("nomeCampo", "idUnidade");
		
		} else {
			form.setIdUnidadeDestinoGrandeConsumidor("");
			httpServletRequest.removeAttribute("idUnidadeEncontrada");
			httpServletRequest
					.removeAttribute("idUnidadeDestinoGrandeConsumidor");
			form.setNomeUnidadeDestinoGrandeConsumidor("Unidade  Organizacional Inexistente");
			httpServletRequest.setAttribute("nomeCampo", "idUnidade");
		}
	}
	
	/*
	* Pesquisar Servico Tipo
	* Autor: Jonathan Marcos
	* UC - 0060
	* RM - 9021
	*/
	private void pesquisarServicoTipo(InformarSistemaParametrosActionForm form,
		String objetoConsulta, HttpServletRequest httpServletRequest) {
		Object local = form.getIdServicoTipo();			
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(
			new ParametroSimples(FiltroServicoTipo.ID,local));
		// Recupera Tipo de ServiÁo
		Collection<ServicoTipo> colecaoServicoTipo = 
			this.getFachada().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
	
		if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
			ServicoTipo servicoTipo = 
				(ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
			
			form.setIdServicoTipo(servicoTipo.getId().toString());
			form.setDesServicoTipo(servicoTipo.getDescricao());
			
		} else {				
			form.setIdServicoTipo(null);
			form.setDesServicoTipo("Tipo de serviço inexistente");
			httpServletRequest.setAttribute("servicoTipoInexistente","true");
		}
	}
}
