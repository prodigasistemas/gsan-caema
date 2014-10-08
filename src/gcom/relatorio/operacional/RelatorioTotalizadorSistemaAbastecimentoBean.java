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
package gcom.relatorio.operacional;

import gcom.relatorio.RelatorioBean;


public class RelatorioTotalizadorSistemaAbastecimentoBean implements RelatorioBean {
	
	private String sistemaAbastecimento;
	private String subsistemaAbastecimento;
	private String setorAbastecimento;
	private String distritoOperacional;
	private String areaOperacional;
	private String zonaPressao;
	private String matriculaImovel;
	private String nomeUsuario;
	private String localidade;
	private String situacaoAgua;
	private String situacaoMedicao;
	private String volumeMedido;
	private String volumeFaturado;
	private Integer quantidade;
	
	
	public String getDistritoOperacional() {
		return distritoOperacional;
	}
	public void setDistritoOperacional(String distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}
	public String getSetorAbastecimento() {
		return setorAbastecimento;
	}
	public void setSetorAbastecimento(String setorAbastecimento) {
		this.setorAbastecimento = setorAbastecimento;
	}
	public String getSubsistemaAbastecimento() {
		return subsistemaAbastecimento;
	}
	public void setSubsistemaAbastecimento(String subsistemaAbastecimento) {
		this.subsistemaAbastecimento = subsistemaAbastecimento;
	}
	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}
	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSituacaoMedicao() {
		return situacaoMedicao;
	}
	public void setSituacaoMedicao(String situacaoMedicao) {
		this.situacaoMedicao = situacaoMedicao;
	}
	public String getVolumeMedido() {
		return volumeMedido;
	}
	public void setVolumeMedido(String volumeMedido) {
		this.volumeMedido = volumeMedido;
	}
	public String getVolumeFaturado() {
		return volumeFaturado;
	}
	public void setVolumeFaturado(String volumeFaturado) {
		this.volumeFaturado = volumeFaturado;
	}
	public String getAreaOperacional() {
		return areaOperacional;
	}
	public void setAreaOperacional(String areaOperacional) {
		this.areaOperacional = areaOperacional;
	}
	public String getZonaPressao() {
		return zonaPressao;
	}
	public void setZonaPressao(String zonaPressao) {
		this.zonaPressao = zonaPressao;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	

	
}