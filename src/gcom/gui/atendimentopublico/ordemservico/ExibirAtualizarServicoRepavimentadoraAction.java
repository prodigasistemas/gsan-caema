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

import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.FiltroServicoRepavimentadora;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.ServicoRepavimentadora;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
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
 * [UC 1474] - Manter Serviço da Repavimentadora
 * 
 * @author Davi Menezes
 * @date 20/05/2013
 *
 */
public class ExibirAtualizarServicoRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarServicoRepavimentadora");
		AtualizarServicoRepavimentadoraActionForm atualizarServicoRepavimentadoraActionForm = (AtualizarServicoRepavimentadoraActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();
		Collection<MaterialUnidade> colecaoMaterialUnidade = fachada.pesquisar(filtroMaterialUnidade, MaterialUnidade.class.getName());
		httpServletRequest.setAttribute("colecaoMaterialUnidade", colecaoMaterialUnidade);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		this.pesquisarUnidadeRepavimentadora(fachada, httpServletRequest, usuario);

		ServicoRepavimentadora servicoRepavimentadora = null;
		String idServico = null;

		if (httpServletRequest.getParameter("idServico") != null) {
			//tela do manter
			idServico = (String) httpServletRequest.getParameter("idServico");
			sessao.setAttribute("idServico", idServico);
		} else if (sessao.getAttribute("idServico") != null) {
			//tela do filtrar
			idServico = (String) sessao.getAttribute("idServico");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir serviço repavimentadora
			idServico = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMaterialAction.do?menu=sim");
		}

		if (idServico == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				servicoRepavimentadora = (ServicoRepavimentadora) sessao.getAttribute("servicoRepavimentadoraAtualizar");
			}else{
				idServico = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}

		//------Inicio da parte que verifica se vem da página de
		// 		servico_repavimentadora_manter.jsp
		if (servicoRepavimentadora == null){

			if (idServico != null && !idServico.equals("")) {
				FiltroServicoRepavimentadora filtroServicoRepavimentadora = new FiltroServicoRepavimentadora();
				filtroServicoRepavimentadora.adicionarCaminhoParaCarregamentoEntidade("materialUnidade");
				filtroServicoRepavimentadora.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
				filtroServicoRepavimentadora.adicionarParametro(new ParametroSimples(FiltroServicoRepavimentadora.ID, idServico));
				Collection<ServicoRepavimentadora> colecaoServicoRepavimentadora = 
						fachada.pesquisar(filtroServicoRepavimentadora, ServicoRepavimentadora.class.getName());

				if (!Util.isVazioOrNulo(colecaoServicoRepavimentadora)) {
					servicoRepavimentadora = (ServicoRepavimentadora) Util.retonarObjetoDeColecao(colecaoServicoRepavimentadora);
				}
			}
		}

		//  ------  O serviço da repavimentadora foi encontrado
		atualizarServicoRepavimentadoraActionForm.setId(String.valueOf(servicoRepavimentadora.getId()));
		atualizarServicoRepavimentadoraActionForm.setDescricao(servicoRepavimentadora.getDescricao());
		
		if(servicoRepavimentadora.getDescricaoAbreviada() != null){
			atualizarServicoRepavimentadoraActionForm.setDescricaoAbreviada(servicoRepavimentadora.getDescricaoAbreviada());
		}else{
			atualizarServicoRepavimentadoraActionForm.setDescricaoAbreviada("");
		}
		
		atualizarServicoRepavimentadoraActionForm.setUnidadeMaterial(servicoRepavimentadora.getMaterialUnidade().getId().toString());
		atualizarServicoRepavimentadoraActionForm.setUnidadeRepavimentadora(servicoRepavimentadora.getUnidadeRepavimentadora().getId().toString());
		atualizarServicoRepavimentadoraActionForm.setValorServico(Util.formatarMoedaReal(servicoRepavimentadora.getValorServico()));
		atualizarServicoRepavimentadoraActionForm.setIndicadorUso(String.valueOf(servicoRepavimentadora.getIndicadorUso()));
		
		sessao.setAttribute("servicoRepavimentadora", servicoRepavimentadora);
		// ------ Fim da parte que verifica se vem da página de servico_repavimentadora_manter.jsp

		return retorno;
	}
	
	/**
	 * Método responsável por pesquisar as unidades repavimentadoras
	 * de acordo com a unidade organizacional do usuário
	 * 
	 * @author Davi Menezes
	 * @date 16/05/2013
	 * 
	 * @param fachada
	 * @param request
	 * @param usuarioLogado
	 */
	private void pesquisarUnidadeRepavimentadora(Fachada fachada, HttpServletRequest request, Usuario usuarioLogado){
		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = new ArrayList<UnidadeOrganizacional>();
		
		UnidadeOrganizacional unidadeOrganizacional = usuarioLogado.getUnidadeOrganizacional();
		if(unidadeOrganizacional.getUnidadeTipo() != null && 
				(unidadeOrganizacional.getUnidadeTipo().getId().intValue() != UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue())){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeOrganizacional.ID_UNIDADE_REPAVIMENTADORA));
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
			
			Collection<?> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				Iterator<?> it = colecaoUnidadeOrganizacional.iterator();
				while(it.hasNext()){
					unidadeOrganizacional = (UnidadeOrganizacional) it.next();
					
					if(!colecaoUnidadeRepavimentadora.contains(unidadeOrganizacional.getUnidadeRepavimentadora())){
						colecaoUnidadeRepavimentadora.add(unidadeOrganizacional.getUnidadeRepavimentadora());
					}
				}
			}
		}else{
			colecaoUnidadeRepavimentadora.add(unidadeOrganizacional);
		}
		
		request.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
	}
	
}