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
package gcom.batch;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.cliente.RepositorioClienteImovelHBM;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.localidade.IRepositorioSetorComercial;
import gcom.cadastro.localidade.RepositorioSetorComercialHBM;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.ControladorAcessoLocal;
import gcom.seguranca.acesso.ControladorAcessoLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;

import gcom.util.ErroRepositorioException;
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;

import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;


import java.util.Date;


import javax.ejb.CreateException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;


public class ExecutarBatch extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		// Seta o mapeamento de retorno
		ActionForward retorno = null;



//		try {
//			
//			Usuario usuario = (Usuario)this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
//			/*Collection<Integer> colecaoComandos = this.getControladorAtendimentoPublico()
//					.obterComandoOSConexaoEsgotoExecucao(new Integer(1));*/
//			
//			
//			this.getControladorAtendimentoPublico()
//			.gerarRelatorioImoveisCartasNaoImpressas(new Integer(16),usuario,null);
//								
//			
//			/* Iterator<Integer> iterator = colecaoComandos.iterator();
//		        while (iterator.hasNext()) {
//		        	
//		        	 Integer idComando = iterator.next();
//
//						this.getControladorAtendimentoPublico()
//								.gerarOrdensServicoFactivelFaturavel(
//										null,
//										idComando,
//										new Integer(1),
//										usuario
//						);*/	        	 
//			
//            
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
		

		

		File file = new File("/home/ipad/ipad/eclipse/eclipse_android/android.png"); 
        String filename = file.getName(); 
        String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length()); 
        
        DataOutputStream dos = null; 
 
 
        int bytesRead, bytesAvailable, bufferSize; 
        byte[] buffer; 
        int maxBufferSize = 1 * 1024 * 1024; 
        long fileSize = file.length(); 
        
 
        // Send request 
        try { 
            // Configure connection 
            URL url = new URL("http://192.168.64.80:8080/gsan/processarRequisicaoIntegracaoProgisAction.do"); 
            HttpURLConnection conn = ((HttpURLConnection)url.openConnection()); 
 
            conn.setDoOutput(true); 
            conn.setUseCaches(false); 
            conn.setRequestMethod("POST"); 
            conn.setRequestProperty("Connection", "Keep-Alive"); 
            conn.setRequestProperty("Content-Type", "multipart/form-data"); 
 
            dos = new DataOutputStream(conn.getOutputStream()); 
            
            //Tipo de Requisição
            dos.writeUTF("1");
            //Celular 
            dos.writeUTF("8199288080"); 
            //idRa 
            dos.writeUTF("null"); 
            //idTipoSolicitacao 
            dos.writeUTF("700"); //700
            //idTipoSolicitacaoEspecificacao 
            dos.writeUTF("709"); //709
            //idEndereco 1235 
            dos.writeUTF("6");              
            //Nome 
            dos.writeUTF("tuca"); 
            //PontoReferencia 
            dos.writeUTF("ponto");     
            //CoordenadaX 
            dos.writeUTF("5858.2"); 
            //CoordenadaY 
            dos.writeUTF("148488.2"); 
            //Email 
            dos.writeUTF("davi.menezes@ipad.com.br"); 
            //Extencao 
            dos.writeUTF(extension); 
            //Tamanho foto 
            dos.writeUTF(new Long(fileSize).toString() ); 
 
            // A foto so sera considerada caso tenha extensão e tamanho 
            
            // Read file and create buffer 
            FileInputStream fileInputStream = new FileInputStream(file); 
            bytesAvailable = fileInputStream.available(); 
            bufferSize = Math.min(bytesAvailable, maxBufferSize); 
            buffer = new byte[bufferSize]; 
            
            bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
            while (bytesRead > 0) { 
                 //Write buffer to socket 
                dos.write(buffer, 0, bufferSize); 
 
                // Update progress dialog 
                bytesAvailable = fileInputStream.available(); 
                bufferSize = Math.min(bytesAvailable, maxBufferSize); 
                bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
            }
            
            //Código do Atendente
            dos.writeUTF("60160");
            //Imovel mais proximo
            dos.writeUTF("27462928");
            //CPF
            dos.writeUTF("34493138602");
            //Observacao
            dos.writeUTF("Teste do PROGIS");
            
            dos.flush(); 
            
            // Get the response 
            StringBuffer answer = new StringBuffer(); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
            String line; 
            while ((line = reader.readLine()) != null) { 
                answer.append(line); 
            } 
            
            String resposta = "ProcessarRequisicaoIntegracaoProgis retornou ERRO"; 
            if(answer.toString().contains("*")){ 
                resposta = "ProcessarRequisicaoIntegracaoProgis retornou OK"; 
            } 
            
            //PrintWriter print = response.getWriter(); 
            //print.println(resposta); 
             
            dos.close(); 
            reader.close(); 
            //fileInputStream.close();   
              
             
            System.out.println(resposta);
            System.out.println(answer.toString());
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
		
		return null;

	}


	
    /**
     * Retorna o valor de controladorLocalidade
     * 
     * @return O valor de controladorLocalidade
     */
	private ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {
		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;

		// pega a instância do ServiceLocator.


		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ACESSO_SEJB);


			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	

	

		
}