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
package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC1279] - Consultar Imóveis Com Situação da Ligação de Água Cortado
 *
 * @author Arthur Carvalho
 * @date 14/02/2012
 */
public class FiltrarImovelCortadoActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	
	private String periodoCorteInicial;
	private String periodoCorteFinal;
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String idSetorComercialInicial;
	private String descricaoSetorComercialInicial;
	private String idQuadraInicial;
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String idSetorComercialFinal;
	private String descricaoSetorComercialFinal;
	private String idQuadraFinal;
	private String idMotivoCorte;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String indicadorGerarOSFiscalizacao;
	private String[] idRegistro;
	private String mensagemQuadra;
	private String mensageQuadraFinal;
	
	//aux
	private String botaoGerarOrdemServico;

	//Emitir fiscalizacao
	private String matriculaImovel;
	private String inscricaoImovel;
	private String enderecoImovel;
	private String tipo;
	private String tipoHidden;
	private String indicadorPermitirEmitir;
	private String indicadorPermitirGerarOs;
	private String dataEmissao;
	private String idPerfilImovel;
	private String descricaoPerfilImovel;
	private String endereco;
	private String ultimaAlteracao;
	private String faturamentoGrupo;
	private String situacaoLigacaoAgua;
	private String consumoMedioAgua;
	private String dataCorte;
	private String dataSupressaoParcial;
	private String dataSupressaoTotal;
	private String situacaoLigacaoEsgoto;
	private String volumeFixoEsgoto;
	private String ocorrencia;
	private String valorServicos;
	private String valorDebitosAteDataVencimento;
	private String nomeCliente;
	private String cpfCnpj;
	private String rg;
	private String ddd;
	private String numeroTelefone;
	private String ramal;
	private String tipoTelefone;
	private Integer qtdeEconResidencial;
	private Integer qtdeEconComercial;
	private Integer qtdeEconIndustrial;
	private Integer qtdeEconPublica;
	private Integer qtdeEconTotal;
	private String ordemServico;
	private String uf;
	private String situacaoAguaDebitos;
	private String situacaoEsgotoDebitos;
	
	
	
	public String getPeriodoCorteInicial() {
		return periodoCorteInicial;
	}

	public void setPeriodoCorteInicial(String periodoCorteInicial) {
		this.periodoCorteInicial = periodoCorteInicial;
	}

	public String getPeriodoCorteFinal() {
		return periodoCorteFinal;
	}

	public void setPeriodoCorteFinal(String periodoCorteFinal) {
		this.periodoCorteFinal = periodoCorteFinal;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}

	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}

	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}

	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}

	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}

	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}

	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}

	public String getIdMotivoCorte() {
		return idMotivoCorte;
	}

	public void setIdMotivoCorte(String idMotivoCorte) {
		this.idMotivoCorte = idMotivoCorte;
	}

	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}

	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}

	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}

	public String getIndicadorGerarOSFiscalizacao() {
		return indicadorGerarOSFiscalizacao;
	}

	public void setIndicadorGerarOSFiscalizacao(String indicadorGerarOSFiscalizacao) {
		this.indicadorGerarOSFiscalizacao = indicadorGerarOSFiscalizacao;
	}

	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}

	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}

	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}

	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}

	public String[] getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String[] idRegistro) {
		this.idRegistro = idRegistro;
	}

	/**
	 * @return the matriculaImovel
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel the matriculaImovel to set
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return the inscricaoImovel
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel the inscricaoImovel to set
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return the enderecoImovel
	 */
	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	/**
	 * @param enderecoImovel the enderecoImovel to set
	 */
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipoHidden
	 */
	public String getTipoHidden() {
		return tipoHidden;
	}

	/**
	 * @param tipoHidden the tipoHidden to set
	 */
	public void setTipoHidden(String tipoHidden) {
		this.tipoHidden = tipoHidden;
	}

	/**
	 * @return the indicadorPermitirEmitir
	 */
	public String getIndicadorPermitirEmitir() {
		return indicadorPermitirEmitir;
	}

	/**
	 * @param indicadorPermitirEmitir the indicadorPermitirEmitir to set
	 */
	public void setIndicadorPermitirEmitir(String indicadorPermitirEmitir) {
		this.indicadorPermitirEmitir = indicadorPermitirEmitir;
	}

	/**
	 * @return the indicadorPermitirGerarOs
	 */
	public String getIndicadorPermitirGerarOs() {
		return indicadorPermitirGerarOs;
	}

	/**
	 * @param indicadorPermitirGerarOs the indicadorPermitirGerarOs to set
	 */
	public void setIndicadorPermitirGerarOs(String indicadorPermitirGerarOs) {
		this.indicadorPermitirGerarOs = indicadorPermitirGerarOs;
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
	 * @return the idPerfilImovel
	 */
	public String getIdPerfilImovel() {
		return idPerfilImovel;
	}

	/**
	 * @param idPerfilImovel the idPerfilImovel to set
	 */
	public void setIdPerfilImovel(String idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	/**
	 * @return the descricaoPerfilImovel
	 */
	public String getDescricaoPerfilImovel() {
		return descricaoPerfilImovel;
	}

	/**
	 * @param descricaoPerfilImovel the descricaoPerfilImovel to set
	 */
	public void setDescricaoPerfilImovel(String descricaoPerfilImovel) {
		this.descricaoPerfilImovel = descricaoPerfilImovel;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the ultimaAlteracao
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao the ultimaAlteracao to set
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return the faturamentoGrupo
	 */
	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	/**
	 * @param faturamentoGrupo the faturamentoGrupo to set
	 */
	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	/**
	 * @return the situacaoLigacaoAgua
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua the situacaoLigacaoAgua to set
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return the consumoMedioAgua
	 */
	public String getConsumoMedioAgua() {
		return consumoMedioAgua;
	}

	/**
	 * @param consumoMedioAgua the consumoMedioAgua to set
	 */
	public void setConsumoMedioAgua(String consumoMedioAgua) {
		this.consumoMedioAgua = consumoMedioAgua;
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
	 * @return the dataSupressaoParcial
	 */
	public String getDataSupressaoParcial() {
		return dataSupressaoParcial;
	}

	/**
	 * @param dataSupressaoParcial the dataSupressaoParcial to set
	 */
	public void setDataSupressaoParcial(String dataSupressaoParcial) {
		this.dataSupressaoParcial = dataSupressaoParcial;
	}

	/**
	 * @return the dataSupressaoTotal
	 */
	public String getDataSupressaoTotal() {
		return dataSupressaoTotal;
	}

	/**
	 * @param dataSupressaoTotal the dataSupressaoTotal to set
	 */
	public void setDataSupressaoTotal(String dataSupressaoTotal) {
		this.dataSupressaoTotal = dataSupressaoTotal;
	}

	/**
	 * @return the situacaoLigacaoEsgoto
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto the situacaoLigacaoEsgoto to set
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	/**
	 * @return the volumeFixoEsgoto
	 */
	public String getVolumeFixoEsgoto() {
		return volumeFixoEsgoto;
	}

	/**
	 * @param volumeFixoEsgoto the volumeFixoEsgoto to set
	 */
	public void setVolumeFixoEsgoto(String volumeFixoEsgoto) {
		this.volumeFixoEsgoto = volumeFixoEsgoto;
	}

	/**
	 * @return the ocorrencia
	 */
	public String getOcorrencia() {
		return ocorrencia;
	}

	/**
	 * @param ocorrencia the ocorrencia to set
	 */
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	/**
	 * @return the valorServicos
	 */
	public String getValorServicos() {
		return valorServicos;
	}

	/**
	 * @param valorServicos the valorServicos to set
	 */
	public void setValorServicos(String valorServicos) {
		this.valorServicos = valorServicos;
	}

	/**
	 * @return the valorDebitosAteDataVencimento
	 */
	public String getValorDebitosAteDataVencimento() {
		return valorDebitosAteDataVencimento;
	}

	/**
	 * @param valorDebitosAteDataVencimento the valorDebitosAteDataVencimento to set
	 */
	public void setValorDebitosAteDataVencimento(
			String valorDebitosAteDataVencimento) {
		this.valorDebitosAteDataVencimento = valorDebitosAteDataVencimento;
	}

	/**
	 * @return the nomeCliente
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente the nomeCliente to set
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the rg
	 */
	public String getRg() {
		return rg;
	}

	/**
	 * @param rg the rg to set
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

	/**
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return the numeroTelefone
	 */
	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	/**
	 * @param numeroTelefone the numeroTelefone to set
	 */
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	/**
	 * @return the ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal the ramal to set
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * @return the tipoTelefone
	 */
	public String getTipoTelefone() {
		return tipoTelefone;
	}

	/**
	 * @param tipoTelefone the tipoTelefone to set
	 */
	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	/**
	 * @return the qtdeEconResidencial
	 */
	public Integer getQtdeEconResidencial() {
		return qtdeEconResidencial;
	}

	/**
	 * @param qtdeEconResidencial the qtdeEconResidencial to set
	 */
	public void setQtdeEconResidencial(Integer qtdeEconResidencial) {
		this.qtdeEconResidencial = qtdeEconResidencial;
	}

	/**
	 * @return the qtdeEconComercial
	 */
	public Integer getQtdeEconComercial() {
		return qtdeEconComercial;
	}

	/**
	 * @param qtdeEconComercial the qtdeEconComercial to set
	 */
	public void setQtdeEconComercial(Integer qtdeEconComercial) {
		this.qtdeEconComercial = qtdeEconComercial;
	}

	/**
	 * @return the qtdeEconIndustrial
	 */
	public Integer getQtdeEconIndustrial() {
		return qtdeEconIndustrial;
	}

	/**
	 * @param qtdeEconIndustrial the qtdeEconIndustrial to set
	 */
	public void setQtdeEconIndustrial(Integer qtdeEconIndustrial) {
		this.qtdeEconIndustrial = qtdeEconIndustrial;
	}

	/**
	 * @return the qtdeEconPublica
	 */
	public Integer getQtdeEconPublica() {
		return qtdeEconPublica;
	}

	/**
	 * @param qtdeEconPublica the qtdeEconPublica to set
	 */
	public void setQtdeEconPublica(Integer qtdeEconPublica) {
		this.qtdeEconPublica = qtdeEconPublica;
	}

	/**
	 * @return the qtdeEconTotal
	 */
	public Integer getQtdeEconTotal() {
		return qtdeEconTotal;
	}

	/**
	 * @param qtdeEconTotal the qtdeEconTotal to set
	 */
	public void setQtdeEconTotal(Integer qtdeEconTotal) {
		this.qtdeEconTotal = qtdeEconTotal;
	}

	/**
	 * @return the ordemServico
	 */
	public String getOrdemServico() {
		return ordemServico;
	}

	/**
	 * @param ordemServico the ordemServico to set
	 */
	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * @return the situacaoAguaDebitos
	 */
	public String getSituacaoAguaDebitos() {
		return situacaoAguaDebitos;
	}

	/**
	 * @param situacaoAguaDebitos the situacaoAguaDebitos to set
	 */
	public void setSituacaoAguaDebitos(String situacaoAguaDebitos) {
		this.situacaoAguaDebitos = situacaoAguaDebitos;
	}

	/**
	 * @return the situacaoEsgotoDebitos
	 */
	public String getSituacaoEsgotoDebitos() {
		return situacaoEsgotoDebitos;
	}

	/**
	 * @param situacaoEsgotoDebitos the situacaoEsgotoDebitos to set
	 */
	public void setSituacaoEsgotoDebitos(String situacaoEsgotoDebitos) {
		this.situacaoEsgotoDebitos = situacaoEsgotoDebitos;
	}

	/**
	 * @return the botaoGerarOrdemServico
	 */
	public String getBotaoGerarOrdemServico() {
		return botaoGerarOrdemServico;
	}

	/**
	 * @param botaoGerarOrdemServico the botaoGerarOrdemServico to set
	 */
	public void setBotaoGerarOrdemServico(String botaoGerarOrdemServico) {
		this.botaoGerarOrdemServico = botaoGerarOrdemServico;
	}

	/**
	 * @return the mensagemQuadra
	 */
	public String getMensagemQuadra() {
		return mensagemQuadra;
	}

	/**
	 * @param mensagemQuadra the mensagemQuadra to set
	 */
	public void setMensagemQuadra(String mensagemQuadra) {
		this.mensagemQuadra = mensagemQuadra;
	}

	/**
	 * @return the mensageQuadraFinal
	 */
	public String getMensageQuadraFinal() {
		return mensageQuadraFinal;
	}

	/**
	 * @param mensageQuadraFinal the mensageQuadraFinal to set
	 */
	public void setMensageQuadraFinal(String mensageQuadraFinal) {
		this.mensageQuadraFinal = mensageQuadraFinal;
	}
}
