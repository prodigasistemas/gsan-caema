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
 * along with this program; if not, wrie to the Free Software
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
package gcom.gui.financeiro;


import gcom.fachada.Fachada;
import gcom.financeiro.bean.ParametrosPerdasFiscaisHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1388] Consultar Quantidade Acessos a Base da Receita Federal
 *
 * @author Ricardo Germinio
 * @date 27/11/2012
 *
 */

public class InserirParametrosPerdasFiscaisAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ParametrosPerdasFiscaisActionForm form = (ParametrosPerdasFiscaisActionForm) actionForm;

		ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper = this.criarHelper(form);

		if(sessao.getAttribute("parametrosPerdasFiscaisHelper") != null ){
			parametrosPerdasFiscaisHelper = (ParametrosPerdasFiscaisHelper)sessao.getAttribute("parametrosPerdasFiscaisHelper");
			Integer anoMesReferenciaContabil = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil());
			if(!anoMesReferenciaContabil.equals(parametrosPerdasFiscaisHelper.getAnoMesReferenciaContabil())){
				parametrosPerdasFiscaisHelper.setAnoMesReferenciaContabil(anoMesReferenciaContabil);
			}
			
			if(form.getValorABaixar() != null && !form.getValorABaixar().equals("")){
    			BigDecimal valorABaixar = Util.formatarMoedaRealparaBigDecimal(form.getValorABaixar());
    			if(!(valorABaixar.compareTo(parametrosPerdasFiscaisHelper.getValorABaixar()) == 0)){
    				parametrosPerdasFiscaisHelper.setValorABaixar(valorABaixar);
    			}
			}
		}else{
			throw new ActionServletException("atencao.nao_pode_inserir");
		}

		if(fachada.pesquisarAnoMesReferencia(parametrosPerdasFiscaisHelper.getAnoMesReferenciaContabil()) != null
				&& fachada.pesquisarAnoMesReferencia(parametrosPerdasFiscaisHelper.getAnoMesReferenciaContabil())){
			if(parametrosPerdasFiscaisHelper.getCollParametrosPerdasFiscaisItensHelper() != null && !parametrosPerdasFiscaisHelper.getCollParametrosPerdasFiscaisItensHelper().isEmpty()){
				fachada.inserirParametrosPerdasFiscais(parametrosPerdasFiscaisHelper);
			} else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"os dados da tabela");
			}
		} else {
			throw new ActionServletException("atencao.ano_mes_ja_esta_na_base");
		}

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Parâmetro de perdas fiscais "
				+ " inserido com sucesso.", "Inserir outro parâmetro",
				"exibirInserirParametrosPerdasFiscaisAction.do?menu=sim",
				"exibirManterParametrosPerdasFiscaisAction.do"
				,"Manter Parâmetro de Perdas Fiscais");
		
		sessao.removeAttribute("parametrosPerdasFiscaisHelper");


		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirInserirParametrosPerdasFiscaisAction.do");
		if(httpServletRequest.getParameter("menu") != null){
			sessao.removeAttribute("parametrosPerdasFiscaisHelper");
		}
		return retorno;

	}
	private ParametrosPerdasFiscaisHelper criarHelper(
			ParametrosPerdasFiscaisActionForm form) {

		ParametrosPerdasFiscaisHelper retorno = 
				new ParametrosPerdasFiscaisHelper();



		if (Util.verificarNaoVazio(form.getAnoMesReferenciaContabil())){

			Integer anoMesReferenciaContabil = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil());

			retorno.setAnoMesReferenciaContabil(anoMesReferenciaContabil);
		}

		if (Util.verificarNaoVazio(form.getValorABaixar())){

			BigDecimal valorABaixar = Util.formatarMoedaRealparaBigDecimal(form.getValorABaixar());

			retorno.setValorABaixar(valorABaixar);
		}		

		return retorno;
	}

}