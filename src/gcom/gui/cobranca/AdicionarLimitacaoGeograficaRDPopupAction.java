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
package gcom.gui.cobranca;

import gcom.cobranca.LimitacaoGeograficaRDHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC0217][SB0001]Adicionar limita��o Geogr�fica RD Popup
 * 
 * @author Nathalia Santos 
 * @since 18/05/2012
 */
public class AdicionarLimitacaoGeograficaRDPopupAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("adicionarLimitacaoGeograficaRDPopup");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarLimitacaoGeograficaRDPopupActionForm form = (AdicionarLimitacaoGeograficaRDPopupActionForm) actionForm;
		
		Collection collectionLimitacaoGeograficaRDHelper = 
			(Collection) sessao.getAttribute("collectionLimitacaoGeograficaRDHelper");
		
		if(collectionLimitacaoGeograficaRDHelper == null){
			collectionLimitacaoGeograficaRDHelper = new ArrayList();	
		}  

		Integer[] localidades = form.getIdLocalidade();
		Integer[] codigosSetores = form.getCodigoSetorComercial();
		Integer[] numerosQuadras = form.getNumeroQuadra();
		
		if(localidades != null && localidades.length > 1){
		
			for (int i = 0; i < localidades.length; i++) {
				
				Integer idLocalidade = localidades[i];
				
				LimitacaoGeograficaRDHelper helper = this.montarHelper(form,idLocalidade,null,null);
				
				if(!collectionLimitacaoGeograficaRDHelper.contains(helper)){
					collectionLimitacaoGeograficaRDHelper.add(helper);	
				}
			}
		}else if(codigosSetores != null && codigosSetores.length > 1){
			
			Integer idLocalidade = localidades[0];
			
			for (int i = 0; i < codigosSetores.length; i++) {
				
				Integer codigoSetor = codigosSetores[i];
				
				LimitacaoGeograficaRDHelper helper = this.montarHelper(form,idLocalidade,codigoSetor,null);
				
				if(!collectionLimitacaoGeograficaRDHelper.contains(helper)){
					collectionLimitacaoGeograficaRDHelper.add(helper);	
				}
			}
		}else if(numerosQuadras != null && numerosQuadras.length > 1){
			
			Integer idLocalidade = localidades[0];
			Integer codigoSetor = codigosSetores[0];
			for (int i = 0; i < numerosQuadras.length; i++) {
				
				Integer numeroQuadra = numerosQuadras[i];
				
				LimitacaoGeograficaRDHelper helper = this.montarHelper(form,idLocalidade,codigoSetor,numeroQuadra);
				
				if(!collectionLimitacaoGeograficaRDHelper.contains(helper)){
					collectionLimitacaoGeograficaRDHelper.add(helper);	
				}
			}
		} else {
			
			Integer idLocalidade = null;	
			if(localidades != null){
				idLocalidade = localidades[0];
			}
			
			Integer codigoSetor = null;
			if(codigosSetores !=  null){
				codigoSetor = codigosSetores[0];
			}
					
			Integer numeroQuadra = null;
			if(numerosQuadras !=  null){
				numeroQuadra = numerosQuadras[0];
			}
			
			LimitacaoGeograficaRDHelper helper = 
					this.montarHelper(form,idLocalidade,codigoSetor,numeroQuadra);
				
			if(!collectionLimitacaoGeograficaRDHelper.contains(helper)){
				collectionLimitacaoGeograficaRDHelper.add(helper);	
			}
		
		} 
		
		if(form.getIdGerenciaRegional() == null || form.getIdGerenciaRegional().equals("-1") || form.getIdGerenciaRegional().equals("")){
			if(form.getIdUnidadeNegocio() == null || form.getIdUnidadeNegocio().equals("-1") || form.getIdUnidadeNegocio().equals("")){
				if(form.getIdLocalidade() == null || form.getIdLocalidade().equals("") || form.getIdLocalidade().equals("-1")){
					if(form.getCodigoSetorComercial() == null || form.getCodigoSetorComercial().equals("") || form.getCodigoSetorComercial().equals("-1")){	
						if(form.getNumeroQuadra() == null || form.getNumeroQuadra().equals("") || form.getNumeroQuadra().equals("-1")){
							throw new ActionServletException(
									"atencao.limitacao_geografica_nao_informada");
						}
					}
				}
			}
		}
		  
		if (form.getDataVigenciaInicio() != null && !form.getDataVigenciaInicio().trim().equals("")
				&& form.getDataVigenciaFim() != null && !form.getDataVigenciaFim().trim().equals("")) {
			if ((Util.converteStringParaDate(form.getDataVigenciaInicio())).compareTo(Util
					.converteStringParaDate(form.getDataVigenciaFim())) >= 0) {
				throw new ActionServletException(
						"atencao.termino_vigencia.anterior.inicio_vigencia");
			}
		}
			
		// Per�odo de vig�ncia inicial Obrigat�rio.
		if (form.getDataVigenciaInicio() == null || form.getDataVigenciaInicio().equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",null,"data ving�ncia in�cio");
		}
		
		// Per�odo de vig�ncia final Obrigat�rio.
		if (form.getDataVigenciaFim() == null || form.getDataVigenciaFim().equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",null,"data ving�ncia fim");
		}
		
		sessao.setAttribute("collectionLimitacaoGeograficaRDHelper", collectionLimitacaoGeograficaRDHelper);
		
		sessao.setAttribute("fechaPopup", "true");
		return retorno;

	}
	
	public LimitacaoGeograficaRDHelper montarHelper(
		AdicionarLimitacaoGeograficaRDPopupActionForm form,Integer idLcalidade,Integer codigoSetor,Integer numeroQuadra){
		
		//criar um novo helper adicionando os  valores do form. e adicionar o helper  a colecao.
		LimitacaoGeograficaRDHelper limitacaoGeograficaRDHelper = new LimitacaoGeograficaRDHelper();
			
			
		if(form.getDataLimiteVencimentoContaParcelar() != null &&
				!form.getDataLimiteVencimentoContaParcelar().equals("")){
			
			limitacaoGeograficaRDHelper.setDataLimiteVencimentoContaParcelar(form.getDataLimiteVencimentoContaParcelar());
		}
		
		if(form.getDataLimiteVencimentoContaVista() != null &&
				!form.getDataLimiteVencimentoContaVista().equals("")){
			
			limitacaoGeograficaRDHelper.setDataLimiteVencimentoContaVista(form.getDataLimiteVencimentoContaVista());
		}
		
		if(form.getDataVigenciaFim() != null &&
				!form.getDataVigenciaFim().equals("")){
			
			limitacaoGeograficaRDHelper.setDataVigenciaFim(form.getDataVigenciaFim());
		}
		
		if(form.getDataVigenciaInicio() != null &&
				!form.getDataVigenciaInicio().equals("")){
			
			limitacaoGeograficaRDHelper.setDataVigenciaInicio(form.getDataVigenciaInicio());
		}
		
		if(form.getNumero() != null &&
				!form.getNumero().equals("")){
			
			limitacaoGeograficaRDHelper.setNumero(form.getNumero());
		}
		
		if(form.getIdGerenciaRegional() != null && 
			!form.getIdGerenciaRegional().equals("-1") &&  
			!form.getIdGerenciaRegional().equals("")){
			
			limitacaoGeograficaRDHelper.setIdGerenciaRegional(form.getIdGerenciaRegional());	
		}
		
		if(form.getIdUnidadeNegocio() != null && 
				!form.getIdUnidadeNegocio().equals("-1") &&  
				!form.getIdUnidadeNegocio().equals("")){
		
			limitacaoGeograficaRDHelper.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
		}
	
		if(idLcalidade != null && 
				!String.valueOf(idLcalidade).equals("-1") && 
					!String.valueOf(idLcalidade).equals("")){
			limitacaoGeograficaRDHelper.setIdLocalidade(idLcalidade);	
		}
		
		if(codigoSetor != null && 
				!String.valueOf(codigoSetor).equals("-1") && 
					!String.valueOf(codigoSetor).equals("")){
			limitacaoGeograficaRDHelper.setCodigoSetorComercial(codigoSetor);	
		}
		
		if(numeroQuadra != null && 
				!String.valueOf(numeroQuadra).equals("-1") && 
					!String.valueOf(numeroQuadra).equals("")){
			limitacaoGeograficaRDHelper.setNumeroQuadra(numeroQuadra);	
		}
		
			
		return  limitacaoGeograficaRDHelper;

	}
}

	
