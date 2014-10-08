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
package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1162]	Autorizar Alteracao Inscricao Imovel
 * 
 * @author Rodrigo Cabral
 * @date 04/04/2011
 */

public class FiltrarAlteracaoInscricaoImovelActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idLocalidade;
	private String desLocalidade;
	private String indicadorImovelAlteradoExcluido;
	private String indicadorSelecionarPor;
	private String indicadorOrigemAtualizacao;
	
	private Integer[] setorComercial;
	private Integer[] setorComercialSelecionados;
	
	private Integer[] quadra;
	private Integer[] quadraSelecionados;
	
	private String matriculaImovel1;
	private String matriculaImovel2;
	private String matriculaImovel3;
	private String matriculaImovel4;
	private String matriculaImovel5;
	private String matriculaImovel6;
	private String matriculaImovel7;
	private String matriculaImovel8;
	private String matriculaImovel9;
	private String matriculaImovel10;
	private String matriculaImovel11;
	private String matriculaImovel12;
	
	
	public String getDesLocalidade() {
		return desLocalidade;
	}
	public void setDesLocalidade(String desLocalidade) {
		this.desLocalidade = desLocalidade;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIndicadorImovelAlteradoExcluido() {
		return indicadorImovelAlteradoExcluido;
	}
	public void setIndicadorImovelAlteradoExcluido(String indicadorImovelAlteradoExcluido) {
		this.indicadorImovelAlteradoExcluido = indicadorImovelAlteradoExcluido;
	}
	public String getIndicadorSelecionarPor() {
		return indicadorSelecionarPor;
	}
	public void setIndicadorSelecionarPor(String indicadorSelecionarPor) {
		this.indicadorSelecionarPor = indicadorSelecionarPor;
	}
	
	public String getIndicadorOrigemAtualizacao() {
		return indicadorOrigemAtualizacao;
	}
	public void setIndicadorOrigemAtualizacao(String indicadorOrigemAtualizacao) {
		this.indicadorOrigemAtualizacao = indicadorOrigemAtualizacao;
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
	
	public String getMatriculaImovel1() {
		return matriculaImovel1;
	}
	public void setMatriculaImovel1(String matriculaImovel1) {
		this.matriculaImovel1 = matriculaImovel1;
	}
	public String getMatriculaImovel2() {
		return matriculaImovel2;
	}
	public void setMatriculaImovel2(String matriculaImovel2) {
		this.matriculaImovel2 = matriculaImovel2;
	}
	public String getMatriculaImovel3() {
		return matriculaImovel3;
	}
	public void setMatriculaImovel3(String matriculaImovel3) {
		this.matriculaImovel3 = matriculaImovel3;
	}
	public String getMatriculaImovel4() {
		return matriculaImovel4;
	}
	public void setMatriculaImovel4(String matriculaImovel4) {
		this.matriculaImovel4 = matriculaImovel4;
	}
	public String getMatriculaImovel5() {
		return matriculaImovel5;
	}
	public void setMatriculaImovel5(String matriculaImovel5) {
		this.matriculaImovel5 = matriculaImovel5;
	}
	public String getMatriculaImovel6() {
		return matriculaImovel6;
	}
	public void setMatriculaImovel6(String matriculaImovel6) {
		this.matriculaImovel6 = matriculaImovel6;
	}
	public String getMatriculaImovel7() {
		return matriculaImovel7;
	}
	public void setMatriculaImovel7(String matriculaImovel7) {
		this.matriculaImovel7 = matriculaImovel7;
	}
	public String getMatriculaImovel8() {
		return matriculaImovel8;
	}
	public void setMatriculaImovel8(String matriculaImovel8) {
		this.matriculaImovel8 = matriculaImovel8;
	}
	public String getMatriculaImovel9() {
		return matriculaImovel9;
	}
	public void setMatriculaImovel9(String matriculaImovel9) {
		this.matriculaImovel9 = matriculaImovel9;
	}
	public String getMatriculaImovel10() {
		return matriculaImovel10;
	}
	public void setMatriculaImovel10(String matriculaImovel10) {
		this.matriculaImovel10 = matriculaImovel10;
	}
	public String getMatriculaImovel11() {
		return matriculaImovel11;
	}
	public void setMatriculaImovel11(String matriculaImovel11) {
		this.matriculaImovel11 = matriculaImovel11;
	}
	public String getMatriculaImovel12() {
		return matriculaImovel12;
	}
	public void setMatriculaImovel12(String matriculaImovel12) {
		this.matriculaImovel12 = matriculaImovel12;
	}
	
}