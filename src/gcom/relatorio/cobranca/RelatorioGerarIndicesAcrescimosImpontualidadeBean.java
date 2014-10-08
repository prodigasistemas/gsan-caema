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
package gcom.relatorio.cobranca;

import java.math.BigDecimal;

import gcom.cobranca.IndicesAcrescimosImpontualidade;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * [UC ]
 * 
 * @author S�vio Luiz
 * @date 27/08/2007
 */
public class RelatorioGerarIndicesAcrescimosImpontualidadeBean implements
		RelatorioBean {

	private String mesAnoReferencia;

	private String percentualMulta;

	private String percentualJurosMora;

	private String fatorAtualizacaoMonetaria;

	private String percentualAtualizacao;

	public RelatorioGerarIndicesAcrescimosImpontualidadeBean(
			IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade,
			BigDecimal fatorAtualizacaoMonetariaAnterior) {
		this.mesAnoReferencia = Util
				.formatarAnoMesParaMesAno(indicesAcrescimosImpontualidade
						.getAnoMesReferencia());
		this.percentualMulta = Util
				.formatarMoedaReal(indicesAcrescimosImpontualidade
						.getPercentualMulta());
		this.percentualJurosMora = Util
				.formatarMoedaReal(indicesAcrescimosImpontualidade
						.getPercentualJurosMora());
		this.fatorAtualizacaoMonetaria = Util
				.formatarMoedaReal4Casas(indicesAcrescimosImpontualidade
						.getFatorAtualizacaoMonetaria());
		this.percentualAtualizacao = calcularPercentualAtualizacao(
				indicesAcrescimosImpontualidade,
				fatorAtualizacaoMonetariaAnterior);

	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getPercentualJurosMora() {
		return percentualJurosMora;
	}

	public void setPercentualJurosMora(String percentualJurosMora) {
		this.percentualJurosMora = percentualJurosMora;
	}

	public String getPercentualMulta() {
		return percentualMulta;
	}

	public void setPercentualMulta(String percentualMulta) {
		this.percentualMulta = percentualMulta;
	}

	public String getFatorAtualizacaoMonetaria() {
		return fatorAtualizacaoMonetaria;
	}

	public void setFatorAtualizacaoMonetaria(String fatorAtualizacaoMonetaria) {
		this.fatorAtualizacaoMonetaria = fatorAtualizacaoMonetaria;
	}

	public String getPercentualAtualizacao() {
		return percentualAtualizacao;
	}

	public void setPercentualAtualizacao(String percentualAtualizacao) {
		this.percentualAtualizacao = percentualAtualizacao;
	}

	public String calcularPercentualAtualizacao(
			IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade,
			BigDecimal fatorAtualizacaoMonetariaAnterior) {
		String percentualAtualizacaoCalculado = "";

		BigDecimal fatorAtualizacaoCalculado = new BigDecimal("0.00");
		if (fatorAtualizacaoMonetariaAnterior.compareTo(BigDecimal.ZERO) != 0) {
			if (indicesAcrescimosImpontualidade.getFatorAtualizacaoMonetaria() != null) {
				fatorAtualizacaoCalculado = indicesAcrescimosImpontualidade
						.getFatorAtualizacaoMonetaria().divide(
								fatorAtualizacaoMonetariaAnterior, 4,
								BigDecimal.ROUND_HALF_UP);
			}
			fatorAtualizacaoCalculado = fatorAtualizacaoCalculado
					.subtract(new BigDecimal("1"));
			fatorAtualizacaoCalculado = fatorAtualizacaoCalculado
					.multiply(new BigDecimal("100"));
		}
		percentualAtualizacaoCalculado = Util
				.formatarMoedaReal(fatorAtualizacaoCalculado);

		return percentualAtualizacaoCalculado;

	}

}
