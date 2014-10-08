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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.bean.PesquisarDocumentosHelper;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1395] Pesquisar Documentos Pagos
 * 
 * @author Hugo Azevedo
 * @date 20/11/2012
 * 
 */
public class PesquisarDocumentosPagosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("pesquisarDocumentosPagosResultado");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarDocumentosPagosActionForm form = (PesquisarDocumentosPagosActionForm) actionForm;
		Integer idRA = Util.converterStringParaInteger(form.getIdRA());
		
		//Caso o usu�rio tenha selecionado os documentos da pesquisa
		String selecionou = httpServletRequest.getParameter("selecionou");
		if(selecionou != null && selecionou.equals("sim")){
			
			String[] idsSelecionados = form.getIdsSelecionados();
			Collection colecaoDocumentosPagos = (Collection)sessao.getAttribute("colecaoDocumentosPagos");
			
			//Caso n�o tenha selecionado nenhum
			if(idsSelecionados == null || idsSelecionados.length == 0){
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
			
			//5. O usu�rio seleciona os documentos apresentados.
			Collection colecaoRetorno = this.retornarColecaoFiltrada(idsSelecionados,colecaoDocumentosPagos);
			
			//Verificar se o documento ja foi utilizado para uma devolu��o
			Iterator iColecaoRetorno = colecaoRetorno.iterator();
			
			while (iColecaoRetorno.hasNext()){
				
				PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper) iColecaoRetorno.next();
				
				//[FS0006] - Documento j� possui RA Devolu��o
				Integer utilizando = fachada.verificarDocumentoUtilizadoParaDevolucao(helper.getIdDocumento(), helper.getIndicadorTipoDocumento(),idRA);
				
				if (utilizando != null){
					
					switch(helper.getIndicadorTipoDocumento()){
						
						//Caso tenha sido selecionada uma conta e j� exista uma RA de Devolu��o associada � conta
						case 1:
							throw new ActionServletException("atencao.existe_ra_dev_pgt_n_conf_cnta",
									utilizando.toString(),Util.formatarAnoMesParaMesAno(helper.getAmReferenciaDocumento()));
						
						//Caso tenha sido selecionada uma guia de pagamento e j� exista uma RA de Devolu��o associada � guia
						case 7:
							throw new ActionServletException("atencao.existe_ra_dev_pgt_n_conf_guia",
									utilizando.toString(),Util.formatarAnoMesParaMesAno(helper.getAmReferenciaDocumento()));
							
						//Caso tenha sido selecionado um d�bito a cobrar e j� exista uma RA de Devolu��o associada ao d�bito	
						case 6:
							throw new ActionServletException("atencao.existe_ra_dev_pgt_n_conf_debito",
									utilizando.toString(),Util.formatarAnoMesParaMesAno(helper.getAmReferenciaDocumento()));
					}
				}
			}
			
			sessao.removeAttribute("colecaoDocumentosPagos");
			
			//6. Os documentos s�o retornados para o caso de uso que chamou esta funcionalidade.
			sessao.setAttribute("colecaoPagamentosValoresCobIndevidamente", colecaoRetorno);
			retorno = actionMapping.findForward("pesquisarDocumentosPagosConcluir");
			return retorno;
			
		}
		
		String matriculaImovel = form.getMatriculaImovel();
		String tipoDocumento = form.getTipoDocumento();
		String referenciaInicial = form.getReferenciaInicial();
		String referenciaFinal = form.getReferenciaFinal();
		String dataEmissaoInicial = form.getDataEmissaoInicial();
		String dataEmissaoFinal = form.getDataEmissaoFinal();
		String dataVencimentoInicial = form.getDataVencimentoInicial();
		String dataVencimentoFinal = form.getDataVencimentoFinal();
		String dataPagamentoInicial = form.getDataPagamentoInicial();
		String dataPagamentoFinal = form.getDataPagamentoFinal();
		
		//Valida��o dos campos do formul�rio
		//========================================================================================
		
		//[FS0001] Verificar preenchimento dos campos
		//---------------------------------------------------------------------------------------
		//Caso n�o tenha sido informada nenhuma op��o de filtragem
		if(
				(referenciaInicial == null || referenciaInicial.equals("")) &&
				(referenciaFinal == null || referenciaFinal.equals("")) &&
				(dataEmissaoInicial == null || dataEmissaoInicial.equals("")) &&
				(dataEmissaoFinal == null || dataEmissaoFinal.equals("")) &&
				(dataVencimentoInicial == null || dataVencimentoInicial.equals("")) &&
				(dataVencimentoFinal == null || dataVencimentoFinal.equals("")) &&
				(dataPagamentoInicial == null || dataPagamentoInicial.equals("")) &&
				(dataPagamentoFinal == null || dataPagamentoFinal.equals(""))
	      ){
			
			//o sistema dever� exibir a mensagem "Informe pelo menos uma op��o de sele��o"
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		//---------------------------------------------------------------------------------------		
		
		//[FS0005] Validar refer�ncia
		this.validarReferencia(referenciaInicial,"Refer�ncia Inicial");
		this.validarReferencia(referenciaFinal,"Refer�ncia Final");
		
		//[FS0002] Validar data
		this.validarData(dataEmissaoInicial, "Data de Emiss�o Inicial");
		this.validarData(dataEmissaoFinal, "Data de Emiss�o Final");
		this.validarData(dataVencimentoInicial, "Data de Vecimento Inicial");
		this.validarData(dataVencimentoFinal, "Data de Vencimento Final");
		this.validarData(dataPagamentoInicial, "Data de Pagamento Inicial");
		this.validarData(dataPagamentoFinal, "Data de Pagamento Final");
		
		//[FS0002] Validar Per�odo de datas
		this.verificarDataInicialMenorFinal(dataEmissaoInicial, dataEmissaoFinal, "Data de Emiss�o","atencao.dataemissaofinal.menorque");
		this.verificarDataInicialMenorFinal(dataVencimentoInicial, dataVencimentoFinal, "Data de Vecimento","atencao.datavencimentofinal.menorque");
		this.verificarDataInicialMenorFinal(dataPagamentoInicial, dataPagamentoFinal, "Data de Pagamento","atencao.data_final.anterior.data_inicial");
		
		//[FS0005] Validar Per�odo de refer�ncias
		this.validarReferenciaInicialMenorFinal(referenciaInicial,referenciaFinal,"Refer�ncia","atencao.referencia_final_anterior_referencia_inicial");
		
		//3. O usu�rio efetua a pesquisa
		Collection colecaoDocumentosPagos = fachada.pesquisarDocumentosPagos(matriculaImovel,
																			 tipoDocumento,
																			 referenciaInicial,	
																			 referenciaFinal,
																			 dataEmissaoInicial,
																			 dataEmissaoFinal,
																			 dataVencimentoInicial,
																			 dataVencimentoFinal,
																			 dataPagamentoInicial,
																			 dataPagamentoFinal);
		
		//[FS0004] Nenhum registro encontrado
		//Caso a busca n�o retorne nenhum registro
		if(colecaoDocumentosPagos == null || colecaoDocumentosPagos.size() == 0){
			
			//o sistema dever� exibir a mensagem "A pesquisa n�o retornou nenhum resultado"
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		sessao.setAttribute("colecaoDocumentosPagos", colecaoDocumentosPagos);
		
		return retorno;
	}
	
	private void validarReferenciaInicialMenorFinal(String referenciaInicial,
			String referenciaFinal, String nomeCampo, String msgErro) {
		
		//Validando se os dois campos foram informados
		if(referenciaInicial != null && !referenciaInicial.equals("") &&
		   (referenciaFinal == null || referenciaFinal.equals(""))){
			
			throw new ActionServletException("atencao.informe_campo_final",null,nomeCampo);
		}
		
		if(referenciaFinal != null && !referenciaFinal.equals("") &&
		   (referenciaInicial == null || referenciaInicial.equals(""))){
			
			throw new ActionServletException("atencao.informe_campo_inicial",null,nomeCampo);
		}
		
		
		if(referenciaInicial != null && !referenciaInicial.equals("") && 
				referenciaFinal != null && !referenciaFinal.equals("")){
			
			Integer amReferenciaInicial = Util.formatarMesAnoComBarraParaAnoMes(referenciaInicial);
			Integer amReferenciaFinal = Util.formatarMesAnoComBarraParaAnoMes(referenciaFinal);
			
			if((amReferenciaInicial.compareTo(amReferenciaFinal)) > 0){
				
				throw new ActionServletException(msgErro);
			}
		}		
	}

	/***
	 * [FS0002] Validar data
	 * Fun��o de valida��o das datas
	 */
	private void validarData(String data, String nomeCampo){
		if(data != null && !data.equals("")){
			//Caso a data esteja inv�lida
			if(Util.validarDiaMesAno(data)){
				throw new ActionServletException("atencao.erro_invalido",null,nomeCampo);
			}
		}
	}
	
	/***
	 * [FS0005] Validar refer�ncia
	 * Fun��o de valida��o das refer�ncias
	 */
	private void validarReferencia(String ref, String nomeCampo){
		
		if(ref != null && !ref.equals("")){
			//Caso o m�s/ano de refer�ncia esteja inv�lido
			if(!Util.validarMesAno(ref)){
				
				//exibir a mensagem "M�s e Ano de Refer�ncia Inv�lido"
				throw new ActionServletException("atencao.erro_invalido",null,nomeCampo);
			}
			
			Integer anoMesAtual = Util.formataAnoMes(new Date());
			String mesAnoAtual = Util.formatarAnoMesParaMesAno(anoMesAtual);
			Integer anoMesRef = Util.formatarMesAnoComBarraParaAnoMes(ref);
			
			//Caso o m�s/ano de refer�ncia esteja seja superior ao m�s corrente
			if(anoMesRef.intValue() > anoMesAtual.intValue()){
				
				//exibir a mensagem "M�s e Ano de Refer�ncia n�o pode ser superior ao m�s <<M�s/Ano Corrente>>"
				throw new ActionServletException("atencao.mes_ano_ref_superior_mes_atual",null,mesAnoAtual);
			}
		}
		
	}
	
	/***
	 * [FS0005] Validar refer�ncia
	 * Fun��o de valida��o das refer�ncias
	 */
	private void verificarDataInicialMenorFinal(String data1str, String data2str, String nomeCampo, String msgErro){
		
		//Validando se os dois campos foram informados
		if(data1str != null && !data1str.equals("") &&
		   (data2str == null || data2str.equals(""))){
			
			throw new ActionServletException("atencao.informe_campo_final",null,nomeCampo);
		}
		
		if(data2str != null && !data2str.equals("") &&
		   (data1str == null || data1str.equals(""))){
			
			throw new ActionServletException("atencao.informe_campo_inicial",null,nomeCampo);
		}
		
		
		if(data1str != null && !data1str.equals("") && 
		   data2str != null && !data2str.equals("")){
			
			Date data1 = Util.converteStringParaDate(data1str);
			Date data2 = Util.converteStringParaDate(data2str);
			
			//Caso a data final seja anterior � data inicial
			if(Util.compararData(data2, data1) < 0){
				
				//exibir a mensagem "Data Final do Per�odo � anterior � Data Inicial do Per�odo"
				throw new ActionServletException(msgErro);
			}
		}
	}
	
	
	//Fun��o auxiliar para retirar os im�veis selecionados pelo usu�rio da cole��o total
	private Collection retornarColecaoFiltrada(String[] ids, Collection aFiltrar){
		
		Collection colecaoRetorno = new ArrayList();
		Iterator itAFiltrar = aFiltrar.iterator();
		while(itAFiltrar.hasNext()){
			PesquisarDocumentosHelper helper = (PesquisarDocumentosHelper)itAFiltrar.next();
			for(String id : ids){
				if(helper.getIdPagamento().toString().equals(id)){
					colecaoRetorno.add(helper);
					break;
				}
			}
		}
		return colecaoRetorno;
	}
	
}