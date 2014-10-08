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

package gcom.mobile;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ArquivoTextoOSCobrancaCliente extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	
	private ExecucaoOSPK comp_id;
	private Cliente clienteOriginal;
	private String nomeCliente;
	private String cpf;
	private String cnpj;
	private String rg;

	/** nullable persistent field */
    private String dddTelefone;
    
	/** nullable persistent field */
    private String telefone;
    
    /** nullable persistent field */
    private String ramalTelefone;
	

	private OrgaoExpedidorRg orgaoExpedidorRg;
	private UnidadeFederacao unidadeFederacao;
	private Date ultimaAlteracao;
	
	
	public ArquivoTextoOSCobrancaCliente(){

	}
	
	public ArquivoTextoOSCobrancaCliente( 
			Integer idArquivo, 
			Integer numeroOS,
			Integer idCliente,
			String nomeCliente,
			String cpf,
			String cnpj,
			String rg,
			String dddTelefone,
			String telefone,
			String ramalTelefone,
			OrgaoExpedidorRg orgaoExpedidorRg,
			UnidadeFederacao unidadeFederacao){
		ExecucaoOSPK pk = new ExecucaoOSPK();
		
		ArquivoTextoOSCobranca atoc = new ArquivoTextoOSCobranca();
		atoc.setId( idArquivo );
		pk.setArquivoTextoOSCobranca( atoc );
		
		OrdemServico os = new OrdemServico();
		os.setId( numeroOS );
		pk.setOrdemServico( os );
		
		this.setComp_id( pk );
		
		Cliente cliente = new Cliente();
		cliente.setId( idCliente );
		this.setClienteOriginal( cliente );
		
		this.cnpj = cnpj;
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.unidadeFederacao = unidadeFederacao;
		this.rg = rg;
		this.dddTelefone = dddTelefone;
		this.telefone = telefone;
		this.ramalTelefone = ramalTelefone;		
		
		this.setUltimaAlteracao(new Date());		
	}
	
	public ExecucaoOSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ExecucaoOSPK comp_id) {
		this.comp_id = comp_id;
	}	
		
	

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public OrgaoExpedidorRg getOrgaoExpedidorRg() {
		return orgaoExpedidorRg;
	}

	public void setOrgaoExpedidorRg(OrgaoExpedidorRg orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}
	
	public Cliente getClienteOriginal() {
		return clienteOriginal;
	}

	public void setClienteOriginal(Cliente clienteOriginal) {
		this.clienteOriginal = clienteOriginal;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
    public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRamalTelefone() {
		return ramalTelefone;
	}

	public void setRamalTelefone(String ramalTelefone) {
		this.ramalTelefone = ramalTelefone;
	}	
}