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
package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterTipoDebitoBean implements RelatorioBean {
	
	private String codigo;
	
	private String descricao;

	private String descricaoAbreviada;

	private String lancamentoItemContabil;

	private String financiamentoTipo;

	private String indicadorGeracaoDebitoAutomatica;

	private String indicadorGeracaoDebitoConta;

	private String indicadorUso;

	private String valorLimite;

	private String valorSugerido;

	/**
	 * Construtor da classe RelatorioManterBaciaBean
	 * 
	 * @param id
	 *            Descri��o do par�metro
	 * @param descricao
	 *            Descri��o do par�metro
	 * @param sistemaEsgoto
	 *            Descri��o do par�metro
	 */

	public RelatorioManterTipoDebitoBean(String codigo, String descricao, String descricaoAbreviada, 
			String lancamentoItemContabil, String financiamentoTipo, String indicadorGeracaoDebitoAutomatica,
			String indicadorGeracaoDebitoConta, String indicadorUso, String valorLimite, String valorSugerido ){
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.financiamentoTipo = financiamentoTipo;
		this.indicadorGeracaoDebitoAutomatica = indicadorGeracaoDebitoAutomatica;
		this.indicadorGeracaoDebitoConta = indicadorGeracaoDebitoConta;
		this.indicadorUso = indicadorUso;
		this.valorLimite = valorLimite;
		this.valorSugerido = valorSugerido;
	
		
	}
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getFinanciamentoTipo() {
		return financiamentoTipo;
	}

	public void setFinanciamentoTipo(String financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public String getIndicadorGeracaoDebitoAutomatica() {
		return indicadorGeracaoDebitoAutomatica;
	}

	public void setIndicadorGeracaoDebitoAutomatica(
			String indicadorGeracaoDebitoAutomatica) {
		this.indicadorGeracaoDebitoAutomatica = indicadorGeracaoDebitoAutomatica;
	}

	public String getIndicadorGeracaoDebitoConta() {
		return indicadorGeracaoDebitoConta;
	}

	public void setIndicadorGeracaoDebitoConta(String indicadorGeracaoDebitoConta) {
		this.indicadorGeracaoDebitoConta = indicadorGeracaoDebitoConta;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(String lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}


	public String getValorLimite() {
		return valorLimite;
	}

	public void setValorLimite(String valorLimite) {
		this.valorLimite = valorLimite;
	}

	public String getValorSugerido() {
		return valorSugerido;
	}

	public void setValorSugerido(String valorSugerido) {
		this.valorSugerido = valorSugerido;
	}
}