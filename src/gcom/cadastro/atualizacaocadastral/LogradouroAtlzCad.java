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
* Anderson Cabral do Nascimento
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
package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

/**
 * @author Anderson Cabral
 * @created 06/03/2013
 */
public class LogradouroAtlzCad extends ObjetoGcom{
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigo;
    private String nome;
    private String nomePopular;
    private String nomeLoteamento;
    private Municipio municipio;
    private LogradouroTitulo logradouroTitulo;
    private LogradouroTipo logradouroTipo;
    private Localidade localidade;
    private Empresa empresa;
    private Short indicadorAtualizado;
    private Date ultimaAlteracao;
    private Logradouro logradouro;

	public LogradouroAtlzCad() {
		super();
	}

	public LogradouroAtlzCad(Integer id, String codigo, String nome,
			String nomePopular, String nomeLoteamento, Municipio municipio,
			LogradouroTitulo logradouroTitulo, LogradouroTipo logradouroTipo,
			Localidade localidade, Empresa empresa, Short indicadorAtualizado,
			Date ultimaAlteracao) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.nomePopular = nomePopular;
		this.nomeLoteamento = nomeLoteamento;
		this.municipio = municipio;
		this.logradouroTitulo = logradouroTitulo;
		this.logradouroTipo = logradouroTipo;
		this.localidade = localidade;
		this.empresa = empresa;
		this.indicadorAtualizado = indicadorAtualizado;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	public String getNomeLoteamento() {
		return nomeLoteamento;
	}

	public void setNomeLoteamento(String nomeLoteamento) {
		this.nomeLoteamento = nomeLoteamento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public LogradouroTitulo getLogradouroTitulo() {
		return logradouroTitulo;
	}

	public void setLogradouroTitulo(LogradouroTitulo logradouroTitulo) {
		this.logradouroTitulo = logradouroTitulo;
	}

	public LogradouroTipo getLogradouroTipo() {
		return logradouroTipo;
	}

	public void setLogradouroTipo(LogradouroTipo logradouroTipo) {
		this.logradouroTipo = logradouroTipo;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
}
