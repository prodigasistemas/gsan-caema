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
package gcom.gui.faturamento;


import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Grupo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1282] Excluir Comando Os Anormalidade
 * 
 * @author Fernanda Almeida
 * @created 12/02/2012
 */
public class ExibirFiltrarOsAnormalidadeConsumoAction extends GcomAction {
	

    private Collection<?> colecaoPesquisa = null;

    private String localidadeIDOrigem = null;
    
    private String localidadeIDDestino = null;
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarOsAnormalidadeConsumo");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
	
		OsAnormalidadeConsumoFiltrarActionForm osAnormalidadeConsumoFiltrarActionForm = (OsAnormalidadeConsumoFiltrarActionForm) actionForm;
		
		
		if ( httpServletRequest.getParameter("limpaLocalidade") != null ) {
			osAnormalidadeConsumoFiltrarActionForm.setLocalidadeOrigemID("");
			osAnormalidadeConsumoFiltrarActionForm.setLocalidadeDestinoID("");
		}
		
		//Colecao de Grupo
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,
			ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection<Grupo> colecaoGrupo = fachada.pesquisar(filtroFaturamentoGrupo,
				FaturamentoGrupo.class.getName());
		
		sessao.setAttribute("colecaoGrupo", colecaoGrupo);		
		
        String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						osAnormalidadeConsumoFiltrarActionForm, fachada,
						httpServletRequest);

				break;
		
			default:
				break;
			}
		}
		
		return retorno;
	}
	
	
    private void pesquisarLocalidade(String inscricaoTipo,
    		OsAnormalidadeConsumoFiltrarActionForm form,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        //Recebe o valor do campo localidadeOrigemID do formulário.
        localidadeIDOrigem = (String) form.getLocalidadeOrigemID();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeIDOrigem));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !localidadeIDOrigem.equalsIgnoreCase("")) {
            //Localidade nao encontrada
            //Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
            // formulário
        	if(form.getLocalidadeOrigemID().equals(form.getLocalidadeDestinoID()))
            {
            	form.setLocalidadeDestinoID("");
            }
        	form.setLocalidadeOrigemID("");
            form.setNomeLocalidadeOrigem("Localidade inexistente");
            httpServletRequest.setAttribute("corLocalidadeOrigem",
            		"exception");
        } else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
            Localidade objetoLocalidade = (Localidade) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            form.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
            form.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
            if(form.getLocalidadeDestinoID() == null || form.getLocalidadeDestinoID().equals("") || 
            		form.getLocalidadeOrigemID().equals(form.getLocalidadeDestinoID()))
            {
            	form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
            	form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
            }
            httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
            httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
        }
        //Recebe o valor do campo localidadeDestinoID do formulário.
        localidadeIDDestino = (String) form.getLocalidadeDestinoID();

        
        /*
         * Alterado por Raphael Rossiter em 26/12/2007
         * OBJ: Corrigir bug aberto por Rosana Carvalho por email em 26/12/2007
         */
        if (localidadeIDDestino != null
            && !localidadeIDDestino.trim().equalsIgnoreCase("")) {
        	
        	//Limpa os parametros do filtro
            filtroLocalidade.limparListaParametros();
            
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, localidadeIDDestino));

            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna localidade
            colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                    Localidade.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Localidade nao encontrada
                //Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
                // do formulário
                form.setLocalidadeDestinoID("");
                form.setNomeLocalidadeDestino("Localidade inexistente");
                httpServletRequest.setAttribute("corLocalidadeDestino",
                        "exception");
                httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");
            } else {
                Localidade objetoLocalidade = (Localidade) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
                form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
                httpServletRequest
                        .setAttribute("corLocalidadeDestino", "valor");
               
            }
        }
        
    }
    
}