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
package gcom.gui.faturamento.consumotarifa.bean;

import gcom.faturamento.consumotarifa.ConsumoTarifaCategPpp;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategPpp;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixaPpp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Classe que ir� auxiliar para fazer a contagem do numero de faixa de
 * categorias
 * 
 * @author Tiago Moreno
 * @date 27/03/2006
 */
public class CategoriaFaixaConsumoTarifaPppHelper {

	private Integer quantidadesFaixa;

	private ConsumoTarifaCategPpp consumoTarifaCategPpp;

	private Collection<ConsumoTarifaFaixaPpp> colecaoFaixas = new ArrayList();

	public Collection<ConsumoTarifaFaixaPpp> getColecaoFaixas() {
		return colecaoFaixas;
	}

	public void setColecaoFaixas(Collection<ConsumoTarifaFaixaPpp> colecaoFaixas) {
		this.colecaoFaixas = colecaoFaixas;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof CategoriaFaixaConsumoTarifaPppHelper)) {
			return false;
		}
		CategoriaFaixaConsumoTarifaPppHelper castOther = (CategoriaFaixaConsumoTarifaPppHelper) other;

		return ((this.getConsumoTarifaCategPpp().getCategoria().getId().equals(castOther
				.getConsumoTarifaCategPpp().getCategoria().getId())));
	}

	public CategoriaFaixaConsumoTarifaPppHelper(Integer quantidadesFaixa,
			ConsumoTarifaCategPpp consumoTarifaCategPpp) {
		super();

		this.quantidadesFaixa = quantidadesFaixa;
		this.consumoTarifaCategPpp = consumoTarifaCategPpp;
	}

	public CategoriaFaixaConsumoTarifaPppHelper(Integer quantidadesFaixa,
			ConsumoTarifaCategPpp consumoTarifaCategPpp,
			Collection<ConsumoTarifaFaixaPpp> colecaoFaixas) {
		super();

		this.quantidadesFaixa = quantidadesFaixa;
		this.consumoTarifaCategPpp = consumoTarifaCategPpp;
		this.colecaoFaixas = colecaoFaixas;
	}

	public ConsumoTarifaCategPpp getConsumoTarifaCategPpp() {
		return consumoTarifaCategPpp;
	}

	public void setConsumoTarifaCategPpp(
			ConsumoTarifaCategPpp consumoTarifaCategPpp) {
		this.consumoTarifaCategPpp = consumoTarifaCategPpp;
	}

	public Integer getQuantidadesFaixa() {
		return quantidadesFaixa;
	}

	public void setQuantidadesFaixa(Integer quantidadesFaixa) {
		this.quantidadesFaixa = quantidadesFaixa;
	}

	public Date getUltimaAlteracao() {
		return this.consumoTarifaCategPpp.getUltimaAlteracao();
	}

	public Integer getId() {
		return this.consumoTarifaCategPpp.getId();

	}
}