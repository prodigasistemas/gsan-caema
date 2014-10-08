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
package gcom.gui.atendimentopublico.ordemservico;


import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorActionForm;

public class FiltrarOrdemServicoConexaoEsgotoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	
	public static final int COMPESA = 1;
	public static final int PPP = 2;
	public static final int DISPONIVEL = 2;
	public static final int EFETIVADO = 1;
	
	
	private String descricaoComando;
	private String indicadorExecucao;
	
	private String idImovel;
	private String descricaoImovel;
	private String idMunicipio;
	private String descricaoMunicipio;
	private String idLogradouro;
	private String descricaoLogradouro;
	
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String idSetorComercialInicial;
	private String codSetorComercialInicial;
	private String descricaoSetorComercialInicial;
	private String quadraInicial;
	private String rotaInicial;
	private String rotaSequenciaInicial;
	
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String idSetorComercialFinal;
	private String codSetorComercialFinal;
	private String descricaoSetorComercialFinal;
	private String quadraFinal;
	private String rotaFinal;
	private String rotaSequenciaFinal;
	
	private String dataLigacao;
	private String idDiametroLigacao;
	private String idMaterialLigacao;
	private String idPerfilLigacao;
	private String percentualColeta;
	private String percentualEsgoto;
	private String idLigacaoOrigem;
	private String indicadorCaixaGordura;
	private String indicadorLigacao;
	private String idCondicaoEsgotamento;
	private String idSituacaoCaixaInspecao;
	private String idDestinoDejetos;
	private String idDestinoAguasPluviais;
	
	private String investimento;
	private String populacao;
	private String indicadorEncerramentoAutomatico;
	private String parecerEncerramento;
	
	private String idGrupoFaturamento;
	
	//Bloqueios do imóvel
	private String bloquearMunicipio;
	private String bloquearLogradouro;
	private String bloquearInscricaoInicial;
	private String bloquearInscricaoFinal;
	private String bloquearGrupoFaturamento;
	private String bloquearSetorComercial;
	private String bloquearQuadra;
	private String bloquearRota;
	private String bloquearPercentualColeta;
	private String bloquearEncerramentoAutomatico;
	private String indicadorPermissaoEspecialEncerramentoAutomatico;
	private String bloquearImovel;
	
	
	public FiltrarOrdemServicoConexaoEsgotoActionForm(){
		
		//Bloqueios
		this.bloquearLogradouro = ConstantesSistema.SIM+"";
		this.bloquearSetorComercial = ConstantesSistema.SIM+"";
		this.bloquearQuadra = ConstantesSistema.SIM+"";
		this.bloquearRota = ConstantesSistema.SIM+"";
		this.bloquearPercentualColeta = ConstantesSistema.NAO+"";
		this.bloquearEncerramentoAutomatico = ConstantesSistema.NAO+"";
		this.bloquearImovel = ConstantesSistema.NAO+"";
		
		//Indicadores
		this.indicadorExecucao = COMPESA+"";
		this.indicadorCaixaGordura = ConstantesSistema.NAO+"";
		this.indicadorLigacao = EFETIVADO+"";
		this.indicadorEncerramentoAutomatico = ConstantesSistema.NAO+"";
	}
	
	
	//============Métodos auxiliares para o JSP ======================
	public int getCompesa(){
		return COMPESA;
	}
	
	public int getPpp(){
		return PPP;
	}
	
	public int getSim(){
		return ConstantesSistema.SIM;
	}
	
	public int getNao(){
		return ConstantesSistema.NAO;
	}
	
	public int getDisponivel(){
		return DISPONIVEL;
	}
	
	public int getEfetivado(){
		return EFETIVADO;
	}
	//================================================================

	public String getDescricaoComando() {
		return descricaoComando;
	}

	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}

	public String getIndicadorExecucao() {
		return indicadorExecucao;
	}

	public void setIndicadorExecucao(String indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
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

	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}

	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}

	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}

	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}

	public String getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getRotaSequenciaInicial() {
		return rotaSequenciaInicial;
	}

	public void setRotaSequenciaInicial(String rotaSequenciaInicial) {
		this.rotaSequenciaInicial = rotaSequenciaInicial;
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

	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}

	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}

	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}

	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}

	public String getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaSequenciaFinal() {
		return rotaSequenciaFinal;
	}

	public void setRotaSequenciaFinal(String rotaSequenciaFinal) {
		this.rotaSequenciaFinal = rotaSequenciaFinal;
	}

	public String getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public String getIdDiametroLigacao() {
		return idDiametroLigacao;
	}

	public void setIdDiametroLigacao(String idDiametroLigacao) {
		this.idDiametroLigacao = idDiametroLigacao;
	}

	public String getIdMaterialLigacao() {
		return idMaterialLigacao;
	}

	public void setIdMaterialLigacao(String idMaterialLigacao) {
		this.idMaterialLigacao = idMaterialLigacao;
	}

	public String getIdPerfilLigacao() {
		return idPerfilLigacao;
	}

	public void setIdPerfilLigacao(String idPerfilLigacao) {
		this.idPerfilLigacao = idPerfilLigacao;
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

	public String getIdLigacaoOrigem() {
		return idLigacaoOrigem;
	}

	public void setIdLigacaoOrigem(String idLigacaoOrigem) {
		this.idLigacaoOrigem = idLigacaoOrigem;
	}

	public String getIndicadorCaixaGordura() {
		return indicadorCaixaGordura;
	}

	public void setIndicadorCaixaGordura(String indicadorCaixaGordura) {
		this.indicadorCaixaGordura = indicadorCaixaGordura;
	}

	public String getIndicadorLigacao() {
		return indicadorLigacao;
	}

	public void setIndicadorLigacao(String indicadorLigacao) {
		this.indicadorLigacao = indicadorLigacao;
	}

	public String getIdCondicaoEsgotamento() {
		return idCondicaoEsgotamento;
	}

	public void setIdCondicaoEsgotamento(String idCondicaoEsgotamento) {
		this.idCondicaoEsgotamento = idCondicaoEsgotamento;
	}

	public String getIdSituacaoCaixaInspecao() {
		return idSituacaoCaixaInspecao;
	}

	public void setIdSituacaoCaixaInspecao(String idSituacaoCaixaInspecao) {
		this.idSituacaoCaixaInspecao = idSituacaoCaixaInspecao;
	}

	public String getIdDestinoDejetos() {
		return idDestinoDejetos;
	}

	public void setIdDestinoDejetos(String idDestinoDejetos) {
		this.idDestinoDejetos = idDestinoDejetos;
	}

	public String getIdDestinoAguasPluviais() {
		return idDestinoAguasPluviais;
	}

	public void setIdDestinoAguasPluviais(String idDestinoAguasPluviais) {
		this.idDestinoAguasPluviais = idDestinoAguasPluviais;
	}

	public String getInvestimento() {
		return investimento;
	}

	public void setInvestimento(String investimento) {
		this.investimento = investimento;
	}

	public String getPopulacao() {
		return populacao;
	}

	public void setPopulacao(String populacao) {
		this.populacao = populacao;
	}

	public String getIndicadorEncerramentoAutomatico() {
		return indicadorEncerramentoAutomatico;
	}

	public void setIndicadorEncerramentoAutomatico(
			String indicadorEncerramentoAutomatico) {
		this.indicadorEncerramentoAutomatico = indicadorEncerramentoAutomatico;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getBloquearMunicipio() {
		return bloquearMunicipio;
	}

	public void setBloquearMunicipio(String bloquearMunicipio) {
		this.bloquearMunicipio = bloquearMunicipio;
		if(bloquearMunicipio.equals(ConstantesSistema.SIM.toString())){
			this.setIdMunicipio(null);
			this.setDescricaoMunicipio(null);
		}
	}

	public String getBloquearLogradouro() {
		return bloquearLogradouro;
	}

	public void setBloquearLogradouro(String bloquearLogradouro) {
		this.bloquearLogradouro = bloquearLogradouro;
		if(bloquearLogradouro.equals(ConstantesSistema.SIM.toString())){
			this.setIdLogradouro(null);
			this.setDescricaoLogradouro(null);
		}
	}

	public String getBloquearInscricaoInicial() {
		return bloquearInscricaoInicial;
	}

	public void setBloquearInscricaoInicial(String bloquearInscricaoInicial) {
		this.bloquearInscricaoInicial = bloquearInscricaoInicial;
		if(bloquearInscricaoInicial.equals(ConstantesSistema.SIM.toString())){
			this.setIdLocalidadeInicial(null);
			this.setDescricaoLocalidadeInicial(null);
			this.setIdSetorComercialInicial(null);
			this.setCodSetorComercialInicial(null);
			this.setDescricaoSetorComercialInicial(null);
			this.setQuadraInicial(null);
			this.setQuadraFinal(null);
			this.setRotaInicial(null);
			this.setRotaSequenciaInicial(null);
		}
		
	}

	public String getBloquearInscricaoFinal() {
		return bloquearInscricaoFinal;
	}

	public void setBloquearInscricaoFinal(String bloquearInscricaoFinal) {
		this.bloquearInscricaoFinal = bloquearInscricaoFinal;
		if(bloquearInscricaoFinal.equals(ConstantesSistema.SIM.toString())){
			this.setIdLocalidadeFinal(null);
			this.setDescricaoLocalidadeFinal(null);
			this.setIdSetorComercialFinal(null);
			this.setCodSetorComercialFinal(null);
			this.setDescricaoSetorComercialFinal(null);
			this.setQuadraFinal(null);
			this.setQuadraFinal(null);
			this.setRotaFinal(null);
			this.setRotaSequenciaFinal(null);
		}
	}

	public String getBloquearGrupoFaturamento() {
		return bloquearGrupoFaturamento;
	}

	public void setBloquearGrupoFaturamento(String bloquearGrupoFaturamento) {
		this.bloquearGrupoFaturamento = bloquearGrupoFaturamento;
		if(bloquearGrupoFaturamento.equals(ConstantesSistema.SIM.toString())){
			this.setIdGrupoFaturamento(null);
		}
	}

	public String getBloquearSetorComercial() {
		return bloquearSetorComercial;
	}

	public void setBloquearSetorComercial(String bloquearSetorComercial) {
		this.bloquearSetorComercial = bloquearSetorComercial;
		if(bloquearSetorComercial.equals(ConstantesSistema.SIM.toString())){
			this.setIdSetorComercialInicial(null);
			this.setCodSetorComercialInicial(null);
			this.setDescricaoSetorComercialInicial(null);
			this.setIdSetorComercialFinal(null);
			this.setCodSetorComercialFinal(null);
			this.setDescricaoSetorComercialFinal(null);
			this.setQuadraInicial(null);
			this.setQuadraFinal(null);
			this.setRotaInicial(null);
			this.setRotaSequenciaInicial(null);
			this.setRotaFinal(null);
			this.setRotaSequenciaFinal(null);
		}
	}

	public String getBloquearQuadra() {
		return bloquearQuadra;
	}

	public void setBloquearQuadra(String bloquearQuadra) {
		this.bloquearQuadra = bloquearQuadra;
		if(bloquearQuadra.equals(ConstantesSistema.SIM.toString())){
			this.setQuadraInicial(null);
			this.setQuadraFinal(null);
			this.setRotaInicial(null);
			this.setRotaSequenciaInicial(null);
			this.setRotaFinal(null);
			this.setRotaSequenciaFinal(null);
		}
	}


	public String getCodSetorComercialInicial() {
		return codSetorComercialInicial;
	}


	public void setCodSetorComercialInicial(String codSetorComercialInicial) {
		this.codSetorComercialInicial = codSetorComercialInicial;
	}


	public String getCodSetorComercialFinal() {
		return codSetorComercialFinal;
	}


	public void setCodSetorComercialFinal(String codSetorComercialFinal) {
		this.codSetorComercialFinal = codSetorComercialFinal;
	}


	public String getBloquearRota() {
		return bloquearRota;
	}


	public void setBloquearRota(String bloquearRota) {
		this.bloquearRota = bloquearRota;
		if(bloquearRota.equals(ConstantesSistema.SIM.toString())){
			this.setRotaInicial(null);
			this.setRotaSequenciaInicial(null);
			this.setRotaFinal(null);
			this.setRotaSequenciaFinal(null);
		}
		
	}


	public String getBloquearPercentualColeta() {
		return bloquearPercentualColeta;
	}


	public void setBloquearPercentualColeta(String bloquearPercentualColeta) {
		this.bloquearPercentualColeta = bloquearPercentualColeta;
	}


	public String getBloquearEncerramentoAutomatico() {
		return bloquearEncerramentoAutomatico;
	}


	public void setBloquearEncerramentoAutomatico(
			String bloquearEncerramentoAutomatico) {
		this.bloquearEncerramentoAutomatico = bloquearEncerramentoAutomatico;
		if(bloquearEncerramentoAutomatico.equals(ConstantesSistema.SIM.toString())){
			this.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO.toString());
		}
	}


	public String getIndicadorPermissaoEspecialEncerramentoAutomatico() {
		return indicadorPermissaoEspecialEncerramentoAutomatico;
	}


	public void setIndicadorPermissaoEspecialEncerramentoAutomatico(
			String indicadorPermissaoEspecialEncerramentoAutomatico) {
		this.indicadorPermissaoEspecialEncerramentoAutomatico = indicadorPermissaoEspecialEncerramentoAutomatico;
		if(indicadorPermissaoEspecialEncerramentoAutomatico.equals(ConstantesSistema.NAO.toString())){
			this.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO.toString());
		}
	}


	public String getBloquearImovel() {
		return bloquearImovel;
	}


	public void setBloquearImovel(String bloquearImovel) {
		this.bloquearImovel = bloquearImovel;
	}

}