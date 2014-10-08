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
package gcom.gui.util.log;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @date 12/01/2012
 */
public class ExibirLogTelaFinalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirLogTelaFinal");
		ExibirLogActionForm form = (ExibirLogActionForm) actionForm;
		StringBuffer textoErro = new StringBuffer("");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		int TAMANHO_PAGINA = 1500;
		int NUMERO_TOTAL_LINHAS_ARQUIVO = 0;
		try{
			
			
			String nomeArquivo = form.getArquivo();
//			File file = new File("C:\\IPAD\\jboss\\server\\default\\log\\"+nomeArquivo);
			File file = new File("/usr/local/jboss/server/default/log/"+nomeArquivo);
			
			String numeroPagina = httpServletRequest.getParameter("numeroPagina");
			String ini = httpServletRequest.getParameter("inicio");
			int inicio = 0;
			
			String palavra = httpServletRequest.getParameter("palavra");
			if ( palavra != null && !palavra.equals("") ) {
				sessao.removeAttribute("textoPesquisado");
			}
			
			String texto = httpServletRequest.getParameter("texto");
			if ( texto != null && !texto.equals("") ) {
				sessao.removeAttribute("palavraPesquisada");
			}
			String menu = httpServletRequest.getParameter("menu");
			if (menu != null && menu.equals("sim") ) {
				sessao.removeAttribute("palavraPesquisada");
				sessao.removeAttribute("textoPesquisado");
			}
			
			FileReader fw = new FileReader(file);
			BufferedReader bis = new BufferedReader (fw);
			try{
				//Caso pesquise por texto, verifica o numero de linhas que contem a pesquisa.
				boolean aux = false;
				if ( (texto != null && !texto.equals("")) || sessao.getAttribute("textoPesquisado") != null ) {
					if ( texto == null || texto.equals("") ) {
						texto = (String) sessao.getAttribute("textoPesquisado");
						aux = true;
					} else {
						sessao.setAttribute("textoPesquisado", texto);
					}
					String line;
					while ((line = bis.readLine()) != null ){
						int posicao = line.toUpperCase().indexOf(texto.toUpperCase());
						if( posicao != -1 ){
							NUMERO_TOTAL_LINHAS_ARQUIVO++;	
						}
					}
					if ( aux ) {
						texto = null;
					}
				} else {
					//Calcula o tamanho de linhas do arquivo.
					while ( bis.readLine() != null ){
						NUMERO_TOTAL_LINHAS_ARQUIVO++;
					}
				}
			
				if (bis != null){
					bis.close();	
				}
				if (fw != null){
					fw.close();
				}
				
				fw = new FileReader(file);				
				bis = new BufferedReader (fw);
				
				if (ini != null && !ini.equals("")) {
					inicio = Integer.parseInt(ini);
				
				}else if(numeroPagina != null && !numeroPagina.equals("") ){
					try{
						Integer numPagina = Integer.parseInt(numeroPagina);
						if(numPagina < 0){
							throw new Exception();
						}
					}catch(Exception ex){
						throw new ActionServletException("atencao.numero_pagina_invalido");
					}
					
					inicio = Integer.parseInt(numeroPagina);
					inicio = inicio*TAMANHO_PAGINA;
				
				}
				
				//Controle de paginação
				int numeroPaginaAtual = 1;
				if ( form.getPaginaAtual() != null ) {
					numeroPaginaAtual = new Integer(form.getPaginaAtual());
				}
				if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("proximo") ) {
					numeroPaginaAtual ++;
				} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("anterior") ) {
					if ( numeroPaginaAtual != 1 ) {
						numeroPaginaAtual --;
					}
				} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("ultimo") ) {
					numeroPaginaAtual = Util.dividirArredondarResultadoBaixo(NUMERO_TOTAL_LINHAS_ARQUIVO, TAMANHO_PAGINA) ;
					numeroPaginaAtual++;
				} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("primeiro") ) {
					numeroPaginaAtual = 1;
				} else if(numeroPagina != null && !numeroPagina.equals("") ){
					
					numeroPaginaAtual = new Integer(numeroPagina);
					form.setPaginaAtual(""+numeroPaginaAtual);
					sessao.removeAttribute("palavraPesquisada");
					sessao.removeAttribute("textoPesquisado");
				}
		
				String line;
				int tam = 0;
				//Toda palavra que foi informada no filtro, sera exibida no log em destaque
				if( palavra != null && !palavra.equals("") || sessao.getAttribute("palavraPesquisada") != null ){	
					if ( palavra != null && !palavra.equals("") ) {
						sessao.setAttribute("palavraPesquisada", palavra);
						form.setPaginaAtual("1");
						numeroPaginaAtual = new Integer(form.getPaginaAtual());
					} else {
						palavra = (String) sessao.getAttribute("palavraPesquisada");
					}
					String linhaPalavra;
					int tamPalavra = 0;
					
					while ( (linhaPalavra = bis.readLine()) != null && tamPalavra <= (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual)) ){
						tamPalavra++;
						if ( tamPalavra > ( (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual) ) - TAMANHO_PAGINA) ) {
							int posicao = linhaPalavra.toUpperCase().indexOf(palavra.toUpperCase());
							if(posicao != -1){
								
								textoErro.append(linhaPalavra.substring(0,posicao));
								textoErro.append("<span style=\"background:#44ff77;\" >");
								textoErro.append(linhaPalavra.substring(posicao,posicao+palavra.length()));
								textoErro.append("</span>");
								textoErro.append(linhaPalavra.substring(posicao+palavra.length()));
								textoErro.append("<br>");
								tamPalavra++;
							
							} else {
								textoErro.append(linhaPalavra);
								textoErro.append("<br>");
								tamPalavra++;
							}
						}
					}
				}else if(texto != null && !texto.equals("") || sessao.getAttribute("textoPesquisado") != null ){				
					//Somente as linhas que contem o texto informado no filtro que serao exibidas.
					
					if ( texto != null && !texto.equals("") ) {
						sessao.setAttribute("textoPesquisado", texto);
						form.setPaginaAtual("1");
						numeroPaginaAtual = new Integer(form.getPaginaAtual());
					} else {
						texto = (String) sessao.getAttribute("textoPesquisado");
					}
					if ( form.getPaginaAtual() == null || form.getPaginaAtual().equals("") ) {
						form.setPaginaAtual("1");
						numeroPaginaAtual = new Integer(form.getPaginaAtual());						
					}
					String linhaTexto;
					int tamTexto = 0;
					int in = 0;
					
					while ( (linhaTexto = bis.readLine()) != null && tamTexto <= (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual)) ){
					
						int posicao = linhaTexto.toUpperCase().indexOf(texto.toUpperCase());
						if( posicao != -1 ){
							in++;
							if ( in > ( (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual) ) - TAMANHO_PAGINA) ) {
								
								textoErro.append(linhaTexto.substring(0,posicao));
								textoErro.append("<span style=\"background:#44ff77;\" >");
								textoErro.append(linhaTexto.substring(posicao,posicao+texto.length()));
								textoErro.append("</span>");
								textoErro.append(linhaTexto.substring(posicao+texto.length()));
								textoErro.append("<br>");
								
								tamTexto++;
							}
						}
					}
				} else {
					//VAI LER O ARQUIVO ATE A ULTIMA LINHA DA PAGINA SELECIONADA(ULTIMA, PRIMEIRA, ANTERIOR OU PROXIMA)
					while ( (line = bis.readLine()) != null && tam <= (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual)) ){
						tam++;
						//VAI COMECAR A GRAVAR AS LINHAS DO TAMANHO DA PAGINA
						if ( tam > ( (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual) ) - TAMANHO_PAGINA) ) {
		
						textoErro.append(line);
						textoErro.append("<br>");
						}
					}
				
				}
				//Caso não exista pagina.
				if ( textoErro.toString().equals("") ) {
					throw new ActionServletException("atencao.pagina_inexistente");
				}
				form.setTextoErro(textoErro.toString());
				
				//Controla a numeracao das paginas na tela.
				int anterior = inicio-TAMANHO_PAGINA;
				int proximo = inicio+TAMANHO_PAGINA ;
				int ultima 	= NUMERO_TOTAL_LINHAS_ARQUIVO;
				int paginaAtual = 0;
				if ( numeroPagina != null && !numeroPagina.equals("") ) {
					paginaAtual = Integer.valueOf(numeroPagina); 
				} else {
					paginaAtual = (inicio/TAMANHO_PAGINA) + 1;
				}
				int ultimaPagina = Util.dividirArredondarResultadoBaixo(NUMERO_TOTAL_LINHAS_ARQUIVO, TAMANHO_PAGINA);
				ultimaPagina ++;
				
				if ( paginaAtual == ultimaPagina ) {
					form.setUltima(null);
				} else {
					form.setUltima(""+ ultima);
				}
				if ( form.getPaginaAtual() != null ) {
					paginaAtual = numeroPaginaAtual;
				}
				
				if(inicio != 0 && paginaAtual != 1){
					form.setInicio(""+inicio);	
				}else{
					form.setInicio(null);
				}
				
				
				
				form.setAnterior(""+anterior);
				form.setProximo(""+proximo);
				
				form.setPaginaAtual(""+paginaAtual);
			
			} catch (EOFException eof){
				System.out.println("Fim do arquivo");
			} finally {
				if (bis != null){
					bis.close();	
				}
				if (fw != null){
					fw.close();
				}
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
        return retorno;
	}
}		

