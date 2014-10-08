/*
* Copyright (C) 2007-2007 the GSAN -Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place -Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN -Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.micromedicao.consumo;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsumoAnormalidadeAtividadeAcaoMensal implements Serializable {
	private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private Integer id2;

    private ConsumoAnormalidadeAtividadeAcao consumoAnormalidadeAtividadeAcao;
    
    private Integer codigoOrdemMes;
    
    private LeituraAnormalidadeConsumo leituraAnormalidadeConsumo;
    
    private BigDecimal numerofatorConsumo;

    private Short indicadorGeracaoCarta;

    private ServicoTipo servicoTipo;

    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
    
    private String descricaoContaMensagem;

   private Date ultimaAlteracao;

    public ConsumoAnormalidadeAtividadeAcaoMensal(){}
    
	public ConsumoAnormalidadeAtividadeAcaoMensal(Integer id,
			ConsumoAnormalidadeAtividadeAcao consumoAnormalidadeAtividadeAcao,
			Integer codigoOrdemMes,
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo,
			BigDecimal numerofatorConsumo, Short indicadorGeracaoCarta,
			ServicoTipo servicoTipo,
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
			String descricaoContaMensagem, 
			Date ultimaAlteracao) {
		
		this.id = id;
		this.consumoAnormalidadeAtividadeAcao = consumoAnormalidadeAtividadeAcao;
		this.codigoOrdemMes = codigoOrdemMes;
		this.leituraAnormalidadeConsumo = leituraAnormalidadeConsumo;
		this.numerofatorConsumo = numerofatorConsumo;
		this.indicadorGeracaoCarta = indicadorGeracaoCarta;
		this.servicoTipo = servicoTipo;
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
		this.descricaoContaMensagem = descricaoContaMensagem;		
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ConsumoAnormalidadeAtividadeAcao getConsumoAnormalidadeAtividadeAcao() {
		return consumoAnormalidadeAtividadeAcao;
	}

	public void setConsumoAnormalidadeAtividadeAcao(
			ConsumoAnormalidadeAtividadeAcao consumoAnormalidadeAtividadeAcao) {
		this.consumoAnormalidadeAtividadeAcao = consumoAnormalidadeAtividadeAcao;
	}

	public Integer getCodigoOrdemMes() {
		return codigoOrdemMes;
	}

	public void setCodigoOrdemMes(Integer codigoOrdemMes) {
		this.codigoOrdemMes = codigoOrdemMes;
	}

	public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumo() {
		return leituraAnormalidadeConsumo;
	}

	public void setLeituraAnormalidadeConsumo(
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo) {
		this.leituraAnormalidadeConsumo = leituraAnormalidadeConsumo;
	}

	public BigDecimal getNumerofatorConsumo() {
		return numerofatorConsumo;
	}

	public void setNumerofatorConsumo(BigDecimal numerofatorConsumo) {
		this.numerofatorConsumo = numerofatorConsumo;
	}

	public Short getIndicadorGeracaoCarta() {
		return indicadorGeracaoCarta;
	}

	public void setIndicadorGeracaoCarta(Short indicadorGeracaoCarta) {
		this.indicadorGeracaoCarta = indicadorGeracaoCarta;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public String getDescricaoContaMensagem() {
		return descricaoContaMensagem;
	}

	public void setDescricaoContaMensagem(String descricaoContaMensagem) {
		this.descricaoContaMensagem = descricaoContaMensagem;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}
   

}