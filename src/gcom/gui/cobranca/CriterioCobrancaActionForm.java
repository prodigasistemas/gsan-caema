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
package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form respons�vel pelas propiedades do caso de uso de inserir os
 * crit�rios da cobran�a.
 * 
 * @author S�vio Luiz
 * @date 29/05/2006
 */
public class CriterioCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricaoCriterio;

	private String dataInicioVigencia;

	private String numeroAnoContaAntiga;

	private String opcaoAcaoImovelSitEspecial;

	private String opcaoAcaoImovelSit;

	private String opcaoContasRevisao;

	private String opcaoAcaoImovelDebitoMesConta;

	private String opcaoAcaoInquilinoDebitoMesConta;

	private String opcaoAcaoImovelDebitoContasAntigas;

	private String[] parImovelPerfil;

	private String[] parCategoria;

	private String valorDebitoMinimo;

	private String valorDebitoMaximo;

	private String qtdContasMinima;

	private String qtdContasMaxima;

	private String vlMinimoDebitoCliente;

	private String qtdMinContasCliente;

	private String vlMinimoContasMes;
	
	private String indicadorUso;
	
	private String descricaoImovelPerfil;
	
	private String descricaoCategoria;
	
	private String percentualValorMinimoPagoParceladoCancelado;
	
	private String percentualQuantidadeMinimoPagoParceladoCancelado;
	
	private String valorLimitePrioridade;
	
	private String quantidadeMinimaParcelasAtraso;
	
	private String[] idsCobrancaSituacao;
	
	private String[] idsSituacaoLigacaoAgua;
	
	private String[] idsSituacaoLigacaoEsgoto;
	
	private String idResolucaoDiretoria;

	public String[] getIdsCobrancaSituacao() {
		return idsCobrancaSituacao;
	}

	public void setIdsCobrancaSituacao(String[] idsCobrancaSituacao) {
		this.idsCobrancaSituacao = idsCobrancaSituacao;
	}

	public String[] getIdsSituacaoLigacaoAgua() {
		return idsSituacaoLigacaoAgua;
	}

	public void setIdsSituacaoLigacaoAgua(String[] idsSituacaoLigacaoAgua) {
		this.idsSituacaoLigacaoAgua = idsSituacaoLigacaoAgua;
	}

	public String[] getIdsSituacaoLigacaoEsgoto() {
		return idsSituacaoLigacaoEsgoto;
	}

	public void setIdsSituacaoLigacaoEsgoto(String[] idsSituacaoLigacaoEsgoto) {
		this.idsSituacaoLigacaoEsgoto = idsSituacaoLigacaoEsgoto;
	}

	public String getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(String dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public String getDescricaoCriterio() {
		return descricaoCriterio;
	}

	public void setDescricaoCriterio(String descricaoCriterio) {
		this.descricaoCriterio = descricaoCriterio;
	}

	public String getNumeroAnoContaAntiga() {
		return numeroAnoContaAntiga;
	}

	public void setNumeroAnoContaAntiga(String numeroAnoContaAntiga) {
		this.numeroAnoContaAntiga = numeroAnoContaAntiga;
	}

	public String getOpcaoAcaoImovelDebitoMesConta() {
		return opcaoAcaoImovelDebitoMesConta;
	}

	public void setOpcaoAcaoImovelDebitoMesConta(
			String opcaoAcaoImovelDebitoMesConta) {
		this.opcaoAcaoImovelDebitoMesConta = opcaoAcaoImovelDebitoMesConta;
	}

	public String getOpcaoAcaoImovelSit() {
		return opcaoAcaoImovelSit;
	}

	public void setOpcaoAcaoImovelSit(String opcaoAcaoImovelSit) {
		this.opcaoAcaoImovelSit = opcaoAcaoImovelSit;
	}

	public String getOpcaoAcaoImovelSitEspecial() {
		return opcaoAcaoImovelSitEspecial;
	}

	public void setOpcaoAcaoImovelSitEspecial(String opcaoAcaoImovelSitEspecial) {
		this.opcaoAcaoImovelSitEspecial = opcaoAcaoImovelSitEspecial;
	}

	public String getOpcaoAcaoInquilinoDebitoMesConta() {
		return opcaoAcaoInquilinoDebitoMesConta;
	}

	public void setOpcaoAcaoInquilinoDebitoMesConta(
			String opcaoAcaoInquilinoDebitoMesConta) {
		this.opcaoAcaoInquilinoDebitoMesConta = opcaoAcaoInquilinoDebitoMesConta;
	}

	public String getOpcaoContasRevisao() {
		return opcaoContasRevisao;
	}

	public void setOpcaoContasRevisao(String opcaoContasRevisao) {
		this.opcaoContasRevisao = opcaoContasRevisao;
	}

	public String getQtdContasMaxima() {
		return qtdContasMaxima;
	}

	public void setQtdContasMaxima(String qtdContasMaxima) {
		this.qtdContasMaxima = qtdContasMaxima;
	}

	public String getQtdContasMinima() {
		return qtdContasMinima;
	}

	public void setQtdContasMinima(String qtdContasMinima) {
		this.qtdContasMinima = qtdContasMinima;
	}

	public String getQtdMinContasCliente() {
		return qtdMinContasCliente;
	}

	public void setQtdMinContasCliente(String qtdMinContasCliente) {
		this.qtdMinContasCliente = qtdMinContasCliente;
	}

	public String getValorDebitoMaximo() {
		return valorDebitoMaximo;
	}

	public void setValorDebitoMaximo(String valorDebitoMaximo) {
		this.valorDebitoMaximo = valorDebitoMaximo;
	}

	public String getValorDebitoMinimo() {
		return valorDebitoMinimo;
	}

	public void setValorDebitoMinimo(String valorDebitoMinimo) {
		this.valorDebitoMinimo = valorDebitoMinimo;
	}

	public String getVlMinimoContasMes() {
		return vlMinimoContasMes;
	}

	public void setVlMinimoContasMes(String vlMinimoContasMes) {
		this.vlMinimoContasMes = vlMinimoContasMes;
	}

	public String getVlMinimoDebitoCliente() {
		return vlMinimoDebitoCliente;
	}

	public void setVlMinimoDebitoCliente(String vlMinimoDebitoCliente) {
		this.vlMinimoDebitoCliente = vlMinimoDebitoCliente;
	}

	public String getOpcaoAcaoImovelDebitoContasAntigas() {
		return opcaoAcaoImovelDebitoContasAntigas;
	}

	public void setOpcaoAcaoImovelDebitoContasAntigas(
			String opcaoAcaoImovelDebitoContasAntigas) {
		this.opcaoAcaoImovelDebitoContasAntigas = opcaoAcaoImovelDebitoContasAntigas;
	}

	public String[] getParCategoria() {
		return parCategoria;
	}

	public void setParCategoria(String[] parCategoria) {
		this.parCategoria = parCategoria;
	}

	public String[] getParImovelPerfil() {
		return parImovelPerfil;
	}

	public void setParImovelPerfil(String[] parImovelPerfil) {
		this.parImovelPerfil = parImovelPerfil;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDescricaoImovelPerfil() {
		return descricaoImovelPerfil;
	}

	public void setDescricaoImovelPerfil(String descricaoImovelPerfil) {
		this.descricaoImovelPerfil = descricaoImovelPerfil;
	}

	public String getPercentualQuantidadeMinimoPagoParceladoCancelado() {
		return percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	public void setPercentualQuantidadeMinimoPagoParceladoCancelado(
			String percentualQuantidadeMinimoPagoParceladoCancelado) {
		this.percentualQuantidadeMinimoPagoParceladoCancelado = percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	public String getPercentualValorMinimoPagoParceladoCancelado() {
		return percentualValorMinimoPagoParceladoCancelado;
	}

	public void setPercentualValorMinimoPagoParceladoCancelado(
			String percentualValorMinimoPagoParceladoCancelado) {
		this.percentualValorMinimoPagoParceladoCancelado = percentualValorMinimoPagoParceladoCancelado;
	}

	public String getValorLimitePrioridade() {
		return valorLimitePrioridade;
	}

	public void setValorLimitePrioridade(String valorLimitePrioridade) {
		this.valorLimitePrioridade = valorLimitePrioridade;
	}

	public String getQuantidadeMinimaParcelasAtraso() {
		return quantidadeMinimaParcelasAtraso;
	}

	public void setQuantidadeMinimaParcelasAtraso(
			String quantidadeMinimaParcelasAtraso) {
		this.quantidadeMinimaParcelasAtraso = quantidadeMinimaParcelasAtraso;
	}

	public String getIdResolucaoDiretoria() {
		return idResolucaoDiretoria;
	}

	public void setIdResolucaoDiretoria(String idResolucaoDiretoria) {
		this.idResolucaoDiretoria = idResolucaoDiretoria;
	}
	
		
}