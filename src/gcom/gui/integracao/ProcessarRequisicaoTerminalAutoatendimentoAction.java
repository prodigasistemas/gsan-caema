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
package gcom.gui.integracao;

import gcom.autoatendimento.ContaConsultarHelper;
import gcom.autoatendimento.DeclaracaoAnualQuitacaoDebitoHelper;
import gcom.autoatendimento.DeclaracaoQuitacaoConsultarHelper;
import gcom.autoatendimento.OpcaoParcelamentoAutoAtendimentoHelper;
import gcom.autoatendimento.OpcoesParcelamentoAutoAtendimentoHelper;
import gcom.autoatendimento.PagamentoAVistaParcelamentoDebitoHelper;
import gcom.autoatendimento.ParcelamentoDebitoEfetuadoHelper;
import gcom.autoatendimento.ParcelamentoDebitoHelper;
import gcom.autoatendimento.SegundaViaContaHelper;
import gcom.autoatendimento.VecimentoAlternativoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.bean.Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper;
import gcom.util.ConstantesAplicacao;
import gcom.util.FachadaException;
import gcom.util.Util;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe responsável por integração do sistema GSAN ao terminao de autoatendimento da COMPESA.
 * 
 * @author Arthur Carvalho
 * @date 03/09/2013
 */
public class ProcessarRequisicaoTerminalAutoatendimentoAction extends GcomAction {	
	
	/**
	 * Constantes de Classe.
	 */
    private static final String LOGIN_CONSULTAR = "login";
    private static final String SEGUNDAVIA_CONSULTAR = "2viaconsulta";
    private static final String SEGUNDAVIA_IMPRESSAO = "2viaimpressao";
    private static final String QUITACAO_CONSULTAR = "quitacaoconsulta";
    private static final String QUITACAO_IMPRESSAO = "quitacaoimpressao";
    private static final String VENCIMENTO_ALTERNATIVO_CONSULTAR = "vencimentoalternativoconsulta";
    private static final String VENCIMENTO_ALTERNATIVO_IMPRESSAO = "vencimentoalternativoalteracao";
    private static final String PARCELAMENTO_DEBITO_PAGAMENTO_AVISTA = "parcelamentodebitopagamentoavista";
    private static final String PARCELAMENTO_DEBITO_CONSULTAR = "parcelamentodebitoconsulta";
    private static final String PARCELAMENTO_DEBITO_CALCULAR = "parcelamentodebitocalcular";
    private static final String PARCELAMENTO_DEBITO_EFETUAR = "parcelamentodebitoefetuar";
    private static final String PARCELAMENTO_DEBITO_EM_ABERTO_CONSULTAR = "parcelamentodebitoemabertoconsultar"; 
    
    
    
