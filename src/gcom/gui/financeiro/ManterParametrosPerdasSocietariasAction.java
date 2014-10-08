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
package gcom.gui.financeiro;

import gcom.fachada.Fachada;
import gcom.financeiro.ParametrosPerdasSocietarias;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1432] Inserir� Parametros Perdas Societarias
 * 
 * @author Fernanda ALmeida
 *
 * @date 27/01/2013
 */
public class ManterParametrosPerdasSocietariasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");		
		
		ParametrosPerdasSocietariasActionForm form = (ParametrosPerdasSocietariasActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession();
						
		// Seta o helper para que as valida��es sejam feitas no controlador
		ParametrosPerdasSocietariasHelper helper = new ParametrosPerdasSocietariasHelper();
		helper.setIndicadorCategoriaComercial(form.getComercial());
		helper.setIndicadorCategoriaIndustrial(form.getIndustrial());
		helper.setIndicadorCategoriaPublica(form.getPublica());
		helper.setIndicadorCategoriaResidencial(form.getResidencial());
		
		helper.setIndicadorEsferaEstadual(form.getEstadual());
		helper.setIndicadorEsferaFederal(form.getFederal());
		helper.setIndicadorEsferaMunicipal(form.getMunicipal());
		helper.setIndicadorEsferaParticular(form.getParticular());
		
		helper.setMesAnoReferenciaBaixaFinal(form.getMesAnoReferenciaBaixaFinal());
		helper.setMesAnoReferenciaBaixaInicial(form.getMesAnoReferenciaBaixaInicial());
		helper.setMesReferenciaInferior(form.getMesReferenciaInferior());
		helper.setMesAnoReferenciaContabil(form.getMesAnoReferenciaContabil());

		fachada.validarParametrosPerdasSocietarias(helper);	
		
		String referenciaInicial = Util.formatarMesAnoParaAnoMesSemBarra( form.getMesAnoReferenciaBaixaInicial() );
		String referenciaFinal = Util.formatarMesAnoParaAnoMesSemBarra( form.getMesAnoReferenciaBaixaFinal() );
		
		ParametrosPerdasSocietarias parametrosSocietarias = (ParametrosPerdasSocietarias) sessao.getAttribute("parametrosPerdasSocietarias");
		
		if(parametrosSocietarias != null){
			parametrosSocietarias.setAnoMesReferenciaBaixaInicial(Integer.valueOf(referenciaInicial));
			parametrosSocietarias.setAnoMesReferenciaBaixaFinal(Integer.valueOf(referenciaFinal));
			parametrosSocietarias.setNumeroMesesReferenciaInferior(new Integer(form.getMesReferenciaInferior() ));
					
			if ( form.getComercial() != null && form.getComercial().equals("1") ) {
				parametrosSocietarias.setIndicadorCategoriaComercial(Short.valueOf(form.getComercial()));	
			} else {
				parametrosSocietarias.setIndicadorCategoriaComercial(ConstantesSistema.NAO);
			}
			
			if ( form.getIndustrial() != null && form.getIndustrial().equals("1") ) {
				parametrosSocietarias.setIndicadorCategoriaIndustrial(Short.valueOf(form.getIndustrial()));	
			} else {
				parametrosSocietarias.setIndicadorCategoriaIndustrial(ConstantesSistema.NAO);
			}
			
			if ( form.getPublica() != null && form.getPublica().equals("1") ) {
				parametrosSocietarias.setIndicadorCategoriaPublica(Short.valueOf(form.getPublica()));	
			} else {
				parametrosSocietarias.setIndicadorCategoriaPublica(ConstantesSistema.NAO);
			}
			
			if ( form.getResidencial() != null && form.getResidencial().equals("1") ) {
				parametrosSocietarias.setIndicadorCategoriaResidencial(Short.valueOf(form.getResidencial()));	
			} else {
				parametrosSocietarias.setIndicadorCategoriaResidencial(ConstantesSistema.NAO);
			}
			
			
			if ( form.getEstadual() != null && form.getEstadual().equals("1") ) {
				parametrosSocietarias.setIndicadorEsferaEstadual(Short.valueOf(form.getEstadual()));	
			} else {
				parametrosSocietarias.setIndicadorEsferaEstadual(ConstantesSistema.NAO);
			}
			
			if ( form.getMunicipal() != null && form.getMunicipal().equals("1") ) {
				parametrosSocietarias.setIndicadorEsferaMunicipal(Short.valueOf(form.getMunicipal()));	
			} else {
				parametrosSocietarias.setIndicadorEsferaMunicipal(ConstantesSistema.NAO);
			}
			
			if ( form.getParticular() != null && form.getParticular().equals("1") ) {
				parametrosSocietarias.setIndicadorEsferaParticular(Short.valueOf(form.getParticular()));	
			} else {
				parametrosSocietarias.setIndicadorEsferaParticular(ConstantesSistema.NAO);
			}
			
			if ( form.getFederal() != null && form.getFederal().equals("1") ) {
				parametrosSocietarias.setIndicadorEsferaFederal(Short.valueOf(form.getFederal()));	
			} else {
				parametrosSocietarias.setIndicadorEsferaFederal(ConstantesSistema.NAO);
			}
					
			parametrosSocietarias.setIndicadorGeracaoReal(ConstantesSistema.NAO);
			parametrosSocietarias.setUltimaAlteracao(new Date());
			
			//[IT0001] 
			fachada.atualizar(parametrosSocietarias);
			
			sessao.removeAttribute("parametrosPerdasSocietarias");
		}else{
			throw new ActionServletException("atencao.parametros_societarios_invalido");
		}
		
		montarPaginaSucesso(httpServletRequest, "Par�metro de Perda Societ�ria atualizado com sucesso.",
				"Manter outro Par�metro de Perda Societ�ria",
				"exibirManterParametrosPerdasSocietariasAction.do?menu=sim");
		
		return retorno;
	
	}
	
	
}