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

package gcom.gui.relatorio.faturamento;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioMultasAutosInfracaoPendentes;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioMultasAutosInfracaoPendentesAction extends
ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * 
	 * 
	 * RM 944 - Relat�rio das Multas de Autos de Infra��o Pendentes
	 * 
	 * @author Hugo Azevedo 
	 * @date 11/07/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obt�m a inst�ncia da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		GerarRelatorioMultasAutosInfracaoPendentesActionForm form = (GerarRelatorioMultasAutosInfracaoPendentesActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");		
		TarefaRelatorio relatorio = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		
		//Recuperando os dados do formul�rio
		String idFuncionario = form.getIdFuncionario();
		String grupo = form.getGrupo();

		
		//Inteiros para os IDs
		Integer idFuncionarioINT = null;
		Integer grupoINT = null;
		if(!idFuncionario.equals("") )
			idFuncionarioINT  = new Integer(idFuncionario);
		else
			idFuncionarioINT = new Integer("0");
		if(Integer.parseInt(grupo) != -1)
			grupoINT = new Integer(grupo);
		else
			grupoINT = new Integer("0");

		
		//Nenhum par�metro informado
		if(grupoINT.intValue() == 0 && idFuncionarioINT.intValue() == 0){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		//Recuperando o funcion�rio
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
		Collection colecaoRetornoFunc = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
		Funcionario func = null;
		if(colecaoRetornoFunc.size() > 0){
			func = (Funcionario)Util.retonarObjetoDeColecao(colecaoRetornoFunc);
		}

		//Recuperando o grupo
		FiltroFaturamentoGrupo filtroFG = new FiltroFaturamentoGrupo();
		filtroFG.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupo));
		Collection colecaoRetornoGru = fachada.pesquisar(filtroFG, FaturamentoGrupo.class.getName());
		FaturamentoGrupo fg = null;
		if(colecaoRetornoGru.size() > 0){
			fg = (FaturamentoGrupo)Util.retonarObjetoDeColecao(colecaoRetornoGru);
		}
		
		Collection colecaoMultas = fachada.pesquisarDadosRelatorioAutoInfracaoPendentes(grupoINT,idFuncionarioINT);
		
		
		//Nenhum par�metro retornado
		if(colecaoMultas == null || colecaoMultas.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "relat�rio de multas de autos de infra��o pendentes");
		}
		
		
		
		relatorio = new RelatorioMultasAutosInfracaoPendentes(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		
		relatorio.addParametro("colecaoMultas",colecaoMultas);
		relatorio.addParametro("funcionario",func);
		relatorio.addParametro("grupo",fg);

		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		montarPaginaSucesso(httpServletRequest,
				"Relat�rio gerado com sucesso",
				"Gerar outro relat�rio",
				"exibirGerarRelatorioMultasAutosInfracaoPendentesAction.do?limparForm=OK");
		
		return retorno;
	}
}
