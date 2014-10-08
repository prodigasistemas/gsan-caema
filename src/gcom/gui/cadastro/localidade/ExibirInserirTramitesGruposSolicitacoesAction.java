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

		// Obt�m a sess�o
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
			
			//Setando as vari�veis no form
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
			
			//Caso o c�digo da unidade n�o exista na tabela
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
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
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
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());
	
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
	
			// Obt�m o objeto da cole��o pesquisada
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