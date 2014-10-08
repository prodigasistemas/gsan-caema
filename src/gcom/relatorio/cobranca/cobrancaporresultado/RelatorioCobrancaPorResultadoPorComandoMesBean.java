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
* Genival Soares Barbosa Filho
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
package gcom.relatorio.cobranca.cobrancaporresultado;


import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0000] Relatório Opção Totalização por Comando e Opção de Totalização por Mês de Apuração
 * 
 * @author Vivianne Sousa
 * @date 25/04/2014
 */
public class RelatorioCobrancaPorResultadoPorComandoMesBean implements RelatorioBean {	

	private Integer idGerenciaRegional;		
	private Integer idLocalidade;			
	private Integer idFaixaContas;			
	private Integer idRegiao;				
	private Integer idMunicipio;
	private Integer qtdContas;				
	private Integer qtdeClientes;	
	private Integer idComando;
	private Integer idUnidadeNegocio;
	private Integer idMicroregiao;
	private String nomeEmpresa;				
	private String numeroContrato;			
	private String anoMesRef;				
	private String descLocalidade;			
	private String descMunicipio;			
	private String descFaixaContas;	
	private String descGerenciaRegional;	
	private String descRegiao;	
	private String descUnidadeNegocio;
	private String descMicroregiao;
	private BigDecimal vlPagtoAVista;		
	private BigDecimal vlPagtoParcelado;
	private BigDecimal vlPagtoTotal;
	private BigDecimal vlPagtoAVistaPrestadora;		
	private BigDecimal vlPagtoParceladoPrestadora;
	private BigDecimal vlPagtoTotalPrestadora;
	private BigDecimal percentualFaixa;	
	private BigDecimal vlDesconto;			
	private BigDecimal vlTotalDivida;	
	private String  dataExecucao;				
	private String  dataInicio;				
	private String  dataFinal;
	private Integer qtdePagtoAVista;		
	private Integer qtdePagtoParcelado;	
	private Integer qtdePagtoTotal;	

