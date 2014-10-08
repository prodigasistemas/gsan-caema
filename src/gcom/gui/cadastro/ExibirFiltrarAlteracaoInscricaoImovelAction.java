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
package gcom.gui.cadastro;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1162] AUTORIZAR ALTERACAO INSCRICAO IMOVEL
 * 
 * @author Rodrigo Cabral
 * @date 04/04/2011
 */

public class ExibirFiltrarAlteracaoInscricaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarAlteracaoInscricaoImovel");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarAlteracaoInscricaoImovelActionForm form = (FiltrarAlteracaoInscricaoImovelActionForm) actionForm;

		String limparCampos = httpServletRequest.getParameter("limparCampos");
		
		if(limparCampos != null){
			form.setIdLocalidade("");
			form.setDesLocalidade("");
			limparCampos(form, sessao);
			
		}else{
		
			//1 - Pesquisar por Localizacao Geografica
			if(form.getIndicadorSelecionarPor() == null || form.getIndicadorSelecionarPor().equals("")){
				form.setIndicadorSelecionarPor("1");
			}
			
			String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
			
			if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
				
				switch (Integer.parseInt(objetoConsulta)) {
					// Localidade
					case 1:
						limparCampos(form, sessao);
						pesquisarLocalidade(form, fachada, httpServletRequest);
						pesquisarSetorComercial(form, fachada, httpServletRequest);
						break;
					
					default:
						break;
				}
			} else {
				sessao.removeAttribute("form");
			}
			
			
			String removerSetor = httpServletRequest.getParameter("removerSetor");
	
			Collection<SetorComercial> colecaoSetorComercial = (Collection<SetorComercial>) sessao.getAttribute("colecaoSetorComercial");
			if (colecaoSetorComercial == null){
				colecaoSetorComercial = new ArrayList<SetorComercial>();
				sessao.setAttribute("colecaoSetorComercial", colecaoSetorComercial);			
			}
			
			ArrayList<SetorComercial> colecaoSetoresSelecionados = (ArrayList<SetorComercial>) sessao.getAttribute("colecaoSetorComercialSelecionados");
			if (colecaoSetoresSelecionados == null){
				colecaoSetoresSelecionados = new ArrayList<SetorComercial>();
				sessao.setAttribute("colecaoSetorComercialSelecionados", colecaoSetoresSelecionados);			
			}
			
			Integer[] setoresSelecionados = form.getSetorComercialSelecionados();
		
			if (setoresSelecionados != null){
				if (removerSetor != null && !removerSetor.equalsIgnoreCase("")){
					
					List<Integer> setoresSelecionadoList = (List<Integer>) Arrays.asList(setoresSelecionados);
					ArrayList<Integer> setoresSelecionadoArray = new ArrayList<Integer>(setoresSelecionadoList);
					
					//retira dos selecionados
					//coloca nos disponiveis
					ArrayList<SetorComercial> setores = (ArrayList<SetorComercial>) colecaoSetoresSelecionados.clone();
					Iterator<SetorComercial> iter = setores.iterator();
					while (iter.hasNext()) {
						SetorComercial setor = (SetorComercial) iter.next();
						if (!setoresSelecionadoArray.contains(setor.getId())){
							colecaoSetorComercial.add(setor);
							colecaoSetoresSelecionados.remove(setor);
						}
					}
				}else{
					//retira dos disponiveis
					//coloca nos selecionados
					for (int i = 0; i < setoresSelecionados.length; i++) {
						Iterator<SetorComercial> iter = colecaoSetorComercial.iterator();
						while (iter.hasNext()) {
							SetorComercial setor = (SetorComercial) iter.next();
							if (setor.getId().intValue() == setoresSelecionados[i]){
								colecaoSetoresSelecionados.add(setor);
								colecaoSetorComercial.remove(setor);
								break;
							}
						}
					}
				}
			}
					
			sessao.setAttribute("colecaoSetorComercial", colecaoSetorComercial);			
			sessao.setAttribute("colecaoSetorComercialSelecionados", colecaoSetoresSelecionados);
			
			if(colecaoSetoresSelecionados.size() == 1){
				this.pesquisarQuadras(form, httpServletRequest);
			}
		}
		return retorno;
	}
	
	private void pesquisarLocalidade(
			FiltrarAlteracaoInscricaoImovelActionForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			String idLocalidade = (String) form.getIdLocalidade();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formul�rio
				form.setIdLocalidade("");
				form.setDesLocalidade("Localidade inexistente");
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
				
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setIdLocalidade(String.valueOf(objetoLocalidade.getId()));
				form.setDesLocalidade(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
				
				}
		

	}
	
	private void pesquisarSetorComercial(
			FiltrarAlteracaoInscricaoImovelActionForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
			HttpSession sessao = httpServletRequest.getSession(false);
		
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			String idLocalidade = (String) form.getIdLocalidade();
	        
	        Collection<SetorComercial> colecaoSetorComercial = (Collection<SetorComercial>) sessao.getAttribute("colecaoSetorComercial");        
			       
	        // Pesquisando Setor Comercial
			if (idLocalidade != null
					&& !idLocalidade.trim().equalsIgnoreCase("")) {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				
				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna cole��o de setor comercial
				colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {			
					sessao.setAttribute("colecaoSetorComercial",
							colecaoSetorComercial);
					sessao.setAttribute("colecaoSetorComercialSelecionados",
							new ArrayList<SetorComercial>());					
					
				} 
			} 
			if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
				sessao.setAttribute("colecaoSetorComercial", new ArrayList<SetorComercial>());			
			}
	}
	
	/**
	 * @date 22/07/2013
	 * @author Anderson Cabral
	 */
	private void pesquisarQuadras(FiltrarAlteracaoInscricaoImovelActionForm form,  
			HttpServletRequest httpServletRequest) {
		
		Collection<Integer> colecaoSetorComercial = new ArrayList<Integer>();
		
		for (int i = 0; i < form.getSetorComercialSelecionados().length; i++) {
			colecaoSetorComercial.add(form.getSetorComercialSelecionados()[i]);
		}
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimplesIn(FiltroQuadra.ID_SETORCOMERCIAL, colecaoSetorComercial));
        filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
        
		Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (quadras != null && !quadras.isEmpty()) {

			httpServletRequest.setAttribute( "colecaoQuadra", quadras );
		} else {
			form.setQuadraSelecionados(null);
		}
	}
	
	private void limparCampos(FiltrarAlteracaoInscricaoImovelActionForm form, HttpSession sessao){
		form.setSetorComercial(null);
		form.setSetorComercialSelecionados(null);
		form.setQuadra(null);
		form.setQuadraSelecionados(null);
		form.setIndicadorImovelAlteradoExcluido("");
		form.setIndicadorOrigemAtualizacao("");
		
		sessao.setAttribute("colecaoSetorComercial", new ArrayList<SetorComercial>());
		sessao.setAttribute("colecaoSetorComercialSelecionados", new ArrayList<SetorComercial>());
	}
}
