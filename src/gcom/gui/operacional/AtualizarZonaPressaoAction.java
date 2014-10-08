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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaAreaOperacional;
import gcom.operacional.ZonaPressao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarZonaPressaoAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm = (AtualizarZonaPressaoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		ZonaPressao zonaPressao = new ZonaPressao();
		
		zonaPressao.setId(Integer.valueOf(atualizarZonaPressaoActionForm.getId()));
		zonaPressao.setDescricaoZonaPressao(atualizarZonaPressaoActionForm.getDescricao());
		
		FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

		filtroZonaPressao.adicionarParametro(new ParametroSimples(
				FiltroZonaPressao.DESCRICAO, zonaPressao.getDescricaoZonaPressao()));
		filtroZonaPressao.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroZonaPressao.ID, zonaPressao.getId()));
		

		Collection<ZonaPressao> colecaoPesquisa = (Collection<ZonaPressao>) fachada.pesquisar(
				filtroZonaPressao, ZonaPressao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			 
			throw new ActionServletException(
					"atencao.zona_pressao_ja_cadastrada", null,
					zonaPressao.getDescricaoZonaPressao());
		}
		
		if (atualizarZonaPressaoActionForm.getDescricaoAbreviada() != null && !atualizarZonaPressaoActionForm.getDescricaoAbreviada().equals("")){
			zonaPressao.setDescricaoAbreviada(atualizarZonaPressaoActionForm.getDescricaoAbreviada());
		}
		
		zonaPressao.setIndicadorUso(Short.valueOf(atualizarZonaPressaoActionForm.getIndicadorUso()));
		
		Collection<ZonaAreaOperacional> colecaoZonaAreaOperacional = (Collection<ZonaAreaOperacional>) sessao.getAttribute("colecaoZonaAreaOperacional");
		
		if (colecaoZonaAreaOperacional != null && !colecaoZonaAreaOperacional.isEmpty()) {	
			colecaoZonaAreaOperacional = (ArrayList<ZonaAreaOperacional>) sessao.getAttribute("colecaoZonaAreaOperacional");
				
			String indicadorPrincipal = atualizarZonaPressaoActionForm.getIndicadorPrincipal();
			if (indicadorPrincipal != null && !indicadorPrincipal.isEmpty()) {
				Long indicadorPrincipalLong = Long.parseLong(indicadorPrincipal);
				Iterator<ZonaAreaOperacional> iterator = colecaoZonaAreaOperacional.iterator();
				while (iterator.hasNext()) {
					ZonaAreaOperacional zonaAreaOperacional = (ZonaAreaOperacional) iterator.next();

					if (obterTimestampIdObjeto(zonaAreaOperacional) == indicadorPrincipalLong
							.longValue()) {
						zonaAreaOperacional.setIndicadorPrincipal(ConstantesSistema.SIM);
					} else {
						zonaAreaOperacional.setIndicadorPrincipal(ConstantesSistema.NAO);
					}
				}

			} else {
				throw new ActionServletException(
						"atencao.area_operacional_principal.nao_selecionado");
			}
        }
		else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null,"pelo menos uma Área Operacional");
        }
		
		Set<ZonaAreaOperacional> areasOperacionais = new HashSet<ZonaAreaOperacional>();
		areasOperacionais.addAll((Collection<ZonaAreaOperacional>) sessao.getAttribute("colecaoZonaAreaOperacional"));
		
		zonaPressao.setAreasOperacionais(areasOperacionais);
		
		fachada.atualizarZonaPressao(zonaPressao);
		
		montarPaginaSucesso(httpServletRequest, "Zona de Pressão "
				+ atualizarZonaPressaoActionForm.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Zona de Pressão ",
				"exibirFiltrarZonaPressaoAction.do?menu=sim");
		
		return retorno;
	}
}
