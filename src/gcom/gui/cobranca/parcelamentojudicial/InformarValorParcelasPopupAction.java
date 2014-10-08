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
import java.util.Calendar;
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
 * [SB0029] Concluir Informar Lista de Parcelas 
 * 
 * @author Hugo Azevedo
 * @date 26/03/2013
 */
public class InformarValorParcelasPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
	
		EfetuarParcelamentoJudicialActionForm form = (EfetuarParcelamentoJudicialActionForm) actionForm;
		
		//[FE0013] Verificar Lista de Parcelas Informada
		this.verificarListaParcelasInformada(form);
		
		//1. Quantidade de Parcelas da 4ª aba = Quantidade de Parcelas informada no pop-up
		this.getSessao(request).setAttribute("qtdParcelas", form.getQtdParcelasInformar());
		
		//2. Valor Parcelado Final = Valor do Acordo
		BigDecimal valorParceladoFinal = Util.formatarMoedaRealparaBigDecimal(form.getValorAcordo());
		
		//3. Data de Vencimento = Data de Vencimento 1ª Parcela
		Date dataVencimento = Util.converteStringParaDate(form.getDataVencimentoPrimeiraParcela());
		
		//Valores auxiliares
		Integer diaVencimentoParcelas = new Integer(form.getDiaVencimentoParcelas());
		BigDecimal percentualDesconto = Util.formatarMoedaRealparaBigDecimal(form.getPercentualDesconto());
		BigDecimal valorDebito = Util.formatarMoedaRealparaBigDecimal(form.getValorDebito());
		BigDecimal valorAcordo = Util.formatarMoedaRealparaBigDecimal(form.getValorAcordo());
		
		//4. O sistema deverá criar uma Lista de Parcelas 
		//   com a quantidade de itens igual a Quantidade de Parcelas informada.
		form.setListaParcelaJudicial(null);
		Collection<ParcelaJudicialHelper> listaParcelaJudicial = new ArrayList<ParcelaJudicialHelper>();
		Collection<ParcelaJudicialHelper> listaParcelaJudicialInformar = form.getListaParcelaJudicialInformar();
		
		//5. Para cada item da Lista de Parcelas Informada, o sistema deverá adicionar um item na Lista de Parcelas
		for(Iterator<ParcelaJudicialHelper> it = listaParcelaJudicialInformar.iterator();it.hasNext();){
			ParcelaJudicialHelper helper = it.next();
			ParcelaJudicialHelper adicionar = new ParcelaJudicialHelper();
			
			//Número da Parcela
			adicionar.setNumeroParcela(helper.getNumeroParcela());
			
			//Data de Vencimento
			//Caso o Número da Parcela seja igual a 1, 
			//o sistema deverá atribuir Data de Vencimento = Data de Vencimento
			if(helper.getNumeroParcela().intValue() == 1)
				adicionar.setDataVencimento(dataVencimento);
			
			//caso contrário, o sistema deverá atribuir
			//Data de Vencimento = data com o dia igual ao Dia do vencimento das Parcelas e com o mês 
			//imediatamente posterior ao mês da Data de Vencimento da parcela anterior
			else{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataVencimento);
				calendar.add(Calendar.MONTH, 1);
				
				//Verificar se o dia passado é maior do que o último dia do mês seguinte. Caso verdadeiro,
				//definir o dia da data de vencimento como o último dia do mês
				if(diaVencimentoParcelas >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
					dataVencimento = Util.criarData(calendar.getActualMaximum(Calendar.DAY_OF_MONTH), Util.getMes(dataVencimento) + 1, Util.getAno(dataVencimento));
				else
					dataVencimento = Util.criarData(diaVencimentoParcelas, Util.getMes(dataVencimento) + 1, Util.getAno(dataVencimento));
				
				adicionar.setDataVencimento(dataVencimento);
			}
			
			//Valor Parcela Final
			adicionar.setValorParcelaFinal(helper.getValorParcelaFinal());
			
			//Valor Parcela Descontos
			//Caso o Percentual de Desconto seja maior do que 0,00 (zero)
			if(percentualDesconto.compareTo(new BigDecimal("0.00")) > 0){
				
				//((Valor do Débito - Valor do Acordo) * Valor da Parcela) / Valor do Acordo
				BigDecimal resultado = valorDebito
											.subtract(valorAcordo)
											.multiply(helper.getValorParcelaFinal())
											.divide(valorAcordo,2,BigDecimal.ROUND_DOWN);
				adicionar.setValorParcelaDescontos(resultado);
			}
			else
				adicionar.setValorParcelaDescontos(new BigDecimal("0.00"));
			
			//Valor Parcela Bruto
			//Valor da Parcela + Valor Parcela Descontos
			adicionar.setValorParcelaBruto(adicionar.getValorParcelaFinal().add(adicionar.getValorParcelaDescontos()));
			
			//Valor Parcela Custas
			adicionar.setValorParcelaCustas(new BigDecimal("0.00"));
			
			//Valor Parcela Honorários
			adicionar.setValorParcelaHonorarios(new BigDecimal("0.00"));
			
			//Valor Parcela Juros
			adicionar.setValorParcelaJuros(new BigDecimal("0.00"));
			
			listaParcelaJudicial.add(adicionar);
		}
		
		form.setListaParcelaJudicial(listaParcelaJudicial);
		
		//atualizar com o Valor Parcelado Final
		this.getSessao(request).setAttribute("valorParcelado", Util.formatarMoedaReal(valorParceladoFinal));
		
		//Limpando campos do formulário
		form.setQtdParcelasInformar(null);
		form.setParcelaFinal(null);
		form.setParcelaInicial(null);
		form.setValorParcelaInformar(null);
		form.setListaParcelaJudicialInformar(null);
		form.setTotalParcelasInformar("0.00");
		
		
		request.setAttribute("reexibir", "true");
		ActionForward retorno = mapping.findForward("concluir");
		
		return retorno;
	}

	
	/**
	 * 
	 * [UC1441] Efetuar Parcelamento Judicial
	 * [FE0013] Verificar Lista de Parcelas Informada
	 * 
	 * @author Hugo Azevedo
	 * @date 01/04/2013
	 */
	private void verificarListaParcelasInformada(
			EfetuarParcelamentoJudicialActionForm form) {
		
		//Quantidade de Parcelas
		if(!Util.verificarNaoVazio(form.getQtdParcelasInformar())){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Quantidade de Parcelas");
		}	
		
		//1. Caso a Quantidade de Parcelas possua valor igual a zero ("0")
		if(form.getQtdParcelasInformar() == null || new BigDecimal("0").compareTo(Util.formatarMoedaRealparaBigDecimal(form.getQtdParcelasInformar())) == 0){
			throw new ActionServletException("atencao.invalido.matricula",null,"Quantidade de Parcelas");
		}
		
		//2. Caso a quantidade de parcelas da Lista de Parcelas Informada seja diferente da Quantidade de Parcelas
		if(form.getListaParcelaJudicialInformar() != null && 
				Util.converterStringParaInteger(form.getQtdParcelasInformar()).intValue() != form.getListaParcelaJudicialInformar().size()){
			throw new ActionServletException("atencao.qtd_parc_dif_inf");
		}
		
		//3. Caso o Total das Parcelas Informadas seja diferente do Valor Parcelado
		if(form.getSomatorioParcelasAInformar().compareTo(Util.formatarMoedaRealparaBigDecimal(form.getValorAcordo())) != 0){
			throw new ActionServletException("atencao.valor_parc_dif_total");
		}
		
	}
}
