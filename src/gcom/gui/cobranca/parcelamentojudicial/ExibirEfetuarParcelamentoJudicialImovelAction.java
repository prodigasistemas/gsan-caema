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
	 * [SB0008] Pesquisar Cliente Usuário
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
		
		//Validando período de referência		
		this.validarReferenciaInicialMenorFinal(amReferenciaInicial,amReferenciaFinal,"Referência","atencao.referencia_final_anterior_referencia_inicial");
		
		amReferenciaInicial = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaInicial);
		amReferenciaFinal = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaFinal);
		
		//1. O sistema deverá remover todos os registros da Lista de Imóveis
		Collection<RegistroImovelHelper> colecaoRegistroImovelHelper = new ArrayList<RegistroImovelHelper>(); 
		form.setListaRegistroImovelHelper(null);
		
		//2. Caso exista cliente com o código informado
		//   [IT0001] Pesquisar Cliente
		Cliente cliente = this.getFachada().obterCliente(idCliente);
		if(cliente != null){
			
			//2.1. O sistema deverá exibir o nome do cliente no campo TextBox 
			//     referente a Descrição do Cliente Usuário
			//     [IT0002] Pesquisar Nome do Cliente
			form.setDescClienteUsuario(cliente.getNome());
			
			//2.2. O sistema deverá pesquisar a lista de contas associadas ao cliente
			//     passando os seguintes parâmetros:
			//     [UC0067]
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = this.getFachada()
					.obterDebitoImovelOuCliente(
							2,                     //Indicador de débito do imóvel ou cliente (com valor igual a 2);
							null,                  //Matrícula do Imóvel (com valor nulo);
							idCliente,             //Código do cliente (código do cliente usuário informado);
							new Short("2"),        //Tipo de relação do cliente com o imóvel (com valor igual a 2);
							amReferenciaInicial,   //Período de referência do débito 
							amReferenciaFinal,     //Período de referência do débito 
							Util.converteStringParaDate("01/01/0001"), //Período de vencimento do débito
							new Date(),            //Período de vencimento do débito
							1,                     //Indicador de pagamento (com valor igual a 1); 
							1,                     //Indicador de conta em revisão (com valor igual a 1); 
							2,                     //Indicador de débito a cobrar (com valor igual a 2); 
							2,                     //Indicador de crédito a realizar (com valor igual a 2);
							1,					   //Indicador de notas promissórias (com valor igual a 1)
							2,                     //Indicador de guias de pagamento (com valor igual a 2);
							1,                     //Indicador de atualizar débito (com valor igual a 1)
							null);
			
			//2.3. Caso exista alguma conta em aberto para o cliente (existir algum registro na lista de contas)
			if(colecaoDebitoImovel != null 
					&& colecaoDebitoImovel.getColecaoContasValores() != null 
					&& colecaoDebitoImovel.getColecaoContasValores().size() > 0){
				
				//2.3.1. Para cada imóvel selecionado o sistema deverá adicionar um registro à Lista de Imóveis
				Collection<Integer> colecaoImoveisJaAdicionados = new ArrayList<Integer>();
				for(Iterator<ContaValoresHelper> it = colecaoDebitoImovel.getColecaoContasValores().iterator();
						it.hasNext();){
					
					ContaValoresHelper helper = it.next();
					
					//Caso o imóvel ainda não tenha sido adicionado
					if(helper.getConta() != null && !contemImovel(colecaoImoveisJaAdicionados, helper.getConta().getImovel().getId())){
						
						//IT0006 - Obter Dados Registro Imóvel
						colecaoImoveisJaAdicionados.add(helper.getConta().getImovel().getId());
						Collection<RegistroImovelHelper> colecaoRegistroHelper = this.getFachada().obterDadosRegistroImovel(helper.getConta().getImovel().getId());
						colecaoRegistroImovelHelper.addAll(colecaoRegistroHelper);
					}
					
					adicionarHelperAoRegistro(colecaoRegistroImovelHelper,helper);
				}
				form.setListaRegistroImovelHelper(colecaoRegistroImovelHelper);
				this.getSessao(httpServletRequest).setAttribute("bloquearImovel", "sim");
			}
			//2.4. Caso contrário, o sistema deverá exibir a mensagem "Cliente sem débitos."
			else{
				throw new ActionServletException("atencao.cliente_sem_debitos");
			}
		}
		//3. Caso contrário, o sistema deverá exibir "CLIENTE INEXISTENTE" em vermelho 
		//   no campo TextBox referente a Descrição do Cliente Usuário
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
	 * [SB0001] Limpar Cliente Usuário
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward limparClienteUsuario(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//O sistema deverá limpar todos os campos referentes a Cliente Usuário e voltar ao passo 1 do fluxo principal.
		form.setListaRegistroImovelHelper(null);
		form.setIdClienteUsuario("");
		form.setDescClienteUsuario("");
		this.getSessao(httpServletRequest).removeAttribute("bloquearImovel");
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0011] Pesquisar Imóvel
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
		
		//1. Caso exista imóvel com a matrícula informada 
		//   [IT0003] Pesquisar Matrícula do Imóvel
		Imovel imovel = this.getFachada().obterImovel(Integer.parseInt(idImovel));
		if(imovel != null){
			
			//1.1. O sistema deverá exibir a inscrição do imóvel
			//     no campo TextBox referente a Descrição do Imóvel; 
			//     [IT0003] Pesquisar Matrícula do Imóvel
			String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(imovel.getId());
			form.setDescImovel(inscricaoImovel);
		}
		//2. Caso contrário, o sistema deverá exibir 
		//   "MATRÍCULA INEXISTENTE" em vermelho no campo 
		//   TextBox referente a Descrição do Imóvel
		else{
			httpServletRequest.setAttribute("imovelInexistenteException", "sim");
			form.setIdImovel("");
			form.setDescImovel("MATRÍCULA INEXISTENTE");
		}
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0002] Limpar Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward limparImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//1. O sistema deverá limpar todos os campos referentes a Imóvel
		//   e voltar ao passo 1 do fluxo principal
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		form.setIdImovel("");
		form.setDescImovel("");
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0003] Adicionar Imóvel
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
		
		//Validando a existência do imóvel
		if(imovel != null){
			//Verificando se o imóvel já está na lista
			if(contemImovelRegistroImovelHelper(colecaoRegistroImovelHelper,Integer.parseInt(idImovel))){
				throw new ActionServletException("atencao.imovel_ja_adicionado");
			}
			
			if((amReferenciaInicial == null || amReferenciaInicial.equals("")) 
					&& (amReferenciaFinal == null || amReferenciaFinal.equals(""))){
				
				throw new ActionServletException("atencao.preencha_periodo_debibo");
			}
			
			//Validando período de referência		
			this.validarReferenciaInicialMenorFinal(amReferenciaInicial,amReferenciaFinal,"Referência","atencao.referencia_final_anterior_referencia_inicial");
			
			amReferenciaInicial = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaInicial);
			amReferenciaFinal = Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaFinal);
			
			//1. O sistema deverá pesquisar a lista de contas associadas ao imóvel
			//   [SB0013] Obter Débitos do Imóvel
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = this.getFachada()
					.obterDebitoImovelOuCliente(
							1,                   //Indicador de débito do imóvel ou cliente (com valor igual a 1);
							idImovel,            //Matrícula do Imóvel (código do imóvel informado);
							null,                //Código do cliente (com valor nulo);
							null,                //Tipo de relação do cliente com o imóvel (com valor igual a 2);
							amReferenciaInicial, //Período de referência do débito 
							amReferenciaFinal,   //Período de referência do débito 
							Util.converteStringParaDate("01/01/0001"), //Período de vencimento do débito
							new Date(),          //Período de vencimento do débito
							1,                   //Indicador de pagamento (com valor igual a 1); 
							1,                   //Indicador de conta em revisão (com valor igual a 1); 
							2,                   //Indicador de débito a cobrar (com valor igual a 2); 
							2,                   //Indicador de crédito a realizar (com valor igual a 2);
							1,					 //Indicador de notas promissórias (com valor igual a 1)
							2,                   //Indicador de guias de pagamento (com valor igual a 2);
							1,                   //Indicador de atualizar débito (com valor igual a 1)
							null);
			
			//2. Caso exista alguma conta em aberto para o imóvel (existir algum registro na lista de contas obtida)
			if(colecaoDebitoImovel != null 
					&& colecaoDebitoImovel.getColecaoContasValores() != null 
					&& colecaoDebitoImovel.getColecaoContasValores().size() > 0){
				
				//2. o sistema deverá adicionar um registro à Lista de Imóveis, 
				//   passando como parâmetro o Código do Imóvel informado
				Iterator iColecaoContas = colecaoDebitoImovel.getColecaoContasValores().iterator();
				
				while (iColecaoContas.hasNext()){
					ContaValoresHelper helper = (ContaValoresHelper) iColecaoContas.next();
					if(helper != null){
						
						if(helper.getConta() != null && !contemImovelRegistroImovelHelper(colecaoRegistroImovelHelper,helper.getConta().getImovel().getId())){
							
							//IT0006 - Obter Dados Registro Imóvel
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
			//3. Caso contrário, o sistema deverá exibir a mensagem "Imóvel sem débitos."
			else{
				throw new ActionServletException("atencao.imovel_sem_debitos");
			}
		}
		
		//1. Caso não exista imóvel com a matrícula informada [IT0003] Pesquisar Matrícula do Imóvel
		else{
			throw new ActionServletException("atencao.pesquisa_inexistente",null,"Imóvel");
		}
		
		
		return actionMapping.findForward("efetuarParcelamentoJudicialImovelAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0004] Remover Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 15/03/2013
	 */
	public ActionForward removerImovel(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		String idARemover = httpServletRequest.getParameter("id");
		
		
		//1. O sistema deverá remover o registro selecionado da Lista de Imóveis e voltar ao passo 1 do fluxo principal
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
	
	
	//MÉTODOS AUXILIARRES
	//==============================================================================================
	
	/*
	 * Método auxiliar de comparação de períodos de referência
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
	 * Método auxiliar que verica se um inteiro pertence à uma coleção de inteiros
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
	 * Método auxiliar que verica se um inteiro pertence à uma coleção de RegistroImovelHelper
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
