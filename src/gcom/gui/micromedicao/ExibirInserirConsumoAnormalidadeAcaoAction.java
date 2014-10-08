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
import gcom.micromedicao.consumo.ConsumoAnormalidadeAtividadeAcaoMensal;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
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

/**
 * [UC1057] Inserir Consumo Anormalidade e A��o
 * 
 * 
 * @author Rodrigo Cabral
 * @date 29/09/2010
 */
public class ExibirInserirConsumoAnormalidadeAcaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirConsumoAnormalidadeAcao");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirConsumoAnormalidadeAcaoActionForm form = (InserirConsumoAnormalidadeAcaoActionForm) actionForm;

		//Desfazer
		String desfazer = httpServletRequest.getParameter("desfazer");
		if (desfazer != null && desfazer.trim().equals("S")) {
			this.limparTudo(form, sessao);			
		}
		
		//cole��o anormalidade consumo
		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
		filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.ID);		
		Collection<ConsumoAnormalidade> colecaoConsumoAnormalidade = fachada.pesquisar(
				filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());
		sessao.setAttribute("colecaoConsumoAnormalidade", colecaoConsumoAnormalidade);
		
		//Cole��o Categoria
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		Collection<Categoria> colecaoCategoria = fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		
		//Cole��o Perfil do Imovel		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.ID);		
		Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(
				filtroImovelPerfil,	ImovelPerfil.class.getName());
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
		//cole��o anormalidade leitura consumo	   
		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);		
		Collection<LeituraAnormalidadeConsumo> colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(
				filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo", colecaoLeituraAnormalidadeConsumo);

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			
			if (objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("2")|| objetoConsulta.trim().equals("3")) {
				// Faz a consulta de Localidade
				this.pesquisarServicoTipo(form,objetoConsulta, httpServletRequest);
			} else if (objetoConsulta.trim().equals("4")){
				//Adiciona novo Consumo Anormalidade a��o mensal no grid
				addConsumoAnormalidadeMensal(sessao, form);				
			} else if (objetoConsulta.trim().equals("5")){
				//Remover A��o Mensal do grid
				String id = httpServletRequest.getParameter("idAcaoMensal");
				removerAcaoMensal(sessao, Integer.parseInt(id));
			}
			
		}
		
		//cole��o tipo de solicita��o		   
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, "1"));		
		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
    					ConstantesSistema.INDICADOR_USO_DESATIVO));
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO,
    					ConstantesSistema.INDICADOR_USO_ATIVO));		
		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());		
		sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
		
		
		//cole��o tipo de solicita��o especifica��o 		   
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();		
		filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));
		if (form.getSolicitacaoTipo() != null && !form.getSolicitacaoTipo().equals("-1")){
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipo()));	
		}		

		Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
				filtroSolicitacaoTipoEspecificacao, 
				SolicitacaoTipoEspecificacao.class.getName());
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);

		// devolve o mapeamento de retorno
		return retorno;
	}
	
	private void limparTudo(InserirConsumoAnormalidadeAcaoActionForm form, HttpSession sessao) {
		limparAnormalidadeMensal(form);
		
		form.setConsumoAnormalidade("-1");
		form.setCategoria("-1");
		form.setImovelPerfil("-1");
				
		sessao.removeAttribute("colecaoAnormalidadeAcaoMensal");
	}

	private void removerAcaoMensal(HttpSession sessao, int idRemover) {
		Collection<ConsumoAnormalidadeAtividadeAcaoMensal> colecaoAnormalidadeAcaoMensal = 
				(Collection<ConsumoAnormalidadeAtividadeAcaoMensal>) sessao.getAttribute("colecaoAnormalidadeAcaoMensal");
		sessao.removeAttribute("colecaoAnormalidadeAcaoMensal");
		
		ConsumoAnormalidadeAtividadeAcaoMensal obj=null;
		for (ConsumoAnormalidadeAtividadeAcaoMensal consumo:colecaoAnormalidadeAcaoMensal){
			if (consumo.getId2().intValue()==idRemover){
				obj = consumo;
				break;
			}
		}
		
		colecaoAnormalidadeAcaoMensal.remove(obj);
		sessao.setAttribute("colecaoAnormalidadeAcaoMensal", colecaoAnormalidadeAcaoMensal);
	}

	private void addConsumoAnormalidadeMensal(HttpSession sessao, InserirConsumoAnormalidadeAcaoActionForm form) {
		
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
			InserirConsumoAnormalidadeAcaoActionForm form) {
		
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

	//Pesquisar Tipo de Servi�o 
	private void pesquisarServicoTipo(InserirConsumoAnormalidadeAcaoActionForm form,
			String objetoConsulta, HttpServletRequest httpServletRequest) {

			Object local = form.getIdServicoTipo();
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID,local));			
			// Recupera Tipo de Servi�o
			Collection<ServicoTipo> colecaoServicoTipo = 
				this.getFachada().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		
			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {

				ServicoTipo servicoTipo = 
					(ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				
				form.setIdServicoTipo(servicoTipo.getId().toString());
				form.setDesServicoTipo(servicoTipo.getDescricao());

			} else {
				form.setIdServicoTipo(null);
				form.setDesServicoTipo("Tipo de servi�o inexistente");
				httpServletRequest.setAttribute("servicoTipoInexistente","true");			
			}
	}
	
	
}