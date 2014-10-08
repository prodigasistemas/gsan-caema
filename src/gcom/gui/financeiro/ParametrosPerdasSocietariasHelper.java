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
package gcom.gui.financeiro;

import java.io.Serializable;


/**
 *
 * @author Fernanda ALmeida
 * @date 25/01/2013
 */
public class ParametrosPerdasSocietariasHelper  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferenciaContabil;

	private String mesAnoReferenciaBaixaInicial;
	
	private String mesAnoReferenciaBaixaFinal;

	private String mesReferenciaInferior;
	
	private String indicadorCategoriaResidencial;
	    
    private String indicadorCategoriaComercial;
    
    private String indicadorCategoriaIndustrial;
    
    private String indicadorCategoriaPublica;
    
    private String indicadorEsferaParticular;
    
    private String indicadorEsferaMunicipal;
    
    private String indicadorEsferaEstadual;
    
    private String indicadorEsferaFederal;
    
    private String indicadorTipoGeracao;
    
    private String numeroMesesContasVencidas;

	public String getMesAnoReferenciaContabil() {
		return mesAnoReferenciaContabil;
	}

	public void setMesAnoReferenciaContabil(String mesAnoReferenciaContabil) {
		this.mesAnoReferenciaContabil = mesAnoReferenciaContabil;
	}

	public String getMesAnoReferenciaBaixaInicial() {
		return mesAnoReferenciaBaixaInicial;
	}

	public void setMesAnoReferenciaBaixaInicial(String mesAnoReferenciaBaixaInicial) {
		this.mesAnoReferenciaBaixaInicial = mesAnoReferenciaBaixaInicial;
	}

	public String getMesAnoReferenciaBaixaFinal() {
		return mesAnoReferenciaBaixaFinal;
	}

	public void setMesAnoReferenciaBaixaFinal(String mesAnoReferenciaBaixaFinal) {
		this.mesAnoReferenciaBaixaFinal = mesAnoReferenciaBaixaFinal;
	}

	public String getMesReferenciaInferior() {
		return mesReferenciaInferior;
	}

	public void setMesReferenciaInferior(String mesReferenciaInferior) {
		this.mesReferenciaInferior = mesReferenciaInferior;
	}
	
	public String getIndicadorCategoriaResidencial() {
		return indicadorCategoriaResidencial;
	}

	public void setIndicadorCategoriaResidencial(
			String indicadorCategoriaResidencial) {
		this.indicadorCategoriaResidencial = indicadorCategoriaResidencial;
	}

	public String getIndicadorCategoriaComercial() {
		return indicadorCategoriaComercial;
	}

	public void setIndicadorCategoriaComercial(String indicadorCategoriaComercial) {
		this.indicadorCategoriaComercial = indicadorCategoriaComercial;
	}

	public String getIndicadorCategoriaIndustrial() {
		return indicadorCategoriaIndustrial;
	}

	public void setIndicadorCategoriaIndustrial(String indicadorCategoriaIndustrial) {
		this.indicadorCategoriaIndustrial = indicadorCategoriaIndustrial;
	}

	public String getIndicadorCategoriaPublica() {
		return indicadorCategoriaPublica;
	}

	public void setIndicadorCategoriaPublica(String indicadorCategoriaPublica) {
		this.indicadorCategoriaPublica = indicadorCategoriaPublica;
	}

	public String getIndicadorEsferaParticular() {
		return indicadorEsferaParticular;
	}

	public void setIndicadorEsferaParticular(String indicadorEsferaParticular) {
		this.indicadorEsferaParticular = indicadorEsferaParticular;
	}

	public String getIndicadorEsferaMunicipal() {
		return indicadorEsferaMunicipal;
	}

	public void setIndicadorEsferaMunicipal(String indicadorEsferaMunicipal) {
		this.indicadorEsferaMunicipal = indicadorEsferaMunicipal;
	}

	public String getIndicadorEsferaEstadual() {
		return indicadorEsferaEstadual;
	}

	public void setIndicadorEsferaEstadual(String indicadorEsferaEstadual) {
		this.indicadorEsferaEstadual = indicadorEsferaEstadual;
	}

	public String getIndicadorEsferaFederal() {
		return indicadorEsferaFederal;
	}

	public void setIndicadorEsferaFederal(String indicadorEsferaFederal) {
		this.indicadorEsferaFederal = indicadorEsferaFederal;
	}

	public String getIndicadorTipoGeracao() {
		return indicadorTipoGeracao;
	}

	public void setIndicadorTipoGeracao(String indicadorTipoGeracao) {
		this.indicadorTipoGeracao = indicadorTipoGeracao;
	}

	public String getNumeroMesesContasVencidas() {
		return numeroMesesContasVencidas;
	}

	public void setNumeroMesesContasVencidas(String numeroMesesContasVencidas) {
		this.numeroMesesContasVencidas = numeroMesesContasVencidas;
	}


}
