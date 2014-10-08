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
package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class ComandoOSConexaoEsgoto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private Integer referenciaGrupoFaturamento;
	private String descricaoComando;
	private Short indicadorExecutor;
	private Integer numeroSequencialRotaInicial;
	private Integer numeroSequencialRotaFinal;
	private BigDecimal percentualColeta;
	private BigDecimal percentualEsgoto;
	private Short indicadorCaixaGordura;
	private Short indicadorLigacaoEsgoto;
	private BigDecimal valorInvestimento;
	private Integer valorPopulacao;
	private Short indicadorEncerramentoAutomatico;
	private String parecerEncerramentoAutomatico;
	private Date ultimaAlteracao;
	private Date dataCriacaoComando;
	private Date dataExecucaoComando;
	private Date dataFinalizacao;
	
	private Imovel imovel;
	private Municipio municipio;
	private Logradouro logradouro;
	private Localidade localidadeInicial;
	private Localidade localidadeFinal;
	private SetorComercial setorComercialInicial;
	private SetorComercial setorComercialFinal;
	private Quadra quadraInicial;
	private Quadra quadraFinal;
	private Rota rotaInicial;
	private Rota rotaFinal;
	private LigacaoEsgotoDiametro diametroLigacaoEsgoto;
	private LigacaoEsgotoMaterial materialLigacaoEsgoto;
	private LigacaoEsgotoPerfil perfilLigacaoEsgoto;
	private LigacaoOrigem origemLigacao;
	private LigacaoEsgotoEsgotamento condicaoEsgotamento;
	private LigacaoEsgotoCaixaInspecao situacaoCaixaInspecao;
	private LigacaoEsgotoDestinoDejetos destinoDejetos;
	private LigacaoEsgotoDestinoAguasPluviais destinoAguasPluviais;
	private FaturamentoGrupo grupoFaturamento;
	private ServicoTipo servicoTipo;
	
	
	public ComandoOSConexaoEsgoto(){}
	
	public ComandoOSConexaoEsgoto(Integer idConexaoEsgoto){
		this.id = idConexaoEsgoto;
	}
	

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoComando() {
		return descricaoComando;
	}

	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}

	public Short getIndicadorExecutor() {
		return indicadorExecutor;
	}

	public void setIndicadorExecutor(Short indicadorExecutor) {
		this.indicadorExecutor = indicadorExecutor;
	}

	public Integer getNumeroSequencialRotaInicial() {
		return numeroSequencialRotaInicial;
	}

	public void setNumeroSequencialRotaInicial(Integer numeroSequencialRotaInicial) {
		this.numeroSequencialRotaInicial = numeroSequencialRotaInicial;
	}

	public Integer getNumeroSequencialRotaFinal() {
		return numeroSequencialRotaFinal;
	}

	public void setNumeroSequencialRotaFinal(Integer numeroSequencialRotaFinal) {
		this.numeroSequencialRotaFinal = numeroSequencialRotaFinal;
	}


	public BigDecimal getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(BigDecimal percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public Short getIndicadorCaixaGordura() {
		return indicadorCaixaGordura;
	}

	public void setIndicadorCaixaGordura(Short indicadorCaixaGordura) {
		this.indicadorCaixaGordura = indicadorCaixaGordura;
	}

	public Short getIndicadorLigacaoEsgoto() {
		return indicadorLigacaoEsgoto;
	}

	public void setIndicadorLigacaoEsgoto(Short indicadorLigacaoEsgoto) {
		this.indicadorLigacaoEsgoto = indicadorLigacaoEsgoto;
	}

	public BigDecimal getValorInvestimento() {
		return valorInvestimento;
	}

	public void setValorInvestimento(BigDecimal valorInvestimento) {
		this.valorInvestimento = valorInvestimento;
	}

	
	public Integer getValorPopulacao() {
		return valorPopulacao;
	}

	public void setValorPopulacao(Integer valorPopulacao) {
		this.valorPopulacao = valorPopulacao;
	}

	public Short getIndicadorEncerramentoAutomatico() {
		return indicadorEncerramentoAutomatico;
	}

	public void setIndicadorEncerramentoAutomatico(
			Short indicadorEncerramentoAutomatico) {
		this.indicadorEncerramentoAutomatico = indicadorEncerramentoAutomatico;
	}

	public String getParecerEncerramentoAutomatico() {
		return parecerEncerramentoAutomatico;
	}

	public void setParecerEncerramentoAutomatico(
			String parecerEncerramentoAutomatico) {
		this.parecerEncerramentoAutomatico = parecerEncerramentoAutomatico;
	}

	public Date getDataCriacaoComando() {
		return dataCriacaoComando;
	}

	public void setDataCriacaoComando(Date dataCriacaoComando) {
		this.dataCriacaoComando = dataCriacaoComando;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public SetorComercial getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(SetorComercial setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public SetorComercial getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(SetorComercial setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Quadra getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Quadra quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public Quadra getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Quadra quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Rota getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Rota rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Rota getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Rota rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public LigacaoEsgotoDiametro getDiametroLigacaoEsgoto() {
		return diametroLigacaoEsgoto;
	}

	public void setDiametroLigacaoEsgoto(LigacaoEsgotoDiametro diametroLigacaoEsgoto) {
		this.diametroLigacaoEsgoto = diametroLigacaoEsgoto;
	}

	public LigacaoEsgotoMaterial getMaterialLigacaoEsgoto() {
		return materialLigacaoEsgoto;
	}

	public void setMaterialLigacaoEsgoto(LigacaoEsgotoMaterial materialLigacaoEsgoto) {
		this.materialLigacaoEsgoto = materialLigacaoEsgoto;
	}

	public LigacaoEsgotoPerfil getPerfilLigacaoEsgoto() {
		return perfilLigacaoEsgoto;
	}

	public void setPerfilLigacaoEsgoto(LigacaoEsgotoPerfil perfilLigacaoEsgoto) {
		this.perfilLigacaoEsgoto = perfilLigacaoEsgoto;
	}

	public LigacaoOrigem getOrigemLigacao() {
		return origemLigacao;
	}

	public void setOrigemLigacao(LigacaoOrigem origemLigacao) {
		this.origemLigacao = origemLigacao;
	}

	public LigacaoEsgotoEsgotamento getCondicaoEsgotamento() {
		return condicaoEsgotamento;
	}

	public void setCondicaoEsgotamento(LigacaoEsgotoEsgotamento condicaoEsgotamento) {
		this.condicaoEsgotamento = condicaoEsgotamento;
	}

	public LigacaoEsgotoCaixaInspecao getSituacaoCaixaInspecao() {
		return situacaoCaixaInspecao;
	}

	public void setSituacaoCaixaInspecao(
			LigacaoEsgotoCaixaInspecao situacaoCaixaInspecao) {
		this.situacaoCaixaInspecao = situacaoCaixaInspecao;
	}

	public LigacaoEsgotoDestinoDejetos getDestinoDejetos() {
		return destinoDejetos;
	}

	public void setDestinoDejetos(LigacaoEsgotoDestinoDejetos destinoDejetos) {
		this.destinoDejetos = destinoDejetos;
	}

	public LigacaoEsgotoDestinoAguasPluviais getDestinoAguasPluviais() {
		return destinoAguasPluviais;
	}

	public void setDestinoAguasPluviais(
			LigacaoEsgotoDestinoAguasPluviais destinoAguasPluviais) {
		this.destinoAguasPluviais = destinoAguasPluviais;
	}

	public FaturamentoGrupo getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(FaturamentoGrupo grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public Date getDataExecucaoComando() {
		return dataExecucaoComando;
	}

	public void setDataExecucaoComando(Date dataExecucaoComando) {
		this.dataExecucaoComando = dataExecucaoComando;
	}

	public Integer getReferenciaGrupoFaturamento() {
		return referenciaGrupoFaturamento;
	}

	public void setReferenciaGrupoFaturamento(Integer referenciaGrupoFaturamento) {
		this.referenciaGrupoFaturamento = referenciaGrupoFaturamento;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
}
