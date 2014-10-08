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

package gcom.gui.cobranca.parcelamentojudicial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.parcelamentojudicial.bean.RegistroImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 
 * [UC1441] Efetuar Parcelamento Judicial
 * 
 * @author Hugo Azevedo
 * @date 15/03/2013
 */

public class ExibirEfetuarParcelamentoJudicialImovelAction extends GcomAction {
	
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		if(form.getDocumentoAcordoJudicial() != null && !form.getDocumentoAcordoJudicial().getFileName().equals("")){
			form.setDocumentoAcordoJudicialCopia(form.getDocumentoAcordoJudicial());
		}
		else if(form.getDocumentoAcordoJudicialCopia() != null){
			form.setDocumentoAcordoJudicial(form.getDocumentoAcordoJudicialCopia());
		}
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0008] Pesquisar Cliente Usu�rio
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward pesquisarClienteUsuario(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		form.setIdImovel("");
		form.setDescImovel("");
		
		String idCliente = form.getIdClienteUsuario();
		String amReferenciaInicial = form.getAmReferenciaInicial();
		String amReferenciaFinal = form.getAmReferenciaFinal();
		
		if((amReferenciaInicial == null || amReferenciaInicial.equals("")) 
				&& (amReferenciaFinal == null || amReferenciaFinal.equals(""))){
			
			throw new ActionServletException("atencao.preencha_periodo_debibo");
		}
		
		//Validando per�odo de refer�ncia		
		this.validarReferenciaInicialMenorFinal(amReferenciaInicial,amReferenciaFinal,"Refer�ncia","atencao.referencia_final_anterior_referencia_inicial");
		
		amReferenciaInicial = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaInicial);
		amReferenciaFinal = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaFinal);
		
		//1. O sistema dever� remover todos os registros da Lista de Im�veis
		Collection<RegistroImovelHelper> colecaoRegistroImovelHelper = new ArrayList<RegistroImovelHelper>(); 
		form.setListaRegistroImovelHelper(null);
		
		//2. Caso exista cliente com o c�digo informado
		//   [IT0001] Pesquisar Cliente
		Cliente cliente = this.getFachada().obterCliente(idCliente);
		if(cliente != null){
			
			//2.1. O sistema dever� exibir o nome do cliente no campo TextBox 
			//     referente a Descri��o do Cliente Usu�rio
			//     [IT0002] Pesquisar Nome do Cliente
			form.setDescClienteUsuario(cliente.getNome());
			
			//2.2. O sistema dever� pesquisar a lista de contas associadas ao cliente
			//     passando os seguintes par�metros:
			//     [UC0067]
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = this.getFachada()
					.obterDebitoImovelOuCliente(
							2,                     //Indicador de d�bito do im�vel ou cliente (com valor igual a 2);
							null,                  //Matr�cula do Im�vel (com valor nulo);
							idCliente,             //C�digo do cliente (c�digo do cliente usu�rio informado);
							new Short("2"),        //Tipo de rela��o do cliente com o im�vel (com valor igual a 2);
							amReferenciaInicial,   //Per�odo de refer�ncia do d�bito 
							amReferenciaFinal,     //Per�odo de refer�ncia do d�bito 
							Util.converteStringParaDate("01/01/0001"), //Per�odo de vencimento do d�bito
							new Date(),            //Per�odo de vencimento do d�bito
							1,                     //Indicador de pagamento (com valor igual a 1); 
							1,                     //Indicador de conta em revis�o (com valor igual a 1); 
							2,                     //Indicador de d�bito a cobrar (com valor igual a 2); 
							2,                     //Indicador de cr�dito a realizar (com valor igual a 2);
							1,					   //Indicador de notas promiss�rias (com valor igual a 1)
							2,                     //Indicador de guias de pagamento (com valor igual a 2);
							1,                     //Indicador de atualizar d�bito (com valor igual a 1)
							null);
			
			//2.3. Caso exista alguma conta em aberto para o cliente (existir algum registro na lista de contas)
			if(colecaoDebitoImovel != null 
					&& colecaoDebitoImovel.getColecaoContasValores() != null 
					&& colecaoDebitoImovel.getColecaoContasValores().size() > 0){
				
				//2.3.1. Para cada im�vel selecionado o sistema dever� adicionar um registro � Lista de Im�veis
				Collection<Integer> colecaoImoveisJaAdicionados = new ArrayList<Integer>();
				for(Iterator<ContaValoresHelper> it = colecaoDebitoImovel.getColecaoContasValores().iterator();
						it.hasNext();){
					
					ContaValoresHelper helper = it.next();
					
					//Caso o im�vel ainda n�o tenha sido adicionado
					if(helper.getConta() != null && !contemImovel(colecaoImoveisJaAdicionados, helper.getConta().getImovel().getId())){
						
						//IT0006 - Obter Dados Registro Im�vel
						colecaoImoveisJaAdicionados.add(helper.getConta().getImovel().getId());
						Collection<RegistroImovelHelper> colecaoRegistroHelper = this.getFachada().obterDadosRegistroImovel(helper.getConta().getImovel().getId());
						colecaoRegistroImovelHelper.addAll(colecaoRegistroHelper);
					}
					
					adicionarHelperAoRegistro(colecaoRegistroImovelHelper,helper);
				}
				form.setListaRegistroImovelHelper(colecaoRegistroImovelHelper);
				this.getSessao(httpServletRequest).setAttribute("bloquearImovel", "sim");
			}
			//2.4. Caso contr�rio, o sistema dever� exibir a mensagem "Cliente sem d�bitos."
			else{
				throw new ActionServletException("atencao.cliente_sem_debitos");
			}
		}
		//3. Caso contr�rio, o sistema dever� exibir "CLIENTE INEXISTENTE" em vermelho 
		//   no campo TextBox referente a Descri��o do Cliente Usu�rio
		else{
			httpServletRequest.setAttribute("clienteInexistenteException", "sim");
			form.setDescClienteUsuario("CLIENTE INEXISTENTE");
			form.setIdClienteUsuario("");
		}
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}


	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0001] Limpar Cliente Usu�rio
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward limparClienteUsuario(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//O sistema dever� limpar todos os campos referentes a Cliente Usu�rio e voltar ao passo 1 do fluxo principal.
		form.setListaRegistroImovelHelper(null);
		form.setIdClienteUsuario("");
		form.setDescClienteUsuario("");
		this.getSessao(httpServletRequest).removeAttribute("bloquearImovel");
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0011] Pesquisar Im�vel
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward pesquisarImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		String idImovel = form.getIdImovel();
		form.setIdClienteUsuario("");
		form.setDescClienteUsuario("");
		
		//1. Caso exista im�vel com a matr�cula informada 
		//   [IT0003] Pesquisar Matr�cula do Im�vel
		Imovel imovel = this.getFachada().obterImovel(Integer.parseInt(idImovel));
		if(imovel != null){
			
			//1.1. O sistema dever� exibir a inscri��o do im�vel
			//     no campo TextBox referente a Descri��o do Im�vel; 
			//     [IT0003] Pesquisar Matr�cula do Im�vel
			String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(imovel.getId());
			form.setDescImovel(inscricaoImovel);
		}
		//2. Caso contr�rio, o sistema dever� exibir 
		//   "MATR�CULA INEXISTENTE" em vermelho no campo 
		//   TextBox referente a Descri��o do Im�vel
		else{
			httpServletRequest.setAttribute("imovelInexistenteException", "sim");
			form.setIdImovel("");
			form.setDescImovel("MATR�CULA INEXISTENTE");
		}
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0002] Limpar Im�vel
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward limparImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//1. O sistema dever� limpar todos os campos referentes a Im�vel
		//   e voltar ao passo 1 do fluxo principal
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		form.setIdImovel("");
		form.setDescImovel("");
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0003] Adicionar Im�vel
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward adicionarImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		form.setIdClienteUsuario("");
		form.setDescClienteUsuario("");
		
		String idImovel = form.getIdImovel();
		String amReferenciaInicial = form.getAmReferenciaInicial();
		String amReferenciaFinal = form.getAmReferenciaFinal();
		
		Collection<RegistroImovelHelper> colecaoRegistroImovelHelper = null;
		if(form.getListaRegistroImovelHelper() != null)
			colecaoRegistroImovelHelper = form.getListaRegistroImovelHelper();
		else
			colecaoRegistroImovelHelper = new ArrayList<RegistroImovelHelper>();
		
		Imovel imovel = this.getFachada().obterImovel(Integer.parseInt(idImovel));
		
		//Validando a exist�ncia do im�vel
		if(imovel != null){
			//Verificando se o im�vel j� est� na lista
			if(contemImovelRegistroImovelHelper(colecaoRegistroImovelHelper,Integer.parseInt(idImovel))){
				throw new ActionServletException("atencao.imovel_ja_adicionado");
			}
			
			if((amReferenciaInicial == null || amReferenciaInicial.equals("")) 
					&& (amReferenciaFinal == null || amReferenciaFinal.equals(""))){
				
				throw new ActionServletException("atencao.preencha_periodo_debibo");
			}
			
			//Validando per�odo de refer�ncia		
			this.validarReferenciaInicialMenorFinal(amReferenciaInicial,amReferenciaFinal,"Refer�ncia","atencao.referencia_final_anterior_referencia_inicial");
			
			amReferenciaInicial = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaInicial);
			amReferenciaFinal = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaFinal);
			
			//1. O sistema dever� pesquisar a lista de contas associadas ao im�vel
			//   [SB0013] Obter D�bitos do Im�vel
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = this.getFachada()
					.obterDebitoImovelOuCliente(
							1,                   //Indicador de d�bito do im�vel ou cliente (com valor igual a 1);
							idImovel,            //Matr�cula do Im�vel (c�digo do im�vel informado);
							null,                //C�digo do cliente (com valor nulo);
							null,                //Tipo de rela��o do cliente com o im�vel (com valor igual a 2);
							amReferenciaInicial, //Per�odo de refer�ncia do d�bito 
							amReferenciaFinal,   //Per�odo de refer�ncia do d�bito 
							Util.converteStringParaDate("01/01/0001"), //Per�odo de vencimento do d�bito
							new Date(),          //Per�odo de vencimento do d�bito
							1,                   //Indicador de pagamento (com valor igual a 1); 
							1,                   //Indicador de conta em revis�o (com valor igual a 1); 
							2,                   //Indicador de d�bito a cobrar (com valor igual a 2); 
							2,                   //Indicador de cr�dito a realizar (com valor igual a 2);
							1,					 //Indicador de notas promiss�rias (com valor igual a 1)
							2,                   //Indicador de guias de pagamento (com valor igual a 2);
							1,                   //Indicador de atualizar d�bito (com valor igual a 1)
							null);
			
			//2. Caso exista alguma conta em aberto para o im�vel (existir algum registro na lista de contas obtida)
			if(colecaoDebitoImovel != null 
					&& colecaoDebitoImovel.getColecaoContasValores() != null 
					&& colecaoDebitoImovel.getColecaoContasValores().size() > 0){
				
				//2. o sistema dever� adicionar um registro � Lista de Im�veis, 
				//   passando como par�metro o C�digo do Im�vel informado
				Iterator iColecaoContas = colecaoDebitoImovel.getColecaoContasValores().iterator();
				
				while (iColecaoContas.hasNext()){
					ContaValoresHelper helper = (ContaValoresHelper) iColecaoContas.next();
					if(helper != null){
						
						if(helper.getConta() != null && !contemImovelRegistroImovelHelper(colecaoRegistroImovelHelper,helper.getConta().getImovel().getId())){
							
							//IT0006 - Obter Dados Registro Im�vel
							Collection<RegistroImovelHelper> colecaoRegistroHelper = this.getFachada().obterDadosRegistroImovel(helper.getConta().getImovel().getId());
							colecaoRegistroImovelHelper.addAll(colecaoRegistroHelper);
						}
						adicionarHelperAoRegistro(colecaoRegistroImovelHelper,helper);	
					}
				}
				form.setListaRegistroImovelHelper(colecaoRegistroImovelHelper);
				this.getSessao(httpServletRequest).setAttribute("bloquearCliente", "sim");
				form.setIdImovel("");
				form.setDescImovel("");
				
			}
			//3. Caso contr�rio, o sistema dever� exibir a mensagem "Im�vel sem d�bitos."
			else{
				throw new ActionServletException("atencao.imovel_sem_debitos");
			}
		}
		
		//1. Caso n�o exista im�vel com a matr�cula informada [IT0003] Pesquisar Matr�cula do Im�vel
		else{
			throw new ActionServletException("atencao.pesquisa_inexistente",null,"Im�vel");
		}
		
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0004] Remover Im�vel
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward removerImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		String idARemover = httpServletRequest.getParameter("id");
		
		
		//1. O sistema dever� remover o registro selecionado da Lista de Im�veis e voltar ao passo 1 do fluxo principal
		Collection<RegistroImovelHelper> colecaoRegistroImovelHelper = form.getListaRegistroImovelHelper();
		for(Iterator<RegistroImovelHelper> it = colecaoRegistroImovelHelper.iterator();it.hasNext();){
			RegistroImovelHelper helper = it.next();
			if(helper.getIdColecao().equals(idARemover)){
				
				//Caso o registro selecionado seja o mesmo que foi removido
				if(idARemover.equals(form.getIdRegistroPrincipal()))
					form.setIdRegistroPrincipal(null);
				
				it.remove();
				break;
			}
		}
		if(colecaoRegistroImovelHelper.size() == 0){
			this.getSessao(httpServletRequest).removeAttribute("bloquearCliente");
			this.getSessao(httpServletRequest).removeAttribute("bloquearImovel");
		}
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	
	//M�TODOS AUXILIARRES
	//==============================================================================================
	
	/*
	 * M�todo auxiliar de compara��o de per�odos de refer�ncia
	 */
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
	
	
	/*
	 * M�todo auxiliar que verica se um inteiro pertence � uma cole��o de inteiros
	 */
	private boolean contemImovel(Collection<Integer> colecaoImoveisJaAdicionados, Integer idImovel){
		for(Iterator<Integer> it = colecaoImoveisJaAdicionados.iterator();it.hasNext();){
			Integer id = it.next();
			if(id.intValue() == idImovel.intValue())
				return true;
		}
		return false;
	}
	
	/*
	 * M�todo auxiliar que verica se um inteiro pertence � uma cole��o de RegistroImovelHelper
	 */
	private boolean contemImovelRegistroImovelHelper(Collection<RegistroImovelHelper> colecaoImoveisJaAdicionados, Integer idImovel){
		for(Iterator<RegistroImovelHelper> it = colecaoImoveisJaAdicionados.iterator();it.hasNext();){
			RegistroImovelHelper helper = it.next();
			if(helper.getIdImovel().intValue() == idImovel.intValue())
				return true;
		}
		return false;
	}
	
	/*
	 * 
	 */
	private void adicionarHelperAoRegistro(
			Collection<RegistroImovelHelper> colecaoRegistroImovelHelper,
			ContaValoresHelper helper) {
		for(Iterator<RegistroImovelHelper> it = colecaoRegistroImovelHelper.iterator();it.hasNext();){
			RegistroImovelHelper rih = it.next();
			if(rih.getIdImovel().intValue() == helper.getConta().getImovel().getId().intValue()){
				rih.addHelper(helper);
				break;
			}
		}
		
	}
	//==============================================================================================
	
}
