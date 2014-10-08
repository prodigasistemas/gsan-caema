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
 * [UC1432] Inserir– Parametros Perdas Societarias
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
		
		// Seta o helper para que as validações sejam feitas no controlador
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
		
		// Faz todas as validações de campos obrigatórios e regra de negócio
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
		
		montarPaginaSucesso(httpServletRequest, "Parâmetro de Perda Societária inserido com sucesso.",
				"Inserir outro Parâmetro de Perda Societária",
				"exibirInserirParametrosPerdasSocietariasAction.do?menu=sim");
		
		return retorno;
	}
	
	
}