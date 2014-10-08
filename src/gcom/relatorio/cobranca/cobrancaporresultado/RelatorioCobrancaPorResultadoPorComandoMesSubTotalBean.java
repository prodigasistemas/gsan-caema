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
* Genival Soares Barbosa Filho
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
package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.math.BigDecimal;

/**
 * [UC0000] Relatório Opção Totalização por Comando e Opção de Totalização por Mês de Apuração
 * 
 * @author Vivianne Sousa
 * @date 28/04/2014
 */
public class RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean implements RelatorioBean,Comparable<RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean> {	

	
	private Integer idFaixaContas;
	private String descFaixaContas;
	private Integer qtdContas;	
	private Integer qtdeClientes;
	private BigDecimal vlPagtoAVista;		
	private BigDecimal vlPagtoParcelado;
	private BigDecimal vlPagtoTotal;
	private BigDecimal percentualFaixa;
	private BigDecimal vlPagtoAVistaPrestadora;		
	private BigDecimal vlPagtoParceladoPrestadora;
	private BigDecimal vlPagtoTotalPrestadora;
	private BigDecimal vlDesconto;			
	private BigDecimal vlTotalDivida;	
	private Integer qtdePagtoAVista;		
	private Integer qtdePagtoParcelado;	
	private Integer qtdePagtoTotal;	
	private String anoMesRef;
	private String msgTotal;

	public Integer getIdFaixaContas() {
		return idFaixaContas;
	}
	public void setIdFaixaContas(Integer idFaixaContas) {
		this.idFaixaContas = idFaixaContas;
	}
	public String getDescFaixaContas() {
		return descFaixaContas;
	}
	public void setDescFaixaContas(String descFaixaContas) {
		this.descFaixaContas = descFaixaContas;
	}
	public Integer getQtdContas() {
		return qtdContas;
	}
	public void setQtdContas(Integer qtdContas) {
		this.qtdContas = qtdContas;
	}
	public Integer getQtdeClientes() {
		return qtdeClientes;
	}
	public void setQtdeClientes(Integer qtdeClientes) {
		this.qtdeClientes = qtdeClientes;
	}
	public BigDecimal getVlPagtoAVista() {
		return vlPagtoAVista;
	}
	public void setVlPagtoAVista(BigDecimal vlPagtoAVista) {
		this.vlPagtoAVista = vlPagtoAVista;
	}
	public BigDecimal getVlPagtoParcelado() {
		return vlPagtoParcelado;
	}
	public void setVlPagtoParcelado(BigDecimal vlPagtoParcelado) {
		this.vlPagtoParcelado = vlPagtoParcelado;
	}
	public BigDecimal getVlPagtoTotal() {
		return vlPagtoTotal;
	}
	public void setVlPagtoTotal(BigDecimal vlPagtoTotal) {
		this.vlPagtoTotal = vlPagtoTotal;
	}
	public BigDecimal getPercentualFaixa() {
		return percentualFaixa;
	}
	public void setPercentualFaixa(BigDecimal percentualFaixa) {
		this.percentualFaixa = percentualFaixa;
	}
	public BigDecimal getVlPagtoAVistaPrestadora() {
		return vlPagtoAVistaPrestadora;
	}
	public void setVlPagtoAVistaPrestadora(BigDecimal vlPagtoAVistaPrestadora) {
		this.vlPagtoAVistaPrestadora = vlPagtoAVistaPrestadora;
	}
	public BigDecimal getVlPagtoParceladoPrestadora() {
		return vlPagtoParceladoPrestadora;
	}
	public void setVlPagtoParceladoPrestadora(BigDecimal vlPagtoParceladoPrestadora) {
		this.vlPagtoParceladoPrestadora = vlPagtoParceladoPrestadora;
	}
	public BigDecimal getVlPagtoTotalPrestadora() {
		return vlPagtoTotalPrestadora;
	}
	public void setVlPagtoTotalPrestadora(BigDecimal vlPagtoTotalPrestadora) {
		this.vlPagtoTotalPrestadora = vlPagtoTotalPrestadora;
	}
	public BigDecimal getVlDesconto() {
		return vlDesconto;
	}
	public void setVlDesconto(BigDecimal vlDesconto) {
		this.vlDesconto = vlDesconto;
	}
	public BigDecimal getVlTotalDivida() {
		return vlTotalDivida;
	}
	public void setVlTotalDivida(BigDecimal vlTotalDivida) {
		this.vlTotalDivida = vlTotalDivida;
	}
	public String getMsgTotal() {
		return msgTotal;
	}
	public void setMsgTotal(String msgTotal) {
		this.msgTotal = msgTotal;
	}
	public int compareTo(RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean r1){
		
		Integer r1AnoMesRef =  0;
		Integer anoMesRef = 0;
		
		if(!r1.getAnoMesRef().equals("0")){
			r1AnoMesRef =  Util.formatarMesAnoComBarraParaAnoMes(r1.getAnoMesRef());
		}
		if(!this.getAnoMesRef().equals("0")){
			anoMesRef = Util.formatarMesAnoComBarraParaAnoMes(this.getAnoMesRef());
		}
		
		int retorno = anoMesRef.compareTo(r1AnoMesRef);
		
		if(retorno != 0 ){
			return retorno;
		}
		
		return this.getIdFaixaContas().compareTo(r1.getIdFaixaContas());
	}
	
	public Integer getQtdePagtoAVista() {
		return qtdePagtoAVista;
	}
	public void setQtdePagtoAVista(Integer qtdePagtoAVista) {
		this.qtdePagtoAVista = qtdePagtoAVista;
	}
	public Integer getQtdePagtoParcelado() {
		return qtdePagtoParcelado;
	}
	public void setQtdePagtoParcelado(Integer qtdePagtoParcelado) {
		this.qtdePagtoParcelado = qtdePagtoParcelado;
	}
	public String getAnoMesRef() {
		return anoMesRef;
	}
	public void setAnoMesRef(String anoMesRef) {
		this.anoMesRef = anoMesRef;
	}
	public Integer getQtdePagtoTotal() {
		return qtdePagtoTotal;
	}
	public void setQtdePagtoTotal(Integer qtdePagtoTotal) {
		this.qtdePagtoTotal = qtdePagtoTotal;
	}
	
}
