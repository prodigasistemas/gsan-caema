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
 * Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0326] Filtrar Comandos de Ação de Cobrança
 * 
 * @author Rômulo Aurélio
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
