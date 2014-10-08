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
import gcom.financeiro.FiltroParametrosPerdasSocietarias;
import gcom.financeiro.ParametrosPerdasSocietarias;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC1432] Inserir– Parametros Perdas Societarias
 * 
 * @author Fernanda ALmeida
 *
 * @date 27/01/2013
 */
public class ExibirManterParametrosPerdasSocietariasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterParametrosPerdasSocietarias");		
		
		ParametrosPerdasSocietariasActionForm form = (ParametrosPerdasSocietariasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession();
		
		ParametrosPerdasSocietarias parametrosSocietarias = null;
		
		
		if(httpServletRequest.getParameter("pesquisar") != null && httpServletRequest.getParameter("pesquisar").equals("sim") 
				|| ( httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equals("sim") &&
				sessao.getAttribute("parametrosPerdasSocietarias") != null) ){
			
			if(form.getMesAnoReferenciaContabil()!=null && !form.getMesAnoReferenciaContabil().equals("")){ 

				if(Util.validarAnoMesInvalido(form.getMesAnoReferenciaContabil(),"MM/yyyy") ){
					throw new ActionServletException(
							"atencao.parametros_vigencia_invalido",
							null, form.getMesAnoReferenciaContabil());
				}
				
				String referenciaContabil =  Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferenciaContabil());
				
				FiltroParametrosPerdasSocietarias filtro = new FiltroParametrosPerdasSocietarias();
				filtro.adicionarParametro(new ParametroSimples(FiltroParametrosPerdasSocietarias.ANO_MES_REFERENCIA, new Integer(referenciaContabil)));
				Collection<?> colParametros = fachada.pesquisar(filtro, ParametrosPerdasSocietarias.class.getName());
				if( !colParametros.isEmpty() ){
					parametrosSocietarias = (ParametrosPerdasSocietarias) Util.retonarObjetoDeColecao(colParametros);
					sessao.setAttribute("parametrosPerdasSocietarias", parametrosSocietarias);
					
//					form.setIdParametrosPerdasSocietarias(parametrosSocietarias.getId());
					
					String referenciaFinal = Util.formatarAnoMesParaMesAno(parametrosSocietarias.getAnoMesReferenciaBaixaFinal());
					form.setMesAnoReferenciaBaixaFinal(referenciaFinal);
					
					String referenciaInicial = Util.formatarAnoMesParaMesAno(parametrosSocietarias.getAnoMesReferenciaBaixaInicial());
					form.setMesAnoReferenciaBaixaInicial(referenciaInicial);
					
					form.setMesReferenciaInferior(parametrosSocietarias.getNumeroMesesReferenciaInferior().toString());
					
					setaIndicadores(parametrosSocietarias,form,httpServletRequest);
					
				}else{
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				
			}else{
				throw new ActionServletException("atencao.parametros_societarios_referencia_obrigatorio");
			}
			
						
		}
//			else if(){
//			parametrosSocietarias = (ParametrosPerdasSocietarias) sessao.getAttribute("parametrosPerdasSocietarias");
//			setaIndicadores(parametrosSocietarias,form,httpServletRequest);
//		}
		
		return retorno;
	}

	private void setaIndicadores(ParametrosPerdasSocietarias parametrosSocietarias, ParametrosPerdasSocietariasActionForm form, HttpServletRequest httpServletRequest) {
		if(parametrosSocietarias.getIndicadorCategoriaComercial().equals(ConstantesSistema.SIM)){
			form.setIndicadorCategoriaComercial(ConstantesSistema.SIM.toString());
			form.setComercial(ConstantesSistema.SIM.toString());	
			httpServletRequest.setAttribute("comercial", true);
			
		}else{
			form.setIndicadorCategoriaComercial(ConstantesSistema.NAO.toString());
			form.setComercial(ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("comercial", false);
		}
		if(parametrosSocietarias.getIndicadorCategoriaResidencial().equals(ConstantesSistema.SIM)){
			form.setIndicadorCategoriaResidencial(ConstantesSistema.SIM.toString());
			httpServletRequest.setAttribute("residencial", true);
			
			form.setResidencial(ConstantesSistema.SIM.toString());
		}else{
			form.setIndicadorCategoriaResidencial(ConstantesSistema.NAO.toString());
			form.setResidencial(ConstantesSistema.NAO.toString());	
			httpServletRequest.setAttribute("residencial", false);
			
		}
		if(parametrosSocietarias.getIndicadorCategoriaIndustrial().equals(ConstantesSistema.SIM)){
			form.setIndicadorCategoriaIndustrial(ConstantesSistema.SIM.toString());
			form.setIndustrial(ConstantesSistema.SIM.toString());
			httpServletRequest.setAttribute("industrial", true);
		}else{
			form.setIndicadorCategoriaIndustrial(ConstantesSistema.NAO.toString());
			form.setIndustrial(ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("industrial", false);
		}
		if(parametrosSocietarias.getIndicadorCategoriaPublica().equals(ConstantesSistema.SIM)){
			form.setIndicadorCategoriaPublica(ConstantesSistema.SIM.toString());
			form.setPublica(ConstantesSistema.SIM.toString());
			httpServletRequest.setAttribute("publica", true);
		}else{
			form.setIndicadorCategoriaPublica(ConstantesSistema.NAO.toString());
			form.setPublica(ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("publica", false);
		}
		if(parametrosSocietarias.getIndicadorEsferaEstadual().equals(ConstantesSistema.SIM)){
			form.setIndicadorEsferaEstadual(ConstantesSistema.SIM.toString());
			form.setEstadual(ConstantesSistema.SIM.toString());
			httpServletRequest.setAttribute("estadual", true);
		}else{
			form.setIndicadorEsferaEstadual(ConstantesSistema.NAO.toString());
			form.setEstadual(ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("estadual", false);
		}
		if(parametrosSocietarias.getIndicadorEsferaFederal().equals(ConstantesSistema.SIM)){
			form.setIndicadorEsferaFederal(ConstantesSistema.SIM.toString());
			httpServletRequest.setAttribute("federal", true);
			form.setFederal(ConstantesSistema.SIM.toString());
		}else{
			form.setIndicadorEsferaFederal(ConstantesSistema.NAO.toString());
			form.setFederal(ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("federal", false);
		}
		if(parametrosSocietarias.getIndicadorEsferaMunicipal().equals(ConstantesSistema.SIM)){
			form.setIndicadorEsferaMunicipal(ConstantesSistema.SIM.toString());
			form.setMunicipal(ConstantesSistema.SIM.toString());
			httpServletRequest.setAttribute("municipal", true);
		}else{
			form.setIndicadorEsferaMunicipal(ConstantesSistema.NAO.toString());
			form.setMunicipal(ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("municipal", false);
		}
		if(parametrosSocietarias.getIndicadorEsferaParticular().equals(ConstantesSistema.SIM)){
			form.setIndicadorEsferaParticular(ConstantesSistema.SIM.toString());
			form.setParticular(ConstantesSistema.SIM.toString());
			httpServletRequest.setAttribute("particular", true);
		}else{
			form.setIndicadorEsferaParticular(ConstantesSistema.NAO.toString());
			form.setParticular(ConstantesSistema.NAO.toString());
			httpServletRequest.setAttribute("particular", false);
		}
		
	}
	
	
}