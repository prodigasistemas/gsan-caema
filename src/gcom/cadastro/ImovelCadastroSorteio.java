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
package gcom.cadastro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ImovelCadastroSorteio implements Serializable {		private static final long serialVersionUID = 1L;
    private Integer id;    private Integer numeroGerado;    private Short indicadorImovelApto;
    private Date dataInscricao;
    private String descricaoDiferenca;
    private String nomeCliente;
    private String rg;
    private Date dataNascimento;
    private String cpf;
    private String cnpj;
    private Short indicadorProprietario;
    private String email;
    private Integer dddFixo;
    private Integer telefoneFixo;
    private Integer dddCelular;
    private Integer telefoneCelular;
    private String logradouro;
    private Integer numeroEndereco;
    private String complemento;
    private Integer cep;
    private Date dataSorteio;
    private Integer numeroOrdemSorteio;
    private Date ultimaAlteracao;
    private Short indicadorParticipacaoSorteio;
    
    private Imovel imovel;
    private PremioSorteio premioSorteio;
    private Bairro bairro;
    private Usuario usuario;
    private UnidadeOrganizacional unidadeOrganizacional;
    

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNumeroGerado() {
		return numeroGerado;
	}


	public void setNumeroGerado(Integer numeroGerado) {
		this.numeroGerado = numeroGerado;
	}


	public Short getIndicadorImovelApto() {
		return indicadorImovelApto;
	}


	public void setIndicadorImovelApto(Short indicadorImovelApto) {
		this.indicadorImovelApto = indicadorImovelApto;
	}


	public Date getDataInscricao() {
		return dataInscricao;
	}


	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}


	public String getDescricaoDiferenca() {
		return descricaoDiferenca;
	}


	public void setDescricaoDiferenca(String descricaoDiferenca) {
		this.descricaoDiferenca = descricaoDiferenca;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public Short getIndicadorProprietario() {
		return indicadorProprietario;
	}


	public void setIndicadorProprietario(Short indicadorProprietario) {
		this.indicadorProprietario = indicadorProprietario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getDddFixo() {
		return dddFixo;
	}


	public void setDddFixo(Integer dddFixo) {
		this.dddFixo = dddFixo;
	}


	public Integer getTelefoneFixo() {
		return telefoneFixo;
	}


	public void setTelefoneFixo(Integer telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}


	public Integer getDddCelular() {
		return dddCelular;
	}


	public void setDddCelular(Integer dddCelular) {
		this.dddCelular = dddCelular;
	}


	public Integer getTelefoneCelular() {
		return telefoneCelular;
	}


	public void setTelefoneCelular(Integer telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public Integer getNumeroEndereco() {
		return numeroEndereco;
	}


	public void setNumeroEndereco(Integer numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public Integer getCep() {
		return cep;
	}


	public void setCep(Integer cep) {
		this.cep = cep;
	}


	public Date getDataSorteio() {
		return dataSorteio;
	}


	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}


	public Integer getNumeroOrdemSorteio() {
		return numeroOrdemSorteio;
	}


	public void setNumeroOrdemSorteio(Integer numeroOrdemSorteio) {
		this.numeroOrdemSorteio = numeroOrdemSorteio;
	}


	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public Imovel getImovel() {
		return imovel;
	}


	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	public PremioSorteio getPremioSorteio() {
		return premioSorteio;
	}


	public void setPremioSorteio(PremioSorteio premioSorteio) {
		this.premioSorteio = premioSorteio;
	}


	public Bairro getBairro() {
		return bairro;
	}


	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}


	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	

	public Short getIndicadorParticipacaoSorteio() {
		return indicadorParticipacaoSorteio;
	}

	
	public void setIndicadorParticipacaoSorteio(Short indicadorParticipacaoSorteio) {
		this.indicadorParticipacaoSorteio = indicadorParticipacaoSorteio;
	}


	public String toString() {        return new ToStringBuilder(this)            .append("id", getId())            .toString();    }
}