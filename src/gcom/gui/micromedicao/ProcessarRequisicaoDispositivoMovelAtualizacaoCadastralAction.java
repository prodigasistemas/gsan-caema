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
package gcom.gui.micromedicao;

import gcom.cadastro.AtualizacaoCadastralArquivoTexto;
import gcom.cadastro.FiltroAtualizacaoCadastralArquivoTexto;
import gcom.cadastro.SistemaAndroid;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
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

public class ProcessarRequisicaoDispositivoMovelAtualizacaoCadastralAction extends GcomAction {	
	
	/**
	 * Constantes de Classe.
	 */
	private static final char RESPOSTA_ERRO = '#';	
	private static final byte RESPOSTA_OK = '*';

    private static final int BAIXAR_ARQUIVO_TEXTO_LIBERADO = 1;
    private static final int ATUALIZAR_MOVIMENTO_DADOS_IMOVEL = 2;
    private static final int FINALIZAR_ROTEIRO = 3;
    private static final int RECEBER_FOTO = 4;
    private static final int ATUALIZAR_SITUACAO_ARQUIVO = 5;
    private static final int BAIXAR_NOVA_VERSAO_APK = 11;
    private static final int VERIFICAR_VERSAO_ANDROID = 12;
    private static final int PING = 15;    
    private static final int CONSULTAR_SITUACAO_ARQUIVO = 16;    
    
