package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.AtualizacaoCadastralArquivoTexto;
import gcom.cadastro.FiltroAtualizacaoCadastralArquivoTexto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC 1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 26/11/2012
 *
 */
public class TransmitirAtualizacaoCadastralArquivoTextoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		String idArquivo = httpServletRequest.getParameter("idArquivo");
		
		String idSituacaoArquivo = httpServletRequest.getParameter("idSituacaoArquivo");
		
		Fachada fachada = Fachada.getInstancia();
		
		if(idArquivo != null && !idArquivo.equals("")){
			FiltroAtualizacaoCadastralArquivoTexto filtro = new FiltroAtualizacaoCadastralArquivoTexto();
			filtro.adicionarParametro(new ParametroSimples(FiltroAtualizacaoCadastralArquivoTexto.ID, idArquivo));
			
			Collection<?> colArquivo = fachada.pesquisar(filtro, AtualizacaoCadastralArquivoTexto.class.getName());
			if(!Util.isVazioOrNulo(colArquivo)){
				AtualizacaoCadastralArquivoTexto arquivoTexto = (AtualizacaoCadastralArquivoTexto) Util.retonarObjetoDeColecao(colArquivo);
				
				File temporario;
				
				if(arquivoTexto.getArquivoTexto() != null && arquivoTexto.getArquivoTexto().length > 0){
					try {
						temporario = File.createTempFile("temporario", ".txt");

						FileOutputStream out = new FileOutputStream(temporario);
						
						if (idSituacaoArquivo != null && !idSituacaoArquivo.equals("")) {
							Integer idSituacao = Integer.parseInt(idSituacaoArquivo);
							
							if (idSituacao.equals(SituacaoTransmissaoLeitura.LIBERADO)) {
								
								byte[] arq = (byte[]) arquivoTexto.getArquivoTexto();
								
								// Parametro que identifica que o tipo de arquivo da
								// rota
								// está sendo enviado
								String parametroArquivoBaixarRota = "";
								
								// 1 do tipo de resposta ok + parametro Arquivo baixar
								// rota
								// + tamanho do arquivo rota
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
								
								httpServletResponse.setContentType("text/plain");
								httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + arquivoTexto.getDescricaoArquivo());

								ServletOutputStream servletOutputStream;
								
								servletOutputStream = httpServletResponse.getOutputStream();
								servletOutputStream.write(arquivo.toByteArray());
								servletOutputStream.flush();
								servletOutputStream.close();
								
								//Atualizar a situacao do arquivo para EM_CAMPO
								SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.EM_CAMPO);
								arquivoTexto.setSituacaoTransmissaoLeitura(situacao);								
								this.getFachada().atualizar(arquivoTexto);
								
							}else{
								throw new ActionServletException("atencao.arquivo_ja_baixado");
							}
						}
					
					} catch (IOException e) {
						reportarErros(httpServletRequest, "erro.sistema");
					}
					
				}else{
					throw new ActionServletException("atencao.arquivo_ja_baixado");
				}
			}else{
				throw new ActionServletException("atencao.arquivo_ja_baixado");
			}
		}
		
		return retorno;
	}
}
