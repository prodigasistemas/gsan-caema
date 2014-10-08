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
package gcom.atendimentopublico;

import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;

import java.io.Serializable;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class SubstituicaoHidrometroAcompanhamentoServico  implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private SubstituicaoHidrometroAcompanhamentoServicoPK comp_id;
	
	private Integer matriculaImovel;
	
	private Integer numeroLeituraAtual;
	
	private String descricaoNumeroHidrometro;
	
	private Short indicadorTipoMedicao;
	
	private Integer numeroLeituraInstalacao;
	
	private Integer numeroSelo;	
	
	private Short indicadorCavalete;
	
	private HidrometroLocalInstalacao hidrometroLocalInstalacao;
	
	private HidrometroProtecao hidrometroProtecao;	

	private String descricaoErro;	
	
	private Short indicadorSubstituicaoRealizada;	
	
	private Date ultimaAlteracao;
	

	public SubstituicaoHidrometroAcompanhamentoServicoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(SubstituicaoHidrometroAcompanhamentoServicoPK comp_id) {
		this.comp_id = comp_id;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Integer getNumeroLeituraAtual() {
		return numeroLeituraAtual;
	}

	public void setNumeroLeituraAtual(Integer numeroLeituraAtual) {
		this.numeroLeituraAtual = numeroLeituraAtual;
	}

	public String getDescricaoNumeroHidrometro() {
		return descricaoNumeroHidrometro;
	}

	public void setDescricaoNumeroHidrometro(String descricaoNumeroHidrometro) {
		this.descricaoNumeroHidrometro = descricaoNumeroHidrometro;
	}

	public Short getIndicadorTipoMedicao() {
		return indicadorTipoMedicao;
	}

	public void setIndicadorTipoMedicao(Short indicadorTipoMedicao) {
		this.indicadorTipoMedicao = indicadorTipoMedicao;
	}

	public Integer getNumeroLeituraInstalacao() {
		return numeroLeituraInstalacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void setNumeroLeituraInstalacao(Integer numeroLeituraInstalacao) {
		this.numeroLeituraInstalacao = numeroLeituraInstalacao;
	}

	public Integer getNumeroSelo() {
		return numeroSelo;
	}

	public void setNumeroSelo(Integer numeroSelo) {
		this.numeroSelo = numeroSelo;
	}

	public Short getIndicadorCavalete() {
		return indicadorCavalete;
	}

	public void setIndicadorCavalete(Short indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}

	public HidrometroLocalInstalacao getHidrometroLocalInstalacao() {
		return hidrometroLocalInstalacao;
	}

	public void setHidrometroLocalInstalacao(HidrometroLocalInstalacao hidrometroLocalInstalacao) {
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
	}

	public HidrometroProtecao getHidrometroProtecao() {
		return hidrometroProtecao;
	}

	public Short getIndicadorSubstituicaoRealizada() {
		return indicadorSubstituicaoRealizada;
	}

	public void setIndicadorSubstituicaoRealizada(
			Short indicadorSubstituicaoRealizada) {
		this.indicadorSubstituicaoRealizada = indicadorSubstituicaoRealizada;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao) {
		this.hidrometroProtecao = hidrometroProtecao;
	}


	
	
}
