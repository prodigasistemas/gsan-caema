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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.batch.Processo;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarGerarTabelasTemporariasPorLocalidadeAction extends GcomAction {

	/**
	 * [UC1261] Gerar tebalas temporárias para atualização cadastral com dispositivo móvel  
	 * @author: Nathalia Santos , Arthur Carvalho
	 * @date:   14/12/2011      , 27/04/2012
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		GerarTabelasTemporariasPorLocalidadeActionForm form = (GerarTabelasTemporariasPorLocalidadeActionForm) actionForm;
		
		ImovelGeracaoTabelasTemporariasCadastroHelper filtroHelper = this.montarFiltro(form,httpServletRequest);
		
		
		if(filtroHelper.getMatricula() != null && !filtroHelper.getMatricula().equals("")){
			
			
			Collection<Integer> colecaoMatriculas = new ArrayList<Integer>();
			colecaoMatriculas.add(new Integer(filtroHelper.getMatricula()));
			
			filtroHelper.setColecaoMatriculas(colecaoMatriculas);
			
			this.getFachada().obterImovelClienteProprietarioUsuario(filtroHelper);
			
			// montando página de sucesso
			montarPaginaSucesso(httpServletRequest, 
				"Gerado Tabelas Temporarias Com Sucesso !",
				"Gerar Tabelas Temporarias", "/exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?menu=sim");

			
		}else{
			
			Map parametros = new HashMap();
			parametros.put("imovelGeracaoTabelasTemporariasCadastroHelper",filtroHelper);

			this.getFachada().inserirProcessoIniciadoParametrosLivres(
				parametros, 
			    Processo.GERAR_TABELAS_TEMP_ATU_CADASTRAL ,
			    this.getUsuarioLogado(httpServletRequest));
			
			montarPaginaSucesso(httpServletRequest, "Geração de tabelas encaminhada para Batch.", "", "");
			
		}

		return retorno;
	}
	
	private ImovelGeracaoTabelasTemporariasCadastroHelper montarFiltro(
		GerarTabelasTemporariasPorLocalidadeActionForm form,
		HttpServletRequest httpServletRequest){
		
		ImovelGeracaoTabelasTemporariasCadastroHelper filtroHelper =
				new ImovelGeracaoTabelasTemporariasCadastroHelper();
		
		// Empresa (Firma)
		if (form.getEmpresa() != null && 
			!form.getEmpresa().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
					
			filtroHelper.setFirma(form.getEmpresa());
		}
		
		if (form.getMatriculaImovel() != null && 
				!form.getMatriculaImovel().equals("") ) {
						
			filtroHelper.setMatricula(form.getMatriculaImovel());
		}

		
		filtroHelper.setUsuario(this.getUsuarioLogado(httpServletRequest));

		if ( form.getLocalidade() != null && !form.getLocalidade().equals("-1") ) {
			filtroHelper.setLocalidade(form.getLocalidade());
			
			filtroHelper.setLocalidadeInicial(form.getLocalidade());
			filtroHelper.setLocalidadeFinal(form.getLocalidade());
		}
		
		if ( form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("") ) {
			filtroHelper.setMatricula(form.getMatriculaImovel());
		}
		
		if ( form.getSetorComercialSelecionados() != null ) {
			Collection<Integer> colecaoSetorComercial = new ArrayList<Integer>();
			Integer codigoSetorInicial = new Integer(0);
			Integer codigoSetorFinal = new Integer(0);
			for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
				colecaoSetorComercial.add(form.getSetorComercialSelecionados()[i]);
				
				if (codigoSetorInicial.equals(Integer.valueOf(0)) || codigoSetorInicial > form.getSetorComercialSelecionados()[i] ) {
					codigoSetorInicial = form.getSetorComercialSelecionados()[i];
				}
				
				if (codigoSetorFinal.equals(Integer.valueOf(0)) || codigoSetorFinal < form.getSetorComercialSelecionados()[i] ) {
					codigoSetorFinal = form.getSetorComercialSelecionados()[i];
				}
			}
			
			filtroHelper.setCodigoSetorComercial(colecaoSetorComercial);
			filtroHelper.setCodigoSetorComercialFinal(codigoSetorFinal.toString());
			filtroHelper.setCodigoSetorComercialInicial(codigoSetorInicial.toString());
		}
	
		if ( form.getQuadraSelecionados() != null ) {
			Collection<Integer> colecaoQuadra = new ArrayList<Integer>();
			Integer quadraInicial = new Integer(0);
			Integer quadraFinal = new Integer(0);
			for (int i = 0; i < form.getQuadraSelecionados().length; i++) {
				colecaoQuadra.add(form.getQuadraSelecionados()[i]);
				
				if (quadraInicial.equals(Integer.valueOf(0)) || quadraInicial > form.getQuadraSelecionados()[i] ) {
					quadraInicial = form.getQuadraSelecionados()[i];
				}
				
				if (quadraFinal.equals(Integer.valueOf(0)) || quadraFinal < form.getQuadraSelecionados()[i] ) {
					quadraFinal = form.getQuadraSelecionados()[i];
				}
			}
			filtroHelper.setNumeroQuadra(colecaoQuadra);
			filtroHelper.setQuadraInicial(quadraInicial.toString());
			filtroHelper.setQuadraFinal(quadraFinal.toString());
		}
		
		if ( form.getRotaSelecionados() != null ) {
			Collection<Integer> colecaoRota = new ArrayList<Integer>();
			
			Integer rotaInicial = new Integer(0);
			Integer rotaFinal = new Integer(0);
			for (int i = 0; i < form.getRotaSelecionados().length; i++) {
				colecaoRota.add(form.getRotaSelecionados()[i]);
				if (rotaInicial.equals(Integer.valueOf(0)) || rotaInicial > form.getRotaSelecionados()[i] ) {
					rotaInicial = form.getRotaSelecionados()[i];
				}
				
				if (rotaFinal.equals(Integer.valueOf(0)) || rotaFinal < form.getRotaSelecionados()[i] ) {
					rotaFinal = form.getRotaSelecionados()[i];
				}
			}
			filtroHelper.setCodigoRota(colecaoRota);
			filtroHelper.setRotaInicial(Short.valueOf(rotaInicial.toString()));
			filtroHelper.setRotaFinal(Short.valueOf(rotaFinal.toString()));
		}
		
		return filtroHelper;
	}
}
