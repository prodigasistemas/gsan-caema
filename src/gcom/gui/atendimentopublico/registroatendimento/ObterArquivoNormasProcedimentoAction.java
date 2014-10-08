package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.NormaArquivos;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ObterArquivoNormasProcedimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		ActionForward retorno = null;
				
		
		FiltroNormaArquivos filtroNormaArquivos = new FiltroNormaArquivos();
		filtroNormaArquivos.adicionarParametro(new ParametroSimples(FiltroNormaArquivos.ID, 
			Integer.valueOf(httpServletRequest.getParameter("idArquivo").toString())));
		
		Collection<?> colArquivo = Fachada.getInstancia().pesquisar(filtroNormaArquivos, NormaArquivos.class.getName());
		
		if(!Util.isVazioOrNulo(colArquivo)){
			NormaArquivos arquivoTexto = (NormaArquivos) Util.retonarObjetoDeColecao(colArquivo);
			
			File temporario;
			
			if(arquivoTexto.getImagemArquivo() != null && arquivoTexto.getImagemArquivo().length > 0){
				try {
					temporario = File.createTempFile("temporario", ".pdf");

					FileOutputStream out = new FileOutputStream(temporario);
							
						byte[] arq = (byte[]) arquivoTexto.getImagemArquivo();
						
						String parametroArquivoBaixarRota = "";
						
						httpServletResponse.setContentLength(1 + parametroArquivoBaixarRota.getBytes().length + arq.length);
						
						out.write(parametroArquivoBaixarRota.getBytes());
						out.write(arq);
							out.flush();
						
						FileInputStream inputStream = new FileInputStream(temporario);
							int INPUT_BUFFER_SIZE = 1024;
						byte[] temp = new byte[INPUT_BUFFER_SIZE];
						int numBytesRead = 0;
							ByteArrayOutputStream arquivo = new ByteArrayOutputStream();
							while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
							arquivo.write(temp, 0, numBytesRead);
						}
						
						inputStream.close();
						inputStream = null;
							arquivo.close();
						
						httpServletResponse.setContentType(ConstantesSistema.CONTENT_TYPE_PDF);
						httpServletResponse.addHeader("Content-Disposition", "attachment;filename=\""+ MimeUtility.encodeWord(arquivoTexto.getDescricaoArquivoTexto(), "utf-8", "Q")+"\"");
							ServletOutputStream servletOutputStream;
						
						servletOutputStream = httpServletResponse.getOutputStream();
						servletOutputStream.write(arquivo.toByteArray());
						servletOutputStream.flush();
						servletOutputStream.close();
						
				} catch (IOException e) {
					reportarErros(httpServletRequest, "erro.sistema");
				}
				
			}
			
		}
		return retorno;
	}
}
