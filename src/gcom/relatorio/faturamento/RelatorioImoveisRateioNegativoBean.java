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
 * [RM6602] Identificar Im�veis Condom�nios com Rateio Negativo.
 * [UC1281] Relat�rio de Im�veis Com Rateio Negativo
 * 
 * @analyst S�vio Cavalcante
 * @author Th�lio Ara�jo
 * @date 13/02/2012
 */
public class RelatorioImoveisRateioNegativoBean implements RelatorioBean {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegionalCabecalho;
	private String localidadeCabecalho;
	private String unidadeNegocioCabecalho;
	private String setorComercialCabecalho;
	
	private String gerenciaRegionalFiltro;
	private String unidadeNegocioFiltro;
	private String localidadeFiltro;
	private String setorFiltro;
	private String rotaFiltro;
	private String seqRotaFiltro;
	
	private String inscricaoImovel;
	private String matriculaImovel;
	private String cliente;
	private String qtdEconomia;
	private String endereco;
	
	public RelatorioImoveisRateioNegativoBean(RelatorioImoveisRateioNegativoHelper helper) {
		this.gerenciaRegionalCabecalho = helper.getGerenciaRegionalCabecalho();
		this.localidadeCabecalho = helper.getLocalidadeCabecalho();
		this.unidadeNegocioCabecalho = helper.getUnidadeNegocioCabecalho();
		
		this.gerenciaRegionalFiltro = helper.getGerenciaRegionalFiltro();
		this.unidadeNegocioFiltro = helper.getUnidadeNegocioFiltro();
		this.localidadeFiltro = helper.getLocalidadeFiltro();
		this.setorFiltro = helper.getSetorFiltro();
		this.rotaFiltro = helper.getRotaFiltro();
		this.seqRotaFiltro = helper.getSeqRotaFiltro();
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.matriculaImovel = helper.getMatriculaImovel().toString();
		this.cliente = helper.getCliente();
		this.qtdEconomia = helper.getQtdEconomia().toString();
		this.endereco = helper.getEndereco();
	}

	public String getGerenciaRegionalCabecalho() {
		return gerenciaRegionalCabecalho;
	}

	public void setGerenciaRegionalCabecalho(String gerenciaRegionalCabecalho) {
		this.gerenciaRegionalCabecalho = gerenciaRegionalCabecalho;
	}

	public String getLocalidadeCabecalho() {
		return localidadeCabecalho;
	}

	public void setLocalidadeCabecalho(String localidadeCabecalho) {
		this.localidadeCabecalho = localidadeCabecalho;
	}

	public String getUnidadeNegocioCabecalho() {
		return unidadeNegocioCabecalho;
	}

	public void setUnidadeNegocioCabecalho(String unidadeNegocioCabecalho) {
		this.unidadeNegocioCabecalho = unidadeNegocioCabecalho;
	}

	public String getSetorComercialCabecalho() {
		return setorComercialCabecalho;
	}

	public void setSetorComercialCabecalho(String setorComercialCabecalho) {
		this.setorComercialCabecalho = setorComercialCabecalho;
	}

	public String getGerenciaRegionalFiltro() {
		return gerenciaRegionalFiltro;
	}

	public void setGerenciaRegionalFiltro(String gerenciaRegionalFiltro) {
		this.gerenciaRegionalFiltro = gerenciaRegionalFiltro;
	}

	public String getUnidadeNegocioFiltro() {
		return unidadeNegocioFiltro;
	}

	public void setUnidadeNegocioFiltro(String unidadeNegocioFiltro) {
		this.unidadeNegocioFiltro = unidadeNegocioFiltro;
	}

	public String getLocalidadeFiltro() {
		return localidadeFiltro;
	}

	public void setLocalidadeFiltro(String localidadeFiltro) {
		this.localidadeFiltro = localidadeFiltro;
	}

	public String getSetorFiltro() {
		return setorFiltro;
	}

	public void setSetorFiltro(String setorFiltro) {
		this.setorFiltro = setorFiltro;
	}

	public String getRotaFiltro() {
		return rotaFiltro;
	}

	public void setRotaFiltro(String rotaFiltro) {
		this.rotaFiltro = rotaFiltro;
	}

	public String getSeqRotaFiltro() {
		return seqRotaFiltro;
	}

	public void setSeqRotaFiltro(String seqRotaFiltro) {
		this.seqRotaFiltro = seqRotaFiltro;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getQtdEconomia() {
		return qtdEconomia;
	}

	public void setQtdEconomia(String qtdEconomia) {
		this.qtdEconomia = qtdEconomia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