	private JRBeanCollectionDataSource arrayJRFaixasContasTotalGerencia;

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdFaixaContas() {
		return idFaixaContas;
	}
	public void setIdFaixaContas(Integer idFaixaContas) {
		this.idFaixaContas = idFaixaContas;
	}
	public Integer getIdRegiao() {
		return idRegiao;
	}
	public void setIdRegiao(Integer idRegiao) {
		this.idRegiao = idRegiao;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public Integer getQtdContas() {
		return qtdContas;
	}
	public void setQtdContas(Integer qtdContas) {
		this.qtdContas = qtdContas;
	}
	public Integer getQtdeClientes() {
		return qtdeClientes;
	}
	public void setQtdeClientes(Integer qtdeClientes) {
		this.qtdeClientes = qtdeClientes;
	}
	public Integer getIdComando() {
		return idComando;
	}
	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getAnoMesRef() {
		return anoMesRef;
	}
	public void setAnoMesRef(String anoMesRef) {
		this.anoMesRef = anoMesRef;
	}
	public String getDescLocalidade() {
		return descLocalidade;
	}
	public void setDescLocalidade(String descLocalidade) {
		this.descLocalidade = descLocalidade;
	}
	public String getDescMunicipio() {
		return descMunicipio;
	}
	public void setDescMunicipio(String descMunicipio) {
		this.descMunicipio = descMunicipio;
	}
	public String getDescFaixaContas() {
		return descFaixaContas;
	}
	public void setDescFaixaContas(String descFaixaContas) {
		this.descFaixaContas = descFaixaContas;
	}
	public String getDescGerenciaRegional() {
		return descGerenciaRegional;
	}
	public void setDescGerenciaRegional(String descGerenciaRegional) {
		this.descGerenciaRegional = descGerenciaRegional;
	}
	public String getDescRegiao() {
		return descRegiao;
	}
	public void setDescRegiao(String descRegiao) {
		this.descRegiao = descRegiao;
	}
	public String getDescUnidadeNegocio() {
		return descUnidadeNegocio;
	}
	public void setDescUnidadeNegocio(String descUnidadeNegocio) {
		this.descUnidadeNegocio = descUnidadeNegocio;
	}
	public BigDecimal getVlPagtoAVista() {
		return vlPagtoAVista;
	}
	public void setVlPagtoAVista(BigDecimal vlPagtoAVista) {
		this.vlPagtoAVista = vlPagtoAVista;
	}
	public BigDecimal getVlPagtoParcelado() {
		return vlPagtoParcelado;
	}
	public void setVlPagtoParcelado(BigDecimal vlPagtoParcelado) {
		this.vlPagtoParcelado = vlPagtoParcelado;
	}
	public BigDecimal getVlPagtoTotal() {
		return vlPagtoTotal;
	}
	public void setVlPagtoTotal(BigDecimal vlPagtoTotal) {
		this.vlPagtoTotal = vlPagtoTotal;
	}
	public BigDecimal getVlPagtoAVistaPrestadora() {
		return vlPagtoAVistaPrestadora;
	}
	public void setVlPagtoAVistaPrestadora(BigDecimal vlPagtoAVistaPrestadora) {
		this.vlPagtoAVistaPrestadora = vlPagtoAVistaPrestadora;
	}
	public BigDecimal getVlPagtoParceladoPrestadora() {
		return vlPagtoParceladoPrestadora;
	}
	public void setVlPagtoParceladoPrestadora(BigDecimal vlPagtoParceladoPrestadora) {
		this.vlPagtoParceladoPrestadora = vlPagtoParceladoPrestadora;
	}
	public BigDecimal getVlPagtoTotalPrestadora() {
		return vlPagtoTotalPrestadora;
	}
	public void setVlPagtoTotalPrestadora(BigDecimal vlPagtoTotalPrestadora) {
		this.vlPagtoTotalPrestadora = vlPagtoTotalPrestadora;
	}
	public BigDecimal getPercentualFaixa() {
		return percentualFaixa;
	}
	public void setPercentualFaixa(BigDecimal percentualFaixa) {
		this.percentualFaixa = percentualFaixa;
	}
	public BigDecimal getVlDesconto() {
		return vlDesconto;
	}
	public void setVlDesconto(BigDecimal vlDesconto) {
		this.vlDesconto = vlDesconto;
	}
	public BigDecimal getVlTotalDivida() {
		return vlTotalDivida;
	}
	public void setVlTotalDivida(BigDecimal vlTotalDivida) {
		this.vlTotalDivida = vlTotalDivida;
	}
	public String getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public Integer getIdMicroregiao() {
		return idMicroregiao;
	}
	public void setIdMicroregiao(Integer idMicroregiao) {
		this.idMicroregiao = idMicroregiao;
	}
	public String getDescMicroregiao() {
		return descMicroregiao;
	}
	public void setDescMicroregiao(String descMicroregiao) {
		this.descMicroregiao = descMicroregiao;
	}
	public JRBeanCollectionDataSource getArrayJRFaixasContasTotalGerencia() {
		return arrayJRFaixasContasTotalGerencia;
	}
	
	public void setArrayJRFaixasContasTotalGerencia(List<RelatorioCobrancaPorResultadoPorComandoMesSubTotalBean> colecao) {
		this.arrayJRFaixasContasTotalGerencia = new JRBeanCollectionDataSource(colecao);
	}

	public Integer getQtdePagtoAVista() {
		return qtdePagtoAVista;
	}
	public void setQtdePagtoAVista(Integer qtdePagtoAVista) {
		this.qtdePagtoAVista = qtdePagtoAVista;
	}
	public Integer getQtdePagtoParcelado() {
		return qtdePagtoParcelado;
	}
	public void setQtdePagtoParcelado(Integer qtdePagtoParcelado) {
		this.qtdePagtoParcelado = qtdePagtoParcelado;
	}
	public Integer getQtdePagtoTotal() {
		return qtdePagtoTotal;
	}
	public void setQtdePagtoTotal(Integer qtdePagtoTotal) {
		this.qtdePagtoTotal = qtdePagtoTotal;
	}
	
}
