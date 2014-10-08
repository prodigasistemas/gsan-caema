/**
 * 
 */
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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirManterSetorAbastecimentoAction extends GcomAction {
	
	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterSetorAbastecimento"); 
		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = this.getFachada();
		
		FiltrarSetorAbastecimentoActionForm form = (FiltrarSetorAbastecimentoActionForm)actionForm;
		String numeroDaPagina = httpServletRequest.getParameter("page.offset");
		
		
		String codigo = form.getCodigo();
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String indicadorPosicaoTexto = form.getIndicadorPosicaoTexto();
		String sistemaAgua = form.getSistemaAgua();
		String subsistemaAgua = form.getSubsistemaAgua();
		String indicadorUso = form.getIndicadorUso();
		String indicadorAtualizar = form.getIndicadorAtualizar();
		
		if(!Util.verificarNaoVazio(numeroDaPagina)){
			numeroDaPagina = String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO);
		}
		
		//Executando a filtragem
		Integer count = fachada.pesquisarQtdSetorAbastecimento(codigo,descricao,descricaoAbreviada,
								   							  indicadorPosicaoTexto,sistemaAgua,
								   							  subsistemaAgua,indicadorUso);
		
		Collection<Object[]> objs = fachada.pesquisarSetorAbastecimento(codigo,descricao,descricaoAbreviada,
													 indicadorPosicaoTexto,sistemaAgua,
													 subsistemaAgua,indicadorUso,numeroDaPagina,false);
		//Nenhum resultado
		if(objs == null || objs.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null,"Setor de Abastecimento");
		}
		
		
		if(count.intValue() == 1 && Util.verificarNaoVazio(indicadorAtualizar) && indicadorAtualizar.equals("1")){
			Object[] obj = (Object[])Util.retonarObjetoDeColecao(objs);
			retorno = actionMapping.findForward("exibirAtualizarSetorAbastecimento");
			return new ActionForward(retorno.getPath() + "?idSetorAbastecimento="+obj[0]);
		}
		
		retorno = this.controlarPaginacao(httpServletRequest, retorno, objs.size());
		if (httpServletRequest.getParameter("page.offset") != null) {

			sessao.setAttribute("page.offset", httpServletRequest.getAttribute("page.offset"));
		}
		sessao.setAttribute("totalRegistros", count);
		sessao.setAttribute("filtrarSetorAbastecimento", objs);
		
		return retorno;
		
	}
	
	public ActionForward excluirSetoresAbastecimento(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso"); 
		FiltrarSetorAbastecimentoActionForm form = (FiltrarSetorAbastecimentoActionForm)actionForm;
		Fachada fachada = this.getFachada();
		
		String[] idsSelecionados = form.getItensSelecionados();
		
		for(String id:idsSelecionados){
			fachada.removerSetorAbastecimento(Integer.parseInt(id));
		}
		
		montarPaginaSucesso(httpServletRequest,
				idsSelecionados.length +
				" Setor(es) de abastecimento removido(s) com sucesso.",
				"Realizar outra manutenção de setor de abastecimento",
				"exibirFiltrarSetorAbastecimentoAction.do?menu=sim");
		
		return retorno;
	
	}
	
	
}