package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.java.help.search.FullBtreeDict;

public class ReceberInformacoesAbastecimentoCarroPipaArquivoTxtAction
	extends GcomAction{
	
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
				
				// verifica se não é diretorio
				if (!item.isFormField()) {
					String nomeArquivo  = item.getName();
					String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length());  
					if(extensao.toUpperCase().equals(".TXT")){
						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item.getInputStream());
						InputStreamReader readerAuxiliar = new InputStreamReader(item.getInputStream());
						BufferedReader buffer = new BufferedReader(reader);
						BufferedReader bufferAuxiliar = new BufferedReader(readerAuxiliar);
					
						if(item.getSize()!=0 && item.getSize()<=2097152){
							if(Util.validarStringNumerica(bufferAuxiliar.readLine())){
								Integer quantidadeImoveisProcessadosArquivoTexto = null;
								quantidadeImoveisProcessadosArquivoTexto = this.quantidadeImoveisProcessarArquivoTexto(bufferAuxiliar);
								if(quantidadeImoveisProcessadosArquivoTexto!=0){
									Fachada.getInstancia().receberInformacoesAbastecimentoCarroPipaArquivoTxt( buffer,quantidadeImoveisProcessadosArquivoTexto );
									montarPaginaSucesso(httpServletRequest, "Arquivo registrado com sucesso!",
												"", "");
								}else{
									throw new ActionServletException("atencao.quantidade_linhas_arquivo_invalidas");
								}
							}else{
								throw new ActionServletException("atencao.quantidade_linhas_arquivo_invalidas");
							}
						}else{
							if(item.getSize()==0){
								throw new ActionServletException("atencao.arquivo_sem_dados",nomeArquivo);
							}else if(item.getSize()>2097152){
								throw new ActionServletException("atencao.arquivo_tamanho_incompativel");
							}
						}	
					}else{
						throw new ActionServletException("atencao.arquivo_formato_invalido");
					}
				}
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
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
	
	/*
	 * autor:Jonathan Marcos
	 * data:22/10/2013
	 * [Observacoes] retorna quantidade de imoveis a serem processados no arquivo
	 */
	public Integer quantidadeImoveisProcessarArquivoTexto(BufferedReader BReader){
		Integer retorno = 0;
		try {
			while(BReader.readLine()!=null){
				retorno++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
