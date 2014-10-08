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
 * Magno Jean Gouveia Silveira
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
package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * <b>[UC0190]</b> Inserir Anormalidade de Leitura
 * </p>
 * 
 * <p>
 * Esta funcionalidade permite inserir uma Anormalidade de Leitura
 * </p>
 * 
 * @author Thiago Tenório, Magno Gouveia
 * @since 07/02/2007, 23/08/2011
 */
public class ExibirInserirAnormalidadeLeituraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirAnormalidadeLeitura");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirAnormalidadeLeituraActionForm inserirAnormalidadeLeituraActionForm = (InserirAnormalidadeLeituraActionForm) actionForm;

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario
			inserirAnormalidadeLeituraActionForm.setDescricao("");
			inserirAnormalidadeLeituraActionForm.setAbreviatura("");
			inserirAnormalidadeLeituraActionForm.setIndicadorRelativoHidrometro("");
			inserirAnormalidadeLeituraActionForm.setIndicadorImovelSemHidrometro("");
			inserirAnormalidadeLeituraActionForm.setUsoRestritoSistema("");
			inserirAnormalidadeLeituraActionForm.setPerdaTarifaSocial("");
			inserirAnormalidadeLeituraActionForm.setTipoServico("");
			inserirAnormalidadeLeituraActionForm.setConsumoLeituraNaoInformado("");
			inserirAnormalidadeLeituraActionForm.setConsumoLeituraInformado("");
			inserirAnormalidadeLeituraActionForm.setLeituraLeituraInformado("");
			inserirAnormalidadeLeituraActionForm.setLeituraLeituraNaoturaInformado("");
			inserirAnormalidadeLeituraActionForm.setNumeroFatorComLeitura("");
			inserirAnormalidadeLeituraActionForm.setNumeroFatorComLeitura("");
			inserirAnormalidadeLeituraActionForm.setIndicadorLeitura("0");
			inserirAnormalidadeLeituraActionForm.setIndicadorExibirAnormalidadeRelatorio("");
			inserirAnormalidadeLeituraActionForm.setIndicadorNaoImprimirConta("2");
			inserirAnormalidadeLeituraActionForm.setTipoSolicitacao("");
			inserirAnormalidadeLeituraActionForm.setEspecificacao("");
			inserirAnormalidadeLeituraActionForm.setEmpresa("");
			
		}

		if (inserirAnormalidadeLeituraActionForm.getTipoServico() == null
				|| inserirAnormalidadeLeituraActionForm.getTipoServico().equals("-1")) {
			Collection colecaoPesquisa = null;

			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

			filtroTipoServico.setCampoOrderBy(FiltroTipoServico.DESCRICAO);

			filtroTipoServico.adicionarParametro(new ParametroSimples(	FiltroTipoServico.INDICADOR_USO,
																		ConstantesSistema.INDICADOR_USO_ATIVO));
			

			// Retorna Tipo Serviço
			colecaoPesquisa = fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela localidade_porte foi encontrado
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",null,
													"Tipo de Servico");
			} else {
				sessao.setAttribute("colecaoTipoServico", colecaoPesquisa);
			}

		}
		
		/*
		 * Tipo de Solicitação - Carregando a coleção que irá ficar disponível
		 * para escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados
		 */
		Collection colecaoSolicitacaoTipo = (Collection) sessao
				.getAttribute("colecaoSolicitacaoTipo");

		if (colecaoSolicitacaoTipo == null) {

			FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo(
					FiltroSolicitacaoTipo.DESCRICAO);

			filtroSolicitacaoTipo.setConsultaSemLimites(true);

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
					SolicitacaoTipo.INDICADOR_USO_SISTEMA_NAO));

			colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo,
					SolicitacaoTipo.class.getName());

			if (colecaoSolicitacaoTipo == null
					|| colecaoSolicitacaoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"SOLICITACAO_TIPO");
			} else {
				sessao.setAttribute("colecaoSolicitacaoTipo",
						colecaoSolicitacaoTipo);
			}
		}

		/*
		 * Especificação - Carregando a coleção que irá ficar disponível para
		 * escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados [SB0036] –
		 * Habilita/Desabilita Conta
		 */
		String pesquisarEspecificacao = httpServletRequest
				.getParameter("pesquisarEspecificacao");

		if (pesquisarEspecificacao != null
				&& !pesquisarEspecificacao.equalsIgnoreCase("")) {

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(
					FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
							new Integer(inserirAnormalidadeLeituraActionForm
									.getTipoSolicitacao())));

			filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());

			if (colecaoSolicitacaoTipoEspecificacao == null
					|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"SOLICITACAO_TIPO_ESPECIFICACAO");
			} else {
				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);
			}

		}
		
		

		// coleção anormalidade consumo

		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);

		Collection colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo", colecaoLeituraAnormalidadeConsumo);

		// coleção anormalidade leitura

		FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
		filtroLeituraAnormalidadeLeitura.setCampoOrderBy(FiltroLeituraAnormalidadeLeitura.ID);

		Collection colecaoLeituraAnormalidadeLeitura = fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeLeitura", colecaoLeituraAnormalidadeLeitura);
		
		// Seta o indicador de não imprimir conta para o valor defaul NAO (2)
		inserirAnormalidadeLeituraActionForm.setIndicadorNaoImprimirConta("2");
		inserirAnormalidadeLeituraActionForm.setIndicadorExibirMensagemHidrometrosCalcada("2");
		inserirAnormalidadeLeituraActionForm.setIndicadorExibirMensagemHidrometrosSubstituidos("2");
		
		if(inserirAnormalidadeLeituraActionForm.getIndicadorAnormalidadeImpactaLeitura() == null)
			inserirAnormalidadeLeituraActionForm.setIndicadorAnormalidadeImpactaLeitura(ConstantesSistema.SIM.toString());
		
		inserirAnormalidadeLeituraActionForm.setIndicadorFotoObrigatoria("2");
		inserirAnormalidadeLeituraActionForm.setUsoRestritoSistema("2");
		inserirAnormalidadeLeituraActionForm.setPerdaTarifaSocial("2");
		
		/*
		 *  Empresa - Carregando a coleção que irá ficar disponível para
		 * escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados 
		 */
		Collection<?> colecaoEmpresa = (Collection<?>) sessao.getAttribute("colecaoEmpresa");
		if(Util.isVazioOrNulo(colecaoEmpresa)){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
		

		// devolve o mapeamento de retorno
		return retorno;
	}
	
}
