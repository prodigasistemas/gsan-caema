/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN; an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful;
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not; write to the Free Software
* Foundation; Inc.; 59 Temple Place - Suite 330; Boston; MA 02111-1307; USA
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
* modificá-lo sob os termos de Licença Pública Geral GNU; conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil; mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não; escreva para Free Software
* Foundation; Inc.; 59 Temple Place; Suite 330; Boston; MA
* 02111-1307; USA.
*/  
package gcom.cobranca.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * Filtrar os Comandos de Ação de Cobrança tipo comando Eventual
 * 
 * [UC0326] - Filtrar Comandos de Ação de Cobrança
 * 
 * @author Rafael Pinto
 * @date 20/12/2013
 * 
 */
public class FiltrarComandosAcaoCobrancaEventualHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String[] grupoCobranca; 
	private String[] acaoCobranca;
	private String[] atividadeCobranca;
	private String anoMesPeriodoReferenciaContasInicial;
	private String anoMesPeriodoReferenciaContasFinal;
	private Date dataPeriodoComandoInicial; 
	private Date dataPeriodoComandoFinal;
	private Date dataPeriodoRealizacaoComandoInicial;
	private Date dataPeriodoRealizacaoComandoFinal;
	private Date dataPeriodoVencimentoContasInicial;
	private Date dataPeriodoVencimentoContasFinal;
	private BigDecimal intervaloValorDocumentosInicial;
	private BigDecimal intervaloValorDocumentosFinal;
	private String intervaloQuantidadeDocumentosInicial;
	private String intervaloQuantidadeDocumentosFinal;
	private String intervaloQuantidadeItensDocumentosInicial;
	private String intervaloQuantidadeItensDocumentosFinal;
	private String situacaoComando; 
	private Integer indicadorCriterio;
	private String idGerenciaRegional; 
	private String idLocalidadeInicial;
	private String idLocalidadeFinal; 
	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal; 
	private String idRotaInicial;
	private String idRotaFinal; 
	private String idCliente; 
	private String idClienteRelacaoTipo;
	private String criterioCobranca; 
	private String unidadeNegocio; 
	private String[] idCobrancaAcaoAtividadeComando; 
	private Date dataEmissaoInicial;
	private Date dataEmissaoFinal; 
	private String consumoMedioInicial;
	private String consumoMedioFinal; 
	private String tipoConsumo;
	private Date periodoInicialFiscalizacao; 
	private Date periodoFinalFiscalizacao; 
	private String[] situacaoFiscalizacao;
	private String numeroQuadraInicial;
	private String numeroQuadraFinal; 
	private String quantidadeDiasVencimento; 
	private String ligacaoAlteradaDebito;
	
	private String[] idsLocalidadeApartirGerenciaRegional;
	private String localidadeUnica;
	private String[] idsSetoresApartirLocalidadeUnica;
	private Integer numeroPagina;
	
	public String[] getGrupoCobranca() {
		return grupoCobranca;
	}
	public void setGrupoCobranca(String[] grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}
	public String[] getAcaoCobranca() {
		return acaoCobranca;
	}
	public void setAcaoCobranca(String[] acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}
	public String[] getAtividadeCobranca() {
		return atividadeCobranca;
	}
	public void setAtividadeCobranca(String[] atividadeCobranca) {
		this.atividadeCobranca = atividadeCobranca;
	}
	public String getAnoMesPeriodoReferenciaContasInicial() {
		return anoMesPeriodoReferenciaContasInicial;
	}
	public void setAnoMesPeriodoReferenciaContasInicial(String anoMesPeriodoReferenciaContasInicial) {
		this.anoMesPeriodoReferenciaContasInicial = anoMesPeriodoReferenciaContasInicial;
	}
	public String getAnoMesPeriodoReferenciaContasFinal() {
		return anoMesPeriodoReferenciaContasFinal;
	}
	public void setAnoMesPeriodoReferenciaContasFinal(String anoMesPeriodoReferenciaContasFinal) {
		this.anoMesPeriodoReferenciaContasFinal = anoMesPeriodoReferenciaContasFinal;
	}
	public Date getDataPeriodoComandoInicial() {
		return dataPeriodoComandoInicial;
	}
	public void setDataPeriodoComandoInicial(Date dataPeriodoComandoInicial) {
		this.dataPeriodoComandoInicial = dataPeriodoComandoInicial;
	}
	public Date getDataPeriodoComandoFinal() {
		return dataPeriodoComandoFinal;
	}
	public void setDataPeriodoComandoFinal(Date dataPeriodoComandoFinal) {
		this.dataPeriodoComandoFinal = dataPeriodoComandoFinal;
	}
	public Date getDataPeriodoRealizacaoComandoInicial() {
		return dataPeriodoRealizacaoComandoInicial;
	}
	public void setDataPeriodoRealizacaoComandoInicial(Date dataPeriodoRealizacaoComandoInicial) {
		this.dataPeriodoRealizacaoComandoInicial = dataPeriodoRealizacaoComandoInicial;
	}
	public Date getDataPeriodoRealizacaoComandoFinal() {
		return dataPeriodoRealizacaoComandoFinal;
	}
	public void setDataPeriodoRealizacaoComandoFinal(Date dataPeriodoRealizacaoComandoFinal) {
		this.dataPeriodoRealizacaoComandoFinal = dataPeriodoRealizacaoComandoFinal;
	}
	public Date getDataPeriodoVencimentoContasInicial() {
		return dataPeriodoVencimentoContasInicial;
	}
	public void setDataPeriodoVencimentoContasInicial(Date dataPeriodoVencimentoContasInicial) {
		this.dataPeriodoVencimentoContasInicial = dataPeriodoVencimentoContasInicial;
	}
	public Date getDataPeriodoVencimentoContasFinal() {
		return dataPeriodoVencimentoContasFinal;
	}
	public void setDataPeriodoVencimentoContasFinal(Date dataPeriodoVencimentoContasFinal) {
		this.dataPeriodoVencimentoContasFinal = dataPeriodoVencimentoContasFinal;
	}
	public BigDecimal getIntervaloValorDocumentosInicial() {
		return intervaloValorDocumentosInicial;
	}
	public void setIntervaloValorDocumentosInicial(BigDecimal intervaloValorDocumentosInicial) {
		this.intervaloValorDocumentosInicial = intervaloValorDocumentosInicial;
	}
	public BigDecimal getIntervaloValorDocumentosFinal() {
		return intervaloValorDocumentosFinal;
	}
	public void setIntervaloValorDocumentosFinal(BigDecimal intervaloValorDocumentosFinal) {
		this.intervaloValorDocumentosFinal = intervaloValorDocumentosFinal;
	}
	public String getIntervaloQuantidadeDocumentosInicial() {
		return intervaloQuantidadeDocumentosInicial;
	}
	public void setIntervaloQuantidadeDocumentosInicial(String intervaloQuantidadeDocumentosInicial) {
		this.intervaloQuantidadeDocumentosInicial = intervaloQuantidadeDocumentosInicial;
	}
	public String getIntervaloQuantidadeDocumentosFinal() {
		return intervaloQuantidadeDocumentosFinal;
	}
	public void setIntervaloQuantidadeDocumentosFinal(String intervaloQuantidadeDocumentosFinal) {
		this.intervaloQuantidadeDocumentosFinal = intervaloQuantidadeDocumentosFinal;
	}
	public String getIntervaloQuantidadeItensDocumentosInicial() {
		return intervaloQuantidadeItensDocumentosInicial;
	}
	public void setIntervaloQuantidadeItensDocumentosInicial(String intervaloQuantidadeItensDocumentosInicial) {
		this.intervaloQuantidadeItensDocumentosInicial = intervaloQuantidadeItensDocumentosInicial;
	}
	public String getIntervaloQuantidadeItensDocumentosFinal() {
		return intervaloQuantidadeItensDocumentosFinal;
	}
	public void setIntervaloQuantidadeItensDocumentosFinal(String intervaloQuantidadeItensDocumentosFinal) {
		this.intervaloQuantidadeItensDocumentosFinal = intervaloQuantidadeItensDocumentosFinal;
	}
	public String getSituacaoComando() {
		return situacaoComando;
	}
	public void setSituacaoComando(String situacaoComando) {
		this.situacaoComando = situacaoComando;
	}
	public Integer getIndicadorCriterio() {
		return indicadorCriterio;
	}
	public void setIndicadorCriterio(Integer indicadorCriterio) {
		this.indicadorCriterio = indicadorCriterio;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getIdRotaInicial() {
		return idRotaInicial;
	}
	public void setIdRotaInicial(String idRotaInicial) {
		this.idRotaInicial = idRotaInicial;
	}
	public String getIdRotaFinal() {
		return idRotaFinal;
	}
	public void setIdRotaFinal(String idRotaFinal) {
		this.idRotaFinal = idRotaFinal;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}
	public void setIdClienteRelacaoTipo(String idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}
	public String getCriterioCobranca() {
		return criterioCobranca;
	}
	public void setCriterioCobranca(String criterioCobranca) {
		this.criterioCobranca = criterioCobranca;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String[] getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}
	public void setIdCobrancaAcaoAtividadeComando(String[] idCobrancaAcaoAtividadeComando) {
		this.idCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}
	public Date getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}
	public void setDataEmissaoInicial(Date dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}
	public Date getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}
	public void setDataEmissaoFinal(Date dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}
	public String getConsumoMedioInicial() {
		return consumoMedioInicial;
	}
	public void setConsumoMedioInicial(String consumoMedioInicial) {
		this.consumoMedioInicial = consumoMedioInicial;
	}
	public String getConsumoMedioFinal() {
		return consumoMedioFinal;
	}
	public void setConsumoMedioFinal(String consumoMedioFinal) {
		this.consumoMedioFinal = consumoMedioFinal;
	}
	public String getTipoConsumo() {
		return tipoConsumo;
	}
	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
	public Date getPeriodoInicialFiscalizacao() {
		return periodoInicialFiscalizacao;
	}
	public void setPeriodoInicialFiscalizacao(Date periodoInicialFiscalizacao) {
		this.periodoInicialFiscalizacao = periodoInicialFiscalizacao;
	}
	public Date getPeriodoFinalFiscalizacao() {
		return periodoFinalFiscalizacao;
	}
	public void setPeriodoFinalFiscalizacao(Date periodoFinalFiscalizacao) {
		this.periodoFinalFiscalizacao = periodoFinalFiscalizacao;
	}
	public String[] getSituacaoFiscalizacao() {
		return situacaoFiscalizacao;
	}
	public void setSituacaoFiscalizacao(String[] situacaoFiscalizacao) {
		this.situacaoFiscalizacao = situacaoFiscalizacao;
	}
	public String getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}
	public void setNumeroQuadraInicial(String numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}
	public String getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}
	public void setNumeroQuadraFinal(String numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}
	public String getQuantidadeDiasVencimento() {
		return quantidadeDiasVencimento;
	}
	public void setQuantidadeDiasVencimento(String quantidadeDiasVencimento) {
		this.quantidadeDiasVencimento = quantidadeDiasVencimento;
	}
	public String getLigacaoAlteradaDebito() {
		return ligacaoAlteradaDebito;
	}
	public void setLigacaoAlteradaDebito(String ligacaoAlteradaDebito) {
		this.ligacaoAlteradaDebito = ligacaoAlteradaDebito;
	}
	public String[] getIdsLocalidadeApartirGerenciaRegional() {
		return idsLocalidadeApartirGerenciaRegional;
	}
	public void setIdsLocalidadeApartirGerenciaRegional(String[] idsLocalidadeApartirGerenciaRegional) {
		this.idsLocalidadeApartirGerenciaRegional = idsLocalidadeApartirGerenciaRegional;
	}
	public String getLocalidadeUnica() {
		return localidadeUnica;
	}
	public void setLocalidadeUnica(String localidadeUnica) {
		this.localidadeUnica = localidadeUnica;
	}
	public String[] getIdsSetoresApartirLocalidadeUnica() {
		return idsSetoresApartirLocalidadeUnica;
	}
	public void setIdsSetoresApartirLocalidadeUnica(String[] idsSetoresApartirLocalidadeUnica) {
		this.idsSetoresApartirLocalidadeUnica = idsSetoresApartirLocalidadeUnica;
	}
	public Integer getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	
	
	

}