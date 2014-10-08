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
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null,"pelo menos uma �rea Operacional");
        }
		
		Set<ZonaAreaOperacional> areasOperacionais = new HashSet<ZonaAreaOperacional>();
		areasOperacionais.addAll((Collection<ZonaAreaOperacional>) sessao.getAttribute("colecaoZonaAreaOperacional"));
		
		zonaPressao.setAreasOperacionais(areasOperacionais);
		
		fachada.atualizarZonaPressao(zonaPressao);
		
		montarPaginaSucesso(httpServletRequest, "Zona de Press�o "
				+ atualizarZonaPressaoActionForm.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Zona de Press�o ",
				"exibirFiltrarZonaPressaoAction.do?menu=sim");
		
		return retorno;
	}
}
