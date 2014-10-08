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
package gcom.gui.operacional;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSetorSubsistemaAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroSubsistemaSistemaAbastecimento;
import gcom.operacional.SetorSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.operacional.bean.AreaOperacionalHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Vinícius Medeiros
 * @date 20/05/2008
 */

public class ExibirFiltrarZonaPressaoAction extends GcomAction {

	/*
	 * @param actionMapping @param actionForm @param httpServletRequest @param
	 * httpServletResponse @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("filtrarZonaPressao");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm = (FiltrarZonaPressaoActionForm) actionForm;

		String primeiraVez = httpServletRequest.getParameter("menu");
		
		if (primeiraVez != null && !primeiraVez.equals("")) {
			
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarZonaPressaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			filtrarZonaPressaoActionForm.setIndicadorUso("3");
		
		}			
		
		if(filtrarZonaPressaoActionForm.getIndicadorAtualizar()==null){
		
			filtrarZonaPressaoActionForm.setIndicadorAtualizar("1");
		
		}
		
		if (sessao.getAttribute("consulta") != null) {
		
			sessao.removeAttribute("consulta");
		
		}
		
		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = new ArrayList<SistemaAbastecimento>();
		
		if (sessao.getAttribute("colecaoSistemaAbastecimento") == null) {
			
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento(FiltroSistemaAbastecimento.DESCRICAO);
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			
			colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
			
			sessao.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		}
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            
            switch (Integer.parseInt(objetoConsulta)) {

            case 1:
                
            	if (filtrarZonaPressaoActionForm.getIdSistemaAbastecimento() != null &&
            		!filtrarZonaPressaoActionForm.getIdSistemaAbastecimento().equals("") &&
            		!filtrarZonaPressaoActionForm.getIdSistemaAbastecimento().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
            		
            		FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento(FiltroSubsistemaSistemaAbastecimento.DESCRICAO_SUBSISTEMA_ABASTECIMENTO);
            		filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("subsistemaAbastecimento");
            		filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.INDICADOR_USO_SUBSISTEMA_ABASTECIMENTO, ConstantesSistema.INDICADOR_USO_ATIVO));
                    
            		filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SISTEMA_ABASTECIMENTO, Integer.valueOf(filtrarZonaPressaoActionForm.getIdSistemaAbastecimento())));
            	
            		Collection<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = fachada.pesquisar(filtroSubsistemaSistemaAbastecimento, SubsistemaSistemaAbastecimento.class.getName());
        			
        			sessao.setAttribute("colecaoSubsistemaSistemaAbastecimento", colecaoSubsistemaSistemaAbastecimento);
            	}
            	else{
            		
            		sessao.removeAttribute("colecaoSubsistemaSistemaAbastecimento");
            	}
            	
            	sessao.removeAttribute("colecaoSetorSubsistemaAbastecimento");
            	sessao.removeAttribute("colecaoDistritoOperacional");
            	sessao.removeAttribute("colecaoAreaOperacionalHelper");
            	filtrarZonaPressaoActionForm.setIdSubsistemaAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
            	filtrarZonaPressaoActionForm.setIdSetorAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
            	filtrarZonaPressaoActionForm.setIdDistritoOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	filtrarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	
                break;
            
            case 2:
                
            	if (filtrarZonaPressaoActionForm.getIdSubsistemaAbastecimento() != null &&
            		!filtrarZonaPressaoActionForm.getIdSubsistemaAbastecimento().equals("") &&
            		!filtrarZonaPressaoActionForm.getIdSubsistemaAbastecimento().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
            		
            		FiltroSetorSubsistemaAbastecimento filtroSetorSubsistemaAbastecimento = new FiltroSetorSubsistemaAbastecimento(FiltroSetorSubsistemaAbastecimento.DESCRICAO_SETOR_ABASTECIMENTO);
            		filtroSetorSubsistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
            		filtroSetorSubsistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSetorSubsistemaAbastecimento.INDICADOR_USO_SETOR_ABASTECIMENTO, ConstantesSistema.INDICADOR_USO_ATIVO));
                    
            		filtroSetorSubsistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSetorSubsistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, Integer.valueOf(filtrarZonaPressaoActionForm.getIdSubsistemaAbastecimento())));
            	
            		Collection<SetorSubsistemaAbastecimento> colecaoSetorSubsistemaAbastecimento = fachada.pesquisar(filtroSetorSubsistemaAbastecimento, SetorSubsistemaAbastecimento.class.getName());
        			
        			sessao.setAttribute("colecaoSetorSubsistemaAbastecimento", colecaoSetorSubsistemaAbastecimento);
            	}
            	else{
            		
            		sessao.removeAttribute("colecaoSetorSubsistemaAbastecimento");
            	}
            	
            	sessao.removeAttribute("colecaoDistritoOperacional");
            	sessao.removeAttribute("colecaoAreaOperacionalHelper");
            	filtrarZonaPressaoActionForm.setIdSetorAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
            	filtrarZonaPressaoActionForm.setIdDistritoOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	filtrarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	
                break;
                
            case 3:
            	
            	if(Util.verificarIdNaoVazio(filtrarZonaPressaoActionForm.getIdSetorAbastecimento())){
            		Collection<Object[]> colecaoDistrito = fachada.pesquisarDistritoOperacional(null, null, null, null, 
            				filtrarZonaPressaoActionForm.getIdSetorAbastecimento(), ConstantesSistema.INDICADOR_USO_ATIVO.toString(), 
            				null, true);
            		
            		sessao.setAttribute("colecaoDistritoOperacional", colecaoDistrito);
            	}
            	
            	else{
            		sessao.removeAttribute("colecaoDistritoOperacional");
            		
            	}
            	
            	sessao.removeAttribute("colecaoAreaOperacionalHelper");
            	filtrarZonaPressaoActionForm.setIdDistritoOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	filtrarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	
            	break;
            	
            case 4:
                
            	if (Util.verificarIdNaoVazio(filtrarZonaPressaoActionForm.getIdDistritoOperacional())){
            		
            		Collection<AreaOperacionalHelper> colecaoAreaOperacionalHelper = fachada.pesquisarAreaOperacional(null, null, null, 
            			null, filtrarZonaPressaoActionForm.getIdDistritoOperacional(), ConstantesSistema.INDICADOR_USO_ATIVO.toString(), null);
        			
        			sessao.setAttribute("colecaoAreaOperacionalHelper", colecaoAreaOperacionalHelper);
            	}
            	else{
            		
            		sessao.removeAttribute("colecaoAreaOperacionalHelper");
            	}
            	
            	filtrarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	
                break;
            }
        }
	    

        if (httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	filtrarZonaPressaoActionForm.setId("");
        	filtrarZonaPressaoActionForm.setDescricao("");
        	filtrarZonaPressaoActionForm.setDescricaoAbreviada("");
        	
        	filtrarZonaPressaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        	filtrarZonaPressaoActionForm.setIndicadorUso("3");
			sessao.setAttribute("indicadorAtualizar", "1");
        
        	filtrarZonaPressaoActionForm.setIdSistemaAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
        	filtrarZonaPressaoActionForm.setIdSubsistemaAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
        	filtrarZonaPressaoActionForm.setIdSetorAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
        	filtrarZonaPressaoActionForm.setIdDistritoOperacional(ConstantesSistema.INVALIDO_ID.toString());
        	filtrarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
        	
        	sessao.removeAttribute("colecaoSubsistemaSistemaAbastecimento");
        	sessao.removeAttribute("colecaoSetorSubsistemaAbastecimento");
        	sessao.removeAttribute("colecaoDistritoOperacional");
        	sessao.removeAttribute("colecaoAreaOperacionalHelper");
        }
	
        return retorno;
	}
}