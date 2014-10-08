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

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAtividadeAcao;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAtividadeAcaoMensal;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAtividadeAcao;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAtividadeAcaoMensal;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirAtualizarConsumoAnormalidadeAcaoAction extends GcomAction {
	/**
	 * [UC1058] Manter Consumo Anormalidade e Ação
	 * 
	 * 
	 * @author Rodrigo Cabral, Amelia Pessoa
	 * @date 05/10/2010, 23/05/2012
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarConsumoAnormalidadeAcao");				
		
		AtualizarConsumoAnormalidadeAcaoActionForm form = (AtualizarConsumoAnormalidadeAcaoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();		
		
		String codigo = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null || 
				sessao.getAttribute("idRegistroAtualizacao") != null ) {
			
			if ( httpServletRequest.getParameter("idRegistroAtualizacao") != null ) {
				sessao.setAttribute("idRegistroAtualizacao", httpServletRequest.getParameter("idRegistroAtualizacao"));
				codigo = httpServletRequest.getParameter("idRegistroAtualizacao");	
			} else {
				
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			}
			
		} else {
			codigo = ""+((ConsumoAnormalidadeAtividadeAcao)sessao.getAttribute("consumoAnormalidadeAcao")).getId();
		}
		form.setConsumoAnormalidadeAcaoId(codigo);
		
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.setAttribute("menu", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("menu");
		}
		
		if (codigo == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				codigo = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		//coleção anormalidade consumo
		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
		filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.ID);
		
		Collection<ConsumoAnormalidade> colecaoConsumoAnormalidade = fachada.pesquisar(
				filtroConsumoAnormalidade,
				ConsumoAnormalidade.class.getName());
		sessao.setAttribute("colecaoConsumoAnormalidade",
				colecaoConsumoAnormalidade);
		
		//Coleção Categoria
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria
				.setCampoOrderBy(FiltroCategoria.DESCRICAO);		
		Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		sessao.setAttribute("colecaoCategoria",	colecaoCategoria);
		
		//Coleção Perfil do Imovel		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.ID);		
		Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(
				filtroImovelPerfil,ImovelPerfil.class.getName());
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
		//coleção anormalidade leitura consumo	   
		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo
				.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);		
		Collection<LeituraAnormalidadeConsumo> colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(
				filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo", colecaoLeituraAnormalidadeConsumo);
		
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")) {

			this.pesquisarServicoTipo(form,objetoConsulta, httpServletRequest);	
			return retorno;
			
		} else if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			//Adicionar novo Consumo Anormalidade ação mensal no grid
			if (objetoConsulta.trim().equals("4")){
				//Adiciona novo Consumo Anormalidade ação mensal no grid
				addConsumoAnormalidadeMensal(sessao, form);		
			} else if (objetoConsulta.trim().equals("5")){
				//Remover Ação Mensal do grid
				String id = httpServletRequest.getParameter("idAcaoMensal");
				removerAcaoMensal(sessao, Integer.parseInt(id.trim()));
			}
			
			return retorno;
		}
		
		//coleção tipo de solicitação		   
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, "1"));		
		filtroSolicitacaoTipo
				.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);		
		filtroSolicitacaoTipo.adicionarParametro(
    			new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
    					ConstantesSistema.INDICADOR_USO_DESATIVO));
		filtroSolicitacaoTipo.adicionarParametro(
    			new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO,
    					ConstantesSistema.INDICADOR_USO_ATIVO));		
		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());		
		sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);		
		
		ConsumoAnormalidadeAtividadeAcao consumoAnormalidadeAtividadeAcao = null;
		
		if (codigo != null && !codigo.trim().equals("") && Integer.parseInt(codigo) > 0) {
			
			if (httpServletRequest.getParameter("menu") != null) {
				
				form.setConsumoAnormalidade(codigo);
				FiltroConsumoAnormalidadeAtividadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAtividadeAcao();
				filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcao.CONSUMO_ANORMALIDADE);
				filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcao.CATEGORIA);
				filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcao.IMOVEL_PERFIL);
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidadeAtividadeAcao.ID, codigo));
				
				Collection<ConsumoAnormalidadeAtividadeAcao> colecaoConsumoAnormalidadeAcao = fachada.pesquisar
					(filtroConsumoAnormalidadeAcao,ConsumoAnormalidadeAtividadeAcao.class.getName());
				
				if(colecaoConsumoAnormalidadeAcao != null && !colecaoConsumoAnormalidadeAcao.isEmpty()){
				
					
					consumoAnormalidadeAtividadeAcao = (ConsumoAnormalidadeAtividadeAcao)Util.retonarObjetoDeColecao(colecaoConsumoAnormalidadeAcao);
				
					form.setConsumoAnormalidade(consumoAnormalidadeAtividadeAcao.getConsumoAnormalidade().getId().toString());
				
					if (consumoAnormalidadeAtividadeAcao.getCategoria() != null && !consumoAnormalidadeAtividadeAcao.getCategoria().getId().equals("")){
							form.setCategoria(consumoAnormalidadeAtividadeAcao.getCategoria().getId().toString());
					}else{
						form.setCategoria("-1");
					}
				
					if (consumoAnormalidadeAtividadeAcao.getImovelPerfil() != null && !consumoAnormalidadeAtividadeAcao.getImovelPerfil().getId().equals("")){
							form.setImovelPerfil(consumoAnormalidadeAtividadeAcao.getImovelPerfil().getId().toString());
					}else{
						form.setImovelPerfil("-1");
					}
				
					form.setIndicadorValidarRetificacao(consumoAnormalidadeAtividadeAcao.getIndicadorValidarRetificacao().toString());
					
					//Obter coleção de ações mensais
					FiltroConsumoAnormalidadeAtividadeAcaoMensal filtroMensal = new FiltroConsumoAnormalidadeAtividadeAcaoMensal();
					filtroMensal.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidadeAtividadeAcaoMensal.CONSUMO_ANORMALIDADE_ATIVIDADE_ACAO_ID,
							codigo));
					filtroMensal.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcaoMensal.CONSUMO_ANORMALIDADE_ATIVIDADE_ACAO);
					filtroMensal.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcaoMensal.LEITURA_ANORMALIDADE_CONSUMO);
					filtroMensal.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcaoMensal.SERVICO_TIPO);
					filtroMensal.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoAnormalidadeAtividadeAcaoMensal.SOLICITACAO_TIPO_ESPECIFICACAO);
					Collection<ConsumoAnormalidadeAtividadeAcaoMensal> colecaoAnormalidadeAcaoMensal = Fachada.getInstancia().pesquisar(
							filtroMensal, ConsumoAnormalidadeAtividadeAcaoMensal.class.getName());		
					if (colecaoAnormalidadeAcaoMensal!=null && !colecaoAnormalidadeAcaoMensal.isEmpty()){
						sessao.setAttribute("colecaoAnormalidadeAcaoMensal", colecaoAnormalidadeAcaoMensal);
					}
					
					form.setIndicadorUso(consumoAnormalidadeAtividadeAcao.getIndicadorUso().toString());
					
					//Limpa campos de ação mensal
					limparAnormalidadeMensal(form);
				}
								
				
				//coleção tipo de solicitação especificação 				   
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
				.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);				
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));

				if (form.getSolicitacaoTipo() != null && !form.getSolicitacaoTipo().equals("-1")){
					filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipo()));	
				}		

				Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
						filtroSolicitacaoTipoEspecificacao, 
						SolicitacaoTipoEspecificacao.class.getName());
				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
			}
			
			
			sessao.setAttribute("atualizarConsumoAnormalidadeAcao", consumoAnormalidadeAtividadeAcao);

			if (sessao.getAttribute("colecaoConsumoAnormdalidadeAcao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarConsumoAnormalidadeAcaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarConsumoAnormalidadeAcaoAction.do");
			}
			
		}

		return retorno;
	
	}

	//Pesquisar Tipo de Serviço 
	private void pesquisarServicoTipo(AtualizarConsumoAnormalidadeAcaoActionForm form,
			String objetoConsulta, HttpServletRequest httpServletRequest) {

			Object local = form.getIdServicoTipo();			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(
				new ParametroSimples(FiltroServicoTipo.ID,local));
			
			// Recupera Tipo de Serviço
			Collection<ServicoTipo> colecaoServicoTipo = 
				this.getFachada().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		
			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = 
					(ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				
				form.setIdServicoTipo(servicoTipo.getId().toString());
				form.setDesServicoTipo(servicoTipo.getDescricao());
				
			} else {				
				form.setIdServicoTipo(null);
				form.setDesServicoTipo("Tipo de serviço inexistente");
				httpServletRequest.setAttribute("servicoTipoInexistente","true");
			}
	}
	
	private void addConsumoAnormalidadeMensal(HttpSession sessao, AtualizarConsumoAnormalidadeAcaoActionForm form) {
		
		Collection<ConsumoAnormalidadeAtividadeAcaoMensal> colecaoAnormalidadeAcaoMensal = 
				(Collection<ConsumoAnormalidadeAtividadeAcaoMensal>) sessao.getAttribute("colecaoAnormalidadeAcaoMensal");
		
		if (colecaoAnormalidadeAcaoMensal==null){
			colecaoAnormalidadeAcaoMensal = new ArrayList<ConsumoAnormalidadeAtividadeAcaoMensal>();
		}
		//Preenche objeto
		ConsumoAnormalidadeAtividadeAcaoMensal consumo = new ConsumoAnormalidadeAtividadeAcaoMensal();
		consumo.setCodigoOrdemMes(Integer.parseInt(form.getCodigoOrdemMes()));		
		consumo.setDescricaoContaMensagem(form.getDescricaoContaMensagem());
		consumo.setIndicadorGeracaoCarta(new Short(form.getIndicadorGeracaoCarta()));		
		consumo.setNumerofatorConsumo(new BigDecimal(form.getNumerofatorConsumo()));
		
		FiltroLeituraAnormalidadeConsumo filtroLeitura = new FiltroLeituraAnormalidadeConsumo();
		filtroLeitura.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidadeConsumo.ID, form.getLeituraAnormalidadeConsumo()));
		Collection<LeituraAnormalidadeConsumo> colecaoLeitura = Fachada.getInstancia().pesquisar(
				filtroLeitura, LeituraAnormalidadeConsumo.class.getName());		
		LeituraAnormalidadeConsumo leitura = (LeituraAnormalidadeConsumo) Util.retonarObjetoDeColecao(colecaoLeitura);
		consumo.setLeituraAnormalidadeConsumo(leitura);
		
		if (form.getIdServicoTipo()!=null && !form.getIdServicoTipo().equals("")){
			ServicoTipo servico = new ServicoTipo();
			servico.setId(Integer.parseInt(form.getIdServicoTipo()));
			consumo.setServicoTipo(servico);		
			
			SolicitacaoTipoEspecificacao solEspecificacao = new SolicitacaoTipoEspecificacao();
			solEspecificacao.setId(Integer.parseInt(form.getSolicitacaoTipoEspecificacao()));
			consumo.setSolicitacaoTipoEspecificacao(solEspecificacao);
		}
				
		consumo.setId2(colecaoAnormalidadeAcaoMensal.size());
		
		colecaoAnormalidadeAcaoMensal.add(consumo);
		sessao.setAttribute("colecaoAnormalidadeAcaoMensal", colecaoAnormalidadeAcaoMensal);
		
		//Limpa os campos
		limparAnormalidadeMensal(form);
	}

	private void limparAnormalidadeMensal(
			AtualizarConsumoAnormalidadeAcaoActionForm form) {
		
		form.setCodigoOrdemMes("");
		form.setLeituraAnormalidadeConsumo("-1");
		form.setNumerofatorConsumo("");
		form.setIndicadorGeracaoCarta("2");
		form.setIdServicoTipo("");
		form.setDesServicoTipo("");
		form.setSolicitacaoTipo("-1");
		form.setSolicitacaoTipoEspecificacao("-1");
		form.setDescricaoContaMensagem("");		
	}
	
	private void removerAcaoMensal(HttpSession sessao, int idRemover) {
		Collection<ConsumoAnormalidadeAtividadeAcaoMensal> colecaoAnormalidadeAcaoMensal = 
				(Collection<ConsumoAnormalidadeAtividadeAcaoMensal>) sessao.getAttribute("colecaoAnormalidadeAcaoMensal");
		sessao.removeAttribute("colecaoAnormalidadeAcaoMensal");
		
		ConsumoAnormalidadeAtividadeAcaoMensal obj=null;
		for (ConsumoAnormalidadeAtividadeAcaoMensal consumo:colecaoAnormalidadeAcaoMensal){
			if (consumo.getId() ==null) {
				if (consumo.getId2().intValue()==idRemover){
					obj = consumo;
					break;
				}
			} else if (consumo.getId().intValue()==idRemover){
				obj = consumo;
				break;
			}
		}
		
		colecaoAnormalidadeAcaoMensal.remove(obj);
		sessao.setAttribute("colecaoAnormalidadeAcaoMensal", colecaoAnormalidadeAcaoMensal);
	}	
}
