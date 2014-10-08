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
public class InserirParametrosPerdasSocietariasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");		
		
		ParametrosPerdasSocietariasActionForm form = (ParametrosPerdasSocietariasActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		String referenciaContabil =  Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferenciaContabil());
		
		// Seta o helper para que as valida��es sejam feitas no controlador
		ParametrosPerdasSocietariasHelper helper = new ParametrosPerdasSocietariasHelper();
		helper.setIndicadorCategoriaComercial(form.getIndicadorCategoriaComercial());
		helper.setIndicadorCategoriaIndustrial(form.getIndicadorCategoriaIndustrial());
		helper.setIndicadorCategoriaPublica(form.getIndicadorCategoriaPublica());
		helper.setIndicadorCategoriaResidencial(form.getIndicadorCategoriaResidencial());
		
		helper.setIndicadorEsferaEstadual(form.getIndicadorEsferaEstadual());
		helper.setIndicadorEsferaFederal(form.getIndicadorEsferaFederal());
		helper.setIndicadorEsferaMunicipal(form.getIndicadorEsferaMunicipal());
		helper.setIndicadorEsferaParticular(form.getIndicadorEsferaParticular());
		
		helper.setMesAnoReferenciaBaixaFinal(form.getMesAnoReferenciaBaixaFinal());
		helper.setMesAnoReferenciaBaixaInicial(form.getMesAnoReferenciaBaixaInicial());
		helper.setMesReferenciaInferior(form.getMesReferenciaInferior());
		helper.setMesAnoReferenciaContabil(form.getMesAnoReferenciaContabil());
		
		fachada.validarParametrosPerdasSocietarias(helper);		
		
		if(referenciaContabil != null && !referenciaContabil.equals("")){
			ParametrosPerdasSocietarias perdas = fachada.pesquisarParametrosPerdasSocietarias(new Integer(referenciaContabil));
			
			//1.1
			if( perdas != null ){
				throw new ActionServletException("atencao.parametros_societarios_existentes");
			}
		}
		
		// Faz todas as valida��es de campos obrigat�rios e regra de neg�cio
		fachada.validarParametrosPerdasSocietarias(helper);
		
		String referenciaInicial = Util.formatarMesAnoParaAnoMesSemBarra( helper.getMesAnoReferenciaBaixaInicial() );
		String referenciaFinal = Util.formatarMesAnoParaAnoMesSemBarra( helper.getMesAnoReferenciaBaixaFinal() );		
		
		ParametrosPerdasSocietarias parametrosSocietarias = new ParametrosPerdasSocietarias();
		parametrosSocietarias.setAnoMesReferenciaBaixaInicial(new Integer(referenciaInicial));
		parametrosSocietarias.setAnoMesReferenciaBaixaFinal(new Integer(referenciaFinal));
		parametrosSocietarias.setAnoMesReferenciaContabil(new Integer( referenciaContabil) );
		parametrosSocietarias.setNumeroMesesReferenciaInferior(new Integer(form.getMesReferenciaInferior() ));
		
		
		if ( form.getIndicadorCategoriaComercial() != null && form.getIndicadorCategoriaComercial().equals("1") ) {
			parametrosSocietarias.setIndicadorCategoriaComercial(Short.valueOf(form.getIndicadorCategoriaComercial()));	
		} else {
			parametrosSocietarias.setIndicadorCategoriaComercial(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorCategoriaIndustrial() != null && form.getIndicadorCategoriaIndustrial().equals("1") ) {
			parametrosSocietarias.setIndicadorCategoriaIndustrial(Short.valueOf(form.getIndicadorCategoriaIndustrial()));	
		} else {
			parametrosSocietarias.setIndicadorCategoriaIndustrial(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorCategoriaPublica() != null && form.getIndicadorCategoriaPublica().equals("1") ) {
			parametrosSocietarias.setIndicadorCategoriaPublica(Short.valueOf(form.getIndicadorCategoriaPublica()));	
		} else {
			parametrosSocietarias.setIndicadorCategoriaPublica(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorCategoriaResidencial() != null && form.getIndicadorCategoriaResidencial().equals("1") ) {
			parametrosSocietarias.setIndicadorCategoriaResidencial(Short.valueOf(form.getIndicadorCategoriaResidencial()));	
		} else {
			parametrosSocietarias.setIndicadorCategoriaResidencial(ConstantesSistema.NAO);
		}
		
		
		if ( form.getIndicadorEsferaEstadual() != null && form.getIndicadorEsferaEstadual().equals("1") ) {
			parametrosSocietarias.setIndicadorEsferaEstadual(Short.valueOf(form.getIndicadorEsferaEstadual()));	
		} else {
			parametrosSocietarias.setIndicadorEsferaEstadual(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorEsferaMunicipal() != null && form.getIndicadorEsferaMunicipal().equals("1") ) {
			parametrosSocietarias.setIndicadorEsferaMunicipal(Short.valueOf(form.getIndicadorEsferaMunicipal()));	
		} else {
			parametrosSocietarias.setIndicadorEsferaMunicipal(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorEsferaParticular() != null && form.getIndicadorEsferaParticular().equals("1") ) {
			parametrosSocietarias.setIndicadorEsferaParticular(Short.valueOf(form.getIndicadorEsferaParticular()));	
		} else {
			parametrosSocietarias.setIndicadorEsferaParticular(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorEsferaFederal() != null && form.getIndicadorEsferaFederal().equals("1") ) {
			parametrosSocietarias.setIndicadorEsferaFederal(Short.valueOf(form.getIndicadorEsferaFederal()));	
		} else {
			parametrosSocietarias.setIndicadorEsferaFederal(ConstantesSistema.NAO);
		}
				
		parametrosSocietarias.setIndicadorGeracaoReal(ConstantesSistema.NAO);
		parametrosSocietarias.setUltimaAlteracao(new Date());
		
		//[IT0001] 
		fachada.inserir(parametrosSocietarias);
		
		montarPaginaSucesso(httpServletRequest, "Par�metro de Perda Societ�ria inserido com sucesso.",
				"Inserir outro Par�metro de Perda Societ�ria",
				"exibirInserirParametrosPerdasSocietariasAction.do?menu=sim");
		
		return retorno;
	}
	
	
}