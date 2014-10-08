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
package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;


public class RelatorioManterConsumoAnormalidadeAcaDetailsoBean implements RelatorioBean {
	
	private String descricaoConsumoACobrar;
	private String servicoTipo;
	private String tipoEspecificacao;
	private String ordemMes;
	private String indicadorGerarCarta;
	private String numeroFatorConsumo;
	private String mensagemConta;
	
	
	public RelatorioManterConsumoAnormalidadeAcaDetailsoBean(
			String descricaoConsumoACobrar, String servicoTipo,
			String tipoEspecificacao, String ordemMes,
			String indicadorGerarCarta, String numeroFatorConsumo, String mensagemConta) {
		super();
		this.descricaoConsumoACobrar = descricaoConsumoACobrar;
		this.servicoTipo = servicoTipo;
		this.tipoEspecificacao = tipoEspecificacao;
		this.ordemMes = ordemMes;
		this.indicadorGerarCarta = indicadorGerarCarta;
		this.numeroFatorConsumo = numeroFatorConsumo;
		this.mensagemConta = mensagemConta;
	}
	
	public String getDescricaoConsumoACobrar() {
		return descricaoConsumoACobrar;
	}
	public void setDescricaoConsumoACobrar(String descricaoConsumoACobrar) {
		this.descricaoConsumoACobrar = descricaoConsumoACobrar;
	}
	public String getServicoTipo() {
		return servicoTipo;
	}
	public void setServicoTipo(String servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	public String getTipoEspecificacao() {
		return tipoEspecificacao;
	}
	public void setTipoEspecificacao(String tipoEspecificacao) {
		this.tipoEspecificacao = tipoEspecificacao;
	}
	public String getOrdemMes() {
		return ordemMes;
	}
	public void setOrdemMes(String ordemMes) {
		this.ordemMes = ordemMes;
	}
	public String getIndicadorGerarCarta() {
		return indicadorGerarCarta;
	}
	public void setIndicadorGerarCarta(String indicadorGerarCarta) {
		this.indicadorGerarCarta = indicadorGerarCarta;
	}
	public String getNumeroFatorConsumo() {
		return numeroFatorConsumo;
	}
	public void setNumeroFatorConsumo(String numeroFatorConsumo) {
		this.numeroFatorConsumo = numeroFatorConsumo;
	}

	public String getMensagemConta() {
		return mensagemConta;
	}

	public void setMensagemConta(String mensagemConta) {
		this.mensagemConta = mensagemConta;
	} 
	
	

	
	

}
