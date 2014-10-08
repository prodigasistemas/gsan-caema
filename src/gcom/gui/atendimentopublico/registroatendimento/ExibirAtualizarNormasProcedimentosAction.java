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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.NormaArquivos;
import gcom.atendimentopublico.registroatendimento.NormaProcedimentos;
import gcom.atendimentopublico.registroatendimento.bean.NormasProcedimentosHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
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
 * [UC1601] - Filtrar/Manter Normas e Procedimentos
 * 
 * @author Vivianne Sousa
 * @created 14/05/2014
 */
public class ExibirAtualizarNormasProcedimentosAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("atualizarNormasProcedimentos");
        InserirNormasProcedimentosActionForm inserirNormasProcedimentosActionForm = (InserirNormasProcedimentosActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        
//        FiltroNormaDocumentoTipo filtroNormaDocumentoTipo = new FiltroNormaDocumentoTipo();
//        filtroNormaDocumentoTipo.setCampoOrderBy(FiltroNormaDocumentoTipo.DESCRICAO);
//        filtroNormaDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroNormaDocumentoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
//        Collection<NormaDocumentoTipo> colecaoNormaDocumentoTipo = fachada.pesquisar(filtroNormaDocumentoTipo, NormaDocumentoTipo.class.getName());
//        httpServletRequest.setAttribute("colecaoNormaDocumentoTipo", colecaoNormaDocumentoTipo);
       
        String idNormaProcedimentos = null;
		if ( httpServletRequest.getParameter("reloadPage") == null ||  
				httpServletRequest.getParameter("reloadPage").equals("")){
			//Recupera o id da NormaProcedimentos que vai ser atualizado            
            if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
            	idNormaProcedimentos = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
            	//Definindo a volta do bot�o Voltar p Filtrar NormaProcedimentos
    	    	sessao.setAttribute("voltar", "filtrar");
    	    	sessao.setAttribute("idRegistroAtualizacao",idNormaProcedimentos);
            }else if(httpServletRequest.getParameter("idRegistroAtualizacao") == null){
            	idNormaProcedimentos = (String)sessao.getAttribute("idRegistroAtualizacao");
            	//Definindo a volta do bot�o Voltar p Filtrar NormaProcedimentos
    	    	sessao.setAttribute("voltar", "filtrar");
            }else if (httpServletRequest.getParameter("idRegistroAtualizacao")!= null) {
            	idNormaProcedimentos = httpServletRequest.getParameter("idRegistroAtualizacao");
            	//Definindo a volta do bot�o Voltar p Manter NormaProcedimentos
            	sessao.setAttribute("voltar", "manter");
            	sessao.setAttribute("idRegistroAtualizacao",idNormaProcedimentos);
            	sessao.setAttribute("indicadorAtualizar","1");
            }

           exibirNormasProcedimentos(idNormaProcedimentos, inserirNormasProcedimentosActionForm,sessao);
           
		}else {
			idNormaProcedimentos = (String)sessao.getAttribute("idRegistroAtualizacao");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
        	&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {

        	exibirNormasProcedimentos(idNormaProcedimentos, inserirNormasProcedimentosActionForm,sessao);
        }		
       
    	String adicionar = httpServletRequest.getParameter("adicionar");
  		if (adicionar != null && !adicionar.equals("")){
  			//ADICIONANDO UM ARQUIVO
  	        adicionarArquivo(inserirNormasProcedimentosActionForm, sessao);
  		}
  		
  		String remover = httpServletRequest.getParameter("remover");
  		if (remover != null && !remover.equals("")){
  			//REMOVENDO UM ARQUIVO
  			this.removerArquivo(remover, sessao);
  		}
    
       return retorno;  
    }


	private void adicionarArquivo(InserirNormasProcedimentosActionForm inserirNormasProcedimentosActionForm,
			HttpSession sessao) {
		NormaArquivos normaArquivos = new NormaArquivos();
		normaArquivos.setDescricaoArquivoTexto(inserirNormasProcedimentosActionForm.getArquivo().getFileName());
		normaArquivos.setUltimaAlteracao(new Date());
		try {
			
			normaArquivos.setImagemArquivo(inserirNormasProcedimentosActionForm.getArquivo().getFileData());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//S� poder�o ser informados arquivos com a extens�o: PDF
		String arquivoExtensao = Util.obterExtensaoDoArquivoUltimoPonto(inserirNormasProcedimentosActionForm.getArquivo().getFileName());
		
		if (!arquivoExtensao.equalsIgnoreCase("PDF")){				
			throw new ActionServletException("atencao.formato_arquivo_invalido",null,arquivoExtensao);
		}
		
		//Verificar se arquivo j� foi selecionado
		if (sessao.getAttribute("colecaoAnexo") != null){
			Collection<NormaArquivos> colecaoAnexo = (ArrayList<NormaArquivos>) sessao.getAttribute("colecaoAnexo");
			NormaProcedimentos normaProcedimentos  = new NormaProcedimentos();
			normaProcedimentos.setColecaoArquivos(colecaoAnexo);
				for (NormaArquivos arq : normaProcedimentos.getColecaoArquivos()){
					if(arq.getDescricaoArquivoTexto().equals(inserirNormasProcedimentosActionForm.getArquivo().getFileName())){
						throw new ActionServletException("atencao.arquivo_ja_selecionado");
					}
				}				
		}
		
		//INSERINDO O ARQUIVO NA COLE��O DE VISUALIZA��O
		Collection colecaoAnexo = null;
		
		if (sessao.getAttribute("colecaoAnexo") != null){
			
			colecaoAnexo = (Collection) sessao.getAttribute("colecaoAnexo");
			colecaoAnexo.add(normaArquivos);
		}
		else{
			
			colecaoAnexo = new ArrayList();
			colecaoAnexo.add(normaArquivos);
			sessao.setAttribute("colecaoAnexo", colecaoAnexo);
		}
	}
    
    
    private void exibirNormasProcedimentos(String idNormaProcedimentos,
    		InserirNormasProcedimentosActionForm inserirNormasProcedimentosActionForm,
    		HttpSession sessao){
    	
    	NormasProcedimentosHelper helper = this.getFachada().obterNormasProcedimentosEArquivos(new Integer(idNormaProcedimentos));
    	NormaProcedimentos normaProcedimentos = helper.getNormaProcedimentos();
    	Collection<NormaArquivos> colecaoNormaArquivos = helper.getColecaoNormaArquivos();
    	
    	inserirNormasProcedimentosActionForm.setIdDocumentoTipo(normaProcedimentos.getNormaDocumentoTipo().getId().toString());
    	inserirNormasProcedimentosActionForm.setDescricaoDocumentoTipo(normaProcedimentos.getNormaDocumentoTipo().getDescricaoDocumentoTipo());
    	inserirNormasProcedimentosActionForm.setDescricaoTitulo(normaProcedimentos.getDescricaoTitulo());
    	inserirNormasProcedimentosActionForm.setDescricaoAssunto(normaProcedimentos.getDescricaoAssunto());
    	
    	sessao.setAttribute("colecaoAnexo", colecaoNormaArquivos);
    }
    
    private void removerArquivo(String identificacao, HttpSession sessao){
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection colecaoAnexo = (Collection) 
			sessao.getAttribute("colecaoAnexo");
			
			Iterator it = colecaoAnexo.iterator();
			NormaArquivos anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (NormaArquivos) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					colecaoAnexo.remove(anexoColecao);
					break;
				}
			}
			
			if (colecaoAnexo.isEmpty()){
				sessao.removeAttribute("colecaoAnexo");
			}
		}
	}
    
}