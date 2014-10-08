/**
 * 
 */
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
* Diogo Luiz Ramos e Silva
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.bean.UnidadeRepavimentadoraHelper;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1512] Inserir Motivo de Não Execução do Serviço
 * 
 * @author Diogo Luiz
 * @date 20/06/2013
 */
public class ExibirInserirMotivoDeNaoExecucaoDoServicoAction extends GcomAction{
		
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("MotivoDeNaoExecucaoDoServicoInserir");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession();
		
		Usuario usuario = (Usuario)sessao.getAttribute("usuarioLogado");
		
		this.pesquisarUnidadeRepavimentadora(fachada, httpServletRequest, usuario);
		
		InserirMotivoDeNaoExecucaoDoServicoActionForm inserirMotivoDeNaoExecucaoDoServico = 
				(InserirMotivoDeNaoExecucaoDoServicoActionForm) actionForm;
		
		String unidadeRepavimentadora = inserirMotivoDeNaoExecucaoDoServico.getUnidadeRepavimentadora();
		
		Collection<UnidadeRepavimentadoraHelper> colecaoHelper = (Collection<UnidadeRepavimentadoraHelper>) 
				sessao.getAttribute("colecaoHelper");
		
		String adicionar = httpServletRequest.getParameter("adicionar");
			
		if(adicionar != null && adicionar.equals("sim")){
			this.adicionarUnidadeRepavimentadora(unidadeRepavimentadora,colecaoHelper, fachada, sessao);
			inserirMotivoDeNaoExecucaoDoServico.setUnidadeRepavimentadora(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		}			
						
		String remover = httpServletRequest.getParameter("remover");
		String posicao = httpServletRequest.getParameter("posicao");
		if(remover != null && remover.equals("sim")){
			this.removerUnidadeRepavimentadora(posicao,colecaoHelper,sessao);
		}			
		
		return retorno;	
	}
	
	
	private void adicionarUnidadeRepavimentadora(String unidadeRepavimentadora,Collection<UnidadeRepavimentadoraHelper> colecaoHelper,
			Fachada fachada, HttpSession sessao){
		
		if(colecaoHelper == null){
			colecaoHelper = new ArrayList<UnidadeRepavimentadoraHelper>();
		}
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeRepavimentadora));
		Collection<?>colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoUnidade)){
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
			UnidadeRepavimentadoraHelper unidadeHelper = new UnidadeRepavimentadoraHelper();
			unidadeHelper.setId(unidadeOrganizacional.getId());
			unidadeHelper.setUnidadeRepavimentadora(unidadeOrganizacional.getDescricao());
			
			if(!colecaoHelper.contains(unidadeHelper)){
				colecaoHelper.add(unidadeHelper);
			}else{
				throw new ActionServletException("atencao.unidade_repavimentadora_ja_adicionado");
			}
			
			sessao.setAttribute("colecaoHelper", colecaoHelper);
		}		
	}
	
	private void removerUnidadeRepavimentadora(String posicao,Collection<UnidadeRepavimentadoraHelper> colecaoHelper,
			HttpSession sessao){
		Iterator<UnidadeRepavimentadoraHelper> it = colecaoHelper.iterator();
		
		while(it.hasNext()){
			UnidadeRepavimentadoraHelper helper = (UnidadeRepavimentadoraHelper)it.next();
			
			if(helper.getId().toString().equals(posicao)){
				colecaoHelper.remove(helper);
				break;
			}
		}
		sessao.setAttribute("colecaoHelper", colecaoHelper);	
	}	
	
	private void pesquisarUnidadeRepavimentadora(Fachada fachada, HttpServletRequest request, Usuario usuarioLogado){
		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = new ArrayList<UnidadeOrganizacional>();
		
		UnidadeOrganizacional unidadeOrganizacional = usuarioLogado.getUnidadeOrganizacional();
		if(unidadeOrganizacional.getUnidadeTipo() != null && 
				(unidadeOrganizacional.getUnidadeTipo().getId().intValue() != UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue())){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroNaoNulo(FiltroUnidadeOrganizacional.ID_UNIDADE_REPAVIMENTADORA));
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
			
			Collection<?> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, 
					UnidadeOrganizacional.class.getName());
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
