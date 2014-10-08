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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.AreaOperacional;
import gcom.operacional.FiltroAreaOperacional;
import gcom.operacional.FiltroSetorSubsistemaAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroSubsistemaSistemaAbastecimento;
import gcom.operacional.FiltroZonaAreaOperacional;
import gcom.operacional.SetorSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.operacional.ZonaAreaOperacional;
import gcom.operacional.ZonaPressao;
import gcom.operacional.bean.AreaOperacionalHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vinicius Medeiros
 * @date 21/05/2008
 */
public class ExibirAtualizarZonaPressaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("zonaPressaoAtualizar");

		AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm = (AtualizarZonaPressaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		//CARREGAMENTO INICIAL
		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = new ArrayList<SistemaAbastecimento>();
		
		if (sessao.getAttribute("colecaoSistemaAbastecimentoAtualizar") == null) {
			
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento(FiltroSistemaAbastecimento.DESCRICAO);
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			
			colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
			
			sessao.setAttribute("colecaoSistemaAbastecimentoAtualizar", colecaoSistemaAbastecimento);
		}

		String idZonaPressao = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			idZonaPressao = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			idZonaPressao = (String) sessao.getAttribute("idRegistroAtualizacao");
		}

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String adicionar = httpServletRequest.getParameter("adicionar");
		String remover = httpServletRequest.getParameter("remover");
		
		if (idZonaPressao != null && !idZonaPressao.trim().equals("") && Integer.parseInt(idZonaPressao) > 0 &&
			(objetoConsulta == null || objetoConsulta.equals("")) &&
			(adicionar == null || adicionar.equals("")) &&
			(remover == null || remover.equals(""))) {

			ZonaPressao zonaPressao = new ZonaPressao();
			
			FiltroZonaAreaOperacional filtroZonaAreaOperacional = new FiltroZonaAreaOperacional();
			filtroZonaAreaOperacional.adicionarParametro(new ParametroSimples(FiltroZonaAreaOperacional.ID_ZONA_PRESSAO, Integer.valueOf(idZonaPressao)));
			
			filtroZonaAreaOperacional.adicionarCaminhoParaCarregamentoEntidade("areaOperacional");
			filtroZonaAreaOperacional.adicionarCaminhoParaCarregamentoEntidade("zonaPressao");
			
			Collection<ZonaAreaOperacional> colecaoZonaAreaOperacional = fachada.pesquisar(filtroZonaAreaOperacional,
				ZonaAreaOperacional.class.getName());
			
			if (colecaoZonaAreaOperacional != null && !colecaoZonaAreaOperacional.isEmpty()) {
				zonaPressao = ((ZonaAreaOperacional) Util.retonarObjetoDeColecao(colecaoZonaAreaOperacional)).getZonaPressao();
			}

			atualizarZonaPressaoActionForm.setId(zonaPressao.getId().toString());
			atualizarZonaPressaoActionForm.setDescricao(zonaPressao.getDescricaoZonaPressao());
			atualizarZonaPressaoActionForm.setDescricaoAbreviada(zonaPressao.getDescricaoAbreviada());
			atualizarZonaPressaoActionForm.setIndicadorUso(zonaPressao.getIndicadorUso().toString());
			
			atualizarZonaPressaoActionForm.setIdSistemaAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
			atualizarZonaPressaoActionForm.setIdSubsistemaAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
        	atualizarZonaPressaoActionForm.setIdSetorAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
        	atualizarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
			
			sessao.setAttribute("colecaoZonaAreaOperacional", colecaoZonaAreaOperacional);
			
			Iterator<ZonaAreaOperacional> it = colecaoZonaAreaOperacional.iterator();
			
			while (it.hasNext()) {
				
				ZonaAreaOperacional zonaAreaOperacional = (ZonaAreaOperacional) it.next();
				
				if (zonaAreaOperacional.getIndicadorPrincipal().equals(ConstantesSistema.SIM)){
					
					atualizarZonaPressaoActionForm.setIndicadorPrincipal(String.valueOf(GcomAction.obterTimestampIdObjeto(zonaAreaOperacional)));
					break;
				}
			}
			

			if (sessao.getAttribute("colecaoZonaPressao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/filtrarZonaPressaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarZonaPressaoAction.do");
			}
		}
		

        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            switch (Integer.parseInt(objetoConsulta)) {

            case 1:
                
            	if (atualizarZonaPressaoActionForm.getIdSistemaAbastecimento() != null &&
            		!atualizarZonaPressaoActionForm.getIdSistemaAbastecimento().equals("") &&
            		!atualizarZonaPressaoActionForm.getIdSistemaAbastecimento().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
            		
            		FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = new FiltroSubsistemaSistemaAbastecimento(FiltroSubsistemaSistemaAbastecimento.DESCRICAO_SUBSISTEMA_ABASTECIMENTO);
            		filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("subsistemaAbastecimento");
            		filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.INDICADOR_USO_SUBSISTEMA_ABASTECIMENTO, ConstantesSistema.INDICADOR_USO_ATIVO));
                    
            		filtroSubsistemaSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaSistemaAbastecimento.ID_SISTEMA_ABASTECIMENTO, Integer.valueOf(atualizarZonaPressaoActionForm.getIdSistemaAbastecimento())));
            	
            		Collection<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = fachada.pesquisar(filtroSubsistemaSistemaAbastecimento, SubsistemaSistemaAbastecimento.class.getName());
        			
        			sessao.setAttribute("colecaoSubsistemaSistemaAbastecimentoAtualizar", colecaoSubsistemaSistemaAbastecimento);
            	}
            	else{
            		
            		sessao.removeAttribute("colecaoSubsistemaSistemaAbastecimentoAtualizar");
            	}
            	
            	sessao.removeAttribute("colecaoSetorSubsistemaAbastecimentoAtualizar");
            	sessao.removeAttribute("colecaoDistritoOperacionalAtualizar");
            	sessao.removeAttribute("colecaoAreaOperacionalHelperAtualizar");
            	atualizarZonaPressaoActionForm.setIdSubsistemaAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
            	atualizarZonaPressaoActionForm.setIdSetorAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
            	atualizarZonaPressaoActionForm.setIdDistritoOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	atualizarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	
                break;
            
            case 2:
                
            	if (atualizarZonaPressaoActionForm.getIdSubsistemaAbastecimento() != null &&
            		!atualizarZonaPressaoActionForm.getIdSubsistemaAbastecimento().equals("") &&
            		!atualizarZonaPressaoActionForm.getIdSubsistemaAbastecimento().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
            		
            		FiltroSetorSubsistemaAbastecimento filtroSetorSubsistemaAbastecimento = new FiltroSetorSubsistemaAbastecimento(FiltroSetorSubsistemaAbastecimento.DESCRICAO_SETOR_ABASTECIMENTO);
            		filtroSetorSubsistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
            		filtroSetorSubsistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSetorSubsistemaAbastecimento.INDICADOR_USO_SETOR_ABASTECIMENTO, ConstantesSistema.INDICADOR_USO_ATIVO));
                    
            		filtroSetorSubsistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSetorSubsistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, Integer.valueOf(atualizarZonaPressaoActionForm.getIdSubsistemaAbastecimento())));
            	
            		Collection<SetorSubsistemaAbastecimento> colecaoSetorSubsistemaAbastecimento = fachada.pesquisar(filtroSetorSubsistemaAbastecimento, SetorSubsistemaAbastecimento.class.getName());
        			
        			sessao.setAttribute("colecaoSetorSubsistemaAbastecimentoAtualizar", colecaoSetorSubsistemaAbastecimento);
            	}
            	else{
            		
            		sessao.removeAttribute("colecaoSetorSubsistemaAbastecimentoAtualizar");
            	}
            	
            	sessao.removeAttribute("colecaoDistritoOperacionalAtualizar");
            	sessao.removeAttribute("colecaoAreaOperacionalHelperAtualizar");
            	atualizarZonaPressaoActionForm.setIdSetorAbastecimento(ConstantesSistema.INVALIDO_ID.toString());
            	atualizarZonaPressaoActionForm.setIdDistritoOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	atualizarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	
                break;
                
            case 3:
            	
            	if(Util.verificarIdNaoVazio(atualizarZonaPressaoActionForm.getIdSetorAbastecimento())){
            		Collection<Object[]> colecaoDistrito = fachada.pesquisarDistritoOperacional(null, null, null, null, 
            				atualizarZonaPressaoActionForm.getIdSetorAbastecimento(), ConstantesSistema.INDICADOR_USO_ATIVO.toString(), 
            				null, true);
            		
            		sessao.setAttribute("colecaoDistritoOperacionalAtualizar", colecaoDistrito);
            	}
            	
            	else{
            		sessao.removeAttribute("colecaoDistritoOperacionalAtualizar");
            		atualizarZonaPressaoActionForm.setIdDistritoOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	}
            	
            	sessao.removeAttribute("colecaoAreaOperacionalHelperAtualizar");
            	atualizarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	
            	break;
            case 4:
                
            	if (Util.verificarIdNaoVazio(atualizarZonaPressaoActionForm.getIdDistritoOperacional())){
            		
            		Collection<AreaOperacionalHelper> colecaoAreaOperacionalHelper = fachada.pesquisarAreaOperacional(null, null, null, 
            			null, atualizarZonaPressaoActionForm.getIdDistritoOperacional(), ConstantesSistema.INDICADOR_USO_ATIVO.toString(), null);
        			
        			sessao.setAttribute("colecaoAreaOperacionalHelperAtualizar", colecaoAreaOperacionalHelper);
            	}
            	else{
            		
            		sessao.removeAttribute("colecaoAreaOperacionalHelperAtualizar");
            		atualizarZonaPressaoActionForm.setIdAreaOperacional(ConstantesSistema.INVALIDO_ID.toString());
            	}
            	
                break;
            }
        }
        
        
		
		//Adicionar Area Operacional
		if (adicionar != null && !adicionar.equals("")){
			this.adicionarAreaOperacional(sessao, fachada, atualizarZonaPressaoActionForm);
		}
		
		
		
		//Removendo o Distrito Operacional
		if (remover != null && !remover.equals("")){
			this.removerArea(remover, sessao);
		}

		return retorno;
	}
	
	//Adicionando a Area Operacional
		private void adicionarAreaOperacional(HttpSession sessao, Fachada fachada,
				AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm){
										
			if (sessao.getAttribute("colecaoZonaAreaOperacional") != null){
				Collection<ZonaAreaOperacional> colecaoZonaAreaOperacionalAdicionado = (ArrayList<ZonaAreaOperacional>) sessao.getAttribute("colecaoZonaAreaOperacional");
					for (ZonaAreaOperacional zonaAreaOperacional : colecaoZonaAreaOperacionalAdicionado){
						if(zonaAreaOperacional.getAreaOperacional().getId().equals(new Integer(atualizarZonaPressaoActionForm.getIdAreaOperacional()))){
							throw new ActionServletException("atencao.area_operacional_ja_selecionado");
						}
					}				
			}
			
			FiltroAreaOperacional filtroAreaOperacional = new FiltroAreaOperacional();

			filtroAreaOperacional.adicionarParametro(new ParametroSimples(
			FiltroAreaOperacional.ID, Integer.valueOf(atualizarZonaPressaoActionForm.getIdAreaOperacional())));

			filtroAreaOperacional.adicionarParametro(new ParametroSimples(
			FiltroAreaOperacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<AreaOperacional> colecaoAreaOperacional = fachada.pesquisar(filtroAreaOperacional, AreaOperacional.class.getName());

			AreaOperacional areaOperacional = (AreaOperacional) Util.retonarObjetoDeColecao(colecaoAreaOperacional);
			

			ZonaAreaOperacional zonaAreaOperacional = new ZonaAreaOperacional();
			zonaAreaOperacional.setAreaOperacional(areaOperacional);
			zonaAreaOperacional.setUltimaAlteracao(new Date());
		
			//INSERINDO O DISTRITO NA COLEÇÃO DE VISUALIZAÇÃO
			Collection<ZonaAreaOperacional> colecaoZonaAreaOperacional = null;
			
			if (sessao.getAttribute("colecaoZonaAreaOperacional") != null){
				
				colecaoZonaAreaOperacional = (Collection<ZonaAreaOperacional>) sessao.getAttribute("colecaoZonaAreaOperacional");
				
				colecaoZonaAreaOperacional.add(zonaAreaOperacional);
				
			}
			else{
				
				colecaoZonaAreaOperacional = new ArrayList<ZonaAreaOperacional>();
				colecaoZonaAreaOperacional.add(zonaAreaOperacional);
				
				sessao.setAttribute("colecaoZonaAreaOperacional", colecaoZonaAreaOperacional);
			}

			atualizarZonaPressaoActionForm.setIdAreaOperacional("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
		}
		
		//Removendo a Area Operacional
		private void removerArea(String identificacao, HttpSession sessao){
			
			if (identificacao != null && !identificacao.equals("")){
				
				Collection<ZonaAreaOperacional> colecaoZonaAreaOperacional = (Collection<ZonaAreaOperacional>) 
				sessao.getAttribute("colecaoZonaAreaOperacional");
				
				Iterator<ZonaAreaOperacional> it = colecaoZonaAreaOperacional.iterator();
				ZonaAreaOperacional zonaAreaOperacional = null;
				
				while (it.hasNext()){
					
					zonaAreaOperacional = (ZonaAreaOperacional) it.next();
					
					if (obterTimestampIdObjeto(zonaAreaOperacional) == Long.parseLong(identificacao)){
						colecaoZonaAreaOperacional.remove(zonaAreaOperacional);
						break;
					}
				}
				
				if (colecaoZonaAreaOperacional.isEmpty()){
					sessao.removeAttribute("colecaoZonaAreaOperacional");
				}
			}
		}
}