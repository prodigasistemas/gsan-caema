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

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.parcelamentojudicial.bean.ParcelaJudicialHelper;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1441] Efetuar Parcelamento Judicial
 * [SB0023] Informar Valor Parcelas
 * 
 * @author Hugo Azevedo
 * @date 28/03/2013
 */

public class ExibirInformarValorParcelasPopupAction extends GcomAction {
	
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//Recuperar valores que foram passados via GET
		String dataVencimentoPrimeiraParcela = (String)httpServletRequest.getParameter("dataVencimentoPrimeiraParcela");
		String diaVencimentoParcelas = (String)httpServletRequest.getParameter("diaVencimentoParcelas");
		
		Date dataVencimento = Util.converteStringParaDate(dataVencimentoPrimeiraParcela);
		
		//Caso a data de vencimento da primeira parcela seja menor do que a data corrente
		if(Util.compararData(dataVencimento, new Date()) < 0){
			throw new ActionServletException("atencao.data_venc_menor_corrente");
		}
		
		form.setDataVencimentoPrimeiraParcela(dataVencimentoPrimeiraParcela);
		form.setDiaVencimentoParcelas(diaVencimentoParcelas);
		
		return actionMapping.findForward("informarValorParcelasPopupAction");
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0023] Informar Valor Parcelas
	 * [SB0026] Adicionar Parcela
	 * 
	 * @author Hugo Azevedo
	 * @date 01/04/2013
	 */
	public ActionForward adicionarParcela(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//[FE0012] Verificar Informações das Parcelas
		this.validarInformacoesParcelas(form);
		
		//1. O sistema deverá adicionar à Lista de Parcelas Informadas registros 
		//   com a quantidade de itens igual a quantidade total do intervalo de Parcela (inicial e final)
		Integer parcelaInicial = Integer.parseInt(form.getParcelaInicial());
		Integer parcelaFinal = Integer.parseInt(form.getParcelaFinal());
		
		Collection<ParcelaJudicialHelper> listaParcelaJudicial;
		if(form.getListaParcelaJudicialInformar() == null)
			listaParcelaJudicial = new ArrayList<ParcelaJudicialHelper>();
		else
			listaParcelaJudicial = form.getListaParcelaJudicialInformar();
		
		for(int i = parcelaInicial; i <= parcelaFinal; i++){
			ParcelaJudicialHelper helper = new ParcelaJudicialHelper();
			
			//1.1. Parcela - número da parcela informado no intervalo de Parcela
			helper.setNumeroParcela(i);
			BigDecimal valorFormatado = Util.formatarMoedaRealparaBigDecimal(form.getValorParcelaInformar());
			BigDecimal total = Util.formatarMoedaRealparaBigDecimal(form.getTotalParcelasInformar());
			total = total.add(valorFormatado);
			form.setTotalParcelasInformar(Util.formatarMoedaReal(total));
			
			//1.2. Valor da Parcela - Valor da Parcela informado
			helper.setValorParcelaFinal(valorFormatado);
			listaParcelaJudicial.add(helper);
		}
		
		form.setListaParcelaJudicialInformar(listaParcelaJudicial);
		
		return actionMapping.findForward("informarValorParcelasPopupAction");
	}

	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0023] Informar Valor Parcelas
	 * [SB0027] Remover Parcela
	 * 
	 * @author Hugo Azevedo
	 * @date 01/04/2013
	 */
	public ActionForward removerParcela(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//1. O sistema deverá remover o registro selecionado da Lista de Parcelas Informadas
		String idParcelaSelecionada = (String)httpServletRequest.getParameter("id");
		if(Util.verificarIdNaoVazio(idParcelaSelecionada)){
			for(Iterator<ParcelaJudicialHelper> it = form.getListaParcelaJudicialInformar().iterator();it.hasNext();){
				ParcelaJudicialHelper helper = it.next();
				if(helper.getNumeroParcela().toString().equals(idParcelaSelecionada)){
					
					BigDecimal total = Util.formatarMoedaRealparaBigDecimal(form.getTotalParcelasInformar());
					total = total.subtract(helper.getValorParcelaFinal());
					form.setTotalParcelasInformar(Util.formatarMoedaReal(total));
					it.remove();
					break;
				}
			}
		}
	
		return actionMapping.findForward("informarValorParcelasPopupAction"); 
	}
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0023] Informar Valor Parcelas
	 * [SB0026] Adicionar Parcela
	 * 
	 * @author Hugo Azevedo
	 * @date 01/04/2013
	 */
	public ActionForward desfazerParcelasInformadas(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm)actionForm;
		
		//1. O sistema deverá limpar todos os campos e voltar ao estado inicial
		form.setQtdParcelasInformar(null);
		form.setParcelaFinal(null);
		form.setParcelaInicial(null);
		form.setValorParcelaInformar(null);
		form.setListaParcelaJudicialInformar(null);
		form.setTotalParcelasInformar("0.00");
		
		return actionMapping.findForward("informarValorParcelasPopupAction");
	}
	
	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [SB0023] Informar Valor Parcelas
	 * [FE0012] Verificar Informações das Parcelas
	 * 
	 * @author Hugo Azevedo
	 * @date 01/04/2013
	 */
	private void validarInformacoesParcelas(
			EfetuarParcelamentoJudicialActionForm form) {
		
		//1. Caso não tenha sido informado algum dos campos obrigatórios referentes Pop-up,
		//   o sistema deverá exibir a mensagem "Informe <<nome do campo obrigatório que não foi informado>>."
		//Quantidade de Parcelas
		if(!Util.verificarNaoVazio(form.getQtdParcelasInformar())){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Quantidade de Parcelas");
		}
		
		//Parcela Inicial
		if(!Util.verificarNaoVazio(form.getParcelaInicial())){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Parcela Inicial");
		}
		
		//Parcela Final
		if(!Util.verificarNaoVazio(form.getParcelaFinal())){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Parcela Final");
		}
		
		//Valor da Parcela
		if(!Util.verificarNaoVazio(form.getValorParcelaInformar())){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Valor da Parcela");
		}
		
		Integer qtdParcelas = Integer.parseInt(form.getQtdParcelasInformar());
		Integer parcelaInicial = Integer.parseInt(form.getParcelaInicial());
		Integer parcelaFinal = Integer.parseInt(form.getParcelaFinal());
		BigDecimal valorParcela = Util.formatarMoedaRealparaBigDecimal(form.getValorParcelaInformar());
		BigDecimal valorAcordo = Util.formatarMoedaRealparaBigDecimal(form.getValorAcordo());
		Collection<ParcelaJudicialHelper> listaParcelaJudicialInformar = form.getListaParcelaJudicialInformar();
		ParcelaJudicialHelper ultimoElemento = null;
		Integer ultimaParcela = 0;
		if(listaParcelaJudicialInformar != null && listaParcelaJudicialInformar.size() > 0){
			ultimoElemento = (ParcelaJudicialHelper)listaParcelaJudicialInformar.toArray()[listaParcelaJudicialInformar.size() -1];
			ultimaParcela = ultimoElemento.getNumeroParcela();
		}
		
		//2. Caso a Quantidade de Parcelas possua valor igual a zero ("0")
		if(qtdParcelas.intValue() == 0){
			throw new ActionServletException("atencao.invalido.matricula",null,"Quantidade de Parcelas");
		}
		
		//3. Caso a Parcela Final for menor do que a Parcela Inicial
		if(parcelaFinal.intValue() < parcelaInicial.intValue()){
			throw new ActionServletException("atencao.parc_final_menor_inicial");
		}
		
		//4. Caso a Parcela Final seja maior do que a Quantidade de Parcelas informada
		if(parcelaFinal.intValue() > qtdParcelas.intValue()){
			throw new ActionServletException("atencao.parc_final_maior_qtd_parcs");
		}
		
		//5. Caso a Parcela Inicial possua valor igual a zero ("0") 
		//   ou a Parcela Inicial não seja imediatamente posterior a maior Parcela da Lista de Parcelas Informadas
		if(parcelaInicial.intValue() == 0 || parcelaInicial.intValue() != ultimaParcela.intValue() + 1){
			throw new ActionServletException("atencao.invalido.matricula",null,"Parcela Inicial");
		}
		
		//7. Caso o Valor da Parcela possua valor igual a zero ("0,00")
		if(valorParcela.compareTo(new BigDecimal("0")) == 0){
			throw new ActionServletException("atencao.elemento_invalido",null,"Valor da Parcela");
		}
		
		//8. Caso o valor das parcelas (Total das Parcelas Informadas + (Valor da Parcela * quantidade total do intervalo de Parcelas)) 
		//  seja maior do que o Valor Parcelado
		BigDecimal parcelasInseridas = valorParcela.multiply(new BigDecimal(parcelaFinal.intValue() - parcelaFinal.intValue() + 1)).add(form.getSomatorioParcelasAInformar());
		if(parcelasInseridas.compareTo(valorAcordo) > 0){
			throw new ActionServletException("atencao.valor_parc_n_corresponde_total_parc",Util.formatarMoedaReal(parcelasInseridas),form.getValorAcordo());
		}
	}
	
}
