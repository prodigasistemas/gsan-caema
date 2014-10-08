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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.AtualizacaoCadastralArquivoTexto;
import gcom.cadastro.FiltroAtualizacaoCadastralArquivoTexto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1445] Recepcionar Dados dos Im�veis do Tablet para o GSAN OffLine
 * 
 * @author Arthur Carvalho
 * @date 07/03/2013
 */

public class UploadImoveisTabletParaGsanAction extends ExibidorProcessamentoTarefaRelatorio {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        FileItem item = null;
		try {
			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(httpServletRequest);
			
			// pega uma lista de itens do form
			Iterator<?> iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();
				
				// verifica se n�o � diretorio
				if (!item.isFormField()) {
					String nomeArquivo  = item.getName();
					String extensao = "";
					
					//verifica se o arquivo j� foi finalizado
					if(nomeArquivo != null && !nomeArquivo.equals("") && nomeArquivo.split("_")[0] != null){
						String idcomando = nomeArquivo.split("_")[0];
						this.consultarArquivoFinalizado(Long.parseLong(idcomando));
						extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length()); 
					}else{
						throw new ActionServletException("atencao.arquivo_formato_invalido_zip");
					}
					
					if ( extensao.toUpperCase().equals(".ZIP") ) {
						
						InputStream istrm = item.getInputStream();
						ZipInputStream zipInputStream = new ZipInputStream(istrm);
						
						ZipEntry entry;
						//primeiro carrega o txt
			            while ( (entry = zipInputStream.getNextEntry() ) != null ) {
			            	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			            	//zip.getInputStream(zip.getEntry("test.txt"));
			            	String extensaoArquivo = entry.getName().substring(entry.getName().lastIndexOf("."), entry.getName().length());
			            	if( extensaoArquivo.toUpperCase().equals(".TXT") ){
			            		
			            		int count;
			            		byte data[] = new byte[2048];
			            	    String filename = entry.getName();
			            	    System.out.println("Filename: " + filename);
			            	    
			            	    while ((count = zipInputStream.read(data, 0, 2048)) != -1) {
			            	    	byteOut.write(data, 0, count);
			            	    }
			            	     
			            	    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteOut.toByteArray());
			            		
//			            		// abre o arquivo
								InputStreamReader reader = new InputStreamReader( byteArrayInputStream );
								BufferedReader buffer = new BufferedReader(reader);
								
								Fachada.getInstancia().atualizarMovimentoImovelAtualizacaoCadastral(buffer);
			            	} 
			            	
			            }
			            entry= null;
			            InputStream istrmf = item.getInputStream();
						ZipInputStream zipInputStreamf = new ZipInputStream(istrmf);
			            //carrega as imagens
			            while ( (entry = zipInputStreamf.getNextEntry() ) != null ) {
			            	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			            	
			            	String extensaoArquivo = entry.getName().substring(entry.getName().lastIndexOf("."), entry.getName().length());
			            	if ( extensaoArquivo.toUpperCase().equals(".JPG") ) {
			            		
			            		int count;
			            		byte data[] = new byte[1024];
			            	    String filename = entry.getName();
			            	    System.out.println("Filename: " + filename);
			            	    while ((count = zipInputStreamf.read(data, 0, 1024)) != -1) {
			            	    	byteOut.write(data, 0, count);
			            	    }
			            	    
			            	    data = byteOut.toByteArray();

			            	    ArrayList<String> arrayList = this._split(entry.getName());
			            	    String idCodigo = arrayList.get(0);
			            	    String tipoFoto = arrayList.get(1);
			            	    
			            	    getFachada().inserirFotoAtualizacaoCadastral(idCodigo, Integer.parseInt(tipoFoto), data);
			            	}
			            	
			            }
			            
			            montarPaginaSucesso(httpServletRequest, "Arquivo registrado com sucesso!","", "");
						
					} else{
						throw new ActionServletException("atencao.arquivo_formato_invalido_zip");
					}
				}
			}

		} catch (IOException ex) {
			throw new ActionServletException("atencao.importacao.nao_concluida");
		} catch (NumberFormatException ex) {
			throw new ActionServletException("atencao.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}finally{
			try {
				item.getInputStream().close();
			} catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		return retorno;
    }
    
	/**
	 * 
	 * Consultar Arquivo finalizado
	 *
	 * @author Anderson Cabral
	 * @date 01/11/2013
	 *
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void consultarArquivoFinalizado(long idAtualizacaoCadastralArquivoTexto) throws IOException {
		try {
			//Verifica se o arquivo ja foi finalizado
			System.out.println("INICIO: Consulta a situacao do arquivo ID: "+idAtualizacaoCadastralArquivoTexto);
			
			FiltroAtualizacaoCadastralArquivoTexto filtro = new FiltroAtualizacaoCadastralArquivoTexto();
			filtro.adicionarParametro(new ParametroSimples(FiltroAtualizacaoCadastralArquivoTexto.ID_PARAMETRO_TABELA_ATUALIZACAO_CADASTRO, idAtualizacaoCadastralArquivoTexto));
			
			Collection<AtualizacaoCadastralArquivoTexto> colArquivoTexto = this.getFachada().pesquisar(filtro, AtualizacaoCadastralArquivoTexto.class.getName());
			
			AtualizacaoCadastralArquivoTexto atualizacaoCadastralArquivoTexto = (AtualizacaoCadastralArquivoTexto) Util.retonarObjetoDeColecao(colArquivoTexto);
			
			if(atualizacaoCadastralArquivoTexto != null && 
					atualizacaoCadastralArquivoTexto.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)){
				
				System.out.println("FIM: Consulta a situacao do arquivo ID: "+idAtualizacaoCadastralArquivoTexto);
				
			} else {
				System.out.println("ERROR: Ao Consulta situacao do arquivo em campo ID:"+idAtualizacaoCadastralArquivoTexto);
				throw new ActionServletException("atencao.arquivo_finalizado");
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao consultar situacao do arquivo em campo ID:"+idAtualizacaoCadastralArquivoTexto);
			throw new ActionServletException("atencao.arquivo_finalizado");
		}
	}
	/**
     * @author Arthur Carvalho
     * @since 15/09/2011
     * @param line
     *            linha do arquivo que deve ser parseada
     * @return um array com os campos do objeto
     */
    public  ArrayList<String> _split(String line) {
    	ArrayList<String> lines = new ArrayList<String>();

        char[] chars = line.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '_' && chars[i] != '.') {
                sb.append(chars[i]);
            } else {
                lines.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        return lines;
    }
}