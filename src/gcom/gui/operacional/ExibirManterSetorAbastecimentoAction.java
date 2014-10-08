/**
 * 
 */
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
				"Realizar outra manuten��o de setor de abastecimento",
				"exibirFiltrarSetorAbastecimentoAction.do?menu=sim");
		
		return retorno;
	
	}
	
	
}