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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Fernanda Almeida
 * @date 07/11/2006
 */
public class AtualizarTransferirSolicitacaoEspecificacaoAction extends
		GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		TransferirSolicitacaoTipoActionForm form = (TransferirSolicitacaoTipoActionForm) actionForm;

		// [SB0001] inclui nova especificação
		SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
		solicitacaoTipo.setId(Integer.valueOf(form.getIdNovoSolicitacaoTipo()));

		String[] idsRegistros = form.getIdsRegistros();
		for (int i = 0; i < idsRegistros.length; i++) {

			FiltroSolicitacaoTipoEspecificacao filtroEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroEspecificacao.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.ID, idsRegistros[i]));
			Collection<?> colSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());
			SolicitacaoTipoEspecificacao solTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
					.retonarObjetoDeColecao(colSolicitacaoTipoEspecificacao);

			// [SB0001] altera indicador da especificação para 2
			solTipoEspecificacao.setIndicadorUso(ConstantesSistema.NAO);
			fachada.atualizar(solTipoEspecificacao);

			solTipoEspecificacao.setSolicitacaoTipo(solicitacaoTipo);
			solTipoEspecificacao.setUltimaAlteracao(new Date());
			solTipoEspecificacao.setIndicadorUso(ConstantesSistema.SIM);

			fachada.inserir(solTipoEspecificacao);

			// limpa os campos do form
			form.setIdNovoGrupoSolicitacaoTipo("");
			form.setIdNovoSolicitacaoTipo("");

		}

		// Monta a página de sucesso
		montarPaginaSucesso(
				httpServletRequest,
				"Especificações transferidas para o novo tipo de solicitação informado",
				"Realizar outra tranferência de especificação",
				"filtrarTipoSolicitacaoEspecificacaoAction.do");

		return retorno;
	}
}
