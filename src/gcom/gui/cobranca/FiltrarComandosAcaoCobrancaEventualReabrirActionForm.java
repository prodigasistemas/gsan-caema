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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0326] Filtrar Comandos de A��o de Cobran�a
 * 
 * @author R�mulo Aur�lio
 * @since 12/11/2012
 */

public class FiltrarComandosAcaoCobrancaEventualReabrirActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String[] grupoCobranca;

	private String[] acaoCobranca;

	private String[] atividadeCobranca;

	private String periodoEncerramentoComandoInicial;

	private String periodoEncerramentoComandoFinal;

	private String situacaoComando;

	private String indicadorCriterio;

	private String criterioCobranca;

	private String nomeCriterioCobranca;

	private String dataEmissaoInicio;

	private String dataEmissaoFim;

	private String[] IdCobrancaAcaoAtividadeComando;

	private String[] situacaoFiscalizacao;

	public String[] getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String[] grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String[] getAcaoCobranca() {
		return acaoCobranca;
	}

	public void setAcaoCobranca(String[] acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	public String[] getAtividadeCobranca() {
		return atividadeCobranca;
	}

	public void setAtividadeCobranca(String[] atividadeCobranca) {
		this.atividadeCobranca = atividadeCobranca;
	}

	public String getPeriodoEncerramentoComandoInicial() {
		return periodoEncerramentoComandoInicial;
	}

	public void setPeriodoEncerramentoComandoInicial(String periodoEncerramentoComandoInicial) {
		this.periodoEncerramentoComandoInicial = periodoEncerramentoComandoInicial;
	}

	public String getPeriodoEncerramentoComandoFinal() {
		return periodoEncerramentoComandoFinal;
	}

	public void setPeriodoEncerramentoComandoFinal(String periodoEncerramentoComandoFinal) {
		this.periodoEncerramentoComandoFinal = periodoEncerramentoComandoFinal;
	}

	public String getSituacaoComando() {
		return situacaoComando;
	}

	public void setSituacaoComando(String situacaoComando) {
		this.situacaoComando = situacaoComando;
	}

	public String getIndicadorCriterio() {
		return indicadorCriterio;
	}

	public void setIndicadorCriterio(String indicadorCriterio) {
		this.indicadorCriterio = indicadorCriterio;
	}

	public String getCriterioCobranca() {
		return criterioCobranca;
	}

	public void setCriterioCobranca(String criterioCobranca) {
		this.criterioCobranca = criterioCobranca;
	}

	public String getNomeCriterioCobranca() {
		return nomeCriterioCobranca;
	}

	public void setNomeCriterioCobranca(String nomeCriterioCobranca) {
		this.nomeCriterioCobranca = nomeCriterioCobranca;
	}

	public String getDataEmissaoInicio() {
		return dataEmissaoInicio;
	}

	public void setDataEmissaoInicio(String dataEmissaoInicio) {
		this.dataEmissaoInicio = dataEmissaoInicio;
	}

	public String getDataEmissaoFim() {
		return dataEmissaoFim;
	}

	public void setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim = dataEmissaoFim;
	}

	public String[] getIdCobrancaAcaoAtividadeComando() {
		return IdCobrancaAcaoAtividadeComando;
	}

	public void setIdCobrancaAcaoAtividadeComando(String[] idCobrancaAcaoAtividadeComando) {
		IdCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}

	public String[] getSituacaoFiscalizacao() {
		return situacaoFiscalizacao;
	}

	public void setSituacaoFiscalizacao(String[] situacaoFiscalizacao) {
		this.situacaoFiscalizacao = situacaoFiscalizacao;
	}


}
