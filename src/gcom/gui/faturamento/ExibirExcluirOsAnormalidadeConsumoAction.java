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


import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.ComandoOsAnormalidade;
import gcom.faturamento.FiltroComandoOsAnormalidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

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
public class ExibirExcluirOsAnormalidadeConsumoAction extends GcomAction {
	
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirExcluirOsAnormalidadeConsumo");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
	
		OsAnormalidadeConsumoFiltrarActionForm form = (OsAnormalidadeConsumoFiltrarActionForm) actionForm;
		
		this.validaDados(form.getLocalidadeOrigemID(),form.getLocalidadeDestinoID());
		
		String mesReferencia = form.getAnoMesReferencia();
        String mes = mesReferencia.substring(0, 2);
		String ano = mesReferencia.substring(3, 7);

		String anoMesReferencia = ano + mes;
		
		FiltroComandoOsAnormalidade filtroComandoEmpresaCobrancaConta = new FiltroComandoOsAnormalidade();
		filtroComandoEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(FiltroComandoOsAnormalidade.ANO_MES_REFERENCIA, anoMesReferencia));
		if(!form.getLocalidadeOrigemID().isEmpty()){
			filtroComandoEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(FiltroComandoOsAnormalidade.LOCALIDADE_INICIAL,form.getLocalidadeOrigemID()));
			
		}
		Collection<ComandoOsAnormalidade> colComandoOsAnormalidade = null;
						
		Map<?, ?> resultado = controlarPaginacao(httpServletRequest, retorno, 
			filtroComandoEmpresaCobrancaConta, ComandoOsAnormalidade.class.getName());
		colComandoOsAnormalidade = (Collection<ComandoOsAnormalidade>) resultado.get("colecaoRetorno");
	retorno = (ActionForward) resultado.get("destinoActionForward");		
			

		if(colComandoOsAnormalidade!= null && !colComandoOsAnormalidade.isEmpty()){
				
			httpServletRequest.setAttribute("colComandoOsAnormalidade", colComandoOsAnormalidade);						
	
		}else {
			// Caso n�o haja nenhum resultado da pesquisa
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}		

		return retorno;
		
    }	
	
	/**
     * Verifica se as localidades inicial e final  foram digitadas e 
     * estao ativas;
     * 
     * @author Fernanda Almeida
     * @param idLocalidadeInicial
     * @param idLocalidadeFinal
     */
    private void validaDados(String idLocalidadeInicial, String idLocalidadeFinal){
    	
    	Integer locaFinal = null;
    	Integer locaInicial = null;
    	
    	try{
    		if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
    			if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
    				locaFinal = new Integer(idLocalidadeFinal);
    				locaInicial = new Integer(idLocalidadeInicial);
       
    				if(locaFinal < locaInicial){
    					throw new ActionServletException("atencao.localidade_final_menor_inicial");
    				}
    			}else{
    				//for�a informar localidade final
    				throw new ActionServletException("atencao.informe_localidade_final");
    			}
    		}else{
    			if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
    					//for�a informar localidade inicial
    					throw new ActionServletException("atencao.informe_localidade_inicial");
    			}
    		}
        }catch(java.lang.NumberFormatException ex){
        	throw new ActionServletException("atencao.localidade_numeros_positivos");
        }
        
    	
    	/* Verifica se foi digitado algum valor nas localidades e se eles s�o v�lidos;
    	 */
    	if((idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) &&
    			(idLocalidadeFinal != null && !idLocalidadeFinal.equals(""))){
    		
    		Localidade localidadeInicial= this.buscarLocalidadePorId(idLocalidadeInicial, Fachada.getInstancia());               
    		Localidade localidadeFinal= this.buscarLocalidadePorId(idLocalidadeFinal, Fachada.getInstancia());
    		
    		this.validarLocalidades(localidadeInicial, localidadeFinal);
    	}
        
    }
    
    /**
     * Procura a localidade pelo id informado;  
     * @author Erivan
     * @param id
     * @param fachada
     * @return Localidade
     */
    private Localidade buscarLocalidadePorId(String id, Fachada fachada){
    	Localidade localidade = null;
   
	    FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, id));
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	    
	    // Localidade desativada
	    Collection<Localidade> colecaoLocalidadeAtivada = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
	    
	    if(colecaoLocalidadeAtivada == null || colecaoLocalidadeAtivada.isEmpty()){
	    	throw new ActionServletException("atencao.localidade_desativada");
	    }
	    
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.GERENCIA);
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.UNIDADE_NEGOCIO);
   
	    Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
   
	    if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
	    	localidade = (Localidade) colecaoLocalidade.iterator().next();    
	    }
   
	    return localidade;
    }
    
    /**
     * Verifica os campos Localidade inicial e final;  
     * @author Erivan
     * @param localidadeInicial
     * @param localidadeFinal
     */
    private void validarLocalidades(Localidade localidadeInicial, Localidade localidadeFinal){
    	
    	if(localidadeInicial== null){
    		throw new ActionServletException("atencao.localidade_inicial_inexistente");
    	}
    	if(localidadeFinal == null){
    		throw new ActionServletException("atencao.localidade_final_inexistente");
    	}
    }
}