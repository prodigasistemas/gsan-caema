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
package gcom.gui.cadastro.localidade;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.bean.InserirTramitesGruposSolicitacoesHelper;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

public class ExibirInserirTramitesGruposSolicitacoesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirTramitesGrupos");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Form
		InserirTramitesGruposSolicitacoesActionForm form = (InserirTramitesGruposSolicitacoesActionForm) actionForm;
		
		if(httpServletRequest.getParameter("primeiroAcesso") != null && httpServletRequest.getParameter("primeiroAcesso").equals("sim")){
			
			sessao.removeAttribute("colecaoTramitesGruposSolicitacoesHelper");
		
		
			Integer idLocalidade = (Integer)httpServletRequest.getAttribute("idLocalidade");
			Integer idUnidadeOrganizacional = (Integer)httpServletRequest.getAttribute("idUnidadeOrganizacional");
			
			/*Integer idLocalidade = new Integer(999);
			Integer idUnidadeOrganizacional = new Integer(9990);*/
			
			UnidadeOrganizacional unidade = fachada.pesquisarUnidadeOrganizacional(idUnidadeOrganizacional);
			Localidade localidade = this.pesquisarLocalidade(idLocalidade);
			
			//Setando as variáveis no form
			form.setIdLocalidade(localidade.getId().toString());
			form.setDescricaoLocalidade(localidade.getDescricao());
			form.setIdUnidadeOrganizacional(unidade.getId().toString());
			form.setDescricaoUnidadeOrganizacional(unidade.getDescricao());
			form.setIdUnidadeNegocioLocalidade(localidade.getUnidadeNegocio().getId().toString());
			
			//Grupo de Solicitacao
			Collection<SolicitacaoTipoGrupo> colecaoSolicitacao = fachada.obterSolicitacaoTipoGrupo();
	
			Collection<InserirTramitesGruposSolicitacoesHelper> colecaoHelper = new ArrayList<InserirTramitesGruposSolicitacoesHelper>();
			Iterator<SolicitacaoTipoGrupo> it = colecaoSolicitacao.iterator();
			
			while(it.hasNext()){
				
				InserirTramitesGruposSolicitacoesHelper helper = new InserirTramitesGruposSolicitacoesHelper();
				SolicitacaoTipoGrupo tipoGrupo = it.next();
				
				helper.setSolicitacaoTipoGrupo(tipoGrupo);
				helper.setUnidadeOrganizacional(unidade);
				
				colecaoHelper.add(helper);
			}
			
			
			sessao.setAttribute("colecaoTramitesGruposSolicitacoesHelper", colecaoHelper);
		}
		
		
		//Pesquisar Unidade
		//[FS0010] - Validar Unidade Organizacional 
		String pesquisarUnidade = httpServletRequest.getParameter("consultaUnidade");
		if(pesquisarUnidade != null && !pesquisarUnidade.equals("")){
			
			Collection colecaoHelperBusca = (Collection)sessao.getAttribute("colecaoTramitesGruposSolicitacoesHelper");
			
			String posicao = httpServletRequest.getParameter("posicaoUnidade");
			int posicaoINT = Integer.parseInt(posicao);
			String idUnidade = form.getIdsUnidadeOrganizacional()[posicaoINT];
			String idSolicitacaoGrupo = form.getIdsGrupoSolicitacao()[posicaoINT];
			String idUnidadeInserida = form.getIdUnidadeOrganizacional();
			
			UnidadeOrganizacional unidadeInserida = fachada.pesquisarUnidadeOrganizacional(new Integer(idUnidadeInserida));
			
			UnidadeOrganizacional unidadePesquisar = pesquisarUnidadeOrganizacional(new Integer(idUnidade));
			sessao.removeAttribute("corUnidade"+posicaoINT);
			
			//Caso o código da unidade não exista na tabela
			if(unidadePesquisar == null){
				
				throw new ActionServletException("atencao.unidade.organizacional.inexistente");
				
			}
			
			//Caso a unidade de negocio superior da unidade informada for diferente da unidade de negocio da localidade
			else{
				  if(unidadePesquisar.getUnidadeSuperior() == null ||
						  unidadePesquisar.getUnidadeSuperior().getId().compareTo(unidadeInserida.getUnidadeSuperior().getId()) != 0){
					throw new ActionServletException("atencao.unidade_tramite_fora_unidade_negocio");
				}
				
			}
			this.atualizarUnidadeOrganizacional(colecaoHelperBusca, unidadePesquisar, new Integer(idSolicitacaoGrupo));
			
		}
		
		//Remover Unidade
		String apagarUnidade = httpServletRequest.getParameter("apagarUnidade");
		if(apagarUnidade != null && !apagarUnidade.equals("")){
			
			Collection colecaoHelperBusca = (Collection)sessao.getAttribute("colecaoTramitesGruposSolicitacoesHelper");
			
			String posicao = httpServletRequest.getParameter("posicaoUnidade");
			int posicaoINT = Integer.parseInt(posicao);
			String idSolicitacaoGrupo = form.getIdsGrupoSolicitacao()[posicaoINT];
			
			this.atualizarUnidadeOrganizacional(colecaoHelperBusca, null, new Integer(idSolicitacaoGrupo));
			
		}
		
		// devolve o mapeamento de retorno
		return retorno;
	}
	
	
	private UnidadeOrganizacional pesquisarUnidadeOrganizacional(Integer id) {
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		
		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(
			new ParametroSimples(FiltroUnidadeOrganizacional.ID, id));
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacional.UNIDADE_SUPERIOR);

		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			unidadeOrganizacional = 
				(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

		}
		
		return unidadeOrganizacional;
	}
	

	private Localidade pesquisarLocalidade(Integer id) {
		
		Localidade localidade = null;
		
		// Filtro para obter unidade organizacional ativo de id informado
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, id));
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.UNIDADE_NEGOCIO);
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());
	
		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
	
			// Obtém o objeto da coleção pesquisada
			localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
	
		}
		
		return localidade;
	}
	
	
	private void atualizarUnidadeOrganizacional(Collection colecaoUnidades, UnidadeOrganizacional unidade, Integer idSolicitacaoTipo){
		
		Iterator it = colecaoUnidades.iterator();
		while(it.hasNext()){
			
			InserirTramitesGruposSolicitacoesHelper helper = (InserirTramitesGruposSolicitacoesHelper)it.next();
			
			if(idSolicitacaoTipo.compareTo(helper.getSolicitacaoTipoGrupo().getId()) == 0){
				
				helper.setUnidadeOrganizacional(unidade);
				break;
			}	
		}
	}
	

}