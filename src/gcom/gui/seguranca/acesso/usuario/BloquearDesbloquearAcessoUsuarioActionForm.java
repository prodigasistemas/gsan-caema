/**
 * 
 */
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
package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 08/06/2006
 */
public class BloquearDesbloquearAcessoUsuarioActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String login;

	private String usuarioSituacao;
	
	/* RM 3892 - Inclusão de dados de afastamento do usuário */
	private String icAfastamentoTemporario="3";
	private String motivoAfastamento;
	private String dtAfastamentoInicial;
	private String dtAfastamentoFinal;
	private String dtAfastamentoFinalAux;
	
	private String idUsuarioEspelho;
	private String nomeUsuarioEspelho;
	private String observacaoAfastamento;
	private Integer idAfastamento;
	private String erroUsuarioEspelho;
	
	/* RM7146 - Pesquisar usuário por lupa */
	private String usuarioNaoEncontrado = "true";
	private String nomeUsuario;
	private String tipoPesquisa;
	
	/**
	 * @return Retorna o campo login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            O login a ser setado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Retorna o campo usuarioSituacao.
	 */
	public String getUsuarioSituacao() {
		return usuarioSituacao;
	}

	/**
	 * @param usuarioSituacao
	 *            O usuarioSituacao a ser setado.
	 */
	public void setUsuarioSituacao(String usuarioSituacao) {
		this.usuarioSituacao = usuarioSituacao;
	}

	public String getIcAfastamentoTemporario() {
		return icAfastamentoTemporario;
	}

	public void setIcAfastamentoTemporario(String icAfastamentoTemporario) {
		this.icAfastamentoTemporario = icAfastamentoTemporario;
	}

	public String getMotivoAfastamento() {
		return motivoAfastamento;
	}

	public void setMotivoAfastamento(String motivoAfastamento) {
		this.motivoAfastamento = motivoAfastamento;
	}

	public String getDtAfastamentoInicial() {
		return dtAfastamentoInicial;
	}

	public void setDtAfastamentoInicial(String dtAfastamentoInicial) {
		this.dtAfastamentoInicial = dtAfastamentoInicial;
	}

	public String getDtAfastamentoFinal() {
		return dtAfastamentoFinal;
	}

	public void setDtAfastamentoFinal(String dtAfastamentoFinal) {
		this.dtAfastamentoFinal = dtAfastamentoFinal;
	}

	public String getIdUsuarioEspelho() {
		return idUsuarioEspelho;
	}

	public void setIdUsuarioEspelho(String idUsuarioEspelho) {
		this.idUsuarioEspelho = idUsuarioEspelho;
	}

	public String getNomeUsuarioEspelho() {
		return nomeUsuarioEspelho;
	}

	public void setNomeUsuarioEspelho(String nomeUsuarioEspelho) {
		this.nomeUsuarioEspelho = nomeUsuarioEspelho;
	}

	public String getObservacaoAfastamento() {
		return observacaoAfastamento;
	}

	public void setObservacaoAfastamento(String observacaoAfastamento) {
		this.observacaoAfastamento = observacaoAfastamento;
	}

	public Integer getIdAfastamento() {
		return idAfastamento;
	}

	public void setIdAfastamento(Integer idAfastamento) {
		this.idAfastamento = idAfastamento;
	}

	public String getDtAfastamentoFinalAux() {
		return dtAfastamentoFinalAux;
	}

	public void setDtAfastamentoFinalAux(String dtAfastamentoFinalAux) {
		this.dtAfastamentoFinalAux = dtAfastamentoFinalAux;
	}

	public String getErroUsuarioEspelho() {
		return erroUsuarioEspelho;
	}

	public void setErroUsuarioEspelho(String erroUsuarioEspelho) {
		this.erroUsuarioEspelho = erroUsuarioEspelho;
	}

	public String getUsuarioNaoEncontrado() {
		return usuarioNaoEncontrado;
	}

	public void setUsuarioNaoEncontrado(
			String usuarioNaoEncontrado) {
		this.usuarioNaoEncontrado = usuarioNaoEncontrado;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
