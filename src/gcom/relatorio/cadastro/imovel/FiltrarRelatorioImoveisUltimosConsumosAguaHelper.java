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
package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC0731] Gerar Relat�rio de Im�veis com os Ultimos Consumos de Agua
 * 
 * @author Rafael Pinto
 * @date 18/12/2007
 */
public class FiltrarRelatorioImoveisUltimosConsumosAguaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;
	
	private Integer localidadeInicial;
	private Integer setorComercialInicial;
	private Integer rotaInicial;
	private Integer sequencialRotalInicial;

	private Integer localidadeFinal;
	private Integer setorComercialFinal;
	private Integer rotaFinal;
	private Integer sequencialRotalFinal;

	private Collection<Integer> situacaoLigacaoAgua;
	private Collection<Integer> categorias;
	private Collection<Integer> perfilImovel;
	
	private int anoMesSubtraido1;
	private int anoMesSubtraido2;
	private int anoMesSubtraido3;
	private int anoMesSubtraido4;
	private int anoMesSubtraido5;
	private int anoMesSubtraido6;
	private int anoMesSubtraido7;
	private int anoMesSubtraido8;
	private int anoMesSubtraido9;
	private int anoMesSubtraido10;
	private int anoMesSubtraido11;
	private int anoMesSubtraido12;
	
	
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Integer getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Integer rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Collection<Integer> getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(Collection<Integer> situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public Integer getSequencialRotalFinal() {
		return sequencialRotalFinal;
	}

	public void setSequencialRotalFinal(Integer sequencialRotalFinal) {
		this.sequencialRotalFinal = sequencialRotalFinal;
	}

	public Integer getSequencialRotalInicial() {
		return sequencialRotalInicial;
	}

	public void setSequencialRotalInicial(Integer sequencialRotalInicial) {
		this.sequencialRotalInicial = sequencialRotalInicial;
	}

	public Collection<Integer> getCategorias() {
		return categorias;
	}

	public void setCategorias(Collection<Integer> categorias) {
		this.categorias = categorias;
	}

	public Collection<Integer> getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(Collection<Integer> perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public int getAnoMesSubtraido1() {
		return anoMesSubtraido1;
	}

	public void setAnoMesSubtraido1(int anoMesSubtraido1) {
		this.anoMesSubtraido1 = anoMesSubtraido1;
	}

	public int getAnoMesSubtraido2() {
		return anoMesSubtraido2;
	}

	public void setAnoMesSubtraido2(int anoMesSubtraido2) {
		this.anoMesSubtraido2 = anoMesSubtraido2;
	}

	public int getAnoMesSubtraido3() {
		return anoMesSubtraido3;
	}

	public void setAnoMesSubtraido3(int anoMesSubtraido3) {
		this.anoMesSubtraido3 = anoMesSubtraido3;
	}

	public int getAnoMesSubtraido4() {
		return anoMesSubtraido4;
	}

	public void setAnoMesSubtraido4(int anoMesSubtraido4) {
		this.anoMesSubtraido4 = anoMesSubtraido4;
	}

	public int getAnoMesSubtraido5() {
		return anoMesSubtraido5;
	}

	public void setAnoMesSubtraido5(int anoMesSubtraido5) {
		this.anoMesSubtraido5 = anoMesSubtraido5;
	}

	public int getAnoMesSubtraido6() {
		return anoMesSubtraido6;
	}

	public void setAnoMesSubtraido6(int anoMesSubtraido6) {
		this.anoMesSubtraido6 = anoMesSubtraido6;
	}

	public int getAnoMesSubtraido7() {
		return anoMesSubtraido7;
	}

	public void setAnoMesSubtraido7(int anoMesSubtraido7) {
		this.anoMesSubtraido7 = anoMesSubtraido7;
	}

	public int getAnoMesSubtraido8() {
		return anoMesSubtraido8;
	}

	public void setAnoMesSubtraido8(int anoMesSubtraido8) {
		this.anoMesSubtraido8 = anoMesSubtraido8;
	}

	public int getAnoMesSubtraido9() {
		return anoMesSubtraido9;
	}

	public void setAnoMesSubtraido9(int anoMesSubtraido9) {
		this.anoMesSubtraido9 = anoMesSubtraido9;
	}

	public int getAnoMesSubtraido10() {
		return anoMesSubtraido10;
	}

	public void setAnoMesSubtraido10(int anoMesSubtraido10) {
		this.anoMesSubtraido10 = anoMesSubtraido10;
	}

	public int getAnoMesSubtraido11() {
		return anoMesSubtraido11;
	}

	public void setAnoMesSubtraido11(int anoMesSubtraido11) {
		this.anoMesSubtraido11 = anoMesSubtraido11;
	}

	public int getAnoMesSubtraido12() {
		return anoMesSubtraido12;
	}

	public void setAnoMesSubtraido12(int anoMesSubtraido12) {
		this.anoMesSubtraido12 = anoMesSubtraido12;
	}
}