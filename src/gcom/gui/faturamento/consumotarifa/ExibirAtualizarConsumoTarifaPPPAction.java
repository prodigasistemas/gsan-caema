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
package gcom.gui.faturamento.consumotarifa;

import gcom.atendimentopublico.ligacaoagua.FiltroPerfilLigacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroTarifaTipoCalculo;
import gcom.faturamento.TarifaTipoCalculo;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategPpp;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigenPpp;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaCategPpp;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigenPpp;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaPppHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * UC0169-Manter Tarifa de Consumo
 * 
 * @author Administrador,Rafael Santos
 * @since 18/07/2006
 */
public class ExibirAtualizarConsumoTarifaPPPAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarConsumoTarifaPPPAction");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		String limparForm = (String) httpServletRequest
				.getParameter("limparForm");
		
		AtualizarConsumoTarifaPPPActionForm form = (AtualizarConsumoTarifaPPPActionForm) actionForm;

		String idVigencia = null;
		Collection colecaoVigencia = null;
		Short indicadorContratoDemanda=null;
		
       	if(httpServletRequest
        	.getParameter("idVigencia") != null){
       		idVigencia = (String) httpServletRequest
        	.getParameter("idVigencia");
        }else if(httpServletRequest
        	.getAttribute("idVigencia") != null){
        	idVigencia = (String) httpServletRequest
        	.getAttribute("idVigencia");
        }
		
       
       	
		if ((idVigencia != null)
				&& (!idVigencia.equals(""))) {
			
			if(httpServletRequest.getParameter("recarregar") == null){
				String [] ids = new String[1];
				ids[0] = idVigencia;
				
				//Colocado para ao tentar manter uma tarifa ja usada, so poder visualizar 
				// recebe os ids das vigencias a ser exclu�das do action e faz um
				// interator para fazer a verificacao de vigencia por vigencia
				for (int i = 0; i < ids.length; i++) {
					httpServletRequest.setAttribute(
							"caminhoActionConclusao",
							"/gsan/exibirAtualizarConsumoTarifaPPPAction.do?idVigencia="+ids[i]+"&recarregar=false");							
					
					return montarPaginaConfirmacao(
							"atencao.tarifa_consumo_ppp_consultar.confirma", httpServletRequest, actionMapping);
						
				}
			}
			
			//
			FiltroConsumoTarifaVigenPpp filtroConsumoTarifaVigenPpp = new FiltroConsumoTarifaVigenPpp();

			filtroConsumoTarifaVigenPpp
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaVigenPpp.ID, idVigencia));
			filtroConsumoTarifaVigenPpp
					.adicionarCaminhoParaCarregamentoEntidade("consumoTarifaPpp");
			filtroConsumoTarifaVigenPpp
			.adicionarCaminhoParaCarregamentoEntidade("consumoTarifaPpp.tarifaTipoCalculo");
			filtroConsumoTarifaVigenPpp.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigenPpp.CONSUMO_TARIFA_LIGACAO_AGUA_PERFIL);
			colecaoVigencia = fachada.pesquisar(
					filtroConsumoTarifaVigenPpp, ConsumoTarifaVigenPpp.class
							.getName());
			
			//Pesquisa os perfis de liga��o de �gua
			FiltroPerfilLigacao filtroPerfilLigacao = new FiltroPerfilLigacao();
			filtroPerfilLigacao.setCampoOrderBy(FiltroPerfilLigacao.DESCRICAO);
			filtroPerfilLigacao.adicionarParametro(new ParametroSimples(
					FiltroPerfilLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection colecaoLigacaoAguaPerfil = fachada.pesquisar(
					filtroPerfilLigacao, LigacaoAguaPerfil.class.getName());
			if (colecaoLigacaoAguaPerfil == null || colecaoLigacaoAguaPerfil.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
						null, "Tabela Ligacao Agua Perfil");
			} else {
				sessao.setAttribute("colecaoLigacaoAguaPerfil", colecaoLigacaoAguaPerfil);
				
				//seta o perfil de ligacao de agua
				Iterator iter = colecaoVigencia.iterator();
				ConsumoTarifaVigenPpp consumoTarifaVigenPpp = (ConsumoTarifaVigenPpp) iter.next();
				Integer idLigacaoAguaPerfil=0;
				if (consumoTarifaVigenPpp.getConsumoTarifaPpp()!=null){
					if (consumoTarifaVigenPpp.getConsumoTarifaPpp().getLigacaoAguaPerfil()!=null){
						idLigacaoAguaPerfil = consumoTarifaVigenPpp.getConsumoTarifaPpp().getLigacaoAguaPerfil().getId();
						sessao.setAttribute("selectLigacaoAguaPerfil", idLigacaoAguaPerfil);
					}else{
						sessao.setAttribute("selectLigacaoAguaPerfil", "");
					}
					//indicador Contrato Demanda
					indicadorContratoDemanda = consumoTarifaVigenPpp.getConsumoTarifaPpp().getIndicadorContratoDemanda();
					form.setIndicadorContratoDemanda(indicadorContratoDemanda.toString());
				}else{
					sessao.setAttribute("selectLigacaoAguaPerfil", "");
				}
			}
			
			// Pesquisa o tipo de calculo da tarifa
			FiltroTarifaTipoCalculo filtroTarifaTipoCalculo = new FiltroTarifaTipoCalculo();
			filtroTarifaTipoCalculo.setCampoOrderBy(FiltroTarifaTipoCalculo.DESCRICAO);
			filtroTarifaTipoCalculo.adicionarParametro(new ParametroSimples(
					FiltroPerfilLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection colecaoTarifaTipoCalculo = fachada.pesquisar(
					filtroTarifaTipoCalculo, TarifaTipoCalculo.class.getName());
			if (colecaoTarifaTipoCalculo == null || colecaoTarifaTipoCalculo.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
						null, "Tabela Tarifa Tipo Calculo");
			} else {
				sessao.setAttribute("colecaoTarifaTipoCalculo", colecaoTarifaTipoCalculo);
				
				//seta o tipo de calculo da tarifa
				Iterator iter = colecaoVigencia.iterator();
				ConsumoTarifaVigenPpp consumoTarifaVigenPpp = (ConsumoTarifaVigenPpp) iter.next();
				Integer idTarifaTipoCalculo=0;
				if (consumoTarifaVigenPpp.getConsumoTarifaPpp()!=null){
					if (consumoTarifaVigenPpp.getConsumoTarifaPpp().getTarifaTipoCalculo()!=null){
						idTarifaTipoCalculo = consumoTarifaVigenPpp.getConsumoTarifaPpp().getTarifaTipoCalculo().getId();
						sessao.setAttribute("selectTarifaTipoCalculo", idTarifaTipoCalculo);
					}else{
						sessao.setAttribute("selectTarifaTipoCalculo", "");
					}
				}else{
					sessao.setAttribute("selectTarifaTipoCalculo", "");
				}
			}

			FiltroConsumoTarifaCategPpp filtroConsumoTarifaCategPpp = new FiltroConsumoTarifaCategPpp();

			filtroConsumoTarifaCategPpp
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaCategPpp.CONSUMO_VIGENCIA_ID,
							idVigencia));
			
			filtroConsumoTarifaCategPpp
				.adicionarParametro(new ParametroSimples(
						FiltroConsumoTarifaCategPpp.SUBCATEGORIA_ID,
					"0"));

			filtroConsumoTarifaCategPpp
					.adicionarCaminhoParaCarregamentoEntidade("consumoTarifaVigenPpp");
			filtroConsumoTarifaCategPpp
				    .adicionarCaminhoParaCarregamentoEntidade("subCategoria");
			filtroConsumoTarifaCategPpp
					.adicionarCaminhoParaCarregamentoEntidade("categoria");
			filtroConsumoTarifaCategPpp
				.adicionarCaminhoParaCarregamentoEntidade("consumoTarifaFaixas");
			
			filtroConsumoTarifaCategPpp.setCampoOrderBy(
					FiltroConsumoTarifaCategoria.CATEGORIA_ID);
	
			Collection colecaoCategoria = fachada.pesquisar(
					filtroConsumoTarifaCategPpp, ConsumoTarifaCategPpp.class
							.getName());
			Iterator iterator = colecaoCategoria.iterator();
			Collection colecaoHelpers = new ArrayList();
			while (iterator.hasNext()) {
				ConsumoTarifaCategPpp consumoTarifaCategPpp = (ConsumoTarifaCategPpp) iterator
						.next();
				CategoriaFaixaConsumoTarifaPppHelper categoriaFaixaConsumoTarifaPppHelper = new CategoriaFaixaConsumoTarifaPppHelper(
						0, consumoTarifaCategPpp);
				categoriaFaixaConsumoTarifaPppHelper
						.setColecaoFaixas(consumoTarifaCategPpp
								.getConsumoTarifaFaixas());
				colecaoHelpers.add(categoriaFaixaConsumoTarifaPppHelper);
			}

			sessao.setAttribute("indicadorContratoDemanda", indicadorContratoDemanda+"");
			sessao.setAttribute("colecaoVigencia", colecaoVigencia);
			sessao.setAttribute("colecaoCategoria", colecaoHelpers);


		}
		
		// if ((sessao.getAttribute("Vigencia") != null)
		// && (!sessao.getAttribute("Vigencia").equals(""))) {
		// inserirConsumoTarifaActionForm.setDataVigencia((String) sessao
		// .getAttribute("Vigencia"));
		// }
		//
		// FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		//
		// filtroConsumoTarifa.adicionarParametro(new ParametroSimples(
		// FiltroConsumoTarifa.INDICADOR_USO,
		// ConstantesSistema.INDICADOR_USO_ATIVO));
		//
		// Collection colecaoConsumoTarifa = fachada.pesquisar(
		// filtroConsumoTarifa, ConsumoTarifa.class.getName());
		// sessao.setAttribute("colecaoConsumoTarifa", colecaoConsumoTarifa);
		// /*
		// * if (colecaoConsumoTarifa == null ||
		// colecaoConsumoTarifa.isEmpty()){
		// * //... }
		// */
		// // sessao.setAttribute("colecaoConsumoTarifa", colecaoConsumoTarifa);
		// sessao.setAttribute("inserirConsumoTarifaActionForm",
		// inserirConsumoTarifaActionForm);

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			form.reset(actionMapping,
					httpServletRequest);
		}

		return retorno;

	}
}
