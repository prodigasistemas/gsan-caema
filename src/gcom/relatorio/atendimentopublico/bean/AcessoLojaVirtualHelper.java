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
package gcom.relatorio.atendimentopublico.bean;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;


public class AcessoLojaVirtualHelper {
	
	private String codigoTipoAtendimento;
	
	private String tipoAtendimento;
	
	private String quantidade;
	
	private String dataAtendimento;
	
	private String ipAcesso;
	
	private String qtdServicosExecutados;
	
	private String indicadorServicoExecutado;

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		if ( tipoAtendimento.equals(AcessoLojaVirtual.LOJAS_ATENDIMENTO_PRESENCIAL) ) {
			tipoAtendimento = "Lojas Atendimento Presencial";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.TELE_ATENDIMENTO)) {
			tipoAtendimento = "Tele Atendimento";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.AUTO_ATENDIMENTO)) {
			tipoAtendimento = "Auto Atendimento";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.NEGOCIACAO_DEBITO)) {
			tipoAtendimento = "Negociacao debito";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.ESTRUTURA_TARIFARIA)) {
			tipoAtendimento = "Estrutura Tarifaria";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.TARIFA_SOCIAL)) {
			tipoAtendimento = "Tarifa Social";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.NORMAS_INSTALACAO)) {
			tipoAtendimento = "Normas de Instalacao";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.SEGUNDA_VIA_CONTA)) {
			tipoAtendimento = "Segunda Via de Conta";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.DECLARACAO_ANUAL_QUITACAO_DEBITO)) {
			tipoAtendimento = "Declaracao Anual Quitacao de Debitos";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.RECEBIMENTO_FATURA_EMAIL)) {
			tipoAtendimento = "Recebimento Fatura E-mail";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.SOLICITAR_CONTA_BRAILE)) {
			tipoAtendimento = "Solicitar Conta Braile";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.OUTROS_SERVICOS)) {
			tipoAtendimento = "Outros Servicos";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.PARCELAMENTO_DEBITOS)) {
			tipoAtendimento = "Parcelamento de Debitos";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.ALTERAR_VENCIMENTO) ) {
			tipoAtendimento = "Alterar Vencimento";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.CERTIDAO_NEGATIVA_DEBITOS) ) {
			tipoAtendimento = "Certidão Negativa de débitos";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.CONTRATO_ADESAO_TACITA) ) {
			tipoAtendimento = "Contrato Adesão Tácita";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_PAGAMENTOS) ) {
			tipoAtendimento = "Consultar Pagamentos";
		}else if ( tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_CONSUMO_HISTORICO) ) {
			tipoAtendimento = "Consultar Consumo Histórico Água";

		}
		else if(tipoAtendimento.equals(AcessoLojaVirtual.ACOMPANHAR_REGISTRO_ATENDIMENTO))
			tipoAtendimento = "Acompanhar Registro de Atendimento";
		
		this.tipoAtendimento = tipoAtendimento;
	}
	

	public String getCodigoTipoAtendimento() {
		return codigoTipoAtendimento;
	}

	public void setCodigoTipoAtendimento(String codigoTipoAtendimento) {
		this.codigoTipoAtendimento = codigoTipoAtendimento;
	}
	
	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getIpAcesso() {
		return ipAcesso;
	}

	public void setIpAcesso(String ipAcesso) {
		this.ipAcesso = ipAcesso;
	}

	public String getQtdServicosExecutados() {
		return qtdServicosExecutados;
	}

	public void setQtdServicosExecutados(String qtdServicosExecutados) {
		this.qtdServicosExecutados = qtdServicosExecutados;
	}

	public String getIndicadorServicoExecutado() {
		return indicadorServicoExecutado;
	}

	public void setIndicadorServicoExecutado(String indicadorServicoExecutado) {
		this.indicadorServicoExecutado = indicadorServicoExecutado;
	}

}
