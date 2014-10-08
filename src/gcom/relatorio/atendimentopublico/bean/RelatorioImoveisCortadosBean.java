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
package gcom.relatorio.atendimentopublico.bean;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

public class RelatorioImoveisCortadosBean implements RelatorioBean {

	private String descricaoGerencia;
	private String idGerencia ;	
	
	private String descricaoLocalidade ;
	private String idLocalidade ;
	
	private String descricaoUnidadeNegocio ;
	private String idUnidadeNegocio ;
	
	private String perfilImovel;
	private String categoria;
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	
	private String matricula;
	private String motivoCorte;
	private String dataCorte;

	private Integer qtdDebitosAntesCorte;
	private BigDecimal valorDebitosAntesCorte;
	
	private Integer qtdDebitosAposCorte;
	private BigDecimal valorDebitosAposCorte;
	
	private Integer qtdPagamentosAposCorte;
	private BigDecimal valorPagamentosAposCorte;

	private Integer qtdDebitosAtuais;
	private BigDecimal valorDebitosAtuais;
	
	private String dataEmissao;
	
	private String clienteUsuario;
	
 

	public RelatorioImoveisCortadosBean(String descricaoGerencia, String idGerencia, String descricaoLocalidade, String idLocalidade, 
			String descricaoUnidadeNegocio, String idUnidadeNegocio, String matricula, String motivoCorte, String dataCorte, Integer qtdDebitosAntesCorte, 
			BigDecimal valorDebitosAntesCorte, Integer qtdDebitosAposCorte, BigDecimal valorDebitosAposCorte, Integer qtdPagamentosAposCorte, BigDecimal valorPagamentosAposCorte, 
			String dataEmissao) {
		super();

		this.descricaoGerencia = descricaoGerencia;
		this.idGerencia = idGerencia;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idLocalidade = idLocalidade;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.matricula = matricula;
		this.motivoCorte = motivoCorte;
		this.dataCorte = dataCorte;
		this.qtdDebitosAntesCorte = qtdDebitosAntesCorte;
		this.valorDebitosAntesCorte = valorDebitosAntesCorte;
		this.qtdDebitosAposCorte = qtdDebitosAposCorte;
		this.valorDebitosAposCorte = valorDebitosAposCorte;
		this.qtdPagamentosAposCorte = qtdPagamentosAposCorte;
		this.valorPagamentosAposCorte = valorPagamentosAposCorte;
	}

	public RelatorioImoveisCortadosBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}

	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}

	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the motivoCorte
	 */
	public String getMotivoCorte() {
		return motivoCorte;
	}

	/**
	 * @param motivoCorte the motivoCorte to set
	 */
	public void setMotivoCorte(String motivoCorte) {
		this.motivoCorte = motivoCorte;
	}

	/**
	 * @return the dataCorte
	 */
	public String getDataCorte() {
		return dataCorte;
	}

	/**
	 * @param dataCorte the dataCorte to set
	 */
	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	/**
	 * @return the dataEmissao
	 */
	public String getDataEmissao() {
		return dataEmissao;
	}

	/**
	 * @param dataEmissao the dataEmissao to set
	 */
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @return the qtdDebitosAntesCorte
	 */
	public Integer getQtdDebitosAntesCorte() {
		return qtdDebitosAntesCorte;
	}

	/**
	 * @param qtdDebitosAntesCorte the qtdDebitosAntesCorte to set
	 */
	public void setQtdDebitosAntesCorte(Integer qtdDebitosAntesCorte) {
		this.qtdDebitosAntesCorte = qtdDebitosAntesCorte;
	}

	/**
	 * @return the qtdDebitosAposCorte
	 */
	public Integer getQtdDebitosAposCorte() {
		return qtdDebitosAposCorte;
	}

	/**
	 * @param qtdDebitosAposCorte the qtdDebitosAposCorte to set
	 */
	public void setQtdDebitosAposCorte(Integer qtdDebitosAposCorte) {
		this.qtdDebitosAposCorte = qtdDebitosAposCorte;
	}

	/**
	 * @return the qtdPagamentosAposCorte
	 */
	public Integer getQtdPagamentosAposCorte() {
		return qtdPagamentosAposCorte;
	}

	/**
	 * @param qtdPagamentosAposCorte the qtdPagamentosAposCorte to set
	 */
	public void setQtdPagamentosAposCorte(Integer qtdPagamentosAposCorte) {
		this.qtdPagamentosAposCorte = qtdPagamentosAposCorte;
	}

	/**
	 * @return the valorDebitosAntesCorte
	 */
	public BigDecimal getValorDebitosAntesCorte() {
		return valorDebitosAntesCorte;
	}

	/**
	 * @param valorDebitosAntesCorte the valorDebitosAntesCorte to set
	 */
	public void setValorDebitosAntesCorte(BigDecimal valorDebitosAntesCorte) {
		this.valorDebitosAntesCorte = valorDebitosAntesCorte;
	}

	/**
	 * @return the valorDebitosAposCorte
	 */
	public BigDecimal getValorDebitosAposCorte() {
		return valorDebitosAposCorte;
	}

	/**
	 * @param valorDebitosAposCorte the valorDebitosAposCorte to set
	 */
	public void setValorDebitosAposCorte(BigDecimal valorDebitosAposCorte) {
		this.valorDebitosAposCorte = valorDebitosAposCorte;
	}

	/**
	 * @return the valorPagamentosAposCorte
	 */
	public BigDecimal getValorPagamentosAposCorte() {
		return valorPagamentosAposCorte;
	}

	/**
	 * @param valorPagamentosAposCorte the valorPagamentosAposCorte to set
	 */
	public void setValorPagamentosAposCorte(BigDecimal valorPagamentosAposCorte) {
		this.valorPagamentosAposCorte = valorPagamentosAposCorte;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Integer getQtdDebitosAtuais() {
		return qtdDebitosAtuais;
	}

	public void setQtdDebitosAtuais(Integer qtdDebitosAtuais) {
		this.qtdDebitosAtuais = qtdDebitosAtuais;
	}

	public BigDecimal getValorDebitosAtuais() {
		return valorDebitosAtuais;
	}

	public void setValorDebitosAtuais(BigDecimal valorDebitosAtuais) {
		this.valorDebitosAtuais = valorDebitosAtuais;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}
	
}
