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
package gcom.gui.faturamento;


import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.ComandoOsAnormalidade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1280] Informar Imovel Gerar Os Anormalidade Consumo
 * 
 * @author Fernanda ALmeida
 * @created 10/02/2012
 */
public class ConsultarOsAnormalidadeConsumoAction extends GcomAction {
	
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarOsAnormalidadeConsumo");		

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();		

		Collection<ComandoOsAnormalidade> colComandoOsAnormalidade = new ArrayList<ComandoOsAnormalidade>();
		
		if(httpServletRequest.getParameter("id") != null){
			Object[] object = fachada.buscaComandoOsAnormalidade(httpServletRequest.getParameter("id"));
			ComandoOsAnormalidade comandoOsAnormalidade = new ComandoOsAnormalidade();
			comandoOsAnormalidade.setId((Integer) object[0]);
			comandoOsAnormalidade.setAnoMesReferencia((Integer) object[1]);
			
			if(object[2] != null){
				FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
				filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,(Integer)object[2]));
				Collection<GerenciaRegional> colGerencia = fachada.pesquisar(filtroGerencia,GerenciaRegional.class.getName());
				
				GerenciaRegional gerencia = (GerenciaRegional) Util.retonarObjetoDeColecao(colGerencia);
				
				comandoOsAnormalidade.setGerenciaRegional(gerencia);
			}
			
			if(object[3] != null){
				FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
				filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, (Integer)object[3]));
				Collection<UnidadeNegocio> colUnidadeNegocio = fachada.pesquisar(filtroUnidade,UnidadeNegocio.class.getName());
				
				UnidadeNegocio unidade = (UnidadeNegocio) Util.retonarObjetoDeColecao(colUnidadeNegocio);
				
				comandoOsAnormalidade.setUnidadeNegocio(unidade);
			}
			
			if(object[4] != null){
				FiltroFaturamentoGrupo filtroFaturamento = new FiltroFaturamentoGrupo();
				filtroFaturamento.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID,(Integer)object[4]));
				Collection<FaturamentoGrupo> colGrupo = fachada.pesquisar(filtroFaturamento,FaturamentoGrupo.class.getName());
				
				FaturamentoGrupo grupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colGrupo);
				
				comandoOsAnormalidade.setFaturamentoGrupo(grupo);
			}
			
			if(object[5] != null){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,(Integer)object[5]));
				Collection<Localidade> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				Localidade localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
										
				comandoOsAnormalidade.setLocalidadeInicial(localidadeInicial);
			}
			
			if(object[6] != null){
				FiltroSetorComercial filtroSetor = new FiltroSetorComercial();
				filtroSetor.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID,(Integer)object[6]));
				Collection<SetorComercial> colSetorinicial = fachada.pesquisar(filtroSetor, SetorComercial.class.getName());
				
				SetorComercial setorInicial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorinicial);
										
				comandoOsAnormalidade.setSetorComercialInicial(setorInicial);
			}
			
			if(object[7] != null){
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,(Integer)object[11]));
				Collection<Quadra> colQuadraFinal = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colQuadraFinal);
				
				comandoOsAnormalidade.setQuadraInicial(quadra);
			}
			
			if(object[8] != null){
				Rota rotaInicial = new Rota();
				rotaInicial.setId((Integer) object[8]);			
				comandoOsAnormalidade.setRotaInicial(rotaInicial);
			}
			
			if(object[9] != null){
				FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
				filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,(Integer)object[9]));
				Collection<Localidade> colLocalidadeFinal = fachada.pesquisar(filtroLocalidadeFinal, Localidade.class.getName());
				Localidade localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colLocalidadeFinal);
				
				comandoOsAnormalidade.setLocalidadeFinal(localidadeFinal);
			}
			
			if(object[10] != null){			
				FiltroSetorComercial filtroSetorFinal = new FiltroSetorComercial();
				filtroSetorFinal.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID,(Integer)object[10]));
				Collection<SetorComercial> colSetorFinal = fachada.pesquisar(filtroSetorFinal, SetorComercial.class.getName());
				
				SetorComercial setorFinal = (SetorComercial) Util.retonarObjetoDeColecao(colSetorFinal);
										
				comandoOsAnormalidade.setSetorComercialFinal(setorFinal);
			}
			
			if(object[11] != null){		
				FiltroQuadra filtroQuadraFinal = new FiltroQuadra();
				filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,(Integer)object[11]));
				Collection<Quadra> colQuadraFinal = fachada.pesquisar(filtroQuadraFinal, Quadra.class.getName());
				
				Quadra quadraFinal = (Quadra) Util.retonarObjetoDeColecao(colQuadraFinal);
				
				comandoOsAnormalidade.setQuadraFinal(quadraFinal);
			}
			
			if(object[12] != null){		
				Rota rotaFinal = new Rota();
				rotaFinal.setId((Integer) object[12]);	
				comandoOsAnormalidade.setRotaFinal(rotaFinal);
			}
			
			
			colComandoOsAnormalidade.add(comandoOsAnormalidade);
			sessao.setAttribute("colComandoOsAnormalidade", colComandoOsAnormalidade);
		}
		if(colComandoOsAnormalidade.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
				
		return retorno;			
		
    }	
}