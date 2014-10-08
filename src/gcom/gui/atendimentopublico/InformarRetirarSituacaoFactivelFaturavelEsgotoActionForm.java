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

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Formulario do [UC1467] - Informar/Retirar Situação Factível Faturável de Esgoto
 * 
 * @author Arthur Carvalho
 * @date 13/05/2013
 */
public class InformarRetirarSituacaoFactivelFaturavelEsgotoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;


	private String indicadorTipoOperacao;
	
	private String idMunicipio;
	
	private String idLogradouro;
	
	private String idLocalidadeInicial;
	
	private String descricaoLocalidadeInicial;
	
	private String codigoSetorComercialInicial;
	
	private String descricaoSetorComercialInicial;
	
	private String numeroQuadraInicial;
	
	private String numeroRotaInicial;
	
	private String numeroSequencialRotaInicial;
	
	private String idLocalidadeFinal;
	
	private String descricaoLocalidadeFinal;
	
	private String codigoSetorComercialFinal;
	
	private String descricaoSetorComercialFinal;
	
	private String numeroQuadraFinal;
	
	private String numeroRotaFinal;
	
	private String numeroSequencialRotaFinal;
	
	private String idTipoSolicitacaoRA;
	
	private String idEspecificacao;
	
	private String dataLigacao;
	
	//Dados da ligação
	private String indicadorLigacao;
	private String indicadorCaixaGordura;
	private String diametroLigacao;
	private String materialLigacao;
	private String perfilLigacao;
	private String percentualColeta;
	private String percentualEsgoto;
	private String idLigacaoOrigem;
	private String condicaoEsgotamento;
	private String situacaoCaixaInspecao;
	private String destinoDejetos;
	private String destinoAguasPluviais;
	private String qtdTotalImoveis;
	//Dados da ligação fim
	
	

	public String getIndicadorTipoOperacao() {
		return indicadorTipoOperacao;
	}

	public void setIndicadorTipoOperacao(String indicadorTipoOperacao) {
		this.indicadorTipoOperacao = indicadorTipoOperacao;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}

	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}

	public String getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(String numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public String getNumeroRotaInicial() {
		return numeroRotaInicial;
	}

	public void setNumeroRotaInicial(String numeroRotaInicial) {
		this.numeroRotaInicial = numeroRotaInicial;
	}

	public String getNumeroSequencialRotaInicial() {
		return numeroSequencialRotaInicial;
	}

	public void setNumeroSequencialRotaInicial(String numeroSequencialRotaInicial) {
		this.numeroSequencialRotaInicial = numeroSequencialRotaInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}

	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}

	public String getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(String numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public String getNumeroRotaFinal() {
		return numeroRotaFinal;
	}

	public void setNumeroRotaFinal(String numeroRotaFinal) {
		this.numeroRotaFinal = numeroRotaFinal;
	}

	public String getNumeroSequencialRotaFinal() {
		return numeroSequencialRotaFinal;
	}

	public void setNumeroSequencialRotaFinal(String numeroSequencialRotaFinal) {
		this.numeroSequencialRotaFinal = numeroSequencialRotaFinal;
	}

	public String getIdTipoSolicitacaoRA() {
		return idTipoSolicitacaoRA;
	}

	public void setIdTipoSolicitacaoRA(String idTipoSolicitacaoRA) {
		this.idTipoSolicitacaoRA = idTipoSolicitacaoRA;
	}

	public String getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public String getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public String getIndicadorLigacao() {
		return indicadorLigacao;
	}

	public void setIndicadorLigacao(String indicadorLigacao) {
		this.indicadorLigacao = indicadorLigacao;
	}

	public String getIndicadorCaixaGordura() {
		return indicadorCaixaGordura;
	}

	public void setIndicadorCaixaGordura(String indicadorCaixaGordura) {
		this.indicadorCaixaGordura = indicadorCaixaGordura;
	}


	public String getDiametroLigacao() {
		return diametroLigacao;
	}

	public void setDiametroLigacao(String diametroLigacao) {
		this.diametroLigacao = diametroLigacao;
	}

	public String getMaterialLigacao() {
		return materialLigacao;
	}

	public void setMaterialLigacao(String materialLigacao) {
		this.materialLigacao = materialLigacao;
	}

	public String getPerfilLigacao() {
		return perfilLigacao;
	}

	public void setPerfilLigacao(String perfilLigacao) {
		this.perfilLigacao = perfilLigacao;
	}

	public String getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public String getCondicaoEsgotamento() {
		return condicaoEsgotamento;
	}

	public void setCondicaoEsgotamento(String condicaoEsgotamento) {
		this.condicaoEsgotamento = condicaoEsgotamento;
	}

	public String getSituacaoCaixaInspecao() {
		return situacaoCaixaInspecao;
	}

	public void setSituacaoCaixaInspecao(String situacaoCaixaInspecao) {
		this.situacaoCaixaInspecao = situacaoCaixaInspecao;
	}

	public String getDestinoDejetos() {
		return destinoDejetos;
	}

	public void setDestinoDejetos(String destinoDejetos) {
		this.destinoDejetos = destinoDejetos;
	}

	public String getIdLigacaoOrigem() {
		return idLigacaoOrigem;
	}

	public void setIdLigacaoOrigem(String idLigacaoOrigem) {
		this.idLigacaoOrigem = idLigacaoOrigem;
	}

	public String getDestinoAguasPluviais() {
		return destinoAguasPluviais;
	}

	public void setDestinoAguasPluviais(String destinoAguasPluviais) {
		this.destinoAguasPluviais = destinoAguasPluviais;
	}

	public String getQtdTotalImoveis() {
		return qtdTotalImoveis;
	}

	public void setQtdTotalImoveis(String qtdTotalImoveis) {
		this.qtdTotalImoveis = qtdTotalImoveis;
	}

	
}
