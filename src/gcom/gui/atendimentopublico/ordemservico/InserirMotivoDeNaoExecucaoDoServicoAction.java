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
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.FiltroMotivoDeNaoExecucaoDoServico;
import gcom.atendimentopublico.ordemservico.MotivoDeNaoExecucaoDoServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
/**
 * [UC1512] Inserir Motivo de Não Execução do Serviço
 * 
 * @author Diogo Luiz
 * @date 20/06/2013
 */
public class InserirMotivoDeNaoExecucaoDoServicoAction extends GcomAction {
	
	@SuppressWarnings({ "null", "unchecked" })
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
	
		InserirMotivoDeNaoExecucaoDoServicoActionForm inserirMotivoDeNaoExecucaoDoServico = 
				(InserirMotivoDeNaoExecucaoDoServicoActionForm) actionForm;
		
		MotivoDeNaoExecucaoDoServico motivoDeNaoExecucaoDoServico = new MotivoDeNaoExecucaoDoServico();   
		
		//Atualizar entidade com os dados do form
		String descricao = inserirMotivoDeNaoExecucaoDoServico.getDescricao().toUpperCase();
		String descricaoAbreviada = inserirMotivoDeNaoExecucaoDoServico.getDescricaoAbreviada();
		
		motivoDeNaoExecucaoDoServico.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		motivoDeNaoExecucaoDoServico.setUltimaAlteracao(new Date());
		
		Collection<?> colecaoHelper = (Collection) sessao.getAttribute("colecaoHelper");
		
		if(descricao != null || !descricao.equals("")){		
			
			FiltroMotivoDeNaoExecucaoDoServico filtro = new FiltroMotivoDeNaoExecucaoDoServico();  
			filtro.adicionarParametro(new ParametroSimples(FiltroMotivoDeNaoExecucaoDoServico.DESCRICAO, descricao));
			Collection<MotivoDeNaoExecucaoDoServico> verificarDescricao = new ArrayList<MotivoDeNaoExecucaoDoServico>(); 
			verificarDescricao = fachada.pesquisar(filtro, MotivoDeNaoExecucaoDoServico.class.getName());
			if(!Util.isVazioOrNulo(verificarDescricao)){			
				throw new ActionServletException("atencao.descricao_ja_existe");			
			}
			motivoDeNaoExecucaoDoServico.setDescricao(descricao);			
		}else{
			throw new ActionServletException("atencao.descricao_repavimentadora_invalido");
		}		
		
		if(descricaoAbreviada == null || descricaoAbreviada.equals("")){
			motivoDeNaoExecucaoDoServico.setDescricaoAbreviada(null);
		}else{
			motivoDeNaoExecucaoDoServico.setDescricaoAbreviada(descricaoAbreviada.toUpperCase());
		}
		
		Integer id = null;
		if(!Util.isVazioOrNulo(colecaoHelper)){
			
			id = (Integer) fachada.inserirMotivoDeNaoExecucaoDoServico(motivoDeNaoExecucaoDoServico, 
						colecaoHelper);			
		}else{
			throw new ActionServletException("atencao.unidade_repavimentadora_nao_adicionado");
		}		
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Motivo de não execução do serviço " + 
				inserirMotivoDeNaoExecucaoDoServico.getDescricao().toUpperCase()
				+ " inserido com sucesso.", "Inserir outro Motivo de não execução do serviço",
				"exibirInserirMotivoDeNaoExecucaoDoServicoAction.do?menu=sim",
				"exibirAtualizarMotivoDeNaoExecucaoDoServicoAction.do?idMotivo="+ id,
				 "Atualizar Motivo de não execução do serviço");	
		
		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMotivoDeNaoExecucaoDoServicoAction.do");
		sessao.removeAttribute("InserirMotivoDeNaoExecucaoDoServicoActionForm");
		
		return retorno;	
		
	}
}
