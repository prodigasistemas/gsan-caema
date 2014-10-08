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
* Ivan S�rgio Virginio da Silva J�nior
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
package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 1261] Gerar tebalas tempor�rias para atualiza��o cadastral com dispositivo m�vel  
 * 
 * @author Nathalia Santos
 * @created 05/08/2008
 */
public class GerarTabelasTemporariasPorLocalidadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	// Parametros
	private String empresa;
	private String localidade;
	private String matriculaImovel;
	private String nomeImovel;
	private String indicadorGeracaoSetor;
	private String indicadorSelecaoQuadraRota;

	private Integer[] setorComercial;
	private Integer[] setorComercialSelecionados;
	
	private Integer[] quadra;
	private Integer[] quadraSelecionados;
	
	private Integer[] rota;
	private Integer[] rotaSelecionados;
	
	private Integer[] setorComercialEnviados;

	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getIndicadorGeracaoSetor() {
		return indicadorGeracaoSetor;
	}
	public void setIndicadorGeracaoSetor(String indicadorGeracaoSetor) {
		this.indicadorGeracaoSetor = indicadorGeracaoSetor;
	}
	public String getIndicadorSelecaoQuadraRota() {
		return indicadorSelecaoQuadraRota;
	}
	public void setIndicadorSelecaoQuadraRota(String indicadorSelecaoQuadraRota) {
		this.indicadorSelecaoQuadraRota = indicadorSelecaoQuadraRota;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeImovel() {
		return nomeImovel;
	}
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}
	public Integer[] getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(Integer[] setorComercial) {
		this.setorComercial = setorComercial;
	}
	public Integer[] getSetorComercialSelecionados() {
		return setorComercialSelecionados;
	}
	public void setSetorComercialSelecionados(Integer[] setorComercialSelecionados) {
		this.setorComercialSelecionados = setorComercialSelecionados;
	}
	public Integer[] getQuadra() {
		return quadra;
	}
	public void setQuadra(Integer[] quadra) {
		this.quadra = quadra;
	}
	public Integer[] getQuadraSelecionados() {
		return quadraSelecionados;
	}
	public void setQuadraSelecionados(Integer[] quadraSelecionados) {
		this.quadraSelecionados = quadraSelecionados;
	}
	public Integer[] getRota() {
		return rota;
	}
	public void setRota(Integer[] rota) {
		this.rota = rota;
	}
	public Integer[] getRotaSelecionados() {
		return rotaSelecionados;
	}
	public void setRotaSelecionados(Integer[] rotaSelecionados) {
		this.rotaSelecionados = rotaSelecionados;
	}
	public Integer[] getSetorComercialEnviados() {
		return setorComercialEnviados;
	}
	public void setSetorComercialEnviados(Integer[] setorComercialEnviados) {
		this.setorComercialEnviados = setorComercialEnviados;
	}
	
}

