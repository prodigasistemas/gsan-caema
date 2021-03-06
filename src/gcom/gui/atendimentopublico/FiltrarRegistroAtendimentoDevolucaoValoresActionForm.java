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
package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarRegistroAtendimentoDevolucaoValoresActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String atualizar;
	
	private String idImovel;
	
	private String idImovelHidden;

	private String descricaoImovel;

	private String dataAtendimentoInicio;

	private String dataAtendimentoFim;
	
	private String numeroRA;
	
	private String[] perfilImovel;
	
	private String idRAConsulta;
	private String idImovelSelecionado;
	private String nomeClienteUsuarioImovelSelecionado;
	private String idsConta;
	private String totalPagamentoSelecionado = "0,00";
	private String totalCreditoAbatido = "0,00";

	private String idSolicitacaoTipoEspecificacao;
	private String descricaoTipoSolicitacao;
	private String codigoTipoDevolucao;
	
	private String indicadorGerarCreditoARealizar;
	private String saldoCreditoATransferir = "0,00";
	private String valorGuiaDevolucao;
	private String tipoDocumento;
	private String tipoDebito;
	private String nomeTipoDebito;
	private String idFuncionarioAnalista;
	private String nomeFuncionarioAnalista;
	private String idFuncionarioAutorizador;
	private String nomeFuncionarioAutorizador;
		
	public String getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	public String getDataAtendimentoFim() {
		return dataAtendimentoFim;
	}
	public void setDataAtendimentoFim(String dataAtendimentoFim) {
		this.dataAtendimentoFim = dataAtendimentoFim;
	}
	public String getDataAtendimentoInicio() {
		return dataAtendimentoInicio;
	}
	public void setDataAtendimentoInicio(String dataAtendimentoInicio) {
		this.dataAtendimentoInicio = dataAtendimentoInicio;
	}
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdImovelHidden() {
		return idImovelHidden;
	}
	public void setIdImovelHidden(String idImovelHidden) {
		this.idImovelHidden = idImovelHidden;
	}
	public String getIdImovelSelecionado() {
		return idImovelSelecionado;
	}
	public void setIdImovelSelecionado(String idImovelSelecionado) {
		this.idImovelSelecionado = idImovelSelecionado;
	}
	public String getIdRAConsulta() {
		return idRAConsulta;
	}
	public void setIdRAConsulta(String idRAConsulta) {
		this.idRAConsulta = idRAConsulta;
	}
	public String getIdsConta() {
		return idsConta;
	}
	public void setIdsConta(String idsConta) {
		this.idsConta = idsConta;
	}
	public String getNomeClienteUsuarioImovelSelecionado() {
		return nomeClienteUsuarioImovelSelecionado;
	}
	public void setNomeClienteUsuarioImovelSelecionado(
			String nomeClienteUsuarioImovelSelecionado) {
		this.nomeClienteUsuarioImovelSelecionado = nomeClienteUsuarioImovelSelecionado;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String[] getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getTotalPagamentoSelecionado() {
		return totalPagamentoSelecionado;
	}
	public void setTotalPagamentoSelecionado(String totalPagamentoSelecionado) {
		this.totalPagamentoSelecionado = totalPagamentoSelecionado;
	}
	public String getTotalCreditoAbatido() {
		return totalCreditoAbatido;
	}
	public void setTotalCreditoAbatido(String totalCreditoAbatido) {
		this.totalCreditoAbatido = totalCreditoAbatido;
	}
	public String getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}
	public void setIdSolicitacaoTipoEspecificacao(String idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}
	public String getDescricaoTipoSolicitacao() {
		return descricaoTipoSolicitacao;
	}
	public void setDescricaoTipoSolicitacao(String descricaoTipoSolicitacao) {
		this.descricaoTipoSolicitacao = descricaoTipoSolicitacao;
	}
	public String getCodigoTipoDevolucao() {
		return codigoTipoDevolucao;
	}
	public void setCodigoTipoDevolucao(String codigoTipoDevolucao) {
		this.codigoTipoDevolucao = codigoTipoDevolucao;
	}
	public String getIndicadorGerarCreditoARealizar() {
		return indicadorGerarCreditoARealizar;
	}
	public void setIndicadorGerarCreditoARealizar(String indicadorGerarCreditoARealizar) {
		this.indicadorGerarCreditoARealizar = indicadorGerarCreditoARealizar;
	}
	public String getSaldoCreditoATransferir() {
		return saldoCreditoATransferir;
	}
	public void setSaldoCreditoATransferir(String saldoCreditoATransferir) {
		this.saldoCreditoATransferir = saldoCreditoATransferir;
	}
	public String getValorGuiaDevolucao() {
		return valorGuiaDevolucao;
	}
	public void setValorGuiaDevolucao(String valorGuiaDevolucao) {
		this.valorGuiaDevolucao = valorGuiaDevolucao;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public String getNomeTipoDebito() {
		return nomeTipoDebito;
	}
	public void setNomeTipoDebito(String nomeTipoDebito) {
		this.nomeTipoDebito = nomeTipoDebito;
	}
	public String getIdFuncionarioAnalista() {
		return idFuncionarioAnalista;
	}
	public void setIdFuncionarioAnalista(String idFuncionarioAnalista) {
		this.idFuncionarioAnalista = idFuncionarioAnalista;
	}
	public String getNomeFuncionarioAnalista() {
		return nomeFuncionarioAnalista;
	}
	public void setNomeFuncionarioAnalista(String nomeFuncionarioAnalista) {
		this.nomeFuncionarioAnalista = nomeFuncionarioAnalista;
	}
	public String getIdFuncionarioAutorizador() {
		return idFuncionarioAutorizador;
	}
	public void setIdFuncionarioAutorizador(String idFuncionarioAutorizador) {
		this.idFuncionarioAutorizador = idFuncionarioAutorizador;
	}
	public String getNomeFuncionarioAutorizador() {
		return nomeFuncionarioAutorizador;
	}
	public void setNomeFuncionarioAutorizador(String nomeFuncionarioAutorizador) {
		this.nomeFuncionarioAutorizador = nomeFuncionarioAutorizador;
	}
	
}