	/**
	 * Método Execute do Action
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			System.out.println("ERROR:REQUISICAO TERMINAL AUTOATENDIMENTO: " + e.getMessage());
		}
		
		try {
			
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);			
			
			String requisicaoTipo = null;
			if ( request.getParameter("requisicaoTipo") != null && !request.getParameter("requisicaoTipo").equals("") ) {
				requisicaoTipo = (String) request.getParameter("requisicaoTipo");
			} else {
				requisicaoTipo = din.readUTF();	
			}		
				
			System.out.println("INICIO:SOLICITACAO REQUISICAO AUTOATENDIMENTO DO TIPO: " + requisicaoTipo);
			
			if (requisicaoTipo != null && !requisicaoTipo.equals("") ) {
				
				if ( requisicaoTipo.equals(LOGIN_CONSULTAR) ) {
				
					processarRequisicaoLogin(din, response, out, request);
				} 
				
				else if ( requisicaoTipo.equals(SEGUNDAVIA_CONSULTAR)) {
					
					processarRequisicaoSegundaViaConsultar(din, response, out, request);
				}
				
				else if ( requisicaoTipo.equals(SEGUNDAVIA_IMPRESSAO)) {
					
					processarRequisicaoSegundaViaImpressao(din, response, out, request);
				}
				
				else if ( requisicaoTipo.equals(QUITACAO_CONSULTAR)) {
					
					processarRequisicaoQuitacaoConsultar(din, response, out, request);
				}
				
				else if ( requisicaoTipo.equals(QUITACAO_IMPRESSAO)) {
					
					processarRequisicaoQuitacaoImpressao(din, response, out, request);
				}
				
				else if ( requisicaoTipo.equals(VENCIMENTO_ALTERNATIVO_CONSULTAR)) {
					
					processarRequisicaoVencimentoAlternativoConsultar(din, response, out, request);
				}
				
				else if ( requisicaoTipo.equals(VENCIMENTO_ALTERNATIVO_IMPRESSAO)) {
					
					processarRequisicaoVencimentoAlternativoImpressao(din, response, out, request);
				} 
				
				else if ( requisicaoTipo.equals(PARCELAMENTO_DEBITO_PAGAMENTO_AVISTA) ) {
				
					processarRequisicaoPagamentoAVistaParcelamentoWebservice(din, response, out, request);
				}
				
				else if (requisicaoTipo.equals(PARCELAMENTO_DEBITO_CONSULTAR)){
					processarRequisicaoParcelamentoDebitoConsulta(din, response, out, request);
				}
				
				else if(requisicaoTipo.equals(PARCELAMENTO_DEBITO_CALCULAR)){
					processarRequisicaoParcelamentoDebitoCalcular(din, response, out, request);
				}
				
				else if(requisicaoTipo.equals(PARCELAMENTO_DEBITO_EFETUAR)){
					processarRequisicaoParcelamentoDebitoEfetuar(din, response, out, request);
				}
				
				else if(requisicaoTipo.equals(PARCELAMENTO_DEBITO_EM_ABERTO_CONSULTAR)){
					processarRequisicaoParcelamentoDebitoEmAbertoConsultar(din, response, out, request);
				}			
				
				else {
					throw new Exception("atencao.parametros_autoatendimento_invalidos");
				}
				
				
			} else {
				System.out.println("FIM: SOLICITACAO REQUISICAO AUTOATENDIMENTO - REQUISICAO TIPO EM BRANCO OU NULO ");
			}
			System.out.println("FIM:SOLICITACAO REQUISICAO AUTOATENDIMENTO DO TIPO: " + requisicaoTipo);
		} catch (Exception e) {
			
			try {
				
				String mensagem = null;
				String[] parametrosArray = null;
				
				if(e instanceof FachadaException){
					List<String> parametros = ((FachadaException) e).getParametroMensagem();
					if(parametros != null){
						parametrosArray = parametros.toArray(new String[((FachadaException) e).getParametroMensagem().size()]);
					}
				}else if (e instanceof EOFException){
					mensagem  = ConstantesAplicacao.get("atencao.parametros_autoatendimento_invalidos");
				}
				
				if(mensagem == null && parametrosArray !=null && parametrosArray.length > 0){
					mensagem = ConstantesAplicacao.get(e.getMessage(), parametrosArray);
				}else if(mensagem == null){
					mensagem = ConstantesAplicacao.get(e.getMessage());
				}
				
				
				JAXBContext jc = JAXBContext.newInstance(String.class);
				
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
				JAXBElement<String> jaxbElement = new JAXBElement<String>(new QName("mensagemErro"), String.class, mensagem);
				marshaller.marshal(jaxbElement, out);
				
				System.out.println("ERROR:SOLICITACAO REQUISICAO AUTOATENDIMENTO - MessagemErro = " + mensagem);
				out.flush();
			} catch (Exception e1) {
				System.out.println("ERROR:SOLICITACAO REQUISICAO AUTOATENDIMENTO: " + e.getMessage());
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
	 * Metodo resposável por validar o login do usuário no terminal de autoatedimento da COMPESA.
	 * 
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoLogin(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO LOGIN");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		String cpfCnpj = null;
		if ( request.getParameter("cpfCnpj") != null && !request.getParameter("cpfCnpj").equals("") ) {
			cpfCnpj = (String) request.getParameter("cpfCnpj");
		} else {
			cpfCnpj = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		//Verifica se os parametros foram informados
		if ( matricula != null && !matricula.equals("") && cpfCnpj != null && !cpfCnpj.equals("") ) {
			String retorno = new String();
			//verifica se o login é valido
			if ( getFachada().validaLoginAutoAtendimento(Integer.valueOf(matricula), cpfCnpj) ) {
				retorno = "S";	
			} else {
				retorno = "N";
			}
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(String.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<String> jaxbElement = new JAXBElement<String>(new QName("login"), String.class, retorno);
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
			System.out.println("FIM: REQUISICAO LOGIN");
			
		} else {
			System.out.println("FIM: REQUISICAO LOGIN - ERRO PARAMETROS INVALIDOS");
			throw new Exception("atencao.dados.inexistente.parametros.informados");
		}
	}	
	
	/**
	 * Metodo resposável por consultar as contas do usuário no terminal de autoatedimento da COMPESA.
	 * 
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoSegundaViaConsultar(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO 2 VIA CONSULTAR");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		
		ContaConsultarHelper contaValorHelper = (ContaConsultarHelper) getFachada().consultar2ViaContaAutoAtendimento(matricula);
		
		if ( contaValorHelper != null && contaValorHelper.getConta() != null && contaValorHelper.getConta().size() > 0) {
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(ContaConsultarHelper.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<ContaConsultarHelper> jaxbElement = new JAXBElement<ContaConsultarHelper>(new QName("contas"), ContaConsultarHelper.class, contaValorHelper);
			
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
		} else {
			System.out.println("FIM: REQUISICAO 2 VIA CONSULTAR - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO 2 VIA CONSULTAR");
	}	
	
	/**
	 * Metodo resposável por consultar as contas do usuário no terminal de autoatedimento da COMPESA.
	 * 
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoSegundaViaImpressao(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO 2 VIA IMPRESSAO");
		
		String idConta = null;
		if ( request.getParameter("idConta") != null && !request.getParameter("idConta").equals("") ) {
			idConta = (String) request.getParameter("idConta");
		} else {
			idConta = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(idConta)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		SegundaViaContaHelper contaHelper = (SegundaViaContaHelper) getFachada().emitir2ViaContasAutoAtendimento(idConta);
		
		if ( contaHelper != null ) {
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(SegundaViaContaHelper.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<SegundaViaContaHelper> jaxbElement = new JAXBElement<SegundaViaContaHelper>(new QName("contaImpressao"), SegundaViaContaHelper.class, contaHelper);
			
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
		} else {
			System.out.println("FIM: REQUISICAO 2 VIA IMPRESSAO - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO 2 VIA IMPRESSAO");
	}	
	
	
	/**
	 * Metodo resposável por consultar as contas do usuário no terminal de autoatedimento da COMPESA.
	 * 
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoQuitacaoConsultar(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO QUITACAO CONSULTAR");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		Collection<Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper> colecao =  getFachada().pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(matricula);
		
		if ( colecao != null && !colecao.isEmpty()) {
			DeclaracaoQuitacaoConsultarHelper  declaracaoHelper = new DeclaracaoQuitacaoConsultarHelper();
			declaracaoHelper.setDeclaracao(colecao);
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(DeclaracaoQuitacaoConsultarHelper.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<DeclaracaoQuitacaoConsultarHelper> jaxbElement = new JAXBElement<DeclaracaoQuitacaoConsultarHelper>(new QName("quitacao"), DeclaracaoQuitacaoConsultarHelper.class, declaracaoHelper);
			
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
		} else {
			System.out.println("FIM: REQUISICAO QUITACAO CONSULTAR - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO QUITACAO CONSULTAR");
	}	
	
	/**
	 * Metodo resposável por consultar as contas do usuário no terminal de autoatedimento da COMPESA.
	 * 
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoQuitacaoImpressao(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO QUITACAO IMPRESSAO");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		
		String ano = null;
		if ( request.getParameter("ano") != null && !request.getParameter("ano").equals("") ) {
			ano = (String) request.getParameter("ano");
		} else {
			ano = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula) || !Util.verificaSeNumeroNatural(ano)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		DeclaracaoAnualQuitacaoDebitoHelper declaracaoHelper = (DeclaracaoAnualQuitacaoDebitoHelper) getFachada().emitir2ViaDeclaracaoAnualAutoAtendimento(matricula, ano);
		
		if ( declaracaoHelper != null ) {
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(DeclaracaoAnualQuitacaoDebitoHelper.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<DeclaracaoAnualQuitacaoDebitoHelper> jaxbElement = new JAXBElement<DeclaracaoAnualQuitacaoDebitoHelper>(new QName("quitacaoImpressao"), DeclaracaoAnualQuitacaoDebitoHelper.class, declaracaoHelper);
			
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
		} else {
			System.out.println("FIM: REQUISICAO QUITACAO IMPRESSAO - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO QUITACAO IMPRESSAO");
	}	
	
	
	/**
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoVencimentoAlternativoConsultar(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO VENCIMENTO ALTERNATIVO CONSULTAR");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}

		
		VecimentoAlternativoHelper vencimentoHelper = (VecimentoAlternativoHelper) getFachada().consultarDiasVencimentoAlternativoAutoAtendimento(matricula);
		
		if ( vencimentoHelper != null ) {
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(VecimentoAlternativoHelper.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<VecimentoAlternativoHelper> jaxbElement = new JAXBElement<VecimentoAlternativoHelper>(new QName("vencimentos"), VecimentoAlternativoHelper.class, vencimentoHelper);
			
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
		} else {
			System.out.println("FIM: REQUISICAO VENCIMENTO ALTERNATIVO CONSULTAR - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO VENCIMENTO ALTERNATIVO CONSULTAR");
	}	
	
	/**
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoVencimentoAlternativoImpressao(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO VENCIMENTO ALTERNATIVO IMPRESSAO");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		
		String dia = null;
		if ( request.getParameter("dia") != null && !request.getParameter("dia").equals("") ) {
			dia = (String) request.getParameter("dia");
		} else {
			dia = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula) || !Util.verificaSeNumeroNatural(dia)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}

		VecimentoAlternativoHelper vencimentoHelper = (VecimentoAlternativoHelper) getFachada().consultarDiasVencimentoAlternativoAutoAtendimento(matricula);
		
		List<Integer> diasPossiveis = vencimentoHelper.getDiasPossiveis();
		
		if(!diasPossiveis.contains(new Integer(dia))){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		String diaAlterado =  getFachada().inserirDiaVencimentoAlternativoAutoAtendimento(matricula, dia);
		
		if ( diaAlterado != null && !diaAlterado.equals("") ) {
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(String.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<String> jaxbElement = new JAXBElement<String>(new QName("vencimentoImpressao"), String.class, diaAlterado);
			
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
		} else {
			System.out.println("FIM: REQUISICAO VENCIMENTO ALTERNATIVO IMPRESSAO - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO VENCIMENTO ALTERNATIVO IMPRESSAO");
	}	
	
	
	/**
	 * @author Arthur Carvalho
	 * @date 03/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoPagamentoAVistaParcelamentoWebservice(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO PAGAMENTO A VISTA WEBSERVICE");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		
		String cpfCnpj = null;
		if ( request.getParameter("cpfCnpj") != null && !request.getParameter("cpfCnpj").equals("") ) {
			cpfCnpj = (String) request.getParameter("cpfCnpj");
		} else {
			cpfCnpj = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		PagamentoAVistaParcelamentoDebitoHelper helper =  getFachada().consultarPagamentoAVistaDebitosWebserver(matricula, cpfCnpj);
		
		if ( helper != null ) {
			//monta os parametros de retorno.
			JAXBContext jc = JAXBContext.newInstance(PagamentoAVistaParcelamentoDebitoHelper.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			JAXBElement<PagamentoAVistaParcelamentoDebitoHelper> jaxbElement = new JAXBElement<PagamentoAVistaParcelamentoDebitoHelper>(new QName("extratoDebitos"), PagamentoAVistaParcelamentoDebitoHelper.class, helper);
			
			marshaller.marshal(jaxbElement, out);
//			marshaller.marshal(jaxbElement, System.out);
			
			out.flush();
			
		} else {
			System.out.println("FIM: REQUISICAO PAGAMENTO A VISTA WEBSERVICE - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO PAGAMENTO A VISTA WEBSERVICE");
	}
	
	/**
	 * [UC1553] - Consultar Parcelamento de Débitos Webservice
	 * 
	 * @author Anderson Cabral
	 * @date 05/09/2013
	 *  
	 * @param din
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	private void processarRequisicaoParcelamentoDebitoConsulta(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO PARCELAMENTO DÉBITO CONSULTA");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		String cpfCnpj = null;
		if ( request.getParameter("cpfCnpj") != null && !request.getParameter("cpfCnpj").equals("") ) {
			cpfCnpj = (String) request.getParameter("cpfCnpj");
		} else {
			cpfCnpj = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula) || !Util.isLong(cpfCnpj)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		ArrayList<Object> dadosParcelamento = getFachada().consultarNegociacaoDebitoAutoAtendimento(matricula, cpfCnpj, null, "", false);
		
		if(!Util.isVazioOrNulo(dadosParcelamento)){
			ParcelamentoDebitoHelper parcelamentoDebitoHelper = (ParcelamentoDebitoHelper) dadosParcelamento.get(0);
			if ( parcelamentoDebitoHelper != null) {
				//monta os parametros de retorno.
				JAXBContext jc = JAXBContext.newInstance(ParcelamentoDebitoHelper.class);
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
				JAXBElement<ParcelamentoDebitoHelper> jaxbElement = new JAXBElement<ParcelamentoDebitoHelper>(new QName("parcelamento"), ParcelamentoDebitoHelper.class, parcelamentoDebitoHelper);
				
				marshaller.marshal(jaxbElement, out);
//				marshaller.marshal(jaxbElement, System.out);
				
				out.flush();
				
			} else {
				System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO CONSULTA - ERRO PARAMETROS INVALIDOS");
				throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
			}
		}else {
			System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO CONSULTA - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}	
		
		System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO CONSULTA");
	}
	
	/**
	 * [UC1554] - Consultar Calculo Parcelamento de Débitos Webservice
	 * 
	 * @author Anderson Cabral
	 * @date 09/09/2013
	 * @param matricula
	 * @param cpfCliente
	 * @return parcelamentoDebitoHelper
	 */
	private void processarRequisicaoParcelamentoDebitoCalcular(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO PARCELAMENTO DÉBITO CALCULAR");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		String cpfCnpj = null;
		if ( request.getParameter("cpfCnpj") != null && !request.getParameter("cpfCnpj").equals("") ) {
			cpfCnpj = (String) request.getParameter("cpfCnpj");
		} else {
			cpfCnpj = din.readUTF();	
		}
		String valorEntrada = null;
		if ( request.getParameter("valorEntrada") != null && !request.getParameter("valorEntrada").equals("") ) {
			valorEntrada = (String) request.getParameter("valorEntrada");
		} else {
			valorEntrada = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula) || !Util.isLong(cpfCnpj) || !Util.isBigDecimal(valorEntrada) || !temCasaDecimal(valorEntrada)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> colecao = getFachada().calcularParcelamentoDebitoAutoAtendimento(matricula, cpfCnpj, valorEntrada);

		if ( colecao != null && !colecao.isEmpty()) {
				OpcoesParcelamentoAutoAtendimentoHelper opcoesParcelamentoAutoAtendimentoHelper = new OpcoesParcelamentoAutoAtendimentoHelper();
				opcoesParcelamentoAutoAtendimentoHelper.setOpcao(colecao);
				
				//monta os parametros de retorno.
				JAXBContext jc = JAXBContext.newInstance(OpcoesParcelamentoAutoAtendimentoHelper.class);
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
				JAXBElement<OpcoesParcelamentoAutoAtendimentoHelper> jaxbElement = new JAXBElement<OpcoesParcelamentoAutoAtendimentoHelper>(new QName("opcoesParcelamento"), OpcoesParcelamentoAutoAtendimentoHelper.class, opcoesParcelamentoAutoAtendimentoHelper);
				
				marshaller.marshal(jaxbElement, out);
//				marshaller.marshal(jaxbElement, System.out);
				
				out.flush();
				
		} else {
			System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO CALCULAR - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO CALCULAR");
	}
	
	/**
	 * [UC1556] - Consultar Parcelar Parcelamento de Débitos Webservice
	 * 
	 * Esse metodo efetua o parcelamento e retorna a guia e o termo
	 * 
	 * @author Anderson Cabral
	 * @date 10/09/2013
	 * @param matricula
	 * @param cpfCliente
	 * @param valorEntrada
	 * @param numeroParcelas
	 * @return ParcelamentoDebitoEfetuadoHelper
	 */
	private void processarRequisicaoParcelamentoDebitoEfetuar(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO PARCELAMENTO DÉBITO EFETUAR");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		String cpfCnpj = null;
		if ( request.getParameter("cpfCnpj") != null && !request.getParameter("cpfCnpj").equals("") ) {
			cpfCnpj = (String) request.getParameter("cpfCnpj");
		} else {
			cpfCnpj = din.readUTF();	
		}
		String valorEntrada = null;
		if ( request.getParameter("valorEntrada") != null && !request.getParameter("valorEntrada").equals("") ) {
			valorEntrada = (String) request.getParameter("valorEntrada");
			valorEntrada = valorEntrada.replaceAll(",", ".");
		} else {
			valorEntrada = din.readUTF();	
		}
		String numeroParcelas = null;
		if ( request.getParameter("numeroParcelas") != null && !request.getParameter("numeroParcelas").equals("") ) {
			numeroParcelas = (String) request.getParameter("numeroParcelas");
		} else {
			numeroParcelas = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula) || !Util.isLong(cpfCnpj) || !Util.isBigDecimal(valorEntrada) || !Util.isLong(numeroParcelas) || !temCasaDecimal(valorEntrada)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		ParcelamentoDebitoEfetuadoHelper parcelamentoDebitoEfetuadoHelper = getFachada().efetuarParcelamentoDebitoAutoAtendimento(matricula, cpfCnpj, valorEntrada, numeroParcelas);

		if ( parcelamentoDebitoEfetuadoHelper != null) {
				//monta os parametros de retorno.
				JAXBContext jc = JAXBContext.newInstance(ParcelamentoDebitoEfetuadoHelper.class);
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
				JAXBElement<ParcelamentoDebitoEfetuadoHelper> jaxbElement = new JAXBElement<ParcelamentoDebitoEfetuadoHelper>(new QName("dadosParcelamentoEfetuado"), ParcelamentoDebitoEfetuadoHelper.class, parcelamentoDebitoEfetuadoHelper);
				
				marshaller.marshal(jaxbElement, out);
//				marshaller.marshal(jaxbElement, System.out);
				
				out.flush();
				
		} else {
			System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO EFETUAR - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO CALCULAR");
	}
	
	/**
	 *  [UC1557] - Consultar Parcelamento em Aberto
	 * 
	 * @author Anderson Cabral
	 * @date 11/09/2013
	 * @param matricula
	 * @return ParcelamentoDebitoEfetuadoHelper
	 */
	private void processarRequisicaoParcelamentoDebitoEmAbertoConsultar(DataInputStream din, HttpServletResponse response, OutputStream out, HttpServletRequest request) throws Exception {
		
		System.out.println("INICIO: REQUISICAO PARCELAMENTO DÉBITO EM ABERTO CONSULTAR");
		
		String matricula = null;
		if ( request.getParameter("matricula") != null && !request.getParameter("matricula").equals("") ) {
			matricula = (String) request.getParameter("matricula");
		} else {
			matricula = din.readUTF();	
		}
		
		if(!Util.verificaSeNumeroNatural(matricula)){
			throw new ActionServletException("atencao.parametros_autoatendimento_invalidos");
		}
		
		ParcelamentoDebitoEfetuadoHelper parcelamentoDebitoEfetuadoHelper = getFachada().consultarParcelamentoEfetuado(matricula);

		if ( parcelamentoDebitoEfetuadoHelper != null) {
				//monta os parametros de retorno.
				JAXBContext jc = JAXBContext.newInstance(ParcelamentoDebitoEfetuadoHelper.class);
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
				JAXBElement<ParcelamentoDebitoEfetuadoHelper> jaxbElement = new JAXBElement<ParcelamentoDebitoEfetuadoHelper>(new QName("dadosParcelamentoEmAberto"), ParcelamentoDebitoEfetuadoHelper.class, parcelamentoDebitoEfetuadoHelper);
				
				marshaller.marshal(jaxbElement, out);
//				marshaller.marshal(jaxbElement, System.out);
				
				out.flush();
				
		} else {
			System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO EM ABERTO CONSULTAR - ERRO PARAMETROS INVALIDOS");
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		System.out.println("FIM: REQUISICAO PARCELAMENTO DÉBITO EM ABERTO CONSULTAR");
	}
	

	
	public boolean temCasaDecimal(String valor) {

			boolean temCasaDecimal = false;
			if (valor.length() > 2
					&& (valor.substring(valor.length() - 3, valor.length() - 2)
							.equals(".") || valor.substring(valor.length() - 3,
							valor.length() - 2).equals(","))) {
				temCasaDecimal = true;
				
			}

		return temCasaDecimal;
	}
	
}