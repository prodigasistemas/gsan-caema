/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.seguranca.acesso.usuario;


import org.apache.struts.action.ActionForm;

/**
 * [UC1096] Atualizar Solicita��o de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 16/11/2010
 */

public class AtualizarSolicitacaoAcessoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idFuncionario; 
	private String nomeFuncionario;
	private String idFuncionarioSolicitante; 
	private String nomeFuncionarioSolicitante;
	private String idFuncionarioSuperior; 
	private String nomeFuncionarioSuperior;
	private String idTipoUsuario;
	private String idEmpresa;
	private String nomeUsuario; 
	private String cpf; 
	private String dataNascimento; 
	private String dataCadastramento; 
	private String idLotacao; 
	private String nomeLotacao;
	private String login; 
	private String email; 
	private String dataInicial; 
	private String dataFinal; 
	private String icNotificar; 
	private String atualizar;
	
	private String idSolicitacao;
	
	/* RM 3892 Implantar norma de senhas no GSAN */
	private String abrangencia = "";
	private String[] idsAbrangencia;
	private String gerenciaRegional = "";
	private String[] idsGerenciaRegional;
	private String unidadeNegocio = "";
	private String[] idsUnidadeNegocio;
	private String idLocalidade = "";
	private String nomeLocalidade = "";
	private String localidadeNaoEncontrada = "false";
	private String dominioEmail;
	private String icSuperintendente;
	private String competenciaRetificacao;
	private String idUsuarioSolicitacao;
	
	/* RM 3892.2 Implantar norma de senhas no GSAN */
	private String idUsuarioResponsavel;
	private String nomeUsuarioResponsavel;
	private String tipoPesquisa;
	
	
	/* RM7344 - Ajustes nas funcionalidades de Seguran�a de Acesso */
	private String grupo;
	private String nivel;
	private String[] permissoesEspeciais;
	
	
	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}

	public void setIdFuncionarioSolicitante(String idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}

	public String getNomeFuncionarioSolicitante() {
		return nomeFuncionarioSolicitante;
	}

	public void setNomeFuncionarioSolicitante(String nomeFuncionarioSolicitante) {
		this.nomeFuncionarioSolicitante = nomeFuncionarioSolicitante;
	}

	public String getIdFuncionarioSuperior() {
		return idFuncionarioSuperior;
	}

	public void setIdFuncionarioSuperior(String idFuncionarioSuperior) {
		this.idFuncionarioSuperior = idFuncionarioSuperior;
	}

	public String getNomeFuncionarioSuperior() {
		return nomeFuncionarioSuperior;
	}

	public void setNomeFuncionarioSuperior(String nomeFuncionarioSuperior) {
		this.nomeFuncionarioSuperior = nomeFuncionarioSuperior;
	}

	public String getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(String idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	public String getNomeLotacao() {
		return nomeLotacao;
	}

	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getIcNotificar() {
		return icNotificar;
	}

	public void setIcNotificar(String icNotificar) {
		this.icNotificar = icNotificar;
	}

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getAbrangencia() {
		return abrangencia;
	}

	public void setAbrangencia(String abrangencia) {
		this.abrangencia = abrangencia;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getLocalidadeNaoEncontrada() {
		return localidadeNaoEncontrada;
	}

	public void setLocalidadeNaoEncontrada(String localidadeNaoEncontrada) {
		this.localidadeNaoEncontrada = localidadeNaoEncontrada;
	}

	public String getDominioEmail() {
		return dominioEmail;
	}

	public void setDominioEmail(String dominioEmail) {
		this.dominioEmail = dominioEmail;
	}

	public String getIcSuperintendente() {
		return icSuperintendente;
	}

	public void setIcSuperintendente(String icSuperintendente) {
		this.icSuperintendente = icSuperintendente;
	}

	public String[] getIdsAbrangencia() {
		return idsAbrangencia;
	}

	public void setIdsAbrangencia(String[] idsAbrangencia) {
		this.idsAbrangencia = idsAbrangencia;
	}

	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}

	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}

	public String[] getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}

	public void setIdsUnidadeNegocio(String[] idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}

	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}

	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}

	public String getNomeUsuarioResponsavel() {
		return nomeUsuarioResponsavel;
	}

	public void setNomeUsuarioResponsavel(String nomeUsuarioResponsavel) {
		this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getIdSolicitacao() {
		return idSolicitacao;
	}

	public void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String[] getPermissoesEspeciais() {
		return permissoesEspeciais;
	}

	public void setPermissoesEspeciais(String[] permissoesEspeciais) {
		this.permissoesEspeciais = permissoesEspeciais;
	}

	public String getCompetenciaRetificacao() {
		return competenciaRetificacao;
	}

	public void setCompetenciaRetificacao(String competenciaRetificacao) {
		this.competenciaRetificacao = competenciaRetificacao;
	}

	public String getIdUsuarioSolicitacao() {
		return idUsuarioSolicitacao;
	}

	public void setIdUsuarioSolicitacao(String idUsuarioSolicitacao) {
		this.idUsuarioSolicitacao = idUsuarioSolicitacao;
	}

	public String getDataCadastramento() {
		return dataCadastramento;
	}

	public void setDataCadastramento(String dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}
	
	

}