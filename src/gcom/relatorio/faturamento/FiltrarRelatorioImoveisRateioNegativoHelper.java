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

import java.io.Serializable;

/**
 * [RM6602] Identificar Imóveis Condomínios com Rateio Negativo.
 * [UC1281] Relatório de Imóveis Com Rateio Negativo
 * 
 * @analyst Sávio Cavalcante
 * @author Thúlio Araújo
 * @date 13/02/2012
 */
public class FiltrarRelatorioImoveisRateioNegativoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tipoSelecao;
	
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;
	
	private String nomeGerenciaRegional;
	private String nomeUnidadeNegocio;
	
	private Integer localidadeInicial;
	private Integer setorComercialInicial;
	private Integer rotaInicial;
	private Integer sequencialRotaInicial;

	private Integer localidadeFinal;
	private Integer setorComercialFinal;
	private Integer rotaFinal;
	private Integer sequencialRotaFinal;

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}
	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}
	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	public Integer getRotaInicial() {
		return rotaInicial;
	}
	public void setRotaInicial(Integer rotaInicial) {
		this.rotaInicial = rotaInicial;
	}
	public Integer getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}
	public void setSequencialRotaInicial(Integer sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}
	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}
	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}
	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}
	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	public Integer getRotaFinal() {
		return rotaFinal;
	}
	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}
	public Integer getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}
	public void setSequencialRotaFinal(Integer sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}
	public String getTipoSelecao() {
		return tipoSelecao;
	}
	public void setTipoSelecao(String tipoSelecao) {
		this.tipoSelecao = tipoSelecao;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
}