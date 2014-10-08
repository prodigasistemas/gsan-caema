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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.awt.Menu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * < <Descrição da Classe>>
 * 
 * @author Luis Octavio
 */
public class ExibirAssociarFotosOSRepavimentacaoPopUpAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarOrdemProcessoRepavimentacaoPopUp");

		// Obtém o action form
		AssociarFotosOsRepavimentacaoPopUpActionForm form = (AssociarFotosOsRepavimentacaoPopUpActionForm) actionForm;
		
		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String idRA = httpServletRequest.getParameter("idRA");
		
		if(idRA != null && !idRA.equals("")){
			form.setIdRA(idRA);
		}
		
		String desfazer = httpServletRequest.getParameter("desfazer");
		
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		Collection<RegistroAtendimentoAnexo> colecaoArquivos = (Collection<RegistroAtendimentoAnexo>) sessao
				.getAttribute("colecaoArquivos");

		
		if (idRA != null && ( colecaoArquivos == null || colecaoArquivos!= null && colecaoArquivos.size()==0 ) || 
				desfazer != null && !desfazer.equals("")) {
			
			FiltroRegistroAtendimentoAnexo filtroAnexo = new FiltroRegistroAtendimentoAnexo();
			filtroAnexo.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,idRA));		
			colecaoArquivos = fachada.pesquisar(filtroAnexo, RegistroAtendimentoAnexo.class.getName());		
			
		}


		RegistroAtendimentoAnexo raAnexo = null;
		
//		if(form.getIdRA() == null || form.getIdRA()!= null && form.getIdRA().equals("")){
		
			if (httpServletRequest.getParameter("botao") != null
					&& !httpServletRequest.getParameter("botao").equals("")) {
				
					
				raAnexo = new RegistroAtendimentoAnexo();
							
				FormFile arquivo = form.getArquivo();
	
				byte data[] = upload(arquivo);
				if(data.length > 0){
					
					raAnexo.setDescricaoDocumento(form.getObservacao());
					raAnexo.setImagemDocumento(data);
					String nomeFormatado = Util.obterExtensaoDoArquivoUltimoPonto(arquivo.getFileName());
					
					if(nomeFormatado != null){
						String extensao =  nomeFormatado.trim().toUpperCase();//[FE0001] Validar arquivo.
						if(extensao == null || (!extensao.equals("JPG") && !extensao.equals("DOC") && !extensao.equals("PDF"))){
							throw new ActionServletException("atencao.arquivo_invalido");
						}else{
							httpServletRequest.setAttribute("extensao", extensao);
						}
					}else{
						throw new ActionServletException("atencao.formato_arquivo_invalido");
					}
					
					
					RegistroAtendimento ra = new RegistroAtendimento();
					ra.setId(new Integer(form.getIdRA()));
					
					raAnexo.setRegistroAtendimento(ra);
					raAnexo.setNomeExtensaoDocumento( Util.obterExtensaoDoArquivo(arquivo.getFileName()).trim().toUpperCase());
					
					colecaoArquivos.add(raAnexo);

					//Limpa o campo observacao
					form.setObservacao("");
						
				}else{
					//[FE0001] Validar arquivo.
					throw new ActionServletException(
							"atencao.arquivo_obrigatorio");
				}
					
			}
					
//		}else{
//			
//			throw new ActionServletException(
//					"atencao.registro_nao_informado");
//		}
		
		sessao.setAttribute("colecaoArquivos", colecaoArquivos);
		
		return retorno;
	}


		private byte[] upload(FormFile arquivoAnexo){
	    	byte dataFile[] = null;
	    	try {
	    		dataFile = arquivoAnexo.getFileData();
	    		
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
	    	return dataFile;
		}

	

		


}