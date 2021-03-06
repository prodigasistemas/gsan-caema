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
package gcom.gui.financeiro;

import org.apache.struts.validator.ValidatorActionForm;


/**
 *
 * @author Fernanda ALmeida
 * @date 25/01/2013
 */
public class ParametrosPerdasSocietariasActionForm extends	ValidatorActionForm {

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
    
    private String idParametrosPerdasSocietarias;    

    private String residencial;
    private String comercial;
    private String industrial;
    private String publica;
    private String particular;
    private String municipal;
    private String federal;
    private String estadual;

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

	public String getIdParametrosPerdasSocietarias() {
		return idParametrosPerdasSocietarias;
	}

	public void setIdParametrosPerdasSocietarias(
			String idParametrosPerdasSocietarias) {
		this.idParametrosPerdasSocietarias = idParametrosPerdasSocietarias;
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public String getIndustrial() {
		return industrial;
	}

	public void setIndustrial(String industrial) {
		this.industrial = industrial;
	}

	public String getPublica() {
		return publica;
	}

	public void setPublica(String publica) {
		this.publica = publica;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getMunicipal() {
		return municipal;
	}

	public void setMunicipal(String municipal) {
		this.municipal = municipal;
	}

	public String getFederal() {
		return federal;
	}

	public void setFederal(String federal) {
		this.federal = federal;
	}

	public String getEstadual() {
		return estadual;
	}

	public void setEstadual(String estadual) {
		this.estadual = estadual;
	}


}