	/**
	 * M�todo Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			System.out.println("ERROR:REQUISICAO ATUALIZACAO CADASTRAL: " + e.getMessage());
		}
		
		try {
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);			
						
			int pacote = din.readByte();		
				
			System.out.println("INICIO:SOLICITACAO ATUALIZACAO CADASTRAL PACOTE: " + pacote);
			switch(pacote){
                case BAIXAR_ARQUIVO_TEXTO_LIBERADO:
					//Baixar Arquivo
					this.baixarArquivoTextoLiberado(din,response,out);
					break;
                case ATUALIZAR_SITUACAO_ARQUIVO:
					//Atualizar Situacao do Arquivo
					long idAtualizacaoCadastralArquivoTexto = din.readLong();
					Integer situacaoArquivo = din.readInt();
					this.atualizarSituacaoArquivo(response, out,idAtualizacaoCadastralArquivoTexto, situacaoArquivo);
					break;
                case PING:
    				out.write( RESPOSTA_OK );
    				out.flush();
    				break;
                case BAIXAR_NOVA_VERSAO_APK:
					this.baixarNovaVersaoAtualizacaoCadastralAndroid(din, response, out);
					break;	
                case VERIFICAR_VERSAO_ANDROID:
    				this.verificarVersaoAtualizacaoCadastralAndroid(din,response,out);
    				break;
                case RECEBER_FOTO:
    				this.receberFoto(din, response, out);
    				break;
                case FINALIZAR_ROTEIRO:
					//Finalizar Leitura
					this.finalizarRoteiro(din,response,out);
					break;
                case ATUALIZAR_MOVIMENTO_DADOS_IMOVEL:
					//Recebe a transmiss�o do im�vel um-a-um
					this.atualizarMovimentoImovelAtualizacaoCadastral(din,response,out);
					break;
                case CONSULTAR_SITUACAO_ARQUIVO:
					long idComando = din.readLong();
					this.consultarArquivoFinalizado(response, out,idComando);
                default:
					break;
			}
				
			System.out.println("FIM:SOLICITACAO ATUALIZACAO CADASTRAL PACOTE: " + pacote);
		} catch (Exception e) {
			
			System.out.println("ERROR:REQUISICAO ATUALIZACAO CADASTRAL: " + e.getMessage());
			e.printStackTrace();
			response.setContentLength( 1 );			
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
			} catch (IOException e1) {
				System.out.println("ERROR:REQUISICAO ATUALIZACAO CADASTRAL: " + e.getMessage());
			}			
		}finally{
			if (in != null) {
				try {
					in.close();					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(out != null){
				try {					
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
		
	}
	
	/**
	 * [UC1393] Processar Requisi��es do Dispositivo M�vel Atualiza��o Cadastral.
	 * 
	 * Recebe o dados de apenas um im�vel.
	 * 
	 * @author Rafael Pinto
	 * @date 20/03/2013
	 *  
	 * @param data
	 * @param response
	 * @throws IOException
	 */	
	private void atualizarMovimentoImovelAtualizacaoCadastral(DataInputStream din, HttpServletResponse response, OutputStream out) 
		throws IOException {
		
		System.out.println("INICIO: Recebendo dados do im�vel para atualiza��o ");
		
		InputStreamReader reader = new InputStreamReader(din);
		BufferedReader buffer = new BufferedReader(reader);
		
		try{
			
			getFachada().atualizarMovimentoImovelAtualizacaoCadastral(buffer);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Recebendo dados do im�vel para atualiza��o ");
			
		}catch(Exception e){
			System.out.println("ERROR: Recebendo dados do im�vel para atualiza��o");
			e.printStackTrace();
			response.setContentLength(1);
			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}	
	
	/**
	 * [UC1393] Processar Requisi��es do Dispositivo M�vel Atualiza��o Cadastral.
	 * 
	 * Baixa o arquivo texto na situa��o liberado.
	 * 
	 * @author Anderson Cabral
	 * @date 27/11/2012
	 *  
	 * @param data
	 * @param response
	 * @throws IOException
	 */
	public void baixarArquivoTextoLiberado(DataInputStream data, HttpServletResponse response, OutputStream out ) throws IOException{
		
		System.out.println("INICIO:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL:");
		
		Fachada fachada = Fachada.getInstancia();
		String login = data.readUTF();
		String senha = data.readUTF();
		
		System.out.println("LOGIN:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL: "+ login);
		
		try{
			Object[] retorno =fachada.baixarArquivoTextoLiberadoParaLeituristaAtualizacaoCadastral(login, senha);
			byte[] arq = (byte[]) retorno[0];
			
			if(arq != null){
				System.out.println("INICIO:TRANSMITIR ARQUIVO ATUALIZACAO CADASTRAL:");
				
				response.setContentLength(1 + arq.length);
				out.write(RESPOSTA_OK);

				out.write( arq );
				
				out.flush();

				System.out.println("FIM:TRANSMITIR ARQUIVO ATUALIZACAO CADASTRAL:");
			}else{
				System.out.println("ERROR:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL SEM DADOS:");
				
				response.setContentLength( 1 );
				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch( Exception e ){

			System.out.println("ERROR:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL:");
			e.printStackTrace();
			
			
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}
	
	/**
	 * 
	 * Atualiza o arquivo texto para situacao "EM CAMPO" 
	 *
	 * @author Anderson Cabral
	 * @date 12/12/2012
	 *
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void atualizarSituacaoArquivo(HttpServletResponse response, 
		OutputStream out, long idAtualizacaoCadastralArquivoTexto, Integer situacaoArquivo) throws IOException {
		try {
			//Atualiza o arquivo em campo
			System.out.println("INICIO: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL ID: "+idAtualizacaoCadastralArquivoTexto);
			System.out.println("INICIO: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL SITUACAO: "+situacaoArquivo);
			
			FiltroAtualizacaoCadastralArquivoTexto filtro = new FiltroAtualizacaoCadastralArquivoTexto();
			filtro.adicionarParametro(new ParametroSimples(FiltroAtualizacaoCadastralArquivoTexto.PARAMETRO_TABELA_ATUALIZACAO_CADASTRO, idAtualizacaoCadastralArquivoTexto));
			
			Collection<AtualizacaoCadastralArquivoTexto> colArquivoTexto = 
					this.getFachada().pesquisar(filtro, AtualizacaoCadastralArquivoTexto.class.getName());
			
			AtualizacaoCadastralArquivoTexto atualizacaoCadastralArquivoTexto = (AtualizacaoCadastralArquivoTexto) Util.retonarObjetoDeColecao(colArquivoTexto);
			
			if(atualizacaoCadastralArquivoTexto != null){
				SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(situacaoArquivo);
				atualizacaoCadastralArquivoTexto.setSituacaoTransmissaoLeitura(situacao);
				
				/*
				 * @author Jonathan Marcos
				 * @date 24/02/2014
				 * RM10091
				 * [OBSERVACOES]
				 * 		- [SB0014] ATUALIZAR A SITUACAO DO ARQUIVO FINALIZADO
				 * 		  SETA A DATA CORRENTE DE FINALIZACAO DO ARQUIVO
				 */
				if(situacaoArquivo.compareTo(SituacaoTransmissaoLeitura.TRANSMITIDO)==0){
					atualizacaoCadastralArquivoTexto.setDataFinalizacaoArquivo(new Date());
				}
				
				/*
				 * @author Jonathan Marcos
				 * @date 08/04/2014
				 * RM10592
				 * [OBSERVACOES]
				 * 		- Inconsistencia do que foi solicitado no RM 9795 
				 * 		  quando a proposta entrou em producao (RM 10399 COMPESA)
				 * 		  (O PROBLEMA EH QUE A DATA DA ULTIMA ALTERACAO NAO ESTAVA
				 * 			SENDO SETADA QUANDO MUDAVA A SITUACAO DO ARQUIVO
				 * 			PARA FINALIZADO)
				 */
				atualizacaoCadastralArquivoTexto.setUltimaAlteracao(new Date());
				
				this.getFachada().atualizar(atualizacaoCadastralArquivoTexto);
				
				if ( situacaoArquivo.equals(SituacaoTransmissaoLeitura.TRANSMITIDO) ) {
					this.getFachada().atualizarSituacaoTransmissaoImovel(Integer.valueOf(String.valueOf(idAtualizacaoCadastralArquivoTexto)));
				}
				
			}
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL ID: "+idAtualizacaoCadastralArquivoTexto);
			
		} catch(Exception e){
			System.out.println("ERROR: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL ID: "+idAtualizacaoCadastralArquivoTexto);
			e.printStackTrace();

			response.setContentLength(1);
			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	 /**
	  * 
	  * @param din
	  * @param response
	  * @param out
	  * @throws IOException
	  */
    private void baixarNovaVersaoAtualizacaoCadastralAndroid(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException{
		Fachada fachada = Fachada.getInstancia();
		System.out.println("INICIO:SOLICITAR NOVA VERSAO ATUALIZACAO CADASTRAL:");
        try{
        	Object[] obj = fachada.baixarNovaVersaoApk( SistemaAndroid.ATUALIZACAO_CADASTRAL );
        	if(obj != null){
        		
        		byte[] arq = (byte[]) obj[0];

                System.out.println("INICIO: TRANSMITINDO NOVA VERSAO APK ATUALIZACAO CADASTRAL:");
                
                response.setContentLength( arq.length );

                out.write(RESPOSTA_OK);
                
                out.write(arq);
                out.flush();

                System.out.println("FIM: TRANSMITINDO NOVA VERSAO APK ATUALIZACAO CADASTRAL:");
            }else{
                System.out.println("ERROR:SOLICITAR NOVA VERSAO ATUALIZACAO CADASTRAL SEM VERS�O:");
                response.setContentLength( 1 );

                out.write(RESPOSTA_ERRO);
                out.flush();
            }
            
        } catch( Exception e ){

        	System.out.println("ERROR:SOLICITAR NOVA VERSAO ATUALIZACAO CADASTRAL:");
        	e.printStackTrace();
        	
            response.setContentLength( 1 );
            out.write(RESPOSTA_ERRO);
            out.flush();

         }
		
	}
    
	private void verificarVersaoAtualizacaoCadastralAndroid(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException{
		
		Fachada fachada = Fachada.getInstancia();
		System.out.println("INICIO: VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");
        
		try{
        
			Object[] obj = fachada.baixarNovaVersaoApk( SistemaAndroid.ATUALIZACAO_CADASTRAL );
        	
        	if(obj != null){
        		
        		String versao = (String)obj[1].toString();
        		

        		System.out.println("INICIO: TRANSMITINDO VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");

                out.write(RESPOSTA_OK);
               
        		out.write(versao.getBytes());
                out.flush();

                System.out.println("FIM: TRANSMITINDO VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");
        	}else{
        		System.out.println("ERROR: VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL SEM DADOS:");
                response.setContentLength( 1 );

                out.write(RESPOSTA_ERRO);
                out.flush();
        	}
        } catch( Exception e ){

            System.out.println("ERROR: VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");
            e.printStackTrace();

            response.setContentLength( 1 );
            out.write(RESPOSTA_ERRO);
            out.flush();

         }
        
	}
	
	/**
	 * 
	 * @param din
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	private void receberFoto(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		try {
			System.out.println("INICIO: Recebendo foto atualizacao cadastral");
			//Lemos o n�mero da OS para qual essa foto pertence
			String codigo = din.readUTF();
			
			//Verificamos qual � o tipo de foto
			int tipoFoto = din.readInt();
			
			long fileSize = din.readLong();
			
			byte[] bytesFoto = new byte[(int)fileSize];
			int read = 0;
			int numRead = 0;
			while(read < bytesFoto.length && (numRead=din.read(bytesFoto, read, bytesFoto.length-read))>= 0){
				read = read + numRead;
			}
			
			//inserimos na base
			System.out.println("INICIO: Recebendo foto atualizacao cadastral:"+codigo+" TIPO FOTO:"+tipoFoto);
			
			getFachada().inserirFotoAtualizacaoCadastral(codigo, tipoFoto, bytesFoto);
			
			System.out.println("FIM: Recebendo foto atualizacao cadastral:"+codigo+" TIPO FOTO:"+tipoFoto);
			
			response.setContentLength(1+read+bytesFoto.length);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Recebendo foto atualizacao cadastral");
			
		} catch (Exception e) {
			System.out.println("ERROR: Ao receber a foto de atualizacao cadastral");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	private void finalizarRoteiro(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		InputStreamReader reader = new InputStreamReader(din);
		BufferedReader buffer = new BufferedReader(reader);
		
		try{
			System.out.println("INICIO: Finalizando roteiro atualizacao cadastral ");
			getFachada().atualizarMovimentoImovelAtualizacaoCadastral(buffer);
			
			out.write(RESPOSTA_OK);
			out.flush();
			System.out.println("FIM: Finalizando roteiro atualizacao cadastral ");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao finalizar roteiro  atualizacao cadastral");
			response.setContentLength(1);
			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	
	/**
	 * 
	 * Atualiza o arquivo texto para situacao "EM CAMPO" 
	 *
	 * @author Anderson Cabral
	 * @date 12/12/2012
	 *
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void consultarArquivoFinalizado(HttpServletResponse response, OutputStream out, long idAtualizacaoCadastralArquivoTexto) throws IOException {
		try {
			//Atualiza o arquivo em campo
			System.out.println("INICIO: Consulta a situacao do arquivo ID: "+idAtualizacaoCadastralArquivoTexto);
			
			FiltroAtualizacaoCadastralArquivoTexto filtro = new FiltroAtualizacaoCadastralArquivoTexto();
			filtro.adicionarParametro(new ParametroSimples(FiltroAtualizacaoCadastralArquivoTexto.ID_PARAMETRO_TABELA_ATUALIZACAO_CADASTRO, idAtualizacaoCadastralArquivoTexto));
//			filtro.adicionarParametro(new ParametroSimples(FiltroAtualizacaoCadastralArquivoTexto.ID_SITUACAO_TRANSMISSAO_LEITURA, SituacaoTransmissaoLeitura.TRANSMITIDO));
			
			Collection<AtualizacaoCadastralArquivoTexto> colArquivoTexto = this.getFachada().pesquisar(filtro, AtualizacaoCadastralArquivoTexto.class.getName());
			
			AtualizacaoCadastralArquivoTexto atualizacaoCadastralArquivoTexto = (AtualizacaoCadastralArquivoTexto) Util.retonarObjetoDeColecao(colArquivoTexto);
			
			if(atualizacaoCadastralArquivoTexto != null && 
					atualizacaoCadastralArquivoTexto.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)){
				
				System.out.println("FIM: Consulta a situacao do arquivo ID: "+idAtualizacaoCadastralArquivoTexto);
				out.write(RESPOSTA_OK);
				out.flush();
			} else {
				System.out.println("ERROR: Ao Consulta situacao do arquivo em campo ID:"+idAtualizacaoCadastralArquivoTexto);
				response.setContentLength(1);
				
				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao consultar situacao do arquivo em campo ID:"+idAtualizacaoCadastralArquivoTexto);
			response.setContentLength(1);
			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
}