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
package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Mater Tarifa de Consumo
 * 
 * @author Tiago Moreno
 */
public class InserirReajusteConsumoTarifaPPPAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucessoPopup");


		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		String[] idsRecuperados = (String[]) sessao.getAttribute("ids");
				
		// Variavel para testar se o campo naum obrigatorio esta vazio
		Enumeration parametros = httpServletRequest.getParameterNames();

		Date dataNovaVigencia = Util.converteStringParaDate(httpServletRequest
				.getParameter("dataVigencia"));
		
		Collection colecaoCategoria = (Collection)sessao.getAttribute("colecaoCategoria");
		
		Map listaParametrosValoresCategoria = new HashMap();

		while (parametros.hasMoreElements()) {
			String parametro = (String) parametros.nextElement();
			int codigoCategoria = 0;

			try {
				codigoCategoria = Integer.parseInt(parametro);
			} catch (NumberFormatException e) {
				continue;
			}

			String valorPercentual = httpServletRequest.getParameter(parametro);

			if(valorPercentual == null || !Util.isBigDecimal(valorPercentual.replace(',','.'))){
				String desc = this.retornarNomeCategoria(colecaoCategoria, codigoCategoria);
				throw new ActionServletException("atencao.percentual_reajuste_invalido_cat",null,desc);
			}
			
			listaParametrosValoresCategoria.put(codigoCategoria,
					new BigDecimal(valorPercentual.replace(',','.')));

		}		

		//inicializa o processo de reajustar e logo apos chama o metodo para reajustar as tarifas
		fachada.iniciarProcessoReajustarTarifaConsumoPPP(listaParametrosValoresCategoria, dataNovaVigencia,
				idsRecuperados);
		
		montarPaginaSucesso(httpServletRequest,
				"Tarifa(s) de Consumo Reajustada(s) com sucesso.", "", "");
		
		return retorno;
	}
	
	
	private String retornarNomeCategoria(Collection colecaoCategoria, Integer idCategoria){
		for(Iterator it = colecaoCategoria.iterator();it.hasNext();){
			
			Categoria cat = (Categoria)it.next();
			if(cat.getId().compareTo(idCategoria) == 0){
				return cat.getDescricao().toUpperCase();
			}
		}
		return null;
	}
}