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
			tipoAtendimento = "Certid�o Negativa de d�bitos";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.CONTRATO_ADESAO_TACITA) ) {
			tipoAtendimento = "Contrato Ades�o T�cita";
		} else if ( tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_PAGAMENTOS) ) {
			tipoAtendimento = "Consultar Pagamentos";
		}else if ( tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_CONSUMO_HISTORICO) ) {
			tipoAtendimento = "Consultar Consumo Hist�rico �gua";

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
