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
* Ivan Sérgio da Silva Júnior
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
package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [RM6602] Identificar Imóveis Condomínios com Rateio Negativo.
 * [UC1281] Relatório de Imóveis Com Rateio Negativo
 * 
 * @analyst Sávio Cavalcante
 * @author Thúlio Araújo
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
