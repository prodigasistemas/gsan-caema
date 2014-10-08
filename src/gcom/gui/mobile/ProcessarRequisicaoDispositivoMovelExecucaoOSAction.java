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
* Davi Menezes de Oliveira
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

package gcom.gui.mobile;

import gcom.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gcom.cadastro.SistemaAndroid;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.mobile.ArquivoTextoOSCobranca;
import gcom.mobile.FiltroArquivoTextoOSCobranca;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
 *
 * @author Bruno Barros
 * @date 17/06/2013
 *
 */

public class ProcessarRequisicaoDispositivoMovelExecucaoOSAction extends GcomAction {
	
	//Constantes de Classe
	//Tipos de Resposta
	private static final char RESPOSTA_ERRO = '#';
	private static final byte RESPOSTA_OK = '*';
	
	//Tipos de Requisição
	private static final short BAIXAR_ARQUIVO = 2;
	private static final short ATUALIZAR_MOVIMENTO = 3;
	private static final short RECEBER_FOTO = 4;
	private static final short FINALIZAR_ROTEIRO = 5;
	private static final short ENCERRAR_ARQUIVO_RETORNO = 7;
	private static final short SINAL_ROTA_INICIALIZADA = 10;
	private static final int BAIXAR_APK = 11;
	private static final int VERIFICAR_VERSAO = 12;
	
	
	/**
	 * Método Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException 
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		
		InputStream in = null;
		OutputStream out = null;
		DataInputStream din = null;
		
		//Verificamos qual foi a ação solicitada pelo dispositivo móvel
		try{
			in = request.getInputStream();
			out = response.getOutputStream();
			din = new DataInputStream(in);
			
			//O primeiro byte da requisição possue sempre o tipo da requisição
			// feita pelo dispositivo móvel
			int acaoSolicitada = 0;
			
			if(din != null){
				acaoSolicitada = din.readByte();
			}
				
			switch(acaoSolicitada){
				//No caso de download de arquivo, é sempre passado o imei do celular
				case BAIXAR_ARQUIVO:
					//Baixar Arquivo
					long imei = din.readLong();
					String mac = din.readUTF();
					this.baixarArquivo(imei, mac, response, out);
					break;
				case ATUALIZAR_MOVIMENTO:
					//Atualizar Movimento
					this.atualizarMovimentacao(din,response,out);
					break;
				case FINALIZAR_ROTEIRO:
					//Finalizar Leitura
					this.atualizarMovimentacao(din,response,out);
					break;
				case RECEBER_FOTO:
					//Receber Foto
					this.receberFoto(din, response, out);
					break;
				case SINAL_ROTA_INICIALIZADA:
					//Atualizar Situacao do Arquivo
					long idArquivoTextoOSCobrancaSmartphone = din.readLong();
					this.atualizarSituacaoArquivo(response, out,idArquivoTextoOSCobrancaSmartphone);
					break;
				case BAIXAR_APK:
					// Finalizar Movimento
					out = response.getOutputStream();
					this.baixarNovaVersaoAcompanhamento(din, response, out);
					break;	
				case VERIFICAR_VERSAO:
					this.verificarVersao(din,response,out);
					break;					
				default:
					break;
			}
			
		//Caso aconteça qualquer problema, retornamos para o
		//dispositivo movel o caracter de erro no processamento da requisição
		} catch(Exception e){
			response.setContentLength(1);
			try{
				out.write(RESPOSTA_ERRO);
				out.flush();
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. RETORNOU " + RESPOSTA_ERRO + " PARA O CELULAR /n/n/n");
				e.printStackTrace();
			} catch(IOException ioe){
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. NÃO ENVIOU RESPOSTA PARA O CELULAR /n/n/n");
				ioe.printStackTrace();
				throw new ActionServletException(ioe.getMessage());
			}
		} finally{
			if(in != null){
				try{
					in.close();
				} catch (IOException e){
					e.printStackTrace();
					throw new ActionServletException(e.getMessage());
				}
			}
			if(out != null){
				try{
					out.close();
				} catch(IOException e){
					e.printStackTrace();
					throw new ActionServletException(e.getMessage());
				}
			}
		}
		return null;
	}
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 18/06/2013
	 * 
	 * 
	 * @param BufferedReader
	 * @throws ControladorException
	 */
	private void atualizarMovimentacao(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		InputStreamReader reader = new InputStreamReader(din, "UTF-8");
		BufferedReader buffer = new BufferedReader(reader);
		
		try{
			System.out.println("INICIO: Atualizando movimentacao da Execução de OS");
			
			getFachada().atualizarMovimentacaoExecucaoOS(buffer);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Atualizando movimentacao da Execução de OS ");
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao atualizar o arquivo de Execução de OS");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * 
	 * Atualiza o arquivo texto para situacao "EM CAMPO" 
	 *
	 * @author Bruno Barros
	 * @date 27/10/2011
	 *
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void atualizarSituacaoArquivo(HttpServletResponse response, 
		OutputStream out, long idArquivoTextoOSCobrancaSmartphone) throws IOException {
		try {
			//Atualiza o arquivo em campo
			System.out.println("INICIO: Atualizando a situacao do arquivo ID: "+idArquivoTextoOSCobrancaSmartphone);
			
			FiltroArquivoTextoOSCobranca filtro = new FiltroArquivoTextoOSCobranca();
			filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoOSCobranca.ID, idArquivoTextoOSCobrancaSmartphone));
			
			Collection<ArquivoTextoVisitaCampo> colArquivoTexto = 
					this.getFachada().pesquisar(filtro, ArquivoTextoOSCobranca.class.getName());
			
			ArquivoTextoOSCobranca arquivoTextoOSCobrancaSmartphone = (ArquivoTextoOSCobranca) Util.retonarObjetoDeColecao(colArquivoTexto);
			
			if(arquivoTextoOSCobrancaSmartphone != null){
				SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.EM_CAMPO);
				arquivoTextoOSCobrancaSmartphone.setSituacao(situacao);
				arquivoTextoOSCobrancaSmartphone.setDataEnvio(new Date());
				
				this.getFachada().atualizar(arquivoTextoOSCobrancaSmartphone);
				
			}
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Atualizando a situacao do arquivo ID: "+idArquivoTextoOSCobrancaSmartphone);
			
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao atualizar situacao do arquivo em campo ID:"+idArquivoTextoOSCobrancaSmartphone);
			response.setContentLength(1);
			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param imei - Imei do aparalho que ira receber o arquivo
	 * 
	 * @return array de bytes com o arquivo
	 *         
	 * @throws ControladorException
	 */
	private void baixarArquivo(long imei, String mac, HttpServletResponse response, OutputStream out) throws IOException  {
		try{
			//Arquivo retornado
			byte[] arquivo = getFachada().baixarArquivoTextoExecucaoOS(imei,mac); 
			
			if( arquivo != null ){
				System.out.println("INICIO: Baixando arquivo texto Execução OS IMEI: " + imei );
				
				//Parametro que identifica que o tipo de arquivo da rota está sendo enviado
				String parametroArquivoBaixarRota = "";
				
				//1 do tipo de resposta ok + parametro Arquivo baixar rota + tamanho do arquivo rota
				response.setContentLength(1 + parametroArquivoBaixarRota.getBytes().length + arquivo.length);
				
				out.write(RESPOSTA_OK);
				out.write(parametroArquivoBaixarRota.getBytes());
				out.write(arquivo);
				
				out.flush();
				
				System.out.println("FIM: Baixando arquivo texto Execução OS IMEI: " + imei);
			} else{
				System.out.println("ERROR: Nao existe arquivo texto Execução OS IMEI: " + imei);
				response.setContentLength(1);
				
				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: Ao baixar arquivo texto de fiscalizacacao de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param imei - Imei do aparalho que ira receber o arquivo
	 * 
	 * @return array de bytes com o arquivo
	 *         
	 * @throws ControladorException
	 */
	private void receberFoto(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		try {
			System.out.println("INICIO: Recebendo foto fiscalizacao anormalidade ");			
			
			//Lemos o número da OS para qual essa foto pertence
			int numeroOS = din.readInt();
			
			//Verificamos qual é o tipo de foto
			int tipoFoto = din.readInt();
			
			int idArquivo = din.readInt();			
			
			long fileSize = din.readLong();
			 
			byte[] bytesFoto = new byte[(int)fileSize];
			int read = 0;
			int numRead = 0;
			while(read < bytesFoto.length && (numRead=din.read(bytesFoto, read, bytesFoto.length-read))>= 0){
				read = read + numRead;
			}
			
			//inserimos na base
			System.out.println("INICIO: Recebendo foto de OS de Cobranca Smartphone :"+numeroOS+" TIPO FOTO:"+tipoFoto);
			
			getFachada().inserirFotoOrdemServicoCobrancaSmartphone(idArquivo, numeroOS, tipoFoto, bytesFoto);
			
			System.out.println("FIM: Recebendo foto de OS de Cobranca Smartphone:"+numeroOS+" TIPO FOTO:"+tipoFoto);
			
			response.setContentLength(1+read+bytesFoto.length);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Recebendo foto fiscalizacao anormalidade ");
			
		} catch (Exception e) {
			System.out.println("ERROR: Ao receber a foto de fiscalização de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	private void verificarVersao(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException{
		Fachada fachada = Fachada.getInstancia();
        
        try{
        	Object[] obj = fachada.baixarNovaVersaoApk( SistemaAndroid.GSANEOS );
        	
        	if(obj != null){
        		
        		String versao = (String)obj[1].toString();
        		
        		System.out.println("Inicio : Baixando valor da NOVA VERSÃO,  Android AS");
              //  response.setContentLength( versao.length());

                out.write(RESPOSTA_OK);
               
        		out.write(versao.getBytes());
                out.flush();

                System.out.println("Fim: Baixando valor NOVA VERSÃO, ARQUIVO APK Android AS");  
        	}else{
        		System.out.println("Erro ao Baixar valor da versão Android AS");
                response.setContentLength( 1 );

                out.write(RESPOSTA_ERRO);
                out.flush();
        	}
        } catch( Exception e ){

            System.out.println("Erro ao Baixar valor da VERSÃO Android AS");
            response.setContentLength( 1 );
            out.write(RESPOSTA_ERRO);
            out.flush();

         }
		
	}

	private void baixarNovaVersaoAcompanhamento(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException{
		Fachada fachada = Fachada.getInstancia();
        
        try{
        	Object[] obj = fachada.baixarNovaVersaoApk( SistemaAndroid.GSANEOS );
        	if(obj != null){
        		
        		byte[] arq = (byte[]) obj[0];
            	
                System.out.println("Inicio : Baixando NOVA VERSÃO, ARQUIVO APK Android AS");
                response.setContentLength( arq.length );

                out.write(RESPOSTA_OK);
                //out.write( parametroTipoArquivo.getBytes() );                
                
                out.write(arq);
                out.flush();

                System.out.println("Fim: Baixando NOVA VERSÃO, ARQUIVO APK Android AS");         
            }else{
                System.out.println("Erro ao Baixar arquivo Android AS");
                response.setContentLength( 1 );

                out.write(RESPOSTA_ERRO);
                out.flush();
            }
            
        } catch( Exception e ){

            System.out.println("Erro ao Baixar arquivo Mobile");
            response.setContentLength( 1 );
            out.write(RESPOSTA_ERRO);
            out.flush();

         }
	}	
}
