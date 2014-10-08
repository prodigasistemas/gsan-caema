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
package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;



public class SubstituirValidadorAutorizadorActionForm extends	ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String usuarioTipo;

	private String codigoUsuario;
	
	private String codigoNovoUsuario;

	private String[] solicitacaoSituacao;

	private String dataSolicitacao;
	
	private String descricaoUsuario;
	
	private String nomeNovoUsuario;
	
	private String nomeUsuario;
	
	private String usuarioOK;
	
	private String novoUsuarioOK;
	

	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getCodigoNovoUsuario() {
		return codigoNovoUsuario;
	}

	public void setCodigoNovoUsuario(String codigoNovoUsuario) {
		this.codigoNovoUsuario = codigoNovoUsuario;
	}

	public String[] getSolicitacaoSituacao() {
		return solicitacaoSituacao;
	}

	public void setSolicitacaoSituacao(String[] solicitacaoSituacao) {
		this.solicitacaoSituacao = solicitacaoSituacao;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public String getNomeNovoUsuario() {
		return nomeNovoUsuario;
	}

	public void setNomeNovoUsuario(String nomeNovoUsuario) {
		this.nomeNovoUsuario = nomeNovoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getUsuarioOK() {
		return usuarioOK;
	}

	public void setUsuarioOK(String usuarioOK) {
		this.usuarioOK = usuarioOK;
	}

	public String getNovoUsuarioOK() {
		return novoUsuarioOK;
	}

	public void setNovoUsuarioOK(String novoUsuarioOK) {
		this.novoUsuarioOK = novoUsuarioOK;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
}
