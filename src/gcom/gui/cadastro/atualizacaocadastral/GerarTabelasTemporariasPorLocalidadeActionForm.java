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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 1261] Gerar tebalas temporárias para atualização cadastral com dispositivo móvel  
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

