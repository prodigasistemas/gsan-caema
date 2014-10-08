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
package gcom.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

/**
 * @author Fernando Fontelles
 * @created 09/07/2010
 */
@ControleAlteracao()
public class EmailClienteAlterado extends ObjetoTransacao {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Cliente idCliente;
	private String nomeClienteAnterior;
	private String cpfAnterior;
	private String cnpjAnterior;
	private String emailAnterior;
	
	private String nomeSolicitante;
	private String cpfSolicitante;
	private String cnpjSolicitante;
	
	private String nomeClienteAtual;
	private String cpfAtual;
	private String cnpjAtual;
	private String emailAtual;
	
	private Date confirmacaoOnline;
	private Date solicitacaoOnline;

	private Date ultimaAlteracao;
	
	private Integer telefoneContato;
	
	public EmailClienteAlterado(){
		
	}

	public EmailClienteAlterado(Cliente idCliente, String nomeClienteAnterior, 
			String cpfAnterior, String cnpjAnterior, String emailAnterior, 
			String nomeSolicitante, String cpfSolicitante, String nomeClienteAtual, 
			String cpfAtual, String cnpjAtual, String emailAtual, Date solicitacaoOnline) {
		super();
		// TODO Auto-generated constructor stub
		this.idCliente = idCliente;
		this.nomeClienteAnterior = nomeClienteAnterior;
		this.cpfAnterior = cpfAnterior;
		this.cnpjAnterior = cnpjAnterior;
		this.emailAnterior = emailAnterior;
		this.nomeSolicitante = nomeSolicitante;
		this.cpfSolicitante = cpfSolicitante;
		this.nomeClienteAtual = nomeClienteAtual;
		this.cpfAtual = cpfAtual;
		this.cnpjAtual = cnpjAtual;
		this.emailAtual = emailAtual;
		this.solicitacaoOnline = solicitacaoOnline;
		
	}

	public Date getConfirmacaoOnline() {
		return confirmacaoOnline;
	}

	public void setConfirmacaoOnline(Date confirmacaoOnline) {
		this.confirmacaoOnline = confirmacaoOnline;
	}

	public Date getSolicitacaoOnline() {
		return solicitacaoOnline;
	}

	public void setSolicitacaoOnline(Date solicitacaoOnline) {
		this.solicitacaoOnline = solicitacaoOnline;
	}

	public String getCnpjAnterior() {
		return cnpjAnterior;
	}

	public void setCnpjAnterior(String cnpjAnterior) {
		this.cnpjAnterior = cnpjAnterior;
	}

	public String getCnpjAtual() {
		return cnpjAtual;
	}

	public void setCnpjAtual(String cnpjAtual) {
		this.cnpjAtual = cnpjAtual;
	}

	public String getCpfAnterior() {
		return cpfAnterior;
	}

	public void setCpfAnterior(String cpfAnterior) {
		this.cpfAnterior = cpfAnterior;
	}

	public String getCpfAtual() {
		return cpfAtual;
	}

	public void setCpfAtual(String cpfAtual) {
		this.cpfAtual = cpfAtual;
	}

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getEmailAnterior() {
		return emailAnterior;
	}

	public void setEmailAnterior(String emailAnterior) {
		this.emailAnterior = emailAnterior;
	}

	public String getEmailAtual() {
		return emailAtual;
	}

	public void setEmailAtual(String emailAtual) {
		this.emailAtual = emailAtual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeClienteAnterior() {
		return nomeClienteAnterior;
	}

	public void setNomeClienteAnterior(String nomeClienteAnterior) {
		this.nomeClienteAnterior = nomeClienteAnterior;
	}

	public String getNomeClienteAtual() {
		return nomeClienteAtual;
	}

	public void setNomeClienteAtual(String nomeClienteAtual) {
		this.nomeClienteAtual = nomeClienteAtual;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public Integer getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(Integer telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getCnpjSolicitante() {
		return cnpjSolicitante;
	}

	public void setCnpjSolicitante(String cnpjSolicitante) {
		this.cnpjSolicitante = cnpjSolicitante;
	}

}