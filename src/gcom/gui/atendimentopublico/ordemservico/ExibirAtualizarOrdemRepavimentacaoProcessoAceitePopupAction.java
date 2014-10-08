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

import gcom.atendimentopublico.ordemservico.FiltroOrdemRepavimentadoraServico;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemRepavimentadoraServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.atendimentopublico.ordemservico.bean.ServicoRepavimentadoraHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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

/**
 * [UC1020] Exibir Ordens de Repavimentação em Processo de Aceite.
 * 
 * @author Hugo Leonardo		
 * @date 17/05/2010
 */
public class ExibirAtualizarOrdemRepavimentacaoProcessoAceitePopupAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarOrdemRepavimentacaoProcessoAceitePopUp");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;
		
		if ( sessao.getAttribute("numeroPaginasPesquisa") != null ) {
    		 
    		String paginaAtual = (String) sessao.getAttribute("numeroPaginasPesquisa"); 
    		form.setManterPaginaAux(  paginaAtual );
		}

		form.setDataAceite("");
		form.setIndicadorSituacaoAceite("");
		form.setMotivo("");
		form.setPavimentoRua("");
		form.setPavimentoRuaRetorno("");
		
		Collection collOrdemServicoPavimento = (Collection) sessao.getAttribute("collOrdemServicoPavimentoAceite");
		
		Collection<ServicoRepavimentadoraHelper> colecaoServicoRepavimentadoraHelper = new ArrayList<ServicoRepavimentadoraHelper>();
		
		Collection colecaoAux = null;
		
		Collection colecaoFotoOS = null;
		
		OrdemRepavimentadoraServico ordemRepavimentadoraServico = null;
		
		// Perguntar se posso encontrar apenas pelo numero da Os
       	String numeroOS = null;
       	Integer idOrdemServico = null;
        if (httpServletRequest.getParameter("numeroOS") != null) {
        	numeroOS = httpServletRequest.getParameter("numeroOS"); 
        	idOrdemServico= Util.converterStringParaInteger(numeroOS);       	
        	
        	if ( idOrdemServico != null ) {
	        	FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
				filtroOrdemServicoPavimento.adicionarParametro(new ParametroSimples( FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID,
							idOrdemServico));
		
				Collection colecaoOrdemServicoPavimento = fachada.pesquisar(filtroOrdemServicoPavimento, 
						OrdemServicoPavimento.class.getName());
				OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) 
						Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
				
				//SB0001 
				//1.1
				if ( ordemServicoPavimento.getIndicadorAceite() != null ) {
					form.setIndicadorSituacaoAceite("2");
					form.setDataAceite(Util.formatarData(ordemServicoPavimento.getDataAceite()) );
					form.setMotivo(ordemServicoPavimento.getDescricaoMotivoAceite());
				
				} else {
					
					form.setIndicadorSituacaoAceite("1");
					form.setDataAceite(Util.formatarData(new Date()) );
				}
        	
        	} 
			
        }
     
        if (idOrdemServico != null && httpServletRequest.getParameter("acao") != null && 
        		(httpServletRequest.getParameter("acao").equals("link") 
        		|| httpServletRequest.getParameter("acao").equals("botao"))) {
        	
        	FiltroOrdemRepavimentadoraServico filtroOrdemRepavimentadoraServico = new FiltroOrdemRepavimentadoraServico();
        	
        	FiltroOrdemServicoFoto filtroOrdemServicoFoto = new FiltroOrdemServicoFoto();
       
        	OrdemServico os = fachada.pesquisarOrdemServico(idOrdemServico);
        
        	OrdemServicoPavimento ordemServicoPavimento = null;
        
			if (sessao.getAttribute("collOrdemServicoPavimentoAceite") != null
				&& !sessao.getAttribute("collOrdemServicoPavimentoAceite").equals("")) {
				
					OSPavimentoRetornoHelper oSPavimentoRetornoHelper = null;
		
					Iterator iterator = collOrdemServicoPavimento.iterator();
					
					while (iterator.hasNext()) {
					
						oSPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iterator.next();
		
						if(oSPavimentoRetornoHelper.getOrdemServico().getId().equals(os.getId())){
							
							filtroOrdemRepavimentadoraServico = new FiltroOrdemRepavimentadoraServico();
							filtroOrdemRepavimentadoraServico.adicionarParametro(new ParametroSimples(
									FiltroOrdemRepavimentadoraServico.ID_ORDEM_SERVICO, os.getId()));
							filtroOrdemRepavimentadoraServico.adicionarCaminhoParaCarregamentoEntidade(
									FiltroOrdemRepavimentadoraServico.SERVICO_REPAVIMENTADORA);
							
							colecaoAux = fachada.pesquisar(filtroOrdemRepavimentadoraServico, OrdemRepavimentadoraServico.class.getName());
							
							if(!Util.isVazioOrNulo(colecaoAux)){
								ServicoRepavimentadoraHelper helper = null;
								
								Iterator<?> it = colecaoAux.iterator();
								while(it.hasNext()){
									ordemRepavimentadoraServico = (OrdemRepavimentadoraServico) it.next();
									
									helper = new ServicoRepavimentadoraHelper();
									helper.setIdServico(ordemRepavimentadoraServico.getServicoRepavimentadora().getId().toString());
									helper.setDescricao(ordemRepavimentadoraServico.getServicoRepavimentadora().getDescricao());
									helper.setQuantidade(Util.formatarMoedaReal(ordemRepavimentadoraServico.getQuantidadeServico()));
									
									colecaoServicoRepavimentadoraHelper.add(helper);
								}
							}
							
							ordemServicoPavimento = oSPavimentoRetornoHelper.getOrdemServicoPavimento();
							
							form.setPavimentoRua(ordemServicoPavimento.getPavimentoRua().getDescricaoAbreviada() + " - " +
									Util.formatarBigDecimalComPonto(ordemServicoPavimento.getAreaPavimentoRua()));
							form.setPavimentoRuaRetorno(ordemServicoPavimento.getPavimentoRuaRetorno().getDescricaoAbreviada() + " - " +
									Util.formatarBigDecimalComPonto(ordemServicoPavimento.getAreaPavimentoRuaRetorno()));
							
							filtroOrdemServicoFoto = new FiltroOrdemServicoFoto();
							filtroOrdemServicoFoto.adicionarParametro(new ParametroSimples(FiltroOrdemServicoFoto.ID_ORDEM_SERVICO, os.getId()));
							
							colecaoFotoOS = fachada.pesquisar(filtroOrdemServicoFoto, OrdemServicoFoto.class.getName());
							if(!Util.isVazioOrNulo(colecaoFotoOS)){
								sessao.setAttribute("colecaoFotoOS", colecaoFotoOS);
							}else{
								sessao.removeAttribute("colecaoFotoOS");
							}
							
							httpServletRequest.setAttribute("ordemServicoPavimentoAtualizar", ordemServicoPavimento);
							sessao.setAttribute("ordemServicoPavimentoAtualizar", ordemServicoPavimento);
							sessao.setAttribute("colecaoServicoRepavimentadoraHelper", colecaoServicoRepavimentadoraHelper);
						}
					}	
			}	 
				
        	sessao.setAttribute("acao", "link");       	
		}else if (httpServletRequest.getParameter("acao") != null && 
        		httpServletRequest.getParameter("acao").equals("aceitarOSConvergente") ) {
			
			httpServletRequest.setAttribute("ordemServicoPavimentoAceitarOSConvergente", true);
			httpServletRequest.setAttribute("OSConvergente", "true");
			sessao.setAttribute("ordemServicoPavimentoAceitarOSConvergente", true);
		}
        
		if (httpServletRequest.getParameter("page.offset") != null 
				&& !httpServletRequest.getParameter("page.offset").equals("")) {
			
        	String numeroPagina = httpServletRequest.getParameter("page.offset");   
        	form.setManterPaginaAux(numeroPagina);
		}

		return retorno;
	}
}
