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
package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author Rodrigo Cabral, Amelia Pessoa
 * @date 21/09/2010, 23/05/2012
 */
public class InserirConsumoAnormalidadeAcaoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;


	private String consumoAnormalidade;	
	
	private String categoria;
	
	private String imovelPerfil;
	
	private String indicadorValidarRetificacao = "2";
	
	private String codigoOrdemMes;	
	
    private String leituraAnormalidadeConsumo;

    private String numerofatorConsumo;

    private String indicadorGeracaoCarta = "2";
    
    private String idServicoTipo;
    
    private String desServicoTipo;
    
    private String solicitacaoTipo;

    private String solicitacaoTipoEspecificacao;   
    
    private String descricaoContaMensagem;

    
    public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getLeituraAnormalidadeConsumo() {
		return leituraAnormalidadeConsumo;
	}

	public void setLeituraAnormalidadeConsumo(String leituraAnormalidadeConsumo) {
		this.leituraAnormalidadeConsumo = leituraAnormalidadeConsumo;
	}

	public String getNumerofatorConsumo() {
		return numerofatorConsumo;
	}

	public void setNumerofatorConsumo(String numerofatorConsumo) {
		this.numerofatorConsumo = numerofatorConsumo;
	}

	public String getIndicadorGeracaoCarta() {
		return indicadorGeracaoCarta;
	}

	public void setIndicadorGeracaoCarta(String indicadorGeracaoCarta) {
		this.indicadorGeracaoCarta = indicadorGeracaoCarta;
	}

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getDesServicoTipo() {
		return desServicoTipo;
	}

	public void setDesServicoTipo(String desServicoTipo) {
		this.desServicoTipo = desServicoTipo;
	}

	public String getSolicitacaoTipo() {
		return solicitacaoTipo;
	}

	public void setSolicitacaoTipo(String solicitacaoTipo) {
		this.solicitacaoTipo = solicitacaoTipo;
	}

	public String getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(String solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public String getDescricaoContaMensagem() {
		return descricaoContaMensagem;
	}

	public void setDescricaoContaMensagem(String descricaoContaMensagem) {
		this.descricaoContaMensagem = descricaoContaMensagem;
	}

	public String getCodigoOrdemMes() {
		return codigoOrdemMes;
	}

	public void setCodigoOrdemMes(String codigoOrdemMes) {
		this.codigoOrdemMes = codigoOrdemMes;
	}

	public String getIndicadorValidarRetificacao() {
		return indicadorValidarRetificacao;
	}

	public void setIndicadorValidarRetificacao(
			String indicadorValidarRetificacao) {
		this.indicadorValidarRetificacao = indicadorValidarRetificacao;
	}	
   
}
