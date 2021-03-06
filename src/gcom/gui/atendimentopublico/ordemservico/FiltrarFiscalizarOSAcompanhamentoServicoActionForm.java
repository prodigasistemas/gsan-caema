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
package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0450] Pesquisar Ordem Servi�o
 * 
 * @author Rafael Pinto
 *
 * @date 15/06/2006
 */
public class FiltrarFiscalizarOSAcompanhamentoServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroOS;
	private String matriculaImovel;	
	private String inscricaoImovel;
	private String idLocalidade;
	private String nomeLocalidade;
	
	private String[] idFiscalizarOSAcompanhamento;
	private String[] idFiscalizarOSAcompanhamentoNao;	

	private String[] idRA;	

	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String periodoGeracaoInicial;
	private String periodoGeracaoFinal;
	private String periodoProgramacaoInicial;
	private String periodoProgramacaoFinal;
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	
	private String municipio;
	private String descricaoMunicipio;
	private String codigoBairro;
	private String descricaoBairro;
	private String idBairro;
	private String areaBairro;
	private String logradouro;
	private String descricaoLogradouro;
	
	private String origemOrdemServico;
		
	private String idMotivoNaoAceite;
	private String observacao;
	private String indicadorObrigatorio;
	private String idUnidade;
	private String nomeUnidade;
	
	//RM7889
	private String idTipoServico;
	private String servicoEmCampo;
	private String idOS;
	
	public void resetOS() {
				
		matriculaImovel= null;
		inscricaoImovel= null;	
		
		periodoAtendimentoInicial = null;
		periodoAtendimentoFinal = null;
		periodoEncerramentoInicial = null;
		periodoEncerramentoFinal = null;
		periodoGeracaoInicial = null;
		periodoGeracaoFinal = null;
		periodoProgramacaoInicial = null;
		periodoProgramacaoFinal = null;		
		
		municipio = null;
		descricaoMunicipio = null;
		idBairro = null;
		codigoBairro = null;
		descricaoBairro = null;

		logradouro = null;
		descricaoLogradouro = null;
		
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}


	public String[] getIdFiscalizarOSAcompanhamentoNao() {
		return idFiscalizarOSAcompanhamentoNao;
	}

	public void setIdFiscalizarOSAcompanhamentoNao(String[] idFiscalizarOSAcompanhamentoNao) {
		this.idFiscalizarOSAcompanhamentoNao = idFiscalizarOSAcompanhamentoNao;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	public String[] getIdRA() {
		return idRA;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public void setIdRA(String[] idRA) {
		this.idRA = idRA;
	}

	public String[] getIdFiscalizarOSAcompanhamento() {
		return idFiscalizarOSAcompanhamento;
	}

	public void setIdFiscalizarOSAcompanhamento(String[] idFiscalizarOSAcompanhamento) {
		this.idFiscalizarOSAcompanhamento = idFiscalizarOSAcompanhamento;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}


	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}


	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}


	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}


	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}


	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}


	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}


	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}


	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}


	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoBairro() {
		return codigoBairro;
	}

	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	public String getIdMotivoNaoAceite() {
		return idMotivoNaoAceite;
	}

	public void setIdMotivoNaoAceite(String idMotivoNaoAceite) {
		this.idMotivoNaoAceite = idMotivoNaoAceite;
	}

	public String getIndicadorObrigatorio() {
		return indicadorObrigatorio;
	}

	public void setIndicadorObrigatorio(String indicadorObrigatorio) {
		this.indicadorObrigatorio = indicadorObrigatorio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPeriodoGeracaoFinal() {
		return periodoGeracaoFinal;
	}

	public void setPeriodoGeracaoFinal(String periodoGeracaoFinal) {
		this.periodoGeracaoFinal = periodoGeracaoFinal;
	}

	public String getPeriodoGeracaoInicial() {
		return periodoGeracaoInicial;
	}

	public void setPeriodoGeracaoInicial(String periodoGeracaoInicial) {
		this.periodoGeracaoInicial = periodoGeracaoInicial;
	}

	public String getPeriodoProgramacaoFinal() {
		return periodoProgramacaoFinal;
	}

	public void setPeriodoProgramacaoFinal(String periodoProgramacaoFinal) {
		this.periodoProgramacaoFinal = periodoProgramacaoFinal;
	}

	public String getPeriodoProgramacaoInicial() {
		return periodoProgramacaoInicial;
	}

	public void setPeriodoProgramacaoInicial(String periodoProgramacaoInicial) {
		this.periodoProgramacaoInicial = periodoProgramacaoInicial;
	}

	public String getAreaBairro() {
		return areaBairro;
	}

	public void setAreaBairro(String areaBairro) {
		this.areaBairro = areaBairro;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getOrigemOrdemServico() {
		return origemOrdemServico;
	}

	public void setOrigemOrdemServico(String origemOrdemServico) {
		this.origemOrdemServico = origemOrdemServico;
	}

	public String getServicoEmCampo() {
		return servicoEmCampo;
	}

	public void setServicoEmCampo(String servicoEmCampo) {
		this.servicoEmCampo = servicoEmCampo;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getIdOS() {
		return idOS;
	}

	public void setIdOS(String idOS) {
		this.idOS = idOS;
	}

}