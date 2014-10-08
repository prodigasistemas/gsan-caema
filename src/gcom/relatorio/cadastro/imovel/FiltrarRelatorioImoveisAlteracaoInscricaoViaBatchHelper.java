/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Date;


/**
 * [UC1121] Gerar Relat�rio de Im�veis com Altera��o de Inscri��o Via Batch
 * 
 * Classe que ir� auxiliar no formato de entrada da pesquisa 
 * do relatorio de Im�veis com Altera��o de Inscri��o Via Batch
 * 
 * @author Hugo Leonardo
 * @date 19/01/2011
 */
public class FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer localidadeInicial;
	private Integer localidadeFinal;
	private Integer setorComercialInicial;
	private Integer codigoSetorComercialInicial;
	private Integer setorComercialFinal;
	private Integer codigoSetorComercialFinal;
	private Integer quadraInicial;
	private Integer numeroQuadraInicial;
	private Integer quadraFinal;
	private Integer numeroQuadraFinal;
	private Integer loteInicial;
	private Integer loteFinal;
	private Integer subLoteInicial;
	private Integer subLoteFinal;
	private Integer escolhaRelatorio;
	private Date dataInicio;
	private Date dataFim;
	
	private Short indicadorInscricaoAtualAnterior;
	private String origemAtualizacao;
	
	private Integer idLocalidadeEncontrada;

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getEscolhaRelatorio() {
		return escolhaRelatorio;
	}

	public void setEscolhaRelatorio(Integer escolhaRelatorio) {
		this.escolhaRelatorio = escolhaRelatorio;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getLoteFinal() {
		return loteFinal;
	}

	public void setLoteFinal(Integer loteFinal) {
		this.loteFinal = loteFinal;
	}

	public Integer getLoteInicial() {
		return loteInicial;
	}

	public void setLoteInicial(Integer loteInicial) {
		this.loteInicial = loteInicial;
	}

	public Integer getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Integer quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Integer getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Integer quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Integer getSubLoteFinal() {
		return subLoteFinal;
	}

	public void setSubLoteFinal(Integer subLoteFinal) {
		this.subLoteFinal = subLoteFinal;
	}

	public Integer getSubLoteInicial() {
		return subLoteInicial;
	}

	public void setSubLoteInicial(Integer subLoteInicial) {
		this.subLoteInicial = subLoteInicial;
	}

	public Short getIndicadorInscricaoAtualAnterior() {
		return indicadorInscricaoAtualAnterior;
	}

	public void setIndicadorInscricaoAtualAnterior(
			Short indicadorInscricaoAtualAnterior) {
		this.indicadorInscricaoAtualAnterior = indicadorInscricaoAtualAnterior;
	}

	public String getOrigemAtualizacao() {
		return origemAtualizacao;
	}

	public void setOrigemAtualizacao(String origemAtualizacao) {
		this.origemAtualizacao = origemAtualizacao;
	}

	public Integer getIdLocalidadeEncontrada() {
		return idLocalidadeEncontrada;
	}

	public void setIdLocalidadeEncontrada(Integer idLocalidadeEncontrada) {
		this.idLocalidadeEncontrada = idLocalidadeEncontrada;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}
	
	
	
}