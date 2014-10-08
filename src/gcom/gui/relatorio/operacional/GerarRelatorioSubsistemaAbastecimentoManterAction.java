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
package gcom.gui.relatorio.operacional;

import gcom.fachada.Fachada;
import gcom.gui.operacional.FiltrarSubsistemaAbastecimentoActionForm;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroSubsistemaSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.operacional.RelatorioManterSubsistemaAbastecimento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
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
 * Action responsável pela geração do relatório de subsistema de abastecimento
 * 
 * @author Rafael Corrêa
 * @created 07/06/2014
 */
public class GerarRelatorioSubsistemaAbastecimentoManterAction extends ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarSubsistemaAbastecimentoActionForm filtrarSubsistemaAbastecimentoActionForm = (FiltrarSubsistemaAbastecimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = (FiltroSubsistemaSistemaAbastecimento) sessao.getAttribute("filtroSubsistemaSistemaAbastecimento");

		// Inicio da parte que vai mandar os parametros para o relatório

		SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimentoParametros = new SubsistemaSistemaAbastecimento();
		SubsistemaAbastecimento subsistemaAbastecimento = new SubsistemaAbastecimento();

		String codigo = filtrarSubsistemaAbastecimentoActionForm.getCodigo();
		String descricao = filtrarSubsistemaAbastecimentoActionForm.getDescricao();
		String tipoPesquisa = filtrarSubsistemaAbastecimentoActionForm.getTipoPesquisa();
		String descricaoAbreviada = filtrarSubsistemaAbastecimentoActionForm.getDescricaoAbreviada();
		String idSistemaAbastecimento = filtrarSubsistemaAbastecimentoActionForm.getIdSistemaAbastecimento();
		String indicadorUso = filtrarSubsistemaAbastecimentoActionForm.getIndicadorUso();
		
		if (codigo != null && !codigo.equals("")){
			subsistemaAbastecimento.setId(new Integer(codigo));
		}
		
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			subsistemaAbastecimento.setDescricao(descricao);
		}
		
		if (descricaoAbreviada != null && !descricaoAbreviada.equals("")){
			subsistemaAbastecimento.setDescricaoAbreviada(descricaoAbreviada);
		}
		
		if (idSistemaAbastecimento != null && 
			!idSistemaAbastecimento.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, idSistemaAbastecimento));

			Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
			
			SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSistemaAbastecimento);
			
			subsistemaSistemaAbastecimentoParametros.setSistemaAbastecimento(sistemaAbastecimento);
		}
		
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("") && !indicadorUso.equalsIgnoreCase("3")) {
			subsistemaAbastecimento.setIndicadorUso(new Short(indicadorUso));
		}
		
		subsistemaSistemaAbastecimentoParametros.setSubsistemaAbastecimento(subsistemaAbastecimento);

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioManterSubsistemaAbastecimento relatorio = new RelatorioManterSubsistemaAbastecimento((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorio.addParametro("filtroSubsistemaSistemaAbastecimento", filtroSubsistemaSistemaAbastecimento);
		relatorio.addParametro("subsistemaSistemaAbastecimentoParametros",subsistemaSistemaAbastecimentoParametros);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
