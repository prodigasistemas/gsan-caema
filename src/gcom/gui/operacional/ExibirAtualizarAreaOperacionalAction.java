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
import gcom.operacional.AreaDistritoOperacional;
import gcom.operacional.AreaOperacional;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
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
 * [UC1605]	Manter Área Operacional
 * 
 * @author Ana Maria
 * @date 05/06/2014
 */

public class ExibirAtualizarAreaOperacionalAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("atualizarAreaOperacional");
        InserirAreaOperacionalActionForm inserirAreaOperacionalActionForm = (InserirAreaOperacionalActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        
		Fachada fachada = Fachada.getInstancia();      

        String idAreaOperacional = null;
		if ( httpServletRequest.getParameter("reloadPage") == null ||  
				httpServletRequest.getParameter("reloadPage").equals("")){
			//Recupera o id da area operacional que vai ser atualizado            
            if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
            	idAreaOperacional = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
            	//Definindo a volta do botão Voltar p Filtrar area operacional
    	    	sessao.setAttribute("voltar", "filtrar");
    	    	sessao.setAttribute("idRegistroAtualizacao",idAreaOperacional);
            }else if(httpServletRequest.getParameter("idRegistroAtualizacao") == null){
            	idAreaOperacional = (String)sessao.getAttribute("idRegistroAtualizacao");
            	//Definindo a volta do botão Voltar p Filtrar area operacinal
    	    	sessao.setAttribute("voltar", "filtrar");
            }else if (httpServletRequest.getParameter("idRegistroAtualizacao")!= null) {
            	idAreaOperacional = httpServletRequest.getParameter("idRegistroAtualizacao");
            	//Definindo a volta do botão Voltar p Manter area operacional
            	if(httpServletRequest.getParameter("inserir")!= null){
            		sessao.setAttribute("voltar", "filtrar");
            	}else{
            		sessao.setAttribute("voltar", "manter");
            	}
            	sessao.setAttribute("idRegistroAtualizacao",idAreaOperacional);
            }
            
        	inserirAreaOperacionalActionForm.setIdSistemaAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirAreaOperacionalActionForm.setIdSubsistemaAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirAreaOperacionalActionForm.setIdDistritoOperacional("" + ConstantesSistema.NUMERO_NAO_INFORMADO);

            exibirAreaOperacional(idAreaOperacional, inserirAreaOperacionalActionForm, sessao);
           
		}else {
			idAreaOperacional = (String)sessao.getAttribute("idRegistroAtualizacao");
		}
		
		// [IT0001] Exibir Lista de Sistema de Abastecimento
		FiltroSistemaAbastecimento  filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
		filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples
				(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));	
		filtroSistemaAbastecimento.setCampoOrderBy(FiltroSistemaAbastecimento.DESCRICAO);
		
		Collection colecaoSistemaAbastecimento = fachada.pesquisar(
				filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
				
		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Sistema Abastecimento");
		}
		
		httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
		//[IT0002] Exibir Lista de Subsistema de Abastecimento
		Collection colecaoSubsitemaAbastecimento = new ArrayList();
		
		String idSistemaAbastecimento = inserirAreaOperacionalActionForm.getIdSistemaAbastecimento();
		
		if (idSistemaAbastecimento != null && !idSistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			// Subsistema de Abastecimento						
			colecaoSubsitemaAbastecimento = fachada.obterColecaoSubsistemaAbastecimento(new Integer(idSistemaAbastecimento));			
		} 
		
		httpServletRequest.setAttribute("colecaoSubsitemaAbastecimento", colecaoSubsitemaAbastecimento);
		
		//[IT0003] Exibir Lista de Distrito Operacional
		Collection colecaoDistritoOperacional = new ArrayList();
		
		String idSubsistemaAbastecimento = inserirAreaOperacionalActionForm.getIdSubsistemaAbastecimento();
		
		if (idSistemaAbastecimento != null && !idSistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)
			&&	idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			// Distrito Operacional					
			colecaoDistritoOperacional = fachada.obterColecaoDistritoOperacional(new Integer(idSubsistemaAbastecimento));			
		} 
		
		httpServletRequest.setAttribute("colecaoDistritoOperacional", colecaoDistritoOperacional);
        
        if (httpServletRequest.getParameter("desfazer") != null
        	&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	inserirAreaOperacionalActionForm.setIdSistemaAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirAreaOperacionalActionForm.setIdSubsistemaAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirAreaOperacionalActionForm.setIdDistritoOperacional("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	 exibirAreaOperacional(idAreaOperacional, inserirAreaOperacionalActionForm, sessao);
        }		
       
		String adicionar = httpServletRequest.getParameter("adicionar");
		
		//Adicionar Distrito Operacional
		if (adicionar != null && !adicionar.equals("")){
			this.adicionarDistrito(sessao, fachada, inserirAreaOperacionalActionForm);
		}
			
		String remover = httpServletRequest.getParameter("remover");
		
		//Removendo o Distrito Operacional
		if (remover != null && !remover.equals("")){
			this.removerDistrito(remover, sessao, inserirAreaOperacionalActionForm);
		}
    
       return retorno;  
    }


  //Adicionando o Distrito Operacional
  	private void adicionarDistrito(HttpSession sessao, Fachada fachada,
  			InserirAreaOperacionalActionForm inserirAreaOperacionalActionForm){
  									
  			//Verificar se arquivo já foi selecionado
  			if (sessao.getAttribute("colecaoAreaDistritoOperacional") != null){
  				Collection<AreaDistritoOperacional> colecaoDistritoOperacionalAdicionado = (ArrayList<AreaDistritoOperacional>) sessao.getAttribute("colecaoAreaDistritoOperacional");
  					for (AreaDistritoOperacional areaDistritoOperacional : colecaoDistritoOperacionalAdicionado){
  						if(areaDistritoOperacional.getDistritoOperacional().getId().equals(new Integer(inserirAreaOperacionalActionForm.getIdDistritoOperacional()))){
  							throw new ActionServletException("atencao.distrito_operacional_ja_selecionado");
  						}
  					}				
  			}
  			
  			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

  			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
  			FiltroDistritoOperacional.ID, inserirAreaOperacionalActionForm.getIdDistritoOperacional()));

  			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
  			FiltroDistritoOperacional.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

  			// Procura o Distrito Operacional na base
  			Collection distritosOperacionais = fachada.pesquisar(filtroDistritoOperacional,DistritoOperacional.class.getName());

  			DistritoOperacional distritoOperacional = (DistritoOperacional) Util.retonarObjetoDeColecao(distritosOperacionais);
  			
  	
  	        AreaDistritoOperacional areaDistritoOperacional = new AreaDistritoOperacional();
  	        areaDistritoOperacional.setDistritoOperacional(distritoOperacional);
  	        areaDistritoOperacional.setUltimaAlteracao(new Date());
  		
  			//INSERINDO O DISTRITO NA COLEÇÃO DE VISUALIZAÇÃO
  			Collection colecaoAreaDistritoOperacional = null;
  			
  			if (sessao.getAttribute("colecaoAreaDistritoOperacional") != null){
  				
  				colecaoAreaDistritoOperacional = (Collection) sessao.getAttribute("colecaoAreaDistritoOperacional");
  				
  				colecaoAreaDistritoOperacional.add(areaDistritoOperacional);
  				
  			}
  			else{
  				
  				colecaoAreaDistritoOperacional = new ArrayList();
  				colecaoAreaDistritoOperacional.add(areaDistritoOperacional);
  				
  				sessao.setAttribute("colecaoAreaDistritoOperacional", colecaoAreaDistritoOperacional);
  			}

  			inserirAreaOperacionalActionForm.setIdDistritoOperacional("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
  	}
  	
  	//Removendo o Distrito Operacional
  	private void removerDistrito(String identificacao, HttpSession sessao, 
  			InserirAreaOperacionalActionForm inserirAreaOperacionalActionForm){
  		
  		if (identificacao != null && !identificacao.equals("")){
  			
  			Collection colecaoAreaDistritoOperacional = (Collection) 
  			sessao.getAttribute("colecaoAreaDistritoOperacional");
  			
  			Iterator it = colecaoAreaDistritoOperacional.iterator();
  			AreaDistritoOperacional areaDistritoOperacional = null;
  			
  			while (it.hasNext()){
  				
  				areaDistritoOperacional = (AreaDistritoOperacional) it.next();
  				
  				if (obterTimestampIdObjeto(areaDistritoOperacional) == Long.parseLong(identificacao)){
  					String indicadorPrincipal = inserirAreaOperacionalActionForm.getIndicadorPrincipal();
  					if (indicadorPrincipal != null && !indicadorPrincipal.isEmpty()) {
  						Long indicadorPrincipalLong = Long.parseLong(indicadorPrincipal);
	  					if(obterTimestampIdObjeto(areaDistritoOperacional) == indicadorPrincipalLong){
	  						inserirAreaOperacionalActionForm.setIndicadorPrincipal(null);	
	  					}
  					}	
  					colecaoAreaDistritoOperacional.remove(areaDistritoOperacional);
  					break;
  				}
  			}
  			
  			if (colecaoAreaDistritoOperacional.isEmpty()){
  				sessao.removeAttribute("colecaoAreaDistritoOperacional");
  			}
  		}
  	}
    
    private void exibirAreaOperacional(String idAreaOperacional,
    		InserirAreaOperacionalActionForm inserirAreaOperacionalActionForm,
    		HttpSession sessao){
    	
    	AreaOperacional areaOperacional = this.getFachada().obterAreaOperacionalEDistritos(new Integer(idAreaOperacional));
    	Collection<AreaDistritoOperacional> colecaoAreaDistritoOperacional = areaOperacional.getColecaoAreaDistritoOperacional();
    	
    	Iterator<AreaDistritoOperacional> iterator = colecaoAreaDistritoOperacional.iterator();
		while (iterator.hasNext()) {
			AreaDistritoOperacional areaDistritoOperacional = (AreaDistritoOperacional) iterator.next();
			if(areaDistritoOperacional.getIndicadorPrincipal().equals(ConstantesSistema.SIM)){
				inserirAreaOperacionalActionForm.setIndicadorPrincipal(""+obterTimestampIdObjeto(areaDistritoOperacional));
			}
		}	
    	
    	inserirAreaOperacionalActionForm.setDescricao(areaOperacional.getDescricao());
    	inserirAreaOperacionalActionForm.setDescricaoAbreviada(areaOperacional.getDescricaoAbreviada());
    	inserirAreaOperacionalActionForm.setIndicadorUso(areaOperacional.getIndicadorUso().toString());
    	
    	sessao.setAttribute("colecaoAreaDistritoOperacional", colecaoAreaDistritoOperacional);
    }
      
}